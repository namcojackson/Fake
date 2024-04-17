<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220627093752 --%>
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
			<input type="hidden" name="pageID" value="NSAL1410Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Branch Rep Update">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
										<li title="Update Contract Branch Rep" class="pTab_ON"><a href="#">Br Rep Upd</a></li>
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
					<table style="table-layout:fixed;" width="98%" align="center">
						<tr>
							<td>
								<table border="0" style="table-layout:fixed;" width="95%" align="center">
									<col width=" 60"><!-- Contr# Label-->
									<col width=" 92"><!-- Contr# -->
									<col width=" 30">
									<col width=" 78"><!-- Branch# Label -->
									<col width=" 78"><!-- Branch# -->
									<col width=" 60">
									<col width=" 82"><!-- Branch Name Label -->
									<col width="145"><!-- Branch Name -->
									<col width=" 65"><!-- Branch Rep Label -->
									<col width=" 78"><!-- Branch Rep -->
									<col width=" 30">
									<col width="105"><!-- Branch Rep Name Label-->
									<col width="140"><!-- Branch Rep Name -->
									<tr height= "21">
										<td class="stab">Contr#</td>
										<td><ezf:inputText name="dsContrNum_H" ezfName="dsContrNum_H" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Branch#</td>
										<td><ezf:inputText name="svcContrBrCd_H" ezfName="svcContrBrCd_H" otherAttr=" size=\"10\" maxlength=\"3\""/></td>
										<td><ezf:inputButton name="OpenWin_Branch" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
										<td class="stab">Branch Name(*)</td>
										<td><ezf:inputText name="svcContrBrDescTxt_H" ezfName="svcContrBrDescTxt_H" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
										<td class="stab">Branch Rep</td>
										<td><ezf:inputText name="psnCd_H" ezfName="psnCd_H" otherAttr=" size=\"10\" maxlength=\"8\""/></td>
										<td><ezf:inputButton name="OpenWin_Rep" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
										<td class="stab">Branch Rep Name(*)</td>
										<td><ezf:inputText name="xxPsnNm_H" ezfName="xxPsnNm_H" otherAttr=" size=\"18\" maxlength=\"61\""/></td>
										<td></td>
									</tr>
									<tr height= "21">
										<td class="stab">Bill To Code</td>
										<td><ezf:inputText name="billToCustCd_H" ezfName="billToCustCd_H" otherAttr=" size=\"12\" maxlength=\"20\""/></td>
										<td align="left"><ezf:inputButton name="OpenWin_BillTo" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
										<td class="stab">Bill To Name(*)</td>
										<td colspan="2"><ezf:inputText name="locNm_H" ezfName="locNm_H" otherAttr=" size=\"18\" maxlength=\"60\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td><hr></td>
						</tr>
					</table>

<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
 					<table border="0" style="table-layout:fixed;width=1082px;margin-left:20;">
						<col width=" 70">
						<col width="450">
						<col width="" align="right">
						<tr>
							<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="ImportFile" >Import File</ezf:anchor></td>
							<td><ezf:inputFile name="xxFileData_U" ezfName="xxFileData_U" otherAttr=" size=\"25\" maxlength=\"9999\""/><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" /></td>
							<!-- Pagination & Navigation--START-->
							<td>
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"           value="A" />
									<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"          value="FULL" />
								</jsp:include>
							</td>
							<!-- Pagination & Navigation--END-->
						</tr>
					</table>
<!-- ######################################## TO (COMMON)PAGENATION ###################################### -->

