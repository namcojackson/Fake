<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230313155944 --%>
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
			<input type="hidden" name="pageID" value="NPAL1200Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Insourcing Planning Setup Screen">

<CENTER>
<TABLE height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
  <TBODY>
  <TR>
    <TD>
      <%-- ********************** --%>
      <%-- *** Upper Tab Area *** --%>
      <%-- ********************** --%>
      <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

      <DIV class="pTab_BODY">
                        <!-- ################################################## Search Criteria - [START] ################################################## -->
                        <table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
                            <col width="152">
                            <col width="345">
                            <col width="110">
                            <col width="300">
                            <col width="">
                            <tr>
                                <td class="stab">Saved Search Options</td>
                                <td>
                                    <ezf:select name="srchOptPk_SE" ezfName="srchOptPk_SE" ezfBlank="1" ezfCodeName="srchOptPk_CD" ezfDispName="srchOptNm_DI" otherEvent1=" onchange=\"sendServer('OnChange_SearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
                                </td>
                                <td class="stab">Search Option Name</td>
                                <td class="stab"><ezf:inputText name="srchOptNm_TX" ezfName="srchOptNm_TX" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                <td>
                                    <ezf:inputButton name="SaveSearchOption" value="Save Search" htmlClass="pBtn8" />
                                    <ezf:inputButton name="DeleteSearchOption" value="Delete Search" htmlClass="pBtn8" />
                                </td>
                            </tr>
                        </table>
            <hr>

		  <!-- Line1 -->
		  <table class="header-line" style="margin-top: 8px;" border="0" cellpadding="0" cellspacing="0">
			  <colgroup>
				  <!-- Tech Zone -->
				  <col width="88" /><!-- col1 header -->
				  <col width="167" />
				  <col width="84" /><!-- col1-2 space -->
				  <!-- Tech Zone Warehouse -->
				  <col width="142" /><!-- col2 header -->
				  <col width="48" />
				  <col width="30" align="center" />
				  <col width="auto" />
				  <!-- Tech Request Type -->
				  <col width="5" /><!-- col2-3 space -->
				  <col width="100" /><!-- col3 header -->
				  <col width="145" />
			  </colgroup>
			  <tbody>
              <tr height="20">
				  <td class="stab"><label>Tech Zone</label></td>
				  <td>
					  <ezf:select name="srcZnCd_SF" ezfName="srcZnCd_SF" ezfBlank="1" ezfCodeName="srcZnCd_CF" ezfDispName="srcZnCd_DF" />
				  </td>
				  <td></td>
				  <td class="stab">
					  <ezf:anchor name="rtlWhCd_A1" ezfName="rtlWhCd_A1" ezfEmulateBtn="1" ezfGuard="OpenWinFromWarehouseH" >
						  <label>Tech Zone Warehouse</label>
					  </ezf:anchor>
				  </td>
				  <td>
					  <ezf:inputText name="rtlWhCd_HF" ezfName="rtlWhCd_HF" value="----+-" otherAttr=" size=\"6\" maxlength=\"3\" ezftoupper=\"\""/>
				  </td>
				  <td>
					  <ezf:inputButton name="SearchFromWHNameH" ezfName="SearchFromWHNameH" value=">>" />
				  </td>
				  <td>
					  <ezf:inputText name="rtlWhNm_HF" ezfName="rtlWhNm_HF" value="----+----1----+----2----+----3" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\""/>
				  </td>
				  <td></td>
				  <td class="stab"><label>Tech Request Type</label></td>
				  <td>
					  <ezf:select name="prchReqTpCd_SF" ezfName="prchReqTpCd_SF" ezfBlank="1" ezfCodeName="prchReqTpCd_CF" ezfDispName="prchReqTpDescTxt_DF" />
				  </td>
			  </tr>
			  </tbody>
		  </table>

		  <!-- Line2 -->
		  <table class="header-line" style="margin-top: 2px;" border="0" cellpadding="0" cellspacing="0">
			  <colgroup>
				  <!-- Insourcing Zone -->
				  <col width="88" /><!-- col1 header -->
				  <col width="167" />
				  <col width="84" /><!-- col1-2 space -->
				  <!-- Insourcing Zone Warehouse -->
				  <col width="142" /><!-- col2 header -->
				  <col width="48"/>
				  <col width="30" align="center" />
				  <col width="auto" />
				  <!-- Plan Type -->
				  <col width="5" /><!-- col2-3 space -->
				  <col width="100" /><!-- col3 header -->
				  <col width="60" />
			  </colgroup>
			  <tbody>
              <tr height="20">
				  <td class="stab"><label>Insourcing Zone</label></td>
				  <td>
					  <ezf:select name="srcZnCd_ST" ezfName="srcZnCd_ST" ezfBlank="1" ezfCodeName="srcZnCd_CT" ezfDispName="srcZnCd_DT" />
				  </td>
				  <td></td>
				  <td class="stab">
					  <ezf:anchor name="rtlWhCd_A2" ezfName="rtlWhCd_A2" ezfEmulateBtn="1" ezfGuard="OpenWinToWarehouseH" >
						  <label>Insourcing Zone Warehouse</label>
					  </ezf:anchor>
				  <td>
					  <ezf:inputText name="rtlWhCd_HT" ezfName="rtlWhCd_HT" value="----+-" otherAttr=" size=\"6\" maxlength=\"3\" ezftoupper=\"\""/>
				  </td>
				  <td>
					  <ezf:inputButton name="SearchToWHNameH" ezfName="SearchToWHNameH" value=">>" />
				  </td>
				  <td>
					  <ezf:inputText name="rtlWhNm_HT" ezfName="rtlWhNm_HT" value="----+----1----+----2----+----3" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\""/>
				  </td>
				  <td></td>
				  <td class="stab"><label>Plan Type</label></td>
				  <td>
					  <ezf:select name="xxTpCd_SF" ezfName="xxTpCd_SF" ezfBlank="1" ezfCodeName="xxTpCd_CF" ezfDispName="xxTpNm_DF" />
				  </td>
			  </tr>
			  </tbody>
		  </table>

		  <!-- Line3 -->
		  <table class="header-line" style="margin-top: 2px;" border="0" cellpadding="0" cellspacing="0">
			  <colgroup>
				  <!-- Item Class -->
				  <col width="88" /><!-- col1 header -->
				  <col width="167" />
				  <col width="50" /><!-- col1-2 space -->
				  <!-- Enable Only -->
				  <col width="140" /><!-- col2 header -->
				  <col width="20" />
				  <col width="560" />
				  <!-- Search Button -->
				  <col width="auto" />
			  </colgroup>
			  <tbody>
              <tr height="20">
				  <td class="stab"><label>Item Class</label></td>
				  <td>
					  <ezf:select name="mdseItemClsTpCd_SH" ezfName="mdseItemClsTpCd_SH" ezfBlank="1" ezfCodeName="mdseItemClsTpCd_CH" ezfDispName="mdseItemClsTpDescTxt_DH" otherAttr=" style=\"width:200px\""/>
				  </td>
				  <td></td>
				  <td class="stab"><label>Enable Only</label></td>
				  <td>
					  <ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />
				  </td>
				  <td></td>
				  <td>
					  <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
				  </td>
			  </tr>
			  </tbody>
		  </table>

          <!-- ################################################## Search Criteria - [E N D] ################################################## -->
		  <HR style="margin: 2px 0" />

		  <!-- Detail Header -->
		  <table class="header-line" border="0" style="margin-bottom: 2px;">
			  <colgroup>
				  <!-- Tech Zone -->
				  <col width="120" /><!-- col1 header -->
				  <col width="48" /><!-- 20��������239�A30��������308 -->
				  <col width="30" />
				  <col width="150" />
				  <col width="5" />
				  <!-- Insourcing Zone -->
				  <col width="150" /><!-- col2 header -->
				  <col width="48" />
				  <col width="30" />
				  <col width="150" />
				  <col width="5" />
				  <!-- Item Class -->
				  <col width="60" />
				  <col width="166" />
				  <col width="5" />
				  <!-- Add Button -->
				  <col width="22"/><!-- 50 -->
			  </colgroup>
			  <tbody>
                  <tr>
                      <!-- Tech Zone Warehouse -->
                      <td class="stab">
                          <ezf:anchor name="rtlWhCd_L1" ezfName="rtlWhCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWinFromWarehouseD" >
                              <label>Tech Zone Warehouse</label>
                          </ezf:anchor>
                      </td>
                      <td>
                          <ezf:inputText name="rtlWhCd_DF" ezfName="rtlWhCd_DF" value="----+-" otherAttr=" size=\"6\" maxlength=\"3\" ezftoupper=\"\""/>
                      </td>
                      <td>
                          <ezf:inputButton name="SearchFromWHNameD" ezfName="SearchFromWHNameD" value=">>" />
                      </td>
                      <td>
                          <ezf:inputText name="rtlWhNm_DF" ezfName="rtlWhNm_DF" value="----+----1----+----2----+----3" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\""/>
                      </td>
                      <td></td>
                      <!-- Insourcing Zone Warehouse -->
                      <td class="stab">
                          <ezf:anchor name="rtlWhCd_L2" ezfName="rtlWhCd_L2" ezfEmulateBtn="1" ezfGuard="OpenWinToWarehouseD" >
                              <label>Insourcing Zone Warehouse</label>
                          </ezf:anchor>
                      </td>
                      <td>
                          <ezf:inputText name="rtlWhCd_DT" ezfName="rtlWhCd_DT" value="----+-" otherAttr=" size=\"6\" maxlength=\"3\" ezftoupper=\"\""/>
                      </td>
                      <td>
                          <ezf:inputButton name="SearchToWHNameD" ezfName="SearchToWHNameD" value=">>" />
                      </td>
                      <td>
                          <ezf:inputText name="rtlWhNm_DT" ezfName="rtlWhNm_DT" value="----+----1----+----2----+----3" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\""/>
                      </td>
                      <td></td>
                      <!-- Item Class  -->
                      <td class="stab">
                          <label>Item Class</label>
                      </td>
                      <td>
                          <ezf:select name="mdseItemClsTpCd_SD" ezfName="mdseItemClsTpCd_SD" ezfBlank="1" ezfCodeName="mdseItemClsTpCd_CD" ezfDispName="mdseItemClsTpDescTxt_DD" otherAttr=" style=\"width:200px\""/>
                      </td>
                      <td></td>
                      <td><ezf:inputButton name="AddDetailLine" value="Add" htmlClass="pBtn6" /></td>
                  </tr>
			  </tbody>
		  </table>

 <!-- ################################################## Search Result   - [START] ################################################## -->

		  <div id="TopTBL" style="margin: 0 0 0 10px;  overflow-y:none; overflow-x:hidden; width: 1114px">
			  <table cellSpacing="0" cellPadding="1" border="1" width="1097">
				  <colgroup>
					  <col align="center" width="30" /><!-- Checkbox -->
					  <col align="center" width="155" /><!-- Tech Zone -->
					  <col align="center" width="195" /><!-- Tech Zone Warehouse (1) + (2) + 4 -->
					  <col align="center" width="155" /><!-- Insourcing Zone -->
					  <col align="center" width="200" /><!-- Insourcing Zone Warehouse (1) + (2) + 4 -->
					  <col align="center" width="90" /><!-- Item Class -->
					  <col align="center" width="120"  /><!-- Tech Request Type -->
					  <col align="center" width="81" /><!-- $ Threshold -->
					  <col align="center" width="35" /><!-- Rank -->
					  <col align="center" width="35" /><!-- Enabled -->
				  </colgroup>
				  <tbody>
				  <tr height="28">
					  <td class="pClothBs">&nbsp;</td>
					  <td class="pClothBs">Tech Zone</td>
					  <td class="pClothBs">Tech Zone Warehouse</td>
					  <td class="pClothBs">Insourcing Zone</td>
					  <td class="pClothBs">Insourcing Zone Warehouse</td>
					  <td class="pClothBs">Item Class</td>
					  <td class="pClothBs">Tech Request Type</td>
					  <td class="pClothBs">$ Threshold</td>
					  <td class="pClothBs">Rank</td>
					  <td class="pClothBs">Enabled</td>
				  </tr>
				  </tbody>
			  </table>
		  </div>

		  <div id="TBL" style="margin: 0 0 0 10px; overflow-y:scroll; overflow-x:none; width: 1114px; HEIGHT: 335px">
			  <table cellSpacing="0" cellPadding="1" border="1" width="1097" id="A">
				  <colgroup>
					  <col align="center" width="27" /><!-- Checkbox -->
					  <col align="center" width="140" /><!-- Tech Zone -->
					  <col align="left" width="33" /><!-- Tech Zone Warehouse (1) -->
					  <col align="center" width="143" /><!-- Tech Zone Warehouse (2) -->
					  <col align="center" width="140" /><!-- Insourcing Zone -->
					  <col align="left" width="33" /><!-- Insourcing Zone Warehouse (1) -->
					  <col align="center" width="145" /><!-- Insourcing Zone Warehouse (2) -->
					  <col align="center" width="80" /><!-- Item Class -->
					  <col align="center" width="87" /><!-- Tech Request Type -->
					  <col align="center" width="77" /><!-- $ Threshold -->
					  <col align="center" width="34" /><!-- Rank -->
					  <col align="center" width="40" /><!-- Enabled -->
				  </colgroup>
				  <tbody>
				  <ezf:row ezfHyo="A">
					  <tr height="28" id="id_row{EZF_ROW_NUMBER}">
						  <td><!-- Checkbox -->
							  <ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="A" ezfArrayNo="0" />
						  </td>
						  <td><!-- Tech Zone -->
							  <ezf:inputText name="fromSrcZnCd_D1" ezfName="fromSrcZnCd_D1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"20\" tabindex=\"-1\""/>
						  </td>
						  <td><!-- Tech Zone Warehouse (1) -->
							  <ezf:label name="fromRtlWhCd_D1" ezfName="fromRtlWhCd_D1" ezfHyo="A" ezfArrayNo="0" />
						  </td>
						  <td><!-- Tech Zone Warehouse (2) -->
							  <ezf:inputText name="rtlWhNm_D1" ezfName="rtlWhNm_D1" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"-1\""/>
						  </td>
						  <td><!-- Insourcing Zone -->
							  <ezf:inputText name="toSrcZnCd_D1" ezfName="toSrcZnCd_D1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"20\" tabindex=\"-1\""/>
						  </td>
						  <td><!-- Insourcing Zone Warehouse (1) -->
							  <ezf:label name="toRtlWhCd_D1" ezfName="toRtlWhCd_D1" ezfHyo="A" ezfArrayNo="0" />
						  </td>
						  <td><!-- Insourcing Zone Warehouse (2) -->
							  <ezf:inputText name="rtlWhNm_D2" ezfName="rtlWhNm_D2" value="----+----1----+----2----+----3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" tabindex=\"-1\""/>
						  </td>
						  <td><!-- Item Class -->
							  <ezf:select name="mdseItemClsTpCd_SE" ezfName="mdseItemClsTpCd_SE" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mdseItemClsTpCd_CE" ezfDispName="mdseItemClsTpDescTxt_DE" ezfCodeDispHyo="A" otherAttr=" style=\"width:80px\""/>
						  </td>
						  <td><!-- Tech Request Type -->
							  <ezf:select name="prchReqTpCd_SE" ezfName="prchReqTpCd_SE" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="prchReqTpCd_CE" ezfDispName="prchReqTpDescTxt_DE" ezfCodeDispHyo="A" otherAttr=" style=\"width:110px\""/>
						  </td>
						  <td><!-- $ Threshold -->
							  <ezf:inputText name="insrcItemPrcThrhdAmt_D1" ezfName="insrcItemPrcThrhdAmt_D1" value="99,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"19\" style=\"text-align:right\""/>
						  </td>
						  <td><!-- Rank -->
							  <ezf:inputText name="insrcRnkSortNum_D1" ezfName="insrcRnkSortNum_D1" value="##0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/>
						  </td>
						  <td><!-- Enabled -->
							  <ezf:select name="insrcEnblFlg_SD" ezfName="insrcEnblFlg_SD" ezfHyo="A" ezfArrayNo="0" ezfCodeName="insrcEnblFlg_CD" ezfDispName="insrcEnblFlg_DD" ezfCodeDispHyo="A" />
						  </td>
					  </tr>
				  </ezf:row>

				  </tbody>
			  </table>
		  </div>

		  <!-- footer buttons -->
		  <table style="margin: 6px 0 0 10px" border="0" cellpadding="1" cellspacing="1">
			  <colgroup>
				  <col width="" align="left">
				  <col width="" align="left">
				  <col width="945" align="right">
			  </colgroup>
			  <tbody>
			  <tr>
				<td><ezf:inputButton name="EditDetailLine" value="Edit" htmlClass="pBtn6" /></td>
				<td><ezf:inputButton name="CancelDetailLine" value="Cancel" htmlClass="pBtn6" /></td>
				<td>
				  <%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
					<TABLE cellSpacing="0" cellPadding="1" width="99%" border="0">
						<TBODY>
							<tr align="right">
								  <td style="padding-right: 16px;">
								  <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
									  <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									  <jsp:param name="TableNm"     value="A" />
									  <jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									  <jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									  <jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
								  </jsp:include>
								  </td>
							 </tr>
						</TBODY>
					</TABLE>
				  <%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
				</td>
			  </tr>
			  </tbody>
		  </table>

        <!-- ################################################## Search Result   - [E N D] ################################################## -->
        </DIV></TD></TR></TBODY></TABLE></CENTER>



<%-- **** Task specific area ends here **** --%>
