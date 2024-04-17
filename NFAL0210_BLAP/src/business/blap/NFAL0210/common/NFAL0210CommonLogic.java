/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0210.common;

import static business.blap.NFAL0210.constant.NFAL0210Constant.*;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0210.NFAL0210CMsg;
import business.blap.NFAL0210.NFAL0210Query;
import business.blap.NFAL0210.NFAL0210SMsg;
import business.db.JRNL_CATGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Manual Journal Entry Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/13   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFAL0210CommonLogic {

    /**
     * Check search condition
     * @param cMsg NFAL0210CMsg
     * @return true: has search condition, false: no search condition
     */
    public static boolean hasSearchCondition(NFAL0210CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.manJrnlNm) || ZYPCommonFunc.hasValue(cMsg.glPerNm) || ZYPCommonFunc.hasValue(cMsg.xxFromDt) || ZYPCommonFunc.hasValue(cMsg.xxToDt) || ZYPCommonFunc.hasValue(cMsg.jrnlCatgCd)
                || ZYPCommonFunc.hasValue(cMsg.glSendCpltFlg_SV) || ZYPCommonFunc.hasValue(cMsg.rvslCpltFlg_SV) || ZYPCommonFunc.hasValue(cMsg.manJrnlCpltFlg_SV) || ZYPCommonFunc.hasValue(cMsg.autoRvslFlg_SV)) {
            return true;
        }
        return false;
    }

    /**
     * Get Search Data
     * @param cMsg NFAL0210CMsg
     * @param sMsg NFAL0210SMsg
     */
    public static void getSearchData(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg) {
        S21SsmEZDResult ssmResult = NFAL0210Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(ZZZM9002W);
            }
            cMsg.xxRadioBtn_A.clear();
        } else {
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NFAL0210CMsg cMsg, NFAL0210SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * Journal Category Validation
     * @param cMsg NFAL0210CMsg
     * @return boolean
     */
    public static boolean jrnlCatgSearch(NFAL0210CMsg cMsg) {
        JRNL_CATGTMsg tMsg = new JRNL_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.jrnlCatgCd, cMsg.jrnlCatgCd);
        JRNL_CATGTMsg rslt = (JRNL_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (rslt == null) {
            cMsg.jrnlCatgDescTxt.clear();
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.jrnlCatgDescTxt, rslt.jrnlCatgDescTxt);
            return true;
        }
    }

    /**
     * setPageNum
     * @param cMsg NFAL0210CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NFAL0210CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * clearPageNum
     * @param cMsg NSBL0200CMsg
     */
    public static void clearPageNum(NFAL0210CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

}
