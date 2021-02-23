
$(document).ready(function () {
//    $("#cboModalResponsable").select2();
    $("#cboTipoFecha").select2();
    $("#cboDpto").select2();
    $("#cboPersonal").select2();
    obtenerOficina();
    $("#cboOficina").change(obtenerDptoxOficina);
    $("#cboDpto").change(listarModalResponsable);


});


$("#cboTipoFecha").change(function () {

    var x;
    x = $("#cboTipoFecha").val();
    if (x === '1') {
        $("#divFecUnica").show();
        $("#divMesAño").hide();
        $("#divRangoFec").hide();
        $("#txtfechaMesAnio").val('');
        $("#txtfechaMesAnio").datepicker('update');
        $("#txtFechaInicio").val('');
        $("#txtFechaInicio").datepicker('update');
        $("#txtFechaFin").val('');
        $("#txtFechaFin").datepicker('update');

    } else if (x === '2') {

        $("#divMesAño").show();
        $("#divFecUnica").hide();
        $("#divRangoFec").hide();
        $("#txtFechaUnica").val('');
        $("#txtFechaUnica").datepicker('update');
        $("#txtFechaInicio").val('');
        $("#txtFechaInicio").datepicker('update');
        $("#txtFechaFin").val('');
        $("#txtFechaFin").datepicker('update');

    } else if (x === '3') {

        $("#divRangoFec").show();
        $("#divFecUnica").hide();
        $("#divMesAño").hide();
        $("#txtfechaMesAnio").val('');
        $("#txtfechaMesAnio").datepicker('update');
        $("#txtFechaUnica").val('');
        $("#txtFechaUnica").datepicker('update');

    }
});

$("#txtfechaMesAnio").datepicker({
    autoclose: true,
    format: "mm/yyyy",
    viewMode: "months",
    minViewMode: "months"

});
$('#txtFechaUnica').datepicker({
    format: 'dd/mm/yyyy',
    language: 'es',
    autoclose: true,
});

$('#txtFechaInicio').datepicker({
    format: 'dd/mm/yyyy',
    language: 'es',
    autoclose: true,
});

$('#txtFechaFin').datepicker({
    format: 'dd/mm/yyyy',
    language: 'es',
    autoclose: true,
});


$('#btnGenerarRptResCar').click(function (e) {
    e.preventDefault();
    var x;
    x = $("#cboTipoFecha").val();

    var IdOficina = ($("#cboOficina").val() == '0') ? '' : '&IdOficina=' + $("#cboOficina").val();
    var CodTra = ($("#cboPersonal").val() == '0') ? '' : '&codTra=' + $("#cboPersonal").val();
    var IdDpto = ($("#cboDpto").val() == '0') ? '' : '&iddpto=' + $("#cboDpto").val();


    if (x === '1') {

        var fechaUnica = $("#txtFechaUnica").val();

        if (fechaUnica == '') {

            toast('error', 'Debe seleccionar la fecha', 'Error!');

        } else {

            abrirVentana(ReportSvr + '?%2fRecursosHumanos%2fRptMovTardanzaxFecha&rs:Command=Render&FechaIni=' + fechaUnica + '&FechaFin=' + fechaUnica + IdOficina + IdDpto);

        }
    } else if (x === '2') {

        if ($("#txtfechaMesAnio").val() == '') {

            toast('error', 'Debe seleccionar la fecha', 'Error!');

        } else {
            var mesAnio = $("#txtfechaMesAnio").datepicker('getDate');

            var primerDia = new Date(mesAnio.getFullYear(), mesAnio.getMonth(), 1);
            var ultimoDia = new Date(mesAnio.getFullYear(), mesAnio.getMonth() + 1, 0);
            var primer = (((primerDia.getDate() > 9) ? primerDia.getDate() : ('0' + primerDia.getDate())) + '/' + ((primerDia.getMonth() > 8) ? (primerDia.getMonth() + 1) : ('0' + (primerDia.getMonth() + 1))) + '/' + primerDia.getFullYear());
            var ultimo = (((ultimoDia.getDate() > 9) ? ultimoDia.getDate() : ('0' + ultimoDia.getDate())) + '/' + ((ultimoDia.getMonth() > 8) ? (ultimoDia.getMonth() + 1) : ('0' + (ultimoDia.getMonth() + 1))) + '/' + ultimoDia.getFullYear());

            abrirVentana(ReportSvr + '?%2fRecursosHumanos%2fRptMovTardanzaxFecha&rs:Command=Render&FechaIni=' + primer + '&FechaFin=' + ultimo + IdOficina + IdDpto);

        }

    } else if (x === '3') {
        var fechaIni = $("#txtFechaInicio").val();
        var fechaFin = $("#txtFechaFin").val();

        if (fechaIni == '' || fechaFin == '') {
            toast('error', 'Debe seleccionar la fecha inicio y/o final', 'Error!');
        } else if (validate_fechaMayorQue(fechaIni, fechaFin)) {
            abrirVentana(ReportSvr + '?%2fRecursosHumanos%2fRptMovTardanzaxFecha&rs:Command=Render&FechaIni=' + fechaIni + '&FechaFin=' + fechaFin + IdOficina + IdDpto);
        } else {
            toast('error', 'La fecha inicio no debe ser mayor a la final', 'Error!');
        }



    }
});

function obtenerOficina()
{

    var success = function (data) {
        $('#cboOficina').empty();
        //$('#cboOficina').select();
        // $("#cboOficina").append("<option value='0'>TODOS </option>");
        $.each(data.listOficina, function (i, item) {
            $("#cboOficina").append("<option value='" + item.idOficina + "'>" + item.nombre + "</option>");

        });
        $("#cboOficina").index(1)
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
        $('#cboDpto').empty();
        $("#cboDpto").append("<option value='0'>TODOS </option>");
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
function listarModalResponsable() {

    var datos = {'idOficina': $("#cboOficina").val(), 'idDpto': $("#cboDpto").val()};


    var success = function (res) {
        if (res.Result == 'OK') {
            $('#cboPersonal').empty();
            $("#cboPersonal").append("<option value='0'>TODOS </option>");
            $.each(res.listaEmpleados, function (i, item) {
                $('#cboPersonal').append("<option value='" + item.idPersona + "'>" + item.nombres + "</option>");
            });

        }
    };

    var error = function (data, status, er) {
    };

    fn_callmethod("../sEmpleado?action=listarEmpleados", datos, success, error);
}

function validate_fechaMayorQue(fechaInicial, fechaFinal)
{
    valuesStart = fechaInicial.split("/");
    valuesEnd = fechaFinal.split("/");

    // Verificamos que la fecha no sea posterior a la actual
    var dateStart = new Date(valuesStart[2], (valuesStart[1] - 1), valuesStart[0]);
    var dateEnd = new Date(valuesEnd[2], (valuesEnd[1] - 1), valuesEnd[0]);
    if (dateStart >= dateEnd)
    {
        return 0;
    }
    return 1;
}
