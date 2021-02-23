limpiaData();

$('#frmBuscarSocioNJ').submit(function (e) {
    e.preventDefault();

    if ($('#frmBuscarSocioNJ').valid()) {

        buscarSocioPorNroDoc();

    }
});
$('#btnLimpiarData').click(function (e) {
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
 }else if(key==8){
 limpiaData();
 }
 });
 */
function muestraData(objSocio)
{

    if (objSocio.Result === 'OK')
    {

        if (objSocio.Socio.tipoPersona == 'PERSONA NATURAL') {
            if (objSocio.Socio.objEstadoSocio.codigo.charAt(0) == 0) {
                document.getElementById('divEdad').style.visibility = 'visible';
                document.getElementById('txtNombreCompleto').value = objSocio.Socio.socio;
                document.getElementById('txtCuentaSocio').value = objSocio.Socio.idSocio;
                document.getElementById('txtEstadoSocio').value = objSocio.Socio.objEstadoSocio.estado;
                document.getElementById('txtFecha').value = objSocio.Socio.fechaEstado;
                document.getElementById('txtEdad').value = objSocio.Socio.objPersonaNatural.edad;
                document.getElementById('txtTipoSocio').value = objSocio.Socio.tipoPersona;
                $('#txtIdPersona').val(objSocio.Socio.objPersonaNatural.idPersona);
                if (objSocio.Socio.objPersonaNatural.edad >= 65)
                {

                    document.getElementById('divFondoM').style.visibility = 'hidden';
                } else {
                    document.getElementById("txtFondoM").style.border = "1px solid white";
                }
                $("#divPagosAfiliacion").show();
            } else {
                toast('error', "Estado de Socio : " + objSocio.Socio.objEstadoSocio.estado, 'Error!');
                limpiaData();
            }


        } else {
            if (objSocio.Socio.objEstadoSocio.codigo.charAt(0) == 0) {

                document.getElementById('txtTipoSocio').value = objSocio.Socio.tipoPersona;
                document.getElementById('txtNombreCompleto').value = objSocio.Socio.socio;
                document.getElementById('txtCuentaSocio').value = objSocio.Socio.idSocio;
                document.getElementById('txtEstadoSocio').value = objSocio.Socio.objEstadoSocio.estado;
                document.getElementById('txtFecha').value = objSocio.Socio.fechaEstado;
                document.getElementById('divFondoM').style.visibility = 'hidden';
                document.getElementById("txtFondoM").style.border = "1px solid red";
                document.getElementById('divEdad').style.visibility = 'hidden';
                $('#txtIdPersona').val(objSocio.Socio.objPersonaJuridica.idPersona);
                $("#divPagosAfiliacion").show();
            } else {
                toast('error', "Estado de Socio : " + objSocio.Socio.objEstadoSocio.estado, 'Error!');
                limpiaData();
            }
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
$('#btnReingresar').click(function (event) {

    var data = {'txtCuentaSocio': $("#txtCuentaSocio").val()};
    var success = function (res) {

        if (res.Result === 'OK') {
            toast('success', 'Se reingreso al socio correctamente', 'Exito!');
            abrirVentana(ReportSvr + "?%2fPyAfiliaciones%2fRptFichaSocio&rs:Command=Render&idpersona=" + $('#txtIdPersona').val());
            limpiaData();
            $("#divPagosAfiliacion").hide();
            //setTimeout("location.reload(true);", 5000);


        } else if (res.Result === 'error')
        {
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er)
    {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sReingreso?action=RegistroRegingreso", data, success, error);
});
function limpiaData() {
    document.getElementById('txtNroDocIden').value = "";
    document.getElementById('txtCuentaSocio').value = "";
    document.getElementById('txtEstadoSocio').value = "";
    document.getElementById('txtEdad').value = "";
    document.getElementById('txtNombreCompleto').value = "";
    document.getElementById('txtFecha').value = "";
    document.getElementById('txtTipoSocio').value = "";
    $('#txtIdPersona').val("");
    document.getElementById('divFondoM').style.visibility = 'visible';
    document.getElementById("txtFondoM").style.border = "none";
    document.getElementById('divEdad').style.visibility = 'visible';
}
;
function getAge(dateString) {
    var today = new Date();
    var birthDate = new Date(dateString);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}
;


//*********************** PAUL

$('#btnModalFM').click(function (event) {
    $('#ModalFM').modal('show');
    if (document.getElementById('txtCuentaSocio').value == "")
    {
        $('#ModalFM').modal('show');
    } else if (document.getElementById('txtTipoSocio').value == "PERSONA NATURAL")
    {
        $('#ModalFMN').modal('show');
    } else
    {
        $('#ModalFMJ').modal('show');
    }


});
