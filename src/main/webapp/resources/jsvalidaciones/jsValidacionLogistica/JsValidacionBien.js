//jQuery.validator.setDefaults({
//  debug: true,
//  success: "valid"
//});


$("#form-buscar").validate({
        rules: {
            txtCriterioBusqueda: {
                required: true
            }
        },
        messages: {
            txtCriterioBusqueda: {
                required: "Debe Ingresar un Criterio de Busqueda"
            }
        }
    });