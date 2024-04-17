<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*,java.io.*"%>
<%@ page import="com.lowagie.text.*,com.lowagie.text.pdf.*,com.lowagie.text.pdf.PdfPTable" %>
<%@ page import="com.jscape.inet.sftp.*,com.jscape.inet.sftp.events.*" %>
<%@page import="parts.dbcommon.EZDDBCICarrier"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="com.canon.oracle.custapp.util.CanonUploadUtil"%>

<%
	InputStream in = null;
	boolean invNotFound=false;
	String attachedafileType = "";
	String  strFileName = "" ;
	String strFileContentType = "";
	String fileName = "";
			
	String path = request.getParameter("fileName");
	System.out.println(" Download File PATH: " + path);
	CanonUploadUtil canonUtil	= new CanonUploadUtil();
	
	// Need to extract file name 
	 if(path!=null){
           //For work around.
        ArrayList<String> tokenList = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(path, "/");
        while(st.hasMoreTokens()){
         tokenList.add((String)(st.nextToken()));
                }
          int tokenlength = tokenList.size();
        System.out.println("Token Length~ " + tokenlength);
         strFileName =  tokenList.get(tokenlength-1);
        System.out.println(" Attached File Name: "+strFileName);
        
        if(strFileName!=null){
        	attachedafileType = strFileName.substring(strFileName.indexOf(".")+1,strFileName.length());
        	System.out.println(" AttachedafileType~ "+ attachedafileType);
        	
        	// Need only file name without date and timestamp 
        	ArrayList<String> strFileTokenList = new ArrayList<String>();
        	StringTokenizer stValue = new StringTokenizer(strFileName, "_");
        	while(stValue.hasMoreTokens()){
        		        strFileTokenList.add((String)(stValue.nextToken()));
                       }
               fileName =  strFileTokenList.get(0);
               System.out.println(" Attached File Name without Date and time Stamp: "+fileName);
        }
        strFileContentType  = canonUtil.getUploadContentType(attachedafileType);
        System.out.println(" StrFileContentType~ "+ strFileContentType);
           
          }
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
        String originalFileName = fileName + "."+attachedafileType;
        response.setHeader ("Content-Disposition", "attachment;filename= "+ originalFileName );
      	response.setContentType(strFileContentType);
		// response.setContentType("application/vnd.ms-excel");
		
		// response.setHeader("Content-disposition","inline; filename=test.xls" );

		 response.setContentLength(baos.size());

		 ServletOutputStream sos= response.getOutputStream();

		 baos.writeTo(sos);

		 sos.flush();
	
	System.out.println("end of file");
%>
