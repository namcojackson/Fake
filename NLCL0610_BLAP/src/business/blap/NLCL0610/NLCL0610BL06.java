/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0610;

import static business.blap.NLCL0610.constant.NLCL0610Constant.BLANK;
import static business.blap.NLCL0610.constant.NLCL0610Constant.CONST_VALUE_KEY_TECH_PI_CANC_CNT_STS_CD;
import static business.blap.NLCL0610.constant.NLCL0610Constant.EVENT_NM_NLCL0610_ONCLICK_CANCEL;
import static business.blap.NLCL0610.constant.NLCL0610Constant.MSG_ID_NLAM1118E;
import static business.blap.NLCL0610.constant.NLCL0610Constant.MSG_TXT_TECH_PI_CANC_CNT_STS_CD;
import static business.blap.NLCL0610.constant.NLCL0610Constant.NLCM0179E;
import static business.blap.NLCL0610.constant.NLCL0610Constant.NZZM0002I;

import java.util.ArrayList;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0610.common.NLCL0610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 * 2016/11/08   CITS            R.Shimamoto     Update          V0.2
 * 2018/09/21   CITS            K.Ogino         Update          QC#28191
 *</pre>
 */
public class NLCL0610BL06 extends S21BusinessHandler {

    /**
     * Screen Application ID
     */
    private String screenAplID;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLCL0610_ONCLICK_CANCEL.equals(screenAplID)) {
                doProcess_NLCL0610Scrn00_Cancel((NLCL0610CMsg) cMsg, (NLCL0610SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <p>
     * Cancel PI.
     * </p>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLCL0610Scrn00_Cancel(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String physInvtyNum = cMsg.A.no(cMsg.xxRadioBtn_A.getValueInt()).physInvtyNum_A.getValue();

        // 2016/11/08 V0.2 Mod Start.
        // Get Status codes can be canceled by Tech or MGR.
        String techPiCancCntStsCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_VALUE_KEY_TECH_PI_CANC_CNT_STS_CD, glblCmpyCd);
        if (techPiCancCntStsCd == null || techPiCancCntStsCd.equals(BLANK)) {
            // Status codes can be canceled by Tech or MGR is no data
            throw new S21AbendException(MSG_ID_NLAM1118E, new String[] {MSG_TXT_TECH_PI_CANC_CNT_STS_CD });
        }
        ArrayList<String> techPiCancCntAtsCdList = new ArrayList<String>();
        String[] arrayTechPiCancCntStsCd = techPiCancCntStsCd.split(",", 0);
        for (int i = 0; i < arrayTechPiCancCntStsCd.length; i++) {
            techPiCancCntAtsCdList.add(arrayTechPiCancCntStsCd[i]);
        }

        S21SsmEZDResult result = NLCL0610Query.getInstance().countPiForCancel(glblCmpyCd, physInvtyNum, techPiCancCntAtsCdList);
        // 2016/11/08 V0.2 Add End.

        int cnt = 0;
        if (result.isCodeNormal()) {
            cnt =  (Integer) result.getResultObject();
        }
        // QC#28191
        if (cnt > 0) {
            cMsg.setMessageInfo(NLCM0179E, new String[]{cMsg.A.no(cMsg.xxRadioBtn_A.getValueInt()).physInvtyCtrlNm_A.getValue()});
            return;
        }

        // Cancel PI
        if (!NLCL0610CommonLogic.callPiCloseApi(cMsg)) {
            return;
        }

        cMsg.setMessageInfo(NZZM0002I);
    }
}
