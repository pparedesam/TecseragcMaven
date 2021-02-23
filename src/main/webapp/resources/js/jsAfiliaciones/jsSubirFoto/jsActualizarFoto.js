$('#frmActulizarFotoSocio').submit(function (event) {

    event.preventDefault();
    var validator = $('#frmActulizarFotoSocio').valid();
    var idpersona = document.getElementById('txtNumDoc').getAttribute("data-idPersona");

    if ((idpersona.length) != 0) {
        if (validator) {
            ActualizarFoto()
        }
    } else {
        toast('error', 'Debe Seleccionar una Persona', 'ERROR');
    }

});

function ActualizarFoto() {

    var idpersona = document.getElementById('txtNumDoc').getAttribute("data-idPersona");
    var data = {'txtidpersona': idpersona, 'croppedImageDataURLFoto': croppedImageDataURLFoto, 'croppedImageDataURLFirma': croppedImageDataURLFirma};
    var success = function (res) {

        if (res.Result == 'OK') {
            toast('success', 'Se registr\u00F3 correctamente', 'Exito!');
            setTimeout("location.reload(true);", 5000);
            //document.location.reload();


        } else if (res.Result === "error") {
            toast('error', 'No se cargar\u00F3n las im\u00E1genes correctamente', 'Error');
        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (res) {
        toast('error', res.Message, 'Error');
    };

    fn_callmethod("../sSocio?action=actualizarFotoSocio", data, success, error);
}
;