/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6810;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6810.common.NMAL6810CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Item Master Template Popup
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/17   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NMAL6810BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6810_INIT".equals(screenAplID)) {
                doProcess_NMAL6810_INIT((NMAL6810CMsg) cMsg, (NMAL6810SMsg) sMsg);
            } else if ("NMAL6810Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL6810_CMN_Clear((NMAL6810CMsg) cMsg, (NMAL6810SMsg) sMsg);
            } else if ("NMAL6810Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6810Scrn00_PageNext((NMAL6810CMsg) cMsg, (NMAL6810SMsg) sMsg);
            } else if ("NMAL6810Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6810Scrn00_PagePrev((NMAL6810CMsg) cMsg, (NMAL6810SMsg) sMsg);
            } else if ("NMAL6810Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL6810Scrn00_Search((NMAL6810CMsg) cMsg, (NMAL6810SMsg) sMsg);
            } else if ("NMAL6810Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL6810Scrn00_TBLColumnSort((NMAL6810CMsg) cMsg, (NMAL6810SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL6810_INIT(NMAL6810CMsg cMsg, NMAL6810SMsg sMsg) {
        ZYPCodeDataUtil.createPulldownList("MDSE_ITEM_TP", cMsg.mdseItemTpCd_HL, cMsg.mdseItemTpDescTxt_HL);
        if (ZYPCommonFunc.hasValue(cMsg.mdseItemTpCd_HS)) {
            NMAL6810CommonLogic.search(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL6810_CMN_Clear(NMAL6810CMsg cMsg, NMAL6810SMsg sMsg) {
        cMsg.clear();
        sMsg.clear();
        ZYPCodeDataUtil.createPulldownList("MDSE_ITEM_TP", cMsg.mdseItemTpCd_HL, cMsg.mdseItemTpDescTxt_HL);
    }

    private void doProcess_NMAL6810Scrn00_PageNext(NMAL6810CMsg cMsg, NMAL6810SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NMAL6810CommonLogic.pageNation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NMAL6810Scrn00_PagePrev(NMAL6810CMsg cMsg, NMAL6810SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NMAL6810CommonLogic.pageNation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NMAL6810Scrn00_Search(NMAL6810CMsg cMsg, NMAL6810SMsg sMsg) {
        NMAL6810CommonLogic.search(cMsg, sMsg);
    }

    private void doProcess_NMAL6810Scrn00_TBLColumnSort(NMAL6810CMsg cMsg, NMAL6810SMsg sMsg) {
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdByTxt = cMsg.xxSortOrdByTxt.getValue();
        S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdByTxt);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());
        NMAL6810CommonLogic.pageNation(cMsg, sMsg, 0);
    }

}
