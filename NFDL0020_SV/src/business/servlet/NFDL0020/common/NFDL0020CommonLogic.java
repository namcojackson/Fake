/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_STRGY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.servlet.NFDL0020.NFDL0020BMsg;
import business.servlet.NFDL0020.NFDL0020_PBMsgArray;

import parts.servletcommon.EZDCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2010   Fujitsu         S.Tsuboi        Create          N/A
 * 06/14/2010   Fujitsu         S.Yoshida       Update          Def.7056
 * 2016/06/16   Hitachi         K.Kojima        Update          QC#10200
 * 2016/06/17   Hitachi         K.Kojima        Update          QC#10170
 * 2016/06/17   Hitachi         K.Kojima        Update          QC#10191
 * 2016/06/17   Hitachi         K.Kojima        Update          QC#10198
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 * 2016/06/27   Hitachi         K.Kojima        Update          QC#10781
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10337
 * 2016/07/15   Hitachi         K.Kojima        Update          QC#11478
 * 2016/08/23   Hitachi         K.Kojima        Update          QC#13204
 * 2016/08/30   Hitachi         K.Kojima        Update          QC#10429
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13097
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 * 2017/01/18   Fujitsu         T.Murai         Update          QC#16809
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/03/15   Hitachi         J.Kim           Update          QC#20945
 * 2018/03/23   Hitachi         J.Kim           UPdate          QC#21422
 * 2018/04/03   Hitachi         J.Kim           Update          QC#24729
 * 2018/05/11   Hitachi         J.Kim           Update          QC#21426
 * 2018/05/14   Fujitsu         Y.Matsui        Update          QC#24809
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/05/21   CITS            S.Katsuma       Update          QC#24793
 * 2018/06/05   Hitachi         Y.Takeno        Update          QC#26107
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 * 2018/06/25   Hitachi         Y.Takeno        Update          QC#25767
 * 2018/07/30   Fujitsu         Y.Matsui        Update          QC#25924
 * 2018/07/30   Fujitsu         Y.Matsui        Update          QC#27081
 * 2019/02/05   Hitachi         Y.Takeno        Update          QC#30161
 * 2019/02/12   Fujitsu         S.Ohki          Update          QC#30143
 * 2021/05/25   CITS            G.Delgado       Update          QC#58704
 * 2021/06/11   CITS            K.Suzuki        Update          QC#58704-2
 * 2022/01/20   Hitachi         A.Kohinata      Update          QC#56864
 * 2022/11/01   Hitachi         T.Doi           Update          QC#57088
 *</pre>
 */
public class NFDL0020CommonLogic {

