/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001Constant.EAZY_PACK1;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001Constant.FORCE_DUMMY_WH;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001Constant.MAIN_MACH;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001Constant.RETAIL_EQUIP_ORDER;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001Constant.ROWNUM_1;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001Constant.SERVICE_EXCHANGE;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001Constant.SERVICE_EXCHANGE_ELIG;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NWX.NWXC150001.cache.DataCacheForSSM;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NWXC150001Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/06/14   Fujitsu         S.Takami        Update          S21_NA#18623
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2017/11/16   Fujitsu         T.Aoi           Update          S21_NA#22620
 * 2017/11/16   Fujitsu         Y.Kanefusa      Update          S21_NA#22651
 * 2017/12/26   Fujitsu         S.Takami        Update          S21_NA#22986
 * 2018/02/13   Fujitsu         M.Ohno          Update          S21_NA#22717
 * 2018/03/28   Fujitsu         A.Kosai         Update          S21_NA#24853
 * 2018/05/18   Fujitsu         H.Tomimatsu     Update          S21_NA#25192
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#25151
 * 2018/06/14   Fujitus         K.Ishizuka      Update          S21_NA#24294
 * 2018/06/18   Fujitsu         H.Kumagai       Update          QC#14307
 * 2018/06/26   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 2018/07/09   Fujitsu         S.Takami        Update          S21_NA#26895
 * 2018/07/10   Fujitsu         T.Noguchi       Update          S21_NA#26661
 * 2018/07/13   Fujitsu         S.Takami        Update          S21_NA#27228
 * 2018/07/30   Fujitsu         K.Ishizuka      Update          S21_NA#26181
 * 2018/08/21   Fujitsu         S.Takami        Update          S21_NA#26767
 * 2018/09/25   Fujitsu         K.Ishizuka      Update          S21_NA#28482
 * 2018/12/10   Fujitsu         M.Ohno          Update          S21_NA#29315
 * 2019/01/08   Fujitsu         K.Kato          Update          QC#29241
 * 2018/02/21   Fujitsu         R.Nakamura      Update          S21_NA#29713
 * 2018/03/11   Fujitsu         R.Nakamura      Update          S21_NA#30629
 * 2019/03/12   Fujitsu         Hd.Sugawara     Update          QC#30730
 * 2019/04/08   Fujitsu         K.Ishizuka      Update          S21_NA#31111
 * 2019/04/24   Fujitsu         Hd.Sugawara     Update          QC#31125,QC#31126
 * 2019/11/05   Fujitsu         A.KAZUKI        Update          QC#54211
 * 2019/11/27   Fujitsu         K.Kato          Update          QC#52339
 * 2019/12/14   Fujitsu         Mz.Takahashi    Update          QC#53588
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * </pre>
 */
