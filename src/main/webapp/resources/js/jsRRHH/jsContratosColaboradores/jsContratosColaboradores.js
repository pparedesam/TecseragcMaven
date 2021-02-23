var fecInicio = '';
var fecFin = '';
var salario = '';
var tipOpe = '';
var listaHorario;

var canvasf = $("#canvasfoto"),
        contextf = canvasf.get(0).getContext("2d"),
        $resultf = $('#resultfoto');
var cropperBandera = -1;
var croppedImageDataURLFoto = 'data:image/jpeg;base64,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';

$(document).ready(function (event) {

    $('#cboOficina').change(function () {
        listarDptoC($('#cboOficina').val(), '01');
        var ofi = $('#cboOficina').val();
        listarGrupoHorarioLunVieC(ofi, '0');
        listarGrupoHorarioSabadoC(ofi, '0');
        listarHorarioSabadoC('NO');
        $("#TipoHorarioSabado").css("display", "none");
        $("#horarioSabado").css("display", "none");
    });
    $('#cboDepartamento').change(function () {
        listarPuestoDptoOficinaC($('#cboDepartamento').val(), $('#cboOficina').val(), '0');
    });
    $('#cboTipoContrato').change(function () {
        if ($('#cboTipoContrato').val() == 'PLAZO INDETERMINADO') {
            $('#divFecFinal').css("display", "none");
            $('#txtIndeterminado').css("display", "block");
        } else {
            $('#divFecFinal').css("display", "inline-table");
            $('#txtIndeterminado').css("display", "none");
        }
    });

//    $('#cboTipoEPS').change(function () {
//        listarTipoPlanEPS($('#cboTipoEPS').val(), '0');
//    });

    $('#cboHorario').change(function () {
        var LunVie = $("#cboHorario").val();
        mostrarDetallesHorarios(LunVie);
    });


    $('#cboTipoHorarioSabado').change(function () {
        var Sab = $("#cboTipoHorarioSabado").val();
        mostrarDetallesHorariosSab(Sab);
    });

    $('#cboHorarioSabado').change(function () {
        if ($('#cboHorarioSabado').val() == 'NO') {
            $("#TipoHorarioSabado").css("display", "none");
            $("#horarioSabado").css("display", "none");
        }
        if ($('#cboHorarioSabado').val() == 'SI') {

            $("#TipoHorarioSabado").css("display", "block");

            $("#horarioSabado").css("display", "block");
            $("#cboTipoHorarioSabado").index(1);
        }
    });

    handleOrientationChange();

});

$('#btnNuevoEmpleado').click(function (event) {
    tipOpe = 'nEmp';
    $("#panelEmpleadoSinContrato").css("display", "none");
    $("#panelDatosEmpleado").css("display", "block");
    listarBancosC('0');
//    listarSistemaPensiones('0001');
    $("#txtNroCuenta").val('');
    // $("#txtCodigoAFP").val('');
//    listarEPS('0');
//    listarComisionMixta('0');
//
//    $("#txtESSALUD").val('');
//    $("#txtNroHijos").val('0');
//    listarTipoEPS('1');
//    listarTipoPlanEPS('1', '1');
    $("#chbxFiscalizado").prop("disabled", false);

    $("#btnFotoEmpleado").prop("disabled", false);

    listarOficinasC('01');
    listarDptoC('01', '01');
    listarPuestoDptoOficinaC('01', '01', '01');
    $("#txtFecInicio").val('');
    $("#txtSalInicial").val('0');
    $("#txtFecFinal").val('');
    $("#txtSalFinal").val('0');
    $('#txtFecFinal').datepicker('setEndDate', '');

    //listarCargos('0');
    listarTipoContratoC('PLAZO DETERMINADO');
    $('#divFecFinal').css("display", "inline-table");
    $('#txtIndeterminado').css("display", "none");

    $("#btnRegistrarEmpleado").css("display", "block");
    $("#btnCancelar").css("display", "block");

    $("#btnImprimirContrato").css("display", "none");
    $("#btnExportarReporte").css("display", "none");


    $("#cboBanco").prop("disabled", false);
//     $("#cboAFP").prop("disabled", false);
    $("#txtNroCuenta").prop("disabled", false);
//     $("#txtCodigoAFP").prop("disabled", false);
//    $("#cboEPS").prop("disabled", false);
//    $("#TipoPlan").css("display", "none");
//    $('#cboTipoEPS').prop("disabled", true);
//    $('#cboTipoPlan').prop("disabled", true);
//    $("#cboTipoComision").prop("disabled", false);
//    $("#txtESSALUD").prop("disabled", false);
//    $("#txtNroHijos").prop("disabled", false);
//    $('#cboTipoEPS').prop("disabled", true);
//    $('#cboTipoPlan').prop("disabled", true);

    $("#cboHorario").prop("disabled", false);
    $("#cboTipoHorarioSabado").prop("disabled", false);
    $("#cboHorarioSabado").prop("disabled", false);

    $("#txtSalFinal").css("display", "block");
    salario = '0';

    $('#txtSalFinal').css("border", "1px solid");

    var ofi = $("#cboOficina").val();
    listarGrupoHorarioLunVieC('01', '0');
    listarGrupoHorarioSabadoC('01', '0');
    listarHorarioSabadoC('NO');
    $("#TipoHorarioSabado").css("display", "none");
    $("#horarioSabado").css("display", "none");
    var fec = new Date();
    $('#txtFecInicio').datepicker('update', fec);
    validarFecha();
    desbloquearControlesC();
});

