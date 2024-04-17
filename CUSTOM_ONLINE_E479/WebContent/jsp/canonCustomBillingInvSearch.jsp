<%@page import="oracle.apps.custombilling.dao.CanonCustomBillingSearchingDAO"%>
<%@page import="oracle.apps.custombilling.util.CanonCustomBillingCommon"%>
<%@page import="oracle.apps.fnd.common.WebRequestUtil"%>
<%@page language="java" import="oracle.apps.jtf.calendar.util.CalendarUtil" %>
<%@page import="oracle.apps.jtf.base.Logger" %>
<%@page import="oracle.apps.jtf.util.GeneralPreference" %>
<%@page import="oracle.apps.jtf.base.resources.AOLMessageManager" %>
<%@page language="java" import="java.math.*" %>
<%@page import="canon.apps.pci.util.CanonSecurityUtil"%>
<%@page import="oracle.apps.fnd.common.WebRequestUtil"%>

<jsp:include page="canonCustomBillingUIInclude.jsp"/>

<%
    CanonCustomBillingCommon C = new CanonCustomBillingCommon();
    String myRegionName = "";//"JTF_NOTES_CREATE";
    String myPermissionName = "";//"JTF_NOTES_CREATE";
    oracle.apps.fnd.common.WebAppsContext objWebAppsContext =WebRequestUtil.validateContext(request, response);

    int userId = objWebAppsContext.getUserId();
    String userName = objWebAppsContext.getUserName();
    System.out.println("canonCustomBillingInvSearch: userName: "+userName);
	int resp_Id = objWebAppsContext.getRespId();
    String PAGE_NAME="canonCustomBillingInvSearch.jsp";
	boolean isJspAccessValid=  CanonSecurityUtil.isJSPAccessValid(PAGE_NAME,userId,resp_Id);
	if(!isJspAccessValid){
	%>
   <jsp:forward page="canonInvalidAccess.jsp"></jsp:forward>
	<%	
	}
%>


<HTML>

    <HEAD>

        <TITLE>Custom Billing Invoice Search</TITLE>
        <style type="text/css">
            .eventableDataCell { 
                text-align: left; 
                font-size: 11px; 
                color: #000000; 
            }   
            #page_content 
            {
                padding: 20px 10px 10px 10px;
            }

        </style>

        <script type='text/javascript'>
            
            $.ajaxSetup({ cache: false });
         
            $( function() {
                
                function openInvApprovalDialog(mName, options){
                    var urlDetail = 'canonCustomBillingInvoiceApproval.jsp?modalName='+mName;
                    if(options){
                        urlDetail=urlDetail+"&"+jQuery.param(options);
                    }
                    modelName = "#"+mName;
                    var action;
                    showPleaseWait();		
                    $(modelName).dialog({
                        height: 410,
                        title: "Invoice Approval",
                        modal: true ,
                        autoOpen :false,
                        width: 550,		
                        resizable: false,      
                        buttons: { 
                            "Close": function() {  
                                $(modelName).html("");
                                $(this).dialog("close");
                                $(this).dialog("destroy");
                                if(action){
                                    $(modelName).trigger("closed");
                                }
                            },
                            "Reject": function() {action="Reject";},
                            "Approve": function(){action="Approve";}
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
                    $(modelName).dialog("open");
                }

                function openInvEmailDialog(mName, options){
                    var urlDetail = 'canonCustomBillingInvoiceEmail.jsp?modalName='+mName;
                    if(options){
                        urlDetail=urlDetail+"&"+jQuery.param(options);
                    }
                    modelName = "#"+mName;
                    var action;
                    showPleaseWait();		
                    $(modelName).dialog({
                        height: 310,
                        title: "Email Invoice To Customer",
                        modal: true ,
                        autoOpen :false,
                        width: 550,		
                        resizable: false,      
                        buttons: { 
                            "Close": function() {  
                                $(modelName).html("");
                                $(this).dialog("close");
                                $(this).dialog("destroy");
                                if(action){
                                    $(modelName).trigger("closed");
                                }
                            },
                            "Send": function() {action="Send";}
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
                    $(modelName).dialog("open");
                }
                

                $("#invoiceApprovalDiv").bind("closed", function(event,data){
                    invSearch('invSearchTopDiv', 1, 'invSearchForm');
                });

                $("#billToSiteLovDiv").bind("selected", function(event,data){
                    console.log(data);
                    $("#searchLocation").val(data.location);
                });

                $(".billToSiteDot").live('click',function(){
                    openBillToSiteSearch('billToSiteLovDiv');
                });
        
                $(".downloadInvoice").live('click',function(){
                    var tr=$(this).closest("tr");
                    console.log(tr.data("invoicePath"));
            
                    window.open('canonCustomBillingInvoiceDownload.jsp?path='+tr.data("invoicePath")+"&invoiceId="+tr.data("invoiceId")+"&urnNumber="+tr.data("urnNumber")+"&invFileName="+encodeURIComponent(tr.data("invFileName")));
                });

                $(".approval").live('click',function(){
                    var tr=$(this).closest("tr");
                    openInvApprovalDialog('invoiceApprovalDiv',{invoiceId:tr.data("invoiceId"),userId:"<%=userId%>"});
                });
                
                $(".email").live('click',function(){
                    var tr=$(this).closest("tr");
                    openInvEmailDialog('invoiceEmailDiv',{invoiceId:tr.data("invoiceId"),userId:"<%=userId%>"});
                });
        
            });            
                      
        </script>


    </HEAD>
    <body >
        <%@ include file="jtfcalincps.jsp" %> 
        <%@ include file="canonCustomBillingTop2.jsp" %>  
        <div id="page_content" class="T2 clearfix" >
            <div id = "invSearchTopDiv">
                <jsp:include page="canonCustomBillingInvSearchInclude.jsp">
                    <jsp:param name="userId" value="<%=userId%>" />
                    <jsp:param name="userName" value="<%=userName%>" />
                </jsp:include>
            </div>
            <div id="billToSiteLovDiv" /></div>
        <div id="invoiceApprovalDiv" /></div>
        <div id="invoiceEmailDiv" /></div>

    <%@ include file="canonCBSAppBottom.jsp"%> 
    <%@ include file="jtfcalincpe.jsp" %>
</body>
</html>
