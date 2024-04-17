/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1060;

import static business.servlet.NSAL1060.constant.NSAL1060Constant.SCREEN_ID;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1060.NSAL1060CMsg;
import business.servlet.NSAL1060.common.NSAL1060CommonLogic;
import business.servlet.NSAL1060.constant.NSAL1060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Meter Reading Popup.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   Fujitsu         M.Hara          Create          N/A
 * 11/27/2015   Hitachi         T.Iwamoto       Update          QC#1235
 * 2016/04/27   Hitachi         T.Iwamoto       Update          QC#1759
 * 2019/11/05   Hitachi         K.Kitachi       Update          QC#54164
 * </pre>
 */
public class NSAL1060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1060BMsg scrnMsg = (NSAL1060BMsg) bMsg;
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // SVC_MACH_MSTR_PK
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk, ((EZDBBigDecimalItem) params[0]).getValue());
            // START_DATE
            // [QC#1235,MOD]START
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxFromDt, ((EZDBDateItem) params[1]).getValue());
            // [QC#1235,MOD]END
            // START 2019/11/05 K.Kitachi [QC#54164, ADD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk, ((EZDBBigDecimalItem) params[3]).getValue());
            // END 2019/11/05 K.Kitachi [QC#54164, ADD]
        }

        NSAL1060CMsg bizMsg = new NSAL1060CMsg();
        bizMsg.setBusinessID(NSAL1060Constant.BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1060BMsg scrnMsg = (NSAL1060BMsg) bMsg;
        NSAL1060CMsg bizMsg = (NSAL1060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initControl();

        NSAL1060CommonLogic.setProtected(scrnMsg);
        // ADD start 2016/04/27 CSA Defect#1759
        setRowColors(scrnMsg);
        // ADD end 2016/04/27 CSA Defect#1759

    }

    /**
     * initControl
     */
    private void initControl() {
        setButtonProperties(NSAL1060Constant.BUTTON_NAME_CLEAR, NSAL1060Constant.BUTTON_GUARD_CLEAR, NSAL1060Constant.BUTTON_LABEL_CLEAR, 0, null);
        setButtonProperties(NSAL1060Constant.BUTTON_NAME_RETURN, NSAL1060Constant.BUTTON_GUARD_CLOSE, NSAL1060Constant.BUTTON_LABEL_CLOSE, 1, null);
    }

    // ADD start 2016/04/27 CSA Defect#1759
    /**
     * protectedAllHeaderFields
     * @param NSAL1060BMsg scrnMsg
     */
    private static void setRowColors(NSAL1060BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // ADD end 2016/04/27 CSA Defect#1759

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1060BMsg scrnMsg = (NSAL1060BMsg) bMsg;

        scrnMsg.serNum.setNameForMessage("Serial#");
    }
}
