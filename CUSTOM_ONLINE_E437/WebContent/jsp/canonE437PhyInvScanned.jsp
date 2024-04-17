<%@ page import="java.math.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="oracle.apps.fnd.common.*" %>
<%@ page import="oracle.apps.jtf.base.session.*" %>
<%@ page import="oracle.apps.jtf.aom.transaction.*" %>

<%--
 +===========================================================================+
 |                                                                           |
 +===========================================================================+
 |  FILENAME                                                                 |
 |    canonE437PhyInvScanned.jsp                                    	 	 |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |    Displays All Items Scanned By Current User                             |
 |  NOTES                                                                    |
 |                                                                           |
 |  DEPENDENCIES                                                             |
 |                                                                           |
 |  HISTORY                                                                  |
 |                                                           				 |
 |                                                                           |
 +===========================================================================+
--%>

<!-- Standard JTF include for import statements -->
<%@ include file="./canonE410Session.jsp" %>
<%	
	String strOrgId = request.getParameter("orgId");	
	String strPhyInvId = request.getParameter("phyInvId");	
	String strPhyInv = request.getParameter("phyInvName");	
	String strEmpId = request.getParameter("empId");	
	String strFormMode = request.getParameter("formMode");	
	String strTechId = request.getParameter("techId");
	String strTechName = request.getParameter("techName");
	String strTechOrgId = request.getParameter("techOrgId");
	String strCallType = request.getParameter("callType");	
	
%>

<script src="../js/jtfdsnfb.js" language="javascript"></script>
<script src="../js/jtfdtfrm.js" language="javascript"></script>
<script src="../js/canonTagPosition.js" language="javascript"></script>

