/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200.common;

import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0054E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM2201E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWZM1328E;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.blap.NWAL2200.NWAL2200Query;
import business.blap.NWAL2200.NWAL2200_ACMsg;
import business.blap.NWAL2200.NWAL2200_BCMsg;
import business.blap.NWAL2200.NWAL2200_CCMsg;
import business.blap.NWAL2200.NWAL2200_CCMsgArray;
import business.blap.NWAL2200.NWAL2200_DCMsg;
import business.blap.NWAL2200.constant.NWAL2200Constant;
import business.db.CPO_SRC_TPTMsg;
import business.db.DS_IMPT_DTL_EXT_ATTRBTMsg;
import business.db.DS_IMPT_EXT_ATTRBTMsg;
import business.db.DS_IMPT_ORD_ERRTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_STSTMsg;
import business.db.XTRNL_INTFC_XREFTMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_xxMsgIdListPMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxMsgIdListPMsg;
import business.parts.NWZC157001_xxPrcListPMsgArray;
import business.parts.NWZC157002PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ImportAttribute;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Result;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_XREF_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * NWAL2200CommonLogicForDsCheck
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          S21_NA#8651
 * 2016/07/12   Fujitsu         T.Ishii         Update          S21_NA#11428
 * 2016/07/15   Fujitsu         T.Ishii         Update          S21_NA#11435
 * 2016/07/15   Fujitsu         T.Ishii         Update          S21_NA#11561
 * 2016/09/15   Fujitsu         S.Ohki          Update          S21_NA#14261
 * 2016/09/16   Fujitsu         S.Ohki          Update          S21_NA#12145
 * 2016/09/28   Fujitsu         S.Ohki          Update          S21_NA#14744
 * 2016/10/04   Fujitsu         T.Ishii         Update          S21_NA#14922
 * 2016/10/05   Fujitsu         T.Ishii         Update          S21_NA#11595
 * 10/05/2016   Fujitsu         T.Ishii         Update          S21_NA#15004
 * 10/06/2016   Fujitsu         T.Ishii         Update          S21_NA#15005
 * 10/11/2016   Fujitsu         T.Ishii         Update          S21_NA#15077
 * 10/25/2016   Fujitsu         T.Ishii         Update          S21_NA#15560
 * 11/02/2016   Fujitsu         T.Ishii         Update          S21_NA#14815
 * 2016/11/17   Fujitsu         M.Yamada        Update          S21_NA#14815
 * 2016/12/19   Fujitsu         M.Ohno          Update          S21_NA#16460
 * 2017/02/16   Fujitsu         N.Aoyama        Update          S21_NA#16575
 * 2017/06/09   Fujitsu         N.Aoyama        Update          S21_NA#18296
 * 2017/10/25   CITS            T.Gotoda        Update          S21_NA#22012
 * 2018/06/13   Fujitsu         Y.Kanefusa      Update          S21_NA#24245
 * 2018/12/13   Fujitsu         T.Noguchi       Update          S21_NA#29315
 *</pre>
 */
@Deprecated
public class NWAL2200CommonLogicForDsCheck {

    /**
     * dsCheck
     * @param bizMsg NWAL2200CMsg
     * @return List<DS_IMPT_ORD_ERRTMsg>
     */
    @Deprecated
    public static List<DS_IMPT_ORD_ERRTMsg> dsCheck(NWAL2200CMsg bizMsg) {

        List<DS_IMPT_ORD_ERRTMsg> errList = new ArrayList<DS_IMPT_ORD_ERRTMsg>();

        if (CPO_SRC_TP.EDI.equals(bizMsg.cpoSrcTpCd.getValue())) {
            deriveEdiData(bizMsg, errList);
        }

        deriveData(bizMsg, errList);

        checkEssentialForHeader(bizMsg, bizMsg, errList);

        deriveDataConfig(bizMsg, errList);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2200_ACMsg config = bizMsg.A.no(i);
            checkEssentialForConfig(bizMsg, config, errList);
        }

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {

            NWAL2200_CCMsg config = bizMsg.C.no(i);
            checkEssentialForConfig(bizMsg, config, errList);
        }

        deriveDataDetail(bizMsg, errList);

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL2200_BCMsg line = bizMsg.B.no(i);

            // S21_NA#11561 add start
            if (!S21StringUtil.isEquals(line.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                // skip excluded import line.
                continue;
            }
            // S21_NA#11561 add end
            checkEssentialForDetail(bizMsg, line, errList);
        }
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {

            NWAL2200_DCMsg line = bizMsg.D.no(i);
            checkEssentialForReturn(bizMsg, line, errList);
        }

        checkMasterForHeader(bizMsg, bizMsg, errList);

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL2200_BCMsg line = bizMsg.B.no(i);

