/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3070.NLBL3070CMsg;
import business.servlet.NLBL3070.common.NLBL3070CommonLogic;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLBL3070Scrn00_OpenWin_SerEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        final int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        NLBL3070CMsg bizMsg = new NLBL3070CMsg();
        bizMsg.setBusinessID(NLBL3070Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        NLBL3070CMsg bizMsg  = (NLBL3070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = getButtonSelectNumber();

        Object[] params = new Object[12];
        params[0] = "SN";
        params[1] = scrnMsg.B.no(index).trxHdrNum_B1;
        params[2] = scrnMsg.B.no(index).mdseCd_B1;
        params[3] = scrnMsg.B.no(index).mdseDescShortTxt_B1;
        params[4] = scrnMsg.B.no(index).xxShipQty_B1;

        if (NLBL3070CommonLogic.isUpdateUser(getUserProfileService())
                && ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(index).soLineOpenFlg_B1.getValue())
                && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(index).shipFlg_B1.getValue())) {

            params[5] = "1";

        } else {

            params[5] = "2";
        }

        params[6] = scrnMsg.B.no(index).soSlpNum_B1;

        scrnMsg.S.setValidCount(scrnMsg.B.no(index).xxShipQty_B1.getValue().intValue());

        for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {

            if (NLBL3070CommonLogic.isUpdateUser(getUserProfileService())
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(index).soLineOpenFlg_B1.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(index).shipFlg_B1.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxEdtModeFlg_SN, ZYPConstant.FLG_ON_Y);

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxHdrNum_SN, scrnMsg.B.no(index).soNum_B1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxDplyTrxNumTxt_SN, scrnMsg.B.no(index).soSlpNum_B1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).mdseCd_SN, scrnMsg.B.no(index).mdseCd_B1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).rtlSwhCd_SN, scrnMsg.B.no(index).rtlSwhCd_B1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).invtyLocCd_SN, scrnMsg.B.no(index).invtyLocCd_B1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).svcConfigMstrPk_SN, scrnMsg.B.no(index).pickSvcConfigMstrPk_B1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).serNumTakeFlg_SN, scrnMsg.S.no(i).xxEdtModeFlg_SN);
        }

        params[8] = scrnMsg.S;
        params[9] = scrnMsg.B.no(index).rtlWhCd_B1;
        params[10] = scrnMsg.B.no(index).rtlWhNm_B1;
        params[11] = INBD_OTBD.OUTBOUND;

        setArgForSubScreen(params);
    }
}
