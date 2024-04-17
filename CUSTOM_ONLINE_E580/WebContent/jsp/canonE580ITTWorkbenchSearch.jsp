<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@page import="com.canon.cusa.s21.framework.security.S21PermissionType"%>
<%@page import="java.util.List"%>


<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%-- <%@page import="oracle.apps.e580.itt.workbench.CanonE580ReceivePOApi"%> --%>

<%!final String PAGE_NAME = "canonE580ITTWorkbenchSearch.jsp";

 
public static void checkApiWorking(HttpServletRequest req){
	
}%>
    
 <%--  <%
  CanonE580ReceivePOApi canonE580ReceivePOApiTest=new CanonE580ReceivePOApi();
  	request.setAttribute("rcvline_number", " po_line_num");
	request.setAttribute("rcvpo_number", "po_number");
	request.setAttribute("rcvitem_qty", "receive_po_qty.toString()");
	request.setAttribute("rcvitem_code", "item_name");
	request.setAttribute("rcveta_dt", "etaDt");
	request.setAttribute("rcvstk_sts_cd", "stkStscd");
	request.setAttribute("so_line_number", "line_number");
	request.setAttribute("rcvserial_number", "receive_po_serial_number");
	String[] resArr= canonE580ReceivePOApiTest.createServicerequest(request);
  %> --%>

<html>
    <head>
        <TITLE>Canon ITT Workbench</TITLE>
            <%@include file="canonE580ITTWorkbenchHead.jsp"%>           

        

    </head>
<div class="divHeader logo-img">
	<div id="TopDiv">
            <%@include file="canonE580ITTWorkbenchTop.jsp"%>
        </div>
	
	</div>
    <body>
	<%
	
		
	%>
	
	<div id ="main_content">	
   		<div id="page_top">
			<h1>Inter-Territorial Workbench</h1>
		</div>
        

        <div id = "divCanonE580Main">
            <jsp:include page="canonE580ITTWorkbenchSearchInclude.jsp"/>
        </div>

        <div id="DataDiv"></div>
        <div id="ShipToCustomerLOV-DataDiv"></div>
        <div id="ITTOrderProcessorLOV-DataDiv"></div>
        <div id="SalesRepLOV-DataDiv"></div>
        <div id="SalesBranchLOV-DataDiv"></div>
        <div id="MarkviewLOV-DataDiv"></div>
        <div id="DealerNameLOV-DataDiv"></div>  
        </div>      
    </body>
    <script language="javascript">

            $(function() {              	
                function resetSelectTitles(){
                    $('select').attr('title', function(i, title) {
                        return this.value;
                    });                
                    $('select option').attr('title', function(i, title) {
                        return this.text;
                    });                
                }
                
                setFocus('itt_number');
                $("#ShipToCustomerLOV-DataDiv").bind("selected", function(event, object) {
                    $("#ShipToCustomerLOV-DataDiv").html("");
                    $("#ShipToCustomerLOV-DataDiv").dialog("close");
                    $("#ShipToCustomerLOV-DataDiv").dialog("destroy");
                    $("#ship_to_customer_name").val(object.customer_name);
                    $("#ship_to_account_number").val(object.account_number);
                });

                $("#ITTOrderProcessorLOV-DataDiv").bind("selected", function(event, object) {
                    $("#ITTOrderProcessorLOV-DataDiv").html("");
                    $("#ITTOrderProcessorLOV-DataDiv").dialog("close");
                    $("#ITTOrderProcessorLOV-DataDiv").dialog("destroy");
                    $("#itt_order_processor").val(object.source_name);
                });

                $("#SalesRepLOV-DataDiv").bind("selected", function(event, object) {
                    $("#SalesRepLOV-DataDiv").html("");
                    $("#SalesRepLOV-DataDiv").dialog("close");
                    $("#SalesRepLOV-DataDiv").dialog("destroy");
                    $("#sales_rep").val(object.salesrep);
                });

                $("#SalesBranchLOV-DataDiv").bind("selected", function(event, object) {
                    $("#SalesBranchLOV-DataDiv").html("");
                    $("#SalesBranchLOV-DataDiv").dialog("close");
                    $("#SalesBranchLOV-DataDiv").dialog("destroy");
                    $("#sales_branch").val(object.sales_branch);
                });
                
                $("#DealerNameLOV-DataDiv").bind("selected", function(event, object) {
                    $("#DealerNameLOV-DataDiv").html("");
                    $("#DealerNameLOV-DataDiv").dialog("close");
                    $("#DealerNameLOV-DataDiv").dialog("destroy");
                    $("#dealer_name").val(object.dealer_name);
                });

                
               /*  $(".select-container select").live('mousedown', function() {
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

                }).live('blur change', function() {
                    $(this).css("width", $(this).data("origWidth"));
                    this.dontWiden = false;
                })

                $("select").live('change',function(){
                    this.title=this.value;
                    $('option',this).attr('title', function(i, title) {
                        return this.text;
                    });                
                }); */
                
                resetSelectTitles();
                
            });

        </script>
</html>
