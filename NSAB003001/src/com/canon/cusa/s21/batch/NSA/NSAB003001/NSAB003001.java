package com.canon.cusa.s21.batch.NSA.NSAB003001;

import static com.canon.cusa.s21.batch.NSA.NSAB003001.constant.NSAB003001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BLLG_CYCLETMsg;
import business.db.CFS_CONTR_PRC_UPLFTTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_PRC_CHNG_RECTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.MDL_NMTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxDsContrBllgMtrListPMsg;
import business.parts.NSZC047011PMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NSZC057001PMsg;
import business.parts.NSZC100001PMsg;
import business.parts.NWZC194001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC046001.NSZC046001;
import com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC100001.NSZC100001;
import com.canon.cusa.s21.api.NWZ.NWZC194001.NWZC194001;
import com.canon.cusa.s21.api.NWZ.NWZC194001.constant.NWZC194001Constant;
import com.canon.cusa.s21.batch.NSA.NSAB003001.constant.NSAB003001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.ContrRenewalPreCheckInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.EndOfLifeBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrRenewalPreCheck;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetEndOfLifeInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcTermAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcTermAmt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * Service Contract Auto Renew Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/20/2013   SRAA            Y.Chen          Create          N/A
 * 08/22/2013   SRAA            Y.Chen          Update          QC2053
 * 08/22/2013   SRAA            Y.Chen          Update          QC2188
 * 09/13/2013   SRAA            Y.Chen          Update          QC2245, QC2254
 * 09/24/2013   SRAA            Y.Chen          Update          QC2364
 * 01/20/2016   Hitachi         T.Iwamoto       Update          N/A
 * 01/25/2016   Hitachi         T.Iwamoto       Update          QC#3531,3532,3535,3539,3668
 * 01/26/2016   Hitachi         T.Iwamoto       Update          QC#3744
 * 01/27/2016   Hitachi         T.Iwamoto       Update          QC#3788
 * 02/05/2016   Hitachi         K.Kasai         Create          QC#3996
 * 02/10/2016   Hitachi         T.Iwamoto       Update          QC#3683
 * 05/26/2016   Hitachi         T.Mizuki        Update          QC#447
 * 06/06/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 02/08/2017   Hitachi         K.Kitachi       Update          QC#17410
 * 2017/05/24   Hitachi         K.Kojima        Update          QC#18686
 * 08/22/2017   CITS            M.Naito         Update          QC#8661
 * 2017/09/08   Hitachi         K.Kitachi       Update          QC#20557
 * 2017/10/03   Hitachi         E.Kameishi      Update          QC#21504
 * 2017/10/06   Hitachi         M.Kidokoro      Update          QC#21546
 * 2017/10/30   Hitachi         K.Kojima        Update          QC#21859
 * 2017/11/21   Hitachi         K.Kojima        Update          QC#22712
 * 2017/11/28   Hitachi         K.Kojima        Update          QC#22660
 * 2017/12/06   Hitachi         K.Yamada        Update          QC#22891
 * 2018/03/29   Hitachi         K.Kojima        Update          QC#24889
 * 2018/04/11   Hitachi         K.Kojima        Update          QC#24798
 * 2018/06/18   Hitachi         U.Kim           Update          QC#24903
 * 2018/12/19   Hitachi         T.Tomita        Update          QC#29636
 * 2019/02/12   Hitachi         K.Kitachi       Update          QC#30147
 * 2019/10/28   Hitachi         A.Kohinata      Update          QC#53577
 * 2021/12/02   CITS            R.Cabral        Update          QC#59352
 * 2022/06/01   Hitachi         D.Yoshida       Update          QC#59973
 * 2022/12/01   CITS            R.Jin           Update          QC#60880
 * 2023/07/06   Hitachi         K.Watanabe      Update          QC#61145
 * 2023/07/12   Hitachi         K.Watanabe      Update          QC#61145
 * 2024/04/01   Hitachi         K.Ogasawara     Update          QC#63550
 *</pre>
 */
public class NSAB003001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** default Model Update Rule For Base */
    private BigDecimal defModelUpdateRuleBase = null;

    /** default Model Update Rule For Usage */
    private BigDecimal defModelUpdateRuleUsage = null;

    /** total search count */
    private int searchCnt = 0;

    /** Dc Contract success count */
    private int infoSccessCnt = 0;

    // mod start 2016/05/26 CSA QC#447
    /** System TimeStamp */
    private String sysTime = null;
    // mod end 2016/05/26 CSA QC#447

    // START 2017/02/08 K.Kitachi [QC#17410, ADD]
    /** DS Contract Modification Text */
    private String dsContrModTxt = null;

    /** CFS Dealer Code */
    private String cfsDlrCd = null;

    /** Special Fleet Merchandise Code */
    private String spclFltMdseCd = null;

    /** Model Name TMsg */
    private MDL_NMTMsg mdlNmTMsg;
    // END 2017/02/08 K.Kitachi [QC#17410, ADD]

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NSAB003001().executeBatch(NSAB003001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        // Global company code
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(NASM0010E);
        }

        // Sales date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(NASM0011E);
        }

        // default Model Update Rule
        this.defModelUpdateRuleBase = ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_BASE, glblCmpyCd);
        this.defModelUpdateRuleUsage = ZYPCodeDataUtil.getNumConstValue(DEF_MDL_RULE_USAGE, glblCmpyCd);

        this.excParam = new S21SsmExecutionParameter();
        this.excParam.setFetchSize(FETCH_SIZE_MAX);
        this.excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        this.excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        this.excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        // mod start 2016/05/26 CSA QC#447
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
        // mod end 2016/05/26 CSA QC#447

        // START 2017/02/08 K.Kitachi [QC#17410, ADD]
        this.dsContrModTxt = ZYPDateUtil.getCurrentSystemTime(FORMAT_DS_CONTR_MOD_TXT).toUpperCase();
        this.cfsDlrCd = ZYPCodeDataUtil.getVarCharConstValue(CFS_DLR_CD, this.glblCmpyCd);
        this.spclFltMdseCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, this.glblCmpyCd);
        this.mdlNmTMsg = getMdlNmTMsg();
        // END 2017/02/08 K.Kitachi [QC#17410, ADD]
    }

    @Override
    protected void mainRoutine() {

        doProcess();
    }

    @Override
    protected void termRoutine() {

        // Set term code and total count.
        setTermState(this.termCd, infoSccessCnt, searchCnt - infoSccessCnt, searchCnt);
    }

    /**
     * doProcess
     */
    private void doProcess() {
        // START 2019/02/12 K.Kitachi [QC#30147, MOD]
//        PreparedStatement renewallList = null;
        List<Map<String, Object>> renewalList = null;
//        ResultSet rsRenewal = null;
        // END 2019/02/12 K.Kitachi [QC#30147, MOD]
        BigDecimal preDsContrPk = BigDecimal.ZERO;
        BigDecimal curDsContrPk = BigDecimal.ZERO;
        BigDecimal curDsContrDtlPk = BigDecimal.ZERO;
        String dsContrNum = null;
        String baseFlg = null;
        String usageFlg = null;
        String dsContrCatgCd = null;
        String dsContrDtlTpCd = null;
        String effFromDt = null;
        String rnwPrcMethCd = null;
        BigDecimal curBasePrcDealAmt = null;
        BigDecimal curBasePrcUpRatio = null;
        BigDecimal newBasePrcDealAmt = null;
        BigDecimal newXsMtrAmtRate = null;
        BigDecimal newCalPrcAmtRate = null;
        // START 2018/03/29 K.Kojima [QC#24889,ADD]
        BigDecimal curCalPrcAmtRate = null;
        // END 2018/03/29 K.Kojima [QC#24889,ADD]
        // START 2018/06/18 U.Kim [QC#24903,ADD]
        BigDecimal maxBasePrcDealAmt = null;
        BigDecimal maxCalPrcAmtRate = null;
        BigDecimal maxPrcUpRatio = null;
        // END 2018/06/18 U.Kim [QC#24903,ADD]
        BigDecimal curMtrPrcUpRatio = null;
        BigDecimal aftDeclPntDigitNum = null;
        Map<String, Object> dsContrOrcEff = null;
        NSZC046001PMsg updatePMsg = new NSZC046001PMsg();
        boolean errFlg = false;
        List<String> errList = new ArrayList<String>();
        // mod start 2016/05/26 CSA QC#447
        List<String> errListEOL = new ArrayList<String>();
        // mod end 2016/05/26 CSA QC#447
        // START 2019/02/12 K.Kitachi [QC#30147, DEL]
//        PreparedStatement letterListForNew = null;
//        ResultSet rsLetterForNew = null;
//        PreparedStatement letterListForRevise = null;
//        ResultSet rsLetterForRevise = null;
        // END 2019/02/12 K.Kitachi [QC#30147, DEL]
        List<DS_CONTR_DTLTMsg> updateTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
        // add start 2016/06/06 CSA Defect#1523, 4624
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        // add end 2016/06/06 CSA Defect#1523, 4624
        // START 2017/08/22 M.Naito [QC#8661, ADD]
        List<BigDecimal> prcChngRecList = new ArrayList<BigDecimal>();
        // END 2017/08/22 M.Naito [QC#8661, ADD]
        // START 2019/02/12 K.Kitachi [QC#30147, DEL]
//        try {
        // END 2019/02/12 K.Kitachi [QC#30147, DEL]
            // 1.get Renewal Escalation Target
            // START 2019/02/12 K.Kitachi [QC#30147, MOD]
//            renewallList = ssmLLClient.createPreparedStatement("getRenewalTargetList", setParamForRenewal(), excParam);
            renewalList = getRenewalTargetList();
//            rsRenewal = renewallList.executeQuery();
        // START 2021/12/02 R.Cabral [QC#59352,ADD]
        List<Map<String, Object>> aggMtrPrcList  = new ArrayList<Map<String, Object>>();
        // END   2021/12/02 R.Cabral [QC#59352,ADD]
        // START 2024/04/01 K.Ogasawara [QC#63550, ADD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
        // END 2024/04/01 K.Ogasawara [QC#63550, ADD]

        // add start 2019/10/28 QC#53577
        for (Map<String, Object> renewal : renewalList) {
            // START 2024/04/02 K.Ogasawara [QC#63550, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals((String) renewal.get("AUTO_RNW_FLG"))) {
                dsContrDtlPkList.add((BigDecimal) renewal.get("DS_CONTR_DTL_PK"));
            }
            // END 2024/04/02 K.Ogasawara [QC#63550, ADD]
            if (DS_CONTR_DTL_TP.ACCESSORIES.equals((String) renewal.get("DS_CONTR_DTL_TP_CD"))) {
                String accNewEffThruDt = getAccNewEffThruDt(renewalList, (BigDecimal) renewal.get("DS_CONTR_DTL_PK"), (String) renewal.get("NEW_EFF_THRU_DT"));
                if (ZYPDateUtil.compare(accNewEffThruDt, (String) renewal.get("NEW_EFF_THRU_DT")) != 0) {
                    renewal.put("NEW_EFF_THRU_DT", accNewEffThruDt);
                }
            }
        }
        // add end 2019/10/28 QC#53577

