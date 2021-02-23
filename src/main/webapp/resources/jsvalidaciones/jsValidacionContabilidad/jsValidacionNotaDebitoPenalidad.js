$('#frmPenalidad').validate({
    rules: {
        txtDetalleMotivoxMora: {required: true, minlength: 5},
        txtInteresxMora: {required: true, number: true, min: 0.01}

    },
    messages: {
        txtDetalleMotivoxMora: {required: "Debe ingresar un detalle de penalidad", minlength: "El detalle debe contener almenos 5 caracteres"},
        txtInteresxMora: {required: "Debe ingresar el importe de penalidad", number: "Solo se admiten numeros", min: "Se debe ingresar montos mayor a cero"}
    }
});
