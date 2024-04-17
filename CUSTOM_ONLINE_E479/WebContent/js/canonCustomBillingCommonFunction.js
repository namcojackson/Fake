function showPleaseWait() {
      $.blockUI({ css: { border: 'none',
         padding: '15px',
         backgroundColor: '#000',
         '-webkit-border-radius': '10px',
         '-moz-border-radius': '10px',
         opacity: .5,
         color: '#fff',
         cursor: 'default'
      }, overlayCSS: { cursor: 'default' }, baseZ: 9000
      });
}
	   
function hidePleaseWait() {
    $.unblockUI();
}

function openCustomerProfileSearch(mName, options){
   var urlDetail = 'canonCustomBillingCustomerProfileLOV.jsp?modalName='+mName;
   if(options){
       urlDetail=urlDetail+"&"+jQuery.param(options);
   }
   modelName = "#"+mName;
   showPleaseWait();		
   $(modelName).dialog({
				height: 510,
				title: "Search Customer Profile",
				modal: true ,
        autoOpen :false,
				 width: 750,		
         resizable: false,      
				 buttons: { "Close": function() {  
                                        $(modelName).html("");
                                        $(this).dialog("close");
                                        $(this).dialog("destroy");
                                        $(modelName).trigger("closed");
                                        }
                    
               }					
			});
      
      $.ajax({
				url: urlDetail,
        cache: false,
				success: function(data){     
					hidePleaseWait();      
          $(modelName).html(data);
				}             
			});
     // alert('before');
      $(modelName).dialog("open");
}     //alert('after');

function openCustomerGroupSearch(mName, options){
	   var urlDetail = 'canonCustomBillingCustomerGroupLOV.jsp?modalName='+mName;
	   if(options){
	       urlDetail=urlDetail+"&"+jQuery.param(options);
	   }
	   modelName = "#"+mName;
	   showPleaseWait();		
	   $(modelName).dialog({
					height: 510,
					title: "Search Customer Group",
					modal: true ,
	        autoOpen :false,
					 width: 750,		
	         resizable: false,      
					 buttons: { "Close": function() {  
	                                        $(modelName).html("");
	                                        $(this).dialog("close");
	                                        $(this).dialog("destroy");
	                                        $(modelName).trigger("closed");
	                                        }
	                    
	               }					
				});
	      
	      $.ajax({
					url: urlDetail,
	        cache: false,
					success: function(data){     
						hidePleaseWait();      
	          $(modelName).html(data);
					}             
				});
	     // alert('before');
	      $(modelName).dialog("open");
	}     //alert('after');


function openManualCustomerGroupSearch(mName, options){
	   var urlDetail = 'canonCustomBillingManualCustomerGroupLOV.jsp?modalName='+mName;
	   if(options){
	       urlDetail=urlDetail+"&"+jQuery.param(options);
	   }
	   modelName = "#"+mName;
	   showPleaseWait();		
	   $(modelName).dialog({
					height: 510,
					title: "Search Customer Group",
					modal: true ,
	        autoOpen :false,
					 width: 750,		
	         resizable: false,      
					 buttons: { "Close": function() {  
	                                        $(modelName).html("");
	                                        $(this).dialog("close");
	                                        $(this).dialog("destroy");
	                                        $(modelName).trigger("closed");
	                                        }
	                    
	               }					
				});
	      
	      $.ajax({
					url: urlDetail,
	        cache: false,
					success: function(data){     
						hidePleaseWait();      
	          $(modelName).html(data);
					}             
				});
	     // alert('before');
	      $(modelName).dialog("open");
	} 



  function searchCustomerProfileLOV(mName, formName, pageNo, sortBy){
        showPleaseWait();	
        formId = "#"+formName;
        modelName = "#"+mName;
        if(pageNo){
            $(formId +" input[name='pageNo']").val(pageNo);
        }
        if(sortBy){
            existingSortOrder = $(formId+" input[name='sortOrder']").val();
            existingSortBy = $(formId+" input[name='sortBy']").val();   

            if(sortBy==existingSortBy){
                if(existingSortOrder=='asc'){
                    existingSortOrder = 'desc';
                }else{
                    existingSortOrder = 'asc'
                }
                $(formId+" input[name='sortOrder']").val(existingSortOrder);
            }else{
                existingSortOrder ='asc';
                $(formId+" input[name='sortOrder']").val(existingSortOrder);
                $(formId+" input[name='sortBy']").val(sortBy);
            }
        }
        var url = $(formId).attr('action');
        $.post( url, $(formId).serialize(),
        function( data ) {  
        hidePleaseWait();
         $(modelName).html("");
        $(modelName).html(data);
      });
    
    
  }
  
  function setCustomerProfileValue(mName,data){
     modelName = "#"+mName;
      $(modelName).html("");
     $(modelName).dialog("close");
     $(modelName).dialog("destroy");     
     $(modelName).trigger("selected",data );
  }  
  
  var makereadonly = function(selector, makeReadonly, selectedIndex) {
 
      $(selector).filter("select").each(function(i){
          var select = $(this);
 
          //remove any existing readonly handler
          if(this.readonlyFn) select.unbind("change", this.readonlyFn);
          if(this.readonlyIndex) this.readonlyIndex = null;
          if(selectedIndex){
              this.selectedIndex=selectedIndex;
          }
          if(makeReadonly) {
              this.readonlyIndex = this.selectedIndex;
              $(this).css('background-color','#CDCDCD'); //Adds a background colour to readonly item
              this.readonlyFn = function(){
                  this.selectedIndex = this.readonlyIndex;
              };
              select.bind("change", this.readonlyFn);
          }
      });
 
      //For input items
      $(selector).filter("input,textarea").attr('readOnly','readOnly');
      $(selector).filter("input,textarea").css('background-color','#CDCDCD');
 
  };

