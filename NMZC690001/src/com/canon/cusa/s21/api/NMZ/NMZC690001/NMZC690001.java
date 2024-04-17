/**<pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>*/
/**
 * <pre>
 * Call CANON_E404_SOM_CUSTOMER_PKG.get_customers from SOM
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/26/2016   SRAA            K.Aratani       Create          N/A
 * </pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC690001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import parts.dbcommon.EZDConnectionMgr;
import business.parts.NMZC690001PMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * Call S21_CSA_EXTN.CANON_E404_SOM_CUSTOMER_PKG.get_customers (Interface ID : NMAI1200)
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 09/06/2016   SRAA            K.Aratani       CREATE          NEW
 * 10/13/2016   SRAA            K.Aratani       Update          QC#15208
 * 01/18/2017   SRAA            K.Aratani       Update          QC#15539
 * 08/15/2017   SRAA            K.Aratani       Update          QC#20645
 *</pre>
 */
public class NMZC690001 extends S21ApiCommonBase {

    /** Selected Columns */
    private static final String PARTY_NAME = "party_name";
    private static final String CATEGORY_CODE = "category_code";
    private static final String PARTY_NUMBER = "party_number";
    private static final String PARTY_SITE_NUMBER = "party_site_number";
    private static final String ACCOUNT_NUMBER = "account_number";
    private static final String PARTY_ADDRESS = "party_address";
    private static final String WRITING_EMPLOYEE_NUMBER = "writing_employee_number";
    private static final String WRITING_REP_NAME = "writing_rep_name";
    private static final String WRITING_TERRITORY = "writing_territory";
    private static final String INSTALLING_REP_NAME = "installing_rep_name";
    private static final String INSTALLING_REP_NUMBER = "installing_rep_number";
    private static final String SFDC_NUMBER = "sfdc_number";
    private static final String SFDC_ID = "sfdc_id";
    private static final String LEGAL_NM = "legal_name";
    private static final String DBA_NAME = "dba_name";
    private static final String LOGGED_IN_USER = "logged_in_user";
    private static final String CSMP_LEVEL = "csmp_level";
    private static final String CSMP_START_DATE = "csmp_start_date";
    private static final String CSMP_END_DATE = "csmp_end_date";
    private static final String MAJOR_ACCT_FLAG = "major_acct_flag";
    private static final String DEPTCODE = "deptCode";
    private static final String BID_PRICING = "bid_pricing";
    private static final String DUNS_NUMBER = "duns_number";  //QC#20645
    private static final String PHONE = "phone";  //QC#20645


    /**
     * @param
     * @return
     */
    public NMZC690001() {
        super();
    }

