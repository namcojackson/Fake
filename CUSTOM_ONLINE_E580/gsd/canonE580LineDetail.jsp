
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>

<%!    
    final String PAGE_NAME = "canonE580LineDetail.jsp";

%>


<html>
    <head>
        <TITLE>Canon ITT Workbench Line Detail</TITLE>
        
            <%@include file="canonE580ITTWorkbenchHead.jsp"%>
            <%-- <script src="<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/datatables/bootstrap.min.js" type='text/javascript'></script> 
			<link href="<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/datatables/bootstrap.min.css" rel='stylesheet' type='text/css' /> --%>

        <script language="javascript">

            function ITTDetailSearch()
            {
                var url = $("#lineDetailForm").attr('action');
                blockUsrInterface();
                $.post(url, $("#lineDetailForm").serialize(), function(data) {
                    $("#divCanonE580Main").html("");
                    $("#divCanonE580Main").html(data);
                    unBlockUsrInterface();
                });
            }

            function ITTDetailSave()
            {
                $("#save_flag").val('Y');
                var url = $("#lineDetailForm").attr('action');
                blockUsrInterface();
                $.post(url, $("#comments,#add_to_popdf,#lineDetailForm").serialize(), function(data) {
                    $("#divCanonE580Main").html("");
                    $("#divCanonE580Main").html(data);
                    unBlockUsrInterface();
                });
            }

            function ITTNoteSave()
            {
                $("#save_flag").val('add_new_note');
                var url = $("#lineDetailForm").attr('action');
                blockUsrInterface();
                $.post(url, $("#lineDetailForm").serialize(), function(data) {
                    $("#divCanonE580Main").html("");
                    $("#divCanonE580Main").html(data);
                    unBlockUsrInterface();
                });
            }

            function ITTUpdatePricing()
            {
                $("#save_flag").val('update_pricing');
                var url = $("#lineDetailForm").attr('action');
                blockUsrInterface();
                $.post(url, $("#lineDetailForm").serialize(), function(data) {
                    $("#divCanonE580Main").html("");
                    $("#divCanonE580Main").html(data);
                    unBlockUsrInterface();
                });
            }
            
            function openITTSelect(mName,title,jsp,height,width, options)
            {
            	//blockUsrInterface();
            	 var urlData = jsp + "?modalName=" + mName;
                if (options) {
                    urlData = urlData + "&" + jQuery.param(options);
                }        
               var  modelName = "#" + mName;
                $(modelName).dialog({
        				height: 500,
        				title: title, 
        				modal: true ,
        				zIndex:1005,
        				width: 650,		
                     resizable: false
        			});
                
                
                $.ajax({
                    url: urlData,
                    cache: false,
                    success: function(data) {
                        //unBlockUsrInterface();
                        $(modelName).html("");
                        $(".ui-dialog-buttonpane").remove();
                        $(modelName).html(data);        
                        var buttons='<div class="itt-select-po ui-dialog-buttonpane ui-widget-content ui-helper-clearfix"><div class="ui-dialog-buttonset"><button type="button" class="itt-select-po-confirm ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only btn" role="button" aria-disabled="false"><span class="ui-button-text">Confirm</span></button><button type="button" class="itt-select-po-cancel ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only btn" role="button" aria-disabled="false"><span class="ui-button-text">Cancel</span></button></div></div>';
                        $(buttons).insertAfter("#SelectPO-DataDiv");
                    }
                });
                $(modelName).dialog("open");
                $(".ui-dialog-titlebar").addClass("hd");
        	    $(".ui-dialog").css({"top":"330px"}); 
        	    $('.ui-widget-overlay').css({'background':'none'});
            }
            
            
            function ITTDetailCreatePO(called_from, title_, itt_number)
            {
                $("#save_flag").val('CREATE_PO');
                $("#create_po_called_from").val(called_from);
                
                var url = $("#lineDetailForm").attr('action');
                blockUsrInterface();
                var ser=$("#comments,#lineDetailForm").serialize();

                $.ajax({
                    type: "POST",
                    url: url,
                    data: ser,
                    dataType: "json",
                    success: function(data){
                        if("SINGLE"===data.status_code){
                        	$("#add_to_multiple_po").val("N");
                        	ITTDetailCreatePO(called_from,title_);
                        }else if("MULTIPLE"===data.status_code){
                            setTimeout(function(){ 
    	                        unBlockUsrInterface();
                            	openITTSelect("SelectPO-DataDiv","ITT Select PO",'canonE580SelectPO.jsp',350,550,{"itt_number":itt_number});
                            }, 600);
                        	
                        }else{
	                        $("#create_po_dialog").html("");
	                        var html="";
	                        if("ERROR"===data.status_code){
	                            html+= title_ + " failed with following error message:";
	                        }else if("SUCCESS"===data.status_code){
	                            html+= title_ + " succeed.";
	                        }else{
	                            html+=data.status_code + " ";
	                        }
	                        
	                        if(/\n/.test(data.status_message)){
	                            html=html+"<ul><li>"+data.status_message.replace(/\n/g,"</li><li>")+"</ul>";
	                        }else{
	                            html=html+"  "+data.status_message;
	                        }
	                        $("#create_po_dialog").html(html);
	                		var isCreatePO=called_from=="C";
	                        
	                        $( "#create_po_dialog" ).dialog(
	                            {modal: true,
	                            title: isCreatePO ? "Create PO" : "Update PO", 
	                            width:500,
	                            buttons: {
	                              Ok: function() {
	                                $( this ).dialog( "close" );
	                              }}});
                    	}
                    },
                    complete: function(){
                        unBlockUsrInterface();
                    },
                    error: function( jqXHR, textStatus, errorThrown ){
                        console.log(textStatus);
                        console.log(errorThrown);
                    }
                });
            }
    
	        function setDeclineMaint(ele, vittnum,vmodel){
	               // alert(vittnum);
		        	var flg="";
	                var urlDetail ="CanonE580ITTUtil.jsp";
	                vittnum = encodeURIComponent(vittnum);     
		        	vmodel = encodeURIComponent(vmodel); 		
		        	
		        	if ($(ele).is(':checked')) {
		        		flg="Y";
		        	} else {
		        		flg="N";
		        	} 
		        	//alert(flg);
		        	var qryString ="ittnum="+vittnum+"&model="+vmodel+"&flag="+flg
		        	
		        	$.ajax( {
		        		url : urlDetail,
		        		cache : false,
		        		type : "POST",
		        		data : qryString,
		        		success : function(result) {
		        		     if ( $.trim(result)=="E")
		        			     alert("Error occured while updating decline maintenance flag.");
		                	}
		        	});
					ITTDetailSearch();
		        }			
				 
            
            $(function() {            	 
                
                function isEmpty(str){
                    return typeof(str)=="undefined" || $.trim(str).length == 0; 
                }
                
                function address(addressLine1, addressLine2, addressLine3) {
                    return concat(addressLine1,concat(addressLine2,addressLine3,"\n"),"\n");
                }

                function cityState(city,state){
                    return concat(city,state,",");
                }

                function concat(s1,s2,delimiter){
                    return isEmpty(s2) ?  s1 :  s1 +delimiter+ s2;
                }
            
                function resetSelectTitles(){
                    $('select').attr('title', function(i, title) {
                        return this.value;
                    });                
                    $('select option').attr('title', function(i, title) {
                        return this.text;
                    });                
                }
                
                $("#ITTOrderProcessorLOV-DataDiv").bind("selected", function(event, object) {
                    $("#ITTOrderProcessorLOV-DataDiv").html("");
                    $("#ITTOrderProcessorLOV-DataDiv").dialog("close");
                    $("#ITTOrderProcessorLOV-DataDiv").dialog("destroy");
                    $("#itt_order_processor_display").val(object.display);
                    $("#itt_order_processor_id").val(object.source_number);
                    $("#itt_order_processor_name").val(object.source_name);
                });
                
                $("#DealerCodeLOV-DataDiv").bind("selected", function(event, object) {
                    $("#DealerCodeLOV-DataDiv").html("");
                    $("#DealerCodeLOV-DataDiv").dialog("close");
                    $("#DealerCodeLOV-DataDiv").dialog("destroy");
                    $("#dealer_code_display").val(object.dealer_code_display);
                    $("#dealer_code_id").val(object.dealer_code_id);
                    $("#dealer_code_name").val(object.dealer_name);
                    $("#dealer_contact_name").val(object.dealer_contact_name);
                    $("#dealer_phone").val(object.dealer_phone);
                    $("#dealer_email").val(object.dealer_email);
                    $("#dealer_address").val(address(object.addressLine1,object.addressLine2,object.addressLine3));
                    $("#dealer_city_state").val(cityState(object.city,object.state));
                    $("#dealer_zip").val(object.zip);
                    $("#dealer_country").val(object.country);
                    $("#vendor_id").val(object.vendor_id);
                    $("#vendor_site_id").val(object.vendor_site_id);                   
                    $("#dealer_supplier_code").val(object.dealer_supplier_code);
                });
                
				$("#DealerCodeCLOV-DataDiv").bind("selected", function(event, object) {
                    $("#DealerCodeCLOV-DataDiv").html("");
                    $("#DealerCodeCLOV-DataDiv").dialog("close");
                    $("#DealerCodeCLOV-DataDiv").dialog("destroy");
                    $("#dealer_code_display").val(object.dealer_code_display);
                    $("#dealer_code_id").val(object.dealer_code_id);
                    $("#dealer_code_name").val(object.dealer_name);
                    $("#dealer_contact_name").val(object.dealer_contact_name);
                    $("#dealer_phone").val(object.dealer_phone);
                    $("#dealer_email").val(object.dealer_email);
                    $("#dealer_address").val(address(object.addressLine1,object.addressLine2,object.addressLine3));
                    $("#dealer_city_state").val(cityState(object.city,object.state));
                    $("#dealer_zip").val(object.zip);
                    $("#dealer_country").val(object.country);
                    $("#vendor_id").val(object.vendor_id);
                    $("#vendor_site_id").val(object.vendor_site_id);
                });               
                
                $("#DealerShipToCNACodeLOV-DataDiv").bind("selected", function(event, object) {
                    $(this).html("");
                    $(this).dialog("close");
                    $(this).dialog("destroy");
                    $("#dealer_ship_to_cna_code").val(object.dealer_ship_to_cna_code);
                    $("#dealer_ship_to_cna_code").data("saved_value",object.dealer_ship_to_cna_code);
					$("#dealer_shipto_name").val(object.dealer_shipto_name);
					$("#dealer_shipto_address").val(object.dealer_shipto_address);
					$("#dealer_shipto_city_state").val(cityState(object.city,object.state));
					$("#dealer_shipto_zip").val(object.dealer_shipto_zip);
                    $("#dealer_ship_to_cna_code").data("saved_value",object.dealer_ship_to_cna_code);
                });
                
				$("#DealerShipToCNADnameLOV-DataDiv").bind("selected", function(event, object) {
                    $("#DealerShipToCNADnameLOV-DataDiv").html("");
                    $("#DealerShipToCNADnameLOV-DataDiv").dialog("close");
                    $("#DealerShipToCNADnameLOV-DataDiv").dialog("destroy");
                    $("#dealer_ship_to_cna_code").val(object.dealer_ship_to_cna_code);
					$("#dealer_shipto_name").val(object.dealer_shipto_name);
					$("#dealer_shipto_address").val(object.dealer_shipto_address);
					$("#dealer_shipto_city_state").val(cityState(object.city,object.state));
					$("#dealer_shipto_zip").val(object.dealer_shipto_zip);
                    $("#dealer_ship_to_cna_code").data("saved_value",object.dealer_ship_to_cna_code);
                });
                
                $("body").on("click",".itt-select-po .ui-button",function(event){
                	event.preventDefault();
                	if($(this).hasClass("itt-select-po-cancel")){
                        $("#SelectPO-DataDiv").html("");
                        $("#SelectPO-DataDiv").dialog("close");
                        $("#SelectPO-DataDiv").dialog("destroy");
                    	
                	}else if($(this).hasClass("itt-select-po-confirm")){
                		var valid=true;
                    	if($(".cusa_po").length>0 ){
                    		if(! $(".cusa_po:checked").val()){
                    			$("#select_po_error_message").html("Please select a CUSA PO!");
                    			valid=false;
                    		}	
                    	}
                    	
                    	if(valid && $(".dealer_po").length>0){
                    		if(!$(".dealer_po:checked").val()){
                    			$("#select_po_error_message").html("Please select a dealer PO!");
                    			valid=false;
                    		}
                    	}
                    	
                    	if(valid){
                			$("#cusa_po_num").val($(".cusa_po:checked").val());
                			$("#dealer_po_num").val($(".dealer_po:checked").val());
                			$("#add_to_multiple_po").val("Y");
                			
                            $("#SelectPO-DataDiv").html("");
                            $("#SelectPO-DataDiv").dialog("close");
                            $("#SelectPO-DataDiv").dialog("destroy");
                            
                        	ITTDetailCreatePO('A','Add To Existing PO');
                    	}
                    	
                	}
                	
                });
                
                
               /*  $("select").live('change',function(){
                    this.title=this.value;
                    $('option',this).attr('title', function(i, title) {
                        return this.text;
                    });                
                }); */

               /*  $(".select-container select")
                        .live('mousedown', function() {
                    if (this.dontWiden)
                        return;
                    var styledWidth = this.offsetWidth;
                    if (!$(this).data("origWidth")) {
                        $(this).data("origWidth", $(this).css("width"));
                    }
                    $(this).css("width", "auto");
                    var desiredWidth = this.offsetWidth;
                    // If this control needs less than it was styled for, then we don't need to bother with widening it.
                    if (desiredWidth < styledWidth) {
                        this.dontWiden = true;
                        $(this).css("width", $(this).data("origWidth"));
                    }

                })
                        .live('blur change', function() {
                    $(this).css("width", $(this).data("origWidth"));
                    this.dontWiden = false;
                }) */

                resetSelectTitles();
                
            });


        </script>
    </head>
    <div class="divHeader logo-img">
	<div id="TopDiv">
            <%@include file="canonE580ITTWorkbenchTop.jsp"%>
        </div>
	
	</div>

    <body>

	<div id ="main_content">	
   		<div id="page_top">
			<h1>Inter-Territorial Workbench Line Details</h1>
		</div>
        
        <div id = "divCanonE580Main">
            <jsp:include page="canonE580LineDetailInclude.jsp"/>
        </div>

        <div id="ITTOrderProcessorLOV-DataDiv"></div>
        <div id="DealerCodeLOV-DataDiv"></div>
		<div id="DealerCodeCLOV-DataDiv"></div>
        <div id="MarkviewLOV-DataDiv"></div>
        <div id="DealerShipToCNACodeLOV-DataDiv"></div>
		<div id="DealerShipToCNADnameLOV-DataDiv"></div>		
        <div id="ReasonCodeLOV-DataDiv"></div>
        <div id="ScheduledDDHistory-DataDiv"></div>
        <div id="SelectPO-DataDiv"></div>
        <div id="create_po_dialog"></div>        
		<div id="DealerCompLOV-DataDiv"></div>            <!--Added Raghavendra Uppala ITG690711--> 
		<div id="validatation-DataDiv"></div>  <!-- ITG690711--> 
		</div>

    </body>
</html>