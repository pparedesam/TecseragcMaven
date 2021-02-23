listaGrupoHorario();
var idgrupo = "";
$("#divtblGrupoPersonal").hide();

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

function listaGrupoHorario() {

    var success = function (res) {


        if (res.Result === 'OK') {
            $('#tblGrupoHorario').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}
                ],

                "aaData": res.listaGrupoHorario,
                "columns": [
                    {"data": "nombreGrupo"},
                    {"data": "descGrupo"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html(sData.diaEntrada + "-" + sData.diaSalida + "</td>");
                        }},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            if (sData.tardeEntrada === "12:00AM" && sData.tardeSalida === "12:00AM") {
                                $(nTd).html("-</td>");
                            } else {
                                $(nTd).html(sData.tardeEntrada + "-" + sData.tardeSalida + "</td>");
                            }


                        }}, {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html(
                                    "<a href='javascript:;' class='btn btn-Lapislazuli btn-circle ver' title='Ver'><i class='fa fa-eye'></i></a>" +
                                    "<a href='javascript:;' class='btn btn-Lapislazuli btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a>" +
                                    "<a href='javascript:;' class='btn btn-danger btn-circle delete' title='Eliminar'><i class='fa fa-minus'></i></a>" +
                                    "</td>"
                                    );
                        }}
                ],
                createdRow: function (row, data, indice) {

                    $(row).find("td:eq(0)").attr('data-id', data.idGrupo);
                    $(row).find("td:eq(0)").attr('data-nom', data.nombreGrupo);
                    $(row).find("td:eq(1)").attr('data-des', data.descGrupo);
                    $(row).find("td:eq(2)").attr('data-de', data.diaEntrada);
                    $(row).find("td:eq(2)").attr('data-ds', data.diaSalida);
                    $(row).find("td:eq(3)").attr('data-te', data.tardeEntrada);
                    $(row).find("td:eq(3)").attr('data-ts', data.tardeSalida);

                }
            });

        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sHorario?action=listarGrupoHorario", "", success, error);

}


var fn_ModH = function (elem) {

    var par = $(elem).parent().parent();
    var tdpag = par.children("td:nth-child(1)");
    var tdnomgrupo = par.children("td:nth-child(1)");
    var tddesgrupo = par.children("td:nth-child(2)");
    var tdde = par.children("td:nth-child(3)");
    var tdds = par.children("td:nth-child(3)");
    var tdte = par.children("td:nth-child(4)");
    var tdts = par.children("td:nth-child(4)");
    var codgrupo = tdpag.attr('data-id') === undefined ? 0 : tdpag.attr('data-id');
    var nombreg = tdnomgrupo.attr('data-nom') === undefined ? 0 : tdnomgrupo.attr('data-nom');
    var descg = tddesgrupo.attr('data-des') === undefined ? 0 : tddesgrupo.attr('data-des');
    var de = tdde.attr('data-de') === undefined ? 0 : tdde.attr('data-de');
    var ds = tdds.attr('data-ds') === undefined ? 0 : tdds.attr('data-ds');
    var te = tdte.attr('data-te') === undefined ? 0 : tdte.attr('data-te');
    var ts = tdts.attr('data-ts') === undefined ? 0 : tdts.attr('data-ts');
    $('#txtidGrupo').val(codgrupo);
    $("#txtGrupo").val(nombreg);
    $("#txtDesGrupo").val(descg);
    $("#txtME").val(de);
    $("#txtMS").val(ds);
    if (te === "12:00AM" && ts === "12:00AM") {
        $("#txtTE").val("");
        $("#txtTS").val();
    } else {
        $("#txtTE").val(te);
        $("#txtTS").val(ts);
    }



    $("#modalGrupoHoras").modal();
};
var fn_ShowHP = function (elem) {

    var par = $(elem).parent().parent();
    var tdpag = par.children("td:nth-child(1)");
    var tdnomgrupo = par.children("td:nth-child(1)");
    idgrupo = tdpag.attr('data-id') === undefined ? 0 : tdpag.attr('data-id');
    nomgrupo = tdnomgrupo.attr('data-nom') === undefined ? 0 : tdnomgrupo.attr('data-nom');
    listaGrupoHorarioPersonal(idgrupo);
    $("#divtblGrupoPersonal").show();
    $("#dvnomgrup").empty();
    $("#dvnomgrup").append("<legend class=\"text-success\">" + nomgrupo + "</legend>");
};
var fn_DelH = function (elem) {
    var par = $(elem).parent().parent();
    var tdpag = par.children("td:nth-child(1)");
    var idgrup = tdpag.attr('data-id') === undefined ? 0 : tdpag.attr('data-id');
    var decision = "del"
    var data = {'idGrupo': idgrup, 'decision': decision};
    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.Message === 'del_ok') {
                toast('success', "ELIMINACION DE GRUPO EXITOSO", 'Exito!');
                setTimeout("location.reload(true);", 500);
            } else
            {
                toast('error', res.Message, 'Error!');
            }


        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    bootbox.confirm("\u00BFEst\u00e1 seguro que desea eliminar el grupo \u003F", function (result) {
        if (result) {
            fn_callmethod("../sHorario?action=eliminarGrupo", data, success, error);
        }
    });




};
function listaGrupoHorarioPersonal(idgrupo) {

    var data = {
        'idgrupo': idgrupo
    };
    actualizarTabla(data, '#tblGrupoPersonal', 1);

}
function actualizarTabla(datos, nombreTabla, accion) {

    var data = {'idGrupo': datos.idgrupo};

    var success = function (res) {

        if (res.Result === 'OK') {
            $(nombreTabla).DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}
                ],

                "aaData": res.listaGrupoHorarioPersonal,
                "columns": [
                    {"data": "personal"},
                    {"data": "oficina"},
                    {"data": "area"},
                    {"data": "puesto"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;' class='btn btn-danger btn-circle delete' title='Eliminar'><i class='fa fa-minus'></i></a>" +
                                    "</td>"
                                    );
                        }}
                ],
                createdRow: function (row, data, indice) {

                    $(row).find("td:eq(0)").attr('data-id', data.idGrupo);
                    $(row).find("td:eq(0)").attr('data-idP', data.idPersona);
                }
            });
        } else {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    if (accion == 1) {
        fn_callmethod("../sHorario?action=listarGrupoHorarioPersonal", data, success, error);
    }
}
;