$('#btnModificarContrato').click(function (event) {
    tipOpe = 'aCon'
    $("#cboBanco").prop("disabled", false);
    // $("#cboAFP").prop("disabled", false);
    $("#txtNroCuenta").prop("disabled", false);
    // $("#txtCodigoAFP").prop("disabled", false);
    //$("#cboEPS").prop("disabled", false);
    //$("#cboTipoComision").prop("disabled", false);
    //$("#txtESSALUD").prop("disabled", false);
    // $("#txtNroHijos").prop("disabled", false);
    $("#chbxFiscalizado").prop("disabled", false);
    //$('#cboTipoEPS').prop("disabled", false);
    //$('#cboTipoPlan').prop("disabled", false);
    $("#btnFotoEmpleado").prop("disabled", false);

    //$('#cboCargo').prop("disabled", false);

    if ($("#cboTipoContrato").val() == 'PLAZO DETERMINADO') {
        $("#cboTipoContrato").prop("disabled", false);
    }
    $("#txtSalFinal").prop("disabled", false);

    $("#txtFecFinal").prop("disabled", false);

    $("#btnNuevoContrato").prop("disabled", true);
    $("#btnModalCancelarContrato").prop("disabled", true);

    $("#btnActualizarContrato").css("display", "block");
    $("#btnCancelar").css("display", "block");

    $("#btnImprimirContrato").css("display", "none");
    $("#btnExportarReporte").css("display", "none");

    $("#btnRegistrarContrato").css("display", "none");

    $('#txtSalFinal').css("border", "1px solid");

    $("#cboHorario").prop("disabled", false);
    $("#cboTipoHorarioSabado").prop("disabled", false);
    $("#cboHorarioSabado").prop("disabled", false);

    validarFecha();

});

$('#btnNuevoContrato').click(function (event) {
    tipOpe = 'nCon'

    desbloquearControlesC();

    $('#txtSalFinal').css("border", "1px solid");

    $("#btnModificarContrato").prop("disabled", true);
    $("#btnModalCancelarContrato").prop("disabled", true);
    $("#btnActualizarContrato").css("display", "none");

    $("#btnRegistrarContrato").css("display", "block");
    $("#btnCancelar").css("display", "block");

    $("#btnImprimirContrato").css("display", "none");
    $("#btnExportarReporte").css("display", "none");

    validarFecha();
});

$('#btnModalCancelarContrato').click(function (event) {
    var fIni = $('#txtFecInicio').val();

    var fecha1 = new Date("'" + fIni.substr(6, 4) + "-" + fIni.substr(3, 2) + "-" + fIni.substr(0, 2) + "'");
    $('#btnFinContrato').prop("disabled", false);
    fecha1.setDate(fecha1.getDate() + 1);
    $('#txtFecFinContrato').datepicker('setStartDate', fecha1);
    if ($('#cboTipoContrato').val() == 'PLAZO INDETERMINADO') {
        $('#txtFecFinContrato').datepicker('setEndDate', '');
    } else {
        var fFin = $('#txtFecFinal').val();
        var fecha2 = new Date("'" + fFin.substr(6, 4) + "-" + fFin.substr(3, 2) + "-" + fFin.substr(0, 2) + "'");
        $('#txtFecFinContrato').datepicker('setEndDate', fecha2);
    }
    $('#txtFecFinContrato').datepicker('update', fecha1);
});

$('#btnCancelar').click(function (event) {
    var idPersona = $("#txtIdpersona").val();
    obtenerContratosEmpleadoC(idPersona);

    ocultarControlesC();
});

$('#btnFinContrato').click(function (event) {
    var idPersona = $("#txtIdpersona").val();
    var fecFinal = $("#txtFecFinContrato").val();
    var motSalida = $("#cboMotSalida").val();

    bootbox.confirm("\u00BFEst\u00e1 seguro que desea finalizar el contrato \u003F", function (result) {
        if (result) {
            finContratoEmpleado(idPersona, fecFinal, motSalida);
            ocultarControlesC();
            $('#miModal').modal('hide');
        } else {
            $('#miModal').modal('hide');
        }

    });
});

$('#btnActualizarContrato').click(function (event) {
    var idPersona = $("#txtIdpersona").val();

    var Banco = $("#cboBanco").val();
    // var AFP = $("#cboAFP").val();
    var NroCuenta = $("#txtNroCuenta").val();
    //  var CodigoAFP = $("#txtCodigoAFP").val();
    // var EPS = $("#cboEPS").val();
    var Fiscalizado = '0';
    if ($("#chbxFiscalizado").prop('checked')) {
        Fiscalizado = '1';
    }
    ;


//    var PlanEPS = '0';
//    if (EPS == "1") {
//        var PlanEPS = $("#cboTipoPlan").val();
//    }
//    var TipoComision = $("#cboTipoComision").val();
//    var ESSALUD = $("#txtESSALUD").val();
//    var NroHijos = $("#txtNroHijos").val();

    //var idCargo = $("#cboCargo").val();
    var tipContrato = $("#cboTipoContrato").val();
    var fecFinal = $("#txtFecFinal").val();
    var salActual = $("#txtSalFinal").val();
    if ($("#cboTipoContrato").val() == 'PLAZO INDETERMINADO') {
        var fecFinal = 'INDETERMINADO';
    }

    var LunVie = $("#cboHorario").val();
    var Sab = $("#cboTipoHorarioSabado").val();
    if ($('#cboHorarioSabado').val() == 'NO') {
        Sab = '0';
    }

    bootbox.confirm("\u00BFEst\u00e1 seguro que desea actualizar los datos de contrato \u003F", function (result) {
        if (result) {
            actualizarContratoEmpleado(idPersona, Banco, /*, AFP*/ NroCuenta, /*CodigoAFP, EPS, PlanEPS, TipoComision, ESSALUD, NroHijos,*/ tipContrato, fecFinal, salActual, Fiscalizado, LunVie, Sab);
            ocultarControlesC();
        }
    });


});

