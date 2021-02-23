
function ObtenerFechaActual()
{
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

    return hoy =  yyyy+ '/' + mm + '/' + dd;
}

function parseDMY(value) {
        var date = value.split("/");

        var hoy = new Date();
        var dd = date[0];
        var mm = date[1] - 1; //hoy es 0!
        var yyyy = date[2];

        return hoy = yyyy + '/' +mm + '/' + dd  ;
    }    
function validaDNI(dni) {
    var ex_regular_dni;
    ex_regular_dni = /^\d{8}(?:[-\s]\d{4})?$/;
    if (ex_regular_dni.test(dni) == true) {
        
        return true;
    } else {
        
        return false;
    }
}
function validaPassport(passport) {
    var ex_regular_dni;
    ex_regular_dni = /^\d{12}(?:[-\s]\d{4})?$/;
    
    if (ex_regular_dni.test(passport) == true) {
        return true;
    } else {

        return false;
    }
}
function validaPartidaNac(partida) {
    var ex_regular_dni;
    ex_regular_dni = /^\d{15}(?:[-\s]\d{4})?$/;
    
    if (ex_regular_dni.test(partida) == true) {
        return true;
    } else {

        return false;
    }
}
function validaCarnetExtr(carnet) {
    var ex_regular_dni;
    ex_regular_dni = /^\d{15}(?:[-\s]\d{4})?$/;
    console.log(carnet);
    if (ex_regular_dni.test(carnet) == true) {
        return true;
    } else {

        return false;
    }
}

function validaCuenta(idSocio){
    if(idSocio.length!==7){
        String.format("%07d",idSocio);    
    }
    return idSocio;
}