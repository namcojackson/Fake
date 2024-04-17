/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0240;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import business.servlet.NSBL0240.common.NSBL0240CommonLogic;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/15   Hitachi         Y.Takeno        Create          N/A
 * 2018/01/30   Hitachi         M.Kidokoro      Update          QC#23252
 *</pre>
 */
public class NSBL0240Scrn00_OpenWin_IntgItemPopup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0240BMsg scrnMsg = (NSBL0240BMsg) bMsg;
        setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
        // START 2018/01/30 M.Kidokoro [QC#23252,MOD]
//        setArgForSubScreen(NSBL0240CommonLogic.createItemSearchPopupParameter(scrnMsg, null, null, MDSE_ITEM_TP.INTANGIBLE, MDSE_ITEM_BILL_TP.LABOR));
        setArgForSubScreen(NSBL0240CommonLogic.createItemSearchPopupParameter(scrnMsg, null, null, MDSE_ITEM_TP.INTANGIBLE, MDSE_ITEM_BILL_TP.TRAVEL));
        // END 2018/01/30 M.Kidokoro [QC#23252,MOD]
    }
}
