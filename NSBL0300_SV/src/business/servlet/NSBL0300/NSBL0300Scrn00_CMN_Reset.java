/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0300;

import static business.servlet.NSBL0300.constant.NSBL0300Constant.BIZ_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0300.NSBL0300CMsg;
import business.servlet.NSBL0300.common.NSBL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Skill Level Maintenance
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Hitachi         K.Ochiai        Create          QC#16331
 *</pre>
 */
public class NSBL0300Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;

        NSBL0300CMsg bizMsg = new NSBL0300CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0300BMsg scrnMsg = (NSBL0300BMsg) bMsg;
        NSBL0300CMsg bizMsg = (NSBL0300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.procFlg, ZYPConstant.FLG_ON_Y);
        EZDMsg.copy(scrnMsg.A, "A", scrnMsg.D, "D");
        scrnMsg.D.setValidCount(scrnMsg.A.getValidCount());
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSBL0300CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSBL0300CommonLogic.activateButtonsByFuncList(this, scrnMsg, functionList);
        NSBL0300CommonLogic.activateScreenItemsByFuncList(this, functionList, scrnMsg);
        NSBL0300CommonLogic.alternateTableRowColor(scrnMsg);
        NSBL0300CommonLogic.focusItem(scrnMsg);

    }
}
