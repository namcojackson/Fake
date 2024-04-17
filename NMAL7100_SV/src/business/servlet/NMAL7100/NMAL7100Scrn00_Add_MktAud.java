/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.BIZ_ID;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_MKT_AUD_01;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_MKT_AUD_02;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_MKT_AUD_03;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.TXT_MKT_AUD_01;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7100.NMAL7100CMsg;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 * 2017/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7100Scrn00_Add_MktAud extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        NMAL7100CMsg bizMsg = new NMAL7100CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        NMAL7100CMsg bizMsg  = (NMAL7100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectIdx = bizMsg.X.getValidCount() - 1;

        NMAL7100CommonLogic.mktAudcValTxtCtrl(this, TXT_MKT_AUD_01, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X1.getValue(), scrnMsg);

        //  Mod Start 2017/02/27 QC#16011
//        NMAL7100CommonLogic.mktAudcCatgBtnCtrl(this, BTN_MKT_AUD_01, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X1.getValue(), scrnMsg);
//        NMAL7100CommonLogic.mktAudcCatgBtnCtrl(this, BTN_MKT_AUD_02, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X2.getValue(), scrnMsg);
//        NMAL7100CommonLogic.mktAudcCatgBtnCtrl(this, BTN_MKT_AUD_03, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X3.getValue(), scrnMsg);
        boolean updateAuthFlg = NMAL7100CommonLogic.updateAuthority(getUserProfileService());
        NMAL7100CommonLogic.mktAudcCatgBtnCtrl(this, BTN_MKT_AUD_01, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X1.getValue(), scrnMsg, updateAuthFlg);
        NMAL7100CommonLogic.mktAudcCatgBtnCtrl(this, BTN_MKT_AUD_02, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X2.getValue(), scrnMsg, updateAuthFlg);
        NMAL7100CommonLogic.mktAudcCatgBtnCtrl(this, BTN_MKT_AUD_03, selectIdx, scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X3.getValue(), scrnMsg, updateAuthFlg);
        // Mod End 2017/02/27 QC#16011

        scrnMsg.setFocusItem(scrnMsg.X.no(selectIdx).mktPrmoAudcCatgCd_X1);

    }
}
