
function listarTablaGuiaRemision(listaDetDocCompraVenta) {
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
        "aaData": listaDetDocCompraVenta,
        "columns": [
            {"data": "documentoNro", "width": "20%", className: "text-center"},
            {"data": "ejecutor", "width": "40%", className: "text-center"},
            {"data": "fecha", "width": "10%", className: "text-center"},
            {"data": "totalCarga", "width": "20%", className: "text-center"},
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
                    $(nTd).html("<a class='btn btn-Lapislazuli btn-circle xml' title='Descargar XML'><i class='fa fa-file-code-o'></i></a>");
                },
            className: "text-center", "width": "10%"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-Lapislazuli btn-circle cdr' title='Descargar CDR'><i class='fa fa-cloud-download'></i></a>");
                },
            className: "text-center", "width": "10%"}
        
        ],
        createdRow: function (row, data, indice) {

            $(row).find("td:eq(4)").attr('data-id', data.idOficina);
            $(row).find("td:eq(4)").attr('data-id', data.idDoc);
            $(row).find("td:eq(4)").attr('data-id', data.tipMoneda);
            $(row).find("td:eq(4)").attr('data-id', data.nroDoc);
            
        }
       
    });
}

function listarTablaDetalle(listaDetalle) {
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
        "aaData": listaDetalle,
        "columns": [
            {"data": "descripcion", "width": "40%", className: "text-center"},
            {"data": "cantidad", "width": "20%", className: "text-center"},
            {"data": "unidadMedida", "width": "30%", className: "text-center"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-danger btn-circle remove' title='Eliminar caracteristica'><i class='fa fa-minus'></i></a>");
                },
            className: "text-center", "width": "10%"}
        ],
        createdRow: function (row, data, indice) {
            $(row).find("td:eq(3)").attr('data-id', data.id);            
        }
       
    });
}


$('#tblDetalle').on("click", ".remove", function () {
    fn_quitarDetalleItem(this);
});

$('#tblGuiaRemision').on("click", ".xml", function () {
    fn_descargarXML(this);
});

$('#tblGuiaRemision').on("click", ".cdr", function () {
    fn_descargarCDR(this);
});

function fn_quitarDetalleItem(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var idItem = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
        
    listaDetalle.forEach(function(item, index) {
            if(item.id==idItem){
                listaDetalle.splice(index,1);
            }
        });
    
    listaDetalle.forEach(function(item, index) {
        var objDetalle=new Object();
    
        objDetalle.id=index+1;
        objDetalle.unidadMedida = item.unidadMedida;
        objDetalle.cantidad = item.cantidad
        objDetalle.descripcion= item.descripcion
        listaDetalle.splice(index,1,objDetalle);
    });
        
    listarTablaDetalle(listaDetalle);

}

function fn_descargarXML(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var nroGuia= par.children("td:nth-child(1)");
    descargarXML(nroGuia.html());
}

function fn_descargarCDR(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(5)");
    var nroGuia= par.children("td:nth-child(1)");
    descargarCDR(nroGuia.html());
}
