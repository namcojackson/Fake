/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL2610.NMAL2610CMsg;
//import business.servlet.NMAL2610.constant.NMAL2610Constant;

import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/03/02   Fujitsu         R.Nakamura      Create          QC#15878
 *</pre>
 */
public class NMAL2610_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (NMAL2610Constant.POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.C.no(idx).trtyRuleFromValTxt_C1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.C.no(idx).trtyRuleThruValTxt_C1);
        }

        scrnMsg.xxCellIdx.clear();
        scrnMsg.xxScrEventNm.clear();
    }
}
