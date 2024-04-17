/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0070.NLEL0070CMsg;
import business.servlet.NLEL0070.common.NLEL0070CommonLogic;
import business.servlet.NLEL0070.constant.NLEL0070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   Hitachi         T.Tsuchida      Update          QC#9637
 *</pre>
 */
public class NLEL0070Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;

        NLEL0070CommonLogic.addCheckItems(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;

        NLEL0070CMsg bizMsg = new NLEL0070CMsg();
        bizMsg.setBusinessID(NLEL0070Constant.BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;
        NLEL0070CMsg bizMsg = (NLEL0070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLEL0070CommonLogic.addCheckItems(scrnMsg);
        
        // has error
        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }

        // Protected.
        NLEL0070CommonLogic.protectedInitEv(scrnMsg, this);

        // Focus.
        scrnMsg.setFocusItem(scrnMsg.assetTpCd_H1);

        // normal end Message.
        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NLEL0070Constant.NZZM0002I);
        }
    }
}