            // S21_NA#11561 add start
            if (!S21StringUtil.isEquals(line.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                // skip excluded import line.
                continue;
            }
            // S21_NA#11561 add end
            checkMasterForDetail(bizMsg, bizMsg, line, errList);
        }

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            NWAL2200_DCMsg line = bizMsg.D.no(i);
            checkMasterForReturn(bizMsg, bizMsg, line, errList);
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2200_ACMsg config = bizMsg.A.no(i);
            checkMasterForConfig(bizMsg, config, errList);
        }

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2200_CCMsg config = bizMsg.C.no(i);
            checkMasterForConfig(bizMsg, config, errList);
        }

        otherCheck(bizMsg, errList);

        validatePricing(bizMsg, bizMsg, errList); // S21_NA#11435

        return errList;
    }

    private static void deriveEdiData(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {
        // *****************************************************************
        // derive EDI Attribute
        // *****************************************************************
        List<?> editAttbMapList = deriveEdiAttrb(bizMsg, errList);

        String dsEdiTrdPtnrRefCd = null;
        String poInbdIntfcId = null;

        DS_IMPT_EXT_ATTRBTMsg extAttrbTMsg = null;
        DS_IMPT_DTL_EXT_ATTRBTMsg extAttrbDtlTMsg = null;
        List<DS_IMPT_DTL_EXT_ATTRBTMsg> extdtlAttbTMsgList = new ArrayList<DS_IMPT_DTL_EXT_ATTRBTMsg>();
        BigDecimal dsImptOrdPk = null;
        Map<?, ?> dtlTopMap = null;
        Map<?, ?> dtlMap;
        if (editAttbMapList.size() > 0) {
            dtlTopMap = (Map<?, ?>) editAttbMapList.get(0);
            dsImptOrdPk = (BigDecimal) (dtlTopMap.get("DS_IMPT_ORD_PK"));
            dsEdiTrdPtnrRefCd = (String) (dtlTopMap.get("DS_EDI_TRD_PTNR_REF_CD"));
            poInbdIntfcId = (String) (dtlTopMap.get("PO_INBD_INTFC_ID"));

            extAttrbTMsg = deriveDsImptExtAttrb(bizMsg, dsImptOrdPk, errList);

            for (int i = 0; i < editAttbMapList.size(); i++) {
                dtlMap = (Map<?, ?>) editAttbMapList.get(i);
                extAttrbDtlTMsg = deriveDsImptDtlExtAttrb(bizMsg, dsImptOrdPk, (BigDecimal) dtlMap.get("DS_IMPT_ORD_DTL_PK"), errList);
                extdtlAttbTMsgList.add(extAttrbDtlTMsg);
            }
        }

        // *****************************************************************
        // derive Default DS_ORD_CATG_CD
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
            deriveDefDsOrdCatgCdForEdi(bizMsg, extAttrbTMsg, extdtlAttbTMsgList, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);
        }

        // *****************************************************************
        // derive Default Sold To
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            deriveDefSoldToForEdi(bizMsg, extAttrbTMsg, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);
        }

        // *****************************************************************
        // derive Default Bill To
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
            deriveDefBillToForEdi(bizMsg, extAttrbTMsg, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);
        }

        // *****************************************************************
        // derive Default Ship To
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) {
            deriveDefShipToForEdi(bizMsg, extAttrbTMsg, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);
        }

        // *****************************************************************
        // derive Default FRT_COND_CD
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondCd)) {
            deriveDefFrtCondCdForEdi(bizMsg, extAttrbTMsg, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);
        }

        // *****************************************************************
        // derive Default CARR_SVC_LVL_CD / SHPG_SVC_LVL_CD
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
            deriveDefSvcLvlCdForEdi(bizMsg, extAttrbTMsg, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);
        }

        // *****************************************************************
        // derive Default ADD_PMT_TERM_CASH_DISC_CD
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.addPmtTermCashDiscCd)) {
            deriveDefAddPmtTermCashDiscCdForEdi(bizMsg, extAttrbTMsg, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);
        }

        // *****************************************************************
        // derive Default CARR_ACCT_NUM
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.carrAcctNum) && FRT_COND.COLLECT.equals(bizMsg.frtCondCd)) {
            deriveDefCarrAcctNumForEdi(bizMsg, extAttrbTMsg, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);
        }

        // *****************************************************************
        // derive Default DROP_SHIP_FLG
        // *****************************************************************
        deriveDefDropShipFlgForEdi(bizMsg, extAttrbTMsg, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);

        // *****************************************************************
        // derive Customer PO Duplication Check
        // *****************************************************************
        custPoDupCheckForEdi(bizMsg, extAttrbTMsg, dsEdiTrdPtnrRefCd, poInbdIntfcId, errList);

        // *****************************************************************
        // derive Default Line bussiness Type Code
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.lineBizTpCd)) {
            deriveLineBizTpCdForEdi(bizMsg, errList);
        }

        // *****************************************************************
        // derive Default Price Ctegory Code
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgCd)) {
            derivePrcCatgCdForEdi(bizMsg, errList);
        }

        // *****************************************************************
        // derive config
        // *****************************************************************
        deriveConfig(bizMsg);

        NWAL2200_BCMsg dtlBcMsg;
        for (DS_IMPT_DTL_EXT_ATTRBTMsg extdtlAttbTMsg : extdtlAttbTMsgList) {
            dtlBcMsg = findDtlMsg(bizMsg, extdtlAttbTMsg.dsImptOrdDtlPk.getValue());
            if (dtlBcMsg == null) {
                continue; // S21_NA#15560
            }

            // *********************************************************
            // Derive MDSE Code For EDI
            // *********************************************************
            if (!ZYPCommonFunc.hasValue(dtlBcMsg.mdseCd_LL)) {
                if (!deriveMdseCdForEdi(bizMsg, dtlBcMsg, extdtlAttbTMsg, errList)) {
                    continue; // S21_NA#15560
                }
            }

            // *********************************************************
            // Derive CUST_UOM_CD
            // *********************************************************
            if (!ZYPCommonFunc.hasValue(dtlBcMsg.custUomCd_LL)) {
                if (!deriveCustUomCdForEdi(bizMsg, dtlBcMsg, extdtlAttbTMsg, poInbdIntfcId, extAttrbTMsg.ediTrdPtnrCd.getValue(), errList)) {
                    continue; // S21_NA#15560
                }
            }

            // *********************************************************
            // Derive Order Quantity
            // *********************************************************
            if (!ZYPCommonFunc.hasValue(dtlBcMsg.ordQty_LL)) {
                deriveOrdQtyForEdi(bizMsg, dtlBcMsg, extdtlAttbTMsg, errList);
            }

        }
        return;
    }

    private static void deriveConfig(NWAL2200CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            NWAL2200_ACMsg config = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(config.billToCustLocCd_LC)) {

                ZYPEZDItemValueSetter.setValue(config.billToCustAcctCd_LC, bizMsg.billToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(config.billToCustLocCd_LC, bizMsg.billToCustCd);
            }
            if (!ZYPCommonFunc.hasValue(config.shipToCustLocCd_LC)) {

                ZYPEZDItemValueSetter.setValue(config.shipToCustAcctCd_LC, bizMsg.shipToCustAcctCd);
                ZYPEZDItemValueSetter.setValue(config.shipToCustLocCd_LC, bizMsg.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(config.dropShipFlg_LC, bizMsg.dropShipFlg);
                ZYPEZDItemValueSetter.setValue(config.shipToLocNm_LC, bizMsg.shipToLocNm);
                ZYPEZDItemValueSetter.setValue(config.shipToAddlLocNm_LC, bizMsg.shipToAddlLocNm);
                ZYPEZDItemValueSetter.setValue(config.shipToFirstLineAddr_LC, bizMsg.shipToFirstLineAddr);
                ZYPEZDItemValueSetter.setValue(config.shipToScdLineAddr_LC, bizMsg.shipToScdLineAddr);
                ZYPEZDItemValueSetter.setValue(config.shipToThirdLineAddr_LC, bizMsg.shipToThirdLineAddr);
                ZYPEZDItemValueSetter.setValue(config.shipToFrthLineAddr_LC, bizMsg.shipToFrthLineAddr);
                ZYPEZDItemValueSetter.setValue(config.shipToFirstRefCmntTxt_LC, bizMsg.shipTo01RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(config.shipToScdRefCmntTxt_LC, bizMsg.shipTo02RefCmntTxt);
                ZYPEZDItemValueSetter.setValue(config.shipToCtyAddr_LC, bizMsg.shipToCtyAddr);
                ZYPEZDItemValueSetter.setValue(config.shipToStCd_LC, bizMsg.shipToStCd);
                ZYPEZDItemValueSetter.setValue(config.shipToProvNm_LC, bizMsg.shipToProvNm);
                ZYPEZDItemValueSetter.setValue(config.shipToCntyNm_LC, bizMsg.shipToCntyNm);
                ZYPEZDItemValueSetter.setValue(config.shipToPostCd_LC, bizMsg.shipToPostCd);
                ZYPEZDItemValueSetter.setValue(config.shipToCtryCd_LC, bizMsg.shipToCtryCd);
            }
            for (int j = 0; j < bizMsg.B.getValidCount(); j++) {

                NWAL2200_BCMsg line = bizMsg.B.no(j);

                if (S21StringUtil.isEquals(config.dsOrdPosnNum_LC.getValue(), line.dsOrdPosnNum_LL.getValue())) {

                    ZYPEZDItemValueSetter.setValue(line.dropShipFlg_LL, config.dropShipFlg_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToCustCd_LL, config.shipToCustLocCd_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToCustCd_LL, config.shipToCustLocCd_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToLocNm_LL, config.shipToLocNm_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToAddlLocNm_LL, config.shipToAddlLocNm_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToFirstLineAddr_LL, config.shipToFirstLineAddr_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToScdLineAddr_LL, config.shipToScdLineAddr_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToThirdLineAddr_LL, config.shipToThirdLineAddr_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToFrthLineAddr_LL, config.shipToFrthLineAddr_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToFirstRefCmntTxt_LL, config.shipToFirstRefCmntTxt_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToScdRefCmntTxt_LL, config.shipToScdRefCmntTxt_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToCtyAddr_LL, config.shipToCtyAddr_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToStCd_LL, config.shipToStCd_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToProvNm_LL, config.shipToProvNm_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToCntyNm_LL, config.shipToCntyNm_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToPostCd_LL, config.shipToPostCd_LC);
                    ZYPEZDItemValueSetter.setValue(line.shipToCtryCd_LL, config.shipToCtryCd_LC);

                    ZYPEZDItemValueSetter.setValue(line.prcCatgCd_LL, bizMsg.prcCatgCd);
                    ZYPEZDItemValueSetter.setValue(line.flPrcListCd_LL, bizMsg.flPrcListCd);
                    ZYPEZDItemValueSetter.setValue(line.rddDt_LL, bizMsg.rddDt);
                    ZYPEZDItemValueSetter.setValue(line.slsRepOrSlsTeamTocCd_LL, bizMsg.slsRepTocCd);
                }
            }
        }

    }

    private static List<?> deriveEdiAttrb(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {
        NWXC220001Result<List<?>> result = NWXC220001ImportAttribute.deriveEdiAttrb(bizMsg.glblCmpyCd.getValue(), bizMsg.dsImptOrdPk.getValue());

        if (result.hasError()) {
            NWXC220001ErrorInfo errorInfo = result.getErrInfoList().get(0);
            errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
        }

        return result.getResultObject();
    }

    private static DS_IMPT_EXT_ATTRBTMsg deriveDsImptExtAttrb(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdPk, List<DS_IMPT_ORD_ERRTMsg> errList) {
        DS_IMPT_EXT_ATTRBTMsg tMsg = new DS_IMPT_EXT_ATTRBTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, dsImptOrdPk);

        tMsg = (DS_IMPT_EXT_ATTRBTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            errList.add(createErr(bizMsg, dsImptOrdPk, NWAL2200Constant.NWAM0809E, "DS_IMPT_EXT_ATTR"));
        }

        return tMsg;
    }

    private static DS_IMPT_DTL_EXT_ATTRBTMsg deriveDsImptDtlExtAttrb(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdDtlPk, List<DS_IMPT_ORD_ERRTMsg> errList) {
        DS_IMPT_DTL_EXT_ATTRBTMsg tMsg = new DS_IMPT_DTL_EXT_ATTRBTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdPk, dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(tMsg.dsImptOrdDtlPk, dsImptOrdDtlPk);

        tMsg = (DS_IMPT_DTL_EXT_ATTRBTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            errList.add(createErr(bizMsg, dsImptOrdPk, null, dsImptOrdDtlPk, null, NWAL2200Constant.NWAM0809E, "DS_IMPT_DTL_EXT_ATTRB"));
        }

        return tMsg;
    }

    private static boolean deriveDefDsOrdCatgCdForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, List<DS_IMPT_DTL_EXT_ATTRBTMsg> extdtlAttbTMsgList, String dsEdiTrdPtnrRefCd, String poInbdIntfcId,
            List<DS_IMPT_ORD_ERRTMsg> errList) {

        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);

        if (NWXC220001ImportAttribute.isContentsInArcMgJpmc(dsEdiTrdPtnrRefCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.ORDER_TYPE_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, attrbTMsg.idocPoOrgValTxt_01);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, attrbTMsg.idocPoOrdRsnCd);
        } else if (NWXC220001ImportAttribute.isContentsInNcr(dsEdiTrdPtnrRefCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.NCR_ORDER_TYPE_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, extdtlAttbTMsgList.get(0).idocPoDtlDelyPrtyNm);
        }

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg);

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, resultTMsg.trgtAttrbTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, resultTMsg.trgtAttrbTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnCd, resultTMsg.trgtAttrbTxt_04);

        if (NWXC220001ImportAttribute.isContentsInNcr(dsEdiTrdPtnrRefCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, resultTMsg.trgtAttrbTxt_05);
            ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, resultTMsg.trgtAttrbTxt_06);
            ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlCd, resultTMsg.trgtAttrbTxt_07);
        }

        return true;
    }

    private static boolean deriveDefSoldToForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, String dsEdiTrdPtnrRefCd, String poInbdIntfcId, List<DS_IMPT_ORD_ERRTMsg> errList) {
        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        String srcAttrb3 = "";
        String srcAttrb4 = "";

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.CUSTOMER_MAPPING);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_SOLD_TO_ATTRB2);

        if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_ARC.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
            srcAttrb4 = attrbTMsg.idocPoPtnrNum_01.getValue();
        } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_IHREZ;
            srcAttrb4 = attrbTMsg.idocPtnrCustRefTxt_01.getValue();
        } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
            srcAttrb4 = attrbTMsg.idocPoPtnrId_01.getValue();
        } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_NCR.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
            srcAttrb4 = attrbTMsg.idocPoPtnrId_01.getValue();
        }

        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, srcAttrb3);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, srcAttrb4);

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg, "S21 Sold To");

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();

        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, resultTMsg.trgtAttrbTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, resultTMsg.trgtAttrbTxt_02);

        return true;
    }

    private static boolean deriveDefBillToForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, String dsEdiTrdPtnrRefCd, String poInbdIntfcId, List<DS_IMPT_ORD_ERRTMsg> errList) {
        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        // 09/15/2016 S21_NA#14261 add Start
        String srcAttrb2 = "";
        // 09/15/2016 S21_NA#14261 add End
        String srcAttrb3 = "";
        String srcAttrb4 = "";

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.CUSTOMER_MAPPING);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
        // 09/15/2016 S21_NA#14261 del Start
        // ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02,
        // NWXC220001Constant.EDI_BILL_TO_ATTRB2);
        // 09/15/2016 S21_NA#14261 del End

        // 09/15/2016 S21_NA#14261 mod Start
        if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_ARC.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb2 = NWXC220001Constant.EDI_BILL_TO_ATTRB2;
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
            srcAttrb4 = attrbTMsg.idocPoPtnrNum_02.getValue();
        } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb2 = NWXC220001Constant.EDI_SOLD_TO_ATTRB2;
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_IHREZ;
            srcAttrb4 = attrbTMsg.idocPtnrCustRefTxt_01.getValue();
        } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb2 = NWXC220001Constant.EDI_SOLD_TO_ATTRB2;
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
            srcAttrb4 = attrbTMsg.idocPoPtnrId_01.getValue();
        } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_NCR.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb2 = NWXC220001Constant.EDI_BILL_TO_ATTRB2;
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
            srcAttrb4 = attrbTMsg.idocPoPtnrId_02.getValue();
        }
        // 09/15/2016 S21_NA#14261 mod End

        // 09/15/2016 S21_NA#14261 add Start
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, srcAttrb2);
        // 09/15/2016 S21_NA#14261 add End
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, srcAttrb3);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, srcAttrb4);

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg, "S21 Bill To");

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();

        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, resultTMsg.trgtAttrbTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, resultTMsg.trgtAttrbTxt_04);

        return true;
    }

    private static boolean deriveDefShipToForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, String dsEdiTrdPtnrRefCd, String poInbdIntfcId, List<DS_IMPT_ORD_ERRTMsg> errList) {
        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        String srcAttrb3 = "";
        String srcAttrb4 = "";

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.CUSTOMER_MAPPING);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_SHIP_TO_ATTRB2);

        if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_ARC.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
            srcAttrb4 = attrbTMsg.idocPoPtnrNum_03.getValue();
        } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_MG.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
            srcAttrb4 = attrbTMsg.idocPoPtnrNum_03.getValue();
        } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_JPMC.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_LIFNR;
            srcAttrb4 = attrbTMsg.idocPoPtnrId_03.getValue();
        } else if (NWXC220001Constant.DS_EDI_TRD_PTNR_REF_CD_NCR.equals(dsEdiTrdPtnrRefCd)) {
            srcAttrb3 = NWXC220001Constant.EDI_CUST_ATTRB3_PARTN;
            srcAttrb4 = attrbTMsg.idocPoPtnrNum_03.getValue();
        }

        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, srcAttrb3);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, srcAttrb4);

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg, "S21 Ship To");

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, resultTMsg.trgtAttrbTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, resultTMsg.trgtAttrbTxt_06);

        return true;
    }

    private static boolean deriveDefFrtCondCdForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, String dsEdiTrdPtnrRefCd, String poInbdIntfcId, List<DS_IMPT_ORD_ERRTMsg> errList) {
        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.FREIGHT_MAPPING);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_FRT_COND_CD_ATTRB2);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_FRT_COND_CD_ATTRB3);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoDelyCondCd);

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg);

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, resultTMsg.trgtAttrbTxt_01);

        return true;
    }

    private static boolean deriveDefSvcLvlCdForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, String dsEdiTrdPtnrRefCd, String poInbdIntfcId, List<DS_IMPT_ORD_ERRTMsg> errList) {
        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        if (NWXC220001ImportAttribute.isContentsInArcMgJpmc(dsEdiTrdPtnrRefCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.SHIPPING_SERVICE_LEVEL_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_SVC_LVL_CD_ATTRB2);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_CUST_ATTRB3_PARTN);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoPtnrNum_04);
        } else if (NWXC220001ImportAttribute.isContentsInNcr(dsEdiTrdPtnrRefCd)) {
            return true;
        }

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefSvcLvlCd(inTMsg);

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
        if (resultTMsg != null) {

            ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, resultTMsg.trgtAttrbTxt_01);
            // 09/12/2016 S21_NA#14278 MOD START
            if (S21StringUtil.isEquals(bizMsg.frtCondCd.getValue(), FRT_COND.COLLECT)) {

                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlCd, resultTMsg.trgtAttrbTxt_02);
            } else {

                bizMsg.carrSvcLvlCd.clear();
            }
            // 09/12/2016 S21_NA#14278 MOD END
        }

        return true;
    }

    private static boolean deriveDefAddPmtTermCashDiscCdForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, String dsEdiTrdPtnrRefCd, String poInbdIntfcId, List<DS_IMPT_ORD_ERRTMsg> errList) {
        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        if (NWXC220001ImportAttribute.isContentsInArcMgJpmc(dsEdiTrdPtnrRefCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.SHIPPING_SERVICE_LEVEL_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_SVC_LVL_CD_ATTRB2);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_CUST_ATTRB3_PARTN);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoPtnrNum_04);
        } else if (NWXC220001ImportAttribute.isContentsInNcr(dsEdiTrdPtnrRefCd)) {
            return true;
        }

        NWXC220001Result<String> result = NWXC220001ImportAttribute.deriveDefAddPmtTermCashDiscCd(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustCd.getValue(), bizMsg.billToCustAcctCd.getValue());
        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        String addPmtTermCashDiscCd = result.getResultObject();

        if (ZYPCommonFunc.hasValue(addPmtTermCashDiscCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.addPmtTermCashDiscCd, result.getResultObject());
        }

        return true;
    }

    private static boolean deriveDefCarrAcctNumForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, String dsEdiTrdPtnrRefCd, String poInbdIntfcId, List<DS_IMPT_ORD_ERRTMsg> errList) {
        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        if (NWXC220001ImportAttribute.isContentsInArcMgJpmc(dsEdiTrdPtnrRefCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.NCR_CARRIER_ACCOUNT_NUMBER_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_CARR_ACCT_NUM_ATTRB2);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_CUST_ATTRB3_PARTN);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, attrbTMsg.idocPoPtnrNum_04);
        }

        NWXC220001Result<String> result = NWXC220001ImportAttribute.deriveDefCarrAcctNum(dsEdiTrdPtnrRefCd, inTMsg, attrbTMsg.idocPoNoteTxt.getValue(), bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.shipToCustAcctCd
        // 2018/12/13 S21_NA#29315 Add Start
//              .getValue(), ONBATCH_TYPE.ONLINE);
                .getValue(), ONBATCH_TYPE.ONLINE, null, null, null, null, null /* Dummy Value */);
        // 2018/12/13 S21_NA#29315 Add End
        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        String carrAcctNum = result.getResultObject();

        if (ZYPCommonFunc.hasValue(carrAcctNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.carrAcctNum, carrAcctNum);
        }

        return true;
    }

    private static boolean deriveDefDropShipFlgForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, String dsEdiTrdPtnrRefCd, String poInbdIntfcId, List<DS_IMPT_ORD_ERRTMsg> errList) {
        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        if (NWXC220001ImportAttribute.isContentsInNcrMgJpmc(dsEdiTrdPtnrRefCd)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
            ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.DROP_SHIP_MAPPING);
            ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);
        } else if (NWXC220001ImportAttribute.isContentsInArc(dsEdiTrdPtnrRefCd)) {

            if (NWXC220001Constant.EDI_DROP_SHIP_FLG_ARC_TXT.equalsIgnoreCase(attrbTMsg.idocPoOrgValTxt_01.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            }

            return true;
        }

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg);

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        XTRNL_INTFC_XREFTMsg resultTMsg = result.getResultObject();
        if (resultTMsg != null && ZYPCommonFunc.hasValue(resultTMsg.trgtAttrbTxt_01)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, resultTMsg.trgtAttrbTxt_01);
        }

        return true;
    }

    private static boolean custPoDupCheckForEdi(NWAL2200CMsg bizMsg, DS_IMPT_EXT_ATTRBTMsg attrbTMsg, String dsEdiTrdPtnrRefCd, String poInbdIntfcId, List<DS_IMPT_ORD_ERRTMsg> errList) {
        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.CUSTOMER_PO_DUPLICATION_CHECK);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, attrbTMsg.ediTrdPtnrCd);

        NWXC220001Result<String> result = NWXC220001ImportAttribute.checkDuplicatePo(inTMsg, bizMsg.origOrdNum.getValue(), attrbTMsg.idocPoCustRefNum.getValue(), ORD_HDR_STS.SAVED);

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        return true;
    }

    private static boolean deriveLineBizTpCdForEdi(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {
        DS_ORD_TP_PROC_DFNTMsg inTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);

        inTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(inTMsg);

        if (inTMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, inTMsg.lineBizTpCd);
            return true;
        }

        return false;
    }

    private static boolean derivePrcCatgCdForEdi(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_ON_Y);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        NWZC157001_xxMsgIdListPMsg errMsg;
        for (int i = 0; i < prcApiPMsg.xxMsgIdList.getValidCount(); i++) {
            errMsg = prcApiPMsg.xxMsgIdList.no(i);
            errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errMsg.xxMsgId.getValue(), toArray(errMsg.xxMsgPrmTxt_0.getValue(), errMsg.xxMsgPrmTxt_1.getValue(), errMsg.xxMsgPrmTxt_2.getValue())));
            return false;
        }

        // S21_NA#15077 modify start
        if (prcApiPMsg.xxPrcList.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, prcApiPMsg.xxPrcList.no(0).prcCatgCd);
        } else if (ZYPCommonFunc.hasValue(prcApiPMsg.defPrcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, prcApiPMsg.defPrcCatgCd);
        }
        // S21_NA#15077 modify end

        return ZYPCommonFunc.hasValue(bizMsg.prcCatgCd);
    }

    private static boolean deriveSlsReq(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {
        NMZC260001PMsg pMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, bizMsg.dsOrdTpCd); // S21_NA#15004

        // call NMZC2600 Dafault Sales Rep API
        new NMZC260001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        NMZC260001_xxMsgIdListPMsg errMsg;
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            errMsg = pMsg.xxMsgIdList.no(i);
            errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), errMsg.xxMsgId.getValue(), toArray(errMsg.xxMsgPrmTxt_0.getValue(), errMsg.xxMsgPrmTxt_1.getValue(), errMsg.xxMsgPrmTxt_2.getValue())));
            return false;
        }

        if (pMsg.defSlsRepList.getValidCount() > 0) {
            /** Line Biz Role Writter Type List */
            List<String> writterList = asList(LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER);

            NMZC260001_defSlsRepListPMsg slsRepPMsg;
            for (int i = 0; i < pMsg.defSlsRepList.getValidCount(); i++) {
                if (pMsg.defSlsRepList.no(i).lineBizTpCd.getValue().equals(bizMsg.lineBizTpCd.getValue())) {
                    slsRepPMsg = pMsg.defSlsRepList.no(i);
                    if (writterList.contains(slsRepPMsg.lineBizRoleTpCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, slsRepPMsg.tocCd);
                        break;
                    }
                }
            }
        }

        return true;
    }

    private static void deriveData(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {
        // *****************************************************************
        // derive Default DS_ORD_TP_PROC_DFN
        // *****************************************************************
        deriveDefDsOrdTpProcDfn(bizMsg);

        // *****************************************************************
        // derive Default Sales Rep
        // *****************************************************************
        if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocCd)) {
            deriveSlsReq(bizMsg, errList);
        }
    }

    private static void deriveDefDsOrdTpProcDfn(NWAL2200CMsg bizMsg) {
        DS_ORD_TP_PROC_DFNTMsg inTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        String ordSrcImptTs_len8 = bizMsg.ordSrcImptTs.getValue();

        if (ordSrcImptTs_len8.length() > 8) {
            ordSrcImptTs_len8 = ordSrcImptTs_len8.substring(0, 8);
        }
        ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, ordSrcImptTs_len8);
        ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, ordSrcImptTs_len8);

        NWXC220001Result<DS_ORD_TP_PROC_DFNTMsg> result = NWXC220001.getDefDsOrdTpProcDfn(inTMsg);

        if (result.hasResultObject()) {
            DS_ORD_TP_PROC_DFNTMsg resultTMsg = result.getResultObject();

            if (!ZYPCommonFunc.hasValue(bizMsg.prcCatgCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd, resultTMsg.defPrcCatgCd);
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.flPrcListCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.flPrcListCd, resultTMsg.defPrcCatgCd);
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.frtCondCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, resultTMsg.frtCondCd);
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, resultTMsg.defShpgSvcLvlCd);
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.carrSvcLvlCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlCd, resultTMsg.defCarrSvcLvlCd);
            }
        }
    }

    private static void deriveDataConfig(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {
        NWAL2200_ACMsg lineACMsg;
        NWAL2200_CCMsg lineCCMsg;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            lineACMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(lineACMsg.billToCustLocCd_LC)) {
                ZYPEZDItemValueSetter.setValue(lineACMsg.billToCustLocCd_LC, bizMsg.billToCustCd);
                ZYPEZDItemValueSetter.setValue(lineACMsg.billToCustAcctCd_LC, bizMsg.billToCustAcctCd);
            }

            if (!ZYPCommonFunc.hasValue(lineACMsg.shipToCustLocCd_LC)) {
                ZYPEZDItemValueSetter.setValue(lineACMsg.shipToCustLocCd_LC, bizMsg.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(lineACMsg.shipToCustAcctCd_LC, bizMsg.shipToCustAcctCd);
            }
        }

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            lineCCMsg = bizMsg.C.no(i);
            if (!ZYPCommonFunc.hasValue(lineCCMsg.billToCustLocCd_RC)) {
                ZYPEZDItemValueSetter.setValue(lineCCMsg.billToCustLocCd_RC, bizMsg.billToCustCd);
                ZYPEZDItemValueSetter.setValue(lineCCMsg.billToCustAcctCd_RC, bizMsg.billToCustAcctCd);
            }

            if (!ZYPCommonFunc.hasValue(lineCCMsg.shipToCustLocCd_RC)) {
                ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToCustLocCd_RC, bizMsg.shipToCustCd);
                ZYPEZDItemValueSetter.setValue(lineCCMsg.shipToCustAcctCd_RC, bizMsg.shipToCustAcctCd);
            }
        }

    }

    private static void deriveDataDetail(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {

        NWAL2200_BCMsg lineBCMsg;
        NWAL2200_DCMsg lineDCMsg;

        // *****************************************************************
        // derive Default DS_ORD_LINE_CATG_CD
        // *****************************************************************
        String dsordLineCatgCd = deriveDsOrdLineCatgCd(bizMsg, DS_ORD_LINE_DRCTN.OUTBOUND, errList);

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            lineBCMsg = bizMsg.B.no(i);

            // S21_NA#11561 add start
            if (!S21StringUtil.isEquals(lineBCMsg.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                // skip excluded import line.
                continue;
            }
            // S21_NA#11561 add end
            if (!ZYPCommonFunc.hasValue(lineBCMsg.dsOrdLineCatgCd_LL)) {
                ZYPEZDItemValueSetter.setValue(lineBCMsg.dsOrdLineCatgCd_LL, dsordLineCatgCd);
            }
        }

        dsordLineCatgCd = deriveDsOrdLineCatgCd(bizMsg, DS_ORD_LINE_DRCTN.INBOUND, errList);
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            lineDCMsg = bizMsg.D.no(i);
            if (!ZYPCommonFunc.hasValue(lineDCMsg.dsCpoLineCatgCd_RL)) {
                ZYPEZDItemValueSetter.setValue(lineDCMsg.dsCpoLineCatgCd_RL, dsordLineCatgCd);
            }
        }
    }

    private static String deriveDsOrdLineCatgCd(NWAL2200CMsg bizMsg, String dsOrdLineDrctn, List<DS_IMPT_ORD_ERRTMsg> errList) {
        NWXC220001Result<Map<String, Object>> result = NWXC220001.getPrimDsOrdLineCatg(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdTpCd.getValue(), bizMsg.slsDt.getValue(), dsOrdLineDrctn);

        if (!result.hasResultObject()) {
            return null;
        }

        return (String) result.getResultObject().get("DS_ORD_LINE_CATG_CD");
    }

    private static <T> T[] toArray(T... param) {
        return param;
    }

    private static NWAL2200_BCMsg findDtlMsg(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdDtlPk) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (bizMsg.B.no(i).dsImptOrdDtlPk_LL.getValue().equals(dsImptOrdDtlPk)) {
                return bizMsg.B.no(i);
            }
        }

        return null;
    }

    private static boolean deriveMdseCdForEdi(NWAL2200CMsg bizMsg, NWAL2200_BCMsg dtlBcMsg, DS_IMPT_DTL_EXT_ATTRBTMsg extdtlAttbTMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {
        // Mod Start 2017/10/25 QC#22012
        NWXC220001Result<Map<String, String>> result = NWXC220001ImportAttribute.deriveDefMdse(bizMsg.glblCmpyCd.getValue(), bizMsg.sellToCustCd.getValue(), extdtlAttbTMsg.idocPoDtlMatNum_01.getValue(), extdtlAttbTMsg.idocPoDtlMatNum_02.getValue()); // S21_NA#14815
        // Mod End 2017/10/25 QC#22012

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, dtlBcMsg.dsImptOrdPk_LL.getValue(), dtlBcMsg.dsImptOrdConfigPk_LL.getValue(), dtlBcMsg.dsImptOrdDtlPk_LL.getValue(), null, errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        ZYPEZDItemValueSetter.setValue(dtlBcMsg.mdseCd_LL, result.getResultObject().get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(dtlBcMsg.mdseNm_LL, result.getResultObject().get("MDSE_NM"));

        return true;
    }

    private static boolean deriveCustUomCdForEdi(NWAL2200CMsg bizMsg, NWAL2200_BCMsg dtlBcMsg, DS_IMPT_DTL_EXT_ATTRBTMsg extdtlAttbTMsg, String poInbdIntfcId, String ediTrdPtnrCd, List<DS_IMPT_ORD_ERRTMsg> errList) {

        XTRNL_INTFC_XREFTMsg inTMsg = new XTRNL_INTFC_XREFTMsg();

        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcId, poInbdIntfcId);
        ZYPEZDItemValueSetter.setValue(inTMsg.intfcXrefCtxTpCd, INTFC_XREF_CTX_TP.UOM_MAPPING);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_01, ediTrdPtnrCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_02, NWXC220001Constant.EDI_CUST_UOM_CD_ATTRB2);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_03, NWXC220001Constant.EDI_CUST_UOM_CD_ATTRB3);
        ZYPEZDItemValueSetter.setValue(inTMsg.srcAttrbTxt_04, extdtlAttbTMsg.idocPoDtlUomCd);

        NWXC220001Result<XTRNL_INTFC_XREFTMsg> result = NWXC220001ImportAttribute.deriveDefCommonAttrb(inTMsg);

        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, dtlBcMsg.dsImptOrdPk_LL.getValue(), dtlBcMsg.dsImptOrdConfigPk_LL.getValue(), dtlBcMsg.dsImptOrdDtlPk_LL.getValue(), null, errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }

        ZYPEZDItemValueSetter.setValue(dtlBcMsg.custUomCd_LL, result.getResultObject().trgtAttrbTxt_01);

        return true;
    }

    private static boolean deriveOrdQtyForEdi(NWAL2200CMsg bizMsg, NWAL2200_BCMsg dtlBcMsg, DS_IMPT_DTL_EXT_ATTRBTMsg extdtlAttbTMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {

        NWXC220001Result<Map<String, Object>> result = NWXC220001.getQtyInfoForEdi(bizMsg.glblCmpyCd.getValue(), dtlBcMsg.custUomCd_LL.getValue(), dtlBcMsg.mdseCd_LL.getValue(), extdtlAttbTMsg.idocPoDtlOrdQty.getValue());

        // 09/28/2016 S21_NA#14744 mod Start
        // if (!result.hasResultObject()) {
        // return false;
        // }
        if (result.hasError()) {
            for (NWXC220001ErrorInfo errorInfo : result.getErrInfoList()) {
                errList.add(createErr(bizMsg, dtlBcMsg.dsImptOrdPk_LL.getValue(), dtlBcMsg.dsImptOrdConfigPk_LL.getValue(), dtlBcMsg.dsImptOrdDtlPk_LL.getValue(), null, errorInfo.getErrMsgId(), errorInfo.getParamArray()));
            }
            return false;
        }
        // 09/28/2016 S21_NA#14744 mod End

        Map<String, Object> resultObjectMap = result.getResultObject();

        ZYPEZDItemValueSetter.setValue(dtlBcMsg.ordCustUomQty_LL, NWXC220001Util.convToBigDecimal(resultObjectMap.get("ORD_CUST_UOM_QTY")));
        ZYPEZDItemValueSetter.setValue(dtlBcMsg.ordQty_LL, NWXC220001Util.convToBigDecimal(resultObjectMap.get("ORD_QTY")));

        return true;
    }

    private static void checkMasterForHeader(NWAL2200CMsg bizMsg, NWAL2200CMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!NWXC150001DsCheck.existsDsOrdCatg(bizMsg.glblCmpyCd.getValue(), source.dsOrdCatgCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1415E));

        }
        if (!NWXC150001DsCheck.existsAccount(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.billToCustAcctCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1416E));

        }
        if (!NWXC150001DsCheck.existsAccount(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.shipToCustAcctCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1417E));

        }
        if (!NWXC150001DsCheck.existsSoldToLocation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.soldToCustLocCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1418E));

        }
        if (!NWXC150001DsCheck.existsSalesRep(bizMsg.glblCmpyCd.getValue(), source.slsRepTocCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWAM0054E));

        }
        if (!NWXC150001DsCheck.existsPrcCatg(bizMsg.glblCmpyCd.getValue(), source.prcCatgCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1419E));

        }
        if (!NWXC150001DsCheck.existsFlPrcList(bizMsg.glblCmpyCd.getValue(), source.flPrcListCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1420E));

        }
        if (!NWXC150001DsCheck.existsAssnPgm(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.prcContrNum.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1421E));

        }
        if (!NWXC150001DsCheck.existsLeasePrchOpt(bizMsg.glblCmpyCd.getValue(), source.leasePrchOptCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1422E));

        }
        if (!NWXC150001DsCheck.existsLeaseTerm(bizMsg.glblCmpyCd.getValue(), source.leaseTermCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1423E));

        }
        if (!NWXC150001DsCheck.existsLeasePmtFreq(bizMsg.glblCmpyCd.getValue(), source.leasePmtFreqCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1424E));

        }
        if (!NWXC150001DsCheck.existsOrdLogTp(bizMsg.glblCmpyCd.getValue(), source.ordLogTpCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1425E));

        }
        if (!NWXC150001DsCheck.existsFrtCond(bizMsg.glblCmpyCd.getValue(), source.frtCondCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1426E));

        }
        if (!NWXC150001DsCheck.existsCarrSvcLvl(bizMsg.glblCmpyCd.getValue(), source.carrSvcLvlCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1427E));

        }
        if (!NWXC150001DsCheck.existsSpclHdlgTp(bizMsg.glblCmpyCd.getValue(), source.spclHdlgTpCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1428E));

        }
        if (!NWXC150001DsCheck.existsPrePmtTp(bizMsg.glblCmpyCd.getValue(), source.prePmtTpCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1429E));

        }
        if (!NWXC150001DsCheck.existsCrRebilRsnCatg(bizMsg.glblCmpyCd.getValue(), source.crRebilRsnCatgCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1430E));

        }
    }

    private static void checkEssentialForConfig(NWAL2200CMsg bizMsg, NWAL2200_ACMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!ZYPCommonFunc.hasValue(source.dsOrdPosnNum_LC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1408E));
        }
        if (!ZYPCommonFunc.hasValue(source.configCatgCd_LC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1409E));
        }
        if (!ZYPCommonFunc.hasValue(source.configTpCd_LC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1410E));
        }
        if (!ZYPCommonFunc.hasValue(source.billToCustAcctCd_LC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1377E));
        }
        if (!ZYPCommonFunc.hasValue(source.billToCustLocCd_LC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM0617E));
        }
        if (!ZYPCommonFunc.hasValue(source.shipToCustAcctCd_LC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1379E));
        }
        if (!ZYPCommonFunc.hasValue(source.shipToCustLocCd_LC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM0619E));
        }
    }

    private static void checkMasterForConfig(NWAL2200CMsg bizMsg, NWAL2200_ACMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!NWXC150001DsCheck.existsConfigCatg(bizMsg.glblCmpyCd.getValue(), source.configCatgCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1440E));
        }
        if (!NWXC150001DsCheck.existsConfigTp(bizMsg.glblCmpyCd.getValue(), source.configTpCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1441E));
        }
        // Out bound Not Y N N
        // if
        // (!NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(),
        // source.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND,
        // true, false, false)) {
        // S21_NA#14922
        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), source.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, true)) { // S21_NA#11297
            if (!NWXC150001DsCheck.existsSvcConfigMstr(bizMsg.glblCmpyCd.getValue(), source.svcConfigMstrPk_LC.getValue())) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1442E));
            }
        }
        if (!NWXC150001DsCheck.existsMdlId(bizMsg.glblCmpyCd.getValue(), source.mdlId_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1443E));

        }
        if (!NWXC150001DsCheck.existsAccount(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.billToCustAcctCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1416E));

        }
        if (!NWXC150001DsCheck.existsBillToLocation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.billToCustLocCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1444E));

        }
        if (!NWXC150001DsCheck.existsAccount(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.shipToCustAcctCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1417E));

        }
        if (!NWXC150001DsCheck.existsShipToLocation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.shipToCustLocCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1445E));

        }
        if (!NWXC150001DsCheck.existsState(bizMsg.glblCmpyCd.getValue(), source.shipToStCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1446E));

        }
        if (!NWXC150001DsCheck.existsCtry(bizMsg.glblCmpyCd.getValue(), source.shipToCtryCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1448E));

        }

        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), source.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) {
            if (!ZYPCommonFunc.hasValue(source.svcConfigMstrPk_LC)) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1887E));
            } else {
                if (!SVC_MACH_USG_STS.AT_CUSTOMER.equals(NWXC150001DsCheck.getMachineUsageStatus(bizMsg.glblCmpyCd.getValue(), source.svcConfigMstrPk_LC.getValue()))) {
                    errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1887E));
                }
            }
        }
        // QC#24245 2018/06/13 Add Start
        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), source.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, false, true)) {
            if (!ZYPCommonFunc.hasValue(source.svcConfigMstrPk_LC)) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM2266E));
            } else {
                if (!SVC_MACH_USG_STS.IN_INVENTORY.equals(NWXC150001DsCheck.getMachineUsageStatus(bizMsg.glblCmpyCd.getValue(), source.svcConfigMstrPk_LC.getValue()))) {
                    errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM2266E));
                }
            }
        }
        // QC#24245 2018/06/13 Add End
    }

    private static void checkEssentialForConfig(NWAL2200CMsg bizMsg, NWAL2200_CCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!ZYPCommonFunc.hasValue(source.dsOrdPosnNum_RC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1408E));
        }
        if (!ZYPCommonFunc.hasValue(source.configCatgCd_RC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1409E));
        }
        if (!ZYPCommonFunc.hasValue(source.configTpCd_RC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1410E));
        }
        if (!ZYPCommonFunc.hasValue(source.billToCustAcctCd_RC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1377E));
        }
        if (!ZYPCommonFunc.hasValue(source.billToCustLocCd_RC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM0617E));
        }
        if (!ZYPCommonFunc.hasValue(source.shipToCustAcctCd_RC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1379E));
        }
        if (!ZYPCommonFunc.hasValue(source.shipToCustLocCd_RC)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM0619E));
        }
    }

    private static void checkMasterForConfig(NWAL2200CMsg bizMsg, NWAL2200_CCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!NWXC150001DsCheck.existsConfigCatg(bizMsg.glblCmpyCd.getValue(), source.configCatgCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1440E));
        }
        if (!NWXC150001DsCheck.existsConfigTp(bizMsg.glblCmpyCd.getValue(), source.configTpCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1441E));
        }
        // Out bound Not Y N N
        // if
        // (!NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(),
        // source.configTpCd_RC.getValue(), CONFIG_CATG.OUTBOUND,
        // true, false, false)) {
        // if
        // (!NWXC150001DsCheck.existsSvcConfigMstr(bizMsg.glblCmpyCd.getValue(),
        // source.svcConfigMstrPk_RC.getValue())) {
        // errList.add(createErr(bizMsg,
        // source.dsImptOrdPk_RC.getValue(),
        // source.dsImptOrdConfigPk_RC.getValue(),
        // NWZC150001Constant.NWZM1442E));
        // }
        // }
        if (!NWXC150001DsCheck.existsMdlId(bizMsg.glblCmpyCd.getValue(), source.mdlId_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1443E));
        }
        if (!NWXC150001DsCheck.existsAccount(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.billToCustAcctCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1416E));
        }
        if (!NWXC150001DsCheck.existsBillToLocation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.billToCustLocCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1444E));
        }
        if (!NWXC150001DsCheck.existsAccount(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.shipToCustAcctCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1417E));
        }
        if (!NWXC150001DsCheck.existsShipToLocation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.shipToCustLocCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1445E));
        }
        if (!NWXC150001DsCheck.existsState(bizMsg.glblCmpyCd.getValue(), source.shipToStCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1446E));
        }
        if (!NWXC150001DsCheck.existsCtry(bizMsg.glblCmpyCd.getValue(), source.shipToCtryCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1448E));
        }

        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), source.configTpCd_RC.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) {
            if (!SVC_MACH_USG_STS.AT_CUSTOMER.equals(NWXC150001DsCheck.getMachineUsageStatus(bizMsg.glblCmpyCd.getValue(), source.svcConfigMstrPk_RC.getValue()))) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1887E));

            }
        }
    }

    private static void checkEssentialForDetail(NWAL2200CMsg bizMsg, NWAL2200_BCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!ZYPCommonFunc.hasValue(source.dsOrdLineCatgCd_LL)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1413E));
        }
        if (!ZYPCommonFunc.hasValue(source.baseCmptFlg_LL)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1414E));
        }
    }

    private static void checkMasterForDetail(NWAL2200CMsg bizMsg, NWAL2200CMsg order, NWAL2200_BCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!NWXC150001DsCheck.existsOrdLineSrc(bizMsg.glblCmpyCd.getValue(), source.ordLineSrcCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1432E));

        }
        if (!NWXC150001DsCheck.existsRtlWh(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.rtlWhCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1433E));

        }
        if (!NWXC150001DsCheck.existsRtlSubWh(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.rtlWhCd_LL.getValue(), source.rtlSwhCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1434E));

        }
        if (!NWXC150001DsCheck.existsFlPrcList(bizMsg.glblCmpyCd.getValue(), source.flPrcListCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1420E));

        }
        if (!NWXC150001DsCheck.existsCrRebil(bizMsg.glblCmpyCd.getValue(), source.crRebilCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1435E));

        }
        if (!NWXC150001DsCheck.existsSplyTp(bizMsg.glblCmpyCd.getValue(), source.splyTpCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1436E));

        }
        if (!NWXC150001DsCheck.existsBilgAttrCustAcct(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), order.sellToCustCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1438E));

        }
        if (!NWXC150001DsCheck.existsSbstItem(bizMsg.glblCmpyCd.getValue(), source.sbstMdseCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1439E));

        }
        // move to header
        // if
        // (!NWXC150001DsCheck.existsCarrSvcLvl(bizMsg.glblCmpyCd.getValue(),
        // source.carrSvcLvlCd_LL.getValue())) {
        // errList.add(createErr(bizMsg,
        // source.dsImptOrdPk_LL.getValue(),
        // source.dsImptOrdConfigPk_LL.getValue(),
        // source.dsImptOrdDtlPk_LL.getValue(), null,
        // NWZC150001Constant.NWZM1427E));
        //            
        // }
        if (!NWXC150001DsCheck.existsPrcCatg(bizMsg.glblCmpyCd.getValue(), source.prcCatgCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1419E));

        }
    }

    private static void checkEssentialForReturn(NWAL2200CMsg bizMsg, NWAL2200_DCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!ZYPCommonFunc.hasValue(source.mdseCd_RL)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM0492E));
        }
        if (!ZYPCommonFunc.hasValue(source.shipToCustCd_RL)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM0507E));
        }
        if (!ZYPCommonFunc.hasValue(source.ordQty_RL) //
                && BigDecimal.ZERO.compareTo(source.ordQty_RL.getValue()) == 0) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1486E));
        }
    }

    private static void checkMasterForReturn(NWAL2200CMsg bizMsg, NWAL2200CMsg order, NWAL2200_DCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!NWXC150001DsCheck.existsRtlWh(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.rtlWhCd_RL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1433E));

        }
        if (!NWXC150001DsCheck.existsRtlSubWh(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.rtlWhCd_RL.getValue(), source.rtlSwhCd_RL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1434E));

        }
        if (!NWXC150001DsCheck.existsFlPrcList(bizMsg.glblCmpyCd.getValue(), source.flPrcListCd_RL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1420E));

        }
        if (!NWXC150001DsCheck.existsBilgAttrCustAcct(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), order.sellToCustCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1438E));

        }
        if (!NWXC150001DsCheck.existsPrcCatg(bizMsg.glblCmpyCd.getValue(), source.prcCatgCd_RL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1419E));

        }
        if (!NWXC150001DsCheck.existsHddRmv(bizMsg.glblCmpyCd.getValue(), source.hddRmvCd_RL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1593E));

        }
        if (!NWXC150001DsCheck.existsRtrnRsn(bizMsg.glblCmpyCd.getValue(), source.rtrnRsnCd_RL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1594E));

        }
        // get default WH in import batch.
        // if
        // (!NWXC150001DsCheck.existsThirdPtyDspTp(bizMsg.glblCmpyCd.getValue(),
        // source.thirdPtyDspTpCd_RL.getValue())) {
        // errList.add(createErr(bizMsg,
        // source.dsImptOrdPk_RL.getValue(),
        // source.dsImptOrdConfigPk_RL.getValue(), null,
        // source.dsImptOrdRtrnDtlPk_RL.getValue(),
        // NWZC150001Constant.NWZM1595E));
        //            
        // }
    }

    private static void otherCheck(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {

        otherHeaderCheck(bizMsg, bizMsg, errList);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2200_ACMsg config = bizMsg.A.no(i);
            otherConfigCheck(bizMsg, config, errList);
        }
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {

            NWAL2200_CCMsg config = bizMsg.C.no(i);
            otherConfigCheck(bizMsg, config, errList);
        }
        boolean isCustMdseXrefChk = ZYPConstant.FLG_ON_Y.equals(getCustMdseXrefFlg(bizMsg)); // QC#14815
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL2200_BCMsg line = bizMsg.B.no(i);

            // S21_NA#11561 add start
            if (!S21StringUtil.isEquals(line.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                // skip excluded import line.
                continue;
            }
            // S21_NA#11561 add end
            otherDetailCheck(bizMsg, bizMsg, line, errList, isCustMdseXrefChk); // QC#14815
        }
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {

            NWAL2200_DCMsg line = bizMsg.D.no(i);
            otherReturnDetailCheck(bizMsg, bizMsg, line, errList, isCustMdseXrefChk); // QC#14815
        }

        otherSvcExchCheck(bizMsg, errList);

        List<String> coaMdseTpList = getCoaMdseTpList(bizMsg.glblCmpyCd.getValue());
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL2200_BCMsg line = bizMsg.B.no(i);

            // S21_NA#11561 add start
            if (!S21StringUtil.isEquals(line.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                // skip excluded import line.
                continue;
            }
            // S21_NA#11561 add end
            otherQtyCheck(bizMsg, bizMsg, line, coaMdseTpList, errList);
        }
        otherEasyPackCheck(bizMsg, errList);
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {

            NWAL2200_DCMsg line = bizMsg.D.no(i);
            otherQtyCheckForReturn(bizMsg, bizMsg, line, errList);
        }
        otherEasyPackCheckForReturn(bizMsg, errList);

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            NWAL2200_BCMsg line = bizMsg.B.no(i);

            // S21_NA#11561 add start
            if (!S21StringUtil.isEquals(line.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                // skip excluded import line.
                continue;
            }
            // S21_NA#11561 add end
            otherMdseStsCheck(bizMsg, line, errList);
        }

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {

            NWAL2200_DCMsg line = bizMsg.D.no(i);
            otherMdseStsCheckForReturn(bizMsg, line, errList);
        }
    }

    // QC#14815
    private static Object getCustMdseXrefFlg(NWAL2200CMsg bizMsg) {
        CPO_SRC_TPTMsg tMsg = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoSrcTpCd, bizMsg.cpoSrcTpCd);

        tMsg = (CPO_SRC_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.custMdseXrefChkFlg.getValue();
    }

    private static void otherMdseStsCheckForReturn(NWAL2200CMsg bizMsg, NWAL2200_DCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        String mdseCd = source.mdseCd_RL.getValue();
        MDSETMsg mdse = NWXC220001.getOrderMdse(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (mdse == null) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWAM2201E));
            return;
        }

        // validate MDSE status
        MDSE_ITEM_STSTMsg mdseItemSts = NWXC220001.getMdseItemStatus(bizMsg.glblCmpyCd.getValue(), mdseCd);

        if (mdseItemSts == null) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWAM0037E));
            return;
        }

        // customer return available
        if (S21StringUtil.isEquals(mdseItemSts.custRtrnAvalFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return;
        }

        errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1507E));
    }

    private static void otherMdseStsCheck(NWAL2200CMsg bizMsg, NWAL2200_BCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        String mdseCd = source.mdseCd_LL.getValue();
        MDSETMsg mdse = NWXC220001.getOrderMdse(bizMsg.glblCmpyCd.getValue(), mdseCd);
        if (mdse == null) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWAM2201E));
            return;
        }

        // validate MDSE status
        MDSE_ITEM_STSTMsg mdseItemSts = NWXC220001.getMdseItemStatus(bizMsg.glblCmpyCd.getValue(), mdse.mdseCd.getValue());
        Map<String, Object> ordProcTpInfoMap = NWXC220001.getOrdProcTpInfo(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdTpCd.getValue(), source.dsOrdLineCatgCd_LL.getValue(), bizMsg.slsDt.getValue());

        if (ordProcTpInfoMap == null || mdseItemSts == null) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWAM0037E));
            return;
        }

        // internal order process
        if (ZYPConstant.FLG_ON_Y.equals((String) ordProcTpInfoMap.get("ITRL_ORD_PROC_FLG"))) {
            return;
        }

        // customer order process
        if (ZYPConstant.FLG_ON_Y.equals((String) ordProcTpInfoMap.get("CUST_ORD_PROC_FLG"))) {

            // Dummy WH
            if (!ZYPCommonFunc.hasValue(source.rtlSwhCd_LL)) {
                // skip
                return;
            }

            BigDecimal costPct = NWXC220001.getCostPctInfo(bizMsg.glblCmpyCd.getValue(), source.rtlSwhCd_LL.getValue(), bizMsg.slsDt.getValue());
            if ((BigDecimal.TEN.multiply(BigDecimal.TEN)).compareTo(costPct) == 0) {

                // order entry available
                if (S21StringUtil.isEquals(mdseItemSts.custOrdEntryAvalFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
                    return;
                }
            } else {

                // used only available
                if (S21StringUtil.isEquals(mdseItemSts.usedOnlyAvalFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
                    return;
                }
            }
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWAM0037E));
        }

        // WS order process
        if (S21StringUtil.isEquals((String) ordProcTpInfoMap.get("WS_ORD_PROC_FLG"), ZYPConstant.FLG_ON_Y)) {

            // WS order entry available
            if (S21StringUtil.isEquals(mdseItemSts.wsOrdEntryAvalFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
                return;
            }
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWAM0037E));
        }

    }

    private static void otherHeaderCheck(NWAL2200CMsg bizMsg, NWAL2200CMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (NWXC150001DsCheck.checkDsOrdTpAndDsOrdCatgRelation(bizMsg.glblCmpyCd.getValue(), source.dsOrdTpCd.getValue(), source.dsOrdCatgCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1450E));
        }
        if (NWXC150001DsCheck.checkCpoOrdTpAndDsOrdRsnRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.dsOrdTpCd.getValue(), source.dsOrdRsnCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1451E));
        }
        // S21_NA#11595 add start
        if (NWXC150001DsCheck.checkCarrSvcLevelRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.carrSvcLvlCd.getValue(), bizMsg.frtCondCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM2010E));
        }
        // S21_NA#11595 add end

        checkBillSellShipRelation(bizMsg, source, errList);

        if (NWXC150001DsCheck.checkCarrSvcLvlAndShpgSvcLvlRelation(//
                bizMsg.glblCmpyCd.getValue(), source.shpgSvcLvlCd.getValue(), source.carrSvcLvlCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1457E));
        }
        // 2018/12/13 S21_NA#29315 Add Start
        //if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.dsOrdTpCd.getValue(), source.frtCondCd.getValue(), source.shpgSvcLvlCd.getValue(), source.carrSvcLvlCd.getValue())) {
        if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), source.dsOrdTpCd.getValue(), source.frtCondCd.getValue(), source.shpgSvcLvlCd.getValue(), source.carrSvcLvlCd.getValue(),
                null, null /*Dummy Value*/)) {
        // 2018/12/13 S21_NA#29315 Add End
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1458E));
        }
        if (NWXC150001DsCheck.checkAddlCarrAcctNumRelation(bizMsg.glblCmpyCd.getValue(), source.carrAcctNum.getValue(), source.frtCondCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1459E));
        }
        if (NWXC150001DsCheck.checkPmntMethRelation(source.dsPmtMethCd.getValue(), null)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1460E));
        }
        if (NWXC150001DsCheck.checkCsmpRelation(bizMsg.glblCmpyCd.getValue(), source.csmpContrNum.getValue(), source.sellToCustCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1461E));
        }
        if (NWXC150001DsCheck.checkCsaNumRelation(bizMsg.glblCmpyCd.getValue(), source.dlrRefNum.getValue(), source.sellToCustCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1462E));
        }
        if (NWXC150001DsCheck.checkSalesRepRelation(bizMsg.glblCmpyCd.getValue(), source.slsRepTocCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1463E));
        }
        if (NWXC150001DsCheck.checkPrcCatgRelation(bizMsg.glblCmpyCd.getValue(), source.dsOrdTpCd.getValue(), bizMsg.slsDt.getValue(), source.prcCatgCd.getValue())) {
            NWZC157001PMsg prcApiPMsg //
            = callPricingApiForGetPrcList(//
                    bizMsg //
                    , NWZC157001.GET_PRICE_LIST //
                    , PRC_CTX_TP.SALES_PRICING //
                    , ZYPConstant.FLG_ON_Y);

            if (checkIncludePrcCatg(prcApiPMsg.xxPrcList, source.prcCatgCd.getValue())) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1464E));
            }
        }
        if (NWXC150001DsCheck.checkFlPrcListRelation(bizMsg.glblCmpyCd.getValue(), source.dsOrdTpCd.getValue(), bizMsg.slsDt.getValue(), source.flPrcListCd.getValue())) {
            NWZC157001PMsg prcApiPMsg //
            = callPricingApiForGetPrcList(//
                    bizMsg //
                    , NWZC157001.GET_PRICE_LIST //
                    , PRC_CTX_TP.SALES_PRICING //
                    , ZYPConstant.FLG_ON_Y);

            if (checkIncludePrcCatg(prcApiPMsg.xxPrcList, source.flPrcListCd.getValue())) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1465E));
            }
        }
    }

    private static void checkBillSellShipRelation(NWAL2200CMsg bizMsg, NWAL2200CMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (NWXC150001DsCheck.checkBillToRalation(bizMsg.glblCmpyCd.getValue(), source.billToCustCd.getValue(), source.billToCustAcctCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1452E));
        }
        if (NWXC150001DsCheck.checkShipToRalation(bizMsg.glblCmpyCd.getValue(), source.shipToCustCd.getValue(), source.shipToCustAcctCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1453E));
        }
        if (NWXC150001DsCheck.checkSoldToRalation(bizMsg.glblCmpyCd.getValue(), source.soldToCustLocCd.getValue(), source.sellToCustCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1454E));
        }
        if (checkBillShipSoldRelation(bizMsg, source, errList)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1455E));
        }
    }

    private static boolean checkBillShipSoldRelation(NWAL2200CMsg bizMsg, NWAL2200CMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        // 2017/06/09 S21_NA#18296 Mod Start
        // NMZC610001PMsg custInfoGetApiMsg =
        // NWXC150001DsCheck.callCustInfoGetApi(bizMsg.glblCmpyCd.getValue(),
        // source.billToCustCd.getValue(),
        // source.shipToCustCd.getValue(),
        // source.sellToCustCd.getValue(), ONBATCH_TYPE.ONLINE);
        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(bizMsg.glblCmpyCd.getValue(), source.billToCustCd.getValue(), source.sellToCustCd.getValue(), source.shipToCustAcctCd.getValue(), ONBATCH_TYPE.ONLINE);
        // 2017/06/09 S21_NA#18296 Mod End
        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {

            List<String> ml = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
            for (String msgId : ml) {

                errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), msgId));
            }
            return true;
        }
        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {

            NMZC610001_EligibleCheckListPMsg eligiblesource = custInfoGetApiMsg.EligibleCheckList.no(i);
            // 2017/06/09 S21_NA#18296 Mod Start
            // if (!ZYPConstant.FLG_ON_Y.equals(eligiblesource.dsAcctRelnBillToFlg_B.getValue()) || !ZYPConstant.FLG_ON_Y.equals(eligiblesource.dsAcctRelnShipToFlg_S.getValue())) {
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblesource.dsAcctRelnBillToFlg_B.getValue()) || !ZYPConstant.FLG_ON_Y.equals(eligiblesource.dsAcctRelnRecipFlg.getValue())) {
                // 2017/06/09 S21_NA#18296 Mod End
                return true;
            }
        }
        return false;
    }

    private static void otherConfigCheck(NWAL2200CMsg bizMsg, NWAL2200_ACMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        // customer relation
        checkConfigBillSellShip(bizMsg, source, errList);

        if (CONFIG_CATG.OUTBOUND.equals(source.configCatgCd_LC.getValue()) && ZYPCommonFunc.hasValue(source.svcConfigMstrPk_LC)) {
            if (NWXC150001DsCheck.isErrorConfigTpConfigIdRalation(bizMsg.glblCmpyCd.getValue(), source.configTpCd_LC.getValue(), source.svcConfigMstrPk_LC.getValue())) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1794E));
            }
        }
    }

    private static void getDtlConfigId(NWAL2200CMsg bizMsg, NWAL2200_ACMsg configbizMsg) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            String dsOrdPosnNum = configbizMsg.dsOrdPosnNum_LC.getValue();
            NWAL2200_BCMsg dtlbizMsg = bizMsg.B.no(i);

            // 2017/02/16 QC#16575 UPD START
            if (dsOrdPosnNum.equals(dtlbizMsg.dsOrdPosnNum_LL.getValue()) && ((ZYPCommonFunc.hasValue(dtlbizMsg.serNum_LL) && ZYPCommonFunc.hasValue(dtlbizMsg.mdseCd_LL)) || ZYPCommonFunc.hasValue(dtlbizMsg.svcMachMstrPk_LL))) {
                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(bizMsg.glblCmpyCd.getValue(), dtlbizMsg.serNum_LL.getValue(), dtlbizMsg.mdseCd_LL.getValue(), dtlbizMsg.svcMachMstrPk_LL.getValue());
                // 2017/02/16 QC#16575 UPD E N D
                if (null != map) {
                    ZYPEZDItemValueSetter.setValue(configbizMsg.svcConfigMstrPk_LC, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
                    if (ZYPCommonFunc.hasValue(configbizMsg.svcConfigMstrPk_LC) && (!ZYPCommonFunc.hasValue(configbizMsg.mdlId_LC) || !ZYPCommonFunc.hasValue(configbizMsg.t_MdlNm_LC))) {
                        Map<String, Object> mdlIdNmMap = NWXC220001.getMdlIdNmBySvcConfigMstr(bizMsg.glblCmpyCd.getValue(), configbizMsg.svcConfigMstrPk_LC.getValue());
                        if (null != mdlIdNmMap) {
                            if (null != mdlIdNmMap.get("MDL_ID")) {
                                ZYPEZDItemValueSetter.setValue(configbizMsg.mdlId_LC, (BigDecimal) mdlIdNmMap.get("MDL_ID"));
                            }
                            if (null != mdlIdNmMap.get("MDL_DESC_TXT")) {
                                ZYPEZDItemValueSetter.setValue(configbizMsg.t_MdlNm_LC, (String) mdlIdNmMap.get("MDL_DESC_TXT"));
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    private static void getRtrnConfigId(NWAL2200CMsg bizMsg, NWAL2200_CCMsg configbizMsg) {

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            String dsOrdPosnNum = configbizMsg.dsOrdPosnNum_RC.getValue();
            NWAL2200_DCMsg rtnDtlbizMsg = bizMsg.D.no(i);

            // 2017/02/16 QC#16575 UPD START
            if (dsOrdPosnNum.equals(rtnDtlbizMsg.dsOrdPosnNum_RL.getValue())
                    && ((ZYPCommonFunc.hasValue(rtnDtlbizMsg.serNum_RL) && ZYPCommonFunc.hasValue(rtnDtlbizMsg.mdseCd_RL.getValue())) || ZYPCommonFunc.hasValue(rtnDtlbizMsg.svcMachMstrPk_RL))) {
                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(bizMsg.glblCmpyCd.getValue(), rtnDtlbizMsg.serNum_RL.getValue(), rtnDtlbizMsg.mdseCd_RL.getValue(), rtnDtlbizMsg.svcMachMstrPk_RL.getValue());
                // 2017/02/16 QC#16575 UPD END
                if (null != map) {

                    ZYPEZDItemValueSetter.setValue(configbizMsg.svcConfigMstrPk_RC, (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"));
                    if (ZYPCommonFunc.hasValue(configbizMsg.svcConfigMstrPk_RC) && (!ZYPCommonFunc.hasValue(configbizMsg.mdlId_RC) || !ZYPCommonFunc.hasValue(configbizMsg.t_MdlNm_RC))) {
                        Map<String, Object> mdlIdNmMap = NWXC220001.getMdlIdNmBySvcConfigMstr(bizMsg.glblCmpyCd.getValue(), configbizMsg.svcConfigMstrPk_RC.getValue());
                        if (null != mdlIdNmMap) {
                            if (null != mdlIdNmMap.get("MDL_ID")) {
                                ZYPEZDItemValueSetter.setValue(configbizMsg.mdlId_RC, (BigDecimal) mdlIdNmMap.get("MDL_ID"));
                            }
                            if (null != mdlIdNmMap.get("MDL_DESC_TXT")) {
                                ZYPEZDItemValueSetter.setValue(configbizMsg.t_MdlNm_RC, (String) mdlIdNmMap.get("MDL_DESC_TXT"));
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    private static void checkConfigBillSellShip(NWAL2200CMsg bizMsg, NWAL2200_ACMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (NWXC150001DsCheck.checkBillToRalation(bizMsg.glblCmpyCd.getValue(), source.billToCustLocCd_LC.getValue(), source.billToCustAcctCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1452E));
        }
        if (NWXC150001DsCheck.checkShipToRalation(bizMsg.glblCmpyCd.getValue(), source.shipToCustLocCd_LC.getValue(), source.shipToCustAcctCd_LC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1453E));
        }

        // 2017/06/09 S21_NA#18296 Mod Start
        // NMZC610001PMsg rsltPMsg =
        // NWXC150001DsCheck.callCustInfoGetApi(bizMsg.glblCmpyCd.getValue(),
        // source.billToCustLocCd_LC.getValue(),
        // source.shipToCustLocCd_LC.getValue(),
        // bizMsg.sellToCustCd.getValue(), ONBATCH_TYPE.ONLINE);
        NMZC610001PMsg rsltPMsg = NWXC150001DsCheck.callCustInfoGetApi(bizMsg.glblCmpyCd.getValue(), source.billToCustLocCd_LC.getValue(), bizMsg.sellToCustCd.getValue(), source.shipToCustAcctCd_LC.getValue(), ONBATCH_TYPE.ONLINE);
        // 2017/06/09 S21_NA#18296 Mod End
        if (S21ApiUtil.isXxMsgId(rsltPMsg)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1455E));
        }

        for (int elgChkCnt = 0; elgChkCnt < rsltPMsg.EligibleCheckList.getValidCount(); elgChkCnt++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = rsltPMsg.EligibleCheckList.no(elgChkCnt);
            // 2017/06/09 S21_NA#18296 Mod Start
            // if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
                // 2017/06/09 S21_NA#18296 Mod End
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1455E));
            }
        }

        if (NWXC150001DsCheck.checkConfigIdEssential(bizMsg.glblCmpyCd.getValue(), source.configTpCd_LC.getValue(), source.svcConfigMstrPk_LC.getValue())) {
            getDtlConfigId(bizMsg, source);
            if (!ZYPCommonFunc.hasValue(source.svcConfigMstrPk_LC)) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1466E));
            }
        }
        // Del Start 2016/12/19 M.Ohno S21_NA#16460
//        if (ZYPCommonFunc.hasValue(source.svcConfigMstrPk_LC)) {
//            if (NWXC150001DsCheck.checkConfigShiptoLocation(bizMsg.glblCmpyCd.getValue(), source.configTpCd_LC.getValue(), source.svcConfigMstrPk_LC.getValue(), source.shipToCustLocCd_LC.getValue())) {
//                errList.add(createErr(bizMsg, source.dsImptOrdPk_LC.getValue(), source.dsImptOrdConfigPk_LC.getValue(), NWZC150001Constant.NWZM1467E));
//            }
//        }
        // Del End   2016/12/19 M.Ohno S21_NA#16460
    }

    private static void otherConfigCheck(NWAL2200CMsg bizMsg, NWAL2200_CCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        // customer relation
        checkConfigBillSellShip(bizMsg, source, errList);

        if (NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), source.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, false, true, true)) {
            getRtrnConfigId(bizMsg, source);
            if (!ZYPCommonFunc.hasValue(source.svcConfigMstrPk_RC)) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1466E));
            }
        }

        if (CONFIG_CATG.INBOUND.equals(source.configCatgCd_RC.getValue()) && ZYPCommonFunc.hasValue(source.svcConfigMstrPk_RC)) {
            if (NWXC150001DsCheck.isErrorConfigTpConfigIdRalation(bizMsg.glblCmpyCd.getValue(), source.configTpCd_RC.getValue(), source.svcConfigMstrPk_RC.getValue())) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1794E));
            }
        }
    }

    private static void checkConfigBillSellShip(NWAL2200CMsg bizMsg, NWAL2200_CCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (NWXC150001DsCheck.checkBillToRalation(bizMsg.glblCmpyCd.getValue(), source.billToCustLocCd_RC.getValue(), source.billToCustAcctCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1452E));
        }
        if (NWXC150001DsCheck.checkShipToRalation(bizMsg.glblCmpyCd.getValue(), source.shipToCustLocCd_RC.getValue(), source.shipToCustAcctCd_RC.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1453E));
        }

        // 2017/06/09 S21_NA#18296 Mod Start
        // NMZC610001PMsg rsltPMsg = NWXC150001DsCheck.callCustInfoGetApi(bizMsg.glblCmpyCd.getValue(), source.billToCustLocCd_RC.getValue(), source.shipToCustLocCd_RC.getValue(), bizMsg.sellToCustCd.getValue(), ONBATCH_TYPE.ONLINE);
        NMZC610001PMsg rsltPMsg = NWXC150001DsCheck.callCustInfoGetApi(bizMsg.glblCmpyCd.getValue(), source.billToCustLocCd_RC.getValue(), bizMsg.sellToCustCd.getValue(), source.shipToCustAcctCd_RC.getValue(), ONBATCH_TYPE.ONLINE);
        // 2017/06/09 S21_NA#18296 Mod End
        if (S21ApiUtil.isXxMsgId(rsltPMsg)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1455E));
        }

        for (int elgChkCnt = 0; elgChkCnt < rsltPMsg.EligibleCheckList.getValidCount(); elgChkCnt++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = rsltPMsg.EligibleCheckList.no(elgChkCnt);
            // 2017/06/09 S21_NA#18296 Mod Start
            // if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
                // 2017/06/09 S21_NA#18296 Mod End
                errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1455E));
            }
        }

        if (NWXC150001DsCheck.checkConfigIdEssential(bizMsg.glblCmpyCd.getValue(), source.configTpCd_RC.getValue(), source.svcConfigMstrPk_RC.getValue())) {
            getRtrnConfigId(bizMsg, source);
            if (!ZYPCommonFunc.hasValue(source.svcConfigMstrPk_RC)) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1466E));
            }
        }
        // Del Start 2016/12/19 M.Ohno S21_NA#16460
