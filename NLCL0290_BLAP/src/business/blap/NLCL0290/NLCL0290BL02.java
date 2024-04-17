/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 * 2017/02/09   CITS            M.Naito         Update          QC#12673
 * 2018/06/05   CITS            K.Ogino         Update          QC#26471
 * 06/05/2018   CITS            K.Ogino         Update          QC#26383
 *</pre>
 */
package business.blap.NLCL0290;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0290.common.NLCL0290CommonLogic;
import business.blap.NLCL0290.constant.NLCL0290Constant;
import business.db.ADJ_CATGTMsg;
import business.db.ADJ_TRX_TPTMsg;
import business.db.COA_ACCTTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.file.NLCL0290F00FMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 * 2016/07/03   CSAI            K.Lee           Update          QC#11000
 * 2016/07/03   CSAI            K.Lee           Update          QC#11240
 * 2016/11/09   CITS            K.Kameoka       Update          QC#15558
 * 2016/12/22   CITS            Y.Fujii         Update          QC#16674
 * 2017/01/10   CITS            Y.Fujii         Update          QC#16674-2
 * 2017/02/10   CITS            Y.Fujii         Update          QC#16674-3
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472/18490
 * 2018/04/20   CITS            T.Tokutomi      Update          QC#18380
 * 2018/05/02   CITS            K.Kameoka       Update          QC#25840
 * 2018/05/08   CITS            T.Tokutomi      Update          QC#25925
 * 2018/11/30   CITS            T.Hakodate      Update          QC#29172
 * 2019/01/08   Fujitsu         S.Takami        Update          QC#29763
 * 2019/06/03   CITS            M.Naito         Update          QC#50408
 * 2022/10/21   Hitachi         N.Takatsu       Update          QC#60603
*</pre>
 */
