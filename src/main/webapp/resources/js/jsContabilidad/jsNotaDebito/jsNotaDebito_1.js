var varible = $("#txtVariable").val();
if (varible !== undefined)
    TabSelec(varible);

function TabSelec(idtab)
{
    $('#tabs a[href="#' + idtab + '"]').tab('show');
    $("#tabs a").click(function () {
        $("[id*=" + idtab + "]").val($(this).attr("href").replace("#", ""));
    });
    $("#txtVariable").val(idtab);
}
;
combotable('cboTipoNota');

$('#btnLimpiarNotaCredito').click(function (e) {
    location.reload();
});

$('#btnBuscarFactura').click(function (e) {
    e.preventDefault();

    var validator = $("#form-registrarFactura").valid();
    if (validator) {
        var criterio = $('#cboTipoNota').val();
        obtenerFactura(criterio);
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');

});

function obtenerFactura(criterio) {
    var data = {'nroFactura': $("#txtFactura").val()};
    var success = function (res) {
        if (res.Result == 'OK') {

            switch (criterio)
            {
                case '02':
                    document.getElementById("cboTipoNota").disabled = true;
                    document.getElementById("btnBuscarFactura").disabled = true;
                    document.getElementById("txtFactura").disabled = true;
                    var fechaAdq = res.CabeceraFact.fechaDocumento;
                    var documento = res.CabeceraFact.idOficina + '-' + res.CabeceraFact.idDoc + '-' + res.CabeceraFact.nroDoc + '-' + res.CabeceraFact.tipMoneda;

                    if (res.CabeceraFact.tipMoneda = '1') {
                        $("#txtTipoMoneda").val("SOLES");
                    } else if (res.CabeceraFact.tipMoneda = '2') {
                        $("#txtTipoMoneda").val("DOLARES");
                    }
                    if (res.CabeceraFact.listDetalleFact[0].IdTipDocSUNAT = '01') {
                        $("#txtTipoComprobante").val("FACTURA");
                    }
                    $("#txtRUCEmisor").val($.trim(res.CabeceraFact.objEmisor.nroDocumento));
                    $("#txtRazonSocialEmisor").val($.trim(res.CabeceraFact.objEmisor.nombres));
                    $("#txtidPersonaEmisor").val($.trim(res.CabeceraFact.objEmisor.idPersona));
                    $("#txtRUC").val($.trim(res.CabeceraFact.objEjecutor.nroDocumento));
                    $("#txtRazonSocial").val($.trim(res.CabeceraFact.objEjecutor.nombres));
                    $("#txtidPersonaCliente").val($.trim(res.CabeceraFact.objEjecutor.idPersona));
                    $("#txtNumFactura").val($.trim(res.CabeceraFact.nroDocExt));

                    $("#txtDocumento").val($.trim(documento));

                    $("#txtFecFac").datepicker("update", fechaAdq);

                    $('#txtFecAdq').datepicker('setDate', ObtenerFechaActualDMY());
                    $("#txtSubTotal").val($.trim(res.CabeceraFact.valorV));
                    $("#txtIGV").val($.trim(res.CabeceraFact.igv));
                    $("#txtTotal").val(res.CabeceraFact.total);
                    $("#txtobservacion").val(res.CabeceraFact.observacion);

                    cargarListaGuiaRemision(criterio, res);
                    break;

                case '03':

                    $("#Penalidad").show();
                    document.getElementById("cboTipoNota").disabled = true;
                    document.getElementById("btnBuscarFactura").disabled = true;
                    document.getElementById("txtFactura").disabled = true;
                    var fechaAdq = res.CabeceraFact.fechaDocumento;
                    var documento = res.CabeceraFact.idOficina + '-' + res.CabeceraFact.idDoc + '-' + res.CabeceraFact.nroDoc + '-' + res.CabeceraFact.tipMoneda;

                    if (res.CabeceraFact.tipMoneda = '1') {
                        $("#txtTipoMoneda").val("SOLES");
                    } else if (res.CabeceraFact.tipMoneda = '2') {
                        $("#txtTipoMoneda").val("DOLARES");
                    }
                    if (res.CabeceraFact.listDetalleFact[0].IdTipDocSUNAT = '01') {
                        $("#txtTipoComprobante").val("FACTURA");
                    }
                    $("#txtRUCEmisor").val($.trim(res.CabeceraFact.objEmisor.nroDocumento));
                    $("#txtRazonSocialEmisor").val($.trim(res.CabeceraFact.objEmisor.nombres));
                    $("#txtidPersonaEmisor").val($.trim(res.CabeceraFact.objEmisor.idPersona));
                    $("#txtRUC").val($.trim(res.CabeceraFact.objEjecutor.nroDocumento));
                    $("#txtRazonSocial").val($.trim(res.CabeceraFact.objEjecutor.nombres));
                    $("#txtidPersonaCliente").val($.trim(res.CabeceraFact.objEjecutor.idPersona));
                    $("#txtNumFactura").val($.trim(res.CabeceraFact.nroDocExt));

                    $("#txtDocumento").val($.trim(documento));

                    $("#txtFecFac").datepicker("update", fechaAdq);

                    $('#txtFecAdq').datepicker('setDate', ObtenerFechaActualDMY());
                    $("#txtSubTotal").val($.trim(res.CabeceraFact.valorV));
                    $("#txtIGV").val($.trim(res.CabeceraFact.igv));
                    $("#txtTotal").val(res.CabeceraFact.total);
                    $("#txtobservacion").val(res.CabeceraFact.observacion);


                    cargarListaGuiaRemision(criterio, res);
                    break;
            }



        } else if (res.Result == 'error') {
            toast('error', res.Message, 'ERROR!');
        }
    };
    var error = function () {
        toast('error', 'Error , Consulte con el Area de TI', 'ERROR!');
    };

    fn_callmethod("../sNotaDebito?action=obtenerCabFactura", data, success, error);
}
;


$('#btnCargar').click(function (e) {

    e.preventDefault();
    $("#tabFactura").show();
    document.getElementById("btnCargar").disabled = true;
    document.getElementById("txtInteresxMora").disabled = true;
    document.getElementById("txtDetalleMotivoxMora").disabled = true;
    $('#txtDetalleMotivo').val($('#txtDetalleMotivoxMora').val().toUpperCase());
    $("#tabFactura").show();

    var data = {'descripcion': $('#txtDetalleMotivoxMora').val().toUpperCase(), 'valorunitario': roundN($('#txtInteresxMora').val(), 2)};
    var success = function (res) {

        if (res.Result === 'OK') {

            document.getElementById("txtTotal").value = roundN(res.total, 2);
            document.getElementById("txtSubTotal").value = roundN(res.subtotal, 2)
            document.getElementById("txtIGV").value = roundN(res.igv, 2);
            document.getElementById("txtDetFact").value = '';
            document.getElementById("txtImpFact").value = '';
            document.getElementById("txtValUnit").value = '';
            document.getElementById("txtIGVDet").value = '';
            document.getElementById("txtPreUniFact").value = '';
            document.getElementById("txtCantFact").value = '1';

            cargarDetalleFactura("03", res);


        } else if (res.Result === 'KO') {
            toast('error', "No se puede repetir una descripcion ya ingresada", 'Error');
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sNotaDebito?action=DetallePenalidad", data, success, error);

});


function actualizarTablaBien(nombreTabla) {


    var data = {'descripcion': $('#txtDetalleMotivoxMora').val().toUpperCase(), 'valorunitario': roundN($('#txtInteresxMora').val(), 2)};
    var success = function (res) {

        if (res.Result === 'OK') {

            document.getElementById("txtTotal").value = roundN(res.total, 2);
            document.getElementById("txtSubTotal").value = roundN(res.subtotal, 2)
            document.getElementById("txtIGV").value = roundN(res.igv, 2);
            document.getElementById("txtDetFact").value = '';
            document.getElementById("txtImpFact").value = '';
            document.getElementById("txtValUnit").value = '';
            document.getElementById("txtIGVDet").value = '';
            document.getElementById("txtPreUniFact").value = '';
            document.getElementById("txtCantFact").value = '1';



            $(nombreTabla).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.detalleFactura,
                rowId: 'idTipoServicio', //asigna el id a la fila
                "columns": [
                    {"data": "cantidad"},
                    {"data": "uniMedida"},
                    {"data": "descripcion"}, //, "visible": false},
                    {"data": "valorunitario"},
                    {"data": "importe"},
                    {"data": "igv"}, {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(6)").attr('data-id', data.descripcion);
                    $(row).find("td:eq(6)").attr('data-idUM', data.UM);

                }
            });
        } else if (res.Result === 'KO') {
            toast('error', "No se puede repetir una descripcion ya ingresada", 'Error');
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sNotaDebito?action=DetallePenalidad", data, success, error);

}
;


$('#btnRegistrarFacturaServ').click(function (e) {
    e.preventDefault();
    var validator = $("#form-registrarFactura").valid();
    if (validator) {
        if (document.getElementById("txtTotal").value <= 0.00) {
            toast('warning', "USTED DEBE AGREGAR DETALLE A LA FACTURA", 'warning');

        } else
        {
            registrarNotaDebito();
        }
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');

});

function registrarNotaDebito() {

    var id_codigo = $("#txtDocumento").val();
    separador = "-", // un espacio en blanco
            arregloDeSubCadenas = id_codigo.split(separador);
    var idoficinaR = arregloDeSubCadenas[0];
    var idDocR = arregloDeSubCadenas[1];
    var nroDocR = arregloDeSubCadenas[2];
    var tipMonedaR = arregloDeSubCadenas[3];

    var data = {

        'idPersonaEmisor': $("#txtidPersonaEmisor").val(),
        'idPersonaCliente': $("#txtidPersonaCliente").val(),
        'fechaDoc': $("#txtFecAdq").val(),
        'tipoMoneda': tipMonedaR,
        'idoficinaR': idoficinaR,
        'idDocR': idDocR,
        'nroDocR': nroDocR,
        'tipMonedaR': tipMonedaR,
        'idTipoNota': $("#cboTipoNota").val(),
        'TipoNota': $('#cboTipoNota').find('option:selected').text().trim(),
        'observacion': $("#txtobservacion").val(),
        'detalleMotivo': $("#txtDetalleMotivo").val().toUpperCase()
    }
    ;
    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.result.Message === 'OK') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');

//                abrirVentana(svrbd + '?%2fPyContabilidad%2frptNotaCredito&rs:Command=Render&' +
//                        'nroDoc=' + res.result.nroDoc +
//                        '&idDoc=' + '0008' +
//                        '&idOficina=' + '01' +
//                        '&tipMoneda=' + res.result.tipMoneda);
                setTimeout("location.reload(true);", 5000);
            }
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
            setTimeout("location.reload(true);", 100);

        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
        setTimeout("location.reload(true);", 100);
    };
    bootbox.confirm("\u00BFEst\u00E1 seguro que desea Registrar a este Documento de Venta\u003F", function (result) {
        if (result) {
            fn_callmethod("../sNotaDebito?action=registrarNotaDebito", data, success, error);
        }
    });
}
;

function cargarListaGuiaRemision(criterio, res) {
    cargarDetalleFactura(criterio, res);
    $('#tblGuiaRemision').dataTable().fnUpdate();
    switch (criterio) {
        case '01':
            $("#tabFactura").show();
            $(tblGuiaRemision).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaGuiaRemision,
                rowId: 'TipGuia', //asigna el id a la fila
                "columns": [
                    {"data": "DescripcionGuia"}, //, "visible": false},
                    {"data": "NroGuia"}, //, "visible": false},
                    {"data": "Fecha"}, //, "visible": false},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'></a></td>");
                            //$(nTd).html("<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    // $(row).find("td:eq(2)").attr('data-id', data.IdDescripcion);

                }
            });
            break;
        case '02':
            $("#tabFactura").show();
            $(tblGuiaRemision).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaGuiaRemision,
                rowId: 'TipGuia', //asigna el id a la fila
                "columns": [
                    {"data": "DescripcionGuia"}, //, "visible": false},
                    {"data": "NroGuia"}, //, "visible": false},
                    {"data": "Fecha"}, //, "visible": false},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'></a></td>");
                            //$(nTd).html("<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    // $(row).find("td:eq(2)").attr('data-id', data.IdDescripcion);

                }
            });
            break;

    }
}

