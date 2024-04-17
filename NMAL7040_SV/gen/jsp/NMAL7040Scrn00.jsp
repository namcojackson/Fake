<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181206134631 --%>
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
			<input type="hidden" name="pageID" value="NMAL7040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price List Filter Popup">

<!-- Price List Filter Popup HTML start -->

<center>
	<div style="height:560px; table-layout:fixed; overflow:scroll;">
	<table cellpadding="1" border="0" style="margin-top:4px;" id="mainTbl">
		<col align="left" width="130">
		<col align="left" width="450">
		<!-- Common parameter -->
		<tr>
			<td class="stab">Price List ID</td>
			<td><ezf:inputText name="prcCatgCd_H" ezfName="prcCatgCd_H" value="445" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
		</tr>
		<tr>
			<td class="stab">Price List Name</td>
			<td><ezf:inputText name="prcCatgNm_H" ezfName="prcCatgNm_H" value="E-National IPA" otherAttr=" size=\"50\" maxlength=\"75\" ezftoupper=\"\""/></td>
		</tr>

	<c:choose>
	<c:when test="${(pageScope._ezddatabean.prcListStruTpCd_H == '01')}">
	<!-- '01': Equipment -->
		<tr>
			<td class="stab">Price Config#</td>
			<td>
				<ezf:inputText name="prcListEquipConfigNum" ezfName="prcListEquipConfigNum" otherAttr=" size=\"30\" maxlength=\"3\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Price Config Name(*)</td>
			<td>
				<ezf:inputText name="prcListEquipConfigNm" ezfName="prcListEquipConfigNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Qualifier Type</td>
			<td>
				<ezf:select name="prcQlfyTpCd" ezfName="prcQlfyTpCd" ezfBlank="1" ezfCodeName="prcQlfyTpCd_L" ezfDispName="prcQlfyTpDescTxt_L" otherEvent1=" onchange=\"sendServer('OnChange_PrcQlfyTp')\"" />
			</td>
		</tr>
		<tr>
			<td class="stab">Qualifier Value(*)</td>
			<td>
				<ezf:inputText name="xxPrcQlfyValSrchTxt" ezfName="xxPrcQlfyValSrchTxt" otherAttr=" size=\"30\" maxlength=\"10000\" ezftoupper=\"\""/>
		<c:choose>
		<c:when test="${(pageScope._ezddatabean.prcQlfyTpCd != '')}">
			<c:choose>
			<c:when test="${(pageScope._ezddatabean.prcQlfyTpCd == '01')}">
				<ezf:inputButton name="OpenWin_QualifierValueItem" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueItem\""/>
			</c:when>
			<c:when test="${(pageScope._ezddatabean.prcQlfyTpCd == '02')}">
				<ezf:inputButton name="OpenWin_QualifierValueMdseTp" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueMdseTp\""/>
			</c:when>
			<c:when test="${(pageScope._ezddatabean.prcQlfyTpCd != '01' && pageScope._ezddatabean.prcQlfyTpCd != '02')}">
				<ezf:inputButton name="OpenWin_QualifierValueProd" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_QualifierValueProd\""/>
			</c:when>
			</c:choose>
		</c:when>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td class="stab">Manufacture#(*)</td>
			<td>
				<ezf:inputText name="xxMnfItemCdSrchTxt" ezfName="xxMnfItemCdSrchTxt" otherAttr=" size=\"30\" maxlength=\"10000\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Description(*)</td>
			<td>
				<ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Mdse Type</td>
			<td>
				<ezf:inputText name="coaProjNm" ezfName="coaProjNm" otherAttr=" size=\"30\" maxlength=\"240\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Item Type</td>
			<td>
				<ezf:inputText name="mdseItemTpNm" ezfName="mdseItemTpNm" otherAttr=" size=\"30\" maxlength=\"40\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Prod Code</td>
			<td>
				<ezf:inputText name="coaProdNm" ezfName="coaProdNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Model(*)</td>
			<td>
				<ezf:inputText name="xxTMdlNmSrchTxt" ezfName="xxTMdlNmSrchTxt" otherAttr=" size=\"30\" maxlength=\"10000\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab"><ezf:label name="mdseStruElmntTpDescTxt_T1" ezfName="mdseStruElmntTpDescTxt_T1" /></td>
			<td>
				<ezf:inputText name="zerothProdCtrlCd" ezfName="zerothProdCtrlCd" otherAttr=" size=\"30\" maxlength=\"8\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab"><ezf:label name="mdseStruElmntTpDescTxt_T2" ezfName="mdseStruElmntTpDescTxt_T2" /></td>
			<td>
				<ezf:inputText name="firstProdCtrlCd" ezfName="firstProdCtrlCd" otherAttr=" size=\"30\" maxlength=\"8\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab"><ezf:label name="mdseStruElmntTpDescTxt_T3" ezfName="mdseStruElmntTpDescTxt_T3" /></td>
			<td>
				<ezf:inputText name="scdProdCtrlCd" ezfName="scdProdCtrlCd" otherAttr=" size=\"30\" maxlength=\"8\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab"><ezf:label name="mdseStruElmntTpDescTxt_T4" ezfName="mdseStruElmntTpDescTxt_T4" /></td>
			<td>
				<ezf:inputText name="thirdProdCtrlCd" ezfName="thirdProdCtrlCd" otherAttr=" size=\"30\" maxlength=\"8\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab"><ezf:label name="mdseStruElmntTpDescTxt_T5" ezfName="mdseStruElmntTpDescTxt_T5" /></td>
			<td>
				<ezf:inputText name="frthProdCtrlCd" ezfName="frthProdCtrlCd" otherAttr=" size=\"30\" maxlength=\"8\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">UOM</td>
			<td>
				<ezf:select name="pkgUomCd" ezfName="pkgUomCd" ezfBlank="1" ezfCodeName="pkgUomCd_L" ezfDispName="pkgUomDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Term</td>
			<td>
				<ezf:inputText name="prcTermAot" ezfName="prcTermAot" otherAttr=" size=\"30\" maxlength=\"7\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Term UOM</td>
			<td>
				<ezf:select name="prcTermUomCd" ezfName="prcTermUomCd" ezfBlank="1" ezfCodeName="prcTermUomCd_L" ezfDispName="prcTermUomDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Bid Qty</td>
			<td>
				<ezf:inputText name="custBidQty" ezfName="custBidQty" otherAttr=" size=\"30\" maxlength=\"10\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Price Formula</td>
			<td>
				<ezf:inputText name="prcFmlaNm" ezfName="prcFmlaNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Open Market</td>
			<td>
				<ezf:inputRadio name="openMktFlg" ezfName="openMktFlg" value="" />All
				<ezf:inputRadio name="openMktFlg" ezfName="openMktFlg" value="Y" />Yes
				<ezf:inputRadio name="openMktFlg" ezfName="openMktFlg" value="N" />No
			</td>
		</tr>
		<tr>
			<td class="stab">Comp Code</td>
			<td>
				<ezf:inputText name="compCd" ezfName="compCd" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/>
			</td>
		</tr>
	</c:when>

	<c:when test="${(pageScope._ezddatabean.prcListStruTpCd_H == '02')}">
	<!-- '02': Service -->
		<tr>
			<td class="stab">Rate Type</td>
			<td>
				<ezf:select name="prcRateTpCd" ezfName="prcRateTpCd" ezfBlank="1" ezfCodeName="prcRateTpCd_L" ezfDispName="prcRateTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Service Model Name(*)</td>
			<td>
				<ezf:inputText name="mdlNm" ezfName="mdlNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Model" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Meter Package Name(*)</td>
			<td>
				<ezf:inputText name="prcMtrPkgNm" ezfName="prcMtrPkgNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_MtrPkg" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MtrPkg\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Item#(*)</td>
			<td>
				<ezf:inputText name="prcListMdseCd" ezfName="prcListMdseCd" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PrcListMdse" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Item Description(*)</td>
			<td>
				<ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Plan Type</td>
			<td>
				<ezf:select name="prcSvcPlnTpCd" ezfName="prcSvcPlnTpCd" ezfBlank="1" ezfCodeName="prcSvcPlnTpCd_L" ezfDispName="prcSvcPlnTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Contract Type</td>
			<td>
				<ezf:select name="prcSvcContrTpCd" ezfName="prcSvcContrTpCd" ezfBlank="1" ezfCodeName="prcSvcContrTpCd_L" ezfDispName="prcSvcContrTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Meter Type</td>
			<td>
				<ezf:inputText name="mtrLbNm" ezfName="mtrLbNm" value="WWWWWWWWW1WWWWWWWWW1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_MtrLb" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Band</td>
			<td>
				<ezf:inputText name="prcListBandDescTxt" ezfName="prcListBandDescTxt" value="WWWWWWWWW1WWWWWWWWW1" otherAttr=" size=\"4\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PrcListBand" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Term From(MTH)</td>
			<td>
				<ezf:inputText name="termFromMthAot" ezfName="termFromMthAot" otherAttr=" size=\"30\" maxlength=\"3\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Term Thru(MTH)</td>
			<td>
				<ezf:inputText name="termThruMthAot" ezfName="termThruMthAot" otherAttr=" size=\"30\" maxlength=\"3\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Service Zone(s) Included</td>
			<td>
				<ezf:select name="prcSvcZoneCd" ezfName="prcSvcZoneCd" ezfBlank="1" ezfCodeName="prcSvcZoneCd_L" ezfDispName="prcSvcZoneDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Service Item(*)</td>
			<td>
				<ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Mdse" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
	</c:when>

	<c:when test="${(pageScope._ezddatabean.prcListStruTpCd_H == '03')}">
	<!-- '03': Service Tiers -->
		<tr>
			<td class="stab">Tier Type</td>
			<td>
				<ezf:select name="prcSvcTierTpCd" ezfName="prcSvcTierTpCd" ezfBlank="1" ezfCodeName="prcSvcTierTpCd_L" ezfDispName="prcSvcTierTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Service Model Name(*)</td>
			<td>
				<ezf:inputText name="mdlNm" ezfName="mdlNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Model" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Meter Package Name(*)</td>
			<td>
				<ezf:inputText name="prcMtrPkgNm" ezfName="prcMtrPkgNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_MtrPkg" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MtrPkg\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Plan Type</td>
			<td>
				<ezf:select name="prcSvcPlnTpCd" ezfName="prcSvcPlnTpCd" ezfBlank="1" ezfCodeName="prcSvcPlnTpCd_L" ezfDispName="prcSvcPlnTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Contract Type</td>
			<td>
				<ezf:select name="prcSvcContrTpCd" ezfName="prcSvcContrTpCd" ezfBlank="1" ezfCodeName="prcSvcContrTpCd_L" ezfDispName="prcSvcContrTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Meter Type</td>
			<td>
				<ezf:inputText name="mtrLbNm" ezfName="mtrLbNm" value="WWWWWWWWW1WWWWWWWWW1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_MtrLb" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Band</td>
			<td>
				<ezf:inputText name="prcListBandDescTxt" ezfName="prcListBandDescTxt" value="WWWWWWWWW1WWWWWWWWW1" otherAttr=" size=\"4\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PrcListBand" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Term From(MTH)</td>
			<td>
				<ezf:inputText name="termFromMthAot" ezfName="termFromMthAot" otherAttr=" size=\"30\" maxlength=\"3\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Term Thru(MTH)</td>
			<td>
				<ezf:inputText name="termThruMthAot" ezfName="termThruMthAot" otherAttr=" size=\"30\" maxlength=\"3\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Service Item(*)</td>
			<td>
				<ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Mdse" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
	</c:when>

	<c:when test="${(pageScope._ezddatabean.prcListStruTpCd_H == '04')}">
	<!-- '04': Service Special Charges -->
		<tr>
			<td class="stab">Service Item(*)</td>
			<td>
				<ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Mdse" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Service Item Description(*)</td>
			<td>
				<ezf:inputText name="mdseDescShortTxt_H2" ezfName="mdseDescShortTxt_H2" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Charge Type</td>
			<td>
				<ezf:select name="prcAddlChrgTpCd" ezfName="prcAddlChrgTpCd" ezfBlank="1" ezfCodeName="prcAddlChrgTpCd_L" ezfDispName="prcAddlChrgTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Item Segment</td>
			<td>
				<ezf:select name="mktMdlSegCd" ezfName="mktMdlSegCd" ezfBlank="1" ezfCodeName="mktMdlSegCd_L" ezfDispName="mktMdlSegDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Service Model Name(*)</td>
			<td>
				<ezf:inputText name="mdlNm" ezfName="mdlNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Model" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model\""/>
			</td>
		</tr>
	</c:when>

	<c:when test="${(pageScope._ezddatabean.prcListStruTpCd_H == '05')}">
	<!-- '05': Supply Program -->
		<tr>
			<td class="stab">Service Model Name(*)</td>
			<td>
				<ezf:inputText name="mdlNm" ezfName="mdlNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Model" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Meter Package Name(*)</td>
			<td>
				<ezf:inputText name="prcMtrPkgNm" ezfName="prcMtrPkgNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_MtrPkg" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MtrPkg\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Plan Type</td>
			<td>
				<ezf:select name="prcSvcPlnTpCd" ezfName="prcSvcPlnTpCd" ezfBlank="1" ezfCodeName="prcSvcPlnTpCd_L" ezfDispName="prcSvcPlnTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Contract Type</td>
			<td>
				<ezf:select name="prcSvcContrTpCd" ezfName="prcSvcContrTpCd" ezfBlank="1" ezfCodeName="prcSvcContrTpCd_L" ezfDispName="prcSvcContrTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Meter Type</td>
			<td>
				<ezf:inputText name="mtrLbNm" ezfName="mtrLbNm" value="WWWWWWWWW1WWWWWWWWW1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_MtrLb" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Band</td>
			<td>
				<ezf:inputText name="prcListBandDescTxt" ezfName="prcListBandDescTxt" value="WWWWWWWWW1WWWWWWWWW1" otherAttr=" size=\"4\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_PrcListBand" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Term From(MTH)</td>
			<td>
				<ezf:inputText name="termFromMthAot" ezfName="termFromMthAot" otherAttr=" size=\"30\" maxlength=\"3\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Term Thru(MTH)</td>
			<td>
				<ezf:inputText name="termThruMthAot" ezfName="termThruMthAot" otherAttr=" size=\"30\" maxlength=\"3\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Service Item(*)</td>
			<td>
				<ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Mdse" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Service Zone(s) Included</td>
			<td>
				<ezf:select name="prcSvcZoneCd" ezfName="prcSvcZoneCd" ezfBlank="1" ezfCodeName="prcSvcZoneCd_L" ezfDispName="prcSvcZoneDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Supply Plan(*)</td>
			<td>
				<ezf:inputText name="splyAgmtPlnNm" ezfName="splyAgmtPlnNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_SupplyPlan" value="..." htmlClass="pBtn0" />
			</td>
		</tr>

	</c:when>

	<c:when test="${(pageScope._ezddatabean.prcListStruTpCd_H == '06')}">
	<!-- '06': Lease Rates -->
		<tr>
			<td class="stab">Lease Company Abbr(*)</td>
			<td>
				<ezf:inputText name="prcLeaseCmpyAbbrNm" ezfName="prcLeaseCmpyAbbrNm" otherAttr=" size=\"30\" maxlength=\"10\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Lease Company Name(*)</td>
			<td>
				<ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" otherAttr=" size=\"30\" maxlength=\"16\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_DsAcctCust" value="..." htmlClass="pBtn0" />
			</td>
		</tr>
		<tr>
			<td class="stab">Program Name</td>
			<td>
				<ezf:select name="prcPgmTpCd" ezfName="prcPgmTpCd" ezfBlank="1" ezfCodeName="prcPgmTpCd_L" ezfDispName="prcPgmTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Equipment Type</td>
			<td>
				<ezf:select name="prcEquipTpCd" ezfName="prcEquipTpCd" ezfBlank="1" ezfCodeName="prcEquipTpCd_L" ezfDispName="prcEquipTpDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">Service Model Name(*)</td>
			<td>
				<ezf:inputText name="mdlNm" ezfName="mdlNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Model" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Qualifiying Term From</td>
			<td>
				<ezf:inputText name="termFromMthAot" ezfName="termFromMthAot" otherAttr=" size=\"30\" maxlength=\"3\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Qualifiying Term To</td>
			<td>
				<ezf:inputText name="termThruMthAot" ezfName="termThruMthAot" otherAttr=" size=\"30\" maxlength=\"3\""/>
			</td>
		</tr>
	</c:when>

	<c:when test="${(pageScope._ezddatabean.prcListStruTpCd_H == '07')}">
	<!-- '07': Lease Return Fees -->
		<tr>
			<td class="stab">Lease Company Abbr</td>
			<td>
				<ezf:inputText name="prcLeaseCmpyAbbrNm" ezfName="prcLeaseCmpyAbbrNm" otherAttr=" size=\"30\" maxlength=\"10\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Machine Segment</td>
			<td>
				<ezf:select name="svcSegCd" ezfName="svcSegCd" ezfBlank="1" ezfCodeName="svcSegCd_L" ezfDispName="svcSegDescTxt_L" />
			</td>
		</tr>
		<tr>
			<td class="stab">In or Out of Region</td>
			<td>
				<ezf:select name="prcInOutRgCd" ezfName="prcInOutRgCd" ezfBlank="1" ezfCodeName="prcInOutRgCd_L" ezfDispName="prcInOutRgDescTxt_L" />
			</td>
		</tr>
	</c:when>

	<c:when test="${(pageScope._ezddatabean.prcListStruTpCd_H == '08')}">
	<!-- '08': Trade Ins -->
		<tr>
			<td class="stab">Service Model Name(*)</td>
			<td>
				<ezf:inputText name="mdlNm" ezfName="mdlNm" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
				<ezf:inputButton name="OpenWin_Model" value="..." htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Model\""/>
			</td>
		</tr>
	</c:when>

	</c:choose>
		<!-- Common parameter -->
		<tr>
			<td class="stab">Date From</td>
			<td>
				<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);">
				-
				<ezf:inputText name="effFromDt_H2" ezfName="effFromDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H2', 4);">
			</td>
		</tr>
		<tr>
			<td class="stab">Date To</td>
			<td>
				<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);">
				-
				<ezf:inputText name="effThruDt_H2" ezfName="effThruDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H2', 4);">
			</td>
		</tr>
		<tr>
			<td class="stab">Created By</td>
			<td>
				<ezf:inputText name="xxFullNm_H1" ezfName="xxFullNm_H1" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Created Date</td>
			<td>
				<ezf:inputText name="cratDt_H1" ezfName="cratDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_H1', 4);">
				-
				<ezf:inputText name="cratDt_H2" ezfName="cratDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('cratDt_H2', 4);">
			</td>
		</tr>
		<tr>
			<td class="stab">Update By</td>
			<td>
				<ezf:inputText name="xxFullNm_H2" ezfName="xxFullNm_H2" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/>
			</td>
		</tr>
		<tr>
			<td class="stab">Update Date</td>
			<td>
				<ezf:inputText name="lastUpdDt_H1" ezfName="lastUpdDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('lastUpdDt_H1', 4);">
				-
				<ezf:inputText name="lastUpdDt_H2" ezfName="lastUpdDt_H2" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('lastUpdDt_H2', 4);">
			</td>
		</tr>

	<c:choose>
	<c:when test="${(pageScope._ezddatabean.prcListStruTpCd_H == '01')}">
		<tr>
			<td class="stab">Qty Break</td>
			<td>
				<ezf:inputRadio name="xxYesNoCd" ezfName="xxYesNoCd" value="" />All
				<ezf:inputRadio name="xxYesNoCd" ezfName="xxYesNoCd" value="Y" />Yes
				<ezf:inputRadio name="xxYesNoCd" ezfName="xxYesNoCd" value="N" />No
			</td>
		</tr>
	</c:when>
	</c:choose>

	</table>
	</div>
</center>

<!-- Price List Filter Popup HTML end -->


<%-- **** Task specific area ends here **** --%>
