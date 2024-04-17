/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB002001;

import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.ADJUSTMENT_SIGN;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.ADJUST_SRC_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.API_ID_NLZC0630;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.API_ID_NLZC4030;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_CODE_ITEM_IF;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_CODE_STAT_STOCK_IF;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_CONFIG_MSTR_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_FIRST_PROD_CTRL_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_INVTY_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_PHYS_INVTY_ADJ_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_PHYS_INVTY_CTRL_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_PHYS_INVTY_CTRL_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_PHYS_INVTY_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_PHYS_INVTY_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_QTY_ADJUST_IF;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_RCV_SER_TAKE_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_SEGMENT_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_SEQ_NUMBER;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_SER_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_SIGN_IF;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_UNIT_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_NM_WMS_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.COL_STK_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.CREATE_MODE;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.DATE_TIME_PATTERN_FOR_MAIL;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.INTERFACE_ID_NLCI0330;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.KEY_INFO_DELIM;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.LINE_SEPARATOR_KEY_FOR_MAIL;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MAIL_KEY1_VALUE_TO;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MAIL_TEMPLATE_ID_NO_APPROVE;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MAIL_TEMPLATE_PARAM_KEY_BATCHID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MAIL_TEMPLATE_PARAM_KEY_ERRDATE;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MAIL_TEMPLATE_PARAM_KEY_MESSAGE;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MAIL_TEMPLATE_PARAM_KEY_TIMESTAMP;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_NLCM0047E;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_NLCM0049E;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_NLCM0063E;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_NLCM0148E;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_NLCM0181E;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_NLCM0186W;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_NLGM0007E;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_NLGM0008E;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_ZZBM0009I;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_ID_ZZM9000E;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_STR_COMP_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.MSG_STR_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_PRM_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_PRM_CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_PRM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_PRM_INTERFACE_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_PRM_LOC_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_PRM_PHYS_INVTY_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_PRM_TRANSACTION_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_STID_GET_ADJUSTMENT;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_STID_GET_CONFIG_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SQL_STID_GET_PHYS_INVTY_CTRL_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.STR_MINUS;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.STR_PLUS;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.SYSTEM_USER;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.TBL_ID_MDSE;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.TBL_ID_NLCI0330_01;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.TBL_ID_PHYS_INVTY_AJT;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.TBL_ID_PHYS_INVTY_CNT_DTL;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.TBL_ID_PHYS_INVTY_CNT_HDR;
import static com.canon.cusa.s21.batch.NLC.NLCB002001.constant.NLCB002001Constant.TBL_ID_PHYS_INVTY_CTRL;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PHYS_INVTY_CNT_DTLTMsg;
import business.db.PHYS_INVTY_CNT_HDRTMsg;
import business.db.PHYS_INVTY_CTRLTMsg;
import business.parts.NLZC061001PMsg;
import business.parts.NLZC063001PMsg;
import business.parts.NLZC403001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC061001.NLZC061001;
import com.canon.cusa.s21.api.NLZ.NLZC063001.NLZC063001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.NLZC403001;
import com.canon.cusa.s21.api.NLZ.NLZC403001.constant.NLZC403001Constant;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmVoidResultSetHandlerSupport;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * 1. Difference information of annual cycle count is received from the IDS system. 
 * 2. The Adjustment Order is made based on it.
 * 
 * note.
 * If you exec this program at local PC,
 * you will add following jar.
 *      antlr.jar
 *      asm.jar
 *      bsh.jar
 *      cglib.jar
 *      commons-collections.jar
 *      commons-logging.jar
 *      dom4j.jar
 *      hibernate.jar
 *      jbpm-jpdl.jar
 *
 * Date          Company     Name        Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/30/2009    Fujitsu     Y.Fukiage   Create          N/A
 * 03/13/2010    Fujitsu     M.Yamada    Update          4602
 * 04/29/2010    Fujitsu     M.Yamada    Update          6155
 * 06/10/2010    Fujitsu     M.Yamada    Update          E1266
 * 05/25/2011    CSAI        M.Takahashi Update          2263(PROD)
 * 03/11/2016    CITS        N.Akaishi   Update          V1.0
 * 06/30/2016    CITS        M.Okigami   Update          7176
 * 08/29/2016    CITS        M.Okigami   Update          13208
 * 11/11/2016    CITS        S.Endo      Update          QC#14479
 * 11/14/2016    CITS        S.Endo      Update          QC#14598/QC#15058
 * 11/15/2016    CITS        S.Endo      Update          QC#14479
 * 11/16/2016    CITS        S.Endo      Update          QC#14479
 * 11/18/2016    CITS        S.Endo      Update          QC#15058
 * 12/27/2016    CITS        S.Endo      Update          QC#15340
 * 03/15/2018    CITS        K.Ogino     Update          QC#24449
 * 06/11/2018    CITS        K.Ogino     Update          QC#26529
 * 08/17/2018    CITS        T.hakodate  UPDATE          QC#27709
 * 05/22/2019    Fujitsu     T.Ogura     Update          QC#50055
 * </pre>
 */
public class NLCB002001 extends S21BatchMain {

    /** Transaction ID */
    private BigDecimal transactionId;
    
    /** Transaction IDs */
    private BigDecimal[] transactionIds;

    /** The number of cases : Select */
    private int selectCount;

    /** The number of cases : Normal Count (NLCI0330_01) */
    private int normalCount;

    /** The number of cases : PHYS_INVTY_ADJ */
    private int insertPhysInvtyAdjCount;

    /** The number of cases : PHYS_INVTY_CNT_HDR */
    private int insertPhysInvtyCntHdrCount;

    /** The number of cases : PHYS_INVTY_CTRL */
    private int updatePhysInvtyCtrlCount;

    /** The number of cases : Skip */
    private int skipCount;

    /** Message of error mail */
    private Map<String, List<String>> mailMapData;

    /** S21UserProfileService */
    private S21UserProfileService profile;

    /** SQL Accessor */
    private S21SsmBatchClient ssmClient;

    /** Transaction table accessor */
    private S21TransactionTableAccessor tranAccess;

    /** Termination code */
    private TERM_CD termCd;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Proc Date */
    private String batProcDate;

    /** The number of cases : Error */
    private int errorCount;

    /**
     * Main
     * @param args argument
     */
    public static void main(String[] args) {

        // Initialization of S21WfBatchMain
        new NLCB002001().executeBatch(NLCB002001.class.getSimpleName());

    }

    /**
     * Initialize process
     */
    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmClient = S21SsmBatchClient.getClient(getClass());
        // Initialization of variable
        selectCount = 0;
        normalCount = 0;
        insertPhysInvtyAdjCount = 0;
        insertPhysInvtyCntHdrCount = 0;
        updatePhysInvtyCtrlCount = 0;
        skipCount = 0;
        mailMapData = new HashMap<String, List<String>>();
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profile = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = profile.getGlobalCompanyCode();
        // Check on input parameter
        checkParameter();

