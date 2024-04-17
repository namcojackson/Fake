/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.NMAM0836E;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;
import business.servlet.NMAL7260.common.NMAL7260CommonLogic;
import business.servlet.NMAL7260.constant.NMAL7260Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 * 2017/09/13   Fujitsu         H.Nagashima     Update          QC#20968
 * 2018/04/10   Fujitsu         H.Nagashima     Update          QC#22593
 *</pre>
 */
public class NMAL7260Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcRuleHdrPk_H1)) {
            scrnMsg.prcRuleHdrPk_H1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.prcRuleHdrPk_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.prcRuleHdrPk_H1);
            NMAL7260CommonLogic.setTableColor(scrnMsg);     //QC#22593 add
        }

        scrnMsg.addCheckItem(scrnMsg.prcRuleHdrPk_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

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
        NMAL7260CMsg bizMsg  = (NMAL7260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.scrnProtect(scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());
        // QC#22593 mod Start
//        NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
//        NMAL7260CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
        NMAL7260CommonLogic.setTableColor(scrnMsg);
        // QC#22593 mod End
        NMAL7260CommonLogic.initFilterParam(scrnMsg); // QC#20968 add
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        scrnMsg.addCheckItem(scrnMsg.prcRulePrcdGrpNm_H1);
        scrnMsg.putErrorScreen();
    }
}