public class NLCL0290BL02 extends S21BusinessHandler implements NLCL0290Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            String glblCmpyCd = getGlobalCompanyCode();

            // START 2022/10/21 N.Takatsu[QC#60603, ADD]
            NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
            // END 2022/10/21 N.Takatsu[QC#60603, ADD]
            cMsg.setCommitSMsg(true);

            if ("NLCL0290_INIT".equals(screenAplID)) {
                doProcess_NLCL0290_INIT(cMsg, sMsg);

            } else if ("NLCL0290Scrn00_New".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_New((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_Search".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_Search((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_CMN_Clear((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NLCL0290Scrn00_Delete_Line".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_Delete_Line((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_Add_Line".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_Add_Line((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_PageNext((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_PagePrev((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_CMN_Download((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_Import".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_Import((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_Search_Item".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_Search_Item((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290Scrn00_OnChange_AdjAcctAlias".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_OnChange_AdjAcctAlias((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            } else if ("NLCL0290_NMAL2550".equals(screenAplID)) {
                doProcess_NLCL0290_NMAL2550((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg);

            // QC#11785 add start
            // QC:18472-18490
            } else if ("NLCL0290_NSAL1240".equals(screenAplID)) {
                doProcess_NLCL0290_NSAL1240((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg, glblCmpyCd);

            } else if ("NLCL0290Scrn00_Add_Config".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_AddConfig((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg, glblCmpyCd);

            } else if ("NLCL0290Scrn00_Search_RtlWHInfo".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_SearchRtlWHInfo((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg, glblCmpyCd);

            } else if ("NLCL0290Scrn00_On_Change_Reason".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_OnChangeReason((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg, glblCmpyCd);

            } else if ("NLCL0290Scrn00_TempleteFileForUpload".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_TempleteFileForUpload((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg, glblCmpyCd);

//            } else if ("NLCL0290Scrn00_OpenWin_Account_Header".equals(screenAplID)) {
//                doProcess_NLCL0290Scrn00_OpenWin_Account_Header((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg, glblCmpyCd);

            } else if ("NLCL0290Scrn00_OpenWin_Account_Detail".equals(screenAplID)) {
                doProcess_NLCL0290Scrn00_OpenWin_Account_Detail((NLCL0290CMsg) cMsg, (NLCL0290SMsg) sMsg, glblCmpyCd);
                // QC#11785 add end
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }
            // START 2022/10/21 N.Takatsu[QC#60603, ADD]
            if (!"NLCL0290Scrn00_CMN_Submit".equals(screenAplID) && !"NLCL0290Scrn00_PageNext".equals(screenAplID) && !"NLCL0290Scrn00_PagePrev".equals(screenAplID)) {
                bizMsg.xxWrnSkipFlg_A.clear();
            }
            // END 2022/10/21 N.Takatsu[QC#60603, ADD]
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLCL0290_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290_INIT START ----- ", this);

        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;

        // QC#18380 Update.
        String tmpInvtyOrdNum = bizMsg.invtyOrdNum_H.getValue();

        bizMsg.clear();
        globalMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);
        NLCL0290CommonLogic.setPulldownAdjTrxTypeList(getGlobalCompanyCode(), bizMsg);
        // QC:18472
        // NLCL0290CommonLogic.setPulldownLocStsList(getGlobalCompanyCode(),
        // bizMsg);
        NLCL0290CommonLogic.setPulldownStkStsList(getGlobalCompanyCode(), bizMsg);
        ZYPCodeDataUtil.createPulldownList(ADJ_CATG.class, bizMsg.adjCatgCd_LC, bizMsg.adjCatgDescTxt_LD);

        // QC#18380 Update.
        if (ZYPCommonFunc.hasValue(tmpInvtyOrdNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_H, tmpInvtyOrdNum);
            getAdjustmentOrder(bizMsg, globalMsg);
        }

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290_INIT END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_New(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_New START ----- ", this);

        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;

        Map<String, Object> rtlWhMap = NLCL0290CommonLogic.getRtlWhMap(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), bizMsg.rtlWhNm_H.getValue());
        if (rtlWhMap == null) {
            bizMsg.rtlWhCd_H.setErrorInfo(1, "NPAM1361E", new String[] {"Warehouse" });
            return;
        } else {
            String rtlWhCd = (String) rtlWhMap.get("RTL_WH_CD");
            String rtlWhNm = (String) rtlWhMap.get("RTL_WH_NM");
            String invtyLocCd = (String) rtlWhMap.get("INVTY_LOC_CD");
            NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(getGlobalCompanyCode(), invtyLocCd, BUSINESS_ID, null, ZYPConstant.FLG_ON_Y);
            if (ZYPCommonFunc.hasValue(enableWHData.getXxMsgId())) {
                bizMsg.rtlWhCd_H.setErrorInfo(1, enableWHData.getXxMsgId());
            }

            ADJ_TRX_TPTMsg adjTrxTpTMsg = NLCL0290CommonLogic.getAdjTrxTpTMsg(getGlobalCompanyCode(), bizMsg.adjTrxTpCd_H.getValue());
            if (adjTrxTpTMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.acctAliasAvalFlg_H, adjTrxTpTMsg.acctAliasAvalFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.acctReqFlg_H, adjTrxTpTMsg.acctReqFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.destSwhReqFlg_H, adjTrxTpTMsg.destSwhReqFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.adjQtyIncrFlg_H, adjTrxTpTMsg.adjQtyIncrFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.adjQtyDecrFlg_H, adjTrxTpTMsg.adjQtyDecrFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.trxCd_H, adjTrxTpTMsg.trxCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.trxRsnCd_H, adjTrxTpTMsg.trxRsnCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdTpCd_H, adjTrxTpTMsg.invtyOrdTpCd);

                // QC:18472
//                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.acctAliasAvalFlg_H.getValue())) {
//                    NLCL0290CommonLogic.setAdjAcctAliasList(getGlobalCompanyCode(), rtlWhCd, bizMsg);
//                    if (!ZYPCommonFunc.hasValue(bizMsg.adjAcctAliasNm_LC.no(0).getValue())) {
//                        bizMsg.adjAcctAliasNm_H.setErrorInfo(1, "NPAM1361E", new String[] {"Alias Name" });
//                    }
//                }
            }

            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H, rtlWhCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_HL, rtlWhCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H, rtlWhNm);
            NLCL0290CommonLogic.setPulldownRtlSwhList(getGlobalCompanyCode(), bizMsg);

            // QC:18472
            // SWH does not exist in the master.
            if (!ZYPCommonFunc.hasValue(bizMsg.rtlSwhCd_LC.no(0))) {
                if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                    bizMsg.rtlWhCd_H.setErrorInfo(1, "NLCM0223E", new String[] {bizMsg.rtlWhCd_H.getValue(), "Sub Warehouse Transfer"});
                    return;
                }
            }


            // 9seg Default
//            DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(BUSINESS_ID + bizMsg.adjTrxTpCd_H.getValue());
//            if (tMsg == null) {
//                bizMsg.setMessageInfo(NZZM0000E);
//            } else {
//
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_OFF_N);
//
//                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaCmpyDplyFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd_HG, tMsg.coaCmpyCd.getValue());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_ON_Y);
//                }
//                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaAfflDplyFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd_HG, tMsg.coaAfflCd.getValue());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_ON_Y);
//                }
//                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaBrDplyFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_HG, tMsg.coaBrCd.getValue());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_ON_Y);
//                }
//                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaCcDplyFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_HG, tMsg.coaCcCd.getValue());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_ON_Y);
//                }
//                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaAcctDplyFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_HG, tMsg.coaAcctCd.getValue());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_ON_Y);
//                }
//                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaProdDplyFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_HG, tMsg.coaProdCd.getValue());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_ON_Y);
//                }
//                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaChDplyFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd_HG, tMsg.coaChCd.getValue());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_ON_Y);
//                }
//                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaProjDplyFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd_HG, tMsg.coaProjCd.getValue());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_ON_Y);
//                }
//                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaExtnDplyFlg.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_HG, tMsg.coaExtnCd.getValue());
//                } else {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_H, ZYPConstant.FLG_ON_Y);
//                }
//
//            }
        }

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_New END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_CMN_Clear START ----- ", this);
        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;

        // QC#18380 Update.
        bizMsg.clear();
        globalMsg.clear();

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);

        doProcess_NLCL0290_INIT(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_CMN_Clear END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_CMN_Submit START ----- ", this);

        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;
        // START 2022/10/21 N.Takatsu[QC#60603, ADD]
        if (ERROR.equals(bizMsg.getMessageKind()) || WARNING.equals(bizMsg.getMessageKind())) {
            return;
        }
        // END 2022/10/21 N.Takatsu[QC#60603, ADD]
        doProcess_NLCL0290Scrn00_Search(bizMsg, globalMsg);
        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            bizMsg.setMessageInfo("NPAM0005I");
        }

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_CMN_Submit END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_Search_Item(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Search_Item START ----- ", this);

        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;

        Map<String, Object> mdseMap = NLCL0290CommonLogic.getMdseMap(getGlobalCompanyCode(), bizMsg.A.no(bizMsg.xxNum.getValueInt()).mdseCd_A.getValue());

        if (mdseMap == null) {
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).mdseCd_A.setErrorInfo(1, "NMZM0154E", new String[] {"Item" });
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).mdseDescShortTxt_A.clear();
            return;

        } else {
            String mdseDescShortTxt = (String) mdseMap.get("MDSE_DESC_SHORT_TXT");
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).mdseDescShortTxt_A, mdseDescShortTxt);
        }
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Search_Item END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_Delete_Line(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Delete_Line START ----- ", this);
        int procCnt = 0;
        NLCL0290CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        // QC:18490
        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk)) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(0).xxChkBox_A.getValue())) {
                bizMsg.svcConfigMstrPk.clear();
                ZYPTableUtil.clear(bizMsg.A);
                ZYPTableUtil.clear(globalMsg.A);
            } else {
                bizMsg.setMessageInfo(MESSAGE_ID.NFAM0075E.toString());
                return;
            }
        } else {
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A.getValue())) {
                    procCnt++;
                }
            }
            if (procCnt == 0) {
                bizMsg.setMessageInfo(MESSAGE_ID.NFAM0075E.toString());
                return;
            }

            NLCL0290SMsg tmpMsg = new NLCL0290SMsg();
            EZDMsg.copy(globalMsg, null, tmpMsg, null);
            ZYPTableUtil.clear(globalMsg.A);

            int j = 0;
            for (int i = 0; i < tmpMsg.A.getValidCount(); i++) {
                if (!ZYPConstant.CHKBOX_ON_Y.equals(tmpMsg.A.no(i).xxChkBox_A.getValue())) {
                    EZDMsg.copy(tmpMsg.A.no(i), null, globalMsg.A.no(j), null);
                    j++;
                }
            }
            globalMsg.A.setValidCount(j);
            BigDecimal lastPageFromNum = NLCL0290CommonLogic.getLastPageFromNum(bizMsg, globalMsg);
            if (bizMsg.xxPageShowFromNum_A.getValue().compareTo(lastPageFromNum) < 0) {
                NLCL0290CommonLogic.dispPage(bizMsg, bizMsg.A, globalMsg.A);
            } else {
                NLCL0290CommonLogic.lastPage(bizMsg, globalMsg);
            }
        }

        bizMsg.setMessageInfo("NZZM0002I");
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Delete_Line END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_Add_Line(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Add_Line START ----- ", this);

        // QC:18472
        NLCL0290CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);

        int no = globalMsg.A.getValidCount();

        if (no == globalMsg.A.length()) {
            bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(globalMsg.A.length()) });
            return;
        }

        // QC:18472 Start
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).rtlWhCd_AH , bizMsg.rtlWhCd_H);
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).rtlWhNm_AH , bizMsg.rtlWhNm_H);
        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).locStsCd_AH , LOC_STS.DC_STOCK);
        // QC:18472 End

        globalMsg.A.setValidCount(no + 1);

        NLCL0290CommonLogic.lastPage(bizMsg, globalMsg);
        bizMsg.setMessageInfo("NZZM0002I");

        // QC:14488
        // boolean check = NLCL0290CommonLogic.validateSegmentStringForHeader(getGlobalCompanyCode(), bizMsg, globalMsg, bizMsg.xxScrItem130Txt_H.getValue(), false);

