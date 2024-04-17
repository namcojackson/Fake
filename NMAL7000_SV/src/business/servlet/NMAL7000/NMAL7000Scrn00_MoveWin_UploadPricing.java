/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7000;

import static business.servlet.NMAL7000.constant.NMAL7000Constant.UPLD_CSV_ID_EQUIP_WRK;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.UPLD_CSV_ID_SVC_WRK;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.UPLD_CSV_ID_SVC_TIER_WRK;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.UPLD_CSV_ID_ADDL_CHRG_WRK;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.UPLD_CSV_ID_SPLY_PGM_WRK;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.UPLD_CSV_ID_LEASE_RATE_WRK;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.UPLD_CSV_ID_LEASE_RTRN_WRK;
import static business.servlet.NMAL7000.constant.NMAL7000Constant.UPLD_CSV_ID_TI_VAL_WRK;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7000Scrn00_MoveWin_UploadPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7000Scrn00_MoveWin_UploadPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;

        String prcListStruTpCd = "";
        if (scrnMsg.A.getValidCount() > 0) {
            prcListStruTpCd = scrnMsg.A.no(scrnMsg.xxRadioBtn.getValueInt()).prcListStruTpCd_A1.getValue();
        }

        Object[] params = new Object[1];
        scrnMsg.xxPopPrm_Z0.clear();

        if (PRC_LIST_STRU_TP.EQUIPMENT.equals(prcListStruTpCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, UPLD_CSV_ID_EQUIP_WRK);
        } else if (PRC_LIST_STRU_TP.SERVICE.equals(prcListStruTpCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, UPLD_CSV_ID_SVC_WRK);
        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTpCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, UPLD_CSV_ID_SVC_TIER_WRK);
        } else if (PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(prcListStruTpCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, UPLD_CSV_ID_ADDL_CHRG_WRK);
        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTpCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, UPLD_CSV_ID_SPLY_PGM_WRK);
        } else if (PRC_LIST_STRU_TP.LEASE_RATES.equals(prcListStruTpCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, UPLD_CSV_ID_LEASE_RATE_WRK);
        } else if (PRC_LIST_STRU_TP.LEASE_RETURN_FEES.equals(prcListStruTpCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, UPLD_CSV_ID_LEASE_RTRN_WRK);
        } else if (PRC_LIST_STRU_TP.TRADE_INS.equals(prcListStruTpCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, UPLD_CSV_ID_TI_VAL_WRK);
        }
        params[0] = scrnMsg.xxPopPrm_Z0;
        setArgForSubScreen(params);

    }
}
