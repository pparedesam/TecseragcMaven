$('#btnAgregarBanco').click(function (event) {

    $("#modalBuscarBco").modal();




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

$('#frmBuscarBanco').submit(function (event) {
    event.preventDefault();
    $('#modalBuscarBco').modal('toggle');
});


$("#txtFechaApertura").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '1900:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});


$("#btnBuscarPJ").click(function () {


    var data = {'optradiobco': $('input[name="optradiobco"]:checked').val(),
        'txtCriterioBusqueda': $("#txtCriterioBusqueda").val()};

    var success = function (res) {

        if (res.Result == 'OK') {

            $('#tbl_Banco').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaBanco,
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

    fn_callmethod("../sPersonaJuridica?action=listarPersona", data, success, error);

});


var otabletbl_PersonaJ = $('#tbl_Banco').DataTable();
otabletbl_PersonaJ.on("click", ".select", function () {
    fn_Selection(this);
});

var fn_Selection = function (elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(3)");
    var td_tit = par.children("td:nth-child(2)");
    var id_per = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');


    var data = {'idPersona': id_per, 'titular': td_tit.html()};
    insertarPersona(data);
    $('#modalBuscarBco').modal('toggle');

};

function insertarPersona(data) {

    $("#txtidPersonaBanco").val(data.idPersona);
    $("#txtBanco").val(data.titular);

}

$('#btnRegistrarBanco').click(function (e) {
    e.preventDefault();
    registroBanco();

    /* var validator = $("#form-registrarCheque").valid();
     if (validator) {
     
     } else
     toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');*/
});

function registroBanco() {




 
    var data = {
        'txtidPersonaBanco':  $("#txtidPersonaBanco").val(),
        'txtBanco':  $("#txtBanco").val(),
        'txtAbrev':  $("#txtAbrev").val()

    };
    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.Message === 'true') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                setTimeout("location.reload(true);", 5000);
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
            fn_callmethod("../sBanco?action=registrarBanco", data, success, error);
        }
    });

}
;
