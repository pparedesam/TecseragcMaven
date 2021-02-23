$('#btnAgregarPN').click(function (event) {

    $("#modalPersonaNatural").modal();

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

$("#btnBuscarPersonaN").click(function () {
    if ($("#form-buscarPN").valid()) {

        var data = {'txtPersonaNatural': $("#txtPersonaNatural").val(), 'TipoBusqueda': $('input:radio[name=optradioPN]:checked').val()};

        var success = function (res) {

            if (res.Result == 'OK') {
                $('#tbl_PersonaNatural').DataTable({
                    destroy: true,
                    "columnDefs": [
                        {"className": "text-center", "targets": "_all"}

                    ],
                    "aaData": res.lista,
                    //rowId: 'idbien', //asigna el id a la fila
                    "columns": [
                        {"data": "nroDocumento"}, //, "visible": false},
                        {"data": "nombres"},
                        {
                            "data": null,
                            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                                $(nTd).html("<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                            }}
                    ],
                    createdRow: function (row, data, indice) {
                        $(row).find("td:eq(2)").attr('data-id', data.idPersona);
                    }
                });
            } else if (res.Result === 'error')
            {
                toast('error', res.Message, 'Error!');
            }

        };

        var error = function (res) {
            toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
        };
    }
    fn_callmethod("../sPersonaNatural?action=buscarPersonaNaturalModal", data, success, error);

});

var otablePn = $('#tbl_PersonaNatural').DataTable();
otablePn.on("click", ".select", function () {
    fn_SelectPN(this);
});

var fn_SelectPN = function (elem) {

    var par = $(elem).parent().parent();
    var td_nombre = par.children("td:nth-child(2)");
    var td_dni = par.children("td:nth-child(1)");
    var tdbuttons = par.children("td:nth-child(3)");
    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    var data = {'idPersona': id_persona, 'nombre': td_nombre.html(), 'dni': td_dni.html()};
    insertarConductor(data);
    $('#modalPersonaNatural').modal('toggle');

};

function insertarConductor(data) {
    $("#txtidPersonaNatural").val(data.idPersona);
    $("#txtDNI").val(data.dni);
    $("#txtNombrePersona").val(data.nombre);
}