function openCustomerNameSearch(type,mName, options){
   var urlDetail;
   if (type =='CUSTOMER'){
	   urlDetail ='canonCustomBillingCustomerNameLOV.jsp?modalName='+mName;
   }
   if (type =='PARENT_CUSTOMER'){
	   urlDetail ='canonCustomBillingParentCustomerLOV.jsp?modalName='+mName;
   }
   if (type =='PARENT_CUSTOMER_MANUAL'){
	   urlDetail ='canonCustomBillingManualParentCustomerLOV.jsp?modalName='+mName;
   }
      
   if(options){
       urlDetail=urlDetail+"&"+jQuery.param(options);
   }
   modelName = "#"+mName;
   showPleaseWait();		
   $(modelName).dialog({
				height: 510,
				title: "Search Customer Name",
				modal: true ,
        autoOpen :false,
				 width: 750,		
         resizable: false,      
				 buttons: { "Close": function() {  
                                        $(modelName).html("");
                                        $(this).dialog("close");
                                        $(this).dialog("destroy");
                                        $(modelName).trigger("closed");
                                        }
                    
               }					
			});
      
      $.ajax({
				url: urlDetail,
        cache: false,
				success: function(data){     
					hidePleaseWait();      
          $(modelName).html(data);
				}             
			});
     // alert('before');
      $(modelName).dialog("open");
}     //alert('after');

  function searchcustomerNameLOV(mName, formName, pageNo, sortBy){
        showPleaseWait();	
        formId = "#"+formName;
        modelName = "#"+mName;
        if(pageNo){
            $(formId +" input[name='pageNo']").val(pageNo);
        }
        if(sortBy){
            existingSortOrder = $(formId+" input[name='sortOrder']").val();
            existingSortBy = $(formId+" input[name='sortBy']").val();   

            if(sortBy==existingSortBy){
                if(existingSortOrder=='asc'){
                    existingSortOrder = 'desc';
                }else{
                    existingSortOrder = 'asc'
                }
                $(formId+" input[name='sortOrder']").val(existingSortOrder);
            }else{
                existingSortOrder ='asc';
                $(formId+" input[name='sortOrder']").val(existingSortOrder);
                $(formId+" input[name='sortBy']").val(sortBy);
            }
        }
        var url = $(formId).attr('action');
        $.post( url, $(formId).serialize(),
        function( data ) {  
        hidePleaseWait();
         $(modelName).html("");
        $(modelName).html(data);
      });
    
  }
  
  function searchCustomerGroupLOV(mName, formName, pageNo, sortBy){
      showPleaseWait();	
      formId = "#"+formName;
      modelName = "#"+mName;
      if(pageNo){
          $(formId +" input[name='pageNo']").val(pageNo);
      }
      if(sortBy){
          existingSortOrder = $(formId+" input[name='sortOrder']").val();
          existingSortBy = $(formId+" input[name='sortBy']").val();   

          if(sortBy==existingSortBy){
              if(existingSortOrder=='asc'){
                  existingSortOrder = 'desc';
              }else{
                  existingSortOrder = 'asc'
              }
              $(formId+" input[name='sortOrder']").val(existingSortOrder);
          }else{
              existingSortOrder ='asc';
              $(formId+" input[name='sortOrder']").val(existingSortOrder);
              $(formId+" input[name='sortBy']").val(sortBy);
          }
      }
      var url = $(formId).attr('action');
      $.post( url, $(formId).serialize(),
      function( data ) {  
      hidePleaseWait();
       $(modelName).html("");
      $(modelName).html(data);
    });
  
  
}
  
  function setCustomerNameValue(mName,data){
      console.log("mName "+mName);
      console.log(data);
     modelName = "#"+mName;
      $(modelName).html("");
     $(modelName).dialog("close");
     $(modelName).dialog("destroy");     
     $(modelName).trigger("selected",data );
  }  
  
  function setCustomerGroupValue(mName,data){
      console.log("mName "+mName);
      console.log(data);
     modelName = "#"+mName;
      $(modelName).html("");
     $(modelName).dialog("close");
     $(modelName).dialog("destroy");     
     $(modelName).trigger("selected",data );
  }  

