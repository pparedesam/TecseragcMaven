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

    $('#frmBuscarPersonaxNroDoc').validate({
        rules: {
            txtNroDoc: {
                required: true,
                validaTipoNroDoc: true
            }
        },
        messages: {
            txtNroDoc: {
                required: "Debe ingresar un numero de cuenta",
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
        } else if ((tipobusqueda == 3) && !isNaN(value)) {
            $('#txtNroDoc').val(String("0000000" + value).slice(-7));
            result = true;
        } else
            result = false;

        return result;

    }, "Este Numero de documento no es valido");
});