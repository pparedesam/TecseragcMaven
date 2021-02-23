var oTable;

oTable = fn_iniciarDT(oTable, "tblListaPersonas");


var muestraInformacionSocio = function (listSocios) {    
    
           $('#tblListaPersonas').DataTable({
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
                    $(row).find("td:eq(4)").attr('data-id', data.objPersona.idPersona);                    
                }
            });
    };
   


