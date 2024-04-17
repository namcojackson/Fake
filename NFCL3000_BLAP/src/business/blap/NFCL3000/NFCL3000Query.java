/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL3000;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NFCL3000.constant.NFCL3000Constant;
import business.db.INV_LINETMsg;
import business.db.TAX_ADJ_ITEMTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/12/21   Fujitsu         T.Tanaka        Create          N/A
 * 2016/05/24   Fujitsu         S.Fujita        Update          QC#8522
 * 2016/06/20   Fujitsu         S.Fujita        Update          QC#9454
 * 2016/07/20   Fujitsu         S.Fujita        Update          QC#10148
 * 2016/08/01   Hitachi         Y.Takeno        Update          QC#9192
 * 2016/11/17   Fujitsu         T.Murai         Update          QC#14096
 * 2016/11/22   Fujitsu         S.Fujita        Update          QC#16154
 * 2018/05/22   Fujitsu         Y.Matsui        Update          QC#21841
 * 2018/09/19   Fujitsu         S.Ohki          Update          QC#28089
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 * 2019/05/27   Fujitsu         S.Takami        Update          QC#50541
 * 2021/01/04   CITS            R.Kurahashi     Update          QC#56282
 * 2021/10/25   CITS            S.Go            Update          QC#55215
 *</pre>
 */
public class NFCL3000Query extends S21SsmEZDQuerySupport implements NFCL3000Constant {

    /**
     * Singleton instance.
     */
    private static final NFCL3000Query myInstance = new NFCL3000Query();

    // START 2019/05/27 S.Takami [QC#50541,ADD]
    /** Item Info Result Cache */
    private Map<Map<String, Object>, S21SsmEZDResult> itemInfoRsltMap = new HashMap<Map<String, Object>, S21SsmEZDResult>(0);
    // END 2019/05/27 S.Takami [QC#50541,ADD]
    /**
     * Constructor
     */
    private NFCL3000Query() {
        super();
    }

    // START 2019/04/25 S.Takami [QC#50078,ADD]
    private Map<Map<String, String>, S21SsmEZDResult> bllgCycleUomMap = null;
    // START 2019/04/25 S.Takami [QC#50078,ADD]

    // START 2019/05/27 S.Takami [QC#50541,ADD]
    private List<TAX_ADJ_ITEMTMsg> taxAdjItemTMsgList = null;
    // END 2019/05/27 S.Takami [QC#50541,ADD]

    /**
     * Singleton instance getter.
     * @return NFCL3000Query
     */
    public static NFCL3000Query getInstance() {
        return myInstance;
    }

