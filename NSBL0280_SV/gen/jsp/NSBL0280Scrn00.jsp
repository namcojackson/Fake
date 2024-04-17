<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161205143120 --%>
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
			<input type="hidden" name="pageID" value="NSBL0280Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Resource Skill Maintenance">
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <ezf:skip>
                <div class="pTab_HEAD">
                    <ul>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="96%">
                                    <div>
                                        <li title="Resource Skill Maintenance" class="pTab_ON"><a href="#">Rsrc Skl Mnt</a></li>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </ul>
                </div>
                </ezf:skip>
                <div class="pTab_BODY">

<!-- ######################################## BODY(TAB) ################################## -->
                <div class="pTab_HEAD">
                    <ul>
                        <li id="Resrc_Skill" title="Resource Skill" class="pTab_ON"><a href="#" tabindex="-1">Resource Skill</a></li>
                        <li id="Skills" title="Skills" class="pTab_OFF"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MoveWin_SkillMaint" otherAttr=" tabindex=\"-1\"">Skills</ezf:anchor></li>
                        <li id="Skill_Levels" title="Skill Levels" class="pTab_OFF"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MoveWin_SkillLevelMaint" otherAttr=" tabindex=\"-1\"">Skill Levels</ezf:anchor></li>
                        <li id="Access_Permits" title="Access Permits" class="pTab_OFF"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MoveWin_AccessPermits" otherAttr=" tabindex=\"-1\"">Access Permits</ezf:anchor></li>
                    </ul>
                </div>
                <c:choose>
<%-- ######################################## TAB(Main) ################################### --%>
                <c:when test="${pageScope._ezddatabean.xxScrDply == 'ResourceType'}">
                <script type="text/javascript">
                    document.getElementById( "resourceType" ).style.display = "block";
                    document.getElementById( "skillType" ).style.display = "none";
                </script>
                <div id="resourceType" class="pTab_BODY_In" >
                    <table width="99%">
                        <tr>
                            <td align="left">
                                <table border="0" cellpadding="1" cellspacing="0">
                                    <col width="40">
                                    <col width="110">
                                    <col width="16">
                                    <col width="400">
                                    <col width="16">
                                    <col width="40">
                                    <col width="16">
                                    <col width="80">
                                    <col width="16">
                                    <col >
                                    <col width="16">
                                    <col width="110">
                                    <col width="10">
                                    <col width="110">
                                    <tr>
                                        <td></td>
                                        <td class="stab">Resource Type</td>
                                        <td></td>
                                        <td>
                                            <ezf:select name="svcSkillResrcTpCd_S" ezfName="svcSkillResrcTpCd_S" ezfCodeName="svcSkillResrcTpCd_C" ezfDispName="svcSkillResrcTpDescTxt_D" otherAttr=" style=\"width: 400px\""/>
                                        </td>
                                        <td></td>
                                        <td class="stab"><ezf:anchor name="skillName" ezfName="skillName" ezfEmulateBtn="1" ezfGuard="OpenWin_Resource" >Resource</ezf:anchor></td>
                                        <td></td>
                                        <td><ezf:inputText name="psnCd" ezfName="psnCd" value="10000123" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                        <td></td>
                                        <td><ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" value="RICHARD,SHANNONPXXX2XXXXXXXX3XXXXXXXXX5XXXXXXXXX6X" otherAttr=" size=\"40\""/></td>
                                        <td></td>
                                        <td><ezf:inputButton name="Search_Resource" value="Search" htmlClass="pBtn6" /></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <hr />
<%-- ######################################## DETAIL ##################################################### --%>
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<table border="0" cellpadding="1" cellspacing="0" align="center" width="937px">
						<tr>
							<td align="right">
								<table border="0" cellpadding="0" width="100%">
									<tr align="right">
										<td>
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
						 		
			 				<td>
			 			</tr>
			 		</table>
