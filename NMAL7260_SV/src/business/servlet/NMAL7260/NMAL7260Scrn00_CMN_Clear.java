/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;
import business.servlet.NMAL7260.common.NMAL7260CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          QC#5934
 * 2016/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 * 2017/09/13   Fujitsu         H.Nagashima     Update          QC#20968
 * 2018/04/10   Fujitsu         H.Nagashima     Update          QC#22593
 *</pre>
 */
public class NMAL7260Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H0, ZYPConstant.FLG_OFF_0); // QC#5934 2016/05/19 Add 
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H2, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxFlgNm_H1, ZYPConstant.FLG_OFF_0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxFlgNm_H2, ZYPConstant.FLG_OFF_0);

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


        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        // QC#22593 mod Start
//        NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
//        NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
        NMAL7260CommonLogic.setTableColor(scrnMsg);
        // QC#22593 mod End

        // Mod Start 2017/02/27 QC#16011
//        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg);
//        NMAL7260CommonLogic.scrnProtect(scrnMsg);
//        NMAL7260CommonLogic.setBtnProp(this, scrnMsg);
        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.scrnProtect(scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 QC#16011
        NMAL7260CommonLogic.initFilterParam(scrnMsg); // QC#20968 add
        scrnMsg.setFocusItem(scrnMsg.prcRuleHdrPk_H1);
    }
}
