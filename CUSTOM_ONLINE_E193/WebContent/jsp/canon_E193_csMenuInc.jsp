<!-- $Header: ITG# 74988 canon_E193_csMenuInc.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csMenuInc.jsp - Canon Logo, Tabs and subtabs    
 |   
 | DESCRIPTION
 |   Displays the top links, tabs, subtabs and the search bar
 |   This page is mainly for rendering.  Information and data of
 |   top links and tabs are gotten in ibeCZzdMenu.jsp and they are passed
 |   to this page as pageContext REQUEST SCOPE
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	07/07/2005
 |
 | HISTORY
 | DATE	       WHO		 WHY
 | 30-Nov-06   Vikas Basal       ITG# 74988 CFS Changes
 | 23-Aug-13   Hema Doniparthi	 ITG#361257.
 |
 +=======================================================================--%>

<%@page import="com.canon.oracle.custapp.custinq.beans.*" %>
<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="java.lang.*"%>
<%@page import="java.util.*"%> 
<!-- <link href="/print.css" rel="stylesheet" media="print" type="text/css" /> -->
<%-- <%@ include file="canon_E193_csTopInc.jsp"%>  --%>
<script type="text/javascript">
// Newly Added.
function saveTicket() {
    alert("Before Leaving the current page please save the ticket first and then Call Wrap Up");
}
function addLoadEvent(func)
{
  var oldonload = window.onload;
  if (typeof window.onload != 'function')
  {
    window.onload = func;
  } 
}
addLoadEvent(fNewPDFWindows);

function fNewPDFWindows ()
{
  if (!document.getElementsByTagName) return false;
  var links = document.getElementsByTagName("a");
  for (var eleLink=0; eleLink < links.length; eleLink ++) {
    if (links[eleLink].href.indexOf('.pdf') !== -1) {
      links[eleLink].onclick =
        function() {
          window.open(this.href,'resizable,scrollbars');
          return false;
        }
     // var img = document.createElement("img");
     // img.setAttribute("src", "i/new-win-icon.gif");
    //  img.setAttribute("alt", "(opens in a new window)");
    //  links[eleLink].appendChild(img);
    }
  }
} 
</script>
<%
System.out.println("in menu page");
	// Main Menu Variables
	boolean bIsSelected = false;
	boolean bIsSubMenu = false;
	
	//Sub Menu Variables
	boolean bIsSubMenuSelected = true;

	// Object Variables to retrieve sbMainMenu and submenus from menu-bean and wrapper classes
	Canon_E193_RenderMenu objMenu = new Canon_E193_RenderMenu();
	//Canon_E193_SessionObj sessObj = new Canon_E193_SessionObj();
	//Objects for Main Menu
	ArrayList<Canon_E193_MainMenuObj> alLocalMainMenu = new ArrayList<Canon_E193_MainMenuObj>();
	Canon_E193_MainMenuObj objLocalMainMenu = new Canon_E193_MainMenuObj();
	
	//Objects for Main Menu
	ArrayList<Canon_E193_SubMenuObj> alLocalSubMenu = new ArrayList<Canon_E193_SubMenuObj>();
	Canon_E193_SubMenuObj objLocalSubMenu = new Canon_E193_SubMenuObj();
	Canon_E193_QuickTicketDAO objQTRole = new Canon_E193_QuickTicketDAO();
	
	//Index Variables 
	int iIdx = 0;
	int iIdx2=0;

	//Menu Buffers
	StringBuffer sbMainMenu = new StringBuffer();
	StringBuffer sbSubMenu = new StringBuffer();
	StringBuffer sbHomeMenu = new StringBuffer();
	
	// Constant Variable
	//String constCiHome = "CI_HOME";
	String constCiHome = "CSR_HOME";
	String constCiWorkbench = "Workbench";
	String strPrompt = "";
    
