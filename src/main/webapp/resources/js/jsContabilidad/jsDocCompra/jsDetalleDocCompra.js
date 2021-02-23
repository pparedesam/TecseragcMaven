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
        registrarCarateristica();
        $('#modalCaracteristicas').modal('toggle');

        clearmodalcaracteristicas();
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');
});



function registrarCarateristica() {

    var data = {
        'idTipoServicio': $('#cboTipoServicio').val(), 'servicio': $('#cboTipoServicio').find('option:selected').text(),
        'detalle': $('#txtDetFact').val(), 'importe': $('#txtImpFact').val(), 'igv': $('#txtIGVDet').val()
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

    var data = {'idTipoServicio': datos.idTipoServicio, 'servicio': descripcion, 'importe': roundN(datos.importe, 2), 'igv': roundN(datos.igv, 2)};
    var data2 = {'idTipoServicio': datos.idTipoServicio, 'servicio': descripcion, 'importe': datos.importe, 'igv': datos.igv}

    var success = function (res) {

        if (res.Result === 'OK') {

            document.getElementById("txtTotal").value = roundN(res.total, 2);
            document.getElementById("txtSubTotal").value = roundN(res.subtotal, 2)
            document.getElementById("txtIGV").value = roundN(res.igv, 2);
            document.getElementById("txtDetFact").value = '';
            document.getElementById("txtImpFact").value = '';
            document.getElementById("txtIGVDet").value = '';



            $(nombreTabla).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.detalleFactura,
                rowId: 'idTipoServicio', //asigna el id a la fila
                "columns": [
                    {"data": "descripcion"}, //, "visible": false},
                    {"data": "importe"},
                    {"data": "igv"}, {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(3)").attr('data-id', data.descripcion);

                }
            });
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    if (accion == 1) {
        fn_callmethod("../sDocCompra?action=comprobarDetalleCompra", data, success, error);
    } else if (accion == 2) {
        fn_callmethod("../sDocCompra?action=quitarDetalleCompra", data2, success, error);
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
    var tdbuttons = par.children("td:nth-child(4)");
    var tdimporte = par.children("td:nth-child(2)");
    var tdigv = par.children("td:nth-child(3)");

    var importe = tdimporte.html();
    var igv = tdigv.html();

    var servicio = tdbuttons.attr('data-id');
    var data = {'servicio': servicio, 'importe': importe, 'igv': igv};
    actualizarTablaBien(data, "#tblCarBien", 2);

};


function clearmodalcaracteristicas() {
    $("#txtDesCarBien").val("");
    $("#cboCaractBien").val('1').trigger('change.select2');

}
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


$('#txtImpFact').bind('keyup', function (e) {

    var importe = $("#txtImpFact").val();
    var suma = roundN(importe * 0.18, 2);
    document.getElementById('txtIGVDet').value = suma.toString();

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
    yearRange: '2000:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});

$("#txtFechaHasta").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '2000:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});
