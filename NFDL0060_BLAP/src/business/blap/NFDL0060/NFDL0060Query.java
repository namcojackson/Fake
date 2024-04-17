/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0060;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NFDL0060.constant.NFDL0060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DISP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#10260
 * 2016/08/31   Hitachi         K.Kojima        Update          QC#10786
 * 2016/09/13   Hitachi         K.Kojima        Update          QC#13257
 * 2017/07/31   Hitachi         T.Tsuchida      Update          QC#19575
 * 2018/02/01   Hitachi         T.Tsuchida      Update          QC#21115
 *</pre>
 */
public final class NFDL0060Query extends S21SsmEZDQuerySupport implements NFDL0060Constant {

    /**
     * Instance
     */
    private static final NFDL0060Query INSTANCE = new NFDL0060Query();

    private NFDL0060Query() {
    }

    /**
     * Get instance
     * @return NFDL0060Query
     */
    public static NFDL0060Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get task list.
     * @param cMsg NFDL0060CMsg
     * @param sMsg NFDL0060SMsg
     * @param salesDt String
     * @param usrId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTaskList(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg, String salesDt, String userId) {

        String glblCmpyCd = getGlobalCompanyCode();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("cMsg", cMsg);
        // START 2016/07/08 K.Kojima [QC#11417,DEL]
        // param.put("cltDispTpCd", cMsg.cltDispTpCd_H3.getValue());
        // END 2016/07/08 K.Kojima [QC#11417,DEL]

        // START 2016/07/08 K.Kojima [QC#11417,ADD]
        if (ZYPCommonFunc.hasValue(cMsg.cltDispTpCd_H3)) {
            if (CLT_DISP_TP.SELF.equals(cMsg.cltDispTpCd_H3.getValue())) {
                param.put("cltPsnCd", userId);
            } else if (CLT_DISP_TP.MYGROUP.equals(cMsg.cltDispTpCd_H3.getValue())) {
                param.put("srchPsnCd", userId);
            }
            // END 2018/08/07 J.Kim [QC#24815,DEL]
        }
        // END 2016/07/08 K.Kojima [QC#11417,ADD]

        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt_H1)) {
            String[] cltPsnCdList = cMsg.xxQueryFltrTxt_H1.getValue().split(",");
            param.put("cltPsnCdList", cltPsnCdList);
        } else {
        	param.put("cltPsnCdList", null);
        }
        // START 2017/07/31 T.Tsuchida [QC#19575,ADD]
        if (ZYPCommonFunc.hasValue(cMsg.cltPtfoNm_H1)) {
            param.put("cltPtfoNm", cMsg.cltPtfoNm_H1.getValue().toUpperCase());
        }
        // END 2017/07/31 T.Tsuchida [QC#19575,ADD]

        // START 2017/07/31 T.Tsuchida [QC#19575,MOD]
        //param.put("collectorNm", cMsg.cltPsnNm_H1.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.cltPsnNm_H1)) {
            param.put("collectorNm", cMsg.cltPsnNm_H1.getValue().toUpperCase());
        }
        // END 2017/07/31 T.Tsuchida [QC#19575,MOD]
        param.put("xxFromDt", cMsg.xxFromDt_H1.getValue());
        param.put("xxToDt", cMsg.xxToDt_H1.getValue());
        param.put("cltItemTpCd", cMsg.cltItemTpCd_H3.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            param.put("cltTaskStsCd", CLT_TASK_STS.OPEN);
        }
        // START 2016/07/28 K.Kojima [QC#10260,MOD]
        // param.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
        // START 2016/09/13 K.Kojima [QC#13257,MOD]
        // if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H1)) {
        // String[] dsAcctNumList =
        // cMsg.dsAcctNum_H1.getValue().split(",");
        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt_H2)) {
            // START 2017/07/31 T.Tsuchida [QC#19575,MOD]
            //String[] dsAcctNumList = cMsg.xxQueryFltrTxt_H2.getValue().split(",");
            String[] dsAcctNumList = cMsg.xxQueryFltrTxt_H2.getValue().toUpperCase().split(",");
            // END 2017/07/31 T.Tsuchida [QC#19575,MOD]
            // END 2016/09/13 K.Kojima [QC#13257,MOD]
            param.put("dsAcctNumList", dsAcctNumList);
        } else {
            param.put("dsAcctNumList", null);
        }
        // END 2016/07/28 K.Kojima [QC#10260,MOD]
        param.put("dsAcctNm", cMsg.dsAcctNm_H1.getValue());

        param.put("rowNum", getMaxRowNum(sMsg) + 1);
        param.put("salesDt", salesDt);
        // START 2016/07/08 K.Kojima [QC#11417,DEL]
        // param.put("userId", userId);
        // END 2016/07/08 K.Kojima [QC#11417,DEL]
        // START 2018/02/01 T.Tsuchida [QC#21115,MOD]
        List<String> arTrxTpCdList = new ArrayList<String>();
        arTrxTpCdList.add(AR_TRX_TP.INVOICE);
        arTrxTpCdList.add(AR_TRX_TP.DEDUCTION);
        arTrxTpCdList.add(AR_TRX_TP.ON_ACCOUNT);
        // START 2018/07/24 J.Kim [QC#27082,ADD]
        arTrxTpCdList.add(AR_TRX_TP.RECEIPT);
        // END 2018/07/24 J.Kim [QC#27082,ADD]
        param.put("arTrxTpCdList", arTrxTpCdList);
        param.put("TrxTpInv", AR_TRX_TP.INVOICE);
        param.put("TrxTpDed", AR_TRX_TP.DEDUCTION);
        param.put("TrxTpAcc", AR_TRX_TP.ON_ACCOUNT);
        param.put("TrxTpRcp", AR_TRX_TP.RECEIPT);
        // END 2018/02/01 T.Tsuchida [QC#21115,MOD]
        List<String> arCashApplyStsCdList = new ArrayList<String>();
        arCashApplyStsCdList.add(AR_CASH_APPLY_STS.UNAPPLIED);
        arCashApplyStsCdList.add(AR_CASH_APPLY_STS.PARTIAL);
        param.put("arCashApplyStsCdList", arCashApplyStsCdList);
        // START 2016/08/31 K.Kojima [QC#10786,ADD]
        param.put("cltDsptStsApproved", CLT_DSPT_STS.APPROVED);
        // END 2016/08/31 K.Kojima [QC#10786,ADD]

        if (CLT_ITEM_TP.TASK.equals(cMsg.cltItemTpCd_H3.getValue())) {
            param.put("showTaskFlg", ZYPConstant.FLG_ON_Y);
            param.put("showWorkItemFlg", ZYPConstant.FLG_OFF_N);
        } else if (CLT_ITEM_TP.WORK_ITEM.equals(cMsg.cltItemTpCd_H3.getValue())) {
            param.put("showTaskFlg", ZYPConstant.FLG_OFF_N);
            param.put("showWorkItemFlg", ZYPConstant.FLG_ON_Y);
        } else {
            param.put("showTaskFlg", ZYPConstant.FLG_ON_Y);
            param.put("showWorkItemFlg", ZYPConstant.FLG_ON_Y);
        }

        param.put("itemTypeTask", ZYPCodeDataUtil.getName(CLT_ITEM_TP.class, glblCmpyCd, CLT_ITEM_TP.TASK));
        param.put("itemTypeWorkItem", ZYPCodeDataUtil.getName(CLT_ITEM_TP.class, glblCmpyCd, CLT_ITEM_TP.WORK_ITEM));
        return getSsmEZDClient().queryEZDMsgArray("getTaskList", param, sMsg.A);
    }

    private int getMaxRowNum(NFDL0060SMsg sMsg) {
        return sMsg.A.length();
    }

    // START 2016/07/08 K.Kojima [QC#11417,DEL]
    // /**
    // * getUserIdList
    // * @param glblCmpyCd String
    // * @param userId String
    // * @return S21SsmEZDResult
    // */
    // public S21SsmEZDResult getUserIdList(String glblCmpyCd, String
    // userId) {
    // Map<String, Object> ssmParam = new HashMap<String, Object>();
    // ssmParam.put("glblCmpyCd", glblCmpyCd);
    // ssmParam.put("userId", userId);
    // return getSsmEZDClient().queryObjectList("getUserIdList",
    // ssmParam);
    // }
    // END 2016/07/08 K.Kojima [QC#11417,DEL]
}
