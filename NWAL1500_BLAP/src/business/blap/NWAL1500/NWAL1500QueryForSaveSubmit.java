package business.blap.NWAL1500;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDSMsg;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.db.CPOTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         S.Takami        Create          n/a
 * 2016/01/25   Fujitsu         S.Yamamoto      Update          S21_NA#3516
 * 2016/02/24   Fujitsu         M.Hara          Update          QC#4078
 * 2016/02/25   Fujitsu         K.Sato          Update          S21_NA#1729
 * 2016/05/11   Fujitsu         T.Yoshida       Update          S21_NA#8166
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2016/09/07   Fujitsu         Y.Taoka         Update          S21_NA#8196
 * 2016/09/13   Fujitsu         S.Takami        Update          S21_NA#8276
 * 2016/11/08   Fujitsu         S.Takami        Update          S21_NA#15427
 * 2016/11/08   Fujitsu         S.Iidaka        Update          S21_NA#7749
 * 2016/11/09   Fujitsu         S.Takami        Update          S21_NA#9864
 * 2016/11/17   Fujitsu         T.Yoshida       Update          S21_NA#12819
 * 2016/12/05   Fujitsu         T.Yoshida       Update          S21_NA#15889
 * 2017/02/15   Fujitsu         S.Iidaka        Update          S21_NA#17586
 * 2017/12/07   Fujitsu         S.Takami        Update          S21_NA#22794
 * 2018/01/31   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/12   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * 2018/10/25   Fujitsu         T.Aoi           Update          S21_NA#28897
 * 2018/11/02   Fujitsu         Hd.Sugawara     Update          S21_NA#29017
 * 2019/07/26   Fujitsu         S.Kosaka        Update          S21_NA#51941
 * 2019/11/15   Fujitsu         R.Nakamura      Update          S21_NA#54515
 * 2020/01/17   Fujitsu         A.Kazuki        Update          QC#55202
 */

public final class NWAL1500QueryForSaveSubmit extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1500QueryForSaveSubmit MY_INSTANCE = new NWAL1500QueryForSaveSubmit();

    /**
     * Constructor.
     */
    private NWAL1500QueryForSaveSubmit() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForSaveSubmit getInstance() {
        return MY_INSTANCE;
    }

    public S21SsmEZDResult checkDsOrderCategoryDescTxt(NWAL1500CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("descTxt", bizMsg.dsOrdCatgDescTxt.getValue());
        queryParam.put("tblNm", "DS_ORD_CATG");
        return getSsmEZDClient().queryObjectList("checkCodeTableDescText", queryParam);
    }

   public S21SsmEZDResult checkBillToAcctCd(NWAL1500CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("tblNm", "SELL_TO_CUST");
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
            queryParam.put("custCd", bizMsg.billToCustAcctCd.getValue());
        } else {
            queryParam.put("tblNm", "");
        }
        return getSsmEZDClient().queryObjectList("checkCustCode", queryParam);
    }

    public S21SsmEZDResult checkBillToCustCd(NWAL1500CMsg bizMsg, String billToCustCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("tblNm", "BILL_TO_CUST");
        if (ZYPCommonFunc.hasValue(billToCustCd)) {
            queryParam.put("custCd", billToCustCd);
        } else {
            queryParam.put("custCd", "");
        }
        return getSsmEZDClient().queryObjectList("checkCustCode", queryParam);
    }

    public S21SsmEZDResult checkShipToAcctCd(NWAL1500CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("tblNm", "SELL_TO_CUST");
        if (ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) {
            queryParam.put("custCd", bizMsg.shipToCustAcctCd.getValue());
        } else {
            queryParam.put("tblNm", "");
        }
        return getSsmEZDClient().queryObjectList("checkCustCode", queryParam);
    }

    public S21SsmEZDResult checkShipToCustCd(NWAL1500CMsg bizMsg, String shipToCustCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("tblNm", "SHIP_TO_CUST");
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            queryParam.put("custCd", shipToCustCd);
        } else {
            queryParam.put("custCd", "");
        }
        return getSsmEZDClient().queryObjectList("checkCustCode", queryParam);
    }

    public S21SsmEZDResult checkSoldToAcctCd(NWAL1500CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("tblNm", "SELL_TO_CUST");
        if (ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            queryParam.put("custCd", bizMsg.sellToCustCd.getValue());
        } else {
            queryParam.put("tblNm", "");
        }
        return getSsmEZDClient().queryObjectList("checkCustCode", queryParam);
    }

    public S21SsmEZDResult checkSoldToCustCd(NWAL1500CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("tblNm", "BILL_TO_CUST");
        if (ZYPCommonFunc.hasValue(bizMsg.soldToCustLocCd)) {
            queryParam.put("custCd", bizMsg.soldToCustLocCd.getValue());
        } else {
            queryParam.put("custCd", "");
        }
        return getSsmEZDClient().queryObjectList("checkCustCode", queryParam);
    }

    public S21SsmEZDResult checkPriceCatgNm(NWAL1500CMsg bizMsg, String prcCatgNm) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("prcCatgNm", prcCatgNm);
        return getSsmEZDClient().queryObjectList("checkPriceCatgNm", queryParam);
    }

    public S21SsmEZDResult checkFreightCondText(NWAL1500CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("descTxt", bizMsg.frtCondDescTxt.getValue());
        queryParam.put("tblNm", "FRT_COND");
        return getSsmEZDClient().queryObjectList("checkCodeTableDescText", queryParam);
    }

    public S21SsmEZDResult checkCarrierServiceLevelText(NWAL1500CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("descTxt", bizMsg.carrSvcLvlDescTxt.getValue());
        queryParam.put("tblNm", "CARR_SVC_LVL");
        return getSsmEZDClient().queryObjectList("checkCodeTableDescText", queryParam);
    }

    public S21SsmEZDResult checkPaymentTermCashDiscText(NWAL1500CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("descTxt", bizMsg.pmtTermCashDiscDescTxt.getValue());
        queryParam.put("tblNm", "PMT_TERM_CASH_DISC");
        return getSsmEZDClient().queryObjectList("checkCodeTableDescText", queryParam);
    }

    public S21SsmEZDResult checkAssociationProgram(NWAL1500CMsg bizMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("prcContrNum", bizMsg.prcContrNum.getValue());
        return getSsmEZDClient().queryObjectList("checkAssociationProgram", queryParam);
    }

    public S21SsmEZDResult checkModelNameForConfigLine(NWAL1500CMsg bizMsg, String mdlDescTxt) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("mdlDescTxt", mdlDescTxt);
        return getSsmEZDClient().queryObjectList("checkModelText", queryParam);
    }

    public S21SsmEZDResult checkMdseCdFromAllMdseV(NWAL1500CMsg bizMsg, String mdseCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("checkMdseCdFromAllMdseV", queryParam);
    }

