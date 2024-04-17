/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC060001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NLZC060001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * PI Activity Status API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2015   CSAI            K.Lee           Create          Initial
 * 07/19/2016   CSAI            Y.Imazu         Update          QC#7981
 *</pre>
 */
public class NLZC060001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    private List<Map<String, Object>> physInvtyCtrlList = null;

    public NLZC060001() {

        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute This can be called method from external class.
     * @param NLZC060001PMsg parameter
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(NLZC060001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);
        doProcess();
        msgMap.flush();
    }

    /**
     * execute This can be called method from external class.
     * @param List<NLZC060001PMsg> list
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(List<NLZC060001PMsg> list, final ONBATCH_TYPE onBatchType) {

        for (NLZC060001PMsg param : list) {

            execute(param, onBatchType);
        }
    }

    /**
     * doProcess This is Main process Method.
     */
    private void doProcess() {

        NLZC060001PMsg pMsg = (NLZC060001PMsg) msgMap.getPmsg();

        // Mandatory Check
        if (!checkMandatory(pMsg)) {

            return;
        }

        // Get Open Physical Inventory Control
        if (physInvtyCtrlList == null) {

            physInvtyCtrlList = getPhysInvtyCtrl(pMsg);
        }

        if (physInvtyCtrlList.size() == 0) {

            ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC060001Constant.RETURN_CODE_NORMAL);
            return;
        }

        // Get SO/RWS/Location Retail WH
        List<Map<String, Object>> orderRtlWhList = null;

        if (ZYPCommonFunc.hasValue(pMsg.soNum)) {

            orderRtlWhList = getSo(pMsg);

        } else if (ZYPCommonFunc.hasValue(pMsg.rwsNum)) {

            orderRtlWhList = getRws(pMsg);

        } else {

            orderRtlWhList = getInvtyLoc(pMsg);
        }

        // Check Open Physical Inventory
        isOpenedPhysInvty(pMsg, orderRtlWhList);
    }

    /**
     * checkMandatory
     * @param pMsg NLZC060001PMsg
     * @return boolean Normal:true, Error:false
     */
    private boolean checkMandatory(NLZC060001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {

            setErrMsg(pMsg, NLZC060001Constant.NLGM0012E);
            return false;
        }

        if (!hasValue(pMsg.soNum) && !hasValue(pMsg.rwsNum) && !hasValue(pMsg.invtyLocCd)) {

            setErrMsg(pMsg, NLZC060001Constant.NLZM2474E);
            return false;
        }

        return true;
    }

    /**
     * getPhysInvtyCtrl
     * @param pMsg NLZC060001PMsg
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getPhysInvtyCtrl(NLZC060001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("physInvtyStsCd_Open", PHYS_INVTY_STS.OPEN);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getPhysInvtyCtrl", ssmParam);
    }

    /**
     * getSo
     * @param pMsg NLZC060001PMsg
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getSo(NLZC060001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("soNum", pMsg.soNum.getValue());

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getShpgOrd", ssmParam);
    }

    /**
     * getRws
     * @param pMsg NLZC060001PMsg
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getRws(NLZC060001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("rwsNum", pMsg.rwsNum.getValue());

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getRws", ssmParam);
    }

    /**
     * getInvtyLoc
     * @param pMsg NLZC060001PMsg
     * @return Map<String, Object>
     */
    private List<Map<String, Object>> getInvtyLoc(NLZC060001PMsg pMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("invtyLocCd", pMsg.invtyLocCd.getValue());

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getInvtyLoc", ssmParam);
    }

    /**
     * setErrMsg
     * @param pMsg NLZC060001PMsg
     * @param xxLineNum String
     * @param rtlWhcd String
     * @param rtlSwhCd String
     * @param msgId String
     */
    private void setErrMsg(NLZC060001PMsg pMsg, String xxLineNum, String rtlWhcd, String rtlSwhCd, String msgId) {

        msgMap.addXxMsgId(msgId);
        S21InfoLogOutput.println(msgId);
        S21InfoLogOutput.println(msgMap.getPmsg().toString());

        int no = pMsg.errInfo.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.errInfo.no(no).xxLineNum, xxLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.errInfo.no(no).rtlWhCd, rtlWhcd);
        ZYPEZDItemValueSetter.setValue(pMsg.errInfo.no(no).rtlSwhCd, rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.errInfo.no(no).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC060001Constant.RETURN_CODE_ERROR);
        msgMap.flush();
    }

    /**
     * setErrMsg
     * @param pMsg NLZC060001PMsg
     * @param msgId String
     */
    private void setErrMsg(NLZC060001PMsg pMsg, String msgId) {

        msgMap.addXxMsgId(msgId);
        S21InfoLogOutput.println(msgId);
        S21InfoLogOutput.println(msgMap.getPmsg().toString());

        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, NLZC060001Constant.RETURN_CODE_ERROR);
        msgMap.flush();
    }

    /**
     * isOpenedPhysInvty
     * @param pMsg NLZC060001PMsg
     * @param orderRtlWhList List<Map<String, Object>>
     */
    private void isOpenedPhysInvty(NLZC060001PMsg pMsg, List<Map<String, Object>> orderRtlWhList) {

        for (int i = 0; i < orderRtlWhList.size(); i++) {

            Map<String, Object> orderRtlWhMap = orderRtlWhList.get(i);
            String xxLineNum = (String) orderRtlWhMap.get("XX_LINE_NUM");
            String ordRtlWh = (String) orderRtlWhMap.get("RTL_WH_CD");
            String ordRtlSwh = (String) orderRtlWhMap.get("RTL_SWH_CD");

            for (int j = 0; j < physInvtyCtrlList.size(); j++) {

                Map<String, Object> openedPhysInvtyMap = physInvtyCtrlList.get(j);
                String physInvtyRtlWh = (String) openedPhysInvtyMap.get("RTL_WH_CD");
                String physInvtyRtlSwh = (String) openedPhysInvtyMap.get("RTL_SWH_CD");

                if (!ZYPCommonFunc.hasValue(physInvtyRtlSwh)) {

                    if (physInvtyRtlWh.equals(ordRtlWh)) {

                        setErrMsg(pMsg, xxLineNum, ordRtlWh, ordRtlSwh, NLZC060001Constant.NLZM2277E);
                    }

                } else if (physInvtyRtlWh.equals(ordRtlWh) && physInvtyRtlSwh.equals(ordRtlSwh)) {

                    setErrMsg(pMsg, xxLineNum, ordRtlWh, ordRtlSwh, NLZC060001Constant.NLZM2277E);
                    return;
                }
            }
        }
    }
}
