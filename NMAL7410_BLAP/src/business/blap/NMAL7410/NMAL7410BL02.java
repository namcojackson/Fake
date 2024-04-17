/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL7410;

import static business.blap.NMAL7410.constant.NMAL7410Constant.MSG_PARAM_CHECK_BOX;
import static business.blap.NMAL7410.constant.NMAL7410Constant.NMAM0007I;
import static business.blap.NMAL7410.constant.NMAL7410Constant.ZZM9000E;
import static business.blap.NMAL7410.constant.NMAL7410Constant.ZZZM9002W;
import static business.blap.NMAL7410.constant.NMAL7410Constant.NZZM0013E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7410.common.NMAL7410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Murai         Create          N/A
 * </pre>
 */
public class NMAL7410BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7410CMsg bizMsg = (NMAL7410CMsg) cMsg;
            NMAL7410SMsg glblMsg = (NMAL7410SMsg) sMsg;

            if ("NMAL7410_INIT".equals(screenAplID)) {
                doProcess_NMAL7410_INIT(bizMsg, glblMsg);

            } else if ("NMAL7410Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL7410Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7410Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7410Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL7410Scrn00_Add".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_Add(bizMsg, glblMsg);

            } else if ("NMAL7410Scrn00_Delete".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_Delete(bizMsg, glblMsg);

            } else if ("NMAL7410Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL7410Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL7410Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL7410Scrn00_PageJump(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (Init)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410_INIT(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        // Initial Screen Objects
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        // Setting Initial Values
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum_A, BigDecimal.valueOf(glblMsg.A.length()));
    }

    /**
     * do Process (CMN_Clear)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410Scrn00_CMN_Clear(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {
        doProcess_NMAL7410_INIT(bizMsg, glblMsg);
    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410Scrn00_CMN_Download(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        NMAL7410CommonLogic.csvDownload(bizMsg, glblMsg);

    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410Scrn00_CMN_Submit(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        doProcess_NMAL7410Scrn00_Search(bizMsg, glblMsg);

    }

    /**
     * do Process (Search)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410Scrn00_Search(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        // Search
        S21SsmEZDResult ssmResult = NMAL7410Query.getInstance().getSearchResult(bizMsg, glblMsg);
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

        NMAL7410CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
    }

    /**
     * do Process (PageNext)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410Scrn00_Add(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        if (glblMsg.A.getValidCount() == glblMsg.A.length()) {
            bizMsg.setMessageInfo(NZZM0013E);
            return;
        }

        NMAL7410CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);

        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
        bizMsg.xxPageShowFromNum.setValue(bizMsg.A.length() * ((glblMsg.A.getValidCount() - 1) / bizMsg.A.length()));
        bizMsg.xxPageShowToNum.clear();
        NMAL7410CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);

    }

    /**
     * do Process (PageNext)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410Scrn00_Delete(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        NMAL7410CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        if (checkList.size() == 0) {
            bizMsg.setMessageInfo(ZZM9000E, new String[] {MSG_PARAM_CHECK_BOX });
            return;
        }

        NMAL7410CommonLogic.deleteLine(glblMsg, checkList);

        int fromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        if (fromNum >= glblMsg.A.getValidCount()) {
            fromNum = bizMsg.A.length() * ((glblMsg.A.getValidCount() - 1) / bizMsg.A.length());
        }
        bizMsg.xxPageShowFromNum.setValue(fromNum);

        NMAL7410CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
    }

    /**
     * do Process (PageNext)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410Scrn00_PageNext(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        NMAL7410CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt());
        bizMsg.xxPageShowToNum.clear();
        NMAL7410CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);

    }

    /**
     * do Process (PageNext)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410Scrn00_PagePrev(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        NMAL7410CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);

        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1);
        bizMsg.xxPageShowToNum.clear();

        NMAL7410CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);

    }

    /**
     * do Process (PageJump)
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    private void doProcess_NMAL7410Scrn00_PageJump(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {
        NMAL7410CommonLogic.copyFromCMsgOntoSmsg(bizMsg, glblMsg);

        bizMsg.xxPageShowFromNum.setValue(bizMsg.A.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1));
        bizMsg.xxPageShowToNum.clear();

        NMAL7410CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
    }

}
