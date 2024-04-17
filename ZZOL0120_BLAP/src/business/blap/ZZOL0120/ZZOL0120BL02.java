/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/08/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0120;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.ZZOL0120.common.ZZOL0120CommonLogic;
import business.blap.ZZOL0120.constant.ZZOL0120Constant;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;


public class ZZOL0120BL02 extends S21BusinessHandler implements ZZOL0120Constant {


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZOL0120_INIT".equals(screenAplID)) {
                doProcess_ZZOL0120_INIT((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn00_CMN_Reset".equals(screenAplID)) {
                    doProcess_ZZOL0120Scrn00_CMN_Reset((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120_ZZSL1110".equals(screenAplID)) {
                doProcess_ZZOL0120_ZZSL1110((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn00_Search((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn00_TBLColumnSort((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn00_Detail".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn00_Detail((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn01_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn01_TBLColumnSort((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn01_Detail".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn01_Detail((ZZOL0120CMsg) cMsg);
            } else if ("ZZOL0120Scrn02_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZOL0120Scrn02_TBLColumnSort((ZZOL0120CMsg) cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZOL0120_INIT(ZZOL0120CMsg cMsg) {

        String sGlobalCpyCd = getGlobalCompanyCode();
        ZZOL0120CommonLogic.doClear00(sGlobalCpyCd, cMsg);
        ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg);
        ZZOL0120CommonLogic.getProcGroupList(sGlobalCpyCd, cMsg);

    }
    private void doProcess_ZZOL0120Scrn00_CMN_Reset(ZZOL0120CMsg cMsg) {

        String sGlobalCpyCd = getGlobalCompanyCode();
        ZZOL0120CommonLogic.doClear00(sGlobalCpyCd, cMsg);
        ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg);
        ZZOL0120CommonLogic.getProcGroupList(sGlobalCpyCd, cMsg);

    }

    private void doProcess_ZZOL0120_ZZSL1110(ZZOL0120CMsg cMsg) {

        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();

        if (ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }

    }

    private void doProcess_ZZOL0120Scrn00_Search(ZZOL0120CMsg cMsg) {

        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();

        ZZOL0120CommonLogic.doClear00(sGlobalCpyCd, cMsg);

        if (ZZOL0120CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }

        ZZOL0120CommonLogic.getProcGroupList(sGlobalCpyCd, cMsg);

    }


    private void doProcess_ZZOL0120Scrn00_TBLColumnSort(ZZOL0120CMsg cMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(cMsg.A, cMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, cMsg.A.getValidCount());
        }
    }

    private void doProcess_ZZOL0120Scrn01_TBLColumnSort(ZZOL0120CMsg cMsg) {

        String sortTblNm = cMsg.xxSortTblNm_B1.getValue();
        String sortItemNm = cMsg.xxSortItemNm_B1.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_B1.getValue();

        // Table:B
        if ("B".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(cMsg.B, cMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, cMsg.B.getValidCount());
        }
    }

    private void doProcess_ZZOL0120Scrn00_Detail(ZZOL0120CMsg cMsg) {

        cMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_BK.getValue());

        String glblCmpyCd = cMsg.glblCmpyCd_BK.getValue();
        String menuProcGrpCd = cMsg.menuProcGrpCd_B1.getValue();
        ZZOL0120CommonLogic.getProcList(glblCmpyCd, menuProcGrpCd, cMsg);

    }

    private void doProcess_ZZOL0120Scrn02_TBLColumnSort(ZZOL0120CMsg cMsg) {

        String sortTblNm = cMsg.xxSortTblNm_C1.getValue();
        String sortItemNm = cMsg.xxSortItemNm_C1.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_C1.getValue();

        // Table:C
        if ("C".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(cMsg.C, cMsg.C.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, cMsg.C.getValidCount());
        }
    }

    private void doProcess_ZZOL0120Scrn01_Detail(ZZOL0120CMsg cMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd_BK.getValue();
        String menuProcId = cMsg.menuProcId_C1.getValue();
        ZZOL0120CommonLogic.getUpTabList(glblCmpyCd, menuProcId, cMsg);

    }
}