//        if (check) {
//            NLCL0290CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
//
//            int no = globalMsg.A.getValidCount();
//
//            if (no == globalMsg.A.length()) {
//                bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(globalMsg.A.length()) });
//                return;
//            }
//
//            globalMsg.A.setValidCount(no + 1);
//
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaCmpyCd_AG, bizMsg.coaCmpyCd_HG);
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaAfflCd_AG, bizMsg.coaAfflCd_HG);
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaBrCd_AG, bizMsg.coaBrCd_HG);
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaCcCd_AG, bizMsg.coaCcCd_HG);
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaAcctCd_AG, bizMsg.coaAcctCd_HG);
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaProdCd_AG, bizMsg.coaProdCd_HG);
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaChCd_AG, bizMsg.coaChCd_HG);
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaProjCd_AG, bizMsg.coaProjCd_HG);
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaExtnCd_AG, bizMsg.coaExtnCd_HG);
//            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(no).coaEnblFlg_A, bizMsg.coaEnblFlg_H);
//
//            // QC:14488
//            if (globalMsg.A.getValidCount() > 0 && ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt_H)) {
//                for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCmpyCd_A, bizMsg.coaCmpyCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAfflCd_A, bizMsg.coaAfflCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaBrCd_A, bizMsg.coaBrCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCcCd_A, bizMsg.coaCcCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAcctCd_A, bizMsg.coaAcctCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProdCd_A, bizMsg.coaProdCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaChCd_A, bizMsg.coaChCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProjCd_A, bizMsg.coaProjCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaExtnCd_A, bizMsg.coaExtnCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxScrItem130Txt_A, bizMsg.xxScrItem130Txt_H);
//                }
//            }
//
//            NLCL0290CommonLogic.lastPage(bizMsg, globalMsg);
//            bizMsg.setMessageInfo("NZZM0002I");
//        }
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Add_Line END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_PageNext(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_PageNext START ----- ", this);

        // QC#15558 Start
        globalMsg.A.clearErrorInfo();
        // QC#15558 End

        NLCL0290CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        NLCL0290CommonLogic.nextPage(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_PageNext END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_PagePrev(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_PagePrev START ----- ", this);

        // QC#15558 Start
        globalMsg.A.clearErrorInfo();
        // QC#15558 End

        NLCL0290CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
        NLCL0290CommonLogic.prevPage(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_PagePrev END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Search START ----- ", this);

        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;
        getAdjustmentOrder(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Search END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_OnChange_AdjAcctAlias(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Search START ----- ", this);
        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;

        // QC:18472
//        String coaCmpyCd = null;
//        String coaAfflCd = null;
//        String coaBrCd = null;
//        String coaCcCd = null;
//        String coaAcctCd = null;
//        String coaProdCd = null;
//        String coaChCd = null;
//        String coaProjCd = null;
//        String coaExtnCd = null;
//        StringBuilder acctNum = new StringBuilder();
//        Boolean defaultFlg = true;

        // QC:18472
//        if (ZYPCommonFunc.hasValue(bizMsg.adjAcctAliasNm_H)) {
//
//            Map<String, Object> adjAcctAliasMap = NLCL0290CommonLogic.getAdjAcctAliasMap(getGlobalCompanyCode(), bizMsg);
//            if (adjAcctAliasMap != null) {
//                coaCmpyCd = (String) adjAcctAliasMap.get("COA_CMPY_CD");
//                coaAfflCd = (String) adjAcctAliasMap.get("COA_AFFL_CD");
//                coaBrCd = (String) adjAcctAliasMap.get("COA_BR_CD");
//                coaCcCd = (String) adjAcctAliasMap.get("COA_CC_CD");
//                coaAcctCd = (String) adjAcctAliasMap.get("COA_ACCT_CD");
//                coaProdCd = (String) adjAcctAliasMap.get("COA_PROD_CD");
//                coaChCd = (String) adjAcctAliasMap.get("COA_CH_CD");
//                coaProjCd = (String) adjAcctAliasMap.get("COA_PROJ_CD");
//                coaExtnCd = (String) adjAcctAliasMap.get("COA_EXTN_CD");
//
//                if (ZYPCommonFunc.hasValue(coaCmpyCd) && ZYPCommonFunc.hasValue(coaAfflCd) && ZYPCommonFunc.hasValue(coaBrCd) && ZYPCommonFunc.hasValue(coaCcCd) && ZYPCommonFunc.hasValue(coaAcctCd) && ZYPCommonFunc.hasValue(coaProdCd)
//                        && ZYPCommonFunc.hasValue(coaChCd) && ZYPCommonFunc.hasValue(coaProjCd) && ZYPCommonFunc.hasValue(coaExtnCd)) {
//
//                    // QC#11785 9seg sort change
//                    acctNum.append(coaCmpyCd);
//                    acctNum.append(".");
//                    acctNum.append(coaBrCd);
//                    acctNum.append(".");
//                    acctNum.append(coaCcCd);
//                    acctNum.append(".");
//                    acctNum.append(coaAcctCd);
//                    acctNum.append(".");
//                    acctNum.append(coaProdCd);
//                    acctNum.append(".");
//                    acctNum.append(coaChCd);
//                    acctNum.append(".");
//                    acctNum.append(coaAfflCd);
//                    acctNum.append(".");
//                    acctNum.append(coaProjCd);
//                    acctNum.append(".");
//                    acctNum.append(coaExtnCd);
//
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd_HG, coaCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd_HG, coaAfflCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_HG, coaBrCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_HG, coaCcCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_HG, coaAcctCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_HG, coaProdCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd_HG, coaChCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd_HG, coaProjCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_HG, coaExtnCd);
//                    defaultFlg = false;
//                }
//            }
//        }

//        if (defaultFlg == true && ZYPCommonFunc.hasValue(bizMsg.coaEnblFlg_H) && ZYPConstant.FLG_OFF_N.equals(bizMsg.coaEnblFlg_H.getValue())) {
//            DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(BUSINESS_ID + bizMsg.adjTrxTpCd_H.getValue());
//            if (tMsg != null) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd_HG, tMsg.coaCmpyCd.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd_HG, tMsg.coaAfflCd.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_HG, tMsg.coaBrCd.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_HG, tMsg.coaCcCd.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_HG, tMsg.coaAcctCd.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_HG, tMsg.coaProdCd.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd_HG, tMsg.coaChCd.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd_HG, tMsg.coaProjCd.getValue());
//                ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_HG, tMsg.coaExtnCd.getValue());
//            }
//        }
//
//        ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd_H, coaCmpyCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd_H, coaAfflCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_H, coaBrCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_H, coaCcCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_H, coaAcctCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H, coaProdCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd_H, coaChCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd_H, coaProjCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_H, coaExtnCd);
        // QC:18472
        //ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem130Txt_H, acctNum.toString());

        // QC:18472
        // QC:14488
        //boolean check = NLCL0290CommonLogic.validateSegmentStringForHeader(getGlobalCompanyCode(), bizMsg, globalMsg, bizMsg.xxScrItem130Txt_H.getValue(), false);

        NLCL0290CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);

        // QC:18472
//        if (globalMsg.A.getValidCount() > 0) {
//            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                // QC:14488
//                if (check) {
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCmpyCd_A, bizMsg.coaCmpyCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAfflCd_A, bizMsg.coaAfflCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaBrCd_A, bizMsg.coaBrCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCcCd_A, bizMsg.coaCcCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAcctCd_A, bizMsg.coaAcctCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProdCd_A, bizMsg.coaProdCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaChCd_A, bizMsg.coaChCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProjCd_A, bizMsg.coaProjCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaExtnCd_A, bizMsg.coaExtnCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxScrItem130Txt_A, bizMsg.xxScrItem130Txt_H);
//                }
//
//                if (check || (defaultFlg == true && ZYPCommonFunc.hasValue(bizMsg.coaEnblFlg_H) && ZYPConstant.FLG_OFF_N.equals(bizMsg.coaEnblFlg_H.getValue()))) {
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCmpyCd_AG, bizMsg.coaCmpyCd_HG);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAfflCd_AG, bizMsg.coaAfflCd_HG);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaBrCd_AG, bizMsg.coaBrCd_HG);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCcCd_AG, bizMsg.coaCcCd_HG);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAcctCd_AG, bizMsg.coaAcctCd_HG);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProdCd_AG, bizMsg.coaProdCd_HG);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaChCd_AG, bizMsg.coaChCd_HG);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProjCd_AG, bizMsg.coaProjCd_HG);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaExtnCd_AG, bizMsg.coaExtnCd_HG);
//                }
//            }
//            NLCL0290CommonLogic.dispPage(bizMsg, bizMsg.A, globalMsg.A);
//        }
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_Search END ----- ", this);
    }

    private void doProcess_NLCL0290_NMAL2550(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290_NMAL2550 START ----- ", this);
        // QC:18472 Start
//        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
//        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;
//
//        NLCL0290CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);
//
//        if (globalMsg.A.getValidCount() > 0) {
//            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
//                if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxScrItem130Txt_A)) {
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCmpyCd_A, bizMsg.coaCmpyCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAfflCd_A, bizMsg.coaAfflCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaBrCd_A, bizMsg.coaBrCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCcCd_A, bizMsg.coaCcCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAcctCd_A, bizMsg.coaAcctCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProdCd_A, bizMsg.coaProdCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaChCd_A, bizMsg.coaChCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProjCd_A, bizMsg.coaProjCd_H);
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaExtnCd_A, bizMsg.coaExtnCd_H);
//                    // QC:18472
//                    //ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxScrItem130Txt_A, bizMsg.xxScrItem130Txt_H);
//                }
//            }
//            NLCL0290CommonLogic.dispPage(bizMsg, bizMsg.A, globalMsg.A);
//        }
        // QC:18472 End
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290_NMAL2550 END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_AddConfig(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_AddConfig START ----- ", this);

        getAddConfig(bizMsg, globalMsg, glblCmpyCd);

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_AddConfig END ----- ", this);
    }

    private void doProcess_NLCL0290_NSAL1240(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290_NSAL1240 START ----- ", this);

        if (ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk)) {
            getAddConfig(bizMsg, globalMsg, glblCmpyCd);
        }

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290_NSAL1240 END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_SearchRtlWHInfo(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_SearchRtlWHInfo START ----- ", this);

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.rtlWhCd.setValue(bizMsg.rtlWhCd_H.getValue());

        RTL_WHTMsg existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            //QC#25840 START
            bizMsg.rtlWhNm_H.setValue(existTMsg.rtlWhNm.getValue());
//            bizMsg.rtlWhNm_H.setValue(existTMsg.rtlWhDescTxt.getValue());
            //QC#25840 END
        } else {
            bizMsg.rtlWhCd_H.setErrorInfo(1, "NPAM1361E", new String[] {"Warehouse" });
        }

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_SearchRtlWHInfo END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_OnChangeReason(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_OnChangeReason START ----- ", this);

        if (ADJ_CATG.SPECIAL_ACCOUNT.equals(bizMsg.A.no(bizMsg.xxNum.getValueInt()).adjCatgCd_A.getValue())) {
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).xxScrItem130Txt_A.clear();
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).invtyOrdLineCmntTxt_A.clear();
        } else {
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).invtyOrdLineCmntTxt_A.clear();
            //Get Default Account Code
            ADJ_CATGTMsg tMsg = new ADJ_CATGTMsg();

            tMsg.glblCmpyCd.setValue(glblCmpyCd);
            tMsg.adjCatgCd.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).adjCatgCd_A.getValue());

            ADJ_CATGTMsg existTMsg = (ADJ_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);

            bizMsg.A.no(bizMsg.xxNum.getValueInt()).xxScrItem130Txt_A.setValue(existTMsg.coaAcctCd.getValue());
        }

        EZDDebugOutput.println(1, "----- doProcess_NLCL0290Scrn00_OnChangeReason END ----- ", this);
    }

    private void doProcess_NLCL0290Scrn00_TempleteFileForUpload(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "----- doProcess_NPAL1070Scrn00_TempleteFileForUpload START ----- ", this);

        NLCL0290F00FMsg fMsg = new NLCL0290F00FMsg();

        bizMsg.xxFileData_UP.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("NLCL0290_InventoryAdjustmentForm"), ".csv");
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData_UP.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(HEADER_NAME);

        csvOutFile.close();

        EZDDebugOutput.println(1, "----- doProcess_NPAL1070Scrn00_TempleteFileForUpload END ----- ", this);
    }

    private void getAdjustmentOrder(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- getAdjustmentOrder START ----- ", this);

        NLCL0290_ACMsgArray bizMsgArray = bizMsg.A;
        NLCL0290_ASMsgArray globalMsgArray = globalMsg.A;

        ZYPTableUtil.clear(bizMsgArray);
        ZYPTableUtil.clear(globalMsgArray);

        bizMsg.xxPageShowFromNum_A.clear();
        bizMsg.xxPageShowOfNum_A.clear();
        bizMsg.xxPageShowToNum_A.clear();

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("invtyOrdNum", bizMsg.invtyOrdNum_H.getValue());
        prmMap.put("rowNum", String.valueOf(globalMsg.A.length()));
        // QC:18472
        prmMap.put("adjustment", String.valueOf(INVTY_ORD_TP.ADJUSTMENT));
        prmMap.put("subWhChange", String.valueOf(INVTY_ORD_TP.SUB_WH_CHANGE));
        // QC#29172 add start
        prmMap.put("stockStatusChange", String.valueOf(INVTY_ORD_TP.STOCK_STATUS_CHANGE));
        // QC#29172 add end
        prmMap.put("firstLineNum", String.valueOf("001"));

        S21SsmEZDResult ssmResult = NLCL0290Query.getInstance().getAdjustmentOrder(bizMsg, globalMsg, prmMap);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            int maxCnt = globalMsgArray.length();

            if (queryResCnt > maxCnt) {
                queryResCnt = maxCnt;
                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0098I.toString(), new String[] {Integer.toString(maxCnt) });
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_H, globalMsg.A.no(0).invtyOrdNum_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_HD, globalMsg.A.no(0).invtyOrdNum_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.adjTrxTpCd_H, globalMsg.A.no(0).adjTrxTpCd_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H, globalMsg.A.no(0).rtlWhCd_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H, globalMsg.A.no(0).rtlWhNm_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.locStsCd_H, globalMsg.A.no(0).locStsCd_AH);
            // QC:18472
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(0).svcConfigMstrPk_A)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk, globalMsg.A.no(0).svcConfigMstrPk_A);
            }
            //ZYPEZDItemValueSetter.setValue(bizMsg.stkStsCd_H, globalMsg.A.no(0).stkStsCd_AH);

            NLCL0290CommonLogic.setPulldownRtlSwhList(getGlobalCompanyCode(), bizMsg);

            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).invtyOrdSubmtTs_A)) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxTsDsp19Txt_A, ZYPDateUtil.formatEzd17ToDisp(globalMsg.A.no(i).invtyOrdSubmtTs_A.getValue(), true, true, true, true).substring(0, 19));
                }
            }
            EZDMsg.copy(globalMsgArray, null, bizMsgArray, null);

            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowOfNum_A.setValue(queryResCnt);
            bizMsg.xxPageShowToNum_A.setValue(bizMsgArray.getValidCount());
            // QC:18490 Start
            bizMsg.svcConfigMstrPk.setValue(globalMsg.A.no(0).svcConfigMstrPk_A.getValue());
            // QC:18490 End
        } else {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0007I.toString());
            return;
        }

        EZDDebugOutput.println(1, "----- getAdjustmentOrder END ----- ", this);
    }

    private void getAddConfig(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, String glblCmpyCd) {
        EZDDebugOutput.println(1, "----- getAdjustmentOrder START ----- ", this);

        NLCL0290_ACMsgArray bizMsgArray = bizMsg.A;
        NLCL0290_ASMsgArray globalMsgArray = globalMsg.A;

        ZYPTableUtil.clear(bizMsgArray);
        ZYPTableUtil.clear(globalMsgArray);

        bizMsg.xxPageShowFromNum_A.clear();
        bizMsg.xxPageShowOfNum_A.clear();
        bizMsg.xxPageShowToNum_A.clear();

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcConfigMstrPk", bizMsg.svcConfigMstrPk.getValue());
        prmMap.put("svcMachMaintAvalFlg", ZYPConstant.FLG_ON_Y);
        prmMap.put("crat", String.valueOf(SVC_MACH_MSTR_STS.CREATED));
        prmMap.put("removed", String.valueOf(SVC_MACH_MSTR_STS.REMOVED));
        prmMap.put("dcStock", String.valueOf(LOC_STS.DC_STOCK));
        prmMap.put("adjTrxTpCd", String.valueOf(bizMsg.adjTrxTpCd_H.getValue()));
        prmMap.put("adjustment", String.valueOf(ADJ_TRX_TP.ADJUSTMENT));
        prmMap.put("subWhTransfer", String.valueOf(ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER));
        // QC#29172 add start
        prmMap.put("stockStatusChange", String.valueOf(ADJ_TRX_TP.STOCK_STATUS_CHANGE));
        // QC#29172 add end

        S21SsmEZDResult ssmResult = NLCL0290Query.getInstance().getAddConfig(bizMsg, globalMsg, prmMap);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            int maxCnt = globalMsgArray.length();

            if (queryResCnt > maxCnt) {
                queryResCnt = maxCnt;
                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0098I.toString(), new String[] {Integer.toString(maxCnt) });
            }

            if (!bizMsg.rtlWhCd_H.getValue().equals(globalMsg.A.no(0).rtlWhCd_AH.getValue())) {
                globalMsg.clear();
                bizMsg.svcConfigMstrPk.setErrorInfo(1, "NLCM0213E", new String[] {bizMsg.rtlWhCd_H.getValue()});
                return;
            }

            if (ADJ_TRX_TP.ADJUSTMENT.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toInvtyLocCd_A, globalMsg.A.no(i).fromInvtyLocCd_A);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toRtlSwhCd_A, globalMsg.A.no(i).fromRtlSwhCd_A);
                }
            }
            // QC#29172 add start
            if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toInvtyLocCd_A, globalMsg.A.no(i).fromInvtyLocCd_A);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toRtlSwhCd_A, globalMsg.A.no(i).fromRtlSwhCd_A);
                }
            }
            // QC#29172 add end


            EZDMsg.copy(globalMsgArray, null, bizMsgArray, null);

            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowOfNum_A.setValue(queryResCnt);
            bizMsg.xxPageShowToNum_A.setValue(bizMsgArray.getValidCount());

        } else {
            bizMsg.svcConfigMstrPk.setErrorInfo(1, "NPAM1361E", new String[] {"Config#"});
        }

        EZDDebugOutput.println(1, "----- getAdjustmentOrder END ----- ", this);
    }

    /**
     * CMN_Download
     * @param cMsg NSBL0450CMsg
     * @param sMsg NSBL0450SMsg
     */
    private void doProcess_NLCL0290Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {

        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;

        NLCL0290CommonLogic.copyPage(bizMsg, bizMsg.A, globalMsg.A);

        bizMsg.xxFileData_UP.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
        String csvOutTempPath = bizMsg.xxFileData_UP.getTempFilePath();

        NLCL0290F00FMsg fMsg = new NLCL0290F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(csvOutTempPath, fMsg);
        csvOutFile.writeHeader(HEADER_NAME.clone());

        if (ZYPCommonFunc.hasValue(bizMsg.adjTrxTpCd_H)) {
            ZYPEZDItemValueSetter.setValue(fMsg.adjTrxTpDescTxt, ZYPCodeDataUtil.getName(ADJ_TRX_TP.class, getGlobalCompanyCode(), bizMsg.adjTrxTpCd_H.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(fMsg.invtyLocCd, bizMsg.rtlWhCd_H);
        ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, bizMsg.rtlWhNm_H);
        // QC:18472
        //ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt, bizMsg.xxScrItem130Txt_H);
        //ZYPEZDItemValueSetter.setValue(fMsg.adjAcctAliasNm, bizMsg.adjAcctAliasNm_H);
        // QC# 25925 Delete Location Status.
//        if (ZYPCommonFunc.hasValue(bizMsg.locStsCd_H)) {
//            ZYPEZDItemValueSetter.setValue(fMsg.locStsDescTxt, ZYPCodeDataUtil.getName(LOC_STS.class, getGlobalCompanyCode(), bizMsg.locStsCd_H.getValue()));
//        }
        // QC:18472 Start
//        if (ZYPCommonFunc.hasValue(bizMsg.stkStsCd_H)) {
//            ZYPEZDItemValueSetter.setValue(fMsg.stkStsDescTxt, ZYPCodeDataUtil.getName(STK_STS.class, getGlobalCompanyCode(), bizMsg.stkStsCd_H.getValue()));
//            ;
//        }
        // QC:18472 End
        ZYPEZDItemValueSetter.setValue(fMsg.invtyOrdNum, bizMsg.invtyOrdNum_H);

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, globalMsg.A.no(i).mdseCd_A);
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, globalMsg.A.no(i).mdseDescShortTxt_A);
            ZYPEZDItemValueSetter.setValue(fMsg.fromRtlSwhCd, globalMsg.A.no(i).fromRtlSwhCd_A);
            ZYPEZDItemValueSetter.setValue(fMsg.toRtlSwhCd, globalMsg.A.no(i).toRtlSwhCd_A);
            ZYPEZDItemValueSetter.setValue(fMsg.ordQty, globalMsg.A.no(i).ordQty_A);
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).adjCatgCd_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.adjCatgDescTxt, ZYPCodeDataUtil.getName(ADJ_CATG.class, getGlobalCompanyCode(), globalMsg.A.no(i).adjCatgCd_A.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(fMsg.invtyOrdLineCmntTxt, globalMsg.A.no(i).invtyOrdLineCmntTxt_A);
            ZYPEZDItemValueSetter.setValue(fMsg.serNum, globalMsg.A.no(i).serNum_A);
            ZYPEZDItemValueSetter.setValue(fMsg.invtyOrdStsDescTxt, globalMsg.A.no(i).invtyOrdStsDescTxt_A);
            ZYPEZDItemValueSetter.setValue(fMsg.invtyOrdLineCostAmt, globalMsg.A.no(i).invtyOrdLineCostAmt_A);
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).invtyOrdSubmtTs_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt, ZYPDateUtil.formatEzd17ToDisp(globalMsg.A.no(i).invtyOrdSubmtTs_A.getValue()));
            }
            ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk, globalMsg.A.no(i).svcConfigMstrPk_A);
            ZYPEZDItemValueSetter.setValue(fMsg.svcMachTpDescTxt, globalMsg.A.no(i).svcMachTpDescTxt_A);

            // QC:18472 Start
            // QC#29172 mod start
            // ZYPEZDItemValueSetter.setValue(fMsg.stkStsDescTxt,
            // ZYPCodeDataUtil.getName(STK_STS.class,
            // getGlobalCompanyCode(),
            // globalMsg.A.no(i).stkStsCd_A.getValue()));
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).stkStsCd_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.stkStsDescTxt_F, ZYPCodeDataUtil.getName(STK_STS.class, getGlobalCompanyCode(), globalMsg.A.no(i).stkStsCd_A.getValue()));
            }
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).toStkStsCd_A)) {
                ZYPEZDItemValueSetter.setValue(fMsg.stkStsDescTxt_T, ZYPCodeDataUtil.getName(STK_STS.class, getGlobalCompanyCode(), globalMsg.A.no(i).toStkStsCd_A.getValue()));
            }

            // QC#29172 mod end
            ZYPEZDItemValueSetter.setValue(fMsg.coaAcctCd, globalMsg.A.no(i).xxScrItem130Txt_A);
            ZYPEZDItemValueSetter.setValue(fMsg.invtyAvalQty, globalMsg.A.no(i).invtyAvalQty_A);

            // QC:18472 End
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    /**
     * IMPORT
     * @param cMsg NLCL0290CMsg
     * @param sMsg NLCL0290SMsg
     */
    private void doProcess_NLCL0290Scrn00_Import(NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg) {

        String path = bizMsg.xxFileData_UP.getTempFilePath();
        doProcess_NLCL0290_INIT(bizMsg, globalMsg);

        NLCL0290F00FMsg fMsg = new NLCL0290F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        int count = 0;

        Boolean adjAcctAliasFlg = false;
        try {
            int header = mappedFile.read();
            if (header == 1) {
                bizMsg.setMessageInfo("ZYEM0004E");
            }
            fMsg.clear();
            int status = -1;
            int i = 0;

            // QC:18472
            // get 9seg Default
            //DEF_DPLY_COA_INFOTMsg tMsg = null;

            // rtlSwhCdList
            List<String> rtlSwhCdList = null;

            while ((status = mappedFile.read()) != 1) {
                count++;
                if (validateUploadFile(status, count, bizMsg, globalMsg, fMsg)) {
                    // 2019/01/08 QC#29763 Del Start
//                    if ("E".equals(bizMsg.getMessageKind())) {
//                        break;
//                    }
                    // 2019/01/08 QC#29763 Del End

                    if (count == 1) {

                        if (!checkMandatoryCheck(fMsg, bizMsg)) {
                            break;
                        }

                        String adjTrxTpCd = NLCL0290CommonLogic.getAdjTrxTpCd(getGlobalCompanyCode(), fMsg.adjTrxTpDescTxt.getValue());
                        if (!ZYPCommonFunc.hasValue(adjTrxTpCd)) {
                            bizMsg.setMessageInfo("NPAM1361E", new String[] {"Adjustment Transaction Type" });
                        } else {
                            ZYPEZDItemValueSetter.setValue(bizMsg.adjTrxTpCd_H, adjTrxTpCd);
                        }

                        Map<String, Object> rtlWhMap = NLCL0290CommonLogic.getRtlWhMap(getGlobalCompanyCode(), fMsg.invtyLocCd.getValue(), fMsg.rtlWhNm.getValue());

                        if (rtlWhMap == null) {
                            bizMsg.setMessageInfo("NPAM1361E", new String[] {"Warehouse" });
                        } else {
                            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H, (String) rtlWhMap.get("RTL_WH_CD"));
                            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H, (String) rtlWhMap.get("RTL_WH_NM"));
                        }

                        if ("E".equals(bizMsg.getMessageKind())) {
                            break;
                        }

                        doProcess_NLCL0290Scrn00_New(bizMsg, globalMsg);
                        ZYPTableUtil.clear(bizMsg.A);
                        ZYPTableUtil.clear(globalMsg.A);

                        if ("E".equals(bizMsg.getMessageKind())) {
                            break;
                        }

                        // QC:18472
//                        if (ZYPCommonFunc.hasValue(fMsg.adjAcctAliasNm.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.acctAliasAvalFlg_H.getValue())) {
//                            String adjAcctAliasNm = fMsg.adjAcctAliasNm.getValue();
//                            Map<String, Object> adjAcctAliasNmMap = NLCL0290CommonLogic.getAdjAcctAliasNm(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), adjAcctAliasNm);

//                            if (adjAcctAliasNmMap != null && adjAcctAliasNmMap.isEmpty() == false) {
//                                ZYPEZDItemValueSetter.setValue(bizMsg.adjAcctAliasNm_H, (String) adjAcctAliasNmMap.get("ADJ_ACCT_ALIAS_NM"));
//                                adjAcctAliasFlg = true;
//                            }
//
//                            if ("E".equals(bizMsg.getMessageKind())) {
//                                break;
//                            }
//                        }
//
//                        tMsg = getDefDplyCoaInfo(BUSINESS_ID + bizMsg.adjTrxTpCd_H.getValue());
//                        if (tMsg == null) {
//                            bizMsg.setMessageInfo(NZZM0000E);
//                            break;
//                        }

                        // QC#25925 Delete location status.
//                        if (ZYPCommonFunc.hasValue(fMsg.locStsDescTxt)) {
//                            String locStsCd = NLCL0290CommonLogic.getLocStsCd(getGlobalCompanyCode(), fMsg.locStsDescTxt.getValue());
//                            if (ZYPCommonFunc.hasValue(locStsCd)) {
//                                ZYPEZDItemValueSetter.setValue(bizMsg.locStsCd_H, locStsCd);
//                            }
//                        }
                        // rtlSwhCdList
                        rtlSwhCdList = NLCL0290CommonLogic.getRtlSwhCdList(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue());
                    }

                    if (!ZYPCommonFunc.hasValue(fMsg.adjTrxTpDescTxt)) {
                        continue;
                    }

                    // QC#25925 Set Location Status (DC Stock)
                    ZYPEZDItemValueSetter.setValue(bizMsg.locStsCd_H, LOC_STS.DC_STOCK);

                    // set import file to sMsg work
                    // QC:18472 Start
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).rtlWhCd_AH , bizMsg.rtlWhCd_H);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).rtlWhNm_AH , bizMsg.rtlWhNm_H);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).mdseCd_A, fMsg.mdseCd);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).ordQty_A, fMsg.ordQty);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).locStsCd_AH , bizMsg.locStsCd_H);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).invtyOrdLineCmntTxt_A, fMsg.invtyOrdLineCmntTxt);
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).serNum_A, fMsg.serNum);

                    // Stock Status
                    // QC#29172 mod start
                    // if (ZYPCommonFunc.hasValue(fMsg.stkStsDescTxt))
                    // {
                    // String stkStsCd =
                    // NLCL0290CommonLogic.getStkStsCd(getGlobalCompanyCode(),
                    // fMsg.stkStsDescTxt.getValue());
                    // ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).stkStsCd_A,
                    // stkStsCd);
                    // }
                    if (ZYPCommonFunc.hasValue(fMsg.stkStsDescTxt_F)) {
                        String stkStsCd = NLCL0290CommonLogic.getStkStsCd(getGlobalCompanyCode(), fMsg.stkStsDescTxt_F.getValue());
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).stkStsCd_A, stkStsCd);
                    }
                    if (ZYPCommonFunc.hasValue(fMsg.stkStsDescTxt_T)) {
                        String stkStsCd = NLCL0290CommonLogic.getStkStsCd(getGlobalCompanyCode(), fMsg.stkStsDescTxt_T.getValue());
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toStkStsCd_A, stkStsCd);
                    }
                    // QC#29172 mod end

                    // Item Description
                    Map<String, Object> mdseMap = NLCL0290CommonLogic.getMdseMap(getGlobalCompanyCode(), globalMsg.A.no(i).mdseCd_A.getValue());

                    if (mdseMap == null) {
                        bizMsg.setMessageInfo("NPAM1361E", new String[] {"Item#" });
                        // 2019/01/08 QC#29763 Add Start
                        globalMsg.A.no(i).mdseCd_A.setErrorInfo(1, "NPAM1361E", new String[] {"Item#" });
                        // 2019/01/08 QC#29763 Add End
                    } else {
                        String mdseDescShortTxt = (String) mdseMap.get("MDSE_DESC_SHORT_TXT");
                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).mdseDescShortTxt_A, mdseDescShortTxt);
                    }

                    // check fromRtlSwhCd
                    RTL_SWHTMsg swhTmsg = null;
                    if (ADJ_TRX_TP.ADJUSTMENT.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        globalMsg.A.no(i).fromRtlSwhCd_A.clear();
                        globalMsg.A.no(i).fromInvtyLocCd_A.clear();
                    } else if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        swhTmsg = NLCL0290CommonLogic.getRTL_SWHTMsg(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), fMsg.fromRtlSwhCd.getValue());
                        if (swhTmsg == null) {
                            bizMsg.setMessageInfo("NPAM1361E", new String[] {"Source Sub WH" });
                            // 2019/01/08 QC#29763 Add Start
                            globalMsg.A.no(i).fromRtlSwhCd_A.setErrorInfo(1, "NPAM1361E", new String[] {"Source Sub WH" });
                            // 2019/01/08 QC#29763 Add End
                        } else {
                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).fromRtlSwhCd_A, swhTmsg.rtlSwhCd);
                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).fromInvtyLocCd_A,  swhTmsg.rtlWhCd.getValue() + swhTmsg.rtlSwhCd.getValue());
                        }
                        // QC#29172 add start
                    } else if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        swhTmsg = NLCL0290CommonLogic.getRTL_SWHTMsg(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), fMsg.fromRtlSwhCd.getValue());
                        if (swhTmsg == null) {
                            bizMsg.setMessageInfo("NPAM1361E", new String[] {"Source Sub WH" });
                            // 2019/01/08 QC#29763 Add Start
                            globalMsg.A.no(i).fromRtlSwhCd_A.setErrorInfo(1, "NPAM1361E", new String[] {"Source Sub WH" });
                            // 2019/01/08 QC#29763 Add End
                        } else {
                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).fromRtlSwhCd_A, swhTmsg.rtlSwhCd);
                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).fromInvtyLocCd_A, swhTmsg.rtlWhCd.getValue() + swhTmsg.rtlSwhCd.getValue());
                        }
                        // QC#29172 add end

                    }

