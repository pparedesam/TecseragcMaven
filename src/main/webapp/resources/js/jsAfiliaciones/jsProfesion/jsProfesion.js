var oTable;
$(function () {
    oTable = fn_iniciarDT(oTable, "tblProfesiones");
    $("#btnAdd").on("click", function () {
        fn_Add();
    });
    oTable.on("click", ".edit", function () {
        fn_Edit(this);
    });

    oTable.on("click", ".save", function () {

        fn_save(this);

    });

    oTable.on("click", ".remove", function () {
        var elem = this;
        console.log(elem);
        bootbox.confirm("\u00BFEst\u00e1 seguro de eliminar esta factor?\u003F", function (result) {
            if (result) {
                oTable.fnDestroy();
                fn_Delete(elem);
            }
        });

    });


    oTable.on("click", ".cancel", function () {
        fn_Cancel(this);
    });
});



function fn_Add() {

    $("#tblProfesiones tbody").prepend("<tr>" +
            "<td><input type='text' class='form-upload input-sm' style='width: 100%'/></td>" +
            "<td class='text-center'><input type='checkbox' checked='checked'/></td>" +
            "<td class='text-center'>" +
            "<a class='btn btn-info btn-circle save' href='javascript:;' title='Guardar' ><i  class='fa fa-save'></i></a>" +
            "<a class='btn  btn-warning btn-circle cancel' href='javascript:;' title='Cancelar' ><i class='fa fa-ban'></i></a>" +
            "</td></tr>");
}
;

var fn_save = function (elem) {

    var par = $(elem).parent().parent();
    var tdnombre_profesion = par.children("td:nth-child(1)");
    var tdestado = par.children("td:nth-child(2)");
    var tdbuttons = par.children("td:nth-child(3)");
    var id_codigo = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var estado = tdestado.children("input[type=checkbox]").is(":checked") ? 1 : 0;
    var result = validar(tdnombre_profesion);
    if (result === true) {
        var data = {'idProfesion': id_codigo, 'descripcion': tdnombre_profesion.children("input[type=text]").val(),
            'estado': tdestado.children("input[type=checkbox]").is(":checked") ? 1 : 0
        };

        var success = function (res) {

            if (res.idProfesion > 0)
            {

                tdnombre_profesion.html(tdnombre_profesion.children("input[type=text]").val());
                tdestado.html(estado === 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>');
                /*tdbuttons.html('<a class="btn btn-primary btn-circle edit"><i class="fa fa-edit"></i></a><a class="btn btn-danger btn-circle remove"><i class="fa fa-remove"></i></a>');*/
                tdbuttons.html('<a class="btn btn-primary btn-circle edit"><i class="fa fa-edit"></i></a>');
                tdbuttons.attr("data-id", res.idProfesion);

                $('#btnAdd').prop("disabled", false);
                toast('success', 'Se registró correctamente', 'Exito!');
            }
        };
        var error = function (res) {

            toast('error', res.mensaje, 'Error');
        };

        fn_callmethod(base_url + "../sProfesion?action=registrar", data, success, error);
    } else
    {
        toast('error', result, 'ERROR');
    }

};


var fn_Delete = function (elem) {

    var codigo = $(elem).parent().attr('data-id') === undefined ? 0 : $(elem).parent().attr('data-id');
    if (codigo !== 0) {
        var success = function (res) {
            if (res) {
                var par = $(elem).parent().parent();
                par.remove();
                oTable = fn_iniciarDT(oTable, "tblProfesiones");
            }
        };
        var error = function () {
        };
        fn_callmethod(base_url + "../sProfesion?action=delete", {IdProfesion: codigo}, success, error);
    }
};

function fn_Edit(elem) {

    var par = $(elem).parent().parent();
    var tdnombre_profesion = par.children("td:nth-child(1)");
    var tdestado = par.children("td:nth-child(2)");
    var tdbuttons = par.children("td:nth-child(3)");
    var estado;

    tdnombre_profesion.html("<input type='text' id='txtdescripcion' class='form-control input-sm' style='width: 100%' value='" + tdnombre_profesion.html() + "'/>");
    estado = tdestado.children("i").hasClass("fa-square-o") ? "<input type='checkbox'/>" : "<input type='checkbox'  checked='checked'/>";
    tdestado.html(estado);
    /* estado === 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>' */
    tdbuttons.html('<a class="btn btn-primary btn-circle save"><i class="fa fa-save " title="Guardar"></i></a><a class="btn  btn-warning btn-circle cancel" href="javascript:;" title="Cancelar" ><i class="fa fa-ban"></i></a>');
    /*toast('success', 'Se actualizó correctamente', 'Exito!');*/
    $('#btnAdd').prop("disabled", true);
}
;
var fn_Cancel = function (elem) {


    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(3)");
    var id = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    if (id === 0) {
        par.remove();
    } else {
        var par = $(elem).parent().parent();
        var tdnombre_factor = par.children("td:nth-child(1)");
        var tdestado = par.children("td:nth-child(2)");

        tdnombre_factor.html(tdnombre_factor.children("input[type=text]").val());
        tdestado.html((tdestado.children("input[type=checkbox]").is(":checked") ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>'));
        tdbuttons.html('<a class="btn btn-primary btn-circle edit"><i class="fa fa-edit"></i></a>');
        $('#btnAdd').prop("disabled", false);
    }

};

function validar(txtDescripcion)
{
    var valor = true;
    var descripcion = txtDescripcion.children("input[type=text]").val().length;

    if (descripcion < 4)
        valor = 'El nombre de la profesion debe tener un mínimo de 4 caracteres';
    return valor;
} 