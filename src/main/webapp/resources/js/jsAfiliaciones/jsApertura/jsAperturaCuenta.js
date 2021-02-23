$(".simple-select").select2({
    minimumResultsForSearch: Infinity
});

$(".simple-select2").select2();

var limiteSocios;
var tipodocumento;
var tiposocio;

//tasas de interes y montos
var vTasaPreferenteChk = 0;
var vInteresAdelantadoChk = false;
var vTasaVigente = 0;
var vTea = 0;
var vTasaPreferente = 0;
var vTeaPreferente = 0;
var vTasaVigenteAdelantada = 0;
var vTeaAdelantada = 0;
var vNumCtaDes = '0';
var vTipoPago = '';


//
var tea;


$(document).ready(function () {

    $('#btnSocioModal').click(function () {
        return false;
    });

    $('#btnRegistrarApertura').click(function (e) {
        e.preventDefault();
        var validator = $("#frmRegistrarApertura").valid();

        if (validator == true) {
            aperturarCuenta();
        } else {
            toast('warning', "Usted debe ingresar correctamente los datos requeridos", 'warning');
        }
    });


    reiniciarParametros();
    function reiniciarParametros() {

        document.getElementById("cboTipoApertura").disabled = true;
        document.getElementById("cboTipoCuenta").disabled = true;
        document.getElementById("btnRegistrarApertura").disabled = false;
        document.getElementById("btnValidarApertura").disabled = true;
        $("#cboTipoSocio").val('default').trigger('change.select2');
        $("#cboTipMoneda").val('default').trigger('change.select2');
        $("#cboTipoApertura").val('default').trigger('change.select2');
        $("#cboTipoCuenta").val('default').trigger('change.select2');
        //$("#cboTipoSocio").val('').trigger('change.select2');
        limpiarServlet();
        desabilitarDisposicionInteres();
    }


    /* FUNCION QUE SE EJECUTA AL SELECCIONAR UNA OPCION DEL SELECT TIPO SOCIO.
     EN EL CUAL SEGUN EL TIPO EJECUTA KIS EVENTOS OBTENER TIPO APARTURA Y TIPO CUENTA
     */
    $('#cboTipoSocio').on('change', function (e) {
        obtenerTipoMoneda();
        tiposocio = this.value;

        if (tiposocio == 'N') {
            $('#txtidTipCTA').val('Av');
        } else {
            document.getElementById('txtidTipCTA').value = 'Jv';
        }

        if ($('#cboTipMoneda').val() != 'default') {
            document.getElementById("cboTipoApertura").disabled = false;
            document.getElementById("chkDefDep").disabled = false;
            document.getElementById("chkRepSal").disabled = false;
            document.getElementById('txtMontoMinimo').value = '0.00';
            obtenerTipoApertura();
            obtenerTipoCuenta();
        }
    });

    /*
     FUNCION QUE SE EJECUTA AUTOMATICAMENTE AL SELECCIONAR UN TIPO SOCIO
     SELECCIONA LA PRIMERA OPCION DEL SELECT Y HABILITA EL COMBO DE APERTURA Y 
     LOS CHECKBOX
     */
    function obtenerTipoMoneda() {
        $("#cboTipMoneda").val('1').trigger('change.select2');
        document.getElementById('txtidTipMonedaCTA').value = $('#cboTipMoneda').val();
        document.getElementById("cboTipoApertura").disabled = false;
        document.getElementById("chkDefDep").disabled = false;
        document.getElementById("chkRepSal").disabled = false;
        document.getElementById('txtMontoMinimo').value = '0.00';
    }
    ;

    /* 
     * CUANDO SE REALIZE UNA SELECCION EN EL SELECT MONEDA, ESTA HABILITARA LOS CHECK BOX Y LLAMARA A LA FUNCION OBTENER TIPO APERTURA.
     */
    $('#cboTipMoneda').on('change', function (e) {
        LimpiarEnTipoCuenta();
        document.getElementById('txtidTipMonedaCTA').value = this.value;
        document.getElementById("cboTipoApertura").disabled = false;
        document.getElementById("chkDefDep").disabled = false;
        document.getElementById("chkRepSal").disabled = false;
        document.getElementById('txtMontoMinimo').value = '0.00';
        obtenerTipoCuenta();
    });


    /* 
     *FUNCION DONDE SE POBLA EL COMBO DE TIPO DE APERTURA
     * 
     */
    function obtenerTipoApertura() {

        var data = {'tipoPersona': $("#cboTipoSocio").val()};
        $('#cboTipoApertura').empty();
        var success = function (data) {

            $("#cboTipoApertura").append("<option value='default' option selected='selected'>Seleccione un tipo de Apertura</option>");
            $.each(data.listTipoApertura, function (i, item) {
                $("#cboTipoApertura").append("<option value='" + item.IdTipoApertura + "'>" + item.Descripcion + "</option>");

            });
            $("#cboTipoApertura").val('default').trigger('change.select2');
        };
        var error = function () { };

        fn_callmethod("../sTipoApertura?action=obtenerTipoApertura", data, success, error);
    };

    /*
     ESTA FUNCION INFORMA LA CANTIDAD DE SOCIOS RESPONSABLES SEGUN EL TIPO DE APERTURA. 
     */
    $('#cboTipoApertura').on('change', function (e) {

        var table = $('#tblSocios').DataTable();
        table.clear().draw();
        limpiarServlet();

        switch (this.value) {
            case '0901':
                document.getElementById('txtNroMaxIntegrantes').value = '1 Socio Responsable';
                limiteSocios = 1;
                break;
            case '0902':
                document.getElementById('txtNroMaxIntegrantes').value = 'Hasta 3 Socios Responsables';
                limiteSocios = 3;
                break;
            default:
                document.getElementById('txtNroMaxIntegrantes').value = 'Hasta 4 Socios Responsables';
                limiteSocios = 4;
        }
        document.getElementById("cboTipoCuenta").disabled = false;
        limpiarServlet();
        $('#cboCuentaSocio option').remove();
        $("#cboCuentaSocio").append("<option value='default' option selected='selected'>Seleccione una cuenta socio</option>");

        $('#cboCuentasIA option').remove();
        $("#cboCuentasIA").append("<option value='default' option selected='selected'>Seleccione un tipo de Cuenta </option>");

    });


    $('#cboTipoPago').on('change', function (e) {


        switch (this.value) {
            case '0000':
                document.getElementById("cboCuentaSocio").disabled = true;
                document.getElementById("cboCuentasIA").disabled = true;
                vTipoPago = '';
                break;
            case '0001':
                document.getElementById("cboCuentaSocio").disabled = false;
                document.getElementById("cboCuentasIA").disabled = false;
                llenarcboSociosResponsables();
                vTipoPago = '0001';
                break;
        }

    });


});

