/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1050;

import static business.servlet.NSAL1050.constant.NSAL1050Constant.NSAM0214E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   CUSA            T.Mizuki         Create          N/A
 *</pre>
 */
public class NSAL1050Scrn00_Addline extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;
        if (scrnMsg.A.length() == scrnMsg.A.getValidCount()) {
            scrnMsg.setMessageInfo(NSAM0214E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;

        int addData = scrnMsg.A.getValidCount();
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(addData).effFromDt_A, scrnMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(addData).xxChkBox_AA, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(addData).xxChkBox_AC, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(addData).xxChkBox_AM, ZYPConstant.FLG_ON_Y);
        scrnMsg.A.no(addData).svcTermCondSrcDescTxt_A.setInputProtected(true);
        scrnMsg.A.no(addData).xxChkBox_A.setInputProtected(false);
        this.setButtonEnabled("OpenWin_TandC_Source", addData, true);
        scrnMsg.A.setValidCount(addData + 1);
    }
}
