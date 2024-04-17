/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NMAL6710.common;

import static business.blap.NMAL6710.constant.NMAL6710Constant.DISP_HRCH_ACCT_CD_ALL;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DS_ACCT_RELN_TP_HRCH;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DS_ACCT_RELN_TP_LEASE;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DS_ACCT_RELN_TP_MYCSA;
import static business.blap.NMAL6710.constant.NMAL6710Constant.DS_ACCT_RELN_TP_RELN;
import static business.blap.NMAL6710.constant.NMAL6710Constant.NMAM8233E;
import static business.blap.NMAL6710.constant.NMAL6710Constant.NMAM8234E;
import static business.blap.NMAL6710.constant.NMAL6710Constant.NMAM8676W;
import static business.blap.NMAL6710.constant.NMAL6710Constant.RELN_BILL_ONLY;
import static business.blap.NMAL6710.constant.NMAL6710Constant.RELN_BILL_SHIP;
import static business.blap.NMAL6710.constant.NMAL6710Constant.RELN_SHIP_ONLY;
import static business.blap.NYEL8810.constant.NYEL8810Constant.BIZ_ID;
import static business.blap.NYEL8810.constant.NYEL8810Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL6710.NMAL6710CMsg;
import business.blap.NMAL6710.NMAL6710Query;
import business.blap.NMAL6710.NMAL6710SMsg;
import business.blap.NMAL6710.NMAL6710_CSMsg;
import business.blap.NMAL6710.constant.NMAL6710Constant;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 10/01/2015   Fujitsu         N.Sugiura       Update          CSA
 * 02/26/2016   SRAA            Y.Chen          Update          QC#3290
 * 04/13/2016   SRAA            Y.Chen          Update          QC#5890
 * 06/21/2016   SRAA            Y.Chen          Update          QC#6189
 * 09/21/2016   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 11/11/2016   Fujitsu         N.Sugiura       Update          QC#15431
 * 11/13/2017   Fujitsu         H.Sugawara      Update          QC#17322(Sol#174)
 *</pre>
 */
public class NMAL6710CommonLogic {

    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * getDsAccountList
     * @param bizMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     * @param globalCompanyCode String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getDsAccountList(NMAL6710CMsg bizMsg, NMAL6710SMsg sMsg, String globalCompanyCode) {
        S21SsmEZDResult ssmResult = NMAL6710Query.getInstance().getDsAccountList(bizMsg, sMsg, globalCompanyCode);

        return ssmResult;
    }

    /**
     * getDsAcctRelnTpNm
     * @param dsAcctRelnTpCd String
     * @return String
     */
    public static String getDsAcctRelnTpNm(String dsAcctRelnTpCd) {
        String dsAcctRelnTpNm = "";

        if (DS_ACCT_RELN_TP.PARENT_ACCOUNT.equals(dsAcctRelnTpCd)) {
            dsAcctRelnTpNm = DS_ACCT_RELN_TP_HRCH;
        }
        if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(dsAcctRelnTpCd)) {
            dsAcctRelnTpNm = DS_ACCT_RELN_TP_RELN;
        }
        if (DS_ACCT_RELN_TP.LEASE_ACCOUNT.equals(dsAcctRelnTpCd)) {
            dsAcctRelnTpNm = DS_ACCT_RELN_TP_LEASE;
        }
        // Add Start 2017/11/13 QC#17322(Sol#174)
        if (DS_ACCT_RELN_TP.MYCSA_ACCOUNT.equals(dsAcctRelnTpCd)) {
            dsAcctRelnTpNm = DS_ACCT_RELN_TP_MYCSA;
        }
        // Add End 2017/11/13 QC#17322(Sol#174)

