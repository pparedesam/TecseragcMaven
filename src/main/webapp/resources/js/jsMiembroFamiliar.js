$(document).ready(function () {
    $('#btnBuscarInfo').click(function (event) {
        var dni = $('#txtBuscarInfo').val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('../sMiembroFamiliar', {
            dni: dni,
            btnBuscarInfo: 1
        }, function (responseText) {
            $('#Socio').html(responseText);
        });
    });
});

$(document).ready(function () {
    $('#btnBuscaMiembro').click(function (event) {
        var dni = $('#txtBuscarMiembro').val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('../sSocio', {
            Dni: dni,
            btnBuscarPersona: 1
        }, function (responseText) {
            $('#Miembro').html(responseText);
            if ($('#txtDni').val() != "null")
            {
            document.getElementById('btnRegistrarMiembro').style.visibility = 'visible';
            document.getElementById('cboParentesco').style.visibility = 'visible';
            document.getElementById('parentesco').style.visibility = 'visible';
            }
            else
            {
            document.getElementById('btnRegistrarMiembro').style.visibility = 'hidden';
            document.getElementById('cboParentesco').style.visibility = 'hidden';
            document.getElementById('parentesco').style.visibility = 'hidden';
            }
        });
    });
});

$(document).ready(function () {
    $('#btnRegistrarMiembro').click(function (event) {
        var vIdSocio = $('#txtIdSocio').val();
        var vidMiembro = $('#txtIdPersona').val();
        var vParentesco = $('#cboParentesco').val();

        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('../sMiembroFamiliar', {
            IdSocio: vIdSocio,
            idMiembro: vidMiembro,
            Parentesco: vParentesco,
            btnRegistrarMiembro: 1
        }, function (responseText) {
            //$('#tablaRegistrarSocio').html(responseText);
        });
    });
});