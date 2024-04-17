
<!DOCTYPE html>
<%@page import="com.canon.cusa.s21.framework.security.S21ProtectedResource"%>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContext" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.context.S21SecurityContextFactory" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.config.S21ConfigurationException" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.helpers.S21AuthenticationHelper" %>
<%@ page language="java" import="com.canon.cusa.s21.framework.security.authentication.token.S21UserPasswordAuthenticationToken" %>
<%@ page import="com.canon.cusa.s21.framework.security.S21AuthorizationAction"%>
<%@ page import="canon.apps.common.CanonS21SessionValidate"%>

<html>
<head>
<title>canonE307 Context Parameters</title>
</head>
<body>


<%
String pageName="";
S21SecurityContext context = S21SecurityContextHolder.getContext();

System.out.println("in isJSPAccessValid, context is  "+context);
if (context != null && context.getAuthentication()!=null){
    try {
        S21AuthorizationAction[] actions = context.getAuthentication().getAuthorizationActions();
        if(actions!=null ){
            System.out.println("in isJSPAccessValid actions ="+actions.length );
       
            StringBuffer sb= new StringBuffer("");
            sb.append("<table style='width:600px;text-align:left;'><tr style='font-weight:bold;'>");
            sb.append("<th>ResourceObjName</th>");
            sb.append("<th>AuthorizationName</th>");
          // sb.append("<th>ResourceAlternativeName</th>");
            sb.append("<th></th></tr>");
            
            for (S21AuthorizationAction action : actions) {
                String resourceObjName = action.getName(); //pageName
                
                String authorizationName= action.getAuthorizationName();
                
           //    String resourceAlternativeName =   action.getResourceAlternativeName();
          
          //     String alias = action.getAlias();
                
            //  S21ProtectedResource s21PrtRes = (S21ProtectedResource)   action.getResourceInfo();
              
           //    String resName=    s21PrtRes.getResourceName();
                
              sb.append("<tr><td>"+resourceObjName+"</td>"  );
              sb.append("<td>"+authorizationName+"</td>");
            //  sb.append("<td>"+resourceAlternativeName+" </td>");
              sb.append("<td></td>");
            }
            
            sb.append("</tr></table>");
            out.println(sb.toString());
            
            
       
            out.println("<br><br><b>userName</b> : "+CanonS21SessionValidate.getUserName());
            System.out.println("in isJSPAccessValid "+pageName +" not found.");
        }else{
            System.out.println("in isJSPAccessValid actions is null.");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}



%>

</body>
</html>