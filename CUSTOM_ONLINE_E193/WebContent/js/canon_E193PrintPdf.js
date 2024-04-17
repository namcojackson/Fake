function InvoiceDetailsPDF(val1) {
		/*var vLink = "canonE193GeneratePDF.jsp?InvNo="+val1;
		  var vWin = window.open(vLink, "newwin","height=300,width=900,toolbar=no,menubar=no,scrollbars=yes,resizable=yes" );
		  vWin.focus();
	    */
		
		var url="canonE193GeneratePDF.jsp?InvNo="+val1;
		var l_newWindow = window.open(url);
		l_newWindow.focus();
	}