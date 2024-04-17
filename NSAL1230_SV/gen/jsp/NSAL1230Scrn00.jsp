<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180419153240 --%>
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
			<input type="hidden" name="pageID" value="NSAL1230Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Segment Revenue Distribution">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Contract Segment Revenue Distribution" class="pTab_ON"><a href="#">Seg Rev Dist</a></li>
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
				<div class="pTab_BODY">
				    <table style="margin-left:5; margin-top:0;">
                        <tr valign="top">
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" >
                                </table>
                            </td>
                        </tr>
                    </table>
					<table border="0" width="100%">
						<tr>
							<td>
								<table  cellpadding="1" border="0" cellspacing="0">
									<col width="0">
									<col width="0">
									<col width="10">
									<col width="80">
									<col width="80">
									<col width="50">
									<tr>
										<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
										<td><ezf:inputButton name="UnselectAll" value="Unselect All" htmlClass="pBtn6" /></td>
										<td></td>
										<td><ezf:label name="basePrcDealAmtTxt" ezfName="basePrcDealAmtTxt" /></td>
										<td align="left"><ezf:label name="basePrcDealAmt" ezfName="basePrcDealAmt" /></td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
					<table border="0" width="100%">
						<tr>
							<td>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 34" align="center"><!-- CheckBox -->
									<col width="408" align="center"><!-- Segment -->
									<col width=" 200" align="center"><!-- Account -->
									<col width=" 84" align="center"><!-- Percent -->
									<col width=" 150" align="center"><!-- Price -->
									<tr height=" 35">
										<td class="pClothBs">&nbsp;</td>
										<td class="pClothBs">Segment</td>
										<td class="pClothBs">Account</td>
										<td class="pClothBs">Percent</td>
										<td class="pClothBs">Price</td>
									</tr>
								</table>
								<div style="width:900; height:400; display:block; overflow-x:none; overflow-y:scroll;">
									<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width=" 34" align="center"><!-- CheckBox -->
										<col width="408" align="center"><!-- Segment -->
										<col width="200" align="center"><!-- Segment -->
										<col width=" 84" align="center"><!-- Percent -->
										<col width=" 150" align="center"><!-- Price -->
										<ezf:skip>
											<tr height="25">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="50" value="800.101.XXX.112001.70400050.7300.RT010001.000.000" name="coaAfflAcctNm_A1" ezfname="coaAfflAcctNm_A1" ezfhyo="A"><input type="button" class="pBtn0" value="S" name="Open_Win_GlComPop" onclick="sendServer(this)" ezfhyo="A"></td>
												<td><label ezfout name="coaAcctDescTxt_A1" ezfname="coaAcctDescTxt_A1"  ezfhyo="A">Equipment</label></td>
												<td><input type="text" class="pPro" size="6" value="20.00" name="prcAllocRate_A1" ezfname="prcAllocRate_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="12" value="300" name="prcAllocAmt_A1" ezfname="prcAllocAmt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="50" value="800.101.XXX.112001.70400050.7300.RT010001.000.000" name="coaAfflAcctNm_A1" ezfname="coaAfflAcctNm_A1" ezfhyo="A"><input type="button" class="pBtn0" value="S" name="Open_Win_GlComPop" onclick="sendServer(this)" ezfhyo="A"></td>
												<td><label ezfout name="coaAcctDescTxt_A1" ezfname="coaAcctDescTxt_A1"  ezfhyo="A">Service</label></td>
												<td><input type="text" class="pPro" size="6" value="20.00" name="prcAllocRate_A1" ezfname="prcAllocRate_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="12" value="300" name="prcAllocAmt_A1" ezfname="prcAllocAmt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="50" value="800.101.XXX.112001.70400050.7300.RT010001.000.000" name="coaAfflAcctNm_A1" ezfname="coaAfflAcctNm_A1" ezfhyo="A"><input type="button" class="pBtn0" value="S" name="Open_Win_GlComPop" onclick="sendServer(this)" ezfhyo="A"></td>
												<td><label ezfout name="coaAcctDescTxt_A1" ezfname="coaAcctDescTxt_A1"  ezfhyo="A">Supply</label></td>
												<td><input type="text" class="pPro" size="6" value="20.00" name="prcAllocRate_A1" ezfname="prcAllocRate_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="12" value="300" name="prcAllocAmt_A1" ezfname="prcAllocAmt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="50" value="800.101.XXX.112001.70400050.7300.RT010001.000.000" name="coaAfflAcctNm_A1" ezfname="coaAfflAcctNm_A1" ezfhyo="A"><input type="button" class="pBtn0" value="S" name="Open_Win_GlComPop" onclick="sendServer(this)" ezfhyo="A"></td>
												<td><label ezfout name="coaAcctDescTxt_A1" ezfname="coaAcctDescTxt_A1"  ezfhyo="A">Other</label></td>
												<td><input type="text" class="pPro" size="6" value="20.00" name="prcAllocRate_A1" ezfname="prcAllocRate_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="12" value="300" name="prcAllocAmt_A1" ezfname="prcAllocAmt_A1" ezfhyo="A"></td>
											</tr>
											<tr height="25">
												<td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="50" value="800.101.XXX.112001.70400050.7300.RT010001.000.000" name="coaAfflAcctNm_A1" ezfname="coaAfflAcctNm_A1" ezfhyo="A"><input type="button" class="pBtn0" value="S" name="Open_Win_GlComPop" onclick="sendServer(this)" ezfhyo="A"></td>
												<td><label ezfout name="coaAcctDescTxt_A1" ezfname="coaAcctDescTxt_A1"  ezfhyo="A">Other</label></td>
												<td><input type="text" class="pPro" size="6" value="20.00" name="prcAllocRate_A1" ezfname="prcAllocRate_A1" ezfhyo="A"></td>
												<td><input type="text" class="pPro" size="12" value="300" name="prcAllocAmt_A1" ezfname="prcAllocAmt_A1" ezfhyo="A"></td>
											</tr>
										</ezf:skip>
										<ezf:row ezfHyo="A">
											<tr height="25">
												<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="coaAfflAcctNm_A1" ezfName="coaAfflAcctNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" id=\"coaAfflAcctNmt_A1_A1#{EZF_ROW_NUMBER}\""/><ezf:inputButton name="Open_Win_GlComPop" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
												<td><ezf:label name="coaAcctDescTxt_A1" ezfName="coaAcctDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="prcAllocRate_A1" ezfName="prcAllocRate_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" id=\"prcAllocRate_A1#{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:inputText name="prcAllocAmt_A1" ezfName="prcAllocAmt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" id=\"prcAllocAmt_A1#{EZF_ROW_NUMBER}\""/></td>
												<td class="pAuditInfo">
													<ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
													<ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\""/>
												</td>
											</tr>
										</ezf:row>
									</table>
								</div>
								<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
									<col width=" 34" align="center"><!-- CheckBox -->
									<col width="408" align="center"><!-- Segment -->
									<col width="200" align="center"><!-- Account -->
									<col width=" 84" align="center"><!-- Percent -->
									<col width=" 150" align="center"><!-- Price -->
									<tr height=" 25">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td align="right">total</td>
										<td><ezf:label name="prcAllocRate" ezfName="prcAllocRate" />%</td>
										<td><ezf:label name="prcAllocAmt" ezfName="prcAllocAmt" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="100%">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="0">
									<col width="0">
									<tr>
										<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" /></td>
										<td><ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<ezf:skip>
<script type="text/javascript" src="js/html_preview.js" charset="UTF-8"></script>
</ezf:skip>

<%-- **** Task specific area ends here **** --%>
