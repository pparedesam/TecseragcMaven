var objEmisor = new Object();
var objItem = new Object();
var listaItems = [];
var fechaIni= new Date();
var fechaFin= new Date();
var fechaDocumento= new Date();

$(document).ready(function () {
    
    //FECHAS
    $('#txtBusFecInicio').datepicker('update', fechaIni);
    $('#txtBusFecFin').datepicker('update', fechaFin);
    $('#txtFechaDoc').datepicker('update', fechaDocumento);


    //LISTAR TABLAS
    listarTablaDocumentos('');
    listarTablaModalEmisor('');
    listarTablaDetalle('');
    listarUnidadMedida();
    listarTipoComprobanteCompra('');
});

$("#btnBuscarFactura").click(function () {
    var criterio=$("#cboBusqueda").val();
    var nroDoc= '';
    var fecIni = '';
    var fecFin ='';

    if ( criterio=== "1") {
        nroDoc = $("#txtBusquedaNroDocumento").val();
        fecIni = '';
        fecFin = '';
    } else {
        nroDoc = '';
        fecIni = $("#txtBusFecInicio").val();
        fecFin = $("#txtBusFecFin").val();
        
    }
    buscarDocumentos(nroDoc, fecIni, fecFin);
    
});

$("#btnEmisor").click(function () {
    var listaPersonaJuridicas = [];
    listarTablaModalEmisor(listaPersonaJuridicas);
})

$("#btnRegistrarFactura").click(function(){
    if(objEmisor.idPersona=='' || objEmisor.idPersona==undefined){
        toast('warning','SELECCIONES UN EMISOR','EMISOR');
    }else if($("#txtNroComprobante").val()==''){
        toast('warning','INGRESE UN NRO DE FACTURA','NRO FACTURA');
    }else if(listaItems.length<=0){
        toast('warning','INGRESE UN ITEM EN EL DETALLE','DETALLE');
    }else{
        
        bootbox.confirm("\u00BFEst\u00E1 seguro que desea registrar el documento de compra\u003F", function (result) {
            if (result) {
                var numDoc=$("#txtNroComprobante").val();
                var idEmisor=objEmisor.idPersona;
                var fecDoc=$("#txtFechaDoc").val();
                var tipoComprobante=$("#cboTipoComprobante").val();
                var desComprobante=$("#cboTipoComprobante").find('option:selected').text();
                var tipoMoneda=$("#cboMoneda").val();

                listaItems.forEach(function(elemento,index){
                    listaItems[index].idDetGastoSunat=$("#cboTipoComprobante").val();
                })

                registrarDocumento(numDoc,idEmisor,fecDoc,tipoComprobante,tipoMoneda,desComprobante)
            }
        });
        
        
    }
    
    
})

$("#btnModalClienteBuscar").click(function () {
    var TipoBusqueda = $("#cboModalClienteBusqueda").val();
    var txtPersonaJuridica = $("#txtModalClienteCriterio").val();
    buscarEmisores(TipoBusqueda, txtPersonaJuridica);
})

$("#btnModalDetalleAgregar").click(function () {
    var tmpObjItem = new Object();
    tmpObjItem.glosaVariable=$("#cboModalDetalleDescripcion").val();
    //tmpObjItem.idDetGastoSunat=
    tmpObjItem.precioUnitario=objItem.pUnit;
    var existe = '0';
    listaItems.forEach(function(elemento,index){
        if (elemento.glosaVariable.trim()==tmpObjItem.glosaVariable.trim()){
            existe = '1';
        }
    })
    if ( tmpObjItem.glosaVariable == '' || tmpObjItem.glosaVariable.length<5){
        toast('warning','Ingrese una descripcion correcta','DESCRIPCION');
    }else if ( objItem.importe <= 0){
        toast('warning','Ingrese un precio adecuado','PRECIO');
    }else if ( existe == '1'){
        toast('warning','Ya hay un item registrado con esa descripcion','DUPLICADO')
    }else{
        //tmpObjItem.unidadMedidad=$('#cboModalDetalleUniMedida option:selected').text();
        tmpObjItem.unidadMedidad=$("#cboModalDetalleUniMedida").val();
        tmpObjItem.cantidad=objItem.cant;
        tmpObjItem.valorUnitario=objItem.vUnit;
        tmpObjItem.igv=objItem.igv;
        tmpObjItem.valorUnitarioTotal=objItem.valorUnitarioTotal;
        tmpObjItem.igvTotal=objItem.igvTotal;
        tmpObjItem.monto=objItem.importe;
        tmpObjItem.idDetGastoSunat='';
        listaItems.push(tmpObjItem);
        listarTablaDetalle(listaItems);
       
        calcularTotales();
        $("#modalDetalle").modal('hide');
    }
})

