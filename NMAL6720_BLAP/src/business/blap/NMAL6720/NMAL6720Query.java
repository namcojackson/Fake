/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6720;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

import static business.blap.NMAL6720.constant.NMAL6720Constant.MAX_DT;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/01/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/29   Fujitsu         C.Tanaka        Update          QC#2824
 * 2016/04/26   SRAA            Y.Chen          Update          QC#6186
 * 2016/07/19   Hitachi         Y.Tsuchimoto    Update          QC#10745
 * 2016/09/22   SRAA            Y.Chen          Update          QC#12060
 * 2016/09/30   Fujitsu         C.Yokoi         Update          QC#13877
 * 2016/10/20   Fujitsu         C.Yokoi         Update          QC#14720
 * 2017/06/29   Hitachi         J.Kim           Update          QC#17670
 * 2017/08/10   Fujitsu         N.Sugiura       Update          QC#8598
 * 2018/02/19   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/07/11   Fujitsu         H.Kumagai       Update          QC#26422
 * 2018/10/09   Fujitsu         Hd.Sugawara     Update          QC#27598
 * 2018/12/13   Fujitsu         M.Ishii         Update          QC#29315
 * 2019/02/19   Fujitsu         R.Nakamura      Update          QC#30293
 * 2019/05/23   Fujitsu         Hd.Sugawara     Update          QC#50101
 * 2019/08/07   Fujitsu         Hd.Sugawara     Update          QC#52385
 * 2019/12/13   Fujitsu         M.Nakamura      Update          QC#54882
 * 2021/05/20   CITS            M.Furugoori     Update          QC#58743
 * 2022/07/28   CITS            A.Cullano       Update          QC#60173
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/07/18   Hitachi         T.Doi           Update          QC#61421
 *</pre>
 */
