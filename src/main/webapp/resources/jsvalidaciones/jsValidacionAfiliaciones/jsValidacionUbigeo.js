$("#form-registrar").validate({
        rules: {
            txtDescripcion: {
                required: true
            }
        },
        messages: {
            txtDescripcion: {
                required: "Debe Ingresar un Nombre de ubigeo"
            }
        }
    });


