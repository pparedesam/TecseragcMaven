var idpersona = '';
var listTipoCtaBanco = '';
var listCtasBanco = [];
var objCtasBanco = new Object();
var objTipoCtaBanco = new Object();
var objTipoMoneda = new Object();
var fecha;
$(function () {

    fecha = new Date();
    $('#txtFechaApertura').datepicker('update', fecha);
    $('#txtFechaApertura').datepicker('setEndDate', fecha);
    obtenerBancos();
    obtenerListaTipoCtaBanco();
    listarCtaBanco(listCtasBanco);
});

$('#txtFechaFin').datepicker().on('changeDate', function (e) {

    var tmpfecha = $('#txtFechaApertura').datepicker('getDate');



    if ($('#txtFechaApertura').val() == '') {
        $('#txtFechaApertura').datepicker('update', fecha);
    } else {
        fecha = new Date($('#txtFechaApertura').datepicker('getDate'));
    }
});


$('#txtFechaApertura').datepicker({
    format: 'dd/mm/yyyy',
    language: 'es',
    autoclose: true,
});


var obtenerBancos = function () {

    var success = function (res) {
        if (res.Result == 'OK') {
            //limpiar();
            listarBancos(res.listBancos);

        } else if (res.Result == 'error') {

            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er) {
        // toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sBanco?action=listarBancosNEW", "", success, error);
};

function cargarBanco(idpersona) {

    var data = {'idPersona': idpersona};
    var success = function (res) {
        if (res.Result == 'OK') {
            $('#txtRazonSocial').val(res.Banco.nombreBanco);
            $('#txtRUC').val(res.Banco.objPersonaBanco.nroDocumento);
            $('#txtAbreviatura').val(res.Banco.nombreAbv);
            document.getElementById("txtAbreviatura").readOnly = true;

        } else if (res.Result == 'error') {

            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er) {
        // toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sBanco?action=obtenerBanco", data, success, error);
}

function cargarCtasBanco(idpersona) {

    var data = {'idPersona': idpersona};
    var success = function (res) {
        if (res.Result == 'OK') {
            listCtasBanco.length = 0;
            var tmplistCtasBanco = [];
            tmplistCtasBanco = res.listCtaBanco;
            tmplistCtasBanco.forEach(function (elemento, indice) {
                var tmpObjDescuentoCredito = new Object();

                var tmpObjTipCtaBanco = new Object();
                tmpObjTipCtaBanco.idTipCtaBanco = elemento.objTipoCtaBanco.idTipCtaBanco;
                tmpObjTipCtaBanco.tipoCtaBanco = elemento.objTipoCtaBanco.tipoCtaBanco;

                var tmpObjTipoMoneda = new Object();
                tmpObjTipoMoneda.id = elemento.objTipoMoneda.id;
                tmpObjTipoMoneda.descripcion = elemento.objTipoMoneda.descripcion;

                //tmpObjDescuentoCredito.idPersona = elemento.objBanco.objPersonaBanco.idPersona;
                tmpObjDescuentoCredito.objTipoCtaBanco = tmpObjTipCtaBanco;
                //tmpObjDescuentoCredito.objTipoCtaBanco.descripcion = elemento.objTipoCtaBanco.descripcion;
                tmpObjDescuentoCredito.ctaBanco = elemento.ctaBanco;
                tmpObjDescuentoCredito.objTipoMoneda = tmpObjTipoMoneda;
                //tmpObjDescuentoCredito.objTipoMoneda.descripcion = elemento.objTipoMoneda.descripcion;
                tmpObjDescuentoCredito.fechaApertura = elemento.fechaApertura;
                tmpObjDescuentoCredito.accion = 'N';
                listCtasBanco.push(tmpObjDescuentoCredito);
            });

            listarCtaBanco(listCtasBanco);

        } else if (res.Result == 'error') {

            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er) {
        // toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sCtaBanco?action=obtenerCuentasBanco", data, success, error);
}

function obtenerListaTipoCtaBanco() {
    var success = function (res) {

        listTipoCtaBanco = '';
        listTipoCtaBanco = res.listTipoCtaBanco;
        listarTipoCtaBancos();
    };
    var error = function () {};
    fn_callmethod("../sTipoCtaBanco?action=obtenerTipoCtasBanco", "", success, error);
}

function listarTipoCtaBancos() {

    $("#cboTipoCuenta").empty();
    $.each(listTipoCtaBanco, function (i, item) {
        $("#cboTipoCuenta").append("<option value='" + item.idTipCtaBanco + "'>" + item.tipoCtaBanco + "</option>");
    });
    $('#cboTipoCuenta').select();
    $('#cboTipoCuenta  ').trigger('change.select2');
    //    obtenerTipoCreditoSBS();

}
;

function TabSelec(idtab) {
    $('#tabBancos a[href="#' + idtab + '"]').tab('show');
//    $("#tabBancos a").click(function () {
//        $("[id*=" + idtab + "]").val($(this).attr("href").replace("#", ""));
//    });
    // $("#txtVariable").val(idtab);
}
;

$("#btnAgregarCtaBanco").click(function () {

    if (validacion()) {

        var tmpObjDescuentoCredito = new Object();

        var tmpObjTipCtaBanco = new Object();
        tmpObjTipCtaBanco.idTipCtaBanco = $("#cboTipoCuenta").val();
        tmpObjTipCtaBanco.tipoCtaBanco = $('#cboTipoCuenta').find('option:selected').text();

        var tmpObjTipoMoneda = new Object();
        tmpObjTipoMoneda.id = $("#cboTipMoneda").val();
        tmpObjTipoMoneda.descripcion = $('#cboTipMoneda').find('option:selected').text();

//    tmpObjDescuentoCredito.idTipCtaBanco = $("#cboTipoCuenta").val();
//    tmpObjDescuentoCredito.descripcionCtaBanco = $('#cboTipoCuenta').find('option:selected').text();
        tmpObjDescuentoCredito.ctaBanco = $("#txtNroCtaBanco").val();
//    tmpObjDescuentoCredito.idMoneda = $("#cboTipMoneda").val();
        tmpObjDescuentoCredito.objTipoCtaBanco = tmpObjTipCtaBanco;
        tmpObjDescuentoCredito.objTipoMoneda = tmpObjTipoMoneda;
        tmpObjDescuentoCredito.Monedas = $('#cboTipMoneda').find('option:selected').text();
        tmpObjDescuentoCredito.cuentaContable = $("#txtCuentaContable").val();
        tmpObjDescuentoCredito.fechaApertura = $("#txtFechaApertura").val();
        tmpObjDescuentoCredito.accion = 'I';

        var existe = 0;
        listCtasBanco.forEach(function (elemento, indice) {
            if (elemento.ctaBanco.trim() === tmpObjDescuentoCredito.ctaBanco.trim() && elemento.objTipoCtaBanco.idTipCtaBanco.trim() === tmpObjDescuentoCredito.objTipoCtaBanco.idTipCtaBanco.trim()
                    && elemento.objTipoMoneda.id.trim() === tmpObjDescuentoCredito.objTipoMoneda.id.trim()) {

                if (elemento.accion == 'D') {
                    elemento.accion = 'N';
                    existe = 2;
                } else {
                    existe = 1;
                }

            }
        });

        if (existe === 1) {
            toast('warning', 'La cuenta ya se encuentra registraba para el banco seleccionado', 'Alerta');
        } else if (existe === 0) {
            listCtasBanco.push(tmpObjDescuentoCredito);
        }

//    if (listCtasBanco.includes(tmpObjDescuentoCredito)) {
//        toast('warning', 'Ya existe una cuenta', 'warning')
//    } else {
//        listCtasBanco.push(tmpObjDescuentoCredito);
//    }

        listarCtaBanco(listCtasBanco);
        
        $("#modalCtasBanco").modal('hide');
    }

});

$("#btnNuevoBanco").click(function () {
    TabSelec("RegBancos");

});

$("#btnCancelar").click(function () {
    // TabSelec("Bancos");
    //limpiarcontroles();
    setTimeout("location.reload(true);", 50);

});

$('#form-buscarPJ').submit(function (event) {
    event.preventDefault();
    if ($('#form-buscarPJ').valid()) {
        BuscarPersonaJuridica();
    }
});

$("#btnRegistrarBanco").click(function () {

    if (idpersona != '') {
        bootbox.confirm("\u00BFEst\u00e1 seguro que desea Registrar un Banco\u003F", function (result) {
            if (result) {
                registrarBancos();
            }

        });
    } else
    {
        toast('error', 'Se Debe Seleccionar un Banco', 'Error!');
    }
});


function registrarBancos() {

    var tmplistaCtasBanco = listCtasBanco.filter(function (listbanco) {
        return listbanco.accion !== 'N';
    });

    var data = {'idPersona': idpersona, 'banco': $("#txtRazonSocial").val(),'abrev': $("#txtAbreviatura").val(), 'listaCtasBanco': JSON.stringify(tmplistaCtasBanco)};
    var success = function (res) {

        if (res.Result == 'OK') {
            toast('success', 'Se registr\u00F3 correctamente', 'Exito!');
            cargarCtasBanco(idpersona);
//            setTimeout("location.reload(true);", 500);
//            if (res.resultado === '1') {
//                toast('success', 'Se registr\u00F3 correctamente', 'Exito!');
//                cargarCtasBanco(idpersona);
//                setTimeout("location.reload(true);", 500);
//
//            } else {
//                toast('error', res.resultado, 'Error');
//            }

        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (data, status, er) {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sBanco?action=registrarBancoNEW", data, success, error);

}

function soloNumero(e) {
    var key = window.Event ? e.which : e.keyCode
    e.value = parseInt(e.value, 10);
    return ((key >= 48 && key <= 57));

}

function soloCtas(e) {
    var key = window.Event ? e.which : e.keyCode
    e.value = parseInt(e.value, 10);
    return ((key >= 48 && key <= 57) || key == 45);

}


function validacion() {

    if ($("#txtNroCtaBanco").val() == '') {
        toast('warning', "DEBE INGRESAR LA CUENTA DEL BANCO", 'warning');
        return 0;
    }
    return 1;
}


function limpiarcontroles() {

    idpersona = '';

    $("#txtNroCtaBanco").val('');
    $("#txtAbreviatura").val('');
    $("#txtRazonSocial").val('');
    $("#txtRUC").val('');
    $("#tblCtaBancos").dataTable().fnClearTable();


}