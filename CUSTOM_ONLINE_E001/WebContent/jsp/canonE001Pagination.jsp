
<div id="pagination">
<%
String sTotalLinks =request.getParameter("totalLinks");
String sPageNum =request.getParameter("pageNum");
int totalLinks = (sTotalLinks!=null)?Integer.parseInt(sTotalLinks):0; 
int pageNum = (sPageNum!=null)?Integer.parseInt(sPageNum):1; 

int nop=totalLinks;
 
 if(nop==0){ // no rows
	 
 }else if(0<nop  && nop<=1){
	 
	 
 }else if(nop>1){

	 if(nop>10 ){
	  
		 if(pageNum>2){
			 
				%>
				 <a class="pageNum" href="#" data-page="1">First</a>
				<% 
			 }
		 if(pageNum>1){
			%>
			 <a  class="pageNum" href="#" data-page="<%=(pageNum-1)%>"> Prev</a>
			<% 
		 }
		 int temp=pageNum;
		 if(temp>5)
		  pageNum-=5;
		
		 for(int k=pageNum;k<(pageNum+10) && k<=totalLinks ;k++){
		  %>
		   <a class="pageNum" id="page<%=k %>" href="#" data-page="<%=k %>"><%=k %></a>
 		  <% 
	     }
		 if(temp>5)
			  pageNum+=5;
		 
		 if( (pageNum+1) <= nop){
			 %>
			  <a class="pageNum" href="#" data-page="<%= (pageNum+1) %>">Next</a>
			 <%
		 }
		 %>
		  <a class="pageNum" href="#" data-page="<%= nop %>">Last</a>
		 <%
	 }
	 
	 if(nop<=10){
		 for(int k=1;k<=nop;k++){
			%>
			 <a class="pageNum" id="page<%=k %>" id="page<%=k %>" href="#" data-page="<%= k %>"><%= k %></a>
			<% 
			 
		 }
	 }
 }else{
	 
 }
 
 %>
</div>