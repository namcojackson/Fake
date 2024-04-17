/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.initCommonButton;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.initControlFields;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.setDelyIstlDispFlg;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.setNameForMessageDeryDisp;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.setTabProtect;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BUSINESS_ID;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TAB_INSTALL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1510.NWAL1510CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   CUSA            S.Ohki          Create          N/A
 *</pre>
 */
public class NWAL1510Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        scrnMsg.clear();

        NWAL1510CMsg bizMsg = new NWAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        initControlFields(this, scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab.setValue(TAB_INSTALL);

        setDelyIstlDispFlg(scrnMsg);
        setNameForMessageDeryDisp(scrnMsg);
        initCommonButton(this);
        setTabProtect(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H0);

    }
}
