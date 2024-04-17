/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1830;

import static business.blap.NWAL1830.constant.NWAL1830Constant.BIZ_ID;
import static business.blap.NWAL1830.constant.NWAL1830Constant.DATE_TIME_PATTERN;
import static business.blap.NWAL1830.constant.NWAL1830Constant.INITIAL_SUPPLIES;
import static business.blap.NWAL1830.constant.NWAL1830Constant.LOAN_ORD_ACTION_LOAN_SELL;
import static business.blap.NWAL1830.constant.NWAL1830Constant.MERCHANDISE;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NMAM8543E;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NMAM8544E;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NWAL1830_LOAN_RTRN_RSN_CD;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NWAM0684E;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NWAM0948E;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NWAM0982E;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NWZM1507E;
import static business.blap.NWAL1830.constant.NWAL1830Constant.NZZM0003E;
import static business.blap.NWAL1830.constant.NWAL1830Constant.ORD_LINE_STS_NM_CLOSED;
import static business.blap.NWAL1830.constant.NWAL1830Constant.RADIO_VAL_NEW_ORD;
import static business.blap.NWAL1830.constant.NWAL1830Constant.SVC_ISTL_RULE_NON_RTL_EQIP_ORD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1830.common.NWAL1830CommonLogic;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_ORD_LINE_CATGTMsg;
import business.db.MSG_TXT_DTLTMsg;
import business.db.MSG_TXT_DTLTMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.SPEC_COND_PRCTMsg;
import business.db.SPEC_COND_PRCTMsgArray;
import business.parts.NLZC215001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_cpoIstlInfoListPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnPriceListPMsg;
import business.parts.NWZC150001_siteSrvInfoListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsgArray;

import com.canon.cusa.s21.api.NLZ.NLZC215001.NLZC215001;
import com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1830BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/30   Fujitsu         Y.Taoka         Create          N/A
 * 2016/06/09   Fujitsu         Y.Taoka         Update          QC#9276
 * 2016/09/29   Fujitsu         S.Takami        Update          S21_NA#14887
 * 2016/10/14   Fujitsu         S.Iidaka        Update          S21_NA#14727
 * 2016/12/08   Fujitsu         S.Ohki          Update          S21_NA#15934
 * 2017/01/13   Fujitsu         W.Honda         Update          S21_NA#17076
 * 2017/08/30   Fujitsu         S.Ohki          Update          S21_NA#20763
 * 2017/09/11   Fujitsu         M.Ohno          Update          S21_NA#19800(L3)
 * 2017/09/21   Fujitsu         M.Ohno          Update          S21_NA#21308
 * 2017/10/16   Fujitsu         R.Nakamura      Update          S21_NA#21507
 * 2017/11/02   Fujitsu         M.Ohno          Update          S21_NA#22248
 * 2017/11/06   Fujitsu         S.Takami        Update          S21_NA#22157
 * 2017/11/20   Fujitsu         Y.Kanefusa      Update          S21_NA#22631
 * 2017/11/22   Fujitsu         K.Ishizuka      Update          S21_NA#22693
 * 2017/11/24   Fujitsu         A.Kosai         Update          S21_NA#21508
 * 2017/12/01   Fujitsu         M.Yamada        Update          S21_NA#22641
 * 2017/12/27   Fujitsu         K.Ishizuka      Update          S21_NA#22991
 * 2017/12/27   Fujitsu         K.Ishizuka      Update          S21_NA#23204
 * 2017/12/27   Fujitsu         A.Kosai         Update          S21_NA#22804
 * 2018/01/12   Fujitsu         K.Ishizuka      Update          S21_NA#22372
 * 2018/01/19   Fujitsu         M.Ohno          Update          S21_NA#23338
 * 2018/07/26   Fujitsu         M.Yamada        Update          S21_NA#27004
 * 2018/07/30   Fujitsu         M.Ohno          Update          S21_NA#26330
 * 2018/08/21   Fujitsu         Hd.Sugawara     Update          S21_NA#27489
 * 2019/05/28   Fujitsu         Y.Kanefusa      Update          S21_NA#50548
 * 2019/05/31   Fujitsu         Y.Kanefusa      Update          S21_NA#50556
 * 2019/08/03   Fujitsu         S.Kosaka        Update          S21_NA#52338
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2019/12/12   Fujitsu         Mz.Takahashi    Update          QC#54747
 * 2020/04/13   Fujitsu         C.Hara          Update          QC#56374
 * 2020/04/14   Fujitsu         Y.Kanefusa      Update          S21_NA#56558
 * 2020/05/14   Fujitsu         C.Hara          Update          QC#56509-1
 * 2021/09/22   CITS            K.Ogino         Update          QC#59222
 * 2022/02/04   CITS            K.Watanabe      Update          QC#59647
 * 2024/03/14   CITS            J.Cho           Update          QC#63527
 *</pre>
 */
