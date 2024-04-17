/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7220;

import static business.servlet.NMAL7220.constant.NMAL7220Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7220.common.NMAL7220CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/**
 *<pre>
 * NMAL7220Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7220Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7220BMsg scrnMsg = (NMAL7220BMsg) bMsg;

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        scrnMsg.prcFmlaPk_H1.clear();
        scrnMsg.prcFmlaPk_BK.clear();
        scrnMsg.prcFmlaNm_H1.clear();
        scrnMsg.prcFmlaDescTxt_H1.clear();
        scrnMsg.effFromDt_H1.clear();
        scrnMsg.effThruDt_H1.clear();
        scrnMsg.actvFlg_H1.clear();
        scrnMsg.A.no(0).prcFmlaTpCd_A1.clear();
        scrnMsg.A.no(0).prcCatgCd_A1.clear();
        scrnMsg.A.no(0).prcCatgNm_A1.clear();
        scrnMsg.A.no(0).prcFmlaOpCd_A1.clear();
        scrnMsg.A.no(0).prcFmlaNum_A1.clear();
        scrnMsg.A.no(0).prcFuncTpCd_A1.clear();

        boolean authorityFlg = NMAL7220CommonLogic.createAuthorityFlg(scrnMsg.xxYesNoCd.getValue());

        NMAL7220CommonLogic.initCmnBtnProp(this, authorityFlg);
        NMAL7220CommonLogic.scrnProtect(scrnMsg, true);
        NMAL7220CommonLogic.setBtnProp(this, scrnMsg, authorityFlg);

        scrnMsg.setFocusItem(scrnMsg.prcFmlaPk_H1);
    }
}
