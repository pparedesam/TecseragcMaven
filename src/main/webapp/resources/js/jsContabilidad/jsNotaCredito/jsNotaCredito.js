var fechaIni = new Date();
var fechaFin = new Date();

var fechaModalIni = new Date();
var fechaModalFin = new Date();

var fechaDocumento = new Date();

var objItem = new Object();
var listaItems = [];
var subTotal = 0.00;
var tipoDetalle = "nuevo";

let objDocumentoGenerado = new Object();

$(document).ready(function () {

    //FECHAS
    $('#txtBusFecInicio').datepicker('update', fechaIni);
    $('#txtBusFecFin').datepicker('update', fechaFin);

    $('#txtModalBusFecInicio').datepicker('update', fechaModalIni);
    $('#txtModalBusFecFin').datepicker('update', fechaModalFin);

    $('#txtFechaDoc').datepicker('update', fechaDocumento);



    //LISTAR TABLAS
    listarTablaNotaCredito('');
    listarTablaModalFacturas('');
    listarTablaGuiaRemision('');
    listarTablaDetalle('');
    listarUnidadMedida();
    listarTipos();
    listarTipoServicio();

    $('#cboModalDetalleTipoServicio').select2();
});

$("#btnBuscarNotaCredito").click(function () {
    var criterio = $("#cboBusqueda").val();
    var nroDoc = '';
    var fecIni = '';
    var fecFin = '';

    if (criterio === "2") {
        nroDoc = $("#txtBusquedaNroNotaCredito").val();
        fecIni = '';
        fecFin = '';
    } else {
        nroDoc = '';
        fecIni = $("#txtBusFecInicio").val();
        fecFin = $("#txtBusFecFin").val();

    }
    buscarNotasCredito(nroDoc, fecIni, fecFin);
});

$("#btnModalBuscarFactura").click(function () {
    var criterio = $("#cboModalBusquedaFactura").val();
    var nroDoc = '';
    var fecIni = '';
    var fecFin = '';

    if (criterio === "2") {
        nroDoc = $("#txtModalBusquedaNroFactura").val();
        fecIni = '';
        fecFin = '';
    } else {
        nroDoc = '';
        fecIni = $("#txtModalBusFecInicio").val();
        fecFin = $("#txtModalBusFecFin").val();

    }
    buscarFacturas(nroDoc, fecIni, fecFin);
});

$("#btnRegistrarNotaCredito").click(function () {



    bootbox.confirm("\u00BFEst\u00E1 seguro que desea registrar la nota de cr\u00E9dito\u003F", function (result) {
        if (result) {
            objDocumentoGenerado.listDetalleDocCompraVenta = listaItems;

            let idTipoNota = $("#cboTipo").val();

            let detalleMotivo = $("#txtMotivo").val();
            let fechaDoc = $("#txtFechaDoc").val();

            registrarDocumento(idTipoNota, detalleMotivo, fechaDoc);
        }
    });


    /*if (objCliente.idPersona == '' || objCliente.idPersona == undefined) {
     toast('warning', 'SELECCIONE UN CLIENTE', 'CLIENTE');
     } else if (listaItems.length <= 0) {
     toast('warning', 'INGRESE UN ITEM EN EL DETALLE', 'DETALLE');
     } else {
     
     bootbox.confirm("\u00BFEst\u00E1 seguro que desea registrar el documento de venta\u003F", function (result) {
     if (result) {
     var idCliente = objCliente.idPersona;
     var fechaDoc = $("#txtFechaDoc").val();
     var observacion = String($("#txtObservacion").val()).toUpperCase();
     var tipoMoneda = $("#cboMoneda").val();
     
     registrarDocumento(tipoMoneda, idCliente, observacion, fechaDoc, '0000001')
     }
     });
     
     
     }*/


})

$("#btnAgregarItem").click(function () {
    $("#cboModalDetalleDescripcion").val('');
    $("#txtModalDetalleCant").val(1.0000);
    $("#txtModalDetalleVUnit").val(0.00);
    $("#txtModalDetallePUnit").val(0.00);
    $("#txtModalDetalleVTotal").val(0.00);
    $("#txtModalDetalleIGV").val(0.00);
    $("#txtModalDetalleImporte").val(0.00);

    objItem.importe = 0;

    tipoDetalle = "nuevo";
    $("#cboModalDetalleUniMedida").prop("disabled", false);
    $("#cboModalDetalleDescripcion").prop("disabled", false);
})

