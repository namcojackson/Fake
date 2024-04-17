<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingManualDAO"%>
<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingManualUtil"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="canon.apps.pci.util.CanonPCISecurityUtil"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%!
   static String CANON_SIG="===CANON===";
%>
       
<%
    String action = request.getParameter("action");
    if("upload".equals(action)){
        System.out.println("action is "+action);
        try{
            String result=CanonCustomBillingManualUtil.upload(pageContext, request, response);
%>
<%=CANON_SIG%><%=result%>
<%
        }catch(Exception ex){
%>
<%=CANON_SIG%>{"success":false,"error":"<%=ex.getMessage()%>"}
<%
        }
    }else{
%>
<script language="javascript">
    
    function validateEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }
    
    function isNumeric(n) {
      return !isNaN(parseFloat(n)) && isFinite(n);
    }

    function showMessage(msg){
     $(".message").html(msg);
    }
    
    function clearMessage(){
     $(".message").html("");
    }

    $( document ).ready(function() {
            var defaultBillerNameIndex=$("#biller_name")[0].selectedIndex;
            
            $("#BillDate").datepicker({ dateFormat: 'M d, yy' });
            
            //$("#bill_period").datepicker({ dateFormat: 'M, yy' });
            $(".monthPicker").datepicker({
    	    	   showOn: 'button',
    	        dateFormat: 'M-yy',
    	        changeMonth: true,
    	        changeYear: true,
    	        showButtonPanel: true,
    	 		closeText  : "ok",
    	 		onClose: function (dateText, inst) {
    	 	        var isDonePressed = inst.dpDiv.find('.ui-datepicker-close').hasClass('ui-state-hover');
    	 	        if (!isDonePressed)
    	 	            return;

    	 	        var month = inst.dpDiv.find('.ui-datepicker-month').find(':selected').val(),
    	 	            year = inst.dpDiv.find('.ui-datepicker-year').find(':selected').val();

    	 	        $('.monthPicker').datepicker('setDate', new Date(year, month, 1)).change();
    	 	        $('.monthPicker').focusout();


    	 	    },
    	 	    beforeShow: function (input, inst) {
    	 	        var $this = $('.monthPicker'),
    	 	            dateFormat = 'd ' + $this.datepicker('option', 'dateFormat'),
    	 	            date;

    	 	        try {
    	 	            date = $.datepicker.parseDate(dateFormat, '1 ' + $this.val());
    	 	        } catch (ex) {
    	 	            return;
    	 	        }

    	 	        $this.datepicker('option', 'defaultDate', date);
    	 	        $this.datepicker('setDate', date);

    	 	        inst.dpDiv.addClass('datepicker-month-year');
    	                 $this.next(".ui-datepicker-trigger").addClass("negative").css({'display' : 'inline','line-height' : '10px'});
    	 	    }

    	    }).next(".ui-datepicker-trigger").addClass("negative").css({'display' : 'inline','line-height' : '10px'});

    	     $(".monthPicker").focus(function () {
    	 	       $(".ui-datepicker-calendar").hide();
    	 	       $('.date-picker').focusout();
    	 	       $('.date-picker').hide();
    	 	       $("#ui-datepicker-div").position({
    	 	           my: "center top",
    	 	           at: "center bottom",
    	 	           of: $(this)
    	 	       });
    	 	   });
            
            function clearAll(){
                $("#customer_name, #customer_id, #customer_number, #parentCustomer, #customerGroupNm, #bill_location, #biller_email, #bill_amount, #bill_period, #special_bill_file, #comments").val("");
                $('#manualUploadForm').trigger("reset");
                $("#biller_name")[0].selectedIndex=defaultBillerNameIndex;
            }

      	    $('input.date-picker').datepicker();
    	   	$(".datePicker").each(function() {
    	   			$(this).datepicker( {
    	   			    dateFormat: 'dd-M-yy',
    	   				changeMonth : true,
    	   				changeYear : true
    	   			});
    	    	});
            
            
            function openCustNameSearch(mName, options){
               //var urlDetail = 'canonCustomBillingCustNameLOV.jsp?modalName='+mName;
               var urlDetail = 'canonCustomBillingCustomerNameLOV.jsp?modalName='+mName;
               //alert("urlDetail "+urlDetail);
               if(options){
                   urlDetail=urlDetail+"&"+jQuery.param(options);
               }
               modelName = "#"+mName;
               //alert("modelName "+modelName);
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
            }
        
        
            function openBillLocationSearch(mName, options){
               var urlDetail = 'canonCustomBillingBillLocationLOV.jsp?modalName='+mName;
               if(options){
                   urlDetail=urlDetail+"&"+jQuery.param(options);
               }
               modelName = "#"+mName;
               showPleaseWait();		
               $(modelName).dialog({
                                            height: 510,
                                            title: "Search Bill Location",
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
        
        $('#manualUploadForm').iframePostForm
        ({
            post : function ()
            {

                if ($('input[type=file]').val().length)
                {
                    $('.message')
                    .html('Uploading file&hellip;')
                    .slideDown();
                    showPleaseWait();
                }

                else
                {
                    $('.message')
                    .html('Please select an file for uploading.')
                    .slideDown();

                    return false;
                }
            },
            complete : function (response)
            {
                if(response.substring(0,9)=="Exception"){
                    showMessage(response);
                }else{
                    var sigidx=response.indexOf("<%=CANON_SIG%>");
                    var res=response.substr(sigidx+<%=CANON_SIG.length()%>);
                    var readjson=$.parseJSON(res);
                    if(readjson.success){
                        showMessage("file uploaded successfully. URN number is "+readjson.urn);
                        clearAll();
                    }else{
                        showMessage(readjson.error);
                    }
                    hidePleaseWait();
                }
            },
            error : function (err)
            {
                if (console) console.log(err);
                $('.message').html("A server error occurred, excel upload failed.");
                hidePleaseWait();
            }
        });

//        console.log( "ready!" );
        $("#manualBillUpload").click(function(event){
            event.preventDefault();
  
            if(($.trim($("#customer_name").val())!="") && ($.trim($("#searchCustomerProfile").val())!="")){
                showMessage("Customer Or Profile name is required.");
                return;
            }

            if(($.trim($("#customer_name").val())=="") && ($.trim($("#searchCustomerProfile").val())=="")){
                showMessage("Customer Or Profile name is required.");
                return;
            }

            if ($.trim($("#custnameselect").val())!= $.trim($("#customer_name").val())) {
                showMessage("Customer name is not valid.");
                return;
            } 

            if ($.trim($("#custprofileselect").val())!= $.trim($("#searchCustomerProfile").val())) {
                showMessage("Profile Name is not valid.");
                return;
            }            

            if($.trim($("#cust_email").val())==""){
                showMessage("Customer Email is required.");
                return;
            }
            
            if($.trim($("#BillDate").val())!="") {

            	var today = new Date();
            	//showMessage("Today " + today);

            	var month = today.getMonth()+1;
    			var day = today.getDate();
    			var year = today.getYear();

    			var newdate = new Date(month + "/" + day + "/" + year);
    			//var newdate = new Date(year,month,day);
            	
            	
	            var given = $.trim($("#BillDate").val());
	            
	            var givenDate = new Date(given);
	            
	            if(givenDate > newdate ){
	            	showMessage("Bill Date should be equal or less than today Date ");
	            	return;
	            }
	            
            }
            
/*          if($.trim($("#customer_name").val())==""){
                showMessage("Customer name is required.");
                return;
            }
 */   //Commented by DUNA RAO
 
            if($("#bill_period")[0].selectedIndex==0){
                showMessage("Bill period is required.");
                return;
            }
            
            if($.trim($("#biller_name").val())==""){
                showMessage("Biller name is required.");
                return;
            }

            if($.trim($("#bill_amount").val())!=="" && !isNumeric($("#bill_amount").val())){
                showMessage("Invalid bill amount.");
                return;
            }    
            
            if($.trim($("#biller_email").val())!=="" && !validateEmail($("#biller_email").val())){
                showMessage("Invalid biller email.");
                return;
            }    

            $("#manualUploadForm").submit();
            
        });
        
        $("#clearAll").click(function(){
            location.reload();
        });
        
        $("#open_cust_name_lov").click(function(){
        	showMessage("");
            openCustNameSearch("custNameLovDiv");
        });
        
        $("#custNameLovDiv").bind("selected", function(event,data){
            //$("#acctNum").val(data.accountNumber);
            //$("#searchPartyName").val(data.partyName);
//            console.log(data);
			//alert("Data - "+data.customerName);
			//alert("DatacustNumber - "+data.customerNumber);
            $("#customer_name").val(data.customerName);
            $("#customer_number").val(data.customerNumber);
            $("#custnameselect").val(data.customerName);
            if(data.custID!=$("#customer_id")){
                //$("#customer_id").val(data.custID);
                $("#customer_id").val(data.customerNumber);
                $("#bill_location").val("");
            }
        });

        $(".customerGroupDot").live('click',function(){
        	if($("#customer_id").val()==""){
                showMessage("Please select a customer name.");
            }else{
            	showMessage("");
              openManualCustomerGroupSearch('customerGroupLovDiv',{"customer_id":$("#customer_id").val()});
            }
        });
        
        $("#customerGroupLovDiv").bind("selected", function(event,data){
            $("#customerGroupNm").val(data.customerGroupName);
        });

        $(".parentCustomerDot").live('click',function(){
        	if($("#customer_id").val()==""){
                showMessage("Please select a customer name.");
            }else{
            	showMessage("");
            openCustomerNameSearch('PARENT_CUSTOMER_MANUAL','parentCustomerLovDiv',{"customer_id":$("#customer_id").val()});
            }
        });
        
        $("#parentCustomerLovDiv").bind("selected", function(event,data){
            $("#parentCustomer").val(data.parentCustomer);
        });

        
        $("#open_bill_location_lov").click(function(){
            if($("#customer_id").val()==""){
                showMessage("Please select a customer name.");
            }else{
            	showMessage("");
                openBillLocationSearch("billLocationLovDiv",{"customer_id":$("#customer_id").val()});
            }
        });

        $("#billLocationLovDiv").bind("selected", function(event,data){
            //$("#acctNum").val(data.accountNumber);
            //$("#searchPartyName").val(data.partyName);
//            console.log(data);
            $("#bill_location").val(data.location);
        });

    });    
    
</script>
<!-- --------------------------- Canon changes Begin -------------------------------- -->    
<%
    CanonCustomBillingCommon C = new CanonCustomBillingCommon();
    int userId = C.toInt(request.getParameter("userId"));

    String customerName = request.getParameter("customer_name");
    String billerName = request.getParameter("biller_name");
    String billerEmail = request.getParameter("biller_email");
    String billAmount = request.getParameter("bill_amount");
    String custEmail = request.getParameter("cust_email");

    String searchCustomerProfile = CanonCustomBillingCommon.nonNullify(request.getParameter("searchCustomerProfile"));

    String errorMessage = null;

%>

<form id="manualUploadForm" name="manualUploadForm"  method="post" enctype="multipart/form-data" action="canonCustomBillingManualUploadInclude.jsp?action=upload" >
    <input type="hidden" id="userId" name="user_id" value="<%=userId%>"></input>
    <input type="hidden" id="customer_number" name="customer_number" ></input>
    <input type="hidden" id="customer_id" name="customer_id" ></input>
    <input type="hidden" id="custnameselect" name="custnameselect" ></input>
    <input type="hidden" id="custprofileselect" name="custprofileselect" ></input>

    <table align="center" width="850" border="0" cellspacing="0" cellpadding="2" summary="">
        <tr>
            <td class="sectionHeaderBlack" align="left"><div id="serialNumberDiv">Custom Billing Manual Upload</div></td>
        </tr>
        <tr>
            <td align="left" valign=top width="80%" colspan=2><hr width=100%></td>
        </tr>
    </table>
    <div class="message" ></div>
    <table align="center" width="800" border="0" cellspacing="1" cellpadding="2" summary="">
        <tr>
            <td align="left" class="eventableDataCell" style="font-weight:bold;">
                Customer Name *&nbsp;
            </td>
            <td cellspacing="0" cellpadding="0" cellspacing="2" >
                <table>
                    <tr>
                        <td align="left" class="eventableDataCell" style="font-weight:bold;">
                            <input type="text" id="customer_name" name="customer_name" maxlength="200" style="text-align:left" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(customerName))%>"></input>
                        </td>
                        <td class="buttons">
                            <a href="#" id="open_cust_name_lov" class="negative billToSiteDot" style="text-align:'center';font-family:'Verdana,Arial,sans-serif';font-size:'12px';height:'15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                        </td>
                    </tr>
                </table>
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Bill Period *&nbsp;
            </td>

            <!--<td><input type="text" id="bill_period" name ="bill_period" class="datePicker" value = "" ></td>
            -->

            <td class="eventableDataCell buttons"  align="left">
  	       <input type="text" id="bill_period" name="bill_period" class="monthPicker" 
  	          value=""
  	           size="9" readOnly="true" style="font-size:11px"></input>						
		  </td>
            <%-- <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                    <select id="bill_period" name="bill_period" style="text-align:left;margin-left:3px">
                        <option value=""></option>
                        <%
                            Object [] objs=CanonCustomBillingManualDAO.getBillPeriodLov();
                            String validationStatus=(String)CanonCustomBillingCommon.second(objs);
                            if("S".equals(validationStatus)){
                                List l=(List)CanonCustomBillingCommon.first(objs);
                                for(int i=0;i<l.size();i++){
                                    CanonCustomBillingManualDAO.BillPeriodInfo bi=(CanonCustomBillingManualDAO.BillPeriodInfo) l.get(i);
                                    String s__=CanonCustomBillingCommon.nonNullify(bi.getAccountingPeriod());
                                    %><option value="<%=s__%>"><%=s__%></option> <%
                                }
                            }
                        %>
                    </select>
            </td> --%>
        </tr>
        <tr>
<!--             <td align="left" class="eventableDataCell" style="font-weight:bold;">
                Profile Name *&nbsp;
            </td>
            <td cellspacing="0" cellpadding="0" cellspacing="2" >
				<table >
                     <tr valign="middle">
                           <td class="bsd_text" align="left">
                           <input type="text" name="searchCustomerProfile"  id="searchCustomerProfile"  value=" " ></td> 
                           <input type="hidden" name="customerGroupNm" id="customerGroupNm" >
                           <td class="buttons">
                                <a href="#" class="negative customerProfileDot"  style="text-align='center';font-family='Verdana,Arial,sans-serif';font-size='12px';height='15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                           </td>					
                      </tr>
                  </table>
			</td> -->

            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Bill Location:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                    <table>
                    <tbody><tr>
                        <td align="left" class="eventableDataCell" style="font-weight:bold;">
                            <input type="text" id="bill_location" name="bill_location" maxlength="200" style="text-align:right" value="">
                        </td>
                        <td class="buttons">
                            <a href="#" id="open_bill_location_lov" class="negative" style="text-align:'center';font-family:'Verdana,Arial,sans-serif';font-size:'12px';height:'15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                        </td>
                    </tr>
                </tbody></table>                
            </td>

            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Bill Date &nbsp;
            </td>
            <td><input type="text" id="BillDate" name ="BillDate" class="datePicker" value = "" ></td>
            </td>
        </tr>
        <tr>
            <td align="left" class="eventableDataCell" style="font-weight:bold;">
                Parent Customer&nbsp;
            </td>
            <td cellspacing="0" cellpadding="0" cellspacing="2" >
				<table >
                     <tr valign="middle">
                           <td class="bsd_text" align="left">
                         <!--  <input type="text" name="searchParentCustomer"  id="searchParentCustomer"  value=" " ></td> --> 
                           <input type="text" name="parentCustomer" id="parentCustomer" maxlength="200" style="text-align:left" value=""></input>
                           <td class="buttons">
                                <a href="#" class="negative parentCustomerDot"  style="text-align='center';font-family='Verdana,Arial,sans-serif';font-size='12px';height='15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                            </td>					
                           					
                      </tr>
                  </table>
			</td>


            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Biller Name *&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                    <select id="biller_name" name="biller_name" style="text-align:left;margin-left:3px">
                        <%
                           Object [] objs=CanonCustomBillingManualDAO.getBillerNameLov();
                        String validationStatus=(String)CanonCustomBillingCommon.second(objs);
                            if("S".equals(validationStatus)){
                                List l=(List)CanonCustomBillingCommon.first(objs);
                                for(int i=0;i<l.size();i++){
                                    CanonCustomBillingManualDAO.BillerNameInfo bi=(CanonCustomBillingManualDAO.BillerNameInfo) l.get(i);
                                    String s__=CanonPCISecurityUtil.htmlEncode(CanonCustomBillingCommon.nonNullify(bi.getBillerName()));
                                    boolean selected=CanonCustomBillingCommon.isEmpty(billerName) ? userId==bi.getUserId().intValue(): billerName.equals(bi.getBillerName());
                                    %><option value="<%=s__%>" <%=selected? "selected":""%>  ><%=s__%></option> <%
                                }
                            }
                        %>
                    </select>
            </td>
            
<!--            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="biller_name" name="biller_name" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(billerName))%>" /> 
            </td>-->
        </tr>
        <tr>
            
            <td align="left" class="eventableDataCell" style="font-weight:bold;">
                Customer Group&nbsp;
            </td>
            <td cellspacing="0" cellpadding="0" cellspacing="2" >
				<table >
                     <tr valign="middle">
                           <td class="bsd_text" align="left">
                         <!--  <input type="text" name="searchCustomerProfile"  id="searchCustomerProfile"  value=" " ></td> --> 
                           <input type="text" name="customerGroupNm" id="customerGroupNm" maxlength="200" style="text-align:left" value=""></input>
<!--                       <td class="buttons">
                                <a href="#" class="negative customerProfileDot"  style="text-align='center';font-family='Verdana,Arial,sans-serif';font-size='12px';height='15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                           </td>
 -->                        <td class="buttons">
                                <a href="#" class="negative customerGroupDot"  style="text-align='center';font-family='Verdana,Arial,sans-serif';font-size='12px';height='15px';margin:0 0 0 0;padding:2px 2px 2px 2px;">...</a>
                            </td>					
                           					
                      </tr>
                  </table>
			</td>
            
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Biller Email&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="biller_email" name="biller_email" maxlength="200" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(billerEmail))%>" /> 
            </td>
            
        </tr>
        <tr>

            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Customer Email *&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="cust_email" name="cust_email" maxlength="200" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(custEmail))%>" /> 
            </td>

            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Bill Amount:&nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="bill_amount" name="bill_amount" maxlength="20" style="text-align:left;margin-left:3px" value="<%=C.nonNullify(CanonPCISecurityUtil.htmlEncode(billAmount))%>" /> 
            </td>
        </tr>

        <tr>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Original URN# &nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="text" id="original_urn" name="original_urn" style="text-align:left;margin-left:3px" />
            </td>

            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Special Bill File * &nbsp;
            </td>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                <input type="file" id="special_bill_file" name="special_bill_file" accept=".xls,.xlsx" style="text-align:left;margin-left:3px" />
            </td>
        </tr>
        <tr>
            <td cellpadding="2" align="left" class="eventableDataCell" style="font-weight:bold;">
                Comments
            </td>
            <td colspan="3">
                <textarea id="comments"  name="comments" maxlength="4000" style="text-align:left;margin-left:3px;width: 90%"  ></textarea>
            </td>
        </tr>
        

    </table>
    <table cellspacing="2" cellpadding="0" style="float:right;margin-right: 50;margin-bottom: 0">
        <tr>
            <td class="buttons">
                <a href="#"  id="manualBillUpload"  class="negative" style="text-align:'center';height:'25px';font-size:'11px';width:'100px';">Upload ></a>
            </td>
            <td class="buttons">
                <a href="#" id="clearAll" class="negative" style="text-align:'center';height:'25px';font-size:'11px';width:'100px';">Clear ></a>
            </td>
        </tr>
    </table>

    <table align="center" width="850" border="0" cellspacing="1" cellpadding="2" summary="">
        <tr><td>
                <div class="bsd_text_bold error_message" style="font-size:11px; color: blue"><%=C.nonNullify(errorMessage)%>
                </div>
            </td></tr>
    </table>

    <div height="5px">&nbsp;</div>
</form>
<%        
    }
%>                