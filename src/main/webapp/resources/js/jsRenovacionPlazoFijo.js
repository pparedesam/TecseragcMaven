var oTable;
oTable = fn_iniciarDT(oTable, "tblPlazosFijos");

oTable.on("click", ".plazofijo", function () {
    var elem = this;
    fn_Info_Plazo(elem);
});

oTable.on("click", ".info", function () {
    var elem = this;
    fn_Info(elem);

});

$('#btnRenovacion').click(function (event) {
    bootbox.confirm("\u00BFEst\u00e1 seguro que desea registrar a esta Apertura?\u003F", function (result) {
        if (result) {
            renovarPlazoFijo();
        }
    });
});

$('#btnPrueba').click(function (event) {
    //$('#ModalMF').modal('hide');
    //document.getElementById("txttea").setAttribute('value','My default value');
    //para setear valor al llamar al modal
    document.getElementById("txttea").value = "Valor";

    $('#ModalPF').modal({backdrop: 'static', keyboard: false});

});

$('#ModalPF').on('hidden.bs.modal', function () {
    document.getElementById("txtPlazoFijoActual").value = "";
    document.getElementById("txtPlazoFijoNuevo").value = "";
    document.getElementById("txtofi").value = "";
    document.getElementById("txttipo").value = "";
    document.getElementById("txtmoneda").value = "";
    document.getElementById("txtnumcuenta").value = "";
    document.getElementById("txtofiNew").value = "";
    document.getElementById("txttipoNew").value = "";
    document.getElementById("txtmonedaNew").value = "";
    document.getElementById("txtnumcuentaNew").value = "";
    document.getElementById("txttipocuenta").value = "";
    document.getElementById("txtfechaapertura").value = "";
    document.getElementById("txtfechavence").value = "";
    document.getElementById("txttea").value = "";
    document.getElementById("txtsaldo").value = "";
    document.getElementById("txtgarantia").value = "";
    document.getElementById("txttasarenovacion").value = "";
    document.getElementById("txtInteres").value = "";
    document.getElementById("txtMonto").value = "";
    document.getElementById("txtTotal").value = "";
});



var fn_Info = function (elem) {


    var Oficina = $(elem).parent().attr('data-id-oficina') === undefined ? 0 : $(elem).parent().attr('data-id-oficina');
    var TipoCta = $(elem).parent().attr('data-id-tipocta') === undefined ? 0 : $(elem).parent().attr('data-id-tipocta');
    var TipMoneda = $(elem).parent().attr('data-id-tipmoneda') === undefined ? 0 : $(elem).parent().attr('data-id-tipmoneda');
    var NumCuenta = $(elem).parent().attr('data-id-numcuenta') === undefined ? 0 : $(elem).parent().attr('data-id-numcuenta');
    if (NumCuenta !== 0) {
        var success = function (res) {
            if (res.Result == 'OK') {
                fn_llenarTitulares(res.Info);
                var container = $('#titulares-container').clone();
                container.find('table').attr('id', 'lstTitulares');

                var box = bootbox.dialog({
                    show: false,
                    message: container.html(),
                    title: "DataTables in a Bootbox",
                });

                box.on("shown.bs.modal", function () {
                    $('#lstTitulares').DataTable();
                });

                box.modal('show');

            }
        };
        var error = function () {
            toast('error', 'Error', 'Error!');
        };
        fn_callmethod("../sRenovacionPlazoFijo?action=info", {Oficina: Oficina, TipoCta: TipoCta, TipMoneda: TipMoneda, NumCuenta: NumCuenta}, success, error);
    }
    ;
}

