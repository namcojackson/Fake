/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.NLBL3200_ABMsg;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 06/20/2016   CSAI            Y.Imazu         Update          QC#8290
 * 06/21/2016   CSAI            Y.Imazu         Update          QC#9874
 * 07/22/2016   CITS            Y.Nomura        Update          QC#11760
 *</pre>
 */
public class NLBL3200Scrn00_Open_SrcOrdEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        int index = getButtonSelectNumber();
        NLBL3200_ABMsg selectRecord = scrnMsg.A.no(index);

        Object[] params = new Object[1];
        params[0] = selectRecord.trxHdrNum_A1;

        if (SCE_ORD_TP.DIRECT_SALES.equals(selectRecord.sceOrdTpCd_AH.getValue()) || SCE_ORD_TP.DC_TRANSFER.equals(selectRecord.sceOrdTpCd_AH.getValue())) {

            setResult(NLBL3200Constant.RTRN_CD_RS);

        } else if (SCE_ORD_TP.KITTING.equals(selectRecord.sceOrdTpCd_AH.getValue()) || SCE_ORD_TP.UN_KITTING.equals(selectRecord.sceOrdTpCd_AH.getValue())) {

            ZYPTableUtil.clear(scrnMsg.P);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, selectRecord.trxHdrNum_A1);

            params = new Object[8];
            params[0] = scrnMsg.P.no(0).xxPopPrm;
            params[1] = scrnMsg.P.no(1).xxPopPrm;
            params[2] = scrnMsg.P.no(2).xxTrxRefPk;
            params[3] = scrnMsg.P.no(3).xxPopPrm;
            params[4] = scrnMsg.P.no(4).xxPopPrm;
            params[5] = scrnMsg.P.no(5).xxPopPrm;
            params[6] = scrnMsg.P.no(6).xxPopPrm;
            params[7] = scrnMsg.P.no(7).xxTrxRefPk;

            setResult(NLBL3200Constant.RTRN_CD_KT);

        } else if (SCE_ORD_TP.ITEM_CHANGE.equals(selectRecord.sceOrdTpCd_AH.getValue())) {

            setResult(NLBL3200Constant.RTRN_CD_IC);

        } else if (SCE_ORD_TP.STOCK_STATUS_CHANGE.equals(selectRecord.sceOrdTpCd_AH.getValue())) {

            setResult(NLBL3200Constant.RTRN_CD_SC);

        } else if (SCE_ORD_TP.SUB_WH_CHANGE.equals(selectRecord.sceOrdTpCd_AH.getValue())) {

            setResult(NLBL3200Constant.RTRN_CD_SW);

        } else if (SCE_ORD_TP.CONFIG_CHANGE.equals(selectRecord.sceOrdTpCd_AH.getValue())) {

            setResult(NLBL3200Constant.RTRN_CD_CC);

        } else if (SCE_ORD_TP.INTERNAL_TRANSFER.equals(selectRecord.sceOrdTpCd_AH.getValue())
                || SCE_ORD_TP.DISPOSAL.equals(selectRecord.sceOrdTpCd_AH.getValue())
                || SCE_ORD_TP.REPAIR_SUBCONTRACT.equals(selectRecord.sceOrdTpCd_AH.getValue())
                || SCE_ORD_TP.REFURBISH_EXPENSE.equals(selectRecord.sceOrdTpCd_AH.getValue())
                || SCE_ORD_TP.RETURN_TO_VENDOR_DOMESTIC.equals(selectRecord.sceOrdTpCd_AH.getValue())) {

            setResult(NLBL3200Constant.RTRN_CD_DT);

        } else if (SCE_ORD_TP.TECH_REQUEST.equals(selectRecord.sceOrdTpCd_AH.getValue())) {

            setResult(NLBL3200Constant.RTRN_CD_TR);

        } else if (SCE_ORD_TP.REMAN_ITEM_CHANGE.equals(selectRecord.sceOrdTpCd_AH.getValue())
                || SCE_ORD_TP.REMAN_LOCATOR_TRANSFER.equals(selectRecord.sceOrdTpCd_AH.getValue())
                || SCE_ORD_TP.REMAN_PARTS_BACK_INVENTORY.equals(selectRecord.sceOrdTpCd_AH.getValue())) {

            setResult(NLBL3200Constant.RTRN_CD_RM);
        }

        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
