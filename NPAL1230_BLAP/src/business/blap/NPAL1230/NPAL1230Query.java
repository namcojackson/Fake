/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1230;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 * 2018/01/12   CITS            S.Katsuma       Update          QC#12226
 * 2021/01/15   CITS            J.Evangelista   Update          QC#58165
 *</pre>
 */
public final class NPAL1230Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1230Query MY_INSTANCE = new NPAL1230Query();

    /**
     * Constructor.
     */
    private NPAL1230Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1230Query
     */
    public static NPAL1230Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of Merchandise Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMerchandiseTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getMerchandiseTypePulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Merchandise Type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUomTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getUomTypePulldownList", ssmParam);
    }

    /**
     * Get Item Description
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemDescription(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getItemDescription", ssmParam);
    }

    /**
     * Get COA mdse type
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoaMdseTp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getCoaMdseTp", ssmParam);
    }

    /**
     * getItemInfo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemInfo(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getItemInfo", ssmParam);

    }

    /**
     * Get Supplier Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplierName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSupplierName", ssmParam);
    }

    /**
     * Get ASL Header
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1230SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAslHdr(Map<String, Object> ssmParam, NPAL1230SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsg("getAslHdr", ssmParam, sMsg);
    }

    /**
     * Get ASL Qualifier
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1230SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAslQlfyReln(Map<String, Object> ssmParam, NPAL1230SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getAslQlfyReln", ssmParam, sMsg.Q);
    }

    /**
     * Get ASL Detail
     * @param ssmParam Map<String, Object>
     * @param sMsg NPAL1230SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAslDtl(Map<String, Object> ssmParam, NPAL1230SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getAslDtl", ssmParam, sMsg.A);
    }

    /**
     * Check Mdse Code
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkMdseCd(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkMdseCd", ssmParam);
    }

    /**
     * Check Supplier
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkSupplier(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkSupplier", ssmParam);
    }

    /**
     * Check ASL Detail
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkAslDtl(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkAslDtl", ssmParam);
    }

    /**
     * Check Supplier Site
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkSupplierSite(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkSupplierSite", ssmParam);
    }

    /**
     * Get Prime supply flag
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrimSplyFlg(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPrimSplyFlg", ssmParam);
    }

    /**
     * Check ASL Name duplicate
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkAslNm(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("chkAslNm", ssmParam);
    }

    /**
     * Get ASL_DTL_PK
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAslDtlPk(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getAslDtlPk", ssmParam);
    }

    // START 2018/01/12 S.Katsuma [QC#12226,ADD]
    /**
     * Get Supplier Name
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplierSiteName(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSupplierSiteName", ssmParam);
    }
    // END 2018/01/12 S.Katsuma [QC#12226,ADD]

    // START 2021/01/15 J.Evangelista [QC#58165,ADD]
    /**
     * Get Supplier Item Code Max Length
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupplierItemCodeMaxLength(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObject("getSupplierItemCodeMaxLength", ssmParam);
    }
    // END 2021/01/15 J.Evangelista [QC#58165,ADD]
}