public class NWAL1830BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1830CMsg bizMsg = (NWAL1830CMsg) cMsg;

            if ("NWAL1830Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL1830Scrn00_CMN_Save(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1830Scrn00_CMN_Save(NWAL1830CMsg bizMsg) {
        // 2017/09/11 S21_NA#19800 add start
        DS_ORD_LINE_CATGTMsg tMsg = new DS_ORD_LINE_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd, DS_ORD_LINE_CATG.LOAN_ORDERS);
        tMsg = (DS_ORD_LINE_CATGTMsg) S21FastTBLAccessor.findByKey(tMsg);

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())//
                    && !tMsg.dsOrdLineCatgDescTxt.getValue().equals(bizLineMsg.dsOrdLineCatgDescTxt_B1.getValue()) //
                    && ZYPConstant.FLG_OFF_N.equals(bizLineMsg.instlBaseCtrlFlg_B1.getValue()) // 2020/04/13 QC#56374 Add
                    && DS_ORD_LINE_DRCTN.INBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                ZYPEZDItemValueSetter.setValue(bizLineMsg.xxChkBox_B1, ZYPConstant.FLG_OFF_N);
            }
        }
        // 2017/09/11 S21_NA#19800 add start

        /**
         * Validation
         */
        if (!checkAction(bizMsg)) {
            return;
        }
        if (!checkMster(bizMsg)) {
            return;
        }
        if (!checkTimeStamp(bizMsg)) {
            return;
        }
        // QC#63527 2024/03/14 Start
        if (!checkInvtyOwnr(bizMsg)) {
            return;
        }
        // QC#63527 2024/03/14 End

        /**
         * Get CPO/CPO_DTL/Price Calc Base for CPO Update API
         */
        boolean existLineActionLoanReturn = NWAL1830CommonLogic.existLineActionLoanReturn(bizMsg);
        boolean existLineActionLoanSell = NWAL1830CommonLogic.existLineActionLoanSell(bizMsg);

        // Get Order Header
        Map<String, Object> origOrdHdr = new HashMap<String, Object>();
        Map<String, Object> existOrdHdr = new HashMap<String, Object>();
        if (existLineActionLoanReturn //
                || (existLineActionLoanSell && RADIO_VAL_NEW_ORD.equals(bizMsg.xxRadioBtn_OL.getValue()))) {
            // Get Original Order Header
            getOrigOrdHdr(bizMsg, origOrdHdr);
            if (origOrdHdr.isEmpty()) {
                return; // Error
            }
        }
        if (existLineActionLoanSell && !RADIO_VAL_NEW_ORD.equals(bizMsg.xxRadioBtn_OL.getValue())) {
            // Get Exist Order Header
            getExistOrdHdr(bizMsg, existOrdHdr);
            if (existOrdHdr.isEmpty()) {
                return; // Error
            }
        }

        // Get Order Config and Order Line
        //// For New Order
        List<Map<String, Object>> newConfigList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> newLineList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> newPriceList = new ArrayList<Map<String, Object>>();
        // 2017/11/02 QC#22248 add start
        List<Map<String, Object>> origSlsCrList = new ArrayList<Map<String, Object>>();
        // 2017/11/02 QC#22248 add end
        //// For Existing Order
        List<Map<String, Object>> existConfigList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> existLineList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> existPriceList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> existSlsCrList = new ArrayList<Map<String, Object>>();

        List<Map<String, Object>> origiConfigList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> origiLineList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> origiPriceList = new ArrayList<Map<String, Object>>();
        //// For Return
        List<Map<String, Object>> rtrnConfigList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rtrnLineList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rtrnPriceList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rtrnSlsCrList = new ArrayList<Map<String, Object>>();

        // QC#22641 For Return(D.I.S.C.)
        List<Map<String, Object>> rtrnDelyList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rtrnIstlList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rtrnSiteList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> rtrnCtacList = new ArrayList<Map<String, Object>>();

        if (existLineActionLoanSell) {
            if (RADIO_VAL_NEW_ORD.equals(bizMsg.xxRadioBtn_OL.getValue())) {
                // Get Selected Original Order Configs and Lines
                getOrigOrdConfigList(bizMsg, newConfigList, false);
                getOrigOrdLineList(bizMsg, newLineList, false);
                getOrigOrdPriceList(bizMsg, newPriceList, false);
                // 2017/11/02 QC#22248 add start
                getOrigOrdSlsCrList(bizMsg, origSlsCrList);
                // 2017/11/02 QC#22248 add end
                if (newConfigList.size() == 0 || newLineList.size() == 0 || newPriceList.size() == 0) {
                    return; // Error
                }
            } else {
                // Get All Existing Order Configs and Lines

                if (!isHeaderOnly(bizMsg, existConfigList)) {
                    getExistOrdConfigList(bizMsg, existConfigList);
                    getExistOrdLineList(bizMsg, existLineList);
                    getExistOrdPriceList(bizMsg, existPriceList);
                }

                getExistOrdSlsCrList(bizMsg, existSlsCrList);

                getOrigOrdConfigList(bizMsg, origiConfigList, false);
                getOrigOrdLineList(bizMsg, origiLineList, false);
                getOrigOrdPriceList(bizMsg, origiPriceList, false);

                if (origiConfigList.size() == 0 || origiLineList.size() == 0 || origiPriceList.size() == 0) {
                    return; // Error
                }
            }
        }
        if (existLineActionLoanReturn) {
            // Get All Original Order Configs and Lines
            getOrigOrdConfigList(bizMsg, rtrnConfigList, true);
            getOrigOrdLineList(bizMsg, rtrnLineList, true);
            getOrigOrdPriceList(bizMsg, rtrnPriceList, true);
            getOrigOrdSlsCrList(bizMsg, rtrnSlsCrList);
            if (rtrnConfigList.size() == 0 || rtrnLineList.size() == 0 || rtrnPriceList.size() == 0) {
                return; // Error
            }

            // QC#22641
            List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
            getOrigOrdDelyList(bizMsg, rtrnDelyList, selectRows);
            getOrigOrdIstlList(bizMsg, rtrnIstlList, selectRows);
            getOrigOrdSiteList(bizMsg, rtrnSiteList, selectRows);
            getOrigOrdCtacList(bizMsg, rtrnCtacList, selectRows);
        }

        // Get Checked List with set component
        List<NWAL1830_BCMsg> checkLineListSell = new ArrayList<NWAL1830_BCMsg>();
        List<NWAL1830_BCMsg> checkLineListRtrn = new ArrayList<NWAL1830_BCMsg>();

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {

                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                    checkLineListSell.add(bizLineMsg);
                } else {
                    checkLineListRtrn.add(bizLineMsg);
                }

                String lineNum = bizLineMsg.cpoDtlLineNum_B1.getValue();
                // 2017/11/06 S21_NA#22157 Del Start
                //                String setMdseCd = bizLineMsg.mdseCd_B1.getValue();
                // 2017/11/06 S21_NA#22157 Del End
                for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
                    NWAL1830_BCMsg childLineMsg = bizMsg.B.no(j);
                    // Set Component
                    //                    if (lineNum.equals(childLineMsg.cpoDtlLineNum_B1.getValue()) && setMdseCd.equals(childLineMsg.setMdseCd_B1.getValue())) { 2017/11/06 S21_NA#22157 Change Condition
                    if (lineNum.equals(childLineMsg.cpoDtlLineNum_B1.getValue()) && ZYPCommonFunc.hasValue(childLineMsg.setMdseCd_B1)) {

                        if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                            ZYPEZDItemValueSetter.setValue(childLineMsg.dsOrdLineCatgCd_BA, bizLineMsg.dsOrdLineCatgCd_BA);
                            checkLineListSell.add(childLineMsg);
                        } else {
                            // When Loan Return
                            ZYPEZDItemValueSetter.setValue(childLineMsg.dsOrdLineCatgCd_BA, bizLineMsg.dsOrdLineCatgCd_BA);
                            ZYPEZDItemValueSetter.setValue(childLineMsg.rtlWhCd_B1, bizLineMsg.rtlWhCd_B1);
                            ZYPEZDItemValueSetter.setValue(childLineMsg.rtlSwhCd_B1, bizLineMsg.rtlSwhCd_B1);
                            checkLineListRtrn.add(childLineMsg);
                        }
                    }
                }
            }
        }
        /**
         * Call CPO Update API
         */
        // Create CPO Update API Parameter [Header]
        String cpoOrdNum = "";
        if (existLineActionLoanSell) {

            NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();

            if (RADIO_VAL_NEW_ORD.equals(bizMsg.xxRadioBtn_OL.getValue())) {
                // [NEW Order]
                // 2017/11/02 QC#22248 mod start
                //                createCpoUpdApiParamNewOrder(bizMsg, cpoUpdApiReqPMsg, origOrdHdr, newConfigList, newLineList, newPriceList, checkLineListSell);
                createCpoUpdApiParamNewOrder(bizMsg, cpoUpdApiReqPMsg, origOrdHdr, newConfigList, newLineList, newPriceList, checkLineListSell, origSlsCrList);
                // 2017/11/02 QC#22248 mod end

            } else {
                // [Existing Order]
                // Add Existing Line Info
                createCpoUpdApiParamExistOrderAddExistInfo(bizMsg, cpoUpdApiReqPMsg, existConfigList, existLineList, existPriceList, existSlsCrList);

                // Add Conversion Line Info
                createCpoUpdApiParamExistOrder(//
                        bizMsg, cpoUpdApiReqPMsg, existOrdHdr, origiConfigList, origiLineList, origiPriceList, checkLineListSell, existSlsCrList);

            }
            // 2017/11/24 S21_NA#21508 Mod Start
            //cpoOrdNum = callDsCpoUpdApi(bizMsg, cpoUpdApiReqPMsg);
            cpoOrdNum = callDsCpoUpdApi(bizMsg, cpoUpdApiReqPMsg, checkLineListSell);
            // 2017/11/24 S21_NA#21508 Mod End
            if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
                // Error
                return;
            }

        }
        if (existLineActionLoanReturn) {
            NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();
            // 2017/11/02 QC#22248 mod start
            //            createCpoUpdApiParamReturn(bizMsg, cpoUpdApiReqPMsg, origOrdHdr, rtrnConfigList, rtrnLineList, rtrnPriceList, checkLineListRtrn);
            createCpoUpdApiParamReturn(bizMsg, cpoUpdApiReqPMsg, origOrdHdr, rtrnConfigList, rtrnLineList, rtrnPriceList, checkLineListRtrn, rtrnSlsCrList //
                    , rtrnDelyList, rtrnIstlList, rtrnSiteList, rtrnCtacList); // QC#22641
            // 2017/11/02 QC#22248 mod end
            // Add Header Sales Credit
            createCpoUpdApiParamSlsCrHdrForRtrn(bizMsg, cpoUpdApiReqPMsg, rtrnSlsCrList);

            // 2017/11/24 S21_NA#21508 Mod Start
            //cpoOrdNum = callDsCpoUpdApi(bizMsg, cpoUpdApiReqPMsg);
            cpoOrdNum = callDsCpoUpdApi(bizMsg, cpoUpdApiReqPMsg, checkLineListRtrn);
            // 2017/11/24 S21_NA#21508 Mod End
            if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
                // Error
                return;
            }
        }
    }

    // QC#22641
    private void createCpoUpdApiParamDISCForRtrn(//
            NWAL1830CMsg bizMsg //
            , NWZC150001PMsg cpoUpdApiReqPMsg //
            , List<Map<String, Object>> rtrnDelyList //
            , List<Map<String, Object>> rtrnIstlList //
            , List<Map<String, Object>> rtrnSiteList //
            , List<Map<String, Object>> rtrnCtacList //
            , String dsOrdPosnNum //
            , BigDecimal origDsCpoConfigPk) {
        createCpoUpdApiParamDelyForRtrn(bizMsg, cpoUpdApiReqPMsg, rtrnDelyList, dsOrdPosnNum, origDsCpoConfigPk);
        createCpoUpdApiParamIstlForRtrn(bizMsg, cpoUpdApiReqPMsg, rtrnIstlList, dsOrdPosnNum, origDsCpoConfigPk);
        createCpoUpdApiParamSiteForRtrn(bizMsg, cpoUpdApiReqPMsg, rtrnSiteList, dsOrdPosnNum, origDsCpoConfigPk);
        createCpoUpdApiParamCtacForRtrn(bizMsg, cpoUpdApiReqPMsg, rtrnCtacList, dsOrdPosnNum, origDsCpoConfigPk);
    }

    // QC#22641
    private void createCpoUpdApiParamCtacForRtrn(//
            NWAL1830CMsg bizMsg, NWZC150001PMsg cpoUpdApiReqPMsg, List<Map<String, Object>> rtrnCtacList, String dsOrdPosnNum, BigDecimal origDsCpoConfigPk) {
        int i = cpoUpdApiReqPMsg.cpoCtacPsnInfoList.getValidCount();
        for (Map<String, Object> map : rtrnCtacList) {
            //NWZC150001_cpoCtacPsnInfoListPMsg pMsg = cpoUpdApiReqPMsg.cpoCtacPsnInfoList.no(i++);// 2017/12/27 S21_NA#22991 K.Ishizuka MOD
            BigDecimal dsCpoConfigPk = (BigDecimal) map.get("DS_CPO_CONFIG_PK");
            if (!ZYPCommonFunc.hasValue(dsCpoConfigPk) //
                    || !ZYPCommonFunc.hasValue(origDsCpoConfigPk) //
                    || dsCpoConfigPk.compareTo(origDsCpoConfigPk) != 0) {
                continue;
            }
            NWZC150001_cpoCtacPsnInfoListPMsg pMsg = cpoUpdApiReqPMsg.cpoCtacPsnInfoList.no(i++);// 2017/12/27 S21_NA#22991 K.Ishizuka MOD
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CTAC_PSN_NEW);

            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdPosnNum, dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(pMsg.ctacTpCd, (String) map.get("CTAC_PSN_TP_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.firstNm, (String) map.get("CTAC_PSN_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.lastNm, (String) map.get("CTAC_PSN_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.emlAddr, (String) map.get("CTAC_PSN_EML_ADDR"));
            ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnExtnNum, (String) map.get("CTAC_PSN_EXTN_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.telNum, (String) map.get("CTAC_PSN_TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.faxNum, (String) map.get("CTAC_PSN_FAX_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.sortNum, (BigDecimal) map.get("CTAC_PSN_SORT_NUM"));

            ZYPEZDItemValueSetter.setValue(pMsg.configCatgCd, CONFIG_CATG.INBOUND);
            ZYPEZDItemValueSetter.setValue(pMsg.ctacCustRefTpCd, (String) map.get("CTAC_CUST_REF_TP_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnPk, (BigDecimal) map.get("CTAC_PSN_PK"));
        }
        cpoUpdApiReqPMsg.cpoCtacPsnInfoList.setValidCount(i);
    }

    // QC#22641
    private void createCpoUpdApiParamSiteForRtrn(//
            NWAL1830CMsg bizMsg, NWZC150001PMsg cpoUpdApiReqPMsg, List<Map<String, Object>> rtrnSiteList, String dsOrdPosnNum, BigDecimal origDsCpoConfigPk) {
        int i = cpoUpdApiReqPMsg.siteSrvInfoList.getValidCount();
        for (Map<String, Object> map : rtrnSiteList) {
            //NWZC150001_siteSrvInfoListPMsg pMsg = cpoUpdApiReqPMsg.siteSrvInfoList.no(i++);// 2017/12/27 S21_NA#22991 K.Ishizuka MOD
            BigDecimal dsCpoConfigPk = (BigDecimal) map.get("DS_CPO_CONFIG_PK");
            if (!ZYPCommonFunc.hasValue(dsCpoConfigPk) //
                    || !ZYPCommonFunc.hasValue(origDsCpoConfigPk) //
                    || dsCpoConfigPk.compareTo(origDsCpoConfigPk) != 0) {
                continue;
            }
            NWZC150001_siteSrvInfoListPMsg pMsg = cpoUpdApiReqPMsg.siteSrvInfoList.no(i++);// 2017/12/27 S21_NA#22991 K.Ishizuka MOD
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_SITE_SRVY_NEW);

            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdPosnNum, dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(pMsg.cmpyInfoAptBldgNm, (String) map.get("CMPY_INFO_APT_BLDG_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.cmpyInfoFlNm, (String) map.get("CMPY_INFO_FL_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.cmpyInfoDeptNm, (String) map.get("CMPY_INFO_DEPT_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.otsdStepNum, (String) map.get("OTSD_STEP_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.insdStepNum, (String) map.get("INSD_STEP_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.stairCrawReqFlg, (String) map.get("STAIR_CRAW_REQ_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.flgtStairNum, (String) map.get("FLGT_STAIR_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevAvalFlg, (String) map.get("ELEV_AVAL_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevAvalFromHourMn, (String) map.get("ELEV_AVAL_FROM_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevAvalToHourMn, (String) map.get("ELEV_AVAL_TO_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevApptReqFlg, (String) map.get("ELEV_APPT_REQ_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevCtacTelNum, (String) map.get("ELEV_CTAC_TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevProtReqFlg, (String) map.get("ELEV_PROT_REQ_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevWdt, (BigDecimal) map.get("ELEV_WDT"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevDepthNum, (BigDecimal) map.get("ELEV_DEPTH_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevCtacPsnNm, (String) map.get("ELEV_CTAC_PSN_NM"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevCapWt, (BigDecimal) map.get("ELEV_CAP_WT"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevDoorHgt, (BigDecimal) map.get("ELEV_DOOR_HGT"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevDoorWdt, (BigDecimal) map.get("ELEV_DOOR_WDT"));
            ZYPEZDItemValueSetter.setValue(pMsg.stairAndLdgWdt, (BigDecimal) map.get("STAIR_AND_LDG_WDT"));
            ZYPEZDItemValueSetter.setValue(pMsg.crdrWdt, (BigDecimal) map.get("CRDR_WDT"));
            ZYPEZDItemValueSetter.setValue(pMsg.doorWdt, (BigDecimal) map.get("DOOR_WDT"));
            ZYPEZDItemValueSetter.setValue(pMsg.loadDockAvalFlg, (String) map.get("LOAD_DOCK_AVAL_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.loadDockHgt, (BigDecimal) map.get("LOAD_DOCK_HGT"));
            ZYPEZDItemValueSetter.setValue(pMsg.trctrAndTrailAccsFlg, (String) map.get("TRCTR_AND_TRAIL_ACCS_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.bldgEntDoorHgt, (BigDecimal) map.get("BLDG_ENT_DOOR_HGT"));
            ZYPEZDItemValueSetter.setValue(pMsg.bldgEntDoorWdt, (BigDecimal) map.get("BLDG_ENT_DOOR_WDT"));
            ZYPEZDItemValueSetter.setValue(pMsg.loadDockAvalFromHourMn, (String) map.get("LOAD_DOCK_AVAL_FROM_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(pMsg.loadDockAvalToHourMn, (String) map.get("LOAD_DOCK_AVAL_TO_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(pMsg.carrDelyTmHourMn, (String) map.get("CARR_DELY_TM_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(pMsg.delyTrnspOptCd, (String) map.get("DELY_TRNSP_OPT_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.siteSrvyAddlCmntTxt, (String) map.get("SITE_SRVY_ADDL_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(pMsg.configCatgCd, CONFIG_CATG.INBOUND);
        }
        cpoUpdApiReqPMsg.siteSrvInfoList.setValidCount(i);
    }

    // QC#22641
    private void createCpoUpdApiParamIstlForRtrn(NWAL1830CMsg bizMsg, NWZC150001PMsg cpoUpdApiReqPMsg, List<Map<String, Object>> rtrnIstlList, String dsOrdPosnNum, BigDecimal origDsCpoConfigPk) {
        int i = cpoUpdApiReqPMsg.cpoIstlInfoList.getValidCount();
        for (Map<String, Object> map : rtrnIstlList) {
            //NWZC150001_cpoIstlInfoListPMsg pMsg = cpoUpdApiReqPMsg.cpoIstlInfoList.no(i++); // 2017/12/27 S21_NA#22991 K.Ishizuka MOD
            BigDecimal dsCpoConfigPk = (BigDecimal) map.get("DS_CPO_CONFIG_PK");
            if (!ZYPCommonFunc.hasValue(dsCpoConfigPk) //
                    || !ZYPCommonFunc.hasValue(origDsCpoConfigPk) //
                    || dsCpoConfigPk.compareTo(origDsCpoConfigPk) != 0) {
                continue;
            }
            NWZC150001_cpoIstlInfoListPMsg pMsg = cpoUpdApiReqPMsg.cpoIstlInfoList.no(i++); // 2017/12/27 S21_NA#22991 K.Ishizuka MOD
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_INSTL_INFO_NEW);
//            ZYPEZDItemValueSetter.setValue(pMsg.svcIstlRuleNum, getDefaultSvcIstlRuleNum(bizMsg, pMsg.dsOrdPosnNum.getValue()));
            ZYPEZDItemValueSetter.setValue(pMsg.svcIstlRuleNum, getDefaultSvcIstlRuleNum(bizMsg, pMsg.dsOrdPosnNum.getValue(), origDsCpoConfigPk));
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdPosnNum, dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(pMsg.istlDivCd, (String) map.get("ISTL_DIV_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.configCatgCd, CONFIG_CATG.INBOUND);
        }
        cpoUpdApiReqPMsg.cpoIstlInfoList.setValidCount(i);
    }

    // QC#22641
    // 2018/01/19 QC#23338 mod start
//    private String getDefaultSvcIstlRuleNum(NWAL1830CMsg bizMsg, String dsOrdPosnNum) {
    private String getDefaultSvcIstlRuleNum(NWAL1830CMsg bizMsg, String dsOrdPosnNum, BigDecimal origDsCpoConfigPk) {
//        S21SsmEZDResult defultInstType = NWAL1830Query.getInstance().getDefaultInstTypeWithConfigNum(bizMsg, dsOrdPosnNum);
        S21SsmEZDResult defultInstType = NWAL1830Query.getInstance().getDefaultInstTypeWithConfigNum(bizMsg, dsOrdPosnNum, origDsCpoConfigPk);
    // 2018/01/19 QC#23338 mod end
        if (defultInstType.isCodeNotFound()) {
            return ZYPCodeDataUtil.getVarCharConstValue(SVC_ISTL_RULE_NON_RTL_EQIP_ORD, bizMsg.glblCmpyCd.getValue()); // SVC_ISTL_RULE_NUM_NO_INSTALL;
        }

        Map<String, String> defaultInstTypeMap = (Map<String, String>) defultInstType.getResultObject();
        return (String) defaultInstTypeMap.get("SVC_DEINS_RULE_NUM");
    }

    // QC#22641
    private void createCpoUpdApiParamDelyForRtrn(//
            NWAL1830CMsg bizMsg, NWZC150001PMsg cpoUpdApiReqPMsg, List<Map<String, Object>> rtrnDelyList, String dsOrdPosnNum, BigDecimal origDsCpoConfigPk) {
        int i = cpoUpdApiReqPMsg.cpoDlvyInfoList.getValidCount();
        for (Map<String, Object> map : rtrnDelyList) {
            //NWZC150001_cpoDlvyInfoListPMsg pMsg = cpoUpdApiReqPMsg.cpoDlvyInfoList.no(i++); // 2017/12/27 S21_NA#22991 K.Ishizuka MOD
            BigDecimal dsCpoConfigPk = (BigDecimal) map.get("DS_CPO_CONFIG_PK");
            if (!ZYPCommonFunc.hasValue(dsCpoConfigPk) //
                    || !ZYPCommonFunc.hasValue(origDsCpoConfigPk) //
                    || dsCpoConfigPk.compareTo(origDsCpoConfigPk) != 0) {
                continue;
            }
            NWZC150001_cpoDlvyInfoListPMsg pMsg = cpoUpdApiReqPMsg.cpoDlvyInfoList.no(i++); // 2017/12/27 S21_NA#22991 K.Ishizuka MOD
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);

            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdPosnNum, dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(pMsg.dsDelyTpCd, (String) map.get("DS_DELY_TP_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.rddDt, (String) map.get("RDD_DT"));
            ZYPEZDItemValueSetter.setValue(pMsg.opsFromHourMn, (String) map.get("OPS_FROM_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(pMsg.opsToHourMn, (String) map.get("OPS_TO_HOUR_MN"));
            ZYPEZDItemValueSetter.setValue(pMsg.loadDockAvalFlg, (String) map.get("LOAD_DOCK_AVAL_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.stairCrawReqFlg, (String) map.get("STAIR_CRAW_REQ_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.stairCrawNum, (String) map.get("STAIR_CRAW_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.elevAvalFlg, (String) map.get("ELEV_AVAL_FLG"));
            ZYPEZDItemValueSetter.setValue(pMsg.delyAddlCmntTxt, (String) map.get("DELY_ADDL_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(pMsg.configCatgCd, CONFIG_CATG.INBOUND);
        }
        cpoUpdApiReqPMsg.cpoDlvyInfoList.setValidCount(i);
    }

    // QC#22641
    private void getOrigOrdCtacList(NWAL1830CMsg bizMsg, List<Map<String, Object>> rtrnCtacList, List<Integer> selectRows) {

        List<BigDecimal> dsCpoConfigPkList = new ArrayList<BigDecimal>();
        for (int ix : selectRows) {
            dsCpoConfigPkList.add(bizMsg.A.no(ix).dsCpoConfigPk_A1.getValue());
        }

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdCtac(bizMsg, bizMsg.cpoOrdNum_OH.getValue(), dsCpoConfigPkList);
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        rtrnCtacList.addAll(resultMapList);
    }

    // QC#22641
    private void getOrigOrdSiteList(NWAL1830CMsg bizMsg, List<Map<String, Object>> rtrnSiteList, List<Integer> selectRows) {

        List<BigDecimal> dsCpoConfigPkList = new ArrayList<BigDecimal>();
        for (int ix : selectRows) {
            dsCpoConfigPkList.add(bizMsg.A.no(ix).dsCpoConfigPk_A1.getValue());
        }

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdSite(bizMsg, bizMsg.cpoOrdNum_OH.getValue(), dsCpoConfigPkList);
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        rtrnSiteList.addAll(resultMapList);
    }

    // QC#22641
    private void getOrigOrdIstlList(NWAL1830CMsg bizMsg, List<Map<String, Object>> rtrnIstlList, List<Integer> selectRows) {

        List<BigDecimal> dsCpoConfigPkList = new ArrayList<BigDecimal>();
        for (int ix : selectRows) {
            dsCpoConfigPkList.add(bizMsg.A.no(ix).dsCpoConfigPk_A1.getValue());
        }

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdIstl(bizMsg, bizMsg.cpoOrdNum_OH.getValue(), dsCpoConfigPkList);
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        rtrnIstlList.addAll(resultMapList);
    }

    // QC#22641
    private void getOrigOrdDelyList(NWAL1830CMsg bizMsg, List<Map<String, Object>> rtrnDelyList, List<Integer> selectRows) {

        List<BigDecimal> dsCpoConfigPkList = new ArrayList<BigDecimal>();
        for (int ix : selectRows) {
            dsCpoConfigPkList.add(bizMsg.A.no(ix).dsCpoConfigPk_A1.getValue());
        }

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdDely(bizMsg, bizMsg.cpoOrdNum_OH.getValue(), dsCpoConfigPkList);
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        rtrnDelyList.addAll(resultMapList);
    }

    /**
     * Check Action
     * @param bizMsg NWAL1830CMsg
     * @return boolean
     */
    private boolean checkAction(NWAL1830CMsg bizMsg) {
        boolean rc = true;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(bizLineMsg.xxDplyCtrlFlg_B1.getValue())) {
                continue;
            }
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(bizLineMsg.baseCmptFlg_B1.getValue()) && DS_ORD_LINE_DRCTN.INBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                    // To Return in Main Machine
                    if (!isAllSameActionInConfig(bizMsg, bizLineMsg.dsOrdPosnNum_B1.getValue(), DS_ORD_LINE_DRCTN.INBOUND)) {
                        //When There are To-Sell Action in the config.--> Error
                        bizMsg.setMessageInfo(NMAM8543E); // 2020/05/14 QC#56509-1 Add
                        rc = false;
                    }
                    // QC#59222. Machine Master Check
                    if (rc == true && !isAllAccRtrnSmm(bizMsg, bizLineMsg.dsOrdPosnNum_B1.getValue())) {
                        rc = false;
                    }
                } else {
                    if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                        // 2020/05/14 QC#56509-1 Add Start
                        if (ZYPConstant.FLG_ON_Y.equals(bizLineMsg.baseCmptFlg_B1.getValue())) {
                            if (!isAllSameActionInConfig(bizMsg, bizLineMsg.dsOrdPosnNum_B1.getValue(), DS_ORD_LINE_DRCTN.OUTBOUND)) {
                                //When There are To-Sell Action in the config.--> Error
                                bizMsg.setMessageInfo(NWAM0982E);
                                rc = false;
                            }
                        }
                        // 2020/05/14 QC#56509-1 Add End
                        NWAL1830_BCMsg mainMach = getMainMachAction(bizMsg, bizLineMsg.dsOrdPosnNum_B1.getValue());
                        if (mainMach != null && ZYPConstant.FLG_ON_Y.equals(mainMach.xxDplyCtrlFlg_B1.getValue())) {
                            if (!ZYPConstant.CHKBOX_ON_Y.equals(mainMach.xxChkBox_B1.getValue()) || !DS_ORD_LINE_DRCTN.OUTBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, mainMach))) { // 2017/09/21 S21_NA#21308 mod
                                // Need to conversion with main machine
                                mainMach.dsOrdLineCatgCd_BA.setErrorInfo(1, NMAM8544E);
                                rc = false;
                            }
                        }
                    }
                }
            }
        }
        return rc;
    }

    // QC#63527 2024/03/14 Start
    /**
     * check Invty Ownr
     * @param bizMsg NWAL1830CMsg
     * @return boolean
     */
    private boolean checkInvtyOwnr(NWAL1830CMsg bizMsg) {
        boolean rc = true;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {
                if ((DS_ORD_LINE_DRCTN.INBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg)))) {
                    S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getCpoDetailInvLocCd(bizMsg.glblCmpyCd.getValue(), bizLineMsg.cpoOrdNum_B1.getValue()
                            , bizLineMsg.cpoDtlLineNum_B1.getValue(), bizLineMsg.cpoDtlLineSubNum_B1.getValue());
                    String beforeOwnCd = (String) ssmResult.getResultObject();

                    ssmResult = NWAL1830Query.getInstance().getInvtyOwnrCd(bizLineMsg.rtlWhCd_B1.getValue(), bizMsg.glblCmpyCd.getValue());
                    String afterOwnCd = (String) ssmResult.getResultObject();
                    if (beforeOwnCd != null) {
                        if (!beforeOwnCd.equals(afterOwnCd)) {
                            if (INVTY_OWNR.CSA.equals(beforeOwnCd)) {
                                bizLineMsg.rtlWhNm_B1.setErrorInfo(1, "NWZM2278E");
                            } else {
                                bizLineMsg.rtlWhNm_B1.setErrorInfo(1, "NWZM2280E");
                            }
                            return false;
                        }
                    }
                }
            }
        }
        return rc;
    }
    // QC#63527 2024/03/14 End

    /**
     * Is All Same Action in Config
     * @param bizMsg NWAL1830CMsg
     * @param dsOrdPosnNum String
     * @param dsOrdLineDrctn String
     * @return boolean
     */
    private boolean isAllSameActionInConfig(NWAL1830CMsg bizMsg, String dsOrdPosnNum, String dsOrdLineDrctn) {
        boolean rc = true;

        // 2020/04/13 QC#56374 Del Start
        // 2017/09/11 S21_NA#19800 add start
//        DS_ORD_LINE_CATGTMsg tMsg = new DS_ORD_LINE_CATGTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd, DS_ORD_LINE_CATG.LOAN_ORDERS);
//        tMsg = (DS_ORD_LINE_CATGTMsg) S21FastTBLAccessor.findByKey(tMsg);
        // 2017/09/11 S21_NA#19800 add end
        // 2020/04/13 QC#56374 Del End

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            // 2016/10/14 S21_NA#14727 Mod Start
            //            if (!ZYPConstant.FLG_ON_Y.equals(bizLineMsg.xxDplyCtrlFlg_B1.getValue())) {
            // 2017/12/27 S21_NA#22804 Mod Start
//            if (ACTION_LBL_NOT_CONV.equals(bizLineMsg.xxScrItem50Txt_B1.getValue())//
//                    || !tMsg.dsOrdLineCatgDescTxt.getValue().equals(bizLineMsg.dsOrdLineCatgDescTxt_B1.getValue())) //
            // QC#56558 2020/04/14 Del Start
            //if (ZYPConstant.FLG_OFF_N.equals(bizLineMsg.xxDplyCtrlFlg_B1.getValue()))
//                    || !tMsg.dsOrdLineCatgDescTxt.getValue().equals(bizLineMsg.dsOrdLineCatgDescTxt_B1.getValue())) // 2020/04/13 QC#56374 Del
            // 2017/12/27 S21_NA#22804 Mod End
            //{// 2017/09/11 S21_NA#19800 add
                // 2016/10/14 S21_NA#14727 Mod End
            //    continue;
            //}

            // 2020/04/13 QC#56374 Add Start
            //if(ZYPConstant.FLG_OFF_N.equals(bizLineMsg.instlBaseCtrlFlg_B1.getValue())) {
            //    continue;
            //}
            // 2020/04/13 QC#56374 Add End
            // QC#56558 2020/04/14 Del End

            if (dsOrdPosnNum.equals(bizLineMsg.dsOrdPosnNum_B1.getValue())) {
                // QC#56558 2020/04/14 Mod Start
                //if (!ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {
                //    // 2016/10/14 S21_NA#14727 Mod Start
                //    //                    bizLineMsg.dsOrdLineCatgCd_BA.setErrorInfo(1, NMAM8543E);
                //    // 2016/10/14 S21_NA#14727 Mod End
                //    bizMsg.setMessageInfo(NMAM8543E);
                //    rc = false;
                //} else if (!dsOrdLineDrctn.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                //    // 2016/10/14 S21_NA#14727 Mod Start
                //    //                    bizLineMsg.dsOrdLineCatgCd_BA.setErrorInfo(1, NMAM8543E);
                //    // 2016/10/14 S21_NA#14727 Mod End
                //    bizMsg.setMessageInfo(NMAM8543E);
                //    rc = false;
                //}
                if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {
                    if (DS_ORD_LINE_DRCTN.INBOUND.equals(dsOrdLineDrctn)) { // 2020/05/14 QC#56509-1 Add
                        if (!dsOrdLineDrctn.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                         // bizMsg.setMessageInfo(NMAM8543E); // 2020/05/14 QC#56509-1 Del
                            rc = false;
                        }
                    }
                } else {
                    if (ORD_LINE_STS.CANCELLED.equals(bizLineMsg.ordLineStsCd_B1.getValue())) {
                        continue;
                    }
                    if (ZYPConstant.FLG_ON_Y.equals(bizLineMsg.procFlg_B1.getValue())) {
                        continue;
                    }
                    if (DS_ORD_LINE_DRCTN.INBOUND.equals(dsOrdLineDrctn)) { // 2020/05/14 QC#56509-1 Add
                        if (ZYPConstant.FLG_OFF_N.equals(bizLineMsg.instlBaseCtrlFlg_B1.getValue())) {
                            continue;
                        }
                    }
                    if (ZYPCommonFunc.hasValue(bizLineMsg.setMdseCd_B1)) {
                        NWAL1830_BCMsg parentLineMsg = getSetItemParent(bizMsg, bizLineMsg);
                        if (parentLineMsg != null) {
                            if (ZYPConstant.CHKBOX_ON_Y.equals(parentLineMsg.xxChkBox_B1.getValue())) {
                                continue;
                            }
                        }
                        // bizMsg.setMessageInfo(NMAM8543E); // 2020/05/14 QC#56509-1 Del
                        rc = false;
                    } else {
                        // bizMsg.setMessageInfo(NMAM8543E); // 2020/05/14 QC#56509-1 Del
                        rc = false;
                    }
                }
                // QC#56558 2020/04/14 Mod End
            }
        }
        return rc;
    }

    /**
     * @param bizMsg
     * @param dsOrdPosnNum
     * @return
     */
    private NWAL1830_BCMsg getMainMachAction(NWAL1830CMsg bizMsg, String dsOrdPosnNum) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            if (dsOrdPosnNum.equals(bizLineMsg.dsOrdPosnNum_B1.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizLineMsg.baseCmptFlg_B1.getValue())) {
                return bizLineMsg;
            }
        }
        return null;
    }

    /**
     * checkMster
     * @param bizMsg NWAL1830CMsg
     * @return boolean
     */
    private boolean checkMster(NWAL1830CMsg bizMsg) {
        boolean existLoanSell = false;
        boolean hasError = false;

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {
                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                    existLoanSell = true;

                } else if (DS_ORD_LINE_DRCTN.INBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                    // QC#27004
                    if (NWAL1830CommonLogic.isNotReturnable(bizLineMsg.mdseCd_B1.getValue(), bizMsg)) {
                        bizLineMsg.dsOrdLineCatgCd_BA.setErrorInfo(1, "NWBM0056E"); // This is not a returnable item.
                        hasError = true;
                    }
                    // Validate Return WH
                    if (NWAL1830CommonLogic.needsWh(bizMsg.glblCmpyCd.getValue(), bizLineMsg)) { // 2020/05/14 QC#56509-1 Mod
                        // Check Master
                        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getWhInfoWithRsn(bizMsg, bizLineMsg.rtlWhNm_B1.getValue());
                        if (ssmResult.isCodeNotFound()) {
                            /** The entered @ does not exist in Master. */
                            bizLineMsg.rtlWhNm_B1.setErrorInfo(1, "NWAM0181E", new String[] {"Warehouse" });
                            hasError = true;
                            continue;
                        }
                        Map<String, String> whInfo = (Map<String, String>) ssmResult.getResultObject();
                        if (!ZYPCommonFunc.hasValue(whInfo.get("RTL_WH_CD"))) {
                            bizLineMsg.rtlWhNm_B1.setErrorInfo(1, "NWAM0181E", new String[] {"Warehouse" });
                            hasError = true;
                            continue;
                        }

                        //2020/05/14 QC#56509-1 Add Start
                        NWAL1830_BCMsg mainMach = getMainMachAction(bizMsg, bizLineMsg.dsOrdPosnNum_B1.getValue());
                        if (ZYPConstant.FLG_OFF_N.equals(bizLineMsg.baseCmptFlg_B1.getValue()) && mainMach != null && ZYPConstant.CHKBOX_ON_Y.equals(mainMach.xxChkBox_B1.getValue())) {
                            ZYPEZDItemValueSetter.setValue(bizLineMsg.rtlWhCd_B1, mainMach.rtlWhCd_B1);
                            ZYPEZDItemValueSetter.setValue(bizLineMsg.rtlSwhCd_B1, mainMach.rtlSwhCd_B1);
                        } else {
                        //2020/05/14 QC#56509-1 Add End
                            ZYPEZDItemValueSetter.setValue(bizLineMsg.rtlWhCd_B1, whInfo.get("RTL_WH_CD"));

                            // Get Sub WH
                            //// Call Default WH API
                            if (!callDefSubWhApi(bizMsg, bizLineMsg)) {
                                hasError = true;
                            }
                        }
                    }
                }
            }
        }
        if (existLoanSell) {
            //Master Check for Sales Rep
            S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getSlsRep(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.slsRepTocNm_OL.setErrorInfo(1, "NWAM0181E", new String[] {"Salesrep" });
                hasError = true;
            } else {
                Map<String, String> slsrep = (Map<String, String>) ssmResult.getResultObject();
                if (!ZYPCommonFunc.hasValue(slsrep.get("TOC_CD"))) {
                    bizMsg.slsRepTocNm_OL.setErrorInfo(1, "NWAM0181E", new String[] {"Salesrep" });
                    hasError = true;
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd_OL, slsrep.get("TOC_CD"));
            }
        }
        if (hasError) {
            return false;
        }
        return true;
    }

    /**
     * checkTimeStamp
     * @param bizMsg NWAL1830CMsg
     * @return boolean
     */
    private boolean checkTimeStamp(NWAL1830CMsg bizMsg) {
        // CPO
        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum_OH);
        cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cpoTMsg);
        if (cpoTMsg == null) {
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"CPO [#" + bizMsg.cpoOrdNum_OH.getValue() + "]" });
            return false;
        }
        String curUpdtDt = cpoTMsg.ezUpTime.getValue();
        String curTimeZone = cpoTMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_OH.getValue(), bizMsg.ezUpTimeZone_OH.getValue(), curUpdtDt, curTimeZone)) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        // CPO_DTL
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {

                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, bizLineMsg.cpoOrdNum_B1);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, bizLineMsg.cpoDtlLineNum_B1);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, bizLineMsg.cpoDtlLineSubNum_B1);
                cpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cpoDtlTMsg);

                if (cpoDtlTMsg == null) {
                    bizMsg.setMessageInfo(NWAM0684E, new String[] {"CPO Detail [#" + bizLineMsg.cpoOrdNum_B1.getValue() + "]" });
                    return false;
                }
                curUpdtDt = cpoDtlTMsg.ezUpTime.getValue();
                curTimeZone = cpoDtlTMsg.ezUpTimeZone.getValue();
                if (!ZYPDateUtil.isSameTimeStamp(bizLineMsg.ezUpTime_B1.getValue(), bizLineMsg.ezUpTimeZone_B1.getValue(), curUpdtDt, curTimeZone)) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * callDefSubWhApi
     * @param bizMsg NWAL1830CMsg
     * @param bizLineMsg NWAL1830_BCMsg
     * @return boolean
     */
    private boolean callDefSubWhApi(NWAL1830CMsg bizMsg, NWAL1830_BCMsg bizLineMsg) {

        NLZC215001PMsg defSubWhApiPMsg = new NLZC215001PMsg();
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.xxModeCd, NLZC215001Constant.MODE_RMA);
        if (ZYPCommonFunc.hasValue(bizLineMsg.mdseCd_BM)) {
            ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.mdseCd, bizLineMsg.mdseCd_BM);
        } else {
            // 2016/09/29 S21_NA#14887 Mod Start
            // ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.mdseCd, bizLineMsg.mdseCd_B1); 
            ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.mdseCd, bizLineMsg.mdseCd_SP);
            // 2016/09/29 S21_NA#14887 Mod End
        }
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.dsOrdCatgCd, bizLineMsg.dsOrdLineCatgCd_BA);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.destRtlWhCd, bizLineMsg.rtlWhCd_B1);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.svcMachMstrPk, bizLineMsg.svcMachMstrPk_B1);//S21_NA ADD QC#22693

        // call NLZC2150 Get Default Sub Warehouse API
        new NLZC215001().execute(defSubWhApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(defSubWhApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defSubWhApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizLineMsg.rtlWhNm_B1.setErrorInfo(1, msgId, msgPrms);
                return false;
            }
        }
        ZYPEZDItemValueSetter.setValue(bizLineMsg.rtlSwhCd_B1, defSubWhApiPMsg.destRtlSwhCd);
        return true;
    }

    /**
     * getOrigOrdHdr
     * @param bizMsg NWAL1830CMsg
     * @param origOrdHdrMap Map<String, Object>
     */
    private void getOrigOrdHdr(NWAL1830CMsg bizMsg, Map<String, Object> origOrdHdrMap) {

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdHdr(bizMsg, bizMsg.cpoOrdNum_OH.getValue());
        Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"CPO [#" + bizMsg.cpoOrdNum_OH.getValue() + "]" });
        }
        origOrdHdrMap.putAll(resultMap);
    }

    /**
     * getExistOrdHdr
     * @param bizMsg NWAL1830CMsg
     * @param existOrdHdrMap Map<String, Object> 
     */
    private void getExistOrdHdr(NWAL1830CMsg bizMsg, Map<String, Object> existOrdHdrMap) {

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdHdr(bizMsg, bizMsg.cpoOrdNum_OL.getValue());
        Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"CPO [#" + bizMsg.cpoOrdNum_OL.getValue() + "]" });
        }
        existOrdHdrMap.putAll(resultMap);
    }

    /**
     * getOrigOrdConfigList
     * @param bizMsg NWAL1830CMsg
     * @param origOrdConfigMapList List<Map<String, Object>>
     * @param allConfig boolean
     */
    private void getOrigOrdConfigList(NWAL1830CMsg bizMsg, List<Map<String, Object>> origOrdConfigMapList, boolean allConfig) {

        List<BigDecimal> dsCpoConfigPkList = null;
        if (!allConfig) {
            Set<BigDecimal> dsCpoConfigPkSet = new HashSet<BigDecimal>();
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
                if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue()) //
                        && DS_ORD_LINE_DRCTN.OUTBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                    dsCpoConfigPkSet.add(bizLineMsg.dsCpoConfigPk_B1.getValue());
                }
            }
            dsCpoConfigPkList = new ArrayList<BigDecimal>();
            for (BigDecimal pk : dsCpoConfigPkSet) {
                dsCpoConfigPkList.add(pk);
            }
        }

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdConfig(bizMsg, bizMsg.cpoOrdNum_OH.getValue(), dsCpoConfigPkList);
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"CPO Config [#" + bizMsg.cpoOrdNum_OH.getValue() + "]" });
        }
        origOrdConfigMapList.addAll(resultMapList);
    }

    /**
     * getExistOrdConfigList
     * @param bizMsg NWAL1830CMsg
     * @param existOrdConfigMapList List<Map<String, Object>>
     */
    private void getExistOrdConfigList(NWAL1830CMsg bizMsg, List<Map<String, Object>> existOrdConfigMapList) {

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdConfig(bizMsg, bizMsg.cpoOrdNum_OL.getValue(), null);
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"CPO Config [#" + bizMsg.cpoOrdNum_OL.getValue() + "]" });
        }
        existOrdConfigMapList.addAll(resultMapList);

    }

    /**
     * getExistOrdHdr
     * @param bizMsg NWAL1830CMsg
     * @param existOrdHdrMap Map<String, Object> 
     */
    private boolean isHeaderOnly(NWAL1830CMsg bizMsg, List<Map<String, Object>> existOrdConfigMapList) {

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdConfig(bizMsg, bizMsg.cpoOrdNum_OL.getValue(), null);
        if (ssmResult.isCodeNotFound()) {
            //Header only order
            return true;
        }

        //Header and config (and should be lines)
        return false;

    }

    /**
     * getOrigOrdLineList
     * @param bizMsg NWAL1830CMsg
     * @param origOrdLineMapList List<Map<String, Object>>
     * @param allLine boolean
     */
    private void getOrigOrdLineList(NWAL1830CMsg bizMsg, List<Map<String, Object>> origOrdLineMapList, boolean allLine) {

        List<String> lineNumList = new ArrayList<String>();
        if (!allLine) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
                if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue()) //
                        && DS_ORD_LINE_DRCTN.OUTBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                    lineNumList.add(bizLineMsg.cpoDtlLineNum_B1.getValue());
                }
            }
        }

        // 2017/1/13 S21_NA#17076 Mod Start
        //        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdDtl(bizMsg, bizMsg.cpoOrdNum_OH.getValue(), lineNumList);
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdDtl(bizMsg, bizMsg.cpoOrdNum_OH.getValue(), lineNumList, true);
        // 2017/1/13 S21_NA#17076 Mod End
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"CPO Detail [#" + bizMsg.cpoOrdNum_OH.getValue() + "]" });
        }
        origOrdLineMapList.addAll(resultMapList);
    }

    /**
     * getExistOrdLineList
     * @param bizMsg NWAL1830CMsg
     * @param existOrdLineMapList List<Map<String, Object>>
     */
    private void getExistOrdLineList(NWAL1830CMsg bizMsg, List<Map<String, Object>> existOrdLineMapList) {

        // 2017/1/13 S21_NA#17076 Mod Start
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdDtl(bizMsg, bizMsg.cpoOrdNum_OL.getValue(), null, false);
        // 2017/1/13 S21_NA#17076 Mod End
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"CPO Detail [#" + bizMsg.cpoOrdNum_OL.getValue() + "]" });
        }
        existOrdLineMapList.addAll(resultMapList);
    }

    /**
     * getOrigOrdPriceList
     * @param bizMsg NWAL1830CMsg
     * @param origOrdPriceMapList List<Map<String, Object>>
     * @param allLine boolean
     */
    private void getOrigOrdPriceList(NWAL1830CMsg bizMsg, List<Map<String, Object>> origOrdPriceMapList, boolean allLine) {

        List<String> lineNumList = new ArrayList<String>();
        if (!allLine) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);
                if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue()) //
                        && DS_ORD_LINE_DRCTN.OUTBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, bizLineMsg))) {
                    lineNumList.add(bizLineMsg.cpoDtlLineNum_B1.getValue());
                }
            }
        }

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdPrc(bizMsg, bizMsg.cpoOrdNum_OH.getValue(), lineNumList);
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"Price Calculation Base [#" + bizMsg.cpoOrdNum_OH.getValue() + "]" });
        }
        origOrdPriceMapList.addAll(resultMapList);
    }

    /**
     * getExistOrdPriceList
     * @param bizMsg NWAL1830CMsg
     * @param existOrdPriceMapList  List<Map<String, Object>>
     */
    private void getExistOrdPriceList(NWAL1830CMsg bizMsg, List<Map<String, Object>> existOrdPriceMapList) {

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdPrc(bizMsg, bizMsg.cpoOrdNum_OL.getValue(), null);
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"Price Calculation Base [#" + bizMsg.cpoOrdNum_OL.getValue() + "]" });
        }
        existOrdPriceMapList.addAll(resultMapList);
    }

    /**
     * getExistOrdSlsCrList
     * @param bizMsg NWAL1830CMsg
     * @param existOrdPriceMapList  List<Map<String, Object>>
     */
    private void getExistOrdSlsCrList(NWAL1830CMsg bizMsg, List<Map<String, Object>> existOrdSlsCrMapList) {

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdSlsCr(bizMsg, bizMsg.cpoOrdNum_OL.getValue());
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"Sales Credit [#" + bizMsg.cpoOrdNum_OL.getValue() + "]" });
        }
        existOrdSlsCrMapList.addAll(resultMapList);
    }

    /**
     * getOrigOrdSlsCrList
     * @param bizMsg NWAL1830CMsg
     * @param existOrdPriceMapList  List<Map<String, Object>>
     */
    private void getOrigOrdSlsCrList(NWAL1830CMsg bizMsg, List<Map<String, Object>> origOrdSlsCrMapList) {

        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getOrdSlsCr(bizMsg, bizMsg.cpoOrdNum_OH.getValue());
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.isCodeNotFound()) {
            // Error
            bizMsg.setMessageInfo(NWAM0684E, new String[] {"Sales Credit [#" + bizMsg.cpoOrdNum_OL.getValue() + "]" });
        }
        origOrdSlsCrMapList.addAll(resultMapList);
    }

    /**
     * getInvCmntTxt
     * @param bizMsg NWAL1830CMsg
     * @param copOrdNum String
     * @return String
     */
    private String getInvCmntTxt(NWAL1830CMsg bizMsg, String copOrdNum) {
        MSG_TXT_DTLTMsg msgTxtDtl = new MSG_TXT_DTLTMsg();
        msgTxtDtl.setSQLID("001");
        msgTxtDtl.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        msgTxtDtl.setConditionValue("cpoOrdNum01", copOrdNum);
        msgTxtDtl.setConditionValue("txtTpCd01", TXT_TP.INVOICE_COMMENT);

        MSG_TXT_DTLTMsgArray msgTxtDtlAry = (MSG_TXT_DTLTMsgArray) EZDTBLAccessor.findByCondition(msgTxtDtl);
        if (msgTxtDtlAry.getValidCount() != 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msgTxtDtlAry.getValidCount(); i++) {
            sb.append(msgTxtDtlAry.no(i).msgTxtInfoTxt.getValue());
        }
        return sb.toString();
    }

    /**
     * createCpoUpdApiParamNewOrder
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordHdr Map<String, Object>
     * @param ordConfig List<Map<String, Object>>
     * @param ordList   List<Map<String, Object>>
     * @param ordPrc    List<Map<String, Object>>
     * @param checkLineList List<NWAL1830_BCMsg>
     * @param slsCrList List<Map<String, Object>>
     * @return Map<String, String[]>
     */
    //    private Map<BigDecimal, String[]> createCpoUpdApiParamNewOrder(
    //            NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, Map<String, Object> ordHdr, List<Map<String, Object>> ordConfig
    //            ,  List<Map<String, Object>> ordList, List<Map<String, Object>> ordPrc, List<NWAL1830_BCMsg> checkLineList) {
    private Map<BigDecimal, String[]> createCpoUpdApiParamNewOrder(//
            NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, Map<String, Object> ordHdr, List<Map<String, Object>> ordConfig //
            , List<Map<String, Object>> ordList, List<Map<String, Object>> ordPrc, List<NWAL1830_BCMsg> checkLineList, List<Map<String, Object>> slsCrList) {

        /**
         * New Order
         */
        // [Header]
        createCpoUpdApiParamHdr(bizMsg, pMsg, ordHdr);
        // Original Value Setting
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC150001Constant.MODE_SAVE);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, CPO_SRC_TP.LOAN_WORKBENCH);
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN)); // 2017/08/30 QC#20763 Add
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, bizMsg.cpoOrdNum_OH);
        pMsg.cpoOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd_OL);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd_OL);
        pMsg.dsOrdRsnCd.clear();
        pMsg.ordFuflLvlCd.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, bizMsg.slsRepTocCd_OL);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, bizMsg.slsDt);
        pMsg.negoDealAmt.clear();

        //2019/12/12 QC#54747 Add Start
        //
        // Price List
        //
        NWZC157001PMsg prcApiPMsg = NWAL1830CommonLogic.callPricingApiOfGetPriceListMode(bizMsg, pMsg, PRC_CTX_TP.SALES_PRICING, true);
        if (prcApiPMsg == null) {
            return null;
        }

        String prcCatgCd = "";

        if (ZYPCommonFunc.hasValue(pMsg.prcCatgCd)){
            String origPrcCatgCd = pMsg.prcCatgCd.getValue();

            for (int i = 0; i < prcApiPMsg.xxPrcList.getValidCount(); i++) {
                if (origPrcCatgCd.equals(prcApiPMsg.xxPrcList.no(i).prcCatgCd.getValue())) {
                    prcCatgCd = origPrcCatgCd;
                    break;
                }
            }
        }

        if (S21StringUtil.isEmpty(prcCatgCd) &&(prcApiPMsg.xxPrcList.getValidCount() > 0)){
            prcCatgCd = prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue();
        }

        ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd, prcCatgCd);

        NWZC157001PMsg prcApiPMsgFloor = NWAL1830CommonLogic.callPricingApiOfGetPriceListMode(bizMsg, pMsg, PRC_CTX_TP.FLOOR_PRICE, true);
        if (prcApiPMsgFloor == null) {
            return null;
        }

        String floorPrcCatgCd = "";

        if (ZYPCommonFunc.hasValue(pMsg.flPrcListCd)){
            String origFloorPrcCatgCd = pMsg.flPrcListCd.getValue();

            for (int i = 0; i < prcApiPMsgFloor.xxPrcList.getValidCount(); i++) {
                if (origFloorPrcCatgCd.equals(prcApiPMsgFloor.xxPrcList.no(i).prcCatgCd.getValue())) {
                    floorPrcCatgCd = origFloorPrcCatgCd;
                    break;
                }
            }
        }

        if (S21StringUtil.isEmpty(floorPrcCatgCd) &&(prcApiPMsgFloor.xxPrcList.getValidCount() > 0)){
            floorPrcCatgCd = prcApiPMsgFloor.xxPrcList.no(0).prcCatgCd.getValue();
        }

        ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd, floorPrcCatgCd);
        //2019/12/12 QC#54747 Add End

        String resultFlg = NWAL1830Query.getInstance().getOrdCatgBizCtx(bizMsg);
        if (!ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            // For Lease 
            pMsg.leaseCmpyPoNum.clear();
            pMsg.leasePrchOptCd.clear();
            pMsg.leaseTermMthAot.clear();
            pMsg.leasePmtFreqCd.clear();
            pMsg.leaseTotPmtAmt.clear();
        }
        pMsg.sendInvFlg.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTs, bizMsg.slsDt.getValue());
        // [Sales Credit Header]
        String roleTp = NWAL1830CommonLogic.getSlsRepRoleTp(bizMsg, bizMsg.dsOrdTpCd_OL.getValue());
        createCpoUpdApiParamAddSlsCrHdr(bizMsg, pMsg, roleTp, slsCrList);

        // [Add Config]
        createCpoUpdApiParamAddConfig(bizMsg, pMsg, ordConfig);
        // Original Value Setting
        int posnNum = 1;
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(i);
            BigDecimal origDsCpoConfigPk = cpoConfigPMsg.dsCpoConfigPk.getValue();
            cpoConfigPMsg.dsCpoConfigPk.clear();
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, Integer.toString(posnNum));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.TO_SALES_CONVERSION);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            // [Sales Credit Config]
            // 2017/11/02 QC#22248 mod start
            //            createCpoUpdApiParamAddSlsCrConfig(bizMsg, pMsg, roleTp, Integer.toString(posnNum), CONFIG_CATG.OUTBOUND);
            createCpoUpdApiParamAddSlsCrConfig(bizMsg, pMsg, roleTp, Integer.toString(posnNum), CONFIG_CATG.OUTBOUND, slsCrList, origDsCpoConfigPk);
            // 2017/11/02 QC#22248 mod end
            posnNum++;
        }
        // [Add Line]
        posnNum = 0;
        int prePosnNum = 0;

        //2019/12/12 QC#54747 Mod Start
        createCpoUpdApiParamAddLine(bizMsg, pMsg, ordList, true, prcApiPMsg.xxPrcList, prcApiPMsgFloor.xxPrcList);
        //createCpoUpdApiParamAddLine(bizMsg, pMsg, ordList, true);
        //2019/12/12 QC#54747 Mod End

        // 2017/09/11 S21_NA#19800 add start
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getDsOrdCatgListForValSet(bizMsg, LOAN_ORD_ACTION_LOAN_SELL);
        List<Map<String, String>> sellLineCatgList = (List<Map<String, String>>) ssmResult.getResultObject();

        DS_ORD_LINE_CATGTMsg tMsg = new DS_ORD_LINE_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd, DS_ORD_LINE_CATG.LOAN_ORDERS);
        tMsg = (DS_ORD_LINE_CATGTMsg) S21FastTBLAccessor.findByKey(tMsg);
        // 2017/09/11 S21_NA#19800 add End

        Set<BigDecimal> mmPkSet = new HashSet<BigDecimal>();
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);
            NWAL1830_BCMsg convLine = NWAL1830CommonLogic.getOrigLine(dtlPMsg, checkLineList);

            if (Integer.parseInt(convLine.dsOrdPosnNum_B1.getValue()) != prePosnNum) {
                posnNum++;
            }
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_SAVE);
            // 2017/09/11 S21_NA#19800 mod start
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, convLine.dsOrdLineCatgCd_BA);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, NWAL1830CommonLogic.getRtlWhLoanSell(bizMsg, convLine.dsOrdLineCatgCd_BA.getValue()));
            if (tMsg.dsOrdLineCatgDescTxt.getValue().equals(convLine.dsOrdLineCatgDescTxt_B1.getValue()) || sellLineCatgList.size() == 1) {
                // QC#63527 2024/03/14 Start
                ssmResult = NWAL1830Query.getInstance().getCpoDetailInvLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum_OH.getValue(), dtlPMsg.cpoDtlLineNum_A1.getValue(), dtlPMsg.cpoDtlLineSubNum_A1.getValue());
                String result = (String) ssmResult.getResultObject();

                for (int j = 0; j < sellLineCatgList.size(); j++) {
                    if (result.equals(sellLineCatgList.get(j).get("OWNR"))) {
                        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, sellLineCatgList.get(j).get("DS_ORD_LINE_CATG_CD"));
                        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, sellLineCatgList.get(j).get("RTL_WH_CD"));
                    }
                }
                // QC#63527 2024/03/14 End
            } else {
            	// QC#63527 2024/03/14 Start
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, sellLineCatgList.get(1).get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, sellLineCatgList.get(1).get("RTL_WH_CD"));
                // QC#63527 2024/03/14 End
                dtlPMsg.rtlSwhCd_A1.clear();
            }
            // 2017/09/11 S21_NA#19800 mod end
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_A1, dtlPMsg.rtlWhCd_A1.getValue() + dtlPMsg.rtlSwhCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, Integer.toString(posnNum));
            if (ZYPCommonFunc.hasValue(convLine.mdseCd_BM)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, convLine.mdseCd_BM);
            } else {
                // 2016/09/29 S21_NA#14887 Add Start
                // ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, convLine.mdseCd_B1);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, convLine.mdseCd_SP);
                // 2016/09/29 S21_NA#14887 Add End
            }
            ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, bizMsg.slsRepTocCd_OL);

            dtlPMsg.dsCpoConfigPk_A1.clear();

            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_A1, convLine.cpoDtlLineNum_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineSubNum_A1, convLine.cpoDtlLineSubNum_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.loanBalQty_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoOrdNum_A1, bizMsg.cpoOrdNum_OH);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineNum_A1, convLine.cpoDtlLineNum_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineSubNum_A1, convLine.cpoDtlLineSubNum_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, BigDecimal.ONE);
            dtlPMsg.refCpoDtlLineNum_A1.clear();
            dtlPMsg.refCpoDtlLineSubNum_A1.clear();
            dtlPMsg.dplyLineRefNum_A1.clear();

            // Set Serial#, Machine Master PK
            List<NWAL1830_MCMsg> mmList = NWAL1830CommonLogic.getMachMstrListForLine(bizMsg, convLine.cpoDtlLineNum_B1.getValue(), convLine.cpoDtlLineSubNum_B1.getValue());
            for (NWAL1830_MCMsg mm : mmList) {
                if (!mmPkSet.contains(mm.svcMachMstrPk.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_A1, mm.serNum);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_A1, mm.svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, mm.mdseCd);
                    mmPkSet.add(mm.svcMachMstrPk.getValue());
                    break;
                }
            }

            // Set Price Summary
            setPriceSummaryDtail(dtlPMsg, ordPrc);

            prePosnNum = Integer.parseInt(convLine.dsOrdPosnNum_B1.getValue());
        }

        String cpoDtlLineNum = "000";
        Map<BigDecimal, String[]> mmPkToConvLineNumNew = new HashMap<BigDecimal, String[]>(); //Map<MMPK, [0:NewLine#, 1:NewLineSub#]>
        NWAL1830CommonLogic.numberingCpoDtlLineNum(pMsg, cpoDtlLineNum, 0, mmPkToConvLineNumNew, checkLineList);

        // [Add Price]
        //        createCpoUpdApiParamAddPrcCalcBase(bizMsg, pMsg, ordPrc);
        createCpoUpdApiParamAddPrcCalcBase(bizMsg, pMsg, ordPrc, mmPkToConvLineNumNew, checkLineList);

        return mmPkToConvLineNumNew;
    }

    /**
     * createCpoUpdApiParamExistOrder
     * @param bizMsg         NWAL1830CMsg
     * @param pMsg           NWZC150001PMsg
     * @param ordHdr         Map<String, Object>
     * @param origOrdConfig  List<Map<String, Object>>
     * @param existOrdConfig List<Map<String, Object>>
     * @param origOrdList    List<Map<String, Object>>
     * @param existOrdList   List<Map<String, Object>>
     * @param origOrdPrc     List<Map<String, Object>>
     * @param existOrdPrc    List<Map<String, Object>>
     * @param checkLineList  List<NWAL1830_BCMsg>
     * @return Map<String, String[]> origLineNumToConvLineNumMap
     */
    private Map<BigDecimal, String[]> createCpoUpdApiParamExistOrder(//
            NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, Map<String, Object> ordHdr, List<Map<String, Object>> origOrdConfig //
            , List<Map<String, Object>> origOrdList, List<Map<String, Object>> origOrdPrc, List<NWAL1830_BCMsg> checkLineList, List<Map<String, Object>> existSlsCr) {

        /**
         * Existing Order
         */
        // [Header]
        createCpoUpdApiParamHdr(bizMsg, pMsg, ordHdr);
        // Original Value Setting
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC150001Constant.MODE_SAVE);
        //
        //        // Get Sales Rep Role Type for Sales Credit
        //        String roleTp = NWAL1830CommonLogic.getSlsRepRoleTp(bizMsg, (String) ordHdr.get("DS_ORD_TP_CD"));

        int posnNum = NWAL1830CommonLogic.getMaxPosnNum(bizMsg, CONFIG_CATG.OUTBOUND);
        int existOrdConfgCnt = pMsg.cpoConfig.getValidCount();
        // Add Checked Line 
        createCpoUpdApiParamAddConfig(bizMsg, pMsg, origOrdConfig);
        // Original Value Setting
        int posnNumCnt = posnNum + 1;
        for (int i = existOrdConfgCnt; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(i);
            cpoConfigPMsg.dsCpoConfigPk.clear();
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, Integer.toString(posnNumCnt));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.TO_SALES_CONVERSION);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            // [Sales Credit Config]
            createCpoUpdApiParamAddSlsCrConfigForExistingOrd(bizMsg, pMsg, existSlsCr, Integer.toString(posnNumCnt), CONFIG_CATG.OUTBOUND);
            posnNumCnt++;
        }

        // [Add Line]
        String maxLineNum = NWAL1830CommonLogic.getMaxLineNum(bizMsg, CONFIG_CATG.OUTBOUND);
        int existOrdCnt = pMsg.A.getValidCount();

        // Add Checked Line 
        createCpoUpdApiParamAddLine(bizMsg, pMsg, origOrdList, true);

        // 2017/09/11 S21_NA#19800 add start
        S21SsmEZDResult ssmResult = NWAL1830Query.getInstance().getDsOrdCatgListForValSet(bizMsg, LOAN_ORD_ACTION_LOAN_SELL);
        List<Map<String, String>> sellLineCatgList = (List<Map<String, String>>) ssmResult.getResultObject();

        DS_ORD_LINE_CATGTMsg tMsg = new DS_ORD_LINE_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd, DS_ORD_LINE_CATG.LOAN_ORDERS);
        tMsg = (DS_ORD_LINE_CATGTMsg) S21FastTBLAccessor.findByKey(tMsg);
        // 2017/09/11 S21_NA#19800 add End

        posnNumCnt = posnNum;
        int prePosnNum = 0;
        Set<BigDecimal> mmPkSet = new HashSet<BigDecimal>();

        for (int i = existOrdCnt; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);
            NWAL1830_BCMsg convLine = NWAL1830CommonLogic.getOrigLine(dtlPMsg, checkLineList);

            if (Integer.parseInt(convLine.dsOrdPosnNum_B1.getValue()) != prePosnNum) {
                posnNumCnt++;
            }
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_SAVE);
            // 2017/09/11 S21_NA#19800 mod start
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, convLine.dsOrdLineCatgCd_BA);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, NWAL1830CommonLogic.getRtlWhLoanSell(bizMsg, convLine.dsOrdLineCatgCd_BA.getValue()));
            if (tMsg.dsOrdLineCatgDescTxt.getValue().equals(convLine.dsOrdLineCatgDescTxt_B1.getValue()) || sellLineCatgList.size() == 1) {
            	// QC#63527 2024/03/14 Start
                ssmResult = NWAL1830Query.getInstance().getCpoDetailInvLocCd(bizMsg.glblCmpyCd.getValue(), bizMsg.cpoOrdNum_OH.getValue(), dtlPMsg.cpoDtlLineNum_A1.getValue(), dtlPMsg.cpoDtlLineSubNum_A1.getValue());

                String result = (String) ssmResult.getResultObject();
                for (int j = 0; j < sellLineCatgList.size(); j++) {
                    if (result.equals(sellLineCatgList.get(j).get("OWNR"))) {
                        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, sellLineCatgList.get(j).get("DS_ORD_LINE_CATG_CD"));
                        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, sellLineCatgList.get(j).get("RTL_WH_CD"));
                    }
                }
                // QC#63527 2024/03/14 End
            } else {
            	// QC#63527 2024/03/14 Start
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, sellLineCatgList.get(2).get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, sellLineCatgList.get(2).get("RTL_WH_CD"));
                // QC#63527 2024/03/14 End
                dtlPMsg.rtlSwhCd_A1.clear();
            }
            // 2017/09/11 S21_NA#19800 mod end
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_A1, dtlPMsg.rtlWhCd_A1.getValue() + dtlPMsg.rtlSwhCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, Integer.toString(posnNumCnt));
            if (ZYPCommonFunc.hasValue(convLine.mdseCd_BM)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, convLine.mdseCd_BM);
            } else {
                // 2016/09/29 S21_NA#14887 Add Start
                // ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, convLine.mdseCd_B1);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, convLine.mdseCd_SP);
                // 2016/09/29 S21_NA#14887 Add End
            }
            dtlPMsg.dsCpoConfigPk_A1.clear();

            //            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_A1, convLine.cpoDtlLineNum_B1);
            //            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineSubNum_A1, convLine.cpoDtlLineSubNum_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.loanBalQty_A1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoOrdNum_A1, bizMsg.cpoOrdNum_OH);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineNum_A1, convLine.cpoDtlLineNum_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineSubNum_A1, convLine.cpoDtlLineSubNum_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, pMsg.slsRepCd);
            dtlPMsg.refCpoDtlLineNum_A1.clear();
            dtlPMsg.refCpoDtlLineSubNum_A1.clear();
            dtlPMsg.dplyLineRefNum_A1.clear();

            // Add Start 2017/10/17 QC#21507
            ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoSrcTpCd_A1, CPO_SRC_TP.LOAN_WORKBENCH);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefNum_A1, bizMsg.cpoOrdNum_OH);
            // Add End 2017/10/17 QC#21507

            // Set Serial#, Machine Master PK
            List<NWAL1830_MCMsg> mmList = NWAL1830CommonLogic.getMachMstrListForLine(bizMsg, convLine.cpoDtlLineNum_B1.getValue(), convLine.cpoDtlLineSubNum_B1.getValue());
            for (NWAL1830_MCMsg mm : mmList) {
                if (!mmPkSet.contains(mm.svcMachMstrPk.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_A1, mm.serNum);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_A1, mm.svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, mm.mdseCd);
                    mmPkSet.add(mm.svcMachMstrPk.getValue());
                    break;
                }
            }

            // Set Price Summary
            setPriceSummaryDtail(dtlPMsg, origOrdPrc);

            prePosnNum = Integer.parseInt(convLine.dsOrdPosnNum_B1.getValue());
        }
        // Set Line Number
        Map<BigDecimal, String[]> mmPkToConvLineNumNew = new HashMap<BigDecimal, String[]>(); //Map<MMPK, [0:NewLine#, 1:NewLineSub#]>
        NWAL1830CommonLogic.numberingCpoDtlLineNum(pMsg, maxLineNum, existOrdCnt, mmPkToConvLineNumNew, checkLineList);

        // [Add Price]
        // Existing Order
        // Add Checked Line 
        createCpoUpdApiParamAddPrcCalcBase(bizMsg, pMsg, origOrdPrc, mmPkToConvLineNumNew, checkLineList);

        return mmPkToConvLineNumNew;

    }

    private void createCpoUpdApiParamExistOrderAddExistInfo(//
            NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> ordConfig, List<Map<String, Object>> ordList, List<Map<String, Object>> ordPrc, List<Map<String, Object>> slsCr) {

        // [Add Config]
        createCpoUpdApiParamAddConfig(bizMsg, pMsg, ordConfig);

        // [Add Line]
        createCpoUpdApiParamAddLine(bizMsg, pMsg, ordList, false);

        // [Add Price]
        createCpoUpdApiParamAddPrcCalcBase(bizMsg, pMsg, ordPrc);

        // [Add Sales Credit]
        createCpoUpdApiParamAddSlsCr(bizMsg, pMsg, slsCr);

    }

    // 2017/11/02 QC#22248 mod start
    /**
     * createCpoUpdApiParamReturn
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordHdr Map<String, Object>
     * @param ordConfig List<Map<String, Object>>
     * @param ordList List<Map<String, Object>> 
     * @param ordPrc List<Map<String, Object>>
     * @param checkLineList List<NWAL1830_BCMsg>
     * @param slsCrList List<Map<String, Object>>
     * @param rtrnCtacList List<Map<String, Object>>
     * @param rtrnSiteList List<Map<String, Object>>
     * @param rtrnIstlList List<Map<String, Object>>
     * @param rtrnDelyList List<Map<String, Object>>
     * @return Map<String, String[]> origLineNumToConvLineNumMap
     */
    //    private Map<BigDecimal, String[]> createCpoUpdApiParamReturn(
    //            NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, Map<String, Object> ordHdr, List<Map<String, Object>> ordConfig
    //            ,  List<Map<String, Object>> ordList, List<Map<String, Object>> ordPrc, List<NWAL1830_BCMsg> checkLineList) {
    private Map<BigDecimal, String[]> createCpoUpdApiParamReturn(//
            NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, Map<String, Object> ordHdr, List<Map<String, Object>> ordConfig //
            , List<Map<String, Object>> ordList, List<Map<String, Object>> ordPrc, List<NWAL1830_BCMsg> checkLineList, List<Map<String, Object>> slsCrList //
            , List<Map<String, Object>> rtrnDelyList, List<Map<String, Object>> rtrnIstlList, List<Map<String, Object>> rtrnSiteList, List<Map<String, Object>> rtrnCtacList) { // QC#22641
        // 2017/11/02 QC#22248 mod end
        /**
         * Loan Return
         */
        // [Header] 
        createCpoUpdApiParamHdr(bizMsg, pMsg, ordHdr);
        // Original Value Setting
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC150001Constant.MODE_SUBMIT);

        // // Get Sales Rep Role Type for Sales Credit
        String roleTp = NWAL1830CommonLogic.getSlsRepRoleTp(bizMsg, (String) ordHdr.get("DS_ORD_TP_CD"));

        // [Add Config]
        // Original Order
        int posnNum = NWAL1830CommonLogic.getMaxPosnNum(bizMsg, CONFIG_CATG.INBOUND);
        // Add Checked Line 
        createCpoUpdApiParamAddConfigRetrun(bizMsg, pMsg, ordConfig, checkLineList);
        // Original Value Setting
        int posnNumCnt = posnNum + 1;
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(i);
            BigDecimal origDsCpoConfigPk = cpoConfigPMsg.dsCpoConfigPk.getValue();
            // 2018/01/19 QC#23338 add start
            String origDsPosnNum = cpoConfigPMsg.dsOrdPosnNum.getValue();
            // 2018/01/19 QC#23338 add end
            cpoConfigPMsg.dsCpoConfigPk.clear();
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, Integer.toString(posnNumCnt));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.RETURN_EXISTING_IB);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.INBOUND);
            // [Sales Credit Config]
            // 2017/11/02 QC#22248 mod start
            //            createCpoUpdApiParamAddSlsCrConfig(bizMsg, pMsg, roleTp, Integer.toString(posnNumCnt), CONFIG_CATG.INBOUND);
            createCpoUpdApiParamAddSlsCrConfig(bizMsg, pMsg, roleTp, Integer.toString(posnNumCnt), CONFIG_CATG.INBOUND, slsCrList, origDsCpoConfigPk);
            // 2017/11/02 QC#22248 mod end
            // QC#22641
            createCpoUpdApiParamDISCForRtrn(bizMsg, pMsg, rtrnDelyList, rtrnIstlList, rtrnSiteList, rtrnCtacList, Integer.toString(posnNumCnt), origDsCpoConfigPk);

            posnNumCnt++;
        }

        // [Add Line]
        String maxLineNum = NWAL1830CommonLogic.getMaxLineNum(bizMsg, CONFIG_CATG.INBOUND);
        // Add Checked Line 
        createCpoUpdApiParamAddLineReturn(bizMsg, pMsg, ordList, checkLineList);

        posnNumCnt = posnNum;
        int prePosnNum = 0;
        Set<BigDecimal> mmPkSet = new HashSet<BigDecimal>();

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg dtlPMsg = pMsg.rtnDtl.no(i);
            NWAL1830_BCMsg convLine = null;
            for (NWAL1830_BCMsg bbizMsg : checkLineList) {
                if (bbizMsg.cpoDtlLineNum_B1.getValue().equals(dtlPMsg.cpoDtlLineNum_B1.getValue()) //
                        && bbizMsg.cpoDtlLineSubNum_B1.getValue().equals(dtlPMsg.cpoDtlLineSubNum_B1.getValue())) {
                    convLine = bbizMsg;
                    break;
                }
            }
            if (Integer.parseInt(convLine.dsOrdPosnNum_B1.getValue()) != prePosnNum) {
                posnNumCnt++;
            }
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_B1, NWZC150001Constant.RQST_TP_DTL_NEW);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.configTpCd_B1, CONFIG_TP.RETURN_EXISTING_IB);     // QC#50556 2019/05/31 Add
            // 2020/04/13 QC#56374 Add Start
            if (MERCHANDISE.equals(convLine.dsOrdLineCatgDescTxt_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_B1, DS_ORD_LINE_CATG.RMA_NO_CREDIT);
            } else if (INITIAL_SUPPLIES.equals(convLine.dsOrdLineCatgDescTxt_B1.getValue())) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_B1, DS_ORD_LINE_CATG.RMA_INITIAL_SUPPLIES);
            } else {
            // 2020/04/13 QC#56374 Add End
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_B1, convLine.dsOrdLineCatgCd_BA);
            }
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_B1, convLine.rtlWhCd_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_B1, convLine.rtlSwhCd_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_B1, dtlPMsg.rtlWhCd_B1.getValue() + dtlPMsg.rtlSwhCd_B1.getValue());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_B1, Integer.toString(posnNumCnt));
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_B1, BigDecimal.ONE.negate());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_B1, BigDecimal.ONE.negate());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.taxFlg_B1, ZYPConstant.FLG_ON_Y);
            // ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_B1, ZYPConstant.FLG_OFF_N); QC#50556 2019/05/31 Delete
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnQty_B1, BigDecimal.ZERO);
            // 2018/07/30 S21_NA#26330 mod start
            String defaultRsnCd = ZYPCodeDataUtil.getVarCharConstValue(NWAL1830_LOAN_RTRN_RSN_CD, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnRsnCd_B1, RTRN_RSN.REGULAR_RETURN);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnRsnCd_B1, defaultRsnCd);
            // 2018/07/30 S21_NA#26330 mod end
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoOrdNum_B1, bizMsg.cpoOrdNum_OH);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineNum_B1, convLine.cpoDtlLineNum_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineSubNum_B1, convLine.cpoDtlLineSubNum_B1);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rqstPickUpDt_B1, bizMsg.slsDt);
            if (ZYPCommonFunc.hasValue(convLine.mdseCd_BM)) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_B1, convLine.mdseCd_BM);
            } else {
                // 2016/09/29 S21_NA#14887 Mod Start
                // ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_B1, convLine.mdseCd_B1);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_B1, convLine.mdseCd_SP);
                // 2016/09/29 S21_NA#14887 Mod End
            }

            // Add Start 2017/10/16 QC#21507
            ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoSrcTpCd_B1, CPO_SRC_TP.LOAN_WORKBENCH);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefNum_B1, bizMsg.cpoOrdNum_LK);
            // Add End 2017/10/16 QC#21507

            dtlPMsg.dsCpoConfigPk_B1.clear();
            dtlPMsg.basePrcAmt_B1.clear();
            dtlPMsg.discPrcAmt_B1.clear();
            dtlPMsg.xxTotSpclPrcAmt_B1.clear();
            dtlPMsg.xxTotNetDiscAmt_B1.clear();
            dtlPMsg.netAmt_B1.clear();
            dtlPMsg.totFrtAmt_B1.clear();
            dtlPMsg.xxTotSpclChrgAmt_B1.clear();
            dtlPMsg.xxTotTaxAmt_B1.clear();
            dtlPMsg.netAmt_B2.clear();
            dtlPMsg.rtrnTotAmt_B1.clear();
            dtlPMsg.refCpoDtlLineNum_B1.clear();
            dtlPMsg.refCpoDtlLineSubNum_B1.clear();
            dtlPMsg.dplyLineRefNum_B1.clear();

            // Set Serial#, Machine Master PK
            List<NWAL1830_MCMsg> mmList = NWAL1830CommonLogic.getMachMstrListForLine(bizMsg, convLine.cpoDtlLineNum_B1.getValue(), convLine.cpoDtlLineSubNum_B1.getValue());
            for (NWAL1830_MCMsg mm : mmList) {
                if (!mmPkSet.contains(mm.svcMachMstrPk.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_B1, mm.serNum);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_B1, mm.svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_B1, mm.mdseCd);
                    mmPkSet.add(mm.svcMachMstrPk.getValue());
                    break;
                }
            }

            setPriceSummaryDtailRtrn(dtlPMsg, ordPrc);
            prePosnNum = Integer.parseInt(convLine.dsOrdPosnNum_B1.getValue());
        }
        // Set Line Number
        Map<BigDecimal, String[]> mmPkToConvLineNumNew = new HashMap<BigDecimal, String[]>(); //Map<MMPK, [0:NewLine#, 1:NewLineSub#]>
        NWAL1830CommonLogic.numberingCpoRtrnLineNum(pMsg, maxLineNum, 0, mmPkToConvLineNumNew, checkLineList);
        // QC#50556 2019/05/31 Add Start
        NWAL1830CommonLogic.setRtlWhCdAsBaseComponent(pMsg);
        // QC#50556 2019/05/31 Add End

        // [Add Price]
        // Add Checked Line 
        createCpoUpdApiParamAddPrcCalcBaseReturn(bizMsg, pMsg, ordPrc, checkLineList, mmPkToConvLineNumNew, checkLineList);

        return mmPkToConvLineNumNew;
    }

    /**
     * createCpoUpdApiParamHdr
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordHdr Map<String, Object>
     */
    private void createCpoUpdApiParamHdr(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, Map<String, Object> ordHdr) {

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.usrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, BIZ_ID);

        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, (String) ordHdr.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, (String) ordHdr.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, (String) ordHdr.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, (String) ordHdr.get("DS_ORD_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTpCd, (String) ordHdr.get("CPO_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, (String) ordHdr.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoDt, (String) ordHdr.get("CUST_ISS_PO_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, (String) ordHdr.get("SYS_SRC_CD")); // After Update
        ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, (String) ordHdr.get("CPO_SRC_TP_CD")); // After Update
        ZYPEZDItemValueSetter.setValue(pMsg.ordFuflLvlCd, (String) ordHdr.get("ORD_FUFL_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, (String) ordHdr.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, (String) ordHdr.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, (String) ordHdr.get("SHIP_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, (String) ordHdr.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.soldToCustLocCd, (String) ordHdr.get("SOLD_TO_CUST_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.adminPsnCd, getContextUserInfo().getUserId());
        // 2016/12/08 S21_NA#15934 Mod Start
        //        ZYPEZDItemValueSetter.setValue(pMsg.addRddDt, (String) ordHdr.get("ADD_RDD_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.addRddDt, bizMsg.slsDt);
        // 2016/12/08 S21_NA#15934 Mod End
        pMsg.addRsdDt.clear();
        ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, (String) ordHdr.get("ADD_SHPG_SVC_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.addDropShipFlg, (String) ordHdr.get("ADD_DROP_SHIP_FLG"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCustCd, (String) ordHdr.get("ADD_SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToLocNm, (String) ordHdr.get("ADD_SHIP_TO_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToAddlLocNm, (String) ordHdr.get("ADD_SHIP_TO_ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFirstLineAddr, (String) ordHdr.get("ADD_SHIP_TO_FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToScdLineAddr, (String) ordHdr.get("ADD_SHIP_TO_SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToThirdLineAddr, (String) ordHdr.get("ADD_SHIP_TO_THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFrthLineAddr, (String) ordHdr.get("ADD_SHIP_TO_FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtyAddr, (String) ordHdr.get("ADD_SHIP_TO_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToStCd, (String) ordHdr.get("ADD_SHIP_TO_ST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToProvNm, (String) ordHdr.get("ADD_SHIP_TO_PROV_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToPostCd, (String) ordHdr.get("ADD_SHIP_TO_POST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtryCd, (String) ordHdr.get("ADD_SHIP_TO_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCntyNm, (String) ordHdr.get("ADD_SHIP_TO_CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo01RefCmntTxt, (String) ordHdr.get("ADD_SHIP_TO_01_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo02RefCmntTxt, (String) ordHdr.get("ADD_SHIP_TO_02_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.addPmtTermCashDiscCd, (String) ordHdr.get("ADD_PMT_TERM_CASH_DISC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, (String) ordHdr.get("CARR_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.ordSgnDt, (String) ordHdr.get("ORD_SGN_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, (String) ordHdr.get("SLS_REP_TOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, (String) ordHdr.get("PRC_BASE_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, (String) ordHdr.get("PRC_CALC_DT"));

        ZYPEZDItemValueSetter.setValue(pMsg.negoDealAmt, (BigDecimal) ordHdr.get("NEGO_DEAL_AMT")); // After Update
        ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd, (String) ordHdr.get("PRC_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd, (String) ordHdr.get("FL_PRC_LIST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, (String) ordHdr.get("CSMP_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, (String) ordHdr.get("PRC_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.aquNum, (String) ordHdr.get("AQU_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptDt, (String) ordHdr.get("ORD_SRC_IMPT_DT")); // After Update 
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, (String) ordHdr.get("ORD_SRC_REF_NUM")); // After Update
        ZYPEZDItemValueSetter.setValue(pMsg.loanPerDaysAot, (BigDecimal) ordHdr.get("LOAN_PER_DAYS_AOT"));
        ZYPEZDItemValueSetter.setValue(pMsg.leaseCmpyPoNum, (String) ordHdr.get("LEASE_CMPY_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, (String) ordHdr.get("LEASE_PRCH_OPT_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.leaseTermMthAot, (BigDecimal) ordHdr.get("LEASE_TERM_MTH_AOT"));
        ZYPEZDItemValueSetter.setValue(pMsg.leasePmtFreqCd, (String) ordHdr.get("LEASE_PMT_FREQ_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.leaseTotPmtAmt, (BigDecimal) ordHdr.get("LEASE_TOT_PMT_AMT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ordLogTpCd, (String) ordHdr.get("ORD_LOG_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, (String) ordHdr.get("CSMP_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, (String) ordHdr.get("DLR_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, (String) ordHdr.get("FRT_COND_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, (String) ordHdr.get("CARR_SVC_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.spclHdlgTpCd, (String) ordHdr.get("SPCL_HDLG_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsPmtMethCd, (String) ordHdr.get("SPCL_HDLG_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.prePmtChkNum, (String) ordHdr.get("PRE_PMT_CHK_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.prePmtAmt, (BigDecimal) ordHdr.get("PRE_PMT_AMT"));
        ZYPEZDItemValueSetter.setValue(pMsg.prePmtTpCd, (String) ordHdr.get("PRE_PMT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.sendInvFlg, (String) ordHdr.get("SEND_INV_FLG"));
        ZYPEZDItemValueSetter.setValue(pMsg.invCmntTxt, getInvCmntTxt(bizMsg, (String) ordHdr.get("CPO_ORD_NUM")));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTs, (String) ordHdr.get("CPO_ORD_TS"));
        ZYPEZDItemValueSetter.setValue(pMsg.orgRqstDelyDt, (String) ordHdr.get("ORG_RQST_DELY_DT"));

    }

    /**
     * createCpoUpdApiParamAddConfig
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordConfigList List<Map<String, Object>>
     */
    private void createCpoUpdApiParamAddConfig(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> ordConfigList) {

        int configCnt = pMsg.cpoConfig.getValidCount();
        for (Map<String, Object> configMap : ordConfigList) {

            NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(configCnt);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_MODIFY); // Update After

            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsCpoConfigPk, (BigDecimal) configMap.get("DS_CPO_CONFIG_PK"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, (String) configMap.get("DS_ORD_POSN_NUM")); // Update After
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.svcConfigMstrPk, (BigDecimal) configMap.get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, (String) configMap.get("CONFIG_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, (String) configMap.get("CONFIG_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, (BigDecimal) configMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlDescTxt, (String) configMap.get("MDL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, (String) configMap.get("BILL_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, (String) configMap.get("BILL_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, (String) configMap.get("SHIP_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, (String) configMap.get("SHIP_TO_CUST_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dropShipFlg, (String) configMap.get("DROP_SHIP_FLG"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, (String) configMap.get("SHIP_TO_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, (String) configMap.get("SHIP_TO_ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, (String) configMap.get("SHIP_TO_FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, (String) configMap.get("SHIP_TO_SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, (String) configMap.get("SHIP_TO_THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, (String) configMap.get("SHIP_TO_FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, (String) configMap.get("SHIP_TO_FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, (String) configMap.get("SHIP_TO_SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, (String) configMap.get("SHIP_TO_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, (String) configMap.get("SHIP_TO_ST_CD"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, (String) configMap.get("SHIP_TO_PROV_NM"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, (String) configMap.get("SHIP_TO_CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, (String) configMap.get("SHIP_TO_POST_CD"));
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, (String) configMap.get("SHIP_TO_CTRY_CD"));
            // 2019/08/03 QC#52338 Del Start
//            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.pickSvcConfigMstrPk, (BigDecimal) configMap.get("PICK_SVC_CONFIG_MSTR_PK"));
            // 2019/08/03 QC#52338 Del End

            configCnt++;
            pMsg.cpoConfig.setValidCount(configCnt);
        }

    }

    /**
     * createCpoUpdApiParamAddConfigRetrun
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordConfigList List<Map<String, Object>>
     * @param checkedList List<NWAL1830_BCMsg>
     */
    private void createCpoUpdApiParamAddConfigRetrun(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> ordConfigList, List<NWAL1830_BCMsg> checkedList) {
        // Create Map
        List<Map<String, Object>> rtrnConfigList = new ArrayList<Map<String, Object>>();
        Set<BigDecimal> dsCpoConfigPkSet = new HashSet<BigDecimal>();
        for (NWAL1830_BCMsg lineMsg : checkedList) {
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, lineMsg))) {

                for (Map<String, Object> configMap : ordConfigList) {
                    if (lineMsg.dsCpoConfigPk_B1.getValue().compareTo((BigDecimal) configMap.get("DS_CPO_CONFIG_PK")) == 0) {
                        if (!dsCpoConfigPkSet.contains(lineMsg.dsCpoConfigPk_B1.getValue())) {
                            rtrnConfigList.add(configMap);
                            dsCpoConfigPkSet.add(lineMsg.dsCpoConfigPk_B1.getValue());
                        }
                        break;
                    }
                }
            }
        }
        createCpoUpdApiParamAddConfig(bizMsg, pMsg, rtrnConfigList);
    }

    // 2019/12/13 QC#54747 Add Start
    /**
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordLineList List<Map<String, Object>>
     */
    private void createCpoUpdApiParamAddLine(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> ordLineList, boolean partial) {
        createCpoUpdApiParamAddLine(bizMsg, pMsg, ordLineList, partial, null, null);
    }
    // 2019/12/13 QC#54747 Add End

    /**
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordLineList List<Map<String, Object>>
     */
    private void createCpoUpdApiParamAddLine(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> ordLineList, boolean partial, NWZC157001_xxPrcListPMsgArray xxPrcList, NWZC157001_xxPrcListPMsgArray xxFloorPrcList) {

        int lineCnt = pMsg.A.getValidCount();
        // Add Start 2017/10/17 QC#21507
        int getLineCnt = lineCnt;
        // Add End 2017/10/17 QC#21507
        int lineCntRtrn = pMsg.rtnDtl.getValidCount();
        for (Map<String, Object> lineMap : ordLineList) {
            if (INBD_OTBD.OUTBOUND.equals((String) lineMap.get("INBD_OTBD_CD"))) {

                NWZC150001_APMsg dtlPMsg = pMsg.A.no(lineCnt);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_SAVE); //Update After

                ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoOrdTpCd_A1, (String) lineMap.get("CPO_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.pmtTermCashDiscCd_A1, (String) lineMap.get("PMT_TERM_CASH_DISC_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, (String) lineMap.get("SLS_REP_OR_SLS_TEAM_TOC_CD"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg_A1, (String) lineMap.get("DROP_SHIP_FLG"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_A1, (String) lineMap.get("SHIP_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm_A1, (String) lineMap.get("SHIP_TO_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm_A1, (String) lineMap.get("SHIP_TO_ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr_A1, (String) lineMap.get("SHIP_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr_A1, (String) lineMap.get("SHIP_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr_A1, (String) lineMap.get("SHIP_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr_A1, (String) lineMap.get("SHIP_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr_A1, (String) lineMap.get("SHIP_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd_A1, (String) lineMap.get("SHIP_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm_A1, (String) lineMap.get("SHIP_TO_PROV_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd_A1, (String) lineMap.get("SHIP_TO_POST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd_A1, (String) lineMap.get("SHIP_TO_CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm_A1, (String) lineMap.get("SHIP_TO_CNTY_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt_A1, (String) lineMap.get("SHIP_TO_FIRST_REF_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt_A1, (String) lineMap.get("SHIP_TO_SCD_REF_CMNT_TXT"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoConfigPk_A1, (BigDecimal) lineMap.get("DS_CPO_CONFIG_PK"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, (String) lineMap.get("DS_ORD_POSN_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, (String) lineMap.get("DS_ORD_LINE_CATG_CD")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd_A1, (String) lineMap.get("ORD_LINE_SRC_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, (String) lineMap.get("RTL_WH_CD")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_A1, (String) lineMap.get("RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_A1, (String) lineMap.get("SER_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.svcMachMstrPk_A1, (BigDecimal) lineMap.get("SVC_MACH_MSTR_PK")); // Update After

                // 2019/12/13 QC#54747 Mod Start
                if (xxFloorPrcList != null){
                    String origFlPrcListCd = (String) lineMap.get("FL_PRC_LIST_CD");
                    String flPrcListCd = "";

                    for (int i = 0; i < xxFloorPrcList.getValidCount(); i++) {
                        if (origFlPrcListCd.equals(xxFloorPrcList.no(i).prcCatgCd.getValue())) {
                            flPrcListCd = origFlPrcListCd;
                            break;
                        }
                    }

                    if (S21StringUtil.isEmpty(flPrcListCd) &&(xxFloorPrcList.getValidCount() > 0)){
                        flPrcListCd = xxFloorPrcList.no(0).prcCatgCd.getValue();
                    }

                    ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, flPrcListCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, (String) lineMap.get("FL_PRC_LIST_CD"));
                }

                //ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, (String) lineMap.get("FL_PRC_LIST_CD"));
                // 2019/12/13 QC#54747 Mod End

                ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_A1, (String) lineMap.get("DPLY_LINE_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.crRebilCd_A1, (String) lineMap.get("CR_REBIL_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_A1, (String) lineMap.get("PRC_BASE_DT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_A1, (String) lineMap.get("CUST_UOM_CD"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_A1, (String) lineMap.get("CPO_DTL_LINE_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_A1, (String) lineMap.get("CPO_DTL_LINE_SUB_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, (String) lineMap.get("MDSE_CD")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, (String) lineMap.get("ORIG_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, (BigDecimal) lineMap.get("ORD_QTY"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, (BigDecimal) lineMap.get("ORD_CUST_UOM_QTY"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_A1, (String) lineMap.get("INVTY_LOC_CD")); // Update After
                // QC#50548 2019/05/28 Add Start
                // ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt_A1, (BigDecimal) lineMap.get("ENT_DEAL_NET_UNIT_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt_A1, (BigDecimal) lineMap.get("DEAL_PRC_LIST_PRC_AMT"));
                // QC#50548 2019/05/28 Add End

                PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
                prcCatgTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, (String) lineMap.get("PRC_CATG_CD"));
                prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);

                // 2016/12/07 S21_NA#15934 Mod Start
                //                ZYPEZDItemValueSetter.setValue(dtlPMsg.rddDt_A1, (String) lineMap.get("RDD_DT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.rddDt_A1, bizMsg.slsDt);
                // 2016/12/07 S21_NA#15934 Mod End

                dtlPMsg.rsdDt_A1.clear();
                dtlPMsg.shipCpltCd_A1.clear();
                ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd_A1, (String) lineMap.get("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, (String) lineMap.get("SLS_REP_OR_SLS_TEAM_TOC_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.custMdseCd_A1, (String) lineMap.get("CUST_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_A1, (String) lineMap.get("CUST_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, (BigDecimal) lineMap.get("UNIT_NET_WT"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.frtCondCd_A1, (String) lineMap.get("FRT_COND_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrNum_A1, (String) lineMap.get("DS_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrSqNum_A1, (String) lineMap.get("DS_CONTR_SQ_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoConfigPk_A1, (BigDecimal) lineMap.get("DS_CPO_CONFIG_PK")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineNum_A1, (BigDecimal) lineMap.get("DS_CPO_LINE_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineSubNum_A1, (BigDecimal) lineMap.get("DS_CPO_LINE_SUB_NUM")); // Update After

                ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineNum_A1, (String) lineMap.get("REF_CPO_DTL_LINE_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineSubNum_A1, (String) lineMap.get("REF_CPO_DTL_LINE_SUB_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_A1, (String) lineMap.get("DPLY_LINE_REF_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.splyTpCd_A1, (String) lineMap.get("SPLY_TP_CD"));
                // 2019/10/04 S21_NA#51372 Mod Start
                //ZYPEZDItemValueSetter.setValue(dtlPMsg.splyNm_A1, (String) lineMap.get("SPLY_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.prntVndNm_A1, (String) lineMap.get("PRNT_VND_NM"));
                // 2019/10/04 S21_NA#51372 Mod End
                ZYPEZDItemValueSetter.setValue(dtlPMsg.splyFirstAddr_A1, (String) lineMap.get("SPLY_FIRST_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.splyCtyAddr_A1, (String) lineMap.get("SPLY_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.splyStCd_A1, (String) lineMap.get("SPLY_ST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.splyPostCd_A1, (String) lineMap.get("SPLY_POST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpContrNum_A1, (String) lineMap.get("CSMP_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dlrRefNum_A1, (String) lineMap.get("DLR_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpPrcListCd_A1, (String) lineMap.get("CSMP_PRC_LIST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.rntlTrmnDt_A1, (String) lineMap.get("RNTL_TRMN_DT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.bllgAttrbCustAcctCd_A1, (String) lineMap.get("BLLG_ATTRB_CUST_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbNm_A1, (String) lineMap.get("FIRST_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbValTxt_A1, (String) lineMap.get("FIRST_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbNm_A1, (String) lineMap.get("SCD_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbValTxt_A1, (String) lineMap.get("SCD_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbNm_A1, (String) lineMap.get("THIRD_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbValTxt_A1, (String) lineMap.get("THIRD_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbNm_A1, (String) lineMap.get("FRTH_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbValTxt_A1, (String) lineMap.get("FRTH_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbNm_A1, (String) lineMap.get("FIFTH_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbValTxt_A1, (String) lineMap.get("FIFTH_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbNm_A1, (String) lineMap.get("SIXTH_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbValTxt_A1, (String) lineMap.get("SIXTH_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.sbstMdseCd_A1, (String) lineMap.get("SBST_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_A1, (String) lineMap.get("ORD_SRC_REF_LINE_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineSubNum_A1, (String) lineMap.get("ORD_SRC_REF_LINE_SUB_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.carrSvcLvlCd_A1, (String) lineMap.get("CARR_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.supdLockFlg_A1, (String) lineMap.get("SUPD_LOCK_FLG"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.prcListEquipConfigNum_A1, (BigDecimal) lineMap.get("PRC_LIST_EQUIP_CONFIG_NUM"));

                // 2019/12/13 QC#54747 Mod Start
                if (xxPrcList != null){
                    String origPrcListCd = (String) lineMap.get("PRC_CATG_CD");
                    String prcListCd = "";

                    for (int i = 0; i < xxPrcList.getValidCount(); i++) {
                        if (origPrcListCd.equals(xxPrcList.no(i).prcCatgCd.getValue())) {
                            prcListCd = origPrcListCd;
                            break;
                        }
                    }

                    if (S21StringUtil.isEmpty(prcListCd) &&(xxPrcList.getValidCount() > 0)){
                        prcListCd = xxPrcList.no(0).prcCatgCd.getValue();
                    }

                    ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, prcListCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, (String) lineMap.get("PRC_CATG_CD"));
                }

                //ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, (String) lineMap.get("PRC_CATG_CD"));
                // 2019/12/13 QC#54747 Mod End

                ZYPEZDItemValueSetter.setValue(dtlPMsg.loanBalQty_A1, (BigDecimal) lineMap.get("LOAN_BAL_QTY")); // Update

                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, (BigDecimal) lineMap.get("FUNC_PRC_LIST_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_A1, (BigDecimal) lineMap.get("DEAL_PRC_LIST_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcRevTrnsfAmt_A1, (BigDecimal) lineMap.get("FUNC_SVC_REV_TRNSF_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcRevTrnsfAmt_A1, (BigDecimal) lineMap.get("DEAL_SVC_REV_TRNSF_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcCostTrnsfAmt_A1, (BigDecimal) lineMap.get("FUNC_SVC_COST_TRNSF_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcCostTrnsfAmt_A1, (BigDecimal) lineMap.get("DEAL_SVC_COST_TRNSF_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManFlAdjAmt_A1, (BigDecimal) lineMap.get("FUNC_MAN_FL_ADJ_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManFlAdjAmt_A1, (BigDecimal) lineMap.get("DEAL_MAN_FL_ADJ_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManRepRevAdjAmt_A1, (BigDecimal) lineMap.get("FUNC_MAN_REP_REV_ADJ_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManRepRevAdjAmt_A1, (BigDecimal) lineMap.get("DEAL_MAN_REP_REV_ADJ_AMT"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.shopItemFlg_A1, (String) lineMap.get("SHOP_ITEM_FLG"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordUpldRefNum_A1, (String) lineMap.get("ORD_UPLD_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_A1, (String) lineMap.get("BASE_CMPT_FLG"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk_A1, (BigDecimal) lineMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.pickSvcConfigMstrPk_A1, (BigDecimal) lineMap.get("PICK_SVC_CONFIG_MSTR_PK"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoOrdNum_A1, (String) lineMap.get("ORIG_CPO_ORD_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineNum_A1, (String) lineMap.get("ORIG_CPO_DTL_LINE_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.origCpoDtlLineSubNum_A1, (String) lineMap.get("ORIG_CPO_DTL_LINE_SUB_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcUnitFlPrcAmt_A1, (BigDecimal) lineMap.get("FUNC_UNIT_FL_PRC_AMT")); // 2018/01/12 S21_NA#22372 ADD

                lineCnt++;
                pMsg.A.setValidCount(lineCnt);

                if (!partial) {
                    continue;
                }

                // Add Start 2018/08/21 QC#27489
                int qty = 0;
                String ordLineStsNm = (String) lineMap.get("ORD_LINE_STS_NM");
                String instlBaseCtrlFlg = (String) lineMap.get("INSTL_BASE_CTRL_FLG");

                if (ORD_LINE_STS_NM_CLOSED.equals(ordLineStsNm) && ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                    qty = ((BigDecimal) lineMap.get("ORD_QTY")).intValue();
                } else {
                    qty = ((BigDecimal) lineMap.get("LOAN_BAL_QTY")).intValue();
                }
                // Add End 2018/08/21 QC#27489

                // Mod Start 2018/08/21 QC#27489
                //if (1 < ((BigDecimal) lineMap.get("LOAN_BAL_QTY")).intValue()) {
                if (1 < qty) {
                    // Mod End 2018/08/21 QC#27489
                    NWZC150001_APMsg dtlPMsgCopy = dtlPMsg;
                    // Del Start 2018/08/21 QC#27489
                    //int qty = ((BigDecimal) lineMap.get("LOAN_BAL_QTY")).intValue();
                    // Del End 2018/08/21 QC#27489
                    int cnt = 1;

                    while (cnt < qty) {
                        dtlPMsg = pMsg.A.no(lineCnt);
                        EZDMsg.copy(dtlPMsgCopy, null, dtlPMsg, null);
                        lineCnt++;
                        pMsg.A.setValidCount(lineCnt);

                        cnt++;
                    }
                }

            } else {

                NWZC150001_rtnDtlPMsg dtlPMsg = pMsg.rtnDtl.no(lineCntRtrn);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_B1, NWZC150001Constant.RQST_TP_DTL_SAVE); //Update After

                ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnRsnCd_B1, (String) lineMap.get("RTRN_RSN_CD")); //Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_B1, (String) lineMap.get("SLS_REP_OR_SLS_TEAM_TOC_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg_B1, (String) lineMap.get("DROP_SHIP_FLG"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_B1, (String) lineMap.get("SHIP_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm_B1, (String) lineMap.get("SHIP_TO_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm_B1, (String) lineMap.get("SHIP_TO_ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr_B1, (String) lineMap.get("SHIP_TO_FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr_B1, (String) lineMap.get("SHIP_TO_SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr_B1, (String) lineMap.get("SHIP_TO_THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr_B1, (String) lineMap.get("SHIP_TO_FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr_B1, (String) lineMap.get("SHIP_TO_CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd_B1, (String) lineMap.get("SHIP_TO_ST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm_B1, (String) lineMap.get("SHIP_TO_PROV_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd_B1, (String) lineMap.get("SHIP_TO_POST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd_B1, (String) lineMap.get("SHIP_TO_CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm_B1, (String) lineMap.get("SHIP_TO_CNTY_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt_B1, (String) lineMap.get("SHIP_TO_FIRST_REF_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt_B1, (String) lineMap.get("SHIP_TO_SCD_REF_CMNT_TXT"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoConfigPk_B1, (BigDecimal) lineMap.get("DS_CPO_CONFIG_PK"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_B1, (String) lineMap.get("DS_ORD_POSN_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_B1, (String) lineMap.get("DS_ORD_LINE_CATG_CD")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd_B1, (String) lineMap.get("ORD_LINE_SRC_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_B1, (String) lineMap.get("RTL_WH_CD")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_B1, (String) lineMap.get("RTL_SWH_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_B1, (String) lineMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_B1, (String) lineMap.get("FL_PRC_LIST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotTaxAmt_B1, (BigDecimal) lineMap.get("LINE_DEAL_TAX_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_B1, (String) lineMap.get("DPLY_LINE_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.crRebilCd_B1, (String) lineMap.get("CR_REBIL_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_B1, (String) lineMap.get("PRC_BASE_DT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_B1, (String) lineMap.get("CUST_UOM_CD"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_B1, (String) lineMap.get("CPO_DTL_LINE_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_B1, (String) lineMap.get("CPO_DTL_LINE_SUB_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_B1, (String) lineMap.get("MDSE_CD")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_B1, (String) lineMap.get("ORIG_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_B1, (BigDecimal) lineMap.get("ORD_QTY")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_B1, (BigDecimal) lineMap.get("ORD_CUST_UOM_QTY"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_B1, (String) lineMap.get("INVTY_LOC_CD")); // Update After
                // QC#50548 2019/05/28 Add Start
                // ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt_B1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt_B1, (BigDecimal) lineMap.get("FUNC_PRC_LIST_PRC_AMT"));
                // QC#50548 2019/05/28 Add End

                PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
                prcCatgTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(prcCatgTMsg.prcCatgCd, (String) lineMap.get("PRC_CATG_CD"));
                prcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatgTMsg);

                ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd_B1, (String) lineMap.get("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_B1, (String) lineMap.get("SLS_REP_OR_SLS_TEAM_TOC_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.custMdseCd_B1, (String) lineMap.get("CUST_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_B1, (String) lineMap.get("CUST_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.taxFlg_B1, (String) lineMap.get("TAX_FLG")); // Update After

                ZYPEZDItemValueSetter.setValue(dtlPMsg.lineNetWt_B1, (BigDecimal) lineMap.get("UNIT_NET_WT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrNum_B1, (String) lineMap.get("DS_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrSqNum_B1, (String) lineMap.get("DS_CONTR_SQ_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoConfigPk_B1, (BigDecimal) lineMap.get("DS_CPO_CONFIG_PK")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineNum_B1, (BigDecimal) lineMap.get("DS_CPO_LINE_NUM")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoLineSubNum_B1, (BigDecimal) lineMap.get("DS_CPO_LINE_SUB_NUM")); // Update After

                ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineNum_B1, (String) lineMap.get("REF_CPO_DTL_LINE_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineSubNum_B1, (String) lineMap.get("REF_CPO_DTL_LINE_SUB_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_B1, (String) lineMap.get("DPLY_LINE_REF_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpContrNum_B1, (String) lineMap.get("CSMP_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dlrRefNum_B1, (String) lineMap.get("DLR_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpPrcListCd_B1, (String) lineMap.get("CSMP_PRC_LIST_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.bllgAttrbCustAcctCd_B1, (String) lineMap.get("BLLG_ATTRB_CUST_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbNm_B1, (String) lineMap.get("FIRST_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbValTxt_B1, (String) lineMap.get("FIRST_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbNm_B1, (String) lineMap.get("SCD_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbValTxt_B1, (String) lineMap.get("SCD_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbNm_B1, (String) lineMap.get("THIRD_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbValTxt_B1, (String) lineMap.get("THIRD_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbNm_B1, (String) lineMap.get("FRTH_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbValTxt_B1, (String) lineMap.get("FRTH_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbNm_B1, (String) lineMap.get("FIFTH_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbValTxt_B1, (String) lineMap.get("FIFTH_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbNm_B1, (String) lineMap.get("SIXTH_BLLG_ATTRB_NM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbValTxt_B1, (String) lineMap.get("SIXTH_BLLG_ATTRB_VAL_TXT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_B1, (String) lineMap.get("ORD_SRC_REF_LINE_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineSubNum_B1, (String) lineMap.get("ORD_SRC_REF_LINE_SUB_NUM")); // Update
                ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_B1, (String) lineMap.get("PRC_CATG_CD"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_B1, (BigDecimal) lineMap.get("FUNC_PRC_LIST_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_B1, (BigDecimal) lineMap.get("DEAL_PRC_LIST_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcRevTrnsfAmt_B1, (BigDecimal) lineMap.get("FUNC_SVC_REV_TRNSF_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcRevTrnsfAmt_B1, (BigDecimal) lineMap.get("DEAL_SVC_REV_TRNSF_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcCostTrnsfAmt_B1, (BigDecimal) lineMap.get("FUNC_SVC_COST_TRNSF_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcCostTrnsfAmt_B1, (BigDecimal) lineMap.get("DEAL_SVC_COST_TRNSF_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManFlAdjAmt_B1, (BigDecimal) lineMap.get("FUNC_MAN_FL_ADJ_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManFlAdjAmt_B1, (BigDecimal) lineMap.get("DEAL_MAN_FL_ADJ_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManRepRevAdjAmt_B1, (BigDecimal) lineMap.get("FUNC_MAN_REP_REV_ADJ_AMT"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManRepRevAdjAmt_B1, (BigDecimal) lineMap.get("DEAL_MAN_REP_REV_ADJ_AMT"));

                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordUpldRefNum_B1, (String) lineMap.get("ORD_UPLD_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_B1, (String) lineMap.get("BASE_CMPT_FLG")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk_B1, (BigDecimal) lineMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(dtlPMsg.rtrnQty_B1, (BigDecimal) lineMap.get("RTRN_QTY")); // Update After
                ZYPEZDItemValueSetter.setValue(dtlPMsg.funcUnitFlPrcAmt_B1, (BigDecimal) lineMap.get("FUNC_UNIT_FL_PRC_AMT")); // 2018/01/12 S21_NA#22372 ADD
                
                lineCntRtrn++;
                pMsg.rtnDtl.setValidCount(lineCntRtrn);

                if (!partial) {
                    continue;
                }

                // Add Start 2018/08/21 QC#27489
                int qty = 0;
                String ordLineStsNm = (String) lineMap.get("ORD_LINE_STS_NM");
                String instlBaseCtrlFlg = (String) lineMap.get("INSTL_BASE_CTRL_FLG");

                if (ORD_LINE_STS_NM_CLOSED.equals(ordLineStsNm) && ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                    qty = ((BigDecimal) lineMap.get("ORD_QTY")).intValue();
                } else {
                    qty = ((BigDecimal) lineMap.get("LOAN_BAL_QTY")).intValue();
                }
                // Add End 2018/08/21 QC#27489

                // Mod Start 2018/08/21 QC#27489
                //if (1 < ((BigDecimal) lineMap.get("LOAN_BAL_QTY")).intValue()) {
                if (1 < qty) {
                    // Mod End 2018/08/21 QC#27489
                    NWZC150001_rtnDtlPMsg dtlPMsgCopy = dtlPMsg;
                    // Del Start 2018/08/21 QC#27489
                    //int qty = ((BigDecimal) lineMap.get("LOAN_BAL_QTY")).intValue();
                    // Del End 2018/08/21 QC#27489
                    int cnt = 1;
                    while (cnt < qty) {
                        dtlPMsg = pMsg.rtnDtl.no(lineCntRtrn);
                        EZDMsg.copy(dtlPMsgCopy, null, dtlPMsg, null);
                        lineCntRtrn++;
                        pMsg.rtnDtl.setValidCount(lineCntRtrn);

                        cnt++;
                    }
                }
            }
        }
    }

    /**
     * createCpoUpdApiParamAddLineReturn
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordLineList List<Map<String, Object>>
     * @param checkedList List<NWAL1830_BCMsg>
     */
    private void createCpoUpdApiParamAddLineReturn(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> ordLineList, List<NWAL1830_BCMsg> checkedList) {

        // Create List
        List<Map<String, Object>> rtrnLineList = new ArrayList<Map<String, Object>>();
        for (NWAL1830_BCMsg lineMsg : checkedList) {
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, lineMsg))) {

                for (Map<String, Object> inLineMap : ordLineList) {
                    if (lineMsg.cpoDtlLineNum_B1.getValue().equals((String) inLineMap.get("CPO_DTL_LINE_NUM")) && lineMsg.cpoDtlLineSubNum_B1.getValue().equals((String) inLineMap.get("CPO_DTL_LINE_SUB_NUM"))) {
                        inLineMap.put("INBD_OTBD_CD", INBD_OTBD.INBOUND);
                        rtrnLineList.add(inLineMap);
                        break;
                    }
                }
            }
        }
        createCpoUpdApiParamAddLine(bizMsg, pMsg, rtrnLineList, true);
    }

    /**
     * createCpoUpdApiParamAddPrcCalcBase
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordPrcList List<Map<String, Object>>
     * @param mmPkToConvLineNumNew Map<BigDecimal, String[]>
     * @param checkLineList List<NWAL1830_BCMsg>
     */
    private void createCpoUpdApiParamAddPrcCalcBase(//
            NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> ordPrcList, Map<BigDecimal, String[]> mmPkToConvLineNumNew, List<NWAL1830_BCMsg> checkLineList) {
        List<List<Map<String, Object>>> prcList = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> prcGroup = null;
        String lineNum = "000";
        for (Map<String, Object> prcMap : ordPrcList) {
            if (!lineNum.equals((String) prcMap.get("CPO_DTL_LINE_NUM"))) {
                if (prcGroup != null) {
                    prcList.add(prcGroup);
                }
                prcGroup = new ArrayList<Map<String, Object>>();
                prcGroup.add(prcMap);
            } else {
                prcGroup.add(prcMap);
            }
            lineNum = (String) prcMap.get("CPO_DTL_LINE_NUM");
        }
        if (prcGroup != null) {
            prcList.add(prcGroup);
        }

        for (List<Map<String, Object>> group : prcList) {
            int qty = 0;
            Map<String, Object> prcMapFirst = group.get(0);
            List<NWAL1830_MCMsg> mmList = NWAL1830CommonLogic.getMachMstrListForLine(bizMsg, (String) prcMapFirst.get("CPO_DTL_LINE_NUM"), (String) prcMapFirst.get("CPO_DTL_LINE_SUB_NUM"));
            for (Map<String, Object> prcMap : group) {
                // Add Start 2018/08/21 QC#27489
                //qty = ((BigDecimal) prcMap.get("ORD_QTY")).intValue();
                String ordLineStsNm = (String) prcMap.get("ORD_LINE_STS_NM");
                String instlBaseCtrlFlg = (String) prcMap.get("INSTL_BASE_CTRL_FLG");

                if (ORD_LINE_STS_NM_CLOSED.equals(ordLineStsNm) && ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                    qty = ((BigDecimal) prcMap.get("ORD_QTY")).intValue();
                } else {
                    qty = ((BigDecimal) prcMap.get("LOAN_BAL_QTY")).intValue();

                    if (qty == 0) {
                        qty = 1;
                    }
                }
                // Add End 2018/08/21 QC#27489

                createCpoUpdApiParamAddPrcCalcBase(bizMsg, pMsg, prcMap);

                // Update
                if (INBD_OTBD.OUTBOUND.equals(prcMap.get("INBD_OTBD_CD"))) {
                    NWZC150001_priceListPMsg priceListPMsg = pMsg.priceList.no(pMsg.priceList.getValidCount() - 1);
                    // Get New Line Num
                    BigDecimal mmPk = null;
                    if (mmList != null && 0 < mmList.size()) {
                        mmPk = mmList.get(0).svcMachMstrPk.getValue();
                    } else {
                        NWAL1830_BCMsg convLine = NWAL1830CommonLogic.getOrigLine(priceListPMsg.cpoDtlLineNum.getValue(), priceListPMsg.cpoDtlLineSubNum.getValue(), checkLineList);
                        mmPk = convLine.xxRowNum_B1.getValue();
                    }
                    String[] lineNmNew = NWAL1830CommonLogic.getNewLineNum(mmPk, mmPkToConvLineNumNew);
                    updateValCpoUpdApiParamAddPrcCalcBase(priceListPMsg, lineNmNew[0], lineNmNew[1]);

                } else {
                    NWZC150001_rtnPriceListPMsg priceListPMsg = pMsg.rtnPriceList.no(pMsg.rtnPriceList.getValidCount() - 1);
                    // Get New Line Num
                    BigDecimal mmPk = null;
                    if (mmList != null && 0 < mmList.size()) {
                        mmPk = mmList.get(0).svcMachMstrPk.getValue();
                    } else {
                        NWAL1830_BCMsg convLine = NWAL1830CommonLogic.getOrigLine(priceListPMsg.cpoDtlLineNum.getValue(), priceListPMsg.cpoDtlLineSubNum.getValue(), checkLineList);
                        mmPk = convLine.xxRowNum_B1.getValue();
                    }
                    String[] lineNmNew = NWAL1830CommonLogic.getNewLineNum(mmPk, mmPkToConvLineNumNew);
                    updateValCpoUpdApiParamAddPrcCalcBaseRtrn(priceListPMsg, lineNmNew[0], lineNmNew[1]);
                }
            }

            if (1 < qty) {
                int cnt = 1;
                while (cnt < qty) {
                    for (Map<String, Object> prcMap : group) {
                        createCpoUpdApiParamAddPrcCalcBase(bizMsg, pMsg, prcMap);

                        // Update
                        if (cnt < mmList.size()) {
                            // Update
                            if (INBD_OTBD.OUTBOUND.equals(prcMap.get("INBD_OTBD_CD"))) {
                                NWZC150001_priceListPMsg priceListPMsg = pMsg.priceList.no(pMsg.priceList.getValidCount() - 1);
                                // Get New Line Num
                                BigDecimal mmPk = null;
                                if (mmList != null && 0 < mmList.size()) {
                                    mmPk = mmList.get(cnt).svcMachMstrPk.getValue();
                                } else {
                                    NWAL1830_BCMsg convLine = NWAL1830CommonLogic.getOrigLine(priceListPMsg.cpoDtlLineNum.getValue(), priceListPMsg.cpoDtlLineSubNum.getValue(), checkLineList);
                                    mmPk = convLine.xxRowNum_B1.getValue();
                                }
                                String[] lineNmNew = NWAL1830CommonLogic.getNewLineNum(mmPk, mmPkToConvLineNumNew);
                                updateValCpoUpdApiParamAddPrcCalcBase(priceListPMsg, lineNmNew[0], lineNmNew[1]);

                            } else {
                                NWZC150001_rtnPriceListPMsg priceListPMsg = pMsg.rtnPriceList.no(pMsg.rtnPriceList.getValidCount() - 1);
                                // Get New Line Num
                                BigDecimal mmPk = null;
                                if (mmList != null && 0 < mmList.size()) {
                                    mmPk = mmList.get(cnt).svcMachMstrPk.getValue();
                                } else {
                                    NWAL1830_BCMsg convLine = NWAL1830CommonLogic.getOrigLine(priceListPMsg.cpoDtlLineNum.getValue(), priceListPMsg.cpoDtlLineSubNum.getValue(), checkLineList);
                                    mmPk = convLine.xxRowNum_B1.getValue();
                                }
                                String[] lineNmNew = NWAL1830CommonLogic.getNewLineNum(mmPk, mmPkToConvLineNumNew);
                                updateValCpoUpdApiParamAddPrcCalcBaseRtrn(priceListPMsg, lineNmNew[0], lineNmNew[1]);
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
    }

    /**
     * updateValCpoUpdApiParamAddPrcCalcBase
     * @param priceListPMsg
     * @param lineNum
     * @param lineSubNum
     */
    private void updateValCpoUpdApiParamAddPrcCalcBase(NWZC150001_priceListPMsg priceListPMsg, String lineNum, String lineSubNum) {
        priceListPMsg.ordPrcCalcBasePk.clear();
        ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, lineNum);
        ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, lineSubNum);
        ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, priceListPMsg.unitPrcAmt);
    }

    /**
     * updateValCpoUpdApiParamAddPrcCalcBaseRtrn
     * @param priceListPMsg
     * @param lineNum
     * @param lineSubNum
     */
    private void updateValCpoUpdApiParamAddPrcCalcBaseRtrn(NWZC150001_rtnPriceListPMsg priceListPMsg, String lineNum, String lineSubNum) {
        priceListPMsg.ordPrcCalcBasePk.clear();
        ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, lineNum);
        ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, lineSubNum);

        // QC#22631 2017/11/20 Mod Start
        // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, BigDecimal.ZERO);
        // Mod Start 2018/08/21 QC#27489
        //ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, priceListPMsg.calcPrcAmtRate.getValue().negate());
        ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, priceListPMsg.unitPrcAmt.getValue().negate());
        // Mod End 2018/08/21 QC#27489
        // QC#22631 2017/11/20 Mod End

    }

    /**
     * createCpoUpdApiParamAddPrcCalcBase
     * @param bizMsg
     * @param pMsg
     * @param prcMap
     */
    private void createCpoUpdApiParamAddPrcCalcBase(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, Map<String, Object> prcMap) {

        if (INBD_OTBD.OUTBOUND.equals(prcMap.get("INBD_OTBD_CD"))) {
            NWZC150001_priceListPMsg priceListPMsg = pMsg.priceList.no(pMsg.priceList.getValidCount());

            ZYPEZDItemValueSetter.setValue(priceListPMsg.ordPrcCalcBasePk, (BigDecimal) prcMap.get("ORD_PRC_CALC_BASE_PK")); //Update After
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, (String) prcMap.get("CPO_DTL_LINE_NUM")); //Update After
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, (String) prcMap.get("CPO_DTL_LINE_SUB_NUM")); //Update After
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, (String) prcMap.get("PRC_COND_TP_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, (String) prcMap.get("PRC_DTL_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, (String) prcMap.get("PRC_JRNL_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, (String) prcMap.get("PRC_COND_MAN_ENTRY_FLG"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, (String) prcMap.get("PRC_COND_MAN_ADD_FLG"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, (String) prcMap.get("PRC_COND_MAN_DEL_FLG"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, (String) prcMap.get("PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, (String) prcMap.get("PRC_COND_UNIT_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, (String) prcMap.get("PRC_CALC_METH_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, (BigDecimal) prcMap.get("AUTO_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, (BigDecimal) prcMap.get("MAX_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, (BigDecimal) prcMap.get("MIN_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, (BigDecimal) prcMap.get("CALC_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, (BigDecimal) prcMap.get("UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, (BigDecimal) prcMap.get("DS_MDSE_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, (BigDecimal) prcMap.get("SPEC_COND_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, (BigDecimal) prcMap.get("FRT_PER_WT_PK"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, (String) prcMap.get("AUTO_PRC_CCY_CD"));

            // QC#50548 2019/05/28 Add Start
            // Add Start 2018/08/21 QC#27489
            //boolean changeFlag = false;
            //
            //if (PRC_COND_TP.BASE_PRICE.equals((String) prcMap.get("PRC_COND_TP_CD"))) {
            //    changeFlag = false;
            //} else if (PRC_COND_TP.MANUAL_PRICE.equals((String) prcMap.get("PRC_COND_TP_CD"))) {
            //    changeFlag = false;
            //} else if (PRC_DTL_GRP.TAX.equals((String) prcMap.get("PRC_DTL_GRP_CD"))) {
            //    changeFlag = false;
            //} else {
            //    if (!ZYPCommonFunc.hasValue((BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"))) {
            //        changeFlag = true;
            //    } else {
            //        changeFlag = false;
            //    }
            //}
            //
            //if (changeFlag) {
            //    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            //    ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("AUTO_PRC_AMT_RATE"));
            //} else {
            //    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, (String) prcMap.get("PRC_COND_MAN_ENTRY_FLG"));
            //    ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"));
            //}
            // Add End 2018/08/21 QC#27489
            // QC#50548 2019/05/28 Add End

            pMsg.priceList.setValidCount(pMsg.priceList.getValidCount() + 1);

        } else {
            NWZC150001_rtnPriceListPMsg priceListPMsg = pMsg.rtnPriceList.no(pMsg.rtnPriceList.getValidCount());

            ZYPEZDItemValueSetter.setValue(priceListPMsg.ordPrcCalcBasePk, (BigDecimal) prcMap.get("ORD_PRC_CALC_BASE_PK")); //Update After
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, (String) prcMap.get("CPO_DTL_LINE_NUM")); //Update After
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, (String) prcMap.get("CPO_DTL_LINE_SUB_NUM")); //Update After
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, (String) prcMap.get("PRC_COND_TP_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, (String) prcMap.get("PRC_DTL_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, (String) prcMap.get("PRC_JRNL_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, (String) prcMap.get("PRC_COND_MAN_ENTRY_FLG"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, (String) prcMap.get("PRC_COND_MAN_ADD_FLG"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, (String) prcMap.get("PRC_COND_MAN_DEL_FLG"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, (String) prcMap.get("PKG_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, (String) prcMap.get("PRC_COND_UNIT_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, (String) prcMap.get("PRC_CALC_METH_CD"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, (BigDecimal) prcMap.get("AUTO_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, (BigDecimal) prcMap.get("MAX_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, (BigDecimal) prcMap.get("MIN_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, (BigDecimal) prcMap.get("CALC_PRC_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, (BigDecimal) prcMap.get("UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, (BigDecimal) prcMap.get("DS_MDSE_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, (BigDecimal) prcMap.get("SPEC_COND_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, (BigDecimal) prcMap.get("FRT_PER_WT_PK"));
            ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, (String) prcMap.get("AUTO_PRC_CCY_CD"));

            // QC#50548 2019/05/28 Add Start
            // Add Start 2018/08/21 QC#27489
            //boolean changeFlag = false;
            //
            //if (PRC_COND_TP.BASE_PRICE.equals((String) prcMap.get("PRC_COND_TP_CD"))) {
            //    changeFlag = false;
            //} else if (PRC_COND_TP.MANUAL_PRICE.equals((String) prcMap.get("PRC_COND_TP_CD"))) {
            //    changeFlag = false;
            //} else if (PRC_DTL_GRP.TAX.equals((String) prcMap.get("PRC_DTL_GRP_CD"))) {
            //    changeFlag = false;
            //} else {
            //    if (!ZYPCommonFunc.hasValue((BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"))) {
            //        changeFlag = true;
            //    } else {
            //        changeFlag = false;
            //    }
            //}
            //
            //if (changeFlag) {
            //    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            //    ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("AUTO_PRC_AMT_RATE"));
            //} else {
            //    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, (String) prcMap.get("PRC_COND_MAN_ENTRY_FLG"));
            //    ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"));
            //}
            // Add End 2018/08/21 QC#27489
            // QC#50548 2019/05/28 Add End

            pMsg.rtnPriceList.setValidCount(pMsg.rtnPriceList.getValidCount() + 1);

        }
    }

    /**
     * createCpoUpdApiParamAddPrcCalcBase
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordPrcList List<Map<String, Object>>
     */
    private void createCpoUpdApiParamAddPrcCalcBase(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> ordPrcList) {

        int prcCnt = pMsg.priceList.getValidCount();
        int prcCntRtrn = pMsg.rtnPriceList.getValidCount();
        // Del Start 2018/08/21 QC#27489
        //int prcCntRtrnAdd = pMsg.rtnPriceList.getValidCount();
        // Del End 2018/08/21 QC#27489
        for (Map<String, Object> prcMap : ordPrcList) {

            if (INBD_OTBD.OUTBOUND.equals(prcMap.get("INBD_OTBD_CD"))) {
                NWZC150001_priceListPMsg priceListPMsg = pMsg.priceList.no(prcCnt);

                ZYPEZDItemValueSetter.setValue(priceListPMsg.ordPrcCalcBasePk, (BigDecimal) prcMap.get("ORD_PRC_CALC_BASE_PK")); //Update After
                ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, (String) prcMap.get("CPO_DTL_LINE_NUM")); //Update After
                ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, (String) prcMap.get("CPO_DTL_LINE_SUB_NUM")); //Update After
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, (String) prcMap.get("PRC_COND_TP_CD"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, (String) prcMap.get("PRC_DTL_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, (String) prcMap.get("PRC_JRNL_GRP_CD"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, (String) prcMap.get("PRC_COND_MAN_ENTRY_FLG"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, (String) prcMap.get("PRC_COND_MAN_ADD_FLG"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, (String) prcMap.get("PRC_COND_MAN_DEL_FLG"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, (String) prcMap.get("PKG_UOM_CD"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, (String) prcMap.get("PRC_COND_UNIT_CD"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, (String) prcMap.get("PRC_CALC_METH_CD"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, (BigDecimal) prcMap.get("AUTO_PRC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, (BigDecimal) prcMap.get("MAX_PRC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, (BigDecimal) prcMap.get("MIN_PRC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, (BigDecimal) prcMap.get("CALC_PRC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, (BigDecimal) prcMap.get("UNIT_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, (BigDecimal) prcMap.get("DS_MDSE_PRC_PK"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, (BigDecimal) prcMap.get("SPEC_COND_PRC_PK"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, (BigDecimal) prcMap.get("FRT_PER_WT_PK"));
                ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, (String) prcMap.get("AUTO_PRC_CCY_CD"));

                prcCnt++;
                pMsg.priceList.setValidCount(prcCnt);

            } else {
                NWZC150001_rtnPriceListPMsg priceListPMsg = pMsg.rtnPriceList.no(prcCntRtrn);

                // QC#22631 2017/11/20 Add Start
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.ordPrcCalcBasePk, (BigDecimal) prcMap.get("ORD_PRC_CALC_BASE_PK")); //Update After
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, (String) prcMap.get("CPO_DTL_LINE_NUM")); //Update After
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, (String) prcMap.get("CPO_DTL_LINE_SUB_NUM")); //Update After
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, (String) prcMap.get("PRC_COND_TP_CD"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, (String) prcMap.get("PRC_DTL_GRP_CD"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, (String) prcMap.get("PRC_JRNL_GRP_CD"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, (String) prcMap.get("PRC_COND_MAN_ENTRY_FLG"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, (String) prcMap.get("PRC_COND_MAN_ADD_FLG"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, (String) prcMap.get("PRC_COND_MAN_DEL_FLG"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, (String) prcMap.get("PKG_UOM_CD"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, (String) prcMap.get("PRC_COND_UNIT_CD"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, (String) prcMap.get("PRC_CALC_METH_CD"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, (BigDecimal) prcMap.get("AUTO_PRC_AMT_RATE"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, (BigDecimal) prcMap.get("MAX_PRC_AMT_RATE"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, (BigDecimal) prcMap.get("MIN_PRC_AMT_RATE"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, (BigDecimal) prcMap.get("CALC_PRC_AMT_RATE"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, (BigDecimal) prcMap.get("UNIT_PRC_AMT"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, (BigDecimal) prcMap.get("DS_MDSE_PRC_PK"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, (BigDecimal) prcMap.get("SPEC_COND_PRC_PK"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, (BigDecimal) prcMap.get("FRT_PER_WT_PK"));
                // ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, (String) prcMap.get("AUTO_PRC_CCY_CD"));
                //
                //
                // prcCntRtrn++;
                // pMsg.rtnPriceList.setValidCount(prcCntRtrn);

                // Del Start 2018/08/21 QC#27489
                //if (PRC_DTL_GRP.BASE_PRICE.equals((String) prcMap.get("PRC_DTL_GRP_CD"))) {
                    // Del End 2018/08/21 QC#27489
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.ordPrcCalcBasePk, (BigDecimal) prcMap.get("ORD_PRC_CALC_BASE_PK"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, (String) prcMap.get("CPO_DTL_LINE_NUM"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, (String) prcMap.get("CPO_DTL_LINE_SUB_NUM"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, (String) prcMap.get("PRC_COND_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, (String) prcMap.get("PRC_DTL_GRP_CD"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, (String) prcMap.get("PRC_JRNL_GRP_CD"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, (String) prcMap.get("PRC_COND_MAN_ENTRY_FLG"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, (String) prcMap.get("PRC_COND_MAN_ADD_FLG"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, (String) prcMap.get("PRC_COND_MAN_DEL_FLG"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, (String) prcMap.get("PKG_UOM_CD"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, (String) prcMap.get("PRC_COND_UNIT_CD"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, (String) prcMap.get("PRC_CALC_METH_CD"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, (BigDecimal) prcMap.get("AUTO_PRC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, (BigDecimal) prcMap.get("MAX_PRC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, (BigDecimal) prcMap.get("MIN_PRC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, (BigDecimal) prcMap.get("CALC_PRC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, (BigDecimal) prcMap.get("UNIT_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, (BigDecimal) prcMap.get("DS_MDSE_PRC_PK"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, (BigDecimal) prcMap.get("SPEC_COND_PRC_PK"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, (BigDecimal) prcMap.get("FRT_PER_WT_PK"));
                    ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, (String) prcMap.get("AUTO_PRC_CCY_CD"));
                    // Mod Start 2018/08/21 QC#27489
                    //prcCntRtrnAdd++;
                    //pMsg.rtnPriceList.setValidCount(prcCntRtrnAdd);
                    prcCntRtrn++;
                    pMsg.rtnPriceList.setValidCount(prcCntRtrn);
                    // Mod End 2018/08/21 QC#27489

                    // Del Start 2018/08/21 QC#27489
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.ordPrcCalcBasePk, (BigDecimal) prcMap.get("ORD_PRC_CALC_BASE_PK"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, (String) prcMap.get("CPO_DTL_LINE_NUM"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, (String) prcMap.get("CPO_DTL_LINE_SUB_NUM"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, PRC_COND_TP.MANUAL_PRICE);
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, (String) getPrcCondTpDescTxt(bizMsg.glblCmpyCd.getValue(), PRC_COND_TP.MANUAL_PRICE));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, PRC_DTL_GRP.DISCOUNT);
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, (String) prcMap.get("PRC_COND_MAN_ENTRY_FLG"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, (String) prcMap.get("PRC_COND_MAN_ADD_FLG"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, (String) prcMap.get("PRC_COND_MAN_DEL_FLG"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, (String) prcMap.get("PKG_UOM_CD"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, (String) prcMap.get("PRC_COND_UNIT_CD"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, (String) prcMap.get("PRC_CALC_METH_CD"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, (BigDecimal) prcMap.get("AUTO_PRC_AMT_RATE"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, (BigDecimal) prcMap.get("MAX_PRC_AMT_RATE"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, (BigDecimal) prcMap.get("MIN_PRC_AMT_RATE"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, (BigDecimal) prcMap.get("MAN_PRC_AMT_RATE"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, (BigDecimal) prcMap.get("CALC_PRC_AMT_RATE"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, (BigDecimal) prcMap.get("UNIT_PRC_AMT"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, (BigDecimal) prcMap.get("DS_MDSE_PRC_PK"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, (BigDecimal) getSpecCondPrcPk(bizMsg.glblCmpyCd.getValue(), PRC_COND_TP.MANUAL_PRICE));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, (BigDecimal) prcMap.get("FRT_PER_WT_PK"));
                    //ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, (String) prcMap.get("AUTO_PRC_CCY_CD"));
                    //prcCntRtrnAdd++;
                    //pMsg.rtnPriceList.setValidCount(prcCntRtrnAdd);
                //}
                //prcCntRtrn++;
                // Del End 2018/08/21 QC#27489
                // QC#22631 2017/11/20 Add End

            }
        }

    }

    /**
     * createCpoUpdApiParamAddPrcCalcBaseReturn
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param ordPrcList List<Map<String, Object>>
     * @param checkedList List<NWAL1830_BCMsg>
     */
    private void createCpoUpdApiParamAddPrcCalcBaseReturn(//
            NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> ordPrcList, List<NWAL1830_BCMsg> checkedList, Map<BigDecimal, String[]> mmPkToConvLineNumNew, List<NWAL1830_BCMsg> checkLineList) {
        // Create List
        List<Map<String, Object>> rtrnPrcList = new ArrayList<Map<String, Object>>();
        for (NWAL1830_BCMsg lineMsg : checkedList) {
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(NWAL1830CommonLogic.getActionInbdOutbd(bizMsg, lineMsg))) {

                for (Map<String, Object> inLineMap : ordPrcList) {
                    if (lineMsg.cpoDtlLineNum_B1.getValue().equals((String) inLineMap.get("CPO_DTL_LINE_NUM")) && lineMsg.cpoDtlLineSubNum_B1.getValue().equals((String) inLineMap.get("CPO_DTL_LINE_SUB_NUM"))) {
                        inLineMap.put("INBD_OTBD_CD", INBD_OTBD.INBOUND);
                        rtrnPrcList.add(inLineMap);
                    }
                }
            }
        }
        createCpoUpdApiParamAddPrcCalcBase(bizMsg, pMsg, rtrnPrcList, mmPkToConvLineNumNew, checkLineList);
    }

    /**
     * createCpoUpdApiParamAddSlsCr
     * @param bizMsg
     * @param pMsg
     * @param slsCrList
     */
    private void createCpoUpdApiParamAddSlsCr(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> slsCrList) {

        for (Map<String, Object> slsCrMap : slsCrList) {
            int cnt = pMsg.cpoSlsCr.getValidCount();
            NWZC150001_cpoSlsCrPMsg slsCr = pMsg.cpoSlsCr.no(cnt);
            ZYPEZDItemValueSetter.setValue(slsCr.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_MODIFY);
            ZYPEZDItemValueSetter.setValue(slsCr.dsCpoSlsCrPk, (BigDecimal) slsCrMap.get("DS_CPO_SLS_CR_PK"));
            ZYPEZDItemValueSetter.setValue(slsCr.dsCpoConfigPk, (BigDecimal) slsCrMap.get("DS_CPO_CONFIG_PK"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCd, (String) slsCrMap.get("SLS_REP_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepRoleTpCd, (String) slsCrMap.get("SLS_REP_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCrPct, (BigDecimal) slsCrMap.get("SLS_REP_CR_PCT"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsCrQuotFlg, (String) slsCrMap.get("SLS_CR_QUOT_FLG"));
            ZYPEZDItemValueSetter.setValue(slsCr.dsOrdPosnNum, (String) slsCrMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(slsCr.configCatgCd, (String) slsCrMap.get("CONFIG_CATG_CD"));
            pMsg.cpoSlsCr.setValidCount(cnt + 1);
        }
    }

    /**
     * callDsCpoUpdApi
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @return String
     */
    // 2017/11/24 S21_NA#21508 Mod Start
    //private String callDsCpoUpdApi(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg) {
    private String callDsCpoUpdApi(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<NWAL1830_BCMsg> checkLineList) {
        // 2017/11/24 S21_NA#21508 Mod End
        /**************************************************
         * NWZC150001 : DS CPO Update API
         **************************************************/
        List<NWZC150002PMsg> cpoUpdApiOutMsgList02 = new ArrayList<NWZC150002PMsg>();
        List<NWZC150003PMsg> cpoUpdApiOutMsgList03 = new ArrayList<NWZC150003PMsg>();

        new NWZC150001().execute(pMsg, cpoUpdApiOutMsgList02, cpoUpdApiOutMsgList03, ONBATCH_TYPE.ONLINE);

        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                bizMsg.setMessageInfo(xxMsgId);
                return null;
            } else {
                bizMsg.setMessageInfo(xxMsgId);
            }
        }
        for (NWZC150002PMsg pMsg02 : cpoUpdApiOutMsgList02) {
            for (int i = 0; i < pMsg02.xxMsgIdList.getValidCount(); i++) {
                String xxMsgId = pMsg02.xxMsgIdList.no(i).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    bizMsg.setMessageInfo(xxMsgId);
                    return null;
                } else {
                    bizMsg.setMessageInfo(xxMsgId);
                }
            }

        }
        for (NWZC150003PMsg pMsg03 : cpoUpdApiOutMsgList03) {
            for (int i = 0; i < pMsg03.xxMsgIdList.getValidCount(); i++) {
                String xxMsgId = pMsg03.xxMsgIdList.no(i).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    // 2017/11/24 S21_NA#21508 Mod Start
                    //bizMsg.setMessageInfo(xxMsgId);
                    if (NWZM1507E.equals(xxMsgId)) {
                        // 2017/12/27 S21_NA#23204 MOD START
                        //NWAL1830_BCMsg line = NWAL1830CommonLogic.getOrigLine(// 2017/12/27 S21_NA#23204 MOD
                                //pMsg03.cpoDtlLineNum.getValue(), pMsg03.cpoDtlLineSubNum.getValue(), checkLineList);
                        //bizMsg.setMessageInfo(NWAM0948E, new String[] {line.mdseCd_B1.getValue() });
                        bizMsg.setMessageInfo(NWAM0948E, new String[] { NWAL1830CommonLogic.getMdseCdFromRtnDtlByLineNum(//
                                pMsg03.cpoDtlLineNum.getValue(), pMsg03.cpoDtlLineSubNum.getValue(), pMsg.rtnDtl)});
                        // 2017/12/27 S21_NA#23204 MOD END
                    } else {
                        bizMsg.setMessageInfo(xxMsgId);
                    }
                    // 2017/11/24 S21_NA#21508 Mod End
                    return null;
                } else {
                    bizMsg.setMessageInfo(xxMsgId);
                }
            }

        }
        String cpoOrdNum = pMsg.cpoOrdNum.getValue();
        return cpoOrdNum;
    }

    /**
     * setPriceSummaryDtail
     * @param dtlPMsg NWZC150001_APMsg
     * @param ordPrc List<Map<String, Object>>
     */
    private void setPriceSummaryDtail(NWZC150001_APMsg dtlPMsg, List<Map<String, Object>> ordPrc) {
        BigDecimal totalBaseAmt = BigDecimal.ZERO;
        BigDecimal totDiscAmt = BigDecimal.ZERO;
        BigDecimal totFrtAmt = BigDecimal.ZERO;
        BigDecimal totTaxAmt = BigDecimal.ZERO;
        for (Map<String, Object> prcMap : ordPrc) {
            if (!INBD_OTBD.OUTBOUND.equals((String) prcMap.get("INBD_OTBD_CD"))) {
                continue;
            }
            if (dtlPMsg.cpoDtlLineNum_A1.getValue().equals((String) prcMap.get(("CPO_DTL_LINE_NUM"))) && dtlPMsg.cpoDtlLineSubNum_A1.getValue().equals((String) prcMap.get(("CPO_DTL_LINE_SUB_NUM")))) {
                String prcDtlGrp = (String) prcMap.get("PRC_DTL_GRP_CD");
                if (!ZYPCommonFunc.hasValue((BigDecimal) prcMap.get("UNIT_PRC_AMT"))) {
                    continue;
                }
                if (PRC_DTL_GRP.BASE_PRICE.equals(prcDtlGrp)) {
                    totalBaseAmt = totalBaseAmt.add((BigDecimal) prcMap.get("UNIT_PRC_AMT"));
                } else if (PRC_DTL_GRP.DISCOUNT.equals(prcDtlGrp)) {
                    totDiscAmt = totDiscAmt.add((BigDecimal) prcMap.get("UNIT_PRC_AMT"));
                } else if (PRC_DTL_GRP.FREIGHT.equals(prcDtlGrp)) {
                    totFrtAmt = totFrtAmt.add((BigDecimal) prcMap.get("UNIT_PRC_AMT"));
                } else if (PRC_DTL_GRP.TAX.equals(prcDtlGrp)) {
                    totTaxAmt = totTaxAmt.add((BigDecimal) prcMap.get("UNIT_PRC_AMT"));
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotBaseAmt_A1, totalBaseAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotDiscAmt_A1, totDiscAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotFrtAmt_A1, totFrtAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotTaxAmt_A1, totTaxAmt);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclPrcAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetDiscAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetPrcAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclChrgAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxSlsAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotAmt_A1, BigDecimal.ZERO);

    }

    /**
     * setPriceSummaryDtailRtrn
     * @param dtlPMsgRtrn NWZC150001_rtnDtlPMsg
     * @param ordPrc List<Map<String, Object>>
     */
    private void setPriceSummaryDtailRtrn(NWZC150001_rtnDtlPMsg dtlPMsgRtrn, List<Map<String, Object>> ordPrc) {

        ZYPEZDItemValueSetter.setValue(dtlPMsgRtrn.basePrcAmt_B1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPMsgRtrn.totFrtAmt_B1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dtlPMsgRtrn.xxTotTaxAmt_B1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclPrcAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetDiscAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetPrcAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclChrgAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxSlsAmt_A1, BigDecimal.ZERO);
        //      ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotAmt_A1, BigDecimal.ZERO);
    }

    /**
     * createCpoUpdApiParamAddSlsCrHdr
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param slsRepRoleTpCd String
     */
    //    private void createCpoUpdApiParamAddSlsCrHdr(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, String slsRepRoleTpCd) {
    private void createCpoUpdApiParamAddSlsCrHdr(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, String slsRepRoleTpCd, List<Map<String, Object>> slsCrList) {
        // 2017/11/02 QC#22278 add start
        for (Map<String, Object> slsCrMap : slsCrList) {
            if (slsCrMap.get("DS_CPO_CONFIG_PK") != null) {
                continue;
            }
            // 2017/11/02 QC#22278 add end
            int cnt = pMsg.cpoSlsCr.getValidCount();
            NWZC150001_cpoSlsCrPMsg slsCr = pMsg.cpoSlsCr.no(cnt);
            ZYPEZDItemValueSetter.setValue(slsCr.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            // 2017/11/02 QC#22278 mod start
            //            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCd, bizMsg.slsRepTocCd_OL);
            //            ZYPEZDItemValueSetter.setValue(slsCr.slsRepRoleTpCd, slsRepRoleTpCd);
            //            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCrPct, new BigDecimal(100));
            //            ZYPEZDItemValueSetter.setValue(slsCr.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCd, (String) slsCrMap.get("SLS_REP_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepRoleTpCd, (String) slsCrMap.get("SLS_REP_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCrPct, (BigDecimal) slsCrMap.get("SLS_REP_CR_PCT"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsCrQuotFlg, (String) slsCrMap.get("SLS_CR_QUOT_FLG"));
            // 2017/11/02 QC#22278 mod end
            pMsg.cpoSlsCr.setValidCount(cnt + 1);
        }
    }

    /**
     * createCpoUpdApiParamAddSlsCrConfig
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param slsRepRoleTpCd String
     * @param dsOrdPosnNum String
     * @param configCatg String
     */
    //    private void createCpoUpdApiParamAddSlsCrConfig(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, String slsRepRoleTpCd, String dsOrdPosnNum, String configCatg) {
    private void createCpoUpdApiParamAddSlsCrConfig(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, String slsRepRoleTpCd, String dsOrdPosnNum, String configCatg, List<Map<String, Object>> slsCrList, BigDecimal origDsCpoConfigPk) {
        // 2017/11/02 QC#22278 add start
        for (Map<String, Object> slsCrMap : slsCrList) {
            if (slsCrMap.get("DS_CPO_CONFIG_PK") == null || origDsCpoConfigPk.compareTo((BigDecimal) slsCrMap.get("DS_CPO_CONFIG_PK")) != 0) {
                continue;
            }
            // 2017/11/02 QC#22278 add end
            int cnt = pMsg.cpoSlsCr.getValidCount();
            NWZC150001_cpoSlsCrPMsg slsCr = pMsg.cpoSlsCr.no(cnt);
            ZYPEZDItemValueSetter.setValue(slsCr.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            // 2017/11/02 QC#22278 mod start
            //            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCd, pMsg.slsRepCd);
            //            ZYPEZDItemValueSetter.setValue(slsCr.slsRepRoleTpCd, slsRepRoleTpCd);
            //            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCrPct, new BigDecimal(100));
            //            ZYPEZDItemValueSetter.setValue(slsCr.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCd, (String) slsCrMap.get("SLS_REP_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepRoleTpCd, (String) slsCrMap.get("SLS_REP_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCrPct, (BigDecimal) slsCrMap.get("SLS_REP_CR_PCT"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsCrQuotFlg, (String) slsCrMap.get("SLS_CR_QUOT_FLG"));
            // 2017/11/02 QC#22278 mod end
            ZYPEZDItemValueSetter.setValue(slsCr.dsOrdPosnNum, dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(slsCr.configCatgCd, configCatg);
            pMsg.cpoSlsCr.setValidCount(cnt + 1);
        }

    }

    /**
     * createCpoUpdApiParamAddSlsCrConfigForExistingOrd
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param existSlsCr List<Map<String, Object>>
     * @param dsOrdPosnNum String
     * @param configCatg String
     */
    private void createCpoUpdApiParamAddSlsCrConfigForExistingOrd(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> existSlsCr, String dsOrdPosnNum, String configCatg) {

        for (Map<String, Object> existSlsCrMap : existSlsCr) {
            if (existSlsCrMap.get("DS_ORD_POSN_NUM") != null) {
                continue;
            }
            int cnt = pMsg.cpoSlsCr.getValidCount();
            NWZC150001_cpoSlsCrPMsg slsCr = pMsg.cpoSlsCr.no(cnt);
            ZYPEZDItemValueSetter.setValue(slsCr.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCd, pMsg.slsRepCd);
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepRoleTpCd, (String) existSlsCrMap.get("SLS_REP_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCrPct, (BigDecimal) existSlsCrMap.get("SLS_REP_CR_PCT"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsCrQuotFlg, (String) existSlsCrMap.get("SLS_CR_QUOT_FLG"));
            ZYPEZDItemValueSetter.setValue(slsCr.dsOrdPosnNum, dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue(slsCr.configCatgCd, configCatg);
            pMsg.cpoSlsCr.setValidCount(cnt + 1);
        }
    }

    /**
     * createCpoUpdApiParamSlsCrHdrForRtrn
     * @param bizMsg NWAL1830CMsg
     * @param pMsg NWZC150001PMsg
     * @param slsCrList List<Map<String, Object>>
     */
    private void createCpoUpdApiParamSlsCrHdrForRtrn(NWAL1830CMsg bizMsg, NWZC150001PMsg pMsg, List<Map<String, Object>> slsCrList) {
        for (Map<String, Object> slsCrMap : slsCrList) {
            if (slsCrMap.get("DS_ORD_POSN_NUM") != null) {
                continue;
            }
            int cnt = pMsg.cpoSlsCr.getValidCount();
            NWZC150001_cpoSlsCrPMsg slsCr = pMsg.cpoSlsCr.no(cnt);
            ZYPEZDItemValueSetter.setValue(slsCr.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_MODIFY);
            ZYPEZDItemValueSetter.setValue(slsCr.dsCpoSlsCrPk, (BigDecimal) slsCrMap.get("DS_CPO_SLS_CR_PK"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCd, (String) slsCrMap.get("SLS_REP_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepRoleTpCd, (String) slsCrMap.get("SLS_REP_ROLE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsRepCrPct, (BigDecimal) slsCrMap.get("SLS_REP_CR_PCT"));
            ZYPEZDItemValueSetter.setValue(slsCr.slsCrQuotFlg, (String) slsCrMap.get("SLS_CR_QUOT_FLG"));
            pMsg.cpoSlsCr.setValidCount(cnt + 1);
        }
    }

    // QC#22631 2017/11/20 Add Start
    private String getPrcCondTpDescTxt(String glblCmpyCd, String prcCondTpCd) {
        PRC_COND_TPTMsg inTMsg = new PRC_COND_TPTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.prcCondTpCd.setValue(prcCondTpCd);
        PRC_COND_TPTMsg outTMsg = (PRC_COND_TPTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg.prcCondTpDescTxt.getValue();
    }

    private BigDecimal getSpecCondPrcPk(String glblCmpyCd, String prcCondTpCd) {
        if (!ZYPCommonFunc.hasValue(prcCondTpCd)) {
            return null;
        }
        SPEC_COND_PRCTMsg inTMsg = new SPEC_COND_PRCTMsg();
        inTMsg.setSQLID("004");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("prcCondTpCd01", prcCondTpCd);
        SPEC_COND_PRCTMsgArray array = (SPEC_COND_PRCTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        if (array == null || array.length() == 0) {
            return BigDecimal.ZERO;
        }
        return array.no(0).specCondPrcPk.getValue();
    }
    // QC#22631 2017/11/20 Add End
    // QC#56558 2020/04/14 Add Start
    private NWAL1830_BCMsg getSetItemParent(NWAL1830CMsg bizMsg, NWAL1830_BCMsg bizLineMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg lineMsg = bizMsg.B.no(i);
            if (bizLineMsg.dsOrdPosnNum_B1.getValue().equals(lineMsg.dsOrdPosnNum_B1.getValue()) 
                    && bizLineMsg.cpoDtlLineNum_B1.getValue().equals(lineMsg.cpoDtlLineNum_B1.getValue()) 
                    && !ZYPCommonFunc.hasValue(lineMsg.setMdseCd_B1))
                return lineMsg;
        }
        return null;
    }
    // QC#56558 2020/04/14 Add End

    /**
     * QC#59222. All Acc Return Service Machine Master Check
     * @param bizMsg NWAL1830CMsg
     * @param dsOrdPosnNum String
     * @return boolean
     */
    private boolean isAllAccRtrnSmm(NWAL1830CMsg bizMsg, String dsOrdPosnNum) {
        boolean rc = true;
 
        BigDecimal svcMachMstrConfigPk = null;
        List<BigDecimal> machMstrPkList = new ArrayList<BigDecimal>();
        // db check.
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1830_BCMsg bizLineMsg = bizMsg.B.no(i);

            for (int c = 0; c < bizMsg.A.getValidCount(); c++) {
                if (!ZYPCommonFunc.hasValue(svcMachMstrConfigPk) && //
                        ZYPCommonFunc.hasValue(dsOrdPosnNum) &&  //
                        dsOrdPosnNum.equals(bizMsg.A.no(c).dsOrdPosnNum_A1.getValue())) {

                    svcMachMstrConfigPk = bizMsg.A.no(c).svcConfigMstrPk_A1.getValue();

                    if (ZYPCommonFunc.hasValue(svcMachMstrConfigPk)) {
                        break;
                    }
                }
            }

            if (!ZYPCommonFunc.hasValue(svcMachMstrConfigPk)) {
                return rc;
            }

            if (dsOrdPosnNum.equals(bizLineMsg.dsOrdPosnNum_B1.getValue())) {
                // Same config line
                if (ZYPConstant.CHKBOX_ON_Y.equals(bizLineMsg.xxChkBox_B1.getValue())) {
                    // Targe Line
                    /* 2022/02/02 QC#59647 Comment Out
                    if (ZYPCommonFunc.hasValue(bizLineMsg.svcMachMstrPk_B1)) {
                        machMstrPkList.add(bizLineMsg.svcMachMstrPk_B1.getValue());
                    }
                    */
                    
                    // 2022/02/02 QC#59647 Add Start
                    //List<BigDecimal> svcMachMstrPkList = (List<BigDecimal>) (NWAL1830Query.getInstance().getSvcMachMstrPk(bizLineMsg));
                    S21SsmEZDResult result = NWAL1830Query.getInstance().getSvcMachMstrPk(bizLineMsg);
                    List<BigDecimal> svcMachMstrPkList = (List<BigDecimal>) result.getResultObject();
                    
                    if (svcMachMstrPkList != null) {
                        for(BigDecimal svcMachMstrPk : svcMachMstrPkList) {
                            machMstrPkList.add(svcMachMstrPk);
                        }
                    }
                    // 2022/02/02 QC#59647 Add End
                }
            }
        }

        if (ZYPCommonFunc.hasValue(svcMachMstrConfigPk) && !machMstrPkList.isEmpty()) {
            S21SsmEZDResult result = NWAL1830Query.getInstance().isAllRtrnAccCheck(bizMsg.glblCmpyCd.getValue(), svcMachMstrConfigPk, machMstrPkList);
            if (result.isCodeNormal()) {
                rc = false;
                bizMsg.setMessageInfo("NWAM0985E", new String[] {(String) result.getResultObject() });
            }
        }

        return rc;
    }
}
