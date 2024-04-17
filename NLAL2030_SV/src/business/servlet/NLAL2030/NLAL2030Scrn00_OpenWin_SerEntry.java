/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2030.NLAL2030CMsg;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030Scrn00_OpenWin_SerEntry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLAL2030Scrn00_OpenWin_SerEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        NLAL2030CMsg bizMsg = new NLAL2030CMsg();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(getButtonSelectNumber()));

        bizMsg.setBusinessID(NLAL2030Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        NLAL2030CMsg bizMsg  = (NLAL2030CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = getButtonSelectNumber();

        Object[] params = new Object[12];
        params[0] = "SN";

        if (NLAL2030Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue())) {

            NLAL2030_ABMsg scrnMsgLine = scrnMsg.A.no(index);
            params[1] = scrnMsgLine.trxOrdNum_A1;
            params[2] = scrnMsgLine.mdseCd_A1;
            params[3] = scrnMsgLine.mdseDescShortTxt_A1;
            params[4] = scrnMsgLine.xxQty10Num_A1;
            params[6] = scrnMsgLine.dplyLineNum_A1;
            params[9] = scrnMsgLine.rtlWhCd_A1;
            params[10] = scrnMsgLine.rtlWhNm_A1;

        } else if (NLAL2030Constant.TAB_ID_RWS.equals(scrnMsg.xxDplyTab.getValue())) {

            NLAL2030_BBMsg scrnMsgLine = scrnMsg.B.no(index);

            if (BigDecimal.ZERO.compareTo(scrnMsgLine.rwsPutAwayQty_B1.getValue()) < 0) {

                params[4] = scrnMsgLine.rwsPutAwayQty_B1;

            } else {

                params[4] = scrnMsgLine.rwsQty_B1;
            }

            params[1] = scrnMsgLine.trxOrdNum_B1;
            params[2] = scrnMsgLine.mdseCd_B1;
            params[3] = scrnMsgLine.mdseDescShortTxt_B1;
            params[6] = scrnMsgLine.dplyLineNum_B1;
            params[9] = scrnMsgLine.rtlWhCd_B1;
            params[10] = scrnMsgLine.rtlWhNm_B1;
        }

        params[5] = "2";
        params[8] = scrnMsg.S;
        params[11] = INBD_OTBD.INBOUND;

        setArgForSubScreen(params);
    }
}
