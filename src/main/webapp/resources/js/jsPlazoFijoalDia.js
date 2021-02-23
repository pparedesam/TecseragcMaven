/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var oTable;
$(function () {
    oTable = fn_iniciarDT(oTable, "tblPlazoFijoDia");
    
    oTable.on("click", ".edit", function () {
        fn_Edit(this);
    });

         
});


function fn_Edit(elem) {
   
  
    var NumCuentaNueva = $(elem).parent().attr('data-idnumcuentanueva') === undefined ? 0 : $(elem).parent().attr('data-idnumcuentanueva');
    var OficinaNueva = $(elem).parent().attr('data-oficinanueva') === undefined ? 0 : $(elem).parent().attr('data-oficinanueva');
    var TipoCtaNueva = $(elem).parent().attr('data-tipctanueva') === undefined ? 0 : $(elem).parent().attr('data-tipctanueva');
    var TipMonedaNueva= $(elem).parent().attr('data-tipmonedanueva') === undefined ? 0 : $(elem).parent().attr('data-tipmonedanueva');
    
    var NumCuentaAnt = $(elem).parent().attr('data-idnumcuentaant') === undefined ? 0 : $(elem).parent().attr('data-idnumcuentaant');
    var OficinaAnt = $(elem).parent().attr('data-oficinaant') === undefined ? 0 : $(elem).parent().attr('data-oficinaant');
    var TipoCtaAnt = $(elem).parent().attr('data-tipctaant') === undefined ? 0 : $(elem).parent().attr('data-tipctaant');
    var TipMonedaAnt= $(elem).parent().attr('data-tipmonedaant') === undefined ? 0 : $(elem).parent().attr('data-tipmonedaant');
    
   
    window.open('http://190.41.141.164:808/ReportServer/Pages/ReportViewer.aspx?%2fPyAfiliaciones%2fRptCertificadoPlazoFijo&rs:Command=Render&IdOficina=' + OficinaNueva + '&TipMoneda=' + TipMonedaNueva + '&IdTipCta=' + TipoCtaNueva + '&NumCuenta=' + NumCuentaNueva + '&TipoImpresion=2&IdOficinaAnt=' + OficinaAnt + '&TipMonedaAnt=' + TipMonedaAnt + '&IdTipCtaAnt=' + TipoCtaAnt + '&NumCuentaAnt=' + NumCuentaAnt);

    


};