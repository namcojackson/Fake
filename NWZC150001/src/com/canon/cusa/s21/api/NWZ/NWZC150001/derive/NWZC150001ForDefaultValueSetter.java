/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.derive;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.CPO_SRC_TP_CR;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.IMPT_API_PG_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWAM0983E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0925E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1466E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1503E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1504E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1505E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1889E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1990E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2033E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM2252E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.PCT_100;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CONFIG_MODIFY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CONFIG_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_SLS_CR_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RTL_WH_LOAN;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.SET_LINE_SUB_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.STR_ASTERISK;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.LOAN_ORD_ACTION_LOAN_SELL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CONFIG_TPTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.FRT_CONDTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_LINE_VAL_SETTMsg;
import business.db.ORD_LINE_VAL_SETTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_CATGTMsg;
import business.db.RTL_WHTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.parts.NLZC215001PMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NPZC113001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_APMsgArray;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoConfigPMsgArray;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsgArray;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnDtlPMsgArray;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC215001.NLZC215001;
import com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001DsCpoCommonBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Query;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SvcMdlFunc;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SvcMdlFuncParamBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/03   Fujitsu         T.Ogura         Create          N/A
 * 2017/09/13   Fujitsu         T.Murai         Update          S21_NA#16346(Sol#373)
 * 2017/09/20   Fujitsu         M.Ohno          Update          S21_NA#21284
 * 2017/10/18   Fujitsu         S.Ohki          Update          S21_NA#16347(Sol#430)
 * 2017/10/24   Fujitsu         Mz.Takahashi    Update          S21_NA#16347(Sol#430)
 * 2017/10/24   Fujitsu         W.Honda         Update          S21_NA#21773
 * 2017/11/01   Fujitsu         S.Takami        Update          S21_NA#22140
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22555
 * 2017/12/21   Fujitsu         S.Takami        Update          S21_NA#20050
 * 2017/12/26   Fujitsu         S.Takami        Update          S21_NA#22986
 * 2018/01/11   Fujitsu         A.Kosai         Update          S21_NA#22372
 * 2018/01/11   Fujitsu         S.Takami        Update          S21_NA#23329
 * 2018/01/18   Fujitsu         K.Ishizuka      Update          S21_NA#23555
 * 2018/01/25   Fujitsu         Y.Kanefusa      Update          S21_NA#23773
 * 2018/02/05   Fujitsu         T.Aoi           Update          S21_NA#23329(Sol#387)
 * 2018/02/28   Fujitsu         M.Ohno          Update          S21_NA#24117
 * 2018/03/20   Fujitsu         S.Takami        Update          S21_NA#24698
 * 2018/04/10   Fujitsu         K.Ishizuka      Update          S21_NA#24842
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/05/24   Fujitsu         S.Takami        Update          S21_NA#25604-2
 * 2018/06/06   Fujitsu         M.Ohno          Update          S21_NA#25860
 * 2018/06/13   Fujitsu         Y.Kanefusa      Update          S21_NA#24245
 * 2018/06/18   Fujitsu         K.Ishizuka      Update          S21_NA#24294
 * 2018/07/04   Fujitsu         S.Takami        Update          S21_NA#26607
 * 2018/08/30   Fujitsu         K.Ishizuka      Update          S21_NA#27489
 * 2018/09/21   Fujitsu         K.Ishizuka      Update          S21_NA#28363
 * 2018/10/18   Fujitsu         S.Takami        Update          S21_NA#26229
 * 2018/11/27   Fujitsu         Y.Kanefusa      Update          S21_NA#29262
 * 2018/12/19   Fujitsu         K.Ishizuka      Update          S21_NA#29561
 * 2019/03/14   Fujitsu         K.Ishizuka      Update          S21_NA#30739
 * 2019/08/07   Fujitsu         S.Kosaka        Update          QC#52505
 * 2019/08/06   Fujitsu         S.Kosaka        Update          S21_NA#52409
 * 2019/11/27   Fujitsu         M.Nakamura      Update          S21_NA#54212
 * 2019/12/13   Fujitsu         S.Iidaka        Update          S21_NA#53013
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2020/06/09   CITS            K.Ogino         Update          QC#56978
 * 2021/02/04   CITS            K.Ogino         Update          QC#58230
 * 2024/03/14   CITS            J.Cho           Update          QC#63527
 * </pre>
 */

public class NWZC150001ForDefaultValueSetter extends S21ApiCommonBase {

    // 2018/07/04 S21_NA#26607 Start
    private static boolean LOG_OUT = false;
    // 2018/07/04 S21_NA#26607 End

    public static void setDefaultValues(NWZC150001PMsg pMsg
            , List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, List<MDSETMsg> mdseTMsgList
            , List<MDSETMsg> mdseTMsgRtrnList, CPOTMsg cpoTMsg, NWZC150001CpouLocalCache localCache
            , S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) { // Mod(Add Param) S21_NA#14107
        setDefaultSlsRep(pMsg, pMsg2List, msgIdMgr); // 2018/09/21 S21_NA#28363 Add
        // 2016/01/26 S21_NA#3740 Mod Start
        //setDefaultTrxCd(pMsg);
        setDefaultTrxCd(pMsg, pMsg2List, pMsg3List);
        // 2016/01/26 S21_NA#3740 Mod End

        setDefaultWH(pMsg, mdseTMsgList, mdseTMsgRtrnList, pMsg2List, commonBean);
        setDefaultPmtTerm(pMsg, msgIdMgr);

        setDefaultDealWH(pMsg, mdseTMsgList, mdseTMsgRtrnList);
        // 2016/06/16 S21_NA#9855 Mod Start
        // setDefaultRetailSubWH(pMsg, pMsg2List, pMsg3List);
        setDefaultRetailSubWH(pMsg, pMsg2List, pMsg3List, mdseTMsgRtrnList, msgIdMgr, commonBean);
        // 2016/06/16 S21_NA#9855 Mod Start

        setDefaultFreightCharge(pMsg, pMsg2List);
        setDefaultCurrency(pMsg);

        // 2019/12/13 S21_NA#53013 Mod Start
        if (CPO_SRC_TP.DEAL_CONFIGURATOR.equals(pMsg.cpoSrcTpCd.getValue()) && IMPT_API_PG_ID.equals(pMsg.bizAppId.getValue())) {
            // Skip Model Derivation
        } else {
            setDefaultModel(pMsg, pMsg2List, commonBean);
        }
        // 2019/12/13 S21_NA#53013 Mod Start

        setDefaultVendor(pMsg, pMsg2List, mdseTMsgList, msgIdMgr, commonBean);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        setDefaultCsmpPriceList(pMsg, pMsg2List, pMsg3List, mdseTMsgList);
        setDefaultCsmpPriceList(pMsg, pMsg2List, pMsg3List, mdseTMsgList, localCache, commonBean);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
        //        getDefaultShppingCarrierServiceLevel(pMsg, pMsg2List);
        //        setDefaultCarrierServiceLevel(pMsg);
        setDefaultCarrierServiceLevelForDtl(pMsg);
        setDefaultCarrier(pMsg, pMsg2List);

        setDefaultHddEraseRemovalFlg(pMsg, mdseTMsgList, pMsg2List, commonBean);
        setDefaultCustInstlFlg(pMsg, pMsg2List);
        setDefaultConfigFlg(pMsg, mdseTMsgList, pMsg2List);
        setDefaultShopItemFlg(pMsg, pMsg2List);
        // setDefaultConfigId(pMsg, pMsg2List); 2016/08/01 S21_NA#12637 Del
        // 2017/03/09 S21_NA#17979 Mod Start
//        setDefaultConfigIdByEveryConfig(pMsg, pMsg2List); // 2016/08/01 S21_NA#12637 Add
        setDefaultConfigIdByEveryConfig(pMsg, pMsg2List, pMsg3List, commonBean);
        // 2017/03/09 S21_NA#17979 Mod End

        Map<Map<String, String>, Map<String, String>> cacheMap = new HashMap<Map<String, String>, Map<String, String>>();
        setDefaultShipToInfoForHeader(pMsg, cacheMap);
        setDefaultShipToInfoForConfig(pMsg.cpoConfig, pMsg.A, pMsg.rtnDtl, cacheMap, pMsg); // S21_NA#8003 MOD

        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum) || ORD_HDR_STS.SAVED.equals(cpoTMsg.ordHdrStsCd.getValue())) { // ADD S21_NA#14107
            deriveDsCpoLineNum(pMsg);
        }

        if (!isInternalOrder(pMsg)) { // S21_NA#8018
            deriveSlsRepSlsCr(pMsg, pMsg2List, pMsg3List, msgIdMgr, commonBean); // 2016/10/13 S21_NA#7924-2 Add pMsg2List and pMsg3List
        }
        // 2017/12/21 S21_NA#20050 Add Start
        setRmaMachMstrPk(pMsg);
        // 2017/12/21 S21_NA#20050 Add End

        // 2018/01/11 S21_NA#22372 Add Start
        setDefaultFloorPrice(pMsg, commonBean);
        // 2018/01/11 S21_NA#22372 Add End
    }

    private static void setDefaultTrxCd(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List) {
        Map<Map<String, String>, Map<String, String>> cacheMap = new HashMap<Map<String, String>, Map<String, String>>();
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            Map<String, String> rsltMap //
            = NWZC150001Query.getInstance().getDefaultTrxCd(pMsg, aPMsg.dsOrdLineCatgCd_A1.getValue(), cacheMap);

            // S21_NA#3740 Mod Start
//            if (rsltMap == null) {
//                continue;
//            }
            if (!isObtainTrxCd(rsltMap)) {
                NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                NWZC150001Common.setMsgId2(pMsg2, NWZM1889E);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                pMsg2List.add(pMsg2);
                continue;
            }
            // S21_NA#3740 Mod End
            ZYPEZDItemValueSetter.setValue(aPMsg.trxCd_A1, rsltMap.get("TRX_CD"));
            ZYPEZDItemValueSetter.setValue(aPMsg.trxRsnCd_A1, rsltMap.get("TRX_RSN_CD"));

            // 2017/10/18 S21_NA#16347 Add Start
            if (CR_REBIL.CREDIT.equals(aPMsg.crRebilCd_A1.getValue())) {

                Map<String, String> convMap = NWZC150001Query.getInstance().getConvTrxCd(pMsg.glblCmpyCd.getValue(), aPMsg.trxCd_A1.getValue(), aPMsg.trxRsnCd_A1.getValue());

                if (convMap == null) {
                    convMap = NWZC150001Query.getInstance().getConvTrxCd(pMsg.glblCmpyCd.getValue(), STR_ASTERISK, STR_ASTERISK);
                }

                ZYPEZDItemValueSetter.setValue(aPMsg.trxCd_A1, convMap.get("TO_TRX_CD"));
                ZYPEZDItemValueSetter.setValue(aPMsg.trxRsnCd_A1, convMap.get("TO_TRX_RSN_CD"));
            }
            // 2017/10/18 S21_NA#16347 Add End
        }

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg aPMsg = pMsg.rtnDtl.no(i);

            // Add Start 2017/09/13 S21_NA#16346
            
            // Mode Start 2017/10/24 S21_NA#16347
            if (CPO_SRC_TP.LOAN_WORKBENCH.equals(pMsg.cpoSrcTpCd.getValue())) {
            //if (CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY.equals(pMsg.cpoSrcTpCd.getValue()) //
            //        || CPO_SRC_TP.LOAN_WORKBENCH.equals(pMsg.cpoSrcTpCd.getValue())) {
            // Mode End 2017/10/24 S21_NA#16347
                String inBoundOrdLineCatgCd = NWZC150001Query.getInstance().getInBoundLineCatg(pMsg, aPMsg);
                if (ZYPCommonFunc.hasValue(inBoundOrdLineCatgCd)) {
                    ZYPEZDItemValueSetter.setValue(aPMsg.dsOrdLineCatgCd_B1, inBoundOrdLineCatgCd);
                }
            }
            // Add End 2017/09/13 S21_NA#16346

            Map<String, String> rsltMap //
            = NWZC150001Query.getInstance().getDefaultTrxCd(pMsg, aPMsg.dsOrdLineCatgCd_B1.getValue(), cacheMap);

            // S21_NA#3740 Mod Start
//            if (rsltMap == null) {
//                continue;
//            }
            if (!isObtainTrxCd(rsltMap)) {
                NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
                NWZC150001Common.setMsgId3(pMsg3, NWZM1889E);
                ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, aPMsg.cpoDtlLineNum_B1);
                ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_B1);
                pMsg3List.add(pMsg3);
                continue;
            }
            // S21_NA#3740 Mod End
            ZYPEZDItemValueSetter.setValue(aPMsg.trxCd_B1, rsltMap.get("TRX_CD"));
            ZYPEZDItemValueSetter.setValue(aPMsg.trxRsnCd_B1, rsltMap.get("TRX_RSN_CD"));
        }
    }

    private static boolean isObtainTrxCd(Map<String, String> rsltMap) {
        if (null == rsltMap) {
            return false;
        }
        if (null == rsltMap.get("TRX_CD")) {
            return false;
        }
        if (null == rsltMap.get("TRX_RSN_CD")) {
            return false;
        }
        return true;
    }

    private static void setDefaultWH(NWZC150001PMsg pMsg //
            , List<MDSETMsg> mdseTMsgList //
            , List<MDSETMsg> mdseTMsgRtrnList //
            , List<NWZC150002PMsg> pMsg2List
            , NWZC150001DsCpoCommonBean commonBean) {
        String dsWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", pMsg.glblCmpyCd.getValue());

        // 2017/12/26 S21_NA#22986 Add Start
        String notIttHldOrdLineSrcCd = null;
        // 2017/12/26 S21_NA#22986 Add End
        
        // 2018/01/18 S21_NA#23555 Add Start
        String crRebillDummyWhCdCsv = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD, pMsg.glblCmpyCd.getValue());
        List<String> crRebillDummyWhCdList = new ArrayList<String>();
        if (crRebillDummyWhCdCsv != null) {
            String[] crRebillDummyWhCd = crRebillDummyWhCdCsv.split(",");
            crRebillDummyWhCdList = Arrays.asList(crRebillDummyWhCd);
        }
        // 2018/01/18 S21_NA#23555 Add End

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
            MDSETMsg mdseTMsg = mdseTMsgList.get(i);

            // 2017/12/26 S21_NA#22986 Add Start
            // 2018/01/18 S21_NA#23555 Mod Start
            //if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue()) //
            //        && !S21StringUtil.isEquals(MDSE_TP.SET, mdseTMsg.mdseTpCd.getValue())) {
            if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue()) //
                    && !S21StringUtil.isEquals(MDSE_TP.SET, mdseTMsg.mdseTpCd.getValue()) //
                    && (ZYPCommonFunc.hasValue(aPMsg.invtyLocCd_A1) && !crRebillDummyWhCdList.contains(aPMsg.invtyLocCd_A1.getValue()))) {
            // 2018/01/18 S21_NA#23555 Mod End
                aPMsg.rtlWhCd_A1.clear();
                aPMsg.rtlSwhCd_A1.clear();
                aPMsg.invtyLocCd_A1.clear();

                if (NWXC150001DsCheck.isOrdLineSrcItt(pMsg.glblCmpyCd.getValue(), aPMsg.ordLineSrcCd_A1.getValue())) {
                    if (notIttHldOrdLineSrcCd == null) {
                        notIttHldOrdLineSrcCd = NWXC150001DsCheck.getMinOrdLineSrcNotIttHld(pMsg.glblCmpyCd.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(aPMsg.ordLineSrcCd_A1, notIttHldOrdLineSrcCd);
                }
                continue;
            }

            // QC#63527 2024/03/14 Start
            if (DS_ORD_LINE_CATG.LOAN_BILL_ONLY.equals(aPMsg.dsOrdLineCatgCd_A1.getValue())) {
                String beforeOwnCd = NWZC150001Query.getInstance().getCpoDetailInvLocCd(pMsg.glblCmpyCd.getValue(), aPMsg.origCpoOrdNum_A1.getValue(), aPMsg.origCpoDtlLineNum_A1.getValue(), aPMsg.origCpoDtlLineSubNum_A1.getValue());

                List<Map<String, Object>> sellLineCatgList = NWZC150001Query.getInstance().getDsOrdCatgListForValSet(pMsg.glblCmpyCd.getValue(), LOAN_ORD_ACTION_LOAN_SELL);

                if (beforeOwnCd != null && sellLineCatgList != null) {
                    for (int j = 0; j < sellLineCatgList.size(); j++) {
                        if (beforeOwnCd.equals(sellLineCatgList.get(j).get("OWNR"))) {
                            ZYPEZDItemValueSetter.setValue(aPMsg.dsOrdLineCatgCd_A1, sellLineCatgList.get(j).get("DS_ORD_LINE_CATG_CD").toString());
                            ZYPEZDItemValueSetter.setValue(aPMsg.rtlWhCd_A1, sellLineCatgList.get(j).get("RTL_WH_CD").toString());
                        }
                    }
                }
            }
            // QC#63527 2024/03/14 End

            // 2017/12/26 S21_NA#22986 Add End
            if (!ZYPCommonFunc.hasValue(aPMsg.rtlWhCd_A1)) {

                // 2018/02/05 S21_NA#23329 Mod Start
                // 2017/12/26 S21_NA#22986 Del Start
                if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
                    aPMsg.rtlWhCd_A1.clear();
                    aPMsg.rtlSwhCd_A1.clear();
                    aPMsg.invtyLocCd_A1.clear();
                    continue;
                }
                // 2017/12/26 S21_NA#22986 Del End
                // 2018/02/05 S21_NA#23329 Mod End

                NWZC180001PMsg defaultWHApiMsg = callDefaultWHApi(pMsg, aPMsg, mdseTMsg, commonBean);
                if (S21ApiUtil.isXxMsgId(defaultWHApiMsg)) {
                    List<String> msgIdList = S21ApiUtil.getXxMsgIdList(defaultWHApiMsg);
                    int ix = pMsg2.xxMsgIdList.getValidCount();
                    for (int n = 0; n < msgIdList.size(); n++) {
                        ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgId, msgIdList.get(n));
                        ix++;
                    }
                    pMsg2.xxMsgIdList.setValidCount(ix);
                    // return; // 2019/03/14 S21_NA#30739 Del
                }

                // QC#58230 Add
                String prodCondCd = aPMsg.prodCondCd.getValue();
                String cpoSrcTpCd = pMsg.cpoSrcTpCd.getValue();
                String rtlSwhCd = aPMsg.rtlSwhCd_A1.getValue();

                if (dsWhCd.equals(defaultWHApiMsg.rtlWhCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(aPMsg.rtlWhCd_A1, defaultWHApiMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(aPMsg.rtlSwhCd_A1, defaultWHApiMsg.rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(aPMsg.invtyLocCd_A1, defaultWHApiMsg.vndCd);
                    ZYPEZDItemValueSetter.setValue(aPMsg.ordLineSrcCd_A1, defaultWHApiMsg.ordLineSrcCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(aPMsg.rtlWhCd_A1, defaultWHApiMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(aPMsg.rtlSwhCd_A1, defaultWHApiMsg.rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(aPMsg.invtyLocCd_A1 //
                            , S21StringUtil.concatStrings(defaultWHApiMsg.rtlWhCd.getValue(), defaultWHApiMsg.rtlSwhCd.getValue()));
                    ZYPEZDItemValueSetter.setValue(aPMsg.ordLineSrcCd_A1, defaultWHApiMsg.ordLineSrcCd);
                }
                // QC#58230 Add
                if (ZYPCommonFunc.hasValue(cpoSrcTpCd) && CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd) && ZYPCommonFunc.hasValue(prodCondCd)) {
                    aPMsg.rtlWhCd_A1.clear();
                    aPMsg.invtyLocCd_A1.clear();
                    ZYPEZDItemValueSetter.setValue(aPMsg.rtlSwhCd_A1, rtlSwhCd);
                }
            }
            // QC#29262 2018/11/27 Add Start
            if(crRebillDummyWhCdList.contains(aPMsg.rtlWhCd_A1.getValue())){
                ZYPEZDItemValueSetter.setValue(aPMsg.ordLineSrcCd_A1, ORD_LINE_SRC.INTERNAL);
            }
            // QC#29262 2018/11/27 Add End
        }

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
            MDSETMsg mdseTMsg = mdseTMsgRtrnList.get(i);
            
            // QC#63527 2024/03/14 Start
            String defaultRenCode = ZYPCodeDataUtil.getVarCharConstValue("LOAN_RTRN_RSN_CD", pMsg.glblCmpyCd.getValue());
            if(defaultRenCode.equals(rtnDtlPMsg.rtrnRsnCd_B1)){

                String beforeOwnCd = NWZC150001Query.getInstance().getCpoDetailInvLocCd(pMsg.glblCmpyCd.getValue(),
                       rtnDtlPMsg.origCpoOrdNum_B1.getValue(), rtnDtlPMsg.origCpoDtlLineNum_B1.getValue(), rtnDtlPMsg.origCpoDtlLineSubNum_B1.getValue());
                if (beforeOwnCd != null) {
                    if (INVTY_OWNR.GMD.equals(beforeOwnCd)) {
                        String rtlWhCd = rtnDtlPMsg.rtlWhCd_B1.getValue();
                        
                        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
                        rtlWhTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                        rtlWhTMsg.rtlWhCd.setValue(rtlWhCd);
                        rtlWhTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);
                        
                        String physWhCd = rtlWhTMsg.physWhCd.getValue();
                        String rtlWhCdGmd =  NWZC150001Query.getInstance().getRtlWh(pMsg.glblCmpyCd.getValue(), physWhCd);
                        
                        if (rtlWhCdGmd != null) {
                            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.rtlWhCd_B1, rtlWhCdGmd);
                        } else {
                            rtnDtlPMsg.rtlWhCd_B1.clear();
                        }
                    }
                }
            }
            // QC#63527 2024/03/14 End

            if (!ZYPCommonFunc.hasValue(rtnDtlPMsg.rtlWhCd_B1)) {
                
                // 2018/12/19 S21_NA#29561 Add Start
                if (S21StringUtil.isEquals(CPO_SRC_TP.CREDIT, rtnDtlPMsg.cpoSrcTpCd_B1.getValue())) {
                    continue;
                }
                // 2018/12/19 S21_NA#29561 Add End

                if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
                    rtnDtlPMsg.rtlWhCd_B1.clear();
                    rtnDtlPMsg.rtlSwhCd_B1.clear();
                    rtnDtlPMsg.invtyLocCd_B1.clear();
                    continue;
                }

                NWZC180001PMsg defaultWHApiMsg = callDefaultWHApiForRtrn(pMsg, rtnDtlPMsg, mdseTMsg, commonBean);
                if (S21ApiUtil.isXxMsgId(defaultWHApiMsg)) {
                    List<String> msgIdList = S21ApiUtil.getXxMsgIdList(defaultWHApiMsg);
                    int ix = pMsg2.xxMsgIdList.getValidCount();
                    for (int n = 0; n < msgIdList.size(); n++) {
                        ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgId, msgIdList.get(n));
                        ix++;
                    }
                    pMsg2.xxMsgIdList.setValidCount(ix);
                    // return; // 2019/03/14 S21_NA#30739 Del
                }

                if (dsWhCd.equals(defaultWHApiMsg.rtlWhCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.rtlWhCd_B1, defaultWHApiMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.rtlSwhCd_B1, defaultWHApiMsg.rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.invtyLocCd_B1, defaultWHApiMsg.vndCd);
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.ordLineSrcCd_B1, defaultWHApiMsg.ordLineSrcCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.rtlWhCd_B1, defaultWHApiMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.rtlSwhCd_B1, defaultWHApiMsg.rtlSwhCd);
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.invtyLocCd_B1 //
                            , S21StringUtil.concatStrings(defaultWHApiMsg.rtlWhCd.getValue(), defaultWHApiMsg.rtlSwhCd.getValue()));
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.ordLineSrcCd_B1, defaultWHApiMsg.ordLineSrcCd);
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.thirdPtyDspTpCd_B1, defaultWHApiMsg.thirdPtyDspTpCd);
                }
            }
        }
    }

    private static NWZC180001PMsg callDefaultWHApi(NWZC150001PMsg dsCpoMsg //
            , NWZC150001_APMsg aPMsg //
            , MDSETMsg mdseTMsg
            , NWZC150001DsCpoCommonBean commonBean) {
        NWZC180001PMsg pMsg = new NWZC180001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, dsCpoMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, dsCpoMsg.slsDt); // 2016/10/06 S21_NA#13905 Add
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, dsCpoMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, dsCpoMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, aPMsg.shipToPostCd_A1);
        // 2016/06/02 S21_NA#9273 Modify Start
