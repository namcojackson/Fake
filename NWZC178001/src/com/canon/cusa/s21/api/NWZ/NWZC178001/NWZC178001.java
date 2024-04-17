/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC178001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Contract Node Credit Check API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/26   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/03   Hitachi         T.Tsuchida      Update          QC4746
 * 2016/11/15   Fujitsu         H.Nagashima     Update          QC15745
 * 2017/04/12   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.1
 * 2017/05/17   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7319
 * 2017/06/27   Fujitsu         S.Takami        Update          S21_NA#19291
 *</pre>
 */
public class NWZC178001 extends S21ApiCommonBase {

    // START 2016/03/03 T.Tsuchida [QC4736, MOD]
    /** Data Company Code is not entered. */
    private static final String NWZM0001E = "NWZM0001E";

    /** Order Number is not entered. */
    private static final String NWZM0002E = "NWZM0002E";

//QC#15745 del Start
//    /** Order Line Number is not entered. */
//    private static final String NWZM0003E = "NWZM0003E";
//
//    /** Order Line Sub Number is not entered. */
//    private static final String NWZM0004E = "NWZM0004E";
//
//    /** "Shipping Plan Number" is not entered. */
//    private static final String NWZM0250E = "NWZM0250E";
//
//    /** The data does not exist in CPO. */
//    private static final String NWZM0073E = "NWZM0073E";
//QC#15745 del End

    /** Sales Date is not entered. */
    private static final String NWZM0445E = "NWZM0445E";

    // 2017/06/27 S21_NA#19291 Del Start
//    /** The data does not exist in DS CPO. */
//    private static final String NWZM1491E = "NWZM1491E";
//    // 2017/06/27 S21_NA#19291 Del End
    // END 2016/03/03 T.Tsuchida [QC4736, MOD]

//QC#15745 add Start
    /** Set Parent Line Sub Number */
    public static final String SET_PARENT_LINE_SUB_NUM = "000";

//    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = null;
//QC#15745 add End

    /**
     * Constructor
     */
    public NWZC178001() {
        super();
        ssmClient = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * <pre>
     * Refer to the class comment.
     * </pre>
     * @param param Interface Msg of Contract Over Due Hold API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(param.getInputPMsg());

        doProcess(param, msgMap);

        msgMap.flush();
    }

    /**
     * <pre>
     * Refer to the class comment.
     * One Msg in List is taken out, and the execute(NWXC005001ValidationBean, ONBATCH_TYPE) is executed.
     * </pre>
     * @see #execute(NWXC005001ValidationBean,
     * com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE)
     * @param params Interface Msg list of Contract Over Due Hold API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWXC005001ValidationBean> params, final ONBATCH_TYPE onBatchType) {
        for (NWXC005001ValidationBean param : params) {
            execute(param, onBatchType);
        }
    }

    private void doProcess(NWXC005001ValidationBean param, S21ApiMessageMap msgMap) {

        if (!checkInput(msgMap)) {
            return;
        }
//QC#15745 del Start
//        SHPG_PLNTMsg shpgPlnTMsg = param.getSpTMsg();
//        if (shpgPlnTMsg == null) {
//            msgMap.addXxMsgId(NWZM0073E);
//            return;
//        }
//QC#15745 del End

        // 2017/06/27 S21_NA#19291 Del Start
//        CPO_DTLTMsg cpoDtlTMsg = param.getCdTMsg();
//        if (cpoDtlTMsg == null) {
//            msgMap.addXxMsgId(NWZM1491E);
//            return;
//        }
        // 2017/06/27 S21_NA#19291 Del End

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        // 2017/06/27 S21_NA#19291 Mod Start
//        String contrHldFlg = getContrHldFlg(inMsg.glblCmpyCd.getValue(), cpoDtlTMsg.dsContrNum.getValue());
        String contrHldFlg = getContrHldFlg(inMsg.glblCmpyCd.getValue(), inMsg.cpoOrdNum_I.getValue());
        // 2017/06/27 S21_NA#19291 Mod End
        if (ZYPConstant.FLG_ON_Y.equals(contrHldFlg)) {
            //QC#15745 mod Start
//            setHldRsn(inMsg, dsCpoDtlTMsg, shpgPlnTMsg);
            setOutParam(inMsg);
            //QC#15745 mod End
        }
    }

    private static boolean checkInput(S21ApiMessageMap msgMap) {

        NWXC005001PMsg inMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(inMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0001E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(inMsg.cpoOrdNum_I)) {
            msgMap.addXxMsgId(NWZM0002E);
            return false;
        }

//QC#15745 del Start
//        if (!ZYPCommonFunc.hasValue(inMsg.cpoDtlLineNum_I)) {
//            msgMap.addXxMsgId(NWZM0003E);
//            return false;
//        }
//
//        if (!ZYPCommonFunc.hasValue(inMsg.cpoDtlLineSubNum_I)) {
//            msgMap.addXxMsgId(NWZM0004E);
//            return false;
//        }
//
//        if (!ZYPCommonFunc.hasValue(inMsg.shpgPlnNum_I)) {
//            msgMap.addXxMsgId(NWZM0250E);
//            return false;
//        }
//QC#15745 del End

        if (!ZYPCommonFunc.hasValue(inMsg.slsDt)) {
            msgMap.addXxMsgId(NWZM0445E);
            return false;
        }

        return true;
    }

    /**
     * Get contract hold flag.
     * @param glblCmpyCd String
     * @param dsContrNum String
     */
//    private String getContrHldFlg(String glblCmpyCd, String dsContrNum) { 2017/06/27 S21_NA#19291 Mod Interface
    private String getContrHldFlg(String glblCmpyCd, String cpoOrdNum) {

        // 2017/06/27 S21_NA#19291 Add Start
        // get ds contract number from CPO_DTL depended on cpoOrdNum
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        List<String> dsContrNumList = (List<String>) ssmClient.queryObjectList("getDsContrNumByCpoOrdNum", ssmParam);
        if (dsContrNumList == null || dsContrNumList.isEmpty()) {
            return ZYPConstant.FLG_OFF_N;
        }
        // 2017/06/27 S21_NA#19291 Add End

        // 2017/06/27 S21_NA#19291 Mod Start
//        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
//        dsContrTMsg.setSQLID("003");
//        dsContrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        dsContrTMsg.setConditionValue("dsContrNum01", dsContrNum);
//        DS_CONTRTMsgArray dsContrTMsgArray = (DS_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrTMsg);
//        if (dsContrTMsgArray.getValidCount() == 0) {
//            return ZYPConstant.FLG_OFF_N;
//        }
//        return dsContrTMsgArray.no(0).contrHldFlg.getValue();

        for (String dsContrNum : dsContrNumList) {
            DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
            dsContrTMsg.setSQLID("003");
            dsContrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            dsContrTMsg.setConditionValue("dsContrNum01", dsContrNum);
            DS_CONTRTMsgArray dsContrTMsgArray = (DS_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrTMsg);
            if (dsContrTMsgArray.getValidCount() == 0) {
                continue;
            }

            for (int i = 0; i < dsContrTMsgArray.getValidCount(); i++) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dsContrTMsgArray.no(i).contrHldFlg.getValue())) {
                    return ZYPConstant.FLG_ON_Y;
                }
            }
        }
        return ZYPConstant.FLG_OFF_N;
        // 2017/06/27 S21_NA#19291 Add End
    }

