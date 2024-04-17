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
public class NLBL3050Scrn00_Search_Today extends S21CommonHandler {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_A, scrnMsg.xxTotCnt_T);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_OA, scrnMsg.xxTotCnt_OT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotCnt_IA, scrnMsg.xxTotCnt_IT);

        scrnMsg.A.clear();

        int i = 0;

        for (; i < scrnMsg.T.getValidCount(); i++) {

            if (i == scrnMsg.A.length()) {

                break;
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).trxHdrNumTpCd_A1, scrnMsg.T.no(i).trxHdrNumTpCd_T1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).trxHdrNum_A1, scrnMsg.T.no(i).trxHdrNum_T1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxOrdTs_A1, scrnMsg.T.no(i).xxOrdTs_T1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).delyCoordStsDescTxt_A1, scrnMsg.T.no(i).delyCoordStsDescTxt_T1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToAcctNm_A1, scrnMsg.T.no(i).shipToAcctNm_T1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToAddr_A1, scrnMsg.T.no(i).shipToAddr_T1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToCtyAddr_A1, scrnMsg.T.no(i).shipToCtyAddr_T1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).shipToStCd_A1, scrnMsg.T.no(i).shipToStCd_T1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxTotCnt_AO, scrnMsg.T.no(i).xxTotCnt_TO);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxTotCnt_AI, scrnMsg.T.no(i).xxTotCnt_TI);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxTblSortColNm_AS, scrnMsg.T.no(i).xxTblSortColNm_TS);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).cutOffAot_AS, scrnMsg.T.no(i).cutOffAot_TS);
        }

        scrnMsg.A.setValidCount(i);

        NLBL3050CommonLogic.initialControlScreen(this, scrnMsg);
        NLBL3050CommonLogic.searchTodayButton(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cpoNum_H);
    }
}
