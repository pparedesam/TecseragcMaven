var oTable;

oTable = fn_iniciarDT(oTable, "tblListaPersonas");


var muestraInformacionSocio = function (listSocios) {    
    
           $('#tblListaPersonas').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"},
                    {"className": "dt-body-center", "targets": "_all"},
                    {"width": "10%", "targets": [0,1,3,4]}
                ],
                "aaData": listSocios,
                "columns": [
                    {"data": "idSocio"},
                    {"data": "objPersona.nroDocumento"},
                    {"data": "objPersona.nombres"},  
                    {"data": "objEstadoSocio.descripcion"},
                    {"data": "convenio"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html(                               
                                "<a class='btn btn-Lapislazuli btn-circle show_ec' title='ver Cuentas'><i class='fa fa-eye'></i></a>");
                        }}
                ],         
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(5)").attr('data-id', data.objPersona.idPersona);                    
                }
            });
    };
   


