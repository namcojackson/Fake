/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2018/10/10   Hitachi         K.Kojima        Update          QC#28715
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_Item_Search_Additional_Charge_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do Nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2018/10/10 K.Kojima [QC#28715,MOD]
        // return null;
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID("NSAL1330");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        // END 2018/10/10 K.Kojima [QC#28715,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        // START 2018/10/10 K.Kojima [QC#28715,ADD]
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END 2018/10/10 K.Kojima [QC#28715,ADD]
        scrnMsg.delFlg_W.clear();

        scrnMsg.P.clear();

        int index = getButtonSelectNumber();

        Object[] params = NSAL1330CommonLogic.getAddlChrgItemPopUpPrm(scrnMsg, index);
        setArgForSubScreen(params);
    }

}
