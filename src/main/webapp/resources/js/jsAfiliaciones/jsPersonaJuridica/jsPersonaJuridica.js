$(".simple-select").select2({
    minimumResultsForSearch: Infinity
});

$(".simple-select2 ").select2();


$("#cboProveedor").change(function () {
    var x;
    x = $("#cboProveedor").val();
    if (x === '1') {
        $("#divRubroProv").show();
    } else {

        $("#divRubroProv").hide();
    }
});

$('#btnRegistrarPersona').click(function (e) {

    e.preventDefault();
    var validator = $("#form-registrarPerjur").valid();

    var txt = $("btnRegistrarPersona").val();
    var txt1 = $("btnLimpiarPerJur").val();


    if (validator)
    {
        registraractualizarPersonaJuridica();
    } else
        toast('warning', "Usted debe ingresar correctamente los datos requeridos", 'warning');


});

function registraractualizarPersonaJuridica() {

    // event.preventDefault();

    var data = {
        'txtIdPersona': $("#txtIdPersona").val(), 'cboTipDocumento': $("#cboTipDocumento").val(), 'txtRazonSocial': $("#txtRazonSocial").val(), 'txtNroRuc': $("#txtNroRuc").val(),
        'cboGiroNegocio': $("#cboGiroNegocio").val(), 'txtFechaCons': $("#txtFechaCons").val(), //'txtIngreso': $("#txtIngreso").val(),
        'cboUrbanizacion': $("#cboUrbanizacion").val(), 'txtDireccion': $("#txtDireccion").val(), 'txtTelefono': $("#txtTelefono").val(), 'txtCelular': $("#txtCelular").val(),
        'txtEmail': $("#txtEmail").val(), 'idpaises': $("#idpaises").val(), 'iddepartamento': $("#iddepartamento").val(), 'idprovincias': $("#idprovincias").val(),
        'iddistritos': $("#iddistritos").val(), 'txtReferencia': $("#txtReferencia").val(), 'proveedor': $("#cboProveedor").val(), 'rubro': $("#cboRubro").val()};

    var success = function (res) {

        if (res.Result === 'OK')
        {
            toast('success', res.Message, 'Exito!');
        } else if (res.Result === 'error')
        {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };


    bootbox.confirm("\u00BFEst\u00e1 seguro que desea registrar a esta persona Juridica?\u003F", function (result) {
        if (result) {
            fn_callmethod("../sPersonaJuridica?action=registrar", data, success, error);
        }
    });
}
;

function btnlimpiar()
{

    $("#cboTipDocumento").val('default').trigger('change.select2');
    //  $("#cboGiroNegocio").val('').prop('selectedIndex','default'); 
    $("#txtIdPersona").val('');
    $("#txtNroRuc").prop("disabled", false); //habilitar caja de texto
    $("#txtNroRuc").val('');
    $("#txtRazonSocial").val('');
    $("#txtFechaCons").val('');
    //$("#txtIngreso").val('');
    $("#txtTelefono").val('');
    $("#txtCelular").val('');
    $("#txtEmail").val('');
    $("#txtDireccion").val('');
    $("#txtReferencia").val('');

    //$("#cboLocal").val('default').trigger('change.select2');
    $("#cboGiroNegocio").val('default').trigger('change.select2');
    $("#cboUrbanizacion").val('default').trigger('change.select2');
    $("#idpaises").val('default').trigger('change.select2');
    $("#iddepartamento").val('default').trigger('change.select2');
    $("#idprovincias").val('default').trigger('change.select2');
    $("#iddistritos").val('default').trigger('change.select2');

    $("#idpaises").val('').trigger('change.select2');
    $("#iddepartamento").val('').trigger('change.select2');
    $("#idprovincias").val('').trigger('change.select2');
    $("#iddistritos").val('').trigger('change.select2');

    $("#cboProveedor").val('0').trigger('change.select2');
    $("#cboRubro").val('2101').trigger('change.select2');
    $("#divRubroProv").hide();
}
;

/*Aqui se ejecuta el metdod queme retorna todos los datos de la persona juridica, asi como el seteo de sus valores*/
function fn_Edit(elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var id_codigo = tdbuttons.attr("data-id");

    var data = {'idPersona': id_codigo};

    var success = function (res) {

        if (res.Result == 'OK') {

            var str = res.PersonaJuridica.objUbigeo.Ubigeo;

            var pais = DividirUbigeo(str, 2);
            var departamento = DividirUbigeo(str, 4);
            var Provincia = DividirUbigeo(str, 6);
            var distrito = DividirUbigeo(str, 8);
            var fecha = res.PersonaJuridica.fechaNacimiento;


            $("#txtIdPersona").val($.trim(res.PersonaJuridica.idPersona));
            $("#txtNroRuc").prop('disabled', true);
            $("#cboTipDocumento").prop('disabled', true);
            $("#cboTipDocumento").val(res.PersonaJuridica.objTipoDocIdentidad.idTipoDocIdentidad);
            $("#txtNroRuc").val($.trim(res.PersonaJuridica.nroDocumento));
            $("#txtRazonSocial").val(res.PersonaJuridica.nombres);
            $("#cboGiroNegocio").val(res.PersonaJuridica.objActividadEconomica.IdActEcon);
            // $("#cboLocal").val(res.PersonaJuridica.tipoVivienda);
            $("#txtFechaCons").datepicker("update", fecha);
            //$("#txtIngreso").val(res.PersonaJuridica.ingresoMensual);
            $("#txtTelefono").val(res.PersonaJuridica.telefono);
            $("#txtCelular").val(res.PersonaJuridica.celular);
            $("#txtEmail").val(res.PersonaJuridica.correo);
            $("#cboUrbanizacion").val(res.PersonaJuridica.objUrbanizacion.codigo);
            $("#txtDireccion").val(res.PersonaJuridica.direccion);
            $("#txtDireccionNeg").val(res.PersonaJuridica.direccionNegocio);
            $("#txtReferencia").val(res.PersonaJuridica.referenciaDireccion);
            $("#idpaises").val(pais).prop('selected', true);
            $("#codDep").val(departamento);
            $("#codProv").val(Provincia);
            $("#codDist").val(distrito);

            $("#cboProveedor").val(res.PersonaJuridica.proveedor);
            $("#cboRubro").val(res.PersonaJuridica.idRubro);

            if (res.PersonaJuridica.proveedor === '1') {
                $("#divRubroProv").show();
            } else {

                $("#divRubroProv").hide();
            }

            $("#iddepartamento").val(departamento).prop('selected', true);
            $("#idprovincias").val(Provincia).prop('selected', true);
            $("#iddistritos").val(distrito).prop('selected', true);
            $("#cboTipDocumento").trigger('change.select2');
            $("#cboGiroNegocio").trigger('change.select2');
            $("#cboUrbanizacion").trigger('change.select2');
            //$("#cboLocal").trigger('change.select2');
            $("#idpaises").trigger('change.select2');
            $("#iddepartamento").trigger('change.select2');
            $("#idprovincias").trigger('change.select2');
            $("#iddistritos").trigger('change.select2');

            $("#cboProveedor").trigger('change.select2');
            $("#cboRubro").trigger('change.select2');

            TabSelec("tab-RegistrarPerJur");

        } else if (res.Result == 'error') {
            toast('error', $.trim(res.Message), 'ERROR!');
        }
    };
    var error = function () {
        toast('error', 'Error , Consulte con el Area de TI', 'ERROR!');
    };

    fn_callmethod("../sPersonaJuridica?action=ObtenerPersonaJuridica", data, success, error);

}
;
//
//$("#txtIngreso").keyup(function (e) {
//    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
//    e.preventDefault();
//    if (key == 13) {
//        var Monto = convertirDecimales(document.getElementById('txtIngreso').value);
//        this.value = Monto;
//    }
//}
//);




/*Funciones para cargar datos RENIEC *ANDREE ZUÑIGA**/

$("#txtNroRuc").keyup(function (e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    var ruc = $("#txtNroRuc").val();
    if (ruc.length == 11 && $("#cboTipDocumento").val() == '6') {
        consultarSunatRUC(ruc)
    } else {
        $("#txtRazonSocial").prop("disabled", false);
        $("#txtDireccion").prop("disabled", false);
        $("#txtRazonSocial").val('');
        $("#txtDireccion").val('');
    }

    //alert(code);
});


function consultarSunatRUC(numRUC) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var JSONObject = JSON.parse(xmlhttp.responseText);
            if (JSONObject.razonSocial == '') {
                $("#txtRazonSocial").prop("disabled", false);
                $("#txtDireccion").prop("disabled", false);

                $("#txtRazonSocial").val('');
                $("#txtDireccion").val('');

            } else {
                $("#txtRazonSocial").prop("disabled", true);
                $("#txtDireccion").prop("disabled", true);
                $("#txtDireccion").val(htmlEntities(JSONObject.direccion));
                $("#txtRazonSocial").val(htmlEntities(JSONObject.razonSocial));

            }

        }
    }

    xmlhttp.open("GET", "https://dniruc.apisperu.com/api/v1/ruc/" + numRUC + "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InBhdWwucGFyZWRlc2FtQGdtYWlsLmNvbSJ9.M-pxyvmH7MyD581GQpKPJsurx1eDlrxZ8KxWlDeSwfM&fbclid=IwAR1VUe8X-2tCsgmQa54QgfzKqq_lh_i3F5cKUErQSJLm96pEzW_60ghrcmg", true);
    xmlhttp.send();
}


function htmlEntities(str) {
    return String(str).replace('&ntilde;', '\u00F1')
            .replace('&Ntilde;', '\u00D1')
            .replace('&Aacute;', '\u00C1')
            .replace('&aacute;', '\u00E1')
            .replace('&Eacute;', '\u00C9')
            .replace('&eacute;', '\u00E9')
            .replace('&Iacute;', '\u00CD')
            .replace('&iacute;', '\u00ED')
            .replace('&Oacute;', '\u00D3')
            .replace('&oacute;', '\u00F3')
            .replace('&Uacute;', '\u00DA')
            .replace('&uacute;', '\u00FA')
            .replace('&Uuml;', '\u00DC')
            .replace('&uuml;', '\u00FC')


}