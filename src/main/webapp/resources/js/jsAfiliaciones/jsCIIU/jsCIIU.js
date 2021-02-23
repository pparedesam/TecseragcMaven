var oTable;
$(function () {
    oTable = fn_iniciarDT(oTable, "tblCIIU");
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
        bootbox.confirm("\u00BFEst\u00e1 seguro de eliminar esta CIIU?\u003F", function (result) {
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

    $("#tblCIIU tbody").prepend("<tr>" +
            "<td>" +
            "<input type='text' class='form-control' id='txtidCIIU' style='width: 100%'/>" +
            "</td>" +
            "<td>" +
            "<input type='text' class='form-control' id='txtCIIU' style='width: 100%'/>" +
            "</td>" +
            "<td class='text-center'>" +
            "<div class='checkbox'>" +
            "<input type='checkbox' checked='checked' id='chkestado'/><label for='chkestado'></label>" +
            "</div>" +
            "</td>" +
            "<td class='text-center'>" +
            "<a class='btn  btn-success btn-circle save' href='javascript:;' title='Guardar' ><i  class='fa fa-save'></i></a>\n" +
            "<a class='btn  btn-warning btn-circle cancel' href='javascript:;' title='Cancelar' ><i class='fa fa-ban'></i></a>" +
            "</td>" +
            "</tr>");
    $('#btnAdd').prop("disabled", true);
}
;

var fn_save = function (elem) {

    var par = $(elem).parent().parent();
    var tdcodigo = par.children("td:nth-child(1)");
    var tdnombre_ciiu = par.children("td:nth-child(2)");
    var tdestado = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    var id_codigo = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var estado = tdestado.children("input[type=checkbox]").is(":checked") ? 1 : 0;
    var result = true;
    var decision;
    var CodigoCiiu;

    if (id_codigo === 0) {
        decision = 'in';
        result = validarCodigoCIIU(tdcodigo);
        CodigoCiiu = tdcodigo.children("input[type=text]").val();

    } else {
        decision = 'up';
        CodigoCiiu = id_codigo;
    }

    if (result == true && validarNombreCiiu(tdnombre_ciiu) == true)
    {
        var data = {'idciiu': CodigoCiiu, 'descripcion': tdnombre_ciiu.children("input[type=text]").val(),
            'estado': tdestado.children("input[type=checkbox]").is(":checked") ? 1 : 0, 'decision': decision};

        var success = function (res) {

            if (res.codigo === "1")
            {
                tdcodigo.html(tdcodigo.children("input[type=text]").val());
                tdnombre_ciiu.html(tdnombre_ciiu.children("input[type=text]").val());
                tdestado.html(estado === 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>');
                tdbuttons.html('<a class="btn  btn-Lapislazuli btn-circle edit"><i class="fa fa-edit"></i></a>');
                tdbuttons.attr("data-id", res.codigo);

                $('#btnAdd').prop("disabled", false);
                toast('success', 'Se registró correctamente', 'Exito!');
                setTimeout("location.reload(true);", 5000);
            } else
            {
                var mensaje;
                if (res.codigo == 0) {
                    mensaje = 'Error el Codigo de CIIU ya existe';

                }
                if (res.codigo == 2) {
                    mensaje = 'Error CIIU no se puedo actualizar';

                }
                toast('error', mensaje, 'ERROR');
            }
        };
    } else {
        toast('error', 'EL codigo del CIIU debe tener 4 digitos y la descripcion debe tener almenos 4 caracteres  ', 'ERROR');
    }

    var error = function (res) {
        toast('error', res.mensaje, 'Error');
    };

    fn_callmethod("../sCIIU?action=registrar", data, success, error);

}
;

var fn_Delete = function (elem) {

    var codigo = $(elem).parent().attr('data-id') === undefined ? 0 : $(elem).parent().attr('data-id');
    if (codigo !== 0) {
        var success = function (res) {
            if (res) {
                var par = $(elem).parent().parent();
                par.remove();
                oTable = fn_iniciarDT(oTable, "tblCIIU");

            }
        };
        var error = function () {
        };
        fn_callmethod("../sCIIU?action=delete", {codigo: codigo}, success, error);
    }
};

function fn_Edit(elem) {

    var par = $(elem).parent().parent();

    var tdnombre_ciiu = par.children("td:nth-child(2)");
    var tdestado = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    var estado;

    tdnombre_ciiu.html("<input type='text' id='txtCIIU' class='form-control input-sm' style='width: 100%' value='" + tdnombre_ciiu.html() + "'/>");
    estado = tdestado.children("i").hasClass("fa-square-o") ?
            "<div class='checkbox'><input type='checkbox' id='chkestado'/><label for='chkestado'></label></div>"
            : "<div class='checkbox'><input type='checkbox' checked='checked' id='chkestado'/><label for='chkestado'></label></div>";
    tdestado.html(estado);
    tdbuttons.html('<a class="btn  btn-success btn-circle save"><i class="fa fa-save " title="Guardar"></i></a>\n<a class="btn  btn-warning btn-circle cancel" href="javascript:;" title="Cancelar" ><i class="fa fa-ban"></i></a>');
    $('#btnAdd').prop("disabled", true);
}
;
var fn_Cancel = function (elem) {


    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var id = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');


    if (id == 0) {
        par.remove();
        $('#btnAdd').prop("disabled", false);
    } else {
        var par = $(elem).parent().parent();
        var tdcodigo = par.children("td:nth-child(1)");
        var tdnombre_factor = par.children("td:nth-child(2)");
        var tdestado = par.children("td:nth-child(3)");

        tdcodigo.html(tdcodigo.children("input[type=text]").val());
        tdnombre_factor.html(tdnombre_factor.children("input[type=text]").val());
        tdestado.html((tdestado.children("input[type=checkbox]").is(":checked") ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>'));
        tdbuttons.html('<a class="btn  btn-Lapislazuli btn-circle edit"><i class="fa fa-edit"></i></a>');
        $('#btnAdd').prop("disabled", false);
    }

};



function validarCodigoCIIU(tdcodigo)
{
    var valor;
    var data = tdcodigo.children("input[type=text]").val();
    if (data.length == 4 && isNaN(data) == false)
    {

        valor = true;
    } else
        valor = false;

    return valor;

}

function validarNombreCiiu(tdnombre_ciiu)
{
    var valor;
    var data = tdnombre_ciiu.children("input[type=text]").val();
    console.log("Tamanio" + data);
    if (data.length >= 4)
    {

        valor = true;
    } else
        valor = false;

    return valor;

}


