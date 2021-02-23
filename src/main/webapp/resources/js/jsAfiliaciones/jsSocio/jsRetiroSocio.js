

$('#frmBuscarSocioNJ').submit(function (e) {
    e.preventDefault();

    if ($('#frmBuscarSocioNJ').valid())
    {
        buscarSocioPorNroDoc();
    }
});

function buscarSocioPorNroDoc() {
    var data = {'txtNroDocIden': $('#txtNroDocIden').val()};
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
        toast('error', data + '-' + status + '-' + er, 'Error2!');

    };
    fn_callmethod("../sSocio?action=BuscaSocio", data, success, error);
};



$('#btnLimpia').click(function (e) {
    e.preventDefault();
    limpiaData();
});
/*
$("#txtNroDocIden").keyup(function (e) {
    e.preventDefault();
    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
    if (key == 13) {
        if ($('#frmBuscarSocioNJ').valid())
        {
            buscarSocioPorNroDoc();
        }
    }
});
*/
function muestraData(objSocio)
{

    if (objSocio.Result === 'OK')
    {


        $('#txtNombreCompleto').val(objSocio.Socio.socio);
        $('#txtCuentaSocio').val(objSocio.Socio.idSocio);
        $('#txtEstadoSocio').val(objSocio.Socio.objEstadoSocio.estado);
        $('#txtTipoSocio').val(objSocio.Socio.tipoPersona);

        if (objSocio.Socio.tipoPersona == 'PERSONA NATURAL')
        {
            $('#txtIdPersona').val(objSocio.Socio.objPersonaNatural.idPersona);
        } else {


            $('#txtIdPersona').val(objSocio.Socio.objPersonaJuridica.idPersona);

        }


    } else if (objSocio.Result === 'error')
    {
        limpiaData();
    } else
    {
        limpiaData();
    }

}
;

function limpiaData()
{
    $('#txtNroDocIden').val("");
    $('#txtCuentaSocio').val("");
    $('#txtEstadoSocio').val("");
    $('#txtNombreCompleto').val("");
    $('#txtTipoSocio').val("");
    $('#txtIdPersona').val("");

}
;

$('#frmRetiroSocio').submit(function (e) {
    e.preventDefault();

    if ($('#frmRetiroSocio').valid()) {
        if ($('#txtIdPersona').val().length == 7) {

            var data = {
                'txtcuenta': $('#txtCuentaSocio').val(),
                'cboestado': $('#cboEstadoSocio').val()
            };

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
                toast('error', data + '-' + status + '-' + er, 'Error2!');
            };
            fn_callmethod("../sSocio?action=retirar", data, success, error);


        } else {
            toast('error', "Debe primero buscar un Socio", 'Error!');
        }


    }
});