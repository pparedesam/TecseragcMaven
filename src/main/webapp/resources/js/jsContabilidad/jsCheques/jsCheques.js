var idPersona = '';
var chequeFecha = new Date();
var busFecIni = new Date();
var busFecFin = new Date();
var listaDetalle = [];
var objCheque = new Object();
$(document).ready(function () {
    
    $('#txtBusFecInicio').datepicker('update', busFecIni);
    $('#txtBusFecFin').datepicker('update', busFecFin);
    $('#txtChequeFecha').datepicker('update', chequeFecha);
    

    listarChequesDelDia();
    listarTiposMoneda();
    
    listarTablaTitular('') 
    
});

$("#btnBuscarCheques").click(function(){
    
    buscarCheques();
})

$("#btnAnularCheque").click(function(){
    var motivo = $("#txtModalAnularMotivo").val();
    if(motivo.length<4){
        toast('warning','Por favor ingrese un motivo','MOTIVO');
    }else{
        bootbox.confirm("\u00BFEst\u00E1 seguro que desea anular el cheque\u003F", function (result) {
            if (result) {
                anularCheque(objCheque.idDoc,objCheque.nroDoc,objCheque.idTipMoneda,objCheque.idOficina,motivo);
            }
        });
        
    }
    
})

$("#cboBusqueda").change(function () {
    var tipoBusqueda = $("#cboBusqueda").val();
    if (tipoBusqueda == '0') {
        $("#busquedaNroCheque").css("display", "block");
        $("#busquedaNombres").css("display", "none");
        $("#busquedaFechas").css("display", "none");
    } else if (tipoBusqueda == '1') {
        $("#busquedaNroCheque").css("display", "block");
        $("#busquedaNombres").css("display", "block");
        $("#busquedaFechas").css("display", "none");
    } else {
        $("#busquedaNroCheque").css("display", "none");
        $("#busquedaNombres").css("display", "none");
        $("#busquedaFechas").css("display", "block");
    }
})

$("#btnTitularBuscar").click(function () {
    var tipoBusqueda =$('#cboModalEmisorBusqueda').val();
    var criterio= $("#txtModalTitularCriterio").val();
    buscarPersonaNJ(tipoBusqueda,criterio)
})

$("#btnRegistrarCheque").click(function () {
    var fecha = $("#txtChequeFecha").val();
    var tipomoneda = $("#cboChequeMoneda").val();
    var nrocheque = $("#txtChequeNro").val();
    var idBanco = $("#cboChequeBanco").val();
    
    var idCtaBanco = $("#cboChequeCuenta").val();
    var ctaBanco = $('#cboChequeCuenta').find('option:selected').text();
    var glosa = $("#txtChequeDetalle").val();
    var monto = $("#txtChequeMonto").val();
    var glosavariable = nrocheque+ " " + $("#txtTitNombres").val();
    
    
    if(idPersona.trim()==''){
        toast('warning','SELECCIONE UNA PERSONA','PERSONA')
    }else if(nrocheque.trim().length<8){
        toast('warning','INGRESE UN NUMERO DE CHEQUE DE 8 CARACTERES','NRO CHEQUE')
    }else if(monto<8){
        toast('warning','INGRESE UN MONTO MAYOR A 0','MONTO')
    }else if(glosa.trim().length==0){
        toast('warning','INGRESE UN DETALLE','DETALLE')
    }else{
         bootbox.confirm("\u00BFEst\u00e1 seguro que desea registrar el cheque \u003F", function (result) {
                if (result) {
                    registrarCheque(tipomoneda,fecha,glosa,glosavariable,nrocheque,idBanco,idCtaBanco,ctaBanco,monto);
                }
            });
    }
    
})

$('#tabCheques a').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
});

