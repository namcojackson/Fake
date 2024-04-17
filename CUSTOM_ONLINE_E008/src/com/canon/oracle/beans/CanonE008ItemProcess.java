/*
 * CanonE590ItemProcess.java
 *
 * Madhusudan Duna
 */
package com.canon.oracle.beans;

import oracle.apps.jtf.aom.transaction.TransactionScope;

import java.sql.*;
import java.util.*;
  
public class CanonE008ItemProcess { 

    public CanonE008ItemProcess() {
    }
        
    private void logErrorMessage(String message) {
        System.err.println(message);
    }   
    
    private void logMessage(String message) {
        System.out.println(message);     
    }
    
    private void closeResources(ResultSet resultSet) {   
        if (resultSet != null) { 
            try {     
                resultSet.close();
            } catch (Exception exception) {  
                logErrorMessage("Unable to close result set:  " + exception);
            }  
        }       
    }       
                
    public void closeResources(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception exception) {
                logErrorMessage("Unable to close statement:  " + exception);
            }
        }
    }
     
 
    public ArrayList getProjectDetails(String projectNo, String projectName, String projCat, String projDesc,String masterProj, String submitter,String fromdate,String todate, String status,String itemNo) throws Exception {
        StringBuffer qry = new StringBuffer(20);
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        ArrayList list = new ArrayList();

        logMessage("@@@ getProjects"); 
        
        qry.append(" SELECT project_number,project_name,PROJECT_TYPE,PROJECT_DESC,MASTER_PROJECT,(select FIRST_NM || ' ' ||LAST_NM from AUTH_PSN where usr_NM = cpt.PROJECT_REQUESTOR and ROWNUM =1) PROJECT_REQUESTOR,approval_status,creation_date,(select count(1) from canon_e008_proj_items_tbl where project_id = project_number) count,process_flag \n");
        qry.append(" FROM canon_e008_project_wb_tbl cpt  WHERE 1 = 1 \n");
       /* qry.append(" SELECT project_number,project_name,UPPER (ppf.psn_first_nm || ' ' || ppf.psn_last_nm),approval_status,process_flag \n");
        qry.append(" FROM canon_e590_project_ext_tbl cpt, s21_psn ppf  WHERE     1 = 1  AND cpt.project_submitter = ppf.psn_cd \n");*/
        //qry.append(" AND approval_status <> 'CLOSED' \n");
       
  
        if ((projectNo != null) && !("".equals(projectNo))) {
            qry.append("  AND PROJECT_NUMBER LIKE  '%");
            qry.append(projectNo);
            qry.append("%'\n");
        }   
        if ((projectName != null) && !("".equals(projectName))) { 
            qry.append("  AND UPPER(PROJECT_NAME) LIKE  UPPER('%");
            qry.append(projectName);
            qry.append("%') \n");
        }

        if (!("00".equals(projCat)) && (projCat != null) && !("".equals(projCat))) {
            qry.append("  AND PROJECT_TYPE = '");
            qry.append(projCat);
            qry.append("' \n");
        } 

        if (!("00".equals(masterProj)) && (masterProj != null) && !("".equals(masterProj))) {
            qry.append("  AND MASTER_PROJECT = '");
            qry.append(masterProj);
            qry.append("' \n");
        } 

        if ((projDesc != null) && !("".equals(projDesc))) {
            qry.append("  AND UPPER(PROJECT_DESC) like UPPER('%");
            qry.append(projDesc);
            qry.append("%') \n");
        } 
        
        if ("OPEN".equals(status)) {  
            qry.append("  AND APPROVAL_STATUS NOT IN ( 'CLOSED','FAILED') \n");
        }else if ((status != null) && !("".equals(status)) && !("ALL".equals(status))) {
            qry.append("  AND APPROVAL_STATUS = DECODE( '");
            qry.append(status);
            qry.append("','PENDING APPROVAL ACCOUNTING','PENDING APPROVAL','PENDING APPROVAL INCENTIVE COMP','PENDING APPROVAL','PENDING APPROVAL SERVICE','PENDING APPROVAL','PENDING APPROVAL SUPPLIES','PENDING APPROVAL', 'PENDING APPROVAL SOLUTIONS','PENDING APPROVAL','PENDING APPROVAL MARKETING','PENDING APPROVAL','PENDING APPROVAL PRICING','PENDING APPROVAL', '");
			qry.append(status);
			qry.append("') \n");  
        }
		
		if (("PENDING APPROVAL ACCOUNTING".equals(status)) ||("PENDING APPROVAL INCENTIVE COMP".equals(status)) ||
				("PENDING APPROVAL MARKETING".equals(status)) || ("PENDING APPROVAL PRICING".equals(status)) ||
					("PENDING APPROVAL SERVICE".equals(status)) || ("PENDING APPROVAL SOLUTIONS".equals(status)) ||
						("PENDING APPROVAL SUPPLIES".equals(status))) {
			qry.append("  AND EXISTS ( SELECT 1 FROM CANON_E008_PROJ_APPRV_HIST_TBL WHERE process_id = CPT.process_id ");
			qry.append("  AND   APPROVING_DEPARTMENT   = DECODE( '");
            qry.append(status);
			qry.append("','PENDING APPROVAL ACCOUNTING','ITEM MASTER - ACCOUNTING',"
					+ "'PENDING APPROVAL INCENTIVE COMP','ITEM MASTER - SALES COMP',"
					+ "'PENDING APPROVAL MARKETING','ITEM MASTER - MARKETING',"
					+ "'PENDING APPROVAL PRICING','ITEM MASTER - PRICING',"
					+ "'PENDING APPROVAL SERVICE','ITEM MASTER - SERVICE',"
					+ "'PENDING APPROVAL SOLUTIONS','ITEM MASTER - SOLUTIONS',"
					+ "'PENDING APPROVAL SUPPLIES','ITEM MASTER - SUPPLIES' ) AND APPROVAL_STATUS = 'PENDING') \n");
		}
		
		
		/*  
       if ((submitter != null) && !("".equals(submitter))) {
            qry.append("  AND PROJECT_REQUESTOR like '%");
            qry.append(submitter);
            qry.append("%' \n");
        } */
		
        if ((itemNo != null) && !("".equals(itemNo))) {
            qry.append("  AND  PROJECT_NUMBER IN \n");
            qry.append("  (SELECT  PROJECT_ID FROM CANON_E008_PROJ_ITEMS_TBL where \n");
            qry.append("  UPPER(item_number) like UPPER('");
            qry.append(itemNo);
            qry.append("') \n");
            qry.append("  and PROJECT_ID = CPT.project_number ) \n");
        }
        
		if ((submitter != null) && !("".equals(submitter))) {
    	   qry.append("  AND ( PROJECT_REQUESTOR IN (");
           qry.append("  select usr_NM from AUTH_PSN where UPPER(FIRST_NM) like UPPER('%");
           qry.append(submitter);
           qry.append("%')) \n");
       } 

       if ((submitter != null) && !("".equals(submitter))) {
    	   qry.append("  OR PROJECT_REQUESTOR IN (");
           qry.append("  select usr_NM from AUTH_PSN where UPPER(LAST_NM) like UPPER('%");
           qry.append(submitter);
           qry.append("%')) ) \n");
       } 
       
       
       //if ((fromdate != null) && !("".equals(fromdate)) && (todate != null) && !("".equals(todate))) {
       if (((fromdate != null) && !("".equals(fromdate))) || ((todate != null) && !("".equals(todate)) )) {
           qry.append("  AND CREATION_DATE BETWEEN '");
           qry.append(fromdate);
           qry.append("' AND NVL('");
           qry.append(todate);
           qry.append("',SYSDATE+1) \n");
       } 

        qry.append("    Order by PROJECT_NUMBER desc  \n");

        logMessage(qry.toString());

        try {
        	connection = TransactionScope.getConnection();
            if (connection != null) {
                statement = connection.createStatement();
                System.out.println("Statemment");
                if (statement != null) {
                    resultSet = statement.executeQuery(qry.toString());
                    while (resultSet.next()) {
                        CanonE008AttributeBean itemObj = new CanonE008AttributeBean();
                        itemObj.setAttribute1(resultSet.getString(1));
                        itemObj.setAttribute2(resultSet.getString(2));
                        itemObj.setAttribute3(resultSet.getString(3));
                        itemObj.setAttribute4(resultSet.getString(4));
                        itemObj.setAttribute5(resultSet.getString(5));
                        itemObj.setAttribute6(resultSet.getString(6));
                        itemObj.setAttribute7(resultSet.getString(7));
                        itemObj.setAttribute8(resultSet.getString(8));
                        itemObj.setAttribute9(resultSet.getString(9));
                        itemObj.setAttribute10(resultSet.getString(10));

                        list.add(itemObj);
                    }//while ends
                } else {
                    logErrorMessage("dbStatus: DBStatus.UNABLE_TO_CREATE_STATEMENT");
                }
            } else {
                logErrorMessage("dbStatus: UNABLE_TO_GET_CONNECTION");
            }
        } catch (SQLException sqlExp) {
        	sqlExp.printStackTrace();
            logErrorMessage("SQLexception: " + sqlExp);
            throw sqlExp;
        } catch (Exception exception) {
        	exception.printStackTrace();
        	logErrorMessage("exception: " + exception);
        } finally {
            closeResources(resultSet);
            closeResources(statement);
            try {
                if (connection != null) {
                    TransactionScope.releaseConnection(connection);
                }
            } catch (Exception eExp) {
            }
        }
        return list;
    }

    private String dbToJavaName(String colName) {
        colName = getColumnLabel(colName);
        return colName;
    }

    private String getColumnLabel(String attrName) {
        String val = attrName.replaceAll("_", " ");
        return initCap(val);
    }

    private String initCap(String in) {
        boolean capitalize = true;
        char[] data = in.toCharArray();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == ' ' || Character.isWhitespace(data[i])) {
                capitalize = true;
            } else if (capitalize) {
                data[i] = Character.toUpperCase(data[i]);
                capitalize = false;
            } else {
                data[i] = Character.toLowerCase(data[i]);
            }
        }
        return new String(data);
    }
}