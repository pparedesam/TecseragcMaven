var varible = $("#txtVariable").val();
if (varible !== undefined)
    TabSelec(varible);

function TabSelec(idtab)
{
    $('#tabs a[href="#' + idtab + '"]').tab('show');
    $("#tabs a").click(function () {
        $("[id*=" + idtab + "]").val($(this).attr("href").replace("#", ""));
    });
    $("#txtVariable").val(idtab);
}
;

$(document).ready(function () {

    obtenerCuentaBco();

    function obtenerCuentaBco() {
        var data = {'cboBanco': $("#cboBanco").val(), 'cboTipoMoneda': $("#cboTipoMoneda").val()};

        $('#cboctabco').empty();
        var success = function (data) {

            //$("#cboctabco").append("<option value='0'>TODOS </option>");
            $.each(data.listaCtaBanco, function (i, item) {
                $("#cboctabco").append("<option value='" + item.idTipCtaBanco + "'>" + item.ctaBanco + "</option>");
            });
        };
        var error = function () { };

        fn_callmethod("../sCtaBanco?action=obtenerCtaBanco", data, success, error);
    }
    ;
    $('#cboBanco').on('change', function (e) {
        obtenerCuentaBco();
    });
    $('#cboTipoMoneda').on('change', function (e) {
        obtenerCuentaBco();
    });
});

$('#btnRegistrarCheque').click(function (e) {
    e.preventDefault();
    var validator = $("#form-registrarCheque").valid();
    if (validator) {
        registroCheque();
    } else
        toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');
});

function registroCheque() {




    var id_codigo = $('#cboctabco').find('option:selected').text();
    separador = "/", // un espacio en blanco
            arregloDeSubCadenas = id_codigo.split(separador);

    var ctabanco = arregloDeSubCadenas[1];
    var glosavariable = $("#txtcheque").val() + " " + $("#txtNombre").val();
    var fecha = $("#txtFecCheq").val();
    var tipomoneda = $("#cboTipoMoneda").val();
    var nrocheque = $("#txtcheque").val();
    var idpersona = $("#txtidPersonaCliente").val();
    var idpersonabco = $("#cboBanco").val();
    var idtipctabanco = $("#cboctabco").val();
    var glosa = $("#txtglosa").val();
    var monto = $("#txtMonto").val();



    var data = {
        'idpersona': idpersona,
        'tipomoneda': tipomoneda,
        'fecha': fecha,
        'glosa': glosa,
        'glosavariable': glosavariable,
        'idtipctabanco': idtipctabanco,
        'ctabanco': ctabanco,
        'idpersonabco': idpersonabco,
        'nrocheque': nrocheque,
        'monto': monto

    };
    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.Message === 'OK') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                setTimeout("location.reload(true);", 5000);
            
            }
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    bootbox.confirm("\u00BFEst\u00E1 seguro que desea Registrar a este Cheque\u003F", function (result) {
        if (result) {
            fn_callmethod("../sCheque?action=registrarCheque", data, success, error);
        }
    });

}
;

var oTableBF;
$(function () {
    oTableBF = fn_iniciarDT(oTableBF, "tbl_Cheque");

    oTableBF.on("click", ".remove", function () {
        fn_Remove(this);
    });

    function fn_Remove(elem) {

        var par = $(elem).parent().parent();
        var tdbuttons = par.children("td:nth-child(7)");
        var id_codigo = tdbuttons.attr("data-id");
        separador = "&", // un espacio en blanco
                arregloDeSubCadenas = id_codigo.split(separador);
        var idoficina = arregloDeSubCadenas[0];
        var IdDoc = arregloDeSubCadenas[1];
        var TipMoneda = arregloDeSubCadenas[2];
        var NroDoc = arregloDeSubCadenas[3];
        var data = {
            'idoficina': idoficina,
            'IdDoc': IdDoc,
            'TipMoneda': TipMoneda,
            'NroDoc': NroDoc,
        };
        var success = function (res) {

            if (res.resultado == 'true') {


                toast('success', "ANULACION EXITOSA", 'Exito!');
                setTimeout("location.reload(true);", 500);


            } else if (res.Result == 'error') {
                toast('error', res.Message, 'ERROR!');
            }
        };
        var error = function () {
            toast('error', 'Error , Consulte con el Area de TI', 'ERROR!');
        };

        bootbox.confirm("\u00BFEst\u00E1 seguro que desea Anular este Cheque\u003F", function (result) {
            if (result) {
                fn_callmethod("../sCheque?action=anularCheque", data, success, error);
            }
        });



    }
    ;

});



$('#txtFecCheq').datepicker('setDate', ObtenerFechaActualDMY());
$("#txtFecCheq").datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange: '1900:2999',
    dateFormat: 'dd/mm/yyyy',
    minDate: 0,
    defaultDate: null
}).on('change', function () {
    $(this).valid();
});

function toggle(elemento) {
    if (elemento.value == "1") {


        document.getElementById("txtFechaDesde").value = '';
        document.getElementById("txtFechaHasta").value = '';
        document.getElementById("txtFechaDesde").disabled = false;
        document.getElementById("txtFechaHasta").disabled = false;
        document.getElementById("txtBuscarNroDoc").disabled = true;


    } else {
        if (elemento.value == "2") {
            document.getElementById("txtFechaDesde").disabled = true;
            document.getElementById("txtFechaHasta").disabled = true;
            document.getElementById("txtBuscarNroDoc").disabled = false;
            document.getElementById("txtBuscarNroDoc").value = '';

        }
    }
}
;

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
