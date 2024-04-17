/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSBL0040.common.NSBL0040CommonLogic;
import business.servlet.NSBL0040.constant.NSBL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/01   SRA             Otsuji          Create          N/A
 * 2017/06/14   Hitachi         Y.Osawa         Update          QC#18970
 * 2019/10/02   Hitachi         K.Kitachi       Update          QC#53692
 *</pre>
 */
public class NSBL0040Scrn00_OpenWin_SellTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2017/06/14 Y.Osawa [QC#18970, MOD]
//        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;
//
//        // Open NMAL6010
//        Object[] param = new Object[6];
//
//        int i = 0;
//        param[i++] = scrnMsg.billToCustCd;
//        param[i++] = scrnMsg.locNm_01;
//        param[i++] = scrnMsg.sellToCustCd;
//        param[i++] = scrnMsg.locNm_02;
//        param[i++] = scrnMsg.shipToCustCd;
//        param[i++] = scrnMsg.locNm_03;

        NSBL0040BMsg scrnMsg = (NSBL0040BMsg) bMsg;
        NSBL0040CommonLogic.clearPopupDataForScrnMsg(scrnMsg);
        // START 2019/10/02 K.Kitachi [QC#53692, ADD]
        NSBL0040CommonLogic.setOpenParamLocNm(scrnMsg.locNm_02, scrnMsg.xxPopPrm_01);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, NSBL0040Constant.OPENWIN_SELLTO);
        // END 2019/10/02 K.Kitachi [QC#53692, ADD]

        // Open NMAL6760
        Object[] param = new Object[17];

        int i = 0;

        param[i++] = scrnMsg.sellToCustCd;
        // START 2019/10/02 K.Kitachi [QC#53692, MOD]
//        param[i++] = scrnMsg.locNm_02;
        param[i++] = scrnMsg.xxPopPrm_01;
        // END 2019/10/02 K.Kitachi [QC#53692, MOD]
        param[i++] = scrnMsg.xxPopPrm_02;
        param[i++] = scrnMsg.xxPopPrm_03;
        param[i++] = scrnMsg.xxPopPrm_04;
        param[i++] = scrnMsg.xxPopPrm_05;
        param[i++] = scrnMsg.xxPopPrm_06;
        param[i++] = scrnMsg.xxPopPrm_07;
        param[i++] = scrnMsg.xxPopPrm_08;
        param[i++] = scrnMsg.xxPopPrm_09;
        param[i++] = scrnMsg.xxPopPrm_10;
        param[i++] = scrnMsg.xxPopPrm_11;
        param[i++] = scrnMsg.xxPopPrm_12;
        param[i++] = scrnMsg.xxPopPrm_13;
        param[i++] = scrnMsg.xxPopPrm_14;
        param[i++] = scrnMsg.xxPopPrm_15;
        param[i++] = scrnMsg.xxPopPrm_16;
        // END   2017/06/14 Y.Osawa [QC#18970, MOD]

        setArgForSubScreen(param);
    }
}
