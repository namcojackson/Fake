/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0100;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZZOL0100.common.ZZOL0100CommonLogic;
import business.blap.ZZOL0100.constant.ZZOL0100Constant;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;


public class ZZOL0100BL02 extends S21BusinessHandler implements ZZOL0100Constant {


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZOL0100_INIT".equals(screenAplID)) {
                doProcess_ZZOL0100_INIT((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            } else if ("ZZOL0100Scrn00_CMN_Reset".equals(screenAplID)) {
                    doProcess_ZZOL0100Scrn00_CMN_Reset((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            } else if ("ZZOL0100_ZZSL1110".equals(screenAplID)) {
                doProcess_ZZOL0100_ZZSL1110((ZZOL0100CMsg) cMsg);
            } else if ("ZZOL0100Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZOL0100Scrn00_Search((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            } else if ("ZZOL0100Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZOL0100Scrn00_PageNext((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            } else if ("ZZOL0100Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZOL0100Scrn00_PagePrev((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            } else if ("ZZOL0100Scrn00_Preview".equals(screenAplID)) {
                doProcess_ZZOL0100Scrn00_Preview((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            } else if ("ZZOL0100Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZOL0100Scrn00_TBLColumnSort((ZZOL0100CMsg) cMsg, (ZZOL0100SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZOL0100_INIT(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        String sGlobalCpyCd = getGlobalCompanyCode();

        ZZOL0100CommonLogic.doClear(sGlobalCpyCd, cMsg, sMsg); 

        if (ZZOL0100CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == true) {
            // information
            ZZOL0100CommonLogic.getInformationList(sGlobalCpyCd, cMsg, sMsg);   
        }

    }
    private void doProcess_ZZOL0100Scrn00_CMN_Reset(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        String sGlobalCpyCd = getGlobalCompanyCode();    

        ZZOL0100CommonLogic.doClear(sGlobalCpyCd, cMsg, sMsg); 

        if (ZZOL0100CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == true) {
            // information
            ZZOL0100CommonLogic.getInformationList(sGlobalCpyCd, cMsg, sMsg);   
        }

    }

    private void doProcess_ZZOL0100_ZZSL1110(ZZOL0100CMsg cMsg) {

        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();

        if (ZZOL0100CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }

    }
    private void doProcess_ZZOL0100Scrn00_Search(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();

        ZZOL0100CommonLogic.doClear(sGlobalCpyCd, cMsg, sMsg); 
        
        if (ZZOL0100CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }

        ZZOL0100CommonLogic.getInformationList(sGlobalCpyCd, cMsg, sMsg);  

    }


    private void doProcess_ZZOL0100Scrn00_PageNext(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        // set values to items of pagenation for next page pagenation
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowToNum.getValueInt();
        int i              = pagenationFrom;
        for ( ; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }


    private void doProcess_ZZOL0100Scrn00_PagePrev(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        // set values to items of pagenation for prev page
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        int i              = pagenationFrom;
        for ( ; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }

    private void doProcess_ZZOL0100Scrn00_Preview(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        String sGlobalCpyCd = cMsg.glblCmpyCd_BK.getValue();
        String sYmd = cMsg.menuEffFromDt_P0.getValue();
        String sHms = cMsg.menuEffFromTm_P3.getValue();
        if (!sHms.equals("")) {
            sHms = sHms + "00";
        }

        cMsg.menuInfoTxt_P1.setValue(ZZOL0100CommonLogic.getInformationPreviewList(sGlobalCpyCd, sYmd, sHms));  

    }

    private void doProcess_ZZOL0100Scrn00_TBLColumnSort(ZZOL0100CMsg cMsg, ZZOL0100SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            if (cMsg.A.getValidCount() > 0) {

                // SMsg -> CMsg
                int i = 0;
                for (; i < sMsg.A.getValidCount(); i++) {
                    if (i == cMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

                cMsg.xxPageShowFromNum.setValue(1);
                cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            }
        }
    }
 
}
