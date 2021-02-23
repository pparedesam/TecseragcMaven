$("#frmRetencion").validate({
    rules: {
        txtRUC: {required: true},
        txtRazonSocial: {required: true},
    },
    messages:
            {
                txtRUC: {required: "Debe identificar al proveedor para poder continuar"},
                txtRazonSocial: {required: "Debe ingresar el campo Razon Social, con su denominacion o Apellidos y Nombres, para poder continuar"},

            }
});

$("#frmFactReten").validate({
    rules: {
        txtSerieDoc: {required: true},
        txtNroDoc: {required: true},
        txtFecEmi2: {required: true},
        txtTotal: {required: true},
        txtCorrelativo: {required: true},
        txtImporteSR: {required: true},
    },
    messages:
            {
                txtSerieDoc: {required: "Se debe ingresar la serie del documento"},
                txtNroDoc: {required: "Se debe ingresar el número de documento"},
                txtFecEmi2: {required: "Este campo es obligatorio."},
                txtTotal: {required: "Se debe ingresar el Importe Total del Comprobante y que sea válido"},
                txtCorrelativo: {required: "Se debe ingresar el Numero correlativo de pago del comprobante y que sea válido"},
                txtImporteSR: {required: "Se debe ingresar el Importe de pago del comprobante y que sea válido"},
            }
});
