/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0160;

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
 * 2015/10/29   Hitachi         T.Tomita        Create          N/A
 * 2016/02/19   Hitachi         K.Kasai         Update          QC#3689
 * 2017/08/09   Hitachi         U.Kim           Update          QC#18151
 *</pre>
 */
public class NSBL0160Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // add start 2016/02/19 CSA Defect#3689
        NSBL0160BMsg scrnMsg = (NSBL0160BMsg)bMsg;
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_SC);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt_SC);
        scrnMsg.putErrorScreen();
        // add end 2016/02/19 CSA Defect#3689
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;

        NSBL0160CMsg bizMsg = new NSBL0160CMsg();
        bizMsg.setBusinessID("NSBL0160");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0160BMsg scrnMsg = (NSBL0160BMsg) bMsg;
        NSBL0160CMsg bizMsg  = (NSBL0160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        if (scrnMsg.B.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.B.no(0).svcCmntTxt_B);
        }
        // START 2017/08/09 U.Kim [QC#18151, ADD]
        NSBL0160CommonLogic.setProtected(scrnMsg, this);
        // END 2017/08/09 U.Kim [QC#18151, ADD]
    }
}