        errorCount = 0;
    }

    /**
     * Main process
     */
    @Override
    protected void mainRoutine() {

        // The Transaction ID is obtained
        tranAccess = new S21TransactionTableAccessor();
        transactionIds = tranAccess.getIntegrationRecord(getInterfaceID());

        for (BigDecimal transactionId : transactionIds) {

            if (!ZYPCommonFunc.hasValue(transactionId)) {
                return;
            }

            // START 2019/05/22 T.Ogura [QC#50055,ADD]
            BigDecimal physInvtyCtrlCancelCnt = getPhysInvtyCtrlCancelCnt(transactionId);

            if (physInvtyCtrlCancelCnt.compareTo((BigDecimal.ZERO)) > 0) {
                // The transaction management table is renewed to
                // "Processed it".
                tranAccess.endIntegrationProcess(getInterfaceID(), transactionId);
                commit();
                continue;
            }
            // END   2019/05/22 T.Ogura [QC#50055,ADD]

            this.transactionId = transactionId;    // 2019/05/22 T.Ogura [QC#50055,ADD]

            // Get operation date.
            batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);

            // Create Dummy Header and Detail
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(SQL_PRM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(SQL_PRM_INTERFACE_ID, "NLCI0330");
            queryParam.put(SQL_PRM_TRANSACTION_ID, transactionId);
            queryParam.put(SQL_PRM_LOC_STS_CD, LOC_STS.DC_STOCK);
            ssmClient.queryObject("getAdjustmentPhysInvtyNum", queryParam, new CreateAdjustmentDuumyOrder());
            
            // Extraction of Adjustment data and
            // making of Adjustment Order
            queryParam = new HashMap<String, Object>();
            queryParam.put(SQL_PRM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(SQL_PRM_INTERFACE_ID, getInterfaceID());
            queryParam.put(SQL_PRM_TRANSACTION_ID, transactionId);
            queryParam.put(SQL_PRM_LOC_STS_CD, LOC_STS.DC_STOCK);
            ssmClient.queryObject(SQL_STID_GET_ADJUSTMENT, queryParam, new CreateAdjustmentOrder());

            if (errorCount == 0) {
                // The transaction management table is renewed to
                // "Processed it".
                tranAccess.endIntegrationProcess(getInterfaceID(), transactionId);
                commit();
            } else {
                rollback();
            }

            if (!mailMapData.isEmpty()) {
                // Mail Sending processing for error data
                sendMailForErrorData();
                commit();
            }
        }

    }

    /**
     * getPhysInvtyCtrlCancel
     * @param transactionId BigDecimal
     * @return BigDecimal
     */
    private BigDecimal getPhysInvtyCtrlCancelCnt(BigDecimal transactionId) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("interfaceId", getInterfaceID());
        ssmParam.put("transactionId", transactionId.toPlainString());
        ssmParam.put("physInvtyStsCd_Cancel", PHYS_INVTY_STS.CANCELLED);

        return (BigDecimal) ssmClient.queryObject("getPhysInvtyCtrlCancelCnt", ssmParam);
    }

    /**
     * Termination process
     */
    @Override
    protected void termRoutine() {

        // The number of cases : Select is output
        String[] tmp1 = {TBL_ID_NLCI0330_01, "Read", Integer.toString(selectCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp1);

        String[] tmp2 = {TBL_ID_PHYS_INVTY_CNT_HDR, "Regist", Integer.toString(insertPhysInvtyCntHdrCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp2);
        String[] tmp3 = {TBL_ID_PHYS_INVTY_AJT, "Regist", Integer.toString(insertPhysInvtyAdjCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp3);
        String[] tmp4 = {TBL_ID_PHYS_INVTY_CTRL, "Update", Integer.toString(updatePhysInvtyCtrlCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp4);

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        // Setting of termination code
        setTermState(termCd, normalCount - errorCount, errorCount, selectCount);

    }

    /**
     * Check processing of input parameter
     */
    private void checkParameter() {

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
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
        } else {
            if (!INTERFACE_ID_NLCI0330.equals(getInterfaceID())) {
                // When the Interface ID is not correct,
                // processing is ended
                String[] tmp = {MSG_STR_INTERFACE_ID };
                throw new S21AbendException(MSG_ID_NLCM0063E, tmp);
            }
        }

    }

    /**
     * Mail Sending processing for error data
     */
    private void sendMailForErrorData() {

        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();
        if (fromAddrList.isEmpty()) {
            return;
        }

        for (String key : mailMapData.keySet()) {
            List<String> mailList = mailMapData.get(key);

            S21Mail mail = new S21Mail(glblCmpyCd);
            mail.setFromAddress(fromAddrList.get(0));

            S21MailGroup toGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            toGrp.setMailKey1(key);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (toAddrList.isEmpty()) {
                toGrp.setMailKey1(MAIL_KEY1_VALUE_TO);
                toAddrList = toGrp.getMailAddress();
                if (toAddrList.isEmpty()) {
                    return;
                }
            }
            mail.setToAddressList(toAddrList);

            S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
            if (!ZYPCommonFunc.hasValue(tmpl.getSubject())) {
                return;
            }

            String newLine = System.getProperty(LINE_SEPARATOR_KEY_FOR_MAIL);

            StringBuilder msgBuf = new StringBuilder();
            for (String msg : mailList) {
                msgBuf.append(msg);
                msgBuf.append(newLine);
            }

            SimpleDateFormat errTmFmt = new SimpleDateFormat(DATE_TIME_PATTERN_FOR_MAIL);
            String ts = errTmFmt.format(new Date());

            tmpl.setTemplateParameter(MAIL_TEMPLATE_PARAM_KEY_BATCHID, BUSINESS_ID);
            tmpl.setTemplateParameter(MAIL_TEMPLATE_PARAM_KEY_TIMESTAMP, ts);
            tmpl.setTemplateParameter(MAIL_TEMPLATE_PARAM_KEY_ERRDATE, ts);
            tmpl.setTemplateParameter(MAIL_TEMPLATE_PARAM_KEY_MESSAGE, msgBuf.toString());

            mail.setMailTemplate(tmpl);
            mail.postMail();
        }
    }

    /**
     * Registration class of NLCI0330_01 table
     */
    private class CreateAdjustmentOrder extends S21SsmVoidResultSetHandlerSupport {

        /**
         * adjstVarAmtPiTotalMap
         */
        private Map<BigDecimal, BigDecimal> adjstVarAmtPiTotalMap = new HashMap<BigDecimal, BigDecimal>();
        /**
         * adjstGrsAmtPiTotalMap
         */
        private Map<BigDecimal, BigDecimal> adjstGrsAmtPiTotalMap = new HashMap<BigDecimal, BigDecimal>();


        /**
         * Making processing of Adjustment Order
         * @param rs NLCI0330_01ResultSet
         * @throws SQLException
         */
        protected void doProcessQueryResult(final ResultSet rs) throws SQLException {

            boolean isAdjust = false;
            boolean isSkip = false;
            String nowPiHeaderKey = null;
            String beforePiHeaderKey = null;
            String nowPiNum = null;
            String beforePiNum = null;
            String beforePiDt = null;
            String nowPiDt = null;
            String beforePiCtrlNm = null;
            String nowPiCtrlNm = null;
            List<PHYS_INVTY_CNT_DTLTMsg> piDetailList = null;
            Map<String, PHYS_INVTY_CNT_HDRTMsg> piHeaderMap = new HashMap<String, PHYS_INVTY_CNT_HDRTMsg>();
            Map<String, List<PHYS_INVTY_CNT_DTLTMsg>> piDetailMap = new HashMap<String, List<PHYS_INVTY_CNT_DTLTMsg>>();
            // getAvailDcStock from AVAL_INVTY_APP_ID
            String availDcStock = getAvailDcStock();

            while (rs.next()) {
                boolean isApprverErr = false;
                selectCount++;
                nowPiNum = rs.getString(COL_NM_PHYS_INVTY_NUM);
                nowPiCtrlNm = rs.getString(COL_NM_PHYS_INVTY_CTRL_NM);
                nowPiDt = rs.getString(COL_NM_PHYS_INVTY_DT);
                nowPiHeaderKey = rs.getString(COL_NM_PHYS_INVTY_NUM) + KEY_INFO_DELIM + rs.getString(COL_NM_INVTY_LOC_CD) + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_ITEM_IF) + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_STAT_STOCK_IF);

                if (ZYPCommonFunc.hasValue(nowPiNum) && !ZYPCommonFunc.hasValue(rs.getString(COL_NM_CODE_ITEM_IF))) {
                    isAdjust = false;
                } else {
                    isAdjust = true;
                }

                // check mandatory fields & adjustData
                if (isAdjust) {
                    if (!checkMandatoryData(rs) || !checkAdjustmentData(rs, availDcStock)) {
                        skipCount++;
                        isSkip = true;
                    }
                }
                if ((beforePiNum != null && !beforePiNum.equals(nowPiNum))) {
                    // aggregate
                    aggregatePIData(beforePiNum, piHeaderMap, piDetailMap);
                    // update(insert) PI tables
                    updatePITables(beforePiNum, piHeaderMap, piDetailMap);
                    // call NLZC0610 PI Approval To WF API
                    String targetPiNum = null;
                    for (Map.Entry<String, PHYS_INVTY_CNT_HDRTMsg> headerPiEntry : piHeaderMap.entrySet()) {
                        targetPiNum = headerPiEntry.getKey().substring(0, headerPiEntry.getKey().indexOf(KEY_INFO_DELIM));
                        if (beforePiNum.equals(targetPiNum)) {
                            // QC#24449
                            NLZC061001PMsg pMsg = callPIApprovalToWfApi(beforePiCtrlNm, beforePiDt, beforePiNum);
                            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
                            if (!msgList.isEmpty()) {
                                for (String xxMsgId : msgList) {
                                    if (xxMsgId.equals("NLCM0168E")) {
                                        rollback();
                                        isApprverErr = true;
                                        insertPhysInvtyAdjCount = 0;
                                        insertPhysInvtyCntHdrCount = 0;
                                        updatePhysInvtyCtrlCount = 0;
                                        String errorMsg = S21MessageFunc.clspGetMessage(xxMsgId, null);
                                        S21InfoLogOutput.println(errorMsg);
                                        // START 2019/05/22 T.Ogura [QC#50055,MOD]
//                                        sendMailForNoApprove();
                                        String message = "Transaction ID = " + transactionId + ", " + "Warehouse Code = " + rs.getString(COL_NM_RTL_WH_CD);
                                        sendMailForNoApprove(message);
                                        // END   2019/05/22 T.Ogura [QC#50055,MOD]
                                        commit();
                                    }
                                }
                            }
                            break;
                        }
                    }
                    for (Map.Entry<String, PHYS_INVTY_CNT_HDRTMsg> headerPiEntry : piHeaderMap.entrySet()) {
                        targetPiNum = headerPiEntry.getKey().substring(0, headerPiEntry.getKey().indexOf(KEY_INFO_DELIM));
                        if (beforePiNum.equals(targetPiNum)) {
                            normalCount += piDetailMap.get(headerPiEntry.getKey()).size();
                        }
                    }
                    if(isApprverErr) {
                        errorCount = normalCount;
                        isApprverErr = false;
                    }
                    piHeaderMap.clear();
                    if (piDetailList != null) {
                        piDetailList.clear();
                    }
                    piDetailMap.clear();
                    adjstVarAmtPiTotalMap.clear();
                    adjstGrsAmtPiTotalMap.clear();
                }
                if (isAdjust && !isSkip) {
                    // Create AdjustData(Header)
                    if (beforePiHeaderKey == null || !beforePiHeaderKey.equals(nowPiHeaderKey) || piHeaderMap.get(nowPiHeaderKey) == null) {
                        piHeaderMap.put(nowPiHeaderKey, createAdjustmentHeaderData(rs));
                    }
                    // Create AdjustData(Detail)
                    piDetailList = piDetailMap.get(nowPiHeaderKey);
                    if (piDetailList == null) {
                        piDetailList = new ArrayList<PHYS_INVTY_CNT_DTLTMsg>();
                    }
                    piDetailList.add(createAdjustmentDetailData(piHeaderMap.get(nowPiHeaderKey).physInvtyCntHdrPk.getValue(), rs));
                    piDetailMap.put(nowPiHeaderKey, piDetailList);
                    // check Create WF or not?
                } else if (!isAdjust && !isSkip) {
                    // Close PI
                    if (!closePI(nowPiNum)) {
                        errorCount++;
                        return;
                    }
                }
                beforePiHeaderKey = nowPiHeaderKey;
                beforePiNum = nowPiNum;
                beforePiDt = nowPiDt;
                beforePiCtrlNm = nowPiCtrlNm;
                if (rs.isLast() && isAdjust) {
                    // aggregate
                    aggregatePIData(nowPiNum, piHeaderMap, piDetailMap);
                    // update(insert) PI tables
                    updatePITables(nowPiNum, piHeaderMap, piDetailMap);
                    // call NLZC0610 PI Approval To WF API
                    String targetPiNum = null;
                    for (Map.Entry<String, PHYS_INVTY_CNT_HDRTMsg> headerPiEntry : piHeaderMap.entrySet()) {
                        targetPiNum = headerPiEntry.getKey().substring(0, headerPiEntry.getKey().indexOf(KEY_INFO_DELIM));
                        if (nowPiNum.equals(targetPiNum)) {
                            // QC#24449
                            NLZC061001PMsg pMsg = callPIApprovalToWfApi(nowPiCtrlNm, nowPiDt, nowPiNum);
                            List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
                            if (!msgList.isEmpty()) {
                                for (String xxMsgId : msgList) {
                                    if (xxMsgId.equals("NLCM0168E")) {
                                        rollback();
                                        isApprverErr = true;
                                        insertPhysInvtyAdjCount = 0;
                                        insertPhysInvtyCntHdrCount = 0;
                                        updatePhysInvtyCtrlCount = 0;
                                        String errorMsg = S21MessageFunc.clspGetMessage(xxMsgId, null);
                                        S21InfoLogOutput.println(errorMsg);
                                        // START 2019/05/22 T.Ogura [QC#50055,MOD]
//                                        sendMailForNoApprove();
                                        String message = "Transaction ID = " + transactionId + ", " + "Warehouse Code = " + rs.getString(COL_NM_RTL_WH_CD);
                                        sendMailForNoApprove(message);
                                        // END   2019/05/22 T.Ogura [QC#50055,MOD]
                                        commit();
                                    }
                                }
                            }
                            break;
                        }
                    }
                    for (Map.Entry<String, PHYS_INVTY_CNT_HDRTMsg> headerPiEntry : piHeaderMap.entrySet()) {
                        targetPiNum = headerPiEntry.getKey().substring(0, headerPiEntry.getKey().indexOf(KEY_INFO_DELIM));
                        if (nowPiNum.equals(targetPiNum)) {
                            normalCount += piDetailMap.get(headerPiEntry.getKey()).size();
                        }
                    }
                    if(isApprverErr) {
                        errorCount = normalCount;
                        isApprverErr = false;
                    }
                }
                isSkip = false;

            }
        }

        // QC#24449
        private NLZC061001PMsg callPIApprovalToWfApi(String piCtrlNm, String piDt, String piNum) throws SQLException {
            
         // QC# 27709 ADD START
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("physInvtyNum", piNum);

            List<BigDecimal> result = (List<BigDecimal>) ssmClient.queryObjectList("getPIControlsFromPINumer", ssmParam);

            for (BigDecimal piCtrlPk : result) {

                PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, piCtrlPk);
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

                PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

                if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {

                    throw new S21AbendException(NMAM0177E, new String[] {"Physical Inventory Control" });

                }

                ZYPEZDItemValueSetter.setValue(outMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.WAITAPPROVAL);

                EZDTBLAccessor.update(outMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                    throw new S21AbendException(NMAM0177E, new String[] {"Physical Inventory Control" });

                }

            }
            // QC# 27709 ADD END
            
            
            
            NLZC061001 nlzc061001 = new NLZC061001();
            NLZC061001PMsg pMsg = new NLZC061001PMsg();
            pMsg.glblCmpyCd.setValue(glblCmpyCd);
            //pMsg.salesDate.setValue(ZYPDateUtil.getSalesDate(glblCmpyCd));
            pMsg.salesDate.setValue(batProcDate);
            pMsg.xxMode.setValue(CREATE_MODE);
            pMsg.physInvtyCtrlNm.setValue(piCtrlNm);
            pMsg.physInvtyDt.setValue(piDt);
            pMsg.physInvtyNum.setValue(piNum);
            pMsg.xxUserId.setValue(SYSTEM_USER);
            nlzc061001.execute(pMsg, ONBATCH_TYPE.BATCH);

            return pMsg;
            
        }

        private void updatePITables(String  piNum, Map<String, PHYS_INVTY_CNT_HDRTMsg> piHeaderMap, Map<String, List<PHYS_INVTY_CNT_DTLTMsg>> piDetailMap) throws SQLException {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(SQL_PRM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(SQL_PRM_PHYS_INVTY_NUM, piNum);
            List<BigDecimal> result = (List<BigDecimal>) ssmClient.queryObjectList(SQL_STID_GET_PHYS_INVTY_CTRL_PK, paramMap);

            if (result.size() == 0) {
                throw new S21AbendException(MSG_ID_NLGM0008E, new String[] {TBL_ID_PHYS_INVTY_CTRL
                        , COL_NM_PHYS_INVTY_NUM
                        , piNum });
            }
            for (BigDecimal piCtrlPk : result) {
                PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, piCtrlPk);
                PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

                if (outMsg == null) {
                    throw new S21AbendException(MSG_ID_NLGM0008E, new String[] {TBL_ID_PHYS_INVTY_CTRL, COL_NM_PHYS_INVTY_CTRL_PK, piCtrlPk.toPlainString() });
                }
                if (adjstVarAmtPiTotalMap.get(piCtrlPk) !=  null) {
                    ZYPEZDItemValueSetter.setValue(outMsg.adjNetAmt, adjstVarAmtPiTotalMap.get(piCtrlPk));
                }
                if (adjstGrsAmtPiTotalMap.get(piCtrlPk) !=  null) {
                    ZYPEZDItemValueSetter.setValue(outMsg.adjGrsAmt, adjstGrsAmtPiTotalMap.get(piCtrlPk));
                }
                S21ApiTBLAccessor.update(outMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                    throw new S21AbendException(MSG_ID_NLGM0008E, new String[] {TBL_ID_PHYS_INVTY_CTRL, COL_NM_PHYS_INVTY_CTRL_PK, piCtrlPk.toPlainString() });
                }
                updatePhysInvtyCtrlCount++;
            }

            // save PIHeader/PIDetail
            for (PHYS_INVTY_CNT_HDRTMsg headerPiMsg : piHeaderMap.values()) {
                if (!ZYPCommonFunc.hasValue(headerPiMsg.adjVarFlg)) {
                    continue;
                }
                S21ApiTBLAccessor.insert(headerPiMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(headerPiMsg.getReturnCode())) {
                    throw new S21AbendException(MSG_ID_NLGM0007E, new String[] {TBL_ID_PHYS_INVTY_CNT_HDR, COL_NM_PHYS_INVTY_CTRL_PK, headerPiMsg.physInvtyCtrlPk.getValue().toPlainString() });
                }
                insertPhysInvtyCntHdrCount++;
            }

            for (List<PHYS_INVTY_CNT_DTLTMsg> dtlMsgList : piDetailMap.values()) {
                for (PHYS_INVTY_CNT_DTLTMsg dtlMsg : dtlMsgList) {
                    S21ApiTBLAccessor.insert(dtlMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlMsg.getReturnCode())) {
                        throw new S21AbendException(MSG_ID_NLGM0007E, new String[] {TBL_ID_PHYS_INVTY_CNT_DTL, COL_NM_PHYS_INVTY_CTRL_PK, dtlMsg.physInvtyCtrlPk.getValue().toPlainString() });
                    }
                }
            }
        }

        private void aggregatePIData(String piNum, Map<String, PHYS_INVTY_CNT_HDRTMsg> piHeaderMap, Map<String, List<PHYS_INVTY_CNT_DTLTMsg>> piDetailMap) throws SQLException {
            // aggregate PI Detail -> PI Header
            for (Map.Entry<String, PHYS_INVTY_CNT_HDRTMsg> headerPiEntry : piHeaderMap.entrySet()) {
                //String headerPiMapKey = headerPiEntry.getKey();

                BigDecimal latestCountInputQty = BigDecimal.ZERO;
                String latestCountInputTs = "";
                BigDecimal invtyAvailQty = BigDecimal.ZERO;
                String invtyRgstTs = "";
                BigDecimal adjstVarQty = BigDecimal.ZERO;
                BigDecimal adjstVarAmt = BigDecimal.ZERO;

                if (!headerPiEntry.getKey().startsWith(piNum)) {
                    continue;
                }
                List<PHYS_INVTY_CNT_DTLTMsg> targetDetailList = piDetailMap.get(headerPiEntry.getKey());
                if (targetDetailList != null) {
                    for (PHYS_INVTY_CNT_DTLTMsg targetDetailMsg : targetDetailList) {
                        latestCountInputQty = latestCountInputQty.add(targetDetailMsg.ltstCntInpQty.getValue());

                        if (latestCountInputTs.compareTo(targetDetailMsg.ltstCntInpTs.getValue()) < 0) {
                            latestCountInputTs = targetDetailMsg.ltstCntInpTs.getValue();
                        }
                        if (ZYPCommonFunc.hasValue(targetDetailMsg.invtyAvalQty.getValue())) {
                            invtyAvailQty = invtyAvailQty.add(targetDetailMsg.invtyAvalQty.getValue());
                        }
                        if (invtyRgstTs.compareTo(targetDetailMsg.invtyRegdTs.getValue()) < 0) {
                            invtyRgstTs = targetDetailMsg.invtyRegdTs.getValue();
                        }
                        if (ZYPCommonFunc.hasValue(targetDetailMsg.adjVarQty.getValue())) {
                            adjstVarQty = adjstVarQty.add(targetDetailMsg.adjVarQty.getValue());
                        }
                        if (ZYPCommonFunc.hasValue(targetDetailMsg.adjVarAmt.getValue())) {
                            adjstVarAmt = adjstVarAmt.add(targetDetailMsg.adjVarAmt.getValue());
                        }
                    }
                }


                ZYPEZDItemValueSetter.setValue(headerPiEntry.getValue().firstCntInpQty, latestCountInputQty);
                ZYPEZDItemValueSetter.setValue(headerPiEntry.getValue().firstCntInpTs, latestCountInputTs);
                ZYPEZDItemValueSetter.setValue(headerPiEntry.getValue().ltstCntInpQty, latestCountInputQty);
                ZYPEZDItemValueSetter.setValue(headerPiEntry.getValue().ltstCntInpTs, latestCountInputTs);
                ZYPEZDItemValueSetter.setValue(headerPiEntry.getValue().invtyAvalQty, invtyAvailQty);
                ZYPEZDItemValueSetter.setValue(headerPiEntry.getValue().invtyRegdTs, invtyRgstTs);
                if ((targetDetailList != null) && (targetDetailList.size() > 0)) {
                    ZYPEZDItemValueSetter.setValue(headerPiEntry.getValue().adjVarFlg, ZYPConstant.FLG_ON_Y);
                }
                ZYPEZDItemValueSetter.setValue(headerPiEntry.getValue().adjVarQty, adjstVarQty);
                ZYPEZDItemValueSetter.setValue(headerPiEntry.getValue().adjVarAmt, adjstVarAmt);
            }

            // aggregate PI Header -> PI Control
            //for (PHYS_INVTY_CNT_HDRTMsg headerPiMsg : piHeaderMap.values()) {
              for (Map.Entry<String, PHYS_INVTY_CNT_HDRTMsg> headerPiEntry : piHeaderMap.entrySet()) {
                if (adjstVarAmtPiTotalMap.get(headerPiEntry.getValue().physInvtyCtrlPk.getValue()) == null) {
                    adjstVarAmtPiTotalMap.put(headerPiEntry.getValue().physInvtyCtrlPk.getValue(), headerPiEntry.getValue().adjVarAmt.getValue());
                } else {
                    adjstVarAmtPiTotalMap.put(headerPiEntry.getValue().physInvtyCtrlPk.getValue(),
                            adjstVarAmtPiTotalMap.get(headerPiEntry.getValue().physInvtyCtrlPk.getValue()).add(headerPiEntry.getValue().adjVarAmt.getValue()));
                }
                List<PHYS_INVTY_CNT_DTLTMsg> targetDetailList = piDetailMap.get(headerPiEntry.getKey());
                if (targetDetailList != null) {
                    for (PHYS_INVTY_CNT_DTLTMsg targetDetailMsg : targetDetailList) {
                        if (adjstGrsAmtPiTotalMap.get(headerPiEntry.getValue().physInvtyCtrlPk.getValue()) == null) {
                            adjstGrsAmtPiTotalMap.put(headerPiEntry.getValue().physInvtyCtrlPk.getValue(), targetDetailMsg.adjVarAmt.getValue().abs());
                        } else {
                            adjstGrsAmtPiTotalMap.put(headerPiEntry.getValue().physInvtyCtrlPk.getValue(),
                                    adjstGrsAmtPiTotalMap.get(headerPiEntry.getValue().physInvtyCtrlPk.getValue()).add(targetDetailMsg.adjVarAmt.getValue().abs()));
                        }
                    }
                }
              }
        }

