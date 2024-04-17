/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.TXT_MKT_AUD_01;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;
import business.servlet.NMAL7100.constant.NMAL7100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2017/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7100Scrn00_OnChange_MktPrmoAudcCatg_01 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();

        scrnMsg.X.no(selectIdx).xxScrItem30Txt_X1.clear();
        scrnMsg.X.no(selectIdx).xxRecNmTxt_X1.clear();

        NMAL7100CommonLogic.mktAudcValTxtCtrl(this, TXT_MKT_AUD_01, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X1.getValue(), scrnMsg);
        // Mod Start 2017/02/27 QC#16011
//        NMAL7100CommonLogic.mktAudcCatgBtnCtrl(this, NMAL7100Constant.BTN_MKT_AUD_01, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X1.getValue(), scrnMsg);
        boolean updateAuthFlg = NMAL7100CommonLogic.updateAuthority(getUserProfileService());
        NMAL7100CommonLogic.mktAudcCatgBtnCtrl(this, NMAL7100Constant.BTN_MKT_AUD_01, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X1.getValue(), scrnMsg, updateAuthFlg);
        // Mod End 2017/02/27 QC#16011
    }
}
