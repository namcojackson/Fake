/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0060;

import static business.blap.NSAL0060.constant.NSAL0060Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;

import business.blap.NSAL0060.common.NSAL0060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi      Create          N/A
 * 08/29/2013   Hitachi         T.Aoyagi        Update          QC1955
 * 2018/06/18   Fujitsu         T.Ogura         Update          QC#24512
 *</pre>
 */
public class NSAL0060BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            // START 2018/06/18 T.Ogura [QC#24512,MOD]
//            if ("NSAL0060_INIT".equals(screenAplID)) {
//                doProcess_NSAL0060_INIT((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
//            } else if ("NSAL0060Scrn00_Search".equals(screenAplID)) {
            if ("NSAL0060Scrn00_Search".equals(screenAplID)) {
            // END   2018/06/18 T.Ogura [QC#24512,MOD]
                doProcess_NSAL0060Scrn00_Search((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
            } else if ("NSAL0060Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSAL0060Scrn00_InsertRow((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
            } else if ("NSAL0060Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSAL0060Scrn00_DeleteRow((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
            } else if ("NSAL0060Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0060Scrn00_PageNext((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
            } else if ("NSAL0060Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0060Scrn00_PagePrev((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
            } else if ("NSAL0060Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0060Scrn00_PageJump((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
            } else if ("NSAL0060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0060Scrn00_CMN_Submit((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
            } else if ("NSAL0060Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL0060Scrn00_CMN_Clear((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // START 2018/06/18 T.Ogura [QC#24512,DEL]
//    private void doProcess_NSAL0060_INIT(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
//        cMsg.clear();
//        NSAL0060CommonLogic.clearTable(cMsg, sMsg);
//        NSAL0060CommonLogic.clearPageNum(cMsg);
//        // START 2013/08/29 T.Aoyagi [QC1955,ADD]
//        NSAL0060CommonLogic.searchMdlGrp(cMsg, sMsg, getGlobalCompanyCode(), false);
//        // END 2013/08/29 T.Aoyagi [QC1955,ADD]
//    }
    // END   2018/06/18 T.Ogura [QC#24512,DEL]

    private void doProcess_NSAL0060Scrn00_Search(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        NSAL0060CommonLogic.searchMdlGrp(cMsg, sMsg, getGlobalCompanyCode(), false);
    }

    private void doProcess_NSAL0060Scrn00_InsertRow(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        int count = sMsg.A.getValidCount();
        if (sMsg.A.length() < count + 1) {
            cMsg.setMessageInfo(NSAM0112E);
            return;
        }
        NSAL0060CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        sMsg.A.setValidCount(count + 1);
        sMsg.B.setValidCount(count + 1);

        int lastPage = count / cMsg.A.length();
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(lastPage * cMsg.A.length());
        cMsg.xxPageShowToNum.clear();

        NSAL0060CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSAL0060Scrn00_DeleteRow(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        NSAL0060CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_SR", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        int n = sMsg.C.getValidCount();
        for (int i : selectedRows) {
            if (!hasValue(sMsg.A.no(i).mdlGrpId_SR)) {
                continue;
            }
            if (NSAL0060CommonLogic.existRelatedInfo(sMsg.A.no(i).mdlGrpId_SR.getValue(), getGlobalCompanyCode())) {
                int showPage = i / cMsg.A.length();
                int showFromNum = showPage * cMsg.A.length();

                NSAL0060CommonLogic.setPagenation(cMsg, sMsg, showFromNum);
                setRelatedError(cMsg, sMsg.A.no(i).mdlGrpId_SR);
                return;
            }
            EZDMsg.copy(sMsg.A.no(i), null, sMsg.C.no(n), null);
            n++;
        }
        sMsg.C.setValidCount(n);

        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
        ZYPTableUtil.deleteRows(sMsg.B, selectedRows);

        int currPage = cMsg.xxPageShowFromNum.getValueInt();
        int lastPage = (sMsg.A.getValidCount() - 1) / cMsg.A.length();
        if (lastPage < currPage) {
            currPage = lastPage;
        }

        NSAL0060CommonLogic.setPagenation(cMsg, sMsg, (currPage * cMsg.A.length()));
    }

    private void setRelatedError(NSAL0060CMsg cMsg, EZDSBigDecimalItem mdlGrpId) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (mdlGrpId.getValue().equals(cMsg.A.no(i).mdlGrpId_SR.getValue())) {
                cMsg.A.no(i).xxChkBox_SR.setErrorInfo(1, NSAM0184E, new String[]{"model"});
                return;
            }
        }
    }

    private void doProcess_NSAL0060Scrn00_PageNext(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        NSAL0060CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        cMsg.xxPageShowToNum.clear();

        NSAL0060CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSAL0060Scrn00_PagePrev(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        NSAL0060CommonLogic.copyCMsgToSMsg(cMsg, sMsg);

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);
        cMsg.xxPageShowToNum.clear();

        NSAL0060CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSAL0060Scrn00_PageJump(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        NSAL0060CommonLogic.copyCMsgToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum_BK.getValueInt() - 1);
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowToNum.clear();
        NSAL0060CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSAL0060Scrn00_CMN_Submit(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        if (!hasValue(cMsg.mdlGrpNm_SC) && !hasValue(cMsg.mdlGrpDescTxt_SC)) {
            cMsg.clear();
            NSAL0060CommonLogic.clearTable(cMsg, sMsg);
            NSAL0060CommonLogic.clearPageNum(cMsg);
            return;
        }
        NSAL0060CommonLogic.searchMdlGrp(cMsg, sMsg, getGlobalCompanyCode(), true);
    }

    private void doProcess_NSAL0060Scrn00_CMN_Clear(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        cMsg.clear();
        NSAL0060CommonLogic.clearTable(cMsg, sMsg);
        NSAL0060CommonLogic.clearPageNum(cMsg);
    }
}
