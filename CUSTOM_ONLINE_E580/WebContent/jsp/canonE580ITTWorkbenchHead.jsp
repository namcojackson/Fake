<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<link href="<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui.css" rel='stylesheet' type='text/css' />

<script src="<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-1.10.2.min.js" type='text/javascript'></script>  
<script src="<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-1.10.3.custom.min.js" type='text/javascript'></script>
<script src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-timepicker-addon.js' type='text/javascript'></script>
<script src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery.blockUI2.js' type='text/javascript'></script> 
<script src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/ui.core.js' type='text/javascript'></script>
<script src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/autoNumeric-1.7.1.min.js' type='text/javascript'></script>
<link rel="stylesheet" href="/s21extn/e580/css/canon_e580_style.css" type="text/css">
<link rel="stylesheet" href="/s21extn/e580/css/styles.css" type="text/css">	
<link rel="stylesheet" href="/s21extn/e580/css/canon_e580_style2.css" type="text/css">

<style>
.ui-dialog .ui-dialog-title{
	 	float: none;
	 }
</style>
<script language="javascript">
    if (!window.console) {
        var console = {};
    }
    if (!console.log) {
        console.log = function() {
        };
    }

    function blockUsrInterface()
    {
        $.blockUI({css: {
                border: 'none',
                padding: '15px',
                backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                opacity: .5,
                color: '#fff',
                cursor: 'default'
            },
            overlayCSS: {cursor: 'default'},
            baseZ: 9000,
            message: '<h1> Please Wait...</h1>'
        });

    }

    function unBlockUsrInterface()
    {
        $.unblockUI();
    }

    function setFocus(x)
    {
        document.getElementById(x).focus();
    }


    function openITTLOV(mName,title,jsp,height,width, options)
    {
    	blockUsrInterface();
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
                unBlockUsrInterface();               
                $(modelName).html("");
                $(modelName).html(data);               
                
            }
        });
        $(modelName).dialog("open");
        $(".ui-dialog-titlebar").addClass("hd");
	     $(".ui-dialog").css({"top":"330px"}); 
	     $('.ui-widget-overlay').css({'background':'none'});
    }

    function openITTOrderProcessorLOV(mName, options)
    {
        openITTLOV(mName,"ITT Order Processor LOV",'canonE580ITTOrderProcessorLOV.jsp',350,550, options);
    }
    
    function openMarkviewLOV(mName, options)
    {
        openITTLOV(mName,"Therefore Attached Documents",'canonE580MarkviewLOV.jsp',450,720, options);
    }

    function openDealerLOV(mName, options)
    {
        openITTLOV(mName,"Dealer LOV",'canonE580DealerLOV.jsp',500,800, options);
    }

</script>