//        protected void doProcessQueryResultOld(final ResultSet rs) throws SQLException {
//
//            int lineCount = 0;
//            boolean adjustRec = false;
//            boolean beforeAdjustRec = false;
//            String nowRtlWhCd = null;
//            String beforeRtlWhCd = null;
//            String nowPiNum = null;
//            String beforePiNum = null;
//
//            List<NLZC004001PMsg> dLZC004001PMsgList = new ArrayList<NLZC004001PMsg>();
//            Map<String, NLZC004001PMsg> dLZC004001PMsgMap = new HashMap<String, NLZC004001PMsg>();
//            Map<String, PHYS_INVTY_ADJTMsg> piAdjustMap = new HashMap<String, PHYS_INVTY_ADJTMsg>();
//            Map<String, PHYS_INVTY_CNT_HDRTMsg> piCountHeaderMap = new HashMap<String, PHYS_INVTY_CNT_HDRTMsg>();
//            Map<String, PHYS_INVTY_CNT_DTLTMsg> piCountDetailMap = new HashMap<String, PHYS_INVTY_CNT_DTLTMsg>();
//
//            // getAvailDcStock from AVAL_INVTY_APP_ID
//            String availDcStock = getAvailDcStock();
//
//            while (rs.next()) {
//                selectCount++;
//
//                if (isAdjustment(rs)) {
//                    adjustRec = true;
//                } else {
//                    adjustRec = false;
//                }
//
//                boolean skipRec = false;
//
//                // Check on Adjustment data
//                if (adjustRec && !checkAdjustmentData(rs, availDcStock)) {
//                    skipCount++;
//                    skipRec = true;
//                }
//
//                // Whether the RTL_WH_CD or PHYS_INVTY_NUM or SIGN_IF
//                // changed is compared
//                nowRtlWhCd = rs.getString(COL_NM_RTL_WH_CD);
//                nowPiNum = rs.getString(COL_NM_PHYS_INVTY_NUM);
//
//                boolean isAdjustFlg = false;
//
//                if (beforePiNum != null) {
//                    if (!beforePiNum.equals(nowPiNum)) {
//                        isAdjustFlg = true;
//                    }
//                    if (adjustRec) {
//                        if ((beforeRtlWhCd != null && !beforeRtlWhCd.equals(nowRtlWhCd)) || (nowRtlWhCd != null && !nowRtlWhCd.equals(beforeRtlWhCd))) {
//                            isAdjustFlg = true;
//                        }
//                        if (!dLZC004001PMsgList.isEmpty()) {
//                            if ((dLZC004001PMsgList.size() - 1) == DETAIL_MAX_CNT) {
//                                isAdjustFlg = isAdjustmentApiCallable(rs, dLZC004001PMsgList);
//                            }
//                        }
//                    }
//                }
//
//                if (isAdjustFlg && !dLZC004001PMsgList.isEmpty()) {
//                    if (executeAdjustmentOrderApi(dLZC004001PMsgList)) {
//                        // add PI Adjust
//                        addPiAdjust(dLZC004001PMsgList, piAdjustMap, piCountHeaderMap);
//                        normalCount += lineCount;
//                    } else {
//                        errorCount++;
//                        return;
//                    }
//                    dLZC004001PMsgList.clear();
//                    dLZC004001PMsgMap.clear();
//                    piAdjustMap.clear();
//
//                    lineCount = 0;
//                }
//
//                if (beforePiNum != null && !nowPiNum.equals(beforePiNum)) {
//                    if (!closePIOld(piCountHeaderMap, piCountDetailMap, beforeAdjustRec, beforePiNum)) {
//                        errorCount++;
//                        return;
//                    }
//                }
//
//                if (adjustRec) {
//                    if (skipRec) {
//                        createPhysInvtyWorkObjectForSkip(rs, piAdjustMap, piCountHeaderMap, piCountDetailMap);
//                    } else {
//                        createAdjustmentApiParam(rs, dLZC004001PMsgList, dLZC004001PMsgMap);
//                        createPhysInvtyWorkObject(rs, piAdjustMap, piCountHeaderMap, piCountDetailMap);
//                    }
//                }
//
//                // Keep of variable for comparison
//                beforePiNum = nowPiNum;
//                beforeRtlWhCd = nowRtlWhCd;
//                beforeAdjustRec = adjustRec;
//
//                if (!skipRec) {
//                    lineCount++;
//                }
//            }
//
//            // Adlust and Close PI non processed
//            if (!dLZC004001PMsgList.isEmpty()) {
//                if (executeAdjustmentOrderApi(dLZC004001PMsgList)) {
//                    addPiAdjust(dLZC004001PMsgList, piAdjustMap, piCountHeaderMap);
//                    normalCount += lineCount;
//                } else {
//                    errorCount++;
//                    return;
//                }
//                dLZC004001PMsgList.clear();
//                dLZC004001PMsgMap.clear();
//                piAdjustMap.clear();
//            } else {
//                normalCount += lineCount;
//            }
//            if (!piCountHeaderMap.isEmpty() || !piCountDetailMap.isEmpty()) {
//                if (!closePIOld(piCountHeaderMap, piCountDetailMap, beforeAdjustRec, beforePiNum)) {
//                    errorCount++;
//                    return;
//                }
//            }
//        }

        /**
         * getAvailDcStock from AVAL_INVTY_APP_ID
         * @return availDcStock
         * @throws SQLException
         */
        private String getAvailDcStock() throws SQLException {

            StringBuilder availDcStock = new StringBuilder();

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(SQL_PRM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(SQL_PRM_LOC_STS_CD, LOC_STS.DC_STOCK);
            queryParam.put(SQL_BIZ_APP_ID, ADJUST_SRC_ID);
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            S21SsmLowLevelCodingClient ssmLowLevelCodingClient = S21SsmLowLevelCodingClient.getClient(NLCB002001.class);

            try {

                // Find AvailDcStock.
                pstmt = ssmLowLevelCodingClient.createPreparedStatement("getAvailDcStock", queryParam);
                rs = pstmt.executeQuery();

                // Data does not exist.
                while (rs.next()) {
                    availDcStock.append(rs.getString(COL_STK_STS_CD));
                }

            } finally {
                S21SsmLowLevelCodingClient.closeResource(pstmt, rs);
            }
            return availDcStock.toString();
        }

//        /**
//         * Check processing of Adjustment target data
//         * @param rs NLCI0330_01ResultSet
//         * @return check result
//         * @throws SQLException
//         */
//        private boolean isAdjustment(ResultSet rs) throws SQLException {
//            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_CODE_ITEM_IF))
//                    && !ZYPCommonFunc.hasValue(rs.getString(COL_NM_CODE_STAT_STOCK_IF))
//                    && !ZYPCommonFunc.hasValue(rs.getString(COL_NM_SIGN_IF))
//                    && !ZYPCommonFunc.hasValue(rs.getString(COL_NM_QTY_ADJUST_IF))
//                    && !ZYPCommonFunc.hasValue(rs.getString(COL_NM_INVTY_LOC_CD))
//                    && ZYPCommonFunc.hasValue(rs.getString(COL_NM_PHYS_INVTY_NUM))
//                    && !ZYPCommonFunc.hasValue(rs.getString(COL_NM_SER_NUM))) {
//                return false;
//            }
//            return true;
//        }

        private boolean checkMandatoryData(ResultSet rs) throws SQLException {
            String keyInfo = COL_NM_INTERFACE_ID + "=" + getInterfaceID() + ", " + COL_NM_TRANSACTION_ID + "=" + transactionId + ", " + COL_NM_SEGMENT_ID + "=" + rs.getBigDecimal(COL_NM_SEGMENT_ID) + ", " + COL_NM_UNIT_ID + "="
            + rs.getBigDecimal(COL_NM_UNIT_ID) + ", " + COL_NM_SEQ_NUMBER + "=" + rs.getBigDecimal(COL_NM_SEQ_NUMBER);

            // Check on Item Code
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_CODE_ITEM_IF))) {
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_CODE_ITEM_IF, keyInfo, rs.getString(COL_NM_CODE_ITEM_IF) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }
            // Check on Stock Status Code
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_CODE_STAT_STOCK_IF))) {
                // When stock_sts cannot be acquired, it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_CODE_STAT_STOCK_IF, keyInfo, rs.getString(COL_NM_CODE_STAT_STOCK_IF) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }
            // Check on Sign
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_SIGN_IF))) {
                // When SIGN_IF cannot be acquired, it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_SIGN_IF, keyInfo, rs.getString(COL_NM_SIGN_IF) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }
            // Check on quantity
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_QTY_ADJUST_IF))) {
                // When QTY_ADJUST_IF cannot be acquired, it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_QTY_ADJUST_IF, keyInfo, rs.getString(COL_NM_QTY_ADJUST_IF) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }
            // Check on PI#
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_PHYS_INVTY_NUM))) {
                // When QTY_ADJUST_IF cannot be acquired, it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_PHYS_INVTY_NUM, keyInfo, rs.getString(COL_NM_PHYS_INVTY_NUM) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }
            // Check on Inventory Adjustment Name
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_PHYS_INVTY_ADJ_NM))) {
                // When QTY_ADJUST_IF cannot be acquired, it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_PHYS_INVTY_ADJ_NM, keyInfo, rs.getString(COL_NM_PHYS_INVTY_ADJ_NM) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }
            return true;

        }

        /**
         * Check processing of Adjustment data
         * @param rs NLCI0330_01ResultSet
         * @param String availDcStock
         * @return check result
         * @throws SQLException
         */
        private boolean checkAdjustmentData(ResultSet rs, String availDcStock) throws SQLException {

            String keyInfo = COL_NM_INTERFACE_ID + "=" + getInterfaceID() + ", " + COL_NM_TRANSACTION_ID + "=" + transactionId + ", " + COL_NM_SEGMENT_ID + "=" + rs.getBigDecimal(COL_NM_SEGMENT_ID) + ", " + COL_NM_UNIT_ID + "="
                    + rs.getBigDecimal(COL_NM_UNIT_ID) + ", " + COL_NM_SEQ_NUMBER + "=" + rs.getBigDecimal(COL_NM_SEQ_NUMBER);

            // Check on stock_sts 1
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_CODE_STAT_STOCK_IF))) {
                // When stock_sts cannot be acquired, it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_CODE_STAT_STOCK_IF, keyInfo, rs.getString(COL_NM_CODE_STAT_STOCK_IF) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }

            // Check on stock_sts 2
            if (!availDcStock.contains(rs.getString(COL_NM_CODE_STAT_STOCK_IF))) {
                // When stock_sts is invalid , it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_CODE_STAT_STOCK_IF, keyInfo, rs.getString(COL_NM_CODE_STAT_STOCK_IF) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }

            // Check on WH code
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_INVTY_LOC_CD))) {
                // When CODE_WH_IF cannot be acquired,
                // it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_INVTY_LOC_CD, keyInfo, rs.getString(COL_NM_INVTY_LOC_CD) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }

            // Check on sign
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_SIGN_IF))) {
                // When SIGN_IF cannot be acquired, it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_SIGN_IF, keyInfo, rs.getString(COL_NM_SIGN_IF) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }

            // Check on quantity
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_QTY_ADJUST_IF))) {
                // When QTY_ADJUST_IF cannot be acquired, it skips
                String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_QTY_ADJUST_IF, keyInfo, rs.getString(COL_NM_QTY_ADJUST_IF) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            } else {
                // When QTY_ADJUST_IF is 0, it skips
                if (BigDecimal.ZERO.compareTo(rs.getBigDecimal(COL_NM_QTY_ADJUST_IF)) == 0) {
                    String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_QTY_ADJUST_IF, keyInfo, rs.getString(COL_NM_QTY_ADJUST_IF) };
                    setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                    return false;
                }