//    public S21SsmEZDResult checkMachSerNum(NWAL1500CMsg bizMsg, String serNum, String mdseCd) {
//        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("serNum", serNum);
//        queryParam.put("mdseCd", mdseCd);
//        return getSsmEZDClient().queryObjectList("checkMachSerNum", queryParam);
//    }

//    public S21SsmEZDResult checkBillToCustAndBillToAccnt(NWAL1500CMsg bizMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("billToCustCd", bizMsg.billToCustCd.getValue());
//        queryParam.put("rgtnStsCdReadyForOrdTake", RGTN_STS.READY_FOR_ORDER_TAKING);
//        queryParam.put("billToCustAcctCd", bizMsg.billToCustAcctCd.getValue());
//        return getSsmEZDClient().queryObject("checkBillToCustAndBillToAccnt", queryParam);
//    }

//    public S21SsmEZDResult checkShipToCustAndShipToAccnt(NWAL1500CMsg bizMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("shipToCustCd", bizMsg.shipToCustCd.getValue());
//        queryParam.put("shipToCustAcctCd", bizMsg.shipToCustAcctCd.getValue());
//        queryParam.put("rgtnStsCdReadyForOrdTake", RGTN_STS.READY_FOR_ORDER_TAKING);
//        return getSsmEZDClient().queryObject("checkShipToCustAndAhipToAccnt", queryParam);
//    }

//    public S21SsmEZDResult checkSoldToLocationAndSoldToAccnt(NWAL1500CMsg bizMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("billToCustCd", bizMsg.soldToCustLocCd.getValue());
//        queryParam.put("rgtnStsCdReadyForOrdTake", RGTN_STS.READY_FOR_ORDER_TAKING);
//        queryParam.put("billToCustAcctCd", bizMsg.sellToCustCd.getValue());
//        return getSsmEZDClient().queryObject("checkBillToCustAndBillToAccnt", queryParam);
//    }

//    public S21SsmEZDResult checkSalesRepRelation(NWAL1500CMsg bizMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("slsRepTocCd", bizMsg.slsRepTocCd.getValue());
//        queryParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
//        return getSsmEZDClient().queryObject("checkSalesRepRelation", queryParam);
//    }

