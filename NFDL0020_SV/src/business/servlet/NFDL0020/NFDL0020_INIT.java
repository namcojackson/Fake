/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0020.NFDL0020CMsg;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_TASK_STS;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 * 2016/07/01   Hitachi         K.Kojima        Update          QC#10429
 * 2016/07/27   Hitachi         K.Kojima        Update          QC#10199
 * 2016/08/22   Hitachi         K.Kojima        Update          QC#13331
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/01/15   Hitachi         Y.Takeno        Update          QC#21078
 * 2018/03/15   Hitachi         J.Kim           Update          QC#20945
 * 2018/04/03   Hitachi         J.Kim           Update          QC#24729
 * 2018/05/11   Hitachi         J.Kim           Update          QC#21426
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/06/15   Hitachi         Y.Takeno        Update          QC#24792
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 * 2018/07/23   Hitachi         Y.Takeno        Update          QC#25780
 * 2018/07/25   Hitachi         Y.Takeno        Update          QC#25767
 * 2019/02/01   Hitachi         Y.Takeno        Update          QC#30161
 * 2022/11/01   Hitachi         T.Doi           Update          QC#60415
 *</pre>
 */
public class NFDL0020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFDL0020");
        // End   2016/12/27 H.Ikeda [QC#12865,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        NFDL0020CMsg bizMsg = new NFDL0020CMsg();
        bizMsg.setBusinessID("NFDL0020");
        bizMsg.setFunctionCode("20");

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0) {
            if (params.length == 1) {
                // START 2018/01/15 [QC#21078, MOD]
                if (params[0] instanceof String) {
                    // from NYEL8810
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, (String)params[0]);
                } else {
                    EZDBStringItem param_01 = (EZDBStringItem) params[0];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, param_01);
                }
                // END   2018/01/15 [QC#21078, MOD]
            }
            if (params.length == 2) {
                EZDBStringItem param_01 = (EZDBStringItem) params[0];
                EZDBStringItem param_02 = (EZDBStringItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, param_01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_H, param_02);
            }
            // START 2016/08/22 K.Kojima [QC#13331,ADD]
            if (params.length == 3) {
                EZDBStringItem param_01 = (EZDBStringItem) params[0];
                EZDBBigDecimalItem param_03 = (EZDBBigDecimalItem) params[2];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, param_01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cltTaskPk_PM, param_03);
            }
            if (params.length == 4) {
                EZDBStringItem param_01 = (EZDBStringItem) params[0];
                EZDBBigDecimalItem param_04 = (EZDBBigDecimalItem) params[3];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, param_01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cltStrgyWrkItemTrxPk_PM, param_04);
            }
            // END 2016/08/22 K.Kojima [QC#13331,ADD]
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFDL0020CommonLogic.initialize(this, scrnMsg);

        // START 2018/07/23 [QC#25780, MOD]
        // scrnMsg.xxYesNoCd_GH.setValue("0");
        if (ZYPCommonFunc.hasValue(scrnMsg.cltTaskPk_PM)) {
            int selectIdx = -1;
            for (int i = 0 ; i < scrnMsg.G.getValidCount(); i++) {
                if (scrnMsg.cltTaskPk_PM.getValue().equals(scrnMsg.G.no(i).cltTaskPk_GD.getValue())) {
                    selectIdx = i;
                    break;
                }
            }
            if (selectIdx == -1) {
                return;
            }

            if (scrnMsg.G.no(selectIdx).cltTaskStsCd_GD.getValue().equals(CLT_TASK_STS.OPEN)) {
                scrnMsg.xxYesNoCd_GH.setValue("1");
                NFDL0020CommonLogic.setTaskInputProtectFalse(this, scrnMsg);
            } else {
                scrnMsg.xxYesNoCd_GH.setValue("0");
                NFDL0020CommonLogic.setTaskInputProtectTrue(this, scrnMsg);
            }
            scrnMsg.cltTaskPk_PM.clear();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.cltStrgyWrkItemTrxPk_PM)) {
            NFDL0020CommonLogic.setTabStrategyEnabled(this, scrnMsg);
            scrnMsg.cltStrgyWrkItemTrxPk_PM.clear();
        }
        // END   2018/07/23 [QC#25780, MOD]
        scrnMsg.xxYesNoCd_FH.setValue("0");
    }


    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        // START 2016/06/22 K.Kojima [QC#10529,ADD]
        // "Note" Tab
        scrnMsg.colNoteSubjTxt_FH.setNameForMessage("Name");
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // scrnMsg.dtlNoteTxt_FH.setNameForMessage("Note");
        scrnMsg.xxMlBodyTxt_FH.setNameForMessage("Note");
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        // "Task" Tab
        scrnMsg.cltTaskTpCd_GH.setNameForMessage("Type");
        scrnMsg.cltTaskStsCd_GH.setNameForMessage("Status");
        scrnMsg.cltTaskSubjTxt_GH.setNameForMessage("Name");
        scrnMsg.cltTaskOwnrId_GH.setNameForMessage("Owner");
        scrnMsg.cltTaskRwsdDt_GH.setNameForMessage("Start Date");
        scrnMsg.cltTaskRwedDt_GH.setNameForMessage("End Date");
        scrnMsg.cltTaskCtacNm_GH.setNameForMessage("Cont Cust");
        scrnMsg.cltTaskCtacPhoNum_GH.setNameForMessage("Cont Num");
        scrnMsg.cltTaskDescTxt_GH.setNameForMessage("Note");
        // END 2016/06/22 K.Kojima [QC#10529,ADD]
        // START 2016/07/27 K.Kojima [QC#10199,ADD]
        scrnMsg.cltTaskRwsdDt_GF.setNameForMessage("Date(From)");
        scrnMsg.cltTaskRwsdDt_GT.setNameForMessage("Date(To)");
        scrnMsg.cltTaskSubjTxt_GS.setNameForMessage("Name");
        // END 2016/07/27 K.Kojima [QC#10199,ADD]

        scrnMsg.dealRmngBalGrsAmt_H1.setAppFracDigit(2);
        scrnMsg.dealRmngBalGrsAmt_H2.setAppFracDigit(2);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //scrnMsg.invTotDealNetAmt_H.setAppFracDigit(2);
        scrnMsg.invTotDealNetAmt_H.setAppFracDigit(0);
        // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
        scrnMsg.rcptAmt_H.setAppFracDigit(2);
        // START 2017/08/07 T.Tsuchida [QC#19576,DEL]
        //scrnMsg.xxNum.setAppFracDigit(2);
        // END 2017/08/07 T.Tsuchida [QC#19576,DEL]
        scrnMsg.xxTotAmt_H1.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H2.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H3.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H4.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H5.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H6.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H7.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H8.setAppFracDigit(2);
        scrnMsg.xxTotAmt_H9.setAppFracDigit(2);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //scrnMsg.xxTotAmt_H0.setAppFracDigit(2);
        scrnMsg.dealCltDsptAmt_H.setAppFracDigit(2);
        scrnMsg.dealCltPrmsAmt_H.setAppFracDigit(2);
        // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

        // START 2018/06/15 [QC#24792, ADD]
        scrnMsg.dsContrNum_AH.setNameForMessage("Contract");
        scrnMsg.serNum_AH.setNameForMessage("Serial");
        scrnMsg.invDueDt_A1.setNameForMessage("Due Date(From)");
        scrnMsg.invDueDt_A2.setNameForMessage("Due Date(To)");
        scrnMsg.arCustRefNum_AH.setNameForMessage("Transaction #");
        scrnMsg.custIssPoNum_AH.setNameForMessage("PO Number");
        // END   2018/06/15 [QC#24792, ADD]

        // START 2018/02/01 [QC#30161, ADD]
        scrnMsg.dealRmngBalGrsAmt_AH.setAppFracDigit(2);
        // END   2018/02/01 [QC#30161, ADD]

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealNetSlsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealFrtAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealTaxAmt_A.setAppFracDigit(2);
            // START 2022/11/01 T.Doi [QC#60415, ADD]
            scrnMsg.A.no(i).addlChrgPrcDealAmt_A.setAppFracDigit(2);
            // END 2022/11/01 T.Doi [QC#60415, ADD]
            scrnMsg.A.no(i).dealOrigGrsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealCltPrmsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealCltDsptAmt_A.setAppFracDigit(2);
            // START 2016/07/01 K.Kojima [QC#10429,ADD]
            scrnMsg.A.no(i).arLateFeeAmt_A.setAppFracDigit(2);
            // END 2016/07/01 K.Kojima [QC#10429,ADD]
            // START 2018/05/11 J.Kim [QC#20945,ADD]
            scrnMsg.A.no(i).dealCltPrmsAmt_A1.setAppFracDigit(2);
            // END 2018/05/11 J.Kim [QC#20945,ADD]
        }

        // START 2018/03/15 J.Kim [QC#20945,ADD]
        scrnMsg.glDt_H1.setNameForMessage("Adjustment Date From");
        scrnMsg.glDt_H2.setNameForMessage("Adjustment Date To");
        scrnMsg.arTrxNum_H1.setNameForMessage("Transaction#");
        for (int j = 0; j < scrnMsg.H.length(); j++) {
            scrnMsg.H.no(j).dealApplyAmt_H.setAppFracDigit(2);
        }
        // END 2018/03/15 J.Kim [QC#20945,ADD]

        // START 2018/05/11 J.Kim [QC#21426,ADD]
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).cltWrkItemRwsdDt_DD.setNameForMessage("Request Start Date");
            scrnMsg.D.no(i).cltWrkItemRwedDt_DD.setNameForMessage("Request Comp Date");
            scrnMsg.D.no(i).cltWrkItemWsrdDt_DD.setNameForMessage("Actual Start Date");
            scrnMsg.D.no(i).cltWrkItemWerdDt_DD.setNameForMessage("Actual Comp Date");
        }
        // END 2018/05/11 J.Kim [QC#21426,ADD]

        // START 2017/08/07 T.Tsuchida [QC#19576,DEL]
        //for (int i = 0; i < scrnMsg.D.length(); i++) {
        //    scrnMsg.D.no(i).xxQty10Num_DD.setAppFracDigit(2);
        //}
        // END 2017/08/07 T.Tsuchida [QC#19576,DEL]

        // START 2018/05/16 [QC#24329,ADD]
        for (int j = 0; j < scrnMsg.J.length(); j++) {
            scrnMsg.J.no(j).ageCurAmt_J.setAppFracDigit(2);
            scrnMsg.J.no(j).age0130Amt_J.setAppFracDigit(2);
            scrnMsg.J.no(j).age3160Amt_J.setAppFracDigit(2);
            scrnMsg.J.no(j).age6190Amt_J.setAppFracDigit(2);
            scrnMsg.J.no(j).ageOverAmt_J.setAppFracDigit(2);
            scrnMsg.J.no(j).balTotAmt_J.setAppFracDigit(2);
        }
        // END 2018/05/16 [QC#24329,ADD]

        // START 2018/06/21 [QC#25080,ADD]
        scrnMsg.cratDt_FF.setNameForMessage("Date(From)");
        scrnMsg.cratDt_FT.setNameForMessage("Date(To)");
        scrnMsg.cltNoteTpCd_FS.setNameForMessage("Note Type");
        scrnMsg.xxMlBodyTxt_FS.setNameForMessage("Note");
        scrnMsg.cltNoteTpCd_FH.setNameForMessage("Note Type");
        scrnMsg.xxMlBodyTxt_FH.setNameForMessage("Note");
        // END   2018/06/21 [QC#25080,ADD]
    }
}
