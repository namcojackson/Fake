/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import static business.servlet.NFCL0770.constant.NFCL0770Constant.AR_CANCEL_LIMIT_MONTH;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_MODE_CANCEL;
import static business.servlet.NFCL0770.constant.NFCL0770Constant.SCRN_STATUS_N;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;
import business.servlet.NFCL0770.constant.NFCL0770Constant.DATE_INFO;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 *</pre>
 */
public class NFCL0770Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {


        NFCL0770CMsg bizMsg = null;

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;


        NFCL0770CommonLogic.controlTableBegin_NFCL0770_A(this, scrnMsg);

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770_INIT(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {


        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        if (null != bizMsg) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NFCL0770CommonLogic.initialize(this, scrnMsg);

        // Details position Initialize
        scrnMsg.xxListNum_LX.setValue(0);
        scrnMsg.xxListNum_LY.setValue(0);

        NFCL0770CommonLogic.commonBtnControl_NFCL0770_INIT(this, scrnMsg);

        NFCL0770CommonLogic.busBtnControl_NFCL0770_INIT(this, scrnMsg);

        if (null != bizMsg) {
            if ("E".equals(bizMsg.getMessageKind()) && SCRN_STATUS_N.equals(scrnMsg.xxRsltStsCd_H1.getValue())) {
                NFCL0770CommonLogic.setErrorScreen_NFCL0770(this, scrnMsg);

            } else {

                if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
                    // get GLdate
                    String cashAppGlDtYm = scrnMsg.cashAppDt_BK.getValue().substring(DATE_INFO.YEAR_START_POS.getValue(), DATE_INFO.MONTH_END_POS.getValue());
                    String acctYmOrg = scrnMsg.acctYrMth.getValue();

                    BigDecimal limitMonthsBigDecimal = ZYPCodeDataUtil.getNumConstValue(AR_CANCEL_LIMIT_MONTH, bizMsg.glblCmpyCd_H1.getValue());

                    int limitMonths = limitMonthsBigDecimal.intValue();

                    String acctYm = NFCL0770CommonLogic.getLimitGLDate(acctYmOrg, limitMonths);
                    if (acctYm != null && acctYm.length() > DATE_INFO.MONTH_END_POS.getValue()) {
                        acctYm = acctYm.substring(DATE_INFO.YEAR_START_POS.getValue(), DATE_INFO.MONTH_END_POS.getValue());
                    }

                    if (null != acctYm && acctYm.compareTo(cashAppGlDtYm) > 0) {
                        NFCL0770CommonLogic.setCancelScreen_NFCL0770Error(this, scrnMsg);
                    } else {
                        NFCL0770CommonLogic.setCancelScreen_NFCL0770(this, scrnMsg);
                    }
                    scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox);

                } else {
                    NFCL0770CommonLogic.setEntryScreen_NFCL0770(this, scrnMsg);
                }

                setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
            }

            NFCL0770CommonLogic.setRowBg(scrnMsg);

        } else {
            NFCL0770CommonLogic.setErrorScreen_NFCL0770(this, scrnMsg);
        }
        NFCL0770CommonLogic.setCheckAllBtn(this, scrnMsg);

        if (!SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL0770CommonLogic.protectAddDetailLine(scrnMsg, this);
        } else {
            if ("E".equals(bizMsg.getMessageKind())) {
                this.setButtonEnabled("Check_All", false);
                this.setButtonEnabled("Un_Check_All", false);
            }
        }
        if (SCRN_MODE_CANCEL.equals(scrnMsg.xxModeInd_H1.getValue())) {
            NFCL0770CommonLogic.protectCancelSubmit(scrnMsg, this);
        }

        scrnMsg.putErrorScreen();
        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);
        NFCL0770CommonLogic.setAppFracDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.payerCustCd);
    }
}
