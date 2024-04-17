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
<script type="text/javascript">
// Newly Added.

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
     // img.setAttribute("alt", "(opens in a new window)");
     // links[eleLink].appendChild(img);
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
        System.out.println("Menu Include - Access Flag - ");
        System.out.println(objCiSession.getCFSAccessFlag());
        System.out.println("- User Flag -");
        System.out.println(objCiSession.getCFSUserFlag());
        System.out.println("resource id is " + objCiSession.getResourceId());
        System.out.println("resp id is " + objCiSession.getRespId() + "app id" + objCiSession.getApplId());
/* ITG# 74988 - End */
%>
	
<%	
	// Get arraylist Menu Object
	//try{
	alLocalMainMenu = objMenu.getMenuAndSubMenuInfo(objCiSession.getRespId(),objCiSession.getApplId());
	 //}catch(Exception e){
	//	  	System.out.println("ERROR : "+e.getMessage().toString());
	 //}
System.out.println("alLocalMainMenu : " + alLocalMainMenu + "respId1..: " + objCiSession.getRespId());
	String role = objQTRole.getCustomerServiceGroup(objCiSession.getResourceId());
	System.out.println("role : " + role + "respId1..: " + objCiSession.getRespId());
	//loop through the arraylist to retrieve individual objects.
				
%>


<div class="logo-img">
		<!--utility navigation start-->	
		<div id="div_print">
       <div id="header" style="background-color:white;"></div>
       <div id="footer" style="background-color:white;"></div>
       </div>		
 		<div class="utility-nav">
  			 <!-- <div><a href="#" onclick="javascript:window.print()">Print</a><a href="/s21extn/common/images/PDF/Customer Care Manual_Revised_2.pdf">Help</a><a class="searchBarLink" href="canon_E193_csLogout.jsp">Logout</a><a href="/e193/jsp/canon_E193_csCFSIssueCapture.jsp" >Home</a></div> -->
		    <div><a href="<%=ctxPath%>/e193/images/pdf/Customer Care Manual_Revised_2.pdf">Help</a><a class="searchBarLink" href="canon_E193_csLogout.jsp">Logout</a><a href="/e193/jsp/canon_E193_csEIHome.jsp" >Home</a></div>
		</div>
		<!--utility navigation end-->
		<div class="logo"><a href="#"><img src="<%=ctxPath%>/common/images/csa_logo.jpg" width="210" height="98" alt="Canon Solutons America" title="Canon Solutons America" /></a></div>
		<!--main navigation start-->
	  <nav>
				<% 
					for(iIdx=0;iIdx < alLocalMainMenu.size();iIdx++){
						objLocalMainMenu = alLocalMainMenu.get(iIdx);
						
				%>
				<a id="<%=iIdx%>" style="color: #000000" href="<%=objLocalMainMenu.getUrlName()%>"><%=objLocalMainMenu.getPromptName()%></a>|	
					
				<% }%>		
			
			</nav>
		<!--main navigation end-->
</div>