//        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk //
//                , NWZC150001Query.getInstance().getSvcMachMstrPk(glblCmpyCd, aPMsg.serNum_A1.getValue()));
        if (ZYPCommonFunc.hasValue(aPMsg.svcMachMstrPk_A1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, aPMsg.svcMachMstrPk_A1);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk //
         // 2017/02/22 QC#16575 UPD START
                    , NWZC150001Query.getInstance().getSvcMachMstrPk(pMsg.glblCmpyCd.getValue(), aPMsg.serNum_A1.getValue(), aPMsg.mdseCd_A1.getValue()));
         // 2017/02/22 QC#16575 UPD E N D
        }
        // 2016/06/02 S21_NA#9273 Modify End
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, aPMsg.ordQty_A1);

        NWZC180001 nwzc180001 = new NWZC180001();
        nwzc180001.execute(pMsg, commonBean.getOnBatchType());

        return pMsg;
    }

    private static NWZC180001PMsg callDefaultWHApiForRtrn(NWZC150001PMsg dsCpoMsg //
            , NWZC150001_rtnDtlPMsg rtnDtlPMsg, MDSETMsg mdseTMsg, NWZC150001DsCpoCommonBean commonBean) {
        NWZC180001PMsg pMsg = new NWZC180001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, dsCpoMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, dsCpoMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, dsCpoMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, dsCpoMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, rtnDtlPMsg.rtrnRsnCd_B1);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, rtnDtlPMsg.shipToPostCd_B1);
        // 2016/06/02 S21_NA#9273 Modify Start
//        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, rtnDtlPMsg.svcMachMstrPk_B1.getValue());
        if (ZYPCommonFunc.hasValue(rtnDtlPMsg.svcMachMstrPk_B1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, rtnDtlPMsg.svcMachMstrPk_B1);
        } else {
            // 2017/02/22 QC#16575 ADD START
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk //
                    , NWZC150001Query.getInstance().getSvcMachMstrPk(pMsg.glblCmpyCd.getValue(), rtnDtlPMsg.serNum_B1.getValue(), rtnDtlPMsg.mdseCd_B1.getValue()));
            // 2017/02/22 QC#16575 ADD E N D
        }
        // 2016/06/02 S21_NA#9273 Modify End
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, rtnDtlPMsg.ordQty_B1);

        NWZC180001 nwzc180001 = new NWZC180001();
        nwzc180001.execute(pMsg, commonBean.getOnBatchType());

        return pMsg;
    }

    private static void setDefaultPmtTerm(NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {
        if (ZYPCommonFunc.hasValue(pMsg.addPmtTermCashDiscCd)) {
            return;
        }
        String defaultPmtTerm = NWXC150001DsCheck.getDefaultLocPmtTerm(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue());
        if (ZYPCommonFunc.hasValue(defaultPmtTerm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.addPmtTermCashDiscCd, defaultPmtTerm);
            return;
        }
        defaultPmtTerm = NWXC150001DsCheck.getDefaultAcctPmtTerm(pMsg.glblCmpyCd.getValue(), pMsg.billToCustAcctCd.getValue());
        if (ZYPCommonFunc.hasValue(defaultPmtTerm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.addPmtTermCashDiscCd, defaultPmtTerm);
            return;
        }
        msgIdMgr.addXxMsgId(NWZM0925E, pMsg);
    }

    private static void setDefaultDealWH(NWZC150001PMsg pMsg //
            , List<MDSETMsg> mdseTMsgList //
            , List<MDSETMsg> mdseTMsgRtrnList) {

        List<Map<String, String>> forceDummyWhList = NWZC150001Query.getInstance().getForceDummyWhList(pMsg);
        Map<String, String> dealWhMap = new HashMap<String, String>();
        for (Map<String, String> map : forceDummyWhList) {
            dealWhMap.put(map.get("DS_ORD_LINE_CATG_CD"), map.get("SCD_BIZ_CTX_ATTRB_TXT"));
        }

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            MDSETMsg mdseTMsg = mdseTMsgList.get(i);
            if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
                // return;
                continue; // 2018/08/30 S21_NA#27489 Mod
            }

            // 2017/10/24 S21_NA#21773 Add Start
            if (CPO_SRC_TP_CR.equals(aPMsg.rtlWhCd_A1.getValue())) {
                return;
            }
            // 2017/10/24 S21_NA#21773 Add End

            // 2016/06/09 S21_NA#9499 Mod Start
//            if (dealWhMap.containsValue(aPMsg.rtlWhCd_A1.getValue())) {
//                aPMsg.rtlSwhCd_A1.clear();
//                ZYPEZDItemValueSetter.setValue(aPMsg.invtyLocCd_A1, aPMsg.rtlWhCd_A1);
//            }
            String dummyRtlWhCd = dealWhMap.get(aPMsg.dsOrdLineCatgCd_A1.getValue());
            // 2019/08/06 QC#52409 Mod Start
//            if (ZYPCommonFunc.hasValue(dummyRtlWhCd)) {
            if (ZYPCommonFunc.hasValue(dummyRtlWhCd) && !RTL_WH_LOAN.equals(dummyRtlWhCd)) {
                ZYPEZDItemValueSetter.setValue(aPMsg.invtyLocCd_A1, dummyRtlWhCd);
                ZYPEZDItemValueSetter.setValue(aPMsg.rtlWhCd_A1, dummyRtlWhCd);
                aPMsg.rtlSwhCd_A1.clear();
            }
            // 2019/08/06 QC#52409 Mod End
            // 2016/06/09 S21_NA#9499 Mod End
        }

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            MDSETMsg mdseTMsg = mdseTMsgRtrnList.get(i);
            if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
                // return;
                continue; // 2018/08/30 S21_NA#27489 Mod
            }

            // 2016/06/09 S21_NA#9499 Mod Start
//            if (dealWhMap.containsValue(rtnDtlPMsg.rtlWhCd_B1.getValue())) {
//                rtnDtlPMsg.rtlSwhCd_B1.clear();
//                ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.invtyLocCd_B1, rtnDtlPMsg.rtlWhCd_B1);
//            }
            String dummyRtlWhCd = dealWhMap.get(rtnDtlPMsg.dsOrdLineCatgCd_B1.getValue());
            // 2019/08/06 QC#52409 Mod Start
//            if (ZYPCommonFunc.hasValue(dummyRtlWhCd)) {
            if (ZYPCommonFunc.hasValue(dummyRtlWhCd) && !RTL_WH_LOAN.equals(dummyRtlWhCd)) {
                ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.invtyLocCd_B1, dummyRtlWhCd);
                ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.rtlWhCd_B1, dummyRtlWhCd);
                rtnDtlPMsg.rtlSwhCd_B1.clear();
            }
            // 2019/08/06 QC#52409 Mod End
            // 2016/06/09 S21_NA#9499 Mod End
        }
    }

    private static void setDefaultRetailSubWH(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, List<MDSETMsg> mdseTMsgRtrnList, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
        String notHardAllocWh = ZYPCodeDataUtil.getVarCharConstValue("NOT_HARD_ALLOC_WH_CD", pMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(notHardAllocWh)) {
            msgIdMgr.addXxMsgId(NWZM1504E, pMsg);
            return;
        }
        List<String> notHardAllocWhList = S21StringUtil.toList(notHardAllocWh);

        // For Performance QC#8166 Add Start
        Map<Map<String, String>, String> subWhCacheMap = new HashMap<Map<String, String>, String>();
        // For Performance QC#8166 Add End

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(aPMsg.rtlSwhCd_A1)) {
                continue;
            }
            String rtlWhCd = aPMsg.rtlWhCd_A1.getValue();
            if (notHardAllocWhList.contains(rtlWhCd)) {
                continue;
            }
            // 2015/12/11 S21_NA#1809 Add Start
            String origMdseCd = aPMsg.mdseCd_A1.getValue();
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), origMdseCd);
            if (null != mdseTMsg) {
                if (!MDSE_TP.SET.equals(mdseTMsg.mdseTpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) { // S21_NA#2522
                    continue;
                }
            }
            // 2015/12/11 S21_NA#1809 Add End

            // QC#58230
            String prodCondCd = aPMsg.prodCondCd.getValue();
            String cpoSrcTpCd = pMsg.cpoSrcTpCd.getValue();
            if (ZYPCommonFunc.hasValue(cpoSrcTpCd) && CPO_SRC_TP.DEAL_CONFIGURATOR.equals(cpoSrcTpCd) && ZYPCommonFunc.hasValue(prodCondCd)) {
                continue;
            }

            // For Performance QC#8166 Mod Start
            // String defaultRtlSubWh = NWZC150001Query.getInstance().getDefaultRetailSubWH(pMsg, aPMsg);
            String defaultRtlSubWh = getDefaultRetailSubWHFromCache(pMsg, aPMsg, subWhCacheMap);
            // For Performance QC#8166 Mod End

            if (!ZYPCommonFunc.hasValue(defaultRtlSubWh)) {
                // 2015/12/18 S21_NA#1643 Add Start
                //addMsgId2List(pMsg2List, aPMsg, NWZM1434E);
                //QC#12531
                NWZC150001Common.addMsgId2List(pMsg2List, aPMsg, NWZM1990E);
                // 2015/12/18 S21_NA#1643 Add End
            }
            ZYPEZDItemValueSetter.setValue(aPMsg.rtlSwhCd_A1, defaultRtlSubWh);
            // 2015/12/18 S21_NA#1643 Add Start
            ZYPEZDItemValueSetter.setValue(aPMsg.invtyLocCd_A1, S21StringUtil.concatStrings(rtlWhCd, defaultRtlSubWh));
            // 2015/12/18 S21_NA#1643 Add End
        }

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            // 2018/12/19 S21_NA#29561 Add Start
            if (S21StringUtil.isEquals(CPO_SRC_TP.CREDIT, rtnDtlPMsg.cpoSrcTpCd_B1.getValue())) {
                continue;
            }
            // 2018/12/19 S21_NA#29561 Add End
            // 2016/06/16 S21_NA#9855 Add Start
            MDSETMsg mdseTMsg = mdseTMsgRtrnList.get(i);
            if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, mdseTMsg.invtyCtrlFlg.getValue())) {
                continue;
            }
            // 2016/06/16 S21_NA#9855 Add End
            if (ZYPCommonFunc.hasValue(rtnDtlPMsg.rtlSwhCd_B1)) {
                continue;
            }
            if (notHardAllocWhList.contains(rtnDtlPMsg.rtlWhCd_B1.getValue())) {
                continue;
            }
            NLZC215001PMsg gdswPMsg = callGetDefaultSubWHApi(pMsg, rtnDtlPMsg, commonBean);
            if (S21ApiUtil.isXxMsgId(gdswPMsg)) {
                // 2015/12/01  S21_NA#1006 Mod Start
                // List<String> msgIdList = S21ApiUtil.getXxMsgIdList(gdswPMsg);
                // for (String msgId : msgIdList) {
                //     addMsgId3List(pMsg3List, rtnDtlPMsg, msgId);
                // }
                List<S21ApiMessage> msgIdList = S21ApiUtil.getXxMsgList(gdswPMsg);
                for (S21ApiMessage msg : msgIdList) {
                    String msgId = msg.getXxMsgid();
                    String msgPrm[] = msg.getXxMsgPrmArray();
                    addMsgId3List(pMsg3List, rtnDtlPMsg, msgId, msgPrm);
                }
                // 2015/12/01  S21_NA#1006 Mod End
            }
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.rtlSwhCd_B1, gdswPMsg.destRtlSwhCd);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.thirdPtyDspTpCd_B1, gdswPMsg.thirdPtyDspTpCd);
        }
    }

    private static String getDefaultRetailSubWHFromCache(NWZC150001PMsg pMsg, NWZC150001_APMsg aPMsg, Map<Map<String, String>, String> subWhCacheMap) {

        // Mapkey
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("rtlWhCd", aPMsg.rtlWhCd_A1.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        
        if (subWhCacheMap.containsKey(ssmParam)) {
            return subWhCacheMap.get(ssmParam);
        }

        String defaultRtlSubWh = NWZC150001Query.getInstance().getDefaultRetailSubWH(pMsg, aPMsg);
        if (ZYPCommonFunc.hasValue(defaultRtlSubWh)) {
            subWhCacheMap.put(ssmParam, defaultRtlSubWh);
            return defaultRtlSubWh;
        }

        return null;
    }

    private static NLZC215001PMsg callGetDefaultSubWHApi(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlPMsg, NWZC150001DsCpoCommonBean commonBean) {
        NLZC215001PMsg gdswPMsg = new NLZC215001PMsg();

        ZYPEZDItemValueSetter.setValue(gdswPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(gdswPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(gdswPMsg.xxModeCd, NLZC215001Constant.MODE_RMA);
        ZYPEZDItemValueSetter.setValue(gdswPMsg.mdseCd, rtnDtlPMsg.mdseCd_B1);
        ZYPEZDItemValueSetter.setValue(gdswPMsg.svcMachMstrPk, rtnDtlPMsg.svcMachMstrPk_B1);
        ZYPEZDItemValueSetter.setValue(gdswPMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(gdswPMsg.destRtlWhCd, rtnDtlPMsg.rtlWhCd_B1);

        NLZC215001 gdswApi = new NLZC215001();
        gdswApi.execute(gdswPMsg, commonBean.getOnBatchType());

        return gdswPMsg;
    }

    private static void addMsgId3List(List<NWZC150003PMsg> pMsg3List, NWZC150001_rtnDtlPMsg rtnDtlPMsg, String msgId, String msgParam[]) {
        NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
        setMsgId3(pMsg3, msgId, msgParam);
        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
        pMsg3List.add(pMsg3);
    }

    private static void setMsgId3(NWZC150003PMsg pMsg3, String msgId, String[] msgParam) {
        int ix = pMsg3.xxMsgIdList.getValidCount();
        if (ix >= pMsg3.xxMsgIdList.length()) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg3.xxMsgIdList.no(ix).xxMsgId, msgId);
        int msgParamLen = msgParam.length;
        switch (msgParamLen) {
            case 5:
                ZYPEZDItemValueSetter.setValue(pMsg3.xxMsgIdList.no(ix).xxMsgPrmTxt_4, msgParam[4]);
            case 4:
                ZYPEZDItemValueSetter.setValue(pMsg3.xxMsgIdList.no(ix).xxMsgPrmTxt_3, msgParam[3]);
            case 3:
                ZYPEZDItemValueSetter.setValue(pMsg3.xxMsgIdList.no(ix).xxMsgPrmTxt_2, msgParam[2]);
            case 2:
                ZYPEZDItemValueSetter.setValue(pMsg3.xxMsgIdList.no(ix).xxMsgPrmTxt_1, msgParam[1]);
            case 1:
                ZYPEZDItemValueSetter.setValue(pMsg3.xxMsgIdList.no(ix).xxMsgPrmTxt_0, msgParam[0]);
                break;
            default:
                break;
        }
        pMsg3.xxMsgIdList.setValidCount(ix + 1);
    }

    private static void setDefaultFreightCharge(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List) {
        pMsg.addFrtChrgMethCd.clear();
        pMsg.addFrtChrgToCd.clear();

        FRT_CONDTMsg tMsg = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, pMsg.frtCondCd);

        tMsg = (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (!(tMsg == null)) {
            ZYPEZDItemValueSetter.setValue(pMsg.addFrtChrgMethCd, tMsg.frtChrgMethCd);
            ZYPEZDItemValueSetter.setValue(pMsg.addFrtChrgToCd, tMsg.frtChrgToCd);
        }

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            FRT_CONDTMsg inTMsg = new FRT_CONDTMsg(); // 2017/09/20 S21_NA#21284 Add

            if (ZYPCommonFunc.hasValue(aPMsg.frtChrgMethCd_A1) //
                    && ZYPCommonFunc.hasValue(aPMsg.frtChrgToCd_A1)) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(inTMsg.frtCondCd, aPMsg.frtCondCd_A1);

            inTMsg = (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
            if (!(inTMsg == null)) {
                if (!ZYPCommonFunc.hasValue(aPMsg.frtChrgMethCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(aPMsg.frtChrgMethCd_A1, inTMsg.frtChrgMethCd);
                }
                if (!ZYPCommonFunc.hasValue(aPMsg.frtChrgToCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(aPMsg.frtChrgToCd_A1, inTMsg.frtChrgToCd);
                }
            }
        }
    }

    private static void setDefaultCurrency(NWZC150001PMsg pMsg) {
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(aPMsg.ccyCd_A1)) {
                continue;
            }

            PRC_CATGTMsg tMsg = new PRC_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, aPMsg.prcCatgCd_A1);
            tMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(aPMsg.ccyCd_A1, tMsg.ccyCd);
        }

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            if (ZYPCommonFunc.hasValue(rtnDtlPMsg.ccyCd_B1)) {
                continue;
            }

            PRC_CATGTMsg tMsg = new PRC_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, rtnDtlPMsg.prcCatgCd_B1);
            tMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.ccyCd_B1, tMsg.ccyCd);
        }
    }

    private static void setDefaultModel(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, NWZC150001DsCpoCommonBean commonBean) {

        // 2018/05/20 S21_NA#25604 Add Start
        boolean isOrderRetailEquip = NWXC150001DsCheck.isRetailEquipOrder(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue());
        boolean isOrderServiceExchange = NWXC150001DsCheck.isSvcExch(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue());
        boolean needModelOrd = isOrderRetailEquip || isOrderServiceExchange;
        // 2018/05/20 S21_NA#25604 Add End
        // 2015/12/17 S21_NA#1987 Add Start
        // For Performance QC#8166 Mod Start
        // if (!NWZC150001Query.getInstance().isExistOrdCatg(pMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS)) {
//        if (!NWZC150001Common.isExistOrdCatgFromCache(pMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, commonBean)) { 2018/05/20 S21_NA#25604 Mod Condition
        if (!needModelOrd) { // 2018/05/20 S21_NA#25604 Mod Condition
        // For Performance QC#8166 Mod End
            // 2017/03/02 S21_NA#17714-2 Add Start
            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
                // 2017/03/03 S21_NA#17714-3 Add Start
                // 2017/11/21 S21_NA#22555 Mod Start
                //if (!CONFIG_CATG.OUTBOUND.equals(pMsg.cpoConfig.no(i).configCatgCd.getValue())) {
                if (CONFIG_CATG.INBOUND.equals(pMsg.cpoConfig.no(i).configCatgCd.getValue()) &&
                        ZYPCommonFunc.hasValue(pMsg.cpoConfig.no(i).svcConfigMstrPk)) {
                // 2017/11/21 S21_NA#22555 Mod End
                    continue;
                }
                // 2017/03/03 S21_NA#17714-3 Add End
                pMsg.cpoConfig.no(i).mdlDescTxt.clear();
                pMsg.cpoConfig.no(i).mdlId.clear();
            }
            // 2017/03/02 S21_NA#17714-2 Add End
            return;
        }
        // 2015/12/17 S21_NA#1987 Add End

        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(i);

            // 2016/06/02 S21_NA#9107 Add Start
            // 2017/11/21 S21_NA#22555 Mod Start
            //if (!CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
            if (CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue()) &&
                    ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
            // 2017/11/21 S21_NA#22555 Mod End
                continue;
            }
            // 2016/06/02 S21_NA#9107 Add End

            // 2017/11/01 S21_NA#22140 Add Start
            // If this config is related to Credit or Rebill, no need to apply Model.
            if (NWZC150001Common.isCreditConfig(configPMsg, pMsg)) {
                continue;
            }
            if (NWZC150001Common.isAllDetailRebillConfig(configPMsg, pMsg)) {
                continue;
            }
            // 2017/11/01 S21_NA#22140 Add End
            // 2016/02/23 S21_NA#3239 Mod Start
