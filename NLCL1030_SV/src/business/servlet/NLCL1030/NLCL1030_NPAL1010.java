/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1030;

import static business.servlet.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_OPEN_WIN_SWH;
import static business.servlet.NLCL1030.constant.NLCL1030Constant.EVENT_NM_NLCL1030SCRN00_OPEN_WIN_WH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLCL1030.common.NLCL1030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLCL1030 Inventory ABC Analysis Search
 * Function Name : NLCL1030_NPAL1010
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   CITS            T.Hakodate      Create          N/A
 * 11/21/2018   Fujitsu         T.Ogura         Update          QC#29123
 *</pre>
 */
public class NLCL1030_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1030BMsg scrnMsg = (NLCL1030BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCdSrchTxt_RW, scrnMsg.rtlWhCdSrchTxt_PR);
        // START 2018/11/21 T.Ogura [QC#29123,MOD]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt_RW, NLCL1030CommonLogic.toUpperCase(scrnMsg.rtlWhCdSrchTxt_PR.getValue()));
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt_RW, NLCL1030CommonLogic.toUpperCase(scrnMsg.rtlWhNmSrchTxt_PR.getValue()));
        // END   2018/11/21 T.Ogura [QC#29123,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCdSrchTxt_SW, scrnMsg.rtlWhCdSrchTxt_PS);

        if (EVENT_NM_NLCL1030SCRN00_OPEN_WIN_WH.equals(scrnMsg.xxPopPrm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.rtlWhCdSrchTxt_RW);

        } else if (EVENT_NM_NLCL1030SCRN00_OPEN_WIN_SWH.equals(scrnMsg.xxPopPrm.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.rtlSwhCdSrchTxt_SW);
        }
    }
}