function cargarDetalleFactura(criterio, res) {
    $('#tblCarBien').dataTable().fnUpdate();
    switch (criterio) {
        case '01':
            $("#tabFactura").show();
            $(tblCarBien).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.DetalleFactura,
                rowId: 'IdDescripcion', //asigna el id a la fila
                "columns": [

                    {"data": "Cantidad"}, //, "visible": false},
                    {"data": "UMdes"}, //, "visible": false},
                    {"data": "Descripcion"}, //, "visible": false},
                    {"data": "ValorUnitario"}, //, "visible": false},
                    {"data": "Monto"},
                    {"data": "IGV"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'></a></td>");
                            //$(nTd).html("<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    // $(row).find("td:eq(2)").attr('data-id', data.IdDescripcion);

                }
            });
            break;
        case '02':
            $("#tabFactura").show();
            document.getElementById("txtImpFact").disabled = false;
            document.getElementById("txtIGVDet").disabled = false;
            $(tblCarBien).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.DetalleFactura,
                rowId: 'IdDescripcion', //asigna el id a la fila
                "columns": [

                    {"data": "Cantidad"}, //, "visible": false},
                    {"data": "UMdes"}, //, "visible": false},
                    {"data": "Descripcion"}, //, "visible": false},
                    {"data": "ValorUnitario"}, //, "visible": false},
                    {"data": "Monto"},
                    {"data": "IGV"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            //$(nTd).html("<a href='javascript:;'></a></td>");
                            $(nTd).html("<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(0)").attr('data-id', data.IdDescripcion);
                    $(row).find("td:eq(0)").attr('data-detalle', data.Detalle);
                    $(row).find("td:eq(3)").attr('data-index', data.Indice);

                }
            });
            break;

        case '03':

            document.getElementById("txtImpFact").disabled = false;
            document.getElementById("txtIGVDet").disabled = false;
            $(tblCarBien).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.DetalleFactura,
                rowId: 'IdDescripcion', //asigna el id a la fila
                "columns": [

                    {"data": "Cantidad"}, //, "visible": false},
                    {"data": "UMdes"}, //, "visible": false},
                    {"data": "Descripcion"}, //, "visible": false},
                    {"data": "ValorUnitario"}, //, "visible": false},
                    {"data": "Monto"},
                    {"data": "IGV"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            //$(nTd).html("<a href='javascript:;'></a></td>");
                            $(nTd).html("<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(0)").attr('data-id', data.IdDescripcion);
                    $(row).find("td:eq(0)").attr('data-detalle', data.Detalle);
                    $(row).find("td:eq(3)").attr('data-index', data.Indice);

                }
            });
            break;

    }

}




