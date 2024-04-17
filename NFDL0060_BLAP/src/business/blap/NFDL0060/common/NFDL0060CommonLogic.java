/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0060.common;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NFDL0060.NFDL0060CMsg;
import business.blap.NFDL0060.NFDL0060SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#11417
 *</pre>
 */
public class NFDL0060CommonLogic {
    
    /**
     * Paginate
     * @param cMsg NFDL0060CMsg
     * @param sMsg NFDL0060SMsg
     * @param startIndex int
     */
    public static void paginate(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg, int startIndex) {
        paginate(cMsg.A, sMsg.A, startIndex);
        setPaginationParameters(cMsg, sMsg, startIndex);
    }

    /**
     * Paginate
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     * @param startIndex int
     */
    public static void paginate(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, int startIndex) {

        int num = 0;

        ZYPTableUtil.clear(cMsgArray);

        for (int i = startIndex; i < sMsgArray.getValidCount(); i++) {

            if (num >= cMsgArray.length()) {
                break;
            }

            EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(num), null);
            num++;
        }

        cMsgArray.setValidCount(num);
    }

    /**
     * Set pagination parameters
     * @param cMsg NFDL0060CMsg
     * @param sMsg NFDL0060SMsg
     * @param startIndex int
     */
    public static void setPaginationParameters(NFDL0060CMsg cMsg, NFDL0060SMsg sMsg, int startIndex) {
        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Sort
     * @param sMsgArray EZDSMsgArray
     * @param baseContents String[][]
     * @param sortItemNm String
     * @param sortOrderBy String
     */
    public static void sort(EZDSMsgArray sMsgArray, String[][] baseContents, String sortItemNm, String sortOrderBy) {
        S21SortTarget sortTarget = new S21SortTarget(sMsgArray, baseContents);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrderBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsgArray.getValidCount());
    }

    // START 2016/07/08 K.Kojima [QC#11417,DEL]
    // /**
    // * isManager
    // * @param glblCmpyCd String
    // * @param userId String
    // */
    // public static boolean isManager(String glblCmpyCd, String
    // userId) {
    // boolean result = false;
    // S21SsmEZDResult res =
    // NFDL0060Query.getInstance().getUserIdList(glblCmpyCd, userId);
    // if (res.isCodeNormal()) {
    // if (res.getQueryResultCount() > 1) {
    // result = true;
    // }
    // }
    // return result;
    // }
    // END 2016/07/08 K.Kojima [QC#11417,DEL]
}
