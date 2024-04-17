/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import static business.blap.NWAL1770.constant.NWAL1770Constant.PRNT_SUB_LINE_NUM;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         T.Yoshida       Create          N/A
 * 2017/08/09   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2018/04/04   Fujitsu         K.Ishizuka      Update          S21_NA#24988
 * </pre>
 */
public final class NWAL1770QueryForSearch extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770QueryForSearch MY_INSTANCE = new NWAL1770QueryForSearch();

    /**
     * Constructor.
     */
    private NWAL1770QueryForSearch() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770Query
     */
    public static NWAL1770QueryForSearch getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Supply Quote Header
     * @param bizMsg NWAL1770CMsg
     * @return Supply Quote Header
     */
    public S21SsmEZDResult getSplyQuoteHdr(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("splyQuoteNum", bizMsg.splyQuoteNum.getValue());

        return getSsmEZDClient().queryEZDMsg("getSplyQuoteHdr", params, bizMsg);
    }

    /**
     * get Supply Quote Detail
     * @param bizMsg NWAL1770CMsg
     * @return Supply Quote Detail
     */
    // 2018/04/04 S21_NA#24988 Mod Start
    // public S21SsmEZDResult getSplyQuoteDtl(NWAL1770CMsg bizMsg) {
    public S21SsmEZDResult getSplyQuoteDtl(NWAL1770CMsg bizMsg, boolean isCallCopy) {
    // 2018/04/04 S21_NA#24988 Mod End

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("splyQuoteNum", bizMsg.splyQuoteNum.getValue());
        params.put("prntSubLineNum", PRNT_SUB_LINE_NUM);
        // 2018/04/04 S21_NA#24988 Add Start
        if (isCallCopy) {
            params.put("splyQuoteLineStsCd", SPLY_QUOTE_STS.CANCELLED);
        }
        // 2018/04/04 S21_NA#24988 Add End

        return getSsmEZDClient().queryEZDMsgArray("getSplyQuoteDtl", params, bizMsg.B);
    }

    /**
     * get Supply Quote Sales Credit
     * @param bizMsg NWAL1770CMsg
     * @return Supply Quote Sales Credit
     */
    public S21SsmEZDResult getSplyQuoteSalesCredit(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("splyQuoteNum", bizMsg.splyQuoteNum.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getSplyQuoteSalesCredit", params, bizMsg.D);
    }

    /**
     * get Supply Quote Contact Person
     * @param bizMsg NWAL1770CMsg
     * @return Supply Quote Contact Person
     */
    public S21SsmEZDResult getSplyQuoteCtacPsn(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("splyQuoteNum", bizMsg.splyQuoteNum.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getSplyQuoteCtacPsn", params, bizMsg.A);
    }

    /**
     * get Supply Quote Additional Data
     * @param bizMsg NWAL1770CMsg
     * @return Supply Quote Additional Data
     */
    public S21SsmEZDResult getSplyQuoteAddlData(NWAL1770CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("splyQuoteNum", bizMsg.splyQuoteNum.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getSplyQuoteAddlData", params, bizMsg.C);
    }

    /**
     * get Supply Quote Price Calc Base
     * @param bizMsg NWAL1770CMsg
     * @return Supply Quote Contact Person
     */
    // 2018/04/04 S21_NA#24988 Mod Start
    // public S21SsmEZDResult getSplyQuotePrcCalcBase(NWAL1770CMsg bizMsg) {
    public S21SsmEZDResult getSplyQuotePrcCalcBase(NWAL1770CMsg bizMsg, boolean isCallCopy) {
    // 2018/04/04 S21_NA#24988 Mod End

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("splyQuoteNum", bizMsg.splyQuoteNum.getValue());
        // 2018/04/04 S21_NA#24988 Add Start
        if (isCallCopy) {
            params.put("splyQuoteLineStsCd", SPLY_QUOTE_STS.CANCELLED);
        }
        // 2018/04/04 S21_NA#24988 Add End

        return getSsmEZDClient().queryEZDMsgArray("getSplyQuotePrcCalcBase", params, bizMsg.E);
    }

    /**
     * Get Confirmation Output List
     * @param bizMsg NWAL1770CMsg
     * @return Confirmation Output List
     */
    public S21SsmEZDResult getConfOutputList(NWAL1770CMsg bizMsg) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("splyQuoteNum", bizMsg.splyQuoteNum.getValue());

        return getSsmEZDClient().queryObjectList("getConfOutputList", params);
    }

    //QC#16452 add Start
    /**
     * Contact Customer Reference Search
     * @param bizMsg NWAL1770CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacCustRefTp(NWAL1770CMsg bizMsg, String ctacTpCd, String ctacCustRefTpCd) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        NWAL1770_ACMsg ctacMsg = bizMsg.A.no(idx);
        String billShipCd = bizMsg.shipToCustCd.getValue();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ctacPsnPk", ctacMsg.ctacPsnPk_A.getValue());
        params.put("ctacTpCd", ctacTpCd);
        if (CTAC_CUST_REF_TP.BILL_TO.equals(ctacCustRefTpCd)) {
            billShipCd = bizMsg.billToCustCd.getValue();
            params.put("bill", ZYPConstant.FLG_ON_Y);

        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(ctacCustRefTpCd)) {
            billShipCd = bizMsg.shipToCustCd.getValue();
            params.put("ship", ZYPConstant.FLG_ON_Y);

        } else if (CTAC_CUST_REF_TP.SOLD_TO.equals(ctacCustRefTpCd)) {
            billShipCd = bizMsg.soldToCustLocCd.getValue();
            params.put("bill", ZYPConstant.FLG_ON_Y);

        } else {
            billShipCd = bizMsg.shipToCustCd.getValue();
            params.put("ship", ZYPConstant.FLG_ON_Y);
        }

        params.put("billShipCd", billShipCd);

        return getSsmEZDClient().queryObject("getCtacCustRefTp", params);

    }
    //QC#16452 add End

}
