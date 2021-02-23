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



    $("#form-registrarCheque").validate({
        rules:
                {
                    txtcheque: {required: true, minlength: 8, maxlength: 8, number: true},
                    txtNombre: {required: true},
                    txtNroDoc: {required: true, number: true},
                    txtFecCheq: {required: true, valueFecha: true},
                    txtglosa: {required: true},
                    txtSubTotal: {required: true, min: 0.01, number: true}

                },
        messages:
                {
                    txtcheque: {required: "Debe ingresar un numero de cheque", minlength: "El numero de documento debe tener 10 numeros", number: "Solo se aceptan numeros"},
                    txtNombre: {required: "Debe ingresar un Titular"},
                    txtglosa: {required: "Debe ingresar una Glosa"},
                    txtNroDoc: {required: "El Titular debe Tener un Numero de Documento", number: "Solo se aceptan numeros"},
                    txtFecCheq: {valueFecha: "Seleccione una Fecha mayor a la actual"},
                    txtSubTotal: {required: "Debe ingresar el monto del cheque", min: "El monto tiene que se mayor a cero", number: "Solo se aceptan numeros"},

                }
    });





    $("#txtNroDoc").on('change', function () {
        $(this).valid();
    });

    $("#txtcheque").on('change', function () {
        $(this).valid();
    });




});