    /**
     * getInvTpPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getInvTpPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getInvTpPullDownList", ssmParam);
    }

    /**
     * getDsInvTpPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsInvTpPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDsInvTpPullDownList", ssmParam);
    }

    /**
     * getPmtTermCashDiscPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getPmtTermCashDiscPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPmtTermCashDiscPullDownList", ssmParam);
    }

    /**
     * getDfrdInvRulePullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDfrdInvRulePullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDfrdInvRulePullDownList", ssmParam);
    }

    /**
     * getPkgUomPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getPkgUomPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPkgUomPullDownList", ssmParam);
    }

    /**
     * getBolLineNumPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getBolLineNumPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getBolLineNumPullDownList", ssmParam);
    }

    /**
     * getDfrdAcctgRulePullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDfrdAcctgRulePullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDfrdAcctgRulePullDownList", ssmParam);
    }

    /**
     * getInvLineSplTpPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getInvLineSplTpPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getInvLineSplTpPullDownList", ssmParam);
    }

    /**
     * getDsPmtMethPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsPmtMethPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDsPmtMethPullDownList", ssmParam);
    }

    /**
     * getShpgSvcLvlPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getShpgSvcLvlPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getShpgSvcLvlPullDownList", ssmParam);
    }

    /**
     * getShpgSvcLvlPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getAjeInvAcctClsPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getAjeInvAcctClsPullDownList", ssmParam);
    }

    /**
     * getInvProcTpPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getInvProcTpPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getInvProcTpPullDownList", ssmParam);
    }

    /**
     * getDsContrCatgPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsContrCatgPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDsContrCatgPullDownList", ssmParam);
    }

    /**
     * getDsOrdTpPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsOrdTpPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDsOrdTpPullDownList", ssmParam);
    }

    /**
     * getArCashApplyStsPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getArCashApplyStsPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArCashApplyStsPullDownList", ssmParam);
    }

    /**
     * getItemInfo
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getItemInfo(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        // START 2019/05/27 S.Takami [QC#50541,MOD]
//        return getSsmEZDClient().queryObject("getItemInfo", ssmParam);
        S21SsmEZDResult ssmRslt = this.itemInfoRsltMap.get(ssmParam);
        if (ssmRslt == null) {
            ssmRslt = getSsmEZDClient().queryObject("getItemInfo", ssmParam);
            this.itemInfoRsltMap.put(ssmParam, ssmRslt);
        }
        return ssmRslt;
        // START 2019/05/27 S.Takami [QC#50541,MOD]
    }

    /**
     * searchBillTo
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchBillTo(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchBillTo", ssmParam);
    }

    /**
     * searchShipTo
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchShipTo(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchShipTo", ssmParam);
    }

    /**
     * getBillToInfo
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getBillToInfo(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getBillToInfo", ssmParam);
    }

    /**
     * searchWarehouse
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchWarehouse(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchWarehouse", ssmParam);
    }

    /**
     * calcNetDueDate
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult calcNetDueDate(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("calcNetDueDate", ssmParam);
    }

    // START 2019/05/27 S.Takami [QC#50541,DEL]
//    /**
//     * getItemName
//     * @param bizMsg
//     * @param ssmParam
//     * @return
//     */
//    public S21SsmEZDResult getItemName(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
//        return getSsmEZDClient().queryObject("getItemName", ssmParam);
//    }
    // END 2019/05/27 S.Takami [QC#50541,DEL]

    /**
     * searchSalesRep
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchSalesRep(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchSalesRep", ssmParam);
    }

    /**
     * searchShipToAddr
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchShipToAddr(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsg("searchShipToAddr", ssmParam, bizMsg);
    }

    /**
     * searchShipToAddr_D
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchShipToAddr_D(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam, int idx) {
        return getSsmEZDClient().queryEZDMsg("searchShipToAddr_D", ssmParam, bizMsg.D.no(idx));
    }

    /**
     * searchBillToAddr
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchBillToAddr(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsg("searchBillToAddr", ssmParam, bizMsg);
    }

    /**
     * searchInv
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInv(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsg("searchInv", ssmParam, bizMsg);
    }

    /**
     * searchInvLine
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvLine(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("searchInvLine", ssmParam, bizMsg.A);
    }

    /**
     * searchInvSlsCr
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvSlsCr(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("searchInvSlsCr", ssmParam, bizMsg.B);
    }

    /**
     * searchInvAcctDist_Summary
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvAcctDist_Summary(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
//TODO C.SMsgに置き換える。※XMLも
        return getSsmEZDClient().queryEZDMsgArray("searchInvAcctDist_Summary", ssmParam, bizMsg.C);
    }

    /**
     * searchInvAcctDist_Edit
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvAcctDist_Edit(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
//TODO C.SMsgに置き換える。※XMLも
        return getSsmEZDClient().queryEZDMsgArray("searchInvAcctDist_Edit", ssmParam, bizMsg.C);
    }

    /**
     * searchInvAcctDist_F
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvAcctDist_F(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
//TODO C.SMsgに置き換える。※XMLも
        return getSsmEZDClient().queryEZDMsgArray("searchInvAcctDist_F", ssmParam, bizMsg.C);
    }

    /**
     * searchInvBOL
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvBOL(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("searchInvBOL", ssmParam, bizMsg.D);
    }

    /**
     * updateAjeInvAcctDist
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult updateAjeInvAcctDist(NFCL3000SMsg globalMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("updateAjeInvAcctDist", ssmParam, globalMsg.E);
    }

    // START 2016/05/24 S.Fujita [QC#8522,ADD]
    /**
     * searchInvSlsCrPk
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvSlsCrPk(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryEZDMsgArray("searchInvSlsCrPk", ssmParam, bizMsg.G);
    }
    // END   2016/05/24 S.Fujita [QC#8522,ADD]

    // START 2016/06/20 S.Fujita [QC#9454,ADD]
    /**
     * searchSellto
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchSellto(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchSellto", ssmParam);
    }

    /**
     * searchInvRcpnt
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvRcpnt(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchInvRcpnt", ssmParam);
    }

    /**
     * searchInvRcpntCust
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvRcpntCust(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchInvRcpntCust", ssmParam);
    }

    /**
     * searchFlPlnCmpyFlg
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchFlPlnCmpyFlg(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchFlPlnCmpyFlg", ssmParam);
    }

    /**
     * searchShipto2
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchShipto2(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchShipto2", ssmParam);
    }

    /**
     * searchSellToCust
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchSellToCust(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchSellToCust", ssmParam);
    }

    /**
     * searchCtacPsnList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchCtacPsnList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchCtacPsnList", ssmParam);
    }

    // START 2019/05/27 S.Takami [QC#50541,DEL]
//    /**
//     * searchCtacPsnList
//     * @param bizMsg
//     * @param ssmParam
//     * @return
//     */
//    public S21SsmEZDResult searchMdse(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
//        return getSsmEZDClient().queryObject("searchMdse", ssmParam);
//    }
    // END 2019/05/27 S.Takami [QC#50541,DEL]

