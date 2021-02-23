function listarTablaNotaDebito(listaDocCompraVenta) {
    $('#tblNotasDebito').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "zeroRecords": "Nada encontrado - lo siento",
            "info": "Mostrando la p&aacute;gina _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registro disponibles.",
            "infoFiltered": "(Filtrado _MAX_ registros en total)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Ultimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            }
        },
        destroy: true, "bFilter": true, "lengthChange": false, "paging": true, "bInfo": false,
        responsive: true,
        "autoWidth": false,
        ordering: false,
        "pageLength": 20,
        "columnDefs": [
            {
                "className": "text-center",
                "targets": "_all"
            }
        ],
        "aaData": listaDocCompraVenta,
        "columns": [
            {"data": "documentoNro", "width": "20%", className: "text-center"},
            {"data": "ejecutor", "width": "40%", className: "text-center"},
            {"data": "fecha", "width": "10%", className: "text-center"},
            {"data": "total", "width": "20%", className: "text-center"},
            {"data": "estado", "width": "20%", className: "text-center"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-Lapislazuli btn-circle print' title='Imprimir'><i class='fa fa-print'></i></a>");
                },
                className: "text-center", "width": "10%"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-Lapislazuli btn-circle info' title='Informacion'><i class='fa fa-info-circle'></i></a>");
                },
                className: "text-center", "width": "10%"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a href = \"../sDownload?idFact=08-" + sData.documentoNro + "\" class='btn btn-Lapislazuli btn-circle xml' title='Descargar XML'><i class='fa fa-file-code-o'></i></a>");
                },
                className: "text-center", "width": "10%"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a  href = \"../sDownloadCDR?idFactCDR=08-" + sData.documentoNro + "\" class='btn btn-Lapislazuli btn-circle cdr' title='Descargar CDR'><i class='fa fa-cloud-download'></i></a>");
                },
                className: "text-center", "width": "10%"}

        ],
        createdRow: function (row, data, indice) {

            $(row).find("td:eq(4)").attr('data-idOficina', '01');
            $(row).find("td:eq(4)").attr('data-idDoc', data.idDoc);
            $(row).find("td:eq(4)").attr('data-tipMoneda', data.objMoneda.id);
            $(row).find("td:eq(4)").attr('data-nroDoc', data.nroDoc);

        }

    });
}

function listarTablaModalFacturas(listaDocCompraVenta) {
    $('#tblModalFactura').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "zeroRecords": "Nada encontrado - lo siento",
            "info": "Mostrando la p&aacute;gina _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registro disponibles.",
            "infoFiltered": "(Filtrado _MAX_ registros en total)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Ultimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            }
        },
        destroy: true, "bFilter": true, "lengthChange": false, "paging": true, "bInfo": false,
        responsive: true,
        "autoWidth": false,
        ordering: false,
        "pageLength": 5,
        "columnDefs": [
            {
                "className": "text-center",
                "targets": "_all"
            }
        ],
        "aaData": listaDocCompraVenta,
        "columns": [
            {"data": "documentoNro", "width": "15%", className: "text-center"},
            {"data": "ejecutor", "width": "30%", className: "text-center"},
            {"data": "fecha", "width": "10%", className: "text-center"},
            {"data": "total", "width": "20%", className: "text-center"},
            {"data": "estado", "width": "20%", className: "text-center"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-success btn-circle add' title='agregar'><i class='fa fa-plus'></i></a>");
                },
                className: "text-center", "width": "20%"}

        ],
        createdRow: function (row, data, indice) {

            $(row).find("td:eq(4)").attr('data-idOficina', '01');
            $(row).find("td:eq(4)").attr('data-idDoc', data.idDoc);
            $(row).find("td:eq(4)").attr('data-tipMoneda', data.objMoneda.id);
            $(row).find("td:eq(4)").attr('data-nroDoc', data.nroDoc);

        }

    });
}

function listarTablaGuiaRemision(listaGuias) {
    $('#tblGuiaRemision').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "zeroRecords": "Nada encontrado - lo siento",
            "info": "Mostrando la p&aacute;gina _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registro disponibles.",
            "infoFiltered": "(Filtrado _MAX_ registros en total)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Ultimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            }
        },
        destroy: true, "bFilter": false, "lengthChange": false, "paging": true, "bInfo": false,
        responsive: true,
        "autoWidth": false,
        ordering: false,
        "pageLength": 5,
        "columnDefs": [
            {
                "className": "text-center",
                "targets": "_all"
            }
        ],
        "aaData": listaGuias,
        "columns": [
            {"data": "tipoGuia", "width": "30%", className: "text-center"},
            {"data": "objPersonaDestinatario.nombres", "3idth": "20%", className: "text-center"},
            {"data": "nroGuia", "width": "20%", className: "text-center"},
            {"data": "fechaGuia", "width": "20%", className: "text-center"},
        ],
        createdRow: function (row, data, indice) {

        }

    });
}

