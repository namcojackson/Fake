<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160630233003 --%>
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
			<input type="hidden" name="pageID" value="NFCL3130Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Receipt Sources Maintenance">

<center>
<!-- ######################################## HEADER ######################################## -->
<%-- ###TAB - HEAD --%>
<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
<table border="0" cellpadding="1" cellspacing="0">
        <tr>
		<td>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Actual Counters for Interface" class="pTab_ON"><a href="#">Col Search</a></li>
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
		</td>
        </tr>
	<tr height="200">
		<td align="center">
			<table border="0" cellpadding="1" cellspacing="0">
				<col width="100">
				<col width="300">
				<col width="100">
				<col width="300">
				<tr>
					<td class="stab" align="right"><ezf:anchor name="Click_rcptSrc" ezfName="Click_rcptSrc" ezfEmulateBtn="1" ezfGuard="Click_rcptSrc" otherAttr=" id=\"Click_rcptSrc\" ezfanchortext=\"\" tabindex=\"4001\""><label>Name：</label></ezf:anchor></td>
					<td><ezf:inputText name="arRcptSrcNm" ezfName="arRcptSrcNm" value="WWWWWWW" otherAttr=" size=\"45\""/></td>
					<td class="stab" align="right">Discription：</td>
					<td><ezf:inputText name="arRcptSrcDescTxt" ezfName="arRcptSrcDescTxt" value="WWWWWWW" otherAttr=" size=\"45\""/></td>
				</tr>
				<tr>
					<td class="stab" align="right">Type：</td>
					<td>
						<ezf:select name="arRcptSrcTpCd_S1" ezfName="arRcptSrcTpCd_S1" ezfBlank="1" ezfCodeName="arRcptSrcTpCd" ezfDispName="xxMsgTxt_D1" otherAttr=" style=\"width:285px;\" tabindex=\"-1\""/>
					</td>
					<td class="stab" align="right">Active：</td>
					<td><ezf:inputCheckBox name="ezCancelFlag" ezfName="ezCancelFlag" value="Y" otherAttr=" id=\"xxChkBox\" tabindex=\"4001\""/></td>
				</tr>
				<tr>
					<td class="stab" align="right"><ezf:anchor name="Click_bankAcct" ezfName="Click_bankAcct" ezfEmulateBtn="1" ezfGuard="Click_bankAcct" otherAttr=" id=\"Click_rcptSrc\" ezfanchortext=\"\" tabindex=\"4001\""><label>Bank Account：</label></ezf:anchor></td>
					<td colspan="3"><ezf:inputText name="dsBankAcctNm" ezfName="dsBankAcctNm" value="WWWWWWW" otherAttr=" size=\"108\""/></td>
				</tr>
			</table>
		</td>
	</tr>
<hr>
	<tr>
		<td align="center">
			<table border="1" cellpadding="1" cellspacing="0">
				<col width="130">
				<col width="500">
				<tr>
					<td class="pClothBs">Cash：</td>
					<td align="ritght">
						<ezf:inputText name="xxScrItem61Txt_C1" ezfName="xxScrItem61Txt_C1" value="WWWWWWW" otherAttr=" size=\"73\""/>
						<ezf:inputButton name="openCashCOA" value="COA" htmlClass="pBtn01" otherAttr=" id=\"openCashCOA\""/>
					</td>
				<tr>
				<tr>
					<td class="pClothBs">Receipt Confirmation：</td>
					<td align="ritght">
						<ezf:inputText name="xxScrItem61Txt_C2" ezfName="xxScrItem61Txt_C2" value="WWWWWWW" otherAttr=" size=\"73\""/>
						<ezf:inputButton name="openRcptCnfCOA" value="COA" htmlClass="pBtn02" otherAttr=" id=\"openRcptCnfCOA\""/>
					</td>
				</tr>
				<tr>
					<td class="pClothBs">Remmittance：</td>
					<td align="ritght">
						<ezf:inputText name="xxScrItem61Txt_C3" ezfName="xxScrItem61Txt_C3" value="WWWWWWW" otherAttr=" size=\"73\""/>
						<ezf:inputButton name="openRemmitanceCOA" value="COA" htmlClass="pBtn03" otherAttr=" id=\"openRemmitanceCOA\""/>
					</td>
				</tr>
				<tr>
					<td class="pClothBs">Unapplied Receipts：</td>
					<td align="ritght">
						<ezf:inputText name="xxScrItem61Txt_C4" ezfName="xxScrItem61Txt_C4" value="WWWWWWW" otherAttr=" size=\"73\""/>
						<ezf:inputButton name="openUnappliedRcpt" value="COA" htmlClass="pBtn04" otherAttr=" id=\"openUnappliedRcpt\""/>
					</td>
				</tr>
				<tr>
					<td class="pClothBs">Undentified Receipts：</td>
					<td align="ritght">
						<ezf:inputText name="xxScrItem61Txt_C5" ezfName="xxScrItem61Txt_C5" value="WWWWWWW" otherAttr=" size=\"73\""/>
						<ezf:inputButton name="openUndentifiedRcpt" value="COA" htmlClass="pBtn05" otherAttr=" id=\"openUndentifiedRcpt\""/>
					</td>
				</tr>
				<tr>
					<td class="pClothBs">On Account Receipts：</td>
					<td align="ritght">
						<ezf:inputText name="xxScrItem61Txt_C6" ezfName="xxScrItem61Txt_C6" value="WWWWWWW" otherAttr=" size=\"73\""/>
						<ezf:inputButton name="openOnAccountRcpt" value="COA" htmlClass="pBtn06" otherAttr=" id=\"openOnAccountRcpt\""/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr height="100">
		<td align="center">
			<table border="1" cellpadding="1" cellspacing="0">
				<col width="130">
				<col width="500">
				<tr>
					<td class="stab">Discription：</td>
					<td align="ritght"><ezf:inputText name="xxSignaDataDescTxt" ezfName="xxSignaDataDescTxt" value="WWWWWWW" otherAttr=" size=\"73\""/></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</center>





<%-- **** Task specific area ends here **** --%>