//    public S21SsmEZDResult checkPrcListRelation(NWAL1500CMsg bizMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
//        // At init process, sales date had been set in order date.
//        queryParam.put("slsDate", ZYPDateUtil.getSalesDate());
//        return getSsmEZDClient().queryObject("checkPrcListRelation", queryParam);
//    }

//    public S21SsmEZDResult checkCarrShpgSvcLvlAndShpgSvcLvl(NWAL1500CMsg bizMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
//        queryParam.put("carrSvcLvlCd", bizMsg.carrSvcLvlCd.getValue());
//        return getSsmEZDClient().queryObject("checkCarrShpgSvcLvlAndShpgSvcLvl", queryParam);
//}

//    public S21SsmEZDResult checkFrtCondRelation(NWAL1500CMsg bizMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("lineBizTpCd", bizMsg.lineBizTpCd.getValue());
//        queryParam.put("frtCondCd",  bizMsg.frtCondCd.getValue());
//        queryParam.put("shpgSvcLvlCd", bizMsg.shpgSvcLvlCd.getValue());
//        queryParam.put("carrSvcLvlCd", bizMsg.carrSvcLvlCd.getValue());
//        return getSsmEZDClient().queryObject("checkFrtCondRelation", queryParam);
//    }

    // 2016/08/30 S21_NA#9806 Add Start
    public S21SsmEZDResult checkCSMPNum(NWAL1500CMsg bizMsg, EZDSMsg configMsg) { // 2018/01/31 S21_NA#19808 Mod

        String sellToCustCd = "";
        String csmpNum = "";
        String dlrRefNum = "";

        if (configMsg == null) {
            sellToCustCd = bizMsg.sellToCustCd.getValue();
            csmpNum = bizMsg.csmpContrNum.getValue();
            dlrRefNum = bizMsg.dlrRefNum.getValue();
        } else {
            if (configMsg instanceof NWAL1500_ASMsg) { // 2018/01/31 S21_NA#19808 Mod
                NWAL1500_ASMsg lineConfigMsg = (NWAL1500_ASMsg) configMsg; // 2018/01/31 S21_NA#19808 Mod
                sellToCustCd = lineConfigMsg.sellToCustCd_LC.getValue();
                csmpNum = lineConfigMsg.csmpContrNum_LC.getValue();
                dlrRefNum = lineConfigMsg.dlrRefNum_LC.getValue();
            } else if (configMsg instanceof NWAL1500_CSMsg) { // 2018/01/31 S21_NA#19808 Mod
                NWAL1500_CSMsg rmaConfigMsg = (NWAL1500_CSMsg) configMsg; // 2018/01/31 S21_NA#19808 Mod
                sellToCustCd = rmaConfigMsg.sellToCustCd_RC.getValue();
                csmpNum = rmaConfigMsg.csmpContrNum_RC.getValue();
                dlrRefNum = rmaConfigMsg.dlrRefNum_RC.getValue();
            }
        }

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("sellToCustCd", sellToCustCd);
        queryParam.put("slsDt", bizMsg.slsDt.getValue());
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        if (ZYPCommonFunc.hasValue(bizMsg.csmpContrNum)) {
            queryParam.put("csmpNum", csmpNum);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dlrRefNum)) {
            queryParam.put("dlrRefNum", dlrRefNum);
        }
        return getSsmEZDClient().queryObject("checkCSMPNum", queryParam);
    }
        // 2016/08/30 S21_NA#9806 Add End

//    public S21SsmEZDResult checkCSANum(NWAL1500CMsg bizMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("dlrRefNum", bizMsg.dlrRefNum.getValue());
//        queryParam.put("soldToAcctNum", bizMsg.sellToCustCd.getValue());
//        return getSsmEZDClient().queryObject("checkCSMPNum", queryParam);
//    }

//    public S21SsmEZDResult checkCustMdseAndMdse(NWAL1500CMsg bizMsg, NWAL1500_BCMsg bcMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("mdseCd", bcMsg.mdseCd_LL.getValue());
//        queryParam.put("custMdseCd", bcMsg.custMdseCd_LL.getValue());
//        queryParam.put("soldToNum", bizMsg.sellToCustCd.getValue());
//        return getSsmEZDClient().queryObject("checkCustMdseAndMdse", queryParam);
//    }

