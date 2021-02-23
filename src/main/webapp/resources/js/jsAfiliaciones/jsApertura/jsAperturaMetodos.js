
/*
 * accion 1 agregar miembro
 * accion 2 actualizar lista titular
 * acccion 3 eliminar integrante
 */

var pruebaCuenta;
function AddRow(data) {

    var table = $('#tblSocios').DataTable();

    var tableSize = table.data().count();

    if (tableSize >= limiteSocios) {
        toast('warning', 'maximo numero de socios alcanzados', 'CUIDADO');
    } else {
        actualizarTabla(data, '#tblSocios', 1);
    }

    var count = $('#tblSocios tbody tr').length;

    if (count > 0)
    {
        document.getElementById("inlineRadio_1").disabled = false;
        document.getElementById("inlineRadio_2").disabled = false;
    }


}
;

var fn_Select = function (elem) {

    var par = $(elem).parent().parent();
    var td_nroDoc = par.children("td:nth-child(1)");
    var td_nombre = par.children("td:nth-child(2)");
    var tdbuttons = par.children("td:nth-child(3)");
    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    var data = {'nombre': td_nombre.html(), 'idPersona': id_persona, 'nroDoc': td_nroDoc.html()};

    AddRow(data);
    $('#modalBuscar').modal('toggle');

};


function actualizarTabla(datos, nombreTabla, accion) {

    var data = {'idPersona': datos.idPersona, 'nroDoc': datos.nroDoc, 'nombres': datos.nombre};

    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.listaPersona.length == 0) {
                desabilitarDisposicionInteres();
            }
            $(nombreTabla).DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaPersona,
                rowId: 'idPersona', //asigna el id a la fila
                "columns": [
                    {"data": "nroDoc"}, //, "visible": false},
                    {"data": "nombres"},
                    {"data": "titular",
                        render: function (data, type, row) {
                            return data == 1 ? '<input type="radio" name="idRadio" checked="checked">' : '<input type="radio" name="idRadio">';
                        },
                        className: "dt-body-center"
                    }, {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(2)").attr('data-id', data.idPersona);
                    $(row).find("td:eq(2) input:radio").attr('value', data.idPersona);
                }
            });
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    if (accion == 1) {
        fn_callmethod("../sSocio?action=ComprobarLista", data, success, error);
    } else if (accion == 2) {
        fn_callmethod("../sSocio?action=actualizarTitular", data, success, error);
    } else {
        fn_callmethod("../sSocio?action=quitarSocioResponsable", datos, success, error);
    }
}
;

var tablaPersona;
var oTable;

oTable = fn_iniciarDT(oTable, "tblSocios");
oTable.on("click", ".remove", function () {
    var elem = this;
    bootbox.confirm("\u00BFEst\u00e1 seguro de eliminar este socio responsable?\u003F", function (result) {
        if (result) {
            oTable.fnDestroy();
            fn_Delete(elem);
        }
    });
});

var fn_Delete = function (elem) {
    var par = $(elem).parent().parent();
    var tdTitular = par.children("td:nth-child(3)");
    var idPersona = tdTitular.attr('data-id');
    var data = {'idPersona': idPersona};
    actualizarTabla(data, "#tblSocios", 3);

};

function obtenerCuentasIA(idPersona, tipMoneda) {

    var datos = {'idPersona': idPersona, 'tipMoneda': tipMoneda};

    var success = function (data) {

        $("#cboCuentasIA").html("");
        $("#cboCuentasIA").append("<option disabled selected value> -- Selecciones Una Opcion -- </option>");

        console.log(data.cuentasIA.length);
        if(data.cuentasIA.length==0){
            $("#inlineRadio_1").prop("checked", true);
            $("input[id='inlineRadio_1']").trigger("change");
          //  $("input[id='inlineRadio_1']").change();
        }

        $.each(data.cuentasIA, function (i, item) {
            $("#cboCuentasIA").append("<option value='" + item.titular + "'>" + item.objCuenta.ObjOficina.idOficina + "-" + item.objCuenta.ObjTipoCuenta.IdTipCta + "-" + item.objCuenta.ObjTipoCuenta.TipMoneda + "-" + item.objCuenta.NumCuenta + "</option>");
        });
    };
    var error = function () {
    };
    fn_callmethod("../sTipoCuenta?action=obtenerCuentasIA", datos, success, error);
}
;
