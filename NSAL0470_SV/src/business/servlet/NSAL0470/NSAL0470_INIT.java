/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0470;

import static business.servlet.NSAL0470.constant.NSAL0470Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0470.NSAL0470CMsg;
import business.servlet.NSAL0470.common.NSAL0470CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Yamada        Create          N/A
 * 2017/07/25   Hitachi         K.kasai         Update          QC18882
 *</pre>
 */
public class NSAL0470_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0470BMsg scrnMsg = (NSAL0470BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params.length > 0) {
                setValue(scrnMsg.dsContrNum, (EZDBStringItem) params[0]);
            }
        }

        NSAL0470CMsg bizMsg = new NSAL0470CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0470BMsg scrnMsg = (NSAL0470BMsg) bMsg;
        NSAL0470CMsg bizMsg  = (NSAL0470CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0470CommonLogic.initialize(this, scrnMsg);
        //START 2017/07/24 K.Kasai [QC#18882,ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
        //END 2017/07/24 K.Kasai [QC#18882,ADD]
        NSAL0470CommonLogic.screenItemControl(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsContrVldPk_H3);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0470BMsg scrnMsg = (NSAL0470BMsg) bMsg;

        scrnMsg.dsContrVldPk_H3.setNameForMessage("Validation");
        scrnMsg.dsContrVldPk_H3.setNameForMessage("Serial#");
        scrnMsg.xxChkBox_H1.setNameForMessage("Outcome");
        scrnMsg.xxChkBox_H2.setNameForMessage("Outcome");
        scrnMsg.xxChkBox_H3.setNameForMessage("Outcome");
        scrnMsg.xxChkBox_H4.setNameForMessage("Outcome");

    }
}
