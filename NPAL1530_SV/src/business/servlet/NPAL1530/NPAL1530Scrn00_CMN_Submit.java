/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1530;

import static business.servlet.NPAL1530.constant.NPAL1530Constant.BIZ_APP_ID;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_DMND_CTOFF_DAYS_AOT;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_NM_MRP_PLN_NM;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_NM_RTL_SWH_CD;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_NM_RTL_WH_CD;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.DISPLAY_SPLY_CTOFF_DAYS_AOT;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.PLAN_NAME;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.SUB_WAREHOUSE;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.NPAM0079E;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.NPAM1494E;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.ZZM9000E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1530.NPAL1530CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Submit
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/23/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1530Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;

        // mandatory check
        if (PLAN_NAME.equals(scrnMsg.xxCmntTxt_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.mrpPlnNm)) {
                scrnMsg.mrpPlnNm.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_MRP_PLN_NM});
            }
        }
        if (SUB_WAREHOUSE.equals(scrnMsg.xxCmntTxt_SL.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
                scrnMsg.rtlWhCd.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_RTL_WH_CD});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd)) {
                scrnMsg.rtlSwhCd.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_RTL_SWH_CD});
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.dmndCtoffDaysAot)) {
            if (BigDecimal.ZERO.compareTo(scrnMsg.dmndCtoffDaysAot.getValue()) > 0) {
                scrnMsg.dmndCtoffDaysAot.setErrorInfo(1, NPAM1494E, new String[] {DISPLAY_DMND_CTOFF_DAYS_AOT});
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.splyCtoffDaysAot)) {
            if (BigDecimal.ZERO.compareTo(scrnMsg.splyCtoffDaysAot.getValue()) > 0) {
                scrnMsg.splyCtoffDaysAot.setErrorInfo(1, NPAM1494E, new String[] {DISPLAY_SPLY_CTOFF_DAYS_AOT});
            }
        }
        // date check
        if (ZYPCommonFunc.hasValue(scrnMsg.dmndCtoffDt)) {
            if (scrnMsg.slsDt.getValue().compareTo(scrnMsg.dmndCtoffDt.getValue()) > 0) {
                scrnMsg.dmndCtoffDt.setErrorInfo(1, NPAM0079E);
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.splyCtoffDt)) {
            if (scrnMsg.slsDt.getValue().compareTo(scrnMsg.splyCtoffDt.getValue()) > 0) {
                scrnMsg.splyCtoffDt.setErrorInfo(1, NPAM0079E);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.dmndCtoffDt);
        scrnMsg.addCheckItem(scrnMsg.dmndCtoffDaysAot);
        scrnMsg.addCheckItem(scrnMsg.splyCtoffDt);
        scrnMsg.addCheckItem(scrnMsg.splyCtoffDaysAot);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;

        NPAL1530CMsg bizMsg = new NPAL1530CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;
        NPAL1530CMsg bizMsg  = (NPAL1530CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }

        scrnMsg.putErrorScreen();

    }
}
