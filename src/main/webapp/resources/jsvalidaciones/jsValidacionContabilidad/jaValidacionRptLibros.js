$("#form-buscar-PerJur").validate({
    rules: {
        cboLibro: {valueNotEquals: "default"},

    },
    messages:
            {
                cboLibro: {valueNotEquals: "Seleccione un documento!"}

            }


});
$('#cboLibro').on('change', function () {
    $(this).valid();
});