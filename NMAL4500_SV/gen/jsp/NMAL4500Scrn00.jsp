<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160728160136 --%>
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
			<input type="hidden" name="pageID" value="NMAL4500Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Carrier New/Edit Information">

			<center>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ###TAB - HEAD --%>
							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
						
							<%-- ######################################## HEADER ######################################## --%>
							<%-- ###TAB - HEAD --%>
							<div class="pTab_BODY">
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="5">
									<col width="203">
									<col width="5">
									<col width="40">
									<col width="5">
									<col width="586">
									<col width="10">
									<tr>
										<td>&nbsp;</td>
									    <td class="stab"><ezf:anchor name="xxLinkProt_01" ezfName="xxLinkProt_01" ezfEmulateBtn="1" ezfGuard="OpenWin_Vendor_CD" otherAttr=" tabindex=\"1\"">Carrier&nbsp;CD</ezf:anchor>
											<ezf:inputText name="vndCd_01" ezfName="vndCd_01" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"7\" ezftoupper=\"\" tabindex=\"2\""/>
										</td>
										<td>&nbsp;</td>
										<td>
											<ezf:inputButton name="Search_Vendor_CD" value=">>" htmlClass="pBtn2" otherAttr=" tabindex=\"3\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Carrier&nbsp;Name
											<ezf:inputText name="locNm_01" ezfName="locNm_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"51\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"4\""/>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="7" class="stab">DBA&nbsp;Name
											<ezf:inputText name="dbaNm_01" ezfName="dbaNm_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\" tabindex=\"5\""/>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
								<hr>
								<div height="620;" width="1005;">
									<table border="0" cellpadding="1" cellspacing="0" width="1004">
										<col width="5">
										<col width="85">
										<col width="145">
										<col width="5">
										<col width="70">
										<col width="60">
										<col width="150">
										<col width="5">
										<col width="78">
										<col width="248">
										<col width="10">
										<tr>
											<td>&nbsp;</td>
											<td class="stab" colspan="10">Carrier&nbsp;Address:</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td class="stab">Address&nbsp;Line&nbsp;1</td>
											<td>
												<ezf:inputText name="firstLineAddr_01" ezfName="firstLineAddr_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"20\" maxlength=\"35\" ezftoupper=\"\" tabindex=\"101\""/>
											</td>
											<td>&nbsp;</td>
											<%-- MOD START 2013/08/05 QC1469 --%>
											<%-- <td class="stab"><ezf:anchor name="xxLinkProt_02" ezfName="xxLinkProt_02" ezfEmulateBtn="1" ezfGuard="OpenWin_City" otherAttr=" tabindex=\"105\"">City</ezf:anchor></td> --%>
											<td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_City" >City</ezf:anchor></td>
											<%-- MOD END 2013/08/05 QC1469 --%>
											<td colspan="2">
												<ezf:inputText name="ctyAddr_01" ezfName="ctyAddr_01" value="WWWWWWWWW1WWWWWWWWW2WWWWW" otherAttr=" size=\"25\" maxlength=\"20\" ezftoupper=\"\" tabindex=\"106\""/>
											</td>
											<td>&nbsp;</td>
											<td class="stab">Reference1</td>
											<td>
												<ezf:inputText name="firstRefCmntTxt_01" ezfName="firstRefCmntTxt_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9" otherAttr=" size=\"30\" maxlength=\"90\" ezftoupper=\"\" tabindex=\"114\""/>
											</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td class="stab">Address&nbsp;Line&nbsp;2</td>
											<td>
												<ezf:inputText name="scdLineAddr_01" ezfName="scdLineAddr_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"20\" maxlength=\"35\" ezftoupper=\"\" tabindex=\"102\""/>
											</td>
											<td>&nbsp;</td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_Cnty" >County</ezf:anchor></td>
											<td colspan="2">
												<ezf:select name="cntyPk_03" ezfName="cntyPk_03" ezfBlank="1" ezfCodeName="cntyPk_01" ezfDispName="cntyNm_02" otherAttr=" tabindex=\"107\""/>
											</td>
											<td>&nbsp;</td>
											<td class="stab">Reference2</td>
											<td>
												<ezf:inputText name="scdRefCmntTxt_01" ezfName="scdRefCmntTxt_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9" otherAttr=" size=\"30\" maxlength=\"90\" ezftoupper=\"\" tabindex=\"115\""/>
											</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td class="stab">Address&nbsp;Line&nbsp;3</td>
											<td>
												<ezf:inputText name="thirdLineAddr_01" ezfName="thirdLineAddr_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"20\" maxlength=\"35\" ezftoupper=\"\" tabindex=\"103\""/>
											</td>
											<td>&nbsp;</td>
											<!-- <td class="stab"><a href="#" onclick="sendServer('OpenWin_State')" name="xxLinkProt_03" ezfname="xxLinkProt_03" tabindex="108">State</a></td> -->
											<td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_ST" >State</ezf:anchor></td>
											<td>
												<ezf:inputText name="stCd_01" ezfName="stCd_01" value="WW" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\" tabindex=\"109\""/>
											</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td class="stab">Telephone</td>
											<td>
												<ezf:inputText name="telNum_01" ezfName="telNum_01" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"15\" ezftoupper=\"\" tabindex=\"116\""/>
											</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td class="stab">Address&nbsp;Line&nbsp;4</td>
											<td>
												<ezf:inputText name="frthLineAddr_01" ezfName="frthLineAddr_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"20\" maxlength=\"35\" ezftoupper=\"\" tabindex=\"104\""/>
											</td>
											<td>&nbsp;</td>
											<td class="stab">Province</td>
											<td colspan="2">
												<ezf:inputText name="provNm_01" ezfName="provNm_01" value="WWWWWWWWW1WWWWWWWWW2WWWWW" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\" tabindex=\"110\""/>
											</td>
											<td>&nbsp;</td>
											<td class="stab">Fax</td>
											<td>
												<ezf:inputText name="faxNum_01" ezfName="faxNum_01" value="WWWWWWWWW1WWWWWWWWW2" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\" tabindex=\"117\""/>
											</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td colspan="4">&nbsp;</td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_PostCd" >Postal&nbsp;Code</ezf:anchor></td>
											<td colspan="2">
												<ezf:inputText name="postCd_01" ezfName="postCd_01" value="WWWWWWWWW1WWWWW" otherAttr=" size=\"15\" maxlength=\"10\" ezftoupper=\"\""/>
												<ezf:inputButton name="GetAddress" value="Get" htmlClass="pBtn2" />
											</td>
											<%-- MOD START 2013/08/05 QC1469 --%>
											<%-- <td> --%>
											<%-- 	<ezf:inputButton name="Get_Address" value="Get" htmlClass="pBtn3" otherAttr=" tabindex=\"112\""/> --%>
											<%-- </td> --%>
											<!-- <td>&nbsp;</td> -->
											<%-- MOD END 2013/08/05 QC1469 --%>
											<td>&nbsp;</td>
											<td class="stab">Attention&nbsp;Name</td>
											<td>
												<ezf:inputText name="attnNm_01" ezfName="attnNm_01" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7WWWWWWWWW8WWWWWWWWW9WWWWWWWW10" otherAttr=" ezftoupper=\"\" size=\"30\" maxlength=\"100\" tabindex=\"118\""/>
											</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td colspan="4">&nbsp;</td>
											<td class="stab">Country</td>
											<td colspan="2">
												<ezf:select name="ctryCd_03" ezfName="ctryCd_03" ezfBlank="1" ezfCodeName="ctryCd_01" ezfDispName="ctryNm_02" otherAttr=" tabindex=\"113\""/>
											</td>
											<td colspan="4">&nbsp;</td>
										</tr>
									</table>
									<table border="0" cellpadding="1" cellspacing="0">
										<tr valign="top">
											<td>
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="5">
													<col width="90">
													<col width="5">
													<col width="16">
													<col width="30">
													<col width="5">
													<col width="400">
													<col width="">
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td class="stab">Carrier&nbsp;Type</td>
														<td>
															<ezf:select name="carrTpCd_03" ezfName="carrTpCd_03" ezfBlank="1" ezfCodeName="carrTpCd_01" ezfDispName="carrTpDescTxt_02" otherAttr=" tabindex=\"221\""/>
														</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td></td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td>&nbsp;</td>
														<td></td>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td></td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td></td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td class="stab"></td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td>&nbsp;</td>
														<td class="stab"></td>
														<td>&nbsp;</td>
														<td class="stab"></td>
							                                                        <td></td>
							                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="5" rowspan="2">
                                                            <table border="0" cellpadding="1" cellspacing="0">
                                                                <col >
                                                                <col width="210">
                                                                <col >
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td class="stab"></td>
                                                                    <td class="stab">
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td class="stab" colspan="2"></td>
                                                                </tr>
                                                            </table>
                                                            <table border="0" cellpadding="1" cellspacing="0">
                                                                <col >
                                                                <col width="210">
                                                                <col >
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td class="stab"></td>
                                                                    <td class="stab"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td class="stab" colspan="2"></td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                        <td class="stab"></td>
                                                        <td>
                                                        </td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="stab" colspan="2" valign="top"></td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td class="stab"></td>
                                                        <td>&nbsp;</td>
                                                        <td></td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                    <td>&nbsp;</td>
                                                    <td class="stab"></td>
                                                    <td>&nbsp;</td>
                                                    <td></td>
                                                    <td class="stab" colspan="3"></td>
                                                    <td>&nbsp;</td>
                                                    </tr>
                                                </table>
											</td>
											<td>
												<table border="0">
													<tr>
														<td>&nbsp;</td>
													</tr>
												</table>
												<div id="topTBL" style="overflow-x:hidden; width:380; overflow-y:none; height:18;">
													<table border="1">
															<col width="26" align="left">
															<col width="354" align="left">
														<tr>
															<td class="pClothBs" align="center">&nbsp;</td>
															<td class="pClothBs" align="center">Vendor&nbsp;Type</td>
														</tr>
													</table>
												</div>
												<div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; width:396; height:290" onscroll="synchroBottomScroll();">
													<table border="1" width="380">
															<col width="24" align="left">
															<col width="356" align="left">
														<ezf:row ezfHyo="A">
														<tr>
															<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"301\""/></td>
															<td><ezf:label name="vndTpNm_A1" ezfName="vndTpNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														</tr>
														</ezf:row>
														<ezf:skip>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
															<tr class="pEvenNumberBGcolor">
																<td><input type="checkbox" value="1"></td>
																<td><label ezfout>WWWWWWWWW1WWWWWWWWW2W</label></td>
															</tr>
														</ezf:skip>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroBottomScroll() {
		var bottomTBL = document.getElementById( 'bottomTBL' );
		var topTBL    = document.getElementById( 'topTBL'    );
		topTBL.scrollLeft = bottomTBL.scrollLeft;
	}
</script>


<%-- **** Task specific area ends here **** --%>