function listarChequesDelDia() {

    var success = function (res) {

        if (res.Result == 'OK') {
            var listaCheques = res.listaCheques;
            listarTablaCheques(listaCheques);
        } else {
            toast('error', res.Message, 'Error');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sCheque?action=listarChequesDelDia", '', success, error);

}

function buscarCheques() {
    var nroCheque = $("#txtBusNroCheque").val();
    var fecIni = $("#txtBusFecInicio").val();
    var fecFin = $("#txtBusFecFin").val();
    
    if($("#cboBusqueda").val()=='1'){
        fecIni='';
        fecFin='';
    }else{
        nroCheque='';
    }
    
    var data = {
            'nroCheque': nroCheque, 
            'fecIni': fecIni,
            'fecFin': fecFin,
        };

    var success = function (res) {

        if (res.Result == 'OK') {
            var listaCheques = res.listaCheques;
            listarTablaCheques(listaCheques);
        } else {
            toast('error', res.Message, 'Error');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sCheque?action=buscarCheques", data, success, error);

}

function anularCheque(idDoc,nroDoc,tipMoneda,idOficina,motivo) {
    
    var data = {
            'idDoc': idDoc, 
            'nroDoc': nroDoc,
            'tipMoneda': tipMoneda,
            'idOficina': idOficina,
            'motivo': motivo
        };

    var success = function (res) {

        if (res.Result == 'OK') {
            toast('success', "SE ANULO EL CHEQUE DE MANERA EXITOSA", 'Exito!');
            $("#modalChequeAnular").modal("hide");
            buscarCheques();
        } else {
            toast('error', res.Message, 'Error');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sCheque?action=anularCheque", data, success, error);

}

function buscarPersonaNJ(tipoBusqueda,criterio){

        var data = {
            'TipoBusqueda': tipoBusqueda, 
            'txtPersona': criterio};

        var success = function (res) {

            if (res.Result == 'OK') {
                var listaPersona = res.listaPersona
                listarTablaTitular(listaPersona)
                
            } else if (res.Result === 'error')
            {
                toast('error', res.Message, 'Error!');
            }

        };

        var error = function (res) {
            toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
        };
    
    fn_callmethod("../sPersona?action=buscarPersonaJN", data, success, error);


}

function registrarCheque(tipomoneda,fecha,glosa,glosavariable,nrocheque,idBanco,idCtaBanco,ctaBanco,monto){
    var data = {
        'idpersona': idPersona,
        'tipomoneda': tipomoneda,
        'fecha': fecha,
        'glosa': glosa,
        'glosavariable': glosavariable,
        'idtipctabanco': idCtaBanco,
        'ctabanco': ctaBanco,
        'idpersonabco': idBanco,
        'nrocheque': nrocheque,
        'monto': monto

    };
    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.Message === 'OK') {
                toast('success', "SE REGISTRO EL CHEQUE DE MANERA EXITOSA", 'Exito!');
                setTimeout("location.reload(true);", 5000);
            
            }
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    
    fn_callmethod("../sCheque?action=registrarCheque", data, success, error);
}

function listarBancos() {
    var success = function (res) {

        if (res.Result == 'OK') {
            var listaBancos = res.listaBancos;
            listarComboBancos(listaBancos);
        } else {
            toast('error', res.Message, 'Error');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sBanco?action=listarBancos", '', success, error);
}

function listarTiposMoneda() {
    var success = function (res) {

        if (res.Result == 'OK') {
            var listaTipoMoneda = res.listaTipoMonedas;
            listarComboTipoMonedas(listaTipoMoneda);
            listarBancos();
        } else {
            toast('error', res.Message, 'Error');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sMoneda?action=listarTipoMoneda", '', success, error);
}

function listarCtasBanco(idBanco,tipMoneda) {
    var data = {'cboBanco': idBanco, 'cboTipoMoneda': tipMoneda};
    
    var success = function (res) {
        var listaCtasBanco=res.listaCtaBanco;
        listarComboCtasBanco(listaCtasBanco);
        
    };
    var error = function () { };

    fn_callmethod("../sCtaBanco?action=obtenerCtaBanco", data, success, error);
}


function listarComboTipoMonedas(listaTipoMonedas) {
    $("#cboChequeMoneda").empty();
    $.each(listaTipoMonedas, function (i, item) {
        $('#cboChequeMoneda').append("<option value='" + item.id + "'>" + item.descripcion + "</option>");
    });
}

function listarComboBancos(listaBancos) {
    $("#cboChequeBanco").empty();
    $.each(listaBancos, function (i, item) {
        $('#cboChequeBanco').append("<option value='" + item.objPersonaBanco.idPersona + "'>" + item.nombreBanco + "</option>");
    });
    
    listarCtasBanco($("#cboChequeBanco").val(),$("#cboChequeMoneda").val())
}

function listarComboCtasBanco(listaCtasBanco) {
    $("#cboChequeCuenta").empty();
    $.each(listaCtasBanco, function (i, item) {
        $('#cboChequeCuenta').append("<option value='" + item.idTipCtaBanco + "'>" + item.ctaBanco + "</option>");
    });
  
}


$("#txtBusFecInicio").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$("#txtBusFecFin").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$("#txtChequeFecha").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$('#txtBusFecInicio').datepicker().on('changeDate', function (e) {
    if ($('#txtBusFecInicio').val() == '') {
        $('#txtBusFecInicio').datepicker('update', busFecIni);
    } else {
        busFecIni = new Date($('#txtBusFecInicio').datepicker('getDate'));
    }
})

$('#txtBusFecFin').datepicker().on('changeDate', function (e) {
    if ($('#txtBusFecFin').val() == '') {
        $('#txtBusFecFin').datepicker('update', busFecFin);
    } else {
        busFecFin = new Date($('#txtBusFecFin').datepicker('getDate'));
    }
})

$('#txtChequeFecha').datepicker().on('changeDate', function (e) {
    if ($('#txtChequeFecha').val() == '') {
        $('#txtChequeFecha').datepicker('update', chequeFecha);
        //$('#txtAdelantoMesDcto').datepicker('update', AdelantoFechaDcto);
    } else {
        chequeFecha = new Date($('#txtChequeFecha').datepicker('getDate'));
    }
})


//IMPRESION
//==================================================

JSPM.JSPrintManager.auto_reconnect = true;
JSPM.JSPrintManager.start();
JSPM.JSPrintManager.WS.onStatusChanged = function () {
    if (jspmWSStatus()) {
        //get client installed printers
        JSPM.JSPrintManager.getPrinters().then(function (myPrinters) {
            var options = '';
            for (var i = 0; i < myPrinters.length; i++) {
                options += '<option>' + myPrinters[i] + '</option>';
            }
            $('#cboImpresoras').html(options);
        });
    }
};

//Check JSPM WebSocket status
function jspmWSStatus() {
    if (JSPM.JSPrintManager.websocket_status == JSPM.WSStatus.Open)
        return true;
    else if (JSPM.JSPrintManager.websocket_status == JSPM.WSStatus.Closed) {
        alert('JSPrintManager (JSPM) is not installed or not running! Download JSPM Client App from https://neodynamic.com/downloads/jspm');
        return false;
    } else if (JSPM.JSPrintManager.websocket_status == JSPM.WSStatus.Blocked) {
        alert('JSPM has blocked this website!');
        return false;
    }
}

//Do printing...
function print(o) {

    JSPM.JSPrintManager.auto_reconnect = true;
    JSPM.JSPrintManager.start();
    JSPM.JSPrintManager.WS.onStatusChanged = function () {
        if (JSPM.JSPrintManager.websocket_status == JSPM.WSStatus.Open) {
            var espacio = decodeURIComponent(" ");
            var cpj = new JSPM.ClientPrintJob();
            //cpj.clientPrinter = new JSPM.DefaultPrinter();
            cpj.clientPrinter = new JSPM.InstalledPrinter($('#cboImpresoras').val());
            //var my_file1 = new JSPM.PrintFilePDF('/files/LoremIpsum.pdf', JSPM.FileSourceType.URL, 'MyFile.pdf', 1);
            //var texto = toUTF8Array("hola")
           
            var espacioFecha = "                                                     ";
            var espacioMonto = "                               ";
            var chequeTitular = "\n\n\n\n"+objCheque.persona;
            
            var tmpMl=objCheque.montoLetras.split(" ");
            var montoParte1='\n';
            var montoParte2='\n';
            tmpMl.forEach(function(item,i){
                if(i<=8){
                    montoParte1=montoParte1+item.toString()+" ";
                }else{
                    montoParte2=montoParte2+item.toString()+" ";
                }
                    
            })
            var chequeMontoLetras = montoParte1+montoParte2;
            //var chequeMontoLetras="\n"+;
            
            var textoCheque = espacioFecha+objCheque.dia + "   " + objCheque.mes +"  "+objCheque.anio+espacioMonto+objCheque.monto+chequeTitular+chequeMontoLetras;
            textoCheque.replace(" ", "   ");
            textoCheque.replace('ñ','\u00F1');
            var myblob = new Blob([textoCheque], {
                type: 'text/plain'
            });
            var my_file2 = new JSPM.PrintFileTXT(myblob, 'Cheque.txt', 1);

            cpj.files.push(my_file2);
            cpj.sendToClient();
        }
    };


}

function toUTF8Array(str) {
    var utf8 = [];
    for (var i = 0; i < str.length; i++) {
        var charcode = str.charCodeAt(i);
        if (charcode < 0x80)
            utf8.push(charcode);
        else if (charcode < 0x800) {
            utf8.push(0xc0 | (charcode >> 6),
                    0x80 | (charcode & 0x3f));
        } else if (charcode < 0xd800 || charcode >= 0xe000) {
            utf8.push(0xe0 | (charcode >> 12),
                    0x80 | ((charcode >> 6) & 0x3f),
                    0x80 | (charcode & 0x3f));
        }
        // surrogate pair
        else {
            i++;
            // UTF-16 encodes 0x10000-0x10FFFF by
            // subtracting 0x10000 and splitting the
            // 20 bits of 0x0-0xFFFFF into two halves
            charcode = 0x10000 + (((charcode & 0x3ff) << 10)
                    | (str.charCodeAt(i) & 0x3ff));
            utf8.push(0xf0 | (charcode >> 18),
                    0x80 | ((charcode >> 12) & 0x3f),
                    0x80 | ((charcode >> 6) & 0x3f),
                    0x80 | (charcode & 0x3f));
        }
    }
    return utf8;
}



//FUNCIONES SOLO NUMERO
function numeroDosDecimales(evt, input) {
    // Backspace = 8, Enter = 13, ‘0′ = 48, ‘9′ = 57, ‘.’ = 46, ‘-’ = 43
    var key = window.Event ? evt.which : evt.keyCode;
    var chark = String.fromCharCode(key);
    var tempValue = input.value + chark;
    if (key >= 48 && key <= 57) {

        return true;

    } else {
        if (key == 8 || key == 13 || key == 0) {
            return true;
        } else if (key == 46) {
            if (filter(tempValue) === false) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
function filter(__val__) {
    var preg = /^([0-9]+\.?[0-9]{0,2})$/;
    if (preg.test(__val__) === true) {
        return true;
    } else {
        return false;
    }

}


function format(input)
{   
    
    
    
    if(input.value==''){
        var numero = 0;
    }else{
        var numero = input.value;
    }
    
    var num = parseFloat(numero);
    
    input.value = num.toFixed(2);
}

//Solo numero
function soloNumero(e) {
    var key = window.Event ? e.which : e.keyCode
    if(e.value=="" || e.value=="0"){
        e.value='1';
    }else{
        e.value = parseInt(e.value,10);
        return ((key >= 48 && key <= 57));
    }
    
}