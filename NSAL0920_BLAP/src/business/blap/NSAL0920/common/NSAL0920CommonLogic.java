/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0920.common;

import static business.blap.NSAL0920.constant.NSAL0920Constant.END_DT;
import static business.blap.NSAL0920.constant.NSAL0920Constant.NZZM0000E;
import static business.blap.NSAL0920.constant.NSAL0920Constant.NZZM0001W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import business.blap.NSAL0920.NSAL0920CMsg;
import business.blap.NSAL0920.NSAL0920Query;
import business.blap.NSAL0920.NSAL0920SMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Contract Billing Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         K.Kasai         Create          N/A
 * 2016/08/09   Hitachi         T.Tomita        Update          QC#13149
 * 2016/09/01   Hitachi         T.Tomita        Update          QC#13976
 * 2016/10/18   Hitachi         N.Arai          Update          QC#15216
 * 2017/01/23   Hitachi         K.Kitachi       Update          QC#17219
 *</pre>
 */
public class NSAL0920CommonLogic {


    /**
     * Create Pull Down
     * @param cMsg NSAL0720CMsg
     */
    public static void createPullDown(NSAL0920CMsg cMsg) {
        createRegionPullDown(cMsg);
// START 2016/10/18 N.Arai [QC#15216, MOD]
//        createBranchPullDown(cMsg);
// END 2016/10/18 N.Arai [QC#15216, MOD]
        // START 2017/01/23 K.Kitachi [QC#17219, DEL]
//        createSupervisorPullDown(cMsg);
        // END 2017/01/23 K.Kitachi [QC#17219, DEL]
    }

    // START 2016/09/01 T.Tomita [QC#13976, DEL]
//    private static void createRegionPullDown(NSAL0920CMsg cMsg) {
//        SVC_RGTMsgArray tMsgAry = getSvcRgPulldownList(cMsg.glblCmpyCd.getValue(), ZYPDateUtil.getSalesDate());
//        if (tMsgAry.getValidCount() > 0) {
//            for (int i = 0; i < tMsgAry.getValidCount(); i++) {
//                SVC_RGTMsg outTMsg = tMsgAry.no(i);
//                setValue(cMsg.svcRgPk_H1.no(i), outTMsg.svcRgPk);
//                setValue(cMsg.svcRgDescTxt_H2.no(i), outTMsg.svcRgDescTxt);
//            }
//        }
//    }
//
//    private static SVC_RGTMsgArray getSvcRgPulldownList(String glblCmpyCd, String slsDt) {
//        SVC_RGTMsg inMsg = new SVC_RGTMsg();
//        inMsg.setSQLID("002");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("svcRgActvFlg01", FLG_ON_Y);
//        inMsg.setConditionValue("effFromDt01", slsDt);
//        inMsg.setConditionValue("effThruDt01", slsDt);
//        return (SVC_RGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//    }
//
//    private static void createBranchPullDown(NSAL0920CMsg cMsg) {
//        SVC_CONTR_BRTMsgArray tMsgAry = getSvcContrBrPulldownList(cMsg.glblCmpyCd.getValue(), ZYPDateUtil.getSalesDate());
//        Map<String, String> tMsgKeys = new HashMap<String, String>();
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcContrBrCd");
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcContrBrDescTxt");
//        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcContrBrCd_H1, cMsg.svcContrBrDescTxt_H2);
//    }
//
//    private static SVC_CONTR_BRTMsgArray getSvcContrBrPulldownList(String glblCmpyCd, String slsDt) {
//        SVC_CONTR_BRTMsg inMsg = new SVC_CONTR_BRTMsg();
//        inMsg.setSQLID("002");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("svcContrBrActvFlg01", FLG_ON_Y);
//        inMsg.setConditionValue("effFromDt01", slsDt);
//        inMsg.setConditionValue("effThruDt01", slsDt);
//        return (SVC_CONTR_BRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//    }
    // END 2016/09/01 T.Tomita [QC#13976, DEL]

