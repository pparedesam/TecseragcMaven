$(".simple-select").select2({
    minimumResultsForSearch: Infinity
});

$(".simple-select2 ").select2();

$("#cboProveedor").change(function () {
    var x;
    x = $("#cboProveedor").val();
    if (x === '1') {

        $("#divProveedor").show();
    } else {


        $("#divProveedor").hide();
    }
});


$('#btnRegistrarPersona').click(function (e) {
    e.preventDefault();
    var validator = $("#form-registrar").valid();

    if (validator) {
        if ($("#cboProveedor").val() == '1') {
            if (validacionRUS()) {
                registraractualizarPersonaNatural();
            }
        } else {
            registraractualizarPersonaNatural();
        }
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');


});


function registraractualizarPersonaNatural() {

    var data = {
        'txtIdPersona': $("#txtIdPersona").val(), 'cboTipDocumento': $("#cboTipDocumento").val(), 'txtNumDocumento': $("#txtNumDocumento").val(),
        'txtApePaterno': $("#txtApePaterno").val(), 'txtApeMaterno': $("#txtApeMaterno").val(), 'txtNombres': $("#txtNombres").val(), 'txtFechaNac': $("#txtFechaNac").val(),
        'cboSexPersona': $("#cboSexPersona").val(), 'cboEstPersona': $("#cboEstPersona").val(), 'cboGradoInstruccion': $("#cboGradoInstruccion").val(),
        'cboProfesion': $("#cboProfesion").val(), 'txtTelefono': $("#txtTelefono").val(), 'txtCelular': $("#txtCelular").val(), 'txtEmail': $("#txtEmail").val(), 'idpaises': $("#idpaises").val(),
        'iddepartamento': $("#iddepartamento").val(), 'idprovincias': $("#idprovincias").val(), 'iddistritos': $("#iddistritos").val(), 'cboUrbanizacion': $("#cboUrbanizacion").val(),
        'txtDireccion': $("#txtDireccion").val(), 'txtReferencia': $("#txtReferencia").val(), 'proveedor': $("#cboProveedor").val(), 'idRubro': $("#cboRubro").val(), 'rus': $("#txtRUS").val(),
    };

    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.Message == 'ins_ok') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                btnlimpiar();
            } else if (res.Message == 'upd_ok') {
                toast('success', "ACTUALIZACI&Oacute;N EXITOSA", 'Exito!');
                btnlimpiar();
            }
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };


    bootbox.confirm("\u00BFEst\u00e1 seguro que desea registrar a esta persona\u003F", function (result) {
        if (result) {
            fn_callmethod("../sPersonaNatural?action=registrar", data, success, error);
        }
    });
}
;

var oTable;
$(function () {
    oTable = fn_iniciarDT(oTable, "tbl_PersonaNatural");

    oTable.on("click", ".edit", function () {
        fn_Edit(this);
    });


});

function btnlimpiar() {


    $("#cboTipDocumento").val('default').trigger('change.select2');
    $("#cboSexPersona").val('default').trigger('change.select2');
    $("#cboEstPersona").val('default').trigger('change.select2');
    $("#cboTipVivienda").val('default').trigger('change.select2');
    $("#cboDiscapacidad").val('default').trigger('change.select2');
    $("#cboGradoInstruccion").val('default').trigger('change.select2');
    $("#cboProfesion").val('default').trigger('change.select2');

    $("#cboUrbanizacion").val('default').trigger('change.select2');


    $("#txtIdPersona").val('xxxxxxxx');
    $("#txtNumDocumento").prop("disabled", false); //habilitar caja de texto
    $("#txtNumDocumento").val('');
    $("#txtApePaterno").val('');
    $("#txtApeMaterno").val('');
    $("#txtNombres").val('');
    $("#txtFechaNac").val('');

    $("#txtTelefono").val('');
    $("#txtCelular").val('');
    $("#txtEmail").val('');
    $("#txtDireccion").val('');
    $("#txtReferencia").val('');

    $("#idpaises").val('').trigger('change.select2');
    $("#iddepartamento").val('').trigger('change.select2');
    $("#idprovincias").val('').trigger('change.select2');
    $("#iddistritos").val('').trigger('change.select2');
    // $('.selectpicker').selectpicker('refresh');

    $("#txtRUS").val('');
    $("#cboProveedor").val('0').trigger('change.select2');
    $("#cboRubro").val('2101').trigger('change.select2');
    $("#divProveedor").hide();

}
;

