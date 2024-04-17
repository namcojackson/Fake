package oracle.apps.e008.item.process;

import java.sql.Connection;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;

import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;
import oracle.apps.jtf.aom.transaction.TransactionScope;

public class CanonE008TemplateDAO {
   
   public static final String TEMPLATE_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.TEMPLATE_NAME_LIST(?,?,?)}";	
   public static final String TEMPLATE_ATTRIBUTE_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.TEMPLATE_ATTRIBUTE_LIST(?,?,?)}";
   public static final String TEMPLATE_STATUS_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.TEMPLATE_STATUS_LIST(?,?,?)}";
   public static final String TEMPLATE_TYPE_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.TEMPLATE_TYPE_LIST(?,?,?)}";
   public static final String WB_DISPLAY_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.WB_DISPLAY_LIST(?,?,?)}";
   public static final String TEMPLATE_CATEGORY_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.TEMPLATE_CATEGORY_LIST(?,?,?)}";
   public static final String TEMPLATE_OWNER_GROUP_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.APPROVAL_GROUP_LIST(?,?,?)}";

   public static final String SAVE_TEMPLATE_ATTRIBUTE= "{call CANON_E008_ITEM_TEMPLATE_PKG.SAVE_TEMPLATE_ATTRIBUTES(?,?,?)}";
   public static final String SAVE_TEMPLATE_ATTRIBUTE_ASSIGN= "{call CANON_E008_ITEM_TEMPLATE_PKG.SAVE_TEMPL_ATTR_ASSGMT(?,?,?)}";
   public static final String UPDATE_TEMPLATE_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.SAVE_TEMPLATE_HEADER(?,?,?)}";
   public static final String CREATE_TEMPLATE= "{call CANON_E008_ITEM_TEMPLATE_PKG.CREATE_TEMPLATE_HEADER(?,?,?,?)}";
    
   public static final String GET_TEMPLATE_ATTRIBUTES= "{call CANON_E008_ITEM_TEMPLATE_PKG.GET_TEMPLATE_ATTRIBUTES(?,?,?,?)}";
   public static final String GET_TEMPLATE_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.GET_TEMPLATE_LIST(?,?,?)}";
   public static final String GET_TEMPLATE_ATTRIBUTE_ASSIGN_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.GET_TEMPL_ATTR_ASSIGN(?,?,?,?,?)}";
   
   public static final String GET_SEARCH_TEMPLATE_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.TEMPLATE_SEARCH(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
   
   public static final String SAVE_SEARCH_CRITERIA= "{call CANON_E008_ITEM_TEMPLATE_PKG.SAVE_TEMPLATE_SEARCH(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
   public static final String GET_SAVEDSEARCH_LIST= "{call CANON_E008_ITEM_TEMPLATE_PKG.GET_SAVEDSEARCH_LIST(?,?,?)}";
   
   public static final String GET_SAVEDSEARCH_VALUES= "{call CANON_E008_ITEM_TEMPLATE_PKG.GET_SAVEDSEARCH_VALUES(?,?,?,?,?)}";
   
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
    
   public CanonE008TemplateDAO(){ 
   }

  
   public static Object[] getTemplateAttr(String catName){
        clearException();
        System.out.println("in getTemplateAttr ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_TEMPLATE_ATTRIBUTES);
                if (statement != null) {
                	statement.setObject(1, catName, OracleTypes.VARCHAR);
                	statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),  CanonE008TemplateAttributeRec.getRowMapper())
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

   public static Object[] getTemplateAttrAssign(BigDecimal template_id, BigDecimal attribute_id){
       clearException();
       //System.out.println("in getTemplateAttrAssign template_id" + template_id);
       //System.out.println("in getTemplateAttrAssign attribute_id" + attribute_id);
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
        	   
        	   //System.out.println("in getTemplateAttrAssign 1");
        	   
               statement = (CallableStatement) connection.prepareCall(GET_TEMPLATE_ATTRIBUTE_ASSIGN_LIST);
               if (statement != null) {
            	   //System.out.println("in getTemplateAttrAssign 2");
            	   statement.setObject(1, template_id, OracleTypes.NUMBER);
            	   statement.setObject(2, attribute_id, OracleTypes.NUMBER);
            	   //System.out.println("in getTemplateAttrAssign 3");
                   statement.registerOutParameter(3, OracleTypes.CURSOR);
                   //System.out.println("in getTemplateAttrAssign 4");
                   statement.registerOutParameter(4, OracleTypes.VARCHAR);
                   //System.out.println("in getTemplateAttrAssign 5");
                   statement.registerOutParameter(5, OracleTypes.VARCHAR);
                   //System.out.println("in getTemplateAttrAssign 6");
                   statement.execute();
                   //System.out.println("in getTemplateAttrAssign 7");
                   return new Object[]{cursorToList((ResultSet)statement.getObject(3),  CanonE008TemplateAttrAssignListRec.getRowMapper())
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
   
   
   public static Object[] getsearchTemplateList(BigDecimal template_id,String template_name,String htemplateType,String templateStatus,
		   										String htemplatedesc,String hitemassigned, String hattribute1, String hattributeVal1,
		   										String hattribute2, String hattributeVal2,String hattribute3, String hattributeVal3,
		   										String hattribute4, String hattributeVal4,String hattribute5, String hattributeVal5
		   										){
       clearException();
       System.out.println("in getsearchTemplateList ");
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_SEARCH_TEMPLATE_LIST);
               if (statement != null) {
            	   System.out.println("in getsearchTemplateList 1");
                   
             	 
                   statement.registerOutParameter(1, OracleTypes.CURSOR);
                   statement.registerOutParameter(2, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.setObject(4, template_name, OracleTypes.VARCHAR);
                   statement.setObject(5, htemplateType, OracleTypes.VARCHAR);
                   statement.setObject(6, templateStatus, OracleTypes.VARCHAR);
                   statement.setObject(7, htemplatedesc, OracleTypes.VARCHAR);
                   statement.setObject(8, hitemassigned, OracleTypes.VARCHAR);
                   statement.setObject(9, hattribute1, OracleTypes.VARCHAR);
                   statement.setObject(10, hattributeVal1, OracleTypes.VARCHAR);
                   statement.setObject(11, hattribute2, OracleTypes.VARCHAR);
                   statement.setObject(12, hattributeVal2, OracleTypes.VARCHAR);
                   statement.setObject(13, hattribute3, OracleTypes.VARCHAR);
                   statement.setObject(14, hattributeVal3, OracleTypes.VARCHAR);
                   statement.setObject(15, hattribute4, OracleTypes.VARCHAR);
                   statement.setObject(16, hattributeVal4, OracleTypes.VARCHAR);
                   statement.setObject(17, hattribute5, OracleTypes.VARCHAR);
                   statement.setObject(18, hattributeVal5, OracleTypes.VARCHAR);  
                   statement.setObject(19, template_id, OracleTypes.NUMBER);
    
                   System.out.println("in getsearchTemplateList 2");
                   statement.execute();
                   System.out.println("in getsearchTemplateList 3");
                   return new Object[]{cursorToList((ResultSet)statement.getObject(1),  CanonE008TemplateListRec.getRowMapper())
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
  
   public static Object[] getTemplateList(){
       clearException();
       System.out.println("in getTemplateList ");
       CallableStatement statement = null;
       Connection connection = null;
       try {
           connection = TransactionScope.getConnection();
           if (connection != null) {
               statement = (CallableStatement) connection.prepareCall(GET_TEMPLATE_LIST);
               if (statement != null) {
                   statement.registerOutParameter(1, OracleTypes.CURSOR);
                   statement.registerOutParameter(2, OracleTypes.VARCHAR);
                   statement.registerOutParameter(3, OracleTypes.VARCHAR);
                   statement.execute();
                   return new Object[]{cursorToList((ResultSet)statement.getObject(1),  CanonE008TemplateListRec.getRowMapper())
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


   public static Object[] TemplateAttributeList(){
      clearException();
      System.out.println("in templateList ");
              
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
      System.out.println("in templateList ");
              
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

  public static Object[] TemplateTypeList(){
      clearException();
      System.out.println("in templateList ");
              
      CallableStatement statement = null;
      Connection connection = null;
      try {
          connection = TransactionScope.getConnection();
          if (connection != null) {
              statement = (CallableStatement) connection.prepareCall(TEMPLATE_TYPE_LIST);
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

  public static Object[] WBDisplayList(){
      clearException();
      System.out.println("in templateList ");
              
      CallableStatement statement = null;
      Connection connection = null;
      try {
          connection = TransactionScope.getConnection();
          if (connection != null) {
              statement = (CallableStatement) connection.prepareCall(WB_DISPLAY_LIST);
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

  public static Object[] templateCategoryList(){
      clearException();
      System.out.println("in templateList ");
              
      CallableStatement statement = null;
      Connection connection = null;
      try {
          connection = TransactionScope.getConnection();
          if (connection != null) {
              statement = (CallableStatement) connection.prepareCall(TEMPLATE_CATEGORY_LIST);
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
  
  public static Object[] ownerGroupList(){
      clearException();
      System.out.println("in templateList ");
              
      CallableStatement statement = null;
      Connection connection = null;
      try {
          connection = TransactionScope.getConnection();
          if (connection != null) {
              statement = (CallableStatement) connection.prepareCall(TEMPLATE_OWNER_GROUP_LIST);
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

    public static Object[] saveAttributeList( List p_attribute_tbl){
    	        clearException();
    	        System.out.println("in saveAttributeList ");
    	        CallableStatement statement = null;
    	        Connection connection = null;
    	        try {
    	            connection = TransactionScope.getConnection();
    	            System.out.println("in saveAttributeList 1 ");
    	            if (connection != null) {
    	                statement = (CallableStatement) connection.prepareCall(SAVE_TEMPLATE_ATTRIBUTE);
    	                System.out.println("in saveAttributeList 2 ");
    	                if (statement != null) {
    	                    System.out.println("in saveAttributeList 5 ");
    	                    statement.setObject(1, canonE008attributeTypeListToArray(p_attribute_tbl, connection), OracleTypes.ARRAY);
    	                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
    	                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
    	                    statement.execute();
    	                    System.out.println("in saveAttributeList 6 ");
    	                    return new Object[]{
    	                         statement.getObject(2)
    	                        ,statement.getObject(3)}; 
    	                    
    	                } else {
    	                	System.out.println("in saveAttributeList 7 ");
    	                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
    	                }
    	            } else {
    	            	System.out.println("in saveAttributeList 8 ");
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

    public static Object[] saveAttributeAssignList( List p_attribute_Assign__tbl){
        clearException();
        System.out.println("in saveAttributeAssignList ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("in saveAttributeAssignList 1 ");
            if (connection != null) {
            	statement = (CallableStatement) connection.prepareCall(SAVE_TEMPLATE_ATTRIBUTE_ASSIGN);
                System.out.println("in saveAttributeAssignList 2 ");
                if (statement != null) {
                    System.out.println("in saveAttributeAssignList 5 ");
                    statement.setObject(1, canonE008attributeAssignTypeListToArray(p_attribute_Assign__tbl, connection), OracleTypes.ARRAY);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("in saveAttributeAssignList 6 ");
                    return new Object[]{
                         statement.getObject(2)
                        ,statement.getObject(3)}; 
                    
                } else {
                	System.out.println("in saveAttributeAssignList 7 ");
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
    
    public static Object[] updateTemplateList( List p_templates__tbl){
        clearException();
        System.out.println("in updateTemplateList ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("in updateTemplateList 1 ");
            if (connection != null) {
            	statement = (CallableStatement) connection.prepareCall(UPDATE_TEMPLATE_LIST);
                System.out.println("in updateTemplateList 2 ");
                if (statement != null) {
                    System.out.println("in updateTemplateList 5 ");
                    statement.setObject(1, canonE008TemplateListToArray(p_templates__tbl, connection), OracleTypes.ARRAY);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("in updateTemplateList 6 ");
                    return new Object[]{
                         statement.getObject(2)
                        ,statement.getObject(3)}; 
                    
                } else {
                	System.out.println("in updateTemplateList 7 ");
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
            	System.out.println("in updateTemplateList 8 ");
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

    public static Object[] createTemplate( CanonE008TemplateListRec p_templates_rec){
        clearException();
        System.out.println("in createTemplate ");
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            System.out.println("in createTemplate 1 ");
            if (connection != null) {
            	statement = (CallableStatement) connection.prepareCall(CREATE_TEMPLATE);
                System.out.println("in createTemplate 2 ");
                if (statement != null) {
                    System.out.println("in createTemplate 5 ");
                    connection.getTypeMap().put("CANON_E008_TEMPL_REC", CanonE008TemplateListRec.class);
                    System.out.println("in createTemplate 4 ");
                    statement.setObject(1, p_templates_rec);                    
                    //statement.setObject(1, canonE008TemplateListToArray(p_templates__tbl, connection), OracleTypes.ARRAY);
                    statement.registerOutParameter(2, OracleTypes.NUMBER);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.execute();
                    System.out.println("in createTemplate 6 ");
                    return new Object[]{
                    	statement.getObject(2)	
                        ,statement.getObject(3)
                        ,statement.getObject(4)}; 
                    
                } else {
                	System.out.println("in createTemplate 7 ");
                    System.err.println("DBStatus.UNABLE_TO_CREATE_STATEMENT ");
                }
            } else {
            	System.out.println("in createTemplate 8 ");
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
    
    static oracle.sql.ARRAY canonE008TemplateListToArray(List list, Connection connection) throws SQLException {
    	Connection nativeConn=null;    	
    	nativeConn=TransactionScope.nativeConnection(connection);
    	System.out.println("in canonE008attributeTypeListToArray 1 ");
    	ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E008_TEMPL_TBL_TYPE", nativeConn);
    	System.out.println("in canonE008attributeTypeListToArray 2 ");
    	CanonE008TemplateListRec[] elements = (CanonE008TemplateListRec[]) list.toArray(new CanonE008TemplateListRec[0]);
    	System.out.println("in canonE008attributeTypeListToArray 3 ");
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, nativeConn, elements);
        System.out.println("in canonE008attributeTypeListToArray 4 ");
        return array;    	    
    }

    
    static oracle.sql.ARRAY canonE008attributeTypeListToArray(List list, Connection connection) throws SQLException {
    	Connection nativeConn=null;    	
    	nativeConn=TransactionScope.nativeConnection(connection);
    	System.out.println("in canonE008attributeTypeListToArray 1 ");
    	ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E008_TEMPL_ATTR_TBL_TYPE", nativeConn);
    	System.out.println("in canonE008attributeTypeListToArray 2 ");
    	CanonE008TemplateAttributeRec[] elements = (CanonE008TemplateAttributeRec[]) list.toArray(new CanonE008TemplateAttributeRec[0]);
    	System.out.println("in canonE008attributeTypeListToArray 3 ");
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, nativeConn, elements);
        System.out.println("in canonE008attributeTypeListToArray 4 ");
        return array;    	    
    }
     
    static oracle.sql.ARRAY canonE008attributeAssignTypeListToArray(List list, Connection connection) throws SQLException {
    	Connection nativeConn=null;    	
    	nativeConn=TransactionScope.nativeConnection(connection);
    	System.out.println("in canonE008attributeTypeListToArray 1 ");
    	ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E008_TEMPL_AA_TBL_TYPE", nativeConn);
    	System.out.println("in canonE008attributeTypeListToArray 2 ");
    	CanonE008TemplateAttrAssignListRec[] elements = (CanonE008TemplateAttrAssignListRec[]) list.toArray(new CanonE008TemplateAttrAssignListRec[0]);
    	System.out.println("in canonE008attributeTypeListToArray 3 ");
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, nativeConn, elements);
        System.out.println("in canonE008attributeTypeListToArray 4 ");
        return array;    	    
    }
    
    public static class CanonE008lovRec {
        private String listcode;
        private String listname;
        
        public CanonE008lovRec(){
        }
        
		public CanonE008lovRec(String listcode, String listname) {
			super();
			this.listcode = listcode;
			this.listname = listname;
		}
		public String getListcode() {
			return listcode;
		}
		public void setListcode(String listcode) {
			this.listcode = listcode;
		}
		public String getListname() {
			return listname;
		}
		public void setListname(String listname) {
			this.listname = listname;
		}
     
		public static RowMapper getRowMapper(){
            return new RowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
           			return new CanonE008lovRec(
                    		rs.getString("LISTCODE"),
                    		rs.getString("LISTNAME") );
                }
           };
	    }

		

        public void readSQL(SQLInput stream, String typeName) throws SQLException {
        	listcode = stream.readString();
        	listname = stream.readString();
        }

        public void writeSQL(SQLOutput stream) throws SQLException {
               stream.writeString(listcode);
               stream.writeString(listname);
        }
        
        
        
        
    } 
    public static class CanonE008TemplateAttributeRec implements java.sql.SQLData, java.io.Serializable  {
           private String eztableid;
           private String companycode;
           private BigDecimal attributeid;
           private String workbenchdisplay;
           private BigDecimal displaysort;
           private String categoryname;
           private String approvalgroupowner;
           private String attributename;
           private BigDecimal templateattrversion;
           private String createdBy;
           private String createdByName;
           private Timestamp creationDate;
           private String lastUpdateBy;
           private String lastUpdateName;
           private Timestamp lastUpdateDate;
           
           
            public CanonE008TemplateAttributeRec(){
            }
            public CanonE008TemplateAttributeRec(String eztableid,
					String companycode, BigDecimal attributeid,
					String workbenchdisplay, BigDecimal displaysort,
					String categoryname, String approvalgroupowner,
					String attributename, BigDecimal templateattrversion, String createdBy,
					String createdByName, Timestamp creationDate,
					String lastUpdateBy, String lastUpdateName,
					Timestamp lastUpdateDate) {
				super();
				this.eztableid = eztableid;
				this.companycode = companycode;
				this.attributeid = attributeid;
				this.workbenchdisplay = workbenchdisplay;
				this.displaysort = displaysort;
				this.categoryname = categoryname;
				this.approvalgroupowner = approvalgroupowner;
				this.attributename = attributename;
				this.templateattrversion = templateattrversion;
				this.createdBy = createdBy;
				this.createdByName = createdByName;
				this.creationDate = creationDate;
				this.lastUpdateBy = lastUpdateBy;
				this.lastUpdateName = lastUpdateName;
				this.lastUpdateDate = lastUpdateDate;
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
			public BigDecimal getAttributeid() {
				return attributeid;
			}
			public void setAttributeid(BigDecimal attributeid) {
				this.attributeid = attributeid;
			}
			public String getWorkbenchdisplay() {
				return workbenchdisplay;
			}
			public void setWorkbenchdisplay(String workbenchdisplay) {
				this.workbenchdisplay = workbenchdisplay;
			}
			public BigDecimal getDisplaysort() {
				return displaysort;
			}
			public void setDisplaysort(BigDecimal displaysort) {
				this.displaysort = displaysort;
			}
			public String getCategoryname() {
				return categoryname;
			}
			public void setCategoryname(String categoryname) {
				this.categoryname = categoryname;
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

			public BigDecimal getTemplateattrversion() {
				return templateattrversion;
			}
			public void setTemplateattrversion(BigDecimal templateattrversion) {
				this.templateattrversion = templateattrversion;
			}
		
			public String getCreatedBy() {
				return createdBy;
			}
			public void setCreatedBy(String createdBy) {
				this.createdBy = createdBy;
			}
			public String getCreatedByName() {
				return createdByName;
			}
			public void setCreatedByName(String createdByName) {
				this.createdByName = createdByName;
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
			public String getLastUpdateName() {
				return lastUpdateName;
			}
			public void setLastUpdateName(String lastUpdateName) {
				this.lastUpdateName = lastUpdateName;
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
	           			return new CanonE008TemplateAttributeRec(
	                    		rs.getString("EZTABLEID"),
	                    		rs.getString("EZINCOMPANYCODE"),
	            				rs.getBigDecimal("ATTRIBUTE_ID"),
	            				rs.getString("WORKBENCH_DISPLAY"),
	            				rs.getBigDecimal("DISPLAY_SORT"),
	            				rs.getString("CATEGORY_NAME"),
	            				rs.getString("APPROVAL_GROUP_OWNER"),
	            				rs.getString("ATTRIBUTE_NAME"),
	            				rs.getBigDecimal("TEMPLATE_ATTR_VERSION"),
	            				rs.getString("CREATED_BY"),
	            				rs.getString("CREATED_BY_NAME"),
	            				rs.getTimestamp("CREATION_DATE"),
	            				rs.getString("LAST_UPDATE_BY"),
	            				rs.getString("LAST_UPDATE_BY_NAME"),
	            				rs.getTimestamp("LAST_UPDATE_DATE")
	                    );
	                }
	           };
		    }

			
			public String getSQLTypeName() throws SQLException {
                return "CANON_E008_TEMPL_ATTR_REC";
            }

            public void readSQL(SQLInput stream, String typeName) throws SQLException {
            	   eztableid = stream.readString();
            	   companycode = stream.readString();
            	   attributeid = stream.readBigDecimal();
            	   workbenchdisplay = stream.readString();
            	   displaysort = stream.readBigDecimal();
            	   categoryname = stream.readString();
            	   approvalgroupowner = stream.readString();
            	   attributename = stream.readString();
            	   templateattrversion = stream.readBigDecimal();
                   createdBy = stream.readString();
                   createdByName = stream.readString();
                   creationDate = stream.readTimestamp();
                   lastUpdateBy = stream.readString();
                   lastUpdateName = stream.readString();
                   lastUpdateDate = stream.readTimestamp();
            }

            public void writeSQL(SQLOutput stream) throws SQLException {
                   stream.writeString(eztableid);
                   stream.writeString(companycode);
                   stream.writeBigDecimal(attributeid);
                   stream.writeString(workbenchdisplay);
                   stream.writeBigDecimal(displaysort);
                   stream.writeString(categoryname);
                   stream.writeString(approvalgroupowner);
                   stream.writeString(attributename);
                   stream.writeBigDecimal(templateattrversion);
                   stream.writeString(createdBy);
                   stream.writeString(createdByName);
                   stream.writeTimestamp(creationDate);
                   stream.writeString(lastUpdateBy);
                   stream.writeString(lastUpdateName);
                   stream.writeTimestamp(lastUpdateDate);
            }

        }

  public static class CanonE008TemplateListRec implements java.sql.SQLData, java.io.Serializable  {
	  
	  private String	  eztableid        	;
	  private String	  companycode      	;
	  private BigDecimal  templateid        ;
	  private String      templatename      ;
	  private String	  templatetype    	;
	  private String	  templatestatus 	;
	  private String	  templatedescription   	;
	  //private String	  sourcetemplatename      	;
	  private String	  itemassigned   	;
	  private String      templateheaderversion      	;
      private String 	  createdBy;
      private Timestamp   creationDate;
      private String 	  lastUpdateBy;
      private Timestamp   lastUpdateDate;
      
      public CanonE008TemplateListRec(){
      }
      
     public CanonE008TemplateListRec(String eztableid, String companycode,
			BigDecimal templateid, String templatename, String templatetype,
			String templatestatus, String templatedescription,/* String sourcetemplatename,*/
			String itemassigned, String templateheaderversion,
			String createdBy, Timestamp creationDate, String lastUpdateBy,
			Timestamp lastUpdateDate) {
		super();
		this.eztableid = eztableid;
		this.companycode = companycode;
		this.templateid = templateid;
		this.templatename = templatename;
		this.templatetype = templatetype;
		this.templatestatus = templatestatus;
		this.templatedescription = templatedescription;
		//this.sourcetemplatename = sourcetemplatename;
		this.itemassigned = itemassigned;
		this.templateheaderversion = templateheaderversion;
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

			public String getCompanycode() {
				return companycode;
			}

			public void setCompanycode(String companycode) {
				this.companycode = companycode;
			}

			public BigDecimal getTemplateid() {
				return templateid;
			}

			public void setTemplateid(BigDecimal templateid) {
				this.templateid = templateid;
			}

			public String getTemplatename() {
				return templatename;
			}

			public void setTemplatename(String templatename) {
				this.templatename = templatename;
			}

			public String getTemplatetype() {
				return templatetype;
			}

			public void setTemplatetype(String templatetype) {
				this.templatetype = templatetype;
			}

			public String getTemplatestatus() {
				return templatestatus;
			}

			public void setTemplatestatus(String templatestatus) {
				this.templatestatus = templatestatus;
			}

			public String getTemplatedescription() {
				return templatedescription;
			}

			public void setTemplatedescription(String templatedescription) {
				this.templatedescription = templatedescription;
			}

			public String getItemassigned() {
				return itemassigned;
			}

			public void setItemassigned(String itemassigned) {
				this.itemassigned = itemassigned;
			}

			public String getTemplateheaderversion() {
				return templateheaderversion;
			}

			public void setTemplateheaderversion(String templateheaderversion) {
				this.templateheaderversion = templateheaderversion;
			}

/*			public String getSourcetemplatename() {
				return sourcetemplatename;
			}
		
			public void setSourcetemplatename(String sourcetemplatename) {
				this.sourcetemplatename = sourcetemplatename;
			}
*/			
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
	           			return new CanonE008TemplateListRec(
	                    		rs.getString("EZTABLEID"),
	                    		rs.getString("EZINCOMPANYCODE"),
	            				rs.getBigDecimal("TEMPLATE_ID"),
	            				rs.getString("TEMPLATE_NAME"),
	            				rs.getString("TEMPLATE_TYPE"),
	            				rs.getString("TEMPLATE_STATUS"),
	            				rs.getString("TEMPLATE_DESCRIPTION"),
	            				//rs.getString("SOURCE_TEMPLATE_NAME"),
	            				rs.getString("ITEM_ASSIGNED"),
	            				rs.getString("TEMPLATE_HDR_VERSION"),
	            				rs.getString("CREATED_BY"),
	            				rs.getTimestamp("CREATION_DATE"),
	            				rs.getString("LAST_UPDATE_BY"),
	            				rs.getTimestamp("LAST_UPDATE_DATE")
	                    );
	                }
	           };
		    }

			
		public String getSQLTypeName() throws SQLException {
            return "CANON_E008_TEMPL_REC";
        }

        public void readSQL(SQLInput stream, String typeName) throws SQLException {
        	  eztableid          	= stream.readString();
        	  companycode      		= stream.readString();
        	  templateid           		= stream.readBigDecimal();
        	  templatename        		=stream.readString();
        	  templatetype           	= stream.readString();
        	  templatestatus         		= stream.readString();
        	  templatedescription   	= stream.readString();
        	 // sourcetemplatename  		= stream.readString();
        	  itemassigned  		= stream.readString();
        	  templateheaderversion    = stream.readString();
        	  createdBy         	= stream.readString();
        	  creationDate    		= stream.readTimestamp();
        	  lastUpdateBy     		= stream.readString();
        	  lastUpdateDate  		= stream.readTimestamp();
        }

        public void writeSQL(SQLOutput stream) throws SQLException {
        	stream.writeString(	  eztableid          	);
        	stream.writeString(	  companycode      	);
        	stream.writeBigDecimal(	  templateid           	);
        	stream.writeString(	  templatename        	);
        	stream.writeString(	  templatetype           	);
        	stream.writeString(	  templatestatus         	);
        	stream.writeString(	  templatedescription   	);
        	//stream.writeString(	  sourcetemplatename  	);
        	stream.writeString(	  itemassigned  	);
        	stream.writeString(	  templateheaderversion      	);
        	stream.writeString(	  createdBy         	);
        	stream.writeTimestamp(	  creationDate    	);
        	stream.writeString(	  lastUpdateBy     	);
        	stream.writeTimestamp(	  lastUpdateDate  	);
        }
        /*
        public String toString() {
            return "CanonE008ItemRec{" + "batchName="+batchName+", itemId="+itemId+", projectId="+projectId+", itemCode="+itemCode+", modelNo="+modelNo+", itemDescription="+itemDescription+", canonTemplate="+canonTemplate+", approvalStatus="+approvalStatus+", itemType="+itemType+", template="+template+", merchType="+merchType+", prodCode="+prodCode+", thirdParty="+thirdParty+", supplierName="+supplierName+", supplierSiteName="+supplierSiteName+", supplierItemNo="+supplierItemNo+", serviceItem="+serviceItem+", licenseControl="+licenseControl+", maintenanceTerm="+maintenanceTerm+", cost="+cost+", msrp="+msrp+", counterGroup="+counterGroup+", serviceMode="+serviceMode+", speedSegment="+speedSegment+", supplyCategory="+supplyCategory+", costingEnabled="+costingEnabled+", cogs="+cogs+", revenue="+revenue+", setItem="+setItem+", serialIndicator="+serialIndicator+", lotIndicator="+lotIndicator+", company="+company+", addToBill="+addToBill+", height="+height+", width="+width+", length="+length+", weight="+weight+", sys80Meter="+sys80Meter+", processFlag="+processFlag+", processMessage="+processMessage+", attribute1="+attribute1+", attribute2="+attribute2+", attribute3="+attribute3+", attribute4="+attribute4+", attribute5="+attribute5+", attribute6="+attribute6+", attribute7="+attribute7+", attribute8="+attribute8+", attribute9="+attribute9+", attribute10="+attribute10+", createdBy="+createdBy+", creationDate="+creationDate+", lastUpdateBy="+lastUpdateBy+", lastUpdateDate="+lastUpdateDate+", requestId="+requestId+", usaCompensation="+usaCompensation+", usaCompensation2="+usaCompensation2+'}';
        }
        */
        
    }
  
public static class CanonE008TemplateAttrAssignListRec implements java.sql.SQLData, java.io.Serializable  {
	  private String	  eztableid        	;
	  private String	  companycode      	;
	  private BigDecimal  templateid        ;
	  private BigDecimal  attributeid      ;
	  private String	  required    	;
	  private String	  valid 	;
	  private String	  defaultvalue   	;
      private String 	  createdBy;
      private Timestamp   creationDate;
      private String 	  lastUpdateBy;
      private Timestamp   lastUpdateDate;
      private BigDecimal   templattrassigver;
      
      
      public CanonE008TemplateAttrAssignListRec(){
      }

	public CanonE008TemplateAttrAssignListRec(String eztableid,
			String companycode, BigDecimal templateid, BigDecimal attributeid,
			String required, String valid, String defaultvalue,
			String createdBy, Timestamp creationDate, String lastUpdateBy,
			Timestamp lastUpdateDate,BigDecimal templattrassigver ) {
		super();
		this.eztableid = eztableid;
		this.companycode = companycode;
		this.templateid = templateid;
		this.attributeid = attributeid;
		this.required = required;
		this.valid = valid;
		this.defaultvalue = defaultvalue;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateDate = lastUpdateDate;
		this.templattrassigver = templattrassigver;
		
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

	public BigDecimal getTemplateid() {
		return templateid;
	}

	public void setTemplateid(BigDecimal templateid) {
		this.templateid = templateid;
	}

	public BigDecimal getAttributeid() {
		return attributeid;
	}

	public void setAttributeid(BigDecimal attributeid) {
		this.attributeid = attributeid;
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

	public BigDecimal getTemplattrassigver() {
		return templattrassigver;
	}

	public void setTemplattrassigver(BigDecimal templattrassigver) {
		this.templattrassigver = templattrassigver;
	}
    
	public static RowMapper getRowMapper(){
        return new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
       			return new CanonE008TemplateAttrAssignListRec(
                		rs.getString("EZTABLEID"),
                		rs.getString("EZINCOMPANYCODE"),
        				rs.getBigDecimal("TEMPLATE_ID"),
        				rs.getBigDecimal("ATTRIBUTE_ID"),
        				rs.getString("REQUIRED_VALUE"),
        				rs.getString("VALID"),
        				rs.getString("DEFAULT_VALUE"),
        				rs.getString("CREATED_BY"),
        				rs.getTimestamp("CREATION_DATE"),
        				rs.getString("LAST_UPDATE_BY"),
        				rs.getTimestamp("LAST_UPDATE_DATE"),
        				rs.getBigDecimal("TEMPL_ATTR_ASGN_VER")
        				);
            }
       };
    }

	
	public String getSQLTypeName() throws SQLException {
	    return "CANON_E008_TEMPL_AA_REC";
	}
	
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		  eztableid          	= stream.readString();
		  companycode      		= stream.readString();
		  templateid           		= stream.readBigDecimal();
		  attributeid        		=stream.readBigDecimal();
		  required           	= stream.readString();
		  valid         		= stream.readString();
		  defaultvalue   	= stream.readString();
		  createdBy         	= stream.readString();
		  creationDate    		= stream.readTimestamp();
		  lastUpdateBy     		= stream.readString();
		  lastUpdateDate  		= stream.readTimestamp();
		  templattrassigver		= stream.readBigDecimal();
	}
	
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(	  eztableid          	);
		stream.writeString(	  companycode      	);
		stream.writeBigDecimal(	  templateid           	);
		stream.writeBigDecimal(	  attributeid        	);
		stream.writeString(	  required           	);
		stream.writeString(	  valid         	);
		stream.writeString(	  defaultvalue   	);
		stream.writeString(	  createdBy         	);
		stream.writeTimestamp(	  creationDate    	);
		stream.writeString(	  lastUpdateBy     	);
		stream.writeTimestamp(	  lastUpdateDate  	);
		stream.writeBigDecimal(	  templattrassigver      	);
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

    

    public static RowMapper stringRowMapper() {
        return new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        };
    }

    public static Object[] getitemattvalueList(String attributenm){
        clearException();
        System.out.println("in getitemattvalueList " + attributenm);
                
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall("{call CANON_E008_ITEM_TEMPLATE_PKG."+attributenm+"_LIST(?,?,?)}");
                if (statement != null) {
                    statement.registerOutParameter(1, OracleTypes.CURSOR);
                    statement.registerOutParameter(2, OracleTypes.VARCHAR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(1),CanonE008lovRec.getRowMapper() )
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
    
    public static Object[] getitemattdefvalueList(String attributenm,String defaultvalue){
        clearException();
        System.out.println("in getitemattdefvalueList "+attributenm+" : "+defaultvalue+" : ");
                
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            
	            if (connection != null) {
	
	            	if ("MARKETING_MODEL".equals(attributenm) && (!"".equals(defaultvalue)))
	            		defaultvalue = "NF-"+defaultvalue;
	            	
	            	if ("SERVICE_MODEL".equals(attributenm) && (!"".equals(defaultvalue)))
	            		defaultvalue = "NF-"+defaultvalue;

	            	
	            	if ("".equals(defaultvalue))
	            		defaultvalue = "-#*";
	            		
	            		
	            	/*if ("PRODUCT_LEVEL4".equals(attributenm))
	            		statement = (CallableStatement) connection.prepareCall("{call CANON_E008_ITEM_TEMPLATE_PKG."+attributenm+"_LIST(?,?,?,?,?)}");
	            	else
	            	*/
	            	
	            		statement = (CallableStatement) connection.prepareCall("{call CANON_E008_ITEM_TEMPLATE_PKG."+attributenm+"_LIST(?,?,?,?)}");
            	
                if (statement != null) {
                	statement.setObject(1, defaultvalue, OracleTypes.VARCHAR);
                	statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR); 
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    
                    /*if ("PRODUCT_LEVEL4".equals(attributenm))
                    	statement.setObject(5, productlevel3, OracleTypes.VARCHAR);
					*/
                    
                    statement.execute();
                    return new Object[]{cursorToList((ResultSet)statement.getObject(2),CanonE008lovRec.getRowMapper() )
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

    public Object[] getitemattdefvalueListClick(String attributenm,String defaultvalue,String productlevel3){
        clearException();
        System.out.println("in getitemattdefvalueListClick "+attributenm+" : "+defaultvalue+" : "+ productlevel3);
                
        CallableStatement statement = null;
        Connection connection = null;
        List<String> attrValues=new ArrayList<String>();
        Object[] itemMainAttrValues=null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                
            	if ("PRODUCT_LEVEL4".equals(attributenm))
            	{
                		statement = (CallableStatement) connection.prepareCall("{call CANON_E008_ITEM_TEMPLATE_PKG."+attributenm+"_LIST(?,?,?,?,?)}");
                		System.out.println("in getitemattdefvalueListClick Level4 ");
            	}
                else
                {	
                		statement = (CallableStatement) connection.prepareCall("{call CANON_E008_ITEM_TEMPLATE_PKG."+attributenm+"_LIST(?,?,?,?)}");
                		System.out.println("in getitemattdefvalueListClick Others");
                }
            	
                if (statement != null) {
                	statement.setObject(1, defaultvalue, OracleTypes.VARCHAR);
                	statement.registerOutParameter(2, OracleTypes.CURSOR);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    
                    if ("PRODUCT_LEVEL4".equals(attributenm))
                    	statement.setObject(5, productlevel3, OracleTypes.VARCHAR);
                    
                    statement.execute();
                    ResultSet rs=(ResultSet) statement.getObject(2);
                    while(rs.next())
                    {          
                 	   String str1=rs.getString(1);
                 	   String str2=rs.getString(2);
                 	  // attrValues.add(str1);
                 	   attrValues.add(str1+"*"+str2+"|");
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
    
    public Object[] getitemattdefSupplierList(String suppliercode,String attributenm,String defaultvalue){
        clearException();
        System.out.println("in getitemattdefSupplierList ");
                
        CallableStatement statement = null;
        Connection connection = null;
        List<String> attrValues=new ArrayList<String>();
        Object[] itemMainAttrValues=null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall("{call CANON_E008_ITEM_TEMPLATE_PKG."+attributenm+"_LIST(?,?,?,?,?)}");
                if (statement != null) {
                	statement.setObject(1, suppliercode, OracleTypes.VARCHAR);
                	statement.setObject(2, defaultvalue, OracleTypes.VARCHAR);
                	statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
                    statement.execute();
                    ResultSet rs=(ResultSet) statement.getObject(3);
                    while(rs.next())
                    {          
                 	   String str1=rs.getString(1);
                 	   String str2=rs.getString(2);
                 	  // attrValues.add(str1);
                 	   attrValues.add(str1+"*"+str2+"|");
                 	   //System.out.println("Attribute names::"+str2);
                    }  
                    itemMainAttrValues=new Object[]{attrValues,statement.getString(4),statement.getString(5)};
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
    
    public static Object[] savesearchcriteria(String templatename, String templatetype,String templatecat,
    											String templatestatus, String templateassigned,String templatedesc,
    											String templateatt1, String templateatt1val,
    											String templateatt2, String templateatt2val,
    											String templateatt3, String templateatt3val,
    											String templateatt4, String templateatt4val,
    											String templateatt5, String templateatt5val,
    											String user, String savedname){
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
             	   statement.setObject(1, templatename, OracleTypes.VARCHAR);
             	   statement.setObject(2, templatetype, OracleTypes.VARCHAR);
             	   statement.setObject(3, templatecat, OracleTypes.VARCHAR);
             	   statement.setObject(4, templatestatus, OracleTypes.VARCHAR);
             	   statement.setObject(5, templateassigned, OracleTypes.VARCHAR);
             	   statement.setObject(6, templatedesc, OracleTypes.VARCHAR);
             	   statement.setObject(7, templateatt1, OracleTypes.VARCHAR);
             	   statement.setObject(8, templateatt1val, OracleTypes.VARCHAR);
             	   statement.setObject(9, templateatt2, OracleTypes.VARCHAR);
             	   statement.setObject(10, templateatt2val, OracleTypes.VARCHAR);
             	   statement.setObject(11, templateatt3, OracleTypes.VARCHAR);
             	   statement.setObject(12, templateatt3val, OracleTypes.VARCHAR);
             	   statement.setObject(13, templateatt4, OracleTypes.VARCHAR);
             	   statement.setObject(14, templateatt4val, OracleTypes.VARCHAR);
             	   statement.setObject(15, templateatt5, OracleTypes.VARCHAR);
             	   statement.setObject(16, templateatt5val, OracleTypes.VARCHAR);
             	   statement.setObject(17, user, OracleTypes.VARCHAR);
             	   statement.setObject(18, savedname, OracleTypes.VARCHAR);
                   statement.registerOutParameter(19, OracleTypes.VARCHAR);
                   statement.registerOutParameter(20, OracleTypes.VARCHAR);
                   statement.execute();
                   System.out.println("in savesearchcriteria after execution");
                    return new Object[]{statement.getObject(19)
                        ,statement.getObject(20)};
                    
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
   
    public static Object[] getsavedSearchList(){
        clearException();
        System.out.println("in getsavedSearchList ");
                
        CallableStatement statement = null;
        Connection connection = null;
        try {
            connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = (CallableStatement) connection.prepareCall(GET_SAVEDSEARCH_LIST);
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

    public Object[] getsavedsearchvalues(String Name,String user){
        clearException();
        System.out.println("in geItemMainAttributesValue ");
                
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

    
    public static class CanonE008savedsearchRec  {
  	  private String	  eztableid        	;
  	  private String	  companycode      	;
  	  private String  userid        ;
  	  private String  searchname      ;
  	  private String	  templatename    	;
  	  private String	  templatetype 	;
  	  private String	  templatestatus   	;
  	  private String	  templatecat    	;
  	  private String	  templatedesc 	;
  	  private String	  itemassigned   	;
  	  private String	  attribute1    	;
  	  private String	  attribute1val 	;
  	  private String	  attribute2    	;
  	  private String	  attribute2val 	;
  	  private String	  attribute3    	;
  	  private String	  attribute3val 	;
  	  private String	  attribute4    	;
  	  private String	  attribute4val 	;
  	  private String	  attribute5    	;
  	  private String	  attribute5val 	;
      private String 	  createdBy;
      private Timestamp   creationDate;
      private String 	  lastUpdateBy;
      private Timestamp   lastUpdateDate;
        
        public CanonE008savedsearchRec(){
        }

  	public CanonE008savedsearchRec(String eztableid, String companycode,
				String userid, String searchname, String templatename,
				String templatetype, String templatestatus, String templatecat,
				String templatedesc, String itemassigned, String attribute1,
				String attribute1val, String attribute2, String attribute2val,
				String attribute3, String attribute3val, String attribute4,
				String attribute4val, String attribute5, String attribute5val,
				String createdBy, Timestamp creationDate, String lastUpdateBy,
				Timestamp lastUpdateDate) {
			super();
			this.eztableid = eztableid;
			this.companycode = companycode;
			this.userid = userid;
			this.searchname = searchname;
			this.templatename = templatename;
			this.templatetype = templatetype;
			this.templatestatus = templatestatus;
			this.templatecat = templatecat;
			this.templatedesc = templatedesc;
			this.itemassigned = itemassigned;
			this.attribute1 = attribute1;
			this.attribute1val = attribute1val;
			this.attribute2 = attribute2;
			this.attribute2val = attribute2val;
			this.attribute3 = attribute3;
			this.attribute3val = attribute3val;
			this.attribute4 = attribute4;
			this.attribute4val = attribute4val;
			this.attribute5 = attribute5;
			this.attribute5val = attribute5val;
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

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSearchname() {
		return searchname;
	}

	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public String getTemplatetype() {
		return templatetype;
	}

	public void setTemplatetype(String templatetype) {
		this.templatetype = templatetype;
	}

	public String getTemplatestatus() {
		return templatestatus;
	}

	public void setTemplatestatus(String templatestatus) {
		this.templatestatus = templatestatus;
	}

	public String getTemplatecat() {
		return templatecat;
	}

	public void setTemplatecat(String templatecat) {
		this.templatecat = templatecat;
	}

	public String getTemplatedesc() {
		return templatedesc;
	}

	public void setTemplatedesc(String templatedesc) {
		this.templatedesc = templatedesc;
	}

	public String getItemassigned() {
		return itemassigned;
	}

	public void setItemassigned(String itemassigned) {
		this.itemassigned = itemassigned;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute1val() {
		return attribute1val;
	}

	public void setAttribute1val(String attribute1val) {
		this.attribute1val = attribute1val;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute2val() {
		return attribute2val;
	}

	public void setAttribute2val(String attribute2val) {
		this.attribute2val = attribute2val;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute3val() {
		return attribute3val;
	}

	public void setAttribute3val(String attribute3val) {
		this.attribute3val = attribute3val;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute4val() {
		return attribute4val;
	}

	public void setAttribute4val(String attribute4val) {
		this.attribute4val = attribute4val;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute5val() {
		return attribute5val;
	}

	public void setAttribute5val(String attribute5val) {
		this.attribute5val = attribute5val;
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
         			return new CanonE008savedsearchRec(
                  		rs.getString("EZTABLEID"),
                  		rs.getString("EZINCOMPANYCODE"),
          				rs.getString("USER_ID"),
          				rs.getString("SEARCH_NAME"),
          				rs.getString("TEMPLATE_NAME"),
          				rs.getString("TEMPLATE_TYPE"),
          				rs.getString("TEMPLATE_STATUS"),
                  		rs.getString("TEMPLATE_CATEGORY"),
                  		rs.getString("TEMPLATE_DESCRIPTION"),
          				rs.getString("ITEM_ASSIGNED"),
          				rs.getString("ATTRIBUTE_NAME1"),
          				rs.getString("ATTRIBUTE_VAL1"),
          				rs.getString("ATTRIBUTE_NAME2"),
          				rs.getString("ATTRIBUTE_VAL2"),
          				rs.getString("ATTRIBUTE_NAME3"),
          				rs.getString("ATTRIBUTE_VAL3"),
          				rs.getString("ATTRIBUTE_NAME4"),
          				rs.getString("ATTRIBUTE_VAL4"),
          				rs.getString("ATTRIBUTE_NAME5"),
          				rs.getString("ATTRIBUTE_VAL5"),
          				rs.getString("CREATED_BY"),
          				rs.getTimestamp("CREATION_DATE"),
          				rs.getString("LAST_UPDATE_BY"),
          				rs.getTimestamp("LAST_UPDATE_DATE")
          				);
              }
         };
      }

  	
/*  	public String getSQLTypeName() throws SQLException {
  	    return "CANON_E008_TEMPL_AA_REC";
  	}*/



	
  	public void readSQL(SQLInput stream, String typeName) throws SQLException {
  		  eztableid          	= stream.readString();
  		  companycode      		= stream.readString();
  		  userid           		= stream.readString();
  		  searchname        	= stream.readString();
  		  templatename          = stream.readString();
  		  templatetype         	= stream.readString();
  		  templatestatus   		= stream.readString();
	  	  templatecat          	= stream.readString();
	  	  templatedesc     		= stream.readString();
	  	  itemassigned    		= stream.readString();
	  	  attribute1        	= stream.readString();
	  	  attribute1val         = stream.readString();
	  	  attribute2         	= stream.readString();
    	  attribute2val   		= stream.readString();
	  	  attribute3        	= stream.readString();
	  	  attribute3val         = stream.readString();
	  	  attribute4         	= stream.readString();
    	  attribute4val   		= stream.readString();
	  	  attribute5         	= stream.readString();
    	  attribute5val   		= stream.readString();
  		  createdBy         	= stream.readString();
  		  creationDate    		= stream.readTimestamp();
  		  lastUpdateBy     		= stream.readString();
  		  lastUpdateDate  		= stream.readTimestamp();
  	}
  	
  	public void writeSQL(SQLOutput stream) throws SQLException {
  		stream.writeString(	  eztableid          	);
  		stream.writeString(	  companycode      	);
  		stream.writeString(	  userid           	);
  		stream.writeString(	  searchname        	);
  		stream.writeString(	  templatename           	);
  		stream.writeString(	  templatetype         	);
  		stream.writeString(	  templatestatus   	);
  		stream.writeString(	  templatecat        	);
  		stream.writeString(	  templatedesc           	);
  		stream.writeString(	  itemassigned         	);
  		stream.writeString(	  attribute1   	);
  		stream.writeString(	  attribute1val   	);
  		stream.writeString(	  attribute2   	);
  		stream.writeString(	  attribute2val   	);
  		stream.writeString(	  attribute3   	);
  		stream.writeString(	  attribute3val   	);
  		stream.writeString(	  attribute4   	);
  		stream.writeString(	  attribute4val   	);
  		stream.writeString(	  attribute5   	);
  		stream.writeString(	  attribute5val   	);
  		stream.writeString(	  createdBy         	);
  		stream.writeTimestamp(	  creationDate    	);
  		stream.writeString(	  lastUpdateBy     	);
  		stream.writeTimestamp(	  lastUpdateDate  	);
  	}
  		
        
  }     
    
    public static void main(String args[]){
//        Object[] result=getProjectItems(new BigDecimal(1040));
//        System.out.println(result.length);
//        System.out.println(CanonE590ItemProcessUtil.asList(result));
        //project_number=1203&respId=50347,userId=393182
//        Object[] result=checkModifyProject(new BigDecimal(1203),new BigDecimal(50347),new BigDecimal(393182));
 //       Object[] result=checkStatusMonitor(new BigDecimal(1039));
//        System.out.println(CanonE008ItemProcessUtil.first(result));
    }
}
