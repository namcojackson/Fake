<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20231225145051 --%>
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
			<input type="hidden" name="pageID" value="NMAL0110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Item Detail">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" otherAttr=" id=\"xxRecHistCratTs\""/>
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" otherAttr=" id=\"xxRecHistCratByNm\""/>
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" otherAttr=" id=\"xxRecHistUpdTs\""/>
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" otherAttr=" id=\"xxRecHistUpdByNm\""/>
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" otherAttr=" id=\"xxRecHistTblNm\""/>

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
				<%-- ###TAB - HEAD --%>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<!-- include S21BusinessProcessTAB --> 
													<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
												</div>
											</td>
										</tr>
									</table>
								</ul>
							</div>
				<div class="pTab_BODY">
					
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top">
                            </td>
							<td valign="top" width="349">
								<table  width="347" cellpadding="0">
									<col align="left" width="140">
									<col align="left" width="207">
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_01" ezfEmulateBtn="1" ezfGuard="Item_Link" otherAttr=" tabindex=\"-1\"">Item Number</ezf:anchor></td><!--MDSE.MDSE_CD-->
										<td><ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
										<ezf:inputButton name="Search_Item" value="Search" htmlClass="pBtn6" /></td>
									</tr>
									<tr>
										<td class="stab">Item Description</td><!--MDSE.MDSE_NM-->
										<td><ezf:inputText name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" value="XX" otherAttr=" size=\"27\" maxlength=\"250\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_33" ezfEmulateBtn="1" ezfGuard="Manufacturer_Link" otherAttr=" tabindex=\"-1\"">Manufacturer</ezf:anchor></td><!--DS_MDSE_INFO.MDSE_ITEM_MNF_CD-->
										<td><ezf:inputHidden name="mdseItemMnfCd_H1" ezfName="mdseItemMnfCd_H1" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
										<ezf:inputText name="mdseItemMnfNm_H1" ezfName="mdseItemMnfNm_H1" value="XX" otherAttr=" size=\"27\" maxlength=\"40\" tabindex=\"-1\""/></td>
									</tr>
									<tr>
										<td class="stab">Manufacturer Item #</td><!--DS_MDSE_INFO.MNF_ITEM_CD-->
										<td><ezf:inputText name="mnfItemCd_H1" ezfName="mnfItemCd_H1" value="XX" otherAttr=" size=\"27\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">UPC Code</td><!--MDSE.UPC_CD-->
										<td><ezf:inputText name="upcCd_H1" ezfName="upcCd_H1" value="XX" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Status</td><!--DS_MDSE_INFO.MDSE_ITEM_STS_CD-->
										<td><ezf:select name="mdseItemStsCd_H1" ezfName="mdseItemStsCd_H1" ezfBlank="1" ezfCodeName="mdseItemStsCd_L1" ezfDispName="mdseItemStsNm_L1" otherAttr=" style=\"width:195px\""/></td>
									</tr>
									<tr>
										<td class="stab" vAlign="top">Long Description</td><!--MDSE.MDSE_FML_NM-->
										<td><ezf:textArea name="mdseDescLongTxt_H1" ezfName="mdseDescLongTxt_H1" otherAttr=" size=\"27\" maxlength=\"90\" rows=\"5\" cols=\"25\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Implementation Date</td><!--DS_MDSE_INFO.MDSE_ITEM_ACTV_DT-->
										<td><ezf:inputText name="mdseItemActvDt_H1" ezfName="mdseItemActvDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
                                        <img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseItemActvDt_H1', 4);" ></td>
									</tr>
								</table>
							</td>
							<td valign="top" width="345">
								<table width="343" cellpadding="0" border="0">
									<tr>
										<td class="stab">Registration Mode</td><!--DS_MDSE_INFO.MDSE_ITEM_TP_CD-->
										<td><ezf:select name="mdseRgtnTpCd_H1" ezfName="mdseRgtnTpCd_H1" ezfBlank="1" ezfCodeName="mdseRgtnTpCd_L1" ezfDispName="mdseRgtnTpDescTxt_L1" otherAttr=" style=\"width:215\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Item Type</td><!--DS_MDSE_INFO.MDSE_ITEM_TP_CD-->
										<td><ezf:select name="mdseItemTpCd_H1" ezfName="mdseItemTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemTpCd_L1" ezfDispName="mdseItemTpDescTxt_L1" otherEvent1=" onchange=\"if (sendCheck(this)) {sendServer('Change_ItemTp_Pulldown');} else {return false;}\"" otherAttr=" style=\"width:215\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Item Classification</td><!--DS_MDSE_INFO.MDSE_ITEM_CLS_TP_CD-->
										<td><ezf:select name="mdseItemClsTpCd_H1" ezfName="mdseItemClsTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemClsTpCd_L1" ezfDispName="mdseItemClsTpDescTxt_L1" otherAttr=" style=\"width:215\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_28" ezfEmulateBtn="1" ezfGuard="Merchandise_Type_Link" otherAttr=" tabindex=\"-1\"">Merchandise Type</ezf:anchor></td><!--DS_MDSE_INFO.COA_MDSE_TP_CD-->
<!--										<td><select style="width:215" name="coaMdseTpCd_H1" ezfname="coaMdseTpCd_H1" ezflist="coaMdseTpCd_L1,coaMdseTpNm_L1,99, ,blank">-->
<!--												<option>XXXXXXXXX1XXXXXXXXX2</option>-->
<!--											</select>-->
<!--										</td>-->
										<td>
                                            <ezf:inputText name="coaMdseTpCd_H1" ezfName="coaMdseTpCd_H1" value="XX" otherEvent1="OnBlur_CoaMdseTpCd" otherAttr=" size=\"2\" maxlength=\"2\" ezffocusout=\"OnBlur_CoaMdseTpCd\""/>&nbsp;<ezf:inputText name="coaProjDescTxt_H1" ezfName="coaProjDescTxt_H1" value="XX" otherAttr=" size=\"26\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr><!--MDSE.COA_PROD_CD-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_07" ezfEmulateBtn="1" ezfGuard="COA_Product_Link" otherAttr=" tabindex=\"-1\"">Product Code</ezf:anchor></td>
										<td>
                                            <ezf:inputText name="coaProdCd_H1" ezfName="coaProdCd_H1" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="coaProdNm_H1" ezfName="coaProdNm_H1" value="XX" otherAttr=" size=\"26\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Planning Group</td><!--DS_MDSE_INFO.PRCH_GRP_CD-->
										<td><ezf:select name="prchGrpCd_H1" ezfName="prchGrpCd_H1" ezfBlank="1" ezfCodeName="prchGrpCd_L1" ezfDispName="prchGrpNm_L1" otherAttr=" style=\"width:100\""/>
											Third Party<ezf:inputCheckBox name="thirdPtyItemFlg_H1" ezfName="thirdPtyItemFlg_H1" value="Y" />
										</td>
									</tr>
<!--									<tr>-->
<!--										<td class="stab">Pricing Group</td><!--DS_MDSE_INFO.MDSE_PRC_GRP_CD-->
<!--										<td><select style="width:215" name="mdsePrcGrpCd_H1" ezfname="mdsePrcGrpCd_H1" ezflist="mdsePrcGrpCd_L1,mdsePrcGrpNm_L1,99, ,blank">-->
<!--												<option>XXXXXXXXX1XXXXXXXXX2</option>-->
<!--											</select>-->
<!--										</td>-->
<!--									</tr>-->
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_19" ezfEmulateBtn="1" ezfGuard="Mkt_Mdl_Link" otherAttr=" tabindex=\"-1\"">Marketing Model</ezf:anchor></td><!--DS_MDSE_INFO.MKT_MDL_CD-->
										<td>
                                            <ezf:inputText name="mktMdlCd_H1" ezfName="mktMdlCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="mktMdlNm_H1" ezfName="mktMdlNm_H1" value="XX" otherAttr=" size=\"18\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_20" ezfEmulateBtn="1" ezfGuard="Mkt_Mdl_Seg_Link" otherAttr=" tabindex=\"-1\"">Marketing Segment</ezf:anchor></td><!--DS_MDSE_INFO.MKT_MDL_SEG_CD-->
										<td>
                                            <ezf:inputText name="mktMdlSegCd_H1" ezfName="mktMdlSegCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="mktMdlSegNm_H1" ezfName="mktMdlSegNm_H1" value="XX" otherAttr=" size=\"18\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_18" ezfEmulateBtn="1" ezfGuard="Item_Tmpl_Link" otherAttr=" tabindex=\"-1\"">Created From Template</ezf:anchor></td><!--MDSE_CRAT_TMPL.MDSE_CRAT_TMPL_NM-->
										<td><ezf:inputText name="mdseCratTmplNm_H1" ezfName="mdseCratTmplNm_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"-1\""/></td>
									</tr>
									<tr>
										<td class="stab">Representative</td>
										<td><ezf:inputCheckBox name="xxChkBox_RP" ezfName="xxChkBox_RP" value="Y" /></td>
									</tr>
								</table>
							</td>


							<td valign="top" width="424">
								<table cellpadding="0" width="420">
									<col align="left" width="106">
									<col align="left" width="316">
									<tr><!--MDSE.ZEROTH_PROD_CTRL_CD-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_02" ezfEmulateBtn="1" ezfGuard="Product_Line_Group_Link" otherAttr=" tabindex=\"-1\"">Product Level 1</ezf:anchor></td>
										<td><ezf:inputText name="zerothProdCtrlCd_H1" ezfName="zerothProdCtrlCd_H1" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="zerothProdCtrlNm_H1" ezfName="zerothProdCtrlNm_H1" value="XX" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L1" ezfName="mdseStruElmntTpNm_L1" />
										</td>
									</tr>
									<tr><!--MDSE.FIRST_PROD_CTRL_CD-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_03" ezfEmulateBtn="1" ezfGuard="Product_Line_Link" otherAttr=" tabindex=\"-1\"">Product Level 2</ezf:anchor></td>
										<td><ezf:inputText name="firstProdCtrlCd_H1" ezfName="firstProdCtrlCd_H1" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="firstProdCtrlNm_H1" ezfName="firstProdCtrlNm_H1" value="XX" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L2" ezfName="mdseStruElmntTpNm_L2" />
										</td>
									</tr>
									<tr><!--MDSE.SCD_PROD_CTRL_CD-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_04" ezfEmulateBtn="1" ezfGuard="Product_Line_Level_2_Link" otherAttr=" tabindex=\"-1\"">Product Level 3</ezf:anchor></td>
										<td><ezf:inputText name="scdProdCtrlCd_H1" ezfName="scdProdCtrlCd_H1" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="scdProdCtrlNm_H1" ezfName="scdProdCtrlNm_H1" value="XX" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L3" ezfName="mdseStruElmntTpNm_L3" />
										</td>
									</tr>
									<tr><!--MDSE.THIRD_PROD_CTRL_CD-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_05" ezfEmulateBtn="1" ezfGuard="Product_Line_Level_3_Link" otherAttr=" tabindex=\"-1\"">Product Level 4</ezf:anchor></td>
										<td><ezf:inputText name="thirdProdCtrlCd_H1" ezfName="thirdProdCtrlCd_H1" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="thirdProdCtrlNm_H1" ezfName="thirdProdCtrlNm_H1" value="XX" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L4" ezfName="mdseStruElmntTpNm_L4" />
										</td>
									</tr>
									<tr><!--MDSE.FRTH_PROD_CTRL_CD-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_06" ezfEmulateBtn="1" ezfGuard="Product_Line_Level_4_Link" otherAttr=" tabindex=\"-1\"">Product Level 5</ezf:anchor></td>
										<td><ezf:inputText name="frthProdCtrlCd_H1" ezfName="frthProdCtrlCd_H1" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="frthProdCtrlNm_H1" ezfName="frthProdCtrlNm_H1" value="XX" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"50\""/>&nbsp;<ezf:label name="mdseStruElmntTpNm_L5" ezfName="mdseStruElmntTpNm_L5" />
										</td>
									</tr>

									<tr><!--DS_MDSE_INFO.SLS_MAT_GRP_CD_01-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_22" ezfEmulateBtn="1" ezfGuard="Sls_Mat_Group_Link_01" otherAttr=" tabindex=\"-1\"">Material Group 1</ezf:anchor></td>
										<td><ezf:inputText name="slsMatGrpCd_01" ezfName="slsMatGrpCd_01" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="slsMatGrpDescTxt_01" ezfName="slsMatGrpDescTxt_01" value="XX" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"50\""/>&nbsp;MG1 Desc</label>
										</td>
									</tr>
									<tr><!--DS_MDSE_INFO.SLS_MAT_GRP_CD_02-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_23" ezfEmulateBtn="1" ezfGuard="Sls_Mat_Group_Link_02" otherAttr=" tabindex=\"-1\"">Material Group 2</ezf:anchor></td>
										<td><ezf:inputText name="slsMatGrpCd_02" ezfName="slsMatGrpCd_02" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="slsMatGrpDescTxt_02" ezfName="slsMatGrpDescTxt_02" value="XX" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"50\""/>&nbsp;MG2 Desc</label>
										</td>
									</tr>
									<tr><!--DS_MDSE_INFO.SLS_MAT_GRP_CD_03-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_24" ezfEmulateBtn="1" ezfGuard="Sls_Mat_Group_Link_03" otherAttr=" tabindex=\"-1\"">Material Group 3</ezf:anchor></td>
										<td><ezf:inputText name="slsMatGrpCd_03" ezfName="slsMatGrpCd_03" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="slsMatGrpDescTxt_03" ezfName="slsMatGrpDescTxt_03" value="XX" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"50\""/>&nbsp;MG3 Desc</label>
										</td>
									</tr>
									<tr><!--DS_MDSE_INFO.DS_CMSN_GRP_CD-->
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_25" ezfEmulateBtn="1" ezfGuard="Comsn_Group_Link" otherAttr=" tabindex=\"-1\"">Commision Group</ezf:anchor></td>
										<td><ezf:inputText name="dsCmsnGrpCd_H1" ezfName="dsCmsnGrpCd_H1" value="XX" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="dsCmsnGrpDescTxt_H1" ezfName="dsCmsnGrpDescTxt_H1" value="XX" otherAttr=" tabindex=\"-1\" size=\"15\" maxlength=\"50\""/>&nbsp;CG Desc</label>
										</td>
									</tr>


<!--									<tr>-->
<!--										<td class="stab"><a href="#" onclick="sendServer('Item_Tmpl_Link')" ezfname="xxLinkProt_18" tabindex="-1">Template Name</a></td><!--MDSE_CRAT_TMPL.MDSE_CRAT_TMPL_NM-->
<!--										<td><input type="text" name="mdseCratTmplNm_H2" value="XX"  size="30" maxlength="50" ezftoupper="" ezfname="mdseCratTmplNm_H2"></td>-->
<!--									</tr>-->
<!--									<tr>-->
<!--										<td class="stab">Template Created</td><!--MDSE_CRAT_TMPL.MDSE_CRAT_TMPL_CRAT_DT-->
<!--										<td><input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="mdseCratTmplCratDt_H1" ezfname="mdseCratTmplCratDt_H1">-->
<!--                                     <img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCratTmplCratDt_H1', 4);" ></td>-->
<!--									</tr>-->
<!--									<tr>-->
<!--										<td colspan="2" align="right">-->
<!--<table><tr><td align="center">-->
<%//String attrbTmplDescTxt_H1 = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getAttrbTmplDescTxt_H1();%>
<%//if(attrbTmplDescTxt_H1 != null && !"".equals(attrbTmplDescTxt_H1)){%>
<!--                                            <div style="border-style: solid ; border-color: red; border-width: 1px; width: 270px;">-->
<!--                                            <table><tr><td>-->
<!--                                            <font color="red" size="1">Item Template</font>-->
<!--                                            </td></tr></table>-->
<!--                                            </div>-->
<%//} else {%>

<%//}%>
<!--</td>-->
<!--<td align="right">-->
<%
//    String xxRsltFlg_H3 = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxRsltFlg_H3();
//    if ("Y".equals(xxRsltFlg_H3)) {
%>
<!--                                          <input type="button" class="pBtn10" value="Save as Tmpl" name="Save_Tmpl" onclick="if (confirmItemTemplateName()) {sendServer(this);}" disabled>-->
<%//} else { %>
<!--                                          <input type="button" class="pBtn10" value="Save as Tmpl" name="Save_Tmpl" onclick="if (confirmItemTemplateName()) {sendServer(this);}">-->
<%//}%>
<!--</td></tr></table>-->
<!--					                                        </td>-->
<!--									</tr>-->
								</table>
							</td>
						</tr>
					</table>
                                        <ezf:inputHidden name="xxModeCd_H1" ezfName="xxModeCd_H1" />
                                        <ezf:inputHidden name="mdseCratTmplNm_BK" ezfName="mdseCratTmplNm_BK" />
						<hr style="height: 0px;" cellpadding="0">
<%-- #################################################### DETAIL ################################################### --%>
<%
    String GeneralFlg = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxTabProt_HG();
    String InventoryFlg = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxTabProt_HI();
    String AccountingFlg = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxTabProt_HA();
    String FieldServiceFlg = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxTabProt_HF();
    String OrderProcessingFlg = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxTabProt_HO();
    String SupercessionFlg = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxTabProt_HS();
    String CustomerRefFlg = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxTabProt_HC();
    String TaxingFlg = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxTabProt_HT();
    String xxDplyTab_H1 = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getXxDplyTab_H1();

    String mdseItemTpCd_H1 = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getMdseItemTpCd_H1();
    String coaMdseTpCd_H1 = ((business.servlet.NMAL0110.NMAL0110Bean)databean).getCoaMdseTpCd_H1();

%>

						<table cellpadding="0" width="100%">
							<col valign="top">
							<tr>
								<td>
									<div class="pTab_HEAD">
										<ul>
<%if(GeneralFlg.equals("Y")){%>
											<li class="pTab_OFF" id="General"    title="General"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_General" otherAttr=" tabindex=\"-1\"">General</ezf:anchor></li>
<%}%>
<%if(InventoryFlg.equals("Y")){%>
											<li class="pTab_OFF" id="Inventory" title="Inventory"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Inventory" otherAttr=" tabindex=\"-1\"">Inventory</ezf:anchor></li>
<%}%>
<%if(AccountingFlg.equals("Y")){%>
											<li class="pTab_OFF" id="Accounting" title="Accounting"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Accounting" otherAttr=" tabindex=\"-1\"">Accounting</ezf:anchor></li>
<%}%>
<%if(FieldServiceFlg.equals("Y")){%>
											<li class="pTab_OFF" id="FieldService" title="Field Service"><ezf:anchor name="" ezfName="xxTabProt_04" ezfEmulateBtn="1" ezfGuard="TAB_FieldService" otherAttr=" tabindex=\"-1\"">Field Service</ezf:anchor></li>
<%}%>
<%if(OrderProcessingFlg.equals("Y")){%>
											<li class="pTab_OFF" id="OrderProcessing" title="Order Processing"><ezf:anchor name="" ezfName="xxTabProt_05" ezfEmulateBtn="1" ezfGuard="TAB_OrderProcessing" otherAttr=" tabindex=\"-1\"">Order Proc</ezf:anchor></li>
<%}%>
<%if(SupercessionFlg.equals("Y")){%>
											<li class="pTab_OFF" id="Supercession" title="Supersession/Relationships"><ezf:anchor name="" ezfName="xxTabProt_07" ezfEmulateBtn="1" ezfGuard="TAB_Supercession" otherAttr=" tabindex=\"-1\"">Sup/Relation</ezf:anchor></li>
<%}%>
<%if(CustomerRefFlg.equals("Y")){%>
											<li class="pTab_OFF" id="CustomerRef" title="Customer References"><ezf:anchor name="" ezfName="xxTabProt_08" ezfEmulateBtn="1" ezfGuard="TAB_CustomerRef" otherAttr=" tabindex=\"-1\"">Cust Ref</ezf:anchor></li>
<%}%>
<%if(TaxingFlg.equals("Y")){%>
											<li class="pTab_OFF" id="Taxing" title="Taxing"><ezf:anchor name="" ezfName="xxTabProt_09" ezfEmulateBtn="1" ezfGuard="TAB_Taxing" otherAttr=" tabindex=\"-1\"">Taxing</ezf:anchor></li>
<%}%>
										</ul>
									</div>

