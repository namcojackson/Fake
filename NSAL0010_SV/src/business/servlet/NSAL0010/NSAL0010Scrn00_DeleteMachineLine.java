/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;
import business.servlet.NSAL0010.constant.NSAL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2016/03/30   Hitachi         T.Tomita        Update          QC#6092
 *</pre>
 */
public class NSAL0010Scrn00_DeleteMachineLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_A.getValue())) {
            scrnMsg.setMessageInfo("NFCM0094E");
        }
        int deleteRow = scrnMsg.xxRadioBtn_A.getValueInt();
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(deleteRow).svcConfigMstrPk_A.getValue())) {
            scrnMsg.setMessageInfo("NATM0031E");
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
        bizMsg.setBusinessID(NSAL0010Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg  = (NSAL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // add start 2016/03/30 CSA Defect#6092
        NSAL0010CommonLogic.controlScreenFields(this, scrnMsg, bizMsg.getUserID(), true, getUserProfileService());
        scrnMsg.setFocusItem(scrnMsg.serNum_H1);
        // add end 2016/03/30 CSA Defect#6092
    }
}