function openBillToSiteSearch(mName, options){
   var urlDetail = 'canonCustomBillingBillToSiteLOV.jsp?modalName='+mName;
   if(options){
       urlDetail=urlDetail+"&"+jQuery.param(options);
   }
   modelName = "#"+mName;
   showPleaseWait();		
   $(modelName).dialog({
				height: 560,
				title: "Search Bill To Location",
				modal: true ,
        autoOpen :false,
				 width: 750,		
         resizable: false,      
				 buttons: { "Close": function() {  
                                        $(modelName).html("");
                                        $(this).dialog("close");
                                        $(this).dialog("destroy");
                                        $(modelName).trigger("closed");
                                        }
                    
               }					
			});
      
      $.ajax({
				url: urlDetail,
        cache: false,
				success: function(data){     
					hidePleaseWait();      
          $(modelName).html(data);
				}             
			});
     // alert('before');
      $(modelName).dialog("open");
}     //alert('after');

  function searchBillToSiteLOV(mName, formName, pageNo, sortBy){
        showPleaseWait();	
        formId = "#"+formName;
        modelName = "#"+mName;
        if(pageNo){
            $(formId +" input[name='pageNo']").val(pageNo);
        }
        if(sortBy){
            existingSortOrder = $(formId+" input[name='sortOrder']").val();
            existingSortBy = $(formId+" input[name='sortBy']").val();   

            if(sortBy==existingSortBy){
                if(existingSortOrder=='asc'){
                    existingSortOrder = 'desc';
                }else{
                    existingSortOrder = 'asc'
                }
                $(formId+" input[name='sortOrder']").val(existingSortOrder);
            }else{
                existingSortOrder ='asc';
                $(formId+" input[name='sortOrder']").val(existingSortOrder);
                $(formId+" input[name='sortBy']").val(sortBy);
            }
        }
        var url = $(formId).attr('action');
        $.post( url, $(formId).serialize(),
        function( data ) {  
        hidePleaseWait();
         $(modelName).html("");
        $(modelName).html(data);
      });
    
    
  }
  
  function setBillToSiteValue(mName,data){
     modelName = "#"+mName;
      $(modelName).html("");
     $(modelName).dialog("close");
     $(modelName).dialog("destroy");     
     $(modelName).trigger("selected",data );
  }  
  

  var makereadonly = function(selector, makeReadonly, selectedIndex) {
 
      $(selector).filter("select").each(function(i){
          var select = $(this);
 
          //remove any existing readonly handler
          if(this.readonlyFn) select.unbind("change", this.readonlyFn);
          if(this.readonlyIndex) this.readonlyIndex = null;
          if(selectedIndex){
              this.selectedIndex=selectedIndex;
          }
          if(makeReadonly) {
              this.readonlyIndex = this.selectedIndex;
              $(this).css('background-color','#CDCDCD'); //Adds a background colour to readonly item
              this.readonlyFn = function(){
                  this.selectedIndex = this.readonlyIndex;
              };
              select.bind("change", this.readonlyFn);
          }
      });
 
      //For input items
      $(selector).filter("input,textarea").attr('readOnly','readOnly');
      $(selector).filter("input,textarea").css('background-color','#CDCDCD');
 
  };

// define console if not found.
if(typeof console == "undefined") console = { log: function(){} };

$('input.datePicker:not(.endLive)').live('focus click',function() {
    var elm=$(this);
    elm.addClass('endLive').datepicker({dateFormat: 'M d, yy',changeMonth: true, changeYear: true}).focus();
    elm.bind("keyup",function(event){
        if(event.keyCode==46){
            elm[0].value="";
            elm.datepicker("hide");
        }
    });
    return false;
});

// Bring datepicker back if click scroll bar in IE.
$(document).click(function(){
    var elm=$(document.activeElement);
    if(elm && elm.hasClass("datePicker")){
        setTimeout(function(){ 
            elm.datepicker("show");
        },200);
    }
});
