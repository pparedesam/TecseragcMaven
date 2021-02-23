$('#btnAgregarTitular').click(function (event) {

    $("#modalBuscarPersonaJN").modal();




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


$('#frmBuscarPersonaJN').submit(function (event) {
    event.preventDefault();
    $('#modalBuscarPersonaJN').modal('toggle');
});

$("#btnBuscarSocio").click(function () {
  

        var data = {'optradiobus': $('input[name="optradiobus"]:checked').val(), 'cboTipoPersona': $("#cboTipoPersona").val(),
            'txtCriterioBusqueda': $("#txtCriterioBusqueda").val()};

        var success = function (res) {

            if (res.Result == 'OK') {

                $('#tbl_PersonaJN').DataTable({
                    destroy: true,
                    "columnDefs": [
                        {"className": "text-center", "targets": "_all"}

                    ],
                    "aaData": res.listaPersonaJN,
                    //rowId: 'idbien', //asigna el id a la fila
                    "columns": [
                        {"data": "nroDocumento"},
                        {"data": "nombres"},

                        {
                            "data": null,
                            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                                $(nTd).html("<a href='javascript:;'  class='btn btn-HippieGreen btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
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
    
    fn_callmethod("../sPersonaJuridica?action=buscarPersonaJN", data, success, error);

});


var otabletbl_PersonaJN = $('#tbl_PersonaJN').DataTable();
otabletbl_PersonaJN.on("click", ".select", function () {
    fn_Selection(this);
});

var fn_Selection = function (elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(3)");
    var td_nrodoc = par.children("td:nth-child(1)");
    var td_tit = par.children("td:nth-child(2)");
    var id_per = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');


    var data = {'idPersona': id_per, 'titular': td_tit.html(), 'Nrodoc': td_nrodoc.html()};
    insertarPersona(data);
    $('#modalBuscarPersonaJN').modal('toggle');

};



function insertarPersona(data) {

    $("#txtidPersonaCliente").val(data.idPersona);
    $("#txtNombre").val(data.titular);
    $("#txtNroDoc").val(data.Nrodoc);

}