//    public S21SsmEZDResult checkRtlWhAndRtlSubWh(NWAL1500CMsg bizMsg, NWAL1500_BCMsg bcMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("dsOrdTpCd", bizMsg.dsOrdCatgCd.getValue());
//        queryParam.put("rtlWhCd", bcMsg.rtlSwhCd_LL.getValue());
//        queryParam.put("rtlSwhNm", bcMsg.rtlSwhNm_LL.getValue());
//        // At init process, sales date had been set in order date.
//        queryParam.put("slsDate", ZYPDateUtil.getSalesDate());
//        return getSsmEZDClient().queryObject("checkRtlWhAndRtlSubWh", queryParam);
//    }

//    public S21SsmEZDResult checkRtlWhWithName(NWAL1500CMsg bizMsg, NWAL1500_BCMsg bcMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("rtlWhNm", bcMsg.rtlWhNm_LL.getValue());
//        return getSsmEZDClient().queryObject("checkRtlWhWithName", queryParam);
//    }
//
//    public S21SsmEZDResult checkRtlWhWithName(NWAL1500CMsg bizMsg, NWAL1500_DCMsg dcMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("rtlWhNm", dcMsg.rtlWhNm_RL.getValue());
//        return getSsmEZDClient().queryObject("checkRtlWhWithName", queryParam);
//    }

    // For Performance QC#8166 Add Start
    public S21SsmEZDResult getSubWhWithNameOfPerfectMatching(NWAL1500CMsg bizMsg, String rtlWhCd, String rtlSwhNm) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("rtlWhCd", rtlWhCd);
        queryParam.put("rtlSWhNm", rtlSwhNm);
        return getSsmEZDClient().queryObjectList("getSubWhInfoWithRsnOfPerfectMatching", queryParam);
    }
    // For Performance QC#8166 Add End

//    public S21SsmEZDResult checkRtlSubWhWithName(NWAL1500CMsg bizMsg, NWAL1500_BCMsg bcMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("rtlWhCd", bcMsg.rtlWhCd_LL.getValue());
//        queryParam.put("rtlSWhNm", bcMsg.rtlSwhNm_LL.getValue());
//        return getSsmEZDClient().queryObject("checkRtlSubWhWithName", queryParam);
//    }
//
//    public S21SsmEZDResult checkRtlSubWhWithName(NWAL1500CMsg bizMsg, NWAL1500_DCMsg dcMsg) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        queryParam.put("rtlWhCd", dcMsg.rtlWhCd_RL.getValue());
//        queryParam.put("rtlSWhNm", dcMsg.rtlSwhNm_RL.getValue());
//        return getSsmEZDClient().queryObject("checkRtlSubWhWithName", queryParam);
//    }

    public S21SsmEZDResult getCcyCdByPriceCatgNm(NWAL1500CMsg bizMsg, String prcCatgNm) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("prcCatgNm", prcCatgNm);
        return getSsmEZDClient().queryObjectList("getCcyCdByPriceCatgNm", queryParam);
    }

    public S21SsmEZDResult checkFutureRddOrCriticEss(NWAL1500CMsg bizMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        queryParam.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());

        List<String> ordCatgCtxTpCdList = new ArrayList<String>();
        ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
        ordCatgCtxTpCdList.add(ORD_CATG_CTX_TP.DAYS_PRIOR_ALLOCATION_CONTROL_ORDERS);
        queryParam.put("ordCatgCtxTpCdList", ordCatgCtxTpCdList.toArray(new String[0]));

        return getSsmEZDClient().queryObjectList("checkFutureRddOrCriticEss", queryParam);
    }

    public S21SsmEZDResult getDsConfigPkForAlloc(String glblCmpyCd, String cpoOrdNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("configCatgOutbound", CONFIG_CATG.OUTBOUND);

        return getSsmEZDClient().queryObjectList("getDsConfigPkForAlloc", queryParam);
    }

    public S21SsmEZDResult getLineAllocInfo(String glblCmpyCd, String cpoOrdNum, BigDecimal dsCpoConfigPk, List<String> whLst) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", cpoOrdNum);
        queryParam.put("dsCpoConfigPk", dsCpoConfigPk);
        queryParam.put("ordLineStsCancelled", ORD_LINE_STS.CANCELLED);
        queryParam.put("allocTpAuto", HARD_ALLOC_TP.AUTO);

        // 2016/01/25  S21_NA#3516 Add Start
        // S21_NA#12819 Del Start
