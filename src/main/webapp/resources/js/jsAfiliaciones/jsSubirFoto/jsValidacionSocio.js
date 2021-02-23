limpiarDatosSocio();

$(function () {

    $('#frmBuscarSocioNaturalFoto').validate({
        rules: {
            txtCuenta: {
                required: true,
                validaTipoNroDoc: true
            }
        },
        messages: {
            txtCuenta: {
                required: "Debe ingresar un numero de cuenta",
                validaTipoNroDoc: "Ingrese los datos correctamente segun los items seleccionados"
            }
        }
    });

    $.validator.addMethod("validaTipoNroDoc", function (value) {

        if (!isNaN(value)) {
            $('#txtCuenta').val(String("0000000" + value).slice(-7));
            result = true;
        } else
            result = false;

        return result;

    }, "Este Numero de cuenta no es valido");


});

function mostrarDatosSocioNaturalFoto() {

    var data = {'txtNroDocIden': $('#txtCuenta').val()};

    var success = function (res) {
        if (res.Result == 'OK') {
            if (res.Socio.objEstadoSocio.codigo.substr(0, 1) == '1') {
                document.getElementById("txtNumDocumento").value = res.Socio.objPersona.nroDocumento;
                document.getElementById("txtNroCuenta").value = res.Socio.idSocio;
                document.getElementById("txtSocio").value = res.Socio.objPersona.nombres;
                document.getElementById('txtNumDoc').setAttribute("data-idPersona", res.Socio.objPersona.idPersona);
                mostrarImagenPersona(res.Socio.objPersona.idPersona);
                $("#divimagenes").show();
                $("#divimgbutton").show();


            } else {
                toast('error', "Estado del Socio : " + res.Socio.objEstadoSocio.descripcion, 'Error');
                limpiarDatosSocio();
            }
        } else {
            toast('error', res.Message, 'Error');
            limpiarDatosSocio();
        }
    };
    var error = function (data, status, er) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sSocio?action=ObtenerDatosBasicosSocioNaturaloJuridicoxCuentaSocio", data, success, error);
}
;

function limpiarDatosSocio() {
    document.getElementById("txtNumDocumento").value = "";
    document.getElementById("txtNroCuenta").value = "";
    document.getElementById("txtSocio").value = "";
    document.getElementById('txtNumDoc').setAttribute("data-idPersona", "");

}
