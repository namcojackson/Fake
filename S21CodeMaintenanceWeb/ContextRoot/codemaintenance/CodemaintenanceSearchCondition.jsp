<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.ap.S21CMSearchScreenBean" %>
<%@ page import="com.canon.cusa.s21.framework.codetable.ap.S21CMSecurityAccessor" %>
<script type="text/javascript">
<!--

function funcS21NEUpload(code) {
    document.F1.S21NE_REQ_ID.value = code;
    document.F1.enctype="multipart/form-data";
    document.F1.encoding ="multipart/form-data"; // IE Problem
    oldAction = document.F1.action;

    <%-- CSA QC#58722 Mz.Takahashi Mode Start --%>
    document.F1.action = document.F1.action + "?file_upload=true"
    document.activeElement.disabled = true;
    <%-- document.F1.action="S21NEUploadServlet"; --%>
    <%-- CSA QC#58722 Mz.Takahashi Mode End --%>

	try {
    	document.F1.submit();
    	
    } catch(e) {
    	if (e.number == -2147024891) {
	    	alert(e.description);
		}
	    document.activeElement.disabled = false;
	    document.F1.action = oldAction;
    }
}


// -->
</script>

	<%-- JSP initialize --%>
	<%
		S21NEContainerDataBean bean = (S21NEContainerDataBean) request.getAttribute("s21nevo");
		S21CMSearchScreenBean screen = (S21CMSearchScreenBean) bean.getResponse();

		request.setAttribute("colInfos", screen.getColInfos());
		request.setAttribute("condition", screen.getCondition());
		if (screen.getConditionOld() == null) {
			request.setAttribute("conditionOld", screen.getCondition());
		} else {
			request.setAttribute("conditionOld", screen.getConditionOld());
		}

		List longNames = new ArrayList();
		if (screen.getTableLongName() != null
			&& screen.getTableLongName().length() > 32) {
			String name = screen.getTableLongName();
			while (name != null) {
				if (name.length() <= 32) {
					longNames.add(name);
					name = null;
				} else {
					longNames.add(name.substring(0, 32));
					name = name.substring(32);
				}
			}
		} else {
			longNames.add(screen.getTableLongName());
		}
		request.setAttribute("longNames", longNames);

		request.setAttribute("editable", new Boolean(S21CMSecurityAccessor.getInstance().isTableWrite(screen.getTableName())));
	%>

<div style="OVERFLOW: auto; width: 300px; HEIGHT: 556px">
	<table width="75%">
		<tbody>
			<tr>
				<td>
					<table width="100%">
						<tbody>
							<tr>
								<td class="stab" width="90px" nowrap>Table Name</td>
								<td class="pOut" width="180px"><c:out value='${screen.tableName}'/></td>
							</tr>
							<tr>
								<td class="stab" width="90px" nowrap>Long Name</td>
								<td class="pOut" width="180px">
<c:forEach var="longNm" items="${longNames}">
									<c:out value='${longNm}'/><br>
</c:forEach>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td class="stab">Search Condition </td>
			</tr>
			<tr>
				<td>
					<table width="100%">
						<tbody>
<c:forEach var="col" items="${colInfos}" varStatus="ccount">
	<c:if test="${col.key}">
							<tr>
								<td class="stab">&nbsp;<c:out value='${col.columnName}'/></td>
							</tr>
							<tr>
								<td>
									<input type="text" value="<c:out value='${condition[col.columnName]}'/>" name="NE_EZM_<c:out value='${col.columnName}'/>" maxlength=<c:out value='${col.inputLength}'/> style="margin-left:32px;width:240px">
									<input type="hidden" value="<c:out value='${conditionOld[col.columnName]}'/>" name="NE_EZM_OLD_<c:out value='${col.columnName}'/>" >
								</td>
							</tr>
	</c:if>
</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td align=right><input class="cBtn" type="button" value="Search" onclick="funcS21NEPost('searchCondition')"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
<c:if test="${screen.updatable}" >
			<tr>
				<td class="stab">CSV Upload / Download</td>
			</tr>
			<tr>
				<td>
					<table width="100%">
						<tbody>
							<tr>
								<td>&nbsp;<input name="uploadFile" type="file" value="" <c:if test="${!editable}">disabled</c:if> style="margin-left:24px;width:240px"></td>
							</tr>
							<tr>
								<td align=right>
									<input class="cBtn" id="fileupload" type="button" value="Upload" onclick="funcS21NEUpload('upload')" <c:if test="${!editable}">disabled</c:if> >
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
</c:if>
			<tr>
				<td>
					<br>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%">
						<tbody>
							<tr>
								<td align=right>
									<input class="cBtn" type="button" value="Download" onclick="funcS21NEPost('download');this.disabled=false;">
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</div>
