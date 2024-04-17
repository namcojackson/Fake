/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230;

import static business.servlet.NPAL1230.constant.NPAL1230Constant.BIZ_APP_ID;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.FUNCTION_CD_SEARCH;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1230.NPAL1230CMsg;
import business.servlet.NPAL1230.common.NPAL1230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NPAL1230_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // dev env doesn't need security check
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        setParametersFromPreviousScreen(scrnMsg);

        NPAL1230CMsg bizMsg = new NPAL1230CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * <p>
     * Sets the parameters from previous screen.
     * </p>
     * @param scrnMsg scrnMsg
     */
    private void setParametersFromPreviousScreen(NPAL1230BMsg scrnMsg) {

        Serializable args = getArgForSubScreen();
        if (args == null || !(args instanceof Object[])) {
            return;
        }
        Object[] params = (Object[]) args;
        if (params.length == 0) {
            return;
        }
        // sets the Parent Vendor PK.
        ZYPEZDItemValueSetter.setValue(scrnMsg.aslHdrPk_S1, (EZDBBigDecimalItem) params[0]);

        if (ZYPCommonFunc.hasValue((EZDBStringItem) params[1])) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_S1, (EZDBStringItem) params[1]);
        }

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;
        NPAL1230CMsg bizMsg = (NPAL1230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1230CommonLogic.ctrlScrnItemDispInit(this, scrnMsg);
        NPAL1230CommonLogic.ctrlDetailButton(this, scrnMsg);
        NPAL1230CommonLogic.setTableScreen(scrnMsg);

        if (scrnMsg.Q.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, ZYPConstant.FLG_OFF_N);
        }

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.aslNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NPAL1230CommonLogic.setNameForMessage((NPAL1230BMsg) scrnMsg);
    }
}