function listarTablaDetalle(listaItem) {
    $('#tblDetalle').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "zeroRecords": "Nada encontrado - lo siento",
            "info": "Mostrando la p&aacute;gina _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registro disponibles.",
            "infoFiltered": "(Filtrado _MAX_ registros en total)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Ultimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            }
        },
        destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
        responsive: true,
        "autoWidth": false,
        ordering: false,
        "pageLength": 10,
        "columnDefs": [
            {
                "className": "text-center",
                "targets": "_all"
            }
        ],
        "aaData": listaItem,
        "columns": [
            {"data": "cantidad", "width": "10%", className: "text-center"},
            {"data": "unidadMedidad", "width": "15%", className: "text-center"},
            {"data": "glosaVariable", "width": "35%", className: "text-left"},
            {"data": "valorUnitario", "width": "10%", className: "text-right"},
            {"data": "igvTotal", "width": "10%", className: "text-right"},
            {"data": "monto", "width": "10%", className: "text-right"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-success btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a> <a class='btn btn-danger btn-circle remove' title='Retirar'><i class='fa fa-minus'></i></a>");
                },
                className: "text-center", "width": "10%"}
        ],
        createdRow: function (row, data, indice) {
            $(row).find("td:eq(4)").attr('data-id', data.idPersona);
        }

    });
}




$("#tblNotasDebito").on("click", ".print", function () {
    fn_imprimirFactura(this);
})

$("#tblNotasDebito").on("click", ".info", function () {
    fn_infoFactura(this);
})

$("#tblModalFactura").on("click", ".add", function () {
    fn_addFactura(this);
})

$("#tblDetalle").on("click", ".edit", function () {
    fn_editarItem(this);
})

$("#tblDetalle").on("click", ".remove", function () {
    fn_retirarItem(this);
})

function fn_imprimirFactura(elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(5)");
    var nroDoc = tdbuttons.attr('data-nroDoc') === undefined ? 0 : tdbuttons.attr('data-nroDoc');
    var idDoc = tdbuttons.attr('data-idDoc') === undefined ? 0 : tdbuttons.attr('data-idDoc');
    var idOficina = tdbuttons.attr('data-idOficina') === undefined ? 0 : tdbuttons.attr('data-idOficina');
    var tipMoneda = tdbuttons.attr('data-tipMoneda') === undefined ? 0 : tdbuttons.attr('data-tipMoneda');

    abrirVentana(svrbd + '?%2fContabilidad%2frptNotaDebitoElectronica&rs:Command=Render&' +
            'nroDoc=' + nroDoc +
            '&idDoc=' + idDoc +
            '&idOficina=' + idOficina +
            '&tipMoneda=' + tipMoneda);
}

function fn_infoFactura(elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(5)");
    var nroFactura = par.children("td:nth-child(1)").html();



    var data = {

        'nroFactura': nroFactura

    };
    var success = function (res) {

        if (res.Result === 'OK') {

            $('#txtResponse').val(res.response);
            $("#modalInfoFactura").modal('show');

        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };
    var error = function (res) {
        $('#txtResponse').val('');
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sNotaDebito?action=obtenerRsptaSUNAT", data, success, error);
}

function fn_addFactura(elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(5)");
    var nroDoc = tdbuttons.attr('data-nroDoc') === undefined ? 0 : tdbuttons.attr('data-nroDoc');
    var idDoc = tdbuttons.attr('data-idDoc') === undefined ? 0 : tdbuttons.attr('data-idDoc');
    var idOficina = tdbuttons.attr('data-idOficina') === undefined ? 0 : tdbuttons.attr('data-idOficina');
    var tipMoneda = tdbuttons.attr('data-tipMoneda') === undefined ? 0 : tdbuttons.attr('data-tipMoneda');

    obtenerFactura(nroDoc, idDoc, idOficina, tipMoneda);

    $("#modalFactura").modal("hide");
}

function fn_retirarItem(elem) {
    var par = $(elem).parent().parent();
    var glosaVariable = par.children("td:nth-child(3)").html();
    //var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    listaItems.forEach(function (item, index) {
        if (item.glosaVariable === glosaVariable) {
            listaItems.splice(index, 1)
        }
    });
    calcularTotales();
    listarTablaDetalle(listaItems);
}

function fn_editarItem(elem) {
    var par = $(elem).parent().parent();
    var undMedida = par.children("td:nth-child(2)").html();
    var glosaVariable = par.children("td:nth-child(3)").html();
    //var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    $("#cboModalDetalleUniMedida").val(undMedida);

    listaItems.forEach(function (item, index) {
        if (item.glosaVariable === glosaVariable) {
            $("#cboModalDetalleDescripcion").val(item.glosaVariable);
            $("#txtModalDetalleCant").val(item.cantidad);
            $("#txtModalDetalleVUnit").val(item.valorUnitario);
            $("#txtModalDetallePUnit").val(item.precioUnitario);
            $("#txtModalDetalleVTotal").val(item.valorUnitarioTotal);
            $("#txtModalDetalleIGV").val(item.igvTotal);
            $("#txtModalDetalleImporte").val(item.monto);
        }
    });
    tipoDetalle = "editar";
    $("#cboModalDetalleUniMedida").prop("disabled", true);
    $("#cboModalDetalleDescripcion").prop("disabled", true);
    $("#modalDetalle").modal('show');
}