$('#txtImpFact').bind('keyup', function (e) {

    var importe = $("#txtImpFact").val();
    var suma = roundN(importe * 0.18, 2);
    document.getElementById('txtIGVDet').value = suma.toString();

});

function roundN(num, n) {
    return parseFloat(Math.round(num * Math.pow(10, n)) / Math.pow(10, n)).toFixed(n);
}


var oTable;

oTable = fn_iniciarSinDT(oTable, "tblCarBien");
oTable.on("click", ".edit", function () {
    var elem = this;
    fn_verItem(elem);


});

var fn_verItem = function (elem) {

    $("#modalModCaracteristicas").modal();

    var par = $(elem).parent().parent();

    var cantidad = par.children("td:nth-child(1)");
    var unidadmedida = par.children("td:nth-child(2)");
    var descripcion = par.children("td:nth-child(3)");
    var valorunitario = par.children("td:nth-child(4)");
    var importe = par.children("td:nth-child(5)");
    var igv = par.children("td:nth-child(6)");
    var preciounitario = roundN(valorunitario.html(), 2) * 1.18;


    $("#txtCantFactEdit").val(cantidad.html());
    $("#txtTipoServicioEdit").val(descripcion.html());
    $("#txtUnidadMedidaEdit").val(unidadmedida.html());
    $("#txtValUnitEdit").val(valorunitario.html());
    $("#txtImpFactEdit").val(importe.html());
    $("#txtIGVDetEdit").val(igv.html());
    $("#txtPreUniFactEdit").val(roundN(preciounitario, 2));
    $("#txtDescAnt").val(descripcion.html());

};



