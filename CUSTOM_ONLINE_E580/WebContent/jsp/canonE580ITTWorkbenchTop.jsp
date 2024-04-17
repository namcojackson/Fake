
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@ page import="canon.apps.common.CanonS21SessionValidate"%>

<%
    String [] names=CanonE580ITTWorkbenchUtil.getUserNameAndFullNameS21(request,response);
    String userName=names[0];
    String userFullName=names[1];
%>    

<table width="100%" border="0" >					
    <tr> 
        <td bgcolor="" align=""><img src="<%=CanonS21SessionValidate.commonRoot(request)%>/images/csa_logo.jpg"></td>        
    </tr>
</table>
<table width="99%" border="0" >
    <tr> 
        <td align="right"> <a href="canonE580ITTWorkbenchSearch.jsp" >Home</a>&nbsp;&nbsp; | &nbsp;&nbsp;<b  class ="search_text">Logged As:</b>&nbsp;&nbsp;<%=userName%>&nbsp;(<%=userFullName%>)</td>
    </tr>
</table>
