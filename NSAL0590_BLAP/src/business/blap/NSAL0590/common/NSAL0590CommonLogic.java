/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0590.common;

import static business.blap.NSAL0590.constant.NSAL0590Constant.*;

import java.util.HashMap;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.NSAL0590.NSAL0590CMsg;
import business.blap.NSAL0590.NSAL0590Query;
import business.blap.NSAL0590.NSAL0590SMsg;
import business.blap.NSAL0590.NSAL0590_ASMsg;
import business.blap.NSAL0590.NSAL0590_BSMsg;
import business.db.DS_CONTR_RPT_GRPTMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 *  2015/10/30   Hitachi        J.Kim         Create          N/A
 *  2016/03/25   Hitachi        M.Gotou         Update          QC#4919
 *</pre>
 */
public class NSAL0590CommonLogic {

    /**
     * Search DS Contract Report Group Info.
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    public static void searchDsContrRptGrpInfo(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

        HashMap<String, Object> params = setDsContrRptGrpSearchParam(cMsg, SEARCH_LIMIT_COUNT + 1);
        S21SsmEZDResult ssmResult = NSAL0590Query.getInstance().getDsContracReportGroupInfoList(params, sMsg);

        if (ssmResult.isCodeNormal()) {

            // result > 2000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NSAM0006I, new String[] {Integer.toString(sMsg.A.length()) });
                queryResCnt = sMsg.A.length();
            }

            // Copy one page from SMsg to CMsg
            NSAL0590CommonLogic.copySMsgToCMsg(cMsg, sMsg);

            // Set page#
            NSAL0590CommonLogic.pagenation(cMsg, sMsg, 0);
        } else {
            cMsg.setMessageInfo(NSAM0194I, null);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    /**
     * Create DS Contract Report Group search query parameter
     * @param bizMsg NSAL0590CMsg
     * @param limitCount int
     * @return HashMap<String, Object> Report Group search parameter
     */
    private static HashMap<String, Object> setDsContrRptGrpSearchParam(NSAL0590CMsg bizMsg, int limitCount) {

        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("bizMsg", bizMsg);
        params.put("limitCount", limitCount);
        // START 2016/03/25 M.Gotou [QC#4919, ADD]
        params.put("endDt", END_DT);
        // END 2016/03/25 M.Gotou [QC#4919, ADD]

        return params;
    }