    /**
     * searchTaxAcct
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchTaxAcct(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchTaxAcct", ssmParam);
    }

    /**
     * searchTaxShipto
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchTaxShipto(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchTaxShipto", ssmParam);
    }

    /**
     * searchSalesRepAdress
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchSalesRepAdress(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchSalesRepAdress", ssmParam);
    }

    /**
     * searchDsInvtyLoc
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchDsInvtyLoc(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("searchDsInvtyLoc", ssmParam);
    }

    /**
     * getSoldToCustLocNm
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getSoldToCustLocNm(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSoldToCustLocNm", ssmParam);
    }

    /**
     * searchDsInvSlsCrList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchDsInvSlsCrList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchDsInvSlsCrList", ssmParam);
    }

    /**
     * searchDsInvSlsCrList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchInvPrmoInfoList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchInvPrmoInfoList", ssmParam);
    }

    /**
     * searchAjeInvAcctDistDfrdList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchAjeInvAcctDistDfrdList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchAjeInvAcctDistDfrdList", ssmParam);
    }

    /**
     * searchDsInvSlsCrList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchDsInvLineTaxDtlList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchDsInvLineTaxDtlList", ssmParam);
    }
   // END   2016/06/20 S.Fujita [QC#9454,ADD]

    // START 2016/07/20 S.Fujita [QC#10148,ADD]
    /**
     * searchAjeInvAcctDistList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchAjeInvAcctDistList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchAjeInvAcctDistList", ssmParam);
    }

    /**
     * searchAjeInvAcctDistErrList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchAjeInvAcctDistErrList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchAjeInvAcctDistErrList", ssmParam);
    }

    /**
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsAcctNm(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getDsAcctNm", ssmParam);
    }

    /**
     * getChrgTpPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getChrgTpPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getChrgTpPullDownList", ssmParam);
    }

    /**
     * getCrDrRsnPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getCrDrRsnPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCrDrRsnPullDownList", ssmParam);
    }

    /**
     * getDsOrdCatgPullDownList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getDsOrdCatgPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getDsOrdCatgPullDownList", ssmParam);
    }
    // END   2016/07/20 S.Fujita [QC#10148,ADD]

    // START 2016/08/01 [QC#9192, ADD]
    /**
     * getTaxAreaId
     * @param inParam Map<String, Object>
     * @return String
     */
    public String getTaxAreaId(Map<String, Object> inParam) {
        return (String) getSsmEZDClient().queryObject("getTaxAreaId", inParam).getResultObject();
    }
    // END   2016/08/01 [QC#9192, ADD]

