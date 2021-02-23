fn_llenarTipCta();
var oTableEc;
oTableEc = fn_iniciarDT(oTableEc, "tblSocios");

oTableEc.on("click", ".show", function () {
    fn_Show(this);
});


oTableEc.on("click", ".show_ec", function () {
    fn_Show_ec(this);
});

$('#cboMoneda').on('change', function () {
    
fn_llenarTipCta();

});

var muestraInformacionSocio = function (listSocios) {
    $("#divTablaPersonas").css("display", "block");
    $('#tblSocios').DataTable({
        destroy: true,
        "columnDefs": [
            {"className": "text-center", "targets": "_all"}
        ],
        "aaData": listSocios,
        "columns": [
            {"data": "idSocio"},
            {"data": "objPersona.nroDocumento"},
            {"data": "objPersona.nombres"},
            {
                "data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html(
                            "<a class='btn btn-Lapislazuli btn-circle show_ec' title='ver Cuentas'><i class='fa fa-eye'></i></a>");
                }}
        ],
        createdRow: function (row, data, indice) {
            $(row).find("td:eq(3)").attr('data-id', data.objPersona.idPersona);
        }
    });
};

function fn_llenarTipCta (){
    
    var data = {'tipMoneda':  $('#cboMoneda').val()};    
    $('#cboTipCuenta').empty();
    var success = function (data) {       
        
        $.each(data.lstTipCta, function (i, item) {
            $("#cboTipCuenta").append("<option value='" + item.IdTipCta + "'>" + item.IdTipCta  + "</option>");

        });
        $("#cboTipCuenta").val('P1').trigger('change.select2');
        
    };
    var error = function () {
    };

    fn_callmethod("../sTipoCuenta?action=obtenerCuentasPlazoFijo", data, success, error);
};

var fn_Show_ec = function (elem) {
    var par = $(elem).parent().parent();
    var tdCuenta = par.children("td:nth-child(1)");
    var tdNroDoc = par.children("td:nth-child(2)");
    var tdNnombre = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    $("#txtNumDocumento").val(tdNroDoc.html());
    $("#txtNroCuenta").val(tdCuenta.html());
    $("#txtSocio").val(tdNnombre.html());
    document.getElementById('txtNumDocumento').setAttribute("data-idPersona", idPersona);
    $("#divTablaPersonas").css("display", "none");
    $("#divInformacionSocio").show();
};

$("#txtNumCuenta").on('keyup', function (e) {
    e.preventDefault();
    if (e.keyCode == 13) {
        e.preventDefault();        
        obtenerMontoInteresAdelantado();
    }
});
$('input[type=radio][name=optradio]').change(function() {
        obtenerMontoInteresAdelantado();
    });
    
function  obtenerMontoInteresAdelantado(){
    
    var data={'idMoneda':$('#cboMoneda').val(),'idTipCta':$('#cboTipCuenta').val(),'idOficina':$('#cboOficina').val(),
          'numCuenta':$('#txtNumCuenta').val(),'idSocio':$('#txtNroCuenta').val(),'tipoplazo':$('input[name="optradio"]:checked').val()};
      
          //limpiar combo de Interes adelantado
    var success = function (res) {
          if (res.Result == 'OK') {
              $('#txtmonto').val(res.Monto);
           document.getElementById("btnRegistrarPlazoInteresAdelantado").disabled = false;              
          }else if (res.Result == 'error') {
            toast('error', res.Message, 'ERROR!');        
          }
 
    };
    var error = function () {};

    fn_callmethod("../sPlazoFijo?action=obtenerMontoPlazoFijoInteresAdelantado",data, success, error);
}


$("#btnRegistrarPlazoInteresAdelantado").click(function () {

    var data = {'idMoneda': $('#cboMoneda').val(),'montoIntAdelantado': $('#txtmonto').val(), 'idTipCta': $('#cboTipCuenta').val(), 'idOficina': $('#cboOficina').val(),
        'numCuenta': $('#txtNumCuenta').val(), 'idPersona':  document.getElementById('txtNumDocumento').getAttribute("data-idPersona")};

    //limpiar combo de Interes adelantado
    var success = function (res) {
        if (res.Result == 'OK') {
          if(res.resultado=='ins_ok'){
              abrirVentana(ReportSvr+'?%2fPyAfiliaciones%2frptSolicitudPFEgresoIntAdel&rs:Command=Render&'
                        +'IdOficina='+data.idOficina
                        +'&IdTipCta='+data.idTipCta
                        +'&TipMoneda='+data.idMoneda
                        +'&NumCuenta='+data.numCuenta
                      );
              limpiar();
          }
          else {
              toast('error',res.resultado, 'ERROR!');            
          }
        } else if (res.Result == 'error') {
            toast('error', res.Message, 'ERROR!');
        }

    };
    var error = function () {};

    fn_callmethod("../sPlazoFijo?action=registrarPlazoInteresAdelantado", data, success, error);
});


var limpiar =  function () {
    
    $("#txtNumDocumento").val('');
    $("#txtNroCuenta").val('');
    $("#txtSocio").val('');
    document.getElementById('txtNumDocumento').setAttribute("data-idPersona",'');
    $('#txtNumCuenta').val('');
    $('#txtmonto').val('');
    document.getElementById("btnRegistrarPlazoInteresAdelantado").disabled = true;              
    $("#divTablaPersonas").css("display", "none");
    $("#divInformacionSocio").css("display", "none");
};