//          if (ZYPCommonFunc.hasValue(configPMsg.mdlId)) {
//              continue;
//          }
            BigDecimal mdlId = configPMsg.mdlId.getValue();
            // 2016/02/23 S21_NA#3239 Mod End
            // 2017/11/21 S21_NA#22555 Mod Start
            //NSZC048001PMsg svcMdlPMsg = callSvcMdlApi(configPMsg, pMsg.A, pMsg, commonBean);
            // 2018/03/20 S21_NA#24698 Add Start
            List<String> ibItemList = new ArrayList<String>(0);
            // 2018/03/20 S21_NA#24698 Add End
            // 2018/05/20 S21_NA#25604 Mod Start
//            NSZC048001PMsg svcMdlPMsg = callSvcMdlApi(configPMsg, pMsg, commonBean, ibItemList); // 2018/03/20 S21_NA#24698 Add ibItemList
            NWXC150001SvcMdlFuncParamBean funcParam = callSvcMdlApi(configPMsg, pMsg, commonBean);
            // 2018/05/20 S21_NA#25604 Mod End
            // 2017/11/21 S21_NA#22555 Mod End

            // 2018/05/20 S21_NA#25604 Mod Start
//            // 2015/11/20  S21_NA#1006 Add Start
//            if (null == svcMdlPMsg) {
//                continue;
//            }
//            // 2015/12/01  S21_NA#1006 Mod End
            if (funcParam == null) {
                continue;
            }
            // 2018/05/20 S21_NA#25604 Mod End
            // if (S21ApiUtil.isXxMsgId(svcMdlPMsg)) { 2018/05/20 S21_NA#25604 Mod
            if ((funcParam.getErrMsgIdList() != null && !funcParam.getErrMsgIdList().isEmpty()) //
                    || !ZYPCommonFunc.hasValue(funcParam.getMdlId())) { // 2018/05/20 S21_NA#25604 Mod condition
                // 2015/12/01  S21_NA#1006 Mod Start
                // List<String> msgIdList = S21ApiUtil.getXxMsgIdList(svcMdlPMsg);
                // for (String msgId : msgIdList) {
                //     NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                //     setMsgId2(pMsg2, msgId);
                //     ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, configPMsg.dsOrdPosnNum);
                //     pMsg2List.add(pMsg2);
                // }
                // 2016/02/23 S21_NA#3239 Mod Start
                // 2017/11/21 S21_NA#22555 Mod Start
//                if (MODE_SAVE.equals(pMsg.xxModeCd.getValue())) {
//                    if (BIZ_APP_ID_ORDER_ENTRY.equals(pMsg.bizAppId.getValue())) {
//                        if (!ZYPConstant.FLG_ON_Y.equals(pMsg.xxWrnSkipFlg_00.getValue())) {
//                            pMsg.xxWrnSkipFlg_00.setValue(ZYPConstant.FLG_ON_Y);
//
//                            String msgId = NWZM1926W;
//                            String msgParam[] = new String[0];
//                            addMsgId2List(pMsg2List, configPMsg, msgId, msgParam);
//
//                        }
//                    }
//
//                } else {
//                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(svcMdlPMsg);
//                    for (S21ApiMessage msg : msgList) {
//                        String msgId = msg.getXxMsgid();
//                        String msgParam[] = msg.getXxMsgPrmArray();
//                        addMsgId2List(pMsg2List, configPMsg, msgId, msgParam);
//                    }
//                }
                // 2018/02/28 S21_NA#24117 del start
//                if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
//
//                    if (MODE_SAVE.equals(pMsg.xxModeCd.getValue())) {
//                        if (BIZ_APP_ID_ORDER_ENTRY.equals(pMsg.bizAppId.getValue())) {
//                            if (!ZYPConstant.FLG_ON_Y.equals(pMsg.xxWrnSkipFlg_00.getValue())) {
//                                pMsg.xxWrnSkipFlg_00.setValue(ZYPConstant.FLG_ON_Y);
//
//                                String msgId = NWZM1926W;
//                                String msgParam[] = new String[0];
//                                addMsgId2List(pMsg2List, configPMsg, msgId, msgParam);
//
//                            }
//                        }
//
//                    } else {
//                        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(svcMdlPMsg);
//                        for (S21ApiMessage msg : msgList) {
//                            String msgId = msg.getXxMsgid();
//                            String msgParam[] = msg.getXxMsgPrmArray();
//                            addMsgId2List(pMsg2List, configPMsg, msgId, msgParam);
//                        }
//                    }
//                }
                // 2018/02/28 S21_NA#24117 del end
                // 2017/11/21 S21_NA#22555 Mod End
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(svcMdlPMsg);
//                for (S21ApiMessage msg : msgList) {
//                    String msgId = msg.getXxMsgid();
//                    String msgParam[] = msg.getXxMsgPrmArray();
//                    addMsgId2List(pMsg2List, configPMsg, msgId, msgParam);
//                }
                // 2015/12/01  S21_NA#1006 Mod End
                // 2016/02/23 S21_NA#3239 Mod End
                // 2016/02/23 S21_NA#3239 Mod Start
                configPMsg.mdlId.clear();
                configPMsg.mdlDescTxt.clear();
                // 2016/02/23 S21_NA#3239 Mod End
                continue;
            }
            // 2018/05/20 S21_NA#25604 Mod Start
//            ZYPEZDItemValueSetter.setValue(configPMsg.mdlId, svcMdlPMsg.mdlId);
            ZYPEZDItemValueSetter.setValue(configPMsg.mdlId, funcParam.getMdlId());
            ZYPEZDItemValueSetter.setValue(configPMsg.mdlDescTxt, funcParam.getMdlNm());
            // 2018/05/20 S21_NA#25604 Mod End
            // 2016/02/23 S21_NA#3239 Mod Start
            // 2018/05/24 S21_NA#25604-2 Del Start
//            if (ZYPCommonFunc.hasValue(configPMsg.mdlId)) {
//                ZYPEZDItemValueSetter.setValue(configPMsg.mdlDescTxt, getDsMdlDescTxt(pMsg, configPMsg.mdlId.getValue()));
//            }
            // 2018/05/24 S21_NA#25604-2 Del End
            if (ZYPCommonFunc.hasValue(mdlId) && ZYPCommonFunc.hasValue(configPMsg.mdlId)) {
                if (configPMsg.mdlId.getValue().compareTo(mdlId) != 0) {
                    pMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
                }
            } else if (!ZYPCommonFunc.hasValue(mdlId) && ZYPCommonFunc.hasValue(configPMsg.mdlId)) {
                pMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
            // 2016/02/23 S21_NA#3239 Mod End
            // 2018/01/11 S21_NA#23329 Add Start
            // 2018/05/20 S21_NA#25604 Mod Start
//            checkSoftModelSetting(pMsg, configPMsg, svcMdlPMsg, pMsg2List, ibItemList); // 2018/03/20 S21_NA#24698 Add ibItemList
            checkSoftModelSetting(pMsg, configPMsg, funcParam, pMsg2List, ibItemList);
            // 2018/05/20 S21_NA#25604 Mod End
            // 2018/01/11 S21_NA#23329 Add End
        }

    }

 // 2018/05/20 S21_NA#25604 Del Start
//    // 2017/11/21 S21_NA#22555 Mod Start
////    private static NSZC048001PMsg callSvcMdlApi(NWZC150001_cpoConfigPMsg configPMsg, NWZC150001_APMsgArray aPMsgAry, NWZC150001PMsg pMsg, NWZC150001DsCpoCommonBean commonBean) {
//    private static NSZC048001PMsg callSvcMdlApi(NWZC150001_cpoConfigPMsg configPMsg //
//            , NWZC150001PMsg pMsg //
//            , NWZC150001DsCpoCommonBean commonBean //
//            , List<String> ibItemList) { // 2018/03/20 S21_NA#24698 Add ibItemList
//    // 2017/11/21 S21_NA#22555 Mod End
//        NSZC048001PMsg smPMsg = new NSZC048001PMsg();
//
//        ZYPEZDItemValueSetter.setValue(smPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(smPMsg.slsDt, pMsg.slsDt.getValue());
//
//        // 2015/11/20  S21_NA#1006 Update Start
//        // setMdseCdForSvcMdlPMsg(smPMsg, configPMsg.dsOrdPosnNum.getValue(), aPMsgAry);
//        // 2017/11/21 S21_NA#22555 Mod Start
//        //boolean isCallApi = setMdseCdForSvcMdlPMsg(smPMsg, configPMsg.dsOrdPosnNum.getValue(), aPMsgAry);
//        boolean isCallApi = setMdseCdForSvcMdlPMsg(smPMsg, configPMsg, pMsg, ibItemList); // 2018/03/20 S21_NA#24698 Add ibItemList
//        // 2017/11/21 S21_NA#22555 Mod End
//        if (!isCallApi) {
//            return null;
//        }
//        // 2015/11/20  S21_NA#1006 Update End
//
//        NSZC048001 smApi = new NSZC048001();
//        smApi.execute(smPMsg, commonBean.getOnBatchType());
//
//        return smPMsg;
//    }
    // 2018/05/20 S21_NA#25604 Del End

    // 2017/11/21 S21_NA#22555 Mod Start
//    /**
//     * set NSZC0480  Service Model API
//     * @param smPMsg  Service Model API PMessage
//     * @param dsOrdPosnNum ds Order Position Number
//     * @param aPMsgAry DS CPO Update API Detail PMsg
//     * @return true: need to call  NSZC0480  Service Model API false: needless to callNSZC0480  Service Model API
//     */
//    private static boolean setMdseCdForSvcMdlPMsg(NSZC048001PMsg smPMsg, String dsOrdPosnNum, NWZC150001_APMsgArray aPMsgAry) {
//        boolean isCallApi = false;
//        for (int i = 0; i < aPMsgAry.getValidCount(); i++) {
//            NWZC150001_APMsg aPMsg = aPMsgAry.no(i);
//            if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
//                continue;
//            }
//            if (ZYPConstant.FLG_ON_Y.equals(aPMsg.baseCmptFlg_A1.getValue())) {
//                ZYPEZDItemValueSetter.setValue(smPMsg.prntMdseCd, aPMsg.mdseCd_A1);
//                isCallApi = true;
//            } else {
//                int ix = smPMsg.xxChildMdseCdList.getValidCount();
//                ZYPEZDItemValueSetter.setValue(smPMsg.xxChildMdseCdList.no(ix++).childMdseCd, aPMsg.mdseCd_A1);
//                smPMsg.xxChildMdseCdList.setValidCount(ix);
//
//                // 2016/07/13 S21_NA#11629 Add Start
//                if (ix >= smPMsg.xxChildMdseCdList.length()) {
//                    break;
//                }
//                // 2016/07/13 S21_NA#11629 Add End
//            }
//        }
//        return isCallApi;
//    }
    // 2018/05/20 S21_NA#25604 Del Start
