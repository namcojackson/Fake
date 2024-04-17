/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2020;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2020.NLAL2020CMsg;
import business.servlet.NLAL2020.common.NLAL2020CommonLogic;
import business.servlet.NLAL2020.constant.NLAL2020Constant;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2020Scrn00_OpenWin_SerEntry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         Y.Taoka         Create          N/A
 * 03/17/2016   CSAI            Y.Imazu         Update          QC#5478
 *</pre>
 */
public class NLAL2020Scrn00_OpenWin_SerEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        final int eventLine = getButtonSelectNumber();

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(eventLine).rwsOpenFlg_A1.getValue())) {

            NLAL2020CommonLogic.chkRwsBalQtyForSerEntry(scrnMsg.A.no(eventLine));
            scrnMsg.addCheckItem(scrnMsg.A.no(eventLine).poBalQty_A1);
            scrnMsg.putErrorScreen();
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(eventLine));
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;

        NLAL2020CMsg bizMsg = new NLAL2020CMsg();
        bizMsg.setBusinessID(NLAL2020Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2020BMsg scrnMsg = (NLAL2020BMsg) bMsg;
        NLAL2020CMsg bizMsg = (NLAL2020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = getButtonSelectNumber();
        NLAL2020_ABMsg bizMsgLine = scrnMsg.A.no(index);

        scrnMsg.addCheckItem(scrnMsg.A.no(index).rtlWhCd_A1);
        scrnMsg.putErrorScreen();

        Object[] params = new Object[12];
        params[0] = "SN";
        params[1] = bizMsgLine.srcOrdNum_A1;
        params[2] = bizMsgLine.mdseCd_A1;
        params[3] = bizMsgLine.mdseDescShortTxt_A1;
        params[4] = bizMsgLine.poBalQty_A1;

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsgLine.rwsOpenFlg_A1.getValue()) && ZYPCommonFunc.hasValue(bizMsgLine.rwsStkQty_A1) && 1 < bizMsgLine.rwsStkQty_A1.getValueInt()) {

            params[4] = bizMsgLine.rwsStkQty_A1;
        }

        String editMd = "";

        if (NLAL2020CommonLogic.hasUpdateFuncId(scrnMsg) //
                && ZYPConstant.FLG_ON_Y.equals(bizMsgLine.rwsOpenFlg_A1.getValue())) {

            params[5] = "1";
            editMd = ZYPConstant.FLG_ON_Y;

        } else {

            params[5] = "2";
            editMd = ZYPConstant.FLG_OFF_N;
        }

        params[6] = bizMsgLine.rwsDtlLineNum_A1;

        for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {

            if(!SCE_ORD_TP.RETURN.equals(bizMsgLine.sceOrdTpCd_A1.getValue())){
                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxEdtModeFlg_SN, editMd);
            }
        }

        params[8] = scrnMsg.S;

        if (ZYPCommonFunc.hasValue(bizMsgLine.rtlWhCd_A1)) {

            params[9] = bizMsgLine.rtlWhCd_A1;
            params[10] = bizMsgLine.rmaSlsWhNm_A1;

        } else {

            params[9] = bizMsgLine.shipToRtlWhCd_A1;
            params[10] = bizMsgLine.rtlWhNm_A1;
        }
        params[11] = INBD_OTBD.INBOUND;
        setArgForSubScreen(params);
    }
}