//            while (rsRenewal.next()) {
            for (Map<String, Object> renewal : renewalList) {
            // END 2019/02/12 K.Kitachi [QC#30147, MOD]

                // START 2019/02/12 K.Kitachi [QC#30147, MOD]
//                curDsContrPk = rsRenewal.getBigDecimal("DS_CONTR_PK");
//                curDsContrDtlPk = rsRenewal.getBigDecimal("DS_CONTR_DTL_PK");
//                dsContrCatgCd = rsRenewal.getString("DS_CONTR_CATG_CD");
//                dsContrDtlTpCd = rsRenewal.getString("DS_CONTR_DTL_TP_CD");
//                dsContrNum = rsRenewal.getString("DS_CONTR_NUM");
//                baseFlg = rsRenewal.getString("BASE_FLG");
//                usageFlg = rsRenewal.getString("USAGE_FLG");
                curDsContrPk = (BigDecimal) renewal.get("DS_CONTR_PK");
                curDsContrDtlPk = (BigDecimal) renewal.get("DS_CONTR_DTL_PK");
                dsContrCatgCd = (String) renewal.get("DS_CONTR_CATG_CD");
                dsContrDtlTpCd = (String) renewal.get("DS_CONTR_DTL_TP_CD");
                dsContrNum = (String) renewal.get("DS_CONTR_NUM");
                baseFlg = (String) renewal.get("BASE_FLG");
                usageFlg = (String) renewal.get("USAGE_FLG");
                // END 2019/02/12 K.Kitachi [QC#30147, MOD]

                if (BigDecimal.ZERO.compareTo(preDsContrPk) != 0 && curDsContrPk.compareTo(preDsContrPk) != 0) {

                    // START 2017/05/24 K.Kojima [QC#18686,ADD]
                    if (hasValue(updatePMsg.glblCmpyCd)) {
                    // END 2017/05/24 K.Kojima [QC#18686,ADD]

                    // 2.4 Call Contract Update API
                    if (!errFlg) {
                        if (!callUpdateApi(updatePMsg, errList)) {
                            errFlg = true;
                            // START 2017/02/08 K.Kitachi [QC#17410, DEL]
//                            preDsContrPk = curDsContrPk;
//                            updatePMsg = new NSZC046001PMsg();
//                            continue;
                            // END 2017/02/08 K.Kitachi [QC#17410, DEL]
                        }
                    }
                    // START 2017/02/08 K.Kitachi [QC#17410, DEL]
//                    updatePMsg = new NSZC046001PMsg();
                    // END 2017/02/08 K.Kitachi [QC#17410, DEL]

                    // 2.5 Update Contract Detail
                    if (!errFlg) {
                        if (!updateDsContrDtl(updateTMsgList)) {
                            errFlg = true;
                            addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), dsContrNum);
                            // START 2017/02/08 K.Kitachi [QC#17410, DEL]
//                            preDsContrPk = curDsContrPk;
//                            updateTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
//                            continue;
                            // END 2017/02/08 K.Kitachi [QC#17410, DEL]
                        }
                    }
                    updateTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();

                    // START 2017/08/22 M.Naito [QC#8661, ADD]
                    // 2.6 Insert DS_CONTR_PRC_CHNG_REC
                    if (getRnwLtrCtrlInfo(preDsContrPk)) {
                        if (!insertDsContrPrcChngRec(prcChngRecList, preDsContrPk, effFromDt)) {
                            addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {"DS_CONTR_PRC_CHNG_REC"}), dsContrNum);
                            errFlg = true;
                        }
                    }
                    prcChngRecList.clear();
                    // END 2017/08/22 M.Naito [QC#8661, ADD]

                    // 3.QA Validation
                    if (!errFlg) {
                        if (!callQaValidationApi(setQavalidation(dsContrNum), errList, updatePMsg)) {
                            errFlg = true;
                            // START 2017/02/08 K.Kitachi [QC#17410, DEL]
//                            preDsContrPk = curDsContrPk;
//                            continue;
                            // END 2017/02/08 K.Kitachi [QC#17410, DEL]
                        }
                    }

                    // START 2024/04/04 K.Ogasawara [QC#63550, ADD]
                    if (!errFlg) {
                        if (!setManualRenew(errList, updatePMsg, dsContrDtlPkList)) {
                            errFlg = true;
                        }
                    }
                    // END 2024/04/04 K.Ogasawara [QC#63550, ADD]

                    // START 2017/02/08 K.Kitachi [QC#17410, ADD]
                    if (!errFlg) {
                        if (!createCfsContrPrcUplft(updatePMsg, errList)) {
                            errFlg = true;
                        }
                    }
                    // END 2017/02/08 K.Kitachi [QC#17410, ADD]

                    // START 2017/11/28 K.Kojima [QC#22660,ADD]
                    if (!errFlg) {
                        if (!callSumAggregateAPI(errList, preDsContrPk)) {
                            errFlg = true;
                        }
                    }
                    // END 2017/11/28 K.Kojima [QC#22660,ADD]

                    // START 2022/06/01 [QC#59973,ADD]
                    if (!errFlg) {
                        for (int i = 0; i < updatePMsg.xxContrDtlList.getValidCount(); i++) {
                            if (!callSchdAgmtAdjContrApi(errList, updatePMsg.xxContrDtlList.no(i).svcMachMstrPk.getValue(), updatePMsg.dsContrPk.getValue(), updatePMsg.dsContrNum.getValue())) {
                                errFlg = true;
                            }
                        }
                    }
                    // END 2022/06/01 [QC#59973,ADD]

                    searchCnt++;

                    if (!errFlg) {
                        infoSccessCnt++;
                        commit();
                    } else {
                        rollback();
                    }

                    // START 2017/05/24 K.Kojima [QC#18686,ADD]
                    }
                    // END 2017/05/24 K.Kojima [QC#18686,ADD]

                    errFlg = false;
                    // START 2017/02/08 K.Kitachi [QC#17410, ADD]
                    updatePMsg = new NSZC046001PMsg();
                    preDsContrPk = curDsContrPk;
                    // END 2017/02/08 K.Kitachi [QC#17410, ADD]
                    // START 2021/12/02 R.Cabral [QC#59352,ADD]
                    aggMtrPrcList.clear();
                    // END   2021/12/02 R.Cabral [QC#59352,ADD]
                } else {
                    if (errFlg) {
                        // START 2017/02/08 K.Kitachi [QC#17410, ADD]
                        updatePMsg = new NSZC046001PMsg();
                        // END 2017/02/08 K.Kitachi [QC#17410, ADD]
                        preDsContrPk = curDsContrPk;
                        continue;
                    }
                }
                // START 2017/08/22 M.Naito [QC#8661, ADD]
                prcChngRecList.add(curDsContrDtlPk);
                // END 2017/08/22 M.Naito [QC#8661, ADD]

                // mod start 2016/05/26 CSA QC#447
                // check EndofLife
                if (!eolCheck(curDsContrPk, curDsContrDtlPk, dsContrCatgCd, dsContrDtlTpCd, dsContrNum, errListEOL)) {
                    continue;
                }
                // mod end 2016/05/26 CSA QC#447

                // ********************************
                // 2.1 get DS_CONTR_DTL(Base)
                // ********************************
                List<Map<String, Object>> dsContrDtlBaseList = new ArrayList<Map<String, Object>>();
                if (!isFleetMachine(dsContrCatgCd, dsContrDtlTpCd)) {
                    dsContrDtlBaseList = getDsContrDtlBase(curDsContrPk, curDsContrDtlPk);
                } else {
                    dsContrDtlBaseList = getDsContrDtlBaseNotFleetMachine(curDsContrPk, curDsContrDtlPk);
                }
                // add start 2019/10/28 QC#53577
                if (DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd)) {
                    if (ZYPDateUtil.compare((String) dsContrDtlBaseList.get(0).get("NEW_EFF_THRU_DT"),(String) renewal.get("NEW_EFF_THRU_DT")) != 0) {
                        dsContrDtlBaseList.get(0).put("NEW_EFF_THRU_DT", (String) renewal.get("NEW_EFF_THRU_DT"));
                    }
                }
                // add end 2019/10/28 QC#53577
                // START 2019/02/12 K.Kitachi [QC#30147, ADD]
                if (isAggLine(dsContrCatgCd, dsContrDtlTpCd)) {
                    String aggNewEffThruDt = getAggNewEffThruDt(renewalList, curDsContrPk, curDsContrDtlPk);
                    if (hasValue(aggNewEffThruDt) && ZYPDateUtil.compare(aggNewEffThruDt, (String) renewal.get("CONTR_EFF_THRU_DT")) > 0) {
                        dsContrDtlBaseList.get(0).put("NEW_EFF_THRU_DT", aggNewEffThruDt);
                    }
                    // add start 2019/10/28 QC#53577
                    if (hasValue(aggNewEffThruDt) && ZYPDateUtil.compare(aggNewEffThruDt, (String) renewal.get("CONTR_EFF_THRU_DT")) <= 0) {
                        continue;
                    }
                    // add end 2019/10/28 QC#53577
                }
                // END 2019/02/12 K.Kitachi [QC#30147, ADD]
                DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = getDsContrPrcEff(curDsContrDtlPk);
                if (!isFleetMachine(dsContrCatgCd, dsContrDtlTpCd) && dsContrPrcEffTMsg == null) {
                    addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0370E), dsContrNum);
                    errFlg = true;
                    preDsContrPk = curDsContrPk;
                    continue;
                }

                // START 2018/06/18 U.Kim [QC#24903,ADD]
                aftDeclPntDigitNum = (BigDecimal) dsContrDtlBaseList.get(0).get("AFT_DECL_PNT_DIGIT_NUM");
                // END 2018/06/18 U.Kim [QC#24903,ADD]

                rnwPrcMethCd = (String) dsContrDtlBaseList.get(0).get("RNW_PRC_METH_CD");
                // START 2018/06/18 U.Kim [QC#24903,ADD]
                maxPrcUpRatio = (BigDecimal) dsContrDtlBaseList.get(0).get("MAX_PRC_UP_RATIO");
                // END 2018/06/18 U.Kim [QC#24903,ADD]
                if (!isFleetMachine(dsContrCatgCd, dsContrDtlTpCd)) {
                    curBasePrcDealAmt = dsContrPrcEffTMsg.basePrcDealAmt.getValue();
                    // START 2018/06/18 U.Kim [QC#24903,ADD]
                    maxBasePrcDealAmt = recalcValueForMaxRate(dsContrPrcEffTMsg.basePrcDealAmt.getValue(), maxPrcUpRatio, aftDeclPntDigitNum);
                    // END 2018/06/18 U.Kim [QC#24903,ADD]
                } else {
                    curBasePrcDealAmt = null;
                }
                // START 2018/06/18 U.Kim [QC#24903,DEL]
                // aftDeclPntDigitNum = (BigDecimal) dsContrDtlBaseList.get(0).get("AFT_DECL_PNT_DIGIT_NUM");
                // END 2018/06/18 U.Kim [QC#24903,DEL]
                effFromDt = (String) dsContrDtlBaseList.get(0).get("NEW_EFF_FROM_DT");

                if (ZYPConstant.FLG_ON_Y.equals(baseFlg) && !isAggLineBrank(dsContrDtlTpCd, dsContrDtlBaseList.get(0))) {
                    if (RNW_PRC_METH.MARKUP_PERCENT.equals(rnwPrcMethCd)) {
                        curBasePrcUpRatio = (BigDecimal) dsContrDtlBaseList.get(0).get("BASE_PRC_UP_RATIO");
                        newBasePrcDealAmt = calcNewBasePrcDealAmt(curBasePrcDealAmt, curBasePrcUpRatio, aftDeclPntDigitNum);
                    } else if (RNW_PRC_METH.MODEL_PERCENT.equals(rnwPrcMethCd)) {
                        curBasePrcUpRatio = getModelUpdateRuleBase(curDsContrDtlPk, effFromDt, dsContrDtlTpCd);
                        if (!hasValue(curBasePrcUpRatio)) {
                            addErrList(errList, S21MessageFunc.clspGetMessage(NSZM0396E, new String[] {"Setup Information for Escalation rule", "DEF_MDL_RULE_BASE" }), dsContrNum);
                            errFlg = true;
                            preDsContrPk = curDsContrPk;
                            continue;
                        }
                        newBasePrcDealAmt = calcNewBasePrcDealAmt(curBasePrcDealAmt, curBasePrcUpRatio, aftDeclPntDigitNum);
                    }
                    // Calculate Term Amount
                    if (!isFleetMachine(dsContrCatgCd, dsContrDtlTpCd)) {
                        // START 2017/10/30 K.Kojima [QC#21859,MOD]
                        int bllgCycleCnt = calcBllgCycleCntFromDuration(glblCmpyCd, effFromDt, (String) dsContrDtlBaseList.get(0).get("NEW_EFF_THRU_DT"), (String) dsContrDtlBaseList.get(0).get("BASE_BLLG_CYCLE_CD"));
                        if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(newBasePrcDealAmt)) {
                            newCalPrcAmtRate = newBasePrcDealAmt.multiply(BigDecimal.valueOf(bllgCycleCnt));
                        } else {
                            newCalPrcAmtRate = getCalPrcAmtRate(effFromDt, newBasePrcDealAmt, dsContrDtlBaseList);
                        }
                        // START 2018/03/29 K.Kojima [QC#24889,ADD]
                        if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(curBasePrcDealAmt)) {
                            curCalPrcAmtRate = curBasePrcDealAmt.multiply(BigDecimal.valueOf(bllgCycleCnt));
                        } else {
                            curCalPrcAmtRate = getCalPrcAmtRate(effFromDt, curBasePrcDealAmt, dsContrDtlBaseList);
                        }
                        // END 2018/03/29 K.Kojima [QC#24889,ADD]
                        // END 2017/10/30 K.Kojima [QC#21859,MOD]
                        // START 2018/06/18 U.Kim [QC#24903,ADD]
                        if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(maxBasePrcDealAmt)) {
                            maxCalPrcAmtRate = maxBasePrcDealAmt.multiply(BigDecimal.valueOf(bllgCycleCnt));
                        } else {
                            maxCalPrcAmtRate = getCalPrcAmtRate(effFromDt, maxBasePrcDealAmt, dsContrDtlBaseList);
                        }
                        // END 2018/06/18 U.Kim [QC#24903,ADD]
                    } else {
                        newCalPrcAmtRate = null;
                    }
                // START 2016/01/27 T.Iwamoto [QC#3788 ADD]
                } else if (!isFleetMachine(dsContrCatgCd, dsContrDtlTpCd)) {
                    newBasePrcDealAmt = dsContrPrcEffTMsg.basePrcDealAmt.getValue();
                    // START 2017/11/21 K.Kojima [QC#22712,MOD]
                    // newCalPrcAmtRate = dsContrPrcEffTMsg.basePrcTermDealAmtRate.getValue();
                    int bllgCycleCnt = calcBllgCycleCntFromDuration(glblCmpyCd, effFromDt, (String) dsContrDtlBaseList.get(0).get("NEW_EFF_THRU_DT"), (String) dsContrDtlBaseList.get(0).get("BASE_BLLG_CYCLE_CD"));
                    if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(newBasePrcDealAmt)) {
                        newCalPrcAmtRate = newBasePrcDealAmt.multiply(BigDecimal.valueOf(bllgCycleCnt));
                    } else {
                        newCalPrcAmtRate = getCalPrcAmtRate(effFromDt, newBasePrcDealAmt, dsContrDtlBaseList);
                    }
                    // START 2018/03/29 K.Kojima [QC#24889,ADD]
                    if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(curBasePrcDealAmt)) {
                        curCalPrcAmtRate = curBasePrcDealAmt.multiply(BigDecimal.valueOf(bllgCycleCnt));
                    } else {
                        curCalPrcAmtRate = getCalPrcAmtRate(effFromDt, curBasePrcDealAmt, dsContrDtlBaseList);
                    }
                    // END 2018/03/29 K.Kojima [QC#24889,ADD]
                    // END 2017/11/21 K.Kojima [QC#22712,MOD]
                    // START 2018/06/18 U.Kim [QC#24903,ADD]
                    if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(maxBasePrcDealAmt)) {
                        maxCalPrcAmtRate = maxBasePrcDealAmt.multiply(BigDecimal.valueOf(bllgCycleCnt));
                    } else {
                        maxCalPrcAmtRate = getCalPrcAmtRate(effFromDt, maxBasePrcDealAmt, dsContrDtlBaseList);
                    }
                    // END 2018/06/18 U.Kim [QC#24903,ADD]
                } else {
                    newBasePrcDealAmt = null;
                    newCalPrcAmtRate = null;
                    // START 2018/03/29 K.Kojima [QC#24889,ADD]
                    curCalPrcAmtRate = null;
                    // END 2018/03/29 K.Kojima [QC#24889,ADD]
                    // START 2018/06/18 U.Kim [QC#24903,ADD]
                    maxCalPrcAmtRate = null;
                    // END 2018/06/18 U.Kim [QC#24903,ADD]
                }
                // END 2016/01/27 T.Iwamoto [QC#3788 ADD]

                // START 2017/09/08 K.Kitachi [QC#20557, ADD]
                BigDecimal svcMachMstrPk = getSvcMachMstrPkFromDsContrDtl(curDsContrDtlPk);
                // END 2017/09/08 K.Kitachi [QC#20557, ADD]

                // set Base param Contract Update API Detail
                setUpdateApiParamForHdr(updatePMsg, curDsContrPk, dsContrCatgCd);
                // START 2017/09/08 K.Kitachi [QC#20557, MOD]
                // START 2018/03/29 K.Kojima [QC#24889,MOD]
                // setUpdateApiParamForBase(updatePMsg, curDsContrDtlPk, dsContrDtlTpCd, dsContrDtlBaseList.get(0), newBasePrcDealAmt, newCalPrcAmtRate, svcMachMstrPk);
                // START 2018/06/18 U.Kim [QC#24903,MOD]
                // setUpdateApiParamForBase(updatePMsg, curDsContrDtlPk, dsContrDtlTpCd, dsContrDtlBaseList.get(0), newBasePrcDealAmt, newCalPrcAmtRate, curBasePrcDealAmt, curCalPrcAmtRate, svcMachMstrPk);
                setUpdateApiParamForBase(updatePMsg, curDsContrDtlPk, dsContrDtlTpCd, dsContrDtlBaseList.get(0), newBasePrcDealAmt, newCalPrcAmtRate, maxBasePrcDealAmt, maxCalPrcAmtRate, svcMachMstrPk);
                // END 2018/06/18 U.Kim [QC#24903,MOD]
                // END 2018/03/29 K.Kojima [QC#24889,MOD]
                // END 2017/09/08 K.Kitachi [QC#20557, MOD]

                // ********************************
                // 2.2 get DS_CONTR_DTL(Usage)
                // ********************************

                List<Map<String, Object>> dsContrDtlUsgList = getDsContrDtlUsage(curDsContrPk, curDsContrDtlPk);
                if (!isFleetOrAggMachine(dsContrCatgCd, dsContrDtlTpCd)) {
                    dsContrDtlUsgList = getDsContrDtlUsage(curDsContrPk, curDsContrDtlPk);
                } else {
                    dsContrDtlUsgList = getDsContrDtlUsageNotRegularMachine(curDsContrPk, curDsContrDtlPk);
                }

                effFromDt = (String) dsContrDtlUsgList.get(0).get("NEW_EFF_FROM_DT");
                rnwPrcMethCd = (String) dsContrDtlUsgList.get(0).get("RNW_PRC_METH_CD");
                // mod start 2016/02/05 CSA Defect#3996
                aftDeclPntDigitNum = new BigDecimal(IDX_6);
                // mod end 2016/02/05 CSA Defect#3996
                // Add Start 2018/12/19 QC#29636
                maxPrcUpRatio = (BigDecimal) dsContrDtlUsgList.get(0).get("MAX_PRC_UP_RATIO");
                // Add End 2018/12/19 QC#29636
                List<Map<String, Object>> dsContrPrcEffList = getUsagePrcEff(curDsContrDtlPk);
                if (dsContrPrcEffList.size() > 0) {
                    for (int i = 0; i < dsContrPrcEffList.size(); i++) {
                        dsContrOrcEff = dsContrPrcEffList.get(i);
                        if (ZYPConstant.FLG_ON_Y.equals(usageFlg)) {
                            if (RNW_PRC_METH.MARKUP_PERCENT.equals(rnwPrcMethCd)) {
                                curMtrPrcUpRatio = (BigDecimal) dsContrDtlUsgList.get(0).get("MTR_PRC_UP_RATIO");
                                newXsMtrAmtRate = calcNewXsMtrAmtRate((BigDecimal) dsContrOrcEff.get("XS_MTR_AMT_RATE"), curMtrPrcUpRatio, aftDeclPntDigitNum);
                            } else if (RNW_PRC_METH.MODEL_PERCENT.equals(rnwPrcMethCd)) {
                                BigDecimal dsContrBllgMtrPk = (BigDecimal) dsContrOrcEff.get("DS_CONTR_BLLG_MTR_PK");
                                curMtrPrcUpRatio = getModelUpdateRuleMtr(curDsContrDtlPk, effFromDt, dsContrDtlTpCd, dsContrBllgMtrPk, dsContrCatgCd);
                                if (!hasValue(curMtrPrcUpRatio)) {
                                    addErrList(errList, S21MessageFunc.clspGetMessage(NSZM0396E, new String[] {"Setup Information for Escalation rule", "DEF_MDL_RULE_USAGE" }), dsContrNum);
                                    errFlg = true;
                                    preDsContrPk = curDsContrPk;
                                    break;
                                }
                                newXsMtrAmtRate = calcNewXsMtrAmtRate((BigDecimal) dsContrOrcEff.get("XS_MTR_AMT_RATE"), curMtrPrcUpRatio, aftDeclPntDigitNum);
                            }
                        // START 2016/01/27 T.Iwamoto [QC#3788 ADD]
                        } else {
                            newXsMtrAmtRate = (BigDecimal) dsContrOrcEff.get("XS_MTR_AMT_RATE");
                        }
                        // END 2016/01/27 T.Iwamoto [QC#3788, ADD]

                        // set Usage param Contract Update API
                        // START 2017/09/08 K.Kitachi [QC#20557, MOD]
                        // START 2018/06/18 U.Kim [QC#24903,MOD]
                        // setUpdateApiParamForMtr(updatePMsg, dsContrOrcEff, newXsMtrAmtRate, curDsContrDtlPk, isSetFlgForUsage(dsContrCatgCd, dsContrDtlTpCd), svcMachMstrPk);
                        // START 2021/12/02 R.Cabral [QC#59352,MOD]
                        // setUpdateApiParamForMtr(updatePMsg, dsContrOrcEff, newXsMtrAmtRate, curDsContrDtlPk, isSetFlgForUsage(dsContrCatgCd, dsContrDtlTpCd), svcMachMstrPk, maxPrcUpRatio, aftDeclPntDigitNum);
                        String bllgMtrLbCd = (String) dsContrOrcEff.get("BLLG_MTR_LB_CD");
                        setUpdateApiParamForMtr(updatePMsg, dsContrOrcEff, newXsMtrAmtRate, curDsContrDtlPk, isSetFlgForUsage(dsContrCatgCd, dsContrDtlTpCd), svcMachMstrPk, maxPrcUpRatio, aftDeclPntDigitNum,
                            dsContrCatgCd, dsContrDtlTpCd, bllgMtrLbCd, aggMtrPrcList);
                        // END   2021/12/02 R.Cabral [QC#59352,MOD]
                        // END 2018/06/18 U.Kim [QC#24903,MOD]
                        // END 2017/09/08 K.Kitachi [QC#20557, MOD]
                    }
                    if (errFlg) {
                        continue;
                    }
                }

                // set Update Contract Detail
                setUpdateApiParamForHdr(updatePMsg, curDsContrPk, dsContrCatgCd);
                updateTMsgList.add(setForUpdateDsContrDtl(curDsContrDtlPk, effFromDt));

                preDsContrPk = curDsContrPk;
                // add start 2016/06/06 CSA Defect#1523, 4624
                if (!dsContrPkList.contains(curDsContrPk)) {
                    dsContrPkList.add(curDsContrPk);
                }
                // add end 2016/06/06 CSA Defect#1523, 4624
            }

            // Last Row
            if (hasValue(updatePMsg.glblCmpyCd)) {
                // 2.4 Call Contract Update API
                if (!errFlg) {
                    if (!callUpdateApi(updatePMsg, errList)) {
                        errFlg = true;
                    }
                }

                // 2.5 Update Contract Detail
                if (!errFlg) {
                    if (!updateDsContrDtl(updateTMsgList)) {
                        errFlg = true;
                        addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), dsContrNum);
                    }
                }

                // START 2017/08/22 M.Naito [QC#8661, ADD]
                // 2.6 Insert DS_CONTR_PRC_CHNG_REC
                if (getRnwLtrCtrlInfo(curDsContrPk)) {
                    if (!insertDsContrPrcChngRec(prcChngRecList, curDsContrPk, effFromDt)) {
                        errFlg = true;
                        addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {"DS_CONTR_PRC_CHNG_REC"}), dsContrNum);
                    }
                }
                // END 2017/08/22 M.Naito [QC#8661, ADD]

                // 3.QA Validation
                if (!errFlg) {
                    if (!callQaValidationApi(setQavalidation(dsContrNum), errList, updatePMsg)) {
                        errFlg = true;
                    }
                }

                // START 2024/04/04 K.Ogasawara [QC#63550, ADD]
                if (!errFlg) {
                    if (!setManualRenew(errList, updatePMsg, dsContrDtlPkList)) {
                        errFlg = true;
                    }
                }
                // END 2024/04/04 K.Ogasawara [QC#63550, ADD]

                // START 2017/02/08 K.Kitachi [QC#17410, ADD]
                if (!errFlg) {
                    if (!createCfsContrPrcUplft(updatePMsg, errList)) {
                        errFlg = true;
                    }
                }
                // END 2017/02/08 K.Kitachi [QC#17410, ADD]

                // START 2017/11/28 K.Kojima [QC#22660,ADD]
                if (!errFlg) {
                    if (!callSumAggregateAPI(errList, curDsContrPk)) {
                        errFlg = true;
                    }
                }
                // END 2017/11/28 K.Kojima [QC#22660,ADD]

                // START 2022/06/01 [QC#59973,ADD]
                if (!errFlg) {
                    for (int i = 0; i < updatePMsg.xxContrDtlList.getValidCount(); i++) {
                        if (!callSchdAgmtAdjContrApi(errList, updatePMsg.xxContrDtlList.no(i).svcMachMstrPk.getValue(), updatePMsg.dsContrPk.getValue(), updatePMsg.dsContrNum.getValue())) {
                            errFlg = true;
                        }
                    }
                }
                // END 2022/06/01 [QC#59973,ADD]

                searchCnt++;

                if (!errFlg) {
                    infoSccessCnt++;
                    commit();
                } else {
                    rollback();
                }
                errFlg = false;
            }

            // *******************************************
            // 4. Create Contract Renewal Letter
            // *******************************************
            // START 2017/08/22 M.Naito [QC#8661, MOD]
