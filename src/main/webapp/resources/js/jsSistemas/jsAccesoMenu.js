$(function () {

    obtenerOficina();
    $("#cboOficina").change(obtenerDptoxOficina);
    $("#cboDpto").change(obtenerPstoxDptoxOficina);
    $("#cboPsto").change(obtenerMenu);
});
function obtenerOficina()
{

    var success = function (data) {

        $.each(data.listOficina, function (i, item) {
            $("#cboOficina").append("<option value='" + item.idOficina + "'>" + item.nombre + "</option>");
            $('#cboOficina').trigger("refresh");
        });
        if ($("#cboOficina option").length > 1) {
            $("#cboOficina option:eq('01')").prop('selected', true);
        }
        $('#cboOficina').select2();
        $('#cboOficina').trigger('change.select2');
        $('#cboOficina').change();
    };
    var error = function () {};
    fn_callmethod("../sAccesoMenu?action=obtenerOficina", '', success, error);
}
;
function obtenerDptoxOficina() {

    var datos = {'idOficina': $("#cboOficina").val()};
    var success = function (data) {
        $("#cboDpto").html("");
        $.each(data.listArea, function (i, item) {
            $("#cboDpto").append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
        });
        if ($("#cboDpto option").length > 1) {
            $("#cboDpto option:eq('00')").prop('selected', true);
        }
        $('#cboDpto').select2();
        $('#cboDpto').trigger('change.select2');
        $('#cboDpto').change();
    };
    var error = function () {};
    fn_callmethod("../sAccesoMenu?action=obtenerDptoxOficina", datos, success, error);
}
;
function obtenerPstoxDptoxOficina()
{
    var datos = {'idOficina': $("#cboOficina").val(), 'idDpto': $("#cboDpto").val()};
    var success = function (data) {

        if (data.Result == 'OK') {
            $("#cboPsto").html("");
            $.each(data.listPuesto, function (i, item) {
                $("#cboPsto").append("<option value='" + item.idPuesto + "' >" + item.nombre + "</option>");
            });
            if ($("#cboPsto option").length > 1) {
                $("#cboPsto option:eq('00')").prop('selected', true);
            }
            $('#cboPsto').select2();
            $('#cboPsto').trigger('change.select2');
            $('#cboPsto').change();
        } else {
            toast('Error', data.Message, 'warning');
        }
    };
    var error = function () { };
    fn_callmethod("../sAccesoMenu?action=obtenerPstoxDptoxOficina", datos, success, error);
}
;
function obtenerMenu() {
    var data = {'idOficina': $("#cboOficina").val(), 'idDpto': $("#cboDpto").val(), 'idPsto': $("#cboPsto").val()};
    actualizarTabla(data, '#tblMenu', 1);
}

function actualizarTabla(datos, nombreTabla, accion)
{

    var data = {'idOficina': datos.idOficina, 'idDpto': datos.idDpto, 'idPsto': datos.idPsto, 'idMenu': datos.idMenu, 'estado': datos.estado,
        'dependiente': datos.dependiente, 'dependiente2': datos.dependiente2};
    var success = function (res) {
        if (res.Result === 'OK') {
            $(nombreTabla).DataTable({
                destroy: true,
                ordering: false,
                "bFilter": true,
                "lengthChange": false,
                "paging": false,
                "bInfo": false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all", }
                ],
                "aaData": res.listaMenu,
                "columns": [
                    {"data": 'descripcionN1'},
                    {"data": function (row, type, val, meta) {
                            if (row.nivel === '2') {
                                return row.descripcion;
                            } else {
                                return row.descripcionN2;
                            }

                        }},
                    {"data": function (row, type, val, meta) {
                            if (row.nivel === '3') {
                                return row.descripcion;
                            } else {
                                return  null;
                            }

                        }},
//                    {"data": null,
//                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
//                            if (sData.nivel === '3')
//                                $(nTd).html(sData.descripcion + "</td>");
//                            else
//                                $(nTd).html(+"</td>");
//                        }},
                    {"data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            if (sData.estado === '1') {
                                $(nTd).html(
                                        "<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle remove' title='Eliminar'><i class='fa fa-check'></i></a>"
                                        // "<input type='checkbox' id='chkestado' checked /><label for='chkestado'></label>"
                                        );
                            } else {
                                $(nTd).html(
                                        "<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-times'></i></a>"
                                        //"<input type='checkbox' id='chkestado'/><label for='chkestado'></label>"
                                        );
                            }
                        }},
                ],
                createdRow: function (row, data, indice) {

                    $(row).find("td:eq(0)").attr('data-idMenu', data.idMenu);
                    $(row).find("td:eq(0)").attr('data-estado', data.estado);
                    $(row).find("td:eq(0)").attr('data-dependiente', data.dependiente);
                    $(row).find("td:eq(0)").attr('data-dependiente2', data.dependiente2);
                }
            });
        } else {
            toast('error', res.Message, 'Error');
        }
    };
    var error = function (res) {
        toast('error', res, 'ERROR!');
    };
    if (accion == 1) {
        fn_callmethod("../sAccesoMenu?action=obtenerListaMenu", data, success, error);
    } else if (accion == 2) {
        fn_callmethod("../sAccesoMenu?action=cambiarestadoMenu", data, success, error);
    }



}
;
var oTable;
$(function () {
    oTable = fn_iniciarDT(oTable, "tblMenu");
    oTable.on("click", ".remove", function () {
        fn_EditEstado(this);
    });
});
var fn_EditEstado = function (elem) {
    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(1)");
    var idMenu = tdbuttons.attr("data-idMenu");
    var estado = tdbuttons.attr("data-estado");
    var dependiente = tdbuttons.attr("data-dependiente");
    var dependiente2 = tdbuttons.attr("data-dependiente2");
    var data = {'idOficina': $("#cboOficina").val(), 'idDpto': $("#cboDpto").val(), 'idPsto': $("#cboPsto").val(), 'idMenu': idMenu, 'estado': estado,
        'dependiente': dependiente, 'dependiente2': dependiente2}
    ;
    actualizarTabla(data, "#tblMenu", 2);
};
$('#btnActualizarMenu').click(function (e) {

    e.preventDefault();
    actualizarMenu();
});
function actualizarMenu() {

    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.Message === '1') {
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
    bootbox.confirm("\u00BFEst\u00e1 seguro que desea actualizar el menu\u003F", function (result) {
        if (result) {
            fn_callmethod("../sAccesoMenu?action=actualizarMenu", "", success, error);
        }
    });
}
;
/*
 $('#btnprueba').click(function (e) {
 
 e.preventDefault();
 var table = $('#tblMenu').DataTable();
 var miVector1 = new Array();
 var data = table.rows().data();
 data.each(function (value, index) {
 miVector1.push(value);
 });
 
 
 function getProtAcionValues(rowNo, columnCount)
 {
 for (var j = 0; j < columnCount; j++)
 {
 miVector1[j] = document.getElementById('textActions' + rowNo + '' + j).value;
 alert(miVector1[j]);
 }
 }
 
 var success = function (data) {
 
 };
 var error = function () {};
 fn_callmethod("../sAccesoMenu?action=pruebita", miVector1, success, error);
 
 });
 */