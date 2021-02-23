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
    
    $('#frmRegistrarApertura').validate({
        rules: {
            txtMontoApertura: {
                required: true,
                number: true,
                validaMApertura: true
            },
            cboTipoSocio: {
                valueNotEquals: "default"
            },
            cboTipMoneda: {
                valueNotEquals: "default"
            },
            cboTipoApertura: {
                valueNotEquals: "default"
            },
            cboTipoCuenta: {
                valueNotEquals: "default"
            },
            cboTipoPago: {
                valueNotEquals: "default"
            }
        },
        messages: {
            txtMontoApertura: {
                required: "Debe ingresar un monto de apertura",
                number: "Solo se admiten numeros",
                validaMApertura: "El Numero ingresado es menor al monto minimo"
            },
            cboTipoSocio: {valueNotEquals: "Seleccione un tipo de Socio!"},
            cboTipMoneda: {valueNotEquals: "Seleccione un tipo de Moneda!"},
            cboTipoApertura: {valueNotEquals: "Seleccione un tipo de Apertura!"},
            cboTipoCuenta: {valueNotEquals: "Seleccione un tipo de Cuenta!"},
            cboTipoPago: {valueNotEquals: "Seleccione un tipo de Pago!"}          
        }

    });

    $("#cboTipoSocio").on('change', function () {
        $(this).valid();
    });
    $("#cboTipMoneda").on('change', function () {
        $(this).valid();
    });
    $("#cboTipoApertura").on('change', function () {
        $(this).valid();
    });
    $("#cboTipoPago").on('change', function () {
        if($(this).disabled==true){
            $(this).valid();
        }
        
    });


    $.validator.addMethod("validaMApertura", function (value, element) {

        var txtMontoMinimo = $('#txtMontoMinimo').val();
        var result=false;
        var Number = (value.replace(/[^0-9\.-]+/g, ""));      
        if((Math.round(Number * 100) >= Math.round(txtMontoMinimo * 100))){
            result = true;
        }         
        return result;

    }, "Este Numero no cumple con los requisitos");
    
});

