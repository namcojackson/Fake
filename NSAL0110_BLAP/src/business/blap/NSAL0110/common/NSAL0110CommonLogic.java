/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0110.common;

import static business.blap.NSAL0110.constant.NSAL0110Constant.DISPLAY_MODE_DETAIL;
import static business.blap.NSAL0110.constant.NSAL0110Constant.DISPLAY_MODE_SUMMARY;
import static business.blap.NSAL0110.constant.NSAL0110Constant.NSAM0082E;
import static business.blap.NSAL0110.constant.NSAL0110Constant.NZZM0000E;
import static business.blap.NSAL0110.constant.NSAL0110Constant.NZZM0001W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import business.blap.NSAL0110.NSAL0110CMsg;
import business.blap.NSAL0110.NSAL0110Query;
import business.blap.NSAL0110.NSAL0110SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/** 
 *<pre>
 *
 * Contract Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/12   Hitachi         Y.Igarashi      Create          N/A
 * 2013/11/19   Hitachi         T.Arakawa       Update          QC2852
 * 2016/07/08   Hitachi         M.Gotou         Update          QC#11596
 * 2017/01/30   Hitachi         K.Kitachi       Update          QC#17308
 *</pre>
 */
public class NSAL0110CommonLogic {

    /**
     * 
     * searchContractList
     * 
     * @param cMsg NSAL0110CMsg
     * @param sMsg NSAL0110SMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void searchContractList(NSAL0110CMsg cMsg, NSAL0110SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> sc = new HashMap<String, Object>();
        sc.put("glblCmpyCd", glblCmpyCd);
        sc.put("dsContrNum", cMsg.dsContrNum_SC.getValue());
        // START 2016/07/08 M.Gotou [QC#11596, ADD]
        sc.put("dsContrNm", cMsg.dsContrNm_SC.getValue());
        // END 2016/07/08 M.Gotou [QC#11596, ADD]
        sc.put("dsContrCatgCd", cMsg.dsContrCatgCd_SV.getValue());
        sc.put("dsAcctNum", cMsg.sellToCustCd_SC.getValue());
        sc.put("dsContrTpCd", cMsg.dsContrTpCd_SV.getValue());
        sc.put("dsContrClsCd", cMsg.dsContrClsCd_SV.getValue());
        sc.put("svcContrBrCd", cMsg.svcContrBrCd_SC.getValue());
        sc.put("dsContrCtrlStsHdr", cMsg.dsContrCtrlStsCd_HV.getValue());
        sc.put("dsContrDtlTpCd", cMsg.dsContrDtlTpCd_SV.getValue());
        sc.put("dsContrCtrlStsDtl", cMsg.dsContrCtrlStsCd_DV.getValue());
        sc.put("serNum", cMsg.serNum_SC.getValue());
        sc.put("mdlNm", cMsg.mdlNm_SC.getValue());
        sc.put("displayMode", cMsg.xxScrItem10Txt_SX.getValue());
        sc.put("mainUnitLineFlg", ZYPConstant.FLG_ON_Y);
        sc.put("rownum", (sMsg.A.length() + 1));

        S21SsmEZDResult ssmResult = NSAL0110Query.getInstance().searchContractList(sc, sMsg);
        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            copyFromSMsgOntoCmsg(cMsg, sMsg);
        } else {
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    // START 2017/01/30 K.Kitachi [QC#17308, DEL]
//    /**
//     * 
//     * createDisplayNum
//     * 
//     * @param cMsg NSAL0110CMsg
//     */
//    public static void createDisplayNum(NSAL0110CMsg cMsg) {
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            setValue(cMsg.A.no(i).xxScrItem40Txt_RS, cMsg.A.no(i).dsContrNum_RS.getValue() + DISPLAY_DELIMITER + cMsg.A.no(i).dsContrSqNum_RS.getValue());
//        }
//    }
    // END 2017/01/30 K.Kitachi [QC#17308, DEL]

    /**
     * 
     * setPageNum
     * 
     * @param cMsg NSAL0110CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NSAL0110CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * 
     * clearPageNum
     * 
     * @param cMsg NSAL0110CMsg
     */
    public static void clearPageNum(NSAL0110CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    /**
     * 
     * copyFromSMsgOntoCmsg
     * 
     * @param cMsg NSAL0110CMsg
     * @param sMsg NSAL0110SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL0110CMsg cMsg, NSAL0110SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);
        setPageNum(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.A.getValidCount()), sMsg.A.getValidCount());
    }

    public static boolean checkParam(NSAL0110CMsg cMsg) {

        if (DISPLAY_MODE_DETAIL.equals(cMsg.xxScrItem10Txt_SX.getValue())) {
            if (!hasValue(cMsg.dsContrNum_SC)
                    && !hasValue(cMsg.svcContrBrCd_SC)
                    && !hasValue(cMsg.dsContrClsCd_SV)
                    && !hasValue(cMsg.dsContrNm_SC)
                    && !hasValue(cMsg.sellToCustCd_SC)
                    && !hasValue(cMsg.dsContrCatgCd_SV)
                    && !hasValue(cMsg.dsContrCtrlStsCd_HV)
                    && !hasValue(cMsg.dsContrDtlTpCd_SV)
                    && !hasValue(cMsg.dsContrCtrlStsCd_DV)
                    && !hasValue(cMsg.serNum_SC)
                    && !hasValue(cMsg.mdlNm_SC)) {
                cMsg.setMessageInfo(NSAM0082E);
                return false;
            }
        } else if (DISPLAY_MODE_SUMMARY.equals(cMsg.xxScrItem10Txt_SX.getValue())) {
            if (!hasValue(cMsg.dsContrNum_SC)
                    && !hasValue(cMsg.svcContrBrCd_SC)
                    && !hasValue(cMsg.dsContrClsCd_SV)
                    && !hasValue(cMsg.dsContrNm_SC)
                    && !hasValue(cMsg.sellToCustCd_SC)
                    && !hasValue(cMsg.dsContrCatgCd_SV)
                    && !hasValue(cMsg.dsContrCtrlStsCd_HV)) {
                cMsg.setMessageInfo(NSAM0082E);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    // [QC2852] 2013-11-19 ADD-S
    public static String i18n(String str) {
        return EZDI18NContext.getInstance().getI18NAccessor().getLabelConv().convLabel2i18nLabel("", str);
    }
    // [QC2852] 2013-11-19 ADD-E
}

