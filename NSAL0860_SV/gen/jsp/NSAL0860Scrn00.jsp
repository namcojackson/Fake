<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160613185004 --%>
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
			<input type="hidden" name="pageID" value="NSAL0860Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Register & Deregister Screen">

<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<%-- ###TAB - HEAD --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Reg & Dereg</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
						<tr>
							<td valign="top">
								<table border="0" style="table-layout:fixed;" width="95%" align="center">
									<col width="80">
									<col width="150">
									<col width="30">
									<col width="90">
									<col width="150">
									<col width="40">
									<col width="90">
									<col width="180">
									<col align="right">
									<tr height="21">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SearchSerial" >Serial#(*)</ezf:anchor></td>
										<td><ezf:inputText name="serNum" ezfName="serNum" value="200215%" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Customer Name(*)</td>
										<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="200215%" otherAttr=" size=\"20\""/></td>
										<td></td>
										<td class="stab">IWR Status</td>
										<td><ezf:select name="iwrCondCd" ezfName="iwrCondCd" ezfBlank="1" ezfCodeName="iwrCondCd_L" ezfDispName="iwrCondDescTxt_L" /></td>
										<td></td>
									</tr>
									<tr height="21">
										<td class="stab">Model Name(*)</td>
										<td><ezf:inputText name="mdlNm" ezfName="mdlNm" value="200215%" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab">Customer#(*)</td>
										<td><ezf:inputText name="curLocAcctNum" ezfName="curLocAcctNum" value="200215%" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpneWin_SearchContr" otherAttr=" ezfanchortext=\"\"">Contract#(*)</ezf:anchor></td>
										<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="200215%" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
										<td></td>
										<td></td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
					</table>
					<br>
					<!-- ######################################## Detail ######################################## -->
 					<table border="0" style="table-layout:fixed;width=1080px;margin-left:20;">
						<col width="100">
						<col width="60">
						<col width="80">
						<col width="390">
						<col width="">
						<tr>
							<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<td></td>
							<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
							<td><ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn6" /></td>
							<!-- Pagination & Navigation--START-->
							<td align="right">
								<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"           value="A" />
									<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
									<jsp:param name="ViewMode"          value="LEFT_NONE" />
								</jsp:include>
								<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="54"  align="center">
									<col width="40"  align="center">
									<col width="16"  align="center">
									<col width="40"  align="center">
									<col width="16"  align="center">
									<col width="10">
									<col>
									<col width="20">
									<col>	
									<col width="1">
									<col>
									<tr>
										<td class="stab">Showing</td>
										<td><input type="text" name="xxPageShowFromNum" value="1" size="4" maxlength="4" style="text-align:right" id="txtShowingCur" ezfname="xxPageShowFromNum" ></td>
										<td class="stab">to</td>
										<td><input type="text" name="xxPageShowToNum" ezfName="xxPageShowToNum" size="4" maxlength="4" value="2" class="pPro" style="text-align:right" id="txtShowingTo" readOnly></td>
										<td class="stab">of</td>
										<td><input type="text" name="xxPageShowOfNum" ezfName="xxPageShowOfNum" size="4" maxlength="4" value="3" class="pPro" style="text-align:right" id="txtShowingTot" readOnly></td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')" disabled></td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')" ></td>
									</tr>
								</table>
								</ezf:skip>
							</td>
							<!-- Pagination & Navigation--END-->
						</tr>
					</table>
					<table align="center">
						<tr>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:383px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1620px" style="margin-right:20px" >
												<col width="30" align="center">	<!-- CheckBox -->
												<col width="100" align="center">	<!-- Serial# -->
												<col width="150" align="center">	<!-- Model Name -->
												<col width="120" align="center">	<!-- Location -->
												<col width="120" align="center">	<!-- Connectivity -->
												<col width="100" align="center">	<!-- Shared By Dealer -->
												<col width="150" align="center">	<!-- Customer Name -->
												<col width="100" align="center">	<!-- Customer# -->
												<col width="80" align="center">	<!-- Contract# -->
												<col width="100" align="center">	<!-- Action -->
												<col width="80" align="center">	<!-- Status -->
												<col width="150" align="center">	<!-- Message -->
												<tr height="34px">
													<td id="AH0" class="pClothBs colFix">&nbsp;</td>
													<td id="AH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort('A', 'serNum_A')">Serial#</a><img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 't_MdlNm_A')">Model Name</a><img id="sortIMG.t_MdlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shipToCustLocAddr_A')">Location</a><img id="sortIMG.shipToCustLocAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxScrStsTxt_A')">Connectivity</a><img id="sortIMG.xxScrStsTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'shrDlrFlg_A')">Shared By Dealer</a><img id="sortIMG.shrDlrFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsAcctNm_A')">Customer Name</a><img id="sortIMG.dsAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'curLocAcctNum_A')">Customer#</a><img id="sortIMG.curLocAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'contrNum_A')">Contract#</a><img id="sortIMG.contrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ugwActDescTxt_A')">Action</a><img id="sortIMG.ugwActDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'ugwActStsDescTxt_A')">Status</a><img id="sortIMG.ugwActStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
													<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxMsgTxt_A')">Message</a><img id="sortIMG.xxMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
												</tr>
											</table>
										</div><!-- 'rightTblHead' -->
										<div id="rightTbl" style="width:1095px; height:400px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1620px" >
												<col width="30" align="center">	<!-- CheckBox -->
												<col width="100" align="center">	<!-- Serial# -->
												<col width="150" align="left">	<!-- Model Name -->
												<col width="120" align="center">	<!-- Location -->
												<col width="120" align="center">	<!-- Connectivity -->
												<col width="100" align="center">	<!-- Shared By Dealer -->
												<col width="150" align="center">	<!-- Counter Name -->
												<col width="100" align="center">	<!-- Customer# -->
												<col width="80" align="center">	<!-- Contract# -->
												<col width="100" align="center">	<!-- Action -->
												<col width="80" align="center">	<!-- Status -->
												<col width="150" align="center">	<!-- Message -->
												<ezf:row ezfHyo="A">
												<tr height="23px">
													<td align="center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
													<td>
														<ezf:anchor name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_MachMainte" otherAttr=" id=\"serNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
															<ezf:label name="serNum_A" ezfName="serNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/>
														</ezf:anchor>
													</td>
													<td align="center"><ezf:inputText name="t_MdlNm_A" ezfName="t_MdlNm_A" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
													<td align="center"><ezf:inputText name="shipToCustLocAddr_A" ezfName="shipToCustLocAddr_A" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
													<td align="center"><ezf:inputText name="xxScrStsTxt_A" ezfName="xxScrStsTxt_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\""/></td>
													<td align="center"><ezf:inputText name="shrDlrFlg_A" ezfName="shrDlrFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"2\""/></td>
													<td align="center"><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="BPR COPY SHOP INC." ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
													<td align="center"><ezf:inputText name="curLocAcctNum_A" ezfName="curLocAcctNum_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
													<td>
														<ezf:anchor name="contrNum_A" ezfName="contrNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ContrMainte" otherAttr=" id=\"contrNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
															<ezf:label name="contrNum_A" ezfName="contrNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/>
														</ezf:anchor>
													</td>
													<td align="center"><ezf:inputText name="ugwActDescTxt_A" ezfName="ugwActDescTxt_A" value="DEREGISTER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
													<td align="center"><ezf:inputText name="ugwActStsDescTxt_A" ezfName="ugwActStsDescTxt_A" value="WARNING" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
													<td align="center"><ezf:inputText name="xxMsgTxt_A" ezfName="xxMsgTxt_A" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr height="23px">
													<td align="center"><input type="checkbox" value="Y" name="xxChkBox_A" ezfname="xxChkBox_A" ezfhyo="A"></td>
													<td style="word-break: break-all;">
														<a href="#" onclick="sendServer('OpenWin_MachMainte')" ezfanchortext>
															<label ezfout name="serNum_A" ezfname="serNum_A" ezfhyo="A">123456789012</label>
														</a>
													</td>
													<td align="center"><input type="text" class="" size="20" maxlength="50" value="12345678901234567890" name="t_MdlNm_A" ezfname="t_MdlNm_A" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="15" value="123456789012345" name="shipToCustLocAddr_A" ezfname="shipToCustLocAddr_A" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="15" value="1234567890" name="xxScrStsTxt_A" ezfname="xxScrStsTxt_A" ezfhyo="A" ></td>
													<td align="center"><input type="text" class="" size="2" value="N" name="shrDlrFlg_A" ezfname="shrDlrFlg_A" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="20" value="BPR COPY SHOP INC." name="dsAcctNm_A" ezfname="dsAcctNm_A" ezfhyo="A"></td>
													<td align="center"><input type="text" class="" size="12" value="1234567890" name="curLocAcctNum_A" ezfname="curLocAcctNum_A" ezfhyo="A"></td>
													<td style="word-break: break-all;">
														<a href="#" onclick="sendServer('OpenWin_ContrMainte')" ezfanchortext>
															<label ezfout name="contrNum_A" ezfname="contrNum_A" ezfhyo="A">1234567890</label>
														</a>
													</td>
													<td align="center"><input type="text" class="" size="12" value="DE-REGISTER" name="ugwActDescTxt_A" ezfname="ugwActDescTxt_A" ezfhyo="A" ></td>
													<td align="center"><input type="text" class="" size="8" value="SUCCESS" name="ugwActStsDescTxt_A" ezfname="ugwActStsDescTxt_A" ezfhyo="A" ></td>
													<td align="center"><input type="text" class="" size="20" value="12345678901234567890" name="xxMsgTxt_A" ezfname="xxMsgTxt_A" ezfhyo="A" ></td>
												</tr>

												</ezf:skip>
											</table>
										</div><!-- rightTbl -->
									</div><!-- right table -->
								</div><!-- parent box -->
							</td>
						</tr>
					</table>
					<table  border="0" cellpadding="1" cellspacing="0">
						<col width="30">
						<col width="130">
						<col width="425">
						<col width="10">
						<col width="50">
						<col width="160">
						<col width="50">
						<col width="10">
						<col width="50">
						<tr>
							<td></td>
							<td class="stab"><ezf:anchor name="xxLinkAncr" ezfName="xxLinkAncr" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Download Template</ezf:anchor></td>
							<td><ezf:inputFile name="xxFileData_U" ezfName="xxFileData_U" otherAttr=" size=\"60\" maxlength=\"9999\""/></td>
							<td></td>
							<td><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" /></td>
							<td></td>
							<td><ezf:inputButton name="Register" value="Register" htmlClass="pBtn6" /></td>
							<td></td>
							<td><ezf:inputButton name="Deregister" value="Deregister" htmlClass="pBtn6" /></td>
						</tr>
					</table>
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>
	<script type="text/javascript" defer>
	    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2);
	</script>


<%-- **** Task specific area ends here **** --%>