    /**
     * Submit DS Contract Report Group.
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    public static void submitDsContrRptGrp(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

        int count = sMsg.A.getValidCount();
        for (int idx = 0; idx < count; idx++) {

            NSAL0590_ASMsg asMsg = sMsg.A.no(idx);
            NSAL0590_BSMsg bsMsg = sMsg.B.no(idx);
            if (ZYPCommonFunc.hasValue(asMsg.dsContrRptGrpNum)) {
                updateDsContrRptGrp(asMsg, bsMsg, cMsg);
            } else {
                DS_CONTR_RPT_GRPTMsg insertTmsg = new DS_CONTR_RPT_GRPTMsg();
                ZYPEZDItemValueSetter.setValue(insertTmsg.glblCmpyCd, cMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(insertTmsg.dsContrRptGrpNum, ZYPExtnNumbering.getUniqueID(cMsg.glblCmpyCd.getValue(), "DS_CONTR_RPT_GRP_NUM"));
                ZYPEZDItemValueSetter.setValue(insertTmsg.dsContrRptGrpDescTxt, asMsg.dsContrRptGrpDescTxt);
                ZYPEZDItemValueSetter.setValue(insertTmsg.dsContrRptGrpStartDt, asMsg.dsContrRptGrpStartDt);
                ZYPEZDItemValueSetter.setValue(insertTmsg.dsContrRptGrpEndDt, asMsg.dsContrRptGrpEndDt);
                S21FastTBLAccessor.insert(insertTmsg);
            }
        }
    }

    private static void updateDsContrRptGrp(NSAL0590_ASMsg asMsg, NSAL0590_BSMsg bsMsg, NSAL0590CMsg cMsg) {

        if (isSamesMsg(asMsg, bsMsg)) {

            DS_CONTR_RPT_GRPTMsg inParam = new DS_CONTR_RPT_GRPTMsg();
            ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inParam.dsContrRptGrpNum, asMsg.dsContrRptGrpNum);

            DS_CONTR_RPT_GRPTMsg searchTMsg = (DS_CONTR_RPT_GRPTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inParam);

            if (searchTMsg == null) {
                cMsg.setMessageInfo(NZZM0003E, null);
            } else {
                if (isSameTimeStamp(asMsg, searchTMsg, cMsg)) {
                    ZYPEZDItemValueSetter.setValue(searchTMsg.dsContrRptGrpDescTxt, asMsg.dsContrRptGrpDescTxt);
                    ZYPEZDItemValueSetter.setValue(searchTMsg.dsContrRptGrpStartDt, asMsg.dsContrRptGrpStartDt);
                    ZYPEZDItemValueSetter.setValue(searchTMsg.dsContrRptGrpEndDt, asMsg.dsContrRptGrpEndDt);
                    S21FastTBLAccessor.update(searchTMsg);
                }
            }
        }
    }

    private static boolean isSameTimeStamp(NSAL0590_ASMsg asMsg, DS_CONTR_RPT_GRPTMsg tMsg, NSAL0590CMsg cMsg) {

        String preUpTime = asMsg.ezUpTime.getValue();
        String preTimeZone = asMsg.ezUpTimeZone.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            cMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        return true;
    }

    private static boolean isSamesMsg(NSAL0590_ASMsg asMsg, NSAL0590_BSMsg bsMsg) {

        String asMsgCrgdt = asMsg.dsContrRptGrpDescTxt.getValue();
        String bsMsgCrgdt = bsMsg.dsContrRptGrpDescTxt.getValue();
        String asMsgCrgsd = asMsg.dsContrRptGrpStartDt.getValue();
        String bsMsgCrgsd = bsMsg.dsContrRptGrpStartDt.getValue();
        String asMsgCrged = asMsg.dsContrRptGrpEndDt.getValue();
        String bsMsgCrged = bsMsg.dsContrRptGrpEndDt.getValue();

        if (asMsgCrgdt.equals(bsMsgCrgdt) && asMsgCrgsd.equals(bsMsgCrgsd) && asMsgCrged.equals(bsMsgCrged)) {
            return false;
        }
        return true;
    }

    /**
     * Pagenation
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     * @param pageFrom int
     */
    public static void pagenation(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg, int pageFrom) {

        EZDCMsgArray cAry = null;
        EZDSMsgArray sAry = null;

        cAry = cMsg.A;
        sAry = sMsg.A;

        int i = pageFrom;
        int j = 0;
        for (; i < sAry.getValidCount() && j < cAry.length(); i++, j++) {
            EZDMsg.copy(sAry.get(i), null, cAry.get(j), null);
        }
        cAry.setValidCount(j);

        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cAry.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sAry.getValidCount());
    }

    /**
     * copyCMsgToSMsg
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    public static void copyCMsgToSMsg(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {
        copyCMsgToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt() - 1);
    }

    /**
     * copyCMsgToSMsg
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     * @param fromCnt From Count
     */
    public static void copyCMsgToSMsg(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg, int fromCnt) {
        int curCnt = fromCnt;
        int cMsgCnt = 0;
        for (int i = curCnt; i < (curCnt + cMsg.A.getValidCount()); i++) {
            EZDMsg.copy(cMsg.A.no(cMsgCnt), null, sMsg.A.no(i), null);
            cMsgCnt++;
        }
    }

    /**
     * copySMsgToCMsg
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    public static void copySMsgToCMsg(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {
        // 1page copy（SMsg -> CMsg）
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    /**
     * copyFromSMsgOntoCmsg
     * @param cMsg NSAL0590CMsg
     * @param sMsg NSAL0590SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL0590CMsg cMsg, NSAL0590SMsg sMsg) {

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

    /**
     * setPageNum
     * @param cMsg DSAL0060CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NSAL0590CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * clear Search Result
     * @param bizMsg NSAL0590CMsg
     * @param globalMsg NSAL0590SMsg
     */
    public static void clearTable(NSAL0590CMsg bizMsg, NSAL0590SMsg globalMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
    }

    /**
     * clearPageNum
     * @param cMsg NSAL0590CMsg
     */
    public static void clearPageNum(NSAL0590CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }
}
