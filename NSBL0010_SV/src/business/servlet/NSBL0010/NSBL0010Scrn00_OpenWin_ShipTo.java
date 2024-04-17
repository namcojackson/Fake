/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0010.NSBL0010BMsg;
import business.servlet.NSBL0010.common.NSBL0010CommonLogic;
import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 *</pre>
 */
public class NSBL0010Scrn00_OpenWin_ShipTo extends S21CommonHandler implements NSBL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

// START 2016/10/19 N.Arai [QC#13901, MOD]
        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

//        scrnMsg.xxPopPrm_P1.clear();
//        scrnMsg.xxPopPrm_P2.clear();
//        scrnMsg.xxPopPrm_P3.clear();
//        scrnMsg.xxPopPrm_P4.clear();
//        scrnMsg.xxPopPrm_P5.clear();

//        Object[] params = new Object[PARAM_6];
//        params[PARAM_0] = scrnMsg.xxPopPrm_P1;
//        params[PARAM_1] = scrnMsg.xxPopPrm_P2;
//        params[PARAM_2] = scrnMsg.xxPopPrm_P3;
//        params[PARAM_3] = scrnMsg.xxPopPrm_P4;
//        params[PARAM_4] = scrnMsg.shipToCustCd;
//        params[PARAM_5] = scrnMsg.xxPopPrm_P5;

        NSBL0010CommonLogic.clearPopupParameter(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, OPENWIN_SHIPTO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PM, PARAMS_SHIP_TO_ONLY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PQ, scrnMsg.shipToCustCd);
        Object[] params = new Object[24];
        params[0] = scrnMsg.xxPopPrm_PA;
        params[1] = scrnMsg.xxPopPrm_PB;
        params[2] = scrnMsg.xxPopPrm_PC;
        params[3] = scrnMsg.xxPopPrm_PD;
        params[4] = scrnMsg.xxPopPrm_PE;
        params[5] = scrnMsg.xxPopPrm_PF;
        params[6] = scrnMsg.xxPopPrm_PG;
        params[7] = scrnMsg.xxPopPrm_PH;
        params[8] = scrnMsg.xxPopPrm_PI;
        params[9] = scrnMsg.xxPopPrm_PJ;
        params[10] = scrnMsg.xxPopPrm_PK;
        params[11] = scrnMsg.xxPopPrm_PL;
        params[12] = scrnMsg.xxPopPrm_PM;
        params[13] = scrnMsg.xxPopPrm_PN;
        params[14] = scrnMsg.xxPopPrm_PO;
        params[15] = scrnMsg.xxPopPrm_PP;
        params[16] = scrnMsg.xxPopPrm_PQ;
        params[17] = scrnMsg.xxPopPrm_PR;
        params[18] = scrnMsg.xxPopPrm_PS;
        params[19] = scrnMsg.xxPopPrm_PT;
        params[20] = scrnMsg.xxPopPrm_PU;
        params[21] = scrnMsg.xxPopPrm_PV;
        params[22] = scrnMsg.xxPopPrm_PW;
        params[23] = scrnMsg.xxPopPrm_PX;
        setArgForSubScreen(params);}
// END 2016/10/19 N.Arai [QC#13901, MOD]
}
