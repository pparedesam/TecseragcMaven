var mostrarImagenPersona = function (idPersona) {
    var data ={'idPersona':idPersona};
    var dataimage="data:image/JPEG;base64,";       
    var success = function (res) {
        if (res.Result=='OK') {
            $("#imgFotoSocio").attr('src',
                res.fotoSocio === undefined ? "../resources/imagenes/foto.jpg":dataimage.concat(res.fotoSocio));
            $("#imgFirmaSocio").attr('src',
                res.firmaSocio=== undefined?"../resources/imagenes/firma.jpg":dataimage.concat(res.firmaSocio));
        }
    };
    var error = function (data, status, er){
           toast('error', data + '-' + status + '-' + er, 'Error2!');
    };

    fn_callmethod( "../sSocio?action=obtenerImagenPersona",data, success, error);
};


