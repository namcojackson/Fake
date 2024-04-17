<%@page import="com.canon.apps.servreq.beans.CanonE307SRMsgsBean"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServMsgDao"%>
<%@page language="java" %>
<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>
<%@page import="java.text.*" %>
<%@page import="java.sql.*" %>

 <style>
 #paging a{
 margin: 20px 5px 0px 5px;
 font-weight: bold;
 font-size: 11px;
 float: left;
 border: 1px solid #F6F6F6;
 padding: 2px 5px;
 color: #666666;
 text-decoration: underline;
}	
 .pageLinkBg {
	color : #FFFFFF;
	background-color : #999999;
}
.ascctablecell
{
    background-color: #aaaaaa;
	font-size: 14px;
	text-align: center;
	color: white;
	height: 25px;
	font-weight: bold

}

.ascctable td, th {
    border: 1px solid black;
}

.ascctable {
    border-collapse: collapse;
}
body {
  margin: 0px;
  font-family: arial;
  font-size: +12;
}
 </style>

<script language="javascript">

</script>
<table width="100%">
<tr>
<td>
<%
  
		CanonE307ServMsgDao objLevelDAO = new CanonE307ServMsgDao();
        String mName = request.getParameter("modelName"); 
    	String strPartyNumber = request.getParameter("level");
    	String strPartyName = request.getParameter("strPartyName");
		String rowIndex = request.getParameter("rowIndex");
    	String levelDropdownValue = request.getParameter("levelDropdownValue");
    	//System.out.println("rowIndex::"+rowIndex); 
    	if(strPartyName==null||strPartyName.equalsIgnoreCase("undefined"))
    		strPartyName="";
    	else
    		strPartyName=strPartyName.trim();
    	if(strPartyNumber==null||strPartyNumber.equalsIgnoreCase("undefined"))
    		strPartyNumber="";
    	else
    		strPartyNumber=strPartyNumber.trim();
    	//System.out.println("strPartyName:"+strPartyName);
    	//System.out.println("strPartyNumber:"+strPartyNumber);
		int pageNum =1; 		
		String strPageNum =request.getParameter("pageNum");
		
		if(strPageNum!=null){
			 try{	
			   pageNum = Integer.parseInt(strPageNum);
			 }catch(Exception e){
			   System.out.println("Page Num. parse error : "+e.getMessage());
			   pageNum =1;
			 }
		}	
		
		
		int ct=10; // number of Records per page 
		int start = ((pageNum-1)*ct) + 1 ;
		int end= pageNum*ct;
		int totalLinks =0;
		
		String system =null;
		String sortBy ="MODEL_NAME ASC";
		
		ArrayList arList  =objLevelDAO.getPartyNumberLOV(strPartyName,strPartyNumber,start, end);

		
 %>
 
 	<TABLE cellspacing="0" cellpadding="4" width="40%" summary="" align="center">	
		<tr>	
			<td nowrap align="right" class="search_text" width="30%">Party Name</td>
			<td>
				<input style="border: solid 1px #336699"  type="text" name="strLevelName" id="strLevelName" value="<%=strPartyName==null?"":strPartyName%>" />
		    </td>
			<td nowrap align="right" class="search_text" width="30%">Party Number</td>
			<td>
				<input style="border: solid 1px #336699"  type="text" name="strLevel" id="strLevel" value="<%=strPartyNumber==null?"":strPartyNumber%>" />
		    </td>
			<td nowrap align="left" width="20%">
				<a href="javascript:searchParty('1')" class="btn">Search</a>
			</td>
		</tr>
	</TABLE>
            <br/>
 <%           		
		
		if(arList.size()>1){ 
		
			int totalRecords=Integer.parseInt(""+arList.get(0));
			arList.remove(0); // removing total Records param from list
			totalLinks =(totalRecords%ct>0)?((totalRecords/ct)+1):totalRecords/ct;
			//System.out.println("TotalLinks::"+totalLinks);
			String fnGetParty="searchParty";
			
			String pageLinkMsg="";
    	   
    	    if(totalLinks>1){
    	    	  pageLinkMsg= start+" to "+(end-(ct-arList.size())) +" of "+ totalRecords +" records found.";
    	    }
    	   
            %>
            <table width="100%" >
             <tr><td align="right" style="text-align: right;font-size: 11px;font-weight: bold;"><%=pageLinkMsg %></td></tr>
            </table>
            <table class="supplies-table" style="width: 98%;"  cellpadding="1" cellspacing="1" style="background-color:#cccc99" id="prtyTbl">
            <thead>             
               <tr >
				<th width="8%"> </th>
				<th width="45%">Party Name</th>
				<th width="30%">Party Number</th>
			 </tr>
             </thead> 
             <thead>
		            <%
			         for(int i=0;i<arList.size();i++){
			        	 CanonE307SRMsgsBean partyBeanObj = (CanonE307SRMsgsBean) arList.get(i);
			 			String strParty = partyBeanObj.partyName;
			 			String strPartyNo = partyBeanObj.partyNumber;
			 			
					%>
			   	      <tr>
			   	       <td >
			   	            <input type="radio" class="chk" name="rMdl" />
			   	          </td>			   	         
			   	            <td><%=strParty %></td>
			   	            <td><%=strPartyNo %></td>							
			   	      </tr>
			   		<%
			         }
			        %>
			    </thead>
            </table>	   
            </td>
		   </tr>
	   <tr>
		     <td>
				 <table width="100%" style="font-size: 11px;" id="mdlLinks">
			      <tr>
					  <td align="left">
					         <jsp:include page="canonE307MsgPaginationIncl.jsp">
							   <jsp:param value="<%=totalLinks %>" name="totalLinks"/>
							   <jsp:param value="<%=pageNum %>" name="pageNum"/>
							   <jsp:param value="<%=fnGetParty %>" name="fn"/>
							   </jsp:include>
					  </td>
				   </tr>
			 	  </table>
		     </td>
		   </tr>
		</table>		   
		  <% 
		}else{
	     %>
	       <p style="font-size: 11px;font-weight: bold;">No records found for this search criteria.</p>
	     <%		
		  }
          %>
   		  <table style="width: 100%;align:right;">
			  <tr align="right">
			 	  <td><a href="javascript:fnCloseSearchDlg()" class="btn" style="white-space: nowrap;">Close</a></td>
			 </tr>
		   </table>             