/*FUNCION QUE POBLA EL SELECT CON LOS TIPO DE APERTURA EN BASE AL TIPO DE CUENTA*/
function obtenerTipoCuenta() {

    LimpiarEnTipoCuenta();

    var datos = {'tipMoneda': $("#cboTipMoneda").val(), 'tipoPersona': $('#cboTipoSocio').val()};

    var success = function (data) {

        $("#cboTipoCuenta").html("");
        $("#cboTipoCuenta").append("<option value='default' option selected='selected' >Seleccione un tipo de Cuenta</option>");
        $.each(data.infoTipoCuenta, function (i, item) {

            $("#cboTipoCuenta").append("<option value='" + item.IdTipCta + "' id='" + item.MontoMinimo + "'>" + item.Descripcion + "</option>");

        });
    };
    var error = function () {
    };
    fn_callmethod("../sTipoCuenta?action=obtenerTipoCuenta", datos, success, error);
}
;

$('#cboTipoCuenta').on('change', function (e) {
    LimpiarEnTipoCuenta();
    var optionSelected = $("option:selected", this);
    var minimoSelected = $(this).find(optionSelected).attr('id');
    var valueSelected = this.value;
    document.getElementById('txtidTipCTA').value = valueSelected;
    document.getElementById('txtMontoMinimo').value = minimoSelected;
    document.getElementById("txtMontoApertura").disabled = false;

    if (valueSelected == 'P4' || valueSelected == 'P5' || valueSelected == 'P6') {
        document.getElementById("inlineRadio3").disabled = false;
    } else {
        document.getElementById("inlineRadio3").disabled = true;
        document.getElementById("inlineRadio_1").checked = false;
    }

    switch (this.value) {
        case 'P4':
            tea = 1;
            break;
        case 'P5':
            tea = 2;
            break;
        case 'P6':
            tea = 3;
            break;
    }
});

