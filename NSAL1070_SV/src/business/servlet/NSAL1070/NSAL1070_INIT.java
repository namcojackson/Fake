/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1070;

import static business.servlet.NSAL1070.constant.NSAL1070Constant.SCREEN_ID;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1070.NSAL1070CMsg;
import business.servlet.NSAL1070.common.NSAL1070CommonLogic;
import business.servlet.NSAL1070.constant.NSAL1070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/26/2015   Fujitsu         M.Hara          Create          N/A
 * 2016/04/26   Hitachi         T.Iwamoto       Update          QC#1759
 * 2017/09/28   Hitachi         K.Kojima        Update          QC#18376
 *</pre>
 */
public class NSAL1070_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1070CMsg bizMsg = new NSAL1070CMsg();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // DS_CONTR_DTL_PK
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrDtlPk, ((EZDBBigDecimalItem) params[0]).getValue());
            // DS_CONTR_CATG_CD
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgCd, ((EZDBStringItem) params[1]).getValue());
            // MTR_PRC_UP_RATIO 
            ZYPEZDItemValueSetter.setValue(bizMsg.mtrPrcUpRatio, ((EZDBBigDecimalItem) params[2]).getValue());
            // START 2017/09/28 K.Kojima [QC#18376,ADD]
            // MTR_PRC_UP_RATIO 
            ZYPEZDItemValueSetter.setValue(bizMsg.rnwPrcMethCd, ((EZDBStringItem) params[3]).getValue());
            // END 2017/09/28 K.Kojima [QC#18376,ADD]
        }

        bizMsg.setBusinessID(NSAL1070Constant.BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1070BMsg scrnMsg = (NSAL1070BMsg) bMsg;
        NSAL1070CMsg bizMsg  = (NSAL1070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initControl();

        NSAL1070CommonLogic.setProtected(scrnMsg);
        // ADD start 2016/04/26 CSA Defect#1759
        setRowColors(scrnMsg);
        // ADD end 2016/04/26 CSA Defect#1759
    }

    /**
     * initControl
     */
    private void initControl() {
        setButtonProperties(NSAL1070Constant.BUTTON_NAME_CLEAR, NSAL1070Constant.BUTTON_GUARD_CLEAR, NSAL1070Constant.BUTTON_LABEL_CLEAR, 0, null);
        setButtonProperties(NSAL1070Constant.BUTTON_NAME_RETURN, NSAL1070Constant.BUTTON_GUARD_CLOSE, NSAL1070Constant.BUTTON_LABEL_CLOSE, 1, null);
    }

    // ADD start 2016/04/26 CSA Defect#1759
    /**
     * protectedAllHeaderFields
     * @param NSAL1070BMsg scrnMsg
     */
    private static void setRowColors(NSAL1070BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // ADD end 2016/04/26 CSA Defect#1759

    @Override
    protected void setNameForMessage(EZDBMsg msg) {
    }
}