//                else {
//                    // When QTY_ADJUST_IF exceeds On-Hand, it skips
//                    if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                        if (rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).compareTo(rs.getBigDecimal(COL_NM_INVTY_QTY)) > 0) {
//                            String[] tmp = {TBL_ID_NLCI0330_01 + "." + COL_NM_QTY_ADJUST_IF, keyInfo, rs.getString(COL_NM_QTY_ADJUST_IF) };
//                            setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
//                            return false;
//                        }
//                    }
//                }
            }

            // Check on SQL JOIN
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_FIRST_PROD_CTRL_CD))) {
                // When FIRST_PROD_CTRL_CD cannot be acquired,
                // it skips
                String[] tmp = {TBL_ID_MDSE + "." + COL_NM_FIRST_PROD_CTRL_CD, COL_NM_GLBL_CMPY_CD + "=" + glblCmpyCd + ", " + COL_NM_MDSE_CD + "=" + rs.getString(COL_NM_CODE_ITEM_IF), rs.getString(COL_NM_FIRST_PROD_CTRL_CD) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }

            // Check on PI Control not exist
            if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_PHYS_INVTY_CTRL_PK))) {
                // When PHYS_INVTY_CTRL.PHYS_INVTY_CTRL_PK cannot be acquired,
                // it skips
                String piKeyInfo = ", " + COL_NM_PHYS_INVTY_NUM + "=" + rs.getString(COL_NM_PHYS_INVTY_NUM);
                String[] tmp = {TBL_ID_PHYS_INVTY_CTRL + "." + COL_NM_PHYS_INVTY_CTRL_PK, keyInfo + piKeyInfo, rs.getString(COL_NM_PHYS_INVTY_CTRL_PK) };
                setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
                return false;
            }

            // Check on PI Control Status
//            if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_PHYS_INVTY_STS_CD))) {
//                if (!PHYS_INVTY_STS.OPEN.equals(rs.getString(COL_NM_PHYS_INVTY_STS_CD))) {
//                    String piKeyInfo = ", " + COL_NM_PHYS_INVTY_NUM + "=" + rs.getString(COL_NM_PHYS_INVTY_NUM);
//                    String[] tmp = {TBL_ID_PHYS_INVTY_CTRL + "." + COL_NM_PHYS_INVTY_STS_CD, keyInfo + piKeyInfo, rs.getString(COL_NM_PHYS_INVTY_STS_CD) };
//                    setErrorInfo(MSG_ID_NLCM0049E, tmp, rs);
//                    return false;
//                }
//            }

            // Check on Serial
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_NM_RCV_SER_TAKE_FLG))) {
                if (!ZYPCommonFunc.hasValue(rs.getString(COL_NM_SER_NUM))) {
                    setErrorInfo(MSG_ID_NLCM0181E, new String[] {rs.getString(COL_NM_CODE_ITEM_IF)}, rs);
                    return false;
                }

                NLZC403001PMsg pmsg = new NLZC403001PMsg();
                ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
                String xxModeCd = new String();
                // sign of  I/F NLCI0330_01.SIGN_IF
                String nowSignIf = rs.getString(COL_NM_SIGN_IF);
                if (STR_MINUS.equals(nowSignIf)) {
                    xxModeCd = NLZC403001Constant.MODE_INBOUND;
                } else if (STR_PLUS.equals(nowSignIf)) {
                    xxModeCd = NLZC403001Constant.MODE_ADJUSTMENT_DECREASE;
                }
                ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, xxModeCd);
                ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(COL_NM_CODE_ITEM_IF));
                ZYPEZDItemValueSetter.setValue(pmsg.serNum, rs.getString(COL_NM_SER_NUM));
                ZYPEZDItemValueSetter.setValue(pmsg.whCd, rs.getString(COL_NM_INVTY_LOC_CD));

                // Serial Validation API is executed
                boolean returnFlg = true;
                NLZC403001 nlzc4030 = new NLZC403001();
                nlzc4030.execute(pmsg, ONBATCH_TYPE.BATCH);

                // Error judgment by message ID
                List<String> msgList = S21ApiUtil.getXxMsgIdList(pmsg);
                if (!msgList.isEmpty()) {
                    for (String xxMsgId : msgList) {
                        setApiErrorInfo(API_ID_NLZC4030, xxMsgId, rs);
                        returnFlg = false;
                    }
                    S21InfoLogOutput.println(API_ID_NLZC4030 + MSG_ID_NLCM0047E + ", PMsg : " + pmsg.toString());
                }

                String retCd = pmsg.xxRsltStsCd.getValue();
                if (!NLZC403001Constant.RETURN_CODE_NORMAL.equals(retCd)) {
                    returnFlg = false;
                }

                if (!returnFlg) {
                    return false;
                }
            } else {
                if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_SER_NUM))) {
                    setErrorInfo(MSG_ID_NLCM0186W, new String[] {rs.getString(COL_NM_SER_NUM), rs.getString(COL_NM_CODE_ITEM_IF)}, rs);
                }
            }

            // Check on MachineMaster
            if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_CONFIG_MSTR_NUM))) {
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put(SQL_PRM_GLBL_CMPY_CD, glblCmpyCd);
                queryParam.put(SQL_PRM_CONFIG_MSTR_PK, rs.getString(COL_NM_CONFIG_MSTR_NUM));
                queryParam.put(SQL_PRM_CUR_LOC_NUM, rs.getString(COL_NM_INVTY_LOC_CD));
                BigDecimal count = (BigDecimal) ssmClient.queryObject(SQL_STID_GET_CONFIG_ID, queryParam);
                if (count.compareTo(BigDecimal.ZERO) == 0) {
                    setErrorInfo(MSG_ID_NLCM0148E, new String[] {rs.getString(COL_NM_CONFIG_MSTR_NUM)}, rs);
                    return false;
                }

            }


            return true;

        }

//        /**
//         * Execution processing of Adjustment Order API
//         * @param pmsgList List<NLZC004001PMsg>
//         * @param locNm Location Name
//         * @return processing result
//         */
//        private boolean executeAdjustmentOrderApi(List<NLZC004001PMsg> pmsgList) {
//
//            boolean returnFlg = true;
//
//            // Adjustment Order API is executed
//            NLZC004001 nlzc0040 = new NLZC004001();
//            nlzc0040.execute(pmsgList, ONBATCH_TYPE.BATCH);
//
//            for (NLZC004001PMsg pmsg : pmsgList) {
//                // Error judgment by message ID
//                List<String> msgList = S21ApiUtil.getXxMsgIdList(pmsg);
//                if (!msgList.isEmpty()) {
//                    for (String xxMsgId : msgList) {
//                        S21InfoLogOutput.println(xxMsgId);
//                        if (xxMsgId.endsWith(MSG_KIND_ERROR)) {
//                            returnFlg = false;
//                        }
//                        setApiErrorInfo(API_ID_NLZC0040, xxMsgId);
//                    }
//                    S21InfoLogOutput.println(API_ID_NLZC0040 + MSG_ID_NLCM0047E + ", PMsg : " + pmsg.toString());
//                }
//            }
//
//            //
//            return returnFlg;
//        }

        /**
         * PI Close when no adjustment
         * @param nowPiNum String
         * @return boolean
         */
        private boolean closePI(String nowPiNum) {
            // Reset AMT Value to 0
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(SQL_PRM_GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(SQL_PRM_PHYS_INVTY_NUM, nowPiNum);
            List<BigDecimal> result = (List<BigDecimal>) ssmClient.queryObjectList(SQL_STID_GET_PHYS_INVTY_CTRL_PK, paramMap);

            if (result.size() == 0) {
                throw new S21AbendException(MSG_ID_NLGM0008E, new String[] {TBL_ID_PHYS_INVTY_CTRL
                        , COL_NM_PHYS_INVTY_NUM
                        , nowPiNum });
            }
            for (BigDecimal pk : result) {
                PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, pk);
                PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);

                if (outMsg == null) {
                    throw new S21AbendException(MSG_ID_NLGM0008E, new String[] {TBL_ID_PHYS_INVTY_CTRL
                    , COL_NM_PHYS_INVTY_CTRL_PK
                    , pk.toPlainString() });
                }
                ZYPEZDItemValueSetter.setValue(outMsg.adjGrsAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(outMsg.adjNetAmt, BigDecimal.ZERO);
                S21ApiTBLAccessor.update(outMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                    throw new S21AbendException(MSG_ID_NLGM0008E, new String[] {TBL_ID_PHYS_INVTY_CTRL
                    , COL_NM_PHYS_INVTY_CTRL_PK
                    , pk.toPlainString() });
                }
            }
            // Call PI Close API
            NLZC063001PMsg dNLZC063001PMsg = new NLZC063001PMsg();
            ZYPEZDItemValueSetter.setValue(dNLZC063001PMsg.glblCmpyCd , glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dNLZC063001PMsg.salesDate, ZYPDateUtil.getBatProcDate());
            ZYPEZDItemValueSetter.setValue(dNLZC063001PMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.PI_COMPLETED);
            ZYPEZDItemValueSetter.setValue(dNLZC063001PMsg.physInvtyNum, nowPiNum);

            if (executePiCloseApi(dNLZC063001PMsg)) {
                updatePhysInvtyCtrlCount++;
                normalCount++;
            } else {
                return false;
            }
            return true;

        }

