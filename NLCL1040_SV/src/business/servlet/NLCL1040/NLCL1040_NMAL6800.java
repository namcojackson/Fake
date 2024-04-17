/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1040;


import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_0;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_7;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1040.common.NLCL1040CommonLogic;
import business.servlet.NLCL1040.constant.NLCL1040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040_NMAL6800
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1040_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1040BMsg scrnMsg = (NLCL1040BMsg) bMsg;

        if (NLCL1040Constant.EVENT_NM_OPEN_WIN_ITEM.equals(scrnMsg.xxMntEventNm.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.P.no(POP_UP_PARAM_0).srchOptTxt_P.getValue());

        } else if (NLCL1040Constant.EVENT_NM_OPEN_WIN_ITEM_DTL.equals(scrnMsg.xxMntEventNm.getValue())) {

            int selectNum = getButtonSelectNumber();

            if (selectNum >= 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNum).mdseCd_A, scrnMsg.P.no(POP_UP_PARAM_0).srchOptTxt_P.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(selectNum).mdseDescShortTxt_A, scrnMsg.P.no(POP_UP_PARAM_7).srchOptTxt_P.getValue());
            }
        }

        NLCL1040CommonLogic.ctrlScrnItemDisplay(this, scrnMsg);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.mdseCd);
    }
}
