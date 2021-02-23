var listPais;
var listDepartamentos;
var listProvincias;
var listDistritos;
var listUrbanizacion;

$(function () {

    obtenerUbigeo();
});

function obtenerUbigeo() {
    var success = function (data) {

        listPais = '';
        listDepartamentos = '';
        listProvincias = '';
        listDistritos = '';
        listPais = data.listaUbigeo[0];
        listDepartamentos = data.listaUbigeo[1];
        listProvincias = data.listaUbigeo[2];
        listDistritos = data.listaUbigeo[3];

        obtenerPaises('#cboPartidaPais', '0');
        obtenerDepartamentos('#cboPartidaDepartamento', '0');
        obtenerProvincia('#cboPartidaProvincia', '0', '0');
        obtenerDistrito('#cboPartidaDistrito', '0', '0');
    };
    var error = function () {};
    fn_callmethod("../sUbigeo?action=obtenerUbigeo", '', success, error);
}
;

$("#cboDepartamentoS").change(function () {
    var x;
    x = $("#cboDepartamentoS").val();
    obtenerProvincia('#cboProvinciasS', '0', x);
    x = $("#cboProvinciasS").val();
    obtenerDistrito('#cboDistritoS', '0', x);
    x = $("#cboDistritoS").val();
    obtenerUrbanizaciones('#cboUrbanizacionS', '0', x);
});

$("#cboDepartamentoO").change(function () {
    var x;
    x = $("#cboDepartamentoO").val();
    obtenerProvincia('#cboProvinciasO', '0', x);
});

$("#cboDepartamentoB").change(function () {
    var x;
    x = $("#cboDepartamentoB").val();
    obtenerProvincia('#cboProvinciasB', '0', x);
});

$("#cboProvinciasS").change(function () {
    var x;
    x = $("#cboProvinciasS").val();
    obtenerDistrito('#cboDistritoS', '0', x);
    x = $("#cboDistritoS").val();
    obtenerUrbanizaciones('#cboUrbanizacionS', '0', x);
});
$("#cboDistritoS").change(function () {
    var x;
    x = $("#cboDistritoS").val();
    obtenerUrbanizaciones('#cboUrbanizacionS', '0', x);
});


function obtenerPaises(combobox, select) {
    $(combobox).empty();
    $.each(listPais, function (i, item) {
        $(combobox).append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
       // $(combobox).trigger("refresh");
    });
    if (select == '0') {
        $(combobox).index(1);
    } else {
        $(combobox).val(select).prop('selected', true);
    }
}
;

function obtenerDepartamentos(combobox, select) {
    $(combobox).empty();
    $.each(listDepartamentos, function (i, item) {
        $(combobox).append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
    });
    if (select == '0') {
        $(combobox).index(1);
    } else {
        $(combobox).val(select).prop('selected', true);
    }

}
;
function obtenerProvincia(combobox, select, region) {
    $(combobox).empty();
    $.each(listProvincias, function (i, item) {
        if (item.region === region) {
            $(combobox).append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
        }
    });
    if (select == '0') {
        $(combobox).index(1);
    } else {
        $(combobox).val(select).prop('selected', true);
    }
}
;
function obtenerDistrito(combobox, select, provincia) {
    $(combobox).empty();
    $.each(listDistritos, function (i, item) {
        if (item.provincia === provincia) {
            $(combobox).append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
        }
    });
    if (select == '0') {
        $(combobox).index(1);
    } else {
        $(combobox).val(select).prop('selected', true);
    }
}
;


