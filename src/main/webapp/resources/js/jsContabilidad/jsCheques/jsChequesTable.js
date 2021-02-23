
function listarTablaCheques(listaCheques) {
    $('#tblCheques').DataTable({
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
        "aaData": listaCheques,
        "columns": [
            {"data": "nrocheque", "width": "10%", className: "text-center"},
            {"data": "objDocGenerado.fechaDocumento", "width": "10%", className: "text-center"},
            {"data": "objPersona.nombres", "width": "20%", className: "text-left"},
            {"data": "tipMoneda", "width": "10%", className: "text-center"},
            {"data": "monto", "width": "10%", className: "text-right", render: $.fn.dataTable.render.number( ',', '.', 2)},
            {"data": "objDocGenerado.glosaVariable", "width": "20%", className: "text-left"},
            {"data": "estado", "width": "10%", className: "text-center"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    if(sData.objDocGenerado.idEstadoDocumento=='05'){
                        $(nTd).html(" ");
                    }else{
                        $(nTd).html("<a class='btn btn-Lapislazuli btn-circle print' title='Imprimir cheque' ><i class='fa fa-print'></i></a> <a class='btn btn-danger btn-circle remove' title='Anular cheque' ><i class='fa fa-minus'></i></a>");
                    }
                    
                },
            className: "text-center", "width": "10%"},
        ],
        createdRow: function (row, data, indice) {

            $(row).find("td:eq(6)").attr('data-id', data.idOficina);
            $(row).find("td:eq(6)").attr('data-montoLetras', data.montoLetras);
            $(row).find("td:eq(6)").attr('data-idDoc', data.objDocGenerado.idDoc);
            $(row).find("td:eq(6)").attr('data-nroDoc', data.objDocGenerado.nroDoc);
            $(row).find("td:eq(6)").attr('data-idOficina', data.objDocGenerado.objOficina.idOficina);
            $(row).find("td:eq(6)").attr('data-idTipMoneda', data.objDocGenerado.idTipMoneda);
        }
    });
}

function listarTablaTitular(listaTitutlares) {
    $('#tblModalTitular').DataTable({
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
        "aaData": listaTitutlares,
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


$('#tblCheques').on("click", ".print", function () {
    fn_imprimirCheque(this);
});

$('#tblCheques').on("click", ".remove", function () {
    fn_anularCheque(this);
});

$('#tblModalTitular').on("click", ".add", function () {
    fn_addTitular(this);
});

function fn_addTitular(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(3)");
    idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    
    var nroDocumento= par.children("td:nth-child(1)").html();
    var nombres= par.children("td:nth-child(2)").html();
    
    $("#txtTitNroDoc").val(nroDocumento);
    $("#txtTitNombres").val(nombres);
    
    $("#modalTitular").modal('hide');
    
}

function fn_imprimirCheque(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(7)");
    var montoLetras = tdbuttons.attr('data-montoLetras') === undefined ? 0 : tdbuttons.attr('data-montoLetras');
    
    var fecha= par.children("td:nth-child(2)").html();
    var nombres= par.children("td:nth-child(3)").html();
    var monto= par.children("td:nth-child(5)").html();
    
    objCheque.fecha = fecha;
    objCheque.dia = objCheque.fecha.substring(0,2);
    objCheque.mes = objCheque.fecha.substring(3,5);
    objCheque.anio = objCheque.fecha.substring(6,10);
    objCheque.monto = String(monto);
    objCheque.persona = String(nombres);
    objCheque.montoLetras = String(montoLetras);
    
    document.getElementById("svgDia").innerHTML = objCheque.dia;
    document.getElementById("svgMes").innerHTML = objCheque.mes;
    document.getElementById("svgAnio").innerHTML = objCheque.anio;
    document.getElementById("svgMonto").innerHTML = objCheque.monto;
    document.getElementById("svgPersona").innerHTML = objCheque.persona;
    document.getElementById("svgMontoLetras").innerHTML = objCheque.montoLetras;
    $("#modalImprimir").modal("show");

}

function fn_anularCheque(elem){
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(7)");
    var tmpObjCheque=new Object();
    tmpObjCheque.idDoc = tdbuttons.attr('data-idDoc') === undefined ? 0 : tdbuttons.attr('data-idDoc');
    tmpObjCheque.nroDoc = tdbuttons.attr('data-nroDoc') === undefined ? 0 : tdbuttons.attr('data-nroDoc');
    tmpObjCheque.idOficina = tdbuttons.attr('data-idOficina') === undefined ? 0 : tdbuttons.attr('data-idOficina');
    tmpObjCheque.idTipMoneda = tdbuttons.attr('data-idTipMoneda') === undefined ? 0 : tdbuttons.attr('data-idTipMoneda');
    
    objCheque = tmpObjCheque;

    

    
    $("#txtModalAnularMotivo").val('');
    $("#modalChequeAnular").modal("show");

}