//                    if (ZYPConstant.FLG_ON_Y.equals(bizMsg.destSwhReqFlg_H.getValue()) || ZYPConstant.FLG_ON_Y.equals(bizMsg.adjQtyDecrFlg_H.getValue())) {
//                        if (rtlSwhCdList != null && rtlSwhCdList.indexOf(fMsg.fromRtlSwhCd.getValue()) >= 0) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).fromRtlSwhCd_A, fMsg.fromRtlSwhCd);
//                        }
//                    }
                    // check toRtlSwhCd
                    // QC#29172 add start
                    if (!ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        swhTmsg = NLCL0290CommonLogic.getRTL_SWHTMsg(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), fMsg.toRtlSwhCd.getValue());
                        if (swhTmsg == null) {
                            bizMsg.setMessageInfo("NPAM1361E", new String[] {"Destination Sub WH" });
                            // 2019/01/08 QC#29763 Add Start
                            globalMsg.A.no(i).toRtlSwhCd_A.setErrorInfo(1, "NPAM1361E", new String[] {"Destination Sub WH" });
                            // 2019/01/08 QC#29763 Add End
                        } else {
                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toRtlSwhCd_A, swhTmsg.rtlSwhCd);
                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toInvtyLocCd_A, swhTmsg.rtlWhCd.getValue() + swhTmsg.rtlSwhCd.getValue());
                        }
                    }
                    // QC#29172 add end
