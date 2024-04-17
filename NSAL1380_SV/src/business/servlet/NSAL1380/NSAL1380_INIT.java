/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1380;

import static business.servlet.NSAL1380.constant.NSAL1380Constant.BIZ_ID;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.INPUT_PARAM_NUM;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.NWAM0755E;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.SCRN_ID_00;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1380.NSAL1380CMsg;
import business.servlet.NSAL1380.common.NSAL1380CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Hitachi         N.Arai          Create          N/A
 *</pre>
 */
public class NSAL1380_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1380BMsg scrnMsg = (NSAL1380BMsg) bMsg;
        NSAL1380CMsg bizMsg = new NSAL1380CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= INPUT_PARAM_NUM) {

            setValue(scrnMsg.shellLineNum,          (EZDBBigDecimalItem) params[0]);
            setValue(scrnMsg.svcPgmMdseCd,          (EZDBStringItem) params[1]);
            setValue(scrnMsg.termMthAot,            (EZDBBigDecimalItem) params[2]);
            setValue(scrnMsg.mdlId,                 (EZDBBigDecimalItem) params[3]);
            setValue(scrnMsg.prcMtrPkgPk,           (EZDBBigDecimalItem) params[4]);
            setValue(scrnMsg.prcSvcPlnTpCd,         (EZDBStringItem) params[5]);
            setValue(scrnMsg.prcSvcContrTpCd,       (EZDBStringItem) params[6]);
            setValue(scrnMsg.bllgMtrLbCd,           (EZDBStringItem) params[7]);
            setValue(scrnMsg.prcListBandDescTxt,    (EZDBStringItem) params[8]);
            setValue(scrnMsg.prcCatgCd,             (EZDBStringItem) params[9]);
            setValue(scrnMsg.dsContrDtlPk,          (EZDBBigDecimalItem) params[10]);
        } else {
            scrnMsg.setMessageInfo(NWAM0755E);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1380BMsg scrnMsg = (NSAL1380BMsg) bMsg;
        NSAL1380CMsg bizMsg = (NSAL1380CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL1380CommonLogic.initControlScreen(this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        setAppFracDigit(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL1380BMsg scrnMsg = (NSAL1380BMsg) bMsg;

        scrnMsg.shellLineNum.setNameForMessage("Shell#");
        scrnMsg.svcPgmMdseCd.setNameForMessage("Service Program MDSE Code");
        scrnMsg.termMthAot.setNameForMessage("Term Month Number");
        scrnMsg.splyBaseAmt.setNameForMessage("Supply Base Price");
        scrnMsg.splyAgmtPlnPk.setNameForMessage("Plan ID");
        scrnMsg.splyAgmtPlnNm.setNameForMessage("Plan Name");
        scrnMsg.splyAgmtPlnShortNm.setNameForMessage("Plan Short Name");
        scrnMsg.splyAgmtPlnDescTxt.setNameForMessage("Plan Description/Notes");
        scrnMsg.annTermCapNum.setNameForMessage("Annual Term Cap");
        scrnMsg.qtyContrCapQty.setNameForMessage("Total Term Cap");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1380_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.imgSplyTpNm_A.setNameForMessage("Item Type");
            abMsg.mdseCd_A.setNameForMessage("Item Code");
            abMsg.mnfItemCd_A.setNameForMessage("Manufacture Item#");
            abMsg.mdseCd_A.setNameForMessage("Item Code");
            abMsg.mdseDescShortTxt_A.setNameForMessage("Item Description");
            abMsg.shpgIntvlCd_A.setNameForMessage("Frequency");
            abMsg.firstShipQty_A.setNameForMessage("First Qty");
            abMsg.otmShipQty_A.setNameForMessage("Quantity");
            abMsg.xxTotQtyCnt_A.setNameForMessage("Annual Entitlement");
            abMsg.xxTotQtyCnt_AT.setNameForMessage("Term Total Entitlement");
        }
    }

    private void setAppFracDigit(NSAL1380BMsg scrnMsg) {
        scrnMsg.splyBaseAmt.setAppFracDigit(2);
    }
}