//        /**
//         * Execution processing of PI Close
//         * @param piCountHeaderMap Map<String, PHYS_INVTY_CNT_HDRTMsg>
//         * @param piCountDetailMap Map<String, PHYS_INVTY_CNT_DTLTMsg>
//         * @param beforeAdjustRec boolean 
//         * @param piNum String PHYS_INVTY_NUM
//         * @return processing result
//         */
//        private boolean closePIOld(Map<String, PHYS_INVTY_CNT_HDRTMsg> piCountHeaderMap, Map<String, PHYS_INVTY_CNT_DTLTMsg> piCountDetailMap, boolean beforeAdjustRec, String piNum) {
//
//            Map<String, PHYS_INVTY_CTRLTMsg> piCtrlMap = new HashMap<String, PHYS_INVTY_CTRLTMsg>();
//
//            // Loop By PI Count Header Map
//            Set<String> piCountHeaderMapKeySet = piCountHeaderMap.keySet();
//            for (String key : piCountHeaderMapKeySet) {
//                PHYS_INVTY_CNT_HDRTMsg targetPiCountHeaderTmsg = piCountHeaderMap.get(key);
//                PHYS_INVTY_CNT_HDRTMsg insertPiCountHeaderTmsg = new PHYS_INVTY_CNT_HDRTMsg();
//
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.physInvtyCntHdrPk, targetPiCountHeaderTmsg.physInvtyCntHdrPk);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.glblCmpyCd, targetPiCountHeaderTmsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.physInvtyCtrlPk, targetPiCountHeaderTmsg.physInvtyCtrlPk);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.physInvtyCtrlNm, targetPiCountHeaderTmsg.physInvtyCtrlNm);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.physInvtyDt, targetPiCountHeaderTmsg.physInvtyDt);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.whCd, targetPiCountHeaderTmsg.whCd);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.rtlWhCd, targetPiCountHeaderTmsg.rtlWhCd);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.rtlSwhCd, targetPiCountHeaderTmsg.rtlSwhCd);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.mdseCd, targetPiCountHeaderTmsg.mdseCd);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.stkStsCd, targetPiCountHeaderTmsg.stkStsCd);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.sysCntRegdFlg, targetPiCountHeaderTmsg.sysCntRegdFlg);
//                BigDecimal firstCntInpQty = targetPiCountHeaderTmsg.invtyAvalQty.getValue().add(targetPiCountHeaderTmsg.adjVarQty.getValue());
//                String currentTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.firstCntInpQty, firstCntInpQty);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.firstCntInpTs, currentTimeStamp);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.ltstCntInpQty, firstCntInpQty);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.ltstCntInpTs, currentTimeStamp);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.invtyAvalQty, targetPiCountHeaderTmsg.invtyAvalQty);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.invtyRegdTs, targetPiCountHeaderTmsg.invtyRegdTs);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.adjVarFlg, targetPiCountHeaderTmsg.adjVarFlg);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.adjVarQty, targetPiCountHeaderTmsg.adjVarQty);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.invtyOrdNum, targetPiCountHeaderTmsg.invtyOrdNum);
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.physInvtyAdjNm, targetPiCountHeaderTmsg.physInvtyAdjNm);
//
//                // NLXC0010 get Inventory Item Cost
//                NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
//                bean.setGlblCmpyCd(glblCmpyCd);
//                bean.setInvtyLocCd(targetPiCountHeaderTmsg.whCd.getValue());
//                bean.setMdseCd(targetPiCountHeaderTmsg.mdseCd.getValue());
//                bean.setQty(targetPiCountHeaderTmsg.adjVarQty.getValue());
//                NLXC001001GetInventoryItemCostBean retNLXC001001GetInventoryItemCostBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
//                BigDecimal adjVarAmt = BigDecimal.ZERO;
//                if (retNLXC001001GetInventoryItemCostBean.getErrList().isEmpty()) {
//                    adjVarAmt = retNLXC001001GetInventoryItemCostBean.getTotPrcAmt();
//                }
//                ZYPEZDItemValueSetter.setValue(insertPiCountHeaderTmsg.adjVarAmt, adjVarAmt);
//
//                EZDTBLAccessor.insert(insertPiCountHeaderTmsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertPiCountHeaderTmsg.getReturnCode())) {
//                    throw new S21AbendException(MSG_ID_NLGM0007E, new String[] {TBL_ID_PHYS_INVTY_CNT_HDR //
//                            , COL_NM_PHYS_INVTY_CNT_HDR_PK
//                            , insertPiCountHeaderTmsg.physInvtyCntHdrPk.getValue().toPlainString() });
//                }
//                insertPhysInvtyCntHdrCount++;
//
//                // PI#/WH Code
//                if (ZYPCommonFunc.hasValue(targetPiCountHeaderTmsg.whCd)) {
//                    String piCtrlMapKey = piNum + KEY_INFO_DELIM + targetPiCountHeaderTmsg.whCd.getValue();
//                    PHYS_INVTY_CTRLTMsg targetPiCtrlTmsg = new PHYS_INVTY_CTRLTMsg();
//                    if (piCtrlMap.containsKey(piCtrlMapKey)) {
//                        targetPiCtrlTmsg = piCtrlMap.get(piCtrlMapKey);
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(targetPiCtrlTmsg.physInvtyCtrlPk, targetPiCountHeaderTmsg.physInvtyCtrlPk); // for update
//                        ZYPEZDItemValueSetter.setValue(targetPiCtrlTmsg.glblCmpyCd, glblCmpyCd);
//                        ZYPEZDItemValueSetter.setValue(targetPiCtrlTmsg.adjGrsAmt, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(targetPiCtrlTmsg.adjNetAmt, BigDecimal.ZERO);
//                    }
//                    BigDecimal adjGrsAmt = targetPiCtrlTmsg.adjGrsAmt.getValue().add(adjVarAmt.abs());
//                    BigDecimal adjNetAmt = targetPiCtrlTmsg.adjNetAmt.getValue().add(adjVarAmt);
//                    ZYPEZDItemValueSetter.setValue(targetPiCtrlTmsg.adjGrsAmt, adjGrsAmt);
//                    ZYPEZDItemValueSetter.setValue(targetPiCtrlTmsg.adjNetAmt, adjNetAmt);
//                    piCtrlMap.put(piCtrlMapKey, targetPiCtrlTmsg);
//                }
//            }
//
//            // Loop By PI Ctrl Map Key
//            Set<String> piCtrlMapKeySet = piCtrlMap.keySet();
//            for (String piCtrlMapKey : piCtrlMapKeySet) {
//                PHYS_INVTY_CTRLTMsg targetPiCtrlTmsg = piCtrlMap.get(piCtrlMapKey);
//                PHYS_INVTY_CTRLTMsg updatePiCtrlTmsg = (PHYS_INVTY_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(targetPiCtrlTmsg);
//                ZYPEZDItemValueSetter.setValue(updatePiCtrlTmsg.adjGrsAmt, targetPiCtrlTmsg.adjGrsAmt);
//                ZYPEZDItemValueSetter.setValue(updatePiCtrlTmsg.adjNetAmt, targetPiCtrlTmsg.adjNetAmt);
//                EZDTBLAccessor.update(updatePiCtrlTmsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updatePiCtrlTmsg.getReturnCode())) {
//                    throw new S21AbendException(MSG_ID_NLGM0008E, new String[] {TBL_ID_PHYS_INVTY_CTRL
//                            , COL_NM_PHYS_INVTY_CTRL_PK
//                            , updatePiCtrlTmsg.physInvtyCtrlPk.getValue().toPlainString() });
//                }
//            }
//
//            // Loop By PI Count Detail Map
//            Set<String> piCountDetailMapKeySet = piCountDetailMap.keySet();
//            for (String key : piCountDetailMapKeySet) {
//                PHYS_INVTY_CNT_DTLTMsg targetPiCountDetailTmsg = piCountDetailMap.get(key);
//
//                // PI#/WH Code/MDSE Code/Stock Status Code/Sign/Config Master Number/Serial#
//                String[] piCountDetailKeyList = key.split("\\/", -1);
//                String targetInvtyLocCd = piCountDetailKeyList[IDX_1];
//
//                // NLXC0010 get Inventory Item Cost
//                NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
//                bean.setGlblCmpyCd(glblCmpyCd);
//                bean.setInvtyLocCd(targetInvtyLocCd);
//                bean.setMdseCd(targetPiCountDetailTmsg.mdseCd.getValue());
//                bean.setQty(targetPiCountDetailTmsg.adjVarQty.getValue());
//                NLXC001001GetInventoryItemCostBean retNLXC001001GetInventoryItemCostBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
//                BigDecimal adjVarAmt = BigDecimal.ZERO;
//                if (retNLXC001001GetInventoryItemCostBean.getErrList().isEmpty()) {
//                    adjVarAmt = retNLXC001001GetInventoryItemCostBean.getTotPrcAmt();
//                }
//                ZYPEZDItemValueSetter.setValue(targetPiCountDetailTmsg.adjVarAmt, adjVarAmt);
//
//                EZDTBLAccessor.insert(targetPiCountDetailTmsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(targetPiCountDetailTmsg.getReturnCode())) {
//                    throw new S21AbendException(MSG_ID_NLGM0007E, new String[] {TBL_ID_PHYS_INVTY_CNT_HDR //
//                            , COL_NM_PHYS_INVTY_CNT_DTL_PK
//                            , targetPiCountDetailTmsg.physInvtyCntDtlPk.getValue().toPlainString() });
//                }
//            }
//
//            // NLZC0630(Tech-PI Entry Close API)
//            NLZC063001PMsg dNLZC063001PMsg = new NLZC063001PMsg();
//            ZYPEZDItemValueSetter.setValue(dNLZC063001PMsg.glblCmpyCd , glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(dNLZC063001PMsg.salesDate, ZYPDateUtil.getBatProcDate());
//            String physInvtyCntStsCd = BLANK;
//            if (beforeAdjustRec) {
//                physInvtyCntStsCd = PHYS_INVTY_CNT_STS.PI_VARIANCE;
//            } else {
//                physInvtyCntStsCd = PHYS_INVTY_CNT_STS.PI_COMPLETED;
//            }
//            ZYPEZDItemValueSetter.setValue(dNLZC063001PMsg.physInvtyCntStsCd, physInvtyCntStsCd);
//            ZYPEZDItemValueSetter.setValue(dNLZC063001PMsg.physInvtyNum, piNum);
//
//            if (executePiCloseApi(dNLZC063001PMsg)) {
//                updatePhysInvtyCtrlCount++;
//            } else {
//                return false;
//            }
//
//            // 
//            piCountHeaderMap.clear();
//            piCtrlMap.clear();
//            piCountDetailMap.clear();
//
//            return true;
//        }

        /**
         * Execution processing of Tech-PI Entry Close API
         * @param pmsgList List<NLZC004001PMsg>
         * @param locNm Location Name
         * @return processing result
         */
        private boolean executePiCloseApi(NLZC063001PMsg pmsg) {

            boolean returnFlg = true;

            // Tech-PI Entry Close API is executed
            NLZC063001 nlzc0630 = new NLZC063001();
            nlzc0630.execute(pmsg, ONBATCH_TYPE.BATCH);

            // Error judgment by message ID
            List<String> msgList = S21ApiUtil.getXxMsgIdList(pmsg);
            if (!msgList.isEmpty()) {
                for (String xxMsgId : msgList) {
                    setApiErrorInfo(API_ID_NLZC0630, xxMsgId);
                }
                S21InfoLogOutput.println(API_ID_NLZC0630 + MSG_ID_NLCM0047E + ", PMsg : " + pmsg.toString());
                returnFlg = false;
            }

            //
            return returnFlg;
        }

//        /**
//         * check for Adjustment Order API is callable
//         * @param rs ResultSet
//         * @param nlzc004001PMsgList List<NLZC004001PMsg> 
//         * @return  boolean (true : callable, false : not callable)
//         */
//        private boolean isAdjustmentApiCallable(ResultSet rs, List<NLZC004001PMsg> nlzc004001PMsgList) throws SQLException {
//
//            boolean ret = false;
//            boolean isSetSerial = false;
//
//            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_NM_RCV_SER_TAKE_FLG))) {
//                isSetSerial = true;
//            }
//
//            NLZC004001PMsg targetNLZC004001PMsg = null;
//            boolean existFlg = false;
//            String xxRqstTpCd = "";
//            if (STR_MINUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                xxRqstTpCd = NLZC004001Constant.RQST_STOCK_IN;
//            } else if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                xxRqstTpCd = NLZC004001Constant.RQST_STOCK_OUT;
//            }
//            BigDecimal svcConfigMstrPk = null;
//            if (rs.getString(COL_NM_CONFIG_MSTR_NUM) != null) {
//                svcConfigMstrPk = new BigDecimal(rs.getString(COL_NM_CONFIG_MSTR_NUM));
//            }
//            for (int idx = 1; idx < nlzc004001PMsgList.size(); idx++) {
//                targetNLZC004001PMsg = nlzc004001PMsgList.get(idx);
//                if (targetNLZC004001PMsg.invtyLocCd.getValue().equals(rs.getString(COL_NM_INVTY_LOC_CD))
//                    && targetNLZC004001PMsg.mdseCd.getValue().equals(rs.getString(COL_NM_CODE_ITEM_IF))
//                    && targetNLZC004001PMsg.stkStsCd.getValue().equals(rs.getString(COL_NM_CODE_STAT_STOCK_IF))
//                    && targetNLZC004001PMsg.xxRqstTpCd.getValue().equals(xxRqstTpCd)
//                    && equalValue(targetNLZC004001PMsg.svcConfigMstrPk.getValue(), svcConfigMstrPk)
//                ) {
//                    if (isSetSerial) { // add serial
//                        if (targetNLZC004001PMsg.serialInfoList.getValidCount() >= SERIAL_MAX_CNT) {
//                            continue;
//                        } else {
//                            ret = false; // add serial to existing line
//                        }
//                    }
//                    existFlg = true;
//                }
//            }
//            if (!existFlg) { // no existing line
//                ret = true;
//            }
//
//            return ret;
//        }


        private PHYS_INVTY_CNT_HDRTMsg createAdjustmentHeaderData(ResultSet rs)  throws SQLException {
            PHYS_INVTY_CNT_HDRTMsg headerPiMsg = new PHYS_INVTY_CNT_HDRTMsg();
            BigDecimal physInvtyCntHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_HDR_SQ);
            ZYPEZDItemValueSetter.setValue(headerPiMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(headerPiMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
            ZYPEZDItemValueSetter.setValue(headerPiMsg.physInvtyCtrlPk, rs.getBigDecimal(COL_NM_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.physInvtyCtrlNm, rs.getString(COL_NM_PHYS_INVTY_CTRL_NM));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.physInvtyDt, rs.getString(COL_NM_PHYS_INVTY_DT));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.whCd, rs.getString(COL_NM_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.rtlWhCd, rs.getString(COL_NM_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.rtlSwhCd, rs.getString(COL_NM_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.mdseCd, rs.getString(COL_NM_CODE_ITEM_IF));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.stkStsCd, rs.getString(COL_NM_CODE_STAT_STOCK_IF));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.sysCntRegdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(headerPiMsg.physInvtyAdjNm, rs.getString(COL_NM_PHYS_INVTY_ADJ_NM));
            return headerPiMsg;
        }

        private PHYS_INVTY_CNT_DTLTMsg createAdjustmentDetailData(BigDecimal piHeaderPk, ResultSet rs)  throws SQLException {
            PHYS_INVTY_CNT_DTLTMsg dtlPiMsg = new PHYS_INVTY_CNT_DTLTMsg();
            BigDecimal physInvtyCntDtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_DTL_SQ);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.physInvtyCntDtlPk, physInvtyCntDtlSq);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.physInvtyCntHdrPk, piHeaderPk);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.physInvtyCtrlPk, rs.getBigDecimal(COL_NM_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.rtlWhCd, rs.getString(COL_NM_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.rtlSwhCd, rs.getString(COL_NM_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.mdseCd, rs.getString(COL_NM_CODE_ITEM_IF));
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.serNum, rs.getString(COL_NM_SER_NUM));

            BigDecimal sign = BigDecimal.ONE;
            if (rs.getString(COL_NM_SIGN_IF).equals(STR_PLUS)) {
                sign = ADJUSTMENT_SIGN;
            } else {
                sign = BigDecimal.ONE;
            }
            BigDecimal countNum = rs.getBigDecimal(COL_NM_INVTY_QTY).add(rs.getBigDecimal(COL_NM_QTY_ADJUST_IF)).multiply(sign);

            ZYPEZDItemValueSetter.setValue(dtlPiMsg.firstCntInpQty, countNum);

            String currentTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");

            ZYPEZDItemValueSetter.setValue(dtlPiMsg.firstCntInpTs, currentTimeStamp);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.ltstCntInpQty, countNum);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.ltstCntInpTs, currentTimeStamp);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.invtyAvalQty, rs.getBigDecimal(COL_NM_INVTY_QTY));
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.invtyRegdTs, currentTimeStamp);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.adjVarQty, rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).multiply(sign));

            // NLXC0010 get Inventory Item Cost
            NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();
            bean.setGlblCmpyCd(glblCmpyCd);
            bean.setInvtyLocCd(rs.getString(COL_NM_INVTY_LOC_CD));
            bean.setMdseCd(rs.getString(COL_NM_CODE_ITEM_IF));
            bean.setQty(rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).multiply(sign));
            NLXC001001GetInventoryItemCostBean retNLXC001001GetInventoryItemCostBean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);
            BigDecimal adjVarAmt = BigDecimal.ZERO;
            if (retNLXC001001GetInventoryItemCostBean.getErrList().isEmpty()) {
                adjVarAmt = retNLXC001001GetInventoryItemCostBean.getTotPrcAmt();
            }
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.adjVarAmt, adjVarAmt);
            if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_CONFIG_MSTR_NUM))) {
                ZYPEZDItemValueSetter.setValue(dtlPiMsg.svcConfigMstrPk, new BigDecimal(rs.getString(COL_NM_CONFIG_MSTR_NUM)));
            }
            return dtlPiMsg;
        }
