/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0670.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0670.NSAL0670CMsg;
import business.blap.NSAL0670.NSAL0670SMsg;
import business.blap.NSAL0670.NSAL0670_ACMsg;
import business.blap.NSAL0670.NSAL0670_ACMsgArray;
import business.blap.NSAL0670.NSAL0670_ASMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;

/**
 *<pre>
 * Cancel Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kasai         Create          N/A
 * 2016/11/28   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0670CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL0670CMsg
     */
    public static void createPullDown(NSAL0670CMsg cMsg) {
        createSvcMemoRsnPullDown(cMsg);
    }

    private static void createSvcMemoRsnPullDown(NSAL0670CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.CANCEL_CONTRACT_OR_MACHINE);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_H1, cMsg.svcMemoRsnNm_H2);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // add start 2016/11/28 CSA QC#4210
    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0670CMsg
     * @param sMsg NSAL0670SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0670CMsg cMsg, NSAL0670SMsg sMsg) {

        // NSAL0670_ACMsg -> NSAL0670_BSMsg
        NSAL0670_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0670_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A1.getValueInt();

            NSAL0670_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            setValue(asMsg.xxChkBox_A1, acMsg.xxChkBox_A1);
        }
    }

    /**
     * @param cMsg NSAL0670CMsg
     * @param sMsg NSAL0670SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL0670CMsg cMsg, NSAL0670SMsg sMsg) {

        int noCmsg = cMsg.A.no(0).xxRowNum_A1.getValueInt();
        cMsg.A.clear();
        int count = 0;
        for (int i = noCmsg; i < sMsg.A.getValidCount(); i++) {
            if (count < cMsg.A.length()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(count), null);
                count++;
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(count);
    }
    // add end 2016/11/28 CSA QC#4210
}
