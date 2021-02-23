$.validator.setDefaults({
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
        } else if (element.is('select')) {
            error.insertAfter(element.siblings(".select2"));
        } else {
            error.insertAfter(element);
        }
    },
    ignore: ":hidden:not(.select2)"

});

$('#frmBuscarSocioJuridico').validate({

    rules: {
        txtCuentaRuc: {
            required : true,
            validaCuentaRuc: true
        }
    },
    messages: {
        txtCuentaRuc: {
            required : "Debe ingresar un nro de documento"
        }
    }

});

$('#frmtblRepresentantes').validate({
    ignore: "input[type='text']:hidden",
    rules: {
        txtIdPersonaRL: {
            required : true,
            valueIdPersona: true
        },
        cboCargosRepresentateLegal0:{
            validaComboCero :true
        }

    },
    messages: {
        txtIdPersonaRL: {
            required : "seleccionar una Persona"
        }
    }

});
//