//                    if (ZYPConstant.FLG_ON_Y.equals(bizMsg.destSwhReqFlg_H.getValue()) || ZYPConstant.FLG_ON_Y.equals(bizMsg.adjQtyIncrFlg_H.getValue())) {
//                        if (rtlSwhCdList != null && rtlSwhCdList.indexOf(fMsg.toRtlSwhCd.getValue()) >= 0) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).toRtlSwhCd_A, fMsg.toRtlSwhCd);
//                        }
//                    }

                    // check Reason Code
                    // QC#26383
                    if (!ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(bizMsg.adjTrxTpCd_H.getValue())) {
                        if (ZYPCommonFunc.hasValue(fMsg.adjCatgDescTxt)) {
                            String adjCatgCd = NLCL0290CommonLogic.getAdjCatgCd(getGlobalCompanyCode(), fMsg.adjCatgDescTxt.getValue());
                            if (ZYPCommonFunc.hasValue(adjCatgCd)) {
                                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).adjCatgCd_A, adjCatgCd);
                            }
                        }

                        // QC:18472 Start
                        // set Account Code
                        ADJ_CATGTMsg adjCatgTMsg = new ADJ_CATGTMsg();
                        ZYPEZDItemValueSetter.setValue(adjCatgTMsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(adjCatgTMsg.adjCatgCd, globalMsg.A.no(i).adjCatgCd_A.getValue());
                        adjCatgTMsg = (ADJ_CATGTMsg) EZDTBLAccessor.findByKey(adjCatgTMsg);

                        // QC#26471 Start
                        if (adjCatgTMsg != null) {
                            if (ZYPConstant.FLG_ON_Y.equals(adjCatgTMsg.cmntReqFlg.getValue())) {
                                if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).invtyOrdLineCmntTxt_A)) {
                                    bizMsg.setMessageInfo("NWBM0136E", new String[] {adjCatgTMsg.adjCatgDescTxt.getValue(), "Reason Code", "Comments" });
                                    // 2019/01/08 QC#29763 Add Start
                                    globalMsg.A.no(i).invtyOrdLineCmntTxt_A.setErrorInfo(1, "NWBM0136E", new String[] {adjCatgTMsg.adjCatgDescTxt.getValue(), "Reason Code", "Comments" });
                                    // 2019/01/08 QC#29763 Add End
                                }
                            }

                            if (ADJ_CATG.SPECIAL_ACCOUNT.equals(globalMsg.A.no(i).adjCatgCd_A.getValue())) {
                                ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxScrItem130Txt_A, fMsg.coaAcctCd.getValue());
                                if (!ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxScrItem130Txt_A)) {
                                    bizMsg.setMessageInfo("NLCM0217E", new String[] {adjCatgTMsg.adjCatgDescTxt.getValue() });
                                    // 2019/01/08 QC#29763 Add Start
                                    globalMsg.A.no(i).xxScrItem130Txt_A.setErrorInfo(1, "NLCM0217E", new String[] {adjCatgTMsg.adjCatgDescTxt.getValue() });
                                    // 2019/01/08 QC#29763 Add End
                                } else {
                                    COA_ACCTTMsg tMsg = new COA_ACCTTMsg();

                                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                                    ZYPEZDItemValueSetter.setValue(tMsg.coaAcctCd, globalMsg.A.no(i).xxScrItem130Txt_A.getValue());

                                    COA_ACCTTMsg existTMsg = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(tMsg);

                                    if (existTMsg == null) {
                                        bizMsg.setMessageInfo("NLCM0197E", new String[] {"Account" });
                                        // 2019/01/08 QC#29763 Add Start
                                        globalMsg.A.no(i).xxScrItem130Txt_A.setErrorInfo(1, "NLCM0197E", new String[] {"Account" });
                                        // 2019/01/08 QC#29763 Add End
                                    } else {
                                        if (!NLCL0290CommonLogic.checkAllowAccount(getGlobalCompanyCode(), bizMsg.adjTrxTpCd_H.getValue(), globalMsg.A.no(i).xxScrItem130Txt_A.getValue())) {
                                            bizMsg.setMessageInfo("NLCM0214E");
                                            // 2019/01/08 QC#29763 Add Start
                                            globalMsg.A.no(i).xxScrItem130Txt_A.setErrorInfo(1, "NLCM0214E");
                                            // 2019/01/08 QC#29763 Add End
                                        }
                                    }
                                }
                            } else {
                                if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxScrItem130Txt_A)) {
                                    bizMsg.setMessageInfo("NLCM0218E", new String[] {adjCatgTMsg.adjCatgDescTxt.getValue() });
                                    // 2019/01/08 QC#29763 Add Start
                                    globalMsg.A.no(i).xxScrItem130Txt_A.setErrorInfo(1, "NLCM0218E", new String[] {adjCatgTMsg.adjCatgDescTxt.getValue() });
                                    // 2019/01/08 QC#29763 Add End
                                } else {
                                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).xxScrItem130Txt_A, adjCatgTMsg.coaAcctCd);
                                }
                            }
                        }
                    }
                    // QC#26471 End

                    // QC:18490 Start
                    // check Config
                    String mdseCd = globalMsg.A.no(i).mdseCd_A.getValue();
                    String serNum = globalMsg.A.no(i).serNum_A.getValue();

                    if (ZYPCommonFunc.hasValue(fMsg.svcConfigMstrPk)) {
                        bizMsg.setMessageInfo("NLCM0215E", new String[] {serNum, mdseCd });
                        // 2019/01/08 QC#29763 Add Start
                        globalMsg.A.no(i).svcConfigMstrPk_A.setErrorInfo(1, "NLCM0215E", new String[] {serNum, mdseCd });
                        // 2019/01/08 QC#29763 Add End
                    }

                    // START 2019/06/03 M.Naito [QC#50408,MOD]
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).serNum_A) && BigDecimal.ZERO.compareTo(globalMsg.A.no(i).ordQty_A.getValue()) < 0) {
                        if (!NLCL0290CommonLogic.checkConfig(getGlobalCompanyCode(), mdseCd, serNum)) {
                            bizMsg.setMessageInfo("NLCM0215E", new String[] {serNum, mdseCd });
                            // 2019/01/08 QC#29763 Add Start
                            globalMsg.A.no(i).serNum_A.setErrorInfo(1, "NLCM0215E", new String[] {serNum, mdseCd });
                            // 2019/01/08 QC#29763 Add End
                        }
                    }
                    // END 2019/06/03 M.Naito [QC#50408,MOD]

                    // QC:18490 End

