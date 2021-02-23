/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).on("ready", inicio);


function inicio()
{
    $("span.help-block").hide();
    $("#btnRegSocio").click(function ()
    {

        if (validarCampos() == true)
        {

            var txtidpersona = document.getElementById("txtidpersona");

            if (txtidpersona == null)
            {

            } else {
                var data = {
                    'txtidpersona': document.getElementById("txtidpersona").value,
                    'txtAporteInicial': document.getElementById("txtAporteInicial").value,
                    'txtFondoMortuorio': document.getElementById("txtFondoMortuorio").value,
                    'txtDerechoInscripcion': document.getElementById("txtDerechoInscripcion").value,
                    'cboTipoTrabajador': document.getElementById("cboTipoTrabajador").value,
                    'txtedad': document.getElementById("txtedad").value
                };
            }
            ejecutarservletRegistrarSocio(data);
        } else
        {
            toast('error', 'Los Campos no estan validados', 'Error!');
            $("#datosSocio").html("");
        }
    })
            ;

}

function validarCampos() {
    return true;
}




function validarSocio()
{
    var textbox = document.getElementById("txtcuenta");
    if (textbox == null)
    {
        toast('error', 'Se Necesita un socio', 'Error!');

    } else
    {

        var data = {
            'txtcuenta': document.getElementById("txtcuenta").value,
            'cboestado': document.getElementById("cboEstadoSocio").value
        };

        ejecutarservletRetirarSocio(data);


    }



};