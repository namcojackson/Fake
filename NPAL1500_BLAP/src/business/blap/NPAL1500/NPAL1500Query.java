/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1500;

import static business.blap.NPAL1500.constant.NPAL1500Constant.APP_FUNC_ID_FOR_VA;
import static business.blap.NPAL1500.constant.NPAL1500Constant.BLANK;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DEF_THRU_DT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SQL_FROM_MIN_DT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SQL_LIKE_PERCENT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.SQL_TO_MAX_DT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_NPAL1500_EXCLUDE_WH;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_PO_SC_WH_OWNR_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_PO_VAR_COA_PROJ_ACCT_TP_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DR_CR_TP_CREDIT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DR_CR_TP_DEBIT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.VAR_CHAR_NFBL1130_COA_ACCT_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.DEFAULT_COA_ACCT_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.COMMA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDSStringItem;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 * 09/15/2016   CITS            S.Endo          Update          N/A(Refactoring)
 * 09/27/2016   CITS            S.Endo          Update          QC#11985
 * 10/18/2016   CITS            R.Shimamoto     Update          QC#6159
 * 10/19/2016   CITS            S.Endo          Update          QC#10456
 * 2017/02/21   CITS            Y.IWASAKI       Update          QC#17487
 * 2017/06/07   CITS            S.Endo          Update          QC#17952
 * 2017/08/03   CITS            R.Shimamoto     Update          QC#18671
 * 2017/10/13   CITS            S.Katsuma       Update          QC#21845
 * 2017/11/08   Hitachi         Y.Takeno        Update          QC#21849(Sol#218)
 * 2017/12/04   CITS            K.Kameoka       Update          QC#14858(Sol#060)
 * 01/09/2018   CITS            K.Kameoka       Update          QC#18602(Sol#102)
 * 04/20/2018   CITS            Y.Iwasaki       Update          QC#25019
 * 06/19/2018   CITS            K.Kameoka       Update          QC#21358
 * 06/19/2018   CITS            T.Hakodate      Update          QC#24932
 * 06/26/2018   CITS            K.Kameoka       Update          QC#25314
 * 07/03/2018   CITS            T.Tokutomi      Update          QC#23726
 * 07/10/2018   CITS            K.Kameoka       Update          QC#25024
 * 07/12/2018   CITS            S.Katsuma       Update          QC#26867
 * 07/26/2018   CITS            K.Ogino         Update          QC#27127
 * 07/30/2018   CITS            K.Kameoka       Update          QC#27024
 * 08/07/2018   CITS            K.Ogino         Update          QC#27024
 * 10/01/2018   CITS            K.Kameoka       Update          QC#28460
 * 12/13/2018   CITS            M.Naito         Update          QC#29027
 * 12/02/2019   Fujitsu         T.Ogura         Update          QC#54813
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 04/03/2020   Fujitsu         T.Ogura         Update          QC#56386
 * 2022/05/16   Hitachi         A.Kohinata      Update          QC#57934
 * 2022/09/12   Hitachi         T.NEMA          Update          QC#60413
 * 2022/12/09   Hitachi         M.Kikushima     Update          QC#60604
 * 2023/06/02   Hitachi         E.Watabe        Update          QC#61493
 * 2024/03/04   CITS            S.Okamoto       Update          QC#62443
 * </pre>
 */
