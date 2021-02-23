
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
            abrirVentana(ReportSvr+"?%2fPyAfiliaciones%2fRptFichaSocio&rs:Command=Render&idpersona=" + res.idPersona);
            setTimeout("location.reload(true);", 5000);
        

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
}
;

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