/*Aqui se ejecuta el metdod queme retorna todos los datos de la persona natural, asi como el seteo de sus valores*/
function fn_Edit(elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var id_codigo = tdbuttons.attr("data-id");
    var success = function (res) {

        if (res.Result == 'OK') {

            var str = res.PersonaNatural.objUbigeoNacionalidad.codigo;

            var pais = DividirUbigeo(str, 2);
            var departamento = DividirUbigeo(str, 4);
            var Provincia = DividirUbigeo(str, 6);
            var distrito = DividirUbigeo(str, 8);

            $("#txtIdPersona").val($.trim(res.PersonaNatural.idPersona));
            $("#cboTipDocumento").val(res.PersonaNatural.objTipoDocIdentidad.idTipoDocIdentidad);

            $("#txtNumDocumento").prop('disabled', true);
            $("#txtNumDocumento").val($.trim(res.PersonaNatural.nroDocumento));
            $("#txtApePaterno").val(res.PersonaNatural.apPaterno);
            $("#txtApeMaterno").val(res.PersonaNatural.apMaterno);
            $("#txtNombres").val(res.PersonaNatural.nombres);

            var fecha = res.PersonaNatural.fechaNacimiento;

            $("#txtFechaNac").datepicker("update", fecha);

            $("#cboSexPersona").val(res.PersonaNatural.sexo);
            $("#cboEstPersona").val(res.PersonaNatural.estadoCivil);
            $("#cboGradoInstruccion").val(res.PersonaNatural.gradoInstruccion);
            $("#cboProfesion").val(res.PersonaNatural.objProfesion.idProfesion);

            $("#txtTelefono").val(res.PersonaNatural.telefono);
            $("#txtCelular").val(res.PersonaNatural.celular);
            $("#txtEmail").val(res.PersonaNatural.correo);

            $("#cboProveedor").val(res.PersonaNatural.proveedor);
            $("#cboRubro").val(res.PersonaNatural.idRubro);
            $("#txtRUS").val(res.PersonaNatural.rus);

            if (res.PersonaNatural.proveedor === '1') {

                $("#divProveedor").show();
            } else {

                $("#divProveedor").hide();
            }

            $("#idpaises").val(pais).prop('selected', true);

            $("#codDep").val(departamento);
            $("#codProv").val(Provincia);
            $("#codDist").val(distrito);

            $("#iddepartamento").val(departamento).prop('selected', true);

            $("#idprovincias").val(Provincia).prop('selected', true);
            $("#iddistritos").val(distrito).prop('selected', true);
            $("#cboUrbanizacion").val(res.PersonaNatural.objUrbanizacion.codigo);
            $("#txtDireccion").val(res.PersonaNatural.direccion);
            $("#txtReferencia").val(res.PersonaNatural.referenciaDireccion);

            $("#cboTipDocumento").trigger('change.select2');
            $("#cboSexPersona").trigger('change.select2');
            $("#cboEstPersona").trigger('change.select2');

            $("#cboGradoInstruccion").trigger('change.select2');
            $("#cboProfesion").trigger('change.select2');
            $("#cboOcupacion").trigger('change.select2');
            $("#cboUrbanizacion").trigger('change.select2');

            $("#idpaises").trigger('change.select2');
            $("#iddepartamento").trigger('change.select2');
            $("#idprovincias").trigger('change.select2');
            $("#iddistritos").trigger('change.select2');

            $("#cboProveedor").trigger('change.select2');
            $("#cboRubro").trigger('change.select2');

            TabSelec("tabRegistrarPersona");

        } else if (res.Result == 'error') {
            toast('error', res.Message, 'ERROR!');
        }
    };
    var error = function () {
        toast('error', 'Error , Consulte con el Area de TI', 'ERROR!');
    };

    fn_callmethod("../sPersonaNatural?action=ObtenerPersonaNatural", {idPersona: id_codigo}, success, error);

}
;

