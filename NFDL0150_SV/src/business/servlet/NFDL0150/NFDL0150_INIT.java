/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0150;

import static business.servlet.NFDL0150.constant.NFDL0150Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0150.NFDL0150CMsg;
import business.servlet.NFDL0150.common.NFDL0150CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFDL0150_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0150BMsg scrnMsg = (NFDL0150BMsg) bMsg;

        NFDL0150CMsg bizMsg = new NFDL0150CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0150BMsg scrnMsg = (NFDL0150BMsg) bMsg;
        NFDL0150CMsg bizMsg = (NFDL0150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0150CommonLogic.setupScreenItems(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0150BMsg scrnMsg = (NFDL0150BMsg) bMsg;

        for (int num = 0; num < scrnMsg.A.length(); num++) {
            scrnMsg.A.no(num).cltStrgyCd_A.setNameForMessage("Code");
            scrnMsg.A.no(num).cltStrgyNm_A.setNameForMessage("Name");
        }

    }
}
