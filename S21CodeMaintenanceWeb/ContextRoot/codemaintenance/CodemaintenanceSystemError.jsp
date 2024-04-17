<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean" %>
<%-- JSP initialize --%>
<%
	S21NEContainerDataBean bean = (S21NEContainerDataBean) request.getAttribute("s21nevo");
	Exception exp = bean.getExp();
	List msg = new ArrayList();
	if (exp != null) {
		msg.add(exp.toString());
		StackTraceElement[] ste = exp.getStackTrace();
		for (int i = 0; i < ste.length; i++) {
			msg.add(ste[i].toString());
		}
	} else {
		msg.add("See log file.");
	}
	request.setAttribute("sysmessages", msg);
%>
<!-- Code Maintenance Edit Start -->
<table cellpadding="0" width="95%">
	<tr>
		<td width="5%">
		</td>
		<td width="85%">

			<div style="OVERFLOW: auto; WIDTH: 880px; HEIGHT: 480px">
				<table border="1" width="100%">
					<tr>
						<td>System Error</td>
					</tr>
					<tr>
						<td>
<c:forEach var="errmsg" items="${sysmessages}">
							<c:out value='${errmsg}'/><br>
</c:forEach>
						</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
</TABLE>

<!-- Code Maintenance Edit End -->