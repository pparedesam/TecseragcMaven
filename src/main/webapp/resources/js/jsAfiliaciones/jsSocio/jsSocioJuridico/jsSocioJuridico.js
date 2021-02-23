/******* BUSQUEDA DE UN UNICO SOCIO JURIDICO POR CUENTA O RUC******************/
/**
 * 
 * @param {event} event eventoclick
 */
$('#frmBuscarSocioJuridico').submit(function (event) {
    event.preventDefault();
    if ($('#frmBuscarSocioJuridico').valid()) {
        mostrarSocioJuridico();

    }
});


/******* BUSQUEDA DE UN UNICO SOCIO JURIDICO POR CUENTA O RUC******************/

/********************Se usa en Registro de Socio Juridico***********************/
/**
 * 
 * @param res res Datos Persona juridica
 */
function mostrarPersonaJuridica(res) {

    $('#txtNroRuc').val(res.Persona.nroDocumento);
    $('#txtrazonSocial').val(res.Persona.nombres);
    $('#txtTipoPersonaJ').val('PERSONA JURIDICA');
    $('#txtidpersona').val(res.Persona.idPersona);
    $("#datosSocioJuridico").show();
    $("#cont-Aport").show();
    $("#divFondoM").hide();
    document.getElementById('txtNroRuc').setAttribute("data-idPersona", res.Persona.idPersona);

}
;
$('#frmRegistrarsocio').submit(function (event) {

    event.preventDefault();
    var validator = $('#frmRegistrarsocio').valid();
    var idpersona = $('#txtidpersona').val();
    var data = {'cboTipoTrabajador': $('#cboTipoTrabajador').val(),
        'txtidpersona': $("#txtidpersona").val()};
    if (idpersona.length != 0) {
        if (validator) {
            var success = function (res) {
                if (res.Result == 'OK') {
                    toast('success', 'Se registr\u00F3 correctamente', 'Exito!');
                     $('.nav-tabs a[href="#TabRegRepLeg"]').tab('show');   
                     document.getElementById("btnGenerarReporte").disabled =false;   
                    //abrirVentana(ReportSvr + "?%2fPyAfiliaciones%2fRptFichaSocioJuridico&rs:Command=Render&idpersona=" + res.idPersona);
                    //LimpiarData();
                } else if (res.Result === 'error') {
                    toast('error', res.Message, 'Error!');
                }
            };
            var error = function (data, status, er) {
                toast('error', data + '-' + status + '-' + er, 'Error2!');
            };
            fn_callmethod("../sSocioJuridico?action=RegSocioJuridico", data, success, error);
        }
    } else {
        toast('error', 'Debe Seleccionar una Persona', 'ERROR');
    }
    ;
});

$("#btnGenerarReporte").click(function () {    
    var idpersona = document.getElementById('txtNroRuc').getAttribute("data-idPersona");
    abrirVentana(ReportSvr+"?%2fPyAfiliaciones%2fRptFichaSocioJuridico&rs:Command=Render&idpersona=" + idpersona);
});

$("#btnLimpiar").click(function () {
    LimpiarData();
});

function LimpiarData() {
    $('#txtnombres').val('');
    $('#txtapellidos').val('');
    $('#txttipopersona').val('');
    $('#txtedad').val('');
    $('#txtidpersona').val('');
    $('#txtNroRuc').val('');
    $('#txtrazonSocial').val('');
    $('#txtTipoPersonaJ').val('');
    $("#cboTipoTrabajador").val('0701').trigger('change.select2');
}
;
/********************Se usa en Registro de Socio Juridico***********************/