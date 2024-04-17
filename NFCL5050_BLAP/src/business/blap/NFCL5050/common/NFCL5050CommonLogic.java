/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009   Fujitsu         FXS)Z.Wang      Create          N/A
 * 10/29/2009   Fujitsu         FAP)D.Kato      Update          DefID 0346
 * 11/09/2009   Fujitsu         FAP)N.Aoyama    Update          DefID 1647
 * 11/25/2009   Fujitsu         FAP)D.Kato      Update          DefID 2024
 * 12/08/2009   Fujitsu         FAP)N.Aoyama    Update          DefID 2460
 * 01/25/2010   Fujitsu         FAP)D.Kato      Update
 * 02/04/2010   Fujitsu         FAP)D.Kato      Update
 * 05/14/2010   Fujitsu         FAP)N.Aoyama    Update          DefID:6321   
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 08/10/2010   Fujitsu         I.Kondo         Update          For EXPT_FLG.
 * 11/01/2010   Fujitsu         I.Kondo         Update          DefID:M-19
 * 10/05/2011   Fujitsu         T.Tanaka        Update          ITG 36455
 * 2018/07/20   Fujitsu         Y.Matsui        Update          QC#26985
 * </pre>
 */
package business.blap.NFCL5050.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL5050.NFCL5050CMsg;
import business.blap.NFCL5050.NFCL5050SMsg;
import business.blap.NFCL5050.constant.NFCL5050Constant;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 * NFCL5050CommonLogic.
 */
public class NFCL5050CommonLogic implements NFCL5050Constant {

    /**
     * @param bizMsg NFCL5050CMsg
     * @param globalMsg NFCL5050SMsg
     * @param inMsg BILL_TO_CUSTTMsg
     * @return BILL_TO_CUSTTMsgArray
     */
    public static BILL_TO_CUSTTMsgArray findBillToCustList(NFCL5050CMsg bizMsg, NFCL5050SMsg globalMsg, BILL_TO_CUSTTMsg inMsg) {
        inMsg.setConditionValue("billToCustCd01", bizMsg.payerCustCd.getValue());
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());

        inMsg.setMaxCount(1);
        inMsg.setSQLID("017");