<%-- ######################################## TO (COMMON)PAGENATION #################################### --%>
                    <table style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0" align="center" width="712px" >
                        <tr>
                            <td align="left" valign="top">
                                <table border="1" cellpadding="1" cellspacing="0" width="925px" style="table-layout: fixed;">
                                    <colgroup>
                                    <col width="25px"   align="center">
                                    <col width="220px"  align="middle">
                                    <col width="240px"  align="center">
                                    <col width="240px"  align="center">
                                    <col width="100px"  align="center">
                                    <col width="100px"  align="center">
                                    </colgroup>
                                    <tbody>
                                    <tr height="35px">
                                        <td class="pClothBs">&nbsp;</td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcSkillTpDescTxt_A' )">Skill Type<img id="sortIMG.svcSkillTpDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcSkillDescTxt_A' )">Skill Name<img id="sortIMG.svcSkillDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcSkillLvlPk_AS' )">Skill Level<img id="sortIMG.svcSkillLvlPk_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A' )">Effective Date<br>From<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A' )">Effective Date<br>Thru<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div style="overflow-y:scroll; height:390px; width:942px;">
                                <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
                                        <colgroup>
                                        <col width="25" >
                                        <col width="220">
                                        <col width="240">
                                        <col width="240">
                                        <col width="100">
                                        <col width="100">
                                        </colgroup>
                                        <ezf:row ezfHyo="A">
                                            <tr height="25px">
                                                <td align="center"><ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
                                                <td><ezf:inputText name="svcSkillTpDescTxt_A" ezfName="svcSkillTpDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" style=\"border:none; background-color:transparent;\""/></td>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_S" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_S#{EZF_ROW_NUMBER}\""/>
                                                    <ezf:inputText name="svcSkillDescTxt_A" ezfName="svcSkillDescTxt_A" value="XXXXXXXXX1XXXXXXXXX2XXXXXXX89" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\""/>
                                                </td>
                                                <td >
                                                    <ezf:select name="svcSkillLvlPk_AS" ezfName="svcSkillLvlPk_AS" ezfHyo="A" ezfArrayNo="0" ezfCodeName="svcSkillLvlGrpCd_BC" ezfDispName="svcSkillLvlDescTxt_BD" otherAttr=" style=\"width: 235px\""/>
                                                </td>                                            
                                                <td>
                                                    <ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="99/99/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');">
                                                </td>                                            
                                                <td>
                                                    <ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="99/99/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');">
                                                </td>
                                                <td class="pAuditInfo">
                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                </td>
                                            </tr>
                                        </ezf:row>
                                        <ezf:skip>
                                        </ezf:skip>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
               </c:when>
<%-- ######################################## FROM DIV2   #################################### --%>
                <c:when test="${pageScope._ezddatabean.xxScrDply == 'skillType'}">
                <script type="text/javascript">
                    document.getElementById( "resourceType" ).style.display = "none";
                    document.getElementById( "skillType" ).style.display = "block";
                </script>
                <div class="pTab_BODY_In" id="skillType">
                    <table width="99%">
                        <tr>
                            <td align="left">
                                <table border="0" cellpadding="1" cellspacing="0">
                                    <col width="40">
                                    <col width="110">
                                    <col width="16">
                                    <col width="400">
                                    <col width="16">
                                    <col width="80">
                                    <col width="16">
                                    <col >
                                    <col width="16">
                                    <col width="80">
                                    <col width="16">
                                    <col width="110">
                                    <col width="10">
                                    <col width="110">
                                    <tr>
                                        <td></td>
                                        <td class="stab">Skill Type</td>
                                        <td></td>
                                        <td>
                                            <ezf:select name="svcSkillTpCd_S" ezfName="svcSkillTpCd_S" ezfCodeName="svcSkillTpCd_C" ezfDispName="svcSkillTpDescTxt_D" otherAttr=" style=\"width: 400px\""/>
                                        </td>
                                        <td></td>
                                        <td class="stab"><ezf:anchor name="skillName" ezfName="skillName" ezfEmulateBtn="1" ezfGuard="OpenWin_Skill" >Skill Name</ezf:anchor></td>
                                        <td></td>
                                        <td><ezf:inputText name="svcSkillDescTxt" ezfName="svcSkillDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" otherAttr=" size=\"50\""/></td>
                                        <td></td>
                                        <td><ezf:inputButton name="Search_Skill" value="Search" htmlClass="pBtn6" /></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <hr />
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<table border="0" cellpadding="1" cellspacing="0" align="center" width="937px">
						<tr>
							<td align="right">
								<table border="0" cellpadding="0" width="100%">
									<tr align="right">
										<td>
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
												<jsp:param name="TableNm"     value="B" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
											</jsp:include>
										</td>
									</tr>
						 		</table>
			 				<td>
			 			</tr>
			 		</table>
