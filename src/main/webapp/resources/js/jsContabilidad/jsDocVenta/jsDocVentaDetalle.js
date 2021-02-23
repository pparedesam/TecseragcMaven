/* global parseFloat */



$('#btnAgregarCaracteristica').click(function (event) {

    $("#modalCaracteristicas").modal();

});

function setModalMaxHeight(element) {
    this.$element = $(element);
    this.$content = this.$element.find('.modal-content');
    var borderWidth = this.$content.outerHeight() - this.$content.innerHeight();
    var dialogMargin = $(window).width() < 768 ? 20 : 60;
    var contentHeight = $(window).height() - (dialogMargin + borderWidth);
    var headerHeight = this.$element.find('.modal-header').outerHeight() || 0;
    var footerHeight = this.$element.find('.modal-footer').outerHeight() || 0;
    var maxHeight = contentHeight - (headerHeight + footerHeight);

    this.$content.css({
        'overflow': 'hidden'
    });

    this.$element
            .find('.modal-body').css({
        'max-height': maxHeight,
        'overflow-y': 'auto'
    });
}

$('.modal').on('show.bs.modal', function () {
    $(this).show();
    setModalMaxHeight(this);
});

$(window).resize(function () {
    if ($('.modal.in').length != 0) {
        setModalMaxHeight($('.modal.in'));
    }
});


