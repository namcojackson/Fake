/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1130.common;

import static business.blap.NSAL1130.constant.NSAL1130Constant.NSBM0002E;
import static business.blap.NSAL1130.constant.NSAL1130Constant.NSBM0009W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NSAL1130.NSAL1130CMsg;
import business.blap.NSAL1130.NSAL1130Query;
import business.blap.NSAL1130.NSAL1130SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 *
 * NSAL1130 Counter History Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         K.Kojima        Create          N/A
 * 2016/04/19   Hitachi         K.Yamada        Update          CSA QC#7233
 * 
 *</pre>
 */
public class NSAL1130CommonLogic {

    /**
     * searchTechInfo
     * @param cMsg NSAL1130CMsg
     * @param sMsg NSAL1130SMsg
     * @param gcc String
     */
    public static void searchCounterHistoryList(NSAL1130CMsg cMsg, NSAL1130SMsg sMsg, String gcc) {

        cMsg.xxPageShowFromNum.setValue(0);
        sMsg.A.clear();

        Map<String, Object> sc = new HashMap<String, Object>();

        sc.put("glblCmpyCd", gcc);
        sc.put("svcMachMstrPk", cMsg.svcMachMstrPk.getValue());
        sc.put("mtrLbCd", cMsg.mtrLbCd_SV.getValue());
        sc.put("mtrReadFromDt", cMsg.effFromDt_SC.getValue());
        sc.put("mtrReadThruDt", cMsg.effThruDt_SC.getValue());
        sc.put("rowNum", sMsg.A.length() + 1);

        S21SsmEZDResult ssmResult = NSAL1130Query.getInstance().getCounterHistoryList(sc, sMsg);

        if (ssmResult.getQueryResultCount() == 0) {
            cMsg.setMessageInfo(NSBM0002E);
            return;
        }

        if (ssmResult.getQueryResultCount() > sMsg.A.length() - 1) {
            cMsg.setMessageInfo(NSBM0009W, new String[] {String.valueOf(sMsg.A.length()) });

            sMsg.A.setValidCount(sMsg.A.length());
        }

        copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    // add start 2016/04/19 CSA Defect#7233
    /**
     * createMtrLbPulldown
     * @param cMsg NSAL1130CMsg
     * @param String global company code
     */
    public static void createMtrLbPulldown(NSAL1130CMsg cMsg, String gcc) {

        cMsg.mtrLbCd_CD.clear();
        cMsg.mtrLbDescTxt_SC.clear();

        Map<String, Object> sc = new HashMap<String, Object>();
        sc.put("glblCmpyCd", gcc);
        sc.put("svcMachMstrPk", cMsg.svcMachMstrPk.getValue());

        S21SsmEZDResult ssmResult = NSAL1130Query.getInstance().getMtrLb(sc);

        if (ssmResult.getQueryResultCount() == 0) {
            cMsg.setMessageInfo(NSBM0002E);
            return;
        }

        List<Map<String, String>> mtrLbList = (List<Map<String, String>>) ssmResult.getResultObject();
        for (int i = 0; i < mtrLbList.size(); i++) {
            Map<String, String> mtrLb = mtrLbList.get(i);
            setValue(cMsg.mtrLbCd_CD.no(i), mtrLb.get("MTR_LB_CD"));
            setValue(cMsg.mtrLbDescTxt_SC.no(i), mtrLb.get("MTR_LB_DESC_TXT"));
        }
    }
    // add end 2016/04/19 CSA Defect#7233

    /**
     * copyFromSMsgOntoCmsg
     * @param cMsg NSAL1130CMsg
     * @param sMsg NSAL1130SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL1130CMsg cMsg, NSAL1130SMsg sMsg) {
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

    /**
     * setPageNum
     * @param cMsg NSAL1130CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NSAL1130CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * clearPageNum
     * @param cMsg NSBL0200CMsg
     */
    public static void clearPageNum(NSAL1130CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

}
