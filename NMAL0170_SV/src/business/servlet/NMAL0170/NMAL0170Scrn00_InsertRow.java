/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.BIZ_ID;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.EVENT_INSERT_ROW;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.FUNC_CD_SRCH;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0170.NMAL0170CMsg;
import business.servlet.NMAL0170.common.NMAL0170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0170Scrn00_InsertRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         T.Arima         Create          N/A
 * 2016/02/25   S.Tanikawa      S.Tanikawa      Update          QC#2669
 * 2017/02/09   Fujitsu         K.Ishizuka      Update          QC#17463
 *</pre>
 */
public class NMAL0170Scrn00_InsertRow extends S21CommonHandler {

    @Override
    /*
     * Check Input Event - do Nothing.
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do Nothing
    }

    @Override
    /*
     * Set Request Date Event - do Nothing.
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // MOD START S21_NA #17463
        // do Nothing

        //return null;
        
        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        NMAL0170CMsg bizMsg = new NMAL0170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        // MOD END S21_NA #17463
    }

    @Override
    /*
     * Do Process Event - Create New Line. - Set Protect for input field.
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        NMAL0170CMsg bizMsg = (NMAL0170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        int index = scrnMsg.A.getValidCount();
        // DEL START S21_NA #17463
//        if (index == scrnMsg.A.length()) {
//            return;
//        }
//        scrnMsg.A.setValidCount(index + 1);
        // DEL END S21_NA #17463
        //String salseDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        //ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index -1).supdRelnStageDt_A1, salseDate);

        // DEL START S21_NA #17463
        // ADD START 2016/03/02 QC#2669
//        if (index == 0) {
//            BigDecimal adVal = new BigDecimal("1");
//            BigDecimal curNum = scrnMsg.xxPageShowCurNum.getValue().add(adVal);
//            BigDecimal frmNum = scrnMsg.xxPageShowFromNum.getValue().add(adVal);
//            BigDecimal ofNum = scrnMsg.xxPageShowOfNum.getValue().add(adVal);
//            BigDecimal toNum = scrnMsg.xxPageShowToNum.getValue().add(adVal);
//            BigDecimal totNum = scrnMsg.xxPageShowTotNum.getValue().add(adVal);
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowCurNum, curNum);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowFromNum, frmNum);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowOfNum, ofNum);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowToNum, toNum);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageShowTotNum, totNum);
//        }
        // ADD END 2016/03/02 QC#2669
        // DEL END S21_NA #17463
        NMAL0170CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A"); // ADD S21_NA #17463
        NMAL0170CommonLogic.setCmnBtnProp(this, scrnMsg, EVENT_INSERT_ROW);
        NMAL0170CommonLogic.setScrnLineProtected(scrnMsg);
        // ADD START S21_NA #17463
        scrnMsg.setFocusItem(scrnMsg.A.no(index -1).supdToMdseCd_A1);
        // ADD END S21_NA #17463
    }
}
