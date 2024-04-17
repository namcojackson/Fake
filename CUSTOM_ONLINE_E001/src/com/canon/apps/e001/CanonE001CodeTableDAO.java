package com.canon.apps.e001;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Array;
import java.sql.Timestamp; 
import java.sql.SQLInput;
import java.sql.SQLOutput;

import oracle.sql.ArrayDescriptor;
import oracle.jdbc.OracleTypes;

import java.math.BigDecimal;

import oracle.apps.jtf.aom.transaction.TransactionScope;
import canon.apps.common.CanonRowMapper;

public class CanonE001CodeTableDAO {
   public static final String PROFILE_SEARCH= "{call CANON_E001_CODETABLE_UI_PKG.PROFILE_SEARCH(?,?,?,?,?,?,?,?,?,?)}";
   public static final String GET_PROFILE_DETAILS= "{call CANON_E001_CODETABLE_UI_PKG.GET_PROFILE_DETAILS(?,?,?,?,?,?)}";
   public static final String CREATE_UPDATE_PROFILE_VALUES= "{call CANON_E001_CODETABLE_UI_PKG.CREATE_UPDATE_PROFILE_VALUES(?,?,?,?,?)}";
   public static final String CREATE_UPDATE_PROFILE= "{call CANON_E001_CODETABLE_UI_PKG.CREATE_UPDATE_PROFILE(?,?,?,?,?,?)}";   
   public static final String DELETE_PROFILE_VALUES= "{call CANON_E001_CODETABLE_UI_PKG.DELETE_PROFILE_VALUES(?,?,?,?,?)}";
   public static final String CODE_NAME_SEARCH= "{call CANON_E001_CODETABLE_UI_PKG.CODE_NAME_SEARCH(?,?,?,?,?,?,?,?,?,?)}";
   public static final String GET_CODE_TAB_AND_COLS= "{call CANON_E001_CODETABLE_UI_PKG.GET_CODE_TAB_AND_COLS(?,?,?,?,?,?,?,?)}";
   public static final String CREATE_UPDATE_CODE_TABLE= "{call CANON_E001_CODETABLE_UI_PKG.CREATE_UPDATE_CODE_TABLE(?,?,?,?,?,?)}";
   public static final String CREATE_UPDATE_CODE_TAB_COL= "{call CANON_E001_CODETABLE_UI_PKG.CREATE_UPDATE_CODE_TAB_COL(?,?,?,?,?)}";
   public static final String DELETE_CODE_TAB_COL= "{call CANON_E001_CODETABLE_UI_PKG.DELETE_CODE_TAB_COL(?,?,?,?,?)}";
   public static final String GET_CODE_TAB_VALS= "{call CANON_E001_CODETABLE_UI_PKG.GET_CODE_TAB_VALS(?,?,?,?,?,?,?,?,?,?)}";
   public static final String DELETE_CODE_TAB_VAL= "{call CANON_E001_CODETABLE_UI_PKG.DELETE_CODE_TAB_VAL(?,?,?,?,?)}";
   public static final String CREATE_UPDATE_CODE_TAB_VAL= "{call CANON_E001_CODETABLE_UI_PKG.CREATE_UPDATE_CODE_TAB_VAL(?,?,?,?,?,?)}";
   public static final String GET_USER_LIST= "{call CANON_E001_CODETABLE_UI_PKG.GET_USER_LIST(?,?,?,?,?,?,?,?,?,?,?)}";
   public static final String GET_ROLE_LIST= "{call CANON_E001_CODETABLE_UI_PKG.GET_ROLE_LIST(?,?,?,?,?,?,?,?,?)}";
   public static final String CHECK_DUPLICATE_VALUES= "{call CANON_E001_CODETABLE_UI_PKG.record_duplicate_check(?,?,?,?,?,?)}";

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

   public CanonE001CodeTableDAO(){
   }
   
