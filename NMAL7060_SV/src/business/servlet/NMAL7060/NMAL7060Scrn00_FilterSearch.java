/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.BIZ_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NMAM0192E;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.ZZM9037E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7060.NMAL7060CMsg;
//import business.servlet.NMAL7060.constant.NMAL7060Constant;

import business.blap.NMAL7060.NMAL7060CMsg;
import business.servlet.NMAL7060.common.NMAL7060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/10   Fujitsu         R.Nakamura      Create          QC#17529
 *</pre>
 */
public class NMAL7060Scrn00_FilterSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgNm)) {
            scrnMsg.prcMtrPkgNm.setErrorInfo(1, NMAM0192E);
            scrnMsg.setMessageInfo(ZZM9037E);
        }

        scrnMsg.addCheckItem(scrnMsg.prcMtrPkgNm);
        scrnMsg.addCheckItem(scrnMsg.mdlNm_F1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        ZYPTableUtil.clear(scrnMsg.C);
        scrnMsg.prcMtrPkgPk.clear();
        scrnMsg.prcMtrPkgDescTxt.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        scrnMsg.effThruDt.clear();
        scrnMsg.ezInTime.clear();
        scrnMsg.ezInTimeZone.clear();
        scrnMsg.ezUpTime.clear();
        scrnMsg.ezUpTimeZone.clear();
        scrnMsg.xxRadioBtn.clear();

        NMAL7060CMsg bizMsg = new NMAL7060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg  = (NMAL7060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        NMAL7060CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);

        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(1).mdlNm_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.mdlNm_F1);
        }

    }
}
