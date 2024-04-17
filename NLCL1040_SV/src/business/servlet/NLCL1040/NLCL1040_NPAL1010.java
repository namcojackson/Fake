/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1040;

import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_6;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_7;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_8;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_9;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL1040.common.NLCL1040CommonLogic;
import business.servlet.NLCL1040.constant.NLCL1040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040_NPAL1010
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1040_NPAL1010 extends S21CommonHandler {

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
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCdSrchTxt_RW, scrnMsg.P.no(POP_UP_PARAM_6).srchOptTxt_P.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt_RW, NLCL1040CommonLogic.toUpperCase(scrnMsg.P.no(POP_UP_PARAM_7).srchOptTxt_P.getValue()));

        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(POP_UP_PARAM_8).srchOptTxt_P)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCdSrchTxt_SW, scrnMsg.P.no(POP_UP_PARAM_8).srchOptTxt_P.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNmSrchTxt_SW, NLCL1040CommonLogic.toUpperCase(scrnMsg.P.no(POP_UP_PARAM_9).srchOptTxt_P.getValue()));
        }

        // Set Focus
        if (NLCL1040Constant.EVENT_NM_OPENWIN_WH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.rtlWhCdSrchTxt_RW);

        } else if (NLCL1040Constant.EVENT_NM_OPENWIN_SWH.equals(scrnMsg.xxMntEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.rtlSwhCdSrchTxt_SW);
        }


        NLCL1040CommonLogic.ctrlScrnItemDisplay(this, scrnMsg);

    }
}