$("#btnModalDetalleAgregar").click(function () {
    var tmpObjItem = new Object();
    tmpObjItem.glosaVariable = String(String($("#cboModalDetalleDescripcion").val().toUpperCase())).trim();
    tmpObjItem.precioUnitario = objItem.pUnit;
    tmpObjItem.idDetGastoSunat = $("#cboModalDetalleTipoServicio").val();

    var existe = '0';
    if (tipoDetalle == "nuevo") {
        listaItems.forEach(function (elemento, index) {
            if (elemento.glosaVariable.trim() == tmpObjItem.glosaVariable.trim()) {
                existe = '1';
            }
        })
    }

    if (tmpObjItem.glosaVariable == '' || tmpObjItem.glosaVariable.length < 5) {
        toast('warning', 'Ingrese una descripcion correcta', 'DESCRIPCION');
    } else if (objItem.importe == undefined) {
        toast('warning', 'Ingrese un precio adecuado', 'PRECIO');
    } else if (existe == '1') {
        toast('warning', 'Ya hay un item registrado con esa descripcion', 'DUPLICADO')
    } else {
        tmpObjItem.unidadMedidad = $('#cboModalDetalleUniMedida').val();
        tmpObjItem.cantidad = $('#txtModalDetalleCant').val();
        tmpObjItem.valorUnitario = $('#txtModalDetalleVUnit').val();
        tmpObjItem.igv = objItem.igv;
        //tmpObjItem.valorUnitarioTotal = objItem.valorUnitarioTotal;
        tmpObjItem.valorUnitarioTotal = $("#txtModalDetalleVTotal").val();
        //tmpObjItem.igvTotal = objItem.igvTotal;
        tmpObjItem.igvTotal = $("#txtModalDetalleIGV").val();
        //tmpObjItem.monto = objItem.importe;
        tmpObjItem.monto = $("#txtModalDetalleImporte").val();


        if (tipoDetalle == "nuevo") {
            listaItems.push(tmpObjItem);
        } else {
            listaItems.forEach(function (item, index) {
                if (item.glosaVariable === tmpObjItem.glosaVariable) {
                    listaItems.splice(index, 1, tmpObjItem)
                }
            });
        }

        listarTablaDetalle(listaItems);

        //subTotal = parseFloat(subTotal) + (parseFloat(tmpObjItem.vUnit) * parseFloat(tmpObjItem.cant));
        calcularTotales();
        $("#modalDetalle").modal('hide');
    }
})

