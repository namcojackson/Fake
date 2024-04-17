<%@page import="java.math.BigDecimal"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessDAO"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessHelper"%>
<%@page import="java.util.List"%>
<%!
    static final boolean IS_DEV=CanonE008ItemProcessHelper.isDevEnviornment();
    
%>    
<%
    String action = request.getParameter("action");
    String project_number = request.getParameter("project_number");
    String user_id = request.getParameter("userid");
    //BigDecimal respId=(BigDecimal)CanonE008ItemProcessUtil.toBigDecimal(request.getParameter("respId"),false);
    if ("download".equals(action)) {
//        List alCntrReadHdrLbl = (List) pageContext.getAttribute("alCntrReadHdrLbl");
//        List downloadCntrReads = (List) pageContext.getAttribute("downloadCntrReads");
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename= " + project_number + ".xls");
//            WorkbookStream workbookStream = new CanonE008ItemProcessHelper().genMeterReadWorkbookStream(project_number, alCntrReadHdrLbl, downloadCntrReads);
//            workbookStream.save(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else if ("transform".equals(action)) {
        try{
            String result=CanonE008ItemProcessHelper.transform(project_number,user_id,pageContext, request, response);
%>
'<%=result%>'
<%
//        out.println(result);
//        out.flush();
       }catch(Exception ex){
       System.out.println(ex);
%>
Exception: '<%=ex.getMessage()%>'
<%
       }
    } else if ("upload".equals(action)) {
        String  resultMsg = (String) pageContext.getAttribute("resultMsg",PageContext.REQUEST_SCOPE);
        if(resultMsg!=null){
            out.println(resultMsg);
            out.flush();
        }
    } else if ("select".equals(action)) {
%>

<script>
    $(function ()
    {
        function showPleaseWait(){
            blockUsrInterface();
        }
        
        function hidePleaseWait(){
            unBlockUsrInterface()
        }

        $('#uploadForm').iframePostForm
        ({
            post : function ()
            {
			
                if ($('input[type=file]').val().length)
                {
                    $('.message')
                    .html('Uploading file&hellip;')
                    .slideDown();
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
                    $('.message')
                    .append(response)
                    $("#submitButton")[0].disabled=true;
                }else{
                    var res=response.substr(1,response.length-2);
                    $("#submitForm input[id^='_xls_']").remove();
                    $(res).appendTo("#submitForm");
                    var handler=$.makeMultiErrorHandler();
                    handler.showErrors_= function (errs) {
                        var messages="<ul>";
                        $(errs).each(function(i,error){
                            //error.component.addClass( "ui-state-error");
                            messages+="<li>";
                            if($(error.component).data("item-code")){
                                messages+="Canon Item#->"+$(error.component).data("item-code")+" ";
                            }
                            messages+= error.message+"</li>";
                        });
                        messages+="</ul>";
                        var html=
                            '<div title="Alert">'
                                +'<p>'
                                + messages 
                                + '</p>'+
                            '</div>';
                       $('.message').html(html);
                    };
                    ///alert("22222");
                    $.checkItems("_xls_",handler);
                    handler.done();
                    
                    if(handler.valid()){
                        $("#submitButton")[0].disabled=false;
                        $("#excelFile")[0].disabled=true;
                        $('.message')
                        .html("Click submit button to upload the items.")
                        .slideDown();
                    }else{
                        handler.showError();
                        $('.message').append('<span style="font-weight: normal; color: black">Excel validation failed. Please fix the data.</span>');
                        $("#submitButton")[0].disabled=true;
                    }
                }
            }
        });
        
        
        $("#excelFile").change(function(event){
            if(!event.hasOwnProperty("result")){
                $("#uploadForm").submit();
            }
        });

        $("#submitButton").click(function(){
            var url =  $("#submitForm").attr('action');
            //alert("4444");
            showPleaseWait();           
            $("#submitButton")[0].disabled=true;
            $.ajax({
              type: "POST",
              url: url,
              data: $("#submitForm").serialize(),
              success: function(data){
                hidePleaseWait();           
                if(error_message(data)){
                    error_handler("Failed to upload item(s).",data);
                }else{
                    update_status("Item(s) has been uploaded.");
                    $("#submitButton").data("need-refresh",true);
                }
              },
              error:function(request){
                    hidePleaseWait();           
                    var response=request.responseText;
                    error_handler("Failed to upload item(s).",response);
              }
            });                        
            
        });
        
        function error_handler(errmsg,response){
            update_status(errmsg);
<%--             <% if(IS_DEV){ %> --%>
                  $("#_xls_validMsg" ).append('&nbsp;<a class="jsp_error" href="#">view error detail (visible only in dev environment)</a>');
                  $("#_xls_validMsg .jsp_error").click(function (){
                     var newWindow = window.open("","Error","width=400,height=500,scrollbars=1,resizable=1")
                     newWindow .document.open()
                     newWindow .document.write(response)
                     newWindow .document.close()
                  });
            <%-- <%} %>  --%>   
        }        

        function error_message(data){
            var re=new RegExp("^<!--ERROR_MESSAGE(.*)ERROR_MESSAGE-->","m");
            var match=re.exec(data);
            if (match){
                return match[1];
            }else{
                return "";
            }
        }
         function update_status( t) {
            var project_status = $("#_xls_validMsg" );
            project_status
              .text( t )
              .addClass( "ui-state-highlight" );
            setTimeout(function() {
              project_status.removeClass( "ui-state-highlight", 1500 );
            }, 500 );
        }
        
    });    
    
</script>

<form id="uploadForm" action="CanonE008ExcelProcess.jsp?action=transform&project_number=<%=project_number%>" method="post" enctype="multipart/form-data">
    <input type="file" name="excelFile" id="excelFile" size="30" />
</form>

<form id="submitForm" action="CanonE008ItemWorkbenchProcess.jsp?action=UPLOAD&projectNumber=<%=project_number%>" method="post" >
    <input type="button" value="Submit" id="submitButton" disabled="disabled"/>
    <input type="hidden" name="param_prefix" value="_xls_">
</form>

<div id="_xls_validMsg" class="message" style="font-weight: bold; color: blue"></div>
<%}
%>