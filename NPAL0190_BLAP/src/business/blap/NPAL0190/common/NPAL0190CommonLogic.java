/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL0190.common;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NPAL0190.NPAL0190CMsg;
import business.blap.NPAL0190.NPAL0190SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * PO History Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2011   SRA             N.Otsuji        Create          N/A
 * 03/14/2013   Hitachi         T.Arakawa       Create          K25(QC822)
 *</pre>
 */
public class NPAL0190CommonLogic {

    /**
     * Paginate
     * @param cMsg NPAL0190CMsg
     * @param sMsg NPAL0190SMsg
     * @param startIndex int
     */
    public static void paginate(NPAL0190CMsg cMsg, NPAL0190SMsg sMsg, int startIndex) {

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
     * @param cMsg NPAL0190CMsg
     * @param sMsg NPAL0190SMsg
     * @param startIndex int
     */
    public static void setPaginationParameters(NPAL0190CMsg cMsg, NPAL0190SMsg sMsg, int startIndex) {

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