$('#tabFacturas a').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
});

$("#txtModalDetalleCant").change(function (e) {
    calcularItemMontos(2);
});

$("#txtModalDetalleVUnit").change(function (e) {
    calcularItemMontos(1);
});

$("#txtModalDetallePUnit").change(function (e) {
    calcularItemMontos(2);
});

function buscarDocumentos(nroDoc, fecIni, fecFin) {
    var data = {
        'tipo': '0003',
        'nroDoc': nroDoc,
        'fecIni': fecIni,
        'fecFin': fecFin
    }

    var success = function (res) {
        if (res.Result === 'OK') {
            var listaDocumentosCompraVenta = res.listaDocumentosCompraVenta;
            listarTablaDocumentos(listaDocumentosCompraVenta);
        } else {
            toast('error', res.mensaje, 'Error');
        }
    };

    var error = function (data, status, er) {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sDocCompra?action=buscarDocumentoCompraVenta", data, success, error);
}

function registrarDocumento(numDoc,idEmisor,fecDoc,tipoComprobante,tipoMoneda,desComprobante){
    var data = {
        'numDoc': numDoc,
        'idPersonaEmisor': idEmisor,
        'fechaDoc': fecDoc,
        'tipoComprobante': tipoComprobante,
        'desComprobante':desComprobante,
        'tipoMoneda': tipoMoneda,
        'listaDetalle': JSON.stringify(listaItems)

    };
    
    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.result.Message === 'OK') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                setTimeout("location.reload(true);", 5000);
                limpiarFactura();
                setTimeout("location.reload(true);", 5000);
            }
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };
    
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    
    fn_callmethod("../sDocCompra?action=registrarDocumentoCompra", data, success, error);
}

function buscarEmisores(TipoBusqueda, txtPersonaJuridica) {
    var data = {'TipoBusqueda': TipoBusqueda, 'txtPersonaJuridica': txtPersonaJuridica};
    var success = function (res) {
        if (res.Result === 'OK') {
            var listaPersonaJuridicas = res.listaJ;
            listarTablaModalEmisor(listaPersonaJuridicas);
        } else {
            toast('error', res.mensaje, 'Error');
        }
    };

    var error = function (res) {
        toast('error', res.mensaje, 'Error');
    };
    fn_callmethod("../sPersonaJuridica?action=buscarPersonaJuridicaModal", data, success, error);
}

function listarUnidadMedida() {

    var success = function (res) {
        if (res.Result === 'OK') {
            var listaUnidadMedida = res.listarUnidadMedida;
            listarComboUnidadMedida(listaUnidadMedida);
        } else {
            toast('error', res.mensaje, 'Error');
        }
    };

    var error = function (res) {
        toast('error', res.mensaje, 'Error');
    };
    fn_callmethod("../sUnidadMedida?action=obtenerUnidadMedida", '', success, error);
}

function listarTipoServicio() {
    var success = function (res) {
        if (res.Result === 'OK') {
            var listaTipoServicio = res.listaTipoServicio;
            listarComboTipoServicio(listaTipoServicio);
        } else {
            toast('error', res.mensaje, 'Error');
        }
    };

    var error = function (res) {
        toast('error', res.mensaje, 'Error');
    };
    fn_callmethod("../sTipoServicio?action=listarTipoServicio", '', success, error);
}

function listarTipoComprobanteCompra(){
    var success = function (res) {
        if (res.Result === 'OK') {
            var listaComprobanteCompra = res.listaComprobanteCompra;
            listarComboTipoDocumento(listaComprobanteCompra);
        } else {
            toast('error', res.mensaje, 'Error');
        }
    };

    var error = function (res) {
        toast('error', res.mensaje, 'Error');
    };
    fn_callmethod("../sTipoComprobantePago?action=listarComprobanteCompra", '', success, error);
}



function listarComboUnidadMedida(listaUnidadMedida) {
    $('#cboModalDetalleUniMedida').empty();

    $.each(listaUnidadMedida, function (i, item) {
        $('#cboModalDetalleUniMedida').append("<option value='" + item.codigoMedida + "'>" + item.descripcion + "</option>");
    });
    $("#cboModalDetalleUniMedida").index(1);
}

function listarComboTipoDocumento(listarComboTipoServicio) {
    $('#cboTipoComprobante').empty();

    $.each(listarComboTipoServicio, function (i, item) {
        $('#cboTipoComprobante').append("<option value='" + item.idTipoComprobante + "'>" + item.descripcion + "</option>");
    });
    $("#cboTipoComprobante").index(1);
}

