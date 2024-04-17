<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessHelper"%>
<%@ page isErrorPage="true" %>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessUtil"%>
<%-- <%@page import="oracle.apps.e008.item.process.CanonE008ItemProcessDAO"%> --%>
<%@ page import="canon.apps.common.CanonS21SessionValidate"%>
<%!
    static final boolean IS_DEV=false;//CanonE590ItemProcessHelper.isDevEnviornment();

%>
<%
    if(exception==null){
        exception=new Exception("No Error.");
    } 
%>    
<!--ERROR_MESSAGE <%=exception.getMessage()%> ERROR_MESSAGE-->

<% if(IS_DEV) { 
        StringWriter sw=new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
%>
<!--STACKTRACE ( stacktrace information visible in dev environment
<%=sw.toString()%>
) STACKTRACE-->
<%  
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
<style>
    .homeBigHeaderCell { background-color: #6361CE; font-family: Arial, Helvetica, sans-serif; font-size: 16pt; color: #FFFFFF }
    .debugMessage { background-color: #f7f7e7; font-family: Arial, Helvetica, sans-serif; font-size: 10pt; color: #336699 }
    .errorHeader { background-color: #CCCC99; font-family: Arial, Helvetica, sans-serif; font-size: 16pt; color: #CC0000 }
    .errorMessage { background-color: #CCCC99; font-family: Arial, Helvetica, sans-serif; font-size: 10pt; color: #CC0000 }
    .prompt { font-family: Arial, Helvetica, sans-serif; font-size: 10pt }
    .footnote { font-family: Arial, Helvetica, sans-serif; font-size: 9pt; color: #336699 }
    .footnoteHeader { font-family: Arial, Helvetica, sans-serif; font-size: 10pt; font-weight: bold; color: #336699 }
    
</style>        
    </head>
    <body>
        <table width="100%" border="0" cellpadding="5" summary=""> 
            <tbody><tr>
                    <td width="5%">&nbsp;</td>
                    <td><img src="<%=CanonS21SessionValidate.commonRoot(request)%>/csastyles/img/csa_logo.jpg"></td> <!-- Performs no action so alt is blank--> 
                    <td width="5%">&nbsp;</td>
                </tr>
                <tr>
                    <td width="5%">&nbsp;</td>
                    <td class="homeBigHeaderCell">Error Message</td>
                    <td width="5%">&nbsp;</td>
                </tr>
                <tr>
                    <td width="5%">&nbsp;</td>
                    <td class="prompt">
                        <p>The following error occurred:</p>
                    </td>
                    <td width="5%">&nbsp;</td>
                </tr>
                <tr>
                    <td width="5%">&nbsp;</td>
                    <td class="errorMessage">&nbsp; <%=exception.getMessage()%></td>
                    <td width="5%">&nbsp;</td>
                </tr>
                <tr>
                    <td width="5%">&nbsp;</td>

                    <td class="prompt">No further information.</td>


                    <td width="5%">&nbsp;</td>
                </tr>
                <tr>
                    <td width="5%">&nbsp;</td>
                    <td class="prompt">
                        <p>Contact your system administrator if the error persists.</p>
                    </td>
                    <td width="5%">&nbsp;</td>
                </tr>
            </tbody></table>

    </body>
</html>


