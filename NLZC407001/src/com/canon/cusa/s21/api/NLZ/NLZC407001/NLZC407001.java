/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC407001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDDBRetryRequestException;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.RTL_WHTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_SERTMsg;
import business.db.RWS_SERTMsgArray;
import business.db.RWS_SHTG_WRKTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_MACH_ROSS_SHIP_RELNTMsg;
import business.db.SVC_MACH_ROSS_SHIP_RELNTMsgArray;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC309001PMsg;
import business.parts.NLZC407001PMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC302001.NLZC302001;
import com.canon.cusa.s21.api.NLZ.NLZC309001.NLZC309001;
import com.canon.cusa.s21.api.NLZ.NLZC309001.constant.NLZC309001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC407001.constant.NLZC407001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SerialInfo;
import com.canon.cusa.s21.common.NLX.NLXC042001.NLXC042001SvcMachMstrCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SER_TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * NLZC407001: Update Serial for Put Away Confirmation And RWS Completion API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name       Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   CITS            K.Ogino    Update          QC#16556
 * 2017/03/17   CITS            T.Tokutomi Update          Merge DS
 * 2017/04/11   CITS            T.Tokutomi Update          Merge DS Lv2
 * 2017/06/21   CITS            K.Fukumura Update          QC#18866
 * 2017/07/06   CITS            Y.Iwasaki  Update          QC#19673
 * 07/07/2017   CITS            Y.Imazu    Update          QC#19730
 * 10/13/2017   CITS            T.Hakodate UPDATE          QC#21857
 * 11/02/2017   CITS            M.Naito    UPDATE          QC#18216
 * 11/06/2017   CITS            K.Ogino    UPDATE          QC#22231
 * 11/20/2017   CITS            T.Tokutomi UPDATE          QC#22178
 * 12/06/2017   CITS            K.Ogino    UPDATE          QC#22837
 * 2017/12/04   Hitachi         J.Kim      Update          QC#18127
 * 12/22/2017   CITS            M.Naito    UPDATE          QC#18216
 * 01/10/2018   CITS            K.Ogino    UPDATE          QC#21756
 * 06/29/2018   CITS            T.Hakodate UPDATE          QC#27036
 * 02/15/2019   CITS            K.Ogino    Update          QC#29680
 * 04/11/2019   CITS            A.Kobayashi UPDATE         QC#30926
 * 10/02/2019   CITS            T.Wada     UPDATE          QC#53836
 * 10/09/2019   CITS            T.Wada     UPDATE          QC#53385
 * 10/17/2019   CITS            T.Wada     UPDATE          QC#54247
 * 11/26/2019   CITS            T.Wada     UPDATE          QC#54249
 * 01/14/2020   CITS            T.Wada     UPDATE          QC#55273
 * 04/17/2020   CITS            K.Ogino    UPDATE          QC#56610
 * 09/22/2023   Hitachi         T.Kuroda   UPDATE          QC#61265
 *</pre>
 */
public class NLZC407001 extends S21ApiCommonBase implements NLZC407001Constant {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21ApiMessageMap */
    private ONBATCH_TYPE onBatchType = null;

    /** glblRwsPutAwayWrkList */
    private List<Map<String, Object>> glblRwsPutAwayWrkList;

    /** svcExchgCatgList */
    private List<String> svcExchgCatgList = null;

    /** svcExchgRwsNumSet */
    private HashSet<String> svcExchgRwsNumSet = null;

    /** nonSvcExchgRwsNumSet */
    private HashSet<String> nonSvcExchgRwsNumSet = null;

    /** warningSkipFlg */
    private String warningSkipFlg = ZYPConstant.FLG_ON_Y;

