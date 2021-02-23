$('#txtFechaDesde').datepicker('setDate', '01/01/2018');
$('#txtFechaHasta').datepicker('setDate', ObtenerFechaActualDMY());
$("#txtFechaDesde").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '1900:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});
$("#txtFechaHasta").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '1900:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});



var oTable;


$(function () {
    oTable = fn_iniciarDT(oTable, "tbl_PersonaJuridica");

    oTable.on("click", ".edit", function () {
        fn_Edit(this);
    });
});


function fn_Edit(elem) {

    var idDoc = $(elem).parent().attr('data-id') === undefined ? 0 : $(elem).parent().attr('data-id');

    separador = "&", arregloDeSubCadenas = idDoc.split(separador);
    var idoficina = arregloDeSubCadenas[0];
    var idDoc = arregloDeSubCadenas[1];
    var tipMoneda = arregloDeSubCadenas[2];
    var nroDoc = arregloDeSubCadenas[3];

    switch (idDoc) {
        case '0001':
            abrirVentana(svrbd + '?%2fContabilidad%2frptFacturaElectronica&rs:Command=Render&' +
                    'nroDoc=' + nroDoc +
                    '&idDoc=' + idDoc +
                    '&idOficina=' + idoficina +
                    '&tipMoneda=' + tipMoneda);
        case '0004' :
            abrirVentana(svrbd + '?%2fContabilidad%2frptNotaCreditoElectronica&rs:Command=Render&' +
                    'nroDoc=' + nroDoc +
                    '&idDoc=' + idDoc +
                    '&idOficina=' + idoficina +
                    '&tipMoneda=' + tipMoneda);
    }
}
;

$('#btnBuscarDocumento').submit(function (e) {
    e.preventDefault();
    var validator = $("#form-buscar-PerJur").valid();
    if (validator) {

    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');
});


$('#btnGenerarRpt').click(function (e) {
    e.preventDefault();
    var id_codigo = $("#fechas").val();
    separador = "&", arregloDeSubCadenas = id_codigo.split(separador);
    var desde = arregloDeSubCadenas[0];
    var hasta = arregloDeSubCadenas[1];
    var tipoMoneda = arregloDeSubCadenas[2];
    var idDoc = arregloDeSubCadenas[3];



    abrirVentana(svrbd + '?%2fContabilidad%2frptDocumentosElectronicos&rs:Command=Render&' +
            'fechaInicio=' + desde +
            '&fechaFin=' + hasta +
            '&tipMoneda=' + tipoMoneda +
            '&idDoc=' + idDoc
            );
    setTimeout("location.reload(true);", 5000);





});

