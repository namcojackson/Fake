/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC036001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.api.NSZ.NSZC036001.constant.NSZC036001Constant.*;

import java.math.BigDecimal;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_CONFIG_MSTRTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NSZC036001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 * Solution Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tsuchida      Create          N/A
 * 2016/06/21   Hitachi         T.Tomita        Update          QC#6999
 *</pre>
 */
public class NSZC036001 extends S21ApiCommonBase {

    /**
     * NSZC036001
     */
    public NSZC036001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC036001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC036001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC036001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }


    /**
     * execute Solution Update API.
     * @param param NSZC036001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC036001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        ZYPTableUtil.clear(param.xxMsgIdList);

        if (!checkInputParam(msgMap)) {
            msgMap.flush();
            return;
        }
        if (!checkMachSts(msgMap)) {
            msgMap.flush();
            return;
        }
        executeForUpdate(msgMap);
        msgMap.flush();
    }

    /**
     * Check Input Parameter.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkInputParam(S21ApiMessageMap msgMap) {
        NSZC036001PMsg pMsg = (NSZC036001PMsg) msgMap.getPmsg();

        // Mandatory Check
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0001E.toString());
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0580E.toString());
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcSlnUpdPsnCd)) {
            msgMap.addXxMsgId(MESSAGE_ID.NSZM0581E.toString());
            return false;
        }
        for (int i = 0; i < pMsg.xxSvcConfigList.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.xxSvcConfigList.no(i).svcConfigMstrPk)) {
                msgMap.addXxMsgId(MESSAGE_ID.NSZM0570E.toString());
                return false;
            }
            if (!ZYPCommonFunc.hasValue(pMsg.xxSvcConfigList.no(i).delFlg)) {
                msgMap.addXxMsgId(MESSAGE_ID.NSZM0571E.toString());
                return false;
            }
            if (pMsg.xxSvcConfigList.no(i).svcConfigMstrPk.getValue().compareTo(BigDecimal.ZERO) < 0) {
                msgMap.addXxMsgId(MESSAGE_ID.NSZM0572E.toString());
                return false;
            }
        }
        // Relation Check
        if (!ZYPCommonFunc.hasValue(pMsg.svcSlnSq)) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcSlnNm)) {
                msgMap.addXxMsgId(MESSAGE_ID.NSZM0573E.toString());
                return false;
            }
            for (int i = 0; i < pMsg.xxSvcConfigList.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxSvcConfigList.no(i).delFlg.getValue())) {
                    msgMap.addXxMsgId(MESSAGE_ID.NSZM0574E.toString());
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check Machine Status.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkMachSts(S21ApiMessageMap msgMap) {
        NSZC036001PMsg pMsg = (NSZC036001PMsg) msgMap.getPmsg();
        SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray;

        for (int i = 0; i < pMsg.xxSvcConfigList.getValidCount(); i++) {
            if (ZYPConstant.FLG_OFF_N.equals(pMsg.xxSvcConfigList.no(i).delFlg.getValue())) {
                svcMachMstrTMsgArray = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.xxSvcConfigList.no(i).svcConfigMstrPk.getValue());
                for (int j = 0; j < svcMachMstrTMsgArray.getValidCount(); j++) {
                    if (svcMachMstrTMsgArray.no(j).svcMachMstrStsCd.getValue().equals(SVC_MACH_MSTR_STS.TERMINATED)) {
                        msgMap.addXxMsgIdWithPrm(MESSAGE_ID.NSZM0575E.toString(), new String[] {pMsg.xxSvcConfigList.no(i).svcConfigMstrPk.getValue().toString()});
                        return false;
                    }
                    if (svcMachMstrTMsgArray.no(j).svcMachMstrStsCd.getValue().equals(SVC_MACH_MSTR_STS.DUPLICATE)) {
                        msgMap.addXxMsgIdWithPrm(MESSAGE_ID.NSZM0575E.toString(), new String[] {pMsg.xxSvcConfigList.no(i).svcConfigMstrPk.getValue().toString()});
                        return false;
                    }
                    if (svcMachMstrTMsgArray.no(j).svcMachUsgStsCd.getValue().equals(SVC_MACH_USG_STS.IN_INVENTORY)) {
                        msgMap.addXxMsgIdWithPrm(MESSAGE_ID.NSZM0576E.toString(), new String[] {pMsg.xxSvcConfigList.no(i).svcConfigMstrPk.getValue().toString()});
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Check Param for Update.
     * @param msgMap S21ApiMessageMap
     * @param tMsg SVC_CONFIG_MSTRTMsg
     * @param pMsg NSZC036001PMsg
     * @param svcSlnSq BigDecimal
     * @param svcConfigMstrPk String
     * @return true:OK, false:NG
     */
    private boolean checkParamForUpdate(S21ApiMessageMap msgMap, SVC_CONFIG_MSTRTMsg tMsg, NSZC036001PMsg pMsg, BigDecimal svcSlnSq, String svcConfigMstrPk) {

        if (tMsg == null) {
            msgMap.addXxMsgIdWithPrm(MESSAGE_ID.NSZM0578E.toString(), new String[] {svcConfigMstrPk});
            return false;
        // START 2016/06/21 T.Tomita [QC#6999, DEL]
//        } else if (ZYPCommonFunc.hasValue(tMsg.svcSlnSq)) {
//            if (!ZYPCommonFunc.hasValue(pMsg.svcSlnSq)) {
//                if (tMsg.svcSlnSq.getValue().compareTo(svcSlnSq) == 0) {
//                    //Check Insert
//                    msgMap.addXxMsgIdWithPrm(MESSAGE_ID.NSZM0577E.toString(), new String[] {svcConfigMstrPk});
//                    return false;
//                }
//            } else {
//                if (tMsg.svcSlnSq.getValue().compareTo(svcSlnSq) != 0) {
//                    //Check Update and Delete
//                    msgMap.addXxMsgIdWithPrm(MESSAGE_ID.NSZM0577E.toString(), new String[] {svcConfigMstrPk});
//                    return false;
//                }
//            }
        // END 2016/06/21 T.Tomita [QC#6999, DEL]
        }
        return true;
    }

    /**
     * Update SVC_CONFIG_MSTR.
     * @param msgMap S21ApiMessageMap
     */
    private void executeForUpdate(S21ApiMessageMap msgMap) {
        NSZC036001PMsg pMsg = (NSZC036001PMsg) msgMap.getPmsg();
        SVC_CONFIG_MSTRTMsgArray svcConfigMstrTMsgArray;
        BigDecimal svcSlnSq;
        String svcSlnNm = "";

        //Get Service Solution Sequence
        if (!ZYPCommonFunc.hasValue(pMsg.svcSlnSq)) {
            svcSlnSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SLN_SQ);
        } else {
            svcSlnSq = pMsg.svcSlnSq.getValue();
        }

        //Get Service Config Master List
        svcConfigMstrTMsgArray = getSvcConfigMstrList(pMsg.glblCmpyCd.getValue(), svcSlnSq);
        if (!ZYPCommonFunc.hasValue(pMsg.svcSlnNm)) {
            if (svcConfigMstrTMsgArray.getValidCount() > 0) {
                svcSlnNm = svcConfigMstrTMsgArray.no(0).svcSlnNm.getValue();
            }
        } else {
            svcSlnNm = pMsg.svcSlnNm.getValue();
        }

        //Update Service Config Master
        for (int i = 0; i < pMsg.xxSvcConfigList.getValidCount(); i++) {
            SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = getSvcConfigMstrByKeyForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.xxSvcConfigList.no(i).svcConfigMstrPk.getValue());
            if (!checkParamForUpdate(msgMap, svcConfigMstrTMsg, pMsg, svcSlnSq, pMsg.xxSvcConfigList.no(i).svcConfigMstrPk.getValue().toString())) {
                return;
            }
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxSvcConfigList.no(i).delFlg.getValue())) {
                svcConfigMstrTMsg.svcSlnSq.clear();
                svcConfigMstrTMsg.svcSlnNm.clear();
                svcConfigMstrTMsg.svcSlnCratDt.clear();
                svcConfigMstrTMsg.svcSlnCratPsnCd.clear();
                svcConfigMstrTMsg.svcSlnUpdDt.clear();
                svcConfigMstrTMsg.svcSlnUpdPsnCd.clear();
            } else {
                svcConfigMstrTMsg.svcSlnSq.setValue(svcSlnSq);
                svcConfigMstrTMsg.svcSlnNm.setValue(svcSlnNm);
                if (!ZYPCommonFunc.hasValue(svcConfigMstrTMsg.svcSlnCratDt)) {
                    svcConfigMstrTMsg.svcSlnCratDt.setValue(pMsg.slsDt.getValue());
                }
                if (!ZYPCommonFunc.hasValue(svcConfigMstrTMsg.svcSlnCratPsnCd)) {
                    svcConfigMstrTMsg.svcSlnCratPsnCd.setValue(pMsg.svcSlnUpdPsnCd.getValue());
                }
                svcConfigMstrTMsg.svcSlnUpdDt.setValue(pMsg.slsDt.getValue());
                svcConfigMstrTMsg.svcSlnUpdPsnCd.setValue(pMsg.svcSlnUpdPsnCd.getValue());
            }
            S21ApiTBLAccessor.update(svcConfigMstrTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcConfigMstrTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(MESSAGE_ID.NSZM0579E.toString(), new String[] {pMsg.xxSvcConfigList.no(i).svcConfigMstrPk.getValue().toString()});
            }
        }
        pMsg.svcSlnSq.setValue(svcSlnSq);
        msgMap.setPmsg(pMsg);
    }

    /**
     * Get SVC_MACH_MSTR.
     * @param glblCmpyCd Global Company Code
     * @param svcConfigMstrPk Service Config Master Primary Key
     * @return SVC_MACH_MSTRTMsgArray
     */
    private static SVC_MACH_MSTRTMsgArray getSvcMachMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        inMsg.setConditionValue("svcMachMstrStsCd01A", SVC_MACH_MSTR_STS.TERMINATED);
        inMsg.setConditionValue("svcMachMstrStsCd01B", SVC_MACH_MSTR_STS.DUPLICATE);
        inMsg.setConditionValue("svcMachUsgStsCd01", SVC_MACH_USG_STS.IN_INVENTORY);
        return (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    /**
     * Get SVC_CONFIG_MSTR List.
     * @param glblCmpyCd Global Company Code
     * @param svcSlnSq Service Solution Sequence
     * @return SVC_CONFIG_MSTRTMsgArray
     */
    private static SVC_CONFIG_MSTRTMsgArray getSvcConfigMstrList(String glblCmpyCd, BigDecimal svcSlnSq) {
        SVC_CONFIG_MSTRTMsg inMsg = new SVC_CONFIG_MSTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcSlnSq01", svcSlnSq);
        return (SVC_CONFIG_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    /**
     * Get SVC_CONFIG_MSTRTMsg for update
     * @param glblCmpyCd Global Company Code
     * @param svcConfigMstrPk Service Config Master Primary Key
     * @return SVC_CONFIG_MSTRTMsg
     */
    private static SVC_CONFIG_MSTRTMsg getSvcConfigMstrByKeyForUpdate(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        SVC_CONFIG_MSTRTMsg inMsg = new SVC_CONFIG_MSTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcConfigMstrPk, svcConfigMstrPk);
        return (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
    }
}
