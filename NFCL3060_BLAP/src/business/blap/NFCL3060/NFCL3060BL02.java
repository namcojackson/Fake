package business.blap.NFCL3060;

import static business.blap.NFCL3060.constant.NFCL3060Constant.NZZM0000E;
import static business.blap.NFCL3060.constant.NFCL3060Constant.NZZM0001W;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL3060.common.NFCL3060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFCL3060BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/1/8     Fujitsu         S.Fujita        Create          N/A
 * 2016/07/14   Fujitsu         S.Fujita        Update          QC#11023
 *</pre>
 */
public class NFCL3060BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL3060CMsg bizMsg = (NFCL3060CMsg) cMsg;
            NFCL3060SMsg glblMsg = (NFCL3060SMsg) sMsg;

            if ("NFCL3060_INIT".equals(screenAplID)) {
                doProcess_NFCL3060_INIT(bizMsg, glblMsg);
            // START 2016/07/14 S.Fujita [QC#11023,ADD]
            } else if ("NFCL3060_NFCL0730".equals(screenAplID)) {
                doProcess_NFCL3060_NFCL0730(bizMsg, glblMsg);
            } else if ("NFCL3060_NFCL0860".equals(screenAplID)) {
                doProcess_NFCL3060_NFCL0860(bizMsg, glblMsg);
            // END   2016/07/14 S.Fujita [QC#11023,ADD]
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3060_INIT(NFCL3060CMsg bizMsg, NFCL3060SMsg glblMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        if (ZYPCommonFunc.hasValue(bizMsg.arTrxNum)) {
            paymentsearch(bizMsg, glblMsg);
            search(bizMsg, glblMsg);
        }
    }

    // START 2016/07/14 S.Fujita [QC#11023,ADD]
    /**
     * Return From NFCL0730 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3060_NFCL0730(NFCL3060CMsg bizMsg, NFCL3060SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * Return From NFCL0860 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3060_NFCL0860(NFCL3060CMsg bizMsg, NFCL3060SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }
    // END   2016/07/14 S.Fujita [QC#11023,ADD]

    /**
     * payment search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void paymentsearch(NFCL3060CMsg bizMsg, NFCL3060SMsg glblMsg) {

        S21SsmEZDResult ssmResult = NFCL3060Query.getInstance().paymentsearch(bizMsg, glblMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyByItemNm, glblMsg.xxDplyByItemNm);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NFCL3060CMsg bizMsg, NFCL3060SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NFCL3060Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            NFCL3060CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
        }
    }
}