    /**
     * execute.
     * @param param NMZC690001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NMZC690001PMsg param, final ONBATCH_TYPE onBatchType) {

        final List<NMZC690001PMsg> params = new ArrayList<NMZC690001PMsg>();
        params.add(param);

        this.execute(params, onBatchType);
    }

    /**
     * execute.
     * @param params List<NMZC690001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NMZC690001PMsg> params, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "execute : params.size=[" + params.size() + "]";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            if (isEmpty(params)) {
                return;
            }

            for (NMZC690001PMsg param : params) {
                final S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
                if (!checkInput(msgMap)) {
                    msgMap.flush();
                    return;
                }
                Connection connection = null;
                CallableStatement statement = null;
                try {
                    connection = EZDConnectionMgr.getInstance().getConnection();
                    String sql = ("{call CANON_E404_SOM_CUSTOMER_PKG.get_customers(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    statement = connection.prepareCall(sql);
                    statement.setString(1, param.psnNum.getValue());
                    statement.setString(2, param.xxExtnPtyNm.getValue());
                    statement.setString(3, param.xxExtnStrAddr.getValue());
                    statement.setString(4, param.xxExtnCtyAddr.getValue());
                    statement.setString(5, param.xxExtnStAddr.getValue());
                    statement.setString(6, param.xxExtnPostCd.getValue());
                    statement.setString(7, param.xxExtnIndMngCd.getValue());
                    statement.setString(8, param.xxExtnIndSpclCd.getValue());
                    statement.setString(9, param.xxExtnFmDeptNm.getValue());
                    statement.setString(10, param.xxExtnSortOrdTxt.getValue());
                    statement.setString(11, param.xxExtnSortFldTxt.getValue());
                    statement.setString(12, param.xxExtnPtyNum.getValue());
                    statement.setString(13, param.xxExtnPtySiteNum.getValue());
                    statement.registerOutParameter(14,OracleTypes.CURSOR);
                    statement.executeUpdate(); 
                    ResultSet resultSet = (ResultSet) statement.getObject(14);
                    
                    int cnt = 0;
                    param.xxCustList.setValidCount(0);
                    while(resultSet.next()){
                        param.xxCustList.setValidCount(cnt+1);
                        debugPrint(resultSet);
                        //get cursor Data
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnPtyNm, resultSet.getString(PARTY_NAME));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnCatgCd, resultSet.getString(CATEGORY_CODE));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnPtyNum, resultSet.getString(PARTY_NUMBER));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnPtySiteNum, resultSet.getString(PARTY_SITE_NUMBER));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnAcctNum, resultSet.getString(ACCOUNT_NUMBER));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnPtyAddr, resultSet.getString(PARTY_ADDRESS));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnWriteEmpNum, resultSet.getString(WRITING_EMPLOYEE_NUMBER));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnWriteRepNm, resultSet.getString(WRITING_REP_NAME));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnWriteTrtyNm, resultSet.getString(WRITING_TERRITORY));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnIstlRepNm, resultSet.getString(INSTALLING_REP_NAME));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnIstlRepNum, resultSet.getString(INSTALLING_REP_NUMBER));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnCrmSlsNum, resultSet.getString(SFDC_NUMBER));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnCrmSlsId, resultSet.getString(SFDC_ID));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnLegalNm, resultSet.getString(LEGAL_NM));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnDbaNm, resultSet.getString(DBA_NAME));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnLogInUsrNm, resultSet.getString(LOGGED_IN_USER));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnCsmpLvlNm, resultSet.getString(CSMP_LEVEL));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnCsmpStartTxt, resultSet.getString(CSMP_START_DATE));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnCsmpEndTxt, resultSet.getString(CSMP_END_DATE));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnMajIndAcctCd, resultSet.getString(MAJOR_ACCT_FLAG));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnDeptCd, resultSet.getString(DEPTCODE));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnBidPrcNm, resultSet.getString(BID_PRICING));
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnDunsNum, resultSet.getString(DUNS_NUMBER));  //QC#20645
                        ZYPEZDItemValueSetter.setValue(param.xxCustList.no(cnt).xxExtnCustTelNum, resultSet.getString(PHONE));  //QC#20645
                        
                        cnt++;
                    } 
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                    msgMap.addXxMsgId("NWZM2035E");
                    msgMap.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    msgMap.addXxMsgId("NWZM2036E");
                    msgMap.flush();
                } finally {
                    closeResource(connection, statement, null);
                    msgMap.flush();
                }
            }
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private static boolean checkInput(S21ApiMessageMap msgMap) {

        NMZC690001PMsg inMsg = (NMZC690001PMsg) msgMap.getPmsg();

        if (!hasValue(inMsg.glblCmpyCd)) {
            //NWZM0001E=0,Data Company Code is not entered. 
            msgMap.addXxMsgId("NWZM0001E");
            return false;
        }

        if (!hasValue(inMsg.psnNum)) {
            //NWZM2034E=0,Party Number is not entered. 
            msgMap.addXxMsgId("NWZM2034E");
            return false;
        }

        return true;
    }
    
    private void closeResource(Connection c, Statement s, ResultSet r) {

        if (r != null) {
            try {
                r.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (s != null) {
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLogEnd(Class clazz, String methodNm) {
        // DefID:1434 Performance Log CommentOut
        // final StringBuilder sb = new StringBuilder();
        // sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [E N D] ").append(System.currentTimeMillis());
        // System.out.println(sb.toString());
    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLogStart(Class clazz, String methodNm) {
        // DefID:1434 Performance Log CommentOut
        // final StringBuilder sb = new StringBuilder();
        // sb.append("#Performance Profiling# [").append(clazz.getSimpleName()).append("] : ").append(methodNm).append(" - [START] ").append(System.currentTimeMillis());
        // System.out.println(sb.toString());
    }
    
    private static boolean isEmpty(List<NMZC690001PMsg> list) {

        return list == null || list.isEmpty();
    }
    
    private static void debugPrint(ResultSet resultSet) throws SQLException {
        System.out.println("###############################################################");
        System.out.println(resultSet.getString(PARTY_NAME));
        System.out.println(resultSet.getString(CATEGORY_CODE));
        System.out.println(resultSet.getString(PARTY_NUMBER));
        System.out.println(resultSet.getString(PARTY_SITE_NUMBER));
        System.out.println(resultSet.getString(ACCOUNT_NUMBER));
        System.out.println(resultSet.getString(PARTY_ADDRESS));
        System.out.println(resultSet.getString(WRITING_EMPLOYEE_NUMBER));
        System.out.println(resultSet.getString(WRITING_REP_NAME));
        System.out.println(resultSet.getString(WRITING_TERRITORY));
        System.out.println(resultSet.getString(INSTALLING_REP_NAME));
        System.out.println(resultSet.getString(INSTALLING_REP_NUMBER));
        System.out.println(resultSet.getString(SFDC_NUMBER));
        System.out.println(resultSet.getString(SFDC_ID));
        System.out.println(resultSet.getString(LEGAL_NM));
        System.out.println(resultSet.getString(DBA_NAME));
        System.out.println(resultSet.getString(LOGGED_IN_USER));
        System.out.println(resultSet.getString(CSMP_LEVEL));
        System.out.println(resultSet.getString(CSMP_START_DATE));
        System.out.println(resultSet.getString(CSMP_END_DATE));
        System.out.println(resultSet.getString(MAJOR_ACCT_FLAG));
        System.out.println(resultSet.getString(DEPTCODE));
        System.out.println(resultSet.getString(BID_PRICING));
        System.out.println(resultSet.getString(DUNS_NUMBER));  //QC#20645
        System.out.println(resultSet.getString(PHONE));  //QC#20645
    }

}
