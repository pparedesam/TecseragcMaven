
function listarBancos(listBancos) {
    $('#tblBancos').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "zeroRecords": "Nada encontrado - lo siento",
            "info": "Mostrando la p&aacute;gina _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registro disponibles.",
            "infoFiltered": "(Filtrado _MAX_ registros en total)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Ultimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            }
        },
        destroy: true, "lengthChange": false, "paging": true, "bInfo": false,
        responsive: true,
        "autoWidth": false,
        ordering: false,
        "pageLength": 20,
        "columnDefs": [
            {
                "className": "text-center",
                "targets": "_all"
            }
        ],
        "aaData": listBancos,
        "columns": [
            {"data": "nombreBanco", "width": "50%", className: "text-center"},
            {"data": "nombreAbv", "width": "20%"},
            {"data": "objPersonaBanco.nroDocumento", "width": "20%"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-Lapislazuli btn-circle select_Banco title='Banco'><i class='fa fa-eye'></i></a></td>");
                },
                className: "text-center", "width": "10%"}
        ], createdRow: function (row, data, indice) {
            $(row).find("td:eq(0)").attr('data-id', data.objPersonaBanco.idPersona);

        }
    });
}

$('#tblBancos').on("click", ".select_Banco", function () {
    fn_Select_Banco(this);
});

function fn_Select_Banco(elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(1)");

    idpersona = '';
    idpersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    cargarBanco(idpersona);
    cargarCtasBanco(idpersona);
    $("#btnBuscar").hide();
    TabSelec("RegBancos");

}
;

function listarCtaBanco(listCtasBanco) {
    var tmplistarCtaBanco = listCtasBanco.filter(function (cuenta) {
        return cuenta.accion !== 'D';
    });
    $('#tblCtaBancos').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "zeroRecords": "Nada encontrado - lo siento",
            "info": "Mostrando la p&aacute;gina _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registro disponibles.",
            "infoFiltered": "(Filtrado _MAX_ registros en total)",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Ultimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            }
        },
        destroy: true, "lengthChange": false, "paging": true, "bInfo": false,
        responsive: true,
        "autoWidth": false,
        ordering: false,
        "pageLength": 20,
        "columnDefs": [
            {
                "className": "text-center",
                "targets": "_all"
            }
        ],
        "aaData": tmplistarCtaBanco,
        "columns": [
            {"data": "objTipoCtaBanco.tipoCtaBanco", "width": "25%", className: "text-center"},
            {"data": "ctaBanco", "width": "15%"},
            {"data": "objTipoMoneda.descripcion", "width": "15%"},

            {"data": "fechaApertura", "width": "15%"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-danger btn-circle deleteCta title='Banco'><i class='fa fa-minus'></i></a></td>");
                },
                className: "text-center", "width": "10%"}
        ], createdRow: function (row, data, indice) {
            $(row).find("td:eq(0)").attr('data-id', data.idPersona);
            $(row).find("td:eq(0)").attr('data-idtipMoneda', data.objTipoMoneda.id);
            $(row).find("td:eq(0)").attr('data-idtipCtaBanco', data.objTipoCtaBanco.idTipCtaBanco);

        }
    });
}

$('#tblCtaBancos').on("click", ".deleteCta", function () {
    fn_deleteCta(this);
});

function fn_deleteCta(elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(1)");
    var ctaBanco = par.children("td:nth-child(2)").text();


    var tipMoneda = tdbuttons.attr('data-idtipMoneda') === undefined ? 0 : tdbuttons.attr('data-idtipMoneda');
    var tipCtaBanco = tdbuttons.attr('data-idtipCtaBanco') === undefined ? 0 : tdbuttons.attr('data-idtipCtaBanco');


    listCtasBanco.forEach(function (elemento, indice) {
        if (elemento.ctaBanco.trim() === ctaBanco.trim() && elemento.objTipoCtaBanco.idTipCtaBanco.trim() === tipCtaBanco.trim() && elemento.objTipoMoneda.id.trim() === tipMoneda.trim()) {
            if (elemento.accion == 'I') {
                listCtasBanco.splice(indice, 1);
            } else {
                var tmpObjDescuentoCredito = new Object();

                var tmpObjTipCtaBanco = new Object();
                tmpObjTipCtaBanco.idTipCtaBanco = elemento.objTipoCtaBanco.idTipCtaBanco;
                tmpObjTipCtaBanco.tipoCtaBanco = elemento.objTipoCtaBanco.tipoCtaBanco;

                var tmpObjTipoMoneda = new Object();
                tmpObjTipoMoneda.id = elemento.objTipoMoneda.id;
                tmpObjTipoMoneda.descripcion = elemento.objTipoMoneda.descripcion;

                //tmpObjDescuentoCredito.idPersona = elemento.idPersona;
//                tmpObjDescuentoCredito.idTipCtaBanco = elemento.idTipCtaBanco;
//                tmpObjDescuentoCredito.descripcionCtaBanco = elemento.descripcionCtaBanco;
                tmpObjDescuentoCredito.objTipoCtaBanco = tmpObjTipCtaBanco;
                tmpObjDescuentoCredito.ctaBanco = elemento.ctaBanco;
//                tmpObjDescuentoCredito.idMoneda = elemento.idMoneda;
//                tmpObjDescuentoCredito.Monedas = elemento.Monedas;
                tmpObjDescuentoCredito.objTipoMoneda = tmpObjTipoMoneda;
                tmpObjDescuentoCredito.fechaApertura = elemento.fechaApertura;
                tmpObjDescuentoCredito.accion = 'D';
                listCtasBanco.splice(indice, 1, tmpObjDescuentoCredito);
            }
        }
    });
    listarCtaBanco(listCtasBanco);
//alert(listCtasBanco.length);

}
;


function BuscarPersonaJuridica() {


    var data = {'txtNroDoc': $("#txtNroDocPJ").val(), 'cboTipoBusqueda': $("#cboTipoBusquedaPJ").val()};

    var success = function (res) {

        if (res.Result == 'OK') {

            $('#tblPJBuscar').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaPersonaJuridica,
                //rowId: 'idbien', //asigna el id a la fila
                "columns": [
                    {"data": "nombres", "width": "60%", className: "text-center"},
                    {"data": "nroDocumento", "width": "30%", className: "text-center"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle selectPJ' title='Ver'><i class='fa fa-plus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(0)").attr('data-id', data.idPersona);
                    $(row).find("td:eq(0)").attr('data-pj', data.nombres);

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

    fn_callmethod("../sPersonaJuridica?action=BuscarPersonaJuridicaNoBanco", data, success, error);
}
;

$('#tblPJBuscar').on("click", ".selectPJ", function () {
    fn_Select_PJ(this);
});

function fn_Select_PJ(elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(1)");
    var ruc = par.children("td:nth-child(2)");

    idpersona = '';

    idpersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    var razonSocial = tdbuttons.attr('data-pj') === undefined ? 0 : tdbuttons.attr('data-Pj');

    $('#txtRazonSocial').val(razonSocial);
    $('#txtRUC').val(ruc.html());
    document.getElementById("txtAbreviatura").readOnly = false;

    $('#modalPJBuscar').modal('hide');

}
;