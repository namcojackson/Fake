/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.NMAM8340E;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.TAB_REGD_ACCT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_TAB_RegdAcct
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/09   Fujitsu         W.Honda         Update          CSA-QC#3263
 * 2016/02/22   Fujitsu         W.Honda         Update          CSA-QC#3636
 *</pre>
 */
public class NMAL7130Scrn00_TAB_RegdAcct extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Add Start 2016/02/09 CSA-QC#3263
        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        // START 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]

        NMAL7130CommonLogic.addCheckScreenItem(scrnMsg);

        scrnMsg.putErrorScreen();
        // END 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcContrPk_BK)) {
            scrnMsg.prcContrPk_H1.setErrorInfo(1, NMAM8340E);
            scrnMsg.setMessageInfo(NMAM8340E);
            scrnMsg.addCheckItem(scrnMsg.prcContrPk_H1);
            scrnMsg.putErrorScreen();
        }
        // Add End 2016/02/09 CSA-QC#3263

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_REGD_ACCT);

        NMAL7130CommonLogic.setCmnBtnProp(this, BTN_CMN_DWL, 1);
        NMAL7130CommonLogic.setRowsBGWithClear(scrnMsg);
   }
}
