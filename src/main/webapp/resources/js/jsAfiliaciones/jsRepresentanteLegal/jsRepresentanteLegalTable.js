
var tableRepresentanteLegal = function () {

    var idPersona = document.getElementById('txtNroRuc').getAttribute("data-idPersona");

    var data = {'txtIdPersona': idPersona};

    var success = function (res) {

        if (res.Result == 'OK') {
            $('#btnAgregarRepresentante').prop("disabled", false);
            $('#tblRepresentantes').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}
                ],
                "aaData": res.ListaRepresentantes,
                "columns": [
                    {"data": "objRepresentanteLegal.nroDocumento"},
                    {"data": function (data) {
                            return data.objRepresentanteLegal.apPaterno + " " + data.objRepresentanteLegal.apMaterno + " " + data.objRepresentanteLegal.nombres;
                        }
                    },
                    {"data": "objCargoRepresentante.descripcion"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html(
                                    // "<a href='javascript:;'  class='btn btn-success btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a>\n" +
                                    "<a class='btn btn-danger btn-circle remove' title='Eliminar' id='Delete'><i class='fa fa-remove'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(3)").attr('data-id', data.objRepresentanteLegal.idPersona);
                    $(row).find("td:eq(3)").attr('data-codRep', data.objCargoRepresentante.codigo);
                }
            });

        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };
    var error = function (res) {
        toast('error', res.Message, 'Error');
    };
    fn_callmethod("../sRepresentantesLegales?action=obtenerRepresentanteLegal", data, success, error);
};

var oTable;

oTable = fn_iniciarDT(oTable, "tblRepresentantes");

oTable.on("click", ".save", function () {
    fn_SaveRL(this);
});

oTable.on("click", ".cancel", function () {
    fn_CancelRL(this);
});

oTable.on("click", ".remove", function () {
    var elem = this;
    bootbox.confirm({
        title: "ELIMINAR REPRESENTANTE LEGAL",
        message: "ESTA SEGURO QUE DESEA ELIMINAR UN REPRESENTANTE LEGAL",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Cancelar'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Confirmar'
            }
        },
        callback: function (result) {
            if (result) {
                oTable.fnDestroy();
                fn_DeleteRL(elem);
            }
        }
    });
});

oTable.on("click", ".edit", function () {
    fn_EditRL(this);
});

$("#btnAgregarRepresentante").click(function (event) {

    $("#txtIdPersonaRL").val('');
    $("#txtNomPersonaRL").val('');
    $("#txtNombrePersonaRL").val('');
    var idPersona = document.getElementById('txtNroRuc').getAttribute("data-idPersona");
    if (idPersona.length === 0) {
        bootbox.alert("No se ha seleccionado una Empresa");
    } else {
        $('#btnAgregarRepresentante').prop("disabled", true);
        oTable.DataTable({
            destroy: true,
            "paging": false,
            "ordering": false,
            "info": false
        });
        $("#tblRepresentantes tbody").prepend(
                "<tr>" +
                "<td style='width:5%'></td>" +
                "<td style='width:15%'>" +
                "<div class='form-group' style='width:100%'>" +
                "<div class='input-group' style='width:100%'>" +
                "<input type='text' class='form-control' id='txtNomPersonaRL' name='txtNomPersonaRL' value=''>" +
                "<input type='hidden' id='txtIdPersonaRL' name='txtIdPersonaRL' value=''/>" +
                "<input type='hidden' id='txtNombrePersonaRL' name='txtNombrePersonaRL' value=''/>" +
                "<div class='input-group-btn'>" +
                "<a class='btn btn-Lapislazuli btn-bitbucket buscar' href='frmBusqueda/frmBuscarPersonaNatural.jsp' data-target='#modalBuscar' data-toggle='modal'>" +
                "<i class='fa fa-search'></i>" +
                "</a>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "</td>" +
                "<td class='text-center' style='width: 10%'>" +
                "<select class='form-control' id='cboCargosRepresentateLegal0' style='width: 100%'  name='cboCargosRepresentateLegal0'>" +
                "</select>" +
                "</td>" +
                "<td class='text-center' style='width: 3%'>" +
                "<a class='btn btn-Lapislazuli btn-circle save' href='javascript:;' title='Guardar' ><i  class='fa fa-save'></i></a>" +
                "<a class='btn  btn-warning btn-circle cancel' href='javascript:;' title='Cancelar' ><i class='fa fa-ban'></i></a>" +
                "</td>" +
                "</tr>");
        combo("../sRepresentantesLegales?action=mostrarCargos", "cboCargosRepresentateLegal0");
        combotable('cboCargosRepresentateLegal0');

        //$("#cboCargosRepresentateLegal0").select2({ width: '300px' });


    }
});


