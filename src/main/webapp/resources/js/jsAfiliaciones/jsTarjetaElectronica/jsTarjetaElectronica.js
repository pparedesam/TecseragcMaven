$(".js-example-basic-hide-search").select2({
    minimumResultsForSearch: Infinity
});


reiniciarTablas();
function reiniciarTablas() {

    var tables = $.fn.dataTable.fnTables(true);

    $(tables).each(function () {
        $(this).dataTable().fnClearTable();
        $(this).dataTable().fnDestroy();
    });

    $("#txtNombrePersonax").text('');
    $("#txtidPersonax").val('');
    if ($("#txtidPersonax").val() == "") {
        $('#btnAdd').prop("disabled", true);

    }
}
;

function fn_Seleccion(elem) {

    var par = $(elem).parent().parent();
    var td_nombres = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");
    var id_codigo = tdbuttons.attr("data-id");


    var data = {'idPersonax': id_codigo};
    var success = function (res) {

        if (res.Result == 'OK') {

            if (res.size != 0) {
                $('#btnAdd').prop("disabled", false);
                $("#txtNombrePersonax").text(td_nombres.html());
                $("#txtidPersonax").val(id_codigo);


                $('#tbl_ListaTarjetas').DataTable({
                    destroy: true,
                    "columnDefs": [
                        {"className": "text-center",
                            "targets": "_all"

                        }
                    ],
                    "aaData": res.Tarjeta,
                    "columns": [
                        {"data": "nroTarjeta"},
                        {"data": "clave",
                            render: function (data, type, row) {
                                return data.length == 4 ? ' &#9679 &#9679 &#9679 &#9679' : ' &#9679 &#9679 &#9679 &#9679';
                            },
                            className: "dt-body-center"
                        },
                        {"data": "estado",
                            render: function (data, type, row) {
                                return data == '1' ? 'ACTIVA' : 'BLOQUEADA';
                            },
                        }, {
                            "data": null,
                            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                                if (sData.estado == '1') {

                                    $(nTd).html("<a href='javascript:;' class='btn btn-Lapislazuli btn-circle selectrow' title='Cambiar Contrase&ntilde;a' data-toggle='modal' data-target='#updatePass'>"
                                            + "<i class='fa fa-edit'></i></a>\n" +
                                            "<a href='javascript:;' class='btn btn-danger btn-circle ban' title='Dar de Baja'><i class='fa fa-caret-down'></i></a>" +
                                            "</td>");
                                } else {
                                    $(nTd).html("");
                                }
                            }}


                    ],
                    createdRow: function (row, data, indice) {

                        $(row).find("td:eq(3)").attr('data-id', id_codigo);

                    }
                });
            } else if (res.size == 0) {
                $('#btnAdd').prop("disabled", false);
                $("#txtNombrePersonax").text(td_nombres.html());
                $("#txtidPersonax").val(id_codigo);

            }
            $("#divTablaPertarj").hide();
        } else if (res.Result == 'error') {
            toast('error', $.trim(res.Message), 'ERROR!');
        }
    };
    var error = function () {
        toast('error', 'ERROR, CONSULTE CON EL AREA DE TI', 'ERROR!');


    };

    fn_callmethod("../sTarjetaElectronica?action=ObtenerTarjetasxPersona", data, success, error);

}
;
function fn_Add() {

    $("#tbl_ListaTarjetas tbody").prepend("<tr>" +
            "<td>" +
            "<input type='text' class='form-control' id='txtnrotarjeta' style='width: 100%'/>" +
            "<input type='hidden' class='form-control' id='txtaccion' style='width: 100%' value='INSERT' disabled='disabled'/>" +
            "</td>" +
            "<td>" + "<input type='password' maxlength='4' class='form-control' id='txtclave' style='width: 100%' />" +
            "</td>" +
            "<td><input type='text' class='form-control' id='txtestado' style='width: 100%' value='ACTIVA' disabled='disabled'/></td>" +
            "<td class='text-center'>" +
            "<a class='btn btn-success btn-circle save' href='javascript:;' title='Guardar' ><i  class='fa fa-save'></i></a>\n" +
            "<a class='btn  btn-warning btn-circle cancel' href='javascript:;' title='Cancelar' ><i class='fa fa-ban'></i></a>" +
            "</td></tr>");
    $('#btnAdd').prop("disabled", true);
}
function fn_Edit(elem) {

    var par = $(elem).parent().parent();
    var tdnro_tarjeta = par.children("td:nth-child(1)");
    var tdclave = par.children("td:nth-child(2)");
    var tdestado = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");



    tdnro_tarjeta.html("<input type='hidden' class='form-control' id='txtaccion' style='width: 100%' value='UPDATE'/> <input type='text' id='txtnrotarjeta' class='form-control input-sm' style='width: 100%' value='" + tdnro_tarjeta.html() + "' disabled='disabled'/>");
    tdclave.html("<input maxlength='4' type='text' id='txtclave' class='form-control input-sm' style='width: 100%' value='" + tdclave.html() + "'/>");
    tdestado.html("<input type='text' id='txtestado' class='form-control input-sm' style='width: 100%' value='" + tdestado.html() + "' disabled='disabled'/>");
    tdbuttons.html('<a class="btn btn-success btn-circle save"><i class="fa fa-save " title="Guardar"></i></a>\n<a class="btn  btn-warning btn-circle cancel" href="javascript:;" title="Cancelar" ><i class="fa fa-ban"></i></a>');

    $('#btnAdd').prop("disabled", true);

}
;
var fn_Cancel = function (elem) {


    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(4)");
    var id = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');


    if (id === 0) {
        par.remove();
        $('#btnAdd').prop("disabled", false);
    } else {
        var par = $(elem).parent().parent();
        var tdnro_tarjeta = par.children("td:nth-child(1)");
        var tdclave = par.children("td:nth-child(2)");
        var tdestado = par.children("td:nth-child(3)");
        var tdbuttons = par.children("td:nth-child(4)");

        tdnro_tarjeta.html(tdnro_tarjeta.children("input[type=text]").val());
        tdclave.html(tdclave.children("input[type=text]").val());
        tdestado.html(tdestado.children("input[type=text]").val());

        tdbuttons.html("<a class='btn btn-Lapislazuli btn-circle edit' title='Editar' data-toggle='modal' data-target='#updatePass'>"
                + "<i class='fa fa-edit'></i></a>" +
                +" " +
                "<a href='javascript:;' class='btn btn-danger btn-circle ban' title='Dar de Baja'><i class='fa fa-caret-down'></i></a>" +
                "</td>");
        $('#btnAdd').prop("disabled", false);
    }

};