//    /**
//     * set NSZC0480  Service Model API
//     * @param smPMsg  Service Model API PMessage
//     * @param configPMsg DS CPO Config PMsg
//     * @param pMsg DS CPO Update API PMsg
//     * @return true: need to call  NSZC0480  Service Model API false: needless to callNSZC0480  Service Model API
//     */
//    private static boolean setMdseCdForSvcMdlPMsg(NSZC048001PMsg smPMsg //
//            , NWZC150001_cpoConfigPMsg configPMsg //
//            , NWZC150001PMsg pMsg  //
//            , List<String> ibItemList) { // 2018/03/20 S21_NA#24698 Add ibItemList
//        boolean isCallApi = false;
//        // 2018/01/11 S21_NA#23329 Add Start
//        boolean isChildOfModel = false;
//        // 2018/01/11 S21_NA#23329 Add End
//        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
//
//        // 2018/03/20 S21_NA#24698 Add Start
//        CONFIG_TPTMsg tMsg = getConfigTp(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue());
//        boolean isUseConfigItem = false;
//        if (tMsg != null) {
//            boolean atCust  = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, tMsg.configExstAtCustFlg.getValue());
//            boolean atWh = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, tMsg.configExstWhFlg.getValue());
//            isUseConfigItem = atCust | atWh;
//        }
//        isUseConfigItem &= ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk);
//        boolean alreadySetBaseCmptFlg = false;
//        if (isUseConfigItem) {
//            SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = getSvcConfigMstr(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());
//            DS_MDLTMsg dsMdlTMsg = null;
//            if (svcConfigMstrTMsg != null && ZYPCommonFunc.hasValue(svcConfigMstrTMsg.mdlId)) {
//                dsMdlTMsg = getDsMdl(pMsg, svcConfigMstrTMsg.mdlId.getValue());
//            }
//            boolean isSoftWareMdl = false;
//            if (dsMdlTMsg != null && S21StringUtil.isEquals(SVC_MDL_TP.SOFTWARE, dsMdlTMsg.svcMdlTpCd.getValue())) {
//                isSoftWareMdl = true;
//            }
//            List<Map<String, String>> svcConfInfoList = NWZC150001Query.getInstance().getSvcConfItemInfo(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());
//            if (isSoftWareMdl) {
//                for (Map<String, String> svcConfInfo : svcConfInfoList) {
//                    String mdseCd = svcConfInfo.get("MDSE_CD");
//                    ibItemList.add(mdseCd);
//                    if (!alreadySetBaseCmptFlg) {
//                        if (NWZC150001Query.getInstance().asItemSoftModelParent(pMsg, mdseCd)) {
//                            alreadySetBaseCmptFlg = true;
//                            ZYPEZDItemValueSetter.setValue(smPMsg.prntMdseCd, mdseCd);
//                        }
//                    } else {
//                        int ix = smPMsg.xxChildMdseCdList.getValidCount();
//                        ZYPEZDItemValueSetter.setValue(smPMsg.xxChildMdseCdList.no(ix++).childMdseCd, mdseCd);
//                        smPMsg.xxChildMdseCdList.setValidCount(ix);
//                    }
//                }
//            } else {
//                boolean isSetParentAsIstlBase = false; // 2018/04/10 S21_NA#24842 Move To
//                for (Map<String, String> svcConfInfo : svcConfInfoList) {
//                    String mdseCd = svcConfInfo.get("MDSE_CD");
//                    ibItemList.add(mdseCd);
//                    // boolean isSetParentAsIstlBase = false; // 2018/04/10 S21_NA#24842 Move From
//                    boolean isIstlBaseFlg = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, svcConfInfo.get("INSTL_BASE_CTRL_FLG"));
//                    boolean isMachineItem = ZYPCommonFunc.hasValue(svcConfInfo.get("MDSE_TP_CTX_TP_CD"));
//                    if (!alreadySetBaseCmptFlg) {
//                        if (isMachineItem) {
//                            String curBaseItem = smPMsg.prntMdseCd.getValue();
//                            if (ZYPCommonFunc.hasValue(curBaseItem)) {
//                                int ix = smPMsg.xxChildMdseCdList.getValidCount();
//                                ZYPEZDItemValueSetter.setValue(smPMsg.xxChildMdseCdList.no(ix++).childMdseCd, curBaseItem);
//                                smPMsg.xxChildMdseCdList.setValidCount(ix);
//                            }
//                            alreadySetBaseCmptFlg = true;
//                            ZYPEZDItemValueSetter.setValue(smPMsg.prntMdseCd, mdseCd);
//                        } else if (isIstlBaseFlg && !isSetParentAsIstlBase) {
//                            isSetParentAsIstlBase = true;
//                            ZYPEZDItemValueSetter.setValue(smPMsg.prntMdseCd, mdseCd);
//                        // 2018/04/10 S21_NA#24842 Add Start
//                        } else {
//                            int ix = smPMsg.xxChildMdseCdList.getValidCount();
//                            ZYPEZDItemValueSetter.setValue(smPMsg.xxChildMdseCdList.no(ix++).childMdseCd, mdseCd);
//                            smPMsg.xxChildMdseCdList.setValidCount(ix);
//                        }
//                        // 2018/04/10 S21_NA#24842 Add End
//                    } else {
//                        int ix = smPMsg.xxChildMdseCdList.getValidCount();
//                        ZYPEZDItemValueSetter.setValue(smPMsg.xxChildMdseCdList.no(ix++).childMdseCd, mdseCd);
//                        smPMsg.xxChildMdseCdList.setValidCount(ix);
//                    }
//                }
//            }
//        }
//        // 2018/03/20 S21_NA#24698 Add End
//        if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
//            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//                NWZC150001_APMsg aPMsg = pMsg.A.no(i);
//                if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
//                    continue;
//                }
//                if (ZYPConstant.FLG_ON_Y.equals(aPMsg.baseCmptFlg_A1.getValue()) && !alreadySetBaseCmptFlg) { // 2018/03/20 S21_NA#24698 Add !alreadySetBaseCmptFlg
//                    ZYPEZDItemValueSetter.setValue(smPMsg.prntMdseCd, aPMsg.mdseCd_A1);
//                    isCallApi = true;
//                } else {
//                    int ix = smPMsg.xxChildMdseCdList.getValidCount();
//                    ZYPEZDItemValueSetter.setValue(smPMsg.xxChildMdseCdList.no(ix++).childMdseCd, aPMsg.mdseCd_A1);
//                    smPMsg.xxChildMdseCdList.setValidCount(ix);
//
//                    // 2018/01/11 S21_NA#23329 Add Start
//                    if (!isChildOfModel) {
//                        isChildOfModel = isChildItemOfModel(smPMsg.glblCmpyCd.getValue(), aPMsg.mdseCd_A1.getValue());
//                    }
//                    // 2018/01/11 S21_NA#23329 Add End
//                    if (ix >= smPMsg.xxChildMdseCdList.length()) {
//                        break;
//                    }
//                }
//            }
//
//        } else if (CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue())) {
//            for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
//                NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
//                if (!dsOrdPosnNum.equals(rtnDtlPMsg.dsOrdPosnNum_B1.getValue())) {
//                    continue;
//                }
//                if (ZYPConstant.FLG_ON_Y.equals(rtnDtlPMsg.baseCmptFlg_B1.getValue()) && !alreadySetBaseCmptFlg) { // 2018/03/20 S21_NA#24698 Add !alreadySetBaseCmptFlg
//                    ZYPEZDItemValueSetter.setValue(smPMsg.prntMdseCd, rtnDtlPMsg.mdseCd_B1);
//                    isCallApi = true;
//                } else {
//                    int ix = smPMsg.xxChildMdseCdList.getValidCount();
//                    ZYPEZDItemValueSetter.setValue(smPMsg.xxChildMdseCdList.no(ix++).childMdseCd, rtnDtlPMsg.mdseCd_B1);
//                    smPMsg.xxChildMdseCdList.setValidCount(ix);
//
//                    // 2018/01/11 S21_NA#23329 Add Start
//                    if (!isChildOfModel) {
//                        isChildOfModel = isChildItemOfModel(smPMsg.glblCmpyCd.getValue(), rtnDtlPMsg.mdseCd_B1.getValue());
//                    }
//                    // 2018/01/11 S21_NA#23329 Add End
//                    if (ix >= smPMsg.xxChildMdseCdList.length()) {
//                        break;
//                    }
//                }
//            }
//        }
//        // 2018/01/11 S21_NA#23329 Add Start
//        if (!isCallApi && isChildOfModel) {
//            isCallApi = true;
//        }
//        // 2018/01/11 S21_NA#23329 Add End
//        return isCallApi;
//    }
    // 2018/05/20 S21_NA#25604 Del End
    // 2017/11/21 S21_NA#22555 Mod End

    private static void addMsgId2List(List<NWZC150002PMsg> pMsg2List, NWZC150001_cpoConfigPMsg confPMsg, String msgId, String msgParam[]) {
        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
        setMsgId2(pMsg2, msgId, msgParam);
        ZYPEZDItemValueSetter.setValue(pMsg2.dsOrdPosnNum, confPMsg.dsOrdPosnNum);
        pMsg2List.add(pMsg2);
    }

    private static void setMsgId2(NWZC150002PMsg pMsg2, String msgId, String[] msgParam) {
        int ix = pMsg2.xxMsgIdList.getValidCount();
        if (ix >= pMsg2.xxMsgIdList.length()) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgId, msgId);
        int msgParamLen = msgParam.length;
        switch (msgParamLen) {
            case 5:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_4, msgParam[4]);
            case 4:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_3, msgParam[3]);
            case 3:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_2, msgParam[2]);
            case 2:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_1, msgParam[1]);
            case 1:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_0, msgParam[0]);
                break;
            default:
                break;
        }
        pMsg2.xxMsgIdList.setValidCount(ix + 1);
    }

    /**
     * Get DS Model Desc Text
     * @param bizMsg NWAL1500CMsg
     * @param mdlId Model ID
     * @return DS Model Desc Text
     */
    private static String getDsMdlDescTxt(NWZC150001PMsg pMsg, BigDecimal mdlId) {

        // 2016/03/07 S21_NA#3239 Mod Start
//        MDL_NMTMsg mdlNmTMsg = new MDL_NMTMsg();
//        ZYPEZDItemValueSetter.setValue(mdlNmTMsg.t_GlblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(mdlNmTMsg.t_MdlId, mdlId);
//        mdlNmTMsg = (MDL_NMTMsg) EZDTBLAccessor.findByKey(mdlNmTMsg);
//        if (mdlNmTMsg != null) {
//            return mdlNmTMsg.t_MdlNm.getValue();
//        }
        // 2018/03/20 S21_NA#24698 Mod, using getDsMdl() Start
        DS_MDLTMsg dsMdlTMsg = getDsMdl(pMsg, mdlId);
        // 2018/03/20 S21_NA#24698 Mod, using getDsMdl() End
        if (dsMdlTMsg != null) {
            return dsMdlTMsg.mdlDescTxt.getValue();
        }
        // 2016/03/07 S21_NA#3239 Mod Start

        return null;
    }

    private static void setDefaultVendor(NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> pMsg2List //
            , List<MDSETMsg> mdseTMsgList
            , S21ApiMessageIdMgr msgIdMgr
            , NWZC150001DsCpoCommonBean commonBean) {
        String dsWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", pMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(dsWhCd)) {
            msgIdMgr.addXxMsgId(NWZM1503E, pMsg);
            return;
        }

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            if (dsWhCd.equals(aPMsg.rtlWhCd_A1.getValue())) {
                NPZC113001PMsg aslApiMsg = callAslApi(aPMsg, pMsg, commonBean);
                if (S21ApiUtil.isXxMsgId(aslApiMsg)) {
                    List<String> msgIdList = S21ApiUtil.getXxMsgIdList(aslApiMsg);
                    for (String msgId : msgIdList) {
                        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                        NWZC150001Common.setMsgId2(pMsg2, msgId);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                        pMsg2List.add(pMsg2);
                    }
                    continue;
                }
                // QC#56978 Add Start
                if (ZYPCommonFunc.hasValue(aslApiMsg.vndCd)) {
                    VNDTMsg keyVndTMsg = new VNDTMsg();
                    keyVndTMsg.setSQLID("001");
                    keyVndTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
                    keyVndTMsg.setConditionValue("vndCd01", aslApiMsg.vndCd.getValue());

                    VNDTMsgArray rsltArray = (VNDTMsgArray) EZDTBLAccessor.findByCondition(keyVndTMsg);
                    if (rsltArray == null || rsltArray.getValidCount() == 0) {
                        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                        NWZC150001Common.setMsgId4(pMsg2, NWAM0983E, new String[] {aslApiMsg.vndCd.getValue() }); // TODO NEW Error Code
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                        pMsg2List.add(pMsg2);
                    }
                }
                // QC#56978 Add End
                ZYPEZDItemValueSetter.setValue(aPMsg.invtyLocCd_A1, aslApiMsg.vndCd);
            }
        }
    }

    private static NPZC113001PMsg callAslApi(NWZC150001_APMsg aPMsg, NWZC150001PMsg pMsg, NWZC150001DsCpoCommonBean commonBean) {
        NPZC113001PMsg aslPMsg = new NPZC113001PMsg();

        ZYPEZDItemValueSetter.setValue(aslPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(aslPMsg.slsDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(aslPMsg.mdseCd, aPMsg.mdseCd_A1);

        NPZC113001 aslApi = new NPZC113001();
        aslApi.execute(aslPMsg, commonBean.getOnBatchType());

        return aslPMsg;
    }

    private static void setDefaultCsmpPriceList(NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> pMsg2List //
            , List<NWZC150003PMsg> pMsg3List //
            , List<MDSETMsg> mdseTMsgList
            // 2017/05/11 S21_NA#Review structure Lv.2 Add Start
            , NWZC150001CpouLocalCache localCache
            , NWZC150001DsCpoCommonBean commonBean) {
            // 2017/05/11 S21_NA#Review structure Lv.2 Add End
        if (!ZYPCommonFunc.hasValue(pMsg.csmpContrNum) //
                && !ZYPCommonFunc.hasValue(pMsg.dlrRefNum)) {
            return;
        }

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aPMsg.csmpPrcListCd_A1)) {
                NWZC157001PMsg prcApiPMsg //
                // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//                = callPricingApiForGetPrcListForDtl(pMsg, aPMsg, NWZC157001.GET_PRICE_LIST, PRC_CTX_TP.CSMP_CREDIT, ZYPConstant.FLG_OFF_N);
                = callPricingApiForGetPrcListForDtl(pMsg, aPMsg, NWZC157001.GET_PRICE_LIST, PRC_CTX_TP.CSMP_CREDIT, ZYPConstant.FLG_OFF_N, localCache, commonBean);
                // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
                // S21_NA#3250 Mod Start
//                if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
//                    NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
//                    EZDMsg.copy(prcApiPMsg, null, pMsg2, null);
//                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
//                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
//                    pMsg2List.add(pMsg2);
//                    return;
//                }
//                if (prcApiPMsg.xxPrcList.getValidCount() == 0) {
//                    NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
//                    setMsgId2(pMsg2, NWZM1419E);
//                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
//                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
//                    pMsg2List.add(pMsg2);
//                    return;
//                }
                if (!S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                    ZYPEZDItemValueSetter.setValue(aPMsg.csmpPrcListCd_A1, prcApiPMsg.xxPrcList.no(0).prcCatgCd);
                }
                // S21_NA#3250 Mod End
            }
        }

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            if (!ZYPCommonFunc.hasValue(rtnDtlPMsg.csmpPrcListCd_B1)) {
                NWZC157001PMsg prcApiPMsg //
                // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//                = callPricingApiForGetPrcListForRtn(pMsg, rtnDtlPMsg, NWZC157001.GET_PRICE_LIST, PRC_CTX_TP.CSMP_CREDIT, ZYPConstant.FLG_OFF_N);
                = callPricingApiForGetPrcListForRtn(pMsg, rtnDtlPMsg, NWZC157001.GET_PRICE_LIST, PRC_CTX_TP.CSMP_CREDIT, ZYPConstant.FLG_OFF_N, localCache, commonBean);
                // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
                 // S21_NA#3250 Mod Start
//                if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
//                    NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
//                    EZDMsg.copy(prcApiPMsg, null, pMsg3, null);
//                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
//                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
//                    pMsg3List.add(pMsg3);
//                    return;
//                }
//                if (prcApiPMsg.xxPrcList.getValidCount() == 0) {
//                    NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
//                    setMsgId3(pMsg3, NWZM1419E);
//                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineNum, rtnDtlPMsg.cpoDtlLineNum_B1);
//                    ZYPEZDItemValueSetter.setValue(pMsg3.cpoDtlLineSubNum, rtnDtlPMsg.cpoDtlLineSubNum_B1);
//                    pMsg3List.add(pMsg3);
//                    return;
//                }
                if (!S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.csmpPrcListCd_B1, prcApiPMsg.xxPrcList.no(0).prcCatgCd);
                }
                // S21_NA#3250 Mod End
            }
        }
    }

    private static NWZC157001PMsg callPricingApiForGetPrcListForDtl(NWZC150001PMsg pMsg, NWZC150001_APMsg aPMsg, String modeCd, String prcCtxTpCd, String prcCatgOpeFlg, NWZC150001CpouLocalCache localCache, NWZC150001DsCpoCommonBean commonBean) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, modeCd);
        // QC#9437 2016/06/21 Mod Start
        // if (ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.prcBaseDt);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, slsDt);
        // }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt.getValue());
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, prcCtxTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd //
                , NWXC150001DsCheck.getLineBizTpCd(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.dsOrdCatgCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, pMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, aPMsg.csmpContrNum_A1);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, aPMsg.dlrRefNum_A1);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, pMsg.prcContrNum);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, NWZC150001.getCoaBrCd(pMsg.slsRepCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd
                , NWZC150001Common.getCoaBrCd(pMsg.glblCmpyCd.getValue(), pMsg.slsRepCd.getValue(), localCache));
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, prcCatgOpeFlg);

        new NWZC157001().execute(prcApiPMsg, commonBean.getOnBatchType());
        return prcApiPMsg;
    }

    private static NWZC157001PMsg callPricingApiForGetPrcListForRtn(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlPMsg, String modeCd, String prcCtxTpCd, String prcCatgOpeFlg, NWZC150001CpouLocalCache localCache, NWZC150001DsCpoCommonBean commonBean) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, modeCd);
        // QC#9437 2016/06/21 Mod Start
        // if (ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.prcBaseDt);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, slsDt);
        // }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt.getValue());
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, prcCtxTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd //
                , NWXC150001DsCheck.getLineBizTpCd(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.dsOrdCatgCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, pMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, rtnDtlPMsg.csmpContrNum_B1);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, rtnDtlPMsg.dlrRefNum_B1);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, pMsg.prcContrNum);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, NWZC150001.getCoaBrCd(pMsg.slsRepCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd
                , NWZC150001Common.getCoaBrCd(pMsg.glblCmpyCd.getValue(), pMsg.slsRepCd.getValue(), localCache));
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, prcCatgOpeFlg);

        new NWZC157001().execute(prcApiPMsg, commonBean.getOnBatchType());
        return prcApiPMsg;
    }

    private static void setDefaultCarrierServiceLevelForDtl(NWZC150001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.carrSvcLvlCd)) {
            return;
        }
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(aPMsg.carrSvcLvlCd_A1)) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(aPMsg.carrSvcLvlCd_A1, pMsg.carrSvcLvlCd);
        }
        return;
    }

    private static void setDefaultCarrier(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List) {

        // For Performance QC#8166 Add Start
        Map<String, String> carrierCacheMap = new HashMap<String, String>();
        // For Performance QC#8166 Add End

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(aPMsg.carrSvcLvlCd_A1)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(aPMsg.carrCd_A1)) {
                continue;
            }

            // For Performance QC#8166 Mod Start
            // String defaultCarrCd = NWZC150001Query.getInstance().getDefaultCarrCd(pMsg, aPMsg);
            String defaultCarrCd = getDefaultCarrierFromCache(pMsg, aPMsg, carrierCacheMap);
            // For Performance QC#8166 Mod End

            if (ZYPCommonFunc.hasValue(defaultCarrCd)) {
                ZYPEZDItemValueSetter.setValue(aPMsg.carrCd_A1, defaultCarrCd);
            }
        }
    }

    private static String getDefaultCarrierFromCache(NWZC150001PMsg pMsg, NWZC150001_APMsg aPMsg, Map<String, String> carrierCacheMap) {

        String carrSvcLvlCd = aPMsg.carrSvcLvlCd_A1.getValue();
        if (carrierCacheMap.containsKey(carrSvcLvlCd)) {
            return carrierCacheMap.get(carrSvcLvlCd);
        }

        String defaultCarrCd = NWZC150001Query.getInstance().getDefaultCarrCd(pMsg, aPMsg);
        if (ZYPCommonFunc.hasValue(defaultCarrCd)) {
            carrierCacheMap.put(carrSvcLvlCd, defaultCarrCd);
            return defaultCarrCd;
        }

        return null;
    }

    private static void setDefaultHddEraseRemovalFlg(NWZC150001PMsg pMsg, List<MDSETMsg> mdseTMsgList, List<NWZC150002PMsg> pMsg2List, NWZC150001DsCpoCommonBean commonBean) {
        //QC#14021
        //if (!NWXC150001DsCheck.isRetailEquipOrder(glblCmpyCd, pMsg.dsOrdCatgCd.getValue())) {
        // 2016/11/01 S21_NA#10965 Mod Start
        commonBean.setIsRetailEquipOrder(NWXC150001DsCheck.isRetailEquipOrder(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue()));
        if (!commonBean.getIsRetailEquipOrder()) {
        // 2016/11/01 S21_NA#10965 Mod End
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                NWZC150001_APMsg aPMsg = pMsg.A.no(i);
                ZYPEZDItemValueSetter.setValue(aPMsg.hardDriveEraseInclFlg_A1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(aPMsg.hardDriveRmvInclFlg_A1, ZYPConstant.FLG_OFF_N);
            }
            return;
        }

        String coaMdseTpCd = NWXC150001DsCheck.getCoaMdseTpCd(pMsg.glblCmpyCd.getValue());
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            MDSETMsg mdseTMsg = mdseTMsgList.get(i);

            // 2016/05/13 S21_NA#8297 Add Start
            if (!ZYPCommonFunc.hasValue(aPMsg.hardDriveEraseInclFlg_A1)) {
                ZYPEZDItemValueSetter.setValue(aPMsg.hardDriveEraseInclFlg_A1, ZYPConstant.FLG_OFF_N);
            }

            if (!ZYPCommonFunc.hasValue(aPMsg.hardDriveRmvInclFlg_A1)) {
                ZYPEZDItemValueSetter.setValue(aPMsg.hardDriveRmvInclFlg_A1, ZYPConstant.FLG_OFF_N);
            }
            // 2016/05/13 S21_NA#8297 Add End

            if (NWXC150001DsCheck.getCoaMdseTp(pMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue()).equals(coaMdseTpCd)) {
                Map<String, String> map = NWXC150001DsCheck.getDefaultHddEraseRemovalFlg(pMsg.glblCmpyCd.getValue(), aPMsg.prcCatgCd_A1.getValue());
                if (map == null) {
                    NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                    NWZC150001Common.setMsgId2(pMsg2, NWZM1505E);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, aPMsg.cpoDtlLineNum_A1);
                    ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, aPMsg.cpoDtlLineSubNum_A1);
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(aPMsg.hardDriveEraseInclFlg_A1, (String) map.get("HARD_DRIVE_ERASE_INCL_FLG"));
                ZYPEZDItemValueSetter.setValue(aPMsg.hardDriveRmvInclFlg_A1, (String) map.get("HARD_DRIVE_RMV_INCL_FLG"));
            } else {
                ZYPEZDItemValueSetter.setValue(aPMsg.hardDriveEraseInclFlg_A1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(aPMsg.hardDriveRmvInclFlg_A1, ZYPConstant.FLG_OFF_N);
            }
        }
    }

    private static void setDefaultCustInstlFlg(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List) {
        String dsOrdPosnNum = "";
        String cpoOrdNum = pMsg.cpoOrdNum.getValue();  // QC#14593 2016/09/28 Add
        BigDecimal dsCpoConfigPk = BigDecimal.ZERO;    // QC#14593 2016/09/28 Add
        String custIstlFlg = ZYPConstant.FLG_OFF_N;
        // int ixCinfig = 0; // 2016/06/02 S21_NA#9107 Del
        // 2016/08/01 S21_NA#12637 Del Start
//        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
//            if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
//                dsOrdPosnNum = aPMsg.dsOrdPosnNum_A1.getValue();
//                // 2016/06/02 S21_NA#9107 Mod Start
////                NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(ixCinfig++);
//                NWZC150001_cpoConfigPMsg configPMsg = getOutBoundConfigPMsg(dsOrdPosnNum, pMsg);
//                if (configPMsg == null) {
//                    continue;
//                }
//                // 2016/06/02 S21_NA#9107 Mod End
//                custIstlFlg = NWXC150001DsCheck.getDefaultCustIstlFlg(glblCmpyCd, configPMsg.mdlId.getValue());
//                if (!ZYPCommonFunc.hasValue(custIstlFlg)) {
//                    custIstlFlg = ZYPConstant.FLG_OFF_N;
//                }
//            }
//            ZYPEZDItemValueSetter.setValue(aPMsg.custIstlFlg_A1, custIstlFlg);
//        }
        // 2016/08/01 S21_NA#12637 Del End
        // 2016/08/01 S21_NA#12637 Add Start
        for (int sltConfigIdx = 0; sltConfigIdx < pMsg.cpoConfig.getValidCount(); sltConfigIdx++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(sltConfigIdx);
            if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configPMsg.configCatgCd.getValue())) {
                continue;
            }
            // QC#14593 2016/09/28 Mod Start
            //custIstlFlg = NWXC150001DsCheck.getDefaultCustIstlFlg(glblCmpyCd, configPMsg.mdlId.getValue());
            //if (!ZYPCommonFunc.hasValue(custIstlFlg)) {
            //     custIstlFlg = ZYPConstant.FLG_OFF_N;
            //}
            dsCpoConfigPk = configPMsg.dsCpoConfigPk.getValue();
            custIstlFlg = NWXC150001DsCheck.getSvcIstlRuleFlg(pMsg.glblCmpyCd.getValue(), cpoOrdNum, dsCpoConfigPk);
            if (!ZYPCommonFunc.hasValue(custIstlFlg)) {
                custIstlFlg = NWXC150001DsCheck.getDefaultCustIstlFlg(pMsg.glblCmpyCd.getValue(), configPMsg.mdlId.getValue());
                if (!ZYPCommonFunc.hasValue(custIstlFlg)) {
                    custIstlFlg = ZYPConstant.FLG_OFF_N;
                }
            }
            dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
            // QC#14593 2016/09/28 Mod End
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                NWZC150001_APMsg aPMsg = pMsg.A.no(i);
                if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(aPMsg.custIstlFlg_A1, custIstlFlg);
            }
        }
        // 2016/08/01 S21_NA#12637 Add End
    }

    private static void setDefaultConfigFlg(NWZC150001PMsg pMsg, List<MDSETMsg> mdseTMsgList, List<NWZC150002PMsg> pMsg2List) {
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            MDSETMsg mdseTMsg = mdseTMsgList.get(i);

            String configFlg = NWXC150001DsCheck.getDefaultConfigFlg(pMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(aPMsg.configFlg_A1, configFlg);
        }
    }

    private static void setDefaultShopItemFlg(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List) {
        String dsOrdPosnNum = "";
        String shopItemFlg = ZYPConstant.FLG_OFF_N;
        // int ixCinfig = 0; // 2016/06/02 S21_NA#9107 Del
        // 2016/08/01 S21_NA#12637 Del Start
//        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
//            if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
//                dsOrdPosnNum = aPMsg.dsOrdPosnNum_A1.getValue();
//                // 2016/06/02 S21_NA#9107 Mod Start
////              NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(ixCinfig++);
//              NWZC150001_cpoConfigPMsg configPMsg = getOutBoundConfigPMsg(dsOrdPosnNum, pMsg);
//              if (configPMsg == null) {
//                  continue;
//              }
//              // 2016/06/02 S21_NA#9107 Mod End
//                shopItemFlg = NWXC150001DsCheck.getDefaultShopItemFlg(glblCmpyCd, configPMsg.mdlId.getValue());
//                if (!ZYPCommonFunc.hasValue(shopItemFlg)) {
//                    shopItemFlg = ZYPConstant.FLG_OFF_N;
//                }
//            }
//            ZYPEZDItemValueSetter.setValue(aPMsg.svcPreIstlShopFlg_A1, shopItemFlg);
//        }
        // 2016/08/01 S21_NA#12637 Del End
        // 2016/08/01 S21_NA#12637 Add Start
        for (int sltConfigIdx = 0; sltConfigIdx < pMsg.cpoConfig.getValidCount(); sltConfigIdx++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(sltConfigIdx);
            if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configPMsg.configCatgCd.getValue())) {
                continue;
            }
            shopItemFlg = NWXC150001DsCheck.getDefaultShopItemFlg(pMsg.glblCmpyCd.getValue(), configPMsg.mdlId.getValue());
            if (!ZYPCommonFunc.hasValue(shopItemFlg)) {
                shopItemFlg = ZYPConstant.FLG_OFF_N;
            }
            dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                NWZC150001_APMsg aPMsg = pMsg.A.no(i);
                if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(aPMsg.svcPreIstlShopFlg_A1, shopItemFlg);
            }
        }
        // 2016/08/01 S21_NA#12637 Add End
    }

    private static void setDefaultConfigIdByEveryConfig(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, NWZC150001DsCpoCommonBean commonBean) {

        // 2016/07/22 S21_NA#11970 Add Start
//        if (!NWXC150001DsCheck.isMachMstrCrat(glblCmpyCd, pMsg.dsOrdTpCd.getValue())) {
//            return;
//        }
        // 2016/07/22 S21_NA#11970 Add End

        String dsOrdPosnNum = "";
        // int ixCinfig = 0; 2016/06/02 S21_NA#8464 Del
        // S21_NA#2595
        BigDecimal svcConfigMstrPk = null;
        BigDecimal pickConfigPk = null;

        for (int sltConfigIdx = 0; sltConfigIdx < pMsg.cpoConfig.getValidCount(); sltConfigIdx++) {
            NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(sltConfigIdx);

            // 2017/03/09 S21_NA#17979 Add Start This section was moved from the checkConfigBillSellShip()
            boolean errFlg = false;
            if (NWXC150001DsCheck.checkConfigIdEssential(pMsg.glblCmpyCd.getValue(), configPMsg.configTpCd.getValue(), configPMsg.svcConfigMstrPk.getValue())) {
                // 2016/03/16 S21_NA#5519 Mod Start
                // setMsgId2(pMsg2, NWZM1466E);
                if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
                    getDtlConfigId(pMsg, configPMsg);
                    if (!ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
                        NWZC150002PMsg pMsg2 = new NWZC150002PMsg();
                        NWZC150001Common.setMsgId2(pMsg2, NWZM1466E);
                        errFlg = true;
                    }
                } else if (CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue())) {
                    // 2016/10/04 S21_NA#9215 Add Start
                    // 2018/05/20 S21_NA#25604 Mod Start
//                    boolean isRetailEquipmentOrder = NWZC150001Common.isExistOrdCatgFromCache(pMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, commonBean);
                    boolean isRetailEquipmentOrder = NWXC150001DsCheck.isRetailEquipOrder(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue());
                    // 2018/05/20 S21_NA#25604 Mod End
                    // 2016/10/04 S21_NA#9215 Add END
                    // 2016/10/04 S21_NA#9215 Add Start
                    if (isRetailEquipmentOrder) { // 2016/10/04 S21_NA#9215 Add End
                        getRtrnConfigId(pMsg, configPMsg);
                        if (!ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)) {
                            NWZC150003PMsg pMsg3 = new NWZC150003PMsg();
                            NWZC150001Common.setMsgId3(pMsg3, NWZM1466E);
                            errFlg = true;
                        }
                    } // 2016/10/04 S21_NA#9215 Add
                }
                // 2016/03/16 S21_NA#5519 Mod End
            }
            if (errFlg) {
                continue;
            }
            // 2017/03/09 S21_NA#17979 Del End This section was moved from the checkConfigBillSellShip()

            if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configPMsg.configCatgCd.getValue())) {
                continue;
            }
            dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
            if (!isMachMstrCratByConfig(pMsg, dsOrdPosnNum, commonBean)) {
                // 2018/02/28 S21_NA#24117 add start
                configPMsg.svcConfigMstrPk.clear();
                // 2018/02/28 S21_NA#24117 add end
                continue;
            }
            // NWZC150001_cpoConfigPMsg configPMsg = pMsg.cpoConfig.no(ixCinfig++); 2016/06/02 S21_NA#8464 Del
            String xxRqstTpCd = configPMsg.xxRqstTpCd.getValue(); // 2016/06/02 S21_NA#8464 Add Start
            boolean isNewConfig = NWZC150001Constant.RQST_TP_CONFIG_NEW.equals(xxRqstTpCd); // 2016/06/02 S21_NA#8464 Add Start
            boolean isModConfig = NWZC150001Constant.RQST_TP_CONFIG_MODIFY.equals(xxRqstTpCd); // 2016/06/02 S21_NA#8464 Add Start
            // 2018/06/06 S21_NA#25860-1 add start
            String cpoSrcTpCd = pMsg.cpoSrcTpCd.getValue();
            // 2018/06/06 S21_NA#25860-1 add end

            CONFIG_TPTMsg tMsg = new CONFIG_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            // S21_NA#2024
            // ZYPEZDItemValueSetter.setValue(tMsg.configTpCd,
            // pMsg.cpoConfig.no(i).configTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.configTpCd, configPMsg.configTpCd);

            svcConfigMstrPk = null;
            pickConfigPk = null;
            tMsg = (CONFIG_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {

                if (ZYPConstant.FLG_ON_Y.equals(tMsg.pickConfigPkReqFlg.getValue())) { // S21_NA#2595
                    pickConfigPk = configPMsg.svcConfigMstrPk.getValue();
                    ZYPEZDItemValueSetter.setValue(configPMsg.pickSvcConfigMstrPk, pickConfigPk);
                }
                // 2018/06/06 S21_NA#25860-1 mod start
//                if (ZYPConstant.FLG_ON_Y.equals(tMsg.configPkAsgFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(tMsg.configPkAsgFlg.getValue()) && !CPO_SRC_TP.REBILL.equals(cpoSrcTpCd)) {
                // 2018/06/06 S21_NA#25860-1 mod end
                    // 2016/02/02 S21_NA#3970 Mod Start
                    // svcConfigMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ);
                    // ZYPEZDItemValueSetter.setValue(configPMsg.svcConfigMstrPk, svcConfigMstrPk);
//                    if (NWZC150001Query.getInstance().isExistOrdCatg(pMsg, ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET) 2016/04/28 S21_NA#7516 Mod Condition
                    // if (NWZC150001Common.isExistOrdCatgFromCache(pMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS, commonBean)) { // For Performance QC#8166 Mod 2018/05/20 S21_NA#25604 Mod Condition
                    if (NWXC150001DsCheck.isRetailEquipOrder(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue())) { // 2018/05/20 S21_NA#25604 Mod Condition
                            // && !ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk)  2016/06/02 S21_NA#8464 Del
                        if (isNewConfig) { // 2016/06/02 S21_NA#8464 Add Condition.
                            svcConfigMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ);
                            ZYPEZDItemValueSetter.setValue(configPMsg.svcConfigMstrPk, svcConfigMstrPk);
                        } else if (isModConfig) {
                            if (ZYPCommonFunc.hasValue(configPMsg.dsCpoConfigPk)) {
                                DS_CPO_CONFIGTMsg dsCpoConfigTMsg = getDsCpoConfigTMsg(pMsg.glblCmpyCd.getValue(), configPMsg.dsCpoConfigPk.getValue(), commonBean);
                                if (null != dsCpoConfigTMsg) {
                                    if (S21StringUtil.isEquals(dsCpoConfigTMsg.configTpCd.getValue(), configPMsg.configTpCd.getValue())) { // QC#24245 2018/06/13 Add
                                        svcConfigMstrPk = dsCpoConfigTMsg.svcConfigMstrPk.getValue();
                                    }
                                }
                            }
                            if (null == svcConfigMstrPk) {
                                svcConfigMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ);
                            }
                            ZYPEZDItemValueSetter.setValue(configPMsg.svcConfigMstrPk, svcConfigMstrPk);
                        } // 2016/06/02 S21_NA#8464 Add Condition.
                    } else { // 2017/03/03 S21_NA#17714-3 Add Start
                        configPMsg.svcConfigMstrPk.clear();
                        svcConfigMstrPk = null;
                    } // 2017/03/03 S21_NA#17714-3 Add End
                    // 2016/02/02 S21_NA#3970 Mod End
                } else { // 2017/03/09 S21_NA#17979 Add Start
                    svcConfigMstrPk = configPMsg.svcConfigMstrPk.getValue();
                } // 2017/03/09 S21_NA#17979 Add End
            } else { // 2017/03/09 S21_NA#17979 Add Start
                svcConfigMstrPk = configPMsg.svcConfigMstrPk.getValue();
            } // 2017/03/09 S21_NA#17979 Add End
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                NWZC150001_APMsg aPMsg = pMsg.A.no(i);
    //            if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) { 2016/07/22 S21_NA#11970 Add Start
                if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
                    continue;
                }
                // if (BigDecimal.ZERO.compareTo(svcConfigMstrPk) != 0) {
                // ZYPEZDItemValueSetter.setValue(aPMsg.svcConfigMstrPk_A1,
                // svcConfigMstrPk);
                // if (BigDecimal.ZERO.compareTo(pickConfigPk) != 0) {
                // ZYPEZDItemValueSetter.setValue(aPMsg.pickSvcConfigMstrPk_A1,
                // pickConfigPk);
                // }
                // }
                // S21_NA#2595
                if (svcConfigMstrPk != null) {
                    ZYPEZDItemValueSetter.setValue(aPMsg.svcConfigMstrPk_A1, svcConfigMstrPk);
                } else { // 2017/03/03 S21_NA#17714-3 Add Start
                    aPMsg.svcConfigMstrPk_A1.clear();
                } // 2017/03/03 S21_NA#17714-3 Add End


                // 2016/09/28 S21_NA#14264 Add Start
                Map<String, Object> svcMachMstrMap = null;
                // 2019/08/07 QC#52505 Mod Start
