/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC611001;

import static com.canon.cusa.s21.api.NMZ.NMZC611001.constant.NMZC611001Constant.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NMZC611001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Default Carrier Get API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/15   Fujitsu         K.Koitabashi    Create          N/A
 * 2018/12/06   Fujitsu         M.Ohno          Update          QC#29315
 *</pre>
 */
public class NMZC611001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NMZC611001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Default Carrier Get API (List)
     * </pre>
     * @param params Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(List<NMZC611001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NMZC611001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * <pre>
     * Default Carrier Get API
     * </pre>
     * @param param Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(final NMZC611001PMsg param, final ONBATCH_TYPE onBatchType) {

        // Checking Input value
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (!checkInput(msgMap)) {
            msgMap.flush();
            return;
        }

        // main
        doProcess(msgMap);
        // end
        msgMap.flush();

    }

    /**
     * Input parameter check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkInput(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC611001PMsg param = (NMZC611001PMsg) msgMap.getPmsg();

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NMZM0009E);
            returnValue = false;
        }

        // Sales Date
        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NMZM0011E);
            return false;
        }

        // Account Number
        if (!ZYPCommonFunc.hasValue(param.dsAcctNum)) {
            msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Account Number" });
            return false;
        }

        // 2018/12/06 QC#29315 Add Start
        // Location Number
        if (!ZYPCommonFunc.hasValue(param.locNum)) {
            msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Location Number" });
            return false;
        }

        // Line Business Type
        if (!ZYPCommonFunc.hasValue(param.lineBizTpCd)) {
            msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Line Business Type Code" });
            return false;
        }

        // Business Area Business Area 
        if (!ZYPCommonFunc.hasValue(param.dsBizAreaCd)) {
            msgMap.addXxMsgIdWithPrm(NMZM0056E, new String[] {"Business Area Code" });
            return false;
        }
        // 2018/12/06 QC#29315 Add End

        return returnValue;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        NMZC611001PMsg param = (NMZC611001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.vndCd_I)) {
            // 2018/12/06 QC#29315 Add Start
            if (searchCarrierForLoc(msgMap, param)) {
                return;
            }
            // 2018/12/06 QC#29315 Add End
            searchCarrier(msgMap, param);
        } else {
            // 2018/12/06 QC#29315 Add Start
            if (searchCarrierAccountForLoc(msgMap, param)) {
                return;
            }
            // 2018/12/06 QC#29315 Add End
            searchCarrierAccount(msgMap, param);
        }
    }

    /**
     * Get Default Carrier information
     * @param msgMap S21ApiMessageMap
     * @param param NMZC611001PMsg
     */
    private void searchCarrier(S21ApiMessageMap msgMap, NMZC611001PMsg param) {

        List<CarrierInfoBean> defaultCarrierList = getDefaultCarrior(param);

        if (defaultCarrierList != null && defaultCarrierList.size() == 1) {
            CarrierInfoBean defaultCarrier = defaultCarrierList.get(0);
            ZYPEZDItemValueSetter.setValue(param.vndCd_O, defaultCarrier.getVndCd());
            ZYPEZDItemValueSetter.setValue(param.dsCarrAcctNum, defaultCarrier.getDsCarrAcctNum());
            ZYPEZDItemValueSetter.setValue(param.defAcctCarrFlg, defaultCarrier.getDefAcctCarrFlg());
        }
    }

    /**
     * Get Default Carrier Account information
     * @param msgMap S21ApiMessageMap
     * @param param NMZC611001PMsg
     */
    private void searchCarrierAccount(S21ApiMessageMap msgMap, NMZC611001PMsg param) {

        CarrierInfoBean carriorAccount = null;
        List<CarrierInfoBean> carriorAccountList = null;

        carriorAccountList = getDefaultCarriorAccount(param);

        if (carriorAccountList != null && carriorAccountList.size() > 0) {
            int idx = 0;
            if (carriorAccountList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(param.vndCd_O, carriorAccountList.get(idx).getVndCd());
                ZYPEZDItemValueSetter.setValue(param.dsCarrAcctNum, carriorAccountList.get(idx).getDsCarrAcctNum());
                ZYPEZDItemValueSetter.setValue(param.defAcctCarrFlg, carriorAccountList.get(idx).getDefAcctCarrFlg());
            } else {
                carriorAccount = getDefaultCarrierAccountForMultiple(param);
                if (carriorAccount != null) {
                    ZYPEZDItemValueSetter.setValue(param.vndCd_O, carriorAccount.getVndCd());
                    ZYPEZDItemValueSetter.setValue(param.dsCarrAcctNum, carriorAccount.getDsCarrAcctNum());
                    ZYPEZDItemValueSetter.setValue(param.defAcctCarrFlg, carriorAccount.getDefAcctCarrFlg());
                }
            }
        }
    }

    /**
     * @param param NMZC611001PMsg
     * @return CarrierInfoBean
     */
    private List<CarrierInfoBean> getDefaultCarrior(NMZC611001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsAcctNum", pMsg.dsAcctNum.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());
        // 2018/12/06 QC#29315 Add Start
        param.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        param.put("dsBizAreaCd", pMsg.dsBizAreaCd.getValue());
        param.put("frtCondCd", pMsg.frtCondCd.getValue());
        param.put("shpgSvcLvlCd", pMsg.shpgSvcLvlCd.getValue());
        // 2018/12/06 QC#29315 Add Start

        return (List<CarrierInfoBean>) ssmBatchClient.queryObjectList("getDefaultCarrier", param);
    }

    /**
     * @param param NMZC611001PMsg
     * @return CarrierInfoBean
     */
    @SuppressWarnings("unchecked")
    private List<CarrierInfoBean> getDefaultCarriorAccount(NMZC611001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsAcctNum", pMsg.dsAcctNum.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());
        param.put("vndCd", pMsg.vndCd_I.getValue());
        // 2018/12/06 QC#29315 Add Start
        param.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        param.put("dsBizAreaCd", pMsg.dsBizAreaCd.getValue());
        // 2018/12/06 QC#29315 Add Start

        return (List<CarrierInfoBean>) ssmBatchClient.queryObjectList("getDefaultCarrierAccount", param);
    }

    /**
     * @param param NMZC611001PMsg
     * @return CarrierInfoBean
     */
    private CarrierInfoBean getDefaultCarrierAccountForMultiple(NMZC611001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsAcctNum", pMsg.dsAcctNum.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());
        param.put("vndCd", pMsg.vndCd_I.getValue());
        // 2018/12/06 QC#29315 Add Start
        param.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        param.put("dsBizAreaCd", pMsg.dsBizAreaCd.getValue());
        // 2018/12/06 QC#29315 Add Start

        return (CarrierInfoBean) ssmBatchClient.queryObject("getDefaultCarrierAccountForMultiple", param);
    }

    // 2018/12/06 QC#29315 Add Start
    /**
     * Get Default Carrier information
     * @param msgMap S21ApiMessageMap
     * @param param NMZC611001PMsg
     */
    private boolean searchCarrierForLoc(S21ApiMessageMap msgMap, NMZC611001PMsg param) {

        List<CarrierInfoBean> defaultCarrierList = getDefaultCarriorForLoc(param);

        if (defaultCarrierList != null && defaultCarrierList.size() == 1) {
            CarrierInfoBean defaultCarrier = defaultCarrierList.get(0);
            ZYPEZDItemValueSetter.setValue(param.vndCd_O, defaultCarrier.getVndCd());
            ZYPEZDItemValueSetter.setValue(param.dsCarrAcctNum, defaultCarrier.getDsCarrAcctNum());
            ZYPEZDItemValueSetter.setValue(param.defAcctCarrFlg, defaultCarrier.getDefAcctCarrFlg());
            return true;
        }

        if (defaultCarrierList.size() > 1 ) {
            return true;
        }

        return false;
    }

    /**
     * @param param NMZC611001PMsg
     * @return CarrierInfoBean
     */
    private List<CarrierInfoBean> getDefaultCarriorForLoc(NMZC611001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("locNum", pMsg.locNum.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());
        param.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        param.put("dsBizAreaCd", pMsg.dsBizAreaCd.getValue());
        param.put("frtCondCd", pMsg.frtCondCd.getValue());
        param.put("shpgSvcLvlCd", pMsg.shpgSvcLvlCd.getValue());

        return (List<CarrierInfoBean>) ssmBatchClient.queryObjectList("getDefaultCarrier", param);
    }
    

    /**
     * Get Default Carrier Account information
     * @param msgMap S21ApiMessageMap
     * @param param NMZC611001PMsg
     */
    private boolean searchCarrierAccountForLoc(S21ApiMessageMap msgMap, NMZC611001PMsg param) {

        CarrierInfoBean carriorAccount = null;
        List<CarrierInfoBean> carriorAccountList = null;

        carriorAccountList = getDefaultCarriorAccountForLoc(param);

        if (carriorAccountList != null && carriorAccountList.size() > 0) {
            int idx = 0;
            if (carriorAccountList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(param.vndCd_O, carriorAccountList.get(idx).getVndCd());
                ZYPEZDItemValueSetter.setValue(param.dsCarrAcctNum, carriorAccountList.get(idx).getDsCarrAcctNum());
                ZYPEZDItemValueSetter.setValue(param.defAcctCarrFlg, carriorAccountList.get(idx).getDefAcctCarrFlg());
                return true;
            } else {
                carriorAccount = getDefaultCarrierAccountForMultipleForLoc(param);
                if (carriorAccount != null) {
                    ZYPEZDItemValueSetter.setValue(param.vndCd_O, carriorAccount.getVndCd());
                    ZYPEZDItemValueSetter.setValue(param.dsCarrAcctNum, carriorAccount.getDsCarrAcctNum());
                    ZYPEZDItemValueSetter.setValue(param.defAcctCarrFlg, carriorAccount.getDefAcctCarrFlg());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param param NMZC611001PMsg
     * @return CarrierInfoBean
     */
    @SuppressWarnings("unchecked")
    private List<CarrierInfoBean> getDefaultCarriorAccountForLoc(NMZC611001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("locNum", pMsg.locNum.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());
        param.put("vndCd", pMsg.vndCd_I.getValue());
        param.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        param.put("dsBizAreaCd", pMsg.dsBizAreaCd.getValue());

        return (List<CarrierInfoBean>) ssmBatchClient.queryObjectList("getDefaultCarrierAccount", param);
    }

    /**
     * @param param NMZC611001PMsg
     * @return CarrierInfoBean
     */
    private CarrierInfoBean getDefaultCarrierAccountForMultipleForLoc(NMZC611001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("locNum", pMsg.locNum.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());
        param.put("vndCd", pMsg.vndCd_I.getValue());
        param.put("lineBizTpCd", pMsg.lineBizTpCd.getValue());
        param.put("dsBizAreaCd", pMsg.dsBizAreaCd.getValue());

        return (CarrierInfoBean) ssmBatchClient.queryObject("getDefaultCarrierAccountForMultiple", param);
    }
    // 2018/12/06 QC#29315 Add End
}
