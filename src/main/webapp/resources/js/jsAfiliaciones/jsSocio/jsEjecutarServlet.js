function ejecutarServletSelect(data, url) {
  
    var success = function (res) {
        if (res.Result === 'OK')
        {         
           muestraData(res);

        } else if (res.Result === 'error')
        {
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er)
    {
        toast('error', data + '-' + status + '-' + er, 'Error Ejecutar Servlet!');
    };
    fn_callmethod(url, data, success, error);
}

function ejecutarServletUpdate(data, url) {
    var success = function (res) {
        if (res.Result === 'OK')
        {
            toast('success', 'Proceso Realizado Exitosamente', 'Exito!');
            setTimeout("location.reload(true);", 5000);
            
        } else if (res.Result === 'error')
        {
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er)
    {
        toast('error', data + '-' + status + '-' + er, 'Error Servlet Update!');
    };
    fn_callmethod(url, data, success, error);
}

function ejecutarservletRetirarSocio(data)
{
    var url = "../sSocio?action=retirar";

    ejecutarServletUpdate(data, url);
}
;

function  ejecutarservletBuscarSocio(tipBusqueda) {
    
    var data = {'txtNroDocIden': $('#txtNroDocIden').val()};

    var url;
    if (tipBusqueda == 1) {

        url = "../sSocio?action=BuscaSocio";
    }
    if (tipBusqueda == 2) {
        url = "../sPersonaNatural?action=BuscaPersonaNoSocio"
    }
    if (tipBusqueda == 4) {
        url = "../sPersonaJuridica?action=BuscaPersonaNoSocio";
    }
    if (tipBusqueda == 5) {
        url = "../sPersonaNatural?action=BuscaPersonaGeneralNoEmpleado";
    }
    ejecutarServletSelect(data, url);
}
;