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

    $('#frmCaractBien').validate({
        rules: {
            txtImpFact: {
                required: true,
                number: true,
                min: 0.01
            },
            txtPreUniFact: {
                required: true,
                number: true,
                min: 0.01
            },
            txtCantFact: {
                required: true,
                number: true,
                min: 0.01
            },
            txtValUnit: {
                required: true,
                number: true,
                min: 0.01
            },
            cboTipoServicio: {
                valueNotEquals: "default"
            },
            txtDetFact: {required: true, minlength: 5},
            txtIGVDet: {
                required: true,
                number: true,
                min: 0.01
            }

        },
        messages: {
            txtImpFact: {
                required: "Debe ingresar un importe",
                number: "Solo se admiten numeros",

            },
            txtValUnit: {
                required: "Debe ingresar un valor unitario",
                number: "Solo se admiten numeros",

            },
             txtPreUniFact: {
                required: "Debe ingresar un precio unitario",
                number: "Solo se admiten numeros",

            },
            txtCantFact: {
                required: "Debe ingresar una cantidad",
                number: "Solo se admiten numeros",

            },

            cboTipoServicio: {valueNotEquals: "Seleccione un tipo de Servicio!"},
            txtDetFact: {required: "Ingrese el detalle de la factura", minlength: "El detalle debe contener almenos 5 caracteres", min: "Se debe ingresar montos mayor a cero"}
        },
        txtIGVDet: {
            required: "Debe ingresar el IGV",
            number: "Solo se admiten numeros",

        },
    });


    $('#frmGuiaRemision').validate({
        rules: {

            txtNroGuia: {
                required: true,
               
            },
            txtFechaGuia: {required: true, valueFecha: true},
            cboTipoServicio: {
                valueNotEquals: "default"
            }

        },
        messages: {

            txtNroGuia: {required: "Debe ingresar el numero de la Guia"},
            txtFechaGuia: {valueFecha: "Seleccione una Fecha mayor a la actual"},
            cboTipoServicio: {valueNotEquals: "Seleccione un tipo de Guia de Remisión!"}

        },
    });

    $("#form-registrarFactura").validate({
        rules:
                {
                    txtNumFactura: {required: true, minlength: 12},
                    txtRUCEmisor: {required: true, number: true, valueRuc: true},
                    txtFecAdq: {required: true, valueFecha: true},
                    txtSubTotal: {min: 0.01},
                    txtIGV: {min: 0.01},
                    txtTotal: {min: 0.01}
                },
        messages:
                {
                    txtNumFactura: {required: "Debe ingresar un numero de documento", minlength: "EL numero de documento debe tener 12 numeros"},
                    txtRUCEmisor: {required: "Es necesario ingresar el numero de ruc", number: "El ruc deben ser numeros", valueRuc: "RUC debe tener 11 numeros"},
                    txtFecAdq: {valueFecha: "Seleccione una Fecha mayor a la actual"},
                    txtSubTotal: {min: "El subtotal tiene que se mayor a cero"},
                    txtIGV: {min: "El IGV tiene que se mayor a cero"},
                    txtTotal: {min: "El total tiene que se mayor a cero"},

                }
    });



    $("#txtImpFact").on('change', function () {
        $(this).valid();
    });

    $("#txtRUC").on('change', function () {
        $(this).valid();
    });

    $("#txtNumFactura").on('change', function () {
        $(this).valid();
    });




});

