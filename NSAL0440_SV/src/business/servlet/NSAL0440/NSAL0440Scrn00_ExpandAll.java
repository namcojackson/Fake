/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0440;

import static business.servlet.NSAL0440.common.NSAL0440CommonLogic.initialControlScreen;
import static business.servlet.NSAL0440.constant.NSAL0440Constant.BUSINESS_ID;
import static business.servlet.NSAL0440.constant.NSAL0440Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL0440.constant.NSAL0440Constant.IMG_OPEN_ALL;
import static business.servlet.NSAL0440.constant.NSAL0440Constant.IMG_CLOSE_ALL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
                                                  // business.blap.NSAL0440.NSAL0440CMsg;
// import business.servlet.NSAL0440.constant.NSAL0440Constant;

import business.blap.NSAL0440.NSAL0440CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/08/03   Hitachi         N.Takatsu         Create          QC#60077
 *</pre>
 */
public class NSAL0440Scrn00_ExpandAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;

        // String openCloseFlg = ZYPConstant.FLG_ON_Y;
        // if (IMG_OPEN_ALL.equals(scrnMsg.xxFilePathTxt.getValue()))
        // {
        // openCloseFlg = ZYPConstant.FLG_ON_Y;
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt,
        // IMG_CLOSE_ALL);
        // } else {
        // openCloseFlg = ZYPConstant.FLG_OFF_N;
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt,
        // IMG_OPEN_ALL);
        // }
        // for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        // ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxBtnFlg_A,
        // openCloseFlg);
        // }

        NSAL0440CMsg bizMsg = new NSAL0440CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0440BMsg scrnMsg = (NSAL0440BMsg) bMsg;
        NSAL0440CMsg bizMsg = (NSAL0440CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);

    }
}
