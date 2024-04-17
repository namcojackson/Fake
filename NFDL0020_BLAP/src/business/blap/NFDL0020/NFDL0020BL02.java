package business.blap.NFDL0020;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_NOTE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_PRMS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_STRGY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_TASK_PRTY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_TASK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

import business.blap.NFDL0020.common.NFDL0020CommonLogic;
import business.blap.NFDL0020.constant.NFDL0020Constant;
import business.db.CLT_STRGY_WRK_ITEM_TRXTMsg;
import business.db.CLT_TASKTMsg;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/04/27   Fujitsu         S.Fujita        Update          QC#7218
 * 2016/05/26   Fujitsu         S.Fujita        Update          QC#8338
 * 2016/05/26   Fujitsu         S.Fujita        Update          QC#8339
 * 2016/06/17   Hitachi         K.Kojima        Update          QC#10170
 * 2016/06/17   Hitachi         K.Kojima        Update          QC#10172
 * 2016/06/17   Hitachi         K.Kojima        Update          QC#10191
 * 2016/06/17   Hitachi         K.Kojima        Update          QC#10197
 * 2016/06/21   Hitachi         K.Kojima        Update          QC#10177
 * 2016/06/21   Hitachi         K.Kojima        Update          QC#10181
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10334
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10337
 * 2016/07/15   Hitachi         K.Kojima        Update          QC#11478
 * 2016/07/27   Hitachi         K.Kojima        Update          QC#10199
 * 2016/07/27   Hitachi         K.Kojima        Update          QC#10999
 * 2016/07/28   Hitachi         K.Kojima        Update          QC#12096
 * 2016/08/22   Hitachi         K.Kojima        Update          QC#13331
 * 2016/08/23   Hitachi         K.Kojima        Update          QC#13204
 * 2016/08/29   Hitachi         K.Kojima        Update          QC#10786
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2016/10/31   Hitachi         J.Kim           Update          QC#13004
 * 2017/02/28   Fujitsu         T.Murai         Update          QC#17193
 * 2017/03/07   Fujitsu         T.Murai         Update          QC#17193
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/02/01   Hitachi         T.Tsuchida      Update          QC#23895
 * 2018/03/15   Hitachi         J.Kim           Update          QC#20945
 * 2018/03/26   Hitachi         J.Kim           Update          QC#21422
 * 2018/05/11   Hitachi         K.Kojima        Update          QC#21426
 * 2018/05/16   Fujisu          Y.Matsui        Update          QC#24329
 * 2018/05/21   CITS            S.Katsuma       Update          QC#24793
 * 2018/06/12   Hitachi         Y.Takeno        Update          QC#26373
 * 2018/06/15   Hitachi         Y.Takeno        Update          QC#24792
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 * 2018/06/22   Hitachi         Y.Takeno        Update          QC#25080
 * 2018/06/25   Hitachi         Y.Takeno        Update          QC#26239
 * 2018/07/23   Hitachi         Y.Takeno        Update          QC#25780
 * 2018/07/25   Hitachi         Y.Takeno        Update          QC#25767
 * 2018/07/27   Hitachi         Y.Takeno        Update          QC#25767
 * 2018/08/30   Hitachi         Y.Takeno        Update          QC#27603
 * 2018/10/19   Fujitsu         T.Noguchi       Update          QC#28823
 * 2019/01/24   Fujitsu         S.Ohki          Update          QC#30008
 * 2019/02/04   Hitachi         Y.Takeno        Update          QC#30161
 * 2020/01/29   CITS            M.Furugoori     Update          QC#55017
 * 2020/02/18   Fujitsu         H.Mizukami      Update          QC#55664
 * 2021/05/25   CITS            G.Delgado       Update          QC#58704
 * 2022/03/29   CITS            T.Omura         Update          QC#59011
 * 2022/12/12   Hitachi         S.Fujita        Update          QC#60406
 * 2023/02/24   Hitachi         S.Fujita        Update          QC#61220
 *</pre>
 */
