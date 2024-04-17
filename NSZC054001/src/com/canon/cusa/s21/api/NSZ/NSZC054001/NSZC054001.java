/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC054001;

import static com.canon.cusa.s21.api.NSZ.NSZC054001.constant.NSZC054001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDPItem;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_WIN_DAYSTMsg;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_MEMOTMsg;
import business.parts.NSZC034001PMsg;
import business.parts.NSZC035001PMsg;
import business.parts.NSZC054001PMsg;
import business.parts.NSZC056001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC034001.NSZC034001;
import com.canon.cusa.s21.api.NSZ.NSZC035001.NSZC035001;
import com.canon.cusa.s21.api.NSZ.NSZC035001.constant.NSZC035001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC056001.NSZC056001;
import com.canon.cusa.s21.api.NSZ.NSZC056001.constant.NSZC056001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.ContrTrkInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Preview Billing API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2015   Hitachi         A.Kohinata      Create          N/A
 * 01/22/2016   Hitachi         T.Kanasaka      Update          QC3475
 * 03/17/2016   Hitachi         K.Kishimoto     Update          QC5629
 * 03/30/2016   Hitachi         T.Aoyagi        Update          QC#1467
 * 04/08/2016   Hitachi         T.Kanasaka      Update          QC#6730
 * 04/14/2016   Hitachi         K.Kishimoto     Update          QC#6657
 * 06/01/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 10/03/2016   Hitachi         K.Yamada        Update          QC#10729
 * 12/15/2016   Hitachi         Y.Takeno        Update          QC#16285
 * 02/22/2017   Hitachi         Y.Takeno        Update          QC#16285-1
 * 02/24/2017   Hitachi         Y.Takeno        Update          QC#16285-2
 * 2017/08/15   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/09/01   Hitachi         K.Kitachi       Update          QC#20890
 * 2018/03/13   Hitachi         U.kim           Update          QC#18884(Sol#337)
 * 2018/04/23   Hitachi         K.Kojima        Update          QC#25446
 * 2018/07/04   Hitachi         K.Kitachi       Update          QC#26891
 * 2018/09/10   Hitachi         K.Kojima        Update          QC#28107
 * 2019/01/22   Hitachi         Y.Takeno        Update          QC#29791
 * 2019/09/02   Hitachi         K.Kitachi       Update          QC#52695
 * 2019/09/05   Hitachi         K.Kim           Update          QC#52820
 * 2019/10/01   Hitachi         A.Kohinata      Update          QC#53643
 * 2019/10/23   Hitachi         A.Kohinata      Update          QC#53574
 * 2019/11/28   Hitachi         K.Kishimoto     Update          QC#53567
 * 2022/12/19   Hitachi         H.Watanabe      Update          QC#60886
 * 2023/05/09   Hitachi         K.Kitachi       Update          QC#61469
 *</pre>
 */
public class NSZC054001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Online Batch Type */
    private ONBATCH_TYPE onBatTp = null;

    /** BLLG_PRVW_AVAL_MAX_PRC */
    BigDecimal bllgPrvwAvalMaxPrc = null;

    /** BLLG_PRVW_AVAL_UPDOWN_RATIO */
    BigDecimal bllgPrvwAvalUpdownRatio = null;

    // START 2019/09/05 [QC#52820, ADD]
    /** BLLG_PRVW_MIN_AMT */
    BigDecimal bllgPrvwMinAmt = null;

    /** BLLG_PRVW_1ST_MAX_PRC */
    BigDecimal bllgPrvw1stMaxPrc = null;
    // END 2019/09/05 [QC#52820, ADD]

    /** Meter Read Date From */
    String mtrReadDtFrom = null;

    // del start 2017/08/15 QC#18799
//    /** Meter Read Date Thru */
//    String mtrReadDtThru = null;
    // del end 2017/08/15 QC#18799

    /** Meter Read Date Preview */
    String mtrReadDtPrvw = null;

    /** Amount Check Error */
    boolean amtCheckErr = false;

    // START 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]
    /** Meter Copy Minus Flag*/
    boolean mtrCopyMinusFlg = false;
    // END 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]

    // START 2018/04/23 K.Kojima [QC#25446,ADD]
    /** Billing Hold Target Detail Pk */
    List<BigDecimal> bllgHldTargetDtlPk = null;
    // END 2018/04/23 K.Kojima [QC#25446,ADD]

    /**
     * Constructor
     */
    public NSZC054001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC054001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC054001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC054001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * execute
     * @param pMsg NSZC054001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC054001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        this.onBatTp = onBatchType;

        if (!checkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        init(msgMap);

        doProcess(msgMap);
        msgMap.flush();
    }

    private boolean checkParam(S21ApiMessageMap msgMap) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        checkMandatory(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        checkMandatory(msgMap, pMsg.slsDt, NSZM0002E);
        checkMandatory(msgMap, pMsg.mtrReadDt, NSZM0268E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void checkMandatory(S21ApiMessageMap msgMap, EZDPItem item, String messageId) {
        if (!hasValue(item)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void init(S21ApiMessageMap msgMap) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        // mod start 2017/08/15 QC#18799
//        this.bllgPrvwAvalMaxPrc = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_AVAL_MAX_PRC, pMsg.glblCmpyCd.getValue()));
        // START 2019/09/05 [QC#52820, DEL]
        // this.bllgPrvwAvalMaxPrc = getBllgPrvwAvalMaxPrc(pMsg);
        // // mod end 2017/08/15 QC#18799
        // this.bllgPrvwAvalUpdownRatio = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_AVAL_UPDOWN_RATIO, pMsg.glblCmpyCd.getValue()));
        // END 2019/09/05 [QC#52820, ADD]

        // del start 2017/08/15 QC#18799
//        BigDecimal bllgMtrReadWindowBefDays = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_MTR_READ_WINDOW_BEF_DAYS, pMsg.glblCmpyCd.getValue()));
        // del end 2017/08/15 QC#18799
        BigDecimal bllgMtrReadWindowAftDays = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_MTR_READ_WINDOW_AFT_DAYS, pMsg.glblCmpyCd.getValue()));
        BigDecimal bllgPrvwChkBtwMthNum = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_CHK_BTW_MTH_NUM, pMsg.glblCmpyCd.getValue()));

        Date mtrReadDt = toDate(pMsg.mtrReadDt.getValue(), FORMAT_DT);
        Calendar cal = Calendar.getInstance();

        cal.setTime(mtrReadDt);
        cal.add(Calendar.DATE, bllgMtrReadWindowAftDays.negate().intValue());
        this.mtrReadDtFrom = toStringDate(cal.getTime(), FORMAT_DT);

        // del start 2017/08/15 QC#18799
