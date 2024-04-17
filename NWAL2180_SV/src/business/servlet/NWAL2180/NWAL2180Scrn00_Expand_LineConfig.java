/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2180.NWAL2180CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2180Scrn00_Expand_LineConfig
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/06   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2180Scrn00_Expand_LineConfig extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        scrnMsg.xxNum_Z.setValue(getButtonSelectNumber());
        ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(getButtonSelectNumber()).xxSmryLineFlg_U, ZYPConstant.FLG_OFF_N); //[+] -> [-]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;

        NWAL2180CMsg bizMsg = new NWAL2180CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        NWAL2180CMsg bizMsg = (NWAL2180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.U.no(getButtonSelectNumber()).prcListBandDescTxt_U);
    }
}