$('#frmCaractBienEdit').submit(function (event) {
    event.preventDefault();
    var validator = $("#frmCaractBienEdit").valid();
    if (validator) {
        actualizarItem();
        $('#modalModCaracteristicas').modal('toggle');
        // clearmodalupdate();

    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');



});




function actualizarItem() {
//    var data = {'idTipoServicio': $("#cboTipoServicio").val(), 'igv': $("#txtIGVDet").val(), 'importe': $("#txtImpFact").val(), 'servicio': $('#cboTipoServicio').find('option:selected').text().toUpperCase(),
//        'detalle': $("#txtDetFact").val().toUpperCase(), 'indice': $("#txtindice").val()};
    var data = {'detalleant': $('#txtDescAnt').val(),
        'UM': $('#txtUnidadMedidaEdit').val(),
        'detalle': $('#txtTipoServicioEdit').val(),
        'preciounitario': $('#txtPreUniFactEdit').val(),
        'valorunitario': $('#txtValUnitEdit').val(),
        'importe': $('#txtImpFactEdit').val(),
        'igv': $('#txtIGVDetEdit').val(),
        'cantidad': $('#txtCantFactEdit').val(),
        'subTotal': $('#txtSubTotal').val(),
        'igvTotal': $('#txtIGV').val()
    };
    var success = function (res) {
        if (res.Result == 'OK') {
            $("#txtSubTotal").val(res.subtotal, 2);
            $("#txtIGV").val(roundN(res.igv, 2));
            $("#txtTotal").val(roundN(res.total, 2));
            cargarDetalleFactura("02", res);
        } else if (res.Result == 'error') {
            toast('error', res.Message, 'ERROR!');
        }
    };
    var error = function () {
        toast('error', 'Error , Consulte con el Area de TI', 'ERROR!');
    };
    fn_callmethod("../sNotaDebito?action=actualizarItem", data, success, error);

}

$("#txtRUC").keypress(function (e) {
    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;

    if (key == 8) {
        if ($("#txtRuc").val().length !== 0) {
        }
    }
});

$("#txtRUC").autocomplete({
    source: function (request, response) {
        var data = {txtruc: request.term};
        var success = function (res) {
            if (res.Result === 'OK') {
                response($.map(res.lista, function (lista) {
                    return {
                        label: lista.nroDocumento,
                        value: lista.idPersona,
                        direccion: lista.direccion,
                        razonsocial: lista.nombres
                    };
                }));
            } else {
                toast('error', res.mensaje, 'Error');
            }
        };
        var error = function (res) {
            toast('error', res.mensaje, 'Error');
        };
        fn_callmethod("../sPersonaJuridica?action=PersonaJuridicaText", data, success, error);
    },
    focus: function (event, ui) {
        event.preventDefault();
        $("#txtRUC").val(ui.item.label);
        $("#txtidPersonaCliente").val(ui.item.value ? ui.item.value : "");
        $("#txtDireccion").val(ui.item.direccion ? ui.item.direccion : "");
        $("#txtRazonSocial").val(ui.item.razonsocial ? ui.item.razonsocial : "");
    },
    select: function (event, ui) {
        $("#txtRUC").val(ui.item.label);
        $("#txtidPersonaCliente").val(ui.item.value ? ui.item.value : "");
        $("#txtDireccion").val(ui.item.direccion ? ui.item.direccion : "");
        $("#txtRazonSocial").val(ui.item.razonsocial ? ui.item.razonsocial : "");
        return false;
    }
});



function toggle(elemento) {
    if (elemento.value == "1") {


        document.getElementById("txtFechaDesde").value = '';
        document.getElementById("txtFechaHasta").value = '';
        document.getElementById("txtFechaDesde").disabled = false;
        document.getElementById("txtFechaHasta").disabled = false;
        document.getElementById("txtBuscarNroDoc").disabled = true;


    } else {
        if (elemento.value == "2") {
            document.getElementById("txtFechaDesde").disabled = true;
            document.getElementById("txtFechaHasta").disabled = true;
            document.getElementById("txtBuscarNroDoc").disabled = false;
            document.getElementById("txtBuscarNroDoc").value = '';

        }
    }
}
;

$("#txtFechaDesde").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '1900:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});

$("#txtFechaHasta").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '1900:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});


var oTableBF;
$(function () {
    oTableBF = fn_iniciarDT(oTableBF, "tbl_PersonaJuridica");

    oTableBF.on("click", ".remove", function () {
        fn_Remove(this);
    });

    function fn_Remove(elem) {
        var par = $(elem).parent().parent();
        var tdbuttons = par.children("td:nth-child(6)");
        var id_codigo = tdbuttons.attr("data-id");
        separador = "&", // un espacio en blanco
                arregloDeSubCadenas = id_codigo.split(separador);
        var idoficina = arregloDeSubCadenas[0];
        var IdDoc = arregloDeSubCadenas[1];
        var TipMoneda = arregloDeSubCadenas[2];
        var NroDoc = arregloDeSubCadenas[3];
        abrirVentana(svrbd + '?%2fPyContabilidad%2frptNotaCreditoElectronica&rs:Command=Render&' +
                'nroDoc=' + NroDoc +
                '&idDoc=' + IdDoc +
                '&idOficina=' + idoficina +
                '&tipMoneda=' + TipMoneda);

    }
    ;

    oTableBF.on("click", ".info", function () {
        fn_Info(this);
    });

    function fn_Info(elem) {

        var par = $(elem).parent().parent();
        var tdbuttons = par.children("td:nth-child(7)");
        var id_codigo = tdbuttons.attr("data-idFac");

        var data = {

            'nroFactura': id_codigo

        };
        var success = function (res) {

            if (res.Result === 'OK') {
                $('#txtResponse').val(res.response);
            } else if (res.Result === 'error') {
                toast('error', res.Message, 'Error!');
            }

        };
        var error = function (res) {
            $('#txtResponse').val('');
            toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
        };

        fn_callmethod("../sNotaDebito?action=obtenerRsptaSUNAT", data, success, error);

        $("#modal-infoFactura").modal();

    }
    ;

});


$('#txtCantFact').bind('keyup', function (e) {


    var valunit = $("#txtValUnit").val();
    var importe = $("#txtPreUniFact").val();
    var cantidad = $("#txtCantFact").val();

    importe = roundN((valunit * 1.18), 2);
    document.getElementById('txtPreUniFact').value = importe;
    var suma = roundN((valunit * cantidad), 2);
    document.getElementById('txtImpFact').value = suma.toString();
    var igv = roundN((importe * cantidad) - suma, 2);
    document.getElementById('txtIGVDet').value = igv.toString();
});


$('#txtValUnit').bind('keyup', function (e) {

    var valunit = $("#txtValUnit").val();
    var importe = $("#txtPreUniFact").val();
    var cantidad = $("#txtCantFact").val();

    importe = roundN((valunit * 1.18), 2);
    document.getElementById('txtPreUniFact').value = importe;
    var suma = roundN((valunit * cantidad), 2);
    document.getElementById('txtImpFact').value = suma.toString();
    var igv = roundN((importe * cantidad) - suma, 2);
    document.getElementById('txtIGVDet').value = igv.toString();


});



$('#txtCantFactEdit').bind('keyup', function (e) {


    var valunit = $("#txtValUnitEdit").val();
    var importe = $("#txtPreUniFactEdit").val();
    var cantidad = $("#txtCantFactEdit").val();

    importe = roundN((valunit * 1.18), 2);
    document.getElementById('txtPreUniFactEdit').value = importe;
    var suma = roundN((valunit * cantidad), 2);
    document.getElementById('txtImpFactEdit').value = suma.toString();
    var igv = roundN((importe * cantidad) - suma, 2);
    document.getElementById('txtIGVDetEdit').value = igv.toString();
});


$('#txtValUnitEdit').bind('keyup', function (e) {

    var valunit = $("#txtValUnitEdit").val();
    var importe = $("#txtPreUniFactEdit").val();
    var cantidad = $("#txtCantFactEdit").val();

    importe = roundN((valunit * 1.18), 2);
    document.getElementById('txtPreUniFactEdit').value = importe;
    var suma = roundN((valunit * cantidad), 2);
    document.getElementById('txtImpFactEdit').value = suma.toString();
    var igv = roundN((importe * cantidad) - suma, 2);
    document.getElementById('txtIGVDetEdit').value = igv.toString();


});

$('#txtSubTotal').bind('keyup', function (e) {

    var subTotal = $("#txtSubTotal").val();
    var igv = $("#txtIGV").val();
    var suma = roundN(parseFloat(subTotal) + parseFloat(igv), 2);
    document.getElementById('txtTotal').value = suma;



});

$('#txtIGV').bind('keyup', function (e) {

    var subTotal = roundN($("#txtSubTotal").val(), 2);
    var igv = roundN($("#txtIGV").val(), 2);
    var suma = roundN(parseFloat(subTotal) + parseFloat(igv), 2);
    document.getElementById('txtTotal').value = suma;

});
