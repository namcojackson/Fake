<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.ap.S21CMEditRecordScreenBean" %>
<%@ page import="parts.common.EZDCommonFunc"%>
<%-- JSP initialize --%>
<%
	S21NEContainerDataBean bean = (S21NEContainerDataBean) request.getAttribute("s21nevo");
	S21CMEditRecordScreenBean screen = (S21CMEditRecordScreenBean) bean.getResponse();
	request.setAttribute("screen", screen);
	request.setAttribute("colInfos", screen.getColInfos());
	request.setAttribute("focus", "");
%>
<!-- Code Maintenance Edit Start -->
<input type="hidden" name="S21NE_SCREEN_ID" value="cm_edit">
<input type="hidden" name="S21NE_REQ_ID" value="">

<input type="hidden" name="selectTable" value="<c:out value='${screen.tableName}'/>">
<input type="hidden" name="editType" value="<c:out value='${screen.type}'/>">

<table cellpadding="0" width="95%">
	<tr>
		<td width="5%">
		</td>
		<td width="85%">

<table border="0" width="100%">
		<tr>
			<td>
				<div style="OVERFLOW: auto; WIDTH: 1000px; HEIGHT: 56px">
				<table width="100%">
								<tr>
									<td class="stab" nowrap width="120px">Table Name</td>
									<td class="pOut" nowrap width="780px"><c:out value='${screen.tableName}'/></td>
								</tr>
								<tr>
									<td class="stab" nowrap width="120px">Long Name</td>
									<td class="pOut" nowrap width="780px"><c:out value='${screen.tableLongName}'/><br></td>
								</tr>
				</table>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" width="100%">
				<div style="OVERFLOW: scroll; WIDTH: 1000px; HEIGHT: 520px">
				<TABLE border="1" cellspacing="0" cellpadding="1" width="100%">
					<tr>
						<th class="pClothBs" width=250px>Column</th>
						<th class="pClothBs" width=250px>Disp</th>
						<th class="pClothBs" width=500px>Value</th>
					</tr>

<c:forEach var="col" items="${colInfos}" varStatus="ccount">
	<c:if test="${!col.ezd}">
		<c:choose>
			<c:when test="${screen.confirm}">
					<tr>
						<td class="stab">&nbsp;<c:out value='${col.columnName}'/>&nbsp;</td>
						<td class="stab">&nbsp;<c:out value='${col.dispName}'/>&nbsp;</td>
						<td class="stab">
						<c:if test="${screen.msgMap[col.columnName] != null && screen.msgMap[col.columnName] != ''}">
							<!--<c:out value='${screen.msgMap[col.columnName]}'/>-->
							<c:set var="itemValue" value="${screen.msgMap[col.columnName]}" />
							<%= EZDCommonFunc.toHTMLString((String)pageContext.getAttribute("itemValue")) %>
							<c:remove var="itemValue" />
						</c:if>
						<c:if test="${screen.msgMap[col.columnName] == null || screen.msgMap[col.columnName] == ''}">
							<br>
						</c:if>
						<input type="hidden" value="<c:out value='${screen.msgMap[col.columnName]}'/>" name="NE_EZM_<c:out value='${col.columnName}'/>">
						</td>
					</tr>
			</c:when>
			<c:when test="${screen.type=='EDIT'}">
				<c:if test="${col.key}">
					<tr style="height:28px;">
						<td class="stab">&nbsp;<c:out value='${col.columnName}'/>&nbsp;</td>
						<td class="stab">&nbsp;<c:out value='${col.dispName}'/>&nbsp;</td>
						<td class="stab">
						<c:out value='${screen.msgMap[col.columnName]}'/>
						<input type="hidden" value="<c:out value='${screen.msgMap[col.columnName]}'/>" name="NE_EZM_<c:out value='${col.columnName}'/>">
						</td>
					</tr>
				</c:if>
				<c:if test="${!col.key}">
					<tr style="height:28px;">
						<td class="stab">&nbsp;<c:out value='${col.columnName}'/>&nbsp;</td>
						<td class="stab">&nbsp;<c:out value='${col.dispName}'/>&nbsp;</td>
						<td class="stab"><input type="text" value="<c:out value='${screen.msgMap[col.columnName]}'/>" size="80" name="NE_EZM_<c:out value='${col.columnName}'/>" maxlength="<c:out value='${col.inputLength}'/>"></td>
					</tr>

					<c:if test="${focus == ''}">
						<c:set var="focus" value="${col.columnName}"/>
					</c:if>
				</c:if>
			</c:when>
			<c:when test="${screen.type=='ADD'}">
					<tr>
						<td class="stab">&nbsp;<c:out value='${col.columnName}'/>&nbsp;</td>
						<td class="stab">&nbsp;<c:out value='${col.dispName}'/>&nbsp;</td>
						<td class="stab"><input type="text" value="<c:out value='${screen.msgMap[col.columnName]}'/>" size="80" name="NE_EZM_<c:out value='${col.columnName}'/>" maxlength="<c:out value='${col.inputLength}'/>"></td>
					</tr>

					<c:if test="${focus == ''}">
						<c:set var="focus" value="${col.columnName}"/>
					</c:if>
			</c:when>
		</c:choose>
	</c:if>

</c:forEach>
				</table>
				</div>
			</td>
		</tr>
		<tr>
			<td align=right>
<c:choose>
	<c:when test="${screen.confirm}">
				<input class="cBtn" type="button" value="Submit" onclick="funcS21NEPost('updateRecord')">
				&nbsp;
				<input class="cBtn" type="button" value="Return" onclick="funcS21NEPost('reedit')">
	</c:when>
	<c:when test="${screen.type=='EDIT'}">
				<input class="cBtn" type="button" value="&nbsp;Next&nbsp;" onclick="funcS21NEPost('confirmRecord')">
	</c:when>
	<c:when test="${screen.type=='ADD'}">
				<input class="cBtn" type="button" value="&nbsp;Next&nbsp;" onclick="funcS21NEPost('confirmRecord')">
	</c:when>
</c:choose>
				&nbsp;
				<input class="cBtn" type="button" value="Cancel" onclick="funcS21NEPostScreenChange('cm_search', 'selectTable')">
			</td>
		</tr>
</table>
		</td>
	</tr>
</TABLE>

<script type="text/javascript">
<!--
function funcOnload() {
<c:if test="${focus != ''}">
    document.F1.NE_EZM_<c:out value='${focus}'/>.focus();
</c:if>
}
// -->
</script>

<!-- Code Maintenance Edit End -->