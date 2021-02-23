
var fechaEmisor = new Date;
var montoOperacion;
var regimenRetencion;
var correlativo = 0;

$(document).ready(function (event) {
    limpiarServletRetencion();
    $('#txtFecEmi').val(ObtenerFechaActualDMY());
    asignarFecha();
    $("#cboRegimen").change(cargarRegimen());
});

$(".simple-select").select2({
    minimumResultsForSearch: Infinity
});

$(".simple-select2").select2();

$('#checkboxPerInter').on('change', function () {
    $('#checkboxPerInter').val(this.checked ? 1 : 0);
});

$('#checkboxPercombu').on('change', function () {
    $('#checkboxPercombu').val(this.checked ? 1 : 0);
});

$('#checkboxreten').on('change', function () {
    $('#checkboxreten').val(this.checked ? 1 : 0);
});

$('#checkboxcontri').on('change', function () {
    $('#checkboxcontri').val(this.checked ? 1 : 0);
});



$('#cboRegimen').on('change', function (e) {
    cargarRegimen();
});

$('#cboTipoMoneda').on('change', function (e) {
    if ($('#cboTipoMoneda').val() == '2') {
        document.getElementById("txtTC").readOnly = false;

    } else {
        document.getElementById("txtTC").readOnly = true;
    }
});

$('#txtFecEmi2').datepicker({
    format: 'dd/mm/yyyy',
    language: 'es',
    autoclose: true,
});

$('#txtFecEmi').datepicker({
    format: 'dd/mm/yyyy',
    language: 'es',
    autoclose: true,
});

$('#txtFecPago').datepicker({
    format: 'dd/mm/yyyy',
    language: 'es',
    autoclose: true,
});

$('#txtFecEmi').datepicker().on('changeDate', function (e) {
    asignarPago();
    $('#txtPeriodo').val($('#txtFecEmi').val().substr(3, 10));
    $('#txtFecPago').val($('#txtFecEmi').val());
    $('#fechaReten').val($('#txtFecPago').val());

});

$('#txtFecPago').datepicker().on('changeDate', function (e) {

    $('#fechaReten').val($('#txtFecPago').val());

});

$("#txtRUC").keypress(function (e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        buscarPersonaJuridica();
    }
});

$("#txtTotal").keypress(function (e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        validarMontoOperacion();
    }
});

$("#txtImporteSR").keypress(function (e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        calculoRetencion();
    }

});

$("#txtTC").keypress(function (e) {
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        calculoRetencion();
    }

});

$('#btnModalRetencion').click(function (event) {

    event.preventDefault();
    var validator = $("#frmRetencion").valid();
    if (validator) {
        $("#modalRetencion").modal();

        correlativo = correlativo + 1;
        $('#txtCorrelativo').val(correlativo);

    } else {
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');
    }
});

$('#cerrarModal').click(function (event) {

    correlativo = correlativo - 1;
    limpiarModal();

});

$('#btn_Cerrar').click(function (event) {

    correlativo = correlativo - 1;
    $('#modalRetencion').modal('hide');
    limpiarModal();
});

$('#btn_Agregar').click(function (event) {
    event.preventDefault();
    var validator = $("#frmFactReten").valid();
    if (validator) {
        agregarDetalleRetencion();
    } else {
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');
    }

});

$('#btnRegistrarRetenciones').click(function (e) {
    e.preventDefault();
    if (document.getElementById("txtTotalReten").value <= 0.00) {
        toast('warning', "USTED DEBE AGREGAR DETALLE DE RETENCION", 'warning');

    } else
    {
        registrarRetencion();
    }

});

function agregarDetalleRetencion() {
    var data = {
        'idTipDocumento': $('#cboTipDocSUNAT').val(), 'tipoDocumento': $('#cboTipDocSUNAT').find('option:selected').text(), 'nroSerie': $('#txtSerieDoc').val(), 'nroDoc': $('#txtNroDoc').val(),
        'fechaEmisionDoc': $('#txtFecEmi2').val(), 'totalComprobante': $('#txtTotal').val(), 'nroPago': $('#txtCorrelativo').val(), 'importePago': $('#txtImporteSR').val(),
        'regimenRetencion': regimenRetencion, 'importeRetencion': $('#txtIR').val(), 'importeNeto': $('#txtImporteNP').val()
    };
    actualizarTabla(data, '#tblDetRet', 1);
    $('#modalRetencion').modal('hide');
}

