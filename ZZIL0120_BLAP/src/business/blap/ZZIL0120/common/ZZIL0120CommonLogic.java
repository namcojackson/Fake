/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0120.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.ZZIL0120.ZZIL0120CMsg;
import business.blap.ZZIL0120.ZZIL0120Query;
import business.blap.ZZIL0120.ZZIL0120SMsg;
import business.blap.ZZIL0120.constant.ZZIL0120Constant;

public class ZZIL0120CommonLogic extends ZZIL0120Constant {
    /**
     * get Internal Interface
     * @param cMsg
     * @param sMsg
     * @param glblCd
     */
    public static void getItrlIntfcIds(ZZIL0120CMsg cMsg, ZZIL0120SMsg sMsg, String glblCd) {
        EZDMsg.copy(cMsg, null, sMsg, null);
        ZYPTableUtil.clear(sMsg.A);

        List<Map<String, String>> mstTables = getMstrTbls();
        int sumQueryResCnt = 0;
        Iterator<Map<String, String>> itr = mstTables.iterator();
        while (itr.hasNext()) {
            Map<String, String> config = itr.next();
            sumQueryResCnt += execQueryForEachTable(cMsg, sMsg, sumQueryResCnt, config);
            if (sumQueryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(ZZZM9002W);
                sumQueryResCnt = sMsg.A.length();
                break;
            }
        }

        // no result
        if (sumQueryResCnt == 0) {
            cMsg.setMessageInfo(ZZZM9005W);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }

        // copy smsg to cmsg
        int i;
        for (i = 0; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                // TODO add 1?
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);

        // set page value
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sumQueryResCnt);
    }

    /**
     * get master table name
     * @return List<String> : List of table name after trim
     */
    public static List<Map<String, String>> getMstrTbls() {
        S21SsmEZDResult result = ZZIL0120Query.getInstance().getMasterTableNames();
        List<Map<String, String>> mstTables = new ArrayList<Map<String, String>>();
        if (!result.isCodeNormal()) {
            return mstTables;
        }
        List<Map<String, String>> list = (List) result.getResultObject();
        if (list == null || list.isEmpty()) {
            return mstTables;
        }
        return list;
    }

    /**
     * execute query for each table
     * @param cMsg
     * @param sMsg
     * @param tblName
     * @param sumQueryResCnt
     * @return queryResult count
     */
    private static int execQueryForEachTable(ZZIL0120CMsg cMsg, ZZIL0120SMsg sMsg, int sumQueryResCnt, Map<String, String> config) {
        String tblName = config.get("mstr_tbl_id");
        String configId = config.get("configId");
        String config_name = config.get("config_name");
        if(!validateTableName(tblName, cMsg)){
            return 0;
        }
        S21SsmEZDResult result = ZZIL0120Query.getInstance().getItrlIntfcIdsForMap(cMsg, sMsg, tblName, configId);
        // no search result
        if (!result.isCodeNormal()) {
            return 0;
        }
        List<Map<String, String>> list = (List) result.getResultObject();
        if (list == null || list.isEmpty()) {
            return 0;
        }
        Iterator<Map<String, String>> itr = list.iterator();
        int listnum = sumQueryResCnt;
        while (itr.hasNext()) {
            Map<String, String> map = (Map<String, String>) itr.next();
            if (map == null) {
                continue;
            }
            sMsg.A.no(listnum).itrlIntfcId_A.setValue(map.get("itrlIntfcId_A"));
            sMsg.A.no(listnum).itrlIntfcTrxConfigId_A.setValue(configId);
            sMsg.A.no(listnum).itrlIntfcTrxConfigNm_A.setValue(config_name);
            sMsg.A.no(listnum).xxIntfcInterCntNum_A.setValue(Integer.parseInt(map.get("xxIntfcInterCntNum_A")));
            sMsg.A.no(listnum).xxDtTm_AC.setValue(map.get("xxDtTm_AC") != null ? map.get("xxDtTm_AC") : "");
            sMsg.A.no(listnum).xxDtTm_AU.setValue(map.get("xxDtTm_AU") != null ? map.get("xxDtTm_AU") : "");
            listnum++;
        }
        sMsg.A.setValidCount(listnum);
        return result.getQueryResultCount();
    }
    
    /**
     * validate table name
     * @param str
     * @param cMsg
     * @return
     */
    public static boolean validateTableName(String str, ZZIL0120CMsg cMsg) {
        String matcher = "^[A-Z_]+$";
        if (!ZYPCommonFunc.hasValue(str) || !str.matches(matcher)) {
            cMsg.setMessageInfo(ZZIM0012E, new String[] {str + " table", "not found."});
            return false;
        }
        return true;
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