//        /**
//         * Execution processing of Adjustment API and Work Object
//         * @param rs ResultSet
//         * @param nlzc004001PMsgList List<NLZC004001PMsg> 
//         * @param nlzc004001PMsgMap Map<String, NLZC004001PMsg>
//         */
//        private void createAdjustmentApiParam(ResultSet rs, List<NLZC004001PMsg> nlzc004001PMsgList, Map<String, NLZC004001PMsg> nlzc004001PMsgMap) throws SQLException {
//
//            boolean isSetSerial = false;
//            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_NM_RCV_SER_TAKE_FLG))) {
//                isSetSerial = true;
//            }
//
//            String configMstrNum = "";
//            if (rs.getString(COL_NM_CONFIG_MSTR_NUM) != null) {
//                configMstrNum = rs.getString(COL_NM_CONFIG_MSTR_NUM);
//            }
//            // Key : PI#/RWH/SWH/MDSE/StockStatus/Sign/ConfigMasterNumber  (Detail)
//            String piAdjustApiKey = rs.getString(COL_NM_PHYS_INVTY_NUM)
//                                    + KEY_INFO_DELIM + rs.getString(COL_NM_RTL_WH_CD)
//                                    + KEY_INFO_DELIM + rs.getString(COL_NM_RTL_SWH_CD)
//                                    + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_ITEM_IF)
//                                    + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_STAT_STOCK_IF)
//                                    + KEY_INFO_DELIM + rs.getString(COL_NM_SIGN_IF)
//                                    + KEY_INFO_DELIM + configMstrNum;
//
//            BigDecimal currentOrdQty = BigDecimal.ZERO;
//            NLZC004001PMsg targetNLZC004001PMsg = null;
//
//            // Create Adjustment Header Parameter And Detail Parameter
//            if (nlzc004001PMsgList.isEmpty()) {
//
//                nlzc004001PMsgList.add(createHeaderParam(rs.getString(COL_NM_RTL_WH_CD), rs.getString(COL_NM_PHYS_INVTY_ADJ_NM)));
//
//                if (STR_MINUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                    currentOrdQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF);
//                } else if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                    currentOrdQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).negate();
//                }
//
//                NLZC004001PMsg currentDetailParam = createDetailParam(currentOrdQty, rs.getString(COL_NM_INVTY_LOC_CD), rs.getString(COL_NM_CODE_ITEM_IF),
//                        rs.getString(COL_NM_CODE_STAT_STOCK_IF), rs.getString(COL_NM_PHYS_INVTY_ADJ_NM), rs.getString(COL_NM_CONFIG_MSTR_NUM));
//                if (isSetSerial) {
//                    // Set Serial#
//                    currentDetailParam.serialInfoList.setValidCount(1);
//                    ZYPEZDItemValueSetter.setValue(currentDetailParam.serialInfoList.no(0).serNum, rs.getString(COL_NM_SER_NUM));
//                }
//                nlzc004001PMsgList.add(currentDetailParam);
//                nlzc004001PMsgMap.put(piAdjustApiKey, currentDetailParam);
//
//                return;
//            }
//
//            boolean existFlg = false;
//            String xxRqstTpCd = "";
//            if (STR_MINUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                xxRqstTpCd = NLZC004001Constant.RQST_STOCK_IN;
//            } else if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                xxRqstTpCd = NLZC004001Constant.RQST_STOCK_OUT;
//            }
//            BigDecimal svcConfigMstrPk = null;
//            if (rs.getString(COL_NM_CONFIG_MSTR_NUM) != null) {
//                svcConfigMstrPk = new BigDecimal(rs.getString(COL_NM_CONFIG_MSTR_NUM));
//            }
//            for (int idx = 1; idx < nlzc004001PMsgList.size(); idx++) { // 
//                targetNLZC004001PMsg = nlzc004001PMsgList.get(idx);
//                if (targetNLZC004001PMsg.invtyLocCd.getValue().equals(rs.getString(COL_NM_INVTY_LOC_CD))
//                    && targetNLZC004001PMsg.mdseCd.getValue().equals(rs.getString(COL_NM_CODE_ITEM_IF))
//                    && targetNLZC004001PMsg.stkStsCd.getValue().equals(rs.getString(COL_NM_CODE_STAT_STOCK_IF))
//                    && targetNLZC004001PMsg.xxRqstTpCd.getValue().equals(xxRqstTpCd)
//                    && equalValue(targetNLZC004001PMsg.svcConfigMstrPk.getValue(), svcConfigMstrPk)
//                ) {
//                    // 2016/04/20 N.Akaishi [V1.0 Mod] Start
//                    if (targetNLZC004001PMsg.serialInfoList.getValidCount() >= SERIAL_MAX_CNT) {
//                        continue; // New Detail Line
//                    }
//
//                    BigDecimal addValue = BigDecimal.ZERO;
//                    currentOrdQty = targetNLZC004001PMsg.ordQty.getValue();
//                    if (STR_MINUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                        addValue = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF);
//                    } else if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                        addValue = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).negate();
//                    }
//                    ZYPEZDItemValueSetter.setValue(targetNLZC004001PMsg.ordQty, currentOrdQty.add(addValue));
//
//                    if (isSetSerial) {
//                        // Set Serial#
//                        targetNLZC004001PMsg.serialInfoList.setValidCount(targetNLZC004001PMsg.serialInfoList.getValidCount() + 1);
//                        int serIdx = targetNLZC004001PMsg.serialInfoList.getValidCount() - 1;
//                        ZYPEZDItemValueSetter.setValue(targetNLZC004001PMsg.serialInfoList.no(serIdx).serNum, rs.getString(COL_NM_SER_NUM));
//                    }
//                    nlzc004001PMsgList.set(idx, targetNLZC004001PMsg);
//                    nlzc004001PMsgMap.put(piAdjustApiKey, targetNLZC004001PMsg);
//                    existFlg = true;
//                }
//            }
//            if (!existFlg) {
//                if (STR_MINUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                    currentOrdQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF);
//                } else if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                    currentOrdQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).negate();
//                }
//                NLZC004001PMsg currentDetailParam = createDetailParam(currentOrdQty, rs.getString(COL_NM_INVTY_LOC_CD), rs.getString(COL_NM_CODE_ITEM_IF),
//                        rs.getString(COL_NM_CODE_STAT_STOCK_IF), rs.getString(COL_NM_PHYS_INVTY_ADJ_NM), rs.getString(COL_NM_CONFIG_MSTR_NUM));
//                if (isSetSerial) {
//                    // Set Serial#
//                    currentDetailParam.serialInfoList.setValidCount(1);
//                    ZYPEZDItemValueSetter.setValue(currentDetailParam.serialInfoList.no(0).serNum, rs.getString(COL_NM_SER_NUM));
//                }
//                nlzc004001PMsgList.add(currentDetailParam);
//                nlzc004001PMsgMap.put(piAdjustApiKey, currentDetailParam);
//            }
//
//        }

//        /**
//         * Execution processing of Work Object(PHYS_INVTY_ADJ , PHYS_INVTY_CNT_HDR)
//         * @param rs ResultSet
//         * @param piAdjustMap Map<String, PHYS_INVTY_ADJTMsg> 
//         * @param piCountHeaderMap Map<String, PHYS_INVTY_CNT_HDRTMsg>
//         * @param piCountDetailMap Map<String, PHYS_INVTY_CNT_DTLTMsg>
//         */
//        private void createPhysInvtyWorkObject(ResultSet rs, Map<String, PHYS_INVTY_ADJTMsg> piAdjustMap, Map<String, PHYS_INVTY_CNT_HDRTMsg> piCountHeaderMap, Map<String, PHYS_INVTY_CNT_DTLTMsg> piCountDetailMap) throws SQLException {
//
//            String configMstrNum = "";
//            BigDecimal svcConfigMstrPk = null;
//            if (rs.getString(COL_NM_CONFIG_MSTR_NUM) != null) {
//                configMstrNum = rs.getString(COL_NM_CONFIG_MSTR_NUM);
//                svcConfigMstrPk = new BigDecimal(configMstrNum);
//            }
//
//            BigDecimal adjVarQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF);
//            if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                adjVarQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).negate();
//            } else if (STR_MINUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                adjVarQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF);
//            }
//
//            PHYS_INVTY_ADJTMsg piAdjustTMsg = new PHYS_INVTY_ADJTMsg();
//            // create map key(PHYS_INVTY_ADJ) : PI#/WH Code/MDSE Code/Stock Status Code/Sign/Config Master Number/Serial#
//            String piAdjustMapKey = rs.getString(COL_NM_PHYS_INVTY_NUM)
//                                    + KEY_INFO_DELIM + rs.getString(COL_NM_INVTY_LOC_CD)
//                                    + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_ITEM_IF)
//                                    + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_STAT_STOCK_IF)
//                                    + KEY_INFO_DELIM + rs.getString(COL_NM_SIGN_IF)
//                                    + KEY_INFO_DELIM + configMstrNum;
//            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_NM_RCV_SER_TAKE_FLG))) {
//                piAdjustMapKey += KEY_INFO_DELIM + rs.getString(COL_NM_SER_NUM);
//            } else {
//                piAdjustMapKey += KEY_INFO_DELIM + "";
//            }
//
//            if (piAdjustMap.containsKey(piAdjustMapKey)) {
//                piAdjustTMsg = piAdjustMap.get(piAdjustMapKey);
//            } else {
//                ZYPEZDItemValueSetter.setValue(piAdjustTMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(piAdjustTMsg.physInvtyCtrlPk, rs.getBigDecimal(COL_NM_PHYS_INVTY_CTRL_PK));
//                ZYPEZDItemValueSetter.setValue(piAdjustTMsg.whCd, rs.getString(COL_NM_INVTY_LOC_CD));
//                ZYPEZDItemValueSetter.setValue(piAdjustTMsg.mdseCd, rs.getString(COL_NM_CODE_ITEM_IF));
//                ZYPEZDItemValueSetter.setValue(piAdjustTMsg.stkStsCd, rs.getString(COL_NM_CODE_STAT_STOCK_IF));
//                ZYPEZDItemValueSetter.setValue(piAdjustTMsg.adjVarQty, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(piAdjustTMsg.adjVarSgnCd, rs.getString(COL_NM_SIGN_IF));
//                ZYPEZDItemValueSetter.setValue(piAdjustTMsg.physInvtyAdjNm, rs.getString(COL_NM_PHYS_INVTY_ADJ_NM));
//                if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_NM_RCV_SER_TAKE_FLG))) {
//                    ZYPEZDItemValueSetter.setValue(piAdjustTMsg.serNum, rs.getString(COL_NM_SER_NUM));
//                }
//                ZYPEZDItemValueSetter.setValue(piAdjustTMsg.svcConfigMstrPk, svcConfigMstrPk);
//            }
//            ZYPEZDItemValueSetter.setValue(piAdjustTMsg.adjVarQty, piAdjustTMsg.adjVarQty.getValue().add(adjVarQty));
//            piAdjustMap.put(piAdjustMapKey, piAdjustTMsg);
//
//            PHYS_INVTY_CNT_HDRTMsg piCountHeaderTMsg = new PHYS_INVTY_CNT_HDRTMsg();
//            // create map key(PHYS_INVTY_CNT_HDR) : PI#/WH Code/MDSE Code/Stock Status Code
//            String piCountHeaderMapKey = rs.getString(COL_NM_PHYS_INVTY_NUM)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_INVTY_LOC_CD)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_ITEM_IF)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_STAT_STOCK_IF);
//
//            if (piCountHeaderMap.containsKey(piCountHeaderMapKey)) {
//                piCountHeaderTMsg = (PHYS_INVTY_CNT_HDRTMsg) piCountHeaderMap.get(piCountHeaderMapKey);
//            } else {
//                BigDecimal physInvtyCntHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_HDR_SQ);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyCtrlPk, rs.getBigDecimal(COL_NM_PHYS_INVTY_CTRL_PK));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyCtrlNm, rs.getString(COL_NM_PHYS_INVTY_CTRL_NM));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyDt, rs.getString(COL_NM_PHYS_INVTY_DT));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.whCd, rs.getString(COL_NM_INVTY_LOC_CD));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.rtlWhCd, rs.getString(COL_NM_RTL_WH_CD));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.rtlSwhCd, rs.getString(COL_NM_RTL_SWH_CD));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.mdseCd, rs.getString(COL_NM_CODE_ITEM_IF));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.stkStsCd, rs.getString(COL_NM_CODE_STAT_STOCK_IF));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.sysCntRegdFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.invtyAvalQty, rs.getBigDecimal(COL_NM_INVTY_QTY));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.invtyRegdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.adjVarQty, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyAdjNm, rs.getString(COL_NM_PHYS_INVTY_ADJ_NM));
//            }
//            ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.adjVarQty, piCountHeaderTMsg.adjVarQty.getValue().add(adjVarQty));
//            piCountHeaderMap.put(piCountHeaderMapKey, piCountHeaderTMsg);
//
//            PHYS_INVTY_CNT_DTLTMsg piCountDetailTMsg = new PHYS_INVTY_CNT_DTLTMsg();
//            // create map key(PHYS_INVTY_CNT_DTL) : PI#/WH Code/MDSE Code/Stock Status Code/Sign/Config Master Number/Serial#
//            String piCountDetailMapKey = rs.getString(COL_NM_PHYS_INVTY_NUM)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_INVTY_LOC_CD)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_ITEM_IF)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_STAT_STOCK_IF)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_SIGN_IF)
//                                       + KEY_INFO_DELIM + configMstrNum;
//            if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_SER_NUM))) {
//                piCountDetailMapKey += KEY_INFO_DELIM + rs.getString(COL_NM_SER_NUM);
//            } else {
//                piCountDetailMapKey += KEY_INFO_DELIM + "";
//            }
//
//            BigDecimal physInvtyCntDtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_DTL_SQ);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.physInvtyCntDtlPk, physInvtyCntDtlSq);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.physInvtyCntHdrPk, piCountHeaderTMsg.physInvtyCntHdrPk);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.physInvtyCtrlPk, rs.getBigDecimal(COL_NM_PHYS_INVTY_CTRL_PK));
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.rtlWhCd, rs.getString(COL_NM_RTL_WH_CD));
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.rtlSwhCd, rs.getString(COL_NM_RTL_SWH_CD));
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.mdseCd, rs.getString(COL_NM_CODE_ITEM_IF));
//            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_NM_RCV_SER_TAKE_FLG))) {
//                ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.serNum, rs.getString(COL_NM_SER_NUM));
//            }
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.invtyRegdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.adjVarQty, adjVarQty);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.physInvtyAdjStsCd, PHYS_INVTY_ADJ_STS.NONE);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.svcConfigMstrPk, svcConfigMstrPk);
//            piCountDetailMap.put(piCountDetailMapKey, piCountDetailTMsg);
//        }

