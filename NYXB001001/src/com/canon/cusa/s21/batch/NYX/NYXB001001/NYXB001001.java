/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NYX.NYXB001001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;

import business.db.INTFC_DT_MGTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * GP310 Open Order
 * 
 * Parameters
 *  INTERFACE_ID : Interface ID
 *  USER_VAR1    : Date Management Program ID.
 *
 * Date          Company     Name        Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/16/2010    Fujitsu     M.Yamada    Create          RQ1317
 *</pre>
 */
public class NYXB001001 extends S21BatchMain {

    /** Log Level */
    private static final int LOG_LVL = 1;

    /** S21UserProfileService */
    private S21UserProfileService profile;

    /** SQL Accessor */
    private S21SsmBatchClient ssmClient;

    /** Interface ID List */
    ArrayList<String> intfcIdList;

    // Table ID
    /** DT_MGT */
    private static final String TBL_ID_DT_MGT = "DT_MGT";

    /** INTFC_DT_MGT */
    private static final String TBL_ID_INTFC_DT_MGT = "INTFC_DT_MGT";

    // Colmn Name
    /** Management Interface Date */
    private static final String COL_NM_MNG_INTFC_DT = "MNG_INTFC_DT";

    // Message ID
    /** NLCM0050E DB Access Error. Table[@], Command[@], Key[@] */
    private static final String MSG_ID_NLCM0050E = "NLCM0050E";

    /** ZZBM0009I The (@) was (@) . ResultCount = (@) */
    private static final String MSG_ID_ZZBM0009I = "ZZBM0009I";

    /** ZZM9000E [@] field is mandatory. */
    private static final String MSG_ID_ZZM9000E = "ZZM9000E";

    //
    /** SQL Map Statement ID : getMgtDt */
    private static final String SQL_STID_GET_MGT_DT = "getMgtDt";

    // SQL Map bind key
    /** GLBL_CMPY_CD */
    private static final String SQL_PRM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DT_MGT_PGM_ID */
    private static final String SQL_PRM_DT_MGT_PGM_ID = "dtMgtPgmId";

    /** DT_PROC_CD_BATCH */
    private static final String SQL_PRM_DT_PROC_CD_BATCH = "dtProcCdBatch";

    /** DT_MGT_PGM_ID_AST */
    private static final String SQL_PRM_DT_MGT_PGM_ID_AST = "dtMgtPgmIdAst";

    // Message Strings
    /** Global Company Code */
    private static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Interface ID */
    private static final String MSG_STR_INTERFACE_ID = "Interface ID";

    /** User Variable1(Program ID) */
    private static final String MSG_STR_USER_VAR1 = "User Variable1(Program ID)";

    //
    /** Asterisk */
    private static final String ASTERISK = "*";

    /** Process Code : Batch */
    private static final String PROC_CD_BATCH = "B";

    /** Delimiter : Comma */
    private static final String DELIMITER_COMMA = ",";

    // Return Code
    /** Normal */
    private static final String RETURN_CODE_NORMAL = "0000";

    // Counter
    /** The number of cases : Select */
    private int selectCount;

    /** The number of cases : Insert */
    private int insertCount;

    /** The number of cases : Update */
    private int updateCount;

    /** The number of cases : Error */
    private int errorCount;

    /** Termination code */
    private TERM_CD termCd;

    /*
     * (non-Javadoc)
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#initRoutine()
     */
    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmClient = S21SsmBatchClient.getClient(getClass());
        // Initialization of variable
        selectCount = 0;
        insertCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profile = S21UserProfileServiceFactory.getInstance().getService();

