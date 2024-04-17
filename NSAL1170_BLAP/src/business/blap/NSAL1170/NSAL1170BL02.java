package business.blap.NSAL1170;

import static business.blap.NSAL1170.constant.NSAL1170Constant.NFCM0094E;
import static business.blap.NSAL1170.constant.NSAL1170Constant.NZZM0000E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1170.common.NSAL1170CommonLogic;
import business.db.DS_MDLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Model Escalation Rules Maintenance.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27   Hitachi         T.Nishimura     Create          N/A
 * 2016/02/04   Hitachi         T.Nishimura     Update          QC#4121
 * 2016/12/13   Hitachi         T.Mizuki        Update          QC#16369
 *</pre>
 */
public class NSAL1170BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        super.preDoProcess(arg0, arg1);
        NSAL1170CMsg cMsg = (NSAL1170CMsg) arg0;
        NSAL1170SMsg sMsg = (NSAL1170SMsg) arg1;

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1170_INIT".equals(screenAplID)) {
                doProcess_NSAL1170_INIT(cMsg, sMsg);
            } else if ("NSAL1170Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1170Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL1170Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1170Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL1170Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSAL1170Scrn00_InsertRow(cMsg, sMsg);
            } else if ("NSAL1170Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSAL1170Scrn00_DeleteRow(cMsg, sMsg);
            } else if ("NSAL1170Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1170Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL1170Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1170Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1170Scrn00_CMN_Submit(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        doProcess_NSAL1170_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL1170_INIT(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        NSAL1170CommonLogic.getAddRowData(cMsg, sMsg);
        // mod start 2016/12/13 CSA QC#16369
        NSAL1170CommonLogic.getSearchData(cMsg, sMsg);
        if (sMsg.Y.getValidCount() == 0 && sMsg.A.getValidCount() == 0) {
            DS_MDLTMsg dsMdlTMsg = NSAL1170Query.getInstance().getDsMdl(getGlobalCompanyCode(), cMsg.t_MdlId.getValue());
            if (dsMdlTMsg == null) {
                // No result
                cMsg.setMessageInfo(NZZM0000E);
                cMsg.xxPageShowFromNum.clear();
                cMsg.xxPageShowToNum.clear();
                cMsg.xxPageShowOfNum.clear();
                return;
            }
        }

//        NSAL1170CommonLogic.getSearchData(cMsg, sMsg);
        // mod end 2016/12/13 CSA QC#16369
        if (sMsg.A.getValidCount() == 0) {
            insertRow(cMsg, sMsg);
        }
        NSAL1170CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        NSAL1170CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);

        NSAL1170CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());

    }

    private void doProcess_NSAL1170Scrn00_PagePrev(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {

        if (!hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }
        NSAL1170CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);
        setValue(cMsg.xxPageShowFromNum, cMsg.xxPageShowFromNum.getValue().subtract(BigDecimal.valueOf(cMsg.A.length())));
        NSAL1170CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL1170CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    private void doProcess_NSAL1170Scrn00_PageNext(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {

        if (!hasValue(cMsg.xxPageShowFromNum)) {
            return;
        }
        NSAL1170CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        setValue(cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum.getValue().add(BigDecimal.ONE));
        NSAL1170CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL1170CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    private void doProcess_NSAL1170Scrn00_DeleteRow(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        if (!hasValue(cMsg.xxRadioBtn)) {
            cMsg.setMessageInfo(NFCM0094E);
            return;
        }
        NSAL1170CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
        NSAL1170CommonLogic.deleteRow(cMsg, sMsg);
        cMsg.setCommitSMsg(true);
        if (cMsg.xxPageShowFromNum.getValue().intValue() > sMsg.A.getValidCount()) {
            setValue(cMsg.xxPageShowFromNum, cMsg.xxPageShowFromNum.getValue().subtract(BigDecimal.valueOf(cMsg.A.length())));
        }

        NSAL1170CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL1170CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());
    }

    private void doProcess_NSAL1170Scrn00_InsertRow(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        NSAL1170CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);
        insertRow(cMsg, sMsg);
        NSAL1170CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        int pageSize = cMsg.A.getValidCount();
        if (pageSize > cMsg.A.length()) {
            pageSize = cMsg.A.length();
            cMsg.A.setValidCount(pageSize);
        }
        NSAL1170CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), pageSize, sMsg.A.getValidCount());
    }

    private void insertRow(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        NSAL1170CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);
        NSAL1170CommonLogic.insertRow(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add("xxRowNum_T", S21SortKey.ASC);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());
        NSAL1170CommonLogic.preSetToPageOne(cMsg.xxPageShowFromNum);
        int pageSize = cMsg.A.getValidCount();
        if (cMsg.A.length() < pageSize) {
            pageSize = cMsg.A.length();
        }

        NSAL1170CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), pageSize, sMsg.A.getValidCount());

    }

    private void doProcess_NSAL1170Scrn00_CMN_Clear(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        doProcess_NSAL1170_INIT(cMsg, sMsg);
    }

}