$("#cboBusqueda").change(function () {
    var tipoBusqueda = $("#cboBusqueda").val();
    if (tipoBusqueda == '1') {
        $("#busquedaNroDocumento").css("display", "block");
        $("#busquedaFechas").css("display", "none");
    } else {
        
        $("#busquedaNroDocumento").css("display", "none");
        $("#busquedaFechas").css("display", "block");
    }
})

$("#cboModalDetalleTipoServicio").change(function () {
    var tipoTipoServicio = $("#cboModalDetalleTipoServicio").val();
    if (tipoTipoServicio == '0') {
        $("#cboModalDetalleDescripcion").prop("disabled", false);
        $("#cboModalDetalleDescripcion").val("");
    } else {
        $("#cboModalDetalleDescripcion").prop("disabled", false);
        $("#cboModalDetalleDescripcion").val($('#cboModalDetalleTipoServicio option:selected').text());
    }
})

function calcularItemMontos(tipo) {
    objItem.cant = $("#txtModalDetalleCant").val();
    if(tipo===1){
        objItem.vUnit = parseFloat($("#txtModalDetalleVUnit").val()).toFixed(2);
        objItem.pUnit = parseFloat(objItem.vUnit * 1.18).toFixed(2);
        $("#txtModalDetallePUnit").val(objItem.pUnit);
        objItem.importe = parseFloat((objItem.cant * objItem.vUnit) * 1.18).toFixed(2);
        objItem.igv = parseFloat(objItem.vUnit * 0.18).toFixed(2);
    }else{
        
        objItem.pUnit  = parseFloat($("#txtModalDetallePUnit").val()).toFixed(2);
        objItem.vUnit= (objItem.pUnit / 1.18).toFixed(2);
        $("#txtModalDetalleVUnit").val(objItem.vUnit);
        objItem.importe = parseFloat((objItem.cant * objItem.pUnit)).toFixed(2);
        objItem.igv = parseFloat((objItem.pUnit / 1.18) * 0.18).toFixed(2);
    }
    objItem.valorUnitarioTotal=parseFloat(objItem.cant * objItem.vUnit).toFixed(2);
    objItem.igvTotal=parseFloat(objItem.cant * objItem.igv).toFixed(2);
    
    
    
    
    $("#txtModalDetalleIGV").val(objItem.igv);
    $("#txtModalDetalleImporte").val(objItem.importe);
}

function calcularTotales() {
    var subTotal=0.00;
    var igvTotal=0.00;
    var total=0.00;
    
    listaItems.forEach(function(elem,index){
        subTotal=parseFloat(parseFloat(subTotal)+parseFloat(elem.valorUnitario)).toFixed(2);
        igvTotal=parseFloat(parseFloat(igvTotal)+parseFloat(elem.igv)).toFixed(2);
        total=parseFloat(parseFloat(total)+parseFloat(elem.monto)).toFixed(2);
    })
    
    

    $("#lblSubTotal").html(subTotal);
    $("#lblIGV").html(igvTotal);
    $("#lblTotal").html(total);
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

$("#txtFechaDoc").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$('#txtBusFecInicio').datepicker().on('changeDate', function (e) {
    if ($('#txtBusFecInicio').val() == '') {
        $('#txtBusFecInicio').datepicker('update', fechaIni);
    } else {
        fechaIni = new Date($('#txtBusFecInicio').datepicker('getDate'));
    }
})

$('#txtBusFecFin').datepicker().on('changeDate', function (e) {
    if ($('#txtBusFecFin').val() == '') {
        $('#txtBusFecFin').datepicker('update', fechaFin);
    } else {
        fechaFin = new Date($('#txtBusFecFin').datepicker('getDate'));
    }
})

$('#txtFechaDoc').datepicker().on('changeDate', function (e) {
    if ($('#txtFechaDoc').val() == '') {
        $('#txtFechaDoc').datepicker('update', fechaDocumento);
    } else {
        fechaDocumento = new Date($('#txtFechaDoc').datepicker('getDate'));
    }
})

//validacion numero
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
    if (input.value == '') {
        var numero = 0;
    } else {
        var numero = input.value;
    }

    var num = parseFloat(numero);

    input.value = num.toFixed(2);
}

//Solo numero
function soloNumero(e) {
    var key = window.Event ? e.which : e.keyCode
    if (e.value == "" || e.value == "0") {
        e.value = '1';
    } else {
        e.value = parseInt(e.value, 10);

        return ((key >= 48 && key <= 57));
    }


}