    /**
     * @param  scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NFDL0020(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        scrnAppli.setButtonProperties("btn2", "", "Submit", 0, null);
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        scrnAppli.setButtonProperties("btn6", "", "Download", 0, null);
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "", "Clear", 0, null);

        scrnAppli.setButtonConfirmMsg("CMN_Reset", "ZZM8102I", new String[]{"Reset"}, 0);
        scrnAppli.setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 0);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NFDL0020BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFDL0020BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("btn1", "", "Save", 0, null);
        // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        // mod start 2022/01/20 QC#56864
        } else if (hasUpdateOnlyNoteFuncId(scrnMsg) && scrnMsg.xxDplyTab_H.getValue().equals("Note")) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        // mod end 2022/01/20 QC#56864
        } else {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
        // End   2016/12/27 H.Ikeda [QC#12865,ADD]
        scrnAppli.setButtonProperties("btn3", "", "Apply", 0, null);
        scrnAppli.setButtonProperties("btn4", "", "Approve", 0, null);
        scrnAppli.setButtonProperties("btn5", "", "Reject", 0, null);
        // START 2018/05/21 S.Katsuma [QC#24793,MOD]
        // Start 2018/03/15 J.Kim [QC#20945,MOD]
        if (scrnMsg.xxDplyTab_H.getValue().equals("AdjHistory")
                || scrnMsg.xxDplyTab_H.getValue().equals("Statement")
                || scrnMsg.xxDplyTab_H.getValue().equals("Transaction")
                || scrnMsg.xxDplyTab_H.getValue().equals("Note")
                || scrnMsg.xxDplyTab_H.getValue().equals("Task")
                || scrnMsg.xxDplyTab_H.getValue().equals("Contract")) {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 0, null);
        }
        // End 2018/03/15 J.Kim [QC#20945,MOD]
        // END 2018/05/21 S.Katsuma [QC#24793,MOD]
        scrnAppli.setButtonProperties("btn7", "", "Delete", 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn9", "CMN_Reset", "Reset", 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return", "Return", 1, null);

        scrnAppli.setButtonProperties("Click_ContactInfoEdit", "Click_ContactInfoEdit", "Edit", 1, null);
        scrnAppli.setButtonProperties("Click_ContactInfoUpdate", "Click_ContactInfoUpdate", "Update", 0, null);
        scrnAppli.setButtonProperties("Click_ContactInfoCancel", "Click_ContactInfoCancel", "Cancel", 0, null);

        scrnAppli.setButtonProperties("Click_TransactionActivity", "Click_TransactionActivity", "Activity", 1, null);
        // Start 2018/03/08 J.Kim [QC#20945,ADD]
        scrnAppli.setButtonProperties("Click_TransactionPromiseDispute", "Click_TransactionPromiseDispute", "Promise/Dispute", 1, null);
        // END 2018/03/08 J.Kim [QC#20945,ADD]
        scrnAppli.setButtonProperties("Click_TransactionCustomerCare", "Click_TransactionCustomerCare", "Customer Care", 1, null);
        scrnAppli.setButtonProperties("Click_TransactionPayment", "Click_TransactionPayment", "Payment", 1, null);
        scrnAppli.setButtonProperties("Click_TransactionPostApplies", "Click_TransactionPostApplies", "Post Applies", 1, null);
        scrnAppli.setButtonProperties("Click_TransactionPrintInvoice", "Click_TransactionPrintInvoice", "Print Invoice", 1, null);
        scrnAppli.setButtonProperties("Click_TransactionSearch", "Click_TransactionSearch", "Search", 1, null);
        scrnAppli.setButtonProperties("Click_TransactionWriteOff", "Click_TransactionWriteOff", "Write Off", 1, null);

        scrnAppli.setButtonProperties("Click_NoteNew", "Click_NoteNew", "New", 1, null);
        scrnAppli.setButtonProperties("Click_NoteSetCreateByName", "Click_NoteSetCreateByName", ">>", 0, null);

        scrnAppli.setButtonProperties("Click_TaskNew", "Click_TaskNew", "New", 1, null);
        scrnAppli.setButtonProperties("Click_TaskSetCreateByName", "Click_TaskSetCreateByName", ">>", 0, null);
        scrnAppli.setButtonProperties("Click_TaskSetOwnerName", "Click_TaskSetOwnerName", ">>", 0, null);

        scrnAppli.setButtonProperties("Click_ContractSearch", "Click_ContractSearch", "Search", 1, null);
        scrnAppli.setButtonProperties("Click_ContractPaymentProcessing", "Click_ContractPaymentProcessing", "Payment Processing", 1, null);

        scrnAppli.setButtonProperties("Click_StrategyChangeStrategy", "Click_StrategyChangeStrategy", "Change Strategy", 1, null);
        scrnAppli.setButtonProperties("Click_StrategyChangeWorkItems", "Click_StrategyChangeWorkItems", "Change Work Items", 1, null);
        scrnAppli.setButtonProperties("Click_StrategySendDunning", "Click_StrategySendDunning", "Send Dunning", 1, null);
        // START 2018/05/08 J.Kim [QC#21426,ADD]
        scrnAppli.setButtonProperties("Click_AddWorkItem", "Click_AddWorkItem", "Add Work Item", 1, null);
        // END 2018/05/08 J.Kim [QC#21426,ADD]
        // START 2018/07/09 J.Kim [QC#26801,ADD]
        scrnAppli.setButtonProperties("Click_NoteAttach", "Click_NoteAttach", "Attachment", 1, null);
        if (ZYPCommonFunc.hasValue(scrnMsg.cltNoteDtlPk_FH)) {
            scrnAppli.setButtonProperties("Click_NoteAttachment", "Click_NoteAttachment", "Attachment", 1, null);
        } else {
            scrnAppli.setButtonProperties("Click_NoteAttachment", "Click_NoteAttachment", "Attachment", 0, null);
        }
        // END 2018/07/09 J.Kim [QC#26801,ADD]
        // START 2019/02/12 S.Ohki [QC#30143,ADD]
        scrnAppli.setButtonConfirmMsgEx("CMN_Return", "NZZM0004W", null, 1);
        // END 2019/02/12 S.Ohki [QC#30143,ADD]

        scrnMsg.locNm_H.setInputProtected(true);

        // START 2018/05/14 [QC#24809,DEL]
        // scrnMsg.dsAcctNum_H.setInputProtected(true);
        // END   2018/05/14 [QC#24809,DEL]
        scrnMsg.dsAcctNm_H.setInputProtected(true);
        scrnMsg.addlLocNm_H.setInputProtected(true);
        scrnMsg.firstLineAddr_H.setInputProtected(true);
        scrnMsg.scdLineAddr_H.setInputProtected(true);
        scrnMsg.thirdLineAddr_H.setInputProtected(true);
        scrnMsg.frthLineAddr_H.setInputProtected(true);
        scrnMsg.ctryCd_H.setInputProtected(true);
        scrnMsg.postCd_H.setInputProtected(true);
        scrnMsg.ctyAddr_H.setInputProtected(true);
        scrnMsg.cntyNm_H.setInputProtected(true);
        scrnMsg.stCd_H.setInputProtected(true);
        scrnMsg.provNm_H.setInputProtected(true);

        scrnMsg.ctacTpCd_H.setInputProtected(true);
        scrnMsg.ctacPsnFirstNm_H.setInputProtected(true);
        scrnMsg.ctacPsnLastNm_H.setInputProtected(true);
        scrnMsg.dsCtacPntValTxt_H1.setInputProtected(true);
        scrnMsg.dsCtacPntValTxt_H2.setInputProtected(true);

        scrnMsg.xxScrItem130Txt_H.setInputProtected(true);
        scrnMsg.dealRmngBalGrsAmt_H1.setInputProtected(true);
        scrnMsg.dealRmngBalGrsAmt_H2.setInputProtected(true);
        scrnMsg.invTotDealNetAmt_H.setInputProtected(true);
        scrnMsg.rcptDt_H.setInputProtected(true);
        scrnMsg.rcptAmt_H.setInputProtected(true);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //scrnMsg.xxNum.setInputProtected(true);
        scrnMsg.custCrRtgDescTxt_H.setInputProtected(true);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]

        scrnMsg.xxTotAmt_H1.setInputProtected(true);
        scrnMsg.xxTotAmt_H2.setInputProtected(true);
        scrnMsg.xxTotAmt_H3.setInputProtected(true);
        scrnMsg.xxTotAmt_H4.setInputProtected(true);
        scrnMsg.xxTotAmt_H5.setInputProtected(true);
        scrnMsg.xxTotAmt_H6.setInputProtected(true);
        scrnMsg.xxTotAmt_H7.setInputProtected(true);
        scrnMsg.xxTotAmt_H8.setInputProtected(true);
        scrnMsg.xxTotAmt_H9.setInputProtected(true);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //scrnMsg.xxTotAmt_H0.setInputProtected(true);
        scrnMsg.dealCltDsptAmt_H.setInputProtected(true);
        scrnMsg.dealCltPrmsAmt_H.setInputProtected(true);
        scrnMsg.xxTotAmt_L1.setInputProtected(false);
        scrnMsg.xxTotAmt_L1.setValue(BigDecimal.ONE);
        scrnMsg.xxTotAmt_L2.setInputProtected(false);
        scrnMsg.xxTotAmt_L2.setValue(BigDecimal.ONE);
        scrnMsg.xxTotAmt_L3.setInputProtected(false);
        scrnMsg.xxTotAmt_L3.setValue(BigDecimal.ONE);
        scrnMsg.xxTotAmt_L4.setInputProtected(false);
        scrnMsg.xxTotAmt_L4.setValue(BigDecimal.ONE);
        scrnMsg.xxTotAmt_L5.setInputProtected(false);
        scrnMsg.xxTotAmt_L5.setValue(BigDecimal.ONE);
        scrnMsg.xxTotAmt_L6.setInputProtected(false);
        scrnMsg.xxTotAmt_L6.setValue(BigDecimal.ONE);
        scrnMsg.xxTotAmt_L7.setInputProtected(false);
        scrnMsg.xxTotAmt_L7.setValue(BigDecimal.ONE);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]

        // START 2019/02/05 [QC#30161, ADD]
        scrnMsg.dealRmngBalGrsAmt_AH.setInputProtected(true);
        // START 2019/02/05 [QC#30161, ADD]

        //Transaction Tab
        // START 2016/06/17 K.Kojima [QC#10170,DEL]
        // scrnMsg.xxNum_AH.setInputProtected(true);
        // END 2016/06/17 K.Kojima [QC#10170,DEL]
        scrnAppli.setButtonEnabled("Click_TransactionCustomerCare", true);
        scrnAppli.setButtonEnabled("Click_TransactionPayment", true);
        scrnAppli.setButtonEnabled("Click_TransactionPostApplies", true);
        scrnAppli.setButtonEnabled("Click_TransactionPrintInvoice", true);
        scrnAppli.setButtonEnabled("Click_TransactionWriteOff", true);

        //Note Tab
        scrnMsg.cltNoteDtlPk_FH.setInputProtected(true);
        scrnMsg.cratDt_FH.setInputProtected(true);
        scrnMsg.cratUsrId_FH.setInputProtected(true);
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // scrnMsg.dtlNoteTxt_FH.setInputProtected(true);
        scrnMsg.xxMlBodyTxt_FH.setInputProtected(true);
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        scrnMsg.colNoteSubjTxt_FH.setInputProtected(true);
        // START 2018/06/21 [QC#25080, MOD]
        // scrnMsg.arCltNoteTpCd_FH.setInputProtected(true);
        scrnMsg.cltNoteTpCd_FH.setInputProtected(true);
        // END   2018/06/21 [QC#25080, MOD]
        scrnMsg.xxPsnNm_FH.setInputProtected(true);
        // START 2018/06/21 [QC#25080, ADD]
        scrnMsg.cratTsDplyTxt_FH.setInputProtected(true);
        // END   2018/06/21 [QC#25080, ADD]

        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).cratDt_FD.setInputProtected(true);
            // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
            scrnMsg.F.no(i).cratTsDplyTxt_FD.setInputProtected(true);
            // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
            scrnMsg.F.no(i).cratUsrId_FD.setInputProtected(true);
            // START 2018/04/03 J.Kim [QC#25096,MOD]
            // scrnMsg.F.no(i).dtlNoteTxt_FD.setInputProtected(true);
            scrnMsg.F.no(i).xxMlBodyTxt_FD.setInputProtected(true);
            // END 2018/04/03 J.Kim [QC#25096,MOD]
            scrnMsg.F.no(i).colNoteSubjTxt_FD.setInputProtected(true);
            scrnMsg.F.no(i).xxPsnNm_FD.setInputProtected(true);
            // START 2016/06/17 K.Kojima [QC#10200,ADD]
            // START 2018/06/21 [QC#25080, MOD]
            // scrnMsg.F.no(i).arCltNoteTpDescTxt_FD.setInputProtected(true);
            scrnMsg.F.no(i).cltNoteTpDescTxt_FD.setInputProtected(true);
            // END   2018/06/21 [QC#25080, MOD]
            // END 2016/06/17 K.Kojima [QC#10200,ADD]
        }

        //Task Tab
        scrnMsg.cltTaskPk_GH.setInputProtected(true);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //scrnMsg.cltTaskStsCd_GH.setInputProtected(false);
        scrnMsg.cltTaskStsCd_GH.setInputProtected(true);
        // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
        scrnMsg.cltTaskTpCd_GH.setInputProtected(true);
        scrnMsg.cltTaskPrtyCd_GH.setInputProtected(true);
        scrnMsg.cltTaskOwnrId_GH.setInputProtected(true);
        // START 2016/07/07 K.Kojima [QC#10337,MOD]
        // scrnMsg.xxPsnNm_G1.setInputProtected(true);
        scrnMsg.cltPsnNm_G1.setInputProtected(true);
        // END 2016/07/07 K.Kojima [QC#10337,MOD]
        scrnMsg.cratUsrId_GH.setInputProtected(true);
        scrnMsg.xxPsnNm_G2.setInputProtected(true);
        scrnMsg.cltTaskDescTxt_GH.setInputProtected(true);
        scrnMsg.cltTaskSubjTxt_GH.setInputProtected(true);
        scrnMsg.cltTaskRwsdDt_GH.setInputProtected(true);
        scrnMsg.cltTaskRwedDt_GH.setInputProtected(true);
        scrnMsg.cltTaskCtacNm_GH.setInputProtected(true);
        scrnMsg.cltTaskCtacPhoNum_GH.setInputProtected(true);
        // START 2018/07/25 [QC#25767, ADD]
        scrnMsg.updUsrId_GH.setInputProtected(true);
        scrnMsg.xxPsnNm_G5.setInputProtected(true);
        scrnMsg.cltTaskUpdDt_GH.setInputProtected(true);
        // END   2018/07/25 [QC#25767, ADD]
        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).cltTaskPk_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskTpCd_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskTpDescTxt_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskStsCd_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskStsDescTxt_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskSubjTxt_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskOwnrId_GD.setInputProtected(true);
            // START 2016/07/07 K.Kojima [QC#10337,MOD]
            // scrnMsg.G.no(i).xxPsnNm_G3.setInputProtected(true);
            scrnMsg.G.no(i).cltPsnNm_G3.setInputProtected(true);
            // END 2016/07/07 K.Kojima [QC#10337,MOD]
            scrnMsg.G.no(i).cratUsrId_GD.setInputProtected(true);
            scrnMsg.G.no(i).xxPsnNm_G4.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskPrtyCd_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskPrtyDescTxt_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskRwsdDt_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskRwedDt_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskCtacNm_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskCtacPhoNum_GD.setInputProtected(true);
            scrnMsg.G.no(i).cltTaskDescTxt_GD.setInputProtected(true);
            // START 2016/06/17 K.Kojima [QC#10198,ADD]
            scrnMsg.G.no(i).cltTaskCratDt_GD.setInputProtected(true);
            // END 2016/06/17 K.Kojima [QC#10198,ADD]
        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).cltStrgyNm_CD.setInputProtected(true);
            scrnMsg.C.no(i).cltStrgyStsCd_CD.setInputProtected(true);
            scrnMsg.C.no(i).cltStrgyCurFlg_CD.setInputProtected(true);
            scrnMsg.C.no(i).arDunRnkCd_CD.setInputProtected(true);
            // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
            scrnMsg.C.no(i).entBillToCustLocAddr_CD.setInputProtected(true);
            // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
        }

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            // START 2016/08/23 K.Kojima [QC#13204,MOD]
            // scrnMsg.D.no(i).cltWrkCatgNm_DD.setInputProtected(true);
            scrnMsg.D.no(i).cltPsnNm_DD.setInputProtected(true);
            // END 2016/08/23 K.Kojima [QC#13204,MOD]
            scrnMsg.D.no(i).cltWrkItemCd_DD.setInputProtected(true);
            scrnMsg.D.no(i).cltWrkItemNm_DD.setInputProtected(true);
            scrnMsg.D.no(i).cltWrkItemRwedDt_DD.setInputProtected(true);
            scrnMsg.D.no(i).cltWrkItemRwsdDt_DD.setInputProtected(true);
            scrnMsg.D.no(i).cltWrkItemStsCd_DD.setInputProtected(true);
            scrnMsg.D.no(i).cltWrkTpNm_DD.setInputProtected(true);
            // START 2016/09/26 K.Kojima [QC#13907,ADD]
            scrnMsg.D.no(i).cltWrkItemWsrdDt_DD.setInputProtected(true);
            scrnMsg.D.no(i).cltWrkItemWerdDt_DD.setInputProtected(true);
            // END 2016/09/26 K.Kojima [QC#13907,ADD]
            // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
            scrnMsg.D.no(i).cltDunLtrRqstNum_DD.setInputProtected(true);
            if (ZYPCommonFunc.hasValue(scrnMsg.D.no(i).cltDunLtrRqstNum_DD)) {
                scrnMsg.D.no(i).cltWrkItemNm_LK.setInputProtected(false);
                scrnMsg.D.no(i).cltWrkItemNm_LK.setValue(ZYPConstant.FLG_ON_1);
            } else {
                scrnMsg.D.no(i).cltWrkItemNm_LK.clear();
                scrnMsg.D.no(i).cltWrkItemNm_LK.setInputProtected(true);
            }
            // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
        }

        // ADD 2017/01/17 T.Murai [QC#16809,MOD]
        setTabStrategyEnabled(scrnAppli, scrnMsg);

        // START 2016/07/19 K.Kojima [QC#11478,DEL]
        // for (int i = 0; i < scrnMsg.B.length(); i++) {
        // scrnMsg.B.no(i).dsContrNum_BD.setInputProtected(true);
        // scrnMsg.B.no(i).svcContrRefCmntTxt_BD.setInputProtected(true);
        // scrnMsg.B.no(i).ccyCd_BD.setInputProtected(true);
        // scrnMsg.B.no(i).contrVrsnEffFromDt_BD.setInputProtected(true);
        // scrnMsg.B.no(i).contrVrsnEffThruDt_BD.setInputProtected(true);
        // scrnMsg.B.no(i).contrCloDt_BD.setInputProtected(true);
        // scrnMsg.B.no(i).hldBllgRsnCd_BD.setInputProtected(true);
        // scrnMsg.B.no(i).hldBllgUntilDt_BD.setInputProtected(true);
        // if
        // (!scrnMsg.B.no(i).contrHldFlg_BK.getValue().equals(ZYPConstant.CHKBOX_ON_Y))
        // {
        // scrnMsg.B.no(i).contrHldFlg_BD.setInputProtected(true);
        // } else {
        // scrnMsg.B.no(i).contrHldFlg_BD.setInputProtected(false);
        // }
        // }
        // END 2016/07/19 K.Kojima [QC#11478,DEL]
        // START 2016/07/19 K.Kojima [QC#11478,ADD]
        setInputProtected_B(scrnMsg);
        // END 2016/07/19 K.Kojima [QC#11478,ADD]

        // START 2016/06/27 K.Kojima [QC#10781,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2016/08/30 K.Kojima [QC#10429,ADD]
            scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
            // END 2016/08/30 K.Kojima [QC#10429,ADD]
            // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
            scrnMsg.A.no(i).shipToLocNm_A.setInputProtected(true);
            // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
            // START 2018/06/05 [QC#26107, MOD]
            // if (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.INVOICE) || scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.CREDIT_MEMO) || scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.RECEIPT)) {
            if (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.INVOICE) || scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.CREDIT_MEMO) || scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.RECEIPT)
                    || (scrnMsg.A.no(i).arTrxTpCd_A.getValue().equals(AR_TRX_TP.DEDUCTION) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).eipRptRqstPk_A))) {
            // END   2018/06/05 [QC#26107, MOD]
                scrnMsg.A.no(i).xxLinkProt_A.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkProt_A.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.A.no(i).xxLinkProt_A.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkProt_A.clear();
            }
            // START 2018/05/10 J.Kim [QC#21720,ADD]
            scrnMsg.A.no(i).invFirstCmntTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).invTpNm_A.setInputProtected(true);
            scrnMsg.A.no(i).dsInvTpNm_A.setInputProtected(true);
            // START 2022/11/01 T.Doi [QC#57088, ADD]
            scrnMsg.A.no(i).svcInvChrgTpRptTxt_A.setInputProtected(true);
            // END 2022/11/01 T.Doi [QC#57088, ADD]
            scrnMsg.A.no(i).pmtTermCashDiscDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).tocNm_A.setInputProtected(true);
            // END 2018/05/10 J.Kim [QC#21720,ADD]
            // START 2018/07/30 Y.Matsui [QC#27081,ADD]
            scrnMsg.A.no(i).xxFullNm_A.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntValTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).dsCtacPntValTxt_A1.setInputProtected(true);
            // END   2018/07/30 Y.Matsui [QC#27081,ADD]
        }
        // END 2016/06/27 K.Kojima [QC#10781,ADD]
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg   NFDL0020BMsg
     */
    public static void setTabStrategyEnabled(EZDCommonHandler scrnAppli, NFDL0020BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            if (scrnMsg.C.no(i).cltStrgyStsCd_CB.getValue().equals(CLT_STRGY_STS.OPEN)) {
                scrnMsg.C.no(i).cltStrgyStsCd_CD.setInputProtected(false);
            } else {
                scrnMsg.C.no(i).cltStrgyStsCd_CD.setInputProtected(true);
            }
        }

