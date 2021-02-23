$('#frmBuscarEmpleado').submit(function (event) {
    event.preventDefault();
    if ($('#frmBuscarEmpleado').valid()) {
     //   LimpiarData();
        buscarEmpleado();
        $("#divTablaEmpleados").css("display", "block");
        $("#datosEmpleado").css("display", "none");
        ocultarControles();
    }
});

var buscarEmpleado = function () {

    var txtEmpleado = $("#txtBuscarEmpleado").val();
    var cboTipoBusqueda = $("#cboTipoBusqueda").val();

    var data = {'txtBuscarEmpleado': txtEmpleado, 'cboTipoBusqueda': cboTipoBusqueda};

    var success = function (res) {
        if (res.Result == 'OK') {
            //limpiar();
            listarEmpleados(res.listEmpleados);

        } else if (res.Result == 'error') {

            toast('error', res.Message, 'Error!');
        }
    };
    var error = function (data, status, er) {
        // toast('error', data + '-' + status + '-' + er, 'Error2!');
    };
    fn_callmethod("../sEmpleado?action=listEmpleados", data, success, error);
};