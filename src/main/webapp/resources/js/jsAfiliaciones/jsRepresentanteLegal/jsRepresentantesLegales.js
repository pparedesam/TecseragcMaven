$(".simple-select").select2({
    minimumResultsForSearch: Infinity
});

$(".simple-select2 ").select2();

function mostrarSocioJuridico() {
    var data = {'txtCuentaRuc': $('#txtCuentaRuc').val(),'tipoBusqueda':$('#cboTipoBusqueda').val()};

    var success = function (res) {
        if (res.Result == 'OK') {
                limpiarDatosSocio();
                document.getElementById("txtRazonSocial").value = res.Socio.objPersona.nombres;
                document.getElementById("txtNroRuc").value =res.Socio.objPersona.nroDocumento;
                document.getElementById("txtNroCuenta").value = res.Socio.idSocio;
                document.getElementById("txtEstadoSocio").value = res.Socio.objEstadoSocio.descripcion;
                document.getElementById('txtNroRuc').setAttribute("data-idPersona", res.Socio.objPersona.idPersona);
                tableRepresentanteLegal(res.Socio.objPersona.idPersona);

        } else {
            toast('error', res.Message, 'Error');
            limpiarDatosSocio();
        }
    };
    var error = function (data, status, er)
    {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sSocioJuridico?action=ObtenerDatosBasicosSocioJuridicoxCuentaxRuc", data, success, error);

}

function limpiarDatosSocio(){

    $('#txtRazonSocial').val();
    $('#txtIdPersonaJuridica').val();
    $('#txtNroRuc').val();
    document.getElementById('txtNroRuc').setAttribute("data-idPersona",'');
};