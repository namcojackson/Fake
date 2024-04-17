<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchDAO"%>
<%!    
	final static int ALL_ROWS = Integer.MAX_VALUE;
	final static String ITT_NUM_PLACE_HOLDER="ITT";
	final static String ORDER_NUM_PLACE_HOLDER="Order";
	final static String SHIP_TO_CUSTOMER_NAME_PLACE_HOLDER="SHIP TO CUSTOMER NAME";
	final static String SHIP_TO_ACCOUNT_NUM_PLACE_HOLDER="SHIP TO ACCOUNT";
	final static String CUSA_PO_NUM_PLACE_HOLDER="CUSA PO";
	final static String DEALER_NAME_PLACE_HOLDER="DEALER NAME";
	final static String DELIVER_DATE_FROM_PLACE_HOLDER="Delivery Date From";
	final static String DELIVER_DATE_TO_PLACE_HOLDER="Delivery Date To";
%>     
<%
      
    CanonE580ITTWorkbenchDAO dao = new CanonE580ITTWorkbenchDAO();

    String action = request.getParameter("action");
    boolean exportExcel="export-excel".equals(action);
    String p_itt_number = ITT_NUM_PLACE_HOLDER.equalsIgnoreCase(request.getParameter("itt_number"))?"":request.getParameter("itt_number");
    String p_so_number = ORDER_NUM_PLACE_HOLDER.equalsIgnoreCase(request.getParameter("order_number"))?"":request.getParameter("order_number");
    String p_customer_name = SHIP_TO_CUSTOMER_NAME_PLACE_HOLDER.equalsIgnoreCase(request.getParameter("ship_to_customer_name"))?"":request.getParameter("ship_to_customer_name");
    String p_ship_to_account = SHIP_TO_ACCOUNT_NUM_PLACE_HOLDER.equalsIgnoreCase(request.getParameter("ship_to_account_number"))?"":request.getParameter("ship_to_account_number");
    String p_itt_status = request.getParameter("status");
    String p_sales_rep = "Sales Rep".equalsIgnoreCase(request.getParameter("sales_rep"))?"":request.getParameter("sales_rep");
    String p_sales_zone = request.getParameter("sales_zone");
    String p_sales_branch = "Sales Branch".equalsIgnoreCase(request.getParameter("sales_branch"))?"":request.getParameter("sales_branch");
    String p_cusa_po_number = CUSA_PO_NUM_PLACE_HOLDER.equalsIgnoreCase(request.getParameter("csa_po_number"))?"":request.getParameter("csa_po_number");
    String p_order_by = request.getParameter("order_by");
    String p_asc_desc = request.getParameter("asc_desc");
    String p_itt_order_processor = "ITT Order Processor".equalsIgnoreCase(request.getParameter("itt_order_processor"))?"":request.getParameter("itt_order_processor");
    String p_itt_out_in = request.getParameter("itt_out_in");
    String p_include_closed =CanonE580ITTWorkbenchUtil.toYesNo(request.getParameter("include_closed"));

    String p_rows_per_page = request.getParameter("rowsPerPage");
    int rowsPerPage = p_rows_per_page == null ? 10 : CanonE580ITTWorkbenchUtil.toInt(p_rows_per_page);
    int page_no = CanonE580ITTWorkbenchUtil.isEmpty(request.getParameter("page_no")) ? -1 : CanonE580ITTWorkbenchUtil.toInt(request.getParameter("page_no"));
    BigDecimal p_from_record = page_no == -1 ? new BigDecimal(1) : new BigDecimal((page_no - 1) * rowsPerPage + 1);
    BigDecimal p_to_record = p_from_record == null ? null : p_from_record.add(new BigDecimal(rowsPerPage - 1));
    String p_dealer_name=DEALER_NAME_PLACE_HOLDER.equalsIgnoreCase(request.getParameter("dealer_name"))?"":request.getParameter("dealer_name");
    System.out.println("request.getParameter(delivery_date_from): "+request.getParameter("delivery_date_from"));
    System.out.println("request.getParameter(delivery_date_to): "+request.getParameter("delivery_date_to"));
    Timestamp p_delivery_date_from=	DELIVER_DATE_FROM_PLACE_HOLDER.equalsIgnoreCase(request.getParameter("delivery_date_from"))?CanonE580ITTWorkbenchUtil.toTimestamp2(""):CanonE580ITTWorkbenchUtil.toTimestamp2(request.getParameter("delivery_date_from")); 
    Timestamp p_delivery_date_to=DELIVER_DATE_TO_PLACE_HOLDER.equalsIgnoreCase(request.getParameter("delivery_date_to"))?CanonE580ITTWorkbenchUtil.toTimestamp2(""):CanonE580ITTWorkbenchUtil.toTimestamp2(request.getParameter("delivery_date_to")); 
    
    System.out.println("Prior to call ittHeaderDetails:"+p_itt_number+","+p_so_number+","+p_customer_name+","+p_ship_to_account+","+p_itt_status+","+p_itt_order_processor+","+p_sales_rep+","+p_sales_zone+","+p_sales_branch+","+p_cusa_po_number+","+p_itt_out_in+","+p_dealer_name+","+p_delivery_date_from+","+p_delivery_date_to+","+p_include_closed+","+p_from_record+","+p_to_record+","+p_order_by+","+p_asc_desc);
    Object[] result = dao.ittHeaderDetails(p_itt_number,
            p_so_number,
            p_customer_name,
            p_ship_to_account,
            p_itt_status,
            p_itt_order_processor,
            p_sales_rep,
            p_sales_zone,
            p_sales_branch,
            p_cusa_po_number,
            p_itt_out_in,
            p_dealer_name,
            p_delivery_date_from,
            p_delivery_date_to,
            p_include_closed,
            exportExcel?null:p_from_record,
            exportExcel?null:p_to_record,
            p_order_by,
            p_asc_desc);
    List list = CanonE580ITTWorkbenchUtil.first(result)!=null? (List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;
    System.out.println("ittheader Deatils list size:"+list.size());
    
if(exportExcel){
    pageContext.setAttribute("export-excel-summary", list,PageContext.REQUEST_SCOPE);
    pageContext.forward("canonE580ExcelDownload.jsp");
}else{
    
    int total_record = CanonE580ITTWorkbenchUtil.second(result)==null? 0: ((BigDecimal) CanonE580ITTWorkbenchUtil.second(result)).intValue();

    result = dao.ittStatusLov();
    List statusList = CanonE580ITTWorkbenchUtil.first(result)!=null? (List) CanonE580ITTWorkbenchUtil.first(result) :Collections.EMPTY_LIST;
    result = dao.ittLineTypes();
    List lineTypeList =CanonE580ITTWorkbenchUtil.first(result)!=null?  (List) CanonE580ITTWorkbenchUtil.first(result) :Collections.EMPTY_LIST;
    result = dao.ittSalesZoneLov();
    List salesZoneList =CanonE580ITTWorkbenchUtil.first(result)!=null?  (List) CanonE580ITTWorkbenchUtil.first(result) :Collections.EMPTY_LIST;
    
    
%>    

<style>
    #report_tbl_first a {
        color: white;
    }
    .select-container {
        overflow: hidden
    }
    .select-container select {
        border-left-width: 0px;
        border-right-width: 0px;
    }

    input{
        width:80%;
    }
    .select-container{
        width:80%;
    }
    .select-container select{
        width:100%;
    }
       
</style>

<script>

    $(document).ready(function()
    {
    $('input[type="text"]').each(function (){
	 $(this).addClass("inTxt");	  
});
    
   $("#searchForm input[type=text]").each(function() {
    			    var input = $(this);
    			    if (input.val() == input.attr('placeholder')) {
    			      input.val('');
    			    }
    			 });
    			 
         $('[placeholder]').focus(function() {
    		  var input = $(this);    		  
    		  if (input.val().toLowerCase() == input.attr('placeholder').toLowerCase()) {
    		    input.val('');
    		    input.removeClass('placeholder');
    		  }
    		}).blur(function() {
    		  var input = $(this);
    		  if (input.val() == '' || input.val().toLowerCase() == input.attr('placeholder').toLowerCase()) {
    		    input.addClass('placeholder');
    		    input.val(input.attr('placeholder'));
    		  }
    		}).blur(); 
         
   
 //   }
        
        
        function openShipToCustomerLOV(mName, options)
        {
            openITTLOV(mName,"Ship To Customer LOV",'canonE580ShipToCustomerLOV.jsp',450,720, options);
        }

        function openSalesRepLOV(mName, options)
        {
            openITTLOV(mName,"Sales Rep LOV",'canonE580SalesRepLOV.jsp',450,720, options);
        }

        function openSalesBranchLOV(mName, options)
        {
            openITTLOV(mName,"Sales Branch LOV",'canonE580SalesBranchLOV.jsp',450,720, options);
        }

        function openLineDetail(mName, options)
        {
            var url = 'canonE580LineDetail.jsp';
            url = url + "?modalName=" + mName;
            if (options) {
                url = url + "&" + jQuery.param(options);
            }

            blockUsrInterface();
            $.post(url, null, function(data) {
                $("#divCanonE580Main").html("");
                $("#divCanonE580Main").html(data);
                unBlockUsrInterface();
            });
        }

        function clearSearch(ele)
        {
            $("#search_result_div").html("");
            $(ele).find(':input').each(function()
            {
                switch (this.type)
                {
                    case 'password':
                    case 'select-multiple':
                    case 'text':
                    case 'textarea':
                        $(this).val('');

                        break;
                    case 'checkbox':
                    case 'radio':
                        this.checked = false;
                }
            });
            $("select").val("");
            $("#rowsPerPage").val("10");
            $("#page_no,#order_by,#asc_desc").remove();
            setFocus('itt_number');
        }


        function updateTips(t) {
            var html=
                '<div title="Alert">'
                    +'<p>'
                    +    '<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 50px 0;"></span>'
                    + t 
                    + '</p>'+
                '</div>';
            $( html ).dialog({
              modal: true,
              height: "auto !important",
              buttons: {
                Ok: function() {
                  $( this ).dialog( "close" );
                }
              }
            });                
        }

        function checkIfTrue( n, valid) {
          if (!valid) {
            updateTips(n);
            return false;
          } else {
            return true;
          }
        }

        function checkDateTimeAfter( o, n, after, after_name) {
            var afterDateTime=new Date(after);
            var d=new Date(o.val());
            if (d.getTime()<afterDateTime.getTime()){
              updateTips( n + " must be after "+after_name+".");
              return false;
            } else {
              return true;
            }
        }

        function empty(str){
            return !str || !/[^\s]+/.test(str);
        }

        function search()
        {
            var bValid = true;
            bValid = bValid && ( (empty($("#delivery_date_from").val()) && empty($("#delivery_date_to").val())) 
                    || checkDateTimeAfter($("#delivery_date_to"), "Delivery Date To", $("#delivery_date_from").val(),"Delivery Date From"));
            bValid = bValid && checkIfTrue("Please specify both Delivery Date From and Delivery Date To or leave both field empty.",
                    (empty($("#delivery_date_from").val()) && empty($("#delivery_date_to").val()))
                    ||(!empty($("#delivery_date_from").val()) && !empty($("#delivery_date_to").val()))
                    );
                
            if ( bValid ) {
                $("#action").val("search");
                var url = $("#searchForm").attr('action');
                blockUsrInterface();
                $.post(url, $("#searchForm").serialize(), function(data) {
                    $("#divCanonE580Main").html("");
                    $("#divCanonE580Main").html(data);
                    unBlockUsrInterface();
                });
            }
        }
        
        function sort(sortBy) {
            var existingSortOrder = $("#asc_desc").val();
            var existingSortBy = $("#order_by").val();

            if (sortBy == existingSortBy) {
                if (existingSortOrder == 'asc') {
                    existingSortOrder = 'desc';
                } else {
                    existingSortOrder = 'asc'
                }
                $("#asc_desc").val(existingSortOrder);
            } else {
                existingSortOrder = 'asc';
                $("#asc_desc").val(existingSortOrder);
                $("#order_by").val(sortBy);
            }

            search();

        }

        $('.search').click(function()
        {
            search();
        });

        $('#download_button').click(function()
        {
            $("#action").val("export-excel");
            $("#searchForm").submit();
        });
        
        $('.clear_search').click(function()
        {
            clearSearch($("#searchForm")[0]);
        });

        $('#ship_to_customer_lov_icon').click(function()
        {
       		 var ship_to_customer_name= $("#ship_to_customer_name").val();
      		 if("<%=SHIP_TO_CUSTOMER_NAME_PLACE_HOLDER%>"==ship_to_customer_name)
      			 ship_to_customer_name="";
            openShipToCustomerLOV("ShipToCustomerLOV-DataDiv", {"cust_name": ship_to_customer_name});
        });

        $('#itt_order_processor_lov_icon').click(function()
        {
        	var itt_order_processor= $("#itt_order_processor").val();
      		 if("ITT Order Processor"==itt_order_processor)
      			 itt_order_processor="";
      			 
            openITTOrderProcessorLOV("ITTOrderProcessorLOV-DataDiv", {"processor_name": itt_order_processor});
        });
        
        $('#ship_to_account_number_lov_icon').click(function()
        {
       		 var ship_to_account_number= $("#ship_to_account_number").val();
      		 if("<%=SHIP_TO_ACCOUNT_NUM_PLACE_HOLDER%>"==ship_to_account_number)
      			 ship_to_account_number="";
            openShipToCustomerLOV("ShipToCustomerLOV-DataDiv", {"cust_number": ship_to_account_number});
        });

        $('#sales_rep_lov_icon').click(function()
        {
         	var sales_rep= $("#sales_rep").val();
      		 if("Sales Rep"==sales_rep)
      			 sales_rep="";
         openSalesRepLOV("SalesRepLOV-DataDiv", {"salesrep_name": sales_rep});
        });

        $('#sales_branch_lov_icon').click(function()
        {
        var sales_branch= $("#sales_branch").val();
      		 if("Sales Branch"==sales_branch)
      			 sales_branch="";
            openSalesBranchLOV("SalesBranchLOV-DataDiv", {"sales_branch": sales_branch});
        });
        
        $('#dealer_lov_icon').click(function()
        {
       var dealer_name= $("#dealer_name").val();
       if("<%=DEALER_NAME_PLACE_HOLDER%>"==dealer_name)
       dealer_name="";
            openDealerLOV("DealerNameLOV-DataDiv",{"dealer_name": dealer_name });
        });
        

        $('.line_detail_selection').click(function()
        {
            var ittNumber=$(this).data("itt_number");
            var url = "/s21extn/e580/jsp/canonE580LineDetail.jsp?itt_number="+ittNumber;
            var lineDetailWin = window.open(url,"LineDetailWindow","height=600px,width=1280px,status=yes,scrollbars=yes,resizable=yes");
            lineDetailWin.focus();
        });

        $("a.linkPage").click(function(event) {
            var pageNo = $(this).data("page");
            $("#page_no").val(pageNo);
            search();
            event.preventDefault();
        });

        $('.therefore_link').click(function()
        {
            var ittNumber=$(this).data("itt_number");
            console.log("ittNumber is "+ittNumber);
            openMarkviewLOV("MarkviewLOV-DataDiv",{"itt_number":ittNumber});
        });
        
        $('.sort_by_button').click(function(){
            var sortBy=$(this).data("sort-by");
            sort(sortBy);
            event.preventDefault();
        });
        
        var defaulutbutton=$("#searchForm").attr("defaultbutton");
        $("input").bind("keydown", function(event) {
            // track enter key
            var keycode = (event.keyCode ? event.keyCode : (event.which ? event.which : event.charCode));
            if (keycode == 13) { // keycode for enter key
               // force the 'Enter Key' to implicitly click the default button
               $("#"+defaulutbutton).click();
               return false;
            } else  {
               return true;
            }
         }); // end of function
         
        
          $(".date-picker").datepicker({
            		 dateFormat: 'mm/dd/yy',
            		 changeMonth: true,
            		 changeYear: true
            	 });
         
    });



</script>
<form name="searchForm" id="searchForm" defaultbutton="search" method="post" action="canonE580ITTWorkbenchSearchInclude.jsp">
    <input type="hidden" name="action"  id="action"/>
    <input type="hidden" name="page_no" id="page_no"/>
    <input type='hidden' name='order_by' id='order_by' value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_order_by)%>">
    <input type='hidden' name='asc_desc' id='asc_desc' value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_asc_desc)%>">
    <input type='hidden' name='rowsPerPage' id='rowsPerPage' value="<%=rowsPerPage%>">
    <!-- Search -->

	<table id="ittTbl" class="service" style='align: center; width: 1200px;border: none;float:right'>
		<tr>
			<td  valign="top" style="border: 1px #cccccc solid;">
			<table  id="srchittTbl" style="margin:0px;width:100%;border:none">
						<tr>
							<th colspan="3" class="hd"><%=ITT_NUM_PLACE_HOLDER%></th>						
						</tr>			
						<tr class="trEmpty"><td colspan=3>&nbsp;</td></tr>
						<tr align="center">							
							<td nowrap  width="33%"><input type="text" placeholder="<%=ITT_NUM_PLACE_HOLDER%>" name="itt_number" id="itt_number"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_number)%>"></td>
							<td  width="33%" ><input type="text" placeholder="<%=ORDER_NUM_PLACE_HOLDER%>" name="order_number" id="order_number"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_so_number)%>">
							</td>
							<td  width="33%">  
                		  <select name="status" id="status" style="width: 155px;margin-left:5px;">
                        	<option value="">Status</option>
		                        <% for (int i = 0; i < statusList.size(); i++) {
		                                String status_lov = (String) statusList.get(i);
		                        %>
                        	<option value="<%=status_lov%>"  <%=status_lov.equals(p_itt_status) ? "SELECTED" : ""%> > <%=status_lov%> </option>
                        	<%}%>
                     	 </select>		                          
               		    </td>
						</tr>
						<tr align="center">
						 <td nowrap  width="33%">
						  <input type="text" placeholder="Sales Rep"  id="sales_rep" name ="sales_rep" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_sales_rep)%>"  onkeypress=""  onKeyUp ="">
                            <img class="lov_icon" height="16" src="/s21extn/common/images/download.png" id="sales_rep_lov_icon">
						 </td>
						 <td  width="33%">  
               			<select name="sales_zone" id="sales_zone" style="width: 155px;margin-left:5px;">
                        	<option value="">Sales Zone</option>
	                        <%  for (int i = 0; i < salesZoneList.size(); i++) {
	                                String sales_zone_lov = (String) salesZoneList.get(i);
	                        %>
	                        <option value="<%=sales_zone_lov%>"  <%=sales_zone_lov.equalsIgnoreCase(p_sales_zone) ? "SELECTED" : ""%> > <%=sales_zone_lov%> </option>
	                        <%}%>
                    	</select>		                          
               		   </td>
		                 <td>  
		             		<input type="text" class="inTxt placeholder" placeholder="Sales Branch" id="sales_branch" name ="sales_branch" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_sales_branch)%>"  onkeypress=""  onKeyUp ="">
		             		<img  class="lov_icon" height="16" src="/s21extn/common/images/download.png" id="sales_branch_lov_icon">
		                         
		                </td>
						</tr>
						<tr align="center">
						<td  width="33%">               
             			  <input type="text" placeholder="ITT Order Processor" id="itt_order_processor" name ="itt_order_processor" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_order_processor)%>"  onkeypress=""  onKeyUp ="">
                        	<img class="lov_icon" id="itt_order_processor_lov_icon" src="/s21extn/common/images/download.png" alt="" height="16">                                                             
                		</td>
                		<td  width="33%"> 
                			<input type="text" placeholder="<%=CUSA_PO_NUM_PLACE_HOLDER%>" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="csa_po_number" name ="csa_po_number" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_cusa_po_number)%>"  onkeypress=""  onKeyUp ="">
                		</td>  
                		<td>  
               			 <select name="itt_out_in" id="itt_out_in" style="width: 155px;margin-left:5px;">
                          <option value="">Line Type</option>
	                        <% for (int i = 0; i < lineTypeList.size(); i++) {
	                                String line_type_lov = (String) lineTypeList.get(i);
	                        %>
	                        <option value="<%=line_type_lov%>"  <%=line_type_lov.equalsIgnoreCase(p_itt_out_in) ? "SELECTED" : ""%> > <%=line_type_lov%> </option>
	                        <%}%>
                         </select>	                          
                	    </td> 
					  </tr>
					  <tr align="center">
					  <td  width="33%">
                		<input class="date-picker"  placeholder="<%=DELIVER_DATE_FROM_PLACE_HOLDER%>" type="text" id="delivery_date_from" name ="delivery_date_from" onkeypress=""  onKeyUp ="" value = "<%=CanonE580ITTWorkbenchUtil.formatDate2(p_delivery_date_from)%>" >            
                	  </td>
                	  <td  width="33%">  
                		<input class="date-picker" type="text"  placeholder="<%=DELIVER_DATE_TO_PLACE_HOLDER %>" id="delivery_date_to" name ="delivery_date_to" onkeypress=""  onKeyUp =""  value = "<%=CanonE580ITTWorkbenchUtil.formatDate2(p_delivery_date_to)%>">          
                	  </td>
                	  <td> 
                  		&nbsp;Include Closed ITT Orders<input type="checkbox" id="include_closed" name ="include_closed" value = "Y" <%="Y".equals(p_include_closed) ? "checked":""%> style="border-style:none">
                	  </td>  
					  </tr>		
		  			  <TR><TD style="TEXT-ALIGN: right; PADDING-BOTTOM: 10px" colSpan=3><A style="MARGIN: 0px" class="btn search">Search</A> <A class="btn clear_search">Clear</A> </TD></TR>
					  			  
					</table>
			</td>
			<td valign="top" style="border: 1px #cccccc solid;">
			<table id="custTbl" class="service" style='align:center;margin:0%;width:100%;border:none'>
					 <tr>
						<th class="hd" >Customer</th>
					 </tr>

					 <tr class="trEmpty"><td>&nbsp;</td></tr>
					 <tr>
						 <td> 
	                		<input type="text" placeholder="<%=SHIP_TO_CUSTOMER_NAME_PLACE_HOLDER%>" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="ship_to_customer_name" name ="ship_to_customer_name" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_customer_name)%>"  onkeypress=""  onKeyUp =""><img height="16" src="/s21extn/common/images/download.png" id="ship_to_customer_lov_icon">                        
	                	 </td>   	                	                 
					 </tr>
					 <tr>
						  <td>  
	             				<input type="text"  placeholder="<%=SHIP_TO_ACCOUNT_NUM_PLACE_HOLDER%>" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="ship_to_account_number" name ="ship_to_account_number" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_ship_to_account)%>"  onkeypress=""  onKeyUp =""><img class="lov_icon"  id="ship_to_account_number_lov_icon" src="/s21extn/common/images/download.png" alt="" height="16">          
	                	  </td>
					 </tr>
					 <tr>
					  	<th class="hd" >Dealer</th>
					 </tr>
					 <tr class="trEmpty"><td>&nbsp;</td></tr>
					 <tr>
					 <td> 
		                <input type="text" placeholder="<%=DEALER_NAME_PLACE_HOLDER%>" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="dealer_name" name ="dealer_name" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_name)%>"  onkeypress=""  onKeyUp ="">
		                <img height="16" src="/s21extn/common/images/download.png" id="dealer_lov_icon">
		                </td>  						  	                	                
					 </tr>					 
		  			  <TR><TD style="TEXT-ALIGN: right; PADDING-BOTTOM: 10px" colSpan=3><A style="MARGIN: 0px" class="btn search">Search</A> <A class="btn clear_search">Clear</A> </TD></TR>
					 
					 </table>
			</td>
		</tr>
	</table>
	<div style="clear: both;"></div>
					<br>
					
					

    <% if(action!=null){ %>    
        <% 
        int totalLks=0;
        	if (list.size() > 0 && rowsPerPage != ALL_ROWS) {
                int totalPages = (total_record - 1) / rowsPerPage + 1;
                int currentPage = (p_from_record.intValue() - 1) / rowsPerPage + 1;
        %>
		<div id="search_result_div" class="table-inner">
         <div style="float:right;margin-right:10px;">Showing <%=CanonE580ITTWorkbenchUtil.genPaginationSummary(total_record, rowsPerPage, currentPage)%> found</div>
        <div style="float:left; margin-bottom:5">
            <%=CanonE580ITTWorkbenchUtil.genPageLinks(null, null, null, totalPages, rowsPerPage, currentPage, total_record)%>
        </div>
        <% } else {%>
        <legend>Results<%=list.size() > 0?  " of "+ Integer.toString(total_record) :"" %></legend>
        <% }%>
				
		<table cellspacing="1" class="supplies-table">        
            <tr> 	
                <th  colspan="2"><a  href="#" data-sort-by='ITT_NUMBER' ><%=ITT_NUM_PLACE_HOLDER%>(select)</a></th>
                <th >ITT Status</th>
                <th ><a href="#" data-sort-by='PARTY_NAME' >Ship To Cust</a></th>
                <th ><a href="#" data-sort-by='DEALER' >Dealer Name</a></th>
                <th ><a href="#" data-sort-by='DEALER_WHSE_CODE' >Dealer Code</a></th>
                <th >ITT Order Processor</th>
                <th ><%=ORDER_NUM_PLACE_HOLDER%></th>
                <th >Last Update Date</th>
                <th >Ship To Address</th>
                <th >City,State</th>
                <th ><a href="#" data-sort-by='POSTAL_CODE' >Postal</a></th>
                <th >CSA Sales Branch</th>
                <th >Salesrep</th>
            </tr>

            <%
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        CanonE580ITTWorkbenchDAO.HeaderDetail header = (CanonE580ITTWorkbenchDAO.HeaderDetail) list.get(i);
                        String itt_numner = CanonE580ITTWorkbenchUtil.nonNullify(header.getIttNumber());

            %>

            <tr class="<%=i%2==0? "evenRow":"oddRow" %>">
                <td align="center" NOWRAP  style="color:#000000;">                
                <a href="#" class="line_detail_selection" data-itt_number="<%=itt_numner%>"><%=itt_numner%></a>
                </td>
                <td align="center"><a href="#" class="therefore_link" data-itt_number="<%=itt_numner%>" >IMAGE</a> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getIttStatus())%> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getPartyName())%> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getDealer())%> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getDealerWhseCode())%> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getIttAdminOwner())%> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getOrderNumber())%> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.formatDate3(header.getLastUpdateDate())%></td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getCustShipToAddress())%> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getCity())%>,<%=CanonE580ITTWorkbenchUtil.nonNullify(header.getState())%></td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getPostalCode())%> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getCsaSalesBranch())%> </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(header.getName())%> </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td class="search_res" color = "#FF0000" colspan="14" align="center"> No records found. Please adjust your search criteria.&nbsp;</td>
            </tr>
            <%                }
            %>
        </table>
        <br>
        <% if (list.size() > 0) { %>
        <div style="float:right">
            <a href="#" id="download_button" class="btn" >Export Excel</a>
        </div>
        <% } %>
        </form>

    <% }%>
    
    
<%
}
%>