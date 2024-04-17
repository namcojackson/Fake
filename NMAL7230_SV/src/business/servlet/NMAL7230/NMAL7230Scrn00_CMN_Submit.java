/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.BIZ_ID;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM0042E;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM0043E;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM8075E;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM8409E;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NMAM8410E;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.NZZM0002I;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.POSTALCODE_PATTERN;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.SCRN_ID_00;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7230.NMAL7230CMsg;
import business.servlet.NMAL7230.common.NMAL7230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL7230Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        NMAL7230CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7230CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();

        boolean isErr = false;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // detail combination check
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsAcctNm_A1)) {
                // Customer
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcGrpNm_A1)) {
                    scrnMsg.A.no(i).prcGrpNm_A1.setErrorInfo(1, NMAM8409E, new String[] {scrnMsg.A.no(i).dsAcctNm_A1.getNameForMessage(), scrnMsg.A.no(i).prcGrpNm_A1.getNameForMessage()});
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).prcGrpNm_A1);
                    isErr = true;
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).shipToCtryCd_A1)
                    || ZYPCommonFunc.hasValue(scrnMsg.A.no(i).shipToStCd_A1)) {
                // Country & State
                if (NMAL7230CommonLogic.checkCombinationMandatoryForShipto(scrnMsg.A.no(i), scrnMsg)) {
                    isErr = true;
                }
            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).shipToFromPostCd_A1)
                    || ZYPCommonFunc.hasValue(scrnMsg.A.no(i).shipToThruPostCd_A1)) {
                // Portal Code From/To
                boolean isFormatNoErr = true;
                if (NMAL7230CommonLogic.checkCombinationMandatoryForPostalCode(scrnMsg.A.no(i), scrnMsg)) {
                    isErr = true;
                } else {
                    if (!NMAL7230CommonLogic.checkPortalCodeFormat(scrnMsg.A.no(i).shipToFromPostCd_A1.getValue())) {
                        scrnMsg.A.no(i).shipToFromPostCd_A1.setErrorInfo(1, NMAM8075E, new String[] {POSTALCODE_PATTERN});
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToFromPostCd_A1);
                        isFormatNoErr = false;
                    }

                    if (!NMAL7230CommonLogic.checkPortalCodeFormat(scrnMsg.A.no(i).shipToThruPostCd_A1.getValue())) {
                        scrnMsg.A.no(i).shipToThruPostCd_A1.setErrorInfo(1, NMAM8075E, new String[] {POSTALCODE_PATTERN});
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToThruPostCd_A1);
                        isFormatNoErr = false;
                    }

                    if (isFormatNoErr) {
                        BigDecimal fromPostCode = new BigDecimal(NMAL7230CommonLogic.convertdigit5to10(scrnMsg.A.no(i).shipToFromPostCd_A1.getValue().replaceAll("-", "")));
                        BigDecimal thruPostCode = new BigDecimal(NMAL7230CommonLogic.convertdigit5to10(scrnMsg.A.no(i).shipToThruPostCd_A1.getValue().replaceAll("-", "")));

                        if (ZYPCommonFunc.hasValue(fromPostCode)
                                && ZYPCommonFunc.hasValue(thruPostCode)
                                && fromPostCode.compareTo(thruPostCode) > 0) {
                            scrnMsg.A.no(i).shipToFromPostCd_A1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).shipToFromPostCd_A1.getNameForMessage(), scrnMsg.A.no(i).shipToThruPostCd_A1.getNameForMessage()});
                            scrnMsg.A.no(i).shipToThruPostCd_A1.setErrorInfo(1, NMAM0042E, new String[] {scrnMsg.A.no(i).shipToThruPostCd_A1.getNameForMessage(), scrnMsg.A.no(i).shipToFromPostCd_A1.getNameForMessage()});
                            scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToFromPostCd_A1);
                            scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToThruPostCd_A1);
                            isErr = true;
                        }
                    } else {
                        isErr = true;
                    }
                }
            } else {
                scrnMsg.A.no(i).shipToCtryCd_A1.setErrorInfo(1, NMAM8410E, new String[] {scrnMsg.A.no(i).shipToCtryCd_A1.getNameForMessage() + "/" + scrnMsg.A.no(i).shipToStCd_A1.getNameForMessage() + " or " + scrnMsg.A.no(i).shipToFromPostCd_A1.getNameForMessage() + "/" + scrnMsg.A.no(i).shipToThruPostCd_A1.getNameForMessage()});
                scrnMsg.A.no(i).shipToStCd_A1.setErrorInfo(1, NMAM8410E, new String[] {scrnMsg.A.no(i).shipToCtryCd_A1.getNameForMessage() + "/" + scrnMsg.A.no(i).shipToStCd_A1.getNameForMessage() + " or " + scrnMsg.A.no(i).shipToFromPostCd_A1.getNameForMessage() + "/" + scrnMsg.A.no(i).shipToThruPostCd_A1.getNameForMessage()});
                scrnMsg.A.no(i).shipToFromPostCd_A1.setErrorInfo(1, NMAM8410E, new String[] {scrnMsg.A.no(i).shipToCtryCd_A1.getNameForMessage() + "/" + scrnMsg.A.no(i).shipToStCd_A1.getNameForMessage() + " or " + scrnMsg.A.no(i).shipToFromPostCd_A1.getNameForMessage() + "/" + scrnMsg.A.no(i).shipToThruPostCd_A1.getNameForMessage()});
                scrnMsg.A.no(i).shipToThruPostCd_A1.setErrorInfo(1, NMAM8410E, new String[] {scrnMsg.A.no(i).shipToCtryCd_A1.getNameForMessage() + "/" + scrnMsg.A.no(i).shipToStCd_A1.getNameForMessage() + " or " + scrnMsg.A.no(i).shipToFromPostCd_A1.getNameForMessage() + "/" + scrnMsg.A.no(i).shipToThruPostCd_A1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToCtryCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToStCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToFromPostCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToThruPostCd_A1);
                isErr = true;
            }

            // Effective Date Check
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A1)
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)
                    && ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A1.getValue(), scrnMsg.A.no(i).effThruDt_A1.getValue()) > 0) {
                scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).effFromDt_A1.getNameForMessage(), scrnMsg.A.no(i).effThruDt_A1.getNameForMessage()});
                scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM0042E, new String[] {scrnMsg.A.no(i).effThruDt_A1.getNameForMessage(), scrnMsg.A.no(i).effFromDt_A1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
                isErr = true;
            }
        }

        if (isErr) {
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        NMAL7230CMsg bizMsg = new NMAL7230CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;
        NMAL7230CMsg bizMsg = (NMAL7230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            NMAL7230CommonLogic.addCheckItemForHeader(scrnMsg);
            // Detail
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NMAL7230_ABMsg scrnLineMsg = scrnMsg.A.no(i);

                scrnMsg.addCheckItem(scrnLineMsg.xxChkBox_A1);
                scrnMsg.addCheckItem(scrnLineMsg.lineBizTpDescTxt_A1);
                scrnMsg.addCheckItem(scrnLineMsg.dsAcctNm_A1);
                scrnMsg.addCheckItem(scrnLineMsg.prcGrpNm_A1);
                scrnMsg.addCheckItem(scrnLineMsg.frtZoneNum_A1);
                scrnMsg.addCheckItem(scrnLineMsg.shipToStCd_A1);
                scrnMsg.addCheckItem(scrnLineMsg.shipToCtryCd_A1);
                scrnMsg.addCheckItem(scrnLineMsg.shipToFromPostCd_A1);
                scrnMsg.addCheckItem(scrnLineMsg.shipToThruPostCd_A1);
                scrnMsg.addCheckItem(scrnLineMsg.effFromDt_A1);
                scrnMsg.addCheckItem(scrnLineMsg.effThruDt_A1);
            }
            scrnMsg.putErrorScreen();
        }

        NMAL7230CommonLogic.controlScreen(this, scrnMsg);

        if (0 < scrnMsg.A.getValidCount()) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).lineBizTpDescTxt_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.lineBizTpCd);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
