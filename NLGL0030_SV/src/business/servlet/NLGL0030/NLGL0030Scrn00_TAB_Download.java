/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLGL0030.constant.NLGL0030Constant;

import business.blap.NLGL0030.NLGL0030CMsg;
import business.servlet.NLGL0030.common.NLGL0030CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * ForcedItem Master download
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            M.Shimamura     Create          MW Replace Initial
 *</pre>
 */
public class NLGL0030Scrn00_TAB_Download extends S21CommonHandler implements NLGL0030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // there is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0030BMsg scrnMsg = (NLGL0030BMsg) bMsg;
        NLGL0030CMsg bizMsg = NLGL0030CommonLogic.setRequestData_NLGL0030Scrn00_Function_20();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0030BMsg scrnMsg = (NLGL0030BMsg) bMsg;
        NLGL0030CMsg bizMsg = (NLGL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) && scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            return;
        }

        scrnMsg.B.setInputProtected(true);

        NLGL0030CommonLogic.commonBtnControl_NLGL0030Scrn00_CMN_DOWNLOAD(this);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, "xxDownload");
    }
}