//            createContractAgreementLetter(errList);
            createRenewalLetterWork(errList);
            // END 2017/08/22 M.Naito [QC#8661, MOD]

            // add start 2016/06/06 CSA Defect#1523, 4624
            // *****************************
            // 5. Call Contract Tracking API
            // *****************************
            callContrTrk(dsContrPkList, errList);
            // add end 2016/06/06 CSA Defect#1523, 4624

            // *****************************
            // 6. Send Error Mail
            // *****************************
            if (errList.size() > 0) {
                sendErrMail(errList);
            }
            // mod start 2016/05/26 CSA QC#477
            if (errListEOL.size() > 0) {
                // START 2023/07/06 K.Watanabe [QC#61145,MOD]
                //sendErrMail(errListEOL);
                sendErrMailEol(errListEOL);
                // END 2023/07/06 K.Watanabe [QC#61145,MOD]
            }
            // mod end 2016/05/26 CSA QC#477

        // START 2019/02/12 K.Kitachi [QC#30147, DEL]
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(renewallList, rsRenewal);
//            S21SsmLowLevelCodingClient.closeResource(letterListForNew, rsLetterForNew);
//            S21SsmLowLevelCodingClient.closeResource(letterListForRevise, rsLetterForRevise);
//        }
        // END 2019/02/12 K.Kitachi [QC#30147, DEL]
    }

      // START 2017/08/22 M.Naito [QC#8661, DEL]
