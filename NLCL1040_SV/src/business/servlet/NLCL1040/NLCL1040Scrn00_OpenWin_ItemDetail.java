/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1040;

import static business.servlet.NLCL1040.constant.NLCL1040Constant.EVENT_NM_OPEN_WIN_ITEM_DTL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1040.common.NLCL1040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040Scrn00_OpenWin_ItemDetail
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1040Scrn00_OpenWin_ItemDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        int selectNum = getButtonSelectNumber();

        NLCL1040BMsg scrnMsg = (NLCL1040BMsg) bMsg;
        Object[] params = NLCL1040CommonLogic.setParamForNMAL6800ForDetail(scrnMsg, selectNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, EVENT_NM_OPEN_WIN_ITEM_DTL);

        setArgForSubScreen(params);
    }
}
