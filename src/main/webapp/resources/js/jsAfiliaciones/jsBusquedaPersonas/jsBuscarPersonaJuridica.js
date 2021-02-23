$('#btnAgregarPJ').click(function (event) {

    $("#modalPersonaJuridica").modal();

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

$("#btnBuscarPersonaJ").click(function () {
    if ($("#form-buscarJ").valid()) {

        var data = {'txtPersonaJuridica': $("#txtPersonaJuridica").val(), 'TipoBusqueda': $('input:radio[name=optradioPJ]:checked').val()};

        var success = function (res) {

            if (res.Result == 'OK') {
                $('#tbl_PersonaJuridicaModal').DataTable({
                    destroy: true,
                    "columnDefs": [
                        {"className": "text-center", "targets": "_all"}

                    ],
                    "aaData": res.listaJ,
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
    fn_callmethod("../sPersonaJuridica?action=buscarPersonaJuridicaModal", data, success, error);

});


var otablePJ = $('#tbl_PersonaJuridicaModal').DataTable();
otablePJ.on("click", ".select", function () {
    fn_Select(this);
});

var fn_Select = function (elem) {

    var par = $(elem).parent().parent();
    var td_nombre = par.children("td:nth-child(2)");
    var td_dni = par.children("td:nth-child(1)");
    var tdbuttons = par.children("td:nth-child(3)");
    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    var dato = {'idPersona': id_persona, 'nombre': td_nombre.html(), 'dni': td_dni.html()};
    insertarDestinatario(dato);

    $('#modalPersonaJuridica').modal('toggle');

};

function insertarDestinatario(data) {
    $("#txtidPersonaPJ").val(data.idPersona);
    $("#txtRUC").val(data.dni);
    $("#txtNombrePJ").val(data.nombre);
}