public final class NPAL1500Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1500Query MY_INSTANCE = new NPAL1500Query();

    /**
     * Constructor.
     */
    private NPAL1500Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1500Query
     */
    public static NPAL1500Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * get DS Order Type List
     * </pre>
     * @param cMsg NPAL1500CMsg
     * @param dplyFlg boolean
     * @return S21SsmEZDResult DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpList(NPAL1500CMsg cMsg, boolean dplyFlg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());

        if (dplyFlg) {
            params.put("dsPoTpDplyFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryObjectList("getDsOrdTpList", params);
    }

    /**
     * <pre>
     * get RetailWarehouse Name
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return RTL_WHTMsg (if not exists, null)
     */
    public S21SsmEZDResult getRtlWhNm(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("salesDate", ZYPDateUtil.getSalesDate());
        params.put("prtyLocFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getRtlWhNm", params);
    }

    /**
     * <pre>
     * get Vendor info
     * </pre>
     * @param cMsg NPAL1500CMsg
     * @return S21SsmEZDResult Vendor info
     */
    public S21SsmEZDResult getVendorInfo(NPAL1500CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("vndCd", cMsg.vndCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getVendorInfo", params);
    }

    /**
     * <pre>
     * get Preferred Carrier info
     * </pre>
     * @param cMsg NPAL1500CMsg
     * @return S21SsmEZDResult Vendor info
     */
    public S21SsmEZDResult getPreferredCarrierInfo(NPAL1500CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("rtlWhCd", cMsg.destRtlWhCd_DS.getValue());

        return getSsmEZDClient().queryObjectList("getPreferredCarrierInfo", params);
    }

    /** for cancel,close **/
    /**
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return BigDecimal
     */
    public BigDecimal countValidRws(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GLBL_CMPY_CD", glblCmpyCd);
        params.put("PO_ORD_NUM", poOrdNum);
        params.put("PO_ORD_DTL_LINE_NUM", poOrdDtlLineNum);
        params.put("RWS_OPEN_FLG", ZYPConstant.FLG_ON_Y);

        return (BigDecimal) getSsmEZDClient().queryObject("countValidRws", params).getResultObject();
    }

    /**
     * <pre>
     * countValidRwsSet
     * </pre>
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return BigDecimal
     */
    public BigDecimal countValidRwsSet(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GLBL_CMPY_CD", glblCmpyCd);
        params.put("PO_ORD_NUM", poOrdNum);
        params.put("PO_ORD_DTL_LINE_NUM", poOrdDtlLineNum);
        params.put("RWS_OPEN_FLG", ZYPConstant.FLG_ON_Y);

        return (BigDecimal) getSsmEZDClient().queryObject("countValidRwsSet", params).getResultObject();
    }

    // START 2022/09/12 T.NEMA [QC#60413, ADD]
    /**
     * <pre>
     * countValidAPInvoiceForCancel
     * </pre>
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return BigDecimal
     */
    public BigDecimal countValidAPInvoiceSetItemForCancel(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GLBL_CMPY_CD", glblCmpyCd);
        params.put("PO_ORD_NUM", poOrdNum);
        params.put("PO_ORD_DTL_LINE_NUM", poOrdDtlLineNum);
        params.put("ACCT_INV_STS_CD", ACCT_INV_STS.ENTERED);

        return (BigDecimal) getSsmEZDClient().queryObject("countValidAPInvoiceSetItemForCancel", params).getResultObject();
    }

    /**
     * <pre>
     * countValidAPInvoiceForCancel
     * </pre>
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return BigDecimal
     */
    public BigDecimal countValidAPInvoiceForCancel(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GLBL_CMPY_CD", glblCmpyCd);
        params.put("PO_ORD_NUM", poOrdNum);
        params.put("PO_ORD_DTL_LINE_NUM", poOrdDtlLineNum);
        params.put("ACCT_INV_STS_CD", ACCT_INV_STS.ENTERED);

        return (BigDecimal) getSsmEZDClient().queryObject("countValidAPInvoiceForCancel", params).getResultObject();
    }
    // END   2022/09/12 T.NEMA [QC#60413, ADD]

    /** for init **/
    /**
     * getBusinessUnitList
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBusinessUnitList(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getBusinessUnitList", params);
    }

    /**
     * getFreightTermList
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFreightTermList(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getFreightTermList", params);
    }

    /**
     * getShippingMethodList
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShippingMethodList(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getShippingMethodList", params);
    }

    /**
     * getStockStatusList
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStockStatusList(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getStockStatusList", params);
    }

    /**
     * getTransmissionMethodTypeList
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTransmissionMethodTypeList(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getTransmissionMethodTypeList", params);
    }

    /**
     * getPoLineTypeList
     * @param bizMsg NPAL1500CMsg
     * @param scrEntAval boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoLineTypeList(NPAL1500CMsg bizMsg, boolean scrEntAval) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        if (scrEntAval) {
            params.put("scrEntAvalFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryObjectList("getPoLineTypeList", params);
    }


    /** for save,submit **/
    /**
     * getInventoryReq
     * @param sMsg NPAL1500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInventoryReq(NPAL1500SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        String[] poMdseCmpsnTpCdAry = {PO_MDSE_CMPSN_TP.PARENT , PO_MDSE_CMPSN_TP.REGULAR };
        params.put("PO_MDSE_CMPSN_TP_CD", poMdseCmpsnTpCdAry);
        params.put("poOrdNum", sMsg.poNum.getValue());

        return getSsmEZDClient().queryObject("getInventoryReq", params);
    }

    /**
     * getConfigComponebts
     * @param sMsg NPAL1500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfigComponents(NPAL1500SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        params.put("svcConfigMstrPk", sMsg.svcConfigMstrPk.getValue());

        return getSsmEZDClient().queryObjectList("getConfigComponents", params);
    }

    /**
     * Get Service Machine Master
     * @param sMsg NPAL1500SMsg
     * @param serNum String
     * @param index int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstrForSer(NPAL1500SMsg sMsg, String serNum, int index) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        params.put("serNum", serNum);
        params.put("mdseCd", sMsg.A.no(index).mdseCd_A1.getValue());
        String[] svcMachMstrStsCdList = {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED};
        params.put("SVC_MACH_MSTR_STS_CD_LIST" , svcMachMstrStsCdList);
        params.put("srcRtlWhCd", sMsg.srcRtlWhCd_SC.getValue());

        return getSsmEZDClient().queryObjectList("getSvcMachMstrForSer", params);
    }
    // QC#25314 Mod Start
//    /**
//     * countValidRwsForSubmmit
//     * @param glblCmpyCd String
//     * @param poOrdNum String
//     * @return String
//     */
//    public BigDecimal countValidRwsForSubmmit(String glblCmpyCd, String poOrdNum) {
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", glblCmpyCd);
//        params.put("poOrdNum", poOrdNum);
//
//        String[] rwsStsCdList = {RWS_STS.SAVED, RWS_STS.PRINTED, RWS_STS.RECEIVING, RWS_STS.RECEIVED };
//        params.put("rwsStsCdList", rwsStsCdList);
//        return (BigDecimal) getSsmEZDClient().queryObject("countValidRwsForSubmmit", params).getResultObject();
//    }
    /**
     * countValidRwsForSubmmit
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public BigDecimal countValidRwsForSubmmit(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poOrdNum", poOrdNum);
        params.put("poOrdDtlLineNum", poOrdDtlLineNum);
        params.put("mdseCd", mdseCd);

        String[] rwsStsCdList = {RWS_STS.SAVED, RWS_STS.PRINTED, RWS_STS.RECEIVING, RWS_STS.RECEIVED };
        params.put("rwsStsCdList", rwsStsCdList);
        // START 04/03/2020 T.Ogura [QC#56386,ADD]
        params.put("rwsStsPrinted", RWS_STS.PRINTED);
        params.put("rwsStsReceiving", RWS_STS.RECEIVING);
        params.put("rwsStsReceived", RWS_STS.RECEIVED);
        // END   04/03/2020 T.Ogura [QC#56386,ADD]
        return (BigDecimal) getSsmEZDClient().queryObject("countValidRwsForSubmmit", params).getResultObject();
    }
    // QC#25314 Mod End

    /** for search **/
    /**
     * getPoData
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoData(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
        params.put("PO_ORD_NUM", bizMsg.poNum.getValue());

        return getSsmEZDClient().queryObjectList("getPoData", params);
    }

    /**
     * getAdditionalPoDtlData
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAdditionalPoDtlData(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
        params.put("PO_ORD_NUM", bizMsg.poNum.getValue());

        return getSsmEZDClient().queryObjectList("getDsPoDtlData", params);
    }

    /**
     * getPoDtlData
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoDtlData(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
        params.put("PO_ORD_NUM", bizMsg.poNum.getValue());

        return getSsmEZDClient().queryObjectList("getPoDtlData", params);
    }

    /**
     * getPoMsgTxt
     * @param bizMsg NPAL1500CMsg
     * @param poMsgTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoMsgTxt(NPAL1500CMsg bizMsg, String poMsgTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
        params.put("PO_ORD_NUM", bizMsg.poNum.getValue());
        params.put("PO_MSG_TP_CD", poMsgTpCd);

        return getSsmEZDClient().queryObjectList("getPoMsgTxt", params);
    }

    /**
     * getDetailListInfo
     * @param bizMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailListInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", bizMsg);
        ssmParam.put("poVarCoaProjAcctTpCd", ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_PO_VAR_COA_PROJ_ACCT_TP_CD, bizMsg.glblCmpyCd.getValue()));
        ssmParam.put("appFuncId", APP_FUNC_ID_FOR_VA);
        // QC#13985 2016/09/12 Add Start
        ssmParam.put("poAcctTpCdCharge", PO_ACCT_TP.CHARGE);
        ssmParam.put("poAcctTpCdAccrual", PO_ACCT_TP.ACCRUAL);
        ssmParam.put("poAcctTpCdVariance", PO_ACCT_TP.VARIANCE);
        // QC#13985 2016/09/12 Add End
        // QC#55313
        ssmParam.put("prchReqLineStsCdCancel", PRCH_REQ_LINE_STS.CANCELLED);
        // add start 2022/05/16 QC#57934
        ssmParam.put("drCrTpCr", DR_CR_TP_CREDIT);
        ssmParam.put("drCrTpDr", DR_CR_TP_DEBIT);
        ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        String coaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_NFBL1130_COA_ACCT_CD, bizMsg.glblCmpyCd.getValue());
        List<String> coaAcctCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
            coaAcctCdList = Arrays.asList(coaAcctCd.split(COMMA));
        } else {
            coaAcctCdList.add(DEFAULT_COA_ACCT_CD);
        }
        ssmParam.put("coaAcctCdList", coaAcctCdList);
        // add end 2022/05/16 QC#57934

        return getSsmEZDClient().queryEZDMsgArray("getDetailListInfo", ssmParam, sMsg.A);

    }

    /**
     * get Ship To Address Info
     * @param bizMsg NPAL1500CMsg
     * @return Ship To Address Info
     */
    public S21SsmEZDResult getShipToAddress(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", bizMsg.destRtlWhCd_DS.getValue());

        return getSsmEZDClient().queryObject("getShipToAddress", params);
    }

    /**
     * count Ship To Address
     * @param bizMsg NPAL1500CMsg
     * @return count of Ship To Address
     */
    public S21SsmEZDResult countShipToCustAddr(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.varCharConstVal_GC.getValue()); // Search by CUSA Global Company Code
        params.put("shipToCustCd", bizMsg.shipToCustCd.getValue());

        return getSsmEZDClient().queryObject("countShipToCustAddr", params);
    }

    /**
     * get Return Ship To Retail Warehouse Code
     * @param bizMsg NPAL1500CMsg
     * @return Return Ship To Retail Warehouse Code
     */
    public S21SsmEZDResult getRtrnShipToRtlWHCd(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToStCd", bizMsg.stCd_RW.getValue());
        params.put("shipToPostCd", bizMsg.postCd_RW.getValue());

        return getSsmEZDClient().queryObject("getRtrnShipToRtlWHCd", params);
    }

    /**
     * get Default Return Ship To Retail Warehouse Code
     * @param bizMsg NPAL1500CMsg
     * @return Default Return Ship To Retail Warehouse Code
     */
    public S21SsmEZDResult getDefRtrnShipToRtlWHCd(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("asta", "*");

        return getSsmEZDClient().queryObject("getDefRtrnShipToRtlWHCd", params);
    }

    /**
     * get Address Info by Retail Warehouse Code
     * @param bizMsg NPAL1500CMsg
     * @param rtlWHCd String
     * @return Address Info
     */
    public S21SsmEZDResult getAddressInfoByRtlWh(NPAL1500CMsg bizMsg, String rtlWHCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("rtlWHCd", rtlWHCd);

        return getSsmEZDClient().queryObject("getAddressInfoByRtlWh", params);
    }

    /**
     * get Derive Item Info
     * @param bizMsg NPAL1500CMsg
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDeriveItemInfo(NPAL1500CMsg bizMsg, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("mdseCd", mdseCd);

        String[] rgtnStsList = {RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING , RGTN_STS.READY_FOR_ORDER_TAKING };
        params.put("RGTN_STS", rgtnStsList);

        return getSsmEZDClient().queryObject("getDeriveItemInfo", params);
    }

    /**
     * get Component
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return Component Info S21SsmEZDResult
     */
    public S21SsmEZDResult getComponent(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("mdseCmpsnTpCd_setMdse", MDSE_CMPSN_TP.SET_MDSE);
        params.put("mdseCmpsnTpCd_ordTakeMdse", MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        params.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        params.put("effThruMaxDt", "99991231");

        String[] rgtnStsList = {RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING , RGTN_STS.READY_FOR_ORDER_TAKING };
        params.put("RGTN_STS", rgtnStsList);
        //params.put("readyForClrnRecv", RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);
        //params.put("readyForOrdTak", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("poAcctTpCd", PO_ACCT_TP.ACCRUAL);

        return getSsmEZDClient().queryObjectList("getComponent", params);
    }

    /**
     * get Charge Account
     * @param glblCmpyCd String
     * @param vndCd String
     * @return Charge Account Info S21SsmEZDResult
     */
    public S21SsmEZDResult getChargeAcct(String glblCmpyCd, String vndCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("vndCd", vndCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getChargeAcct", params);
    }

    /**
     * get Default Account
     * @param glblCmpyCd String
     * @param poLineTpCd String
     * @return Default Account Info S21SsmEZDResult
     */
    public S21SsmEZDResult getDefAcct(String glblCmpyCd, String poLineTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poLineTpCd", poLineTpCd);

        return getSsmEZDClient().queryObjectList("getDefAcct", params);
    }

    /**
     * Default account info query
     * @param glblCmpyCd String
     * @param appFuncId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctInfoFromHeader(String glblCmpyCd, String rtlWhCd, String lineBizTpCd, String shipToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
//        params.put("lineBizTpCd", lineBizTpCd);
        params.put("shipToCustCd", shipToCustCd);
        return getSsmEZDClient().queryObject("getAcctInfoFromHeader", params);
    }

    /**
     * <pre>
     * get Vendor Transmit Info
     * </pre>
     * @param bizMsg NPAL1500CMsg
     * @return Vendor Transmit Info
     */
    public S21SsmEZDResult getVendorTrsmtInfo(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("vndCd", bizMsg.vndCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("getVendorTrsmtInfo", params);
    }

    /**
     * <pre>
     * get EIP Report Info
     * </pre>
     * @param bizMsg NPAL1500CMsg
     * @return Report Info
     */
    public S21SsmEZDResult getEipRptInfo(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("poRptId", "PO_RPT_ID");
        params.put("rptBrNum", "00");
        params.put("destTpCd", DEST_TP.PRINTER);

        return getSsmEZDClient().queryObjectList("getEipRptInfo", params);
    }

    /**
     * <pre>
     * count Career Shipping Service Level and Shipping Service Level 's Relation
     * </pre>
     * @param glblCmpyCd String
     * @param shpgSvcLvlCd String
     * @param carrSvcLvlCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkCarrShpgSvcLvlAndShpgSvcLvl(String glblCmpyCd, String shpgSvcLvlCd, String carrSvcLvlCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        queryParam.put("carrSvcLvlCd", carrSvcLvlCd);
        return getSsmEZDClient().queryObject("checkCarrShpgSvcLvlAndShpgSvcLvl", queryParam);
    }


    /**
     * <pre>
     * get Vendor Order Qty from ASL
     * </pre>
     * @param glblMsg NPAL1500SMsg
     * @param tagDate String
     * @param asMsg NPAL1500_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVndOrdQty(NPAL1500SMsg glblMsg , String tagDate, NPAL1500_ASMsg asMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblMsg.glblCmpyCd.getValue());
        params.put("vndCd", glblMsg.vndCd.getValue());
        params.put("prntVndCd", glblMsg.prntVndCd.getValue());
        params.put("tagDate", tagDate);
        params.put("mdseCd", asMsg.mdseCd_A1.getValue());
        params.put("effFromDt", SQL_FROM_MIN_DT);
        params.put("effThruDt", SQL_TO_MAX_DT);
        params.put("aslStartDt", SQL_FROM_MIN_DT);
        params.put("aslEndDt", SQL_TO_MAX_DT);
        return getSsmEZDClient().queryObjectList("getVndOrdQty", params);
    }

    /**
     * <pre>
     * get Ship To Customer Info 's List from PO_SHIP_TO_CUST_V
     * </pre>
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoShipToCustList(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("shipToCustCode", shipToCustCd + SQL_LIKE_PERCENT);
        return getSsmEZDClient().queryObjectList("getPoShipToCustList", params);
    }

    /**
     * <pre>
     * get Max Value of PO_DTL.PO_ORD_DTL_LINE_NUM
     * </pre>
     * @param poOrdNum String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxPoDtlLineNum(String glblCmpyCd, String poOrdNum) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poOrdNum", poOrdNum);
        return getSsmEZDClient().queryObjectList("getMaxPoDtlLineNum", params);
    }

    /**
     * <pre>
     * get Ser Number of PO_DTL.PO_ORD_DTL_LINE_NUM
     * </pre>
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoSerNum(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poOrdNum", poOrdNum);
        params.put("poOrdDtlLineNum", poOrdDtlLineNum);
        return getSsmEZDClient().queryObjectList("getPoSerNum", params);
    }

    /**
     * <pre>
     * get PKG_UOM_CLS_DESC_TXT
     * </pre>
     * @param glblCmpyCd String
     * @param pkgUomCd String
     * @return S21SsmEZDResult PKG_UOM_CLS_DESC_TXT
     */
    public S21SsmEZDResult getPkgUomClsDescTxt(String glblCmpyCd, String pkgUomCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("pkgUomCd", pkgUomCd);

        return getSsmEZDClient().queryObject("getPkgUomClsDescTxt", params);
    }

    /**
     * get Default Source/Destination SWH Code
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlSwhCd(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("prtyLocFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getRtlSwhCd", params);
    }

    /**
     * check Technician warehouse code
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkTechnicianWh(String glblCmpyCd, String rtlWhCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("locTpCd", LOC_TP.TECHNICIAN);

        return getSsmEZDClient().queryObjectList("chkTechnicianWh", params);
    }

    /**
     * get EnableAssetFlg
     * @param glblCmpyCd String
     * @param dsPoTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEnableAssetFlg(String glblCmpyCd, String dsPoTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsPoTpCd", dsPoTpCd);

        return getSsmEZDClient().queryObject("getEnableAssetFlg", params);
    }

    /**
     * Mod QC#27127
     * getRegularStkInvtyLocCd
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param rtlSwhCd String
     * @param getInvtyLocCdCache
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRegularStkInvtyLocCd(String glblCmpyCd, String rtlWhCd, String rtlSwhCd, S21LRUMap<Object, S21SsmEZDResult> getInvtyLocCdCache) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("rtlSwhCd", rtlSwhCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // QC#16080 Mod.
//        params.put("locRoleTpCd", LOC_ROLE_TP.REGULAR_STOCK_WAREHOUSE);

        // Get NPAL1500 Exclude WH
        String excludeWh = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_NPAL1500_EXCLUDE_WH, glblCmpyCd);
        if (excludeWh != null && !excludeWh.equals(BLANK)) {
            ArrayList<String> excludeWhList = new ArrayList<String>();
            String[] arrayExcludeWh = excludeWh.split(",", 0);
            for (int i = 0; i < arrayExcludeWh.length; i++) {
                excludeWhList.add(arrayExcludeWh[i]);
            }
            params.put("locRoleTpCd", excludeWhList);

        }

        S21SsmEZDResult ssmResult = getInvtyLocCdCache.get(params);
        if (ssmResult == null) {
            ssmResult = getSsmEZDClient().queryObject("getRegularStkInvtyLocCd", params);
            if (!ssmResult.isCodeNormal()) {
                // QC#27024
                return ssmResult;
            }
            getInvtyLocCdCache.put(params, ssmResult);
        }

        return ssmResult;
    }

    /**
     * getMdseSerNumRng
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdseSerNumRng(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getMdseSerNumRng", params);
    }

    /**
     * getPrtVndNm
     * @param glblCmpyCd String
     * @param prtVndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrtVndNm(String glblCmpyCd, String prtVndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntVndCd", prtVndCd);
        params.put("salesDate", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getPrtVndNm", params);
    }

    /**
     * getRegularStkInvtyLocCd
     * @param glblCmpyCd String
     * @param vndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVndNm(String glblCmpyCd, String vndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("vndCd", vndCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("salesDate", ZYPDateUtil.getSalesDate());
        return getSsmEZDClient().queryObject("getVndNm", params);
    }

    /**
     * getStNm
     * @param glblCmpyCd String
     * @param stCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStNm(String glblCmpyCd, String stCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("stCd", stCd);

        return getSsmEZDClient().queryObject("getStNm", params);
    }

    /**
     * getPoSerNumPk
     * @param glblCmpyCd String
     * @param serNum String
     * @param asMsg NPAL1500_ASMsg
     * @param poOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoSerNumPk(//
            String glblCmpyCd, String serNum, NPAL1500_ASMsg asMsg, String poOrdNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("serNum", serNum);
        params.put("mdseCd", asMsg.mdseCd_A1.getValue());
        params.put("poOrdNum", poOrdNum);
        params.put("poOrdDtlLineNum", asMsg.poOrdDtlLineNum_A1.getValue());
        String[] poLineStsList = {PO_LINE_STS.CLOSED , PO_LINE_STS.CANCELLED};
        params.put("PO_LINE_STS_CD", poLineStsList);

        return getSsmEZDClient().queryObjectList("getPoSerNumPk", params);
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
     * getSourceWH
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSourceWH(String glblCmpyCd, String rtlWhCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        // START 2023/06/02 E.Watabe [QC#61493, MOD]
        //params.put("invtyOwnrCd", INVTY_OWNR.CFS);
        List<String> invtyOwnrCdList = new ArrayList<String>();
        invtyOwnrCdList.add(INVTY_OWNR.CFS);
        invtyOwnrCdList.add(INVTY_OWNR.GMD);
        params.put("invtyOwnrCdList", invtyOwnrCdList);
        // END   2023/06/02 E.Watabe [QC#61493, MOD]
        return getSsmEZDClient().queryObjectList("getSourceWH", params);
    }
    /**
     * Bill to location query
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToLocation(String glblCmpyCd, String billToCustCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("billToCustCd", billToCustCd);
        return getSsmEZDClient().queryObjectList("getBillToLocation", params);
    }
    /**
     * getSellToCustCd
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSellToCustCd(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("shipToCustCd", shipToCustCd);

        return  getSsmEZDClient().queryObject("getSellToCustCd", params);
    }

    /**
     * get SvcMachMstr
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param svcConfigMstrPk BigDecimal
     * @param serNum EZDSStringItem
     * @return SvcMachMstr Info
     */
    public S21SsmEZDResult getSvcMachMstr(String glblCmpyCd, String mdseCd, BigDecimal svcConfigMstrPk, EZDSStringItem serNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("svcConfigMstrPk", svcConfigMstrPk);
        if (ZYPCommonFunc.hasValue(serNum)) {
            params.put("serNum", serNum.getValue());
        }

        return getSsmEZDClient().queryObjectList("getSvcMachMstr", params);
    }

    /**
     * countRtlWhInWhOwnr
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param whOwnrCdArray String[]
     * @return int
     */
    public int countRtlWhInWhOwnr(String glblCmpyCd, String rtlWhCd, String[] whOwnrCdArray) {
        HashMap<String, Object> bindParams = new HashMap<String, Object>();
        bindParams.put("glblCmpyCd", glblCmpyCd);
        bindParams.put("rtlWhCd", rtlWhCd);
        bindParams.put("whOwnrCdList", whOwnrCdArray);

        S21SsmEZDResult ezResult = getSsmEZDClient().queryObject("countRtlWhInWhOwnr", bindParams);
        return (Integer) ezResult.getResultObject();
    }

    /**
     * getSetChildMasterQty
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSetChildMasterQty(String glblCmpyCd, String mdseCd){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        return getSsmEZDClient().queryObjectList("getSetChildMasterQty", params);
    }
    
    /**QC#18671 Add.
     * getMdseFromSupplierItem
     * @param sMsg
     * @param glblCmpyCd
     * @param sMsgALine
     * @return
     */
    public S21SsmEZDResult getMdseFromSupplierItem(NPAL1500SMsg sMsg, String glblCmpyCd, NPAL1500_ASMsg sMsgALine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("vndCd", sMsg.vndCd.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        params.put("mdseCd", sMsgALine.mdseCd_A1.getValue());

        return getSsmEZDClient().queryObjectList("getMdseFromSupplierItem", params);
    }

    // START 2017/11/07 [QC#21849, ADD]
    /**
     * checkFMCustomer
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return String
     */
    public S21SsmEZDResult checkFMCustomer(String glblCmpyCd, String shipToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("shipToCustCd", shipToCustCd);
        params.put("dsAcctClsCd", DS_ACCT_CLS.FM_ACCTS);
        return getSsmEZDClient().queryObject("checkFMCustomer", params);
    }

    /**
     * get Ship To Address Info
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return Ship To Address Info
     */
    // START 2019/12/02 T.Ogura [QC#54813,MOD]
//    public S21SsmEZDResult getShipToAddressFromShipToCustomer(NPAL1500CMsg bizMsg) {
    public S21SsmEZDResult getShipToAddressFromShipToCustomer(String glblCmpyCd, String shipToCustCd) {
    // END   2019/12/02 T.Ogura [QC#54813,MOD]

        Map<String, Object> params = new HashMap<String, Object>();
        // START 2019/12/02 T.Ogura [QC#54813,MOD]
//        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        params.put("shipToCustCd", bizMsg.shipToCustCd.getValue());
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("shipToCustCd", shipToCustCd);
        // END   2019/12/02 T.Ogura [QC#54813,MOD]

        return getSsmEZDClient().queryObject("getShipToAddressFromShipToCustomer", params);
    }
    // END   2017/11/07 [QC#21849, ADD]
    
    /**
     * get TEXT ITEM for Check.
     * @param glblCmpyCd String
     * @return Text Item S21SsmEZDResult
     */
    public S21SsmEZDResult getAvailableTextItem(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("mdseItemTpCd", MDSE_ITEM_TP.TEXT_ITEM);

        return getSsmEZDClient().queryObject("getAvailableTextItem", params);
    }
    
    /**
     * get TEXT ITEM
     * @param glblCmpyCd String
     * @return Text Item S21SsmEZDResult
     */
    public S21SsmEZDResult getTextItem(NPAL1500SMsg sMsg ,String[] textItemList) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        params.put("textItemList", textItemList);
        params.put("mdseItemTpCd", MDSE_ITEM_TP.TEXT_ITEM);        

        return getSsmEZDClient().queryObjectList("getTextItem", params);
    }
    // END 2017/12/04 [QC#14858, ADD]
    
    //QC#18602(Sol#102) ADD Start
    
    /**
     * <pre>
     * get Re-Open Flag
     * </pre>
     * @param bizMsg
     * @return Count Re Open Flag 
     */
    public BigDecimal getReOpenFlgDPT(NPAL1500CMsg bizMsg)  {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsPoTpCd", bizMsg.dsPoTpCd.getValue());
        params.put("RE_OPEN_FLG",  ZYPConstant.FLG_ON_Y);

        return (BigDecimal) getSsmEZDClient().queryObject("getReOpenFlgDPT", params).getResultObject();
    }
    /**
     * <pre>
     * get Re-Open Flag
     * </pre>
     * @param bizMsg
     * @return Count Re Open Flag 
     */
    public BigDecimal getReOpenFlgPOS(NPAL1500CMsg bizMsg)  {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("poOrdSrcCd", bizMsg.poOrdSrcCd.getValue());
        params.put("RE_OPEN_FLG",  ZYPConstant.FLG_ON_Y);

        return (BigDecimal) getSsmEZDClient().queryObject("getReOpenFlgPOS", params).getResultObject();
    }

    /** for search **/
    /**
     * getPoData
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAPInvoiceData(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
        params.put("PO_NUM", bizMsg.poNum.getValue());
        params.put("AP_LINE_TP_CD", AP_LINE_TP.ITEM);
        params.put("ACCT_INV_STS_CD", ACCT_INV_STS.CANCEL);
        params.put("textItemTpCd", MDSE_ITEM_TP.TEXT_ITEM);

        return getSsmEZDClient().queryObjectList("getAPInvoiceData", params);
    }
    //QC#18602(Sol#102) ADD End
    
    //QC#21358 ADD Start
    /**
     * <p>
     * Searches Contact Info.
     * </p>
     * @param cMsg bizMsg
     * @param sMsg glblMsg
     * @return Supplier Site
     */
    public S21SsmEZDResult getContactInfo(NPAL1500CMsg bizMsg, NPAL1500SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("defThruDt", DEF_THRU_DT);
        params.put("dsCtacPntTpCd_T", DS_CTAC_PNT_TP.PHONE_WORK);
        params.put("dsCtacPntTpCd_F", DS_CTAC_PNT_TP.FAX_WORK);
        params.put("dsCtacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        params.put("glblCmpyCd", bizMsg.glblCmpyCd);
        params.put("vndCd", bizMsg.vndCd);
        params.put("ctacPsnActvFlg", ZYPConstant.FLG_ON_Y);
        // START 2018/12/13 M.Naito [QC#29027,ADD]
        params.put("ctacTpCdDI", CTAC_TP.DELIVERY_INSTALL);
        // END 2018/12/13 M.Naito [QC#29027,ADD]

        return getSsmEZDClient().queryObjectList("getContactInfo", params);
    }
    //QC#21358 ADD End


    // QC# 24932 ADD START
    /**
     * searchSupplier
     */
    public S21SsmEZDResult searchSupplier(NPAL1500SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        params.put("vndCd", sMsg.vndCd.getValue());

        return getSsmEZDClient().queryObject("searchSupplier", params);
    }
    // QC# 24932 ADD END


    /**
     * getCPOInfo
     * QC#23726 Add method.
     * @param glblCmpyCd String
     * @param poNum String
     * @return cpo info map S21SsmEZDResult
     */
    public S21SsmEZDResult getCPOInfo(String glblCmpyCd, String poNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poNum", poNum);

        return getSsmEZDClient().queryObject("getCPOInfo", params);
    }

    /**
     * getCarrSvcLvlCd
     * QC#23726 Add method.
     * @param glblCmpyCd String
     * @param poNum String
     * @return Carrier Service Level Code S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrSvcLvlCd(String glblCmpyCd, String carrCd, String shpgSvcLvlCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("carrCd", carrCd);
        params.put("shpgSvcLvlCd", shpgSvcLvlCd);

        return getSsmEZDClient().queryObject("getCarrSvcLvlCd", params);
    }

    /**
     * getDsAcctNum
     * QC#23726 Add method.
     * @param glblCmpyCd String
     * @param poNum String
     * @return dsAcctNum S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNum(String glblCmpyCd, String poNum) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poNum", poNum);

        return getSsmEZDClient().queryObject("getDsAcctNum", params);
    }
    
    //QC#25024 Add Start
    /**
     * getCPOInfo
     * QC#23726 Add method.
     * @param glblCmpyCd String
     * @param poNum String
     * @return Invd_Vis info map S21SsmEZDResult
     */
    public S21SsmEZDResult getInbdVis(NPAL1500CMsg bizMsg, NPAL1500SMsg sMsg, int i) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        params.put("poNum", sMsg.poNum.getValue() + SQL_LIKE_PERCENT);
        params.put("poOrdDtlLineNum", sMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
        params.put("inbdLtstRecFlg", ZYPConstant.FLG_ON_Y);
        params.put("calcFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getInbdVis", params);
    }
    //QC#25024 Add End

    // START 2018/07/12 S.Katsuma [QC#26867,ADD]
    public S21SsmEZDResult checkConfig(String glblCmpyCd, String mdseCd, String serNum) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("serNum", serNum);

        return getSsmEZDClient().queryObject("checkConfig", params);
    }
    // END 2018/07/12 S.Katsuma [QC#26867,ADD]
    
    
    // QC#27024 Add Start
    public S21SsmEZDResult checkReceivedSerNum(String glblCmpyCd, String mdseCd, String serNum) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        params.put("serNum", serNum);

        return getSsmEZDClient().queryObject("checkReceivedSerNum", params);
    }
    // QC#27024 Add End

    // Add QC#27024
    public S21SsmEZDResult chkRwsPutAwaySer(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, String serNum) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("poOrdNum", poOrdNum);
        params.put("poOrdDtlLineNum", poOrdDtlLineNum);
        params.put("serNum", serNum);

        return getSsmEZDClient().queryObject("chkRwsPutAwaySer", params);
    }
    
    //QC#28460 Add Start
    /**
     * <pre>
     * get Vendor info
     * </pre>
     * @param cMsg NPAL1500CMsg
     * @return S21SsmEZDResult Vendor info
     */
    public S21SsmEZDResult checkWhOwnrCdForSc(NPAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("whOwnrCd",  ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_PO_SC_WH_OWNR_CD, bizMsg.glblCmpyCd.getValue()));
        params.put("rtlWhCd", bizMsg.destRtlWhCd_DS);
        params.put("salesDate", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("checkWhOwnrCdForSc", params);
    }
    //QC#28460 Add End

    // START 2018/12/13 M.Naito [QC#29027,ADD]
    /**
     * checkPrntCmpySetMdse
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkPrntCmpySetMdse(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        List<String> mdseCmpsnTpList = new ArrayList<String>();
        mdseCmpsnTpList.add(MDSE_CMPSN_TP.SET_MDSE);
        mdseCmpsnTpList.add(MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        params.put("mdseCmpsnTpCd_setMdse", mdseCmpsnTpList);
        params.put("salesDate", ZYPDateUtil.getSalesDate());
        params.put("effThruMaxDt", "99991231");

        return getSsmEZDClient().queryObject("checkPrntCmpySetMdse", params);
    }
    // END 2018/12/13 M.Naito [QC#29027,ADD]

    // QC#30436 ADD START
    /**
     * @param glblCmpyCd
     * @param poOrdNum
     * @return
     */
    public S21SsmEZDResult searchPrchReq(NPAL1500SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd",  sMsg.glblCmpyCd.getValue());
        params.put("poOrdNum", sMsg.poNum_HD.getValue());
        
        return getSsmEZDClient().queryObject("searchPrchReq", params);
    }
    // QC#30436 ADD END

    // add start 2022/05/16 QC#57934
    /**
     * getSsmParamDetailListInfo
     * @param bizMsg NPAL1500CMsg
     * @return S21SsmEZDResult
     */
    public static Map<String, Object> getSsmParamDetailListInfo(NPAL1500CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", bizMsg);
        ssmParam.put("poVarCoaProjAcctTpCd", ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_PO_VAR_COA_PROJ_ACCT_TP_CD, bizMsg.glblCmpyCd.getValue()));
        ssmParam.put("appFuncId", APP_FUNC_ID_FOR_VA);
        ssmParam.put("poAcctTpCdCharge", PO_ACCT_TP.CHARGE);
        ssmParam.put("poAcctTpCdAccrual", PO_ACCT_TP.ACCRUAL);
        ssmParam.put("poAcctTpCdVariance", PO_ACCT_TP.VARIANCE);
        ssmParam.put("prchReqLineStsCdCancel", PRCH_REQ_LINE_STS.CANCELLED);
        ssmParam.put("credit", DR_CR_TP_CREDIT);
        ssmParam.put("debit", DR_CR_TP_DEBIT);
        ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        String coaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_NFBL1130_COA_ACCT_CD, bizMsg.glblCmpyCd.getValue());
        List<String> coaAcctCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
            coaAcctCdList = Arrays.asList(coaAcctCd.split(COMMA));
        } else {
            coaAcctCdList.add(DEFAULT_COA_ACCT_CD);
        }
        ssmParam.put("coaAcctCdList", coaAcctCdList);
        ssmParam.put("coaAcctCdList", coaAcctCdList);

        return ssmParam;
    }
    // add end 2022/05/16 QC#57934

    // START 2022/12/09 M.Kikushima[QC#60604, ADD]
    /**
     * @param prmMap Map<String, Object>
     * @return count of CSA Warehouse
     */
    public S21SsmEZDResult getCsaWhCount(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getCsaWhCount", prmMap);
    }
    // END 2022/12/09 M.Kikushima[QC#60604, ADD]

    //QC#62443 2024/3/4 Add Start
    /**
     * getCoaCcCd
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoaCcCd(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getCoaCcCd", params);
    }
    //QC#62443 2024/3/4 Add End

}
