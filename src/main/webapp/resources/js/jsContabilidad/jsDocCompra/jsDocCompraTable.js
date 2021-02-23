function listarTablaDocumentos(listaDocCompraVenta) {
    $('#tblDocumento').DataTable({
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
            {"data": "objTipComprobante.descripcion", "width": "20%", className: "text-center"},
            {"data": "documentoNro", "width": "20%", className: "text-center"},
            {"data": "ejecutor", "width": "40%", className: "text-center"},
            {"data": "fecha", "width": "10%", className: "text-center"},
            {"data": "totalVN", "width": "10%", className: "text-center"},
            {"data": "totalIGV", "width": "10%", className: "text-center"},
            {"data": "total", "width": "20%", className: "text-center"},
            {"data": "estado", "width": "20%", className: "text-center"},
//            {"data": null,
//                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
//                    $(nTd).html("<a class='btn btn-Lapislazuli btn-circle print' title='Imprimir'><i class='fa fa-print'></i></a>");
//                },
//            className: "text-center", "width": "10%"},
            
        ],
        createdRow: function (row, data, indice) {

            $(row).find("td:eq(4)").attr('data-id', data.idOficina);
            $(row).find("td:eq(4)").attr('data-id', data.idDoc);
            $(row).find("td:eq(4)").attr('data-id', data.tipMoneda);
            $(row).find("td:eq(4)").attr('data-id', data.nroDoc);
            
        }
       
    });
}

function listarTablaModalEmisor(listaClientes) {
    $('#tblModalEmisor').DataTable({
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
        "pageLength": 10,
        "columnDefs": [
            {
                "className": "text-center",
                "targets": "_all"
            }
        ],
        "aaData": listaClientes,
        "columns": [
            {"data": "nroDocumento", "width": "15%", className: "text-center"},
            {"data": "nombres", "width": "35%", className: "text-left"},
            {"data": "direccion", "width": "35%", className: "text-left"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-Lapislazuli btn-circle add' title='Selecionar'><i class='fa fa-plus'></i></a>");
                },
            className: "text-center", "width": "15%"}
        ],
        createdRow: function (row, data, indice) {
            $(row).find("td:eq(3)").attr('data-id', data.idPersona);
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
            {"data": "igv", "width": "10%", className: "text-right"},
            {"data": "monto", "width": "10%", className: "text-right"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-danger btn-circle remove' title='Retirar'><i class='fa fa-minus'></i></a>");
                },
            className: "text-center", "width": "10%"}
        ],
        createdRow: function (row, data, indice) {
            $(row).find("td:eq(4)").attr('data-id', data.idPersona);
        }
       
    });
}

$('#tblModalEmisor').on("click", ".add", function () {
    fn_agregarEmisor(this);
});

$('#tblDetalle').on("click", ".remove", function () {
    fn_removeDetalle(this);
});

function fn_agregarEmisor(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var ruc= par.children("td:nth-child(1)");
    var razonSocial= par.children("td:nth-child(2)");
    var direccion= par.children("td:nth-child(3)");
    var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    
    objEmisor.idPersona=idPersona;
    objEmisor.ruc=ruc.html();
    objEmisor.razonSocial=razonSocial.html();
    objEmisor.direccion=direccion.html();
    
    $("#txtEmisorRUC").val(objEmisor.ruc);
    $("#txtEmisorRazonSocial").val(objEmisor.razonSocial);
    $("#txtEmisorDireccion").val(objEmisor.direccion);
    
    $("#modalEmisor").modal("hide");
}

function fn_removeDetalle(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var glosaVariable= par.children("td:nth-child(3)").html();
    //var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    
    listaItems.forEach(function(item,index){
        if(item.glosaVariable===glosaVariable){
            listaItems.splice(index,1)
        }
    });
    
    listarTablaDetalle(listaItems);
}