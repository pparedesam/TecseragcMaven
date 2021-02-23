var oTable;

oTable = fn_iniciarDT(oTable, "tblListaEmpleados");

var listarEmpleados = function (listEmpleados) {
    $('#tblListaEmpleados').DataTable({
        destroy: true,
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
        "aaData": listEmpleados,
        "columns": [
            {"data": "nroDocumento", "width": "25%", className: "text-center"},
            {"data": "nombres", "width": "50%"},
            {"data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a class='btn btn-Lapislazuli btn-circle show_con title='ver Contratos'><i class='fa fa-eye'></i></a>");
                },
                className: "text-center", "width": "25%"}
        ],

        createdRow: function (row, data, indice) {
            $(row).find("td:eq(2)").attr('data-id', data.idPersona);
        }
    });
    listarGrupoHorarioC();
};

oTable.on("click", ".show_con", function () {
    fn_Show_Con(this);
});

var fn_Show_Con = function (elem) {
    var par = $(elem).parent().parent();
    var tdNombre = par.children("td:nth-child(2)");
    var tdNroDoc = par.children("td:nth-child(1)");
    var tdbuttons = par.children("td:nth-child(3)");
    var idPersona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    $("#txtNombreEmpleado").val(tdNombre.html());
    $("#txtNroDoc").val(tdNroDoc.html());

    obtenerContratosEmpleadoC(idPersona);

};

