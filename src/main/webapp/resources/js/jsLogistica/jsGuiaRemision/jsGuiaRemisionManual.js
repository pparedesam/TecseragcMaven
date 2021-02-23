var listPais;
var listDepartamentos;
var listProvincias;
var listDistritos;
var listUrbanizacion;
var listModalidadTraslado;
var listMotivoTraslado;
var listUnidadMedida;
var idPersonaDestinatario = '';
var idPersonaConductor = '';
var idPersonaTransportista = '';
var idPersonaEmisor = '';
var listaDetalle =[];

$(function () {
    obtenerUbigeo();
    obtenerModalidadTraslado();
    obtenerMotivoTraslado();
    obtenerUnidadMedida();
    cargarDatosComunidad();
});

$(document).ready(function () {
    fecha = new Date();
    $('#txtBusFecInicio').datepicker('update', fecha);
    $('#txtBusFecFin').datepicker('update', fecha);

    $('#txtTrasladoFecha').datepicker('update', fecha);
    $('#txtTrasladoFechaTraslado').datepicker('update', fecha);
    $('#txtTrasladoFechaEntregaTransportista').datepicker('update', fecha);
    
});

function obtenerUnidadMedida() {
    var success = function (data) {

        listUnidadMedida = '';
        listUnidadMedida = data.listarUnidadMedida;
        cargarUnidadMedida();
        cargarUnidadMedidaModal();

    };
    var error = function () {};
    fn_callmethod("../sUnidadMedida?action=obtenerUnidadMedida", '', success, error);
}
;

function obtenerModalidadTraslado() {
    var success = function (data) {

        listModalidadTraslado = '';
        listModalidadTraslado = data.listarModalidadTraslado;
        cargarModalidadTraslado();

    };
    var error = function () {};
    fn_callmethod("../sModalidadTraslado?action=obtenerModalidadTraslado", '', success, error);
}
;
function obtenerMotivoTraslado() {
    var success = function (data) {

        listMotivoTraslado = '';
        listMotivoTraslado = data.listarMotivoTraslado;
        cargarMotivoTraslado();

    };
    var error = function () {};
    fn_callmethod("../sMotivoTraslado?action=obtenerMotivoTraslado", '', success, error);
}
;
function obtenerUbigeo() {
    var success = function (data) {

        listPais = '';
        listDepartamentos = '';
        listProvincias = '';
        listDistritos = '';
        listPais = data.listaUbigeo[0];
        listDepartamentos = data.listaUbigeo[1];
        listProvincias = data.listaUbigeo[2];
        listDistritos = data.listaUbigeo[3];

        cargarUbigeoPartida();
        cargarUbigeoLlegada();


    };
    var error = function () {};
    fn_callmethod("../sUbigeo?action=obtenerUbigeo", '', success, error);
}
;
$("#cboPartidaDepartamento").change(function () {
    var x;
    x = $("#cboPartidaDepartamento").val();
    obtenerProvincia('#cboPartidaProvincia', '0', x);
    x = $("#cboPartidaProvincia").val();
    obtenerDistrito('#cboPartidaDistrito', '0', x);

});

$("#cboPartidaProvincia").change(function () {
    var x;
    x = $("#cboPartidaProvincia").val();
    obtenerDistrito('#cboPartidaDistrito', '0', x);

});

$("#cboPartidaDepartamento").change(function () {
    var x;
    x = $("#cboPartidaDepartamento").val();
    obtenerProvincia('#cboLlegadaProvincia', '0', x);
    x = $("#cboLlegadaProvincia").val();
    obtenerDistrito('#cboLlegadaDistrito', '0', x);

});

$("#cboLlegadaProvincia").change(function () {
    var x;
    x = $("#cboLlegadaProvincia").val();
    obtenerDistrito('#cboLlegadaDistrito', '0', x);

});


$("#btnBuscarGuia").click(function () {
    var criterio = $("#cboBusqueda").val();
    if (criterio == 1) {
        var nroGuia = $("#txtNroGuia").val();
        buscarGuiaRemision(criterio, nroGuia, '', '');
    } else {
        var fecIni = $("#txtBusFecInicio").val();
        var fecFin = $("#txtBusFecFin").val();
        buscarGuiaRemision(criterio, '', fecIni, fecFin);
    }
})

