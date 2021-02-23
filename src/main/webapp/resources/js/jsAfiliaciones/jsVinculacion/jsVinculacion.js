$(document).ready(function () {

});

//$("#btnBuscarSocio").click(function () {
//    $("#divBuscarSocio").css("display", "block");
//    $("#divtblConstancia").css("display", "none");
//    $("#btnBuscarSocio").css("display", "none");
//    $("#btnCancelar").css("display", "block");
//
//});

$("#txtSocioNaturalJuridico").keypress(function (event) {

    if (event.which == 13) {
        $("#btnBuscarSocioNaturalJuridico").click();
        event.preventDefault();
    }

});

$("#btnBuscarSocioNaturalJuridico").click(function () {
    var validator = $("#frmBuscarPersonal").valid();
    if (validator) {
        buscarPersonal();
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');



});

function buscarPersonal() {

    var data = {'txtCriterioBusqueda': $("#txtSocioNaturalJuridico").val(),
        'optradiobus': $("#cboTipoBusqueda").val()};
    var success = function (res) {

        if (res.Result == 'OK') {

            $('#tbl_SocioJN').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaEmpleado,
                //rowId: 'idbien', //asigna el id a la fila
                "columns": [
                    {"data": "nroDocumento"}, //, "visible": false},
                    {"data": "nombres"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-HippieGreen btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(0)").attr('data-id', data.idPersona);
                    $(row).find("td:eq(0)").attr('data-area', data.objArea.descripcion);
                    $(row).find("td:eq(0)").attr('data-puesto', data.objPuesto.nombre);
                }
            });
            $("#divtblSocio").show();
//            $("#divInfoSocio").css("display", "none");
//            $("#divtblCuentas").css("display", "none");
//            $("#estadoSocio").css("display", "none");
//            $("#panelDatosSocio").css("display", "none");
//            $("#btnGenerarSol").css("display", "none");
        } else if (res.Result == 'error')
        {
            toast('error', res.Message, 'Error!');
        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sEmpleado?action=obtenerPersonal", data, success, error);
}

var otableSocio = $('#tbl_SocioJN').DataTable();

otableSocio.on("click", ".select", function () {
    fn_Select(this);
});

var fn_Select = function (elem) {
    $("#divtblSocio").hide();
//    $("#divInfoSocio").css("display", "none");
    $("#divInfoSocio").show();
    $("#divbuscarUsuario").show();
    $("#divInfoSocio").show();

    var area = "";
    var puesto = "";
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(1)");
    var tdPersonal = par.children("td:nth-child(2)");
    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    area = tdbuttons.attr('data-area') === undefined ? 0 : tdbuttons.attr('data-area');
    puesto = tdbuttons.attr('data-puesto') === undefined ? 0 : tdbuttons.attr('data-puesto');

    $("#txtidPersona").val(id_persona);
    $("#txtNombres").val(tdPersonal.html());
    $("#txtArea").val(area);
    $("#txtPsto").val(puesto);

};

$("#txtBuscarUsuario").keypress(function (event) {

    if (event.which == 13) {
        $("#btnBuscarUsuario").click();
        event.preventDefault();
    }

});

$("#btnBuscarUsuario").click(function () {

    var validator = $("#frmBuscarUsuario").valid();
    if (validator) {
        buscarUsuario();
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');
});

function buscarUsuario() {


    var data = {'criterio': $("#txtBuscarUsuario").val(), 'tipoBusqueda': $("#cboTipoBusquedaUsuario").val()
    };
    var success = function (res) {

        if (res.Result == 'OK') {

            $('#tbl_Usuario').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaUsuario,
                //rowId: 'idbien', //asigna el id a la fila
                "columns": [
                    {"data": "objEmpleado.nroDocumento"},
                    {"data": "objEmpleado.nombres"}, //, "visible": false},

                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-HippieGreen btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(1)").attr('data-idusu', data.idUsuario);

                }
            });
            $("#divtblUsuario").show();
//            $("#divInfoSocio").css("display", "none");
//            $("#divtblCuentas").css("display", "none");
//            $("#estadoSocio").css("display", "none");
//            $("#panelDatosSocio").css("display", "none");
//            $("#btnGenerarSol").css("display", "none");
        } else if (res.Result == 'error')
        {
            toast('error', res.Message, 'Error!');
        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sEmpleado?action=obtenerUsuarioMarcacion", data, success, error);
}
var otableusu = $('#tbl_Usuario').DataTable();

otableusu.on("click", ".select", function () {
    fn_SelectU(this);
});

var fn_SelectU = function (elem) {
    $("#divtblUsuario").hide();
//    $("#divInfoSocio").css("display", "none");


    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(2)");

    var id_usuario = tdbuttons.attr('data-idusu') === undefined ? 0 : tdbuttons.attr('data-idusu');

    $("#txtisUsuMar").val(id_usuario);

    $("#txtUsumar").val(tdbuttons.html());

    document.getElementById("btnVincular").disabled = false;


};

$("#btnVincular").click(function () {
    var data = {'idPersona': $("#txtidPersona").val(),
        'idMarcacion': $("#txtisUsuMar").val(),
    };
    var success = function (res) {

        if (res.Result === 'OK') {

            if (res.Message === 'true') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                setTimeout("location.reload(true);", 500);
            }
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    bootbox.confirm("\u00BFEst\u00e1 seguro que desea vicular el ususario con este personal \u003F", function (result) {
        if (result) {
            fn_callmethod("../sEmpleado?action=vinculacionMarcacion", data, success, error);
        }
    });

})