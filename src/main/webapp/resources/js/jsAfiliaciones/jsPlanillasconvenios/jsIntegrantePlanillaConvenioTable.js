var oTableI;
    oTableI = fn_iniciarDT(oTableI, "tbl_IntegrantePlanilla");
    oTableI.on("click", ".save", function () {
        fn_InSave(this);
    });
    
    oTableI.on("click", ".cancel", function () {
        fn_InCancel(this);
    });
    oTableI.on("click", ".remove", function () {
        var elem = this;
    bootbox.confirm({
        title: "ELIMINAR INTEGRANTE",
        message: "ESTA SEGURO QUE DESEA ELIMINAR EL INTEGRANTE",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Cancelar'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Confirmar'
            }
        },
       callback: function(result) {
            if (result) {
                oTableI.fnDestroy();
                fn_InDelete(elem);
            }
        }
    });
       
    });
    oTableI.on("click", ".edit", function () {
        fn_InEdit(this);
    });

$("#btnAgregarPersonaPlanilla").click(function (event) {
       
        var Datos =JSON.parse($.session.get('Datos'));      
        //$.session.remove('Datos');
      
        if (Datos.codigoApoderado.length === 0 && Datos.codigoConvenio.length === 0){
            bootbox.alert("No se ha seleccionado un Apoderado");
        } else{
            
            $('#btnAgregarPersonaPlanilla').prop("disabled", true);
            TipoBusqueda='IN';
            $('.alertaI').show().html('<strong> Para Habilitar el boton vuelva a seleccionar un apoderado </strong>');
            $("#tbl_IntegrantePlanilla tbody").prepend(
                    "<tr>" +
                    "<td style='width: 3%;'>"+
                    "</td>"+                   
                    "<td style='width:20%'>"+
                        "<div class='form-group' style='width:100%'>"+
                            "<div class='input-group' style='width:100%'>"+
                                "<input type='text' class='form-control' id='NombrePersonaIntegrante' name='NombrePersonaIntegrante' value=''>"+
                                "<input type='hidden' id='idIntegrante' name='idIntegrante' value=''>"+
                                "<input type='hidden' id='NomIntegrante' name='NomIntegrante' value=''>"+
                                "<div class='input-group-btn'>"+
                                    "<a class='btn btn-Lapislazuli btn-bitbucket buscar' data-toggle='modal' data-target='#modalBuscar' title='Buscar'>"+
                                        "<i class='fa fa-search'></i>"+
                                    "</a>"+
                                "</div>"+
                            "</div>"+
                        "</div>"+
                    "</td>"+
                     "<td style='width: 7%'>"+
                        Datos.nombreApoderado +                        
                    "</td>" +
                    "<td style='width: 1%'>"+
                        "<input type='checkbox' checked='checked'/>"+                      
                    "</td>" +
                    "<td style='width: 1%'>"+
                        "<input type='checkbox' checked='checked'/>"+                      
                    "</td>" +
                    "<td style='width: 1%'>"+
                        "<input type='checkbox' checked='checked'/>"+                      
                    "</td>" +
                     "<td style='width: 2%'>"+ 
                     Datos.nombreConvenio+
                    "</td>" +
                    "<td class='text-center' style='width: 3%' data-ap='"+Datos.codigoApoderado+"' data-co='"+Datos.codigoConvenio+"'>" +
                    "<a class='btn btn-success btn-circle save' href='javascript:;' title='Guardar' ><i  class='fa fa-save'></i></a>\n" +
                    "<a class='btn btn-warning btn-circle cancel' href='javascript:;' title='Cancelar' ><i class='fa fa-ban'></i></a>" +
                    "</td>"+                   
                    "</tr>");
        }

    });