    // START 2016/09/01 T.Tomita [QC#13976, ADD]
    private static void createRegionPullDown(NSAL0920CMsg cMsg) {
        S21SsmEZDResult result = getSvcRgPulldownList(cMsg.glblCmpyCd.getValue());
        if (result != null && result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> valueMap = list.get(i);
                setValue(cMsg.svcRgPk_H1.no(i), (BigDecimal) valueMap.get("SVC_RG_PK"));
                setValue(cMsg.svcRgDescTxt_H2.no(i), (String) valueMap.get("SVC_RG_DESC_TXT"));
            }
        }
    }

    private static S21SsmEZDResult getSvcRgPulldownList(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("endDt", END_DT);
        return NSAL0920Query.getInstance().getSvcRgPulldownList(params);
    }

// START 2016/10/18 N.Arai [QC#15216, MOD]
//    private static void createBranchPullDown(NSAL0920CMsg cMsg) {
//        S21SsmEZDResult result = getSvcContrBrPulldownList(cMsg.glblCmpyCd.getValue());
//        if (result != null && result.isCodeNormal()) {
//            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//            for (int i = 0; i < list.size(); i++) {
//                Map<String, Object> valueMap = list.get(i);
//                setValue(cMsg.svcContrBrCd_H1.no(i), (String) valueMap.get("SVC_CONTR_BR_CD"));
//                setValue(cMsg.svcContrBrDescTxt_H2.no(i), (String) valueMap.get("SVC_CONTR_BR_DESC_TXT"));
//            }
//        }
//    }

    // START 2017/01/23 K.Kitachi [QC#17219, DEL]
//    private static S21SsmEZDResult getSvcContrBrPulldownList(String glblCmpyCd) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", glblCmpyCd);
//        params.put("slsDt", ZYPDateUtil.getSalesDate());
//        params.put("endDt", END_DT);
//        return NSAL0920Query.getInstance().getSvcContrBrPulldownList(params);
//    }
    // END 2017/01/23 K.Kitachi [QC#17219, DEL]
    // END 2016/09/01 T.Tomita [QC#13976, ADD]

    // START 2017/01/23 K.Kitachi [QC#17219, DEL]