function insertarpersonafila(data) {
    $("#txtIdPersonaRL").val(data.idPersona);
    $("#txtNomPersonaRL").val(data.nombre);
}

function  registrarRepresentanteLegal(data) {

    var success = function (res) {
        if (res.Result == 'OK') {
            tableRepresentanteLegal();
            if (res.Message == 'ins_ok') {
                toast('success', 'SE REGISTR&Oacute; CORRECTAMENTE', 'Exito!');
                //setTimeout("location.reload(true);", 5000);
            } else {
                toast('error', res.Message, 'Error');
            }
        } else if (res.Result == 'error') {
            toast('error', res.Message, 'Error');
        }
        $('#btnAgregarMiembro').prop("disabled", false);
    }
    var error = function (res) {

        toast('error', res.message, 'Error');
    };
    fn_callmethod("../sRepresentantesLegales?action=registrarRepresentanteLegal", data, success, error);

}

var fn_SaveRL = function (elem) {

    var par = $(elem).parent().parent();
    var td_cargoRepresentante = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    //var idPersonaRL = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var idCargoRepresentante = td_cargoRepresentante.children().find('option:selected').val();
    var idPersona = document.getElementById('txtNroRuc').getAttribute("data-idPersona");


    var idPersonaRL = idPersonaRL = $("#txtIdPersonaRL").val();


    var data = {
        'IdPersona': idPersona,
        'IdPersonaRL': idPersonaRL,
        'idCargoRepresentante': idCargoRepresentante
    };

    if ($('#frmtblRepresentantes').valid()) {
        registrarRepresentanteLegal(data);
    }


};

var fn_CancelRL = function (elem) {


    var par = $(elem).parent().parent();
    var tdCargo = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    var idPersonaRL = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var idPersona = document.getElementById('txtNroRuc').getAttribute("data-idPersona");

    if (idPersonaRL === 0) {
        par.remove();

        $('#btnAgregarRepresentante').prop("disabled", false);
    } else {

        tdCargo.html($("#cboCargosRepresentateLegal" + idPersona + " option:selected").text());
        tdbuttons.html("<a class='btn btn-Lapislazuli btn-circle edit'><i class='fa fa-edit'></i></a>\n" +
                "<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a>");
        $('#btnAgregarRepresentante').prop("disabled", false);
    }

    oTable.DataTable({
        destroy: true,
        "paging": true,
        "ordering": true,
        "info": true
    });
};

var fn_DeleteRL = function (elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var idPersonaRL = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    id = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var idPersona = document.getElementById('txtNroRuc').getAttribute("data-idPersona");
    var data = {
        'IdPersona': idPersona,
        'IdPersonaRL': idPersonaRL
    };
    var success = function (res) {

        if (res.Result == 'OK') {
            if (res.Message == 'del_ok') {
                var par = $(elem).parent().parent();
                par.remove();
                toast('success', 'SE ELIMIN&Oacute; CORRECTAMENTE', 'Exito!');
                oTable = fn_iniciarDT(oTable, "tblRepresentantes");
                $('#btnAgregarMiembro').prop("disabled", false);
            }

        } else {
            toast('error', res.Message, 'Error');
        }

    };
    var error = function () {
    };
    fn_callmethod("../sRepresentantesLegales?action=eliminarRepresentante", data, success, error);
};

var fn_EditRL = function (elem) {

    var par = $(elem).parent().parent();
    var td_cargoRepresentante = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    var idPersonaRL = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var idPersona = document.getElementById('txtNroRuc').getAttribute("data-idPersona");
    var codrep = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-codrep')
    td_cargoRepresentante.html("<select id='cboCargosRepresentateLegal" + idPersona + "'  class='simple-select2 form-control' ></select>");
    tdbuttons.html('<a class="btn btn-Lapislazuli btn-circle save"><i class="fa fa-save" title="Guardar"></i></a>\n'
            + '<a class="btn  btn-warning btn-circle cancel" href="javascript:;" title="Cancelar" ><i class="fa fa-ban"></i></a>');

    var cbo = "cboCargosRepresentateLegal" + idPersona;
    combo("../sRepresentantesLegales?action=mostrarCargos", cbo);
    setSelect2(cbo, codrep);


    $('#btnAgregarMiembro').prop("disabled", true);
};