        BILL_TO_CUSTTMsgArray outMsg = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return outMsg;
    }

    /**
     * @param bizMsg NFCL5050CMsg
     * @param globalMsg NFCL5050SMsg
     * @return Map<String, Object>
     */
    public static Map<String, Object> setSsmMap(NFCL5050CMsg bizMsg, NFCL5050SMsg globalMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        createCond_TrxType(ssmParam, bizMsg);

        int count = bizMsg.B.getValidCount();

        ArrayList<String> serchArTrxNum = new ArrayList<String>();
        ArrayList<String> serchArTrxTpCd = new ArrayList<String>();
        ArrayList<String> sercharTrxBalPk = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).arTrxNum_B1.getValue())) {
                serchArTrxNum.add(bizMsg.B.no(i).arTrxNum_B1.getValue());
            }

            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).arTrxTpCd_B1.getValue())) {
                serchArTrxTpCd.add(bizMsg.B.no(i).arTrxTpCd_B1.getValue());
            }

            if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).arTrxBalPk_B1.getValue())) {
                sercharTrxBalPk.add(bizMsg.B.no(i).arTrxBalPk_B1.getValue().toString());
            }

        }
        String[] arTrxNumList = (String[]) serchArTrxNum.toArray(new String[serchArTrxNum.size()]);
        // String[] arTrxTpCdList = (String[])
        // serchArTrxTpCd.toArray(new String[0]);
        String[] arTrxBalPkList = (String[]) sercharTrxBalPk.toArray(new String[sercharTrxBalPk.size()]);

        if (arTrxBalPkList.length == 0) {
            arTrxBalPkList = null;
        }

        if (arTrxNumList.length == 0) {
            arTrxNumList = null;
        }

        ssmParam.put("rowNum", globalMsg.A.length());
        ssmParam.put("cMsg", bizMsg);
        // ssmParam.put("arTrxTpCd", bizMsg.arTrxTpCd_P1.getValue());
        ssmParam.put("arTrxNum01", arTrxNumList);
        // ssmParam.put("arTrxTpCd01", arTrxTpCdList);
        ssmParam.put("arTrxBalPk01", arTrxBalPkList);
        ssmParam.put("arCashApplyStsCdA", AR_CASH_APPLY_STS.APPLIED);
        ssmParam.put("arCashApplyStsCdV", AR_CASH_APPLY_STS.VOID);
        // DefID:6321 ArTrxDate Over DepositDate
        // ssmParam.put("maxArTrxDate",endManthDayforDepositDate(bizMsg.glDt.getValue()));
        
        ssmParam.put("dsAcctRelnTp1", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        ssmParam.put("dsAcctRelnTp2", DS_ACCT_RELN_TP.RELATED_ACCOUNT);

        // START 2018/07/20 Y.Matsui [QC#26985,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
            String trxNumSrchTxt = bizMsg.xxTrxNumSrchTxt.getValue();
            if (trxNumSrchTxt.contains("%")) {
                bizMsg.xxChkBox_S1.setValue("Y");
            } else if (trxNumSrchTxt.contains(TRX_NUM_SEPARATOR)) {
                String[] invoices = trxNumSrchTxt.split(TRX_NUM_SEPARATOR);
                ssmParam.put("invoices", invoices);
                bizMsg.xxChkBox_S1.setValue("N");
            } else {
                bizMsg.xxChkBox_S1.setValue("N");
            }
        }
        // END   2018/07/20 Y.Matsui [QC#26985,ADD]

        return ssmParam;
    }

    /**
     * @param cMsg EZDCMsg
     */
    private static void createCond_TrxType(Map<String, Object> ssmParam, NFCL5050CMsg cMsg) {

        List<String> condList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_H1)) {
            condList.add(AR_TRX_TP.INVOICE);
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_H2)) {
            condList.add(AR_TRX_TP.CREDIT_MEMO);
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_H3)) {
            condList.add(AR_TRX_TP.ON_ACCOUNT);
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_H4)) {
            condList.add(AR_TRX_TP.DEDUCTION);
        }
        if (condList.isEmpty()) {
            ssmParam.put("arTrxTpCdisEmpty", null);
        } else {
            ssmParam.put("arTrxTpCdisEmpty", "N");
        }
        ssmParam.put("arTrxTpCd", (String[]) condList.toArray(new String[condList.size()]));

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     */
    public static void setPage(NFCL5050SMsg globalMsg, NFCL5050CMsg bizMsg) {

        int pagenationFrom = getPagenationFrom(bizMsg.xxPageShowFromNum.getValueInt());
        setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     */
    public static void setPageJump(NFCL5050SMsg globalMsg, NFCL5050CMsg bizMsg) {

        int pagenationFrom = getPagenationFrom(bizMsg.xxPageShowFromNum_H1.getValueInt());

        setBizMsgToGlobalMsg(globalMsg, bizMsg, pagenationFrom, pagenationFrom);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param pageFromNum int
     * @return int
     */
    public static int getPagenationFrom(int pageFromNum) {

        int pagenationFrom = pageFromNum;

        if (0 != pagenationFrom) {
            pagenationFrom = pagenationFrom - 1;
        }
        return pagenationFrom;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param index Global area information Start
     * @param pagenationFrom pagenationFrom
     */
    public static void setBizMsgToGlobalMsg(NFCL5050SMsg globalMsg, NFCL5050CMsg bizMsg, int index, int pagenationFrom) {

        for (int i = index; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);
            } else {
                break;
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param glDt String
     * @return String
     */
    public static String endMonthDayforDepositDate(String glDt) {

        String depositYYYYMM = null;
        if (glDt.length() >= GL_DT_LENGTH) {
            depositYYYYMM = NFCCmnMethod.getEndofMonth(glDt);
        } else {
            depositYYYYMM = glDt;
        }

        return depositYYYYMM;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0660CMsg
     * @param globalMsg NFCL0660SMsg
     * @param cnt int
     * @return boolean
     */
    public static boolean isDepositDateAndTrxDate(NFCL5050CMsg bizMsg, NFCL5050SMsg globalMsg, int cnt) {

        if (isAdvance(bizMsg)) {
            return true;
        }

        //if(!NFCCmnMethod.isDepositDateAndTrxDate(bizMsg.glDt.getValue(), globalMsg.A.no(cnt).arTrxDt_A1.getValue())) {
        //    globalMsg.A.no(cnt).xxChkBox_A1.setErrorInfo(1, "NFCM0182E", null);
        //    bizMsg.setMessageInfo("ZZM9037E", null);
        //    return false;
        //}
        return true;
    }

    private static boolean isAdvance(NFCL5050CMsg bizMsg) {
        return (ZYPCommonFunc.hasValue(bizMsg.arRcptTrxTpCd_H1) && AR_RCPT_TRX_TP_CD_ADVANCE.equals(bizMsg.arRcptTrxTpCd_H1.getValue()));
    }

    /**
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param dataCount int
     */
    public static void setPageData(NFCL5050SMsg globalMsg, NFCL5050CMsg bizMsg, int dataCount) {

        if (0 == dataCount) {
            ZYPTableUtil.clear(bizMsg.A);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }

        int page = dataCount / bizMsg.A.length();
        int rest = dataCount % bizMsg.A.length();
        int fromNum = 0;

        if (0 == rest) {
            fromNum = ((page - 1) * bizMsg.A.length());
        } else {
            fromNum = (page * bizMsg.A.length());
        }

        setGlobalMsgToBizMsg(globalMsg, bizMsg, fromNum, fromNum);

        bizMsg.xxPageShowFromNum.setValue(fromNum + 1);
        bizMsg.xxPageShowToNum.setValue(fromNum + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());

    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param index Global area information Start
     * @param pagenationFrom pagenationFrom
     */
    public static void setGlobalMsgToBizMsg(NFCL5050SMsg globalMsg, NFCL5050CMsg bizMsg, int index, int pagenationFrom) {

        ZYPTableUtil.clear(bizMsg.A);
        int i = index;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }

        bizMsg.A.setValidCount(i - pagenationFrom);
    }

    // START 2018/07/20 Y.Matsui [QC#26985,ADD]
    /**
     * @param globalMsg Global area information
     * @param bizMsg Business Component Interface Message
     * @param checked "Y" or "N"
     */
    public static void selectAll(NFCL5050SMsg globalMsg, NFCL5050CMsg bizMsg, String checked) {
        NFCL5050CommonLogic.setPage(globalMsg, bizMsg);
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            globalMsg.A.no(i).xxChkBox_A1.setValue(checked);
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.A.no(i).xxChkBox_A1.setValue(checked);
        }
    }
    // END   2018/07/20 Y.Matsui [QC#26985,ADD]

}
