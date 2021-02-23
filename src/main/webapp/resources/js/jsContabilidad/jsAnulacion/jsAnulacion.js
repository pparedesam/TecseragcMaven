
combotable('cboDocumento');

$('#txtFecha').datepicker('setDate', ObtenerFechaActualDMY());
$("#txtFecha").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '1900:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});




$('#btnBuscarFactura').click(function (e) {
    e.preventDefault();


    var validator = $("#form-registrarFactura").valid();
    if (validator) {
        var nrofactura = $('#txtFactura').val();
        var iddoc = $('#cboDocumento').val();


        obtenerFactura(nrofactura, iddoc);
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');



});

function obtenerFactura(nrofactura, iddoc) {
    var data = {'nroFactura': nrofactura, 'idDoc': iddoc};
    var success = function (res) {
        if (res.Result == 'OK') {
            document.getElementById("cboDocumento").disabled = true;
            document.getElementById("btnBuscarFactura").disabled = true;
            document.getElementById("txtFactura").disabled = true;
            var fechaAdq = res.CabeceraFact.fechaDocumento;
            var documento = res.CabeceraFact.idOficina + '-' + res.CabeceraFact.idDoc + '-' + res.CabeceraFact.nroDoc + '-' + res.CabeceraFact.tipMoneda;

            if (res.CabeceraFact.tipMoneda === '1') {
                $("#txtTipoMoneda").val("SOLES");
            } else if (res.CabeceraFact.tipMoneda === '2') {
                $("#txtTipoMoneda").val("DOLARES");
            }

            if (res.CabeceraFact.idDoc === '0001') {
                $("#txtTipoComprobante").val("FACTURA");
            } else if (res.CabeceraFact.idDoc === '0004') {
                $("#txtTipoComprobante").val("NOTA CREDITO");
            } else if (res.CabeceraFact.idDoc === '0008') {
                $("#txtTipoComprobante").val("NOTA DEBITO");
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

            $("#tabFactura").show();
            cargarDetalleFactura(res);
            cargarListaGuiaRemision(res);



        } else if (res.Result == 'error') {
            toast('error', res.Message, 'ERROR!');
        }
    };
    var error = function () {
        toast('error', 'Error , Consulte con el Area de TI', 'ERROR!');
    };

    fn_callmethod("../sAnulacion?action=obtenerCabFacturaAnulacion", data, success, error);
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
    }
}



function cargarDetalleFactura(res) {
    $('#tblCarBien').dataTable().fnUpdate();

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

}

function registrarDocBaja() {

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
        'TipoNota': $("#cboTipoNota").text(),
        'observacion': $("#txtobservacion").val(),
        'fecha': $("#txtFecha").val(),
        'detalleMotivo': $("#txtDetalleMotivo").val().toUpperCase()
    }
    ;
    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.result.Message === 'OK') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                setTimeout("location.reload(true);", 5000);

            }
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
            setTimeout("location.reload(true);", 3000);

        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
        setTimeout("location.reload(true);", 3000);
    };
    bootbox.confirm("\u00BFEst\u00E1 seguro que desea Registrar a este Documento de Venta\u003F", function (result) {
        if (result) {
            fn_callmethod("../sAnulacion?action=baja", data, success, error);
        }
    });
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
            registrarDocBaja();
        }
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');

});

