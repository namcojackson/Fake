<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean" %>
<%@ page import="com.canon.cusa.s21.framework.generictable.ap.S21CMSearchScreenBean" %>
<%@ page import="com.canon.cusa.s21.framework.generictable.ap.S21CMRequestDef" %>
<%-- JSP initialize --%>
<%
	S21NEContainerDataBean bean = (S21NEContainerDataBean) request.getAttribute("s21nevo_kim");
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
	
	List searchTypeNames = new ArrayList();
	List searchTypeSels = new ArrayList();
	
	searchTypeNames.add(S21CMRequestDef.REQ_SEARCH_TABLE_NAME);
	searchTypeNames.add(S21CMRequestDef.REQ_SEARCH_TABLE_LONGNAME);
	searchTypeNames.add(S21CMRequestDef.REQ_SEARCH_TABLE_COMMENT);
	
	for(int i=0; i<searchTypeNames.size(); i++) {
		if (screen.getSearchType() != null && searchTypeNames.get(i).equals(screen.getSearchType())) {
			searchTypeSels.add("selected");
		} else {
			searchTypeSels.add("");
		}
	}

	request.setAttribute("exp", bean.getExp());
	request.setAttribute("screen", screen);
	request.setAttribute("tableListLong", tables);
	request.setAttribute("tableList", screen.getTableNames());
	request.setAttribute("tableListSel", sels);
	request.setAttribute("searchType", searchTypeNames);
	request.setAttribute("searchTypeSel", searchTypeSels);
	
%>
<c:if test="${screen.tableSearchList}" >
<script type="text/javascript">
<!--
function funcOnload() {
    document.F1.searchTable.focus();
}
// -->
</script>
</c:if>
<c:if test="${!screen.tableSearchList}" >
<script type="text/javascript">
<!--
function funcOnload() {
    document.F1.selectTable.focus();
}
// -->
</script>
</c:if>

<input type="hidden" name="S21NE_SCREEN_ID" value="cm_search">
<input type="hidden" name="S21NE_REQ_ID" value="cm_search">

<table cellpadding="0" width="100%">
	<tr>
		<td width="16px">
		</td>
		<td class="stab">

&nbsp;Table Name&nbsp;
<select name="selectTable" style="width:350">
<c:forEach var="tl" items="${tableListLong}" varStatus="tcount">
	<option value="<c:out value='${tableList[tcount.index]}'/>" <c:out value='${tableListSel[tcount.index]}'/>><c:out value='${tl}'/>
</c:forEach>
</select>
<input class="cBtn" type="button" value="Select" onclick="document.F1.showTable.value = '';funcS21NEPost('selectTable')">
&nbsp;
&nbsp;
<input type="text" name="searchTable" maxlength="128" style="width:240" value="<c:out value='${screen.tableSearchKey}'/>">
<input class="cBtn" type="button" value="Search" onclick="funcS21NEPost('searchTable')">
<!--
<input class="stab" type="checkbox" name="searchLongName" value="true" <c:if test="${screen.searchLongName}">checked</c:if>>Long Name
-->
<select name="searchType">
<c:forEach var="st" items="${searchType}" varStatus="tcount">
	<c:if test="${st == '0'}">
		<option value="0" <c:out value='${searchTypeSel[tcount.index]}'/>>Table Name</option>
	</c:if>
	<c:if test="${st == '1'}">
		<option value="1" <c:out value='${searchTypeSel[tcount.index]}'/>>Long Name</option>
	</c:if>
	<c:if test="${st == '2'}">
		<option value="2" <c:out value='${searchTypeSel[tcount.index]}'/>>Comment</option>
	</c:if>
</c:forEach>
</select>
<input type="text" value="duummy" disabled style="display:none;height:0;width:0">
<input type="hidden" name="showTable" value="<c:out value='${screen.tableName}'/>">
<input type="hidden" name="condSearch" value="<c:out value='${screen.condSearch}'/>">
<br>
<hr>

		</td>
	</tr>
</TABLE>
<input type="hidden" name="viewTable" value="<c:out value='${screen.tableName}'/>">

<table width="100%" cellpadding="0">
	<tbody>

<c:if test="${exp == null}" >

	<c:if test="${screen.tableSearchList}" >
		<tr>
			<td>
				<jsp:include page="GenericmaintenanceSearchTable.jsp" flush="true"/>
			</td>
		</tr>
	</c:if>

	<c:if test="${!screen.tableSearchList}" >
		<tr>
			<td width="16px">
			</td>
			<td valign="top">
				<!-- Search Condition  -->
				<c:if test="${screen.showCondition}" >
					<jsp:include page="GenericmaintenanceSearchCondition.jsp" flush="true"/>
				</c:if>
			</td>

			<td width="828px" valign="top">
				<!-- Search Result  -->
				<c:if test="${screen.showResult}" >
					<jsp:include page="GenericmaintenanceSearchList.jsp" flush="true"/>
				</c:if>
			</td>
		</tr>
		<c:if test="${!screen.showResult}" >
		<tr height=532px>
			<td>
			</td>
		</tr>
		<tr>
			<td width="100%" align=right>
				<input class="cBtn" type="button" value="Return" onclick="returnMenu()" style="margin-right:9px;">
			</td>
		</tr>
		</c:if>
	</c:if>

</c:if>

<c:if test="${exp != null}" >
		<tr>
			<td>
				<jsp:include page="GenericmaintenanceSystemError.jsp" flush="true"/>
			</td>
		</tr>
</c:if>

	</tbody>
</table>