<script language="javascript">
	// to avoid backspacing from a readonly field
	document.onkeydown = function(e) {
		var e=window.event || e;
		if (e.keyCode == '8') { // for backspace button
			if (e.srcElement) {
				var tag = e.srcElement.tagName.toUpperCase(); 
				var tagId = e.srcElement.id;
				var type = e.srcElement.type; 
				if (tag =='INPUT' && document.getElementById(tagId).readOnly) return false;
				if (tag =='DIV' || tag=='TD' || tag=='TABLE') return false;
			}
		}
	}
	
	//AJAX
	function GetXmlHttpObject() {
		var xmlHttp=null;
		try { // Firefox, Opera 8.0+, Safari
			xmlHttp=new XMLHttpRequest();
		} catch (e) { //Internet Explorer
			try {
				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
    }

	function disableDefault() {
		var keyCode = event.keyCode;
		if (keyCode == 0) event.returnValue = false;
		event.returnValue = false;
		return false;
	}

	function whichButton(event) {
		var vMode = document.getElementById("hFormMode").value.toUpperCase();
		// F1
		if (event.keyCode == 112) {
			var url = './canonE410PartScanningHelp.jsp';
			var winhelp = window.open(url,'mywin');
		}     

		//F2
		if (event.keyCode == 113) {
			if (vMode == "WAREHOUSE") {	
				var url = './canonE410PartScanningMenu.jsp';
				window.location.href = url;
			} else return;	
		}

		//F3
		if (event.keyCode == 114) {
			var url = './canonE437PhysicalInventory.jsp?mode='+vMode;
			if (vMode == "TECHNICIAN") {	
				var vTechId = document.getElementById("pTechId").value.toUpperCase();
				var vTechName = document.getElementById("pTechName").value.toUpperCase();
				var vTechOrgId = document.getElementById("pTechOrgId").value.toUpperCase();
				url += "&techId="+encodeURI(vTechId);
				url += "&techName="+encodeURI(vTechName);
				url += "&techOrgId="+encodeURI(vTechOrgId);
			}
			window.location.href = url;
		}
	}	

	function clearError() {
		var errorLoaded=(document.getElementById("errorDiv").innerHTML);
		if (errorLoaded!=null) document.getElementById("errorDiv").innerHTML ="";
	}

	function displayError(errMsg) {
		var errorTbl = "<table border='0'><tr><td color = '#FF0000'><font size='2' color='red'><b>"+errMsg+"</b></font></td> </tr> </table>";
		document.getElementById("errorDiv").innerHTML = errorTbl;
		return;
	}
	
	function fetchData(pNaviMode) {
		if ((pNaviMode != "next") && (pNaviMode != "prev")) {
			alert("Incorrect navigation mode "+ pNaviMode);
			return;
		}
		var vPageNum = document.getElementById("hPageNum").value;
		if ((pNaviMode == "prev") && (vPageNum <= 1)) return;
		if (pNaviMode == "prev") { 
			vPageNum--;
		} else if (pNaviMode == "next") {
			vPageNum++;
		}
		document.getElementById("hPageNum").value = vPageNum;		
		clearError();
		var vOrgId = document.getElementById("hOrgId").value;
		var vPhyInvId = document.getElementById("hPhyInvId").value;
		var vEmpId = document.getElementById("hEmpId").value;
		var vMode = document.getElementById("hFormMode").value.toUpperCase();
		var vBeginItem = document.getElementById("beginItem").value;
		var vEndItem = document.getElementById("endItem").value;
		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=getScannedDataByPage";
		params = params+"&orgId="+encodeURI(vOrgId);
		params = params+"&phyInvId="+encodeURI(vPhyInvId);
		params = params+"&empId="+encodeURI(vEmpId);
		params = params+"&formMode="+encodeURI(vMode);
		params = params+"&pageNum="+encodeURI(vPageNum);
		params = params+"&beginItem="+encodeURI(vBeginItem);
		params = params+"&endItem="+encodeURI(vEndItem);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { displayScannedData(pNaviMode,vMode); }; 
		httpRequest.send(null); 
	}
	
	function displayScannedData(pNaviMode,vMode) {
		if (httpRequest.readyState == 4) {        
			if (httpRequest.status == 200) {          
				document.getElementById("wait").style.display = "none";
				var errorFlag = httpRequest.responseXML.getElementsByTagName("errFlag")[0].childNodes[0].nodeValue;
				
				if (errorFlag == "E") {
					//var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					//displayError(errorMsg);
					document.getElementById("hPageNum").value=0;
					fetchData("next");
				} else {
					document.getElementById("dataDiv").style.display = "";		
					
					var rcount = httpRequest.responseXML.getElementsByTagName("rcount")[0].childNodes[0].nodeValue;
										
					if (vMode == "TECHNICIAN") {
						var hdrElementsName = ["Part","SubInv","Tag","Qty"];						
						var subElementNames = ["itemNum","subInv","tagNum","tagQty"];
					} else if (vMode == "WAREHOUSE") {
						var hdrElementsName = ["Part","Locator","Tag","Qty"];
						var subElementNames = ["itemNum","locator","tagNum","tagQty"];
					}					
					
					var dataTblHTML = "<table border='0' width='100%' align='left'>\n"
					
					dataTblHTML+= "	<tr id='report_tbl_first'>\n";					
					for (var i=0; i<(hdrElementsName.length); i++) dataTblHTML+="		<td class='hd'>" + hdrElementsName[i] + "</td>\n";
					dataTblHTML+= "	</tr>\n";					

					var rows = httpRequest.responseXML.getElementsByTagName("row");
					var noRows = new Array(rows.length);
					
					for (var i=0; i<rows.length; i++) {
						var values =  new Array(subElementNames.length);
						for (var j=0; j<subElementNames.length; j++) {
							var name = subElementNames[j];
							var subElement = rows[i].getElementsByTagName(name)[0];
							subElement.normalize();
							values[j] = subElement.childNodes[0].nodeValue;
						}
						noRows[i] = values;
					}
					
					for (var i=0; i<(noRows.length); i++) {
						var row=noRows[i];
						dataTblHTML+= "	<tr id=cntData"+i+" class = 'eventableDataCell'>\n"
						dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[0]+i+" style='border: solid 0px;font-size:8pt;width=75px;' value='"+ row[0] + "' readonly> </td>\n"
						dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[1]+i+" style='border: solid 0px;font-size:8pt;width=60px;' value='"+ row[1] + "' readonly> </td>\n"
						dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[2]+i+" style='border: solid 0px;font-size:8pt;width=30px;' value='"+ row[2] + "' readonly> </td>\n"
						dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[3]+i+" style='border: solid 0px;font-size:8pt;width=20px;' value='"+ row[3] + "' readonly> </td>\n"
						dataTblHTML+= "	</tr>\n";
					}
					dataTblHTML+="</table>";
					
					//alert(dataTblHTML);
					
					document.getElementById("dataDiv").innerHTML=dataTblHTML;															
				}
			}
		}	
	}

	
//	function beginScan() {
//		window.location.href = "./canonE436CycleCount.jsp";
//	}
	
	
	function filterItems() {
		var vBeginItem = document.getElementById("beginItem").value;
		var vEndItem = document.getElementById("endItem").value;
		if ((vBeginItem == "") && (vEndItem == "")) {
			return;
		} else {
			document.getElementById("hPageNum").value=0;
			fetchData("next");
		}
	}	

</script>

<html>
    <head>
        <title>Physical Inventory Data</title>
        <link rel="stylesheet" href="../css/CanonE410PartScanning.css" type="text/css" />
    </head>
    <body onhelp="disableDefault();" onkeydown="whichButton(event)" onload="fetchData('next');">	
		<input type="hidden" id="hOrgId" name="hOrgId" value="<%=strOrgId%>" />
		<input type="hidden" id="hPhyInvId" name="hPhyInvId" value="<%=strPhyInvId%>" />
		<input type="hidden" id="hEmpId" name="hEmpId" value="<%=strEmpId%>" />
		<input type="hidden" id="hFormMode" name="hFormMode" value="<%=strFormMode%>" />
		<input type="hidden" id="hCallType" name="hCallType" value="<%=strCallType%>" />	
		<input type="hidden" id="pTechId" name="pTechId" value="<%=strTechId%>">
		<input type="hidden" id="pTechName" name="pTechName" value="<%=strTechName%>">
		<input type="hidden" id="pTechOrgId" name="pTechOrgId" value="<%=strTechOrgId%>">		
		<input type="hidden" id="hPageNum" name="hPageNum" value=0>	
		<form name="phyInvDataForm">
			<fieldset>
				<b> <legend>PhyInv (F3-Back)</legend></b>
				<table id="buttons" width="100%" border="0">
					<tr>
						<td colspan="2" align="left"><input style="border: solid 0px;width:100%" type="text" value="<%=strPhyInv%>"  readonly /></td>
					</tr>				
					<tr>
						<td align="left"><input type="button" name="prevBatch" id="prevBatch" onclick="fetchData('prev');" style="height: 18px; width: 48px;border:1px solid;" value="Prev"/></td>
						<td align="right"><input type="button" name="nextBatch" id="nextBatch" onclick="fetchData('next');" style="height: 18px; width: 48px;border:1px solid;" value="Next"/></td>
					</tr>
					<tr>
						<td align="left"><input type="text" name="beginItem" id="beginItem" onblur="filterItems();" onchange="filterItems();" style="width: 70px;border:1px solid;" value=""/></td>
						<td align="right"><input type="text" name="endItem" id="endItem" onblur="filterItems();" onchange="filterItems();" style="width: 70px;border:1px solid;" value=""/></td>
					</tr>			
				</table>											
				<div name="errorDiv" id="errorDiv"></div>
				<div id="wait" style ="display:none;"><font color="green"><i>Please Wait...</i></font><img src="../images/canonE410Wait.gif" id="tLov"></div>
			</fieldset>
			<fieldset>
				<div id="dataDiv" style="display:none;background-color:#D8E0D2;color:#000; position:absolute;"></div>
			</fieldset>					
		</form>
    </body>
</html>