var oTableH;
$(function () {
    oTableH = fn_iniciarDT(oTableH, "tblGrupoHorario");
    oTableH.on("click", ".ver", function () {
        fn_ShowHP(this);
    });
    oTableH.on("click", ".edit", function () {
        fn_ModH(this);
    });
    oTableH.on("click", ".delete", function () {
        fn_DelH(this);
    });
});

var oTableHP;
$(function () {
    oTableHP = fn_iniciarDT(oTableHP, "tblGrupoPersonal");
    oTableHP.on("click", ".delete", function () {
        fn_removePersonal(this);
    });
});

var fn_removePersonal = function (elem) {

    var par = $(elem).parent().parent();
    var tdpag = par.children("td:nth-child(1)");
    var idpersona = tdpag.attr('data-idP') === undefined ? 0 : tdpag.attr('data-idP');
    var data = {'idPersona': idpersona};
    eliminarPersonal(data);

};

$('#frmGrupoHoras').submit(function (event) {
    event.preventDefault();
    var idGrupo = $('#txtidGrupo').val();
    var decision = "";
    if (idGrupo === "0") {
        decision = 'ins';
        procesoGrupoHorario(decision);
    } else {
        decision = 'upd';
        procesoGrupoHorario(decision);
    }

    $('#modalGrupoHoras').modal('toggle');
    clearmodalGrupoHoras();
});

function procesoGrupoHorario(decision) {

    var data = {'idGrupo': $('#txtidGrupo').val(), 'Grupo': $('#txtGrupo').val().toUpperCase(), 'Descripcion': $('#txtDesGrupo').val().toUpperCase(),
        'MananaEntrada': $('#txtME').val(), 'MananaSalida': $('#txtMS').val(),
        'TardeEntrada': $('#txtTE').val(), 'TardeSalida': $('#txtTS').val(), 'decision': decision
    };
    // actualizarTablaBien(data, '#tblCarBien', 1);

    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.Message === 'ins_ok') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                setTimeout("location.reload(true);", 500);
            } else if (res.Message === 'upd_ok') {
                toast('success', "ACTUALIZACION EXITOSO", 'Exito!');
                setTimeout("location.reload(true);", 500);
            } else
            {
                toast('error', 'Error al crear el grupo', 'Error!');
            }


        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    bootbox.confirm("\u00BFEst\u00e1 seguro que desea Registrar/Actualizar este grupo \u003F", function (result) {
        if (result) {
            fn_callmethod("../sHorario?action=registrarGrupo", data, success, error);
        }
    });



}

