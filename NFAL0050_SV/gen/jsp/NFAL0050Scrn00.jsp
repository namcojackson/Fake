<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161125182817 --%>
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
			<input type="hidden" name="pageID" value="NFAL0050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Automatic Journal Entry Pattern Maintenance">
                        <!-- Upper Tab Start -->
                        <!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
<!-- Application Parts Start -->
<div class="pTab_BODY">
<table width="96%" height="94%" align="center" border="0" cellspacing="0">
	<tr valign="top">
		<td>
			<table width="970" align="center" border="0" cellspacing="0">
					<col width="68">
					<col width="60">
					<col width="130">
					<col width="42">
					<col width="60">
					<col width="130">
					<col width="95">
					<col width="60">
					<col width="30">
					<col width="165">
				<tr height="20px">
					<td align="left" class="stab">Sys Src</td>
					<td align="left" class="stab">
						<ezf:select name="sysSrcCd_3" ezfName="sysSrcCd_3" ezfBlank="1" ezfCodeName="sysSrcCd_1" ezfDispName="sysSrcCd_2" otherEvent1=" onchange=\"sendServer('OnChange_SYS_SRC_CD')\"" otherAttr=" style=\"width:52px;\""/>
					</td>
					<td align="left" class="stab">
						<ezf:inputText name="sysSrcNm" ezfName="sysSrcNm" value="S21 Order" otherAttr=" style=\"height:20px;width:120px;font-size:9pt;\""/>
					</td>
					<td align="center" class="stab">Trx</td>
					<td align="left" class="stab">
						<ezf:select name="trxCd_3" ezfName="trxCd_3" ezfBlank="1" ezfCodeName="trxCd_1" ezfDispName="trxCd_2" otherEvent1=" onchange=\"sendServer('OnChange_TRX_CD')\"" otherAttr=" style=\"width:52px;\""/>
					</td>
					<td align="left" class="stab">
						<ezf:inputText name="trxNm" ezfName="trxNm" value="Description" otherAttr=" style=\"height:20px;width:120px;font-size:9pt;\""/>
					</td>
					<td align="center" class="stab">Trx Reason</td>
					<td align="left" class="stab">
						<ezf:select name="trxRsnCd_3" ezfName="trxRsnCd_3" ezfBlank="1" ezfCodeName="trxRsnCd_1" ezfDispName="trxRsnCd_2" otherEvent1=" onchange=\"sendServer('OnChange_TRX_RSN_CD')\"" otherAttr=" style=\"width:52px;\""/>
					</td>
					<td align="left" class="stab">
						<ezf:inputText name="trxRsnNm" ezfName="trxRsnNm" value="Loan Description" otherAttr=" style=\"height:20px;width:120px;font-size:9pt;\""/>
					</td>
					<td></td>
				</tr>
			</table>
			<table>
				<tr height="5px"><td class="stab"></td></tr>
			</table>
			<table width="970" align="center" border="0" cellspacing="0">
				<col width="67">
				<col width="115">
				<col width="85">
				<col width="675">
				<tr height="27px">
					<td align="left" class="stab">
						<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="AjeIdLink" >AJE ID</ezf:anchor>
					</td>
					<td align="left" class="stab">
						<ezf:inputText name="ajeId" ezfName="ajeId" value="AWB-080-02" otherAttr=" size=\"11\" maxlength=\"10\" ezftoupper=\"\""/>
					</td>
					<td align="left" class="stab">
						<ezf:inputButton name="SearchAjeIdBtn" value="Search" htmlClass="pBtn5" otherAttr=" style=\"width:85px;height:20px\""/>
					</td>
					<td align="left" class="stab">&nbsp;
						<b><ezf:label name="msgTxtInfoTxt_A" ezfName="msgTxtInfoTxt_A" /></b>
						<b><ezf:label name="msgTxtInfoTxt_B" ezfName="msgTxtInfoTxt_B" /></b>
						<b><font color="blue"><ezf:label name="msgTxtInfoTxt_P" ezfName="msgTxtInfoTxt_P" /></font></b>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="7">
						<table width="970" border="1" cellspacing="0">
							<col width="40">
							<col width="500">
							<col width="400">
							<tr height="23px">
								<td align="center" class="pClothBs">&nbsp;</td>
								<td align="center" class="pClothBs">AJE Pattern Indicator Type</td>
								<td align="center" class="pClothBs">AJE Code Value Type</td>
							</tr>
							<tr height="18px">
								<td align="center" class="stab">1</td>
								<td align="center" class="stab">
									<ezf:select name="ajePtrnIndTpCd_1V" ezfName="ajePtrnIndTpCd_1V" ezfBlank="1" ezfCodeName="ajePtrnIndTpCd_1C" ezfDispName="ajePtrnIndTpNm_1D" otherEvent1=" onchange=\"sendServer('OnChange_IND_TP_NM_01')\"" otherAttr=" style=\"width:480px;\""/>
								</td>
								<td align="center" class="stab">
									<ezf:select name="ajePtrnActlCd_1V" ezfName="ajePtrnActlCd_1V" ezfBlank="1" ezfCodeName="ajePtrnActlCd_1C" ezfDispName="ajePtrnActlNm_1D" otherEvent1=" onchange=\"sendServer('OnChange_ACTL_NM_01')\"" otherAttr=" style=\"width:380px;\""/>
								</td>
							</tr>
							<tr height="18px">
								<td align="center" class="stab">2</td>
								<td align="center" class="stab">
									<ezf:select name="ajePtrnIndTpCd_2V" ezfName="ajePtrnIndTpCd_2V" ezfBlank="1" ezfCodeName="ajePtrnIndTpCd_2C" ezfDispName="ajePtrnIndTpNm_2D" otherEvent1=" onchange=\"sendServer('OnChange_IND_TP_NM_02')\"" otherAttr=" style=\"width:480px;\""/>
								</td>
								<td align="center" class="stab">
									<ezf:select name="ajePtrnActlCd_2V" ezfName="ajePtrnActlCd_2V" ezfBlank="1" ezfCodeName="ajePtrnActlCd_2C" ezfDispName="ajePtrnActlNm_2D" otherEvent1=" onchange=\"sendServer('OnChange_ACTL_NM_02')\"" otherAttr=" style=\"width:380px;\""/>
								</td>
							</tr>
							<tr height="18px">
								<td align="center" class="stab">3</td>
								<td align="center" class="stab">
									<ezf:select name="ajePtrnIndTpCd_3V" ezfName="ajePtrnIndTpCd_3V" ezfBlank="1" ezfCodeName="ajePtrnIndTpCd_3C" ezfDispName="ajePtrnIndTpNm_3D" otherEvent1=" onchange=\"sendServer('OnChange_IND_TP_NM_03')\"" otherAttr=" style=\"width:480px;\""/>
								</td>
								<td align="center" class="stab">
									<ezf:select name="ajePtrnActlCd_3V" ezfName="ajePtrnActlCd_3V" ezfBlank="1" ezfCodeName="ajePtrnActlCd_3C" ezfDispName="ajePtrnActlNm_3D" otherEvent1=" onchange=\"sendServer('OnChange_ACTL_NM_03')\"" otherAttr=" style=\"width:380px;\""/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table width="970" align="center" border="0" cellspacing="0">
				<col width="100">
				<col width="80">
				<col width="490">
				<col width="150">
				<col width="150">
				<tr height="30px">
					<td align="left" class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="JrnlCatgLink" >Journal Category</ezf:anchor></td>
					
					<td align="left" class="stab">
						<ezf:inputText name="jrnlCatgCd" ezfName="jrnlCatgCd" value="A01" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
						<ezf:inputButton name="CatgSearchBtn" value=">>" htmlClass="pBtn5" otherAttr=" style=\"width:30px;height:20px\""/>
					</td>
					<td align="left" class="stab">
						<ezf:inputText name="jrnlCatgNm" ezfName="jrnlCatgNm" value="DOMESTIC VENDOR MDSE RETU" otherAttr=" size=\"62\""/>
					</td>
					<td align="left" class="stab">
						<ezf:inputCheckBox name="xxChkBox_AM" ezfName="xxChkBox_AM" value="Y" /> Flip Amount
					</td>
					<td align="left" class="stab">
						<ezf:inputCheckBox name="xxChkBox_QT" ezfName="xxChkBox_QT" value="Y" /> Flip Qty
					</td>
				</tr>
			</table>
			<!-- To enable horizontal scroll bar, change overflow-x:hidden to overflow-x:scroll-->
			<div id="topTBL" style="overflow-y:hidden; height:49; overflow-x:hidden; width:1090;" onScroll="synchroScrollLeft( this.id, new Array( 'bottomTBL' ) );">
				<table width="1090" border="1" cellspacing="0">
					<col width="50">
					<col width="50">
					<col width="90">
					<col width="90">
					<col width="90">
					<col width="180">
					<col width="190">
					<col width="90">
					<col width="90">
					<col width="90">
					<col width="90">
					<col width="90">
					<tr height="23px">
						<td align="center" class="pClothBs">Idx/DC</td>
						<td align="center" class="pClothBs">Co</td>
						<td align="center" class="pClothBs"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CoaBrLink" ><font color="#ffffff">Br</font></ezf:anchor></td>
						<td align="center" class="pClothBs"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CoaCcLink" ><font color="#ffffff">CC</font></ezf:anchor></td>
						<td align="center" class="pClothBs"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CoaAcctLink" ><font color="#ffffff">Acct</font></ezf:anchor></td>
						<td align="center" class="pClothBs">Line Description</td>
						<td align="center" class="pClothBs">Line Indicator Type</td>
						<td align="center" class="pClothBs"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CoaProdLink" ><font color="#ffffff">Prod</font></ezf:anchor></td>
						<td align="center" class="pClothBs"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CoaChLink" ><font color="#ffffff">Ch</font></ezf:anchor></td>
						<td align="center" class="pClothBs"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CoaAfflLink" ><font color="#ffffff">Intercmpy</font></ezf:anchor></td>
						<td align="center" class="pClothBs"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="CoaProjLink" ><font color="#ffffff">Merch Type</font></ezf:anchor></td>
						<td align="center" class="pClothBs">Bus Unit</td>
					</tr>
					<tr height="20px">
						<td align="center" class="stab">
							<ezf:select name="ajeLineIdxNum_3" ezfName="ajeLineIdxNum_3" ezfBlank="1" ezfCodeName="ajeLineIdxNum_1" ezfDispName="ajeLineIdxNum_2" otherEvent1=" onchange=\"sendServer('OnChange_AJE_LINE_IDX_NUM')\"" otherAttr=" style=\"width:44px;\""/>
						</td>
						<td align="center" class="stab">
							<ezf:select name="ajeCoaCmpyCd_3" ezfName="ajeCoaCmpyCd_3" ezfBlank="1" ezfCodeName="ajeCoaCmpyCd_1" ezfDispName="ajeCoaCmpyCd_2" otherEvent1=" onchange=\"sendServer('OnChange_AJE_COA_CMPY_CD')\"" otherAttr=" style=\"width:44px;\""/>
						</td>
						<td align="center" class="stab">
							<ezf:inputText name="ajeCoaBrCd" ezfName="ajeCoaBrCd" value="888888" otherAttr=" size=\"9\" ezftoupper=\"\""/>
						</td>
						<td align="center" class="stab">
							<ezf:inputText name="ajeCoaCcCd" ezfName="ajeCoaCcCd" value="888888" otherAttr=" size=\"9\" ezftoupper=\"\""/>
						</td>
						<td align="center" class="stab">
							<ezf:inputText name="ajeCoaAcctCd" ezfName="ajeCoaAcctCd" value="88888888" otherAttr=" size=\"9\" ezftoupper=\"\""/>
						</td>
						<td align="left" class="stab">
							<ezf:inputText name="ajeLineIdxDescTxt" ezfName="ajeLineIdxDescTxt" value="Looooooong description" otherAttr=" size=\"25\""/>
						</td>
						<td align="center" class="stab">
							<ezf:select name="ajeLineIndTpCd_3" ezfName="ajeLineIndTpCd_3" ezfBlank="1" ezfCodeName="ajeLineIndTpCd_1" ezfDispName="ajeLineIndTpNm_2" otherEvent1=" onchange=\"sendServer('OnChange_AJE_LINE_IND_TP_CD')\"" otherAttr=" style=\"width:120px;\""/>
						</td>
						<td align="center" class="stab">
							<ezf:inputText name="ajeCoaProdCd" ezfName="ajeCoaProdCd" value="@ITEM" otherAttr=" size=\"9\" ezftoupper=\"\""/>
						</td>
						<td align="center" class="stab">
							<ezf:select name="ajeCoaChCd_3" ezfName="ajeCoaChCd_3" ezfBlank="1" ezfCodeName="ajeCoaChCd_1" ezfDispName="ajeCoaChCd_2" otherEvent1=" onchange=\"sendServer('OnChange_AJE_COA_CH_CD')\"" otherAttr=" style=\"width:80px;\""/>
						</td>
						<td align="center" class="stab">
							<ezf:inputText name="ajeCoaAfflCd" ezfName="ajeCoaAfflCd" value="@CUST" otherAttr=" size=\"9\" ezftoupper=\"\""/>
						</td>
						<td align="center" class="stab">
							<ezf:inputText name="ajeCoaProjCd" ezfName="ajeCoaProjCd" value="@LINK" otherAttr=" size=\"9\" ezftoupper=\"\""/>
						</td>
						<td align="center" class="stab">
							<ezf:select name="ajeCoaExtnCd_3" ezfName="ajeCoaExtnCd_3" ezfBlank="1" ezfCodeName="ajeCoaExtnCd_1" ezfDispName="ajeCoaExtnCd_2" otherEvent1=" onchange=\"sendServer('OnChange_AJE_COA_EXTN_CD')\"" otherAttr=" style=\"width:80px;\""/>
						</td>
					</tr>
				</table>
			</div>
			<table width="1090" border="0" cellspacing="0">
				<col width="760">
				<col width="95">
				<col width="95">
				<tr height="50px">
					<td align="right">
						<ezf:inputButton name="CopyRowBtn" value="Copy Row" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
					</td>
					<td align="right">
						<ezf:inputButton name="PasteRowBtn" value="Paste Row" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
					</td>
					<td align="right">
						<ezf:inputButton name="AddRowBtn" value="Add Row" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
					</td>
				</tr>
			</table>
			<!--<div id="bottomTBL" style="overflow-y:hidden; height:205; overflow-x:hidden; width:1090;" onScroll="synchroScrollLeft( this.id, new Array( 'bottomTBL' ) );">-->
			<table width="1090" border="1" cellspacing="0" cellpadding="0">
				<col width="35">
				<col width="30">
				<col width="35">
				<col width="50">
				<col width="50">
				<col width="60">
				<col width="60">
				<col width="300">
				<col width="125">
				<col width="60">
				<col width="60">
				<col width="75">
				<col width="75">
				<col width="75">
				<tr height="23px">
					<td align="center" class="pClothBs">&nbsp;</td>
					<td align="center" class="pClothBs">Idx</td>
					<td align="center" class="pClothBs">D/C</td>
					<td align="center" class="pClothBs">Co</td>
					<td align="center" class="pClothBs">Br</td>
					<td align="center" class="pClothBs">CC</td>
					<td align="center" class="pClothBs">Acct</td>
					<td align="center" class="pClothBs">Line Description</td>
					<td align="center" class="pClothBs">Line Indicator Type</td>
					<td align="center" class="pClothBs">Prod</td>
					<td align="center" class="pClothBs">Ch</td>
					<td align="center" class="pClothBs">Intercmpy</td>
					<td align="center" class="pClothBs">Merch Type</td>
					<td align="center" class="pClothBs">Bus Unit</td>
				</tr>
			</table>
			<div id="bottomTBL" style="overflow-y:scroll; height:202; overflow-x:hidden; width:1107;">
			<table width="1090" border="1" cellspacing="0" cellpadding="0" id="A">
				<col width="35">
				<col width="30">
				<col width="35">
				<col width="50">
				<col width="50">
				<col width="60">
				<col width="60">
				<col width="285">
				<col width="125">
				<col width="60">
				<col width="60">
				<col width="75">
				<col width="75">
				<col width="75">
				<tbody>
					<ezf:row ezfHyo="A">
						<tr height="18px">
							<td class="stab" align="center">
								<ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OnClick_XX_CHK_BOX_A')" />
							</td>
							<td align="left"class="stab"><ezf:label name="ajeLineIdxNum_A" ezfName="ajeLineIdxNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:label name="drCrTpCd_A" ezfName="drCrTpCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:label name="ajeCoaCmpyCd_A" ezfName="ajeCoaCmpyCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:label name="ajeCoaBrCd_A" ezfName="ajeCoaBrCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:label name="ajeCoaCcCd_A" ezfName="ajeCoaCcCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:label name="ajeCoaAcctCd_A" ezfName="ajeCoaAcctCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:inputText name="ajeLineIdxDescTxt_A" ezfName="ajeLineIdxDescTxt_A" value="Domestic - Export - Import" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:280px;font-size:9pt;\""/></td>
							<td align="left"class="stab"><ezf:inputText name="ajeLineIndTpNm_A" ezfName="ajeLineIndTpNm_A" value="**COST OF SALES**" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:18px;width:125px;font-size:9pt;\""/></td>
							<td align="left"class="stab"><ezf:label name="ajeCoaProdCd_A" ezfName="ajeCoaProdCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:label name="ajeCoaChCd_A" ezfName="ajeCoaChCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:label name="ajeCoaAfflCd_A" ezfName="ajeCoaAfflCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:label name="ajeCoaProjCd_A" ezfName="ajeCoaProjCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
							<td align="left"class="stab"><ezf:label name="ajeCoaExtnCd_A" ezfName="ajeCoaExtnCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
						</tr>
					</ezf:row>

				</tbody>
			</table>
			</div>
			</div>
			<table width="1090" border="0" cellspacing="0">
				<col width="95">
				<col width="95">
				<col width="760">
				<tr height="40px">
					<td align="left">
						<ezf:inputButton name="SelectAllBtn" value="Select All" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
					</td>
					<td align="left">
						<ezf:inputButton name="UnselectAllBtn" value="Unselect All" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
					</td>
					<td align="right">
						<ezf:inputButton name="DeleteRowBtn" value="Delete Row" htmlClass="pBtn5" otherAttr=" style=\"width:90px;height:20px\""/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>

<%-- **** Task specific area ends here **** --%>
