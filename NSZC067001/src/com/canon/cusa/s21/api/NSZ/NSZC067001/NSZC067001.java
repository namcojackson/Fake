/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC067001;

import static com.canon.cusa.s21.api.NSZ.NSZC067001.constant.NSZC067001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import business.db.SVC_MODTMsg;
import business.db.SVC_MODTMsgArray;
import business.db.SVC_MOD_DTLTMsg;
import business.db.SVC_MOD_DTLTMsgArray;
import business.db.SVC_MOD_STSTMsg;

import business.parts.NSZC067001PMsg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;

import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SerialRangeCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Modification Status Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2015   Hitachi         Y.Tsuchimoto    Create          NA#Modification Status Update API
 * 01/13/2017   Hitachi         T.Mizuki        Update          QC#17018
 * 2017/11/10   Hitachi         K.Kojima        Update          QC#22523
 * 2018/08/29   Hitachi         K.Kitachi       Update          QC#27882
 * 2021/10/25   CITS            R.Cabral        Update          QC#58668
 * </pre>
 */
public class NSZC067001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NSZC067001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NSZC067001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NSZC067001PMsg param, final ONBATCH_TYPE onBatchType) {

        if (param != null) {
            ZYPEZDItemValueSetter.setValue(param.svcModStsPk, (BigDecimal) null);
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (checkParameter(msgMap)) {
            updateModificationStatus(msgMap);
        }
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC067001PMsg pMsg = (NSZC067001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NZZM0012E, PARAM_GLBL_CMPY_CD);
        mandatoryCheck(msgMap, pMsg.svcModPlnId, NZZM0012E, PARAM_SVC_MOD_PLN_ID);
        mandatoryCheck(msgMap, pMsg.svcTaskNum, NZZM0012E, PARAM_SVC_TASK_NUM);
        mandatoryCheck(msgMap, pMsg.svcMachMstrPk, NZZM0012E, PARAM_SVC_MACH_MSTR_PK);
        mandatoryCheck(msgMap, pMsg.mdseCd, NZZM0012E, PARAM_MDSE_CD);
        mandatoryCheck(msgMap, pMsg.serNum, NZZM0012E, PARAM_SER_NUM);
        mandatoryCheck(msgMap, pMsg.svcModProcStsCd, NZZM0012E, PARAM_SVC_MOD_PROC_STS_CD);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId, String itemName) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgIdWithPrm(messageId, new String[] {itemName });
        }
    }

    private void updateModificationStatus(S21ApiMessageMap msgMap) {
        NSZC067001PMsg pMsg = (NSZC067001PMsg) msgMap.getPmsg();
        // START 2021/10/25 R.Cabral [QC#58668, ADD]
        BigDecimal svcModDtlPk = null;
        // END   2021/10/25 R.Cabral [QC#58668, ADD]

        Map<String, Object> svcModSts = (Map<String, Object>) getModificationStatus(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.svcTaskNum.getValue(), pMsg.svcModPlnId.getValue());
        if (svcModSts != null) {
            // Update
            // Serial# Range Check
            // START 2021/10/25 R.Cabral [QC#58668, MOD]
            // if (!NSXC002001SerialRangeCheck.isSerialRangeCheck(pMsg.glblCmpyCd.getValue(), pMsg.svcModPlnId.getValue(), pMsg.mdseCd.getValue(), pMsg.serNum.getValue())) {
            svcModDtlPk = (BigDecimal) svcModSts.get(CLM_SVC_MOD_DTL_PK);
            if (!NSXC002001SerialRangeCheck.isSerialRangeCheck(pMsg.glblCmpyCd.getValue(), pMsg.svcModPlnId.getValue(), pMsg.mdseCd.getValue(), pMsg.serNum.getValue(), svcModDtlPk)) {
            // END   2021/10/25 R.Cabral [QC#58668, MOD]
                msgMap.addXxMsgId(NSZM0623E);
                return;
            }

            // Update SVC_MOD_STS
            SVC_MOD_STSTMsg svcModStsTMsg = new SVC_MOD_STSTMsg();
            setValue(svcModStsTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(svcModStsTMsg.svcModStsPk, (BigDecimal) svcModSts.get(CLM_SVC_MOD_STS_PK));
            setValue(svcModStsTMsg.svcModPk, (BigDecimal) svcModSts.get(CLM_SVC_MOD_PK));
            setValue(svcModStsTMsg.svcModDtlPk, (BigDecimal) svcModSts.get(CLM_SVC_MOD_DTL_PK));
            setValue(svcModStsTMsg.svcMachMstrPk, (BigDecimal) svcModSts.get(CLM_SVC_MACH_MSTR_PK));
            setValue(svcModStsTMsg.svcTaskNum, (String) svcModSts.get(CLM_SVC_TASK_NUM));
            setValue(svcModStsTMsg.svcModOptCd, (String) svcModSts.get(CLM_SVC_MOD_OPT_CD));
            setValue(svcModStsTMsg.svcModOptDt, (String) svcModSts.get(CLM_SVC_MOD_OPT_DT));

            setValue(svcModStsTMsg.svcModProcStsCd, pMsg.svcModProcStsCd.getValue());
            S21ApiTBLAccessor.update(svcModStsTMsg);
            if (!RETURN_CD_NORMAL.equals(svcModStsTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0625E, new String[] {svcModStsTMsg.getTableName() });
            }

        } else {
            // Insert
            SVC_MODTMsg svcModTMsg = getSvcMod(pMsg.glblCmpyCd.getValue(), pMsg.svcModPlnId.getValue());
            if (svcModTMsg == null) {
                msgMap.addXxMsgId(NSZM0622E);
                return;
            }

            // START 2018/08/29 K.Kitachi [QC#27882, MOD]
            // START 2021/10/25 R.Cabral [QC#58668, MOD]
            // String mdseCdForArg = getOrigMdseCd(pMsg.glblCmpyCd.getValue(), pMsg.mdseCd.getValue());
            String mdseCdForArg = pMsg.mdseCd.getValue();
            // END   2021/10/25 R.Cabral [QC#58668, MOD]
            SVC_MOD_DTLTMsg svcModDtlTMsg = getSvcModDtl(pMsg.glblCmpyCd.getValue(), svcModTMsg.svcModPk.getValue(), mdseCdForArg);
            // END 2018/08/29 K.Kitachi [QC#27882, MOD]
            if (svcModDtlTMsg == null) {
                // mod start 2017/01/13 CSA QC#17018
                if (mdseCdForArg.length() > MDSE_CD_LENGTH) {
                    mdseCdForArg = mdseCdForArg.substring(0, MDSE_CD_LENGTH);
                    svcModDtlTMsg = getSvcModDtl(pMsg.glblCmpyCd.getValue(), svcModTMsg.svcModPk.getValue(), mdseCdForArg);
                }
                if (svcModDtlTMsg == null) {
                    // START 2021/10/25 R.Cabral [QC#58668, MOD]
                    // msgMap.addXxMsgId(NSZM0622E);
                    // return;
                    if (svcModDtlTMsg == null) {
                        mdseCdForArg = getOrigMdseCd(pMsg.glblCmpyCd.getValue(), pMsg.mdseCd.getValue());
                        svcModDtlTMsg = getSvcModDtl(pMsg.glblCmpyCd.getValue(), svcModTMsg.svcModPk.getValue(), mdseCdForArg);
                        if (svcModDtlTMsg == null) {
                            msgMap.addXxMsgId(NSZM0622E);
                            return;
                        }
                    }
                    // END   2021/10/25 R.Cabral [QC#58668, MOD]
                }
//                mdseCdForArg = pMsg.mdseCd.getValue().substring(0, MSDE_CD_LENGTH);
                // mod end 2017/01/13 CSA QC#17018
            }
            // START 2021/10/25 R.Cabral [QC#58668, MOD]
            // Serial# Range Check
            // if (!NSXC002001SerialRangeCheck.isSerialRangeCheck(pMsg.glblCmpyCd.getValue(), pMsg.svcModPlnId.getValue(), mdseCdForArg, pMsg.serNum.getValue())) {
            svcModDtlPk = svcModDtlTMsg.svcModDtlPk.getValue();
            if (!NSXC002001SerialRangeCheck.isSerialRangeCheck(pMsg.glblCmpyCd.getValue(), pMsg.svcModPlnId.getValue(), mdseCdForArg, pMsg.serNum.getValue(), svcModDtlPk)) {
            // END   2021/10/25 R.Cabral [QC#58668, MOD]
                msgMap.addXxMsgId(NSZM0623E);
                return;
            }

            // Insert SVC_MEMO_STS
            // START 2017/11/10 K.Kojima [QC#22523,MOD]
            // BigDecimal createSvcModStsPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MOD_SQ);
            BigDecimal createSvcModStsPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MOD_STS_SQ);
            // END 2017/11/10 K.Kojima [QC#22523,MOD]

            SVC_MOD_STSTMsg svcModStsTMsg = new SVC_MOD_STSTMsg();
            setValue(svcModStsTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            setValue(svcModStsTMsg.svcModStsPk, createSvcModStsPk);
            setValue(svcModStsTMsg.svcModPk, svcModTMsg.svcModPk.getValue());
            setValue(svcModStsTMsg.svcModDtlPk, svcModDtlTMsg.svcModDtlPk.getValue());
            setValue(svcModStsTMsg.svcMachMstrPk, pMsg.svcMachMstrPk.getValue());
            setValue(svcModStsTMsg.svcTaskNum, pMsg.svcTaskNum.getValue());
            setValue(svcModStsTMsg.svcModProcStsCd, pMsg.svcModProcStsCd.getValue());

            S21ApiTBLAccessor.create(svcModStsTMsg);
            if (!RETURN_CD_NORMAL.equals(svcModStsTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSZM0626E, new String[] {svcModStsTMsg.getTableName() });
            }

            setValue(pMsg.svcModStsPk, createSvcModStsPk);
        }
    }

    private Map<String, Object> getModificationStatus(String glblCmpyCd, BigDecimal svcMachMstrPk, String svcTaskNum, String svcModPlnId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("svcTaskNum", svcTaskNum);
        param.put("svcModPlnId", svcModPlnId);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getModificationStatus", param);
    }

    private SVC_MODTMsg getSvcMod(String glblCmpyCd, String svcModPlnId) {
        SVC_MODTMsg param = new SVC_MODTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcModPlnId01", svcModPlnId);

        SVC_MODTMsgArray result = (SVC_MODTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_MODTMsg) result.get(0);
        }
        return null;
    }

    private SVC_MOD_DTLTMsg getSvcModDtl(String glblCmpyCd, BigDecimal svcModPk, String mdseCd) {
        SVC_MOD_DTLTMsg param = new SVC_MOD_DTLTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("svcModPk01", svcModPk);
        param.setConditionValue("mdseCd01", mdseCd);

        SVC_MOD_DTLTMsgArray result = (SVC_MOD_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {
            return (SVC_MOD_DTLTMsg) result.get(0);
        }
        return null;
    }

    // START 2018/08/29 K.Kitachi [QC#27882, ADD]
    private String getOrigMdseCd(String glblCmpyCd, String relnMdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("relnMdseCd", relnMdseCd);
        List<String> mdseItemRelnTpCdList = new ArrayList<String>();
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REFURBISHED);
        mdseItemRelnTpCdList.add(MDSE_ITEM_RELN_TP.REMANUFACTURED);
        param.put("mdseItemRelnTpCdList", mdseItemRelnTpCdList);

        String origMdseCd = (String) this.ssmBatchClient.queryObject("getOrigMdseCd", param);
        if (hasValue(origMdseCd)) {
            return origMdseCd;
        }
        return relnMdseCd;
    }
    // END 2018/08/29 K.Kitachi [QC#27882, ADD]
}
