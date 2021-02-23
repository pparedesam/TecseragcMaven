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
    $('#frmRegTiposResp').validate({
        rules: {
            txtRespuesta: {required: true},
            txtCalificacion: {required: true, number: true,valueNum:true},
            txtImpacto: {required: true, number: true,valueNum:true}         
        },
        messages: {
            txtRespuesta: {required: "Debe ingresar una respuesta"},
            txtCalificacion: {required: "Debe ingresar una calificacion",
                number:"Deben ser numeros",
                valueNum : "Debe ser un numero mayor o igual a cero"
            },
            txtImpacto: {required: "Debe ingresar un impacto",
                number:"Deben ser numeros",
                valueNum : "Debe ser un numero mayor o igual a cero"            }
            
        }
    });

    $('#cboTipoBusqueda').on('change', function () {
        $(this).valid();
    });

    $.validator.addMethod("valueNum", function (value, element) {
        if (value >= 0) {
            return true;
        } else
            return false;
    }, "Debe Ingresar un valor mayor o igual a cero");

});