//                if (ZYPCommonFunc.hasValue(aPMsg.serNum_A1) //
//                        && !ZYPCommonFunc.hasValue(aPMsg.svcMachMstrPk_A1)) {
//                    // 2017/02/22 QC#16575 ADD START
//                    svcMachMstrMap = commonBean.getSvcMachCache().getSvcMachMstrMap(aPMsg.serNum_A1.getValue(), aPMsg.svcMachMstrPk_A1.getValue(),
//                            aPMsg.mdseCd_A1.getValue() );
                if (ZYPCommonFunc.hasValue(aPMsg.serNum_A1)) {
                    svcMachMstrMap = commonBean.getSvcMachCache().getSvcMachMstrMap(aPMsg.serNum_A1.getValue(), aPMsg.svcMachMstrPk_A1.getValue(),
                            aPMsg.mdseCd_A1.getValue());
                    // 2017/02/22 QC#16575 ADD E N D
                    //if (svcMachMstrMap != null) {
                    if (svcMachMstrMap != null
                            && !ZYPCommonFunc.hasValue(aPMsg.svcMachMstrPk_A1)) {
                        // 2019/08/07 QC#52505 Mod End
                        ZYPEZDItemValueSetter.setValue(aPMsg.svcMachMstrPk_A1, (BigDecimal) svcMachMstrMap.get("SVC_MACH_MSTR_PK"));
                    }
                }
                // 2016/09/28 S21_NA#14264 Add End

                // 2016/09/28 S21_NA#14264 Mod Start
//                if (pickConfigPk != null) {
                if (pickConfigPk != null //
                        && ZYPCommonFunc.hasValue(aPMsg.svcMachMstrPk_A1)) { // 2016/09/28 S21_NA#14264 Mod End
                    // 2019/08/07 QC#52505 Add Start
                    if (svcMachMstrMap != null && !ZYPCommonFunc.hasValue((BigDecimal) svcMachMstrMap.get("SVC_CONFIG_MSTR_PK"))) {
                        // When this serial item have not config id, then not set pickSvcConfigMstrPk.
                        aPMsg.pickSvcConfigMstrPk_A1.clear();
                    } else {
                        // 2019/08/07 QC#52505 Add End
                        ZYPEZDItemValueSetter.setValue(aPMsg.pickSvcConfigMstrPk_A1, pickConfigPk);
                    }
                }
            }
        }
    }

    private static void getDtlConfigId(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configPMsg) {
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
            NWZC150001_APMsg dtlPMsg = pMsg.A.no(i);

            // 2016/06/02 S21_NA#9273 Modify Start
//            if (dsOrdPosnNum.equals(dtlPMsg.dsOrdPosnNum_A1.getValue())
//                    && ZYPCommonFunc.hasValue(dtlPMsg.serNum_A1)) {
//                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
//                        glblCmpyCd //
//                        , dtlPMsg.serNum_A1.getValue());
            if (dsOrdPosnNum.equals(dtlPMsg.dsOrdPosnNum_A1.getValue())
                    && (ZYPCommonFunc.hasValue(dtlPMsg.serNum_A1) || ZYPCommonFunc.hasValue(dtlPMsg.svcMachMstrPk_A1))) {
                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
                        pMsg.glblCmpyCd.getValue() //
                        , dtlPMsg.serNum_A1.getValue()//
                        // 2017/02/22 QC#16575 ADD START
                        , dtlPMsg.mdseCd_A1.getValue() //
                        // 2017/02/22 QC#16575 ADD E N D
                        , dtlPMsg.svcMachMstrPk_A1.getValue());
                // 2016/06/02 S21_NA#9273 Modify End
                if (null != map) {
                    ZYPEZDItemValueSetter.setValue(configPMsg.svcConfigMstrPk, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
                    // 2016/04/15 S21_NA#5321 Add Start
                    if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk) //
                            && (!ZYPCommonFunc.hasValue(configPMsg.mdlId) || !ZYPCommonFunc.hasValue(configPMsg.mdlDescTxt))) {
                        // 2016/04/18 S21_NA#5321-2 Mod Start
//                        SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = getSvcConfigMstr(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());
//                        if (null != svcConfigMstrTMsg) {
//                            ZYPEZDItemValueSetter.setValue(configPMsg.mdlId, svcConfigMstrTMsg.mdlId);
//                            if (ZYPCommonFunc.hasValue(svcConfigMstrTMsg.mdlId)) {
//                                String mdlDescTxt =  getDsMdlDescTxt(pMsg, svcConfigMstrTMsg.mdlId.getValue());
//                                ZYPEZDItemValueSetter.setValue(configPMsg.mdlDescTxt, mdlDescTxt);
//                            }
//                        }
                        Map<String, Object> mdlIdNmMap = NWZC150001Query.getInstance().getMdlIdNmBySvcConfigMstr(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());
                        if (null != mdlIdNmMap) {
                            if (null != mdlIdNmMap.get("MDL_ID")){ 
                                ZYPEZDItemValueSetter.setValue(configPMsg.mdlId, (BigDecimal) mdlIdNmMap.get("MDL_ID"));
                            }
                            if (null != mdlIdNmMap.get("MDL_DESC_TXT")) {
                                ZYPEZDItemValueSetter.setValue(configPMsg.mdlDescTxt, (String) mdlIdNmMap.get("MDL_DESC_TXT"));
                            }
                        }
                        // 2016/04/18 S21_NA#5321-2 Mod End
                    }
                    // 2016/04/15 S21_NA#5321 Add End
                    break;
                }
            }
        }
    }

    private static void getRtrnConfigId(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configPMsg) {
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);

            // 2016/06/02 S21_NA#9273 Modify Start