public final class NMAL6720Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6720Query INSTANCE = new NMAL6720Query();

    /**
     * Constructor.
     */
    private NMAL6720Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL6720Query
     */
    public static NMAL6720Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDsAcctInfoByDsAcctNum
     * @param ssmParam Map
     * @param sMsg NMAL6720SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfo(Map<String, Object> ssmParam, NMAL6720SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsg("getHeaderInfo", ssmParam, sMsg);
    }

    /**
     * getDsAcctTpCd
     * @param ssmParam Map
     * @return String
     */
    public S21SsmEZDResult getDsAcctTpCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsAcctTpCd", ssmParam);
    }

    /**
     * getAccountList
     * @param queryParam Map
     * @param aSMsgArray NMAL6720_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAccountList(Map<String, Object> queryParam, NMAL6720_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getAccountList", queryParam, aSMsgArray);
    }

    /**
     * getClassificationsInfo
     * @param queryParam Map
     * @param sMsg NMAL6720SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getClassificationsInfo(Map<String, Object> queryParam, NMAL6720SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsg("getClassificationsInfo", queryParam, sMsg);
    }

    /**
     * getCrossReerenceList
     * @param queryParam Map
     * @param bSMsgArray NMAL6720_BSMsgArray
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getCrossReferenceList(Map<String, Object> queryParam, NMAL6720_BSMsgArray bSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getCrossReferenceList", queryParam, bSMsgArray);
    }

    /**
     * getContactList
     * @param queryParam Map
     * @param cSMsgArray NMAL6720_CSMsgArray
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getContactList(Map<String, Object> queryParam, NMAL6720_CSMsgArray cSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContactList", queryParam, cSMsgArray);
    }

    /**
     * getTransactionList
     * @param queryParam Map
     * @param dSMsgArray NMAL6720_DSMsgArray
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getTransactionList(Map<String, Object> queryParam, NMAL6720_DSMsgArray dSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getTransactionList", queryParam, dSMsgArray);
    }

    /**
     * getMsgNoteList
     * @param queryParam Map
     * @param eSMsgArray NMAL6720_ESMsgArray
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getMsgNoteList(Map<String, Object> queryParam, NMAL6720_ESMsgArray eSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getMsgNoteList", queryParam, eSMsgArray);
    }

    /**
     * getCertReqList
     * @param queryParam Map
     * @param fSMsgArray NMAL6720_FSMsgArray
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getCertReqList(Map<String, Object> queryParam, NMAL6720_FSMsgArray fSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getCertReqList", queryParam, fSMsgArray);
    }

    /**
     * getDoNotSentTechList
     * @param queryParam Map
     * @param gSMsgArray NMAL6720_GSMsgArray
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getDoNotSentTechList(Map<String, Object> queryParam, NMAL6720_GSMsgArray gSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getDoNotSentTechList", queryParam, gSMsgArray);
    }

    /**
     * getFirstLocInfo
     * @param queryParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getFirstLocInfo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getFirstLocInfo", queryParam);
    }

    /**
     * getPrimaryLoc
     * @param queryParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getPrimaryLoc(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getPrimaryLoc", queryParam);
    }

    /**
     * getOtherLoc
     * @param queryParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getOtherLoc(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getOtherLoc", queryParam);
    }

    /**
     * getPrinCust
     * @param queryParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getPrinCust(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getPrinCust", queryParam);
    }

    /**
     * getPrinCustFirst
     * @param queryParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getPrinCustFirst(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getPrinCustFirst", queryParam);
    }

    /**
     * getPrimaryBillTo
     * @param queryParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getPrimaryBillTo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getPrimaryBillTo", queryParam);
    }

    /**
     * getPrimaryShipTo
     * @param queryParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getPrimaryShipTo(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getPrimaryShipTo", queryParam);
    }

    /**
     * getStateList
     * @param queryParam Map
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getStateList(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getStateList", queryParam);
    }

    /**
     * <pre>
     * Get Other Primary Contact
     * </pre>
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOtherPrimCtac(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getOtherPrimCtac", queryParam);
    }

    /**
     * getDefaultRemId
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultRemId(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getDefaultRemId", queryParam);
    }

    /**
     * getDefaultRemId
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultRemIdNoState(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getDefaultRemIdNoState", queryParam);
    }

    /**
     * getAcct
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcct(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getAcct", queryParam);
    }

    /**
     * "getAddrByPost"
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddrByPost(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getAddrByPost", queryParam);
    }

    /**
     * getDuplicateRefAndType
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDuplicateRefAndType(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getDuplicateRefAndType", queryParam);
    }

    /**
     * getLocationWithoutLocNum
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationWithoutLocNum(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getLocationWithoutLocNum", queryParam);
    }

    /**
     * getShipToCustCd
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustCd(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getShipToCustCd", queryParam);
    }

    /**
     * getWareHouse
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWareHouse(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObjectList("getWareHouse", queryParam);
    }

    /**
     * checkActiveAcct
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkActiveAcct(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("checkActiveAcct", queryParam);
    }

    // 2019/12/13 QC#54882 Mod Start
//    // START 2017/06/29 J.Kim [QC#17670,ADD]
//    // Mod Start 2018/10/09 QC#27598
//    ///**
//    // * countCpoOpenOrder
//    // * @param dsAcctNum String
//    // * @param glblCmpyCd String
//    // * @return S21SsmEZDResult
//    // */
//    //public S21SsmEZDResult countCpoOpenOrder(String locNum, String glblCmpyCd) {
//    /**
//     * countCpoOpenOrder
//     * @param glblCmpyCd String
//     * @param dsAcctNum String
//     * @param locNum String
//     * @param billToCd String
//     * @param shipToCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult countCpoOpenOrder(String glblCmpyCd, String dsAcctNum, String locNum, String billToCd, String shipToCd) {
//        // Mod End 2018/10/09 QC#27598
//        Map<String, Object> query = new HashMap<String, Object>();
//        query.put("glblCmpyCd", glblCmpyCd);
//        // Mod Start 2018/10/09 QC#27598
//        //query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
//        query.put("slsDtPast", getOneYearPast(ZYPDateUtil.getSalesDate(glblCmpyCd)));
//        // Mod End 2018/10/09 QC#27598
//        query.put("locNum", locNum);
//        // Add Start 2018/10/09 QC#27598
//        query.put("dsAcctNum", dsAcctNum);
//
//        if(billToCd != null) {
//            query.put("billToCd", billToCd);
//        }
//        if(shipToCd != null) {
//            query.put("shipToCd", shipToCd);
//        }
//
//        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        query.put("flgOnY", ZYPConstant.FLG_ON_Y);
//        // Add End 2018/10/09 QC#27598
//        query.put("rowNum", 1);
//
//        return getSsmEZDClient().queryObject("cpoOpenOrder", query);
//    }
//
//    // Mod Start 2018/10/09 QC#27598
//    ///**
//    // * countCpoOpenOrder
//    // * @param dsAcctNum String
//    // * @param glblCmpyCd String
//    // * @return S21SsmEZDResult
//    // */
//    //public S21SsmEZDResult countOpenReturnOrder(String locNum, String glblCmpyCd) {
//    /**
//     * countOpenReturnOrder
//     * @param glblCmpyCd String
//     * @param dsAcctNum String
//     * @param locNum String
//     * @param billToCd String
//     * @param shipToCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult countOpenReturnOrder(String glblCmpyCd, String dsAcctNum, String locNum, String billToCd, String shipToCd) {
//        // Mod End 2018/10/09 QC#27598
//        Map<String, Object> query = new HashMap<String, Object>();
//        query.put("glblCmpyCd", glblCmpyCd);
//        // Mod Start 2018/10/09 QC#27598
//        //query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
//        query.put("slsDtPast", getOneYearPast(ZYPDateUtil.getSalesDate(glblCmpyCd)));
//        // Mod End 2018/10/09 QC#27598
//        query.put("locNum", locNum);
//        // Add Start 2018/10/09 QC#27598
//        query.put("dsAcctNum", dsAcctNum);
//
//        if(billToCd != null) {
//            query.put("billToCd", billToCd);
//        }
//        if(shipToCd != null) {
//            query.put("shipToCd", shipToCd);
//        }
//
//        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        query.put("flgOnY", ZYPConstant.FLG_ON_Y);
//        // Add End 2018/10/09 QC#27598
//        query.put("rowNum", 1);
//
//        return getSsmEZDClient().queryObject("openReturnOrder", query);
//    }
//
//    // Mod Start 2018/10/09 QC#27598
//    ///**
//    // * countOpenMachineMasterCheck
//    // * @param dsAcctNum String
//    // * @param glblCmpyCd String
//    // * @return S21SsmEZDResult
//    // */
//    //public S21SsmEZDResult countOpenMachineMasterCheck(String locNum, String glblCmpyCd) {
//    /**
//     * countOpenMachineMasterCheck
//     * @param glblCmpyCd String
//     * @param dsAcctNum String
//     * @param locNum String
//     * @param billToCd String
//     * @param shipToCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult countOpenMachineMasterCheck(String glblCmpyCd, String dsAcctNum, String locNum, String billToCd, String shipToCd) {
//        // Mod End 2018/10/09 QC#27598
//        Map<String, Object> query = new HashMap<String, Object>();
//        query.put("glblCmpyCd", glblCmpyCd);
//        // Del Start 2018/10/09 QC#27598
//        //query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
//        // Del End 2018/10/09 QC#27598
//        query.put("locNum", locNum);
//        // Add Start 2018/10/09 QC#27598
//        query.put("dsAcctNum", dsAcctNum);
//
//        if(billToCd != null) {
//            query.put("billToCd", billToCd);
//        }
//        if(shipToCd != null) {
//            query.put("shipToCd", shipToCd);
//        }
//
//        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
//        // Add End 2018/10/09 QC#27598
//
//        query.put("rowNum", 1);
//
//        query.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
//        query.put("instl", SVC_MACH_MSTR_STS.INSTALLED);
//        query.put("w4r", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
//        query.put("dlrsv", SVC_MACH_MSTR_STS.DEALER_SERVICE);
//
//        return getSsmEZDClient().queryObject("openMachineMasterCheck", query);
//    }
//    // END 2017/06/29 J.Kim [QC#17670,ADD]

    /*
     * countOpenTransaction
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param locNum String
     * @param billToCd String
     * @param shipToCd String
     * @return S21SsmEZDResult
     */
    // 2021/05/20 QC#58743 Mod Start