    // START 2016/11/17 [QC#14096, ADD]
    /**
     * getSerNumINV
     * @param bizMsg  NFCL3000CMsg 
     * @param aLineMsg NFCL3000_ACMsg 
     * @return String
     */
    public String getSerNumINV(NFCL3000CMsg bizMsg, NFCL3000_ACMsg aLineMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("invBolLineNum", aLineMsg.invBolLineNum_A1.getValue());
        ssmParam.put("invLineNum", aLineMsg.invLineNum_A1.getValue());
        ssmParam.put("invLineSubNum", aLineMsg.invLineSubNum_A1.getValue());
        ssmParam.put("invTrxLineSubNum", aLineMsg.invLineSubTrxNum_A1.getValue());

        return (String) getSsmEZDClient().queryObject("getSerNumINV", ssmParam).getResultObject();
    }
    /**
     * getSerNumSvcINV
     * @param inParam Map<String, Object>
     * @return String
     */
    public String getSerNumSvcINV(NFCL3000CMsg bizMsg, NFCL3000_ACMsg aLineMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("srcTpCdDSPT", SVC_INV_SRC_TP.DISPATCH);
        ssmParam.put("srcTpCdCNTR", SVC_INV_SRC_TP.CONTRACT);
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("invBolLineNum", aLineMsg.invBolLineNum_A1.getValue());
        ssmParam.put("invLineNum", aLineMsg.invLineNum_A1.getValue());
        ssmParam.put("invLineSubNum", aLineMsg.invLineSubNum_A1.getValue());
        ssmParam.put("invLineSubTrxNum", aLineMsg.invLineSubTrxNum_A1.getValue());

        return (String) getSsmEZDClient().queryObject("getSerNumSvcINV", ssmParam).getResultObject();
    }
    // End   2016/11/17 [QC#14096, ADD]

    // START 2016/11/22 S.Fujita [QC#16154,MOD]
    /**
     * getAjeInvAcctDistList
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getAjeInvAcctDistList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getAjeInvAcctDistList", ssmParam);
    }
    // END   2016/11/22 S.Fujita [QC#16154,MOD]

    // START 2018/05/22 Y.Matsui [QC#21841,ADD]
    /**
     * getInvLineCatgPullDownList
     * @param bizMsg NFCL3000CMsg
     * @param ssmParam Map<String, Object> 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvLineCatgPullDownList(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getInvLineCatgPullDownList", ssmParam);
    }
    // END   2018/05/22 Y.Matsui [QC#21841,ADD]
    // START 2018/07/19 E.Kameishi [QC#27007,ADD]
    /**
     * getPmtTermFromCustCrPrfl
     * @param bizMsg NFCL3000CMsg
     * @param ssmParam Map<String, Object> 
     * @return String
     */
    public String getPmtTermFromCustCrPrfl(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return (String) getSsmEZDClient().queryObject("getPmtTermFromCustCrPrfl", ssmParam).getResultObject();
    }
    /**
     * getPmtTermFromCustCrPrfl
     * @param bizMsg NFCL3000CMsg
     * @param ssmParam Map<String, Object> 
     * @return String
     */
    public String getPmtTermFromDsAcctCrPrfl(NFCL3000CMsg bizMsg, Map<String, Object> ssmParam) {
        return (String) getSsmEZDClient().queryObject("getPmtTermFromDsAcctCrPrfl", ssmParam).getResultObject();
    }

