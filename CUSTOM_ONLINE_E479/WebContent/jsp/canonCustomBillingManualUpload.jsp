<%@page import="oracle.apps.custombilling.util.CanonCustomBillingManualUtil"%>
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
<%-- <%@page import="oracle.apps.fnd.common.URLTools"%> --%>

<%!
   static String CANON_SIG="===CANON===";
%>

<jsp:include page="canonCustomBillingUIInclude.jsp"/>

<%
    String myRegionName = "";//"JTF_NOTES_CREATE";
    String myPermissionName = "";//"JTF_NOTES_CREATE";
    oracle.apps.fnd.common.WebAppsContext objWebAppsContext = WebRequestUtil.validateContext(request, response);
    int userId = objWebAppsContext.getUserId();
    /* boolean isBiller = "Y".equals((String) CanonCustomBillingCommon.first(CanonCustomBillingSearchingDAO.isUserBiller(new BigDecimal(userId)))); */
%>

<HTML>

    <HEAD>

        <TITLE>Custom Billing Manual Upload</TITLE>
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
            
            .message{
                height:20px;
                margin-left:80px;
                color:blue
            }

        </style>

        <script type='text/javascript'>
            
	$.fn.iframePostForm = function (options)
	{
		var response,
			returnReponse,
			element,
			status = true,
			iframe;
		
		options = $.extend({}, $.fn.iframePostForm.defaults, options);
		
		
		// Add the iframe.
		if (!$('#' + options.iframeID).length)
		{
			$('body').append('<iframe id="' + options.iframeID + '" name="' + options.iframeID + '" style="display:none" />');
		}
		
		
		return $(this).each(function ()
		{
			element = $(this);
			
			
			// Target the iframe.
			element.attr('target', options.iframeID);
			
			
			// Submit listener.
			element.submit(function ()
			{
				// If status is false then abort.
				status = options.post.apply(this);
				
				if (status === false)
				{
					return status;
				}
				
				
				iframe = $('#' + options.iframeID).load(function ()
				{
                                    
                                        try {
                                            response = iframe.contents().find('body');
                                            if (options.json)
                                            {
                                                    returnReponse = $.parseJSON(response.html());
                                            }
                                            else
                                            {
                                                    returnReponse = response.html();
                                            }
                                            options.complete.apply(this, [returnReponse]);
                                            iframe.unbind('load');
                                            setTimeout(function ()
                                            {
                                                    response.html('');
                                            }, 1);
                                        
                                        }
                                        catch (e) {
                                            options.error.apply(this,[e]);
                                        }                                    
				});
			});
		});
	};
	
	
	$.fn.iframePostForm.defaults =
	{
		iframeID : 'iframe-post-form',       // Iframe ID.
		json : false,                        // Parse server response as a json object.
		post : function () {},               // Form onsubmit.
		complete : function (response) {},   // After response from the server has been received.
		error : function (err) {}               // server error occurs.
	};
            
            $.ajaxSetup({ cache: false });
         
            $( function() {
                

                $("#invoiceApprovalDiv").bind("closed", function(event,data){
                    invSearch('invSearchTopDiv', 1, 'invSearchForm');
                });

                $("#billToSiteLovDiv").bind("selected", function(event,data){
                    console.log(data);
                    $("#searchLocation").val(data.location);
                });

                $(".downloadInvoice").live('click',function(){
                    var tr=$(this).closest("tr");
                    window.open('canonCustomBillingInvoiceDownload.jsp?path='+tr.data("invoicePath")+"&invoiceId="+tr.data("invoiceId")+"&urnNumber="+tr.data("urnNumber")+"&invFileName="+encodeURIComponent(tr.data("invFileName")));
                });
				// Added by DUNA RAO for ITG#Request 645933
                $("#customerProfileLovDiv").bind("selected", function(event,data){
                    $("#searchCustomerProfile").val(data.profileName);
                    $("#customerProfileNum").val(data.profileNum);
                    $("#custprofileselect").val(data.profileName);
                }); 
                
				$(".customerProfileDot").live('click',function(){
					openCustomerProfileSearch('customerProfileLovDiv');
				});
				// Added by DUNA RAO for ITG#Request 645933
            });
            
        </script>


    </HEAD>
    <body >
        <%@ include file="jtfcalincps.jsp" %>
        <%@ include file="canonCustomBillingTop2.jsp" %>

        <div id="page_content" class="T2 clearfix" >
            <div id = "invSearchTopDiv">
                <jsp:include page="canonCustomBillingManualUploadInclude.jsp">
                    <jsp:param name="userId" value="<%=userId%>" />
                </jsp:include>
            </div>
		<!-- <div id="customerProfileLovDiv"></div> -->
		<div id="customerGroupLovDiv"></div>
        <div id="billLocationLovDiv" /></div>
        <div id="custNameLovDiv"/></div>
		<div id="customerNameLovDiv"/></div>
        <div id="parentCustomerLovDiv"/></div>        
    <%@ include file="canonCBSAppBottom.jsp"%> 
    <%-- <%@ include file="jtfcalincpe.jsp" %> --%>
</body>
</html>
