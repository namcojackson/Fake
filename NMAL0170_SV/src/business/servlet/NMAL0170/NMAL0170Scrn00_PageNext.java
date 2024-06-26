/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0170.NMAL0170CMsg;
import business.servlet.NMAL0170.common.NMAL0170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL0170 Supersession Staging Info Inquiry
 * Function Name : Button Action to Next Page
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/22/2016   CITS            S.Tanikawa      Create          QC#2669
 * 2017/02/09   Fujitsu         K.Ishizuka      Update          QC#17513
 *</pre>
 */
public class NMAL0170Scrn00_PageNext extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;

        //ZYPTableUtil.clear(scrnMsg.A); // DEL S21_NA #17513
        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
        scrnMsg.xxPageShowToNum.clear();

        NMAL0170CMsg bizMsg = new NMAL0170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.A);
        NMAL0170CMsg bizMsg = (NMAL0170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0170CommonLogic.setScrnLineProtected(scrnMsg);
    }
}
