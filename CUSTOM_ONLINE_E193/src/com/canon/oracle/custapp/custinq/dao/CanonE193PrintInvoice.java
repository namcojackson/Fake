package com.canon.oracle.custapp.custinq.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;

public class CanonE193PrintInvoice {

	

	// Initialize EIP Printing Service Instance
	S21EIPPrintingService eipService = new S21EIPPrintingService();

	
	public  byte[] getByteReportArray(long requestPK)
	{
		 long rqstPK = requestPK; // It Could be provided from Invoice Table
		
		byte[] report=eipService.getArchivedReport(rqstPK);
		//System.out.println("report array size for reqPk:"+report);
		return report;
		/*if (report != null) {
			byteArrayToFile(report);
		}*/
		 
	}
	
	private static void byteArrayToFile(byte[] bArray) {  
	    	OutputStream out = null;
		try {
			out = new FileOutputStream("./invoice_442046.pdf");
		    	out.write(bArray);
		    	out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public long getInvoiceReqNum(String invoiceNum)
	{
		System.out.println("in getInvoiceReqNum: "+invoiceNum);
        CallableStatement statement = null;
        Connection connection = null;
        try{
        	 connection = TransactionScope.getConnection();
        	 if (connection != null) {
        		 statement = (CallableStatement) connection.prepareCall("{? = call CANON_E193_CS_SQLS_PKG.GET_INVOICE_RQST_NUM(?)}");
        		 if(statement!=null)
        		 {
        			 statement.setObject(2,invoiceNum,OracleTypes.VARCHAR);  
        			 statement.registerOutParameter(1, OracleTypes.NUMBER);
        			  statement.execute();
        			  long reqPk=statement.getInt(1);
        			  return reqPk;
        		 }
        		 else
        			 System.err.println("in getInvoiceReqNum DBStatus.UNABLE_TO_GET_SSTATEMENT");
        	 }
        	 else
        		 System.err.println("in getInvoiceReqNum DBStatus.UNABLE_TO_GET_CONNECTION");
        	
        }
        catch (Exception ex) {
        	System.err.println("Exception occured in getInvoiceReqNum DBStatus.UNABLE_TO_GET_CONNECTION");
            ex.printStackTrace();
        } 
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    TransactionScope.releaseConnection(connection);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
        return 0l;
	}
}
