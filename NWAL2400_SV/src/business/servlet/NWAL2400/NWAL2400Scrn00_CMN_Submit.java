/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2400;

import static business.servlet.NWAL2400.constant.NWAL2400Constant.BIZ_APP_ID;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.NWAM8456E;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.NWAM0223E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2400.NWAL2400CMsg;
import business.servlet.NWAL2400.common.NWAL2400CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 * 2016/08/01   CITS            S.Tanikawa      Update          QC#11099
 * 2016/08/02   CITS            S.Tanikawa      Update          QC#10537
 *</pre>
 */
public class NWAL2400Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;

        // checks the mandatory fields.
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).coaBrCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).coaBrDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlDlrCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlDivCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsOrdCatgDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsOrdTpDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrGrpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).billToCustCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);

            // Date check
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsRtlDlrInfoPk_A) 
                        && ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A.getValue(), ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())) < 0) {
                    scrnMsg.A.no(i).effFromDt_A.setErrorInfo(1, NWAM8456E);
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A)
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A)) {
                if (ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A.getValue(), scrnMsg.A.no(i).effThruDt_A.getValue()) > 0) {
                    scrnMsg.A.no(i).effThruDt_A.setErrorInfo(1, NWAM0223E, new String[] {"End Date", "Start Date"});
                }
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;

        NWAL2400CMsg bizMsg = new NWAL2400CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;
        NWAL2400CMsg bizMsg  = (NWAL2400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2400CommonLogic.ctrlDetailButton(this, scrnMsg);
        NWAL2400CommonLogic.setTableScreen(scrnMsg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).coaBrCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).coaBrDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlDlrCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlDivCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsOrdCatgDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsOrdTpDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrGrpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).billToCustCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
        }
        scrnMsg.putErrorScreen();

        // set focus
        scrnMsg.setFocusItem(scrnMsg.rtlDlrCd);
    }
}