$('#btnRegistrarContrato').click(function (event) {
    var idPersona = $("#txtIdpersona").val();

    var puesto = $("#cboPuesto").val();
    var departamento = $("#cboDepartamento").val();
    //var idCargo = $("#cboCargo").val();
    var tipContrato = $("#cboTipoContrato").val();
    var oficina = $("#cboOficina").val();
    var fecInicio = $("#txtFecInicio").val();
    var fecFinal = $("#txtFecFinal").val();
    if ($("#cboTipoContrato").val() == 'PLAZO INDETERMINADO') {
        var fecFinal = 'INDETERMINADO';
    }
    var salActual = $("#txtSalFinal").val();

    bootbox.confirm("\u00BFEst\u00e1 seguro que desea registrar un nuevo contrato \u003F", function (result) {
        agregarContratoEmpleado(idPersona, puesto, departamento, tipContrato, oficina, fecInicio, fecFinal, salActual);
        ocultarControlesC();
    });
});

$('#btnRegistrarEmpleado').click(function (event) {
    var idPersona = $("#txtIdpersona").val();

    var Banco = $("#cboBanco").val();
//    var AFP = $("#cboAFP").val();
    var NroCuenta = $("#txtNroCuenta").val();
//    var CodigoAFP = $("#txtCodigoAFP").val();
//    var EPS = $("#cboEPS").val();
//
//    var TipoComision = $("#cboTipoComision").val();
//    var ESSALUD = $("#txtESSALUD").val();
//    var NroHijos = $("#txtNroHijos").val();

    var Fiscalizado = '0';
    if ($("#chbxFiscalizado").prop('checked')) {
        Fiscalizado = '1';
    }
    ;

//    var PlanEPS = '0';
//    if (EPS == "1") {
//        var PlanEPS = $("#cboTipoPlan").val();
//    }

    var puesto = $("#cboPuesto").val();
    var departamento = $("#cboDepartamento").val();
    //var idCargo = $("#cboCargo").val();
    var tipContrato = $("#cboTipoContrato").val();
    var oficina = $("#cboOficina").val();
    var fecInicio = $("#txtFecInicio").val();

    var fecFinal = $("#txtFecFinal").val();
    if ($("#cboTipoContrato").val() == 'PLAZO INDETERMINADO') {
        var fecFinal = 'INDETERMINADO';
    }
    var salActual = $("#txtSalFinal").val();

    var LunVie = $("#cboHorario").val();
    var Sab = $("#cboTipoHorarioSabado").val();

    if ($('#cboHorarioSabado').val() == 'NO') {
        Sab = '0';
    }


    if ($("#cboTipoContrato").val() == 'PLAZO INDETERMINADO') {
        var fecFinal = 'INDETERMINADO';
    }

    bootbox.confirm("\u00BFEst\u00e1 seguro que desea actualizar los datos de contrato \u003F", function (result) {
        if (result) {
            registarEmpleado(idPersona, Banco, /*AFP,*/ NroCuenta, /* CodigoAFP, EPS, PlanEPS, TipoComision, ESSALUD, NroHijos,*/ puesto, departamento, tipContrato, oficina, fecInicio, fecFinal, salActual, Fiscalizado, LunVie, Sab);
            bloquearControlesC();
            ocultarControlesC();
            $("#motSalida").css("display", "none");
            $("#txtSalFinal").css("display", "block")
            fecConIni = new Date($('#txtFecInicio').datepicker('getDate'));
            fecConFin = new Date($('#txtFecFinal').datepicker('getDate'));
        }
    });

});

$('#expandirContrato').click(function (event) {
    $('#expandirContrato').css("display", "none");
    $('#contraerContrato').css("display", "block");
});

$('#contraerContrato').click(function (event) {
    $('#expandirContrato').css("display", "block");
    $('#contraerContrato').css("display", "none");
});

$('#btnImprimirContrato').click(function (event) {
    var IdPersona = $("#txtIdpersona").val();
    if ($("#chbxFiscalizado").prop('checked')) {
        abrirVentana(ReportSvr + '?%2fRecursosHumanos%2fRptContratoGestor&rs:Command=Render&IdPersona=' + IdPersona);
    } else {
        abrirVentana(ReportSvr + '?%2fRecursosHumanos%2fRptContratoAnalista&rs:Command=Render&IdPersona=' + IdPersona);
    }
});

$('#btnExportarReporte').click(function (event) {
    var IdPersona = $("#txtIdpersona").val();
    abrirVentana(ReportSvr + '?%2fRecursosHumanos%2fRptContratosEmpleado&rs:Command=Render&IdPersona=' + IdPersona);
});

var finContratoEmpleado = function (idPersona, fecFinal, motSalida) {

    var data = {'idPersona': idPersona, 'fecFinal': fecFinal, 'motSalida': motSalida};

    var success = function (res) {
        if (res.Result == 'OK') {
            limpiarControlesC();
            bloquearControlesC();
            if (res.resultado == 'del_ok') {
                toast('success', 'SE FINALIZO EL CONTRATO', 'EXITO!');
            } else {
                toast('error', res.resultado, 'ERROR!');
            }
        } else {
            toast('error', res.Result, 'ERROR!');
        }
        ;
        obtenerContratosEmpleadoC(idPersona);
    };

    var error = function (data, status, er) {
    };

    fn_callmethod("../sContrato?action=finContratoColaborador", data, success, error);
}

