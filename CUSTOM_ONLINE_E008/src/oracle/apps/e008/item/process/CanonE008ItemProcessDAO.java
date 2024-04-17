package oracle.apps.e008.item.process;

import java.sql.Connection;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Struct;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.sql.CallableStatement;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;  
import oracle.sql.ArrayDescriptor;   
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import oracle.apps.jtf.aom.transaction.TransactionScope;

public class CanonE008ItemProcessDAO {
	

   public static final String UPLOAD_PROJECT_ITEMS= "{call CANON_E008_ITEM_WORKBENCH_PKG.UPLOAD_PROJECT_ITEMS(?,?,?,?,?)}";
   public static final String VALIDATE_UPLOAD_ITEMS= "{call CANON_E008_ITEM_WORKBENCH_PKG.VALIDATE_UPLOAD_ITEMS(?,?,?,?,?,?)}";
   
   public static final String SAVE_PROJECT_ITEMS= "{call CANON_E008_ITEM_WORKBENCH_PKG.SAVE_PROJECT_ITEMS(?,?,?,?,?)}";
   public static final String GET_PROJECT_ITEMS= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_PROJECT_ITEMS(?,?,?,?,?)}";
   public static final String GET_COLUMN_HEADER_DETAILS= "{call CANON_E008_ITEM_WORKBENCH_PKG.Get_Column_headers(?,?,?)}";
   public static final String GET_PROCESSOR_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_PROCESSOR_LIST(?,?,?,?)}";
   public static final String DELETE_PROJECT_ITEMS= "{call CANON_E008_ITEM_WORKBENCH_PKG.DELETE_PROJECT_ITEMS(?,?,?,?)}";
   public static final String DELETE_PROJECT= "{call CANON_E008_ITEM_WORKBENCH_PKG.DELETE_PROJECT(?,?,?)}";
   public static final String CLOSE_PROJECT= "{call CANON_E008_ITEM_WORKBENCH_PKG.CLOSE_PROJECT(?,?,?)}"; 
   public static final String GET_ROLE_EMAIL= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_ROLE_EMAIL(?,?,?)}"; 
   public static final String GET_DB_NAME= "{call CANON_E479_TEMPLATE_UTIL_PKG.GET_DB_NAME(?)}";
   public static final String START_APPRV_PROCESS= "{call CANON_E008_ITEM_WORKBENCH_PKG.START_APPRV_PROCESS(?,?,?)}";
   public static final String GET_RESP_NAME= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_RESP_NAME(?,?,?,?)}";
   public static final String GET_PROJECT_HISTORY= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_PROJECT_HISTORY(?,?,?,?)}";
   public static final String CHECK_VIEW_PROJECT= "{?= call CANON_E008_ITEM_WORKBENCH_PKG.CHECK_VIEW_PROJECT(?,?,?)}";
   public static final String CHECK_SUBMIT_APPROVAL_PROJECT= "{?= call CANON_E008_ITEM_WORKBENCH_PKG.CHECK_SUBMIT_APPROVAL_PROJECT(?,?,?)}";
   public static final String CHECK_MODIFY_PROJECT= "{?= call CANON_E008_ITEM_WORKBENCH_PKG.CHECK_MODIFY_PROJECT(?,?,?)}";
   public static final String CHECK_DELETE_PROJECT= "{?= call CANON_E008_ITEM_WORKBENCH_PKG.CHECK_DELETE_PROJECT(?,?,?)}";
   public static final String CHECK_CREATE_PROJECT= "{?= call CANON_E008_ITEM_WORKBENCH_PKG.CHECK_CREATE_PROJECT(?,?,?)}";
   public static final String VALIDATE_PROJECT= "{call CANON_E008_ITEM_WORKBENCH_PKG.VALIDATE_PROJECT(?,?,?,?,?,?)}";
   public static final String NOTIFICATION_ACTION= "{call CANON_E008_ITEM_WORKBENCH_PKG.APPREJ_ACTION(?,?,?,?,?,?,?)}";
   public static final String CHECK_VALIDATE_REJECT_PROJECT= "{?= call CANON_E008_ITEM_WORKBENCH_PKG.CHECK_VALIDATE_REJECT_PROJECT(?,?,?)}";
   public static final String VALIDATE_REQUIRED_FIELDS= "{call CANON_E008_ITEM_WORKBENCH_PKG.VALIDATE_REQUIRED_FIELDS(?,?,?,?,?)}";
   public static final String COUNTERGROUP_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.COUNTERGROUP_LIST(?,?,?)}";
   public static final String VALIDATE_OWNERSHIP_FIELDS= "{call CANON_E008_ITEM_WORKBENCH_PKG.VALIDATE_OWNERSHIP_FIELDS(?,?,?,?,?,?)}";
   public static final String CHECK_STATUS_MONITOR= "{?= call CANON_E008_ITEM_WORKBENCH_PKG.CHECK_STATUS_MONITOR(?)}";
   public static final String GET_CCID_DESC= "{?= call CANON_E008_ITEM_WORKBENCH_PKG.GET_CCID_DESC(?)}";
   public static final String TEMPLATE_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.TEMPLATE_NAME_LIST(?,?,?)}";
   public static final String CAT_TEMPLATE_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.CAT_TEMPLATE_LIST(?,?,?,?)}";
   public static final String SUPPLY_CATEGORY_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.SUPPLY_CATEGORY_LIST(?,?,?)}";
   public static final String SUPPLIER_SITE_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.SUPPLIER_SITE_LIST(?,?,?,?)}";
   public static final String SUPPLIER_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.SUPPLIER_LIST(?,?,?,?)}";
   public static final String SOFT_LICENSE_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.SOFT_LICENSE_LIST(?,?,?)}";
   public static final String PRODUCT_CODE_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.PRODUCT_CODE_LIST(?,?,?)}";
   public static final String MERCH_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.MERCH_LIST(?,?,?)}";
   public static final String ITEM_TYPE_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.ITEM_TYPE_LIST(?,?,?)}";
   public static final String SPEED_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.SPEED_LIST(?,?,?)}";
   public static final String CANON_TEMPLATE_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.CANON_TEMPLATE_LIST(?,?,?)}";
   public static final String THIRD_PARTY_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.THIRD_PARTY_LIST(?,?,?)}";
   public static final String GET_APPR_HIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_APPR_HIST(?,?,?,?)}";
   public static final String USA_COMPENSATION_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.USA_COMPENSATION_LIST(?,?,?)}";
   public static final String USA_COMPENSATION2_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.USA_COMPENSATION2_LIST(?,?,?)}";
   
   public static final String TEMPLATE_ATTRIBUTE_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.TEMPLATE_ATTRIBUTE_LIST(?,?,?)}";
   public static final String TEMPLATE_STATUS_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.TEMPLATE_STATUS_LIST(?,?,?)}";
   public static final String GET_ITEM_MAIN_ATTRIBUTES= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_ITEM_MAIN_ATTRIBUTES(?,?,?,?,?)}";
   public static final String GET_ITEM_MAIN_ATTR_VALUES= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_ITEM_MAIN_ATTR_VALUES(?,?,?,?)}";
   public static final String GET_PROJ_ITEM_ATTR_VALUES= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_PROJ_ITEM_ATTR_VALUES(?,?,?,?,?,?)}";
   public static final String GET_PROJ_ITEM_ADDITIONAL_ATTR_VALUES= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_PROJ_ITEM_ADD_ATTR_VALUES(?,?,?,?,?)}";
   
   public static final String GET_PROJECT_ITEMS_DETAILS= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_PROJECT_ITEMS_DETAILS(?,?,?,?)}";
   public static final String MASTER_PROJECT_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.master_project_list(?,?,?)}";
   
   public static final String GET_COMPONENT_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_COMPONENT_LIST(?,?,?,?)}";
   public static final String GET_COMPONENT_DETAILS= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_COMP_ITEM_DETAILS(?,?,?,?)}";
   public static final String SAVE_BOM_ITEMS= "{call CANON_E008_ITEM_WORKBENCH_PKG.SAVE_BOM_ITEMS(?,?,?)}";
   public static final String GET_BOM_ITEMS_DETAILS= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_COMP_ITEMS(?,?,?,?)}";
   
   public static final String PROJECT_TYPE_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.PROJECT_TYPE_LIST(?,?,?)}";
   
   public static final String ADD_NOTES= "{call CANON_E008_ITEM_WORKBENCH_PKG.ADD_NOTES(?,?,?,?,?)}";
   
   public static final String SAVE_SEARCH_CRITERIA= "{call CANON_E008_ITEM_WORKBENCH_PKG.SAVE_PROJECT_SEARCH(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
   public static final String GET_SAVEDSEARCH_LIST= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_SAVEDSEARCH_LIST(?,?,?,?)}";
   
   public static final String GET_SAVEDSEARCH_VALUES= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_SAVEDSEARCH_VALUES(?,?,?,?,?)}";

   public static final String GET_CUSA_ITEMS= "{call CANON_E008_ITEM_WORKBENCH_PKG.GET_CUSA_ITEMS(?,?,?,?,?,?,?)}";
   
   private static ThreadLocal exception = new ThreadLocal();
   
   private static void saveException(Exception e){ 
       exception.set(e);  
   }   
   private static void clearException(){
       exception.set(null);
   }

   public static Exception getException(){
       return (Exception)exception.get();
   }
    
   public CanonE008ItemProcessDAO(){
   }

   public static Object[] uploadProjectItems(CanonE008ProjectRec p_project_rec,
    List p_item_tbl){
        clearException();
        System.out.println("in uploadProjectItems "+p_project_rec+"|"+p_item_tbl);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("in saveProjectItems 1 ");
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(UPLOAD_PROJECT_ITEMS);
                
                if (statement != null) {
                	
                	Connection nativeConnection=TransactionScope.nativeConnection(connection);                	                
                    connection.getTypeMap().put("CANON_E008_PROJECT_REC", CanonE008ProjectRec.class);
                    statement.setObject(1, p_project_rec);      
                    
                    ArrayDescriptor arrydesc = ArrayDescriptor.createDescriptor("CANON_E008_PROJ_ITEMS_TBL_TYPE",  nativeConnection);
                    Object[] java_record_array   = new Object[p_item_tbl.size()];
                    for(int i=0;i<p_item_tbl.size();i++)
                    	java_record_array[i]=p_item_tbl.get(i);    
                    
                    
                    ARRAY canonE008ProjItemList=new ARRAY(arrydesc, nativeConnection, java_record_array);
                    System.out.println("canonE008ProjItemList convertion done::: ");
                    
                    statement.setArray(2,canonE008ProjItemList);
                    //statement.setArray(2, canonE008ItemsTblTypeListToArray(p_item_tbl, connection), OracleTypes.ARRAY);
                    System.out.println("in uploadProjectItems 2 ");
                    statement.registerOutParameter(3, OracleTypes.NUMBER);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("in uploadProjectItems 6 ");
                    return new Object[]{statement.getObject(3)
                        ,statement.getObject(4),statement.getObject(5)
                        }; 
                    
                } else {
                	System.out.println("in uploadProjectItems 7 ");
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
            	System.out.println("in uploadProjectItems 8 ");
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
    
   public static Object[] saveProjectItems(CanonE008ProjectRec p_project_rec,
		    List p_item_tbl){
		        clearException();
		        System.out.println("in saveProjectItems "+p_project_rec+"|"+p_item_tbl);
		        CallableStatement statement = null;
		        Connection connection = null;
		        try {
		            connection = TransactionScope.getConnection();
		            System.out.println("in saveProjectItems 1 ");
		            if (connection != null) {
		                statement = (CallableStatement) connection.prepareCall(SAVE_PROJECT_ITEMS);
		                
		                if (statement != null) {
		                	
		                	Connection nativeConnection=TransactionScope.nativeConnection(connection);                	                
		                    connection.getTypeMap().put("CANON_E008_PROJECT_REC", CanonE008ProjectRec.class);
		                    statement.setObject(1, p_project_rec);      
		                    
		                    ArrayDescriptor arrydesc = ArrayDescriptor.createDescriptor("CANON_E008_PROJ_ITEMS_TBL_TYPE",  nativeConnection);
		                    Object[] java_record_array   = new Object[p_item_tbl.size()];
		                    for(int i=0;i<p_item_tbl.size();i++)
		                    	java_record_array[i]=p_item_tbl.get(i);    
		                    
		                    
		                    ARRAY canonE008ProjItemList=new ARRAY(arrydesc, nativeConnection, java_record_array);
		                    System.out.println("canonE008ProjItemList convertion done::: ");
		                    
		                    statement.setArray(2,canonE008ProjItemList);
		                    //statement.setArray(2, canonE008ItemsTblTypeListToArray(p_item_tbl, connection), OracleTypes.ARRAY);
		                    System.out.println("in saveProjectItems 2 ");
		                    statement.registerOutParameter(3, OracleTypes.NUMBER);
		                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
		                    statement.execute();
		                    System.out.println("in saveProjectItems 6 ");
		                    return new Object[]{statement.getObject(3)
		                        ,statement.getObject(4),statement.getObject(5)
		                        }; 
		                    
		                } else {
		                	System.out.println("in saveProjectItems 7 ");
		                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
		                }
		            } else {
		            	System.out.println("in saveProjectItems 8 ");
		                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		            }
		        } catch (Exception ex) {
		            saveException(ex);
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
		        return null;
		   }
   
   public static Object[] getProjectItems(BigDecimal p_project_no){
        clearException();
        System.out.println("in getProjectItems "+p_project_no);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_PROJECT_ITEMS);
                if (statement != null) {
                    statement.setObject(1, p_project_no, OracleTypes.NUMBER);
                    connection.getTypeMap().put("CANON_E008_PROJECT_REC", CanonE008ProjectRec.class);
                    statement.registerOutParameter(2, OracleTypes.STRUCT, "CANON_E008_PROJECT_REC");
                    statement.registerOutParameter(3, OracleTypes.CURSOR);  
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(2)
                        //,cursorToList((ResultSet)statement.getObject(3),mainiteminfoRec.getRowMapper())
                        ,cursorToList((ResultSet)statement.getObject(3),ItemInfoRec.getRowMapper())
                        ,statement.getObject(4)
                        ,statement.getObject(5)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   public static Object[] getProjectItemsDetails(BigDecimal p_project_no){
       clearException();
       System.out.println("in getProjectItemsDetails "+p_project_no);
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_PROJECT_ITEMS_DETAILS);
               if (statement != null) {
            	   System.out.println("in getProjectItemsDetails 1 "+p_project_no);
                   statement.setObject(1, p_project_no, OracleTypes.NUMBER);
                   System.out.println("in getProjectItemsDetails 2 "+p_project_no);
                   statement.registerOutParameter(2, OracleTypes.CURSOR);
                   System.out.println("in getProjectItemsDetails 3 "+p_project_no);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   System.out.println("in getProjectItemsDetails 4 "+p_project_no);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   System.out.println("in getProjectItemsDetails 5 "+p_project_no);
                   statement.execute();
                   System.out.println("in getProjectItemsDetails 6 "+p_project_no);
                   return new Object[]{cursorToList((ResultSet)statement.getObject(2),CanonE008ItemRec.getRowMapper())
				                       ,statement.getObject(3)
				                       ,statement.getObject(4)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                   System.out.println("in getProjectItemsDetails 8 "+p_project_no);
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
               System.out.println("in getProjectItemsDetails 9 "+p_project_no);
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }

   public static Object[] getColumnHeaderDetails(){
       clearException();
       System.out.println("in getColumnHeaderDetails ");
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           System.out.println("in getColumnHeaderDetails 1");
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_COLUMN_HEADER_DETAILS);
               if (statement != null) {
            	   System.out.println("in getColumnHeaderDetails 2");
                   //statement.setObject(1, p_project_no, OracleTypes.NUMBER);
                   //connection.getTypeMap().put("CANON_E008_ITEM_TEMPL_ATTR_REC", projectLineHeaderInfo.class);
                   System.out.println("in getColumnHeaderDetails 3");
                   //statement.registerOutParameter(1, OracleTypes.STRUCT, "CANON_E008_ITEM_TEMPL_ATTR_REC");
                   statement.registerOutParameter(1, OracleTypes.CURSOR);
                   statement.registerOutParameter(2, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.execute();
                   System.out.println("in getColumnHeaderDetails 4");
                   return new Object[]{//statement.getObject(1)
                       cursorToList((ResultSet)statement.getObject(1),projectLineHeaderInfo.getRowMapper())
                       ,statement.getObject(2)
                       ,statement.getObject(3)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
           ex.printStackTrace(); 
       } finally {
           if (statement != null) {
               try {
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
       return null;
  }
   
    private static List cursorToList(ResultSet cursor, RowMapper rowMapper) {
        List list = new ArrayList();
        try {
            while (cursor.next()) {
                list.add(rowMapper.mapRow(cursor, 0));
            }
        } catch (SQLException ex) {
            saveException(ex);
            ex.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    static oracle.sql.ARRAY canonE008ItemsTblTypeListToArray(List list, Connection connection) throws SQLException {
    	Connection nativeConn=null;    	
    	nativeConn=TransactionScope.nativeConnection(connection);
    	ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("canon_e008_proj_items_tbl_type", nativeConn);        
        CanonE008ItemRec[] elements = (CanonE008ItemRec[]) list.toArray(new CanonE008ItemRec[0]);
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, nativeConn, elements);
        return array;    	    
    }

    static List arrayToCanonE008ItemsTblTypeList(Array a, Connection connection) throws SQLException {
        List list = new ArrayList();
        connection.getTypeMap().put("CANON_E008_ITEM_REC", CanonE008ItemRec.class);
        Object[] oo = (Object[]) a.getArray();
        for (int i = 0; i < oo.length; i++) {
            list.add(oo[i]);
        }
        return list;
    }

    static oracle.sql.ARRAY canonE008ProjectTblTypeListToArray(List list, Connection connection) throws SQLException {
    	Connection nativeConn=null;    	
    	nativeConn=TransactionScope.nativeConnection(connection);
        ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E008_PROJECT_TBL_TYPE", nativeConn);
        CanonE008ProjectRec[] elements = (CanonE008ProjectRec[]) list.toArray(new CanonE008ProjectRec[0]);
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, nativeConn, elements);
        return array;
    }

    static List arrayToCanonE008ProjectTblTypeList(Array a, Connection connection) throws SQLException {
        List list = new ArrayList();
        connection.getTypeMap().put("CANON_E008_PROJECT_REC", CanonE008ProjectRec.class);
        Object[] oo = (Object[]) a.getArray();
        for (int i = 0; i < oo.length; i++) {
            list.add(oo[i]);
        }
        return list;
    }

    public static class CanonE008ProjectRec implements java.sql.SQLData, java.io.Serializable 
    {
    	private String eztableid;
        private String companycode;
        private BigDecimal projectId;
        private BigDecimal projectNumber;           
        private String projectName;
        private String projectType;
        private String projectDesc;
        private String masterproject;
        private String projectRequester;
        private Date requestedDate;
        private String projectNotes;
        private String approvalStatus;
        private String approvalComments;  
        private String processFlag;
        private String createdBy;
        private Date creationDate;         
        private String lastUpdateBy;
        private Date lastUpdateDate;
          
           
            public CanonE008ProjectRec(){
            }
            public CanonE008ProjectRec(
            		String eztableid,
            		String companycode,
                    BigDecimal projectId, 
                    BigDecimal projectNumber,                     
                    String projectName,
                    String projectType,
                    String projectDesc,
                    String masterproject,
                    String projectRequester, 
                    Date requestedDate, 
                    String projectNotes, 
                    String approvalStatus, 
                    String approvalComments,
                    String processFlag,
                    String createdBy,                    
                    Date creationDate, 
                    String lastUpdateBy, 
                    String lastUpdateName, 
                    Date lastUpdateDate 
                    ){
                this.eztableid=eztableid;
                this.companycode=companycode;
                this.projectId=projectId;
                this.projectNumber=projectNumber;
                this.projectName=projectName;
                this.projectType=projectType;
                this.projectDesc=projectDesc;
                this.masterproject=masterproject;
                this.projectRequester=projectRequester;
                this.requestedDate=requestedDate;
                this.projectNotes=projectNotes;
                this.approvalStatus=approvalStatus;
                this.approvalComments=approvalComments; 
                this.processFlag = processFlag;
                this.createdBy=createdBy;
                this.creationDate=creationDate;
                this.lastUpdateBy=lastUpdateBy;
                this.lastUpdateDate=lastUpdateDate;
            }
           
            public String getEztableid() {
				return eztableid;
			}
			public void setEztableid(String eztableid) {
				this.eztableid = eztableid;
			}
			public String getCompanycode() {
				return companycode;
			}
			public void setCompanycode(String companycode) {
				this.companycode = companycode;
			}
			public BigDecimal getProjectId() {
				return projectId;
			}
			public void setProjectId(BigDecimal projectId) {
				this.projectId = projectId;
			}
			public BigDecimal getProjectNumber() {
				return projectNumber;
			}
			public void setProjectNumber(BigDecimal projectNumber) {
				this.projectNumber = projectNumber;
			}
			public String getProjectName() {
				return projectName;
			}
			public void setProjectName(String projectName) {
				this.projectName = projectName;
			}
			
			public String getProjectType() {
				return projectType;
			}
			public void setProjectType(String projectType) {
				this.projectType = projectType;
			}
			public String getProjectDesc() {
				return projectDesc;
			}
			public void setProjectDesc(String projectDesc) {
				this.projectDesc = projectDesc;
			}
			public String getMasterproject() {
				return masterproject;
			}
			public void setMasterproject(String masterproject) {
				this.masterproject = masterproject;
			}
			public String getProjectRequester() {
				return projectRequester;
			}
			public void setProjectRequester(String projectRequester) {
				this.projectRequester = projectRequester;
			}
			public Date getRequestedDate() {
				return requestedDate;
			}
			public void setRequestedDate(Date requestedDate) {
				this.requestedDate = requestedDate;
			}
			public String getProjectNotes() {
				return projectNotes;
			}
			public void setProjectNotes(String projectNotes) {
				this.projectNotes = projectNotes;
			}
			public String getApprovalStatus() {
				return approvalStatus;
			}
			public void setApprovalStatus(String approvalStatus) {
				this.approvalStatus = approvalStatus;
			}
			public String getApprovalComments() {
				return approvalComments;
			}
			public void setApprovalComments(String approvalComments) {
				this.approvalComments = approvalComments;
			}

			public String getProcessFlag() {
				return processFlag;
			}
			public void setProcessFlag(String processFlag) {
				this.processFlag = processFlag;
			}
			public String getCreatedBy() {
				return createdBy;
			}
			public void setCreatedBy(String createdBy) {
				this.createdBy = createdBy;
			}
			public Date getCreationDate() {
				return creationDate;
			}
			public void setCreationDate(Date creationDate) {
				this.creationDate = creationDate;
			}
			public String getLastUpdateBy() {
				return lastUpdateBy;
			}
			public void setLastUpdateBy(String lastUpdateBy) {
				this.lastUpdateBy = lastUpdateBy;
			}
			
			public Date getLastUpdateDate() {
				return lastUpdateDate;
			}
			public void setLastUpdateDate(Date lastUpdateDate) {
				this.lastUpdateDate = lastUpdateDate;
			}
			public String getSQLTypeName() throws SQLException {
                return "CANON_E008_PROJECT_REC";
            }

            public void readSQL(SQLInput stream, String typeName) throws SQLException {
            	   eztableid = stream.readString();
            	   companycode = stream.readString();
                   projectId = stream.readBigDecimal();
                   projectNumber = stream.readBigDecimal();
                   projectName = stream.readString();
                   projectType=stream.readString();
                   projectDesc = stream.readString();
                   masterproject = stream.readString();
                   projectRequester = stream.readString();
                   requestedDate=stream.readDate();
                   projectNotes = stream.readString();
                   approvalStatus = stream.readString();
                   approvalComments = stream.readString();
                   processFlag = stream.readString();
                   createdBy = stream.readString();
                   creationDate = stream.readDate();
                   lastUpdateBy = stream.readString();
                   lastUpdateDate = stream.readDate();
            }

            public void writeSQL(SQLOutput stream) throws SQLException {
                   stream.writeString(eztableid);
                   stream.writeString(companycode);
                   stream.writeBigDecimal(projectId);
                   stream.writeBigDecimal(projectNumber);
                   stream.writeString(projectName);
                   stream.writeString(projectType);
                   stream.writeString(projectDesc);
                   stream.writeString(masterproject);
                   stream.writeString(projectRequester);
                   stream.writeDate(requestedDate);
                   stream.writeString(projectNotes);
                   stream.writeString(approvalStatus);
                   stream.writeString(approvalComments); 
                   stream.writeString(processFlag);
                   stream.writeString(createdBy);
                   stream.writeDate(creationDate);
                   stream.writeString(lastUpdateBy);
                   stream.writeDate(lastUpdateDate);
            }

            public String toString() {
                return "CanonE008ProjectRec{" + "eztableId="+eztableid+", companyCode="
            +companycode+", " + "projectId="+projectId+", projectNumber="+
                		projectNumber+"," +", projectName="+
            projectName+", projectType="+projectType+", projectDesc="+projectDesc+", masterProject="+masterproject+ ", projectRequester="+
            projectRequester+", requestedDate="+requestedDate+
                		", projectNotes="+projectNotes+", approvalStatus="+approvalStatus+
                		", approvalComments="+approvalComments+", processFlag="+processFlag+", createdBy="+createdBy+", creationDate="
                		+creationDate+", lastUpdateBy="+lastUpdateBy+", lastUpdateDate="+lastUpdateDate+'}';
            }
        } 

    /*public static class ItemInfoRec implements java.sql.SQLData, java.io.Serializable  {*/
    public static class ItemInfoRec
    {	
        private BigDecimal  projectid        	;
        private BigDecimal  itemid           	;
    	private BigDecimal  templateid       	;
    	private String	  template	     	;
    	private String	  attribute        	;
    	private String	  attributevalue    ;
    	private String	  attributereq      ;
    	private String	  attributevalid    ;
    	private String	  attributelovflag  ;
    	private String	  attributelovname  ;
    	private String	  attributeDefaultValue  ;
    	private String	  mercuryinclude  ;
          
           
            public ItemInfoRec(){
            }
            public ItemInfoRec (BigDecimal projectid,BigDecimal itemid,
    				BigDecimal templateid,String template,
    				String attribute, String attributevalue,String attributereq,String attributevalid
    				,String attributelovflag,String attributelovname,String attributeDefaultValue,String mercuryInclude) {
    			super();
    			this.projectid = projectid;
    			this.itemid = itemid;
    			this.templateid = templateid;
    			this.template = template;
    			this.attribute = attribute;
    			this.attributevalue = attributevalue;
    			this.attributereq = attributereq;
    			this.attributevalid = attributevalid;
    			this.attributelovflag = attributelovflag;
    			this.attributelovname = attributelovname;
    			this.attributeDefaultValue = attributeDefaultValue;
    			this.mercuryinclude = mercuryInclude;
            }
           
    		public BigDecimal getProjectid() {
    			return projectid;
    		}

    		public void setProjectid(BigDecimal projectid) {
    			this.projectid = projectid;
    		}

    		public BigDecimal getItemid() {
    			return itemid;
    		}

    		public void setItemid(BigDecimal itemid) {
    			this.itemid = itemid;
    		}

    		public BigDecimal getTemplateid() {
    			return templateid;
    		}

    		public void setTemplateid(BigDecimal templateid) {
    			this.templateid = templateid;
    		}

    		public String getTemplate() {
    			return template;
    		}

    		public void setTemplate(String template) {
    			this.template = template;
    		}

    		public String getAttribute() {
    			return attribute;
    		}

    		public void setAttribute(String attribute) {
    			this.attribute = attribute;
    		}

    		public String getAttributevalue() {
    			return attributevalue;
    		}

    		public void setAttributevalue(String attributevalue) {
    			this.attributevalue = attributevalue;
    		}

            public String getAttributereq() {
				return attributereq;
			}
			public void setAttributereq(String attributereq) {
				this.attributereq = attributereq;
			}
			public String getAttributevalid() {
				return attributevalid;
			}
			public void setAttributevalid(String attributevalid) {
				this.attributevalid = attributevalid;
			}

			public String getAttributelovflag() {
				return attributelovflag;
			}
			public void setAttributelovflag(String attributelovflag) {
				this.attributelovflag = attributelovflag;
			}
			public String getAttributelovname() {
				return attributelovname;
			}
			public void setAttributelovname(String attributelovname) {
				this.attributelovname = attributelovname;
			}
			
			public String getAttributeDefaultValue() {
				return attributeDefaultValue;
			}
			public void setAttributeDefaultValue(String attributeDefaultValue) {
				this.attributeDefaultValue = attributeDefaultValue;
			}
			
			
/*			public String getSQLTypeName() throws SQLException {
                return "CANON_E008_PROJ_ITEM_ATT_REC";
            }*/

			
			public String getMercuryinclude() {
				return mercuryinclude;
			}
			public void setMercuryinclude(String mercuryinclude) {
				this.mercuryinclude = mercuryinclude;
			}
			
			public static RowMapper getRowMapper(){
                return new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new ItemInfoRec(
                				rs.getBigDecimal("PROJECT_ID")==null?new BigDecimal(0):rs.getBigDecimal("PROJECT_ID"),
                   				rs.getBigDecimal("ITEM_ID")==null?new BigDecimal(0):rs.getBigDecimal("ITEM_ID"),
                   				rs.getBigDecimal("TEMPLATE_ID")==null?new BigDecimal(0):rs.getBigDecimal("TEMPLATE_ID"),
                   				rs.getString("TEMPLATE")==null?"":rs.getString("TEMPLATE"),
                     			rs.getString("ATTRIBUTE")==null?"":rs.getString("ATTRIBUTE"),
                        		rs.getString("ATTRIBUTE_VALUE")==null?"":rs.getString("ATTRIBUTE_VALUE"),
                        	    rs.getString("ATTRIBUTE_REQ")==null?"":rs.getString("ATTRIBUTE_REQ"),
                        		rs.getString("ATTRIBUTE_VALID")==null?"":rs.getString("ATTRIBUTE_VALID"),		
                           	    rs.getString("ATTRIBUTE_LOVFLAG")==null?"":rs.getString("ATTRIBUTE_LOVFLAG"),
                           		rs.getString("ATTRIBUTE_LOVNAME")==null?"":rs.getString("ATTRIBUTE_LOVNAME"),
                           		rs.getString("ATTRIBUTE_DEFVALUE")==null?"":rs.getString("ATTRIBUTE_DEFVALUE"),	
                           		rs.getString("MERCURY_INCLUDE")==null?"":rs.getString("MERCURY_INCLUDE")		
                        );
                    }
                };
            }
			
            public void readSQL(SQLInput stream, String typeName) throws SQLException {
            	   projectid = stream.readBigDecimal();
                   itemid = stream.readBigDecimal();
                   templateid = stream.readBigDecimal();
                   template = stream.readString();
                   attribute=stream.readString();
                   attributevalue = stream.readString();
                   attributereq = stream.readString();
                   attributevalid = stream.readString();
                   attributelovflag = stream.readString();
                   attributelovname = stream.readString();
                   attributeDefaultValue = stream.readString();
                   mercuryinclude = stream.readString();
            }

            public void writeSQL(SQLOutput stream) throws SQLException {
                   stream.writeBigDecimal(projectid);
                   stream.writeBigDecimal(itemid);
                   stream.writeBigDecimal(templateid);
                   stream.writeString(template);
                   stream.writeString(attribute);
                   stream.writeString(attributevalue);
                   stream.writeString(attributereq);
                   stream.writeString(attributevalid);
                   stream.writeString(attributelovflag);
                   stream.writeString(attributelovname);
                   stream.writeString(attributeDefaultValue);
                   stream.writeString(mercuryinclude);
            }

    } 

    public static class mainiteminfoRec {
        private BigDecimal  projectid        	;
        private BigDecimal  itemid           	;
    	private BigDecimal  templateid       	;
    	private String	  template	     	;
    	private String	  attribute        	;
    	private String	  attributevalue    ;
    	private String	  attributereq      ;
    	private String	  attributevalid    ;
           
            public mainiteminfoRec(){
            }
            public mainiteminfoRec (BigDecimal projectid,BigDecimal itemid,
    				BigDecimal templateid,String template,
    				String attribute, String attributevalue,String attributereq,String attributevalid ) {
    			super();
    			this.projectid = projectid;
    			this.itemid = itemid;
    			this.templateid = templateid;
    			this.template = template;
    			this.attribute = attribute;
    			this.attributevalue = attributevalue;
    			this.attributereq = attributereq;
    			this.attributevalid = attributevalid;
            }
           
			public BigDecimal getProjectid() {
    			return projectid;
    		}

    		public void setProjectid(BigDecimal projectid) {
    			this.projectid = projectid;
    		}

    		public BigDecimal getItemid() {
    			return itemid;
    		}

    		public void setItemid(BigDecimal itemid) {
    			this.itemid = itemid;
    		}

    		public BigDecimal getTemplateid() {
    			return templateid;
    		}

    		public void setTemplateid(BigDecimal templateid) {
    			this.templateid = templateid;
    		}

    		public String getTemplate() {
    			return template;
    		}

    		public void setTemplate(String template) {
    			this.template = template;
    		}

    		public String getAttribute() {
    			return attribute;
    		}

    		public void setAttribute(String attribute) {
    			this.attribute = attribute;
    		}

    		public String getAttributevalue() {
    			return attributevalue;
    		}

    		public void setAttributevalue(String attributevalue) {
    			this.attributevalue = attributevalue;
    		}

            public String getAttributereq() {
				return attributereq;
			}
			public void setAttributereq(String attributereq) {
				this.attributereq = attributereq;
			}
			public String getAttributevalid() {
				return attributevalid;
			}
			public void setAttributevalid(String attributevalid) {
				this.attributevalid = attributevalid;
			}

			public static RowMapper getRowMapper(){
                return new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new mainiteminfoRec(
                				rs.getBigDecimal("PROJECT_ID")==null?new BigDecimal(0):rs.getBigDecimal("PROJECT_ID"),
                   				rs.getBigDecimal("ITEM_ID")==null?new BigDecimal(0):rs.getBigDecimal("ITEM_ID"),
                   				rs.getBigDecimal("TEMPLATE_ID")==null?new BigDecimal(0):rs.getBigDecimal("TEMPLATE_ID"),
                   				rs.getString("TEMPLATE")==null?"":rs.getString("TEMPLATE"),
                     			rs.getString("ATTRIBUTE")==null?"":rs.getString("ATTRIBUTE"),
                        		rs.getString("ATTRIBUTE_VALUE")==null?"":rs.getString("ATTRIBUTE_VALUE"),
                        	    rs.getString("ATTRIBUTE_REQ")==null?"":rs.getString("ATTRIBUTE_REQ"),
                        		rs.getString("ATTRIBUTE_VALID")==null?"":rs.getString("ATTRIBUTE_VALID")
                        );
                    }
                };
            }
            public void readSQL(SQLInput stream, String typeName) throws SQLException {
            	   projectid = stream.readBigDecimal();
                   itemid = stream.readBigDecimal();
                   templateid = stream.readBigDecimal();
                   template = stream.readString();
                   attribute=stream.readString();
                   attributevalue = stream.readString();
                   attributereq = stream.readString();
                   attributevalid = stream.readString();
                   
            }

            public void writeSQL(SQLOutput stream) throws SQLException {
                   stream.writeBigDecimal(projectid);
                   stream.writeBigDecimal(itemid);
                   stream.writeBigDecimal(templateid);
                   stream.writeString(template);
                   stream.writeString(attribute);
                   stream.writeString(attributevalue);
                   stream.writeString(attributereq);
                   stream.writeString(attributevalid);
            }

    } 

    
    public static class projectLineHeaderInfo {
    	private String  eztableid;             
    	private BigDecimal  templateid;           
    	private String  workbenchdisplay;     
    	private String  displaysort;          
    	private String  category;              
    	private String  approvalgroupowner;  
    	private String  attributename;        
    	private String  required;              
    	private String  valid;                 
    	private String  defaultvalue;         
    	private BigDecimal  templatelineversion; 
    	private String  createdby;            
    	private Timestamp  creationdate;         
    	private String  lastupdateby;        
    	private Timestamp  lastupdatedate;

        public projectLineHeaderInfo(){
          }

		public projectLineHeaderInfo(String eztableid, BigDecimal templateid,
				String workbenchdisplay, String displaysort, String category,
				String approvalgroupowner, String attributename,
				String required, String valid, String defaultvalue,
				BigDecimal templatelineversion, String createdby,
				Timestamp creationdate, String lastupdateby,
				Timestamp lastupdatedate) {
			super();
			this.eztableid = eztableid;
			this.templateid = templateid;
			this.workbenchdisplay = workbenchdisplay;
			this.displaysort = displaysort;
			this.category = category;
			this.approvalgroupowner = approvalgroupowner;
			this.attributename = attributename;
			this.required = required;
			this.valid = valid;
			this.defaultvalue = defaultvalue;
			this.templatelineversion = templatelineversion;
			this.createdby = createdby;
			this.creationdate = creationdate;
			this.lastupdateby = lastupdateby;
			this.lastupdatedate = lastupdatedate;
		}

		public String getEztableid() {
			return eztableid;
		}

		public void setEztableid(String eztableid) {
			this.eztableid = eztableid;
		}

		public BigDecimal getTemplateid() {
			return templateid;
		}

		public void setTemplateid(BigDecimal templateid) {
			this.templateid = templateid;
		}

		public String getWorkbenchdisplay() {
			return workbenchdisplay;
		}

		public void setWorkbenchdisplay(String workbenchdisplay) {
			this.workbenchdisplay = workbenchdisplay;
		}

		public String getDisplaysort() {
			return displaysort;
		}

		public void setDisplaysort(String displaysort) {
			this.displaysort = displaysort;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getApprovalgroupowner() {
			return approvalgroupowner;
		}

		public void setApprovalgroupowner(String approvalgroupowner) {
			this.approvalgroupowner = approvalgroupowner;
		}

		public String getAttributename() {
			return attributename;
		}

		public void setAttributename(String attributename) {
			this.attributename = attributename;
		}

		public String getRequired() {
			return required;
		}

		public void setRequired(String required) {
			this.required = required;
		}

		public String getValid() {
			return valid;
		}

		public void setValid(String valid) {
			this.valid = valid;
		}

		public String getDefaultvalue() {
			return defaultvalue;
		}

		public void setDefaultvalue(String defaultvalue) {
			this.defaultvalue = defaultvalue;
		}

		public BigDecimal getTemplatelineversion() {
			return templatelineversion;
		}

		public void setTemplatelineversion(BigDecimal templatelineversion) {
			this.templatelineversion = templatelineversion;
		}

		public String getCreatedby() {
			return createdby;
		}

		public void setCreatedby(String createdby) {
			this.createdby = createdby;
		}

		public Timestamp getCreationdate() {
			return creationdate;
		}

		public void setCreationdate(Timestamp creationdate) {
			this.creationdate = creationdate;
		}

		public String getLastupdateby() {
			return lastupdateby;
		}

		public void setLastupdateby(String lastupdateby) {
			this.lastupdateby = lastupdateby;
		}

		public Timestamp getLastupdatedate() {
			return lastupdatedate;
		}

		public void setLastupdatedate(Timestamp lastupdatedate) {
			this.lastupdatedate = lastupdatedate;
		}

	    public static RowMapper getRowMapper(){
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
           			return new projectLineHeaderInfo(
                    		rs.getString("EZTABLEID"),
            				rs.getBigDecimal("TEMPLATE_ID"),
            				rs.getString("WORKBENCH_DISPLAY"),
            				rs.getString("DISPLAY_SORT"),
            				rs.getString("CATEGORY"),
            				rs.getString("APPROVAL_GROUP_OWNER"),
            				rs.getString("ATTRIBUTE_NAME"),
            				rs.getString("REQUIRED"),
            				rs.getString("VALID"),
            				rs.getString("DEFAULT_VALUE"),
            				rs.getBigDecimal("TEMPLATE_LINE_VERSION"),
            				rs.getString("CREATED_BY"),
            				rs.getTimestamp("CREATION_DATE"),
            				rs.getString("LAST_UPDATE_BY"),
            				rs.getTimestamp("LAST_UPDATE_DATE")
                    );
                }
           };
	    }
	    
		 public String getSQLTypeName() throws SQLException {
             return "CANON_E008_ITEM_TEMPL_ATTR_REC";
         }		

    }
    
    public static interface RowMapper {
        Object mapRow(ResultSet rs, int rowNum) throws SQLException;
    }

    public static String getFullName(String userName) throws Exception {
        clearException();
        String fullName = null;
        StringBuffer qry = new StringBuffer(20);
        Statement statement = null;
        ResultSet resultSet = null;
        Connection oracleconnection = null;


        qry.append(" SELECT UPPER(papf.first_name || ' ' || papf.last_name) full_name \n");
        qry.append("   FROM per_all_people_f papf \n");
        qry.append("  WHERE 1=1\n");

        if (userName != null && !("".equals(userName))) {
            qry.append(" AND upper(papf.employee_number) = '");
            qry.append(userName.toUpperCase());
            qry.append("'\n");
        }


        //logMessage(qry.toString());
        try {
            oracleconnection = (Connection) TransactionScope.getConnection();
            if (oracleconnection != null) {
                statement = oracleconnection.createStatement();
                if (statement != null) {
                    resultSet = statement.executeQuery(qry.toString());


                    if (resultSet.next()) {
                        fullName = resultSet.getString(1);

                    } else {

                        logErrorMessage("dbStatus: WHILE RETRIEVING THE RESULTSET");
                    }

                } else {
                    logErrorMessage("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");
                }
            } else {
                logErrorMessage("dbStatus: UNABLE_TO_GET_CONNECTION");
            }
        } catch (SQLException sqlExp) {
            logErrorMessage("SQLexception: " + sqlExp);
            throw sqlExp;
        } catch (Exception exception) {
            logErrorMessage("exception: " + exception);
        } finally {
            closeResources(resultSet);
            closeResources(statement);
            try {
                if (oracleconnection != null) {
                    TransactionScope.releaseConnection(oracleconnection);
                }
            } catch (Exception eExp) {
            }
        }
        return fullName;
    }

    private static void logErrorMessage(String message) {
        System.err.println(message);
    }

    private static void logMessage(String message) {
        System.out.println(message);
    }

    private static void closeResources(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception exception) {
                logErrorMessage("Unable to close result set:  " + exception);
            }
        }
    }

    public static void closeResources(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception exception) {
                logErrorMessage("Unable to close statement:  " + exception);
            }
        }
    }

    
   public static Object[] getProcessorList(String p_processor){
        clearException();
        System.out.println("in getProcessorList "+p_processor);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_PROCESSOR_LIST);
                if (statement != null) {
                    statement.setObject(1, p_processor, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),ProjectProcessorInfo.getRowMapper())
                        ,statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   

    // Database name CANDEV
    /*
    {call CANON_E590_ITEM_PROCESS_PKG.GET_PROCESSOR_LIST('',?,?,?)}
    (
    FULL_NAME VARCHAR2,
    EMPLOYEE_NUMBER VARCHAR2,
    PERSON_ID NUMBER
    )
    */
    public static class ProjectProcessorInfo {
       private String fullName;
       private String employeeNumber;
       private BigDecimal personId;

        public ProjectProcessorInfo(){
        }
        public ProjectProcessorInfo(String fullName, 
                String employeeNumber, 
                BigDecimal personId){
            this.fullName=fullName;
            this.employeeNumber=employeeNumber;
            this.personId=personId;
        }
        public String getFullName() {
            return fullName;
        }
        public void setFullName(String fullName) {
            this.fullName=fullName;
        }
        public String getEmployeeNumber() {
            return employeeNumber;
        }
        public void setEmployeeNumber(String employeeNumber) {
            this.employeeNumber=employeeNumber;
        }
        public BigDecimal getPersonId() {
            return personId;
        }
        public void setPersonId(BigDecimal personId) {
            this.personId=personId;
        }
        public static RowMapper getRowMapper(){
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ProjectProcessorInfo(
                        rs.getString("FULL_NAME"),
                        rs.getString("EMPLOYEE_NUMBER"),
                        rs.getBigDecimal("PERSON_ID")
                    );
                }
            };
        }
        public String toString() {
            return "ProjectProcessorInfo{" + "fullName="+fullName+", employeeNumber="+employeeNumber+", personId="+personId+'}';
        }
    }
   
   public static Object[] deleteProjectItems(BigDecimal p_project_id,
    BigDecimal p_item_id){
        clearException();
        System.out.println("in deleteProjectItems "+p_project_id+"|"+p_item_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(DELETE_PROJECT_ITEMS);
                if (statement != null) {
                    statement.setObject(1, p_project_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_item_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
    

   public static Object[] deleteProject(BigDecimal p_project_id){
        clearException();
        System.out.println("in deleteProject "+p_project_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(DELETE_PROJECT);
                if (statement != null) {
                    statement.setObject(1, p_project_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   // Added by Madhusudan Duna
   public static Object[] closeProject(BigDecimal p_project_id){
       clearException();
       System.out.println("in closeProject "+p_project_id);
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(CLOSE_PROJECT);
               if (statement != null) {
                   statement.setObject(1, p_project_id, OracleTypes.NUMBER);
                   statement.registerOutParameter(2, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{statement.getObject(2)
                       ,statement.getObject(3)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           } 
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }     // Added by Madhusudan Duna

   // Added by Madhusudan Duna
   public static Object[] getRoleEmailAddress(String p_role_id){
       clearException();
       System.out.println("in getRoleEmailAddress "+p_role_id);
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_ROLE_EMAIL);
               if (statement != null) {
                   statement.setObject(1, p_role_id, OracleTypes.VARCHAR);
                   statement.registerOutParameter(2, OracleTypes.CURSOR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.execute();
                   //return new String[]{statement.getString(2)};
                   return new Object[]{cursorToList((ResultSet)statement.getObject(2),stringRowMapper())
                		   				,statement.getObject(3)
                   						};
                   
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           } 
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }     // Added by Madhusudan Duna
   
  
   public static Object[] startapprovalprocess(BigDecimal p_project_id){
        clearException();
        System.out.println("in startapprovalprocess "+p_project_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(START_APPRV_PROCESS);
                if (statement != null) {
                	System.out.println("in startapprovalprocess 1");
                    statement.setObject(1, p_project_id, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    System.out.println("in startapprovalprocess 2");
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    System.out.println("in startapprovalprocess 3");
                    statement.execute();
                    return new Object[]{statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   public static Object[] getRespName(String p_resp_id){
        clearException();
        System.out.println("in getRespName "+p_resp_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_RESP_NAME);
                if (statement != null) {
                    statement.setObject(1, p_resp_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(2)
                        ,statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   public static Object[] getcurrentapprovals(BigDecimal p_project_no){
        clearException();
        System.out.println("in getcurrentapprovals "+p_project_no);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_PROJECT_HISTORY);
                if (statement != null) {
                    statement.setObject(1, p_project_no, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),ApprovalStatusInfo.getRowMapper())
                        ,statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

/*
{call CANON_E590_ITEM_PROCESS_PKG.GET_PROJECT_HISTORY('1259',?,?,?)}
(
PROJECT_ID NUMBER,
APPROVING_DEPARTMENT VARCHAR2,
APPROVER_ROLE VARCHAR2,
APPROVER_NAME VARCHAR2,
APPROVAL_STATUS VARCHAR2,
APPROVER_COMMENTS VARCHAR2,
NOTIFICATION_ID NUMBER,
ITEM_TYPE VARCHAR2,
ITEM_KEY VARCHAR2,
CREATED_BY NUMBER,
CREATION_DATE DATE,
LAST_UPDATE_BY NUMBER,
LAST_UPDATE_DATE DATE,
RESP_ID NUMBER
)
*/
public static class ApprovalStatusInfo {
   private BigDecimal projectId;
   private String approvingDepartment;
   private String approverRole;
   private String approverName;
   private String approvalStatus;
   private String approverComments;
   private String age;
   private BigDecimal createdBy;
   private Timestamp creationDate;
   private BigDecimal lastUpdateBy;
   private Timestamp lastUpdateDate;

    public ApprovalStatusInfo(){
    }
    public ApprovalStatusInfo(BigDecimal projectId, 
            String approvingDepartment, 
            String approverRole, 
            String approverName, 
            String approvalStatus, 
            String approverComments,
            String age,
            BigDecimal createdBy, 
            Timestamp creationDate, 
            BigDecimal lastUpdateBy, 
            Timestamp lastUpdateDate
            ){
        this.projectId=projectId;
        this.approvingDepartment=approvingDepartment;
        this.approverRole=approverRole;
        this.approverName=approverName;
        this.approvalStatus=approvalStatus;
        this.approverComments=approverComments;
        this.age=age;
        this.createdBy=createdBy;
        this.creationDate=creationDate;
        this.lastUpdateBy=lastUpdateBy;
        this.lastUpdateDate=lastUpdateDate;
        /*this.respId=respId;*/
    }
    public BigDecimal getProjectId() {
        return projectId;
    }
    public void setProjectId(BigDecimal projectId) {
        this.projectId=projectId;
    }
    public String getApprovingDepartment() {
        return approvingDepartment;
    }
    public void setApprovingDepartment(String approvingDepartment) {
        this.approvingDepartment=approvingDepartment;
    }
    public String getApproverRole() {
        return approverRole;
    }
    public void setApproverRole(String approverRole) {
        this.approverRole=approverRole;
    }
    public String getApproverName() {
        return approverName;
    }
    public void setApproverName(String approverName) {
        this.approverName=approverName;
    }
    public String getApprovalStatus() {
        return approvalStatus;
    }
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus=approvalStatus;
    }
    public String getApproverComments() {
        return approverComments;
    }
    public void setApproverComments(String approverComments) {
        this.approverComments=approverComments;
    }

    public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public BigDecimal getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy=createdBy;
    }
    public Timestamp getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate=creationDate;
    }
    public BigDecimal getLastUpdateBy() {
        return lastUpdateBy;
    }
    public void setLastUpdateBy(BigDecimal lastUpdateBy) {
        this.lastUpdateBy=lastUpdateBy;
    }
    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate=lastUpdateDate;
    }

    public static RowMapper getRowMapper(){
        return new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ApprovalStatusInfo(
                    rs.getBigDecimal("PROJECT_ID"),
                    rs.getString("APPROVING_DEPARTMENT"),
                    rs.getString("APPROVER_ROLE"),
                    rs.getString("APPROVER_NAME"),
                    rs.getString("APPROVAL_STATUS"),
                    rs.getString("APPROVER_COMMENTS"),
                    rs.getString("AGING_DAYS"),
                    rs.getBigDecimal("CREATED_BY"),
                    rs.getTimestamp("CREATION_DATE"),
                    rs.getBigDecimal("LAST_UPDATE_BY"),
                    rs.getTimestamp("LAST_UPDATE_DATE")
                    /*rs.getBigDecimal("RESP_ID")*/
                );
            }
        };
    }
/*    public String toString() {
        return "ApprovalStatusInfo{" + "projectId="+projectId+", approvingDepartment="+approvingDepartment+", approverRole="+approverRole+", approverName="+approverName+", approvalStatus="+approvalStatus+", approverComments="+approverComments+", notificationId="+notificationId+", itemType="+itemType+", itemKey="+itemKey+", createdBy="+createdBy+", creationDate="+creationDate+", lastUpdateBy="+lastUpdateBy+", lastUpdateDate="+lastUpdateDate+", respId="+respId+'}';
    }*/
}

  public static Object[] checkViewProject(BigDecimal p_project_id,
    String p_resp_id,
    String p_user_id){
        clearException();
        System.out.println("in checkViewProject "+p_project_id+"|"+p_resp_id+"|"+p_user_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CHECK_VIEW_PROJECT);
                if (statement != null) {
                    statement.setObject(2, p_project_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_resp_id, OracleTypes.VARCHAR);
                    statement.setObject(4, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.CHAR);
                    statement.execute();
                    System.out.println("in checkViewProject returns "+statement.getObject(1));
                    return new Object[]{statement.getObject(1)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   public static Object[] checkSubmitApprovalProject(BigDecimal p_project_id,
    String p_resp_id,
    String p_user_id){
        clearException();
        System.out.println("in checkSubmitApprovalProject "+p_project_id+"|"+p_resp_id+"|"+p_user_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CHECK_SUBMIT_APPROVAL_PROJECT);
                if (statement != null) {
                    statement.setObject(2, p_project_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_resp_id, OracleTypes.VARCHAR);
                    statement.setObject(4, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.CHAR);
                    statement.execute();
                    System.out.println("in checkSubmitApprovalProject returns "+statement.getObject(1));
                    return new Object[]{statement.getObject(1)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   public static Object[] checkModifyProject(BigDecimal p_project_id,
    String p_resp_id,
    String p_user_id){
        clearException();
        System.out.println("in checkModifyProject "+p_project_id+"|"+p_resp_id+"|"+p_user_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CHECK_MODIFY_PROJECT);
                if (statement != null) {
                    statement.setObject(2, p_project_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_resp_id, OracleTypes.VARCHAR);
                    statement.setObject(4, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.CHAR);
                    statement.execute();
                    System.out.println("in checkModifyProject returns "+statement.getObject(1));
                    return new Object[]{statement.getObject(1)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   public static Object[] checkDeleteProject(BigDecimal p_project_id,
    String p_resp_id,
    String p_user_id){
        clearException();
        System.out.println("in checkDeleteProject "+p_project_id+"|"+p_resp_id+"|"+p_user_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CHECK_DELETE_PROJECT);
                if (statement != null) {
                    statement.setObject(2, p_project_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_resp_id, OracleTypes.VARCHAR);
                    statement.setObject(4, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.CHAR);
                    statement.execute();
                    System.out.println("in checkDeleteProject returns "+statement.getObject(1));
                    return new Object[]{statement.getObject(1)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   public static Object[] checkCreateProject(BigDecimal p_project_id,
    String p_resp_id,
    String p_user_id){
        clearException();
        System.out.println("in checkCreateProject "+p_project_id+"|"+p_resp_id+"|"+p_user_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CHECK_CREATE_PROJECT);
                if (statement != null) {
                    statement.setObject(2, p_project_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_resp_id, OracleTypes.VARCHAR);
                    statement.setObject(4, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.CHAR);
                    statement.execute();
                    System.out.println("in checkCreateProject returns "+statement.getObject(1));
                    return new Object[]{statement.getObject(1)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   public static Object[] validateProject(BigDecimal p_project_no,
    String p_resp_id,
    String p_user_id){
        System.out.println("in validateProject "+p_project_no+"|"+p_resp_id+"|"+p_user_id);
        CallableStatement statement = null;
        Connection connection = null;      
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(VALIDATE_PROJECT);
                if (statement != null) {
                    statement.setObject(1, p_project_no, OracleTypes.NUMBER);
                    statement.setObject(2, p_resp_id, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.CURSOR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(4),ProjectValidateInfo.getRowMapper())
                        ,statement.getObject(5)
                        ,statement.getObject(6)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
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
        return null;
   }   

   public static Object[] validateUploaditems(BigDecimal p_project_no,
		   List p_item_tbl, String p_user_id){
		        System.out.println("in validateUploaditems "+p_project_no+"|"+"|"+p_user_id);
		        CallableStatement statement = null;
		        Connection connection = null;
		        try {
		            connection = TransactionScope.getConnection();
		            if (connection != null) {
		                statement = (CallableStatement) connection.prepareCall(VALIDATE_UPLOAD_ITEMS);
		                if (statement != null) {
		                    statement.setObject(1, p_project_no, OracleTypes.NUMBER);
		                    
		                    Connection nativeConnection=TransactionScope.nativeConnection(connection); 
		                    ArrayDescriptor arrydesc = ArrayDescriptor.createDescriptor("CANON_E008_PROJ_ITEMS_TBL_TYPE",  nativeConnection);
		                    Object[] java_record_array   = new Object[p_item_tbl.size()];
		                    for(int i=0;i<p_item_tbl.size();i++)
		                    	java_record_array[i]=p_item_tbl.get(i);    
		                    
		                    
		                    ARRAY canonE008ProjItemList=new ARRAY(arrydesc, nativeConnection, java_record_array);
		                    System.out.println("validateUploaditems convertion done::: ");
		                    
		                    statement.setArray(2,canonE008ProjItemList);
		                    //statement.setArray(2, canonE008ItemsTblTypeListToArray(p_item_tbl, connection), OracleTypes.ARRAY);
		                    System.out.println("in validateUploaditems 2 ");
		                    
		                    
		                    statement.setObject(3, p_user_id, OracleTypes.VARCHAR);
		                    //statement.registerOutParameter(4, OracleTypes.CURSOR);
		                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
		                    statement.execute();
		                    //return new Object[]{cursorToList((ResultSet)statement.getObject(4),ProjectValidateInfo.getRowMapper())
		                   	return new Object[]{statement.getObject(4)		
		                        ,statement.getObject(5)
		                        ,statement.getObject(6)};
		                } else {
		                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
		                }
		            } else {
		                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
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
		        return null;
		   }   

   
    // Database name CANDEV
    /*
    {call CANON_E590_ITEM_PROCESS_PKG.VALIDATE_PROJECT(1022,50347,381817,?,?,?)}
    (
    PROJECT_ID NUMBER,
    ITEM_ID NUMBER,
    FIELD_NAME VARCHAR2,
    MESSAGE VARCHAR2
    )
    */
    public static class ProjectValidateInfo {
       private BigDecimal projectId;
       private String userId;
       private String itemId;
       private String fieldName;
       private String message;

        public ProjectValidateInfo(){
        }
        public ProjectValidateInfo(BigDecimal projectId, 
        		String userId,
        		String itemId,
                String fieldName, 
                String message){
            this.projectId=projectId;
            this.userId=userId;
            this.itemId=itemId;
            this.fieldName=fieldName;
            this.message=message;
        }

        public BigDecimal getProjectId() {
            return projectId;
        }
        public void setProjectId(BigDecimal projectId) {
            this.projectId=projectId;
        }

        public String getUserId() {
			return userId;
		}
		public void setUserIdId(String userId) {
			this.userId = userId;
		}
        public String getItemId() {
            return itemId;
        }
        public void setItemId(String itemId) {
            this.itemId=itemId;
        }
        public String getFieldName() {
            return fieldName;
        }
        public void setFieldName(String fieldName) {
            this.fieldName=fieldName;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message=message;
        }
        public static RowMapper getRowMapper(){
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ProjectValidateInfo(
                        rs.getBigDecimal("PROJECT_ID"),
                        rs.getString("USER_ID"),
                        rs.getString("ITEM_ID"),
                        rs.getString("FIELD_NAME"),
                        rs.getString("MESSAGE")
                    );
                }
            };
        }
        public String toString() {
            return "ProjectValidateInfo{" + "projectId="+projectId+", userId="+userId+", fieldName="+fieldName+", message="+message+'}';
        }
    }

    public static class ProjectReqValidateInfo {
        private BigDecimal projectId;
        private BigDecimal templateId;
        //private BigDecimal itemId;
        private String fieldName;
        private String message;

         public ProjectReqValidateInfo(){
         }
         public ProjectReqValidateInfo(BigDecimal projectId, 
                 BigDecimal templateId,
                 //BigDecimal itemId,
                 String fieldName, 
                 String message){
             this.projectId=projectId;
             this.templateId=templateId;
             //this.itemId=itemId;
             this.fieldName=fieldName;
             this.message=message;
         }
         public BigDecimal getProjectId() {
             return projectId;
         }
         public void setProjectId(BigDecimal projectId) {
             this.projectId=projectId;
         }

         
         public BigDecimal getTemplateId() {
 			return templateId;
 		}
 		public void setTemplateId(BigDecimal templateId) {
 			this.templateId = templateId;
 		}
 /*        public BigDecimal getItemId() {
             return itemId;
         }
         public void setItemId(BigDecimal itemId) {
             this.itemId=itemId;
         }
 */        public String getFieldName() {
             return fieldName;
         }
         public void setFieldName(String fieldName) {
             this.fieldName=fieldName;
         }
         public String getMessage() {
             return message;
         }
         public void setMessage(String message) {
             this.message=message;
         }
         public static RowMapper getRowMapper(){
             return new RowMapper() {
                 public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                     return new ProjectReqValidateInfo(
                         rs.getBigDecimal("PROJECT_ID"),
                         rs.getBigDecimal("TEMPLATE_ID"),
                         //rs.getBigDecimal("ITEM_ID"),
                         rs.getString("FIELD_NAME"),
                         rs.getString("MESSAGE")
                     );
                 }
             };
         }
         public String toString() {
             return "ProjectReqValidateInfo{" + "projectId="+projectId+", templateId="+templateId+", fieldName="+fieldName+", message="+message+'}';
         }
     }
    
    
   public static Object[] notificationAction(String p_project_id,
    String p_user_id,
    String p_comments,
    String p_role_id,    
    String p_action){
        clearException();
        System.out.println("in notificationAction "+p_project_id+"|"+p_user_id+"|"+p_comments+"|"+p_action);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(NOTIFICATION_ACTION);
                if (statement != null) {
                    statement.setObject(1, p_project_id, OracleTypes.VARCHAR);
                    statement.setObject(2, p_user_id, OracleTypes.VARCHAR);
                    statement.setObject(3, p_comments, OracleTypes.VARCHAR);
                    statement.setObject(4, p_role_id, OracleTypes.VARCHAR);                    
                    statement.setObject(5, p_action, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
                    statement.registerOutParameter(7, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(6)
                        ,statement.getObject(7)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   public static Object[] addNotes(BigDecimal p_project_id,
		    String p_user_id,
		    String p_comments
		    ){
		        clearException();
		        System.out.println("in addNotes "+p_project_id+"|"+p_user_id+"|"+p_comments);
		        CallableStatement statement = null;
		        Connection connection = null;
		        try {
		            connection = TransactionScope.getConnection();
		            if (connection != null) {
		                statement = (CallableStatement) connection.prepareCall(ADD_NOTES);
		                if (statement != null) {
		                    statement.setObject(1, p_project_id, OracleTypes.NUMBER);
		                    statement.setObject(2, p_user_id, OracleTypes.VARCHAR);
		                    statement.setObject(3, p_comments, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
		                    statement.execute();
		                    return new Object[]{statement.getObject(4)
		                        ,statement.getObject(5)};
		                } else {
		                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
		                }
		            } else {
		                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		            }
		        } catch (Exception ex) {
		            saveException(ex);
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
		        return null;
   }  
   
   public static Object[] getDbName(){
        clearException();
        System.out.println("in getDbName ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_DB_NAME);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(1)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   public static Object[] checkValidateRejectProject(BigDecimal p_project_id,
    String p_resp_id,
    String p_user_id){
        clearException();
        System.out.println("in checkValidateRejectProject "+p_project_id+"|"+p_resp_id+"|"+p_user_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CHECK_VALIDATE_REJECT_PROJECT);
                if (statement != null) {
                    statement.setObject(2, p_project_id, OracleTypes.NUMBER);
                    statement.setObject(3, p_resp_id, OracleTypes.VARCHAR);
                    statement.setObject(4, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.CHAR);
                    statement.execute();
                    System.out.println("in checkValidateRejectProject returns "+statement.getObject(1));
                    return new Object[]{statement.getObject(1)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   public static Object[] validateRequiredFields(BigDecimal p_project_id, BigDecimal p_template_id
    //String p_resp_id, String p_user_id
		   ){
        //clearException();
        System.out.println("in validateRequiredFields "+p_template_id);
        
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(VALIDATE_REQUIRED_FIELDS);
                if (statement != null) {
                    statement.setObject(1, p_project_id, OracleTypes.NUMBER);
                    statement.setObject(2, p_template_id, OracleTypes.NUMBER);
/*                    statement.setObject(2, p_resp_id, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_id, OracleTypes.VARCHAR);
*/                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("in checkValidateRejectProject returns "+statement.getObject(4));
                    return new Object[]{cursorToList((ResultSet)statement.getObject(3),ProjectReqValidateInfo.getRowMapper())
                        ,statement.getObject(4)
                        ,statement.getObject(5)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   public static Object[] countergroupList(){
       // clearException();
        System.out.println("in countergroupList ");        
        
        
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(COUNTERGROUP_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

    public static RowMapper stringRowMapper() {
        return new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        };
    }
    
   
   public static Object[] validateOwnershipFields(BigDecimal p_project_no,
    String p_role_id,
    String p_user_id){
       
        System.out.println("in validateOwnershipFields "+p_project_no+"|"+p_role_id+"|"+p_user_id);
                       
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(VALIDATE_OWNERSHIP_FIELDS);
                if (statement != null) {
                    statement.setObject(1, p_project_no, OracleTypes.NUMBER);
                    statement.setObject(2, p_role_id, OracleTypes.VARCHAR);
                    statement.setObject(3, p_user_id, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.CURSOR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.registerOutParameter(6, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(4),ProjectValidateInfo.getRowMapper())
                            ,statement.getObject(5)
                            ,statement.getObject(6)};
                      
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }  
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   

   public static Object[] checkStatusMonitor(BigDecimal p_project_id){
        clearException();
        System.out.println("in checkStatusMonitor "+p_project_id);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CHECK_STATUS_MONITOR);
                if (statement != null) {
                    statement.setObject(2, p_project_id, OracleTypes.NUMBER);
                    System.out.println("in checkStatusMonitor 1");
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    System.out.println("in checkStatusMonitor 2");
                    statement.execute();
                    System.out.println("in checkStatusMonitor 3 "+statement.getObject(1));
                    return new Object[]{statement.getObject(1)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   public static Object[] getCcidDesc(String p_segments){
        clearException();
        System.out.println("in getCcidDesc "+p_segments);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_CCID_DESC);
                if (statement != null) {
                    statement.setObject(2, p_segments, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{statement.getObject(1)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   

   public static Object[] templateList(){
       clearException();
       System.out.println("in templateList ");
               
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(TEMPLATE_LIST);
               if (statement != null) {
                   statement.registerOutParameter(1, OracleTypes.CURSOR);
                   statement.registerOutParameter(2, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                       ,statement.getObject(2)
                       ,statement.getObject(3)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  } 

   public static Object[] CattemplateList(String projectCat){
       clearException();
       System.out.println("in CattemplateList ");
               
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(CAT_TEMPLATE_LIST);
               if (statement != null) {
               	statement.setObject(1, projectCat, OracleTypes.VARCHAR);
                   statement.registerOutParameter(2, OracleTypes.CURSOR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(2),stringRowMapper())
                       ,statement.getObject(3)
                       ,statement.getObject(4)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }
   
   public static Object[] TemplateAttributeList(){
       clearException();
       System.out.println("in TemplateAttributeList ");
               
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(TEMPLATE_ATTRIBUTE_LIST);
               if (statement != null) {
                   statement.registerOutParameter(1, OracleTypes.CURSOR);
                   statement.registerOutParameter(2, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                       ,statement.getObject(2)
                       ,statement.getObject(3)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }
   
   public static Object[] TemplateStatusList(){
       clearException();
       System.out.println("in TemplateStatusList ");
               
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(TEMPLATE_STATUS_LIST);
               if (statement != null) {
                   statement.registerOutParameter(1, OracleTypes.CURSOR);
                   statement.registerOutParameter(2, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                       ,statement.getObject(2)
                       ,statement.getObject(3)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }
   
   public static Object[] softLicenseList(){
        clearException();
        System.out.println("in softLicenseList ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SOFT_LICENSE_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   public static Object[] productCodeList(){
        clearException();
        System.out.println("in productCodeList ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(PRODUCT_CODE_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   public static Object[] merchList(){
        clearException();
        System.out.println("in merchList ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(MERCH_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   
   public static Object[] supplierList(String p_supplier_str){
        clearException();
        System.out.println("in supplierList "+p_supplier_str);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SUPPLIER_LIST);
                if (statement != null) {
                    statement.setObject(2, p_supplier_str, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }   
   
   public static Object[] supplierSiteList(String p_supplier_name){
        clearException();
        System.out.println("in supplierSiteList "+p_supplier_name);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SUPPLIER_SITE_LIST);
                if (statement != null) {
                    statement.setObject(2, p_supplier_name, OracleTypes.VARCHAR);
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   
   public static Object[] supplyCategoryList(){
        clearException();
        System.out.println("in supplyCategoryList ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SUPPLY_CATEGORY_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   public static Object[] itemTypeList(){
        clearException();
        System.out.println("in itemTypeList ");
              
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(ITEM_TYPE_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   public static Object[] speedList(){
        clearException();
        System.out.println("in speedList ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(SPEED_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};  
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
   
   
   public static Object[] canonTemplateList(){
        clearException();
        System.out.println("in canonTemplateList ");
         
        	
       
     CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(CANON_TEMPLATE_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),CanonTemplateInfo.getRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   
   public static Object[] getItemMainAttributeHeaders(BigDecimal p_project_id,String p_user_id){
       clearException();
       System.out.println("in getItemMainAttributeHedaers ");
               
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_ITEM_MAIN_ATTRIBUTES);
               if (statement != null) {
            	   statement.setObject(1, p_project_id, OracleTypes.NUMBER);
            	   statement.setObject(2, p_user_id, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.CURSOR);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   statement.registerOutParameter(5, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(3),attributeheaderInfo.getRowMapper()) // // stringRowMapper()  
                       ,statement.getObject(4)
                       ,statement.getObject(5)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }
    // Database name CANDEV
    /*
    {call CANON_E590_ITEM_PROCESS_PKG.CANON_TEMPLATE_LIST(?,?,?)}
    (
    CANON_TEMPLATE VARCHAR2,
    ITEM_TYPE VARCHAR2,
    TEMPLATE VARCHAR2,
    MERCH_TYPE VARCHAR2,
    PROD_CODE VARCHAR2,
    THIRD_PARTY VARCHAR2
    )
    */
    public static class CanonTemplateInfo {
       private String canonTemplate;
       private String itemType;
       private String template;
       private String merchType;
       private String prodCode;
       private String thirdParty;

        public CanonTemplateInfo(){
        }
        public CanonTemplateInfo(String canonTemplate, 
                String itemType, 
                String template, 
                String merchType, 
                String prodCode, 
                String thirdParty){
            this.canonTemplate=canonTemplate;
            this.itemType=itemType;
            this.template=template;
            this.merchType=merchType;
            this.prodCode=prodCode;
            this.thirdParty=thirdParty;
        }
        public String getCanonTemplate() {
            return canonTemplate;
        }
        public void setCanonTemplate(String canonTemplate) {
            this.canonTemplate=canonTemplate;
        }
        public String getItemType() {
            return itemType;
        }
        public void setItemType(String itemType) {
            this.itemType=itemType;
        }
        public String getTemplate() {
            return template;
        }
        public void setTemplate(String template) {
            this.template=template;
        }
        public String getMerchType() {
            return merchType;
        }
        public void setMerchType(String merchType) {
            this.merchType=merchType;
        }
        public String getProdCode() {
            return prodCode;
        }
        public void setProdCode(String prodCode) {
            this.prodCode=prodCode;
        }
        public String getThirdParty() {
            return thirdParty;
        }
        public void setThirdParty(String thirdParty) {
            this.thirdParty=thirdParty;
        }
        public static RowMapper getRowMapper(){
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new CanonTemplateInfo(
                        rs.getString("CSA_TEMPLATE"),
                        rs.getString("ITEM_TYPE"),
                        rs.getString("TEMPLATE"),
                        rs.getString("MERCH_TYPE"),
                        rs.getString("PROD_CODE"),
                        rs.getString("THIRD_PARTY")
                    );
                }
            };
        }
        public String toString() {
            return "CanonTemplateInfo{" + "canonTemplate="+canonTemplate+", itemType="+itemType+", template="+template+", merchType="+merchType+", prodCode="+prodCode+", thirdParty="+thirdParty+'}';
        }
    }

    
    public static class attributeheaderInfo {
        private String attributeName;
        private String e008WBtblMap;
        private String lovFlag;

         
         public attributeheaderInfo(String attributeName, String e008wBtblMap,
				String lovFlag) {
			super();
			this.attributeName = attributeName;
			e008WBtblMap = e008wBtblMap;
			this.lovFlag = lovFlag;
		}

		public attributeheaderInfo() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getAttributeName() {
			return attributeName;
		}

		public void setAttributeName(String attributeName) {
			this.attributeName = attributeName;
		}

		public String getE008WBtblMap() {
			return e008WBtblMap;
		}

		public void setE008WBtblMap(String e008wBtblMap) {
			e008WBtblMap = e008wBtblMap;
		}

		public String getLovFlag() {
			return lovFlag;
		}

		public void setLovFlag(String lovFlag) {
			this.lovFlag = lovFlag;
		}

		public static RowMapper getRowMapper(){
             return new RowMapper() {
                 public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                     return new attributeheaderInfo(
                         rs.getString("ATTRIBUTE_NAME"),
                         rs.getString("E008_WB_TBL_MAP"),
                         rs.getString("LOV_FLAG")
                     );
                 }
             };
         }
     }
    
   public static Object[] thirdPartyList(){
        clearException();
        System.out.println("in thirdPartyList ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(THIRD_PARTY_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }    

   public static Object[] gethistapprovals(BigDecimal p_project_no){
        clearException();
        System.out.println("in getApprHist "+p_project_no);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_APPR_HIST);
                if (statement != null) {
                    statement.setObject(1, p_project_no, OracleTypes.NUMBER);
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),ApprHistInfo.getRowMapper())
                        ,statement.getObject(3)
                        ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

    // Database name CANDEV
    /*
    {call CANON_E590_ITEM_PROCESS_PKG.GET_APPR_HIST(1063,?,?,?)}
    (
    PROJECT_ID NUMBER,
    APPROVING_DEPARTMENT VARCHAR2,
    APPROVER_ROLE VARCHAR2,
    APPROVER_NAME VARCHAR2,
    APPROVAL_STATUS VARCHAR2,
    APPROVER_COMMENTS VARCHAR2,
    NOTIFICATION_ID NUMBER,
    ITEM_TYPE VARCHAR2,
    ITEM_KEY VARCHAR2,
    CREATED_BY NUMBER,
    CREATION_DATE DATE,
    LAST_UPDATE_BY NUMBER,
    LAST_UPDATE_DATE DATE,
    RESP_ID NUMBER
    )
    */
    public static class ApprHistInfo {
       private BigDecimal projectId;
       private String approvingDepartment;
       private String approverRole;
       private String approverName;
       private String approvalStatus;
       private String approverComments;
       private String age;
       private String createdBy;
       private Timestamp creationDate;
       private String lastUpdateBy;
       private Timestamp lastUpdateDate;

        public ApprHistInfo(){
        }
        public ApprHistInfo(BigDecimal projectId, 
                String approvingDepartment, 
                String approverRole, 
                String approverName, 
                String approvalStatus, 
                String approverComments, 
                String age, 
                String createdBy, 
                Timestamp creationDate, 
                String lastUpdateBy, 
                Timestamp lastUpdateDate 
                ){
            this.projectId=projectId;
            this.approvingDepartment=approvingDepartment;
            this.approverRole=approverRole;
            this.approverName=approverName;
            this.approvalStatus=approvalStatus;
            this.approverComments=approverComments;
            this.age=age;
            this.createdBy=createdBy;
            this.creationDate=creationDate;
            this.lastUpdateBy=lastUpdateBy;
            this.lastUpdateDate=lastUpdateDate;
        }
        public BigDecimal getProjectId() {
            return projectId;
        }
        public void setProjectId(BigDecimal projectId) {
            this.projectId=projectId;
        }
        public String getApprovingDepartment() {
            return approvingDepartment;
        }
        public void setApprovingDepartment(String approvingDepartment) {
            this.approvingDepartment=approvingDepartment;
        }
        public String getApproverRole() {
            return approverRole;
        }
        public void setApproverRole(String approverRole) {
            this.approverRole=approverRole;
        }
        public String getApproverName() {
            return approverName;
        }
        public void setApproverName(String approverName) {
            this.approverName=approverName;
        }
        public String getApprovalStatus() {
            return approvalStatus;
        }
        public void setApprovalStatus(String approvalStatus) {
            this.approvalStatus=approvalStatus;
        }
        public String getApproverComments() {
            return approverComments;
        }
        public void setApproverComments(String approverComments) {
            this.approverComments=approverComments;
        }
        
        public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public Timestamp getCreationDate() {
            return creationDate;
        }
        public void setCreationDate(Timestamp creationDate) {
            this.creationDate=creationDate;
        }
        
        public String getLastUpdateBy() {
			return lastUpdateBy;
		}
		public void setLastUpdateBy(String lastUpdateBy) {
			this.lastUpdateBy = lastUpdateBy;
		}
		public Timestamp getLastUpdateDate() {
            return lastUpdateDate;
        }
        public void setLastUpdateDate(Timestamp lastUpdateDate) {
            this.lastUpdateDate=lastUpdateDate;
        }

        public static RowMapper getRowMapper(){
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ApprHistInfo(
                        rs.getBigDecimal("PROJECT_ID"),
                        rs.getString("APPROVING_DEPARTMENT"),
                        rs.getString("APPROVER_ROLE"),
                        rs.getString("APPROVER_NAME"),
                        rs.getString("APPROVAL_STATUS"),
                        rs.getString("APPROVER_COMMENTS"),
                        rs.getString("AGING_DAYS"),
                        rs.getString("CREATED_BY"),
                        rs.getTimestamp("CREATION_DATE"),
                        rs.getString("LAST_UPDATE_BY"),
                        rs.getTimestamp("LAST_UPDATE_DATE")
                    );
                }
            };
        }
/*        public String toString() {
            return "ApprHistInfo{" + "projectId="+projectId+", approvingDepartment="+approvingDepartment+", approverRole="+approverRole+", approverName="+approverName+", approvalStatus="+approvalStatus+", approverComments="+approverComments+", notificationId="+notificationId+", itemType="+itemType+", itemKey="+itemKey+", createdBy="+createdBy+", creationDate="+creationDate+", lastUpdateBy="+lastUpdateBy+", lastUpdateDate="+lastUpdateDate+", respId="+respId+'}';
        }*/
    }


    public static Object[] savebomItems(List p_bom_tbl){
    	        clearException();
    	        System.out.println("in savebomItems "+p_bom_tbl);
    	        CallableStatement statement = null;
    	        Connection connection = null;
    	        try {
    	            connection = TransactionScope.getConnection();
    	            System.out.println("in savebomItems 1 ");
    	            if (connection != null) {
    	                statement = (CallableStatement) connection.prepareCall(SAVE_BOM_ITEMS);
    	                System.out.println("in savebomItems 2 ");
    	                if (statement != null) {
    	                	
    	                	Connection nativeConnection=TransactionScope.nativeConnection(connection);                	                
    	                	System.out.println("in savebomItems 3 ");
    	                    ArrayDescriptor arrydesc = ArrayDescriptor.createDescriptor("CANON_E008_BOM_ITEMS_TBL_TYPE",  nativeConnection);
    	                    System.out.println("in savebomItems 4 ");
    	                    Object[] java_record_array   = new Object[p_bom_tbl.size()];
    	                    System.out.println("in savebomItems 5 ");
    	                    for(int i=0;i<p_bom_tbl.size();i++)
    	                    	java_record_array[i]=p_bom_tbl.get(i);    
    	                    
    	                    System.out.println("in savebomItems 6 ");
    	                    ARRAY canonE008ProjbomItemList=new ARRAY(arrydesc, nativeConnection, java_record_array);
    	                    System.out.println("canonE008ProjbomItemList convertion done::: ");
    	                    
    	                    statement.setArray(1,canonE008ProjbomItemList);
    	                    //statement.setArray(2, canonE008ItemsTblTypeListToArray(p_item_tbl, connection), OracleTypes.ARRAY);
    	                    System.out.println("in saveProjectItems 2 ");
    	                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
    	                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
    	                    statement.execute();
    	                    System.out.println("in saveProjectItems 6 ");
    	                    return new Object[]{ statement.getObject(4),statement.getObject(5) }; 
    	                    
    	                } else {
    	                	System.out.println("in saveProjectItems 7 ");
    	                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
    	                }
    	            } else {
    	            	System.out.println("in saveProjectItems 8 ");
    	                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
    	            }
    	        } catch (Exception ex) {
    	            saveException(ex);
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
    	        return null;
    	   }    
    
    public static Object[] getbomItems(BigDecimal setItemId){
        clearException();
        System.out.println("in getbomItems "+setItemId);
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_BOM_ITEMS_DETAILS);
                if (statement != null) {
             	   System.out.println("in getbomItems 1 "+ setItemId);
                    statement.setObject(1, setItemId, OracleTypes.NUMBER);
                    System.out.println("in getbomItems 2 ");
                    statement.registerOutParameter(2, OracleTypes.CURSOR);
                    System.out.println("in getbomItems 3 ");
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    System.out.println("in getbomItems 4 ");
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    System.out.println("in getbomItems 5 ");
                    statement.execute();
                    System.out.println("in getbomItems 6 ");
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),getCompItems.getRowMapper())
 				                       ,statement.getObject(3)
 				                       ,statement.getObject(4)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                    System.out.println("in getbomItems 8 ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
                System.out.println("in getbomItems 9 ");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }
    
    public class bomDetailsInfo implements java.sql.SQLData, java.io.Serializable  {
   // public static class  bomDetailsInfo {
        private String eztableid;
        private String ezincompanycode;
        private BigDecimal setitemid;
        private String bominstructions;
        private String componentitem;
        private BigDecimal qty;
        private String createdBy;
        private Timestamp creationDate;
        private String lastUpdateBy;
        private Timestamp lastUpdateDate;

        public bomDetailsInfo(){
         }
         
         
         public bomDetailsInfo(String eztableid, String ezincompanycode,
				BigDecimal setitemid, String bominstructions,
				String componentitem, BigDecimal qty, String createdBy,
				Timestamp creationDate, String lastUpdateBy,
				Timestamp lastUpdateDate) {
			super();
			this.eztableid = eztableid;
			this.ezincompanycode = ezincompanycode;
			this.setitemid = setitemid;
			this.bominstructions = bominstructions;
			this.componentitem = componentitem;
			this.qty = qty;
			this.createdBy = createdBy;
			this.creationDate = creationDate;
			this.lastUpdateBy = lastUpdateBy;
			this.lastUpdateDate = lastUpdateDate;
		}

         public String getEztableid() {
			return eztableid;
		}


		public void setEztableid(String eztableid) {
			this.eztableid = eztableid;
		}


		public String getEzincompanycode() {
			return ezincompanycode;
		}


		public void setEzincompanycode(String ezincompanycode) {
			this.ezincompanycode = ezincompanycode;
		}


		public BigDecimal getSetitemid() {
			return setitemid;
		}


		public void setSetitemid(BigDecimal setitemid) {
			this.setitemid = setitemid;
		}


		public String getBominstructions() {
			return bominstructions;
		}


		public void setBominstructions(String bominstructions) {
			this.bominstructions = bominstructions;
		}


		public String getComponentitem() {
			return componentitem;
		}


		public void setComponentitem(String componentitem) {
			this.componentitem = componentitem;
		}


		public BigDecimal getQty() {
			return qty;
		}


		public void setQty(BigDecimal qty) {
			this.qty = qty;
		}


		public String getCreatedBy() {
			return createdBy;
		}


		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}


		public Timestamp getCreationDate() {
			return creationDate;
		}


		public void setCreationDate(Timestamp creationDate) {
			this.creationDate = creationDate;
		}


		public String getLastUpdateBy() {
			return lastUpdateBy;
		}


		public void setLastUpdateBy(String lastUpdateBy) {
			this.lastUpdateBy = lastUpdateBy;
		}


		public Timestamp getLastUpdateDate() {
			return lastUpdateDate;
		}


		public void setLastUpdateDate(Timestamp lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}


		public RowMapper getRowMapper(){
		    return new RowMapper() {
		        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		   			return new bomDetailsInfo (
                         rs.getString("EZTABLEID"),
                         rs.getString("EZINCOMPANYCODE"),
                         rs.getBigDecimal("SET_ITEM_ID"),
                         rs.getString("BOM_INSTRUCTIONS"),
                         rs.getString("COMPONENT_ITEM"),
                         rs.getBigDecimal("QTY"),
                         rs.getString("CREATED_BY"),
                         rs.getTimestamp("CREATION_DATE"),
                         rs.getString("LAST_UPDATE_BY"),
                         rs.getTimestamp("LAST_UPDATE_DATE")
                     );
                 }
             };
         }
		
		public String getSQLTypeName() throws SQLException {
	        return "CANON_E008_BOM_ITEMS_REC";
	    }

	    public void readSQL(SQLInput stream, String typeName) throws SQLException {
	    	eztableid=stream.readString();
	    	ezincompanycode=stream.readString();
	    	setitemid=stream.readBigDecimal();		
	    	bominstructions=stream.readString();
	    	componentitem=stream.readString();
	    	qty=stream.readBigDecimal();
	    	createdBy=stream.readString();
	    	creationDate=stream.readTimestamp();
	    	lastUpdateBy=stream.readString();
	    	lastUpdateDate=stream.readTimestamp();
	    }  	
    
		public void writeSQL(SQLOutput stream) throws SQLException {
	    	stream.writeString(eztableid);
	    	stream.writeString(ezincompanycode);
	    	stream.writeBigDecimal(setitemid);
	    	stream.writeString(bominstructions);
	    	stream.writeString(componentitem);
	    	stream.writeBigDecimal(qty);
	    	stream.writeString(createdBy);
	    	stream.writeTimestamp(creationDate);
	    	stream.writeString(lastUpdateBy);
	    	stream.writeTimestamp(lastUpdateDate);
	    } 	
	    
    }
    
    public static class getCompItems {
        private BigDecimal setItemId;
        private String bomInstructions;
        private String componentItem;
        private String compdesc;
        private String manufacturer;
        private String qty;
        private String merchType;
        private String prodCode;
        private String itemtype;
        private String cost;        
        private String createdBy;
        private Timestamp creationDate;
        private String lastUpdateBy;
        private Timestamp lastUpdateDate;
 
         public getCompItems(){
         }

         
         public getCompItems(BigDecimal setItemId, String bomInstructions,
				String componentItem, String compdesc, String manufacturer,
				String qty, String merchType, String prodCode, String itemtype,
				String cost, String createdBy, Timestamp creationDate,
				String lastUpdateBy, Timestamp lastUpdateDate) {
			super();
			this.setItemId = setItemId;
			this.bomInstructions = bomInstructions;
			this.componentItem = componentItem;
			this.compdesc = compdesc;
			this.manufacturer = manufacturer;
			this.qty = qty;
			this.merchType = merchType;
			this.prodCode = prodCode;
			this.itemtype = itemtype;
			this.cost = cost;
			this.createdBy = createdBy;
			this.creationDate = creationDate;
			this.lastUpdateBy = lastUpdateBy;
			this.lastUpdateDate = lastUpdateDate;
		}


		public BigDecimal getSetItemId() {
			return setItemId;
		}


		public void setSetItemId(BigDecimal setItemId) {
			this.setItemId = setItemId;
		}


		public String getBomInstructions() {
			return bomInstructions;
		}


		public void setBomInstructions(String bomInstructions) {
			this.bomInstructions = bomInstructions;
		}


		public String getComponentItem() {
			return componentItem;
		}


		public void setComponentItem(String componentItem) {
			this.componentItem = componentItem;
		}


		public String getCompdesc() {
			return compdesc;
		}


		public void setCompdesc(String compdesc) {
			this.compdesc = compdesc;
		}


		public String getManufacturer() {
			return manufacturer;
		}


		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}


		public String getQty() {
			return qty;
		}


		public void setQty(String qty) {
			this.qty = qty;
		}


		public String getMerchType() {
			return merchType;
		}


		public void setMerchType(String merchType) {
			this.merchType = merchType;
		}


		public String getProdCode() {
			return prodCode;
		}


		public void setProdCode(String prodCode) {
			this.prodCode = prodCode;
		}


		public String getItemtype() {
			return itemtype;
		}


		public void setItemtype(String itemtype) {
			this.itemtype = itemtype;
		}


		public String getCost() {
			return cost;
		}


		public void setCost(String cost) {
			this.cost = cost;
		}


		public String getCreatedBy() {
			return createdBy;
		}


		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}


		public Timestamp getCreationDate() {
			return creationDate;
		}


		public void setCreationDate(Timestamp creationDate) {
			this.creationDate = creationDate;
		}


		public String getLastUpdateBy() {
			return lastUpdateBy;
		}


		public void setLastUpdateBy(String lastUpdateBy) {
			this.lastUpdateBy = lastUpdateBy;
		}


		public Timestamp getLastUpdateDate() {
			return lastUpdateDate;
		}


		public void setLastUpdateDate(Timestamp lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}


		public static RowMapper getRowMapper(){
             return new RowMapper() {
                 public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                     return new getCompItems(
                         rs.getBigDecimal("SET_ITEM_ID"),
                         rs.getString("BOM_INSTRUCTIONS"),
                         rs.getString("COMPONENT_ITEM"),
                         rs.getString("COMPONENT_DESCRIPTION"),
                         rs.getString("MANUFACTURER"),
                         rs.getString("QTY"),
                         rs.getString("MERCH_TYPE"),
                         rs.getString("PROD_CODE"),
                         rs.getString("ITEM_TYPE"),
                         rs.getString("COST"),
                         rs.getString("CREATED_BY"),
                         rs.getTimestamp("CREATION_DATE"),
                         rs.getString("LAST_UPDATE_BY"),
                         rs.getTimestamp("LAST_UPDATE_DATE")
                     );
                 }
             };
         }

     }

    
    
   public static Object[] usaCompensationList(){
        clearException();
        System.out.println("in usaCompensationList ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(USA_COMPENSATION_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }    
   
   public static Object[] usaCompensation2List(){
        clearException();
        System.out.println("in usaCompensation2List ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(USA_COMPENSATION2_LIST);
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                        ,statement.getObject(2)
                        ,statement.getObject(3)};
                } else {
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
                System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
            }
        } catch (Exception ex) {
            saveException(ex);
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
        return null;
   }

   public Object[] getProjItemAttrValues(BigDecimal projectId,BigDecimal lineId,String userId){
       clearException();
       System.out.println("in geProjItemAttrValues ");
               
       CallableStatement statement = null;
       Connection connection = null;
/*       List<String> attrValues=new ArrayList<String>();
       Object[] itemMainAttrValues=null;
*/       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_PROJ_ITEM_ATTR_VALUES);
               if (statement != null) {
            	   statement.setBigDecimal(1, projectId);
            	   statement.setBigDecimal(2, lineId);
            	   statement.setString(3, userId);
                   statement.registerOutParameter(4, OracleTypes.CURSOR);
                   statement.registerOutParameter(5, OracleTypes.VARCHAR);
                   statement.registerOutParameter(6, OracleTypes.VARCHAR);
                   statement.execute();
                   System.out.println("in geProjItemAttrValues after");
                   return new Object[]{cursorToList((ResultSet)statement.getObject(4),ItemInfoRec.getRowMapper())
                           ,statement.getObject(5)
                           ,statement.getObject(6)};                   
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }
   
   public Object[] getProjItemAdditionalAttrValues(BigDecimal projectId,BigDecimal lineId){
       clearException();
       System.out.println("in getProjItemAdditionalAttrValues ");
               
       CallableStatement statement = null;
       Connection connection = null;
/*       List<String> attrValues=new ArrayList<String>();
       Object[] itemMainAttrValues=null;
*/       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_PROJ_ITEM_ADDITIONAL_ATTR_VALUES);
               if (statement != null) {
            	   statement.setBigDecimal(1, projectId);
            	   statement.setBigDecimal(2, lineId);
                   statement.registerOutParameter(3, OracleTypes.CURSOR);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   statement.registerOutParameter(5, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(3),ItemInfoRec.getRowMapper())
                           ,statement.getObject(4)
                           ,statement.getObject(5)};                   
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }
   
   public Object[] geItemMainAttrValues(String temaplateName){
       clearException();
       System.out.println("in geItemMainAttributesValue ");
               
       CallableStatement statement = null;
       Connection connection = null;
       List<String> attrValues=new ArrayList<String>();
       Object[] itemMainAttrValues=null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_ITEM_MAIN_ATTR_VALUES);
               if (statement != null) {
            	   statement.setString(1, temaplateName);
                   statement.registerOutParameter(2, OracleTypes.CURSOR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   statement.execute();
                   ResultSet rs=(ResultSet) statement.getObject(2);
                   while(rs.next())
                   {          
                	  // String str1=rs.getString(1);
                	   String str2=rs.getString(2);
                	  // attrValues.add(str1);
                	   attrValues.add(str2);
                	   //System.out.println("Attribute names::"+str2);
                   }  
                   itemMainAttrValues=new Object[]{attrValues,statement.getString(3),statement.getString(4)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return itemMainAttrValues;
  }
   
   public static Object[] masterProjectList(){
       clearException();
       System.out.println("in masterProjectList ");
               
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(MASTER_PROJECT_LIST);
               if (statement != null) {
                   statement.registerOutParameter(1, OracleTypes.CURSOR);
                   statement.registerOutParameter(2, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                       ,statement.getObject(2)
                       ,statement.getObject(3)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }

   public static Object[] projectTypeList(){
       clearException();
       System.out.println("in projectTypeList ");
               
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(PROJECT_TYPE_LIST);
               if (statement != null) {
                   statement.registerOutParameter(1, OracleTypes.CURSOR);
                   statement.registerOutParameter(2, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(1),stringRowMapper())
                       ,statement.getObject(2)
                       ,statement.getObject(3)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }

   public static Object[] savesearchcriteria(BigDecimal projectNo, String projectName,String projcat,
			String projDesc, String masterProj,String requester,
			String status, String dateFrom,
			String dateTo, String user, String savedname){
		clearException();
		System.out.println("in savesearchcriteria savedname" + savedname);
		CallableStatement statement = null;
		Connection connection = null;
		try {
		connection = TransactionScope.getConnection();
		if (connection != null) {
		
		//System.out.println("in getTemplateAttrAssign 1");
		
		statement = (CallableStatement) connection.prepareCall(SAVE_SEARCH_CRITERIA);
		if (statement != null) {
		//System.out.println("in getTemplateAttrAssign 2");
		statement.setObject(1, projectNo, OracleTypes.NUMBER );
		statement.setObject(2, projectName, OracleTypes.VARCHAR);
		statement.setObject(3, projcat, OracleTypes.VARCHAR);
		statement.setObject(4, projDesc, OracleTypes.VARCHAR);
		statement.setObject(5, masterProj, OracleTypes.VARCHAR);
		statement.setObject(6, requester, OracleTypes.VARCHAR);
		statement.setObject(7, status, OracleTypes.VARCHAR);
		statement.setObject(8, dateFrom, OracleTypes.VARCHAR);
		statement.setObject(9, dateTo, OracleTypes.VARCHAR);
		statement.setObject(10, user, OracleTypes.VARCHAR);
		statement.setObject(11, savedname, OracleTypes.VARCHAR);
		statement.registerOutParameter(12, OracleTypes.VARCHAR);
		statement.registerOutParameter(13, OracleTypes.VARCHAR);
		statement.execute();
		System.out.println("in savesearchcriteria after execution");
		return new Object[]{statement.getObject(12),statement.getObject(13)	};
		
		} else {
		System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
		}
		} else {
		System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
		}
		} catch (Exception ex) {
		saveException(ex);
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
		return null;
		}
   

   public Object[] getsavedsearchvalues(String Name,String user){
       clearException();
       System.out.println("in getsavedsearchvalues ");
               
       CallableStatement statement = null;
       Connection connection = null;
       List<String> attrValues=new ArrayList<String>();
       Object[] savedSearchSelectedValues=null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_SAVEDSEARCH_VALUES);
               if (statement != null) {
            	    statement.setString(1, Name);
            	    statement.setString(2, user);
                   statement.registerOutParameter(3, OracleTypes.CURSOR);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   statement.registerOutParameter(5, OracleTypes.VARCHAR);
                   statement.execute();
                   ResultSet rs=(ResultSet) statement.getObject(3);
                  while(rs.next())
                   {          
                	  String str1=rs.getString(1);
                	  //String str2=rs.getString(2);
                	   attrValues.add(str1);
                	  //attrValues.add(str2);
                	  //System.out.println("Attribute names::"+str1);
                   }  
                   savedSearchSelectedValues=new Object[]{attrValues,
														statement.getString(4),statement.getString(5)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return savedSearchSelectedValues;
  }

   
   public Object[] getCUSAItem(String temaplateName,BigDecimal proj_no,String item_no,String user){
       clearException();
       System.out.println("in getCUSAItem ");
               
       CallableStatement statement = null;
       Connection connection = null;
       List<String> attrValues=new ArrayList<String>();
       List<String> bomItems=new ArrayList<String>();
       Object[] itemMainAttrValues=null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_CUSA_ITEMS);
               if (statement != null) {

            	   statement.setObject(1, temaplateName, OracleTypes.VARCHAR );
            	   statement.setObject(2, proj_no, OracleTypes.NUMBER );
           	       statement.setObject(3, item_no, OracleTypes.VARCHAR );

                   statement.registerOutParameter(4, OracleTypes.CURSOR);
                   statement.registerOutParameter(5, OracleTypes.CURSOR);
                   statement.registerOutParameter(6, OracleTypes.VARCHAR);
                   statement.registerOutParameter(7, OracleTypes.VARCHAR);
                   statement.execute();
                   ResultSet rs=(ResultSet) statement.getObject(4);
                   while(rs.next())
                   {          
                	  // String str1=rs.getString(1);
                	   String str2=rs.getString(2);
                	  // attrValues.add(str1);
                	   attrValues.add(str2);
                	   //System.out.println("Attribute names::"+str2);
                   }  
                   ResultSet rs1=(ResultSet) statement.getObject(5);
                   while(rs1.next())
                   {          
                	   String str1=rs1.getString(1);
                	   bomItems.add(str1);
                	   //System.out.println("Attribute names::"+str2);
                   }  

                   itemMainAttrValues=new Object[]{attrValues,bomItems,statement.getString(6),statement.getString(7)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return itemMainAttrValues;
  }
   
   /*
   public Object[] getCUSAItem(BigDecimal proj_no,String item_no,String user){
       clearException();
       System.out.println("in getCUSAItem ");
               
       CallableStatement statement = null;
       Connection connection = null;
       List<String> attrValues=new ArrayList<String>();
       Object[] savedSearchSelectedValues=null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_CUSA_ITEMS);
               if (statement != null) {
            	    //statement.setString(1, Name);
            	   statement.setObject(1, proj_no, OracleTypes.VARCHAR );
            	    statement.setObject(2, proj_no, OracleTypes.NUMBER );
            	    statement.setObject(3, item_no, OracleTypes.VARCHAR );
            	    //statement.setString(2, user);
            	    statement.registerOutParameter(4, OracleTypes.CURSOR);
            	    statement.registerOutParameter(5, OracleTypes.VARCHAR);
            	    statement.registerOutParameter(6, OracleTypes.VARCHAR);
            	    statement.execute();

                    savedSearchSelectedValues= new Object[]{ cursorToList((ResultSet)statement.getObject(3),ItemInfoRec.getRowMapper())
								                           ,statement.getObject(4)
								                           ,statement.getObject(5)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return savedSearchSelectedValues;
  }  */
   
   public static Object[] getsavedSearchList(String user){
       clearException();
       System.out.println("in getsavedSearchList ");
               
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_SAVEDSEARCH_LIST);
               if (statement != null) {
            	   statement.setString(1, user);
                   statement.registerOutParameter(2, OracleTypes.CURSOR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(2),stringRowMapper())
                       ,statement.getObject(3)
                       ,statement.getObject(4)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }

   public static Object[] getComponentList(String component){
       clearException();
       System.out.println("in S21ItemsList ");
               
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_COMPONENT_LIST);
               if (statement != null) {
            	   statement.setString(1, component);
                   statement.registerOutParameter(2, OracleTypes.CURSOR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(2),stringRowMapper())
                       ,statement.getObject(3)
                       ,statement.getObject(4)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return null;
  }
   
   public Object[] getComponentDetails(String componentName){
       clearException();
       System.out.println("in geItemMainAttributesValue ");
               
       CallableStatement statement = null;
       Connection connection = null;
       List<String> attrValues=new ArrayList<String>();
       Object[] itemMainAttrValues=null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_COMPONENT_DETAILS);
               if (statement != null) {
            	   statement.setString(1, componentName);
                   statement.registerOutParameter(2, OracleTypes.CURSOR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   statement.execute();
                   ResultSet rs=(ResultSet) statement.getObject(2);
                   while(rs.next())
                   {          
                	  // String str1=rs.getString(1);
                	   String str2=rs.getString(2);
                	  // attrValues.add(str1);
                	   attrValues.add(str2);
                	   //System.out.println("Attribute names::"+str2);
                   }  
                   itemMainAttrValues=new Object[]{attrValues,statement.getString(3),statement.getString(4)};
               } else {
                   System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
               }
           } else {
               System.err.println("DBStatus.UNABLE_TO_GET_CONNECTION");
           }
       } catch (Exception ex) {
           saveException(ex);
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
       return itemMainAttrValues;
  }
   
    public static void main(String args[]){
//        Object[] result=getProjectItems(new BigDecimal(1040));
//        System.out.println(result.length);
//        System.out.println(CanonE590ItemProcessUtil.asList(result));
        //project_number=1203&respId=50347,userId=393182
//        Object[] result=checkModifyProject(new BigDecimal(1203),new BigDecimal(50347),new BigDecimal(393182));
        Object[] result=checkStatusMonitor(new BigDecimal(1039));
        System.out.println(CanonE008ItemProcessUtil.first(result));
    }
}

 
