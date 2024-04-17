/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;
import business.servlet.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLBL2020Scrn00_OpenWin_SerEntry extends S21CommonHandler {

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

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        int index = getButtonSelectNumber();

        Object[] params = new Object[12];
        params[0] = "SN";
        params[1] = scrnMsg.A.no(index).trxHdrNum_HI;
        params[2] = scrnMsg.A.no(index).mdseCd_A1;
        params[3] = scrnMsg.A.no(index).mdseDescShortTxt_A1;
        params[4] = scrnMsg.A.no(index).shipQty_A1.getValue().abs();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL2020Constant.BUSINESS_ID);
        boolean inquiry = NLBL2020CommonLogic.chkInquiryRole(funcList);

        if (inquiry || !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).soLineOpenFlg_AH.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).shipFlg_AH.getValue())) {

            params[5] = NLBL2020Constant.MODE_INQUIRY;

        } else {

            params[5] = NLBL2020Constant.MODE_UPDATE;
        }

        params[6] = scrnMsg.A.no(index).soSlpNum_HI;
        params[8] = NLBL2020CommonLogic.setSerNumEntryPopupParam(scrnMsg, scrnMsg.A.no(index), inquiry);
        params[9] = scrnMsg.A.no(index).shipFromRtlWhCd_AH;
        params[10] = scrnMsg.A.no(index).rtlWhNm_A1;
        params[11] = INBD_OTBD.OUTBOUND;

        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).xxWrnSkipFlg_A1, ZYPConstant.FLG_OFF_N);
        setArgForSubScreen(params);
    }
}
