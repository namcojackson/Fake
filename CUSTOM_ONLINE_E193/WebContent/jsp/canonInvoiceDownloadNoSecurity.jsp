<!--contents of downloadInvoice.jsp-->
<%@ page import="com.canon.oracle.custapp.util.*" %>
<%@ page import="java.util.*,java.io.*"%>
<%@ page import="com.lowagie.text.*"%>


<%@ page import="com.aspose.cells.Workbook" %>
<%@ page import="com.aspose.cells.Worksheet" %>
<%@ page import="com.aspose.cells.License" %>
<%@ page import="com.canon.oracle.custapp.util.CanonBSDAdminUtil" %>
<%@ page import="com.canon.oracle.custapp.custinq.dao.CanonE522InvoiceSearchDao" %>





<!--Assumes that file name is in the request objects query Parameter -->
<%
try{


    ByteArrayOutputStream baos =null;    
    com.lowagie.text.Document document=null;
    com.lowagie.text.pdf.PdfWriter writer =null;
    com.lowagie.text.pdf.PdfContentByte cb =null;
    com.lowagie.text.pdf.RandomAccessFileOrArray rand =null;
    com.lowagie.text.Image img =null;
 //   com.canon.oracle.custapp.util.CanonE182FTPWrapper ftpObj =null;
    String invNbr = request.getParameter("InvoiceNumber");
    System.out.println ("Checking for Images for Invoice Number " +invNbr );

    
    
  //  canon.apps.pci.codecs.OracleCodec oracodec=new canon.apps.pci.codecs.OracleCodec();
    java.util.ArrayList list =null;
    
    boolean invNotFound=false;
   
    
    CanonE522InvoiceSearchDao invSrchDao  = new CanonE522InvoiceSearchDao();
    	
    java.util.ArrayList arList=invSrchDao.getInvoiceDetailForEmange(invNbr);
    	
    	if(arList!=null  && arList.size()>2){
    	
    	 String strNotFound =(String) arList.get(0);
    	 invNotFound = (strNotFound.equalsIgnoreCase("N"))?true:false;
    	   
    	 if(!invNotFound){
    		 try {
    			 System.out.println("Invoice found in E522 or 170 , Inv. Number : "+invNbr);
	         	
    			String invoiceType =((String)arList.get(1));
    	    	
	            InputStream in = null;
	         	
	            if(invoiceType.equals("170")) {
		           	 System.out.println("Invoice found in 170 System , Inv. Number : "+invNbr);
		           	 document = new Document();
			         baos = new ByteArrayOutputStream();
			         writer =com.lowagie.text.pdf.PdfWriter.getInstance(document, baos);
			         document.open();
			         cb = writer.getDirectContent();
			         boolean streamNotFound=false;	
			       for(int i =2; i < arList.size(); i++) {
			        		String path = (String)arList.get(i);
			        		InputStream is = com.canon.oracle.custapp.custinq.dao.CanonE182InvoiceDownloadAuth.getImageStream(path);
			        		document.newPage();		
			        		if (is != null){
			        		   
			        			rand = new com.lowagie.text.pdf.RandomAccessFileOrArray(is);
			            		try{
			        				img = com.lowagie.text.pdf.codec.TiffImage.getTiffImage(rand,1);		
			        				
			        			}catch(Exception eof)
			        			{
			        				eof.printStackTrace();
			        				img = null;
			        			}	
			        		    
			        			if (img != null) {
			        			    System.out.println("Image is not null");
			        				img.setAbsolutePosition(0, 0);
			        				img.scaleToFit(595, 842);
			        		
			        				cb.addImage(img);
			        	
			        			}   			
			        			is.close();
			        		}else{
			        			streamNotFound=true;
			        			invNotFound=true;
			        			break;
			        		}
			        	}
		
				        if(!streamNotFound){	
				        	document.close();
				        	response.setHeader ("Content-Disposition", "attachment;filename=\"Invoice-"+invNbr+".pdf\"");
				        	response.setContentType("application/pdf");
				        	response.setContentLength(baos.size());
				        	ServletOutputStream out1 = response.getOutputStream();
				        	baos.writeTo(out1);
				        	baos.close();
				        	out1.flush();	
				         }	   
		           }else{
	           
		         	if( invoiceType.equalsIgnoreCase("PDF") ){
		        	
		        		String serverUrl =((String)arList.get(2)).split("@")[0];
		        		String strPath= ((String)arList.get(2)).split("@")[1];
		            	strPath = strPath.substring(strPath.indexOf("custombilling"));
		        		
			        	String path = serverUrl+strPath;
			            java.net.URL url = new java.net.URL(path);
			            String separator =File.separator;
			            String name ="Invoice-"+invNbr+".pdf";
			            
			            String cntType="vnd.ms-excel";
			            if(path!=null && path.indexOf("pdf")>0){
			          	  cntType="pdf";
			            }
			            in = url.openStream();
			            if(in!=null){
				            response.setContentType("application/pdf");
				            response.setHeader("Content-disposition", "attachment; filename= " + name);
			            } 
			                
		        	}else{
		        		
		        	  CanonBSDAdminUtil util = new CanonBSDAdminUtil();
		        	  License lic=null;
		      		 
		   	          String billingPath = util.getProfile(CanonBSDAdminUtil.BSD_PATH);
		   	          BufferedReader input;
		   	          input = new BufferedReader(
		   	                    new FileReader(billingPath
		   	                    + System.getProperty("file.separator")
		   	                    + "Aspose.Total.Java.lic"));
		   	           lic = new License();
		   	          lic.setLicense(input);
		      	     	        		
		        		String  name ="Invoice-"+invNbr+".xls";
		        		if(arList.size()==3){
		        			  name =  ((String) arList.get(2)).split("@")[3];
		        			  name =   name.replaceAll(" +", "_");
		        		}
		        		Workbook wb = new Workbook();
		        		for (int i = 2; i < arList.size(); i++) {
	
		        			String serverUrl = ((String) arList.get(i)).split("@")[0];
		        			String strPath = ((String) arList.get(i)).split("@")[1];
		        			strPath = strPath.substring(strPath.indexOf("custombilling"));
		        			String path = serverUrl + strPath;
		        			java.net.URL url = new java.net.URL(path);
		        			if (i == 2) {
		        				String separator =File.separator;
		        				
		     		            wb.open(url.openStream());
		     		           for (int k = 0; k < wb.getWorksheets().size(); k++) {
	       			            Worksheet sheet = wb.getSheet(k);
	       			            sheet.setName(sheet.getName() + "_" + 1);
	       			           }
		        			} else {
		        				Workbook wb2 = new Workbook();
		        				wb2.open(url.openStream());
		        				for (int k = 0; k < wb2.getWorksheets().size(); k++) {
	        			            Worksheet sheet = wb2.getSheet(k);
	        			            sheet.setName(sheet.getName() + "_" + (i-1));
	        			        }
		        				
		        				wb.combine(wb2);
		        				
		        			}
	
		        		}
	
		        		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		        		wb.save(arrayOutputStream);
		        		arrayOutputStream.flush();
		        		 in = new ByteArrayInputStream(arrayOutputStream
		        				.toByteArray());
		        		 if(in!=null){
		        		 response.setContentType("application/vnd.ms-excel");
				         response.setHeader("Content-disposition", "attachment; filename= " + name);
		        		 }
		        	}
		         	
		         	
		           if(in!=null){
			            for (int c = in.read(); c != -1; c = in.read()) {
			                response.getWriter().write(c); 
			            }
		            in.close();
		           }else{
		        	   invNotFound=true;
		           }
	           }
	            
	         	
	        } catch (Exception e) {
	        	invNotFound=false;
	           System.out.println(e.getMessage());
	        }
       
    	 }
      }else{
    	  invNotFound=true;
      }
    

    
    if(invNotFound){
    
	String text = "";
	text = text + "Unable to Pull the  copy of Invoice at this time, please try again later. <BR>";
	
   /*
	text = text + "Please contact your System Administrator.";
    Canon_E182_BusinessUserRegProfile cbus = new Canon_E182_BusinessUserRegProfile();
	String web_admin_info [] = (String [])cbus.getWebAdminEmail();
	String canon_web_admin_email = web_admin_info[0];
	String canon_primary_phoneNo = AOLMessageManager.getMessageSt("IBE","CANON_E182_WEBADMIN_USER_PHNO");

	text = text + canon_primary_phoneNo;
	text = text + AOLMessageManager.getMessageSt("IBE","CANON_E182_IBE_CONTACT_ORG_EM");
	text = text +  " " + canon_web_admin_email;
  */
	out.println (text);    
    
    }
    
    
    


  }catch(IOException ioe) {
    System.err.println("IOException ----->"+ioe.getMessage());
    String text = "";
    text = text + "Unable to Pull the PDF copy of Invoice at this time, please try again later. <BR>";
    text = text + "If Problem still exits, please call eManage Webadmin ";

  /*  Canon_E182_BusinessUserRegProfile cbus = new Canon_E182_BusinessUserRegProfile();
    String web_admin_info [] = (String [])cbus.getWebAdminEmail();
    String canon_web_admin_email = web_admin_info[0];
    String canon_primary_phoneNo = AOLMessageManager.getMessageSt("IBE","CANON_E182_WEBADMIN_USER_PHNO");

    text = text + canon_primary_phoneNo;
    text = text + AOLMessageManager.getMessageSt("IBE","CANON_E182_IBE_CONTACT_ORG_EM");
    text = text +  " " + canon_web_admin_email;*/

    out.println (text);
  }catch (Exception e)
  {
    System.err.println(e.getMessage());
    e.printStackTrace();

     String text = "";
     text = text + "Unable to Pull the PDF copy of Invoice at this time, please try again later. <BR>";
     text = text + "If Problem still exits, please call eManage Webadmin ";

   /*  Canon_E182_BusinessUserRegProfile cbus = new Canon_E182_BusinessUserRegProfile();
     String web_admin_info [] = (String [])cbus.getWebAdminEmail();
     String canon_web_admin_email = web_admin_info[0];
     String canon_primary_phoneNo = AOLMessageManager.getMessageSt("IBE","CANON_E182_WEBADMIN_USER_PHNO");

     text = text + canon_primary_phoneNo;
     text = text + AOLMessageManager.getMessageSt("IBE","CANON_E182_IBE_CONTACT_ORG_EM");
     text = text + " " + canon_web_admin_email; */

    out.println (text);
  }

%>