$('#cboCuentaSocio').on('change', function (e) {

    var idPersona = this.value;
    var idTipMoneda = document.getElementById('txtidTipMonedaCTA').value;

    obtenerCuentasIA(idPersona, idTipMoneda);

});

$('#txtAhorroPreferente').on('keyup', function (e) {

    tasa = document.getElementById('txtAhorroPreferente').value;
    document.getElementById('txtTeaPreferente').value = tasa / tea;

});



//FUNCION QUE OBTIENE LOS % DE INTERES EN BASE AL MONTO INGRESADO
$("#txtMontoApertura").on('keyup', function (e) {
    e.preventDefault();
    if (e.keyCode == 13) {
        e.preventDefault();

        var Monto = convertirDecimales(document.getElementById('txtMontoApertura').value);
        var idTipCta = document.getElementById('cboTipoCuenta').value;
        var tipMoneda = document.getElementById('cboTipMoneda').value;
        var MontoMinimo = convertirDecimales(document.getElementById('txtMontoMinimo').value);

        if ((Math.round(Monto * 100) >= Math.round(MontoMinimo * 100)) && Monto != '') {
            obtenerTasaxMonto(idTipCta, tipMoneda, Monto);
            obtenerTipoPagoInteres();
        }
        document.getElementById("inlineRadio1").checked = true;
        this.value = Monto;
        $('#chkDefDep').focus();

    }
});

function  convertirDecimales(numero) {

    var numeroDecimal;
    numeroDecimal = parseFloat(numero).toFixed(2);
    numeroDecimal = (numeroDecimal.replace(/[^0-9\.-]+/g, ""));
    return numeroDecimal;
}

//OBTIENE LAS TASA POR MONTOS  
function obtenerTasaxMonto(IdTipCta, TipMoneda, Monto) {
    var datos = {'IdTipCta': IdTipCta, 'TipMoneda': TipMoneda, 'Monto': Monto};

    var success = function (data) {
        if (data.Result == 'OK') {
            document.getElementById('txtAhorroNormal').value = data.infoTasaxMonto.TasaVigente;
            document.getElementById('txtTeaNormal').value = data.infoTasaxMonto.TEAVigente;
            document.getElementById('txtAhorroAdelantada').value = data.infoTasaxMonto.TasaAdelantada;
            document.getElementById('txtTeaAdelantada').value = data.infoTasaxMonto.TEAAdelantada;

            $('#btnSocioModal').unbind('click');
            var fechaVence = sumaDias(30);
            $('#txtFechaVence').text('Al Vencimiento Certificado del Deposito al ' + fechaVence);
            document.getElementById('txtFechaVence').style.fontWeight = 'bold';

        } else if (data.Result == 'error') {
            toast('error', data.error, 'Error');
        }

    };
    var error = function () {
        console.log("error");
    };
    fn_callmethod("../sTipoCuenta?action=obtenerTasaxMonto", datos, success, error);
}
;

//FUNCION QUE OBTIENE LA ULTIMA FECHA DEL MES PARA EL CALCULO DE LAS FECHAS
function sumaDias(days) {
    //var result = new Date(date);
    var today = new Date();
    today.setDate(today.getDate() + days);
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!

    var yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    var today = dd + '/' + mm + '/' + yyyy;
    return today;
}

function limpiarServlet() {

    var data = {};

    var success = function (data) {
    };
    var error = function () {
    };
    fn_callmethod("../sSocio?action=limpiarSession", '', success, error);

}
;

function obtenerTipoPagoInteres() {

    var datos = {};

    var success = function (data) {

        $('#cboTipoPago').empty();
        $("#cboTipoPago").append("<option value='default' option selected='selected' >Seleccione una opcion</option>");
        $.each(data.tipoPagoInteres, function (i, item) {
            $("#cboTipoPago").append("<option value='" + item.idTipoPagoInteres + "'>" + item.descripcion + "</option>");
        });
        // parseSelect2('cboTipoPago');

    };
    var error = function () {

    };
    fn_callmethod("../sTipoCuenta?action=obtenerTipoPagoInteres", datos, success, error);
}
;

