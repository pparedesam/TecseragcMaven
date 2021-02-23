function ObtenerFechaActualDMY(){
    
    var hoy = new Date();
    var dd = hoy.getDate();
    var mm = hoy.getMonth() + 1; //hoy es 0!
    var yyyy = hoy.getFullYear();

    if (dd < 10) {
        dd = '0' + dd
    }

    if (mm < 10) {
        mm = '0' + mm
    }

    return hoy =  dd+ '/' + mm + '/' + yyyy;
}

function abrirVentana(url) {

    window.open(url, "nuevo", "directories=no, location=no, menubar=no, scrollbars=yes, statusbar=no, tittlebar=no, width=400, height=400");
    window.open(url);
}

function  convertirDecimales(numero) {

    var numeroDecimal;
    numeroDecimal = parseFloat(numero).toFixed(2);
    numeroDecimal = (numeroDecimal.replace(/[^0-9\.-]+/g, ""));
    return numeroDecimal;
}