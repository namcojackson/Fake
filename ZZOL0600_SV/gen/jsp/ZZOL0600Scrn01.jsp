<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20110126043348 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZZOL0600Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Abend Log Details">
<center>

			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

	<div class="pTab_BODY">

		<table width="90%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" colspan="4">&nbsp;&nbsp;</td>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>Global Company Code</b></label></td>
				<td class="stab" align="left" width="40%"><ezf:label name="glblCmpyCd_B" ezfName="glblCmpyCd_B" /></td>
				<td class="stab" align="left" width="10%"><label><b>Online/Batch<b></label></td>
				<td class="stab" align="left" width="25%"><ezf:label name="xxAlmsOnlBatFlgTxt_B" ezfName="xxAlmsOnlBatFlgTxt_B" /></td>
			</tr>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>Timestamp<b></label></td>
				<td class="stab" align="left" width="40%"><ezf:label name="xxDtTm_B" ezfName="xxDtTm_B" /></td>
				<td class="stab" align="left" width="10%"><label><b>Timezone<b></label></td>
				<td class="stab" align="left" width="25%"><ezf:label name="ezInTimeZone_B" ezfName="ezInTimeZone_B" /></td>
			</tr>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>Message ID<b></label></td>
				<td class="stab" align="left" width="40%"><ezf:label name="almsMsgId_B" ezfName="almsMsgId_B" /></td>
				<td class="stab" align="left" width="10%"><label><b>Severity Level<b></label></td>
				<td class="stab" align="left" width="25%"><ezf:label name="almsSevtyLvlTxt_B" ezfName="almsSevtyLvlTxt_B" /></td>
			</tr>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>UUID<b></label></td>
				<td class="stab" align="left" width="40%"><ezf:label name="almsUnivsUniqId_B" ezfName="almsUnivsUniqId_B" /></td>
				<td class="stab" align="left" width="10%"><label><b>User ID<b></label></td>
				<td class="stab" align="left" width="25%"><ezf:label name="almsUsrId_B" ezfName="almsUsrId_B" /></td>
			</tr>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>Program ID<b></label></td>
				<td class="stab" align="left" width="40%"><ezf:label name="almsPgmId_B" ezfName="almsPgmId_B" /></td>
				<td class="stab" align="left" width="10%"><label><b>System<b></label></td>
				<td class="stab" align="left" width="25%"><ezf:label name="almsSysNm_B" ezfName="almsSysNm_B" /></td>
			</tr>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>Host Name<b></label></td>
				<td class="stab" align="left" width="40%"><ezf:label name="almsHostNm_B" ezfName="almsHostNm_B" /></td>
				<td class="stab" align="left" width="10%"><label><b>JVM<b></label></td>
				<td class="stab" align="left" width="25%"><ezf:label name="almsJvmNm_B" ezfName="almsJvmNm_B" /></td>
			</tr>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>Batch Process ID<b></label></td>
				<td class="stab" align="left" width="40%"><ezf:label name="almsBatProcId_B" ezfName="almsBatProcId_B" /></td>
				<td class="stab" align="left" width="10%"><label><b>Job ID<b></label></td>
				<td class="stab" align="left" width="25%"><ezf:label name="almsJobId_B" ezfName="almsJobId_B" /></td>
			</tr>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>Framework Version<b></label></td>
				<td class="stab" align="left" width="40%"><ezf:label name="almsFwkVrsnTxt_B" ezfName="almsFwkVrsnTxt_B" /></td>
				<td class="stab" align="left" width="10%"><label><b>Application Version<b></label></td>
				<td class="stab" align="left" width="25%"><ezf:label name="almsAppVrsnTxt_B" ezfName="almsAppVrsnTxt_B" /></td>
			</tr>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>Release Version<b></label></td>
				<td class="stab" align="left" width="40%" ><ezf:label name="almsRelVrsnTxt_B" ezfName="almsRelVrsnTxt_B" /></td>
				<td class="stab" align="left" width="10%"><label><b>Databse Version<b></label></td>
				<td class="stab" align="left" width="25%" ><ezf:label name="almsDbVrsnTxt_B" ezfName="almsDbVrsnTxt_B" /></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td class="stab" align="left" width="15%"><label><b>Abend Message<b></label></td>
				<td class="stab" align="left" colspan="3"><ezf:textArea name="almsMsgTxt_B" ezfName="almsMsgTxt_B" otherAttr=" rows=\"2\" cols=\"115\""/></td>
			</tr>
			<tr>
				<td class="stab" align="left" colspan="4"><label><b>Abend Log Details<b></label></td>
			</tr>
			<tr>
				<td class="stab" align="left" colspan="4"><ezf:textArea name="xxLogDtlTxt_B" ezfName="xxLogDtlTxt_B" otherAttr=" rows=\"30\" cols=\"140\""/></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="left" width="15%" class="cBtnCloth"><ezf:inputButton name="btn11" value="btn11" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn12" otherAttr=" id=\"btn11\""/></td>
			</tr>
		</table>
	</div>
</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroBottomScroll() {
		var bottomTBL = document.getElementById( 'bottomTBL' );
		var topTBL    = document.getElementById( 'topTBL'    );
		topTBL.scrollLeft = bottomTBL.scrollLeft;
	}
</script>


<%-- **** Task specific area ends here **** --%>
