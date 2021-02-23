
$(document).ready(function () {
    $('#btnBuscarPersona').click(function (event) {
        var dni = $('#txtbuscarPersona').val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('../sSocio', {
            Dni: dni,
            btnBuscarPersona: 1
        }, function (responseText) {
            $('#tabla').html(responseText);
        });
    });
});
$(document).ready(function () {
    $('#btnRegSocio').click(function (event) {
        var vAporteInicial = $('#txtAporteInicial').val();
        var vDerechoInscripcion = $('#txtDerechoInscripcion').val();
        var vFondoMortuorio = $('#txtFondoMortuorio').val();
        var vTipDocumento = $('#cboTipDocumento').val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('../sSocio', {
            AporteInicial: vAporteInicial,
            DerechoInscripcion: vDerechoInscripcion,
            FondoMortuorio: vFondoMortuorio,
            TipDocumento: vTipDocumento,
            btnRegSocio: 1
        }, function (responseText) {
            $('#tablaRegistrarSocio').html(responseText);
        });
    });
});
$(document).ready(function () {
    $('#btnBuscarJuridico').click(function (event) {
        var Ruc = $('#txtBuscarJuridico').val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('../sSocio', {
            Ruc: Ruc,
            btnBuscarJuridico: 1
        }, function (responseText) {
            $('#Persona').html(responseText);
        });
    });
});
$(document).ready(function () {
    $('#btnRegistrarSocioJuridico').click(function (event) {
        var vAporteInicial = $('#txtAporteInicial').val();
        var vDerechoInscripcion = $('#txtDerechoInscripcion').val();
        var vTipDocumento = $('#cboTipDocumento').val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('../sSocio', {
            AporteInicial: vAporteInicial,
            DerechoInscripcion: vDerechoInscripcion,
            TipDocumento: vTipDocumento,
            btnRegistrarSocioJuridico: 1
        }, function (responseText) {
            //$('#tablaRegistrarSocio').html(responseText);
        });
    });
});
function  ejecutarservletBuscarSocio(tipBusqueda) {
    
    var data = {'txtNroDocIden': $('#txtNroDocIden').val()};

    var url;
    if (tipBusqueda == 1) {

        url = "../sSocio?action=BuscaSocio";
    }
    if (tipBusqueda == 2) {
        url = "../sPersonaNatural?action=BuscaPersonaNoSocio";
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

function ejecutarservletRetirarSocio(data)
{
    var url = "../sSocio?action=retirar";

    ejecutarServletUpdate(data, url);
}
;
//////////////////////////////////jsSocio (carpeta)////////////////////////////////////////////////////
$('#frmBuscarSocio').submit(function (event) {
    event.preventDefault();
    if ($('#frmBuscarSocio').valid())
    {
        buscarSocio();
    }
});

$("#txtNroDocIden").keyup(function (e) {
    e.preventDefault();
    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
    if (key == 13) {
        if ($('#frmBuscarSocio').valid())
        {
            ejecutarServletBuscarsocio();
        }
    }
    if (key == 8)
    {
        LimpiarData();
    }
});

function buscarSocio() {
    var tipo = $('input:radio[name=optradio]:checked').val();
    var url;
    var data = {'txtNroDocIden': $('#txtNroDocIden').val(), 'optTipo': $('input:radio[name=optradio]:checked').val()};

    if (tipo == 'N') {
        url = "../sPersonaNatural?action=BuscaPersonaNoSocioNoEmpleado";
    }
    if (tipo == 'J') {
        url = "../sPersonaJuridica?action=BuscaPersonaNoSocioNoEmpleado";
    }

    var success = function (res) {
        if (res.Result == 'OK') {
            if (tipo == 'N') {
                mostrarPersonaN(res);
            } else if (tipo == 'J') {
                mostrarPersonaJ(res);
            }

        } else if (res.Result == 'error') {
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er)
    {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod(url, data, success, error);
};

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

$('#frmRegistrarsocio').submit(function (event) {

    
    event.preventDefault();
    var validator = $('#frmRegistrarsocio').valid();
    var url;
    var idpersona = $('#txtidpersona').val();
    

    if ((idpersona.length) !== 0) {
        if (validator) {
            var data = {'cboTipoTrabajador': $('#cboTipoTrabajador').val(),
                'txtidpersona': $("#txtidpersona").val()};
           
            if ($('#txtTipoPersonaJ').val() == 'PERSONA JURIDICA') {
                registrarSocioJuridico("../sSocioJuridico?action=RegSocioJuridico", data);
            } else if ($('#txttipopersona').val() == 'PERSONA NATURAL')
            {
                
                registrarSocioNatural("../sSocio?action=registrarSocioNatural", data);
            }
        } else {
            toast('error', 'Debe Seleccionar una Tipo Trabajador', 'ERROR');
        }
    } else {
        toast('error', 'Debe Seleccionar una Persona', 'ERROR');
    }
   
});

/////////////////////////////////////////////////////////////

$(".chosen-select").chosen('destroy');
$(".chosen-select").chosen({width: "100%"});


/**********************************************************/

function mostrarPersonaN(res)
{   
   

    $('#txtnombres').val(res.Persona.nombres);
    $('#txtapellidos').val(res.Persona.apPaterno + " " + res.Persona.apMaterno);
    $('#txttipopersona').val('PERSONA NATURAL');
    $('#txtedad').val(res.Persona.edad);
    $('#txtidpersona').val(res.Persona.idPersona);
   
    $("#datosSocioNatural").show();
    
    $("#cont-Aport").show();
    
    $(".chosen-select").chosen('destroy');
    $(".chosen-select").chosen({width: "100%"});


    if (res.Persona.edad > 65)
    {
       
         $("#divFondoM").hide();
    } else
    {
        $("#divFondoM").show();
    }
    
}

function registrarSocioNatural(url,data)
{
    var success = function (res) {
        if (res.Result === 'OK')
        {
            toast('success', 'Se registr\u00F3 correctamente', 'Exito!');
            LimpiarData();
            setTimeout("location.reload(true);", 5000);
            abrirVentana("http://190.41.141.164:808/Reportserver?%2fPyAfiliaciones%2fRptFichaSocio&rs:Command=Render&idpersona=" + res.IdPersona);

        } else if (res.Result === "error")
        {
            toast('error', res.Message, 'Error');
        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (res) {

        toast('error', res.Message, 'Error');
    };
    
     fn_callmethod(url, data, success, error);
};

function LimpiarData() {

    $('#txtnombres').val('');
    $('#txtapellidos').val('');
    $('#txttipopersona').val('');
    $('#txtedad').val('');
    $('#txtidpersona').val('');

    $("#datosSocioNatural").hide();

    $('#txtnroruc').val('');
    $('#txtrazonSocial').val('');
    $('#txtTipoPersonaJ').val('');   
    $("#datosSocioJuridico").hide();
    $("#cont-Aport").hide();
    
}
;