//    /**
//     * createContractAgreementLetter
//     * @param errList
//     */
//    private void createContractAgreementLetter(List<String> errList) {
//        NSZC100001PMsg letterForNewPMsg = new NSZC100001PMsg();
//        NSZC100001PMsg letterForRevisePMsg = new NSZC100001PMsg();
//        BigDecimal preDsContrPk = BigDecimal.ZERO;
//        BigDecimal curDsContrPk = BigDecimal.ZERO;
//        String dsContrNum = null;
//        BigDecimal curDsContrDtlPk = BigDecimal.ZERO;
//        PreparedStatement letterListForNew = null;
//        ResultSet rsLetterForNew = null;
//        PreparedStatement letterListForRevise = null;
//        ResultSet rsLetterForRevise = null;
//        boolean errFlg = false;
//
//        try {
//            // *******************************************
//            // 4.1.Letter issuance of PO issuance request
//            // *******************************************
//            letterListForNew = ssmLLClient.createPreparedStatement("getLetterList", setParamForLetter(true), excParam);
//            rsLetterForNew = letterListForNew.executeQuery();
//            while (rsLetterForNew.next()) {
//                curDsContrPk = rsLetterForNew.getBigDecimal("DS_CONTR_PK");
//                dsContrNum = rsLetterForNew.getString("DS_CONTR_NUM");
//                curDsContrDtlPk = rsLetterForNew.getBigDecimal("DS_CONTR_DTL_PK");
//                if (!rsLetterForNew.isFirst() && curDsContrPk.compareTo(preDsContrPk) != 0) {
//                    searchCnt++;
//                    // Call Contract Agreement Letter Creation API
//                    if (!callLetterApi(letterForNewPMsg, errList, dsContrNum)) {
//                        errFlg = true;
//                    }
//                    letterForNewPMsg = new NSZC100001PMsg();
//
//                    if (!errFlg) {
//                        infoSccessCnt++;
//                        commit();
//                    } else {
//                        rollback();
//                    }
//                    errFlg = false;
//                } else {
//                    if (errFlg) {
//                        preDsContrPk = curDsContrPk;
//                        continue;
//                    }
//                }
//                // set api param
//                setLetterApiParam(letterForNewPMsg, curDsContrPk, curDsContrDtlPk);
//
//            }
//            // Last Row
//            if (rsLetterForNew.isAfterLast()) {
//                searchCnt++;
//
//                // Call Contract Agreement Letter Creation API
//                if (!callLetterApi(letterForNewPMsg, errList, dsContrNum)) {
//                    errFlg = true;
//                }
//
//                if (!errFlg) {
//                    infoSccessCnt++;
//                    commit();
//                } else {
//                    rollback();
//                }
//                errFlg = false;
//            }
//
//            // *******************************************
//            // 4.2.Letter issuance of revised information
//            // *******************************************
//            letterListForRevise = ssmLLClient.createPreparedStatement("getLetterList", setParamForLetter(false), excParam);
//            rsLetterForRevise = letterListForRevise.executeQuery();
//            preDsContrPk = BigDecimal.ZERO;
//            while (rsLetterForRevise.next()) {
//                curDsContrPk = rsLetterForRevise.getBigDecimal("DS_CONTR_PK");
//                dsContrNum = rsLetterForRevise.getString("DS_CONTR_NUM");
//                curDsContrDtlPk = rsLetterForRevise.getBigDecimal("DS_CONTR_DTL_PK");
//                if (!rsLetterForRevise.isFirst() && curDsContrPk.compareTo(preDsContrPk) != 0) {
//                    searchCnt++;
//                    // Call Contract Agreement Letter Creation API
//                    if (!callLetterApi(letterForRevisePMsg, errList, dsContrNum)) {
//                        errFlg = true;
//                    }
//                    letterForRevisePMsg = new NSZC100001PMsg();
//
//                    if (!errFlg) {
//                        infoSccessCnt++;
//                        commit();
//                    } else {
//                        rollback();
//                    }
//                    errFlg = false;
//                } else {
//                    if (errFlg) {
//                        preDsContrPk = curDsContrPk;
//                        continue;
//                    }
//                }
//                // set api param
//                setLetterApiParam(letterForRevisePMsg, curDsContrPk, curDsContrDtlPk);
//
//            }
//            // Last Row
//            if (rsLetterForRevise.isAfterLast()) {
//                searchCnt++;
//
//                // Call Contract Agreement Letter Creation API
//                if (!callLetterApi(letterForRevisePMsg, errList, dsContrNum)) {
//                    errFlg = true;
//                }
//                if (!errFlg) {
//                    infoSccessCnt++;
//                    commit();
//                } else {
//                    rollback();
//                }
//                errFlg = false;
//            }
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        } finally {
//            S21SsmLowLevelCodingClient.closeResource(letterListForNew, rsLetterForNew);
//            S21SsmLowLevelCodingClient.closeResource(letterListForRevise, rsLetterForRevise);
//        }
//    }
    // END 2017/08/22 M.Naito [QC#8661, DEL]

    // START 2017/08/22 M.Naito [QC#8661, ADD]
    /**
     * createRenewalLetterWork
     * @param errList
     */
    private void createRenewalLetterWork(List<String> errList) {
        NSZC100001PMsg letterForNewPMsg = new NSZC100001PMsg();
        BigDecimal preDsContrPk = BigDecimal.ZERO;
        BigDecimal curDsContrPk = BigDecimal.ZERO;
        String dsContrNum = null;
        String preDsContrNum = null;
        String printOldPrcFlg = null;
        String dsAcctNum = null;
        String billToCustCd = null;
        BigDecimal curDsContrDtlPk = BigDecimal.ZERO;
        PreparedStatement letterListForNew = null;
        ResultSet rsLetterForNew = null;
        PreparedStatement letterListForRevise = null;
        ResultSet rsLetterForRevise = null;
        boolean errFlg = false;
        // START 2017/10/03 E.Kameishi [QC#21504,ADD]
        boolean procFlg = false;
        // END 2017/10/03 E.Kameishi [QC#21504,ADD]

        try {
            // *******************************************
            // 4.1.Price Renewal Letter Work request
            // *******************************************
            letterListForNew = ssmLLClient.createPreparedStatement("getRenewalLetterList", setParamForLetter(true), excParam);
            rsLetterForNew = letterListForNew.executeQuery();
            while (rsLetterForNew.next()) {
                // START 2017/10/03 E.Kameishi [QC#21504,ADD]
                procFlg = true;
                // END 2017/10/03 E.Kameishi [QC#21504,ADD]
                curDsContrPk = rsLetterForNew.getBigDecimal("DS_CONTR_PK");
                dsContrNum = rsLetterForNew.getString("DS_CONTR_NUM");
                curDsContrDtlPk = rsLetterForNew.getBigDecimal("DS_CONTR_DTL_PK");
                printOldPrcFlg = rsLetterForNew.getString("PRINT_OLD_PRC_FLG");
                dsAcctNum = rsLetterForNew.getString("DS_ACCT_NUM");
                billToCustCd = rsLetterForNew.getString("ALT_PAYER_CUST_CD");
                if (!rsLetterForNew.isFirst() && curDsContrPk.compareTo(preDsContrPk) != 0) {
                    searchCnt++;
                    // Call Contract Agreement Letter Creation API
                    if (!callLetterApi(letterForNewPMsg, errList, preDsContrNum)) {
                        errFlg = true;
                    }
                    letterForNewPMsg = new NSZC100001PMsg();

                    if (!errFlg) {
                        infoSccessCnt++;
                        commit();
                    } else {
                        rollback();
                    }
                    errFlg = false;
                }
                // set api param
                setLetterApiParam(letterForNewPMsg, curDsContrPk, curDsContrDtlPk, printOldPrcFlg, dsAcctNum, billToCustCd);
                preDsContrPk = curDsContrPk;
                preDsContrNum = dsContrNum;

            }
            // START 2017/10/03 E.Kameishi [QC#21504,ADD]
            // Last Row
            //if (rsLetterForNew.isAfterLast()) {
            if (procFlg) {
            // END 2017/10/03 E.Kameishi [QC#21504,ADD]
                searchCnt++;

                // Call Contract Agreement Letter Creation API
                if (!callLetterApi(letterForNewPMsg, errList, preDsContrNum)) {
                    errFlg = true;
                }

                if (!errFlg) {
                    infoSccessCnt++;
                    commit();
                } else {
                    rollback();
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(letterListForNew, rsLetterForNew);
            S21SsmLowLevelCodingClient.closeResource(letterListForRevise, rsLetterForRevise);
        }
    }
    // END 2017/08/22 M.Naito [QC#8661, ADD]

    /**
     * isSetFlgForUsage
     * @param dsContrCatgCd
     * @param dsContrDtlTpCd
     * @return
     */
    private boolean isSetFlgForUsage(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            return false;
        }
        return true;
    }

    /**
     * setForUpdateDsContrDtl
     * @param curDsContrDtlPk
     * @param effFromDt
     * @return
     */
    private DS_CONTR_DTLTMsg setForUpdateDsContrDtl(BigDecimal curDsContrDtlPk, String effFromDt) {
        DS_CONTR_DTLTMsg inParam = new DS_CONTR_DTLTMsg();
        setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        setValue(inParam.dsContrDtlPk, curDsContrDtlPk);
        setValue(inParam.uplftEffFromDt, effFromDt);
        return inParam;
    }

    /**
     * setParamForRenewal
     * @return
     */
    private Map<String, Object> setParamForRenewal() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.slsDt);
        paramMap.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        paramMap.put("dsContrCatgReg", DS_CONTR_CATG.REGULAR);
        paramMap.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        paramMap.put("dsContrCatgAgg", DS_CONTR_CATG.AGGREGATE);
        String[] dsContrCatgList = new String[] {DS_CONTR_CATG.FLEET, DS_CONTR_CATG.AGGREGATE };
        paramMap.put("dsContrCatgList", dsContrCatgList);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNmBase", DS_CONTR_BASE_USG_NM_B);
        paramMap.put("dsContrBaseUsgNmUsage", DS_CONTR_BASE_USG_NM_U);
        // START 2016/01/26 T.Iwamoto [QC#3744, ADD]
        paramMap.put("doNotRenew", CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
        // END 2016/01/26 T.Iwamoto [QC#3744, ADD]
        String[] dsContrDtlTpList = new String[] {DS_CONTR_DTL_TP.FLEET, DS_CONTR_DTL_TP.AGGREGATE };
        paramMap.put("dsContrDtlTpList", dsContrDtlTpList);
        // START 2018/04/11 K.Kojima [QC#24798,ADD]
        paramMap.put("dsContrDtlTpAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        // END 2018/04/11 K.Kojima [QC#24798,ADD]
        return paramMap;
    }

    /**
     * setQavalidation
     * @return
     */
    private NSZC057001PMsg setQavalidation(String dsContrNum) {
        NSZC057001PMsg qaValPMsg = new NSZC057001PMsg();
        setValue(qaValPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(qaValPMsg.slsDt, this.slsDt);
        setValue(qaValPMsg.dsContrNum, dsContrNum);
        return qaValPMsg;
    }

    /**
     * isFleetMachine
     * @param dsContrCatgCd
     * @param dsContrDtlTpCd
     * @return
     */
    private boolean isFleetMachine(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            return true;
        }
        return false;
    }

    /**
     * isFleetOrAggMachine
     * @param dsContrCatgCd
     * @param dsContrDtlTpCd
     * @return
     */
    private boolean isFleetOrAggMachine(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            return true;
        }
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            return true;
        }
        return false;
    }

    /**
     * callUpdateApi
     * @param pMsg
     * @param errList
     * @return
     */
    private boolean callUpdateApi(NSZC046001PMsg pMsg, List<String> errList) {
        new NSZC046001().execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

        // START 2016/01/25 T.Iwamoto [QC#3532, ADD]
        // Renewal Hold Check
        boolean apiErrFlg = false;
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            apiErrFlg = true;
        }
        for (int i = 0; i < pMsg.xxContrDtlList.getValidCount(); i++) {
            if (hasValue(pMsg.xxContrDtlList.no(i).xxDsMultMsgDplyTxt_DT)) {
                apiErrFlg = true;
                break;
            }
        }
        for (int j = 0; j < pMsg.xxDsContrBllgMtrList.getValidCount(); j++) {
            if (hasValue(pMsg.xxDsContrBllgMtrList.no(j).xxDsMultMsgDplyTxt)) {
                apiErrFlg = true;
                break;
            }
        }
        for (int k = 0; k < pMsg.xxContrXsCopyList.getValidCount(); k++) {
            if (hasValue(pMsg.xxContrXsCopyList.no(k).xxDsMultMsgDplyTxt)) {
                apiErrFlg = true;
                break;
            }
        }

        // Renewal Hold By UpdateAPI
        if (apiErrFlg) {
            if (!renewalHoldByUpdateApi(pMsg.dsContrPk.getValue())) {
                addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), pMsg.dsContrNum.getValue());
                return false;
            }
        }
        // END 2016/01/25 T.Iwamoto [QC#3532, ADD]
        return true;
    }

    /**
     * renewalHoldByUpdateApi
     * @param dsContrPk
     * @return
     */
    private boolean renewalHoldByUpdateApi(BigDecimal dsContrPk) {

        // get Renewal Hold Traget
        List<Map<String, Object>> renewTargetList = renewalHoldTargetByUpdateApi(dsContrPk);

        // update DS Contract Price Effective
        for (Map<String, Object> renewTarget : renewTargetList) {
            DS_CONTR_PRC_EFFTMsg prcEff = getPrcEffForUpdate((BigDecimal) renewTarget.get("DS_CONTR_PRC_EFF_PK"));
            ZYPEZDItemValueSetter.setValue(prcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD);
            S21ApiTBLAccessor.update(prcEff);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEff.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * renewalHoldTargetByUpdateApi
     * @param dsContrPk
     * @return
     */
    private List<Map<String, Object>> renewalHoldTargetByUpdateApi(BigDecimal dsContrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("renewalHoldTargetByUpdateApi", paramMap);
    }

    /**
     * getPrcEffForUpdate
     * @param dsContrPrcEffPk
     * @return
     */
    private DS_CONTR_PRC_EFFTMsg getPrcEffForUpdate(BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg tMsg = new DS_CONTR_PRC_EFFTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcEffPk, dsContrPrcEffPk);

        return (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
    }

    /**
     * callQaValidationApi
     * @param pMsg
     * @param errList
     * @return
     */
    private boolean callQaValidationApi(NSZC057001PMsg pMsg, List<String> errList, NSZC046001PMsg updatePMsg) {
        new NSZC057001().execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            addErrList(errList, S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue()), pMsg.dsContrNum.getValue());
            return false;
        }

        // START 2016/01/25 T.Iwamoto [QC#3532, ADD]
        // Renewal Hold By QA Validation
        if (!renewalHoldByQAValidation(updatePMsg)) {
            addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), pMsg.dsContrNum.getValue());
            return false;
        }
        // END 2016/01/25 T.Iwamoto [QC#3532, ADD]

        return true;
    }

    /**
     * renewalHoldByQAValidation
     * @param updatePMsg
     * @return
     */
    private boolean renewalHoldByQAValidation(NSZC046001PMsg updatePMsg) {

        // check ValidationResult
        if (checkValidationResult(updatePMsg.dsContrPk.getValue())) {
            return true;
        }

        // get Renewal Hold Traget
        for (int i = 0; i < updatePMsg.xxContrDtlList.getValidCount(); i++) {
            BigDecimal dsContrDtlPk = updatePMsg.xxContrDtlList.no(i).dsContrDtlPk.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(updatePMsg.xxContrDtlList.no(i).rnwBaseFlg.getValue())) {
                BigDecimal dsContrPrcEffPk = renewalHoldTargetByQaValidation(dsContrDtlPk, null);
                if (!hasValue(dsContrPrcEffPk)) {
                    continue;
                }

                // update DS Contract Price Effective
                DS_CONTR_PRC_EFFTMsg prcEff = getPrcEffForUpdate(dsContrPrcEffPk);
                ZYPEZDItemValueSetter.setValue(prcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD);
                S21ApiTBLAccessor.update(prcEff);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEff.getReturnCode())) {
                    return false;
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(updatePMsg.xxContrDtlList.no(i).rnwUsgFlg.getValue())) {
                for (int j = 0; j < updatePMsg.xxDsContrBllgMtrList.getValidCount(); j++) {
                    if (dsContrDtlPk.compareTo(updatePMsg.xxDsContrBllgMtrList.no(j).dsContrDtlPk.getValue()) == 0) {
                        BigDecimal dsContrPrcEffPk = renewalHoldTargetByQaValidation(dsContrDtlPk, updatePMsg.xxDsContrBllgMtrList.no(j).dsContrBllgMtrPk.getValue());
                        if (!hasValue(dsContrPrcEffPk)) {
                            continue;
                        }

                        // update DS Contract Price Effective
                        DS_CONTR_PRC_EFFTMsg prcEff = getPrcEffForUpdate(dsContrPrcEffPk);
                        ZYPEZDItemValueSetter.setValue(prcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD);
                        S21ApiTBLAccessor.update(prcEff);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEff.getReturnCode())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * checkValidationResult
     * @param glblCmpyCd
     * @param dsContrPk
     * @return
     */
    private boolean checkValidationResult(BigDecimal dsContrPk) {
        DS_CONTR_VLD_RSLT_WRKTMsg inMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPk01", dsContrPk);
        inMsg.setConditionValue("dsContrVldStsCd01", DS_CONTR_VLD_STS.ERROR);
        int rsCnt = EZDTBLAccessor.count(inMsg);
        if (rsCnt == 0) {
            return true;
        }
        return false;
    }

    /**
     * renewalHoldTargetByQAValidationBase
     * @param glblCmpyCd
     * @param dsContrNum
     * @return
     */
    private BigDecimal renewalHoldTargetByQaValidation(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (BigDecimal) ssmBatchClient.queryObject("renewalHoldTargetByQaValidation", paramMap);
    }

    /**
     * addErrList
     * @param errList
     * @param msgTxt
     * @param dsContrNum
     */
    private void addErrList(List<String> errList, String msgTxt, String dsContrNum) {
        errList.add(ZYPCommonFunc.concatString(msgTxt, " :Contract#: ", dsContrNum));

    }

    /**
     * updateDsContrDtl
     * @param inMsgLst
     * @return
     */
    private boolean updateDsContrDtl(List<DS_CONTR_DTLTMsg> inMsgLst) {
        DS_CONTR_DTLTMsg[] outTMsgArray = new DS_CONTR_DTLTMsg[inMsgLst.size()];
        List<DS_CONTR_DTLTMsg> outTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();

        for (DS_CONTR_DTLTMsg inTMsg : inMsgLst) {
            setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inTMsg.dsContrDtlPk, inTMsg.dsContrDtlPk);
            DS_CONTR_DTLTMsg outTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
            if (outTMsg == null) {
                return false;
            }
            setValue(outTMsg.uplftEffFromDt, inTMsg.uplftEffFromDt);
            outTMsgList.add(outTMsg);
        }
        S21FastTBLAccessor.update(outTMsgList.toArray(outTMsgArray));
        return true;
    }

    /**
     * setUpdateApiParamForHdr
     * @param pMsg
     * @param curDsContrPk
     * @param dsContrCatgCd
     */
    private void setUpdateApiParamForHdr(NSZC046001PMsg pMsg, BigDecimal curDsContrPk, String dsContrCatgCd) {
        if (hasValue(pMsg.glblCmpyCd)) {
            return;
        }
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.xxModeCd, NSZC046001Constant.MODE_RENEWAL);
        setValue(pMsg.slsDt, this.slsDt);
        setValue(pMsg.dsContrPk, curDsContrPk);
        setValue(pMsg.dsContrCatgCd, dsContrCatgCd);
        setValue(pMsg.psnCd, SYSTEM_PSN_CD);
    }

    /**
     * isAggLineBrank
     * @param dsContrDtlTpCd
     * @param dsContrDtl
     * @return
     */
    private boolean isAggLineBrank(String dsContrDtlTpCd, Map<String, Object> dsContrDtl) {
        if (!DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            return false;
        }
        if (!hasValue((BigDecimal) dsContrDtl.get("BASE_PRC_UP_RATIO"))) {
            return true;
        }
        if (!hasValue((String) dsContrDtl.get("RNW_PRC_METH_CD"))) {
            return true;
        }
        return false;
    }

    /**
     * setUpdateApiParamForBase
     * @param pMsg
     * @param dsContrDtlPk
     * @param dsContrDtlTpCd
     * @param dsContrDtl
     * @param newBasePrcDealAmt
     * @param newCalPrcAmtRate
     * @param maxBasePrcDealAmt
     * @param maxCalPrcAmtRate
     * @param svcMachMstrPk
     */
    // START 2017/09/08 K.Kitachi [QC#20557, MOD]
    // START 2018/03/29 K.Kojima [QC#24889,MOD]
    // private void setUpdateApiParamForBase(NSZC046001PMsg pMsg, BigDecimal dsContrDtlPk, String dsContrDtlTpCd, Map<String, Object> dsContrDtl, BigDecimal newBasePrcDealAmt, BigDecimal newCalPrcAmtRate, BigDecimal svcMachMstrPk) {
    // START 2018/06/18 U.Kim [QC#24903,MOD]
    // private void setUpdateApiParamForBase(NSZC046001PMsg pMsg, BigDecimal dsContrDtlPk, String dsContrDtlTpCd, Map<String, Object> dsContrDtl, BigDecimal newBasePrcDealAmt, BigDecimal newCalPrcAmtRate, BigDecimal curBasePrcDealAmt, BigDecimal curCalPrcAmtRate, BigDecimal svcMachMstrPk) {
    private void setUpdateApiParamForBase(NSZC046001PMsg pMsg, BigDecimal dsContrDtlPk, String dsContrDtlTpCd, Map<String, Object> dsContrDtl, BigDecimal newBasePrcDealAmt, BigDecimal newCalPrcAmtRate, BigDecimal maxBasePrcDealAmt, BigDecimal maxCalPrcAmtRate, BigDecimal svcMachMstrPk) {
    // END 2018/06/18 U.Kim [QC#24903,MOD]
    // END 2018/03/29 K.Kojima [QC#24889,MOD]
    // END 2017/09/08 K.Kitachi [QC#20557, MOD]

        int i = pMsg.xxContrDtlList.getValidCount();
        setValue(pMsg.xxContrDtlList.no(i).dsContrDtlPk, dsContrDtlPk);
        setValue(pMsg.xxContrDtlList.no(i).dsContrDtlTpCd, dsContrDtlTpCd);
        setValue(pMsg.xxContrDtlList.no(i).contrEffThruDt, (String) dsContrDtl.get("NEW_EFF_THRU_DT"));
        // START 2016/01/25 T.Iwamoto [QC#3668, ADD]
        setValue(pMsg.xxContrDtlList.no(i).contrRnwTotCnt, (BigDecimal) dsContrDtl.get("CONTR_RNW_TOT_CNT"));
        // END 2016/01/25 T.Iwamoto [QC#3668, ADD]
        setValue(pMsg.xxContrDtlList.no(i).basePrcDealAmt, newBasePrcDealAmt);
        setValue(pMsg.xxContrDtlList.no(i).basePrcTermDealAmtRate, newCalPrcAmtRate);
        // START 2016/01/25 T.Iwamoto [QC#3531, ADD]
        setValue(pMsg.xxContrDtlList.no(i).rnwBaseFlg, ZYPConstant.FLG_ON_Y);
        // END 2016/01/25 T.Iwamoto [QC#3531, ADD]
        // START 2017/09/08 K.Kitachi [QC#20557, ADD]
        setValue(pMsg.xxContrDtlList.no(i).svcMachMstrPk, svcMachMstrPk);
        // END 2017/09/08 K.Kitachi [QC#20557, ADD]

        // 2.3 call Contr Renewal Pre-Check
        String contrRnwErrRsnCd = callContrRenewalPreCheck(dsContrDtlPk, newBasePrcDealAmt, null, null, null);
        if (hasValue(contrRnwErrRsnCd)) {
            setValue(pMsg.xxContrDtlList.no(i).contrRnwErrRsnCd, contrRnwErrRsnCd);
            // START 2017/10/06 M.Kidokoro [QC#21546,ADD]
            // START 2018/03/29 K.Kojima [QC#24889,MOD]
            // setValue(pMsg.xxContrDtlList.no(i).basePrcDealAmt, (BigDecimal) dsContrDtl.get("BASE_PRC_DEAL_AMT"));
            // setValue(pMsg.xxContrDtlList.no(i).basePrcTermDealAmtRate, (BigDecimal) dsContrDtl.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
            // START 2018/06/18 U.Kim [QC#24903,MOD]
            // setValue(pMsg.xxContrDtlList.no(i).basePrcDealAmt, curBasePrcDealAmt);
            // setValue(pMsg.xxContrDtlList.no(i).basePrcTermDealAmtRate, curCalPrcAmtRate);
            setValue(pMsg.xxContrDtlList.no(i).basePrcDealAmt, maxBasePrcDealAmt);
            setValue(pMsg.xxContrDtlList.no(i).basePrcTermDealAmtRate, maxCalPrcAmtRate);
            // END 2018/06/18 U.Kim [QC#24903,MOD]
            // END 2018/03/29 K.Kojima [QC#24889,MOD]
            // END 2017/10/06 M.Kidokoro [QC#21546,ADD]
        }
        pMsg.xxContrDtlList.setValidCount(i + 1);
    }

    /**
     * setUpdateApiParamForMtr
     * @param pMsg
     * @param dsContrOrcEff
     * @param newXsMtrAmtRate
     * @param dsContrDtlPk
     * @param setFlg
     * @param svcMachMstrPk
     * @param maxPrcUpRatio
     * @param aftDeclPntDigitNum
     */
    // START 2017/09/08 K.Kitachi [QC#20557, MOD]
    // START 2018/06/18 U.Kim [QC#24903,MOD]
    // private void setUpdateApiParamForMtr(NSZC046001PMsg pMsg, Map<String, Object> dsContrOrcEff, BigDecimal newXsMtrAmtRate, BigDecimal dsContrDtlPk, boolean setFlg, BigDecimal svcMachMstrPk) {
    // START 2021/12/02 R.Cabral [QC#59352,MOD]
    // private void setUpdateApiParamForMtr(NSZC046001PMsg pMsg, Map<String, Object> dsContrOrcEff, BigDecimal newXsMtrAmtRate, BigDecimal dsContrDtlPk, boolean setFlg, BigDecimal svcMachMstrPk, BigDecimal maxPrcUpRatio, BigDecimal aftDeclPntDigitNum) {
    private void setUpdateApiParamForMtr(NSZC046001PMsg pMsg, Map<String, Object> dsContrOrcEff, BigDecimal newXsMtrAmtRate, BigDecimal dsContrDtlPk, boolean setFlg, BigDecimal svcMachMstrPk, BigDecimal maxPrcUpRatio, BigDecimal aftDeclPntDigitNum,
            String dsContrCatgCd, String dsContrDtlTpCd, String bllgMtrLbCd, List<Map<String, Object>> aggMtrPrcList) {
    // END   2021/12/02 R.Cabral [QC#59352,MOD]
    // END 2018/06/18 U.Kim [QC#24903,MOD]
    // END 2017/09/08 K.Kitachi [QC#20557, MOD]
        BigDecimal dsContrBllgMtrPk = (BigDecimal) dsContrOrcEff.get("DS_CONTR_BLLG_MTR_PK");
        BigDecimal contrXsCopyPk = (BigDecimal) dsContrOrcEff.get("CONTR_XS_COPY_PK");
        BigDecimal xsMtrCopyQty = (BigDecimal) dsContrOrcEff.get("XS_MTR_COPY_QTY");
        // START 2017/10/06 M.Kidokoro [QC#21546,ADD]
        BigDecimal xsMtrAmtRate = (BigDecimal) dsContrOrcEff.get("XS_MTR_AMT_RATE");
        // END 2017/10/06 M.Kidokoro [QC#21546,ADD]
        String xsMtrFirstFlg = (String) dsContrOrcEff.get("XS_MTR_FIRST_FLG");
        // START 2012/12/01 R.Jin [QC#60880,MOD]
        String tirNum = String.valueOf(dsContrOrcEff.get("TIRNUM"));
        // END 2012/12/01 R.Jin [QC#60880,MOD]

        int i = pMsg.xxDsContrBllgMtrList.getValidCount();
        if (i == 0 || dsContrBllgMtrPk.compareTo(pMsg.xxDsContrBllgMtrList.no(i - 1).dsContrBllgMtrPk.getValue()) != 0) {
            setValue(pMsg.xxDsContrBllgMtrList.no(i).dsContrDtlPk, dsContrDtlPk);
            setValue(pMsg.xxDsContrBllgMtrList.no(i).dsContrBllgMtrPk, dsContrBllgMtrPk);
            // START 2017/09/08 K.Kitachi [QC#20557, ADD]
            setValue(pMsg.xxDsContrBllgMtrList.no(i).svcMachMstrPk, svcMachMstrPk);
            // END 2017/09/08 K.Kitachi [QC#20557, ADD]
            pMsg.xxDsContrBllgMtrList.setValidCount(i + 1);
        }

        int j = pMsg.xxContrXsCopyList.getValidCount();
        setValue(pMsg.xxContrXsCopyList.no(j).dsContrBllgMtrPk, dsContrBllgMtrPk);
        if (setFlg) {
            setValue(pMsg.xxContrXsCopyList.no(j).contrXsCopyPk, contrXsCopyPk);
            setValue(pMsg.xxContrXsCopyList.no(j).xsMtrCopyQty, xsMtrCopyQty);
            setValue(pMsg.xxContrXsCopyList.no(j).xsMtrAmtRate, newXsMtrAmtRate);
            setValue(pMsg.xxContrXsCopyList.no(j).xsMtrFirstFlg, xsMtrFirstFlg);
        }

        // 2.3 call Contr Renewal Pre-Check
        String contrRnwErrRsnCd = callContrRenewalPreCheck(dsContrDtlPk, null, dsContrBllgMtrPk, xsMtrCopyQty, newXsMtrAmtRate);
        if (hasValue(contrRnwErrRsnCd)) {
            setValue(pMsg.xxContrXsCopyList.no(j).contrRnwErrRsnCd, contrRnwErrRsnCd);
            // START 2017/10/06 M.Kidokoro [QC#21546,ADD]
            if (setFlg) {
                // START 2018/06/18 U.Kim [QC#24903,MOD]
                // setValue(pMsg.xxContrXsCopyList.no(j).xsMtrAmtRate, xsMtrAmtRate);
                setValue(pMsg.xxContrXsCopyList.no(j).xsMtrAmtRate, recalcValueForMaxRate(xsMtrAmtRate, maxPrcUpRatio, aftDeclPntDigitNum));
                // END 2018/06/18 U.Kim [QC#24903,MOD]
            }
            // END 2017/10/06 M.Kidokoro [QC#21546,ADD]
        }
        // START 2021/12/02 R.Cabral [QC#59352,ADD]
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            if (!isFleetOrAggMachine(dsContrCatgCd, dsContrDtlTpCd)) {
                if (ZYPCommonFunc.hasValue(pMsg.xxContrXsCopyList.no(j).xsMtrAmtRate)) {
                    HashMap<String, Object> aggPrc = new HashMap<String, Object>();
                    // START 2012/12/01 R.Jin [QC#60880,MOD]
//                    aggPrc.put(bllgMtrLbCd, pMsg.xxContrXsCopyList.no(j).xsMtrAmtRate.getValue());
                    aggPrc.put(bllgMtrLbCd.concat(tirNum), pMsg.xxContrXsCopyList.no(j).xsMtrAmtRate.getValue());
                    // END 2012/12/01 R.Jin [QC#60880,MOD]
                    aggMtrPrcList.add(aggPrc);
                }
            } else {
                for (int idx = 0; idx < aggMtrPrcList.size(); idx++) {
                    HashMap<String, Object> aggPrc = (HashMap<String, Object>) aggMtrPrcList.get(idx);
                    // START 2012/12/01 R.Jin [QC#60880,MOD]
//                    if (aggPrc.containsKey(bllgMtrLbCd)) {
                    if (aggPrc.containsKey(bllgMtrLbCd.concat(tirNum))) {
//                        setValue(pMsg.xxContrXsCopyList.no(j).xsMtrAmtRate, (BigDecimal) aggPrc.get(bllgMtrLbCd));
                        setValue(pMsg.xxContrXsCopyList.no(j).xsMtrAmtRate, (BigDecimal) aggPrc.get(bllgMtrLbCd.concat(tirNum)));
                        break;
                     // END 2012/12/01 R.Jin [QC#60880,MOD]
                    }
                }
            }
        }
        // END   2021/12/02 R.Cabral [QC#59352,ADD]
        pMsg.xxContrXsCopyList.setValidCount(j + 1);

        // START 2016/01/25 T.Iwamoto [QC#3531, ADD]
        for (int k = 0; k < pMsg.xxContrDtlList.getValidCount(); k++) {
            if (dsContrDtlPk.compareTo(pMsg.xxContrDtlList.no(k).dsContrDtlPk.getValue()) == 0) {
                setValue(pMsg.xxContrDtlList.no(k).rnwUsgFlg, ZYPConstant.FLG_ON_Y);
            }
        }
        // END 2016/01/25 T.Iwamoto [QC#3531, ADD]

    }

    /**
     * getCalPrcAmtRate
     * @param effFromDt
     * @param newBasePrcDealAmt
     * @param dsContrDtlBaseList
     * @return
     */
    private BigDecimal getCalPrcAmtRate(String effFromDt, BigDecimal newBasePrcDealAmt, List<Map<String, Object>> dsContrDtlBaseList) {
        CalcTermAmtBean inBean = new CalcTermAmtBean();
        inBean.setGlblCmpyCd(this.glblCmpyCd);
        inBean.setXxModeCd(NSXC003001CalcTermAmt.MODE01_CONTRACT);
        inBean.setSlsDt(this.slsDt);
        inBean.setCcyCd((String) dsContrDtlBaseList.get(0).get("CCY_CD"));
        inBean.setContrPrcEffFromDt(effFromDt);
        inBean.setContrPrcEffThruDt((String) dsContrDtlBaseList.get(0).get("NEW_EFF_THRU_DT"));
        inBean.setContrCloDay((String) dsContrDtlBaseList.get(0).get("CONTR_CLO_DAY"));
        inBean.setBllgCycleCd((String) dsContrDtlBaseList.get(0).get("BASE_BLLG_CYCLE_CD"));
        inBean.setBasePrcDealAmt(newBasePrcDealAmt);
        return NSXC003001CalcTermAmt.calcTermAmt(inBean);
    }

    /**
     * calcNewBasePrcDealAmt
     * @param curBasePrcDealAmt
     * @param curBasePrcUpRatio
     * @param aftDeclPntDigitNum
     * @return
     */
    private BigDecimal calcNewBasePrcDealAmt(BigDecimal curBasePrcDealAmt, BigDecimal curBasePrcUpRatio, BigDecimal aftDeclPntDigitNum) {
        if (!hasValue(curBasePrcDealAmt)) {
            return null;
        }
        if (!hasValue(curBasePrcUpRatio)) {
            return null;
        }
        BigDecimal hundred = new BigDecimal("100");
        int digitNum = 2;
        if (hasValue(aftDeclPntDigitNum)) {
            digitNum = aftDeclPntDigitNum.intValue();
        }
        // mod start 2016/02/05 CSA Defect#3996
        return curBasePrcDealAmt.multiply((hundred.add(curBasePrcUpRatio)).divide(hundred)).setScale(digitNum, BigDecimal.ROUND_HALF_UP);
        // mod end 2016/02/05 CSA Defect#3996
    }

    /**
     * calcNewXsMtrAmtRate
     * @param curXsMtrAmtRate
     * @param curPrcUpRatio
     * @param aftDeclPntDigitNum
     * @return
     */
    private BigDecimal calcNewXsMtrAmtRate(BigDecimal curXsMtrAmtRate, BigDecimal curPrcUpRatio, BigDecimal aftDeclPntDigitNum) {
        if (!hasValue(curXsMtrAmtRate)) {
            return null;
        }
        if (!hasValue(curPrcUpRatio)) {
            return null;
        }
        BigDecimal hundred = new BigDecimal("100");
        int digitNum = 2;
        if (hasValue(aftDeclPntDigitNum)) {
            digitNum = aftDeclPntDigitNum.intValue();
        }
        // mod start 2016/02/05 CSA Defect#3996
        return curXsMtrAmtRate.multiply((hundred.add(curPrcUpRatio)).divide(hundred)).setScale(digitNum, BigDecimal.ROUND_HALF_UP);
        // mod end 2016/02/05 CSA Defect#3996
    }

    /**
     * getDsContrDtlBase
     * @param curDsContrPk
     * @param curDsContrDtlPk
     * @return
     */
    private List<Map<String, Object>> getDsContrDtlBase(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk) {
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRenewalDetail", setParamForDsContrDtl(curDsContrPk, curDsContrDtlPk, true));
    }

    /**
     * getDsContrDtlBaseNotFleetMachine
     * @param curDsContrPk
     * @param curDsContrDtlPk
     */
    private List<Map<String, Object>> getDsContrDtlBaseNotFleetMachine(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk) {
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRenewalDetailNotRegularMachine", setParamForDsContrDtl(curDsContrPk, curDsContrDtlPk, true));
    }

    /**
     * getDsContrDtlUsage
     * @param curDsContrPk
     * @param curDsContrDtlPk
     */
    private List<Map<String, Object>> getDsContrDtlUsage(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk) {
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRenewalDetail", setParamForDsContrDtl(curDsContrPk, curDsContrDtlPk, false));
    }

    /**
     * @param curDsContrPk
     * @param curDsContrDtlPk
     */
    private List<Map<String, Object>> getDsContrDtlUsageNotRegularMachine(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk) {
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRenewalDetailNotRegularMachine", setParamForDsContrDtl(curDsContrPk, curDsContrDtlPk, false));
    }

    /**
     * setParamForDsContrDtl
     * @param curDsContrPk
     * @param curDsContrDtlPk
     * @param baseFlg
     */
    private Map<String, Object> setParamForDsContrDtl(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk, boolean baseFlg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", curDsContrPk);
        paramMap.put("dsContrDtlPk", curDsContrDtlPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        if (baseFlg) {
            paramMap.put("baseFlg", ZYPConstant.FLG_ON_Y);
            paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_B);
        } else {
            paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_U);
        }
        String[] dsContrDtlTpList = new String[] {DS_CONTR_DTL_TP.FLEET, DS_CONTR_DTL_TP.AGGREGATE };
        paramMap.put("dsContrDtlTpList", dsContrDtlTpList);
        return paramMap;
    }

    /**
     * setParamForUsagePrcEff
     * @param curDsContrDtlPk
     * @return
     */
    private Map<String, Object> setParamForUsagePrcEff(BigDecimal curDsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", curDsContrDtlPk);
        return paramMap;
    }

    /**
     * getUsagePrcEff
     * @param curDsContrDtlPk
     * @return
     */
    private List<Map<String, Object>> getUsagePrcEff(BigDecimal curDsContrDtlPk) {
        return ssmBatchClient.queryObjectList("getUsagePrcEff", setParamForUsagePrcEff(curDsContrDtlPk));
    }

    /**
     * getDsContrPrcEff
     * @param curDsContrDtlPk
     * @return
     */
    private DS_CONTR_PRC_EFFTMsg getDsContrPrcEff(BigDecimal curDsContrDtlPk) {
        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();
        inTMsg.setSQLID("006");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("dsContrDtlPk01", curDsContrDtlPk);
        DS_CONTR_PRC_EFFTMsgArray outTmsgArray = (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTmsgArray.getValidCount() == 0) {
            return null;
        }
        return outTmsgArray.no(0);
    }

    /**
     * setParamForModelUpdateRule
     * @param curDsContrDtlPk
     * @param effFromDt
     * @param dsContrBllgMtrPk
     * @return
     */
    private Map<String, Object> setParamForModelUpdateRule(BigDecimal curDsContrDtlPk, String effFromDt, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", curDsContrDtlPk);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        paramMap.put("effFromDt", effFromDt);
        paramMap.put("maxDt", MAX_DATE);
        String[] dsContrDtlTpList = new String[] {DS_CONTR_DTL_TP.FLEET, DS_CONTR_DTL_TP.AGGREGATE };
        paramMap.put("dsContrDtlTpList", dsContrDtlTpList);
        return paramMap;
    }

    /**
     * getModelUpdateRuleBase
     * @param curDsContrDtlPk
     * @param effFromDt
     * @param dsContrDtlTpCd
     * @return
     */
    private BigDecimal getModelUpdateRuleBase(BigDecimal curDsContrDtlPk, String effFromDt, String dsContrDtlTpCd) {
        List<BigDecimal> rsltList = new ArrayList<BigDecimal>();
        // START 2017/12/06 [QC#22891,MOD]
        //if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
        //    rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleBaseForLine", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, null));
        //} else {
        //    rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleBase", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, null));
        //}
        if (!DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleBase", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, null));
        }
        // END 2017/12/06 [QC#22891,MOD]
        if (rsltList.size() == 0) {
            return this.defModelUpdateRuleBase;
        }
        return rsltList.get(0);
    }

    /**
     * getModelUpdateRuleMtr
     * @param curDsContrDtlPk
     * @param effFromDt
     * @param dsContrDtlTpCd
     * @param dsContrBllgMtrPk
     * @return
     */
    private BigDecimal getModelUpdateRuleMtr(BigDecimal curDsContrDtlPk, String effFromDt, String dsContrDtlTpCd, BigDecimal dsContrBllgMtrPk, String dsContrCatgCd) {
        List<BigDecimal> rsltList = new ArrayList<BigDecimal>();
        // START 2017/12/06 [QC#22891,MOD]
        //if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
        //    rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleMtrForLine", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, dsContrBllgMtrPk));
        //} else {
        //    rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleMtr", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, dsContrBllgMtrPk));
        //}
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            rsltList = ssmBatchClient.queryObjectList("getModelUpdateRuleMtr", setParamForModelUpdateRule(curDsContrDtlPk, effFromDt, dsContrBllgMtrPk));
        }
        if (rsltList.size() == 0) {
            return this.defModelUpdateRuleUsage;
        }
        return rsltList.get(0);
    }

    /**
     * callContrRenewalPreCheck
     * @param dsContrDtlPk
     * @param basePrcDealAmt
     * @param dsContrBllgMtrPk
     * @param xsMtrCopyQty
     * @param xsMtrAmtRate
     * @return
     */
    private String callContrRenewalPreCheck(BigDecimal dsContrDtlPk, BigDecimal basePrcDealAmt, BigDecimal dsContrBllgMtrPk, BigDecimal xsMtrCopyQty, BigDecimal xsMtrAmtRate) {
        List<ContrRenewalPreCheckInfo> inBeanList = new ArrayList<ContrRenewalPreCheckInfo>();
        ContrRenewalPreCheckInfo inBean = new ContrRenewalPreCheckInfo();
        inBean.setGlblCmpyCd(this.glblCmpyCd);
        inBean.setDsContrDtlPk(dsContrDtlPk);
        inBean.setBasePrcDealAmt(basePrcDealAmt);
        inBean.setDsContrBllgMtrPk(dsContrBllgMtrPk);
        inBean.setXsMtrCopyQty(xsMtrCopyQty);
        inBean.setXsMtrAmtRate(xsMtrAmtRate);
        inBeanList.add(inBean);

        NSXC001001ContrRenewalPreCheck calculator = new NSXC001001ContrRenewalPreCheck(inBeanList);
        calculator.contrRenewalPreCheck();
        for (ContrRenewalPreCheckInfo rsBean : inBeanList) {
            if (hasValue(rsBean.getContrRnwErrRsnCd())) {
                return rsBean.getContrRnwErrRsnCd();
            }
        }
        return null;
    }

    /**
     * setParamForLetter
     * @param newFlg
     * @return
     */
    private Map<String, Object> setParamForLetter(boolean newFlg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        String[] dsContrCatgList = new String[] {DS_CONTR_CATG.REGULAR, DS_CONTR_CATG.AGGREGATE };
        paramMap.put("dsContrCatgList", dsContrCatgList);
        paramMap.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        if (newFlg) {
            String[] dsContrCtrlStsList = new String[] {DS_CONTR_CTRL_STS.RENEWAL_HOLD };
            paramMap.put("dsContrCtrlStsList", dsContrCtrlStsList);
        } else {
            String[] dsContrCtrlStsList = new String[] {DS_CONTR_CTRL_STS.ACTIVE, DS_CONTR_CTRL_STS.SINGED };
            paramMap.put("dsContrCtrlStsList", dsContrCtrlStsList);
        }
        // START 2017/08/23 M.Naito [QC#8661, ADD]
        paramMap.put("slsDt", this.slsDt);
        // END 2017/08/23 M.Naito [QC#8661, ADD]
        return paramMap;
    }

    // START 2017/08/23 M.Naito [QC#8661, MOD]
    /**
     * setLetterApiParam
     * @param letterForNewPMsg
     * @param dsContrPk
     * @param dsContrDtlPk
     */
