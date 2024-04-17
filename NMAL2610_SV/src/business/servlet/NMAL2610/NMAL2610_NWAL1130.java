/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/03/02   Fujitsu         R.Nakamura      Create          QC#15878
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 *</pre>
 */
public class NMAL2610_NWAL1130 extends S21CommonHandler {

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
        String getValue = null;
        if (TRTY_RULE_TP.STATE.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
            getValue = scrnMsg.R.no(1).xxComnScrColValTxt.getValue();
        } else if (TRTY_RULE_TP.POSTAL_CODE.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
            getValue = scrnMsg.R.no(2).xxComnScrColValTxt.getValue();
            // Del Start 2018/06/01 QC#24293
        //} else if (TRTY_RULE_TP.COUNTY.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
        //    getValue = scrnMsg.R.no(3).xxComnScrColValTxt.getValue();
        //} else if (TRTY_RULE_TP.CITY.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
        //    getValue = scrnMsg.R.no(0).xxComnScrColValTxt.getValue();
            // Del End 2018/06/01 QC#24293
        }

        if (NMAL2610Constant.POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).trtyRuleFromValTxt_C1, getValue);
            scrnMsg.setFocusItem(scrnMsg.C.no(idx).trtyRuleFromValTxt_C1);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).trtyRuleThruValTxt_C1, getValue);
            scrnMsg.setFocusItem(scrnMsg.C.no(idx).trtyRuleThruValTxt_C1);
        }
    }
}
