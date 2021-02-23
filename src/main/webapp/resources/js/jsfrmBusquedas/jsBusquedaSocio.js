$("#frmBuscarSocio").validate({
    rules: {
        txtCriterioBusqueda: {
            required: true,
            validaTipoNroDoc: true
        }
    },
    messages: {
        txtCriterioBusqueda: {
            required: "Debe ingresar un valor",
            validaTipoNroDoc: "Debe Ingresar la cantidad de digitos segun el numero de documento"
        }
    }
});


$.validator.addMethod("validaTipoNroDoc", function (value, element) {

    var ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;
    var ex_regular_ruc = /^\d{11}(?:[-\s]\d{4})?$/;
    var Tipo = $('#cboTipoPersona').val();
    var opcion = $('input:radio[name=optradio]:checked').val();
    var result;
    
    if ((opcion == 1) && (Tipo == 'N') && (ex_regular_dni.test(value) == true)) {
        result = true;

    } else if ((opcion == 1) && (Tipo == 'J') && (ex_regular_ruc.test(value) == true)) {
        result = true;
    } else if ((opcion == 2)) {
        result = true;
    } else if ((opcion == 3) && isNaN(value) == false && value.length == 16) {
        result = true;
    } else
        result = false;

    return result;

}, "Este Numero de documento no es valido");