//var tabName = $("#txtVariable").val() != "tabRegistrarPersona" ? $("#txtVariable").val() : "tab_BuscarPersona";
var varible = $("#txtVariable").val();
if (varible !== undefined)
    TabSelec(varible);



/*Esta funcion sirve para mantener seleccionado un tab*/
function TabSelec(idtab) {
    $('#tabPersonaNatural a[href="#' + idtab + '"]').tab('show');
    $("#tabPersonaNatural a").click(function () {
        $("[id*=" + idtab + "]").val($(this).attr("href").replace("#", ""));
    });
    $("#txtVariable").val(idtab);
}
;

$("#txtIngMensual").keyup(function (e) {

    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
    e.preventDefault();
    if (key == 13) {
        var Monto = convertirDecimales(document.getElementById('txtIngMensual').value);
        this.value = Monto;
    }
}
);



/*Funciones para cargar datos RENIEC *ANDREE ZUÑIGA**/

$("#cboTipDocumento").change(function () {
    if ($("#cboTipDocumento").val() == '0301') {
        var dni = $("#txtNumDocumento").val();
        if (dni.length == 8) {
            consultarPersonaReniec(dni)
        }
    } else {
        $("#txtApePaterno").prop("disabled", false);
        $("#txtApeMaterno").prop("disabled", false);
        $("#txtNombres").prop("disabled", false);
        $("#txtApePaterno").val('');
        $("#txtApeMaterno").val('');
        $("#txtNombres").val('');
    }
});




$("#txtNumDocumento").keyup(function (e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    var dni = $("#txtNumDocumento").val();
    if (dni.length == 8 && $("#cboTipDocumento").val() == '1') {
        consultarPersonaReniec(dni)
    } else {
        $("#txtApePaterno").val('');
        $("#txtApeMaterno").val('');
        $("#txtNombres").val('');
    }

    //alert(code);
});

function validacionRUS() {

    if ($("#txtRUS").val().length < 11) {
        toast('warning', "EL RUS DEBE TENER 11 DIGITOS", 'warning');
        return 0;
    }
    return 1;
}

function consultarPersonaReniec(numDNI) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var JSONObject = JSON.parse(xmlhttp.responseText);
            if (JSONObject.apellidoPaterno == '') {
                $("#txtApePaterno").prop("disabled", false);
                $("#txtApeMaterno").prop("disabled", false);
                $("#txtNombres").prop("disabled", false);
                $("#txtApePaterno").val('');
                $("#txtApeMaterno").val('');
                $("#txtNombres").val('');
            } else {
                $("#txtApePaterno").prop("disabled", true);
                $("#txtApeMaterno").prop("disabled", true);
                $("#txtNombres").prop("disabled", true);
                $("#txtApePaterno").val(htmlEntities(JSONObject.apellidoPaterno));
                $("#txtApeMaterno").val(htmlEntities(JSONObject.apellidoMaterno));
                $("#txtNombres").val(JSONObject.nombres);
            }

        }
    }

    xmlhttp.open("GET", "https://dniruc.apisperu.com/api/v1/dni/" + numDNI + "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InBhdWwucGFyZWRlc2FtQGdtYWlsLmNvbSJ9.M-pxyvmH7MyD581GQpKPJsurx1eDlrxZ8KxWlDeSwfM&fbclid=IwAR254HBOuD9CpnaNU4at0nIf91gWiS4WiEG5VNUaiUx5gDKIATES08KjLVk", true);
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