//                    String coaEnblFlg = ZYPConstant.FLG_OFF_N;
//                    if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaCmpyDplyFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCmpyCd_AG, tMsg.coaCmpyCd.getValue());
//                    } else {
//                        coaEnblFlg = ZYPConstant.FLG_ON_Y;
//                    }
//                    if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaAfflDplyFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAfflCd_AG, tMsg.coaAfflCd.getValue());
//                    } else {
//                        coaEnblFlg = ZYPConstant.FLG_ON_Y;
//                    }
//                    if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaBrDplyFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaBrCd_AG, tMsg.coaBrCd.getValue());
//                    } else {
//                        coaEnblFlg = ZYPConstant.FLG_ON_Y;
//                    }
//                    if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaCcDplyFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCcCd_AG, tMsg.coaCcCd.getValue());
//                    } else {
//                        coaEnblFlg = ZYPConstant.FLG_ON_Y;
//                    }
//                    if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaAcctDplyFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAcctCd_AG, tMsg.coaAcctCd.getValue());
//                    } else {
//                        coaEnblFlg = ZYPConstant.FLG_ON_Y;
//                    }
//                    if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaProdDplyFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProdCd_AG, tMsg.coaProdCd.getValue());
//                    } else {
//                        coaEnblFlg = ZYPConstant.FLG_ON_Y;
//                    }
//                    if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaChDplyFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaChCd_AG, tMsg.coaChCd.getValue());
//                    } else {
//                        coaEnblFlg = ZYPConstant.FLG_ON_Y;
//                    }
//                    if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaProjDplyFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProjCd_AG, tMsg.coaProjCd.getValue());
//                    } else {
//                        coaEnblFlg = ZYPConstant.FLG_ON_Y;
//                    }
//                    if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaExtnDplyFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaExtnCd_AG, tMsg.coaExtnCd.getValue());
//                    } else {
//                        coaEnblFlg = ZYPConstant.FLG_ON_Y;
//                    }
//                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaEnblFlg_A, coaEnblFlg);
//
//                    if (ZYPCommonFunc.hasValue(fMsg.xxScrItem130Txt_D1)) {
//                        String xxScrItem130Txt = fMsg.xxScrItem130Txt_D1.getValue();
//                        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, getGlobalCompanyCode());
//
//                        String[] list = xxScrItem130Txt.split(Pattern.quote(delimiter), -1);
//                        List<String> tokenList = new ArrayList<String>();
//                        for (String val : list) {
//                            tokenList.add(val);
//                        }
//
//                        if (tokenList.size() > SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCmpyCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
//                        }
//                        if (tokenList.size() > SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAfflCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
//                        }
//                        if (tokenList.size() > SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaBrCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
//                        }
//                        if (tokenList.size() > SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaCcCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
//                        }
//                        if (tokenList.size() > SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaAcctCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
//                        }
//                        if (tokenList.size() > SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProdCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
//                        }
//                        if (tokenList.size() > SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaChCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
//                        }
//                        if (tokenList.size() > SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaProjCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
//                        }
//                        if (tokenList.size() > SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD) {
//                            ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).coaExtnCd_A, tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
//                        }
//                    }
                    // QC:18472 End
                    i++;
                    globalMsg.A.setValidCount(i);
                }
            }
        } finally {
            mappedFile.close();
            bizMsg.xxFileData_UP.deleteTempFile();
        }

        if (globalMsg.A.getValidCount() > 0) {
            bizMsg.xxPageShowFromNum_A.setValue(1);
            //QC#18472/18490
            NLCL0290CommonLogic.dispPage(bizMsg, bizMsg.A, globalMsg.A);
//            if (adjAcctAliasFlg) {
//                doProcess_NLCL0290Scrn00_OnChange_AdjAcctAlias(bizMsg, globalMsg);
//            } else {
//                NLCL0290CommonLogic.dispPage(bizMsg, bizMsg.A, globalMsg.A);
//            }
            bizMsg.setMessageInfo("NPAM0005I");
        }

    }

    /**
     * <pre>
      * validateAndCopyToSMsg_UPLOAD
      * </pre>
     * @param int status
     * @param int count
     * @param NLCL0290CMsg cMsg
     * @param NLCL0290_ASMsgArray aSMsg
     * @param NLCL0290F00FMsg FileMsg
     */
    private boolean validateUploadFile(int status, int count, NLCL0290CMsg bizMsg, NLCL0290SMsg globalMsg, NLCL0290F00FMsg fMsg) {
        if (count > globalMsg.A.length()) {
            bizMsg.setMessageInfo("NPAM1199E");
            return false;
        }
        if (status == 1000) {
            bizMsg.setMessageInfo("NMAM0052E", new String[] {"CSV" });
            return false;
        }
        return true;
    }

    /**
     * <pre>
      * copySMsgAToSMsgB
      * </pre>
     * @param fMsg NLCL2090F00FMsg
     * @param cMsg NLCL2090CMsg
     * @return boolean
     */
    private static boolean checkMandatoryCheck(NLCL0290F00FMsg fMsg, NLCL0290CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(fMsg.adjTrxTpDescTxt.getValue())) {
            bizMsg.setMessageInfo("ZZZM9025E", new String[] {"Adjustment Transaction Type" });
            return false;
        }
        if (!ZYPCommonFunc.hasValue(fMsg.invtyLocCd.getValue())) {
            bizMsg.setMessageInfo("ZZZM9025E", new String[] {"Warehouse" });
            return false;
        }
        return true;
    }

    /**
     * Get Default Display COA Information
     * @param bizAppId String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    private DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(String appFuncId) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        tMsg.appFuncId.setValue(appFuncId);

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    // QC:18472
    // QC#11785 Add
    /**
     * <pre>
       * doProcess_NLCL0290Scrn00_OpenWin_Account_Header
       * </pre>
     * @param cMsg
     * @param sMsg
     */
