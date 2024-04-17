<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171218105552 --%>
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
			<input type="hidden" name="pageID" value="ZZIL0030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="API Inbound Target I/F Master Maintenance">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<!-- Header Area Starts Here -->
								<table width="820" align="left" border="0">
									<col width="16">
									<col width="100">
									<col width="248">
									<col width="100">
									<col width="248">
									<col width="72">
									<col>
									<tr>
										<td></td>
										<td class="stab" height="24"><label>Table Name</label></td>
										<td class="stab"><ezf:inputText name="ezTableID" ezfName="ezTableID" value="MDB_INBD_INTFC_CONFIG" otherAttr=" size=\"28\""/></td>
										<td class="stab" height="24"><label>Interface ID(*)</label></td>
										<td class="stab"><ezf:inputText name="intfcId" ezfName="intfcId" otherAttr=" size=\"30\" ezftoupper=\"\""/></td>
										<td align="right" class="stab">
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr width="98%"></td>
						</tr>
					</table>
					<!-- Detail Header Area Starts Here-->
					<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="1100px" align="center" border="0" cellpadding="1" cellspacing="0">
									<col width="54">
									<col width="498">
									<col>
									<col width="17">
									<tr>
										<td align="center">
											<ezf:inputButton name="AddIF" value="Add I/F" htmlClass="pBtn6" />
										</td>
										<td></td>
										<td align="right">
												<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
													<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
													<jsp:param name="TableNm"     value="A" />
													<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
													<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
													<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
												</jsp:include>
										</td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- Detail Area Starts Here -->
					<table width="100%">
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<col valign="top" align="right">
									<col valign="top" align="left">
									<tr>
									<!-- ********* Left TBL : START ********* -->
										<td>
										<!-- ##### Left TBL - Top -->
											<table border="1" cellpadding="1" cellspacing="0" width="106">
												<col width="100">
												<tr height="32">
													<td class="pClothBs"></td>
												</tr>
											</table>
											<div id="LeftTBL" style="overflow-y:hidden;overflow-x:hidden;width:106px;height:443px;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
												<table border="1" cellpadding="1" cellspacing="0" id="A_LeftTBL">
													<col width="40" align="center">
													<col width="54" align="center">
													<tbody>
													<ezf:row ezfHyo="A">
														<tr id="id_row{EZF_ROW_NUMBER}" height="28">
															<td class="stab"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputButton name="Edit" value="Edit" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" /></td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr id="id_row{EZF_ROW_NUMBER}" height="28">
															<td class="stab"><input type="checkbox" class="" value="Y" ezfhyo="A"></td>
															<td><input type="button" class="pBtn3" value="Edit" ezfhyo="A"></td>
														</tr>
														<tr id="id_row{EZF_ROW_NUMBER}" height="28">
															<td class="stab"><input type="checkbox" class="" value="Y" ezfhyo="A"></td>
															<td><input type="button" class="pBtn3" value="Edit" ezfhyo="A"></td>
														</tr>
														<tr id="id_row{EZF_ROW_NUMBER}" height="28">
															<td class="stab"><input type="checkbox" class="" value="Y" ezfhyo="A"></td>
															<td><input type="button" class="pBtn3" value="Edit" ezfhyo="A"></td>
														</tr>
													</ezf:skip>
													</tbody>
												</table>
											</div>
										</td>
										<!-- ********* Left TBL : E N D ********* -->
										<!-- ********* Right TBL : START ********* -->
										<td>
											<div id="RightTBL_Top" style="overflow-y:hidden; height:; overflow-x:hidden; width:978px;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
												<table align="center" cellpadding="1" cellspacing="0" border="1" width="1570" style="table-layout:fixed">
													<col width="144">
													<col width="144">
													<col width="400">
													<col width="240">
													<col width="400">
													<col width="100">
													<col width="100">
													<tr align="center" height="32">
														<td class="pClothBs" align="center">Interface ID</td>
														<td class="pClothBs" align="center">Business API Name</td>
														<td class="pClothBs" align="center">Interface ID Description</td>
														<td class="pClothBs" align="center">Target Batch Job Name</td>
														<td class="pClothBs" align="center">Target Batch Job Description</td>
														<td class="pClothBs" align="center">Active Flag</td>
														<td class="pClothBs" align="center">Force Queuing Enable Flag</td>
													</tr>
												</table>
											</div>
											<div id="RightTBL" style="word-break:break-all; overflow-y:scroll; overflow-x:scroll; height:460px;width:995px" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
												<table cellpadding="1" cellspacing="0" border="1" width="1570" id="A_RightTBL" style="table-layout:fixed">
													<col width="144">
													<col width="144">
													<col width="400">
													<col width="240">
													<col width="400">
													<col width="100" align="center">
													<col width="100" align="center">
													<ezf:row ezfHyo="A">
														<tr height="28">
															<td class="stab"><ezf:label name="intfcId_A" ezfName="intfcId_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="stab"><ezf:label name="bizApiId_A" ezfName="bizApiId_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="stab"><ezf:label name="intfcIdDescTxt_A" ezfName="intfcIdDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="stab"><ezf:label name="trgtBatJobNm_A" ezfName="trgtBatJobNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="stab"><ezf:label name="trgtBatJobDescTxt_A" ezfName="trgtBatJobDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="stab"><ezf:label name="actvFlg_A" ezfName="actvFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
															<td class="stab"><ezf:label name="frceQueueEnblFlg_A" ezfName="frceQueueEnblFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr height="28">
															<td class="stab"><label ezfout ezfhyo="A">ZZZI0010</label></td>
															<td class="stab"><label ezfout ezfhyo="A">ZZZC015201</label></td>
															<td class="stab"><label ezfout ezfhyo="A">API Inbound Test API</label></td>
															<td class="stab"><label ezfout ezfhyo="A">S21SWD_SZZND9010</label></td>
															<td class="stab"><label ezfout ezfhyo="A">S21 CSA nightly batch job</label></td>
															<td class="stab"><label ezfout ezfhyo="A">Y</label></td>
															<td class="stab"><label ezfout ezfhyo="A">N</label></td>
														</tr>
														<tr height="28">
															<td class="stab"><label ezfout ezfhyo="A">ZZZI0010</label></td>
															<td class="stab"><label ezfout ezfhyo="A">ZZZC015201</label></td>
															<td class="stab"><label ezfout ezfhyo="A">API Inbound Test API</label></td>
															<td class="stab"><label ezfout ezfhyo="A">S21SWD_SZZND9010</label></td>
															<td class="stab"><label ezfout ezfhyo="A">S21 CSA nightly batch job</label></td>
															<td class="stab"><label ezfout ezfhyo="A">Y</label></td>
															<td class="stab"><label ezfout ezfhyo="A">N</label></td>
														</tr>
														<tr height="28">
															<td class="stab"><label ezfout ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCC</label></td>
															<td class="stab"><label ezfout ezfhyo="A">AAAAAAAAAABB</label></td>
															<td class="stab"><label ezfout ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFF</label></td>
															<td class="stab"><label ezfout ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCC</label></td>
															<td class="stab"><label ezfout ezfhyo="A">AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFF</label></td>
															<td class="stab"><label ezfout ezfhyo="A">Y</label></td>
															<td class="stab"><label ezfout ezfhyo="A">N</label></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
										<!-- ********* Right TBL : END ********* -->
									</tr>
								</table>
							</td>
						</tr>
					</table>
				<!-- ********* DATA ********* -->
				</div>
			</td>
		</tr>
	</table>
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
