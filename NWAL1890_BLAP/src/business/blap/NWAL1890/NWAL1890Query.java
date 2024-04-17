/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1890;

import static business.blap.NWAL1890.constant.NWAL1890Constant.CANCELLED;
import static business.blap.NWAL1890.constant.NWAL1890Constant.CLOSED;
import static business.blap.NWAL1890.constant.NWAL1890Constant.LINE_CONFIG_MODE;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 *  Order Line Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/10   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public final class NWAL1890Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1890Query MY_INSTANCE = new NWAL1890Query();

    /**
     * Private constructor
     */
    private NWAL1890Query() {
        super();
    }

    /**
     * Get the NWAL1890Query instance.
     * @return NWAL1890Query instance
     */
    public static NWAL1890Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Config Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigNum(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dsOrdPosnNum", bizMsg.dsOrdPosnNum.getValue());
        if (LINE_CONFIG_MODE.equals(bizMsg.xxModeCd.getValue())) {
            params.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        } else {
            params.put("configCatgCd", CONFIG_CATG.INBOUND);
        }

        return getSsmEZDClient().queryObjectList("getConfigNum", params);
    }

    /**
     * get Config ID
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigID(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("svcConfigMstrPk", bizMsg.xxConfigIdSrchTxt.getValue());
        if (LINE_CONFIG_MODE.equals(bizMsg.xxModeCd.getValue())) {
            params.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        } else {
            params.put("configCatgCd", CONFIG_CATG.INBOUND);
        }

        return getSsmEZDClient().queryObjectList("getConfigID", params);
    }

    /**
     * get Model
     * @param bizMsg NWAL1890CMsg
     * @param mdlParam String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getModel(NWAL1890CMsg bizMsg, String mdlParam) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        if (LINE_CONFIG_MODE.equals(bizMsg.xxModeCd.getValue())) {
            params.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        } else {
            params.put("configCatgCd", CONFIG_CATG.INBOUND);
        }
        params.put("mdlNm", mdlParam);

        return getSsmEZDClient().queryObjectList("getModel", params);
    }

    /**
     * get Bill To Cust Name
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustNm(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("billToCustNm", bizMsg.xxBillToAcctNmSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getBillToCustNm", params);
    }

    /**
     * get Bill To Account Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustCd(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("billToCustCd", bizMsg.xxBillToAcctCdSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getBillToCustCd", params);
    }

    /**
     * get Bill To Location Code
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToLocCd(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("billToLocCd", bizMsg.xxBillToLocCdSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getBillToLocCd", params);
    }

    /**
     * get Ship To Cust Name
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustNm(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("shipToCustNm", bizMsg.xxShipToAcctNmSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getShipToCustNm", params);
    }

    /**
     * get Ship To Account Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToCustCd(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("shipToCustCd", bizMsg.xxShipToAcctCdSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getShipToCustCd", params);
    }

    /**
     * get Ship To Location Code
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToLocCd(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("shipToLocCd", bizMsg.xxShipToLocCdSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getShipToLocCd", params);
    }

    /**
     * get Sold To Cust Name
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoldToCustNm(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("soldToCustNm", bizMsg.xxSoldToAcctNmSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getSoldToCustNm", params);
    }

    /**
     * get Sold To Account Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoldToCustCd(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("sellToCustCd", bizMsg.xxSoldToAcctCdSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getSoldToCustCd", params);
    }

    /**
     * get Sold To Location Code
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSoldToLocCd(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("soldToLocCd", bizMsg.xxSoldToLocCdSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getSoldToLocCd", params);
    }

    /**
     * get Line Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineNum(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dplyLineNum", bizMsg.xxLineNum.getValue());

        return getSsmEZDClient().queryObjectList("getLineNum", params);
    }

    /**
     * get Line Status
     * @param bizMsg NWAL1890CMsg
     * @param lineStsParam String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineSts(NWAL1890CMsg bizMsg, String lineStsParam) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("ordLineDplySts", lineStsParam);
        params.put("cancelled", CANCELLED);
        params.put("closed", CLOSED);

        return getSsmEZDClient().queryObjectList("getLineSts", params);
    }

    /**
     * get Ordered Item
     * @param bizMsg NWAL1890CMsg
     * @param ordItemParam String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdItem(NWAL1890CMsg bizMsg, String ordItemParam) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("mdseCd", ordItemParam);

        return getSsmEZDClient().queryObjectList("getOrdItem", params);
    }

    /**
     * get Warehouse
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWh(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("whNm", bizMsg.xxRtlWhSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getWh", params);
    }

    /**
     * get Sub Warehouse
     * @param bizMsg NWAL1890CMsg
     * @param swhNmParam Strings
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSwh(NWAL1890CMsg bizMsg, String swhNmParam) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("swhNm", swhNmParam);

        return getSsmEZDClient().queryObjectList("getSwh", params);
    }

    /**
     * get Source Type
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSrcType(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("ordLineSrcNm", bizMsg.xxCpoSrcTpSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getSrcType", params);
    }

    /**
     * get Line Source Reference Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLineSrcRef(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("lineSrcRefNum", bizMsg.xxOrdSrcRefNumSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getLineSrcRef", params);
    }

    /**
     * get Substitute Item
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSbstItem(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("sbstItem", bizMsg.xxSbstItemSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getSbstItem", params);
    }

    /**
     * get Serial Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSerNum(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("serNum", bizMsg.xxSerNumSrchTxt.getValue());

        return getSsmEZDClient().queryObjectList("getSerNum", params);
    }

    /**
     * get RMA Line Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRMALineNum(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("dplyLineNum", bizMsg.xxLineNum_R.getValue());

        return getSsmEZDClient().queryObjectList("getRMALineNum", params);
    }

    /**
     * get RMA Line Status
     * @param bizMsg NWAL1890CMsg
     * @param rmaLineStsParam String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRMALineSts(NWAL1890CMsg bizMsg, String rmaLineStsParam) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("rtrnLineDplySts", rmaLineStsParam);
        params.put("cancelled", CANCELLED);
        params.put("closed", CLOSED);

        return getSsmEZDClient().queryObjectList("getRMALineSts", params);
    }

    /**
     * get RMA Ordered Item
     * @param bizMsg NWAL1890CMsg
     * @param rmaOrdItemParam String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRMAOrdItem(NWAL1890CMsg bizMsg, String rmaOrdItemParam) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("mdseCd", rmaOrdItemParam);

        return getSsmEZDClient().queryObjectList("getRMAOrdItem", params);
    }

    /**
     * get RMA Return Reason
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRMARtrnRsn(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("rtrnRsnDescTxt", bizMsg.xxRtrnRsnSrchTxt_R.getValue());

        return getSsmEZDClient().queryObjectList("getRMARtrnRsn", params);
    }

    /**
     * get RMA Warehouse
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRMAWh(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("whNm", bizMsg.xxRtlWhSrchTxt_R.getValue());

        return getSsmEZDClient().queryObjectList("getRMAWh", params);
    }

    /**
     * get RMA Sub Warehouse
     * @param bizMsg NWAL1890CMsg
     * @param rmaSwhNmParam String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRMASwh(NWAL1890CMsg bizMsg, String rmaSwhNmParam) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("swhNm", rmaSwhNmParam);

        return getSsmEZDClient().queryObjectList("getRMASwh", params);
    }

    /**
     * get RMA Line Source Reference Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRMALineSrcRef(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("lineSrcRefNum", bizMsg.xxOrdSrcRefNumSrchTxt_R.getValue());

        return getSsmEZDClient().queryObjectList("getRMALineSrcRef", params);
    }

    /**
     * get RMA Serial Number
     * @param bizMsg NWAL1890CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRMASerNum(NWAL1890CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("serNum", bizMsg.xxSerNumSrchTxt_R.getValue());

        return getSsmEZDClient().queryObjectList("getRMASerNum", params);
    }
}