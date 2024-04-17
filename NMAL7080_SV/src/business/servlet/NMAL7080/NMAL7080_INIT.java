/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7080;

import static business.servlet.NMAL7080.constant.NMAL7080Constant.BIZ_ID;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.MODE_UPDATE;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.SCRN_ID_00;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7080.NMAL7080CMsg;
import business.servlet.NMAL7080.common.NMAL7080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7080_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;
        NMAL7080CMsg bizMsg = new NMAL7080CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params != null) {
                String screenMode = (String) params[0];
                if (MODE_UPDATE.equals(screenMode)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.splyAgmtPlnPk, (EZDBBigDecimalItem) params[1]);
                } else {
                    scrnMsg.splyAgmtPlnPk.clear();
                }
            }
        } else {
            scrnMsg.splyAgmtPlnPk.clear();
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;
        NMAL7080CMsg bizMsg = (NMAL7080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7080CommonLogic.initControlScreen(this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;

        scrnMsg.splyAgmtPlnPk.setNameForMessage("Plan ID");
        scrnMsg.splyAgmtPlnNm.setNameForMessage("Plan Name");
        scrnMsg.splyAgmtPlnShortNm.setNameForMessage("Plan Short Name");
        scrnMsg.splyAgmtPlnDescTxt.setNameForMessage("Plan Description/Notes");
        scrnMsg.splyAgmtPlnTpCd.setNameForMessage("Plan Type");
        scrnMsg.splyAgmtDocTpCd.setNameForMessage("Document Type");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");
        scrnMsg.annTermCapNum.setNameForMessage("Annual Term Cap");
        scrnMsg.xxChkBox_AF.setNameForMessage("Active");
        scrnMsg.splyAgmtPlnStsCd.setNameForMessage("Show");
        scrnMsg.mdseCd_IC.setNameForMessage("Item Code");
        scrnMsg.xxDt10Dt_MD.setNameForMessage("Mass Update Date");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NMAL7080_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.xxChkBox_A.setNameForMessage("Update/Delete");
            abMsg.mdseCd_A.setNameForMessage("Item Code");
            abMsg.mdseDescShortTxt_A.setNameForMessage("Item Description");
            abMsg.splyAgmtFreqTpCd_A.setNameForMessage("Frequency");
            abMsg.splyAgmtPlnFirstQty_A.setNameForMessage("First Quantity");
            abMsg.splyAgmtPlnQty_A.setNameForMessage("Quantity");
            abMsg.splyAgmtPlnSqNum_A.setNameForMessage("Sequence #");
            abMsg.effFromDt_A.setNameForMessage("Effective Date From");
            abMsg.effThruDt_A.setNameForMessage("Effective Date To");
        }
    }
}