$("#cboBusqueda").change(function () {
    var tipoBusqueda = $("#cboBusqueda").val();
    if (tipoBusqueda == '1') {
        $("#busquedaNroGuia").css("display", "block");
        $("#busquedaFechas").css("display", "none");
    } else {
        $("#busquedaNroGuia").css("display", "none");
        $("#busquedaFechas").css("display", "block");
    }
})

$('#tabGuia a').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
});




$("#txtBusFecInicio").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$("#txtBusFecFin").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$("#txtTrasladoFecha").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$("#txtTrasladoFechaTraslado").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});

$("#txtTrasladoFechaEntregaTransportista").datepicker({
    autoclose: true,
    format: "dd/mm/yyyy",
    viewMode: "days",
    minViewMode: "days",
    language: "es-ES"
});



$("#btnModalAgregarDetalle").click(function () {
    var objDetalle = new Object();

    objDetalle.id = listaDetalle.length + 1;
    objDetalle.unidadMedida = $('#cboModalDetalleUnidadMedida option:selected').text();
    objDetalle.cantidad = $("#txtModalDetalleCantidad").val();
    objDetalle.descripcion = $("#txtModalDetalleDescripcion").val();
    listaDetalle.push(objDetalle);

    listarTablaDetalle(listaDetalle);

    $("#modalDetalle").modal('hide');

    $("#txtModalDetalleCantidad").val("");
    $("#txtModalDetalleDescripcion").val("");

})


function cargarModalidadTraslado() {
    $('#cboModalidad').empty();
    $.each(listModalidadTraslado, function (i, item) {
        $('#cboModalidad').append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
        // $(combobox).trigger("refresh");
    });

    $('#cboModalidad').index(1);
    validacion();
}
;

function cargarUnidadMedida() {
    $('#cboTrasladoUnidadMedida').empty();
    $.each(listUnidadMedida, function (i, item) {
        $('#cboTrasladoUnidadMedida').append("<option value='" + item.codigoMedida + "'>" + item.descripcion + "</option>");
        // $(combobox).trigger("refresh");
    });

    $('#cboTrasladoUnidadMedida').index(1);
}
function cargarUnidadMedidaModal() {
    $('#cboModalDetalleUnidadMedida').empty();
    $.each(listUnidadMedida, function (i, item) {
        $('#cboModalDetalleUnidadMedida').append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
        // $(combobox).trigger("refresh");
    });

    $('#cboModalDetalleUnidadMedida').index(1);
}

function cargarMotivoTraslado() {
    $('#cboTrasladoMotivo').empty();
    $.each(listMotivoTraslado, function (i, item) {
        $('#cboTrasladoMotivo').append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
        // $(combobox).trigger("refresh");
    });

    $('#cboTrasladoMotivo').index(1);
}

function obtenerPaises(combobox, select) {
    $(combobox).empty();
    $.each(listPais, function (i, item) {
        $(combobox).append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
        // $(combobox).trigger("refresh");
    });
    if (select == '0') {
        $(combobox).index(1);
    } else {
        $(combobox).val(select).prop('selected', true);
    }
}
;

function obtenerDepartamentos(combobox, select) {
    $(combobox).empty();
    $.each(listDepartamentos, function (i, item) {
        $(combobox).append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
    });
    if (select == '0') {
        if(combobox=="#cboPartidaDepartamento"){
            document.getElementById("cboPartidaDepartamento").selectedIndex = 11;
        }else{
            document.getElementById("cboLlegadaDepartamento").selectedIndex = 11;
        }
        //$(combobox).index(10);
    } else {
        $(combobox).val(select).prop('selected', true);
    }

}
;
function obtenerProvincia(combobox, select, region) {
    $(combobox).empty();
    $.each(listProvincias, function (i, item) {
        if (item.region === region) {
            $(combobox).append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
        }
    });
    if (select == '0') {
        $(combobox).index(1);
    } else {
        $(combobox).val(select).prop('selected', true);
    }
}
;
function obtenerDistrito(combobox, select, provincia) {
    $(combobox).empty();
    $.each(listDistritos, function (i, item) {
        if (item.provincia === provincia) {
            $(combobox).append("<option value='" + item.codigo + "'>" + item.descripcion + "</option>");
        }
    });
    if (select == '0') {
        $(combobox).index(1);
    } else {
        $(combobox).val(select).prop('selected', true);
    }
}
;