$('#tabNotaCredito a').click(function (e) {
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

$("#txtModalDetallePUnit").change(function (e) {
    calcularItemMontos(2);
});

$("#txtModalDetalleVTotal").change(function (e) {
    var valorTotal = $("#txtModalDetalleVTotal").val();
    var IGV = $("#txtModalDetalleIGV").val();
    var total = parseFloat(parseFloat(valorTotal) + parseFloat(IGV)).toFixed(2);
    $("#txtModalDetalleImporte").val(total);
});

$("#txtModalDetalleIGV").change(function (e) {
    var valorTotal = $("#txtModalDetalleVTotal").val();
    var IGV = $("#txtModalDetalleIGV").val();
    var total = parseFloat(parseFloat(valorTotal) + parseFloat(IGV)).toFixed(2);
    $("#txtModalDetalleImporte").val(total);
});

$("#btnModalGuiaAgregar").click(function (e) {
    var tipoGuia = $('#cboBusquedaGuiaTipo option:selected').html();
    var emisor = "GUIA NO REGISTRADA";
    var fecha = $("#txtModalGuiaNRFecha").val();
    var idOficina = "01";
    var idDoc = $("#cboBusquedaGuiaTipo").val();
    var tipMoneda = "0";
    var nroDoc = String($("#txtModalGuiaNRNroGuia").val()).toUpperCase();
    var tipo = $("#cboBusquedaGuiaRegistrada").val();
    //var IdTipoGuia = tdbuttons.attr('data-idTipDoc') === undefined ? 0 : tdbuttons.attr('data-idTipDoc');

    var tmpObjGuia = new Object();
    var objTipComprobante = new Object();
    objTipComprobante.descripcion = tipoGuia;
    tmpObjGuia.objTipComprobante = objTipComprobante;
    tmpObjGuia.ejecutor = emisor;
    tmpObjGuia.nroGuia = nroDoc;
    tmpObjGuia.fechaGuia = fecha;
    tmpObjGuia.idOficina = idOficina;
    tmpObjGuia.idTipoGuia = idDoc;
    tmpObjGuia.tipMoneda = tipMoneda;
    tmpObjGuia.tipo = tipo;
    //tmpObjGuia.idTipoGuia=IdTipoGuia;


    listaGuias.push(tmpObjGuia);

    listarTablaGuiaRemision(listaGuias);
    $("#modalGuiaRemision").modal("hide");
})

function buscarNotasCredito(nroDoc, fecIni, fecFin) {
    var data = {
        'tipo': '0004',
        'nroDoc': nroDoc,
        'fecIni': fecIni,
        'fecFin': fecFin
    }

    var success = function (res) {
        if (res.Result === 'OK') {
            var listaDocCompraVenta = res.listaDocumentosCompraVenta;
            listarTablaNotaCredito(listaDocCompraVenta);
        } else {
            toast('error', res.mensaje, 'Error');
        }
    };

    var error = function (data, status, er) {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sDocVenta?action=buscarFacturas", data, success, error);
}

function buscarFacturas(nroDoc, fecIni, fecFin) {
    var data = {
        'tipo': '0001',
        'nroDoc': nroDoc,
        'fecIni': fecIni,
        'fecFin': fecFin
    }

    var success = function (res) {
        if (res.Result === 'OK') {
            var listaDocCompraVenta = res.listaDocumentosCompraVenta;
            listarTablaModalFacturas(listaDocCompraVenta);
        } else {
            toast('error', res.mensaje, 'Error');
        }
    };

    var error = function (data, status, er) {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sDocVenta?action=buscarFacturas", data, success, error);
}

function obtenerFactura(nroDoc, idDoc, idOficina, tipMoneda) {
    var data = {
        'nroDoc': nroDoc,
        'idDoc': idDoc,
        'idOficina': idOficina,
        'tipMoneda': tipMoneda
    }

    var success = function (res) {
        if (res.Result === 'OK') {

            objDocumentoGenerado = res.objDocumentoGenerado;

            txtNroFactura

            $("#txtNroFactura").val(objDocumentoGenerado.nroDocExt)
            $("#txtFechaEmision").val(objDocumentoGenerado.fechaDocumento)

            $("#txtClienterRUC").val(objDocumentoGenerado.objEjecutor.nroDocumento)
            $("#txtClienteRazonSocial").val(objDocumentoGenerado.objEjecutor.nombres)
            $("#txtClienteDireccion").val(objDocumentoGenerado.objEjecutor.direccion)
            listaItems = objDocumentoGenerado.listDetalleDocCompraVenta;
            let listaGuias = objDocumentoGenerado.listGuiaRemision;

            $("#lblSubTotal").html(objDocumentoGenerado.totalNeto);
            $("#lblIGV").html(objDocumentoGenerado.totalIGV);
            $("#lblTotal").html(objDocumentoGenerado.total);
            $("#txtObservacion").val(objDocumentoGenerado.glosaVariable);

            $("#cboMoneda").val(objDocumentoGenerado.objTipoMoneda.id);

            listarTablaDetalle(listaItems);

            listarTablaGuiaRemision(listaGuias)

            $("#modalFactura").modal("hide");
        } else {
            toast('error', res.mensaje, 'Error');
        }
    };

    var error = function (data, status, er) {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sNotaCredito?action=obtenerDocumentoGeneradoNota", data, success, error);
}

function registrarDocumento(idTipoNota, detalleMotivo, fechaDoc) {
    var data = {
        'objDocGenerado': JSON.stringify(objDocumentoGenerado),
        'idTipoNota': idTipoNota,
        'detalleMotivo': detalleMotivo,
        'fechaDoc': fechaDoc,

    };

    var success = function (res) {

        if (res.Result === 'OK') {

            toast('success', "SE REGISTRO LA NOTA DE CREDITO DE MANERA EXITOSA", 'Exito!');


            abrirVentana(svrbd + '?%2fContabilidad%2frptNotaCreditoElectronica&rs:Command=Render&' +
                    'nroDoc=' + res.nroDoc +
                    '&idDoc=' + res.idDoc +
                    '&idOficina=' + res.idOficina +
                    '&tipMoneda=' + res.tipMoneda);

            setTimeout("location.reload(true);", 2500);

        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        } else if (res.Result === 'errorConexion') {
            toast('error', res.message, 'SIN CONEXION')
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sNotaCredito?action=registrarNotaCredito", data, success, error);

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

function listarTipos() {

    var success = function (res) {
        if (res.Result === 'OK') {
            let listaTipos = res.listarTipoNotaCredito;
            listarTipoNotaCredito(listaTipos);
        } else {
            toast('error', res.mensaje, 'Error');
        }
    };

    var error = function (res) {
        toast('error', res.mensaje, 'Error');
    };
    fn_callmethod("../sNotaCredito?action=listarTipoNotaCredito", '', success, error);
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

function listarComboUnidadMedida(listaUnidadMedida) {
    $('#cboModalDetalleUniMedida').empty();

    $.each(listaUnidadMedida, function (i, item) {
        $('#cboModalDetalleUniMedida').append("<option value='" + item.codigoMedida + "'>" + item.descripcion + "</option>");
    });
    $("#cboModalDetalleUniMedida").index(1);
}

function listarTipoNotaCredito(listaTipos) {
    $('#cboTipo').empty();

    $.each(listaTipos, function (i, item) {
        $('#cboTipo').append("<option value='" + item.idTipo + "'>" + item.descripcion + "</option>");
    });
    $("#cboTipo").index(1);
}

function listarComboTipoServicio(listaTipoServicio) {
    $('#cboModalDetalleTipoServicio').empty();
    $('#cboModalDetalleTipoServicio').append("<option value='0003'>PERSONALIZADO</option>");
    $.each(listaTipoServicio, function (i, item) {
        $('#cboModalDetalleTipoServicio').append("<option value='" + item.idTipoServicio + "'>" + item.descripcion + "</option>");
    });
    $("#cboModalDetalleTipoServicio").index(1);
}

$("#cboBusqueda").change(function () {
    var tipoBusqueda = $("#cboBusqueda").val();
    if (tipoBusqueda == '1') {
        $("#busquedaNroFactura").css("display", "none");
        $("#busquedaFechas").css("display", "block");
    } else {
        $("#busquedaNroFactura").css("display", "block");
        $("#busquedaFechas").css("display", "none");
    }
})

$("#cboModalBusquedaFactura").change(function () {
    var tipoModalBusqueda = $("#cboModalBusquedaFactura").val();
    if (tipoModalBusqueda == '1') {
        $("#busquedaModalNroFactura").css("display", "none");
        $("#busquedaModalFechas").css("display", "block");
    } else {
        $("#busquedaModalNroFactura").css("display", "block");
        $("#busquedaModalFechas").css("display", "none");
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
    if (tipo === 1) {
        objItem.vUnit = parseFloat($("#txtModalDetalleVUnit").val()).toFixed(2);
        objItem.pUnit = parseFloat(objItem.vUnit * 1.18).toFixed(2);
        $("#txtModalDetallePUnit").val(objItem.pUnit);
        objItem.importe = parseFloat((objItem.cant * objItem.vUnit) * 1.18).toFixed(2);
        objItem.igv = parseFloat(objItem.vUnit * 0.18).toFixed(2);
    } else {

        objItem.pUnit = parseFloat($("#txtModalDetallePUnit").val()).toFixed(2);
        objItem.vUnit = (objItem.pUnit / 1.18).toFixed(2);
        $("#txtModalDetalleVUnit").val(objItem.vUnit);
        objItem.importe = parseFloat((objItem.cant * objItem.pUnit)).toFixed(2);
        objItem.igv = parseFloat((objItem.pUnit / 1.18) * 0.18).toFixed(2);
    }
    objItem.valorUnitarioTotal = parseFloat(objItem.cant * objItem.vUnit).toFixed(2);
    objItem.igvTotal = parseFloat(objItem.cant * objItem.igv).toFixed(2);



    $("#txtModalDetalleVTotal").val(objItem.valorUnitarioTotal);
    $("#txtModalDetalleIGV").val(objItem.igvTotal);
    $("#txtModalDetalleImporte").val(objItem.importe);
}

function calcularTotales() {

    var subTotal = 0.00;
    var igvTotal = 0.00;
    var total = 0.00;

    listaItems.forEach(function (elem, index) {
        subTotal = parseFloat(parseFloat(subTotal) + parseFloat(elem.valorUnitarioTotal)).toFixed(2);
        igvTotal = parseFloat(parseFloat(igvTotal) + parseFloat(elem.igvTotal)).toFixed(2);
        total = parseFloat(parseFloat(total) + parseFloat(elem.monto)).toFixed(2);
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

$("#txtModalBusFecInicio").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$("#txtModalBusFecFin").datepicker({
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

$('#txtModalBusFecFin').datepicker().on('changeDate', function (e) {
    if ($('#txtModalBusFecFin').val() == '') {
        $('#txtModalBusFecFin').datepicker('update', fechaFin);
    } else {
        fechaFin = new Date($('#txtModalBusFecFin').datepicker('getDate'));
    }
})

$('#txtModalBusFecInicio').datepicker().on('changeDate', function (e) {
    if ($('#txtModalBusFecInicio').val() == '') {
        $('#txtModalBusFecInicio').datepicker('update', fechaModalIni);
    } else {
        fechaModalIni = new Date($('#txtModalBusFecInicio').datepicker('getDate'));
    }
})

$('#txtModalBusFecFin').datepicker().on('changeDate', function (e) {
    if ($('#txtModalBusFecFin').val() == '') {
        $('#txtModalBusFecFin').datepicker('update', fechaModalFin);
    } else {
        fechaModalFin = new Date($('#txtModalBusFecFin').datepicker('getDate'));
    }
})

$('#txtFechaDoc').datepicker().on('changeDate', function (e) {
    if ($('#txtFechaDoc').val() == '') {
        $('#txtFechaDoc').datepicker('update', fechaDocumento);
    } else {
        fechaDocumento = new Date($('#txtFechaDoc').datepicker('getDate'));
    }
})

$("#txtMotivo").keypress(function (event) {

    var texto = $("#txtMotivo").val();

    if (event.which == 13 || texto.length >= 200) {
        return false
    } else {
        document.getElementById("txtObservacionCantCaract").innerHTML = (texto.length + 1) + ' de 200 caracteres';
    }
})

$("#txtMotivo").keyup(function (e) {

    if (e.keyCode == 46 || e.keyCode == 8) {
        var texto = $("#txtMotivo").val();
        document.getElementById("txtObservacionCantCaract").innerHTML = (texto.length) + ' de 200 caracteres';
    }

})

function getTextAreaSelection(textarea) {
    var start = textarea.selectionStart, end = textarea.selectionEnd;
    return {
        start: start,
        end: end,
        length: end - start,
        text: textarea.value.slice(start, end)
    };
}

function detectPaste(textarea, callback) {
    textarea.onpaste = function () {
        var sel = getTextAreaSelection(textarea);
        var initialLength = textarea.value.length;
        window.setTimeout(function () {
            var val = textarea.value;
            var pastedTextLength = val.length - (initialLength - sel.length);
            var end = sel.start + pastedTextLength;
            callback({
                start: sel.start,
                end: end,
                length: pastedTextLength,
                text: val.slice(sel.start, end)
            });
        }, 1);
    };
}

var textarea = document.getElementById("txtMotivo");
detectPaste(textarea, function (pasteInfo) {
    var texto = '';
    texto = textarea.value;
    if (texto.length > 200) {
        textarea.value = texto.substr(0, 200);
        document.getElementById("txtObservacionCantCaract").innerHTML = '200 de 200 caracteres';
    } else {
        document.getElementById("txtObservacionCantCaract").innerHTML = (texto.length) + ' de 200 caracteres';
    }
    //cadena.substring(indiceA[, indiceB'])

    // pasteInfo also has properties for the start and end character
    // index and length of the pasted text
});

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
    var preg = /^([0-9]+\.?[0-9]{0,4})$/;
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


function format4(input)
{
    if (input.value == '') {
        var numero = 0;
    } else {
        var numero = input.value;
    }

    var num = parseFloat(numero);

    input.value = num.toFixed(4);
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

document.getElementById('txtModalDetalleCant').addEventListener('keydown', function () {
    if (event.keyCode == 13) {
        document.getElementById('txtModalDetalleVUnit').focus();
    }
});

document.getElementById('txtModalDetalleVUnit').addEventListener('keydown', function () {
    if (event.keyCode == 13) {
        document.getElementById('txtModalDetallePUnit').focus();
    }
});

document.getElementById('txtModalDetallePUnit').addEventListener('keydown', function () {
    if (event.keyCode == 13) {
        document.getElementById('txtModalDetalleVTotal').focus();
    }
});

document.getElementById('txtModalDetalleVTotal').addEventListener('keydown', function () {

    if (event.keyCode == 13) {
        var valorTotal = $("#txtModalDetalleVTotal").val();
        var IGV = $("#txtModalDetalleIGV").val();
        var total = parseFloat(parseFloat(valorTotal) + parseFloat(IGV)).toFixed(2);
        $("#txtModalDetalleImporte").val(total);
        document.getElementById('txtModalDetalleIGV').focus();
    }
});

document.getElementById('txtModalDetalleIGV').addEventListener('keydown', function () {
    if (event.keyCode == 13) {
        var valorTotal = $("#txtModalDetalleVTotal").val();
        var IGV = $("#txtModalDetalleIGV").val();
        var total = parseFloat(parseFloat(valorTotal) + parseFloat(IGV)).toFixed(2);
        $("#txtModalDetalleImporte").val(total);
        document.getElementById('txtModalDetalleImporte').focus();
    }
});

document.getElementById('txtModalDetalleImporte').addEventListener('keydown', function () {
    if (event.keyCode == 13) {
        document.getElementById('txtModalDetalleCant').focus();
    }
});



