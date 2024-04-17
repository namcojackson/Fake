/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0110.common;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

import business.blap.ZZIL0110.ZZIL0110CMsg;
import business.blap.ZZIL0110.ZZIL0110Query;
import business.blap.ZZIL0110.ZZIL0110SMsg;
import business.blap.ZZIL0110.constant.ZZIL0110Constant;
import business.db.ITRL_INTFC_TRX_CONFIGTMsg;

public class ZZIL0110CommonLogic extends ZZIL0110Constant {
    /**
     * Get Internal Interface Master table name from
     * ITRL_INTFC_TRX_CONFIG table
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @return
     */
    public static String getMstrTbl(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg, String glblCmpyCd) {
        ITRL_INTFC_TRX_CONFIGTMsg tmsg = new ITRL_INTFC_TRX_CONFIGTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.itrlIntfcTrxConfigId.setValue(cMsg.itrlIntfcTrxConfigId_PS.getValue());
        ITRL_INTFC_TRX_CONFIGTMsg tmsgResult = (ITRL_INTFC_TRX_CONFIGTMsg) EZDTBLAccessor.findByKey(tmsg);
        return tmsgResult.itrlIntfcMstrTblId.getValue();
    }

    /**
     * Set list
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     */
    public static void setIntfctblidToMsg(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg, String glblCmpyCd) {
        String itrlIntfcId = cMsg.itrlIntfcId_B1.getValue();
        // Scrn01
        setConfigListBox(cMsg.itrlIntfcTrxConfigId_BC, cMsg.itrlIntfcTrxConfigNm_BC);
        if (!ZYPCommonFunc.hasValue(itrlIntfcId)) {
            ZYPTableUtil.clear(cMsg.B);
            ZYPTableUtil.clear(sMsg.B);
            return;
        }

        String mstrTable = getMstrTbl(cMsg, sMsg, glblCmpyCd);
        if (!validateTableName(mstrTable, cMsg)) {
            return;
        }
        S21SsmEZDResult ssmResult = ZZIL0110Query.getInstance().getItrlIntfcTbl(cMsg, sMsg, mstrTable, itrlIntfcId);
        String msgCode = setSsmResultToList(ssmResult, cMsg.B, sMsg.B, cMsg.xxPageShowFromNum_B, cMsg.xxPageShowToNum_B, cMsg.xxPageShowOfNum_B);
        if (msgCode != null) {
            cMsg.setMessageInfo(msgCode);
        }

    }

    /**
     * set config name pulldown list to message
     * @param itrlIntfcTrxConfigId
     * @param itrlIntfcTrxConfigNm
     */
    public static void setConfigListBox(EZDCStringItemArray itrlIntfcTrxConfigId, EZDCStringItemArray itrlIntfcTrxConfigNm) {
        S21SsmEZDResult result = ZZIL0110Query.getInstance().getConfigNamesForSelf();
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
            // cMsg.interfaceId
            itrlIntfcTrxConfigId.no(listnum).setValue(map.get("configId"));
            itrlIntfcTrxConfigNm.no(listnum).setValue(map.get("configName"));
            listnum++;
        }
    }

    /**
     * validate table name
     * @param str
     * @param cMsg
     * @return
     */
    public static boolean validateTableName(String str, ZZIL0110CMsg cMsg) {
        String matcher = "^[A-Z_]+$";
        if (!ZYPCommonFunc.hasValue(str) || !str.matches(matcher)) {
            cMsg.setMessageInfo(ZZIM0012E, new String[] {str + " table", "not found."});
            return false;
        }
        return true;
    }

    /**
     * set ssm result to message array list
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
    
    public static String getSelfPrefix(){
        S21SsmEZDResult result = ZZIL0110Query.getInstance().getMyId();
        if (!result.isCodeNormal()) {
            return "___"; // not match string
        }
        List<Map<String, String>> list = (List) result.getResultObject();
        if (list == null || list.isEmpty()) {
            return "___"; // not match string
        }
        Map<String, String> map = (Map<String, String>) list.get(0);
        return map.get("mstrId");
    }
}