//    private void setLetterApiParam(NSZC100001PMsg letterForNewPMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
    private void setLetterApiParam(NSZC100001PMsg letterForNewPMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String printOldPrcFlg, String dsAcctNum, String billToCustCd) {
        String rptId = null;

        // check Po Required
        boolean poReq = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, billToCustCd, ONBATCH_TYPE.BATCH);

        setValue(letterForNewPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(letterForNewPMsg.slsDt, this.slsDt);
        setValue(letterForNewPMsg.bizAppId, BIZ_APP_ID);
        setValue(letterForNewPMsg.otptOpCd, OTPT_OP_CD);
        setValue(letterForNewPMsg.dsContrPk, dsContrPk);
        if (ZYPConstant.FLG_ON_Y.equals(printOldPrcFlg)) {
            if (poReq) {
                rptId = RPT_ID_NSAF0050;
            } else {
                rptId = RPT_ID_NSAF0030;
            }
        } else {
            if (poReq) {
                rptId = RPT_ID_NSAF0060;
            } else {
                rptId = RPT_ID_NSAF0040;
            }
        }
        setValue(letterForNewPMsg.rptId, rptId);
        int cnt = letterForNewPMsg.dsContrDtlPkList.getValidCount();
        setValue(letterForNewPMsg.dsContrDtlPkList.no(cnt).dsContrDtlPk, dsContrDtlPk);
        letterForNewPMsg.dsContrDtlPkList.setValidCount(cnt + 1);
    }
    // END 2017/08/23 M.Naito [QC#8661, MOD]

    /**
     * callLetterApi
     * @param pMsg
     * @param errList
     * @param dsContrNum
     * @return
     */
    private boolean callLetterApi(NSZC100001PMsg pMsg, List<String> errList, String dsContrNum) {
        new NSZC100001().execute(pMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            addErrList(errList, S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue()), dsContrNum);
            return false;
        }
        return true;
    }

    /**
     * sendErrMail
     * @param errList
     */
    private void sendErrMail(List<String> errList) {
        NSXC001001SendMailForErrorInfo errorInfo = new NSXC001001SendMailForErrorInfo();
        errorInfo.addErrMsgList(errList);
        errorInfo.sendMail(this.glblCmpyCd, BATCH_ID);
    }

    // START 2023/07/06 K.Watanabe [QC#61145,ADD]
    /**
     * sendErrMailEol
     * @param errList
     */
    private void sendErrMailEol(List<String> errList) {

        // 1. Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }

        // 2. Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        // START 2023/07/12 K.Watanabe [QC#61145,DEL]
        // groupTo.setMailKey1(MAIL_KEY_TO);
        // END 2023/07/12 K.Watanabe [QC#61145,DEL]
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(NSAM0069E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // 3. Create message for Body
        StringBuilder msgBuf = new StringBuilder();
        for (String wkStr : errList) {
            msgBuf.append(wkStr);
            msgBuf.append(LINE_FEED_CODE);
        }
        String message = msgBuf.toString();

        // 4. Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID_02);
        if (template == null) {
            throw new S21AbendException(NSAM0069E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID_02 });
        }
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);

        // 5. Call Mail API
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

    }
    // END 2023/07/06 K.Watanabe [QC#61145,ADD]

    // mod start 2016/05/25 CSA QC#447
    /**
     * eolCheck
     * @param 
     * @return
     */
    private boolean eolCheck(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk, String dsContrCatgCd, String dsContrDtlTpCd, String dsContrNum, List<String> errListEOL) {

        boolean checkResult = true;
        if (!DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) && !(DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd))) {
            // get SvcMachMstrPk from DS_CONTR_DTL
            DS_CONTR_DTLTMsg dtlTMsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dtlTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlTMsg.dsContrDtlPk, curDsContrDtlPk);
            dtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(dtlTMsg);

            EndOfLifeBean infoBean = new EndOfLifeBean();
            infoBean.setGlblCmpyCd(glblCmpyCd);
            infoBean.setSvcMachMstrPk(dtlTMsg.svcMachMstrPk.getValue());
            // CONTR_EFF_THRU_DT + 1
            infoBean.setEolDt(ZYPDateUtil.addDays(dtlTMsg.contrEffThruDt.getValue(), 1));
            if (NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(infoBean)) {

                // checkError
                if (ZYPConstant.FLG_OFF_N.equals(infoBean.getContrAvalFlg())) {
                    checkResult = false;
                }
            } else {
                checkResult = false;
            }
            if (checkResult) {
                return true;
            } else {
                
                // update
                updateDsContrRnwEsclForEOL(curDsContrPk, curDsContrDtlPk, dsContrCatgCd);
                // insert
                insertSvcMemoAndSvcBatErrLogForEOL(curDsContrPk, curDsContrDtlPk, dtlTMsg.svcMachMstrPk.getValue());
                // send error mail
                // START 2023/07/06 K.Watanabe [QC#61145,MOD]
                //errListEOL.add(createErrorTxt(dtlTMsg.svcMachMstrPk.getValue(), dsContrNum));
                String mdlName = getMdlNm(dtlTMsg.svcMachMstrPk.getValue());
                errListEOL.add(createErrorTxt(dtlTMsg.svcMachMstrPk.getValue(), mdlName, dsContrNum));
                // END 2023/07/06 K.Watanabe [QC#61145,MOD]

                return false;
            }
        }
        return true;
    }

    /**
     * updateDsContrRnwEsclForEOL
     * @param curDsContrDtlPk
     * @return
     */
    private void updateDsContrRnwEsclForEOL(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk, String dsContrCatgCd) {

        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            DS_CONTR_DTLTMsg dtlTMsg = new DS_CONTR_DTLTMsg();
            dtlTMsg.setSQLID("006");
            dtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            dtlTMsg.setConditionValue("dsContrPk01", curDsContrPk);
            dtlTMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.FLEET);
            DS_CONTR_DTLTMsgArray inMsgList = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(dtlTMsg);
            if (inMsgList.length() > 0) {
                curDsContrDtlPk = inMsgList.no(0).dsContrDtlPk.getValue();
            }
        }

        // update DS_CONTR_RNW_ESCL CONTR_AUTO_RNW_TP_CD=01(Do not renew)
        DS_CONTR_RNW_ESCLTMsg esclTMsg = new DS_CONTR_RNW_ESCLTMsg();
        esclTMsg.setSQLID("003");
        esclTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        esclTMsg.setConditionValue("dsContrDtlPk01", curDsContrDtlPk);
        DS_CONTR_RNW_ESCLTMsgArray inMsgList = (DS_CONTR_RNW_ESCLTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(esclTMsg);
        for (int i = 0; i < inMsgList.length(); i++) {
            ZYPEZDItemValueSetter.setValue(inMsgList.no(i).contrAutoRnwTpCd, CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
            S21FastTBLAccessor.update(inMsgList.no(i));
        }
    }

    /**
     * insertSvcMemoAndSvcBatErrLogForEOL
     * @param curDsContrDtlPk
     * @return
     */
    private void insertSvcMemoAndSvcBatErrLogForEOL(BigDecimal curDsContrPk, BigDecimal curDsContrDtlPk, BigDecimal svcMachMstrPK) {
        // insert SVC_MEMO
        SVC_MEMOTMsg memoTMsg = new SVC_MEMOTMsg();
        ZYPEZDItemValueSetter.setValue(memoTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(memoTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        ZYPEZDItemValueSetter.setValue(memoTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        ZYPEZDItemValueSetter.setValue(memoTMsg.svcMemoTpCd, SVC_MEMO_TP.RENEW_CONTRACT_OR_MACHINE);
        ZYPEZDItemValueSetter.setValue(memoTMsg.svcCmntTxt, SVC_CMNT_TXT);
        ZYPEZDItemValueSetter.setValue(memoTMsg.svcMachMstrPk, svcMachMstrPK);
        ZYPEZDItemValueSetter.setValue(memoTMsg.dsContrPk, curDsContrPk);
        ZYPEZDItemValueSetter.setValue(memoTMsg.dsContrDtlPk, curDsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(memoTMsg.lastUpdUsrId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(memoTMsg.lastUpdTs, this.sysTime);
        S21FastTBLAccessor.insert(memoTMsg);

        // insert SVC_BAT_ERR_LOG
        SVC_BAT_ERR_LOGTMsg logTMsg = new SVC_BAT_ERR_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(logTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        ZYPEZDItemValueSetter.setValue(logTMsg.bizAppId, BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrLogTs, this.sysTime);
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrKeyNum_01, String.valueOf(curDsContrPk));
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrKeyNum_02, String.valueOf(curDsContrDtlPk));
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrKeyNum_03, String.valueOf(svcMachMstrPK));
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrKeyNum_04, SVC_MEMO_TP.RENEW_CONTRACT_OR_MACHINE);
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrKeyNm_01, KEY_DS_CONTR_PK);
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrKeyNm_02, KEY_DS_CONTR_DTL_PK);
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrKeyNm_03, KEY_SVC_MACH_MSTR_PK);
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrKeyNm_04, KEY_CONTR_AUTO_RNW_TP_CD);
        ZYPEZDItemValueSetter.setValue(logTMsg.svcBatErrMsgTxt, SVC_CMNT_TXT);
        S21FastTBLAccessor.insert(logTMsg);
    }

    /**
     * createErrorTxt
     * @param
     * @return
     */
    // START 2023/07/06 K.Watanabe [QC#61145,MOD]
    // private String createErrorTxt(BigDecimal svcMachMstrPk, String dsContrNum) {
    private String createErrorTxt(BigDecimal svcMachMstrPk, String mdlName, String dsContrNum) {
    // END 2023/07/06 K.Watanabe [QC#61145,MOD]

        StringBuilder msgBuf = new StringBuilder();
            // START 2023/07/06 K.Watanabe [QC#61145,DEL]
            //msgBuf.append(SVC_CMNT_TXT);
            //msgBuf.append(ERR_MSG_SPACE_4);
            // END 2023/07/06 K.Watanabe [QC#61145,DEL]
            msgBuf.append(IB_ID);
            msgBuf.append(STR_COLON);
            msgBuf.append(svcMachMstrPk);
            msgBuf.append(ERR_MSG_SPACE_4);
            // START 2023/07/06 K.Watanabe [QC#61145,ADD]
            msgBuf.append(MODEL);
            msgBuf.append(STR_COLON);
            msgBuf.append(mdlName);
            msgBuf.append(ERR_MSG_SPACE_4);
            // END 2023/07/06 K.Watanabe [QC#61145,ADD]
            msgBuf.append(CONTRACT_NO);
            msgBuf.append(STR_COLON);
            msgBuf.append(dsContrNum);

        return msgBuf.toString();
    }
    // mod end 2016/05/25 CSA QC#447
    // add start 2016/06/06 CSA Defect#1523, 4624
    private void callContrTrk(List<BigDecimal> dsContrPkList, List<String> errList) {
        for (BigDecimal dsContrPk : dsContrPkList) {
            callContrTrkAPI(dsContrPk, errList);
        }
    }

    private void callContrTrkAPI(BigDecimal dsContrPk, List<String> errList) {
        String userId = NSAB003001Constant.BATCH_ID;
        if (!NSXC001001ContractTracking.callContrTrk(this.glblCmpyCd, ContrTrkProcMode.AUTO_RENEWAL.code, dsContrPk, userId, slsDt, null, null, ONBATCH_TYPE.BATCH)) {
            DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
            tMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                addErrList(errList, S21MessageFunc.clspGetMessage(NSXC001001ContractTracking.ERR_MSG_ID), tMsg.dsContrNum.getValue());
            }
        }
    }
    // add end 2016/06/06 CSA Defect#1523, 4624

    // START 2017/02/08 K.Kitachi [QC#17410, ADD]
    private MDL_NMTMsg getMdlNmTMsg() {
        if (!ZYPCommonFunc.hasValue(this.spclFltMdseCd)) {
            return null;
        }
        NSZC048001 svcMdlApi = new NSZC048001();
        NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.prntMdseCd, this.spclFltMdseCd);
        svcMdlApi.execute(svcMdlPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(svcMdlPMsg)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(svcMdlPMsg.mdlId)) {
            return null;
        }
        MDL_NMTMsg inMsg = new MDL_NMTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.t_GlblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.t_MdlId, svcMdlPMsg.mdlId);
        return (MDL_NMTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private boolean createCfsContrPrcUplft(NSZC046001PMsg pMsg, List<String> errList) {
        CFS_CONTR_PRC_UPLFTTMsg inMsg;
        DS_CONTR_DTLTMsg dsContrDtlTMsg;
        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg;
        for (int i = 0; i < pMsg.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg xxContrDtl = pMsg.xxContrDtlList.no(i);
            // Base
            if (xxContrDtl.rnwBaseFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                dsContrDtlTMsg = getDsContrDtlTMsg(xxContrDtl.dsContrDtlPk.getValue());
                if (dsContrDtlTMsg != null && isCfsDlr(dsContrDtlTMsg.baseBillToCustCd.getValue())) {
                    inMsg = new CFS_CONTR_PRC_UPLFTTMsg();
                    setCommonParam(inMsg, pMsg, xxContrDtl);
                    if (isRegOrAgg(pMsg.dsContrCatgCd.getValue(), xxContrDtl.dsContrDtlTpCd.getValue())) {
                        setRegAndAggBaseParam(inMsg, pMsg, xxContrDtl);
                        if (!insertCfsContrPrcUplft(inMsg, errList)) {
                            return false;
                        }
                    }
                    if (isFltLine(pMsg.dsContrCatgCd.getValue(), xxContrDtl.dsContrDtlTpCd.getValue())) {
                        setFltBaseParam(inMsg, pMsg, xxContrDtl);
                        if (!insertCfsContrPrcUplft(inMsg, errList)) {
                            return false;
                        }
                    }
                }
            }
            // Usage
            if (xxContrDtl.rnwUsgFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                for (int j = 0; j < pMsg.xxDsContrBllgMtrList.getValidCount(); j++) {
                    NSZC046001_xxDsContrBllgMtrListPMsg xxDsContrBllgMtr = pMsg.xxDsContrBllgMtrList.no(j);
                    if (xxContrDtl.dsContrDtlPk.getValue().compareTo(xxDsContrBllgMtr.dsContrDtlPk.getValue()) != 0) {
                        continue;
                    }
                    dsContrBllgMtrTMsg = getDsContrBllgMtrTMsg(xxDsContrBllgMtr.dsContrBllgMtrPk.getValue());
                    if (dsContrBllgMtrTMsg != null && isCfsDlr(dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue())) {
                        inMsg = new CFS_CONTR_PRC_UPLFTTMsg();
                        setCommonParam(inMsg, pMsg, xxContrDtl);
                        if (isRegOrAgg(pMsg.dsContrCatgCd.getValue(), xxContrDtl.dsContrDtlTpCd.getValue())) {
                            setRegAndAggUsgParam(inMsg, pMsg, xxDsContrBllgMtr);
                            if (!insertCfsContrPrcUplft(inMsg, errList)) {
                                return false;
                            }
                        }
                        if (isFltLine(pMsg.dsContrCatgCd.getValue(), xxContrDtl.dsContrDtlTpCd.getValue())) {
                            setFltUsgParam(inMsg, pMsg, xxDsContrBllgMtr);
                            if (!insertCfsContrPrcUplft(inMsg, errList)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isCfsDlr(String billToCustCd) {
        BigDecimal count = countCfsDlr(billToCustCd);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isRegOrAgg(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (dsContrCatgCd.equals(DS_CONTR_CATG.REGULAR) || (dsContrCatgCd.equals(DS_CONTR_CATG.AGGREGATE) && !dsContrDtlTpCd.equals(DS_CONTR_DTL_TP.AGGREGATE))) {
            return true;
        }
        return false;
    }

    private boolean isFltLine(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (dsContrCatgCd.equals(DS_CONTR_CATG.FLEET) && dsContrDtlTpCd.equals(DS_CONTR_DTL_TP.FLEET)) {
            return true;
        }
        return false;
    }

    private void setCommonParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxContrDtlListPMsg xxContrDtl) {
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal cfsContrPrcUplftPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_CONTR_PRC_UPLFT_SQ);
        ZYPEZDItemValueSetter.setValue(inMsg.cfsContrPrcUplftPk, cfsContrPrcUplftPk);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPk, pMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrNum, pMsg.dsContrNum);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, xxContrDtl.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrModTxt, this.dsContrModTxt);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrCatgCd, pMsg.dsContrCatgCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cfsProcStsCd, CFS_PROC_STS.IN_COMPLETED);
    }

    private void setRegAndAggBaseParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxContrDtlListPMsg xxContrDtl) {
        Map<String, Object> map = getRegAndAggBase(xxContrDtl.dsContrDtlPk.getValue());
        if (map != null) {
            ZYPEZDItemValueSetter.setValue(inMsg.uplftEffFromDt, (String) map.get("UPLFT_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) map.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(inMsg.mdlId, (BigDecimal) map.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(inMsg.mdlNm, (String) map.get("MDL_NM"));
            ZYPEZDItemValueSetter.setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlTpCd, (String) map.get("DS_CONTR_DTL_TP_CD"));
            ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
        }
        ZYPEZDItemValueSetter.setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
    }

    private void setRegAndAggUsgParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxDsContrBllgMtrListPMsg xxDsContrBllgMtr) {
        Map<String, Object> map = getRegAndAggUsg(xxDsContrBllgMtr.dsContrDtlPk.getValue(), xxDsContrBllgMtr.dsContrBllgMtrPk.getValue());
        if (map != null) {
            ZYPEZDItemValueSetter.setValue(inMsg.uplftEffFromDt, (String) map.get("UPLFT_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(inMsg.serNum, (String) map.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(inMsg.mdlId, (BigDecimal) map.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(inMsg.mdlNm, (String) map.get("MDL_NM"));
            ZYPEZDItemValueSetter.setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlTpCd, (String) map.get("DS_CONTR_DTL_TP_CD"));
            ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, (BigDecimal) map.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) map.get("DS_CONTR_BLLG_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(inMsg.mtrLbCd, (String) map.get("MTR_LB_CD"));
        }
        ZYPEZDItemValueSetter.setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
    }

    private void setFltBaseParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxContrDtlListPMsg xxContrDtl) {
        Map<String, Object> map = getFltBase(xxContrDtl.dsContrDtlPk.getValue());
        if (map != null) {
            ZYPEZDItemValueSetter.setValue(inMsg.uplftEffFromDt, (String) map.get("UPLFT_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlTpCd, (String) map.get("DS_CONTR_DTL_TP_CD"));
        }
        if (this.mdlNmTMsg != null) {
            ZYPEZDItemValueSetter.setValue(inMsg.mdlId, this.mdlNmTMsg.t_MdlId);
            ZYPEZDItemValueSetter.setValue(inMsg.mdlNm, this.mdlNmTMsg.t_MdlNm);
        }
        String fleetSerNum = SFX_FLT_SER_NUM + inMsg.dsContrNum.getValue();
        ZYPEZDItemValueSetter.setValue(inMsg.fleetSerNum, fleetSerNum);
        ZYPEZDItemValueSetter.setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
    }

    private void setFltUsgParam(CFS_CONTR_PRC_UPLFTTMsg inMsg, NSZC046001PMsg pMsg, NSZC046001_xxDsContrBllgMtrListPMsg xxDsContrBllgMtr) {
        Map<String, Object> map = getFltUsg(xxDsContrBllgMtr.dsContrDtlPk.getValue(), xxDsContrBllgMtr.dsContrBllgMtrPk.getValue());
        if (map != null) {
            ZYPEZDItemValueSetter.setValue(inMsg.uplftEffFromDt, (String) map.get("UPLFT_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(inMsg.prcUpRatio, (BigDecimal) map.get("PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlTpCd, (String) map.get("DS_CONTR_DTL_TP_CD"));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) map.get("DS_CONTR_BLLG_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(inMsg.mtrLbCd, (String) map.get("MTR_LB_CD"));
        }
        if (this.mdlNmTMsg != null) {
            ZYPEZDItemValueSetter.setValue(inMsg.mdlId, this.mdlNmTMsg.t_MdlId);
            ZYPEZDItemValueSetter.setValue(inMsg.mdlNm, this.mdlNmTMsg.t_MdlNm);
        }
        String fleetSerNum = SFX_FLT_SER_NUM + inMsg.dsContrNum.getValue();
        ZYPEZDItemValueSetter.setValue(inMsg.fleetSerNum, fleetSerNum);
        ZYPEZDItemValueSetter.setValue(inMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
    }

    private BigDecimal countCfsDlr(String billToCustCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("billToCustCd", billToCustCd);
        paramMap.put("cfsDlrCd", this.cfsDlrCd);
        return (BigDecimal) this.ssmBatchClient.queryObject("countCfsDlr", paramMap);
    }

    private Map<String, Object> getRegAndAggBase(BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_B);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRegAndAggBase", paramMap);
    }

    private Map<String, Object> getRegAndAggUsg(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_U);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getRegAndAggUsg", paramMap);
    }

    private Map<String, Object> getFltBase(BigDecimal dsContrDtlPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_B);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getFltBase", paramMap);
    }

    private Map<String, Object> getFltUsg(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrDtlPk", dsContrDtlPk);
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        paramMap.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        paramMap.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        paramMap.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        paramMap.put("dsContrBaseUsgNm", DS_CONTR_BASE_USG_NM_U);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getFltUsg", paramMap);
    }

    private boolean insertCfsContrPrcUplft(CFS_CONTR_PRC_UPLFTTMsg inMsg, List<String> errList) {
        S21FastTBLAccessor.insert(inMsg);
        String rtnCd = inMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0032E, new String[] {MSG_INFO_CFS_CONTR_PRC_UPLFT }), inMsg.dsContrNum.getValue());
            return false;
        }
        return true;
    }

    private DS_CONTR_DTLTMsg getDsContrDtlTMsg(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private DS_CONTR_BLLG_MTRTMsg getDsContrBllgMtrTMsg(BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }
    // END 2017/02/08 K.Kitachi [QC#17410, ADD]

    // START 2017/08/22 M.Naito [QC#8661, ADD]
    /**
     * getRnwLtrCtrlInfo
     * @param dsContrPk BigDecimal
     * @return boolean
     */
    private boolean getRnwLtrCtrlInfo(BigDecimal dsContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        BigDecimal rnwLtrCtrlInfo = (BigDecimal) this.ssmBatchClient.queryObject("getRnwLtrCtrlInfo", ssmParam);
        if (BigDecimal.ZERO.equals(rnwLtrCtrlInfo)) {
            return false;
        }
        return true;
    }

    // START 2023/07/06 K.Watanabe [QC#61145, ADD]
    /**
     * getMdlNm
     * @param svcMachMstrPk BigDecimal
     * @return String
     */
    private String getMdlNm(BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        return (String) this.ssmBatchClient.queryObject("getMdlNm", ssmParam);
    }
    // END 2023/07/06 K.Watanabe [QC#61145, ADD]

    /**
     * insertDsContrPrcChngRec
     * @param dsContrDtlPk BigDecimal
     * @param dsContrPk BigDecimal
     * @param rnwEffFromDt String
     * @return
     */
    private boolean insertDsContrPrcChngRec(List<BigDecimal> dsContrDtlPkList, BigDecimal dsContrPk, String rnwEffFromDt) {
        // insert DS_CONTR_PRC_CHNG_REC
        DS_CONTR_PRC_CHNG_RECTMsg tMsg = new DS_CONTR_PRC_CHNG_RECTMsg();

        for (int i = 0; i < dsContrDtlPkList.size(); i++) {
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcChngRecPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_CHNG_REC_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPkList.get(i));
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(tMsg.rnwEffFromDt, rnwEffFromDt);
            S21FastTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                return false;
            }
        }
        return true;
    }
    // END 2017/08/22 M.Naito [QC#8661, ADD]

    // START 2017/09/08 K.Kitachi [QC#20557, ADD]
    private BigDecimal getSvcMachMstrPkFromDsContrDtl(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlTMsg(dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            return null;
        }
        return dsContrDtlTMsg.svcMachMstrPk.getValue();
    }
    // END 2017/09/08 K.Kitachi [QC#20557, ADD]

    // START 2017/10/30 K.Kojima [QC#21859,ADD]
    private int calcBllgCycleCntFromDuration(String glblCmpyCd, String contrEffFromDt, String contrEffThruDt, String baseBllgCycleCd) {
        // calculate duration
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(contrEffFromDt);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        String paramEndDate = contrEffThruDt;
        Calendar cal = Calendar.getInstance();
        String calcEndDate = "";
        BigDecimal durnCnt = BigDecimal.ZERO;

        while (paramEndDate.compareTo(calcEndDate) > 0) {
            cal.setTime(startDt);
            durnCnt = durnCnt.add(BigDecimal.ONE);

            cal.add(Calendar.MONTH, durnCnt.intValue());
            cal.add(Calendar.DATE, -1);

            calcEndDate = df.format(cal.getTime());
        }

        if (paramEndDate.compareTo(calcEndDate) != 0) {
            return 0;
        }

        BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
        setValue(bcTMsg.glblCmpyCd, glblCmpyCd);
        setValue(bcTMsg.bllgCycleCd, baseBllgCycleCd);
        bcTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bcTMsg);

        if (bcTMsg == null || durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() != 0) {
            return 0;
        }
        return durnCnt.intValue() / bcTMsg.bllgCycleMthAot.getValueInt();
    }
    // END 2017/10/30 K.Kojima [QC#21859,ADD]

    // START 2017/11/28 K.Kojima [QC#22660,ADD]
    /**
     * callQaValidationApi
     * @param pMsg
     * @param errList
     * @return
     */
    private boolean callSumAggregateAPI(List<String> errList, BigDecimal dsContrPk) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrPk, dsContrPk);
        dsContrTMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(dsContrTMsg);
        if (dsContrTMsg == null) {
            return false;
        }
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return true;
        }

        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        dsContrDtlTMsg.setSQLID("006");
        dsContrDtlTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        dsContrDtlTMsg.setConditionValue("dsContrPk01", dsContrPk);
        dsContrDtlTMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);
        DS_CONTR_DTLTMsgArray outTmsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(dsContrDtlTMsg);
        if (outTmsgArray.getValidCount() == 0) {
            addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), dsContrTMsg.dsContrNum.getValue());
            return false;
        }
        dsContrDtlTMsg = outTmsgArray.no(0);

        NSZC047011PMsg pMsg = new NSZC047011PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "11");
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk.getValue());
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), dsContrTMsg.dsContrNum.getValue());
            return false;
        }
        return true;
    }
    // END 2017/11/28 K.Kojima [QC#22660,ADD]

    // START 2018/06/18 U.Kim [QC#24903,ADD]
    private BigDecimal recalcValueForMaxRate(BigDecimal value, BigDecimal maxPrcUpRatio, BigDecimal aftDeclPntDigitNum) {
        if (value == null) {
            return null;
        }
        if (maxPrcUpRatio == null) {
            return value;
        }
        return value.add(value.multiply(maxPrcUpRatio).divide(BigDecimal.valueOf(100), aftDeclPntDigitNum.intValue(), BigDecimal.ROUND_HALF_UP));
    }
    // END 2018/06/18 U.Kim [QC#24903,ADD]

    // START 2019/02/12 K.Kitachi [QC#30147, ADD]
    private List<Map<String, Object>> getRenewalTargetList() {
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getRenewalTargetList", setParamForRenewal(), this.excParam);
    }

    private boolean isAggLine(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            return true;
        }
        return false;
    }

    private String getAggNewEffThruDt(List<Map<String, Object>> renewalList, BigDecimal aggContrPk, BigDecimal aggLinePk) {
        String aggNewEffThruDt = null;
        for (int i = 0; i < renewalList.size(); i++) {
            BigDecimal dsContrPk = (BigDecimal) renewalList.get(i).get("DS_CONTR_PK");
            if (dsContrPk.compareTo(aggContrPk) != 0) {
                continue;
            }
            BigDecimal dsContrDtlPk = (BigDecimal) renewalList.get(i).get("DS_CONTR_DTL_PK");
            if (dsContrDtlPk.compareTo(aggLinePk) == 0) {
                continue;
            }
            String newEffThruDt = (String) renewalList.get(i).get("NEW_EFF_THRU_DT");
            if (!hasValue(aggNewEffThruDt) || ZYPDateUtil.compare(newEffThruDt, aggNewEffThruDt) > 0) {
                aggNewEffThruDt = newEffThruDt;
            }
        }
        return aggNewEffThruDt;
    }
    // END 2019/02/12 K.Kitachi [QC#30147, ADD]

    // add start 2019/10/28 QC#53577
    private String getAccNewEffThruDt(List<Map<String, Object>> renewalList, BigDecimal accDtlPk, String accNewEffThruDt) {
        DS_CONTR_DTLTMsg accDtlTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(accDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(accDtlTMsg.dsContrDtlPk, accDtlPk);
        accDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(accDtlTMsg);
        if (accDtlTMsg == null || !hasValue(accDtlTMsg.prntDsContrDtlPk)) {
            return accNewEffThruDt;
        }

        for (int i = 0; i < renewalList.size(); i++) {
            BigDecimal dsContrDtlPk = (BigDecimal) renewalList.get(i).get("DS_CONTR_DTL_PK");
            if (dsContrDtlPk.compareTo(accDtlTMsg.prntDsContrDtlPk.getValue()) != 0) {
                continue;
            }
            String prntNewEffThruDt = (String) renewalList.get(i).get("NEW_EFF_THRU_DT");
            if (ZYPDateUtil.compare(accNewEffThruDt, prntNewEffThruDt) > 0) {
                return prntNewEffThruDt;
            }
            return accNewEffThruDt;
        }

        DS_CONTR_DTLTMsg prntDtlTMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prntDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prntDtlTMsg.dsContrDtlPk, accDtlTMsg.prntDsContrDtlPk.getValue());
        prntDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(prntDtlTMsg);
        if (prntDtlTMsg == null) {
            return accNewEffThruDt;
        }

        if (ZYPDateUtil.compare(accNewEffThruDt, prntDtlTMsg.contrEffThruDt.getValue()) > 0) {
            return prntDtlTMsg.contrEffThruDt.getValue();
        }
        return accNewEffThruDt;
    }
    // add end 2019/10/28 QC#53577

    // START 2022/06/01 [QC#59973, ADD]
    private boolean callSchdAgmtAdjContrApi(List<String> errList, BigDecimal svcMachMstrPk, BigDecimal dsContrPk, String dsContrNum) {
        // Create Api
        NWZC194001PMsg apiPMsg = new NWZC194001PMsg();
        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NWZC194001Constant.MODE_RENEWAL);
        setValue(apiPMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(apiPMsg.dsContrPk, dsContrPk);
        // Call Api
        new NWZC194001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        // Check returns
        if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
            addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), dsContrNum);
            return false;
        }
        return true;
    }
    // END 2022/06/01 [QC#59973, ADD]
    
    // START 2024/04/04 K.Ogasawara [QC#63550, ADD]
    private boolean setManualRenew(List<String> errList, NSZC046001PMsg updatePMsg, List<BigDecimal> dsContrDtlPkList) {

        if (!renewalHoldManualRenew(updatePMsg, dsContrDtlPkList)) {
            addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), updatePMsg.dsContrNum.getValue());
            return false;
        }
        return true;
    }

    private boolean renewalHoldManualRenew(NSZC046001PMsg updatePMsg, List<BigDecimal> dsContrDtlPkList) {
        for (int i = 0; i < updatePMsg.xxContrDtlList.getValidCount(); i++) {
            BigDecimal dsContrDtlPk = updatePMsg.xxContrDtlList.no(i).dsContrDtlPk.getValue();
            if (!dsContrDtlPkList.contains(dsContrDtlPk)){
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(updatePMsg.xxContrDtlList.no(i).rnwBaseFlg.getValue())) {
                BigDecimal dsContrPrcEffPk = renewalHoldTargetByQaValidation(dsContrDtlPk, null);
                if (!hasValue(dsContrPrcEffPk)) {
                    continue;
                }

                // update DS Contract Price Effective
                DS_CONTR_PRC_EFFTMsg prcEff = getPrcEffForUpdate(dsContrPrcEffPk);
                ZYPEZDItemValueSetter.setValue(prcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD);
                S21ApiTBLAccessor.update(prcEff);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEff.getReturnCode())) {
                    return false;
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(updatePMsg.xxContrDtlList.no(i).rnwUsgFlg.getValue())) {
                for (int j = 0; j < updatePMsg.xxDsContrBllgMtrList.getValidCount(); j++) {
                    if (dsContrDtlPk.compareTo(updatePMsg.xxDsContrBllgMtrList.no(j).dsContrDtlPk.getValue()) == 0) {
                        BigDecimal dsContrPrcEffPk = renewalHoldTargetByQaValidation(dsContrDtlPk, updatePMsg.xxDsContrBllgMtrList.no(j).dsContrBllgMtrPk.getValue());
                        if (!hasValue(dsContrPrcEffPk)) {
                            continue;
                        }

                        // update DS Contract Price Effective
                        DS_CONTR_PRC_EFFTMsg prcEff = getPrcEffForUpdate(dsContrPrcEffPk);
                        ZYPEZDItemValueSetter.setValue(prcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.RENEWAL_HOLD);
                        S21ApiTBLAccessor.update(prcEff);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prcEff.getReturnCode())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    // END 2024/04/04 K.Ogasawara [QC#63550, ADD]
}
