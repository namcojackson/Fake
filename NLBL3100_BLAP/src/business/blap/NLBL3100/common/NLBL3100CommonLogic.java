/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3100.common;

import parts.common.EZDMsg;
import business.blap.NLBL3100.NLBL3100CMsg;
import business.blap.NLBL3100.NLBL3100Query;
import business.blap.NLBL3100.NLBL3100SMsg;
import business.blap.NLBL3100.NLBL3100_ACMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NLBL3100 Warehouse Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLBL3100CommonLogic {

    /**
     * clear Msg
     * @param cMsg NLBL3100CMsg
     * @param sMsg NLBL3100SMsg
     */
    public static void clearMsg(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg) {

        // cMsg initialization
        // Header
        cMsg.rtlWhCd_H1.clear();
        cMsg.rtlWhNm_H1.clear();
        cMsg.schdCoordPsnCd_H1.clear();
        cMsg.psnFirstNm_H1.clear();
        cMsg.psnLastNm_H1.clear();
        cMsg.xxPsnNm_H1.clear();
        cMsg.mgrPsnCd_H1.clear();
        cMsg.psnFirstNm_H2.clear();
        cMsg.psnLastNm_H2.clear();
        cMsg.xxPsnNm_H2.clear();
        cMsg.supvPsnCd_H1.clear();
        cMsg.psnFirstNm_H3.clear();
        cMsg.psnLastNm_H3.clear();
        cMsg.xxPsnNm_H3.clear();
        cMsg.carrCd_H1.clear();
        cMsg.psnFirstNm_H4.clear();
        cMsg.psnLastNm_H4.clear();
        cMsg.xxPsnNm_H4.clear();
        cMsg.stCd_H1.clear();
        cMsg.effFromDt_H1.clear();
        cMsg.effThruDt_H1.clear();

        ZYPTableUtil.clear(cMsg.A);

        // sMsg initialization
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

    }

    /**
     * The method explanation: The search processing of Retail
     * Warehouse information is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param handler S21BusinessHandler
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult search(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg, S21BusinessHandler handler) {

        S21SsmEZDResult ssmResult = NLBL3100Query.getInstance().getCoordinatorInfo(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            EZDMsg.copy(sMsg, null, cMsg, null);

            int queryResCnt = ssmResult.getQueryResultCount();

            int i = 0;
            for (; i < queryResCnt; i++) {

                if (i == cMsg.A.length()) {
                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
        }

        return ssmResult;
    }

    /**
     * Copy CMsg to SMsg
     * @param cMsg NLBL3100CMsg
     * @param sMsg NLBL3100SMsg
     */
    public static void copyCMsgToSMsg(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg) {
        cMsg.clearErrorInfo();
        sMsg.clearErrorInfo();

        copyInputValueToSMsg(cMsg, sMsg);

        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        for (int idx = 0; idx < cMsg.A.getValidCount(); idx++) {

            int sMsgIdx = idx + cMsg.xxPageShowFromNum.getValueInt() - 1;

            if (sMsgIdx < sMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.no(idx), null, sMsg.A.no(sMsgIdx), null);
            }
        }
    }

    /**
     * Copy SMsg to CMsg
     * @param cMsg NLBL3100CMsg
     * @param sMsg NLBL3100SMsg
     * @param index int
     */
    public static void copySMsgToCMsg(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg, int index) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        int startIdx = 0;
        int sMsgIdx = 0;
        int dispRowCnt = 0;

        startIdx = getPageStartRowIndex(index, cMsg.A);
        sMsgIdx = startIdx;

        for (int idx = 0; idx < cMsg.A.length(); idx++) {

            if (sMsgIdx > sMsg.A.getValidCount() - 1) {
                break;
            }

            EZDMsg.copy(sMsg.A.no(sMsgIdx), null, cMsg.A.no(idx), null);
            dispRowCnt++;
            sMsgIdx++;
        }
        cMsg.A.setValidCount(dispRowCnt);
        cMsg.xxPageShowFromNum.setValue(startIdx + 1);
        cMsg.xxPageShowToNum.setValue(startIdx + dispRowCnt);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Copy input value to SMsg
     * @param cMsg NLBL3100CMsg
     * @param sMsg NLBL3100SMsg
     */
    private static void copyInputValueToSMsg(NLBL3100CMsg cMsg, NLBL3100SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCd_H1, cMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_H1, cMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.schdCoordPsnCd_H1, cMsg.schdCoordPsnCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxPsnNm_H1, cMsg.xxPsnNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.mgrPsnCd_H1, cMsg.mgrPsnCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxPsnNm_H2, cMsg.xxPsnNm_H2);
        ZYPEZDItemValueSetter.setValue(sMsg.supvPsnCd_H1, cMsg.supvPsnCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxPsnNm_H3, cMsg.xxPsnNm_H3);
        ZYPEZDItemValueSetter.setValue(sMsg.carrCd_H1, cMsg.carrCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxPsnNm_H4, cMsg.xxPsnNm_H4);
        ZYPEZDItemValueSetter.setValue(sMsg.stCd_H1, cMsg.stCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.effFromDt_H1, cMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.effThruDt_H1, cMsg.effThruDt_H1);
    }

    /**
     * Get page start row index
     * @param index int
     * @param aMsg NLBL3100_ACMsgArray
     * @return int
     */
    private static int getPageStartRowIndex(int index, NLBL3100_ACMsgArray aMsg) {
        int startIndex = (index / aMsg.length()) * aMsg.length();
        return startIndex;
    }

}
