/**********************Registro Socio******************************************/
$('#frmBuscarPersonaJuridicaNatural').submit(function (event) {
    event.preventDefault();
    if ($('#frmBuscarPersonaJuridicaNatural').valid()) {
        LimpiarData();
        buscarSocioxNumeroDocumento();
    }
});

$("#txtNroDocumentoIdentidad").keyup(function (e) {
    e.preventDefault();
    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
    if (key == 13) {
        $('#frmBuscarPersonaJuridicaNatural').submit();
    }
    if (key == 8){
        LimpiarData();
    }
});

function buscarSocioxNumeroDocumento() {

    var tipo = $('#cboTipoPersona').val();
    var url;

    var data = {'txtNroDocumentoIdentidad': $('#txtNroDocumentoIdentidad').val(), 'cboTipoPersona': tipo};

    if (tipo == 'N') {
        url = "../sPersonaNatural?action=BuscaPersonaNoSocioNoEmpleado";
    }
    if (tipo == 'J') {
        url = "../sPersonaJuridica?action=BuscaPersonaNoSocioNoEmpleado";
    }


    var success = function (res) {
        if (res.Result === 'OK') {
            if (tipo == 'N') {
                mostrarPersonaNatural(res);
            } else if (tipo == 'J') {
                mostrarPersonaJuridica(res);
            }

        } else if (res.Result == 'error') {
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er) {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod(url, data, success, error);
};

/******************************************************************************/

/**************************REINGRESO DE SOCIO**********************************/
function buscarSocioPorNroDoc() {
    var data = {'txtNroDocIden': $('#txtNroDocIden').val()};
    var success = function (res) {
        if (res.Result === 'OK'){
            muestraData(res);

        } else if (res.Result == 'error'){
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er) {
        toast('error', data + '-' + status + '-' + er, 'Error2!');

    };
    fn_callmethod("../sSocio?action=BuscaSocio", data, success, error);
};
/**************************REINGRESO DE SOCIO**********************************/
/**************************SolicitudPlazoFijoInteresAdelantado**********************************/
/**********************Busqueda Persona Estado de Cuenta***********************/
$('#frmBuscarPersonaxNroDoc').submit(function (event) {
    event.preventDefault();
    if ($('#frmBuscarPersonaxNroDoc').valid()) {
        buscarSocioxNroDocumento();
        limpiar();
    }
});

$("#txtNroDoc").keyup(function (e) {
    e.preventDefault();
    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
    if (key == 13) {
        $('#frmBuscarPersonaxNroDoc').submit();
    }
    if (key == 8)
    {
        LimpiarData();
    }
});

var buscarSocioxNroDocumento = function () {

    var txtNroDoc = $("#txtNroDoc").val();
    var cboTipoBusqueda = $("#cboTipoBusqueda").val();

    var data = {'txtNroDoc': txtNroDoc, 'cboTipoBusqueda': cboTipoBusqueda};

    var success = function (res) {
        if (res.Result == 'OK') {
            
            muestraInformacionSocio(res.listSocios);

        } else if (res.Result == 'error') {
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er) {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sSocio?action=obtenerInformacionSocio", data, success, error);
};
/**************************SolicitudPlazoFijoInteresAdelantado**********************************/
