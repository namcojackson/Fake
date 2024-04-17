/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2410.common;

import static business.blap.NWAL2410.constant.NWAL2410Constant.DATE_TIME_LENGTH;
import static business.blap.NWAL2410.constant.NWAL2410Constant.KEY_CODE_DISP_PK;
import static business.blap.NWAL2410.constant.NWAL2410Constant.NWAM0925E;
import static business.blap.NWAL2410.constant.NWAL2410Constant.NZZM0001W;
import static business.blap.NWAL2410.constant.NWAL2410Constant.PSN_CODE;
import static business.blap.NWAL2410.constant.NWAL2410Constant.ZZZM9006E;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import business.blap.NWAL2410.NWAL2410CMsg;
import business.blap.NWAL2410.NWAL2410SMsg;
import business.blap.NWAL2410.NWAL2410_ACMsgArray;
import business.blap.NWAL2410.NWAL2410_ASMsgArray;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL2410CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 * 2017/02/17   Fujitsu         N.Aoyama        Update          QC#17676
 * 2017/02/21   Fujitsu         N.Aoyama        Update          QC#17676-2
 *</pre>
 */
public class NWAL2410CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWAL2410CMsg bizMsg = (NWAL2410CMsg) cMsg;

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
     * It copy 'NWAL2410CMsg.A' to 'NWAL2410SMsg.A' .
     * @param bizMsg NWAL2410CMsg
     * @param glblMsg NWAL2410SMsg
     */
    public static void saveCurrentPageToSMsg(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        bizMsg.clearErrorInfo();
        bizMsg.A.clearErrorInfo();
        glblMsg.clearErrorInfo();
        glblMsg.A.clearErrorInfo();

        int fromIdx = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(fromIdx + i), null);
        }
    }

    /**
     * getPageStartRowIndex
     * @param bizMsg NWAL2410CMsg
     * @param index int
     * @return startIndex
     */
    public static int getPageStartRowIndex(NWAL2410CMsg bizMsg, int index) {
        int startIndex = (index / bizMsg.A.length()) * bizMsg.A.length();
        return startIndex;
    }

    /**
     * Set Result Data
     * @param ssmResult S21SsmEZDResult
     * @param bizMsg NWAL2410CMsg
     * @param glblMsg NWAL2410SMsg
     */
    public static void setResult(S21SsmEZDResult ssmResult, NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg) {

        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        int i = 0;
        for (; i < resultList.size(); i++) {

            if (i == glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }

            Map<String, String> map = resultList.get(i);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtOrgCd_A, map.get("DOC_MGT_ORG_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtScanBrCd_A, map.get("DOC_MGT_SCAN_BR_CD"));

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtScanBrNm_A, map.get("DOC_MGT_SCAN_BR_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtScanRgNm_A, map.get("DOC_MGT_SCAN_RG_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtScanZnNm_A, map.get("DOC_MGT_SCAN_ZN_NM"));

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).defOrdProcPsnCd_A, map.get("DEF_ORD_PROC_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).defBrAdminPsnCd_A, map.get("DEF_BR_ADMIN_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).leaseCmpyProcPsnCd_A, map.get("LEASE_CMPY_PROC_PSN_CD"));

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poPendEmlAddr_A, map.get("PO_PEND_EML_ADDR"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poIssEmlAddr_A, map.get("PO_ISS_EML_ADDR"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).invPkgPendEmlAddr_A, map.get("INV_PKG_PEND_EML_ADDR"));

            if (ZYPConstant.FLG_ON_Y.equals(map.get("ACTV_FLG"))) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).actvFlg_A, map.get("ACTV_FLG"));
            }

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtIntfcProcTs_A, map.get("DOC_MGT_INTFC_PROC_TS"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxScrItem23Txt_A, formatDateTime(map.get("DOC_MGT_INTFC_PROC_TS")));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtIntfcStsCd_A, map.get("DOC_MGT_INTFC_STS_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).docMgtIntfcStsDescTxt_A, map.get("DOC_MGT_INTFC_STS_NM"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).procModeCd_A, map.get("PROC_MODE_CD"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTime_A, map.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ezUpTimeZone_A, map.get("EZUPTIMEZONE"));
        }
        glblMsg.A.setValidCount(i);
    }

    /**
     * inputCheck(SMsg Duplicate Check)
     * @param bizMsg NWAL2410CMsg
     * @param glblMsg NWAL2410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean inputCheck(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg, String glblCmpyCd) {

        boolean isNotError = true;
        int firstErrIdx = -1;

        // 1. Duplicate Check(Code)
        Map<String, Integer> keyMap = new HashMap<String, Integer>();

        NWAL2410_ASMsgArray asMsgArray = glblMsg.A;

        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            String keycd1 = asMsgArray.no(i).docMgtOrgCd_A.getValue();
            String keycd2 = asMsgArray.no(i).docMgtScanBrCd_A.getValue();
            String keycd = ZYPCommonFunc.leftPad(String.valueOf(keycd1), 5, "0") + ZYPCommonFunc.leftPad(String.valueOf(keycd2), 3, "0");

            if (!keyMap.containsKey(keycd)) {
                keyMap.put(keycd, i);
            } else {
                int duplIdx = keyMap.get(keycd);
                if (!asMsgArray.no(duplIdx).docMgtScanBrCd_A.isError()) {
                    asMsgArray.no(duplIdx).docMgtScanBrCd_A.setErrorInfo(1, NWAM0925E, new String[] {KEY_CODE_DISP_PK });
                    // 2017/02/17 QC#17676 UPD START
                    // if (firstErrIdx == -1) {
                    if (!ZYPCommonFunc.hasValue(asMsgArray.no(duplIdx).ezUpTime_A) && firstErrIdx == -1) {
                        // 2017/02/17 QC#17676 UPD E N D
                        firstErrIdx = duplIdx;
                    }
                }
                asMsgArray.no(i).docMgtScanBrCd_A.setErrorInfo(1, NWAM0925E, new String[] {KEY_CODE_DISP_PK });
                if (firstErrIdx == -1) {
                    firstErrIdx = i;
                }
                isNotError = false;
            }

        }

        // 2017/02/17 QC#17676 ADD START
        HashMap<String, Boolean> authPsnMap = new HashMap<String, Boolean>();
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            String userCd1 = asMsgArray.no(i).defOrdProcPsnCd_A.getValue();
            String userCd2 = asMsgArray.no(i).defBrAdminPsnCd_A.getValue();
            String userCd3 = asMsgArray.no(i).leaseCmpyProcPsnCd_A.getValue();

            if (ZYPCommonFunc.hasValue(userCd1)) {
                if (!checkDbAuthPsnUsrNm(authPsnMap, glblCmpyCd, userCd1)) {
                    asMsgArray.no(i).defOrdProcPsnCd_A.setErrorInfo(1, ZZZM9006E, new String[] {PSN_CODE });
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                        isNotError = false;
                    }
                }
            }
            if (ZYPCommonFunc.hasValue(userCd2)) {
                if (!checkDbAuthPsnUsrNm(authPsnMap, glblCmpyCd, userCd2)) {
                    asMsgArray.no(i).defBrAdminPsnCd_A.setErrorInfo(1, ZZZM9006E, new String[] {PSN_CODE });
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                        isNotError = false;
                    }
                }
            }
            if (ZYPCommonFunc.hasValue(userCd3)) {
                if (!checkDbAuthPsnUsrNm(authPsnMap, glblCmpyCd, userCd3)) {
                    asMsgArray.no(i).leaseCmpyProcPsnCd_A.setErrorInfo(1, ZZZM9006E, new String[] {PSN_CODE });
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                        isNotError = false;
                    }
                }
            }

        }
        // 2017/02/17 QC#17676 ADD END

        if (!isNotError) {
            bizMsg.xxPageShowFromNum.setValue(NWAL2410CommonLogic.getPageStartRowIndex(bizMsg, firstErrIdx));
            NWAL2410CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            return isNotError;
        }

        return isNotError;

    }

    // 2017/02/21 QC#17676-2 ADD START
    /**
     * inputCheckForPage(SMsg Duplicate Check)
     * @param bizMsg NWAL2410CMsg
     * @param glblMsg NWAL2410SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean inputCheckForPage(NWAL2410CMsg bizMsg, NWAL2410SMsg glblMsg, String glblCmpyCd) {

        boolean isNotError = true;
        int firstErrIdx = -1;

        // 1. Duplicate Check(Code)
        Map<String, Integer> keyMap = new HashMap<String, Integer>();

        NWAL2410_ACMsgArray acMsgArray = bizMsg.A;

        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            String keycd1 = acMsgArray.no(i).docMgtOrgCd_A.getValue();
            String keycd2 = acMsgArray.no(i).docMgtScanBrCd_A.getValue();
            String keycd = ZYPCommonFunc.leftPad(String.valueOf(keycd1), 5, "0") + ZYPCommonFunc.leftPad(String.valueOf(keycd2), 3, "0");

            if (!keyMap.containsKey(keycd)) {
                keyMap.put(keycd, i);
            } else {
                int duplIdx = keyMap.get(keycd);
                if (!acMsgArray.no(duplIdx).docMgtScanBrCd_A.isError()) {
                    acMsgArray.no(duplIdx).docMgtScanBrCd_A.setErrorInfo(1, NWAM0925E, new String[] {KEY_CODE_DISP_PK });
                    if (!ZYPCommonFunc.hasValue(acMsgArray.no(duplIdx).ezUpTime_A) && firstErrIdx == -1) {
                        firstErrIdx = duplIdx;
                    }
                }
                acMsgArray.no(i).docMgtScanBrCd_A.setErrorInfo(1, NWAM0925E, new String[] {KEY_CODE_DISP_PK });
                if (firstErrIdx == -1) {
                    firstErrIdx = i;
                }
                isNotError = false;
            }

        }

        HashMap<String, Boolean> authPsnMap = new HashMap<String, Boolean>();
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            String userCd1 = acMsgArray.no(i).defOrdProcPsnCd_A.getValue();
            String userCd2 = acMsgArray.no(i).defBrAdminPsnCd_A.getValue();
            String userCd3 = acMsgArray.no(i).leaseCmpyProcPsnCd_A.getValue();

            if (ZYPCommonFunc.hasValue(userCd1)) {
                if (!checkDbAuthPsnUsrNm(authPsnMap, glblCmpyCd, userCd1)) {
                    acMsgArray.no(i).defOrdProcPsnCd_A.setErrorInfo(1, ZZZM9006E, new String[] {PSN_CODE });
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                        isNotError = false;
                    }
                }
            }
            if (ZYPCommonFunc.hasValue(userCd2)) {
                if (!checkDbAuthPsnUsrNm(authPsnMap, glblCmpyCd, userCd2)) {
                    acMsgArray.no(i).defBrAdminPsnCd_A.setErrorInfo(1, ZZZM9006E, new String[] {PSN_CODE });
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                        isNotError = false;
                    }
                }
            }
            if (ZYPCommonFunc.hasValue(userCd3)) {
                if (!checkDbAuthPsnUsrNm(authPsnMap, glblCmpyCd, userCd3)) {
                    acMsgArray.no(i).leaseCmpyProcPsnCd_A.setErrorInfo(1, ZZZM9006E, new String[] {PSN_CODE });
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                        isNotError = false;
                    }
                }
            }

        }

        return isNotError;

    }

    // 2017/02/21 QC#17676-2 ADD E N D

    // 2017/02/17 QC#17676 ADD START
    /**
     * checkDbAuthPsnUsrNm
     * @param authPsnMap HashMap<String, Boolean>
     * @param glblCmpyCd String
     * @param psnCd String
     * @return boolean
     */
    private static Boolean checkDbAuthPsnUsrNm(HashMap<String, Boolean> authPsnMap, String glblCmpyCd, String psnCd) {
        AUTH_PSNTMsg inMsg = new AUTH_PSNTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("usrNm01", psnCd);
        inMsg.setSQLID("053");
        String keyAuthPsnMap = glblCmpyCd.concat(psnCd);

        if (authPsnMap.containsKey(keyAuthPsnMap)) {
            return authPsnMap.get(keyAuthPsnMap);
        }
        AUTH_PSNTMsgArray authPsnArray = (AUTH_PSNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (authPsnArray == null || authPsnArray.length() == 0) {
            authPsnMap.put(keyAuthPsnMap, false);
            return false;
        }
        authPsnMap.put(keyAuthPsnMap, true);
        return true;
    }

    // 2017/02/17 QC#17676 ADD E N D

    /**
     * When origValue not equals inValue, return true.
     * @param origValue EZDSStringItem
     * @param inValue EZDSStringItem
     * @return boolean
     */
    public static boolean isNotCompareString(EZDSStringItem origValue, EZDSStringItem inValue) {
        if (ZYPCommonFunc.hasValue(origValue)) {
            if (!origValue.getValue().equals(inValue.getValue())) {
                return true;
            }
        } else if (ZYPCommonFunc.hasValue(inValue)) {
            return true;
        }
        return false;
    }

    /**
     * formatDateTime
     * @param dt time stamp
     * @return after format date
     */
    public static String formatDateTime(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > DATE_TIME_LENGTH) {
            dt = dt.substring(0, DATE_TIME_LENGTH);
        }

        dt = ZYPDateUtil.formatEzd17ToDisp(dt);

        return dt;
    }

}