//        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
//        setValue(varCharConstTMsg.glblCmpyCd,     glblCmpyCd);
//        setValue(varCharConstTMsg.varCharConstNm, NWAL1500Constant.NOT_ALLOC_WH_CD);
//
//        varCharConstTMsg = (VAR_CHAR_CONSTTMsg)S21FastTBLAccessor.findByKey(varCharConstTMsg);
//
//        List<String> whLst = null;
//
//        if (varCharConstTMsg != null) {
//            final String varCharConstVal = varCharConstTMsg.varCharConstVal.getValue();
//            whLst =asList(varCharConstVal.split(","));
//        }
        // S21_NA#12819 Del End
        queryParam.put("whLst", whLst);
        // 2016/01/25  S21_NA#3516 Add End

        // S21_NA#8196 ADD
        queryParam.put("shpgPlnStsCd", SHPG_STS.VALIDATED);

        return getSsmEZDClient().queryObjectList("getLineAllocInfo", queryParam);
    }

//    public S21SsmEZDResult getSvcMachMaintFlg(String glblCmpyCd, String serNum, String mdseCd) {
    public S21SsmEZDResult getSvcMachMaintFlg(String glblCmpyCd, String serNum, BigDecimal svcMachMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("serNum", serNum);
        queryParam.put("svcMachMstrPk", svcMachMstrPk); // QC#9277 Add
//        queryParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("getSvcMachMaintFlg", queryParam);
    }
    
    public S21SsmEZDResult getBizProcLogPK(String glblCmpyCd, CPOTMsg cpoTMsg,String eventId){
        Map<String, Object> queryParam = new HashMap<String, Object>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("subSysID", NWAL1500Constant.SUB_SYS_ID);
        queryParam.put("procId", NWAL1500Constant.PROCESS_ID);
        queryParam.put("eventId", eventId);
        queryParam.put("docTpCd", NWAL1500Constant.DOCUMENT_TYPE);
        queryParam.put("prntDocId", cpoTMsg.cpoOrdNum.getValue());
        queryParam.put("ezUpUsrId", cpoTMsg.ezUpUserID.getValue());
        queryParam.put("ezUpTimeZone", cpoTMsg.ezUpTimeZone.getValue());
        queryParam.put("ezUpTime", cpoTMsg.ezUpTime.getValue());
        return getSsmEZDClient().queryObject("getBizProcLogPK", queryParam);
    }

    /**
     * <pre>
     * Get Retail WH Info from DS_INVTY_LOC_V
     * </pre>
     * @param glblCmpyCd gllobal cmomany code
     * @param rtlWhNm Retail WH Code
     * @param rtlSwhNm Retail Sub WH Code
     * @param slsDt Sales Date
     * @param isUseRgtnSts true: use RGTN_STS_CD=P20(Ready For Order Taking), false: not use status cd
     * @return S21SsmEZDResult object.
     */
    public S21SsmEZDResult getRtlWh(String glblCmpyCd, String rtlWhNm, String rtlSwhNm, String slsDt, boolean isUseRgtnSts) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rtlWhNm", rtlWhNm);
        if (ZYPCommonFunc.hasValue(rtlSwhNm)) {
            queryParam.put("rtlSwhNm", rtlSwhNm);
        }
        if (ZYPCommonFunc.hasValue(slsDt)) {
            queryParam.put("slsDt", slsDt);
        }
        if (isUseRgtnSts) {
            queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        }
        return getSsmEZDClient().queryObject("getRtlWh", queryParam);
    }

    public S21SsmEZDResult checkSalesRep(String glblCmpyCd, String slsRepCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("slsRepCd", slsRepCd);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        queryParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        return getSsmEZDClient().queryObject("checkSalesRep", queryParam);
    }

    // 2017/05/16 S21_NA#Review structure Lv.2 (Delete Logif For Copy) Del Start
