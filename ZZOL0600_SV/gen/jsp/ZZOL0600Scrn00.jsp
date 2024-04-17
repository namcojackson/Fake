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
			<input type="hidden" name="pageID" value="ZZOL0600Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Abend Log Viewer">
<center>

			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<div class="pTab_BODY">

			<table width="1100px" align="center">
				<tbody>
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CompanyLookup" >Global Company CD</ezf:anchor></td>
							<td><ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="XXX" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr></tr>
						<tr>
							<td class="stab" align="right"><label>Online/Batch</label></td>
							<td>
								<ezf:select name="almsOnlBatFlg_F2" ezfName="almsOnlBatFlg_F2" ezfCodeName="almsOnlBatFlg_F1" ezfDispName="xxAlmsOnlBatFlgTxt" />
							</td>
							<td class="stab" align="right"><label >Message ID</label></td>
							<td><ezf:inputText name="almsMsgId" ezfName="almsMsgId" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label >User ID</label></td>
							<td><ezf:inputText name="almsUsrId" ezfName="almsUsrId" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label >Job ID</label></td>
							<td><ezf:inputText name="almsJobId" ezfName="almsJobId" otherAttr=" size=\"22\""/></td>
						</tr>
						<tr></tr>
						<tr>
							<td class="stab" align="right"><label>UUID</label></td>
							<td><ezf:inputText name="almsUnivsUniqId" ezfName="almsUnivsUniqId" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label>Batch Process ID</label></td>
							<td><ezf:inputText name="almsBatProcId" ezfName="almsBatProcId" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label>Program ID</label></td>
							<td><ezf:inputText name="almsPgmId" ezfName="almsPgmId" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label>Java Virtual Machine</label></td>
							<td><ezf:inputText name="almsJvmNm" ezfName="almsJvmNm" otherAttr=" size=\"22\""/></td>
						</tr>
						<tr></tr>
						<tr>
							<td class="stab" align="right"><label>Framework Version</label></td>
							<td><ezf:inputText name="almsFwkVrsnTxt" ezfName="almsFwkVrsnTxt" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label>Application Version</label></td>
							<td><ezf:inputText name="almsAppVrsnTxt" ezfName="almsAppVrsnTxt" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label>Release Version</label></td>
							<td><ezf:inputText name="almsRelVrsnTxt" ezfName="almsRelVrsnTxt" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label>Database Version</label></td>
							<td><ezf:inputText name="almsDbVrsnTxt" ezfName="almsDbVrsnTxt" otherAttr=" size=\"22\""/></td>
						</tr>
						<tr></tr>
						<tr>
							<td class="stab" align="right"><label>System</label></td>
							<td><ezf:inputText name="almsSysNm" ezfName="almsSysNm" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label>Host Name</label></td>
							<td><ezf:inputText name="almsHostNm" ezfName="almsHostNm" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label>Abend Message</label></td>
							<td><ezf:inputText name="almsMsgTxt" ezfName="almsMsgTxt" otherAttr=" size=\"22\""/></td>
							<td class="stab" align="right"><label>Severity Level</label></td>
							<td><ezf:inputText name="almsSevtyLvlTxt" ezfName="almsSevtyLvlTxt" otherAttr=" size=\"22\""/></td>
						</tr>
					</tbody>
				</table>
					<table align="center" border="0"width="1100px">
						<col width="50">
						<col width="35">
						<col width="45">
						<col width="25">
						<col width="15">
						<col width="15">
						<col width="15">
						<col width="15">
						<col width="15">
						<col width="15">
						<col width="25">
						<col width="20">
						<col width="120">
						<col width="95">
						<col width="15">
						<tr>

							<td class="stab"><label>Log Date Time</label></td>
							<td class="stab" align="right"><label>From</label></td>
							<td><ezf:inputText name="xxFromDt" ezfName="xxFromDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt', 4);" >
							</td>
							<td>
								<ezf:select name="xxHrs_FS" ezfName="xxHrs_FS" ezfBlank="1" ezfCodeName="xxHrs_FC" ezfDispName="xxHrs_FD" />
							</td>
							<td>:</td>
							<td>
								<ezf:select name="xxMn_FS" ezfName="xxMn_FS" ezfBlank="1" ezfCodeName="xxMn_FC" ezfDispName="xxMn_FD" /></td>
							</td>
							<td class="stab" align="right"><label>To</label></td>
							<td><ezf:inputText name="xxToDt" ezfName="xxToDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxToDt', 4);" >
							</td>
							<td>
								<ezf:select name="xxHrs_TS" ezfName="xxHrs_TS" ezfBlank="1" ezfCodeName="xxHrs_TC" ezfDispName="xxHrs_TD" />
							</td>
							<td>:</td>
							<td>
								<ezf:select name="xxMn_TS" ezfName="xxMn_TS" ezfBlank="1" ezfCodeName="xxMn_TC" ezfDispName="xxMn_TD" />
							</td>
							<td>
								<ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" />
							</td>
							<td class="stab">
								<label>Except Typical Message</label>
							</td>
							<td>
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" />
							</td>
							<td></td>
						</tr>
					</table>
				<hr>

				<%-- Pagenation --%>
				<table width="1075">
					<tr align="right">
						<td>
							<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
							</jsp:include>
						</td>
					</tr>
				</table>

     	<table width="100%">
    		<tr>
    			<td>

     				<table width="100%" border="0" cellpadding="0" cellspacing="0">
    					<col valign="top" align="right">
    					<col valign="top" align="left">
    					<tr>
  <%-- ++++++++++++++++++++++++++++++++++++++++++++++++++ Left TBL : START ++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
    					<td>
							<%-- ##### Left TBL - Top --%>
							<table border="1" cellpadding="1" cellspacing="0" width="80">
								<col width="80" align="left">
								<tr>
									<td class="pClothBs" align="center">View More Details</td>
								</tr>
							</table>

							<div id="LeftTBL" style="overflow-y:hidden;overflow-x:hidden;width:80;height:293;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
								<table border="1" cellpadding="1" cellspacing="0" id="A_LeftTBL">
									<col width="80" align="center">
									<tbody>
									<ezf:row ezfHyo="A">
										<tr>
											<td align="left"><ezf:inputButton name="View" value="View" ezfHyo="A" ezfArrayNo="0" htmlClass="cBtn" /></td>
										</tr>
									</ezf:row>
									</tbody>
								</table>
							</div>
					</td>
					<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++ Left TBL : E N D ++++++++++++++++++++++++++++++++++++++++++++++++++ --%>

					<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++ Right TBL : START ++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
					<td>
						<div id="RightTBL_Top" style="overflow-y:hidden; height:; overflow-x:hidden; width:1030;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ) );">
							<table align="center" cellpadding="1" cellspacing="0" border="1" width="3400" style="table-layout:fixed">
								<col width="180" align="left">
								<col width="90" align="left">
								<col width="90" align="left">
								<col width="260" align="left">
								<col width="90" align="left">
								<col width="180" align="left">
								<col width="90" align="left">
								<col width="120" align="left">
								<col width="100" align="left">
								<col width="120" align="left">
								<col width="180" align="left">
								<col width="210" align="left">
								<col width="210" align="left">
								<col width="240" align="left">
								<col width="210" align="left">
								<col width="220" align="left">
								<col width="80" align="left">
								<col width="160" align="left">
								<col width="380" align="left">
									<tr align="center">
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_FR' )">Date Time
											<img id="sortIMG.xxDtTm_FR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ezInTimeZone_TR' )">Time Zone
											<img id="sortIMG.ezInTimeZone_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'glblCmpyCd_TR' )">Global Company Code
											<img id="sortIMG.glblCmpyCd_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsUnivsUniqId_TR' )">Universal Unique ID
											<img id="sortIMG.almsUnivsUniqId_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsSysNm_TR' )">System
											<img id="sortIMG.almsSysNm_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsHostNm_TR' )">Host Name
											<img id="sortIMG.almsHostNm_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxAlmsOnlBatFlgTxt_TR' )">Online/Batch
											<img id="sortIMG.almsOnlBatFlg_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsMsgId_TR' )">Message ID
											<img id="sortIMG.almsMsgId_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsUsrId_TR' )">User ID
											<img id="sortIMG.almsUsrId_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsJobId_TR' )">Job Id
											<img id="sortIMG.almsJobId_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsBatProcId_TR' )">Batch Process ID
											<img id="sortIMG.almsBatProcId_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsFwkVrsnTxt_TR' )">Framework Version
											<img id="sortIMG.almsFwkVrsnTxt_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsAppVrsnTxt_TR' )">Application Version
											<img id="sortIMG.almsAppVrsnTxt_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsRelVrsnTxt_TR' )">Release Version
											<img id="sortIMG.almsRelVrsnTxt_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsDbVrsnTxt_TR' )">Database Version
											<img id="sortIMG.almsDbVrsnTxt_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>

										<td class="pClothBs" align="center">
											<a href="#" class="pSortCol" onclick="columnSort( 'A', 'almsPgmId_TR' )">Program ID
											<img id="sortIMG.almsPgmId_TR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
										</td>
										<td class="pClothBs" align="center">Severity Level</td>
										<td class="pClothBs" align="center">Java Virtual Machine</td>
										<td class="pClothBs" align="center">Abend Message</td>

									</tr>
							   </table>
					    </div>

						<div id="RightTBL" style="overflow-y:scroll; overflow-x:scroll; height:310;width:1047" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ) );">
							<table ID="A" cellpadding="1" cellspacing="0" border="1" width="3400" id="A_RightTBL" style="table-layout:fixed">
								<col width="180" align="left">
								<col width="90" align="left">
								<col width="90" align="left">
								<col width="260" align="left">
								<col width="90" align="left">
								<col width="180" align="left">
								<col width="90" align="left">
								<col width="120" align="left">
								<col width="100" align="left">
								<col width="120" align="left">
								<col width="180" align="left">
								<col width="210" align="left">
								<col width="210" align="left">
								<col width="240" align="left">
								<col width="210" align="left">
								<col width="220" align="left">
								<col width="80" align="left">
								<col width="160" align="left">
								<col width="380" align="left">
								<ezf:row ezfHyo="A">
									<tr>
										<td height="24"><ezf:label name="xxDtTm_FR" ezfName="xxDtTm_FR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="ezInTimeZone_TR" ezfName="ezInTimeZone_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="glblCmpyCd_TR" ezfName="glblCmpyCd_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsUnivsUniqId_TR" ezfName="almsUnivsUniqId_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsSysNm_TR" ezfName="almsSysNm_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsHostNm_TR" ezfName="almsHostNm_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="xxAlmsOnlBatFlgTxt_TR" ezfName="xxAlmsOnlBatFlgTxt_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsMsgId_TR" ezfName="almsMsgId_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsUsrId_TR" ezfName="almsUsrId_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsJobId_TR" ezfName="almsJobId_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsBatProcId_TR" ezfName="almsBatProcId_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsFwkVrsnTxt_TR" ezfName="almsFwkVrsnTxt_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsAppVrsnTxt_TR" ezfName="almsAppVrsnTxt_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsRelVrsnTxt_TR" ezfName="almsRelVrsnTxt_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsDbVrsnTxt_TR" ezfName="almsDbVrsnTxt_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsPgmId_TR" ezfName="almsPgmId_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsSevtyLvlTxt_TR" ezfName="almsSevtyLvlTxt_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="almsJvmNm_TR" ezfName="almsJvmNm_TR" ezfHyo="A" ezfArrayNo="0" /></td>
										<td height="24"><ezf:label name="xxAbendMsgTxt_TR" ezfName="xxAbendMsgTxt_TR" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\""/></td>
									</tr>
								</ezf:row>
									<ezf:skip>
									</ezf:skip>
							 </table>
						</div>
					</td>
					<%-- ++++++++++++++++++++++++++++++++++++++++++++++++++ Right TBL : E N D ++++++++++++++++++++++++++++++++++++++++++++++++++ --%>
					</tr>
				</table>
				</td>
			</tr>
			</table>
		</div>
</center>



<%-- **** Task specific area ends here **** --%>
