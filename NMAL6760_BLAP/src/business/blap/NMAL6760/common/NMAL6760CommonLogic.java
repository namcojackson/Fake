/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6760.common;

import static business.blap.NMAL6760.constant.NMAL6760Constant.DS_ACCT_RELN_TP_HRCH;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DS_ACCT_RELN_TP_LEASE;
import static business.blap.NMAL6760.constant.NMAL6760Constant.DS_ACCT_RELN_TP_RELN;
import static business.blap.NMAL6760.constant.NMAL6760Constant.NMAM8676W;
import static business.blap.NMAL6760.constant.NMAL6760Constant.RELN_BILL_ONLY;
import static business.blap.NMAL6760.constant.NMAL6760Constant.RELN_BILL_SHIP;
import static business.blap.NMAL6760.constant.NMAL6760Constant.RELN_SHIP_ONLY;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NMAL6760.NMAL6760CMsg;
import business.blap.NMAL6760.NMAL6760Query;
import business.blap.NMAL6760.NMAL6760SMsg;
import business.blap.NMAL6760.NMAL6760_CSMsg;
import business.blap.NMAL6760.constant.NMAL6760Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * NMAL6760CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/26   SRAA            Y.Chen          Update          QC#3290, QC#4389
 * 2016/04/13   SRAA            Y.Chen          Update          QC#5890
 * 2016/11/11   Fujitsu         N.Sugiura       Update          QC#15431
 * 2018/02/23   Fujitsu         Hd.Sugawara     Update          QC#22570
 * 2018/10/24   Fujitsu         H.Kumagai       Update          QC#28866
 *</pre>
 */