        // Check on input parameter
        checkParameter();
        intfcIdList = new ArrayList<String>();
        for (Object intfcId : S21StringUtil.stringToList(getInterfaceID(), DELIMITER_COMMA)) {
            intfcIdList.add((String) intfcId);
        }

    }

    /*
     * (non-Javadoc)
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#mainRoutine()
     */
    @Override
    protected void mainRoutine() {

        // Extraction and registration of interface data
        doProcess();

    }

    /*
     * (non-Javadoc)
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#termRoutine()
     */
    @Override
    protected void termRoutine() {

        // The number of cases : Select is output
        String[] tmp1 = {TBL_ID_DT_MGT, "Read", Integer.toString(selectCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp1);
        // The number of cases : Number Inserted(INTFC_MNG_DT)
        String[] tmp2 = {TBL_ID_INTFC_DT_MGT, "Inserted", Integer.toString(insertCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp2);
        // The number of cases : Number Inserted(INTFC_MNG_DT)
        String[] tmp3 = {TBL_ID_INTFC_DT_MGT, "Updated", Integer.toString(updateCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp3);

        // Setting of termination code
        setTermState(termCd, selectCount, errorCount, insertCount + updateCount);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Initialization of S21BatchMain
        new NYXB001001().executeBatch(NYXB001001.class.getSimpleName());

    }

    /**
     * Check processing of input parameter
     */
    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(getGlobalCompanyCode())) {
            // When the Global Company Code is not obtained,
            // processing is ended
            String[] tmp = {MSG_STR_COMP_CODE };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }
        if (!ZYPCommonFunc.hasValue(getInterfaceID())) {
            // When the Interface ID is not obtained,
            // processing is ended
            String[] tmp = {MSG_STR_INTERFACE_ID };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }
        if (!ZYPCommonFunc.hasValue(getUserVariable1())) {
            // When the Program ID is not obtained,
            // processing is ended
            String[] tmp = {MSG_STR_USER_VAR1 };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }

    }

    /**
     * Extraction and registration of interface data
     */
    private void doProcess() {

        // Extractive process of interface data
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(SQL_PRM_GLBL_CMPY_CD, profile.getGlobalCompanyCode());

        queryParam.put(SQL_PRM_DT_MGT_PGM_ID, getUserVariable1());
        queryParam.put(SQL_PRM_DT_MGT_PGM_ID_AST, ASTERISK);

        queryParam.put(SQL_PRM_DT_PROC_CD_BATCH, PROC_CD_BATCH);

        // Registration processing of interface data
        ssmClient.queryObject(SQL_STID_GET_MGT_DT, queryParam, new UpsertIntfcDtMgt());
        commit();
        EZDDebugOutput.println(LOG_LVL, "Commit Last" + (insertCount + updateCount), this);

    }

    /**
     * Registration class of Interface Date Management table
     */
    private class UpsertIntfcDtMgt extends S21SsmVoidResultSetHandlerSupport {

        /**
         * Registration processing of interface data
         * @param rs ResultSet
         * @throws SQLException
         */
        protected void doProcessQueryResult(final ResultSet rs) throws SQLException {

            if (rs.next()) {

                selectCount++;
                for (String intfcId : intfcIdList) {

                    INTFC_DT_MGTTMsg tmsg = new INTFC_DT_MGTTMsg();
                    INTFC_DT_MGTTMsg tmsgKey = new INTFC_DT_MGTTMsg();

                    ZYPEZDItemValueSetter.setValue(tmsgKey.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tmsgKey.mngIntfcId, intfcId);

                    tmsg = (INTFC_DT_MGTTMsg) EZDTBLAccessor.findByKeyForUpdate(tmsgKey);
                    if (tmsg == null) {
                        insertRecord(rs, tmsgKey);
                    } else {
                        updateRecord(rs, tmsg);
                    }

                }
            } else {
                String[] tmp = {TBL_ID_INTFC_DT_MGT, "Select", "" };
                throw new S21AbendException(MSG_ID_NLCM0050E, tmp);
            }
            return;
        }
    }

    /**
     * Registration processing of interface date management
     * @param rs ResultSet
     * @param tmsg INTFC_DT_MGT
     * @throws SQLException
     */
    private void insertRecord(final ResultSet rs, INTFC_DT_MGTTMsg tmsg) throws SQLException {

        ZYPEZDItemValueSetter.setValue(tmsg.mngIntfcDt, rs.getString(COL_NM_MNG_INTFC_DT));

        EZDTBLAccessor.create(tmsg);
        String returnCode = tmsg.getReturnCode();
        if (RETURN_CODE_NORMAL.equals(returnCode)) {
            insertCount++;

        } else {
            String[] tmp = {TBL_ID_INTFC_DT_MGT, "Insert", tmsg.toString() };
            throw new S21AbendException(MSG_ID_NLCM0050E, tmp);
        }

        return;
    }

    /**
     * Update processing of interface date management
     * @param rs ResultSet
     * @param tmsg INTFC_DT_MGT
     * @throws SQLException
     */
    private void updateRecord(final ResultSet rs, INTFC_DT_MGTTMsg tmsg) throws SQLException {

        ZYPEZDItemValueSetter.setValue(tmsg.mngIntfcDt, rs.getString(COL_NM_MNG_INTFC_DT));

        EZDTBLAccessor.update(tmsg);
        String returnCode = tmsg.getReturnCode();
        if (RETURN_CODE_NORMAL.equals(returnCode)) {
            updateCount++;

        } else {
            String[] tmp = {TBL_ID_INTFC_DT_MGT, "Insert", tmsg.toString() };
            throw new S21AbendException(MSG_ID_NLCM0050E, tmp);
        }

        return;
    }
}
