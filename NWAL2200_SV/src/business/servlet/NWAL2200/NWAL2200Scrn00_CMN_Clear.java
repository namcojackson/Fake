/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogic;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL2200Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        NWAL2200CommonLogic.setInitRequestData(scrnMsg, params);

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2200CommonLogicForScreenFields.setLabel(scrnMsg);
        NWAL2200CommonLogicForScreenFields.initCmnBtnProp(this);
        NWAL2200CommonLogicForScreenFields.setConditionForHeader(this, scrnMsg);
        NWAL2200CommonLogicForScreenFields.setConditionForHeaderTab(this, scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) && ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
   }
}