    // START 2019/04/25 S.Takami [QC#50078,MOD]
    /**
     * getInvLine
     * @param bizMsg Biz. Message
     * @param i index of Invoice Line
     * @return INV_LINETMsg
     */
    public INV_LINETMsg getInvLine(NFCL3000CMsg bizMsg, int i) {

        if (!ZYPCommonFunc.hasValue(bizMsg.glblCmpyCd) //
                || !ZYPCommonFunc.hasValue(bizMsg.invNum_H1) //
                || !ZYPCommonFunc.hasValue(bizMsg.A.no(i).invBolLineNum_A1) //
                || !ZYPCommonFunc.hasValue(bizMsg.A.no(i).invLineNum_A1) //
                || !ZYPCommonFunc.hasValue(bizMsg.A.no(i).invLineSubNum_A1) //
                || !ZYPCommonFunc.hasValue(bizMsg.A.no(i).invLineSubTrxNum_A1)) {
            return null;
        }
        INV_LINETMsg inInvLineTMsg = new INV_LINETMsg();
        ZYPEZDItemValueSetter.setValue(inInvLineTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inInvLineTMsg.invNum, bizMsg.invNum_H1);
        ZYPEZDItemValueSetter.setValue(inInvLineTMsg.invBolLineNum, bizMsg.A.no(i).invBolLineNum_A1);
        ZYPEZDItemValueSetter.setValue(inInvLineTMsg.invLineNum, bizMsg.A.no(i).invLineNum_A1);
        ZYPEZDItemValueSetter.setValue(inInvLineTMsg.invLineSubNum, bizMsg.A.no(i).invLineSubNum_A1);
        ZYPEZDItemValueSetter.setValue(inInvLineTMsg.invLineSubTrxNum, bizMsg.A.no(i).invLineSubTrxNum_A1);

        return (INV_LINETMsg) S21FastTBLAccessor.findByKey(inInvLineTMsg);
    }
//    /**
//     * getInvLine
//     * @param inParam Map<String, Object>
//     * @return String
//     */
//    public S21SsmEZDResult getInvLine(NFCL3000CMsg bizMsg, int i) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
//        ssmParam.put("invBolLineNum", bizMsg.A.no(i).invBolLineNum_A1.getValue());
//        ssmParam.put("invLineNum", bizMsg.A.no(i).invLineNum_A1.getValue());
//        ssmParam.put("invLineSubNum", bizMsg.A.no(i).invLineSubNum_A1.getValue());
//        ssmParam.put("invLineSubTrxNum", bizMsg.A.no(i).invLineSubTrxNum_A1.getValue());
//
//        return getSsmEZDClient().queryObject("getInvLine", ssmParam);
//    }
    // END 2019/04/25 S.Takami [QC#50078,MOD]
    // END 2018/07/19 E.Kameishi [QC#27007,ADD]

