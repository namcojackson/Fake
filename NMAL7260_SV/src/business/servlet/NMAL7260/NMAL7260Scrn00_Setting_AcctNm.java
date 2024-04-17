/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_Setting_AcctNm
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7260Scrn00_Setting_AcctNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
//
//        int idx = getButtonSelectNumber();
//        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_B3)) {
//            scrnMsg.B.no(idx).prcRuleCondFromTxt_B3.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.B.no(idx).prcRuleCondFromTxt_B3.getNameForMessage()});
//            scrnMsg.addCheckItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_B3);
//        }
//
//        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
//
//        int idx = getButtonSelectNumber();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));
//
//        NMAL7260CMsg bizMsg = new NMAL7260CMsg();
//        bizMsg.setBusinessID(BIZ_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
//        NMAL7260CMsg bizMsg  = (NMAL7260CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
//            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_B3);
//        }
//
//        scrnMsg.putErrorScreen();
    }
}