var registarEmpleado = function (idPersona, Banco, /*AFP,*/ NroCuenta, /*CodigoAFP, EPS, PlanEPS, TipoComision, ESSALUD, NroHijos,*/ puesto, departamento, tipContrato, oficina, fecInicio, fecFinal, salActual, Fiscalizado, LunVie, Sab) {

    var data = {'idPersona': idPersona, 'Banco': Banco, /*'AFP': AFP,*/ 'NroCuenta': NroCuenta, /*'CodigoAFP': CodigoAFP, 'EPS': EPS, 'PlanEPS': PlanEPS, 'TipoComision': TipoComision, 'ESSALUD': ESSALUD, 'NroHijos': NroHijos,*/ 'puesto': puesto, 'departamento': departamento, 'tipContrato': tipContrato, 'oficina': oficina, 'fecInicio': fecInicio, 'fecFinal': fecFinal, 'salActual': salActual, 'croppedImageDataURLFoto': croppedImageDataURLFoto, 'Fiscalizado': Fiscalizado, 'LunVie': LunVie, 'Sab': Sab};

    var success = function (res) {
        if (res.Result == 'OK') {
            bloquearControlesC();
            if (res.resultado == 'in_ok') {
                toast('success', 'REGISTRO EXITOSO', 'EXITO!');
                ocultarControlesC();
            } else {
                toast('error', res.resultado, 'ERROR!');
            }
        } else {
            toast('error', res.Result, 'ERROR!');
        }
        ;
        obtenerContratosEmpleadoC(idPersona);
    };

    var error = function (data, status, er) {
    };
    fn_callmethod("../sContrato?action=registarColaborador", data, success, error);
}

var actualizarContratoEmpleado = function (idPersona, Banco, /*AFP,*/ NroCuenta, /*CodigoAFP, EPS, PlanEPS, TipoComision, ESSALUD, NroHijos,*/ tipContrato, fecFinal, salActual, Fiscalizado, LunVie, Sab) {

    var data = {'idPersona': idPersona, 'Banco': Banco, /*'AFP': AFP,*/ 'NroCuenta': NroCuenta, /*'CodigoAFP': CodigoAFP, 'EPS': EPS, 'PlanEPS': PlanEPS, 'TipoComision': TipoComision, 'ESSALUD': ESSALUD, 'NroHijos': NroHijos,*/ 'tipContrato': tipContrato, 'fecFinal': fecFinal, 'salActual': salActual, 'croppedImageDataURLFoto': croppedImageDataURLFoto, 'Fiscalizado': Fiscalizado, 'LunVie': LunVie, 'Sab': Sab};

    var success = function (res) {
        if (res.Result == 'OK') {
            bloquearControlesC();
            if (res.resultado == 'upd_ok') {
                toast('success', 'ACTUALIZACION EXITOSA', 'EXITO!');
            } else {
                toast('error', res.resultado, 'ERROR!');
            }
        } else {
            toast('error', res.Result, 'ERROR!');
        }
        ;
        obtenerContratosEmpleadoC(idPersona);
    };

    var error = function (data, status, er) {
    };

    fn_callmethod("../sContrato?action=actualizarContratoColaborador", data, success, error);
}

var agregarContratoEmpleado = function (idPersona, puesto, departamento, tipContrato, oficina, fecInicio, fecFinal, salActual) {

    var data = {'idPersona': idPersona, 'puesto': puesto, 'departamento': departamento, 'tipContrato': tipContrato, 'oficina': oficina, 'fecInicio': fecInicio, 'fecFinal': fecFinal, 'salActual': salActual};

    var success = function (res) {
        if (res.Result == 'OK') {
            bloquearControlesC();
            toast('success', 'REGISTRO DE CONTRATO EXITOSO', 'EXITO!');
        } else {
            toast('error', res.Result, 'ERROR!');
        }
        ;
        obtenerContratosEmpleadoC(idPersona);
    };

    var error = function (data, status, er) {
    };

    fn_callmethod("../sContrato?action=agregarContratoColaborador", data, success, error);
}

//var listarSistemaPensiones = function (select) {
//    $('#cboAFP').empty();
//    var success = function (res) {
//        if (res.Result == 'OK') {
//            $.each(res.listaSistemaPensiones, function (i, item) {
//                $('#cboAFP').append("<option value='" + item.IdSistema + "'>" + item.Nombre + "</option>");
//            });
//            $("#cboAFP").val(select).prop('selected', true);
//        }
//    };
//    var error = function (data, status, er) {
//    };
//
//    fn_callmethod("../sSistemaPensiones?action=listarSistemaPensiones", '', success, error);
//}

var listarOficinasC = function (select) {
    $('#cboOficina').empty();
    var success = function (res) {
        if (res.Result == 'OK') {
            $.each(res.listaOficinas, function (i, item) {
                $('#cboOficina').append("<option value='" + item.idOficina + "'>" + item.nombre + "</option>");
            });
            $("#cboOficina").val(select).prop('selected', true);
        }
    };

    var error = function (data, status, er) {

    };

    fn_callmethod("../sOficina?action=listarOficinas", '', success, error);
}

