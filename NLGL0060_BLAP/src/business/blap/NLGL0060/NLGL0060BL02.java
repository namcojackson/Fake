/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0060;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NLGL0060.common.NLGL0060CommonLogic;
import business.blap.NLGL0060.constant.NLGL0060Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/01/09   CITS            M.Furugoori         Create          N/A
 * </pre>
 */
public class NLGL0060BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String scrnAplId = cMsg.getScreenAplID();

            if ("NLGL0060_INIT".equals(scrnAplId)) {
                doProcess_Init((NLGL0060CMsg) cMsg, (NLGL0060SMsg) sMsg);
            } else if ("NLGL0060Scrn00_OnClick_Search".equals(scrnAplId)) {
                doProcess_OnClickSearch((NLGL0060CMsg) cMsg, (NLGL0060SMsg) sMsg);
            } else if ("NLGL0060Scrn00_PagePrev".equals(scrnAplId)) {
                doProcess_PagePrev((NLGL0060CMsg) cMsg, (NLGL0060SMsg) sMsg);
            } else if ("NLGL0060Scrn00_PageNext".equals(scrnAplId)) {
                doProcess_PageNext((NLGL0060CMsg) cMsg, (NLGL0060SMsg) sMsg);
            } else if ("NLGL0060Scrn00_CMN_Submit".equals(scrnAplId)) {
                doProcess_CMN_Submit((NLGL0060CMsg) cMsg, (NLGL0060SMsg) sMsg);
            } else if ("NLGL0060Scrn00_CMN_Clear".equals(scrnAplId)) {
                doProcess_CMN_Clear((NLGL0060CMsg) cMsg, (NLGL0060SMsg) sMsg);
                doProcess_Init((NLGL0060CMsg) cMsg, (NLGL0060SMsg) sMsg);
            } else {
                throw new S21AbendException("Illegal Screen Application Id. : " + scrnAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_Init
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     */
    private void doProcess_Init(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg) {

        // Initialize
        ZYPTableUtil.clear(cMsg.A);
        NLGL0060CommonLogic.setCommonValues(cMsg, sMsg, getGlobalCompanyCode());

        // PullDown
        NLGL0060CommonLogic.setPulldownTaskCode(cMsg);
        NLGL0060CommonLogic.setPulldownProcessStatus(cMsg);

    }

    /**
     * <p>
     * doProcess_OnClickSearch
     * </p>
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     */
    private void doProcess_OnClickSearch(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        S21SsmEZDResult result = NLGL0060Query.getInstance().search(cMsg, sMsg);

        if (result.isCodeNormal()) {

            // Max record Over
            int resultCount = result.getQueryResultCount();
            if (resultCount > sMsg.A.length()) {
                cMsg.setMessageInfo(NLGL0060Constant.NZZM0001W);
                resultCount = sMsg.A.length();
            }

            // Sets the search result to the table.
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                NLGL0060CommonLogic.setDateFormatter(cMsg, i);
            }
            cMsg.A.setValidCount(i);

            // set Pagenation
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(resultCount);

        } else {
            cMsg.setMessageInfo(NLGL0060Constant.NLGM0027W);
            ZYPTableUtil.clear(cMsg.A);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }

    }

    /**
     * <p>
     * doProcess_PagePrev
     * </p>
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     */
    private void doProcess_PagePrev(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg) {

        NLGL0060CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        cMsg.xxPageShowToNum.clear();

        doProcessPagination(cMsg, sMsg);

    }

    /**
     * <p>
     * doProcess_PageNext
     * </p>
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     */
    private void doProcess_PageNext(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg) {

        NLGL0060CommonLogic.saveCurrentPageToSMsg(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);
        cMsg.xxPageShowToNum.clear();

        doProcessPagination(cMsg, sMsg);
    }

    /**
     * <p>
     * Pagination process.<br>
     * Copies data from SMsg onto CMsg, and calculates the number of
     * showing from / to.
     * </p>
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     */
    private void doProcessPagination(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg) {
        // from index.
        int from = cMsg.xxPageShowFromNum.getValueInt();

        // copy data from SMsg onto CMsg
        int row = 0;
        for (; row < cMsg.A.length(); row++) {
            if (from + row > cMsg.xxPageShowOfNum.getValueInt()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(from + row - 1), null, cMsg.A.no(row), null);
            NLGL0060CommonLogic.setDateFormatter(cMsg, row);
        }
        cMsg.A.setValidCount(row);

        // set value to paging items.
        cMsg.xxPageShowFromNum.setValue(from);
        cMsg.xxPageShowToNum.setValue(from + row - 1);
    }

    /**
     * doProcess_NLGL0060Scrn00_CMN_Submit
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     */
    private void doProcess_CMN_Submit(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {

            EZDMessageInfo msgInfo = cMsg.getMessageInfo();
            doProcess_OnClickSearch(cMsg, sMsg);

            if (msgInfo != null) {
                cMsg.setMessageInfo(null);
                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLGL0060Scrn00_CMN_Clear
     * @param cMsg NLGL0060CMsg
     * @param sMsg NLGL0060SMsg
     */
    private void doProcess_CMN_Clear(NLGL0060CMsg cMsg, NLGL0060SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        cMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        sMsg.clear();

    }

}
