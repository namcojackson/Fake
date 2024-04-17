<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190110134029 --%>
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
			<input type="hidden" name="pageID" value="NMAL7420Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Filter Adjustment Table Filter Popup">
			
			
<center>
	<div style="height:560px; table-layout:fixed; overflow:scroll;">
	<table cellpadding="1" border="0" style="margin-top:4px;" id="mainTbl">
		<col align="left" width="160">
		<col align="left" width="450">
		<!-- Common parameter starts here -->
		<tr id="BH0">
			<td class="stab">Adjustment Table ID</td>
			<td><ezf:inputText name="prcRuleHdrPk_H1" ezfName="prcRuleHdrPk_H1" value="1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
		</tr>
		<tr id="BH1">
			<td class="stab">Table Name</td>
			<td><ezf:inputText name="prcRuleNm_H1" ezfName="prcRuleNm_H1" value="E-National IPA" otherAttr=" size=\"50\" maxlength=\"75\" ezftoupper=\"\""/></td>
		</tr>
		
		<!-- Common parameter ends here -->
		
		<!-- Dynamic parameter starts here -->
		
		
		<tr id="BH4">
			<td class="stab">CSMP#</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_04" ezfName="prcRuleCondFromTxt_04" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="csmpNumCmntTxt_04" ezfName="csmpNumCmntTxt_04" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_CsmpNum" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CsmpNum\""/>
			</td>
		</tr>
		<tr id="BH5">
			<td class="stab">Material Price Group</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_05" ezfName="prcRuleCondFromTxt_05" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prcGrpNm_05" ezfName="prcGrpNm_05" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PrcGrpMat" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcGrpMat\""/>
			</td>
		</tr>
		<tr id="BH6">
			<td class="stab">Prod Ctrl -1(BU)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_06" ezfName="prcRuleCondFromTxt_06" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prodCtrlNm_06" ezfName="prodCtrlNm_06" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ProdCtrl" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl\""/>
			</td>
		</tr>
		<tr id="BH7">
			<td class="stab">Prod Ctrl -2(Model Series)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_07" ezfName="prcRuleCondFromTxt_07" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prodCtrlNm_07" ezfName="prodCtrlNm_07" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ProdCtrl2" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl2\""/>
			</td>
		</tr>
		<tr id="BH8">
			<td class="stab">Mdse Type</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_08" ezfName="prcRuleCondFromTxt_08" otherAttr=" size=\"5\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="coaMdseTpDescTxt_08" ezfName="coaMdseTpDescTxt_08" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_CoaMdseTp" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CoaMdseTp\""/>
			</td>
		</tr>
		<tr id="BH9">
			<td class="stab">Product Code</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_09" ezfName="prcRuleCondFromTxt_09" otherAttr=" size=\"5\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="coaProdDescTxt_09" ezfName="coaProdDescTxt_09" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_CoaProd" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CoaProd\""/>
			</td>
		</tr>
		<tr id="BH62">
			<td class="stab">Manufacture#(*)</td>
			<td>
				<ezf:inputText name="xxPrcQlfyValSrchTxt_J3" ezfName="xxPrcQlfyValSrchTxt_J3" otherAttr=" size=\"25\" maxlength=\"10000\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH10">
			<td class="stab">Item Code</td>
			<td>
				<ezf:inputText name="xxPrcQlfyValSrchTxt_J1" ezfName="xxPrcQlfyValSrchTxt_J1" otherAttr=" size=\"25\" maxlength=\"10000\" ezftoupper=\"\""/>
				<ezf:inputText name="xxPrcQlfyValSrchTxt_J2" ezfName="xxPrcQlfyValSrchTxt_J2" otherAttr=" size=\"25\" maxlength=\"10000\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ItemSearch" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ItemSearch\""/>
			</td>
		</tr>
		<tr id="BH11">
			<td class="stab">Order Category</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_11" ezfName="prcRuleCondFromTxt_11" otherAttr=" size=\"5\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="dsOrdCatgDescTxt_11" ezfName="dsOrdCatgDescTxt_11" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_DSOrdCatg" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_DSOrdCatg\""/>
			</td>
		</tr>
		<tr id="BH12">
			<td class="stab">Order Reason</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_12" ezfName="prcRuleCondFromTxt_12" otherAttr=" size=\"5\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="dsOrdTpDescTxt_12" ezfName="dsOrdTpDescTxt_12" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_DSOrdTp" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_DSOrdTp\""/>
			</td>
		</tr>
		<tr id="BH13">
			<td class="stab">Order Line of Business</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_13" ezfName="prcRuleCondFromTxt_13" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="lineBizTpDescTxt_13" ezfName="lineBizTpDescTxt_13" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_LineBizTp" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_LineBizTp\""/>
			</td>
		</tr>
		<tr id="BH14">
			<td class="stab">Transaction Group</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_14" ezfName="prcRuleCondFromTxt_14" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prcGrpNm_14" ezfName="prcGrpNm_14" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PrcGrpTrx" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcGrpTrx\""/>
			</td>
		</tr>
		<tr id="BH15">
			<td class="stab">Total Transaction Weight From</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_AF" ezfName="prcRuleCondFromTxt_AF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondFromTxt_AT" ezfName="prcRuleCondFromTxt_AT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH15-2">
			<td class="stab">Total Transaction Weight To</td>
			<td>
				<ezf:inputText name="prcRuleCondThruTxt_AF" ezfName="prcRuleCondThruTxt_AF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondThruTxt_AT" ezfName="prcRuleCondThruTxt_AT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH16">
			<td class="stab">Bill To (Acct#)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_16" ezfName="prcRuleCondFromTxt_16" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="billToAcctNm_16" ezfName="billToAcctNm_16" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_BillTo" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_BillTo\""/>
			</td>
		</tr>
		<tr id="BH17">
			<td class="stab">Bill To Acct (Channel)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_17" ezfName="prcRuleCondFromTxt_17" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="coaChDescTxt_17" ezfName="coaChDescTxt_17" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_BillToAcctChnl" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_BillToAcctChnl\""/>
			</td>
		</tr>
		<tr id="BH18">
			<td class="stab">Bill To Acct (Classification)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_18" ezfName="prcRuleCondFromTxt_18" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="dsAcctClsDescTxt_18" ezfName="dsAcctClsDescTxt_18" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_BillToAcctClss" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_BillToAcctClss\""/>
			</td>
		</tr>
		<tr id="BH19">
			<td class="stab">Branch</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_19" ezfName="prcRuleCondFromTxt_19" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="coaBrDescTxt_19" ezfName="coaBrDescTxt_19" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Branch" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Branch\""/>
			</td>
		</tr>
		<tr id="BH20">
			<td class="stab">Call Type</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_20" ezfName="prcRuleCondFromTxt_20" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="svcCallTpDescTxt_20" ezfName="svcCallTpDescTxt_20" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_CallType" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CallType\""/>
			</td>
		</tr>
		<tr id="BH21">
			<td class="stab">Call Date From</td>
			<td>
				<ezf:inputText name="xxSvcCallDt_FF" ezfName="xxSvcCallDt_FF" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxSvcCallDt_FF', 4);" >
				-
				<ezf:inputText name="xxSvcCallDt_FT" ezfName="xxSvcCallDt_FT" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxSvcCallDt_FT', 4);" >
			</td>
		</tr>
		<tr id="BH21-2">
			<td class="stab">Call Date To</td>
			<td>
				<ezf:inputText name="xxSvcCallDt_TF" ezfName="xxSvcCallDt_TF" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxSvcCallDt_TF', 4);" >
				-
				<ezf:inputText name="xxSvcCallDt_TT" ezfName="xxSvcCallDt_TT" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxSvcCallDt_TT', 4);" >
			</td>
		</tr>
		<tr id="BH22">
			<td class="stab">Customer PO From</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_BF" ezfName="prcRuleCondFromTxt_BF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondFromTxt_BT" ezfName="prcRuleCondFromTxt_BT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH22-2">
			<td class="stab">Customer PO To</td>
			<td>
				<ezf:inputText name="prcRuleCondThruTxt_BF" ezfName="prcRuleCondThruTxt_BF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondThruTxt_BT" ezfName="prcRuleCondThruTxt_BT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH24">
			<td class="stab">Line Amount From</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_CF" ezfName="prcRuleCondFromTxt_CF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondFromTxt_CT" ezfName="prcRuleCondFromTxt_CT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH24-2">
			<td class="stab">Line Amount To</td>
			<td>
				<ezf:inputText name="prcRuleCondThruTxt_CF" ezfName="prcRuleCondThruTxt_CF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondThruTxt_CT" ezfName="prcRuleCondThruTxt_CT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH25">
			<td class="stab">Line Category (Line Type)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_25" ezfName="prcRuleCondFromTxt_25" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="dsOrdLineCatgDescTxt_25" ezfName="dsOrdLineCatgDescTxt_25" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_LineCatg" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_LineCatg\""/>
			</td>
		</tr>
		<tr id="BH26">
			<td class="stab">Line Qty From</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_DF" ezfName="prcRuleCondFromTxt_DF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondFromTxt_DT" ezfName="prcRuleCondFromTxt_DT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH26-2">
			<td class="stab">Line Qty To</td>
			<td>
				<ezf:inputText name="prcRuleCondThruTxt_DF" ezfName="prcRuleCondThruTxt_DF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondThruTxt_DT" ezfName="prcRuleCondThruTxt_DT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH27">
			<td class="stab">Marketing Model Name</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_27" ezfName="prcRuleCondFromTxt_27" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="mktMdlDescTxt_27" ezfName="mktMdlDescTxt_27" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_MarketMdlNm" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MarketMdlNm\""/>
			</td>
		</tr>
		<tr id="BH28">
			<td class="stab">Model Segment</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_28" ezfName="prcRuleCondFromTxt_28" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="mktMdlSegDescTxt_28" ezfName="mktMdlSegDescTxt_28" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ModelSeg" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ModelSeg\""/>
			</td>
		</tr>
		<tr id="BH29">
			<td class="stab">Order Source</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_29" ezfName="prcRuleCondFromTxt_29" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="cpoSrcTpDescTxt_29" ezfName="cpoSrcTpDescTxt_29" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_OrderSrc" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_OrderSrc\""/>
			</td>
		</tr>
		<tr id="BH30">
			<td class="stab">Order Subtotal From</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_EF" ezfName="prcRuleCondFromTxt_EF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondFromTxt_ET" ezfName="prcRuleCondFromTxt_ET" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH30-2">
			<td class="stab">Order Subtotal To</td>
			<td>
				<ezf:inputText name="prcRuleCondThruTxt_EF" ezfName="prcRuleCondThruTxt_EF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondThruTxt_ET" ezfName="prcRuleCondThruTxt_ET" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH31">
			<td class="stab">Payment Type</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_31" ezfName="prcRuleCondFromTxt_31" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="dsPmtMethDescTxt_31" ezfName="dsPmtMethDescTxt_31" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PaymentTp" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PaymentTp\""/>
			</td>
		</tr>
		<tr id="BH32">
			<td class="stab">Price List</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_32" ezfName="prcRuleCondFromTxt_32" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prcCatgNm_32" ezfName="prcCatgNm_32" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PrcList" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcList\""/>
			</td>
		</tr>
		<tr id="BH33">
			<td class="stab">Pricing Date From</td>
			<td>
				<ezf:inputText name="prcDt_FF" ezfName="prcDt_FF" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('prcDt_FF', 4);" >
				-
				<ezf:inputText name="prcDt_FT" ezfName="prcDt_FT" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('prcDt_FT', 4);" >
			</td>
		</tr>
		<tr id="BH33-2">
			<td class="stab">Pricing Date To</td>
			<td>
				<ezf:inputText name="prcDt_TF" ezfName="prcDt_TF" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('prcDt_TF', 4);" >
				-
				<ezf:inputText name="prcDt_TT" ezfName="prcDt_TT" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('prcDt_TT', 4);" >
			</td>
		</tr>
		<tr id="BH34">
			<td class="stab">Prod Ctrl -3(Product)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_34" ezfName="prcRuleCondFromTxt_34" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prodCtrlNm_34" ezfName="prodCtrlNm_34" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ProdCtrl3" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl3\""/>
			</td>
		</tr>
		<tr id="BH35">
			<td class="stab">Prod Ctrl -4(Product-Group)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_35" ezfName="prcRuleCondFromTxt_35" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prodCtrlNm_35" ezfName="prodCtrlNm_35" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ProdCtrl4" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl4\""/>
			</td>
		</tr>
		<tr id="BH36">
			<td class="stab">Prod Ctrl -5(Product-Type)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_36" ezfName="prcRuleCondFromTxt_36" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prodCtrlNm_36" ezfName="prodCtrlNm_36" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ProdCtrl5" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl5\""/>
			</td>
		</tr>
		<tr id="BH37">
			<td class="stab">Request Date From</td>
			<td>
				<ezf:inputText name="xxRqstDt_FF" ezfName="xxRqstDt_FF" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxRqstDt_FF', 4);" >
				-
				<ezf:inputText name="xxRqstDt_FT" ezfName="xxRqstDt_FT" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxRqstDt_FT', 4);" >
			</td>
		</tr>
		<tr id="BH37-2">
			<td class="stab">Request Date To</td>
			<td>
				<ezf:inputText name="xxRqstDt_TF" ezfName="xxRqstDt_TF" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxRqstDt_TF', 4);" >
				-
				<ezf:inputText name="xxRqstDt_TT" ezfName="xxRqstDt_TT" value="yyyy/MM/dd" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxRqstDt_TT', 4);" >
			</td>
		</tr>
		<tr id="BH38">
			<td class="stab">Return Reason Code</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_38" ezfName="prcRuleCondFromTxt_38" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="rtrnRsnDescTxt_38" ezfName="rtrnRsnDescTxt_38" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_RtnRsnCd" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_RtnRsnCd\""/>
			</td>
		</tr>
		<tr id="BH39">
			<td class="stab">Service Level</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_39" ezfName="prcRuleCondFromTxt_39" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="shpgSvcLvlDescTxt_39" ezfName="shpgSvcLvlDescTxt_39" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ServiceLv" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ServiceLv\""/>
			</td>
		</tr>
		<tr id="BH40">
			<td class="stab">Service Model</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_40" ezfName="prcRuleCondFromTxt_40" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="mdlNm_40" ezfName="mdlNm_40" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ServiceMdl" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ServiceMdl\""/>
			</td>
		</tr>
		<tr id="BH41">
			<td class="stab">Service Zone</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_41" ezfName="prcRuleCondFromTxt_41" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prcSvcZoneDescTxt_41" ezfName="prcSvcZoneDescTxt_41" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ServiceZone" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ServiceZone\""/>
			</td>
		</tr>
		<tr id="BH42">
			<td class="stab">Ship to Acct (Classification)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_42" ezfName="prcRuleCondFromTxt_42" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="dsAcctClsDescTxt_42" ezfName="dsAcctClsDescTxt_42" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_ShipToAcct" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ShipToAcct\""/>
			</td>
		</tr>
		<tr id="BH44">
			<td class="stab">Special Handling Type</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_44" ezfName="prcRuleCondFromTxt_44" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="spclHdlgTpDescTxt_44" ezfName="spclHdlgTpDescTxt_44" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_SpecialHandTp" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SpecialHandTp\""/>
			</td>
		</tr>
		<tr id="BH45">
			<td class="stab">Total Qty From</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_FF" ezfName="prcRuleCondFromTxt_FF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondFromTxt_FT" ezfName="prcRuleCondFromTxt_FT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH45-2">
			<td class="stab">Total Qty To</td>
			<td>
				<ezf:inputText name="prcRuleCondThruTxt_FF" ezfName="prcRuleCondThruTxt_FF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondThruTxt_FT" ezfName="prcRuleCondThruTxt_FT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH46">
			<td class="stab">Business Unit</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_46" ezfName="prcRuleCondFromTxt_46" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="coaExtnDescTxt_46" ezfName="coaExtnDescTxt_46" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_BizUnit" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_BizUnit\""/>
			</td>
		</tr>
		<tr id="BH48">
			<td class="stab">Freight Term</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_48" ezfName="prcRuleCondFromTxt_48" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="frtCondDescTxt_48" ezfName="frtCondDescTxt_48" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_FreightTerm" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_FreightTerm\""/>
			</td>
		</tr>
		<tr id="BH49">
			<td class="stab">Freight Zone</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_49" ezfName="prcRuleCondFromTxt_49" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="fill40Txt_49" ezfName="fill40Txt_49" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_FreightZone" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_FreightZone\""/>
			</td>
		</tr>
		<tr id="BH53">
			<td class="stab">Customer Price Group(Sold To)</td>
			<td>
				<ezf:inputText name="xxPrcQlfyValSrchTxt_53" ezfName="xxPrcQlfyValSrchTxt_53" otherAttr=" size=\"25\" maxlength=\"10000\" ezftoupper=\"\""/>
				<ezf:inputText name="prcGrpNm_53" ezfName="prcGrpNm_53" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PrcGrpCustSold" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcGrpCustSold\""/>
			</td>
		</tr>
		<tr id="BH54">
			<td class="stab">Sold To (Acct#)</td>
			<td>
				<ezf:inputText name="xxPrcQlfyValSrchTxt_K1" ezfName="xxPrcQlfyValSrchTxt_K1" otherAttr=" size=\"25\" maxlength=\"10000\" ezftoupper=\"\""/>
				<ezf:inputText name="xxPrcQlfyValSrchTxt_K2" ezfName="xxPrcQlfyValSrchTxt_K2" otherAttr=" size=\"25\" maxlength=\"10000\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_AcctNumCustSold" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_AcctNumCustSold\""/>
			</td>
		</tr>
		<tr id="BH55">
			<td class="stab">Sold To Acct (Channel)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_55" ezfName="prcRuleCondFromTxt_55" otherAttr=" size=\"5\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="coaChDescTxt_55" ezfName="coaChDescTxt_55" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_CoaChSold" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CoaChSold\""/>
			</td>
		</tr>
		<tr id="BH56">
			<td class="stab">Sold To Acct (Classification)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_56" ezfName="prcRuleCondFromTxt_56" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="dsAcctClsDescTxt_56" ezfName="dsAcctClsDescTxt_56" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_DsAcctClsSold" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_DsAcctClsSold\""/>
			</td>
		</tr>
		<tr id="BH57">
			<td class="stab">Material Price Group(QTY Break)</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_57" ezfName="prcRuleCondFromTxt_57" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prcGrpNm_57" ezfName="prcGrpNm_57" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PrcGrpMatQtyBrk" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcGrpMatQtyBrk\""/>
			</td>
		</tr>
		<tr id="BH58">
			<td class="stab">Line Qty(QTY Break) From</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_GF" ezfName="prcRuleCondFromTxt_GF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondFromTxt_GT" ezfName="prcRuleCondFromTxt_GT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH58-2">
			<td class="stab">Line Qty(QTY Break) To</td>
			<td>
				<ezf:inputText name="prcRuleCondThruTxt_GF" ezfName="prcRuleCondThruTxt_GF" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				-
				<ezf:inputText name="prcRuleCondThruTxt_GT" ezfName="prcRuleCondThruTxt_GT" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH59">
			<td class="stab">Material Group 1</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_59" ezfName="prcRuleCondFromTxt_59" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="slsMatGrpDescTxt_59" ezfName="slsMatGrpDescTxt_59" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_SlsMatGrpDescTxt1" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SlsMatGrpDescTxt1\""/>
			</td>
		</tr>
		<tr id="BH60">
			<td class="stab">Material Group 2</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_60" ezfName="prcRuleCondFromTxt_60" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="slsMatGrpDescTxt_60" ezfName="slsMatGrpDescTxt_60" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_SlsMatGrpDescTxt2" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SlsMatGrpDescTxt2\""/>
			</td>
		</tr>
		<tr id="BH61">
			<td class="stab">Material Group 3</td>
			<td>
				<ezf:inputText name="prcRuleCondFromTxt_61" ezfName="prcRuleCondFromTxt_61" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="slsMatGrpDescTxt_61" ezfName="slsMatGrpDescTxt_61" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_SlsMatGrpDescTxt3" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SlsMatGrpDescTxt3\""/>
			</td>
		</tr>
		<tr id="BH50">
			<td class="stab">Formula</td>
			<td>
				<ezf:inputText name="prcFmlaPk" ezfName="prcFmlaPk" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputText name="prcFmlaNm" ezfName="prcFmlaNm" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Formula" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Formula\""/>
			</td>
		</tr>
		<tr id="BH51">
			<td class="stab">Percent</td>
			<td>
				<ezf:inputText name="prcRuleDtlRate" ezfName="prcRuleDtlRate" value="0" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH52">
			<td class="stab">Value</td>
			<td>
				<ezf:inputText name="prcRuleDtlTxt" ezfName="prcRuleDtlTxt" value="0" otherAttr=" size=\"12\" maxlength=\"13\" ezftoupper=\"\""/>
			</td>
		</tr>
		<!-- Dynamic parameter ends here -->
		
		<!-- Common parameter starts here -->
		<tr id="BH90"></tr>
		<tr id="BH97"></tr>
		<tr id="BH98">
			<td class="stab">Date From</td>
			<td>
				<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);">
				-
				<ezf:inputText name="effFromDt_H2" ezfName="effFromDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H2', 4);">
			</td>
		</tr>
		<tr id="BH99">
			<td class="stab">Date To</td>
			<td>
				<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);">
				-
				<ezf:inputText name="effThruDt_H2" ezfName="effThruDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H2', 4);">
			</td>
		</tr>
		<tr id="BH93">
			<td class="stab">Created By</td>
			<td>
				<ezf:inputText name="xxFullNm_H1" ezfName="xxFullNm_H1" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH94">
			<td class="stab">Created Date</td>
			<td>
				<ezf:inputText name="cratDt_H1" ezfName="cratDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_H1', 4);">
				-
				<ezf:inputText name="cratDt_H2" ezfName="cratDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_H2', 4);">
			</td>
		</tr>
		<tr id="BH95">
			<td class="stab">Update By</td>
			<td>
				<ezf:inputText name="xxFullNm_H2" ezfName="xxFullNm_H2" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr id="BH96">
			<td class="stab">Update Date</td>
			<td>
				<ezf:inputText name="lastUpdDt_H1" ezfName="lastUpdDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('lastUpdDt_H1', 4);">
				-
				<ezf:inputText name="lastUpdDt_H2" ezfName="lastUpdDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('lastUpdDt_H2', 4);">
			</td>
		</tr>
		<!-- Common parameter ends here -->
		
	</table>
	<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
	</div>
</center>
<script type="text/javascript" defer>

function displayCal(){

	var dynamicAttrb = document.getElementById("xxComnColOrdTxt").value;
	if(!dynamicAttrb){
		return;
		//dynamicAttrb = "BH0:BH1:BH5:BH6:BH94:BH95:BH96:BH97:BH98:BH99";
	}

	var mainTbl = document.getElementById("mainTbl");
	var attrb = mainTbl.getElementsByTagName("tr");
	var attrbLen = attrb.length;
	for (var i = 0; i < attrbLen; i++) {
    	attrb[i].style.display = 'none';
	}
    
    var calId =  dynamicAttrb.split(":");
    var len = calId.length;
	for (var i = 0; i < len; i++) {
    	var selectedCol = document.getElementById(calId[i]);
    	selectedCol.style.display = 'block';
	}
   }
	


</script>

<script type="text/javascript" defer>
	displayCal();
</script>


<%-- **** Task specific area ends here **** --%>
