/*****************************Se usa en Miembro Familiar***********************/
/* Busca Socio Natural por cuenta */
$('#frmBuscarSocioNatural').submit(function (event) {
    event.preventDefault();
    if ($('#frmBuscarSocioNatural').valid()) {
        mostrarDatosSocioNatural();
    }
});

/* Busca Socio Natural por cuenta */

/*****************************Se usa en Miembro Familiar***********************/

/********************Se usa en Registro de Socio Natural***********************/

/**
 *  @param res datos persona natural
 */
function mostrarPersonaNatural(res) {
    $('#txtnombres').val(res.Persona.nombres);
    $('#txtapellidos').val(res.Persona.apPaterno + " " + res.Persona.apMaterno);
    $('#txttipopersona').val('PERSONA NATURAL');
    $('#txtedad').val(res.Persona.edad);
    document.getElementById('txtNumDocumento').setAttribute("data-idPersona",res.Persona.idPersona);

    if (res.Persona.edad > 65){

        $("#divFondoM").hide();
    } else{
        $("#divFondoM").show();
    }

}

function registrarSocioNatural() { 
    
    var idpersona = document.getElementById('txtNumDocumento').getAttribute("data-idPersona");
    var data = {'cboTipoTrabajador': $('#cboTipoTrabajador').val(),'txtidpersona': idpersona,'croppedImageDataURLFoto': croppedImageDataURLFoto,'croppedImageDataURLFirma': croppedImageDataURLFirma}; 
    var success = function (res) {  
          
        if (res.Result == 'OK'){
            toast('success', 'Se registr\u00F3 correctamente', 'Exito!');
            tableMiembroFamiliar();
            $('.nav-tabs a[href="#TabRegMieFam"]').tab('show');   
            document.getElementById("btnGenerarReporte").disabled =false;    
            //LimpiarData();
            //setTimeout("location.reload(true);", 5000);            
            //abrirVentana(ReportSvr+"?%2fPyAfiliaciones%2fRptFichaSocio&rs:Command=Render&idpersona=" + res.IdPersona);

        } else if (res.Result === "error") {
            toast('error', 'No se cargar\u00F3n las im\u00E1genes correctamente', 'Error');
        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (res) {
        toast('error', res.Message, 'Error');
    };

    fn_callmethod("../sSocio?action=registrarSocioNatural", data, success, error);
};
/********************Se usa en Registro de Socio Natural***********************/
$('#frmRegistrarsocio').submit(function (event) {

    event.preventDefault();
    var validator = $('#frmRegistrarsocio').valid();   
    var idpersona = document.getElementById('txtNumDocumento').getAttribute("data-idPersona");

    if ((idpersona.length) != 0) {
        if (validator) {
          registrarSocioNatural();
        } else {
            toast('error', 'Debe Seleccionar una Tipo Trabajador', 'ERROR');
        }
    } else {
        toast('error', 'Debe Seleccionar una Persona', 'ERROR');
    }

});

function LimpiarData() {
    $('#txtnombres').val('');
    $('#txtapellidos').val('');
    $('#txttipopersona').val('');
    $('#txtedad').val('');
    $('#txtidpersona').val('');
    document.getElementById('txtNumDocumento').setAttribute("data-idPersona",'');
    var tblMiembros = $('#tblMiembros').DataTable();
    var tblBeneficiarios = $('#tblBeneficiarios').DataTable();
    tblMiembros.clear().draw();
    tblBeneficiarios.clear().draw();
    document.getElementById("btnAgregarBeneficiario").disabled =true;       
    document.getElementById("btnGenerarReporte").disabled =true;    
    tblMiembroF='';
};

$("#btnGenerarReporte").click(function () {    
    var idpersona = document.getElementById('txtNumDocumento').getAttribute("data-idPersona");
    abrirVentana(ReportSvr+"?%2fPyAfiliaciones%2fRptFichaSocio&rs:Command=Render&idpersona=" + idpersona);
});

$("#btnLimpiar").click(function () {        
   LimpiarData();
});