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


    if ($("#cboModalidad").val() === '01') {
        $("#form-registrarGuiaRemision").validate({
            rules:
                    {
                        txtEmisorRUC: {required: true},
                        txtDestinatarioRUC: {required: true},
                        txtTransportistaNroDoc: {required: true},
                        txtConductorNroDoc: {required: true},
                        txtPuntoPartida: {required: true, minlength: 5, maxlength: 100},
                        txtPuntoLlegada: {required: true, minlength: 5, maxlength: 100},
                        txtTrasladoFecha: {required: true, valueFechaMenor: true},
                        txtMarcaNroPlaca: {required: true, minlength: 5, maxlength: 8},
                        txtTrasladoPesoBruto: {required: true, number: true, min: 0.01},
                        txtTrasladoFechaEntregaTransportista: {required: true, valueFechaMenor: true}


                    },
            messages:
                    {

                        txtEmisorRUC: {required: "Debe ingresar al Emisor"},
                        txtDestinatarioRUC: {required: "Debe ingresar al Destinatario"},
                        txtTransportistaNroDoc: {required: "Debe ingresar a la Empresa de Transporte"},
                        txtConductorNroDoc: {required: "Debe ingresar al Conductor"},
                        txtPuntoPartida: {required: "Debe ingresar la direccion de partida", minlength: "La direccion debe contar con al menos 5 caracteres", maxlength: "La direccion no debe exceder de 100 caracteres"},
                        txtPuntoLlegada: {required: "Debe ingresar la direccion de llegada", minlength: "La direccion debe contar con al menos 5 caracteres", maxlength: "La direccion no debe exceder de 100 caracteres"},
                        txtTrasladoFecha: {required: "Debe de ingresar una fecha de translado", valueFechaMenor: "Seleccione una Fecha mayor a la actual"},
                        txtMarcaNroPlaca: {required: "Debe ingresar la placa del vehiculo", minlength: "La placa debe contar con al menos 5 caracteres", maxlength: "La placa no debe exceder de 8 caracteres"},
                        txtTrasladoPesoBruto: {required: "Debe ingresar el peso", number: "El peso deben ser numeros", min: "El peso tiene que se mayor a cero"},
                        txtTrasladoFechaEntregaTransportista: {required: "Debe de ingresar una fecha de translado", valueFechaMenor: "Seleccione una Fecha mayor a la actual"}
                    }
        });
    } else if ($("#cboModalidad").val() === '02') {
        $("#form-registrarGuiaRemision").validate({
            rules:
                    {
                        txtEmisorRUC: {required: true},
                        txtDestinatarioRUC: {required: true},
                        txtTransportistaNroDoc: {required: true},
                        txtPuntoPartida: {required: true, minlength: 5, maxlength: 100},
                        txtPuntoLlegada: {required: true, minlength: 5, maxlength: 100},
                        txtTrasladoFecha: {required: true, valueFechaMenor: true},
                        txtTrasladoPesoBruto: {required: true, number: true, min: 0.01},
                        txtTrasladoFechaEntregaTransportista: {required: true, valueFechaMenor: true}

                    },
            messages:
                    {
                        txtEmisorRUC: {required: "Debe ingresar al Emisor"},
                        txtDestinatarioRUC: {required: "Debe ingresar al Destinatario"},
                        txtTransportistaNroDoc: {required: "Debe ingresar a la Empresa de Transporte"},
                        txtPuntoPartida: {required: "Debe ingresar la direccion de partida", minlength: "La direccion debe contar con al menos 5 caracteres", maxlength: "La direccion no debe exceder de 100 caracteres"},
                        txtPuntoLlegada: {required: "Debe ingresar la direccion de llegada", minlength: "La direccion debe contar con al menos 5 caracteres", maxlength: "La direccion no debe exceder de 100 caracteres"},
                        txtTrasladoFecha: {required: "Debe de ingresar una fecha de translado", valueFechaMenor: "Seleccione una Fecha mayor a la actual"},
                        txtTrasladoPesoBruto: {required: "Debe ingresar el peso", number: "El peso deben ser numeros", min: "El peso tiene que se mayor a cero"},
                        txtTrasladoFechaEntregaTransportista: {required: "Debe de ingresar una fecha de translado", valueFechaMenor: "Seleccione una Fecha mayor a la actual"}
                    }
        });

    }



    $("#frmCaractBien").validate({
        rules:
                {
                    txtDetFact: {required: true, minlength: 5, maxlength: 250},
                    txtImpFact: {required: true, number: true, min: 0.01},

                },
        messages:
                {

                    txtDetFact: {required: "Debe ingresar una descripcion al item", minlength: "La descripcion debe contar con al menos 5 caracteres", maxlength: "La descripcion no debe exceder de 250 caracteres"},
                    txtImpFact: {required: "Debe ingresar la cantidad", number: "La cantidad deben ser numeros", min: "La cantidad tiene que se mayor a cero"},

                }
    });



});