function cargarUbigeoPartida() {
    obtenerPaises('#cboPartidaPais', '0');
    obtenerDepartamentos('#cboPartidaDepartamento', '0');
    obtenerProvincia('#cboPartidaProvincia', '0', $("#cboPartidaDepartamento").val());
    obtenerDistrito('#cboPartidaDistrito', '0', $("#cboPartidaProvincia").val());
}
function cargarUbigeoLlegada() {
    obtenerPaises('#cboLlegadaPais', '0');
    obtenerDepartamentos('#cboLlegadaDepartamento', '0');
    obtenerProvincia('#cboLlegadaProvincia', '0', $("#cboLlegadaDepartamento").val());
    obtenerDistrito('#cboLlegadaDistrito', '0', $("#cboLlegadaProvincia").val());
}

function buscarGuiaRemision(criterio, nroGuia, fecIni, fecFin) {
    //  var tipoMoneda = $('#cboTipMoneda').val();

    var data = {
        'criterio': criterio,
        'nroGuia': nroGuia,
        'fecIni': fecIni,
        'fecFin': fecFin
    };



    var success = function (res) {

        if (res.Result == 'OK') {
            var listaDetDocCompraVenta = res.listaDetDocCompraVenta;
            listarTablaGuiaRemision(listaDetDocCompraVenta);
        } else {
            toast('error', res.Message, 'Error');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sGuiaRemisionManual?action=buscarGuiaRemision", data, success, error);
}

function descargarXML(nroGuia) {
    //  var tipoMoneda = $('#cboTipMoneda').val();

    var data = {
        'idFact': nroGuia,

    };



    var success = function (res) {

        if (res.Result == 'OK') {
            
        } else {
            toast('error', res.Message, 'Error');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sDownload?action=descargarXML", data, success, error);
}

function descargarCDR(nroGuia) {
    //  var tipoMoneda = $('#cboTipMoneda').val();
    var data = {
        'idFactCDR': nroGuia,
    };

    var success = function (res) {

        if (res.Result == 'OK') {
            
        } else {
            toast('error', res.Message, 'Error');
        }
    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    fn_callmethod("../sDownloadCDR?action=descargarCDR", data, success, error);
}


function validacion() {
    var tipoModalidad = $("#cboModalidad").val();
    if (tipoModalidad == '01') {
        $("#transporte2").hide();
        $("#transporte1").show();


    } else if (tipoModalidad === '02') {
        $("#transporte2").show();
        $("#transporte1").hide();

    }

}
;

function cargarDatosComunidad() {
    idPersonaEmisor = '0000001';
    $("#txtEmisorRazonSocial").val('COMUNIDAD CAMPESINA LLACUABAMBA');
    $("#txtEmisorRUC").val('20315295573');
}

function pad(input, length, padding) {
    var str = input + "";
    return (length <= str.length) ? str : pad(str + padding, length, padding);
}

// <editor-fold defaultstate="collapsed" desc="BUSCAR EMISOR">
$("#btnEmisorBuscar").click(function () {

    var data = {'txtPersonaJuridica': $("#txtModalEmisorCriterio").val(), 'TipoBusqueda': $("#cboModalEmisorBusqueda").val()};

    var success = function (res) {

        if (res.Result === 'OK') {
            $('#tblModalEmisor').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaJ,
                //rowId: 'idbien', //asigna el id a la fila
                "columns": [
                    {"data": "nroDocumento"}, //, "visible": false},
                    {"data": "nombres"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(2)").attr('data-id', data.idPersona);
                }
            });
        } else if (res.Result === 'error')
        {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sPersonaJuridica?action=buscarPersonaJuridicaModal", data, success, error);

});


var otablePJE = $('#tblModalEmisor').DataTable();
otablePJE.on("click", ".select", function () {
    fn_SelectE(this);
});

var fn_SelectE = function (elem) {

    var par = $(elem).parent().parent();
    var td_nombre = par.children("td:nth-child(2)");
    var td_dni = par.children("td:nth-child(1)");
    var tdbuttons = par.children("td:nth-child(3)");
    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    var dato = {'idPersona': id_persona, 'nombre': td_nombre.html(), 'dni': td_dni.html()};
    insertarEmisor(dato);

    $('#modalEmisor').modal('hide');

};

function insertarEmisor(data) {
    idPersonaEmisor = '';
    idPersonaEmisor = data.idPersona;
    $("#txtEmisorRUC").val(data.dni);
    $("#txtEmisorRazonSocial").val(data.nombre);
}

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="BUSCAR DESTINATARIO">
$("#btnModalDestinatarioBuscar").click(function () {

    var data = {'txtPersonaJuridica': $("#txtModalDestinatarioCriterio").val(), 'TipoBusqueda': $("#cboModalDestinatarioBusqueda").val()};

    var success = function (res) {

        if (res.Result == 'OK') {
            $('#tblModalDestinatario').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaJ,
                //rowId: 'idbien', //asigna el id a la fila
                "columns": [
                    {"data": "nroDocumento"}, //, "visible": false},
                    {"data": "nombres"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(2)").attr('data-id', data.idPersona);
                }
            });
        } else if (res.Result === 'error')
        {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sPersonaJuridica?action=buscarPersonaJuridicaModal", data, success, error);

});


var otablePJ = $('#tblModalDestinatario').DataTable();
otablePJ.on("click", ".select", function () {
    fn_Select(this);
});

var fn_Select = function (elem) {

    var par = $(elem).parent().parent();
    var td_nombre = par.children("td:nth-child(2)");
    var td_dni = par.children("td:nth-child(1)");
    var tdbuttons = par.children("td:nth-child(3)");
    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    var dato = {'idPersona': id_persona, 'nombre': td_nombre.html(), 'dni': td_dni.html()};
    insertarDestinatario(dato);

    $('#modalDestinatario').modal('hide');

};

function insertarDestinatario(data) {
    idPersonaDestinatario = '';
    idPersonaDestinatario = data.idPersona;
    $("#txtDestinatarioRUC").val(data.dni);
    $("#txtDestinatarioNombres").val(data.nombre);
}

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="BUSCAR CONDUCTOR">
$("#btnBuscarConductor").click(function () {


    var data = {'txtPersonaNatural': $("#txtModalConductorCriterio").val(), 'TipoBusqueda': $("#cboModalConductorBusqueda").val()};

    var success = function (res) {

        if (res.Result == 'OK') {
            $('#tblModalConductor').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.lista,
                //rowId: 'idbien', //asigna el id a la fila
                "columns": [
                    {"data": "nroDocumento"}, //, "visible": false},
                    {"data": "nombres"},
                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(2)").attr('data-id', data.idPersona);
                }
            });
        } else if (res.Result === 'error')
        {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sPersonaNatural?action=buscarPersonaNaturalModal", data, success, error);

});

var otablePn = $('#tblModalConductor').DataTable();
otablePn.on("click", ".select", function () {
    fn_SelectPN(this);
});

var fn_SelectPN = function (elem) {

    var par = $(elem).parent().parent();
    var td_nombre = par.children("td:nth-child(2)");
    var td_dni = par.children("td:nth-child(1)");
    var tdbuttons = par.children("td:nth-child(3)");
    var id_persona = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');

    var data = {'idPersona': id_persona, 'nombre': td_nombre.html(), 'dni': td_dni.html()};
    insertarConductor(data);

    $('#modalConductor').modal('hide');

};

function insertarConductor(data) {
    idPersonaConductor = '';
    idPersonaConductor = data.idPersona
    $("#txtConductorNroDoc").val(data.dni);
    $("#txtConductorNombres").val(data.nombre);
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="BUSCAR TRANSPORTISTA">
$("#btnBuscarTransportista").click(function () {

    var data = {'txtPersona': $("#txtModalTransportistaCriterio").val(), 'TipoBusqueda': $("#cboModalTransportistaBusqueda").val()};

    var success = function (res) {

        if (res.Result == 'OK') {

            $('#tblModalTransportista').DataTable({
                destroy: true,
                "columnDefs": [
                    {"className": "text-center", "targets": "_all"}

                ],
                "aaData": res.listaPersona,
                //rowId: 'idbien', //asigna el id a la fila
                "columns": [
                    {"data": "nroDocumento"},
                    {"data": "nombres"},

                    {
                        "data": null,
                        "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            $(nTd).html("<a href='javascript:;'  class='btn btn-HippieGreen btn-circle select' title='Agregar'><i class='fa fa-plus'></i></a></td>");
                        }}
                ],
                createdRow: function (row, data, indice) {
                    $(row).find("td:eq(2)").attr('data-id', data.idPersona);

                }
            });
        } else if (res.Result === 'error')
        {
            toast('error', res.Message, 'Error!');
        }

    };

    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };

    fn_callmethod("../sPersona?action=buscarPersonaJN", data, success, error);

});


var otabletbl_PersonaJN = $('#tblModalTransportista').DataTable();
otabletbl_PersonaJN.on("click", ".select", function () {
    fn_Selection(this);
});

var fn_Selection = function (elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(3)");
    var td_nrodoc = par.children("td:nth-child(1)");
    var td_tit = par.children("td:nth-child(2)");
    var id_per = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');


    var data = {'idPersona': id_per, 'titular': td_tit.html(), 'Nrodoc': td_nrodoc.html()};
    insertarTransportista(data);
    $('#modalTransportista').modal('hide');

};

function insertarTransportista(data) {
    idPersonaTransportista = '';
    idPersonaTransportista = data.idPersona;
    $("#txtTransportistaNombres").val(data.titular);
    $("#txtTransportistaNroDoc").val(data.Nrodoc);

}
// </editor-fold>

$('#cboModalidad').on('change', function (e) {
    validacion();
});

$('#btnRegistrarGuia').click(function (e) {
    
   // var validator = $("#form-registrarGuiaRemision").valid();
   // if (validator) {

        registrarGR();
   // } else
   //     toast('warning', "USTED DEBE INGRESAR CORRECTAMENTE LOS DATOS REQUERIDOS", 'warning');

});

function registrarGR() {

    var data = {

        'idPersonaEmisor': idPersonaEmisor,
        //'idUbigeoEmisor': $("#cboPartidaDistrito").val(),
        'partida': $("#txtPuntoPartida").val(),
        'idPersonaDestino': idPersonaDestinatario,
        
        'idPaisPartida': $("#cboPartidaPais").val(),
        'idDepartamentoPartida': $("#cboPartidaDepartamento").val(),
        'idProvinciasPartida': $("#cboPartidaProvincia").val(),
        'idDistritosPartida': $("#cboPartidaDistrito").val(),
        
        'idPaisLlegada': $("#cboLlegadaPais").val(),
        'idDepartamentoLlegada': $("#cboLlegadaDepartamento").val(),
        'idProvinciasLlegada': $("#cboLlegadaProvincia").val(),
        'idDistritosLlegada': $("#cboLlegadaDistrito").val(),
        
        //'idUbigeoDestino': $("#cboLlegadaDistrito").val(),
        'llegada': $("#txtPuntoLlegada").val(),
        'placa': $("#txtMarcaNroPlaca").val(),
        'observacion': $("#txtObservacion").val(),
        'idPersonaConductor': idPersonaConductor,
        'idPersonaEmpresaTransporte': idPersonaTransportista,
        'idModalidadTraslado': $("#cboModalidad").val(),
        'fecha': $("#txtTrasladoFecha").val(),
        'fechaTraslado': $("#txtTrasladoFechaTraslado").val(),
        'idMotivoTraslado': $("#cboTrasladoMotivo").val(),
        'fechaEntrega': $("#txtTrasladoFechaEntregaTransportista").val(),
        'Umedida': $("#cboTrasladoUnidadMedida").val(),
        'Pbruto': $("#txtTrasladoPesoBruto").val(),
        'listaDetalle': JSON.stringify(listaDetalle)};

    var success = function (res) {

        if (res.Result === 'OK') {
            if (res.result.Message === 'OK') {
                toast('success', "REGISTRO EXITOSO", 'Exito!');
                setTimeout("location.reload(true);", 5000);
                limpiarFactura();
                abrirVentana(svrbd + '?%2fContabilidad%2frptGuiaRemision&rs:Command=Render&' +
                        'nrodoc=' + res.result.nroDoc +
                        '&iddoc=' + '0012' +
                        '&idoficina=' + '01' +
                        '&tipmoneda=' + '0');

            }
        } else if (res.Result === 'error') {
            toast('error', res.Message, 'Error!');
        }

    };
    var error = function (res) {
        toast('error', "Error Inesperado, Refresque la Pagina", 'Error');
    };
    bootbox.confirm("\u00BFEst\u00E1 seguro que desea Registrar Guia de Remision\u003F", function (result) {
        if (result) {
            fn_callmethod("../sGuiaRemisionManual?action=registrarGuiaRemision", data, success, error);
        }
    });
}
;




