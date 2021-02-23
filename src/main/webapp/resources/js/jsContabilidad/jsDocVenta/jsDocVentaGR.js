$('#btnAgregarGR').click(function (event) {

    $("#modalGuiaRemision").modal();

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


$('#frmGuiaRemision').submit(function (event) {
    event.preventDefault();
    var validator = $("#frmGuiaRemision").valid();
    if (validator) {
        registrarGuiaRemisionDet();
        $('#modalGuiaRemision').modal('toggle');

        clearmodalcaracteristicas();
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');



});


function registrarGuiaRemisionDet() {

    var data = {
        'idTipoGuia': $('#cboTipGuia').val(), 'tipoGuia': $('#cboTipGuia').find('option:selected').text(), 'nroGuia': $('#txtNroGuia').val(),
        'fechaGuia': $('#txtFechaGuia').val()
    };
    actualizarTablaGR(data, '#tblGuiaRemision', 1);

}


function actualizarTablaGR(datos, nombreTabla, accion) {
    var descripcion;
    var idtipGuia;
    descripcion = datos.nroGuia;
    idtipGuia = datos.idTipoGuia;


    var data = {'idTipoGuia': idtipGuia, 'tipoGuia': datos.tipoGuia, 'nroGuia': descripcion, 'fechaGuia': datos.fechaGuia};

    var success = function (res) {

        if (res.Result === 'OK') {


            document.getElementById("txtNroGuia").value = '';
            document.getElementById("txtFechaGuia").value = '';

            $(nombreTabla).DataTable({
                destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.detalladoGuiaRemisionF,
                rowId: 'idTipoGuia', //asigna el id a la fila
                "columns": [
                    {"data": "tipoGuia"},
                    {"data": "nroGuia"},
                    {"data": "fechaGuia"}, //, "visible": false},
                    {"data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-danger btn-circle removeGR' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(3)").attr('data-id', data.nroGuia);
                    $(row).find("td:eq(3)").attr('data-idtg', data.idTipoGuia);

                }
            });
        } else if (res.Result === 'KO') {
            toast('error', "No se puede repetir una guia de remision ya ingresada", 'Error');
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    if (accion == 1) {
        fn_callmethod("../sDocVenta?action=comprobarDetalleGuia", data, success, error);
    } else if (accion == 2) {
        fn_callmethod("../sDocVenta?action=quitarDetalleGuia", data, success, error);
    }
}
;

function clearmodalcaracteristicas() {
    $("#txtDesCarBien").val("");
    $("#cboCaractBien").val('1').trigger('change.select2');

}

var oTable;

oTable = fn_iniciarSinDT(oTable, "tblGuiaRemision");
oTable.on("click", ".removeGR", function () {
    var elem = this;
    oTable.fnDestroy();
    fn_DeleteGR(elem);
});

var fn_DeleteGR = function (elem) {
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var idTipoGuia = tdbuttons.attr('data-idtg');
    var descripcion = tdbuttons.attr('data-id');
    var data = {'nroGuia': descripcion,'idTipoGuia':idTipoGuia};
    actualizarTablaGR(data, "#tblGuiaRemision", 2);

};

$("#txtFechaGuia").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '1900:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});