/* ITG# 74988 - Begin */
        String constCiCFSCare = "CFS Cost Per Copy - Care";
        System.out.println("Menu Include - Access Flag - " + objCiSession.getCFSAccessFlag());
        System.out.println("- User Flag - " + objCiSession.getCFSUserFlag());
        System.out.println("resource id is " + objCiSession.getResourceId());
        System.out.println("resp id is " + objCiSession.getRespId() + " app id " + objCiSession.getApplId());
/* ITG# 74988 - End */
%>
	
<%	
	// Get arraylist Menu Object
	//try{
	alLocalMainMenu = objMenu.getMenuInfo(objCiSession.getRespId(),objCiSession.getApplId());
	 //}catch(Exception e){
	//	  	System.out.println("ERROR : "+e.getMessage().toString());
	 //}
System.out.println("alLocalMainMenu : " + alLocalMainMenu + "respId1..: " + objCiSession.getRespId());
	String role = objQTRole.getCustomerServiceGroup(objCiSession.getResourceId());
	System.out.println("role : " + role + "respId1..: " + objCiSession.getRespId());
	//loop through the arraylist to retrieve individual objects.  onclick="return false"
	for(iIdx=0;iIdx < alLocalMainMenu.size();iIdx++)
	{}

			
%>
<div class="logo-img">
		<!--utility navigation start-->	
		<div class="utility-nav">
  			<%-- <div style="float:right;"><a href="#" onclick="javascript:window.print()">Print</a><a href="<%=ctxPath%>/common/images/PDF/Customer Care Manual_Revised_2.pdf">Help</a><a href="<%=ctxPath%>/e193/jsp/canon_E193_csCFSIssueCapture.jsp" >Home</a></div> --%>
  			<div style="float:right;"><a href="<%=ctxPath%>/e193/images/pdf/Customer Care Manual_Revised_2.pdf">Help</a>
  			<%if( ("Y".equals(objCiSession.getCFSAccessFlag())) && ("Y".equals(objCiSession.getCFSUserFlag())) ) { %>
				<a href="<%=ctxPath%>/e193/jsp/canon_E193_csCFSIssueCapture.jsp" >Home</a>
			<% } else { %>
  				<a href="<%=ctxPath%>/e193/jsp/canon_E193_csEIHome.jsp" >Home</a>
  			<% } %>
  			</div>
		</div>
		<!--utility navigation end-->
		<div class="logo"><a href="#"><img src="<%=ctxPath%>/common/images/csa_logo.jpg" width="210" height="98" alt="Canon Solutons America" title="Canon Solutons America" /></a></div>
		<!--main navigation start-->
	  <nav id="nav1">
				<% 
				
				String strOwnedValue = request.getParameter("ownedYesNo");
				 
				
					String activeClass="";
					for(iIdx=0;iIdx < alLocalMainMenu.size();iIdx++){
						objLocalMainMenu = alLocalMainMenu.get(iIdx);
						System.out.println("strPageName:"+strPageName+" strPrompt:"+strPrompt);
						strPrompt=objLocalMainMenu.getPromptName();
						if(strPageName.equalsIgnoreCase(strPrompt)) 
						{
							activeClass="class=\'active\'";
						}
						else
							activeClass="";
						
				%>
				<% if(isActivePage){
					System.out.println("Save Ticket True");
					// alert("Before Leaving current page please save the ticket first /n Call Wrap Up");
					// "pointer-events: none; display: inline-block; cursor: default;"
				%>
				<% if(iIdx==alLocalMainMenu.size()-1){%>
				<a id="<%=iIdx%>" <%=activeClass %> style="color: #000000;" href="#" onclick="saveTicket();"><%=objLocalMainMenu.getPromptName()%></a>
				<%}else{ %>	
				<a id="<%=iIdx%>" <%=activeClass %> style="color: #000000;" href="#" onclick="saveTicket();"><%=objLocalMainMenu.getPromptName()%></a>|
				<%} %>	
					<%}else{ 
					System.out.println("Save Ticket false");%>
				<% if(iIdx==alLocalMainMenu.size()-1){%>
				<a id="<%=iIdx%>" <%=activeClass %> style="color: #000000;" href="<%=objLocalMainMenu.getUrlName()%>"><%=objLocalMainMenu.getPromptName()%></a>
				<%}else{ %>	
				<a id="<%=iIdx%>" <%=activeClass %> style="color: #000000;" href="<%=objLocalMainMenu.getUrlName()%>"><%=objLocalMainMenu.getPromptName()%></a>|
				<%} %>	 
					<%}%>
			<%} %>
			</nav>
			<% 
				String subActiveClass="class=\'active\'";
				if(objLocalMainMenu.getSubMenuID() != "") 
					bIsSubMenu = true; 
				 else 
					bIsSubMenu = false;
				if(strPageName.equals(constCiWorkbench))
				{
					%>
					<nav id="nav2">	
					<%
					if("OWNED".equalsIgnoreCase(strOwnedValue))
					{
						if(strSubMenuPageName.equals("Owned")) 
						{
					%>
							<a id="ownedWorkbench" <%=subActiveClass %> style="color: #000000" href="canon_E193_csWorkbenchOwned.jsp?ownedYesNo=OWNED">Owned</a>|
							<a id="assignedWorkbench"  style="color: #000000" href="canon_E193_csWorkbenchAssigned.jsp?ownedYesNo=OWNED">Assigned</a>|
							<a id="createdWorkbench"  style="color: #000000" href="canon_E193_csWorkbenchCreated.jsp?ownedYesNo=OWNED">Created</a>
					<%						
						}
						else if(strSubMenuPageName.equals("Assigned"))
						{
					%>	
							<a id="ownedWorkbench"  style="color: #000000" href="canon_E193_csWorkbenchOwned.jsp?ownedYesNo=OWNED">Owned</a>|
							<a id="assignedWorkbench" <%=subActiveClass %> style="color: #000000" href="canon_E193_csWorkbenchAssigned.jsp?ownedYesNo=OWNED">Assigned</a>|
							<a id="createdWorkbench"  style="color: #000000" href="canon_E193_csWorkbenchCreated.jsp?ownedYesNo=OWNED">Created</a>
					<%					
						}
						else 
						{  
					%>
							<a id="ownedWorkbench"  style="color: #000000" href="canon_E193_csWorkbenchOwned.jsp?ownedYesNo=OWNED">Owned</a>|
					  		<a id="assignedWorkbench"  style="color: #000000" href="canon_E193_csWorkbenchAssigned.jsp?ownedYesNo=OWNED">Assigned</a>|
					 		<a id="createdWorkbench" <%=subActiveClass %> style="color: #000000" href="canon_E193_csWorkbenchCreated.jsp?ownedYesNo=OWNED">Created</a>
					<%	}
					}
					else if("ASSIGNED".equalsIgnoreCase(strOwnedValue))
					{
						if(strSubMenuPageName.equals("Assigned"))
						{					
					%>
							<a id="assignedWorkbench" <%=subActiveClass %> style="color: #000000" href="canon_E193_csWorkbenchAssigned.jsp?ownedYesNo=ASSIGNED">Assigned</a>|
					  		<a id="createdWorkbench" style="color: #000000" href="canon_E193_csWorkbenchCreated.jsp?ownedYesNo=ASSIGNED">Created</a>
					<%	
						}
						else 
						{
					%>
							<a id="assignedWorkbench" style="color: #000000" href="canon_E193_csWorkbenchAssigned.jsp?ownedYesNo=ASSIGNED">Assigned</a>|
					  		<a id="createdWorkbench" <%=subActiveClass %> style="color: #000000" href="canon_E193_csWorkbenchCreated.jsp?ownedYesNo=ASSIGNED">Created</a>
					<%
						}
					}
					%>
					 </nav>
				<%	
				}
		%>
		<!--main navigation end-->
</div>
