<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160701130353 --%>
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
			<input type="hidden" name="pageID" value="NMAL3200Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Marketing Data Analysis">

<center>
    <table height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
        <tbody>
            <tr>
                <td>
                    <%-- ********************** --%>
                    <%-- *** Upper Tab Area *** --%>
                    <%-- ********************** --%>
                    <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                    <ezf:skip>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
                            <tr>
                                <td width="1070px" height="28px" valign="bottom">
                                    <table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
                                        <tr title="Marketing Data Analysis">
                                            <td width="107px" align="center" class="same">Mrkt Data</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </ezf:skip>

                    <div class="pTab_BODY">
                        <!-- ################################################## Header - [START] ################################################## -->
                        <!-- ################################################## Header - [E N D] ################################################## -->
						<%-- #### TAB - HEAD #### --%>
						<div class="pTab_HEAD">
							<ul>
								<table border="0" cellpadding="0" cellspacing="0" width="900px">
									<tr>
										<td  class="stab" width="96%">
											<li id="Upload" title="Upload" class="pTab_ON">
												<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_Upload" >Upload</ezf:anchor>
											</li>
											<li id="Download" title="Download" class="pTab_OFF">
												<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TAB_Download" >Download</ezf:anchor>
											</li>
										</td>
									</tr>
								</table>
							</ul>
						</div>
						<div class="pTab_BODY_In">
							<div style="height: 530px" >
								<c:choose>
									<%-- ########## Upload Tab [START] ########## --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Upload'}">
										<div id="TabContent-Upload">
											<script type="text/javascript">
											document.getElementById("Upload").className = "pTab_ON";
											document.getElementById("Download").className = "pTab_OFF";
											</script>
											<table border="0" cellpadding="0" cellspacing="0" style="margin-top:3px;margin-left:10px;">
												<col width="700">
												<col width="60">
												<tr>
													<td style="vertical-align: top;">
														<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
															<col width="125">
															<col width="100">
															<col width="70">
															<!-- 1 -->
															<tr style="height: 24px;">
																<td class="stab">
																	<ezf:anchor name="xxLinkAncr_UE" ezfName="xxLinkAncr_UE" ezfEmulateBtn="1" ezfGuard="OpenWin_MktFldMapU" >Use Existing Mapping</ezf:anchor>
																</td>
																<td>
																	<ezf:inputText name="mktgFldMapNm_DB" ezfName="mktgFldMapNm_DB" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/>
																</td>
																<td style="text-align:right;">
																	<ezf:inputButton name="Search_MktFldMap" value="Search" htmlClass="pBtn5" />
																</td>
															</tr>
															<tr style="height: 24px;">
																<td class="stab">New  Mapping Name</td>
																<td><ezf:inputText name="mktgFldMapNm_SC" ezfName="mktgFldMapNm_SC" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
															</tr>
														</table>
														<br>
														<table style="table-layout:fixed;" style="margin-right:20px" >
															<colgroup>
																<col align="left" width="125" />
														  	</colgroup>
															<tbody>
														  		<tr>
															  		<td class="stab"><label>Table Name Column</label></td>
														  		</tr>
															</tbody>
														</table>
														<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" style="margin-right:20px" >
															<colgroup>
																<col align="center" width="125" />
																<col align="center" width="375" />
														  	</colgroup>
															<tbody>
														  		<tr height="28">
															  		<td class="pClothBs">Table Column Name</td>
															  		<td class="pClothBs">File Column Name</td>
														  		</tr>
															</tbody>
														</table>
														<div  id="rightTbl" style="width:500px; height:250px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
															<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
																<colgroup>
																	<col align="left" width="125" />
																	<col align="left" width="375" />
																</colgroup>
																<tbody>
																	<tr height="28">
																		<td class="stab">
																			<label>Customer Name</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="dsAcctNmColDfnNm" ezfName="dsAcctNmColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Address 1</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="firstLineAddrColDfnNm" ezfName="firstLineAddrColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Address 2</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="scdLineAddrColDfnNm" ezfName="scdLineAddrColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Address 3</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="thirdLineAddrColDfnNm" ezfName="thirdLineAddrColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Address 4</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="frthLineAddrColDfnNm" ezfName="frthLineAddrColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>City</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="ctyAddrColDfnNm" ezfName="ctyAddrColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>County</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="cntyPkColDfnNm" ezfName="cntyPkColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>State</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="stCdColDfnNm" ezfName="stCdColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Postal Code</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="postCdColDfnNm" ezfName="postCdColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Country</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="ctryCdColDfnNm" ezfName="ctryCdColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Duns Number</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="dunsNumColDfnNm" ezfName="dunsNumColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>GLN Number</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="glnColDfnNm" ezfName="glnColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>HIN Number</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="hinColDfnNm" ezfName="hinColDfnNm" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 1</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_01" ezfName="attrbTxtColDfnNm_01" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 2</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_02" ezfName="attrbTxtColDfnNm_02" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 3</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_03" ezfName="attrbTxtColDfnNm_03" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 4</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_04" ezfName="attrbTxtColDfnNm_04" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 5</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_05" ezfName="attrbTxtColDfnNm_05" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 6</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_06" ezfName="attrbTxtColDfnNm_06" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 7</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_07" ezfName="attrbTxtColDfnNm_07" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 8</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_08" ezfName="attrbTxtColDfnNm_08" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 9</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_09" ezfName="attrbTxtColDfnNm_09" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 10</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_10" ezfName="attrbTxtColDfnNm_10" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 11</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_11" ezfName="attrbTxtColDfnNm_11" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 12</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_12" ezfName="attrbTxtColDfnNm_12" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 13</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_13" ezfName="attrbTxtColDfnNm_13" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 14</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_14" ezfName="attrbTxtColDfnNm_14" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 15</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_15" ezfName="attrbTxtColDfnNm_15" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 16</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_16" ezfName="attrbTxtColDfnNm_16" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 17</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_17" ezfName="attrbTxtColDfnNm_17" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 18</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_18" ezfName="attrbTxtColDfnNm_18" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 19</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_19" ezfName="attrbTxtColDfnNm_19" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																	<tr height="28">
																		<td class="stab">
																			<label>Attribute 20</label>
																		</td>
																		<td class="stab">
																			<ezf:inputText name="attrbTxtColDfnNm_20" ezfName="attrbTxtColDfnNm_20" value="----+----1----+----2----+----3----+----4----+----5" otherAttr=" size=\"51\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																	</tr>
																</tbody>
															</table>
														</div>
														<br>
														<br>
														<table class="header-line" style="margin-top: 8px;margin-left:5px;width:1100px;" border="0" cellpadding="0" cellspacing="0">
															<colgroup>
																<col width="250" />
														  	</colgroup>
														  	<tbody>
														    	<tr>
																	<td class="stab">
																		<label>Contact Association & Target List</label>
																	</td>
																	<td class="stab">
																		<ezf:select name="contrAssnTrgtTpCd_SL" ezfName="contrAssnTrgtTpCd_SL" ezfBlank="1" ezfCodeName="contrAssnTrgtTpCd_PD" ezfDispName="contrAssnTrgtTpDescTxt_PD" otherAttr=" style=\"width:250px\""/>
																	</td>
																</tr>
															</tbody>
														</table>
														<br>
														<table class="header-line" style="margin-top: 8px;margin-left:5px;width:1100px;" border="0" cellpadding="0" cellspacing="0">
															<colgroup>
																<col width="330" />
																<col width="200" />
																<col width="50" />
																<col width="300" />
														  	</colgroup>
														  	<tbody>
																<tr>
																	<td class="stab">
																		<label>MATCHING CRITERIA</label>
																	</td>
																</tr>
																<tr>
																	<td class="stab">
																		<ezf:inputCheckBox name="exactCondFlg" ezfName="exactCondFlg" value="Y" otherAttr=" id=\"exactCondFlg\""/>Exact Match On Name and Address1
																	</td>
																	<td class="stab">
																		<ezf:inputCheckBox name="ovrdVldFlg" ezfName="ovrdVldFlg" value="Y" otherAttr=" id=\"ovrdVldFlg\""/>Reviewed File. Overriding validations for upload.
																	</td>
																</tr>
																<tr>
																	<td class="stab">
																		<ezf:inputCheckBox name="prtlCondFlg" ezfName="prtlCondFlg" value="Y" otherAttr=" id=\"prtlCondFlg\""/>6 Characters Match on Name and 8 Characters Match on Address1
																	</td>
																</tr>
																<tr>
																	<td class="stab">
																		<ezf:inputCheckBox name="dunsCondFlg" ezfName="dunsCondFlg" value="Y" otherAttr=" id=\"dunsCondFlg\""/>DUNS Number Match
																	</td>
																</tr>
																<tr>
																	<td class="stab">
																		<ezf:inputCheckBox name="glnCondFlg" ezfName="glnCondFlg" value="Y" otherAttr=" id=\"glnCondFlg\""/>GLN Number Match
																	</td>
																	<td class="stab">
																		<ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"40\" maxlength=\"9999\" ezftoupper=\"\""/>
																		&nbsp;&nbsp;
																		<ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" otherAttr=" id=\"btnUpload\""/>
																	</td>
																</tr>
														  	</tbody>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</c:when>
									<%-- ########## Upload Tab [END] ########## --%>

									<c:when test="${pageScope._ezddatabean.xxDplyTab == 'Download'}">
									<%-- ########## Download Tab [START] ########## --%>
										<div id="TabContent-Download">
											<script type="text/javascript">
												document.getElementById("Upload").className = "pTab_OFF";
												document.getElementById("Download").className = "pTab_ON";
											</script>
											<table class="header-line" style="margin-top: 8px;margin-left:5px;width:1100px;" border="0" cellpadding="0" cellspacing="0">
												<colgroup>
													<col width="100" />
													<col width="180" />
													<col width="130" />
													<col width="180" />
													<col width="130" />
													<col width="120" />
													<col width="180" />
													<col width="110" />
													<col width="10" />
											  	</colgroup>
											  	<tbody>
											    	<tr height="30">
														<td class="stab"
															<label>Request ID</label>
														</td>
														<td>
															<ezf:inputText name="mktgMapRqstPk_DL" ezfName="mktgMapRqstPk_DL" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/>
														</td>
														<td class="stab">
															<ezf:anchor name="xxLinkAncr_LH" ezfName="xxLinkAncr_LH" ezfEmulateBtn="1" ezfGuard="OpenWin_MktFldMapD" >Mapping Name</ezf:anchor>
														</td>
														<td>
															<ezf:inputText name="mktgFldMapNm_DL" ezfName="mktgFldMapNm_DL" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
														</td>
														<td class="stab">
															<label>Upload Error</label>
														</td>
														<td>
															<ezf:select name="upldErrFlg_SL" ezfName="upldErrFlg_SL" ezfBlank="1" ezfCodeName="upldErrFlg_UE" ezfDispName="xxFlgNm_UE" otherAttr=" style=\"width:80px\""/>
														</td>
														<td class="stab">
															<label>Request Processed</label>
														</td>
														<td>
															<ezf:select name="mktgMapRqstProcFlg_SL" ezfName="mktgMapRqstProcFlg_SL" ezfBlank="1" ezfCodeName="mktgMapRqstProcFlg_RP" ezfDispName="xxFlgNm_RP" otherAttr=" style=\"width:80px\""/>
														</td>
														<td>
															<ezf:inputButton name="Search_MktMapReq" value="Search" htmlClass="pBtn6" />
														</td>
											  		</tr>
											  	</tbody>
											</table>
											<hr>
											<table border="0" style="table-layout:fixed;width=500px;">
												<col width="80">
												<col width="425">
												<col width="610">
												<col width="10">
												<tr>
													<td></td>
													<td></td>
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
														<ezf:skip>
															<table border="0" cellpadding="0" width="100%">
																<tr>
																	<td align="left">
																		<table border="0" cellpadding="0" align="left" cellspacing="0">
																			<col>
																				<tr>
																				<td>Results 1 - 40 of 1000</td>
																				</tr>
																		</table>
																	</td>
																	<td align="right">
																		<table border="0" cellpadding="0" cellspacing="0">
																			<col width="54"  align="center">
																			<col width="40"  align="center">
																			<col width="16"  align="center">
																			<col width="40"  align="center">
																			<col width="26"  align="center">
																			<col width="10">
																			<col>
																			<col width="20">
																			<col>
																			<col width="1">
																			<col>
																			<tr>
																				<td class="stab">Showing</td>
																				<td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right"></td>
																				<td class="stab">/</td>
																				<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="25" class="pPro" style="text-align:right" readOnly></td>
																				<td class="stab">page</td>
																				<td>&nbsp;</td>
																				<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																				<td></td>
																				<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																				<td></td>
																				<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</ezf:skip>
													</td>
													<!-- Pagination & Navigation--END-->
												</tr>
											</table>
											<div id="topRightTBL" style="width:1118px; display:block; overflow-y:hidden; margin:0px;padding:0px;">
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="35px" id="AHEAD" style="margin-right:20px" >
													<colgroup>
									                  	<col align="center" width="65"><!-- Request ID -->
													  	<col align="center" width="121" /><!-- Mapping Name -->
													  	<col align="center" width="70" /><!-- Upload Error -->
														<col align="center" width="70" /><!-- Request Processed -->
														<col align="center" width="149" /><!-- Upload File Name -->
														<col align="center" width="122" /><!-- Contract Association & Target -->
														<col align="center" width="155" /><!-- Request By -->
														<col align="center" width="150" /><!-- Request On -->
														<col align="center" width="70" /><!-- Success -->
														<col align="center" width="70" /><!-- Review -->
														<col align="center" width="70" /><!-- Potential New Prospect -->
												  	</colgroup>
													<tbody>
												  		<tr height="28">
													  		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mktgMapRqstPk_A1' )">Request ID<img id="sortIMG.mktgMapRqstPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													  		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mktgFldMapNm_A1' )">Mapping Name<img id="sortIMG.mktgFldMapNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													  		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upldErrFlg_A1' )">Upload<br>  Error<img id="sortIMG.upldErrFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													  		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mktgMapRqstProcFlg_A1' )">Request<br> Processed<img id="sortIMG.mktgMapRqstProcFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													  		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upldFileNm_A1' )">Upload File Name<img id="sortIMG.upldFileNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													  		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'contrAssnTrgtTpDescTxt_A1' )">Contract Association<br> & Target<img id="sortIMG.contrAssnTrgtTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													  		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxFullNm_A1' )">Request By<img id="sortIMG.xxFullNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													  		<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_A1' )">Request On<img id="sortIMG.xxDtTm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													  		<td class="pClothBs">Success</td>
													  		<td class="pClothBs">Review</td>
													  		<td class="pClothBs">Potential<br> New Prospect</a></td>
												  		</tr>
													</tbody>
												</table>
											</div>
											<div  id="rightTbl" style="width:1135px; height:420px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A">
													<colgroup>
										            	<col align="left" width="65"><!-- Request ID -->
													  	<col align="left" width="121" /><!-- Mapping Name -->
													  	<col align="center" width="70" /><!-- Upload Error -->
														<col align="center" width="70" /><!-- Request Processed -->
														<col align="left" width="149" /><!-- Upload File Name -->
														<col align="left" width="122" /><!-- Contract Association & Target -->
														<col align="left" width="155" /><!-- Request By -->
														<col align="left" width="150" /><!-- Request On -->
														<col align="center" width="70" /><!-- Success -->
														<col align="center" width="70" /><!-- Review -->
														<col align="center" width="70" /><!-- Potential New Prospect -->
													</colgroup>
													<tbody>
													<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
													<%@ page import="business.servlet.NMAL3200.NMAL3200BMsg" %>
													<%@ page import="business.servlet.NMAL3200.NMAL3200_ABMsg" %>
													<% 
													NMAL3200BMsg bMsg = (NMAL3200BMsg)databean.getEZDBMsg(); 
													for ( int i = 0; i < bMsg.A.getValidCount(); i++ ) { 
														NMAL3200_ABMsg lineMsg = bMsg.A.no(i);
														String upldErrFlg = lineMsg.upldErrFlg_A1.getValue();
														String exactMatchFlg = lineMsg.exactMatchFlg_A1.getValue();
														String prtlMatchFlg = lineMsg.prtlMatchFlg_A1.getValue();
														String dunsMatchFlg = lineMsg.dunsMatchFlg_A1.getValue();
														String glnMatchFlg = lineMsg.glnMatchFlg_A1.getValue();
													%>
														<tr height="28" id="id_row<%= i %>">
															<td class="stab">
																<ezf:anchor name="mktgMapRqstPk_A1" ezfName="mktgMapRqstPk_A1" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="Download_UpldData" otherAttr=" id=\"mktgMapRqstPk_A1<%= i %>\"">
																	<ezf:label name="mktgMapRqstPk_A1" ezfName="mktgMapRqstPk_A1" ezfHyo="A" ezfArrayNo="<%= i %>"/>
																</ezf:anchor>
															</td>
															<td class="stab">
																<ezf:inputText name="mktgFldMapNm_A1" ezfName="mktgFldMapNm_A1" value="----+----1----+----2----+" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/>
															</td>
															<td class="stab">
																<ezf:inputCheckBox name="upldErrFlg_A1" ezfName="upldErrFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" id=\"upldErrFlg_A1<%= i %>\""/>
															</td>
															<td class="stab">
																<ezf:inputCheckBox name="mktgMapRqstProcFlg_A1" ezfName="mktgMapRqstProcFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" id=\"mktgMapRqstProcFlg_A1<%= i %>\""/>
															</td>
															<td class="stab">
																<ezf:inputText name="upldFileNm_A1" ezfName="upldFileNm_A1" value="----+----1----+----2----+----3----+----4" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\" maxlength=\"256\" ezftoupper=\"\""/>
															</td>
															<td class="stab">
																<ezf:inputText name="contrAssnTrgtTpDescTxt_A1" ezfName="contrAssnTrgtTpDescTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"16\" maxlength=\"50\" ezftoupper=\"\""/>
															</td>
															<td class="stab">
																<ezf:inputText name="xxFullNm_A1" ezfName="xxFullNm_A1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\" maxlength=\"100\" ezftoupper=\"\""/>
															</td>
															<td class="stab">
																<ezf:inputText name="xxDtTm_A1" ezfName="xxDtTm_A1" value="06/16/2016 13:19:30" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\" maxlength=\"23\" ezftoupper=\"\""/>
															</td>
															<td class="stab">
																<% if( "N".equals(upldErrFlg) && ("Y".equals(exactMatchFlg) || "Y".equals(dunsMatchFlg) || "Y".equals(glnMatchFlg))) { %>
																	<ezf:inputButton name="Download_SuccessFile" value="Download" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn5" />
																<% } else { %>
																	&nbsp;
																<% } %>
															</td>
															<td class="stab">
																<% if( "N".equals(upldErrFlg) && "Y".equals(prtlMatchFlg)) { %>
																	<ezf:inputButton name="Download_ReviewFile" value="Download" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn5" />
																<% } else { %>
																	&nbsp;
																<% } %>
															</td>
															<td class="stab">
																<% if( "N".equals(upldErrFlg) && "N".equals(exactMatchFlg) && "N".equals(dunsMatchFlg) && "N".equals(glnMatchFlg) && "N".equals(prtlMatchFlg)) { %>
																	<ezf:inputButton name="Download_NewProsFile" value="Download" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn5" />
																<% } else { %>
																	&nbsp;
																<% } %>
															</td>
														</tr>
													<% } %>
													<ezf:skip>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" ezftoupper>1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_A1" ezfname="mktgFldMapNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_A1" value="Y" ezfname="upldErrFlg_A1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_A1" value="Y" ezfname="mktgMapRqstProcFlg_A1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_A1" ezfname="upldFileNm_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_A1" ezfname="contrAssnTrgtTpDescTxt_A1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="cratUsrId_A1" ezfname="cratUsrId_A1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_A1" ezfname="xxDtTm_A1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
														<tr height="28" id="id_a_row{EZF_ROW_NUMBER}">
															<td class="stab">
																<a href="#" name="xxLinkAncr_DU" ezfname="xxLinkAncr_DU" ezfhyo="A" onclick="sendServer('Download_UpldData', '{EZF_ROW_NUMBER}')" >
																	<label name="mktgMapRqstPk_B1" ezfname="mktgMapRqstPk_B1" ezfhyo="A" value="" >1234567890</label>
																</a>
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="mktgFldMapNm_B1" ezfname="mktgFldMapNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+" ezftoupper />
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="upldErrFlg_B1" value="Y" ezfname="upldErrFlg_B1" ezfhyo="A" id="upldErrFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="checkbox" class="pPro" name="mktgMapRqstProcFlg_B1" value="Y" ezfname="xxChkBox_ELmktgMapRqstProcFlg_B1" ezfhyo="A" id="mktgMapRqstProcFlg_B1{EZF_ROW_NUMBER}">
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="256" name="upldFileNm_B1" ezfname="upldFileNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3----+----4" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="16" maxlength="50" name="contrAssnTrgtTpDescTxt_B1" ezfname="contrAssnTrgtTpDescTxt_B1" ezfhyo="A" ezfout value="----+----1----+----2" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="100" name="xxFullNm_B1" ezfname="xxFullNm_B1" ezfhyo="A" ezfout value="----+----1----+----2----+----3" ezftoupper />
															</td>
															<td class="stab">
																<input type="text" class="pPro" size="20" maxlength="23" name="xxDtTm_B1" ezfname="xxDtTm_B1" ezfhyo="A" ezfout value="06/16/2016 13:19:30" ezftoupper />
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_SuccessFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_ReviewFile" ezfhyo="A">
															</td>
															<td class="stab">
																<input onclick="sendServer(this,{EZF_ROW_NUMBER})" type="button" class="pBtn5" value="Download" name="Download_NewProsFile" ezfhyo="A">
															</td>
														</tr>
													</ezf:skip>
													</tbody>
												</table>
											</div>
                                        	<!-- ################################################## Search Result   - [E N D] ################################################## -->
										</div>
									</c:when>
								<%-- ########## Download Tab [END] ########## --%>
                        		</c:choose>
                        	</div>
                    	</div>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</center>



<%-- **** Task specific area ends here **** --%>
