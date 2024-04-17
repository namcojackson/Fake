/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1310;

import static business.servlet.NSAL1310.constant.NSAL1310Constant.BUSINESS_ID;
import static business.servlet.NSAL1310.constant.NSAL1310Constant.EZD_FUNC_CD_INQ;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1310.NSAL1310CMsg;
import business.servlet.NSAL1310.common.NSAL1310CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Default Coverage T&C Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/20   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSAL1310Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;

        NSAL1310CMsg bizMsg = new NSAL1310CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;
        NSAL1310CMsg bizMsg = (NSAL1310CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1310CommonLogic.setupScreenItemsInit(this, scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H, ZYPConstant.CHKBOX_ON_Y);

        scrnMsg.setFocusItem(scrnMsg.svcCovTmplTpCd_SV);

    }
}
