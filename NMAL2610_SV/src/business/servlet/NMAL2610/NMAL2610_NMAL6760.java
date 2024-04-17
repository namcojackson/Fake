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
public class NMAL2610_NMAL6760 extends S21CommonHandler {

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
        // Mod Start 2018/06/01 QC#24293
        //if (TRTY_RULE_TP.ACCOUNT_NAME.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
        //    getValue = scrnMsg.P.no(1).xxPopPrm_P.getValue();
        if (TRTY_RULE_TP.ACCOUNT_NUMBER.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
            getValue = scrnMsg.P.no(0).xxPopPrm_P.getValue();
            // Mod End 2018/06/01 QC#24293
        } else if (TRTY_RULE_TP.LOCATION_NUMBER.equals(scrnMsg.C.no(idx).trtyRuleTpCd_P1.getValue())) {
            getValue = scrnMsg.P.no(2).xxPopPrm_P.getValue();
        }

        if (NMAL2610Constant.POPUP_BTN_FLG_FROM.equals(scrnMsg.xxBtnFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).trtyRuleFromValTxt_C1, getValue);
            scrnMsg.setFocusItem(scrnMsg.C.no(idx).trtyRuleFromValTxt_C1);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(idx).trtyRuleThruValTxt_C1, getValue);
            scrnMsg.setFocusItem(scrnMsg.C.no(idx).trtyRuleThruValTxt_C1);
        }

        scrnMsg.xxCellIdx.clear();
        scrnMsg.xxScrEventNm.clear();
    }
}
