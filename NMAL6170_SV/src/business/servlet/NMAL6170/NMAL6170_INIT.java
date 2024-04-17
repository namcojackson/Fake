/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6170;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6170.NMAL6170CMsg;
import business.servlet.NMAL6170.common.NMAL6170CommonLogic;
import business.servlet.NMAL6170.constant.NMAL6170Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/05   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NMAL6170_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6170BMsg scrnMsg = (NMAL6170BMsg) bMsg;
        Object[] arg = (Object[]) getArgForSubScreen();
        NMAL6170CommonLogic.setInputParam(scrnMsg, arg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6170BMsg scrnMsg = (NMAL6170BMsg) bMsg;

        NMAL6170CMsg bizMsg = new NMAL6170CMsg();
        bizMsg.setBusinessID(NMAL6170Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6170BMsg scrnMsg = (NMAL6170BMsg) bMsg;
        NMAL6170CMsg bizMsg = (NMAL6170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6170CommonLogic.initCmnBtnProp(this);
        NMAL6170CommonLogic.setScrnCtrl(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6170BMsg scrnMsg = (NMAL6170BMsg) bMsg;

        scrnMsg.dsAcctRelnTpCd_H.setNameForMessage("Relationship Type");
        scrnMsg.dsAcctNum_F.setNameForMessage("Related Account#");
        scrnMsg.dsAcctNm_F.setNameForMessage("Related Account Name(*)");
        scrnMsg.xxChkBox_FB.setNameForMessage("Bill To");
        scrnMsg.xxChkBox_FS.setNameForMessage("Ship To");
        scrnMsg.xxChkBox_FR.setNameForMessage("Reciprocal");
        scrnMsg.effFromDt_F1.setNameForMessage("Start Date From");
        scrnMsg.effFromDt_F2.setNameForMessage("Start Date To");
        scrnMsg.effThruDt_F1.setNameForMessage("End Date From");
        scrnMsg.effThruDt_F2.setNameForMessage("End Date To");
    }
}
