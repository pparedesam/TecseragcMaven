function GenerarTablaIntegrantePlanillaConvenio(idConvenio,idApoderado) {
    var data = {'idConvenio': idConvenio,'idApoderado':idApoderado};
    var success = function (res) {


        if (res.Result === 'OK') {
            
           
            $("#tabla_IntegrantePlanilla").show();
            
           
                    $('#tbl_IntegrantePlanilla').DataTable({                   
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"},
                    {orderable: false, "targets": [0]},
                    {orderable: false, "targets": "_all"}
                ],
                "aaData": res.listIntegrantesPlanilla,
                "columns": [
                    {"data": "objSocio.idSocio"},
                    {"data": "objSocio.objPersonaNatural.nombres"},
                    {"data": "objApoderadoPlanillaConvenio.objSocio.objPersonaNatural.nombres"},
                    {"data": "aporte",
                        render: function (data, type, row) {
                            return data == 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>';
                        },
                        className: "dt-body-center"
                    },
                    {"data": "fondoMortuorio",
                        render: function (data, type, row) {
                            return data == 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>';
                        },
                        className: "dt-body-center"
                    },
                    {"data": "prestamo",
                        render: function (data, type, row) {
                            return data == 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>';
                        },
                        className: "dt-body-center"
                    },
                    {"data": "objApoderadoPlanillaConvenio.objCrediticio.nomProducto"},
                    {"data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html(                                    
                                    "<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a>\n"+
                                    "<a href='javascript:;'  class='btn btn-danger  btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>"
                                    );

                        }}
                ],
                createdRow: function (row, data, indice) {
                 
                    $(row).find("td:eq(7)").attr('data-co', data.objApoderadoPlanillaConvenio.objCrediticio.idTipProducto);                    
                    $(row).find("td:eq(7)").attr('data-ap', data.objApoderadoPlanillaConvenio.objSocio.objPersonaNatural.idPersona);
                    $(row).find("td:eq(7)").attr('data-id', data.objSocio.objPersonaNatural.idPersona);
                }
                
            });

        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sIntegrantePlanillaConvenio?action=obtenerIntegrantePlanilla", data, success, error);

}