        return dsAcctRelnTpNm;
    }
    /**
     * getRelationship
     * @param billToCustCd String
     * @param shipToCustCd String
     * @return String
     */
    public static String getRelationship(String billToCustCd, String shipToCustCd) {
        String relationship = "";
        if (ZYPCommonFunc.hasValue(billToCustCd) && ZYPCommonFunc.hasValue(shipToCustCd)) {
            relationship = RELN_BILL_SHIP;
        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {
            relationship = RELN_BILL_ONLY;
        } else if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            relationship = RELN_SHIP_ONLY;
        }
        return relationship;
    }
    /**
     * Check Search Condition.
     * @param cMsg NMAL6710CMsg
     * @return boolean
     */
    public static boolean checkSearchCondition(NMAL6710CMsg cMsg) {

        // Check Display Address, Display Hierarchy Accounts
        if (!ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_01.getValue())
                && !DISP_HRCH_ACCT_CD_ALL.equals(cMsg.xxAcctSrchDplyHrchCd_D3.getValue())) {
            cMsg.xxChkBox_01.setErrorInfo(1, NMAM8233E);
            cMsg.xxAcctSrchDplyHrchCd_D3.setErrorInfo(1, NMAM8233E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.xxAcctSrchDplyRelnCd_D4)) {
            if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_RT)
                    || ZYPCommonFunc.hasValue(cMsg.dsAcctNm_RT)
                    || ZYPCommonFunc.hasValue(cMsg.xxAllLineAddr_RT)) {
                cMsg.dsAcctNum_RT.setErrorInfo(1, NMAM8234E);
                cMsg.dsAcctNm_RT.setErrorInfo(1, NMAM8234E);
                cMsg.xxAllLineAddr_RT.setErrorInfo(1, NMAM8234E);
                return false;
            }
        }

        return true;
    }
    /**
     * <pre>
     * copyFromSMsgOntoCmsg
     * </pre>
     * 
     * @param cMsg NMAL6710CMsg
     * @param sMsg NMAL6710SMsg
     */
    public static void copyFromSMsgOntoCmsg(NMAL6710CMsg cMsg, NMAL6710SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.C.length(); i++) {
            if (i < sMsg.C.getValidCount()) {

                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.C.setValidCount(i - pagenationFrom);
        setPageNum(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.C.getValidCount()), sMsg.C.getValidCount());
    }
    /**
    * <pre>
    * setPageNum
    * </pre>
    * 
    * @param cMsg NMAL6710CMsg
    * @param fromCnt PageShowFromNum
    * @param toCnt PageShowToNum
    * @param allCnt PageShowOfNum
    */
   public static void setPageNum(NMAL6710CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
       cMsg.xxPageShowFromNum.setValue(fromCnt);
       cMsg.xxPageShowToNum.setValue(toCnt);
       cMsg.xxPageShowOfNum.setValue(allCnt);
   }
   /**
    * <pre>
    * clearPageNum
    * </pre>
    * 
    * @param cMsg NMAL6710CMsg
    */
   public static void clearPageNum(NMAL6710CMsg cMsg) {
       cMsg.xxPageShowFromNum.clear();
       cMsg.xxPageShowToNum.clear();
       cMsg.xxPageShowOfNum.clear();
   }
   /**
    * Check Max Count for Line
    * @param cMsg NMAL6710CMsg
    * @param lineCnt String
    * @param maxCnt String
    * @return true/false
    */
    public static boolean checkMaxCount(NMAL6710CMsg cMsg, int lineCnt, int maxCnt) {

       if (lineCnt > maxCnt) {
           // Mod Start 2018/02/23 QC#22570
           //cMsg.setMessageInfo("NZZM0001W");
            cMsg.setMessageInfo(NMAM8676W, new String[] {String.valueOf(maxCnt) });
           // Mod End 2018/02/23 QC#22570
           return false;
       }
       return true;
   }
   /**
    * getInclRelnAcct
    * @param bizMsg NMAL6710CMsg
    * @param sharedMsg NMAL6710SMsg
    * @param queryResCnt int
    * @param resultList List<Map<String, Object>>
    */
   public static void getInclRelnAcct(NMAL6710CMsg bizMsg, NMAL6710SMsg sharedMsg, int queryResCnt, List<Map<String, Object>> resultList) {
       int j = 0;
       String befAccountNum = "";
       for (int i = 0; i < queryResCnt; i++) {
           if (!NMAL6710CommonLogic.checkMaxCount(bizMsg, j + 1, sharedMsg.C.length())) {

               break;
           }
           Map map = (Map) resultList.get(i);
           // QC#5890
           // NMAL6710CommonLogic.setAccountInfo(sharedMsg.C.no(j), map);
           // j++;
           // String orgAccountNum = sharedMsg.C.no(i).dsAcctNum_C2.getValue();
           String orgAccountNum = (String) map.get("ORIG_ACCT_NUM");
           
           if (!befAccountNum.equals(orgAccountNum)) {
               S21SsmEZDResult ssmResult = null;
               ssmResult = NMAL6710Query.getInstance().getRelatedAccountList(bizMsg, sharedMsg, orgAccountNum);
               if (ssmResult.isCodeNormal()) {
                   for (int l = 0; l < ssmResult.getQueryResultCount(); l++) {
                       if (!NMAL6710CommonLogic.checkMaxCount(bizMsg, j + 1, sharedMsg.C.length())) {

                           break;
                       }
                       List<Map<String, Object>> resultRelnList = (List<Map<String, Object>>) ssmResult.getResultObject();
                       map = (Map) resultRelnList.get(l);
                       NMAL6710CommonLogic.setAccountInfo(bizMsg, sharedMsg.C.no(j), map);
                       j++;

                   }
                   befAccountNum = orgAccountNum;
               }
           }

       }

       sharedMsg.C.setValidCount(j);
   }
   /**
    * setAccountInfo
    * @param csMsg NMAL6710_CSMsg
    * @param map Map
    */
   public static void setAccountInfo(NMAL6710CMsg cMsg, NMAL6710_CSMsg csMsg, Map map) {
       ZYPEZDItemValueSetter.setValue(csMsg.dsAcctNum_C1, (String) map.get("DS_ACCT_NUM"));
       ZYPEZDItemValueSetter.setValue(csMsg.dsAcctRelnTpNm_C1, NMAL6710CommonLogic.getDsAcctRelnTpNm((String) map.get("DS_ACCT_RELN_TP_CD")));
       ZYPEZDItemValueSetter.setValue(csMsg.dsAcctNm_C1, (String) map.get("DS_ACCT_NM"));
       ZYPEZDItemValueSetter.setValue(csMsg.xxAllLineAddr_C1, (String) map.get("SHIP_FROM_FULL_CMPY_ADDR"));
       ZYPEZDItemValueSetter.setValue(csMsg.locNum_C1, (String) map.get("LOC_NUM"));
       ZYPEZDItemValueSetter.setValue(csMsg.xxYesNoCd_C1, (String) map.get("XX_YES_NO_CD"));
       ZYPEZDItemValueSetter.setValue(csMsg.dsAcctTpNm_C1, (String) map.get("DS_ACCT_TP_NM"));
       ZYPEZDItemValueSetter.setValue(csMsg.xxCtlNm_C1, NMAL6710CommonLogic.getRelationship((String) map.get("BILL_TO_CUST_CD"), (String) map.get("SHIP_TO_CUST_CD")));
       ZYPEZDItemValueSetter.setValue(csMsg.dsAcctNum_C2, (String) map.get("ORIG_ACCT_NUM"));
       ZYPEZDItemValueSetter.setValue(csMsg.relnDsAcctNum_C1, (String) map.get("RELN_DS_ACCT_NUM"));
       ZYPEZDItemValueSetter.setValue(csMsg.billToCustCd_C1, (String) map.get("BILL_TO_CUST_CD"));
       ZYPEZDItemValueSetter.setValue(csMsg.shipToCustCd_C1, (String) map.get("SHIP_TO_CUST_CD"));
       // QC#3290
       ZYPEZDItemValueSetter.setValue(csMsg.xxRgtnStsTxt_C1, getStatusDescriptionByFlag((String) map.get("DS_ACCT_ACTV_CUST_FLG")));
       if (ZYPCommonFunc.hasValue(csMsg.locNum_C1)) {
           ZYPEZDItemValueSetter.setValue(csMsg.xxRgtnStsTxt_C2, getStatusDescriptionByCode((String) map.get("PTY_RGTN_STS_CD")));
           ZYPEZDItemValueSetter.setValue(csMsg.xxRgtnStsTxt_C5, getStatusDescriptionByCode((String) map.get("LOC_RGTN_STS_CD"))); // QC#15431 Add
       }
       if (ZYPCommonFunc.hasValue(csMsg.billToCustCd_C1)) {
           ZYPEZDItemValueSetter.setValue(csMsg.xxRgtnStsTxt_C3, getStatusDescriptionByCode((String) map.get("BILL_RGTN_STS_CD")));
       }
       if (ZYPCommonFunc.hasValue(csMsg.shipToCustCd_C1)) {
           ZYPEZDItemValueSetter.setValue(csMsg.xxRgtnStsTxt_C4, getStatusDescriptionByCode((String) map.get("SHIP_RGTN_STS_CD")));
       }
       // QC#6189
       if(!ZYPCommonFunc.hasValue(cMsg.xxAcctSrchDplyRelnCd_D4)){
           ZYPEZDItemValueSetter.setValue(csMsg.relnDsAcctNum_C1, (String) map.get("PRNT_DS_ACCT_NUM"));
       }
   }
   
   public static String getStatusDescriptionByFlag(String actvCustFlg) {
       if(ZYPConstant.FLG_ON_Y.equals(actvCustFlg)){
           return NMAL6710Constant.STS_ACTV;
       }
       return NMAL6710Constant.STS_INCTV;
   }

   public static String getStatusDescriptionByCode(String rgtnStsCd) {
       if(ZYPCommonFunc.hasValue(rgtnStsCd)){
           if(RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)){
               return NMAL6710Constant.STS_ACTV;
           }
           return NMAL6710Constant.STS_INCTV;
       }
       return "";
   }

   /**
    * @param glblCmpyCd String
    * @param targetDsAcctNum String
    * @return List
    */
   public static List getMostParentNode(NMAL6710CMsg cMsg, String glblCmpyCd, String targetDsAcctNum) {
       S21SsmEZDResult ssmResult = NMAL6710Query.getInstance().getMostParentNode(cMsg, glblCmpyCd, targetDsAcctNum);

       List resultList = (List) ssmResult.getResultObject();
       if (!ssmResult.isCodeNormal()) {
           return null;
       }
       return resultList;
   }
   /**
    * getTreeListParent
    * @param cMsg NMAL6710CMsg
    * @param glblCmpyCd String
    * @param parentDsAcctNum String
    * @param targetDsAcctNum String
    * @return List
    */
    public static List getTreeListParent(NMAL6710CMsg cMsg, String glblCmpyCd, String parentDsAcctNum, String targetDsAcctNum) {
        S21SsmEZDResult ssmResult = NMAL6710Query.getInstance().getTreeListParent(cMsg, glblCmpyCd, parentDsAcctNum, targetDsAcctNum);
            List resultList = (List) ssmResult.getResultObject();
            if (!ssmResult.isCodeNormal()) {
                return null;
            }
            return resultList;
    }
    /**
     * getTreeListLeaseReln
     * @param cMsg NMAL6710CMsg
     * @param glblCmpyCd String
     * @param parentDsAcctNum String
     * @param targetDsAcctNum String
     * @return List
     */
     public static List getTreeListLeaseReln(NMAL6710CMsg cMsg, String glblCmpyCd, String parentDsAcctNum, String targetDsAcctNum) {
         S21SsmEZDResult ssmResult = NMAL6710Query.getInstance().getTreeListLeaseReln(cMsg, glblCmpyCd, parentDsAcctNum, targetDsAcctNum);
             List resultList = (List) ssmResult.getResultObject();
             if (!ssmResult.isCodeNormal()) {
                 return null;
             }
             return resultList;
     }
     public static List getTreeListLocation(NMAL6710CMsg cMsg, String glblCmpyCd, String dsAcctNum, String tagetDsAcctNum) {
         S21SsmEZDResult ssmResult = NMAL6710Query.getInstance().getTreeListLocation(cMsg, glblCmpyCd, dsAcctNum, tagetDsAcctNum);
             List resultList = (List) ssmResult.getResultObject();
             if (!ssmResult.isCodeNormal()) {
                 return null;
             }
             return resultList;
     }

     /*
      * Save Search Option
      */
     public static void callNszc0330forDeleteSearch(NMAL6710CMsg bizMsg, NMAL6710SMsg glblMsg, String userId, String glblCmpyCd) {
         NSZC033001PMsg pMsg = new NSZC033001PMsg();
         pMsg.clear();

         ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
         ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

         if (callNszc0330(bizMsg, pMsg)) {
             createSavedSearchOptionsPullDown(bizMsg, userId, glblCmpyCd);
             bizMsg.srchOptNm_S.clear();
             bizMsg.setMessageInfo("ZZZM9003I" //
                     , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
         }
     }

     public static boolean isExistSaveSearchName(NMAL6710CMsg bizMsg) {
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

     public static void callNszc0330forSaveSearch(NMAL6710CMsg bizMsg, NMAL6710SMsg glblMsg, String usrId, String glblCmpyCd) {
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

         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.xxAcctSrchModeCd_D1);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.xxAcctSrchStsCd_D2);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.xxChkBox_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.xxAcctSrchDplyHrchCd_D3);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.dsAcctNm_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.xxAllLineAddr_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.ctyAddr_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.stCd_DP);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.postCd_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.dsAcctNum_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.locNum_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.dbaNm_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.dsAcctLegalNm_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.dsAcctGrpCd_DP);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.dsAcctGrpDescTxt_DP);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.dsAcctClsCd_DP);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.dsLocNm_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.dsAcctDunsNum_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.dsUltDunsNum_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.dsCustSicDescTxt_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.ctacPsnLastNm_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, bizMsg.ctacPsnFirstNm_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, bizMsg.ctacPsnTelNum_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, bizMsg.dsXrefAcctTpCd_DP);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, bizMsg.dsXrefAcctCd_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, bizMsg.billToCustCd_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, bizMsg.shipToCustCd_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, bizMsg.dsXtrnlRefTxt_01);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, bizMsg.dsAcctTpCd_DP);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, bizMsg.dsAcctItrlFlg_C1);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, bizMsg.xxAcctSrchDplyRelnCd_D4);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_33, bizMsg.dsAcctNm_RT);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_34, bizMsg.dsAcctNum_RT);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_35, bizMsg.xxAllLineAddr_RT);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_36, bizMsg.xxChkBox_02);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_37, bizMsg.xxChkBox_03);

         if (callNszc0330(bizMsg, pMsg)) {
             createSavedSearchOptionsPullDown(bizMsg, usrId, glblCmpyCd);
             ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
             bizMsg.srchOptNm_S.clear();
             bizMsg.setMessageInfo("ZZZM9003I" //
                     , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
         }
     }

     /**
      * setSelectSaveSearchName
      * @param pMsg NSZC033001PMsg
      * @param bizMsg NYEL8810CMsg
      */
     public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL6710CMsg bizMsg) {
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


     private static boolean isSameSaveSearchName(NMAL6710CMsg cMsg) {
         if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
             return false;
         }
         if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
             return false;
         }

         for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
             if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                 return false;
             }
             if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                 if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                     return true;
                 }
                 return false;
             }
         }
         return false;
     }

     
     private static boolean callNszc0330(NMAL6710CMsg bizMsg, NSZC033001PMsg pMsg) {
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
      * createSavedSearchOptionsPullDown
      * @param bizMsg NMAL6710CMsg
      * @param srchOptUsrId user id
      */
     public static void createSavedSearchOptionsPullDown(NMAL6710CMsg bizMsg, String srchOptUsrId, String glblCmpyCd) {
         S21SsmEZDResult ssmResult = NYXC880002Query.getInstance().getSavedSearchOptionList(srchOptUsrId, BIZ_ID, glblCmpyCd);
         if (!ssmResult.isCodeNormal()) {
             bizMsg.srchOptPk_L.clear();
             bizMsg.srchOptNm_L.clear();
             return;
         }

         bizMsg.srchOptPk_L.clear();
         bizMsg.srchOptNm_L.clear();
         @SuppressWarnings("unchecked")
         List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
         for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
             Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
             bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
             bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
         }

     }

     /**
      * callNszc0330forSearchOption
      * @param cMsg NMAL6710CMsg
      * @param sMsg NMAL6710SMsg
      * @param usrId String
      * @param glblCmpyCd String
      */
     public static void callNszc0330forSearchOption(NMAL6710CMsg bizMsg, NMAL6710SMsg glblMsg, String usrId, String glblCmpyCd) {

         NSZC033001PMsg pMsg = new NSZC033001PMsg();
         pMsg.clear();

         ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
         ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
         ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

         if (!callNszc0330(bizMsg, pMsg)) {
             return; // error
         }

         ZYPEZDItemValueSetter.setValue(bizMsg.xxAcctSrchModeCd_D1, pMsg.srchOptTxt_01);
         ZYPEZDItemValueSetter.setValue(bizMsg.xxAcctSrchStsCd_D2, pMsg.srchOptTxt_02);
         ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_01, pMsg.srchOptTxt_03);
         ZYPEZDItemValueSetter.setValue(bizMsg.xxAcctSrchDplyHrchCd_D3, pMsg.srchOptTxt_04);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_01, pMsg.srchOptTxt_05);
         ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_01, pMsg.srchOptTxt_06);
         ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr_01, pMsg.srchOptTxt_07);
         ZYPEZDItemValueSetter.setValue(bizMsg.stCd_DP, pMsg.srchOptTxt_08);
         ZYPEZDItemValueSetter.setValue(bizMsg.postCd_01, pMsg.srchOptTxt_09);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_01, pMsg.srchOptTxt_10);
         ZYPEZDItemValueSetter.setValue(bizMsg.locNum_01, pMsg.srchOptTxt_11);
         ZYPEZDItemValueSetter.setValue(bizMsg.dbaNm_01, pMsg.srchOptTxt_12);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctLegalNm_01, pMsg.srchOptTxt_13);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctGrpCd_DP, pMsg.srchOptTxt_14);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctGrpDescTxt_DP, pMsg.srchOptTxt_15);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctClsCd_DP, pMsg.srchOptTxt_16);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsLocNm_01, pMsg.srchOptTxt_17);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctDunsNum_01, pMsg.srchOptTxt_18);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsUltDunsNum_01, pMsg.srchOptTxt_19);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsCustSicDescTxt_01, pMsg.srchOptTxt_20);
         ZYPEZDItemValueSetter.setValue(bizMsg.ctacPsnLastNm_01, pMsg.srchOptTxt_21);
         ZYPEZDItemValueSetter.setValue(bizMsg.ctacPsnFirstNm_01, pMsg.srchOptTxt_23);
         ZYPEZDItemValueSetter.setValue(bizMsg.ctacPsnTelNum_01, pMsg.srchOptTxt_24);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsXrefAcctTpCd_DP, pMsg.srchOptTxt_25);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsXrefAcctCd_01, pMsg.srchOptTxt_26);
         ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_01, pMsg.srchOptTxt_27);
         ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_01, pMsg.srchOptTxt_28);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsXtrnlRefTxt_01, pMsg.srchOptTxt_29);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctTpCd_DP, pMsg.srchOptTxt_30);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctItrlFlg_C1, pMsg.srchOptTxt_31);
         ZYPEZDItemValueSetter.setValue(bizMsg.xxAcctSrchDplyRelnCd_D4, pMsg.srchOptTxt_32);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_RT, pMsg.srchOptTxt_33);
         ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_RT, pMsg.srchOptTxt_34);
         ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_RT, pMsg.srchOptTxt_35);
         ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_02, pMsg.srchOptTxt_36);
         ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_03, pMsg.srchOptTxt_37);

     }

}
