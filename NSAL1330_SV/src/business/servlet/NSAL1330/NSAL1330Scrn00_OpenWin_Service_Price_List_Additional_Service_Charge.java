/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.LINK_SVC_PRC_LIST;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0655E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_AD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_Service_Price_List_Additional_Service_Charge extends S21CommonHandler {

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

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.delFlg_W.clear();

        if (scrnMsg.prcCatgNm_C.isInputProtected()) {
            scrnMsg.setMessageInfo(NSAM0655E, new String[] {LINK_SVC_PRC_LIST, LINK_SVC_PRC_LIST });
            throw new EZDFieldErrorException();
        }
        ZYPTableUtil.clear(scrnMsg.P);

        //        // set the transition screen name
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POP_UP_SVC_PRC_AD);
        //
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, PRC_CTX_TP.SPECIAL_CHARGE);
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm_P, scrnMsg.dsOrdCatgCd);
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm_P, scrnMsg.dsOrdTpCd);
        //        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm_P, scrnMsg.sellToCustCd);
        //
        //        Object[] param = new Object[13];
        //        param[0] = scrnMsg.prcBaseDt;
        //        param[1] = scrnMsg.P.no(1).xxPopPrm_P;
        //        param[2] = scrnMsg.P.no(2).xxPopPrm_P;
        //        param[3] = scrnMsg.P.no(3).xxPopPrm_P;
        //        param[4] = scrnMsg.P.no(4).xxPopPrm_P;
        //        param[5] = scrnMsg.P.no(5).xxPopPrm_P;
        //        param[6] = scrnMsg.P.no(6).xxPopPrm_P;
        //        param[7] = scrnMsg.P.no(7).xxPopPrm_P;
        //        param[8] = scrnMsg.P.no(8).xxPopPrm_P;
        //        param[9] = scrnMsg.P.no(9).xxPopPrm_P;
        //        param[10] = scrnMsg.P.no(10).xxPopPrm_P;
        //        param[11] = scrnMsg.P.no(11).xxPopPrm_P;
        //        param[12] = scrnMsg.P.no(12).xxPopPrm_P;

        Object[] params = NSAL1330CommonLogic.getNWAL1760Prm(scrnMsg, POP_UP_SVC_PRC_AD, PRC_CTX_TP.SPECIAL_CHARGE, null);
        setArgForSubScreen(params);

    }
}
