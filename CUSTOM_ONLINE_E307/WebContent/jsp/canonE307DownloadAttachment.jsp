<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*,java.io.*"%>
<%@ page import="com.lowagie.text.*,com.lowagie.text.pdf.*,com.lowagie.text.pdf.PdfPTable" %>
<%@ page import="com.jscape.inet.sftp.*,com.jscape.inet.sftp.events.*" %>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
	InputStream in = null;
	boolean invNotFound=false;
	String path = request.getParameter("fileName");
	if(path.indexOf(".pdf")>-1){
		 String folderPath = "///"+path;
    //   String folderPath="////WebSphere/apps/s21/csawds/updownfiles/download/Service2_20151201141613821.pdf";

         File file = new File(folderPath);

         FileInputStream fis = new FileInputStream(file);
         
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] buf = new byte[256];
          try {
                 for (int readNum; (readNum = fis.read(buf)) != -1;) {
                	 baos.write(buf, 0, readNum); 
                 }

             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         
		 response.setContentType("application/pdf");

		 response.setHeader("Content-disposition","inline; filename=test.pdf" );

		 response.setContentLength(baos.size());

		 ServletOutputStream sos= response.getOutputStream();

		 baos.writeTo(sos);

		 sos.flush();
	}
	System.out.println("end of file");
%>
