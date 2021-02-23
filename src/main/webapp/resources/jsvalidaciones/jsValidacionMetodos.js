
$(function () {
    /*****************************IMPLEMENTACION DE VALIDACIONES *************/
// add the rule here
    $.validator.addMethod("valueNotEquals", function (value, element, arg) {

        return arg != value;
    }, "Value must not equal arg.");

//Valida RUC
    $.validator.addMethod("valueRuc", function (value, element) {
        var ex_regular_ruc = /^\d{11}(?:[-\s]\d{4})?$/;

        if (ex_regular_ruc.test(value)) {
            return true;
        } else
            return false;
    }, "Este Numero de documento no es valido");

//Valida Celular
    $.validator.addMethod("valueCelular", function (value, element) {
        var nro = -(value.length - 1);

        if (value.charAt(0) === '#' || !isNaN(value.charAt(0))) {

            if (!isNaN(value.slice(nro)))
            {
                return true;
            }

        } else
            return false;

    }, "Este Numero Movil no es Valido");

    $.validator.addMethod("valueTelefono", function (value, element) {
        var valor;


        if (isNaN(value) == false)
            valor = true;
        else if (value == "") {
            valor = true;

        } else {
            valor = false;
        }


        return valor;
    }, "El telefono no es valido");

    /*Arreglar*/
    $.validator.addMethod("ValueDocumento", function (value, element) {


        var tipoDocumento = document.getElementById("cboTipDocumento");
        var Tipo = tipoDocumento.options[tipoDocumento.selectedIndex].value;

        switch (Tipo) {
            case "0301":
                return validaDNI(value);
                break;
            case "0304":
                return validaPassport(value);
                break;
            case "0306":
                return validaPartidaNac(value);
                break;
            case "0310":
                return validaCarnetExtr(value);
                break;
            default:
                return validaDNI(value);
                break;

        }


        return this.optional(element);
    }, "El numero de documento debe tener digitos y no caracteres");

    $.validator.addMethod("valueFecha", function (value, element) {

        var fechaactual = (ObtenerFechaActualDMY());
        var fechaselect = (value);
        fechaactual = moment(fechaactual, "DD-MM-YYYY");//.format('L');
        fechaselect = moment(fechaselect, "DD-MM-YYYY");//.format('L');    

        if (fechaselect <= fechaactual) {
            return true;
        } else
            return false;
    }, "Esta fecha debe ser menor a la actual");


    $.validator.addMethod("valueFechaMenor", function (value, element) {

        var fechaactual = (ObtenerFechaActualDMY());
        var fechaselect = (value);
        fechaactual = moment(fechaactual, "DD-MM-YYYY");//.format('L');
        fechaselect = moment(fechaselect, "DD-MM-YYYY");//.format('L');    

        if (fechaselect >= fechaactual) {
            return true;
        } else
            return false;
    }, "Esta fecha debe ser mayor a la actual");


    $.validator.addMethod("valueDni", function (value, element) {

        var ex_regular_dni;
        ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;
        if (ex_regular_dni.test(value) == true) {
            return true;
        } else {

            return false;
        }
    }, "Esta fecha debe ser menor a la actual");

    //validaCuentaRuc
    $.validator.addMethod("validaCuentaRuc", function (value, element) {
        var ex_regular_ruc = /^\d{11}(?:[-\s]\d{4})?$/;

        var ex_regular_cuenta = /^\d{7}(?:[-\s]\d{4})?$/;

        var tipoBusqueda = $('#cboTipoBusqueda').val();

        if (tipoBusqueda == 1 && ex_regular_cuenta.test(value)) {
            return true;
        } else if (tipoBusqueda == 1 && ex_regular_cuenta.test(value) == false) {
            $.validator.messages.validaCuentaRuc = "Cuenta no valida";
        } else if (tipoBusqueda == 2 && ex_regular_ruc.test(value)) {
            return true;
        } else {
            $.validator.messages.validaCuentaRuc = "Ruc no valido";
        }
    }, $.validator.messages.validaCuentaRuc);

    $.validator.addMethod("validaComboCero", function (value, element) {


        value = $.trim(value);
        if (value != 0) {
            return true;
        } else {
            return false;
        }
    }, "Seleccione una Opcion");

    $.validator.addMethod("validaCuenta", function (value, element) {

        var ex_regular_cuenta = /^\d{7}(?:[-\s]\d{4})?$/;
        value = $.trim(value);
        if ((ex_regular_cuenta.test(value) == true)) {
            return true;
        } else {
            return false;
        }
    }, "Este Numero de cuenta no es valido");

    $.validator.addMethod("valueIdPersona", function (value, element) {


        value = $.trim(value);
        if (value.length == 7) {
            return true;
        } else {
            return false;
        }
    }, "Seleccione una Persona");



});

function Textarea_Sin_Enter($char, $mozChar, $id){
    //alert ($char+" "+$mozChar);
    $textarea = document.getElementById($id);
    niveles = -1;
    
    if($mozChar != null) { // Navegadores compatibles con Mozilla
        if($mozChar == 13){
            if(navigator.appName == "Opera") niveles = -2;
            $textarea.value = $textarea.value.slice(0, niveles);
        }
    // navegadores compatibles con IE
    } else if($char == 13) $textarea.value = $textarea.value.slice(0,-2);
}
