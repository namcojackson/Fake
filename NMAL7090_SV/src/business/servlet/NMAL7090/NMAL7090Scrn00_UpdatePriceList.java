/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7090;

import static business.servlet.NMAL7090.constant.NMAL7090Constant.BIZ_ID;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_LBL_PROCESSED;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_UPDATE_PRC_LIST;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_UPDATE_PRICE_LIST;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.FUNCTION_CD_SEARCH;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7090.NMAL7090CMsg;
import business.servlet.NMAL7090.common.NMAL7090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID  : NMAL7090 Item Supersessions Mass Add
 * Function Name: Update Price List
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NMAL7090Scrn00_UpdatePriceList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;
        NMAL7090CommonLogic.addCheckTableB(scrnMsg, false);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_SL, BigDecimal.valueOf(getButtonSelectNumber()));

        NMAL7090CMsg bizMsg = new NMAL7090CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;
        NMAL7090CMsg bizMsg = (NMAL7090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // set screen property
        NMAL7090CommonLogic.setScrnProp(this, scrnMsg, EVENT_UPDATE_PRICE_LIST);

        // disable Update Price List Button
        this.setButtonProperties(BTN_UPDATE_PRC_LIST, getButtonSelectNumber(), null, BTN_LBL_PROCESSED, 0, null);

        NMAL7090CommonLogic.setScrnProp(this, scrnMsg, EVENT_UPDATE_PRICE_LIST);

        // set focus
        if (scrnMsg.B.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.B.getValidCount() - 1).oldMdseCd_B);
        }
    }
}
