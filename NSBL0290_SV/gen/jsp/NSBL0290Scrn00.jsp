<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161205155440 --%>
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
            <input type="hidden" name="pageID" value="NSBL0290Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Skill Maintenance">

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
                        <li id="Resrc_Skill" title="Resource Skill" class="pTab_OFF"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MoveWin_ResrcSkillMaint" otherAttr=" tabindex=\"-1\"">Resource Skill</ezf:anchor></li>
                        <li id="Skills" title="Skills" class="pTab_ON"><a href="#" tabindex="-1">Skills</a></li>
                        <li id="Skill_Levels" title="Skill Levels" class="pTab_OFF"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MoveWin_SkillLevelMaint" otherAttr=" tabindex=\"-1\"">Skill Levels</ezf:anchor></li>
                        <li id="Access_Permits" title="Access Permits" class="pTab_OFF"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MoveWin_AccessPermits" otherAttr=" tabindex=\"-1\"">Access Permits</ezf:anchor></li>
                    </ul>
                </div>

<!-- ######################################## TAB(Main) ################################## -->
                        <div class="pTab_BODY_In">
                            <table width="99%">
                                <tr>
                                    <td align="left">
                                        <table border="0" cellpadding="1" cellspacing="0">
                                            <col width="20">
                                            <col width="110">
                                            <col width="5">
                                            <col width="190">
                                            <col width="16">
                                            <col width="70">
                                            <col width="5">
                                            <col width="190">
                                            <col width="16">
                                            <col width="100">
                                            <col width="5">
                                            <col width="110">
                                            <col width="10">
                                            <col width="110">
                                            <col width="10">
                                            <col>
                                            <tr>
                                                <td></td>
                                                <td class="stab">Skill Type</td>
                                                <td></td>
                                                <td>
                                                    <ezf:select name="svcSkillTpCd_S" ezfName="svcSkillTpCd_S" ezfBlank="1" ezfCodeName="svcSkillTpCd_C" ezfDispName="svcSkillTpNm_D" otherAttr=" style=\"width: 182px\""/>
                                                </td>
                                                <td></td>
                                                <td class="stab">Skill Name(*)</td>
                                                <td></td>
                                                <td><ezf:inputText name="svcSkillNm_H" ezfName="svcSkillNm_H" value="Skill Name" otherAttr=" size=\"25\""/></td>
                                                <td></td>
                                                <td class="stab">Skill Description(*)</td>
                                                <td></td>
                                                <td colspan="3"><ezf:inputText name="svcSkillDescTxt_H" ezfName="svcSkillDescTxt_H" value="Skill Description" otherAttr=" size=\"25\""/></td>
                                                <td></td>
                                                <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td class="stab">Skill Type Description</td>
                                                <td></td>
                                                <td><ezf:inputText name="svcSkillTpDescTxt" ezfName="svcSkillTpDescTxt" value="Technical Skills" otherAttr=" size=\"25\""/></td>
                                                <td></td>
                                                <td class="stab">Use Scale</td>
                                                
                                                <td></td>
                                                <td>
                                                    <ezf:select name="svcSkillLvlGrpCd_S" ezfName="svcSkillLvlGrpCd_S" ezfBlank="1" ezfCodeName="svcSkillLvlGrpCd_C" ezfDispName="svcSkillLvlGrpDescTxt_D" />
                                                </td>
                                                <td></td>
                                                <td class="stab">Effective Date</td>
                                                <td></td>
                                                <td><ezf:inputText name="effFromDt_H" ezfName="effFromDt_H" value="02/02/2015" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H', 4);" ></td>
                                                <td>-</td>
                                                <td><ezf:inputText name="effThruDt_H" ezfName="effThruDt_H" value="02/02/2015" otherAttr=" size=\"12\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H', 4);" ></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <hr />
<!-- ######################################## DETAIL ######################################## -->
<!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
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
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</td>
									</tr>
						 		</table>
						 		
			 				<td>
			 			</tr>
			 		</table>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
                            <table style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0" align="center" width="712px">
                                <!-- =============== TABLE HEADER =============== -->
                                <tr>
                                    <td align="left" valign="top">
                                        <table border="1" cellpadding="1" cellspacing="0" width="925px" style="table-layout: fixed;" >
                                            <colgroup>
                                            <col width="25px"   align="center">
                                            <col width="220px"  align="middle">
                                            <col width="290px"  align="center">
                                            <col width="150px"  align="center">
                                            <col width="120px"  align="center">
                                            <col width="120px"  align="center">
                                            </colgroup>
                                            <tbody>
                                            <tr height="35px">
                                                <td class="pClothBs">&nbsp;</td>
                                                <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcSkillNm' )">Skill Name<img id="sortIMG.svcSkillNm" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcSkillDescTxt' )">Skill Description<img id="sortIMG.svcSkillDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcAliasRate' )">Alias<img id="sortIMG.svcAliasRate" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt' )">Effective Date<br>From<img id="sortIMG.effFromDt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt' )">Effective Date<br>Thru<img id="sortIMG.effThruDt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div style="overflow-y:scroll; height:380px; width:942px;">
                                        <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A" >
                                                <colgroup>
                                                <col width="25" >
                                                <col width="220">
                                                <col width="290">
                                                <col width="150" align="right">
                                                <col width="120">
                                                <col width="120">
                                                </colgroup>
                                                <ezf:row ezfHyo="A">
                                                    <tr height="25px">
                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        <td><ezf:inputText name="svcSkillNm" ezfName="svcSkillNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\""/></td>
                                                        <td><ezf:inputText name="svcSkillDescTxt" ezfName="svcSkillDescTxt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\""/></td>
                                                        <td align="right"><ezf:inputText name="svcAliasRate" ezfName="svcAliasRate" value="12,3456,789.12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"25\" style=\"text-align:right\""/></td>                                            
                                                        <td>
                                                            <ezf:inputText name="effFromDt" ezfName="effFromDt" value="99/99/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4, '{EZF_ROW_NUMBER}');">
                                                        </td>                                            
                                                        <td>
                                                            <ezf:inputText name="effThruDt" ezfName="effThruDt" value="99/99/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4, '{EZF_ROW_NUMBER}');">
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
                                        </div>
                                    </td>
                                </tr>
                            </table>
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
                                            <tr>
                                                <td></td>
                                                <td><ezf:inputButton name="Insert_Row" value="Insert Row" htmlClass="pBtn7" /></td>
                                                <td></td>
                                                <td><ezf:inputButton name="Delete_Row" value="Delete Row" htmlClass="pBtn7" /></td>
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

<script type="text/javascript">

    function clickImg(prntObj, idx) {
        var eventNm = prntObj.all(0).value;
        sendServer(eventNm, idx);
    }

    function changeBGColor(chkObj, idx) {
        var bgColorCls  = "";
        var origBgColor = document.getElementById("xxTblItemTxt_A#" + idx).value;
        
        if (chkObj.checked) {
            bgColorCls = "checkLineBGcolor";
            if (bgColorCls != origBgColor) {
                document.getElementById("xxTblItemTxt_A#" + idx).value =
                    document.getElementById("A_RightTr#"  + idx).className;
            }
        } else {
            bgColorCls = origBgColor;
        }
        document.getElementById("A_RightTr#" + idx).className = bgColorCls;
    }
</script>


<%-- **** Task specific area ends here **** --%>