//        /**
//         * Execution processing of Work Object(PHYS_INVTY_ADJ , PHYS_INVTY_CNT_HDR) for skip record
//         * @param rs ResultSet
//         * @param piAdjustMap Map<String, PHYS_INVTY_ADJTMsg> 
//         * @param piCountHeaderMap Map<String, PHYS_INVTY_CNT_HDRTMsg>
//         * @param piCountDetailMap Map<String, PHYS_INVTY_CNT_DTLTMsg>
//         */
//        private void createPhysInvtyWorkObjectForSkip(ResultSet rs, Map<String, PHYS_INVTY_ADJTMsg> piAdjustMap, Map<String, PHYS_INVTY_CNT_HDRTMsg> piCountHeaderMap, Map<String, PHYS_INVTY_CNT_DTLTMsg> piCountDetailMap) throws SQLException {
//
//            String configMstrNum = "";
//            BigDecimal svcConfigMstrPk = null;
//            if (rs.getString(COL_NM_CONFIG_MSTR_NUM) != null) {
//                configMstrNum = rs.getString(COL_NM_CONFIG_MSTR_NUM);
//                svcConfigMstrPk = new BigDecimal(configMstrNum);
//            }
//
//            BigDecimal adjVarQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF);
//            if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                adjVarQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).negate();
//            } else if (STR_MINUS.equals(rs.getString(COL_NM_SIGN_IF))) {
//                adjVarQty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF);
//            }
//
//            PHYS_INVTY_CNT_HDRTMsg piCountHeaderTMsg = new PHYS_INVTY_CNT_HDRTMsg();
//            // create map key(PHYS_INVTY_CNT_HDR) : PI#/WH Code/MDSE Code/Stock Status Code
//            String piCountHeaderMapKey = rs.getString(COL_NM_PHYS_INVTY_NUM)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_INVTY_LOC_CD)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_ITEM_IF)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_STAT_STOCK_IF);
//
//            if (piCountHeaderMap.containsKey(piCountHeaderMapKey)) {
//                piCountHeaderTMsg = (PHYS_INVTY_CNT_HDRTMsg) piCountHeaderMap.get(piCountHeaderMapKey);
//            } else {
//                BigDecimal physInvtyCntHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_HDR_SQ);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyCtrlPk, rs.getBigDecimal(COL_NM_PHYS_INVTY_CTRL_PK));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyCtrlNm, rs.getString(COL_NM_PHYS_INVTY_CTRL_NM));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyDt, rs.getString(COL_NM_PHYS_INVTY_DT));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.whCd, rs.getString(COL_NM_INVTY_LOC_CD));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.rtlWhCd, rs.getString(COL_NM_RTL_WH_CD));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.rtlSwhCd, rs.getString(COL_NM_RTL_SWH_CD));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.mdseCd, rs.getString(COL_NM_CODE_ITEM_IF));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.stkStsCd, rs.getString(COL_NM_CODE_STAT_STOCK_IF));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.sysCntRegdFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.invtyAvalQty, rs.getBigDecimal(COL_NM_INVTY_QTY));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.invtyRegdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.adjVarQty, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(piCountHeaderTMsg.physInvtyAdjNm, rs.getString(COL_NM_PHYS_INVTY_ADJ_NM));
//            }
//            piCountHeaderMap.put(piCountHeaderMapKey, piCountHeaderTMsg);
//
//            PHYS_INVTY_CNT_DTLTMsg piCountDetailTMsg = new PHYS_INVTY_CNT_DTLTMsg();
//            // create map key(PHYS_INVTY_CNT_DTL) : PI#/WH Code/MDSE Code/Stock Status Code/Sign/Config Master Number/Serial#
//            String piCountDetailMapKey = rs.getString(COL_NM_PHYS_INVTY_NUM)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_INVTY_LOC_CD)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_ITEM_IF)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_CODE_STAT_STOCK_IF)
//                                       + KEY_INFO_DELIM + rs.getString(COL_NM_SIGN_IF)
//                                       + KEY_INFO_DELIM + configMstrNum;
//            if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_SER_NUM))) {
//                piCountDetailMapKey += KEY_INFO_DELIM + rs.getString(COL_NM_SER_NUM);
//            } else {
//                piCountDetailMapKey += KEY_INFO_DELIM + "";
//            }
//
//            BigDecimal physInvtyCntDtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_DTL_SQ);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.physInvtyCntDtlPk, physInvtyCntDtlSq);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.physInvtyCntHdrPk, piCountHeaderTMsg.physInvtyCntHdrPk);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.physInvtyCtrlPk, rs.getBigDecimal(COL_NM_PHYS_INVTY_CTRL_PK));
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.rtlWhCd, rs.getString(COL_NM_RTL_WH_CD));
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.rtlSwhCd, rs.getString(COL_NM_RTL_SWH_CD));
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.mdseCd, rs.getString(COL_NM_CODE_ITEM_IF));
//            if (ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_NM_RCV_SER_TAKE_FLG))) {
//                ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.serNum, rs.getString(COL_NM_SER_NUM));
//            }
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.invtyRegdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.adjVarQty, adjVarQty);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.physInvtyAdjStsCd, PHYS_INVTY_ADJ_STS.SKIP_ADJUSTMENT_DUE_TO_ERROR);
//            ZYPEZDItemValueSetter.setValue(piCountDetailTMsg.svcConfigMstrPk, svcConfigMstrPk);
//            piCountDetailMap.put(piCountDetailMapKey, piCountDetailTMsg);
//        }

//        /**
//         * Header Param
//         * @param String rtlWhCd
//         * @param String physInvtyAdjNm
//         * @return NLZC004001PMsg
//         */
//        private NLZC004001PMsg createHeaderParam(String rtlWhCd, String physInvtyAdjNm) {
//            NLZC004001PMsg pMsg = new NLZC004001PMsg();
//            ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
//            ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, NLZC004001Constant.DT_TP_HDR);
//            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, batProcDate);
//            ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
//            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, rtlWhCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
//            ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
//            ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
//            ZYPEZDItemValueSetter.setValue(pMsg.adjTrxTpCd, ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT);
//            ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm);
//            return pMsg;
//        }

//        /**
//         * Detail Param
//         * @param BigDecimal adjVarQty
//         * @param String invtyLocCd
//         * @param String mdseCd
//         * @param String stkStsCd
//         * @param String physInvtyAdjNm
//         * @param String configMstrNum
//         * @return NLZC004001PMsg
//         */
//        private NLZC004001PMsg createDetailParam(BigDecimal adjVarQty, String invtyLocCd, String mdseCd, String stkStsCd, String physInvtyAdjNm, String configMstrNum) {
//            NLZC004001PMsg pMsg = new NLZC004001PMsg();
//            ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
//            ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, NLZC004001Constant.DT_TP_DTL);
//            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, batProcDate);
//            if (adjVarQty.compareTo(BigDecimal.ZERO) == 1) {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_IN);
//            } else {
//                ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_OUT);
//            }
//            ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
//            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, invtyLocCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
//            ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm);
//            ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
//            ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
//            ZYPEZDItemValueSetter.setValue(pMsg.adjTrxTpCd, ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT);
//            ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, stkStsCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd_D1, invtyLocCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.locStsCd_D1, LOC_STS.DC_STOCK);
//            ZYPEZDItemValueSetter.setValue(pMsg.ordQty, adjVarQty);
//            if (configMstrNum != null) {
//                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, new BigDecimal(configMstrNum));
//            }
//            return pMsg;
//        }

//        /**
//         * <pre>
//         * add Pi Adjust
//         * @param nlzc004001PMsgList List<NLZC004001PMsg>
//         * @param piAdjustMap Map<String, PHYS_INVTY_ADJTMsg> 
//         * @param piCountHeaderMap Map<String, PHYS_INVTY_CNT_HDRTMsg> 
//         * @throws SQLException
//         * </pre>
//         */
//        private void addPiAdjust(List<NLZC004001PMsg> nlzc004001PMsgList, Map<String, PHYS_INVTY_ADJTMsg> piAdjustMap, Map<String, PHYS_INVTY_CNT_HDRTMsg> piCountHeaderMap) throws SQLException {
//
//            PHYS_INVTY_ADJTMsg targetPhysInvtyAdjTmsg = null;
//
//            String targetPiNum = EMPTY;
//            String targetInvtyLocCd = EMPTY;
//            String targetMdseCd = EMPTY;
//            String targetStkStsCd = EMPTY;
//            String targetSerNum = EMPTY;
//            String targetSignIf = EMPTY;
//            String targetXxRqstTpCd = EMPTY;
//            String invtyOrdNum = EMPTY;
//            String targetConfigMstrNum = EMPTY;
//
//            Set<String> piAdjustMapKeySet = piAdjustMap.keySet();
//            for (String key : piAdjustMapKeySet) {
//                String[] piAdjustKeyList = key.split("\\/", -1);
//
//                // PI#/WH Code/MDSE Code/Stock Status Code/Sign/Config Master Number/Serial#
//                targetPiNum = piAdjustKeyList[IDX_0];
//                targetInvtyLocCd = piAdjustKeyList[IDX_1];
//                targetMdseCd = piAdjustKeyList[IDX_2];
//                targetStkStsCd = piAdjustKeyList[IDX_3];
//                targetSignIf = piAdjustKeyList[IDX_4];
//                targetConfigMstrNum = piAdjustKeyList[IDX_5];
//                targetSerNum = piAdjustKeyList[IDX_6];
//
//                if (STR_MINUS.equals(targetSignIf)) {
//                    targetXxRqstTpCd = NLZC004001Constant.RQST_STOCK_IN;
//                } else if (STR_PLUS.equals(targetSignIf)) {
//                    targetXxRqstTpCd = NLZC004001Constant.RQST_STOCK_OUT;
//                }
//
//                BigDecimal targetSvcConfigMstrPk = null;
//                if (!targetConfigMstrNum.equals(EMPTY)) {
//                    targetSvcConfigMstrPk = new BigDecimal(targetConfigMstrNum);
//                }
//
//                // check existing Adjust Serial
//                for (NLZC004001PMsg nlzc004001PMsg : nlzc004001PMsgList) {
//                    if (!NLZC004001Constant.DT_TP_DTL.equals(nlzc004001PMsg.xxDtTpCd.getValue())) {
//                        continue;
//                    }
//                    if (!targetInvtyLocCd.equals(nlzc004001PMsg.invtyLocCd_D1.getValue())
//                            || !targetMdseCd.equals(nlzc004001PMsg.mdseCd.getValue())
//                            || !targetStkStsCd.equals(nlzc004001PMsg.stkStsCd.getValue())
//                            || !targetXxRqstTpCd.equals(nlzc004001PMsg.xxRqstTpCd.getValue())
//                            || !equalValue(targetSvcConfigMstrPk, nlzc004001PMsg.svcConfigMstrPk.getValue())
//                            ) {
//                        continue;
//                    }
//                    if (ZYPCommonFunc.hasValue(targetSerNum)) { // serial
//                        boolean isAdjustSerial = false;
//                        for (int idx = 0; idx < nlzc004001PMsg.serialInfoList.getValidCount(); idx++) {
//                            if (targetSerNum.equals(nlzc004001PMsg.serialInfoList.no(idx))) { // serial adjust
//                                if (ZYPCommonFunc.hasValue(nlzc004001PMsg.invtyOrdNum)) {
//                                    invtyOrdNum = nlzc004001PMsg.invtyOrdNum.getValue();
//                                }
//                                isAdjustSerial = true;
//                                break;
//                            }
//                        }
//                        if (!isAdjustSerial) {
//                            //  serial validation is error, but qty adjust
//                            if (ZYPCommonFunc.hasValue(nlzc004001PMsg.invtyOrdNum)) {
//                                invtyOrdNum = nlzc004001PMsg.invtyOrdNum.getValue();
//                            }
//                        }
//                    } else { // non serial
//                        invtyOrdNum = nlzc004001PMsg.invtyOrdNum.getValue();
//                    }
//                }
//
//                targetPhysInvtyAdjTmsg = piAdjustMap.get(key);
//                if (targetPhysInvtyAdjTmsg != null) {
//                    insertPhysInvtyAdj(targetPhysInvtyAdjTmsg, invtyOrdNum);
//                }
//
//                // create map key(PHYS_INVTY_CNT_HDR) : PI#/WH Code/MDSE Code/Stock Status Code
//                String piCountHeaderMapKey = targetPiNum
//                                           + KEY_INFO_DELIM + targetInvtyLocCd
//                                           + KEY_INFO_DELIM + targetMdseCd
//                                           + KEY_INFO_DELIM + targetStkStsCd;
//                PHYS_INVTY_CNT_HDRTMsg targetPiCountHeaderTmsg = piCountHeaderMap.get(piCountHeaderMapKey);
//                if (targetPiCountHeaderTmsg != null) {
//                    ZYPEZDItemValueSetter.setValue(targetPiCountHeaderTmsg.invtyOrdNum, invtyOrdNum);
//                }
//            }
//        }