    // START 2018/09/19 S.Ohki [QC#28089,ADD]
    /**
     * isCreditApplied
     * @param bizMsg NFCL3000CMsg
     * @return boolean
     */
    public boolean isCreditApplied(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("arTrxNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("arTrxTpCdInv", AR_TRX_TP.INVOICE);
        ssmParam.put("arTrxTpCdCrm", AR_TRX_TP.CREDIT_MEMO);

        BigDecimal count = (BigDecimal) getSsmEZDClient().queryObject("isCreditApplied", ssmParam).getResultObject();

        return count.compareTo(BigDecimal.ZERO) > 0;
    }
    // END 2018/09/19 S.Ohki [QC#28089,ADD]

    // START 2019/04/25 S.Takami [QC#50078,ADD]
    /**
     * Get MDSE_PKG_UOM record(s) by Item Code and Base Package UOM Code
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Mdse Code
     * @param basePkgUomCd Base Package UOM Cd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPkgUomByBaseUomCd(String glblCmpyCd, String mdseCd, String basePkgUomCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("basePkgUomCd", basePkgUomCd);

        return getSsmEZDClient().queryObjectList("getPkgUomByBaseUomCd", ssmParam);
    }

    /**
     * <pre>
     * Get BLLG_CYCLE_PULL_DOWN PullDown List
     * @param bizMsg Biz. Message
     * @return S21SsmEZDResult of BLLG_CYCLE_UOM
     * </pre>
     */
    public S21SsmEZDResult getBllgCycleUomPullDownList(NFCL3000CMsg bizMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>(0);
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        if (this.bllgCycleUomMap == null) {
            this.bllgCycleUomMap = new HashMap<Map<String, String>, S21SsmEZDResult>(0);
        }
        S21SsmEZDResult ssmRslt = this.bllgCycleUomMap.get(ssmParam);
        if (ssmRslt == null) {
            ssmRslt = getSsmEZDClient().queryObjectList("getBllgCycleUomPullDownList", ssmParam);
            if (ssmRslt.isCodeNormal()) {
                this.bllgCycleUomMap.put(ssmParam, ssmRslt);
            }
        }

        return ssmRslt;
    }
    // END 2019/04/25 S.Takami [QC#50078,ADD]
    // START 2019/05/27 S.Takami [QC#50541,ADD]
    /**
     * <pre>
     * Get TAX_ADJ_ITEMTMsg from parameter glblCmpyCd, and taxAdjItemMdseCd.
     * This method cache the TAX_ADJ_TEM record.
     * @param glblCmpyCd Global Company Code
     * @param taxAdjItemMdseCd check item for TAX_ADJ_ITEM or not.
     * @return if parameter taxAdjItemMdseCd is TAX_ADJ_ITEM, return TAX_ADJ_ITEMTMsg.
     * null means parameter taxAdjItemMdseCd is not TAX_ADJ_ITEM.
     * </pre>
     */
    public TAX_ADJ_ITEMTMsg getTaxAdjItem(String glblCmpyCd, String taxAdjItemMdseCd) {

        if (this.taxAdjItemTMsgList == null) {
            this.taxAdjItemTMsgList = new ArrayList<TAX_ADJ_ITEMTMsg>(0);
            Map<String, String> ssmParam = new HashMap<String, String>(0);
            ssmParam.put("glblCmpyCd", glblCmpyCd);

            S21SsmEZDResult ssmRslt = getSsmEZDClient().queryObjectList("getTaxAdjItem", ssmParam);
            if (ssmRslt.isCodeNormal()) {
                for (TAX_ADJ_ITEMTMsg taxAdjItemTMsg : (List<TAX_ADJ_ITEMTMsg>) ssmRslt.getResultObject()) {
                    this.taxAdjItemTMsgList.add((TAX_ADJ_ITEMTMsg) taxAdjItemTMsg.clone());
                }
            }
        }
        for (TAX_ADJ_ITEMTMsg taxAdjItemTMsg : this.taxAdjItemTMsgList) {
            if (S21StringUtil.isEquals(taxAdjItemTMsg.mdseCd.getValue(), taxAdjItemMdseCd)) {
                return taxAdjItemTMsg;
            }
        }
        return null;
    }
    // END 2019/05/27 S.Takami [QC#50541,ADD]
    // START 2021/01/04 R.Kurahashi [QC#56282, ADD]
    /**
     * getSrcTpCd
     * @param bizMsg NFCL3000CMsg
     * @param aLineMsg NFCL3000_ACMsg
     * @return String
     */
    public String getSrcTpCd(NFCL3000CMsg bizMsg, NFCL3000_ACMsg aLineMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invNum", bizMsg.invNum_H1.getValue());
        ssmParam.put("invBolLineNum", aLineMsg.invBolLineNum_A1.getValue());
        ssmParam.put("invLineNum", aLineMsg.invLineNum_A1.getValue());
        ssmParam.put("invLineSubNum", aLineMsg.invLineSubNum_A1.getValue());
        ssmParam.put("invLineSubTrxNum", aLineMsg.invLineSubTrxNum_A1.getValue());

        return (String) getSsmEZDClient().queryObject("getSrcTpCd", ssmParam).getResultObject();
    }
    // END 2020/01/04 R.Kurahashi [QC#56282, ADD]
    // START 2021/10/25 S.Go [QC#55215, ADD]
    /**
     * getCOALineBizTpCd
     * @param bizMsg NFCL3000CMsg
     * @return String
     */
    public String getCOALineBizTpCd(NFCL3000CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("invSlsRepTocCd", bizMsg.slsRepTocCd_H1.getValue());

        return (String) getSsmEZDClient().queryObject("getCOALineBizTpCd", ssmParam).getResultObject();
    }
    // END 2021/10/25 S.Go [QC#55215, ADD]
}
