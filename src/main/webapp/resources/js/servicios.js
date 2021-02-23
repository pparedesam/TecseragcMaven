var ReportServer = 'http://190.41.141.164:808';
//var ReportSvr="http://190.41.141.164:808/ReportServer/Pages/ReportViewer.aspx";
var svrbd = 'http://svrbd/ReportServer/Pages/ReportViewer.aspx';
//var ReportSvr = "http://andrewaw:8090/ReportSvr/Pages/ReportViewer.aspx";
//"http://svrbd/ReportServer/Pages/ReportViewer.aspx";

//?%2fPyContabilidad%2frptDetalleFactura&rs:Command=Render

var fn_callmethod = function (url, data, success, error) {
	$("#contenedor_carga").removeClass('hidden');
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json",
        success: success,
        complete: function(){
            $("#contenedor_carga").addClass('hidden');
        },
        error:function(){
            $("#contenedor_carga").addClass('hidden');
            toast('error', 'SE SUPERO EL TIEMPO DE ESPERA', 'ERROR')
        }
    });
};

var fn_callmethod_get = function (url, data, success, error) {
    $.ajax({
        type: "GET",
        url: url,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json",
        success: success,
        error: error
    });
};

var fn_callmethod_getHTML = function (url, data, success, error) {
    $.ajax({
        type: "GET",
        url: url,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
         dataType: "json",
        success: success,
        error: error
    });
};

var fn_callmethod_getND = function (url, success, error) {
    $.ajax({
        type: "GET",
        url: url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json",
        success: success,
        error: error
    });
};
var fn_callmethod_PostND = function (url, success, error) {
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json",
        success: success,
        error: error
    });
};

function combo(url, id) {
    $.ajax({
        type: "GET",
        url: url,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var option = "<option value='0'>Seleccione</option>";
            var tam = data.length;
            $("#" + id).append(option);
            for (var i = 0; i < tam; i++)
            {
                var option = $(document.createElement('option'));
                option.text(data[i].descripcion);
                option.val(data[i].codigo);
                $("#" + id).append(option);
            }
        }
    });
}

var fn_iniciarDT = function (oTable, idtable) {
    oTable = $("#" + idtable).dataTable({

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
    return oTable;
};


var fn_iniciarSinDT = function (oTable, idtable) {
    oTable = $("#" + idtable).dataTable({
        destroy: true, "bFilter": false, "lengthChange": false, "paging": false, "bInfo": false,
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
    return oTable;
};



function abrirVentana(url) {
    window.open(url, "nuevo", "directories=no, location=no, menubar=no, scrollbars=yes, statusbar=no, tittlebar=no, width=800, height=600");
}

/*divide el ubigeo de 8 digitos segun la necesidad*/
function DividirUbigeo(cadena, divisor) {
    var valor;
    return valor = (cadena.charAt(divisor - 2) + cadena.charAt(divisor - 1));
}
;

function combotableSetTimeout(cbo, id) {
    $('#' + cbo).select2();

    setTimeout(function () {
        $("#" + cbo).val(id).trigger('change');
    }, 500);
    $('#' + cbo).trigger('change.select2');
    $('#' + cbo).change();
    $('#' + cbo).select2({width: '300px'});
}
;

function combotable(cbo) {

    $('#' + cbo).select2();
    $('#' + cbo).trigger('change.select2');
    $('#' + cbo).change();
    $('#' + cbo).select2({width: '300px'});
}

function parseSelect2(cbo) {
    $('#' + cbo).select2();
    $('#' + cbo).trigger('change.select2');
    $('#' + cbo).change();
}

//Sin Promesa
var xhr = function (url, data, success, error) {
    var xhRequest = new XMLHttpRequest();
    xhRequest.open('POST', url, true);
    xhRequest.responseType = 'json';
    xhRequest.onload = function () {
        var status = xhRequest.status;
        if (status == 200) {
            success && success(xhRequest.response);
        } else {
            error && error(status);
        }
    };
    xhRequest.send(data);
};

