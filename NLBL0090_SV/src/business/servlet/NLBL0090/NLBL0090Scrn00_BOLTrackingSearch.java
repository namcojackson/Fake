/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.BIZ_APP_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.NLBM1296E;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.SRCH_FUNCTION_ID;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0090.NLBL0090CMsg;
import business.servlet.NLBL0090.common.NLBL0090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 * 2014/01/13   CSAI            K.Lee           Update          QC3321
 *</pre>
 */
public class NLBL0090Scrn00_BOLTrackingSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        // InputCheck
        scrnMsg.addCheckItem(scrnMsg.bolNum_H1);
        scrnMsg.addCheckItem(scrnMsg.proNum_H1);
        scrnMsg.addCheckItem(scrnMsg.carrCd_H2);
        scrnMsg.addCheckItem(scrnMsg.whCd_H1);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H2);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H2);
        scrnMsg.addCheckItem(scrnMsg.podStsTpForScrCd_H2);
        scrnMsg.addCheckItem(scrnMsg.podStsCd_H2);
        scrnMsg.addCheckItem(scrnMsg.trxHdrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.podStsDt_H1);
        scrnMsg.addCheckItem(scrnMsg.podStsDt_H2);
        scrnMsg.putErrorScreen();

        // Date Mandatory input check. If one has value, other one
        // must have value, too.
        if (!ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H2)) {
            scrnMsg.podStsDt_H1.setErrorInfo(1, "NLBM0008E");
            scrnMsg.addCheckItem(scrnMsg.podStsDt_H1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H1) && !ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H2)) {
            scrnMsg.podStsDt_H2.setErrorInfo(1, "NLBM0008E");
            scrnMsg.addCheckItem(scrnMsg.podStsDt_H2);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H2) && !ZYPCommonFunc.hasValue(scrnMsg.xxDtTpCd_H2)) {
            scrnMsg.xxDtTpCd_H2.setErrorInfo(1, "NLBM0008E");
            scrnMsg.addCheckItem(scrnMsg.xxDtTpCd_H2);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H1) && !ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H2) && ZYPCommonFunc.hasValue(scrnMsg.xxDtTpCd_H2)) {
            scrnMsg.podStsDt_H1.setErrorInfo(1, "NLBM0001E");
            scrnMsg.addCheckItem(scrnMsg.podStsDt_H1);
        }

        // When both have value, check date to be from <= to.
        if (ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.podStsDt_H2)) {
            // Date check
            if (ZYPDateUtil.compare(scrnMsg.podStsDt_H1.getValue(), scrnMsg.podStsDt_H2.getValue()) == 1) {
                scrnMsg.podStsDt_H1.setErrorInfo(1, "NLBM0020E", new String[] {"Date", "Date" });
                scrnMsg.addCheckItem(scrnMsg.podStsDt_H1);
            }
            // Date Range check
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Date date = sdf.parse(scrnMsg.podStsDt_H1.getValue());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.MONTH, 3);
                calendar.add(Calendar.DATE, -1);

                if (ZYPDateUtil.getDiffDays(scrnMsg.podStsDt_H2.getValue(), sdf.format(calendar.getTime())) > 0) {
                    scrnMsg.podStsDt_H1.setErrorInfo(1, NLBM1296E, new String[] {"3 Month" });
                    scrnMsg.addCheckItem(scrnMsg.podStsDt_H1);
                }
            } catch (ParseException e) {
                scrnMsg.podStsDt_H1.setErrorInfo(1, NLBM1296E, new String[] {"3 Month" });
                scrnMsg.addCheckItem(scrnMsg.podStsDt_H1);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        NLBL0090CMsg bizMsg = new NLBL0090CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(SRCH_FUNCTION_ID);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        NLBL0090CMsg bizMsg = (NLBL0090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Contorol Tabs
        NLBL0090CommonLogic.showBOLTrckngTab(scrnMsg, this);

        // Combine Cells
        NLBL0090CommonLogic.setTblBackColorAndUnitCellsA(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.carrCd_H2);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H2);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H2);
        scrnMsg.addCheckItem(scrnMsg.whCd_H1);
        scrnMsg.putErrorScreen();

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.xxRadioBtn_A1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.bolNum_H1);
        }

        // set success message
        scrnMsg.setMessageInfo("NZZM0002I");
    }

}
