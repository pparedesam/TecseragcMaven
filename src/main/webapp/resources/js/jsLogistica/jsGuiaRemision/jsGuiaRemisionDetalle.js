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



function registrarCarateristicaVenta() {

    var data = {
        'uniMedida': $('#cboTipoServicio').find('option:selected').text(), 'UM': $('#cboTipoServicio').val(),
        'descripcion': $('#txtDetFact').val(), 'cantidad': $('#txtImpFact').val()
    };
    actualizarTablaBien(data, '#tblCarBien', 1);


}

function actualizarTablaBien(datos, nombreTabla, accion) {

    var data = {'uniMedida': datos.uniMedida, 'UM': datos.UM, 'descripcion': datos.descripcion, 'cantidad': datos.cantidad};


    var success = function (res) {

        if (res.Result === 'OK') {


            document.getElementById("txtDetFact").value = '';
            document.getElementById("txtImpFact").value = '';
        



            $(nombreTabla).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.detalleFactura,
                rowId: 'idTipoServicio', //asigna el id a la fila
                "columns": [
                    {"data": "descripcion"}, //, "visible": false},
                    {"data": "cantidad"},
                    {"data": "uniMedida"},
                    {

                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(2)").attr('data-id', data.descripcion);
                    $(row).find("td:eq(2)").attr('data-idUM', data.UM);

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
        fn_callmethod("../sGuiaRemision?action=comprobarDetalleGuia", data, success, error);
    } else if (accion == 2) {
        fn_callmethod("../sGuiaRemision?action=quitarDetalleGuia", data, success, error);
    }
}
;

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

    var descripcion = tdbuttons.attr('data-id');
    var data = {'descripcion': descripcion};
    actualizarTablaBien(data, "#tblCarBien", 2);

};

function clearmodalcaracteristicas() {
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


$("#txtnroDoc").autocomplete({

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
        fn_callmethod("../sPersonaJuridica?action=PersonaNaturalJuridicaText", data, success, error);
    },
    focus: function (event, ui) {
        event.preventDefault();
        $("#txtnroDoc").val(ui.item.label);
        $("#txtidPersonaDestino").val(ui.item.value ? ui.item.value : "");
        $("#txtDestinatario").val(ui.item.razonsocial ? ui.item.razonsocial : "");


    },
    select: function (event, ui) {

        $("#txtnroDoc").val(ui.item.label);
        $("#txtidPersonaDestino").val(ui.item.value ? ui.item.value : "");
        $("#txtDestinatario").val(ui.item.razonsocial ? ui.item.razonsocial : "");
        return false;
    }

});


$("#txtRUC").keypress(function (e) {
    var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;

    if (key == 8) {
        if ($("#txtRUC").val().length !== 0) {
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
        $("#txtidPersona").val(ui.item.value ? ui.item.value : "");
        $("#txtRazonSocial").val(ui.item.razonsocial ? ui.item.razonsocial : "");


    },
    select: function (event, ui) {

        $("#txtRUCEmisor").val(ui.item.label);
        $("#txtIdPersonaEmisor").val(ui.item.value ? ui.item.value : "");
        $("#txtDireccionEmisor").val(ui.item.direccion ? ui.item.direccion : "");
        $("#txtRazonSocialEmisor").val(ui.item.razonsocial ? ui.item.razonsocial : "");
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