//    private boolean doProcess_NLCL0290Scrn00_OpenWin_Account_Header(EZDCMsg cMsg, EZDSMsg sMsg, String glblCmpyCd) {
//
//        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
//        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;
//
//        if (!ZYPCommonFunc.hasValue(bizMsg.xxScrItem130Txt_H)) {
//            return true;
//        }
//
//        return NLCL0290CommonLogic.validateSegmentStringForHeader(glblCmpyCd, bizMsg, globalMsg, bizMsg.xxScrItem130Txt_H.getValue(), true);
//    }

    /**
     * <pre>
       * doProcess_NLCL0290Scrn00_OpenWin_Account_Detail
       * </pre>
     * @param cMsg
     * @param sMsg
     */
    private boolean doProcess_NLCL0290Scrn00_OpenWin_Account_Detail(EZDCMsg cMsg, EZDSMsg sMsg, String glblCmpyCd) {

        NLCL0290CMsg bizMsg = (NLCL0290CMsg) cMsg;
        NLCL0290SMsg globalMsg = (NLCL0290SMsg) sMsg;

        int index = bizMsg.xxNum.getValueInt();

        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(index).xxScrItem130Txt_A)) {
            return true;
        }

        return NLCL0290CommonLogic.validateSegmentStringForDetail(glblCmpyCd, bizMsg, globalMsg, false, true);
    }
}