        boolean addWorkItemBt = false;
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            if (scrnMsg.D.no(i).cltWrkItemStsCd_DB.getValue().equals(CLT_WRK_ITEM_STS.PENDING)) {
                scrnMsg.D.no(i).cltWrkItemStsCd_DD.setInputProtected(false);
            } else if (scrnMsg.D.no(i).cltWrkItemStsCd_DB.getValue().equals(CLT_WRK_ITEM_STS.OPEN)) {
                scrnMsg.D.no(i).cltWrkItemStsCd_DD.setInputProtected(false);
                // START 2016/09/26 K.Kojima [QC#13004,ADD]
            } else if (scrnMsg.D.no(i).cltWrkItemStsCd_DB.getValue().equals(CLT_WRK_ITEM_STS.SKIP)) {
                scrnMsg.D.no(i).cltWrkItemStsCd_DD.setInputProtected(false);
                // END 2016/09/26 K.Kojima [QC#13004,ADD]
            } else {
                scrnMsg.D.no(i).cltWrkItemStsCd_DD.setInputProtected(true);
            }
            // START 2018/03/23 J.Kim [QC#21422,MOD]
            String cltWRkTpCd = scrnMsg.D.no(i).cltWrkTpCd_DD.getValue();
            // START 2021/05/25 G.Delgado [QC#58704,MOD]
            // if (CLT_WRK_TP.AUTOMATIC.equals(cltWRkTpCd)) {
            if (CLT_WRK_TP.AUTOMATIC.equals(cltWRkTpCd)
                    && !CLT_WRK_ITEM_STS.PENDING.equals(scrnMsg.D.no(i).cltWrkItemStsCd_DB.getValue())
                    && !CLT_WRK_ITEM_STS.OPEN.equals(scrnMsg.D.no(i).cltWrkItemStsCd_DB.getValue())) {
            // END 2021/05/25 G.Delgado [QC#58704,MOD]
                scrnMsg.D.no(i).cltWrkItemStsCd_DD.setInputProtected(true);
            } else {
                scrnMsg.D.no(i).cltWrkItemStsCd_DD.setInputProtected(false);
            }
            // END 2018/03/23 J.Kim [QC#21422,MOD]
            // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.D.no(i).cltDunLtrRqstNum_DD)) {
                scrnMsg.D.no(i).cltWrkItemNm_LK.setInputProtected(false);
                scrnMsg.D.no(i).cltWrkItemNm_LK.setValue(ZYPConstant.FLG_ON_1);
            } else {
                scrnMsg.D.no(i).cltWrkItemNm_LK.clear();
                scrnMsg.D.no(i).cltWrkItemNm_LK.setInputProtected(true);
            }
            // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
            // START 2018/05/09 J.Kim [QC#21426,ADD]
            if (scrnMsg.D.no(i).cltWrkItemStsCd_DB.getValue().equals(CLT_WRK_ITEM_STS.COMPLETED) || CLT_WRK_TP.AUTOMATIC.equals(cltWRkTpCd)) {
                scrnMsg.D.no(i).cltWrkItemRwsdDt_DD.setInputProtected(true);
                scrnMsg.D.no(i).cltWrkItemRwedDt_DD.setInputProtected(true);
                scrnMsg.D.no(i).cltWrkItemWsrdDt_DD.setInputProtected(true);
                scrnMsg.D.no(i).cltWrkItemWerdDt_DD.setInputProtected(true);
            } else {
                scrnMsg.D.no(i).cltWrkItemRwsdDt_DD.setInputProtected(false);
                scrnMsg.D.no(i).cltWrkItemRwedDt_DD.setInputProtected(false);
                scrnMsg.D.no(i).cltWrkItemWsrdDt_DD.setInputProtected(false);
                scrnMsg.D.no(i).cltWrkItemWerdDt_DD.setInputProtected(false);
            }

            // START 2018/07/30 Y.Matsui [QC#25924,ADD]
            String selectedCltStrgyStsCd = getSelectedCltStrgyStsCd(scrnMsg);
            if (CLT_STRGY_STS.CLOSE.equals(selectedCltStrgyStsCd)) {
                // Work Item cannot be changed if Strategy is closed.
                scrnMsg.D.no(i).cltWrkItemStsCd_DD.setInputProtected(true);
                scrnMsg.D.no(i).cltWrkItemRwsdDt_DD.setInputProtected(true);
                scrnMsg.D.no(i).cltWrkItemRwedDt_DD.setInputProtected(true);
                scrnMsg.D.no(i).cltWrkItemWsrdDt_DD.setInputProtected(true);
                scrnMsg.D.no(i).cltWrkItemWerdDt_DD.setInputProtected(true);
            }
            // END   2018/07/30 Y.Matsui [QC#25924,ADD]

            // START 2021/06/11 k.Suzuki [QC#58704-2,MOD]
            //if (scrnMsg.D.no(i).cltWrkItemStsCd_DB.getValue().equals(CLT_WRK_ITEM_STS.PENDING) && CLT_WRK_TP.MANUAL.equals(cltWRkTpCd)) {
            //    addWorkItemBt = true;
            //} else if (scrnMsg.D.no(i).cltWrkItemStsCd_DB.getValue().equals(CLT_WRK_ITEM_STS.OPEN) && CLT_WRK_TP.MANUAL.equals(cltWRkTpCd)) {
            //    addWorkItemBt = true;
            //}
            if (CLT_STRGY_STS.OPEN.equals(selectedCltStrgyStsCd)) {
                addWorkItemBt = true;
            }
            // END 2021/06/11 k.Suzuki [QC#58704-2,MOD]
            // END 2018/05/09 J.Kim [QC#21426,ADD]
        }

        // add start 2022/01/20 QC#56864
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else if (hasUpdateOnlyNoteFuncId(scrnMsg) && scrnMsg.xxDplyTab_H.getValue().equals("Note")) {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
        }
        // add end 2022/01/20 QC#56864
        // START 2018/05/21 S.Katsuma [QC#24793,MOD]
        // START 2018/05/16 [QC#24329,MOD]
        // Start 2018/03/15 J.Kim [QC#20945,MOD]
        if (scrnMsg.xxDplyTab_H.getValue().equals("AdjHistory")
                || scrnMsg.xxDplyTab_H.getValue().equals("Statement")
                || scrnMsg.xxDplyTab_H.getValue().equals("Transaction")
                || scrnMsg.xxDplyTab_H.getValue().equals("Note")
                || scrnMsg.xxDplyTab_H.getValue().equals("Task")
                || scrnMsg.xxDplyTab_H.getValue().equals("Contract")) {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 1, null);
        } else {
            scrnAppli.setButtonProperties("btn6", "CMN_Download", "Download", 0, null);
        }
        // End 2018/03/15 J.Kim [QC#20945,MOD]
        // END 2018/05/16 [QC#24329,MOD]
        // END 2018/05/21 S.Katsuma [QC#24793,MOD]
        // START 2018/05/09 J.Kim [QC#21426,ADD]
        if (addWorkItemBt) {
            scrnAppli.setButtonProperties("Click_AddWorkItem", "Click_AddWorkItem", "Add Work Item", 1, null);
        } else {
            scrnAppli.setButtonProperties("Click_AddWorkItem", "Click_AddWorkItem", "Add Work Item", 0, null);
        }
        // END 2018/05/09 J.Kim [QC#21426,ADD]

        // START 2018/05/16 [QC#24329,ADD]
        initializeStatementTab(scrnMsg);
        // END 2018/05/16 [QC#24329,ADD]
        // START 2018/07/09 J.Kim [QC#26801,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.cltNoteDtlPk_FH)) {
            scrnAppli.setButtonProperties("Click_NoteAttachment", "Click_NoteAttachment", "Attachment", 1, null);
        } else {
            scrnAppli.setButtonProperties("Click_NoteAttachment", "Click_NoteAttachment", "Attachment", 0, null);
        }
        // END 2018/07/09 J.Kim [QC#26801,ADD]
    }

    /**
     * @param scrnMsg  NFDL0020BMsg
     */
    public static void clearScrnBackgroundColor(NFDL0020BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute("NFDL0020Scrn00");
    }

    // START 2016/06/16 K.Kojima [QC#10200,ADD]
    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFDL0020BMsg
     */
    public static void setTaskInputProtectFalse(EZDCommonHandler handler, NFDL0020BMsg scrnMsg) {
        scrnMsg.cltTaskPk_GH.setInputProtected(false);
        scrnMsg.cltTaskStsCd_GH.setInputProtected(false);
        scrnMsg.cltTaskTpCd_GH.setInputProtected(false);
        scrnMsg.cltTaskPrtyCd_GH.setInputProtected(false);
        scrnMsg.cltTaskOwnrId_GH.setInputProtected(false);
        // START 2016/07/07 K.Kojima [QC#10337,MOD]
        // scrnMsg.xxPsnNm_G1.setInputProtected(true);
        scrnMsg.cltPsnNm_G1.setInputProtected(true);
        // END 2016/07/07 K.Kojima [QC#10337,MOD]
        scrnMsg.cratUsrId_GH.setInputProtected(true);
        scrnMsg.xxPsnNm_G2.setInputProtected(true);
        scrnMsg.cltTaskDescTxt_GH.setInputProtected(false);
        scrnMsg.cltTaskSubjTxt_GH.setInputProtected(false);
        scrnMsg.cltTaskRwsdDt_GH.setInputProtected(false);
        scrnMsg.cltTaskRwedDt_GH.setInputProtected(false);
        scrnMsg.cltTaskCtacNm_GH.setInputProtected(false);
        scrnMsg.cltTaskCtacPhoNum_GH.setInputProtected(false);
        // START 2016/06/22 K.Kojima [QC#10529,ADD]
        handler.setButtonEnabled("Click_TaskSetOwnerName", true);
        // END 2016/06/22 K.Kojima [QC#10529,ADD]
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFDL0020BMsg
     */
    public static void setTaskInputProtectTrue(EZDCommonHandler handler, NFDL0020BMsg scrnMsg) {
        scrnMsg.cltTaskPk_GH.setInputProtected(true);
        // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
        //scrnMsg.cltTaskStsCd_GH.setInputProtected(false);
        scrnMsg.cltTaskStsCd_GH.setInputProtected(true);
        // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
        scrnMsg.cltTaskTpCd_GH.setInputProtected(true);
        scrnMsg.cltTaskPrtyCd_GH.setInputProtected(true);
        scrnMsg.cltTaskOwnrId_GH.setInputProtected(true);
        // START 2016/07/07 K.Kojima [QC#10337,MOD]
        // scrnMsg.xxPsnNm_G1.setInputProtected(true);
        scrnMsg.cltPsnNm_G1.setInputProtected(true);
        // END 2016/07/07 K.Kojima [QC#10337,MOD]
        scrnMsg.cratUsrId_GH.setInputProtected(true);
        scrnMsg.xxPsnNm_G2.setInputProtected(true);
        scrnMsg.cltTaskDescTxt_GH.setInputProtected(true);
        scrnMsg.cltTaskSubjTxt_GH.setInputProtected(true);
        scrnMsg.cltTaskRwsdDt_GH.setInputProtected(true);
        scrnMsg.cltTaskRwedDt_GH.setInputProtected(true);
        scrnMsg.cltTaskCtacNm_GH.setInputProtected(true);
        scrnMsg.cltTaskCtacPhoNum_GH.setInputProtected(true);
        // START 2016/06/22 K.Kojima [QC#10529,ADD]
        handler.setButtonEnabled("Click_TaskSetOwnerName", false);
        // END 2016/06/22 K.Kojima [QC#10529,ADD]
    }
    // END 2016/06/16 K.Kojima [QC#10200,ADD]

    // START 2016/06/22 K.Kojima [QC#10529,ADD]
    /**
     * @param scrnMsg NFDL0020BMsg
     */
    public static void addCheckItemForSubmit(NFDL0020BMsg scrnMsg) {
        // "Note" Check
        if (ZYPCommonFunc.hasValue(scrnMsg.xxYesNoCd_FH) && "1".equals(scrnMsg.xxYesNoCd_FH.getValue())) {
            // START 2018/06/21 [QC#25080, MOD]
            // scrnMsg.addCheckItem(scrnMsg.colNoteSubjTxt_FH);
            scrnMsg.addCheckItem(scrnMsg.cltNoteTpCd_FH);
            // END   2018/06/21 [QC#25080, MOD]
            // START 2018/04/03 J.Kim [QC#25096,MOD]
            // scrnMsg.addCheckItem(scrnMsg.dtlNoteTxt_FH);
            scrnMsg.addCheckItem(scrnMsg.xxMlBodyTxt_FH);
            // END 2018/04/03 J.Kim [QC#25096,MOD]
        }
        // "Task" Check
        scrnMsg.addCheckItem(scrnMsg.cltTaskStsCd_GH);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxYesNoCd_GH) && "1".equals(scrnMsg.xxYesNoCd_GH.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.cltTaskTpCd_GH);
            scrnMsg.addCheckItem(scrnMsg.cltTaskSubjTxt_GH);
            scrnMsg.addCheckItem(scrnMsg.cltTaskOwnrId_GH);
            scrnMsg.addCheckItem(scrnMsg.cltTaskRwsdDt_GH);
            scrnMsg.addCheckItem(scrnMsg.cltTaskRwedDt_GH);
            scrnMsg.addCheckItem(scrnMsg.cltTaskCtacNm_GH);
            scrnMsg.addCheckItem(scrnMsg.cltTaskCtacPhoNum_GH);
            scrnMsg.addCheckItem(scrnMsg.cltTaskDescTxt_GH);
        }
        // START 2016/09/26 K.Kojima [QC#13004,ADD]
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).cltStrgyStsCd_CD);
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).cltWrkItemStsCd_DD);
            // START 2018/05/11 J.Kim [QC#21426,ADD]
            scrnMsg.addCheckItem(scrnMsg.D.no(i).cltWrkItemRwsdDt_DD);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).cltWrkItemRwedDt_DD);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).cltWrkItemWsrdDt_DD);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).cltWrkItemWerdDt_DD);
            // END 2018/05/11 J.Kim [QC#21426,ADD]
        }
        // END 2016/09/26 K.Kojima [QC#13004,ADD]
    }
    // END 2016/06/22 K.Kojima [QC#10529,ADD]

    // START 2016/07/19 K.Kojima [QC#11478,ADD]
    /**
     * setInputProtected_B
     * @param scrnMsg NFDL0020BMsg
     */
    public static void setInputProtected_B(NFDL0020BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).dsContrNum_BD.setInputProtected(true);
            scrnMsg.B.no(i).svcContrRefCmntTxt_BD.setInputProtected(true);
            scrnMsg.B.no(i).ccyCd_BD.setInputProtected(true);
            scrnMsg.B.no(i).contrVrsnEffFromDt_BD.setInputProtected(true);
            scrnMsg.B.no(i).contrVrsnEffThruDt_BD.setInputProtected(true);
            scrnMsg.B.no(i).contrCloDt_BD.setInputProtected(true);
            // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
            //scrnMsg.B.no(i).hldBllgRsnCd_BD.setInputProtected(true);
            //scrnMsg.B.no(i).hldBllgUntilDt_BD.setInputProtected(true);
            //if (!scrnMsg.B.no(i).contrHldFlg_BK.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            scrnMsg.B.no(i).xxDt10Dt_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxDt10Dt_B2.setInputProtected(true);
            scrnMsg.B.no(i).xxDt10Dt_B3.setInputProtected(true);
            if (!scrnMsg.B.no(i).relFlg_BD.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                scrnMsg.B.no(i).contrHldFlg_BD.setInputProtected(true);
            } else {
                scrnMsg.B.no(i).contrHldFlg_BD.setInputProtected(false);
            }
            // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
            scrnMsg.B.no(i).svcContrRefCmntTxt_BD.setInputProtected(true);
        }
    }
    // END 2016/07/19 K.Kojima [QC#11478,ADD]

    // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFDL0020BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId("NFDL0020");
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains("NFDL0020T020")) {
            return true;
        }
        return false;
    }
    // End  2016/12/27 H.Ikeda [QC#12865,ADD]

    // START 2018/05/08 J.Kim [QC#12525, ADD]
    public static Object[] setParamAddWorkItemPopup(NFDL0020BMsg scrnMsg, String glblCmpyCd) {

        scrnMsg.xxScrEventNm.setValue("Click_AddWorkItem");

        Object[] params = new Object[7];
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    CWI.GLBL_CMPY_CD ");
        sql.append("  , CWI.EZCANCELFLAG ");
        sql.append("  , CWI.CLT_WRK_ITEM_CD ");
        sql.append("  , CWI.CLT_WRK_ITEM_NM ");
        sql.append("  , CWI.CLT_WRK_TP_CD ");
        sql.append("  , CWT.CLT_WRK_TP_NM ");
        sql.append("FROM ");
        sql.append("    CLT_WRK_ITEM CWI ");
        sql.append("  , CLT_WRK_TP   CWT ");
        sql.append("WHERE ");
        sql.append("    CWI.GLBL_CMPY_CD  = '" + glblCmpyCd + "' ");
        sql.append("AND CWI.EZCANCELFLAG  = '0' ");
        sql.append("AND CWI.GLBL_CMPY_CD  = CWT.GLBL_CMPY_CD ");
        sql.append("AND CWI.EZCANCELFLAG  = CWT.EZCANCELFLAG ");
        sql.append("AND CWI.CLT_WRK_TP_CD = CWT.CLT_WRK_TP_CD ");
        sql.append("AND CWI.CLT_WRK_TP_CD = 'MN' ");

        List<Object[]> whereList = new ArrayList<Object[]>();

        List<Object[]> columnList = new ArrayList<Object[]>();
        addDisplayColumn(columnList, "Code", "CLT_WRK_ITEM_CD", BigDecimal.valueOf(10), "Y");
        addDisplayColumn(columnList, "Work Item Name", "CLT_WRK_ITEM_NM", BigDecimal.valueOf(40), "N");
        addDisplayColumn(columnList, "Work Item Type", "CLT_WRK_TP_NM", BigDecimal.valueOf(10), "N");

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        addSortCondition(sortConditionList, "CLT_WRK_ITEM_CD", "ASC");

        scrnMsg.Q.clear();

        params[0] = "";
        params[1] = "Strategy Master - Work Item";
        params[2] = sql.toString();
        params[3] = whereList;
        params[4] = columnList;
        params[5] = sortConditionList;
        params[6] = scrnMsg.Q;

        return params;
    }

    private static void addDisplayColumn(List<Object[]> columnList, String labelName, String dbColumnName, BigDecimal displaySize, String linkFlag){
        Object[] columnArray = new Object[4];
        columnArray[0] = labelName;
        columnArray[1] = dbColumnName;
        columnArray[2] = displaySize;
        columnArray[3] = linkFlag;
        columnList.add(columnArray);
    }

    private static void addSortCondition(List<Object[]> sortConditionList, String dbColumnName, String orderBy){
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = dbColumnName;
        sortConditionArray[1] = orderBy;
        sortConditionList.add(sortConditionArray);
    }
    // END 2018/05/08 J.Kim [QC#12525, ADD]

    // START 2018/05/08 J.Kim [QC#12525, ADD]
    //// START 2017/08/07 T.Tsuchida [QC#19576,ADD]
    // mod start 2022/01/20 QC#56864
    /**
     * hasUpdateOnlyNoteFuncId
     * @return boolean
     */
    private static boolean hasUpdateOnlyNoteFuncId(NFDL0020BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId("NFDL0020");
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId() });
            return false;
        }
        if (funcList.contains("NFDL0020T030")) {
            return true;
        }
        return false;
    }
    // mod end 2022/01/20 QC#56864
    //// END 2017/08/07 T.Tsuchida [QC#19576,ADD]
    // END 2018/05/08 J.Kim [QC#12525, ADD]

    // START 2018/05/16 [QC#24329,ADD]
    /**
     * @param scrnMsg NFDL0020BMsg scrnMsg
     */
    public static void initializeStatementTab(NFDL0020BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.J.length(); i++) {
            scrnMsg.J.no(i).scdCustNm_J.setInputProtected(true);
            scrnMsg.J.no(i).billToCustLocAddr_J.setInputProtected(true);
            if (ZYPCommonFunc.hasValue(scrnMsg.J.no(i).eipRptRqstPk_J)) {
                scrnMsg.J.no(i).stmtSqPk_LK.setInputProtected(false);
                scrnMsg.J.no(i).stmtSqPk_LK.setValue(BigDecimal.ONE);
            } else {
                scrnMsg.J.no(i).stmtSqPk_LK.clear();
                scrnMsg.J.no(i).stmtSqPk_LK.setInputProtected(true);
            }
        }
    }
    // END 2018/05/16 [QC#24329,ADD]

    // START 2018/07/09 J.Kim [QC#26801, ADD]
    /**
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NFDL0020_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArray_popupForZYPL0310(NFDL0020_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm.getValue();
        }
        return param;
    }
    // END 2018/07/09 J.Kim [QC#26801, ADD]

    // START 2018/07/30 Y.Matsui [QC#25924,MOD]
    private static String getSelectedCltStrgyStsCd(NFDL0020BMsg scrnMsg) {
        int selectIdx = scrnMsg.xxRadioBtn_CD.getValueInt();
        if (selectIdx + 1 > scrnMsg.C.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_CD, BigDecimal.ZERO);
            selectIdx = scrnMsg.xxRadioBtn_CD.getValueInt();
        }
        String cltStrgyStsCd = "";
        if (scrnMsg.C.getValidCount() > 0) {
            cltStrgyStsCd = scrnMsg.C.no(selectIdx).cltStrgyStsCd_CD.getValue();
        }
        return cltStrgyStsCd;
    }
    // END   2018/07/30 Y.Matsui [QC#25924,MOD]
}
