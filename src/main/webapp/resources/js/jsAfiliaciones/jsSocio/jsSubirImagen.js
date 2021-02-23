function mostrarDatosSocioNatural() {

    var data = {'txtNroDocIden': $('#txtCuenta').val()};

    var success = function (res) {
        if (res.Result == 'OK') {            
            if ( res.Socio.objEstadoSocio.codigo.substr(0,1)=='1') {
                document.getElementById("txtNumDocumento").value = res.Socio.objPersona.nroDocumento;
                document.getElementById("txtNroCuenta").value = res.Socio.idSocio;
                document.getElementById("txtSocio").value = res.Socio.objPersona.nombres;
                document.getElementById('txtNumDocumento').setAttribute("data-idPersona", res.Socio.objPersona.idPersona);
                 $("#divimagenes").show();
                
            }else{
               toast('error',"Estado del Socio : " +res.Socio.objEstadoSocio.descripcion , 'Error');
               limpiarDatosSocio();
            }  
        } else {
            toast('error', res.Message, 'Error');
            limpiarDatosSocio();
        }
    };
    var error = function (data, status, er){
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sSocio?action=ObtenerDatosBasicosSocioNaturaloJuridicoxCuentaSocio", data, success, error);
};

 $('#clearFotoSocio').click(function(){      
        $('.image-preview-filename').val("");
        $('.image-preview-clear').hide();
        $('.image-preview-input input:file').val("");
        $(".image-preview-input-title").text("Browse"); 
    }); 
    
        $(".input-file-preview-fotosocio input:file").change(function (){     

    }); 