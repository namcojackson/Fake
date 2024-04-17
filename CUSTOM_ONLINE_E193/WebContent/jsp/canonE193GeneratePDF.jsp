<%@page import="com.canon.oracle.custapp.custinq.dao.*" %>
<%@page import="java.io.OutputStream"%>
<%@page import= "java.io.FileNotFoundException"%>
<%@page import= "java.io.IOException"%>

<%
   
    try {
        String ittNumber= request.getParameter("InvNo");
        String type="pdf";
           
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename= " +ittNumber+".pdf");
        System.out.println("Priro to call canonE193PrintInvoice:");
        CanonE193PrintInvoice canonE193PrintInvoice=new CanonE193PrintInvoice();
        Long reqPk=canonE193PrintInvoice.getInvoiceReqNum(ittNumber);
       byte[]bArray= canonE193PrintInvoice.getByteReportArray(reqPk);
        OutputStream outPutStream = response.getOutputStream();
        outPutStream.write(bArray);
        outPutStream.close();
       
    }
catch (FileNotFoundException e) {
	out.println(e.getMessage());
	e.printStackTrace();
} catch (IOException e) {
	out.println(e.getMessage());
	e.printStackTrace();
}	
catch (Exception e) {
        out.println(e.getMessage());
        e.printStackTrace();
    }
%>