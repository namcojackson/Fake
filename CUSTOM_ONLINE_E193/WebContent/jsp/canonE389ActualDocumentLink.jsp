<!-- $Header: canonE389ActualDocumentLink.jsp $ -->
<%--========================================================================
 |
 | FILE
 | canonE389ActualDocumentLink.jsp - View actual invoice from 170 system.
 |
 | DESCRIPTION
 |   For a given Document Id, get actual Document link
 |
 | AUTHOR
 | Chandra Sekhar
 |
 | CREATION DATE
 | 07-Apr-2009
 |
 | HISTORY
 | DATE         WHO               WHY
 |
 | 06-Apr-2009  Chandra Sekhar    ITG # 209125 - JSP DFF Utility
 |
 +=======================================================================--%>
<%@page language="java" %>

<%@page import="java.lang.*" %>
<%@page import ="java.util.*" %> 
<%@ page language="java" import="com.canon.oracle.custapp.custinq.beans.CanonE389DffDataBean" %>


<head>
  <title>Document Link</title>
  <style>
    textarea { border: 0;overflow: hidden; }
  </style>
</head>

<%
   try {
%>

<%
      // forward to next page
      String strNextPage = "";
      String strDocumentId = request.getParameter("DocumentId")==null?"":request.getParameter("DocumentId");
      String strActualDocumentLink = CanonE389DffDataBean.getDocumentIdLink(strDocumentId);

      if("ERROR".equalsIgnoreCase(strActualDocumentLink))
      {
%>
        <b><font color="red" size = 5 class="search_text">Actual Document is not available on 170 System</font></b><br><br>
        <input type="button" name="Close" id="Close" size="15" value="&nbsp;&nbsp;&nbsp;&nbsp;Close&nbsp;&nbsp;&nbsp;&nbsp;"           onClick="javascript:window.close();" />
<%
      }
      else
      {
        strNextPage = strActualDocumentLink;
        response.sendRedirect(strNextPage);
      }

}
catch (Exception eExp) {
%>
     <b><font color="red" size = 5 class="search_text">Actual Document is not available on 170 System</font></b><br><br>
     <input type="button" name="Close" id="Close" size="15" value="&nbsp;&nbsp;&nbsp;&nbsp;Close&nbsp;&nbsp;&nbsp;&nbsp;"           onClick="javascript:window.close();" />
<%
}
%>