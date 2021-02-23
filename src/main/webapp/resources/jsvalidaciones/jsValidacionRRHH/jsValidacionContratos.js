$(function () {
    
    
    $(".simple-select").select2({
        minimumResultsForSearch: Infinity
    });

    $(".simple-select2").select2();

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

    $('#frmBuscarEmpleado').validate({
        rules: {
            txtBuscarEmpleado: {
                required: true,
                validaTipoNroDoc: true
            }
        },
        messages: {
            txtBuscarEmpleado: {
                required: "Debe ingresar un numero DNI",
                validaTipoNroDoc: "Ingrese los datos correctamente segun los items seleccionados"
            }
        }
    });
    


    $('#cboTipoPersona').on('change', function () {
        $(this).valid();
    });
    $('#cboTipoBusqueda').on('change', function () {
        $(this).valid();
    });

    $('#txtFechaInicio').datepicker('setDate', '01/02/2006');
    $('#txtFechaInicio').datepicker('update');
    $('#txtFechaFin').datepicker('setDate', ObtenerFechaActualDMY());
    $('#txtFechaFin').datepicker('update');


    $("#txtFechaInicio").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: 'dd/mm/yyyy',
        defaultDate: '01/02/2006',
        yearRange: '1900:2999'

    }).on('change', function () {
        $(this).valid();
    });

    $("#txtFechaFin").datepicker({
        changeMonth: true,
        changeYear: true,
        yearRange: '1900:2999',
        dateFormat: 'dd/mm/yyyy',
        minDate: 0,
        defaultDate: null
    }).on('change', function () {
        $(this).valid();
    });

    $('#data_1 .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true
    });

    $.validator.addMethod("validaTipoNroDoc", function (value, element) {

        var ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;
        
        var tipobusqueda = $('#cboTipoBusqueda').val();
        var result;
        var nro = value.length;
        if (nro == 8 && (tipobusqueda == 1) && (ex_regular_dni.test(value))) {
            result = true;
        }else if ((tipobusqueda == 2)) {
            result = true;
        }else
            result = false;

        return result;

    }, "Este Numero de documento no es valido");


});