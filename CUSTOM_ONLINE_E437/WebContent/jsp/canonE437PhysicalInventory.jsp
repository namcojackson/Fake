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
 |    canonE437PhysicalInventory.jsp                                  		 |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |    Enables performing physical inventory counts from handheld device    	 |
 |                                                                           |
 |  NOTES                                                                    |
 |                                                                           |
 |  DEPENDENCIES                                                             |
 |                                                                           |
 |  HISTORY                                                                  |
 |                                                        	                 |
 +===========================================================================+
--%>

<!-- Standard JTF include for import statements -->
<%@ include file="./canonE410Session.jsp" %>
<%	
	//String strMode = request.getParameter("mode");
	String strMode = "TECHNICIAN";
//	String strMode = "WAREHOUSE";
    String userPhySubInv = "";
//    String userPhySubInv = "5X1";
    String userOrgId = "";

	String strTechId = request.getParameter("techId");
	String strTechName = request.getParameter("techName");
	String strTechOrgId = request.getParameter("techOrgId");

//Debug
//Manager
//userId="D09553";
//userName="Marcus Hadley";

//Tech
//userId="D09194";
//userName="Bobby Jamison";

%>

<script src="../js/jtfdsnfb.js" language="javascript"></script>
<script src="../js/jtfdtfrm.js" language="javascript"></script>
<script src="../js/canonTagPosition.js" language="javascript"></script>
<script language="javascript">
	
	//disable default help
	function disableDefault() {
//alert('disableDefault');
//		var keyCode = event.keyCode;
//alert('keyCode['+keyCode+']');
//		if (keyCode == 0) {	
//			event.returnValue = false;	
//		}
//		event.returnValue = false;
		return false;
	} //function disableDefault()
	
	

	// to avoid backspacing from a readonly field
	document.onkeydown = function(e) {
		var e=window.event || e
		if (e.keyCode == '8') { // for backspace button
			if (e.srcElement) {
				var tag = e.srcElement.tagName.toUpperCase(); 
				var tagId = e.srcElement.id;
				var type = e.srcElement.type; 
				if (tag =='INPUT' && document.getElementById(tagId).readOnly) {
					return false;
				}
				if (tag =='DIV' || tag=='TD' || tag=='TABLE') {
					return false;
				}
			}
		}
	}



	//get httprequest for AJAX
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
    } //function GetXmlHttpObject()


	
	//keydown
	function whichButton(event) {
		var vMode = document.getElementById("hMode").value.toUpperCase();

		// F1
		if (event.keyCode == 112) {
			var url = './canonE410PartScanningHelp.jsp';
			var winhelp = window.open(url,'mywin');	
		}	
			
		//F2
		if (event.keyCode == 113) {
			//if (vMode == "WAREHOUSE") {	
			//	//var url = './canonE410PartScanningMenu.jsp';
			//	var url ='./canonE410InventoryMenu.jsp';
			//	window.location.href = url;
			//} else return;
		}
			
		//F3
		if (event.keyCode == 114) {
			var url = './canonE437PhysicalInventory.jsp?mode='+vMode;
			window.location.href = url;
		}		
		
		//ITG 301386, Added Logic To Print The Physical Inventory Report
		//F8
		if (event.keyCode == 119) {
			if (vMode == "TECHNICIAN") {	
				clearError();
				var vOrgId = document.getElementById("hOrgId").value;
				var vPhyInvId = document.getElementById("hPhyInvId").value;
				var vUserId = document.getElementById("hUserId").value;
				var vMode = document.getElementById("hMode").value.toUpperCase();
				var url = "./canonE437PhyInvData.jsp";

				var params = "?callType=printReport";
				params = params+"&orgId="+encodeURI(vOrgId);
				params = params+"&phyInvId="+encodeURI(vPhyInvId);
				params = params+"&userId="+encodeURI(vUserId);
				params = params+"&formMode="+encodeURI(vMode);
				
				url = url+params;

				httpRequest = GetXmlHttpObject();
				if (httpRequest==null) {
					alert ("Browser does not support HTTP Request");
					return;
				}
				document.getElementById("wait").style.display = "";
				httpRequest.open("POST", url, true); 
				httpRequest.onreadystatechange = function() { processPrintMessage(); }; 
				httpRequest.send(null); 	
				
				//displayError("Physical Inventory Counted Parts Report Submitted"); //25-Oct-2010
				//document.getElementById("wait").style.display = "none"; //25-Oct-2010
			} else return;
		}
	} //function whichButton(event)
	
	
	//25-Oct-2010
	// display print message
	function processPrintMessage() {
		if (httpRequest.readyState == 4) {        
			if (httpRequest.status == 200) {          
				document.getElementById("wait").style.display = "none";
				var errorFlag = httpRequest.responseXML.getElementsByTagName("errFlag")[0].childNodes[0].nodeValue;

				if (errorFlag == "E") {				
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					displayError(errorMsg);
				} else {
					displayError("Physical Inventory Counted Parts Report Submitted");					
				}
			} else { 
				alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
        }
	
	} //function displayScannedData(vMode)

	
	//default focus on page load
	function focusOnLoad() {
		clearError();
		var vMode = document.getElementById("hMode").value.toUpperCase();
		
		if (vMode == "TECHNICIAN") {	
			document.getElementById("techLegendDiv").style.display = "";
			var vTechId = document.getElementById("pTechId").value.toUpperCase();
			var vTechName = document.getElementById("pTechName").value.toUpperCase();
			var vTechOrgId = document.getElementById("pTechOrgId").value.toUpperCase();
			
			document.getElementById("techInfoTbl").style.display = "";
			
			if ((vTechId!='NULL')&&(vTechName!='NULL')&&(vTechOrgId!='NULL')) {
				document.getElementById("hTechId").value = vTechId.replace(/^\s+|\s+$/g, '');
				document.getElementById("techName").value = vTechName.replace(/^\s+|\s+$/g, '');
				document.getElementById("hOrgId").value = vTechOrgId.replace(/^\s+|\s+$/g, '');
				
				getTechPhyInvData();
			} else getTechList("S");
		} else if (vMode == "WAREHOUSE") {
			document.getElementById("whLegendDiv").style.display = "";
			var vPhyInvSub = document.getElementById("hPhyInvSub").value;
			if (vPhyInvSub == "" || vPhyInvSub == null) {
				displayError("SubInventory Missing");
			} else {
				getWhPhyInvData();
			}
			
		} else	displayError("Form Has Been Invoked With An Invalid Mode "+vMode);
	} //function focusOnLoad()
	
	
	
	//Function to handle the key up and down in lov div
	function arrowKeyDown(divid,tableid,rowId,colId,inputid,deLim) {
		var pos= getMeFocusedLine(tableid,colId);
		
		if (pos ==(document.getElementById(tableid).getElementsByTagName('tr').length)-1) {
			try {
				document.getElementById(rowId+pos).style.backgroundColor="";
			} catch (e) { }			
			document.getElementById(colId+pos).style.backgroundColor="";
			pos = -1;
		}

		if (pos!=-1) {
			try {
				document.getElementById(rowId+pos).style.backgroundColor="";
			} catch (e) { }					
			document.getElementById(colId+pos).style.backgroundColor="";
		} 
		
		var idx = pos+1;

		try {
			document.getElementById(rowId+idx).style.backgroundColor="#FFFFFF";
		} catch (e) { }
			
		document.getElementById(colId+idx).style.backgroundColor="#FFFFFF";
		document.getElementById(colId+idx).focus();
		document.getElementById(inputid).value	=splitter(document.getElementById(colId+idx).innerHTML,deLim);
		document.getElementById(inputid).focus();
	} //function arrowKeyDown
	
	
	
	//Function to handle the key up and down in lov div
	function arrowKeyUp(divid,tableid,rowId,colId,inputid,deLim) {
		var y = getMeFocusedLine(tableid,colId);
		
		if (y==0) {
			m=(document.getElementById(tableid).getElementsByTagName("tr").length)-1;
			try {
				document.getElementById(rowId+m).style.backgroundColor="#FFFFFF";
			} catch (e) { }
			document.getElementById(colId+m).style.backgroundColor="#FFFFFF";
			document.getElementById(inputid).value= splitter(document.getElementById(colId+m).innerHTML,deLim);
			document.getElementById(colId+m).focus();
			
		}
		
		if (y!=-1) {
			try {
				document.getElementById(rowId+y).style.backgroundColor="";
			} catch (e) { }			
			document.getElementById(colId+y).style.backgroundColor="";
		}
		
		if (y!=0 && y!=-1) {
			var idx = y-1;
			try {
				document.getElementById(rowId+idx).style.backgroundColor="#FFFFFF";
			} catch (e) { }			
			document.getElementById(colId+idx).style.backgroundColor="#FFFFFF";
			document.getElementById(colId+idx).focus();
			document.getElementById(inputid).value=splitter(document.getElementById(colId+idx).innerHTML,deLim);
		}
		
		document.getElementById(inputid).focus();
	} //function arrowKeyUp
	
	
	
	//used by js arrowKeyUp arrowKeyDown
	function getMeFocusedLine(tableId,colId) {
		var x=(document.getElementById(tableId).getElementsByTagName('tr').length);
		var cnt=0;
		var where=-1;

		for (i=0;i<x;i++) {
			if (document.getElementById(colId+i).style.backgroundColor=='#ffffff') {
				cnt=cnt+1;
				where = i;
				break;
			}
		}
		return where;
	} //function getMeFocusedLine
	
	
	
	//used by js arrowKeyUp arrowKeyDown
	function splitter(str,delimiter) {
		var x = new Array();
		if (delimiter =="") {
			x=str.split();
		} else {
			x=(str.split(delimiter));
		}
		return x[0].replace(/^\s+|\s+$/g, '');
	} //function splitter



	//clear error division
	function clearError() {
		var errorLoaded=(document.getElementById('errorDiv').innerHTML);
		if (errorLoaded!=null) {
			document.getElementById('errorDiv').innerHTML ="";
		}
	} //function clearError()
	
	
	
	//clear Lov division
	function clearLovDiv(id) {
		if (document.getElementById(id).style.display=="") {
			document.getElementById(id).innerHTML="";
			document.getElementById(id).style.display="none";
		}
	} //function clearLovDiv(id) 

	
	
	//clear error division
	function displayError(errMsg) {
		var errorTbl = "<table border='0'><tr><td color = '#FF0000'><font size='2' color='red'>"+errMsg+"</font></td> </tr> </table>";
		document.getElementById("errorDiv").innerHTML = errorTbl;
		return;
	} //function displayError()

	
	
	//populate tech lov
	function popTechLov(e) {
		document.getElementById("phyInvInfo").style.display="none";
		if (e!=13 && e!=40 && e!=38 && e!=27 && e!=37 && e!=39 && e!=18 && (document.getElementById("techName").readOnly==false)) {
			getTechList("S");
		}
		if (e==40 && (document.getElementById("selTechLovDiv").innerHTML!="")) {
						//divid,tableid,rowId,colId,inputid,deLim
			arrowKeyDown("selTechLovDiv","techTbl","techRow","techNum","techName","");
		}
		if (e==38 && (document.getElementById("selTechLovDiv").innerHTML!="")) {
						//divid,tableid,rowId,colId,inputid,deLim
			arrowKeyUp("selTechLovDiv","techTbl","techRow","techNum","techName","");
		}
		if (e==27) {
			clearLovDiv("selTechLovDiv");
		}
		if (e==13 && (document.getElementById("techName").readOnly==false)) {
			clearLovDiv("selTechLovDiv");
			getTechPhyInvData();			
			if (document.getElementById("techName").value !="") {
				//getTechList("V");
			} else {
				getTechList("S");
			}
		}
	} // function popTechLov(e)

	
	
	//retrieve technician List
	function getTechList(listMode) {
		clearError();
		document.getElementById("phyInvInfo").style.display="none";		
		var vUserId = document.getElementById("hUserId").value;
		if (vUserId==null) {
			alert ("User Id Is Null");
			return;
		}
		var vTechId = document.getElementById("hTechId").value;
		var vMode = document.getElementById("hMode").value.toUpperCase();
		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=techLov";
		params = params+"&userId="+encodeURI(vUserId);
		//params = params+"&techId="+encodeURI(vTechId);
		params = params+"&techId=";
		params = params+"&formMode="+encodeURI(vMode);
		params = params+"&callMode="+encodeURI(listMode);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { processTechList(listMode); }; 
		httpRequest.send(null); 
	} //function getTechList(listMode)	

	
	
	function processTechList(listMode) {
		if (httpRequest.readyState == 4) {        
			if (httpRequest.status == 200) {          
				document.getElementById("wait").style.display = "none";
				var errorFlag = httpRequest.responseXML.getElementsByTagName("errFlag")[0].childNodes[0].nodeValue;

				if (errorFlag == "E") {				
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					displayError(errorMsg);
				} else {
					var rcount = httpRequest.responseXML.getElementsByTagName("rcount")[0].childNodes[0].nodeValue;
					var rows = httpRequest.responseXML.getElementsByTagName("row");
					var techIds = new Array(rows.length);
					var techNums = new Array(rows.length);
					var techNames = new Array(rows.length);
					var techOrgIds = new Array(rows.length);
					for (var i=0; i<rows.length; i++) {
						var subElement1 = rows[i].getElementsByTagName("techId")[0];
						subElement1.normalize();
						techIds[i] = subElement1.childNodes[0].nodeValue;
						var subElement2 = rows[i].getElementsByTagName("techNum")[0];
						subElement2.normalize();
						techNums[i] = subElement2.childNodes[0].nodeValue;
						var subElement3 = rows[i].getElementsByTagName("techName")[0];
						subElement3.normalize();
						techNames[i] = (subElement3.childNodes[0].nodeValue).substring(0,20);
						var subElement4 = rows[i].getElementsByTagName("techOrgId")[0];
						subElement4.normalize();
						techOrgIds[i] = subElement4.childNodes[0].nodeValue;					
					}
					if (rcount == 1) {
						setTechName(techIds[0],techNums[0],techOrgIds[0]);
					} else {
						var AnchorPos = getAnchorPosition("techName");
						var divWidth = 0, divHeight = 0;
						if (typeof(window.innerWidth) == 'number') {
							//Non-IE
							divWidth = window.innerWidth;
							divHeight = window.innerHeight;
						} else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
							//IE 6+ in 'standards compliant mode'
							divWidth = document.documentElement.clientWidth;
							divHeight = document.documentElement.clientHeight;
						} else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
							//IE 4 compatible
							divWidth = document.body.clientWidth;
							divHeight = document.body.clientHeight;
						}
						document.getElementById("selTechLovDiv").style.display="";
						
						document.getElementById("selTechLovDiv").style.left=AnchorPos.x+2;
						document.getElementById("selTechLovDiv").style.top=AnchorPos.y+22;
						document.getElementById("selTechLovDiv").style.width=(divWidth-(AnchorPos.x+2));
						document.getElementById("selTechLovDiv").style.height="100px";	

						document.getElementById("techName").disabled=false;

						var dataTblHTML = "";					
						dataTblHTML="<table border='0' id='techTbl'>\n";
						for (var i=0; i<rows.length; i++) {
							dataTblHTML+= " <tr id=techRow"+i+">"
							dataTblHTML+= " <td onclick='setTechLovClick("+i+");' style='font-size:7pt' id=techNum"+i+"  nowrap >"+ techNums[i]+"</td>"
							dataTblHTML+= " <td onclick='setTechLovClick("+i+");' style='font-size:7pt' id=techName"+i+"  nowrap >"+ techNames[i]+"</td>"
							dataTblHTML+= " </tr>\n";
							dataTblHTML+= " <input type='hidden' id= 'techId"+i+"' value="+techIds[i]+">\n";
							dataTblHTML+= " <input type='hidden' id= 'techOrgId"+i+"' value="+techOrgIds[i]+">\n";
						}
						dataTblHTML=dataTblHTML+"</table>";
//alert(dataTblHTML);
						document.getElementById("selTechLovDiv").innerHTML =dataTblHTML;
						document.getElementById("techName").focus();					
					}
				}	
			} else { 
				alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
        }
	} //function processTechList(listMode)
	


	//setting the tech name for single record
	function setTechName(x,y,z) {
		document.getElementById("hTechId").value = x.replace(/^\s+|\s+$/g, '');
		document.getElementById("techName").value = y.replace(/^\s+|\s+$/g, '');
		document.getElementById("hOrgId").value = z.replace(/^\s+|\s+$/g, '');
		getTechPhyInvData();
	} //function setTechName(x,y,z)

	
	//setting the tech lov on click
	function setTechLovClick(i) {
		document.getElementById("hTechId").value = (document.getElementById("techId"+i).value).replace(/^\s+|\s+$/g, '');
		document.getElementById("techName").value = (document.getElementById("techNum"+i).innerHTML).replace(/^\s+|\s+$/g, '');
		document.getElementById("hOrgId").value = (document.getElementById("techOrgId"+i).value).replace(/^\s+|\s+$/g, '');
		clearLovDiv("selTechLovDiv");
		getTechPhyInvData();
	} // function setTechLovClick(i)
	


	//retrieve physical inventory data
	function getWhPhyInvData() {
		clearError();
		document.getElementById("phyInvInfo").style.display="none";		
		var vOrgId = document.getElementById("hOrgId").value;
		var vUserId = document.getElementById("hUserId").value;
		var vSubInv = document.getElementById("hPhyInvSub").value;
		var vMode = document.getElementById("hMode").value.toUpperCase();
		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=whPhyInv";
		params = params+"&orgId="+encodeURI(vOrgId);
		params = params+"&userId="+encodeURI(vUserId);
		params = params+"&subInv="+encodeURI(vSubInv);
		params = params+"&formMode="+encodeURI(vMode);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { processPhyInvData("WAREHOUSE"); }; 
		httpRequest.send(null); 
	} //function getWhPhyInvData()	



	//retrieve physical inventory data
	function getTechPhyInvData() {
		clearError();
		document.getElementById("phyInvInfo").style.display="none";		
		var vOrgId = document.getElementById("hOrgId").value;
		var vUserId = document.getElementById("hUserId").value;
		var vTechName = document.getElementById("techName").value;
		var vMode = document.getElementById("hMode").value.toUpperCase();
		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=techPhyInv";
		params = params+"&orgId="+encodeURI(vOrgId);
		params = params+"&userId="+encodeURI(vUserId);
		params = params+"&techName="+encodeURI(vTechName);
		params = params+"&formMode="+encodeURI(vMode);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { processPhyInvData("TECHNICIAN"); }; 
		httpRequest.send(null); 
	} //function getTechPhyInvData()	
	
	
	
	// process physical inventory data
	function processPhyInvData(vMode) {
		if (httpRequest.readyState == 4) {        
			if (httpRequest.status == 200) {          
				document.getElementById("wait").style.display = "none";
				var errorFlag = httpRequest.responseXML.getElementsByTagName("errFlag")[0].childNodes[0].nodeValue;

				if (errorFlag == "E") {				
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					displayError(errorMsg);
				} else if (errorFlag == "W") {
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					var errorTbl = "<table border='0'><tr><td color = '#FF0000'><font size='2' color='green'>"+errorMsg+"</font></td> </tr> </table>";
					document.getElementById("errorDiv").innerHTML = errorTbl;
					document.getElementById("phyInvInfo").style.display="";
					if (vMode == "WAREHOUSE") {
						document.getElementById("consent").disabled = true;
					} else {
						document.getElementById("techInfoTbl").style.display = "none";
                        <%if (!isAuthEditableUser) {%>
						document.getElementById("consent").disabled = true;					
                        <%} else {%>
						document.getElementById("consent").disabled = false;					
                        <%}%>
					}
					var vPhyInvId = httpRequest.responseXML.getElementsByTagName("PhyInvId")[0].childNodes[0].nodeValue;
					var vPhyInv = httpRequest.responseXML.getElementsByTagName("PhyInv")[0].childNodes[0].nodeValue;
					var vEmpId = httpRequest.responseXML.getElementsByTagName("EmpId")[0].childNodes[0].nodeValue;
					var vEmpUser = httpRequest.responseXML.getElementsByTagName("EmpUser")[0].childNodes[0].nodeValue;
					document.getElementById("hEmpId").value = vEmpId;
					document.getElementById("hEmpUsr").value = vEmpUser;
					document.getElementById("hPhyInvId").value = vPhyInvId;
					document.getElementById("phyInvName").value = vPhyInv;					
				} else {
					document.getElementById("phyInvInfo").style.display="";
					if (vMode == "WAREHOUSE") {
						document.getElementById("consent").disabled = true;
					}					
					var vPhyInvId = httpRequest.responseXML.getElementsByTagName("PhyInvId")[0].childNodes[0].nodeValue;
					var vPhyInv = httpRequest.responseXML.getElementsByTagName("PhyInv")[0].childNodes[0].nodeValue;
					var vEmpId = httpRequest.responseXML.getElementsByTagName("EmpId")[0].childNodes[0].nodeValue;
					var vEmpUser = httpRequest.responseXML.getElementsByTagName("EmpUser")[0].childNodes[0].nodeValue;
					document.getElementById("hEmpId").value = vEmpId;
					document.getElementById("hEmpUsr").value = vEmpUser;
					document.getElementById("hPhyInvId").value = vPhyInvId;
					document.getElementById("phyInvName").value = vPhyInv;
					// get the records scanned already by user
					getScannedData(vMode,null);					
				}	
			} else { 
				alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
        }
	
	} //function processPhyInvData(vMode)

	

	// get data elements in row
	function getTableData(rowTag, subElementNames) {
		var rows = httpRequest.responseXML.getElementsByTagName(rowTag);
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
		return(noRows);
	}
	

	
	//Function to validate number from the input qtys
	function checkNum() {
		var eve = event || evt; 
		var charCode = eve.which || eve.keyCode;

		if (charCode > 31 && (charCode < 48 || charCode > 57)) return false;
		return true;
	}

	
	
	//function to validate qty manual update
	function validateQty(event,index) {
		if (event==13) {
			var qty = document.getElementById("tagQty"+index).value;
			//ITG #408790 Quantity Should not exceeed more than 9999			
			
			 if (qty > 9999){
				alert("Quantity Cannot Be More Than 9999 ");
				return;
			}	

			if ((qty=="")||(qty==null)) {
				alert("Quantity Cannot Be Null");
				return;
			}	
			updateDB(index);
			document.getElementById("scanItem").focus();
		}
	}	



	//retrieve scanned data
	function getScannedData(vMode,vIdx) {
		clearError();
		document.getElementById("dataDiv").innerHTML="";		
		document.getElementById("dataDiv").style.display = "none";
		var vOrgId = document.getElementById("hOrgId").value;
		var vPhyInvId = document.getElementById("hPhyInvId").value;
		var vEmpId = document.getElementById("hEmpId").value;
		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=getScannedData";
		params = params+"&orgId="+encodeURI(vOrgId);
		params = params+"&phyInvId="+encodeURI(vPhyInvId);
		params = params+"&empId="+encodeURI(vEmpId);
		params = params+"&formMode="+encodeURI(vMode);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { displayScannedData(vMode,vIdx); }; 
		httpRequest.send(null); 
	} //function getScannedData()
	

	
	// display scanned data
	function displayScannedData(vMode,vIdx) {
		if (httpRequest.readyState == 4) {        
			if (httpRequest.status == 200) {          
				document.getElementById("wait").style.display = "none";
				var errorFlag = httpRequest.responseXML.getElementsByTagName("errFlag")[0].childNodes[0].nodeValue;

				if (errorFlag == "E") {				
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					displayError(errorMsg);
				} else {
					document.getElementById("dataDiv").style.display = "";
					
					if (vMode == "TECHNICIAN") {
						document.getElementById("techInfoTbl").style.display = "none";
                        <%if (!isAuthEditableUser) {%>
						document.getElementById("consent").disabled = true;					
                        <%} else {%>
						document.getElementById("consent").disabled = false;					
                        <%}%>
					}
					
					var rcount = httpRequest.responseXML.getElementsByTagName("rcount")[0].childNodes[0].nodeValue;
										
					if (vMode == "TECHNICIAN") {
						var hdrElementsName = ["Part","SubInv","Tag","Qty"];						
						var subElementNames = ["itemNum","subInv","tagNum","tagQty","tagId","subInv","locatorId","itemId"];
					} else if (vMode == "WAREHOUSE") {
						var hdrElementsName = ["Part","Locator","Tag","Qty"];
						var subElementNames = ["itemNum","locator","tagNum","tagQty","tagId","subInv","locatorId","itemId"];
					}					
					
					var dataTblHTML = "<table border='0' width='100%' align='left' id='countDataTBL'>\n"
					
					dataTblHTML+= "	<tr id='report_tbl_first'>\n";					
					for (var i=0; i<(hdrElementsName.length); i++) dataTblHTML+="		<td class='hd' rowspan='1' colspan='1'>" + hdrElementsName[i] + "</td>\n";
					dataTblHTML+= "	</tr>\n";					
					
					var noRows = getTableData("row", subElementNames);
					
					for (var i=0; i<(noRows.length); i++) {
						var row=noRows[i];
						dataTblHTML+= "	<tr id=cntData"+i+" class = 'eventableDataCell'>\n"
						//dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[0]+i+" style='border: solid 0px;font-size:8pt;width=75px;' value='"+ row[0] + "' readonly> </td>\n"
						//dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[1]+i+" style='border: solid 0px;font-size:8pt;width=60px;' value='"+ row[1] + "' readonly> </td>\n"
						//dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[2]+i+" style='border: solid 0px;font-size:8pt;width=30px;' value='"+ row[2] + "' readonly> </td>\n"
						//dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[3]+i+" onkeypress='return checkNum();' onKeyUp='validateQty(event.keyCode,"+i+");' style='border: solid 1px;font-size:8pt;width=20px;' value='"+ row[3] + "' <%=readonlyText%>> </td>\n"
						dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[0]+i+" style='border: solid 0px;font-size:8pt;width:75px;' value='"+ row[0] + "' readonly> </td>\n"
						dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[1]+i+" style='border: solid 0px;font-size:8pt;width:60px;' value='"+ row[1] + "' readonly> </td>\n"
						dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[2]+i+" style='border: solid 0px;font-size:8pt;width:30px;' value='"+ row[2] + "' readonly> </td>\n"
						dataTblHTML+= "		<td align='left' nowrap ><input type='text' id="+subElementNames[3]+i+" onkeypress='return checkNum();' onKeyUp='validateQty(event.keyCode,"+i+");' style='border: solid 1px;font-size:8pt;width:20px;' value='"+ row[3] + "' <%=readonlyText%>> </td>\n"
						dataTblHTML+= "		<input type='hidden' id="+subElementNames[4]+i+" value='"+ row[4] + "' readonly>\n";
						dataTblHTML+= "		<input type='hidden' id=h"+subElementNames[5]+i+" value='"+ row[5] + "' readonly>\n";
						dataTblHTML+= "		<input type='hidden' id=h"+subElementNames[6]+i+" value='"+ row[6] + "' readonly>\n";
						dataTblHTML+= "		<input type='hidden' id=h"+subElementNames[7]+i+" value='"+ row[7] + "' readonly>\n";
						dataTblHTML+= "	</tr>\n";
					}
					dataTblHTML+="</table>";
					
					//alert(dataTblHTML);
					
					document.getElementById("dataDiv").innerHTML=dataTblHTML;

					if (vIdx!=null) {
						var item = document.getElementById("itemNum"+vIdx).value;
						if ((item=="")||(item==null)) {
							return;
						}	else paintGreen(vIdx);
					}
				}
				document.getElementById("scanItem").focus();
			} else { 
				alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
        }
	
	} //function displayScannedData(vMode)
					
					
					
	// item has been scanned
	function itemScanned(e) {
		if (e==13) {
			clearError();
			var itemDescr=(document.getElementById('itemDescr').innerHTML);
			if (itemDescr!=null) {
				document.getElementById('itemDescr').innerHTML ="";
			}			
			document.getElementById("scanItem").value = ((document.getElementById("scanItem").value).toUpperCase()).replace(/^\s+|\s+$/g, '');
			var x = document.getElementById("scanItem").value;
			if ((x == null) || (x == "")) return;
			validateItem(x);	
		}
	}


	function validateItem(x) {
		var vOrgId = document.getElementById("hOrgId").value;
		var vPhyInvId = document.getElementById("hPhyInvId").value;
		var vEmpId = document.getElementById("hEmpId").value;
		var vUserId = document.getElementById("hUserId").value;
		var vMode = document.getElementById("hMode").value.toUpperCase();
		var vTechName = document.getElementById("techName").value;
		var vPhyInvSub = document.getElementById("hPhyInvSub").value.toUpperCase();

		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=validateItem";
		params = params+"&orgId="+encodeURI(vOrgId);
		params = params+"&phyInvId="+encodeURI(vPhyInvId);
//		params = params+"&empId="+encodeURI(vEmpId);
		params = params+"&empId="+encodeURI(vUserId);
//		params = params+"&userId="+encodeURI(vUserId);
        if (vMode == "TECHNICIAN") {
			params = params+"&userId="+encodeURI(vTechName);
        } else {
			params = params+"&userId="+encodeURI(vPhyInvSub);
		}
		params = params+"&itemNum="+encodeURI(x);
		params = params+"&formMode="+encodeURI(vMode);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { processItemData(); }; 
		httpRequest.send(null); 		
	}


	// process physical inventory data
	function processItemData(x) {
		if (httpRequest.readyState == 4) {        
			if (httpRequest.status == 200) {          
				document.getElementById("wait").style.display = "none";
				var vMode = document.getElementById("hMode").value.toUpperCase();
				var errorFlag = httpRequest.responseXML.getElementsByTagName("errFlag")[0].childNodes[0].nodeValue;

				if (errorFlag == "E") {				
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					displayError(errorMsg);
				} else if (errorFlag == "M") {	//multiple records found for the item
					clearError();
					try {
						var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
						var errorTbl = "<table border='0'><tr><td color = '#FF0000'><font size='2' color='green'>"+errorMsg+"</font></td> </tr> </table>";
						document.getElementById("itemDescr").innerHTML = errorTbl;						
					} catch (e) { }
					var rcount = httpRequest.responseXML.getElementsByTagName("rcount")[0].childNodes[0].nodeValue;
					var rows = httpRequest.responseXML.getElementsByTagName("row");
					var tagIds = new Array(rows.length);
					var subInvs = new Array(rows.length);
					var locators = new Array(rows.length);
					var tagQtys = new Array(rows.length);
					var locatorIds = new Array(rows.length);
					var itemIds = new Array(rows.length);
					var scannedEmps = new Array(rows.length);
					for (var i=0; i<rows.length; i++) {
						var subElement1 = rows[i].getElementsByTagName("tagId")[0];
						subElement1.normalize();
						tagIds[i] = subElement1.childNodes[0].nodeValue;
						var subElement2 = rows[i].getElementsByTagName("subInv")[0];
						subElement2.normalize();
						subInvs[i] = subElement2.childNodes[0].nodeValue;
						var subElement3 = rows[i].getElementsByTagName("locator")[0];
						subElement3.normalize();
						locators[i] = subElement3.childNodes[0].nodeValue;									
						var subElement4 = rows[i].getElementsByTagName("tagQty")[0];
						subElement4.normalize();
						tagQtys[i] = subElement4.childNodes[0].nodeValue;
						var subElement5 = rows[i].getElementsByTagName("locatorId")[0];
						subElement5.normalize();
						locatorIds[i] = subElement5.childNodes[0].nodeValue;						
						//var subElement6 = rows[i].getElementsByTagName("itemId")[0];
						var subElement6 = rows[i].getElementsByTagName("itemNum")[0];
						subElement6.normalize();
						itemIds[i] = subElement6.childNodes[0].nodeValue;						
						var subElement7 = rows[i].getElementsByTagName("scannedEmp")[0];
						subElement7.normalize();
						scannedEmps[i] = subElement7.childNodes[0].nodeValue;						
					}
					var AnchorPos = getAnchorPosition("scanItem");
					var divWidth = 0, divHeight = 0;
					if (typeof(window.innerWidth) == 'number') {
						//Non-IE
						divWidth = window.innerWidth;
						divHeight = window.innerHeight;
					} else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
						//IE 6+ in 'standards compliant mode'
						divWidth = document.documentElement.clientWidth;
						divHeight = document.documentElement.clientHeight;
					} else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
						//IE 4 compatible
						divWidth = document.body.clientWidth;
						divHeight = document.body.clientHeight;
					}
					document.getElementById("popUpLovDiv").style.display="";
					
					document.getElementById("popUpLovDiv").style.left=AnchorPos.x+2;
					document.getElementById("popUpLovDiv").style.top=AnchorPos.y+22;
					document.getElementById("popUpLovDiv").style.width=(divWidth-(AnchorPos.x+2));
					document.getElementById("popUpLovDiv").style.height="100px";
					var vMode = document.getElementById("hMode").value.toUpperCase();
	
					var dataTblHTML = "";					
					dataTblHTML="<table border='0' id='multiItemTbl'>\n";
					for (var i=0; i<rows.length; i++) {
						dataTblHTML+= " <tr>"
						if (vMode == "TECHNICIAN") {
							dataTblHTML+= " <td onKeyUp='multiSelect(event.keyCode,"+i+");' onclick='updateSelRow("+i+");' style='font-size:7pt' id=multiItem"+i+"  nowrap >"+ subInvs[i]+"</td>"
						} else if (vMode == "WAREHOUSE") {
							dataTblHTML+= " <td onKeyUp='multiSelect(event.keyCode,"+i+");' onclick='updateSelRow("+i+");' style='font-size:7pt' id=multiItem"+i+"  nowrap >"+ locators[i]+"</td>"
						}					
						dataTblHTML+= " </tr>\n";
						dataTblHTML+= " <input type='hidden' id= 'hSubInvSel"+i+"' value="+subInvs[i]+">\n";
						dataTblHTML+= " <input type='hidden' id= 'locatorIdSel"+i+"' value="+locatorIds[i]+">\n";
						dataTblHTML+= " <input type='hidden' id= 'itemIdSel"+i+"' value="+itemIds[i]+">\n";
						dataTblHTML+= " <input type='hidden' id= 'tagIdSel"+i+"' value="+tagIds[i]+">\n";
						dataTblHTML+= " <input type='hidden' id= 'tagQtySel"+i+"' value="+tagQtys[i]+">\n";
						dataTblHTML+= " <input type='hidden' id= 'scannedEmps"+i+"' value="+scannedEmps[i]+">\n";
					}
					dataTblHTML=dataTblHTML+"</table>";
					//alert(dataTblHTML);
					document.getElementById("popUpLovDiv").innerHTML =dataTblHTML;
					multiSelect(40,0);
				} else {
					document.getElementById("scanItem").value = "";
					clearError();
					try {
						var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
						var errorTbl = "<table border='0'><tr><td color = '#FF0000'><font size='2' color='green'>"+errorMsg+"</font></td> </tr> </table>";
						document.getElementById("itemDescr").innerHTML = errorTbl;						
					} catch (e) { }
					getScannedData(vMode,0);					
				}	
			} else { 
				alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
        }	
	} //function processItemData()
	
	
	
	function multiSelect(e,i) {
		//arrowKeyDown
		if (e==40 && (document.getElementById("popUpLovDiv").innerHTML!="")) {
			var pos= getMeFocusedLine("multiItemTbl","multiItem");
			
			if (pos ==(document.getElementById("multiItemTbl").getElementsByTagName('tr').length)-1) {	
				document.getElementById("multiItem"+pos).style.backgroundColor="";
				pos = -1;
			}

			if (pos!=-1) {			
				document.getElementById("multiItem"+pos).style.backgroundColor="";
			} 
			
			var idx = pos+1;
				
			document.getElementById("multiItem"+idx).style.backgroundColor="#FFFFFF";
			document.getElementById("multiItem"+idx).focus();
		}
		//arrowKeyUp
		if (e==38 && (document.getElementById("popUpLovDiv").innerHTML!="")) {
			var y = getMeFocusedLine("multiItemTbl","multiItem");
			
			if (y==0) {
				m=(document.getElementById("multiItemTbl").getElementsByTagName("tr").length)-1;
				document.getElementById("multiItem"+m).style.backgroundColor="#FFFFFF";
				document.getElementById("multiItem"+m).focus();
			}
			
			if (y!=-1) {		
				document.getElementById("multiItem"+y).style.backgroundColor="";
			}
			
			if (y!=0 && y!=-1) {
				var idx = y-1;	
				document.getElementById("multiItem"+idx).style.backgroundColor="#FFFFFF";
				document.getElementById("multiItem"+idx).focus();
			}
		}
		if (e==13 && (document.getElementById("popUpLovDiv").innerHTML!="")) updateSelRow(i);
	}
	
	
	
	function updateSelRow(index) {
		var vOrgId = document.getElementById("hOrgId").value;
		var vPhyInvId = document.getElementById("hPhyInvId").value;
		var vQty = (document.getElementById("tagQtySel"+index).value).replace(/^\s+|\s+$/g, '');
		var vTagId = (document.getElementById("tagIdSel"+index).value).replace(/^\s+|\s+$/g, '');
		var vSubInv = (document.getElementById("hSubInvSel"+index).value).replace(/^\s+|\s+$/g, '');
		var vLocatorId = (document.getElementById("locatorIdSel"+index).value).replace(/^\s+|\s+$/g, '');
		var vItemId = (document.getElementById("itemIdSel"+index).value).replace(/^\s+|\s+$/g, '');
		var vScannedEmp = (document.getElementById("scannedEmps"+index).value).replace(/^\s+|\s+$/g, '');

		if (vQty == "-") {
			var incQty = 1;
		} else {
			var incQty = parseInt(vQty)+1;
		}		
		if (vLocatorId == "-") {
			vLocatorId = "";
		}
		clearLovDiv("popUpLovDiv");
		var vEmpId = document.getElementById("hEmpId").value;
		var vUserId = document.getElementById("hUserId").value;
		var vMode = document.getElementById("hMode").value.toUpperCase();
		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=updateDBManual";
		params = params+"&orgId="+encodeURI(vOrgId);
		params = params+"&phyInvId="+encodeURI(vPhyInvId);
		params = params+"&tagId="+encodeURI(vTagId);
		params = params+"&tagQty="+encodeURI(incQty);
		//params = params+"&empId="+encodeURI(vEmpId);
		params = params+"&empId="+encodeURI(vUserId);
		//params = params+"&userId="+encodeURI(vUserId);
		params = params+"&userId="+encodeURI(vScannedEmp);
		params = params+"&subInv="+encodeURI(vSubInv);
		params = params+"&locatorId="+encodeURI(vLocatorId);
		params = params+"&itemId="+encodeURI(vItemId);
		params = params+"&formMode="+encodeURI(vMode);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { processUpdatedInfo(); }; 
		httpRequest.send(null); 		
	}
	
	function updateDB(index) {
		var vOrgId = document.getElementById("hOrgId").value;
		var vPhyInvId = document.getElementById("hPhyInvId").value;
		var vQty = (document.getElementById("tagQty"+index).value).replace(/^\s+|\s+$/g, '');
		var vTagId = (document.getElementById("tagId"+index).value).replace(/^\s+|\s+$/g, '');
		var vSubInv = (document.getElementById("hsubInv"+index).value).replace(/^\s+|\s+$/g, '');
		var vLocatorId = (document.getElementById("hlocatorId"+index).value).replace(/^\s+|\s+$/g, '');
		var vItemId = (document.getElementById("hitemId"+index).value).replace(/^\s+|\s+$/g, '');
		if (vLocatorId == "-") {
			vLocatorId = "";
		}
		var vEmpId = document.getElementById("hEmpId").value;
		var vUserId = document.getElementById("hUserId").value;
		var vMode = document.getElementById("hMode").value.toUpperCase();
		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=updateDBManual";
		params = params+"&orgId="+encodeURI(vOrgId);
		params = params+"&phyInvId="+encodeURI(vPhyInvId);
		params = params+"&tagId="+encodeURI(vTagId);
		params = params+"&tagQty="+encodeURI(vQty);
		params = params+"&empId="+encodeURI(vEmpId);
		params = params+"&userId="+encodeURI(vUserId);
		params = params+"&subInv="+encodeURI(vSubInv);
		params = params+"&locatorId="+encodeURI(vLocatorId);
		params = params+"&itemId="+encodeURI(vItemId);
		params = params+"&formMode="+encodeURI(vMode);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { processUpdatedInfo(index); }; 
		httpRequest.send(null); 		
	}


	function processUpdatedInfo (index) {
		if (httpRequest.readyState == 4) {        
			if (httpRequest.status == 200) {          
				document.getElementById("wait").style.display = "none";
				var errorFlag = httpRequest.responseXML.getElementsByTagName("errFlag")[0].childNodes[0].nodeValue;

				if (errorFlag == "E") {				
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					displayError(errorMsg);
				} else {
					clearError();
					document.getElementById("scanItem").value = "";
					var vMode = document.getElementById("hMode").value.toUpperCase();
					getScannedData(vMode,0);
				}	
			} else { 
				alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
        }
	}


	function checkPending() {
		clearError();
		var vOrgId = document.getElementById("hOrgId").value;
		var vPhyInvId = document.getElementById("hPhyInvId").value;
		var vMode = document.getElementById("hMode").value.toUpperCase();
		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=checkPendingCounts";
		params = params+"&orgId="+encodeURI(vOrgId);
		params = params+"&phyInvId="+encodeURI(vPhyInvId);
		params = params+"&formMode="+encodeURI(vMode);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { processPendingInfo(vOrgId,vPhyInvId,vMode); }; 
		httpRequest.send(null); 	
	}
	
	
	
	function processPendingInfo(vOrgId,vPhyInvId,vMode) {
		if (httpRequest.readyState == 4) {        
			if (httpRequest.status == 200) {          
				document.getElementById("wait").style.display = "none";
				var errorFlag = httpRequest.responseXML.getElementsByTagName("errFlag")[0].childNodes[0].nodeValue;

				if (errorFlag == "E") {				
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					displayError(errorMsg);
				} else if (errorFlag == "W") {	
					document.getElementById("scanItem").focus();
				} else {
					var cntMsg = httpRequest.responseXML.getElementsByTagName("pendCntMsg")[0].childNodes[0].nodeValue;
					var x = confirm(cntMsg);
					if (x==true) {
						clearError();
						var vEmpId = document.getElementById("hEmpId").value;
						var vPhyInv = document.getElementById("phyInvName").value;
						var vTechId = document.getElementById("hTechId").value.toUpperCase();
						var vTechName = document.getElementById("techName").value.toUpperCase();
						var vTechOrgId = document.getElementById("hOrgId").value.toUpperCase();						
						var url = "./canonE437PhyInvScanned.jsp";

						var params = "?callType=getAllScannedData";
						params = params+"&orgId="+encodeURI(vOrgId);
						params = params+"&phyInvName="+encodeURI(vPhyInv);
						params = params+"&phyInvId="+encodeURI(vPhyInvId);
						params = params+"&empId="+encodeURI(vEmpId);
						params = params+"&techId="+encodeURI(vTechId);
						params = params+"&techName="+encodeURI(vTechName);
						params = params+"&techOrgId="+encodeURI(vTechOrgId);
						params = params+"&formMode="+encodeURI(vMode);
						
						url = url+params;
						
						//alert(url);

						window.location.href = url;
					} else document.getElementById("scanItem").focus();
				}
			} else { 
				alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
        }
	}



	function getConsent() {
		clearError();
		var itemDescr=(document.getElementById('itemDescr').innerHTML);
		if (itemDescr!=null) {
			document.getElementById('itemDescr').innerHTML ="";
		}	
		if (document.getElementById("consent").checked == true) {
			var x = confirm("By pressing on the radio button, I hereby accept all the counts.");
			if (x==true) {
			    document.getElementById("check").disabled = true;	
				document.getElementById("consent").disabled = true;
				document.getElementById("scanItem").disabled = true; 
				var rows;
				try {
					rows = (document.getElementById("countDataTBL").getElementsByTagName('tr').length)-1;
				} catch (e) {
				
				}
				if ((!(rows==""))&&(!(rows==null))) {
					for (i=0; i<rows; i++) {
						document.getElementById("tagQty"+i).disabled = true;
					}
				}
                <%if (!isAuthEditableUser) {%>
				document.getElementById("submit").disabled = true;					
                <%} else {%>
				document.getElementById("submit").disabled = false;					
                <%}%>

			} else {
				document.getElementById("consent").checked = false;
				document.getElementById("scanItem").focus();
			}
		}
	}

	
	//assign 0 to pending counts and approve all adjustments
	function submitData() {
		clearError();
		var itemDescr=(document.getElementById('itemDescr').innerHTML);
		if (itemDescr!=null) {
			document.getElementById('itemDescr').innerHTML ="";
		}			
		var vOrgId = document.getElementById("hOrgId").value;
		var vPhyInvId = document.getElementById("hPhyInvId").value;
		var vEmpId = document.getElementById("hEmpId").value;
		var vUserId = document.getElementById("hUserId").value;
		var vMode = document.getElementById("hMode").value.toUpperCase();
		var url = "./canonE437PhyInvData.jsp";

		var params = "?callType=assignZeroCounts";
		params = params+"&orgId="+encodeURI(vOrgId);
		params = params+"&phyInvId="+encodeURI(vPhyInvId);
		params = params+"&empId="+encodeURI(vEmpId);
		params = params+"&userId="+encodeURI(vUserId);
		params = params+"&formMode="+encodeURI(vMode);
		
		url = url+params;

		httpRequest = GetXmlHttpObject();
		if (httpRequest==null) {
			alert ("Browser does not support HTTP Request");
			return;
		}
		document.getElementById("wait").style.display = "";
		httpRequest.open("POST", url, true); 
		httpRequest.onreadystatechange = function() { displayUpdateInfo(); }; 
		httpRequest.send(null); 	
	}

	
	function displayUpdateInfo() {
		if (httpRequest.readyState == 4) {        
			if (httpRequest.status == 200) {          
				document.getElementById("wait").style.display = "none";
				var errorFlag = httpRequest.responseXML.getElementsByTagName("errFlag")[0].childNodes[0].nodeValue;

				if (errorFlag == "E") {				
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					displayError(errorMsg);
				} else {
					document.getElementById("submit").disabled = true;
					var errorMsg = httpRequest.responseXML.getElementsByTagName("errMsg")[0].childNodes[0].nodeValue;
					alert(errorMsg);
					var vMode = document.getElementById("hMode").value.toUpperCase();
					var url = './canonE437PhysicalInventory.jsp?mode='+vMode;
					window.location.href = url;
				}
			} else { 
				alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
			} 
        }
	}

	
	function paintGreen(idx) {
		var vMode = document.getElementById("hMode").value.toUpperCase();
	
		document.getElementById("cntData"+idx).style.backgroundColor='#CEED91';
		document.getElementById("itemNum"+idx).style.backgroundColor='#CEED91';
		if (vMode == "TECHNICIAN") {
			document.getElementById("subInv"+idx).style.backgroundColor='#CEED91';
		} else if (vMode == "WAREHOUSE") {
			document.getElementById("locator"+idx).style.backgroundColor='#CEED91';
		}					
		document.getElementById("tagNum"+idx).style.backgroundColor='#CEED91';
		document.getElementById("tagQty"+idx).style.backgroundColor='#CEED91';
	}

	
</script>

<html>
	<head>
		<title>Physical Inventory</title>
		<link rel="stylesheet" href="../css/CanonE410PartScanning.css" type="text/css">
	</head>
	
	<body onhelp="return false;" onkeydown="whichButton(event);" onload="focusOnLoad();">
		<input type="hidden" id ="hMode" name="hMode" value="<%=strMode%>">	
		<input type="hidden" id ="hPhyInvSub" name="hPhyInvSub" value="<%=userPhySubInv%>">
		<input type="hidden" id ="hOrgId" name="hOrgId" value="<%=userOrgId%>">
		<input type="hidden" id ="hUserId" name="hUserId" value="<%=userId%>">		
		<input type="hidden" id ="pTechId" name="pTechId" value="<%=strTechId%>">
		<input type="hidden" id ="pTechName" name="pTechName" value="<%=strTechName%>">
		<input type="hidden" id ="pTechOrgId" name="pTechOrgId" value="<%=strTechOrgId%>">
		<input type="hidden" id ="hEmpId" name="hEmpId" value="">
		<input type="hidden" id ="hEmpUsr" name="hEmpUsr" value="">
		<input type="hidden" id ="hTechId" name="hTechId" value="">
		<input type="hidden" id ="hPhyInvId" name="hPhyInvId" value="">
		<form name="physicalInventory">
			<fieldset>
				<div name="techLegendDiv" id="techLegendDiv" style="display:none;"><b> <legend>PhyInv (F3-Refresh/F8-Print)</legend></b></div>
				<div name="whLegendDiv" id="whLegendDiv" style="display:none;"><b> <legend>PhyInv (F3-Refresh)</legend></b></div>
				<table id="techInfoTbl" width="100%" border="0" style="display:none;">			
					<tr><td valign="left">
						<div id="selTech"><b>Location &nbsp;<input tabindex="1" style="width:200px;" type="text" size="30" maxlength="30" id="techName" name="techName" value="" onKeyUp ="popTechLov(event.keyCode);"/></b></div>
						<div id="selTechLovDiv" style ="display:none;border:1px solid #88979E;background-color:#D8E0D2;color:#000; position:absolute;overflow-y: scroll;"></div>			                                  
					<td></tr>
				</table>	
				<div name="phyInvInfo" id="phyInvInfo" style="display:none;">			
					<table>
						<tr><td valign="left">
							<b>Name &nbsp;<input style="border: solid 0px" type="text" size="20" maxlength="20" id="phyInvName" name ="phyInvName" value=""  readonly /></b>
						</td></tr>
					</table>
					<table id="scanItemTbl">
						<tr id="scanRow" nowrap><td>
							<b>Scan->&nbsp; </b><input type="text" id="scanItem" name ="scanItem" style="width:150px;" onKeyUp ="itemScanned(event.keyCode);">
						</td></tr>
					</table>
					<div id="popUpLovDiv" style="display:none;border:1px solid #88979E;background-color:#D8E0D2; position:absolute;overflow-y: scroll;"></div>						
					<table id="buttonsTbl" width="100%" border="0">
						<tr colspan="3">
							<td align="left"><input type="button" name="check" id="check" onclick="checkPending();" style="height: 18px; width: 58px;border:1px solid;text-align: center;" value="Check"/></td>
							<td align="center"><input type="radio" name="consent" id="consent" onclick="getConsent();" style="border:0px solid;" disabled/>Accept</td>
							<td align="right"><input type="button" name="submit" id="submit" onclick="submitData();" style="height: 18px; width: 58px;border:1px solid;text-align: center;" value="Submit" disabled/></td>
						</tr>						
					</table>
					<div name="itemDescr" id="itemDescr"></div>
				</div>					
				<div name="errorDiv" id="errorDiv"></div>
				<div id="wait" style ="display:none;"><font color="green"><i>Please Wait...</i></font><img src="../images/canonE410Wait.gif" id="tLov"></div>
			</fieldset>
			<fieldset>
				<div id="dataDiv" style="display:none;"></div>
			</fieldset>				
		</form>
	</body>
</html>
