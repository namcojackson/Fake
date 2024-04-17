/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB031001;

import static com.canon.cusa.s21.batch.NSA.NSAB031001.constant.NSAB031001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttrDefines;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AGGR_USG_RECALTMsg;
import business.db.AGGR_USG_RECAL_DTLTMsg;
import business.db.AGGR_USG_RECAL_XS_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_WIN_DAYSTMsg;
import business.db.FLEET_READ_ROLL_UPTMsg;
import business.db.FLEET_READ_ROLL_UP_DTLTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLG_ALLOCTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC035001PMsg;
import business.parts.NSZC056001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.api.NSZ.NSZC035001.NSZC035001;
import com.canon.cusa.s21.api.NSZ.NSZC035001.constant.NSZC035001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC0470Query;
import com.canon.cusa.s21.api.NSZ.NSZC056001.NSZC056001;
import com.canon.cusa.s21.api.NSZ.NSZC056001.constant.NSZC056001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetNotArvMachCntForFlt;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/** 
 *<pre>
 * Billing Data Stage
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         T.Aoyagi        Create          N/A
 * 2016/01/22   Hitachi         T.Aoyagi        Update          QC3530
 * 2016/03/17   Hitachi         K.Kishimoto     Update          QC3789
 * 04/07/2016   Hitachi         K.Kishimoto     Update          QC5720
 * 06/08/2016   Hitachi         T.Aoyagi        Update          QC9636
 * 08/11/2016   Fujitsu         S.Nakai         Update          QC13329
 * 08/29/2016   Hitachi         K.Kishimoto     Update          QC13799
 * 02/09/2016   Hitachi         K.Kishimoto     Update          QC17509
 * 03/13/2016   Hitachi         K.Kishimoto     Update          QC17753
 * 08/03/2017   Hitachi         K.Kasai         Update          QC18882
 * 08/31/2017   Hitachi         T.Tomita        Update          QC19194
 * 2017/12/01   Hitachi         K.Kojima        Update          QC#17495
 * 2017/12/14   Hitachi         K.Kojima        Update          QC#17495-1
 * 2018/07/17   Hitachi         K.Kishimoto     Update          QC#25959
 * 2018/08/29   Hitachi         K.Kojima        Update          QC#28012
 * 2018/10/18   Hitachi         K.Kojima        Update          QC#28848
 * 2019/03/08   Hitachi         K.Kitachi       Update          QC#30619
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/09/20   Hitachi         D.Yoshida       Update          QC#60070
 * 2023/05/09   Hitachi         K.Kitachi       Update          QC#61469
 *</pre>
 */
public class NSAB031001 extends S21BatchMain implements ZYPConstant {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** User Variable1 */
    private String usrVar1;

    /** Sales Date */
    private String salesDate;

    /** Multi Billing Calculation */
    private BigDecimal multiCnt;

    /** Service Billing Calculation Window Days */
    // START 2022/09/20 [QC#60070,DEL]
//    private BigDecimal windowDays;
    // END   2022/09/20 [QC#60070,DEL]
    // START 2023/05/09 K.Kitachi [QC#61469, ADD]
    private BigDecimal svcBllgCalcWinDaysAot = BigDecimal.ZERO;
    // END 2023/05/09 K.Kitachi [QC#61469, ADD]

    /** Total Count */
    private int totalCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // Add Start 08/29/2016 <QC#13799>
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;
    // Add End   08/29/2016 <QC#13799>

    // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
    /** [@] is not registered.(@) */
    private static final String NSAM0069E = "NSAM0069E";

    /** Business Application ID */
    private static final String BIZ_APP_ID = "NSAB0310";

    /** mail group id for From */
    private static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** mail group id for To */
    private static final String MAIL_GROUP_ID_TO = "NSAB0310";

    /** template ID */
    private static final String MAIL_TEMPLATE_ID = BIZ_APP_ID + "M001";

    /** template parameter key : batch id */
    private static final String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** template parameter key : err date */
    private static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** template parameter key : message */
    private static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    private static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

    private long counter = 0;

    private int logUnit = LOG_UNIT;

    private String logMsg = null;

    /** From Address List */
    List<S21MailAddress> fromAddrList = null;

    /** To Address List */
    List<S21MailAddress> addrToList = null;

    /** Mail Template */
    S21MailTemplate template = null;

    /** Error Message List */
    private List<String> errMsgList = new ArrayList<String>();
    // END 2016/06/08 T.Aoyagi [QC#9636, ADD]

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code"});
        }

        // Get User Variable1
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(ZZM9000E, new String[] {"User Variable1"});
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Get Number Constant
        this.multiCnt = ZYPCodeDataUtil.getNumConstValue("MULTI_BLLG_CALC_CNT", this.glblCmpyCd);
        if (!hasValue(this.multiCnt)) {
            throw new S21AbendException(ZZZM9006E, new String[]{"VAR_CHAR_CONST"});
        }

        // START 2022/09/20 [QC#60070,DEL]