var listarGrupoHorarioC = function () {
    var success = function (res) {
        if (res.Result == 'OK') {
            listaHorario = '';
            listaHorario = res.listaGrupoHorario;
        }
    };
    var error = function (data, status, er) {
    };
    fn_callmethod("../sHorario?action=listarGrupoHorario", '', success, error);
}

var listarGrupoHorarioLunVieC = function (oficina, selectLunVie) {
    $('#cboHorario').empty();
    $.each(listaHorario, function (i, item) {

        if (item.descGrupo == 'HORARIO NORMAL') {
            if (item.diaHorario == 'LUN-VIE') {
                $('#cboHorario').append("<option value='" + item.idGrupo + "'>" + item.nombreGrupo + "</option>");
            }
        }

    });
    if (selectLunVie == '0') {
        $("#cboHorario").index(1);
        var LunVie = $("#cboHorario").val();
        mostrarDetallesHorarios(LunVie);
    } else {
        $("#cboHorario").val(selectLunVie).prop('selected', true);
        var LunVie = $("#cboHorario").val();
        mostrarDetallesHorarios(LunVie);
    }
}

var listarGrupoHorarioSabadoC = function (oficina, selectSab) {

    $('#cboTipoHorarioSabado').empty();
    $.each(listaHorario, function (i, item) {
//        if (oficina == '01') {
            if (item.descGrupo == 'HORARIO SABADO') {
                if (item.diaHorario == 'SABADO') {
                    $('#cboTipoHorarioSabado').append("<option value='" + item.idGrupo + "'>" + item.nombreGrupo + "</option>");
                }
            }
//        } else {
//            if (item.descGrupo == 'AGENCIAS') {
//                if (item.diaHorario == 'SABADO') {
//                    $('#cboTipoHorarioSabado').append("<option value='" + item.idGrupo + "'>" + item.nombreGrupo + "</option>");
//                }
//            }
//        }
    });
    if (selectSab == '0') {
        $("#cboTipoHorarioSabado").index(1);
        var Sab = $("#cboTipoHorarioSabado").val();
        mostrarDetallesHorariosSab(Sab);
    } else {
        $("#cboTipoHorarioSabado").val(selectSab).prop('selected', true);
        var Sab = $("#cboTipoHorarioSabado").val();
        mostrarDetallesHorariosSab(Sab);
    }
}

var mostrarDetallesHorarios = function (LunVie) {
    $.each(listaHorario, function (i, item) {
        if (item.idGrupo == LunVie) {
            $("#txtHoraIngreso").val(item.diaEntrada);
            if (item.tardeSalida == '12:00AM') {
                $("#horaRefrigerio").css("display", "none");
                $("#txtHoraSalida").val(item.diaSalida);
            } else {
                $("#horaRefrigerio").css("display", "block");
                $("#txtHoraSalida").val(item.tardeSalida);
                $("#txtRefrigerioIngreso").val(item.diaSalida);
                $("#txtRefrigerioFin").val(item.tardeEntrada);
            }
        }
    });
}

var mostrarDetallesHorariosSab = function (Sab) {
    $.each(listaHorario, function (i, item) {
        if (item.idGrupo == Sab) {
            $("#txtIngresoSabado").val(item.diaEntrada);
            $("#txtSalidaSabado").val(item.diaSalida);
        }
    });
}

//var listarComisionMixta = function (select) {
//    $('#cboTipoComision').empty();
//    $('#cboTipoComision').append("<option value='0'>NO</option>");
//    $('#cboTipoComision').append("<option value='1'>SI</option>");
//    $("#cboTipoComision").val(select).prop('selected', true);
//
//}

//var listarEPS = function (select) {
//    $('#cboEPS').empty();
//    $('#cboEPS').append("<option value='0'>NO</option>");
//    $('#cboEPS').append("<option value='1'>SI</option>");
//    $("#cboEPS").val(select).prop('selected', true);
//}

var listarHorarioSabadoC = function (select) {
    $('#cboHorarioSabado').empty();
    $('#cboHorarioSabado').append("<option value='NO'>NO</option>");
    $('#cboHorarioSabado').append("<option value='SI'>SI</option>");
    $("#cboHorarioSabado").val(select).prop('selected', true);
}

//var listarTipoEPS = function (select) {
//    $('#cboTipoEPS').empty();
//    var success = function (res) {
//        if (res.Result == 'OK') {
//            $.each(res.listaOficinas, function (i, item) {
//                $('#cboTipoEPS').append("<option value='" + item.idTipoEPS + "'>" + item.nombre + "</option>");
//            });
//            if (select == '0') {
//                $("#cboTipoPlan").index(1);
//            } else {
//                $("#cboTipoPlan").val(select).prop('selected', true);
//            }
//        }
//    };
//    var error = function (data, status, er) {
//    };
//    fn_callmethod("../sTipoEPS?action=listarTipoEPS", '', success, error);
//}

//var listarTipoPlanEPS = function (idTipoEPS, select) {
//    $('#cboTipoPlan').empty();
//    var data = {'idTipoEPS': idTipoEPS};
//
//    var success = function (res) {
//        if (res.Result == 'OK') {
//            $.each(res.listaTipoPlanEPS, function (i, item) {
//                $('#cboTipoPlan').append("<option value='" + item.idEPS + "'>" + item.descripcion + "</option>");
//            });
//            if (select == '0') {
//                $("#cboTipoPlan").index(1);
//            } else {
//                $("#cboTipoPlan").val(select).prop('selected', true);
//            }
//
//            //listarTipoEPS(tipoEPS);
//        }
//    };
//    var error = function (data, status, er) {
//    };
//    fn_callmethod("../sTipoPlanEPS?action=listarTipoPlanEPS", data, success, error);
//}

