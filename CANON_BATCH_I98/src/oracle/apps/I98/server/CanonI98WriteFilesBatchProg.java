/***********************************************************************
*  FileName : CanonI98WriteFilesBatchProg.java
*	
*	Modification History :
*	Date		ITG Ticket #	Author			Description
*	---------------------------------------------------------------------
*	
*
************************************************************************/
package oracle.apps.I98.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleTypes;

public class CanonI98WriteFilesBatchProg {

	public static final String GET_FILEPATH = "{call CANON_I98_ATRS_PKG.get_file_path(?)}";
	public static final String GET_TABLE_DATA = "{call CANON_I98_ATRS_PKG.get_table_data(?,?)}";
	
	 public static void main(String args[]) throws Exception {
    	    	
    	String fileName=System.getenv("VAR_USER1");
    			
    	if(fileName != null || fileName.trim().length()> 0)  {
			CanonI98WriteFilesBatchProg i98BP=new CanonI98WriteFilesBatchProg();
			i98BP.writeFiles(fileName);
		}else fileName="No Value Provided";
    	
		System.out.println("FileName: "+fileName);
    	
    }
	 
	
	
	
	public void writeFiles(String fileName) {
        CallableStatement statement = null;
        Connection connection = null;
        String filePath = null;
        
        try {
            connection = TransactionScope.getConnection();
            System.out.println("connection:"+connection);
            if (connection != null) {
            	
                statement = (CallableStatement) connection.prepareCall(GET_FILEPATH);
                if (statement != null) {
                    statement.registerOutParameter(1,OracleTypes.VARCHAR);  
                    statement.execute();
                    filePath = statement.getString(1);
        			statement.close();
                } else {
                    System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
                //System.out.println("filePath : " +filePath);
                dumpToFile(connection,filePath,fileName+"_Territory","CANON_I98_TERRITORIES_TBL");
                dumpToFile(connection,filePath,fileName+"_Terr_Zip","CANON_I98_TERR_ZIP_TBL");
                dumpToFile(connection,filePath,fileName+"_Prospect","CANON_I98_PROSPECTS_TBL");
                dumpToFile(connection,filePath,fileName+"_Customer","CANON_I98_CUSTOMERS_TBL");
                dumpToFile(connection,filePath,fileName+"_Salesrep","CANON_I98_SALESREPS_TBL");
                TransactionScope.releaseConnection(connection);
            } else {
                System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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
   }
	
	public void dumpToFile(Connection conn, String filePath, String fileName, String tableName) {
		CallableStatement stmt = null;
		try{
			//System.out.println("fileName : " +fileName);
			//System.out.println("filePath : " +filePath);
			FileWriter fw = new FileWriter(filePath + "/" + fileName + new SimpleDateFormat("yyyyMMddhhmm'.txt'").format(new java.util.Date()));
			BufferedWriter bw = new BufferedWriter(fw);
			
			//System.out.println("fw: " +fw);
			
			stmt = (CallableStatement) conn.prepareCall(GET_TABLE_DATA);
			if (stmt != null) {
				stmt.registerOutParameter(2,OracleTypes.CURSOR);
				stmt.setObject(1,tableName,OracleTypes.VARCHAR);
				stmt.execute();
             final ResultSet cursor = (ResultSet) stmt.getObject(2);
             ResultSetMetaData rsmd = cursor.getMetaData();
             int columnCount = rsmd.getColumnCount();
             //System.out.println("columnCount : " +columnCount);
             while(cursor.next()){
             	StringBuilder row = new StringBuilder();
             	for(int i=1; i<=columnCount; i++) {
             			if (i>1 && i<=columnCount)
             				row.append(',');
             			row.append(cursor.getObject(rsmd.getColumnName(i)));
             	}
             	//System.out.println("row: " +row);
             	row.append(System.getProperty("line.separator"));
             	bw.append(row);
             }
 			
 			cursor.close();
             bw.close(); stmt.close();
 			
         } else {
             System.err.println("executeSQLPKG: DBStatus.UNABLE_TO_CREATE_STATEMENT ");
         }
		} catch (Exception es){
			 es.printStackTrace();
		} finally {
			 if (stmt != null) {
	                try {
	                	stmt.close();
	                	stmt = null;
	                } catch (Exception exp) {
	                    exp.printStackTrace();
	                }
	            }
		}
	}
	
    
}
