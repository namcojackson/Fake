/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Hitachi         T.Kanasaka      Create          QC#4806
 * 2019/01/28   Fujitsu         Nagshima        Update          QC#28632
 *</pre>
 */
public class NSAL0300Scrn00_ExpandEndCustomer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (NSAL0300Constant.IMG_OPEN_ARROW.equals(scrnMsg.xxFilePathTxt_EC.getValue())) {
            NSAL0300CommonLogic.addCheckItemForEndCustomer(scrnMsg);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;

        if (NSAL0300Constant.IMG_CLOSE_ARROW.equals(scrnMsg.xxFilePathTxt_EC.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_EC, NSAL0300Constant.IMG_OPEN_ARROW);
            scrnMsg.dsAcctNm_EC.clear();
            scrnMsg.mdseDescShortTxt_EC.clear();
            scrnMsg.setFocusItem(scrnMsg.dsAcctNum);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFilePathTxt_EC, NSAL0300Constant.IMG_CLOSE_ARROW);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_EC, scrnMsg.dsAcctNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt_EC, scrnMsg.mdseDescShortTxt_SP);
//            scrnMsg.setFocusItem(scrnMsg.dsContrNum);
        }
    }
}
