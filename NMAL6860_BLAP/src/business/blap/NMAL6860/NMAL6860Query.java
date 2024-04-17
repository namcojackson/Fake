/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6860;

import static business.blap.NMAL6860.constant.NMAL6860Constant.DEF_THRU_DT;

import java.util.HashMap;
import java.util.Map;

import business.parts.NMZC201001PMsg;
import business.parts.NMZC201002PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NMAL6860 Supplier Entry
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/04   CITS            M.Ouchi         Create          N/A
 * 08/09/2016   CITS            S.Endo          Update          QC#10839
 * 09/06/2016   CITS            T.Gotoda        Update          QC#13264
 * 09/15/2016   CITS            T.Gotoda        Update          QC#13313
 * 09/26/2016   CITS            T.Gotoda        Update          QC#13163
 * 06/11/2018   CITS            K.Ogino         Update          QC#26498
 * 08/09/2018   CITS            K.Ogino         Update          QC#27686
 * 08/31/2020   CITS            R.Kurahashi     Update          QC#57546
 * 10/28/2020   CITS            R.Kurahashi     Update          QC#57732
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 * </pre>
 */
public class NMAL6860Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL6860Query INSTANCE = new NMAL6860Query();

    /**
     * Default Constructor.
     */
    public NMAL6860Query() {
        super();
    }

    /**
     * <p>
     * Gets the singleton instance.
     * </p>
     * @return the instance of NMAL6860Query
     */
    public static NMAL6860Query getInstance() {
        return INSTANCE;
    }

    /**
     * <p>
     * Searches Supplier Sites.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     * @return Supplier
     */
    public S21SsmEZDResult searchSupplier(NMAL6860CMsg cMsg, NMAL6860SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("prntVndPk", cMsg.prntVndPk_P);

        return getSsmEZDClient().queryEZDMsg("searchSupplier", params, sMsg);
    }

    /**
     * <p>
     * Searches Supplier Sites.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     * @return Supplier Sites
     */
    public S21SsmEZDResult searchSupplierSites(NMAL6860CMsg cMsg, NMAL6860SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("prntVndPk", cMsg.prntVndPk_P);
        params.put("vndTpCd", VND_TP.SUPPLIER);

        return getSsmEZDClient().queryEZDMsgArray("searchSupplierSites", params, sMsg.A);
    }

    /**
     * <p>
     * Searches Contact Info.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     * @return Supplier Site
     */
    public S21SsmEZDResult searchContactInfo(NMAL6860CMsg cMsg, NMAL6860SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("defThruDt", DEF_THRU_DT);
        params.put("dsCtacPntTpCd_T", DS_CTAC_PNT_TP.PHONE_WORK);
        params.put("dsCtacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
        params.put("dsCtacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("prntVndPk", cMsg.prntVndPk_P);
        params.put("ctacPsnActvFlg", ZYPConstant.FLG_ON_Y);
        // QC#27686
        params.put("ctacTpCdDI", CTAC_TP.DELIVERY_INSTALL);

        return getSsmEZDClient().queryEZDMsgArray("searchContactInfo", params, sMsg.B);
    }

    /**
     * <p>
     * Search Account Customer.
     * </p>
     * @param cMsg bizMsg
     * @return Account Customer
     */
    public S21SsmEZDResult searchAccountCustomer(NMAL6860CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("billToCustCd", cMsg.billToCustCd_P);

        return getSsmEZDClient().queryObject("searchAccountCustomer", params);

    }

    /**
     * <p>
     * Search Default Vendor Info.
     * </p>
     * @param cMsg bizMsg
     * @return Account Customer
     */
    public S21SsmEZDResult searchDefaultVendorInfo(NMAL6860CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("prntVndTpCd", cMsg.prntVndTpCd);

        return getSsmEZDClient().queryObject("searchDefaultVendorInfo", params);

    }

    /**
     * <p>
     * Check Direct Sales Vendor.
     * </p>
     * @param cMsg bizMsg
     * @return Direct Sales Vendor
     */
    public S21SsmEZDResult checkDirectSalesVendor(NMAL6860CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("dsAcctNum", cMsg.dsAcctNum_P);
        params.put("prntVndTpCd", cMsg.prntVndTpCd);

        return getSsmEZDClient().queryObject("checkDirectSalesVendor", params);
    }

    /**
     * <p>
     * Check Direct Account Customer.
     * </p>
     * @param cMsg bizMsg
     * @return Account Customer
     */
    public S21SsmEZDResult checkAccountCustomer(NMAL6860CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("dsAcctNum", cMsg.dsAcctNum_H1);

        return getSsmEZDClient().queryObjectList("checkAccountCustomer", params);
    }

    /**
     * <p>
     * Check Bill To Customer.
     * </p>
     * @param cMsg bizMsg
     * @return Bill To Customer
     */
    public S21SsmEZDResult checkBillToCustomer(NMAL6860CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("billToCustCd", cMsg.billToCustCd_H2);

        return getSsmEZDClient().queryObjectList("checkBillToCustomer", params);
    }

    /**
     * <p>
     * Check Bill To Customer.
     * </p>
     * @param cMsg bizMsg
     * @return Bill To Customer
     */
    public S21SsmEZDResult checkCustomerLocationAndNumber(NMAL6860CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("billToCustCd", cMsg.billToCustCd_H2);
        params.put("sellToCustCd", cMsg.dsAcctNum_H1);

        return getSsmEZDClient().queryObjectList("checkCustomerLocationAndNumber", params);
    }

    /**
     * <p>
     * Get Parent Vendor PK
     * </p>
     * @param cMsg bizMsg
     * @return Account Customer
     */
    public S21SsmEZDResult getPrntVndPk(NMZC201001PMsg pMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd);
        params.put("prntVndCd", pMsg.prntVndCd);
        params.put("inacDt", pMsg.inacDt);

        return getSsmEZDClient().queryObject("getPrntVndPk", params);
    }

    /**
     * @param cMsg
     * @param pMsg
     * @return
     */
    public S21SsmEZDResult getCreatedLocNum(NMAL6860CMsg cMsg, NMZC201002PMsg pMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd);
        params.put("vndCd", pMsg.vndCd);

        return getSsmEZDClient().queryObject("getCreatedLocNum", params);
    }

    /**
     * getAddrByPost
     * @param glblCmpyCd String
     * @param postCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddrByPost(String glblCmpyCd, String postCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("postCd", postCd);

        return getSsmEZDClient().queryObjectList("getAddrByPost", params);
    }

    /**
     * getCtacTpList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacTpList(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return getSsmEZDClient().queryObjectList("getCtacTpList", params);
    }

    /**
     * getPrntVndTpList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrntVndTpList(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        return getSsmEZDClient().queryObjectList("getPrntVndTpList", params);
    }

    /**
     * getVndPmtTermCd
     * @param glblCmpyCd String
     * @param vndPmtTermDescTxt String
     * @return vndPmtTermCd
     */
    public S21SsmEZDResult getVndPmtTermCd(String glblCmpyCd, String vndPmtTermDescTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("vndPmtTermDescTxt", vndPmtTermDescTxt);

        return getSsmEZDClient().queryObject("getVndPmtTermCd", params);
    }
    /**
     * QC#26498 Add
     * getPrntVndPkforNm
     * @param bizMsg NMAL6860CMsg
     * @return prntVndPk
     */
    public S21SsmEZDResult getPrntVndPkforNm(NMAL6860CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("prntVndNm", bizMsg.prntVndNm.getValue());
        params.put("prntVndTpCd", bizMsg.prntVndTpCd.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.prntVndPk_P)) {
            params.put("prntVndPk", bizMsg.prntVndPk_P.getValue());
        }

        return getSsmEZDClient().queryObject("getPrntVndPkforNm", params);
    }
    // START 2020/08/31 R.Kurahashi [QC#57546,ADD]
    /**
     * getCustInfoForPrimBill
     * @param glblCmpyCd String
     * @param acctNum String
     * @param rgtnStsCd String
     * @return billToCustCd
     */
    public S21SsmEZDResult getCustInfoForPrimBill(String glblCmpyCd, String acctNum, String rgtnStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("acctNum", acctNum);
        params.put("rgtnStsCd", rgtnStsCd);

        return getSsmEZDClient().queryObject("getCustInfoForPrimBill", params);
    }
    
    /**
     * getCustInfoForPrimShip
     * @param glblCmpyCd String
     * @param acctNum String
     * @param rgtnStsCd String
     * @return shipToCustCd
     */
    public S21SsmEZDResult getCustInfoForPrimShip(String glblCmpyCd, String acctNum, String rgtnStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("acctNum", acctNum);
        params.put("rgtnStsCd", rgtnStsCd);

        return getSsmEZDClient().queryObject("getCustInfoForPrimShip", params);
    }
    
    /**
     * getBillToShipToFromPrimLoc
     * @param glblCmpyCd String
     * @param acctNum String
     * @param rgtnStsCd String
     * @param rownum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToShipToFromPrimLoc(String glblCmpyCd, String acctNum, String rgtnStsCd, int rownum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("acctNum", acctNum);
        params.put("rgtnStsCd", rgtnStsCd);
        params.put("rownum", rownum);

        return getSsmEZDClient().queryObjectList("getBillToShipToFromPrimLoc", params);
    }
    // END 2020/08/31 R.Kurahashi [QC#57546,ADD]
    // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
    /**
     * getPrntVndNm
     * @param bizMsg NMAL6860CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrntVndNm(NMAL6860CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd);
        params.put("prntVndNm", bizMsg.prntVndNm);
        params.put("prntVndPk", bizMsg.prntVndPk_P);

        return getSsmEZDClient().queryObjectList("getPrntVndNm", params);
    }
    
    /**
     * getPrntVndNmForPrntVndTpCd
     * @param bizMsg NMAL6860CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrntVndNmForPrntVndTpCd(NMAL6860CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd);
        params.put("prntVndNm", bizMsg.prntVndNm);
        params.put("prntVndPk", bizMsg.prntVndPk_P);
        params.put("prntVndTpCd", bizMsg.prntVndTpCd);

        return getSsmEZDClient().queryObjectList("getPrntVndNmForPrntVndTpCd", params);
    }
    // END 2020/10/28 R.Kurahashi [QC#57732,ADD]

    // START 2021/03/01 G.Delgado [QC#56057,ADD]
    /**
     * checkSupplierOpenPo
     * @param bizMsg NMAL6860CMsg
     * @param vndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSupplierOpenPo(NMAL6860CMsg bizMsg, String vndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd);
        params.put("prntVndCd", bizMsg.prntVndCd);
        params.put("vndCd", vndCd);

        return getSsmEZDClient().queryObjectList("checkSupplierOpenPo", params);
    }

    /**
     * checkSupplierOpenAp
     * @param bizMsg NMAL6860CMsg
     * @param acctInvStsCdConst String
     * @param vndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkSupplierOpenAp(NMAL6860CMsg bizMsg, String acctInvStsCdConst, String vndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd);
        params.put("prntVndCd", bizMsg.prntVndCd);
        params.put("openAcctInvStsCds", acctInvStsCdConst.split(","));
        params.put("vndCd", vndCd);

        return getSsmEZDClient().queryObjectList("checkSupplierOpenAp", params);
    }

    /**
     * getPrntVndTpCdforDescTxt
     * @param glblCmpyCd String
     * @param prntVndTpDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrntVndTpCdforDescTxt(String glblCmpyCd, String prntVndTpDescTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntVndTpDescTxt", prntVndTpDescTxt);

        return getSsmEZDClient().queryObject("getPrntVndTpCdforDescTxt", params);
    }
    // END 2021/03/01 G.Delgado [QC#56057,ADD]
}
