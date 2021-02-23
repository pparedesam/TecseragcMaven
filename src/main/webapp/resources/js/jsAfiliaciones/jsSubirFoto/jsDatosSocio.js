-/*****************************Se usa en Miembro Familiar***********************/
/* Busca Socio Natural por cuenta */
$('#frmBuscarSocioNaturalFoto').submit(function (event) {
    event.preventDefault();
    if ($('#frmBuscarSocioNaturalFoto').valid()) {
        mostrarDatosSocioNaturalFoto();
    }
});


/* Busca Socio Natural por cuenta */