function actualizarTabla(datos, nombreTabla, accion) {
    var idTipDocumento;
    var nroSerie;
    var nroDoc;
    idTipDocumento = datos.idTipDocumento;
    nroSerie = datos.nroSerie;
    nroDoc = datos.nroDoc;

    var data = {'idTipDocumento': idTipDocumento, 'tipoDocumento': datos.tipoDocumento, 'nroSerie': nroSerie, 'nroDoc': nroDoc, 'fechaEmisionDoc': datos.fechaEmisionDoc, 'totalComprobante': datos.totalComprobante, 'nroPago': datos.nroPago, 'importePago': datos.importePago, 'regimenRetencion': datos.regimenRetencion, 'importeRetencion': datos.importeRetencion, 'importeNeto': datos.importeNeto}

    //var data2 = {'idTipoServicio': datos.idTipoServicio, 'valorunitario': roundN(datos.valorunitario, 2), 'servicio': descripcion, 'uniMedida': datos.uniMedida, 'UM': datos.UM, 'preciounitario': datos.preciounitario, 'importe': datos.importe, 'igv': datos.igv, 'cantidad': datos.cantidad}
    //var data3 = {'detalleant': datos.detalleant, 'valorunitario': roundN(datos.valorunitario, 2), 'servicio': descripcion, 'preciounitario': roundN(datos.preciounitario, 2), 'importe': roundN(datos.importe, 2), 'igv': roundN(datos.igv, 2), 'cantidad': datos.cantidad};
    var success = function (res) {

        if (res.Result === 'OK') {

            limpiarModal();

            document.getElementById("txtTotalReten").value = roundN(res.totalRetencion, 2);
            document.getElementById("txtTotalImpNeto").value = roundN(res.totalImporteNeto, 2);

            $(nombreTabla).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.detalleRetencion,
                rowId: 'idTipDocumento', //asigna el id a la fila
                "columns": [
                    {"data": "tipoDocumento"},
                    {"data": "nroSerie"},
                    {"data": "nroDoc"}, //, "visible": false},
                    {"data": "fechaEmisionDoc"},
                    {"data": "totalComprobante"},
                    {"data": "nroPago"},
                    {"data": "importePago"},
                    {"data": "regimenRetencion"},
                    {"data": "importeRetencion"},
                    {"data": "importeNeto"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;' class='btn btn-Lapislazuli btn-circle edit' title='Editar'> <i class='fa fa-edit'> </i></a>\n"
                                    + "<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(0)").attr('data-id', data.idTipDocumento);
                    $(row).find("td:eq(0)").attr('data-nroSerie', data.nroSerie);
                    $(row).find("td:eq(0)").attr('data-nroDoc', data.nroDoc);

                }
            });
        } else if (res.Result === 'KO') {
            toast('error', "No se puede repetir una Factura ya ingresada", 'Error');
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    if (accion == 1) {
        fn_callmethod("../sRetencion?action=comprobarDetalleRetencion", data, success, error);
    } else if (accion == 2) {
        fn_callmethod("../sRetencion?action=quitarDetalleRetencion", data, success, error);
//    } else if (accion == 3) {
//        fn_callmethod("../sDocVenta?action=modificarDetalleVentaF", data3, success, error);
    }
}
;

var oTable;

oTable = fn_iniciarSinDT(oTable, "tblDetRet");

oTable.on("click", ".remove", function () {
    var elem = this;
    oTable.fnDestroy();
    fn_DeleteFact(elem);
});

var fn_DeleteFact = function (elem) {
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(1)");
//    var tdimporte = par.children("td:nth-child(5)");
//    var tdigv = par.children("td:nth-child(6)");
//    var importe = tdimporte.html();
//    var igv = tdigv.html();
    var idTipDocumento = tdbuttons.attr('data-id');
    var nroSerie = tdbuttons.attr('data-nroSerie');
    var nroDoc = tdbuttons.attr('data-nroDoc');
    var data = {'idTipDocumento': idTipDocumento, 'nroSerie': nroSerie, 'nroDoc': nroDoc};
    actualizarTabla(data, "#tblDetRet", 2);

};

function asignarFecha() {
    var fechaInicio = new Date(fechaEmisor.setDate(fechaEmisor.getDate() - 5));
    var fechaFin = new Date(fechaEmisor.setDate(fechaEmisor.getDate() + 10));
    $('#txtFecEmi').datepicker('setStartDate', fechaInicio);
    $('#txtFecEmi').datepicker('setEndDate', fechaFin);

}

