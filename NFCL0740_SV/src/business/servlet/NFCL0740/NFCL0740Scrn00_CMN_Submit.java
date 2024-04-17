/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0740;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0740.NFCL0740CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Write-Off Request Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         T.Tanaka        Create          N/A
 * 2016/03/01   CSAI            K.Uramori       Update          Complete Submit Function
 * 2016/07/08   Hitachi         K.Kojima        Update          QC#8784
 *</pre>
 */
public class NFCL0740Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.arAdjRsnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.arAdjTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.dealRmngBalGrsAmt_H1);
        scrnMsg.addCheckItem(scrnMsg.dealRmngBalGrsAmt_H2);

        if (ZYPCommonFunc.hasValue(scrnMsg.dealRmngBalGrsAmt_H1.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.dealRmngBalGrsAmt_H2.getValue())) {
            if (scrnMsg.dealRmngBalGrsAmt_H1.getValue().compareTo(scrnMsg.dealRmngBalGrsAmt_H2.getValue()) > 0) {
                scrnMsg.dealRmngBalGrsAmt_H2.setErrorInfo(1, "NFCM0023E");
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H1) && ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H2)) {
            if (scrnMsg.arTrxNum_H1.getValue().compareTo(scrnMsg.arTrxNum_H2.getValue()) > 0) {
                scrnMsg.arTrxNum_H2.setErrorInfo(1, "NFCM0023E");
            }
        } else if (ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H2)) {
            scrnMsg.arTrxNum_H2.setErrorInfo(1, "ZZM9000E", new String[] {"To Invoice Number" });
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H1) && ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H2)) {
            scrnMsg.arTrxNum_H1.setErrorInfo(1, "ZZM9000E", new String[] {"From Invoice Number" });
        }

        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) &&
        // ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H2)) {
        // if
        // (scrnMsg.dsAcctNum_H1.getValue().compareTo(scrnMsg.dsAcctNum_H2.getValue())
        // > 0) {
        // scrnMsg.dsAcctNum_H2.setErrorInfo(1, "NFCM0023E");
        // }
        // } else if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) &&
        // !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H2)) {
        // scrnMsg.dsAcctNum_H2.setErrorInfo(1, "ZZM9000E", new
        // String[] {"To Customer Number" });
        // } else if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) &&
        // ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H2)) {
        // scrnMsg.dsAcctNum_H1.setErrorInfo(1, "ZZM9000E", new
        // String[] {"From Customer Number" });
        // }
        // END 2016/07/08 K.Kojima [QC#8784,DEL]

        if (ZYPCommonFunc.hasValue(scrnMsg.invDueDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.invDueDt_H2)) {
            if (ZYPDateUtil.compare(scrnMsg.invDueDt_H1.getValue(), scrnMsg.invDueDt_H2.getValue()) > 0) {
                scrnMsg.invDueDt_H2.setErrorInfo(1, "NFCM0023E");
            }
        } else if (ZYPCommonFunc.hasValue(scrnMsg.invDueDt_H1) && !ZYPCommonFunc.hasValue(scrnMsg.invDueDt_H2)) {
            scrnMsg.invDueDt_H2.setErrorInfo(1, "ZZM9000E", new String[] {"To Date" });
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.invDueDt_H1) && ZYPCommonFunc.hasValue(scrnMsg.invDueDt_H2)) {
            scrnMsg.invDueDt_H1.setErrorInfo(1, "ZZM9000E", new String[] {"From Date" });
        }
        scrnMsg.addCheckItem(scrnMsg.invDueDt_H1);
        scrnMsg.addCheckItem(scrnMsg.invDueDt_H2);
        //---- start add 2016/03/08
        scrnMsg.addCheckItem(scrnMsg.arTrxNum_H1);
        scrnMsg.addCheckItem(scrnMsg.arTrxNum_H2);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        // START 2016/07/08 K.Kojima [QC#8784,DEL]
        // scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H2);
        // END 2016/07/08 K.Kojima [QC#8784,DEL]
        //---- end 2016/03/08

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;

        NFCL0740CMsg bizMsg = new NFCL0740CMsg();
        bizMsg.setBusinessID("NFCL0740");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0740BMsg scrnMsg = (NFCL0740BMsg) bMsg;
        NFCL0740CMsg bizMsg  = (NFCL0740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.putErrorScreen();

        // when it's "Report Only" request, goes to NFCL0760
        if (scrnMsg.xxRadioBtn.getValueInt() == 1 && scrnMsg.getMessageCode().equals("")) {
            setResult("RsultDetail");

            // set parameter
            Object[] param = new Object[4];
            param[0] = scrnMsg.arWrtOffRqstPk;
            param[1] = scrnMsg.wrtOffRqstGrpCd;
            param[2] = scrnMsg.usrId;
            scrnMsg.arWrtOffRqstTpCd.setValue(AR_WRT_OFF_RQST_TP.USER_REQUEST);
            param[3] = scrnMsg.arWrtOffRqstTpCd;
            setArgForSubScreen(param);
        } else {
            setResult("StaySameScrn");
        }
        scrnMsg.setFocusItem(scrnMsg.arAdjRsnCd_H1);
    }
}
