/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0040.common;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NSBL0040.NSBL0040CMsg;
import business.blap.NSBL0040.NSBL0040SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * Credit Approval
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2016/12/07   Hitachi         K.Kojima        Update          QC#14204
 *</pre>
 */
public class NSBL0040CommonLogic {

    /**
     * Paginate
     * @param cMsg NSBL0040CMsg
     * @param sMsg NSBL0040SMsg
     * @param startIndex int
     */
    public static void paginate(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg, int startIndex) {
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
     * @param cMsg NSBL0040CMsg
     * @param sMsg NSBL0040SMsg
     * @param startIndex int
     */
    public static void setPaginationParameters(NSBL0040CMsg cMsg, NSBL0040SMsg sMsg, int startIndex) {
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

}
