package business.blap.NSAL0740.common;

import static business.blap.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_BASE_OVERAGE;
import static business.blap.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_CONTRACT;
import static business.blap.NSAL0740.constant.NSAL0740Constant.LINE_LEVEL_CONTRACT_DETAIL;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0740.NSAL0740CMsg;
import business.blap.NSAL0740.NSAL0740SMsg;
import business.db.CONTR_UPLFT_TPTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/07   Hitachi         T.Aoyagi        Update          QC#3945
 * 2016/05/17   Hitachi         M.Gotou         Update          QC#4472
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#8608
 * 2016/12/05   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */

public class NSAL0740CommonLogic {

    // mod start 2016/05/17 CSA Defect#4472
    /**
     * validate checkbox.
     * @param cMsg NSAL0740CMsg cMsg
     * @return true/false
     */
    public static boolean validateCheckBox(NSAL0740CMsg cMsg) {
        boolean rtnVal = false;
        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            if ((LINE_LEVEL_CONTRACT.equals(cMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(index).xxChkBox_S1.getValue()))
                    || (LINE_LEVEL_CONTRACT_DETAIL.equals(cMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(index).xxChkBox_S1.getValue()))
                    || (LINE_LEVEL_BASE_OVERAGE.equals(cMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(index).xxChkBox_S2.getValue()))) {
                rtnVal = true;
                break;
            }
        }
        return rtnVal;
    }
    /**
     * validate checkbox.
     * @param sMsg NSAL0740SMsg sMsg
     * @return true/false
     */
    public static boolean validateCheckBoxSMsg(NSAL0740SMsg sMsg) {
        boolean rtnVal = false;
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            if ((LINE_LEVEL_CONTRACT.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S1.getValue()))
                    || (LINE_LEVEL_CONTRACT_DETAIL.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S1.getValue()))
                    || (LINE_LEVEL_BASE_OVERAGE.equals(sMsg.A.no(index).dsContrMachLvlNum_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(index).xxChkBox_S2.getValue()))) {
                rtnVal = true;
                break;
            }
        }
        return rtnVal;
    }
    // mod end 2016/05/17 CSA Defect#4472

    /**
     * create pulldown list.
     * @param cMsg NSAL0740CMsg cMsg
     */
    public static void createPullDown(NSAL0740CMsg cMsg) {
        // START 2016/03/07 T.Aoyagi [QC#3945, MOD]
        ZYPCodeDataUtil.createPulldownList(CONTR_UPLFT_TP.class, cMsg.contrUplftTpCd_H1, cMsg.contrUplftTpNm_H2);
        ZYPCodeDataUtil.createPulldownList(UPLFT_PRC_METH.class, cMsg.uplftPrcMethCd_H1, cMsg.uplftPrcMethNm_H2);
        // END 2016/03/07 T.Aoyagi [QC#3945, MOD]
        createSvcMemoPullDown(cMsg);
    }

    private static void createSvcMemoPullDown(NSAL0740CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_H1, cMsg.svcMemoRsnNm_H2);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(NSAL0740CMsg cMsg) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.ESCALATION_RULES);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    // add start 2016/07/13 CSA Defect#8608
    /**
     * getContrUplftTp
     * @param glblCmpyCd String
     * @param contrUplftTpCd String
     * @return CONTR_UPLFT_TPTMsg
     */
    public static CONTR_UPLFT_TPTMsg getContrUplftTp(String glblCmpyCd, String contrUplftTpCd) {
        if (!ZYPCommonFunc.hasValue(contrUplftTpCd)) {
            return null;
        }
        return (CONTR_UPLFT_TPTMsg) ZYPCodeDataUtil.findByCode(CONTR_UPLFT_TP.class, glblCmpyCd, contrUplftTpCd);
    }
    // add end 2016/07/13 CSA Defect#8608

    // mod start 2016/12/05 CSA QC#4210
    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL0740CMsg
     * @param sMsg NSAL0740SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0740CMsg cMsg, NSAL0740SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }
    // mod end 2016/12/05 CSA QC#4210
}
