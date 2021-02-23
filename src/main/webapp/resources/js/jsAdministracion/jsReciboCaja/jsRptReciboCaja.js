let fechaInicio = new Date();
let fechaFin = new Date();


$(document).ready(function () {
   
    $('#txtFechaInicio').datepicker('update', fechaInicio);
    $('#txtFechaFin').datepicker('update', fechaFin);
});

$('#btnGenerarReporte').click(function (e) {
    e.preventDefault();
    let tmpFechaInicio = $('.fechaInicio').val();
    let tmpFechaFin = $('.fechaFin').val();
    
      abrirVentana(svrbd + '?%2fCaja%2frptControlCaja&rs:Command=Render&fechaIni='+tmpFechaInicio+'&fechaFin='+tmpFechaFin);
    setTimeout("location.reload(true);", 5000);
});

$("#txtFechaInicio").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$("#txtFechaFin").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$('#txtFechaInicio').datepicker().on('changeDate', function (e) {
    if ($('.fechaInicio').val() == '') {
        $('#txtFechaInicio').datepicker('update', fecha);
    } else {
        fecha = new Date($('#txtFechaInicio').datepicker('getDate'));
    }
});

$('#txtFechaFin').datepicker().on('changeDate', function (e) {
    if ($('.fechaFin').val() == '') {
        $('#txtFechaFin').datepicker('update', fecha);
    } else {
        fecha = new Date($('#txtFechaFin').datepicker('getDate'));
    }
});