//        this.windowDays = ZYPCodeDataUtil.getNumConstValue("SVC_BLLG_CALC_WINDOW_DAYS", this.glblCmpyCd);
//        if (!hasValue(this.windowDays)) {
//            throw new S21AbendException(ZZZM9006E, new String[]{"VAR_CHAR_CONST"});
//        }
        // END   2022/09/20 [QC#60070,DEL]
        // START 2023/05/09 K.Kitachi [QC#61469, ADD]
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, "*");
        ZYPEZDItemValueSetter.setValue(dsWinDaysTMsg.svcLineBizCd, "*");
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21FastTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            this.svcBllgCalcWinDaysAot = dsWinDaysTMsg.svcBllgCalcWinDaysAot.getValue();
        }
        // END 2023/05/09 K.Kitachi [QC#61469, ADD]

        // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        this.fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"From mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        this.addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template
        this.template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }
        // END 2016/06/08 T.Aoyagi [QC#9636, ADD]

        // Initialize
        this.totalCount = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Add Start 08/29/2016 <QC#13799>
        this.ssmBatchClient = S21SsmBatchClient.getClient(getClass());
        // Add End   08/29/2016 <QC#13799>
    }

    @Override
    protected void mainRoutine() {
        createBillingStage();
    }

    @Override
    protected void termRoutine() {
        // START 2016/06/08 T.Aoyagi [QC#9636, MOD]
        if (this.errMsgList.size() > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCount - this.errMsgList.size(), this.errMsgList.size(), this.totalCount);
        // END 2016/06/08 T.Aoyagi [QC#9636, MOD]
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB031001().executeBatch(NSAB031001.class.getSimpleName());
    }

    private void createBillingStage() {

        logMsg = ZYPCodeDataUtil.getVarCharConstValue(NSAB0310_LOG, glblCmpyCd);
        String logUnitStr = ZYPCodeDataUtil.getVarCharConstValue(NSAB0310_LOG_UNIT, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(logUnitStr)) {
            logUnit = Integer.valueOf(logUnitStr).intValue();
        }

        // Add Start 08/29/2016 <QC#13799>
        recoverRelation();
        // Add End   08/29/2016 <QC#13799>

        // START 2017/12/04 K.Kojima [QC#17495,ADD]
        meterEntry(SSM_ID_USG_CHRG);
        // END 2017/12/04 K.Kojima [QC#17495,ADD]
        // Base Charge
        createSvcContrBllg(SSM_ID_BASE_CHRG);
        // Usage Charge
        createSvcContrBllg(SSM_ID_USG_CHRG);
        // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
        sendMail();
        // END 2016/06/08 T.Aoyagi [QC#9636, ADD]
    }

    // END 2017/12/04 K.Kojima [QC#17495,ADD]
    private void meterEntry(String ssmId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(null);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(ssmId, ssmParam, execParam);
            rs = stmt.executeQuery();

            BigDecimal dsContrPk = null;
            String dsContrCatgCd = null;
            String dsContrDtlStsCd = null;
            BigDecimal dsContrBllgSchdPk = null;
            while (rs.next()) {
                dsContrPk = rs.getBigDecimal("DS_CONTR_PK");
                dsContrCatgCd = rs.getString("DS_CONTR_CATG_CD");
                dsContrDtlStsCd = rs.getString("DS_CONTR_DTL_STS_CD");
                dsContrBllgSchdPk = rs.getBigDecimal("DS_CONTR_BLLG_SCHD_PK");
                if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && DS_CONTR_DTL_STS.SIGNED.equals(dsContrDtlStsCd) && !checkAllMachArv(dsContrPk)) {
                    continue;
                }
                if (!checkMeterEntryAndEntry(rs)) {
                    rollback();
                    continue;
                }
                if (!checkMeterEntryAndEntryForMachine(ssmId, dsContrBllgSchdPk)) {
                    rollback();
                    continue;
                }
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // START 2017/12/04 K.Kojima [QC#17495,ADD]

    private void createSvcContrBllg(String ssmId) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(null);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(ssmId, ssmParam, execParam);
            rs = stmt.executeQuery();

            //START 2017/07/26 K.Kasai [QC#18882,ADD]
            BigDecimal dsContrPk = null;
            String dsContrCatgCd = null;
            String dsContrDtlStsCd = null;
            //END 2017/07/26 K.Kasai [QC#18882,ADD]
            while (rs.next()) {
                //START 2017/07/26 K.Kasai [QC#18882,ADD]
                dsContrPk = rs.getBigDecimal("DS_CONTR_PK");
                dsContrCatgCd = rs.getString("DS_CONTR_CATG_CD");
                dsContrDtlStsCd = rs.getString("DS_CONTR_DTL_STS_CD");

                //if fleet contract of signed status and part of machine doesn't arrive at customer site yet, skip insert staging process.
                if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && DS_CONTR_DTL_STS.SIGNED.equals(dsContrDtlStsCd) && !checkAllMachArv(dsContrPk)) {
                    continue;
                }
                //END 2017/07/26 K.Kasai [QC#18882,ADD]
                // Mod Start 2017/08/31 QC#19194
                BigDecimal prntDsContrBllgSchdPk = rs.getBigDecimal("PRNT_DS_CONTR_BLLG_SCHD_PK");
                SVC_CONTR_BLLGTMsg tMsg = new SVC_CONTR_BLLGTMsg();
                if (!hasValue(prntDsContrBllgSchdPk)) {
                    tMsg = getTMsg(rs, null);
                } else {
                    SVC_CONTR_BLLGTMsg parentTMsg = getPrntSvcContrBllgTMsg(prntDsContrBllgSchdPk);
                    tMsg = getTMsg(rs, parentTMsg);
                }

                insertSvcContrBllg(tMsg);
                updateDsContrBllgSchd(rs);
                if (!hasValue(prntDsContrBllgSchdPk)) {
                    // Create Contract Billing of Regular Accessory, Fleet Machine, Aggregate Machine
                    createRelatedSvcContrBllg(ssmId, tMsg);
                }
                // Mod End 2017/08/31 QC#19194


                // START 2016/06/08 T.Aoyagi [QC#9636, MOD]
                boolean hasErr = false;
                // Mod Start 2017/03/13 <QC#17753>
                // START 2016/01/22 T.Aoyagi [CSA-QC3530, MOD]
                if (SSM_ID_BASE_CHRG.equals(ssmId)) {
                    if (!callBllgCalcAPI(rs)) {
                        hasErr = true;
                    }
                    // Del Start 2017/08/30 QC#19194
//                    //Add Start 04/26/2016 <QC#5720>
//                    if (DS_CONTR_CATG.REGULAR.equals(rs.getString("DS_CONTR_CATG_CD"))) {
//                        if (!callBllgCalcAPIForRegAcc(ssmId, tMsg)) {
//                            hasErr = true;
//                        }
//                    }
//                    //Add End   04/26/2016 <QC#5720>
                    // Del End 2017/08/30 QC#9194
                } else {
                    if (DS_CONTR_CATG.FLEET.equals(rs.getString("DS_CONTR_CATG_CD"))) {
                        if (!callFleetCalculationAPI(rs)) {
                            hasErr = true;
                        }
                        
                    }
                }
                // Mod End  2017/03/13 <QC#17753>
                // END   2016/01/22 T.Aoyagi [CSA-QC3530, MOD]
                if (hasErr) {
                    rollback();
                } else {
                    commit();
                }
                // END 2016/06/08 T.Aoyagi [QC#9636, MOD]
                counter++;
                if (logUnit !=0 && counter%logUnit == 0) {
                    setLogMsg(NSAM0611I, String.valueOf(counter));
                }
            }
            setLogMsg(NSAM0611I, String.valueOf(counter));

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // START 2017/12/04 K.Kojima [QC#17495,ADD]
    private boolean checkMeterEntryAndEntryForMachine(String ssmId, BigDecimal dsContrBllgSchdPk) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(dsContrBllgSchdPk);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(ssmId, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if(!checkMeterEntryAndEntry(rs)){
                    return false;
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return true;
    }
    // END 2017/12/04 K.Kojima [QC#17495,ADD]

    private void createRelatedSvcContrBllg(String ssmId, SVC_CONTR_BLLGTMsg parentTMsg) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(parentTMsg.dsContrBllgSchdPk.getValue());

        try {
            stmt = this.ssmLLClient.createPreparedStatement(ssmId, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SVC_CONTR_BLLGTMsg tMsg = getTMsg(rs, parentTMsg);
                insertSvcContrBllg(tMsg);
                updateDsContrBllgSchd(rs);
                //Add Start 04/07/2016 <QC#5720>
                createRelatedSvcContrBllgForAggAcc(ssmId, tMsg);
                //Add End  04/07/2016 <QC#5720>
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    //Add Start 04/26/2016 <QC#5720>
    // START 2016/06/08 T.Aoyagi [QC#9636, MOD]
    private boolean callBllgCalcAPIForRegAcc(String ssmId, SVC_CONTR_BLLGTMsg parentTMsg) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(parentTMsg.dsContrBllgSchdPk.getValue());
        ssmParam.put("bllgStageFlg", FLG_ON_Y);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(ssmId, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                if (!callBllgCalcAPI(rs)) {
                    return false;
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return true;
    }
    // END 2016/06/08 T.Aoyagi [QC#9636, MOD]
    //Add End   04/26/2016 <QC#5720>

    //Add Start 04/07/2016 <QC#5720>
    private void createRelatedSvcContrBllgForAggAcc(String ssmId, SVC_CONTR_BLLGTMsg parentTMsg) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(parentTMsg.dsContrBllgSchdPk.getValue());

        try {
            stmt = this.ssmLLClient.createPreparedStatement(ssmId, ssmParam, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SVC_CONTR_BLLGTMsg tMsg = getTMsg(rs, parentTMsg);
                insertSvcContrBllg(tMsg);
                updateDsContrBllgSchd(rs);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
    //Add End  04/07/2016 <QC#5720>

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_COMMIT_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> getSsmParam(BigDecimal prntDsContrBllgSchdPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("bllgCpltStsCdCR", BLLG_CPLT_STS.CREATED);
        ssmParam.put("day0", DAY0);
        ssmParam.put("day99", DAY99);
        ssmParam.put("dsContrDtlTpCdFLEET", DS_CONTR_DTL_TP.FLEET);
        ssmParam.put("dsContrDtlTpCdAGG", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("dsContrCatgCdAGG", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("dsContrCatgCdFLEET", DS_CONTR_CATG.FLEET);
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsBllgSchdStsCdOpen", DS_BLLG_SCHD_STS.OPEN);
		// START 2017/07/17 [QC#25959, ADD]
        ssmParam.put("dsBllgSchdStsCdClose", DS_BLLG_SCHD_STS.CLOSE);
		// END   2017/07/17 [QC#25959, ADD]
        // START 2022/09/20 [QC#60070,MOD]
//        String nextBllgDt = ZYPDateUtil.addDays(this.salesDate, this.windowDays.intValue());
//        ssmParam.put("nextBllgDt", nextBllgDt);
        ssmParam.put("salesDate", this.salesDate);
        ssmParam.put("formatYMD", ZYPDateUtil.TYPE_YYYYMMDD);
        ssmParam.put("defWinDays", 0);
        // END   2022/09/20 [QC#60070,MOD]
        // START 2023/05/09 K.Kitachi [QC#61469, ADD]
        ssmParam.put("svcBllgCalcWinDaysAot", this.svcBllgCalcWinDaysAot);
        // END 2023/05/09 K.Kitachi [QC#61469, ADD]
        ssmParam.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        ssmParam.put("multiCnt", this.multiCnt);
        ssmParam.put("usrVar1", this.usrVar1);
        //Add Start 03/17/2016 <QC#3789>
        ssmParam.put("advance", BLLG_TMG_TP.ADVANCE);
        ssmParam.put("arrears", BLLG_TMG_TP.ARREARS);
        //Add End   03/17/2016 <QC#3789>
        // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
        ssmParam.put("dsContrDtlStsCdList", new String[] {DS_CONTR_DTL_STS.SAVED, DS_CONTR_DTL_STS.SUBMITED, DS_CONTR_DTL_STS.CANCELLED });
        ssmParam.put("dsContrBllgMtrStsCdList", new String[] {DS_CONTR_DTL_STS.SAVED, DS_CONTR_DTL_STS.SUBMITED, DS_CONTR_DTL_STS.CANCELLED });
        // END 2016/06/08 T.Aoyagi [QC#9636, ADD]
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        ssmParam.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        return ssmParam;
    }

    private SVC_CONTR_BLLGTMsg getTMsg(ResultSet rs, SVC_CONTR_BLLGTMsg parentTMsg) {

        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();

        @SuppressWarnings("unchecked")
        ArrayList[] columnList = inMsg.getSelectColumnList();

        List<String> rsColNames = new ArrayList<String>();
        ResultSetMetaData rsm;
        String dbNm;
        String rsNm;

        try {
            rsm = rs.getMetaData();
            for (int i = 0; i < rsm.getColumnCount(); i++) {
                rsColNames.add(rsm.getColumnName(i + 1));
            }
            // ----------------------------------------
            // map to SVC_CONTR_BLLGTMsg from ResutlSet
            // ----------------------------------------
            for (int idx = VAR_ITEM_START_POSN; idx < columnList[0].size(); idx++) {
                dbNm = columnList[0].get(idx).toString();
                rsNm = columnList[1].get(idx).toString();
                if (!rsColNames.contains(rsNm)) {
                    continue;
                }
                if (inMsg.getAttr(dbNm).getType() == EZDItemAttrDefines.TYPE_SEISU_SYOSU) {
                    inMsg.setDBValue(dbNm, rs.getBigDecimal(rsNm));
                } else {
                    inMsg.setDBValue(dbNm, rs.getString(rsNm));
                }
            }

            // ----------------------------------------
            // edit value of SVC_CONTR_BLLGTMsg
            // ----------------------------------------
            BigDecimal svcContrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_SQ);
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.svcContrBllgPk, svcContrBllgPk);

            if (parentTMsg == null) {
                // Parent Contract
                setValue(inMsg.svcContrBllgGrpSq, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_GRP_SQ));
            } else {
                // Child Contract
                setValue(inMsg.prntSvcContrBllgPk, parentTMsg.svcContrBllgPk);
                setValue(inMsg.svcContrBllgGrpSq, parentTMsg.svcContrBllgGrpSq);
                // Add Start 02/07/2017 <QC#17509>
                if (DS_CONTR_CATG.FLEET.equals(rs.getString("DS_CONTR_CATG_CD"))) {
                    setValue(inMsg.billToCustCd, parentTMsg.billToCustCd);
                }
                // Add End   02/07/2017 <QC#17509>
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inMsg;
    }

    // START 2016/06/08 T.Aoyagi [QC#9636, MOD]
    private boolean callBllgCalcAPI(ResultSet rs) throws SQLException {

        NSZC056001 api = new NSZC056001();
        NSZC056001PMsg pMsg = new NSZC056001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC056001Constant.MODE_BILLING_STAGE);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
        setValue(pMsg.baseChrgFlg, FLG_ON_Y);
        setValue(pMsg.usgChrgFlg, FLG_OFF_N);
        setValue(pMsg.nextBllgDt, rs.getString("NEXT_BLLG_DT"));
        // START 2018/08/29 K.Kojima [QC#28012,ADD]
        setValue(pMsg.svcContrBllgFromDt, rs.getString("CALC_BLLG_SCHD_FROM_DT"));
        // END 2018/08/29 K.Kojima [QC#28012,ADD]
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();

            addErrMsg(msgId, msgPrms, rs.getBigDecimal("DS_CONTR_PK"), rs.getBigDecimal("DS_CONTR_DTL_PK"));
            return false;
        }
        return true;
    }
    // END 2016/06/08 T.Aoyagi [QC#9636, MOD]

    private void insertSvcContrBllg(SVC_CONTR_BLLGTMsg inMsg) {

        S21FastTBLAccessor.insert(inMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NZZM0011E, new String[] {"SVC_CONTR_BLLG"});
        }
    }

    private void updateDsContrBllgSchd(ResultSet rs) throws SQLException {

        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, rs.getBigDecimal("DS_CONTR_BLLG_SCHD_PK"));
        inMsg = (DS_CONTR_BLLG_SCHDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (inMsg == null) {
            throw new S21AbendException(NZZM0003E, new String[] {"DS_CONTR_BLLG_SCHD"});
        }

        setValue(inMsg.bllgStageFlg, FLG_ON_Y);
        EZDTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NZZM0012E, new String[] {"DS_CONTR_BLLG_SCHD"});
        }
        this.totalCount++;
    }

    // START 2016/06/08 T.Aoyagi [QC#9636, ADD]
    private void addErrMsg(String msgId, String[] msgPrms, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {

        String errMsg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
        StringBuilder sb = new StringBuilder();
        if (hasValue(errMsg)) {
            sb.append(errMsg);
            sb.append("[DS_CONTR_PK=");
            sb.append(dsContrPk);
            sb.append("]");
            sb.append("[DS_CONTR_DTL_PK=");
            sb.append(dsContrDtlPk);
            sb.append("]");
            this.errMsgList.add(sb.toString());
        }
    }

    private void sendMail() {

        if (this.errMsgList.isEmpty()) {
            return;
        }

        S21MailAddress from = null;
        if (!this.fromAddrList.isEmpty()) {
            from = this.fromAddrList.get(0);
        }

        // Create Subject and Body
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        String newLine = System.getProperty("line.separator");
        StringBuilder msgBuf = new StringBuilder();
        for (String errMsg : this.errMsgList) {
            msgBuf.append(errMsg);
            msgBuf.append(newLine);
        }

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, msgBuf.toString());

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

        StringBuilder logBuf = new StringBuilder();
        logBuf.append(newLine);
        logBuf.append("==================== Skip or Error Data ====================");
        logBuf.append(newLine);
        logBuf.append(msgBuf);
        logBuf.append("============================================================");
        S21InfoLogOutput.println(logBuf.toString());
    }
    // END 2016/06/08 T.Aoyagi [QC#9636, ADD]
    // Add Start 08/29/2016 <QC#13799>
    private void recoverRelation() {
        List<BigDecimal> inconsistencyRelationList = getInconsistencyRelation();
        for (BigDecimal inconsistencyRelation : inconsistencyRelationList) {
            deleteSvcContrBllgInfo(this.glblCmpyCd, inconsistencyRelation);
            DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.dsContrBllgSchdPk, inconsistencyRelation);
            inMsg = (DS_CONTR_BLLG_SCHDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
            if (inMsg != null) {
                setValue(inMsg.bllgStageFlg, FLG_OFF_N);
                setValue(inMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
                EZDTBLAccessor.update(inMsg);
            }
        }
    }

    private List<BigDecimal> getInconsistencyRelation() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("multiCnt", this.multiCnt);
        ssmParam.put("usrVar1", this.usrVar1);
        // START 2019/03/08 K.Kitachi [QC#30619, ADD]
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        // END 2019/03/08 K.Kitachi [QC#30619, ADD]
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        ssmParam.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getInconsistencyRelation", ssmParam);
    }

    private void deleteSvcContrBllgInfo(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        List<BigDecimal> svcContrBllgPkList = getSvcContrBllgPk(glblCmpyCd, dsContrBllgSchdPk);

        for (BigDecimal svcContrBllgPk : svcContrBllgPkList) {

            List<BigDecimal> svcContrBllgAllocPkList = getSvcContrBllgAllocPk(glblCmpyCd, svcContrBllgPk);
            removeSvcContrBllgAlloc(glblCmpyCd, svcContrBllgAllocPkList);

            List<BigDecimal> svcContrBaseBllgPkList = getSvcContrBaseBllgPk(glblCmpyCd, svcContrBllgPk);
            removeSvcContrBaseBllg(glblCmpyCd, svcContrBaseBllgPkList);

            List<BigDecimal> svcContrMtrBllgPkList = getSvcContrMtrBllgPk(glblCmpyCd, svcContrBllgPk);
            removeSvcContrMtrBllg(glblCmpyCd, svcContrMtrBllgPkList);

            List<BigDecimal> svcContrXsMtrBllgPkList = NSZC0470Query.getInstance().getSvcContrXsMtrBllgPk(glblCmpyCd, svcContrBllgPk);
            removeSvcContrXsMtrBllg(glblCmpyCd, svcContrXsMtrBllgPkList);

            deleteSvcContrAddlChrgInfo(glblCmpyCd, svcContrBllgPk);
        }

        List<BigDecimal> fleetReadRollUpPkList = getFleetReadRollUpPk(glblCmpyCd, dsContrBllgSchdPk);
        removeFleetReadRollUp(glblCmpyCd, fleetReadRollUpPkList);

        List<BigDecimal> fleetReadRollUpDtlPkList = getFleetReadRollUpDtlPk(glblCmpyCd, fleetReadRollUpPkList);
        removeFleetReadRollUpDtl(glblCmpyCd, fleetReadRollUpDtlPkList);

        List<BigDecimal> svcContrBllgGrpSqList = getSvcContrBllgGrpSq(glblCmpyCd, dsContrBllgSchdPk);

        for (BigDecimal svcContrBllgGrpSq : svcContrBllgGrpSqList) {
            List<BigDecimal> aggrUsgRecalPkList = getAggrUsgRecalPk(glblCmpyCd, svcContrBllgGrpSq);

            for (BigDecimal aggrUsgRecalPk : aggrUsgRecalPkList) {
                List<BigDecimal> aggrUsgRecalDtlPkList = getAggrUsgRecalDtlPk(glblCmpyCd, aggrUsgRecalPk);

                for (BigDecimal aggUsgRecalDtlPk : aggrUsgRecalDtlPkList) {
                    List<BigDecimal> aggrUsgRecalXsMtrList = getAggrUsgRecalXsMtrPk(glblCmpyCd, aggUsgRecalDtlPk);
                    removeAggrUsgRecalXsMtr(glblCmpyCd, aggUsgRecalDtlPk, aggrUsgRecalXsMtrList);
                }
                removeAggrUsgRecalDtl(glblCmpyCd, aggrUsgRecalPk, aggrUsgRecalDtlPkList);
            }
            removeAggrUsgRecal(glblCmpyCd, aggrUsgRecalPkList);
        }

        removeSvcContrBllg(glblCmpyCd, svcContrBllgPkList);
        return;
    }

    private List<BigDecimal> getSvcContrBllgPk(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcContrBllgPk", ssmParam);
    }

    private List<BigDecimal> getSvcContrBllgGrpSq(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcContrBllgGrpSq", ssmParam);
    }

    private List<BigDecimal> getSvcContrBllgAllocPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcContrBllgAllocPk", ssmParam);
    }

    private List<BigDecimal> getSvcContrBaseBllgPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcContrBaseBllgPk", ssmParam);
    }

    private List<BigDecimal> getSvcContrMtrBllgPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcContrMtrBllgPk", ssmParam);
    }

    private List<BigDecimal> getSvcContrBllgPkForAddl(String glblCmpyCd, BigDecimal prntSvcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntSvcContrBllgPk", prntSvcContrBllgPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcContrBllgPkForAddl", ssmParam);
    }

    private List<BigDecimal> getSvcContrAddlChrgBllgPk(String glblCmpyCd, BigDecimal svcContrBllgPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgPk", svcContrBllgPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcContrAddlChrgBllgPk", ssmParam);
    }

    private List<BigDecimal> getFleetReadRollUpPk(String glblCmpyCd, BigDecimal prntDsContrBllgSchdPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getFleetReadRollUpPk", ssmParam);
    }

    private List<BigDecimal> getFleetReadRollUpDtlPk(String glblCmpyCd, List<BigDecimal> fleetReadRollUpPkList) {

        if (fleetReadRollUpPkList == null || fleetReadRollUpPkList.isEmpty()) {
            return null;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("fleetReadRollUpPkList", fleetReadRollUpPkList);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getFleetReadRollUpDtlPk", ssmParam);
    }

    private List<BigDecimal> getAggrUsgRecalPk(String glblCmpyCd, BigDecimal svcContrBllgGrpSq) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcContrBllgGrpSq", svcContrBllgGrpSq);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getAggrUsgRecalPk", ssmParam);
    }

    private List<BigDecimal> getAggrUsgRecalDtlPk(String glblCmpyCd, BigDecimal aggrUsgRecalPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("aggrUsgRecalPk", aggrUsgRecalPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getAggrUsgRecalDtlPk", ssmParam);
    }

    private List<BigDecimal> getAggrUsgRecalXsMtrPk(String glblCmpyCd, BigDecimal aggrUsgRecalDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("aggrUsgRecalDtlPk", aggrUsgRecalDtlPk);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getAggrUsgRecalXsMtrPk", ssmParam);
    }

    private void removeSvcContrBllgAlloc(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_BLLG_ALLOCTMsg inTMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrBllgAllocPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeSvcContrBaseBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_BASE_BLLGTMsg inTMsg = new SVC_CONTR_BASE_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrBaseBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeSvcContrMtrBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_MTR_BLLGTMsg inTMsg = new SVC_CONTR_MTR_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrMtrBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeSvcContrXsMtrBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_XS_MTR_BLLGTMsg inTMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrXsMtrBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeSvcContrAddlChrgBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_ADDL_CHRG_BLLGTMsg inTMsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrAddlChrgBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeFleetReadRollUp(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            FLEET_READ_ROLL_UPTMsg inTMsg = new FLEET_READ_ROLL_UPTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.fleetReadRollUpPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeFleetReadRollUpDtl(String glblCmpyCd, List<BigDecimal> pkList) {
        if (pkList == null) {
            return;
        }

        for (BigDecimal pk : pkList) {

            FLEET_READ_ROLL_UP_DTLTMsg inTMsg = new FLEET_READ_ROLL_UP_DTLTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.fleetReadRollUpDtlPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeAggrUsgRecal(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            AGGR_USG_RECALTMsg inTMsg = new AGGR_USG_RECALTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.aggrUsgRecalPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeAggrUsgRecalDtl(String glblCmpyCd, BigDecimal aggrUsgRecalPk, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            AGGR_USG_RECAL_DTLTMsg inTMsg = new AGGR_USG_RECAL_DTLTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.aggrUsgRecalPk, aggrUsgRecalPk);
            setValue(inTMsg.aggrUsgRecalDtlPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeAggrUsgRecalXsMtr(String glblCmpyCd, BigDecimal aggrUsgRecalDtlPk, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            AGGR_USG_RECAL_XS_MTRTMsg inTMsg = new AGGR_USG_RECAL_XS_MTRTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.aggrUsgRecalDtlPk, aggrUsgRecalDtlPk);
            setValue(inTMsg.aggrUsgRecalXsMtrPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void removeSvcContrBllg(String glblCmpyCd, List<BigDecimal> pkList) {

        for (BigDecimal pk : pkList) {

            SVC_CONTR_BLLGTMsg inTMsg = new SVC_CONTR_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrBllgPk, pk);
            S21ApiTBLAccessor.logicalRemove(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                return;
            }
        }
        return;
    }

    private void deleteSvcContrAddlChrgInfo(String glblCmpyCd, BigDecimal prntSvcContrBllgPk) {

        List<BigDecimal> svcContrBllgPkList = getSvcContrBllgPkForAddl(glblCmpyCd, prntSvcContrBllgPk);

        for (BigDecimal svcContrBllgPk : svcContrBllgPkList) {

            List<BigDecimal> svcContrBllgAllocPkList = getSvcContrBllgAllocPk(glblCmpyCd, svcContrBllgPk);
            removeSvcContrBllgAlloc(glblCmpyCd, svcContrBllgAllocPkList);

            List<BigDecimal> svcContrAddlChrgBllgPkList = getSvcContrAddlChrgBllgPk(glblCmpyCd, svcContrBllgPk);
            removeSvcContrAddlChrgBllg(glblCmpyCd, svcContrAddlChrgBllgPkList);
        }

        removeSvcContrBllg(glblCmpyCd, svcContrBllgPkList);
    }
    // Add End   08/29/2016 <QC#13799>
    private void setLogMsg(String msgId, String... param) {
        if (logMsg == null || ZYPConstant.FLG_ON_Y.equals(logMsg)) {
            String msg = S21MessageFunc.clspGetMessage(msgId, param);
            S21InfoLogOutput.println(msg);
        }
    }
    // Add Start 2017/03/13 <QC#17753>
    private boolean callFleetCalculationAPI(ResultSet rs) throws SQLException {

        NSZC035001 api = new NSZC035001();
        NSZC035001PMsg pMsg = new NSZC035001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC035001Constant.MODE_NORMAL);
        setValue(pMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        setValue(pMsg.bllgDt, rs.getString("SVC_CONTR_BLLG_THRU_DT"));
        setValue(pMsg.prntDsContrBllgSchdPk, rs.getBigDecimal("DS_CONTR_BLLG_SCHD_PK"));
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            setLogMsg(msgId, msgPrms);
            return true;
        }
        return true;
    }
    // Add End   2017/03/13 <QC#17753>

    //START 2017/07/24 K.Kasai [QC#18882,ADD]
    private boolean checkAllMachArv(BigDecimal dsContrPk) {

        // check Not Arrived Machine in Fleet Contract
        BigDecimal notArvMachCnt = NSXC001001GetNotArvMachCntForFlt.getNotArvMachCnt(getGlobalCompanyCode(), dsContrPk);

        if (BigDecimal.ZERO.compareTo(notArvMachCnt) < 0) {
            return false;
        }
        return true;
    }
    //END 2017/07/24 K.Kasai [QC#18882,ADD]

    // Add Start 2017/08/31 QC#19194
    private SVC_CONTR_BLLGTMsg getPrntSvcContrBllgTMsg(BigDecimal dsContrBllgSchdPk) {
        if (!hasValue(dsContrBllgSchdPk)) {
            return null;
        }

        DS_CONTR_BLLG_SCHDTMsg schdTMsg = getDsContrBllgSchd(dsContrBllgSchdPk);
        if (schdTMsg == null) {
            return null;
        }

        if (!hasValue(schdTMsg.dsBllgSchdStsCd) || DS_BLLG_SCHD_STS.CLOSE.equals(schdTMsg.dsBllgSchdStsCd.getValue())) {
            return null;
        }

        return getSvcContrBllg(dsContrBllgSchdPk);
    }

    private DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchd(BigDecimal dsContrBllgSchdPk) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    private SVC_CONTR_BLLGTMsg getSvcContrBllg(BigDecimal dsContrBllgSchdPk) {
        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("", this.glblCmpyCd);
        inMsg.setConditionValue("", dsContrBllgSchdPk);
        inMsg.setConditionValue("", ZYPConstant.FLG_OFF_N);
        SVC_CONTR_BLLGTMsgArray resultList = (SVC_CONTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (resultList.getValidCount() == 0) {
            return null;
        }
        return resultList.no(0);
    }
    // Add End 2017/08/31 QC#19194

    // START 2017/12/01 K.Kojima [QC#17495,ADD]
    private boolean checkMeterEntryAndEntry(ResultSet rs) throws SQLException {
        BigDecimal dsContrBllgSchdPk = rs.getBigDecimal("DS_CONTR_BLLG_SCHD_PK");
        BigDecimal svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK");
        String mtrEntryCpltFlg = rs.getString("MTR_ENTRY_CPLT_FLG");
        BigDecimal svcPhysMtrReadGrpSq = rs.getBigDecimal("SVC_PHYS_MTR_READ_GRP_SQ");
        String dsContrCatgCd = rs.getString("DS_CONTR_CATG_CD");

        // No Meter Entry -> Return
        if (!hasValue(mtrEntryCpltFlg) || !ZYPConstant.FLG_ON_Y.equals(mtrEntryCpltFlg)) {
            return true;
        }
        // No Pre Meter Entry Schedule -> Return
        Map<String, Object> preNoMeterEntrySchedule = null;
        if (hasValue(dsContrCatgCd) && DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            preNoMeterEntrySchedule = getPreNoMeterEntryScheduleForFleet(dsContrBllgSchdPk);
        } else {
            preNoMeterEntrySchedule = getPreNoMeterEntrySchedule(dsContrBllgSchdPk);
        }
        if (preNoMeterEntrySchedule == null) {
            return true;
        }
        // MeterEntry
        return callMeterEntry(svcMachMstrPk, svcPhysMtrReadGrpSq, (String) preNoMeterEntrySchedule.get("BLLG_SCHD_THRU_DT"));
    }

    private boolean callMeterEntry(BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrReadGrpSq, String mtrReadDt) {
        SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadList = getSvcPhysMtrReadList(svcPhysMtrReadGrpSq);

        NSZC010001PMsg pMsg = new NSZC010001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
        setValue(pMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
        setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
        setValue(pMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        pMsg.fsrNum.clear();
        pMsg.fsrVisitNum.clear();
        int cnt = 0;
        for (int i = 0; i < svcPhysMtrReadList.getValidCount(); i++) {
            SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = svcPhysMtrReadList.no(i);

            // START 2018/10/18 K.Kojima [QC#28848,MOD]
            // Map<String, Object> preMtrRead = getPreMtrRead(svcPhysMtrReadTMsg.svcPhysMtrReadPk.getValue());
            Map<String, Object> preMtrRead = getPreMtrRead(svcPhysMtrReadTMsg.svcPhysMtrReadPk.getValue(), mtrReadDt);
            // END 2018/10/18 K.Kojima [QC#28848,MOD]
            if (preMtrRead == null) {
                continue;
            }
            SvcPhysMtrReadInfoBean preMtrReadInfo = new SvcPhysMtrReadInfoBean();
            preMtrReadInfo.setSvcPhysMtrReadPk((BigDecimal) preMtrRead.get("SVC_PHYS_MTR_READ_PK"));
            preMtrReadInfo.setSvcPhysMtrPk((BigDecimal) preMtrRead.get("SVC_PHYS_MTR_PK"));
            preMtrReadInfo.setMtrReadDt((String) preMtrRead.get("MTR_READ_DT"));
            preMtrReadInfo.setReadMtrCnt((BigDecimal) preMtrRead.get("READ_MTR_CNT"));

            SvcPhysMtrReadInfoBean mtrReadInfo = new SvcPhysMtrReadInfoBean();
            mtrReadInfo.setSvcPhysMtrReadPk(svcPhysMtrReadTMsg.svcPhysMtrReadPk.getValue());
            mtrReadInfo.setSvcPhysMtrPk(svcPhysMtrReadTMsg.svcPhysMtrPk.getValue());
            mtrReadInfo.setMtrReadDt(svcPhysMtrReadTMsg.mtrReadDt.getValue());
            mtrReadInfo.setReadMtrCnt(svcPhysMtrReadTMsg.readMtrCnt.getValue());

            List<SvcPhysMtrReadInfoBean> mtrReadList = new ArrayList<SvcPhysMtrReadInfoBean>();
            mtrReadList.add(preMtrReadInfo);
            mtrReadList.add(mtrReadInfo);
            BigDecimal estMtrCnt = NSXC003001SvcPhysMtrRead.estMtrCnt(glblCmpyCd, mtrReadDt, mtrReadList);

            pMsg.A.no(cnt).physMtrId.clear();
            setValue(pMsg.A.no(cnt).mtrReadDt, mtrReadDt);
            setValue(pMsg.A.no(cnt).rgtnMtrDt, this.salesDate);
            pMsg.A.no(cnt).svcPhysMtrReadPk.clear();
            setValue(pMsg.A.no(cnt).readMtrCnt, estMtrCnt);
            setValue(pMsg.A.no(cnt).testMtrCnt, BigDecimal.ZERO);
            setValue(pMsg.A.no(cnt).rgtnUsrId, "NSAB032001");
            setValue(pMsg.A.no(cnt).estFlg, ZYPConstant.FLG_ON_Y);
            setValue(pMsg.A.no(cnt).invProcFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg.A.no(cnt).svcPhysMtrPk, svcPhysMtrReadTMsg.svcPhysMtrPk);
            setValue(pMsg.A.no(cnt).vldMtrFlg, ZYPConstant.FLG_ON_Y);
            cnt++;
        }
        pMsg.A.setValidCount(cnt);

        if (pMsg.A.getValidCount() > 0) {
            new NSZC010001().execute(pMsg, ONBATCH_TYPE.BATCH);
        }

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private Map<String, Object> getPreNoMeterEntrySchedule(BigDecimal dsContrBllgSchdPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        // START 2017/12/14 K.Kojima [QC#17495-1,ADD]
        ssmParam.put("dsBllgSchdStsClose", DS_BLLG_SCHD_STS.CLOSE);
        // END 2017/12/14 K.Kojima [QC#17495-1,ADD]
        return (Map<String, Object>) ssmBatchClient.queryObject("getPreNoMeterEntrySchedule", ssmParam);
    }

    private Map<String, Object> getPreNoMeterEntryScheduleForFleet(BigDecimal dsContrBllgSchdPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        // START 2017/12/14 K.Kojima [QC#17495-1,ADD]
        ssmParam.put("dsBllgSchdStsClose", DS_BLLG_SCHD_STS.CLOSE);
        // END 2017/12/14 K.Kojima [QC#17495-1,ADD]
        return (Map<String, Object>) ssmBatchClient.queryObject("getPreNoMeterEntryScheduleForFleet", ssmParam);
    }

    // START 2018/10/18 K.Kojima [QC#28848,MOD]
    // private Map<String, Object> getPreMtrRead(BigDecimal svcPhysMtrReadPk) {
    private Map<String, Object> getPreMtrRead(BigDecimal svcPhysMtrReadPk, String mtrReadDt) {
    // END 2018/10/18 K.Kojima [QC#28848,MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcPhysMtrReadPk", svcPhysMtrReadPk);
        // START 2018/10/18 K.Kojima [QC#28848,ADD]
        ssmParam.put("mtrReadDt", mtrReadDt);
        // END 2018/10/18 K.Kojima [QC#28848,ADD]
        return (Map<String, Object>) ssmBatchClient.queryObject("getPreMtrRead", ssmParam);
    }

    private SVC_PHYS_MTR_READTMsgArray getSvcPhysMtrReadList(BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        return (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // END 2017/12/01 K.Kojima [QC#17495,ADD]
}