    /**
     * NLZC407001
     */
    public NLZC407001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute This can be called method from external class.
     * @param param NLZC407001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NLZC407001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);
        this.onBatchType = onBatchType;
        glblRwsPutAwayWrkList = new ArrayList<Map<String, Object>>();
        svcExchgRwsNumSet = new HashSet<String>();
        nonSvcExchgRwsNumSet = new HashSet<String>();
        doProcess();
        msgMap.flush();
    }

    /**
     * execute This can be called method from external class.
     * @param list List<NLZC407001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NLZC407001PMsg> list, final ONBATCH_TYPE onBatchType) {

        for (NLZC407001PMsg param : list) {

            execute(param, onBatchType);
        }
    }

    /**
     * doProcess This is Main process Method.
     */
    private void doProcess() {

        NLZC407001PMsg pMsg = (NLZC407001PMsg) msgMap.getPmsg();

        // Mandatory Check
        if (checkParameter(pMsg)) {

            return;
        }

        try {
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxPrcCloFlg.getValue())) {

                rwsCompletion(pMsg);

            } else {

                doLineProcess(pMsg);
            }

        } catch (SuspendException e) {
            // Error. Process suspend.
            return;
        }
    }

    /**
     * checkMandatory
     * @return boolean Normal:true, Error:false
     */
    private boolean checkParameter(NLZC407001PMsg pMsg) {

        boolean isError = false;

        if (!hasValue(pMsg.glblCmpyCd)) {
            // Global Company Code is mandatory.
            msgMap.addXxMsgId(NLGM0012E);
            isError = true;
        }

        if (!hasValue(pMsg.slsDt)) {
            // Sales Date Code is mandatory
            msgMap.addXxMsgId(NLGM0012E);
            isError = true;
        }

        if (!hasValue(pMsg.rwsNum)) {
            // RWS# is mandatory
            msgMap.addXxMsgId(NLGM0012E);
            isError = true;
        }

        if (!hasValue(pMsg.xxWrnSkipFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            warningSkipFlg = ZYPConstant.FLG_ON_Y;
        } else {
            warningSkipFlg = pMsg.xxWrnSkipFlg.getValue();
        }

        if (!hasValue(pMsg.xxPrcCloFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        }

        return isError;
    }

    /**
     * rwsCompletion
     * @param pMsg
     * @throws SuspendException
     */
    private void rwsCompletion(NLZC407001PMsg pMsg) throws SuspendException {

        Map<String, String> rws = getTargetRwsCompletion(pMsg.glblCmpyCd.getValue(), pMsg.rwsNum.getValue());
        String sceOrdTpCd = rws.get(KEY_SCE_ORD_TP_CD);

        if (isTargetShortage(sceOrdTpCd)) {

            insertRwsShtgWrk(pMsg, sceOrdTpCd);
        }

        // QC#19673: In case of canceling request with the SceOrdTp of Return/Kitting/UnKitting,
        // item should keep its allocation and create new RWS to receive at source WH.
        if (!Arrays.asList(SCE_ORD_TP.RETURN, SCE_ORD_TP.KITTING, SCE_ORD_TP.UN_KITTING).contains(sceOrdTpCd)) {

            closeRwsAllocationOff(pMsg);
        }
    }

    /**
     * @param sceOrdTpCd
     * @return boolean
     */
    private boolean isAssetReturn(String glblCmpyCd, String trxCd, String rtlWhCd) {

        if (TRX.RENTAL_SHIPMENT.equals(trxCd) || TRX.EMSD_SHIPMENT.equals(trxCd) || isAssetWh(glblCmpyCd, rtlWhCd)) {

            return true;
        }

        return false;
    }

    /**
     * @param sceOrdTpCd
     * @return boolean
     */
    private boolean isTargetShortage(String sceOrdTpCd) {

        if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd) || SCE_ORD_TP.RETURN.equals(sceOrdTpCd)) {

            return true;
        }

        return false;
    }

    /**
     * isAssetWh
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean true: Asset false: Inventory
     */
    private boolean isAssetWh(String glblCmpyCd, String rtlWhCd) {

        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, rtlWhCd);
        rtlWhTMsg = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg != null) {
            if (INVTY_ACCT.ASSET.equals(rtlWhTMsg.invtyAcctCd.getValue())) {
                return true;
            }
        }

        return false;
    }

    /**
     * setRwsShtgWrk
     * @param glblCmpyCd
     * @param rwsShotageInfo
     * @param machMstrAlloc
     * @param shtgQty
     * @return RWS_SHTG_WRKTMsg
     */
    private RWS_SHTG_WRKTMsg setRwsShtgWrk(String glblCmpyCd, RwsShotageInfoBean rwsShotageInfo, Map<String, Object> machMstrAlloc, int shtgQty) {
        RWS_SHTG_WRKTMsg rwsShtgWrk = new RWS_SHTG_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsNum, rwsShotageInfo.getRwsNum());
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsLineNum, rwsShotageInfo.getRwsDtlLineNum());
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.svcMachMstrPk, (BigDecimal) machMstrAlloc.get(KEY_SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.serNum, (String) machMstrAlloc.get(KEY_SER_NUM));
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.mdseCd, (String) machMstrAlloc.get(KEY_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.svcConfigMstrPk, (BigDecimal) machMstrAlloc.get(KEY_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.curLocNum, rwsShotageInfo.getInvtyLocCd());
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsShtgQty, new BigDecimal(shtgQty));
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.procStsCd, PROC_STS.COMPLEATED);

        return rwsShtgWrk;
    }

    /**
     * setRwsShtgWrk
     * @param glblCmpyCd String
     * @param rwsShotageInfo String
     * @param machMstr Map<String, Object>
     * @param shtgQty int
     * @param mainSvcMachMstrPk BigDecimal
     * @return RWS_SHTG_WRKTMsg
     */
    private RWS_SHTG_WRKTMsg setRwsShtgWrk(String glblCmpyCd, RwsShotageInfoBean rwsShotageInfo, Map<String, Object> machMstr, int shtgQty, BigDecimal mainSvcMachMstrPk) {

        BigDecimal svcMachMstrPk = (BigDecimal) machMstr.get("SVC_MACH_MSTR_PK");

        RWS_SHTG_WRKTMsg rwsShtgWrk = new RWS_SHTG_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsNum, rwsShotageInfo.getRwsNum());
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsLineNum, rwsShotageInfo.getRwsDtlLineNum());
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.serNum, (String) machMstr.get(KEY_SER_NUM));
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.mdseCd, (String) machMstr.get(KEY_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.svcConfigMstrPk, (BigDecimal) machMstr.get(KEY_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.curLocNum, rwsShotageInfo.getInvtyLocCd());
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.rwsShtgQty, new BigDecimal(shtgQty));
        ZYPEZDItemValueSetter.setValue(rwsShtgWrk.procStsCd, PROC_STS.COMPLEATED);

        // Main machine of Config
        if (ZYPCommonFunc.hasValue(svcMachMstrPk) && ZYPCommonFunc.hasValue(mainSvcMachMstrPk) && svcMachMstrPk.compareTo(mainSvcMachMstrPk) == 0) {

            ZYPEZDItemValueSetter.setValue(rwsShtgWrk.procStsCd, PROC_STS.IN_COMPLETED);
        }

        return rwsShtgWrk;
    }

    /**
     * createMachMstrPMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param mode String
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg createMachMstrPMsg(String glblCmpyCd, String slsDt, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String mode) {

        NSZC001001PMsg machMstrPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, mode);

        if (svcMachMstrTMsg != null) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, svcMachMstrTMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.serNum, svcMachMstrTMsg.serNum.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, svcMachMstrTMsg.stkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, svcMachMstrTMsg.svcMachUsgStsCd.getValue());
        }

        if (ProcessMode.DISPOSAL.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.effThruDt, slsDt);
            // START 2023/09/22 T.Kuroda [QC#61265, ADD]
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
            // END   2022/09/22 T.Kuroda [QC#61265, ADD]
        }

        return machMstrPMsg;
    }

    /**
     * createAssetStgnApiPMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param mdseCd String
     * @param svcMachMstrPk BigDecimal
     * @return NLZC309001PMsg
     */
    private NLZC309001PMsg createAssetStgnApiPMsg(String glblCmpyCd, String slsDt, String mdseCd, BigDecimal svcMachMstrPk) {

        NLZC309001PMsg assetStgnApiPMsg = new NLZC309001PMsg();
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.procModeCd, NLZC309001Constant.PROC_MODE_ASSET_ADJ_OR_DISPOSAL);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.svcMachMstrPk, svcMachMstrPk);

        return assetStgnApiPMsg;
    }

    /**
     * insertRwsShtgWrk
     * @param pMsg NLZC407001PMsg
     * @param sceOrdTpCd String
     * @throws SuspendException
     */
    private void insertRwsShtgWrk(NLZC407001PMsg pMsg, String sceOrdTpCd) throws SuspendException {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String rwsNum = pMsg.rwsNum.getValue();
        String slsDt = pMsg.slsDt.getValue();

        List<Map<String, Object>> machMstrNotRcvList = getMachMstrNotRecieved(glblCmpyCd, rwsNum, sceOrdTpCd);

        if (machMstrNotRcvList == null || machMstrNotRcvList.isEmpty()) {

            return;
        }

        HashMap<String, RwsShotageInfoBean> machMstrNotRcvDtlLineSet = new HashMap<String, RwsShotageInfoBean>();

        // Create PMsg & RWS_SHTG_WRKTMsg
        List<RWS_SHTG_WRKTMsg> rwsShtgWrkList = new ArrayList<RWS_SHTG_WRKTMsg>();
        List<NSZC001001PMsg> machMstrPMsgList = new ArrayList<NSZC001001PMsg>();
        List<NLZC309001PMsg> assetStgnApiPMsgList = new ArrayList<NLZC309001PMsg>();

        // QC#55273 Add Start
        RWS_SERTMsg rwsSerTMsg = new RWS_SERTMsg();
        rwsSerTMsg.setSQLID("002");
        rwsSerTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        rwsSerTMsg.setConditionValue("rwsNum01", pMsg.rwsNum.getValue());
        RWS_SERTMsgArray rwsSerTMsgArray = (RWS_SERTMsgArray) EZDTBLAccessor.findByCondition(rwsSerTMsg);
        // QC#55273 Add End

        for (Map<String, Object> machMstrNotRcv : machMstrNotRcvList) {

            // QC#55273 Add Start
            // Check if the Serial# is included in RWS_SER
            if (!SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) && !SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)){
                boolean rwsSerCheck = false;
                String serNum = (String) machMstrNotRcv.get("SER_NUM");
                if (ZYPCommonFunc.hasValue(serNum) && rwsSerTMsgArray.length() > 0) {
                    for (int i = 0; i < rwsSerTMsgArray.length(); i++) {
                        String rwsSerNum = rwsSerTMsgArray.no(i).serNum.getValue();
                        if (serNum.equals(rwsSerNum)) {
                            rwsSerCheck = true;
                            break;
                        }
                    }
                } else {
                    rwsSerCheck = true;
                }
                if (!rwsSerCheck) {
                    continue;
                }
            }
            // QC#55273 Add End

            String rwsDtlLineNum = (String) machMstrNotRcv.get(KEY_RWS_DTL_LINE_NUM);

            if (machMstrNotRcvDtlLineSet.containsKey(rwsDtlLineNum)) {

                RwsShotageInfoBean rwsShotageInfo = machMstrNotRcvDtlLineSet.get(rwsDtlLineNum);
                rwsShotageInfo.getMachMstrNotRcvList().add(machMstrNotRcv);

            } else {

                RwsShotageInfoBean rwsShotageInfo = new RwsShotageInfoBean();
                Map<String, Object> rwsDtlInfo = getRwsDtlInfo(glblCmpyCd, rwsNum, rwsDtlLineNum);

                rwsShotageInfo.setRwsNum(rwsNum);
                rwsShotageInfo.setRwsDtlLineNum(rwsDtlLineNum);
                rwsShotageInfo.setRtlWhCd((String) rwsDtlInfo.get(KEY_RTL_WH_CD));
                rwsShotageInfo.setSerNumTakeFlg((String) rwsDtlInfo.get(KEY_SER_NUM_TAKE_FLG));
                rwsShotageInfo.setRwsQty((BigDecimal) rwsDtlInfo.get(KEY_RWS_QTY));
                rwsShotageInfo.setRwsPutAwayQty((BigDecimal) rwsDtlInfo.get(KEY_RWS_PUT_AWAY_QTY));
                rwsShotageInfo.setSvcConfigMstrPk((BigDecimal) rwsDtlInfo.get(KEY_SVC_CONFIG_MSTR_PK));
                rwsShotageInfo.setMdseCd((String) rwsDtlInfo.get(KEY_MDSE_CD));
                rwsShotageInfo.setInvtyLocCd((String) rwsDtlInfo.get(KEY_INVTY_LOC_CD));
                rwsShotageInfo.setInvtyStkStsCd((String) rwsDtlInfo.get(KEY_INVTY_STK_STS_CD));
                rwsShotageInfo.setTrxCd((String) rwsDtlInfo.get(KEY_TRX_CD));

                ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                list.add(machMstrNotRcv);

                rwsShotageInfo.setMachMstrNotRcvList(list);
                machMstrNotRcvDtlLineSet.put(rwsDtlLineNum, rwsShotageInfo);
            }
        }

        for (Map.Entry<String, RwsShotageInfoBean> entry : machMstrNotRcvDtlLineSet.entrySet()) {

            RwsShotageInfoBean rwsShotageInfo = entry.getValue();
            int lossQty = rwsShotageInfo.getRwsQty().intValue() - rwsShotageInfo.getRwsPutAwayQty().intValue();

            if (ZYPConstant.FLG_OFF_N.equals(rwsShotageInfo.getSerNumTakeFlg()) && SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {

                int shotageRecord = rwsShotageInfo.getMachMstrNotRcvList().size();
                int shotageCnt = lossQty - shotageRecord;

                if (shotageCnt > 0) {

                    List<Map<String, Object>> machMstrNonAllocList = getMachMstrNonAllocation(glblCmpyCd, rwsShotageInfo);

                    for (int i = 0; i < shotageCnt || i < machMstrNonAllocList.size(); i++) {

                        Map<String, Object> machMstrNonAlloc = machMstrNonAllocList.get(i);

                        // set RWS Shortage Work
                        rwsShtgWrkList.add(setRwsShtgWrk(glblCmpyCd, rwsShotageInfo, machMstrNonAlloc, lossQty));

                        // set Machine Master API Parameter
                        SVC_MACH_MSTRTMsg svcMachMstr = getSvcMachMstr(glblCmpyCd, (BigDecimal) machMstrNonAlloc.get(KEY_SVC_MACH_MSTR_PK));
                        machMstrPMsgList.add(createMachMstrPMsg(glblCmpyCd, slsDt, svcMachMstr, ProcessMode.ALLOCATION_OFF.code));
                        machMstrPMsgList.add(createMachMstrPMsg(glblCmpyCd, slsDt, svcMachMstr, ProcessMode.DISPOSAL.code));

                        // set Asset Master API Parameter
                        if (isAssetReturn(glblCmpyCd, rwsShotageInfo.getTrxCd(), rwsShotageInfo.getRtlWhCd())) {

                            assetStgnApiPMsgList.add(createAssetStgnApiPMsg(glblCmpyCd, slsDt, svcMachMstr.mdseCd.getValue(), svcMachMstr.svcMachMstrPk.getValue()));
                        }
                    }
                }
            }

            ArrayList<Map<String, Object>> machMstrList = rwsShotageInfo.getMachMstrNotRcvList();

            for (Map<String, Object> machMstr : machMstrList) {

                // set RWS Shortage Work
                rwsShtgWrkList.add(setRwsShtgWrk(glblCmpyCd, rwsShotageInfo, machMstr, lossQty, (BigDecimal) machMstr.get(KEY_MAIN_SVC_MACH_MSTR_PK)));

                // set Machine Master API Parameter
                SVC_MACH_MSTRTMsg svcMachMstr = getSvcMachMstr(glblCmpyCd, (BigDecimal) machMstr.get(KEY_SVC_MACH_MSTR_PK));
                machMstrPMsgList.add(createMachMstrPMsg(glblCmpyCd, slsDt, svcMachMstr, ProcessMode.ALLOCATION_OFF.code));
                machMstrPMsgList.add(createMachMstrPMsg(glblCmpyCd, slsDt, svcMachMstr, ProcessMode.DISPOSAL.code));

                // set Asset Master API Parameter
                if (isAssetReturn(glblCmpyCd, rwsShotageInfo.getTrxCd(), rwsShotageInfo.getRtlWhCd())) {

                    assetStgnApiPMsgList.add(createAssetStgnApiPMsg(glblCmpyCd, slsDt, svcMachMstr.mdseCd.getValue(), svcMachMstr.svcMachMstrPk.getValue()));
                }
            }
        }

        // Insert RWS Shortage Work
        for (RWS_SHTG_WRKTMsg rwsShtgWrk : rwsShtgWrkList) {

            EZDTBLAccessor.insert(rwsShtgWrk);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsShtgWrk.getReturnCode())) {

                setMessageID(NLZM2509E);
            }
        }

        // Execute Machine Mster
        if (!machMstrPMsgList.isEmpty()) {

            executeMchMstrUpdateApi(machMstrPMsgList);
        }

        // Execute Asset Master
        if (!assetStgnApiPMsgList.isEmpty()) {

            executeAssetStagingApi(assetStgnApiPMsgList);
        }
    }

    // 2017/11/2 ADD M.Naito QC#18216 START
    /**
     * insertSvcMachRossShipReln
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param soNum String
     * @param soSlpNum String
     * @throws SuspendException
     */
    private void insertSvcMachRossShipReln(String glblCmpyCd, BigDecimal svcMachMstrPk, String soNum, String soSlpNum) throws SuspendException {

        if (!ZYPCommonFunc.hasValue(svcMachMstrPk) || !ZYPCommonFunc.hasValue(soNum) || !ZYPCommonFunc.hasValue(soSlpNum)) {
            return;
        }

        SVC_MACH_ROSS_SHIP_RELNTMsg tMsg = new SVC_MACH_ROSS_SHIP_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.soNum, soNum);
        ZYPEZDItemValueSetter.setValue(tMsg.soSlpNum, soSlpNum);

        S21ApiTBLAccessor.create(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NLZM2514E);
        }
    }
    // 2017/11/2 ADD M.Naito QC#18216 END

    // 2017/12/22 ADD M.Naito QC#18216 START
    /**
     * removeSvcMachRossShipReln
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @throws SuspendException
     */
    private void removeSvcMachRossShipReln(String glblCmpyCd, BigDecimal svcMachMstrPk) throws SuspendException {

        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return;
        }

        SVC_MACH_ROSS_SHIP_RELNTMsg tMsg = new SVC_MACH_ROSS_SHIP_RELNTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);

        SVC_MACH_ROSS_SHIP_RELNTMsgArray removeArray = (SVC_MACH_ROSS_SHIP_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        for (int i = 0; i < removeArray.getValidCount(); i++) {
            S21ApiTBLAccessor.remove(removeArray.no(i));
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(removeArray.no(i).getReturnCode())) {
                this.msgMap.addXxMsgId(NLZM2517E);
            }
        }
    }
    // 2017/12/22 ADD M.Naito QC#18216 END

    /**
     * closeRwsAllocationOff
     * @param pMsg
     * @throws SuspendException
     */
    private void closeRwsAllocationOff(NLZC407001PMsg pMsg) throws SuspendException {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        List<Map<String, Object>> machMstrList = getMachMstrNotRecieved(glblCmpyCd, pMsg.rwsNum.getValue());

        List<NSZC001001PMsg> machMstrPMsgList = new ArrayList<NSZC001001PMsg>();

        for (Map<String, Object> machMstr : machMstrList) {

            NSZC001001PMsg machMstrPMsg = new NSZC001001PMsg();
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrPk, (BigDecimal) machMstr.get("SVC_MACH_MSTR_PK"));
            machMstrPMsgList.add(machMstrPMsg);
        }

        // Execute Machine Mster
        if (!machMstrPMsgList.isEmpty()) {

            executeMchMstrUpdateApi(machMstrPMsgList);
        }
    }

    /**
     * doLineProcess
     * @param pMsg NLZC407001PMsg
     * @throws SuspendException
     */
    private void doLineProcess(NLZC407001PMsg pMsg) throws SuspendException {

        svcExchgCatgList = getSvcExchgCatgList(pMsg.glblCmpyCd.getValue());

        if (pMsg.inputList.getValidCount() > 0) {

            // If detail line Number set, get target put away work.
            for (int i = 0; i < pMsg.inputList.getValidCount(); i++) {

                setRwsPutAwayWrkList(pMsg, i);
            }

        } else {

            setRwsPutAwayWrkList(pMsg, 0);
        }

        for (int i = 0; i < glblRwsPutAwayWrkList.size(); i++) {

            doLineProcess(pMsg, glblRwsPutAwayWrkList.get(i));
        }
    }

    /**
     * doProcess This is Main process Method.
     * @param pMsg NLZC407001PMsg
     * @param rwsPutAwayWrkMap Map<String, Object>
     * @throws SuspendException
     */
    private void doLineProcess(NLZC407001PMsg pMsg, Map<String, Object> rwsPutAwayWrkMap) throws SuspendException {

        String sceOrdTpCd = (String) rwsPutAwayWrkMap.get(KEY_SCE_ORD_TP_CD);
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        String rwsNum = (String) rwsPutAwayWrkMap.get(KEY_RWS_NUM);
        // 2017/11/2 ADD M.Naito QC#18216 START
        String rwsDtlLineNum = (String) rwsPutAwayWrkMap.get(KEY_RWS_DTL_LINE_NUM);
        // 2017/11/2 ADD M.Naito QC#18216 END
        String installBaseCtrlFlg = (String) rwsPutAwayWrkMap.get(KEY_INSTL_BASE_CTRL_FLG);
        String serNumTakeFlg = (String) rwsPutAwayWrkMap.get(KEY_SER_NUM_TAKE_FLG);
        String trxCd = (String) rwsPutAwayWrkMap.get(TRX_CD);
        String invtyAcctCd = (String) rwsPutAwayWrkMap.get(KEY_INVTY_ACCT_CD);
        List<NSZC001001PMsg> machMstrPMsgList = new ArrayList<NSZC001001PMsg>();
        boolean remanItemConv = false;

        /*********************************************************
         * Disposal for Original Item
         ********************************************************/
        // QC#22837 Start
        if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) //
                || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd) //
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)) {

            // True : getMachMstrOriginalItemForKT
            // False : getMachMstrOriginalItemForRP
            String ssmId = (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) ? "getMachMstrOriginalItemForKT" : "getMachMstrOriginalItemForRP";

            // 2017/10/13 MOD T.Hakodate QC#21857 START
            if(SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)){
                ssmId = "getMachMstrOriginalItemForRM";
            }
            // 2017/10/13 MOD T.Hakodate QC#21857 END

            List<Map<String, Object>> oldMachMstrList = getMachMstrOriginalItem(//
                    pMsg.glblCmpyCd.getValue() //
                    , (String) rwsPutAwayWrkMap.get(KEY_PO_RCV_TRX_HDR_NUM) //
                    , (String) rwsPutAwayWrkMap.get(KEY_PO_RCV_TRX_LINE_NUM) //
                    , ssmId);

            if (oldMachMstrList != null && !oldMachMstrList.isEmpty()) {

                List<NSZC001001PMsg> machMstrPmsgList = new ArrayList<NSZC001001PMsg>();

                for (Map<String, Object> oldMachMstr : oldMachMstrList) {

                    SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(glblCmpyCd, (BigDecimal) oldMachMstr.get("SVC_MACH_MSTR_PK"));

                    if (svcMachMstrTMsg == null) {

                        continue;
                    }

                    machMstrPmsgList.add(createMachMstrPMsg(glblCmpyCd, slsDt, svcMachMstrTMsg, null, ProcessMode.ALLOCATION_OFF.code, rwsPutAwayWrkMap));
                    machMstrPmsgList.add(createMachMstrPMsg(glblCmpyCd, slsDt, svcMachMstrTMsg, null, ProcessMode.DISPOSAL.code, rwsPutAwayWrkMap));
                }

                // execute MachMstrUpdtAPI
                if (!machMstrPmsgList.isEmpty()) {

                    executeMchMstrUpdateApi(machMstrPmsgList);
                }
            }

        } else if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {
            // QC#22837
            String ssmId = "getMachMstrOriginalItemForRM";

            List<Map<String, Object>> oldMachMstrList = getMachMstrOriginalItem(//
                    pMsg.glblCmpyCd.getValue() //
                    , (String) rwsPutAwayWrkMap.get(KEY_PO_RCV_TRX_HDR_NUM) //
                    , (String) rwsPutAwayWrkMap.get(KEY_PO_RCV_TRX_LINE_NUM) //
                    , ssmId);

            if (oldMachMstrList != null && !oldMachMstrList.isEmpty()) {

                List<NSZC001001PMsg> machMstrPmsgListRm = new ArrayList<NSZC001001PMsg>();

                for (Map<String, Object> oldMachMstr : oldMachMstrList) {

                    BigDecimal smmSvcConfigMstrPk = (BigDecimal) oldMachMstr.get("SVC_CONFIG_MSTR_PK_SMM");
                    BigDecimal svcMachMstrPK = (BigDecimal) oldMachMstr.get("SVC_MACH_MSTR_PK");
                    BigDecimal svcConfigMstrPk = (BigDecimal) oldMachMstr.get("SVC_CONFIG_MSTR_PK");

                    if (ZYPCommonFunc.hasValue(svcMachMstrPK)) {
                        // Serial
                        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(glblCmpyCd, svcMachMstrPK);

                        if (svcMachMstrTMsg == null) {

                            continue;
                        }

                        machMstrPmsgListRm.add(createMachMstrPMsg(glblCmpyCd, slsDt, svcMachMstrTMsg, null, ProcessMode.ALLOCATION_OFF.code, rwsPutAwayWrkMap));
                        if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && ZYPCommonFunc.hasValue(smmSvcConfigMstrPk)) {
                            machMstrPmsgListRm.add(createMachMstrPMsgRm(glblCmpyCd, slsDt, svcMachMstrTMsg, null, ProcessMode.UPDATE_WAREHOUSE.code, rwsPutAwayWrkMap, oldMachMstr));
                            remanItemConv = true;
                            NSZC001001PMsg machMstr = new NSZC001001PMsg();
                            machMstr.svcMachMstrPk.setValue(svcMachMstrPK);
                            machMstrPMsgList.add(machMstr);
                            continue;
                        } else {
                            machMstrPmsgListRm.add(createMachMstrPMsg(glblCmpyCd, slsDt, svcMachMstrTMsg, null, ProcessMode.DISPOSAL.code, rwsPutAwayWrkMap));
                        }

                    } else if (ZYPCommonFunc.hasValue((String) oldMachMstr.get(KEY_INSTL_BASE_CTRL_FLG)) && //
                            ZYPConstant.FLG_ON_Y.equals((String) oldMachMstr.get(KEY_INSTL_BASE_CTRL_FLG))) {
                        // Non Serial
                        SHPG_ORD_DTLTMsg shpgOrdDtlTMsg = new SHPG_ORD_DTLTMsg();
                        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soNum, (String) oldMachMstr.get(KEY_SO_NUM));
                        ZYPEZDItemValueSetter.setValue(shpgOrdDtlTMsg.soSlpNum, (String) oldMachMstr.get(KEY_SO_SLP_NUM));

                        shpgOrdDtlTMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(shpgOrdDtlTMsg);

                        if (shpgOrdDtlTMsg == null) {
                            continue;
                        }

                        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
                        svcMachMstrTMsg.setSQLID("009");
                        svcMachMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                        svcMachMstrTMsg.setConditionValue("mdseCd01", shpgOrdDtlTMsg.mdseCd.getValue());
                        svcMachMstrTMsg.setConditionValue("curLocNum01", shpgOrdDtlTMsg.invtyLocCd.getValue());
                        svcMachMstrTMsg.setConditionValue("svcMachMaintAvalFlg01", ZYPConstant.FLG_ON_Y);
                        svcMachMstrTMsg.setConditionValue("svcMachMstrStsCd01A", SVC_MACH_MSTR_STS.CREATED);
                        svcMachMstrTMsg.setConditionValue("svcMachMstrStsCd01B", SVC_MACH_MSTR_STS.REMOVED);

                        SVC_MACH_MSTRTMsgArray nonAllocSvcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(svcMachMstrTMsg);

                        if (nonAllocSvcMachMstrTMsgArray == null) {
                            continue;
                        }

                        int shipQty = ((BigDecimal) rwsPutAwayWrkMap.get(KEY_RWS_STK_QTY)).intValue();
                        if (nonAllocSvcMachMstrTMsgArray.getValidCount() > shipQty) {

                            nonAllocSvcMachMstrTMsgArray.setValidCount(shipQty);
                        }

                        for (int i = 0; i < nonAllocSvcMachMstrTMsgArray.getValidCount(); i++) {
                            machMstrPmsgListRm.add(createMachMstrPMsg(glblCmpyCd, slsDt, nonAllocSvcMachMstrTMsgArray.no(i), null, ProcessMode.DISPOSAL.code, rwsPutAwayWrkMap));
                        }
                    }

                }

                // execute MachMstrUpdtAPI
                if (!machMstrPmsgListRm.isEmpty()) {

                    executeMchMstrUpdateApi(machMstrPmsgListRm);
                }
            }
        }
        // QC#22837 End

        // No IB, No Serial. process end.
        if (ZYPConstant.FLG_OFF_N.equals(installBaseCtrlFlg) && ZYPConstant.FLG_OFF_N.equals(serNumTakeFlg)) {

            return;
        }

        /*********************************************************
         * Create Machine Master Update API PMsg for Receive
         ********************************************************/
        // Service Exchange Check
        boolean isSvcExchg = false;

        if (SCE_ORD_TP.RETURN.equals(sceOrdTpCd)) {
            // START 2017/12/04 J.Kim [QC#18127,MOD]
            String trxOrdNum = (String) rwsPutAwayWrkMap.get(KEY_TRX_ORD_NUM);
            isSvcExchg = isServiceExchange(glblCmpyCd, rwsNum, trxOrdNum);
            // END 2017/12/04 J.Kim [QC#18127,MOD]
        }

        BigDecimal rwsStkQty = (BigDecimal) rwsPutAwayWrkMap.get(KEY_RWS_STK_QTY);

        // QC#16556
        List<BigDecimal> detachCmptMachList = new ArrayList<BigDecimal>();
        List<BigDecimal> detachCmptMachListConfigChange = new ArrayList<BigDecimal>();
        List<BigDecimal> attachCmptMachListConfigChange = new ArrayList<BigDecimal>();
        List<BigDecimal> machMstrPkList = new ArrayList<BigDecimal>();
        // For Config Add
        List<BigDecimal> svcConfigMsgrPkList  = new ArrayList<BigDecimal>();
        svcConfigMsgrPkList.add((BigDecimal) rwsPutAwayWrkMap.get(KEY_SVC_CONFIG_MSTR_PK));

        List<NLZC309001PMsg> rtrnAssetStaingPMsgList = new ArrayList<NLZC309001PMsg>();
        List<NLZC309001PMsg> configChngAssetStaingPMsgList = new ArrayList<NLZC309001PMsg>();

        if (ZYPConstant.FLG_ON_Y.equals(installBaseCtrlFlg) && ZYPConstant.FLG_ON_Y.equals(serNumTakeFlg)) {

            List<Map<String, Object>> rwsPutAwaySerList = getRwsPutAwaySerList(glblCmpyCd, (String) rwsPutAwayWrkMap.get(KEY_WRK_TRX_ID), (String) rwsPutAwayWrkMap.get(KEY_SQ_ID));

            BigDecimal serQty = new BigDecimal(rwsPutAwaySerList.size());
            BigDecimal nonSerQty = rwsStkQty.subtract(serQty);
            BigDecimal svcConfigMstrPk = (BigDecimal) rwsPutAwayWrkMap.get(KEY_SVC_CONFIG_MSTR_PK);
            BigDecimal pickSvcConfigMstrPk = (BigDecimal) rwsPutAwayWrkMap.get(KEY_PICK_SVC_CONFIG_MSTR_PK);
            String rmvConfigFlg = (String) rwsPutAwayWrkMap.get(KEY_RMV_CONFIG_FLG);

            // Serial
            if (!remanItemConv) { 
                for (Map<String, Object> rwsPutAwaySerMap : rwsPutAwaySerList) {

                    NLXC042001SerialInfo serialInfo = getSvcMachMstrModeCdList(glblCmpyCd, rwsPutAwayWrkMap, rwsPutAwaySerMap);
                    List<String> ibUpdModeList = serialInfo.getModeCdList();

                    SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();

                    // QC#16556
                    if (ZYPCommonFunc.hasValue(serialInfo.getSvcMachMstrPk())) {

                        svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), serialInfo.getSvcMachMstrPk());

                        if (svcMachMstrTMsg == null) {

                            break;
                        }

                        // Detached from Config
                        if (isDetachCmptMach(sceOrdTpCd, svcMachMstrTMsg.svcConfigMstrPk.getValue(), svcConfigMstrPk, isSvcExchg)) {

                            detachCmptMachList.add(svcMachMstrTMsg.svcMachMstrPk.getValue());

                        } else if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

                            if (ZYPCommonFunc.hasValue(pickSvcConfigMstrPk) && ZYPConstant.FLG_ON_Y.equals(rmvConfigFlg)) {

                                detachCmptMachListConfigChange.add(svcMachMstrTMsg.svcMachMstrPk.getValue());

                            } else if (!ZYPCommonFunc.hasValue(pickSvcConfigMstrPk) && ZYPConstant.FLG_OFF_N.equals(rmvConfigFlg)) {

                                // AddConfig SvcMachMasterPK
                                if (!attachCmptMachListConfigChange.contains(svcMachMstrTMsg.svcMachMstrPk.getValue())) {

                                    attachCmptMachListConfigChange.add(svcMachMstrTMsg.svcMachMstrPk.getValue());
                                }
                            // QC#29680
                            } else if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && ZYPCommonFunc.hasValue(pickSvcConfigMstrPk) && svcConfigMstrPk.compareTo(pickSvcConfigMstrPk) != 0) {
                                // AddConfig SvcMachMasterPK
                                if (!attachCmptMachListConfigChange.contains(svcMachMstrTMsg.svcMachMstrPk.getValue())) {

                                    attachCmptMachListConfigChange.add(svcMachMstrTMsg.svcMachMstrPk.getValue());
                                }                                
                            }

                            // Config Change Asset Staging
                            if (INVTY_ACCT.ASSET.equals(invtyAcctCd)) {

                                configChngAssetStaingPMsgList.add(createAssetStagePMsgForCc(glblCmpyCd, slsDt, rwsPutAwayWrkMap, svcMachMstrTMsg.svcMachMstrPk.getValue(), svcMachMstrTMsg.serNum.getValue()));
                            }
                        }

                        // Return Asset Staging
                        // START 2017/12/04 J.Kim [QC#18127,ADD]
                        // if (isRtrnAssetStage(sceOrdTpCd, trxCd)) {
                        if (isRtrnAssetStage(sceOrdTpCd, trxCd) && !isSvcExchg) {
                            // END 2017/12/04 J.Kim [QC#18127,ADD]

                            rtrnAssetStaingPMsgList.add(createAssetStagePMsgForRt(glblCmpyCd, slsDt, rwsPutAwayWrkMap, svcMachMstrTMsg.svcMachMstrPk.getValue()));
                        }
                    }

                    if (ibUpdModeList != null && !ibUpdModeList.isEmpty()) {

                        machMstrPMsgList.addAll(updateIb(glblCmpyCd, slsDt, svcMachMstrTMsg, rwsPutAwayWrkMap, ibUpdModeList, rwsPutAwaySerMap));
                    }

                    String serErrStsCd = (String) rwsPutAwaySerMap.get(KEY_SER_ERR_STS_CD);

                    if (!ZYPCommonFunc.hasValue(serErrStsCd)) {

                        setSerialErrorStatus(pMsg.glblCmpyCd.getValue(), rwsPutAwaySerMap);
                    }

                    if (!SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

                        updateSerTrx(glblCmpyCd, rwsPutAwayWrkMap, rwsPutAwaySerMap);
                    }
                }
            }

            // Loss Serial
            for (int i = 0; i < nonSerQty.intValue(); i++) {

                NLXC042001SerialInfo serialInfo = getSvcMachMstrModeCdList(glblCmpyCd, rwsPutAwayWrkMap, null);
                List<String> ibUpdModeList = serialInfo.getModeCdList();

                SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();

                if (ZYPCommonFunc.hasValue(serialInfo.getSvcMachMstrPk())) {

                    svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), serialInfo.getSvcMachMstrPk());

                    if (svcMachMstrTMsg == null) {

                        break;
                    }
                }

                if (ibUpdModeList != null && !ibUpdModeList.isEmpty()) {

                    machMstrPMsgList.addAll(updateIb(glblCmpyCd, slsDt, svcMachMstrTMsg, rwsPutAwayWrkMap, ibUpdModeList, null));
                }
            }

            // Set Output Param
            HashSet<BigDecimal> chkMachMstrDuplicate = new HashSet<BigDecimal>();

            for (NSZC001001PMsg machMstrPMsg : machMstrPMsgList) {

                BigDecimal tmpSvcMachMstrPk = machMstrPMsg.svcMachMstrPk.getValue();

                if (!chkMachMstrDuplicate.contains(tmpSvcMachMstrPk)) {

                    setOutputMsg(pMsg, rwsPutAwayWrkMap, tmpSvcMachMstrPk);
                    chkMachMstrDuplicate.add(tmpSvcMachMstrPk);
                }
            }

        } else if (ZYPConstant.FLG_ON_Y.equals(installBaseCtrlFlg) && ZYPConstant.FLG_OFF_N.equals(serNumTakeFlg)) {

            BigDecimal pickSvcConfigMstrPk = (BigDecimal) rwsPutAwayWrkMap.get(KEY_PICK_SVC_CONFIG_MSTR_PK);
            String rmvConfigFlg = (String) rwsPutAwayWrkMap.get(KEY_RMV_CONFIG_FLG);

            // Non-Serial
            int cnt = 0;

            // Get Allocated Machine Master
            List<Map<String, Object>> ibList = getIbListForNonSerial(glblCmpyCd, sceOrdTpCd, rwsPutAwayWrkMap);

            if (ibList != null && !ibList.isEmpty()) {

                for (Map<String, Object> ibMap : ibList) {

                    BigDecimal ibSvcMachMstrPk = (BigDecimal) ibMap.get(KEY_IB_SVC_MACH_MSTR_PK);

                    if (!machMstrPkList.contains(ibSvcMachMstrPk)) {

                        machMstrPkList.add((BigDecimal) ibMap.get(KEY_IB_SVC_MACH_MSTR_PK));
                    }

                    SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), ibSvcMachMstrPk);

                    if (svcMachMstrTMsg != null) {

                        machMstrPMsgList.add(createMachMstrPMsg(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), svcMachMstrTMsg, null, ProcessMode.ALLOCATION_OFF.code, rwsPutAwayWrkMap));
                        machMstrPMsgList.add(setNonSerMachMstrPMsg(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), sceOrdTpCd, svcMachMstrTMsg, rwsPutAwayWrkMap));

                        // QC#30926 START
                        if (SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd) && !ZYPCommonFunc.hasValue(svcMachMstrTMsg.serNum)) {
                            // From Terminate
                            machMstrPMsgList.add(createMachMstrPMsg(glblCmpyCd, slsDt, svcMachMstrTMsg, ProcessMode.DISPOSAL.code));
                        }
                        // QC#30926 END

                        // Detached from Config
                        if (isDetachCmptMach(sceOrdTpCd, svcMachMstrTMsg.svcConfigMstrPk.getValue(), (BigDecimal) rwsPutAwayWrkMap.get(KEY_SVC_CONFIG_MSTR_PK), isSvcExchg)) {

                            detachCmptMachList.add(svcMachMstrTMsg.svcMachMstrPk.getValue());

                        } else if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

                            if (ZYPCommonFunc.hasValue(pickSvcConfigMstrPk) && ZYPConstant.FLG_ON_Y.equals(rmvConfigFlg)) {

                                detachCmptMachListConfigChange.add(svcMachMstrTMsg.svcMachMstrPk.getValue());

                            } else if (!ZYPCommonFunc.hasValue(pickSvcConfigMstrPk) && ZYPConstant.FLG_OFF_N.equals(rmvConfigFlg)) {

                                // AddConfig SvcMachMasterPK
                                if (!attachCmptMachListConfigChange.contains(svcMachMstrTMsg.svcMachMstrPk.getValue())) {
                                    attachCmptMachListConfigChange.add(svcMachMstrTMsg.svcMachMstrPk.getValue());
                                }
                            }

                            // Config Change Asset Staging
                            if (INVTY_ACCT.ASSET.equals(invtyAcctCd)) {

                                configChngAssetStaingPMsgList.add(createAssetStagePMsgForCc(glblCmpyCd, slsDt, rwsPutAwayWrkMap, svcMachMstrTMsg.svcMachMstrPk.getValue(), svcMachMstrTMsg.serNum.getValue()));
                            }
                        }

                        // Return Asset Staging
                        // START 2017/12/04 J.Kim [QC#18127,ADD]
                        // if (isRtrnAssetStage(sceOrdTpCd, trxCd)) {
                        if (isRtrnAssetStage(sceOrdTpCd, trxCd) && !isSvcExchg) {
                            // END 2017/12/04 J.Kim [QC#18127,ADD]

                            rtrnAssetStaingPMsgList.add(createAssetStagePMsgForRt(glblCmpyCd, slsDt, rwsPutAwayWrkMap, svcMachMstrTMsg.svcMachMstrPk.getValue()));
                        }

                        cnt++;

                        if (cnt == rwsStkQty.intValue()) {
                            break;
                        }
                    }
                }
            }

            // WH Transfer Process
            if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) && cnt < rwsStkQty.intValue()) {

                // Get RWS Stock Status Code
                String stkStsCd = getRwsStkStsCd(pMsg, (String) rwsPutAwayWrkMap.get(KEY_RWS_NUM), (String) rwsPutAwayWrkMap.get(KEY_RWS_DTL_LINE_NUM));

                // Get non Allocated Machine Master
                List<Map<String, Object>> nonAllocMachMstrList = getNonAllocMachMstrWHT(pMsg, rwsPutAwayWrkMap, stkStsCd);

                if (nonAllocMachMstrList != null && !nonAllocMachMstrList.isEmpty()) {

                    for (Map<String, Object> nonAllocMachMstrMap : nonAllocMachMstrList) {

                        machMstrPMsgList.add(setNonSerMachMstrPMsg(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), sceOrdTpCd, setSvcMachMstrTMsg(nonAllocMachMstrMap), rwsPutAwayWrkMap));
                        cnt++;

                        if (cnt == rwsStkQty.intValue()) {

                            break;
                        }
                    }
                }
            }

            // Process for Non-Allocated Machine Master
            if (cnt < rwsStkQty.intValue() && isMachMstrLocWh(sceOrdTpCd)) {

                List<Map<String, Object>> nonAllocMachMstrList = getNonAllocMachMstrNonSer(pMsg, rwsPutAwayWrkMap);
                // QC#56610-1
                if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd) && (nonAllocMachMstrList == null || nonAllocMachMstrList.isEmpty())) {
                    cnt++;
                }

                if (nonAllocMachMstrList != null && !nonAllocMachMstrList.isEmpty()) {

                    for (Map<String, Object> nonAllocMachMstrMap : nonAllocMachMstrList) {

                        machMstrPMsgList.add(setNonSerMachMstrPMsg(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), sceOrdTpCd, setSvcMachMstrTMsg(nonAllocMachMstrMap), rwsPutAwayWrkMap));
                        cnt++;

                        if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

                            if (ZYPCommonFunc.hasValue(pickSvcConfigMstrPk) && ZYPConstant.FLG_ON_Y.equals(rmvConfigFlg)) {

                                detachCmptMachListConfigChange.add((BigDecimal) nonAllocMachMstrMap.get(KEY_SVC_MACH_MSTR_PK));

                            } else if (!ZYPCommonFunc.hasValue(pickSvcConfigMstrPk) && ZYPConstant.FLG_OFF_N.equals(rmvConfigFlg)) {

                                // AddConfig SvcMachMasterPK
                                if (!attachCmptMachListConfigChange.contains((BigDecimal) nonAllocMachMstrMap.get(KEY_SVC_MACH_MSTR_PK))) {

                                    attachCmptMachListConfigChange.add((BigDecimal) nonAllocMachMstrMap.get(KEY_SVC_MACH_MSTR_PK));
                                }
                            }

                            // Config Change Asset Staging
                            if (INVTY_ACCT.ASSET.equals(invtyAcctCd)) {

                                configChngAssetStaingPMsgList.add(createAssetStagePMsgForCc(glblCmpyCd, slsDt, rwsPutAwayWrkMap, (BigDecimal) nonAllocMachMstrMap.get(KEY_SVC_MACH_MSTR_PK), (String) nonAllocMachMstrMap.get(KEY_SER_NUM)));
                            }
                        }

                        // Return Asset Staging
                        // START 2017/12/04 J.Kim [QC#18127,ADD]
                        // if (isRtrnAssetStage(sceOrdTpCd, trxCd)) {
                        if (isRtrnAssetStage(sceOrdTpCd, trxCd) && !isSvcExchg) {
                            // END 2017/12/04 J.Kim [QC#18127,ADD]

                            rtrnAssetStaingPMsgList.add(createAssetStagePMsgForRt(glblCmpyCd, slsDt, rwsPutAwayWrkMap, (BigDecimal) nonAllocMachMstrMap.get(KEY_SVC_MACH_MSTR_PK)));
                        }

                        if (cnt == rwsStkQty.intValue()) {

                            break;
                        }
                    }
                }
            }

            // New creation process for remaining qty
            if (cnt < rwsStkQty.intValue()) {

				// QC#54249 Mod Start
                // QC#54247 Mod Start
//                boolean isCfgChgAllRmv = false;
//                if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {
//                    String trxOrdNum = (String) rwsPutAwayWrkMap.get("TRX_ORD_NUM");
//                    if (ZYPCommonFunc.hasValue(trxOrdNum)) {
//                        if (isCfgChgAllRmv(glblCmpyCd,trxOrdNum)) {
//                            isCfgChgAllRmv = true;
//                        }
//                    }
//                }
//                if (!isCfgChgAllRmv) {
                if (!isCfgChgAllRmv(glblCmpyCd,rwsPutAwayWrkMap,sceOrdTpCd)) {
				// QC#54249 Mod End
                    int createCnt = rwsStkQty.intValue() - cnt;

                    while (createCnt != 0) {

                        machMstrPMsgList.add(createMachMstrPMsg(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), null, null, ProcessMode.INSERT_WAREHOUSE.code, rwsPutAwayWrkMap));
                        createCnt--;
                    }
                }
                //QC#54247 Mod End
            }

            // Set Output Param
            for (NSZC001001PMsg machMstrPMsg : machMstrPMsgList) {

                setOutputMsg(pMsg, rwsPutAwayWrkMap, machMstrPMsg.svcMachMstrPk.getValue());
            }

        } else if (ZYPConstant.FLG_OFF_N.equals(installBaseCtrlFlg) && ZYPConstant.FLG_ON_Y.equals(serNumTakeFlg)) {

            List<Map<String, Object>> rwsPutAwaySerList = getRwsPutAwaySerList(glblCmpyCd, (String) rwsPutAwayWrkMap.get(KEY_WRK_TRX_ID), (String) rwsPutAwayWrkMap.get(KEY_SQ_ID));

            // Serial
            for (Map<String, Object> rwsPutAwaySerMap : rwsPutAwaySerList) {

                String serErrStsCd = (String) rwsPutAwaySerMap.get(KEY_SER_ERR_STS_CD);

                if (!ZYPCommonFunc.hasValue(serErrStsCd)) {

                    setSerialErrorStatus(pMsg.glblCmpyCd.getValue(), rwsPutAwaySerMap);
                }

                if (!SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

                    updateSerTrx(glblCmpyCd, rwsPutAwayWrkMap, rwsPutAwaySerMap);
                }
            }

            // Set Output Param
            setOutputMsg(pMsg, rwsPutAwayWrkMap, null);
        }

        // QC#22178 Update start.
        /*********************************************************
         * Machine Master Update for Receive
         ********************************************************/
        // 2017/11/2 MOD M.Naito QC#18216 START
        // check ROSS Item
        Map<String, String> rossShipItemMap = getRossShipItemInfo(pMsg.glblCmpyCd.getValue(), rwsNum, rwsDtlLineNum);
        if (rossShipItemMap != null && !rossShipItemMap.isEmpty()) {

            for (NSZC001001PMsg machMstrPmsg : machMstrPMsgList) {
                executeMchMstrUpdateApiForRossItem(machMstrPmsg, pMsg.glblCmpyCd.getValue(), rossShipItemMap.get(KEY_SO_NUM), rossShipItemMap.get(KEY_SO_SLP_NUM));
            }
        } else {
            if (0 < machMstrPMsgList.size() && !remanItemConv) {

                executeMchMstrUpdateApi(machMstrPMsgList);
            }

            if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)  && !remanItemConv) {
                BigDecimal pickSvcConfigMstrPk = (BigDecimal) rwsPutAwayWrkMap.get(KEY_PICK_SVC_CONFIG_MSTR_PK);
                BigDecimal svcConfigMstrPk = (BigDecimal) rwsPutAwayWrkMap.get(KEY_SVC_CONFIG_MSTR_PK);

                if (pickSvcConfigMstrPk == null) {
                    pickSvcConfigMstrPk = BigDecimal.ZERO;
                }
                if (svcConfigMstrPk == null) {
                    svcConfigMstrPk = BigDecimal.ZERO;
                }

                if (!svcConfigMsgrPkList.isEmpty() //
                        && (BigDecimal.ZERO.compareTo(pickSvcConfigMstrPk) != 0 || BigDecimal.ZERO.compareTo(svcConfigMstrPk) != 0) //
                        && pickSvcConfigMstrPk.compareTo(svcConfigMstrPk) != 0) {
                    for (NSZC001001PMsg machMstr : machMstrPMsgList) {

                        BigDecimal svcMachMstrPk = machMstr.svcMachMstrPk.getValue();

                        // AddConfig SvcMachMasterPK
                        if (!attachCmptMachListConfigChange.contains(svcMachMstrPk)) {
                            attachCmptMachListConfigChange.add(svcMachMstrPk);
                        }
                    }
                }
            }
        }

        /*********************************************************
         * Machine Master Update for Detached from Config
         ********************************************************/
        if (detachCmptMachList != null && !detachCmptMachList.isEmpty()) {

            // Add pMsg for detached from Config
            // QC#53836
            //List<NSZC001001PMsg> machMstrPMsgForDetachConfigList = addMachMstrPMsgForDetachConfig(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), detachCmptMachList, ProcessMode.UPDATE_MACHINE_IN_FIELD.code);
            List<NSZC001001PMsg> machMstrPMsgForDetachConfigList = addMachMstrPMsgForDetachConfig(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), detachCmptMachList, ProcessMode.UPDATE_MACHINE_IN_FIELD.code, rwsPutAwayWrkMap);

            if (machMstrPMsgForDetachConfigList != null && !machMstrPMsgForDetachConfigList.isEmpty()) {

                // Call Machine Master API
                executeMchMstrUpdateApi(machMstrPMsgForDetachConfigList);
            }
        }

        if (detachCmptMachListConfigChange != null && !detachCmptMachListConfigChange.isEmpty()) {

            // Add pMsg for detached from Config
            // QC#53836
            // List<NSZC001001PMsg> machMstrPMsgForDetachConfigList = addMachMstrPMsgForDetachConfig(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), detachCmptMachListConfigChange, ProcessMode.UPDATE_WAREHOUSE.code);
            // QC#54249 Mod Start
        	List<NSZC001001PMsg> machMstrPMsgForDetachConfigList = null;
            if (isCfgChgAllRmv(glblCmpyCd,rwsPutAwayWrkMap,sceOrdTpCd)) {
            	machMstrPMsgForDetachConfigList = addMachMstrPMsgForAllDetachConfig(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), detachCmptMachListConfigChange, ProcessMode.REMOVE_CONFIG.code, rwsPutAwayWrkMap);
            } else {
            	machMstrPMsgForDetachConfigList = addMachMstrPMsgForDetachConfig(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), detachCmptMachListConfigChange, ProcessMode.UPDATE_WAREHOUSE.code, rwsPutAwayWrkMap);
            }

            //List<NSZC001001PMsg> machMstrPMsgForDetachConfigList = addMachMstrPMsgForDetachConfig(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), detachCmptMachListConfigChange, ProcessMode.UPDATE_WAREHOUSE.code, rwsPutAwayWrkMap);
            // QC#54249 Mod End

            if (machMstrPMsgForDetachConfigList != null && !machMstrPMsgForDetachConfigList.isEmpty()) {

                // Call Machine Master API
                executeMchMstrUpdateApi(machMstrPMsgForDetachConfigList);
            }
        }

        /*********************************************************
         * Machine Master Update for Attached to Config
         ********************************************************/
        if (attachCmptMachListConfigChange != null && !attachCmptMachListConfigChange.isEmpty()) {

            Map<BigDecimal, List<BigDecimal>> attachCmptMachMap = new HashMap<BigDecimal, List<BigDecimal>>();
            attachCmptMachMap.put(svcConfigMsgrPkList.get(0), attachCmptMachListConfigChange);

            // Add pMsg for detached from Config
            // QC#53385 Mod Start
            //List<NSZC001001PMsg> machMstrPMsgForAttachConfigList = addMachMstrPMsgForAttachConfig(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), svcConfigMsgrPkList, attachCmptMachMap);
            List<NSZC001001PMsg> machMstrPMsgForAttachConfigList = new ArrayList<NSZC001001PMsg>();
            if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {
                machMstrPMsgForAttachConfigList = addMachMstrPMsgForAttachConfig4CC(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), svcConfigMsgrPkList, attachCmptMachMap, rwsPutAwayWrkMap);
            } else {
                machMstrPMsgForAttachConfigList = addMachMstrPMsgForAttachConfig(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), svcConfigMsgrPkList, attachCmptMachMap);
            }
            // QC#53385 Mod End


            if (machMstrPMsgForAttachConfigList != null && !machMstrPMsgForAttachConfigList.isEmpty()) {

                // Call Machine Master API
                executeMchMstrUpdateApi(machMstrPMsgForAttachConfigList);
            }
        }

        // 2017/11/2 MOD M.Naito QC#18216 END

        // QC#22178 Update end.

        /*********************************************************
         * Return Asset Staging
         ********************************************************/
        if (0 < rtrnAssetStaingPMsgList.size()) {

            executeAssetStagingApi(rtrnAssetStaingPMsgList);
        }

        /*********************************************************
         * QC#16556 Asset Staging
         ********************************************************/
        if (0 < configChngAssetStaingPMsgList.size()) {

            executeAssetStagingApi(configChngAssetStaingPMsgList);
        }

    }

    /**
     * isRtrnAssetStage
     * @param sceOrdTpCd String
     * @param trxCd String
     */
    private boolean isRtrnAssetStage(String sceOrdTpCd, String trxCd) {

        if (SCE_ORD_TP.RETURN.equals(sceOrdTpCd)) {

            if (TRX.RENTAL_SHIPMENT.equals(trxCd) || TRX.EMSD_SHIPMENT.equals(trxCd)) {

                return true;
            }
        }

        return false;
    }

    /**
     * setRwsPutAwayWrkList
     * @param pMsg NLZC407001PMsg
     * @param inputLineNum int
     */
    private void setRwsPutAwayWrkList(NLZC407001PMsg pMsg, int inputLineNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("rwsNum", pMsg.rwsNum.getValue());
        ssmParam.put("svcMachProcStsCd", SVC_MACH_PROC_STS.IN_COMPLETED);

        if (inputLineNum >= 0) {

            if (ZYPCommonFunc.hasValue(pMsg.inputList.no(inputLineNum).wrkTrxId)) {

                ssmParam.put("wrkTrxId", pMsg.inputList.no(inputLineNum).wrkTrxId.getValue());
            }

            if (ZYPCommonFunc.hasValue(pMsg.inputList.no(inputLineNum).sqId)) {

                ssmParam.put("sqId", pMsg.inputList.no(inputLineNum).sqId.getValue());
            }

            if (ZYPCommonFunc.hasValue(pMsg.inputList.no(inputLineNum).rwsDtlLineNum)) {

                ssmParam.put("rwsDtlLineNum", pMsg.inputList.no(inputLineNum).rwsDtlLineNum.getValue());
            }

            if (ZYPCommonFunc.hasValue(pMsg.inputList.no(inputLineNum).invtyStkStsCd)) {

                ssmParam.put("invtyStkStsCd", pMsg.inputList.no(inputLineNum).invtyStkStsCd.getValue());
            }

            if (ZYPCommonFunc.hasValue(pMsg.inputList.no(inputLineNum).mdseCd)) {

                ssmParam.put("mdseCd", pMsg.inputList.no(inputLineNum).mdseCd.getValue());
            }
        }

        glblRwsPutAwayWrkList = (List<Map<String, Object>>) ssmClient.queryObjectList("getRwsPutAwaySerWrkList", ssmParam);
    }

    /**
     * setSerialErrorStatus
     * @param glblCmpyCd String
     * @param rwsPutAwaySerMap Map<String, Object>
     * @throws SuspendException
     */
    private void setSerialErrorStatus(String glblCmpyCd, Map<String, Object> rwsPutAwaySerMap) throws SuspendException {

        String rwsNum = (String) rwsPutAwaySerMap.get(KEY_RWS_NUM);
        String rwsDtlLineNum = (String) rwsPutAwaySerMap.get(KEY_RWS_DTL_LINE_NUM);
        String serNum = (String) rwsPutAwaySerMap.get(KEY_SER_NUM);
        String serErrStsCd = null;

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rwsNum", rwsNum);
        queryParam.put("rwsDtlLineNum", rwsDtlLineNum);

        List<String> rwsSerialList = (List<String>) ssmClient.queryObjectList("getRwsSerialList", queryParam);

        if (rwsSerialList != null && !rwsSerialList.isEmpty()) {

            if (!rwsSerialList.contains(serNum)) {

                setMessageID("NLBM2441W");
                serErrStsCd = "NSC";
                rwsPutAwaySerMap.put("SER_ERR_STS_CD", serErrStsCd);
            }
        }
    }

    /**
     * updateIb
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param rwsPutAwayWrkMap Map<String, Object>
     * @param ibUpdModeList List<String>
     * @param rwsPutAwaySerMap Map<String, Object>
     * @return List<NSZC001001PMsg>
     */
    private List<NSZC001001PMsg> updateIb(String glblCmpyCd, String slsDt, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, Object> rwsPutAwayWrkMap, List<String> ibUpdModeList, Map<String, Object> rwsPutAwaySerMap) {

        String rwsStkDt = (String) rwsPutAwayWrkMap.get(KEY_RWS_STK_DT_TM_TS);
        String invtyStkStsCd = (String) rwsPutAwayWrkMap.get(KEY_INVTY_STK_STS_CD);
        String invtyLocCd = (String) rwsPutAwayWrkMap.get(KEY_INVTY_LOC_CD);
        String mdseCd = (String) rwsPutAwayWrkMap.get(KEY_MDSE_CD);
        String rwsNum = (String) rwsPutAwayWrkMap.get(KEY_RWS_NUM);
        String rwsDtlLineNum = (String) rwsPutAwayWrkMap.get(KEY_RWS_DTL_LINE_NUM);

        String serNum = null;
        BigDecimal svcMachMstrPk = null;
        BigDecimal svcConfigMstrPk = null;
        String svcMachMstrStsCd = null;

        if (rwsPutAwaySerMap != null) {

            serNum = (String) rwsPutAwaySerMap.get(KEY_SER_NUM);
        }

        if (svcMachMstrTMsg != null) {

            svcMachMstrPk = svcMachMstrTMsg.svcMachMstrPk.getValue();
            svcConfigMstrPk = svcMachMstrTMsg.svcConfigMstrPk.getValue();
            svcMachMstrStsCd = svcMachMstrTMsg.svcMachMstrStsCd.getValue();
        }

        if (ZYPCommonFunc.hasValue(rwsStkDt)) {

            if (rwsStkDt.length() > 8) {

                rwsStkDt = rwsStkDt.substring(0, 8);
            }
        }

        List<NSZC001001PMsg> pMsgList = new ArrayList<NSZC001001PMsg>();

        for (String ibUpdMode : ibUpdModeList) {

            NSZC001001PMsg ibUpdApiPMsg = new NSZC001001PMsg();
            ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrPk, svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.serNum, serNum);
            ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcConfigMstrPk, svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrStsCd, svcMachMstrStsCd);

            if (ibUpdMode.equals(ProcessMode.INSERT_WAREHOUSE.code)) {

                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.serNum, serNum);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.stkStsCd, invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.effFromDt, rwsStkDt);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.curLocNum, invtyLocCd);

            } else if (ibUpdMode.equals(ProcessMode.UPDATE_WAREHOUSE.code)) {

                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.stkStsCd, invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.effFromDt, rwsStkDt);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.curLocNum, invtyLocCd);

            } else if (ibUpdMode.equals(ProcessMode.INSERT_WAREHOUSE.code)) {

                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.stkStsCd, invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.effFromDt, rwsStkDt);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.curLocNum, invtyLocCd);

            } else if (ibUpdMode.equals(ProcessMode.INSERT_WAREHOUSE.code)) {

                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.REMOVED);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.stkStsCd, invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.effFromDt, rwsStkDt);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.curLocNum, invtyLocCd);

            } else if (ibUpdMode.equals(ProcessMode.RWS.code)) {

                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.xxModeCd, ProcessMode.RWS.code);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.REMOVED);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.RETURNED);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.rtrnToWhCd, invtyLocCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.stkStsCd, invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.curLocNum, invtyLocCd);

                Map<String, String> srcMap = getRtrnSrcOrder(glblCmpyCd, rwsNum, rwsDtlLineNum);

                if (srcMap != null && !srcMap.isEmpty()) {

                    ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.rmaNum, srcMap.get("CPO_ORD_NUM"));
                    ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.rmaLineNum, srcMap.get("DS_CPO_RTRN_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.rmaLineSubNum, srcMap.get("DS_CPO_RTRN_LINE_SUB_NUM"));
                }

                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachRmvDt, rwsStkDt);

            } else if (ibUpdMode.equals(ProcessMode.TRANSFER_IN.code)) {

                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.xxModeCd, ProcessMode.TRANSFER_IN.code);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrPk, svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrStsCd, svcMachMstrStsCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.stkStsCd, invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.curLocNum, invtyLocCd);

            } else if (ibUpdMode.equals(ProcessMode.ALLOCATION_OFF.code)) {

                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrPk, svcMachMstrPk);

            } else if (ibUpdMode.equals(ProcessMode.UPDATE_INVENTORY.code)) {

                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.xxModeCd, ProcessMode.UPDATE_INVENTORY.code);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcConfigMstrPk, svcConfigMstrPk);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrPk, svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.stkStsCd, invtyStkStsCd);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
                ZYPEZDItemValueSetter.setValue(ibUpdApiPMsg.curLocNum, invtyLocCd);
            }

            pMsgList.add(ibUpdApiPMsg);
        }

        return pMsgList;
    }

    /**
     * updateSerTrx
     * @param pMsg NLZC407001PMsg
     * @param index int
     * @param rwsPutAwaySerMap Map<String, Object>
     * @return boolean
     * @throws SuspendException
     */
    private void updateSerTrx(String glblCmpyCd, Map<String, Object> rwsPutAwayWrkMap, Map<String, Object> rwsPutAwaySerMap) throws SuspendException {

        String serNum = (String) rwsPutAwaySerMap.get(KEY_SER_NUM);
        String invtyStkStsCd = (String) rwsPutAwaySerMap.get(KEY_INVTY_STK_STS_CD);
        String serErrStsCd = (String) rwsPutAwaySerMap.get(KEY_SER_ERR_STS_CD);
        String sceOrdTpCd = (String) rwsPutAwayWrkMap.get(KEY_SCE_ORD_TP_CD);

        NLZC302001PMsg serApiPMsg = new NLZC302001PMsg();
        ZYPEZDItemValueSetter.setValue(serApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).serNum, serNum);
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).mdseCd, (String) rwsPutAwayWrkMap.get(KEY_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).serTrxTs, (String) rwsPutAwayWrkMap.get(KEY_RWS_STK_DT_TM_TS));
        String serTrxEventCd = "";
        String[] serTrxEventStr = CONV_SCE_ORD_TP_MAP.get(sceOrdTpCd);
        if (serTrxEventStr == null) {
            serTrxEventCd = "";
        } else if (SCE_ORD_TP.RETURN.equals(sceOrdTpCd) && ZYPConstant.FLG_OFF_N.equals((String) rwsPutAwayWrkMap.get(KEY_CMPY_INVTY_FLG))) {
            serTrxEventCd = serTrxEventStr[1];
        } else {
            serTrxEventCd = serTrxEventStr[0];
        }
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).serTrxEventCd, serTrxEventCd);
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).serTrxSrcTpCd, SER_TRX_SRC_TP.RWS);
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).serTrxSrcHdrNum, (String) rwsPutAwayWrkMap.get(KEY_RWS_NUM));
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).serTrxSrcLineNum, (String) rwsPutAwayWrkMap.get(KEY_RWS_DTL_LINE_NUM));
        serApiPMsg.UpdateDetailList.no(0).serTrxSrcLineSubNum.clear();
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).serTrxRefNum, (String) rwsPutAwayWrkMap.get(KEY_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).fromLocCd, (String) rwsPutAwayWrkMap.get(KEY_SHIP_FROM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).toLocCd, (String) rwsPutAwayWrkMap.get(KEY_INVTY_LOC_CD));
        serApiPMsg.UpdateDetailList.no(0).oldMdseCd.clear();
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).manCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).serErrStsCd, serErrStsCd);
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).toStkStsCd, invtyStkStsCd);
        serApiPMsg.UpdateDetailList.no(0).fromStkStsCd.clear();
        ZYPEZDItemValueSetter.setValue(serApiPMsg.UpdateDetailList.no(0).locStsCd, LOC_STS.DC_STOCK);
        serApiPMsg.UpdateDetailList.setValidCount(1);
        // Serial Transaction API
        NLZC302001 serTrxUpdApi = new NLZC302001();
        serTrxUpdApi.execute(serApiPMsg, onBatchType);

        if (S21ApiUtil.isXxMsgId(serApiPMsg)) {
            setMessageID(S21ApiUtil.getXxMsgList(serApiPMsg));
        }
    }

    /**
     * executeMchMstrUpdateApi
     * @param machMstrPmsgList List<NSZC001001PMsg>
     * @return true:Success
     * @throws SuspendException
     */
    private void executeMchMstrUpdateApi(List<NSZC001001PMsg> machMstrPmsgList) throws SuspendException {
        NSZC001001 ibUpdApi = new NSZC001001();
        ibUpdApi.execute(machMstrPmsgList, onBatchType);

        for (NSZC001001PMsg machMstrPmsg : machMstrPmsgList) {
            if (S21ApiUtil.isXxMsgId(machMstrPmsg)) {
                setMessageID(S21ApiUtil.getXxMsgList(machMstrPmsg));
            }
        }
    }

    // 2017/11/2 ADD M.Naito QC#18216 START
    /**
     * executeMchMstrUpdateApiForRossItem
     * @param machMstrPmsg NSZC001001PMsg
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param soNum String
     * @param soSlpNum String
     * @return true:Success
     * @throws SuspendException
     */
    private void executeMchMstrUpdateApiForRossItem(NSZC001001PMsg machMstrPmsg, String glblCmpyCd, String soNum, String soSlpNum) throws SuspendException {
        NSZC001001 ibUpdApi = new NSZC001001();
        ibUpdApi.execute(machMstrPmsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(machMstrPmsg)) {
            setMessageID(S21ApiUtil.getXxMsgList(machMstrPmsg));
            return;
        }
        // 2017/12/22 ADD M.Naito QC#18216 START
        // Delete SVC_MACH_ROSS_SHIP_RELN
        removeSvcMachRossShipReln(glblCmpyCd, machMstrPmsg.svcMachMstrPk.getValue());
        // 2017/12/22 ADD M.Naito QC#18216 START
        // Insert SVC_MACH_ROSS_SHIP_RELN
        insertSvcMachRossShipReln(glblCmpyCd, machMstrPmsg.svcMachMstrPk.getValue(), soNum, soSlpNum);
    }
    // 2017/11/2 ADD M.Naito QC#18216 END

    /**
     * executeMchMstrUpdateApi
     * @param machMstrPmsgList List<NSZC001001PMsg>
     * @return true:Success
     * @throws SuspendException
     */
    private void executeAssetStagingApi(List<NLZC309001PMsg> assetStaingPMsgList) throws SuspendException {
        NLZC309001 assetStagingAPI = new NLZC309001();
        assetStagingAPI.execute(assetStaingPMsgList, onBatchType);

        for (NLZC309001PMsg assetStaingPMsg : assetStaingPMsgList) {
            if (S21ApiUtil.isXxMsgId(assetStaingPMsg)) {
                setMessageID(S21ApiUtil.getXxMsgList(assetStaingPMsg));
            }
        }
    }

    /**
     * getSvcMachMstr
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        try {

            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);

            svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(svcMachMstrTMsg);

        } catch (EZDDBRecordLockedException rle) {

            msgMap.addXxMsgId(NLBM1087E);
            return null;

        } catch (EZDDBRetryRequestException rre) {

            msgMap.addXxMsgId(NLBM1077E);
            return null;
        }

        if (svcMachMstrTMsg == null) {

            msgMap.addXxMsgId(NLBM1087E);
            return null;
        }

        return svcMachMstrTMsg;
    }

    /**
     * createMachMstrPMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param serNum String
     * @param mode String
     * @param outputList NLZC407001_outputListPMsg
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg createMachMstrPMsg(String glblCmpyCd, String slsDt, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String serNum, String mode, Map<String, Object> rwsPutAwayWrkMap) {

        NSZC001001PMsg machMstrPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, mode);

        String rwsStkDt = (String) rwsPutAwayWrkMap.get(KEY_RWS_STK_DT_TM_TS);
        String invtyStkStsCd = (String) rwsPutAwayWrkMap.get(KEY_INVTY_STK_STS_CD);
        String invtyLocCd = (String) rwsPutAwayWrkMap.get(KEY_INVTY_LOC_CD);
        String mdseCd = (String) rwsPutAwayWrkMap.get(KEY_MDSE_CD);
        String sceOrdTpCd = (String) rwsPutAwayWrkMap.get(KEY_SCE_ORD_TP_CD);

        if (ZYPCommonFunc.hasValue(rwsStkDt)) {
            if (rwsStkDt.length() > 8) {
                rwsStkDt = rwsStkDt.substring(0, 8);
            }
        }

        if (svcMachMstrTMsg != null) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
            if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, (String) rwsPutAwayWrkMap.get("CONV_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.serNum, (String) rwsPutAwayWrkMap.get("CONV_SER_NUM"));
            // QC#30926 START
            } else if (SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)) {
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.serNum, svcMachMstrTMsg.serNum.getValue());
            // QC#30926 END
            } else {
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, svcMachMstrTMsg.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.serNum, svcMachMstrTMsg.serNum.getValue());
            }
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, svcMachMstrTMsg.stkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, svcMachMstrTMsg.svcMachUsgStsCd.getValue());
        }

        if (ProcessMode.INSERT_WAREHOUSE.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, invtyStkStsCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.effFromDt, rwsStkDt);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.curLocNum, invtyLocCd);

        } else if (ProcessMode.ALLOCATION_OFF.code.equals(mode)) {

            // No Additional Parameter

        } else if (ProcessMode.DISPOSAL.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.TERMINATED);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.effThruDt, slsDt);
            // START 2023/09/22 T.Kuroda [QC#61265, ADD]
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.GONE);
            // END   2022/09/22 T.Kuroda [QC#61265, ADD]

        } else if (ProcessMode.ITEM_CHANGE.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.serNum, serNum);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, mdseCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.effFromDt, slsDt);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.curLocNum, invtyLocCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, invtyStkStsCd);

        } else if (ProcessMode.UPDATE_INVENTORY.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.curLocNum, invtyLocCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, invtyStkStsCd);

        } else if (ProcessMode.RWS.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, ProcessMode.RWS.code);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.REMOVED);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.RETURNED);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.rtrnToWhCd, invtyLocCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, invtyStkStsCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.curLocNum, invtyLocCd);
            Map<String, String> srcMap = getRtrnSrcOrder(glblCmpyCd, (String) rwsPutAwayWrkMap.get(KEY_RWS_NUM), (String) rwsPutAwayWrkMap.get(KEY_RWS_DTL_LINE_NUM));
            if (srcMap != null && !srcMap.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.rmaNum, srcMap.get("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.rmaLineNum, srcMap.get("DS_CPO_RTRN_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsg.rmaLineSubNum, srcMap.get("DS_CPO_RTRN_LINE_SUB_NUM"));
            }
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachRmvDt, rwsStkDt);

        } else if (ProcessMode.RMA.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.rtrnToWhCd, invtyLocCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, invtyStkStsCd);

        } else if (ProcessMode.UPDATE_WAREHOUSE.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.effFromDt, slsDt);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.curLocNum, invtyLocCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, invtyStkStsCd);

        } else if (ProcessMode.TRANSFER_IN.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, invtyStkStsCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.curLocNum, invtyLocCd);

        } else if (ProcessMode.ITEM_CHANGE.code.equals(mode)) {

            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);

        }

        return machMstrPMsg;
    }

    /**
     * isDetachCmptMach
     * @param sceOrdTpCd String
     * @param svcConfigMstrPk BigDecimal
     * @param rwsSvcConfigMstrPk BigDecimal
     * @param boolean isSvcExchg
     * @return boolean
     */
    private boolean isDetachCmptMach(String sceOrdTpCd, BigDecimal svcConfigMstrPk, BigDecimal rwsSvcConfigMstrPk, boolean isSvcExchg) {

        if (SCE_ORD_TP.RETURN.equals(sceOrdTpCd) && !isSvcExchg) {

            if (ZYPCommonFunc.hasValue(svcConfigMstrPk) && !ZYPCommonFunc.hasValue(rwsSvcConfigMstrPk)) {

                return true;
            }
        }

        return false;
    }

    /**
     * setNonSerMachMstrPMsg
     * @param bizMsg NLAL2020CMsg
     * @param glblMsgLine NLAL2020_ASMsg
     * @param rcvDtTm String
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg setNonSerMachMstrPMsg(String glblCmpyCd, String rcvDtTm, String sceOrdTpCd, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, Object> rwsPutAwayWrkMap) {

        if (SCE_ORD_TP.DOMESTIC_CANON_GROUP.equals(sceOrdTpCd) || SCE_ORD_TP.DOMESTIC_PO_EXISTS.equals(sceOrdTpCd) || SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY.equals(sceOrdTpCd)) {

            return createMachMstrPMsg(glblCmpyCd, rcvDtTm, svcMachMstrTMsg, null, ProcessMode.UPDATE_WAREHOUSE.code, rwsPutAwayWrkMap);

        } else if (SCE_ORD_TP.RETURN.equals(sceOrdTpCd)) {

            return createMachMstrPMsg(glblCmpyCd, rcvDtTm, svcMachMstrTMsg, null, ProcessMode.RWS.code, rwsPutAwayWrkMap);

        } else if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

            return createMachMstrPMsg(glblCmpyCd, rcvDtTm, svcMachMstrTMsg, null, ProcessMode.TRANSFER_IN.code, rwsPutAwayWrkMap);

        } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)) {

            return createMachMstrPMsg(glblCmpyCd, rcvDtTm, svcMachMstrTMsg, null, ProcessMode.UPDATE_WAREHOUSE.code, rwsPutAwayWrkMap);

        } else if (SCE_ORD_TP.KITTING_CANCEL.equals(sceOrdTpCd) || SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {

            return createMachMstrPMsg(glblCmpyCd, rcvDtTm, svcMachMstrTMsg, null, ProcessMode.UPDATE_INVENTORY.code, rwsPutAwayWrkMap);
        // QC#30926 START    
        } else if (SCE_ORD_TP.ITEM_CHANGE.equals(sceOrdTpCd)) {

            return createMachMstrPMsg(glblCmpyCd, rcvDtTm, svcMachMstrTMsg, null, ProcessMode.INSERT_WAREHOUSE.code, rwsPutAwayWrkMap);
        // QC#30926 END
        }

        return createMachMstrPMsg(glblCmpyCd, rcvDtTm, svcMachMstrTMsg, null, ProcessMode.UPDATE_INVENTORY.code, rwsPutAwayWrkMap);
    }

    /**
     * addMachMstrPMsgForDetachConfig
     * @param glblCmpyCd String
     * @param slsDt String
     * @param detachCmptMachList List<BigDecimal>
     * @return List<NSZC001001PMsg>
     */
    // QC#53836
    private List<NSZC001001PMsg> addMachMstrPMsgForDetachConfig(String glblCmpyCd, String slsDt, List<BigDecimal> detachCmptMachList, String processMode, Map<String, Object> rwsPutAwayWrkMap) {
    //private List<NSZC001001PMsg> addMachMstrPMsgForDetachConfig(String glblCmpyCd, String slsDt, List<BigDecimal> detachCmptMachList, String processMode) {

        List<Map<String, Object>> configCmptMapList = getConfigCmptList(glblCmpyCd, detachCmptMachList);

        // Config Component Information is not found.
        if (configCmptMapList == null || configCmptMapList.isEmpty()) {

            return null;
        }

        NSZC001001PMsg machMstrPMsgForDetachConfig = new NSZC001001PMsg();
        BigDecimal preSvcConfigMstrPk = null;
        int cmptIndex = 0;

        List<NSZC001001PMsg> machMstrPMsgForDetachConfigList = new ArrayList<NSZC001001PMsg>();

        for (Map<String, Object> configCmptMap : configCmptMapList) {

            BigDecimal svcConfigMstrPk = (BigDecimal) configCmptMap.get("SVC_CONFIG_MSTR_PK");
            BigDecimal mainSvcMachMstrPk = (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_MSTR_PK");
            BigDecimal cmptSvcMachMstrPk = (BigDecimal) configCmptMap.get("CMPT_SVC_MACH_MSTR_PK");

            if (preSvcConfigMstrPk == null || !svcConfigMstrPk.equals(preSvcConfigMstrPk)) {

                if (preSvcConfigMstrPk != null) {

                    machMstrPMsgForDetachConfigList.add(machMstrPMsgForDetachConfig);
                    machMstrPMsgForDetachConfig = new NSZC001001PMsg();
                }

                preSvcConfigMstrPk = svcConfigMstrPk;
                cmptIndex = 0;

                // Main Machine
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.slsDt, slsDt);
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxModeCd, processMode);
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcConfigMstrPk, svcConfigMstrPk);
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachMstrPk, mainSvcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.mdseCd, (String) configCmptMap.get("MAIN_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachMstrStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_MSTR_STS_CD"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.locNm, (String) configCmptMap.get("MAIN_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.addlLocNm, (String) configCmptMap.get("MAIN_ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.firstLineAddr, (String) configCmptMap.get("MAIN_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.scdLineAddr, (String) configCmptMap.get("MAIN_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.thirdLineAddr, (String) configCmptMap.get("MAIN_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.frthLineAddr, (String) configCmptMap.get("MAIN_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.ctyAddr, (String) configCmptMap.get("MAIN_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.stCd, (String) configCmptMap.get("MAIN_ST_CD"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.provNm, (String) configCmptMap.get("MAIN_PROV_NM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.cntyNm, (String) configCmptMap.get("MAIN_CNTY_NM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.postCd, (String) configCmptMap.get("MAIN_POST_CD"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.ctryCd, (String) configCmptMap.get("MAIN_CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachFlNm, (String) configCmptMap.get("MAIN_SVC_MACH_FL_NM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.iwrCondCd, (String) configCmptMap.get("MAIN_IWR_COND_CD"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.effFromDt, (String) configCmptMap.get("MAIN_EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachUsgStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_USG_STS_CD"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.prntSerNum, (String) configCmptMap.get("MAIN_PRNT_SER_NUM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachTrxTpCd, (String) configCmptMap.get("MAIN_SVC_MACH_TRX_TP_CD"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachQty, (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_QTY"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.ownrAcctNum, (String) configCmptMap.get("MAIN_OWNR_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.ownrLocNum, (String) configCmptMap.get("MAIN_OWNR_LOC_NUM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.billToAcctNum, (String) configCmptMap.get("MAIN_BILL_TO_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.billToLocNum, (String) configCmptMap.get("MAIN_BILL_TO_LOC_NUM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.curLocAcctNum, (String) configCmptMap.get("MAIN_CUR_LOC_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.curLocNum, (String) configCmptMap.get("MAIN_CUR_LOC_NUM"));
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachMstrLocStsCd, (String) configCmptMap.get("MAIN_MACH_LOC_STS_CD"));
                // QC#21756
                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.istlDt, (String) configCmptMap.get("MAIN_ISTL_DT"));
            }

            // Component (include main machine)
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, cmptSvcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxCmptMachList.no(cmptIndex).mdseCd, (String) configCmptMap.get("CMPT_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, (String) configCmptMap.get("CMPT_SVC_MACH_TP_CD"));

            if (detachCmptMachList.contains(cmptSvcMachMstrPk)) {

                ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxCmptMachList.no(cmptIndex).effThruDt, slsDt);
            }
            // QC#53836 Add Start
            if (ProcessMode.UPDATE_WAREHOUSE.code.equals(processMode)) {
                String trxOrdNum = (String) rwsPutAwayWrkMap.get("TRX_ORD_NUM");
                if (ZYPCommonFunc.hasValue(trxOrdNum)) {
                    if (isMainMachRmv(glblCmpyCd,trxOrdNum,mainSvcMachMstrPk)) {
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxCmptMachList.no(cmptIndex).effThruDt, slsDt);
                    }
                }
            }
            // QC#53836 Add End

            cmptIndex++;
            machMstrPMsgForDetachConfig.xxCmptMachList.setValidCount(cmptIndex);
        }

        machMstrPMsgForDetachConfigList.add(machMstrPMsgForDetachConfig);
        return machMstrPMsgForDetachConfigList;
    }

    // QC#54249 Add Start
    private List<NSZC001001PMsg> addMachMstrPMsgForAllDetachConfig(String glblCmpyCd, String slsDt, List<BigDecimal> detachCmptMachList, String processMode, Map<String, Object> rwsPutAwayWrkMap) {
        List<Map<String, Object>> configCmptMapList = getConfigCmptList(glblCmpyCd, detachCmptMachList);

        // Config Component Information is not found.
        if (configCmptMapList == null || configCmptMapList.isEmpty()) {
            return null;
        }

        NSZC001001PMsg machMstrPMsgForDetachConfig = new NSZC001001PMsg();
        int cmptIndex = 0;

        List<NSZC001001PMsg> machMstrPMsgForDetachConfigList = new ArrayList<NSZC001001PMsg>();

        for (Map<String, Object> configCmptMap : configCmptMapList) {

            BigDecimal svcConfigMstrPk = (BigDecimal) configCmptMap.get("SVC_CONFIG_MSTR_PK");
            BigDecimal mainSvcMachMstrPk = (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_MSTR_PK");
            BigDecimal cmptSvcMachMstrPk = (BigDecimal) configCmptMap.get("CMPT_SVC_MACH_MSTR_PK");

            // Main Machine
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxModeCd, processMode);
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcConfigMstrPk, svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachMstrPk, mainSvcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.mdseCd, (String) configCmptMap.get("MAIN_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachMstrStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_MSTR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.locNm, (String) configCmptMap.get("MAIN_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.addlLocNm, (String) configCmptMap.get("MAIN_ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.firstLineAddr, (String) configCmptMap.get("MAIN_FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.scdLineAddr, (String) configCmptMap.get("MAIN_SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.thirdLineAddr, (String) configCmptMap.get("MAIN_THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.frthLineAddr, (String) configCmptMap.get("MAIN_FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.ctyAddr, (String) configCmptMap.get("MAIN_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.stCd, (String) configCmptMap.get("MAIN_ST_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.provNm, (String) configCmptMap.get("MAIN_PROV_NM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.cntyNm, (String) configCmptMap.get("MAIN_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.postCd, (String) configCmptMap.get("MAIN_POST_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.ctryCd, (String) configCmptMap.get("MAIN_CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachFlNm, (String) configCmptMap.get("MAIN_SVC_MACH_FL_NM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.iwrCondCd, (String) configCmptMap.get("MAIN_IWR_COND_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.effFromDt, (String) configCmptMap.get("MAIN_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachUsgStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_USG_STS_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.prntSerNum, (String) configCmptMap.get("MAIN_PRNT_SER_NUM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachTrxTpCd, (String) configCmptMap.get("MAIN_SVC_MACH_TRX_TP_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachQty, (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_QTY"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.ownrAcctNum, (String) configCmptMap.get("MAIN_OWNR_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.ownrLocNum, (String) configCmptMap.get("MAIN_OWNR_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.billToAcctNum, (String) configCmptMap.get("MAIN_BILL_TO_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.billToLocNum, (String) configCmptMap.get("MAIN_BILL_TO_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.curLocAcctNum, (String) configCmptMap.get("MAIN_CUR_LOC_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.curLocNum, (String) configCmptMap.get("MAIN_CUR_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.svcMachMstrLocStsCd, (String) configCmptMap.get("MAIN_MACH_LOC_STS_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.istlDt, (String) configCmptMap.get("MAIN_ISTL_DT"));

            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, cmptSvcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxCmptMachList.no(cmptIndex).mdseCd, (String) configCmptMap.get("CMPT_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, (String) configCmptMap.get("CMPT_SVC_MACH_TP_CD"));

            ZYPEZDItemValueSetter.setValue(machMstrPMsgForDetachConfig.xxCmptMachList.no(cmptIndex).effThruDt, slsDt);

            cmptIndex++;
            machMstrPMsgForDetachConfig.xxCmptMachList.setValidCount(cmptIndex);
        }

        machMstrPMsgForDetachConfigList.add(machMstrPMsgForDetachConfig);
        return machMstrPMsgForDetachConfigList;
    	
    }
    // QC#54249 Add End

    /**
     * addMachMstrPMsgForDetachConfig
     * @param glblCmpyCd String
     * @param slsDt String
     * @param attachConfigList List<BigDecimal>
     * @param attachCmptMachMap Map<BigDecimal, List<BigDecimal>>
     * @return List<NSZC001001PMsg>
     */
    private List<NSZC001001PMsg> addMachMstrPMsgForAttachConfig(String glblCmpyCd, String slsDt, List<BigDecimal> attachConfigList, Map<BigDecimal, List<BigDecimal>> attachCmptMachMap) {

        List<NSZC001001PMsg> machMstrPMsgForAttachConfigList = new ArrayList<NSZC001001PMsg>();
        NSZC001001PMsg machMstrPMsgForAttachConfig = new NSZC001001PMsg();

        // Config Master PK Loop
        for (BigDecimal attachConfigPk : attachConfigList) {

            List<BigDecimal> attachCmptMachPkList = attachCmptMachMap.get(attachConfigPk);

            if (attachCmptMachPkList == null || attachCmptMachPkList.isEmpty()) {

                continue;
            }

            // SvcMachMaster in ConfigMaster
            List<Map<String, Object>> configCmptMapList = getConfigCmptListForAddConfig(glblCmpyCd, attachConfigPk);
            BigDecimal preSvcConfigMstrPk = null;
            int cmptIndex = 0;

            // Config Component Information is not found.
            if (configCmptMapList == null || configCmptMapList.isEmpty()) {
                // ******************************************************
                // * Create From Machine Master (Config Master Not Exist)
                // ******************************************************
                configCmptMapList = getSvcMachList(glblCmpyCd, attachCmptMachPkList);

                machMstrPMsgForAttachConfig = new NSZC001001PMsg();

                BigDecimal svcConfigMstrPk = attachConfigPk;
                BigDecimal mainSvcMachMstrPk = attachCmptMachPkList.get(0);
                if (configCmptMapList == null || configCmptMapList.isEmpty()) {
                    mainSvcMachMstrPk = null;

                } else {
                    mainSvcMachMstrPk = attachCmptMachPkList.get(0);
                }
                // Main Machine
                for (Map<String, Object> configCmptMap : configCmptMapList) {
                    BigDecimal cmptSvcMachMstrPk = (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_MSTR_PK");
                    if (preSvcConfigMstrPk == null || !svcConfigMstrPk.equals(preSvcConfigMstrPk)) {

                        if (preSvcConfigMstrPk != null) {
                            machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);
                            machMstrPMsgForAttachConfig = new NSZC001001PMsg();
                        }

                        preSvcConfigMstrPk = svcConfigMstrPk;
                        cmptIndex = 0;
                        // Main Machine
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.slsDt, slsDt);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcConfigMstrPk, svcConfigMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrPk, mainSvcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.serNum, (String) configCmptMap.get("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachTpCd, SVC_MACH_TP.MACHINE);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.mdseCd, (String) configCmptMap.get("MAIN_MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_MSTR_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.locNm, (String) configCmptMap.get("MAIN_LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.addlLocNm, (String) configCmptMap.get("MAIN_ADDL_LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.firstLineAddr, (String) configCmptMap.get("MAIN_FIRST_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.scdLineAddr, (String) configCmptMap.get("MAIN_SCD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.thirdLineAddr, (String) configCmptMap.get("MAIN_THIRD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.frthLineAddr, (String) configCmptMap.get("MAIN_FRTH_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ctyAddr, (String) configCmptMap.get("MAIN_CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.stCd, (String) configCmptMap.get("MAIN_ST_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.provNm, (String) configCmptMap.get("MAIN_PROV_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.cntyNm, (String) configCmptMap.get("MAIN_CNTY_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.postCd, (String) configCmptMap.get("MAIN_POST_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ctryCd, (String) configCmptMap.get("MAIN_CTRY_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachFlNm, (String) configCmptMap.get("MAIN_SVC_MACH_FL_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.iwrCondCd, (String) configCmptMap.get("MAIN_IWR_COND_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.effFromDt, (String) configCmptMap.get("MAIN_EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachUsgStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_USG_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.prntSerNum, (String) configCmptMap.get("MAIN_PRNT_SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachTrxTpCd, (String) configCmptMap.get("MAIN_SVC_MACH_TRX_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachQty, (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_QTY"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ownrAcctNum, (String) configCmptMap.get("MAIN_OWNR_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ownrLocNum, (String) configCmptMap.get("MAIN_OWNR_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.billToAcctNum, (String) configCmptMap.get("MAIN_BILL_TO_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.billToLocNum, (String) configCmptMap.get("MAIN_BILL_TO_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.curLocAcctNum, (String) configCmptMap.get("MAIN_CUR_LOC_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.curLocNum, (String) configCmptMap.get("MAIN_CUR_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrLocStsCd, (String) configCmptMap.get("MAIN_MACH_LOC_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.MACHINE);

                    } else {
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                    }
                    // Component (include main machine)
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, cmptSvcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, (String) configCmptMap.get("MAIN_MDSE_CD"));
                    cmptIndex++;
                    machMstrPMsgForAttachConfig.xxCmptMachList.setValidCount(cmptIndex);
                }
                machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);

            } else {
                // ******************************************************
                // * Config Master Exist
                // ******************************************************
                for (Map<String, Object> configCmptMap : configCmptMapList) {

                    BigDecimal svcConfigMstrPk = (BigDecimal) configCmptMap.get("SVC_CONFIG_MSTR_PK");
                    BigDecimal mainSvcMachMstrPk = (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_MSTR_PK");
                    BigDecimal cmptSvcMachMstrPk = (BigDecimal) configCmptMap.get("CMPT_SVC_MACH_MSTR_PK");

                    if (preSvcConfigMstrPk == null || !svcConfigMstrPk.equals(preSvcConfigMstrPk)) {

                        if (preSvcConfigMstrPk != null) {

                            machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);
                            machMstrPMsgForAttachConfig = new NSZC001001PMsg();
                        }

                        preSvcConfigMstrPk = svcConfigMstrPk;
                        cmptIndex = 0;

                        // Main Machine
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.slsDt, slsDt);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcConfigMstrPk, svcConfigMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrPk, mainSvcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.mdseCd, (String) configCmptMap.get("MAIN_MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_MSTR_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.locNm, (String) configCmptMap.get("MAIN_LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.addlLocNm, (String) configCmptMap.get("MAIN_ADDL_LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.firstLineAddr, (String) configCmptMap.get("MAIN_FIRST_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.scdLineAddr, (String) configCmptMap.get("MAIN_SCD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.thirdLineAddr, (String) configCmptMap.get("MAIN_THIRD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.frthLineAddr, (String) configCmptMap.get("MAIN_FRTH_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ctyAddr, (String) configCmptMap.get("MAIN_CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.stCd, (String) configCmptMap.get("MAIN_ST_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.provNm, (String) configCmptMap.get("MAIN_PROV_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.cntyNm, (String) configCmptMap.get("MAIN_CNTY_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.postCd, (String) configCmptMap.get("MAIN_POST_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ctryCd, (String) configCmptMap.get("MAIN_CTRY_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachFlNm, (String) configCmptMap.get("MAIN_SVC_MACH_FL_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.iwrCondCd, (String) configCmptMap.get("MAIN_IWR_COND_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.effFromDt, (String) configCmptMap.get("MAIN_EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachUsgStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_USG_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.prntSerNum, (String) configCmptMap.get("MAIN_PRNT_SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachTrxTpCd, (String) configCmptMap.get("MAIN_SVC_MACH_TRX_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachQty, (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_QTY"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ownrAcctNum, (String) configCmptMap.get("MAIN_OWNR_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ownrLocNum, (String) configCmptMap.get("MAIN_OWNR_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.billToAcctNum, (String) configCmptMap.get("MAIN_BILL_TO_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.billToLocNum, (String) configCmptMap.get("MAIN_BILL_TO_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.curLocAcctNum, (String) configCmptMap.get("MAIN_CUR_LOC_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.curLocNum, (String) configCmptMap.get("MAIN_CUR_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.MACHINE);

                    } else {
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                    }

                    // Component (include main machine)
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, cmptSvcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, (String) configCmptMap.get("CMPT_MDSE_CD"));
                    //ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, (String) configCmptMap.get("CMPT_SVC_MACH_TP_CD"));

                    cmptIndex++;
                    machMstrPMsgForAttachConfig.xxCmptMachList.setValidCount(cmptIndex);
                }
                //machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);
                // Add
                // SvcMachMaster in ConfigMaster
                List<Map<String, Object>> svcMachMstrMapList = getSvcMachList(glblCmpyCd, attachCmptMachPkList);
                for (Map<String, Object> svcMachMstrMap : svcMachMstrMapList) {

                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, (BigDecimal) svcMachMstrMap.get(KEY_MAIN_SVC_MACH_MSTR_PK));
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, (String) svcMachMstrMap.get(KEY_MAIN_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                    cmptIndex++;
                    machMstrPMsgForAttachConfig.xxCmptMachList.setValidCount(cmptIndex);
                    //machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);
                }
                machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);
            }
        }
        return machMstrPMsgForAttachConfigList;
    }
    // QC#53385 Add Start
    /**
     * addMachMstrPMsgForAttachConfig4CC
     * @param glblCmpyCd
     * @param slsDt
     * @param attachConfigList
     * @param attachCmptMachMap
     * @return
     */
    private List<NSZC001001PMsg> addMachMstrPMsgForAttachConfig4CC(String glblCmpyCd, String slsDt, List<BigDecimal> attachConfigList, Map<BigDecimal, List<BigDecimal>> attachCmptMachMap, Map<String, Object> rwsPutAwayWrkMap) {

        List<NSZC001001PMsg> machMstrPMsgForAttachConfigList = new ArrayList<NSZC001001PMsg>();
        NSZC001001PMsg machMstrPMsgForAttachConfig = new NSZC001001PMsg();

        // Config Master PK Loop
        for (BigDecimal attachConfigPk : attachConfigList) {

            List<BigDecimal> attachCmptMachPkList = attachCmptMachMap.get(attachConfigPk);

            if (attachCmptMachPkList == null || attachCmptMachPkList.isEmpty()) {

                continue;
            }

            // SvcMachMaster in ConfigMaster
            List<Map<String, Object>> configCmptMapList = getConfigCmptListForAddConfig(glblCmpyCd, attachConfigPk);
            BigDecimal preSvcConfigMstrPk = null;
            int cmptIndex = 0;

            // Config Component Information is not found.
            if (configCmptMapList == null || configCmptMapList.isEmpty()) {
                // ******************************************************
                // * Create From Machine Master (Config Master Not Exist)
                // ******************************************************
                configCmptMapList = getSvcMachList(glblCmpyCd, attachCmptMachPkList);

                machMstrPMsgForAttachConfig = new NSZC001001PMsg();

                BigDecimal svcConfigMstrPk = attachConfigPk;
                BigDecimal mainSvcMachMstrPk = attachCmptMachPkList.get(0);
                if (configCmptMapList == null || configCmptMapList.isEmpty()) {
                    mainSvcMachMstrPk = null;

                } else {
                    mainSvcMachMstrPk = attachCmptMachPkList.get(0);
                }
                // Main Machine
                for (Map<String, Object> configCmptMap : configCmptMapList) {
                    BigDecimal cmptSvcMachMstrPk = (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_MSTR_PK");
                    if (preSvcConfigMstrPk == null || !svcConfigMstrPk.equals(preSvcConfigMstrPk)) {

                        if (preSvcConfigMstrPk != null) {
                            machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);
                            machMstrPMsgForAttachConfig = new NSZC001001PMsg();
                        }

                        preSvcConfigMstrPk = svcConfigMstrPk;
                        cmptIndex = 0;
                        // Main Machine
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.slsDt, slsDt);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcConfigMstrPk, svcConfigMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrPk, mainSvcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.serNum, (String) configCmptMap.get("SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachTpCd, SVC_MACH_TP.MACHINE);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.mdseCd, (String) configCmptMap.get("MAIN_MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_MSTR_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.locNm, (String) configCmptMap.get("MAIN_LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.addlLocNm, (String) configCmptMap.get("MAIN_ADDL_LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.firstLineAddr, (String) configCmptMap.get("MAIN_FIRST_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.scdLineAddr, (String) configCmptMap.get("MAIN_SCD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.thirdLineAddr, (String) configCmptMap.get("MAIN_THIRD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.frthLineAddr, (String) configCmptMap.get("MAIN_FRTH_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ctyAddr, (String) configCmptMap.get("MAIN_CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.stCd, (String) configCmptMap.get("MAIN_ST_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.provNm, (String) configCmptMap.get("MAIN_PROV_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.cntyNm, (String) configCmptMap.get("MAIN_CNTY_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.postCd, (String) configCmptMap.get("MAIN_POST_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ctryCd, (String) configCmptMap.get("MAIN_CTRY_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachFlNm, (String) configCmptMap.get("MAIN_SVC_MACH_FL_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.iwrCondCd, (String) configCmptMap.get("MAIN_IWR_COND_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.effFromDt, (String) configCmptMap.get("MAIN_EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachUsgStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_USG_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.prntSerNum, (String) configCmptMap.get("MAIN_PRNT_SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachTrxTpCd, (String) configCmptMap.get("MAIN_SVC_MACH_TRX_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachQty, (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_QTY"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ownrAcctNum, (String) configCmptMap.get("MAIN_OWNR_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ownrLocNum, (String) configCmptMap.get("MAIN_OWNR_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.billToAcctNum, (String) configCmptMap.get("MAIN_BILL_TO_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.billToLocNum, (String) configCmptMap.get("MAIN_BILL_TO_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.curLocAcctNum, (String) configCmptMap.get("MAIN_CUR_LOC_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.curLocNum, (String) configCmptMap.get("MAIN_CUR_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrLocStsCd, (String) configCmptMap.get("MAIN_MACH_LOC_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.MACHINE);

                    } else {
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                    }
                    // Component (include main machine)
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, cmptSvcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, (String) configCmptMap.get("MAIN_MDSE_CD"));
                    cmptIndex++;

                    String trxOrdNum = (String) rwsPutAwayWrkMap.get("TRX_ORD_NUM");
                    if (ZYPCommonFunc.hasValue(trxOrdNum)) {
                        if (isMainMachRmv(glblCmpyCd,trxOrdNum,mainSvcMachMstrPk)) {
                        }
                    }

                    // QC#56610
                    List<Map<String, Object>> soSerList = getSoSer(glblCmpyCd, trxOrdNum);
                    for (Map<String, Object> soSer : soSerList) {
                        String serMdseCd = (String) soSer.get("MDSE_CD");
                        BigDecimal serSvcMachMstrPk = (BigDecimal) soSer.get("SVC_MACH_MSTR_PK");
                        if (!ZYPCommonFunc.hasValue(serSvcMachMstrPk) || serSvcMachMstrPk.compareTo(mainSvcMachMstrPk) == 0) {
                            continue;
                        }
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, serSvcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, serMdseCd);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                        cmptIndex++;
                    }
                    // QC#56610. Non-Serial
                    List<Map<String, Object>> soNonSerList = getSoNonSerialList(glblCmpyCd, trxOrdNum);
                    List<BigDecimal> svcMachMstrPKList = new ArrayList<BigDecimal>();
                    for (Map<String, Object> nonSer : soNonSerList) {
                        String curLocNum = (String) nonSer.get("INVTY_LOC_CD");
                        String stkStsCd = (String) nonSer.get("FROM_STK_STS_CD");
                        String mdseCd = (String) nonSer.get("MDSE_CD");

                        BigDecimal svcMachMstrPk = getNonSerialSMMPK(glblCmpyCd, curLocNum, stkStsCd, mdseCd, svcMachMstrPKList);

                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, svcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, mdseCd);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                        cmptIndex++;
                        svcMachMstrPKList.add(svcMachMstrPk);
                    }

                    machMstrPMsgForAttachConfig.xxCmptMachList.setValidCount(cmptIndex);

                }
                machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);

            } else {
                // ******************************************************
                // * Config Master Exist
                // ******************************************************
                // QC#54249 Mod
            	BigDecimal mainSvcMachMstrPk = null;

                for (Map<String, Object> configCmptMap : configCmptMapList) {

                    BigDecimal svcConfigMstrPk = (BigDecimal) configCmptMap.get("SVC_CONFIG_MSTR_PK");
                    mainSvcMachMstrPk = (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_MSTR_PK");
                    BigDecimal cmptSvcMachMstrPk = (BigDecimal) configCmptMap.get("CMPT_SVC_MACH_MSTR_PK");
                    if (preSvcConfigMstrPk == null || !svcConfigMstrPk.equals(preSvcConfigMstrPk)) {

                        if (preSvcConfigMstrPk != null) {

                            machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);
                            machMstrPMsgForAttachConfig = new NSZC001001PMsg();
                        }

                        preSvcConfigMstrPk = svcConfigMstrPk;
                        cmptIndex = 0;

                        // Main Machine
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.slsDt, slsDt);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcConfigMstrPk, svcConfigMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrPk, mainSvcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.mdseCd, (String) configCmptMap.get("MAIN_MDSE_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachMstrStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_MSTR_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.locNm, (String) configCmptMap.get("MAIN_LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.addlLocNm, (String) configCmptMap.get("MAIN_ADDL_LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.firstLineAddr, (String) configCmptMap.get("MAIN_FIRST_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.scdLineAddr, (String) configCmptMap.get("MAIN_SCD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.thirdLineAddr, (String) configCmptMap.get("MAIN_THIRD_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.frthLineAddr, (String) configCmptMap.get("MAIN_FRTH_LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ctyAddr, (String) configCmptMap.get("MAIN_CTY_ADDR"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.stCd, (String) configCmptMap.get("MAIN_ST_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.provNm, (String) configCmptMap.get("MAIN_PROV_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.cntyNm, (String) configCmptMap.get("MAIN_CNTY_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.postCd, (String) configCmptMap.get("MAIN_POST_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ctryCd, (String) configCmptMap.get("MAIN_CTRY_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachFlNm, (String) configCmptMap.get("MAIN_SVC_MACH_FL_NM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.iwrCondCd, (String) configCmptMap.get("MAIN_IWR_COND_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.effFromDt, (String) configCmptMap.get("MAIN_EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachUsgStsCd, (String) configCmptMap.get("MAIN_SVC_MACH_USG_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.prntSerNum, (String) configCmptMap.get("MAIN_PRNT_SER_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachTrxTpCd, (String) configCmptMap.get("MAIN_SVC_MACH_TRX_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.svcMachQty, (BigDecimal) configCmptMap.get("MAIN_SVC_MACH_QTY"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ownrAcctNum, (String) configCmptMap.get("MAIN_OWNR_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.ownrLocNum, (String) configCmptMap.get("MAIN_OWNR_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.billToAcctNum, (String) configCmptMap.get("MAIN_BILL_TO_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.billToLocNum, (String) configCmptMap.get("MAIN_BILL_TO_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.curLocAcctNum, (String) configCmptMap.get("MAIN_CUR_LOC_ACCT_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.curLocNum, (String) configCmptMap.get("MAIN_CUR_LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.MACHINE);

                    } else {
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                    }

                    // Component (include main machine)
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, cmptSvcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, (String) configCmptMap.get("CMPT_MDSE_CD"));

                    cmptIndex++;
                    machMstrPMsgForAttachConfig.xxCmptMachList.setValidCount(cmptIndex);
                }
                // QC#54249 Mod Start
                String trxOrdNum = (String) rwsPutAwayWrkMap.get("TRX_ORD_NUM");
                if (ZYPCommonFunc.hasValue(trxOrdNum)) {
                    // QC#56610
                    List<Map<String, Object>> soSerList = getSoSer(glblCmpyCd, trxOrdNum);
                    for (Map<String, Object> soSer : soSerList) {
                        String serMdseCd = (String) soSer.get("MDSE_CD");
                        BigDecimal serSvcMachMstrPk = (BigDecimal) soSer.get("SVC_MACH_MSTR_PK");
                        if (!ZYPCommonFunc.hasValue(serSvcMachMstrPk) || serSvcMachMstrPk.compareTo(mainSvcMachMstrPk) == 0) {
                            continue;
                        }
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, serSvcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, serMdseCd);
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                        cmptIndex++;
                        machMstrPMsgForAttachConfig.xxCmptMachList.setValidCount(cmptIndex);
                    }
                } else {
                    List<Map<String, Object>> svcMachMstrMapList = getSvcMachList(glblCmpyCd, attachCmptMachPkList);
                    for (Map<String, Object> svcMachMstrMap : svcMachMstrMapList) {
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, (BigDecimal) svcMachMstrMap.get(KEY_MAIN_SVC_MACH_MSTR_PK));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, (String) svcMachMstrMap.get(KEY_MAIN_MDSE_CD));
                        ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                        cmptIndex++;
                        machMstrPMsgForAttachConfig.xxCmptMachList.setValidCount(cmptIndex);
                    }
                }
                // For NonSerial
                // QC#56610
                BigDecimal soMdlId = (BigDecimal) rwsPutAwayWrkMap.get("SO_MDL_ID");
                if (ZYPCommonFunc.hasValue(soMdlId)) {
                    List<Map<String, Object>> svcMachMstrMapList = getSvcMachList(glblCmpyCd, attachCmptMachPkList);
                    for (Map<String, Object> svcMachMstrMap : svcMachMstrMapList) {
                        String serNum = (String) svcMachMstrMap.get("SER_NUM");
                        if (!ZYPCommonFunc.hasValue(serNum)) {
                            // NonSerial
                            ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachMstrPk, (BigDecimal) svcMachMstrMap.get(KEY_MAIN_SVC_MACH_MSTR_PK));
                            ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).mdseCd, (String) svcMachMstrMap.get(KEY_MAIN_MDSE_CD));
                            ZYPEZDItemValueSetter.setValue(machMstrPMsgForAttachConfig.xxCmptMachList.no(cmptIndex).svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                            cmptIndex++;
                            machMstrPMsgForAttachConfig.xxCmptMachList.setValidCount(cmptIndex);
                        }
                    }
                }
                // QC#54249 Mod End
                machMstrPMsgForAttachConfigList.add(machMstrPMsgForAttachConfig);
            }
        }
        return machMstrPMsgForAttachConfigList;
    }
    // QC#53385 Add End

    /**
     * createAssetStagePMsg (Return)
     * @param glblCmpyCd String
     * @param slsDt String
     * @param rwsPutAwayWrkMap Map<String, Object>
     * @param svcMachMstrPk BigDecimal
     * @return NLZC309001PMsg
     */
    private NLZC309001PMsg createAssetStagePMsgForRt(String glblCmpyCd, String slsDt, Map<String, Object> rwsPutAwayWrkMap, BigDecimal svcMachMstrPk) {

        NLZC309001PMsg assetStgnApiPMsg = new NLZC309001PMsg();
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.procModeCd, NLZC309001Constant.PROC_MODE_RETURN_TANGIBLE);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.mdseCd, (String) rwsPutAwayWrkMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.rtrnWhCd, (String) rwsPutAwayWrkMap.get("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.cpoOrdNum, (String) rwsPutAwayWrkMap.get("TRX_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.cpoDtlLineNum, (String) rwsPutAwayWrkMap.get("TRX_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.cpoDtlLineSubNum, (String) rwsPutAwayWrkMap.get("TRX_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.dsOrdPosnNum, (String) rwsPutAwayWrkMap.get("DS_ORD_POSN_NUM"));

        return assetStgnApiPMsg;
    }

    /**
     * createAssetStagePMsg (Config Change)
     * @param glblCmpyCd String
     * @param slsDt String
     * @param rwsPutAwayWrkMap Map<String, Object>
     * @param svcMachMstrPk BigDecimal
     * @param serNum String
     * @return NLZC309001PMsg
     */
    private NLZC309001PMsg createAssetStagePMsgForCc(String glblCmpyCd, String slsDt, Map<String, Object> rwsPutAwayWrkMap, BigDecimal svcMachMstrPk, String serNum) {

        NLZC309001PMsg assetStgnApiPMsg = new NLZC309001PMsg();
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.slsDt,  slsDt);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.procModeCd, NLZC309001Constant.PROC_MODE_CONFIGRATION_CHANGE);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.mdseCd, (String) rwsPutAwayWrkMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.fromSvcConfigMstrPk, (BigDecimal) rwsPutAwayWrkMap.get(KEY_PICK_SVC_CONFIG_MSTR_PK));

        if (!ZYPConstant.FLG_ON_Y.equals((String) rwsPutAwayWrkMap.get(KEY_RMV_CONFIG_FLG))) {

            ZYPEZDItemValueSetter.setValue(assetStgnApiPMsg.toSvcConfigMstrPk, (BigDecimal) rwsPutAwayWrkMap.get(KEY_SVC_CONFIG_MSTR_PK));
        }

        return assetStgnApiPMsg;
    }

    /**
     * setSvcMachMstrTMsg
     * @param svcMachMstrMap Map<String, Object>
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg setSvcMachMstrTMsg(Map<String, Object> svcMachMstrMap) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.mdseCd, (String) svcMachMstrMap.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.serNum, (String) svcMachMstrMap.get("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcConfigMstrPk, (BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrStsCd, (String) svcMachMstrMap.get("SVC_MACH_MSTR_STS_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.stkStsCd, (String) svcMachMstrMap.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachUsgStsCd, (String) svcMachMstrMap.get("SVC_MACH_USG_STS_CD"));

        return svcMachMstrTMsg;
    }

    /**
     * @param sceOrdTpCd
     * @return boolean
     */
    private boolean isMachMstrLocWh(String sceOrdTpCd) {
        // QC#22231 Start
        if (SCE_ORD_TP.DOMESTIC_CANON_GROUP.equals(sceOrdTpCd) || SCE_ORD_TP.DOMESTIC_PO_EXISTS.equals(sceOrdTpCd) || SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY.equals(sceOrdTpCd) || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd)
                || SCE_ORD_TP.RETURN.equals(sceOrdTpCd) || SCE_ORD_TP.KITTING.equals(sceOrdTpCd)) {

            return false;
        }
        // QC#22231 End

        return true;
    }

    /**
     * isTargetDisposal
     * @param sceOrdTpCd String
     * @return boolean
     */
    private boolean isTargetDisposal(String sceOrdTpCd) {

// 2017/10/13 MOD T.Hakodate QC#21857 START
        if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd)//
                || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd) //
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) //
                || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {//
// 2017/10/13 MOD T.Hakodate QC#21857 END
            return true;
        }

        return false;
    }

    /**
     * isServiceExchange
     * @param glblCmpyCd
     * @param rwsNum
     * @param sceOrdTpCd
     * @return
     */
    private boolean isServiceExchange(String glblCmpyCd, String rwsNum, String trxOrdNum) {
        boolean isSvcExchg = false;

        if (svcExchgCatgList != null && !svcExchgCatgList.isEmpty()) {
            if (svcExchgRwsNumSet.contains(rwsNum)) {

                isSvcExchg = true;

            } else if (!nonSvcExchgRwsNumSet.contains(rwsNum)) {

                CPOTMsg cpoTMsg = new CPOTMsg();
                ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
                // START 2017/12/04 J.Kim [QC#18127,MOD]
                // ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, rwsNum);
                ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, trxOrdNum);
                // END 2017/12/04 J.Kim [QC#18127,MOD]
                cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpoTMsg);

                if (cpoTMsg != null && svcExchgCatgList.contains(cpoTMsg.dsOrdCatgCd.getValue())) {
                    isSvcExchg = true;
                    svcExchgRwsNumSet.add(rwsNum);
                } else {
                    nonSvcExchgRwsNumSet.add(rwsNum);
                }
            }
        }
        return isSvcExchg;
    }

    /**
     * setOutputMsg
     * @param pMsg
     * @param rwsPutAwayWrkMap
     */
    private void setOutputMsg(NLZC407001PMsg pMsg, Map<String, Object> rwsPutAwayWrkMap, BigDecimal svcMachMstrPk) {
        int index = pMsg.outputList.getValidCount();

        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).xxNum, new BigDecimal(index));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).wrkTrxId, (String) rwsPutAwayWrkMap.get(KEY_WRK_TRX_ID));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).sqId, (String) rwsPutAwayWrkMap.get(KEY_SQ_ID));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).rwsDtlLineNum, (String) rwsPutAwayWrkMap.get(KEY_RWS_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).sceOrdTpCd, (String) rwsPutAwayWrkMap.get(KEY_SCE_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).invtyStkStsCd, (String) rwsPutAwayWrkMap.get(KEY_INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).mdseCd, (String) rwsPutAwayWrkMap.get(KEY_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).rwsStkDtTmTs, (String) rwsPutAwayWrkMap.get(KEY_RWS_STK_DT_TM_TS));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).rwsStkQty, (BigDecimal) rwsPutAwayWrkMap.get(KEY_RWS_STK_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).poRcvTrxHdrNum, (String) rwsPutAwayWrkMap.get(KEY_PO_RCV_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).instlBaseCtrlFlg, (String) rwsPutAwayWrkMap.get(KEY_INSTL_BASE_CTRL_FLG));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).serNumTakeFlg, (String) rwsPutAwayWrkMap.get(KEY_SER_NUM_TAKE_FLG));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).rcvFlg, (String) rwsPutAwayWrkMap.get(KEY_CMPY_INVTY_FLG));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).invtyLocCd, (String) rwsPutAwayWrkMap.get(KEY_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).rwsRefNum, (String) rwsPutAwayWrkMap.get(KEY_RWS_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).shipFromInvtyLocCd, (String) rwsPutAwayWrkMap.get(KEY_SHIP_FROM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).trxHdrNum_SO, (String) rwsPutAwayWrkMap.get(KEY_SO_TRX_HDR_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).trxOrdNum, (String) rwsPutAwayWrkMap.get(KEY_TRX_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).svcConfigMstrPk, (BigDecimal) rwsPutAwayWrkMap.get(KEY_SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).rwsNum, (String) rwsPutAwayWrkMap.get(KEY_RWS_NUM));
        ZYPEZDItemValueSetter.setValue(pMsg.outputList.no(index).svcMachMstrPk, svcMachMstrPk);

        pMsg.outputList.setValidCount(index + 1);
    }

    /**
     * getSvcMachMstrModeCdList
     * @param glblCmpyCd String
     * @param rwsPutAwayWrkMap Map<String, Object>
     * @param rwsPutAwaySerMap Map<String, Object>
     * @return NLXC042001SerialInfo
     * @throws SuspendException
     */
    private NLXC042001SerialInfo getSvcMachMstrModeCdList(String glblCmpyCd, Map<String, Object> rwsPutAwayWrkMap, Map<String, Object> rwsPutAwaySerMap) throws SuspendException {

        NLXC042001SerialInfo serialInfo = new NLXC042001SerialInfo();
        serialInfo.setGlblCmpyCd(glblCmpyCd);
        serialInfo.setDupChkCd(ZYPCodeDataUtil.getVarCharConstValue("SER_DUP_CHK_CD", glblCmpyCd));
        serialInfo.setSceOrdTpCd((String) rwsPutAwayWrkMap.get(KEY_SCE_ORD_TP_CD));
        String sceOrdTpCd = (String) rwsPutAwayWrkMap.get(KEY_SCE_ORD_TP_CD);

        if (rwsPutAwaySerMap != null) {

            serialInfo.setMdseCd((String) rwsPutAwaySerMap.get(KEY_MDSE_CD));
            serialInfo.setSerNum((String) rwsPutAwaySerMap.get(KEY_SER_NUM));

        } else {

            serialInfo.setMdseCd((String) rwsPutAwayWrkMap.get(KEY_MDSE_CD));
        }

        /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 START */
        // serialInfo.setShipFromLocCd((String) rwsPutAwayWrkMap.get(KEY_FROM_LOC_CD));
        serialInfo.setShipFromLocCd((String) rwsPutAwayWrkMap.get(KEY_SHIP_FROM_INVTY_LOC_CD));
        /* 07/07/2017 CSAI Y.Imazu Mod QC#19730 END */
        serialInfo.setRcvRtlWhCd((String) rwsPutAwayWrkMap.get(KEY_RTL_WH_CD));
        serialInfo.setTrxHdrNum((String) rwsPutAwayWrkMap.get(KEY_SER_ALLOC_TRX_HDR_NUM));
        serialInfo.setRwsNum((String) rwsPutAwayWrkMap.get(KEY_RWS_NUM));

        if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

            serialInfo.setOrdSvcConfigMstrPk((BigDecimal) rwsPutAwayWrkMap.get(KEY_PICK_SVC_CONFIG_MSTR_PK));

        } else {

            serialInfo.setOrdSvcConfigMstrPk((BigDecimal) rwsPutAwayWrkMap.get(ORD_SVC_CONFIG_MSTR_PK));
        }

        serialInfo.setMdlId((BigDecimal) rwsPutAwayWrkMap.get(ORD_MDL_ID));
        /* 07/07/2017 CSAI Y.Imazu Add QC#19730 START */
        serialInfo.setOnBatchType(this.onBatchType);
        /* 07/07/2017 CSAI Y.Imazu Add QC#19730 END */

        if (SCE_ORD_TP.SUB_WH_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {

            serialInfo.setSoNum((String) rwsPutAwayWrkMap.get(KEY_RWS_REF_NUM));
            serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForShipSerial(serialInfo);

        } else {

            serialInfo = NLXC042001SvcMachMstrCheck.chkSvcMachMstrForRcvSerial(serialInfo);
        }

        if (ZYPCommonFunc.hasValue(serialInfo.getErrMsgId())) {

            setMessageID(serialInfo.getErrMsgId());
        }

        return serialInfo;
    }

    /**
     * setMessageID
     * @param msgId
     * @throws SuspendException
     */
    private void setMessageID(String msgId) throws SuspendException {

        msgMap.addXxMsgId(msgId);

        if (msgId.endsWith(MSG_KIND_WARNING)) {

            if (ZYPConstant.FLG_OFF_N.equals(warningSkipFlg)) {

                throw new SuspendException();
            }

        } else if (msgId.endsWith(MSG_KIND_ERROR)) {

            throw new SuspendException();
        }
    }

    /**
     * setMessageID
     * @param msgId
     * @throws SuspendException
     */
    private void setMessageID(List<S21ApiMessage> msgList) throws SuspendException {

        boolean errFlg = false;

        for (int i = 0; i < msgList.size(); i++) {

            S21ApiMessage msg = msgList.get(i);
            String msgId = msg.getXxMsgid();

            msgMap.addXxMsgId(msgId);

            if (msgId.endsWith(MSG_KIND_WARNING)) {

                if (ZYPConstant.FLG_OFF_N.equals(warningSkipFlg)) {

                    errFlg = true;
                }

            } else if (msgId.endsWith(MSG_KIND_ERROR)) {

                errFlg = true;
            }
        }

        if (errFlg) {

            throw new SuspendException();
        }
    }

    /**
     * getRwsPutAwaySerList
     * @param pMsg NLZC407001PMsg
     * @param index int
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getRwsPutAwaySerList(String glblCmpyCd, String wrkTrxId, String sqId) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("wrkTrxId", wrkTrxId);
        ssmParam.put("sqId", sqId);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getRwsPutAwaySerList", ssmParam);
    }

    /**
     * getIbListForNonSerial
     * @param glblCmpyCd String
     * @param sceOrdTpCd String
     * @param rwsPutAwayWrkMap Map<String, Object>
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getIbListForNonSerial(String glblCmpyCd, String sceOrdTpCd, Map<String, Object> rwsPutAwayWrkMap) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", (String) rwsPutAwayWrkMap.get("SER_ALLOC_TRX_HDR_NUM"));

        if (SCE_ORD_TP.RETURN.equals(sceOrdTpCd)) {

            ssmParam.put("trxLineNum", (String) rwsPutAwayWrkMap.get("TRX_LINE_NUM"));
            ssmParam.put("trxLineSubNum", (String) rwsPutAwayWrkMap.get("TRX_LINE_SUB_NUM"));

        } else if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {

            ssmParam.put("trxLineNum", (String) rwsPutAwayWrkMap.get("SO_TRX_LINE_NUM"));
            ssmParam.put("trxLineSubNum", (String) rwsPutAwayWrkMap.get("SO_TRX_LINE_SUB_NUM"));

        } else if (SCE_ORD_TP.DOMESTIC_CANON_GROUP.equals(sceOrdTpCd) || SCE_ORD_TP.DOMESTIC_PO_EXISTS.equals(sceOrdTpCd) || SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY.equals(sceOrdTpCd)
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) || SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd) || SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(sceOrdTpCd)) {

            ssmParam.put("trxLineNum", (String) rwsPutAwayWrkMap.get("PO_RCV_TRX_LINE_NUM"));

        } else if (SCE_ORD_TP.SUB_WH_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd) || SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(sceOrdTpCd)
                || SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(sceOrdTpCd) || SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(sceOrdTpCd)) {

            ssmParam.put("trxLineNum", (String) rwsPutAwayWrkMap.get("SD_TRX_LINE_NUM"));

        } else if (!SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)){

            ssmParam.put("trxLineNum", (String) rwsPutAwayWrkMap.get("SO_TRX_LINE_NUM"));
        }

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getIbList", ssmParam);
    }

    /**
     * getNonAllocMachMstrNonSer
     * @param pMsg NLZC407001PMsg
     * @param rwsPutAwayWrkMap Map<String, Object>
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getNonAllocMachMstrNonSer(NLZC407001PMsg pMsg, Map<String, Object> rwsPutAwayWrkMap) {

        String sceOrdTpCd = (String) rwsPutAwayWrkMap.get(KEY_SCE_ORD_TP_CD);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("mdseCd", (String) rwsPutAwayWrkMap.get(KEY_MDSE_CD));
        params.put("curlocNum", (String) rwsPutAwayWrkMap.get(KEY_SHIP_FROM_INVTY_LOC_CD));
        params.put("svcMachMstrStsTrmn", SVC_MACH_MSTR_STS.TERMINATED);
        params.put("svcMachMstrStsDup", SVC_MACH_MSTR_STS.DUPLICATE);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        if (SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {
            params.put("pickSvcConfigMstrPk", (BigDecimal) rwsPutAwayWrkMap.get(KEY_PICK_SVC_CONFIG_MSTR_PK));
        }
        //QC#27036 ADD START
        params.put("stkStsCd", (String) rwsPutAwayWrkMap.get(KEY_INVTY_STK_STS_CD));
        //QC#27036 ADD END
        return (List<Map<String, Object>>) ssmClient.queryObjectList("getNonAllocMachMstrNonSer", params);
    }

    /**
     * getNonAllocMachMstrWHT
     * @param pMsg getNonAllocMachMstrWHT
     * @param rwsPutAwayWrkMap Map<String, Object>
     * @param stkStsCd String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getNonAllocMachMstrWHT(NLZC407001PMsg pMsg, Map<String, Object> rwsPutAwayWrkMap, String stkStsCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("svcConfigMstrPk", (BigDecimal) rwsPutAwayWrkMap.get(KEY_SVC_CONFIG_MSTR_PK));
        params.put("mdseCd", (String) rwsPutAwayWrkMap.get(KEY_MDSE_CD));
        params.put("curlocNum", (String) rwsPutAwayWrkMap.get(KEY_INVTY_LOC_CD));
        params.put("svcMachMstrLocStsCd", LOC_STS.IN_TRANSIT_WH);
        params.put("stkStsCd", stkStsCd);
        params.put("svcMachMstrStsTrmn", SVC_MACH_MSTR_STS.TERMINATED);
        params.put("svcMachMstrStsDup", SVC_MACH_MSTR_STS.DUPLICATE);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getNonAllocMachMstrWHT", params);
    }

    /**
     * getRwsStkStsCd
     * @param pMsg NLZC407001PMsg
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @return String
     */
    public String getRwsStkStsCd(NLZC407001PMsg pMsg, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("rwsNum", rwsNum);
        params.put("rwsDtlLineNum", rwsDtlLineNum);

        return (String) ssmClient.queryObject("getRwsStkStsCd", params);
    }

    /**
     * getConfigCmptList
     * @param glblCmpyCd String
     * @param detachCmptMachList List<BigDecimal>
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getConfigCmptList(String glblCmpyCd, List<BigDecimal> detachCmptMachList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("detachCmptMachList", detachCmptMachList);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getConfigCmptList", params);
    }

    /**
     * getConfigCmptList
     * @param glblCmpyCd String
     * @param detachCmptMachList List<BigDecimal>
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getConfigCmptListForAddConfig(String glblCmpyCd, BigDecimal svcConfigMsgrPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcConfigMsgrPk", svcConfigMsgrPk);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getConfigCmptListForAddConfig", params);
    }

    /**
     * getConfigCmptList
     * @param glblCmpyCd String
     * @param detachCmptMachList List<BigDecimal>
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getSvcMachList(String glblCmpyCd, List<BigDecimal> attachCmptMachList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("attachCmptMachList", attachCmptMachList);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getSvcMachList", params);
    }

    /**
     * getSvcExchgCatgList
     * @param glblCmpyCd String
     * @return List<String>
     */
    public List<String> getSvcExchgCatgList(String glblCmpyCd) {

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", "SERVICE_EXCHANGE");

        return (List<String>) ssmClient.queryObjectList("getSvcExchgCatgList", params);
    }

    /**
     * getRtrnSrcOrder
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @return Map<String, String>
     */
    private Map<String, String> getRtrnSrcOrder(String glblCmpyCd, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("rwsDtlLineNum", rwsDtlLineNum);

        return (Map<String, String>) ssmClient.queryObject("getRtrnSrcOrder", params);
    }

    /**
     * getMachMstrOriginalItem
     * @param glblCmpyCd String
     * @param poRcvTrxNum String
     * @param poRcvTrxDtlNum String
     * @param ssmId
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getMachMstrOriginalItem(String glblCmpyCd, String poRcvTrxNum, String poRcvTrxDtlNum, String ssmId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poRcvTrxNum", poRcvTrxNum);
        params.put("poRcvTrxLineNum", poRcvTrxDtlNum);
        params.put("prntLineNum", "001");

        return (List<Map<String, Object>>) ssmClient.queryObjectList(ssmId, params);
    }

    /**
     * getTargetRwsCompletion
     * @param glblCmpyCd String
     * @param rwsNum String
     * @return Map<String, String>
     */
    private Map<String, String> getTargetRwsCompletion(String glblCmpyCd, String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);

        return (Map<String, String>) ssmClient.queryObject("getTargetRwsCompletion", params);
    }

    /**
     * getMachMstrNotRecieved
     * @param glblCmpyCd
     * @param rwsNum
     * @param sceOrdTpCd
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getMachMstrNotRecieved(String glblCmpyCd, String rwsNum, String sceOrdTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("instlBaseCtrlFlg", ZYPConstant.FLG_ON_Y);

        String ssmId = "getMachMstrNotRecievedForRT";

        if (SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {

            ssmId = "getMachMstrNotRecievedForDT";

        } else if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

            ssmId = "getMachMstrNotRecievedForTR";
        }

        // QC#55273 Add Start
        if ("getMachMstrNotRecievedForRT".equals(ssmId)) {

            params.put("svcMachMstrStsCd", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);

            // get ConfigPk
            RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(rwsHdrT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsHdrT.rwsNum, rwsNum);
            rwsHdrT = (RWS_HDRTMsg) EZDTBLAccessor.findByKey(rwsHdrT);
            if (ZYPCommonFunc.hasValue(rwsHdrT.svcConfigMstrPk)) {
                params.put("svcConfigMstrPk", rwsHdrT.svcConfigMstrPk.getValue());
            }
        }
        // QC#55273 Add End

        return (List<Map<String, Object>>) ssmClient.queryObjectList(ssmId, params);
    }

    /**
     * getMachMstrNotRecieved
     * @param glblCmpyCd
     * @param rwsNum
     * @param sceOrdTpCd
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getMachMstrNotRecieved(String glblCmpyCd, String rwsNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getMachMstrNotRecieved", params);
    }

    /**
     * getRwsDtlInfo
     * @param glblCmpyCd
     * @param rwsNum
     * @param rwsDtlLineNum
     * @return
     */
    private Map<String, Object> getRwsDtlInfo(String glblCmpyCd, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("rwsDtlLineNum", rwsDtlLineNum);

        return (Map<String, Object>) ssmClient.queryObject("getRwsDtlInfo", params);
    }

    /**
     * getMachMstrNonAllocation
     * @param glblCmpyCd
     * @param rwsShotageInfo
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getMachMstrNonAllocation(String glblCmpyCd, RwsShotageInfoBean rwsShotageInfo) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("svcConfigMstrPk", rwsShotageInfo.getSvcConfigMstrPk());
        params.put("mdseCd", rwsShotageInfo.getMdseCd());
        params.put("curlocNum", rwsShotageInfo.getInvtyLocCd());
        params.put("svcMachMstrLocStsCd", LOC_STS.IN_TRANSIT_WH);
        params.put("stkStsCd", rwsShotageInfo.getInvtyStkStsCd());
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("svcMachMstrStsTrmn", SVC_MACH_MSTR_STS.TERMINATED);
        params.put("svcMachMstrStsDup", SVC_MACH_MSTR_STS.DUPLICATE);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getMachMstrNonAllocation", params);
    }

    // 2017/11/2 ADD M.Naito QC#18216 START
    /**
     * createMachMstrPMsg
     * @param glblCmpyCd String
     * @param rwsNum String
     * @param rwsDtlLineNum String
     * @return Map<String, String>
     */
    private Map<String, String> getRossShipItemInfo(String glblCmpyCd, String rwsNum, String rwsDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rwsNum", rwsNum);
        params.put("rwsDtlLineNum", rwsDtlLineNum);

        return (Map<String, String>) ssmClient.queryObject("getRossShipItemInfo", params);
    }
    // 2017/11/2 ADD M.Naito QC#18216 END

    /**
     * createMachMstrPMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param serNum String
     * @param mode String
     * @param rwsPutAwayWrkMap Map<String, Object>
     * @param oldMachMstr Map<String, Object>
     * @return NSZC001001PMsg
     */
    private NSZC001001PMsg createMachMstrPMsgRm(String glblCmpyCd, String slsDt, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String serNum, String mode, Map<String, Object> rwsPutAwayWrkMap, Map<String, Object> oldMachMstr) {

        NSZC001001PMsg machMstrPMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxModeCd, mode);

        ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.mdseCd, (String) oldMachMstr.get("CONV_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.serNum, (String) oldMachMstr.get("CONV_SER_NUM"));
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.custMachCtrlNum, (String) oldMachMstr.get("CONV_SER_NUM"));
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.oldSerNum, (String) oldMachMstr.get(KEY_SER_NUM));
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.prntSerNum, machMstrPMsg.serNum);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.stkStsCd, (String) rwsPutAwayWrkMap.get(KEY_INVTY_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.curLocNum, (String) rwsPutAwayWrkMap.get(KEY_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachTpCd, svcMachMstrTMsg.svcMachTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.effFromDt, slsDt);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(machMstrPMsg.svcMachQty, BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(machMstrPMsg.xxCmptMachList.no(0).svcMachMstrPk, machMstrPMsg.svcMachMstrPk);
        machMstrPMsg.xxCmptMachList.setValidCount(1);

        return machMstrPMsg;
    }
    // QC#53836 Add
    /**
     * isMainMachRmv
     * @param glblCmpyCd
     * @param trxOrdNum
     * @param svcMachMstrPk
     * @return
     */
    private boolean isMainMachRmv(String glblCmpyCd, String trxOrdNum, BigDecimal svcMachMstrPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("trxOrdNum", trxOrdNum);
        params.put("svcMachMstrPk", svcMachMstrPk);

        String rmvFlg =  (String)ssmClient.queryObject("isMainMachRmv", params);
        // QC#54247 Mod
//        if(rmvFlg.equals(ZYPConstant.FLG_ON_Y)) {
        if(ZYPConstant.FLG_ON_Y.equals(rmvFlg)) {
            return true;
        } else {
            return false;
        }
    }

    // QC#53385 Add. Mod QC#56610
    /**
     * getSoSer
     * @param glblCmpyCd
     * @param svcConfigMsgrPk
     * @return
     */
    private List<Map<String, Object>> getSoSer(String glblCmpyCd, String soNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("soNum", soNum);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getSoSer", params);
    }
    // QC#54249 Mod Start
    // QC#54247 Add
    /**
     * isCfgChgAllRmv
     * @param glblCmpyCd
     * @param trxOrdNum
     * @return
     */
    private boolean isCfgChgAllRmv(String glblCmpyCd, Map<String, Object> rwsPutAwayWrkMap, String sceOrdTpCd) {

        if (!SCE_ORD_TP.CONFIG_CHANGE.equals(sceOrdTpCd)) {
            return false;
        }
        String trxOrdNum = (String) rwsPutAwayWrkMap.get("TRX_ORD_NUM");
        if (!ZYPCommonFunc.hasValue(trxOrdNum)) {
            return false;
        }

        boolean isCfgChgAllRmv = true;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("trxOrdNum", trxOrdNum);

        List<Map<String, Object>> rsltlist = (List<Map<String, Object>>) ssmClient.queryObjectList("isCfgChgAllRmv", params);

        if (rsltlist == null || rsltlist.isEmpty()) {
            isCfgChgAllRmv = false;
        } else {
            for (Map<String, Object> rslt : rsltlist) {
                String rmvFlg = (String) rslt.get("RMV_CONFIG_FLG");
                if(!ZYPConstant.FLG_ON_Y.equals(rmvFlg)) {
                    isCfgChgAllRmv = false;
                    break;
                }
            }
        }

        return isCfgChgAllRmv;
    }
//    private boolean isCfgChgAllRmv(String glblCmpyCd, String trxOrdNum) {
//
//        boolean isCfgChgAllRmv = true;
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", glblCmpyCd);
//        params.put("trxOrdNum", trxOrdNum);
//
//        List<Map<String, Object>> rsltlist = (List<Map<String, Object>>) ssmClient.queryObjectList("isCfgChgAllRmv", params);
//
//        if (rsltlist == null || rsltlist.isEmpty()) {
//            isCfgChgAllRmv = false;
//        } else {
//            for (Map<String, Object> rslt : rsltlist) {
//                String rmvFlg = (String) rslt.get("RMV_CONFIG_FLG");
//                if(!ZYPConstant.FLG_ON_Y.equals(rmvFlg)) {
//                    isCfgChgAllRmv = false;
//                    break;
//                }
//            }
//        }
//
//        return isCfgChgAllRmv;
//    }
    // QC#54249 Mod End

    // Add QC#56610
    /**
     * getSoNonSerialList
     * @param glblCmpyCd
     * @param svcConfigMsgrPk
     * @return
     */
    private List<Map<String, Object>> getSoNonSerialList(String glblCmpyCd, String soNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("soNum", soNum);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("flgY", ZYPConstant.FLG_ON_Y);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getSoNonSerialList", params);
    }

    /**
     * getSoNonSerialList
     * @param glblCmpyCd
     * @param svcConfigMsgrPk
     * @return BigDecimal
     */
    private BigDecimal getNonSerialSMMPK(String glblCmpyCd, String curLocNum, String stkStsCd, String mdseCd, List<BigDecimal> svcMachMstrPkList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("curLocNum", curLocNum);
        params.put("stkStsCd", stkStsCd);
        params.put("mdseCd", mdseCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("svcMachMstrStsCrat", SVC_MACH_MSTR_STS.CREATED);
        params.put("svcMachMstrStsRmv", SVC_MACH_MSTR_STS.REMOVED);
        params.put("svcMachMstrPkList", svcMachMstrPkList);

        return (BigDecimal) ssmClient.queryObject("getNonSerialSMMPK", params);
    }
}
