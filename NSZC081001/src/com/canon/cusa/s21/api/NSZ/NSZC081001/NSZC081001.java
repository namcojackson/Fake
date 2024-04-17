/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC081001;

import static com.canon.cusa.s21.api.NSZ.NSZC081001.constant.NSZC081001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NSZC081001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Meter Validtion API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/15/2015   Hitachi         T.Iwamoto       Create          NA#Meter Validtion API
 * </pre>
 */
public class NSZC081001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NSZC081001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NSZC081001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC081001PMsg param, final ONBATCH_TYPE onBatchTp) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParameter(msgMap)) {
            doProcess(msgMap, param);
        }

        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC081001PMsg pMsg = (NSZC081001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            setValue(pMsg.glblCmpyCd, GLBL_CMPY_CD);
        }
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        mandatoryCheck(msgMap, pMsg.serNum, NSZM0653E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void doProcess(S21ApiMessageMap msgMap, NSZC081001PMsg pMsg) {
        if (!checkExistsSerialNum(pMsg)) {
            msgMap.addXxMsgIdWithPrm(NSZM0703E, new String[] {pMsg.serNum.getValue() });
            return;
        }

        List<Map<String, Object>> rsSvcMachMtr = getSvcMachMtr(pMsg);
        setOutputParam(pMsg, rsSvcMachMtr);
    }

    private boolean checkExistsSerialNum(NSZC081001PMsg pMsg) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("serNum01", pMsg.serNum.getValue());
        SVC_MACH_MSTRTMsgArray outArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return false;
        }
        return true;
    }

    private List<Map<String, Object>> getSvcMachMtr(NSZC081001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        String dateFormat = ZYPCodeDataUtil.getVarCharConstValue(CLICKDATEFORMAT, pMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(dateFormat)) {
            dateFormat = DEFAULTDATEFORMAT;
        }
        param.put("dtFmt", dateFormat);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcMachMtr", param);
        return rs;
    }

    private void setOutputParam(NSZC081001PMsg pMsg, List<Map<String, Object>> rsSvcMachMtr) {
        int i = 0;

        for (Map<String, Object> rs : rsSvcMachMtr) {
            setValue(pMsg.mtrList.no(i).mtrLbCd, (String) rs.get("MTR_LB_CD"));
            setValue(pMsg.mtrList.no(i).mtrLbDescTxt, (String) rs.get("MTR_LB_DESC_TXT"));
            setValue(pMsg.mtrList.no(i).readMtrCnt, (BigDecimal) rs.get("READ_MTR_CNT"));
            setValue(pMsg.mtrList.no(i).xxUsrSysDtTxt, (String) rs.get("MTR_READ_DT"));
            setValue(pMsg.mtrList.no(i).mtrReadTpCd, (String) rs.get("DS_MTR_READ_TP_CD"));
            setValue(pMsg.mtrList.no(i).mtrReadTpDescTxt, (String) rs.get("DS_MTR_READ_TP_DESC_TXT"));
            i++;
        }
        pMsg.mtrList.setValidCount(i);
    }
}
