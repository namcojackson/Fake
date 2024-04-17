/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;
import business.servlet.NMAL7010.constant.NMAL7010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_OnChange_PrcCustAudcCatg_02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2017/02/23   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7010Scrn00_OnChange_PrcCustAudcCatg_02 extends S21CommonHandler {

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

        scrnMsg.X.no(selectIdx).xxScrItem30Txt_X2.clear();
        scrnMsg.X.no(selectIdx).xxRecNmTxt_X2.clear();

        // Mod Start 2017/02/23 QC#16011
//        NMAL7010CommonLogic.prcCustAudcCatgCtrl(this, NMAL7010Constant.BTN_PRC_CUST_AUDC_02, selectIdx, scrnMsg.X.no(selectIdx).prcCustAudcCatgCd_X2.getValue(), scrnMsg);
        boolean updateAuthFlg = NMAL7010CommonLogic.updateAuthority(getUserProfileService());
        NMAL7010CommonLogic.prcCustAudcCatgCtrl(this, NMAL7010Constant.BTN_PRC_CUST_AUDC_02, selectIdx, scrnMsg.X.no(selectIdx).prcCustAudcCatgCd_X2.getValue(), scrnMsg, updateAuthFlg);
        // Mod End 2017/02/23 QC#16011

        scrnMsg.setFocusItem(scrnMsg.X.no(selectIdx).xxScrItem30Txt_X2);
    }
}