function eliminarPersonal(dato) {
    var data = {'idGrupo': idgrupo, 'idpersona': dato.idPersona};
    var success = function (res) {


        if (res.Result === 'OK') {
            if (res.Codigo === '1') {
                toast('success', "ELIMINACION EXITOSA", 'Exito!');
                listaGrupoHorarioPersonal(idgrupo);
                //setTimeout("location.reload(true);", 500);
            } else {
                toast('error', res.Message, 'Error');
            }
        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    bootbox.confirm("\u00BFEst\u00e1 seguro que desea eliminar este trabajador del grupo \u003F", function (result) {
        if (result) {
            fn_callmethod("../sHorario?action=eliminarPersonal", data, success, error);
        }
    });

}

function clearmodalGrupoHoras() {

    $('#txtidGrupo').val("0");
    $("#txtGrupo").val("");
    $("#txtDesGrupo").val("");
    $("#txtME").val("");
    $("#txtMS").val("");
    $("#txtTE").val("");
    $("#txtTS").val("");

}

$("#btnBuscarPersonal").click(function () {
    if ($("#frmPersonal").valid()) {

        var data = {'txtNomEncar': $("#txtNomPersonal").val()};

        var success = function (res) {

            if (res.Result == 'OK') {

                $('#tbl_PersonalHorario').DataTable({
                    destroy: true,
                    "columnDefs": [
                        {"className": "text-center", "targets": "_all"}

                    ],
                    "aaData": res.listaencargado,
                    //rowId: 'idbien', //asigna el id a la fila
                    "columns": [
                        {"data": "objPersonaNatural.nombres"},
                        {"data": "objOficina.nombre"},
                        {"data": "objArea.descripcion"},
                        {
                            "data": null,
                            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                                $(nTd).html("<a href='javascript:;'  class='btn btn-HippieGreen btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                            }}
                    ],
                    createdRow: function (row, data, indice) {
                        $(row).find("td:eq(3)").attr('data-id', data.objPersonaNatural.idPersona);

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
    fn_callmethod("../sEncargado?action=buscarEncargadoBien", data, success, error);

});

var otableEncargado = $('#tbl_PersonalHorario').DataTable();
otableEncargado.on("click", ".select", function () {
    fn_Select(this);
});

var fn_Select = function (elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    var data = {'idPersona': id_persona};
    insertarPersonal(data);
    $('#modalPersonal').modal('toggle');

};

function insertarPersonal(dato) {
    var data = {'idGrupo': idgrupo, 'idpersona': dato.idPersona};
    var success = function (res) {


        if (res.Result === 'OK') {
            if (res.Codigo === '1') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                listaGrupoHorarioPersonal(idgrupo);
                //setTimeout("location.reload(true);", 500);
            } else if (res.Codigo === '0') {
                toast('error', "EL TRABAJADOR YA SE ENCUENTRA REGISTRADO EN OTRO GRUPO", 'Error!');

            } else {
                toast('error', res.Message, 'Error');
            }
        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    bootbox.confirm("\u00BFEst\u00e1 seguro que desea registrar este trabajador al grupo \u003F", function (result) {
        if (result) {
            fn_callmethod("../sHorario?action=registrarHorarioPersonal", data, success, error);
        }
    });

}

$('#btnAgregarHorario').click(function (event) {
    clearmodalGrupoHoras();
    $("#modalGrupoHoras").modal();
});

$('#btnAgregarHorarioPersonal').click(function (event) {

    $("#modalPersonal").modal();
});
$('#txtME').clockpicker({
    placement: 'bottom',
    align: 'left',
    autoclose: true,
    'default': 'now',
    twelvehour: true

});
$('#txtMS').clockpicker({
    placement: 'bottom',
    align: 'left',
    autoclose: true,
    'default': 'now',
    twelvehour: true
});

$('#txtTE').clockpicker({
    placement: 'bottom',
    align: 'left',
    autoclose: true,
    'default': 'now',
    twelvehour: true

});
$('#txtTS').clockpicker({
    placement: 'bottom',
    align: 'left',
    autoclose: true,
    'default': 'now',
    twelvehour: true

});


