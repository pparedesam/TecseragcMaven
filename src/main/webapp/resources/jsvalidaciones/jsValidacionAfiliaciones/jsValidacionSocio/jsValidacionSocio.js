$( "#tabs" ).tabs();

$(".simple-select").select2({
    minimumResultsForSearch: Infinity
});

$(".simple-select2").select2();
 
 
/*
 * FORMULARIO DE REGISTRO DE SOCIO
 */
$('#frmBuscarPersonaJuridicaNatural').validate({

    rules: {
        txtNroDocumentoIdentidad: {
            required : true,
            validaNroDocNJ: true
        }
    },
    messages: {
        txtNroDocumentoIdentidad: {
            required : "Debe ingresar un numero de documento"          
        }
    }

});

$.validator.addMethod("validaNroDocNJ", function (value, element) {

    var ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;
    var ex_regular_ruc = /^\d{11}(?:[-\s]\d{4})?$/;
    var tipoPersona = $('#cboTipoPersona').val();
    var result=false;;

    if (tipoPersona==='N' && ex_regular_dni.test(value)) {
        result = true;

    } else if (tipoPersona==='J' && ex_regular_ruc.test(value))
    {
        result = true;
    } 

    return result;

},"Ingrese numero de documento valido,DNI son 8, RUC son 11 digitos");


/******************************************************************************/