//    public S21SsmEZDResult countOpenTransaction(String glblCmpyCd, String dsAcctNum, String locNum, String billToCd, String shipToCd) {
    public S21SsmEZDResult countOpenTransaction(String glblCmpyCd, String dsAcctNum, String locNum, String billToCd, String shipToCd, String billToRgtnStsCd, String shipToRgtnStsCd) {
        // 2021/05/20 QC#58743 Mod End
        Map<String, Object> query = new HashMap<String, Object>();

        query.put("glblCmpyCd", glblCmpyCd);
        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        query.put("acctNum", dsAcctNum);
        query.put("locNum", locNum);
        if (ZYPCommonFunc.hasValue(billToCd)) {
            query.put("billToCustCd", billToCd);
        }
        if (ZYPCommonFunc.hasValue(shipToCd)) {
            query.put("shipToCustCd", shipToCd);
        }
        // 2021/05/20 QC#58743 Add Start
        if (RGTN_STS.TERMINATED.equals(billToRgtnStsCd)) {
            query.put("billToTerminated", ZYPConstant.FLG_ON_Y);
        }
        if (RGTN_STS.TERMINATED.equals(shipToRgtnStsCd)) {
            query.put("shipToTerminated", ZYPConstant.FLG_ON_Y);
        }
        // 2021/05/20 QC#58743 Add End

        query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
        query.put("flgY", ZYPConstant.FLG_ON_Y);
        query.put("flgN", ZYPConstant.FLG_OFF_N);
        query.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);
        query.put("svcMachStsW4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        query.put("svcMachStsInstl", SVC_MACH_MSTR_STS.INSTALLED);
        query.put("svcMachStsW4R", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        query.put("svcMachStsDlrSvc", SVC_MACH_MSTR_STS.DEALER_SERVICE);
        query.put("dsContrCtrlStsCancelled", DS_CONTR_CTRL_STS.CANCELLED);
        query.put("dsContrCtrlStsDraft", DS_CONTR_CTRL_STS.DRAFT);
        // 2021/05/20 QC#58743 Add Start
        query.put("dsContrCtrlStsTerminated", DS_CONTR_CTRL_STS.TERMINATED);
        // 2021/05/20 QC#58743 Add End
        query.put("svcTaskStsClosed", SVC_TASK_STS.CLOSED);
        query.put("svcTaskStsCancelled", SVC_TASK_STS.CANCELLED);
        query.put("maxThruDt", "99991231");
        // START 2022/07/28 A.Cullano [QC#60173, ADD]
        query.put("skipRecovTpSkip", SKIP_RECOV_TP.SKIP);
        // END 2022/07/28 A.Cullano [QC#60173, ADD]

        query.put("rowNum", 1);
        
        return getSsmEZDClient().queryObject("countOpenTransaction", query);
    }
    // 2019/12/13 QC#54882 Mod End


    // 2017/08/10 S21_NA#8598 Add Start
    public S21SsmEZDResult getPrimaryContactForDuplicateCheck(String locNum, BigDecimal ctacPsnPk, String ctacTpCd, String glblCmpyCd, String chkEffFromDt, String chkEffThruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        ssmParam.put("ctacPsnPk", ctacPsnPk);
        ssmParam.put("ctacTpCd", ctacTpCd);
        ssmParam.put("chkEffFromDt", chkEffFromDt);
        ssmParam.put("chkEffThruDt", chkEffThruDt);
        ssmParam.put("maxEffThruDt", MAX_DT);

        return getSsmEZDClient().queryObjectList("getPrimaryContactForDuplicateCheck", ssmParam);
    }
    // 2017/08/10 S21_NA#8598 Add End

    // 2018/02/19 QC#20297(Sol#379) add start
    /**
     * getDsCustShpgDefList
     * @param queryParam Map
     * @param eSMsgArray NMAL6720_ESMsgArray
     * @return getSsmEZDClient
     */
    public S21SsmEZDResult getDsCustShpgDefList(Map<String, Object> queryParam, NMAL6720_ISMsgArray iSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getDsCustShpgDefList", queryParam, iSMsgArray);
    }

    // 2018/12/13 QC#29315 Add Start
    public S21SsmEZDResult getBankAcctPulldownList(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("outboundCarrier", VND_TP.OUTBOUND_CARRIER);
        String salesDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        ssmParam.put("salesDate", salesDate);
        // Mod Start 2019/08/07 QC#52385
        //ssmParam.put("maxDate", MAX_EFF_THRU_DT);
        ssmParam.put("maxDate", MAX_DT);
        // Mod End 2019/08/07 QC#52385
        return getSsmEZDClient().queryObjectList("getBankAcctPulldownList", ssmParam);
    }
    // 2018/12/13 QC#29315 Add End

    public S21SsmEZDResult getFrtCondSvcReln(String glblCmpyCd, String lineBizTpCd, String frtCondCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("lineBizTpCd", lineBizTpCd);
        if (ZYPCommonFunc.hasValue(frtCondCd)) {
            ssmParam.put("frtCondCd", frtCondCd);
        }
        return getSsmEZDClient().queryObjectList("getFrtCondSvcReln", ssmParam);
    }
    // 2018/02/19 QC#20297(Sol#379) add end

    // 2018/07/11 Update Start QC#26422
    /**
     * "getBillToCustCd"
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustCd(Map<String, Object> queryParam) {
        return getSsmEZDClient().queryObject("getBillToCustCd", queryParam);
    }
    // 2018/07/11 Update End QC#26422

    // Add Start 2018/10/09 QC#27598
    /**
     * getCurrentAccount
     * @param glblCmpyCd String
     * @param dsAcctTpCd String
     * @param locNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCurrentAccount(String glblCmpyCd, String dsAcctTpCd, String locNum) {
        Map<String, Object> query = new HashMap<String, Object>();

        query.put("glblCmpyCd", glblCmpyCd);
        query.put("dsAcctTpCd", dsAcctTpCd);
        query.put("locNum", locNum);
        query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getCurrentAccount", query);
    }

    // 2019/12/13 QC#54882 Del Start
//    private String getOneYearPast(String yyyyMMdd) {
//
//        Calendar cal = Calendar.getInstance();
//        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
//        try {
//            cal.setTime(dateformat.parse(yyyyMMdd));
//        } catch (ParseException e) {
//            return null;
//        }
//        cal.add(Calendar.YEAR, -1);
//        return dateformat.format(cal.getTime());
//    }
//    // Add End 2018/10/09 QC#27598
    // 2019/12/13 QC#54882 Del End

    // Add Start 2019/02/19 QC#30293
    /**
     * getTemplateForFinancials
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTemplateForFinancials(String glblCmpyCd) {
        Map<String, Object> query = new HashMap<String, Object>();

        query.put("glblCmpyCd", glblCmpyCd);

        return getSsmEZDClient().queryObjectList("getTemplateForFinancials", query);
    }
    // Add End 2019/02/19 QC#30293

    // Add Start 2019/05/23 QC#50101
    /**
     * getAcctCmpyPkByAcctNum
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctCmpyPkByAcctNum(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObject("getAcctCmpyPkByAcctNum", ssmParam);
    }
    // Add End 2019/05/23 QC#50101

    // Add Start 2019/08/07 QC#52385
    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param defBillToList List<String>
     * @param defShipToList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedBillShipFromChildReln(String glblCmpyCd, String dsAcctNum, //
            List<String> defBillToList, List<String> defShipToList) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("acctNum", dsAcctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);

        if (defBillToList != null && defBillToList.size() > 0) {
            String[] strDefBillToList = defBillToList.toArray(new String[0]);
            param.put("defBillToList", strDefBillToList);
        }
        if (defShipToList != null && defShipToList.size() > 0) {
            String[] strDefShipToList = defShipToList.toArray(new String[0]);
            param.put("defShipToList", strDefShipToList);
        }

        return getSsmEZDClient().queryObjectList("getCustInfoForRelatedChildReln", param);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param defBillToList List<String>
     * @param defShipToList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedBillShipFromCurrent(String glblCmpyCd, String dsAcctNum, //
            List<String> defBillToList, List<String> defShipToList) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("acctNum", dsAcctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        if (defBillToList != null && defBillToList.size() > 0) {
            String[] strDefBillToList = defBillToList.toArray(new String[0]);
            param.put("defBillToList", strDefBillToList);
        }
        if (defShipToList != null && defShipToList.size() > 0) {
            String[] strDefShipToList = defShipToList.toArray(new String[0]);
            param.put("defShipToList", strDefShipToList);
        }

        return getSsmEZDClient().queryObjectList("getCustInfoForRelatedCurrent", param);
    }

    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param defBillToList List<String>
     * @param defShipToList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRelatedBillShipFromParentReln(String glblCmpyCd, String dsAcctNum, //
            List<String> defBillToList, List<String> defShipToList) {

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", glblCmpyCd);
        param.put("acctNum", dsAcctNum);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsAcctRelnTpCd", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        param.put("dsAcctRelnTpCd_Exclusion", DS_ACCT_RELN_TP.MYCSA_ACCOUNT);

        if (defBillToList != null && defBillToList.size() > 0) {
            String[] strDefBillToList = defBillToList.toArray(new String[0]);
            param.put("defBillToList", strDefBillToList);
        }
        if (defShipToList != null && defShipToList.size() > 0) {
            String[] strDefShipToList = defShipToList.toArray(new String[0]);
            param.put("defShipToList", strDefShipToList);
        }

        return getSsmEZDClient().queryObjectList("getCustInfoForRelatedParentReln", param);
    }
    // Add End 2019/08/07 QC#52385
    // 2023/01/13 QC#60860 Add Start
    public String getVndCd(String glblCmpyCd, String vndNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("vndNm", vndNm);

        return (String) getSsmEZDClient().queryObject("getVndCd", ssmParam).getResultObject();
    }
    // 2023/01/13 QC#60860 Add End

    // add start 2023/07/18 QC#61421
    public S21SsmEZDResult getDsBizArea(String glblCmpyCd, String spclInstnFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(spclInstnFlg)) {
            ssmParam.put("spclInstnFlg", spclInstnFlg);
        }
        return getSsmEZDClient().queryObjectList("getDsBizArea", ssmParam);
    }
    // add end 2023/07/18 QC#61421
}