//            if (dsOrdPosnNum.equals(rtnDtlPMsg.dsOrdPosnNum_B1.getValue())
//                    && ZYPCommonFunc.hasValue(rtnDtlPMsg.serNum_B1)) {
//                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
//                        glblCmpyCd //
//                        , rtnDtlPMsg.serNum_B1.getValue());
            if (dsOrdPosnNum.equals(rtnDtlPMsg.dsOrdPosnNum_B1.getValue())
                    && (ZYPCommonFunc.hasValue(rtnDtlPMsg.serNum_B1) || ZYPCommonFunc.hasValue(rtnDtlPMsg.svcMachMstrPk_B1))) {
                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
                        pMsg.glblCmpyCd.getValue() //
                        , rtnDtlPMsg.serNum_B1.getValue()//
                        // 2017/02/22 QC#16575 ADD START
                        , rtnDtlPMsg.mdseCd_B1.getValue() //
                        // 2017/02/22 QC#16575 ADD E N D
                        , rtnDtlPMsg.svcMachMstrPk_B1.getValue());
                // 2016/06/02 S21_NA#9273 Modify End
                if (null != map) {
                    ZYPEZDItemValueSetter.setValue(configPMsg.svcConfigMstrPk, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
                    // 2016/04/15 S21_NA#5321 Add Start
                    if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk) //
                            && (!ZYPCommonFunc.hasValue(configPMsg.mdlId) || !ZYPCommonFunc.hasValue(configPMsg.mdlDescTxt))) {
                        // 2016/04/18 S21_NA#5321-2 Mod Start
//                        SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = getSvcConfigMstr(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());
//                        if (null != svcConfigMstrTMsg) {
//                            ZYPEZDItemValueSetter.setValue(configPMsg.mdlId, svcConfigMstrTMsg.mdlId);
//                            if (ZYPCommonFunc.hasValue(svcConfigMstrTMsg.mdlId)) {
//                                String mdlDescTxt =  getDsMdlDescTxt(pMsg, svcConfigMstrTMsg.mdlId.getValue());
//                                ZYPEZDItemValueSetter.setValue(configPMsg.mdlDescTxt, mdlDescTxt);
//                            }
//                        }
                        Map<String, Object> mdlIdNmMap = NWZC150001Query.getInstance().getMdlIdNmBySvcConfigMstr(pMsg.glblCmpyCd.getValue(), configPMsg.svcConfigMstrPk.getValue());
                        if (null != mdlIdNmMap) {
                            if (null != mdlIdNmMap.get("MDL_ID")){ 
                                ZYPEZDItemValueSetter.setValue(configPMsg.mdlId, (BigDecimal) mdlIdNmMap.get("MDL_ID"));
                            }
                            if (null != mdlIdNmMap.get("MDL_DESC_TXT")) {
                                ZYPEZDItemValueSetter.setValue(configPMsg.mdlDescTxt, (String) mdlIdNmMap.get("MDL_DESC_TXT"));
                            }
                        }
                        // 2016/04/18 S21_NA#5321-2 Mod End
                    }
                    // 2016/04/15 S21_NA#5321 Add End
                    break;
                }
            }
        }
    }

    private static boolean isMachMstrCratByConfig(NWZC150001PMsg pMsg, String dsOrdPosnNum, NWZC150001DsCpoCommonBean commonBean) {

        for (int idx = 0; idx < pMsg.A.getValidCount(); idx++) {
            if (S21StringUtil.isEquals(dsOrdPosnNum, pMsg.A.no(idx).dsOrdPosnNum_A1.getValue())) {
                // 2018/06/18 S21_NA#24294 Mod Start
                // MDSETMsg dsMdseInfoMsg = commonBean.getDsMdseInfoList().get(idx);
                MDSETMsg mdseInfoMsg = commonBean.getMdseInfoList().get(idx);
                // if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dsMdseInfoMsg.instlBaseCtrlFlg.getValue())) {
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseInfoMsg.instlBaseCtrlFlg.getValue())) {
                // 2018/06/18 S21_NA#24294 Mod End
                    return true;
                }
            }
        }
        return false;
    }

    private static DS_CPO_CONFIGTMsg getDsCpoConfigTMsg(String glblCmpyCd, BigDecimal dsCpoConfigPk, NWZC150001DsCpoCommonBean commonBean) {
        DS_CPO_CONFIGTMsg dsCpoConfigTMsg = null;
        if (commonBean.getDsCpoConfigList() == null) {
            dsCpoConfigTMsg = getDsCpoConfigTMsgFromDb(glblCmpyCd, dsCpoConfigPk);
            if (null != dsCpoConfigTMsg) {
                commonBean.setDsCpoConfigList(new ArrayList<DS_CPO_CONFIGTMsg>(1));
                commonBean.getDsCpoConfigList().add(dsCpoConfigTMsg);
            }
        } else {
            if (null == dsCpoConfigPk) {
                dsCpoConfigPk = BigDecimal.valueOf(-1);
            }
            for (DS_CPO_CONFIGTMsg dsCpoConfigTMsgInLoop : commonBean.getDsCpoConfigList()) {
                String glblCmpyCdInLoop = dsCpoConfigTMsgInLoop.glblCmpyCd.getValue();
                BigDecimal dsCpoConfigPkInLoop = dsCpoConfigTMsgInLoop.dsCpoConfigPk.getValue();
                if (null == dsCpoConfigPkInLoop) {
                    dsCpoConfigPkInLoop = BigDecimal.valueOf(-2);
                }
                if (S21StringUtil.isEquals(glblCmpyCd, glblCmpyCdInLoop) //
                        && dsCpoConfigPk.compareTo(dsCpoConfigPkInLoop) == 0) {
                    dsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
                    EZDMsg.copy(dsCpoConfigTMsgInLoop, null, dsCpoConfigTMsg, null);
                    break;
                }
            }
            if (null == dsCpoConfigTMsg) {
                dsCpoConfigTMsg = getDsCpoConfigTMsgFromDb(glblCmpyCd, dsCpoConfigPk);
            }
        }
        return dsCpoConfigTMsg;
    }

    private static DS_CPO_CONFIGTMsg getDsCpoConfigTMsgFromDb(String glblCmpyCd, BigDecimal dsCpoConfigPk) {

        DS_CPO_CONFIGTMsg dsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.dsCpoConfigPk, dsCpoConfigPk);
        dsCpoConfigTMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKey(dsCpoConfigTMsg);

        return dsCpoConfigTMsg;
    }

    private static void setDefaultShipToInfoForHeader(NWZC150001PMsg pMsg, Map<Map<String, String>, Map<String, String>> cacheMap) {
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.addDropShipFlg.getValue())) {
            return;
        }
        Map<String, String> rsltMap //
        = NWZC150001Query.getInstance().getDefaultShipToInfo(pMsg.glblCmpyCd.getValue(), pMsg.addShipToCustCd.getValue(), cacheMap);
        if (rsltMap == null || rsltMap.isEmpty()) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.addShipToLocNm, (String) rsltMap.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToAddlLocNm, (String) rsltMap.get("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFirstLineAddr, (String) rsltMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToScdLineAddr, (String) rsltMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToThirdLineAddr, (String) rsltMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFrthLineAddr, (String) rsltMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtyAddr, (String) rsltMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToStCd, (String) rsltMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToProvNm, (String) rsltMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToPostCd, (String) rsltMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtryCd, (String) rsltMap.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo01RefCmntTxt, (String) rsltMap.get("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo02RefCmntTxt, (String) rsltMap.get("SCD_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCntyNm, (String) rsltMap.get("CNTY_NM"));

        return;
    }

    private static void setDefaultShipToInfoForConfig(//
            NWZC150001_cpoConfigPMsgArray ccPMsgAry //
            , NWZC150001_APMsgArray aPMsgAry //
            , NWZC150001_rtnDtlPMsgArray rtnDtlPMsgAry //
            , Map<Map<String, String>, Map<String, String>> cacheMap
            , NWZC150001PMsg pMsg) { // S21_NA#8003 MOD

        for (int i = 0; i < ccPMsgAry.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg ccPMsg = ccPMsgAry.no(i);

            if (!ZYPConstant.FLG_ON_Y.equals(ccPMsg.dropShipFlg.getValue())) {
                Map<String, String> rsltMap //
                = NWZC150001Query.getInstance().getDefaultShipToInfo(pMsg.glblCmpyCd.getValue(), ccPMsg.shipToCustCd.getValue(), cacheMap);
                if (rsltMap == null || rsltMap.isEmpty()) {
                    continue;
                }
    
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToLocNm, (String) rsltMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToAddlLocNm, (String) rsltMap.get("ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToFirstLineAddr, (String) rsltMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToScdLineAddr, (String) rsltMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToThirdLineAddr, (String) rsltMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToFrthLineAddr, (String) rsltMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToCtyAddr, (String) rsltMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToStCd, (String) rsltMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToProvNm, (String) rsltMap.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToPostCd, (String) rsltMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToCtryCd, (String) rsltMap.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipTo01RefCmntTxt, (String) rsltMap.get("FIRST_REF_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipTo02RefCmntTxt, (String) rsltMap.get("SCD_REF_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(ccPMsg.shipToCntyNm, (String) rsltMap.get("CNTY_NM"));
            }

            if (CONFIG_CATG.OUTBOUND.equals(ccPMsg.configCatgCd.getValue())) {
                setDefaultShipToInfoForDetail(ccPMsg, aPMsgAry, pMsg); // S21_NA#8003 MOD
            }
            if (CONFIG_CATG.INBOUND.equals(ccPMsg.configCatgCd.getValue())) {
                setDefaultShipToInfoForRtnDtl(ccPMsg, rtnDtlPMsgAry);
            }
        }
        return;
    }

    private static void setDefaultShipToInfoForDetail(NWZC150001_cpoConfigPMsg ccPMsg, NWZC150001_APMsgArray aPMsgAry, NWZC150001PMsg pMsg) {
        // S21_NA#8003 ADD START
        if (CPO_SRC_TP.INVENTORY_REQUEST_ENTRY.equals(pMsg.cpoSrcTpCd.getValue()) && isInternalOrder(pMsg)) {
            return;
        }
        // S21_NA#8003 ADD END
        for (int i = 0; i < aPMsgAry.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = aPMsgAry.no(i);
            if (!ccPMsg.dsOrdPosnNum.getValue().equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(aPMsg.dropShipFlg_A1, ccPMsg.dropShipFlg);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToCustCd_A1, ccPMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToLocNm_A1, ccPMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToAddlLocNm_A1, ccPMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToFirstLineAddr_A1, ccPMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToScdLineAddr_A1, ccPMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToThirdLineAddr_A1, ccPMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToFrthLineAddr_A1, ccPMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToCtyAddr_A1, ccPMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToStCd_A1, ccPMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToProvNm_A1, ccPMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToPostCd_A1, ccPMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToCtryCd_A1, ccPMsg.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToFirstRefCmntTxt_A1, ccPMsg.shipTo01RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToScdRefCmntTxt_A1, ccPMsg.shipTo02RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(aPMsg.shipToCntyNm_A1, ccPMsg.shipToCntyNm);
        }
    }

    private static boolean isInternalOrder(NWZC150001PMsg pMsg) {

        Map<String, String> ordProcTpInfoMap = NWZC150001Query.getInstance().getOrdProcTpInfo(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdTpCd.getValue(), null, pMsg.slsDt.getValue(), null);
        if (ordProcTpInfoMap == null) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals((String) ordProcTpInfoMap.get("ITRL_ORD_PROC_FLG"))) {
            return true;
        }
        return false;
    }

    private static void setDefaultShipToInfoForRtnDtl(NWZC150001_cpoConfigPMsg ccPMsg, NWZC150001_rtnDtlPMsgArray rtnDtlPMsgAry) {
        for (int i = 0; i < rtnDtlPMsgAry.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = rtnDtlPMsgAry.no(i);
            if (!ccPMsg.dsOrdPosnNum.getValue().equals(rtnDtlPMsg.dsOrdPosnNum_B1.getValue())) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.dropShipFlg_B1, ccPMsg.dropShipFlg);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToCustCd_B1, ccPMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToLocNm_B1, ccPMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToAddlLocNm_B1, ccPMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToFirstLineAddr_B1, ccPMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToScdLineAddr_B1, ccPMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToThirdLineAddr_B1, ccPMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToFrthLineAddr_B1, ccPMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToCtyAddr_B1, ccPMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToStCd_B1, ccPMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToProvNm_B1, ccPMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToPostCd_B1, ccPMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToCtryCd_B1, ccPMsg.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToFirstRefCmntTxt_B1, ccPMsg.shipTo01RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToScdRefCmntTxt_B1, ccPMsg.shipTo02RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.shipToCntyNm_B1, ccPMsg.shipToCntyNm);
        }
    }

    private static void deriveDsCpoLineNum(NWZC150001PMsg pMsg) {

        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg ccPMsg = pMsg.cpoConfig.no(i);

            if (RQST_TP_CONFIG_NEW.equals(ccPMsg.xxRqstTpCd.getValue())) {
                if (CONFIG_CATG.OUTBOUND.equals(ccPMsg.configCatgCd.getValue())) {
                    deriveDsCpoLineNumForNewProc(ccPMsg.dsOrdPosnNum.getValue(), pMsg.A);
                } else {
                    deriveDsCpoLineNumForNewRtrnProc(ccPMsg.dsOrdPosnNum.getValue(), pMsg.rtnDtl);
                }
            } else if (RQST_TP_CONFIG_MODIFY.equals(ccPMsg.xxRqstTpCd.getValue())) {
                if (CONFIG_CATG.OUTBOUND.equals(ccPMsg.configCatgCd.getValue())) {
                    // 2016/01/06 S21_NA#2582 Mod Start
                    // deriveDsCpoLineNumForModProc(pMsg.cpoOrdNum.getValue(), ccPMsg.dsOrdPosnNum.getValue(), pMsg.A);
                    deriveDsCpoLineNumForNewProc(ccPMsg.dsOrdPosnNum.getValue(), pMsg.A);
                    // 2016/01/06 S21_NA#2582 Mod End
                } else {
                    // 2016/01/06 S21_NA#2582 Mod Start
                    // deriveDsCpoLineNumForModRtrnProc(pMsg.cpoOrdNum.getValue(), ccPMsg.dsOrdPosnNum.getValue(), pMsg.rtnDtl);
                    deriveDsCpoLineNumForNewRtrnProc(ccPMsg.dsOrdPosnNum.getValue(), pMsg.rtnDtl);
                    // 2016/01/06 S21_NA#2582 Mod End
                }
            }

        }
    }

    private static void deriveDsCpoLineNumForNewProc(String dsOrdPosnNum, NWZC150001_APMsgArray aPMsgAry) {
        TreeMap<String, NWZC150001_APMsg> aPMap = new TreeMap<String, NWZC150001_APMsg>();
        for (int i = 0; i < aPMsgAry.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = aPMsgAry.no(i);
            if (!dsOrdPosnNum.equals(aPMsg.dsOrdPosnNum_A1.getValue())) {
                continue;
            }
            String key = S21StringUtil.concatStrings(//
                    aPMsg.cpoDtlLineNum_A1.getValue() //
                    , "." //
                    , aPMsg.cpoDtlLineSubNum_A1.getValue());
            aPMap.put(key, aPMsg);
        }

        Set<String> keySet = aPMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        BigDecimal dsLineNum = BigDecimal.ZERO;
        boolean isSet = false;
        String setLineNum = "";
        while (iterator.hasNext()) {
            String key = iterator.next();
            NWZC150001_APMsg aPMsg = aPMap.get(key);
            // S21_NA#852 modify start.
            // if
            // (!setLineNum.equals(aPMsg.cpoDtlLineNum_A1.getValue()))
            // {
            // setLineNum = "";
            // dsLineNum = BigDecimal.ZERO;
            // }
            // if
            // (SET_LINE_SUB_NUM.equals(aPMsg.cpoDtlLineSubNum_A1.getClass()))
            // {
            // isSet = true;
            // setLineNum = aPMsg.cpoDtlLineNum_A1.getValue();
            // dsLineNum = dsLineNum.add(BigDecimal.ONE);
            // }
            // if (isSet) {
            // ZYPEZDItemValueSetter.setValue(//
            // aPMsg.dsCpoLineSubNum_A1 //
            // ,
            // NWXC150001DsCheck.convCpoLineSubNumToDs(aPMsg.cpoDtlLineSubNum_A1.getValue()));
            // } else {
            // aPMsg.dsCpoLineSubNum_A1.clear();
            // dsLineNum = dsLineNum.add(BigDecimal.ONE);
            // }
            // ZYPEZDItemValueSetter.setValue(aPMsg.dsCpoLineNum_A1,
            // dsLineNum);
            if (!setLineNum.equals(aPMsg.cpoDtlLineNum_A1.getValue())) {
                setLineNum = aPMsg.cpoDtlLineNum_A1.getValue();
                dsLineNum = dsLineNum.add(BigDecimal.ONE);
                isSet = false;
            }
            if (SET_LINE_SUB_NUM.equals(aPMsg.cpoDtlLineSubNum_A1.getValue())) {
                isSet = true;
            }
            if (isSet) {
                ZYPEZDItemValueSetter.setValue(//
                        aPMsg.dsCpoLineSubNum_A1 //
                        , NWXC150001DsCheck.convCpoLineSubNumToDs(aPMsg.cpoDtlLineSubNum_A1.getValue()));
            } else {
                aPMsg.dsCpoLineSubNum_A1.clear();
            }
            ZYPEZDItemValueSetter.setValue(aPMsg.dsCpoLineNum_A1, dsLineNum);
            // S21_NA#852 modify end.
        }
    }

    private static void deriveDsCpoLineNumForNewRtrnProc(String dsOrdPosnNum, NWZC150001_rtnDtlPMsgArray rtnDtlPMsgAry) {
        TreeMap<String, NWZC150001_rtnDtlPMsg> rdPMap = new TreeMap<String, NWZC150001_rtnDtlPMsg>();
        for (int i = 0; i < rtnDtlPMsgAry.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rdPMsg = rtnDtlPMsgAry.no(i);
            if (!dsOrdPosnNum.equals(rdPMsg.dsOrdPosnNum_B1.getValue())) {
                continue;
            }
            String key = S21StringUtil.concatStrings(//
                    rdPMsg.cpoDtlLineNum_B1.getValue() //
                    , "." //
                    , rdPMsg.cpoDtlLineSubNum_B1.getValue());
            rdPMap.put(key, rdPMsg);
        }

        Set<String> keySet = rdPMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        BigDecimal dsLineNum = BigDecimal.ZERO;
        boolean isSet = false;
        String setLineNum = "";
        while (iterator.hasNext()) {
            String key = iterator.next();
            NWZC150001_rtnDtlPMsg rdPMsg = rdPMap.get(key);

            // S21_NA#852 modify start.
            // if
            // (!setLineNum.equals(rdPMsg.cpoDtlLineNum_B1.getValue()))
            // {
            // setLineNum = "";
            // dsLineNum = BigDecimal.ZERO;
            // }
            // if
            // (SET_LINE_SUB_NUM.equals(rdPMsg.cpoDtlLineSubNum_B1.getClass()))
            // {
            // isSet = true;
            // setLineNum = rdPMsg.cpoDtlLineNum_B1.getValue();
            // dsLineNum = dsLineNum.add(BigDecimal.ONE);
            // }
            // if (isSet) {
            // ZYPEZDItemValueSetter.setValue(//
            // rdPMsg.dsCpoLineSubNum_B1 //
            // ,
            // NWXC150001DsCheck.convCpoLineSubNumToDs(rdPMsg.cpoDtlLineSubNum_B1.getValue()));
            // } else {
            // rdPMsg.dsCpoLineSubNum_B1.clear();
            // dsLineNum = dsLineNum.add(BigDecimal.ONE);
            // }
            //
            // ZYPEZDItemValueSetter.setValue(rdPMsg.dsCpoLineNum_B1,
            // dsLineNum);
            if (!setLineNum.equals(rdPMsg.cpoDtlLineNum_B1.getValue())) {
                setLineNum = rdPMsg.cpoDtlLineNum_B1.getValue();
                dsLineNum = dsLineNum.add(BigDecimal.ONE);
                isSet = false;
            }
            if (SET_LINE_SUB_NUM.equals(rdPMsg.cpoDtlLineSubNum_B1.getValue())) {
                isSet = true;
            }
            if (isSet) {
                ZYPEZDItemValueSetter.setValue(//
                        rdPMsg.dsCpoLineSubNum_B1 //
                        , NWXC150001DsCheck.convCpoLineSubNumToDs(rdPMsg.cpoDtlLineSubNum_B1.getValue()));
            } else {
                rdPMsg.dsCpoLineSubNum_B1.clear();
            }
            ZYPEZDItemValueSetter.setValue(rdPMsg.dsCpoLineNum_B1, dsLineNum);
            // S21_NA#852 modify end.
        }
    }

    private static void deriveSlsRepSlsCr(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, List<NWZC150003PMsg> pMsg3List, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) { // 2016/10/13 S21_NA#7924-2 Add pMsg2List, pMsg3List
        String lineBizTpCd = NWXC150001DsCheck.getLineBizTpCdFromDsOrdTp(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdTpCd.getValue());

        if (!isExistHeaderSlsCr(pMsg.cpoSlsCr)) {
            int ix = pMsg.cpoSlsCr.getValidCount();
            NWZC150001_cpoSlsCrPMsg cscPMsg = pMsg.cpoSlsCr.no(ix);
            setDefaultSlsCrForHeader(cscPMsg, pMsg, lineBizTpCd);
            pMsg.cpoSlsCr.setValidCount(++ix);
        }
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg aPMsg = pMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(aPMsg.slsRepOrSlsTeamTocCd_A1)) {
                continue;
            }

//            int ix = pMsg.cpoSlsCr.getValidCount();
//            NWZC150001_cpoSlsCrPMsg cscPMsg = pMsg.cpoSlsCr.no(ix);
            setDefaultSlsRepForDetail(pMsg, aPMsg, lineBizTpCd, pMsg2List, msgIdMgr, commonBean); //  2016/10/13 S21_NA#7924-2 Add pMsg2List
