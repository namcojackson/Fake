/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BTN_PRC_TRX_AUDC_02;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_OnChange_PrcTrxAudcCatg_02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/10   Fujitsu         M.Nakamura      Create          N/A
 * 2017/02/24   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7010Scrn00_OnChange_PrcTrxAudcCatg_02 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        scrnMsg.Y.no(selectIdx).xxScrItem30Txt_Y2.clear();
        scrnMsg.Y.no(selectIdx).xxRecNmTxt_Y2.clear();

        // Mod Start 2017/02/24 QC#16011
        boolean updateAuthFlg = NMAL7010CommonLogic.updateAuthority(getUserProfileService());
//        NMAL7010CommonLogic.prcTrxAudcCatgCtrl(this, BTN_PRC_TRX_AUDC_02, selectIdx, scrnMsg.Y.no(selectIdx).prcTrxAudcCatgCd_Y2.getValue(), scrnMsg);
        NMAL7010CommonLogic.prcTrxAudcCatgCtrl(this, BTN_PRC_TRX_AUDC_02, selectIdx, scrnMsg.Y.no(selectIdx).prcTrxAudcCatgCd_Y2.getValue(), scrnMsg, updateAuthFlg);
        // Mod End 2017/02/24 QC#16011

        scrnMsg.setFocusItem(scrnMsg.Y.no(selectIdx).xxScrItem30Txt_Y2);
    }
}
