$("#txtConvenio").keypress(function (e) {
     var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;

    if (key == 8) {
        if ($("#txtConvenio").val().length !== 0) {
            LimpiartablaApoderadoConvenioPlanilla(); 
            LimpiartablaIntegranteConvenioPlanilla();        }
    }
});

function LimpiartablaApoderadoConvenioPlanilla() {
    $("#tabla_ConApo").hide();
    $("#txtConvenio").val('');
    $("#txtConvenioId").val('');
    $('#tbl_ConvenioApoderado').empty();
     $('#btnAgregar').prop("disabled", false);

}
function LimpiartablaIntegranteConvenioPlanilla() {
    $("#tabla_IntegrantePlanilla").hide();
    $('#tbl_IntegrantePlanilla').empty();

}

$("#txtConvenio").autocomplete({

    source: function (request, response) {

        var data = {txtconvenio: request.term};
        var success = function (res) {
            if (res.Result === 'OK') {
                response($.map(res.lista, function (lista) {
                    return {
                        label: lista.nomProducto,
                        value: lista.idTipProducto
                    };
                }));
            } else {
                toast('error', res.mensaje, 'Error');
            }
        };

        var error = function (res) {
            toast('error', res.mensaje, 'Error');
        };
        fn_callmethod("../sProductoCrediticio?action=ProductoCrediticioText", data, success, error);
    },
    focus: function (event, ui) {
        event.preventDefault();
        $("#txtConvenio").val(ui.item.label);
        $("#txtConvenioId").val(ui.item.value ? ui.item.value : "");
    },
    select: function (event, ui) {

        $("#txtConvenio").val(ui.item.label);
        $("#txtConvenioId").val(ui.item.value ? ui.item.value : "");
        return false;
    }

});


$("#txtConvenio").keypress(function (event) {    
    if (event.which === 13 || event.which === event.ENTER) {
        $('#btnAgregar').prop("disabled", false);
        $('.alert').show().html('');
        GenerarTabla($("#txtConvenioId").val());
    }

});


function GenerarTabla(idconvernio) {
    var data = {'IdConvenio': idconvernio};
    var success = function (res) {


        if (res.Result === 'OK') {
            $("#tabla_ConApo").show();
            
            var otableCA =
                    $('#tbl_ConvenioApoderado').DataTable({                   
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"},
                    {orderable: false, "targets": [0]},
                    {orderable: false, "targets": "_all"}
                ],
                
                "aaData": res.listApoderadoPlanillas,
                "columns": [
                    {"data": "objSocio.idSocio"},
                    {"data": "idCodigoPlanilla"},
                    {"data": "objSocio.objPersonaNatural.nombres"},
                    {"data": "idTipoPlanilla"},
                    {"data": "objCrediticio.nomProducto"},
                    {"data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html(
                                    
                                     "<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a>"
                                    );

                        }},
                    {"data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html(
                                    "<a href='javascript:;'  class='btn btn-HippieGreen btn-circle add' title='Aregar'><i class='fa fa-plus'></i></a>"
                                    );

                        }}
                ],
                createdRow: function (row, data, indice) {
                   
                    $(row).find("td:eq(4)").attr('data-co', data. objCrediticio.idTipProducto);
                    $(row).find("td:eq(5)").attr('data-id', data.idPersonaApoderado);
                }
            });

        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sApoderadoPlanillaConvenio?action=obtenerApoderadosPlanilla", data, success, error);

}
