/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3000.common;

import static business.blap.NMAL3000.constant.NMAL3000Constant.BIZ_ID;
import static business.blap.NMAL3000.constant.NMAL3000Constant.SCRN_ID_00;
import static business.blap.NMAL3000.constant.NMAL3000Constant.ZZZM9003I;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsgArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL3000.NMAL3000CMsg;
import business.blap.NMAL3000.NMAL3000Query;
import business.blap.NMAL3000.NMAL3000SMsg;
import business.blap.NMAL3000.constant.NMAL3000Constant;
import business.blap.NMAL7010.NMAL7010SMsg;
import business.db.DS_DLR_AUTH_INFOTMsg;
import business.db.MKT_MDLTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL3000CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   SRAA            Y.Chen          Update          QC#5169
 * 2016/11/16   Fujitsu         M.Ohno          Update          S21_NA#2680
 * 2018/12/19   Fujitsu         H.Kumagai       Update          QC#29661
 *</pre>
 */
public class NMAL3000CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * callNszc0330forSearchOption
     * @param bizMsg NMAL3000CMsg
     * @param glblMsg NMAL3000SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NMAL3000Constant.BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctCustNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlCd, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctDlrCd, pMsg.srchOptTxt_03);

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_04.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, pMsg.srchOptTxt_04.getValue());
        } else {
            bizMsg.effFromDt.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_05.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt, pMsg.srchOptTxt_05.getValue());
        } else {
            bizMsg.effThruDt.clear();
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.slsAuthFlg_SA, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsAuthFlg_SE, pMsg.srchOptTxt_07);
    }

    /**
     * callNszc0330forDeleteSearch
     * @param bizMsg NLBL3080CMsg
     * @param glblMsg NLBL3080SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NMAL3000Constant.BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I"
                    , new String[] {converter.convLabel2i18nLabel(NMAL3000Constant.SCRN_ID_00, "Delete Search") });
        }
    }

    /**
     * callNszc0330forSaveSearch
     * @param bizMsg NMAL3000CMsg
     * @param glblMsg NMAL3000SMsg
     * @param usrId NMAL3000SMsg
     * @param glblCmpyCd NMAL3000SMsg
     */
    public static void callNszc0330forSaveSearch(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.dsAcctCustNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.mktMdlCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.dsAcctDlrCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.effThruDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.slsAuthFlg_SA);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.slsAuthFlg_SE);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    /**
     * callNszc0330
     * @param bizMsg NMAL3000CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NMAL3000CMsg bizMsg, NSZC033001PMsg pMsg) {

        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NMAL3000CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL3000CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

    private static boolean isSameSaveSearchName(NMAL3000CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NMAL3000CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NMAL3000CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL3000CMsg
     * @param glblMsg NMAL3000SMsg
     */
    public static void updateGlblMsg(NMAL3000CMsg bizMsg, NMAL3000SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }

        bizMsg.setCommitSMsg(true);
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL3000CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NMAL3000CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NMAL3000Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     * @param pageIndex int
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, int pageIndex) {

        NMAL3000CMsg bizMsg = (NMAL3000CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (pageIndex / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

                // Add Start 2016/11/16 M.Ohno S21_NA#2680
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).xxRecHistCratByNm_A, ZYPRecHistUtil.getFullNameForRecHist(bizMsg.A.no(indexOfCMsg).xxRecHistCratByNm_A.getValue()));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexOfCMsg).xxRecHistUpdByNm_A, ZYPRecHistUtil.getFullNameForRecHist(bizMsg.A.no(indexOfCMsg).xxRecHistUpdByNm_A.getValue()));
                // Add End   2016/11/16 M.Ohno S21_NA#2680
            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

     /**
      * <pre>
      * checkDupAttrb
      * </pre>
      * @param msgAry EZDMsgArray
      * @param chkItemNmList Message Item Name for check.
      * @return Duplicate Index
      */
     public static Integer[] checkDupAttrb(EZDMsgArray msgAry, String[] chkItemNmList) {
         return checkDupAttrbByGrp(msgAry, chkItemNmList, null);
     }

     /**
      * <pre>
      * checkDupAttrbByGrp
      * </pre>
      * @param msgAry EZDMsgArray
      * @param chkItemNmList Message Item Name for check
      * @param grpItemNmList Message Item name for grouping
      * @return Duplicate Index
      */
     public static Integer[] checkDupAttrbByGrp(EZDMsgArray msgAry, String[] chkItemNmList, String[] grpItemNmList) {

         if (msgAry.getValidCount() <= 1) {
             return new Integer[0];
         }

         if (chkItemNmList == null
                 || chkItemNmList.length == 0) {
             Integer[] errIdxList = new Integer[msgAry.getValidCount()];
             for (int i = 0; i < errIdxList.length; i++) {
                 errIdxList[i] = i;
             }
             return errIdxList;
         }

         List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());

         for (int i = 0; i < msgAry.getValidCount() - 1; i++) {

             if (errIdxList.contains(i)) {
                 continue;
             }

             EZDMsg msg1 = msgAry.get(i);
             String grpKey1 = makeCompVal(msg1, grpItemNmList);
             String compVal1 = makeCompVal(msg1, chkItemNmList);
             boolean dupFlg = false;

             for (int j = i + 1; j < msgAry.getValidCount(); j++) {

                 EZDMsg msg2 = msgAry.get(j);
                 String grpKey2 = makeCompVal(msg2, grpItemNmList);
                 String compVal2 = makeCompVal(msg2, chkItemNmList);

                 if (!grpKey1.equals(grpKey2)) {
                     continue;
                 }

                 if (compVal1.equals(compVal2)) {
                     errIdxList.add(j);
                     dupFlg = true;
                 }
             }

             if (dupFlg) {
                 errIdxList.add(0, i);
             }
         }

         return errIdxList.toArray(new Integer[0]);
     }

     /**
      * <pre>
      * checkDupAttrbByGrp
      * </pre>
      * @param msgAry EZDMsgArray
      * @param effFromItemNm Effective From Item Name
      * @param effThruItemNm Effective Thru Item Name
      * @param grpItemNmList Message Item name for grouping
      * @return Duplicate Index
      */
     public static Integer[] checkDupTermByGrp(EZDMsgArray msgAry, String effFromItemNm, String effThruItemNm, String[] grpItemNmList) {

         if (msgAry.getValidCount() <= 1) {
             return new Integer[0];
         }

         if (!ZYPCommonFunc.hasValue(effFromItemNm)
                 || !ZYPCommonFunc.hasValue(effThruItemNm)) {
             Integer[] errIdxList = new Integer[msgAry.getValidCount()];
             for (int i = 0; i < errIdxList.length; i++) {
                 errIdxList[i] = i;
             }
             return errIdxList;
         }

         List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());

         for (int i = 0; i < msgAry.getValidCount() - 1; i++) {

             if (errIdxList.contains(i)) {
                 continue;
             }

             EZDMsg msg1 = msgAry.get(i);
             String grpKey1 = makeCompVal(msg1, grpItemNmList);
             String effFromDt1 = msg1.getValueString(effFromItemNm, 0);
             String effThruDt1 = msg1.getValueString(effThruItemNm, 0);
             if (!ZYPCommonFunc.hasValue(effThruDt1)) {
                 effThruDt1 = "99991231";
             }

             boolean dupFlg = false;
             for (int j = i + 1; j < msgAry.getValidCount(); j++) {

                 EZDMsg msg2 = msgAry.get(j);
                 String grpKey2 = makeCompVal(msg2, grpItemNmList);
                 String effFromDt2 = msg2.getValueString(effFromItemNm, 0);
                 String effThruDt2 = msg2.getValueString(effThruItemNm, 0);
                 if (!ZYPCommonFunc.hasValue(effThruDt2)) {
                     effThruDt2 = "99991231";
                 }

                 if (!grpKey1.equals(grpKey2)) {
                     continue;
                 }

                 if (effFromDt1.compareTo(effFromDt2) < 0) {

                     if (effThruDt1.compareTo(effFromDt2) >= 0) {
                         // error
                         errIdxList.add(j);
                         dupFlg = true;
                     }

                 } else if (effFromDt1.compareTo(effFromDt2) > 0) {

                     if (effFromDt1.compareTo(effThruDt2) <= 0) {
                         // error
                         errIdxList.add(j);
                         dupFlg = true;
                     }

                 } else {
                     // error
                     errIdxList.add(j);
                     dupFlg = true;
                 }

             }

             if (dupFlg) {
                 errIdxList.add(0, i);
             }
         }

         return errIdxList.toArray(new Integer[0]);
     }

     /**
      * <pre>
      * makeCompVal
      * </pre>
      * @param msg EZDMsg
      * @param itemNmList Message Item Name
      * @return
      */
     private static String makeCompVal(EZDMsg msg, String[] itemNmList) {

         if (itemNmList == null) {
             return "";
         }

         StringBuilder compVal = new StringBuilder();

         for (String chkAttrbNm : itemNmList) {
             EZDItemAttribute itemAttrb = msg.getAttr(chkAttrbNm);

             String attrbVal = "";
             switch (itemAttrb.getType()) {
                 case EZDItemAttrDefines.TYPE_SEISU_SYOSU:
                     attrbVal = msg.getValueBigDecimal(chkAttrbNm, 0).toString();
                     break;
                 default:
                     attrbVal = msg.getValueString(chkAttrbNm, 0);
             }
             compVal.append(attrbVal);
             compVal.append(",");
         }

         return compVal.toString();
     }

     // Add Start 2018/11/28 QC#27336
     /**
      * <pre>
      * checkOverlapData
      * </pre>
      * @param glblMsg NMAL3000SMsg
      * @param i int
      * @param mkttms MKT_MDLTMsg
      * @return
      */
     public static boolean checkOverlapData(NMAL3000SMsg glblMsg, int i, MKT_MDLTMsg mkttms) {

         S21SsmEZDResult ssmResult = NMAL3000Query.getInstance().getOverlapCount(glblMsg, i, mkttms);

         BigDecimal resultCnt = (BigDecimal) ssmResult.getResultObject();
         
         if (resultCnt.compareTo(BigDecimal.valueOf(1)) <= 0) {
             return false;
         } else {
             return true;
         }
     }
     // Add End 2018/11/28 QC#27336

     // 2018/12/19 Add Start QC#29661
     /**
      * Select Unselect check Flag.
      * @param glblMsg NMAL3000SMsg
      * @param chkFlg String
      */
     public static void selectUnselect(NMAL3000SMsg glblMsg, String chkFlg) {
         for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
             if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                 ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxChkBox_A, chkFlg);
             } else {
                 glblMsg.A.no(i).xxChkBox_A.clear();
             }
         }
     }
     // 2018/12/19 Add End   QC#29661

}
