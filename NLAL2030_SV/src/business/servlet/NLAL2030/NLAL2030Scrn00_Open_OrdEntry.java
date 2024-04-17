/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030Scrn00_Open_OrdEntry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 06/07/2016   CSAI            Y.Imazu         Update          QC#8290
 * 06/23/2016   CSAI            Y.Imazu         Update          QC#9874
 * 09/16/2016   CITS            K.Ogino         Update          QC#13405
 *</pre>
 */
public class NLAL2030Scrn00_Open_OrdEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        int index = getButtonSelectNumber();
        String sceOrdTpCd = "";
        String ordNum = "";

        ZYPTableUtil.clear(scrnMsg.P);

        if (NLAL2030Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue())) {

            sceOrdTpCd = scrnMsg.A.no(index).sceOrdTpCd_A1.getValue();
            ordNum = scrnMsg.A.no(index).trxOrdNum_A1.getValue();

        } else if (NLAL2030Constant.TAB_ID_RWS.equals(scrnMsg.xxDplyTab.getValue())) {

            sceOrdTpCd = scrnMsg.B.no(index).sceOrdTpCd_B1.getValue();
            ordNum = scrnMsg.B.no(index).trxOrdNum_B1.getValue();
        }

        int i = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, ordNum);

        Object[] params = new Object[1];
        params[0] = scrnMsg.P.no(0).xxPopPrm;

        if (SCE_ORD_TP.DOMESTIC_CANON_GROUP.equals(sceOrdTpCd) || SCE_ORD_TP.DOMESTIC_PO_EXISTS.equals(sceOrdTpCd) || SCE_ORD_TP.DOMESTIC_NON_CSA_INVENTORY.equals(sceOrdTpCd)
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(sceOrdTpCd) || SCE_ORD_TP.BUY_BACK.equals(sceOrdTpCd)) {

            // Move to NPAL1500
            setResult("01");

        } else if (SCE_ORD_TP.KITTING.equals(sceOrdTpCd) || SCE_ORD_TP.KITTING_CANCEL.equals(sceOrdTpCd) || SCE_ORD_TP.UN_KITTING.equals(sceOrdTpCd)) {

            scrnMsg.svcConfigMstrPk_P.clear();

            params = new Object[8];
            params[0] = scrnMsg.P.no(0).xxPopPrm;
            params[1] = scrnMsg.P.no(1).xxPopPrm;
            params[2] = scrnMsg.svcConfigMstrPk_P;
            params[3] = scrnMsg.P.no(3).xxPopPrm;
            params[4] = scrnMsg.P.no(4).xxPopPrm;
            params[5] = scrnMsg.P.no(5).xxPopPrm;
            params[6] = scrnMsg.P.no(6).xxPopPrm;
            params[7] = scrnMsg.svcConfigMstrPk_P;

            // Move to NPAL1360
            setResult("02");

        } else if (SCE_ORD_TP.TECH_REQUEST.equals(sceOrdTpCd)) {

            // Move to NPAL1080
            setResult("04");

        } else if (SCE_ORD_TP.RETURN.equals(sceOrdTpCd) || SCE_ORD_TP.TRIAL_INVENTORY.equals(sceOrdTpCd) || SCE_ORD_TP.DC_TRANSFER.equals(sceOrdTpCd)) {

            // Move to NWAL1500
            setResult("05");
        } else if (SCE_ORD_TP.REMAN_PARTS_USAGE.equals(sceOrdTpCd)) {

            // Move to NPAL1410
            setResult("06");
        }

        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