var fn_Info_Plazo = function (elem) {

    var Oficina = $(elem).parent().attr('data-id-oficina') === undefined ? 0 : $(elem).parent().attr('data-id-oficina');
    var TipoCta = $(elem).parent().attr('data-id-tipocta') === undefined ? 0 : $(elem).parent().attr('data-id-tipocta');
    var TipMoneda = $(elem).parent().attr('data-id-tipmoneda') === undefined ? 0 : $(elem).parent().attr('data-id-tipmoneda');
    var NumCuenta = $(elem).parent().attr('data-id-numcuenta') === undefined ? 0 : $(elem).parent().attr('data-id-numcuenta');
    if (NumCuenta != 0) {

        var data = {Oficina: Oficina, TipoCta: TipoCta, TipMoneda: TipMoneda, NumCuenta: NumCuenta};
        var success = function (res) {
            if (res.Result == 'OK') {
                $('#ModalPF').modal({backdrop: 'static', keyboard: false});

                document.getElementById("txtPlazoFijoActual").value =
                        (res.InfoPlazoFijo.idOficinaCTA
                                + '-' + res.InfoPlazoFijo.idTipCtax
                                + '-' + res.InfoPlazoFijo.tipmonedaCTA
                                + '-' + res.InfoPlazoFijo.numcuentaCTA);
                        
                document.getElementById("txtofi").value = res.InfoPlazoFijo.idOficinaCTA;
                document.getElementById("txttipo").value = res.InfoPlazoFijo.idTipCtax;
                document.getElementById("txtmoneda").value = res.InfoPlazoFijo.tipmonedaCTA;
                document.getElementById("txtnumcuenta").value = res.InfoPlazoFijo.numcuentaCTA;

                document.getElementById("txttipocuenta").value = res.InfoPlazoFijo.tipoCta;
                document.getElementById("txtsaldo").value =convertirDecimales(res.InfoPlazoFijo.saldo);
                document.getElementById("txtcontrato").value = convertirDecimales(res.InfoPlazoFijo.tasaContrato);
                document.getElementById("txttea").value = convertirDecimales(res.InfoPlazoFijo.tea);

                document.getElementById("txtInteres").value =convertirDecimales(res.InfoPlazoFijo.fnInteres);
                document.getElementById("txtMonto").value =convertirDecimales(res.InfoPlazoFijo.saldo);
                document.getElementById("txtTotal").value =convertirDecimales(res.InfoPlazoFijo.montoCancelar);
                document.getElementById("txttasarenovacion").value =convertirDecimales(res.InfoPlazoFijo.tasaActiva);

                document.getElementById("txtfechaapertura").value = (res.InfoPlazoFijo.fechaApertura);
                document.getElementById("txtfechavence").value = (res.InfoPlazoFijo.fechaFinPlazo);                
                document.getElementById('txtInteres').setAttribute("data-intProv",convertirDecimales(res.InfoPlazoFijo.interesProvi));


            } else {
                toast('error', res.Mensaje, 'Error!');
            }
        };
        var error = function () {
            toast('error', 'Error', 'Error!');
        };
        fn_callmethod("../sRenovacionPlazoFijo?action=infoplazofijo", data, success, error);
    }
    ;
}

function renovarPlazoFijo()
{
    var vIdOficinaCTA = $('#txtofi').val();
    var vIdTipCTA = $('#txttipo').val();
    var vIdTipMoneda = $('#txtmoneda').val();
    var vNumcuenta = $('#txtnumcuenta').val();
    var vFechaApertura = $('#txtfechaapertura').val();
    var vMonto = $('#txtMonto').val();
    var vMontoTotal = $('#txtTotal').val();
    var vInteres = $('#txtInteres').val();
    var vtasa = $('#txtcontrato').val();
    var vTea = $('#txttea').val();

    var data = {'IdOficinaCTA': vIdOficinaCTA, 'IdTipCTA': vIdTipCTA, 'IdTipMoneda': vIdTipMoneda, 'Numcuenta': vNumcuenta, 'FechaApertura': vFechaApertura,
        'Monto': vMonto, 'MontoTotal': vMontoTotal, 'Interes': vInteres, 'tasa': vtasa, 'Tea': vTea,
        'interesProvi': document.getElementById('txtInteres').getAttribute("data-intProv")};

    var success = function (res) {
        if (res.Result == 'OK') {
            document.getElementById("txtofiNew").value = $('#txtofi').val();
            document.getElementById("txttipoNew").value = $('#txttipo').val();
            document.getElementById("txtmonedaNew").value = $('#txtmoneda').val();
            document.getElementById("txtnumcuentaNew").value = res.Mensaje;
            document.getElementById('btnRenovacion').style.visibility = 'hidden';
            document.getElementById("txtPlazoFijoNuevo").value =
                    ($('#txtofi').val()
                            + '-' + $('#txttipo').val()
                            + '-' + $('#txtmoneda').val()
                            + '-' + $('#txtnumcuentaNew').val());


            toast('success', 'Renovacion Exitosa', 'Exito!');

            var delay = 2000;
            setTimeout(function () {
                window.open(ReportSvr + '?%2fPyAfiliaciones%2fRptCertificadoPlazoFijo&rs:Command=Render&IdOficina='
                        + document.getElementById("txtofiNew").value
                        + '&TipMoneda=' + document.getElementById("txtmonedaNew").value
                        + '&IdTipCta=' + document.getElementById("txttipoNew").value
                        + '&NumCuenta=' + res.Mensaje
                        + '&TipoImpresion=2&IdOficinaAnt='
                        + document.getElementById("txtofi").value
                        + '&TipMonedaAnt=' + document.getElementById("txtmoneda").value
                        + '&IdTipCtaAnt=' + document.getElementById("txttipo").value
                        + '&NumCuentaAnt=' + document.getElementById("txtnumcuenta").value);

                location.reload(true);
            }, delay);


        } else {
            toast('error', res.Mensaje, 'Error!');
        }
    };
    var error = function () {
        toast('error', 'Error', 'Error!');
    };
    fn_callmethod("../sRenovacionPlazoFijo?action=renovacion", data, success, error);
}

var fn_llenarTitulares = function (dataSet) {

    $("#lstTitulares").dataTable().fnDestroy();
    $('#lstTitulares').DataTable({
        "aaData": dataSet,
        "columns": [
            {"data": "socio"},
            {"data": "objPersona.objDocumento.codigo"},
            {"data": "objPersona.nroDocumento"}
        ]
    });

};