var fn_save = function (elem) {

    var par = $(elem).parent().parent();
    var tdnro_tarjeta = par.children("td:nth-child(1)");
    var tdclave = par.children("td:nth-child(2)");
    var tdestado = par.children("td:nth-child(3)");
    var tdbuttons = par.children("td:nth-child(4)");

    var id_codigo = tdbuttons.attr('data-id') === undefined ? $("#txtidPersonax").val() : tdbuttons.attr('data-id');

    var result = validartarjeta(tdnro_tarjeta);
    var result1 = validarclave(tdclave);
    var accion = $("#txtaccion").val();
    bootbox.confirm({
        title: "REGISTRO DE TARJETA",
        message: "ESTA SEGURO QUE DESEA REGISTRAR LA TARJETA",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Cancelar'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Confirmar'
            }
        },
        callback: function (resu) {
            if (resu) {
                if (result === true && result1 === true) {
                    var data = {'codigo': id_codigo, 'nrotarjeta': tdnro_tarjeta.children("input[type=text]").val(),
                        'clave': tdclave.children("input[type=password]").val(), 'accion': accion};

                    var success = function (res) {
                        if (res.Result == 'OK') {
                            if (res.Message == 'ins_ok' || res.Message == 'upd_ok') {
                                tdnro_tarjeta.html(tdnro_tarjeta.children("input[type=text]").val());
                                tdclave.html('&#9679 &#9679 &#9679 &#9679');
                                tdestado.html(tdclave.children("input[type=password]").val());
                                tdbuttons.html("<a href='javascript:;' class='btn btn-Lapislazuli btn-circle selectrow' title='Cambiar Contrase&ntilde;a' data-toggle='modal' data-target='#updatePass'><i class='fa fa-edit'></i></a>\n" +
                                        "<a href='javascript:;' class='btn btn-danger btn-circle ban' title='Dar de Baja'><i class='fa fa-caret-down'></i></a>" +
                                        "</td>");
                                tdbuttons.attr("data-id", res.codigo);
                                $('#btnAdd').prop("disabled", false);
                                abrirVentana(ReportSvr + '?%2fPyAfiliaciones%2fRptRecepcionTarjeta&rs:Command=Render&idPersona=' + id_codigo);
                                toast('success', 'SE REGISTR&Oacute; CORRECTAMENTE', 'Exito!');
                                setTimeout("location.reload(true);", 5000);
                            } else {
                                toast('error', res.Message, 'Error');
                            }
                        } else {
                            toast('error', res.Message, 'Error');
                        }

                    };

                    var error = function (res) {

                        toast('error', res.mensaje, 'Error');
                    };

                    fn_callmethod("../sTarjetaElectronica?action=RegistrarTarjeta", data, success, error);

                } else {
                    toast('error', 'LA TARJETA DEBE CONTENER 16 D&Iacute;GITOS Y LA CLAVE 4', 'ERROR');
                }
            }
        }
    });


};

function validartarjeta(tdnro_tarjeta)
{
    var valor;
    var data = tdnro_tarjeta.children("input[type=text]").val().length;

    if (data == 16)
    {
        valor = true;
    } else
        valor = false;

    return valor;
}