var listarTipoContratoC = function (select) {
    $('#cboTipoContrato').empty();
    $('#cboTipoContrato').append("<option value='PLAZO DETERMINADO'>PLAZO DETERMINADO</option>");
    $('#cboTipoContrato').append("<option value='PLAZO INDETERMINADO'>PLAZO INDETERMINADO</option>");
    $("#cboTipoContrato").val(select).prop('selected', true);
}

var listarBancosC = function (select) {

    $('#cboBanco').empty();
    var success = function (res) {
        if (res.Result == 'OK') {
            $.each(res.listaBancos, function (i, item) {
                $('#cboBanco').append("<option value='" + item.objPersonaBanco.idPersona + "'>" + item.nombreBanco + "</option>");
            });

            if (select == '0') {
                $("#cboBanco").index(1);
            } else {
                $("#cboBanco").val(select).prop('selected', true);
            }

        }
    };

    var error = function (data, status, er) {

    };

    fn_callmethod("../sBanco?action=listarBancos", '', success, error);
}

var listarDptoC = function (idOficina, select) {

    var data = {'idOficina': idOficina};
    $('#cboDepartamento').empty();
    var success = function (res) {
        if (res.Result == 'OK') {
            $.each(res.listaAreaOficina, function (i, item) {
                $('#cboDepartamento').append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
            });
            $("#cboDepartamento").val(select).prop('selected', true);
        }
    };

    var error = function (data, status, er) {

    };

    fn_callmethod("../sArea?action=listaAreaOficina", data, success, error);
}

var listarPuestoDptoOficinaC = function (idDpto, idOficina, select) {

    var data = {'idDpto': idDpto, 'idOficina': idOficina};
    $('#cboPuesto').empty();
    var success = function (res) {
        if (res.Result == 'OK') {
            $.each(res.listaPuestos, function (i, item) {
                $('#cboPuesto').append("<option value='" + item.idPuesto + "'>" + item.nombre + "</option>");
            });
            if (select == '0') {
                $("#cboPuesto").index(1);
            } else {
                $("#cboPuesto").val(select).prop('selected', true);
            }
        }
    };

    var error = function (data, status, er) {

    };

    fn_callmethod("../sPuesto?action=listarPuestoDptoOficina", data, success, error);
}

//var listarCargos = function (select) {
//    $('#cboCargo').empty();
//    var success = function (res) {
//        if (res.Result == 'OK') {
//            $.each(res.listaCargos, function (i, item) {
//                $('#cboCargo').append("<option value='" + item.idCargo + "'>" + item.nombreCargo + "</option>");
//            });
//            if (select == '0') {
//               $("#cboBanco").index(1);
//            } else {
//                $("#cboCargo").val(select).prop('selected', true);
//            }
//            
//        }
//    };
//
//    var error = function (data, status, er) {
//
//    };
//
//    fn_callmethod("../sCargo?action=listarCargos", '', success, error);
//}

var limpiarControlesC = function () {
    listarOficinasC('01');
    listarDptoC($('#cboOficina').val(), '01');
    listarPuestoDptoOficinaC($('#cboDepartamento').val(), $('#cboOficina').val(), '0');
    $("#txtFecInicio").val('');
    $("#txtSalInicial").val('0');
    $("#txtFecFinal").val('');
    $("#txtSalFinal").val('0');
}

var desbloquearControlesC = function () {
    $("#cboOficina").prop("disabled", false);
    $("#cboDepartamento").prop("disabled", false);
    $("#cboPuesto").prop("disabled", false);
    // $("#cboCargo").prop("disabled", false);

    if ($("#cboTipoContrato").val() == "PLAZO DETERMINADO") {
        $("#cboTipoContrato").prop("disabled", false);
    } else {
        $("#cboTipoContrato").prop("disabled", true);
    }

    $("#txtFecInicio").prop("disabled", false);
    $("#txtFecFinal").prop("disabled", false);
    $("#txtSalFinal").prop("disabled", false);
}

var bloquearControlesC = function () {

    $("#cboBanco").prop("disabled", true);
//    $("#cboAFP").prop("disabled", true);
    $("#txtNroCuenta").prop("disabled", true);
//    $("#txtCodigoAFP").prop("disabled", true);
//    $("#cboEPS").prop("disabled", true);
//    $("#cboTipoComision").prop("disabled", true);
//    $("#txtESSALUD").prop("disabled", true);
//    $("#txtNroHijos").prop("disabled", true);
    $("#btnFotoEmpleado").prop("disabled", true);
//    $('#cboTipoEPS').prop("disabled", true);
//    $('#cboTipoPlan').prop("disabled", true);
    $('#chbxFiscalizado').prop("disabled", true);

    $("#cboOficina").prop("disabled", true);
    $("#cboDepartamento").prop("disabled", true);
    $("#cboPuesto").prop("disabled", true);
    //$("#cboCargo").prop("disabled", true);
    $("#cboTipoContrato").prop("disabled", true);
    $("#txtFecInicio").prop("disabled", true);
    $("#txtFecFinal").prop("disabled", true);
    $("#txtSalFinal").prop("disabled", true);

    $("#cboHorario").prop("disabled", true);
    $("#cboTipoHorarioSabado").prop("disabled", true);
    $("#cboHorarioSabado").prop("disabled", true);

    $('#txtSalFinal').css("border", "0px solid");

    fecInicio = '';
    fecFin = '';
    salario = '';
    tipOpe = '';
}

