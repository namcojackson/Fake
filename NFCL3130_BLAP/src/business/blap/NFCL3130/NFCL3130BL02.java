package business.blap.NFCL3130;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NFCL3130.constant.NFCL3130Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/11   Hitachi         K.Kojima        Update          QC#11576
 *</pre>
 */
public class NFCL3130BL02 extends S21BusinessHandler implements NFCL3130Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if (SCREEN_EVENT_NAME.NFCL3130_INIT.toString().equals(screenAplID)) {
                doProcess_NFCL3130_INIT(cMsg, sMsg);
            } else if (SCREEN_EVENT_NAME.NFCL3130_NWAL1130.toString().equals(screenAplID)) {
                doProcess_NWAL1130(cMsg, sMsg);
            } else if (SCREEN_EVENT_NAME.NFCL3130Scrn00_CMN_Submit.toString().equals(screenAplID)) {
                // START 2016/07/11 K.Kojima [QC#11576,MOD]
                // doProcess_NFCL3130_INIT(cMsg, sMsg);
                doProcess_NFCL3130Scrn00_CMN_Submit(cMsg, sMsg);
                // END 2016/07/11 K.Kojima [QC#11576,MOD]
            } else if (SCREEN_EVENT_NAME.NFCL3130Scrn00_CMN_Clear.toString().equals(screenAplID)) {
                doProcess_NFCL3130_INIT(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /***
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3130_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3130CMsg bizMsg = (NFCL3130CMsg) cMsg;
        NFCL3130SMsg globalMsg = (NFCL3130SMsg) sMsg;
        cMsg.clear();
        sMsg.clear();

        ZYPCodeDataUtil.createPulldownList(AR_RCPT_SRC_TP.class, bizMsg.arRcptSrcTpCd, bizMsg.xxMsgTxt_D1, ":");

        EZDMsg.copy(bizMsg, null, globalMsg, null);
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NWAL1130(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3130CMsg bizMsg = (NFCL3130CMsg) cMsg;
        NFCL3130SMsg globalMsg = (NFCL3130SMsg) sMsg;

        // START 2016/07/11 K.Kojima [QC#11576,ADD]
        String scrEventNm = bizMsg.xxScrEventNm.getValue();
        if ("Click_rcptScr".equals(scrEventNm)) {
            // END 2016/07/11 K.Kojima [QC#11576,ADD]
            Map<String, Object> ssmMap = new HashMap<String, Object>();
            ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
            ssmMap.put("arRcptSrcCd", bizMsg.arRcptSrcCd.getValue());

            // Search Receipt Type Data
            S21SsmEZDResult ssmResult = NFCL3130Query.getInstance().findRcptData(ssmMap, bizMsg);

            if (!ssmResult.isCodeNormal()) {
                bizMsg.setMessageInfo("NFDM0034I");
            }

            // Bank Account Information
            ssmResult = new S21SsmEZDResult();
            ssmMap = new HashMap<String, Object>();
            ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
            ssmMap.put("arRcptSrcCd", bizMsg.arRcptSrcCd.getValue());
            ssmMap.put("slsDt", ZYPDateUtil.getSalesDate());
            // Search Receipt Type Data
            ssmResult = NFCL3130Query.getInstance().findBankAcct(ssmMap, bizMsg);

            if (!ssmResult.isCodeNormal()) {
                bizMsg.setMessageInfo("NFDM0034I");
            }

            EZDMsg.copy(bizMsg, null, globalMsg, null);
            // START 2016/07/11 K.Kojima [QC#11576,ADD]
        }
        // END 2016/07/11 K.Kojima [QC#11576,ADD]

    }

    // START 2016/07/11 K.Kojima [QC#11576,ADD]
    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NFCL3130Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
    }
    // END 2016/07/11 K.Kojima [QC#11576,ADD]
}
