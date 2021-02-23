
function listarTablaRecibos(listaRecibos) {
    $('#tblRecibos').DataTable({
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
        "aaData": listaRecibos,
        "columns": [
            {"data": "objTipoComprobante.descripcion", "width": "15%", className: "text-center"},
            {"data": "nroDoc", "width": "10%", className: "text-center"},
            {"data": "objEjecutor.nombres", "width": "25%", className: "text-center"},
            {"data": "fechaDocumento", "width": "10%", className: "text-center"},
            {"data": "total", "width": "10%", className: "text-center",render: $.fn.dataTable.render.number('', '.', 2)},
            {"data": "estado", "width": "15%", className: "text-center"},
           {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                   $(nTd).html("<a class='btn btn-Lapislazuli btn-circle view' title='Ver recibo' ><i class='fa fa-eye'></i></a> <a class='btn btn-danger btn-circle remove' title='Anular recibo' ><i class='fa fa-minus'></i></a>");
                  },
                className: "text-center", "width": "15%"}
        ],
        createdRow: function (row, data, indice) {

            $(row).find("td:eq(0)").attr('data-nroDoc', data.nroDoc);
            $(row).find("td:eq(0)").attr('data-idDoc', data.idDoc);
            $(row).find("td:eq(0)").attr('data-idOficina', data.objOficina.idOficina);
            $(row).find("td:eq(0)").attr('data-idTipMoneda', data.idTipMoneda);
        }
    });
}

function listarTablaPersona(listaPersona) {
    $('#tblModalPersona').DataTable({
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
        "aaData": listaPersona,
        "columns": [
            {"data": "nroDocumento", "width": "10%", className: "text-center"},
            {"data": "nombres", "width": "15%", className: "text-center"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-Lapislazuli btn-circle add' title='Agregar titualar'><i class='fa fa-plus'></i></a>");
                },
            className: "text-center", "width": "10%"},
        ],
        createdRow: function (row, data, indice) {

            $(row).find("td:eq(2)").attr('data-id', data.idPersona);
        }
    });
}

$('#tblModalPersona').on("click", ".add", function () {
    fn_addPersona(this);
});

$('#tblRecibos').on("click", ".remove", function () {
    fn_anularRecibo(this);
});

$('#tblRecibos').on("click", ".view", function () {
    fn_verRecibo(this);
});



function fn_addPersona(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(3)");
    idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    
    var nroDocumento= par.children("td:nth-child(1)").html();
    var nombres= par.children("td:nth-child(2)").html();
    
    $("#txtReciboPersona").val(nombres);
    $("#txtReciboDNI").val(nroDocumento);
    
    $("#modalPersona").modal('hide');
    
}

function fn_anularRecibo(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(1)");
    objRecibo.nroDoc = tdbuttons.attr('data-nroDoc') === undefined ? 0 : tdbuttons.attr('data-nroDoc');
    objRecibo.idDoc = tdbuttons.attr('data-idDoc') === undefined ? 0 : tdbuttons.attr('data-idDoc');
    objRecibo.idOficina = tdbuttons.attr('data-idOficina') === undefined ? 0 : tdbuttons.attr('data-idOficina');
    objRecibo.idTipMoneda = tdbuttons.attr('data-idTipMoneda') === undefined ? 0 : tdbuttons.attr('data-idTipMoneda');
    
    $("#txtModalAnularMotivo").val('');
    $("#modalReciboAnular").modal("show");
}

function fn_verRecibo(elem){
    
     var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(1)");
    objRecibo.nroDoc = tdbuttons.attr('data-nroDoc') === undefined ? 0 : tdbuttons.attr('data-nroDoc');
    objRecibo.idDoc = tdbuttons.attr('data-idDoc') === undefined ? 0 : tdbuttons.attr('data-idDoc');
    objRecibo.idOficina = tdbuttons.attr('data-idOficina') === undefined ? 0 : tdbuttons.attr('data-idOficina');
    objRecibo.idTipMoneda = tdbuttons.attr('data-idTipMoneda') === undefined ? 0 : tdbuttons.attr('data-idTipMoneda');
    
    
   
    
    abrirVentana(svrbd + '?%2fCaja%2frptReciboCaja&rs:Command=Render&' +
            'NroDoc=' + objRecibo.nroDoc +
            '&IdDoc=' + objRecibo.idDoc +
            '&IdOficina=' + objRecibo.idOficina +
            '&TipMoneda=' + objRecibo.idTipMoneda);
    setTimeout("location.reload(true);", 5000);
}