function llenarcboSociosResponsables() {

    $('#cboCuentaSocio').empty();
    $('#cboCuentasIA').empty();

    //limpiar combo de Interes adelantado
    var success = function (data) {

        $("#cboCuentaSocio").append("<option disabled selected value> -- Selecciones Una Opcion -- </option>");
        $.each(data.listaPersona, function (i, item) {
            $("#cboCuentaSocio").append("<option value='" + item.idPersona + "'>" + item.nombres + "</option>");
        });
        $("#cboCuentaSocio").val('default').trigger('change.select2');

    };
    var error = function () {};

    fn_callmethod("../sSocio?action=obtenerlistaSocioResponsable", '', success, error);
}
;

$("input[id='inlineRadio_1']").change(function () {
    obtenerTipoPagoInteres();
    document.getElementById("cboTipoPago").disabled = true;
    document.getElementById("cboCuentaSocio").disabled = true;
    document.getElementById("cboCuentasIA").disabled = true;
    document.getElementById("inlineRadio1").checked = true;
    document.getElementById("cboCuentasIA").disabled = true;
    vInteresAdelantadoChk = false;
});

$("input[id='inlineRadio_2']").change(function () {
    document.getElementById("cboCuentaSocio").disabled = true;
    document.getElementById("cboCuentasIA").disabled = true;
    document.getElementById("cboTipoPago").disabled = false;
    document.getElementById("inlineRadio2").checked = true;
    vInteresAdelantadoChk = true;
});

//verifica si esta marcado o no el checkbox

$('form input[type=checkbox]').change(function () {
    if ($('#inlineRadio3').is(":checked"))
    {
        document.getElementById("txtAhorroPreferente").disabled = false;
        document.getElementById("txtAhorroPreferente").value = '';

    } else
    {
        document.getElementById("txtAhorroPreferente").disabled = true;
        document.getElementById("txtAhorroPreferente").value = '';
    }
});


function LimpiarEnTipoCuenta()
{

    var table = $('#tblSocios').DataTable();
    table.clear().draw();
    limpiarServlet();
    vTipoPago = '';
    vTasaPreferenteChk = 0;
    vInteresAdelantadoChk = false;
    vTasaVigente = 0;
    vTea = 0;
    vTasaPreferente = 0;
    vTeaPreferente = 0;
    vTasaVigenteAdelantada = 0;
    vTeaAdelantada = 0;
    vNumCtaDes = '0';
    document.getElementById("chkDefDep").checked = false;
    document.getElementById("chkRepSal").checked = false;
    document.getElementById("txtMontoApertura").value = '';
    document.getElementById("txtAhorroNormal").value = '';
    document.getElementById("txtTeaNormal").value = '';
    document.getElementById("txtAhorroAdelantada").value = '';
    document.getElementById("txtTeaAdelantada").value = '';
    document.getElementById("txtAhorroPreferente").value = '';
    document.getElementById("txtTeaPreferente").value = '';
    document.getElementById("inlineRadio_1").disabled = true;
    document.getElementById("inlineRadio_1").cheked = true;
    document.getElementById("inlineRadio_2").disabled = true;
    document.getElementById("cboTipoPago").disabled = true;
    document.getElementById("cboCuentaSocio").disabled = true;
    document.getElementById("cboCuentasIA").disabled = true;


}

