/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : OnBlur_OrderQty
 * OnChange_LineType
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 02/18/2016   CITS            K.Ogino          Update          QC#3676
 *</pre>
 */
public class NPAL1280Scrn00_OnBlur_OrderQty extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        int lineNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineNum, Integer.valueOf(lineNum).toString());
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        int lineNum = getButtonSelectNumber();

        scrnMsg.addCheckItem(scrnMsg.A.no(lineNum).prchReqDispQty_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1);
        scrnMsg.putErrorScreen();

        if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1)) && scrnMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1.getValueInt() == 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(lineNum).prntVndNm_A1);
            return;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(lineNum).prchReqDispQty_A1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineNum).xxUnitPrc_A1, scrnMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1.getValue().multiply(scrnMsg.A.no(lineNum).prchReqDispQty_A1.getValue()));
        }
        BigDecimal xxTotAmt = BigDecimal.ZERO;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prchReqLineStsCd_HD) && PRCH_REQ_LINE_STS.CANCELLED.equals(scrnMsg.A.no(i).prchReqLineStsCd_HD.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxUnitPrc_A1)) {
                xxTotAmt = xxTotAmt.add(scrnMsg.A.no(i).xxUnitPrc_A1.getValue());
            } else if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prchReqLineStsCd_HD)) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxUnitPrc_A1)) {
                xxTotAmt = xxTotAmt.add(scrnMsg.A.no(i).xxUnitPrc_A1.getValue());
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotAmt, xxTotAmt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineNum).prchReqDispQty_HD, scrnMsg.A.no(lineNum).prchReqDispQty_A1);
    }
}
