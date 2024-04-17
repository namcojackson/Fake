/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAL1210_POP_PARAM_1;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Open Return to Approve History Popup(NPAL1210)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1080Scrn00_OpenWin_ApproveHistory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        scrnMsg.prchReqNum_P4.clear();
        scrnMsg.apvlHistSrcTpCd_P4.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_P4, scrnMsg.prchReqNum_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.apvlHistSrcTpCd_P4, NPAL1210_POP_PARAM_1);

        Object[] params = new Object[2];
        params[0] = scrnMsg.apvlHistSrcTpCd_P4;
        params[1] = scrnMsg.prchReqNum_P4;

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