function aperturarCuenta()
{
    //SETTEANDO VALORES POR DEFECTO DE LAS VARIABLES
    var vIdPersonaT = 0;
    var vIdPersona2 = 0;
    var vIdPersona3 = 0;
    var vIdPersona4 = 0;
    var vTipMoneda = 0;
    var vIdTipCta = '';
    var vMontoSoles = 0;
    var vMontoDolares = 0;
    var vIdOficina = '01'; //Sesion
    var vTipoApe = '';
    var vUsuario = ''; //Sesion
    var vDifDepositosChk = 0;
    var vRepSaldosChk = 0;
    var vGlosaTipoCuenta = '';
    var vIdOficinaDes = '';
    var vTipMonedaDes = '';
    var vIdTipCtaDes = '';
    var vNumCtaDes = 0;
    var vTipPagoInteres = '';
    var vTipoPagoInteres = '';

    //SETTEANDO VALORES A LAS VARIABLES
    //
    //Id Personas

    $('#tblSocios tbody tr').each(function () {
        $(this).find('td:eq(2)').each(function () {
            //alert($(this).find('input[name="titular"]').attr('Value'));

            if ($(this).find('input[name=idRadio]:checked').is(':checked'))
            {
                vIdPersonaT = $(this).find('input[name="idRadio"]').attr('Value');
            } else if (vIdPersona2 == 0)
            {
                vIdPersona2 = $(this).find('input[name="idRadio"]').attr('Value');
            } else if (vIdPersona3 == 0)
            {
                vIdPersona3 = $(this).find('input[name="idRadio"]').attr('Value');
            } else if (vIdPersona4 == 0)
            {
                vIdPersona4 = $(this).find('input[name="idRadio"]').attr('Value');
            }

        });
    });

    var vTipMoneda = $('#txtidTipMonedaCTA').val();
    var vIdTipCta = $('#txtidTipCTA').val();

    if ($('#cboTipMoneda').val() == '1')
    {
        vMontoSoles = $('#txtMontoApertura').val();
    } else
    {
        vMontoDolares = $('#txtMontoApertura').val();
    }

    vIdOficina = $('#txtidoficinaCTA').val();

    vTipoApe = $('#cboTipoApertura').val();

    vUsuario = 'Melroj';

    if (document.getElementById("chkDefDep").checked == true) {
        vDifDepositosChk = true;
    } else {
        vDifDepositosChk = false;
    }
    if (document.getElementById("chkRepSal").checked == true) {
        vRepSaldosChk = true;
    } else {
        vRepSaldosChk = false;
    }
    if (document.getElementById("inlineRadio1").checked == true) {
        vTasaVigente = $('#txtAhorroNormal').val();
        vTea = $('#txtTeaNormal').val();
        vTasaPreferente = 0;
        vTeaPreferente = 0;
        vTasaVigenteAdelantada = 0;
        vTeaAdelantada = 0;
        vTasaPreferenteChk = false;
    }
    if (document.getElementById("inlineRadio2").checked == true) {

        vTasaVigente = 0;
        vTea = 0;
        vTasaPreferente = 0;
        vTeaPreferente = 0;
        vTasaVigenteAdelantada = $('#txtAhorroAdelantada').val();
        vTeaAdelantada = $('#txtTeaAdelantada').val();

        vTasaPreferenteChk = false;
    }
    if (document.getElementById("inlineRadio3").checked == true) {
        vTasaVigente = 0;
        vTea = 0;
        vTasaPreferente = $('#txtAhorroPreferente').val();
        vTeaPreferente = $('#txtTeaPreferente').val();
        vTasaVigenteAdelantada = 0;
        vTeaAdelantada = 0;
        vTasaPreferenteChk = true;
    }
    ;

    var t = document.getElementById("cboTipoCuenta");
    vGlosaTipoCuenta = t.options[t.selectedIndex].text;
    //alert(document.getElementById('cboTipoPago').value);

    if (document.getElementById("inlineRadio_1").checked == true) {
        vNumCtaDes = '0';
    }

    if (document.getElementById("inlineRadio_2").checked == true) {
        if (document.getElementById('cboTipoPago').value == '0001') {
            var t = document.getElementById("cboCuentasIA");
            vNumCtaDes = t.options[t.selectedIndex].text;
            var n = vNumCtaDes.length;
            // alert(n);
            if (n != 15) {
                vNumCtaDes = 0;
            }
        }
    } else {
        vNumCtaDes = 0;
    }

    var data = {
        'IdPersonaT': vIdPersonaT,
        'IdPersona2': vIdPersona2,
        'IdPersona3': vIdPersona3,
        'IdPersona4': vIdPersona4,
        'TipMoneda': vTipMoneda,
        'IdTipCta': vIdTipCta,
        'MontoSoles': vMontoSoles,
        'MontoDolares': vMontoDolares,
        'IdOficina': vIdOficina,
        'TipoApe': vTipoApe,
        'Usuario': vUsuario,
        'DifDepositosChk': vDifDepositosChk,
        'RepSaldosChk': vRepSaldosChk,
        'GlosaTipoCuenta': vGlosaTipoCuenta,
        'InteresAdelantadoChk': vInteresAdelantadoChk,
        'TasaVigente': vTasaVigente,
        'Tea': vTea,
        'TasaPreferente': vTasaPreferente,
        'TeaPreferente': vTeaPreferente,
        'TasaVigenteAdelantada': vTasaVigenteAdelantada,
        'TeaAdelantada': vTeaAdelantada,
        'TasaPreferenteChk': vTasaPreferenteChk,
        'TipoPago': vTipoPago,
        'NumCtaDes': vNumCtaDes
    };
    bootbox.confirm("\u00BFEst\u00e1 seguro que desea registrar a esta Apertura?\u003F", function (result) {
        if (result) {
            registrarAperturarCuenta(data);
        }
    });
}


