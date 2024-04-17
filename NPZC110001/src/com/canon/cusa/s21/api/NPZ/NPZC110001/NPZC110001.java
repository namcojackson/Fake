/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC110001;

import static com.canon.cusa.s21.api.NPZ.NPZC110001.constant.NPZC110001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import business.db.EDI_PO_DTL_XREFTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_DTLTMsgArray;
import business.parts.NPZC110001PMsg;
import business.parts.NPZC110001_poDetailOutListPMsg;
import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Get PO Line From EDI API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2013   Hitachi         K.Kasai         Create          N/A
 * 08/19/2013   Hitachi         K.Kasai         Update          QC1752
 * 09/27/2013   CSAI            K.Lee           Update          MEX-LC001
 * 10/28/2013   Hitachi         T.Tomita        Update          QC2937
 * 12/23/2013   CSAI            K.Lee           Update          QC3283
 * 12/29/2013   CSAI            K.Lee           Update          QC3301
 * </pre>
 */
public class NPZC110001 extends S21ApiCommonBase {

    /**
     * S21ApiMessageMap
     */
    private S21ApiMessageMap msgMap = null;

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NPZC110001() {
        super();
    }

    /**
     * execute
     * @param param NPZC110001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC110001PMsg param, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(param);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        NPZC110001PMsg pMsg = (NPZC110001PMsg) this.msgMap.getPmsg();
        doProcess(pMsg, onBatchType);
        this.msgMap.flush();
    }

    /**
     * doProcess
     * @param pMsg NPZC110001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    private void doProcess(NPZC110001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        // Check Parameter
        checkParam(pMsg);
        if (this.msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // Main Process
        // START 2013/10/28 T.Tomita [QC2937, UPD]
        if (!setPoOrdNumIn(pMsg)) {
            return;
        }
        // END 2013/10/28 T.Tomita [QC2937, UPD]
        if (!checkPoHdr(pMsg)) {
            return;
        }

        int i = 0;
        for ( ; i < pMsg.poDetailInList.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(pMsg.poDetailInList.no(i).poOrdDtlLineNum) && !ZYPCommonFunc.hasValue(pMsg.poDetailInList.no(i).ediPoOrdDtlLineNum)) {
                setPoLineByMdseCd(pMsg, i, onBatchType);
                if (this.msgMap.getMsgMgr().isXxMsgId()) {
                    pMsg.poDetailOutList.clear();
                    return;
                }
            } else {
                setPoOrdDtlLineNumIn(pMsg, i);
                checkPoLine(pMsg, i, onBatchType);
                if (this.msgMap.getMsgMgr().isXxMsgId()) {
                    pMsg.poDetailOutList.clear();
                    return;
                }
            }
        }
        pMsg.poDetailOutList.setValidCount(i);
    }

    /**
     * checkParam
     * @param pMsg NPZC110001PMsg
     */
    private void checkParam(NPZC110001PMsg pMsg) {
        checkGlblCmpyCd(pMsg);
        checkPoOrdNum(pMsg);
        checkSlsDt(pMsg);

        if (pMsg.poDetailInList != null && pMsg.poDetailInList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.poDetailInList.getValidCount(); i++) {
                checkMdseCd(pMsg.poDetailInList.no(i).mdseCd.getValue(), pMsg.poDetailOutList.no(i));
            }
        }
    }

    /**
     * checkGlblCmpyCd
     * @param pMsg NPZC110001PMsg
     */
    private void checkGlblCmpyCd(NPZC110001PMsg pMsg) {
        if (!hasValue(pMsg.glblCmpyCd)) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0001E));
            msgMap.addXxMsgId(NPZM0001E);
        }
    }

    /**
     * checkPoOrdNum
     * @param pMsg NPZC110001PMsg
     */
    private void checkPoOrdNum(NPZC110001PMsg pMsg) {
        if (!hasValue(pMsg.ediPoOrdNum) && !hasValue(pMsg.poOrdNum_I)) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0159E));
            msgMap.addXxMsgId(NPZM0159E);
        }
    }

    /**
     * checkPoHdr
     * @param pMsg NPZC110001PMsg
     */
    private boolean checkPoHdr(NPZC110001PMsg pMsg) {
        if (!isExistPo(pMsg.glblCmpyCd.getValue(), pMsg.poOrdNum_I.getValue())) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0155E));
            msgMap.addXxMsgId(NPZM0155E);
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_O, pMsg.poOrdNum_I);
            return true;
        }
    }

    /**
     * checkPoLine
     * @param pMsg NPZC110001PMsg
     * @param idx  Integer 
     * @param onBatchType ONBATCH_TYPE
     */
    private boolean checkPoLine(NPZC110001PMsg pMsg, int idx, final ONBATCH_TYPE onBatchType) {
        PO_DTLTMsg tMsg = getPoDtlTMsg(pMsg.glblCmpyCd.getValue(), pMsg.poOrdNum_I.getValue(), pMsg.poDetailInList.no(idx).poOrdDtlLineNum.getValue());
        if (tMsg == null) {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).xxMsgId, NPZM0156E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).poOrdDtlLineNum, tMsg.poOrdDtlLineNum);
        if (!isMatchedMdseCd(pMsg, idx, tMsg.mdseCd.getValue(), onBatchType)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).xxMsgId, NPZM0160E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).mdseCd, tMsg.mdseCd);
        return true;
    }

    /**
     * setPoLineByMdseCd
     * @param pMsg NPZC110001PMsg
     * @param idx Integer
     * @param onBatchType ONBATCH_TYPE
     */
    private boolean setPoLineByMdseCd(NPZC110001PMsg pMsg, int idx, final ONBATCH_TYPE onBatchType) {
        PO_DTLTMsgArray array = getPoDtlTMsgArray(pMsg.glblCmpyCd.getValue(), pMsg.poOrdNum_O.getValue());
        int count = 0;
        String poOrdDtlLineNum = null;
        String mdseCd = null;
        for (int i = 0; i < array.length(); i++) {
            // START Update QC3283
            //if (array.no(i).poStsCd.getValue().equals(PO_STS.CANCELLED) || array.no(i).poStsCd.getValue().equals(PO_STS.CLOSED) ) {
            if (array.no(i).poStsCd.getValue().equals(PO_STS.CANCELLED)) {
            // END Update QC3283
                continue;
            }
            if (isSameMdseCd(pMsg, idx, array.no(i).mdseCd.getValue(), onBatchType)) {
                poOrdDtlLineNum = array.no(i).poOrdDtlLineNum.getValue();
                mdseCd = array.no(i).mdseCd.getValue();
                count++;
            }
        }
        if (count == 1) {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).poOrdDtlLineNum, poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).mdseCd, mdseCd);
            return true;
        } else if (count > 1) {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).xxMsgId, NPZM0142E);
            return false;
        }

        for (int i = 0; i < array.length(); i++) {
            // START Update QC3283
            //if (array.no(i).poStsCd.getValue().equals(PO_STS.CANCELLED) || array.no(i).poStsCd.getValue().equals(PO_STS.CLOSED) ) {
            if (array.no(i).poStsCd.getValue().equals(PO_STS.CANCELLED)) {
            // END Update QC3283
                continue;
            }
            if (isSameEightDigitMdseCd(pMsg, idx, array.no(i).mdseCd.getValue(), onBatchType)) {
                poOrdDtlLineNum = array.no(i).poOrdDtlLineNum.getValue();
                mdseCd = array.no(i).mdseCd.getValue();
                count++;
            }
        }
        if (count == 1) {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).poOrdDtlLineNum, poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).mdseCd, mdseCd);
            return true;
        } else if (count > 1) {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).xxMsgId, NPZM0142E);
            return false;
        }

        for (int i = 0; i < array.length(); i++) {
            if (array.no(i).poStsCd.getValue().equals(PO_STS.CANCELLED)) {
                continue;
            }
            if (isSameSupersedeMdse(pMsg, idx, array.no(i).mdseCd.getValue(), onBatchType)) {
                poOrdDtlLineNum = array.no(i).poOrdDtlLineNum.getValue();
                mdseCd = array.no(i).mdseCd.getValue();
                count++;
            }
        }

        if (count == 1) {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).poOrdDtlLineNum, poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).mdseCd, mdseCd);
            return true;
        } else if (count > 1) {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).xxMsgId, NPZM0142E);
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailOutList.no(idx).xxMsgId, NPZM0158E);
            return false;
        }
    }

    /**
     * checkSlsDt
     * @param pMsg NPZC110001PMsg
     */
    private void checkSlsDt(NPZC110001PMsg pMsg) {
        if (!hasValue(pMsg.slsDt)) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0033E));
            msgMap.addXxMsgId(NPZM0033E);
        }
    }

    /**
     * checkMdseCd
     * @param pMsg NPZC110001PMsg
     */
    private void checkMdseCd(String mdseCode, NPZC110001_poDetailOutListPMsg poDetailOutPMsg) {
        if (!hasValue(mdseCode)) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0020E));
            msgMap.addXxMsgId(NPZM0020E);
            poDetailOutPMsg.xxMsgId.setValue(NPZM0020E);
        }
    }

    /**
     * setPoOrdNumIn
     * @param pMsg NPZC110001PMsg
     */
    // START 2013/10/28 T.Tomita [QC2937, UPD]
    private boolean setPoOrdNumIn(NPZC110001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.poOrdNum_I) && ZYPCommonFunc.hasValue(pMsg.ediPoOrdNum)) {
            List<Map<String, Object>> resultList = getPoOrdNumFromEdiPoDtlXRef(pMsg.glblCmpyCd.getValue(), pMsg.ediPoOrdNum.getValue());
            if (resultList == null || resultList.size() < 1) {
                if (pMsg.getAttr("poOrdNum_I").getDigit() < pMsg.ediPoOrdNum.getValue().length()) {
                    S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0155E));
                    msgMap.addXxMsgId(NPZM0155E);
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_I, pMsg.ediPoOrdNum);
            } else if (resultList.size() == 1) {
                Map<String, Object> result = resultList.get(0);
                String poOrdNum = (String) result.get(DB_PO_ORD_NUM);
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_I, poOrdNum);
            } else {
                S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0157E));
                msgMap.addXxMsgId(NPZM0157E);
                return false;
            }
        }
        return true;
    }
    // START 2013/10/28 T.Tomita [QC2937, UPD]

    /**
     * setPoOrdDtlLineNumIn
     * @param pMsg NPZC110001PMsg
     */
    private void setPoOrdDtlLineNumIn(NPZC110001PMsg pMsg, int idx) {
        if (ZYPCommonFunc.hasValue(pMsg.poDetailInList.no(idx).poOrdDtlLineNum)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(idx).poOrdDtlLineNum, pMsg.poDetailInList.no(idx).poOrdDtlLineNum);
        } else if (ZYPCommonFunc.hasValue(pMsg.poDetailInList.no(idx).ediPoOrdDtlLineNum)) {
            if (ZYPCommonFunc.hasValue(pMsg.ediPoOrdNum)) {
                EDI_PO_DTL_XREFTMsg tMsg = getEdiPoDtlXRefTMsg(pMsg.glblCmpyCd.getValue(), pMsg.ediPoOrdNum.getValue(), pMsg.poDetailInList.no(idx).ediPoOrdDtlLineNum.getValue());
                if (tMsg == null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(idx).poOrdDtlLineNum, convertPoDtlLineNum(pMsg.poDetailInList.no(idx).ediPoOrdDtlLineNum.getValue()));
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(idx).poOrdDtlLineNum, tMsg.poOrdDtlLineNum);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(idx).poOrdDtlLineNum, convertPoDtlLineNum(pMsg.poDetailInList.no(idx).ediPoOrdDtlLineNum.getValue()));
            }
        }
    }

    /**
     * convertPoDtlLineNum
     * @param poDtlLineNum String
     * @return String
     */
    private String convertPoDtlLineNum(String ediPoOrdDtlLineNum) {
        EZDItemAttribute digitPoOrdDtlLineNumEz = new PO_DTLTMsg().getAttr(DB_PO_ORD_DTL_LINE_NUM);
        int digitPoOrdDtlLineNum = digitPoOrdDtlLineNumEz.getDigit();
        if (ediPoOrdDtlLineNum.length() < digitPoOrdDtlLineNum) {
            return ediPoOrdDtlLineNum;
        } else {
            return ediPoOrdDtlLineNum.substring(ediPoOrdDtlLineNum.length() - digitPoOrdDtlLineNum);
        }
    }


   /**
     * convertMdseCd
     * @param mdseCd String
     * @return String
     */
    private String convertMdseCd(String mdseCd) {
        EZDItemAttribute digitMdseCdEz = new ORD_TAKE_MDSETMsg().getAttr(DB_ORD_TAKE_MDSE_CD);
        int digitMdse = digitMdseCdEz.getDigit();
        if (mdseCd.length() < digitMdse) {
            return mdseCd;
        } else {
            return mdseCd.substring(0, digitMdse);
        }
    }

    /**
     * getOrdTakeMdse
     * @param pMsg NPZC110001PMsg
     * @param mdseCd String
     * @return ORD_TAKE_MDSETMsg
     */
    private ORD_TAKE_MDSETMsg getOrdTakeMdse(NPZC110001PMsg pMsg, String mdseCd) {
        ORD_TAKE_MDSETMsg inOrdTakeMdse = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inOrdTakeMdse.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inOrdTakeMdse.ordTakeMdseCd, mdseCd);
        return (ORD_TAKE_MDSETMsg) S21ApiTBLAccessor.findByKey(inOrdTakeMdse);
    }

    /**
     * getPoOrdNumFromEdiPoDtlXRef
     * @param glblCmpyCd String
     * @param ediPoOrdNum String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getPoOrdNumFromEdiPoDtlXRef(String glblCmpyCd, String ediPoOrdNum) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("ediPoOrdNum", ediPoOrdNum);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = ssmBatchClient.queryObjectList("getPoOrdNumFromEdiPoDtlXRef", param);

        return resultList;
    }

    /**
     * isExistPo
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @return boolean
     */
    private boolean isExistPo(String glblCmpyCd, String poOrdNum) {
        // get PO_ORD_LINE from PO
        POTMsg inPoTMsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(inPoTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inPoTMsg.poOrdNum, poOrdNum);

        POTMsg outPoTMsg = (POTMsg) S21ApiTBLAccessor.findByKey(inPoTMsg);

        if (outPoTMsg == null) {
            return false;
        }

        return true;
    }

    /**
     * getPoDtlTMsg
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return PO_DTLTMsg
     */
    private PO_DTLTMsg getPoDtlTMsg(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        // get PO_ORD_LINE from PO
        PO_DTLTMsg inTMsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(inTMsg.poOrdDtlLineNum, poOrdDtlLineNum);

        PO_DTLTMsg outTMsg = (PO_DTLTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

        return outTMsg;
    }

    /**
     * getPoDtlTMsgArray
     * @param String glblCmpyCd
     * @param String poOrdNum
     * @return PO_DTLTMsgArray
     */
    private PO_DTLTMsgArray getPoDtlTMsgArray(String glblCmpyCd, String poOrdNum) {
        // get PO_ORD_LINE from PO
        PO_DTLTMsg inTMsg = new PO_DTLTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("poOrdNum01", poOrdNum);

        PO_DTLTMsgArray outTMsgArray = (PO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);

        return outTMsgArray;
    }

    /**
     * getEdiPoDtlXRefTMsg
     * @param String glblCmpyCd
     * @param String poOrdNum
     * @param String ediPoOrdDtlLineNum
     * @return boolean
     */
    private EDI_PO_DTL_XREFTMsg getEdiPoDtlXRefTMsg(String glblCmpyCd, String poOrdNum, String ediPoOrdDtlLineNum) {

        EDI_PO_DTL_XREFTMsg inTMsg = new EDI_PO_DTL_XREFTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.ediPoOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(inTMsg.ediPoOrdDtlLineNum, ediPoOrdDtlLineNum);

        EDI_PO_DTL_XREFTMsg outTMsg = (EDI_PO_DTL_XREFTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

        return outTMsg;
    }

    /**
     * isMatchedMdseCd
     * @param pMsg NPZC110001PMsg
     * @param idx Integer
     * @param poMdseCd String
     * @param onBatchType ONBATCH_TYPE
     */
    private boolean isMatchedMdseCd(NPZC110001PMsg pMsg, int idx, String poMdseCd, final ONBATCH_TYPE onBatchType) {
        if (isSameMdseCd(pMsg, idx, poMdseCd, onBatchType)) {
            return true;
        }

        if (isSameEightDigitMdseCd(pMsg, idx, poMdseCd, onBatchType)) {
            return true;
        }

        if (isSameSupersedeMdse(pMsg, idx, poMdseCd, onBatchType)) {
            return true;
        }

        return false;
    }

    /**
     * isSameMdseCd
     * @param pMsg NPZC110001PMsg
     * @param idx Integer
     * @param poMdseCd String
     * @param onBatchType ONBATCH_TYPE
     */
    private boolean isSameMdseCd(NPZC110001PMsg pMsg, int idx, String poMdseCd, final ONBATCH_TYPE onBatchType) {
        if (pMsg.poDetailInList.no(idx).mdseCd.getValue().equals(poMdseCd)) {
            return true;
        }

        return false;
    }

    /**
     * isSameEightDigitMdseCd
     * @param pMsg NPZC110001PMsg
     * @param idx Integer
     * @param poMdseCd String
     * @param onBatchType ONBATCH_TYPE
     */
    private boolean isSameEightDigitMdseCd(NPZC110001PMsg pMsg, int idx, String poMdseCd, final ONBATCH_TYPE onBatchType) {

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = getOrdTakeMdse(pMsg, convertMdseCd(poMdseCd));
        if (ordTakeMdseTMsg != null) {
            if (convertMdseCd(poMdseCd).equals(convertMdseCd(pMsg.poDetailInList.no(idx).mdseCd.getValue()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * isSameSupersedeMdse
     * @param pMsg NPZC110001PMsg
     * @param idx Integer
     * @param poMdseCd String
     * @param onBatchType ONBATCH_TYPE
     */
    private boolean isSameSupersedeMdse(NPZC110001PMsg pMsg, int idx, String poMdseCd, final ONBATCH_TYPE onBatchType) {
        NWZC206001 api = new NWZC206001();
        NWZC206001PMsg apiPMsg = setSupersedeApi(pMsg, poMdseCd);
        api.execute(apiPMsg, onBatchType);

        if (addXxMsgIdFromSupersedeApi(apiPMsg)) {
            return false;
        }
//
//        // check supersede MDSE and input parameter MDSE
//        if (isSameSupersedeMdse(pMsg.poDetailInList.no(idx).mdseCd.getValue(), apiPMsg)) {
//            return true;
//        }

        for (int i = 0; i < apiPMsg.A.getValidCount(); i++) {
            if (pMsg.poDetailInList.no(idx).mdseCd.getValue().equals(apiPMsg.A.no(i).mdseCd.getValue())) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * set setSupersedeApi API
     * @param pMsg NPZC110001PMsg
     * @param mdseCd String
     * @return NWZC206001PMsg
     */
    private NWZC206001PMsg setSupersedeApi(NPZC110001PMsg pMsg, String mdseCd) {
        NWZC206001PMsg apiPMsg = new NWZC206001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, BigDecimal.ONE.toString());
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxAvalOrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxAvalPrchFlg, ZYPConstant.FLG_OFF_N);
        return apiPMsg;
    }

    /**
     * addXxMsgIdFromSupersedeApi
     * @param apiPMsg NWZC206001PMsg
     * @return boolean
     */
    private boolean addXxMsgIdFromSupersedeApi(NWZC206001PMsg apiPMsg) {
        if (!apiPMsg.xxMsgIdList.no(0).xxMsgId.isClear()) {
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(NPZM0141E));
            msgMap.addXxMsgId(NPZM0141E);
            return true;
        }
        return false;
    }
}
