/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB006001;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.INVTMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC188001_rmaLineListPMsg;
import business.parts.NWZC188001_shpgPlnNumListPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;


/**
 * <pre>
 * Invoice Finalize Batch
 * 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/23/2010   Fujitsu         A.Suda           Create          N/A
 * 11/05/2015   Fujitsu         H.Nagashima      Update          CSA
 * 24/05/2018   Fujitsu         K.Ishizuka       Update          S21_NA#25562
 * </pre>
 */

public class NWCB006001 extends S21BatchMain {

    /** MessageID */
    private static final String NWCM0008E = "NWCM0008E";

    /** Message ID */
    public static final String NWCM0061I = "NWCM0061I";

    /** Term cd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Processing Count */
    private int procCount = 0;

    /** normal Count */
    private int normalCnt = 0;

    /** error Count */
    private int errCnt = 0;

    /** ssmClient */
    private S21SsmBatchClient ssmClient;

    /**
     * Called batch Shell
     * @param args String[]
     */
    public static void main(final String[] args) {

        new NWCB006001().executeBatch(NWCB006001.class.getSimpleName());

    }

    protected void initRoutine() {

        if (!isGlobalCompanyCode()) {

            S21InfoLogOutput.println(NWCM0008E, new String[] {"Global Company Code" });
            setMainRoutineEnd();
            this.termCd = TERM_CD.ABNORMAL_END;
            return;
        }

        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    protected void mainRoutine() {

        finalizeInvoice();

        commit();
    }

    protected void termRoutine() {

        setTermState(this.termCd, this.normalCnt, this.errCnt, this.procCount);

    }

    private boolean isGlobalCompanyCode() {

        String glblCmpyCd = getGlobalCompanyCode();

        if (!hasValue(glblCmpyCd)) {
            return false;
        }

        return ZYPCodeDataUtil.hasCodeValue(GLBL_CMPY.class, glblCmpyCd, glblCmpyCd);
    }

    private void finalizeInvoice() {

        String glblCmpyCd = getGlobalCompanyCode();
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd",    glblCmpyCd);
        ssmParam.put("fnlzInvFlg",    ZYPConstant.FLG_OFF_N);
        ssmParam.put("itrlInvErrFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("sysSrcCd_NFC", SYS_SRC.S21_ACCOUNTING_AR);

        List<String> invNumList = (List<String>) ssmClient.queryObjectList("getInvoiceNumberForFinalize", ssmParam);
        int resultCnt = invNumList.size();

        if (!invNumList.isEmpty()) {
            INVTMsg invTMsg;
            for (String invNum : invNumList) {
                invTMsg = new INVTMsg();
                ZYPEZDItemValueSetter.setValue(invTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invTMsg.invNum,     invNum);
                ZYPEZDItemValueSetter.setValue(invTMsg.fnlzInvFlg, ZYPConstant.FLG_ON_Y);
                S21ApiTBLAccessor.updateSelectionField(invTMsg, new String[]{"fnlzInvFlg"});

                chgStsbyInvNum(glblCmpyCd, invNum);
            }
        }

        if (resultCnt == 0) {
            S21InfoLogOutput.println(NWCM0061I);
        }
        setCount(resultCnt);

    }

    protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
        boolean ret = true;

        return ret;
    }

    private void setCount(final int tatalCount) {

        this.normalCnt = tatalCount;
        this.normalCnt = tatalCount;
        this.procCount = tatalCount;

    }

    private boolean hasValue(String value) {
        return ZYPCommonFunc.hasValue(value);
    }
    /**
     * Change Order Status for finalize
     * @param glblCmpyCd
     * @param invNum
     * @return boolean
     */
    private boolean chgStsbyInvNum(String glblCmpyCd, String invNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        List<String> shpgNumList = new ArrayList<String>();
        List<String> cpoDtlLineNumList = new ArrayList<String>(); // 2018/05/28 S21_NA#25562 Add

        ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invNum", invNum);

        List<Map<String, String>> cpoMapList = (List<Map<String, String>>) ssmClient.queryObjectList("getCpoNumberForChgSts", ssmParam);

        if (!cpoMapList.isEmpty()) {

            String cpoNum = null;
            String preCpoNum = null;
            // 2018/05/28 S21_NA#25562 Add Start
            String shpgPlnNum = null;
            String cpoDtlLineNum = null;
            String cpoDtlLineSubNum = null;
            // 2018/05/28 S21_NA#25562 Add End

            for (Map<String, String> cpoMap : cpoMapList) {

                cpoNum = cpoMap.get("CPO_ORD_NUM");
                // 2018/05/28 S21_NA#25562 Add Start
                shpgPlnNum = cpoMap.get("SHPG_PLN_NUM");
                cpoDtlLineNum = cpoMap.get("CPO_DTL_LINE_NUM");
                cpoDtlLineSubNum = cpoMap.get("CPO_DTL_LINE_SUB_NUM");
                // 2018/05/28 S21_NA#25562 Add End

                if (!ZYPCommonFunc.hasValue(preCpoNum) || preCpoNum.equals(cpoNum)) {
                    
                    // 2018/05/28 S21_NA#25562 Mod Start
                    // shpgNumList.add(cpoMap.get("SHPG_PLN_NUM"));
                    if(ZYPCommonFunc.hasValue(shpgPlnNum)){
                        shpgNumList.add(shpgPlnNum);
                    } else {
                        if (!cpoDtlLineNumList.contains(cpoDtlLineNum + "," + cpoDtlLineSubNum)) {
                            cpoDtlLineNumList.add(cpoDtlLineNum + "," + cpoDtlLineSubNum);
                        }
                    }
                    // 2018/05/28 S21_NA#25562 Mod End
                    preCpoNum = cpoNum;
                    continue;
                }

                // callStsUpdApi(glblCmpyCd, preCpoNum, shpgNumList);
                callStsUpdApi(glblCmpyCd, cpoNum, shpgNumList, cpoDtlLineNumList);// 2018/05/28 S21_NA#25562 Mod
                shpgNumList.clear();
                cpoDtlLineNumList.clear(); // 2018/05/28 S21_NA#25562 Add

                // 2018/05/28 S21_NA#25562 Mod Start
                // shpgNumList.add(cpoMap.get("SHPG_PLN_NUM"));
                if(ZYPCommonFunc.hasValue(shpgPlnNum)){
                    shpgNumList.add(shpgPlnNum);
                } else{
                    if (!cpoDtlLineNumList.contains(cpoDtlLineNum + "," + cpoDtlLineSubNum)) {
                        cpoDtlLineNumList.add(cpoDtlLineNum + "," + cpoDtlLineSubNum);
                    }
                }
                // 2018/05/28 S21_NA#25562 Mod End
                preCpoNum = cpoNum;
            }

            // callStsUpdApi(glblCmpyCd, cpoNum, shpgNumList);
            callStsUpdApi(glblCmpyCd, cpoNum, shpgNumList, cpoDtlLineNumList); // 2018/05/28 S21_NA#25562 Mod
        }
        return true;

    }

    /**
     * Call NWZC188001 Display Order Line Status Update API
     * @param cpoOrdNum String
     * @return boolean 
     */
    // private boolean callStsUpdApi(String glblCmpyCd, String cpoOrdNum, List<String> shpgNumList) {
    private boolean callStsUpdApi(String glblCmpyCd, String cpoOrdNum, List<String> shpgNumList, List<String> cpoDtlLineNumList) { // 2018/05/28 S21_NA#25562 Mod

        // 2018/05/28 S21_NA#25562 Mod Start
        // if (shpgNumList.size() == 0) {
        //     return true;
        // }
        if (shpgNumList.size() == 0 && cpoDtlLineNumList.size() == 0) {
            return true;
        }
        // 2018/05/28 S21_NA#25562 Mod Start

        NWZC188001PMsg pMsg = new NWZC188001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoOrdNum);

        for (int i = 0; i < shpgNumList.size(); i++) {
            NWZC188001_shpgPlnNumListPMsg shpgNumMsg = pMsg.shpgPlnNumList.no(i);
            ZYPEZDItemValueSetter.setValue(shpgNumMsg.shpgPlnNum, shpgNumList.get(i));
            pMsg.shpgPlnNumList.setValidCount(i + 1);
        }
        
        // 2018/05/28 S21_NA#25562 Add Start
        for (int i = 0; i < cpoDtlLineNumList.size(); i++) {
            NWZC188001_rmaLineListPMsg rmaMsg = pMsg.rmaLineList.no(i);
            ZYPEZDItemValueSetter.setValue(rmaMsg.dsCpoRtrnLineNum, cpoDtlLineNumList.get(i).split(",")[0]);
            ZYPEZDItemValueSetter.setValue(rmaMsg.dsCpoRtrnLineSubNum, cpoDtlLineNumList.get(i).split(",")[1]);
            pMsg.rmaLineList.setValidCount(i + 1);
        }
        // 2018/05/28 S21_NA#25562 Add End

        new NWZC188001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }

        for (int i = 0; i < pMsg.shpgPlnNumList.getValidCount(); i++) {
            // Shipping List: Error check
            NWZC188001_shpgPlnNumListPMsg shpgNumMsg = pMsg.shpgPlnNumList.no(i);

            if (ZYPCommonFunc.hasValue(shpgNumMsg.xxMsgId)) {
                S21InfoLogOutput.println(shpgNumMsg.xxMsgId.getValue());
                return false;
            }
        }
        return true;
    }

}
