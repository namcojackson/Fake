/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC400001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PKTTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.parts.NLZC400001PMsg;
import business.parts.NWZC003001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC400001.constant.NLZC400001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_TASK;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * PKT Status Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2015   CSAI            K.Lee           Create          Initial
 * 11/01/2016   CSAI            K.Lee           Update          QC#15667
 * 07/20/2017   CITS            T.Tokutomi      Update          QC#19947
 * 2018/02/14   CITS            T.Hakodate      Update          QC#22613
 * 2018/06/20   CITS            K.Ogino         Update          QC#26132
 * 03/10/2020   Fujitsu         R.Nakamura      Update          QC#56080
 *</pre>
 */
public class NLZC400001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** S21ApiMessageMap */
    private ONBATCH_TYPE onBatchType = null;

    public NLZC400001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute This can be called method from external class.
     * @param NLZC401001PMsg parameter
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(NLZC400001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);
        this.onBatchType = onBatchType;
        doProcess();
        msgMap.flush();
    }

    /**
     * execute This can be called method from external class.
     * @param List<NLZC401001PMsg> list
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(List<NLZC400001PMsg> list, final ONBATCH_TYPE onBatchType) {
        for (NLZC400001PMsg param : list) {
            execute(param, onBatchType);
        }
    }

    /**
     * doProcess This is Main process Method.
     */
    private void doProcess() {

        NLZC400001PMsg pMsg = (NLZC400001PMsg) msgMap.getPmsg();

        // #######################################
        // 1-1. Mandatory Check
        // #######################################
        if (!checkMandatory(pMsg)) {
            return;
        }

        List<NWZC003001PMsg> shpgPlnUpdList = new ArrayList<NWZC003001PMsg>();
        HashMap<String, Integer> shpgPlnNumMap = new HashMap<String, Integer>();
        for (int i = 0; i < pMsg.pktStsUpdateInfo.getValidCount(); i++) {

            Map<String, Object> soMap = getSo(pMsg.glblCmpyCd.getValue(), pMsg.pktStsUpdateInfo.no(i).soNum.getValue(), pMsg.pktStsUpdateInfo.no(i).soSlpNum.getValue(), pMsg.pktStsUpdateInfo.no(i).dsSoLineStsCd.getValue());
            Map<String, Object> pktStsMap = getPktSts(pMsg.glblCmpyCd.getValue(), pMsg.pktStsUpdateInfo.no(i).dsSoLineStsCd.getValue());

            if (soMap == null) {
                setErrMsg(pMsg, i, NLZC400001Constant.NLAM1295E, new String[] {});
                continue;
            }

            String shpgStsCd = null;
            String pktStsCd = null;
            String shpgPlnNum = (String) soMap.get("SHPG_PLN_NUM");
            // QC#22613 T.Hakodate ADD START
            String wmsTaskCd = pMsg.pktStsUpdateInfo.no(i).wmsTaskCd.getValue();
            String wmsOrdStsCd = pMsg.pktStsUpdateInfo.no(i).wmsOrdStsCd.getValue();
            // QC#22613 T.Hakodate ADD END

            if (pktStsMap != null) {
                shpgStsCd = (String) pktStsMap.get("SHPG_STS_CD");
                pktStsCd = (String) pktStsMap.get("PKT_STS_CD");
            }

            SHPG_ORD_DTLTMsg dtlInTMsg = new SHPG_ORD_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(dtlInTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlInTMsg.soNum, pMsg.pktStsUpdateInfo.no(i).soNum);
            ZYPEZDItemValueSetter.setValue(dtlInTMsg.soSlpNum, pMsg.pktStsUpdateInfo.no(i).soSlpNum);
            SHPG_ORD_DTLTMsg dtlOutTMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dtlInTMsg);

            if (dtlOutTMsg == null) {
                setErrMsg(pMsg, i, NLZC400001Constant.NLAM1295E, new String[] {new SHPG_ORD_DTLTMsg().getTableName() });
                continue;
            }

            // QC#24653 T.Hakodate MOD START
            if (!(WMS_TASK.LSC.equals(wmsTaskCd) && WMS_ORD_STS.PICK.equals(wmsOrdStsCd))) {
                ZYPEZDItemValueSetter.setValue(dtlOutTMsg.dsSoLineStsCd, (String) soMap.get("DS_SO_LINE_STS_CD"));

                if (ZYPCommonFunc.hasValue(shpgStsCd)) {
                    if (shpgStsCd.compareTo(dtlOutTMsg.shpgStsCd.getValue()) > 0) {
                        NWZC003001PMsg nwzc003001Pmsg = new NWZC003001PMsg();
                        ZYPEZDItemValueSetter.setValue(nwzc003001Pmsg.glblCmpyCd, pMsg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(nwzc003001Pmsg.shpgPlnNum, shpgPlnNum);
                        if (SHPG_STS.PICKED.equals(shpgStsCd)) {
                            ZYPEZDItemValueSetter.setValue(nwzc003001Pmsg.shpgModeCd, NWZC003001.MODE_PICKED);
                        } else if (SHPG_STS.PACKED.equals(shpgStsCd)) {
                            ZYPEZDItemValueSetter.setValue(nwzc003001Pmsg.shpgModeCd, NWZC003001.MODE_PACKED);
                        } else if (SHPG_STS.STAGED.equals(shpgStsCd)) {
                            ZYPEZDItemValueSetter.setValue(nwzc003001Pmsg.shpgModeCd, NWZC003001.MODE_STAGED);
                        }
                        if (ZYPCommonFunc.hasValue(nwzc003001Pmsg.shpgModeCd)) {
                            shpgPlnUpdList.add(nwzc003001Pmsg);
                            shpgPlnNumMap.put(shpgPlnNum, i);
                        }
                        // QC#22613 T.Hakodate MOD START
                        ZYPEZDItemValueSetter.setValue(dtlOutTMsg.shpgStsCd, shpgStsCd);
                    }
                    // QC#22613 T.Hakodate MOD END
                }
            }
            // QC#24653 T.Hakodate MOD END

            if (DS_SO_LINE_STS.PICK_CONFIRMED.equals(pMsg.pktStsUpdateInfo.no(i).dsSoLineStsCd.getValue())) {
                BigDecimal shpgQty = (BigDecimal) soMap.get("SHPG_QTY");
                BigDecimal pickConfQty = (BigDecimal) soMap.get("PICK_CONF_QTY");

                if (pickConfQty == null) {
                    pickConfQty = BigDecimal.ZERO;
                }

                if (ZYPCommonFunc.hasValue(pMsg.pktStsUpdateInfo.no(i).pktStsQty)) {
                    pickConfQty = pickConfQty.add(pMsg.pktStsUpdateInfo.no(i).pktStsQty.getValue());
                }

                if (pickConfQty.compareTo(shpgQty) > 0) {
                    pickConfQty = shpgQty;
                }

                ZYPEZDItemValueSetter.setValue(dtlOutTMsg.pickConfQty, pickConfQty);
            }

            S21ApiTBLAccessor.update(dtlOutTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlOutTMsg.getReturnCode())) {
                setErrMsg(pMsg, i, NLZC400001Constant.NLAM1295E, new String[] {dtlOutTMsg.getTableName() });
                continue;
            }

            PKTTMsg pktTMsg = new PKTTMsg();
            ZYPEZDItemValueSetter.setValue(pktTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pktTMsg.soNum, pMsg.pktStsUpdateInfo.no(i).soNum);
            // Mod Start 2020/03/10 QC#56080
//            ZYPEZDItemValueSetter.setValue(pktTMsg.pktSqNum, getMaxPktSqNum(pMsg.glblCmpyCd.getValue(), pMsg.pktStsUpdateInfo.no(i).soNum.getValue()));
            ZYPEZDItemValueSetter.setValue(pktTMsg.pktSqNum, getMaxPktSqNum(pMsg.glblCmpyCd.getValue(), pMsg.pktStsUpdateInfo.no(i).soNum.getValue(), pMsg.pktStsUpdateInfo.no(i).soSlpNum.getValue()));
            // Mod End 2020/03/10 QC#56080
            ZYPEZDItemValueSetter.setValue(pktTMsg.pktStsCd, pktStsCd);
            ZYPEZDItemValueSetter.setValue(pktTMsg.pktStsTs, pMsg.pktStsUpdateInfo.no(i).pktStsTs);
            ZYPEZDItemValueSetter.setValue(pktTMsg.soSlpNum, pMsg.pktStsUpdateInfo.no(i).soSlpNum);
            ZYPEZDItemValueSetter.setValue(pktTMsg.dsSoLineStsCd, pMsg.pktStsUpdateInfo.no(i).dsSoLineStsCd);
            ZYPEZDItemValueSetter.setValue(pktTMsg.pktStsQty, pMsg.pktStsUpdateInfo.no(i).pktStsQty);

            S21ApiTBLAccessor.create(pktTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(pktTMsg.getReturnCode())) {
                setErrMsg(pMsg, i, NLZC400001Constant.NLAM1295E, new String[] {pktTMsg.getTableName() });
                continue;
            }

            // TODO QC#26132
            if (SCE_ORD_TP.ITEM_CHANGE.equals((String) soMap.get("SCE_ORD_TP_CD"))) {
                // Update Dummy Line Shipping Status
                Map<String, Object> soDtlMap = getSoDtlIC(pMsg.glblCmpyCd.getValue(), pMsg.pktStsUpdateInfo.no(i).soNum.getValue());

                if (soDtlMap != null && !soDtlMap.isEmpty()) {

                    SHPG_ORD_DTLTMsg dummySoDtlMsg = new SHPG_ORD_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(dummySoDtlMsg.glblCmpyCd, pMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dummySoDtlMsg.soNum, (String) soDtlMap.get("SO_NUM"));
                    ZYPEZDItemValueSetter.setValue(dummySoDtlMsg.soSlpNum, (String) soDtlMap.get("SO_SLP_NUM"));
                    dummySoDtlMsg = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dummySoDtlMsg);

                    if (dummySoDtlMsg == null) {
                        continue;
                    }

                    ZYPEZDItemValueSetter.setValue(dummySoDtlMsg.shpgStsCd, dtlOutTMsg.shpgStsCd);
                    ZYPEZDItemValueSetter.setValue(dummySoDtlMsg.dsSoLineStsCd, dtlOutTMsg.dsSoLineStsCd);

                    S21ApiTBLAccessor.update(dummySoDtlMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dummySoDtlMsg.getReturnCode())) {
                        continue;
                    }
                }
            }
        }

        // Update Shpg_Pln
        // QC#22613 T.Hakodate MOD START
        if (shpgPlnUpdList.size() > 0) {
        // QC#22613 T.Hakodate MOD   END
            NWZC003001 api = new NWZC003001();
            api.execute(shpgPlnUpdList, ONBATCH_TYPE.BATCH);
            for (int i = 0; i < shpgPlnUpdList.size(); i++) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(shpgPlnUpdList.get(i));
                if (msgList.size() > 0) {
                    for (S21ApiMessage msg : msgList) {
                        String msgId = msg.getXxMsgid();
                        if (msgId.endsWith("E")) {
                            int pMsgIndex = shpgPlnNumMap.get(shpgPlnUpdList.get(i).shpgPlnNum.getValue());
                            setErrMsg(pMsg, pMsgIndex, msgId, null);
                        }
                    }
                }
            }
        }
    }

    /**
     * checkMandatory
     * @return boolean Normal:true, Error:false
     */
    private boolean checkMandatory(NLZC400001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {
            // A value is not set in the required parameter field.
            // Field Name:[Global Company Code] List Index:[0]
            setErrMsg(pMsg, 0, NLZC400001Constant.NLGM0012E, null);
            return false;
        }

        for (int i = 0; i < pMsg.pktStsUpdateInfo.getValidCount(); i++) {
            if (!hasValue(pMsg.pktStsUpdateInfo.no(i).soNum)) {
                // A value is not set in the required parameter field.
                // Field Name:[SO#] List Index:[i]
                setErrMsg(pMsg, i, NLZC400001Constant.NLZM2323E, new String[] {"SO#", String.valueOf(i) });
                return false;
            }

            if (!hasValue(pMsg.pktStsUpdateInfo.no(i).soSlpNum)) {
                // A value is not set in the required parameter field.
                // Field Name:[SO Slip#] List Index:[i]
                setErrMsg(pMsg, i, NLZC400001Constant.NLZM2323E, new String[] {"SO Slip#", String.valueOf(i) });
                return false;
            }

            if (!hasValue(pMsg.pktStsUpdateInfo.no(i).dsSoLineStsCd)) {
                // A value is not set in the required parameter field.
                // Field Name:[GPKT Line Status Code for Update
                // Target] List Index:[i]
                setErrMsg(pMsg, i, NLZC400001Constant.NLZM2323E, new String[] {"PKT Line Status Code for Update Target", String.valueOf(i) });
                return false;
            }

            if (!hasValue(pMsg.pktStsUpdateInfo.no(i).pktStsTs)) {
                // A value is not set in the required parameter field.
                // Field Name:[PKT Status Timestamp] List Index:[i]
                setErrMsg(pMsg, i, NLZC400001Constant.NLZM2323E, new String[] {"PKT Status Timestamp", String.valueOf(i) });
                return false;
            }
        }
        return true;
    }

    /**
     * getIB
     * @param pMsg NLZC401001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getSo(String glblCmpyCd, String soNum, String soSlpNum, String dsSoLineStsCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);
        ssmParam.put("soSlpNum", soSlpNum);
        ssmParam.put("dsSoLineStsCd", dsSoLineStsCd);

        return (Map<String, Object>) ssmClient.queryObject("getSo", ssmParam);
    }

    /**
     * getIB
     * @param pMsg NLZC401001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getPktSts(String glblCmpyCd, String dsSoLineStsCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsSoLineStsCd", dsSoLineStsCd);

        return (Map<String, Object>) ssmClient.queryObject("getPktSts", ssmParam);
    }

    /**
     * getIB
     * @param pMsg NLZC401001PMsg
     * @return Map<String, Object>
     */
    private Map<String, Object> getDetailPktSts(String glblCmpyCd, String soNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);

        return (Map<String, Object>) ssmClient.queryObject("getDetailPktSts", ssmParam);
    }

    /**
     * getIB
     * @param pMsg NLZC401001PMsg
     * @return Map<String, Object>
     */
    // Mod Start 2020/03/10 QC#56080
