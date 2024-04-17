/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1060.NLCL1060CMsg;
import business.servlet.NLCL1060.common.NLCL1060CommonLogic;
import business.servlet.NLCL1060.constant.NLCL1060Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;
        NLCL1060CMsg bizMsg = new NLCL1060CMsg();
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum_A1.setValue(scrnMsg.xxPageShowFromNum_A1.getValueInt() - scrnMsg.A.length() - 1);
        scrnMsg.xxPageShowToNum_A1.clear();
        bizMsg.setBusinessID(NLCL1060Constant.MY_BUSINESS_ID);
        bizMsg.setFunctionCode(NLCL1060Constant.FUNCTION_ID_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;
        NLCL1060CMsg bizMsg  = (NLCL1060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL1060CommonLogic.initButton(this, getUserProfileService(), scrnMsg);
        NLCL1060CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
