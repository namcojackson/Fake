<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.ap.S21CMSearchScreenBean" %>
<%-- JSP initialize --%>
<%
	S21NEContainerDataBean bean = (S21NEContainerDataBean) request.getAttribute("s21nevo");
	S21CMSearchScreenBean screen = (S21CMSearchScreenBean) bean.getResponse();
	request.setAttribute("screen", screen);
	request.setAttribute("colInfos", screen.getColInfos());
	request.setAttribute("values", screen.getValues());
	request.setAttribute("delList", screen.getDelList());
	request.setAttribute("recordCount", "" + screen.getValues().size());
%>
<script type="text/javascript">
<!--
function funcOnload() {
}
// -->
</script>

<!-- Code Maintenance Delete List Start -->

<input type="hidden" name="S21NE_SCREEN_ID" value="cm_delete">
<input type="hidden" name="S21NE_REQ_ID" value="">

<input type="hidden" name="selectTable" value="<c:out value='${screen.tableName}'/>">

<table cellpadding="0" width="95%">
	<tr>
		<td width="5%">
		</td>
		<td width="85%">

<table border="0" width="100%">
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td class="stab" width="120px">Table Name</td>
						<td class="pOut" width="640px"><c:out value='${screen.tableName}'/></td>
					</tr>
					<tr>
						<td class="stab" width="120px">Long Name</td>
						<td class="pOut" width="640px"><c:out value='${screen.tableLongName}'/><br></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" width="100%">
				<div style="OVERFLOW: scroll; WIDTH: 1000px; HEIGHT: 520px">
<%-- Inlucde Table Value List --%>
<%@ include file="CodemaintenanceTableList.jsp" %>
				</div>
			</td>
		</tr>
		<tr>
			<td align=right>
				<input class="cBtn" type="button" value="Submit" onclick="funcS21NEPost('deleteExec')">
				&nbsp;
				<input class="cBtn" type="button" value="Cancel" onclick="funcS21NEPostScreenChange('cm_search', 'selectTable')">
			</td>
		</tr>
</table>

		</td>
	</tr>
</TABLE>