function asignarPago() {

    var parts = $('#txtFecEmi').val().split('/');
    var fecha = new Date(parts[2], parts[1] - 1, parts[0]);
    var fechaInicio = new Date(fecha.getFullYear(), fecha.getMonth(), 1);
    var fechaFin = new Date(fecha.setDate(fecha.getDate()));
    $('#txtFecPago').datepicker('setStartDate', fechaInicio);
    $('#txtFecPago').datepicker('setEndDate', fechaFin);
}

function buscarPersonaJuridica() {

    var data = {'nroRuc': $('#txtRUC').val(), 'tipDoc': $('#cboTipDocumento').val()};

    var success = function (res) {
        if (res.Result === 'OK') {

            $('#txtRazonSocial').val(res.PersonaJuridica.nombres);
            $('#txtidpersonaJN').val(res.PersonaJuridica.idPersona);

        } else if (res.Result == 'error') {

        }
    };
    var error = function (data, status, er) {
        toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sPersonaJuridica?action=obtenerPJ", data, success, error);

}
;

function  validarMontoOperacion() {
    montoOperacion = 0;
    montoOperacion = convertirDecimales($('#txtTotal').val());
    $('#txtTotal').val(montoOperacion);
    $("#txtImporteOpe").empty();
    $("#txtImporteOpe").append("<label class='col-sm-4 control-label m-r'>" + montoOperacion + "</label>");
}

function cargarRegimen() {

    regimenRetencion = $('#cboRegimen').val();

    $("#divICSol").empty();
    $("#divICSol").append("<label class='col-sm-4 control-label m-r'>" + $('#cboRegimen').find('option:selected').text() + "</label>");

}
;

function calculoRetencion() {

    var tipoCambio = 0;
    var importeSR = 0;
    var IR = 0;
    var importeNeto = 0;

    importeSR = convertirDecimales($('#txtImporteSR').val());

    tipoCambio = convertirDecimales($('#txtTC').val());

    $('#txtImporteSR').val(importeSR);

    $('#txtTC').val(tipoCambio);

    regimenRetencion = convertirDecimales(regimenRetencion);

    IR = convertirDecimales(importeSR * regimenRetencion);

    $('#txtIR').val(convertirDecimales(IR * tipoCambio));

    importeNeto = importeSR - IR;

    $('#txtImporteNP').val(convertirDecimales(importeNeto * tipoCambio));

    //txtImporteSR

}

function limpiarServletRetencion() {

    var data = {};

    var success = function (data) {
    };
    var error = function () {
    };
    fn_callmethod("../sRetencion?action=limpiarSession", '', success, error);

}
;

function limpiarModal() {
    document.getElementById("txtSerieDoc").value = '';
    document.getElementById("txtNroDoc").value = '';
    document.getElementById("txtFecEmi2").value = '';
    document.getElementById("txtTotal").value = '';
    document.getElementById("txtTC").value = '1.00';
    document.getElementById("txtImporteSR").value = '';
    document.getElementById("txtIR").value = '';
    document.getElementById("txtImporteNP").value = '';
    $("#txtImporteOpe").empty();
}

function registrarRetencion() {
    var data = {'FechaEmision': $('#txtFecEmi').val(), 'periodo': $('#txtPeriodo').val(), 'tipDoc': $('#cboTipDocumento').val(),
        'ruc': $('#txtRUC').val(), 'idPersona': $('#txtidpersonaJN').val(), 'obvervacion': $('#txtobservacion').val(),
        'chkPerVenInt': $('#checkboxPerInter').val(), 'chkPerComb': $('#checkboxPercombu').val(), 'chkAgentPer': $('#checkboxreten').val(), 'chkBuenContri': $('#checkboxcontri').val()
    }
    var success = function (res) {

        if (res.Result === 'OK') {



        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    
    fn_callmethod("../sRetencion?action=registrarRetencion", data, success, error);
}

function  convertirDecimales(numero) {

    var numeroDecimal;
    numeroDecimal = parseFloat(numero).toFixed(2);
    numeroDecimal = (numeroDecimal.replace(/[^0-9\.-]+/g, ""));
    return numeroDecimal;
}

function roundN(num, n) {
    return parseFloat(Math.round(num * Math.pow(10, n)) / Math.pow(10, n)).toFixed(n);
}

var inputPorcentajesoloNumero = function (e) {

    var key = window.Event ? e.which : e.keyCode

    return ((key >= 48 && key <= 57) || (key == 8))

}

var inputdecimales = function (e) {

    var key = window.Event ? e.which : e.keyCode

    return ((key >= 48 && key <= 57) || (key == 8) || (key == 46))

}
