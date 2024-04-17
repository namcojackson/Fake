/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0860.common;

import static business.blap.NSAL0860.constant.NSAL0860Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_COND;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0860.NSAL0860CMsg;
import business.blap.NSAL0860.NSAL0860Query;
import business.blap.NSAL0860.NSAL0860SMsg;
import business.file.NSAL0860F00FMsg;

/**
 *<pre>
 * Register & Deregister Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/06   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL0860CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL0860CMsg
     * @param sMsg NSAL0860SMsg
     */
    public static void clearMsg(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL0860CMsg
     */
    public static void createPullDown(NSAL0860CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(IWR_COND.class, cMsg.iwrCondCd_L, cMsg.iwrCondDescTxt_L);
    }

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NSAL0860CMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isErrorSearchCondition(NSAL0860CMsg cMsg) {
        if (hasValueItem(cMsg.serNum)) {
            return false;
        }
        if (hasValueItem(cMsg.dsAcctNm)) {
            return false;
        }
        if (hasValueItem(cMsg.iwrCondCd)) {
            return false;
        }
        if (hasValueItem(cMsg.mdlNm)) {
            return false;
        }
        if (hasValueItem(cMsg.curLocAcctNum)) {
            return false;
        }
        if (hasValueItem(cMsg.dsContrNum)) {
            return false;
        }

        return true;
    }

    /**
     * Set MessageInfo SearchCondition
     * @param cMsg NSAL0860CMsg
     */
    public static void setMessageInfoSearchCondition(NSAL0860CMsg cMsg) {

        cMsg.serNum.setErrorInfo(1, NSAM0005E);
        cMsg.mdlNm.setErrorInfo(1, NSAM0005E);
        cMsg.dsAcctNm.setErrorInfo(1, NSAM0005E);
        cMsg.curLocAcctNum.setErrorInfo(1, NSAM0005E);
        cMsg.iwrCondCd.setErrorInfo(1, NSAM0005E);
        cMsg.dsContrNum.setErrorInfo(1, NSAM0005E);

        cMsg.setMessageInfo(NSAM0005E);
    }

    private static boolean hasValueItem(EZDCStringItem item) {
        if (!ZYPCommonFunc.hasValue(item)) {
            return false;
        }
        return true;
    }

    /**
     * Get Search Data
     * @param cMsg NSAL0860CMsg
     * @param sMsg NSAL0860SMsg
     */
    public static void getSearchData(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL0860Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZOM0011E);
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL0860CMsg
     * @param sMsg NSAL0860SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL0860CMsg cMsg, NSAL0860SMsg sMsg, int pageFrom) {

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
    }

    /**
     * Write csv file header
     * @param csvOutFile ZYPCSVOutFile
     * @param fMsg NSAL0860F00FMsg
     * @param cMsg NSAL0860CMsg
     * @throws SQLException
     */
    public static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0860F00FMsg fMsg, NSAL0860CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {labelConv.convLabel2i18nLabel(SCREEN_ID, SCREEN_TITLE + "\r\n" + MEMO + "\r\n" + "Model Name"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Serial Number"),
                labelConv.convLabel2i18nLabel(SCREEN_ID, "Action"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Status"), labelConv.convLabel2i18nLabel(SCREEN_ID, "Message") };

        csvOutFile.writeHeader(csvHeader, fMsg, getFMsgColOrder(new int[FMSG_COL_LENGTH]));
    }

    /**
     * @param fMsgColOrder int[]
     * @return int[]
     */
    public static int[] getFMsgColOrder(int[] fMsgColOrder) {
        for (int i = 0; i < fMsgColOrder.length; i++) {
            fMsgColOrder[i] = i;
        }
        return fMsgColOrder;
    }

    /**
     * Write csv file
     * @param csvOutFile ZYPCSVOutFile
     * @param fMsg NSAL0860F00FMsg
     * @param sMsg NSAL0860CMsg
     * @throws SQLException
     */
    public static void writeCsvFile(ZYPCSVOutFile csvOutFile, NSAL0860F00FMsg fMsg, NSAL0860SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(i), null, fMsg, null);
            csvOutFile.write();
        }

        csvOutFile.close();
    }
}
