/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740;

import static business.servlet.NWAL1740.constant.NWAL1740Constant.BIZ_ID;
import static business.servlet.NWAL1740.constant.NWAL1740Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1740.NWAL1740CMsg;
import business.servlet.NWAL1740.common.NWAL1740CommonLogic;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1740Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1740Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.ordProcNodePrflCd);
        NWAL1740CommonLogic.checkMdseTpCode(scrnMsg);
        NWAL1740CommonLogic.checkLineCatgNm(scrnMsg);
        NWAL1740CommonLogic.checkWH(scrnMsg);
        NWAL1740CommonLogic.addCheckItemMdseTpRules(scrnMsg);
        NWAL1740CommonLogic.addCheckItemLineCatgRules(scrnMsg);
        NWAL1740CommonLogic.addCheckItemWHRules(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        NWAL1740CMsg bizMsg = new NWAL1740CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1740BMsg scrnMsg = (NWAL1740BMsg) bMsg;
        NWAL1740CMsg bizMsg = (NWAL1740CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1740CommonLogic.setOpenBtnProtected(scrnMsg, this);
        NWAL1740CommonLogic.addCheckItemMdseTpRules(scrnMsg);
        NWAL1740CommonLogic.addCheckItemLineCatgRules(scrnMsg);
        NWAL1740CommonLogic.addCheckItemWHRules(scrnMsg);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
        scrnMsg.setFocusItem(scrnMsg.ordProcNodePrflCd);
    }
}
