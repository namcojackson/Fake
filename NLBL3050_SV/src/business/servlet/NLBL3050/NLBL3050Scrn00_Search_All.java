/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3050.common.NLBL3050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/21/2016   CSAI            Y.Imazu         Create          QC#2048
 *</pre>
 */
public class NLBL3050Scrn00_Search_All extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3050BMsg scrnMsg = (NLBL3050BMsg) bMsg;

        scrnMsg.xxListNum_A1.clear();
        scrnMsg.xxListNum_H1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_A, scrnMsg.xxTotCnt_L);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_OA, scrnMsg.xxTotCnt_OL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_IA, scrnMsg.xxTotCnt_IL);

        scrnMsg.A.clear();

        int i = 0;

        for (; i < scrnMsg.L.getValidCount(); i++) {

            if (i == scrnMsg.A.length()) {

                break;
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).trxHdrNumTpCd_A1, scrnMsg.L.no(i).trxHdrNumTpCd_L1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).trxHdrNum_A1, scrnMsg.L.no(i).trxHdrNum_L1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxOrdTs_A1, scrnMsg.L.no(i).xxOrdTs_L1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).delyCoordStsDescTxt_A1, scrnMsg.L.no(i).delyCoordStsDescTxt_L1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToAcctNm_A1, scrnMsg.L.no(i).shipToAcctNm_L1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToAddr_A1, scrnMsg.L.no(i).shipToAddr_L1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToCtyAddr_A1, scrnMsg.L.no(i).shipToCtyAddr_L1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToStCd_A1, scrnMsg.L.no(i).shipToStCd_L1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxTotCnt_AO, scrnMsg.L.no(i).xxTotCnt_LO);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxTotCnt_AI, scrnMsg.L.no(i).xxTotCnt_LI);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxTblSortColNm_AS, scrnMsg.L.no(i).xxTblSortColNm_LS);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).cutOffAot_AS, scrnMsg.L.no(i).cutOffAot_LS);
        }

        scrnMsg.A.setValidCount(i);

        NLBL3050CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cpoNum_H);
    }
}