//        /**
//         * <pre>
//         * insert PHYS_INVTY_ADJ
//         * @param targetPhysInvtyAdjTmsg PHYS_INVTY_ADJTMsg
//         * @param invtyOrdNum String
//         * @throws SQLException
//         * </pre>
//         */
//        private void insertPhysInvtyAdj(PHYS_INVTY_ADJTMsg targetPhysInvtyAdjTmsg, String invtyOrdNum) throws SQLException {
//            PHYS_INVTY_ADJTMsg insPhysInvtyAdjTmsg = new PHYS_INVTY_ADJTMsg();
//            BigDecimal physPhysInvtyAdjSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_ADJ_SQ);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.glblCmpyCd, targetPhysInvtyAdjTmsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.physInvtyAdjPk, physPhysInvtyAdjSq);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.physInvtyCtrlPk, targetPhysInvtyAdjTmsg.physInvtyCtrlPk);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.whCd, targetPhysInvtyAdjTmsg.whCd);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.mdseCd, targetPhysInvtyAdjTmsg.mdseCd);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.stkStsCd, targetPhysInvtyAdjTmsg.stkStsCd);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.adjVarQty, targetPhysInvtyAdjTmsg.adjVarQty);
//            BigDecimal adjVarQty = insPhysInvtyAdjTmsg.adjVarQty.getValue();
//            if (adjVarQty.compareTo(BigDecimal.ZERO) == 1) {
//                ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.adjVarSgnCd, STR_PLUS);
//            } else {
//                ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.adjVarSgnCd, STR_MINUS);
//            }
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.serNum, targetPhysInvtyAdjTmsg.serNum);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.invtyOrdNum, invtyOrdNum);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.physInvtyAdjNm, targetPhysInvtyAdjTmsg.physInvtyAdjNm);
//            ZYPEZDItemValueSetter.setValue(insPhysInvtyAdjTmsg.svcConfigMstrPk, targetPhysInvtyAdjTmsg.svcConfigMstrPk);
//            EZDTBLAccessor.insert(insPhysInvtyAdjTmsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insPhysInvtyAdjTmsg.getReturnCode())) {
//                throw new S21AbendException(MSG_ID_NLGM0007E, new String[] {TBL_ID_PHYS_INVTY_AJT
//                        , COL_NM_PHYS_INVTY_ADJ_PK
//                        , insPhysInvtyAdjTmsg.physInvtyAdjPk.getValue().toPlainString() });
//            }
//            insertPhysInvtyAdjCount++;
//        }

        /**
         * <pre>
         * Set Error Mail Info
         * @param msgId String
         * @param params String[]
         * @param rs ResultSet
         * @throws SQLException
         * </pre>
         */
        private void setErrorInfo(String msgId, String[] params, ResultSet rs) throws SQLException {
            String errorMsg = S21MessageFunc.clspGetMessage(msgId, params);
            String serNum = rs.getString(COL_NM_SER_NUM);
            if (serNum == null) {
                serNum = "";
            }
            BigDecimal qty = null;
            if (STR_MINUS.equals(rs.getString(COL_NM_SIGN_IF))) {
                qty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF);
            } else if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
                qty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).negate();
            }
            String msg = String.format("%s  %-7s  %-10s     %s     %-30s  %5s  %9s  %s",
                    rs.getString(COL_NM_PHYS_INVTY_NUM),
                    rs.getString(COL_NM_INVTY_LOC_CD),
                    rs.getString(COL_NM_CODE_ITEM_IF),
                    rs.getString(COL_NM_CODE_STAT_STOCK_IF),
                    serNum,
                    qty.toPlainString(),
                    msgId,
                    errorMsg);

            S21InfoLogOutput.println(msgId, params);

            String mailKey = rs.getString(COL_NM_WMS_WH_CD);
            if (mailKey == null) {
                mailKey = MAIL_KEY1_VALUE_TO;
            }

            if (mailMapData.containsKey(mailKey)) {
                List<String> mailList = mailMapData.get(mailKey);
                mailList.add(msg);
            } else {
                List<String> mailList = new ArrayList<String>();
                mailList.add(msg);
                mailMapData.put(mailKey, mailList);
            }
        }

        /**
         * <pre>
         * Set API Error Mail Info
         * @param apiId String
         * @param msgId String
         * @param rs ResultSet
         * @throws SQLException
         * </pre>
         */
        private void setApiErrorInfo(String apiId, String msgId, ResultSet rs) throws SQLException {
            String errorMsg = S21MessageFunc.clspGetMessage(msgId);
            String serNum = rs.getString(COL_NM_SER_NUM);
            if (serNum == null) {
                serNum = "";
            }
            BigDecimal qty = null;
            if (STR_MINUS.equals(rs.getString(COL_NM_SIGN_IF))) {
                qty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF);
            } else if (STR_PLUS.equals(rs.getString(COL_NM_SIGN_IF))) {
                qty = rs.getBigDecimal(COL_NM_QTY_ADJUST_IF).negate();
            }
            String msg = String.format("%s  %-7s  %-10s     %s     %-30s  %5s  %9s  %s",
                    rs.getString(COL_NM_PHYS_INVTY_NUM),
                    rs.getString(COL_NM_INVTY_LOC_CD),
                    rs.getString(COL_NM_CODE_ITEM_IF),
                    rs.getString(COL_NM_CODE_STAT_STOCK_IF),
                    serNum,
                    qty.toPlainString(),
                    msgId,
                    apiId + " " + errorMsg);

            S21InfoLogOutput.println(msgId);

            String mailKey = rs.getString(COL_NM_WMS_WH_CD);
            if (mailKey == null) {
                mailKey = MAIL_KEY1_VALUE_TO;
            }

            if (mailMapData.containsKey(mailKey)) {
                List<String> mailList = mailMapData.get(mailKey);
                mailList.add(msg);
            } else {
                List<String> mailList = new ArrayList<String>();
                mailList.add(msg);
                mailMapData.put(mailKey, mailList);
            }
        }

        /**
         * <pre>
         * Set API Error Mail Info
         * @param apiId String
         * @param msgId String
         * </pre>
         */
        private void setApiErrorInfo(String apiId, String msgId) {
            String errorMsg = S21MessageFunc.clspGetMessage(msgId);
            String msg = String.format("                                                                                      %9s  %s",
                    msgId,
                    apiId + " " + errorMsg);

            S21InfoLogOutput.println(msgId);

            String mailKey = MAIL_KEY1_VALUE_TO;

            if (mailMapData.containsKey(mailKey)) {
                List<String> mailList = mailMapData.get(mailKey);
                mailList.add(msg);
            } else {
                List<String> mailList = new ArrayList<String>();
                mailList.add(msg);
                mailMapData.put(mailKey, mailList);
            }
        }

//        /**
//         * <pre>
//         * Equal Value
//         * @param a BigDecimal
//         * @param b BigDecimal
//         * @return  boolean
//         * </pre>
//         */
//        private boolean equalValue(BigDecimal a, BigDecimal b) {
//            if (a == null) {
//                return b == null;
//            } else if (b == null) {
//                return false;
//            }
//            return (a.compareTo(b) == 0);
//        }
    }

    /**
     * QC#24449 Mail Sending processing for error data
     */
    // START 2019/05/22 T.Ogura [QC#50055,MOD]
//    private void sendMailForNoApprove() {
    private void sendMailForNoApprove(String message) {
    // END   2019/05/22 T.Ogura [QC#50055,MOD]

        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();
        if (fromAddrList.isEmpty()) {
            return;
        }

        S21Mail mail = new S21Mail(glblCmpyCd);
        mail.setFromAddress(fromAddrList.get(0));

        S21MailGroup toGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> toAddrList = toGrp.getMailAddress();
        if (toAddrList.isEmpty()) {
            return;
        }

        mail.setToAddressList(toAddrList);

        S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_NO_APPROVE);
        if (!ZYPCommonFunc.hasValue(tmpl.getSubject())) {
            return;
        }

        tmpl.setTemplateParameter(MAIL_TEMPLATE_PARAM_KEY_BATCHID, BUSINESS_ID);
        tmpl.setTemplateParameter("message", message);    // 2019/05/22 T.Ogura [QC#50055,ADD]

        mail.setMailTemplate(tmpl);
        mail.postMail();
    }

    /**
     * QC#26529 Add Registration Dummy Data class of NLCI0330_01 table
     */
    private class CreateAdjustmentDuumyOrder extends S21SsmVoidResultSetHandlerSupport {

        /**
         * Making processing of Adjustment Order
         * @param rs NLCI0330_01ResultSet
         * @throws SQLException
         */
        protected void doProcessQueryResult(final ResultSet rs) throws SQLException {

            while (rs.next()) {

                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put(SQL_PRM_GLBL_CMPY_CD, glblCmpyCd);
                paramMap.put(SQL_PRM_PHYS_INVTY_NUM, rs.getString(COL_NM_PHYS_INVTY_NUM));
                List<Map<String, Object>> result = (List<Map<String, Object>>) ssmClient.queryObjectList("getNoDataWH", paramMap);

                if (result == null || result.isEmpty()) {
                    return;
                }
                for (Map<String, Object> map : result) {
                    BigDecimal physInvtyCntHdrSq = createAdjustmentDummyHeaderData(map);
                    createAdjustmentDummyDetailData(physInvtyCntHdrSq, map);
                }
            }
        }

        private BigDecimal createAdjustmentDummyHeaderData(Map<String, Object> map) throws SQLException {
            PHYS_INVTY_CNT_HDRTMsg headerPiMsg = new PHYS_INVTY_CNT_HDRTMsg();
            BigDecimal physInvtyCntHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_HDR_SQ);
            ZYPEZDItemValueSetter.setValue(headerPiMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(headerPiMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
            ZYPEZDItemValueSetter.setValue(headerPiMsg.physInvtyCtrlPk, (BigDecimal) map.get(COL_NM_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.physInvtyCtrlNm, (String) map.get(COL_NM_PHYS_INVTY_CTRL_NM));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.physInvtyDt, (String) map.get(COL_NM_PHYS_INVTY_DT));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.whCd, (String) map.get(COL_NM_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.rtlWhCd, (String) map.get(COL_NM_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.rtlSwhCd, (String) map.get(COL_NM_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(headerPiMsg.sysCntRegdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(headerPiMsg.adjVarFlg, ZYPConstant.FLG_OFF_N);
            S21ApiTBLAccessor.insert(headerPiMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(headerPiMsg.getReturnCode())) {
                throw new S21AbendException(MSG_ID_NLGM0007E, new String[] {TBL_ID_PHYS_INVTY_CNT_HDR, COL_NM_PHYS_INVTY_CTRL_PK, headerPiMsg.physInvtyCtrlPk.getValue().toPlainString() });
            }
            return physInvtyCntHdrSq;
        }

        private void createAdjustmentDummyDetailData(BigDecimal physInvtyCntHdrSq, Map<String, Object> map) throws SQLException {
            PHYS_INVTY_CNT_DTLTMsg dtlPiMsg = new PHYS_INVTY_CNT_DTLTMsg();
            BigDecimal physInvtyCntDtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_DTL_SQ);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.physInvtyCntDtlPk, physInvtyCntDtlSq);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.physInvtyCtrlPk, (BigDecimal) map.get(COL_NM_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.rtlWhCd, (String) map.get(COL_NM_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.rtlSwhCd, (String) map.get(COL_NM_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(dtlPiMsg.adjVarFlg, ZYPConstant.FLG_OFF_N);
            S21ApiTBLAccessor.insert(dtlPiMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlPiMsg.getReturnCode())) {
                throw new S21AbendException(MSG_ID_NLGM0007E, new String[] {TBL_ID_PHYS_INVTY_CNT_DTL, COL_NM_PHYS_INVTY_CTRL_PK, dtlPiMsg.physInvtyCtrlPk.getValue().toPlainString() });
            }
        }
    }
}