//    // QC#4078 Add Start
//    public S21SsmEZDResult getCpoDelyInfoByCpoOrdNum(String glblCmpyCd, String cpoOrdNum) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        return getSsmEZDClient().queryObjectList("getCpoDelyInfoByCpoOrdNum", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoIstlInfoByCpoOrdNum(String glblCmpyCd, String cpoOrdNum) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        return getSsmEZDClient().queryObjectList("getCpoIstlInfoByCpoOrdNum", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoSiteSurveyByCpoOrdNum(String glblCmpyCd, String cpoOrdNum) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        return getSsmEZDClient().queryObjectList("getCpoSiteSurveyByCpoOrdNum", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoCtacPsnByCpoOrdNum(String glblCmpyCd, String cpoOrdNum) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        return getSsmEZDClient().queryObjectList("getCpoCtacPsnByCpoOrdNum", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoSvcPkByCpoOrdNum(String glblCmpyCd, String cpoOrdNum) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        return getSsmEZDClient().queryObject("getCpoSvcPkByCpoOrdNum", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoSvcDtlForShell(String glblCmpyCd, BigDecimal cpoSvcPk) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoSvcPk", cpoSvcPk.toString());
//        return getSsmEZDClient().queryObjectList("getCpoSvcDtlForShell", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoSvcPrcForShell(String glblCmpyCd, BigDecimal cpoSvcPk) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoSvcPk", cpoSvcPk.toString());
//        return getSsmEZDClient().queryObjectList("getCpoSvcPrcForShell", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoSvcConfigRefForShell(String glblCmpyCd, BigDecimal cpoSvcPk) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoSvcPk", cpoSvcPk.toString());
//        return getSsmEZDClient().queryObjectList("getCpoSvcConfigRefForShell", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoSvcUsgPrcForShell(String glblCmpyCd, BigDecimal cpoSvcPk) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoSvcPk", cpoSvcPk.toString());
//        return getSsmEZDClient().queryObjectList("getCpoSvcUsgPrcForShell", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoSvcAddlBasePrcForShell(String glblCmpyCd, BigDecimal cpoSvcPk) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoSvcPk", cpoSvcPk.toString());
//        return getSsmEZDClient().queryObjectList("getCpoSvcAddlBasePrcForShell", queryParam);
//    }
//
//    public S21SsmEZDResult getCpoSvcAddlChrgPrcForShell(String glblCmpyCd, BigDecimal cpoSvcPk) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoSvcPk", cpoSvcPk.toString());
//        return getSsmEZDClient().queryObjectList("getCpoSvcAddlChrgPrcForShell", queryParam);
//    }
//    // QC#4078 Add End
    // 2017/05/16 S21_NA#Review structure Lv.2 (Delete Logif For Copy) Del End

    // 2016/02/25 S21_NA#1729 Add Start
    /**
     * <pre>
     * Count CPO by Customer PO Number
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult cntCustIssPoNum(NWAL1500CMsg bizMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("billToCustAcctCd", bizMsg.billToCustAcctCd.getValue());
        queryParam.put("custIssPoNum", bizMsg.custIssPoNum.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        return getSsmEZDClient().queryObject("cntCustIssPoNum", queryParam);
    }

    /**
     * <pre>
     * Count CPO by Lease Campany PO Number
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult cntLeaseCmpyPoNum(NWAL1500CMsg bizMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("billToCustAcctCd", bizMsg.billToCustAcctCd.getValue());
        queryParam.put("leaseCmpyPoNum", bizMsg.leaseCmpyPoNum.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        return getSsmEZDClient().queryObject("cntLeaseCmpyPoNum", queryParam);
    }
    // 2016/02/25 S21_NA#1729 Add End

    /**
     * <pre>
     * Get Count CPO_SVC_DTL
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCntCpoSvcDtl(NWAL1500CMsg bizMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        return getSsmEZDClient().queryObject("getCntCpoSvcDtl", queryParam);
    }

    /**
     * <pre>
     * Get Count CPO_SVC_CONFIG_REF
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCntCpoSvcConfigRef(NWAL1500CMsg bizMsg, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("cpoDtlLineNum", cpoDtlLineNum);
        queryParam.put("cpoDtlLineSubNum", cpoDtlLineSubNum);
        return getSsmEZDClient().queryObject("getCntCpoSvcConfigRef", queryParam);
    }

    // 2016/09/13 S21_NA#8276 Add Start
    /**
     * <pre>
     * Check directed Item is parent of the model or not
     * @param glblCmpyCd global company code
     * @param mdseCd Item Code (Full digits)
     * @return S21SsmEZDResult (1 or 2 records, count of records.)
     * </pre>
     */
    public S21SsmEZDResult checkMdlPrntMdse(String glblCmpyCd, String mdseCd, String swMdlFlg) { // 2018/10/09 QC#28383 mod

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("salesDt", ZYPDateUtil.getSalesDate());
        // 2018/10/09 QC#28383 add start
        if (ZYPConstant.FLG_OFF_N.equals(swMdlFlg)) {
            queryParam.put("swMdlFlg", swMdlFlg);
        }
        // 2018/10/09 QC#28383 add end

        return getSsmEZDClient().queryObjectList("checkMdlPrntMdse", queryParam);
    }
    // 2016/09/13 S21_NA#8276 Add End
    // 2016/11/08 S21_NA#15427 Add Start
    /**
     * <pre>
     * Get Order Line Category Code FROM Order Line Value Set (ORD_LINE_VAL_SET)
     * @param glblCmpyCd Global Company Code (Mandatory)
     * @param ordLineCtxTpCd Order Line Context Type Code (Mandatory)
     * @param dsOrdLineDrctnCd (Option, 'O' (outbaound) or 'I' (Inbound))
     * @return S21SsmEZDResult Query Result (List<String>, DS Line Category Code)
     * </pre>
     */
    public S21SsmEZDResult getLineCategoryCodeFromValueSet(String glblCmpyCd, String ordLineCtxTpCd, String dsOrdLineDrctnCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("ordLineCtxTpCd", ordLineCtxTpCd);
        if (ZYPCommonFunc.hasValue(dsOrdLineDrctnCd)) {
            queryParam.put("dsOrdLineDrctnCd", dsOrdLineDrctnCd);
        }

        return getSsmEZDClient().queryObjectList("getLineCategoryCodeFromValueSet", queryParam);
    }
    // 2016/11/08 S21_NA#15427 Add End

    // 2016/11/08 S21_NA#7749 Add Start
    /**
     * <pre>
     * Get Service Machine Master Data by Service Config Master PK(Config ID on Order Entry)
     * @param glblCmpyCd Global Company Code (Mandatory)
     * @param svcConfigMstrPk Service Config Master PK (Mandatory)
     * @return SVC_MACH_MSTRTMsgArray Query Result
     * </pre>
     */
    public SVC_MACH_MSTRTMsgArray getSvcMachMstrByConfigID(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return null;
        }

        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        tMsg.setSQLID("017");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        tMsg.setConditionValue("svcMachMstrStsCd01A", SVC_MACH_MSTR_STS.INSTALLED);
        tMsg.setConditionValue("svcMachMstrStsCd01B", SVC_MACH_MSTR_STS.DEALER_SERVICE);
        tMsg.setConditionValue("svcMachMaintAvalFlg01", ZYPConstant.FLG_ON_Y);
        return (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }
    // 2016/11/08 S21_NA#7749 Add End

    // 2016/11/09 S21_NA#9864 Add Start
    /**
     * <pre>
     * Get Loan Shipment Line Category Info
     * @param glblCmpyCd Global Company Code
     * @param dsOrdLineCatgCd DS Order Line Category Code
     * @param slsDt Sales Date
     * @return S21SsmEZDResult Object List of DS_ORD_LINE_CATG_CD (String)
     * </pre>
     */
    // S21_NA#17586 Mod Start
    public S21SsmEZDResult getLoanShipLineCategory(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineCatgCd, String slsDt) {
    // S21_NA#17586 Mod End

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        // S21_NA#17586 Add Start
        queryParam.put("dsOrdTpCd", dsOrdTpCd);
        // S21_NA#17586 Add End
        queryParam.put("dsOrdLineCatgCd", dsOrdLineCatgCd);
        queryParam.put("slsDt", slsDt);
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getLoanShipLineCategory", queryParam);
    }
    // 2016/11/09 S21_NA#9864 Add End

    // S21_NA#15889 Add Start
    /**
     * <pre>
     * Get DS CPO Config PK
     * @param bizMsg NWAL1500CMsg
     * @param dsCpoConfigPk DS CPO Config PK
     * @return DS CPO Config PK
     * </pre>
     */
    public S21SsmEZDResult getDsCpoDelyInfoPk(NWAL1500CMsg bizMsg, BigDecimal dsCpoConfigPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsCpoConfigPk", dsCpoConfigPk);

        return getSsmEZDClient().queryObjectList("getDsCpoDelyInfoPk", queryParam);
    }

    /**
     * <pre>
     * Get DS CPO Install Info PK
     * @param bizMsg NWAL1500CMsg
     * @param dsCpoConfigPk DS CPO Config PK
     * @return DS CPO Install Info PK
     * </pre>
     */
    public S21SsmEZDResult getDsCpoIstlInfoPk(NWAL1500CMsg bizMsg, BigDecimal dsCpoConfigPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsCpoConfigPk", dsCpoConfigPk);

        return getSsmEZDClient().queryObjectList("getDsCpoIstlInfoPk", queryParam);
    }

    /**
     * <pre>
     * Get DS Site Servey PK
     * @param bizMsg NWAL1500CMsg
     * @param dsCpoConfigPk DS CPO Config PK
     * @return DS Site Servey PK
     * </pre>
     */
    public S21SsmEZDResult getDsSiteSrvyPk(NWAL1500CMsg bizMsg, BigDecimal dsCpoConfigPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsCpoConfigPk", dsCpoConfigPk);

        return getSsmEZDClient().queryObjectList("getDsSiteSrvyPk", queryParam);
    }

    /**
     * <pre>
     * Get DS CPO Contact Person PK List
     * @param bizMsg NWAL1500CMsg
     * @param dsCpoConfigPk DS CPO Config PK
     * @return DS CPO Contact Person PK List
     * </pre>
     */
    public S21SsmEZDResult getDsCpoCtacPsnPkList(NWAL1500CMsg bizMsg, BigDecimal dsCpoConfigPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("dsCpoConfigPk", dsCpoConfigPk);

        return getSsmEZDClient().queryObjectList("getDsCpoCtacPsnPkList", queryParam);
    }
    // S21_NA#15889 Add End
    // 2017/12/07 S21_NA#22794 Add Start
    /**
     * get return detail related to detected configId and not same cpo order number.
     * @param bizMsg Business Message
     * @param configMsg Outbound config message
     * @param rtrnDtlTMsgArray result array (instance of DS_CPO_RTRN_DTLTMsg)
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOtherOrderRmaConfigDetail(NWAL1500CMsg bizMsg, NWAL1500_ASMsg configMsg) { // 2018/01/31 S21_NA#19808 Mod

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("configId", configMsg.svcConfigMstrPk_LC.getValue());
        queryParam.put("configCatgInbound", CONFIG_CATG.INBOUND);

        return getSsmEZDClient().queryObjectList("getOtherOrderRmaConfigDetail", queryParam);
    }
    // 2017/12/07 S21_NA#22794 Add End
    // 2018/03/12 S21_NA#20297(Sol#379) Add Start
    public S21SsmEZDResult getDsCpoDelyInfo(NWAL1500CMsg bizMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());

        return getSsmEZDClient().queryObjectList("getDsCpoDelyInfo", queryParam);
    }
    // 2018/03/12 S21_NA#20297(Sol#379) Add End

    // 2018/10/25 S21_NA#28897 Add Start
    public S21SsmEZDResult checkChangeWhForPendingReturn(NWAL1500CMsg bizMsg, NWAL1500_DSMsg lineMsg) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        queryParam.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        queryParam.put("cpoDtlLineNum", lineMsg.cpoDtlLineNum_RL.getValue());
        queryParam.put("cpoDtlLineSubNum", lineMsg.cpoDtlLineSubNum_RL.getValue());
        queryParam.put("rwsCanceled", RWS_STS.CANCELED);

        return getSsmEZDClient().queryObjectList("checkChangeWhForPendingReturn", queryParam);
    }
    // 2018/10/25 S21_NA#28897 Add End

    // Add Start 2018/11/02 QC#29017
    public S21SsmEZDResult getSvcMachMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);

        return getSsmEZDClient().queryObjectList("getSvcMachMstr", queryParam);
    }
    // Add End 2018/11/02 QC#29017

    // 2019/07/26 QC#51941 Add Start
    public S21SsmEZDResult getMainMachine(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);

        return getSsmEZDClient().queryObjectList("getMainMachine", queryParam);
    }
    // 2019/07/26 QC#51941 Add End

    // Add Start 2019/11/15 QC#54515
    public S21SsmEZDResult checkMultipleIBItem(String glblCmpyCd, BigDecimal svcConfigMstrPk, NWAL1500_DSMsg rmaLineMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        queryParam.put("mdseCd", rmaLineMsg.mdseCd_RL.getValue());
        if (ZYPCommonFunc.hasValue(rmaLineMsg.serNum_RL)) {
            queryParam.put("serNum", rmaLineMsg.serNum_RL.getValue());
        }
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.TERMINATED);
        queryParam.put("svcMachMstrStsCdList", svcMachMstrStsCdList.toArray(new String[0]));

        return getSsmEZDClient().queryObjectList("checkMultipleIBItem", queryParam);
    }
    // Add End 2019/11/15 QC#54515
    // Add Start 2020/01/17 QC#55202
    /**
     * Check IS this defaultWH.
     * @param glblCmpyCd glblCmpyCd
     * @param String returnWH
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkIsDefaultWH(String glblCmpyCd, String returnWH) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("returnWH", returnWH);
        queryParam.put("dropRmaCd", NWAL1500Constant.RTL_WH_CATG_DROP_RMA_CD);
        
        return getSsmEZDClient().queryObjectList("checkIsDefaultWH", queryParam);
    }
    /**
     * Check IS this mdse has Default WH.
     * @param glblCmpyCd glblCmpyCd
     * @param String mdseCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkHasDefaultWH(String glblCmpyCd, String mdseCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("checkHasDefaultWH", queryParam);
    }
    // Add End   2020/01/17 QC#55202

}
