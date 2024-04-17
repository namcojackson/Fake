
package business.blap.ZZIL0100.common;

/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZIL0100.ZZIL0100CMsg;
import business.blap.ZZIL0100.ZZIL0100Query;
import business.blap.ZZIL0100.ZZIL0100SMsg;
import business.blap.ZZIL0100.constant.ZZIL0100Constant;
import business.db.ITRL_INTFC_TRX_CONFIGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class ZZIL0100CommonLogic extends ZZIL0100Constant {
    
    /**
     * Get Internal Interface Master table name from
     * ITRL_INTFC_TRX_CONFIG table
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @return
     */
    public static String getTrxTblId(ZZIL0100CMsg cMsg, String glblCmpyCd) {
        ITRL_INTFC_TRX_CONFIGTMsg tmsg = new ITRL_INTFC_TRX_CONFIGTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.itrlIntfcTrxConfigId.setValue(cMsg.itrlIntfcTrxConfigId_PS.getValue());
        ITRL_INTFC_TRX_CONFIGTMsg tmsgResult = (ITRL_INTFC_TRX_CONFIGTMsg) EZDTBLAccessor.findByKey(tmsg);
        return tmsgResult.itrlIntfcTrxTblId.getValue();
    }
    
    /**
     * set config list to list box
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     */
    public static void setConfigListBox(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg, String glblCmpyCd) {
        S21SsmEZDResult result = ZZIL0100Query.getInstance().getConfigNames();
        if (!result.isCodeNormal()) {
            return;
        }
        List<Map<String, String>> list = (List) result.getResultObject();
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<Map<String, String>> itr = list.iterator();
        int listnum = 0;
        while (itr.hasNext()) {
            Map<String, String> map = (Map<String, String>) itr.next();
            if (map == null) {
                continue;
            }
            cMsg.itrlIntfcTrxConfigId_PC.no(listnum).setValue(map.get("configId"));
            cMsg.itrlIntfcTrxConfigNm_PC.no(listnum).setValue(map.get("configName"));
            listnum++;
        }
    }

    /**
     * validate table name
     * @param str
     * @param cMsg
     * @return
     */
    public static boolean validateTableName(String str, ZZIL0100CMsg cMsg) {
        String matcher = "^[A-Z_]+$";
        if (!ZYPCommonFunc.hasValue(str) || !str.matches(matcher)) {
            cMsg.setMessageInfo(ZZIM0012E, new String[] {str + " table", "not found." });
            return false;
        }
        return true;
    }

    /**
     * set table list from SSM result
     * @param ssmResult
     * @param cMsgArray
     * @param sMsgArray
     * @param fromNum
     * @param toNum
     * @param ofNum
     * @return
     */
    public static String setSsmResultToList(S21SsmEZDResult ssmResult, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, EZDCBigDecimalItem fromNum, EZDCBigDecimalItem toNum, EZDCBigDecimalItem ofNum) {
        // no search result
        if (!ssmResult.isCodeNormal()) {
            fromNum.clear();
            toNum.clear();
            ofNum.clear();
            if (cMsgArray != null) {
                ZYPTableUtil.clear(cMsgArray);
            }
            if (sMsgArray != null) {
                ZYPTableUtil.clear(sMsgArray);
            }
            return ZZZM9005W;
        }

        // copy smsg to cmsg(only one page)
        int i;
        for (i = 0; i < sMsgArray.getValidCount(); i++) {
            if (i == cMsgArray.length()) {
                break;
            }
            EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(i), null);
        }
        cMsgArray.setValidCount(i);

        // reset page number
        fromNum.setValue(1);
        toNum.setValue(cMsgArray.getValidCount());
        // over max count
        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > sMsgArray.length()) {
            ofNum.setValue(sMsgArray.length());
            return ZZZM9002W;
        }
        ofNum.setValue(queryResCnt);
        return null;
    }

    /**
     * next and prev page
     * @param cMsgArray
     * @param sMsgArray
     * @param fromNum
     * @param toNum
     */
    public static void pageMove(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, EZDCBigDecimalItem fromNum, EZDCBigDecimalItem toNum) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = fromNum.getValueInt();
        int i;
        for (i = pagenationFrom; i < pagenationFrom + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsgArray.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        fromNum.setValue(pagenationFrom + 1);
        toNum.setValue(pagenationFrom + cMsgArray.getValidCount());
    }

    /**
     * table column sort
     * @param cMsgArray
     * @param sMsgArray
     * @param fromNum
     * @param toNum
     * @param sortTblNm
     * @param sortItemNm
     * @param sortOrdBy
     * @param baseContents
     * @param tblName
     * @param sortItems
     */
    public static void tblColumnSort(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, EZDCBigDecimalItem fromNum, EZDCBigDecimalItem toNum, String sortTblNm, String sortItemNm, String sortOrdBy, String[][] baseContents, String tblName,
            String[] sortItems) {
        if (!tblName.equals(sortTblNm)) {
            return;
        }
        if (!Arrays.asList(sortItems).contains(sortItemNm)) {
            return;
        }

        // execute column sort function
        S21SortTarget sortTarget = new S21SortTarget(sMsgArray, baseContents);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsgArray.getValidCount());

        if (cMsgArray.getValidCount() > 0) {
            // Copy(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsgArray.getValidCount(); i++) {
                if (i == cMsgArray.length()) {
                    break;
                }
                EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(i), null);
            }
            cMsgArray.setValidCount(i);
            // Set page num (view 1)
            fromNum.setValue(1);
            toNum.setValue(cMsgArray.getValidCount());
        }
    }
}