$('#frmCaractBien').submit(function (event) {
    event.preventDefault();
    var validator = $("#frmCaractBien").valid();
    if (validator) {
        registrarCarateristicaVenta();
        $('#modalCaracteristicas').modal('toggle');
        clearmodalcaracteristicas();

    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');



});

$('#frmCaractBienEdit').submit(function (event) {
    event.preventDefault();
    var validator = $("#frmCaractBienEdit").valid();
    if (validator) {
        modificarCarateristicaVenta();
        $('#modalModCaracteristicas').modal('toggle');
        clearmodalupdate();

    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');



});


function modificarCarateristicaVenta() {


    var data = {'detalleant': $('#txtDescAnt').val(),
        'UM': $('#txtUnidadMedidaEdit').val(), 'detalle': $('#txtTipoServicioEdit').val(), 'preciounitario': $('#txtPreUniFactEdit').val(), 'valorunitario': $('#txtValUnitEdit').val(), 'importe': $('#txtImpFactEdit').val(), 'igv': $('#txtIGVDetEdit').val(), 'cantidad': $('#txtCantFactEdit').val()
    };
    actualizarTablaBien(data, '#tblCarBien', 3);

}


function registrarCarateristicaVenta() {

    var data = {
        'idTipoServicio': $('#cboTipoServicio').val(), 'servicio': $('#cboTipoServicio').find('option:selected').text(), 'uniMedida': $('#cboUnidadMedida').find('option:selected').text(), 'UM': $('#cboUnidadMedida').val(),
        'detalle': $('#txtDetFact').val(), 'preciounitario': $('#txtPreUniFact').val(), 'valorunitario': $('#txtValUnit').val(), 'importe': $('#txtImpFact').val(), 'igv': $('#txtIGVDet').val(), 'cantidad': $('#txtCantFact').val()
    };
    actualizarTablaBien(data, '#tblCarBien', 1);

}

function actualizarTablaBien(datos, nombreTabla, accion) {
    var descripcion;

    if (typeof datos.detalle === 'undefined') {
        descripcion = datos.servicio;
    } else {

        descripcion = datos.servicio + " " + datos.detalle.toUpperCase();
    }

    var data = {'idTipoServicio': datos.idTipoServicio, 'valorunitario': roundN(datos.valorunitario, 2), 'servicio': descripcion, 'uniMedida': datos.uniMedida, 'UM': datos.UM, 'preciounitario': roundN(datos.preciounitario, 2), 'importe': roundN(datos.importe, 2), 'igv': roundN(datos.igv, 2), 'cantidad': datos.cantidad};
    var data2 = {'idTipoServicio': datos.idTipoServicio, 'valorunitario': roundN(datos.valorunitario, 2), 'servicio': descripcion, 'uniMedida': datos.uniMedida, 'UM': datos.UM, 'preciounitario': datos.preciounitario, 'importe': datos.importe, 'igv': datos.igv, 'cantidad': datos.cantidad}
    var data3 = {'detalleant': datos.detalleant, 'valorunitario': roundN(datos.valorunitario, 2), 'servicio': descripcion, 'preciounitario': roundN(datos.preciounitario, 2), 'importe': roundN(datos.importe, 2), 'igv': roundN(datos.igv, 2), 'cantidad': datos.cantidad};
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
                            $(nTd).html("<a href='javascript:;' class='btn btn-Lapislazuli btn-circle edit' title='Editar'> <i class='fa fa-edit'> </i></a>\n"
                                    + "<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
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
    if (accion == 1) {
        fn_callmethod("../sDocVenta?action=comprobarDetalleVenta", data, success, error);
    } else if (accion == 2) {
        fn_callmethod("../sDocVenta?action=quitarDetalleVenta", data2, success, error);
    } else if (accion == 3) {
        fn_callmethod("../sDocVenta?action=modificarDetalleVentaF", data3, success, error);
    }
}
;

function roundN(num, n) {
    return parseFloat(Math.round(num * Math.pow(10, n)) / Math.pow(10, n)).toFixed(n);
}


var oTable;

oTable = fn_iniciarSinDT(oTable, "tblCarBien");

oTable.on("click", ".remove", function () {
    var elem = this;
    oTable.fnDestroy();
    fn_DeleteBien(elem);
});


var fn_DeleteBien = function (elem) {
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(7)");
    var tdimporte = par.children("td:nth-child(5)");
    var tdigv = par.children("td:nth-child(6)");
    var importe = tdimporte.html();
    var igv = tdigv.html();
    var servicio = tdbuttons.attr('data-id');
    var data = {'servicio': servicio, 'importe': importe, 'igv': igv};
    actualizarTablaBien(data, "#tblCarBien", 2);

};

oTable.on("click", ".edit", function () {
    var elem = this;
    oTable.fnDestroy();
    fn_Edit(elem);
});


var fn_Edit = function (elem) {

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



function clearmodalcaracteristicas() {
    $("#txtDesCarBien").val("");
    $("#cboCaractBien").val('1').trigger('change.select2');

}
function clearmodalupdate() {
    $("#txtDesCarBien").val("");
    $("#cboCaractBien").val('1').trigger('change.select2');

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




$("#txtRUCEmisor").keypress(function (e) {
    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;

    if (key == 8) {
        if ($("#txtRUCEmisor").val().length !== 0) {
        }
    }
});


$("#txtRUCEmisor").autocomplete({

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
        $("#txtRUCEmisor").val(ui.item.label);
        $("#txtidPersonaEmisor").val(ui.item.value ? ui.item.value : "");
        $("#txtDireccionEmisor").val(ui.item.direccion ? ui.item.direccion : "");
        $("#txtRazonSocialEmisor").val(ui.item.razonsocial ? ui.item.razonsocial : "");


    },
    select: function (event, ui) {

        $("#txtRUCEmisor").val(ui.item.label);
        $("#txtIdPersonaEmisor").val(ui.item.value ? ui.item.value : "");
        $("#txtDireccionEmisor").val(ui.item.direccion ? ui.item.direccion : "");
        $("#txtRazonSocialEmisor").val(ui.item.razonsocial ? ui.item.razonsocial : "");
        return false;
    }

});



document.getElementById('checkboxCliente').onchange = function () {
    document.getElementById('txtRUC').disabled = !this.checked;
};



$('#txtCantFact').bind('keyup', function (e) {


    var valunit = $("#txtValUnit").val();
    var importe = $("#txtPreUniFact").val();
    var cantidad = $("#txtCantFact").val();

    importe = roundN((valunit * 1.18), 2);
    document.getElementById('txtPreUniFact').value = importe;
    var suma = roundN((valunit * cantidad), 2);
    document.getElementById('txtImpFact').value = suma.toString();
    var igv = roundN(((valunit * 1.18) * cantidad) - suma, 2);
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
    var igv = roundN(((valunit * 1.18) * cantidad) - suma, 2);
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
    var igv = roundN(((valunit * 1.18) * cantidad) - suma, 2);
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
    var igv = roundN(((valunit * 1.18) * cantidad) - suma, 2);
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

function toggle(elemento) {
    if (elemento.value === "1") {

        document.getElementById("txtFechaDesde").value = '';
        document.getElementById("txtFechaHasta").value = '';
        document.getElementById("txtFechaDesde").disabled = false;
        document.getElementById("txtFechaHasta").disabled = false;
        document.getElementById("txtBuscarNroDoc").disabled = true;

    } else {
        if (elemento.value === "2") {
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

function roundN(num, n) {
    return parseFloat(Math.round(num * Math.pow(10, n)) / Math.pow(10, n)).toFixed(n);
}

