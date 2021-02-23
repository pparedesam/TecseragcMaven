var oTable;
$(function () {
    oTable = fn_iniciarDT(oTable, "tblTiposServicio");
    $("#btnAdd").on("click", function () {
        fn_Add();
    });
    oTable.on("click", ".edit", function () {
        fn_Edit(this);
    });
    oTable.on("click", ".save", function () {
        fn_save(this);
    });

    oTable.on("click", ".cancel", function () {
        fn_Cancel(this);
    });
});
function fn_Add() {

    $("#tblTiposServicio tbody").prepend("<tr>" +
            "<td><input type='text' class='form-control' id='txtocupacion' style='width: 100%'/></td>" +
            "<td class='text-center'><input type='checkbox' checked='checked'/></td>" +
            "<td class='text-center'>" +
            "<a class='btn btn-success btn-circle save' href='javascript:;' title='Guardar' ><i  class='fa fa-save'></i></a>\n" +
            "<a class='btn btn-warning btn-circle cancel' href='javascript:;' title='Cancelar' ><i class='fa fa-ban'></i></a>" +
            "</td></tr>");
    $('#btnAdd').prop("disabled", true);
}
;
var fn_save = function (elem) {

    var par = $(elem).parent().parent();
    var tdnombre_factor = par.children("td:nth-child(1)");
    var tdestado = par.children("td:nth-child(2)");
    var tdbuttons = par.children("td:nth-child(3)");
    var id_codigo = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var estado = tdestado.children("input[type=checkbox]").is(":checked") ? 1 : 0;
    var decision;
    if (id_codigo === 0) {
        decision = 'in';
    } else {
        decision = 'up';
    }


    var data = {'codigo': id_codigo, 'descripcion': tdnombre_factor.children("input[type=text]").val(),
        'estado': tdestado.children("input[type=checkbox]").is(":checked") ? 1 : 0, 'decision': decision};

    var success = function (res) {
        if (res.Message == '1') {

            tdnombre_factor.html(tdnombre_factor.children("input[type=text]").val());
            tdestado.html(estado === 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>');
            tdbuttons.html('<a class="btn btn-Lapislazuli btn-circle edit"><i class="fa fa-edit"></i></a>');
            tdbuttons.attr("data-id", res.codigo);
            $('#btnAdd').prop("disabled", false);
            toast('success', 'Se registró correctamente', 'Exito!');
            setTimeout("location.reload(true);", 500);
        }
    };

    var error = function (res) {
        toast('error', res.Message, 'Error');
    };
    fn_callmethod("../sTipoServicio?action=registrar", data, success, error);
};



function fn_Edit(elem) {

    var par = $(elem).parent().parent();
    var tdnombre_factor = par.children("td:nth-child(1)");
    var tdestado = par.children("td:nth-child(2)");
    var tdbuttons = par.children("td:nth-child(3)");
    var estado;
    tdnombre_factor.html("<input type='text' id='txtocupacion' class='form-control input-sm' style='width: 100%' value='" + tdnombre_factor.html() + "'/>");
    estado = tdestado.children("i").hasClass("fa-square-o") ? "<input type='checkbox'/>" : "<input type='checkbox'  checked='checked'/>";
    tdestado.html(estado);
    tdbuttons.html('<a class="btn btn-success btn-circle save"><i class="fa fa-save " title="Guardar"></i></a>\n<a class="btn  btn-warning btn-circle cancel" href="javascript:;" title="Cancelar" ><i class="fa fa-ban"></i></a>');
    $('#btnAdd').prop("disabled", true);
}
;
var fn_Cancel = function (elem) {


    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(3)");
    var id = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    if (id === 0) {
        par.remove();
        $('#btnAdd').prop("disabled", false);
    } else {
        var par = $(elem).parent().parent();
        var tdnombre_factor = par.children("td:nth-child(1)");
        var tdestado = par.children("td:nth-child(2)");
        tdnombre_factor.html(tdnombre_factor.children("input[type=text]").val());
        tdestado.html((tdestado.children("input[type=checkbox]").is(":checked") ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>'));
        tdbuttons.html('<a class="btn btn-Lapislazuli btn-circle edit"><i class="fa fa-edit"></i></a>');
        $('#btnAdd').prop("disabled", false);
    }

};
