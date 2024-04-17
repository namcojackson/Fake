<!-- $Header: canon_E193_csEIAcctAddress.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csEIAcctAddress.jsp - Capture Account Address.
 |
 | DESCRIPTION
 |  Captures account address.
 | 
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	07/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
 <%@ include file="canon_E193_csTopInc.jsp" %>
 <% 
 	// Menu Prompt
	strPageName = "Enter & Inquiry";
	
	// Check page from to show the path
	strPageFrom = request.getParameter("hPageFrom");
	String hPath = request.getParameter("hPath");
	if (hPath == null)
		hPath = strAccountAddressInformationT1;
	else
		if(hPath.indexOf(strAccountAddressInformationT1) < 0)
			hPath = hPath + " -> " + strAccountAddressInformationT1;
		
	String strState = request.getParameter("state")==null?"":request.getParameter("state");
	String strZipCode = request.getParameter("zipCode")==null?"":request.getParameter("zipCode");
	
	String strSelALL = "";
	String strSelAK = "";
	String strSelAL = "";
	String strSelAR = "";
	String strSelAZ = "";
	String strSelBC = "";
	String strSelCA = "";
	String strSelCO = "";
	String strSelCT = "";
	String strSelDC = "";
	String strSelDE = "";
	String strSelFL = "";
	String strSelGA = "";
	String strSelGM = "";
	String strSelHI = "";
	String strSelIA = "";
	String strSelID = "";
	String strSelIL = "";
	String strSelIN = "";
	String strSelKS = "";
	String strSelKY = "";
	String strSelLA = "";
	String strSelMA = "";
	String strSelMD = "";
	String strSelME = "";
	String strSelMI = "";
	String strSelMN = "";
	String strSelMO = "";
	String strSelMS = "";
	String strSelMT = "";
	String strSelNC = "";
	String strSelND = "";
	String strSelNE = "";
	String strSelNH = "";
	String strSelNJ = "";
	String strSelNM = "";
	String strSelNV = "";
	String strSelNY = "";
	String strSelOH = "";
	String strSelOK = "";
	String strSelOR = "";
	String strSelPA = "";
	String strSelPR = "";
	String strSelRI = "";
	String strSelSC = "";
	String strSelSD = "";
	String strSelTN = "";
	String strSelTX = "";
	String strSelUS = "";
	String strSelUT = "";
	String strSelVA = "";
	String strSelVI = "";
	String strSelVT = "";
	String strSelWA = "";
	String strSelWI = "";
	String strSelWV = "";
	String strSelWY = "";	
	 
	if("".equalsIgnoreCase(strState))
		strSelALL = "selected";
	if("AK".equalsIgnoreCase(strState))
		strSelAK = "selected";
	if("AL".equalsIgnoreCase(strState))
		strSelAL = "selected";
	if("AR".equalsIgnoreCase(strState))
		strSelAR = "selected";
	if("AZ".equalsIgnoreCase(strState))
		strSelAZ = "selected";
	if("BC".equalsIgnoreCase(strState))
		strSelBC = "selected";
	if("CA".equalsIgnoreCase(strState))
		strSelCA = "selected";
	if("CO".equalsIgnoreCase(strState))
		strSelCO = "selected";
	if("CT".equalsIgnoreCase(strState))
		strSelCT = "selected";
	if("DC".equalsIgnoreCase(strState))
		strSelDC = "selected";
	if("DE".equalsIgnoreCase(strState))
		strSelDE = "selected";
	if("FL".equalsIgnoreCase(strState))
		strSelFL = "selected";
	if("GA".equalsIgnoreCase(strState))
		strSelGA = "selected";
	if("GM".equalsIgnoreCase(strState))
		strSelGM = "selected";
	if("HI".equalsIgnoreCase(strState))
		strSelHI = "selected";
	if("IA".equalsIgnoreCase(strState))
		strSelIA = "selected";
	if("ID".equalsIgnoreCase(strState))
		strSelID = "selected";
	if("IL".equalsIgnoreCase(strState))
		strSelIL = "selected";
	if("IN".equalsIgnoreCase(strState))
		strSelIN = "selected";
	if("KS".equalsIgnoreCase(strState))
		strSelKS = "selected";
	if("KY".equalsIgnoreCase(strState))
		strSelKY = "selected";
	if("LA".equalsIgnoreCase(strState))
		strSelLA = "selected";
	if("MA".equalsIgnoreCase(strState))
		strSelMA = "selected";
	if("MD".equalsIgnoreCase(strState))
		strSelMD = "selected";
	if("ME".equalsIgnoreCase(strState))
		strSelME = "selected";
	if("MI".equalsIgnoreCase(strState))
		strSelMI = "selected";
	if("MN".equalsIgnoreCase(strState))
		strSelMN = "selected";
	if("MO".equalsIgnoreCase(strState))
		strSelMO = "selected";
	if("MS".equalsIgnoreCase(strState))
		strSelMS = "selected";
	if("MT".equalsIgnoreCase(strState))
		strSelMT = "selected";
	if("NC".equalsIgnoreCase(strState))
		strSelNC = "selected";
	if("ND".equalsIgnoreCase(strState))
		strSelND = "selected";
	if("NE".equalsIgnoreCase(strState))
		strSelNE = "selected";
	if("NH".equalsIgnoreCase(strState))
		strSelNH = "selected";
	if("NJ".equalsIgnoreCase(strState))
		strSelNJ = "selected";
	if("NM".equalsIgnoreCase(strState))
		strSelNM = "selected";
	if("NV".equalsIgnoreCase(strState))
		strSelNV = "selected";
	if("NY".equalsIgnoreCase(strState))
		strSelNY = "selected";
	if("OH".equalsIgnoreCase(strState))
		strSelOH = "selected";
	if("OK".equalsIgnoreCase(strState))
		strSelOK = "selected";
	if("OR".equalsIgnoreCase(strState))
		strSelOR = "selected";
	if("PA".equalsIgnoreCase(strState))
		strSelPA = "selected";
	if("PR".equalsIgnoreCase(strState))
		strSelPR = "selected";
	if("RI".equalsIgnoreCase(strState))
		strSelRI = "selected";
	if("SC".equalsIgnoreCase(strState))
		strSelSC = "selected";
	if("SD".equalsIgnoreCase(strState))
		strSelSD = "selected";
	if("TN".equalsIgnoreCase(strState))
		strSelTN = "selected";
	if("TX".equalsIgnoreCase(strState))
		strSelTX = "selected";
	if("US".equalsIgnoreCase(strState))
		strSelUS = "selected";
	if("UT".equalsIgnoreCase(strState))
		strSelUT = "selected";
	if("VA".equalsIgnoreCase(strState))
		strSelVA = "selected";
	if("VI".equalsIgnoreCase(strState))
		strSelVI = "selected";
	if("VT".equalsIgnoreCase(strState))
		strSelVT = "selected";
	if("WA".equalsIgnoreCase(strState))
		strSelWA = "selected";
	if("WI".equalsIgnoreCase(strState))
		strSelWI = "selected";
	if("WV".equalsIgnoreCase(strState))
		strSelWV = "selected";
	if("WY".equalsIgnoreCase(strState))
		strSelWY = "selected";	
	
 %>
 
  <%@ include file="canon_E193_csMenuInc.jsp" %> 
  
 <script>	
 function validateZip(textfield)
 {
	resetErroMsg();
    var vChar;
	var zipOK = true;
	
	if((textfield.value.length < 5) && (textfield.value.length != 0))
	{
		displayErrorMsg("Zip codes must be exactly five characters!");
		textfield.focus();
		textfield.select();
		return false;
	}
	
	for (var i=0;i<textfield.value.length;i++)
     {
        vChar = textfield.value.charAt(i);
		
       	if ((vChar >= "0") && (vChar <= "9"))
			 continue;
		else
        {
           zipOK = false;
           break;
        }
     }
	 
	if (zipOK == false)
	{
		displayErrorMsg("Please enter a valid zipcode");
	 	textfield.focus();
		textfield.select();
	}
    return zipOK;
 }
 
 function action_func1()
 {
 	document.acctAddrForm.address1.value = document.acctAddrForm.address1.value.toUpperCase();
	document.acctAddrForm.address2.value = document.acctAddrForm.address2.value.toUpperCase();
	document.acctAddrForm.city.value = document.acctAddrForm.city.value.toUpperCase();
 	document.acctAddrForm.action = "canon_E193_csEIAddressController.jsp";
	document.acctAddrForm.submit();
 }
 
 </script>	
  <div id="main_content">
   	<div id="page_top">
   		<h1 style="padding-top:20px;" id="headerText">Customer Care - <%=strAccountAddressInformationT1 %></h1>
		<div class="breadcrumb"><%=hPath%></div>
	</div>
 	<form name="acctAddrForm" method="post" onsubmit="action_func1();">
	<!-- hidden variables -->

	<input type="hidden" name="origName" value="<%=request.getParameter("origName")%>" >
	<input type="hidden" name="origPhNo" value="<%=request.getParameter("origPhNo")%>">
	<input type="hidden" name="origEmailAddress" value="<%=request.getParameter("origEmailAddress")%>">
	<input type="hidden" name="origCheckbox" value="<%=request.getParameter("origCheckbox")%>">
	<input type="hidden" name="origType" value="<%=request.getParameter("origType")%>" >
	<input type="hidden" name="sourceType" value="<%=request.getParameter("sourceType")%>" >		
	<input type="hidden" name="custName" value="<%=request.getParameter("custName")%>" >
	<input type="hidden" name="custPhNo" value="<%=request.getParameter("custPhNo")%>">
	<input type="hidden" name="custEmailAddress" value="<%=request.getParameter("custEmailAddress")%>">
	
	<input type="hidden" name="recurProb" value="<%=request.getParameter("recurProb")%>">
	<input type="hidden" name="probType" value="<%=request.getParameter("probType")%>">
	<input type="hidden" name="val1" value="<%=request.getParameter("val1")%>">
	<input type="hidden" name="address1" value="">
	<input type="hidden" name="address2" value="">
	<input type="hidden" name="city" value="">
	
	<input type="hidden" name="hPageFrom" value="EIAcctAddress" >
	<input type="hidden" name="hPath" value="<%=hPath%>">
	<table class="request-service" style="align:center;width:40%;" cellpadding="5" cellspacing="0" border="0">
	
	<tr>
	<td>&nbsp;</td>		
      <td colspan="2"><font color="#FF0000"><b><%=strAccountAddressInformationN1%></b></font></td>
	</tr>
	<tr>
			    <td colspan="3">
			       <div id="errorWidget"  style="display: none; padding-bottom: 5px; color: red;">
						<p id="eMsg"></p>
				   </div>
			   </td>
			</tr>
    <tr>
		<td width="10"></td>	
		<td align="left" >
			<font class="promptReadOnly">State</font>
		</td>
		<td align="left" >
			<select name="state" size="1" class="searchBarLink">
				<option <%=strSelALL%> value="">ALL</option>
				<option <%=strSelAK%> value=AK>ALASKA</option>
				<option <%=strSelAL%> value=AL>ALABAMA</option>
				<option <%=strSelAR%> value=AR>ARKANSAS</option>
				<option <%=strSelAZ%> value=AZ>ARIZONA</option>
				<option <%=strSelBC%> value=BC>BRITISH COLUMBIA</option>
				<option <%=strSelCA%> value=CA>CALIFORNIA</option>
				<option <%=strSelCO%> value=CO>COLORADO</option>
				<option <%=strSelCT%> value=CT>CONNECTICUT</option>
				<option <%=strSelDC%> value=DC>DISTRICT OF COLUMBIA</option>
				<option <%=strSelDE%> value=DE>DELAWARE</option>
				<option <%=strSelFL%> value=FL>FLORIDA</option>
				<option <%=strSelGA%> value=GA>GEORGIA</option>
				<option <%=strSelGM%> value=GM>GUAM</option>
				<option <%=strSelHI%> value=HI>HAWAII</option>
				<option <%=strSelIA%> value=IA>IOWA</option>
				<option <%=strSelID%> value=ID>IDAHO</option>
				<option <%=strSelIL%> value=IL>ILLINOIS</option>
				<option <%=strSelIN%> value=IN>INDIANA</option>
				<option <%=strSelKS%> value=KS>KANSAS</option>
				<option <%=strSelKY%> value=KY>KENTUCKY</option>
				<option <%=strSelLA%> value=LA>LOUISIANA</option>
				<option <%=strSelMA%> value=MA>MASSACHUSETTS</option>
				<option <%=strSelMD%> value=MD>MARYLAND</option>
				<option <%=strSelME%> value=ME>MAINE</option>
				<option <%=strSelMI%> value=MI>MICHIGAN</option>
				<option <%=strSelMN%> value=MN>MINNESOTA</option>
				<option <%=strSelMO%> value=MO>MISSOURI</option>
				<option <%=strSelMS%> value=MS>MISSISSIPPI</option>
				<option <%=strSelMT%> value=MT>MONTANA</option>
				<option <%=strSelNC%> value=NC>NORTH CAROLINA</option>
				<option <%=strSelND%> value=ND>NORTH DAKOTA</option>
				<option <%=strSelNE%> value=NE>NEBRASKA</option>
				<option <%=strSelNH%> value=NH>NEW HAMPSHIRE</option>
				<option <%=strSelNJ%> value=NJ>NEW JERSEY</option>
				<option <%=strSelNM%> value=NM>NEW MEXICO</option>
				<option <%=strSelNV%> value=NV>NEVADA</option>
				<option <%=strSelNY%> value=NY>NEW YORK</option>
				<option <%=strSelOH%> value=OH>OHIO</option>
				<option <%=strSelOK%> value=OK>OKLAHOMA</option>
				<option <%=strSelOR%> value=OR>OREGON</option>
				<option <%=strSelPA%> value=PA>PENNSYLVANIA</option>
				<option <%=strSelPR%> value=PR>PUERTO RICO</option>
				<option <%=strSelRI%> value=RI>RHODE ISLAND</option>
				<option <%=strSelSC%> value=SC>SOUTH CAROLINA</option>
				<option <%=strSelSD%> value=SD>SOUTH DAKOTA</option>
				<option <%=strSelTN%> value=TN>TENNESSEE</option>
				<option <%=strSelTX%> value=TX>TEXAS</option>
				<option <%=strSelUS%> value=US>NATIONAL</option>
				<option <%=strSelUT%> value=UT>UTAH</option>
				<option <%=strSelVA%> value=VA>VIRGINIA</option>
				<option <%=strSelVI%> value=VI>UNITED STATES VIRGIN ISLANDS</option>
				<option <%=strSelVT%> value=VT>VERMONT</option>
				<option <%=strSelWA%> value=WA>WASHINGTON</option>
				<option <%=strSelWI%> value=WI>WISCONSIN</option>
				<option <%=strSelWV%> value=WV>WEST VIRGINIA</option>
				<option <%=strSelWY%> value=WY>WYOMING</option>
			</select>
		</td>
	</tr>
    <tr>
		<td width="10"></td>	
		<td align="left" >
			<font class="promptReadOnly">Zip Code</font>
		</td>
		<td align="left" >
			<input name="zipCode" type="text" value="<%=strZipCode%>" size="20" maxlength="5" class="txtbox" onChange="validateZip(this)">
		</td>
	</tr>		
			
			</table>
		</form>	
		<div style="text-align: center;">
			
					<a class="btn_red" href="javascript:history.back();">Prev</a> 						
					<a class="btn_red" style="margin-left: 10px;" href="javascript:action_func1();">Next</a> 						
						
			</div>
<%@ include file="canon_E193_csBottomInc.jsp" %>
</div>

<script>
var msg = '<%=request.getParameter("errorMsg")==null?"":request.getParameter("errorMsg")%>';
if(msg != '' && msg != 'null') {
	alert(msg);
}
</script>