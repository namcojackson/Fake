/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC064001;

import static com.canon.cusa.s21.api.NSZ.NSZC064001.constant.NSZC064001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.CPO_DTLTMsg;
import business.db.HLDTMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Pending Orders Validation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/01/2015   Hitachi         J.Kim           Create
 * 03/15/2016   Hitachi         K.Kasai         Update          QC#5282
 * 03/16/2016   Hitachi         A.Kohinata      Update          QC#5540
 * 09/28/2016   Hitachi         A.Kohinata      Update          QC#12898
 * 01/15/2019   Hitachi         A.Kohinata      Update          QC#29917
 * </pre>
 */
public class NSZC064001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NSZC064001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWXC005001ValidationBean
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        NWXC005001PMsg pMsg = param.getInputPMsg();
        if (pMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.hldRsnCd, (String) null);
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        if (checkParameter(msgMap, param)) {
            checkMachineSupplyOrder(msgMap, param);
        }
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap, NWXC005001ValidationBean param) {

        NWXC005001PMsg pMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
            return false;
        }

        HLDTMsg condition = new HLDTMsg();
        condition.setSQLID("015");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("hldRsnCd01", HLD_RSN.PENDING_ORDER_HOLD);
        condition.setConditionValue("cpoOrdNum01", param.getInputPMsg().cpoOrdNum_I.getValue());
        condition.setConditionValue("cpoDtlLineNum01", param.getInputPMsg().cpoDtlLineNum_I.getValue());
        condition.setConditionValue("cpoDtlLineSubNum01", param.getInputPMsg().cpoDtlLineSubNum_I.getValue());
        condition.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);
        int count = EZDTBLAccessor.count(condition);
        if (count > 0) {
            return false;
        }

        // CPO_DTLTMsg
        CPO_DTLTMsg cdTMsg = param.getCdTMsg();
        // START 2016/09/28 A.Kohinata [QC#12898, MOD]
        if (cdTMsg == null) {
            msgMap.addXxMsgId(NSZM0608E);
            return false;
        }
        if (!hasValue(cdTMsg.dsContrNum)) {
            msgMap.addXxMsgId(NSZM0271E);
            return false;
        }
        if (!isFleetContract(pMsg.glblCmpyCd.getValue(), cdTMsg.dsContrNum.getValue())) {
            if (!hasValue(cdTMsg.svcMachMstrPk)) {
                msgMap.addXxMsgId(NSZM0615E);
                return false;
            }
        }
        // END 2016/09/28 A.Kohinata [QC#12898, MOD]

        return true;
    }

    private void checkMachineSupplyOrder(S21ApiMessageMap msgMap, NWXC005001ValidationBean param) {

        NWXC005001PMsg pMsg = (NWXC005001PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String cpoOrdNum = param.getInputPMsg().cpoOrdNum_I.getValue();
        CPO_DTLTMsg cdTMsg = param.getCdTMsg();
        BigDecimal svcMachMstrPk = cdTMsg.svcMachMstrPk.getValue();

        // START 2016/09/28 A.Kohinata [QC#12898, MOD]
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", glblCmpyCd);
        inParam.put("cpoOrdNum", cpoOrdNum);
        inParam.put("cpoHdrStsCd", ORD_HDR_STS.VALIDATED);
        inParam.put("svcMachMstrPk", svcMachMstrPk);
        inParam.put("dsContrNum", cdTMsg.dsContrNum.getValue());
        // add start 2019/01/15 QC#29917
        inParam.put("mdseCd", cdTMsg.mdseCd.getValue());
        // add end 2019/01/15 QC#29917

        BigDecimal resultTMsg;
        if (isFleetContract(glblCmpyCd, cdTMsg.dsContrNum.getValue())) {
            resultTMsg = (BigDecimal) ssmBatchClient.queryObject("getFleetSupplyOrderCnt", inParam);
        } else {
            resultTMsg = (BigDecimal) ssmBatchClient.queryObject("getMachineSupplyOrderCnt", inParam);
        }
        // END 2016/09/28 A.Kohinata [QC#12898, MOD]
        if (resultTMsg.compareTo(BigDecimal.ZERO) > 0) {
            setOutputHoldInfo(param);
        }
    }

    private void setOutputHoldInfo(NWXC005001ValidationBean param) {

        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoOrdNum_O, param.getInputPMsg().cpoOrdNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoDtlLineNum_O, param.getInputPMsg().cpoDtlLineNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoDtlLineSubNum_O, param.getInputPMsg().cpoDtlLineSubNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().hldRsnCd, HLD_RSN.PENDING_ORDER_HOLD);
    }

    // START 2016/09/28 A.Kohinata [QC#12898, ADD]
    private boolean isFleetContract(String glblCmpyCd, String dsContrNum) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        dsContrTMsg.setSQLID("003");
        dsContrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrTMsg.setConditionValue("dsContrNum01", dsContrNum);
        DS_CONTRTMsgArray dsContrTMsgArray = (DS_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(dsContrTMsg);
        if (dsContrTMsgArray.getValidCount() > 0 && DS_CONTR_CATG.FLEET.equals(dsContrTMsgArray.no(0).dsContrCatgCd.getValue())) {
            return true;
        }
        return false;
    }
    // END 2016/09/28 A.Kohinata [QC#12898, ADD]
}