var obtenerContratosEmpleadoC = function (idPersona) {

    var data = {'idPersona': idPersona};
    var success = function (res) {

        if (res.Result == 'OK') {
            $("#txtIdpersona").val(idPersona);

            bloquearControlesC();
            $("#motSalida").css("display", "none");
            $("#txtSalFinal").css("display", "block")

            if (res.ContratosEmpleado.objContratoActual.fecInicio == undefined) {

                $("#NuevoContrato").css("display", "none");

                $("#NuevoEmpleado").css("display", "block");
                $("#btnNuevoEmpleado").css("display", "block");
                $("#ModificarContrato").css("display", "none");
                $("#CancelarContrato").css("display", "none");
                croppedImageDataURLFoto = 'data:image/jpeg;base64,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';
                $("#txtCorreo").val('');
                $("#txtUsuario").val('');
                if (res.ContratosEmpleado.objEmpleado.foto == undefined) {
                    $("#fotoEmpleado").prop("src", croppedImageDataURLFoto);
                } else {
                    var foto = res.ContratosEmpleado.objEmpleado.foto;

                    if (foto.toString() != "") {
                        $("#fotoEmpleado").prop("src", "data:image/jpg;base64," + res.ContratosEmpleado.objEmpleado.foto);
                        croppedImageDataURLFoto = "data:image/jpg;base64," + res.ContratosEmpleado.objEmpleado.foto;
                    } else {
                        $("#fotoEmpleado").prop("src", croppedImageDataURLFoto);
                    }
                }



                $("#panelEmpleadoSinContrato").css("display", "block");
                $("#panelDatosEmpleado").css("display", "none");
            } else {

                $("#NuevoContrato").css("display", "block");
                $("#NuevoEmpleado").css("display", "none");
                $("#ModificarContrato").css("display", "block");
                $("#CancelarContrato").css("display", "block");
                $("#panelEmpleadoSinContrato").css("display", "none");
                $("#panelDatosEmpleado").css("display", "block");

                $("#txtCorreo").val(res.ContratosEmpleado.objEmpleado.correoEmp);
                $("#txtUsuario").val(res.ContratosEmpleado.objEmpleado.usuarioEmp);
                listarBancosC(res.ContratosEmpleado.objEmpleado.Banco.IdBanco);

                //listarSistemaPensiones(res.ContratosEmpleado.objEmpleado.objSistemaPensiones.IdSistema);

                croppedImageDataURLFoto = 'data:image/jpeg;base64,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';
                if (res.ContratosEmpleado.objEmpleado.foto == undefined) {
                    $("#fotoEmpleado").prop("src", croppedImageDataURLFoto);
                } else {
                    var foto = res.ContratosEmpleado.objEmpleado.foto;
                    if (foto.toString() != "") {
                        $("#fotoEmpleado").prop("src", "data:image/jpg;base64," + res.ContratosEmpleado.objEmpleado.foto);
                        croppedImageDataURLFoto = "data:image/jpg;base64," + res.ContratosEmpleado.objEmpleado.foto;
                    } else {
                        $("#fotoEmpleado").prop("src", croppedImageDataURLFoto);
                    }
                }
               // var tipoEPS = res.ContratosEmpleado.objEmpleado.objTipoPlanEPS.objTipoEPS.idTipoEPS;


               // var tipoPlanEPS = res.ContratosEmpleado.objEmpleado.objTipoPlanEPS.idEPS;


                var fiscalizado;
                fiscalizado = res.ContratosEmpleado.objEmpleado.fiscalizado;

                if (fiscalizado == '0') {
                    $("#chbxFiscalizado").prop('checked', false);
                } else {
                    $("#chbxFiscalizado").prop('checked', true);
                }

                $("#txtNroCuenta").val(res.ContratosEmpleado.objEmpleado.CtaSueldo);
//                $("#txtCodigoAFP").val(res.ContratosEmpleado.objEmpleado.CodigoSistema);
//                listarEPS(res.ContratosEmpleado.objEmpleado.EPS);
//                if (res.ContratosEmpleado.objEmpleado.EPS == '1') {
//                    $("#TipoPlan").css("display", "block");
//                    listarTipoEPS(tipoEPS);
//                    listarTipoPlanEPS(tipoEPS, tipoPlanEPS);
//
//                } else {
//                    $("#TipoPlan").css("display", "none");
//                    listarTipoEPS('0');
//                    listarTipoPlanEPS($('#cboTipoEPS').val(), '0');
//                }
//                listarComisionMixta(res.ContratosEmpleado.objEmpleado.ComisionMixta);
//                $("#cboTipoComision").val(res.ContratosEmpleado.objEmpleado.ComisionMixta);
//                $("#txtESSALUD").val(res.ContratosEmpleado.objEmpleado.ESSALUD);
//                $("#txtNroHijos").val(res.ContratosEmpleado.objEmpleado.nroHijos);



                listarOficinasC(res.ContratosEmpleado.objContratoActual.oficina.idOficina);
                listarDptoC(res.ContratosEmpleado.objContratoActual.oficina.idOficina, res.ContratosEmpleado.objContratoActual.area.codigo);

                listarPuestoDptoOficinaC(res.ContratosEmpleado.objContratoActual.area.codigo, res.ContratosEmpleado.objContratoActual.oficina.idOficina, res.ContratosEmpleado.objContratoActual.puesto.idPuesto)

                //listarCargos(res.ContratosEmpleado.objContratoActual.objCargo.idCargo);
                listarTipoContratoC(res.ContratosEmpleado.objContratoActual.tipoContrato);
                //$("#cboTipoContrato").val(res.ContratosEmpleado.objContratoActual.tipoContrato);
                fecInicio = res.ContratosEmpleado.objContratoActual.fecInicio;

                $("#txtFecInicio").val(fecInicio.substr(8, 2) + "/" + fecInicio.substr(5, 2) + "/" + fecInicio.substr(0, 4));

                $("#txtSalInicial").val(res.ContratosEmpleado.objContratoActual.remIncio);
                fecFin = res.ContratosEmpleado.objContratoActual.fecFin;

                if (fecFin == 'INDETERMINADO') {
                    $("#txtIndeterminado").css("display", "block");
                    $("#divFecFinal").css("display", "none");

                } else {
                    $("#txtIndeterminado").css("display", "none");
                    $('#divFecFinal').css("display", "inline-table");
                    var fFin = new Date("'" + fecFin.substr(0, 4) + "-" + fecFin.substr(5, 2) + "-" + fecFin.substr(8, 2) + "'");
                    if (isNaN(fFin.getDate())) {  // d.valueOf() could also work
                        fFin = new Date("'" + fecFin.substr(6, 4) + "-" + fecFin.substr(3, 2) + "-" + fecFin.substr(0, 2) + "'");
                    }

                    $("#txtFecFinal").datepicker('update', fFin);

                }
                salario = res.ContratosEmpleado.objContratoActual.remFinal;
                $("#txtSalFinal").val(salario);
                var LunVie = res.ContratosEmpleado.objEmpleado.objHorarioLunVie.idGrupo;
                var Sab = res.ContratosEmpleado.objEmpleado.objHorarioSab.idGrupo;
                var ofi = res.ContratosEmpleado.objContratoActual.oficina.idOficina;
                if (Sab == '0') {
                    listarHorarioSabadoC('NO');
                    $("#TipoHorarioSabado").css("display", "none");
                    $("#horarioSabado").css("display", "none");
                } else {
                    listarHorarioSabadoC('SI');
                    $("#TipoHorarioSabado").css("display", "block");
                    $("#horarioSabado").css("display", "block");
                }
                listarGrupoHorarioLunVieC(ofi, LunVie);
                listarGrupoHorarioSabadoC(ofi, Sab);
                //validarFecha();
            }

            $('#tblHistorialContratos').DataTable({
                destroy: true,
                responsive: true,
                ordering: false,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"},
                    {"className": "dt-body-center", "targets": "_all"}
                ],
                "aaData": res.ContratosEmpleado.objHistorialContratos,
                "columns": [
                    {"data": "puesto.nombre", "width": "50%", className: "text-center"},
                    {"data": "fecInicio", className: "text-center"},
                    {"data": "remIncio", className: "text-center"},
                    {"data": "fecFin", className: "text-center"},
                    {"data": "remFinal", className: "text-center"},
                    {"data": "area.descripcion", className: "text-center"},
                    {"data": "oficina.nombre", className: "text-center"},
                    {"data": "tipoContrato", className: "text-center"}
                ],
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
                }
            });



            $("#divTablaEmpleados").css("display", "none");
            $("#datosEmpleado").css("display", "block");

        }
    };

    var error = function (data, status, er) {

    };

    fn_callmethod("../sContrato?action=obtenerContratosColaborador", data, success, error);
};