public class NMAL6760CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL6760CMsg bizMsg = (NMAL6760CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

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
     * Get account search result
     * @param bizMsg NMAL6760CMsg
     * @param sMsg NMAL6760SMsg
     * @return ssmResult
     */
    public static S21SsmEZDResult getDsAccountList(NMAL6760CMsg bizMsg, NMAL6760SMsg sMsg) {

        S21SsmEZDResult ssmResult = NMAL6760Query.getInstance().getDsAccountList(bizMsg, sMsg);

        return ssmResult;
    }

    /**
     * Get Relation String
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
        return dsAcctRelnTpNm;
    }

    /**
     * Get Relationship String
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
     * <pre>
     * copyFromSMsgOntoCmsg
     * </pre>
     * @param cMsg NMAL6760CMsg
     * @param sMsg NMAL6760SMsg
     */
    public static void copyFromSMsgOntoCmsg(NMAL6760CMsg cMsg, NMAL6760SMsg sMsg) {

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
     * @param cMsg NMAL6760CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NMAL6760CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * <pre>
     * clearPageNum
     * </pre>
     * @param cMsg NMAL6760CMsg
     */
    public static void clearPageNum(NMAL6760CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }
    /**
     * Check Max Count for Line
     * @param cMsg NMAL6760CMsg
     * @param lineCnt String
     * @param maxCnt String
     * @return true/false
     */
    public static boolean checkMaxCount(NMAL6760CMsg cMsg, int lineCnt, int maxCnt) {

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
     * @param bizMsg NMAL6760CMsg
     * @param sharedMsg NMAL6760SMsg
     * @param queryResCnt int
     * @param resultList List<Map<String, Object>>
     */
    public static void getInclRelnAcct(NMAL6760CMsg bizMsg, NMAL6760SMsg sharedMsg, int queryResCnt, List<Map<String, Object>> resultList) {
        int j = 0;
        String befAccountNum = "";
        for (int i = 0; i < queryResCnt; i++) {
            if (!NMAL6760CommonLogic.checkMaxCount(bizMsg, j + 1, sharedMsg.C.length())) {
                break;
            }
            Map map = (Map) resultList.get(i);
            String orgAccountNum = (String) map.get("ORIG_ACCT_NUM");

            if (!befAccountNum.equals(orgAccountNum)) {
                S21SsmEZDResult ssmResult = null;
                ssmResult = NMAL6760Query.getInstance().getRelatedAccountList(bizMsg, sharedMsg, orgAccountNum);
                if (ssmResult.isCodeNormal()) {
                    for (int l = 0; l < ssmResult.getQueryResultCount(); l++) {
                        if (!NMAL6760CommonLogic.checkMaxCount(bizMsg, j + 1, sharedMsg.C.length())) {

                            break;
                        }
                        List<Map<String, Object>> resultRelnList = (List<Map<String, Object>>) ssmResult.getResultObject();
                        map = (Map) resultRelnList.get(l);
                        NMAL6760CommonLogic.setAccountInfo(bizMsg, sharedMsg.C.no(j), map);
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
     * @param cMsg NMAL6760CMsg
     * @param csMsg NMAL6760_CSMsg
     * @param map Map
     */
    public static void setAccountInfo(NMAL6760CMsg cMsg, NMAL6760_CSMsg csMsg, Map map) {
        ZYPEZDItemValueSetter.setValue(csMsg.dsAcctNum_C1, (String) map.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(csMsg.dsAcctRelnTpNm_C1, NMAL6760CommonLogic.getDsAcctRelnTpNm((String) map.get("DS_ACCT_RELN_TP_CD")));
        ZYPEZDItemValueSetter.setValue(csMsg.dsAcctNm_C1, (String) map.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(csMsg.xxAllLineAddr_C1, (String) map.get("SHIP_FROM_FULL_CMPY_ADDR"));
        ZYPEZDItemValueSetter.setValue(csMsg.locNum_C1, (String) map.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(csMsg.dsLocNm_C1, (String) map.get("DS_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(csMsg.ctyAddr_C1, (String) map.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(csMsg.stCd_C1, (String) map.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(csMsg.postCd_C1, (String) map.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(csMsg.xxYesNoCd_C1, (String) map.get("XX_YES_NO_CD"));
        ZYPEZDItemValueSetter.setValue(csMsg.dsAcctTpNm_C1, (String) map.get("DS_ACCT_TP_NM"));
        ZYPEZDItemValueSetter.setValue(csMsg.xxCtlNm_C1, NMAL6760CommonLogic.getRelationship((String) map.get("BILL_TO_CUST_CD"), (String) map.get("SHIP_TO_CUST_CD")));
        ZYPEZDItemValueSetter.setValue(csMsg.billToCustCd_C1, (String) map.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(csMsg.shipToCustCd_C1, (String) map.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(csMsg.firstLineAddr_C1, (String) map.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(csMsg.scdLineAddr_C1, (String) map.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(csMsg.thirdLineAddr_C1, (String) map.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(csMsg.frthLineAddr_C1, (String) map.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(csMsg.provNm_C1, (String) map.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(csMsg.cntyNm_C1, (String) map.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(csMsg.ctryCd_C1, (String) map.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(csMsg.relnDsAcctNum_C1, (String) map.get("RELN_DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(csMsg.dsAcctNum_C2, (String) map.get("ORIG_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(csMsg.firstRefCmntTxt_C1, (String) map.get("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(csMsg.scdRefCmntTxt_C1, (String) map.get("SCD_REF_CMNT_TXT"));
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
        if (!ZYPCommonFunc.hasValue(cMsg.xxAcctSrchDplyRelnCd_D4)) {
            ZYPEZDItemValueSetter.setValue(csMsg.relnDsAcctNum_C1, (String) map.get("PRNT_DS_ACCT_NUM"));
        }
    }

    private static String getStatusDescriptionByFlag(String actvCustFlg) {
        if (ZYPConstant.FLG_ON_Y.equals(actvCustFlg)) {
            return NMAL6760Constant.STS_ACTV;
        }
        return NMAL6760Constant.STS_INCTV;
    }

    private static String getStatusDescriptionByCode(String rgtnStsCd) {
        if (ZYPCommonFunc.hasValue(rgtnStsCd)) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                return NMAL6760Constant.STS_ACTV;
            }
            return NMAL6760Constant.STS_INCTV;
        }
        return "";
    }

     // 2018/10/24 Add Start QC#28866
    public static void outputSearchLog(NMAL6760CMsg cMsg){
        StringBuffer sb = new StringBuffer();
        sb.append("NMAL6760 Search Condition : ");
        if (hasValue(cMsg.xxAcctSrchModeCd_D1)) {
            sb.append(" Search Mode[").append(cMsg.xxAcctSrchModeCd_D1.getValue()).append("]");
        } else if(hasValue(cMsg.xxAcctSrchModeCd_01.no(0).getValue())) {
            sb.append(" Search Mode[").append(cMsg.xxAcctSrchModeCd_01.no(0).getValue()).append("]");
        }
            
        if (hasValue(cMsg.xxAcctSrchStsCd_D2)) {
            sb.append(" Status[").append(cMsg.xxAcctSrchStsCd_D2.getValue()).append("]");
        }
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_01.getValue())) {
            sb.append(" Display Addresses[").append(cMsg.xxChkBox_01.getValue()).append("]");
        }
        if (hasValue(cMsg.xxAcctSrchDplyHrchCd_D3)) {
            sb.append(" Display Hierarchy Account[").append(cMsg.xxAcctSrchDplyHrchCd_D3.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctNm_01)) {
            sb.append(" Account Name[").append(cMsg.dsAcctNm_01.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctDunsNum_01)) {
            sb.append(" DUNS#[").append(cMsg.dsAcctDunsNum_01.getValue()).append("]");
        }
        if (hasValue(cMsg.dsXtrnlRefTxt_01)) {
            sb.append(" External Ref#[").append(cMsg.dsXtrnlRefTxt_01.getValue()).append("]");
        }
        if (hasValue(cMsg.xxAllLineAddr_01)) {
            sb.append(" Address#[").append(cMsg.xxAllLineAddr_01.getValue()).append("]");
        }
        if (hasValue(cMsg.dsUltDunsNum_01)) {
            sb.append(" UDUNS#[").append(cMsg.dsUltDunsNum_01.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctTpCd_DP)) {
            sb.append(" Category[").append(cMsg.dsAcctTpCd_DP.getValue()).append("]");
        }
        if (hasValue(cMsg.ctyAddr_01)) {
            sb.append(" City[").append(cMsg.ctyAddr_01.getValue()).append("]");
        }
        if (hasValue(cMsg.stCd_DP)) {
            sb.append(" State[").append(cMsg.stCd_DP.getValue()).append("]");
        }
        if (hasValue(cMsg.dsCustSicDescTxt_01)) {
            sb.append(" Industry[").append(cMsg.dsCustSicDescTxt_01.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctItrlFlg_C1)) {
            sb.append(" Internal / External[").append(cMsg.dsAcctItrlFlg_C1.getValue()).append("]");
        }
        if (hasValue(cMsg.postCd_01)) {
            sb.append(" Postal Code[").append(cMsg.postCd_01.getValue()).append("]");
        }
        if (hasValue(cMsg.ctacPsnLastNm_01)) {
            sb.append(" Ctac Last Name[").append(cMsg.ctacPsnLastNm_01.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctNum_01)) {
            sb.append(" Account#[").append(cMsg.dsAcctNum_01.getValue()).append("]");
        }
        if (hasValue(cMsg.ctacPsnFirstNm_01)) {
            sb.append(" Ctac First Name[").append(cMsg.ctacPsnFirstNm_01.getValue()).append("]");
        }
        if (hasValue(cMsg.xxAcctSrchDplyRelnCd_D4)) {
            sb.append(" Display Related Accts[").append(cMsg.xxAcctSrchDplyRelnCd_D4.getValue()).append("]");
        }
        if (hasValue(cMsg.locNum_01)) {
            sb.append(" Location#[").append(cMsg.locNum_01.getValue()).append("]");
        }
        if (hasValue(cMsg.ctacPsnTelNum_01)) {
            sb.append(" Ctac Phone#[").append(cMsg.ctacPsnTelNum_01.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctNm_RT)) {
            sb.append(" Related Acct Name[").append(cMsg.dsAcctNm_RT.getValue()).append("]");
        }
        if (hasValue(cMsg.dbaNm_01)) {
            sb.append(" DBA Name[").append(cMsg.dbaNm_01.getValue()).append("]");
        }
        if (hasValue(cMsg.ctacPsnEmlAddr_01)) {
            sb.append(" Ctac Email#[").append(cMsg.ctacPsnEmlAddr_01.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctNum_RT)) {
            sb.append(" Related Acct#[").append(cMsg.dsAcctNum_RT.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctLegalNm_01)) {
            sb.append(" Acct Legal Name[").append(cMsg.dsAcctLegalNm_01.getValue()).append("]");
        }
        if (hasValue(cMsg.dsXrefAcctTpCd_DP)) {
            sb.append(" Cross Ref Category[").append(cMsg.dsXrefAcctTpCd_DP.getValue()).append("]");
        }
        if (hasValue(cMsg.xxAllLineAddr_RT)) {
            sb.append(" Related Address[").append(cMsg.xxAllLineAddr_RT.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctGrpCd_DP)) {
            sb.append(" Acct Group Name[").append(cMsg.dsAcctGrpCd_DP.getValue()).append("]");
        }
        if (hasValue(cMsg.dsXrefAcctCd_01)) {
            sb.append(" Cross Ref#[").append(cMsg.dsXrefAcctCd_01.getValue()).append("]");
        }
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_02.getValue())) {
            sb.append(" Display Inactive Location[").append(cMsg.xxChkBox_02.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctClsCd_DP)) {
            sb.append(" Acct Classification[").append(cMsg.dsAcctClsCd_DP.getValue()).append("]");
        }
        if (hasValue(cMsg.billToCustCd_01)) {
            sb.append(" Bill To Code[").append(cMsg.billToCustCd_01.getValue()).append("]");
        }
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_03.getValue())) {
            sb.append(" Display Inactive Bill to / Ship to[").append(cMsg.xxChkBox_03.getValue()).append("]");
        }
        if (hasValue(cMsg.dsLocNm_01)) {
            sb.append(" Location Name[").append(cMsg.dsLocNm_01.getValue()).append("]");
        }
        if (hasValue(cMsg.shipToCustCd_01)) {
            sb.append(" Ship To Code[").append(cMsg.shipToCustCd_01.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
     // 2018/10/24 Add End   QC#28866
}
