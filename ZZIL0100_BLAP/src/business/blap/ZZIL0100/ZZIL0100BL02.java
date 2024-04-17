/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0100;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZIL0100.common.ZZIL0100CommonLogic;
import business.blap.ZZIL0100.constant.ZZIL0100Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZIL0100BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZIL0100_INIT".equals(screenAplID)) {
                doProcess_ZZIL0100_INIT((ZZIL0100CMsg) cMsg, (ZZIL0100SMsg) sMsg);

            } else if ("ZZIL0100Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZIL0100Scrn00_Search((ZZIL0100CMsg) cMsg, (ZZIL0100SMsg) sMsg);

            } else if ("ZZIL0100Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZIL0100Scrn00_PageNext((ZZIL0100CMsg) cMsg, (ZZIL0100SMsg) sMsg);

            } else if ("ZZIL0100Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZIL0100Scrn00_PagePrev((ZZIL0100CMsg) cMsg, (ZZIL0100SMsg) sMsg);

            } else if ("ZZIL0100Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZIL0100Scrn00_TBLColumnSort((ZZIL0100CMsg) cMsg, (ZZIL0100SMsg) sMsg);

            } else if ("ZZIL0100Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZIL0100Scrn00_CMN_Clear((ZZIL0100CMsg) cMsg, (ZZIL0100SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * initialization processing
     * @param cMsg ZZIL0100CMsg
     * @param sMsg ZZIL0100SMsg
     */
    private void doProcess_ZZIL0100_INIT(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg) {
        ZZIL0100CommonLogic.setConfigListBox(cMsg, sMsg, getGlobalCompanyCode());
    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0100Scrn00_Search(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg) {
        
        // validate table name
        String tableId = ZZIL0100CommonLogic.getTrxTblId(cMsg, getGlobalCompanyCode());
        if(!ZZIL0100CommonLogic.validateTableName(tableId, cMsg)){
            return;
        }
        S21SsmEZDResult ssmResult = ZZIL0100Query.getInstance().getTransList(cMsg, sMsg, tableId);
        String msgCode = ZZIL0100CommonLogic.setSsmResultToList(ssmResult, cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum, cMsg.xxPageShowOfNum);
        if (msgCode != null) {
            cMsg.setMessageInfo(msgCode);
        }
    }

    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0100Scrn00_PageNext(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg) {
        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();

        // check change status flag
        if (chkChangeFlg(cMsg, sMsg, pagenationFrom - cMsg.A.length(), true)) {
            cMsg.setMessageInfo("ZZZM9019W");
            return;
        }
        ZZIL0100CommonLogic.pageMove(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum);
    }

    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0100Scrn00_PagePrev(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg) {
        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();

        // check change status flag
        if (chkChangeFlg(cMsg, sMsg, pagenationFrom + cMsg.A.length(), true)) {
            cMsg.setMessageInfo("ZZZM9019W");
            return;
        }
        ZZIL0100CommonLogic.pageMove(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum);
    }

    /**
     * table column sort Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0100Scrn00_TBLColumnSort(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg) {
        // Table:A
        String tblName = "A";
        String[] sortItems = ZZIL0100Constant.sortItems_A;
        String[][] baseContents = sMsg.A.no(0).getBaseContents();

        // check change status flag
        if (chkChangeFlg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), false)) {
            cMsg.setMessageInfo("ZZZM9019W");
            return;
        }
        
        ZZIL0100CommonLogic.tblColumnSort(
                cMsg.A, sMsg.A,
                cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum,
                cMsg.xxSortTblNm.getValue(), cMsg.xxSortItemNm.getValue(), cMsg.xxSortOrdByTxt.getValue(),
                baseContents, tblName, sortItems);
    }

    /**
     * clear Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0100Scrn00_CMN_Clear(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        doProcess_ZZIL0100_INIT(cMsg, sMsg);
    }

    /**
     * check change status flag
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param pagenationFrom Page Number
     * @param boolean revFlg Revision value Flag
     */
    private boolean chkChangeFlg(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg, int pagenationFrom, boolean revFlg) {

        boolean retFlg = false;
        Map<Integer, String> flgMap = new HashMap<Integer, String>();

        // out of check and warning flag of check
        if (sMsg.A.getValidCount() <= 1 || "W".equals(cMsg.xxRsltFlg.getValue())) {
            cMsg.xxRsltFlg.clear();
            return retFlg;
        }

        // check change status
        String procFlg = null;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            procFlg = cMsg.A.no(i).processedFlag_AS.getValue();
            if (!"".equals(procFlg)) {
                flgMap.put(i, procFlg);
            }
        }

        // setting data
        if (flgMap.size() > 0) {
            cMsg.A.clear();

            if (!revFlg) {
                pagenationFrom--;
            }

            int num = 0;
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
                if (i < sMsg.A.getValidCount()) {
                    num = i - pagenationFrom;

                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(num), null);
                    procFlg = flgMap.get(num);
                    if (procFlg != null) {
                        cMsg.A.no(num).processedFlag_AS.setValue(procFlg);
                    }
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());

            cMsg.xxSortTblNm.clear();
            cMsg.xxSortItemNm.clear();
            cMsg.xxSortOrdByTxt.clear();

            cMsg.xxRsltFlg.setValue("W");

            retFlg = true;
        }

        return retFlg;
    }
}
