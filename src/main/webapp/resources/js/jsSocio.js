//$(document).ready(function () {
//    $('#btnBuscarPersona').click(function (event) {
//        var dni = $('#txtbuscarPersona').val();
//        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
//        $.post('../sSocio', {
//            Dni: dni,
//            btnBuscarPersona: 1
//        }, function (responseText) {
//            $('#tabla').html(responseText);
//        });
//    });
//});
//$(document).ready(function () {
//    $('#btnRegSocio').click(function (event) {
//        var vAporteInicial = $('#txtAporteInicial').val();
//        var vDerechoInscripcion = $('#txtDerechoInscripcion').val();
//        var vFondoMortuorio = $('#txtFondoMortuorio').val();
//        var vTipDocumento = $('#cboTipDocumento').val();
//        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
//        $.post('../sSocio', {
//            AporteInicial: vAporteInicial,
//            DerechoInscripcion: vDerechoInscripcion,
//            FondoMortuorio: vFondoMortuorio,
//            TipDocumento: vTipDocumento,
//            btnRegSocio: 1
//        }, function (responseText) {
//            $('#tablaRegistrarSocio').html(responseText);
//        });
//    });
//});
//$(document).ready(function () {
//    $('#btnBuscarJuridico').click(function (event) {
//        var Ruc = $('#txtBuscarJuridico').val();
//        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
//        $.post('../sSocio', {
//            Ruc: Ruc,
//            btnBuscarJuridico: 1
//        }, function (responseText) {
//            $('#Persona').html(responseText);
//        });
//    });
//});
//$(document).ready(function () {
//    $('#btnRegistrarSocioJuridico').click(function (event) {
//        var vAporteInicial = $('#txtAporteInicial').val();
//        var vDerechoInscripcion = $('#txtDerechoInscripcion').val();
//        var vTipDocumento = $('#cboTipDocumento').val();
//        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
//        $.post('../sSocio', {
//            AporteInicial: vAporteInicial,
//            DerechoInscripcion: vDerechoInscripcion,
//            TipDocumento: vTipDocumento,
//            btnRegistrarSocioJuridico: 1
//        }, function (responseText) {
//            //$('#tablaRegistrarSocio').html(responseText);
//        });
//    });
//});
//function  ejecutarservletBuscarSocio(tipBusqueda) {
//    
//    var data = {'txtNroDocIden': $('#txtNroDocIden').val()};
//
//    var url;
//    if (tipBusqueda == 1) {
//
//        url = "../sSocio?action=BuscaSocio";
//    }
//    if (tipBusqueda == 2) {
//        url = "../sPersonaNatural?action=BuscaPersonaNoSocio";
//    }
//    if (tipBusqueda == 4) {
//        url = "../sPersonaJuridica?action=BuscaPersonaNoSocio";
//    }
//    if (tipBusqueda == 5) {
//        url = "../sPersonaNatural?action=BuscaPersonaGeneralNoEmpleado";
//    }
//    ejecutarServletSelect(data, url);
//}
//;
//
//function ejecutarservletRetirarSocio(data)
//{
//    var url = "../sSocio?action=retirar";
//
//    ejecutarServletUpdate(data, url)
//}
//;