//            pMsg.cpoSlsCr.setValidCount(ix++);
        }

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            if (ZYPCommonFunc.hasValue(rtnDtlPMsg.slsRepOrSlsTeamTocCd_B1)) {
                continue;
            }

            int ix = pMsg.cpoSlsCr.getValidCount();
            NWZC150001_cpoSlsCrPMsg cscPMsg = pMsg.cpoSlsCr.no(ix);
            setDefaultSlsRepForDetailForReturn(cscPMsg, pMsg, rtnDtlPMsg, lineBizTpCd, pMsg3List, msgIdMgr, commonBean); // 2016/10/13 S21_NA#7924-2 Add pMsg3List
        }
    }

    private static boolean isExistHeaderSlsCr(NWZC150001_cpoSlsCrPMsgArray cpoSlsCrAry) {
        for (int i = 0; i < cpoSlsCrAry.getValidCount(); i++) {
            NWZC150001_cpoSlsCrPMsg cscPMsg = cpoSlsCrAry.no(i);
            if (!ZYPCommonFunc.hasValue(cscPMsg.dsOrdPosnNum)) {
                return true;
            }
        }
        return false;
    }

    private static void setDefaultSlsCrForHeader(NWZC150001_cpoSlsCrPMsg cscPMsg, NWZC150001PMsg pMsg, String lineBizTpCd) {
        cscPMsg.clear();
        ZYPEZDItemValueSetter.setValue(cscPMsg.xxRqstTpCd, RQST_TP_SLS_CR_NEW);
        ZYPEZDItemValueSetter.setValue(cscPMsg.slsRepCd, pMsg.slsRepCd);
        ZYPEZDItemValueSetter.setValue(cscPMsg.slsRepRoleTpCd //
                , NWXC150001DsCheck.getLineBizRoleTpCdForWriter(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(cscPMsg.slsRepCrPct, PCT_100);
//        ZYPEZDItemValueSetter.setValue(cscPMsg.slsCrQuotFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cscPMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y); // QC#5568
    }

    /**
     * <pre>
     * set default sales rep for detaila
     * </pre>
     * @param pMsg PMessage
     * @param aPMsg Detail line PMsg
     * @param lineBizTpCd Line Biz Type Code
     * @param pMsg2List error message list <2016/10/13 S21_NA#7924-2 Add>
     * @param msgIdMgr S21ApiMessageIdMgr
     * @param commonBean NWZC150001DsCpoCommonBean
     */
    private static void setDefaultSlsRepForDetail(NWZC150001PMsg pMsg, NWZC150001_APMsg aPMsg, String lineBizTpCd, List<NWZC150002PMsg> pMsg2List, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
        String slsRep = getSlsRepForDetail(pMsg, aPMsg.dsOrdPosnNum_A1.getValue(), CONFIG_CATG.OUTBOUND, lineBizTpCd, msgIdMgr, commonBean);  // 2016/10/13 S21_NA#7924-2 Add configCatgCd
        if (ZYPCommonFunc.hasValue(slsRep)) {
            // 2016/10/13 S21_NA#7924-2 Add Start
            if (!NWZC150001Common.isAvalableTocCd(pMsg.glblCmpyCd.getValue(), slsRep)) {
                NWZC150001Common.addMsgId2List(pMsg2List, aPMsg.dsOrdPosnNum_A1.getValue(), NWZM2033E);
            }
            // 2016/10/13 S21_NA#7924-2 Add End
            ZYPEZDItemValueSetter.setValue(aPMsg.slsRepOrSlsTeamTocCd_A1, slsRep);
            return;
        }
    }

    /**
     * <pre>
     * get default sales rep code from Sales Credit.
     * @param pMsg PMessage
     * @param dsOrdPosnNum DS Order Position Number
     * @param configCatgCd Config Category Code (O: Outbound, I: Inbound)
     * @param lineBizTpCd Line Biz Type Code
     * @param msgIdMgr S21ApiMessageIdMgr
     * @return Sales Rep Code
     * </pre>
     */
    private static String getSlsRepForDetail(NWZC150001PMsg pMsg, String dsOrdPosnNum, String configCatgCd, String lineBizTpCd, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) { // 2016/10/13 S21_NA#7924-2 Add configCatgCd
        for (int i = 0; i < pMsg.cpoSlsCr.getValidCount(); i++) {
            NWZC150001_cpoSlsCrPMsg cscPMsg = pMsg.cpoSlsCr.no(i);
            if (dsOrdPosnNum.equals(cscPMsg.dsOrdPosnNum.getValue()) //
                    && S21StringUtil.isEquals(cscPMsg.configCatgCd.getValue(), configCatgCd) // 2016/10/13 S21_NA#7924-2 Add
                    && !S21StringUtil.isEquals(NWZC152001Constant.MODE_DEL, cscPMsg.xxRqstTpCd.getValue()) // 2016/10/13 S21_NA#7924-2 Add
                    && isWriter(pMsg.glblCmpyCd.getValue(), cscPMsg.slsRepRoleTpCd.getValue())) {
                return cscPMsg.slsRepCd.getValue();
            }
        }
        NWZC150001_cpoConfigPMsg ccPMsg = null;
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            ccPMsg = pMsg.cpoConfig.no(i);
            // 2019/11/27 S21_NA#54212 Mod Start
//            if (!ccPMsg.dsOrdPosnNum.getValue().equals(dsOrdPosnNum) //
//                    && !S21StringUtil.isEquals(ccPMsg.configCatgCd.getValue(), configCatgCd)) {
//                continue;
//            }
            boolean isCreateTarget = ccPMsg.dsOrdPosnNum.getValue().equals(dsOrdPosnNum) //
                && S21StringUtil.isEquals(ccPMsg.configCatgCd.getValue(), configCatgCd);
            if (!isCreateTarget) {
                continue;
            }
            // 2019/11/27 S21_NA#54212 Mod End
            if (pMsg.addShipToCustCd.getValue().equals(ccPMsg.shipToCustCd.getValue())) {
                setDefaultSlsCrForConfig(pMsg, ccPMsg);
                return pMsg.slsRepCd.getValue();
            }
            break;
        }
        NMZC260001PMsg drPMsg = callDefaultRepApi(pMsg, ccPMsg, commonBean);
        if (S21ApiUtil.isXxMsgId(drPMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(drPMsg);
            for (String m : ml) {
                msgIdMgr.addXxMsgId(m, pMsg);
            }
            for (int i = 0; i < drPMsg.xxMsgIdList.getValidCount(); i++) {
                EZDDebugOutput.println(1, //
                        S21MessageFunc.clspGetMessage(//
                                drPMsg.xxMsgIdList.no(i).xxMsgId.getValue() //
                                , new String[] {drPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue() //
                                        , drPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_1.getValue() //
                                        , drPMsg.xxMsgIdList.no(i).xxMsgPrmTxt_2.getValue() //
                                }), NWZC150001ForDefaultValueSetter.class);
            }
            return null;
        }
        for (int i = 0; i < drPMsg.defSlsRepList.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg dsrlPMsg = drPMsg.defSlsRepList.no(i);

            if (isWriter(pMsg.glblCmpyCd.getValue(), dsrlPMsg.lineBizRoleTpCd.getValue()) //
                    && lineBizTpCd.equals(dsrlPMsg.lineBizTpCd.getValue())) {
                setDefaultSlsCrForConfigWriter(pMsg, ccPMsg, lineBizTpCd);
                return dsrlPMsg.tocCd.getValue();
            }
        }
        return null;
    }

    private static boolean isWriter(String glblCmpyCd, String lineBizRoleTpCd) {
        LINE_BIZ_ROLE_TPTMsg tMsg = new LINE_BIZ_ROLE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizRoleTpCd, lineBizRoleTpCd);

        tMsg = (LINE_BIZ_ROLE_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return ZYPConstant.FLG_ON_Y.equals(tMsg.primRepRoleFlg.getValue());
    }

    private static void setDefaultSlsCrForConfig(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg ccPMsg) {
        List<NWZC150001_cpoSlsCrPMsg> hdrCscPMsgList = new ArrayList<NWZC150001_cpoSlsCrPMsg>();
        for (int i = 0; i < pMsg.cpoSlsCr.getValidCount(); i++) {
            NWZC150001_cpoSlsCrPMsg cscPMsg = pMsg.cpoSlsCr.no(i);
            if (ccPMsg.dsOrdPosnNum.getValue().equals(cscPMsg.dsOrdPosnNum.getValue())) {
                return;
            }
            if (!ZYPCommonFunc.hasValue(cscPMsg.dsOrdPosnNum)) {
                hdrCscPMsgList.add(cscPMsg);
            }
        }
        for (NWZC150001_cpoSlsCrPMsg hdrCscPMsg : hdrCscPMsgList) {
            int ix = pMsg.cpoSlsCr.getValidCount();
            NWZC150001_cpoSlsCrPMsg cscPMsg = pMsg.cpoSlsCr.no(ix);
            EZDMsg.copy(hdrCscPMsg, null, cscPMsg, null);
            ZYPEZDItemValueSetter.setValue(cscPMsg.configCatgCd, ccPMsg.configCatgCd); // QC#5568
            ZYPEZDItemValueSetter.setValue(cscPMsg.dsOrdPosnNum, ccPMsg.dsOrdPosnNum); // QC#5568
            pMsg.cpoSlsCr.setValidCount(++ix); // QC#5568
        }
    }

    private static NMZC260001PMsg callDefaultRepApi(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg ccPMsg, NWZC150001DsCpoCommonBean commonBean) {
        NMZC260001PMsg drPMsg = new NMZC260001PMsg();

        ZYPEZDItemValueSetter.setValue(drPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(drPMsg.shipToCustCd, ccPMsg.shipToCustCd);
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, pMsg.soldToCustLocCd);
        // 2020/04/27 QC#56638 Add End

        NMZC260001 drApi = new NMZC260001();
        drApi.execute(drPMsg, commonBean.getOnBatchType());
        return drPMsg;
    }

    private static void setDefaultSlsCrForConfigWriter(//
            NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg ccPMsg, String lineBizTpCd) {
        int ix = pMsg.cpoSlsCr.getValidCount();
        NWZC150001_cpoSlsCrPMsg cscPMsg = pMsg.cpoSlsCr.no(ix++);

        cscPMsg.clear();
        ZYPEZDItemValueSetter.setValue(cscPMsg.xxRqstTpCd, RQST_TP_SLS_CR_NEW);
        ZYPEZDItemValueSetter.setValue(cscPMsg.configCatgCd, ccPMsg.configCatgCd); // QC#5568
        ZYPEZDItemValueSetter.setValue(cscPMsg.dsOrdPosnNum, ccPMsg.dsOrdPosnNum); // QC#5568
        ZYPEZDItemValueSetter.setValue(cscPMsg.slsRepCd, pMsg.slsRepCd);
        ZYPEZDItemValueSetter.setValue(cscPMsg.slsRepRoleTpCd //
                , NWXC150001DsCheck.getLineBizRoleTpCdForWriter(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(cscPMsg.slsRepCrPct, PCT_100);
        ZYPEZDItemValueSetter.setValue(cscPMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y); // QC#5568

        pMsg.cpoSlsCr.setValidCount(ix);
    }

    /**
     * <pre>
     * set default sales rep for detail for return
     * </pre>
     * @param cscPMsg Sales Credit PMsg
     * @param pMsg PMessage
     * @param rtnDtlPMsg return detail PMsg
     * @param lineBizTpCd Line Biz Type Code
     * @param pMsg3List error message list <2016/10/13 S21_NA#7924-2 Add>
     * @param msgIdMgr S21ApiMessageIdMgr
     * @param commonBean NWZC150001DsCpoCommonBean
     */
    private static void setDefaultSlsRepForDetailForReturn(NWZC150001_cpoSlsCrPMsg cscPMsg, NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlPMsg, String lineBizTpCd, List<NWZC150003PMsg> pMsg3List, S21ApiMessageIdMgr msgIdMgr, NWZC150001DsCpoCommonBean commonBean) {
        String slsRep = getSlsRepForDetail(pMsg, rtnDtlPMsg.dsOrdPosnNum_B1.getValue(), CONFIG_CATG.INBOUND, lineBizTpCd, msgIdMgr, commonBean);  // 2016/10/13 S21_NA#7924-2 Add configCatgCd
        if (ZYPCommonFunc.hasValue(slsRep)) {
            // 2016/10/13 S21_NA#7924-2 Add Start
            if (!NWZC150001Common.isAvalableTocCd(pMsg.glblCmpyCd.getValue(), slsRep)) {
                NWZC150001Common.addMsgId3List(pMsg3List, rtnDtlPMsg.dsOrdPosnNum_B1.getValue(), NWZM2033E);
            }
            // 2016/10/13 S21_NA#7924-2 Add End
            ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.slsRepOrSlsTeamTocCd_B1, slsRep);
            return;
        }
    }

    // 2017/12/21 S21_NA#20050 Add Start
    private static void setRmaMachMstrPk(NWZC150001PMsg pMsg) {

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(i);
            if (ZYPCommonFunc.hasValue(rtnDtlPMsg.serNum_B1) //
                    && !ZYPCommonFunc.hasValue(rtnDtlPMsg.svcMachMstrPk_B1)) {
                SVC_MACH_MSTRTMsgArray svcMachMstrArray = getSvcMachMstrTMsg(pMsg.glblCmpyCd.getValue() //
                        , rtnDtlPMsg.serNum_B1.getValue() //
                        , rtnDtlPMsg.mdseCd_B1.getValue());
                if (svcMachMstrArray != null && svcMachMstrArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(rtnDtlPMsg.svcMachMstrPk_B1, svcMachMstrArray.no(0).svcMachMstrPk);
                }
            }
        }
    }

    
    /**
     * <pre>
     * 2018/10/18 S21_NA#26229 Modified for Public Method
     * Get Service Machine Master TMSG by Serial Number and Item Code (MdseCd)
     * @param glblCmpyCd Global Company Code
     * @param serNum Serial Number
     * @param mdseCd Item Code (Merchandise Code)
     * @return SVC_MACH_MSTRTMsgArray
     * </pre>
     */
    public static SVC_MACH_MSTRTMsgArray getSvcMachMstrTMsg(String glblCmpyCd, String serNum, String mdseCd) {

        SVC_MACH_MSTRTMsg svcMachMstrTmsgKey = new SVC_MACH_MSTRTMsg();

        svcMachMstrTmsgKey.setSQLID("020");
        svcMachMstrTmsgKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcMachMstrTmsgKey.setConditionValue("serNum01", serNum);
        svcMachMstrTmsgKey.setConditionValue("mdseCd01",  mdseCd + '%');

        return (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(svcMachMstrTmsgKey);
    }
    // 2017/12/21 S21_NA#20050 Add End

    // 2018/01/11 S21_NA#22372 Add Start
    private static void setDefaultFloorPrice(NWZC150001PMsg pMsg, NWZC150001DsCpoCommonBean commonBean) {

        if (!isNeedToCalcFlPrc(pMsg)) {
            return;
        }

        NWZC157001PMsg prcPMsg = callPricingApiForGetFlPrc(pMsg, commonBean); 

        if (S21ApiUtil.isXxMsgId(prcPMsg)) {

            for (int j = 0; j < pMsg.A.getValidCount(); j++) {
                NWZC150001_APMsg linePMsg = pMsg.A.no(j);
                ZYPEZDItemValueSetter.setValue(linePMsg.funcUnitFlPrcAmt_A1, BigDecimal.ZERO);
            }

            for (int j = 0; j < pMsg.rtnDtl.getValidCount(); j++) {
                NWZC150001_rtnDtlPMsg rmaLinePMsg = pMsg.rtnDtl.no(j);
                ZYPEZDItemValueSetter.setValue(rmaLinePMsg.funcUnitFlPrcAmt_B1, BigDecimal.ZERO);
            }

        } else {

            for (int i = 0; i < prcPMsg.NWZC157002PMsg.getValidCount(); i++) {
                NWZC157002PMsg prcPMsg2 = prcPMsg.NWZC157002PMsg.no(i);

                if (CONFIG_CATG.OUTBOUND.equals(prcPMsg2.configCatgCd.getValue())) {
                    for (int j = 0; j < pMsg.A.getValidCount(); j++) {
                        NWZC150001_APMsg linePMsg = pMsg.A.no(j);
                        if (S21StringUtil.isEquals(prcPMsg2.trxLineNum.getValue(), linePMsg.cpoDtlLineNum_A1.getValue())
                                && S21StringUtil.isEquals(prcPMsg2.trxLineSubNum.getValue(), linePMsg.cpoDtlLineSubNum_A1.getValue())) {
                            // QC#23773 2018/01/25 Add Start
                            // if (S21ApiUtil.isXxMsgId(prcPMsg2)) {
                            //     ZYPEZDItemValueSetter.setValue(linePMsg.funcUnitFlPrcAmt_A1, BigDecimal.ZERO);
                            // } else {
                            //     NWZC157004PMsg prcPMsg4 = prcPMsg.NWZC157004PMsg.no(i);
                            //     ZYPEZDItemValueSetter.setValue(linePMsg.funcUnitFlPrcAmt_A1, prcPMsg4.xxUnitNetPrcAmt);
                            // }
                            NWZC157004PMsg prcPMsg4 = prcPMsg.NWZC157004PMsg.no(i);
                            if (ZYPCommonFunc.hasValue(prcPMsg4.xxUnitNetPrcAmt)) {
                                ZYPEZDItemValueSetter.setValue(linePMsg.funcUnitFlPrcAmt_A1, prcPMsg4.xxUnitNetPrcAmt);
                            } else {
                                ZYPEZDItemValueSetter.setValue(linePMsg.funcUnitFlPrcAmt_A1, BigDecimal.ZERO);
                            }
                            // QC#23773 2018/01/25 Add End
                            break;
                        }
                    }

                } else if (CONFIG_CATG.INBOUND.equals(prcPMsg2.configCatgCd.getValue())) {
                    for (int j = 0; j < pMsg.rtnDtl.getValidCount(); j++) {
                        NWZC150001_rtnDtlPMsg rmaLinePMsg = pMsg.rtnDtl.no(j);
                        if (S21StringUtil.isEquals(prcPMsg2.trxLineNum.getValue(), rmaLinePMsg.cpoDtlLineNum_B1.getValue())
                                && S21StringUtil.isEquals(prcPMsg2.trxLineSubNum.getValue(), rmaLinePMsg.cpoDtlLineSubNum_B1.getValue())) {
                            // QC#23773 2018/01/25 Add Start
                            // if (S21ApiUtil.isXxMsgId(prcPMsg2)) {
                            //     ZYPEZDItemValueSetter.setValue(rmaLinePMsg.funcUnitFlPrcAmt_B1, BigDecimal.ZERO);
                            // } else {
                            //     NWZC157004PMsg prcPMsg4 = prcPMsg.NWZC157004PMsg.no(i);
                            //     ZYPEZDItemValueSetter.setValue(rmaLinePMsg.funcUnitFlPrcAmt_B1, prcPMsg4.xxUnitNetPrcAmt);
                            // }
                            NWZC157004PMsg prcPMsg4 = prcPMsg.NWZC157004PMsg.no(i);
                            if (ZYPCommonFunc.hasValue(prcPMsg4.xxUnitNetPrcAmt)) {
                                ZYPEZDItemValueSetter.setValue(rmaLinePMsg.funcUnitFlPrcAmt_B1, prcPMsg4.xxUnitNetPrcAmt);
                            } else {
                                ZYPEZDItemValueSetter.setValue(rmaLinePMsg.funcUnitFlPrcAmt_B1, BigDecimal.ZERO);
                            }
                            // QC#23773 2018/01/25 Add End
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean isNeedToCalcFlPrc(NWZC150001PMsg pMsg) {

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg linePMsg = pMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(linePMsg.funcUnitFlPrcAmt_A1)) {
                return true;
            }
        }
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rmaLinePMsg = pMsg.rtnDtl.no(i);
            if (!ZYPCommonFunc.hasValue(rmaLinePMsg.funcUnitFlPrcAmt_B1)) {
                return true;
            }
        }

        return false;
    }

    private static NWZC157001PMsg callPricingApiForGetFlPrc(NWZC150001PMsg pMsg, NWZC150001DsCpoCommonBean commonBean) {

        String dsOrdLineCatgCd = getPrftSvcLineCatgCd(pMsg);

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.FLOOR_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd,
                NWXC150001DsCheck.getLineBizTpCd(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, pMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {

            NWZC150001_APMsg linePMsg = pMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(linePMsg.funcUnitFlPrcAmt_A1)) {
                continue;
            }

            NWZC157002PMsg prcApiPMsg2 = prcApiPMsg.NWZC157002PMsg.no(prcApiPMsg.NWZC157002PMsg.getValidCount());
            NWZC150001_cpoConfigPMsg cpoConfig = getCpoConfig(pMsg, linePMsg);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.trxLineNum, linePMsg.cpoDtlLineNum_A1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.trxLineSubNum, linePMsg.cpoDtlLineSubNum_A1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.prcBaseDt, pMsg.slsDt);
            // QC#23773 2018/01/25 Mod Start
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg2.billToCustCd, pMsg.billToCustAcctCd);
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg2.shipToCustCd, linePMsg.shipToCustCd_A1);
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsAcctNum_BL, pMsg.billToCustAcctCd);
            // ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsAcctNum_SH,
            //        NWXC150001DsCheck.getDefaultAcctFromShipToCust(pMsg.glblCmpyCd.getValue(), linePMsg.shipToCustCd_A1.getValue()));
            // QC#23773 2018/01/25 Mod End
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.billToCustCd, cpoConfig.billToCustCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.shipToCustCd, cpoConfig.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsAcctNum_BL, cpoConfig.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsAcctNum_SH, cpoConfig.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.prcCatgCd, linePMsg.flPrcListCd_A1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.csmpNum, linePMsg.csmpContrNum_A1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dlrRefNum, linePMsg.dlrRefNum_A1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.mdseCd, linePMsg.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.pkgUomCd, linePMsg.custUomCd_A1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsOrdLineCatgCd, dsOrdLineCatgCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.ordQty, linePMsg.ordQty_A1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.ordCustUomQty, linePMsg.ordCustUomQty_A1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

            prcApiPMsg.NWZC157002PMsg.setValidCount(prcApiPMsg.NWZC157002PMsg.getValidCount() + 1);
        }

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {

            NWZC150001_rtnDtlPMsg rmaLinePMsg = pMsg.rtnDtl.no(i);

            if (ZYPCommonFunc.hasValue(rmaLinePMsg.funcUnitFlPrcAmt_B1)) {
                continue;
            }

            NWZC157002PMsg prcApiPMsg2 = prcApiPMsg.NWZC157002PMsg.no(prcApiPMsg.NWZC157002PMsg.getValidCount());
            NWZC150001_cpoConfigPMsg cpoConfig = getCpoConfig(pMsg, rmaLinePMsg);

            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.trxLineNum, rmaLinePMsg.cpoDtlLineNum_B1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.trxLineSubNum, rmaLinePMsg.cpoDtlLineSubNum_B1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.configCatgCd, CONFIG_CATG.INBOUND);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.prcBaseDt, pMsg.slsDt);
            // QC#23773 2018/01/25 Mod Start
            //ZYPEZDItemValueSetter.setValue(prcApiPMsg2.billToCustCd, pMsg.billToCustAcctCd);
            //ZYPEZDItemValueSetter.setValue(prcApiPMsg2.shipToCustCd, rmaLinePMsg.shipToCustCd_B1);
            //ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsAcctNum_BL, pMsg.billToCustAcctCd);
            //ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsAcctNum_SH,
            //        NWXC150001DsCheck.getDefaultAcctFromShipToCust(pMsg.glblCmpyCd.getValue(), rmaLinePMsg.shipToCustCd_B1.getValue()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.billToCustCd, cpoConfig.billToCustCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.shipToCustCd, cpoConfig.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsAcctNum_BL, cpoConfig.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsAcctNum_SH, cpoConfig.shipToCustAcctCd);
            // QC#23773 2018/01/25 Mod End
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.prcCatgCd, rmaLinePMsg.flPrcListCd_B1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.csmpNum, rmaLinePMsg.csmpContrNum_B1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dlrRefNum, rmaLinePMsg.dlrRefNum_B1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.mdseCd, rmaLinePMsg.mdseCd_B1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.pkgUomCd, rmaLinePMsg.custUomCd_B1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.dsOrdLineCatgCd, dsOrdLineCatgCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.ordQty, rmaLinePMsg.ordQty_B1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.ordCustUomQty, rmaLinePMsg.ordCustUomQty_B1);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg2.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

            prcApiPMsg.NWZC157002PMsg.setValidCount(prcApiPMsg.NWZC157002PMsg.getValidCount() + 1);
        }

        new NWZC157001().execute(prcApiPMsg, commonBean.getOnBatchType());
        return prcApiPMsg;
    }

    private static String getPrftSvcLineCatgCd(NWZC150001PMsg pMsg) {

        ORD_LINE_VAL_SETTMsg ordLineValSetTmsgKey = new ORD_LINE_VAL_SETTMsg();

        ordLineValSetTmsgKey.setSQLID("002");
        ordLineValSetTmsgKey.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        ordLineValSetTmsgKey.setConditionValue("?ordLineCtxTpCd01", ORD_LINE_CTX_TP.PRFT_SVC_LINE);

        ORD_LINE_VAL_SETTMsgArray ordLineValSetTmsgAry = 
            (ORD_LINE_VAL_SETTMsgArray) S21ApiTBLAccessor.findByCondition(ordLineValSetTmsgKey);

        if (ordLineValSetTmsgAry.length() > 0) {
            return ordLineValSetTmsgAry.no(0).dsOrdLineCatgCd.getValue();
        } else {
            return null;
        }
    }
    
    // QC#23773 2018/01/25 Add Start
    private static NWZC150001_cpoConfigPMsg getCpoConfig(NWZC150001PMsg pMsg, NWZC150001_APMsg linePMsg) {
        NWZC150001_cpoConfigPMsg config = new NWZC150001_cpoConfigPMsg();
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configMsg = pMsg.cpoConfig.no(i);
            if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configMsg.configCatgCd.getValue())) {
                if (S21StringUtil.isEquals(linePMsg.dsOrdPosnNum_A1.getValue(), configMsg.dsOrdPosnNum.getValue())) {
                    config = configMsg;
                }
            }
        }
        return config;
    }

    private static NWZC150001_cpoConfigPMsg getCpoConfig(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rmaLinePMsg) {
        NWZC150001_cpoConfigPMsg config = new NWZC150001_cpoConfigPMsg();
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg configMsg = pMsg.cpoConfig.no(i);
            if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configMsg.configCatgCd.getValue())) {
                if (S21StringUtil.isEquals(rmaLinePMsg.dsOrdPosnNum_B1.getValue(), configMsg.dsOrdPosnNum.getValue())) {
                    config = configMsg;
                }
            }
        }
        return config;
    }
    // QC#23773 2018/01/25 Add End
    // 2018/01/11 S21_NA#22372 Add End

    // 2018/01/11 S21_NA#23329 Add Start
    private static void checkSoftModelSetting(NWZC150001PMsg pMsg //
            , NWZC150001_cpoConfigPMsg configPMsg //
            // , NSZC048001PMsg svcMdlPMsg // 2018/05/20 S21_NA#25604 Del
            , NWXC150001SvcMdlFuncParamBean funcParam // 2018/05/20 S21_NA#25604 Add
            , List<NWZC150002PMsg> pMsg2List //
            , List<String> ibItemList) { // 2018/03/20 S21_NA#24698 Add ibItemList

        if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configPMsg.configCatgCd.getValue())) {
            return;
        }
        // 2018/05/20 S21_NA#25604 Mod Start
