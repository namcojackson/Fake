/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;
import business.servlet.NMAL7010.constant.NMAL7010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_Add_TrxAudc
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2017/02/24   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7010Scrn00_Add_TrxAudc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg  = (NMAL7010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectIdx = bizMsg.Y.getValidCount() - 1;
        // Mod Start 2017/02/24 QC#16011
        boolean updateAuthFlg = NMAL7010CommonLogic.updateAuthority(getUserProfileService());
//        NMAL7010CommonLogic.prcTrxAudcCatgCtrl(this, NMAL7010Constant.BTN_PRC_TRX_AUDC_01, selectIdx, scrnMsg.Y.no(selectIdx).prcTrxAudcCatgCd_Y1.getValue(), scrnMsg);
//        NMAL7010CommonLogic.prcTrxAudcCatgCtrl(this, NMAL7010Constant.BTN_PRC_TRX_AUDC_02, selectIdx, scrnMsg.Y.no(selectIdx).prcTrxAudcCatgCd_Y2.getValue(), scrnMsg);
        NMAL7010CommonLogic.prcTrxAudcCatgCtrl(this, NMAL7010Constant.BTN_PRC_TRX_AUDC_01, selectIdx, scrnMsg.Y.no(selectIdx).prcTrxAudcCatgCd_Y1.getValue(), scrnMsg, updateAuthFlg);
        NMAL7010CommonLogic.prcTrxAudcCatgCtrl(this, NMAL7010Constant.BTN_PRC_TRX_AUDC_02, selectIdx, scrnMsg.Y.no(selectIdx).prcTrxAudcCatgCd_Y2.getValue(), scrnMsg, updateAuthFlg);
        // Mod End 2017/02/24 QC#16011

        scrnMsg.setFocusItem(scrnMsg.Y.no(selectIdx).prcTrxAudcCatgCd_Y1);
    }
}