var ocultarControlesC = function () {
    $("#btnRegistrarContrato").css("display", "none");
    $("#btnActualizarContrato").css("display", "none");
    $("#btnRegistrarEmpleado").css("display", "none");

    $("#btnCancelar").css("display", "none");

    $("#NuevoContrato").css("display", "block");
    $("#NuevoEmpleado").css("display", "none");
    $("#ModificarContrato").css("display", "block");
    $("#CancelarContrato").css("display", "block");

    $("#btnImprimirContrato").css("display", "block");
    $("#btnExportarReporte").css("display", "block");


    $("#btnNuevoContrato").prop("disabled", false);
    $("#btnModificarContrato").prop("disabled", false);
    $("#btnModalCancelarContrato").prop("disabled", false);

}

$('#txtSalFinal').change(function () {
    newSalario = $('#txtSalFinal').val();
    if (parseInt(newSalario) < parseInt(salario)) {
        $('#txtSalFinal').val(salario);
        $('#txtSalFinal').css("border", "1px solid red");
        toast('error', 'EL SALARIO NO PUEDE SER MENOR AL ACTUAL', 'ERROR!');
    } else {
        $('#txtSalFinal').val(newSalario);
        $('#txtSalFinal').css("border", "1px solid blue");
    }
});

//$('#txtNroHijos').change(function () {
//    nroHijo = $('#txtNroHijos').val();
//    if (parseInt(nroHijo) < 0) {
//        $('#txtNroHijos').val('0');
//    } else {
//        $('#txtNroHijos').val(nroHijo);
//    }
//})

//$('#cboEPS').change(function () {
//    if ($('#cboEPS').val() == '0') {
//        $("#TipoPlan").css("display", "none");
//        $('#cboTipoEPS').prop("disabled", true);
//        $('#cboTipoPlan').prop("disabled", true);
//    }
//    if ($('#cboEPS').val() == '1') {
//        $("#TipoPlan").css("display", "block");
//        $("#cboTipoPlan").index(1);
//        listarTipoPlanEPS($('#cboTipoEPS').val(), '0');
//        $('#cboTipoEPS').prop("disabled", false);
//        $('#cboTipoPlan').prop("disabled", false);
//    }
//});

$('#txtFecInicio').datepicker({
    autoclose: 'true',
    format: 'dd/mm/yyyy',
    language: 'es',
});

$('#txtFecFinal').datepicker({
    autoclose: 'true',
    format: 'dd/mm/yyyy',
    language: 'es',
});

$('#txtFecFinContrato').datepicker({
    autoclose: 'true',
    format: 'dd/mm/yyyy',
    language: 'es',
});

$('#txtFecFinContrato').datepicker().on('changeDate', function (e) {
    if ($('#txtFecFinContrato').val() == '') {
        var fIni = $('#txtFecInicio').val();
        var fecha1 = new Date("'" + fIni.substr(6, 4) + "-" + fIni.substr(3, 2) + "-" + fIni.substr(0, 2) + "'");
        fecha1.setDate(fecha1.getDate() + 1);
        $('#txtFecFinContrato').datepicker('update', fecha1);
    }
});

$('#txtFecInicio').datepicker().on('changeDate', function (e) {
    if (tipOpe == 'nEmp') {
        if ($('#txtFecInicio').val() == '') {
            fecCon1 = new Date();
            $('#txtFecInicio').datepicker('update', fecCon1);
        }
    } else if (tipOpe == 'nCon') {
        if ($('#txtFecInicio').val() == '') {
            fecConIni = new Date("'" + fecInicio.substr(0, 4) + "-" + fecInicio.substr(5, 2) + "-" + fecInicio.substr(8, 2) + "'");
            $('#txtFecInicio').datepicker('update', fecConIni);
        }
    }
    validarFecha();
});

$('#txtFecFinal').datepicker().on('changeDate', function (e) {

    if (tipOpe == 'nEmp') {
        if ($('#txtFecFinal').val() == '') {
            fecFin1 = new Date($('#txtFecInicio').datepicker('getDate'));
            fecFin1.setDate(fecFin1.getDate() + 1);
            $('#txtFecFinal').datepicker('setStartDate', fecFin1);
            $('#txtFecFinal').datepicker('setEndDate', '');
            $('#txtFecFinal').datepicker('update', fecFin1);
        }
    } else if (tipOpe == 'nCon') {
        if ($('#txtFecFinal').val() == '') {
            fecConFin = new Date("'" + fecFin.substr(6, 4) + "-" + fecFin.substr(3, 2) + "-" + fecFin.substr(0, 2) + "'");
            if (isNaN(fecConFin.getDate())) {  // d.valueOf() could also work
                fecConFin = new Date("'" + fecFin.substr(6, 4) + "-" + fecFin.substr(3, 2) + "-" + fecFin.substr(0, 2) + "'");
            }
            $('#txtFecFinal').datepicker('update', fecConFin);
        }
    } else if (tipOpe == 'aCon') {
        if ($('#txtFecFinal').val() == '') {
            fecConFin = new Date("'" + fecFin.substr(6, 4) + "-" + fecFin.substr(3, 2) + "-" + fecFin.substr(0, 2) + "'");
            if (isNaN(fecConFin.getDate())) {  // d.valueOf() could also work
                fecConFin = new Date("'" + fecFin.substr(0, 4) + "-" + fecFin.substr(5, 2) + "-" + fecFin.substr(8, 2) + "'");
                if (isNaN(fecConFin.getDate())) {  // d.valueOf() could also work
                    fecConFin = new Date("'" + fecFin.substr(6, 4) + "-" + fecFin.substr(3, 2) + "-" + fecFin.substr(0, 2) + "'");
                }
            }
            $('#txtFecFinal').datepicker('update', fecConFin);
        }
    }

    //validarFecha(); 
});