//        cal.setTime(mtrReadDt);
//        cal.add(Calendar.DATE, bllgMtrReadWindowBefDays.intValue());
//        this.mtrReadDtThru = toStringDate(cal.getTime(), FORMAT_DT);
        // del end 2017/08/15 QC#18799

        cal.setTime(mtrReadDt);
        cal.add(Calendar.MONTH, bllgPrvwChkBtwMthNum.negate().intValue());
        this.mtrReadDtPrvw = toStringDate(cal.getTime(), FORMAT_DT);

        this.amtCheckErr = false;
        // START 2018/03/13 U.Kim [QC#18884(Sol#337), ADD]
        this.mtrCopyMinusFlg = false;
        // END 2018/03/13 U.Kim [QC#18884(Sol#337), ADD]
        // START 2018/04/23 K.Kojima [QC#25446,ADD]
        this.bllgHldTargetDtlPk = new ArrayList<BigDecimal>();
        // END 2018/04/23 K.Kojima [QC#25446,ADD]
    }

    private void doProcess(S21ApiMessageMap msgMap) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        Map<String, Object> dsContr = getDsContr(pMsg);
        if (dsContr == null) {
            msgMap.addXxMsgId(NSZM0639E);
            return;
        }

        String dsContrCatgCd = (String) dsContr.get("DS_CONTR_CATG_CD");

        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd) && !hasValue(pMsg.svcMachMstrPk)) {
            msgMap.addXxMsgId(NSZM0790E);
            return;
        }

        // START 2019/09/05 [QC#52820, ADD]
        String svcLineBizCd = (String) dsContr.get("SVC_LINE_BIZ_CD");
        setBllgPrvwCheckValue(pMsg, svcLineBizCd);
        // END 2019/09/05 [QC#52820, ADD]

        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            doProcessReg(msgMap, dsContr);
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            doProcessFlt(msgMap, dsContr);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            doProcessAgg(msgMap, dsContr);
        }

        // START 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]
        // if (this.amtCheckErr) {
        if (this.amtCheckErr || this.mtrCopyMinusFlg) {
        // END 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]
            updateDsContr(msgMap, dsContr);
            // START 2018/04/23 K.Kojima [QC#25446,ADD]
            if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
                updateDsContrDtl(msgMap);
            }
            // END 2018/04/23 K.Kojima [QC#25446,ADD]
            // START 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]
            // START 2019/11/28 [QC#53567, Mod]
            if (this.mtrCopyMinusFlg) {
                workflowProcessForComp(msgMap, dsContr);
            }
            // END 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]
            // START 03/30/2016 T.Aoyagi [QC#1467, ADD]
            if (this.amtCheckErr) {
                workflowProcess(msgMap, dsContr);
            // START 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]
            }
            // END 2019/11/28 [QC#53567, Mod]
            // END 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]
            // END 03/30/2016 T.Aoyagi [QC#1467, ADD]
            // mod start 2016/12/15 CSA Defect#16285
            // del start 2017/02/22 CSA Defect#16285-1
            // insertSvcMemo(msgMap, dsContr);
            // del end   2017/02/22 CSA Defect#16285-1
            // mod end   2016/12/15 CSA Defect#16285
            // add start 2016/06/01 CSA Defect#1523, 4624
            callContrTrkAPI(msgMap, dsContr);
            // add end 2016/06/01 CSA Defect#1523, 4624
        }
        return;
    }

    private void doProcessReg(S21ApiMessageMap msgMap, Map<String, Object> dsContr) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        BigDecimal dsContrPk = (BigDecimal) dsContr.get("DS_CONTR_PK");
        List<Map<String, Object>> bllgSchdList = getBllgSchdReg(pMsg, dsContrPk);
        if (bllgSchdList == null || bllgSchdList.size() == 0) {
            return;
        }

        for (int i = 0; i < bllgSchdList.size(); i++) {
            Map<String, Object> bllgSchd = bllgSchdList.get(i);
            if (!callBllgCalcApi(msgMap, bllgSchd)) {
                return;
            }
            if (!checkAmt(msgMap, bllgSchd)) {
                return;
            }
        }
    }

    private void doProcessFlt(S21ApiMessageMap msgMap, Map<String, Object> dsContr) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        BigDecimal dsContrPk = (BigDecimal) dsContr.get("DS_CONTR_PK");
        List<Map<String, Object>> bllgSchdList = getBllgSchdFlt(pMsg, dsContrPk);
        if (bllgSchdList == null || bllgSchdList.size() == 0) {
            return;
        }

        for (int i = 0; i < bllgSchdList.size(); i++) {
            Map<String, Object> bllgSchd = bllgSchdList.get(i);
            if (!callFltCalcApi(msgMap, dsContr, bllgSchd)) {
                return;
            }
            if (!callBllgCalcApi(msgMap, bllgSchd)) {
                return;
            }
            if (!checkAmt(msgMap, bllgSchd)) {
                return;
            }
        }
    }

    private void doProcessAgg(S21ApiMessageMap msgMap, Map<String, Object> dsContr) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        BigDecimal dsContrPk = (BigDecimal) dsContr.get("DS_CONTR_PK");
        List<Map<String, Object>> bllgSchdList = getBllgSchdAgg(pMsg, dsContrPk);
        if (bllgSchdList == null || bllgSchdList.size() == 0) {
            return;
        }

        for (int i = 0; i < bllgSchdList.size(); i++) {
            Map<String, Object> bllgSchd = bllgSchdList.get(i);
            if (!callBllgCalcApi(msgMap, bllgSchd)) {
                return;
            }
        }
        if (!callAggCalcApi(msgMap, dsContr)) {
            return;
        }
        for (int i = 0; i < bllgSchdList.size(); i++) {
            Map<String, Object> bllgSchd = bllgSchdList.get(i);
            //Mod Start 04/14/2016 <QC#6657>
            List<Map<String, Object>> aggChildSchdList = getBllgSchdAggChild(pMsg, (BigDecimal)bllgSchd.get("DS_CONTR_BLLG_SCHD_PK"));
            for (Map<String, Object> aggChildSchd : aggChildSchdList) {
                if (!checkAmt(msgMap, aggChildSchd)) {
                    return;
                }
            }
            //Mod End  04/14/2016 <QC#6657>
        }
    }

    private boolean callBllgCalcApi(S21ApiMessageMap msgMap, Map<String, Object> bllgSchd) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        // Call Billing Calculation API
        NSZC056001PMsg apiPMsg = new NSZC056001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC056001Constant.MODE_PREVIEW_BILLING_API);
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.dsContrDtlPk, (BigDecimal) bllgSchd.get("DS_CONTR_DTL_PK"));
        setValue(apiPMsg.dsContrBllgMtrPk, (BigDecimal) bllgSchd.get("DS_CONTR_BLLG_MTR_PK"));
        setValue(apiPMsg.baseChrgFlg, FLG_OFF_N);
        setValue(apiPMsg.usgChrgFlg, FLG_ON_Y);
        setValue(apiPMsg.nextBllgDt, (String) bllgSchd.get("NEXT_BLLG_DT"));
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        setValue(apiPMsg.xxSetFlg_LG, pMsg.xxSetFlg_LG);
        // END 2018/09/10 K.Kojima [QC#28107,ADD]

        new NSZC056001().execute(apiPMsg, this.onBatTp);
        if (!checkErrMsg(msgMap, apiPMsg)) {
            return false;
        }
        return true;
    }

    private boolean callFltCalcApi(S21ApiMessageMap msgMap, Map<String, Object> dsContr, Map<String, Object> bllgSchd) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        // Call Fleet Calculation API
        NSZC035001PMsg apiPMsg = new NSZC035001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC035001Constant.MODE_NORMAL);
        setValue(apiPMsg.dsContrNum, (String) dsContr.get("DS_CONTR_NUM"));
        setValue(apiPMsg.prntDsContrBllgSchdPk, (BigDecimal) bllgSchd.get("DS_CONTR_BLLG_SCHD_PK"));
        setValue(apiPMsg.bllgDt, pMsg.mtrReadDt);

        new NSZC035001().execute(apiPMsg, this.onBatTp);
        if (!checkErrMsg(msgMap, apiPMsg)) {
            return false;
        }

        if (hasValue(apiPMsg.fleetReadRollUpPk)) {
            int addIndex = pMsg.fltRollUpList.getValidCount();
            pMsg.fltRollUpList.setValidCount(addIndex + 1);
            setValue(pMsg.fltRollUpList.no(addIndex).fleetReadRollUpPk, apiPMsg.fleetReadRollUpPk);
            setValue(pMsg.fltRollUpList.no(addIndex).dsContrBllgMtrPk, (BigDecimal) bllgSchd.get("DS_CONTR_BLLG_MTR_PK"));
        }
        return true;
    }

    private boolean callAggCalcApi(S21ApiMessageMap msgMap, Map<String, Object> dsContr) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        // Call Aggregate Calculation API
        NSZC034001PMsg apiPMsg = new NSZC034001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.dsContrNum, (String) dsContr.get("DS_CONTR_NUM"));
        setValue(apiPMsg.bllgDt, pMsg.mtrReadDt);
        setValue(apiPMsg.slsDt, pMsg.slsDt);

        new NSZC034001().execute(apiPMsg, this.onBatTp);
        if (!checkErrMsg(msgMap, apiPMsg)) {
            return false;
        }
        return true;
    }

    private boolean checkAmt(S21ApiMessageMap msgMap, Map<String, Object> bllgSchd) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        BigDecimal dsContrBllgSchdPk = (BigDecimal) bllgSchd.get("DS_CONTR_BLLG_SCHD_PK");

        // get this meter billing
        Map<String, Object> mtrBllg = getMtrBllg(pMsg, dsContrBllgSchdPk);
        if (mtrBllg == null) {
            return true;
        }
        BigDecimal svcContrBllgPk = (BigDecimal) mtrBllg.get("SVC_CONTR_BLLG_PK");
        BigDecimal dsContrBllgMtrPk = (BigDecimal) mtrBllg.get("DS_CONTR_BLLG_MTR_PK");
        String mtrBllgFromDt = (String) mtrBllg.get("MTR_BLLG_FROM_DT");
        BigDecimal mtrChrgFuncAmt = nullToZero((BigDecimal) mtrBllg.get("MTR_CHRG_FUNC_AMT"));
        // START 2019/09/05 [QC#52820, ADD]
        BigDecimal bllgCopyQty = nullToZero((BigDecimal) mtrBllg.get("BLLG_COPY_QTY"));
        BigDecimal copyInclQty = nullToZero((BigDecimal) mtrBllg.get("COPY_INCL_QTY"));
        // END 2019/09/05 [QC#52820, ADD]

        // START 2018/03/13 U.Kim [QC#18884(Sol#337), ADD]
        BigDecimal mtrCopyQty = nullToZero((BigDecimal) mtrBllg.get("MTR_COPY_QTY"));
        if(mtrCopyQty.compareTo(BigDecimal.ZERO) < 0){
            // check Meter Copy Quantity is minus
            this.mtrCopyMinusFlg = true;
            SVC_CONTR_BLLGTMsg svcContrBllgTMsg = getSvcContrBllgFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), svcContrBllgPk);
            if (svcContrBllgTMsg == null) {
                msgMap.addXxMsgId(NSZM0786E);
                return false;
            }
            setValue(svcContrBllgTMsg.bllgApvlReqFlg, FLG_ON_Y);
            setValue(svcContrBllgTMsg.bllgApvlCpltFlg, FLG_OFF_N);
            S21ApiTBLAccessor.update(svcContrBllgTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcContrBllgTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0787E);
                return false;
            }
            // START 2018/04/23 K.Kojima [QC#25446,ADD]
            if (!this.bllgHldTargetDtlPk.contains(svcContrBllgTMsg.dsContrDtlPk.getValue())) {
                this.bllgHldTargetDtlPk.add(svcContrBllgTMsg.dsContrDtlPk.getValue());
            }
            // END 2018/04/23 K.Kojima [QC#25446,ADD]
            return true;
        }
        // END 2018/03/13 U.Kim [QC#18884(Sol#337), ADD]

        // get previous meter billing
        List<Map<String, Object>> preMtrBllgList = getPreMtrBllg(pMsg, dsContrBllgMtrPk, mtrBllgFromDt);

        // START 2019/09/05 [QC#52820, MOD]
        // First Billing
        // not exists previous meter billing
        if (preMtrBllgList == null || preMtrBllgList.size() == 0) {
            // check OK
            if (mtrChrgFuncAmt.compareTo(this.bllgPrvw1stMaxPrc) < 0) {
                return true;
            }
        } else {
            // exists previous meter billing
            if (mtrChrgFuncAmt.compareTo(this.bllgPrvwAvalMaxPrc) < 0) {
                if (mtrChrgFuncAmt.compareTo(BigDecimal.ZERO) == 0) {
                    // 2022/12/19 QC#60886 Mod Start
                    // Within Allowance
                    // check OK
//                    if (bllgCopyQty.compareTo(BigDecimal.ZERO) == 0 && mtrCopyQty.compareTo(BigDecimal.ZERO) >= 0 && copyInclQty.compareTo(BigDecimal.ZERO) > 0) {
//                        return true;
//                    }
                    // check Average Billing
                    if (!isCreateAverageBillingWF(pMsg, bllgSchd, dsContrBllgMtrPk)) {
                        return true;
                    }
                    // 2022/12/19 QC#60886 Mod End
                } else {
                    // check OK
                    if (mtrChrgFuncAmt.compareTo(this.bllgPrvwMinAmt) < 0) {
                        return true;
                    } else {
                        BigDecimal preMtrChrgFuncAmt = BigDecimal.ZERO;
                        for (int i = 0; i < preMtrBllgList.size(); i++) {
                            Map<String, Object> preMtrBllg = preMtrBllgList.get(i);
                            preMtrChrgFuncAmt = preMtrChrgFuncAmt.add(nullToZero((BigDecimal) preMtrBllg.get("MTR_CHRG_FUNC_AMT")));
                        }
                        preMtrChrgFuncAmt = preMtrChrgFuncAmt.divide(new BigDecimal(preMtrBllgList.size()), 2, RoundingMode.HALF_UP);
                        // mod start 2019/10/23 QC#53574
                        //BigDecimal ratio = mtrChrgFuncAmt.subtract(preMtrChrgFuncAmt).abs().divide(preMtrChrgFuncAmt, SCALE_DIVIDE, RoundingMode.HALF_UP).multiply(SCALE_RATIO);
                        //
                        //// check OK
                        //if (ratio.compareTo(this.bllgPrvwAvalUpdownRatio) < 0) {
                        //    return true;
                        //}
                        if (BigDecimal.ZERO.compareTo(preMtrChrgFuncAmt) != 0) {
                            BigDecimal ratio = mtrChrgFuncAmt.subtract(preMtrChrgFuncAmt).abs().divide(preMtrChrgFuncAmt, SCALE_DIVIDE, RoundingMode.HALF_UP).multiply(SCALE_RATIO);
                            // check OK
                            if (ratio.compareTo(this.bllgPrvwAvalUpdownRatio) < 0) {
                                return true;
                            }
                        }
                        // mod end 2019/10/23 QC#53574
                    }
                }
            }
        }
        // END 2019/09/05 [QC#52820, MOD]

        // check NG
        this.amtCheckErr = true;

        // update SVC_CONTR_BLLG
        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = getSvcContrBllgFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), svcContrBllgPk);
        if (svcContrBllgTMsg == null) {
            msgMap.addXxMsgId(NSZM0786E);
            return false;
        }
        setValue(svcContrBllgTMsg.bllgApvlReqFlg, FLG_ON_Y);
        // Add Start 03/17/2016 <QC#5629>
        setValue(svcContrBllgTMsg.bllgApvlCpltFlg, FLG_OFF_N);
        // Add End   03/17/2016 <QC#5629>
        S21ApiTBLAccessor.update(svcContrBllgTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcContrBllgTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0787E);
            return false;
        }

        // START 2018/04/23 K.Kojima [QC#25446,ADD]
        if (!this.bllgHldTargetDtlPk.contains(svcContrBllgTMsg.dsContrDtlPk.getValue())) {
            this.bllgHldTargetDtlPk.add(svcContrBllgTMsg.dsContrDtlPk.getValue());
        }
        // END 2018/04/23 K.Kojima [QC#25446,ADD]

        return true;
    }

    private boolean updateDsContr(S21ApiMessageMap msgMap, Map<String, Object> dsContr) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        BigDecimal dsContrPk = (BigDecimal) dsContr.get("DS_CONTR_PK");
        DS_CONTRTMsg dsContrTMsg = getDsContrFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), dsContrPk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0639E);
            return false;
        }

        setValue(dsContrTMsg.bllgHldFlg, FLG_ON_Y);
        S21ApiTBLAccessor.update(dsContrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0789E);
            return false;
        }
        return true;
    }

    // START 2018/04/23 K.Kojima [QC#25446,ADD]
    private boolean updateDsContrDtl(S21ApiMessageMap msgMap) {
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        for (BigDecimal dsContrDtlPk : this.bllgHldTargetDtlPk) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlFindByKeyForUpdate(pMsg.glblCmpyCd.getValue(), dsContrDtlPk);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0639E);
                return false;
            }

            setValue(dsContrDtlTMsg.bllgHldFlg, FLG_ON_Y);
            S21ApiTBLAccessor.update(dsContrDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0789E);
                return false;
            }
        }
        return true;
    }

    // END 2018/04/23 K.Kojima [QC#25446,ADD]

    // START 03/30/2016 T.Aoyagi [QC#1467, ADD]
    private void workflowProcess(S21ApiMessageMap msgMap, Map<String, Object> dsContr) {

        String dsContrCatgCd = (String) dsContr.get("DS_CONTR_CATG_CD");
        // START 2019/11/28 [QC#53567, Mod]
        List<Map<String, Object>> contrBllgList = getContrBllgList(msgMap, dsContrCatgCd, false);
        // END 2019/11/28 [QC#53567, Mod]

        for (Map<String, Object> contrBllgMap : contrBllgList) {
            String documentId = getDocumentId(msgMap, contrBllgMap);
            cancelWF(msgMap, documentId);
            // START 2019/09/02 K.Kitachi [QC#52695, ADD]
            BigDecimal dsContrDtlPk = (BigDecimal) contrBllgMap.get("DS_CONTR_DTL_PK");
            documentId = S21StringUtil.concatStrings(documentId, dsContrDtlPk.toPlainString());
            cancelWF(msgMap, documentId);
            // END 2019/09/02 K.Kitachi [QC#52695, ADD]
            // add start 2017/02/22 CSA Defect#16285-1
            BigDecimal workflowId = startWF(msgMap, documentId, contrBllgMap);
            insertSvcMemo(msgMap, workflowId, dsContr);
            // add end   2017/02/22 CSA Defect#16285-1
        }
    }

    private void cancelWF(S21ApiMessageMap msgMap, String documentId) {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        List<S21NwfProcess> processes = null;

        try {
            context = factory.getContex();
            processes = context.getProcess(WF_PROCESS_NM, documentId);

            for (S21NwfProcess nwfProcess : processes) {
                if (nwfProcess.isActive()) {
                    nwfProcess.getToken().cancel();
                }
            }
        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfBizException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfException e) {
            msgMap.addXxMsgId(NSZM0866E);
        }
    }

    // mod start 2017/02/22 CSA Defect#16285-1
    // private void startWF(S21ApiMessageMap msgMap, String documentId, Map<String, Object> contrBllgMap) {
    private BigDecimal startWF(S21ApiMessageMap msgMap, String documentId, Map<String, Object> contrBllgMap) {
    // mod end   2017/02/22 CSA Defect#16285-1
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;
        // add start 2017/02/22 CSA Defect#16285-1
        // mod start 2017/02/24 CSA Defect#16285-2
        BigDecimal workflowId = null;
        // mod end   2017/02/24 CSA Defect#16285-2
        // add end   2017/02/22 CSA Defect#16285-1

        try {
            context = factory.getContex();
            process = context.newProcess(WF_PROCESS_NM);
            process.setDocumentId(documentId);

            // set Token Object
            NSZC054001TokenObject tokenBiz = setTokenBiz(msgMap, documentId, contrBllgMap);
            S21NwfTokenImpl token = (S21NwfTokenImpl) process.getToken();
            // START 2018/01/18 [QC#29791, ADD]
            String rgtnUsrId = getMtrReadRgtnUsrId(contrBllgMap);
            if (rgtnUsrId != null) {
                token.setAutoDelegateUser(rgtnUsrId);
            }
            // END   2018/01/18 [QC#29791, ADD]
            token.setTokenObj(tokenBiz);

            // Start Workflow
            token.start();
            // add start 2017/02/22 CSA Defect#16285-1
            // mod start 2017/02/24 CSA Defect#16285-2
            workflowId = process.getProcessId();
            // mod end   2017/02/24 CSA Defect#16285-2
            // add end   2017/02/22 CSA Defect#16285-1

        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0926E);
        } catch (S21NwfBizException e) {
            // Business Error Logic
            // Auto Approve Process Call APIs
            // Approve API / Reject API / Process End API Error
            msgMap.addXxMsgIdWithPrm(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
        } catch (S21NwfException e) {
            // System Error Logic
            msgMap.addXxMsgId(NSZM0926E);
        }

        // add start 2017/02/22 CSA Defect#16285-1
        // mod start 2017/02/24 CSA Defect#16285-2
        return workflowId;
        // mod end   2017/02/24 CSA Defect#16285-2
        // add end   2017/02/22 CSA Defect#16285-1
    }

    private NSZC054001TokenObject setTokenBiz(S21ApiMessageMap msgMap, String documentId, Map<String, Object> contrBllgMap) {

        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        // Business Token Object
        NSZC054001TokenObject tokenBiz = new NSZC054001TokenObject();

        // Line Data
        NSZC054001TokenObjectLine line = new NSZC054001TokenObjectLine();
        BigDecimal mtrChrgFuncAmt = getMtrChrgFuncAmt(msgMap, contrBllgMap);
        line.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        line.setWfPreViewBllgId(documentId);
        line.setDsContrNum((String) contrBllgMap.get("DS_CONTR_NUM"));
        // mod start 2016/10/05 CSA Defect#10729
        if (hasValue(pMsg.svcMachMstrPk)) {
            line.setSvcMachMstrPk(pMsg.svcMachMstrPk.getValue());
        }
        if (DS_CONTR_CATG.AGGREGATE.equals((String) contrBllgMap.get("DS_CONTR_CATG_CD"))) {
            BigDecimal svcMachMstrPk = (BigDecimal) contrBllgMap.get("SVC_MACH_MSTR_PK");
            line.setSvcMachMstrPk(svcMachMstrPk);
        }
        // mod end 2016/10/05 CSA Defect#10729
        line.setBillToCustCd((String) contrBllgMap.get("BILL_TO_CUST_CD"));
        line.setMtrChrgFuncAmt(mtrChrgFuncAmt);
        line.setContrAdminPsnCd((String) contrBllgMap.get("CONTR_ADMIN_PSN_CD"));
        line.setSvcContrBllgFromDt((String) contrBllgMap.get("SVC_CONTR_BLLG_FROM_DT"));
        line.setSvcContrBllgThruDt((String) contrBllgMap.get("SVC_CONTR_BLLG_THRU_DT"));
        tokenBiz.addLineData(line);

        // Set Condition Data
        BigDecimal svcRgPk = getSvcRgPk(msgMap);
        tokenBiz.setBizId(WF_PROCESS_NM);
        tokenBiz.setCondNum1(svcRgPk);
        tokenBiz.setCondNum2(mtrChrgFuncAmt);
        tokenBiz.setCondStr1((String) contrBllgMap.get("SVC_CONTR_BR_CD"));
        tokenBiz.setCondStr2((String) contrBllgMap.get("CONTR_ADMIN_PSN_CD"));
        tokenBiz.setCondStr3(FLG_OFF_N);
        tokenBiz.setCondStr4(ZYPCodeDataUtil.getVarCharConstValue(CONTR_BR_FIRST_ORG_CD, pMsg.glblCmpyCd.getValue()));

        // Set Screen Data
        tokenBiz.setAttribute1Lbl("Contract #");
        tokenBiz.setAttribute1((String) contrBllgMap.get("DS_CONTR_NUM"));
        tokenBiz.setAttribute2Lbl("Bill To Customer Code");
        tokenBiz.setAttribute2((String) contrBllgMap.get("BILL_TO_CUST_CD"));
        tokenBiz.setAttribute3Lbl("Contract Admin");
        tokenBiz.setAttribute3((String) contrBllgMap.get("CONTR_ADMIN_PSN_CD"));
        // START 2018/07/04 K.Kitachi [QC#26891, ADD]
        tokenBiz.setAttribute4Lbl("Interface Date");
        tokenBiz.setAttribute4(ZYPDateUtil.formatEzd8ToDisp((String) contrBllgMap.get("NEXT_BLLG_DT"), true));
        // END 2018/07/04 K.Kitachi [QC#26891, ADD]

        // START 2018/07/04 K.Kitachi [QC#26891, ADD]
        tokenBiz.setBizScreenId("NSAL0920");
        tokenBiz.setBizScreenParams((String) contrBllgMap.get("DS_CONTR_NUM"), (String) contrBllgMap.get("NEXT_BLLG_DT"), (String) contrBllgMap.get("BLLG_APVL_REQ_FLG"));
        // END 2018/07/04 K.Kitachi [QC#26891, ADD]

        // add start 2019/10/01 QC#53643
        // Set Mail Item
        tokenBiz.setHdrAttrb1(formatAmount(mtrChrgFuncAmt));
        // add end 2019/10/01 QC#53643

        return tokenBiz;
    }
    // START 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]
    private void workflowProcessForComp(S21ApiMessageMap msgMap, Map<String, Object> dsContr) {

        String dsContrCatgCd = (String) dsContr.get("DS_CONTR_CATG_CD");
        // START 2019/11/28 [QC#53567, Mod]
        List<Map<String, Object>> contrBllgList = getContrBllgList(msgMap, dsContrCatgCd, true);
        // END 2019/11/28 [QC#53567, Mod]

        for (Map<String, Object> contrBllgMap : contrBllgList) {
            String documentId = getDocumentId(msgMap, contrBllgMap);
            cancelWFForComp(msgMap, documentId);
            // START 2019/09/02 K.Kitachi [QC#52695, ADD]
            BigDecimal dsContrDtlPk = (BigDecimal) contrBllgMap.get("DS_CONTR_DTL_PK");
            documentId = S21StringUtil.concatStrings(documentId, dsContrDtlPk.toPlainString());
            cancelWFForComp(msgMap, documentId);
            // END 2019/09/02 K.Kitachi [QC#52695, ADD]
            BigDecimal workflowId = startWFForComp(msgMap, documentId, contrBllgMap);
            insertSvcMemo(msgMap, workflowId, dsContr);
        }
    }

    private void cancelWFForComp(S21ApiMessageMap msgMap, String documentId) {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        List<S21NwfProcess> processes = null;

        try {
            context = factory.getContex();
            processes = context.getProcess(WF_PROCESS_NM_COMP, documentId);

            for (S21NwfProcess nwfProcess : processes) {
                if (nwfProcess.isActive()) {
                    nwfProcess.getToken().cancel();
                }
            }
        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfBizException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfException e) {
            msgMap.addXxMsgId(NSZM0866E);
        }
    }

    private BigDecimal startWFForComp(S21ApiMessageMap msgMap, String documentId, Map<String, Object> contrBllgMap) {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;
        BigDecimal workflowId = null;

        try {
            context = factory.getContex();
            process = context.newProcess(WF_PROCESS_NM_COMP);
            process.setDocumentId(documentId);

            // set Token Object
            NSZC054001TokenObject tokenBiz = setTokenBizForComp(msgMap, documentId, contrBllgMap);
            S21NwfTokenImpl token = (S21NwfTokenImpl) process.getToken();
            // START 2018/01/18 [QC#29791, ADD]
            String rgtnUsrId = getMtrReadRgtnUsrId(contrBllgMap);
            if (rgtnUsrId != null) {
                token.setAutoDelegateUser(rgtnUsrId);
            }
            // END   2018/01/18 [QC#29791, ADD]
            token.setTokenObj(tokenBiz);

            // Start Workflow
            token.start();
            workflowId = process.getProcessId();

        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0926E);
        } catch (S21NwfBizException e) {
            // Business Error Logic
            // Auto Approve Process Call APIs
            // Approve API / Reject API / Process End API Error
            msgMap.addXxMsgIdWithPrm(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
        } catch (S21NwfException e) {
            // System Error Logic
            msgMap.addXxMsgId(NSZM0926E);
        }
        return workflowId;
    }

    private NSZC054001TokenObject setTokenBizForComp(S21ApiMessageMap msgMap, String documentId, Map<String, Object> contrBllgMap) {

        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();

        // Business Token Object
        NSZC054001TokenObject tokenBiz = new NSZC054001TokenObject();

        // Line Data
        NSZC054001TokenObjectLine line = new NSZC054001TokenObjectLine();
        line.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        line.setWfPreViewBllgId(documentId);
        line.setDsContrNum((String) contrBllgMap.get("DS_CONTR_NUM"));
        if (hasValue(pMsg.svcMachMstrPk)) {
            line.setSvcMachMstrPk(pMsg.svcMachMstrPk.getValue());
        }
        if (DS_CONTR_CATG.AGGREGATE.equals((String) contrBllgMap.get("DS_CONTR_CATG_CD"))) {
            BigDecimal svcMachMstrPk = (BigDecimal) contrBllgMap.get("SVC_MACH_MSTR_PK");
            line.setSvcMachMstrPk(svcMachMstrPk);
        }
        line.setBillToCustCd((String) contrBllgMap.get("BILL_TO_CUST_CD"));
        line.setContrAdminPsnCd((String) contrBllgMap.get("CONTR_ADMIN_PSN_CD"));
        line.setSvcContrBllgFromDt((String) contrBllgMap.get("SVC_CONTR_BLLG_FROM_DT"));
        line.setSvcContrBllgThruDt((String) contrBllgMap.get("SVC_CONTR_BLLG_THRU_DT"));
        tokenBiz.addLineData(line);

        // Set Condition Data
        tokenBiz.setBizId(WF_PROCESS_NM_COMP);
        tokenBiz.setCondStr1((String) contrBllgMap.get("SVC_CONTR_BR_CD"));
        tokenBiz.setCondStr2(ZYPCodeDataUtil.getVarCharConstValue(SVC_ORG_FUNC_ROLE_TP_SUPERVR, pMsg.glblCmpyCd.getValue()));
        tokenBiz.setCondStr3(pMsg.slsDt.getValue());

        // Set Screen Data
        tokenBiz.setAttribute1Lbl("Contract #");
        tokenBiz.setAttribute1((String) contrBllgMap.get("DS_CONTR_NUM"));
        tokenBiz.setAttribute2Lbl("Bill To Customer Code");
        tokenBiz.setAttribute2((String) contrBllgMap.get("BILL_TO_CUST_CD"));
        tokenBiz.setAttribute3Lbl("Contract Admin");
        tokenBiz.setAttribute3((String) contrBllgMap.get("CONTR_ADMIN_PSN_CD"));
        // START 2018/07/04 K.Kitachi [QC#26891, ADD]
        tokenBiz.setAttribute4Lbl("Interface Date");
        tokenBiz.setAttribute4(ZYPDateUtil.formatEzd8ToDisp((String) contrBllgMap.get("NEXT_BLLG_DT"), true));
        // END 2018/07/04 K.Kitachi [QC#26891, ADD]

        // START 2018/07/04 K.Kitachi [QC#26891, ADD]
        tokenBiz.setBizScreenId("NSAL0920");
        tokenBiz.setBizScreenParams((String) contrBllgMap.get("DS_CONTR_NUM"), (String) contrBllgMap.get("NEXT_BLLG_DT"), (String) contrBllgMap.get("BLLG_APVL_REQ_FLG"));
        // END 2018/07/04 K.Kitachi [QC#26891, ADD]

        // add start 2019/10/01 QC#53643
        // Set Mail Item
        BigDecimal mtrChrgFuncAmt = getMtrChrgFuncAmt(msgMap, contrBllgMap);
        tokenBiz.setHdrAttrb1(formatAmount(mtrChrgFuncAmt));
        // add end 2019/10/01 QC#53643

        return tokenBiz;
    }
    // END 2018/03/13 U.Kim [QC#QC#18884(Sol#337), ADD]

    // START 2019/11/28 [QC#53567, Mod]
    private List<Map<String, Object>> getContrBllgList(S21ApiMessageMap msgMap, String dsContrCatgCd, boolean negFlg) {

        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrPk", pMsg.dsContrPk.getValue());
		// Mod Start 04/14/2016 <QC#6657>
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            ssmParam.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            ssmParam.put("dsContrDtlTpFleet", DS_CONTR_DTL_TP.FLEET);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            ssmParam.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        }
		// Mod End  04/14/2016 <QC#6657>
        ssmParam.put("mtrReadDt", pMsg.mtrReadDt.getValue());
        if (negFlg) {
            ssmParam.put("negFlg", FLG_ON_Y);
        } else  {
            ssmParam.put("negFlg", FLG_OFF_N);
        }
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getContrBllgList", ssmParam);
    }
    // END 2019/11/28 [QC#53567, Mod]

    private String getDocumentId(S21ApiMessageMap msgMap, Map<String, Object> contrBllgMap) {

        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();
        StringBuilder sb = new StringBuilder();
        if (DS_CONTR_CATG.REGULAR.equals((String) contrBllgMap.get("DS_CONTR_CATG_CD"))) {
            sb.append(PREFIX_DOC_ID_REG);
            sb.append(pMsg.svcMachMstrPk.getValue().toPlainString());
        // mod start 2016/10/03 CSA Defect#10729
        } else if (DS_CONTR_CATG.FLEET.equals((String) contrBllgMap.get("DS_CONTR_CATG_CD"))) {
            sb.append(PREFIX_DOC_ID_FLT);
            sb.append(pMsg.dsContrPk.getValue().toPlainString());
        } else if (DS_CONTR_CATG.AGGREGATE.equals((String) contrBllgMap.get("DS_CONTR_CATG_CD"))) {
            sb.append(PREFIX_DOC_ID_AGG);
            BigDecimal svcMachMstrPk = (BigDecimal) contrBllgMap.get("SVC_MACH_MSTR_PK");
            sb.append(svcMachMstrPk.toPlainString());
        }
        // mod end 2016/10/03 CSA Defect#10729
        sb.append(contrBllgMap.get("BILL_TO_CUST_CD"));
        sb.append(contrBllgMap.get("SVC_CONTR_BLLG_THRU_DT"));
        return sb.toString();
    }

    private BigDecimal getMtrChrgFuncAmt(S21ApiMessageMap msgMap, Map<String, Object> contrBllgMap) {

        String dsContrCatgCd = (String) contrBllgMap.get("DS_CONTR_CATG_CD");
        String billToCustCd = (String) contrBllgMap.get("BILL_TO_CUST_CD");

        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrPk", pMsg.dsContrPk.getValue());
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            ssmParam.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        }
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("mtrReadDt", pMsg.mtrReadDt);
        return (BigDecimal) ssmBatchClient.queryObject("getMtrChrgFuncAmt", ssmParam);
    }

    private BigDecimal getSvcRgPk(S21ApiMessageMap msgMap) {

        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrPk", pMsg.dsContrPk.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("maxDate", MAX_DATE);
        return (BigDecimal) ssmBatchClient.queryObject("getSvcRgPk", ssmParam);
    }
    // END 03/30/2016 T.Aoyagi [QC#1467, ADD]

    @SuppressWarnings("unchecked")
    private Map<String, Object> getDsContr(NSZC054001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrPk", pMsg.dsContrPk.getValue());
        paramMap.put("dsContrNum", pMsg.dsContrNum.getValue());
        paramMap.put("dsContrTpWty", DS_CONTR_TP.WARRANTY);
        // Del Start 04/08/2016 <QC#6730>
//        paramMap.put("slsDt", pMsg.slsDt.getValue());
        // Del End 04/08/2016 <QC#6730>
        return (Map<String, Object>) ssmBatchClient.queryObject("getDsContr", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getBllgSchdReg(NSZC054001PMsg pMsg, BigDecimal dsContrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrPk", dsContrPk);
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        // Del Start 04/08/2016 <QC#6730>
//        paramMap.put("slsDt", pMsg.slsDt.getValue());
        // Del End 04/08/2016 <QC#6730>
        paramMap.put("usgChrgFlgY", FLG_ON_Y);
        paramMap.put("mtrReadDtFrom", this.mtrReadDtFrom);
        // mod start 2017/08/15 QC#18799
//        paramMap.put("mtrReadDtThru", this.mtrReadDtThru);
        paramMap.put("slsDt", pMsg.slsDt.getValue());
        paramMap.put("mtrReadDt", pMsg.mtrReadDt.getValue());
        paramMap.put("dateFormat", DATE_FORMAT);
        paramMap.put("months", BLLG_CYCLE_UOM.MONTHS);
        paramMap.put("defBefDays", DEF_WINDOW_BEF_DAYS);
        // mod end 2017/08/15 QC#18799
        // START 2023/05/09 K.Kitachi [QC#61469, ADD]
        BigDecimal mtrReadWinMlyDaysAot = BigDecimal.ZERO;
        BigDecimal mtrReadWinOthDaysAot = BigDecimal.ZERO;
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        setValue(dsWinDaysTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, "*");
        setValue(dsWinDaysTMsg.svcLineBizCd, "*");
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21ApiTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            mtrReadWinMlyDaysAot = dsWinDaysTMsg.mtrReadWinMlyDaysAot.getValue();
            mtrReadWinOthDaysAot = dsWinDaysTMsg.mtrReadWinOthDaysAot.getValue();
        }
        paramMap.put("mtrReadWinMlyDaysAot", mtrReadWinMlyDaysAot);
        paramMap.put("mtrReadWinOthDaysAot", mtrReadWinOthDaysAot);
        // END 2023/05/09 K.Kitachi [QC#61469, ADD]
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBllgSchdReg", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getBllgSchdFlt(NSZC054001PMsg pMsg, BigDecimal dsContrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrPk", dsContrPk);
        paramMap.put("dsContrDtlTpFleet", DS_CONTR_DTL_TP.FLEET);
        // Del Start 04/08/2016 <QC#6730>
//        paramMap.put("slsDt", pMsg.slsDt.getValue());
        // Del End 04/08/2016 <QC#6730>
        paramMap.put("usgChrgFlgY", FLG_ON_Y);
        paramMap.put("mtrReadDtFrom", this.mtrReadDtFrom);
        // mod start 2017/08/15 QC#18799
//        paramMap.put("mtrReadDtThru", this.mtrReadDtThru);
        paramMap.put("slsDt", pMsg.slsDt.getValue());
        paramMap.put("mtrReadDt", pMsg.mtrReadDt.getValue());
        paramMap.put("dateFormat", DATE_FORMAT);
        paramMap.put("months", BLLG_CYCLE_UOM.MONTHS);
        paramMap.put("defBefDays", DEF_WINDOW_BEF_DAYS);
        // mod end 2017/08/15 QC#18799
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBllgSchdFlt", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getBllgSchdAgg(NSZC054001PMsg pMsg, BigDecimal dsContrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrPk", dsContrPk);
        paramMap.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        // Del Start 04/08/2016 <QC#6730>
