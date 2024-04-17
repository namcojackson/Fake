/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL2100.common;

import static business.blap.NFBL2100.constant.NFBL2100Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL2100.NFBL2100CMsg;
import business.blap.NFBL2100.NFBL2100Query;
import business.blap.NFBL2100.NFBL2100SMsg;
import business.db.VNDTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Lease Buyout Approve List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/25   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFBL2100CommonLogic {

    /**
     * Check search condition
     * @param cMsg NFBL2100CMsg
     * @return true: has search condition, false: no search condition
     */
    public static boolean hasSearchCondition(NFBL2100CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.apDsWfStsCd_SV) || ZYPCommonFunc.hasValue(cMsg.vndCd) || ZYPCommonFunc.hasValue(cMsg.xxFromDt) || ZYPCommonFunc.hasValue(cMsg.xxToDt) || ZYPCommonFunc.hasValue(cMsg.cpoOrdNum)) {
            return true;
        }
        return false;
    }

    /**
     * Get Search Data
     * @param cMsg NFBL2100CMsg
     * @param sMsg NFBL2100SMsg
     */
    public static void getSearchData(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg) {
        S21SsmEZDResult ssmResult = NFBL2100Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
        } else {
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NFBL2100CMsg
     * @param sMsg NFBL2100SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NFBL2100CMsg cMsg, NFBL2100SMsg sMsg, int pageFrom) {

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
     * setPageNum
     * @param cMsg NFBL2100CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NFBL2100CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * clearPageNum
     * @param cMsg NSBL0200CMsg
     */
    public static void clearPageNum(NFBL2100CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    /**
     * get VND By Primary Key For Update No Wait
     * @param glblCmpyCd String
     * @param vndCd String
     * @return VNDTMsg
     */
    public static VNDTMsg getVnd(String glblCmpyCd, String vndCd) {
        VNDTMsg prmTMsg = new VNDTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.vndCd, vndCd);
        return (VNDTMsg) EZDTBLAccessor.findByKey(prmTMsg);
    }

}
