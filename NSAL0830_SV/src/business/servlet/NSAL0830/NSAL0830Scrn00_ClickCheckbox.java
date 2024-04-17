/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0830;

import static business.servlet.NSAL0830.common.NSAL0830CommonLogic.initialControlScreen;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0830.NSAL0830CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#6411
 *</pre>
 */
public class NSAL0830Scrn00_ClickCheckbox extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // MOD start 2016/04/07 CSA Defect#6411
//        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
//        int index = getButtonSelectNumber();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum_H, scrnMsg.A.no(index).xxRowNum_A);
//
//        NSAL0830CMsg bizMsg = new NSAL0830CMsg();
//        bizMsg.setBusinessID(BUSINESS_ID);
//        bizMsg.setFunctionCode(FUNCTION_SEARCH);
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
        // MOD end 2016/04/07 CSA Defect#6411
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // MOD start 2016/04/07 CSA Defect#6411
//        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
//        NSAL0830CMsg bizMsg = (NSAL0830CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        initialControlScreen(this, scrnMsg);
//        scrnMsg.xxRowNum_H.clear();
        // MOD end 2016/04/07 CSA Defect#6411
    }
}