function validarclave(tdclave)
{
    var valor;
    var data = tdclave.children("input[type=password]").val().length;

    if (data == 4)
    {
        valor = true;
    } else
        valor = false;

    return valor;
}

var fn_Ban = function (elem) {

    var par = $(elem).parent().parent();
    var codigo = par.children("td:nth-child(1)").html();


    // var codigo = tdNroTarjeta.html();

    var valid = $('#frmTarjetaElectronicaSocio').valid();
    if (valid) {


        bootbox.prompt({
            title: "INGRESE UN MOTIVO",
            size: 'small',
            inputType: 'select',
            inputOptions: [
                {
                    text: 'ELIJA UN MOTIVO...',
                    value: '',
                },
                {
                    text: 'PERDIDA',
                    value: '0001',
                },
                {
                    text: 'ROBO',
                    value: '0002',
                },
                {
                    text: 'DETERIORO',
                    value: '0003',
                },
                {
                    text: 'CADUCIDAD',
                    value: '0004',
                },
                {
                    text: 'ATM',
                    value: '0005',
                },
                {
                    text: 'OLVIDO DE CLAVE',
                    value: '0007',
                },
                {
                    text: 'RENOVACION',
                    value: '0008',
                }
            ],

            callback: function (result) {

                var data = {'nrotarjeta': codigo, 'estadobloqueo': result};

                if (codigo !== 0) {
                    if (result != null) {
                        var success = function (res) {
                            if (res) {

                                toast('success', 'SE ELIMIN&Oacute; CORRECTAMENTE', 'Exito!');
                                var par = $(elem).parent().parent();


                                var tdestado = par.children("td:nth-child(3)");
                                var tdbuttons = par.children("td:nth-child(4)");
                                tdestado.html("BLOQUEADO");
                                tdbuttons.html("");
                            }
                        };
                        var error = function () {
                            toast('error', 'NO SE PUDO ANULAR LA TARJETA', 'ERROR');
                        };
                        fn_callmethod("../sTarjetaElectronica?action=Anular", data, success, error);
                    }


                }

                // console.log(result);
            }
        });


    }

};
/*
 var fn_Ban = function (elem) {
 
 var par = $(elem).parent().parent();
 var codigo = par.children("td:nth-child(1)").html();
 
 // var codigo = tdNroTarjeta.html();
 if (codigo !== 0) {
 var success = function (res) {
 if (res) {
 
 toast('success', 'SE ELIMIN&Oacute; CORRECTAMENTE', 'Exito!');
 var par = $(elem).parent().parent();
 
 
 var tdestado = par.children("td:nth-child(3)");
 var tdbuttons = par.children("td:nth-child(4)");
 tdestado.html("BLOQUEADO");
 tdbuttons.html("");
 }
 };
 var error = function () {
 toast('error', 'NO SE PUDO ANULAR LA TARJETA', 'ERROR');
 };
 fn_callmethod("../sTarjetaElectronica?action=Anular", {'nrotarjeta': codigo}, success, error);
 
 }
 };
 */
$("#form-updatepass").submit(function (event) {
    event.preventDefault();
    var validator = $("#form-updatepass").valid();

    if (validator)
    {
        comprobarContrasenia();
    } else
        toast('warning', "Usted debe ingresar correctamente los datos requeridos", 'warning');


});

function comprobarContrasenia() {

    var datos = JSON.parse($.session.get('datos'));
    $("span.help-block").hide();
    var data = {'txtUdpAleatorio': datos.txtUdpAleatorio, 'idPersona': datos.idPersona, 'pass': $("#txtPassAct").val(), 'newPass': $("#txtNewPass").val()};
    var success = function (res) {

        if (res.Result === 'ok')
        {
            $('#updatePass').modal('toggle');

            toast('success', '', 'Exito!');
            //  abrirVentana(ReportSvr + '?%2fPyAfiliaciones%2frptSolicitudCambioClave&rs:Command=Render&idPersona=' + datos.idPersona);
            abrirVentana(svrbd + '?%2fpyRptOperaciones%2fRptRecepcionTarjeta&rs:Command=Render&idPersona=' + datos.idPersona);
            $.session.remove('datos');

        } else if (res.Result === 'error')
        {
            hasError("#txtPassAct", res.Message);
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sTarjetaElectronica?action=cambiarClave", data, success, error);
}
$("#txtPassAct").change(function () {
    $("#iconotexto").remove();
    hasSuccess("#txtPassAct");

});

function hasSuccess(nombre) {

    $("#iconotexto").remove();
    $(nombre).parent().parent().attr("class", "form-group");
    $(nombre).parent().children("span").hide();
}

function hasError(nombre, Message) {
    $("#iconotexto").remove();
    $(nombre).parent().parent().attr("class", "form-group has-error has-feedback");
    $(nombre).parent().children("span").text(Message).show();
    $(nombre).parent().append("<span id='iconotexto' class='glyphicon glyphicon-remove form-control-feedback'></span>");
}

function errorMessage(Mensaje) {
    toast('error', Mensaje, 'Error!');
}