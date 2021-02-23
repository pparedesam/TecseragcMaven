$('#frmBuscarSocioNJ').validate({

    rules: {
        txtNroDocIden: {
            required : true,
            validaNroDocNJ: true
        }
    },
    messages: {
        txtNroDocIden: {
            required : "Debe ingresar un numero de documento"          
        }
    }

});

$.validator.addMethod("validaNroDocNJ", function (value, element) {

    value = value.trim();
    var ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;
    var ex_regular_ruc = /^\d{11}(?:[-\s]\d{4})?$/;   
    
    if (ex_regular_dni.test(value)||ex_regular_ruc.test(value)) {
       return true;

    } else 
        return false;    

},"Ingrese numero de documento valido,DNI son 8, RUC son 11 digitos");