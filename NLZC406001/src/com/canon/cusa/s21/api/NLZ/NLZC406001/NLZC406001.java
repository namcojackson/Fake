/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC406001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NLZC406001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC406001.constant.NLZC406001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Serial Validation API for WMS
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2015   CSAI            K.Lee           Create          Initial
 * 10/31/2016   CITS            R.Shimamoto     Update          QC#15085
 * 02/22/2017   CITS            T.Wada          Update          QC#17339
 * 2018/08/22   CITS            T.Hakodate      Update          QC#27884
*</pre>
 */
public class NLZC406001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** Serial Range Check Flg */
    private boolean serRangeCheckFlg = false;

    public NLZC406001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute This can be called method from external class.
     * @param NSZC010001PMsg parameter
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(NLZC406001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);
        doProcess();
        msgMap.flush();

        // Set Message Text
        for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
            String msgErrTxt = S21MessageFunc.clspGetMessage(param.xxMsgIdList.no(i).xxMsgId.getValue());
            ZYPEZDItemValueSetter.setValue(param.xxMsgIdList.no(i).intfcErrMsgTxt, msgErrTxt);
        }
    }

    /**
     * execute This can be called method from external class.
     * @param List<NSZC010001PMsg> list
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(List<NLZC406001PMsg> list, final ONBATCH_TYPE onBatchType) {

        for (NLZC406001PMsg param : list) {
            execute(param, onBatchType);
        }
    }

    /**
     * doProcess This is Main process Method.
     */
    private void doProcess() {

        NLZC406001PMsg pMsg = (NLZC406001PMsg) msgMap.getPmsg();

        String serRangeFrom = null;
        String serRangeThru = null;
        int lgSerNum = 0;
        String instlBaseCtrlFlg = null;

        // #######################################
        // 1-1. Mandatory Check
        // #######################################
        if (!checkMandatory(pMsg)) {
            return;
        }

        List<Map<String, Object>> itemInfoList = getItemMaster(pMsg);

        if (itemInfoList != null) {
            // 10/31/2016 R.Shimamoto Mod Start.[QC#15085]
            for (Map<String, Object> itemInfo : itemInfoList) {

                serRangeFrom = (String) itemInfo.get("FROM_SER_NUM");
                serRangeThru = (String) itemInfo.get("THRU_SER_NUM");
                BigDecimal lgSerNumDec = (BigDecimal) itemInfo.get("LG_SER_NUM");
                if (lgSerNumDec != null) {
                    lgSerNum = lgSerNumDec.intValue();
                }
                instlBaseCtrlFlg = (String) itemInfo.get("INSTL_BASE_CTRL_FLG");

                // #######################################
                // 2-1. Serial Range Check
                // #######################################
                serRangeCheckFlg = isValidatedSerialRange(lgSerNum, serRangeFrom, serRangeThru, pMsg.serNum.getValue(), pMsg);

                // QC# 27884 mod start
                // if (instlBaseCtrlFlg.equals(ZYPConstant.FLG_OFF_N))
                // {
                if (ZYPConstant.FLG_OFF_N.equals(instlBaseCtrlFlg)) {
                    // QC# 27884 mod end
                    
                    ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC406001Constant.RETURN_CODE_NA);
                    setWarningMsg(pMsg, NLZC406001Constant.NLZM2288W);
                    return;
                }
                Map<String, Object> ibInfo = getIb(pMsg);

                if (!serRangeCheckFlg) {

                    continue;

                } else {

                    // #######################################
                    // 2-1. Serial Check
                    // #######################################
                    if (!isValidSerial(itemInfo, ibInfo, pMsg)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC406001Constant.RETURN_CODE_ERROR);
                        return;
                    }

                    break;
                }

            }
            if (!serRangeCheckFlg) {
                // The Serial # is incorrect. It is set out of the
                // range.
                setErrMsg(pMsg, NLZC406001Constant.NLZM2292E);
                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC406001Constant.RETURN_CODE_ERROR);
                return;
            }
            // 10/31/2016 R.Shimamoto Mod End.[QC#15085]
        } else {
            // Item does not Exist
            setErrMsg(pMsg, NLZC406001Constant.NLZM2287E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC406001Constant.RETURN_CODE_NORMAL);
    }

    /**
     * setHeaderErrMsg
     * @param NSZC010001PMsg pMsg
     * @param String msgId
     */
    private void setErrMsg(NLZC406001PMsg pMsg, String msgId) {
        msgMap.addXxMsgId(msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC406001Constant.RETURN_CODE_ERROR);
        msgMap.flush();
    }

    /**
     * setHeaderErrMsg
     * @param NSZC010001PMsg pMsg
     * @param String msgId
     */
    private void setWarningMsg(NLZC406001PMsg pMsg, String msgId) {
        msgMap.addXxMsgId(msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC406001Constant.RETURN_CODE_NA);
        msgMap.flush();
    }

    /**
     * checkMandatory
     * @return boolean Normal:true, Error:false
     */
    private boolean checkMandatory(NLZC406001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {
            // Global Company Code is mandatory.
            setErrMsg(pMsg, NLZC406001Constant.NLGM0012E);
            return false;
        }

        if (!hasValue(pMsg.mdseCd)) {
            // Item Code is mandatory.
            setErrMsg(pMsg, NLZC406001Constant.NLZM2289E);
            return false;
        }

        if (!hasValue(pMsg.serNum)) {
            // Input parameter "Mode Code" is a mandatory field.
            setErrMsg(pMsg, NLZC406001Constant.NLZM2091E);
            return false;
        }
        return true;
    }

    /**
     * checkMandatory
     * @return boolean Normal:true, Error:false
     */
    private boolean isValidatedSerialRange(int lgSerNum, String serRangeFrom, String serRangeThru, String serNum, NLZC406001PMsg pMsg) {
        // 10/31/2016 R.Shimamoto Mod Start.[QC#15085]
        if (lgSerNum > 0 && serNum.length() != lgSerNum) {
            return false;
        } else if (ZYPCommonFunc.hasValue(serRangeFrom) && pMsg.serNum.getValue().compareTo(serRangeFrom) < 0) {
            return false;
        } else if (ZYPCommonFunc.hasValue(serRangeThru) && pMsg.serNum.getValue().compareTo(serRangeThru) > 0) {
            return false;
        }
        // 10/31/2016 R.Shimamoto Mod End.[QC#15085]
        return true;
    }

    /**
     * isValidSerial
     * @param itemInfo Map<String, Object>
     * @param soRwsInfo Map<String, Object>
     * @param ibInfo Map<String, Object>
     * @param pMsg NLZC406001PMsg
     * @return boolean
     */
    private boolean isValidSerial(Map<String, Object> itemInfo, Map<String, Object> ibInfo, NLZC406001PMsg pMsg) {

        String rcvSerTakeFlg = (String) itemInfo.get("RCV_SER_TAKE_FLG");

        BigDecimal svcMachMstrPk = null;
        String svcMachMstrStsCd = null;
        String svcMachLocStsCd = null;

        if (ibInfo != null) {
            svcMachMstrPk = (BigDecimal) ibInfo.get("SVC_MACH_MSTR_PK");
            svcMachMstrStsCd = (String) ibInfo.get("SVC_MACH_MSTR_STS_CD");
            svcMachLocStsCd = (String) ibInfo.get("SVC_MACH_MSTR_LOC_STS_CD");
        }
        // QC# 27884 mod start
        // if (rcvSerTakeFlg.equals(ZYPConstant.FLG_ON_Y)) {
        if (ZYPConstant.FLG_ON_Y.equals(rcvSerTakeFlg)) {
            // QC# 27884 mod end
            // QC#17339
            if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {

                // QC# 27884 mod start
                // if
                // (svcMachMstrStsCd.equals(SVC_MACH_MSTR_STS.TERMINATED))
                // {
                if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd)) {
                    // QC# 27884 mod end
                    return true;
                }
                // QC# 27884 mod start
                // if (svcMachLocStsCd.equals(LOC_STS.IN_TRANSIT)) {
                if (LOC_STS.IN_TRANSIT.equals(svcMachLocStsCd)) {
                    // QC# 27884 mod end
                    return true;
                }
                
                // Entered serial is already registered in IB.
                setErrMsg(pMsg, NLZC406001Constant.NLZM2299E);
                ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC406001Constant.RETURN_CODE_ERROR);
                return false;
            }
        }
        return true;
    }

    /**
     * getItemMaster
     * @param pMsg NLZC406001PMsg
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    // 10/31/2016 R.Shimamoto Mod Start.[QC#15085]
    private List<Map<String, Object>> getItemMaster(NLZC406001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());

        return ssmClient.queryObjectList("getItemMaster", ssmParam);
    }

    // 10/31/2016 R.Shimamoto Mod End.[QC#15085]

    /**
     * getIB
     * @param pMsg NLZC406001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getIb(NLZC406001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", pMsg.mdseCd.getValue());
        ssmParam.put("serNum", pMsg.serNum.getValue());

        return (Map<String, Object>) ssmClient.queryObject("getIb", ssmParam);
    }
}
