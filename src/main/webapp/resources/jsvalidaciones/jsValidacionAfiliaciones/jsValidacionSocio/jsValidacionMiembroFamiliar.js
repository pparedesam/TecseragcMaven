

$(function () {

    $('#frmBuscarSocioNatural').validate({
        rules: {
            txtCuenta: {
                required: true,
                validaCuenta: true
            }
        },
        messages: {
            txtCuenta: {
                required: "Debe ingresar un nro de cuenta",
                validaCuenta: "El Numero de Cuenta 7 Digitos"
            }
        }

    });


    $.validator.addMethod("validaCuenta", function (value) {

        if (!isNaN(value)) {
            $('#txtCuenta').val(String("0000000" + value).slice(-7));
            result = true;
        } else
            result = false;

        return result;

    }, "Este Numero de cuenta no es valido");


});

$('#frmtblMiembros').validate({
    //ignore: "input[type='text']:hidden",
    ignore: "",
    highlight: function (element) {
        $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function (element) {
        $(element).closest('.form-group').removeClass('has-error');
    },
    errorElement: 'span',
    errorClass: 'help-block',
    errorPlacement: function (error, element) {
        if (element.parent('.input-group').length) {
            error.insertAfter(element.parent());
        } else {
            error.insertAfter(element);
        }
    },
    rules: {
        txtIdPersonaMF: {
            required: true,
            number: true,
            validaIdPersona: true
        },
        cboParentesco0: {
            valueNotEquals: "default"
        }
    },
    messages: {
        txtIdPersonaMF: {
            required: "Debe Seleccionar una Persona",
            number: "Solo pueden ser numeros"

        },
        cboParentesco0: {
            valueNotEquals: "Seleccione un Parentesco"
        }
    }

});

$('#frmBeneficiaro').validate({
    ignore: ":hidden:not(.chosen-select)",
    rules: {
        cboTipoBeneficio: {
            valueNotEquals: "default"
        },
        cboMFamiliarModal: {
            valueNotEquals: "default"
        },
        txtPorcentaje: {
            required: true,
            number: true,
            min: 1,
            max: 100
        }
    },
    messages: {
        cboTipoBeneficio: {
            valueNotEquals: "Debe seleccionar un Tipo de Beneficiario."
        },
        cboMFamiliarModal: {
            valueNotEquals: "Debe seleccionar un un Miembro Familiar."
        },
        txtPorcentaje: {
            required: "Campo Requerido",
            number: "Solo se aceptan numeros",
            min: "El numero a asignar debe ser mayor a cero",
            max: "El munero no puede sobrepasar el 100%"
        }
    }

});

$("#cboTipoBeneficio").on('change', function () {
    $(this).valid();
});

$("#cboMFamiliarModal").on('change', function () {
    $(this).valid();
});

$.validator.addMethod("validaNroDoc", function (value, element) {

    var ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;

    if ((ex_regular_dni.test(value) == true)) {
        return true;

    } else {
        return false;
    }


}, "Este Numero de documento no es valido");

$.validator.addMethod("validaMiembros", function (value, element) {

    if (tblMiembroF.size() != 0) {
        return true;
    } else
        return false;


}, "No se han agregado Miembros Familiares");

$.validator.addMethod("validaIdPersona", function (value, element) {

    if (value.length == 7) {
        return true;
    } else
        return false;


}, "No se ha seleccionado una persona");