<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160422091609 --%>
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
			<input type="hidden" name="pageID" value="NSAL0610Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Copy Contract">
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
										<li title="Copy Contract" class="pTab_ON"><a href="#">Copy Contr</a></li>
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
					<br />
					<table border="0" cellpadding="0" cellspacing="0" align="center" style="table-layout:fixed;" >
						<col align="left" valign="top" width="480">
						<col align="center" valign="middle" width="60">
						<col align="left" valign="top" width="480">
						<tr>
							<td>
<!-- Existing Contract -->
								<fieldset style="margin:10px 0px 0px 0px; height:100%;">
									<legend style="font-size:12px;">Existing Contract</legend>
									<table width="100%" border="0">
										<tr>
											<td  align="right">
												<ezf:skip>
														<table border="0" cellpadding="1" cellspacing="0">
															<col >
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="10">
															<col width="0">
															<col width="1">
															<col width="0">
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut">1</td>
																<td class="stab">to</td>
																<td class="pOut">3</td>
																<td class="stab">of</td>
																<td class="pOut">1000</td>
																<td>&nbsp;</td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
															</tr>
														</table>
												</ezf:skip>
												<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
													<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
													<jsp:param name="TableNm"     value="A" />
													<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
													<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
													<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
												</jsp:include>
											</td>
										</tr>
									</table>
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="125" align="center"><!-- Contract# -->
										<col width="140" align="center"><!-- Serial# / Access# -->
										<col width="140" align="center"><!-- Base / Overage -->
										<col width="35"  align="center"><!-- Pricing Effectivities -->
										<tr height="28">
											<td class="pClothBs">Contract#</td>
											<td class="pClothBs">Serial#</td>
											<td class="pClothBs">Base / Overage</td>
											<td class="pClothBs"></td>
										</tr>
									</table>
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="125" align="left" valign="center"><!-- Contract# -->
										<col width="140" align="left"><!-- Serial# / Access# -->
										<col width="140" align="left"><!-- Base / Overage -->
										<col width="35"  align="center"><!-- Pricing Effectivities -->
										<tr height="28">
											<td><ezf:inputText name="dsContrNum_H1" ezfName="dsContrNum_H1" value="1221220" otherAttr=" style=\"height:18px; vertical-align:middle;\" size=\"15\""/></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" otherAttr=" style=\"visibility:visible\""/></td>
										</tr>
									</table>
									<div style="overflow-x:hidden; width:459; overflow-y:scroll; height:422;">
										<table id="A" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
											<col width="125" align="left" valign="center"><!-- Contract# -->
											<col width="140" align="left"><!-- Serial# / Access# -->
											<col width="140" align="left"><!-- Base / Overage -->
											<col width="35"  align="center"><!-- Pricing Effectivities -->
											<%@ page import="business.servlet.NSAL0610.NSAL0610BMsg" %>
											<% NSAL0610BMsg bMsg = (NSAL0610BMsg)databean.getEZDBMsg(); %>
											<% int idx=-1; %>
											<ezf:row ezfHyo="A">
											<% idx++; %>
												<tr height="28">
													<td>&nbsp;</td>
												<% if ("Y".equals((String)bMsg.A.no(idx).xxDplyCtrlFlg_A.getValue())) { %>
													<td>
														<ezf:inputText name="serNum_A" ezfName="serNum_A" value="MSK10100" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" style=\"height:18px; vertical-align:middle;\" size=\"15\""/>
														<ezf:anchor name="xxBtnFlg_A" ezfName="xxBtnFlg_A" ezfHyo="A" ezfArrayNo="${idx}" ezfEmulateBtn="1" ezfGuard="OrigCollapse" otherAttr=" ezfanchortext=\"\"">
														<%if ("Y".equals(bMsg.A.no(idx).xxBtnFlg_A.getValue())) { %>
															<img src="./img/biz/downarrow2.png" border="0" value="Y">
														<% } else { %>
															<img src="./img/biz/rightarrow2.png" border="0" value="N">
														<% } %>
														</ezf:anchor>
													</td>
												<% } else { %>
													<td>&nbsp;</td>
												<% } %>
												<% if ("N".equals((String)bMsg.A.no(idx).xxDplyCtrlFlg_A.getValue())) { %>
													<td><ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="Base" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" style=\"height:18px; vertical-align:middle;\" size=\"18\""/></td>
												<% } else { %>
													<td>&nbsp;</td>
												<% } %>
												<% if ("Y".equals((String)bMsg.A.no(idx).xxDplyCtrlFlg_A.getValue())) { %>
													<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="${idx}" /></td>
												<% } else { %>
													<td>&nbsp;<ezf:inputHidden name="xxChkBox_A" ezfName="xxChkBox_A" ezfHyo="A" ezfArrayNo="${idx}" /></td>
												<% } %>
												</tr>
											</ezf:row>
											<ezf:skip>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Base" name="mtrLbDescTxt_A" ezfname="mtrLbDescTxt_A" ezfhyo="A"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Overage - BW"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Overage - CLR"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="ACC - 123"><a href="#" name="xxLinkProt_A0" ezfname="xxLinkProt_A0" onclick="sendServer('Collapse_Item')"><img name="clps_01" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:visible"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A0" ezfname="xxLinkProt_A0" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Base"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="MSK10101"><a href="#" name="xxLinkProt_A0" ezfname="xxLinkProt_A0" onclick="sendServer('Collapse_Item')"><img name="clps_01" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:visible"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A0" ezfname="xxLinkProt_A0" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Base"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A0" ezfname="xxLinkProt_A0" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Overage - BW"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A0" ezfname="xxLinkProt_A0" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Overage - CLR"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="BCC - 123"><a href="#" name="xxLinkProt_C1" ezfname="xxLinkProt_C1" onclick="sendServer('Collapse_Item')"><img name="clps_01" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:visible"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Base"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="MSK10102"><a href="#" name="xxLinkProt_C1" ezfname="xxLinkProt_C1" onclick="sendServer('Collapse_Item')"><img name="clps_01" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:visible"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Base"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Overage - BW"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Overage - CLR"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="CCC - 123"><a href="#" name="xxLinkProt_C1" ezfname="xxLinkProt_C1" onclick="sendServer('Collapse_Item')"><img name="clps_01" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:visible"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Base"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											</ezf:skip>
										</table>
									</div>
									<table width="100%" border="0">
										<tr>
											<td align="right">
												<ezf:inputButton name="OrigSelectAll" value="Select All" htmlClass="pBtn7" />
												<ezf:inputButton name="OrigUnSelectAll" value="Un Select All" htmlClass="pBtn7" />
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
							<td>
								<ezf:inputButton name="RightArrow" value=">>" htmlClass="pBtn0" />
								<br />
								<br />
								<ezf:inputButton name="LeftArrow" value="<<" htmlClass="pBtn0" />
							</td>
							<td>