public class NWXC150001Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWXC150001Query MY_INSTANCE = new NWXC150001Query();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /**
     * @return MY_INSTANCE
     */
    protected static NWXC150001Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * getDefaultLocPmtTerm
     * @param glblCmpyCd    glblCmpyCd
     * @param billToCustCd  billToCustCd
     * @return default payment term cash discount code.
     * </pre>
     */
    protected String getDefaultLocPmtTerm(String glblCmpyCd, String billToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getDefaultLocPmtTerm", ssmParam);
        if (map == null) {
            return "";
        }

        return map.get("PMT_TERM_CASH_DISC_CD");

    }

    /**
     * getDefaultAcctPmtTerm
     * @param glblCmpyCd glblCmpyCd
     * @param dsAcctNum acctNum
     * @return default account payment term.
     */
    protected String getDefaultAcctPmtTerm(String glblCmpyCd, String dsAcctNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getDefaultAcctPmtTerm", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get("PMT_TERM_CASH_DISC_CD");
    }

    /**
     * checkDsOrdRsn
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param dsOrdTpCd dsOrdTpCd
     * @param dsOrdRsnCd dsOrdRsnCd
     * @return number of record count.
     */
    protected Integer checkCpoOrdTpAndDsOrdRsnRelation(String glblCmpyCd, String slsDt, String dsOrdTpCd, String dsOrdRsnCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsOrdRsnCd", dsOrdRsnCd);

        return (Integer) ssmBatchClient.queryObject("checkCpoOrdTpAndDsOrdRsnRelation", ssmParam);
    }

    /**
     * checkBillTo
     * @param glblCmpyCd glblCmpyCd
     * @param billToCustCd billToCustCd
     * @param sellToCustCd sellToCustCd
     * @return number of record count.
     */
    protected Integer checkBillToRelation(String glblCmpyCd, String billToCustCd, String sellToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("sellToCustCd", sellToCustCd);

        return (Integer) ssmBatchClient.queryObject("checkBillToRelation", ssmParam);
    }

    /**
     * checkShipTo
     * @param glblCmpyCd glblCmpyCd
     * @param addShipToCustCd addShipToCustCd
     * @param shipToCustAcctCd shipToCustAcctCd
     * @return number of record count.
     */
    protected Integer checkShipToRelation(String glblCmpyCd, String addShipToCustCd, String shipToCustAcctCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToCustCd", addShipToCustCd);
        ssmParam.put("sellToCustCd", shipToCustAcctCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (Integer) ssmBatchClient.queryObject("checkShipToRelation", ssmParam);
    }

    /**
     * checkSoldTo
     * @param glblCmpyCd glblCmpyCd
     * @param soldToCustLocCd soldToCustLocCd
     * @param sellToCustCd sellToCustCd
     * @return number of record count.
     */
    protected Integer checkSoldToRelation(String glblCmpyCd, String soldToCustLocCd, String sellToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", soldToCustLocCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("sellToCustCd", sellToCustCd);

        return (Integer) ssmBatchClient.queryObject("checkSoldToRelation", ssmParam);
    }

    /**
     * checkShpgSvcLvlRelation
     * @param glblCmpyCd glblCmpyCd
     * @param addShpgSvcLvlCd addShpgSvcLvlCd
     * @param carrSvcLvlCd carrSvcLvlCd
     * @return number of record count.
     */
    protected Integer checkCarrSvcLvlAndShpgSvcLvlRelation(String glblCmpyCd, String addShpgSvcLvlCd, String carrSvcLvlCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shpgSvcLvlCd", addShpgSvcLvlCd);
        ssmParam.put("carrSvcLvlCd", carrSvcLvlCd);

        return (Integer) ssmBatchClient.queryObject("checkCarrSvcLvlAndShpgSvcLvlRelation", ssmParam);
    }

    /**
     * checkFrtCondSvcLvlRelation
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param dsOrdTpCd dsOrdTpCd
     * @param frtCondCd frtCondCd
     * @param addShpgSvcLvlCd addShpgSvcLvlCd
     * @param carrSvcLvlCd carrSvcLvlCd
     * @param dsBizAreaCd dsBizAreaCd
     * @return number of record count.
     */
    protected Integer checkFrtCondSvcLvlRelation(//
            String glblCmpyCd //
            , String slsDt //
            , String dsOrdTpCd //
            , String frtCondCd //
            , String addShpgSvcLvlCd //
            , String carrSvcLvlCd //
            , String dsBizAreaCd //
            , String dsAcctNum //
            , String locNum
            , String lineBizTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("frtCondCd", frtCondCd);
        ssmParam.put("shpgSvcLvlCd", addShpgSvcLvlCd);
        ssmParam.put("carrSvcLvlCd", carrSvcLvlCd);
        // 2018/12/10 S21_NA#29315 Add Start
        ssmParam.put("dsBizAreaCd", dsBizAreaCd);
        if (!FRT_COND.COLLECT.equals(frtCondCd)) {
            ssmParam.put("notCollect", ZYPConstant.FLG_ON_Y);
        }
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("locNum", locNum);
        ssmParam.put("lineBizTpCd", lineBizTpCd);
        // 2018/12/10 S21_NA#29315 Add End

        return (Integer) ssmBatchClient.queryObject("checkFrtCondSvcLvlRelation", ssmParam);
    }

    /**
     * checkCsmpRelation
     * @param glblCmpyCd glblCmpyCd
     * @param csmpNum csmpNum
     * @param dsAcctNum dsAcctNum
     * @return number of record count.
     */
    protected Integer checkCsmpRelation(String glblCmpyCd, String csmpNum, String dsAcctNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("csmpNum", csmpNum);
        ssmParam.put("dsAcctNum", dsAcctNum);

        return (Integer) ssmBatchClient.queryObject("checkCsmpRelation", ssmParam);
    }

    /**
     * checkCsaNumRelation
     * @param glblCmpyCd glblCmpyCd
     * @param dlrRefNum dlrRefNum
     * @param dsAcctNum dsAcctNum
     * @return number of record count.
     */
    protected Integer checkCsaNumRelation(String glblCmpyCd, String dlrRefNum, String dsAcctNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dlrRefNum", dlrRefNum);
        ssmParam.put("dsAcctNum", dsAcctNum);

        return (Integer) ssmBatchClient.queryObject("checkCsaNumRelation", ssmParam);
    }

    /**
     * checkSalesRepRelation
     * @param glblCmpyCd glblCmpyCd
     * @param slsRepCd slsRepCd
     * @return number of record count.
     */
    protected Integer checkSalesRepRelation(String glblCmpyCd, String slsRepCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsRepCd", slsRepCd);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);

        return (Integer) ssmBatchClient.queryObject("checkSalesRepRelation", ssmParam);
    }

    /**
     * checkConfigShiptoLocation
     * @param glblCmpyCd glblCmpyCd
     * @param svcConfigMstrPk svcConfigMstrPk
     * @return number of record count.
     */
    protected String checkConfigShiptoLocation(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // ssmParam.put("svcConfigMstrPk",
        // svcConfigMstrPk.toString()); 2016/04/22 S21_NA#7449 Del
        ssmParam.put("svcConfigMstrPk", String.valueOf(svcConfigMstrPk)); // 2016/04/22
        // S21_NA#7449
        // Add

        String val = (String) ssmBatchClient.queryObject("checkConfigShiptoLocation", ssmParam);
        if (ZYPCommonFunc.hasValue(val)) {
            return val;
        }
        return "";
    }

    /**
     * checkRetailWhRelation
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param dsOrdTpCd dsOrdTpCd
     * @param rtlWhCd rtlWhCd
     * @param rtlSwhCd rtlSwhCd
     * @return number of record count.
     */
    protected Integer checkRetailWhRelation(String glblCmpyCd, String slsDt, String dsOrdTpCd, String rtlWhCd, String rtlSwhCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("rtlSwhCd", rtlSwhCd);

        return DataCacheForSSM.getInstance().getRetailWhRelationCnt(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#8166
        // Mod
    }

    /**
     * getSerNumInfo
     * @param glblCmpyCd glblCmpyCd
     * @param serNum serNum
     * @param svcMachMstrPk svcMachMstrPk
     * @param mdseCd mdseCd
     * @return serial number information.
     */
    // 2016/06/02 S21_NA#9273 Modify Start
    // protected Map<String, Object> getSerNumInfo(String glblCmpyCd,
    // String serNum) {
    protected Map<String, Object> getSerNumInfo(String glblCmpyCd, String serNum, BigDecimal svcMachMstrPk, String mdseCd) {
        // Map<String, String> ssmParam = new HashMap<String,
        // String>();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("mdseCd", mdseCd);

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) ssmBatchClient.queryObject("getSerNumInfo", ssmParam);
        return map;
    }

    // 2016/06/02 S21_NA#9273 Modify End

    /**
     * getRtlSwhInfo
     * @param glblCmpyCd glblCmpyCd
     * @param invtyLocCd invtyLocCd
     * @return Retail SubWH information.
     */
    protected Map<String, String> getRtlSwhInfo(String glblCmpyCd, String invtyLocCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("invtyLocCd", invtyLocCd);

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getRtlSwhInfo", ssmParam);
        return map;
    }

    // 2017/06/14 S21_NA#18623 Mod Interface Start
    // 2016/07/04 S21_NA#1698,3235 Modify Start
    /**
     * checkSbstRelation
     * @param glblCmpyCd global company code
     * @param mdse8LenCd order take merchandise code for order item
     * @param sbstMdse8LenCd order take merchandise code for substitution item
     * @param mdseFullCd merchandise code for order item
     * @param sbstMdseFullCd merchandise code for substitution item
     * @return list of substitution merchandise code
     */
//    protected List<?> getSbstRelation(String glblCmpyCd, String mdseCd, String sbstMdseCd) {
    protected List<?> getSbstRelation(String glblCmpyCd, String mdse8LenCd, String sbstMdse8LenCd, String mdseFullCd, String sbstMdseFullCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // 2017/06/14 S21_NA#18623 Mod Start
//        ssmParam.put("mdseCd", mdseCd);
//        ssmParam.put("sbstMdseCd", sbstMdseCd);
        ssmParam.put("mdse8LenCd", mdse8LenCd);
        ssmParam.put("sbstMdse8LenCd", sbstMdse8LenCd);
        ssmParam.put("mdseCd", mdseFullCd);
        ssmParam.put("sbstMdseCd", sbstMdseFullCd);
        // 2017/06/14 S21_NA#18623 Mod End
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<?>) ssmBatchClient.queryObjectList("getSbstRelation", ssmParam);
    }

    // 2016/07/04 S21_NA#1698,3235 Modify End
    // 2017/06/14 S21_NA#18623 Mod Interface End

    /**
     * getLineStatus
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @param dsOrdPosnNum dsOrdPosnNum
     * @param dsCpoLineNum dsCpoLineNum
     * @return order line status code.
     */
    protected String getLineStatus(//
            String glblCmpyCd //
            , String cpoOrdNum //
            , String dsOrdPosnNum //
            , BigDecimal dsCpoLineNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("dsOrdPosnNum", dsOrdPosnNum);
        ssmParam.put("dsCpoLineNum", dsCpoLineNum);

        return DataCacheForSSM.getInstance().getLineStatus(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#10321
        // Mod
    }

    /**
     * getLineStatusForInbound
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @param dsOrdPosnNum dsOrdPosnNum
     * @param dsCpoLineNum dsCpoLineNum
     * @param dsCpoLineSubNum dsCpoLineSubNum
     * @return order line status code.
     */
    protected String getLineStatusForInbound(//
            String glblCmpyCd //
            , String cpoOrdNum //
            , String dsOrdPosnNum //
            , BigDecimal dsCpoLineNum //
            , BigDecimal dsCpoLineSubNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("dsOrdPosnNum", dsOrdPosnNum);
        ssmParam.put("dsCpoLineNum", dsCpoLineNum);
        ssmParam.put("dsCpoLineSubNum", dsCpoLineSubNum);

        return DataCacheForSSM.getInstance().getLineStatusForInbound(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#10321
        // Mod
    }

    /**
     * getDealWhMapping
     * @param glblCmpyCd glblCmpyCd
     * @return deal WH information.
     */
    protected List<Map<String, String>> getDealWhMapping(String glblCmpyCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("forceDummyWh", FORCE_DUMMY_WH);

        return DataCacheForSSM.getInstance().getDealWhList(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#8166
        // Mod
    }

    /**
     * getAllocQty
     * @param glblCmpyCd glblCmpyCd
     * @param trxHdrNum trxHdrNum
     * @param trxLineNum trxLineNum
     * @param trxLineSubNum trxLineSubNum
     * @return allocated qty.
     */
    protected BigDecimal getAllocQty(String glblCmpyCd, String trxHdrNum, String trxLineNum, String trxLineSubNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("trxHdrNum", trxHdrNum);
        ssmParam.put("trxLineNum", trxLineNum);
        ssmParam.put("trxLineSubNum", trxLineSubNum);
        ssmParam.put("validated", SHPG_STS.VALIDATED);
        ssmParam.put("cancelled", SHPG_STS.CANCELLED);
        // Add Start 2019/02/21 QC#29713
        ssmParam.put("poCancelled", SHPG_STS.P_OR_O_CANCELLED);
        // Add End 2019/02/21 QC#29713
        // Add Start 2019/03/11 QC#30629
        ssmParam.put("soCancelled", SHPG_STS.S_OR_O_CANCELLED);
        // Add End 2019/03/11 QC#30629

        // 2019/11/27 QC#52339 Add Start 
        ssmParam.put("invoiced", SHPG_STS.INVOICED);
        ssmParam.put("invtyCtrlFlgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("invtyCtrlFlgN", ZYPConstant.FLG_OFF_N);
        // 2019/11/27 QC#52339 Add End

        return (BigDecimal) ssmBatchClient.queryObject("getAllocQty", ssmParam);
    }

    /**
     * checkDiChecked
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @param cpoUpdVrsnNum cpoUpdVrsnNum
     * @return number of record count.
     */
    protected Integer checkDiChecked(String glblCmpyCd, String cpoOrdNum, BigDecimal cpoUpdVrsnNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        // ssmParam.put("cpoUpdVrsnNum", cpoUpdVrsnNum.toString());
        // 2015/12/24 S21_NA#2416 Mod
        ssmParam.put("cpoUpdVrsnNum", String.valueOf(cpoUpdVrsnNum)); // 2015/12/24
        // S21_NA#2416
        // Mod

        return (Integer) ssmBatchClient.queryObject("checkDiChecked", ssmParam);
    }

    /**
     * getActiveLineCnt
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @return number of record count.
     */
    protected Integer getActiveLineCnt(String glblCmpyCd, String cpoOrdNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("cancelled", ORD_LINE_STS.CANCELLED);

        return (Integer) ssmBatchClient.queryObject("getActiveLineCnt", ssmParam);
    }

    /**
     * getPostCnt
     * @param glblCmpyCd glblCmpyCd
     * @param postCd postCd
     * @return number of record count.
     */
    protected Integer getPostCnt(String glblCmpyCd, String postCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("postCd", postCd);

        return (Integer) ssmBatchClient.queryObject("getPostCnt", ssmParam);
    }

    /**
     * getAcctCnt
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param acctCd acctCd
     * @return number of record count.
     */
    protected Integer getAcctCnt(String glblCmpyCd, String slsDt, String acctCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("acctCd", acctCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return DataCacheForSSM.getInstance().getAcctCnt(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#8166
        // Mod
    }

    /**
     * getSoldToCnt
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param soldToLocCd soldToLocCd
     * @return number of record count.
     */
    protected Integer getSoldToCnt(String glblCmpyCd, String slsDt, String soldToLocCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("soldToLocCd", soldToLocCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (Integer) ssmBatchClient.queryObject("getSoldToCnt", ssmParam);
    }

    /**
     * getAssnPgmCnt
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param assnPgmCd assnPgmCd
     * @return number of record count.
     */
    protected Integer getAssnPgmCnt(String glblCmpyCd, String slsDt, String assnPgmCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("assnPgmCd", assnPgmCd);
        ssmParam.put("slsDt", slsDt);

        return (Integer) ssmBatchClient.queryObject("getAssnPgmCnt", ssmParam);
    }

    /**
     * getDsAcctCnt
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param dsAcctNum dsAcctNum
     * @return number of record count.
     */
    protected Integer getDsAcctCnt(String glblCmpyCd, String slsDt, String dsAcctNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return DataCacheForSSM.getInstance().getDsAcctCnt(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#8166
        // Mod
    }

    /**
     * getShipToCustCnt
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param shipToCustCd shipToCustCd
     * @return number of record count.
     */
    protected Integer getShipToCustCnt(String glblCmpyCd, String slsDt, String shipToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (Integer) ssmBatchClient.queryObject("getShipToCustCnt", ssmParam);
    }

    /**
     * getBillToCustCnt
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param billToCustCd billToCustCd
     * @return number of record count.
     */
    protected Integer getBillToCustCnt(String glblCmpyCd, String slsDt, String billToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (Integer) ssmBatchClient.queryObject("getBillToCustCnt", ssmParam);
    }

    /**
     * getLineBizTpCd
     * @param glblCmpyCd
     * @param slsDt
     * @param dsOrdTpCd
     * @return default LOB.
     */
    protected String getLineBizTpCd(String glblCmpyCd, String slsDt, String dsOrdTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);

        return (String) ssmBatchClient.queryObject("getLineBizTpCd", ssmParam);
    }

    protected Integer getEzPackCtxCnt(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("easyPack1", EAZY_PACK1);
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);

        return (Integer) ssmBatchClient.queryObject("getEzPackCtxCnt", ssmParam);
    }

    // QC#14021
    // protected Integer getRetailEquipOrderCtxCnt(String glblCmpyCd,
    // String dsOrdCatgCd) {
    protected Integer getRetailEquipOrderCtxCnt(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordCatgCtxTpCd", RETAIL_EQUIP_ORDER);
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("dsOrdRsnCd", dsOrdRsnCd);

        // 2018/05/20 S21_NA#25604 Mod Start
//        return (Integer) ssmBatchClient.queryObject("getRetailEquipOrderCtxCnt", ssmParam);
        return DataCacheForSSM.getInstance().getOrderCtxCnt(ssmParam, ssmBatchClient);
        // 2018/05/20 S21_NA#25604 Mod End
    }

    protected String getCoaMdseTpCd(String glblCmpyCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mainMach", MAIN_MACH);

        return (String) ssmBatchClient.queryObject("getCoaMdseTpCd", ssmParam);
    }

    protected Map<String, String> getDefaultHddEraseRemovalFlg(String glblCmpyCd, String prcCatgCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcCatgCd", prcCatgCd);

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getDefaultHddEraseRemovalFlag", ssmParam);
        return map;
    }

    // QC#14593 2016/09/28 Mod Start
    protected String getSvcIstlRuleFlg(String glblCmpyCd, String cpoOrdNum, Object dsCpoConfigPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("dsCpoConfigPk", dsCpoConfigPk);

        return DataCacheForSSM.getInstance().getSvcIstlRuleFlg(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#8166
        // Mod
    }

    // QC#14593 2016/09/28 Mod End

    protected String getDefaultCustIstlFlg(String glblCmpyCd, BigDecimal mdlId) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // ssmParam.put("mdlId", mdlId.toString()); 2016/04/22
        // S21_NA#7449 Del
        ssmParam.put("mdlId", String.valueOf(mdlId)); // 2016/04/22
        // S21_NA#7449
        // Add

        return DataCacheForSSM.getInstance().getCustIstlFlg(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#8166
        // Mod
    }

    protected String getDefaultShopItemFlg(String glblCmpyCd, BigDecimal mdlId) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // ssmParam.put("mdlId", mdlId.toString()); 2016/04/22
        // S21_NA#7449 Del
        ssmParam.put("mdlId", String.valueOf(mdlId)); // 2016/04/22
        // S21_NA#7449
        // Add

        return DataCacheForSSM.getInstance().getShopItemFlg(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#8166
        // Mod
    }

    // QC#14021
    // protected Integer getSrvExchCnt(String glblCmpyCd, String
    // dsOrdCatgCd) {
    protected Integer getSrvExchCnt(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordCatgCtxTpCd", SERVICE_EXCHANGE);
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        // 2018/05/20 S21_NA#25604 Mod Start
//        ssmParam.put("dsOrdTpCd", dsOrdTpCd == null ? "" : dsOrdTpCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        // 2018/05/20 S21_NA#25604 Mod End

        // 2018/05/20 S21_NA#25604 Mod Start
//        return (Integer) ssmBatchClient.queryObject("getSrvExcCnt", ssmParam);
        return DataCacheForSSM.getInstance().getOrderCtxCnt(ssmParam, ssmBatchClient);
        // 2018/05/20 S21_NA#25604 Mod End
    }

    protected Integer getSrvConfigMstrCnt(String glblCmpyCd, BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // 2016/04/22 S21_NA#7449 Mod Start
        // ssmParam.put("svcConfigMstrPk",
        // svcConfigMstrPk.toString());
        // ssmParam.put("svcMachMstrPk", svcMachMstrPk.toString());
        ssmParam.put("svcConfigMstrPk", String.valueOf(svcConfigMstrPk));
        ssmParam.put("svcMachMstrPk", String.valueOf(svcMachMstrPk));
        // 2016/04/22 S21_NA#7449 Mod End

        return (Integer) ssmBatchClient.queryObject("getSrvConfigMstrCnt", ssmParam);
    }

    protected Map<String, String> getOrdCatgBizCtx(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // ssmParam.put("svcMachMstrPk", svcMachMstrPk.toString());
        // 2016/04/22 S21_NA#7449 Del
        ssmParam.put("svcMachMstrPk", String.valueOf(svcMachMstrPk)); // 2016/04/22
        // S21_NA#7449
        // Add
        // Start
        ssmParam.put("serviceExchangeElig", SERVICE_EXCHANGE_ELIG);

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getOrdCatgBizCtx", ssmParam);
        return map;
    }

    protected Integer getEasyPackCnt(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("easyPack1", "EASY_PACK1");
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        // QC#14021
        // ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd == null ? "" : dsOrdTpCd);

        return (Integer) ssmBatchClient.queryObject("getEasyPackCnt", ssmParam);
    }

    protected Integer getSplyPgmContrCnt(String glblCmpyCd, String slsDt, String billToCustAcctCd, String billToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("billToCustAcctCd", billToCustAcctCd);
        ssmParam.put("billToCustCd", billToCustCd);

        return (Integer) ssmBatchClient.queryObject("getSplyPgmContrCnt", ssmParam);
    }

    protected String getLineBizRoleTpCdForWriter(String glblCmpyCd, String slsDt, String dsOrdTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);

        return (String) ssmBatchClient.queryObject("getLineBizRoleTpCdForWriter", ssmParam);
    }

    public String getDefaultAcctFromShipToCust(String glblCmpyCd, String shipToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shipToCustCd", shipToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rownum", ROWNUM_1);

        return DataCacheForSSM.getInstance().getDefaultAcctFromShipToCust(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#8166
        // Mod
    }

    public String getDefaultAcctFromBillToCust(String glblCmpyCd, String billToCustCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("billToCustCd", billToCustCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rownum", ROWNUM_1);

        return DataCacheForSSM.getInstance().getDefaultAcctFromBillToCust(ssmParam, ssmBatchClient); // For
        // Performance
        // QC#8166
        // Mod
    }

    public Integer matchConfigTp(String glblCmpyCd, String configTpCd, String configCatgCd, boolean configNewFlg, boolean configExstAtCustFlg, boolean configExstWhFlg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("configTpCd", configTpCd);
        params.put("configCatgCd", configCatgCd);
        if (configNewFlg) {
            params.put("configNewFlg", ZYPConstant.FLG_ON_Y);
        }
        if (configExstAtCustFlg) {
            params.put("configExstAtCustFlg", ZYPConstant.FLG_ON_Y);
        }
        if (configExstWhFlg) {
            params.put("configExstWhFlg", ZYPConstant.FLG_ON_Y);
        }

        return (Integer) ssmBatchClient.queryObject("matchConfigTp", params);
    }

    // 2016/01/13 S21_NA#2726 Add Start
    public Integer getIbTrackableNotInSvcConfig(String glblCmpyCd, String mdseCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmParam.put("flgOn", ZYPConstant.FLG_ON_Y);

        return (Integer) ssmBatchClient.queryObject("getIbTrackableNotInSvcConfig", ssmParam);
    }

    // 2016/01/13 S21_NA#2726 Add End
    // 2016/01/25 S21_NA#3505 Add Start
    public String getMachUsgStatus(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        // QC#22651 2017/11/15 Add Start
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return null;
        }
        // QC#22651 2017/11/15 Add End
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);

        return (String) ssmBatchClient.queryObject("getMachUsgStatus", ssmParam);
    }

    // 2016/01/25 S21_NA#3505 Add End
    
    // 2018/09/25 S21_NA#28482 Add Start
    public String getMachMstrStatus(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);

        return (String) ssmBatchClient.queryObject("getMachMstrStatus", ssmParam);
    }
    // 2018/09/25 S21_NA#28482 Add End

    // 2018/05/18 S21_NA#25192 Add Start
    public Integer getTrxHdrNumMatchCnt(String glblCmpyCd, String cpoOrdNum, BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);

        return (Integer) ssmBatchClient.queryObject("getTrxHdrNumMatchCnt", ssmParam);
    }
    // 2018/05/18 S21_NA#25192 Add End

    // 2016/05/20 S21_NA#5335 Add Start
    /**
     * getActiveLineCnt
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @return number of record count.
     */
    protected Integer getActiveRmaLineCnt(String glblCmpyCd, String cpoOrdNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("cancelled", RTRN_LINE_STS.CANCELLED);

        return (Integer) ssmBatchClient.queryObject("getActiveRmaLineCnt", ssmParam);
    }

    // 2016/05/20 S21_NA#5335 Add End

    protected BigDecimal getSvcMachMstrPkBySerNumAndMdseCd(String glblCmpyCd, String slsDt, String serNum, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd);

        // 2018/08/21 S21_NA#26767 Del Start
//        return (BigDecimal) ssmBatchClient.queryObject("getSvcMachMstrPkBySerNumAndMdseCd", ssmParam);
        // 2018/08/21 S21_NA#26767 Del End
        // 2018/08/21 S21_NA#26767 Add Start
        return DataCacheForSSM.getInstance().getSvcMachMstrPkBySerNumAndMdseCd(ssmParam, ssmBatchClient);
        // 2018/08/21 S21_NA#26767 Add End
    }

    protected Map<String, Object> getBaseComponentInformation(String glblCmpyCd, String slsDt, BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPkForComponent) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmParam.put("svcMachMstrPkForComponent", svcMachMstrPkForComponent);
        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getBaseComponentInformation", ssmParam);
        return result;
    }

    protected String getOvrdPmtTermFlg(String glblCmpyCd, String dsAcctNum, String billToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("billToCustCd", billToCustCd);
        String result = (String) ssmBatchClient.queryObject("getOvrdPmtTermFlg", ssmParam);
        return result;
    }

    protected String getCashOrCcReqFlg(String glblCmpyCd, String dsAcctNum, String billToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("billToCustCd", billToCustCd);
        String result = (String) ssmBatchClient.queryObject("getCashOrCcReqFlg", ssmParam);
        return result;
    }

    // 2017/11/16 S21_NA#22620 Mod Start
    // 2017/11/02 S21_NA#20146 Add Start
    /**
     * getOrdCatgBizCtxFstAttbTxt.
     * @param glblCmpyCd String
     * @param ordCatgCtxTpCd String
     * @param dsOrdCatgCd String
     * @param dsOrdTpCd String
     * @return String (Y or Null)
     */
    protected String getOrdCatgBizCtxFstAttbTxt(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {
        return getOrdCatgBizCtxFstAttbTxt(glblCmpyCd, ordCatgCtxTpCd, dsOrdCatgCd, dsOrdTpCd, null);
    }
    // 2017/11/02 S21_NA#20146 Add End
    // 2018/05/20 S21_NA#25604 Add Start
    protected String getOrdCatgBizCtxFstAttbTxt(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            return "";
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", dsOrdCatgCd);
        params.put("dsOrdTpCd", dsOrdTpCd);
        params.put("dsOrdTpCd", dsOrdRsnCd);

        String result = (String) ssmBatchClient.queryObject("getOrdCatgBizCtxFstAttbTxt", params);
        return result;
    }
    // 2018/05/20 S21_NA#25604 Add End
    // 2017/11/16 S21_NA#22620 Mod End

    // QC#21471
    protected Integer existsCfsBillToAcct(String glblCmpyCd, String acctCd, String slsDt) {
        String cfsCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue("CFS_COA_AFFL_CD", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(acctCd) || !ZYPCommonFunc.hasValue(cfsCoaAfflCd)) {
            return 0;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("acctCd", acctCd);
        params.put("cfsCoaAfflCd", cfsCoaAfflCd);
        params.put("slsDt", slsDt);
        params.put("rgtnStsReadyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (Integer) ssmBatchClient.queryObject("existsCfsAcct", params);
    }
    // 2017/12/26 S21_NA#22986 Add Start
    /**
     * get Minimum code of Order Line Source Code, not ITT_HLD
     * @param pMsg NWZC150001 API Parameter Message
     * @return Minimum ORD_LINE_SRC_CD (not ITT_HLD))
     */
    public String getMinOrdLineSrcNotIttHld(String glblCmpyCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("flgOffN", ZYPConstant.FLG_OFF_N);

        return (String) ssmBatchClient.queryObject("getMinOrdLineSrcNotIttHld", params);
    }
    // 2017/12/26 S21_NA#22986 Add End

    // 2018/02/13 S21_NA#22717 add start
    protected String getAssetCratProcFlg(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineCatgCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsOrdTpCd",dsOrdTpCd);
        params.put("dsOrdLineCatgCd", dsOrdLineCatgCd);

        return (String) ssmBatchClient.queryObject("getAssetCratProcFlg", params);
    }

    protected int existsAssetCratTrgt(String glblCmpyCd, String coaMdseTpCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseTpCtxTpCd", MDSE_TP_CTX_TP.ASSET_CREATION_TARGET);
        params.put("coaMdseTpCd", coaMdseTpCd);

        return (Integer) ssmBatchClient.queryObject("existsAssetCratTrgt", params);
    }
    // 2018/02/13 S21_NA#22717 add start

    // 2018/03/28 S21_NA#24853 Add Start
    protected String getInternalOrdProcFlg(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineCatgCd, String slsDt) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsOrdTpCd", dsOrdTpCd);
        params.put("dsOrdLineCatgCd", dsOrdLineCatgCd);
        params.put("slsDt", slsDt);

        // 2018/06/05 S21_NA#25151 Mod Start
//        return (String) ssmBatchClient.queryObject("getInternalOrdProcFlg", params);
        return DataCacheForSSM.getInstance().getInternalOrdProcFlg(params, ssmBatchClient);
        // 2018/06/05 S21_NA#25151 Mod End
    }
    // 2018/03/28 S21_NA#24853 Add End
    // 2018/05/20 S21_NA#25604 Add Start
    /**
     * <pre>
     * @param glblCmpyCd Global Company Code
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @param dsOrdCatgCd DS Order Category Code
     * @param dsOrdTpCd DS Order TYpe Code
     * @param dsOrdRsnCd DS Order Reason Code
     * @return count of result records.
     * </pre>
     */
    protected Integer getOrderCtxCnt(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("dsOrdRsnCd", dsOrdRsnCd);

        // 2018/05/20 S21_NA#25604 Mod Start
//        return (Integer) ssmBatchClient.queryObject("getRetailEquipOrderCtxCnt", ssmParam);
        return DataCacheForSSM.getInstance().getOrderCtxCnt(ssmParam, ssmBatchClient);
        // 2018/05/20 S21_NA#25604 Mod End
    }
    // 2018/05/20 S21_NA#25604 Add Start
    
    // 2018/06/14 S21_NA#24294 Add Start
    /**
     * <pre>
     * @param glblCmpyCd Global Company Code
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @param dsOrdCatgCd DS Order Category Code
     * @param dsOrdTpCd DS Order TYpe Code
     * @return count of result records.
     * </pre>
     */
    protected Map<String, String> getWhMpngTmpl(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.DEFAULT_WAREHOUSE_TEMPLATE);
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);

        return DataCacheForSSM.getInstance().getWhMpngTmpl(ssmParam, ssmBatchClient);
    }
    // 2018/06/14 S21_NA#24294 Add Start

    // 2018/06/18 QC#14307 Add Start
    /**
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public Map<String, Object> getAcctNum(String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("dsAcctNum", dsAcctNum);

        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getAcctNum", ssmParam);
        return result;
    }

    /**
     * @param billToCustCd String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public Map<String, Object> getLocNumFromBill(String billToCustCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("billToCustCd", billToCustCd);

        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getLocNumFromBillTo", ssmParam);
        return result;
    }

    /**
     * @param shipToCustCd String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public Map<String, Object> getLocNumFromShip(String shipToCustCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("shipToCustCd", shipToCustCd);

        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getLocNumFromShipTo", ssmParam);
        return result;

    }

    /**
     * getAcctRefAttrb
     * @param locNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getAcctRefAttrbForLoc(String locNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);

        return getSsmEZDClient().queryObjectList("getAcctRefAttrb", ssmParam);
    }

    /**
     * getAcctRefAttrb
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getAcctRefAttrbForAcct(String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObjectList("getAcctRefAttrb", ssmParam);
    }

    /**
     * getSpclHdlg
     * @param dsAcctNum String
     * @param salesDate String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getSpclHdlg(String dsAcctNum, String salesDate, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("salesDate", salesDate);

        return getSsmEZDClient().queryObjectList("getSpclHdlg", ssmParam);
    }

    /**
     * chkHrchEffLvlTp
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public int chkHrchEffLvlTp(String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        int result = (Integer) ssmBatchClient.queryObject("cntHrchEffLvlTp", ssmParam);

        return result;
    }

    /**
     * chkDisplayForTrxDriver
     * @param funcID String
     * @param funcCatgID String
     * @param glblCmpyCd String
     * @param custSpclInstnCtxTpCd String
     * @return Map<String, String>
     */
    public int chkDisplay(String funcID, String funcCatgID, String custSpclInstnCtxTpCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custSpclInstnCtxTpCd", custSpclInstnCtxTpCd);
        ssmParam.put("funcMstrId_S", funcID);
        ssmParam.put("funcMstrIdDescTxt_S", funcCatgID);

        int result = (Integer) ssmBatchClient.queryObject("cntDisplay", ssmParam);

        return result;
    }

    /**
     * getDisplayAvalCdForTrxDriver
     * @param custSpclInstnCtxTpCd String
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> getDisplayAvalCd(String custSpclInstnCtxTpCd, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custSpclInstnCtxTpCd", custSpclInstnCtxTpCd);

        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDisplayAvalCd", ssmParam);

        return result;
    }

    /**
     * getDisplayAvalFlgForTrxDriver
     * @param funcID String
     * @param funcCatgID String
     * @param custSpclInstnCtxTpCd String
     * @param glblCmpyCd String
     * @return  Map<String, Object>
     */
    public Map<String, Object> getDisplayAvalFlgForTrxDriver(String funcID, String funcCatgID, String custSpclInstnCtxTpCd, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custSpclInstnCtxTpCd", custSpclInstnCtxTpCd);
        ssmParam.put("funcMstrId_S", funcID);
        ssmParam.put("funcMstrIdDescTxt_S", funcCatgID);

        Map<String, Object> result = (Map<String, Object>) ssmBatchClient.queryObject("getDisplayAvalFlg", ssmParam);

        return result;
    }

    /**
     * getLobTypeWithMessageType
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getLobTypeWithMessageType(String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custMsgTpCd", DS_CUST_MSG_TP.NOTE);

        return getSsmEZDClient().queryObjectList("getLobTypeWithMessageType", ssmParam);
    }

    /**
     * @param cMsg NMAL3300CMsg
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getCustSpclInstnForAcct(String funcID, String funcCatgID, String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("searchKey", dsAcctNum);
        ssmParam.put("selectAcctFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("selectLocFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("custSpclInstnCtxTpCd", "01");
        ssmParam.put("funcMstrId_S", funcID);
        ssmParam.put("funcMstrIdDescTxt_S", funcCatgID);

        return getSsmEZDClient().queryObjectList("getCustSpclInstn", ssmParam);

    }

    /**
     * @param cMsg NMAL3300CMsg
     * @param locNum String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    public S21SsmEZDResult getCustSpclInstnForLoc(String funcID, String funcCatgID, String locNum, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("searchKey", locNum);
        ssmParam.put("selectAcctFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("selectLocFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("custSpclInstnCtxTpCd", "01");
        ssmParam.put("funcMstrId_S", funcID);
        ssmParam.put("funcMstrIdDescTxt_S", funcCatgID);

        return getSsmEZDClient().queryObjectList("getCustSpclInstn", ssmParam);

    }

    /**
     * getCustSpclMsgForLoc
     * @param funcID String
     * @param funcCatgID String
     * @param locNum String
     * @param glblCmpyCd String
     * @param lineBizTpCd String
     * @return S21SsmEZDResult
     */
    // 2018/07/10 S21_NA#26661 Mod Start
    //public S21SsmEZDResult getCustSpclMsgForLoc(String funcID, String funcCatgID, String locNum, String glblCmpyCd) {
    public S21SsmEZDResult getCustSpclMsgForLoc(String funcID, String funcCatgID, String locNum, String glblCmpyCd, String lineBizTpCd) {
    // 2018/07/10 S21_NA#26661 Mod End

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("searchKey", locNum);
        ssmParam.put("custMsgTpCd", DS_CUST_MSG_TP.NOTE);
        ssmParam.put("selectAcctFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("selectLocFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("custSpclInstnCtxTpCd", "02");
        ssmParam.put("funcMstrId_S", funcID);
        ssmParam.put("funcMstrIdDescTxt_S", funcCatgID);
        ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
        // 2018/07/10 S21_NA#26661 Add Start
        ssmParam.put("lineBizTpCd_S", lineBizTpCd);
        ssmParam.put("lineBizTpAll", LINE_BIZ_TP.ALL);
        // 2018/07/10 S21_NA#26661 Add End

        setParamAttBizAplId(ssmParam);

        return getSsmEZDClient().queryObjectList("getCustSpclMsg", ssmParam);
    }

    /**
     * getCustSpclMsgForAcct
     * @param funcID String
     * @param funcCatgID String
     * @param locNum String
     * @param glblCmpyCd String
     * @param lineBizTpCd String
     * @return S21SsmEZDResult
     */
    // 2018/07/10 S21_NA#26661 Mod Start
    // public S21SsmEZDResult getCustSpclMsgForAcct(String funcID, String funcCatgID, String dsAcctNum, String glblCmpyCd) {
    public S21SsmEZDResult getCustSpclMsgForAcct(String funcID, String funcCatgID, String dsAcctNum, String glblCmpyCd, String lineBizTpCd) {
    // 2018/07/10 S21_NA#26661 Mod End

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("searchKey", dsAcctNum);
        ssmParam.put("custMsgTpCd", DS_CUST_MSG_TP.NOTE);
        ssmParam.put("selectAcctFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("selectLocFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("custSpclInstnCtxTpCd", "02");
        ssmParam.put("funcMstrId_S", funcID);
        ssmParam.put("funcMstrIdDescTxt_S", funcCatgID);
        ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
        // 2018/07/10 S21_NA#26661 Add Start
        ssmParam.put("lineBizTpCd_S", lineBizTpCd);
        ssmParam.put("lineBizTpAll", LINE_BIZ_TP.ALL);
        // 2018/07/10 S21_NA#26661 Add End

        setParamAttBizAplId(ssmParam);

        return getSsmEZDClient().queryObjectList("getCustSpclMsg", ssmParam);

    }

    private void setParamAttBizAplId(Map<String, Object> ssmParam) {
        List<String> list = new ArrayList<String>();
        list.add("NMAL6700");
        list.add("NMAL6720");
        ssmParam.put("attBizAplIds", list);
    }

    // 2018/06/18 QC#14307 Add End

    // QC#23726 2018/06/25 add Start
    protected List<String> getCustCarrScvLvlReln(String glblCmpyCd, String dsBizAreaCd, String dsAcctNum) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsBizAreaCd", dsBizAreaCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        return DataCacheForSSM.getInstance().getCustCarrScvLvlReln(ssmParam, ssmBatchClient);
    }
    protected String getDsBizArea(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        List<String> ordCatgCtxTpCdList = new ArrayList<String>();
        ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        ssmParam.put("ordCatgCtxTpCdList", ordCatgCtxTpCdList.toArray(new String[0]));
        ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        ssmParam.put("dsOrdTpCd",   dsOrdTpCd);
        ssmParam.put("dsOrdRsnCd",  dsOrdRsnCd);

        return DataCacheForSSM.getInstance().getDsBizArea(ssmParam, ssmBatchClient);
    }
    // QC#23726 2018/06/25 add End

    // 2018/07/09 S21_NA#26895 Add Start
    protected List<String> getOverWriteExemptRtlWhCdList(String glblCmpyCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        return DataCacheForSSM.getInstance().getOverWriteExemptRtlWhCdList(ssmParam, ssmBatchClient);
    }
    // 2018/07/09 S21_NA#26895 Add End
    // 2018/07/13 S21_NA#27228 Add Start
    protected List<String> getOverWriteExemptBaseRtlWhCdList(String glblCmpyCd) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);

        return DataCacheForSSM.getInstance().getOverWriteExemptBaseRtlWhCdList(ssmParam, ssmBatchClient);
    }
    // 2018/07/13 S21_NA#27228 Add Start

    // 2018/07/30 S21_NA#26181 Add Start
    protected Integer alreadyExistOpenRmaLine(String glblCmpyCd, BigDecimal svcMachMstrPk, String serNum, String mdseCd, String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMastrPk", svcMachMstrPk);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("openFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("rtrnLineStsCd", RTRN_LINE_STS.BOOKED);

        return (Integer) ssmBatchClient.queryObject("alreadyExistOpenRmaLine", ssmParam);
    }
    // 2018/07/30 S21_NA#26181 Add End
    
    // QC#26410
    protected String isAvalableItemForOrderLine(String glblCmpyCd, String mdseCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("OPTIMA_DEFERRAL", DFRD_ACCTG_RULE.OPTIMA_DEFERRAL);
        ssmParam.put("SUBSCRIPTION_SERVICE", DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE);
        // 2019/11/05 QC#54211 Add End
        ssmParam.put("DEFERRED", DFRD_ACCTG_RULE.DEFERRED);
        // 2019/11/05 QC#54211 Add End

        return (String) ssmBatchClient.queryObject("isAvalableItemForOrderLine", ssmParam);
    }

    // 2018/08/21 S21_NA#26767 Add Start

    protected Map<String, Object> getSvcMachMstrPkAndConfigIdBySerNumAndMdseCd(String glblCmpyCd, String slsDt, String serNum, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd);

        return DataCacheForSSM.getInstance().getSvcMachMstrPkAndConfigIdBySerNumAndMdseCd(ssmParam, ssmBatchClient);
    }

    protected List<BigDecimal> getSvcMachMstrPkByConfigIdAndMdseCd(String glblCmpyCd, String slsDt, BigDecimal svcConfigMstrPk, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmParam.put("mdseCd", mdseCd);

        return DataCacheForSSM.getInstance().getSvcMachMstrPkByConfigIdAndMdseCd(ssmParam, ssmBatchClient);
    }
    // 2018/08/21 S21_NA#26767 Add End

    // 2018/12/10 S21_NA#29315 Add Start
    protected List<Map<String, Object>> getOrdCatgBizCtxList(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd) {

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("OrdCatgCtxTpCd", ordCatgCtxTpCd);
        mapParam.put("dsOrdCatgCd", dsOrdCatgCd);
        List<Map<String, Object>> resMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrdCatgBizCtx", mapParam);

        return resMapList;
    }
    // 2018/12/10 S21_NA#29315 Add End

    // QC#29248
    protected Integer cntBillableCounter(String glblCmpyCd, BigDecimal mdlId, BigDecimal prcMtrPkgPk, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdlId", mdlId);
        ssmParam.put("prcMtrPkgPk", prcMtrPkgPk);
        ssmParam.put("slsDt", slsDt);

        return (Integer) ssmBatchClient.queryObject("cntBillableCounter", ssmParam);
    }

    // 2019/01/08 QC#29241 Add Start
    /**
     * @param ordTMsg DS_IMPT_ORDTMsg
     * @param dtlTMsg DS_IMPT_ORD_DTLTMsg
     * @param cacheMap Map<Map<String, String>, String>
     * @return String
     */
    protected String getOrdQtyVldFlg(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineCatgCd, String slsDt) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("dsOrdLineCatgCd", dsOrdLineCatgCd);
        ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("salesDate", slsDt);

        return (String) ssmBatchClient.queryObject("getOrdQtyVldFlg", ssmParam);
    }
    // 2019/01/08 QC#29241 Add End

    // Add Start 2019/03/12 QC#30730
    /**
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return String
     */
    public String getRelatedBillTo(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("shipToCustCd", shipToCustCd);

        String result = (String) ssmBatchClient.queryObject("getRelatedBillTo", ssmParam);

        return result;
    }
    // Add End 2019/03/12 QC#30730
    
    // 2019/04/08 S21_NA#31111 Add Start
    protected Integer getConfigCnt4Exch(String glblCmpyCd, String cpoOrdNum, String configCatgCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("configCatgCd", configCatgCd);
        ssmParam.put("svcConfigMstrPk", svcConfigMstrPk);

        return (Integer) ssmBatchClient.queryObject("getConfigCnt4Exch", ssmParam);
    }
    // 2019/04/08 S21_NA#31111 Add End

    // Add Start 2019/04/24 QC#31125,QC#31126
    /**
     * get Latest Sales Rep Info
     * @param glblCmpyCd Global Company Code
     * @param slsDt Sales Date
     * @param tocCd TOC Code
     * @return Sales Rep Info
     */
    protected String getLatestSlsRepInfo(String glblCmpyCd, String slsDt, String tocCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("slsDt", slsDt);
        params.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        params.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        params.put("rgtnStsCd", RGTN_STS.TERMINATED);
        params.put("tocCd", tocCd);

        return (String) ssmBatchClient.queryObject("getLatestSlsRepInfo", params);
    }
    // Add End 2019/04/24 QC#31125,QC#31126

    // 2019/12/14 QC#53588 Add Start
    /**
     * getActiveLineCntEx
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @return number of record count.
     */
    protected Integer getActiveLineCntEx(String glblCmpyCd, String cpoOrdNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("cancelled", ORD_LINE_STS.CANCELLED);
        ssmParam.put("closed", ORD_LINE_STS.CLOSED);

        return (Integer) ssmBatchClient.queryObject("getActiveLineCntEx", ssmParam);
    }

    /**
     * isActiveLine
     * @param glblCmpyCd
     * @param cpoOrdNum
     * @param posNum
     * @param lineNum
     * @return
     */
    protected Integer isActiveLine(String glblCmpyCd, String cpoOrdNum, String posNum, String lineNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("dsOrdPosnNum", posNum);
        ssmParam.put("dsCpoLineNum", lineNum);
        ssmParam.put("cancelled", RTRN_LINE_STS.CANCELLED);
        ssmParam.put("closed", ORD_LINE_STS.CLOSED);

        return (Integer) ssmBatchClient.queryObject("isActiveLine", ssmParam);
    }

    /**
     * getActiveRmaLineCntEx
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @return number of record count.
     */
    protected Integer getActiveRmaLineCntEx(String glblCmpyCd, String cpoOrdNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("cancelled", RTRN_LINE_STS.CANCELLED);
        ssmParam.put("closed", ORD_LINE_STS.CLOSED);

        return (Integer) ssmBatchClient.queryObject("getActiveRmaLineCntEx", ssmParam);
    }
    // 2019/12/14 QC#53588 Add End

    // ADD START 2022/08/19 H.Watanabe [QC#60255]
    /**
     * getCarrAcctNumVld
     * @param glblCmpyCd
     * @param shpgSvcLvlCd
     * @param carrSvcLvlCd
     * @return Carrier Account Number Validation Info
     */
    protected Map<String, Object> getCarrAcctNumVld(String glblCmpyCd, String shpgSvcLvlCd, String carrSvcLvlCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        ssmParam.put("carrSvcLvlCd", carrSvcLvlCd);

        return (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNumVld", ssmParam);
    }
    // ADD END   2022/08/19 H.Watanabe [QC#60255]

}
