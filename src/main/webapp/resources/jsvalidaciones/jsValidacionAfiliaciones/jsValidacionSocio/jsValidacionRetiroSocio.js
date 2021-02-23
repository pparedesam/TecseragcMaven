$('#frmRetiroSocio').validate({
    //ignore: "input[type='text']:hidden",
    ignore: [],
    rules: {
//        txtIdPersona :
//                {valueIdPersona : true},
        cboEstadoSocio:
                {
                 valueNotEquals: "default"

                }
    },
    messages: {

        cboEstadoSocio: {
            valueNotEquals: "Debe Seleccionar un estado de retiro"
        }
//        ,txtIdPersona : {
//            valueIdPersona : "Debe buscar una Persona"
//        }

    }

});


//$.validator.addMethod("valueIdPersona", function (value, element){
//      
//      console.log(value.length);
//      if(value.length==7){
//          return true;
//      }
//      else return false;
//      
//      
//
//    }, "No ha seleccionado un Socio");


//function validarSocio()
//{
//    var textbox = document.getElementById("txtcuenta");
//    if (textbox == null)
//    {
//        toast('error', 'Se Necesita un socio', 'Error!');
//
//    } else
//    {
//
//        var data = {
//            'txtcuenta': document.getElementById("txtcuenta").value,
//            'cboestado': document.getElementById("cboEstadoSocio").value
//        };
//        
//        ejecutarservletRetirarSocio(data);
//    }
//}
//;
