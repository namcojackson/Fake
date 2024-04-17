/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC111001;

import static com.canon.cusa.s21.api.NSZ.NSZC111001.constant.NSZC111001Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NSZC111001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Contract Inquiry API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2017   Hitachi         K.Ochiai        Create          N/A
 * 04/25/2017   Hitachi         T.Tomita        Update          RS#8380
 *</pre>
 */

public class NSZC111001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NSZC111001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsgList List<NSZC111001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC111001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NSZC111001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * Execute API.
     * @param pMsg NSZC111001PMsg
     * @param onBatchType ONLINE/BATCH
     */
    public void execute(NSZC111001PMsg pMsg, ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        ZYPTableUtil.clear(pMsg.xxMsgIdList);

        if (!checkMandatory(msgMap)) {
            msgMap.flush();
            return;
        }
        searchTrgtContr(msgMap);
        msgMap.flush();
    }

    /**
     * Check Mandatory.
     * @param msgMap S21ApiMessageMap
     * @return true:OK, false:NG
     */
    private boolean checkMandatory(S21ApiMessageMap msgMap) {
        NSZC111001PMsg pMsg = (NSZC111001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxModeCd)) {
            msgMap.addXxMsgId(NSZM0122E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NSZM0002E);
            return false;
        }
        return true;
    }

    /**
     * Search Target Contract.
     * @param msgMap S21ApiMessageMap
     */
    private void searchTrgtContr(S21ApiMessageMap msgMap) {
        NSZC111001PMsg pMsg = (NSZC111001PMsg) msgMap.getPmsg();
        String[] contrStsCd = null;
        String strContrStsCd = null;

        String contrBaseDt = null;
        if (ZYPCommonFunc.hasValue(pMsg.procDt)) {
            contrBaseDt = pMsg.procDt.getValue();
        } else {
            contrBaseDt = pMsg.slsDt.getValue();
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("contrBaseDt", contrBaseDt);

        if (BILL_WITH_EQUIP_MODE.equals(pMsg.xxModeCd.getValue())) {
            String billWithEquipFlg = DEF_BILL_WITH_EQUIP_FLG;
            String billWithEquipInvdFlg = DEF_BILL_WITH_EQUIP_INVD_FLG;

            if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
                msgMap.addXxMsgId(NSZM0402E);
                return;
            }
            if (ZYPCommonFunc.hasValue(pMsg.billWithEquipFlg)) {
                billWithEquipFlg = pMsg.billWithEquipFlg.getValue();
            }
            if (ZYPCommonFunc.hasValue(pMsg.billWithEquipInvdFlg)) {
                billWithEquipInvdFlg = pMsg.billWithEquipInvdFlg.getValue();
            }
            strContrStsCd = ZYPCodeDataUtil.getVarCharConstValue("BILL_WITH_EQUIP_CONTR_STS", pMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(strContrStsCd)) {
                contrStsCd = strContrStsCd.split(",");
            }

            param.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
            param.put("cpoDtlLineNum", pMsg.cpoDtlLineNum.getValue());
            param.put("cpoDtlLineSubNum", pMsg.cpoDtlLineSubNum.getValue());
            param.put("billWithEquipFlg", billWithEquipFlg);
            param.put("billWithEquipInvdFlg", billWithEquipInvdFlg);
            param.put("stsCdList", contrStsCd);
            List<Map<String, Object>> srchRsltList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("srchContrBillWithEquip", param);

            for (Map<String, Object> srchRsltMap : srchRsltList) {
                setxxNonWtyContrList(pMsg, srchRsltMap);
            }

        } else if (ASCC_CODE_02.equals(pMsg.xxModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                msgMap.addXxMsgId(NSZM0074E);
                return;
            }
            strContrStsCd = ZYPCodeDataUtil.getVarCharConstValue("NSZC1110_ASCC_CONTR_STS", pMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(strContrStsCd)) {
                contrStsCd = strContrStsCd.split(",");
            }

            param.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
            param.put("stsCdList", contrStsCd);

            List<Map<String, Object>> srchRsltList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("srchContrAscc", param);

            for (Map<String, Object> srchRsltMap : srchRsltList) {
                if (DS_CONTR_CATG.WARRANTY.equals((String) srchRsltMap.get("DS_CONTR_CATG_CD"))) {
                    setxxWtyContrList(pMsg, srchRsltMap);
                } else {
                    setxxNonWtyContrList(pMsg, srchRsltMap);
                }
            }
        } else {
            msgMap.addXxMsgId(NSZM0175E);
        }
    }

    private void setxxWtyContrList(NSZC111001PMsg pMsg, Map<String, Object> wtyMap) {

        int wtyCnt = pMsg.xxWtyContrList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxWtyContrList.no(wtyCnt).dsContrPk, (BigDecimal) wtyMap.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxWtyContrList.no(wtyCnt).dsContrNum, (String) wtyMap.get("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxWtyContrList.no(wtyCnt).dsContrSqNum, (String) wtyMap.get("DS_CONTR_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxWtyContrList.no(wtyCnt).dsContrCatgCd, (String) wtyMap.get("DS_CONTR_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxWtyContrList.no(wtyCnt).dsContrDtlPk, (BigDecimal) wtyMap.get("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxWtyContrList.no(wtyCnt).svcMachMstrPk, (BigDecimal) wtyMap.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxWtyContrList.no(wtyCnt).contrEffFromDt, (String) wtyMap.get("CONTR_EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxWtyContrList.no(wtyCnt).contrEffThruDt, (String) wtyMap.get("CONTR_EFF_THRU_DT"));

        wtyCnt++;
        pMsg.xxWtyContrList.setValidCount(wtyCnt);
    }

    private void setxxNonWtyContrList(NSZC111001PMsg pMsg, Map<String, Object> nonWtyMap) {

        int nonWtyCnt = pMsg.xxNonWtyContrList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxNonWtyContrList.no(nonWtyCnt).dsContrPk, (BigDecimal) nonWtyMap.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxNonWtyContrList.no(nonWtyCnt).dsContrNum, (String) nonWtyMap.get("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxNonWtyContrList.no(nonWtyCnt).dsContrSqNum, (String) nonWtyMap.get("DS_CONTR_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxNonWtyContrList.no(nonWtyCnt).dsContrCatgCd, (String) nonWtyMap.get("DS_CONTR_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxNonWtyContrList.no(nonWtyCnt).dsContrDtlPk, (BigDecimal) nonWtyMap.get("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxNonWtyContrList.no(nonWtyCnt).svcMachMstrPk, (BigDecimal) nonWtyMap.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxNonWtyContrList.no(nonWtyCnt).contrEffFromDt, (String) nonWtyMap.get("CONTR_EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxNonWtyContrList.no(nonWtyCnt).contrEffThruDt, (String) nonWtyMap.get("CONTR_EFF_THRU_DT"));

        nonWtyCnt++;
        pMsg.xxNonWtyContrList.setValidCount(nonWtyCnt);

    }
}
