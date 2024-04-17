/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/07/02   CITS            R.Kurahashi     Create          QC#56696
 * 2020/07/16   CITS            R.Kurahashi     Update          QC#56696-1
 *</pre>
 */
public class NFBL2040Scrn00_Amount_Calc extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        boolean hasErrLinesTab = false;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1)) {
                scrnMsg.A.no(i).apLineTpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Line Type" });
                hasErrLinesTab = true;
            }
            if (!scrnMsg.A.no(i).xxCmntTxt_A1.isInputProtected() && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxCmntTxt_A1)) {
                scrnMsg.A.no(i).xxCmntTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Charge Account" });
                hasErrLinesTab = true;
            }
            // START 2020/07/16 R.Kurahashi [QC#56696-1,ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxInvAmt_A1);
            // END 2020/07/16 R.Kurahashi [QC#56696-1,ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apBillQty_A1);
        }

        BigDecimal lineTotAmt = BigDecimal.ZERO;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (XX_LINE_TP_CD_INVALID.equals(scrnMsg.A.no(i).xxLineTpCd_A1.getValue()) //
                    || XX_LINE_TP_CD_CREDIT.equals(scrnMsg.A.no(i).xxLineTpCd_A1.getValue()) //
                    || "".equals(scrnMsg.A.no(i).xxLineTpCd_A1.getValue()) //
            ) {
                continue;
            }

            BigDecimal lineAmt = scrnMsg.A.no(i).xxInvAmt_A1.getValue();
            BigDecimal apBillQty = scrnMsg.A.no(i).apBillQty_A1.getValue();
            BigDecimal dealGrsUnitPrcAmt = scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue();

            // START 2020/07/16 R.Kurahashi [QC#56696-1,ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
                    && !ZYPCommonFunc.hasValue(apBillQty)) {
                apBillQty = BigDecimal.ZERO;
                scrnMsg.A.no(i).apBillQty_A1.setValue(apBillQty);
                
            }
            // END 2020/07/16 R.Kurahashi [QC#56696-1,ADD]
            
            if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {

                if (AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (apBillQty == null || BigDecimal.ZERO.compareTo(apBillQty) < 0) {
                        scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0231E);
                        hasErrLinesTab = true;
                    }

                    if (dealGrsUnitPrcAmt == null || BigDecimal.ZERO.compareTo(dealGrsUnitPrcAmt) > 0) {
                        scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setErrorInfo(1, NFBM0279E);
                        hasErrLinesTab = true;
                    }

                } else if (AP_LINE_TP.TAX.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue()) || AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (lineAmt == null || BigDecimal.ZERO.compareTo(lineAmt) <= 0) {
                        scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0278E);
                        hasErrLinesTab = true;
                    }
                }

            } else if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {

                if (AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (apBillQty == null || BigDecimal.ZERO.compareTo(apBillQty) > 0) {

                        scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0273E);
                        hasErrLinesTab = true;
                    }

                    if (dealGrsUnitPrcAmt == null || BigDecimal.ZERO.compareTo(dealGrsUnitPrcAmt) > 0) {
                        scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setErrorInfo(1, NFBM0279E);
                        hasErrLinesTab = true;
                    }

                } else if (AP_LINE_TP.TAX.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue()) || AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (lineAmt == null || BigDecimal.ZERO.compareTo(lineAmt) >= 0) {
                        scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0274E);
                        hasErrLinesTab = true;
                    }
                }
            }

            if (lineAmt != null) {
                lineTotAmt = lineTotAmt.add(lineAmt);
            }
        }

        if (hasErrLinesTab) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_02, TAB_LINES);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
            NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
            scrnMsg.putErrorScreen();
        }

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NFBM0044E, new String[] {"Lines Data" });
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
