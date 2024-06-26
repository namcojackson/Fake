/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.ZZM9000E;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.ZZM9004E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/25   Fujitsu         R.Nakamura      Create          QC#3934
 * 2018/06/18   Fujitsu         M.Ishii         Update          QC#22594
 *</pre>
 */
public class NMAL7260Scrn00_OnBlur_Setting_PrcGrpCustNmSold extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        int idx = getButtonSelectNumber();
        // 2018/06/18 Mod Start QC#22594
//        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_56)) {
//            scrnMsg.addCheckItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_56);
//        } else {
//            if (!ZYPCommonFunc.isNumberCheck(scrnMsg.B.no(idx).prcRuleCondFromTxt_56.getValue())) {
//                scrnMsg.B.no(idx).prcRuleCondFromTxt_56.setErrorInfo(1, ZZM9004E, new String[] {scrnMsg.B.no(idx).prcRuleCondFromTxt_56.getNameForMessage() });
//                scrnMsg.addCheckItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_56);
        if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_53)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_53);
        } else {
            if (!ZYPCommonFunc.isNumberCheck(scrnMsg.B.no(idx).prcRuleCondFromTxt_53.getValue())) {
                scrnMsg.B.no(idx).prcRuleCondFromTxt_53.setErrorInfo(1, ZZM9004E, new String[] {scrnMsg.B.no(idx).prcRuleCondFromTxt_53.getNameForMessage() });
                scrnMsg.addCheckItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_53);
        // 2018/06/18 Mod End QC#22594
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        NMAL7260CMsg bizMsg = new NMAL7260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg = (NMAL7260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
        // 2018/06/18 Mod Start QC#22594
//            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_56);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_53);
        // 2018/06/18 Mod End QC#22594
        }

        scrnMsg.putErrorScreen();
        // 2018/06/18 Add Start QC#22594
        int idx = scrnMsg.xxCellIdx.getValueInt();
        scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_53);
        // 2018/06/18 Add End QC#22594
    }
}