//        if (NWXC150001DsCheck.checkConfigShiptoLocation(bizMsg.glblCmpyCd.getValue(), source.configTpCd_RC.getValue(), source.svcConfigMstrPk_RC.getValue(), source.shipToCustLocCd_RC.getValue())) {
//            errList.add(createErr(bizMsg, source.dsImptOrdPk_RC.getValue(), source.dsImptOrdConfigPk_RC.getValue(), NWZC150001Constant.NWZM1467E));
//        }
        // Del End   2016/12/19 M.Ohno S21_NA#16460
    }

    private static void otherDetailCheck(//
            NWAL2200CMsg bizMsg, NWAL2200CMsg order, NWAL2200_BCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList, boolean isCustMdseXrefChk) { // QC#14815

        if (isCustMdseXrefChk) { // QC#14815
            if (NWXC150001DsCheck.checkDetailMdseRelation(//
                    bizMsg.glblCmpyCd.getValue() //
                    , source.mdseCd_LL.getValue() //
                    , source.custMdseCd_LL.getValue() //
                    , order.sellToCustCd.getValue())) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1468E));
            }
        }
        if (NWXC150001DsCheck.checkDetailLineCatgRelation(//
                bizMsg.glblCmpyCd.getValue() //
                , source.dsOrdLineCatgCd_LL.getValue() //
                , order.dsOrdTpCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1469E));
        }
        if (NWXC150001DsCheck.checkRetailWhRelation(//
                bizMsg.glblCmpyCd.getValue() //
                , bizMsg.slsDt.getValue(), order.dsOrdTpCd.getValue() //
                , source.rtlWhCd_LL.getValue() //
                , source.rtlSwhCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1470E));
        }

        // 2017/02/16 QC#16575 DEL START
        // if (ZYPCommonFunc.hasValue(source.serNum_LL)) {
        //     Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(//
        //             bizMsg.glblCmpyCd.getValue() //
        //             , source.serNum_LL.getValue()
        //             , source.mdseCd_LL.getValue());
        //      if (map == null) {
        // 2017/02/16 QC#16575 DEL E N D
                // S21_NA#3112
                // errList.add(createErr(bizMsg,
                // source.dsImptOrdPk_LL.getValue(),
                // source.dsImptOrdConfigPk_LL.getValue(),
                // source.dsImptOrdDtlPk_LL.getValue(), null,
                // NWZC150001Constant.NWZM1471E));
        // 2017/02/16 QC#16575 DEL START
        //      } else {
        // 2017/02/16 QC#16575 DEL E N D
                // NWAL2200_ACMsg configPMsg = getConfigPMsg(source,
                // order.A);
                // if (checkSerNumForOrderDetail(//
                // bizMsg.glblCmpyCd.getValue()//
                // , pMsg.cpoOrdNum.getValue() //
                // , source//
                // , configPMsg.configTpCd.getValue() //
                // , (String) map.get("SVC_MACH_USG_STS_CD") //
                // , (String) map.get("SVC_MACH_MSTR_STS_CD") //
                // , (String) map.get("ALLC_FLG") //
                // , (String) map.get("CPO_ORD_NUM") //
                // , (String) map.get("CPO_DTL_LINE_NUM") //
                // , (String) map.get("CPO_DTL_LINE_SUB_NUM"))) {
                // errList.add(createErr(bizMsg,
                // source.dsImptOrdPk_LL.getValue(),
                // source.dsImptOrdConfigPk_LL.getValue(),
                // source.dsImptOrdDtlPk_LL.getValue(), null,
                // NWZC150001Constant.NWZM1472E));
                // }

                // if
                // (ZYPCommonFunc.hasValue(configPMsg.pickSvcConfigMstrPk))
                // {
                // if
                // (NWXC150001DsCheck.checkSerNumSvcConfigMstrPkRelation(configPMsg.pickSvcConfigMstrPk.getValue(),
                // (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))) {
                // errList.add(createErr(bizMsg,
                // source.dsImptOrdPk_LL.getValue(),
                // source.dsImptOrdConfigPk_LL.getValue(),
                // source.dsImptOrdDtlPk_LL.getValue(), null,
                // NWZC150001Constant.NWZM1473E));
                // }
                // }
                // S21_NA#8651
                // if
                // (NWXC150001DsCheck.checkSerNumRtlWhRelation(bizMsg.glblCmpyCd.getValue(),
                // source.rtlWhCd_LL.getValue(),
                // source.rtlSwhCd_LL.getValue(), (String)
                // map.get("CUR_LOC_NUM"))) {
                // errList.add(createErr(bizMsg,
                // source.dsImptOrdPk_LL.getValue(),
                // source.dsImptOrdConfigPk_LL.getValue(),
                // source.dsImptOrdDtlPk_LL.getValue(), null,
                // NWZC150001Constant.NWZM1474E));
                // }
        // 2017/02/16 QC#16575 DEL E N D
        //      }
        //  }
        // 2017/02/16 QC#16575 DEL E N D

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL2200_BCMsg line = bizMsg.B.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_LL.getValue(), source.dsOrdPosnNum_LL.getValue())) {
                if (S21StringUtil.isEquals(line.xxLineNum_LL.getValue(), source.dplyLineRefNum_LL.getValue())) {

                }
            }
        }
        if (NWXC150001DsCheck.checkSbstRelation(bizMsg.glblCmpyCd.getValue(), source.origMdseCd_LL.getValue(), source.sbstMdseCd_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1475E));
        }

        // line reference num
        if (ZYPCommonFunc.hasValue(source.dplyLineRefNum_LL)) {
            boolean foundRefNum = false;
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL2200_BCMsg line = bizMsg.B.no(i);
                if (S21StringUtil.isEquals(line.dsOrdPosnNum_LL.getValue(), source.dsOrdPosnNum_LL.getValue())) {
                    if (S21StringUtil.isEquals(line.xxLineNum_LL.getValue(), source.dplyLineRefNum_LL.getValue())) {
                        foundRefNum = true;
                    }
                }
            }
            if (!foundRefNum) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1476E));
            }
        }

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), source.mdseCd_LL.getValue());
        if (mdseTMsg != null) {
            if (NWXC150001DsCheck.checkDealWh(bizMsg.glblCmpyCd.getValue(), source.rtlWhCd_LL.getValue(), source.dsOrdLineCatgCd_LL.getValue(), mdseTMsg.invtyCtrlFlg.getValue())) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1478E));
            }
        }
    }

    private static void otherReturnDetailCheck(//
            NWAL2200CMsg bizMsg, NWAL2200CMsg order, NWAL2200_DCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList, boolean isCustMdseXrefChk) {

        NWAL2200_CCMsg configPMsg = getConfigPMsgForRtrn(source, order.C);
        if (isCustMdseXrefChk) { // QC#14815
            if (NWXC150001DsCheck.checkDetailMdseRelation(bizMsg.glblCmpyCd.getValue(), source.mdseCd_RL.getValue(), source.custMdseCd_RL.getValue(), order.sellToCustCd.getValue())) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1468E));
            }
        }

        if (NWXC150001DsCheck.checkDetailLineCatgRelation(bizMsg.glblCmpyCd.getValue(), source.dsCpoLineCatgCd_RL.getValue(), order.dsOrdTpCd.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1469E));
        }
        if (NWXC150001DsCheck.checkRetailWhRelation(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), order.dsOrdTpCd.getValue(), source.rtlWhCd_RL.getValue(), source.rtlSwhCd_RL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1470E));
        }
        if (ZYPCommonFunc.hasValue(source.serNum_RL)) {
            // 2017/02/16 QC#16575 UPD START
            // Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(bizMsg.glblCmpyCd.getValue(), source.serNum_RL.getValue());
            Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(bizMsg.glblCmpyCd.getValue(), source.serNum_RL.getValue(), source.mdseCd_RL.getValue());
            // 2017/02/16 QC#16575 UPD START
            if (map == null) {
                // S21_NA#3112
                // errList.add(createErr(bizMsg,
                // source.dsImptOrdPk_RL.getValue(),
                // source.dsImptOrdConfigPk_RL.getValue(), null,
                // source.dsImptOrdRtrnDtlPk_RL.getValue(),
                // NWZC150001Constant.NWZM1471E));
            } else {
                // CONFIG
                // if (checkSerNumForReturnDetail(//
                // bizMsg.glblCmpyCd.getValue() //
                // , order.cpoOrdNum.getValue() //
                // , source//
                // , configPMsg.configTpCd_RC.getValue() //
                // , (String) map.get("SVC_MACH_USG_STS_CD") //
                // , (String) map.get("SVC_MACH_MSTR_STS_CD") //
                // , (String) map.get("ALLC_FLG") //
                // , (String) map.get("CPO_ORD_NUM") //
                // , (String) map.get("CPO_DTL_LINE_NUM") //
                // , (String) map.get("CPO_DTL_LINE_SUB_NUM"))) {
                // errList.add(createErr(bizMsg,
                // source.dsImptOrdPk_RL.getValue(),
                // source.dsImptOrdConfigPk_RL.getValue(), null,
                // source.dsImptOrdRtrnDtlPk_RL.getValue(),
                // NWZC150001Constant.NWZM1472E));
                // }
                // if
                // (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk_RC))
                // {
                // if
                // (NWXC150001DsCheck.checkSerNumSvcConfigMstrPkRelation(configPMsg.svcConfigMstrPk_RC.getValue(),
                // (BigDecimal) map.get("SVC_CONFIG_MSTR_PK"))) {
                // errList.add(createErr(bizMsg,
                // source.dsImptOrdPk_RL.getValue(),
                // source.dsImptOrdConfigPk_RL.getValue(), null,
                // source.dsImptOrdRtrnDtlPk_RL.getValue(),
                // NWZC150001Constant.NWZM1473E));
                // }
                // }
                if (ZYPCommonFunc.hasValue(source.dplyLineRefNum_RL)) {
                    boolean foundRefNum = false;
                    for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                        NWAL2200_DCMsg line = bizMsg.D.no(i);
                        if (S21StringUtil.isEquals(line.dsOrdPosnNum_RL.getValue(), source.dsOrdPosnNum_RL.getValue())) {
                            if (S21StringUtil.isEquals(line.xxLineNum_RL.getValue(), source.dplyLineRefNum_RL.getValue())) {
                                foundRefNum = true;
                            }
                        }
                    }
                    if (!foundRefNum) {
                        errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1476E));
                    }
                }
                // bllgAttrbCustAcctCd_RL
                // if
                // (ZYPCommonFunc.hasValue(source.bllgAttrbCustAcctCd_RL))
                // {
                // NMZC610001PMsg cbaPMsg =
                // callCheckBillingAttribute(order,
                // source.bllgAttrbCustAcctCd_RL.getValue());
                // if (S21ApiUtil.isXxMsgId(cbaPMsg)) {
                // List<String> msgIdList =
                // S21ApiUtil.getXxMsgIdList(cbaPMsg);
                // for (String msgId : msgIdList) {
                // setMsgId(order, msgId);
                // }
                // }
                // }
            }
        }

        if (ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk_RC)) {
            String mdseCd = source.mdseCd_RL.getValue();
            BigDecimal svcConfigMstrPk = configPMsg.svcConfigMstrPk_RC.getValue();
            if (NWXC150001DsCheck.isIbInstallableAndNotInConfig(bizMsg.glblCmpyCd.getValue(), mdseCd, svcConfigMstrPk)) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1795E));
            }
        }
    }

    private static void otherSvcExchCheck(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {

        // QC#14021
        // if
        // (!NWXC150001DsCheck.isSvcExch(bizMsg.glblCmpyCd.getValue(),
        // bizMsg.dsOrdCatgCd.getValue())) {
        if (!NWXC150001DsCheck.isSvcExch(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue())) {
            return;
        }

        List<BigDecimal> inSvcConfigPkList = new ArrayList<BigDecimal>();
        List<NWAL2200_CCMsg> inConfigPMsgList = new ArrayList<NWAL2200_CCMsg>();
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL2200_CCMsg configPMsg = bizMsg.C.no(i);
            if (CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd_RC.getValue()) //
                    // In bound N Y N
                    && NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configPMsg.configTpCd_RC.getValue(), CONFIG_CATG.INBOUND, false, true, false) // S21_NA#955
                    && ZYPCommonFunc.hasValue(configPMsg.svcConfigMstrPk_RC)) {
                inSvcConfigPkList.add(configPMsg.svcConfigMstrPk_RC.getValue());
                inConfigPMsgList.add(configPMsg);
            }
        }
        if (inSvcConfigPkList.size() == 0) {
            errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1525E));
            return;
        }

        List<String> rtnMdseList = new ArrayList<String>();
        int ix = 0;
        for (BigDecimal svcConfigMstrPk : inSvcConfigPkList) {
            NWAL2200_CCMsg cpoConfigPMsg = inConfigPMsgList.get(ix++);
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                NWAL2200_DCMsg rtnDtlPMsg = bizMsg.D.no(i);
                if (cpoConfigPMsg.dsOrdPosnNum_RC.getValue().equals(rtnDtlPMsg.dsOrdPosnNum_RL.getValue())) {
                    if (!NWXC150001DsCheck.isExistsSvcConfigMstr(bizMsg.glblCmpyCd.getValue(), svcConfigMstrPk, rtnDtlPMsg.svcMachMstrPk_RL.getValue())) {
                        errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1526E));
                        return;
                    }
                    rtnMdseList.add(rtnDtlPMsg.mdseCd_RL.getValue());
                }
            }
        }

        List<NWAL2200_ACMsg> outConfigPMsgList = new ArrayList<NWAL2200_ACMsg>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2200_ACMsg configPMsg = bizMsg.A.no(i);
            if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd_LC.getValue())
            // Out bound N Y N
                    && NWXC150001DsCheck.matchConfigTp(bizMsg.glblCmpyCd.getValue(), configPMsg.configTpCd_LC.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) { // S21_NA#955
                if (inSvcConfigPkList.contains(configPMsg.svcConfigMstrPk_LC.getValue())) {
                    outConfigPMsgList.add(configPMsg);
                } else {
                    errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1527E));
                    return;
                }
            }
        }

        for (NWAL2200_ACMsg cpoConfigPMsg : outConfigPMsgList) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL2200_BCMsg aPMsg = bizMsg.B.no(i);

                // S21_NA#11561 add start
                if (!S21StringUtil.isEquals(aPMsg.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                    // skip excluded import line.
                    continue;
                }
                // S21_NA#11561 add end
                if (cpoConfigPMsg.dsOrdPosnNum_LC.getValue().equals(aPMsg.dsOrdPosnNum_LL.getValue())) {
                    if (!rtnMdseList.contains(aPMsg.mdseCd_LL.getValue())) {
                        errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1528E));
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            NWAL2200_DCMsg rtnDtlPMsg = bizMsg.D.no(i);
            if (NWXC150001DsCheck.checkRtnMMOrdTpAndSvcExchRsnRelation(bizMsg.glblCmpyCd.getValue(), rtnDtlPMsg.svcMachMstrPk_RL.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue())) {
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1529E));
                return;
            }
        }
    }

    private static void otherQtyCheck(NWAL2200CMsg bizMsg, NWAL2200CMsg order, NWAL2200_BCMsg source, List<String> coaMdseTpList, List<DS_IMPT_ORD_ERRTMsg> errList) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), source.mdseCd_LL.getValue());
        if (mdseTMsg == null) {
            return;
        }
        String coaMdseTpCd = NWXC150001DsCheck.getCoaMdseTp(bizMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue());
        if (NWXC150001DsCheck.checkMachQty(coaMdseTpList, coaMdseTpCd, source.ordQty_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1483E));
        }
        if (NWXC150001DsCheck.checkSerialQty(source.serNum_LL.getValue(), source.ordQty_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1484E));
        }
        if (NWXC150001DsCheck.checkLicenseItemQty(mdseTMsg.thirdPtyVndDropFlg.getValue(), source.ordQty_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1485E));
        }
        if (NWXC150001DsCheck.checkMinusQty(source.crRebilCd_LL.getValue(), source.ordQty_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1486E));
        }
        // if set component then, skip this check.
        if (ZYPCommonFunc.hasValue(order.cpoOrdNum) && !ZYPCommonFunc.hasValue(source.dsCpoLineSubNum_LL)) {
            if (NWXC150001DsCheck.checkChangeQty(bizMsg.glblCmpyCd.getValue(), order.cpoOrdNum.getValue(), source.trxLineNum_LL.getValue(), source.trxLineSubNum_LL.getValue(), source.ordQty_LL.getValue())) {
                errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1487E));
            }
        }
        if (NWXC150001DsCheck.checkMinOrdQty(mdseTMsg.cpoMinOrdQty.getValue(), source.ordQty_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1488E));
        }
        if (NWXC150001DsCheck.checkMaxOrdQty(mdseTMsg.cpoMaxOrdQty.getValue(), source.ordQty_LL.getValue())) { // S21_NA#1405
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1489E));
        }
        if (NWXC150001DsCheck.checkIncrOrdQty(mdseTMsg.cpoIncrOrdQty.getValue(), source.ordQty_LL.getValue())) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1492E));
        }
    }

    private static void otherQtyCheckForReturn(NWAL2200CMsg bizMsg, NWAL2200CMsg order, NWAL2200_DCMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (BigDecimal.ZERO.compareTo(source.ordQty_RL.getValue()) <= 0) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1596E));
        }
    }

    private static void otherEasyPackCheck(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (NWXC150001DsCheck.isEasyPack(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue())) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL2200_BCMsg source = bizMsg.B.no(i);

                // S21_NA#11561 add start
                if (!S21StringUtil.isEquals(source.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                    // skip excluded import line.
                    continue;
                }
                // S21_NA#11561 add end
                MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), source.mdseCd_LL.getValue());
                if (mdseTMsg == null) {
                    continue;
                }
                MDSETMsg tMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseTMsg.mdseCd.getValue());
                tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
                if (tMsg == null || !ZYPCommonFunc.hasValue(tMsg.easyPackTpCd)) {
                    errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM1530E));
                    continue;
                }
                if (!NWXC150001DsCheck.isSplyPgmContr(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue())) {
                    errList.add(createErr(bizMsg, source.dsImptOrdPk_LL.getValue(), source.dsImptOrdConfigPk_LL.getValue(), source.dsImptOrdDtlPk_LL.getValue(), null, NWZC150001Constant.NWZM2008E));
                }
            }

        }
    }

    private static void otherEasyPackCheckForReturn(NWAL2200CMsg bizMsg, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (NWXC150001DsCheck.isEasyPack(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue())) {
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                NWAL2200_DCMsg source = bizMsg.D.no(i);
                MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), source.mdseCd_RL.getValue());
                if (mdseTMsg == null) {
                    continue;
                }
                MDSETMsg tMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseTMsg.mdseCd.getValue());
                tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
                if (tMsg == null || !ZYPCommonFunc.hasValue(tMsg.easyPackTpCd)) {
                    errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM1530E));
                    continue;
                }
                if (!NWXC150001DsCheck.isSplyPgmContr(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue())) {
                    errList.add(createErr(bizMsg, source.dsImptOrdPk_RL.getValue(), source.dsImptOrdConfigPk_RL.getValue(), null, source.dsImptOrdRtrnDtlPk_RL.getValue(), NWZC150001Constant.NWZM2008E));
                }
            }

        }
    }

    private static void checkEssentialForHeader(NWAL2200CMsg bizMsg, NWAL2200CMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        if (!ZYPCommonFunc.hasValue(source.dsOrdCatgCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1401E));
        }
        if (!ZYPCommonFunc.hasValue(source.dsOrdTpCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1253E));
        }
        if (!ZYPCommonFunc.hasValue(source.billToCustAcctCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1377E));
        }
        if (!ZYPCommonFunc.hasValue(source.billToCustCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM0617E));
        }
        if (!ZYPCommonFunc.hasValue(source.shipToCustAcctCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1379E));
        }
        if (!ZYPCommonFunc.hasValue(source.sellToCustCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1402E));
        }
        if (!ZYPCommonFunc.hasValue(source.soldToCustLocCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1403E));
        }
        if (!ZYPCommonFunc.hasValue(source.shpgSvcLvlCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM0049E));
        }
        if (!ZYPCommonFunc.hasValue(source.shipToCustCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM0619E));
        }
        if (!ZYPCommonFunc.hasValue(source.slsRepTocCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM0688E));
        }
        if (!ZYPCommonFunc.hasValue(source.prcCatgCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1405E));
        }
        if (!ZYPCommonFunc.hasValue(source.flPrcListCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1406E));
        }
        if (!ZYPCommonFunc.hasValue(source.frtCondCd)) {
            errList.add(createErr(bizMsg, source.dsImptOrdPk.getValue(), NWZC150001Constant.NWZM1266E));
        }
        return;
    }

    // S21_NA#11435 add start
    private static boolean validatePricing(NWAL2200CMsg bizMsg, NWAL2200CMsg source, List<DS_IMPT_ORD_ERRTMsg> errList) {

        NWZC157001PMsg prcApiPMsg = NWAL2200CommonLogicForPricing.callPricingApiGetOrderAllMode(source);

        boolean pricingResult = true;
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {

                S21ApiMessage msg = msgList.get(i);
                String messageId = msg.getXxMsgid();
                String[] messageParameters = msg.getXxMsgPrmArray();
                errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), messageId, messageParameters));
                if (messageId.endsWith("E")) {

                    pricingResult = false;
                }
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {

            NWZC157002PMsg priceLine = prcApiPMsg.NWZC157002PMsg.no(i);
            if (S21ApiUtil.isXxMsgId(priceLine)) {

                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (int j = 0; j < msgList.size(); j++) {

                    S21ApiMessage msg = msgList.get(j);
                    String messageId = msg.getXxMsgid();
                    String[] messageParameters = msg.getXxMsgPrmArray();

                    // 09/16/2016 S21_NA#12145 add Start
                    if (NWZM1328E.equals(messageId) && NWAL2200Query.getInstance().isItrlOrdProcFlgOn(bizMsg)) {
                        continue;
                    }
                    // 09/16/2016 S21_NA#12145 add End

                    if (S21StringUtil.isEquals(priceLine.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {

                        // out bound
                        NWAL2200_BCMsg line = NWAL2200CommonLogicForLineControl.getLine(bizMsg.B, priceLine.trxLineNum.getValue(), priceLine.trxLineSubNum.getValue());
                        if (line != null) {

                            errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), line.dsImptOrdConfigPk_LL.getValue(), line.dsImptOrdDtlPk_LL.getValue(), null, messageId, messageParameters));
                        }
                    } else {

                        // in bound
                        NWAL2200_DCMsg line = NWAL2200CommonLogicForLineControl.getLine(bizMsg.D, priceLine.trxLineNum.getValue(), priceLine.trxLineSubNum.getValue());
                        if (line != null) {

                            errList.add(createErr(bizMsg, bizMsg.dsImptOrdPk.getValue(), line.dsImptOrdConfigPk_RL.getValue(), null, line.dsImptOrdRtrnDtlPk_RL.getValue(), messageId, messageParameters));
                        }
                    }
                    if (messageId.endsWith("E")) {

                        pricingResult = false;
                    }
                }
            }
        }

        return pricingResult;
    }

    // S21_NA#11435 add end

    private static DS_IMPT_ORD_ERRTMsg createErr(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdPk, String messageId) {

        return createErr(bizMsg, dsImptOrdPk, null, null, null, messageId);
    }

    private static DS_IMPT_ORD_ERRTMsg createErr(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdPk, String messageId, String param) {

        return createErr(bizMsg, dsImptOrdPk, null, null, null, messageId, new String[] {param });
    }

    private static DS_IMPT_ORD_ERRTMsg createErr(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdPk, String messageId, String[] paramArray) {

        return createErr(bizMsg, dsImptOrdPk, null, null, null, messageId, paramArray);
    }

    private static DS_IMPT_ORD_ERRTMsg createErr(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, String messageId) {

        return createErr(bizMsg, dsImptOrdPk, dsImptOrdConfigPk, null, null, messageId);
    }

    private static DS_IMPT_ORD_ERRTMsg createErr(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, BigDecimal dsImptOrdDtlPk, BigDecimal dsImptOrdRtrnDtlPk, String messageId, Object... param) {

        String[] paramArray = new String[param.length];

        for (int i = 0; i < paramArray.length; i++) {
            if (param[i] != null) {
                paramArray[i] = param[i].toString();
            } else {
                paramArray[i] = "";
            }
        }

        return createErr(bizMsg, dsImptOrdPk, dsImptOrdConfigPk, dsImptOrdDtlPk, dsImptOrdRtrnDtlPk, messageId, paramArray);
    }

    private static DS_IMPT_ORD_ERRTMsg createErr(NWAL2200CMsg bizMsg, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, BigDecimal dsImptOrdDtlPk, BigDecimal dsImptOrdRtrnDtlPk, String messageId, String[] paramArray) {

        DS_IMPT_ORD_ERRTMsg dsImptOrdErr = new DS_IMPT_ORD_ERRTMsg();
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdPk, dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdConfigPk, dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdDtlPk, dsImptOrdDtlPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdRtrnDtlPk, dsImptOrdRtrnDtlPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrMsgId, messageId);
        ZYPEZDItemValueSetter.setValue(dsImptOrdErr.dsImptOrdErrTxt, S21MessageFunc.clspGetMessage(messageId, paramArray));

        return dsImptOrdErr;
    }

    private static List<String> getCoaMdseTpList(String glblCmpyCd) {

        List<String> resultList = new ArrayList<String>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mainMach", "MAIN_MACH");
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getCoaMdseTpList(ssmParam);
        if (ssmResult.isCodeNotFound()) {
            return resultList;
        }
        return (List<String>) ssmResult.getResultObject();
    }

    private static NWAL2200_CCMsg getConfigPMsgForRtrn(NWAL2200_DCMsg rtnDtlPMsg, NWAL2200_CCMsgArray configPMsgArray) {

        for (int i = 0; i < configPMsgArray.getValidCount(); i++) {
            NWAL2200_CCMsg configPMsg = configPMsgArray.no(i);
            if (rtnDtlPMsg.dsOrdPosnNum_RL.getValue().equals(configPMsg.dsOrdPosnNum_RC.getValue()) && CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd_RC.getValue())) {
                return configPMsg;
            }
        }
        return new NWAL2200_CCMsg();
    }

    private static NWZC157001PMsg callPricingApiForGetPrcList(NWAL2200CMsg bizMsg, String modeCd, String prcCtxTpCd, String prcCatgOpeFlg) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, modeCd);
        if (ZYPCommonFunc.hasValue(bizMsg.prcBaseDt)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.prcBaseDt);
        } else {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, bizMsg.slsDt);
        }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, prcCtxTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd //
                , NWXC150001DsCheck.getLineBizTpCd(bizMsg.glblCmpyCd.getValue(), bizMsg.slsDt.getValue(), bizMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, bizMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, bizMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, bizMsg.coaBrCd.getValue());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, prcCatgOpeFlg);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);
        return prcApiPMsg;
    }

    /**
     * checkIncludePrcCatg
     * @param xxPrcList
     * @param value
     * @return if not include then return true.
     */
    private static boolean checkIncludePrcCatg(NWZC157001_xxPrcListPMsgArray xxPrcList, String value) {
        for (int i = 0; i < xxPrcList.getValidCount(); i++) {
            if (value.equals(xxPrcList.no(i).prcCatgCd.getValue())) {
                return false;
            }
        }
        return true;
    }
}
