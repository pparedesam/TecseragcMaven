/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var oTable;
$(function () {
    oTable = fn_iniciarDT(oTable, "tblFichaSocioDia");
    
    oTable.on("click", ".edit", function () {
        fn_Edit(this);
    });

         
});


function fn_Edit(elem) {
   
  
    var idpersona = $(elem).parent().attr('data-idpersona') === undefined ? 0 : $(elem).parent().attr('data-idpersona');
    var tipopersona = $(elem).parent().attr('data-tipop') === undefined ? 0 : $(elem).parent().attr('data-tipop');
  
  
   
   if(tipopersona==='JURIDICO'){
       window.open('http://svrreports:808/ReportServer/Pages/ReportViewer.aspx?%2fPyAfiliaciones%2fRptFichaSocioJuridico&rs:Command=Render&idpersona=' + idpersona);
   }else if(tipopersona==='NATURAL'){
       window.open('http://svrreports:808/ReportServer/Pages/ReportViewer.aspx?%2fPyAfiliaciones%2fRptFichaSocio&rs:Command=Render&idpersona=' + idpersona);
   }
   
    
  



};