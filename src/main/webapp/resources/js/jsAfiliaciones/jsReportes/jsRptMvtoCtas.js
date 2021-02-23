$(document).ready(function () {
    $(".simple-select2").select2();

    $(".simple-select").select2({
        minimumResultsForSearch: Infinity
    });

    $('#txtFechaInicio').datepicker('setDate', '01/02/2006');
    $('#txtFechaInicio').datepicker('update');
    $('#txtFechaFin').datepicker('setDate', ObtenerFechaActualDMY());
    $('#txtFechaFin').datepicker('update');

    obtenerTipCuenta();

    function obtenerTipCuenta() {
        var data = {'cboProductos': $("#cboProductos").val(), 'cboTipMoneda': $("#cboTipMoneda").val()};

        $('#cboTipcta').empty();
        var success = function (data) {

            $.each(data.listTipCta, function (i, item) {
                $("#cboTipcta").append("<option value='" + item.IdTipCta + "'>" + item.Descripcion + "</option>");

            });
            if ($("#cboProductos").val() == '05') {
                $("#cboTipcta").val('P1').trigger('change.select2');
            } else {
                $("#cboTipcta").val('Av').trigger('change.select2');
            }


        };
        var error = function () { };

        fn_callmethod("../sTipoCuenta?action=obtenerTipCtaMovCtas", data, success, error);
    }
    ;

    function obtenerMotivo() {
        var data = {'cboEstadoCta': $("#cboEstadoCta").val()};

        $('#cboMotivo').empty();
        var success = function (data) {
            $("#cboMotivo").append("<option value='99'>TODOS</option>");
            $.each(data.lisMovCta, function (i, item) {
                $("#cboMotivo").append("<option value='" + item.IdEstadoCuenta + "'>" + item.Estado + "</option>");

            });
            $("#cboMotivo").val('99').trigger('change.select2');


        };
        var error = function () { };

        fn_callmethod("../sEstadoCuenta?action=obtenerMotivoEstado", data, success, error);
    }
    ;

    $('#cboEstadoCta').on('change', function (e) {
        obtenerMotivo();
    });

    $('#cboTipMoneda').on('change', function (e) {
        obtenerTipCuenta();
    });

    $('#cboProductos').on('change', function (e) {
        obtenerTipCuenta();
    });

    $('#frmRptMovCtas').submit(function (event) {
        event.preventDefault();
        console.log("1");
        obtenerAccesoOpexUsuarioRptMvtoCtas().then(function (listaAcceso) {
            if (listaAcceso[0][1] = 1) {
                bootbox.prompt("INGRESE UN MOTIVO", function (result) {
                    if (result != null) {
                        if (result.length >= 4) {
                            fn_registrarBitacora('RptMovCtas', '', result).then(function (rpta) {
                                if (rpta == true) {
                                    obtenerReporteMovimientoCuentas();
                                } else {
                                    toast('error', 'Error en registrar bitacora', 'ERROR');
                                }

                            });
                        } else {
                            bootbox.alert({
                                message: "Error... Debes ingresar un detalle",
                                className: 'bb-alternate-modal'
                            });
                        }
                    } else if (result == null) {


                    }
                });
            }
        });


    });


    var obtenerAccesoOpexUsuarioRptMvtoCtas = function () {
        var promise = new Promise(function (resolve, reject) {
            var success = function (res) {
                if (res.Result == 'OK') {
                    resolve(res.listaAcceso);

                } else {
                    reject(res.Message);
                }
            };
            var error = function (data, status, er) {
                reject("error");
            };
            fn_callmethod_PostND("../sAccesoOpexUsuario?action=obtenerAccesoOpexUsuarioRptMvtoCtas", success, error);
        });
        return promise;
    };
});

var obtenerReporteMovimientoCuentas = function () {
    var IdOficina = ($("#cboOficina").val() == '00') ? '' : '&IdOficina=' + $("#cboOficina").val();
    var TipMoneda = $("#cboTipMoneda").val();
    var IdProducto;
    var IdTipCta = ($("#cboTipcta").val() == '99') ? '' : '&IdTipCta=' + $("#cboTipcta").val();
    var IdTipoApertura = ($("#cboTipApe").val() == '0000') ? '' : '&IdTipoApertura=' + $("#cboTipApe").val();
    var IdEstadoCta2 = ($("#cboEstadoCta").val() == '99') ? '' : '&IdEstadoCta2=' + $("#cboEstadoCta").val();
    var FechaIni = $("#txtFechaInicio").val();
    var FechaFin = $("#txtFechaFin").val();
    var IdEstadoCta = ($("#cboMotivo").val() == '99') ? '' : '&IdEstadoCta=' + $("#cboMotivo").val();
    var IdProducto2, IdEstadoCta;




    if ($("#cboProductos").val() == '99') {
        IdProducto = '&IdProducto=04',
                IdProducto2 = '&IdProducto2=05';
    } else {
        IdProducto = '&IdProducto=' + $("#cboProductos").val(),
                IdProducto2 = '&IdProducto2=' + $("#cboProductos").val();
    }

    console.log(ReportSvr + '?%2fPyAfiliaciones%2frptMovimientosAhorros&rs:Command=Render&TipMoneda=' + TipMoneda
            + IdOficina
            + IdTipCta
            + IdTipoApertura
            + IdEstadoCta
            + IdEstadoCta2
            + '&FechaIni=' + FechaIni
            + '&FechaFin=' + FechaFin
            + IdProducto
            + IdProducto2);
    abrirVentana(ReportSvr + '?%2fPyAfiliaciones%2frptMovimientosAhorros&rs:Command=Render&TipMoneda=' + TipMoneda
            + IdOficina
            + IdTipCta
            + IdTipoApertura
            + IdEstadoCta
            + IdEstadoCta2
            + '&FechaIni=' + FechaIni
            + '&FechaFin=' + FechaFin
            + IdProducto
            + IdProducto2);
}

$("#txtFechaInicio").datepicker({
    changeMonth: true,
    changeYear: true,
    dateFormat: 'dd/mm/yyyy',
    defaultDate: '01/02/2006',
    yearRange: '1900:2999'

}).on('change', function () {
    $(this).valid();
});

$("#txtFechaFin").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '1900:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});

$('#data_1 .input-group.date').datepicker({
    todayBtn: "linked",
    keyboardNavigation: false,
    forceParse: false,
    calendarWeeks: true,
    autoclose: true
});