<!-- New Contract -->
								<fieldset style="margin:10px 0px 0px 0px; height:100%;">
									<legend style="font-size:12px;">New Contract</legend>
									<table width="100%" border="0">
										<tr>
											<td  align="right">
												<ezf:skip>
														<table border="0" cellpadding="1" cellspacing="0">
															<col >
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="16"  align="center">
															<col width="40"  align="right">
															<col width="10">
															<col width="0">
															<col width="1">
															<col width="0">
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut">1</td>
																<td class="stab">to</td>
																<td class="pOut">3</td>
																<td class="stab">of</td>
																<td class="pOut">1000</td>
																<td>&nbsp;</td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
															</tr>
														</table>
												</ezf:skip>
												<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
													<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
													<jsp:param name="TableNm"     value="N" />
													<jsp:param name="ShowingFrom" value="xxPageShowFromNum_N" />
													<jsp:param name="ShowingTo"   value="xxPageShowToNum_N" />
													<jsp:param name="ShowingOf"   value="xxPageShowOfNum_N" />
												</jsp:include>
											</td>
										</tr>
									</table>
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="125" align="center"><!-- Contract# -->
										<col width="140" align="center"><!-- Serial# / Access# -->
										<col width="140" align="center"><!-- Base / Overage -->
										<col width="35"  align="center"><!-- Pricing Effectivities -->
										<tr height="28">
											<td class="pClothBs">Contract#</td>
											<td class="pClothBs">Serial#</td>
											<td class="pClothBs">Base / Overage</td>
											<td class="pClothBs"></td>
										</tr>
									</table>
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
										<col width="125" align="left" valign="center"><!-- Contract# -->
										<col width="140" align="left"><!-- Serial# / Access# -->
										<col width="140" align="left"><!-- Base / Overage -->
										<col width="35"  align="center"><!-- Pricing Effectivities -->
										<tr height="28">
											<td><ezf:inputText name="dsContrNum_H2" ezfName="dsContrNum_H2" value="1221220" otherAttr=" style=\"height:18px; vertical-align:middle;\" size=\"15\""/></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" otherAttr=" style=\"visibility:visible\""/></td>
										</tr>
									</table>
									<div style="overflow-x:hidden; width:459; overflow-y:scroll; height:422;">
										<table id="N" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
											<col width="125" align="left" valign="center"><!-- Contract# -->
											<col width="140" align="left"><!-- Serial# / Access# -->
											<col width="140" align="left"><!-- Base / Overage -->
											<col width="35"  align="center"><!-- Pricing Effectivities -->
											<% int idx2=-1; %>
											<ezf:row ezfHyo="N">
											<% idx2++; %>
												<tr height="28">
													<td>&nbsp;</td>
												<% if ("Y".equals((String)bMsg.N.no(idx2).xxDplyCtrlFlg_N.getValue())) { %>
													<td>
														<ezf:inputText name="serNum_N" ezfName="serNum_N" value="MSK10100" ezfHyo="N" ezfArrayNo="${idx2}" otherAttr=" style=\"height:18px; vertical-align:middle;\" size=\"15\""/>
														<ezf:anchor name="xxBtnFlg_N" ezfName="xxBtnFlg_N" ezfHyo="N" ezfArrayNo="${idx2}" ezfEmulateBtn="1" ezfGuard="NewCollapse" otherAttr=" ezfanchortext=\"\"">
														<%if ("Y".equals(bMsg.N.no(idx2).xxBtnFlg_N.getValue())) { %>
															<img src="./img/biz/downarrow2.png" border="0" value="Y">
														<% } else { %>
															<img src="./img/biz/rightarrow2.png" border="0" value="N">
														<% } %>
														</ezf:anchor>
													</td>
												<% } else { %>
													<td>&nbsp;</td>
												<% } %>
												<% if ("N".equals((String)bMsg.N.no(idx2).xxDplyCtrlFlg_N.getValue())) { %>
													<td><ezf:inputText name="mtrLbDescTxt_N" ezfName="mtrLbDescTxt_N" value="Base" ezfHyo="N" ezfArrayNo="${idx2}" otherAttr=" style=\"height:18px; vertical-align:middle;\" size=\"18\""/></td>
												<% } else { %>
													<td>&nbsp;</td>
												<% } %>
												<% if ("Y".equals((String)bMsg.N.no(idx2).xxDplyCtrlFlg_N.getValue())) { %>
													<td><ezf:inputCheckBox name="xxChkBox_N" ezfName="xxChkBox_N" value="Y" ezfHyo="N" ezfArrayNo="${idx2}" /></td>
												<% } else { %>
													<td>&nbsp;<ezf:inputHidden name="xxChkBox_N" ezfName="xxChkBox_N" ezfHyo="N" ezfArrayNo="${idx2}" /></td>
												<% } %>
												</tr>
											</ezf:row>

											<ezf:skip>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Base" name="mtrLbDescTxt_N" ezfname="mtrLbDescTxt_N" ezfhyo="N"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="18" value="Overage - BW"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											<tr height="28">
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle; visibility:hidden;" size="15" value=""><a href="#" name="xxLinkProt_A" ezfname="xxLinkProt_A" onclick="sendServer('Collapse_Contract')" style="visibility:hidden;"><img name="clps_00" src="./img/biz/downarrow2.png" style="height:18px; width:18px; vertical-align:middle; border:0; visibility:hidden;"></a></td>
												<td><input type="text" class="pPro" style="height:18px; vertical-align:middle;" size="15" value="Overage - CLR"></td>
												<td><input type="checkbox" name="xxCheckBox_A" value="" style="visibility:hidden"></td>
											</tr>
											</ezf:skip>
										</table>
									</div>
									<table width="100%" border="0">
										<tr>
											<td align="right">
												<ezf:inputButton name="NewSelectAll" value="Select All" htmlClass="pBtn7" />
												<ezf:inputButton name="NewUnSelectAll" value="Un Select All" htmlClass="pBtn7" />
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
