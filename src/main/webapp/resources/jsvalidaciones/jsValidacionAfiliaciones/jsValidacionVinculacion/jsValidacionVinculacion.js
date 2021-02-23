$("#frmBuscarUsuario").validate({
    rules: {
        txtBuscarUsuario: {
            required: true, minlength: 3
        }
    },
    messages: {
        txtBuscarUsuario: {
            required: "Debe Ingresar el usuario", minlength: "El criterio debe contener almenos 3 caracteres"
        }
    }
});


$('#frmBuscarPersonal').validate({
    rules: {
        txtSocioNaturalJuridico: {
            required: true,
            validaTipoNroDoc: true
        }
    },
    messages: {
        txtSocioNaturalJuridico: {
            required: "Debe ingresar un criterio",
            validaTipoNroDoc: "Ingrese los datos correctamente segun los items seleccionados"
        }
    }
});

$('#cboTipoBusqueda').on('change', function () {
    $(this).valid();
});
$.validator.addMethod("validaTipoNroDoc", function (value, element) {

    var ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;
    var ex_regular_ruc = /^\d{11}(?:[-\s]\d{4})?$/;
    var tipobusqueda = $('#cboTipoBusqueda').val();
    var result;
    var nro = value.length;
    if (nro == 8 && (tipobusqueda == 1) && (ex_regular_dni.test(value))) {
        result = true;
    } else if (nro == 11 && (tipobusqueda == 1) && (ex_regular_ruc.test(value))) {
        result = true;
    } else if ((tipobusqueda == 2)) {
        result = true;
    } else
        result = false;

    return result;

}, "Este Numero de documento no es valido");
