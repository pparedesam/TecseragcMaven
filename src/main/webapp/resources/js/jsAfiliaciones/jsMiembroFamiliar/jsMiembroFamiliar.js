var tblMiembroF;
limpiarDatosSocio();

function mostrarDatosSocioNatural() {

    var data = {'txtNroDocIden': $('#txtCuenta').val()};

    var success = function (res) {
        if (res.Result == 'OK') {            
            if ( res.Socio.objEstadoSocio.codigo.substr(0,1)=='1') {
                document.getElementById("txtNumDocumento").value = res.Socio.objPersona.nroDocumento;
                document.getElementById("txtNroCuenta").value = res.Socio.idSocio;
                document.getElementById("txtSocio").value = res.Socio.objPersona.nombres;
                document.getElementById("txtEstadoSocio").value = res.Socio.objEstadoSocio.descripcion;
                document.getElementById('txtNumDocumento').setAttribute("data-idPersona", res.Socio.objPersona.idPersona);
                tableMiembroFamiliar();
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

function limpiarDatosSocio() {
    document.getElementById("txtNumDocumento").value ="";
    document.getElementById("txtNroCuenta").value ="";
    document.getElementById("txtSocio").value ="";
    document.getElementById("txtEstadoSocio").value ="";
    document.getElementById('txtNumDocumento').setAttribute("data-idPersona","");
    document.getElementById("btnAgregarBeneficiario").disabled =true;
}
