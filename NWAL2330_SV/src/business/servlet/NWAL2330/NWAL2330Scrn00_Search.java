/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2330;

import static business.servlet.NWAL2330.constant.NWAL2330Constant.BIZ_ID;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.NUM_CONST_INV_TERM;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.NWAM0001E;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.NWAM0756E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2330.NWAL2330CMsg;
import business.servlet.NWAL2330.common.NWAL2330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2330Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NWAL2330Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2330BMsg scrnMsg = (NWAL2330BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.cpoSrcTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H2);
        scrnMsg.addCheckItem(scrnMsg.billToCustAcctCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.invNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H3);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.ordDt_H1);
        scrnMsg.addCheckItem(scrnMsg.ordDt_H2);
        scrnMsg.addCheckItem(scrnMsg.invDt_H1);
        scrnMsg.addCheckItem(scrnMsg.invDt_H2);

        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.cpoSrcTpCd_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.soNum_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H2)
            && !ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctCd_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgCd_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.invNum_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm_H3)
            && !ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.ordDt_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.ordDt_H2)
            && !ZYPCommonFunc.hasValue(scrnMsg.invDt_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.invDt_H2)) {
            scrnMsg.setMessageInfo(NWAM0001E);
            throw new  EZDFieldErrorException();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.invDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.invDt_H2)) {
            BigDecimal dtNum = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_INV_TERM, getGlobalCompanyCode());
            String fromDt = scrnMsg.invDt_H1.getValue();
            String toDt = scrnMsg.invDt_H2.getValue();

            int dtDiff = ZYPDateUtil.getDiffDays(toDt, fromDt);
            if (ZYPCommonFunc.hasValue(dtNum)) {
                if (dtDiff > dtNum.intValue()) {
                    // Mod 20160411 R.Nakamura Start
                    // scrnMsg.invDt_H1.setErrorInfo(1, NWAM0756E, new String[] {scrnMsg.invDt_H1.getNameForMessage()});
                    scrnMsg.invDt_H1.setErrorInfo(1, NWAM0756E, new String[] {String.valueOf(dtNum)});
                    // Mod 20160411 R.Nakamura End
                    scrnMsg.addCheckItem(scrnMsg.invDt_H1);
                }
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2330BMsg scrnMsg = (NWAL2330BMsg) bMsg;

        NWAL2330CMsg bizMsg = new NWAL2330CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2330BMsg scrnMsg = (NWAL2330BMsg) bMsg;
        NWAL2330CMsg bizMsg  = (NWAL2330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NWAL2330CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A1");
        NWAL2330CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A2");
        NWAL2330CommonLogic.scrnProtect(scrnMsg, true);
        // focus set
        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H1);
    }
}
