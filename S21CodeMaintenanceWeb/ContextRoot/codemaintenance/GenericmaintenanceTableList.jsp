<%@ page pageEncoding="UTF-8" %>
<%@ page import="parts.common.EZDCommonFunc"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Generic Table View -->
<%-- JSP Include file --%>
<table width="100%" border="1" cellspacing="0" cellpadding="1">
		<tbody>
			<tr>
				<!-- Header -->
<c:if test="${screen.updatable}" >
				<th class="pClothBs" style="width:22px">&nbsp;</th>
				<th class="pClothBs" style="width:51px">&nbsp;</th>
</c:if>

<c:forEach var="col" items="${colInfos}" varStatus="ccount">
			<c:if test="${col.key}">
				<th class="pClothYs" nowrap>&nbsp;*
				<c:out value='${col.columnName}'/>&nbsp;
				
				<c:set var="comment" value="${columnComment[col.columnName]}"/>
				<c:if test="${comment != null && comment != ''}">
					<BR>(<c:out value='${comment}'/>)
				</c:if>

				</th>
			</c:if>
			<c:if test="${col.key != true}">
				<th class="pClothBs" nowrap>&nbsp
				<c:out value='${col.columnName}'/>&nbsp;
				
				<c:set var="comment" value="${columnComment[col.columnName]}"/>
				<c:if test="${comment != null && comment != ''}">
					<BR>(<c:out value='${comment}'/>)
				</c:if>
				
				</th>
			</c:if>
</c:forEach>
			</tr>
<c:forEach var="vals" items="${values}" varStatus="rowcount">
	<c:if test="${rowcount.index % 2 != 0}">
		<tr style="background-color:#F5F5F5;">
	</c:if>
	<c:if test="${rowcount.index % 2 == 0}">
		<tr style="background-color:#FFFAFA;">
	</c:if>
	<c:if test="${screen.updatable}" >
				<td class="stab" align=center>
					<input type="checkbox" value="True" name="deleteIndex_<c:out value='${rowcount.index}'/>" <c:if test="${delList[rowcount.index]}">checked</c:if>>
				</td>
				<td class="stab" align=center>
					<input class="cBtn" style="font-size:9pt;height:20;width:45" type="button" value="Edit" onclick="funcS21NEPostRecordEdit('<c:out value='${rowcount.index}'/>')">
				</td>
	</c:if>
	<!-- Value -->
		<c:forEach var="val" items="${vals}" varStatus="valcount">
				<td class="stab">
					<c:if test="${val != null && val != ''}">
						<c:set var="itemValue" value="${val}" />
						 <%= EZDCommonFunc.toHTMLString((String)pageContext.getAttribute("itemValue")) %>
						<c:remove var="itemValue" />
					</c:if>
					<c:if test="${val == null || val == ''}">
								<br>
					</c:if>
					<c:if test="${colInfos[valcount.index].key}">
								<input type="hidden" value="<c:out value='${val}'/>"
									name="NE_EZM_KEY_<c:out value='${colInfos[valcount.index].columnName}'/>_<c:out value='${rowcount.index}'/>" >
					</c:if>
				</td>
	</c:forEach>
</c:forEach>
		</tbody>
	</table>

	<input type="hidden" name="recordCount" value="<c:out value='${recordCount}'/>">