   public static Object[] profileSearch(String p_user_name,
		    String p_profile_name,
		    String p_order_by,
		    String p_sort_order,
		    BigDecimal p_page_no,
		    BigDecimal p_records_per_page){
       			clearException();	   
		        System.out.println("in profileSearch "+p_user_name+"|"+p_profile_name+"|"+p_order_by+"|"+p_sort_order+"|"+p_page_no+"|"+p_records_per_page);
		        CallableStatement statement = null;
		        Connection connection = null;
		        try {
		            connection = TransactionScope.getConnection();
		            if (connection != null) {
		                statement = (CallableStatement) connection.prepareCall(PROFILE_SEARCH);
		                if (statement != null) {
		                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
		                    statement.setObject(2, p_profile_name, OracleTypes.VARCHAR);
		                    statement.setObject(3, p_order_by, OracleTypes.VARCHAR);
		                    statement.setObject(4, p_sort_order, OracleTypes.VARCHAR);
		                    statement.setObject(5, p_page_no, OracleTypes.NUMBER);
		                    statement.setObject(6, p_records_per_page, OracleTypes.NUMBER);
		                    statement.registerOutParameter(7, OracleTypes.CURSOR);
		                    statement.registerOutParameter(8, OracleTypes.NUMBER);
		                    statement.registerOutParameter(9, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(10, OracleTypes.VARCHAR);

		                    statement.execute();
		                    return new Object[]{
		                        cursorToList((ResultSet)statement.getObject(7),ProfileInfo.getRowMapper())                   
		                        ,statement.getObject(8)                   
		                        ,statement.getObject(9)                   
		                        ,statement.getObject(10)};
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

   public static Object[] createUpdateProfile(String p_user_name,
		    String p_profile_name,
		    String p_profile_description,
		    BigDecimal p_profile_id){
		        clearException();
		        System.out.println("in createUpdateProfile "+p_user_name+"|"+p_profile_name+"|"+p_profile_description+"|"+p_profile_id);
		        CallableStatement statement = null;
		        Connection connection = null;
		        try {
		            connection = TransactionScope.getConnection();
		            if (connection != null) {
		                statement = (CallableStatement) connection.prepareCall(CREATE_UPDATE_PROFILE);
		                if (statement != null) {
		                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
		                    statement.setObject(2, p_profile_name, OracleTypes.VARCHAR);
		                    statement.setObject(3, p_profile_description, OracleTypes.VARCHAR);
		                    statement.setObject(4, p_profile_id, OracleTypes.NUMBER);
		                    statement.registerOutParameter(4, OracleTypes.NUMBER);
		                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
		                    statement.registerOutParameter(6, OracleTypes.VARCHAR);

		                    statement.execute();
		                    return new Object[]{
		                        statement.getObject(4)                   
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
   
		    /*
		    {call CANON_E001_CODETABLE_UI_PKG.PROFILE_SEARCH(NULL,NULL,NULL,NULL,1,1,?,?,?,?)}
		    (
		    PROFILE_ID NUMBER,
		        PROFILE_NAME VARCHAR2,
		        PROFILE_DESCRIPTION VARCHAR2
		    )
		    */

		    public static class ProfileInfo {
		       private BigDecimal profileId;
		           private String profileName;
		           private String profileDescription;
		        
		        public ProfileInfo(){
		        }
		        public ProfileInfo(BigDecimal profileId, 
		                String profileName, 
		                String profileDescription){
		            this.profileId=profileId;
		            this.profileName=profileName;
		            this.profileDescription=profileDescription;

		        }
		        public BigDecimal getProfileId() {
		            return profileId;
		        }
		        public void setProfileId(BigDecimal profileId) {
		            this.profileId=profileId;
		        }        public String getProfileName() {
		            return profileName;
		        }
		        public void setProfileName(String profileName) {
		            this.profileName=profileName;
		        }        public String getProfileDescription() {
		            return profileDescription;
		        }
		        public void setProfileDescription(String profileDescription) {
		            this.profileDescription=profileDescription;
		        }
		        public static CanonRowMapper getRowMapper(){
		            return new CanonRowMapper() {
		                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		                    return new ProfileInfo(
		                        rs.getBigDecimal("PROFILE_ID"),
		                        rs.getString("PROFILE_NAME"),
		                        rs.getString("PROFILE_DESCRIPTION")
		                    );
		                }
		            };
		        }
		        public String toString() {
		            return "ProfileInfo{" + "profileId="+profileId+", profileName="+profileName+", profileDescription="+profileDescription+'}';
		        }
		    }
   
		    public static Object[] getProfileDetails(String p_user_name,
		    	    BigDecimal p_profile_id){
		    	        clearException();
		    	        System.out.println("in getProfileDetails "+p_user_name+"|"+p_profile_id);
		    	        CallableStatement statement = null;
		    	        Connection connection = null;
		    	        try {
		    	            connection = TransactionScope.getConnection();
		    	            if (connection != null) {
		    	                statement = (CallableStatement) connection.prepareCall(GET_PROFILE_DETAILS);
		    	                if (statement != null) {
		    	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
		    	                    statement.setObject(2, p_profile_id, OracleTypes.NUMBER);
		    	                    statement.registerOutParameter(3, OracleTypes.CURSOR);
		    	                    statement.registerOutParameter(4, OracleTypes.CURSOR);
		    	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
		    	                    statement.registerOutParameter(6, OracleTypes.VARCHAR);

		    	                    statement.execute();
		    	                    return new Object[]{
		    	                        cursorToList((ResultSet)statement.getObject(3),ProfileDetail.getRowMapper())                   
		    	                        ,cursorToList((ResultSet)statement.getObject(4),ProfileValueInfo.getRowMapper())                   
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

    private static List cursorToList(ResultSet cursor,CanonRowMapper rowMapper){
        List list = new ArrayList();
        try {
            while (cursor.next()) {
                list.add(rowMapper.mapRow(cursor, 0));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                cursor.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
    
    /*
    {call CANON_E001_CODETABLE_UI_PKG.GET_PROFILE_DETAILS(NULL,1,?,?,?,?)}
    (
    PROFILE_ID NUMBER,
        PROFILE_NAME VARCHAR2,
        PROFILE_DESCRIPTION VARCHAR2
    )
    */

    public static class ProfileDetail {
       private BigDecimal profileId;
           private String profileName;
           private String profileDescription;
        
        public ProfileDetail(){
        }
        public ProfileDetail(BigDecimal profileId, 
                String profileName, 
                String profileDescription){
            this.profileId=profileId;
            this.profileName=profileName;
            this.profileDescription=profileDescription;

        }
        public BigDecimal getProfileId() {
            return profileId;
        }
        public void setProfileId(BigDecimal profileId) {
            this.profileId=profileId;
        }        public String getProfileName() {
            return profileName;
        }
        public void setProfileName(String profileName) {
            this.profileName=profileName;
        }        public String getProfileDescription() {
            return profileDescription;
        }
        public void setProfileDescription(String profileDescription) {
            this.profileDescription=profileDescription;
        }
        public static CanonRowMapper getRowMapper(){
            return new CanonRowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ProfileDetail(
                        rs.getBigDecimal("PROFILE_ID"),
                        rs.getString("PROFILE_NAME"),
                        rs.getString("PROFILE_DESCRIPTION")
                    );
                }
            };
        }
        public String toString() {
            return "ProfileDetail{" + "profileId="+profileId+", profileName="+profileName+", profileDescription="+profileDescription+'}';
        }
    }
    
    public static Object[] createUpdateProfileValues(String p_user_name,
    	    BigDecimal p_profile_id,
    	    List p_prf_value_rec_tbl){
    	        clearException();
    	        System.out.println("in createUpdateProfileValues "+p_user_name+"|"+p_profile_id+"|"+p_prf_value_rec_tbl);
    	        CallableStatement statement = null;
    	        Connection connection = null;
    	        try {
    	            connection = TransactionScope.getConnection();
    	            if (connection != null) {
    	                statement = (CallableStatement) connection.prepareCall(CREATE_UPDATE_PROFILE_VALUES);
    	                if (statement != null) {
    	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
    	                    statement.setObject(2, p_profile_id, OracleTypes.NUMBER);
    	                    statement.setObject(3, canonE001PrfValueTypeListToArray(p_prf_value_rec_tbl, TransactionScope.nativeConnection(connection)), OracleTypes.ARRAY);
    	                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
    	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);

    	                    statement.execute();
    	                    return new Object[]{
    	                        statement.getObject(4)                   
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
    

    static oracle.sql.ARRAY canonE001PrfValueTypeListToArray(List list, Connection connection) throws SQLException{
        ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E001_PRF_VALUE_TYPE", connection);
        CanonE001PrfValueRec[] elements = (CanonE001PrfValueRec[]) list.toArray(new CanonE001PrfValueRec[0]);
        oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, connection, elements);
        return array;
    }

    static List arrayToCanonE001PrfValueTypeList(Array a, Connection connection) throws SQLException{
        List list=new ArrayList();
        connection.getTypeMap().put("CANON_E001_PRF_VALUE_REC", CanonE001PrfValueRec.class);
        Object[] oo=(Object[])a.getArray();
        for (int i=0;i<oo.length;i++){
            list.add(oo[i]);
        }
        return list;
    }

    public static class CanonE001PrfValueRec implements java.sql.SQLData, java.io.Serializable  {
        private BigDecimal profileId;
        private BigDecimal profileValueId;
        private BigDecimal profileLevelNumber;
        private String profileLevelName;
        private String profileLevelValue;
        private Timestamp startDate;
        private Timestamp endDate;
        private String isActive;
        private String profileValue;

         public CanonE001PrfValueRec(){
         }
         public CanonE001PrfValueRec(BigDecimal profileId, 
                 BigDecimal profileValueId, 
                 BigDecimal profileLevelNumber, 
                 String profileLevelName, 
                 String profileLevelValue, 
                 Timestamp startDate, 
                 Timestamp endDate, 
                 String isActive, 
				String profileValue) {
			this.profileId = profileId;
			this.profileValueId = profileValueId;
			this.profileLevelNumber = profileLevelNumber;
			this.profileLevelName = profileLevelName;
			this.profileLevelValue = profileLevelValue;
			this.startDate = startDate;
			this.endDate = endDate;
			this.isActive = isActive;
			this.profileValue = profileValue;
		}

		public BigDecimal getProfileId() {
             return profileId;
         }
         public void setProfileId(BigDecimal profileId) {
             this.profileId=profileId;
         }        public BigDecimal getProfileValueId() {
             return profileValueId;
         }
         public void setProfileValueId(BigDecimal profileValueId) {
             this.profileValueId=profileValueId;
         }        public BigDecimal getProfileLevelNumber() {
             return profileLevelNumber;
         }
         public void setProfileLevelNumber(BigDecimal profileLevelNumber) {
             this.profileLevelNumber=profileLevelNumber;
         }        public String getProfileLevelName() {
             return profileLevelName;
         }
         public void setProfileLevelName(String profileLevelName) {
             this.profileLevelName=profileLevelName;
         }        public String getProfileLevelValue() {
             return profileLevelValue;
         }
         public void setProfileLevelValue(String profileLevelValue) {
             this.profileLevelValue=profileLevelValue;
         }        public Timestamp getStartDate() {
             return startDate;
         }
         public void setStartDate(Timestamp startDate) {
             this.startDate=startDate;
         }        public Timestamp getEndDate() {
             return endDate;
         }
         public void setEndDate(Timestamp endDate) {
             this.endDate=endDate;
         }        public String getIsActive() {
             return isActive;
         }
         public void setIsActive(String isActive) {
             this.isActive=isActive;
         }        public String getProfileValue() {
             return profileValue;
         }
         public void setProfileValue(String profileValue) {
             this.profileValue=profileValue;
         }

         public String getSQLTypeName() throws SQLException {
             return "CANON_E001_PRF_VALUE_REC";
         }

         public void readSQL(SQLInput stream, String typeName) throws SQLException {
                profileId = stream.readBigDecimal();
                profileValueId = stream.readBigDecimal();
                profileLevelNumber = stream.readBigDecimal();
                profileLevelName = stream.readString();
                profileLevelValue = stream.readString();
                startDate = stream.readTimestamp();
                endDate = stream.readTimestamp();
                isActive = stream.readString();
                profileValue = stream.readString();

         }

         public void writeSQL(SQLOutput stream) throws SQLException {
                stream.writeBigDecimal(profileId);
                stream.writeBigDecimal(profileValueId);
                stream.writeBigDecimal(profileLevelNumber);
                stream.writeString(profileLevelName);
                stream.writeString(profileLevelValue);
                stream.writeTimestamp(startDate);
                stream.writeTimestamp(endDate);
                stream.writeString(isActive);
                stream.writeString(profileValue);

         }

         public String toString() {
             return "CanonE001PrfValueRec{" + "profileId="+profileId+", profileValueId="+profileValueId+", profileLevelNumber="+profileLevelNumber+", profileLevelName="+profileLevelName+", profileLevelValue="+profileLevelValue+", startDate="+startDate+", endDate="+endDate+", isActive="+isActive+", profileValue="+profileValue+'}';
         }
     }
    
    /*
    {call CANON_E001_CODETABLE_UI_PKG.GET_PROFILE_DETAILS(NULL,1,?,?,?,?)}
    (
    PROFILE_ID NUMBER,
        PROFILE_VALUE_ID NUMBER,
        PROFILE_LEVEL_NUMBER NUMBER,
        PROFILE_LEVEL_NAME VARCHAR2,
        PROFILE_LEVEL_VALUE VARCHAR2,
        START_DATE DATE,
        END_DATE DATE,
        IS_ACTIVE VARCHAR2,
        PROFILE_VALUE VARCHAR2
    )
    */

    public static class ProfileValueInfo {
       private BigDecimal profileId;
           private BigDecimal profileValueId;
           private BigDecimal profileLevelNumber;
           private String profileLevelName;
           private String profileLevelValue;
           private Timestamp startDate;
           private Timestamp endDate;
           private String isActive;
           private String profileValue;
        
        public ProfileValueInfo(){
        }
        public ProfileValueInfo(BigDecimal profileId, 
                BigDecimal profileValueId, 
                BigDecimal profileLevelNumber, 
                String profileLevelName, 
                String profileLevelValue, 
                Timestamp startDate, 
                Timestamp endDate, 
                String isActive, 
                String profileValue){
            this.profileId=profileId;
            this.profileValueId=profileValueId;
            this.profileLevelNumber=profileLevelNumber;
            this.profileLevelName=profileLevelName;
            this.profileLevelValue=profileLevelValue;
            this.startDate=startDate;
            this.endDate=endDate;
            this.isActive=isActive;
            this.profileValue=profileValue;

        }
        public BigDecimal getProfileId() {
            return profileId;
        }
        public void setProfileId(BigDecimal profileId) {
            this.profileId=profileId;
        }        public BigDecimal getProfileValueId() {
            return profileValueId;
        }
        public void setProfileValueId(BigDecimal profileValueId) {
            this.profileValueId=profileValueId;
        }        public BigDecimal getProfileLevelNumber() {
            return profileLevelNumber;
        }
        public void setProfileLevelNumber(BigDecimal profileLevelNumber) {
            this.profileLevelNumber=profileLevelNumber;
        }        public String getProfileLevelName() {
            return profileLevelName;
        }
        public void setProfileLevelName(String profileLevelName) {
            this.profileLevelName=profileLevelName;
        }        public String getProfileLevelValue() {
            return profileLevelValue;
        }
        public void setProfileLevelValue(String profileLevelValue) {
            this.profileLevelValue=profileLevelValue;
        }        public Timestamp getStartDate() {
            return startDate;
        }
        public void setStartDate(Timestamp startDate) {
            this.startDate=startDate;
        }        public Timestamp getEndDate() {
            return endDate;
        }
        public void setEndDate(Timestamp endDate) {
            this.endDate=endDate;
        }        public String getIsActive() {
            return isActive;
        }
        public void setIsActive(String isActive) {
            this.isActive=isActive;
        }        public String getProfileValue() {
            return profileValue;
        }
        public void setProfileValue(String profileValue) {
            this.profileValue=profileValue;
        }
        public static CanonRowMapper getRowMapper(){
            return new CanonRowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ProfileValueInfo(
                        rs.getBigDecimal("PROFILE_ID"),
                        rs.getBigDecimal("PROFILE_VALUE_ID"),
                        rs.getBigDecimal("PROFILE_LEVEL_NUMBER"),
                        rs.getString("PROFILE_LEVEL_NAME"),
                        rs.getString("PROFILE_LEVEL_VALUE"),
                        rs.getTimestamp("START_DATE"),
                        rs.getTimestamp("END_DATE"),
                        rs.getString("IS_ACTIVE"),
                        rs.getString("PROFILE_VALUE")
                    );
                }
            };
        }
        public String toString() {
            return "ProfileValueInfo{" + "profileId="+profileId+", profileValueId="+profileValueId+", profileLevelNumber="+profileLevelNumber+", profileLevelName="+profileLevelName+", profileLevelValue="+profileLevelValue+", startDate="+startDate+", endDate="+endDate+", isActive="+isActive+", profileValue="+profileValue+'}';
        }
    }

    public static Object[] deleteProfileValues(String p_user_name,
    	    BigDecimal p_profile_id,
    	    BigDecimal p_profile_value_id){
    	        clearException();
    	        System.out.println("in deleteProfileValues "+p_user_name+"|"+p_profile_id+"|"+p_profile_value_id);
    	        CallableStatement statement = null;
    	        Connection connection = null;
    	        try {
    	            connection = TransactionScope.getConnection();
    	            if (connection != null) {
    	                statement = (CallableStatement) connection.prepareCall(DELETE_PROFILE_VALUES);
    	                if (statement != null) {
    	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
    	                    statement.setObject(2, p_profile_id, OracleTypes.NUMBER);
    	                    statement.setObject(3, p_profile_value_id, OracleTypes.NUMBER);
    	                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
    	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);

    	                    statement.execute();
    	                    return new Object[]{
    	                        statement.getObject(4)                   
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
    
    public static Object[] codeNameSearch(String p_user_name,
    	    String p_code_table_name,
    	    String p_order_by,
    	    String p_sort_order,
    	    BigDecimal p_page_no,
    	    BigDecimal p_records_per_page){
    	        clearException();
    	        System.out.println("in codeNameSearch "+p_user_name+"|"+p_code_table_name+"|"+p_order_by+"|"+p_sort_order+"|"+p_page_no+"|"+p_records_per_page);
    	        CallableStatement statement = null;
    	        Connection connection = null;
    	        try {
    	            connection = TransactionScope.getConnection();
    	            if (connection != null) {
    	                statement = (CallableStatement) connection.prepareCall(CODE_NAME_SEARCH);
    	                if (statement != null) {
    	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
    	                    statement.setObject(2, p_code_table_name, OracleTypes.VARCHAR);
    	                    statement.setObject(3, p_order_by, OracleTypes.VARCHAR);
    	                    statement.setObject(4, p_sort_order, OracleTypes.VARCHAR);
    	                    statement.setObject(5, p_page_no, OracleTypes.NUMBER);
    	                    statement.setObject(6, p_records_per_page, OracleTypes.NUMBER);
    	                    statement.registerOutParameter(7, OracleTypes.CURSOR);
    	                    statement.registerOutParameter(8, OracleTypes.NUMBER);
    	                    statement.registerOutParameter(9, OracleTypes.VARCHAR);
    	                    statement.registerOutParameter(10, OracleTypes.VARCHAR);

    	                    statement.execute();
    	                    return new Object[]{
    	                        cursorToList((ResultSet)statement.getObject(7),CodeTableInfo.getRowMapper())                   
    	                        ,statement.getObject(8)                   
    	                        ,statement.getObject(9)                   
    	                        ,statement.getObject(10)};
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
        {call CANON_E001_CODETABLE_UI_PKG.CODE_NAME_SEARCH(NULL,NULL,NULL,NULL,1,10,?,?,?,?)}
        (
        CD_ID NUMBER,
            CD_NAME VARCHAR2,
            CD_DESCRIPTION VARCHAR2,
            CD_VIEW_NAME VARCHAR2
        )
        */

        public static class CodeTableInfo {
           private BigDecimal cdId;
               private String cdName;
               private String cdDescription;
               private String cdViewName;
            
            public CodeTableInfo(){
            }
            public CodeTableInfo(BigDecimal cdId, 
                    String cdName, 
                    String cdDescription, 
                    String cdViewName){
                this.cdId=cdId;
                this.cdName=cdName;
                this.cdDescription=cdDescription;
                this.cdViewName=cdViewName;

            }
            public BigDecimal getCdId() {
                return cdId;
            }
            public void setCdId(BigDecimal cdId) {
                this.cdId=cdId;
            }        public String getCdName() {
                return cdName;
            }
            public void setCdName(String cdName) {
                this.cdName=cdName;
            }        public String getCdDescription() {
                return cdDescription;
            }
            public void setCdDescription(String cdDescription) {
                this.cdDescription=cdDescription;
            }        public String getCdViewName() {
                return cdViewName;
            }
            public void setCdViewName(String cdViewName) {
                this.cdViewName=cdViewName;
            }
            public static CanonRowMapper getRowMapper(){
                return new CanonRowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new CodeTableInfo(
                            rs.getBigDecimal("CD_ID"),
                            rs.getString("CD_NAME"),
                            rs.getString("CD_DESCRIPTION"),
                            rs.getString("CD_VIEW_NAME")
                        );
                    }
                };
            }
            public String toString() {
                return "CodeTableInfo{" + "cdId="+cdId+", cdName="+cdName+", cdDescription="+cdDescription+", cdViewName="+cdViewName+'}';
            }
        }

   
        public static Object[] getCodeTabAndCols(String p_user_name,
        	    BigDecimal p_cd_id){
        	        clearException();
        	        System.out.println("in getCodeTabAndCols "+p_user_name+"|"+p_cd_id);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(GET_CODE_TAB_AND_COLS);
        	                if (statement != null) {
        	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_cd_id, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(3, OracleTypes.CURSOR);
        	                    statement.registerOutParameter(4, OracleTypes.CURSOR);
        	                    statement.registerOutParameter(5, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(6, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(7, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(8, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    return new Object[]{
        	                        cursorToList((ResultSet)statement.getObject(3),CodeTableInfo.getRowMapper())
        	                        ,cursorToList((ResultSet)statement.getObject(4),CodeTableColumn.getRowMapper())
        	                        ,statement.getObject(5)                   
        	                        ,statement.getObject(6)                   
        	                        ,statement.getObject(7)                   
        	                        ,statement.getObject(8)};
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
        {call CANON_E001_CODETABLE_UI_PKG.GET_CODE_TAB_AND_COLS(NULL,8,?,?,?,?,?,?)}
        (
        COL_ID NUMBER,
            COL_FORMAT VARCHAR2,
            DB_COL_NAME VARCHAR2,
            COL_PROMPT VARCHAR2,
            COL_SEQ NUMBER,
            IS_MANDATORY CHAR,
            RESULT_COL VARCHAR2
        )
        */

        public static class CodeTableColumn {
           private BigDecimal colId;
               private String colFormat;
               private String dbColName;
               private String colPrompt;
               private BigDecimal colSeq;
               private String isMandatory;
               private String resultCol;
            
            public CodeTableColumn(){
            }
            public CodeTableColumn(BigDecimal colId, 
                    String colFormat, 
                    String dbColName, 
                    String colPrompt, 
                    BigDecimal colSeq, 
                    String isMandatory, 
                    String resultCol){
                this.colId=colId;
                this.colFormat=colFormat;
                this.dbColName=dbColName;
                this.colPrompt=colPrompt;
                this.colSeq=colSeq;
                this.isMandatory=isMandatory;
                this.resultCol=resultCol;

            }
            public BigDecimal getColId() {
                return colId;
            }
            public void setColId(BigDecimal colId) {
                this.colId=colId;
            }        public String getColFormat() {
                return colFormat;
            }
            public void setColFormat(String colFormat) {
                this.colFormat=colFormat;
            }        public String getDbColName() {
                return dbColName;
            }
            public void setDbColName(String dbColName) {
                this.dbColName=dbColName;
            }        public String getColPrompt() {
                return colPrompt;
            }
            public void setColPrompt(String colPrompt) {
                this.colPrompt=colPrompt;
            }        public BigDecimal getColSeq() {
                return colSeq;
            }
            public void setColSeq(BigDecimal colSeq) {
                this.colSeq=colSeq;
            }        public String getIsMandatory() {
                return isMandatory;
            }
            public void setIsMandatory(String isMandatory) {
                this.isMandatory=isMandatory;
            }        public String getResultCol() {
                return resultCol;
            }
            public void setResultCol(String resultCol) {
                this.resultCol=resultCol;
            }
            public static CanonRowMapper getRowMapper(){
                return new CanonRowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new CodeTableColumn(
                            rs.getBigDecimal("COL_ID"),
                            rs.getString("COL_FORMAT"),
                            rs.getString("DB_COL_NAME"),
                            rs.getString("COL_PROMPT"),
                            rs.getBigDecimal("COL_SEQ"),
                            rs.getString("IS_MANDATORY"),
                            rs.getString("RESULT_COL")
                        );
                    }
                };
            }
            public String toString() {
                return "CodeTableColumn{" + "colId="+colId+", colFormat="+colFormat+", dbColName="+dbColName+", colPrompt="+colPrompt+", colSeq="+colSeq+", isMandatory="+isMandatory+", resultCol="+resultCol+'}';
            }
        }
   
        public static Object[] createUpdateCodeTable(String p_user_name,
        	    String p_code_table_name,
        	    String p_code_table_description,
        	    BigDecimal p_cd_id){
        	        clearException();
        	        System.out.println("in createUpdateCodeTable "+p_user_name+"|"+p_code_table_name+"|"+p_code_table_description+"|"+p_cd_id);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(CREATE_UPDATE_CODE_TABLE);
        	                if (statement != null) {
        	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_code_table_name, OracleTypes.VARCHAR);
        	                    statement.setObject(3, p_code_table_description, OracleTypes.VARCHAR);
        	                    statement.setObject(4, p_cd_id, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(4, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(6, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    return new Object[]{
        	                        statement.getObject(4)                   
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
        
        static oracle.sql.ARRAY canonE001CodeTabColTypeListToArray(List list, Connection connection) throws SQLException{
            ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E001_CODE_TAB_COL_TYPE", connection);
            CanonE001CodeTabColRec[] elements = (CanonE001CodeTabColRec[]) list.toArray(new CanonE001CodeTabColRec[0]);
            oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, connection, elements);
            return array;
        }
        
        public static class CanonE001CodeTabColRec implements java.sql.SQLData, java.io.Serializable  {
            private BigDecimal cdId;
            private BigDecimal colId;
            private String dbColName;
            private String colFormat;
            private String colPrompt;
            private BigDecimal colSeq;
            private String isMandatory;
            private String resultCol;

             public CanonE001CodeTabColRec(){
             }
             
             public CanonE001CodeTabColRec(BigDecimal cdId, 
                     BigDecimal colId, 
                     String dbColName, 
                     String colFormat, 
                     String colPrompt, 
                     BigDecimal colSeq, 
                     String isMandatory, 
			 String resultCol) {
						this.cdId = cdId;
						this.colId = colId;
						this.dbColName = dbColName;
						this.colFormat = colFormat;
						this.colPrompt = colPrompt;
						this.colSeq = colSeq;
						this.isMandatory = isMandatory;
						this.resultCol = resultCol;
					}
			
			 public BigDecimal getCdId() {
				return cdId;
			 }

             public void setCdId(BigDecimal cdId) {
                 this.cdId=cdId;
             }        public BigDecimal getColId() {
                 return colId;
             }
             public void setColId(BigDecimal colId) {
                 this.colId=colId;
             }        public String getDbColName() {
                 return dbColName;
             }
             public void setDbColName(String dbColName) {
                 this.dbColName=dbColName;
             }        public String getColFormat() {
                 return colFormat;
             }
             public void setColFormat(String colFormat) {
                 this.colFormat=colFormat;
             }        public String getColPrompt() {
                 return colPrompt;
             }
             public void setColPrompt(String colPrompt) {
                 this.colPrompt=colPrompt;
             }        public BigDecimal getColSeq() {
                 return colSeq;
             }
             public void setColSeq(BigDecimal colSeq) {
                 this.colSeq=colSeq;
             }        public String getIsMandatory() {
                 return isMandatory;
             }
             public void setIsMandatory(String isMandatory) {
                 this.isMandatory=isMandatory;
             }        public String getResultCol() {
                 return resultCol;
             }
             public void setResultCol(String resultCol) {
                 this.resultCol=resultCol;
             }

             public String getSQLTypeName() throws SQLException {
                 return "CANON_E001_CODE_TAB_COL_REC";
             }

             public void readSQL(SQLInput stream, String typeName) throws SQLException {
                    cdId = stream.readBigDecimal();
                    colId = stream.readBigDecimal();
                    dbColName = stream.readString();
                    colFormat = stream.readString();
                    colPrompt = stream.readString();
                    colSeq = stream.readBigDecimal();
                    isMandatory = stream.readString();
                    resultCol = stream.readString();

             }

             public void writeSQL(SQLOutput stream) throws SQLException {
                    stream.writeBigDecimal(cdId);
                    stream.writeBigDecimal(colId);
                    stream.writeString(dbColName);
                    stream.writeString(colFormat);
                    stream.writeString(colPrompt);
                    stream.writeBigDecimal(colSeq);
                    stream.writeString(isMandatory);
                    stream.writeString(resultCol);

             }

             public String toString() {
                 return "CanonE001CodeTabColRec{" + "cdId="+cdId+", colId="+colId+", dbColName="+dbColName+", colFormat="+colFormat+", colPrompt="+colPrompt+", colSeq="+colSeq+", isMandatory="+isMandatory+", resultCol="+resultCol+'}';
             }
         }
        
        public static Object[] createUpdateCodeTabCol(String p_user_name,
        	    BigDecimal p_cd_id,
        	    List p_code_tab_cols_tbl){
        	        clearException();
        	        System.out.println("in createUpdateCodeTabCol "+p_user_name+"|"+p_cd_id+"|"+p_code_tab_cols_tbl);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(CREATE_UPDATE_CODE_TAB_COL);
        	                if (statement != null) {
        	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_cd_id, OracleTypes.NUMBER);
        	                    statement.setObject(3, canonE001CodeTabColTypeListToArray(p_code_tab_cols_tbl, TransactionScope.nativeConnection(connection)), OracleTypes.ARRAY);
        	                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    return new Object[]{
        	                        statement.getObject(4)                   
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
        
        public static Object[] deleteCodeTabCol(String p_user_name,
        	    BigDecimal p_cd_id,
        	    BigDecimal p_cd_col_id){
        	        clearException();
        	        System.out.println("in deleteCodeTabCol "+p_user_name+"|"+p_cd_id+"|"+p_cd_col_id);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(DELETE_CODE_TAB_COL);
        	                if (statement != null) {
        	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_cd_id, OracleTypes.NUMBER);
        	                    statement.setObject(3, p_cd_col_id, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    return new Object[]{
        	                        statement.getObject(4)                   
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
        
        
        public static Object[] getCodeTabVals(String p_user_name,
        	    BigDecimal p_cd_id,
        	    String p_order_by,
        	    String p_sort_order,
        	    BigDecimal p_page_no,
        	    BigDecimal p_records_per_page, final List<CanonE001CodeTableDAO.CodeTableColumn > columnList){
        	        clearException();
        	        System.out.println("in getCodeTabVals "+p_user_name+"|"+p_cd_id+"|"+p_order_by+"|"+p_sort_order+"|"+p_page_no+"|"+p_records_per_page);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(GET_CODE_TAB_VALS);
        	                if (statement != null) {
        	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_cd_id, OracleTypes.NUMBER);
        	                    statement.setObject(3, p_order_by, OracleTypes.VARCHAR);
        	                    statement.setObject(4, p_sort_order, OracleTypes.VARCHAR);
        	                    statement.setObject(5, p_page_no, OracleTypes.NUMBER);
        	                    statement.setObject(6, p_records_per_page, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(7, OracleTypes.CURSOR);
        	                    statement.registerOutParameter(8, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(9, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(10, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    return new Object[]{
        	                        cursorToList((ResultSet)statement.getObject(7),codeTableValRowMapper(columnList))                   
        	                        ,statement.getObject(8)                   
        	                        ,statement.getObject(9)                   
        	                        ,statement.getObject(10)};
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
        
        public static interface RowCallbackHandler {
        	public void processRow(ResultSet rs) throws SQLException;
        }
        
        public static void processCursor( ResultSet cursor, RowCallbackHandler rowCallbackHandler){
            try {
                while (cursor.next()) {
                    rowCallbackHandler.processRow(cursor);
                }
            } catch (SQLException ex) {
              System.err.println(ex);
            }finally{
                try {
                    cursor.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
        
        public static CanonRowMapper codeTableValRowMapper(final List<CanonE001CodeTableDAO.CodeTableColumn > columnList){
            return new CanonRowMapper() {
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                	String [] values=new String[columnList.size()];
					for(int i=0;i<columnList.size();i++){
						CanonE001CodeTableDAO.CodeTableColumn column = columnList.get(i);
					    int colIndex=CanonE001CommonUtil.toInt(column.getResultCol().substring(3));
						String val=null;
					    if(CanonE001CommonUtil.isCharCol(colIndex)){
    						val=rs.getString(3+i);
					    }else if (CanonE001CommonUtil.isNumberCol(colIndex)){
							val=CanonE001CommonUtil.checkNull(rs.getBigDecimal(3+i));
					    }else if (CanonE001CommonUtil.isDateCol(colIndex)){
							val=CanonE001CommonUtil.formatDateTime5(rs.getTimestamp(3+i));
					    }
						values[i]=val;
					}	
					CanonS21CdVal v=new CanonS21CdVal();
					v.values=values;
					v.valID=rs.getBigDecimal(2);
                    return v;
                }
            };
        }
        
        public static class CanonS21CdVal {
        	public String [] values;
        	public BigDecimal valID;
			@Override
			public String toString() {
				return "CanonS21CdVal [values=" + Arrays.toString(values)
						+ ", valID=" + valID + "]";
			}
        }
        
        
        public static Object[] deleteCodeTabVal(String p_user_name,
        	    BigDecimal p_cd_id,
        	    BigDecimal p_cd_val_id){
        	        clearException();
        	        System.out.println("in deleteCodeTabVal "+p_user_name+"|"+p_cd_id+"|"+p_cd_val_id);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(DELETE_CODE_TAB_VAL);
        	                if (statement != null) {
        	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_cd_id, OracleTypes.NUMBER);
        	                    statement.setObject(3, p_cd_val_id, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    return new Object[]{
        	                        statement.getObject(4)                   
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
        
        public static Object[] createUpdateCodeTabVal(String p_user_name,
        	    BigDecimal p_cd_id,
        	    List p_code_tab_cols_tbl,
        	    List p_code_tab_vals_tbl){
        	        clearException();
        	        System.out.println("in createUpdateCodeTabVal "+p_user_name+"|"+p_cd_id+"|"+p_code_tab_cols_tbl+"|"+p_code_tab_vals_tbl);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(CREATE_UPDATE_CODE_TAB_VAL);
        	                if (statement != null) {
        	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_cd_id, OracleTypes.NUMBER);
        	                    statement.setObject(3, canonE001CodeTabColTypeListToArray(p_code_tab_cols_tbl, TransactionScope.nativeConnection(connection)), OracleTypes.ARRAY);
        	                    statement.setObject(4, canonE001CodeTabValTypeListToArray(p_code_tab_vals_tbl, TransactionScope.nativeConnection(connection)), OracleTypes.ARRAY);
        	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(6, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    return new Object[]{
        	                        statement.getObject(5)                   
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
        
        public static Object[] checkDuplicateValues(String p_user_name,
        	    BigDecimal p_cd_id,
        	    List p_code_tab_cols_tbl,
        	    List p_code_tab_vals_tbl){
        	        clearException();
        	        System.out.println("in checkDuplicateValues "+p_user_name+"|"+p_cd_id+"|"+p_code_tab_cols_tbl+"|"+p_code_tab_vals_tbl);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(CHECK_DUPLICATE_VALUES);
        	                if (statement != null) {
        	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_cd_id, OracleTypes.NUMBER);
        	                    statement.setObject(3, canonE001CodeTabColTypeListToArray(p_code_tab_cols_tbl, TransactionScope.nativeConnection(connection)), OracleTypes.ARRAY);
        	                    statement.setObject(4, canonE001CodeTabValTypeListToArray(p_code_tab_vals_tbl, TransactionScope.nativeConnection(connection)), OracleTypes.ARRAY);
        	                    statement.registerOutParameter(5, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(6, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    System.out.println("return values in checkDuplicateValues: ");
        	                    System.out.println("Flag in checkDuplicateValues: "+statement.getObject(5));
        	                    System.out.println("Message: in checkDuplicateValues: "+statement.getObject(6));
        	                    		
        	                    return new Object[]{
        	                        statement.getObject(5)                   
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
 
        static oracle.sql.ARRAY canonE001CodeTabValTypeListToArray(List list, Connection connection) throws SQLException{
            ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("CANON_E001_CODE_TAB_VAL_TYPE", connection);
            CanonE001CodeTabValRec[] elements = (CanonE001CodeTabValRec[]) list.toArray(new CanonE001CodeTabValRec[0]);
            oracle.sql.ARRAY array = new oracle.sql.ARRAY(arraydesc, connection, elements);
            return array;
        }
 
        public static class CanonE001CodeTabValRec implements java.sql.SQLData, java.io.Serializable  {
            private BigDecimal cdId;
            private BigDecimal valId;
            private String val1;
            private String val2;
            private String val3;
            private String val4;
            private String val5;
            private String val6;
            private String val7;
            private String val8;
            private String val9;
            private String val10;
            private String val11;
            private String val12;
            private String val13;
            private String val14;
            private String val15;
            private String val16;
            private String val17;
            private String val18;
            private String val19;
            private String val20;
            private String val21;
            private String val22;
            private String val23;
            private String val24;
            private String val25;
            private String val26;
            private String val27;
            private String val28;
            private String val29;
            private String val30;
            private String val31;
            private String val32;
            private String val33;
            private String val34;
            private String val35;
            private String val36;
            private String val37;
            private String val38;
            private String val39;
            private String val40;
            private String val41;
            private String val42;
            private String val43;
            private String val44;
            private String val45;
            private String val46;
            private String val47;
            private String val48;
            private String val49;
            private String val50;
            private BigDecimal val51;
            private BigDecimal val52;
            private BigDecimal val53;
            private BigDecimal val54;
            private BigDecimal val55;
            private BigDecimal val56;
            private BigDecimal val57;
            private BigDecimal val58;
            private BigDecimal val59;
            private BigDecimal val60;
            private BigDecimal val61;
            private BigDecimal val62;
            private BigDecimal val63;
            private BigDecimal val64;
            private BigDecimal val65;
            private BigDecimal val66;
            private BigDecimal val67;
            private BigDecimal val68;
            private BigDecimal val69;
            private BigDecimal val70;
            private BigDecimal val71;
            private BigDecimal val72;
            private BigDecimal val73;
            private BigDecimal val74;
            private BigDecimal val75;
            private Timestamp val76;
            private Timestamp val77;
            private Timestamp val78;
            private Timestamp val79;
            private Timestamp val80;
            private Timestamp val81;
            private Timestamp val82;
            private Timestamp val83;
            private Timestamp val84;
            private Timestamp val85;
            private Timestamp val86;
            private Timestamp val87;
            private Timestamp val88;
            private Timestamp val89;
            private Timestamp val90;
            private Timestamp val91;
            private Timestamp val92;
            private Timestamp val93;
            private Timestamp val94;
            private Timestamp val95;
            private Timestamp val96;
            private Timestamp val97;
            private Timestamp val98;
            private Timestamp val99;
            private Timestamp val100;

             public CanonE001CodeTabValRec(){
             }
             public CanonE001CodeTabValRec(BigDecimal cdId, 
                     BigDecimal valId, 
                     String val1, 
                     String val2, 
                     String val3, 
                     String val4, 
                     String val5, 
                     String val6, 
                     String val7, 
                     String val8, 
                     String val9, 
                     String val10, 
                     String val11, 
                     String val12, 
                     String val13, 
                     String val14, 
                     String val15, 
                     String val16, 
                     String val17, 
                     String val18, 
                     String val19, 
                     String val20, 
                     String val21, 
                     String val22, 
                     String val23, 
                     String val24, 
                     String val25, 
                     String val26, 
                     String val27, 
                     String val28, 
                     String val29, 
                     String val30, 
                     String val31, 
                     String val32, 
                     String val33, 
                     String val34, 
                     String val35, 
                     String val36, 
                     String val37, 
                     String val38, 
                     String val39, 
                     String val40, 
                     String val41, 
                     String val42, 
                     String val43, 
                     String val44, 
                     String val45, 
                     String val46, 
                     String val47, 
                     String val48, 
                     String val49, 
                     String val50, 
                     BigDecimal val51, 
                     BigDecimal val52, 
                     BigDecimal val53, 
                     BigDecimal val54, 
                     BigDecimal val55, 
                     BigDecimal val56, 
                     BigDecimal val57, 
                     BigDecimal val58, 
                     BigDecimal val59, 
                     BigDecimal val60, 
                     BigDecimal val61, 
                     BigDecimal val62, 
                     BigDecimal val63, 
                     BigDecimal val64, 
                     BigDecimal val65, 
                     BigDecimal val66, 
                     BigDecimal val67, 
                     BigDecimal val68, 
                     BigDecimal val69, 
                     BigDecimal val70, 
                     BigDecimal val71, 
                     BigDecimal val72, 
                     BigDecimal val73, 
                     BigDecimal val74, 
                     BigDecimal val75, 
                     Timestamp val76, 
                     Timestamp val77, 
                     Timestamp val78, 
                     Timestamp val79, 
                     Timestamp val80, 
                     Timestamp val81, 
                     Timestamp val82, 
                     Timestamp val83, 
                     Timestamp val84, 
                     Timestamp val85, 
                     Timestamp val86, 
                     Timestamp val87, 
                     Timestamp val88, 
                     Timestamp val89, 
                     Timestamp val90, 
                     Timestamp val91, 
                     Timestamp val92, 
                     Timestamp val93, 
                     Timestamp val94, 
                     Timestamp val95, 
                     Timestamp val96, 
                     Timestamp val97, 
                     Timestamp val98, 
                     Timestamp val99, 
                     Timestamp val100){            this.cdId=cdId;            this.valId=valId;            this.val1=val1;            this.val2=val2;            this.val3=val3;            this.val4=val4;            this.val5=val5;            this.val6=val6;            this.val7=val7;            this.val8=val8;            this.val9=val9;            this.val10=val10;            this.val11=val11;            this.val12=val12;            this.val13=val13;            this.val14=val14;            this.val15=val15;            this.val16=val16;            this.val17=val17;            this.val18=val18;            this.val19=val19;            this.val20=val20;            this.val21=val21;            this.val22=val22;            this.val23=val23;            this.val24=val24;            this.val25=val25;            this.val26=val26;            this.val27=val27;            this.val28=val28;            this.val29=val29;            this.val30=val30;            this.val31=val31;            this.val32=val32;            this.val33=val33;            this.val34=val34;            this.val35=val35;            this.val36=val36;            this.val37=val37;            this.val38=val38;            this.val39=val39;            this.val40=val40;            this.val41=val41;            this.val42=val42;            this.val43=val43;            this.val44=val44;            this.val45=val45;            this.val46=val46;            this.val47=val47;            this.val48=val48;            this.val49=val49;            this.val50=val50;            this.val51=val51;            this.val52=val52;            this.val53=val53;            this.val54=val54;            this.val55=val55;            this.val56=val56;            this.val57=val57;            this.val58=val58;            this.val59=val59;            this.val60=val60;            this.val61=val61;            this.val62=val62;            this.val63=val63;            this.val64=val64;            this.val65=val65;            this.val66=val66;            this.val67=val67;            this.val68=val68;            this.val69=val69;            this.val70=val70;            this.val71=val71;            this.val72=val72;            this.val73=val73;            this.val74=val74;            this.val75=val75;            this.val76=val76;            this.val77=val77;            this.val78=val78;            this.val79=val79;            this.val80=val80;            this.val81=val81;            this.val82=val82;            this.val83=val83;            this.val84=val84;            this.val85=val85;            this.val86=val86;            this.val87=val87;            this.val88=val88;            this.val89=val89;            this.val90=val90;            this.val91=val91;            this.val92=val92;            this.val93=val93;            this.val94=val94;            this.val95=val95;            this.val96=val96;            this.val97=val97;            this.val98=val98;            this.val99=val99;            this.val100=val100;
             }        public BigDecimal getCdId() {
                 return cdId;
             }
             public void setCdId(BigDecimal cdId) {
                 this.cdId=cdId;
             }        public BigDecimal getValId() {
                 return valId;
             }
             public void setValId(BigDecimal valId) {
                 this.valId=valId;
             }        public String getVal1() {
                 return val1;
             }
             public void setVal1(String val1) {
                 this.val1=val1;
             }        public String getVal2() {
                 return val2;
             }
             public void setVal2(String val2) {
                 this.val2=val2;
             }        public String getVal3() {
                 return val3;
             }
             public void setVal3(String val3) {
                 this.val3=val3;
             }        public String getVal4() {
                 return val4;
             }
             public void setVal4(String val4) {
                 this.val4=val4;
             }        public String getVal5() {
                 return val5;
             }
             public void setVal5(String val5) {
                 this.val5=val5;
             }        public String getVal6() {
                 return val6;
             }
             public void setVal6(String val6) {
                 this.val6=val6;
             }        public String getVal7() {
                 return val7;
             }
             public void setVal7(String val7) {
                 this.val7=val7;
             }        public String getVal8() {
                 return val8;
             }
             public void setVal8(String val8) {
                 this.val8=val8;
             }        public String getVal9() {
                 return val9;
             }
             public void setVal9(String val9) {
                 this.val9=val9;
             }        public String getVal10() {
                 return val10;
             }
             public void setVal10(String val10) {
                 this.val10=val10;
             }        public String getVal11() {
                 return val11;
             }
             public void setVal11(String val11) {
                 this.val11=val11;
             }        public String getVal12() {
                 return val12;
             }
             public void setVal12(String val12) {
                 this.val12=val12;
             }        public String getVal13() {
                 return val13;
             }
             public void setVal13(String val13) {
                 this.val13=val13;
             }        public String getVal14() {
                 return val14;
             }
             public void setVal14(String val14) {
                 this.val14=val14;
             }        public String getVal15() {
                 return val15;
             }
             public void setVal15(String val15) {
                 this.val15=val15;
             }        public String getVal16() {
                 return val16;
             }
             public void setVal16(String val16) {
                 this.val16=val16;
             }        public String getVal17() {
                 return val17;
             }
             public void setVal17(String val17) {
                 this.val17=val17;
             }        public String getVal18() {
                 return val18;
             }
             public void setVal18(String val18) {
                 this.val18=val18;
             }        public String getVal19() {
                 return val19;
             }
             public void setVal19(String val19) {
                 this.val19=val19;
             }        public String getVal20() {
                 return val20;
             }
             public void setVal20(String val20) {
                 this.val20=val20;
             }        public String getVal21() {
                 return val21;
             }
             public void setVal21(String val21) {
                 this.val21=val21;
             }        public String getVal22() {
                 return val22;
             }
             public void setVal22(String val22) {
                 this.val22=val22;
             }        public String getVal23() {
                 return val23;
             }
             public void setVal23(String val23) {
                 this.val23=val23;
             }        public String getVal24() {
                 return val24;
             }
             public void setVal24(String val24) {
                 this.val24=val24;
             }        public String getVal25() {
                 return val25;
             }
             public void setVal25(String val25) {
                 this.val25=val25;
             }        public String getVal26() {
                 return val26;
             }
             public void setVal26(String val26) {
                 this.val26=val26;
             }        public String getVal27() {
                 return val27;
             }
             public void setVal27(String val27) {
                 this.val27=val27;
             }        public String getVal28() {
                 return val28;
             }
             public void setVal28(String val28) {
                 this.val28=val28;
             }        public String getVal29() {
                 return val29;
             }
             public void setVal29(String val29) {
                 this.val29=val29;
             }        public String getVal30() {
                 return val30;
             }
             public void setVal30(String val30) {
                 this.val30=val30;
             }        public String getVal31() {
                 return val31;
             }
             public void setVal31(String val31) {
                 this.val31=val31;
             }        public String getVal32() {
                 return val32;
             }
             public void setVal32(String val32) {
                 this.val32=val32;
             }        public String getVal33() {
                 return val33;
             }
             public void setVal33(String val33) {
                 this.val33=val33;
             }        public String getVal34() {
                 return val34;
             }
             public void setVal34(String val34) {
                 this.val34=val34;
             }        public String getVal35() {
                 return val35;
             }
             public void setVal35(String val35) {
                 this.val35=val35;
             }        public String getVal36() {
                 return val36;
             }
             public void setVal36(String val36) {
                 this.val36=val36;
             }        public String getVal37() {
                 return val37;
             }
             public void setVal37(String val37) {
                 this.val37=val37;
             }        public String getVal38() {
                 return val38;
             }
             public void setVal38(String val38) {
                 this.val38=val38;
             }        public String getVal39() {
                 return val39;
             }
             public void setVal39(String val39) {
                 this.val39=val39;
             }        public String getVal40() {
                 return val40;
             }
             public void setVal40(String val40) {
                 this.val40=val40;
             }        public String getVal41() {
                 return val41;
             }
             public void setVal41(String val41) {
                 this.val41=val41;
             }        public String getVal42() {
                 return val42;
             }
             public void setVal42(String val42) {
                 this.val42=val42;
             }        public String getVal43() {
                 return val43;
             }
             public void setVal43(String val43) {
                 this.val43=val43;
             }        public String getVal44() {
                 return val44;
             }
             public void setVal44(String val44) {
                 this.val44=val44;
             }        public String getVal45() {
                 return val45;
             }
             public void setVal45(String val45) {
                 this.val45=val45;
             }        public String getVal46() {
                 return val46;
             }
             public void setVal46(String val46) {
                 this.val46=val46;
             }        public String getVal47() {
                 return val47;
             }
             public void setVal47(String val47) {
                 this.val47=val47;
             }        public String getVal48() {
                 return val48;
             }
             public void setVal48(String val48) {
                 this.val48=val48;
             }        public String getVal49() {
                 return val49;
             }
             public void setVal49(String val49) {
                 this.val49=val49;
             }        public String getVal50() {
                 return val50;
             }
             public void setVal50(String val50) {
                 this.val50=val50;
             }        public BigDecimal getVal51() {
                 return val51;
             }
             public void setVal51(BigDecimal val51) {
                 this.val51=val51;
             }        public BigDecimal getVal52() {
                 return val52;
             }
             public void setVal52(BigDecimal val52) {
                 this.val52=val52;
             }        public BigDecimal getVal53() {
                 return val53;
             }
             public void setVal53(BigDecimal val53) {
                 this.val53=val53;
             }        public BigDecimal getVal54() {
                 return val54;
             }
             public void setVal54(BigDecimal val54) {
                 this.val54=val54;
             }        public BigDecimal getVal55() {
                 return val55;
             }
             public void setVal55(BigDecimal val55) {
                 this.val55=val55;
             }        public BigDecimal getVal56() {
                 return val56;
             }
             public void setVal56(BigDecimal val56) {
                 this.val56=val56;
             }        public BigDecimal getVal57() {
                 return val57;
             }
             public void setVal57(BigDecimal val57) {
                 this.val57=val57;
             }        public BigDecimal getVal58() {
                 return val58;
             }
             public void setVal58(BigDecimal val58) {
                 this.val58=val58;
             }        public BigDecimal getVal59() {
                 return val59;
             }
             public void setVal59(BigDecimal val59) {
                 this.val59=val59;
             }        public BigDecimal getVal60() {
                 return val60;
             }
             public void setVal60(BigDecimal val60) {
                 this.val60=val60;
             }        public BigDecimal getVal61() {
                 return val61;
             }
             public void setVal61(BigDecimal val61) {
                 this.val61=val61;
             }        public BigDecimal getVal62() {
                 return val62;
             }
             public void setVal62(BigDecimal val62) {
                 this.val62=val62;
             }        public BigDecimal getVal63() {
                 return val63;
             }
             public void setVal63(BigDecimal val63) {
                 this.val63=val63;
             }        public BigDecimal getVal64() {
                 return val64;
             }
             public void setVal64(BigDecimal val64) {
                 this.val64=val64;
             }        public BigDecimal getVal65() {
                 return val65;
             }
             public void setVal65(BigDecimal val65) {
                 this.val65=val65;
             }        public BigDecimal getVal66() {
                 return val66;
             }
             public void setVal66(BigDecimal val66) {
                 this.val66=val66;
             }        public BigDecimal getVal67() {
                 return val67;
             }
             public void setVal67(BigDecimal val67) {
                 this.val67=val67;
             }        public BigDecimal getVal68() {
                 return val68;
             }
             public void setVal68(BigDecimal val68) {
                 this.val68=val68;
             }        public BigDecimal getVal69() {
                 return val69;
             }
             public void setVal69(BigDecimal val69) {
                 this.val69=val69;
             }        public BigDecimal getVal70() {
                 return val70;
             }
             public void setVal70(BigDecimal val70) {
                 this.val70=val70;
             }        public BigDecimal getVal71() {
                 return val71;
             }
             public void setVal71(BigDecimal val71) {
                 this.val71=val71;
             }        public BigDecimal getVal72() {
                 return val72;
             }
             public void setVal72(BigDecimal val72) {
                 this.val72=val72;
             }        public BigDecimal getVal73() {
                 return val73;
             }
             public void setVal73(BigDecimal val73) {
                 this.val73=val73;
             }        public BigDecimal getVal74() {
                 return val74;
             }
             public void setVal74(BigDecimal val74) {
                 this.val74=val74;
             }        public BigDecimal getVal75() {
                 return val75;
             }
             public void setVal75(BigDecimal val75) {
                 this.val75=val75;
             }        public Timestamp getVal76() {
                 return val76;
             }
             public void setVal76(Timestamp val76) {
                 this.val76=val76;
             }        public Timestamp getVal77() {
                 return val77;
             }
             public void setVal77(Timestamp val77) {
                 this.val77=val77;
             }        public Timestamp getVal78() {
                 return val78;
             }
             public void setVal78(Timestamp val78) {
                 this.val78=val78;
             }        public Timestamp getVal79() {
                 return val79;
             }
             public void setVal79(Timestamp val79) {
                 this.val79=val79;
             }        public Timestamp getVal80() {
                 return val80;
             }
             public void setVal80(Timestamp val80) {
                 this.val80=val80;
             }        public Timestamp getVal81() {
                 return val81;
             }
             public void setVal81(Timestamp val81) {
                 this.val81=val81;
             }        public Timestamp getVal82() {
                 return val82;
             }
             public void setVal82(Timestamp val82) {
                 this.val82=val82;
             }        public Timestamp getVal83() {
                 return val83;
             }
             public void setVal83(Timestamp val83) {
                 this.val83=val83;
             }        public Timestamp getVal84() {
                 return val84;
             }
             public void setVal84(Timestamp val84) {
                 this.val84=val84;
             }        public Timestamp getVal85() {
                 return val85;
             }
             public void setVal85(Timestamp val85) {
                 this.val85=val85;
             }        public Timestamp getVal86() {
                 return val86;
             }
             public void setVal86(Timestamp val86) {
                 this.val86=val86;
             }        public Timestamp getVal87() {
                 return val87;
             }
             public void setVal87(Timestamp val87) {
                 this.val87=val87;
             }        public Timestamp getVal88() {
                 return val88;
             }
             public void setVal88(Timestamp val88) {
                 this.val88=val88;
             }        public Timestamp getVal89() {
                 return val89;
             }
             public void setVal89(Timestamp val89) {
                 this.val89=val89;
             }        public Timestamp getVal90() {
                 return val90;
             }
             public void setVal90(Timestamp val90) {
                 this.val90=val90;
             }        public Timestamp getVal91() {
                 return val91;
             }
             public void setVal91(Timestamp val91) {
                 this.val91=val91;
             }        public Timestamp getVal92() {
                 return val92;
             }
             public void setVal92(Timestamp val92) {
                 this.val92=val92;
             }        public Timestamp getVal93() {
                 return val93;
             }
             public void setVal93(Timestamp val93) {
                 this.val93=val93;
             }        public Timestamp getVal94() {
                 return val94;
             }
             public void setVal94(Timestamp val94) {
                 this.val94=val94;
             }        public Timestamp getVal95() {
                 return val95;
             }
             public void setVal95(Timestamp val95) {
                 this.val95=val95;
             }        public Timestamp getVal96() {
                 return val96;
             }
             public void setVal96(Timestamp val96) {
                 this.val96=val96;
             }        public Timestamp getVal97() {
                 return val97;
             }
             public void setVal97(Timestamp val97) {
                 this.val97=val97;
             }        public Timestamp getVal98() {
                 return val98;
             }
             public void setVal98(Timestamp val98) {
                 this.val98=val98;
             }        public Timestamp getVal99() {
                 return val99;
             }
             public void setVal99(Timestamp val99) {
                 this.val99=val99;
             }        public Timestamp getVal100() {
                 return val100;
             }
             public void setVal100(Timestamp val100) {
                 this.val100=val100;
             }

             public String getSQLTypeName() throws SQLException {
                 return "CANON_E001_CODE_TAB_VAL_REC";
             }

             public void readSQL(SQLInput stream, String typeName) throws SQLException {
                    cdId = stream.readBigDecimal();
                    valId = stream.readBigDecimal();
                    val1 = stream.readString();
                    val2 = stream.readString();
                    val3 = stream.readString();
                    val4 = stream.readString();
                    val5 = stream.readString();
                    val6 = stream.readString();
                    val7 = stream.readString();
                    val8 = stream.readString();
                    val9 = stream.readString();
                    val10 = stream.readString();
                    val11 = stream.readString();
                    val12 = stream.readString();
                    val13 = stream.readString();
                    val14 = stream.readString();
                    val15 = stream.readString();
                    val16 = stream.readString();
                    val17 = stream.readString();
                    val18 = stream.readString();
                    val19 = stream.readString();
                    val20 = stream.readString();
                    val21 = stream.readString();
                    val22 = stream.readString();
                    val23 = stream.readString();
                    val24 = stream.readString();
                    val25 = stream.readString();
                    val26 = stream.readString();
                    val27 = stream.readString();
                    val28 = stream.readString();
                    val29 = stream.readString();
                    val30 = stream.readString();
                    val31 = stream.readString();
                    val32 = stream.readString();
                    val33 = stream.readString();
                    val34 = stream.readString();
                    val35 = stream.readString();
                    val36 = stream.readString();
                    val37 = stream.readString();
                    val38 = stream.readString();
                    val39 = stream.readString();
                    val40 = stream.readString();
                    val41 = stream.readString();
                    val42 = stream.readString();
                    val43 = stream.readString();
                    val44 = stream.readString();
                    val45 = stream.readString();
                    val46 = stream.readString();
                    val47 = stream.readString();
                    val48 = stream.readString();
                    val49 = stream.readString();
                    val50 = stream.readString();
                    val51 = stream.readBigDecimal();
                    val52 = stream.readBigDecimal();
                    val53 = stream.readBigDecimal();
                    val54 = stream.readBigDecimal();
                    val55 = stream.readBigDecimal();
                    val56 = stream.readBigDecimal();
                    val57 = stream.readBigDecimal();
                    val58 = stream.readBigDecimal();
                    val59 = stream.readBigDecimal();
                    val60 = stream.readBigDecimal();
                    val61 = stream.readBigDecimal();
                    val62 = stream.readBigDecimal();
                    val63 = stream.readBigDecimal();
                    val64 = stream.readBigDecimal();
                    val65 = stream.readBigDecimal();
                    val66 = stream.readBigDecimal();
                    val67 = stream.readBigDecimal();
                    val68 = stream.readBigDecimal();
                    val69 = stream.readBigDecimal();
                    val70 = stream.readBigDecimal();
                    val71 = stream.readBigDecimal();
                    val72 = stream.readBigDecimal();
                    val73 = stream.readBigDecimal();
                    val74 = stream.readBigDecimal();
                    val75 = stream.readBigDecimal();
                    val76 = stream.readTimestamp();
                    val77 = stream.readTimestamp();
                    val78 = stream.readTimestamp();
                    val79 = stream.readTimestamp();
                    val80 = stream.readTimestamp();
                    val81 = stream.readTimestamp();
                    val82 = stream.readTimestamp();
                    val83 = stream.readTimestamp();
                    val84 = stream.readTimestamp();
                    val85 = stream.readTimestamp();
                    val86 = stream.readTimestamp();
                    val87 = stream.readTimestamp();
                    val88 = stream.readTimestamp();
                    val89 = stream.readTimestamp();
                    val90 = stream.readTimestamp();
                    val91 = stream.readTimestamp();
                    val92 = stream.readTimestamp();
                    val93 = stream.readTimestamp();
                    val94 = stream.readTimestamp();
                    val95 = stream.readTimestamp();
                    val96 = stream.readTimestamp();
                    val97 = stream.readTimestamp();
                    val98 = stream.readTimestamp();
                    val99 = stream.readTimestamp();
                    val100 = stream.readTimestamp();

             }

             public void writeSQL(SQLOutput stream) throws SQLException {
                    stream.writeBigDecimal(cdId);
                    stream.writeBigDecimal(valId);
                    stream.writeString(val1);
                    stream.writeString(val2);
                    stream.writeString(val3);
                    stream.writeString(val4);
                    stream.writeString(val5);
                    stream.writeString(val6);
                    stream.writeString(val7);
                    stream.writeString(val8);
                    stream.writeString(val9);
                    stream.writeString(val10);
                    stream.writeString(val11);
                    stream.writeString(val12);
                    stream.writeString(val13);
                    stream.writeString(val14);
                    stream.writeString(val15);
                    stream.writeString(val16);
                    stream.writeString(val17);
                    stream.writeString(val18);
                    stream.writeString(val19);
                    stream.writeString(val20);
                    stream.writeString(val21);
                    stream.writeString(val22);
                    stream.writeString(val23);
                    stream.writeString(val24);
                    stream.writeString(val25);
                    stream.writeString(val26);
                    stream.writeString(val27);
                    stream.writeString(val28);
                    stream.writeString(val29);
                    stream.writeString(val30);
                    stream.writeString(val31);
                    stream.writeString(val32);
                    stream.writeString(val33);
                    stream.writeString(val34);
                    stream.writeString(val35);
                    stream.writeString(val36);
                    stream.writeString(val37);
                    stream.writeString(val38);
                    stream.writeString(val39);
                    stream.writeString(val40);
                    stream.writeString(val41);
                    stream.writeString(val42);
                    stream.writeString(val43);
                    stream.writeString(val44);
                    stream.writeString(val45);
                    stream.writeString(val46);
                    stream.writeString(val47);
                    stream.writeString(val48);
                    stream.writeString(val49);
                    stream.writeString(val50);
                    stream.writeBigDecimal(val51);
                    stream.writeBigDecimal(val52);
                    stream.writeBigDecimal(val53);
                    stream.writeBigDecimal(val54);
                    stream.writeBigDecimal(val55);
                    stream.writeBigDecimal(val56);
                    stream.writeBigDecimal(val57);
                    stream.writeBigDecimal(val58);
                    stream.writeBigDecimal(val59);
                    stream.writeBigDecimal(val60);
                    stream.writeBigDecimal(val61);
                    stream.writeBigDecimal(val62);
                    stream.writeBigDecimal(val63);
                    stream.writeBigDecimal(val64);
                    stream.writeBigDecimal(val65);
                    stream.writeBigDecimal(val66);
                    stream.writeBigDecimal(val67);
                    stream.writeBigDecimal(val68);
                    stream.writeBigDecimal(val69);
                    stream.writeBigDecimal(val70);
                    stream.writeBigDecimal(val71);
                    stream.writeBigDecimal(val72);
                    stream.writeBigDecimal(val73);
                    stream.writeBigDecimal(val74);
                    stream.writeBigDecimal(val75);
                    stream.writeTimestamp(val76);
                    stream.writeTimestamp(val77);
                    stream.writeTimestamp(val78);
                    stream.writeTimestamp(val79);
                    stream.writeTimestamp(val80);
                    stream.writeTimestamp(val81);
                    stream.writeTimestamp(val82);
                    stream.writeTimestamp(val83);
                    stream.writeTimestamp(val84);
                    stream.writeTimestamp(val85);
                    stream.writeTimestamp(val86);
                    stream.writeTimestamp(val87);
                    stream.writeTimestamp(val88);
                    stream.writeTimestamp(val89);
                    stream.writeTimestamp(val90);
                    stream.writeTimestamp(val91);
                    stream.writeTimestamp(val92);
                    stream.writeTimestamp(val93);
                    stream.writeTimestamp(val94);
                    stream.writeTimestamp(val95);
                    stream.writeTimestamp(val96);
                    stream.writeTimestamp(val97);
                    stream.writeTimestamp(val98);
                    stream.writeTimestamp(val99);
                    stream.writeTimestamp(val100);

             }

             public String toString() {
                 return "CanonE001CodeTabValRec{" + "cdId="+cdId+", valId="+valId+", val1="+val1+", val2="+val2+", val3="+val3+", val4="+val4+", val5="+val5+", val6="+val6+", val7="+val7+", val8="+val8+", val9="+val9+", val10="+val10+", val11="+val11+", val12="+val12+", val13="+val13+", val14="+val14+", val15="+val15+", val16="+val16+", val17="+val17+", val18="+val18+", val19="+val19+", val20="+val20+", val21="+val21+", val22="+val22+", val23="+val23+", val24="+val24+", val25="+val25+", val26="+val26+", val27="+val27+", val28="+val28+", val29="+val29+", val30="+val30+", val31="+val31+", val32="+val32+", val33="+val33+", val34="+val34+", val35="+val35+", val36="+val36+", val37="+val37+", val38="+val38+", val39="+val39+", val40="+val40+", val41="+val41+", val42="+val42+", val43="+val43+", val44="+val44+", val45="+val45+", val46="+val46+", val47="+val47+", val48="+val48+", val49="+val49+", val50="+val50+", val51="+val51+", val52="+val52+", val53="+val53+", val54="+val54+", val55="+val55+", val56="+val56+", val57="+val57+", val58="+val58+", val59="+val59+", val60="+val60+", val61="+val61+", val62="+val62+", val63="+val63+", val64="+val64+", val65="+val65+", val66="+val66+", val67="+val67+", val68="+val68+", val69="+val69+", val70="+val70+", val71="+val71+", val72="+val72+", val73="+val73+", val74="+val74+", val75="+val75+", val76="+val76+", val77="+val77+", val78="+val78+", val79="+val79+", val80="+val80+", val81="+val81+", val82="+val82+", val83="+val83+", val84="+val84+", val85="+val85+", val86="+val86+", val87="+val87+", val88="+val88+", val89="+val89+", val90="+val90+", val91="+val91+", val92="+val92+", val93="+val93+", val94="+val94+", val95="+val95+", val96="+val96+", val97="+val97+", val98="+val98+", val99="+val99+", val100="+val100+'}';
             }
         }
      
        public static Object[] getUserList(String p_user_name,
        	    String p_first_name,
        	    String p_last_name,
        	    String p_order_by,
        	    String p_sort_order,
        	    BigDecimal p_page_no,
        	    BigDecimal p_records_per_page){
        	        clearException();
        	        System.out.println("in getUserList "+p_user_name+"|"+p_first_name+"|"+p_last_name+"|"+p_order_by+"|"+p_sort_order+"|"+p_page_no+"|"+p_records_per_page);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(GET_USER_LIST);
        	                if (statement != null) {
        	                    statement.setObject(1, p_user_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_first_name, OracleTypes.VARCHAR);
        	                    statement.setObject(3, p_last_name, OracleTypes.VARCHAR);
        	                    statement.setObject(4, p_order_by, OracleTypes.VARCHAR);
        	                    statement.setObject(5, p_sort_order, OracleTypes.VARCHAR);
        	                    statement.setObject(6, p_page_no, OracleTypes.NUMBER);
        	                    statement.setObject(7, p_records_per_page, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(8, OracleTypes.CURSOR);
        	                    statement.registerOutParameter(9, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(10, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(11, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    return new Object[]{
        	                        cursorToList((ResultSet)statement.getObject(8),UserInfo.getRowMapper())                   
        	                        ,statement.getObject(9)                   
        	                        ,statement.getObject(10)                   
        	                        ,statement.getObject(11)};
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
        {call CANON_E001_CODETABLE_UI_PKG.GET_USER_LIST(NULL,NULL,NULL,NULL,NULL,1,10,?,?,?,?)}
        (
        USER_NM VARCHAR2,
            FIRST_NM VARCHAR2,
            LAST_NM VARCHAR2
        )
        */

        public static class UserInfo {
           private String userNm;
               private String firstNm;
               private String lastNm;
            
            public UserInfo(){
            }
            public UserInfo(String userNm, 
                    String firstNm, 
                    String lastNm){
                this.userNm=userNm;
                this.firstNm=firstNm;
                this.lastNm=lastNm;

            }
            public String getUserNm() {
                return userNm;
            }
            public void setUserNm(String userNm) {
                this.userNm=userNm;
            }        public String getFirstNm() {
                return firstNm;
            }
            public void setFirstNm(String firstNm) {
                this.firstNm=firstNm;
            }        public String getLastNm() {
                return lastNm;
            }
            public void setLastNm(String lastNm) {
                this.lastNm=lastNm;
            }
            public static CanonRowMapper getRowMapper(){
                return new CanonRowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new UserInfo(
                            rs.getString("USR_NM"),
                            rs.getString("FIRST_NM"),
                            rs.getString("LAST_NM")
                        );
                    }
                };
            }
            public String toString() {
                return "UserInfo{" + "userNm="+userNm+", firstNm="+firstNm+", lastNm="+lastNm+'}';
            }
        }

        public static Object[] getRoleList(String p_role_name,
        	    String p_order_by,
        	    String p_sort_order,
        	    BigDecimal p_page_no,
        	    BigDecimal p_records_per_page){
        	        clearException();
        	        System.out.println("in getRoleList "+p_role_name+"|"+p_order_by+"|"+p_sort_order+"|"+p_page_no+"|"+p_records_per_page);
        	        CallableStatement statement = null;
        	        Connection connection = null;
        	        try {
        	            connection = TransactionScope.getConnection();
        	            if (connection != null) {
        	                statement = (CallableStatement) connection.prepareCall(GET_ROLE_LIST);
        	                if (statement != null) {
        	                    statement.setObject(1, p_role_name, OracleTypes.VARCHAR);
        	                    statement.setObject(2, p_order_by, OracleTypes.VARCHAR);
        	                    statement.setObject(3, p_sort_order, OracleTypes.VARCHAR);
        	                    statement.setObject(4, p_page_no, OracleTypes.NUMBER);
        	                    statement.setObject(5, p_records_per_page, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(6, OracleTypes.CURSOR);
        	                    statement.registerOutParameter(7, OracleTypes.NUMBER);
        	                    statement.registerOutParameter(8, OracleTypes.VARCHAR);
        	                    statement.registerOutParameter(9, OracleTypes.VARCHAR);

        	                    statement.execute();
        	                    return new Object[]{
        	                        cursorToList((ResultSet)statement.getObject(6),RoleInfo.getRowMapper())                   
        	                        ,statement.getObject(7)                   
        	                        ,statement.getObject(8)                   
        	                        ,statement.getObject(9)};
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
            {call CANON_E001_CODETABLE_UI_PKG.GET_ROLE_LIST(NULL,NULL,NULL,1,10,?,?,?,?)}
            (
            ROLE_NM VARCHAR2
            )
            */

            public static class RoleInfo {
               private String roleNm;
                
                public RoleInfo(){
                }
                public RoleInfo(String roleNm){
                    this.roleNm=roleNm;

                }
                public String getRoleNm() {
                    return roleNm;
                }
                public void setRoleNm(String roleNm) {
                    this.roleNm=roleNm;
                }
                public static CanonRowMapper getRowMapper(){
                    return new CanonRowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return new RoleInfo(
                                rs.getString("ROLE_NM")
                            );
                        }
                    };
                }
                public String toString() {
                    return "RoleInfo{" + "roleNm="+roleNm+'}';
                }
            }

        
}