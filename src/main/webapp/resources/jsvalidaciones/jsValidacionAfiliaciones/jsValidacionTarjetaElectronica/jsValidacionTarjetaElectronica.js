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
            validaTipoNroDoc: "Debe Ingresar la cantidad de d&iacutegitos segun el numero de documento"
        }
    }
});

$("#form-updatepass").validate({
    rules: {
        txtPassAct: {
            required: true,
            exactlength: 4,
            number : true
        },
        txtNewPass:{
            required :true,
            exactlength: 4,
            number : true
        },
        txtRepNewPass:{
            required :true,
            equalTo: "#txtNewPass",
            exactlength: 4,
            number : true
        }
    },
    messages: {
        txtPassAct: {
            required: "Debe ingresar un valor",
            exactlength: "La contrase&ntildea debe ser de 4 d&iacutegitos",
            number : "La contrase&ntildea deben ser solo n&uacutemeros"
            
        },
        txtNewPass:{
            required: "Debe ingresar un valor",
            exactlength: "La contrase&ntildea debe ser de 4 d&iacutegitos",
            number : "La contrase&ntildea deben ser solo n&uacutemeros"
        },
        txtRepNewPass:{
            required: "Debe ingresar un valor",
            exactlength: "La contrase&ntildea debe ser de 4 d&iacutegitos",
            number : "La contrase&ntildea deben ser solo n&uacutemeros",
            equalTo: "Los Datos Ingresados no coinciden"
        }
    }
});

$.validator.addMethod("exactlength", function(value, element, param) {
 return this.optional(element) || value.length == param;
}, $.validator.format("Please enter exactly {0} characters."));


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
    } else if ((opcion == 3) && isNaN(value) == false && value.length == 15) {
        result = true;
    } else
        result = false;

    return result;

}, "Este Numero de documento no es valido");

/******************************************/