//    private static void createSupervisorPullDown(NSAL0920CMsg cMsg) {
//        S21SsmEZDResult result = getSupervisorPulldownList(cMsg.glblCmpyCd.getValue());
//        if (result != null && result.isCodeNormal()) {
//            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
//            for (int i = 0; i < list.size(); i++) {
//                Map<String, Object> valueMap = list.get(i);
//                setValue(cMsg.psnCd_H1.no(i), (String) valueMap.get("PSN_CD"));
//                setValue(cMsg.xxAllPsnNm_H2.no(i), (String) valueMap.get("PSN_NM"));
//            }
//        }
//    }
//
//    private static S21SsmEZDResult getSupervisorPulldownList(String glblCmpyCd) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", glblCmpyCd);
//        params.put("salesDt", ZYPDateUtil.getSalesDate());
//        // START 2016/09/01 T.Tomita [QC#13976, ADD]
//        params.put("endDt", END_DT);
//        // END 2016/09/01 T.Tomita [QC#13976, ADD]
//        String supervisor = ZYPCodeDataUtil.getVarCharConstValue(SVC_ORG_FUNC_ROLE_TP_SUPERVR, glblCmpyCd);
//        params.put("svcOrgFuncRoleTpCd", supervisor);
//        return NSAL0920Query.getInstance().getPsnData(params);
//    }
    // END 2017/01/23 K.Kitachi [QC#17219, DEL]

    /**
     * Sets the pagenation.
     * @param xxPageShowOfNum the xx page show of num
     * @param xxPageShowToNum the xx page show to num
     * @param xxPageShowToTNum the xx page show to num
     * @param xxPageShowFromNum the xx page show from num
     * @param pageRecs the page records
     * @param totalRecs the total records
     */
    public static void setPagenation(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, EZDCBigDecimalItem xxPageShowToTNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {

        if (xxPageShowOfNum == null //
                || xxPageShowToNum == null) {
            return;
        }
        if (pageRecs == 0 || totalRecs == 0) {
            return;
        }

        xxPageShowToNum.setValue(xxPageShowFromNum + pageRecs - 1);
        xxPageShowToTNum.setValue(xxPageShowFromNum + pageRecs - 1);
        xxPageShowOfNum.setValue(totalRecs);

    }

    /**
     * getSearchData
     * @param cMsg NSAL0920CMsg
     * @param sMsg NSAL0920SMsg
     */
    public static void getSearchData(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL0920Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W, new String[]{Integer.toString(sMsg.A.length())});
            }
            // add start 2016/08/09 CSA Defect#13149
            // START 2018/02/08 K.Kojima [QC#20793,DEL]
            // setDefCurLocForSMsg(cMsg.glblCmpyCd.getValue(), sMsg);
            // END 2018/02/08 K.Kojima [QC#20793,DEL]
            // add end 2016/08/09 CSA Defect#13149
            // Copy one page from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    /**
     * copySMsgTocMsg
     * @param cMsg NSAL0920CMsg
     * @param sMsg NSAL0920SMsg
     */
    public static void copyGlblMsgToBizMsg(NSAL0920CMsg cMsg, NSAL0920SMsg sMsg) {
        int ixS = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.A.length() && ixS < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(ixS++), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    /**
     * preSetToPageOne
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        xxPageShowFromNum.setValue(BigDecimal.ONE);
    }

    // add start 2016/08/09 CSA Defect#13149
    // START 2018/02/08 K.Kojima [QC#20793,DEL]
    // private static void setDefCurLocForSMsg(String glblCmpyCd, NSAL0920SMsg sMsg) {
    //     String dsAcctNum = null;
    //     String billToCustCd = null;
    //     String[] defCurLoc = null;
    //     Map<String, String[]> defCurLocMap = new HashMap<String, String[]>();
    //     for (int i = 0; i < sMsg.A.getValidCount(); i++) {
    //         if (!hasValue(sMsg.A.no(i).dsAcctNum_A) || !hasValue(sMsg.A.no(i).billToCustCd_A)) {
    //             continue;
    //         }
    //         dsAcctNum = sMsg.A.no(i).dsAcctNum_A.getValue();
    //         billToCustCd = sMsg.A.no(i).billToCustCd_A.getValue();
    //         defCurLoc = getDefCurLoc(defCurLocMap, glblCmpyCd, dsAcctNum, billToCustCd);
    //         setValue(sMsg.A.no(i).curLocNum_A, defCurLoc[0]);
    //         setValue(sMsg.A.no(i).locNm_A, defCurLoc[1]);
    //     }
    // }
    // 
    // /**
    //  * getDefCurLoc
    //  * @param defCurLocMap
    //  * @param glblCmpyCd
    //  * @param dsAcctNum
    //  * @param billToCustCd
    //  * @return
    //  */
    // public static String[] getDefCurLoc(Map<String, String[]> defCurLocMap, String glblCmpyCd, String dsAcctNum, String billToCustCd) {
    //     String mapKey = dsAcctNum + HYPHEN + billToCustCd;
    //     if (defCurLocMap.containsKey(mapKey)) {
    //         return defCurLocMap.get(mapKey);
    //     } else {
    //         String[] valList = new String[2];
    //         String defShipToCustCd = NSAL0920CommonLogic.getDefCurLocCd(glblCmpyCd, dsAcctNum, billToCustCd);
    //         SHIP_TO_CUSTTMsg shipToCustTMsg = NSAL0920Query.getInstance().getShipToCust(glblCmpyCd, defShipToCustCd);
    //         if (shipToCustTMsg != null) {
    //             valList[0] = shipToCustTMsg.shipToCustCd.getValue();
    //             valList[1] = shipToCustTMsg.locNm.getValue();
    //             defCurLocMap.put(mapKey, valList);
    //         } else {
    //             defCurLocMap.put(mapKey, valList);
    //         }
    //         return valList;
    //     }
    // }
    // 
    // private static String getDefCurLocCd(String glblCmpyCd, String dsAcctNum, String billToCustCd) {
    //     NMZC610001PMsg pMsg = new NMZC610001PMsg();
    //     setValue(pMsg.glblCmpyCd, glblCmpyCd);
    //     setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
    //     setValue(pMsg.dsAcctNum_I1, dsAcctNum);
    //     setValue(pMsg.billToCustCd, billToCustCd);
    // 
    //     new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
    //     if (S21ApiUtil.isXxMsgId(pMsg)) {
    //         return null;
    //     }
    //     return pMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
    // }
    // END 2018/02/08 K.Kojima [QC#20793,DEL]
    // add end 2016/08/09 CSA Defect#13149
}