//    private String getMaxPktSqNum(String glblCmpyCd, String soNum) {
    private String getMaxPktSqNum(String glblCmpyCd, String soNum, String soSlpNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);
        ssmParam.put("soSlpNum", soSlpNum);

        return (String) ssmClient.queryObject("getMaxPktSqNum", ssmParam);
    }
    // Mod End 2020/03/10 QC#56080

    /**
     * setErrMsg
     * @param NLZC401001PMsg pMsg
     * @param String msgId
     */
    private void setErrMsg(NLZC400001PMsg pMsg, int idx, String msgId, String[] errMsgPrm) {
        if (errMsgPrm == null) {
            msgMap.addXxMsgId(msgId);
        } else {
            msgMap.addXxMsgIdWithPrm(msgId, errMsgPrm);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.pktStsUpdateInfo.no(idx).xxRsltStsCd, NLZC400001Constant.RETURN_CODE_ERROR);
        msgMap.flush();
    }

    /**
     * getSoDtlIC
     * @param glblCmpyCd String
     * @param soNum String
     * @return Map<String, Object>
     */
    private Map<String, Object> getSoDtlIC(String glblCmpyCd, String soNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soNum", soNum);
        ssmParam.put("sceOrdTpCd", SCE_ORD_TP.ITEM_CHANGE);

        return (Map<String, Object>) ssmClient.queryObject("getSoDtlIC", ssmParam);
    }
}