public class NFDL0020BL02 extends S21BusinessHandler implements NFDL0020Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
            if ("NFDL0020_INIT".equals(screenAplID)) {
                doProcess_NFDL0020_INIT(cMsg, sMsg);
                NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
                if (ZYPCommonFunc.hasValue(bizMsg.cltTaskPk_PM)) {
                    doProcess_NFDL0020Scrn00_Click_TabTask(cMsg, sMsg);
                } else if (ZYPCommonFunc.hasValue(bizMsg.cltStrgyWrkItemTrxPk_PM)) {
                    doProcess_NFDL0020Scrn00_Click_TabStrategy(cMsg, sMsg);
                } else {
                    ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
                }
            } else if ("NFDL0020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_CMN_Submit((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg);
            } else if ("NFDL0020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_CMN_Clear(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
            } else if ("NFDL0020Scrn00_PageNext".equals(screenAplID)) {
                NFDL0020CommonLogic.nextPage((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg);
            } else if ("NFDL0020Scrn00_PagePrev".equals(screenAplID)) {
                NFDL0020CommonLogic.prevPage((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg);
            } else if ("NFDL0020Scrn00_Click_ContactInfoUpdate".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_ContactInfoUpdate(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_ContactInfoCancel".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_ContactInfoCancel(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_NoteSearch".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_NoteSearch(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_TabTransaction".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TabTransaction(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
            } else if ("NFDL0020Scrn00_Click_TabNote".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TabNote(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_TabTask".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TabTask(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_TabContract".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TabContract(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "BHEAD");
            } else if ("NFDL0020Scrn00_Click_TabStrategy".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TabStrategy(cMsg, sMsg);
                doProcess_NFDL0020Scrn00_OnChange_Strategy(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_TransactionSearch".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TransactionSearch(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_NoteSearch".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_NoteSearch(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_TaskSearch".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TaskSearch(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_ContractSearch".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_ContractSearch(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_SetBillToLocNm".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_SetBillToLocNm(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_Search".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_Search(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_TaskSetOwnerName".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TaskSetOwnerName(cMsg, sMsg);
            } else if ("NFDL0020_NFDL0030".equals(screenAplID)) {
                doProcess_NFDL0020_NFDL0030(cMsg, sMsg);
            } else if ("NFDL0020_NFDL0040".equals(screenAplID)) {
                doProcess_NFDL0020_NFDL0040(cMsg, sMsg);
            } else if ("NFDL0020_NFDL0070".equals(screenAplID)) {
                doProcess_NFDL0020_NFDL0070(cMsg, sMsg);
            } else if ("NFDL0020_NFDL0090".equals(screenAplID)) {
                doProcess_NFDL0020_NFDL0090(cMsg, sMsg);
            } else if ("NFDL0020_NMAL6760".equals(screenAplID)) {
                doProcess_NFDL0020_NMAL6760(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_CMN_ColSave".equals(screenAplID)) {
            } else if ("NFDL0020Scrn00_CMN_ColClear".equals(screenAplID)) {
            } else if ("NFDL0020Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_OnChange_ChkBoxDisplayStrategies".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_OnChange_ChkBoxDisplayStrategies(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_TransactionPayment".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TransactionPayment(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_AgingCurrent".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_AgingCurrent(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_Aging30".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_Aging30(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_Aging60".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_Aging60(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_Aging90".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_Aging90(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_Aging180".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_Aging180(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_Aging360".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_Aging360(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_Click_Aging361".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_Aging361(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_OnChange_Strategy".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_OnChange_Strategy(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_OnChange_StrategyStatus".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_OnChange_StrategyStatus(cMsg, sMsg);
            // START 2018/03/15 J.Kim [QC#20945,ADD]
            } else if ("NFDL0020Scrn00_Click_TabAdjHistory".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TabAdjHistory(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "HHEAD");
            } else if ("NFDL0020Scrn00_Click_AdjHistorySearch".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_AdjHistorySearch(cMsg, sMsg);
            } else if ("NFDL0020Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NFDL0020_NFCL0860".equals(screenAplID)) {
                doProcess_NFDL0020_NFCL0860(cMsg, sMsg);
            // END 2018/03/15 J.Kim [QC#20945,ADD]
            // START 2018/05/16 [QC#24329,ADD]
            } else if ("NFDL0020Scrn00_Click_TabStatement".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TabStatement(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "JHEAD");
            // ENDT 2018/05/16 [QC#24329,ADD]
            // START 2018/06/04 Y.Matsui [QC#24809,ADD]
            } else if ("NFDL0020Scrn00_Click_AcctBillTo".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_AcctBillTo(cMsg, sMsg);
            // END   2018/06/04 Y.Matsui [QC#24809,ADD]
            // START 2019/02/04 [QC#30161, ADD]
            } else if ("NFDL0020Scrn00_Click_TransactionCalc".equals(screenAplID)) {
                doProcess_NFDL0020Scrn00_Click_TransactionCalc(cMsg, sMsg);
            // END  2019/02/04 [QC#30161, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020_INIT================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        getTrxTypeList(bizMsg, globalMsg);
        ZYPCodeDataUtil.createPulldownList(CLT_TASK_STS.class, bizMsg.cltTaskStsCd_LC, bizMsg.cltTaskStsDescTxt_LD, ":");
        ZYPCodeDataUtil.createPulldownList(CLT_TASK_TP.class, bizMsg.cltTaskTpCd_LC, bizMsg.cltTaskTpDescTxt_LD, ":");
        ZYPCodeDataUtil.createPulldownList(CLT_TASK_PRTY.class, bizMsg.cltTaskPrtyCd_LC, bizMsg.cltTaskPrtyDescTxt_LD, ":");
        ZYPCodeDataUtil.createPulldownList(CLT_STRGY_STS.class, bizMsg.cltStrgyStsCd_LC, bizMsg.cltStrgyStsDescTxt_LD, ":");
        // START 2016/09/26 K.Kojima [QC#13004,MOD]
        // ZYPCodeDataUtil.createPulldownList(CLT_WRK_ITEM_STS.class,
        // bizMsg.cltWrkItemStsCd_LC, bizMsg.cltWrkItemStsDescTxt_LD,
        // ":");
        ZYPCodeDataUtil.createPulldownList(CLT_WRK_ITEM_STS.class, bizMsg.cltWrkItemStsCd_LC, bizMsg.cltWrkItemStsDescTxt_LD);
        // END 2016/09/26 K.Kojima [QC#13004,MOD]

        // START 2018/06/21 [QC#25080, MOD]
        // ZYPCodeDataUtil.createPulldownList(AR_CLT_NOTE_TP.class, bizMsg.arCltNoteTpCd_LC, bizMsg.arCltNoteTpDescTxt_LD, ":");
        ZYPCodeDataUtil.createPulldownList(CLT_NOTE_TP.class, bizMsg.cltNoteTpCd_LC, bizMsg.cltNoteTpDescTxt_LD, ":");
        // END   2018/06/21 [QC#25080, MOD]
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_TP.class, bizMsg.dsContrTpCd_LC, bizMsg.dsContrTpDescTxt_LD, ":");
        getContactTypeCodeList(bizMsg, globalMsg);
        String salesDt = ZYPDateUtil.getSalesDate();
        // START 2017/08/07 T.Tsuchida [QC#19576,DEL]
        //ZYPEZDItemValueSetter.setValue(bizMsg.invDueDt_A1, ZYPDateUtil.addDays(salesDt, -180));
        //ZYPEZDItemValueSetter.setValue(bizMsg.invDueDt_A2, salesDt);
        // END 2017/08/07 T.Tsuchida [QC#19576,DEL]

        // START 2018/06/21 [QC#25080, MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.cratDt_FF, ZYPDateUtil.addDays(salesDt, -30));
        ZYPEZDItemValueSetter.setValue(bizMsg.cratDt_FF, NFDL0020CommonLogic.addMonths(salesDt, -12));
        // END   2018/06/21 [QC#25080, MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.cratDt_FT, salesDt);

        // START 2016/08/23 K.Kojima [QC#13331,MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.cratDt_GF,
        // ZYPDateUtil.addDays(salesDt, -30));
        // ZYPEZDItemValueSetter.setValue(bizMsg.cratDt_GT, salesDt);
        if (ZYPCommonFunc.hasValue(bizMsg.cltTaskPk_PM)) {
            CLT_TASKTMsg taskTMsg = new CLT_TASKTMsg();
            ZYPEZDItemValueSetter.setValue(taskTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(taskTMsg.cltTaskPk, bizMsg.cltTaskPk_PM);
            taskTMsg = (CLT_TASKTMsg) EZDTBLAccessor.findByKey(taskTMsg);
            // START 2018/07/25 [QC#25767, MOD]
            ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskRwsdDt_GF, taskTMsg.cltTaskRwsdDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskRwsdDt_GT, taskTMsg.cltTaskRwsdDt);
        } else {
            // 2018/10/19 QC#28823 Mod Start
            //ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskRwsdDt_GF, ZYPDateUtil.addDays(salesDt, -30));
            //ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskRwsdDt_GT, salesDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskRwsdDt_GF, salesDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskRwsdDt_GT, ZYPDateUtil.addDays(salesDt, 30));
            // 2018/10/19 QC#28823 Mod End
            // END   2018/07/25 [QC#25767, MOD]
        }
        // END 2016/08/23 K.Kojima [QC#13331,MOD]

        // START 2016/07/27 K.Kojima [QC#10999,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_A3, ZYPConstant.FLG_ON_Y);
        // END 2016/07/27 K.Kojima [QC#10999,ADD]
        // START 2016/10/31 J.Kim [QC#13004,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_C, ZYPConstant.FLG_OFF_N);
        // END 2016/10/31 J.Kim [QC#13004,ADD]

        // START 2018/07/23 [QC#25780, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.cltStrgyWrkItemTrxPk_PM)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_C, ZYPConstant.CHKBOX_ON_Y);
        }
        // END   2018/07/23 [QC#25780, ADD]

        // START 2018/08/30 [QC#27603, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_A1, ZYPConstant.FLG_ON_Y);
        // END   2018/08/30 [QC#27603, ADD]

        NFDL0020CommonLogic.setBilltoLocNm(getGlobalCompanyCode(), bizMsg);
        getCollectionHeader(bizMsg, globalMsg);
        // START 2022/03/24 [QC#59011
        getLastPaymentData(bizMsg, globalMsg);
        // END 2022/03/24 [QC#59011
        getCollectionNoteSpecial(bizMsg, globalMsg);
        getTransaction(bizMsg, globalMsg);
        getCollectionNote(bizMsg, globalMsg);
        getCollectionTask(bizMsg, globalMsg);
        getCollectionContract(bizMsg, globalMsg);
        getCollectionStrategy(bizMsg, globalMsg);
        // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
        // START 2018/07/23 [QC#25780, MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.cltStrgyTrxPk_DH, getCltStrgyTrxPk(bizMsg));
        if (ZYPCommonFunc.hasValue(bizMsg.cltStrgyWrkItemTrxPk_PM)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cltStrgyTrxPk_DH, getCltStrgyTrxPkFromWrkItem(bizMsg));
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.cltStrgyTrxPk_DH, getCltStrgyTrxPk(bizMsg));
        }
        // END 2018/07/23 [QC#25780, MOD]
        // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
        getCollectionStrategyWrkItem(bizMsg, globalMsg);
        // START 2018/03/15 J.Kim [QC#20945,ADD]
        NFDL0020CommonLogic.getAdjHistory(bizMsg, globalMsg, getGlobalCompanyCode());
        // END 2018/03/15 J.Kim [QC#20945,ADD]
        // START 2018/05/16 [QC#24329,ADD]
        NFDL0020CommonLogic.getStatement(bizMsg, globalMsg, getGlobalCompanyCode());
        // END 2018/05/16 [QC#24329,ADD]

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Transaction");

        // START 2018/06/08 [QC#26373, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H1, ZYPConstant.CHKBOX_ON_Y);
        // END   2018/06/08 [QC#26373, ADD]

        // START 2016/04/27 S.Fujita [QC#7218,MOD]
        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
        // END 2016/04/27 S.Fujita [QC#7218,MOD]

        EZDDebugOutput.println(1, "doProcess_NFDL0020_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_Search================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        NFDL0020CommonLogic.setBilltoLocNm(getGlobalCompanyCode(), bizMsg);
        getCollectionHeader(bizMsg, globalMsg);
        getCollectionNoteSpecial(bizMsg, globalMsg);
        getTransaction(bizMsg, globalMsg);
        getCollectionNote(bizMsg, globalMsg);
        getCollectionTask(bizMsg, globalMsg);
        getCollectionContract(bizMsg, globalMsg);
        getCollectionStrategy(bizMsg, globalMsg);
        getCollectionStrategyWrkItem(bizMsg, globalMsg);
        // START 2018/03/15 J.Kim [QC#20945,ADD]
        NFDL0020CommonLogic.getAdjHistory(bizMsg, globalMsg, getGlobalCompanyCode());
        // END 2018/03/15 J.Kim [QC#20945,ADD]
        // START 2018/05/16 [QC#24329,ADD]
        NFDL0020CommonLogic.getStatement(bizMsg, globalMsg, getGlobalCompanyCode());
        // END 2018/05/16 [QC#24329,ADD]

        // START 2016/04/27 S.Fujita [QC#7218,MOD]
        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
        // END 2016/04/27 S.Fujita [QC#7218,MOD]

        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_Search================================END", this);
    }

    // START 2016/05/26 S.Fujita [QC#8339,ADD]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020_NFDL0030(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020_NFDL0030================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        getCollectionHeader(bizMsg, globalMsg);
        getCollectionNoteSpecial(bizMsg, globalMsg);
        getTransaction(bizMsg, globalMsg);

        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0020_NFDL0030================================END", this);
    }
    // END   2016/05/26 S.Fujita [QC#8339,ADD]

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020_NFDL0040(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020_NFDL0040================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        // START 2016/05/26 S.Fujita [QC#8339,ADD]
        getCollectionHeader(bizMsg, globalMsg);
        getCollectionNoteSpecial(bizMsg, globalMsg);
        // END   2016/05/26 S.Fujita [QC#8339,ADD]

        getTransaction(bizMsg, globalMsg);

        // START 2016/04/27 S.Fujita [QC#7218,MOD]
        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
        // END 2016/04/27 S.Fujita [QC#7218,MOD]

        EZDDebugOutput.println(1, "doProcess_NFDL0020_NFDL0040================================END", this);
    }

    // START 2016/05/26 S.Fujita [QC#8339,ADD]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020_NFDL0070(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020_NFDL0070================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        getCollectionHeader(bizMsg, globalMsg);
        getCollectionNoteSpecial(bizMsg, globalMsg);
        getTransaction(bizMsg, globalMsg);

        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0020_NFDL0070================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020_NFDL0090(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020_NFDL0090================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        getCollectionHeader(bizMsg, globalMsg);
        getCollectionNoteSpecial(bizMsg, globalMsg);
        getTransaction(bizMsg, globalMsg);

        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0020_NFDL0090================================END", this);
    }
    // END   2016/05/26 S.Fujita [QC#8339,ADD]

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020_NMAL6760(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020_NMAL6760================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        if ( ZYPCommonFunc.hasValue(bizMsg.billToCustCd_H) ) {
            doProcess_NFDL0020Scrn00_Click_SetBillToLocNm(cMsg, sMsg);
        }

        EZDDebugOutput.println(1, "doProcess_NFDL0020_NMAL6760================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_ContactInfoUpdate(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_ContactInfoUpdate================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;
        getCollectionHeader(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_ContactInfoUpdate================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_ContactInfoCancel(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_ContactInfoCancel================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;
        getCollectionHeader(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_ContactInfoCancel================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_TransactionSearch(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TransactionSearch================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;
        NFDL0020CommonLogic.setBilltoLocNm(getGlobalCompanyCode(), bizMsg);
        getTransaction(bizMsg, globalMsg);

        // START 2016/04/27 S.Fujita [QC#7218,MOD]
        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
        // END 2016/04/27 S.Fujita [QC#7218,MOD]

        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TransactionSearch================================END", this);
    }

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        doProcess_NFDL0020Scrn00_Click_NoteSearch(bizMsg, globalMsg);
        doProcess_NFDL0020Scrn00_Click_TaskSearch(bizMsg, globalMsg);
        getCollectionContract(bizMsg, globalMsg);
        getCollectionStrategy(bizMsg, globalMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltStrgyTrxPk_DH, getCltStrgyTrxPk(bizMsg));
        getCollectionStrategyWrkItem(bizMsg, globalMsg);

        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);

    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_TabTransaction(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TabTransaction================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Transaction");
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TabTransaction================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_TabNote(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TabNote================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Note");
        EZDDebugOutput.println(1, "doProcess_NFDL0020_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_TabTask(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TabTask================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Task");

        // START 2018/07/23 [QC#25780, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.cltTaskPk_PM)) {
            NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;
            showTaskDetail(bizMsg, globalMsg);
        }
        // END   2018/07/23 [QC#25780, ADD]

        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TabTask================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_TabContract(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TabContract================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Contract");
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TabContract================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_TabStrategy(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TabStrategy================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Strategy");
        // START 2018/07/23 [QC#25780, MOD]
        // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_CD, BigDecimal.ZERO);
        // END 2017/08/07 T.Tsuchida [QC#19576,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.cltStrgyWrkItemTrxPk_PM)
                && !ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_CD)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_CD, BigDecimal.ZERO);
        }
        // END   2018/07/23 [QC#25780, MOD]
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TabStrategy================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_NoteSearch(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_NoteSearch================================START", this);
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        bizMsg.xxPageShowFromNum_FH.clear();
        bizMsg.xxPageShowToNum_FH.clear();
        bizMsg.xxPageShowOfNum_FH.clear();
        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(globalMsg.F);
        getCollectionNote(bizMsg, globalMsg);

        // START 2016/04/27 S.Fujita [QC#7218,MOD]
        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
        // END 2016/04/27 S.Fujita [QC#7218,MOD]

        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_NoteSearch================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_TaskSearch(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TaskSearch================================START", this);
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        bizMsg.xxPageShowFromNum_GH.clear();
        bizMsg.xxPageShowToNum_GH.clear();
        bizMsg.xxPageShowOfNum_GH.clear();
        ZYPTableUtil.clear(bizMsg.G);
        ZYPTableUtil.clear(globalMsg.G);

        getCollectionTask(bizMsg, globalMsg);

        // START 2016/04/27 S.Fujita [QC#7218,MOD]
        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
        // END 2016/04/27 S.Fujita [QC#7218,MOD]

        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_TaskSearch================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_ContractSearch(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_ContractSearch================================START", this);
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        bizMsg.xxPageShowFromNum_BH.clear();
        bizMsg.xxPageShowToNum_BH.clear();
        bizMsg.xxPageShowOfNum_BH.clear();
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(globalMsg.B);
        getCollectionContract(bizMsg, globalMsg);

        // START 2016/04/27 S.Fujita [QC#7218,MOD]
        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
        // END 2016/04/27 S.Fujita [QC#7218,MOD]

        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_ContractSearch================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_CMN_Clear================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        bizMsg.billToCustCd_H.clear();
        // START 2016/06/22 K.Kojima [QC#10334,ADD]
        bizMsg.locNm_H.clear();
        // END 2016/06/22 K.Kojima [QC#10334,ADD]

        // START 2016/06/22 K.Kojima [QC#10334,ADD]
        bizMsg.arTrxTpCd_AH.clear();
        // END 2016/06/22 K.Kojima [QC#10334,ADD]
        // START 2016/06/17 K.Kojima [QC#10170,ADD]
        bizMsg.dsContrNum_AH.clear();
        // END 2016/06/17 K.Kojima [QC#10170,ADD]
        // START 2016/06/17 K.Kojima [QC#10172,ADD]
        bizMsg.serNum_AH.clear();
        // END 2016/06/17 K.Kojima [QC#10172,ADD]
        // START 2016/06/22 K.Kojima [QC#10334,ADD]
        bizMsg.xxChkBox_A1.clear();
        bizMsg.xxChkBox_A2.clear();
        bizMsg.xxChkBox_A3.clear();
        // END 2016/06/22 K.Kojima [QC#10334,ADD]
        // START 2018/06/15 [QC#24792, ADD]
        bizMsg.arCustRefNum_AH.clear();
        bizMsg.custIssPoNum_AH.clear();
        // END   2018/06/15 [QC#24792, ADD]

        // START 2016/06/17 K.Kojima [QC#10191,ADD]
        // START 2018/06/21 [QC#25080, MOD]
        // bizMsg.arCltNoteTpCd_FS.clear();
        bizMsg.cltNoteTpCd_FS.clear();
        // END   2018/06/21 [QC#25080, MOD]
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // bizMsg.dtlNoteTxt_FS.clear();
        bizMsg.xxMlBodyTxt_FS.clear();
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        // START 2016/06/22 K.Kojima [QC#10334,ADD]
        // START 2018/06/22 [QC#25080, ADD]
        bizMsg.cltNoteDtlPk_FH.clear();
        bizMsg.cratTsDplyTxt_FH.clear();
        // END   2018/06/22 [QC#25080, ADD]
        // START 2018/06/21 [QC#25080, MOD]
        // bizMsg.arCltNoteTpCd_FH.clear();
        bizMsg.cltNoteTpCd_FH.clear();
        // END   2018/06/21 [QC#25080, MOD]
        bizMsg.cratDt_FH.clear();
        bizMsg.colNoteSubjTxt_FH.clear();
        bizMsg.cratUsrId_FH.clear();
        bizMsg.xxPsnNm_FH.clear();
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        // bizMsg.dtlNoteTxt_FH.clear();
        bizMsg.xxMlBodyTxt_FH.clear();
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        // END 2016/06/22 K.Kojima [QC#10334,ADD]

        // END 2016/06/17 K.Kojima [QC#10191,ADD]
        // START 2016/07/27 K.Kojima [QC#10199,ADD]
        bizMsg.cltTaskSubjTxt_GS.clear();
        // END 2016/07/27 K.Kojima [QC#10199,ADD]
        // START 2016/06/17 K.Kojima [QC#10197,ADD]
        bizMsg.cltTaskTpCd_GS.clear();
        // END 2016/06/17 K.Kojima [QC#10197,ADD]
        // START 2016/06/22 K.Kojima [QC#10334,ADD]
        bizMsg.cltTaskTpCd_GH.clear();
        bizMsg.cltTaskStsCd_GH.clear();
        bizMsg.cltTaskSubjTxt_GH.clear();
        bizMsg.cltTaskOwnrId_GH.clear();
        // START 2016/07/07 K.Kojima [QC#10337,MOD]
        // bizMsg.xxPsnNm_G1.clear();
        bizMsg.cltPsnNm_G1.clear();
        // END 2016/07/07 K.Kojima [QC#10337,MOD]
        bizMsg.cratUsrId_GH.clear();
        bizMsg.xxPsnNm_G2.clear();
        bizMsg.cltTaskPrtyCd_GH.clear();
        bizMsg.cltTaskRwsdDt_GH.clear();
        bizMsg.cltTaskRwedDt_GH.clear();
        bizMsg.cltTaskCtacNm_GH.clear();
        bizMsg.cltTaskCtacPhoNum_GH.clear();
        bizMsg.cltTaskDescTxt_GH.clear();
        // START 2018/07/27 [QC#25767, ADD]
        bizMsg.updUsrId_GH.clear();
        bizMsg.xxPsnNm_G5.clear();
        bizMsg.cltTaskUpdDt_GH.clear();
        // END   2018/07/27 [QC#25767, ADD]

        bizMsg.dsContrTpCd_BH.clear();
        // END 2016/06/22 K.Kojima [QC#10334,ADD]
        // START 2018/03/15 J.Kim [QC#20945,ADD]
        bizMsg.arTrxNum_H1.clear();
        bizMsg.glDt_H1.clear();
        bizMsg.glDt_H2.clear();
        bizMsg.xxChkBox_H1.clear();
        // END 2018/03/15 J.Kim [QC#21422,ADD]
        // START 2018/03/26 J.Kim [QC#21422,ADD]
        bizMsg.I.clear();
        bizMsg.xxNum.clear();
        // END 2018/03/26 J.Kim [QC#21422,ADD]

        doProcess_NFDL0020_INIT(bizMsg, globalMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Transaction");

        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_CMN_Clear================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_SetBillToLocNm(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_SetBillToLocNm================================START", this);

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020CommonLogic.setBilltoLocNm(getGlobalCompanyCode(), bizMsg);
        EZDDebugOutput.println(1, "doProcess_NFDL0020Scrn00_Click_SetBillToLocNm================================END", this);
    }

    // START 2016/06/22 K.Kojima [QC#10529,ADD]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020Scrn00_Click_TaskSetOwnerName(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        if (ZYPCommonFunc.hasValue(bizMsg.cltTaskOwnrId_GH)) {
            NFDL0020CommonLogic.getCollectionPorsonName(bizMsg, getGlobalCompanyCode());
            // START 2016/07/07 K.Kojima [QC#10337,MOD]
            // if (!ZYPCommonFunc.hasValue(bizMsg.xxPsnNm_G1)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.cltPsnNm_G1)) {
                // END 2016/07/07 K.Kojima [QC#10337,MOD]
                bizMsg.cltTaskOwnrId_GH.setErrorInfo(1, ZZZM9006E, new String[] {"Owner" });
            }
        } else {
            // START 2016/07/07 K.Kojima [QC#10337,MOD]
            // bizMsg.xxPsnNm_G1.clear();
            bizMsg.cltPsnNm_G1.clear();
            // END 2016/07/07 K.Kojima [QC#10337,MOD]
        }
    }
    // END 2016/06/22 K.Kojima [QC#10529,ADD]

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getCollectionHeader(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        ssmMap.put("salesDt", ZYPDateUtil.getSalesDate());
        ssmMap.put("invTpCd", AR_TRX_TP.INVOICE);
        ssmMap.put("crmTpCd", AR_TRX_TP.CREDIT_MEMO);
        ssmMap.put("dedTpCd", AR_TRX_TP.DEDUCTION);
        ssmMap.put("accTpCd", AR_TRX_TP.ON_ACCOUNT);
        ssmMap.put("rcpTpCd", AR_TRX_TP.RECEIPT);
        ssmMap.put("applyStsU", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmMap.put("applyStsP", AR_CASH_APPLY_STS.PARTIAL);
        ssmMap.put("cltDsptStsApproved", CLT_DSPT_STS.APPROVED);
        ssmMap.put("cltPrmsStsCollectable", CLT_PRMS_STS.COLLECTIBLE);
        ssmMap.put("maxDt", MAX_DT);

        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getCollectionHeader(ssmMap, bizMsg);

        if (ssmResult.isCodeNormal()) {
        } else {
        }
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    // START 2022/03/29 [QC#59011,Add]
    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getLastPaymentData(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("rcpTpCd", AR_TRX_TP.RECEIPT);

        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getLastPaymentData(ssmMap, bizMsg);

        if (ssmResult.isCodeNormal()) {
        } else {
        }
    }
    // End 2022/03/29 [QC#59011,Add]

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getCollectionNoteSpecial(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cltAcctCd", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("cltAcctTpCd", "20");

        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getCollectionNoteSpecial(ssmMap);

        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cltHdrNoteTxt_H, (String) ssmResult.getResultObject());
            ZYPEZDItemValueSetter.setValue(bizMsg.cltHdrNoteTxt_HB, (String) ssmResult.getResultObject());
        } else {
            bizMsg.cltHdrNoteTxt_H.clear();
        }
    }

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getTransaction(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        ZYPTableUtil.clear(globalMsg.A);

        S21SsmEZDResult ssmResult = NFDL0020CommonLogic.queryTransaction(bizMsg, globalMsg, getGlobalCompanyCode());

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.A.length()) {

                queryResCnt = globalMsg.A.length();
                // START 2022/12/14 Y.Mochida [QC#60683,MOD]
                bizMsg.setMessageInfo("NZZM0009W", new String[]{"Transaction"});
                // END 2022/12/14 Y.Mochida [QC#60683,MOD]
            }

            int i;
            for (i = 0; i < globalMsg.A.getValidCount(); i++) {

                if (i < bizMsg.A.length()) {

                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);

                } else {
                    break;
                }
            }

            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum_AH.setValue(1);
            bizMsg.xxPageShowToNum_AH.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_AH.setValue(queryResCnt);

        } else {
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum_AH.setValue(1);
            bizMsg.xxPageShowToNum_AH.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_AH.setValue(0);
        }

        // START 2019/02/04 [QC#30161, ADD]
        bizMsg.dealRmngBalGrsAmt_AH.setValue(BigDecimal.ZERO);
        // END   2019/02/04 [QC#30161, ADD]
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getCollectionNote(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {
        S21SsmEZDResult ssmResult = NFDL0020CommonLogic.queryCollectionNote(bizMsg, globalMsg, getGlobalCompanyCode());

     // Start 2022/12/12 S.Fujita [QC#60406, ADD]
        try{
        // End 2022/12/12 S.Fujita [QC#60406, ADD]
            if (ssmResult.isCodeNormal()) {
    
                int queryResCnt = ssmResult.getQueryResultCount();
    
                if (queryResCnt > globalMsg.F.length()) {
    
                    queryResCnt = globalMsg.F.length();
                    // START 2022/12/14 Y.Mochida [QC#60683,MOD]
                    bizMsg.setMessageInfo("NZZM0009W", new String[]{"Note"});
                    // END 2022/12/14 Y.Mochida [QC#60683,MOD]
                }
    
                int i;
                // Start 2022/12/12 S.Fujita [QC#60406, MOD]
//                for (i = 0; i < globalMsg.F.getValidCount(); i++) {
//    
//                    if (i < bizMsg.F.length()) {
//    
//                        EZDMsg.copy(globalMsg.F.no(i), null, bizMsg.F.no(i), null);
//    
//                    } else {
//                        break;
//                    }
//                }
                
                List<Map<String, Object>> cltNoteList = (List<Map<String, Object>>) ssmResult.getResultObject();
    
                for (i = 0; i < cltNoteList.size(); i++) {
                    Map<String, Object> cltNote = cltNoteList.get(i);
    
                        // Start 2023/02/24 S.Fujita [QC#61220, MOD]
//                    if (i < bizMsg.F.length()) {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).cltNoteDtlPk_FD, (BigDecimal) cltNote.get("CLT_NOTE_DTL_PK_FD"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).cltNoteTpCd_FD, (String) cltNote.get("CLT_NOTE_TP_CD"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).cltNoteTpDescTxt_FD, (String) cltNote.get("CLT_NOTE_TP_DESC_TXT"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).colNoteSubjTxt_FD, (String) cltNote.get("COL_NOTE_SUBJ_TXT_FD"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).cratDt_FD, (String) cltNote.get("CRAT_DT_FD"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).cratTsDplyTxt_FD, (String) cltNote.get("CRAT_TS_DPLY_TXT_FD"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).xxSortKeyTxt_FD, (String) cltNote.get("XX_SORT_KEY_TXT_FD"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).cratUsrId_FD, (String) cltNote.get("CRAT_USR_ID_FD"));
//                        ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).xxPsnNm_FD, (String) cltNote.get("XX_PSN_NM_FD"));
//                        if(cltNote.get("DTL_NOTE_CLOD") != null){
//                            ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).xxMlBodyTxt_FD, NFDL0020CommonLogic.clobToString((Clob) cltNote.get("DTL_NOTE_CLOD")));
//                        } else{
//                            ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).xxMlBodyTxt_FD, (String) cltNote.get("DTL_NOTE_TXT_FD"));
//                        }
                    if (i < globalMsg.F.length()) {
                        ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).cltNoteDtlPk_FD, (BigDecimal) cltNote.get("CLT_NOTE_DTL_PK_FD"));
                        ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).cltNoteTpCd_FD, (String) cltNote.get("CLT_NOTE_TP_CD"));
                        ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).cltNoteTpDescTxt_FD, (String) cltNote.get("CLT_NOTE_TP_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).colNoteSubjTxt_FD, (String) cltNote.get("COL_NOTE_SUBJ_TXT_FD"));
                        ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).cratDt_FD, (String) cltNote.get("CRAT_DT_FD"));
                        ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).cratTsDplyTxt_FD, (String) cltNote.get("CRAT_TS_DPLY_TXT_FD"));
                        ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).xxSortKeyTxt_FD, (String) cltNote.get("XX_SORT_KEY_TXT_FD"));
                        ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).cratUsrId_FD, (String) cltNote.get("CRAT_USR_ID_FD"));
                        ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).xxPsnNm_FD, (String) cltNote.get("XX_PSN_NM_FD"));
                        if(cltNote.get("DTL_NOTE_CLOD") != null){
                            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).xxMlBodyTxt_FD, NFDL0020CommonLogic.clobToString((Clob) cltNote.get("DTL_NOTE_CLOD")));
                        } else{
                            ZYPEZDItemValueSetter.setValue(globalMsg.F.no(i).xxMlBodyTxt_FD, (String) cltNote.get("DTL_NOTE_TXT_FD"));
                        }
                        if (i < bizMsg.F.length()) {
                            EZDMsg.copy(globalMsg.F.no(i), null, bizMsg.F.no(i), null);
                            bizMsg.F.setValidCount(i + 1);
                        }
                        // END 2023/02/24 S.Fujita [QC#61220, MOD]
    
                    } else {
                        break;
                    }
                }
                // End 2022/12/12 S.Fujita [QC#60406, MOD]

                // Start 2023/02/24 S.Fujita [QC#61220, MOD]
                //bizMsg.F.setValidCount(i);
                globalMsg.F.setValidCount(i);
                // End 2023/02/24 S.Fujita [QC#61220, MOD]
    
                bizMsg.xxPageShowFromNum_FH.setValue(1);
                bizMsg.xxPageShowToNum_FH.setValue(bizMsg.F.getValidCount());
                bizMsg.xxPageShowOfNum_FH.setValue(queryResCnt);
    
            } else {
                bizMsg.F.setValidCount(0);
                bizMsg.xxPageShowFromNum_FH.setValue(1);
                bizMsg.xxPageShowToNum_FH.setValue(bizMsg.F.getValidCount());
                bizMsg.xxPageShowOfNum_FH.setValue(0);
            }
        // Start 2022/12/12 S.Fujita [QC#60406, ADD]
        } catch (SQLException e) {
            throw new S21AbendException(e);
        }
        // End 2022/12/12 S.Fujita [QC#60406, ADD]
    }

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getCollectionTask(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {
        S21SsmEZDResult ssmResult = NFDL0020CommonLogic.queryCollectionTask(bizMsg, globalMsg, getGlobalCompanyCode());

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.G.length()) {

                queryResCnt = globalMsg.G.length();
                // START 2022/12/14 Y.Mochida [QC#60683,MOD]
                bizMsg.setMessageInfo("NZZM0009W", new String[]{"Task"});
                // END 2022/12/14 Y.Mochida [QC#60683,MOD]
            }

            int i;
            for (i = 0; i < globalMsg.G.getValidCount(); i++) {

                if (i < bizMsg.G.length()) {

                    EZDMsg.copy(globalMsg.G.no(i), null, bizMsg.G.no(i), null);

                } else {
                    break;
                }
            }

            bizMsg.G.setValidCount(i);

            bizMsg.xxPageShowFromNum_GH.setValue(1);
            bizMsg.xxPageShowToNum_GH.setValue(bizMsg.G.getValidCount());
            bizMsg.xxPageShowOfNum_GH.setValue(queryResCnt);

        } else {
            bizMsg.G.setValidCount(0);
            bizMsg.xxPageShowFromNum_GH.setValue(1);
            bizMsg.xxPageShowToNum_GH.setValue(bizMsg.G.getValidCount());
            bizMsg.xxPageShowOfNum_GH.setValue(0);
        }
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getCollectionContract(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {
        S21SsmEZDResult ssmResult = NFDL0020CommonLogic.queryCollectionContract(bizMsg, globalMsg, getGlobalCompanyCode());

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.B.length()) {

                queryResCnt = globalMsg.B.length();
                // START 2022/12/14 Y.Mochida [QC#60683,MOD]
                bizMsg.setMessageInfo("NZZM0009W", new String[]{"Contract"});
                // END 2022/12/14 Y.Mochida [QC#60683,MOD]
            }

            int i;
            for (i = 0; i < globalMsg.B.getValidCount(); i++) {

                if (i < bizMsg.B.length()) {

                    EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);

                } else {
                    break;
                }
            }

            bizMsg.B.setValidCount(i);

            bizMsg.xxPageShowFromNum_BH.setValue(1);
            bizMsg.xxPageShowToNum_BH.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_BH.setValue(queryResCnt);

        } else {
            bizMsg.B.setValidCount(0);
            bizMsg.xxPageShowFromNum_BH.setValue(1);
            bizMsg.xxPageShowToNum_BH.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_BH.setValue(0);
        }
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getCollectionStrategy(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cltAcctCd", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_C.getValue())) {
            ssmMap.put("cltStrgyStsCd", CLT_STRGY_STS.OPEN);
        }
        ssmMap.put("rowNum", String.valueOf(bizMsg.C.length() + 1));
        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getCollectionStrategy(ssmMap, bizMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > bizMsg.C.length()) {

                queryResCnt = bizMsg.C.length();
                // START 2022/12/14 Y.Mochida [QC#60683,MOD]
                bizMsg.setMessageInfo("NZZM0009W", new String[]{"Strategy"});
                // END 2022/12/14 Y.Mochida [QC#60683,MOD]
            }

        } else {
            bizMsg.C.setValidCount(0);
        }
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getCollectionStrategyWrkItem(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cltAcctCd", bizMsg.dsAcctNum_H.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.cltStrgyTrxPk_DH)) {
            ssmMap.put("cltStrgyTrxPk", String.valueOf(bizMsg.cltStrgyTrxPk_DH.getValue()));
        }
        ssmMap.put("rowNum", String.valueOf(bizMsg.D.length() + 1));
        ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        ssmMap.put("cltPsnNm", ZYPCodeDataUtil.getVarCharConstValue("NFDL0020_ASSIGNED_TO_BATCH", getGlobalCompanyCode()));
        ssmMap.put("cltPrintStsCdIsPrinted", CLT_PRINT_STS.PRINTED);
        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_C.getValue())) {
            ssmMap.put("cltStrgyStsCd", CLT_STRGY_STS.OPEN);
        }
        bizMsg.cltStrgyTrxPk_DH.clear();

        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getCollectionStrategyWrkItem(ssmMap, bizMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > bizMsg.D.length()) {

                queryResCnt = bizMsg.D.length();
                bizMsg.setMessageInfo("NZZM0001W");
            }
            // START 2018/05/11 J.Kim [QC#21426,ADD]
            // START 2021/05/25 G.Delgado [QC#58704,DEL]
