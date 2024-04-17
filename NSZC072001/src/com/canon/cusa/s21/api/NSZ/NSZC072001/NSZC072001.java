/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC072001;

import static com.canon.cusa.s21.api.NSZ.NSZC072001.constant.NSZC072001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;

import business.parts.NSZC072001PMsg;
import business.parts.NSZC072001_machVldListPMsg;
import business.parts.NSZC072001_machVldListPMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Machine Validation API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/01/2015   Hitachi         Y.Tsuchimoto    Create          NA#Machine Validation API
 * 11/08/2016   Hitachi         T.Mizuki        Update          QC#15848
 * 11/15/2016   Hitachi         A.Kohinata      Update          QC#15952
 * 11/22/2016   Hitachi         A.Kohinata      Update          QC#15952
 * 11/29/2016   Hitachi         A.Kohinata      Update          QC#15952
 * 2019/03/04   Hitachi         K.Kitachi       Update          QC#30545
 * </pre>
 */
public class NSZC072001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NSZC072001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NSZC072001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NSZC072001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        outputParamClear(msgMap);

        if (!checkParameter(msgMap)) {
            msgMap.flush();
            return;
        }

        checkMachineValidation(msgMap);
    }

    private void outputParamClear(S21ApiMessageMap msgMap) {
        NSZC072001PMsg pMsg = (NSZC072001PMsg) msgMap.getPmsg();
        if (pMsg != null) {
            NSZC072001_machVldListPMsgArray machVldList = pMsg.machVldList;
            if (machVldList != null) {
                for (int i = 0; i < machVldList.getValidCount(); i++) {
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).serNum, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).machVldStsTxt, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).locNm, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).addlLocNm, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).firstLineAddr, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).scdLineAddr, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).thirdLineAddr, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).frthLineAddr, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).ctyAddr, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).stCd, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).postCd, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).ctryCd, (String) null);
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).mdlNm, (String) null);
                }
            }
        }
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC072001PMsg pMsg = (NSZC072001PMsg) msgMap.getPmsg();

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

    private void checkMachineValidation(S21ApiMessageMap msgMap) {
        NSZC072001PMsg pMsg = (NSZC072001PMsg) msgMap.getPmsg();

        // mod start 2016/11/15 CSA QC#15952
        BigDecimal machLimit = ZYPCodeDataUtil.getNumConstValue(NSZC0720_MACH_LIMIT, pMsg.glblCmpyCd.getValue());

        List<Map<String, String>> machInfoList = (List<Map<String, String>>) getMachineInfo(pMsg.glblCmpyCd.getValue(), pMsg.serNum.getValue(), pMsg.slsDt.getValue(), machLimit.intValue() + 1);
        setOutputParam(msgMap, machInfoList, machLimit.intValue());
        // mod end 2016/11/15 CSA QC#15952
    }

    private void setOutputParam(S21ApiMessageMap msgMap, List<Map<String, String>> machInfoList, int machLimit) {
        NSZC072001PMsg pMsg = (NSZC072001PMsg) msgMap.getPmsg();
        if (machInfoList == null || machInfoList.size() == 0) {
            // mod start 2016/11/29 CSA QC#15952
            //setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(0)).serNum, pMsg.serNum);
            //setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(0)).machVldStsTxt, MACH_VLD_STS_TXT);
            pMsg.machVldList.clear();
            pMsg.machVldList.setValidCount(0);
            // mod end 2016/11/29 CSA QC#15952
            // add start 2016/11/15 CSA QC#15952
            String noRecMessage = ZYPCodeDataUtil.getVarCharConstValue(NSZC0720_NO_RECORDS_MESSAGE, pMsg.glblCmpyCd.getValue());
            setValue(pMsg.clickMsgTxt, noRecMessage);
            // add end 2016/11/15 CSA QC#15952
            return;
        }

        int machInfoListCnt = machInfoList.size();

        NSZC072001_machVldListPMsgArray machVldList = pMsg.machVldList;
        // mod start 2016/11/15 CSA QC#15952
        if (machLimit < machInfoList.size()) {
            pMsg.machVldList.clear();
            pMsg.machVldList.setValidCount(0);
            String maxRecMessage = ZYPCodeDataUtil.getVarCharConstValue(NSZC0720_MAX_RECORDS_MESSAGE, pMsg.glblCmpyCd.getValue());
            setValue(pMsg.clickMsgTxt, maxRecMessage);
            // mod start 2016/11/22 CSA QC#15952
            //machInfoListCnt = machLimit;
            return;
            // mod end 2016/11/22 CSA QC#15952
        }
        // mod end 2016/11/15 CSA QC#15952

        pMsg.machVldList.setValidCount(machInfoListCnt);
        if (machVldList != null) {
            for (int i = 0; i < machInfoListCnt; i++) {
                Map<String, String> machInfoLine = (Map<String, String>) machInfoList.get(i);
                String machVldAddrRtrnFlg = (String) machInfoLine.get(COLUMN_MACH_VLD_ADDR_RTRN_FLG);

                setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).serNum, (String) machInfoLine.get(COLUMN_SER_NUM));
                setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).machVldStsTxt, (String) machInfoLine.get(COLUMN_MACH_VLD_STS_TXT));

                if (ZYPConstant.FLG_ON_Y.equals(machVldAddrRtrnFlg)) {
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).locNm, (String) machInfoLine.get(COLUMN_LOC_NM));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).addlLocNm, (String) machInfoLine.get(COLUMN_ADDL_LOC_NM));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).firstLineAddr, (String) machInfoLine.get(COLUMN_FIRST_LINE_ADDR));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).scdLineAddr, (String) machInfoLine.get(COLUMN_SCD_LINE_ADDR));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).thirdLineAddr, (String) machInfoLine.get(COLUMN_THIRD_LINE_ADDR));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).frthLineAddr, (String) machInfoLine.get(COLUMN_FRTH_LINE_ADDR));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).ctyAddr, (String) machInfoLine.get(COLUMN_CTY_ADDR));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).stCd, (String) machInfoLine.get(COLUMN_ST_CD));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).postCd, (String) machInfoLine.get(COLUMN_POST_CD));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).ctryCd, (String) machInfoLine.get(COLUMN_CTRY_CD));
                    setValue(((NSZC072001_machVldListPMsg) pMsg.machVldList.get(i)).mdlNm, (String) machInfoLine.get(COLUMN_T_MDL_NM));
                }
            }
        }
    }

    // mod start 2016/11/15 CSA QC#15952
    private List<Map<String, String>> getMachineInfo(String glblCmpyCd, String serNum, String slsDt, int maxRowNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("serNum", serNum);
        param.put("slsDt", slsDt);
        // mod start 2016/11/08 CSA QC#15848
        param.put("rowNum", maxRowNum);
        // mod end 2016/11/08 CSA QC#15848
        // START 2019/03/04 K.Kitachi [QC#30545, ADD]
        param.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        // END 2019/03/04 K.Kitachi [QC#30545, ADD]
        return (List<Map<String, String>>) this.ssmBatchClient.queryObjectList("getMachineInfo", param);
    }
    // mod end 2016/11/15 CSA QC#15952
}
