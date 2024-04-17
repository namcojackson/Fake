/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0230;

import static business.servlet.NMAL0230.constant.NMAL0230Constant.BIZ_ID;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.JUMP_PAGE_NUMBER_ITNM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0230.NMAL0230CMsg;
import business.servlet.NMAL0230.common.NMAL0230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL0230_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 * 2017/02/07   Fujitsu         K.Ishizuka      Update          QC#16807
 *</pre>
 */
public class NMAL0230_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;
        NMAL0230CMsg bizMsg = new NMAL0230CMsg();

        ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg_BO, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg_CO, ZYPConstant.FLG_ON_Y);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;
        NMAL0230CMsg bizMsg = (NMAL0230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0230CommonLogic.initCmnBtnProp(this);
        NMAL0230CommonLogic.controlScreen(this, getUserProfileService(), scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        // ADD start S21_NA #16807
        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;
        scrnMsg.xxPageShowCurNum.setNameForMessage(JUMP_PAGE_NUMBER_ITNM);
        // ADD end S21_NA #16807
    }
}
