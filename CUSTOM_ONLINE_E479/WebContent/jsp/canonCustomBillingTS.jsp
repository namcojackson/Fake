<%@page import="oracle.apps.fnd.common.WebRequestUtil"%>
<%@ page language="java" import="oracle.apps.jtf.calendar.util.CalendarUtil" %>
<%@page import="oracle.apps.jtf.base.Logger" %>
<%@page import="oracle.apps.jtf.util.GeneralPreference" %>
<%@ page import="oracle.apps.jtf.base.resources.AOLMessageManager" %>
<%@ page language="java" import="java.math.*" %>



<jsp:include page="canonCustomBillingUIInclude.jsp"/>

<script type='text/javascript'>
    /**
     * converted stringify() to jQuery plugin.
     * serializes a simple object to a JSON formatted string.
     * Note: stringify() is different from jQuery.serialize() which URLEncodes form elements

     * UPDATES:
     *      Added a fix to skip over Object.prototype members added by the prototype.js library
     * USAGE:
     *  jQuery.ajax({
     *	    data : {serialized_object : jQuery.stringify (JSON_Object)},
     *		success : function (data) {
     *
     *		}
     *   });
     *
     * CREDITS: http://blogs.sitepointstatic.com/examples/tech/json-serialization/json-serialization.js
     */
    jQuery.extend({
        stringify  : function stringify(obj) {
            var t = typeof (obj);
            if (t != "object" || obj === null) {
                // simple data type
                if (t == "string") obj = '"' + obj + '"';
                return String(obj);
            } else {
                // recurse array or object
                var n, v, json = [], arr = (obj && obj.constructor == Array);

                for (n in obj) {
                    v = obj[n];
                    t = typeof(v);
                    if (obj.hasOwnProperty(n)) {
                        if (t == "string") v = '"' + v + '"'; else if (t == "object" && v !== null) v = jQuery.stringify(v);
                        json.push((arr ? "" : '"' + n + '":') + String(v));
                    }
                }
                return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
            }
        }
    });

    function searchTemplate(searchType, level){
        $("#mainForm input[name='action']").val("");
        showPleaseWait();	
        formId = "#mainForm";
        divId = "#divTemplateSearch";
        var url = $(formId).attr('action');
        $("#searchType").val(searchType);       
        $.post( url, $(formId).serialize(),
        function( data ) {    
            hidePleaseWait();
            $(divId).html("");       
            $(divId).html(data);
            setupAccordian();        
            $( "#accordion" ).accordion({ active: level }); 
        }); 
    }
  
  
    function setupAccordian(){    
        $("#accordion").accordion(); 
        //$( "#accordion" ).accordion({ collapsible: true });
        // $( "#accordion" ).accordion({ active: false });     
        $( "#accordion" ).accordion({ autoHeight: false,
            clearStyle: true
                                     
        }); 
        var icons = $( "#accordion" ).accordion( "option", "icons" );
        $( "#accordion" ).accordion( "option", "icons", { 'header': 'ui-icon-plus', 'headerSelected': 'ui-icon-minus' } );
    }
  
  
    $(document).ready(function() {
        setupAccordian();
     
    });
    
    function selectTemplateForCopy(){
        var data =$("#selected_template").data("template");
        if (typeof data != "undefined" &&  data!="") {
            var templInfo="Selected Template (";
            if(data.templateId){
                templInfo+=" ID:"+data.templateId
            }
            if(data.profileName){
                templInfo+=" Profile Name:"+data.profileName
            }
            if(data.partyName){
                templInfo+=" Party Name:"+data.partyName
            }
            if(data.billToUse && data.billToUse!="-1"  && data.billToUse!=""){
                templInfo+=" Bill To Use:"+data.billToUse
            }
            templInfo+=" )";
            $("#selected_template").html(templInfo);
            $("#selectedTemplate").val(jQuery.stringify(data));
            $("#selectedTemplateId").val(data.templateId);
            
        }
        
    }
    
    function openValidateTemplate(mName, options){
        var urlDetail = 'canonCustomBillingValidateTemplate.jsp?modalName='+mName;
        if(options){
            urlDetail=urlDetail+"&"+jQuery.param(options);
        }
        modelName = "#"+mName;
        showPleaseWait();		
        $(modelName).dialog({
            height: 200,
            title: "Validate Template Record",
            modal: true ,
            autoOpen :false,
            width: 320,		
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
        $(modelName).dialog("open");
    }

    function openTemplateSetup(options,action){
        console.log(action);
        console.log(options);
        $.each( options, function(name, value){
            $("#mainForm input[name='"+name+"']").val(value);
        });
        $("#mainForm input[name='action']").val(action);
        $("#mainForm").submit();
    }

    $( function() {
     
        $("#customerProfileLovDiv").bind("selected", function(event,data){
            $("#searchCustomerProfile").val(data.profileName);
            $("#customerProfileNum").val(data.profileNum);
        });

        $(".customerProfileDot").live('click',function(){
            openCustomerProfileSearch('customerProfileLovDiv');
        });

        $("#customerGroupLovDiv").bind("selected", function(event,data){
            $("#searchCustomerGroup").val(data.customerGroupName);
        });

        $(".customerGroupDot").live('click',function(){
            openCustomerGroupSearch('customerGroupLovDiv');
        });

        
        $("#customerNameLovDiv").bind("selected", function(event,data){
            $("#searchCustomerName").val(data.customerName);
        });

        $(".customerNameDot").live('click',function(){
            openCustomerNameSearch('CUSTOMER','customerNameLovDiv');
        });
        
        $("#parentCustomerLovDiv").bind("selected", function(event,data){
            $("#searchParentCustomer").val(data.parentCustomer);
        });

        $(".parentCustomerDot").live('click',function(){
            openCustomerNameSearch('PARENT_CUSTOMER','parentCustomerLovDiv');
        });
        
        $("#billToSiteLovDiv").bind("selected", function(event,data){
            $("#searchBillToSite").val(data.location);
        });

        $(".billToSiteDot").live('click',function(){
            openBillToSiteSearch('billToSiteLovDiv');
        });
        
        $("#cleanCustomerProfile").live("click",(function(event){
            $("#searchCustomerProfile").val('');
            event.preventDefault();
        }));
        
        $("#clearPartyName").live("click",(function(event){
            $("#searchCustomerName").val('');
            event.preventDefault();
        }));
        
        $("#clearBillToSite").live("click",(function(event){
            $("#searchBillToSite").val('');
            event.preventDefault();
        }));

        $(".copy").live("click",(function(event){
            tr=$(this).closest("tr");
            data=tr.data("template");
            $("#selected_template").data("template",data);
            selectTemplateForCopy();
            event.preventDefault();
        }));

        $(".paste").live("click",(function(event){
            tr=$(this).closest("tr");
            data=tr.data("template");
            openTemplateSetup(data,"COPY");
            event.preventDefault();
        }));

        $(".action").live("click",(function(event){
            tr=$(this).closest("tr");
            data=tr.data("template");
            openTemplateSetup(data,"NEW_OR_UPDATE");
            event.preventDefault();
        }));
        
        selectTemplateForCopy();

    });
    
</script>


<!--- Do all your cookie processiong code here  --->
<NOSCRIPT>
<P>
    <SPAN CLASS="HELPTEXT">
        <%
            final int _pc = pageContext.REQUEST_SCOPE;

            final int pc = pageContext.REQUEST_SCOPE;
        %>
    </SPAN>
</P>
</NOSCRIPT>

<HTML>

    <HEAD>

        <TITLE>Custom Billing Template Search</TITLE>

        <meta HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">

        <style type="text/css">
            .ui-accordion .ui-accordion-content{
                overflow:hidden !important;

            }

        </style>

    </HEAD>
    <%
        String myRegionName = "";//"JTF_NOTES_CREATE";
        String myPermissionName = "";//"JTF_NOTES_CREATE";
        oracle.apps.fnd.common.WebAppsContext objWebAppsContext =
                WebRequestUtil.validateContext(request, response);

        int userId = objWebAppsContext.getUserId();

    %>
    <body >
        <%@ include file="jtfcalincps.jsp" %>
        <%@ include file="canonCustomBillingTop.jsp" %>
        <div id="page_content" class="T2 clearfix" >

            <div id = "divTemplateSearch">
                <jsp:include page="canonCustomBillingTSInclude.jsp">
                    <jsp:param name="userId" value="<%=userId%>" />
                </jsp:include>
            </div>

        </div>
        <div id="customerProfileLovDiv"></div>
        <div id="customerGroupLovDiv"></div>
        <div id="customerNameLovDiv"/></div>
        <div id="parentCustomerLovDiv"/></div>
    <div id="billToSiteLovDiv"/></div>
    <div id="validateTemplateDiv"/></div>

<%@ include file="canonCBSAppBottom.jsp"%> 
<%@ include file="jtfcalincpe.jsp" %>
</body>
</html>
