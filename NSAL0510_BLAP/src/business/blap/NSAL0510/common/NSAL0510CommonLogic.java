/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0510.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import business.blap.NSAL0510.NSAL0510CMsg;
import business.blap.NSAL0510.NSAL0510Query;
import business.blap.NSAL0510.NSAL0510SMsg;
import business.blap.NSAL0510.constant.NSAL0510Constant.MDL_ACTV_FLG;
import business.blap.NSAL0510.constant.NSAL0510Constant.MSG_ID;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Sub Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/01/08   Hitachi         T.Tsuchida      Update          QC#2844
 * 2017/11/16   Hitachi         K.Kojima        Update          QC#21886
 *</pre>
 */
public class NSAL0510CommonLogic {

    /**
     * Sets the pagenation.
     * @param xxPageShowOfNum the xx page show of num
     * @param xxPageShowToNum the xx page show to num
     * @param xxPageShowFromNum the xx page show from num
     * @param pageRecs the page records
     * @param totalRecs the total records
     */
    public static void setPagenation(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {

        if (xxPageShowOfNum == null //
                || xxPageShowToNum == null) {
            return;
        }
        if (pageRecs == 0 || totalRecs == 0) {
            return;
        }

        xxPageShowToNum.setValue(xxPageShowFromNum + pageRecs - 1);
        xxPageShowOfNum.setValue(totalRecs);

    }

    /**
     * createPullDown
     * @param cMsg NSAL0510CMsg
     * @param srchOptUsrId String
     */
    public static void createPullDown(NSAL0510CMsg cMsg, String srchOptUsrId) {
        createActiveStatusPullDown(cMsg);
        createContractStatusPullDown(cMsg);
    }

    private static void createActiveStatusPullDown(NSAL0510CMsg cMsg) {
        ZYPPulldownValueSetter.insertFirstData(ZYPConstant.FLG_OFF_N, cMsg.mdlActvFlg_L, MDL_ACTV_FLG.N.getmdlActvFlgNm(), cMsg.xxScrItem10Txt_L);
        ZYPPulldownValueSetter.insertFirstData(ZYPConstant.FLG_ON_Y, cMsg.mdlActvFlg_L, MDL_ACTV_FLG.Y.getmdlActvFlgNm(), cMsg.xxScrItem10Txt_L);
    }

    private static void createContractStatusPullDown(NSAL0510CMsg cMsg) {
        // START 2017/11/16 K.Kojima [QC#21886,MOD]
        // DS_CONTR_CTRL_STSTMsgArray tMsgAry = NSAL0510Query.getInstance().findContrCtrlStsCdPulldownList(cMsg.glblCmpyCd.getValue());
        // Map<String, String> tMsgKeys = new HashMap<String, String>();
        // tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "dsContrCtrlStsCd");
        // tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "dsContrCtrlStsNm");
        // ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.dsContrCtrlStsCd_L, cMsg.dsContrCtrlStsNm_L);
        S21SsmEZDResult ssmResult = NSAL0510Query.getInstance().getContrCtrlStsCdPulldownList(cMsg.glblCmpyCd.getValue());
        if (!ssmResult.isCodeNormal()) {
            return;
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < list.size(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(i), (String) list.get(i).get("DS_CONTR_CTRL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(i), (String) list.get(i).get("DS_CONTR_CTRL_STS_NM"));
        }
        // END 2017/11/16 K.Kojima [QC#21886,MOD]
    }

    /**
     * getSearchData
     * @param cMsg NSAL0510CMsg
     * @param sMsg NSAL0510SMsg
     */
    public static void getSearchData(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL0510Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(MSG_ID.NZZM0001W.toString(), new String[]{Integer.toString(sMsg.A.length())});
                queryResCnt = sMsg.A.length();
            }

            // Copy one page from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Set page#
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            // No result
            cMsg.setMessageInfo(MSG_ID.NZZM0000E.toString());
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * copySMsgTocMsg
     * @param cMsg NSAL0510CMsg
     * @param sMsg NSAL0510SMsg
     */
    public static void copyGlblMsgToBizMsg(NSAL0510CMsg cMsg, NSAL0510SMsg sMsg) {
        int ixS = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.A.length() && ixS < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(ixS++), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    /**
     * preSetToPageOne
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        xxPageShowFromNum.setValue(BigDecimal.ONE);
    }
}
