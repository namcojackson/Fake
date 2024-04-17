/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0160;

import static business.servlet.NSBL0160.constant.NSBL0160Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0160.NSBL0160CMsg;
import business.servlet.NSBL0160.common.NSBL0160CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/11   Hitachi         Y.Igarashi         Create          N/A
 * 2015/10/29   Hitachi         T.Tomita           Update          N/A
 * 2016/02/26   Hitachi         T.Tomita           Update          QC#3689
 * 2017/08/09   Hitachi         U.Kim              Update          QC#18151
 *</pre>
 */
public class NSBL0160Scrn00_Add_Cmnt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;
        // mod start 2016/02/26 CSA Defect#3689
//        scrnMsg.addCheckItem(scrnMsg.svcMemoTpCd_AC);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_SC);
        // mod end 2016/02/26 CSA Defect#3689
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;
        NSBL0160CMsg bizMsg = new NSBL0160CMsg();

        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2015/10/29 T.Tomita [N/A, MOD]
        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;
        NSBL0160CMsg bizMsg  = (NSBL0160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

//        NSBL0160CommonLogic.setBgColor(scrnMsg);
//        NSBL0160CommonLogic.setProtected(scrnMsg, this);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        if (scrnMsg.B.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.B.no(scrnMsg.B.getValidCount() - 1).svcCmntTxt_B);
        } else {
            // mod start 2016/02/26 CSA Defect#3689
            scrnMsg.setFocusItem(scrnMsg.svcMemoRsnCd_SC);
            // mod end 2016/02/26 CSA Defect#3689
        }
        // END 2015/10/29 T.Tomita [N/A, MOD]
        // START 2017/08/09 U.Kim [QC#18151, ADD]
        NSBL0160CommonLogic.setProtected(scrnMsg, this);
        // END 2017/08/09 U.Kim [QC#18151, ADD]
    }
}
