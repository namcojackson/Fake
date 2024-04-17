<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>

<%!    final String PAGE_NAME = "canonE008Project.jsp";
%>
<%
    //CanonE590ItemProcessUtil.checkPageAccess(pageContext, request, response, PAGE_NAME);
%>

<!DOCTYPE html>
<html>
	<HEAD>
	
		<TITLE>New Item Setup - Project Display</TITLE>
        <%@include file="canonE008Head.jsp"%>
        

 <script language="javascript">

            $(function() {
                
        
            // http://www.jainaewen.com/files/javascript/jquery/iframe-post-form.html 
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
                                        });
                                });
                        });
                };


                $.fn.iframePostForm.defaults =
                {
                        iframeID : 'iframe-post-form',       // Iframe ID.
                        json : false,                        // Parse server response as a json object.
                        post : function () {},               // Form onsubmit.
                        complete : function (response) {}    // After response from the server has been received.
                };


            });

        </script>

        
        
	</HEAD>

	<body>
		<div id="validatation-DataDiv"></div>
		<div id="uploadExcelDiv"></div>
		
		<div class="logo-img">
		<div class="utility-nav">
			<div>
			<a href="<%=ctxPath%>/e008/jsp/CanonE008ItemSetupSearch.jsp">Home</a>
			</div>
			
		</div>
		<div class="logo">
		<a href="#">
		<img width="210" height="98" title="Canon Solutons America" alt="Canon Solutons America" src="<%=ctxPath%>/common/images/csa_logo.jpg">
		</a>
		</div>
		</div> 
		<input type="hidden" name="pageNo" id="pageNo" value="0">
		<input type="hidden" name="pageFrom" id="pageFrom" value="0">
		<input type="hidden" name="pageTo" id="pageTo" value="0">
	    <div id = "divCanonE008Main">
		    	<jsp:include page="canonE008ProjectInclude.jsp"/>
	    </div>
    </body>
</html>   