var fn_InSave = function (elem) {

        var par = $(elem).parent().parent();
        var td_NombreIntegrante = par.children("td:nth-child(2)");
        var td_Aporte = par.children("td:nth-child(4)");
        var td_FondoMortuorio = par.children("td:nth-child(5)");
        var td_Prestamo = par.children("td:nth-child(6)");       
        var tdbuttons = par.children("td:nth-child(8)");
        var id_codigo = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
        var id_convenio = tdbuttons.attr('data-co') === undefined ? 0 : tdbuttons.attr('data-co');
        var id_Apoderado = tdbuttons.attr('data-ap') === undefined ? 0 : tdbuttons.attr('data-ap');
        var Aporte =td_Aporte.children("input[type=checkbox]").is(":checked") ? 1 : 0;
        var FondoMortuorio =td_FondoMortuorio.children("input[type=checkbox]").is(":checked") ? 1 : 0;
        var Prestamo =td_Prestamo.children("input[type=checkbox]").is(":checked") ? 1 : 0;
        var TipoOperacion;
        var operacion=id_codigo;
        var nombreIntegrante;
        var codigoPersona;
        var validar;
        if(operacion===0){
            nombreIntegrante=$("#NomIntegrante").val();
            codigoPersona = $("#idIntegrante").val();
            TipoOperacion='insert';
            validar = $('#tbl_IntegrantePlanillaConvenioPlanillaForm').valid();
            
        }
        else {
            nombreIntegrante=td_NombreIntegrante.html();
            codigoPersona =id_codigo;
            validar=true;
            TipoOperacion='update';
        }       
     
        if (validar) {
            
            var data = {
                'IdPersona':codigoPersona ,'idApoderado':id_Apoderado,idConvenio:id_convenio,
                'Aporte':Aporte,'Prestamo':Prestamo,'FondoM':FondoMortuorio,'accion':TipoOperacion
            };
            var success = function (res) {

                if (res.Result =='OK'){
                  
                    td_NombreIntegrante.html(nombreIntegrante);
                    td_Aporte.html(Aporte === 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>');
                    td_FondoMortuorio.html(FondoMortuorio === 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>');
                    td_Prestamo.html(Prestamo === 1 ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>');
                    tdbuttons.html(
                            "<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a>\n"+
                            "<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>");
                    tdbuttons.attr("data-id",codigoPersona);
                    tdbuttons.attr("data-co",id_convenio);
                    tdbuttons.attr("data-ap",id_Apoderado);
                    
                    habilitarAgregar();
                    if(res.Message ==='upd_ok')
                    toast('success', 'Se Actualizo correctamente', 'Exito!');
                    else
                    toast('success', 'Se Registro correctamente', 'Exito!');
                }
                else{
                    toast('error', res.Message, 'Error');
                }
                    
            };
            var error = function (res) {
                
                toast('error', res.Message, 'Error');
            };

            fn_callmethod("../sIntegrantePlanillaConvenio?action=RegistroIntegrante", data, success, error);
        }
    };

var fn_InDelete = function (elem) {

    var par = $(elem).parent().parent();
    var tdbuttons = par.children("td:nth-child(8)");
    var idIntegrante = tdbuttons.attr('data-id');
    var id_convenio = tdbuttons.attr('data-co');
    var idApoderado = tdbuttons.attr('data-ap');

    var data = {'idApoderado': idApoderado,'idIntegrante':idIntegrante, 'idConvenio': id_convenio};
    
    var success = function (res) {
        
        if (res.Result === 'OK') {
            var par = $(elem).parent().parent();
            par.remove();
            toast('success', 'Se eliminó correctamente', 'Exito!');
        } else {
                   toast('error', res.Message, 'Error');
        }

    };
    var error = function () {
    };
    fn_callmethod("../sIntegrantePlanillaConvenio?action=eliminarIntegrante",data, success, error);
};

var fn_InCancel = function (elem) {


    var par = $(elem).parent().parent();
    var td_Aporte = par.children("td:nth-child(4)");
    var td_FondoMortuorio = par.children("td:nth-child(5)");
    var td_Prestamo = par.children("td:nth-child(6)");
    var tdbuttons = par.children("td:nth-child(8)");
    var id = tdbuttons.attr('data-id') === undefined ? 0 : tdbuttons.attr('data-id');
    if(id===0){
        par.remove();
    }
    else{         
        td_Aporte.html((td_Aporte.children("input[type=checkbox]").is(":checked") ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>'));
        td_FondoMortuorio.html((td_FondoMortuorio.children("input[type=checkbox]").is(":checked") ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>'));
        td_Prestamo.html((td_Prestamo.children("input[type=checkbox]").is(":checked") ? '<i class="fa fa-check-square-o"></i>' : '<i class="fa fa-square-o"></i>'));
        tdbuttons.html(
                "<a href='javascript:;'  class='btn btn-Lapislazuli btn-circle edit' title='Editar'><i class='fa fa-edit'></i></a>\n"+
                "<a href='javascript:;'  class='btn btn-danger btn-circle remove' title='Eliminar'><i class='fa fa-minus'></i></a></td>"
                ); 
    }
    habilitarAgregar();
  
};

function fn_InEdit(elem) {
    
    var par = $(elem).parent().parent();
    
    var td_Aporte = par.children("td:nth-child(4)");
    var td_FondoMortuorio = par.children("td:nth-child(5)");
    var td_Prestamo = par.children("td:nth-child(6)");       
    var tdbuttons = par.children("td:nth-child(8)");    
    var Aporte =td_Aporte.children("input[type=checkbox]").is(":checked") ? 1 : 0;
    var FondoMortuorio =td_FondoMortuorio.children("input[type=checkbox]").is(":checked") ? 1 : 0;
    var Prestamo =td_Prestamo.children("input[type=checkbox]").is(":checked") ? 1 : 0;
            
    Aporte = td_Aporte.children("i").hasClass("fa-square-o") ? "<input type='checkbox'/>" : "<input type='checkbox'  checked='checked'/>";
    td_Aporte.html(Aporte);
    FondoMortuorio = td_FondoMortuorio.children("i").hasClass("fa-square-o") ? "<input type='checkbox'/>" : "<input type='checkbox'  checked='checked'/>";
    td_FondoMortuorio.html(FondoMortuorio);
    Prestamo = td_Prestamo.children("i").hasClass("fa-square-o") ? "<input type='checkbox'/>" : "<input type='checkbox'  checked='checked'/>";
    td_Prestamo.html(Prestamo);
    tdbuttons.html('<a class="btn btn-primary btn-circle save"><i class="fa fa-save" title="Guardar"></i></a>\n<a class="btn  btn-warning btn-circle cancel" href="javascript:;" title="Cancelar" ><i class="fa fa-ban"></i></a>');           
};


function habilitarAgregar(){
    $('#btnAgregarPersonaPlanilla').prop("disabled", false);  
    $('.alertaI').show().html('');
}
 
 
