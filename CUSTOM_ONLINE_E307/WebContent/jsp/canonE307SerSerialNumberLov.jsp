<%@page import="com.canon.apps.servreq.beans.CanonE307SRMsgsBean"%>
<%@page import="com.canon.apps.servreq.dao.CanonE307ServMsgDao"%>
<%@page language="java" %>
<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>
<%@page import="java.text.*" %>
<%@page import="java.sql.*" %>


 <head>
  <title> Level Search</title>
 </head> 
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
	background-color :  #999999;
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
 </style>

<script language="javascript">

</script>
<table width="100%">
<tr>
<td>
<%
  
		CanonE307ServMsgDao objLevelDAO = new CanonE307ServMsgDao();
        String mName = request.getParameter("modelName"); 
    	String strSerialNumber = request.getParameter("level");
    	String rowIndex = request.getParameter("rowIndex");
    	String levelDropdownValue = request.getParameter("levelDropdownValue");
    	//System.out.println("rowIndex::"+rowIndex); 
    	if(strSerialNumber==null||strSerialNumber.equalsIgnoreCase("undefined"))
    		strSerialNumber="";
    	else
    		strSerialNumber=strSerialNumber.trim();	
    	//System.out.println("strSerialNumber:"+strSerialNumber);
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
		
		ArrayList arList  =objLevelDAO.getSerialLOV(strSerialNumber,start, end);

		
 %>
 
    <TABLE cellspacing="0" cellpadding="4" width="40%" summary="" align="center">	
		<tr>	
			<td nowrap align="right" class="search_text" width="30%">Serial Number</td>
			<td>
				<input style="border: solid 1px #336699"  type="text" name="strLevel" id="strLevel" value="<%=strSerialNumber==null?"":strSerialNumber%>" />
		    </td>
			<td nowrap align="left" width="20%">
				<a href="javascript:searchSerialNumber('1')" class="btn">Search</a>
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
			String fnGetSerialNumber="searchSerialNumber";
			
			String pageLinkMsg="";
    	   
    	    if(totalLinks>1){
    	    	  pageLinkMsg= start+" to "+(end-(ct-arList.size())) +" of "+ totalRecords +" records found.";
    	    }
    	   
            %>
            <table width="100%" >
             <tr><td align="right" style="text-align: right;font-size: 11px;font-weight: bold;"><%=pageLinkMsg %></td></tr>
            </table>
   			<table class="supplies-table" style="width: 98%;"  cellpadding="1" cellspacing="1" style="background-color:#cccc99" id="regionTbl">             
               <thead>
	               <tr >
					<th  width="8%">Select </th>
					<th  width="20%" nowrap>Serial Number</th>
					<th  width="30%" nowrap>Party Name</th>
					<th  width="45%">Address</th>
				   </tr>
			   </thead>
			   <tbody>
		            <%
			         for(int i=0;i<arList.size();i++){
			        	CanonE307SRMsgsBean serialBeanObj = (CanonE307SRMsgsBean) arList.get(i);
			 			String strserial = serialBeanObj.serialNumber;
			 			String strextReference = serialBeanObj.extReference;
			 			String strpartyName = serialBeanObj.partyName;
			 			String straddress = serialBeanObj.address;

					%>
			   	      <tr >
			   	       <td >
			   	            <input type="radio" class="chk" name="rMdl" />
			   	          </td>			   	         
			   	            <td><%=strserial %></td>
			   	            <td><%=strpartyName %></td>	
			   	            <td><%=straddress %></td>				   	          	   	       
			   	      </tr>
			   	      
			   		<%
			         }
			        %>
			    </tbody>
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
							   <jsp:param value="<%=fnGetSerialNumber %>" name="fn"/>
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
		    var mdlVal= $(tr.children()[1]).html();
		    var rowcount=<%=rowIndex%>
		    var table = document.getElementById('itemAdd'),rows = table.getElementsByTagName('tr'), cells
		    table.rows[rowcount].cells[1].children[0].value=mdlVal;
		    $(modalName).dialog("close"); 			
	});
});
function searchSerialNumber(pageNum) {
//alert("searchModel:pageNum:"+pageNum);
	var strLevel = customEncodeURIComponent($("#strLevel").val());
	var rowcount=<%=rowIndex%>	 
	// console.log("rowcount:searchRegion"+rowcount);
     modalName = "#levelSearchLovDiv";
	var qryStr = "modalName=" + modalName + "&pageNum=" + pageNum + "&level=" + strLevel + "&rowIndex=" + rowcount;
	showPleaseWait();
	$.ajax( {
		url : "canonE307SerSerialNumberLov.jsp",
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
