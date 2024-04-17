/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL0070.NLAL0070CMsg;
import business.servlet.NLAL0070.constant.NLAL0070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/03/2013   Hitachi         T.Tomita        Create          QC1209
 *</pre>
 */
public class NLAL0070Scrn00_OpenSubWin_Inventory_Location extends S21CommonHandler implements NLAL0070Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;
        NLAL0070CMsg bizMsg = new NLAL0070CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLAL0070BMsg scrnMsg = (NLAL0070BMsg) bMsg;
        NLAL0070CMsg bizMsg = (NLAL0070CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.P.clear();
        scrnMsg.P.no(0).xxPopPrm_H1.setValue(scrnMsg.invtyLocCd_P1.getValue());
        scrnMsg.P.no(1).xxPopPrm_H1.clear();
        scrnMsg.P.no(2).xxPopPrm_H1.clear();
        scrnMsg.P.no(3).xxPopPrm_H1.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.P.no(4).xxPopPrm_H1.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.P.no(5).xxPopPrm_H1.clear();

        Object[] param = new Object[6];
        param[0] = scrnMsg.P.no(0).xxPopPrm_H1;
        param[1] = scrnMsg.P.no(1).xxPopPrm_H1;
        param[2] = scrnMsg.xxLocRoleTpCdListTxt;
        param[3] = scrnMsg.P.no(3).xxPopPrm_H1;
        param[4] = scrnMsg.P.no(4).xxPopPrm_H1;
        param[5] = scrnMsg.P.no(5).xxPopPrm_H1;

        setArgForSubScreen(param);
    }
}
