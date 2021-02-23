/*********************Validaciones para Apoderado********************/
  $("#tbl_ConvenioApoderadoForm").validate({
      
        rules: {
            txtCodigoPlanilla :{
                digits :true
            },
            txtNomPersona:{
                valuePersona :true
            },
            cboTipoPlanilla0:{
                valueNoCero: true}
        },
        messages: {
            txtNomPersona: {
                valuePersona: "Debe seleccionar una persona"
            },
            txtCodigoPlanilla :{
                digits: "Deben ingresar numeros"
            },
            cboTipoPlanilla0:{
                valueNoCero : 'Debe Seleccionar una opcion'
            }
        },
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });

$.validator.addMethod("valueNoCero", function (value, element) {
        
   
   if(value!=0){
        
        return true;
   }    
      
   else return false;
    
}, "Debe Seleccionar una Opcion");
$.validator.addMethod("valuePersona", function (value, element) {
        
   
   if($("#txtIdPersona").val()!=0){
        
        return true;
   }    
      
   else return false;
    
}, "Debe Seleccionar una Persona en el buscador de persona");


/*******************************************Validacion Integrante**************************************************/



  $("#tbl_IntegrantePlanillaConvenioPlanillaForm").validate({
      
        rules: {
           
            txtNomPersona:{
                valuePersona :true
            }
        },
        messages: {
            txtNomPersona: {
                valuePersona: "Debe seleccionar una persona"
            }
        },
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });
    $.validator.addMethod("valuePersona", function (value, element) {
        
   
   if($("#idIntegrante").val()!=0){
        
        return true;
   }    
      
   else return false;
    
}, "Debe Seleccionar una Persona en el buscador de persona");