<script>
$(function (){
	
	$(".chk").click (function (){
	  	    var tr=$(this).closest("tr");
		    var mdlVal= $(tr.children()[2]).html();
		    var rowcount=<%=rowIndex%>
		    var table = document.getElementById('itemAdd'),rows = table.getElementsByTagName('tr'), cells
		    table.rows[rowcount].cells[1].children[0].value=mdlVal;
			//console.log("mdl"+mdlVal);
		    $(modalName).dialog("close"); 			
	});

});
function searchParty(pageNum) {
//alert("searchModel:pageNum:"+pageNum);
	var strLevel = customEncodeURIComponent($("#strLevel").val());
	 var partyname= customEncodeURIComponent$("#strLevelName").val());
	 var rowcount=<%=rowIndex%>
	    //console.log("rowcount:searchRegion"+rowcount);
  modalName = "#levelSearchLovDiv";
	var qryStr = "modalName=" + modalName + "&pageNum=" + pageNum + "&level=" + strLevel + "&rowIndex=" + rowcount+"&strPartyName="+partyname;
	showPleaseWait();
	$.ajax( {
		url : "canonE307SerPartyLov.jsp",
		data : qryStr,
		type : "POST",
		cache : false,
		success : function(data) {
			hidePleaseWait();
			$(modalName).html("");
			$(modalName).html(data);
			setRowStyles("#regionTbl");
			$("#mdlLinks #paging #a" + pageNum).addClass("pageLinkBg").css( {
				"color" : "#FFFFFF"
			});
	
		}
	});
	$(modalName).dialog("open");
	$(".ui-dialog").css( {
		"top" : "100px"
	});
}
function customEncodeURIComponent(str) {
    return encodeURIComponent(str).replace(/'/g, '%60');
}
function showPleaseWait() {
	$.blockUI( {
		css : {
			border : 'none',
			padding : '15px',
			backgroundColor : '#000',
			'-webkit-border-radius' : '10px',
			'-moz-border-radius' : '10px',
			opacity : .5,
			color : '#fff',
			cursor : 'default'
		},
		overlayCSS : {
			cursor : 'default'
		},
		baseZ : 9000
	});
}

function hidePleaseWait() {
	$.unblockUI();
}

function setRowStyles(tbl) {
	$(tbl + " tbody tr:odd").each(function() {
		$(this).addClass("evenRow");
	});
}

</script>