<%-- ######################################## TO (COMMON)PAGENATION #################################### --%>
                    <table style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0" align="center" width="712px">
                        <tr>
                            <td align="left" valign="top">
                                <table border="1" cellpadding="1" cellspacing="0" width="925px" style="table-layout: fixed;">
                                    <colgroup>
                                    <col width="25px"   align="center">
                                    <col width="190px"  align="middle">
                                    <col width="185px"  align="center">
                                    <col width="175px"  align="center">
                                    <col width="170px"  align="center">
                                    <col width="100px"  align="center">
                                    <col width="100px"  align="center">
                                    </colgroup>
                                    <tbody>
                                    <tr height="35px">
                                        <td class="pClothBs">&nbsp;</td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'techCd_B' )">Person Code<img id="sortIMG.techCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'fullPsnNm_B' )">Person Name<img id="sortIMG.fullPsnNm_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'svcSkillResrcTpCd_BS' )">Resource Type<img id="sortIMG.svcSkillResrcTpCd_BS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'svcSkillLvlPk_BS' )">Skill Level<img id="sortIMG.svcSkillLvlPk_BS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'effFromDt_B' )">Effective Date<br>From<img id="sortIMG.effFromDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'effThruDt_B' )">Effective Date<br>Thru<img id="sortIMG.effThruDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div style="overflow-y:scroll; height:390px; width:942px;">
                                <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="B">
                                        <colgroup>
                                        <col width="25" >
                                        <col width="190">
                                        <col width="185">
                                        <col width="175">
                                        <col width="170">
                                        <col width="100">
                                        <col width="100">
                                        </colgroup>
                                        <ezf:row ezfHyo="B">
                                            <tr height="25px">
                                                <td align="center"><ezf:inputRadio name="xxRadioBtn_B" ezfName="xxRadioBtn_B" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="B" ezfGetLineNoOffset="0" /></td>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_R" value="R" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_R#{EZF_ROW_NUMBER}\""/>
                                                    <ezf:inputText name="techCd_B" ezfName="techCd_B" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" ezftoupper=\"\""/>
                                                </td>
                                                <td><ezf:inputText name="fullPsnNm_B" ezfName="fullPsnNm_B" value="XXXXXXXXX1XXXXXXXXX2XXXX12345" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"24\" style=\"border:none; background-color:transparent;\" ezftoupper=\"\""/></td>
                                                <td >
                                                    <ezf:select name="svcSkillResrcTpCd_BS" ezfName="svcSkillResrcTpCd_BS" ezfHyo="B" ezfArrayNo="0" ezfCodeName="svcSkillResrcTpCd_C" ezfDispName="svcSkillResrcTpDescTxt_D" otherAttr=" style=\"width: 170px\""/>
                                                </td>
                                                <td >
                                                    <ezf:select name="svcSkillLvlPk_BS" ezfName="svcSkillLvlPk_BS" ezfHyo="B" ezfArrayNo="0" ezfCodeName="svcSkillLvlGrpCd_BC" ezfDispName="svcSkillLvlDescTxt_BD" otherAttr=" style=\"width: 165px\""/>
                                                </td>                                            
                                                <td>
                                                    <ezf:inputText name="effFromDt_B" ezfName="effFromDt_B" value="99/99/9999" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_B', 4, '{EZF_ROW_NUMBER}');">
                                                </td>                                            
                                                <td>
                                                    <ezf:inputText name="effThruDt_B" ezfName="effThruDt_B" value="99/99/9999" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_B', 4, '{EZF_ROW_NUMBER}');">
                                                </td>
                                                <td class="pAuditInfo">
                                                    <ezf:inputHidden name="xxRecHistCratTs_B" ezfName="xxRecHistCratTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_B\""/>
                                                    <ezf:inputHidden name="xxRecHistCratByNm_B" ezfName="xxRecHistCratByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_B\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdTs_B" ezfName="xxRecHistUpdTs_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_B\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdByNm_B" ezfName="xxRecHistUpdByNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_B\""/>
                                                    <ezf:inputHidden name="xxRecHistTblNm_B" ezfName="xxRecHistTblNm_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_B\""/>
                                                </td>
                                            </tr>
                                        </ezf:row>
                                        <ezf:skip>
                                        </ezf:skip>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
                </c:when>
                </c:choose>
<!-- ######################################## TO DIV2 #################################### -->
                    <table border="0">
                        <tr height="2">
                            <td></td>
                        </tr>
                        <tr>
                            <td style="text-align:bottom;">
                                <table border="0" cellpadding="1" cellspacing="0" width="100%">
                                    <col width="100">
                                    <col width="40">
                                    <col width="5">
                                    <col width="40">
                                    <col width="5">
                                    <col width="40">
                                    <tr>
                                        <td></td>
                                        <td><ezf:inputButton name="Insert_Row" value="Insert Row" htmlClass="pBtn7" /></td>
                                        <td></td>
                                        <td><ezf:inputButton name="Delete_Row" value="Delete Row" htmlClass="pBtn7" /></td>
                                        <td></td>
                                        <td><ezf:inputButton name="Switch_View" value="Switch View" htmlClass="pBtn7" /></td>
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


<%-- **** Task specific area ends here **** --%>
