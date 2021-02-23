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
    $('#frmRegAreaSubFactores').validate({
        rules: {
            cboSubfactor: {valueNotEquals: "default"}           
        },
        messages: {
            cboSubfactor: {valueNotEquals: "SELECCIONE UNA OPCION"}  
        }
    });
    
     $("#cboSubfactor").on('change', function () {
        $(this).valid();
    });
});