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

    $("#form-buscar").validate({
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

    $("#form-registrar").validate({
        rules: {
            txtNombres: {required: true, minlength: 3},
            txtApePaterno: {required: true, minlength: 3},
            txtApeMaterno: {required: true, minlength: 3},
            cboTipDocumento: {required: true, valueNotEquals: "default"},
            txtNumDocumento: {ValueDocumento: true},
            cboSexPersona: {valueNotEquals: "default"},
            txtFechaNac: {required: true, valueFecha: true},
            cboEstPersona: {valueNotEquals: "default"},
            txtTelefono: {required: false, rangelength: [6, 8], valueTelefono: true},
            cboGradoInstruccion: {required: true},
            cboProfesion: {required: true},
            cboOcupacion: {required: true},
            cboCIIU: {required: true},
            txtIngMensual: {required: true, number: true},
            idpaises: {required: true},
            cboUrbanizacion: {required: true},
            cboDiscapacidad: {required: true, valueNotEquals: "default"},
            txtDireccion: {required: true},
            cboTipVivienda: {valueNotEquals: "default"},
            txtCelular: {rangelength: [9, 10], valueCelular: true}
        },
        messages:
                {
                    txtNombres: {required: "Ingrese el nombre de la persona", minlength: "El nombre debe contener almenos 3 caracteres"},
                    cboTipDocumento: {valueNotEquals: "Seleccione un tipo de documento!", required: "Seleccione un tipo de documento!"},
                    cboSexPersona: {valueNotEquals: "Seleccione un Genero!"},
                    cboEstPersona: {valueNotEquals: "Seleccione un Estado Civil"},
                    txtFechaNac: {valueFecha: "Seleccione una Fecha menor a la actual"},
                    txtNumDocumento: {ValueDocumento: "Ingrese la cantidad de caracteres correctos segun su tipo de documento"},
                    txtTelefono: {valueTelefono: "Numero Telefonico no valido", rangelength: "Numero Telefonico debe estar entre 6 y 8 digitos"},
                    cboGradoInstruccion: {required: "Seleccione un grado de instruccion"},
                    cboProfesion: {required: "Seleccione una Profesion"},
                    cboOcupacion: {required: "Seleccione una Ocupacion"},
                    cboCIIU: {required: "Seleccione una CIIU"},
                    txtIngMensual: {required: "Se necesita un monto de ingreso?", number: "Solo se aceptan numeros"},
                    idpaises: {required: "Seleccione un pais"},
                    cboUrbanizacion: {required: "seleccione una urbanizacion"},
                    cboDiscapacidad: {valueNotEquals: "Seleccione una Opci&oacute;n"},
                    txtDireccion: {required: "Ingrese una Direcci\xf3n"},
                    cboTipVivienda: {valueNotEquals: "Seleccione un tipo de vivienda"},
                    txtCelular: {rangelength: "El Numero Telefonico debe tener entre 9 o 10 digitos", valueCelular: "El numero movil es erroneo"}
                }
    });


    $('#cboTipDocumento').on('change', function () {
        $(this).valid();
    });
    $('#cboGradoInstruccion').on('change', function () {
        $(this).valid();
    });

    $("#cboProfesion").on('change', function () {
        $(this).valid();
    });
    $("#cboOcupacion").on('change', function () {
        $(this).valid();
    });
    $("#cboCIIU").on('change', function () {
        $(this).valid();
    });
    $("#idpaises").on('change', function () {
        $(this).valid();
    });
    $("#cboUrbanizacion").on('change', function () {
        $(this).valid();
    });
    $("#cboTipVivienda").on('change', function () {
        $(this).valid();
    });
    $("#cboDiscapacidad").on('change', function () {
        $(this).valid();
    });
    $("#cboSexPersona").on('change', function () {
        $(this).valid();
    });
    $("#cboEstPersona").on('change', function () {
        $(this).valid();
    });

    $('.selectpicker').on('change', function () {
        $(this).valid();
    });

    $("#txtFechaNac").datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange: '1900:2999',
        dateFormat: 'dd/mm/yyyy',
        minDate: 0,
        defaultDate: null,
        setDate: ObtenerFechaActualDMY()
    }).on('change', function () {
        $(this).valid();
    });

});
