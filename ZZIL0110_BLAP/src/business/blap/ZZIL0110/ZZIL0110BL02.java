/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0110;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZIL0110.common.ZZIL0110CommonLogic;
import business.blap.ZZIL0110.constant.ZZIL0110Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZIL0110BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZIL0110_INIT".equals(screenAplID)) {
                doProcess_ZZIL0110_INIT((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn00_PageNext((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn00_PagePrev((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn00_TBLColumnSort((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn00_CMN_Clear((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn00_Search((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn00_Select".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn00_Select((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn01_PageNext".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn01_PageNext((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn01_PagePrev".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn01_PagePrev((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn01_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn01_TBLColumnSort((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn01_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn01_CMN_Clear((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            } else if ("ZZIL0110Scrn01_CMN_Return".equals(screenAplID)) {
                doProcess_ZZIL0110Scrn01_CMN_Return((ZZIL0110CMsg) cMsg, (ZZIL0110SMsg) sMsg);

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * initialization processing
     * @param cMsg ZZIL0110CMsg
     * @param sMsg ZZIL0110SMsg
     */
    private void doProcess_ZZIL0110_INIT(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        ZZIL0110CommonLogic.setConfigListBox(cMsg.itrlIntfcTrxConfigId_PC, cMsg.itrlIntfcTrxConfigNm_PC);
    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0110Scrn00_Search(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        String glblCmpyCd = super.getGlobalCompanyCode();
        
        String mstrTable = ZZIL0110CommonLogic.getMstrTbl(cMsg, sMsg, glblCmpyCd);
        
        if(!ZZIL0110CommonLogic.validateTableName(mstrTable, cMsg)){
            return;
        }
        S21SsmEZDResult ssmResult = ZZIL0110Query.getInstance().getItrlIntfcIds(cMsg, sMsg, mstrTable);
        String msgCode = ZZIL0110CommonLogic.setSsmResultToList(ssmResult, cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum, cMsg.xxPageShowOfNum);
        if (msgCode != null) {
            cMsg.setMessageInfo(msgCode);
        }
    }

    /**
     * select processing
     * @param cMsg ZZIL0110CMsg
     * @param sMsg ZZIL0110SMsg
     */
    private void doProcess_ZZIL0110Scrn00_Select(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        ZZIL0110CommonLogic.setIntfctblidToMsg(cMsg, sMsg, getGlobalCompanyCode());
    }

    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0110Scrn00_PageNext(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        ZZIL0110CommonLogic.pageMove(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum);
    }

    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0110Scrn00_PagePrev(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        ZZIL0110CommonLogic.pageMove(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum);
    }

    /**
     * table column sort Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0110Scrn00_TBLColumnSort(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        // Table:A
        String tblName = "A";
        String[] sortItems = ZZIL0110Constant.sortItems_A;
        String[][] baseContents = sMsg.A.no(0).getBaseContents();

        ZZIL0110CommonLogic.tblColumnSort(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum, cMsg.xxSortTblNm.getValue(), cMsg.xxSortItemNm.getValue(), cMsg.xxSortOrdByTxt.getValue(), baseContents, tblName, sortItems);
    }

    /**
     * clear Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0110Scrn00_CMN_Clear(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        doProcess_ZZIL0110_INIT(cMsg, sMsg);
    }

    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0110Scrn01_PageNext(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        ZZIL0110CommonLogic.pageMove(cMsg.B, sMsg.B, cMsg.xxPageShowFromNum_B, cMsg.xxPageShowToNum_B);
    }

    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0110Scrn01_PagePrev(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        ZZIL0110CommonLogic.pageMove(cMsg.B, sMsg.B, cMsg.xxPageShowFromNum_B, cMsg.xxPageShowToNum_B);
    }

    /**
     * table column sort Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0110Scrn01_TBLColumnSort(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        // Table:B
        String tblName = "B";
        String[] sortItems = ZZIL0110Constant.sortItems_B;
        String[][] baseContents = sMsg.B.no(0).getBaseContents();

        ZZIL0110CommonLogic.tblColumnSort(cMsg.B, sMsg.B, cMsg.xxPageShowFromNum_B, cMsg.xxPageShowToNum_B, cMsg.xxSortTblNm_B.getValue(), cMsg.xxSortItemNm_B.getValue(), cMsg.xxSortOrdByTxt_B.getValue(), baseContents, tblName, sortItems);
    }

    /**
     * clear Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0110Scrn01_CMN_Clear(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        doProcess_ZZIL0110Scrn00_Select(cMsg, sMsg);
    }

    /**
     * return processing
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZIL0110Scrn01_CMN_Return(ZZIL0110CMsg cMsg, ZZIL0110SMsg sMsg) {
  //      doProcess_ZZIL0110Scrn00_Search(cMsg, sMsg);
    }
}