//        paramMap.put("slsDt", pMsg.slsDt.getValue());
        // Del End 04/08/2016 <QC#6730>
        paramMap.put("usgChrgFlgY", FLG_ON_Y);
        paramMap.put("mtrReadDtFrom", this.mtrReadDtFrom);
        // mod start 2017/08/15 QC#18799
//        paramMap.put("mtrReadDtThru", this.mtrReadDtThru);
        paramMap.put("slsDt", pMsg.slsDt.getValue());
        paramMap.put("mtrReadDt", pMsg.mtrReadDt.getValue());
        paramMap.put("dateFormat", DATE_FORMAT);
        paramMap.put("months", BLLG_CYCLE_UOM.MONTHS);
        paramMap.put("defBefDays", DEF_WINDOW_BEF_DAYS);
      // mod end 2017/08/15 QC#18799
        // START 2023/05/09 K.Kitachi [QC#61469, ADD]
        BigDecimal mtrReadWinMlyDaysAot = null;
        BigDecimal mtrReadWinOthDaysAot = null;
        DS_WIN_DAYSTMsg dsWinDaysTMsg = new DS_WIN_DAYSTMsg();
        setValue(dsWinDaysTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(dsWinDaysTMsg.dsWinDaysTrgtPerTxt, "*");
        setValue(dsWinDaysTMsg.svcLineBizCd, "*");
        dsWinDaysTMsg = (DS_WIN_DAYSTMsg) S21ApiTBLAccessor.findByKey(dsWinDaysTMsg);
        if (dsWinDaysTMsg != null) {
            mtrReadWinMlyDaysAot = dsWinDaysTMsg.mtrReadWinMlyDaysAot.getValue();
            mtrReadWinOthDaysAot = dsWinDaysTMsg.mtrReadWinOthDaysAot.getValue();
        }
        paramMap.put("mtrReadWinMlyDaysAot", mtrReadWinMlyDaysAot);
        paramMap.put("mtrReadWinOthDaysAot", mtrReadWinOthDaysAot);
        // END 2023/05/09 K.Kitachi [QC#61469, ADD]
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBllgSchdAgg", paramMap);
    }

	// Add Start 04/14/2016 <QC#6657>
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getBllgSchdAggChild(NSZC054001PMsg pMsg, BigDecimal prntDsContrBllgSchdPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getBllgSchdAggChild", paramMap);
    }
	// Add End  04/14/2016 <QC#6657>

    @SuppressWarnings("unchecked")
    private Map<String, Object> getMtrBllg(NSZC054001PMsg pMsg, BigDecimal dsContrBllgSchdPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (Map<String, Object>) ssmBatchClient.queryObject("getMtrBllg", paramMap);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getPreMtrBllg(NSZC054001PMsg pMsg, BigDecimal dsContrBllgMtrPk, String mtrBllgFromDt) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        paramMap.put("usgChrgFlgY", FLG_ON_Y);
        paramMap.put("invFlgY", FLG_ON_Y);
        paramMap.put("mtrReadDtPrvw", this.mtrReadDtPrvw);
        paramMap.put("mtrBllgFromDt", mtrBllgFromDt);
        paramMap.put("invTpCd", INV_TP.INVOICE);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPreMtrBllg", paramMap);
    }

    private DS_CONTRTMsg getDsContrFindByKeyForUpdate(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg paramTMsg = new DS_CONTRTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    // START 2018/04/23 K.Kojima [QC#25446,ADD]
    private DS_CONTR_DTLTMsg getDsContrDtlFindByKeyForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg paramTMsg = new DS_CONTR_DTLTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }
    // END 2018/04/23 K.Kojima [QC#25446,ADD]

    private SVC_CONTR_BLLGTMsg getSvcContrBllgFindByKeyForUpdate(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_BLLGTMsg paramTMsg = new SVC_CONTR_BLLGTMsg();
        setValue(paramTMsg.glblCmpyCd, glblCmpyCd);
        setValue(paramTMsg.svcContrBllgPk, svcContrBllgPk);
        return (SVC_CONTR_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(paramTMsg);
    }

    private static Date toDate(String inDtTm, String inFormat) {
        SimpleDateFormat parser = new SimpleDateFormat(inFormat);
        try {
            return parser.parse(inDtTm);
        } catch (ParseException e) {
            return null;
        }
    }

    private static String toStringDate(Date inDtTm, String inFormat) {
        SimpleDateFormat parser = new SimpleDateFormat(inFormat);
        return parser.format(inDtTm);
    }

    private BigDecimal nullToZero(BigDecimal item) {
        if (!hasValue(item)) {
            return BigDecimal.ZERO;
        }
        return item;
    }

    private boolean checkErrMsg(S21ApiMessageMap msgMap, EZDPMsg apiPMsg) {
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                msgMap.addXxMsgId(xxMsgId);
            }
            return false;
        }
        return true;
    }

    // add start 2016/06/01 CSA Defect#1523, 4624
    private void callContrTrkAPI(S21ApiMessageMap msgMap, Map<String, Object> dsContr) {
        S21UserProfileServiceFactory profileService = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService profile;
        S21UserInfo userInfo = null;
        if (profileService != null) {
            profile = profileService.getService();
            if (profile != null) {
                userInfo = profile.getContextUserInfo();
            }
        }
        if (userInfo == null) {
            return;
        }

        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();
        BigDecimal dsContrPk = (BigDecimal) dsContr.get("DS_CONTR_PK");
        if (!hasValue(dsContrPk)) {
            return;
        }

        ContrTrkInfo contrTrkInfo = NSXC001001ContractTracking.createContrTrkInfo(pMsg.glblCmpyCd.getValue(), ContrTrkProcMode.PREVIEW_BILLING.code, dsContrPk, null, null, null, null, null, null, userInfo.getUserId(), null, null, null);
        if (!NSXC001001ContractTracking.callContrTrkForDsContr(contrTrkInfo, this.onBatTp)) {
            msgMap.addXxMsgId(NSXC001001ContractTracking.ERR_MSG_ID);
        }
    }
    // add end 2016/06/01 CSA Defect#1523, 4624

    // mod start 2016/12/15 CSA Defect#16285
    // mod start 2017/02/22 CSA Defect#16285-1
    private boolean insertSvcMemo(S21ApiMessageMap msgMap, BigDecimal workflowId, Map<String, Object> dsContr) {
    // mod end   2017/02/22 CSA Defect#16285-1
        NSZC054001PMsg pMsg = (NSZC054001PMsg) msgMap.getPmsg();
        String dsContrNum = (String) dsContr.get("DS_CONTR_NUM");
        BigDecimal dsContrPk = (BigDecimal) dsContr.get("DS_CONTR_PK");
        // mod start 2017/02/22 CSA Defect#16285-1
        // String svcCmntTxt = String.format(SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR, dsContrNum);
        String svcCmntTxt = String.format(SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR, workflowId, dsContrNum);
        // mod end   2017/02/22 CSA Defect#16285-1

        SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
        setValue(insertTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(insertTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(insertTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(insertTMsg.svcMemoTpCd, SVC_MEMO_TP.BILLING_HOLD_REASON);
        setValue(insertTMsg.svcCmntTxt, svcCmntTxt);
        setValue(insertTMsg.dsContrPk, dsContrPk);
        setValue(insertTMsg.lastUpdUsrId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        setValue(insertTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
        setValue(insertTMsg.svcMemoRsnCd, SVC_MEMO_RSN.BILLING_HOLD_REASON);

        S21ApiTBLAccessor.insert(insertTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0475E);
            return false;
        }

        return true;
    }
    // mod end   2016/12/15 CSA Defect#16285

    // START 2019/09/05 [QC#52820, DEL]
//    // add start 2017/08/15 QC#18799
//    private BigDecimal getBllgPrvwAvalMaxPrc(NSZC054001PMsg pMsg) {
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        paramMap.put("dsContrPk", pMsg.dsContrPk.getValue());
//        paramMap.put("dsContrNum", pMsg.dsContrNum.getValue());
//        paramMap.put("slsDt", pMsg.slsDt.getValue());
//        BigDecimal bllgPrvwAvalMaxPrc = (BigDecimal) ssmBatchClient.queryObject("getBllgPrvwAvalMaxPrc", paramMap);
//        if (!hasValue(bllgPrvwAvalMaxPrc)) {
//            bllgPrvwAvalMaxPrc = BigDecimal.ZERO;
//        }
//        return bllgPrvwAvalMaxPrc;
//    }
//    // add end 2017/08/15 QC#18799
    // END 2019/09/05 [QC#52820, DEL]

    // START 2019/09/05 [QC#52820, ADD]
    private void setBllgPrvwCheckValue(NSZC054001PMsg pMsg, String svcLineBizCd) {
        if (LINE_BIZ_TP.LFS.equals(svcLineBizCd)) {
            this.bllgPrvwAvalMaxPrc = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_AVAL_MAX_PRC_LFS, pMsg.glblCmpyCd.getValue()));
            this.bllgPrvwMinAmt = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_MIN_AMT_LFS, pMsg.glblCmpyCd.getValue()));
            this.bllgPrvwAvalUpdownRatio = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_UPDOWN_RATIO_LFS, pMsg.glblCmpyCd.getValue()));
            this.bllgPrvw1stMaxPrc = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_1ST_MAX_PRC_LFS, pMsg.glblCmpyCd.getValue()));
 
        } else if (LINE_BIZ_TP.PPS.equals(svcLineBizCd)) {
            this.bllgPrvwAvalMaxPrc = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_AVAL_MAX_PRC_PPS, pMsg.glblCmpyCd.getValue()));
            this.bllgPrvwMinAmt = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_MIN_AMT_PPS, pMsg.glblCmpyCd.getValue()));
            this.bllgPrvwAvalUpdownRatio = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_UPDOWN_RATIO_PPS, pMsg.glblCmpyCd.getValue()));
            this.bllgPrvw1stMaxPrc = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_1ST_MAX_PRC_PPS, pMsg.glblCmpyCd.getValue()));

        } else if (LINE_BIZ_TP.ESS.equals(svcLineBizCd)) {
            this.bllgPrvwAvalMaxPrc = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_AVAL_MAX_PRC_ESS, pMsg.glblCmpyCd.getValue()));
            this.bllgPrvwMinAmt = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_MIN_AMT_ESS, pMsg.glblCmpyCd.getValue()));
            this.bllgPrvwAvalUpdownRatio = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_UPDOWN_RATIO_ESS, pMsg.glblCmpyCd.getValue()));
            this.bllgPrvw1stMaxPrc = nullToZero(ZYPCodeDataUtil.getNumConstValue(CONST_KEY_BLLG_PRVW_1ST_MAX_PRC_ESS, pMsg.glblCmpyCd.getValue()));
        }
    }
    // END 2019/09/05 [QC#52820, ADD]

    // START 2019/01/22 [QC#29719, ADD]
    private String getMtrReadRgtnUsrId(Map<String, Object> contrBllgMap) {
        String rgtnUsrId = (String) contrBllgMap.get("RGTN_USR_ID");
        String ezInUserId = (String) contrBllgMap.get("EZINUSERID");
        if (ezInUserId != null && ezInUserId.equals(rgtnUsrId)) {
            return null;
        } else {
            return rgtnUsrId;
        }
    }
    // END   2019/01/22 [QC#29719, ADD]

    // add start 2019/10/01 QC#53643
    private String formatAmount(BigDecimal amt) {
        if (!hasValue(amt)) {
            return null;
        }
        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        return df.format(amt);
    }
    // add end 2019/10/01 QC#53643
    // 2022/12/19 QC#60886 Add Start
    private boolean isCreateAverageBillingWF(NSZC054001PMsg pMsg, Map<String, Object> bllgSchd, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!ZYPCommonFunc.hasValue(pMsg.dsContrPk)) {
            return true;
        }
        // getAverageBillingBorder
        BigDecimal averageBillingBorder = getAverageBillingBorder(pMsg, bllgSchd);
        if (!ZYPCommonFunc.hasValue(averageBillingBorder)) {
            return true;
        }

        // getAverageBilling
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("dsContrBllgMtrPk", dsContrBllgMtrPk.toString());
        paramMap.put("bllgSchdFromDt", bllgSchd.get("BLLG_SCHD_FROM_DT"));
        paramMap.put("dateFormat", FORMAT_DT);
        paramMap.put("invTpCd", INV_TP.INVOICE);
        BigDecimal averageBilling = (BigDecimal) ssmBatchClient.queryObject("getAverageBilling", paramMap);

        if (!ZYPCommonFunc.hasValue(averageBilling)) {
            return true;
        }
        if (averageBilling.compareTo(averageBillingBorder) > 0) {
            return true;
        }
        return false;
    }

    private BigDecimal getAverageBillingBorder(NSZC054001PMsg pMsg, Map<String, Object> bllgSchd) {
        String numConstName = null;
        if (!ZYPCommonFunc.hasValue(bllgSchd.get("SVC_LINE_BIZ_CD").toString())) {
            return null;
        }
        for (int i = 0; i < BILL_PRVW_AVAL_BILL_LIST[0].length; i++) {
            if (bllgSchd.get("SVC_LINE_BIZ_CD").equals(BILL_PRVW_AVAL_BILL_LIST[0][i])) {
                numConstName = BILL_PRVW_AVAL_BILL_LIST[1][i];
            }
        }
        if (!ZYPCommonFunc.hasValue(numConstName)) {
            return null;
        }
        return ZYPCodeDataUtil.getNumConstValue(numConstName, pMsg.glblCmpyCd.getValue());
    }
    // 2022/12/19 QC#60886 Add End
}
