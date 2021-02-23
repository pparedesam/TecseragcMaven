var fn_registrarBitacora =function (nombreFormulario, idPersona, motivo) {
    var promise = new Promise(function (resolve, reject) {
        var data = {'nombre': nombreFormulario, 'idPersona': idPersona, 'motivo': motivo};
        var success = function (res) {
            if (res.Result == 'OK') {
                if (res.Respuesta == true) {
                    resolve(true);
                }
            } else {
                reject(res.Message);
            }
        };
        var error = function (data, status, er) {
            reject("error");
        };
        fn_callmethod("../sBitacora?action=registrarBitacora", data, success, error);
    });
    return promise;
}
;