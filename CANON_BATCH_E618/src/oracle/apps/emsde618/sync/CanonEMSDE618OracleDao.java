package oracle.apps.emsde618.sync;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import oracle.jdbc.OracleCallableStatement;
//import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;


public class CanonEMSDE618OracleDao {
	
	
	
	public boolean truncateTable() {
        boolean res = true;
        Connection connection = null;
        CallableStatement cstmt = null;
        try {
            connection = TransactionScope.getConnection();
		   cstmt = (CallableStatement)connection.prepareCall("{call CANON_E618_EMSD_SER_SYNC_PKG.table_truncate}");
			if (cstmt != null) {
				cstmt.execute();
			}

        } catch (Exception exception1) {
            res = false;
            System.out.println("**ERROR :  " + exception1.getMessage());
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (connection != null) {
                    TransactionScope.releaseConnection(connection);
                }
            } catch (Exception exception2) {
                System.out.println("**ERROR :  " + exception2.getMessage());
            }
        }
        return res;
    }
 
	 public   boolean insertDataForServImpact(List list){
		 System.out.println( "INFO:: Inserting  Data For Service Requests in S21 ...");
		    boolean flag = true;
		    CallableStatement statement = null;
	        Connection oracleconnection =null;
	        try {
		        oracleconnection = TransactionScope.getConnection();
		        oracleconnection.setAutoCommit(true);
		        for (int i = 0; i < list.size(); i++) {
		            ((CanonEMSDE618ServImpactRec) list.get(i)).setConn(oracleconnection);
		        }
	        
	            if (oracleconnection != null) {
	            	CanonEMSDE618ServImpactRec[] insRec = (CanonEMSDE618ServImpactRec[]) list.toArray(new CanonEMSDE618ServImpactRec[0]);
	                statement = (CallableStatement) oracleconnection.prepareCall("{CALL CANON_E618_EMSD_SER_SYNC_PKG.load_impact_data(?,?)}");
	                String insertArrayName = "CANON_E618_SERV_IMPACT_TBL_TYP";
	                ArrayDescriptor insDescriptor = ArrayDescriptor.createDescriptor(insertArrayName,  oracleconnection.getMetaData().getConnection());
	                ARRAY insRecArray = new ARRAY(insDescriptor, oracleconnection.getMetaData().getConnection(), insRec);

	                if (statement != null) {
	                    statement.setObject(1, insRecArray);
	                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
	                    statement.execute();
	                    String res  = ((CallableStatement) statement).getString(2);
	                    if(res!=null && res.equalsIgnoreCase("N")){
	                      flag=false;
	                    }
	                   // statement.clearParameters();
	                } else {
	                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
	                }
	            } else {
	                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
	            }
	        } catch (Exception ex) {
	        	System.out.println("Error in Inserting Data For  ServImpact :  " + ex.getMessage());
	            flag = false;
	        } finally {
	        	try {
					statement.close();
	                statement = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        return flag;
		}
	 
	 
	 public String getSqlServerInfo(){
		   
			System.out.println("INFO : Getting  to SQLServer details...");
		    String res="";
		    StringBuffer qry = new StringBuffer("");
			Statement statement = null;
			ResultSet resultSet = null;
			Connection connection = null;	   
	       qry.append("SELECT CONN_STRING  ");
			qry.append("FROM CANON_E618_EMSD_SQLSERVER_V  ");
			qry.append("WHERE DB_NAME = (SELECT sys_context('userenv', 'db_name') FROM DUAL) ");
			qry.append("AND ENABLED_FLAG = 'Y' ");

			try {
				connection = TransactionScope.getConnection();
				if (connection != null) {
					statement = connection.createStatement();
					if (statement != null) {
						resultSet = statement.executeQuery(qry.toString());
						while (resultSet.next()) {
							res =resultSet.getString(1);
							System.out.println(res);
						}
					} else {
						System.out.println("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");
					}
				} else {
					System.out.println("dbStatus: UNABLE_TO_GET_CONNECTION");
				}
			}

			catch (Exception exception) {
				System.out.println("**ERROR :  " + exception.getMessage());
			} finally {

				try {
					if (statement != null)
						statement.close();
				} catch (Exception eExp) {
				}
				try {
					if (resultSet != null)
						resultSet.close();
				} catch (Exception eExp) {
				}
				try {
					if (connection != null)
						TransactionScope.releaseConnection(connection);
				} catch (Exception eExp) {
				}
			}
			return res;

	   }
	 
	
	   private  void close(OracleCallableStatement statement , Connection oracleconnection ){
		   if (statement != null) {
               try {
                   statement.close();
                   statement = null;
               } catch (Exception exp) {
            	   System.out.println("**ERROR :  " + exp.getMessage());
               }
           }
           if (oracleconnection != null) {
               try {
               	TransactionScope.releaseConnection(oracleconnection);
               } catch (Exception exp) {
            	   System.out.println("**ERROR :  " + exp.getMessage());
               }
           }
	    }
	   
	   
	   
}
