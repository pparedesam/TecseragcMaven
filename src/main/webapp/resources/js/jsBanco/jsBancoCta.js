$('#btnAgregarbancocta').click(function (event) {

    $("#modalBuscarBcoCta").modal();




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

$('#frmBuscarBancoCta').submit(function (event) {
    event.preventDefault();
    $('#modalBuscarBcoCta').modal('toggle');
});

$("#btnBuscarBancoCta").click(function () {


    var data = {'optradiobcocta': $('input[name="optradiobcocta"]:checked').val(),
        'txtCriterioBusqueda': $("#txtCriterioBusquedaBco").val()};

    var success = function (res) {

        if (res.Result == 'OK') {

            $('#tbl_BancoCta').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaBanco,
                //rowId: 'idbien', //asigna el id a la fila
                "columns": [
                    {"data": "objPersonaBanco.nroDocumento"},
                    {"data": "nombreBanco"},

                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-HippieGreen btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(2)").attr('data-id', data.objPersonaBanco.idPersona);

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

    fn_callmethod("../sBanco?action=buscarBanco", data, success, error);

});


var otabletbl_PersonaBco = $('#tbl_BancoCta').DataTable();
otabletbl_PersonaBco.on("click", ".select", function () {
    fn_Selectioncta(this);
});

var fn_Selectioncta = function (elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(3)");
    var td_tit = par.children("td:nth-child(2)");
    var id_per = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');


    var data = {'idPersona': id_per, 'titular': td_tit.html()};
    insertarPersonacta(data);
    listacta(data);
    $('#modalBuscarBcoCta').modal('toggle');

};


function listacta(data) {

    var data = {'txtidPersonaBancocta': $("#txtidPersonaBancocta").val()};

    var success = function (res) {

        if (res.Result == 'OK') {

            $('#tbl_Cta').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listacta,
                //rowId: 'idbien', //asigna el id a la fila
                "columns": [
                    {"data": "objBanco.nombreBanco"},
                    {"data": "ctaBanco"},
                    {"data": "tipMoneda"},
                    {"data": "tipCtaBanco"}

                ]

            });
        } else if (res.Result === 'error')
        {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sBanco?action=listarCta", data, success, error);


}


function insertarPersonacta(data) {

    $("#txtidPersonaBancocta").val(data.idPersona);
    $("#txtBancocta").val(data.titular);

}



$('#btnRegistrarctabnco').click(function (e) {
    e.preventDefault();
    registroCtaBanco();

    /* var validator = $("#form-registrarCheque").valid();
     if (validator) {
     
     } else
     toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');*/
});

function registroCtaBanco() {
    var data = {
        'txtidPersonaBancocta': $("#txtidPersonaBancocta").val(),
        'txtBancocta': $("#txtBancocta").val(),
        'txtCta': $("#txtCta").val(),
        'cboctabco': $("#cboctabco").val(),
        'cboTipoMoneda': $("#cboTipoMoneda").val(),
        'txtFechaApertura': $("#txtFechaApertura").val()

    };
    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.Message === 'true') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                recargarotravez();

                //setTimeout("location.reload(true);", 5000);

            }
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    bootbox.confirm("\u00BFEst\u00E1 seguro que desea Registrar a esta Persona Juridica como Banco\u003F", function (result) {
        if (result) {
            fn_callmethod("../sBanco?action=registrarCtaBanco", data, success, error);
        }
    });

}
;
function recargarotravez() {
    var table = $('#tbl_Cta').DataTable();
    table.clear().draw();
    var data = {'txtidPersonaBancocta': $("#txtidPersonaBancocta").val()};
    listacta(data);
}