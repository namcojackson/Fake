/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3150;

import static business.servlet.NLBL3150.constant.NLBL3150Constant.BIZ_ID;
import static business.servlet.NLBL3150.constant.NLBL3150Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3150.NLBL3150CMsg;
import business.servlet.NLBL3150.common.NLBL3150CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 *</pre>
 */
public class NLBL3150_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3150BMsg scrnMsg = (NLBL3150BMsg) bMsg;

        NLBL3150CMsg bizMsg = new NLBL3150CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3150BMsg scrnMsg = (NLBL3150BMsg) bMsg;
        NLBL3150CMsg bizMsg = (NLBL3150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.mdseCd);
        NLBL3150CommonLogic.initCommonButton(this);
        NLBL3150CommonLogic.setAuthority(this, scrnMsg);
        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.rtlSwhNm.setInputProtected(true);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL3150BMsg scrnMsg = (NLBL3150BMsg) bMsg;
        NLBL3150CommonLogic.setNameForMessage(scrnMsg);
    }
}
