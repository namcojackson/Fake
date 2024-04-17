<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%@ page import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>
<% String ctxPath = request.getContextPath() ; %>
<%
	String commonRoot = commonRoot(request);
%>
  
<title>Item Project Workbench</title>
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta charset="utf-8">
<meta content="" name="description">
<meta content="" name="keywords">
<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">

<link href='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-1.7.2.custom.canon.css' rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/jquery.dataTables.min.css' rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/fixedColumns.dataTables.min.css' rel='stylesheet' type='text/css' />
<link href="<%=ctxPath%>/e008/css/bootstrap.min.css" rel="stylesheet" type='text/css'> 
<link href='<%=ctxPath%>/e008/css/styles.css'	rel='stylesheet' type='text/css' />
<link href='<%=ctxPath%>/e008/css/canonE008_Item_WB.css'	rel='stylesheet' type='text/css' />

<%-- <script	src='<%=ctxPath%>/e008/js/jquery-1.12.0.min.js'	type='text/javascript'></script> --%>
<%-- <script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-1.10.2.min.js'	type='text/javascript'></script> --%>
<%--<script	src='<%=ctxPath%>/e008/js/jquery-1.12.0.min.js'	type='text/javascript'></script> --%>
<%--<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-1.10.3.custom.min.js' type='text/javascript'></script>--%>
<script src="<%=ctxPath%>/common/jquery/jquery-3.5.1.min.js" type='text/javascript'></script>    
<script src="<%=ctxPath%>/common/jquery/jquery-ui.min.js" type='text/javascript'></script>  
 
<script	src='<%=ctxPath%>/e008/js/jquery-1.12.0.min.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-1.10.3.custom.min.js' type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery-ui-timepicker-addon.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/jquery.blockUI2.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/ui.core.js'	type='text/javascript'></script>
<script	src='<%=CanonS21SessionValidate.commonRoot(request)%>/jquery/autoNumeric-1.7.1.min.js'	type='text/javascript'></script>
<script type="text/javascript" charset="utf8"	src="<%=ctxPath%>/e008/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"	src="<%=ctxPath%>/e008/js/dataTables.fixedColumns.js"></script>
<script type="text/javascript" charset="utf8"	src="<%=ctxPath%>/e008/js/bootstrap.min.js"></script>


<!-- JS -->



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
    	//alert("/e008/images/canonE008Wait.gif");
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
            message: '<h1> Please Wait...</h1><img src="<%=ctxPath%>/e008/images/canonE008Wait.gif" />'
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

   

    function openE008LOV(mName,title,jsp,height,width, options)
    {
        var urlData = jsp + "?modalName=" + mName;
        if (options) {
            urlData = urlData + "&" + jQuery.param(options);
        }

        blockUsrInterface();
        $.ajax({
            url: urlData,
            cache: false,
            success: function(data) {
                unBlockUsrInterface();
                modelName = "#" + mName;

                $(modelName).html("");
                $(modelName).html(data);
                $(modelName).dialog({
                    title: title,
                    autoOpen: false,
                    modal: true,
                    height: height, //"auto !important",
                    width: width,
                    resizable: false,
                    closeOnEscape: true,
                    buttons: {"Close": function() {
                            $(this).dialog("close");
                            $(this).dialog("destroy");
                            $(modelName).html("");
                            $(modelName).trigger("closed");
                        }}
                }).dialog("widget")
                .css("padding-top", "10px")
                .find(".ui-dialog-titlebar").css({
                    "float": "right",
                    border: 0,
                    padding: 0
                })
                .find(".ui-dialog-title").css({
                    display: "none"
                });
                $(modelName).dialog("open");
            }
        });
    }   

/* 		 if (!Modernizr.datalistelem) {
			 	alert('This browser does not support HTML5 datalist element, so we will load the polyfills');
			 }
	*/	
  	
  	<%-- Modernizr.load({
			 	test: Modernizr.datalistelem,
			 	nope: ['<%=ctxPath%>/e008/js/jquery.datalist.js', '<%=ctxPath%>/e008/js/load.datalist.js']
			 });   
	 --%>
				//document.createElement('datalist');          // IMPORTANT AS WELL
			 	/* window.onload = function() {
			    	alert(document.getElementById('masterProject').getElementsByTagName('option').length);//should alert 42
					}; 
			  */
    
</script>