//QC#15745 del Start
//    /**
//     * Set hold reason.
//     * @param inMsg NWXC005001PMsg
//     * @param dsCpoDtlTMsg DS_CPO_DTLTMsg
//     * @param shpgPlnTMsg SHPG_PLNTMsg
//     */
//    private void setHldRsn(NWXC005001PMsg inMsg, DS_CPO_DTLTMsg dsCpoDtlTMsg, SHPG_PLNTMsg shpgPlnTMsg) {
//
//        ZYPEZDItemValueSetter.setValue(inMsg.cpoOrdNum_O, dsCpoDtlTMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineNum_O, shpgPlnTMsg.trxLineNum);
//        ZYPEZDItemValueSetter.setValue(inMsg.cpoDtlLineSubNum_O, shpgPlnTMsg.trxLineSubNum);
//        ZYPEZDItemValueSetter.setValue(inMsg.shpgPlnNum_O, shpgPlnTMsg.shpgPlnNum);
//        ZYPEZDItemValueSetter.setValue(inMsg.hldRsnCd, HLD_RSN.CONTRACT_CREDIT_REVIEW);
//    }
//QC#15745 del End

//QC#15745 add Start
    /**
     * Set Output Parameter
     * @param inMsg NWXC005001PMsg
     */
    private void setOutParam(NWXC005001PMsg inMsg) {

        // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Mod Start
//        List<Map<String, Object>> shpgPlnList = getShpgPlnList(inMsg);
//        if (shpgPlnList == null || shpgPlnList.size() == 0) {
//            return;
//        }
//
//        int index = 0;
//        for (; index < shpgPlnList.size(); index++) {
//            NWXC005001_shpgPlnListPMsg shpgPlnListPMsg = inMsg.shpgPlnList.no(index);
//            Map<String, Object> shpgPlnInfo = shpgPlnList.get(index);
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.cpoOrdNum, (String) shpgPlnInfo.get("TRX_HDR_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.cpoDtlLineNum, (String) shpgPlnInfo.get("TRX_LINE_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.cpoDtlLineSubNum, (String) shpgPlnInfo.get("TRX_LINE_SUB_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.shpgPlnNum, (String) shpgPlnInfo.get("SHPG_PLN_NUM"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.ordQty, (BigDecimal) shpgPlnInfo.get("ORD_QTY"));
//            ZYPEZDItemValueSetter.setValue(shpgPlnListPMsg.hldRsnCd, HLD_RSN.CONTRACT_CREDIT_REVIEW);
//        }
//        inMsg.shpgPlnList.setValidCount(index);
        inMsg.cpoOrdNum_O.setValue(inMsg.cpoOrdNum_I.getValue());
        inMsg.hldRsnCd.setValue(HLD_RSN.CONTRACT_CREDIT_REVIEW);
        // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Mod End
    }

    // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Del Start
//    /**
//     * Get Shipping Plan List
//     * @param inMsg NWXC005001PMsg
//     * @return Shipping Plan List
//     */
//    @SuppressWarnings("unchecked")
//    private List<Map<String, Object>> getShpgPlnList(NWXC005001PMsg inMsg) {
//
//        Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd", inMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoOrdNum", inMsg.cpoOrdNum_I.getValue());
//        ssmParam.put("setParentLineSubNum", SET_PARENT_LINE_SUB_NUM);
//        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
//
//        return (List<Map<String, Object>>) ssmClient.queryObjectList("getShpgPlnList", ssmParam);
//    }
    // 2017/05/17 S21_NA#Review structure Lv.2 RS#7319 Del End
//QC#15745 add End
}
