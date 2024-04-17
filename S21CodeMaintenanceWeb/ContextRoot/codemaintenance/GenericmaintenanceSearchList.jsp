<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean" %>
<%@ page import="com.canon.cusa.s21.framework.generictable.ap.S21CMSearchScreenBean" %>
<%@ page import="com.canon.cusa.s21.framework.generictable.ap.S21CMSecurityAccessor" %>
<%-- JSP initialize --%>
<%
	S21NEContainerDataBean bean = (S21NEContainerDataBean) request.getAttribute("s21nevo_kim");
	S21CMSearchScreenBean screen = (S21CMSearchScreenBean) bean.getResponse();
	request.setAttribute("colInfos", screen.getColInfos());
	request.setAttribute("screen", screen);
	request.setAttribute("values", screen.getValues());
	request.setAttribute("delList", screen.getDelList());
	request.setAttribute("recordCount", "" + screen.getValues().size());
	request.setAttribute("editable", new Boolean(S21CMSecurityAccessor.getInstance().isTableWrite(screen.getTableName())));
%>
<script type="text/javascript">
<!--
function funcS21NEPostRecordEdit(index) {
    document.F1.S21NE_REQ_ID.value = "editRecordInit";
    document.F1.editIndex.value = index;
    funcSubmitUrlenc();
}

function funcS21NEPostPage(req, page) {
    document.F1.S21NE_REQ_ID.value = req;
    document.F1.nextPage.value = parseInt(document.F1.viewPage.value) + parseInt(page);
    funcSubmitUrlenc();
}
// -->
</script>
	<input type="hidden" value="" name="editIndex">

	<!-- Search Result -->
	<table width="95%">
		<tbody>
			<tr>
				<td width="100%">
					<div style="OVERFLOW: scroll; width: 812px; HEIGHT: 508px">
<%-- Inlucde Table Value List --%>
<%@ include file="GenericmaintenanceTableList.jsp" %>
					</div>
				</td>
			</tr>
			<tr>
				<td width="100%" align=right>
					<table width="100%">
						<tr>
							<td align=left width="45%">
								Page: <c:out value='${screen.page}'/>/<c:out value='${screen.pageAll}'/>
								<input type="hidden" name="viewPage" value="<c:out value='${screen.page}'/>" >
								<input type="hidden" name="nextPage" value="1" >
							</td>
							<td width="5%">
							</td>
							<td align=right width="45%">
								Total Record: <c:out value='${screen.allRecord}'/>
							</td>
							<td width="10%">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td align=left width="50%">
								<input class="cBtn" type="button" value="Prev" onclick="funcS21NEPostPage('<c:out value='${s21nevo_kim.reqID}'/>', '-1')" <c:if test="${screen.page==1}">disabled</c:if>>
								<input class="cBtn" type="button" value="Next" onclick="funcS21NEPostPage('<c:out value='${s21nevo_kim.reqID}'/>', '1')" <c:if test="${screen.page==screen.pageAll}">disabled</c:if>>
							</td>
							<td align=right width="50%">
<c:if test="${screen.updatable}" >
								<input class="cBtn" type="button" value="&nbsp;Add&nbsp;" onclick="funcS21NEPostRecordEdit('-1')" <c:if test="${!editable}">disabled</c:if> >
								<input class="cBtn" type="button" value="Delete" onclick="funcS21NEPost('deleteSelect')" <c:if test="${!editable}">disabled</c:if> >
</c:if>
								<input class="cBtn" type="button" value="Return" onclick="returnMenu()">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
