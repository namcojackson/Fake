/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL7160;

import static business.blap.NMAL7160.constant.NMAL7160Constant.NMAM0007I;
import static business.blap.NMAL7160.constant.NMAL7160Constant.ZZZM9002W;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7160.common.NMAL7160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public class NMAL7160BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7160CMsg bizMsg = (NMAL7160CMsg) cMsg;
            NMAL7160SMsg glblMsg = (NMAL7160SMsg) sMsg;

            if ("NMAL7160_INIT".equals(screenAplID)) {
                doProcess_NMAL7160_INIT(bizMsg, glblMsg);

            } else if ("NMAL7160Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7160Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL7160Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7160Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7160Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7160Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7160Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7160Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL7160Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7160Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL7160Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7160Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL7160Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL7160Scrn00_PageJump(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (Init)
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    private void doProcess_NMAL7160_INIT(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        // Setting Initial Values
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
    }

    /**
     * do Process (CMN_Clear)
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    private void doProcess_NMAL7160Scrn00_CMN_Clear(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        // Initial Screen Objects
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);

        doProcess_NMAL7160_INIT(bizMsg, glblMsg);
    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    private void doProcess_NMAL7160Scrn00_CMN_Download(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        NMAL7160CommonLogic.csvDownload(bizMsg, glblMsg);

    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    private void doProcess_NMAL7160Scrn00_CMN_Submit(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        doProcess_NMAL7160Scrn00_Search(bizMsg, glblMsg);

    }

    /**
     * do Process (Search)
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    private void doProcess_NMAL7160Scrn00_Search(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        // Search
        S21SsmEZDResult ssmResult = NMAL7160Query.getInstance().getSearchResult(bizMsg, glblMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM0007I);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }

        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > glblMsg.A.length()) {
            bizMsg.setMessageInfo(ZZZM9002W);
        }

        NMAL7160CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
    }

    /**
     * do Process (PageNext)
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    private void doProcess_NMAL7160Scrn00_PageNext(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        NMAL7160CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt());
        bizMsg.xxPageShowToNum.clear();
        NMAL7160CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);

    }

    /**
     * do Process (PageNext)
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    private void doProcess_NMAL7160Scrn00_PagePrev(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        NMAL7160CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1);
        bizMsg.xxPageShowToNum.clear();

        NMAL7160CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);

    }

    /**
     * do Process (PageJump)
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    private void doProcess_NMAL7160Scrn00_PageJump(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {
        NMAL7160CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);

        bizMsg.xxPageShowFromNum.setValue(bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1));
        bizMsg.xxPageShowToNum.clear();

        NMAL7160CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
    }

}