var validarFecha = function () {
    fecConIni = new Date("'" + fecInicio.substr(0, 4) + "-" + fecInicio.substr(5, 2) + "-" + fecInicio.substr(8, 2) + "'");
    fecConFin = new Date("'" + fecFin.substr(0, 4) + "-" + fecFin.substr(5, 2) + "-" + fecFin.substr(8, 2) + "'");
    if (isNaN(fecConFin.getDate())) {  // d.valueOf() could also work
        fecConFin = new Date("'" + fecFin.substr(6, 4) + "-" + fecFin.substr(3, 2) + "-" + fecFin.substr(0, 2) + "'");
    }

    var fecha = new Date($('#txtFecInicio').datepicker('getDate'));
    if (tipOpe == 'nEmp') {
        //fecha = new Date();
        fecha.setDate(fecha.getDate() + 1);
        $('#txtFecFinal').datepicker('setStartDate', fecha);
        $('#txtFecFinal').datepicker('update', fecha);
    } else if (tipOpe == 'aCon') {
        fecha = new Date(fecConFin);
        fecha.setDate(fecha.getDate() + 1);
        $('#txtFecInicio').datepicker('setStartDate', fecConIni);
        $('#txtFecInicio').datepicker('setEndDate', fecha);

        $('#txtFecFinal').datepicker('setStartDate', fecConFin);
        $('#txtFecFinal').datepicker('setEndDate', '');
        $('#txtFecFinal').datepicker('update', fecConFin);
    } else if (tipOpe == 'nCon') {
        fecha = new Date(fecConFin);
        fecha.setDate(fecha.getDate() + 1);

        $('#txtFecInicio').datepicker('setStartDate', fecConIni);
        $('#txtFecInicio').datepicker('setEndDate', fecha);
        var fechaElegida = new Date($('#txtFecInicio').datepicker('getDate'));
        $('#txtFecFinal').datepicker('setEndDate', '');
        if (fechaElegida >= fecConFin) {
            fechaElegida.setDate(fechaElegida.getDate() + 1);
            $('#txtFecFinal').datepicker('setStartDate', fechaElegida);
            $('#txtFecFinal').datepicker('update', fechaElegida);
        } else {
            $('#txtFecFinal').datepicker('setStartDate', fecConFin);
            $('#txtFecFinal').datepicker('update', fecConFin);
        }
    }
}

$('#btnFotoEmpleado').change(function () {
    var archivoInput = $('#btnFotoEmpleado');
    var archivoRuta = archivoInput.val();
    var extPermitidas = /(.jpg|.JPG)$/i;
    if (!extPermitidas.exec(archivoRuta)) {
        toast('error', 'FORMATO DE ARCHIVO NO PERMITIDO', 'ERROR!');
        archivoInput.val('');
        return false;
    } else {
        if (this.files && this.files[0]) {
            if (this.files[0].type.match(/^image\//)) {
                var readerf = new FileReader();
                readerf.onload = function (evt) {
                    var imgf = new Image();
                    imgf.onload = function () {
                        contextf.canvas.height = 700;
                        contextf.canvas.width = 540;
                        contextf.drawImage(imgf, 0, 0, imgf.width, imgf.height, 0, 0, 540, 700);
                        croppedImageDataURLFoto = contextf.canvas.toDataURL("image/png");
                        $("#fotoEmpleado").prop("src", croppedImageDataURLFoto);
                        $resultf.empty();

                        $resultf.append($('<img id="fotoEmpleado">').attr('src', croppedImageDataURLFoto));
                    };
                    imgf.src = evt.target.result;
                };
                readerf.readAsDataURL(this.files[0]);
            } else {
                toast('error', 'FORMATO DE ARCHIVO NO PERMITIDO', 'ERROR!');
            }
        } else {
            canvasf.clearRect(0, 0, contextf.canvas.width, contextf.canvas.height);
            $resultf.empty();
        }
    }
});

function handleOrientationChange() {
    if (screen.width <= 1366 && screen.width >= 600) {
        $("#divDatosEmpleado").removeClass("col-md-10 col-md-12").addClass("col-md-8");
        ;
        $("#divfotoEmpleado").removeClass("col-md-2 col-md-8").addClass("col-md-4");

    } else if (screen.width > 1366) {

        $("#divDatosEmpleado").removeClass("col-md-8 col-md-12").addClass("col-md-10");
        ;
        $("#divfotoEmpleado").removeClass("col-md-4 col-md-8").addClass("col-md-2");
    } else if (screen.width < 600) {

        $("#divDatosEmpleado").removeClass("col-md-8 col-md-10").addClass("col-md-12");
        $("#divfotoEmpleado").removeClass("col-md-4 col-md-2").addClass("col-md-8");
        $("#fotoEmpleado").width(screen.width - 200);

    }
}

function salSoloNumero(e) {
    var key = window.Event ? e.which : e.keyCode

    return ((key >= 48 && key <= 57) || (key == 46))
}

function SoloNumero(e) {
    var key = window.Event ? e.which : e.keyCode

    return ((key >= 48 && key <= 57))
}