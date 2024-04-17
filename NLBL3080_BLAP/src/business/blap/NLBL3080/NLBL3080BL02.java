package business.blap.NLBL3080;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3080.common.NLBL3080CommonLogic;
import business.blap.NLBL3080.contant.NLBL3080Constant;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 2016/03/18   CITS            T.Tokutomi      Update          QC#4596
 * 03/30/2018   CITS            Keiichi Masaki  Update          QC#24997
 * 2018/07/23   CITS            K.Ogino         Update          QC#27047
 *</pre>
 */
public class NLBL3080BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3080_INIT".equals(screenAplID)) {
                doProcess_NLBL3080_INIT((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Search".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Search((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_CMN_Reset((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_CMN_Clear((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_CMN_Download((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_PageNext((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_PagePrev((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_PageJump((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Select_All".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Select_All((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_UnSelect_All".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_UnSelect_All((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Tab_Ord".equals(screenAplID)) {
                // ADD QC#24997 START
                doProcess_NLBL3080Scrn00_Search((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);
                // ADD QC#24997 END
                doProcess_NLBL3080Scrn00_Tab_Ord((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);
                getColData((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Tab_OrdLine".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Tab_OrdLine((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);
                getColData((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_ContractHolds".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_ContractHolds((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_ExpandHolds".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_ExpandHolds((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Collapse_All".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Collapse_All((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Expand_All".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Expand_All((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_OnChange_ChkBoxCpoNum".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_OnChange_ChkBoxCpoNum((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_OnChange_ChkBoxCpoSlipNum".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_OnChange_ChkBoxCpoSlipNum((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NLBL3080_OnChangeSavedSearchOption((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_CMN_Submit((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Alloc_Cancel".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Alloc_Cancel((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Search_RtlWHInfo".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Search_RtlWHInfo((NLBL3080CMsg) cMsg);

            } else if ("NLBL3080Scrn00_Search_ShipToCustInfo".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Search_ShipToCustInfo((NLBL3080CMsg) cMsg);

            } else if ("NLBL3080Scrn00_Search_MdseInfo".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Search_MdseInfo((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_Search_SlsRepInfo".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_Search_SlsRepInfo((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_TBLColumnSort((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            // No process
            } else if ("NLBL3080Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_SaveSearch((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_DeleteSearch((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NNLBL3080Scrn00_CMN_ColClear((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_CMN_ColSave((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else if ("NLBL3080_NLCL0270".equals(screenAplID)) {
                doProcess_NLBL3080Scrn00_NLBL3080_NLCL0270((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3080_INIT
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080_INIT(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        clearAll(cMsg, sMsg);

        // Setup select box
        NLBL3080CommonLogic.createSavedSearchOptionsPullDown(cMsg);

        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(DS_ORD_CATG.class, cMsg.dsOrdCatgCd_P, cMsg.dsOrdCatgDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(DS_ORD_TP.class, cMsg.dsOrdTpCd_P, cMsg.dsOrdTpDescTxt_P);

        // QC#27047
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_AL, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_BO, ZYPConstant.FLG_ON_Y);

        // Parameter Set
        boolean doSearch = false;

        // Sales Order Number
        if (ZYPCommonFunc.hasValue(cMsg.cpoOrdNum_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.cpoOrdNum, cMsg.cpoOrdNum_BK);
            doSearch = true;
        }
        // Retail Warehouse Code
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, cMsg.rtlWhCd_BK);
            doSearch = true;
        }
        // Ship To Customer Code
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, cMsg.shipToCustCd_BK);
            doSearch = true;
        }
        // Configuration ID
        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk, cMsg.svcConfigMstrPk_BK);
            doSearch = true;
        }
        // Model Name
        if (ZYPCommonFunc.hasValue(cMsg.t_MdlNm_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm, cMsg.t_MdlNm_BK);
            doSearch = true;
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxDplyTab_BK)) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, cMsg.xxDplyTab_BK);
            doSearch = true;

        } else if (doSearch) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NLBL3080Constant.TAB_ID_ORD);
        }

        if (doSearch) {
            search(cMsg, sMsg);

        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NLBL3080Constant.TAB_ID_ORD);
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Search
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_Search(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {
        // Start QC#27047
        if ("NLBL3080Scrn00_Tab_Ord".equals(cMsg.getScreenAplID())) {

            if (!ZYPCommonFunc.hasValue(cMsg.cpoOrdNum) //
                    && !ZYPCommonFunc.hasValue(cMsg.t_MdlNm) //
                    && !ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk) //
                    && !ZYPCommonFunc.hasValue(cMsg.rtlWhCd) //
                    && !ZYPCommonFunc.hasValue(cMsg.tocCd) //
                    && !ZYPCommonFunc.hasValue(cMsg.mdseCd) //
                    && !ZYPCommonFunc.hasValue(cMsg.shipToCustCd) //
                    && !ZYPCommonFunc.hasValue(cMsg.rddDt_FR) //
                    && !ZYPCommonFunc.hasValue(cMsg.rddDt_TO) //
                    && !ZYPCommonFunc.hasValue(cMsg.xxOrdDt_FR) //
                    && !ZYPCommonFunc.hasValue(cMsg.xxOrdDt_TO) //
                    && !ZYPCommonFunc.hasValue(cMsg.dsOrdCatgCd) //
                    && !ZYPCommonFunc.hasValue(cMsg.dsOrdTpCd) //
                    && !ZYPCommonFunc.hasValue(cMsg.serNum)) {

                return;

            }

            if (ZYPCommonFunc.hasValue(cMsg.rddDt_FR) && ZYPCommonFunc.hasValue(cMsg.rddDt_TO)) {

                if (0 < cMsg.rddDt_FR.getValue().compareTo(cMsg.rddDt_TO.getValue())) {

                    cMsg.rddDt_FR.setErrorInfo(1, "NLZM2277E", new String[] {"Request Date(Through)", "Request Date(From)" });
                    cMsg.rddDt_TO.setErrorInfo(1, "NLZM2277E", new String[] {"Request Date(Through)", "Request Date(From)" });
                    cMsg.setMessageInfo("ZZM9037E");
                    return;
                }
            }

            if (ZYPCommonFunc.hasValue(cMsg.xxOrdDt_FR) && ZYPCommonFunc.hasValue(cMsg.xxOrdDt_TO)) {

                if (0 < cMsg.xxOrdDt_FR.getValue().compareTo(cMsg.xxOrdDt_TO.getValue())) {

                    cMsg.xxOrdDt_FR.setErrorInfo(1, "NLZM2277E", new String[] {"Order Date(Through)", "Order Date(From)" });
                    cMsg.xxOrdDt_TO.setErrorInfo(1, "NLZM2277E", new String[] {"Order Date(Through)", "Order Date(From)" });
                    cMsg.setMessageInfo("ZZM9037E");
                    return;
                }
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NLBL3080Constant.TAB_ID_ORD);

        }
        // End QC#27047

        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);

        // Master Check
        if (checkInputHeader(cMsg)) {

            // Search
            search(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_CMN_Clear
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_CMN_Clear(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        String col = cMsg.xxComnColOrdTxt.getValue();
        clearAll(cMsg, sMsg);

        // create Pull-Down
        ZYPCodeDataUtil.createPulldownList(DS_ORD_CATG.class, cMsg.dsOrdCatgCd_P, cMsg.dsOrdCatgDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(DS_ORD_TP.class, cMsg.dsOrdTpCd_P, cMsg.dsOrdTpDescTxt_P);

        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, col);
    }

    /**
     * doProcess_NLBL3080Scrn00_PageNext
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080CMsg
     */
    private void doProcess_NLBL3080Scrn00_PageNext(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrd(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowToNum_A.getValueInt());

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrdLine(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.B);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowToNum_B.getValueInt());
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_PagePrev
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080CMsg
     */
    private void doProcess_NLBL3080Scrn00_PagePrev(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrd(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1);

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrdLine(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.B);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum_B.getValueInt() - cMsg.B.length() - 1);
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_PageJump
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080CMsg
     */
    private void doProcess_NLBL3080Scrn00_PageJump(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrd(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.A);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1));

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrdLine(cMsg, sMsg);
            ZYPTableUtil.clear(cMsg.B);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.B.length() * (cMsg.xxPageShowCurNum_B.getValueInt() - 1));
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Select_All
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080CMsg
     */
    private void doProcess_NLBL3080Scrn00_Select_All(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        String preCpoNum = "";

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                if (!preCpoNum.equals(cMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
                }

                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);
                preCpoNum = cMsg.A.no(i).cpoOrdNum_A1.getValue();
            }

            preCpoNum = "";

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                if (!preCpoNum.equals(sMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);
                preCpoNum = sMsg.A.no(i).cpoOrdNum_A1.getValue();
            }

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            String preDsOrdPosnNum = "";

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                if (!preCpoNum.equals(cMsg.B.no(i).cpoOrdNum_B1.getValue()) || !preDsOrdPosnNum.equals(cMsg.B.no(i).dsOrdPosnNum_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B1, ZYPConstant.CHKBOX_ON_Y);
                }

                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B2, ZYPConstant.CHKBOX_ON_Y);
                preCpoNum = cMsg.B.no(i).cpoOrdNum_B1.getValue();
                preDsOrdPosnNum = cMsg.B.no(i).dsOrdPosnNum_B1.getValue();
            }

            preCpoNum = "";
            preDsOrdPosnNum = "";

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                if (!preCpoNum.equals(sMsg.B.no(i).cpoOrdNum_B1.getValue()) || !preDsOrdPosnNum.equals(sMsg.B.no(i).dsOrdPosnNum_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B1, ZYPConstant.CHKBOX_ON_Y);
                }

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B2, ZYPConstant.CHKBOX_ON_Y);
                preCpoNum = sMsg.B.no(i).cpoOrdNum_B1.getValue();
                preDsOrdPosnNum = sMsg.B.no(i).dsOrdPosnNum_B1.getValue();
            }
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_UnSelect_All
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080CMsg
     */
    private void doProcess_NLBL3080Scrn00_UnSelect_All(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                cMsg.A.no(i).xxChkBox_A1.clear();
                cMsg.A.no(i).xxChkBox_A2.clear();
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                sMsg.A.no(i).xxChkBox_A1.clear();
                sMsg.A.no(i).xxChkBox_A2.clear();
            }

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                cMsg.B.no(i).xxChkBox_B1.clear();
                cMsg.B.no(i).xxChkBox_B2.clear();
            }

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                sMsg.B.no(i).xxChkBox_B1.clear();
                sMsg.B.no(i).xxChkBox_B2.clear();
            }
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_CMN_Reset
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080CMsg
     */
    private void doProcess_NLBL3080Scrn00_CMN_Reset(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        doProcess_NLBL3080_INIT(cMsg, sMsg);
        getColData((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg);
    }

    /**
     * doProcess_NLBL3080Scrn00_CMN_Download
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_CMN_Download(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NLBL3080Constant.MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLBL3080Query.getInstance().getClass());

            // create csv file, parameters
            Map<String, Object> ssMParam = null;
            String ssmId = "";
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLBL3080Constant.CSV_FILE_NAME_ORD_LINE), ".csv");
            ssMParam = NLBL3080Query.getSsmParamDownLoadOrderLine(cMsg, NLBL3080Constant.LIMIT_DL_ROWNUM + 1);
            ssmId = "SearchOrdLine";

            ps = ssmLLClient.createPreparedStatement(ssmId, ssMParam, execParam);
            rs = ps.executeQuery();
            NLBL3080CommonLogic.writeCsvFileOrderLine(cMsg, rs);

        } catch (SQLException e) {

            throw new S21AbendException(e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Tab_Ord
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_Tab_Ord(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (!NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            if (sMsg.B.getValidCount() == 0) {

                ZYPTableUtil.clear(cMsg.A);
                ZYPTableUtil.clear(sMsg.A);
                cMsg.xxPageShowFromNum_A.clear();
                cMsg.xxPageShowToNum_A.clear();
                cMsg.xxPageShowOfNum_A.clear();

            } else {

                NLBL3080CommonLogic.saveCurrentPageToSMsgOrdLine(cMsg, sMsg);
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NLBL3080Constant.TAB_ID_ORD);
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Tab_OrdLine
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_Tab_OrdLine(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (!NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            if (sMsg.A.getValidCount() == 0) {

                ZYPTableUtil.clear(cMsg.B);
                ZYPTableUtil.clear(sMsg.B);
                cMsg.xxPageShowFromNum_B.clear();
                cMsg.xxPageShowToNum_B.clear();
                cMsg.xxPageShowOfNum_B.clear();

            } else {

                NLBL3080CommonLogic.saveCurrentPageToSMsgOrd(cMsg, sMsg);
                // MOD QC#24997 START
                search_OrdLine(cMsg, sMsg, NLBL3080Constant.SEARCH_TP_COND);

//                boolean chkOnCount = false;
//
//                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//
//                    if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue()) || ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A2.getValue())) {
//
//                        chkOnCount = true;
//                        break;
//                    }
//                }
//
//                if (!chkOnCount) {
//
//                    // Error
//                    doProcess_NLBL3080Scrn00_Expand_All(cMsg, sMsg);
//
//                    for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//
//                        cMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, "NLBM0002E");
//                        cMsg.A.no(i).xxChkBox_A2.setErrorInfo(1, "NLBM0002E");
//                    }
//
//                    return;
//
//                } else {
//
//                    search_OrdLine(cMsg, sMsg, NLBL3080Constant.SEARCH_TP_TAB);
//                }
                // MOD QC#24997 END
            }

            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, NLBL3080Constant.TAB_ID_ORD_LINE);
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_ContractHolds ([-] --> [+])
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_ContractHolds(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrd(cMsg, sMsg);
            int sMsgIdxA = cMsg.A.no(eventLine).xxNum_A1.getValueInt();
            String preCpoOrdNum = cMsg.A.no(eventLine).cpoOrdNum_A1.getValue();

            for (int i = sMsgIdxA; i < sMsg.A.getValidCount(); i++) {

                if (sMsgIdxA == i) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A2, ZYPConstant.FLG_ON_Y);

                } else if (preCpoOrdNum.equals(sMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A2, ZYPConstant.FLG_OFF_N);

                } else {

                    break;
                }
            }

            ZYPTableUtil.clear(cMsg.A);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1));

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrdLine(cMsg, sMsg);
            int sMsgIdxB = cMsg.B.no(eventLine).xxNum_B1.getValueInt();
            String preCpoOrdNum = cMsg.B.no(eventLine).cpoOrdNum_B1.getValue();
            String preDsOrdPosnNum = cMsg.B.no(eventLine).dsOrdPosnNum_B1.getValue();

            for (int i = sMsgIdxB; i < sMsg.B.getValidCount(); i++) {

                if (sMsgIdxB == i) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B2, ZYPConstant.FLG_ON_Y);

                } else if (preCpoOrdNum.equals(sMsg.B.no(i).cpoOrdNum_B1.getValue()) && preDsOrdPosnNum.equals(sMsg.B.no(i).dsOrdPosnNum_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B2, ZYPConstant.FLG_OFF_N);

                } else {

                    break;
                }
            }

            ZYPTableUtil.clear(cMsg.B);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.B.length() * (cMsg.xxPageShowCurNum_B.getValueInt() - 1));
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_ExpandHolds ([+] --> [-])
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_ExpandHolds(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrd(cMsg, sMsg);
            int sMsgIdxA = cMsg.A.no(eventLine).xxNum_A1.getValueInt();
            String preCpoNum = cMsg.A.no(eventLine).cpoOrdNum_A1.getValue();

            for (int i = sMsgIdxA; i < sMsg.A.getValidCount(); i++) {

               if (sMsgIdxA == i) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A2, ZYPConstant.FLG_ON_Y);


                } else if (preCpoNum.equals(sMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A1, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg_A2, ZYPConstant.FLG_ON_Y);

                } else {

                    break;
                }
            }

            ZYPTableUtil.clear(cMsg.A);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.A.length() * (cMsg.xxPageShowCurNum_A.getValueInt() - 1));

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrdLine(cMsg, sMsg);
            int sMsgIdxB = cMsg.B.no(eventLine).xxNum_B1.getValueInt();
            String preCpoOrdNum = cMsg.B.no(eventLine).cpoOrdNum_B1.getValue();
            String preDsOrdPosnNum = cMsg.B.no(eventLine).dsOrdPosnNum_B1.getValue();

            for (int i = sMsgIdxB; i < sMsg.B.getValidCount(); i++) {

                if (sMsgIdxB == i) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B2, ZYPConstant.FLG_ON_Y);

                } else if (preCpoOrdNum.equals(sMsg.B.no(i).cpoOrdNum_B1.getValue()) && preDsOrdPosnNum.equals(sMsg.B.no(i).dsOrdPosnNum_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B2, ZYPConstant.FLG_ON_Y);

                } else {

                    break;
                }
            }

            ZYPTableUtil.clear(cMsg.B);
            NLBL3080CommonLogic.pagenation(cMsg, sMsg, cMsg.B.length() * (cMsg.xxPageShowCurNum_B.getValueInt() - 1));
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Collapse_All
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_Collapse_All(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                if (ZYPConstant.FLG_OFF_N.equals(cMsg.A.no(i).xxSmryLineFlg_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.xxNum_EV, new BigDecimal(i));
                    doProcess_NLBL3080Scrn00_ContractHolds(cMsg, sMsg);
                }
            }

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                if (ZYPConstant.FLG_OFF_N.equals(cMsg.B.no(i).xxSmryLineFlg_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.xxNum_EV, new BigDecimal(i));
                    doProcess_NLBL3080Scrn00_ContractHolds(cMsg, sMsg);
                }
            }
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Expand_All
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_Expand_All(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.A.getValidCount(); i++) {

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxSmryLineFlg_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.xxNum_EV, new BigDecimal(i));
                    doProcess_NLBL3080Scrn00_ExpandHolds(cMsg, sMsg);
                }
            }

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.B.no(i).xxSmryLineFlg_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.xxNum_EV, new BigDecimal(i));
                    doProcess_NLBL3080Scrn00_ExpandHolds(cMsg, sMsg);
                }
            }
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_OnChange_ChkBoxCpoNum
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_OnChange_ChkBoxCpoNum(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();
        String val = "";
        String cpoOrdNum = "";
        String dsOrdPosnNum = "";
        int sIndex = 0;

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = eventLine; i < cMsg.A.getValidCount(); i++) {

                if (eventLine == i) {

                    val = cMsg.A.no(i).xxChkBox_A1.getValue();
                    cpoOrdNum = cMsg.A.no(i).cpoOrdNum_A1.getValue();
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A2, val);
                    sIndex = cMsg.A.no(i).xxNum_A1.getValueInt();
                    continue;
                }

                if (cpoOrdNum.equals(cMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A2, val);

                } else {

                    break;
                }
            }

            for (int i = sIndex; i < sMsg.A.getValidCount(); i++) {

                if (sIndex == i) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A2, val);
                    continue;
                }

                if (cpoOrdNum.equals(sMsg.A.no(i).cpoOrdNum_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_A2, val);

                } else {

                    break;
                }
            }

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            for (int i = eventLine; i < cMsg.B.getValidCount(); i++) {

                if (eventLine == i) {

                    val = cMsg.B.no(i).xxChkBox_B1.getValue();
                    cpoOrdNum = cMsg.B.no(i).cpoOrdNum_B1.getValue();
                    dsOrdPosnNum= cMsg.B.no(i).dsOrdPosnNum_B1.getValue();
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B2, val);
                    sIndex = cMsg.B.no(i).xxNum_B1.getValueInt();
                    continue;
                }

                if (cpoOrdNum.equals(cMsg.B.no(i).cpoOrdNum_B1.getValue()) && dsOrdPosnNum.equals(cMsg.B.no(i).dsOrdPosnNum_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B2, val);

                } else {

                    break;
                }
            }

            for (int i = sIndex; i < sMsg.B.getValidCount(); i++) {

                if (sIndex == i) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B2, val);
                    continue;
                }

                if (cpoOrdNum.equals(sMsg.B.no(i).cpoOrdNum_B1.getValue()) && dsOrdPosnNum.equals(sMsg.B.no(i).dsOrdPosnNum_B1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_B2, val);

                } else {

                    break;
                }
            }
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_OnChange_ChkBoxCpoSlipNum
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_OnChange_ChkBoxCpoSlipNum(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();
        String val = null;
        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {
            val = cMsg.A.no(eventLine).xxChkBox_A2.getValue();
        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {
            val = cMsg.B.no(eventLine).xxChkBox_B2.getValue();
        }

        // ON --> OFF
        if (!ZYPConstant.CHKBOX_ON_Y.equals(val)) {
            checkOff(cMsg, sMsg);
        }

        // OFF --> ON
        if (ZYPConstant.CHKBOX_ON_Y.equals(val)) {
            checkOn(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3080_OnChangeSavedSearchOption
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080_OnChangeSavedSearchOption(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            NLBL3080CommonLogic.callNszc0330forSearchOption(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_CMN_Submit
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_CMN_Submit(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {

            EZDMessageInfo msgInfo = cMsg.getMessageInfo();
            search_OrdLine(cMsg, sMsg, NLBL3080Constant.SEARCH_TP_RESEARCH);

            if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind()) && msgInfo != null) {

                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Alloc_Cancel
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_Alloc_Cancel(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {
        if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {
            EZDMessageInfo msgInfo = cMsg.getMessageInfo();
            search_OrdLine(cMsg, sMsg, NLBL3080Constant.SEARCH_TP_RESEARCH);
            if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind()) && msgInfo != null) {
                cMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            }
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Search_RtlWHInfo
     * @param cMsg NLBL3080CMsg
     */
    private void doProcess_NLBL3080Scrn00_Search_RtlWHInfo(NLBL3080CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {

            NLBL3080CommonLogic.getRtlWHInfo(cMsg);

        } else {

            cMsg.rtlWhCd.setErrorInfo(1, "ZZM9000E", new String[] {"Warehouse" });
            cMsg.rtlWhNm.clear();
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Search_ShipToCustInfo
     * @param cMsg NLBL3080CMsg
     */
    private void doProcess_NLBL3080Scrn00_Search_ShipToCustInfo(NLBL3080CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {

            NLBL3080CommonLogic.getShipToCustNm(cMsg);

        } else {

            cMsg.shipToCustCd.setErrorInfo(1, "ZZM9000E", new String[] {"Ship To Customer" });
            cMsg.dsAcctNm.clear();
        }
    }

    /**
     * doProcess_SearchMdseInfo
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NLBL3080Scrn00_Search_MdseInfo(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.mdseCd)) {

            NLBL3080CommonLogic.getMdseName(cMsg);

        } else {

            cMsg.mdseCd.setErrorInfo(1, "ZZM9000E", new String[] {"Item Number" });
            cMsg.mdseDescShortTxt.clear();
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_Search_SlsRepInfo
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_Search_SlsRepInfo(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.tocCd)) {

            NLBL3080CommonLogic.getSlsRepInfo(cMsg);

        } else {

            cMsg.tocCd.setErrorInfo(1, "ZZM9000E", new String[] {"Sales Rep" });
            cMsg.tocNm.clear();
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_SaveSearch
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_SaveSearch(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NLBL3080Scrn00_DeleteSearch
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_DeleteSearch(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NNLBL3080Scrn00_CMN_ColClear
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NNLBL3080Scrn00_CMN_ColClear(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NLBL3080Scrn00_CMN_ColSave
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_CMN_ColSave(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {
        // There is no process.
        return;
    }

    /**
     * doProcess_NLBL3080Scrn00_TBLColumnSort
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_TBLColumnSort(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrd(cMsg, sMsg);

            String sortTblNm = cMsg.xxSortTblNm_A.getValue();
            String sortItemNm = cMsg.xxSortItemNm_A.getValue();
            String sortOrdBy = cMsg.xxSortOrdByTxt_A.getValue();

            if ("A".equals(sortTblNm)) {

                // execute column sort function
                NLBL3080CommonLogic.sort(sMsg.A, sortItemNm, sortOrdBy, sMsg.A.no(0).getBaseContents(), false);

                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    NLBL3080_ASMsg sMsgALine = sMsg.A.no(i);
                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxNum_A1, new BigDecimal(i));
                }

                NLBL3080CommonLogic.pagenation(cMsg, sMsg, 0);
            }

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            NLBL3080CommonLogic.saveCurrentPageToSMsgOrdLine(cMsg, sMsg);

            String sortTblNm = cMsg.xxSortTblNm_B.getValue();
            String sortItemNm = cMsg.xxSortItemNm_B.getValue();
            String sortOrdBy = cMsg.xxSortOrdByTxt_B.getValue();

            if ("B".equals(sortTblNm)) {

                // execute column sort function
                NLBL3080CommonLogic.sort(sMsg.B, sortItemNm, sortOrdBy, sMsg.B.no(0).getBaseContents(), false);

                for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                    NLBL3080_BSMsg sMsgBLine = sMsg.B.no(i);
                    ZYPEZDItemValueSetter.setValue(sMsgBLine.xxNum_B1, new BigDecimal(i));
                }

                NLBL3080CommonLogic.pagenation(cMsg, sMsg, 0);
            }
        }
    }

    /**
     * doProcess_NLBL3080Scrn00_NLBL3080_NLCL0270
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void doProcess_NLBL3080Scrn00_NLBL3080_NLCL0270(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        // common data
        int index = cMsg.xxCellIdx.getValueInt();
        String mdseCd = cMsg.B.no(index).mdseCd_B1.getValue();

        // Get Current Available Quantity

        S21SsmEZDResult ssmResult = NLBL3080Query.getInstance().getInvtyAvalQty(cMsg.glblCmpyCd.getValue(), mdseCd, cMsg.B.no(index).invtyLocCd_B1.getValue(), cMsg.B.no(index).stkStsCd_B1.getValue());

        if (ssmResult.isCodeNormal()) {

            BigDecimal invtyAvalQty = (BigDecimal) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(index).invtyAvalQty_B1, invtyAvalQty);
        }

        // set Mdse Name
        S21SsmEZDResult resultMdseDescTxt = NLBL3080Query.getInstance().getMdseDescShortTxt(cMsg.glblCmpyCd.getValue(), mdseCd);

        if (resultMdseDescTxt.isCodeNormal()) {

            String mdseDescTxt = (String) resultMdseDescTxt.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(index).mdseDescShortTxt_B1, mdseDescTxt);
        }
    }

    /**
     * clearAll
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void clearAll(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        sMsg.clear();

        cMsg.srchOptPk_S.clear();
        cMsg.srchOptNm_S.clear();
        cMsg.cpoOrdNum.clear();
        cMsg.dsOrdCatgCd.clear();
        cMsg.dsOrdCatgCd_P.clear();
        cMsg.dsOrdCatgDescTxt_P.clear();
        cMsg.dsOrdTpCd.clear();
        cMsg.dsOrdTpCd_P.clear();
        cMsg.dsOrdTpDescTxt_P.clear();
        cMsg.serNum.clear();
        cMsg.xxLinkAncr_ML.clear();
        cMsg.t_MdlNm.clear();
        cMsg.svcConfigMstrPk.clear();
        cMsg.rtlWhCd.clear();
        cMsg.rtlWhNm.clear();
        cMsg.shipToCustCd.clear();
        cMsg.dsAcctNm.clear();
        cMsg.tocCd.clear();
        cMsg.tocNm.clear();
        cMsg.mdseCd.clear();
        cMsg.mdseDescShortTxt.clear();
        cMsg.rddDt_FR.clear();
        cMsg.rddDt_TO.clear();
        cMsg.xxOrdDt_FR.clear();
        cMsg.xxOrdDt_TO.clear();
        cMsg.xxChkBox_BO.clear();
        // QC#27047
        cMsg.xxChkBox_AL.clear();
        cMsg.xxChkBox_NS.clear();

        // Order Tab
        cMsg.xxPageShowFromNum_A.clear();
        cMsg.xxPageShowToNum_A.clear();
        cMsg.xxPageShowOfNum_A.clear();
        cMsg.xxPageShowCurNum_A.clear();
        cMsg.xxPageShowTotNum_A.clear();
        cMsg.xxSortTblNm_A.clear();
        cMsg.xxSortItemNm_A.clear();
        cMsg.xxSortOrdByTxt_A.clear();

        // OrderLine Tab
        cMsg.xxPageShowFromNum_B.clear();
        cMsg.xxPageShowToNum_B.clear();
        cMsg.xxPageShowOfNum_B.clear();
        cMsg.xxPageShowCurNum_B.clear();
        cMsg.xxPageShowTotNum_B.clear();
        cMsg.xxSortTblNm_B.clear();
        cMsg.xxSortItemNm_B.clear();
        cMsg.xxSortOrdByTxt_B.clear();

        // Set Common Value
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.usrId, getContextUserInfo().getUserId());

        String dupErrCd = ZYPCodeDataUtil.getVarCharConstValue("SER_DUP_CHK_CD", cMsg.glblCmpyCd.getValue());

        if (!ZYPCommonFunc.hasValue(dupErrCd)) {

            dupErrCd = NLBL3080Constant.DUP_CHK_SERIAL;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.dupErrCd, dupErrCd);
        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * search
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private void search(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {

            search_Ord(cMsg, sMsg, NLBL3080Constant.SEARCH_TP_COND);

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            search_OrdLine(cMsg, sMsg, NLBL3080Constant.SEARCH_TP_COND);
        }
    }

    /**
     * search_Ord
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @param searchTp String
     */
    private void search_Ord(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg, String searchTp) {

        S21SsmEZDResult result = null;

        if (NLBL3080Constant.SEARCH_TP_COND.equals(searchTp)) {

            result = NLBL3080Query.getInstance().search_Ord(cMsg, sMsg);
        }

        if (result.isCodeNormal()) {

            NLBL3080CommonLogic.setSearchOrdResultToSMsg(result, sMsg);
            int queryResCnt = result.getQueryResultCount();

            if (queryResCnt > sMsg.A.length()) {

                cMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length();
            }

            int i = 0;

            for (; i < queryResCnt; i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            // Set page number
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(queryResCnt);
            cMsg.setMessageInfo("ZZZM9003I", new String[] {"Search" });

        } else {
            ZYPTableUtil.clear(cMsg.A);
            cMsg.A.setValidCount(0);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
            cMsg.setMessageInfo("NZZM0000E");
        }
    }

    /**
     * search_OrdLine
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     * @param searchTp String
     */
    private void search_OrdLine(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg, String searchTp) {

        S21SsmEZDResult result = null;

        if (NLBL3080Constant.SEARCH_TP_COND.equals(searchTp)) {

            result = NLBL3080Query.getInstance().search_OrdLine(cMsg, sMsg);

        } else if (NLBL3080Constant.SEARCH_TP_RESEARCH.equals(searchTp)) {

            result = NLBL3080Query.getInstance().search_OrdLineResearch(cMsg, sMsg);

        // DEL QC#24997 START
//        } else if (NLBL3080Constant.SEARCH_TP_TAB.equals(searchTp)) {
//
//            result = NLBL3080Query.getInstance().search_OrdLineTab(cMsg, sMsg);
        // DEL QC#24997 END
        }

        if (result.isCodeNormal()) {

            NLBL3080CommonLogic.setSearchOrdLineResultToSMsg(result, sMsg, cMsg.glblCmpyCd.getValue());
            int queryResCnt = result.getQueryResultCount();

            if (queryResCnt > sMsg.B.length()) {

                cMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.B.length();
            }

            int i = 0;

            for (; i < queryResCnt; i++) {

                if (i == cMsg.B.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }

            cMsg.B.setValidCount(i);

            // Set page number
            cMsg.xxPageShowFromNum_B.setValue(1);
            cMsg.xxPageShowToNum_B.setValue(cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_B.setValue(queryResCnt);
            cMsg.setMessageInfo("ZZZM9003I", new String[] {"Search" });

        } else {

            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            ZYPTableUtil.clear(cMsg.B);
            ZYPTableUtil.clear(sMsg.B);
            cMsg.xxPageShowFromNum_B.clear();
            cMsg.xxPageShowToNum_B.clear();
            cMsg.xxPageShowOfNum_B.clear();
            //QC:15350
            if(!NLBL3080Constant.EVENT_NM_CMN_SUBMIT.equals(cMsg.getScreenAplID()) && !NLBL3080Constant.EVENT_NM_ALLOC_CANCEL.equals(cMsg.getScreenAplID())){
                cMsg.setMessageInfo("NZZM0000E");
            }
        }
    }

    /**
     * checkInputHeader
     * @param cMsg NLBL3080CMsg
     * @return boolean
     */
    private boolean checkInputHeader(NLBL3080CMsg cMsg) {

        boolean chkRslt = true;

        // Source WH
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {

            chkRslt = NLBL3080CommonLogic.getRtlWHInfo(cMsg);

        } else {

            cMsg.rtlWhNm.clear();
        }

        // Ship To Customer
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {

            chkRslt = NLBL3080CommonLogic.getShipToCustNm(cMsg);

        } else {

            cMsg.dsAcctNm.clear();
        }

        // Sales Rep
        if (ZYPCommonFunc.hasValue(cMsg.tocCd)) {

            chkRslt = NLBL3080CommonLogic.getSlsRepInfo(cMsg);

        } else {

            cMsg.tocNm.clear();
        }

        // Item Number
        if (ZYPCommonFunc.hasValue(cMsg.mdseCd)) {

            chkRslt = NLBL3080CommonLogic.getMdseName(cMsg);

        } else {

            cMsg.mdseDescShortTxt.clear();
        }

        //Model
        if (ZYPCommonFunc.hasValue(cMsg.t_MdlNm)) {

            MDL_NMTMsg mdlNmTMsg = new MDL_NMTMsg();
            mdlNmTMsg.setSQLID("001");
            mdlNmTMsg.setConditionValue("t_GlblCmpyCd01", cMsg.glblCmpyCd.getValue());
            mdlNmTMsg.setConditionValue("t_MdlNm01", cMsg.t_MdlNm.getValue());
            MDL_NMTMsgArray mdlNmList = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlNmTMsg);

            if (mdlNmList == null || mdlNmList.getValidCount() == 0) {

                cMsg.t_MdlNm.setErrorInfo(1, "NLZM2278E", new String[] {"Model" });
                chkRslt = false;
            }
        }

        return chkRslt;
    }

    /**
     * getColData
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private static void getColData(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {

            ZYPGUITableColumn.getColData((NLBL3080CMsg) cMsg, (NLBL3080SMsg) sMsg, "BHEAD");
        }
    }

    /**
     * checkOn
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private static void checkOn(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {

        int eventLine = cMsg.xxNum_EV.getValueInt();
        int offCount = 0;
        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {
            int sLineNum = cMsg.A.no(eventLine).xxNum_A1.getValueInt();
            String preCpoOrdNum = sMsg.A.no(sLineNum).cpoOrdNum_A1.getValue();
            // Back Line
            for (int i = sLineNum; i >= 0; i--) {
                NLBL3080_ASMsg sMsgALine = sMsg.A.no(i);
                if (i == sLineNum) {
                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);
                    continue;
                }
                if (preCpoOrdNum.equals(sMsgALine.cpoOrdNum_A1.getValue())) {
                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {
                        // Off
                        offCount++;
                    }
                } else {
                    break;
                }
            }
            // Ahead Line
            for (int i = sLineNum; i < cMsg.A.getValidCount(); i++) {
                NLBL3080_ASMsg sMsgALine = sMsg.A.no(i);
                if (i == sLineNum) {
                    ZYPEZDItemValueSetter.setValue(sMsgALine.xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);
                    continue;
                }
                if (preCpoOrdNum.equals(sMsgALine.cpoOrdNum_A1.getValue())) {
                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsgALine.xxChkBox_A2.getValue())) {
                        // Off
                        offCount++;
                    }
                } else {
                    break;
                }
            }
            // cMsg
            if (offCount == 0) {
                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    NLBL3080_ACMsg cMsgALine = cMsg.A.no(i);
                    if (preCpoOrdNum.equals(cMsgALine.cpoOrdNum_A1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(cMsgALine.xxSmryLineFlg_A2.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsgALine.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
                        break;
                    }
                }
            }
        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {
            int sLineNum = cMsg.B.no(eventLine).xxNum_B1.getValueInt();
            String preCpoOrdNum = sMsg.B.no(sLineNum).cpoOrdNum_B1.getValue();
            String preLineNum = sMsg.B.no(sLineNum).dsOrdPosnNum_B1.getValue();
            // Back Line
            for (int i = sLineNum; i >= 0; i--) {
                NLBL3080_BSMsg sMsgBLine = sMsg.B.no(i);
                if (i == sLineNum) {
                    ZYPEZDItemValueSetter.setValue(sMsgBLine.xxChkBox_B2, ZYPConstant.CHKBOX_ON_Y);
                    continue;
                }
                if (preCpoOrdNum.equals(sMsgBLine.cpoOrdNum_B1.getValue()) && preLineNum.equals(sMsgBLine.dsOrdPosnNum_B1.getValue())) {
                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLine.xxChkBox_B2.getValue())) {
                        // Off
                        offCount++;
                    }
                } else {
                    break;
                }
            }
            // Ahead Line
            for (int i = sLineNum; i < cMsg.B.getValidCount(); i++) {
                NLBL3080_BSMsg sMsgBLine = sMsg.B.no(i);
                if (i == sLineNum) {
                    ZYPEZDItemValueSetter.setValue(sMsgBLine.xxChkBox_B2, ZYPConstant.CHKBOX_ON_Y);
                    continue;
                }
                if (preCpoOrdNum.equals(sMsgBLine.cpoOrdNum_B1.getValue()) && preLineNum.equals(sMsgBLine.dsOrdPosnNum_B1.getValue())) {
                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsgBLine.xxChkBox_B2.getValue())) {
                        // Off
                        offCount++;
                    }
                } else {
                    break;
                }
            }
            // cMsg
            if (offCount == 0) {
                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                    NLBL3080_BCMsg cMsgBLine = cMsg.B.no(i);
                    if (preCpoOrdNum.equals(cMsgBLine.cpoOrdNum_B1.getValue()) && preLineNum.equals(cMsgBLine.dsOrdPosnNum_B1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(cMsgBLine.xxSmryLineFlg_B2.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsgBLine.xxChkBox_B1, ZYPConstant.CHKBOX_ON_Y);
                        break;
                    }
                }
            }
        }
    }

    /**
     * checkOff
     * @param cMsg NLBL3080CMsg
     * @param sMsg NLBL3080SMsg
     */
    private static void checkOff(NLBL3080CMsg cMsg, NLBL3080SMsg sMsg) {
        int eventLine = cMsg.xxNum_EV.getValueInt();

        // ON --> OFF
        // sMsg
        if (NLBL3080Constant.TAB_ID_ORD.equals(cMsg.xxDplyTab.getValue())) {
            int sLineNum = cMsg.A.no(eventLine).xxNum_A1.getValueInt();
            // update SMsg
            sMsg.A.no(sLineNum).xxChkBox_A2.clear();
            // update CMsg (Summary Line)
            for (int i = eventLine; i >= 0; i--) {
                NLBL3080_ACMsg cMsgALine = cMsg.A.no(i);
                if (sMsg.A.no(sLineNum).cpoOrdNum_A1.getValue().equals(cMsgALine.cpoOrdNum_A1.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsgALine.xxSmryLineFlg_A2.getValue())) {
                    cMsgALine.xxChkBox_A1.clear();
                    break;
                }
            }

        } else if (NLBL3080Constant.TAB_ID_ORD_LINE.equals(cMsg.xxDplyTab.getValue())) {
            int sLineNum = cMsg.B.no(eventLine).xxNum_B1.getValueInt();
            // update SMsg
            sMsg.B.no(sLineNum).xxChkBox_B2.clear();
            // update CMsg (Summary Line)
            for (int i = eventLine; i >= 0; i--) {
                NLBL3080_BCMsg cMsgBLine = cMsg.B.no(i);
                if (sMsg.B.no(sLineNum).cpoOrdNum_B1.getValue().equals(cMsgBLine.cpoOrdNum_B1.getValue()) && sMsg.B.no(sLineNum).dsOrdPosnNum_B1.getValue().equals(cMsgBLine.dsOrdPosnNum_B1.getValue())
                        && ZYPConstant.FLG_ON_Y.equals(cMsgBLine.xxSmryLineFlg_B2.getValue())) {
                    cMsgBLine.xxChkBox_B1.clear();
                    break;
                }
            }
        }
        return;
    }
}