<!-- ######################################## DETAIL ######################################## -->
					<table>
						<tr>
							<td>
								<div id="Top" style="overflow-x:hidden; width:1095;">
									<table border="1" cellpadding="1" cellspacing="0" width="" height="31">
										<col width=" 75" align="center"><!-- Contr# -->
										<col width=" 80" align="center"><!-- Bill To Code -->
										<col width="120" align="center"><!-- Bill To Name -->
										<col width=" 75" align="center"><!-- Branch# -->
										<col width="120" align="center"><!-- Branch Name -->
										<col width=" 75" align="center"><!-- Branch Rep Code -->
										<col width="130" align="center"><!-- Brach Rep Name -->
										<col width="100" align="center"><!-- New Branch Rep -->
										<col width="130" align="center"><!-- New Branch Rep Name -->
										<col width="150" align="center"><!-- Process Message -->
										<tr height="25">
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'dsContrNum_A')">Contr#</a><img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'billToCustCd_A')">Bill To Code</a><img id="sortIMG.billToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'locNm_A')">Bill To Name</a><img id="sortIMG.locNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'svcContrBrCd_A')">Branch#</a><img id="sortIMG.svcContrBrCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxGenlFldAreaTxt_A')">Branch Name</a><img id="sortIMG.xxGenlFldAreaTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'psnCd_A1')">Branch Rep Code</a><img id="sortIMG.psnCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxPsnNm_A1')">Branch Rep Name</a><img id="sortIMG.xxPsnNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'psnCd_A2')">New Branch Rep Code</a><img id="sortIMG.psnCd_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'xxPsnNm_A2')">New Branch Rep Name</a><img id="sortIMG.xxPsnNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'vldMsgTxt_A')">Process Message</a><img id="sortIMG.vldMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
										</tr>
									</table>
								</div>
								<div id="Detail" style="width:1112; overflow-y:scroll; height:425;">
									<table id="A" border="1" cellpadding="1" cellspacing="0">
										<col width=" 75" align="center"><!-- Contr# -->
										<col width=" 80" align="center"><!-- Bill To Code -->
										<col width="120" align="center"><!-- Bill To Name -->
										<col width=" 75" align="center"><!-- Branch# -->
										<col width="120" align="center"><!-- Branch Name -->
										<col width=" 75" align="center"><!-- Branch Rep Code -->
										<col width="130" align="center"><!-- Brach Rep Name -->
										<col width="100" align="center"><!-- New Branch Rep -->
										<col width="130" align="center"><!-- New Branch Rep Name -->
										<col width="150" align="center"><!-- Process Message -->
										<ezf:row ezfHyo="A">
											<tr height="25">
												<td>
													<ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="0001234" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsContrNum_A#{EZF_ROW_NUMBER}\" size=\"9\""/>
												</td>
												<td>
													<ezf:inputText name="billToCustCd_A" ezfName="billToCustCd_A" value="1002147" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"billToCustCd_A#{EZF_ROW_NUMBER}\" size=\"9\""/>
												</td>
												<td>
													<ezf:inputText name="locNm_A" ezfName="locNm_A" value="CHECKVIEW CORPORATION" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"locNm_A#{EZF_ROW_NUMBER}\" size=\"15\""/>
												</td>
												<td>
													<ezf:inputText name="svcContrBrCd_A" ezfName="svcContrBrCd_A" value="506" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcContrBrCd_A#{EZF_ROW_NUMBER}\" size=\"9\""/>
												</td>
												<td>
													<ezf:inputText name="xxGenlFldAreaTxt_A" ezfName="xxGenlFldAreaTxt_A" value="506 - CHICAGO REGION" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxGenlFldAreaTxt_A#{EZF_ROW_NUMBER}\" size=\"15\""/>
												</td>
												<td>
													<ezf:inputText name="psnCd_A1" ezfName="psnCd_A1" value="C16945" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"psnCd_A1#{EZF_ROW_NUMBER}\" size=\"9\""/>
												</td>
												<td>
													<ezf:inputText name="xxPsnNm_A1" ezfName="xxPsnNm_A1" value="Martin Dunne" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxPsnNm_A1#{EZF_ROW_NUMBER}\" size=\"17\""/>
												</td>
												<td>
													<ezf:inputText name="psnCd_A2" ezfName="psnCd_A2" value="C16945" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onchange=\"sendServer('OnChangeRep', '{EZF_ROW_NUMBER}')\"" otherAttr=" id=\"psnCd_A2#{EZF_ROW_NUMBER}\" size=\"9\" maxlength=\"30\""/>
													<ezf:inputButton name="OpenWin_Rep" value="..." ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
												</td>
												<td>
													<ezf:inputText name="xxPsnNm_A2" ezfName="xxPsnNm_A2" value="Martin Dunne" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxPsnNm_A2#{EZF_ROW_NUMBER}\" size=\"17\""/>
												</td>
												<td>
													<ezf:inputText name="vldMsgTxt_A" ezfName="vldMsgTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"vldMsgTxt_A#{EZF_ROW_NUMBER}\" size=\"20\""/>
												</td>
											</tr>
											<ezf:skip>
											</ezf:skip>
										</ezf:row>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
