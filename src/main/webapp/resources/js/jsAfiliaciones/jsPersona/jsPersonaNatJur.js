/**********************Busqueda Persona Estado de Cuenta***********************/
$('#frmBuscarPersonaxNroDoc').submit(function (event) {
    event.preventDefault();
    if ($('#frmBuscarPersonaxNroDoc').valid()) {
     //   LimpiarData();
        buscarSocioxNroDocumento();
    }
});

var buscarSocioxNroDocumento = function () {
    
    var txtNroDoc=  $("#txtNroDoc").val();
    var cboTipoBusqueda=  $("#cboTipoBusqueda").val();
    
    var data = {'txtNroDoc':txtNroDoc,'cboTipoBusqueda':cboTipoBusqueda};
    
    var success = function (res) {
        if (res.Result == 'OK'){
            limpiar();
            muestraInformacionSocio(res.listSocios);

        } else if (res.Result == 'error'){
            
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er){     
       // toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sSocio?action=obtenerSocioPersonaNatJur", data, success, error);
};
/**********************Busqueda Persona Estado de Cuenta***********************/