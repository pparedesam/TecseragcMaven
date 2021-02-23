
$(function () {

    $("#frmBuscarSocioNatural").validate({
        rules: {
            txtSocioNatural: {
                required: true,
                validaCuentaNroDocNombrexTarjeta: true

            }
        },
        messages: {
            txtSocioNatural: {
                required: "Debe Ingresar un Parametro de Busqueda"
            }
        }
    });
    
    /**
     * validaCuentaNroDocNombres    
     * 1.-Nro Doc
     * 2.-Nombres
     * 3.-Tarjeta
     * 4.-Cuenta
    
     **/
    $.validator.addMethod("validaCuentaNroDocNombrexTarjeta",function (value, element) {
    
    
        var ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;
        var ex_regular_tarjeta = /^\d{16}(?:[-\s]\d{4})?$/;
        var ex_regular_cuenta = /^\d{7}(?:[-\s]\d{4})?$/;
        var tipoBusqueda = $('#cboTipoBusqueda').val();

        if (tipoBusqueda == 4 && ex_regular_cuenta.test(value)) {
            return true;
        } else if (tipoBusqueda == 4 && ex_regular_cuenta.test(value) == false) {
            $.validator.messages.validaCuentaNroDocNombrexTarjeta = "Numero Cuenta no valida";
        } else if (tipoBusqueda == 1 && ex_regular_dni.test(value)) {
            return true;
        } else if (tipoBusqueda == 1 && ex_regular_dni.test(value) == false) {
            $.validator.messages.validaCuentaNroDocNombrexTarjeta = "Numero de Dni no valido";
        } else if (tipoBusqueda == 3 && ex_regular_tarjeta.test(value)) {
            return true;
        } else if (tipoBusqueda == 3 && ex_regular_tarjeta.test(value) == false) {
            $.validator.messages.validaCuentaNroDocNombrexTarjeta = "Ingrese numero de tarjeta electronica valida";
        } else if (tipoBusqueda == 2 && value.length >= 3) {
            return true;
        } else {
            $.validator.messages.validaCuentaNroDocNombrexTarjeta = "Debe ingresar mayor o igual a tres caracteres";
        }

    }, $.validator.messages.validaCuentaNroDocNombrexTarjeta);
});