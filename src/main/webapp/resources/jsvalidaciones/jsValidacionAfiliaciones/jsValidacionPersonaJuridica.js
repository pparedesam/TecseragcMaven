$(function () {
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



    $("#form-buscar-PerJur").validate({
        rules: {
            txtBuscarPersona: {
                required: true
            }
        },
        messages: {
            txtBuscarPersona: {
                required: "Debe Ingresar un Parametro de Busqueda"
            }
        }
    });

    $("#form-registrarPerjur").validate({
        rules:
                {
                    cboTipDocumento: {required: true, valueNotEquals: "default"},
                    txtRazonSocial: {required: true, minlength: 3},
                    txtNroRuc: {required: true, number: true, valueRuc: true},
                    cboGiroNegocio: {valueNotEquals: "default"},
                    cboLocal: {valueNotEquals: "default"},
                    txtFechaCons: {required: true, valueFecha: true},
                    txtIngreso: {required: true, number: true},
                    cboUrbanizacion: {valueNotEquals: "default"},
                    txtDireccion: {required: true, minlength: 8},
                    txtTelefono: {required: false, rangelength: [6, 8], valueTelefono: true},
                    txtCelular: {required: true, minlength: 9},
                    txtEmail: {email: true}
                },
        messages:
                {
                    cboTipDocumento: {valueNotEquals: "Seleccione un tipo de documento!",required:"Seleccione un tipo de documento!"},
                    txtRazonSocial: {required: "Debe ingresar una razon socio", minlength: "EL nombre debe tener minimo 2 letras"},
                    txtNroRuc: {required: "Es necesario ingresar el numero de ruc", number: "El ruc deben ser numeros", valueRuc: "RUC debe tener 11 numeros"},
                    cboGiroNegocio: {valueNotEquals: "Seleccione un giro del negocio!"},
                    cboLocal: {valueNotEquals: "Seleccione un local"},
                    txtFechaCons: {required: "Debe seleccionar una fecha de constitucion", valueFecha: "La fecha seleccionada no es valida"},
                    txtIngreso: {required: "Se necesita un monto de ingreso?", number: "Solo se aceptan numeros"},
                    cboUrbanizacion: {valueNotEquals: "Seleccione una urbanizacion!"},
                    txtDireccion: {required: "Debe ingresar una direccion", minlength: "La direccion debe contener minimo 8 digitos"},
                    txtTelefono: {valueTelefono: "Numero Telefonico no valido", required: "Ingrese un numero telefonico", rangelength: "Numero Telefonocio debe estar entre 6 y 8 digitos"},
                    txtEmail: {email: "El formato no es correcto"}
                }
    });

    $('#cboTipDocumento').on('change', function () {
        $(this).valid();
    });
    $("#idpaises").on('change', function () {
        $(this).valid();
    });

    $("#cboLocal").on('change', function () {
        $(this).valid();
    });


    $("#cboGiroNegocio").on('change', function () {
        $(this).valid();
    });

    $("#cboOcupacion").on('change', function () {
        $(this).valid();
    });

    $("#idpaises").on('change', function () {
        $(this).valid();
    });

    $("#cboUrbanizacion").on('change', function () {
        $(this).valid();
    });

    $("#cboUrbanizacion").on('change', function () {
        $(this).valid();
    });

    $("#txtFechaCons").datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange: '1900:2999',
        dateFormat: 'dd/mm/yyyy',
        minDate: 0,
        defaultDate: null
    }).on('change', function () {
        $(this).valid();
    });

});

