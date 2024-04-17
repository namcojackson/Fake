<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.ap.S21CMSearchScreenBean" %>
<%-- JSP initialize --%>
<%
	S21NEContainerDataBean bean = (S21NEContainerDataBean) request.getAttribute("s21nevo");
	S21CMSearchScreenBean screen = (S21CMSearchScreenBean) bean.getResponse();
	request.setAttribute("searchTableNames", screen.getSearchTableNames());
	request.setAttribute("searchTableLongNames", screen.getSearchTableLongNames());
	request.setAttribute("searchTableVisible", screen.getSearchTableVisible());
%>
<script type="text/javascript">
<!--
function funcS21NEPostTableSelect(table) {
    document.F1.S21NE_REQ_ID.value = "selectTable";
    document.F1.selectTable.value = table;
    funcSubmitUrlenc();
}
// -->
</script>
<table cellpadding="0" width="100%">
	<tr>
		<td width="5%">
		</td>
		<td width="85%">

	<!-- Search Table Result -->
	<div style="OVERFLOW: auto; WIDTH: 980px; HEIGHT: 552px">
	<table border="1" cellspacing="0" cellpadding="1">
		<tr>
			<th class="pClothBs" width=485px>
				Table Name
			</th>
			<th class="pClothBs" width=485px>
				Long Name
			</th>
		</tr>

<c:forEach var="names" items="${searchTableNames}" varStatus="ncount">
	<c:if test="${ncount.index % 2 != 0}">
		<tr style="background-color:#F5F5F5;">
	</c:if>
	<c:if test="${ncount.index % 2 == 0}">
		<tr style="background-color:#FFFAFA;">
	</c:if>
	<c:if test="${searchTableVisible[ncount.index]}">
			<td class="stab">
				<a href="javascript:funcS21NEPostTableSelect('<c:out value='${names}'/>')">
					<c:out value='${names}'/>
				</a>
			</td>
			<td class="stab">
				<a href="javascript:funcS21NEPostTableSelect('<c:out value='${names}'/>')">
					<c:if test="${searchTableLongNames[ncount.index] != null && searchTableLongNames[ncount.index] != ''}">
						<c:out value='${searchTableLongNames[ncount.index]}'/>
					</c:if>
					<c:if test="${searchTableLongNames[ncount.index] == null || searchTableLongNames[ncount.index] == ''}">
						<br>
					</c:if>
				</a>
			</td>
	</c:if>
	<c:if test="${!searchTableVisible[ncount.index]}">
			<td class="stab">
				<c:out value='${names}'/>
			</td>
			<td class="stab">
				<c:if test="${searchTableLongNames[ncount.index] != null && searchTableLongNames[ncount.index] != ''}">
					<c:out value='${searchTableLongNames[ncount.index]}'/>
				</c:if>
				<c:if test="${searchTableLongNames[ncount.index] == null || searchTableLongNames[ncount.index] == ''}">
					<br>
				</c:if>
			</td>
	</c:if>
		</tr>
</c:forEach>
	</table>
	</div>

		</td>
	</tr>
</table>