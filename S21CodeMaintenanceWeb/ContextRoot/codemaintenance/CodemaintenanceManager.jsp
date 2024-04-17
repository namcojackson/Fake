<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.ap.S21CMSearchScreenBean" %>
<%-- JSP initialize --%>
<%
	S21NEContainerDataBean bean = (S21NEContainerDataBean) request.getAttribute("s21nevo");
	S21CMSearchScreenBean screen = (S21CMSearchScreenBean) bean.getResponse();
	List tables = new ArrayList();
	List sels = new ArrayList();
	if (screen != null && screen.getTableNames() != null) {
		for (int i = 0; i < screen.getTableNames().size(); i++) {
			String tableOpt = (String) screen.getTableNames().get(i);
			if (screen.getTableLongNames().get(i) != null
				&& !((String) screen.getTableLongNames().get(i)).equals("")) {
				tableOpt += " - " + screen.getTableLongNames().get(i);
			}
			tables.add(tableOpt);
			if (screen.getTableName() != null && screen.getTableName().equals(screen.getTableNames().get(i))) {
				sels.add("selected");
			} else {
				sels.add("");
			}
		}
	}
	request.setAttribute("screen", screen);
	request.setAttribute("tableListLong", tables);
	request.setAttribute("tableList", screen.getTableNames());
	request.setAttribute("tableListSel", sels);
%>
<script type="text/javascript">
<!--
function funcOnload() {
    document.F1.selectTable.focus();
}
// -->
</script>

<input type="hidden" name="S21NE_SCREEN_ID" value="cm_manager">
<input type="hidden" name="S21NE_REQ_ID" value="init">

<table cellpadding="0" width="100%">
	<tr>
		<td width="5%">
		</td>
		<td class="stab">
			Code Table Cache Clear
		</td>
	</tr>
	<tr>
		<td width="5%">
		</td>
		<td class="stab" width="85%">
&nbsp;Table Name&nbsp;
<select name="selectTable" style="width:350">
<c:forEach var="tl" items="${tableListLong}" varStatus="tcount">
	<option value="<c:out value='${tableList[tcount.index]}'/>" <c:out value='${tableListSel[tcount.index]}'/>><c:out value='${tl}'/>
</c:forEach>
</select>
<input class="cBtn" type="button" value="Clear Cache" onclick="funcS21NEPost('clearCacheTable')">
<br>
<hr>
		</td>
	</tr>
	<tr>
		<td>
		</td>
		<td>
			<input class="cBtn" style="width:150" type="button" value="Clear Cache All" onclick="funcS21NEPost('clearAllTable')">
		</td>
	</tr>
	<tr><td><BR></td></tr>
	<tr>
		<td>
		</td>
		<td>
			<input class="cBtn" style="width:150" type="button" value="Clear Security Cache" onclick="funcS21NEPost('clearSecurityCache')">
		</td>
	</tr>
	<tr height=469px>
		<td>
		</td>
		<td>
		</td>
	</tr>
	<tr>
		<td>
		</td>
		<td align=right>
			<input class="cBtn" type="button" value="Return" onclick="returnMenu()" style="margin-right:12px">
		</td>
	</tr>
</table>
