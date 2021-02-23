$(document).ready(function () {
    $('#btnBuscaProvi').click(function (event) {
    ObetenerInfo();
     
    });
    
   $('#btnUpdateProvi').click(function (event) {
    var vOficinaProviExt = $('#cboOficinaProvExt').val();
    var vTipMonedaProviExt = $('#cboTipMonedaProviExt').val();
    var vNroDocProviExt = $('#txtNroDocExt').val();
    var vIdDocumentoProvi = $('#cboIdDocumentoProvi').val();

    var success = function (res) {
        if (res.Result === 'OK') {
         toast('success', 'La Homologacion de Montos Se Ha Realizado Correctamente', 'Exito!');
         document.getElementById('btnUpdateProvi').disabled = true;
         ObetenerInfo();
        }
    };
    var error = function () {
        toast('error', 'Error al Homologar los Montos', 'Error!');
    };
    fn_callmethod("../sProvisionExtorno?action=updateProvi", {NroDocProviExt: vNroDocProviExt, OficinaProviExt: vOficinaProviExt, TipMonedaProviExt: vTipMonedaProviExt, IdDocumentoProvi: vIdDocumentoProvi}, success, error);
        });
    
});

$(document).ready(function () {
    $('#btnNuevo').click(function (event) {
        Botones(0);
    });
});

function ObetenerInfo() {

    var vOficinaProviExt = $('#cboOficinaProvExt').val();
    var vTipMonedaProviExt = $('#cboTipMonedaProviExt').val();
    var vNroDocProviExt = $('#txtNroDocExt').val();
    var vIdDocumentoProvi = $('#cboIdDocumentoProvi').val();

    var success = function (res) {
        if (res.Info.length > 1) {
            $("#lstDatos").html("");
            var div1 = "<div class='ibox-title'><h5>Informacion del Pagare</h5></div>";
            var div2 = "<div class='ibox-content'><table class='table table-bordered' id='tblPagare'><thead><tr><th>Cuota</th><th>Monto Provision</th><th>Monto Extorno</th><th>Diferencia</th></tr></thead><tbody>";
            var div3 = "";
            $.each(res.Info, function (key, value) {
                div3 = div3 + "<tr><td>" + value.cuota + "</td><td>" + value.montoProvi + "</td><td>" + value.montoProviExt + "</td><td>" + value.diferencia + "</td></tr>" ;
            });
            var div4 = "</tbody></table></div>";
            $("#lstDatos").append(div1 + div2 + div3 + div4);
            Botones(1);
        }
        else
        {
             toast('error', 'La Informacion no corresponde al Documento de Extorno', 'Error!');
        }
    };
    var error = function () {
        toast('error', 'La Informacion no corresponde al Documento de Extorno', 'Error!');
    };
    fn_callmethod("../sProvisionExtorno?action=obetenerInfo", {OficinaProviExt: vOficinaProviExt, TipMonedaProviExt: vTipMonedaProviExt, NroDocProviExt: vNroDocProviExt, IdDocumentoProv: vIdDocumentoProvi}, success, error);
}

function Botones(valor)
{
    if (valor == 1)
    {
        document.getElementById('cboOficinaProvExt').disabled = true;
        document.getElementById('cboTipMonedaProviExt').disabled = true;
        document.getElementById('txtNroDocExt').disabled = true;
        document.getElementById('cboIdDocumentoProvi').disabled = true;
        document.getElementById('btnUpdateProvi').style.visibility = 'visible';
        
    }
    else
    {
        document.getElementById('cboOficinaProvExt').disabled = false;
        document.getElementById('cboTipMonedaProviExt').disabled = false;
        document.getElementById('txtNroDocExt').disabled = false;
        document.getElementById('cboIdDocumentoProvi').disabled = false;
        document.getElementById('btnUpdateProvi').style.visibility = 'hidden';
        $("#lstDatos").html("");

        document.getElementById('cboOficinaProvExt').selectedIndex = "0";
        document.getElementById('cboTipMonedaProviExt').selectedIndex = "0";
        document.getElementById('cboIdDocumentoProvi').selectedIndex = "0";
        document.getElementById('txtNroDocExt').value = "";
        
    }
    
}