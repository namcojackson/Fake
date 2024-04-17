/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27   Fujitsu         T.Yoshida       Create          N/A
 * 2017/08/09   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2019/10/03   Fujitsu         A.Kazuki        Update          QC#53595
 * </pre>
 */
public final class NWAL1770QueryForSaveSubmit extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770QueryForSaveSubmit MY_INSTANCE = new NWAL1770QueryForSaveSubmit();

    /**
     * Constructor.
     */
    private NWAL1770QueryForSaveSubmit() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770Query
     */
    public static NWAL1770QueryForSaveSubmit getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Retail Warehouse Information
     * @param bizMsg NWAL1770CMsg
     * @param itemLineMsg NWAL1770_BCMsg
     * @return Retail Warehouse Information
     */
    public S21SsmEZDResult getRtlWhInfo(NWAL1770CMsg bizMsg, NWAL1770_BCMsg itemLineMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("rtlWhNm", itemLineMsg.rtlWhNm_B.getValue());
        params.put("rtlSwhNm", itemLineMsg.rtlSwhNm_B.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getRtlWhInfo", params);
    }

    /**
     * Check Duplication PO Number For Quote
     * @param bizMsg NWAL1770CMsg
     * @return true: Duplication
     */
    public boolean isDuplicationPoNumForQuote(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("saved", SPLY_QUOTE_STS.SAVED);
        params.put("splyQuoteNum", bizMsg.splyQuoteNum.getValue());
        params.put("splyQuoteStsDescTxt", bizMsg.splyQuoteStsDescTxt.getValue());
        params.put("custIssPoNum", bizMsg.custIssPoNum.getValue());
        params.put("sellToCustCd", bizMsg.sellToCustCd.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isDuplicationPoNumForQuote", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * Check Duplication PO Number For Order
     * @param bizMsg NWAL1770CMsg
     * @return true: Duplication
     */
    public boolean isDuplicationPoNumForOrder(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cancelled", ORD_HDR_STS.CANCELLED);
        params.put("custIssPoNum", bizMsg.custIssPoNum.getValue());
        params.put("sellToCustCd", bizMsg.sellToCustCd.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isDuplicationPoNumForOrder", params);

        return 0 < (Integer) result.getResultObject();
    }
    
    /**
     * Check Duplication PO Number For Order
     * @param bizMsg NWAL1770CMsg
     * @return true: Y
     */
    public boolean getBllgAttrbReqFlg(String glblCmpyCd, String dsAcctNum, String bllgAttrbNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsAcctNum", dsAcctNum);
        params.put("bllgAttrbNm", bllgAttrbNm);

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getBllgAttrbReqFlg", params);
        if (result.isCodeNotFound()) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals((String) result.getResultObject())){
            return true;
        } else {
            return false;
        }
    }

    // QC#16452 add Start
    /**
     * get Loction Number
     * @param bizMsg NWAL1770CMsg
     * @param ctacMsg NWAL1770_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationNumber(NWAL1770CMsg bizMsg, NWAL1770_ACMsg ctacMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        String query = null;
        String ctacCustRefTpCd = ctacMsg.ctacCustRefTpCd_A.getValue();
        if (CTAC_CUST_REF_TP.BILL_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", bizMsg.billToCustCd.getValue());
            query = "getBillToLocationNumber";

        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", bizMsg.shipToCustCd.getValue());
            query = "getShipToLocationNumber";

        } else if (CTAC_CUST_REF_TP.SOLD_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", bizMsg.soldToCustLocCd.getValue());
            query = "getBillToLocationNumber";

        } else {
            ssmParam.put("custCd", bizMsg.shipToCustCd.getValue());
            query = "getShipToLocationNumber";
        }
        ssmParam.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rowNum", 1);

        return getSsmEZDClient().queryObject(query, ssmParam);
    }
    // QC#16452 add End

    // 2019/10/03 QC#53595 Add Start
    /**
     * cntOrdTpCd.
     * @param bizMsg NWAL1500CMsg
     * @return BigDecimal
     */
    public BigDecimal cntOrdTpCd(NWAL1770CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("ordTpCd", bizMsg.dsOrdTpCd.getValue());
        
        return (BigDecimal) getSsmEZDClient().queryObject("cntOrdTpCd", params).getResultObject();
    }

    /**
     * cntAssignedISGroup.
     * @param bizMsg NWAL1500CMsg
     * @return BigDecimal
     */
    public BigDecimal cntAssignedISGroup(NWAL1770CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("psnNum", bizMsg.psnNum.getValue());

        return (BigDecimal) getSsmEZDClient().queryObject("cntAssignedISGroup", params).getResultObject();
    }
    // 2019/10/03 QC#53595 Add End
}
