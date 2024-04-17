<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151202052820 --%>
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
			<input type="hidden" name="pageID" value="NLBL3000Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Serial Number Entry">

<script type="text/javascript" charset="UTF-8">

function arrayIndexOf(array, target) {
    for(var i = 0; i < array.length; i++) {
        if(target === array[i]) {
            return i;
            break;
        }
    }
}

function fPaste(obj) {
    var clipTextStr = window.clipboardData.getData('Text');
    var lines = clipTextStr.split("\n");

    var columnNames = [
        "serNum",
    ];

    // get offset of element which event fired
    var formOffset = 0;
    for (var i = 0; i < document.forms[0].elements.length; i ++) {
        if (obj === document.forms[0].elements[i]) {
            formOffset = i;
            break;
        }
    }

    var dataOffsetY = 0;

    // loop from element which event fired (=formOffset)  to  right and bottom edge of detail
    for (var cnt = formOffset; cnt < document.forms[0].elements.length && dataOffsetY < lines.length; cnt ++) {

        // when column offset is same to argument of.
        if(lines[dataOffsetY].length != 0 && document.forms[0].elements[cnt].name === obj.name){
            var values = lines[dataOffsetY].split("\t");

            // obtain formOffsetX
            var formOffsetX = arrayIndexOf(columnNames, obj.name);

            // save the element last found one
            var prevElem = undefined;
            // fill value along with line
            for(var x = 0; x < values.length; x++) {
                if(x > values.length) {
                    break;
                }
                var elem = document.forms[0].elements[cnt + x];

                var indexCurrent = arrayIndexOf(columnNames, elem.name);
                if(typeof indexCurrent === "undefined") {
                    break;
                }

                if(prevElem) {
                    var indexPrev = arrayIndexOf(columnNames, prevElem.name);
                    // prevent from wrapping line
                    // break if column index is moved right to left (= when wrapped)
                    if (typeof indexPrev !== "undefined" && indexPrev >= indexCurrent) {
                        break;
                    }
                }
                elem.value = values[x].toUpperCase();
                prevElem = elem;
            }
 
            dataOffsetY = dataOffsetY + 1;
        }
    }
 
    return false;
}
</script>
<center>
	<table>
		<tr>
			<td>

<!-- ############################################## Header Start ################################################## -->
				<div style="padding:4px;">
					<table>
						<col>
						<col width="2">
						<col width="100">
						<col width="10">
						<col width="35">
						<col width="15">
						<col width="280">
						<tr>
							<td class="stab">
								Header #
							</td>
							<td></td>
							<td>
								<ezf:inputText name="xxHdrNum_H1" ezfName="xxHdrNum_H1" value="CPO00001" otherAttr=" size=\"15\" tabindex=\"-1\""/>
							</td>
							<td></td>
							<td class="stab">
								Warehouse
							</td>
							<td>
								<ezf:inputText name="rtlWhCd_H1" ezfName="rtlWhCd_H1" value="1A1" otherAttr=" size=\"10\" tabindex=\"-1\""/>
							</td>
							<td>
								<ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" value="MONROE - CSA WH" otherAttr=" size=\"30\" tabindex=\"-1\""/>
							</td>
						</tr>
					</table>
				</div>
<!-- ############################################## Header End ################################################## -->

<!-- ############################################## Serial # List Start ################################################## -->
					<table border="1" cellpadding="1" cellspacing="0">
						<col align="center" width="118">
						<col align="center" width="83">
						<col align="center" width="62">
						<col align="center" width="118">
						<col align="center" width="147">
						<col align="center" width="215">
						<tr height="20">
							<td class="pClothBs">
								Trx#
							</td>
							<td class="pClothBs">
								Trx Line#
							</td>
							<td class="pClothBs">
								Sub WH
							</td>
							<td class="pClothBs">
								Item
							</td>
							<td class="pClothBs">
								Config ID
							</td>
							<td class="pClothBs">
								Serial #
							</td>
						</tr>
					</table>

					<div style="overflow-x:none; overflow-y:scroll; width:766; height:485;">
						<table border="1" cellpadding="1" cellspacing="0" id="S">
						<col align="center" width="118">
						<col align="center" width="83">
						<col align="center" width="62">
						<col align="center" width="118">
						<col align="center" width="147">
						<col align="center" width="215">
							<ezf:row ezfHyo="S">
							<tr>
								<td>
									<ezf:inputText name="xxHdrNum" ezfName="xxHdrNum" value="CXXXXXX1" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/>
								</td>
								<td>
									<ezf:inputText name="xxDplyTrxNumTxt" ezfName="xxDplyTrxNumTxt" value="001.001.001" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"20\" tabindex=\"-1\" ezftoupper=\"\""/>
								</td>
								<td>
									<ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="NEW" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\" tabindex=\"-1\" ezftoupper=\"\""/>
								</td>
								<td>
									<ezf:inputText name="mdseCd" ezfName="mdseCd" value="CXXXXXX1CXXXXXX1" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"20\" tabindex=\"-1\" ezftoupper=\"\""/>
								</td>
								<td>
									<ezf:inputText name="svcConfigMstrPk" ezfName="svcConfigMstrPk" value="10000" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"28\" tabindex=\"-1\" ezftoupper=\"\""/>
								</td>
								<td>
									<ezf:inputText name="serNum" ezfName="serNum" ezfHyo="S" ezfArrayNo="0" otherEvent1=" onpaste=\"return fPaste(this);\"" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
								</td>
							</tr>
							</ezf:row>
							<ezf:skip>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr>
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							<tr  class="pEvenNumberBGcolor">
								<td><label>CXXXXXX1</label></td>
								<td><label>001.001.001</label></td>
								<td><label>NEW</label></td>
								<td><label>CXXXXXX1CXXXXXX1</label></td>
								<td><label>1000</label></td>
								<td><input type="text" size="30" maxlength="30" ezftoupper></td>
							</tr>
							</ezf:skip>
						</table>
					</div>
<!-- ############################################## Serial # List End ################################################## -->

<!-- ############################################## Search Condition Start ################################################## -->
<!-- ############################################## Search Condition End ################################################## -->
<!-- ############################################## Page Start ################################################## -->
<!-- 
					<table>
						<col width="400">
						<col width="">
						<tr>
							<td>
							</td>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col >
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
										<td></td>
										<td class="stab">Showing</td>
										<td class="pOut">1</td>
										<td class="stab">to</td>
										<td class="pOut">20</td>
										<td class="stab">of</td>
										<td class="pOut">200</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
-->
	<!-- ############################################## Page End ################################################## -->

	<!-- ############################################## Machine Master List Start ################################################## -->
<!-- ############################################## Machine Master List End ################################################## -->

			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