$('#btnValidarApertura').click(function (e) {
    e.preventDefault();

    var data = {
        'TipMoneda': document.getElementById("txtidTipMonedaCTA").value,
        'IdTipCta': document.getElementById("txtidTipCTA").value,
        'IdOficina': document.getElementById("txtidoficinaCTA").value,
        'NumCuenta': document.getElementById("txtNumCTA").value
    };
    var success = function (res) {

        if (res.Result == 'OK') {
            if (res.resultado != 0) {
                abrirVentana(ReportSvr + '?%2fPyAfiliaciones%2fRptCertificadoPlazoFijo&rs:Command=Render&' +
                        'IdOficina=' + document.getElementById("txtidoficinaCTA").value +
                        '&TipMoneda=' + document.getElementById("txtidTipMonedaCTA").value +
                        '&IdTipCta=' + document.getElementById("txtidTipCTA").value +
                        '&NumCuenta=' + document.getElementById("txtNumCTA").value +
                        '&TipoImpresion=' + 1 +
                        '&Monto=' + document.getElementById("txtMontoApertura").value);
                setTimeout("location.reload(true);", 5000);
            } else if (res.resultado == 0) {
                toast('error', 'No se ha aprobado la Apertura', 'Error!');
            }
        } else {
            toast('error', 'No se ha aprobado la Apertura!', 'Error!');
        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sAperturaCuenta?action=ValidarApertura", data, success, error);
});


function registrarAperturarCuenta(data) {

    var success = function (res) {

        if (res.Mensaje.length == 7) {
            document.getElementById("txtNumCTA").value = res.Mensaje;
            toast('success', 'Renovacion Exitosa', 'Exito!');

            if (document.getElementById("txtidTipCTA").value.substring(0, 1) == 'P') {
                if ($('#inlineRadio3').is(':checked') == false) {
                    abrirVentana(ReportSvr + '?%2fPyAfiliaciones%2fRptCertificadoPlazoFijo&rs:Command=Render&' +
                            'IdOficina=' + document.getElementById("txtidoficinaCTA").value +
                            '&TipMoneda=' + document.getElementById("txtidTipMonedaCTA").value +
                            '&IdTipCta=' + document.getElementById("txtidTipCTA").value +
                            '&NumCuenta=' + document.getElementById("txtNumCTA").value +
                            '&TipoImpresion=' + 1 +
                            '&Monto=' + document.getElementById("txtMontoApertura").value);
                    setTimeout("location.reload(true);", 5000);

                } else {
                    document.getElementById("btnRegistrarApertura").disabled = true;
                    document.getElementById("btnValidarApertura").disabled = false;
                    setTimeout("location.reload(true);", 5000);
                }
            }



        } else {
            toast('error', res.Mensaje, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sAperturaCuenta?action=RegApertura", data, success, error);
}
;

function desabilitarDisposicionInteres() {
    $("#inlineRadio_1").prop("disabled", true); // disable
    $("#inlineRadio_2").prop("disabled", true); // disable
    $("#cboTipoPago").prop("disabled", true); // disable
    $("#cboCuentaSocio").prop("disabled", true); // disable
    $("#cboCuentasIA").prop("disabled", true); // disable  
}

$('#txtMontoApertura').change(function () {


    document.getElementById('txtAhorroNormal').value = '';
    document.getElementById('txtTeaNormal').value = '';
    document.getElementById('txtAhorroAdelantada').value = '';
    document.getElementById('txtTeaAdelantada').value = '';

});

