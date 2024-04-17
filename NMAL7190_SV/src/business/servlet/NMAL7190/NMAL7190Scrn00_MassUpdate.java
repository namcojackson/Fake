/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190;

import java.util.List;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.XX_CHKBOX_NAME;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7190.NMAL7190CMsg;
//import business.servlet.NMAL7190.constant.NMAL7190Constant;

import business.blap.NMAL7190.NMAL7190SMsg;
import business.blap.NMAL7190.common.NMAL7190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/20   Hitachi         H.Watanabe      Create          QC#61200
 * 
 *</pre>
 */
public class NMAL7190Scrn00_MassUpdate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.effThruDt_MU)) {
            scrnMsg.effThruDt_MU.setErrorInfo(1, "ZZM9000E", new String[] {scrnMsg.effThruDt_MU.getNameForMessage()});
        }
        scrnMsg.addCheckItem(scrnMsg.effThruDt_MU);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;

        NMAL7190CMsg bizMsg = new NMAL7190CMsg();
        bizMsg.setBusinessID("NMAL7190");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7190BMsg scrnMsg = (NMAL7190BMsg) bMsg;
        NMAL7190CMsg bizMsg  = (NMAL7190CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}