<%-- General Start --%>
<%if(GeneralFlg.equals("Y") && xxDplyTab_H1.equals("General")){%>
										<script type="text/javascript">
<%if(GeneralFlg.equals("Y")){%>
											document.getElementById( "General" ).className = "pTab_ON";
<%}%>
<%if(InventoryFlg.equals("Y")){%>
											document.getElementById( "Inventory" ).className = "pTab_OFF";
<%}%>
<%if(AccountingFlg.equals("Y")){%>
											document.getElementById( "Accounting" ).className = "pTab_OFF";
<%}%>
<%if(FieldServiceFlg.equals("Y")){%>
											document.getElementById( "FieldService" ).className = "pTab_OFF";
<%}%>
<%if(OrderProcessingFlg.equals("Y")){%>
											document.getElementById( "OrderProcessing" ).className = "pTab_OFF";
<%}%>
<%if(SupercessionFlg.equals("Y")){%>
											document.getElementById( "Supercession" ).className = "pTab_OFF";
<%}%>
<%if(CustomerRefFlg.equals("Y")){%>
											document.getElementById( "CustomerRef" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
											document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
										</script>

									<div class="pTab_BODY_In">
										<div style="OVERFLOW-Y: scroll; HEIGHT: 285px" >
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top" width="355"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;
																</td>
																<td>
																	<fieldset style="width:340px">
																	<table cellpadding="0">
																		<tr>
																			<td colspan="5" class="stab"><b>Dimensions(Each)</b></td>
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td class="stab">Boxed</td>
																			<td>&nbsp;</td>
																			<td class="stab">UnBoxed</td>
																			<td>&nbsp;</td>
																		</tr>
																		<tr>
																			<td class="stab">Weight</td><!--MDSE_STORE_PKG.IN_POUND_WT-->
																			<td>
																				<ezf:inputText name="inPoundWt_EA" ezfName="inPoundWt_EA" value="99,999.9999" otherAttr=" size=\"10\" maxlength=\"11\" ezftoupper=\"\""/>
																				<ezf:inputHidden name="xxRecHistCratTs_G1" ezfName="xxRecHistCratTs_G1" />
																				<ezf:inputHidden name="xxRecHistCratByNm_G1" ezfName="xxRecHistCratByNm_G1" />
																				<ezf:inputHidden name="xxRecHistUpdTs_G1" ezfName="xxRecHistUpdTs_G1" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_G1" ezfName="xxRecHistUpdByNm_G1" />
																				<ezf:inputHidden name="xxRecHistTblNm_G1" ezfName="xxRecHistTblNm_G1" />
																			</td>
																			<td>
																				<ezf:label name="xxInPoundWtUomDescTxt_EA" ezfName="xxInPoundWtUomDescTxt_EA" otherAttr=" size=\"7\" maxlength=\"50\""/>
																			</td>
																			<td>
																				<ezf:inputText name="inPoundWt_UN" ezfName="inPoundWt_UN" value="99,999.9999" otherAttr=" size=\"10\" maxlength=\"11\" ezftoupper=\"\""/>
																				<ezf:inputHidden name="xxRecHistCratTs_G2" ezfName="xxRecHistCratTs_G2" />
																				<ezf:inputHidden name="xxRecHistCratByNm_G2" ezfName="xxRecHistCratByNm_G2" />
																				<ezf:inputHidden name="xxRecHistUpdTs_G2" ezfName="xxRecHistUpdTs_G2" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_G2" ezfName="xxRecHistUpdByNm_G2" />
																				<ezf:inputHidden name="xxRecHistTblNm_G2" ezfName="xxRecHistTblNm_G2" />
																			</td>
																			<td>
																				<ezf:label name="xxInPoundWtUomDescTxt_UN" ezfName="xxInPoundWtUomDescTxt_UN" otherAttr=" size=\"7\" maxlength=\"50\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Length</td><!--MDSE_STORE_PKG.IN_INCH_LG-->
																			<td>
																				<ezf:inputText name="inInchLg_EA" ezfName="inInchLg_EA" value="99,999.9999" otherAttr=" size=\"10\" maxlength=\"11\" ezftoupper=\"\""/>
																				<ezf:inputHidden name="xxRecHistCratTs_G3" ezfName="xxRecHistCratTs_G3" />
																				<ezf:inputHidden name="xxRecHistCratByNm_G3" ezfName="xxRecHistCratByNm_G3" />
																				<ezf:inputHidden name="xxRecHistUpdTs_G3" ezfName="xxRecHistUpdTs_G3" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_G3" ezfName="xxRecHistUpdByNm_G3" />
																				<ezf:inputHidden name="xxRecHistTblNm_G3" ezfName="xxRecHistTblNm_G3" />
																			</td>
																			<td>
																				<ezf:label name="xxInInchLgUomDescTxt_EA" ezfName="xxInInchLgUomDescTxt_EA" otherAttr=" size=\"7\" maxlength=\"50\""/>
																			</td>
																			<td>
																				<ezf:inputText name="inInchLg_UN" ezfName="inInchLg_UN" value="99,999.9999" otherAttr=" size=\"10\" maxlength=\"11\" ezftoupper=\"\""/>
																				<ezf:inputHidden name="xxRecHistCratTs_G4" ezfName="xxRecHistCratTs_G4" />
																				<ezf:inputHidden name="xxRecHistCratByNm_G4" ezfName="xxRecHistCratByNm_G4" />
																				<ezf:inputHidden name="xxRecHistUpdTs_G4" ezfName="xxRecHistUpdTs_G4" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_G4" ezfName="xxRecHistUpdByNm_G4" />
																				<ezf:inputHidden name="xxRecHistTblNm_G4" ezfName="xxRecHistTblNm_G4" />
																			</td>
																			<td>
																				<ezf:select name="inInchLgUomCd_UN" ezfName="inInchLgUomCd_UN" ezfCodeName="inInchLgUomCd_L1" ezfDispName="xxInInchLgUomDescTxt_L1" otherAttr=" style=\"width:70px\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Width</td><!--MDSE_STORE_PKG.IN_INCH_WDT-->
																			<td>
																				<ezf:inputText name="inInchWdt_EA" ezfName="inInchWdt_EA" value="99,999.9999" otherAttr=" size=\"10\" maxlength=\"11\" ezftoupper=\"\""/>
																				<ezf:inputHidden name="xxRecHistCratTs_G5" ezfName="xxRecHistCratTs_G5" />
																				<ezf:inputHidden name="xxRecHistCratByNm_G5" ezfName="xxRecHistCratByNm_G5" />
																				<ezf:inputHidden name="xxRecHistUpdTs_G5" ezfName="xxRecHistUpdTs_G5" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_G5" ezfName="xxRecHistUpdByNm_G5" />
																				<ezf:inputHidden name="xxRecHistTblNm_G5" ezfName="xxRecHistTblNm_G5" />
																			</td>
																			<td>
																				<ezf:label name="xxInInchWdtUomDescTxt_EA" ezfName="xxInInchWdtUomDescTxt_EA" otherAttr=" size=\"7\" maxlength=\"50\""/>
																			</td>
																			<td>
																				<ezf:inputText name="inInchWdt_UN" ezfName="inInchWdt_UN" value="99,999.9999" otherAttr=" size=\"10\" maxlength=\"11\" ezftoupper=\"\""/>
																				<ezf:inputHidden name="xxRecHistCratTs_G6" ezfName="xxRecHistCratTs_G6" />
																				<ezf:inputHidden name="xxRecHistCratByNm_G6" ezfName="xxRecHistCratByNm_G6" />
																				<ezf:inputHidden name="xxRecHistUpdTs_G6" ezfName="xxRecHistUpdTs_G6" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_G6" ezfName="xxRecHistUpdByNm_G6" />
																				<ezf:inputHidden name="xxRecHistTblNm_G6" ezfName="xxRecHistTblNm_G6" />
																			</td>
																			<td>
																				<ezf:select name="inInchWdtUomCd_UN" ezfName="inInchWdtUomCd_UN" ezfCodeName="inInchWdtUomCd_L1" ezfDispName="xxInInchWdtUomDescTxt_L1" otherAttr=" style=\"width:70px\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Height</td><!--MDSE_STORE_PKG.IN_INCH_HGT-->
																			<td>
																				<ezf:inputText name="inInchHgt_EA" ezfName="inInchHgt_EA" value="99,999.9999" otherAttr=" size=\"10\" maxlength=\"11\" ezftoupper=\"\""/>
																				<ezf:inputHidden name="xxRecHistCratTs_G7" ezfName="xxRecHistCratTs_G7" />
																				<ezf:inputHidden name="xxRecHistCratByNm_G7" ezfName="xxRecHistCratByNm_G7" />
																				<ezf:inputHidden name="xxRecHistUpdTs_G7" ezfName="xxRecHistUpdTs_G7" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_G7" ezfName="xxRecHistUpdByNm_G7" />
																				<ezf:inputHidden name="xxRecHistTblNm_G7" ezfName="xxRecHistTblNm_G7" />
																			</td>
																			<td>
																				<ezf:label name="xxInInchHgtUomDescTxt_EA" ezfName="xxInInchHgtUomDescTxt_EA" otherAttr=" size=\"7\" maxlength=\"50\""/>
																			</td>
																			<td>
																				<ezf:inputText name="inInchHgt_UN" ezfName="inInchHgt_UN" value="99,999.9999" otherAttr=" size=\"10\" maxlength=\"11\" ezftoupper=\"\""/>
																				<ezf:inputHidden name="xxRecHistCratTs_G8" ezfName="xxRecHistCratTs_G8" />
																				<ezf:inputHidden name="xxRecHistCratByNm_G8" ezfName="xxRecHistCratByNm_G8" />
																				<ezf:inputHidden name="xxRecHistUpdTs_G8" ezfName="xxRecHistUpdTs_G8" />
																				<ezf:inputHidden name="xxRecHistUpdByNm_G8" ezfName="xxRecHistUpdByNm_G8" />
																				<ezf:inputHidden name="xxRecHistTblNm_G8" ezfName="xxRecHistTblNm_G8" />
																			</td>
																			<td>
																				<ezf:select name="inInchHgtUomCd_UN" ezfName="inInchHgtUomCd_UN" ezfCodeName="inInchHgtUomCd_L1" ezfDispName="xxInInchHgtUomDescTxt_L1" otherAttr=" style=\"width:70px\""/>
																			</td>
																		</tr>
																	</table>
																	</fieldset>
																</td>
															</tr>
														</table>

														<table cellpadding="0">
															<tr>
																<td>&nbsp;
																</td>
																<td>
																	<fieldset style="width:340px">
																		<table cellpadding="0">
																			<tr>
																				<td class="stab"><b>Units of Measure</b></td>
																			</tr>
																			<tr>
																				<td>
																				    <table cellpadding="0" border="0">
																					    <col width="52">
																					    <col width="100">
																					    <col width="37">
																					    <col width="37">
																						<tr><!--DS_MDSE_INFO.PKG_UOM_CLS_CD-->
																							<td colspan="4" class="stab">Unit of Measure Class&nbsp;
																								<ezf:select name="pkgUomClsCd_H1" ezfName="pkgUomClsCd_H1" ezfBlank="1" ezfCodeName="pkgUomClsCd_L1" ezfDispName="pkgUomClsDescTxt_L1" otherEvent1=" onchange=\"sendServer('Change_PkgUomClsCd_Pulldown')\"" otherAttr=" style=\"width:120px\""/>
																							</td>
																						</tr>
<!--																						<tr>-->
<!--																							<!--PKG_UOM_CLS.BASE_PKG_UOM_NM-->
<!--																							<td colspan="4" class="stab">Base Unit&nbsp;<input type="text" name="pkgUomDescTxt_BA" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3"  size="10" maxlength="30" disabled ezftoupper="" ezfname="pkgUomDescTxt_BA"></td>-->
<!--																						</tr>-->
																					    <tr>
																						    <td class="stab">Packaging</td>
																						    <td>
																								<ezf:select name="pkgUomCd_H1" ezfName="pkgUomCd_H1" ezfBlank="1" ezfCodeName="pkgUomCd_BL" ezfDispName="pkgUomDescTxt_BL" otherAttr=" style=\"width:80px\""/>
																						    </td>
																						    <td><ezf:inputButton name="Add_PkgType_SellingUOM" value="Add" htmlClass="pBtn1" /></td>
																						    <td><ezf:inputButton name="Del_PkgType_Selling" value="Del" htmlClass="pBtn1" /></td>
																					    </tr>
																				    </table>
																				    <table border="0" cellpadding="1" cellspacing="0" >
																					    <tr>
																						    <td valign="top">
																							    <div style="height:62;">
																								    <table border="1" cellpadding="1" cellspacing="0">
																									    <col align="center" width="20">
																									    <col align="center" width="100">
																									    <col align="center" width="76">
																									    <col align="center" width="28">
																									    <tr>
																										    <td class="pClothBs">&nbsp;</td>
																										    <td class="pClothBs">Unit Of Measure</td>
																										    <td class="pClothBs">Qty Inside</td>
																										    <td class="pClothBs">Prim</td>
																									    </tr>
																								    </table>
																								    <div style="overflow-x:none; overflow-y:scroll; height:43;">
																								    <table border="1" cellpadding="1" cellspacing="0" id="K">
																									    <col align="center" width="20">
																									    <col align="left" width="100">
																									    <col align="center" width="76">
																									    <col align="center" width="28">
                                                                                                        <% int valueOfxxRadioBtn_K1 = 0; %>
																									    <ezf:row ezfHyo="K">
																									    <tr height="28">
																										    <td><ezf:inputCheckBox name="xxChkBox_K1" ezfName="xxChkBox_K1" value="Y" ezfHyo="K" ezfArrayNo="0" /></td>
																										    <td><ezf:label name="pkgUomDescTxt_K1" ezfName="pkgUomDescTxt_K1" ezfHyo="K" ezfArrayNo="0" /></td>
																										    <td><ezf:inputText name="inEachQty_K1" ezfName="inEachQty_K1" value="WWWWW.WWWW" ezfHyo="K" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"11\" ezftoupper=\"\""/></td>
														                                                    <td><ezf:inputRadio name="xxRadioBtn_K1" ezfName="xxRadioBtn_K1" value="<%= Integer.toString(valueOfxxRadioBtn_K1) %>" /></td>
														                                                    <td class="pAuditInfo">
																												<ezf:inputHidden name="xxRecHistCratTs_K1" ezfName="xxRecHistCratTs_K1" ezfHyo="K" ezfArrayNo="0" />
																												<ezf:inputHidden name="xxRecHistCratByNm_K1" ezfName="xxRecHistCratByNm_K1" ezfHyo="K" ezfArrayNo="0" />
																												<ezf:inputHidden name="xxRecHistUpdTs_K1" ezfName="xxRecHistUpdTs_K1" ezfHyo="K" ezfArrayNo="0" />
																												<ezf:inputHidden name="xxRecHistUpdByNm_K1" ezfName="xxRecHistUpdByNm_K1" ezfHyo="K" ezfArrayNo="0" />
																												<ezf:inputHidden name="xxRecHistTblNm_K1" ezfName="xxRecHistTblNm_K1" ezfHyo="K" ezfArrayNo="0" />
																											</td>
																									    </tr>
                                                                                                        <% valueOfxxRadioBtn_K1++; %>
																									    </ezf:row>
																									    <ezf:skip>
																									    <tr height="28">
																										    <td><input type="checkbox" class="" value="Y" name="xxChkBox_K1" ezfname="xxChkBox_K1" ezfhyo="K" ></td>
																										    <td><label ezfout name="pkgUomDescTxt_K1" ezfname="pkgUomDescTxt_K1" ezfhyo="K">WWWWWWWWW1</label></td>
																										    <td><input type="text" class="" size="8" maxlength="11" value="WWWWW.WWWW" name="inEachQty_K1" ezfname="inEachQty_K1" ezfhyo="K" ezftoupper></td>
														                                                    <td><input type="radio" name="xxRadioBtn_K1" ezfName="xxRadioBtn_K1" checked value="{EZF_ROW_NUMBER}"/></td>
																									    </tr>
																									    <tr height="28">
																										    <td><input type="checkbox" class="" value="Y" name="xxChkBox_K1" ezfname="xxChkBox_K1" ezfhyo="K" ></td>
																										    <td><label ezfout name="pkgUomDescTxt_K1" ezfname="pkgUomDescTxt_K1" ezfhyo="K">WWWWWWWWW1</label></td>
																										    <td><input type="text" class="" size="8" maxlength="11" value="WWWWW.WWWW" name="inEachQty_K1" ezfname="inEachQty_K1" ezfhyo="K" ezftoupper></td>
														                                                    <td><input type="radio" name="xxRadioBtn_K1" ezfName="xxRadioBtn_K1" checked value="{EZF_ROW_NUMBER}"/></td>
																									    </tr>
																									    <tr height="28">
																										    <td><input type="checkbox" class="" value="Y" name="xxChkBox_K1" ezfname="xxChkBox_K1" ezfhyo="K" ></td>
																										    <td><label ezfout name="pkgUomDescTxt_K1" ezfname="pkgUomDescTxt_K1" ezfhyo="K">WWWWWWWWW1</label></td>
																										    <td><input type="text" class="" size="8" maxlength="11" value="WWWWW.WWWW" name="inEachQty_K1" ezfname="inEachQty_K1" ezfhyo="K" ezftoupper></td>
														                                                    <td><input type="radio" name="xxRadioBtn_K1" ezfName="xxRadioBtn_K1" checked value="{EZF_ROW_NUMBER}"/></td>
																									    </tr>
																									    <tr height="28">
																										    <td><input type="checkbox" class="" value="Y" name="xxChkBox_K1" ezfname="xxChkBox_K1" ezfhyo="K" ></td>
																										    <td><label ezfout name="pkgUomDescTxt_K1" ezfname="pkgUomDescTxt_K1" ezfhyo="K">WWWWWWWWW1</label></td>
																										    <td><input type="text" class="" size="8" maxlength="11" value="WWWWW.WWWW" name="inEachQty_K1" ezfname="inEachQty_K1" ezfhyo="K" ezftoupper></td>
														                                                    <td><input type="radio" name="xxRadioBtn_K1" ezfName="xxRadioBtn_K1" checked value="{EZF_ROW_NUMBER}"/></td>
																									    </tr>
																									    <tr height="28">
																										    <td><input type="checkbox" class="" value="Y" name="xxChkBox_K1" ezfname="xxChkBox_K1" ezfhyo="K" ></td>
																										    <td><label ezfout name="pkgUomDescTxt_K1" ezfname="pkgUomDescTxt_K1" ezfhyo="K">WWWWWWWWW1</label></td>
																										    <td><input type="text" class="" size="8" maxlength="11" value="WWWWW.WWWW" name="inEachQty_K1" ezfname="inEachQty_K1" ezfhyo="K" ezftoupper></td>
														                                                    <td><input type="radio" name="xxRadioBtn_K1" ezfName="xxRadioBtn_K1" checked value="{EZF_ROW_NUMBER}"/></td>
																									    </tr>
																									    </ezf:skip>
																								    </table>
																								    </div>
																							    </div>
																						    </td>
																					    </tr>
																				    </table>
                                                                                </td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
													<td valign="top" width="365"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;
																</td>
																<td>
<%if(mdseItemTpCd_H1.equals("01")){%> <!--Machine-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="150" align="left">
																			<col width="190" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
	<!--MAIN ENGINE-->
																			<tr>
																				<td class="stab">Main Engine</td><!--DS_MDSE_INFO.MAIN_MACH_FLG-->
																				<td align="left"><ezf:inputCheckBox name="xxChkBox_ME" ezfName="xxChkBox_ME" value="Y" /></td>
																			</tr>
    <!--CRITICALITY-->
																			<tr>
																				<td class="stab">Criticality</td>
																				<td align="left"><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP_CD-->
																					<ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--REMAN AVAILABLE-->
																			<tr>
																				<td class="stab">Reman Available</td><!--DS_MDSE_INFO.RE_MNF_AVAL_FLG-->
																				<td align="left"><ezf:inputCheckBox name="xxChkBox_RM" ezfName="xxChkBox_RM" value="Y" /></td>
																			</tr>
    <!--SOW Required-->
																			<tr>
																				<td class="stab">SOW Required</td><!--DS_MDSE_INFO.SOW_REQ_FLG-->
																				<td align="left"><ezf:inputCheckBox name="sowReqFlg_H1" ezfName="sowReqFlg_H1" value="Y" /></td>

    <!--Service Charge Type-->

																			<!--<tr>-->
																			<!--	<td class="stab">Service Charge Type</td>-->
																			<!--	<td><!--DS_MDSE_INFO.SVC_CHRG_ITEM_TP_CD -->
																			<!--		<select style="width:150px" name="svcChrgItemTpCd_H1" ezfname="svcChrgItemTpCd_H1" ezflist="svcChrgItemTpCd_L1,svcChrgItemTpDescTxt_L1,99, ,blank">-->
																			<!--		<option>FW:Firmware</option>-->
																			<!--		</select>-->
																			<!--	</td>-->
																			<!--</tr>-->
																			
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("02")){%> <!--Accessory-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="150"  align="left">
																			<col width="190" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--CRITICALITY-->
																			<tr>
																				<td class="stab">Criticality</td>
																				<td><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP-->
																					<ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--REMAN AVAILABLE-->
																			<tr>
																				<td class="stab">Reman Available</td><!--DS_MDSE_INFO.RE_MNF_AVAL_FLG-->
																				<td><ezf:inputCheckBox name="xxChkBox_RM" ezfName="xxChkBox_RM" value="Y" /></td>
																			</tr>
    <!--SOW Required-->
																			<tr>
																				<td class="stab">SOW Required</td><!--DS_MDSE_INFO.SOW_REQ_FLG-->
																				<td align="left"><ezf:inputCheckBox name="sowReqFlg_H1" ezfName="sowReqFlg_H1" value="Y" /></td>
																			</tr>
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("03")){%> <!--Supply-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="180"  align="left">
																			<col width="160" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--SUPPLY OEM MANUFACTUER-->
																			<tr>
																				<td class="stab">Supply OEM Manufacturer</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_OEM_MNF_CD-->
																					<ezf:select name="imgSplyOemMnfCd_H1" ezfName="imgSplyOemMnfCd_H1" ezfBlank="1" ezfCodeName="imgSplyOemMnfCd_L1" ezfDispName="imgSplyOemMnfDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--SUPPLY OEM CODE-->
																			<tr>
																				<td class="stab">Supply OEM Code</td><!--DS_MDSE_INFO.IMG_SPLY_OEM_CD-->
																				<td><ezf:inputText name="imgSplyOemCd_H1" ezfName="imgSplyOemCd_H1" value="XX" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
																			</tr>
    <!--SUPPLY TYPE-->
																			<tr>
																				<td class="stab">Supply Type</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_TP_CD-->
																					<ezf:select name="imgSplyTpCd_H1" ezfName="imgSplyTpCd_H1" ezfBlank="1" ezfCodeName="imgSplyTpCd_L1" ezfDispName="imgSplyTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Supply Color-->
																			<tr>
																				<td class="stab">Supply Color</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_COLOR_TP_CD-->
																					<ezf:select name="imgSplyColorTpCd_H1" ezfName="imgSplyColorTpCd_H1" ezfBlank="1" ezfCodeName="imgSplyColorTpCd_L1" ezfDispName="imgSplyColorTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Supply Yield-->
																		<tr>
																			<td class="stab">Supply Yield</td><!--DS_MDSE_INFO.IMG_SPLY_YIELD_CNT-->
																			<td><ezf:inputText name="imgSplyYieldCnt_H1" ezfName="imgSplyYieldCnt_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																		</tr>
    <!--Supply Yield UOM-->
																			<tr>
																				<td class="stab">Supply Yield UOM</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_YIELD_UOM_CD-->
																					<ezf:select name="imgSplyYieldUomCd_H1" ezfName="imgSplyYieldUomCd_H1" ezfBlank="1" ezfCodeName="imgSplyYieldUomCd_L1" ezfDispName="imgSplyYieldUomDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Supply Yield Type-->
																			<tr>
																				<td class="stab">Supply Yield Type</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_YIELD_TP_CD-->
																					<ezf:select name="imgSplyYieldTpCd_H1" ezfName="imgSplyYieldTpCd_H1" ezfBlank="1" ezfCodeName="imgSplyYieldTpCd_L1" ezfDispName="imgSplyYieldTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Private Label Flag-->
																			<tr>
																				<td class="stab">Private Label Flag</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_PVT_LB_TP_CD-->
																					<ezf:select name="imgSplyPvtLbTpCd_H1" ezfName="imgSplyPvtLbTpCd_H1" ezfBlank="1" ezfCodeName="imgSplyPvtLbTpCd_L1" ezfDispName="imgSplyPvtLbTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Easy PAC I-->
																			<tr>
																				<td class="stab">Easy PAC I</td>
																				<td><!--DS_MDSE_INFO.EASY_PACK_TP_CD-->
																					<ezf:select name="easyPackTpCd_H1" ezfName="easyPackTpCd_H1" ezfBlank="1" ezfCodeName="easyPackTpCd_L1" ezfDispName="easyPackTpDescTxt_L1" otherEvent1=" onchange=\"sendServer('Protect_AreaOfPaper')\"" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Area of Paper(Square foot)-->
																			<tr>
																				<td class="stab">Area of Paper<BR>(Square foot)</td>
																				<!--DS_MDSE_INFO.AREA_OF_PAPER_NUM-->
																				<td><ezf:inputText name="areaOfPaperNum_H1" ezfName="areaOfPaperNum_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--CRITICALITY-->
                                                                            <tr>
                                                                                <td class="stab">Criticality</td>
                                                                                <td align="left"><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP_CD-->
                                                                                    <ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
                                                                            </tr>
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("04")){%> <!--Parts-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="150"  align="left">
																			<col width="190" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--Part Type-->
																			<tr>
																				<td class="stab">Part Type</td>
																				<td><!--DS_MDSE_INFO.PRT_ITEM_TP_CD-->
																					<ezf:select name="prtItemTpCd_H1" ezfName="prtItemTpCd_H1" ezfBlank="1" ezfCodeName="prtItemTpCd_L1" ezfDispName="prtItemTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Parts Dropship Disabled-->
																			<tr>
																				<td class="stab">Parts Dropship Disabled</td><!--DS_MDSE_INFO.PRT_DROP_SHIP_DSBL_FLG-->
																				<td><ezf:inputCheckBox name="xxChkBox_PD" ezfName="xxChkBox_PD" value="Y" /></td>
																			</tr>
    <!--Parts Yield - Outputs-->
																			<tr>
																				<td class="stab">Parts Yield - Outputs</td>
																				<!--DS_MDSE_INFO.PRT_YIELD_OTPT_QTY-->
																				<td><ezf:inputText name="prtYieldOtptQty_H1" ezfName="prtYieldOtptQty_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Parts Yield - Days-->
																			<tr>
																				<td class="stab">Parts Yield - Days</td>
																				<!--DS_MDSE_INFO.PRT_YIELD_DAYS_AOT-->
																				<td><ezf:inputText name="prtYieldDaysAot_H1" ezfName="prtYieldDaysAot_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Parts Survey Required-->
																			<tr>
																				<td class="stab">Parts Survey Required</td><!--DS_MDSE_INFO.PRT_SRVY_REQ_FLG-->
																				<td><ezf:inputCheckBox name="prtSrvyReqFlg_H1" ezfName="prtSrvyReqFlg_H1" value="Y" /></td>
																			</tr>
    <!--CRITICALITY-->
                                                                            <tr>
                                                                                <td class="stab">Criticality</td>
                                                                                <td align="left"><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP_CD-->
                                                                                    <ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
                                                                            </tr>
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("05")){%> <!--Maintenance-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="180"  align="left">
																			<col width="180" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--Service Allocation Type-->
																			<tr>
																				<td class="stab">Service Allocation Type</td>
																				<td><!--DS_MDSE_INFO.SVC_ALLOC_TP_CD-->
																					<ezf:select name="svcAllocTpCd_H1" ezfName="svcAllocTpCd_H1" ezfBlank="1" ezfCodeName="svcAllocTpCd_L1" ezfDispName="svcAllocTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Coverage Base Type-->
																			<tr>
																				<td class="stab">Coverage Base Type</td>
																				<td><!--DS_MDSE_INFO.SVC_COV_BASE_TP_CD-->
																					<ezf:select name="svcCovBaseTpCd_H1" ezfName="svcCovBaseTpCd_H1" ezfBlank="1" ezfCodeName="svcCovBaseTpCd_L1" ezfDispName="svcCovBaseTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Service Coverage Type-->
																			<tr>
																				<td class="stab">Service Coverage Type</td>
																				<td><!--DS_MDSE_INFO.SVC_COV_TMPL_TP_CD-->
																					<ezf:select name="svcCovTmplTpCd_H1" ezfName="svcCovTmplTpCd_H1" ezfBlank="1" ezfCodeName="svcCovTmplTpCd_L1" ezfDispName="svcCovTmplTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Service Program Type-->
																			<tr>
																				<td class="stab">Service Program Type</td>
																				<td><!--MDSE.SVC_PGM_TP_CD-->
																					<ezf:select name="svcPgmTpCd_H1" ezfName="svcPgmTpCd_H1" ezfBlank="1" ezfCodeName="svcPgmTpCd_L1" ezfDispName="svcPgmTpDescTxt_L1" otherEvent1=" onchange=\"sendServer('Change_svcPgmTpCd_Pulldown')\"" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Override Manufacturer Warranty-->
																			<tr>
																				<td class="stab">Override Manufacturer Warranty</td><!--MDSE.OVRD_MNF_WTY_FLG-->
																				<td><ezf:inputCheckBox name="xxChkBox_OM" ezfName="xxChkBox_OM" value="Y" /></td>
																			</tr>
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("12")){%> <!--Maintenance Charge-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="160"  align="left">
																			<col width="180" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--Service Allocation Type-->
																			<tr>
																				<td class="stab">Service Allocation Type</td>
																				<td><!--DS_MDSE_INFO.SVC_ALLOC_TP_CD-->
																					<ezf:select name="svcAllocTpCd_H1" ezfName="svcAllocTpCd_H1" ezfBlank="1" ezfCodeName="svcAllocTpCd_L1" ezfDispName="svcAllocTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Service Charge Type-->
																			<!--<tr>-->
																			<!--	<td class="stab">Service Charge Type</td>-->
																			<!--	<td><!--DS_MDSE_INFO.SVC_CHRG_ITEM_TP_CD -->
																			<!--		<select style="width:150px" name="svcChrgItemTpCd_H1" ezfname="svcChrgItemTpCd_H1" ezflist="svcChrgItemTpCd_L1,svcChrgItemTpDescTxt_L1,99, ,blank">-->
																			<!--		<option>FW:Firmware</option>-->
																			<!--		</select>-->
																			<!--	</td>-->
																			<!--</tr>-->
																		</table>
																	    <table cellpadding="0" border="0">
																		    <col width="37">
																		    <col width="37">
																		    <tr>
																			    <td><ezf:inputButton name="Add_TermCondOpt" value="Add" htmlClass="pBtn1" /></td>
																			    <td><ezf:inputButton name="Del_TermCondOpt" value="Del" htmlClass="pBtn1" /></td>
																		    </tr>
																	    </table>
																	    <table border="0" cellpadding="1" cellspacing="0" >
																		    <tr>
																			    <td valign="top">
																				    <div style="height:62;">
																					    <table border="1" cellpadding="1" cellspacing="0">
																						    <col align="center" width="20">
																						    <col align="center" width="130">
																						    <col align="center" width="130">
																						    <tr>
																							    <td class="pClothBs">&nbsp;</td>
																							    <td class="pClothBs">T&C Option</td>
																							    <td class="pClothBs">Value</td>
																						    </tr>
																					    </table>
																					    <div style="overflow-x:none; overflow-y:scroll; height:62;">
																					    <table border="1" cellpadding="1" cellspacing="0" id="N">
																						    <col align="center" width="20">
																						    <col align="left" width="130">
																						    <col align="left" width="130">
																						    <ezf:row ezfHyo="N">
																						    <tr height="28">
																							    <td><ezf:inputCheckBox name="xxChkBox_N1" ezfName="xxChkBox_N1" value="Y" ezfHyo="N" ezfArrayNo="0" /></td>
																								<td><!--TERM_COND_OPT_TP_CD-->
																									<ezf:select name="termCondOptTpCd_N1" ezfName="termCondOptTpCd_N1" ezfHyo="N" ezfArrayNo="0" ezfBlank="1" ezfCodeName="termCondOptTpCd_L1" ezfDispName="termCondOptTpDescTxt_L1" otherAttr=" style=\"width:130px\""/>
																								</td>
																							    <td><ezf:inputText name="termCondOptValTxt_N1" ezfName="termCondOptValTxt_N1" value="WWWWW.WWWW" ezfHyo="N" ezfArrayNo="0" otherAttr=" style=\"width:130px\" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																						    </tr>
																						    </ezf:row>
																					    </table>
																					    </div>
																				    </div>
																			    </td>
																		    </tr>
																	    </table>

																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("06")){%> <!--Maintenance Overages-->

																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="160"  align="left">
																			<col width="180" align="left">
    <!--Service Allocation Type-->
																			<tr>
																				<td class="stab">Service Allocation Type</td>
																				<td><!--DS_MDSE_INFO.SVC_ALLOC_TP_CD-->
																					<ezf:select name="svcAllocTpCd_H1" ezfName="svcAllocTpCd_H1" ezfBlank="1" ezfCodeName="svcAllocTpCd_L1" ezfDispName="svcAllocTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
																		</table>
																	</fieldset>

<%}%>
<%if(mdseItemTpCd_H1.equals("07")){%> <!--Software-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="190"  align="left">
																			<col width="150" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--Software Category-->
																			<tr>
																				<td class="stab">Software Category</td>
																				<td><!--DS_MDSE_INFO.SW_CATG_CD-->
																					<ezf:select name="swCatgCd_H1" ezfName="swCatgCd_H1" ezfBlank="1" ezfCodeName="swCatgCd_L1" ezfDispName="swCatgDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Software Type-->
																			<!--<tr>-->
																			<!--	<td class="stab">Software Type</td>-->
																			<!--	<td><!--DS_MDSE_INFO.SW_TP_CD-->
																			<!--		<select name="swTpCd_H1" ezfname="swTpCd_H1" ezflist="swTpCd_L1,swTpDescTxt_L1,99, ,blank">-->
																			<!--		<option>SW:Software</option>-->
																			<!--		<option>LC:License</option>-->
																			<!--		<option>SL:Software and License</option>-->
																			<!--		</select>-->
																			<!--	</td>-->
																			<!--</tr>-->
    <!--Software Version-->
																			<tr>
																				<td class="stab">Software Version</td><!--DS_MDSE_INFO.SW_VRSN_TXT-->
																				<td><ezf:inputText name="swVrsnTxt_H1" ezfName="swVrsnTxt_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Software Product Category-->
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_26" ezfEmulateBtn="1" ezfGuard="Sw_Prod_Catg_Link" otherAttr=" tabindex=\"-1\"">Software Product Category</ezf:anchor></td>
																				<td><!--DS_MDSE_INFO.SW_PROD_CATG_CD-->
										                                            <ezf:inputText name="swProdCatgCd_H1" ezfName="swProdCatgCd_H1" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="swProdCatgDescTxt_H1" ezfName="swProdCatgDescTxt_H1" value="XX" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
																				</td>
																			</tr>
    <!--License Control-->
																			<tr>
																				<td class="stab">License Control</td>
																				<td><!--DS_MDSE_INFO.SW_LIC_CTRL_TP_CD-->
																					<ezf:select name="swLicCtrlTpCd_H1" ezfName="swLicCtrlTpCd_H1" ezfBlank="1" ezfCodeName="swLicCtrlTpCd_L1" ezfDispName="swLicCtrlTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--ELAN Controlled-->
																			<tr>
																				<td class="stab">Elan Control</td><!--MDSE.INTG_PROD_CATG-->
																				<td><ezf:inputCheckBox name="xxChkBox_EC" ezfName="xxChkBox_EC" value="Y" /></td>
																			</tr>
    <!--Seats From-->
																			<tr>
																				<td class="stab">Seats From</td><!--DS_MDSE_INFO.SW_LIC_SEAT_FROM_QTY-->
																				<td><ezf:inputText name="swLicSeatFromQty_H1" ezfName="swLicSeatFromQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Seats To-->
																			<tr>
																				<td class="stab">Seats To</td><!--DS_MDSE_INFO.SW_LIC_SEAT_TO_QTY-->
																				<td><ezf:inputText name="swLicSeatToQty_H1" ezfName="swLicSeatToQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Fixed # of Seats-->
																			<tr>
																				<td class="stab">Fixed # of Seat</td><!--DS_MDSE_INFO.SW_LIC_SEAT_FIX_QTY-->
																				<td><ezf:inputText name="swLicSeatFixQty_H1" ezfName="swLicSeatFixQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Software Maintenance Controlled-->
																			<tr>
																				<td class="stab">Software Maintenance Controlled</td>
																				<td align="left"><!--DS_MDSE_INFO.SW_MAINT_CTRL_TP_CD-->
                                                                                    <ezf:select name="swMaintCtrlTpCd_H1" ezfName="swMaintCtrlTpCd_H1" ezfBlank="1" ezfCodeName="swMaintCtrlTpCd_L1" ezfDispName="swMaintCtrlTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
																			</tr>
    <!--Assurance Points per License-->
																			<tr>
																				<td class="stab">Assurance Points Per License</td>
																				<td align="left"><ezf:inputText name="asrnPntPerLicQty_H1" ezfName="asrnPntPerLicQty_H1" value="9,999,999,999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			    <!--DS_MDSE_INFO.ASRN_PNT_PER_LIC_QTY-->
																			</tr>
    <!--Bundled Maintenance Item-->
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_29" ezfEmulateBtn="1" ezfGuard="Bundle_Item_Link" otherAttr=" tabindex=\"-1\"">Bundled Maintenance Item</ezf:anchor></td>
																				<td align="left"><ezf:inputText name="bdlMaintMdseCd_H1" ezfName="bdlMaintMdseCd_H1" value="1234B001" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
																			    <!--DS_MDSE_INFO.BDL_MAINT_MDSE_CD-->
																			</tr>
    <!--Maintenance - POP Available-->
																			<tr>
																				<td class="stab">Maintenance - POP Available</td><!--DS_MDSE_INFO.MAINT_POP_AVAL_FLG-->
																				<td align="left"><ezf:inputCheckBox name="maintPopAvalFlg_H1" ezfName="maintPopAvalFlg_H1" value="Y" /></td>
																			</tr>
    <!--Extended Maintenance - POP Available-->
																			<tr>
																				<td class="stab">Extended Maintenance - POP Available</td><!--DS_MDSE_INFO.EXT_MAINT_POP_AVAL_FLG-->
																				<td align="left"><ezf:inputCheckBox name="extMaintPopAvalFlg_H1" ezfName="extMaintPopAvalFlg_H1" value="Y" /></td>
																			</tr>
    <!--CRITICALITY-->
                                                                            <tr>
                                                                                <td class="stab">Criticality</td>
                                                                                <td align="left"><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP_CD-->
                                                                                    <ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
                                                                            </tr>
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("13")){%> <!--Software License-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="190"  align="left">
																			<col width="150" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--Software Category-->
																			<tr>
																				<td class="stab">Software Category</td>
																				<td><!--DS_MDSE_INFO.SW_CATG_CD-->
																					<ezf:select name="swCatgCd_H1" ezfName="swCatgCd_H1" ezfBlank="1" ezfCodeName="swCatgCd_L1" ezfDispName="swCatgDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Software Type-->
																			<!--<tr>-->
																			<!--	<td class="stab">Software Type</td>-->
																			<!--	<td><!--DS_MDSE_INFO.SW_TP_CD-->
																			<!--		<select name="swTpCd_H1" ezfname="swTpCd_H1" ezflist="swTpCd_L1,swTpDescTxt_L1,99, ,blank">-->
																			<!--		<option>SW:Software</option>-->
																			<!--		<option>LC:License</option>-->
																			<!--		<option>SL:Software and License</option>-->
																			<!--		</select>-->
																			<!--	</td>-->
																			<!--</tr>-->
    <!--Software Version-->
																			<tr>
																				<td class="stab">Software Version</td><!--DS_MDSE_INFO.SW_VRSN_TXT-->
																				<td><ezf:inputText name="swVrsnTxt_H1" ezfName="swVrsnTxt_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Software Product Category-->
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_26" ezfEmulateBtn="1" ezfGuard="Sw_Prod_Catg_Link" otherAttr=" tabindex=\"-1\"">Software Product Category</ezf:anchor></td>
																				<td><!--DS_MDSE_INFO.SW_PROD_CATG_CD-->
										                                            <ezf:inputText name="swProdCatgCd_H1" ezfName="swProdCatgCd_H1" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="swProdCatgDescTxt_H1" ezfName="swProdCatgDescTxt_H1" value="XX" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
																				</td>
																			</tr>
    <!--License Control-->
																			<tr>
																				<td class="stab">License Control</td>
																				<td><!--DS_MDSE_INFO.SW_LIC_CTRL_TP_CD-->
																					<ezf:select name="swLicCtrlTpCd_H1" ezfName="swLicCtrlTpCd_H1" ezfBlank="1" ezfCodeName="swLicCtrlTpCd_L1" ezfDispName="swLicCtrlTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--ELAN Controlled-->
																			<tr>
																				<td class="stab">Elan Control</td><!--MDSE.INTG_PROD_CATG-->
																				<td><ezf:inputCheckBox name="xxChkBox_EC" ezfName="xxChkBox_EC" value="Y" /></td>
																			</tr>
    <!--Seats From-->
																			<tr>
																				<td class="stab">Seats From</td><!--DS_MDSE_INFO.SW_LIC_SEAT_FROM_QTY-->
																				<td><ezf:inputText name="swLicSeatFromQty_H1" ezfName="swLicSeatFromQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Seats To-->
																			<tr>
																				<td class="stab">Seats To</td><!--DS_MDSE_INFO.SW_LIC_SEAT_TO_QTY-->
																				<td><ezf:inputText name="swLicSeatToQty_H1" ezfName="swLicSeatToQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Fixed # of Seats-->
																			<tr>
																				<td class="stab">Fixed # of Seat</td><!--DS_MDSE_INFO.SW_LIC_SEAT_FIX_QTY-->
																				<td><ezf:inputText name="swLicSeatFixQty_H1" ezfName="swLicSeatFixQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Software Maintenance Controlled-->
																			<tr>
																				<td class="stab">Software Maintenance Controlled</td>
																				<td align="left"><!--DS_MDSE_INFO.SW_MAINT_CTRL_TP_CD-->
                                                                                    <ezf:select name="swMaintCtrlTpCd_H1" ezfName="swMaintCtrlTpCd_H1" ezfBlank="1" ezfCodeName="swMaintCtrlTpCd_L1" ezfDispName="swMaintCtrlTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
																			</tr>
    <!--Assurance Points per License-->
																			<tr>
																				<td class="stab">Assurance Points Per License</td>
																				<td align="left"><ezf:inputText name="asrnPntPerLicQty_H1" ezfName="asrnPntPerLicQty_H1" value="9,999,999,999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			    <!--DS_MDSE_INFO.ASRN_PNT_PER_LIC_QTY-->
																			</tr>
    <!--Bundled Maintenance Item-->
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_29" ezfEmulateBtn="1" ezfGuard="Bundle_Item_Link" otherAttr=" tabindex=\"-1\"">Bundled Maintenance Item</ezf:anchor></td>
																				<td align="left"><ezf:inputText name="bdlMaintMdseCd_H1" ezfName="bdlMaintMdseCd_H1" value="1234B001" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
																			    <!--DS_MDSE_INFO.BDL_MAINT_MDSE_CD-->
																			</tr>
    <!--Maintenance - POP Available-->
																			<tr>
																				<td class="stab">Maintenance - POP Available</td><!--DS_MDSE_INFO.MAINT_POP_AVAL_FLG-->
																				<td align="left"><ezf:inputCheckBox name="maintPopAvalFlg_H1" ezfName="maintPopAvalFlg_H1" value="Y" /></td>
																			</tr>
    <!--Extended Maintenance - POP Available-->
																			<tr>
																				<td class="stab">Extended Maintenance - POP Available</td><!--DS_MDSE_INFO.EXT_MAINT_POP_AVAL_FLG-->
																				<td align="left"><ezf:inputCheckBox name="extMaintPopAvalFlg_H1" ezfName="extMaintPopAvalFlg_H1" value="Y" /></td>
																			</tr>
    <!--CRITICALITY-->
                                                                            <tr>
                                                                                <td class="stab">Criticality</td>
                                                                                <td align="left"><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP_CD-->
                                                                                    <ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
                                                                            </tr>
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("08")){%> <!--Software Maintenance-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="190"  align="left">
																			<col width="150" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--Software Category-->
																			<tr>
																				<td class="stab">Software Category</td>
																				<td><!--DS_MDSE_INFO.SW_CATG_CD-->
																					<ezf:select name="swCatgCd_H1" ezfName="swCatgCd_H1" ezfBlank="1" ezfCodeName="swCatgCd_L1" ezfDispName="swCatgDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Software Type-->
																			<!--<tr>-->
																			<!--	<td class="stab">Software Type</td>-->
																			<!--	<td><!--DS_MDSE_INFO.SW_TP_CD-->
																			<!--		<select name="swTpCd_H1" ezfname="swTpCd_H1" ezflist="swTpCd_L1,swTpDescTxt_L1,99, ,blank">-->
																			<!--		<option>SW:Software</option>-->
																			<!--		<option>LC:License</option>-->
																			<!--		<option>SL:Software and License</option>-->
																			<!--		</select>-->
																			<!--	</td>-->
																			<!--</tr>-->
    <!--Software Version-->
																			<tr>
																				<td class="stab">Software Version</td><!--DS_MDSE_INFO.SW_VRSN_TXT-->
																				<td><ezf:inputText name="swVrsnTxt_H1" ezfName="swVrsnTxt_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Software Product Category-->
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_26" ezfEmulateBtn="1" ezfGuard="Sw_Prod_Catg_Link" otherAttr=" tabindex=\"-1\"">Software Product Category</ezf:anchor></td>
																				<td><!--DS_MDSE_INFO.SW_PROD_CATG_CD-->
										                                            <ezf:inputText name="swProdCatgCd_H1" ezfName="swProdCatgCd_H1" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="swProdCatgDescTxt_H1" ezfName="swProdCatgDescTxt_H1" value="XX" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
																				</td>
																			</tr>
    <!--Seats From-->
																			<tr>
																				<td class="stab">Seats From</td><!--DS_MDSE_INFO.SW_LIC_SEAT_FROM_QTY-->
																				<td><ezf:inputText name="swLicSeatFromQty_H1" ezfName="swLicSeatFromQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Seats To-->
																			<tr>
																				<td class="stab">Seats To</td><!--DS_MDSE_INFO.SW_LIC_SEAT_TO_QTY-->
																				<td><ezf:inputText name="swLicSeatToQty_H1" ezfName="swLicSeatToQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Fixed # of Seats-->
																			<tr>
																				<td class="stab">Fixed # of Seat</td><!--DS_MDSE_INFO.SW_LIC_SEAT_FIX_QTY-->
																				<td><ezf:inputText name="swLicSeatFixQty_H1" ezfName="swLicSeatFixQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Maintenance Item Type-->
																			<tr>
																				<td class="stab">Maintenance Item Type</td>
																				<td><!--DS_MDSE_INFO.SW_MAINT_TP_CD-->
																					<ezf:select name="swMaintTpCd_H1" ezfName="swMaintTpCd_H1" ezfBlank="1" ezfCodeName="swMaintTpCd_L1" ezfDispName="swMaintTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Maintenance Item Term-->
																			<tr>
																				<td class="stab">Maintenance Item Term(Month)</td><!--DS_MDSE_INFO.MAINT_ITEM_TERM_MTH_NUM-->
																				<td><ezf:inputText name="maintItemTermMthNum_H1" ezfName="maintItemTermMthNum_H1" value="2015" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Assurance Point Min-->
																			<tr>
																				<td class="stab">Assurance Point Min</td><!--DS_MDSE_INFO.ASRN_PNT_MIN_QTY-->
																				<td><ezf:inputText name="asrnPntMinQty_H1" ezfName="asrnPntMinQty_H1" value="2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Assurance Points Max-->
																			<tr>
																				<td class="stab">Assurance Point Max</td><!--DS_MDSE_INFO.ASRN_PNT_MAX_QTY-->
																				<td><ezf:inputText name="asrnPntMaxQty_H1" ezfName="asrnPntMaxQty_H1" value="2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Assurance Points Fixed per ordered Qty-->
																			<tr>
																				<td class="stab">Assurance Points Fixed</td><!--DS_MDSE_INFO.ASRN_PNT_FIX_PER_ORD_QTY-->
																				<td><ezf:inputText name="asrnPntFixPerOrdQty_H1" ezfName="asrnPntFixPerOrdQty_H1" value="2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("09")){%> <!--Intangible-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="110"  align="left">
																			<col width="230" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--Intangible Type-->
																			<tr>
																				<td class="stab">Intangible Type</td>
																				<td><!--DS_MDSE_INFO.DS_INTG_MDSE_TP_CD-->
																					<ezf:select name="dsIntgMdseTpCd_H1" ezfName="dsIntgMdseTpCd_H1" ezfBlank="1" ezfCodeName="dsIntgMdseTpCd_L1" ezfDispName="dsIntgMdseTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("10")){%> <!--Set-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="110"  align="left">
																			<col width="230" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--SOW Required-->
																			<tr>
																				<td class="stab">SOW Required</td><!--DS_MDSE_INFO.SOW_REQ_FLG-->
																				<td align="left"><ezf:inputCheckBox name="sowReqFlg_H1" ezfName="sowReqFlg_H1" value="Y" /></td>
																			</tr>
	<%if(coaMdseTpCd_H1.equals("30") || coaMdseTpCd_H1.equals("31")){%>
    <!--Easy PAC I-->
																			<tr>
																				<td class="stab">Easy PAC I</td>
																				<td><!--DS_MDSE_INFO.EASY_PACK_TP_CD-->
																					<ezf:select name="easyPackTpCd_H1" ezfName="easyPackTpCd_H1" ezfBlank="1" ezfCodeName="easyPackTpCd_L1" ezfDispName="easyPackTpDescTxt_L1" otherEvent1=" onchange=\"sendServer('Protect_AreaOfPaper')\"" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Area of Paper(Square foot)-->
																			<tr>
																				<td class="stab">Area of Paper<BR>(Square foot)</td>
																				<!--DS_MDSE_INFO.AREA_OF_PAPER_NUM-->
																				<td><ezf:inputText name="areaOfPaperNum_H1" ezfName="areaOfPaperNum_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
	<%}%>
	<!-- START 2022/08/05 T.NEMA [QC#60319, ADD] -->
	<!--CRITICALITY-->
																			<tr>
																				<td class="stab">Criticality</td>
																				<td align="left"><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP_CD-->
																				<ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																			</td>
																			</tr>
	<!-- END   2022/08/05 T.NEMA [QC#60319, ADD] -->
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("11")){%> <!--Kit-->

    <%if(coaMdseTpCd_H1.equals("10")){%> <!--Machine-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="150" align="left">
																			<col width="190" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
	<!--MAIN ENGINE-->
																			<tr>
																				<td class="stab">Main Engine</td><!--DS_MDSE_INFO.MAIN_MACH_FLG-->
																				<td align="left"><ezf:inputCheckBox name="xxChkBox_ME" ezfName="xxChkBox_ME" value="Y" /></td>
																			</tr>
    <!--CRITICALITY-->
																			<tr>
																				<td class="stab">Criticality</td>
																				<td align="left"><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP_CD-->
																					<ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--REMAN AVAILABLE-->
																			<tr>
																				<td class="stab">Reman Available</td><!--DS_MDSE_INFO.RE_MNF_AVAL_FLG-->
																				<td align="left"><ezf:inputCheckBox name="xxChkBox_RM" ezfName="xxChkBox_RM" value="Y" /></td>
																			</tr>
    <!--SOW Required-->
																			<tr>
																				<td class="stab">SOW Required</td><!--DS_MDSE_INFO.SOW_REQ_FLG-->
																				<td align="left"><ezf:inputCheckBox name="sowReqFlg_H1" ezfName="sowReqFlg_H1" value="Y" /></td>

    <!--Service Charge Type-->

																			<!--<tr>-->
																			<!--	<td class="stab">Service Charge Type</td>-->
																			<!--	<td><!--DS_MDSE_INFO.SVC_CHRG_ITEM_TP_CD -->
																			<!--		<select style="width:150px" name="svcChrgItemTpCd_H1" ezfname="svcChrgItemTpCd_H1" ezflist="svcChrgItemTpCd_L1,svcChrgItemTpDescTxt_L1,99, ,blank">-->
																			<!--		<option>FW:Firmware</option>-->
																			<!--		</select>-->
																			<!--	</td>-->
																			<!--</tr>-->
																			
																		</table>
																	</fieldset>
    <%}%>
    <%if(coaMdseTpCd_H1.equals("20")){%> <!--Accessory-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="150"  align="left">
																			<col width="190" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--CRITICALITY-->
																			<tr>
																				<td class="stab">Criticality</td>
																				<td><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP-->
																					<ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--REMAN AVAILABLE-->
																			<tr>
																				<td class="stab">Reman Available</td><!--DS_MDSE_INFO.RE_MNF_AVAL_FLG-->
																				<td><ezf:inputCheckBox name="xxChkBox_RM" ezfName="xxChkBox_RM" value="Y" /></td>
																			</tr>
    <!--SOW Required-->
																			<tr>
																				<td class="stab">SOW Required</td><!--DS_MDSE_INFO.SOW_REQ_FLG-->
																				<td align="left"><ezf:inputCheckBox name="sowReqFlg_H1" ezfName="sowReqFlg_H1" value="Y" /></td>
																			</tr>
																		</table>
																	</fieldset>
    <%}%>
    <%if(coaMdseTpCd_H1.equals("30")){%> <!--Supply-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="180"  align="left">
																			<col width="160" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--SUPPLY OEM MANUFACTUER-->
																			<tr>
																				<td class="stab">Supply OEM Manufacturer</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_OEM_MNF_CD-->
																					<ezf:select name="imgSplyOemMnfCd_H1" ezfName="imgSplyOemMnfCd_H1" ezfBlank="1" ezfCodeName="imgSplyOemMnfCd_L1" ezfDispName="imgSplyOemMnfDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--SUPPLY OEM CODE-->
																			<tr>
																				<td class="stab">Supply OEM Code</td><!--DS_MDSE_INFO.IMG_SPLY_OEM_CD-->
																				<td><ezf:inputText name="imgSplyOemCd_H1" ezfName="imgSplyOemCd_H1" value="XX" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
																			</tr>
    <!--SUPPLY TYPE-->
																			<tr>
																				<td class="stab">Supply Type</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_TP_CD-->
																					<ezf:select name="imgSplyTpCd_H1" ezfName="imgSplyTpCd_H1" ezfBlank="1" ezfCodeName="imgSplyTpCd_L1" ezfDispName="imgSplyTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Supply Color-->
																			<tr>
																				<td class="stab">Supply Color</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_COLOR_TP_CD-->
																					<ezf:select name="imgSplyColorTpCd_H1" ezfName="imgSplyColorTpCd_H1" ezfBlank="1" ezfCodeName="imgSplyColorTpCd_L1" ezfDispName="imgSplyColorTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Supply Yield-->
																		<tr>
																			<td class="stab">Supply Yield</td><!--DS_MDSE_INFO.IMG_SPLY_YIELD_CNT-->
																			<td><ezf:inputText name="imgSplyYieldCnt_H1" ezfName="imgSplyYieldCnt_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																		</tr>
    <!--Supply Yield UOM-->
																			<tr>
																				<td class="stab">Supply Yield UOM</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_YIELD_UOM_CD-->
																					<ezf:select name="imgSplyYieldUomCd_H1" ezfName="imgSplyYieldUomCd_H1" ezfBlank="1" ezfCodeName="imgSplyYieldUomCd_L1" ezfDispName="imgSplyYieldUomDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Supply Yield Type-->
																			<tr>
																				<td class="stab">Supply Yield Type</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_YIELD_TP_CD-->
																					<ezf:select name="imgSplyYieldTpCd_H1" ezfName="imgSplyYieldTpCd_H1" ezfBlank="1" ezfCodeName="imgSplyYieldTpCd_L1" ezfDispName="imgSplyYieldTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Private Label Flag-->
																			<tr>
																				<td class="stab">Private Label Flag</td>
																				<td><!--DS_MDSE_INFO.IMG_SPLY_PVT_LB_TP_CD-->
																					<ezf:select name="imgSplyPvtLbTpCd_H1" ezfName="imgSplyPvtLbTpCd_H1" ezfBlank="1" ezfCodeName="imgSplyPvtLbTpCd_L1" ezfDispName="imgSplyPvtLbTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Easy PAC I-->
																			<tr>
																				<td class="stab">Easy PAC I</td>
																				<td><!--DS_MDSE_INFO.EASY_PACK_TP_CD-->
																					<ezf:select name="easyPackTpCd_H1" ezfName="easyPackTpCd_H1" ezfBlank="1" ezfCodeName="easyPackTpCd_L1" ezfDispName="easyPackTpDescTxt_L1" otherEvent1=" onchange=\"sendServer('Protect_AreaOfPaper')\"" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Area of Paper(Square foot)-->
																			<tr>
																				<td class="stab">Area of Paper<BR>(Square foot)</td>
																				<!--DS_MDSE_INFO.AREA_OF_PAPER_NUM-->
																				<td><ezf:inputText name="areaOfPaperNum_H1" ezfName="areaOfPaperNum_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--CRITICALITY-->
                                                                            <tr>
                                                                                <td class="stab">Criticality</td>
                                                                                <td><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP-->
                                                                                    <ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
                                                                            </tr>
																		</table>
																	</fieldset>
    <%}%>
    <%if(coaMdseTpCd_H1.equals("53")){%> <!--Software-->
                                                                    <fieldset>
                                                                        <table cellpadding="0" width="340">
                                                                            <col width="150"  align="left">
                                                                            <col width="190" align="left">
                                                                            <tr>
                                                                                <td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
                                                                            </tr>
    <!--CRITICALITY-->
                                                                            <tr>
                                                                                <td class="stab">Criticality</td>
                                                                                <td><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP-->
                                                                                    <ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </fieldset>
    <%}%>
    <%if(coaMdseTpCd_H1.equals("71")){%> <!--Parts-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="150"  align="left">
																			<col width="190" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--Part Type-->
																			<tr>
																				<td class="stab">Part Type</td>
																				<td><!--DS_MDSE_INFO.PRT_ITEM_TP_CD-->
																					<ezf:select name="prtItemTpCd_H1" ezfName="prtItemTpCd_H1" ezfBlank="1" ezfCodeName="prtItemTpCd_L1" ezfDispName="prtItemTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Parts Dropship Disabled-->
																			<tr>
																				<td class="stab">Parts Dropship Disabled</td><!--DS_MDSE_INFO.PRT_DROP_SHIP_DSBL_FLG-->
																				<td><ezf:inputCheckBox name="xxChkBox_PD" ezfName="xxChkBox_PD" value="Y" /></td>
																			</tr>
    <!--Parts Yield - Outputs-->
																			<tr>
																				<td class="stab">Parts Yield - Outputs</td>
																				<!--DS_MDSE_INFO.PRT_YIELD_OTPT_QTY-->
																				<td><ezf:inputText name="prtYieldOtptQty_H1" ezfName="prtYieldOtptQty_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Parts Yield - Days-->
																			<tr>
																				<td class="stab">Parts Yield - Days</td>
																				<!--DS_MDSE_INFO.PRT_YIELD_DAYS_AOT-->
																				<td><ezf:inputText name="prtYieldDaysAot_H1" ezfName="prtYieldDaysAot_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Parts Survey Required-->
																			<tr>
																				<td class="stab">Parts Survey Required</td><!--DS_MDSE_INFO.PRT_SRVY_REQ_FLG-->
																				<td><ezf:inputCheckBox name="prtSrvyReqFlg_H1" ezfName="prtSrvyReqFlg_H1" value="Y" /></td>
																			</tr>
    <!--CRITICALITY-->
                                                                            <tr>
                                                                                <td class="stab">Criticality</td>
                                                                                <td><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP-->
                                                                                    <ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
                                                                            </tr>
																		</table>
																	</fieldset>
    <%}%>
    <%if(coaMdseTpCd_H1.equals("72")){%> <!--Tools-->
                                                                    <fieldset>
                                                                        <table cellpadding="0" width="340">
                                                                            <col width="150"  align="left">
                                                                            <col width="190" align="left">
                                                                            <tr>
                                                                                <td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
                                                                            </tr>
    <!--CRITICALITY-->
                                                                            <tr>
                                                                                <td class="stab">Criticality</td>
                                                                                <td><!--DS_MDSE_INFO.BACK_ORD_IMPCT_TP-->
                                                                                    <ezf:select name="backOrdImpctTpCd_H1" ezfName="backOrdImpctTpCd_H1" ezfBlank="1" ezfCodeName="backOrdImpctTpCd_L1" ezfDispName="backOrdImpctTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </fieldset>
    <%}%>

<%}%>
<%if(mdseItemTpCd_H1.equals("14")){%> <!--Professional Service-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="110"  align="left">
																			<col width="230" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--SOW Required-->
																			<tr>
																				<td class="stab">SOW Required</td><!--DS_MDSE_INFO.SOW_REQ_FLG-->
																				<td align="left"><ezf:inputCheckBox name="sowReqFlg_H1" ezfName="sowReqFlg_H1" value="Y" /></td>
																			</tr>
																		</table>
																	</fieldset>
<%}%>
<%if(mdseItemTpCd_H1.equals("15")){%> <!--Part Consumables-->
																	<fieldset>
																		<table cellpadding="0" width="340">
																			<col width="150"  align="left">
																			<col width="190" align="left">
																			<tr>
																				<td colspan="2" class="stab"><b>Attributes(Item Type Specific)</b></td>
																			</tr>
    <!--Part Type-->
																			<tr>
																				<td class="stab">Part Type</td>
																				<td><!--DS_MDSE_INFO.PRT_ITEM_TP_CD-->
																					<ezf:select name="prtItemTpCd_H1" ezfName="prtItemTpCd_H1" ezfBlank="1" ezfCodeName="prtItemTpCd_L1" ezfDispName="prtItemTpDescTxt_L1" otherAttr=" style=\"width:150px\""/>
																				</td>
																			</tr>
    <!--Parts Dropship Disabled-->
																			<tr>
																				<td class="stab">Parts Dropship Disabled</td><!--DS_MDSE_INFO.PRT_DROP_SHIP_DSBL_FLG-->
																				<td><ezf:inputCheckBox name="xxChkBox_PD" ezfName="xxChkBox_PD" value="Y" /></td>
																			</tr>
    <!--Parts Yield - Outputs-->
																			<tr>
																				<td class="stab">Parts Yield - Outputs</td>
																				<!--DS_MDSE_INFO.PRT_YIELD_OTPT_QTY-->
																				<td><ezf:inputText name="prtYieldOtptQty_H1" ezfName="prtYieldOtptQty_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Parts Yield - Days-->
																			<tr>
																				<td class="stab">Parts Yield - Days</td>
																				<!--DS_MDSE_INFO.PRT_YIELD_DAYS_AOT-->
																				<td><ezf:inputText name="prtYieldDaysAot_H1" ezfName="prtYieldDaysAot_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																			</tr>
    <!--Parts Survey Required-->
																			<tr>
																				<td class="stab">Parts Survey Required</td><!--DS_MDSE_INFO.PRT_SRVY_REQ_FLG-->
																				<td><ezf:inputCheckBox name="prtSrvyReqFlg_H1" ezfName="prtSrvyReqFlg_H1" value="Y" /></td>
																			</tr>
																		</table>
																	</fieldset>
<%}%>

																</td>
															</tr>
														</table>
													</td>
													<td valign="top" width="385"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;
																</td>
																<td>
																	<fieldset style="width:360px">
																		<table cellpadding="0">
																			<tr>
																				<td colspan="2" class="stab"><b>Safety</b></td>
																			</tr>
																			<tr>
																				<td class="stab">Hazardous Material</td><!--MDSE_SFTY_INFO.HAZ_MAT_FLG-->
																				<td>
																					<ezf:inputCheckBox name="xxChkBox_HM" ezfName="xxChkBox_HM" value="Y" onClick="sendServer('Click_HazMat_Checkbox')" />
																					<ezf:inputHidden name="xxRecHistCratTs_G9" ezfName="xxRecHistCratTs_G9" />
																					<ezf:inputHidden name="xxRecHistCratByNm_G9" ezfName="xxRecHistCratByNm_G9" />
																					<ezf:inputHidden name="xxRecHistUpdTs_G9" ezfName="xxRecHistUpdTs_G9" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_G9" ezfName="xxRecHistUpdByNm_G9" />
																					<ezf:inputHidden name="xxRecHistTblNm_G9" ezfName="xxRecHistTblNm_G9" />
																				</td>
																			</tr>
																			<tr>
																				<td class="stab">Hazardous Number</td><!--MDSE_SFTY_INFO.HAZ_MAT_CD-->
																				<td>
																					<ezf:select name="hazMatCd_H1" ezfName="hazMatCd_H1" ezfBlank="1" ezfCodeName="hazMatCd_L1" ezfDispName="hazMatDescTxt_L1" otherEvent1=" onchange=\"sendServer('Change_HazMatCd_Pulldown')\"" otherAttr=" style=\"width:215px\""/>
																					<ezf:inputHidden name="xxRecHistCratTs_GA" ezfName="xxRecHistCratTs_GA" />
																					<ezf:inputHidden name="xxRecHistCratByNm_GA" ezfName="xxRecHistCratByNm_GA" />
																					<ezf:inputHidden name="xxRecHistUpdTs_GA" ezfName="xxRecHistUpdTs_GA" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_GA" ezfName="xxRecHistUpdByNm_GA" />
																					<ezf:inputHidden name="xxRecHistTblNm_GA" ezfName="xxRecHistTblNm_GA" />
																				</td>
																			</tr>
																			<tr>
																				<td class="stab">Hazardous Class</td><!--HAZ_MAT.HAZ_CLS_NM-->
																				<td>
																					<ezf:inputText name="hazClsNm_H1" ezfName="hazClsNm_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/>
																					<ezf:inputHidden name="xxRecHistCratTs_GB" ezfName="xxRecHistCratTs_GB" />
																					<ezf:inputHidden name="xxRecHistCratByNm_GB" ezfName="xxRecHistCratByNm_GB" />
																					<ezf:inputHidden name="xxRecHistUpdTs_GB" ezfName="xxRecHistUpdTs_GB" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_GB" ezfName="xxRecHistUpdByNm_GB" />
																					<ezf:inputHidden name="xxRecHistTblNm_GB" ezfName="xxRecHistTblNm_GB" />
																				</td>
																			</tr>
																			<tr>
																				<td class="stab">Hazardous Shipping Label</td><!--HAZ_MAT.HAZ_PRP_SHIP_NM-->
																				<td>
																					<ezf:inputText name="hazPrpShipNm_H1" ezfName="hazPrpShipNm_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"30\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
																					<ezf:inputHidden name="xxRecHistCratTs_GC" ezfName="xxRecHistCratTs_GC" />
																					<ezf:inputHidden name="xxRecHistCratByNm_GC" ezfName="xxRecHistCratByNm_GC" />
																					<ezf:inputHidden name="xxRecHistUpdTs_GC" ezfName="xxRecHistUpdTs_GC" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_GC" ezfName="xxRecHistUpdByNm_GC" />
																					<ezf:inputHidden name="xxRecHistTblNm_GC" ezfName="xxRecHistTblNm_GC" />
																				</td>
																			</tr>
																			<tr>
																				<td class="stab">Hazardous ID</td><!--HAZ_MAT.HAZ_ID_NUM-->
																				<td>
																					<ezf:inputText name="hazIdNum_H1" ezfName="hazIdNum_H1" value="XXXXXXX" otherAttr=" size=\"7\" maxlength=\"7\" tabindex=\"-1\" ezftoupper=\"\""/>
																					<ezf:inputHidden name="xxRecHistCratTs_GD" ezfName="xxRecHistCratTs_GD" />
																					<ezf:inputHidden name="xxRecHistCratByNm_GD" ezfName="xxRecHistCratByNm_GD" />
																					<ezf:inputHidden name="xxRecHistUpdTs_GD" ezfName="xxRecHistUpdTs_GD" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_GD" ezfName="xxRecHistUpdByNm_GD" />
																					<ezf:inputHidden name="xxRecHistTblNm_GD" ezfName="xxRecHistTblNm_GD" />
																				</td>
																			</tr>
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_08" ezfEmulateBtn="1" ezfGuard="Mnf_Ctry_Link" otherAttr=" tabindex=\"-1\"">Manufactured Country</ezf:anchor></td><!--MDSE_SFTY_INFO.MADE_IN_CTRY_CD-->
																				<td>
                                                                                    <ezf:inputText name="madeInCtryCd_H1" ezfName="madeInCtryCd_H1" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
                                                                                    <ezf:inputText name="ctryNm_MI" ezfName="ctryNm_MI" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/>
                                                                                    <ezf:inputHidden name="xxRecHistCratTs_GE" ezfName="xxRecHistCratTs_GE" />
																					<ezf:inputHidden name="xxRecHistCratByNm_GE" ezfName="xxRecHistCratByNm_GE" />
																					<ezf:inputHidden name="xxRecHistUpdTs_GE" ezfName="xxRecHistUpdTs_GE" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_GE" ezfName="xxRecHistUpdByNm_GE" />
																					<ezf:inputHidden name="xxRecHistTblNm_GE" ezfName="xxRecHistTblNm_GE" />
																				</td>
																			</tr>
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_09" ezfEmulateBtn="1" ezfGuard="Asm_Ctry_Link" otherAttr=" tabindex=\"-1\"">Assembled Country</ezf:anchor></td><!--MDSE_SFTY_INFO.ASM_IN_CTRY_CD-->
																				<td>
                                                                                    <ezf:inputText name="asmInCtryCd_H1" ezfName="asmInCtryCd_H1" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
                                                                                    <ezf:inputText name="ctryNm_AI" ezfName="ctryNm_AI" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/>
                                                                                    <ezf:inputHidden name="xxRecHistCratTs_GF" ezfName="xxRecHistCratTs_GF" />
																					<ezf:inputHidden name="xxRecHistCratByNm_GF" ezfName="xxRecHistCratByNm_GF" />
																					<ezf:inputHidden name="xxRecHistUpdTs_GF" ezfName="xxRecHistUpdTs_GF" />
																					<ezf:inputHidden name="xxRecHistUpdByNm_GF" ezfName="xxRecHistUpdByNm_GF" />
																					<ezf:inputHidden name="xxRecHistTblNm_GF" ezfName="xxRecHistTblNm_GF" />
																				</td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
														</table>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;
																</td>
																<td>
																	<fieldset style="width:360px">
																		<table cellpadding="0">
																			<tr>
																				<td class="stab"><b>Logistics</b></td>
																			<tr>
																			</tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_27" ezfEmulateBtn="1" ezfGuard="Frt_Cls_Link" otherAttr=" tabindex=\"-1\"">Freight Class</ezf:anchor></td><!--MDSE.FRT_CLS_CD-->
																				<td>
																				    <ezf:inputText name="frtClsCd_H1" ezfName="frtClsCd_H1" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="frtClsDescTxt_H1" ezfName="frtClsDescTxt_H1" value="XX" otherAttr=" size=\"26\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
																				</td>
																			</tr>
																			</tr>
																				<td class="stab">NMFC Item#</td>
																				<td class="stab">
																				    <ezf:inputText name="nmfcItemNum_H1" ezfName="nmfcItemNum_H1" value="XXX" otherAttr=" size=\"7\" maxlength=\"7\" tabindex=\"-1\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="nmfcItemSubNum_H1" ezfName="nmfcItemSubNum_H1" value="XX" otherAttr=" size=\"5\" maxlength=\"5\" tabindex=\"-1\" ezftoupper=\"\""/>&nbsp;NMFC Class#&nbsp;<ezf:inputText name="nmfcClsNum_H1" ezfName="nmfcClsNum_H1" value="XXX" otherAttr=" size=\"8\" maxlength=\"8\" tabindex=\"-1\" ezftoupper=\"\""/>
																				</td>
																			</tr>
																			</tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_32" ezfEmulateBtn="1" ezfGuard="Trf_Link" otherAttr=" tabindex=\"-1\"">Tariff</ezf:anchor></td><!--MDSE.TRF_CD-->
																				<td>
																				    <ezf:inputText name="trfCd_H1" ezfName="trfCd_H1" value="XXX" otherAttr=" size=\"11\" maxlength=\"11\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="trfDescTxt_H1" ezfName="trfDescTxt_H1" value="XX" otherAttr=" size=\"26\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
																				</td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>

										</div>
									</div>	

<%}%>
<%-- General End --%>

<%-- Inventory Start --%>
<%if(InventoryFlg.equals("Y") && xxDplyTab_H1.equals("Inventory")){%>
										<script type="text/javascript">
<%if(GeneralFlg.equals("Y")){%>
											document.getElementById( "General" ).className = "pTab_OFF";
<%}%>
<%if(InventoryFlg.equals("Y")){%>
											document.getElementById( "Inventory" ).className = "pTab_ON";
<%}%>
<%if(AccountingFlg.equals("Y")){%>
											document.getElementById( "Accounting" ).className = "pTab_OFF";
<%}%>
<%if(FieldServiceFlg.equals("Y")){%>
											document.getElementById( "FieldService" ).className = "pTab_OFF";
<%}%>
<%if(OrderProcessingFlg.equals("Y")){%>
											document.getElementById( "OrderProcessing" ).className = "pTab_OFF";
<%}%>
<%if(SupercessionFlg.equals("Y")){%>
											document.getElementById( "Supercession" ).className = "pTab_OFF";
<%}%>
<%if(CustomerRefFlg.equals("Y")){%>
											document.getElementById( "CustomerRef" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
											document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
										</script>
											
									<div class="pTab_BODY_In">
									    <div style="OVERFLOW-Y: scroll; HEIGHT: 285px" >

											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top" width="450"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;&nbsp;
																</td>
																<td>
																	<fieldset>
																	<table cellpadding="0">
																		<tr>
																			<td class="stab"><b>Inventory Attributes</b></td>
																			<td>&nbsp;</td>
																		</tr>
																		<tr>
																			<td class="stab">Inventory Trackable</td><!--MDSE.INVTY_CTRL_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_IT" ezfName="xxChkBox_IT" value="Y" /></td>
																		</tr>
																		<tr>
																			<td class="stab">Internal Item</td><!--MDSE.ITNL_ITEM_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_II" ezfName="xxChkBox_II" value="Y" /></td>
																		</tr>
																		<tr>
																			<td class="stab">RMA Allowed</td><!--MDSE.RMA_REQ_TP_CD-->
																			<td><ezf:inputCheckBox name="xxChkBox_RA" ezfName="xxChkBox_RA" value="Y" /></td>
																		</tr>
																		<tr>
																			<td class="stab">RMA Inspection Required</td><!--DS_MDSE_INFO.RMA_INSP_REQ_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_RI" ezfName="xxChkBox_RI" value="Y" /></td>
																		</tr>
																		<tr>
																			<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_10" ezfEmulateBtn="1" ezfGuard="Def_Src_Wh_Link" otherAttr=" tabindex=\"-1\"">Default Source Warehouse</ezf:anchor></td>
																			<td><!--DS_MDSE_INFO.DEF_SRC_WH_CD-->
                                                                                <ezf:inputText name="defSrcWhCd_H1" ezfName="defSrcWhCd_H1" value="XXX" otherAttr=" size=\"5\" maxlength=\"20\" ezftoupper=\"\""/>
                                                                                <ezf:inputText name="locNm_DW" ezfName="locNm_DW" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"25\" maxlength=\"60\" tabindex=\"-1\" ezftoupper=\"\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_11" ezfEmulateBtn="1" ezfGuard="Def_Src_Sub_Wh_Link" otherAttr=" tabindex=\"-1\"">Default Source Sub Warehouse</ezf:anchor></td>
																			<td><!--DS_MDSE_INFO.DEF_SRC_SUB_WH_CD-->
                                                                                <ezf:inputText name="defSrcSubWhCd_H1" ezfName="defSrcSubWhCd_H1" value="XXX" otherAttr=" size=\"5\" maxlength=\"20\" ezftoupper=\"\""/>
                                                                                <ezf:inputText name="locNm_SW" ezfName="locNm_SW" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5XXXXXXXXX6" otherAttr=" size=\"25\" maxlength=\"60\" tabindex=\"-1\" ezftoupper=\"\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Default Source Type</td>
																			<td><!--DS_MDSE_INFO.PROCR_TP_CD-->
																				<ezf:select name="defSrcProcrTpCd_H1" ezfName="defSrcProcrTpCd_H1" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpDescTxt_L1" otherAttr=" style=\"width:120px\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Hard Allocation Type</td>
																			<td><!--DS_MDSE_INFO.INVTY_HARD_ALLOC_TP_CD-->
																				<ezf:select name="invtyHardAllocTpCd_H1" ezfName="invtyHardAllocTpCd_H1" ezfBlank="1" ezfCodeName="hardAllocTpCd_L1" ezfDispName="hardAllocTpDescTxt_L1" otherAttr=" style=\"width:120px\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Drop RMA</td>
																			<td>
																				<ezf:select name="dropRtrnVndCd_H1" ezfName="dropRtrnVndCd_H1" ezfBlank="1" ezfCodeName="dropRtrnVndCd_L1" ezfDispName="dropRtrnVndDescTxt_L1" otherAttr=" style=\"width:120px\""/>
																			</td>
																		</tr>
																		<tr><td colspan="2">&nbsp;</td></tr>
																		<tr><td colspan="2">&nbsp;</td></tr>
																		<tr><td colspan="2">&nbsp;</td></tr>
																		<tr><td colspan="2">&nbsp;</td></tr>
																		<tr><td colspan="2">&nbsp;</td></tr>
																		<tr><td colspan="2">&nbsp;</td></tr>
																		<tr><td colspan="2">&nbsp;</td></tr>
																	</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
													<td valign="top" width="570"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;&nbsp;
																</td>
																<td>
																	<fieldset>
																		<table cellpadding="0">
																			<tr>
																				<td colspan="2" class="stab"><b>Parts Return Control</b></td>
																			</tr>
																			<tr>
																				<td class="stab">Return Controlled</td>
																				<td><!--DS_MDSE_INFO.RTRN_REQ_PRT_FLG-->
																					<ezf:inputCheckBox name="xxChkBox_RR" ezfName="xxChkBox_RR" value="Y" onClick="sendServer('Click_PartsCtrl_Checkbox')" />
																				</td>
																			</tr>
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_30" ezfEmulateBtn="1" ezfGuard="Rtrn_Ctrl_Tp_Link" otherAttr=" tabindex=\"-1\"">Return Control Type</ezf:anchor></td>
																				<td><!--DS_MDSE_INFO.RTRN_CTRL_TP_CD-->
                                                                                    <ezf:inputHidden name="rtrnCtrlTpCd_H1" ezfName="rtrnCtrlTpCd_H1" />
                                                                                    <ezf:inputText name="rtrnCtrlTpNm_H1" ezfName="rtrnCtrlTpNm_H1" otherAttr=" size=\"20\" maxlength=\"50\""/>
																					<!--
																					<select style="width:120px" name="rtrnCtrlTpCd_H1" ezfname="rtrnCtrlTpCd_H1" ezflist="rtrnCtrlTpCd_L1,rtrnCtrlTpNm_L1,99, ,blank" onchange="sendServer('Change_rtrnCtrlTpCd_Pulldown')"/>
																					<option>R:Controlled Part</option>
																					<option>V:Venlo Return Part</option>
																					<option>RR:Refurbished by 3rd Party</option>
																					</select>
																					-->
																				</td>
																			</tr>
																			<tr>
																				<td class="stab">Return Sub Warehouse</td>
																				<td><!--DS_MDSE_INFO.RTRN_DSPL_TP-->
																					<ezf:select name="rtrnDsplTpCd_H1" ezfName="rtrnDsplTpCd_H1" ezfBlank="1" ezfCodeName="rtrnDsplTpCd_L1" ezfDispName="rtrnDsplTpDescTxt_L1" />
																				</td>
																			</tr>
																			<tr>
																				<td class="stab">Return Vendor/Site</td>
																				<td><!--DS_MDSE_INFO.RTRN_TO_VND_CD-->
                                                                                    <ezf:inputText name="locNm_P1" ezfName="locNm_P1" otherAttr=" size=\"20\" maxlength=\"60\""/>&nbsp;<ezf:inputText name="locNm_V1" ezfName="locNm_V1" otherAttr=" size=\"20\" maxlength=\"60\""/>
                                                                                    <ezf:inputHidden name="rtrnToVndCd_H1" ezfName="rtrnToVndCd_H1" />
																					<ezf:inputHidden name="rtrnToPrntVndCd_H1" ezfName="rtrnToPrntVndCd_H1" />
																					<!--
																					<select style="width:120px" name="rtrnToVndCd_H1" ezfname="rtrnToVndCd_H1" ezflist="rtrnToVndCd_L1,vndNm_L1,99, ,blank">
																					<option>DI:DISPOSAL</option>
																					<option>CI:CVI</option>
																					<option>VE:VENLO</option>
																					</select>
																					-->
																				</td>
																			</tr>
																			<tr>
																				<td class="stab">Return Warehouse</td>
																				<td><!--DS_MDSE_INFO.RTRN_WH_CD-->
                                                                                    <ezf:inputHidden name="rtrnWhCd_H1" ezfName="rtrnWhCd_H1" />
                                                                                    <ezf:inputText name="locNm_W1" ezfName="locNm_W1" otherAttr=" size=\"20\" maxlength=\"60\""/>
																				</td>
																			</tr>
																		</table>
																	</fieldset>
                                                                    <br>
																	<fieldset>
																		<table cellpadding="0">
																			<tr><td class="stab"><b>Serial Number Control</b></td>
																			</tr>
																			<tr>
																				<td class="stab"><ezf:inputRadio name="xxRadioBtn_SS" ezfName="xxRadioBtn_SS" value="0" />&nbsp;Not Controlled
                                                                                                 <ezf:inputRadio name="xxRadioBtn_SS" ezfName="xxRadioBtn_SS" value="1" />&nbsp;On Receipt&Shipment
                                                                                                 <ezf:inputRadio name="xxRadioBtn_SS" ezfName="xxRadioBtn_SS" value="2" />&nbsp;On Shipment
																								 &nbsp;&nbsp;<ezf:inputButton name="InsertRow_SER_NUM" value="Insert Row" htmlClass="pBtn6" /><ezf:inputButton name="DeleteRow_SER_NUM" value="Delete Row" htmlClass="pBtn6" />
                                                                                </td><!--MDSE.SHPG_SER_TAKE_FLG-->
																			</tr>
																			<tr>
																				<td colspan="2">
																				<div id="topTBL" style="overflow-x:none; height:20; width:535; overflow-y:none;">
																					<table border="1" cellpadding="1" cellspacing="0">
																						<col width="25"  align="center">
																						<col width="255" align="center">
																						<col width="255" align="center">

																						<tr>
																							<td class="pClothBs">&nbsp;&nbsp;</td>
																							<td class="pClothBs">Serial From</td>
																							<td class="pClothBs">Serial To</td>
																						</tr>
																					</table>
																				</div>
																				<div id="bottomTBL" style="word-break:break-all; width:552; height:75; overflow-y:scroll;">
																					<table border="1" cellpadding="1" cellspacing="0" id="A">
																						<col width="24" align="center">
																						<col width="255" align="center">
																						<col width="255" align="center">
																						<col width="25"  align="center">

																						<ezf:row ezfHyo="A">
																						<tr>
																							<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																							<!--MDSE_SER_NUM_RNG.FROM_SER_NUM-->
																							<td><ezf:inputText name="fromSerNum_A1" ezfName="fromSerNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
																							<!--MDSE_SER_NUM_RNG.THRU_SER_NUM-->
																							<td><ezf:inputText name="thruSerNum_A1" ezfName="thruSerNum_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
																							<td class="pAuditInfo">
																								<ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
																							</td>
																						</tr>
																						</ezf:row>
																						<ezf:skip>
																						<tr class="pEvenNumberBGcolor">
																							<td><input type="checkbox" name="" value=""></td>
																							<td><input type="text" name="" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																							<td><input type="text" name="thruSerNum" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																						</tr>
																						<tr class="pEvenNumberBGcolor">
																							<td><input type="checkbox" name="" value=""></td>
																							<td><input type="text" name="" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																							<td><input type="text" name="thruSerNum" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																						</tr>
																						<tr class="pEvenNumberBGcolor">
																							<td><input type="checkbox" name="" value=""></td>
																							<td><input type="text" name="" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																							<td><input type="text" name="thruSerNum" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																						</tr>
																						<tr class="pEvenNumberBGcolor">
																							<td><input type="checkbox" name="" value=""></td>
																							<td><input type="text" name="" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																							<td><input type="text" name="thruSerNum" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																						</tr>
																						<tr class="pEvenNumberBGcolor">
																							<td><input type="checkbox" name="" value=""></td>
																							<td><input type="text" name="" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																							<td><input type="text" name="thruSerNum" size="30" maxlength="30" value="1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW"></td>
																						</tr>
																						</ezf:skip>
																					</table>
																				</div>
																				</td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</div>

<%}%>
<%-- Inventory End --%>

<%-- Accounting Start --%>
<%if(AccountingFlg.equals("Y") && xxDplyTab_H1.equals("Accounting")){%>
										<script type="text/javascript">
<%if(GeneralFlg.equals("Y")){%>
											document.getElementById( "General" ).className = "pTab_OFF";
<%}%>
<%if(InventoryFlg.equals("Y")){%>
											document.getElementById( "Inventory" ).className = "pTab_OFF";
<%}%>
<%if(AccountingFlg.equals("Y")){%>
											document.getElementById( "Accounting" ).className = "pTab_ON";
<%}%>
<%if(FieldServiceFlg.equals("Y")){%>
											document.getElementById( "FieldService" ).className = "pTab_OFF";
<%}%>
<%if(OrderProcessingFlg.equals("Y")){%>
											document.getElementById( "OrderProcessing" ).className = "pTab_OFF";
<%}%>
<%if(SupercessionFlg.equals("Y")){%>
											document.getElementById( "Supercession" ).className = "pTab_OFF";
<%}%>
<%if(CustomerRefFlg.equals("Y")){%>
											document.getElementById( "CustomerRef" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
											document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
										</script>
									
									<div class="pTab_BODY_In">
									    <div style="OVERFLOW-Y: scroll; HEIGHT: 285px" >


											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top" width="375"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;&nbsp;
																</td>
																<td>
																	<fieldset>
																	<table cellpadding="0">
																		<tr>
																			<td class="stab"><b>Costing</b></td>
																			<td>&nbsp;</td>
																		</tr>
																		<tr>
																			<td class="stab">Standard Cost</td><!--MDSE.THIS_MTH_TOT_STD_COST_AMT-->
																			<td><ezf:inputText name="thisMthTotStdCostAmt_H1" ezfName="thisMthTotStdCostAmt_H1" value="999,999,999,999,999.9999" otherAttr=" size=\"23\" maxlength=\"25\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab">Standard Cost Date</td><!--DS_MDSE_INFO.MDSE_CST_UPD_DT-->
																			<td>
																				<ezf:inputText name="mdseCstUpdDt_H1" ezfName="mdseCstUpdDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCstUpdDt_H1', 4);" >
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Standard Cost Previous</td><!--MDSE.LAST_MTH_TOT_STD_COST_AMT-->
																			<td><ezf:inputText name="lastMthTotStdCostAmt_H1" ezfName="lastMthTotStdCostAmt_H1" value="999,999,999,999,999.9999" otherAttr=" size=\"23\" maxlength=\"25\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																		</tr>
																		<tr>
																			<td class="stab">ARV Cost</td><!--DS_MDSE_INFO.ASSET_RECOV_COST_AMT-->
																			<td><ezf:inputText name="assetRecovCostAmt_H1" ezfName="assetRecovCostAmt_H1" value="9,999,999,999" otherAttr=" size=\"23\" maxlength=\"25\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab">ARV Cost Date</td><!--DS_MDSE_INFO.ASSET_RECOV_CST_EFF_FROM_DT-->
																			<td>
																				<ezf:inputText name="assetRecovCostAmtUpdDt_H1" ezfName="assetRecovCostAmtUpdDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('assetRecovCostAmtUpdDt_H1', 4);" >
                                                                            </td>
																		</tr>
																		<tr>
																			<td class="stab">ARV Cost Previous</td><!--DS_MDSE_INFO.ASSET_RECOV_COST_AMT-->
																			<td><ezf:inputText name="prevAssetRecovCostAmt_H1" ezfName="prevAssetRecovCostAmt_H1" value="9,999,999,999" otherAttr=" size=\"23\" maxlength=\"25\" ezftoupper=\"\" tabindex=\"-1\""/></td>
																		</tr>
																		<tr>
																			<td class="stab">Purchase Price</td><!--DS_MDSE_INFO.ORIG_FOB_AMT-->
																			<td><ezf:inputText name="origFobAmt_H1" ezfName="origFobAmt_H1" value="999,999,999,999,999.9999" otherAttr=" size=\"23\" maxlength=\"25\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab">Purchase Price Date</td><!--DS_MDSE_INFO.ORIG_FOB_AMT_UPD_DT-->
																			<td>
																				<ezf:inputText name="origFobAmtUpdDt_H1" ezfName="origFobAmtUpdDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('origFobAmtUpdDt_H1', 4);" >
																			</td>
																		</tr>
																		<tr>
																			<td colspan="2"><ezf:inputButton name="ViewCostHistory" value="View Cost History" htmlClass="pBtn10" /></td><!---->
																		</tr>
																	</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
													<td valign="top" width="460"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;
																</td>
																<td>
																	<fieldset>
																		<table cellpadding="0">
																			<tr>
																				<td class="stab"><b>Account Numbers</b></td>
																				<td>&nbsp;</td>
																			</tr>
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_12" ezfEmulateBtn="1" ezfGuard="Revenue_Link" otherAttr=" tabindex=\"-1\"">Revenue</ezf:anchor></td><!--DS_MDSE_INFO.REV_COA_ACCT_CD-->
																				<td>
																					<ezf:inputText name="revCoaAcctCd_H1" ezfName="revCoaAcctCd_H1" value="XXXXXXX8" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
																					<ezf:inputText name="coaAcctNm_RV" ezfName="coaAcctNm_RV" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"30\" maxlength=\"240\" ezftoupper=\"\" tabindex=\"-1\""/>
																				</td>
																			</tr>
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_13" ezfEmulateBtn="1" ezfGuard="COG_Link" otherAttr=" tabindex=\"-1\"">Cost of Goods</ezf:anchor></td><!--DS_MDSE_INFO.COGS_COA_ACCT_CD-->
																				<td>
																					<ezf:inputText name="cogsCoaAcctCd_H1" ezfName="cogsCoaAcctCd_H1" value="XXXXXXX8" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
																					<ezf:inputText name="coaAcctNm_CG" ezfName="coaAcctNm_CG" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"30\" maxlength=\"240\" ezftoupper=\"\" tabindex=\"-1\""/>
																				</td>
																			</tr>
																			<tr>
																				<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_14" ezfEmulateBtn="1" ezfGuard="Expense_Link" otherAttr=" tabindex=\"-1\"">Expense</ezf:anchor></td><!--DS_MDSE_INFO.EXP_COA_ACCT_CD-->
																				<td>
																					<ezf:inputText name="expCoaAcctCd_H1" ezfName="expCoaAcctCd_H1" value="XXXXXXX8" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
																					<ezf:inputText name="coaAcctNm_EX" ezfName="coaAcctNm_EX" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4" otherAttr=" size=\"30\" maxlength=\"240\" ezftoupper=\"\" tabindex=\"-1\""/>
																				</td>
																			</tr>
																			<!--<tr>-->
																			<!--	<td class="stab"><a href="#" onclick="sendServer('Accrual_Link')" ezfname="xxLinkProt_15" tabindex="-1">Accrual</a></td><!--DS_MDSE_INFO.ACRL_COA_ACCT_CD-->
																			<!--	<td>-->
																			<!--		<input type="text" name="acrlCoaAcctCd_H1" value="XXXXXXX8"  size="8" maxlength="8" ezftoupper="" ezfname="acrlCoaAcctCd_H1">-->
																			<!--		<input type="text" name="coaAcctNm_AC" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4"  size="30" maxlength="240" ezftoupper="" ezfname="coaAcctNm_AC" disabled tabindex="-1">-->
																			<!--	</td>-->
																			<!--</tr>-->
																			<tr>
																				<td class="stab">Line of Business</td><!--DS_MDSE_INFO.LINE_BIZ_TP_CD-->
																				<td>
																					<ezf:select name="lineBizTpCd_H1" ezfName="lineBizTpCd_H1" ezfBlank="1" ezfCodeName="lineBizTpCd_L1" ezfDispName="lineBizTpDescTxt_L1" otherAttr=" style=\"width:65px\""/>
																				</td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>

													<td valign="top" width="340"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;
																</td>
																<td>
																	<fieldset>
																		<table cellpadding="0">
																			<tr>
																				<td colspan="2" class="stab"><b>Accounting Rules</b></td>
																			</tr>
																			<tr>
																				<td class="stab">Invoiceable</td><!--DS_MDSE_INFO.INV_PSBL_FLG-->
																				<td><ezf:inputCheckBox name="xxChkBox_IP" ezfName="xxChkBox_IP" value="Y" /></td>
																			</tr>
																			<tr>
																				<td class="stab">Accounting Rules</td><!--DS_MDSE_INFO.DFRD_ACCTG_RULE_CD-->
																				<td>
																					<ezf:select name="dfrdAcctgRuleCd_H1" ezfName="dfrdAcctgRuleCd_H1" ezfBlank="1" ezfCodeName="dfrdAcctgRuleCd_L1" ezfDispName="dfrdAcctgRuleDescTxt_L1" otherAttr=" style=\"width:120px\""/>
																				</td>
																			</tr>
																			<tr>
																				<td class="stab">Invoicing Rules</td><!--DS_MDSE_INFO.DFRD_INV_RULE_CD-->
																				<td>
																					<ezf:select name="dfrdInvRuleCd_H1" ezfName="dfrdInvRuleCd_H1" ezfBlank="1" ezfCodeName="dfrdInvRuleCd_L1" ezfDispName="dfrdInvRuleDescTxt_L1" otherAttr=" style=\"width:120px\""/>
																				</td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>

										</div>
									</div>

<%}%>
<%-- Accounting End --%>

<%-- FieldService Start --%>
<%if(FieldServiceFlg.equals("Y") && xxDplyTab_H1.equals("FieldService")){%>
										<script type="text/javascript">
<%if(GeneralFlg.equals("Y")){%>
											document.getElementById( "General" ).className = "pTab_OFF";
<%}%>
<%if(InventoryFlg.equals("Y")){%>
											document.getElementById( "Inventory" ).className = "pTab_OFF";
<%}%>
<%if(AccountingFlg.equals("Y")){%>
											document.getElementById( "Accounting" ).className = "pTab_OFF";
<%}%>
<%if(FieldServiceFlg.equals("Y")){%>
											document.getElementById( "FieldService" ).className = "pTab_ON";
<%}%>
<%if(OrderProcessingFlg.equals("Y")){%>
											document.getElementById( "OrderProcessing" ).className = "pTab_OFF";
<%}%>
<%if(SupercessionFlg.equals("Y")){%>
											document.getElementById( "Supercession" ).className = "pTab_OFF";
<%}%>
<%if(CustomerRefFlg.equals("Y")){%>
											document.getElementById( "CustomerRef" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
											document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
										</script>
									
									<div class="pTab_BODY_In">
									    <div style="OVERFLOW-Y: scroll; HEIGHT: 285px" >



											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top" width="1000"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;&nbsp;
																</td>
																<td>
																	<fieldset>
																	<table cellpadding="0">
																		<tr>
																			<td class="stab"><b>Service Attributes</b></td>
																			<td>&nbsp;</td>
																		</tr>
																		<tr>
																			<td class="stab">Warranty</td><!--DS_MDSE_INFO.SVC_WTY_TP_CD-->
																			<td>
																					<ezf:select name="svcWtyTpCd_H1" ezfName="svcWtyTpCd_H1" ezfBlank="1" ezfCodeName="svcWtyTpCd_L1" ezfDispName="svcWtyTpDescTxt_L1" otherAttr=" style=\"width:160px\""/>
																			</td>
																			<td>&nbsp;&nbsp;</td>
																			<td class="stab">Installation Rules</td><!--MDSE.SVC_ISTL_RULE_NUM-->
																			<td>&nbsp;</td>
																			<td>
																					<ezf:select name="svcIstlRuleNum_H1" ezfName="svcIstlRuleNum_H1" ezfBlank="1" ezfCodeName="svcIstlRuleNum_L1" ezfDispName="svcIstlRuleNm_L1" otherAttr=" style=\"width:210px\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Warranty Period( days )</td><!--DS_MDSE_INFO.WTY_DAYS_AOT-->
																			<td><ezf:inputText name="wtyDaysAot_H1" ezfName="wtyDaysAot_H1" value="9999" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
																			<td>&nbsp;&nbsp;</td>
																			<td class="stab">Install Call Group</td><!--MDSE.SVC_ISTL_CALL_GRP_NUM-->
																			<td>&nbsp;</td>
																			<td>
																					<ezf:select name="svcIstlCallGrpNum_H1" ezfName="svcIstlCallGrpNum_H1" ezfBlank="1" ezfCodeName="svcIstlCallGrpNum_L1" ezfDispName="svcIstlCallGrpNm_L1" otherAttr=" style=\"width:210px\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Metered Machine</td><!--DS_MDSE_INFO.MTR_MACH_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_MM" ezfName="xxChkBox_MM" value="Y" /></td>
																			<td>&nbsp;&nbsp;</td>
																			<td class="stab">Deinstallation Rules</td><!--MDSE.SVC_DEINS_RULE_NUM-->
																			<td>&nbsp;</td>
																			<td>
																					<ezf:select name="svcDeinsRuleNum_H1" ezfName="svcDeinsRuleNum_H1" ezfBlank="1" ezfCodeName="svcDeinsRuleNum_L1" ezfDispName="xxSvcDeinsRuleNm_L1" otherAttr=" style=\"width:210px\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Install Base Trackable</td><!--DS_MDSE_INFO.INSTL_BASE_CTRL_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_IB" ezfName="xxChkBox_IB" value="Y" /></td>
																			<td>&nbsp;&nbsp;</td>
																			<td class="stab">Deinstall Call Group</td><!--MDSE.SVC_DEINS_CALL_GRP_NUM-->
																			<td>&nbsp;</td>
																			<td>
																					<ezf:select name="svcDeinsCallGrpNum_H1" ezfName="svcDeinsCallGrpNum_H1" ezfBlank="1" ezfCodeName="svcDeinsCallGrpNum_L1" ezfDispName="xxSvcDeinsCallGrpNm_L1" otherAttr=" style=\"width:210px\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Service Call Enabled</td><!--DS_MDSE_INFO.SVC_CALL_ENBL_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_SC" ezfName="xxChkBox_SC" value="Y" /></td>
																		</tr>
																		<tr>
																			<td class="stab">Imageware Remote Enabled</td><!--DS_MDSE_INFO.IWR_ENBL_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_IR" ezfName="xxChkBox_IR" value="Y" /></td>
																		</tr>
																		<tr>
																			<td class="stab">Imageware Remote Model</td><!--DS_MDSE_INFO.IWR_MDL_CD-->
																			<td>
																				<ezf:inputText name="iwrMdlCd_H1" ezfName="iwrMdlCd_H1" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"16\" maxlength=\"100\" ezftoupper=\"\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_17" ezfEmulateBtn="1" ezfGuard="Imageware_Item_Link" otherAttr=" tabindex=\"-1\"">Imageware Remote Item</ezf:anchor></td><!--DS_MDSE_INFO.IWR_MDSE_CD-->
																			<td>
																				<ezf:inputText name="iwrMdseCd_H1" ezfName="iwrMdseCd_H1" value="XXXXXXXXX1XXXXX6" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
																				<ezf:inputText name="mdseDescShortTxt_IW" ezfName="mdseDescShortTxt_IW" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\" tabindex=\"-1\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab">Item Billing Type</td><!--DS_MDSE_INFO.MDSE_ITEM_BILL_TP_CD-->
																			<td>
																					<ezf:select name="mdseItemBillTpCd_H1" ezfName="mdseItemBillTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemBillTpCd_L1" ezfDispName="mdseItemBillTpDescTxt_L1" otherAttr=" style=\"width:160px\""/>
																			</td>
																		</tr>
																	</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td>&nbsp;&nbsp;&nbsp;<ezf:inputButton name="ViewServiceModel" value="View Service Model" htmlClass="pBtn10" /></td>
												</tr>
											</table>

										</div>
									</div>
<%}%>
<%-- FieldService End --%>

<%-- OrderProcessing Start --%>
<%if(OrderProcessingFlg.equals("Y") && xxDplyTab_H1.equals("OrderProcessing")){%>
										<script type="text/javascript">
<%if(GeneralFlg.equals("Y")){%>
											document.getElementById( "General" ).className = "pTab_OFF";
<%}%>
<%if(InventoryFlg.equals("Y")){%>
											document.getElementById( "Inventory" ).className = "pTab_OFF";
<%}%>
<%if(AccountingFlg.equals("Y")){%>
											document.getElementById( "Accounting" ).className = "pTab_OFF";
<%}%>
<%if(FieldServiceFlg.equals("Y")){%>
											document.getElementById( "FieldService" ).className = "pTab_OFF";
<%}%>
<%if(OrderProcessingFlg.equals("Y")){%>
											document.getElementById( "OrderProcessing" ).className = "pTab_ON";
<%}%>
<%if(SupercessionFlg.equals("Y")){%>
											document.getElementById( "Supercession" ).className = "pTab_OFF";
<%}%>
<%if(CustomerRefFlg.equals("Y")){%>
											document.getElementById( "CustomerRef" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
											document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
										</script>
									
									<div class="pTab_BODY_In">
									    <div style="OVERFLOW-Y: scroll; HEIGHT: 285px" >



											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top" width="320"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;&nbsp;
																</td>
																<td>
																	<fieldset>
																	<table cellpadding="0">
																		<tr>
																			<td class="stab"><b>Order Processing</b></td>
																			<td>&nbsp;</td>
																		</tr>
																		<tr>
																			<td class="stab">Customer Orderable</td><!--DS_MDSE_INFO.CUST_ORD_ENBL_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_CO" ezfName="xxChkBox_CO" value="Y" /></td>
																		</tr>
																		<tr>
																			<td class="stab">Minimum Order Quantity</td><!--MDSE.CPO_MIN_ORD_QTY-->
																			<td><ezf:inputText name="cpoMinOrdQty_H1" ezfName="cpoMinOrdQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab">Maximum Order Quantity</td><!--MDSE.CPO_MAX_ORD_QTY-->
																			<td><ezf:inputText name="cpoMaxOrdQty_H1" ezfName="cpoMaxOrdQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab">Order Increments</td><!--MDSE.CPO_INCR_ORD_QTY-->
																			<td><ezf:inputText name="cpoIncrOrdQty_H1" ezfName="cpoIncrOrdQty_H1" value="9,999,999,999" otherAttr=" size=\"13\" maxlength=\"13\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab">Remanufactured Item Exists</td><!--DS_MDSE_INFO.RE_MNF_ITEM_EXST_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_IE" ezfName="xxChkBox_IE" value="Y" /></td>
																		</tr>
																		<tr>
																			<td class="stab">Requires Config Center</td><!--DS_MDSE_INFO.CONFIG_FLG-->
																			<td><ezf:inputCheckBox name="xxChkBox_CF" ezfName="xxChkBox_CF" value="Y" /></td>
																		</tr>
																	</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>

										</div>
									</div>
<%}%>
<%-- OrderProcessing End --%>

<%-- Supercession Start --%>
<%if(SupercessionFlg.equals("Y") && xxDplyTab_H1.equals("Supercession")){%>
										<script type="text/javascript">
<%if(GeneralFlg.equals("Y")){%>
											document.getElementById( "General" ).className = "pTab_OFF";
<%}%>
<%if(InventoryFlg.equals("Y")){%>
											document.getElementById( "Inventory" ).className = "pTab_OFF";
<%}%>
<%if(AccountingFlg.equals("Y")){%>
											document.getElementById( "Accounting" ).className = "pTab_OFF";
<%}%>
<%if(FieldServiceFlg.equals("Y")){%>
											document.getElementById( "FieldService" ).className = "pTab_OFF";
<%}%>
<%if(OrderProcessingFlg.equals("Y")){%>
											document.getElementById( "OrderProcessing" ).className = "pTab_OFF";
<%}%>
<%if(SupercessionFlg.equals("Y")){%>
											document.getElementById( "Supercession" ).className = "pTab_ON";
<%}%>
<%if(CustomerRefFlg.equals("Y")){%>
											document.getElementById( "CustomerRef" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
											document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
										</script>
									
									<div class="pTab_BODY_In">
									    <div style="OVERFLOW-Y: scroll; HEIGHT: 285px" >

											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top" width="490"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;&nbsp;
																</td>
																<td>
																	<fieldset>
																		<table cellpadding="0">
																			<tr>
																				<td colspan="2" class="stab"><b>Supersession</b></td>
																			</tr>
																			<tr>
																				<td colspan="2"><!---->
																				<div id="topTBL" style="overflow-x:none; height:20; width:485; overflow-y:none;">
																					<table border="1" cellpadding="1" cellspacing="0">
																						<col width="25"  align="center">
																						<col width="160" align="center">
																						<col width="180" align="center">
																						<col width="100" align="center">
																						<tr>
																							<td class="pClothBs">&nbsp;&nbsp;</td>
																							<td class="pClothBs">Superseded By</td>
																							<td class="pClothBs">Description</td>
																							<td class="pClothBs">Date</td>
																						</tr>
																					</table>
																				</div>
																				<div id="bottomTBL" style="word-break:break-all; width:502; height:190; overflow-y:auto;">
																					<table border="1" cellpadding="1" cellspacing="0" id="C">
																						<col width="25" align="center">
																						<col width="160" align="center">
																						<col width="180" align="center">
																						<col width="100" align="center">

																						<ezf:row ezfHyo="C">
																						<tr>
																							<td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																							<!--SUPD_TO_MDSE_CD-->
																							<td><ezf:inputText name="supdToMdseCd_C1" ezfName="supdToMdseCd_C1" value="1100B001" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
																							<ezf:inputButton name="LineItem_SUPD" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn1" /></td>
																							<!--MDSE_NM-->
																							<td><ezf:inputText name="mdseDescShortTxt_C1" ezfName="mdseDescShortTxt_C1" value="IPC 700 ENGINE" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"250\" ezftoupper=\"\""/></td>
																							<td><!--SUPD_CRAT_DT-->
																								<ezf:inputText name="supdCratDt_C1" ezfName="supdCratDt_C1" value="mm/dd/yyyy" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdCratDt_C1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																							<td class="pAuditInfo">
																								<ezf:inputHidden name="xxRecHistCratTs_C1" ezfName="xxRecHistCratTs_C1" ezfHyo="C" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistCratByNm_C1" ezfName="xxRecHistCratByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistUpdTs_C1" ezfName="xxRecHistUpdTs_C1" ezfHyo="C" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistUpdByNm_C1" ezfName="xxRecHistUpdByNm_C1" ezfHyo="C" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistTblNm_C1" ezfName="xxRecHistTblNm_C1" ezfHyo="C" ezfArrayNo="0" />
																							</td>
																						</tr>
																						</ezf:row>
																						<ezf:skip>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" value="Y" ezfhyo="C"></td>
																							<!--SUPD_TO_MDSE_CD-->
																							<td><input type="text" name="supdToMdseCd_C1" ezfname="supdToMdseCd_C1" size="13" maxlength="16" value="1100B001" ezfhyo="C" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_SUPD" onclick="sendServer(this)" ezfhyo="C"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_C1" ezfname="mdseDescShortTxt_C1" size="18" maxlength="250" value="IPC 700 ENGINE" ezfhyo="C" ezftoupper disabled></td>
																							<td><!--SUPD_CRAT_DT-->
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="supdCratDt_C1" ezfname="supdCratDt_C1" ezfhyo="C" ezftoupper >
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdCratDt_C1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" value="Y" ezfhyo="C"></td>
																							<!--SUPD_TO_MDSE_CD-->
																							<td><input type="text" name="supdToMdseCd_C1" ezfname="supdToMdseCd_C1" size="13" maxlength="16" value="1100B001" ezfhyo="C" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_SUPD" onclick="sendServer(this)" ezfhyo="C"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_C1" ezfname="mdseDescShortTxt_C1" size="18" maxlength="250" value="IPC 700 ENGINE" ezfhyo="C" ezftoupper disabled></td>
																							<td><!--SUPD_CRAT_DT-->
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="supdCratDt_C1" ezfname="supdCratDt_C1" ezfhyo="C" ezftoupper >
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdCratDt_C1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" value="Y" ezfhyo="C"></td>
																							<!--SUPD_TO_MDSE_CD-->
																							<td><input type="text" name="supdToMdseCd_C1" ezfname="supdToMdseCd_C1" size="13" maxlength="16" value="1100B001" ezfhyo="C" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_SUPD" onclick="sendServer(this)" ezfhyo="C"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_C1" ezfname="mdseDescShortTxt_C1" size="18" maxlength="250" value="IPC 700 ENGINE" ezfhyo="C" ezftoupper disabled></td>
																							<td><!--SUPD_CRAT_DT-->
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="supdCratDt_C1" ezfname="supdCratDt_C1" ezfhyo="C" ezftoupper >
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdCratDt_C1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" value="Y" ezfhyo="C"></td>
																							<!--SUPD_TO_MDSE_CD-->
																							<td><input type="text" name="supdToMdseCd_C1" ezfname="supdToMdseCd_C1" size="13" maxlength="16" value="1100B001" ezfhyo="C" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_SUPD" onclick="sendServer(this)" ezfhyo="C"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_C1" ezfname="mdseDescShortTxt_C1" size="18" maxlength="250" value="IPC 700 ENGINE" ezfhyo="C" ezftoupper disabled></td>
																							<td><!--SUPD_CRAT_DT-->
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="supdCratDt_C1" ezfname="supdCratDt_C1" ezfhyo="C" ezftoupper >
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdCratDt_C1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" value="Y" ezfhyo="C"></td>
																							<!--SUPD_TO_MDSE_CD-->
																							<td><input type="text" name="supdToMdseCd_C1" ezfname="supdToMdseCd_C1" size="13" maxlength="16" value="1100B001" ezfhyo="C" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_SUPD" onclick="sendServer(this)" ezfhyo="C"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_C1" ezfname="mdseDescShortTxt_C1" size="18" maxlength="250" value="IPC 700 ENGINE" ezfhyo="C" ezftoupper disabled></td>
																							<td><!--SUPD_CRAT_DT-->
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="supdCratDt_C1" ezfname="supdCratDt_C1" ezfhyo="C" ezftoupper >
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('supdCratDt_C1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																						</tr>
																						</ezf:skip>
																					</table>
																				</div>
																				<div>
																					<table>
																						<tr>
																							<td class="stab"><ezf:inputButton name="InsertRow_SUPD" value="Insert Row" htmlClass="pBtn6" /><ezf:inputButton name="DeleteRow_SUPD" value="Delete Row" htmlClass="pBtn6" /></td>
																						</tr>
																					</table>
																				</div>
																				</td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
													<td valign="top" width="490"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;&nbsp;
																</td>
																<td>
																	<fieldset>
																		<table cellpadding="0">
																			<tr>
																				<td colspan="2" class="stab"><b>Relationships</b></td>
																			</tr>
																			<tr>
																				<td colspan="2"><!---->
																				<div id="topTBL" style="overflow-x:none; height:20; width:505; overflow-y:none;">
																					<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
																						<col width="25"  align="center">
																						<col width="140" align="center">
																						<col width="150" align="center">
																						<col width="210" align="center">

																						<tr>
																							<td class="pClothBs">&nbsp;&nbsp;</td>
																							<td class="pClothBs">Relationship</td>
																							<td class="pClothBs">Related Item</td>
																							<td class="pClothBs">Description</td>
																						</tr>
																					</table>
																				</div>
																				<div id="bottomTBL" style="word-break:break-all; width:522; height:190; overflow-y:auto;">
																					<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="D">
																						<col width="25" align="center">
																						<col width="140" align="center">
																						<col width="150" align="center">
																						<col width="210" align="center">

																						<ezf:row ezfHyo="D">
																						<tr>
																							<td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
																							<td><!--MDSE_ITEM_RELN_TP_CD-->
																								<ezf:select name="mdseItemRelnTpCd_D1" ezfName="mdseItemRelnTpCd_D1" ezfHyo="D" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mdseItemRelnTpCd_DL" ezfDispName="mdseItemRelnTpDescTxt_DL" />
																							</td>
																							<!--RELN_MDSE_CD-->
																							<td><ezf:inputText name="relnMdseCd_D1" ezfName="relnMdseCd_D1" value="1100B001" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
																							<ezf:inputButton name="LineItem_RELN_MDSE" value="..." ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn1" /></td>
																							<!--MDSE_NM-->
																							<td><ezf:inputText name="mdseDescShortTxt_D1" ezfName="mdseDescShortTxt_D1" value="IPC 700 ENGINE" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"250\" ezftoupper=\"\""/></td>
																							<td class="pAuditInfo">
																								<ezf:inputHidden name="xxRecHistCratTs_D1" ezfName="xxRecHistCratTs_D1" ezfHyo="D" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistCratByNm_D1" ezfName="xxRecHistCratByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistUpdTs_D1" ezfName="xxRecHistUpdTs_D1" ezfHyo="D" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistUpdByNm_D1" ezfName="xxRecHistUpdByNm_D1" ezfHyo="D" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistTblNm_D1" ezfName="xxRecHistTblNm_D1" ezfHyo="D" ezfArrayNo="0" />
																							</td>
																						</tr>
																						</ezf:row>
																						<ezf:skip>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_D1" ezfname="xxChkBox_D1" value="Y" ezfhyo="D"></td>
																							<td><!--MDSE_ITEM_RELN_TP_CD-->
																								<select name="mdseItemRelnTpCd_D1" ezfname="mdseItemRelnTpCd_D1" ezflist="mdseItemRelnTpCd_DL,mdseItemRelnTpDescTxt_DL,99, ,blank" ezfhyo="D">
																								<option selected>COMPATIBLE</option>
																								<option>REFURBISHED</option>
																								</select>
																							</td>
																							<!--RELN_MDSE_CD-->
																							<td><input type="text" name="relnMdseCd_D1" ezfname="relnMdseCd_D1" size="13" maxlength="16" value="1100B001" ezfhyo="D" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_RELN_MDSE" onclick="sendServer(this)" ezfhyo="D"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_D1" ezfname="mdseDescShortTxt_D1" size="27" maxlength="250" value="IPC 700 ENGINE" ezfhyo="D" ezftoupper disabled></td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_D1" ezfname="xxChkBox_D1" value="Y" ezfhyo="D"></td>
																							<td><!--MDSE_ITEM_RELN_TP_CD-->
																								<select name="mdseItemRelnTpCd_D1" ezfname="mdseItemRelnTpCd_D1" ezflist="mdseItemRelnTpCd_DL,mdseItemRelnTpDescTxt_DL,99, ,blank" ezfhyo="D">
																								<option selected>COMPATIBLE</option>
																								<option>REFURBISHED</option>
																								</select>
																							</td>
																							<!--RELN_MDSE_CD-->
																							<td><input type="text" name="relnMdseCd_D1" ezfname="relnMdseCd_D1" size="13" maxlength="16" value="1100B001" ezfhyo="D" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_RELN_MDSE" onclick="sendServer(this)" ezfhyo="D"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_D1" ezfname="mdseDescShortTxt_D1" size="27" maxlength="250" value="IPC 700 ENGINE" ezfhyo="D" ezftoupper disabled></td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_D1" ezfname="xxChkBox_D1" value="Y" ezfhyo="D"></td>
																							<td><!--MDSE_ITEM_RELN_TP_CD-->
																								<select name="mdseItemRelnTpCd_D1" ezfname="mdseItemRelnTpCd_D1" ezflist="mdseItemRelnTpCd_DL,mdseItemRelnTpDescTxt_DL,99, ,blank" ezfhyo="D">
																								<option selected>COMPATIBLE</option>
																								<option>REFURBISHED</option>
																								</select>
																							</td>
																							<!--RELN_MDSE_CD-->
																							<td><input type="text" name="relnMdseCd_D1" ezfname="relnMdseCd_D1" size="13" maxlength="16" value="1100B001" ezfhyo="D" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_RELN_MDSE" onclick="sendServer(this)" ezfhyo="D"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_D1" ezfname="mdseDescShortTxt_D1" size="27" maxlength="250" value="IPC 700 ENGINE" ezfhyo="D" ezftoupper disabled></td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_D1" ezfname="xxChkBox_D1" value="Y" ezfhyo="D"></td>
																							<td><!--MDSE_ITEM_RELN_TP_CD-->
																								<select name="mdseItemRelnTpCd_D1" ezfname="mdseItemRelnTpCd_D1" ezflist="mdseItemRelnTpCd_DL,mdseItemRelnTpDescTxt_DL,99, ,blank" ezfhyo="D">
																								<option selected>COMPATIBLE</option>
																								<option>REFURBISHED</option>
																								</select>
																							</td>
																							<!--RELN_MDSE_CD-->
																							<td><input type="text" name="relnMdseCd_D1" ezfname="relnMdseCd_D1" size="13" maxlength="16" value="1100B001" ezfhyo="D" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_RELN_MDSE" onclick="sendServer(this)" ezfhyo="D"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_D1" ezfname="mdseDescShortTxt_D1" size="27" maxlength="250" value="IPC 700 ENGINE" ezfhyo="D" ezftoupper disabled></td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_D1" ezfname="xxChkBox_D1" value="Y" ezfhyo="D"></td>
																							<td><!--MDSE_ITEM_RELN_TP_CD-->
																								<select name="mdseItemRelnTpCd_D1" ezfname="mdseItemRelnTpCd_D1" ezflist="mdseItemRelnTpCd_DL,mdseItemRelnTpDescTxt_DL,99, ,blank" ezfhyo="D">
																								<option selected>COMPATIBLE</option>
																								<option>REFURBISHED</option>
																								</select>
																							</td>
																							<!--RELN_MDSE_CD-->
																							<td><input type="text" name="relnMdseCd_D1" ezfname="relnMdseCd_D1" size="13" maxlength="16" value="1100B001" ezfhyo="D" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_RELN_MDSE" onclick="sendServer(this)" ezfhyo="D"></td>
																							<!--MDSE_NM-->
																							<td><input type="text" name="mdseDescShortTxt_D1" ezfname="mdseDescShortTxt_D1" size="27" maxlength="250" value="IPC 700 ENGINE" ezfhyo="D" ezftoupper disabled></td>
																						</tr>
																						</ezf:skip>
																					</table>
																				</div>
																				<div>
																					<table>
																						<tr>
																							<td class="stab"><ezf:inputButton name="InsertRow_RELN_MDSE" value="Insert Row" htmlClass="pBtn6" /><ezf:inputButton name="DeleteRow_RELN_MDSE" value="Delete Row" htmlClass="pBtn6" /></td>
																						</tr>
																					</table>
																				</div>
																				</td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>


										</div>
									</div>
<%}%>
<%-- Supercession End --%>

<%-- CustomerRef Start --%>
<%if(CustomerRefFlg.equals("Y") && xxDplyTab_H1.equals("CustomerRef")){%>
										<script type="text/javascript">
<%if(GeneralFlg.equals("Y")){%>
											document.getElementById( "General" ).className = "pTab_OFF";
<%}%>
<%if(InventoryFlg.equals("Y")){%>
											document.getElementById( "Inventory" ).className = "pTab_OFF";
<%}%>
<%if(AccountingFlg.equals("Y")){%>
											document.getElementById( "Accounting" ).className = "pTab_OFF";
<%}%>
<%if(FieldServiceFlg.equals("Y")){%>
											document.getElementById( "FieldService" ).className = "pTab_OFF";
<%}%>
<%if(OrderProcessingFlg.equals("Y")){%>
											document.getElementById( "OrderProcessing" ).className = "pTab_OFF";
<%}%>
<%if(SupercessionFlg.equals("Y")){%>
											document.getElementById( "Supercession" ).className = "pTab_OFF";
<%}%>
<%if(CustomerRefFlg.equals("Y")){%>
											document.getElementById( "CustomerRef" ).className = "pTab_ON";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
											document.getElementById( "Taxing" ).className = "pTab_OFF";
<%}%>
										</script>
									
									<div class="pTab_BODY_In">
									    <div style="OVERFLOW-Y: scroll; HEIGHT: 285px" >


											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top" width="1000"><br>
														<table cellpadding="0">
															<tr>
																<td>&nbsp;&nbsp;
																</td>
																<td>
																	<fieldset>
																		<table cellpadding="0">
																			<tr>
																				<td colspan="2" class="stab"><b>Customer Item References</b></td>
																			</tr>
																			<tr>
																				<td colspan="2"><!---->
																				<div id="topTBL" style="overflow-x:none; height:20; width:900; overflow-y:none;">
																					<table border="1" cellpadding="1" cellspacing="0">
																						<col width="25"  align="center">
																						<col width="140" align="center">
																						<col width="200" align="center">
																						<col width="140" align="center">
																						<col width="200" align="center">
																						<col width="100" align="center">
																						<col width="60"  align="center">

																						<tr>
																							<td class="pClothBs">&nbsp;&nbsp;</td>
																							<td class="pClothBs">Customer Number</td>
																							<td class="pClothBs">Customer Name</td>
																							<td class="pClothBs">Customer Item</td>
																							<td class="pClothBs">Description</td>
																							<td class="pClothBs">Date</td>
																							<td class="pClothBs">Enabled</td>
																						</tr>
																					</table>
																				</div>
																				<div id="bottomTBL" style="word-break:break-all; width:917; height:190; overflow-y:auto;">
																					<table border="1" cellpadding="1" cellspacing="0" id="E">
																						<col width="25" align="center">
																						<col width="140" align="center">
																						<col width="200" align="center">
																						<col width="140" align="center">
																						<col width="200" align="center">
																						<col width="100" align="center">
																						<col width="60"  align="center">

																						<ezf:row ezfHyo="E">
																						<tr>
																							<td><ezf:inputCheckBox name="xxChkBox_E1" ezfName="xxChkBox_E1" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																							<!--DS_ACCT_NUM-->
																							<td><ezf:inputText name="dsAcctNum_E1" ezfName="dsAcctNum_E1" value="100001" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/>
																							<ezf:inputButton name="LineItem_CUST" value="..." ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn1" /></td>
																							<!--DS_ACCT_NM-->
																							<td><ezf:inputText name="dsAcctNm_E1" ezfName="dsAcctNm_E1" value="AUXILLIO" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"360\" ezftoupper=\"\""/></td>
																							<!--CUST_MDSE_CD-->
																							<td><ezf:inputText name="custMdseCd_E1" ezfName="custMdseCd_E1" value="1100B001" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"16\" ezftoupper=\"\""/></td>
																							<!--CUST_MDSE_NM-->
																							<td><ezf:inputText name="custMdseNm_E1" ezfName="custMdseNm_E1" value="IPC 700 ENGINE" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"30\" ezftoupper=\"\""/></td>
																							<!--EFF_FROM_DT-->
																							<td>
																								<ezf:inputText name="effFromDt_E1" ezfName="effFromDt_E1" value="mm/dd/yyyy" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_E1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																							<!--CUST_MDSE_XREF_ENBL_FLG-->
																							<td><ezf:inputCheckBox name="xxChkBox_EN" ezfName="xxChkBox_EN" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																							<td class="pAuditInfo">
																								<ezf:inputHidden name="xxRecHistCratTs_E1" ezfName="xxRecHistCratTs_E1" ezfHyo="E" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistCratByNm_E1" ezfName="xxRecHistCratByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistUpdTs_E1" ezfName="xxRecHistUpdTs_E1" ezfHyo="E" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistUpdByNm_E1" ezfName="xxRecHistUpdByNm_E1" ezfHyo="E" ezfArrayNo="0" />
																								<ezf:inputHidden name="xxRecHistTblNm_E1" ezfName="xxRecHistTblNm_E1" ezfHyo="E" ezfArrayNo="0" />
																							</td>
																						</tr>
																						</ezf:row>
																						<ezf:skip>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_E1" ezfname="xxChkBox_E1" value="Y" ezfhyo="E"></td>
																							<!--DS_ACCT_NUM-->
																							<td><input type="text" name="dsAcctNum_E1" ezfname="dsAcctNum_E1" size="13" maxlength="16" value="100001" ezfhyo="E" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_CUST" onclick="sendServer(this)" ezfhyo="E"></td>
																							<!--DS_ACCT_NM-->
																							<td><input type="text" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" size="27" maxlength="360" value="AUXILLIO" ezfhyo="E" ezftoupper disabled></td>
																							<!--CUST_MDSE_CD-->
																							<td><input type="text" name="custMdseCd_E1" ezfname="custMdseCd_E1" size="18" maxlength="16" value="1100B001" ezfhyo="E" ezftoupper></td>
																							<!--CUST_MDSE_NM-->
																							<td><input type="text" name="custMdseNm_E1" ezfname="custMdseNm_E1" size="27" maxlength="30" value="IPC 700 ENGINE" ezfhyo="E" ezftoupper disabled></td>
																							<!--EFF_FROM_DT-->
																							<td>
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="effFromDt_E1" ezfname="effFromDt_E1" ezfhyo="E" ezftoupper>
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_E1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																							<!--CUST_MDSE_XREF_ENBL_FLG-->
																							<td><input type="checkbox" name="xxChkBox_EN" ezfname="xxChkBox_EN" value="Y" ezfhyo="E"></td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_E1" ezfname="xxChkBox_E1" value="Y" ezfhyo="E"></td>
																							<!--DS_ACCT_NUM-->
																							<td><input type="text" name="dsAcctNum_E1" ezfname="dsAcctNum_E1" size="13" maxlength="16" value="100001" ezfhyo="E" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_CUST" onclick="sendServer(this)" ezfhyo="E"></td>
																							<!--DS_ACCT_NM-->
																							<td><input type="text" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" size="27" maxlength="360" value="AUXILLIO" ezfhyo="E" ezftoupper disabled></td>
																							<!--CUST_MDSE_CD-->
																							<td><input type="text" name="custMdseCd_E1" ezfname="custMdseCd_E1" size="18" maxlength="16" value="1100B001" ezfhyo="E" ezftoupper></td>
																							<!--CUST_MDSE_NM-->
																							<td><input type="text" name="custMdseNm_E1" ezfname="custMdseNm_E1" size="27" maxlength="30" value="IPC 700 ENGINE" ezfhyo="E" ezftoupper disabled></td>
																							<!--EFF_FROM_DT-->
																							<td>
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="effFromDt_E1" ezfname="effFromDt_E1" ezfhyo="E" ezftoupper>
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_E1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																							<!--CUST_MDSE_XREF_ENBL_FLG-->
																							<td><input type="checkbox" name="xxChkBox_EN" ezfname="xxChkBox_EN" value="Y" ezfhyo="E"></td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_E1" ezfname="xxChkBox_E1" value="Y" ezfhyo="E"></td>
																							<!--DS_ACCT_NUM-->
																							<td><input type="text" name="dsAcctNum_E1" ezfname="dsAcctNum_E1" size="13" maxlength="16" value="100001" ezfhyo="E" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_CUST" onclick="sendServer(this)" ezfhyo="E"></td>
																							<!--DS_ACCT_NM-->
																							<td><input type="text" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" size="27" maxlength="360" value="AUXILLIO" ezfhyo="E" ezftoupper disabled></td>
																							<!--CUST_MDSE_CD-->
																							<td><input type="text" name="custMdseCd_E1" ezfname="custMdseCd_E1" size="18" maxlength="16" value="1100B001" ezfhyo="E" ezftoupper></td>
																							<!--CUST_MDSE_NM-->
																							<td><input type="text" name="custMdseNm_E1" ezfname="custMdseNm_E1" size="27" maxlength="30" value="IPC 700 ENGINE" ezfhyo="E" ezftoupper disabled></td>
																							<!--EFF_FROM_DT-->
																							<td>
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="effFromDt_E1" ezfname="effFromDt_E1" ezfhyo="E" ezftoupper>
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_E1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																							<!--CUST_MDSE_XREF_ENBL_FLG-->
																							<td><input type="checkbox" name="xxChkBox_EN" ezfname="xxChkBox_EN" value="Y" ezfhyo="E"></td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_E1" ezfname="xxChkBox_E1" value="Y" ezfhyo="E"></td>
																							<!--DS_ACCT_NUM-->
																							<td><input type="text" name="dsAcctNum_E1" ezfname="dsAcctNum_E1" size="13" maxlength="16" value="100001" ezfhyo="E" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_CUST" onclick="sendServer(this)" ezfhyo="E"></td>
																							<!--DS_ACCT_NM-->
																							<td><input type="text" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" size="27" maxlength="360" value="AUXILLIO" ezfhyo="E" ezftoupper disabled></td>
																							<!--CUST_MDSE_CD-->
																							<td><input type="text" name="custMdseCd_E1" ezfname="custMdseCd_E1" size="18" maxlength="16" value="1100B001" ezfhyo="E" ezftoupper></td>
																							<!--CUST_MDSE_NM-->
																							<td><input type="text" name="custMdseNm_E1" ezfname="custMdseNm_E1" size="27" maxlength="30" value="IPC 700 ENGINE" ezfhyo="E" ezftoupper disabled></td>
																							<!--EFF_FROM_DT-->
																							<td>
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="effFromDt_E1" ezfname="effFromDt_E1" ezfhyo="E" ezftoupper>
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_E1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																							<!--CUST_MDSE_XREF_ENBL_FLG-->
																							<td><input type="checkbox" name="xxChkBox_EN" ezfname="xxChkBox_EN" value="Y" ezfhyo="E"></td>
																						</tr>
																						<tr>
																							<td><input type="checkbox" name="xxChkBox_E1" ezfname="xxChkBox_E1" value="Y" ezfhyo="E"></td>
																							<!--DS_ACCT_NUM-->
																							<td><input type="text" name="dsAcctNum_E1" ezfname="dsAcctNum_E1" size="13" maxlength="16" value="100001" ezfhyo="E" ezftoupper>
																							<input type="button" class="pBtn1" value="..." name="LineItem_CUST" onclick="sendServer(this)" ezfhyo="E"></td>
																							<!--DS_ACCT_NM-->
																							<td><input type="text" name="dsAcctNm_E1" ezfname="dsAcctNm_E1" size="27" maxlength="360" value="AUXILLIO" ezfhyo="E" ezftoupper disabled></td>
																							<!--CUST_MDSE_CD-->
																							<td><input type="text" name="custMdseCd_E1" ezfname="custMdseCd_E1" size="18" maxlength="16" value="1100B001" ezfhyo="E" ezftoupper></td>
																							<!--CUST_MDSE_NM-->
																							<td><input type="text" name="custMdseNm_E1" ezfname="custMdseNm_E1" size="27" maxlength="30" value="IPC 700 ENGINE" ezfhyo="E" ezftoupper disabled></td>
																							<!--EFF_FROM_DT-->
																							<td>
																								<input type="text" class="pHsu" size="10" maxlength="10" value="mm/dd/yyyy" name="effFromDt_E1" ezfname="effFromDt_E1" ezfhyo="E" ezftoupper>
																								<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_E1', 4, '{EZF_ROW_NUMBER}');" >
																							</td>
																							<!--CUST_MDSE_XREF_ENBL_FLG-->
																							<td><input type="checkbox" name="xxChkBox_EN" ezfname="xxChkBox_EN" value="Y" ezfhyo="E"></td>
																						</tr>
																						</ezf:skip>
																					</table>
																				</div>
																				<div>
																					<table>
																						<tr>
																							<td class="stab"><ezf:inputButton name="InsertRow_CUST_REF" value="Insert Row" htmlClass="pBtn6" /><ezf:inputButton name="DeleteRow_CUST_REF" value="Delete Row" htmlClass="pBtn6" /></td>
																						</tr>
																					</table>
																				</div>
																				</td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>


										</div>
									</div>

<%}%>
<%-- CustomerRef End --%>

<%-- Taxing Start --%>
<%if(TaxingFlg.equals("Y") && xxDplyTab_H1.equals("Taxing")){%>
										<script type="text/javascript">
<%if(GeneralFlg.equals("Y")){%>
											document.getElementById( "General" ).className = "pTab_OFF";
<%}%>
<%if(InventoryFlg.equals("Y")){%>
											document.getElementById( "Inventory" ).className = "pTab_OFF";
<%}%>
<%if(AccountingFlg.equals("Y")){%>
											document.getElementById( "Accounting" ).className = "pTab_OFF";
<%}%>
<%if(FieldServiceFlg.equals("Y")){%>
											document.getElementById( "FieldService" ).className = "pTab_OFF";
<%}%>
<%if(OrderProcessingFlg.equals("Y")){%>
											document.getElementById( "OrderProcessing" ).className = "pTab_OFF";
<%}%>
<%if(SupercessionFlg.equals("Y")){%>
											document.getElementById( "Supercession" ).className = "pTab_OFF";
<%}%>
<%if(CustomerRefFlg.equals("Y")){%>
											document.getElementById( "CustomerRef" ).className = "pTab_OFF";
<%}%>
<%if(TaxingFlg.equals("Y")){%>
											document.getElementById( "Taxing" ).className = "pTab_ON";
<%}%>
										</script>
									
									<div class="pTab_BODY_In">
										<table border="0" cellpadding="1" cellspacing="0" width="98%" align="center" height="250" style="table-layout: fixed">
											<br/>
											<col width="60" valign="top">
											<col width="240" valign="top">
											<col width="500" valign="top">
											<tr>
												<td class="stab">Tax Code</td><!--DS_MDSE_INFO.TAX_EXEM_TP_CD-->
												<td>
													<ezf:select name="taxExemTpCd_H1" ezfName="taxExemTpCd_H1" ezfBlank="1" ezfCodeName="taxExemTpCd_L1" ezfDispName="taxExemTpDescTxt_L1" otherAttr=" style=\"width:120px\""/>
												</td>
												<td></td>
											</tr>
										</table>
										<br />
									</div>
<%}%>
<%-- Taxing End --%>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td class="stab" align="right">
                <ezf:anchor name="" ezfName="xxLinkProt_31" ezfEmulateBtn="1" ezfGuard="Copy_Item_Link" otherAttr=" tabindex=\"-1\"">Item Number</ezf:anchor>&nbsp;&nbsp;
				<ezf:inputText name="mdseCd_C1" ezfName="mdseCd_C1" value="XX" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>&nbsp;&nbsp;
				<ezf:inputButton name="CopyItem" value="Copy Item" htmlClass="pBtn6" />&nbsp;&nbsp;
				<ezf:inputButton name="ViewBOM" value="Bill of Material" htmlClass="pBtn6" />&nbsp;&nbsp;
				<ezf:inputButton name="Download_Item_History" value="History" htmlClass="pBtn6" />&nbsp;&nbsp;
				<ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn6" />&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</center>

	<jsp:include page="../wfcomp/S21WfProcessStatusPageInvoker.jsp">
		<jsp:param name="processCodes" value='DMAP0010'/>
		<jsp:param name="displayMode" value='static' />
		<jsp:param name="windowWidth" value="1000px" />
		<jsp:param name="windowHeight" value="600px" />
		<jsp:param name="documentId" value='<%=databean.getWfDocumentId()%>' />
	</jsp:include>

	<jsp:include page="../wfcomp/S21WfWorkListSetPageInvoker.jsp">
		<jsp:param name="processCodes" value='<%=databean.getWfWorklistProcessCodes()%>' />
		<jsp:param name="taskCodes" value='<%=databean.getWfWorklistTaskCodes()%>' />
		<jsp:param name="displayColumn" value="Task Name~wf:taskName~center~" />
		<jsp:param name="displayColumn" value="Merchandise Code~wf:documentId~center~" />
		<jsp:param name="displayColumn" value="Requested By~wf:requestedBy~center~" />
		<jsp:param name="displayColumn" value="Task Started~wf:taskStartDate~center~MM/dd/yyyy HH:mm" />
		<jsp:param name="taskDurationColors" value="s1=720,s2=360,s3=120" />
		<jsp:param name="toDoListDisplayMode" value="static" />
		<jsp:param name="toDoListHeight" value="645px" />
		<jsp:param name="canDoListDisplayMode" value="hidden" />
		<jsp:param name="canDoListHeight" value="200px" />
		<jsp:param name="windowWidth" value="1000px" />
		<jsp:param name="windowHeight" value="680px" />
		<jsp:param name="transitionNameOnSelect" value="SelectWorkItem" />
	</jsp:include>

<jsp:include page="../wfcomp/S21WfProcessStatusSearchPageInvoker.jsp">
	<jsp:param name="processCodes" value='DMAP0010'/>
	<jsp:param name="displayMode" value='static' />
	<jsp:param name="displayStatusColumn" value='true' />
	<jsp:param name="windowWidth" value="1000px" />
	<jsp:param name="windowHeight" value="600px" />
	<jsp:param name="documentId" value='<%=databean.getWfDocumentId()%>' />
	<jsp:param name="parentDocumentId" value='<%=databean.getWfParentDocumentId()%>' />
	<jsp:param name="displayColumn" value="MDSE CD~wf:documentId~left~" />
	<jsp:param name="displayColumn" value="Create Date~wf:createDate~center~MM/dd/yyyy HH:mm:ss" />
	<jsp:param name="displayColumn" value="Status~wf:status~left~" />
</jsp:include>

<script type="text/javascript">
var before = document.forms["mainForm"].elements["mdseItemTpCd_H1"].selectedIndex;
function sendCheck(obj) {
  if (before != 0) {
    if(window.confirm('Changing the Item Type will remove the Item Specific Attributes.')) {
      return true;
    } else {
      obj.selectedIndex = before;
      return false;
    }
  } else {
      return true;
  }
}
function confirmItemTemplateName() {
  var before = document.forms["mainForm"].elements["mdseCratTmplNm_H2"].value;
  //var screenModeP = document.forms["mainForm"].elements["xxModeCd_H1"].value;
  //if (screenModeP == "" || screenModeP == null) {
    var nameP = window.prompt("Please enter Item Template Name", before);
    if (nameP != null){
      document.forms["mainForm"].elements["mdseCratTmplNm_BK"].value = nameP;
      return true;
    }else{
      return false;
    }
  //}
}
function onFocusEvent(item){
  if(item.value.length === 5){
    item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
  }
}
function onBlurEvent(item){
  if(item.value.length === 1){
    item.value = '0' + item.value.charAt(0) + ':00';
  }else if(item.value.length === 2){
    item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
  }else if(item.value.length === 3){
    item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
  }else if(item.value.length === 4){
    item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
  }else if(item.value.length === 5){
    item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
  }
}
</script>


<%-- **** Task specific area ends here **** --%>
