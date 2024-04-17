	
	
	<% String ctxPath = request.getContextPath();  %>
	
	
	<html>
	
	<head>
	 <title> Create Customer Care Ticket</title>
	 
	 <script src="<%=ctxPath%>/common/jquery/jquery-1.10.2.min.js" type='text/javascript'></script>   		
	
	
	<script type="text/javascript">
	function createTicket(){
	 $("#msg").html("Creating Ticket");
     var url="canonCustomerCareUtil.jsp";
     var qryStr=$("#ccForm").serialize();
		
			$.ajax( {
				url : url,
				cache : false,
				type : "POST",
				data : qryStr,
				success : function(data) {
					var res="";
					
					 res=data;
					 if ( $.trim(res).length>0 )
					 $("#msg").html(data);
					 else
					  $("#msg").html("Error occured while creating ticket.  Please try again.");
					 
		 	 }
	  	});
	}
	
	
	
	
	
	</script>
	</head>
		
	
	<p id="msg"></p> <br>
	
	<form id="ccForm">
		 <b> Header Parameters </b><br>
		 Customer Name   <input type="text"  name="customerName"  value="CBS EAST, INC." /><br>
		 Customer Num    <input type="text"  name="customerNum"  value="545281" /><br>
		 Cust Acct Id    <input type="text"  name="custAcctId"  value="545281" /><br>
		 Billing Issue   <input type="text"  name="billingIssue"  value="Y" /><br>
		 Recurring      <input type="text"  name="recurring"  value="N" /><br>
		 CatId Desc      <input type="text"  name="catIdDesc"  value="Contract invoice - other" /><br>
		 Invoice Num      <input type="text"  name="invoiceNum"  value="1000140" /><br>
		 Contract Num      <input type="text"  name="contractNum"  value="404881" /><br>
		 Contract Modifier      <input type="text"  name="contractModifier"  value="TE00000003" /><br>
		 OrigName      <input type="text"  name="origName"  value="Test,Test" /><br>
		 OrigPhNum      <input type="text"  name="origPhNum"  value="0001110000" /><br>
		 OrigEmail      <input type="text"  name="origEmail"  value="test@canon_jamesburg.com" /><br>
		 Cust Contact      <input type="text"  name="custContact"  value="Test,Test2" /><br>
		 Cust Phone Num      <input type="text"  name="custPhNum"  value="0001110002" /><br>
		 Cust Email      <input type="text"  name="custEmail"  value="test_2@canon_jamesburg.com" /><br>
		 Orig. Type      <input type="text"  name="origType"  value="customer" /><br>
		 Header CreatedBy      <input type="text"  name="hdrCreatedBy"  value="79004" /><br>
		 Created By ResId      <input type="text"  name="createdByResId"  value="102041548" /><br>
		 Attribute9 ( source )      <input type="text"  name="source"  value="SERVICE CONTRACT" /><br>
	
		<b> line parameters  </b><br>
		
		 Severity    <input type="text"  name="serverity"  value="NORMAL" /><br>
		 Reason Code    <input type="text"  name="reasonCd"  value="CSR_E193_CONTRACTS" /><br>
		 Reason       <input type="text"  name="reason"  value="OTHER -- CONTRACT ISSUES" /><br>
		 Line CreatedBy   <input type="text"  name="lnCreatedBy"  value="79004" /><br>
	
		
		<b> notes  </b><br>
	     Notes    <input type="text"  name="notes"  value="Test Notes From S21 API" /><br>
	    <input type="button"  onclick="createTicket();"  value="Create Ticket"  /><br>
	     
	</form>
	
	
	</html>