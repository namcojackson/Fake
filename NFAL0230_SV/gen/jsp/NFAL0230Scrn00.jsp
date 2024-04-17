<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160810123752 --%>
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
			<input type="hidden" name="pageID" value="NFAL0230Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="COA Hierarchy View">


<%-- include S21BusinessProcessTAB --%>
<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

<center>
	<table border="1" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%"><li title = "View Hierarchy" class="pTab_ON" ><a href="#">View Hierarchy</a></li></td>
								<td valign="bottom" align="center"><a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a></td>
								<td valign="bottom" align="center"><a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a></td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
					<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
						<col width="1054">
						<tr height="24">
							<td align="center" class="pClothBs">Parent - Child Hierarchy</td>
						</tr>
					</table>
<%-- ########## Hierarchy ########## --%>
					<div style="overflow-x:hidden; overflow-y:scroll; width:1074;height:540" id="treeViewer" onScroll=" getScrollFunction();">
						<%@ page import="business.servlet.NFAL0230.NFAL0230Bean" %>
						<% if( ((NFAL0230Bean)databean).getTreeView() != null ) { %>
						<uji:treeView
							bean		= "${__screenName__}"
							indentIcon	= "./img/treeView/child.gif;./img/treeView/lastchild.gif;./img/treeView/hasmorechild.gif;./img/treeView/nomorechild.gif;"
							insets		= "0"
							cellSpacing	= "0"
							background	= "#FFFFFF"
							stripeBackground	= "#D7E2E2"
							css		= "pTreeView"
							noWrap		= "true"
							borderWidth	= "1"
							ruleWidth	= "1"
							rules		= "all"
							dataEscape	= "false;"
							property			= "treeView"
							dataCellType		= "data"
							columnWidth		= "1054;"
							dataAlignmentHorizontal	= ""
						/>
						<% } %>
					</div>
				
				</div>
			</td>
		</tr>
	</table>
</center>

<% if( ((NFAL0230Bean)databean).getTreeView() != null ) { %>

<%-- include S21TreeView.jsp --%>
<%@ page import="business.servlet.NFAL0230.NFAL0230Bean" %>
<jsp:include page="../treeView/S21TreeView.jsp">
	<jsp:param name="treeView" value="<%= ((NFAL0230Bean)databean).getTreeView().isDisableAllFields() %>" />
	<jsp:param name="TreeViewPropertyNameList" value="treeView" />
	<jsp:param name="isFocusTreeView" value="<%= ((NFAL0230Bean)databean).getTreeView().isSetFocusTreeView() %>" />
	<jsp:param name="setFocusTreeViewName" value="treeView" />
</jsp:include>

<% } %>

<%-- for Scroll Rock --%>
<ezf:inputHidden name="xxListNum" ezfName="xxListNum" value="0" otherAttr=" id=\"xxListNum\"" />
<%-- ###SCRIPT --%>
<script type="text/javascript">
	function getScrollFunction() {
		var treeViewer   = document.getElementById('treeViewer');
		var xxListNum    = document.getElementById('xxListNum');
		xxListNum.value  = treeViewer.scrollTop;
	}
	setScrollFunction();
	function setScrollFunction() {
		var xxListNum           = document.getElementById('xxListNum');
		var treeViewer          = document.getElementById('treeViewer');
		treeViewer.scrollTop  = parseInt(xxListNum.value);
	}

</script>




<%-- **** Task specific area ends here **** --%>