//            boolean prevCompletedList = false;
//            List<String> prevCompList = new ArrayList<String>();
//            List<String> prevWrkItemList = new ArrayList<String>();
//            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
//                String cltWrkTpCd_DD = bizMsg.D.no(i).cltWrkTpCd_DD.getValue();
//                String cltWrkItemStsCd_DD = bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue();
//                if (CLT_WRK_TP.AUTOMATIC.equals(cltWrkTpCd_DD)) {
//                    continue;
//                }
//                if (!CLT_WRK_ITEM_STS.COMPLETED.equals(cltWrkItemStsCd_DD) && !prevWrkItemList.isEmpty() && (prevWrkItemList.contains(CLT_WRK_ITEM_STS.PENDING) || prevWrkItemList.contains(CLT_WRK_ITEM_STS.OPEN))) {
//                    bizMsg.D.no(i).cltWrkItemRwsdDt_DD.clear();
//                    bizMsg.D.no(i).cltWrkItemRwedDt_DD.clear();
//                }
//                if (!prevCompletedList && !CLT_WRK_ITEM_STS.COMPLETED.equals(cltWrkItemStsCd_DD) && !prevCompList.isEmpty() && (prevCompList.contains(CLT_WRK_ITEM_STS.COMPLETED))) {
//                    if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).cltWrkItemRwsdDt_DD)) {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).cltWrkItemRwsdDt_DD, ZYPDateUtil.getSalesDate());
//                    }
//                    if (!ZYPCommonFunc.hasValue(bizMsg.D.no(i).cltWrkItemRwedDt_DD)) {
//                        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).cltWrkItemRwedDt_DD, ZYPDateUtil.getSalesDate());
//                    }
//                    prevCompletedList = true;
//                }
//
//                if (!CLT_WRK_ITEM_STS.COMPLETED.equals(cltWrkItemStsCd_DD)) {
//                    prevWrkItemList.add(cltWrkItemStsCd_DD);
//                }
//                if (CLT_WRK_ITEM_STS.COMPLETED.equals(cltWrkItemStsCd_DD)) {
//                    prevCompList.add(cltWrkItemStsCd_DD);
//                }
//            }
            // END 2021/05/25 G.Delgado [QC#58704,DEL]
            // END 2018/05/11 J.Kim [QC#21426,ADD]

        } else {
            bizMsg.D.setValidCount(0);
        }
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getTrxTypeList(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        String arTrxTpCd = ZYPCodeDataUtil.getVarCharConstValue("NFDL0020_CLASS_TRX_TP", getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(arTrxTpCd)) {
            String[] arTrxTpCdList = arTrxTpCd.split(",");
            ssmMap.put("arTrxTpCdList", arTrxTpCdList);
        } else {
            ssmMap.put("arTrxTpCdList", null);
        }
        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getTrxTypeList(ssmMap);

        List resultList = (List) ssmResult.getResultObject();
        for (int iCnt = 0; iCnt < resultList.size(); iCnt++) {
            Map map = (Map) resultList.get(iCnt);
            ZYPEZDItemValueSetter.setValue(bizMsg.arTrxTpCd_LC.no(iCnt), (String) map.get("AR_TRX_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.arTrxTpDescTxt_LD.no(iCnt), (String) map.get("AR_TRX_TP_DESC_TXT"));
        }
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getContactTypeCodeList(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        String ctacTpCd = ZYPCodeDataUtil.getVarCharConstValue("NFDL0020_CTAC_TP_LIST", getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(ctacTpCd)) {
            String[] arTrxTpCdList = ctacTpCd.split(",");
            ssmMap.put("ctacTpCdList", arTrxTpCdList);
        } else {
            ssmMap.put("ctacTpCdList", null);
        }
        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getContactTypeCodeList(ssmMap);

        List resultList = (List) ssmResult.getResultObject();
        for (int iCnt = 0; iCnt < resultList.size(); iCnt++) {
            Map map = (Map) resultList.get(iCnt);
            ZYPEZDItemValueSetter.setValue(bizMsg.ctacTpCd_LC.no(iCnt), (String) map.get("CTAC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.ctacTpDescTxt_LD.no(iCnt), (String) map.get("CTAC_TP_DESC_TXT"));
        }
    }

    // START 2016/07/19 K.Kojima [QC#11478,ADD]
    private void doProcess_NFDL0020Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
        if (sortTblNm.equals("A")) {
            copyCurrentPageToSMsg(bizMsg, globalMsg, bizMsg.xxPageShowFromNum_AH.getValueInt(), bizMsg.A.length());

            bizMsg.xxPageShowFromNum_AH.clear();
            bizMsg.xxPageShowToNum_AH.clear();
            bizMsg.xxPageShowOfNum_AH.clear();

            S21SortTarget sortTarget = new S21SortTarget(globalMsg.A, globalMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.A.getValidCount());

            EZDMsg.copy(globalMsg.A, null, bizMsg.A, null);
            bizMsg.xxPageShowFromNum_AH.setValue(1);
            bizMsg.xxPageShowToNum_AH.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_AH.setValue(globalMsg.A.getValidCount());
        }
        // END 2017/08/07 T.Tsuchida [QC#19576,ADD]

        if (sortTblNm.equals("B")) {
            // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
            copyCurrentPageToSMsg(bizMsg, globalMsg, bizMsg.xxPageShowFromNum_BH.getValueInt(), bizMsg.B.length());
            // END 2017/08/07 T.Tsuchida [QC#19576,ADD]

            bizMsg.xxPageShowFromNum_BH.clear();
            bizMsg.xxPageShowToNum_BH.clear();
            bizMsg.xxPageShowOfNum_BH.clear();

            S21SortTarget sortTarget = new S21SortTarget(globalMsg.B, globalMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.B.getValidCount());

            EZDMsg.copy(globalMsg.B, null, bizMsg.B, null);
            bizMsg.xxPageShowFromNum_BH.setValue(1);
            bizMsg.xxPageShowToNum_BH.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_BH.setValue(globalMsg.B.getValidCount());
        }

        // START 2018/03/15 J.Kim [QC#20945,ADD]
        if (sortTblNm.equals("H")) {
            copyCurrentPageToSMsg(bizMsg, globalMsg, bizMsg.xxPageShowFromNum_H.getValueInt(), bizMsg.H.length());

            bizMsg.xxPageShowFromNum_H.clear();
            bizMsg.xxPageShowToNum_H.clear();
            bizMsg.xxPageShowOfNum_H.clear();

            S21SortTarget sortTarget = new S21SortTarget(globalMsg.H, globalMsg.H.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.H.getValidCount());

            EZDMsg.copy(globalMsg.H, null, bizMsg.H, null);
            bizMsg.xxPageShowFromNum_H.setValue(1);
            bizMsg.xxPageShowToNum_H.setValue(bizMsg.H.getValidCount());
            bizMsg.xxPageShowOfNum_H.setValue(globalMsg.H.getValidCount());
        }
        // END 2018/03/15 J.Kim [QC#20945,ADD]

        // START 2018/05/16 [QC#24329,ADD]
        if (sortTblNm.equals("J")) {

            bizMsg.xxPageShowFromNum_J.clear();
            bizMsg.xxPageShowToNum_J.clear();
            bizMsg.xxPageShowOfNum_J.clear();

            S21SortTarget sortTarget = new S21SortTarget(globalMsg.J, globalMsg.J.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.J.getValidCount());

            EZDMsg.copy(globalMsg.J, null, bizMsg.J, null);
            bizMsg.xxPageShowFromNum_J.setValue(1);
            bizMsg.xxPageShowToNum_J.setValue(bizMsg.J.getValidCount());
            bizMsg.xxPageShowOfNum_J.setValue(globalMsg.J.getValidCount());
        }
        // END 2018/05/16 [QC#24329,ADD]

        // START 2018/05/21 S.Katsuma [QC#24793,ADD]
        if (sortTblNm.equals("F")) {

            bizMsg.xxPageShowFromNum_FH.clear();
            bizMsg.xxPageShowToNum_FH.clear();
            bizMsg.xxPageShowOfNum_FH.clear();

            S21SortTarget sortTarget = new S21SortTarget(globalMsg.F, globalMsg.F.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.F.getValidCount());

            EZDMsg.copy(globalMsg.F, null, bizMsg.F, null);
            bizMsg.xxPageShowFromNum_FH.setValue(1);
            bizMsg.xxPageShowToNum_FH.setValue(bizMsg.F.getValidCount());
            bizMsg.xxPageShowOfNum_FH.setValue(globalMsg.F.getValidCount());
        }

        if (sortTblNm.equals("G")) {

            bizMsg.xxPageShowFromNum_GH.clear();
            bizMsg.xxPageShowToNum_GH.clear();
            bizMsg.xxPageShowOfNum_GH.clear();

            S21SortTarget sortTarget = new S21SortTarget(globalMsg.G, globalMsg.G.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.G.getValidCount());

            EZDMsg.copy(globalMsg.G, null, bizMsg.G, null);
            bizMsg.xxPageShowFromNum_GH.setValue(1);
            bizMsg.xxPageShowToNum_GH.setValue(bizMsg.G.getValidCount());
            bizMsg.xxPageShowOfNum_GH.setValue(globalMsg.G.getValidCount());
        }

        if (sortTblNm.equals("C")) {
            S21SortTarget sortTarget = new S21SortTarget(bizMsg.C, bizMsg.C.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, bizMsg.C.getValidCount());
        }
        // END 2018/05/21 S.Katsuma [QC#24793,ADD]
    }
    // END 2016/07/19 K.Kojima [QC#11478,ADD]

    // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
    private void copyCurrentPageToSMsg(NFDL0020CMsg cMsg, NFDL0020SMsg sMsg, int fromPageNum, int recordsOfPage) {

        int fromIdx = fromPageNum - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromIdx + i), null);
        }
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,ADD]

    // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
    private void doProcess_NFDL0020Scrn00_OnChange_ChkBoxDisplayStrategies(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_C.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_C, ZYPConstant.CHKBOX_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_C, ZYPConstant.FLG_OFF_N);
        }
        getCollectionStrategy(bizMsg, globalMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltStrgyTrxPk_DH, getCltStrgyTrxPk(bizMsg));
        getCollectionStrategyWrkItem(bizMsg, globalMsg);

        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,MOD]

    // START 2017/03/07 T.Murai [QC#17193,ADD]
    private void doProcess_NFDL0020Scrn00_Click_TransactionPayment(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        // START 2019/01/24 [QC#30008, ADD]
        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo("NFAM0075E");
            return;
        }
        // END   2019/01/24 [QC#30008, ADD]
        NFDL0020CommonLogic.copyToSMsg(bizMsg, globalMsg);

        List<Integer> selectedRowsA = ZYPTableUtil.getSelectedRows(globalMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

        if (selectedRowsA.size() == 0) {
            bizMsg.setMessageInfo("NFAM0075E");
            return;
        }

        // START 2020/01/28 [QC#55017, ADD]
        if (selectedRowsA.size() > NFDL0020Constant.INV_LIMIT_COUNT) {
            bizMsg.setMessageInfo("NFBM0105E", new String[]{"Checkbox", "99"});
            return;
        }
        // END   2020/01/28 [QC#55017, ADD]

        StringBuilder invNumList = null;
        boolean errFlg = false;
        int firstErrIdx = 0;

        for (Integer idx : selectedRowsA) {
            // START 2018/06/25 [QC#26239, MOD]
            // Invoice Only
            // if (AR_TRX_TP.CREDIT_MEMO.equals(globalMsg.A.no(idx).arTrxTpCd_A.getValue()) //
            //         || AR_TRX_TP.RECEIPT.equals(globalMsg.A.no(idx).arTrxTpCd_A.getValue())) {
            if (AR_TRX_TP.RECEIPT.equals(globalMsg.A.no(idx).arTrxTpCd_A.getValue())) {
            // END   2018/06/25 [QC#26239, MOD]

                globalMsg.A.no(idx).xxChkBox_A.setErrorInfo(1, "NFDM0045E");
                if (!errFlg) {
                    firstErrIdx = idx;
                    errFlg = true;
                }
            // START 2020/02/18 [QC#55664, ADD]
            } else if (AR_TRX_TP.ON_ACCOUNT.equals(globalMsg.A.no(idx).arTrxTpCd_A.getValue())) {

                globalMsg.A.no(idx).xxChkBox_A.setErrorInfo(1, "NFDM0052E");
                if (!errFlg) {
                    firstErrIdx = idx;
                    errFlg = true;
                }
            // END  2020/02/18 [QC#55664, ADD]
            } else if (!errFlg) {
                if (invNumList == null) {
                    invNumList = new StringBuilder(globalMsg.A.no(idx).arTrxNum_A.getValue());
                } else {
                    invNumList.append(",");
                    invNumList.append(globalMsg.A.no(idx).arTrxNum_A.getValue());
                }
            }
        }
        if (!errFlg) {
            // START 2020/01/29 [QC#55017, MOD]
            // ZYPEZDItemValueSetter.setValue(bizMsg.xxTrxCdSrchTxt, invNumList.toString());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTrxNumSrchTxt, invNumList.toString());
            // END   2020/01/29 [QC#55017, MOD]
        } else {
            int pageNum = firstErrIdx / bizMsg.A.length() + 1;
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_AH, new BigDecimal((pageNum - 1) * bizMsg.A.length() + 1));

            NFDL0020CommonLogic.dispPage(bizMsg, globalMsg);
        }
    }
    // END   2017/03/07 T.Murai [QC#17193,ADD]

    // START 2017/08/07 T.Tsuchida [QC#19576,ADD]
    private void doProcess_NFDL0020Scrn00_Click_AgingCurrent(EZDCMsg cMsg, EZDSMsg sMsg) {
        String salesDt = ZYPDateUtil.getSalesDate();
        String fromDueDt = salesDt;
        String toDueDt = "";
        setSearchAging(cMsg, sMsg, fromDueDt, toDueDt);
        doProcess_NFDL0020Scrn00_Click_TabTransaction(cMsg, sMsg);
        doProcess_NFDL0020Scrn00_Click_TransactionSearch(cMsg, sMsg);
        ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
    }

    private void doProcess_NFDL0020Scrn00_Click_Aging30(EZDCMsg cMsg, EZDSMsg sMsg) {
        String salesDt = ZYPDateUtil.getSalesDate();
        String fromDueDt = ZYPDateUtil.addDays(salesDt, -30);
        String toDueDt = ZYPDateUtil.addDays(salesDt, -1);
        setSearchAging(cMsg, sMsg, fromDueDt, toDueDt);
        doProcess_NFDL0020Scrn00_Click_TabTransaction(cMsg, sMsg);
        doProcess_NFDL0020Scrn00_Click_TransactionSearch(cMsg, sMsg);
        ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
    }

    private void doProcess_NFDL0020Scrn00_Click_Aging60(EZDCMsg cMsg, EZDSMsg sMsg) {
        String salesDt = ZYPDateUtil.getSalesDate();
        String fromDueDt = ZYPDateUtil.addDays(salesDt, -60);
        String toDueDt = ZYPDateUtil.addDays(salesDt, -31);
        setSearchAging(cMsg, sMsg, fromDueDt, toDueDt);
        doProcess_NFDL0020Scrn00_Click_TabTransaction(cMsg, sMsg);
        doProcess_NFDL0020Scrn00_Click_TransactionSearch(cMsg, sMsg);
        ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
    }

    private void doProcess_NFDL0020Scrn00_Click_Aging90(EZDCMsg cMsg, EZDSMsg sMsg) {
        String salesDt = ZYPDateUtil.getSalesDate();
        String fromDueDt = ZYPDateUtil.addDays(salesDt, -90);
        String toDueDt = ZYPDateUtil.addDays(salesDt, -61);
        setSearchAging(cMsg, sMsg, fromDueDt, toDueDt);
        doProcess_NFDL0020Scrn00_Click_TabTransaction(cMsg, sMsg);
        doProcess_NFDL0020Scrn00_Click_TransactionSearch(cMsg, sMsg);
        ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
    }

    private void doProcess_NFDL0020Scrn00_Click_Aging180(EZDCMsg cMsg, EZDSMsg sMsg) {
        String salesDt = ZYPDateUtil.getSalesDate();
        String fromDueDt = ZYPDateUtil.addDays(salesDt, -180);
        String toDueDt = ZYPDateUtil.addDays(salesDt, -91);
        setSearchAging(cMsg, sMsg, fromDueDt, toDueDt);
        doProcess_NFDL0020Scrn00_Click_TabTransaction(cMsg, sMsg);
        doProcess_NFDL0020Scrn00_Click_TransactionSearch(cMsg, sMsg);
        ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
    }

    private void doProcess_NFDL0020Scrn00_Click_Aging360(EZDCMsg cMsg, EZDSMsg sMsg) {
        String salesDt = ZYPDateUtil.getSalesDate();
        // START 2018/06/12 [QC#26373, MOD]
        // String fromDueDt = ZYPDateUtil.addDays(salesDt, -360);
        String fromDueDt = ZYPDateUtil.addDays(salesDt, -365);
        // END   2018/06/12 [QC#26373, MOD]
        String toDueDt = ZYPDateUtil.addDays(salesDt, -181);
        setSearchAging(cMsg, sMsg, fromDueDt, toDueDt);
        doProcess_NFDL0020Scrn00_Click_TabTransaction(cMsg, sMsg);
        doProcess_NFDL0020Scrn00_Click_TransactionSearch(cMsg, sMsg);
        ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
    }

    private void doProcess_NFDL0020Scrn00_Click_Aging361(EZDCMsg cMsg, EZDSMsg sMsg) {
        String salesDt = ZYPDateUtil.getSalesDate();
        String fromDueDt = "";
        // START 2018/06/12 [QC#26373, MOD]
        // String toDueDt = ZYPDateUtil.addDays(salesDt, -361);
        String toDueDt = ZYPDateUtil.addDays(salesDt, -366);
        // END   2018/06/12 [QC#26373, MOD]
        setSearchAging(cMsg, sMsg, fromDueDt, toDueDt);
        doProcess_NFDL0020Scrn00_Click_TabTransaction(cMsg, sMsg);
        doProcess_NFDL0020Scrn00_Click_TransactionSearch(cMsg, sMsg);
        ZYPGUITableColumn.getColData((NFDL0020CMsg) cMsg, (NFDL0020SMsg) sMsg, "AHEAD");
    }

    private void setSearchAging(EZDCMsg cMsg, EZDSMsg sMsg, String fromDueDt, String toDueDt) {
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        ZYPEZDItemValueSetter.setValue(bizMsg.invDueDt_A1, fromDueDt);
        ZYPEZDItemValueSetter.setValue(bizMsg.invDueDt_A2, toDueDt);
        bizMsg.dsContrNum_AH.clear();
        bizMsg.serNum_AH.clear();
        if (ZYPCommonFunc.hasValue(toDueDt)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_A1, ZYPConstant.FLG_ON_Y);
        }
        // START 2018/02/01 T.Tsuchida [QC#23895,MOD]
        bizMsg.xxChkBox_A2.clear();
        bizMsg.xxChkBox_A3.clear();
        // END 2018/02/01 T.Tsuchida [QC#23895,MOD]
        doProcess_NFDL0020Scrn00_Click_TabStrategy(cMsg, sMsg);
        doProcess_NFDL0020Scrn00_Click_TransactionSearch(cMsg, sMsg);
    }

    private void doProcess_NFDL0020Scrn00_OnChange_StrategyStatus(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        String[] cltWrkItemStsList = { CLT_WRK_ITEM_STS.PENDING, CLT_WRK_ITEM_STS.OPEN };

        // START 2018/03/23 J.Kim [QC#21422,MOD]
        int selectIdx = bizMsg.xxNum.getValueInt();
        if (CLT_STRGY_STS.OPEN.equals(bizMsg.C.no(selectIdx).cltStrgyStsCd_CD.getValue())) {

            for (int idx = 0; idx < bizMsg.I.getValidCount(); idx++) {
                int rowNum = bizMsg.I.no(idx).xxRowNum.getValueInt();
                if (!CLT_WRK_ITEM_STS.COMPLETED.equals(bizMsg.D.no(rowNum).cltWrkItemStsCd_DD.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(rowNum).cltWrkItemStsCd_DD, CLT_WRK_ITEM_STS.PENDING);
                }
            }
            bizMsg.I.clear();

        } else {

            int index = 0;
            bizMsg.I.clear();
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                if (Arrays.asList(cltWrkItemStsList).contains(bizMsg.D.no(i).cltWrkItemStsCd_DD.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).cltWrkItemStsCd_DD, CLT_WRK_ITEM_STS.CANCEL);
                    ZYPEZDItemValueSetter.setValue(bizMsg.I.no(index).xxRowNum, new BigDecimal(i));
                    index++;
                }
            }

            bizMsg.I.setValidCount(index);
        }
        // END 2018/03/23 J.Kim [QC#21422,MOD]
    }

    private void doProcess_NFDL0020Scrn00_OnChange_Strategy(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.cltStrgyTrxPk_DH, getCltStrgyTrxPk(bizMsg));
        getCollectionStrategyWrkItem(bizMsg, globalMsg);
        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
    }

    private BigDecimal getCltStrgyTrxPk(NFDL0020CMsg bizMsg) {
        int selectIdx = bizMsg.xxRadioBtn_CD.getValueInt();
        if (selectIdx + 1 > bizMsg.C.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_CD, BigDecimal.ZERO);
            selectIdx = bizMsg.xxRadioBtn_CD.getValueInt();
        }
        if (bizMsg.C.getValidCount() > 0) {
            return bizMsg.C.no(selectIdx).cltStrgyTrxPk_CD.getValue();
        }
        return null;
    }
    // END 2017/08/07 T.Tsuchida [QC#19576,ADD]

    // START 2018/03/15 J.Kim [QC#20945,ADD]
    /**
     * 
     */
    private void doProcess_NFDL0020Scrn00_Click_TabAdjHistory(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "AdjHistory");
    }

    private void doProcess_NFDL0020Scrn00_Click_AdjHistorySearch(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        String glblCmpyCd = getGlobalCompanyCode();
        NFDL0020CommonLogic.getAdjHistory(bizMsg, globalMsg, glblCmpyCd);

        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0020_NFCL0860(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        getCollectionHeader(bizMsg, globalMsg);
        getCollectionNoteSpecial(bizMsg, globalMsg);
        getTransaction(bizMsg, globalMsg);

        NFDL0020CommonLogic.showPage(bizMsg, globalMsg);
    }

    /**
     * CMN_Download Event
     * @param bizMsg NFDL0020CMsg
     * @param glblMsg NFDL0020SMsg
     */
    private void doProcess_NFDL0020Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        String glblCmpyCd = getGlobalCompanyCode();

        // START 2018/05/16 [QC#24329,MOD]
        String tab = bizMsg.xxDplyTab_H.getValue();
        if ("AdjHistory".equals(tab)) {
            NFDL0020CommonLogic.writeCsvFile(bizMsg, globalMsg, glblCmpyCd);

        } else if ("Statement".equals(tab)) {
            NFDL0020CommonLogic.writeStatementCsvFile(bizMsg, globalMsg, glblCmpyCd);
        // START 2018/05/21 S.Katsuma [QC#24793,ADD]
        } else if ("Transaction".equals(tab)) {
            NFDL0020CommonLogic.writeTransactiontCsvFile(bizMsg, globalMsg, glblCmpyCd);
        } else if ("Note".equals(tab)) {
            NFDL0020CommonLogic.writeNoteCsvFile(bizMsg, globalMsg, glblCmpyCd);
        } else if ("Task".equals(tab)) {
            NFDL0020CommonLogic.writeTaskCsvFile(bizMsg, globalMsg, glblCmpyCd);
        } else if ("Contract".equals(tab)) {
            NFDL0020CommonLogic.writeContractCsvFile(bizMsg, globalMsg, glblCmpyCd);
        }
        // END 2018/05/21 S.Katsuma [QC#24793,ADD]
        // END 2018/05/16 [QC#24329,MOD]
    }
    // END 2018/03/15 J.Kim [QC#20945,ADD]

    // START 2018/05/16 [QC#24329,ADD]
    private void doProcess_NFDL0020Scrn00_Click_TabStatement(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H, "Statement");
    }
    // END 2018/05/16 [QC#24329,ADD]

    // START 2018/06/04 Y.Matsui [QC#24809,ADD]
    private void doProcess_NFDL0020Scrn00_Click_AcctBillTo(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;

        NFDL0020CommonLogic.setBilltoLocNm(getGlobalCompanyCode(), bizMsg);
    }
    // END   2018/06/04 Y.Matsui [QC#24809,ADD]

    // START 2018/07/23 [QC#25780, ADD]
    private void showTaskDetail(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {
        int selectIdx = -1;
        for (int i = 0 ; i < globalMsg.G.getValidCount(); i++) {
            if (bizMsg.cltTaskPk_PM.getValue().equals(globalMsg.G.no(i).cltTaskPk_GD.getValue())) {
                selectIdx = i;
                break;
            }
        }
        if (selectIdx == -1) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskPk_GH, globalMsg.G.no(selectIdx).cltTaskPk_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskStsCd_GH, globalMsg.G.no(selectIdx).cltTaskStsCd_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskTpCd_GH, globalMsg.G.no(selectIdx).cltTaskTpCd_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskPrtyCd_GH, globalMsg.G.no(selectIdx).cltTaskPrtyCd_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskOwnrId_GH, globalMsg.G.no(selectIdx).cltTaskOwnrId_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltPsnNm_G1, globalMsg.G.no(selectIdx).cltPsnNm_G3);
        ZYPEZDItemValueSetter.setValue(bizMsg.cratUsrId_GH, globalMsg.G.no(selectIdx).cratUsrId_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_G2, globalMsg.G.no(selectIdx).xxPsnNm_G4);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskDescTxt_GH, globalMsg.G.no(selectIdx).cltTaskDescTxt_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskSubjTxt_GH, globalMsg.G.no(selectIdx).cltTaskSubjTxt_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskRwsdDt_GH, globalMsg.G.no(selectIdx).cltTaskRwsdDt_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskRwedDt_GH, globalMsg.G.no(selectIdx).cltTaskRwedDt_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskCtacNm_GH, globalMsg.G.no(selectIdx).cltTaskCtacNm_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskCtacPhoNum_GH, globalMsg.G.no(selectIdx).cltTaskCtacPhoNum_GD);
        // START 2018/07/27 [QC#25767, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.updUsrId_GH, globalMsg.G.no(selectIdx).updUsrId_GD);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_G5, globalMsg.G.no(selectIdx).xxPsnNm_G6);
        ZYPEZDItemValueSetter.setValue(bizMsg.cltTaskUpdDt_GH, globalMsg.G.no(selectIdx).cltTaskUpdDt_GD);
        // END   2018/07/27 [QC#25767, ADD]

        int xxPageShowFromNum = (selectIdx / bizMsg.G.length()) * bizMsg.G.length() + 1;
        bizMsg.xxPageShowFromNum_GH.setValue(xxPageShowFromNum);
        NFDL0020CommonLogic.dispPage(bizMsg, globalMsg);
    }

    private BigDecimal getCltStrgyTrxPkFromWrkItem(NFDL0020CMsg bizMsg) {
        CLT_STRGY_WRK_ITEM_TRXTMsg wrkItemTMsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
        ZYPEZDItemValueSetter.setValue(wrkItemTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(wrkItemTMsg.cltStrgyWrkItemTrxPk, bizMsg.cltStrgyWrkItemTrxPk_PM);
        wrkItemTMsg = (CLT_STRGY_WRK_ITEM_TRXTMsg) EZDTBLAccessor.findByKey(wrkItemTMsg);
        if (wrkItemTMsg != null) {
            BigDecimal cltStrgyTrxPk = wrkItemTMsg.cltStrgyTrxPk.getValue();
            
            for (int i = 0 ; i < bizMsg.C.getValidCount() ; i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.C.no(i).cltStrgyTrxPk_CD)
                        && bizMsg.C.no(i).cltStrgyTrxPk_CD.getValue().compareTo(cltStrgyTrxPk) == 0) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_CD, BigDecimal.valueOf(i));
                    break;
                }
            }
            return cltStrgyTrxPk;
        }
        return null;
    }
    // END   2018/07/23 [QC#25780, ADD]

    // START 2019/02/04 [QC#30161, ADD]
    private void doProcess_NFDL0020Scrn00_Click_TransactionCalc(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0020CMsg bizMsg = (NFDL0020CMsg) cMsg;
        NFDL0020SMsg globalMsg = (NFDL0020SMsg) sMsg;

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo("NFDM0024E");
            return;
        }
        NFDL0020CommonLogic.copyToSMsg(bizMsg, globalMsg);

        List<Integer> selectedRowsA = ZYPTableUtil.getSelectedRows(globalMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

        if (selectedRowsA.size() == 0) {
            bizMsg.setMessageInfo("NFDM0024E");
            return;
        }

        BigDecimal dealRmngBalGrsAmt = new BigDecimal(0);
        for (Integer idx : selectedRowsA) {
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(idx).dealRmngBalGrsAmt_A))
            dealRmngBalGrsAmt = dealRmngBalGrsAmt.add(globalMsg.A.no(idx).dealRmngBalGrsAmt_A.getValue());
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_AH, dealRmngBalGrsAmt);
    }
    // END   2019/02/04 [QC#30161, ADD]
}
