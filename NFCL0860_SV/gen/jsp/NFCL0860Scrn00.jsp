<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220122123306 --%>
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
			<input type="hidden" name="pageID" value="NFCL0860Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="AR Activity Inquiry">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
			    <%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				</div>
				<%-- ###TAB - BODY --%>
			    <div class="pTab_BODY" style="padding-top:5px;" align="center">
					<table width="94%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<col width="60">
							<col width="700">
								<!-- START 2016/05/17 S.Fujita [QC#7983,MOD] -->
								<!-- START 2022/01/22 K.Suzuki [QC#55788-2,MOD] -->
								<td class="stab">Transaction#</td>
								<!-- END   2022/01/22 K.Suzuki [QC#55788-2,MOD] -->
								<!-- END 2016/05/17 S.Fujita [QC#7983,MOD] -->
								<td>
									<!-- START 2022/01/22 K.Suzuki [QC#55788-2,MOD] -->
									<ezf:inputText name="arCustRefNum_H" ezfName="arCustRefNum_H" value="AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDD" otherAttr=" size=\"35\" maxlength=\"35\""/>
									<!-- END   2022/01/22 K.Suzuki [QC#55788-2,MOD] -->
								</td>
						</tr>
					</table>
					<hr>
<%-- ######################################## DETAIL ######################################## --%>
<!-- START 2016/04/19 S.Fujita [QC#7022,MOD] -->
<%@ page import="business.servlet.NFCL0860.NFCL0860BMsg" %>
<%@ page import="business.servlet.NFCL0860.NFCL0860_ABMsg" %>
<%  NFCL0860BMsg bMsg = (NFCL0860BMsg)databean.getEZDBMsg(); %>
<!-- 2016/04/19 S.Fujita [QC#7022,MOD] -->

					<table border="0" cellpadding="0" cellspacing="0">
						<col width="72">
						<col width="18">
						<col width="120">
						<col width="860">
						<tr>
						<td><ezf:inputButton name="Check_All" value="CheckAll" htmlClass="pBtn6" otherAttr=" id=\"Check_All\""/></td>
						<td></td>
						<td class="stab">Preferred View Setting</td>
							<td>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</td>
						</tr>
					</table>
					<div id="parentBoxA">
						<table>
							<tr>
								<td width="10"></td>
								<td>
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;"></div>
										<!-- START 2016/05/25 T.Tsuchida [QC#8420,MOD] -->
										<div id="leftTbl" style="float:left; display:block; height:450px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
										<!-- END 2016/05/25 T.Tsuchida [QC#8420,MOD] -->
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1083px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="AHEAD" width="1299px" style="margin-right:20px">
												<col width="22" align="center"><!-- Checkbox -->
												<col width="90" align="left"><!-- Type -->
												<col width="135" align="left"><!-- Activity Name -->
												<col width="80" align="center"><!-- Number -->
												<col width="80" align="center"><!-- Activity Date -->
												<col width="100" align="right"><!-- Amount -->
												<col width="100" align="right"><!-- Amt Applied -->
												<col width="80" align="center"><!-- Appl Date -->
												<col width="80" align="center"><!-- GL Date -->
												<col width="70" align="center"><!-- Status -->
												<col width="120" align="left"><!-- Comments -->
												<col width="120" align="left"><!-- Note -->
												<tr height="24">
													<td id="AH0" class="pClothBs">&nbsp;</td>
													<td id="AH1" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxTpDescTxt_A' )">
															Type<img id="sortIMG.arTrxTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH2" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arAdjTpDescTxt_A' )">
															Activity Name<img id="sortIMG.arAdjTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH3" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxNum_A' )">
															Number<img id="sortIMG.arTrxNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH4" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxDt_A' )">
															Activity Date<img id="sortIMG.arTrxDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH5" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealOrigGrsAmt_A' )">
															Amount<img id="sortIMG.dealOrigGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH6" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealApplyAmt_A' )">
															Amt Applied<img id="sortIMG.dealApplyAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH7" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'cashAppDt_A' )">
															Appl Date<img id="sortIMG.cashAppDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH8" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'glDt_A' )">
															GL Date<img id="sortIMG.glDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH9" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arAdjStsNm_A' )">
															Status<img id="sortIMG.arAdjStsNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH10" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'adjCmntTxt_A' )">
															Comments<img id="sortIMG.adjCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH11" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'arWrtOffNoteTxt_A' )">
															Note<img id="sortIMG.arWrtOffNoteTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
												</tr>
											</table>
										</div><!-- rightTblHead-->
										<!-- START 2016/05/25 T.Tsuchida [QC#8420,MOD] -->
										<div id="rightTbl" style="width:1096px; height:450px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
										<!-- END 2016/05/25 T.Tsuchida [QC#8420,MOD] -->
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1300px" >
												<col width="22" align="center"><!-- Checkbox -->
												<col width="90" align="left"><!-- Type -->
												<col width="135" align="left"><!-- Activity Name -->
												<col width="80" align="center"><!-- Number -->
												<col width="80" align="center"><!-- Activity Date -->
												<col width="100" align="right"><!-- Amount -->
												<col width="100" align="right"><!-- Amt Applied -->
												<col width="80" align="center"><!-- Appl Date -->
												<col width="80" align="center"><!-- GL Date -->
												<col width="70" align="center"><!-- Status -->
												<col width="120" align="left"><!-- Comments -->
												<col width="120" align="left"><!-- Note -->

                                                <!-- START 2016/04/19 S.Fujita [QC#7022,MOD] -->
												<% int i = 0; %>
												<ezf:row ezfHyo="A">
													<% NFCL0860_ABMsg lineMsg = bMsg.A.no(i++); %>
													<% String arTrxTpCd_B1 = lineMsg.arTrxTpCd_B1.getValue(); %>
													<% String arTrxTpDescTxt_A = lineMsg.arTrxTpDescTxt_A.getValue(); %>
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td style="height:25px; text-align:center"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:label name="arTrxTpDescTxt_A" ezfName="arTrxTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="arAdjTpDescTxt_A" ezfName="arAdjTpDescTxt_A" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" style=\"border:none; background-color:transparent;\""/></td>
														<td style="word-break: break-all;">
														<%if(arTrxTpCd_B1.equals("DED")){%>
															<ezf:label name="arCustRefNum_A" ezfName="arCustRefNum_A" ezfHyo="A" />
														<%}else{%>
															<div id="arTrxNum_A#{EZF_ROW_NUMBER}"><ezf:anchor name="Click_LinkNum_A" ezfName="Click_LinkNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_LinkNum_A" otherAttr=" id=\"arTrxNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
															<%if(arTrxTpCd_B1.equals("ACC") || arTrxTpDescTxt_A.equals("Payment")){%>
																
																<ezf:label name="arCustRefNum_A" ezfName="arCustRefNum_A" ezfHyo="A" ezfArrayNo="0" />
															<%}else{%>
																<ezf:label name="arTrxNum_A" ezfName="arTrxNum_A" ezfHyo="A" ezfArrayNo="0" />
															<%}%>
															</div></ezf:anchor>
														<%}%>
												<!-- 2016/04/19 S.Fujita [QC#7022,MOD] -->
														</td>
														<td><ezf:label name="arTrxDt_A" ezfName="arTrxDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="dealOrigGrsAmt_A" ezfName="dealOrigGrsAmt_A" value="1,000,000.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;text-align:right;\""/></td>
														<td><ezf:inputText name="dealApplyAmt_A" ezfName="dealApplyAmt_A" value="1,000.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" style=\"border:none; background-color:transparent;text-align:right;\""/></td>
														<td><ezf:label name="cashAppDt_A" ezfName="cashAppDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="glDt_A" ezfName="glDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="arAdjStsNm_A" ezfName="arAdjStsNm_A" value="0--------1---------2---------3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="adjCmntTxt_A" ezfName="adjCmntTxt_A" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
														<td><ezf:inputText name="arWrtOffNoteTxt_A" ezfName="arWrtOffNoteTxt_A" value="0--------1---------2---------3---------4---------5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" style=\"border:none; background-color:transparent;\""/></td>
													</tr>
												</ezf:row>
											</table>
										</div><!-- rightTbl-->
									</div><!-- right table-->
								</td>
							</tr>
						</table>
					</div><!-- parentBoxA -->
					<div align="right" style="width:97%">
						<ezf:inputButton name="Unapply" value="Unapply" htmlClass="pBtn6" />
					</div>
				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2, true);
</script>

<%-- **** Task specific area ends here **** --%>
