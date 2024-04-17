/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2190;

import static business.servlet.NWAL2190.constant.NWAL2190Constant.BIZ_ID;
import static business.servlet.NWAL2190.constant.NWAL2190Constant.INPUT_PARAM_NUM;
import static business.servlet.NWAL2190.constant.NWAL2190Constant.NWAM0755E;
import static business.servlet.NWAL2190.constant.NWAL2190Constant.SCRN_ID_00;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2190.NWAL2190CMsg;
import business.servlet.NWAL2190.common.NWAL2190CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 *
 * Supply Agreement Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/02   Fujitsu         S.Iidaka        Create          N/A
 * 2017/03/07   Fujitsu         S.Iidaka        Update          QC#17668
 *</pre>
 */
public class NWAL2190_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2190BMsg scrnMsg = (NWAL2190BMsg) bMsg;
        NWAL2190CMsg bizMsg = new NWAL2190CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= INPUT_PARAM_NUM) {

            setValue(scrnMsg.dsImptSvcLineNum,         (EZDBBigDecimalItem) params[0]);
            setValue(scrnMsg.dsImptSvcMdseCd,          (EZDBStringItem) params[1]);
            setValue(scrnMsg.shpgIntvlMthNum,            (EZDBBigDecimalItem) params[2]);
            setValue(scrnMsg.mdlId,                 (EZDBBigDecimalItem) params[3]);
            setValue(scrnMsg.prcMtrPkgPk,           (EZDBBigDecimalItem) params[4]);
            setValue(scrnMsg.prcSvcPlnTpCd,         (EZDBStringItem) params[5]);
            setValue(scrnMsg.prcSvcContrTpCd,       (EZDBStringItem) params[6]);
            setValue(scrnMsg.bllgMtrLbCd,           (EZDBStringItem) params[7]);
            setValue(scrnMsg.prcListBandDescTxt,    (EZDBStringItem) params[8]);
            setValue(scrnMsg.prcCatgCd,             (EZDBStringItem) params[9]);
            setValue(scrnMsg.cpoSvcConfigRefPk,     (EZDBBigDecimalItem) params[10]);

            setValue(scrnMsg.xxPageCd,              (EZDBStringItem) params[11]);
            setValue(scrnMsg.xxScrItem50Txt,        (EZDBStringItem) params[12]);
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

        NWAL2190BMsg scrnMsg = (NWAL2190BMsg) bMsg;
        NWAL2190CMsg bizMsg = (NWAL2190CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL2190CommonLogic.initControlScreen(this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        setAppFracDigit(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL2190BMsg scrnMsg = (NWAL2190BMsg) bMsg;

        scrnMsg.dsImptSvcLineNum.setNameForMessage("Maintenance Shell#");
        scrnMsg.dsImptSvcMdseCd.setNameForMessage("Shell Item");
        scrnMsg.shpgIntvlMthNum.setNameForMessage("Shell Term");
        scrnMsg.splyBaseAmt.setNameForMessage("Supply Base Price");
        scrnMsg.splyAgmtPlnPk.setNameForMessage("Plan ID");
        scrnMsg.splyAgmtPlnNm.setNameForMessage("Plan Name");
        scrnMsg.splyAgmtPlnShortNm.setNameForMessage("Plan Short Name");
        scrnMsg.splyAgmtPlnDescTxt.setNameForMessage("Plan Description/Notes");
        scrnMsg.annTermCapNum.setNameForMessage("Annual Term Cap");
        scrnMsg.qtyContrCapQty.setNameForMessage("Total Term Cap");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL2190_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.imgSplyTpNm_A.setNameForMessage("Item Type");
            abMsg.mdseCd_A.setNameForMessage("Item Code");
            abMsg.mnfItemCd_A.setNameForMessage("Manufacture Item#");
            abMsg.mdseCd_A.setNameForMessage("Item Code");
            abMsg.mdseDescShortTxt_A.setNameForMessage("Item Description");
            abMsg.shpgIntvlCd_A.setNameForMessage("Frequency");
            // 2017/03/07 QC#17668 Mod Start
            abMsg.firstShipQty_A.setNameForMessage("First Qty");
            abMsg.otmShipQty_A.setNameForMessage("Quantity");
            // 2017/03/07 QC#17668 Mod End
            abMsg.xxTotQtyCnt_A.setNameForMessage("Annual Entitlement");
            abMsg.xxTotQtyCnt_AT.setNameForMessage("Term Total Entitlement");
        }
    }

    private void setAppFracDigit(NWAL2190BMsg scrnMsg) {
        scrnMsg.splyBaseAmt.setAppFracDigit(2);
    }
}
