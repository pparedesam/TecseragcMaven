
$(document).ready(function () {
    $('#btnBuscarSocExp').click(function (event) {
        var idSocio = $('#txtIdSocio').val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('../sSocio', {
            idSocio: idSocio,
            btnBuscarSocExp: 1
        }, function (responseText) {
            $('#tabla').html(responseText);
        });
    });
});

$(document).ready(function () {
    $('#btnRegistrarExpediente').click(function (event) {
        event.preventDefault();
        $.post('../sDatosCredito', {
            Analista: $('#cboAnalista').val(),
            Verificacion: $('#txtVerificacion').val(),
            Sorteo: $('#txtSorteo').val(),
            DctoAdministrativo: $('#txtDctoAdministrativo').val(),
            MultaElectoral: $('#txtMultaElectoral').val(),
            SeguroCredito: $('#txtSeguroCredito').val(),
            DescCarpeta: $('#txtDescCarpeta').val(),
            MontoCredito: $('#txtMontoCredito').val(),
            TipMoneda: $('#cboTipMoneda').val(),
            CatSocio: $('#txtCatSocio').val(),
            PrimerVencimiento: $('#txtPrimerVencimiento').val(),
            DiaFijoPago: $('#chkDiaFijoPago').val(),
            Mercado: $('#cboMercado').val(),
            FechaDesembolso: $('#txtFechaDesembolso').val(),
            Tasa: $('#txtTasa').val(),
            PlazoCredito: $('#cboPlazoCredito').val(),
            FrecPago: $('#cboFrecPago').val(),
            TipCuota: $('#cboTipCuota').val(),
            CalificacionSBS: $('#cboCalificacionSBS').val(),
            EvaluacionSBS: $('#cboEvaluacionSBS').val(),
            DestinoSBS: $('#cboDestinoSBS').val(),
            TipoSBS: $('#cboTipoSBS').val(),
            TipProducto: $('#cboTipProducto').val(),
            Modalidad: $('#cboModalidad').val(),
            btnRegistrarExpediente: 1
        }, function (responseText) {
            //console.log( $('#cboModalidad').val());
        });
    });
});