//        if (svcMdlPMsg == null) {
//            return;
//        }
//        if (!ZYPCommonFunc.hasValue(svcMdlPMsg.prntMdseCd)) {
//            return;
//        }
//        String prntMdseCd = svcMdlPMsg.prntMdseCd.getValue();
        if (funcParam == null) {
            return;
        }
        if (!funcParam.isSvcMdlTpSoftWare()) {
            return;
        }
        if (!ZYPCommonFunc.hasValue(funcParam.getPrntMdseCd())) {
            return;
        }
        String prntMdseCd = funcParam.getPrntMdseCd();
        // 2018/05/20 S21_NA#25604 End
        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        boolean isOk = false;
        for (int n = 0; n < pMsg.A.getValidCount(); n++) {
            NWZC150001_APMsg lineMsg = pMsg.A.no(n);
            if (!S21StringUtil.isEquals(dsOrdPosnNum, lineMsg.dsOrdPosnNum_A1.getValue())) {
                continue;
            }
            if (NWXC150001DsCheck.isNearEqualsItem(glblCmpyCd, prntMdseCd, lineMsg.mdseCd_A1.getValue())) {
                isOk = true;
                break;
            }
        }
        // 2018/03/20 S21_NA#24698 Add Start
        if (!isOk && ibItemList != null && ibItemList.size() > 0) {
            for (String ibItemCd : ibItemList) {
                if (NWXC150001DsCheck.isNearEqualsItem(glblCmpyCd, prntMdseCd, ibItemCd)) {
                    isOk = true;
                    break;
                }
            }
        }
        // 2018/03/20 S21_NA#24698 Add End
        if (!isOk) {
           NWZC150001Common.addMsgId2List(pMsg2List, dsOrdPosnNum, NWZM2252E);
        }
    }

    private static List<String> getMdseCdList(String glblCmpyCd, String mdseCd) {

        List<String> mdseCdList = new ArrayList<String>(0);
        mdseCdList.add(mdseCd);

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        int ordTakeCodeLen = ordTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();

        if (mdseCd.length() > ordTakeCodeLen) {
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCd.substring(0, ordTakeCodeLen));
            ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);
            if (ordTakeMdseTMsg != null) {
                mdseCdList.add(ordTakeMdseTMsg.ordTakeMdseCd.getValue());
            }
        }
        return mdseCdList;
    }

    private static boolean isChildItemOfModel(String glblCmpyCd, String mdseCd) {

        List<String> childMdseCdList = getMdseCdList(glblCmpyCd, mdseCd);
        return NWZC150001Query.getInstance().isModelChildItem(glblCmpyCd, childMdseCdList);
    }
    // 2018/01/11 S21_NA#23329 Add End

    // 2018/03/20 S21_NA#24698 Add Start
    private static CONFIG_TPTMsg getConfigTp(String glblCmpyCd, String configTpCd) {

        CONFIG_TPTMsg tMsg = new CONFIG_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.configTpCd, configTpCd);

        return (CONFIG_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    private static SVC_CONFIG_MSTRTMsg getSvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, svcConfigMstrPk);

        return (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private static DS_MDLTMsg getDsMdl(NWZC150001PMsg pMsg, BigDecimal mdlId) {

        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, mdlId);
        return (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(dsMdlTMsg);
    }
    // 2018/03/20 S21_NA#24698 Add End

    // 2018/05/20 S21_NA#25604 Add Start
    private static NWXC150001SvcMdlFuncParamBean callSvcMdlApi(NWZC150001_cpoConfigPMsg configPMsg //
            , NWZC150001PMsg pMsg //
            , NWZC150001DsCpoCommonBean commonBean) {

        // 2018/07/04 S21_NA#26607 Add Start
        printDetailParam(pMsg);
        // 2018/07/04 S21_NA#26607 Add End

        boolean isEquipMentOrder = NWXC150001DsCheck.isRetailEquipOrder( //
                pMsg.glblCmpyCd.getValue() //
                , pMsg.dsOrdCatgCd.getValue() //
                , pMsg.dsOrdTpCd.getValue() //
                , pMsg.dsOrdRsnCd.getValue());
        boolean isServiceExchangeOrder = NWXC150001DsCheck.isSvcExch( //
                pMsg.glblCmpyCd.getValue() //
                , pMsg.dsOrdCatgCd.getValue() //
                , pMsg.dsOrdTpCd.getValue());
        if (!isEquipMentOrder && !isServiceExchangeOrder) {
            return null;
        }
        String dsOrdPosnNum = configPMsg.dsOrdPosnNum.getValue();
        boolean hasConfigId = false;
        boolean isConfigOutbnd = CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue());
        boolean isConfigInbnd = CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue());
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String configTpCd = configPMsg.configTpCd.getValue();

        NWXC150001SvcMdlFuncParamBean paramBean = new NWXC150001SvcMdlFuncParamBean();
        paramBean.setGlblCmpyCd(glblCmpyCd);
        paramBean.setSlsDt(pMsg.slsDt.getValue());
        paramBean.setOnBatchType(commonBean.getOnBatchType());
        if (isConfigOutbnd) {
            if (NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configTpCd, CONFIG_CATG.OUTBOUND, false, true, false)) {
                hasConfigId = ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk);
            } else {
                hasConfigId = false;
            }
        } else if (isConfigInbnd) {
            if (NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configTpCd, CONFIG_CATG.INBOUND, false, true, false)) {
                hasConfigId = ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk);
            } else {
                hasConfigId = false;
            }
        }
        BigDecimal configId = null;
        if (hasConfigId) {
            paramBean.setSvcConfigMstrPk(configPMsg.svcConfigMstrPk.getValue());
            configId = configPMsg.svcConfigMstrPk.getValue();
        }
        if (isEquipMentOrder || isServiceExchangeOrder) {
            if (isConfigOutbnd) {
                // 2018/07/04 S21_NA#26607 Add Start
                List<CPO_DTLTMsg> savedLineList = getSavedDetail(glblCmpyCd, pMsg.cpoOrdNum.getValue(), dsOrdPosnNum);
                boolean hasPrnt = false;
                if (savedLineList != null && !savedLineList.isEmpty()) {
                    for (CPO_DTLTMsg savedCpoDtlTMsg : savedLineList) {
                        if (!isParameteredDetail(savedCpoDtlTMsg, pMsg)) {
                            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, savedCpoDtlTMsg.baseCmptFlg.getValue())) {
                                paramBean.setPrntMdseCd(savedCpoDtlTMsg.mdseCd.getValue());
                                hasPrnt = true;
                            } else {
                                paramBean.addOutBndChildMdseCdList(savedCpoDtlTMsg.mdseCd.getValue());
                            }
                        }
                    }
                }
                // 2018/07/04 S21_NA#26607 Add End
                for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                    NWZC150001_APMsg linePMsg = pMsg.A.no(i);
                    String mdseCd = linePMsg.mdseCd_A1.getValue();
                    if (!S21StringUtil.isEquals(dsOrdPosnNum, linePMsg.dsOrdPosnNum_A1.getValue())) {
                        continue;
                    }
                    if (!hasConfigId //
                            && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, linePMsg.baseCmptFlg_A1.getValue()) //
                            && !hasPrnt) { //  2018/07/04 S21_NA#26607 Add Condition !hasPrnt
                        paramBean.setPrntMdseCd(mdseCd);
                    } else {
                        paramBean.addOutBndChildMdseCdList(mdseCd);
                    }
                }
            } else if (isConfigInbnd) {
                // 2018/07/04 S21_NA#26607 Add Start
                List<DS_CPO_RTRN_DTLTMsg> savedRtrnLineList = getSavedRtrnDetail(glblCmpyCd, pMsg.cpoOrdNum.getValue(), dsOrdPosnNum);
                boolean hasPrnt = false;
                if (savedRtrnLineList != null && !savedRtrnLineList.isEmpty()) {
                    for (DS_CPO_RTRN_DTLTMsg savedCpoRtrnDtlTMsg : savedRtrnLineList) {
                        if (!isParameteredRtrnDetail(savedCpoRtrnDtlTMsg, pMsg)) {
                            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, savedCpoRtrnDtlTMsg.baseCmptFlg.getValue())) {
                                paramBean.setPrntMdseCd(savedCpoRtrnDtlTMsg.mdseCd.getValue());
                                hasPrnt = true;
                            } else {
                                paramBean.addOutBndChildMdseCdList(savedCpoRtrnDtlTMsg.mdseCd.getValue());
                            }
                        }
                    }
                }
                // 2018/07/04 S21_NA#26607 Add End
                for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
                    NWZC150001_rtnDtlPMsg rmaLinePMsg = pMsg.rtnDtl.no(i);
                    String mdseCd = rmaLinePMsg.mdseCd_B1.getValue();
                    if (!S21StringUtil.isEquals(dsOrdPosnNum, rmaLinePMsg.dsOrdPosnNum_B1.getValue())) {
                        continue;
                    }
                    if (!hasConfigId //
                            && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rmaLinePMsg.baseCmptFlg_B1.getValue()) //
                            && !hasPrnt) { //  2018/07/04 S21_NA#26607 Add Condition !hasPrnt
                        paramBean.setPrntMdseCd(mdseCd);
                    } else {
                        paramBean.addOutBndChildMdseCdList(mdseCd);
                    }
                }
            }
        }
        if (isServiceExchangeOrder && isConfigOutbnd && hasConfigId) {
            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
                NWZC150001_cpoConfigPMsg rmaConfigPMsg = pMsg.cpoConfig.no(i);
                if (!S21StringUtil.isEquals(CONFIG_CATG.INBOUND, rmaConfigPMsg.configCatgCd.getValue())) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(rmaConfigPMsg.svcConfigMstrPk)) {
                    continue;
                }
                if (configId.compareTo(rmaConfigPMsg.svcConfigMstrPk.getValue()) != 0) {
                    continue;
                }
                for (int j = 0; j < pMsg.rtnDtl.getValidCount(); j++) {
                    NWZC150001_rtnDtlPMsg rtnLinePMsg = pMsg.rtnDtl.no(j);
                    if (!S21StringUtil.isEquals(rmaConfigPMsg.dsOrdPosnNum.getValue(), rtnLinePMsg.dsOrdPosnNum_B1.getValue())) {
                        continue;
                    }
                    paramBean.addInBndChi(rtnLinePMsg.mdseCd_B1.getValue(), rtnLinePMsg.serNum_B1.getValue(), rtnLinePMsg.svcMachMstrPk_B1.getValue());
                }
            }
        }
        NWXC150001SvcMdlFunc.getModel(paramBean);

        return paramBean;
    }
    // 2018/05/20 S21_NA#25604 Add End
    // 2018/07/04 S21_NA#26607 Add Start
    private static List<CPO_DTLTMsg> getSavedDetail(String glblCmpyCd, String cpoOrdNum, String dsOrdPosnNum) {

        if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }
        CPO_DTLTMsg keyCpoDtlTMsg = new CPO_DTLTMsg();
        keyCpoDtlTMsg.setSQLID("502");
        keyCpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        keyCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        keyCpoDtlTMsg.setConditionValue("dsOrdPosnNum01", dsOrdPosnNum);

        CPO_DTLTMsgArray rsltArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(keyCpoDtlTMsg);
        if (rsltArray == null || rsltArray.getValidCount() == 0) {
            return null;
        }
        List<CPO_DTLTMsg> rsltList = new ArrayList<CPO_DTLTMsg>(0);
        for (int i = 0; i < rsltArray.getValidCount(); i++) {
            if (!S21StringUtil.isEquals(ORD_LINE_STS.CANCELLED, rsltArray.no(i).ordLineStsCd.getValue())) {
                rsltList.add(rsltArray.no(i));
            }
        }
        return rsltList;
    }

    private static boolean isParameteredDetail(CPO_DTLTMsg savedCpoDtlTMsg, NWZC150001PMsg pMsg) {

        String compLine = savedCpoDtlTMsg.dsOrdPosnNum.getValue() + "." + String.valueOf(savedCpoDtlTMsg.dsCpoLineNum.getValueInt());
        if (ZYPCommonFunc.hasValue(savedCpoDtlTMsg.dsCpoLineSubNum)) {
            compLine = compLine + "." + String.valueOf(savedCpoDtlTMsg.dsCpoLineSubNum.getValueInt());
        }
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            String paramLine = pMsg.A.no(i).dsOrdPosnNum_A1.getValue() + "." + String.valueOf(pMsg.A.no(i).dsCpoLineNum_A1.getValueInt());
            if (ZYPCommonFunc.hasValue(pMsg.A.no(i).dsCpoLineSubNum_A1)) {
                paramLine = paramLine + "." + String.valueOf(pMsg.A.no(i).dsCpoLineSubNum_A1.getValueInt());
            }
            if (S21StringUtil.isEquals(compLine, paramLine)) {
                return true;
            }
        }
        return false;
    }

    private static List<DS_CPO_RTRN_DTLTMsg> getSavedRtrnDetail(String glblCmpyCd, String cpoOrdNum, String dsOrdPosnNum) {

        if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }
        DS_CPO_RTRN_DTLTMsg keyCpoDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        keyCpoDtlTMsg.setSQLID("005");
        keyCpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        keyCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        keyCpoDtlTMsg.setConditionValue("dsOrdPosnNum01", dsOrdPosnNum);

        DS_CPO_RTRN_DTLTMsgArray rsltArray = (DS_CPO_RTRN_DTLTMsgArray) EZDTBLAccessor.findByCondition(keyCpoDtlTMsg);
        if (rsltArray == null || rsltArray.getValidCount() == 0) {
            return null;
        }
        List<DS_CPO_RTRN_DTLTMsg> rsltList = new ArrayList<DS_CPO_RTRN_DTLTMsg>(0);
        for (int i = 0; i < rsltArray.getValidCount(); i++) {
            if (!S21StringUtil.isEquals(RTRN_LINE_STS.CANCELLED, rsltArray.no(i).rtrnLineStsCd.getValue())) {
                rsltList.add(rsltArray.no(i));
            }
        }
        return rsltList;
    }

    private static boolean isParameteredRtrnDetail(DS_CPO_RTRN_DTLTMsg savedRtrnCpoDtlTMsg, NWZC150001PMsg pMsg) {

        String compLine = savedRtrnCpoDtlTMsg.dsOrdPosnNum.getValue() + "." + String.valueOf(savedRtrnCpoDtlTMsg.dsCpoLineNum.getValueInt());
        if (ZYPCommonFunc.hasValue(savedRtrnCpoDtlTMsg.dsCpoLineSubNum)) {
            compLine = compLine + "." + String.valueOf(savedRtrnCpoDtlTMsg.dsCpoLineSubNum.getValueInt());
        }
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            String paramLine = pMsg.rtnDtl.no(i).dsOrdPosnNum_B1.getValue() + "." + String.valueOf(pMsg.rtnDtl.no(i).dsCpoLineNum_B1.getValueInt());
            if (ZYPCommonFunc.hasValue(pMsg.rtnDtl.no(i).dsCpoLineSubNum_B1)) {
                paramLine = paramLine + "." + String.valueOf(pMsg.A.no(i).dsCpoLineSubNum_A1.getValueInt());
            }
            if (S21StringUtil.isEquals(compLine, paramLine)) {
                return true;
            }
        }
        return false;
    }

    private static void printDetailParam(NWZC150001PMsg pMsg) {

        if (!LOG_OUT) {
            return;
        }
        System.out.println("NWZC150001ForDefaultValueSetter#printDetailParam() ==========>");
        System.out.println("**************** CPO Config ****************");
        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001_cpoConfigPMsg confPMsg = pMsg.cpoConfig.no(i);
            System.out.println(String.format("CPO_CONFIG Catgory: %s Posn#: %s Model: %d Model Txt: %s", //
                    confPMsg.configCatgCd.getValue(), //
                    confPMsg.dsOrdPosnNum.getValue(), //
                    confPMsg.mdlId.getValueInt(), //
                    confPMsg.mdlDescTxt.getValue()));
        }

        System.out.println("**************** CPO Detail ****************");
        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001_APMsg linePMsg = pMsg.A.no(i);
            System.out.println(String.format("CPO_DTL %s.%d.%d Item Code: %s", //
                    linePMsg.dsOrdPosnNum_A1.getValue(), //
                    linePMsg.dsCpoLineNum_A1.getValueInt(), //
                    linePMsg.dsCpoLineSubNum_A1.getValueInt(), //
                    linePMsg.mdseCd_A1.getValue()));
        }

        System.out.println("**************** RTRN Detail ****************");
        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001_rtnDtlPMsg rmaLinePMsg = pMsg.rtnDtl.no(i);
            System.out.println(String.format("RTRN_DTL %s.%d.%d Item Code: %s", //
                    rmaLinePMsg.dsOrdPosnNum_B1.getValue(), //
                    rmaLinePMsg.dsCpoLineNum_B1.getValueInt(), //
                    rmaLinePMsg.dsCpoLineSubNum_B1.getValueInt(), //
                    rmaLinePMsg.mdseCd_B1.getValue()));
        }
        System.out.println("NWZC150001ForDefaultValueSetter#printDetailParam() <==========");
    }
    // 2018/07/04 S21_NA#26607 Add End
    
    // 2018/09/21 S21_NA#28363 Add Start
    private static void setDefaultSlsRep(NWZC150001PMsg pMsg, List<NWZC150002PMsg> pMsg2List, S21ApiMessageIdMgr msgIdMgr) {

        if (ZYPCommonFunc.hasValue(pMsg.slsRepCd)) {
            return;
        }
        
        // Call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, pMsg.addShipToCustCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.billToCustCd, pMsg.soldToCustLocCd);

        if (!callDefSlsRepApi(pMsg, nMZC260001PMsg, msgIdMgr)) {
            return;
        }

        String lineBizTpCd = NWXC150001DsCheck.getLineBizTpCd(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), pMsg.dsOrdTpCd.getValue());
        String trtyGrpTpTxt = getTrtyGrpTpTxtFromDsOrdTpCd(pMsg);
        List<String> trtyGrpTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)) {
            trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
        }
        
        LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
        lineBizRoleTpTMsg.setSQLID("001");
        lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
        LINE_BIZ_ROLE_TPTMsgArray TMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
        List<String> targetWriterList = new ArrayList<String>();
        if (TMsgArray != null && TMsgArray.length() > 0) {
            for (int i = 0; i < TMsgArray.length(); i++) {
                LINE_BIZ_ROLE_TPTMsg tMsg = TMsgArray.no(i);
                targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
            }
        }

        List<NMZC260001_defSlsRepListPMsg> matchLobRoleSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;

        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);
            
            if(ZYPCommonFunc.hasValue(defSlsRepPMsg.xxRsltFlg) && ZYPConstant.FLG_ON_Y.equals(defSlsRepPMsg.xxRsltFlg.getValue())//
                    && !matchLobRoleSlsRepPMsgList.isEmpty()){
                continue;
            }
            if ((trtyGrpTpCdList.isEmpty() && defSlsRepPMsg.lineBizTpCd.getValue().equals(lineBizTpCd))
                    || (trtyGrpTpCdList.size() > 0 && trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {

                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                if (targetWriterList.contains(lineBizRoleTpCd)) {
                    matchLobRoleSlsRepPMsgList.add(defSlsRepPMsg);
                }
            }
        }

        if (defSlsRepMsgArray.getValidCount() > 0) {
            if (matchLobRoleSlsRepPMsgList.size() == 1 && ZYPCommonFunc.hasValue(matchLobRoleSlsRepPMsgList.get(0).tocCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, matchLobRoleSlsRepPMsgList.get(0).tocCd.getValue());
            } else {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0642E, pMsg);
            }
        } else{
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0642E, pMsg);
        }

    }
    
    /**
     * Call NMZC2600 Default Sales Rep API
     * @param bizMsg NWAL1770CMsg
     * @param pMsg NMZC260001PMsg
     * @return has API error : false
     */
    private static boolean callDefSlsRepApi(NWZC150001PMsg pMsg, NMZC260001PMsg nMZC260001PMsg, S21ApiMessageIdMgr msgIdMgr) {

        // Call NMZC2600 Dafault Sales Rep API
        new NMZC260001().execute(nMZC260001PMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(nMZC260001PMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nMZC260001PMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0642E, pMsg);
                    return false;
                }
            }
        }

        return true;
    }
    
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param bizMsg
     * </pre>
     */
    private static String getTrtyGrpTpTxtFromDsOrdTpCd(NWZC150001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(pMsg.dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, pMsg.dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            return dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();
        }

        return null;
    }
    // 2018/09/21 S21_NA#28363 Add End
}
