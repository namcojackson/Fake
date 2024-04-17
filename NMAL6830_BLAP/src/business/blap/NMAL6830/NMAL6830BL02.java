package business.blap.NMAL6830;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6830.common.NMAL6830CommonLogic;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/** 
 *<pre>
 * Cost Update Group Search Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   SRAA            K.Aratani       Create          N/A
 *</pre>
 */
public class NMAL6830BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6830_INIT".equals(screenAplID)) {
                doProcess_NMAL6830_INIT((NMAL6830CMsg) cMsg, (NMAL6830SMsg) sMsg);
            } else if ("NMAL6830Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL6830_CMN_Clear((NMAL6830CMsg) cMsg, (NMAL6830SMsg) sMsg);
            } else if ("NMAL6830Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6830Scrn00_PageNext((NMAL6830CMsg) cMsg, (NMAL6830SMsg) sMsg);
            } else if ("NMAL6830Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6830Scrn00_PagePrev((NMAL6830CMsg) cMsg, (NMAL6830SMsg) sMsg);
            } else if ("NMAL6830Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL6830Scrn00_Search((NMAL6830CMsg) cMsg, (NMAL6830SMsg) sMsg);
            } else if ("NMAL6830Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL6830Scrn00_TBLColumnSort((NMAL6830CMsg) cMsg, (NMAL6830SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL6830_INIT(NMAL6830CMsg cMsg, NMAL6830SMsg sMsg) {

    	//Cost Type
    	//cMsg.mdseCstUpdTpCd_H1.clear();
    	cMsg.mdseCstUpdTpCd_HL.clear();
    	cMsg.mdseCstUpdTpNm_HL.clear();
        NMAL6830CommonLogic.setInitialCostTypePulldown(cMsg, getGlobalCompanyCode());
        NMAL6830CommonLogic.search(cMsg, sMsg);
    }

    private void doProcess_NMAL6830_CMN_Clear(NMAL6830CMsg cMsg, NMAL6830SMsg sMsg) {
        cMsg.clear();
        sMsg.clear();
    	//Cost Type
    	cMsg.mdseCstUpdTpCd_H1.clear();
    	cMsg.mdseCstUpdTpCd_HL.clear();
    	cMsg.mdseCstUpdTpNm_HL.clear();
        NMAL6830CommonLogic.setInitialCostTypePulldown(cMsg, getGlobalCompanyCode());
    }

    private void doProcess_NMAL6830Scrn00_PageNext(NMAL6830CMsg cMsg, NMAL6830SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NMAL6830CommonLogic.pageNation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NMAL6830Scrn00_PagePrev(NMAL6830CMsg cMsg, NMAL6830SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NMAL6830CommonLogic.pageNation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NMAL6830Scrn00_Search(NMAL6830CMsg cMsg, NMAL6830SMsg sMsg) {
        NMAL6830CommonLogic.search(cMsg, sMsg);
    }

    private void doProcess_NMAL6830Scrn00_TBLColumnSort(NMAL6830CMsg cMsg, NMAL6830SMsg sMsg) {
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdByTxt = cMsg.xxSortOrdByTxt.getValue();
        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdByTxt);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());
        NMAL6830CommonLogic.pageNation(cMsg, sMsg, 0);
    }

}
