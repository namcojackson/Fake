/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC201001;

import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.AUTO_SQ_EXTN_KEY_LOC_NUM;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.AUTO_SQ_EXTN_KEY_PRNT_VND_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.AUTO_SQ_EXTN_KEY_VND_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.CREATE_MODE;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.CUSA_EXCL_TAX_PAY_ID;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.DEF_THRU_DT;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.LOC_NM_LENGTH_60;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.MAX_CNT_OF_VND_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.MAX_LOC_NM_LENGTH;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZC2010_PROC_MODE_CRAT;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZC2010_PROC_MODE_UPD;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0009E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0035E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0036E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0037E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0038E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0048E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0049E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0050E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0051E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0054E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0055E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0095E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0096E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0097E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0098E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0099E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0100E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0101E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0102E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0103E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0104E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0105E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0106E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0107E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0108E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0109E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0110E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0111E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0113E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0114E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0115E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0116E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0117E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0118E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0119E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0120E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0121E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0122E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0123E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0124E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0125E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0127E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0128E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0129E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0130E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0132E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0333E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.SSM_PRM_EXCLUDE_TAXPAYER_ID;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.SSM_PRM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.SSM_PRM_LOC_NM;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.SSM_PRM_PRNT_VND_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.SSM_PRM_PRNT_VND_NM;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.SSM_PRM_SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.SSM_PRM_TAX_PAYER_ID;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.SSM_PRM_VND_CD;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.TP_CD_ALL;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.UPDATE_MODE;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.SSM_PRNT_VND_TP_CD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CMPYTMsg;
import business.db.CNTYTMsg;
import business.db.CNTYTMsgArray;
import business.db.DEF_VND_INFOTMsg;
import business.db.LOC_USGTMsg;
import business.db.PRNT_VNDTMsg;
import business.db.PRNT_VND_TPTMsg;
import business.db.PTYTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.VNDTMsg;
import business.db.VND_TP_RELNTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC201001PMsg;
import business.parts.NMZC201002PMsg;
import business.parts.NMZC201003PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRNT_VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Update Supplier API.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   CITS            T.Tokutomi      Create          N/A
 * 2016/05/11   CITS            T.Hakodate      UPDATE          QC#6943
 * 2016/08/22   CITS            T.Gotoda        UPDATE          QC#12215
 * 2016/09/15   CITS            T.Gotoda        UPDATE          QC#13313
 * 2016/10/27   CITS            T.Hakodate      UPDATE          QC#15314
 * 2016/11/28   CITS            R.Shimamoto     UPDATE          QC#16147
 * 2017/01/17   CITS            R.Shimamoto     UPDATE          QC#14489
 * 2017/02/09   CITS            R.Shimamoto     UPDATE          QC#16594
 * 2017/08/04   CITS            T.Kikuhara      UPDATE          QC#8598(L3)
 * 2018/01/17   CITS            T.Wada          UPDATE          QC#22164
 * 2018/02/15   CITS            T.Gotoda        Update          QC#22054
 * 2018/02/15   CITS            T.Tokutomi      Update          QC#27045
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMZC201001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE batchType = null;

    /** global data : prntVndPk */
    private BigDecimal glPrntVndPk = null;

    /** LocNum */
    private String LocNum = null;

    // QC#13313 Start
    /** Previous Partner Vendor Type Code */
    private String prevPrntVndTpCd = null;
    // QC#13313 End

    // QC#14489
    private ArrayList<String> excludeTaxPayerIdList = null;

    /**
     * Constructor
     */
    public NMZC201001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute.
     * 
     * <pre>
     * Create or Update Supplier
     * </pre>
     * @param pMsg01 NMZC201001PMsg
     * @param pMsg02List List<NMZC201002PMsg>
     * @param pMsg03List List<NMZC201003PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NMZC201001PMsg pMsg01, final List<NMZC201002PMsg> pMsg02List, final List<NMZC201003PMsg> pMsg03List, final ONBATCH_TYPE onBatchType) {

        // set message map
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg01);

        // set batch type
        batchType = onBatchType;

        // get Exclude TaxPayerId.
        if (ZYPCommonFunc.hasValue(pMsg01.glblCmpyCd)) {
            getExcludeTaxPayerId(pMsg01.glblCmpyCd.getValue());
        }

        // check common Mandatory
        if (!checkCommonMandatoryItem(pMsg01, msgMap)) {
            // Error
            msgMap.flush();
            return;
        }

        /** Create /Update Supplier **/
        // check supplier date
        if (!checkSupplierMandatoryItem(pMsg01, msgMap)) {
            // Error
            msgMap.flush();
            return;
        }

        if (CREATE_MODE.equals(pMsg01.xxModeCd.getValue())) {
            if (!createSupplier(pMsg01, msgMap)) {
                // Error update msgMap.
                msgMap.flush();
                return;
            }
        } else if (UPDATE_MODE.equals(pMsg01.xxModeCd.getValue())) {
            if (!updateSupplier(pMsg01, msgMap)) {
                // Error update msgMap.
                msgMap.flush();
                return;
            }
        } else {
            // process mode invalid value.
            msgMap.addXxMsgId(NMZM0055E);
        }

        /** Create /Update Supplier site **/
        for (NMZC201002PMsg pMsg02 : pMsg02List) {

            // check modeCd
            if (!checkModeForSupplierSite(pMsg02, msgMap)) {
                // Error update msgMap.
                msgMap.flush();
                return;
            }

            // check Supplier site Mandatory
            if (!checkSupplierSiteMandatoryItem(pMsg02, msgMap)) {
                // Error update msgMap.
                msgMap.flush();
                return;
            }

            // check supplier site item length
            if (!checkLengthForSupplierSiteItem(pMsg02, msgMap)) {
                // Error update msgMap.
                msgMap.flush();
                return;
            }

            // Create / Update
            if (CREATE_MODE.equals(pMsg02.xxModeCd.getValue())) {
                if (!createSupplierSite(pMsg01, pMsg02, glPrntVndPk, msgMap)) {
                    // Error update msgMap.
                    msgMap.flush();
                    return;
                }
            } else if (UPDATE_MODE.equals(pMsg02.xxModeCd.getValue())) {
                if (!updateSupplierSite(pMsg01, pMsg02, glPrntVndPk, msgMap)) {
                    // Error update msgMap.
                    msgMap.flush();
                    return;
                }
            } else {
                // process mode invalid value.
                msgMap.addXxMsgId(NMZM0100E);
            }
        }

        /** Create /Update Contact info **/
        // check parameter
        for (NMZC201003PMsg pMsg03 : pMsg03List) {
            if (!checkContactInfoMandatoryItem(pMsg03, msgMap)) {
                // Error update msgMap.
                msgMap.flush();
                return;
            }
        }

        // Call Contact Info Update API
        if (pMsg03List.size() > 0) {
            if (!executeContactInfoAPI(pMsg01.glblCmpyCd.getValue(), pMsg01.procDt.getValue(), pMsg03List, msgMap)) {
                // Error update msgMap.
                msgMap.flush();
                return;
            }
        }

        // end
        msgMap.flush();
    }

    /**
     * Create Supplier.
     * @param pMsg01 NMZC201001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    @SuppressWarnings("unchecked")
    private boolean createSupplier(NMZC201001PMsg pMsg01, S21ApiMessageMap msgMap) {

        String glblCmpyCd = pMsg01.glblCmpyCd.getValue();

        if (!ZYPCommonFunc.hasValue(pMsg01.prntVndCd)) {
            // get Supplier Number
            ZYPEZDItemValueSetter.setValue(pMsg01.prntVndCd, derivingSupplierNumber(glblCmpyCd));
        }

        // QC#22164
        // master check
        if (getPrntVndPk(pMsg01) != null) {
            // Error
            msgMap.addXxMsgId(NMZM0114E);
            return false;
        }

        // get default vendor info
        DEF_VND_INFOTMsg defVndInfo = getDefaultVenderInfo(pMsg01);

        // check taxPayer
        if (ZYPCommonFunc.hasValue(pMsg01.taxPayerId)) {

            // QC#14489
            List<Map> supplierList = getTaxPayerInfoForCreate(pMsg01);

            boolean chkFlg = false;

            if (supplierList.isEmpty()) {
                chkFlg = true;
            }

            for (Map m : supplierList) {
                String supplierNm = (String) m.get("PRNT_VND_NM");
                String supplierTypeCode = (String) m.get("PRNT_VND_TP_CD");

                if (supplierNm.startsWith("CANON USA")) {
                    chkFlg = true;
                } else if (PRNT_VND_TP.ARREFUND.equals(supplierTypeCode) //
                        || PRNT_VND_TP.LEASE_BUYOUT.equals(supplierTypeCode)) {
                    chkFlg = true;
                }
            }

            if (!chkFlg) {
                // Error
                msgMap.addXxMsgId(NMZM0114E);
                return false;
            }
        }

        // check date format
        if (ZYPCommonFunc.hasValue(pMsg01.inacDt)) {
            if (!ZYPDateUtil.isValidDate(pMsg01.inacDt.getValue(), "yyyyMMdd")) {
                // Error
                msgMap.addXxMsgId(NMZM0116E);
                return false;
            }
        }

        // create prnt vnd
        PRNT_VNDTMsg prntVnd = setPrntVndData(pMsg01, defVndInfo, new PRNT_VNDTMsg());

        // set pk
        glPrntVndPk = ZYPOracleSeqAccessor.getNumberBigDecimal("PRNT_VND_SQ");
        ZYPEZDItemValueSetter.setValue(prntVnd.prntVndPk, glPrntVndPk);

        S21ApiTBLAccessor.create(prntVnd);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prntVnd.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0117E);
            return false;
        }

        return true;
    }

    /**
     * Update Supplier.
     * @param pMsg01 NMZC201001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    @SuppressWarnings("unchecked")
    private boolean updateSupplier(NMZC201001PMsg pMsg01, S21ApiMessageMap msgMap) {

        // master check
        glPrntVndPk = getPrntVndPk(pMsg01);
        if (glPrntVndPk == null) {
            // Error
            msgMap.addXxMsgId(NMZM0115E);
            return false;
        }

        // get default vendor info
        DEF_VND_INFOTMsg defVndInfo = getDefaultVenderInfo(pMsg01);

        // check taxPayer
        if (ZYPCommonFunc.hasValue(pMsg01.taxPayerId)) {

            List<Map> supplierList = getTaxPayerInfo(pMsg01);

            boolean chkFlg = false;

            if (supplierList.isEmpty()) {
                // Error
                msgMap.addXxMsgId(NMZM0114E);
                return false;
            }

            if (supplierList.size() > 1) {

                for (Map m : supplierList) {
                    String supplierNm = (String) m.get("PRNT_VND_NM");
                    String supplierTypeCode = (String) m.get("PRNT_VND_TP_CD");

                    if (supplierNm.startsWith("CANON USA")) {
                        chkFlg = true;
                    } else if (PRNT_VND_TP.ARREFUND.equals(supplierTypeCode) //
                            || PRNT_VND_TP.LEASE_BUYOUT.equals(supplierTypeCode)) {
                        chkFlg = true;
                    }
                }

                if (!chkFlg) {
                    // Error
                    msgMap.addXxMsgId(NMZM0114E);
                    return false;
                }
            }
        }

        // check date format
        if (ZYPCommonFunc.hasValue(pMsg01.inacDt)) {
            if (!ZYPDateUtil.isValidDate(pMsg01.inacDt.getValue(), "yyyyMMdd")) {
                // Error
                msgMap.addXxMsgId(NMZM0116E);
                return false;
            }
        }

        // update prnt vnd
        PRNT_VNDTMsg prntVnd = new PRNT_VNDTMsg();
        ZYPEZDItemValueSetter.setValue(prntVnd.glblCmpyCd, pMsg01.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.prntVndPk, glPrntVndPk);

        // Primary Key Search
        prntVnd = (PRNT_VNDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(prntVnd);

        // QC#13313 Start
        if (prntVnd != null) {
            prevPrntVndTpCd = prntVnd.prntVndTpCd.getValue();
        }
        // QC#13313 End

        PRNT_VNDTMsg prntVndUpdate = setPrntVndData(pMsg01, defVndInfo, prntVnd);

        // Update
        S21ApiTBLAccessor.update(prntVndUpdate);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prntVnd.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0117E);
            return false;
        }

        return true;
    }

    /**
     * Create Supplier Site.
     * @param pMsg01 NMZC201001PMsg
     * @param pMsg02 NMZC201002PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    @SuppressWarnings("unchecked")
    private boolean createSupplierSite(NMZC201001PMsg pMsg01, NMZC201002PMsg pMsg02, BigDecimal prntVndPk, S21ApiMessageMap msgMap) {

        String glblCmpyCd = pMsg01.glblCmpyCd.getValue();
        String procDt = pMsg01.procDt.getValue();
        String prntVndNm = pMsg01.prntVndNm.getValue();

        // get Supplier site Number
        // QC#27045 Update. Specify SupplierSite.
        String supplierSiteNumber = null;
        if(ZYPCommonFunc.hasValue(pMsg02.vndCd)){
            supplierSiteNumber = pMsg02.vndCd.getValue();
        } else {
            supplierSiteNumber = derivingSupplierSiteNumber(glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg02.vndCd, supplierSiteNumber);
        }


        // check ship to customer
        BigDecimal shipToCustPkChk = getShipToCustPk(glblCmpyCd, pMsg02.vndCd.getValue());
        if (shipToCustPkChk != null) {
            boolean chkFlg = false;
            for (int i = 0; i < MAX_CNT_OF_VND_CD; i++) {
                String deriveVndCd = derivingSupplierSiteNumber(glblCmpyCd);
                shipToCustPkChk = getShipToCustPk(glblCmpyCd, deriveVndCd);
                if (shipToCustPkChk == null) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndCd, deriveVndCd);
                    chkFlg = true;
                    break;
                }
            }

            if (!chkFlg) {
                // Error
                msgMap.addXxMsgId(NMZM0118E);
                return false;
            }
        }

        // check supplier site
        Map supplierSiteKeyMap = getSupplierSitePrimaryKey(glblCmpyCd, pMsg02.vndCd.getValue());

        if (supplierSiteKeyMap != null) {
            // Error
            msgMap.addXxMsgId(NMZM0120E);
            return false;
        }

        // check supplier site name
        BigDecimal vndPkChk = getSupplierSiteName(glblCmpyCd, pMsg01.prntVndCd.getValue(), pMsg02.locNm.getValue());

        if (ZYPCommonFunc.hasValue(vndPkChk)) {
            // Error
            msgMap.addXxMsgId(NMZM0122E);
            return false;
        }

        // check date format
        if (ZYPCommonFunc.hasValue(pMsg02.effThruDt)) {
            if (!ZYPDateUtil.isValidDate(pMsg02.effThruDt.getValue(), "yyyyMMdd")) {
                // Error
                msgMap.addXxMsgId(NMZM0116E);
                return false;
            }
        }

        /** create CMPY **/
        CMPYTMsg cmpy = setCmpyData(glblCmpyCd, pMsg02, new CMPYTMsg());

        // Set Primary Key
        BigDecimal cmpyPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CMPY_SQ);
        ZYPEZDItemValueSetter.setValue(cmpy.cmpyPk, cmpyPk);

        S21ApiTBLAccessor.create(cmpy);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmpy.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0124E);
            return false;
        }

        /** create PTY_LOC_WRK **/
        PTY_LOC_WRKTMsg ptyLocWrk = setPtyLocWrkData(glblCmpyCd //
                , procDt, prntVndNm, cmpyPk, pMsg02, new PTY_LOC_WRKTMsg());

        // Set Primary Key
        BigDecimal ptyLocWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PTY_LOC_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.ptyLocPk, ptyLocWrkPk);

        S21ApiTBLAccessor.create(ptyLocWrk);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ptyLocWrk.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0048E);
            return false;
        }

        /** create LOC_USG **/
        LOC_USGTMsg locUsg = setLocUsgData(glblCmpyCd, ptyLocWrkPk, pMsg02, new LOC_USGTMsg());

        // Set Primary Key
        BigDecimal locUsgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.LOC_USG_SQ);
        ZYPEZDItemValueSetter.setValue(locUsg.locUsgPk, locUsgPk);

        S21ApiTBLAccessor.create(locUsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(locUsg.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0051E);
            return false;
        }

        /** create PTY **/
        PTYTMsg pty = setPtyData(glblCmpyCd, ptyLocWrkPk, pMsg02, new PTYTMsg());

        S21ApiTBLAccessor.create(pty);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(pty.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0049E);
            return false;
        }

        /** create VND **/
        VNDTMsg vnd = setVndData(glblCmpyCd, glPrntVndPk, ptyLocWrk, pMsg01, pMsg02, new VNDTMsg());

        // Set Primary Key
        BigDecimal vndPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.VND_SQ);
        ZYPEZDItemValueSetter.setValue(vnd.vndPk, vndPk);

        S21ApiTBLAccessor.create(vnd);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vnd.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0125E);
            return false;
        }

        /** create SHIP_TO_CUST **/
        SHIP_TO_CUSTTMsg shipToCust = setShipToCustData(glblCmpyCd, ptyLocWrk, pMsg02, new SHIP_TO_CUSTTMsg());

        // Set Primary Key
        BigDecimal shipToCustPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SHIP_TO_CUST_SQ);
        ZYPEZDItemValueSetter.setValue(shipToCust.shipToCustPk, shipToCustPk);

        S21ApiTBLAccessor.create(shipToCust);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipToCust.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0050E);
            return false;
        }

        /** create VND_TP_RELN **/
        String vndTpCd = getVndTpCd(glblCmpyCd, pMsg01.prntVndTpCd.getValue());
        VND_TP_RELNTMsg vndTpReln = setVndTpRelnData(glblCmpyCd //
                , pMsg02.vndCd.getValue(), vndTpCd, new VND_TP_RELNTMsg());
        S21ApiTBLAccessor.create(vndTpReln);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndTpReln.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0127E);
            return false;
        }

        // QC#13313 Start
        /** create VND_TP_RELN for Supplier and Dealer **/
        if (ZYPConstant.FLG_ON_Y.equals( pMsg02.splyPoFlg.getValue()) && !VND_TP.SUPPLIER.equals(vndTpCd)) {
            vndTpReln = setVndTpRelnData(glblCmpyCd, pMsg02.vndCd.getValue(), VND_TP.SUPPLIER, new VND_TP_RELNTMsg());
            S21ApiTBLAccessor.create(vndTpReln);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndTpReln.getReturnCode())) {
                // Error
                msgMap.addXxMsgId(NMZM0127E);
                return false;
            }
        }
        // QC#13313 End

        return true;
    }

    /**
     * Update Supplier Site.
     * @param pMsg01 NMZC201001PMsg
     * @param pMsg02 NMZC201002PMsg
     * @param prntVndPk BigDecimal
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    @SuppressWarnings("unchecked")
    private boolean updateSupplierSite(NMZC201001PMsg pMsg01, NMZC201002PMsg pMsg02, BigDecimal prntVndPk, S21ApiMessageMap msgMap) {

        String glblCmpyCd = pMsg01.glblCmpyCd.getValue();
        String procDt = pMsg01.procDt.getValue();
        String prntVndNm = pMsg01.prntVndNm.getValue();

        // check ship to customer
        BigDecimal shipToCustPk = getShipToCustPk(glblCmpyCd, pMsg02.vndCd.getValue());
        if (shipToCustPk == null) {
            // Error
            msgMap.addXxMsgId(NMZM0119E);
            return false;
        }

        // check supplier site
        Map supplierSiteKeyMap = getSupplierSitePrimaryKey(glblCmpyCd, pMsg02.vndCd.getValue());

        if (supplierSiteKeyMap == null) {
            // Error
            msgMap.addXxMsgId(NMZM0121E);
            return false;
        }

        // check supplier site name
        BigDecimal vndPkChk = getSupplierSiteName(glblCmpyCd, pMsg01.prntVndCd.getValue(), pMsg02.locNm.getValue());

        if (!ZYPCommonFunc.hasValue(vndPkChk)) {
            // Error
            msgMap.addXxMsgId(NMZM0123E);
            return false;
        }

        // check date format
        if (ZYPCommonFunc.hasValue(pMsg02.effThruDt)) {
            if (!ZYPDateUtil.isValidDate(pMsg02.effThruDt.getValue(), "yyyyMMdd")) {
                // Error
                msgMap.addXxMsgId(NMZM0116E);
                return false;
            }
        }

        /** update CMPY **/
        CMPYTMsg cmpy = new CMPYTMsg();

        // set primary key
        BigDecimal cmpyPk = (BigDecimal) supplierSiteKeyMap.get("CMPY_PK");
        ZYPEZDItemValueSetter.setValue(cmpy.cmpyPk, cmpyPk);
        ZYPEZDItemValueSetter.setValue(cmpy.glblCmpyCd, glblCmpyCd);

        // get CMPY data
        cmpy = (CMPYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cmpy);

        if (cmpy == null) {
            // Error
            msgMap.addXxMsgId(NMZM0128E);
            return false;
        }

        // set update data
        cmpy = setCmpyData(glblCmpyCd, pMsg02, cmpy);

        // update
        S21ApiTBLAccessor.update(cmpy);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmpy.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0124E);
            return false;
        }

        /** update PTY_LOC_WRK **/
        PTY_LOC_WRKTMsg ptyLocWrk = new PTY_LOC_WRKTMsg();

        // set primary key
        BigDecimal ptyLocWrkPk = (BigDecimal) supplierSiteKeyMap.get("PTY_LOC_PK");
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.ptyLocPk, ptyLocWrkPk);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.glblCmpyCd, glblCmpyCd);

        // get PTY_LOC_WRK data
        ptyLocWrk = (PTY_LOC_WRKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(ptyLocWrk);

        if (ptyLocWrk == null) {
            // Error
            msgMap.addXxMsgId(NMZM0129E);
            return false;
        }

        // set update data
        ptyLocWrk = setPtyLocWrkData(glblCmpyCd, procDt, prntVndNm, cmpyPk, pMsg02, ptyLocWrk);

        // update
        S21ApiTBLAccessor.update(ptyLocWrk);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ptyLocWrk.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0048E);
            return false;
        }

        /** update VND **/
        VNDTMsg vnd = new VNDTMsg();

        // set primary key
        BigDecimal vndPk = (BigDecimal) supplierSiteKeyMap.get("VND_PK");
        ZYPEZDItemValueSetter.setValue(vnd.vndPk, vndPk);
        ZYPEZDItemValueSetter.setValue(vnd.glblCmpyCd, glblCmpyCd);

        // get VND data
        vnd = (VNDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(vnd);

        if (vnd == null) {
            // Error
            msgMap.addXxMsgId(NMZM0130E);
            return false;
        }

        // set update data
        vnd = setVndData(glblCmpyCd, glPrntVndPk, ptyLocWrk, pMsg01, pMsg02, vnd);

        // update
        S21ApiTBLAccessor.update(vnd);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vnd.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0125E);
            return false;
        }

        /** update SHIP_TO_CUST **/
        SHIP_TO_CUSTTMsg shipToCust = setShipToCustData(glblCmpyCd, ptyLocWrk, pMsg02, new SHIP_TO_CUSTTMsg());

        // Set Primary Key
        ZYPEZDItemValueSetter.setValue(shipToCust.shipToCustPk, shipToCustPk);
        ZYPEZDItemValueSetter.setValue(shipToCust.glblCmpyCd, glblCmpyCd);

        // get SHIP_TO_CUST data
        shipToCust = (SHIP_TO_CUSTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(shipToCust);

        if (shipToCust == null) {
            // Error
            msgMap.addXxMsgId(NMZM0132E);
            return false;
        }

        // set update data
        shipToCust = setShipToCustData(glblCmpyCd, ptyLocWrk, pMsg02, shipToCust);

        // update
        S21ApiTBLAccessor.update(shipToCust);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipToCust.getReturnCode())) {
            // Error
            msgMap.addXxMsgId(NMZM0050E);
            return false;
        }

        // QC#13313 Start
        /** VND_TP_RELN for Supplier and Dealer **/
        String vndTpCd = getVndTpCd(glblCmpyCd, pMsg01.prntVndTpCd.getValue());
        String prevVndTpCd = getVndTpCd(glblCmpyCd, prevPrntVndTpCd);

        // When vndTpCd changed.
        if (!vndTpCd.equals(prevVndTpCd)) {

            // Remove previous VND_TP_RELN
            VND_TP_RELNTMsg vndTpReln = setVndTpRelnData(glblCmpyCd, pMsg02.vndCd.getValue(), prevVndTpCd, new VND_TP_RELNTMsg());

            vndTpReln = (VND_TP_RELNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(vndTpReln);

            if (vndTpReln != null) {
                // Remove
                S21ApiTBLAccessor.logicalRemove(vndTpReln);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndTpReln.getReturnCode())) {
                    // Error
                    msgMap.addXxMsgId(NMZM0333E);
                    return false;
                }
            }

            // Create new VND_TP_RELN
            vndTpReln = setVndTpRelnData(glblCmpyCd, pMsg02.vndCd.getValue(), vndTpCd, new VND_TP_RELNTMsg());
            VND_TP_RELNTMsg rsltTMsg = (VND_TP_RELNTMsg) S21ApiTBLAccessor.findByKey(vndTpReln);

            if (rsltTMsg == null) {
                // Create
                S21ApiTBLAccessor.create(vndTpReln);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndTpReln.getReturnCode())) {
                    // Error
                    msgMap.addXxMsgId(NMZM0127E);
                    return false;
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg02.splyPoFlg.getValue()) && !VND_TP.SUPPLIER.equals(vndTpCd)) {
            VND_TP_RELNTMsg vndTpReln = setVndTpRelnData(glblCmpyCd, pMsg02.vndCd.getValue(), VND_TP.SUPPLIER, new VND_TP_RELNTMsg());

            // get VND_TP_RELN data
            VND_TP_RELNTMsg rsltTMsg = (VND_TP_RELNTMsg) S21ApiTBLAccessor.findByKey(vndTpReln);

            if (rsltTMsg == null) {
                // Create
                S21ApiTBLAccessor.create(vndTpReln);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndTpReln.getReturnCode())) {
                    // Error
                    msgMap.addXxMsgId(NMZM0127E);
                    return false;
                }
            }
        } else if (ZYPConstant.FLG_OFF_N.equals(pMsg02.splyPoFlg.getValue()) && !VND_TP.SUPPLIER.equals(vndTpCd)) {
            VND_TP_RELNTMsg vndTpReln = setVndTpRelnData(glblCmpyCd, pMsg02.vndCd.getValue(), VND_TP.SUPPLIER, new VND_TP_RELNTMsg());

            // get VND_TP_RELN data
            vndTpReln = (VND_TP_RELNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(vndTpReln);

            if (vndTpReln != null) {
                // Remove
                S21ApiTBLAccessor.logicalRemove(vndTpReln);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndTpReln.getReturnCode())) {
                    // Error
                    msgMap.addXxMsgId(NMZM0333E);
                    return false;
                }
            }
        }
        // QC#13313 End

        return true;
    }

    /**
     * Check Common Mandatory Item.
     * @param pMsg01
     * @param msgMap
     * @return true:OK false:NG
     */
    private boolean checkCommonMandatoryItem(NMZC201001PMsg pMsg01, S21ApiMessageMap msgMap) {

        boolean errFlg = true;

        // check xxModeCd
        if (!ZYPCommonFunc.hasValue(pMsg01.xxModeCd)) {
            // Error
            msgMap.addXxMsgId(NMZM0054E);
            errFlg = false;
        }
        // check glblCmpyCd
        if (!ZYPCommonFunc.hasValue(pMsg01.glblCmpyCd)) {
            // Error
            msgMap.addXxMsgId(NMZM0009E);
            errFlg = false;
        }
        // check procDt
        if (!ZYPCommonFunc.hasValue(pMsg01.procDt)) {
            // Error
            msgMap.addXxMsgId(NMZM0095E);
            errFlg = false;
        }

        return errFlg;
    }

    /**
     * Check Supplier Mandatory Item.
     * @param pMsg01 NMZC201001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:OK false:NG
     */
    private boolean checkSupplierMandatoryItem(NMZC201001PMsg pMsg01, S21ApiMessageMap msgMap) {

        boolean errFlg = true;

        // Mode: create: no process.

        // Mode: update
        if (UPDATE_MODE.equals(pMsg01.xxModeCd.getValue())) {
            // PRNT_VND_CD
            if (!ZYPCommonFunc.hasValue(pMsg01.prntVndCd)) {
                // Error
                msgMap.addXxMsgId(NMZM0096E);
                errFlg = false;
            }
        }

        // Common
        // PRNT_VND_NM
        if (!ZYPCommonFunc.hasValue(pMsg01.prntVndNm)) {
            // Error
            msgMap.addXxMsgId(NMZM0097E);
            errFlg = false;
        }
        // PRNT_VND_TP_CD
        if (!ZYPCommonFunc.hasValue(pMsg01.prntVndTpCd)) {
            // Error
            msgMap.addXxMsgId(NMZM0098E);
            errFlg = false;
        }

        return errFlg;
    }

    /**
     * Check Mode For Supplier Site.
     * @param pMsg02 NMZC201002PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:OK false:NG
     */
    private boolean checkModeForSupplierSite(NMZC201002PMsg pMsg02, S21ApiMessageMap msgMap) {
        boolean errFlg = true;

        // check xxModeCd
        if (!ZYPCommonFunc.hasValue(pMsg02.xxModeCd)) {
            // Error
            msgMap.addXxMsgId(NMZM0099E);
            errFlg = false;
        }

        return errFlg;
    }

    /**
     * Check Supplier Mandatory Item.
     * @param pMsg01 NMZC201001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:OK false:NG
     */
    private boolean checkSupplierSiteMandatoryItem(NMZC201002PMsg pMsg02, S21ApiMessageMap msgMap) {

        boolean errFlg = true;

        // Mode: create: no process.

        // Mode: update
        if (UPDATE_MODE.equals(pMsg02.xxModeCd.getValue())) {
            // PRNT_VND_CD
            if (!ZYPCommonFunc.hasValue(pMsg02.prntVndCd)) {
                // Error
                msgMap.addXxMsgId(NMZM0096E);
                errFlg = false;
            }
            // VND_CD
            if (!ZYPCommonFunc.hasValue(pMsg02.vndCd)) {
                // Error
                msgMap.addXxMsgId(NMZM0101E);
                errFlg = false;
            }
        }

        // Common
        // FIRST_LINE_ADDR
        if (!ZYPCommonFunc.hasValue(pMsg02.firstLineAddr)) {
            // Error
            msgMap.addXxMsgId(NMZM0035E);
            errFlg = false;
        }
        // CTY_ADDR
        if (!ZYPCommonFunc.hasValue(pMsg02.ctyAddr)) {
            // Error
            msgMap.addXxMsgId(NMZM0036E);
            errFlg = false;
        }

        //QC#12215 Start
        if (CTRY.UNITED_STATES_OF_AMERICA.equals(pMsg02.ctryCd.getValue())) {

            // ST_CD
            if (!ZYPCommonFunc.hasValue(pMsg02.stCd)) {
                // Error
                msgMap.addXxMsgId(NMZM0037E);
                errFlg = false;
            }

            // POST_CD
            if (!ZYPCommonFunc.hasValue(pMsg02.postCd)) {
                // Error
                msgMap.addXxMsgId(NMZM0038E);
                errFlg = false;
            }
        }
        //QC#12215 End

        // LOC_NM
        if (!ZYPCommonFunc.hasValue(pMsg02.locNm)) {
            // Error
            msgMap.addXxMsgId(NMZM0102E);
            errFlg = false;
        }

        return errFlg;
    }

    /**
     * Check Length For Supplier Site Item.
     * @param pMsg01 NMZC201001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:OK false:NG
     */
    private boolean checkLengthForSupplierSiteItem(NMZC201002PMsg pMsg02, S21ApiMessageMap msgMap) {
        boolean errFlg = true;

        // Check Loc_Nm
        int locNmLength = pMsg02.locNm.getValue().length();
        if (locNmLength > MAX_LOC_NM_LENGTH) {
            // Error
            msgMap.addXxMsgId(NMZM0113E);
            errFlg = false;
        }

        return errFlg;
    }

    /**
     * Check Supplier Mandatory Item.
     * @param pMsg01 NMZC201001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:OK false:NG
     */
    private boolean checkContactInfoMandatoryItem(NMZC201003PMsg pMsg03, S21ApiMessageMap msgMap) {

        boolean errFlg = true;

        // Common
        // EFF_FROM_DT
        if (!ZYPCommonFunc.hasValue(pMsg03.effFromDt)) {
            // Error
            msgMap.addXxMsgId(NMZM0107E);
            errFlg = false;
        }
        // CTAC_PSN_FIRST_NM
        if (!ZYPCommonFunc.hasValue(pMsg03.ctacPsnFirstNm)) {
            // Error
            msgMap.addXxMsgId(NMZM0108E);
            errFlg = false;
        }
        // CTAC_PSN_LAST_NM
        if (!ZYPCommonFunc.hasValue(pMsg03.ctacPsnLastNm)) {
            // Error
            msgMap.addXxMsgId(NMZM0109E);
            errFlg = false;
        }
        // CTAC_TP_CD
        if (!ZYPCommonFunc.hasValue(pMsg03.ctacTpCd)) {
            // Error
            msgMap.addXxMsgId(NMZM0110E);
            errFlg = false;
        }

        // contact point info
        for (int i = 0; i < pMsg03.ContactPointInfoList.getValidCount(); i++) {
            // XX_MODE_CD
            if (!ZYPCommonFunc.hasValue(pMsg03.ContactPointInfoList.no(i).xxModeCd)) {
                // Error
                msgMap.addXxMsgId(NMZM0103E);
                errFlg = false;
            }
            // DS_CTAC_PNT_TP_CD
            if (!ZYPCommonFunc.hasValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd)) {
                // Error
                msgMap.addXxMsgId(NMZM0111E);
                errFlg = false;
            }
            // 02/08/2017 QC#16594 Del Start.
//            // DS_CTAC_PNT_VAL_TXT
//            if (!ZYPCommonFunc.hasValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt)) {
//                // Error
//                msgMap.addXxMsgId(NMZM0112E);
//                errFlg = false;
//            }
            // 02/08/2017 QC#16594 Del End.
        }

        if (!ZYPCommonFunc.hasValue(pMsg03.xxModeCd)) {
            // Error
            msgMap.addXxMsgId(NMZM0103E);
            errFlg = false;
        } else {
            // Mode: create
            if (NMZC2010_PROC_MODE_CRAT.equals(pMsg03.xxModeCd.getValue())) {
                // OK
            } else if (NMZC2010_PROC_MODE_UPD.equals(pMsg03.xxModeCd.getValue())) {
                // Mode: update
                // CTAC_PSN_PK
                if (!ZYPCommonFunc.hasValue(pMsg03.ctacPsnPk)) {
                    // Error
                    msgMap.addXxMsgId(NMZM0105E);
                    errFlg = false;
                }
                // LOC_NUM
                if (!ZYPCommonFunc.hasValue(pMsg03.locNum)) {
                    // Error
                    msgMap.addXxMsgId(NMZM0106E);
                    errFlg = false;
                }
            } else {
                // process mode invalid value.
                msgMap.addXxMsgId(NMZM0104E);
            }
        }

        return errFlg;
    }

    // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 START
    /**
     * Deriving Supplier Number.
     * @param glblCmpyCd
     * @return exp:"VS0000001" String
     */
    private String derivingLocationNumber(String glblCmpyCd) {
        return ZYPExtnNumbering.getUniqueID(glblCmpyCd, AUTO_SQ_EXTN_KEY_LOC_NUM);
    }

    // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 END

    /**
     * Deriving Supplier Number.
     * @param glblCmpyCd
     * @return exp:"PV0000001" String
     */
    private String derivingSupplierNumber(String glblCmpyCd) {
        return ZYPExtnNumbering.getUniqueID(glblCmpyCd, AUTO_SQ_EXTN_KEY_PRNT_VND_CD);
    }

    /**
     * Deriving Supplier Number.
     * @param glblCmpyCd
     * @return exp:"VS0000001" String
     */
    private String derivingSupplierSiteNumber(String glblCmpyCd) {
        return ZYPExtnNumbering.getUniqueID(glblCmpyCd, AUTO_SQ_EXTN_KEY_VND_CD);
    }

    /**
     * Get PrntVndCd
     * @param pMsg01 NMZC201001PMsg
     * @return VND_CD or Null
     */
    private BigDecimal getPrntVndPk(NMZC201001PMsg pMsg01) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(SSM_PRM_GLBL_CMPY_CD, pMsg01.glblCmpyCd.getValue());
        param.put(SSM_PRM_PRNT_VND_CD, pMsg01.prntVndCd.getValue());
        param.put(SSM_PRM_PRNT_VND_NM, pMsg01.prntVndNm.getValue());
        // QC#22164
        param.put(SSM_PRNT_VND_TP_CD, pMsg01.prntVndTpCd.getValue());

        return (BigDecimal) this.ssmBatchClient.queryObject("getPrntVndPk", param);
    }

    /**
     * Get Default VenderInfo Primary key search.
     * @param pMsg01
     * @return DEF_VND_INFO List or Null
     */
    private DEF_VND_INFOTMsg getDefaultVenderInfo(NMZC201001PMsg pMsg01) {

        DEF_VND_INFOTMsg cond = new DEF_VND_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, pMsg01.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.prntVndTpCd, pMsg01.prntVndTpCd);

        return (DEF_VND_INFOTMsg) S21ApiTBLAccessor.findByKey(cond);
    }

    /** QC#14489
     * Get TaxPayerInfo for create.
     * @param pMsg01 NMZC201001PMsg
     * @return VND_CD or Null
     */
    @SuppressWarnings("unchecked")
    private List<Map> getTaxPayerInfoForCreate(NMZC201001PMsg pMsg01) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(SSM_PRM_GLBL_CMPY_CD, pMsg01.glblCmpyCd.getValue());
        param.put(SSM_PRM_TAX_PAYER_ID, pMsg01.taxPayerId.getValue());
        param.put(SSM_PRM_EXCLUDE_TAXPAYER_ID, excludeTaxPayerIdList);

        return (List<Map>) this.ssmBatchClient.queryObjectList("getTaxPayerInfoForCreate", param);
    }

    /**
     * Get PrntVndCd.
     * @param pMsg01 NMZC201001PMsg
     * @return VND_CD or Null
     */
    @SuppressWarnings("unchecked")
    private List<Map> getTaxPayerInfo(NMZC201001PMsg pMsg01) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(SSM_PRM_GLBL_CMPY_CD, pMsg01.glblCmpyCd.getValue());
        param.put(SSM_PRM_TAX_PAYER_ID, pMsg01.taxPayerId.getValue());

        return (List<Map>) this.ssmBatchClient.queryObjectList("getTaxPayerInfo", param);
    }

    /**
     * Get ShipToCustPk.
     * @return shipToCustPk
     */
    private BigDecimal getShipToCustPk(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(SSM_PRM_GLBL_CMPY_CD, glblCmpyCd);
        param.put(SSM_PRM_SHIP_TO_CUST_CD, shipToCustCd);

        return (BigDecimal) this.ssmBatchClient.queryObject("getShipToCustPk", param);

    }

    /**
     * Get SupplierSitePrimaryKey.
     * @return CMPY_PK, PTY_LOC_PK, VND_PK or Null
     */
    @SuppressWarnings("unchecked")
    private Map getSupplierSitePrimaryKey(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(SSM_PRM_GLBL_CMPY_CD, glblCmpyCd);
        param.put(SSM_PRM_VND_CD, shipToCustCd);

        return (Map) this.ssmBatchClient.queryObject("getSupplierSitePrimaryKey", param);
    }

    /**
     * Get SupplierSitePrimaryKey
     * @return CMPY_PK, PTY_LOC_PK, VND_PK or Null
     */
    private BigDecimal getSupplierSiteName(String glblCmpyCd, String prntVndCd, String locNm) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(SSM_PRM_GLBL_CMPY_CD, glblCmpyCd);
        param.put(SSM_PRM_PRNT_VND_CD, prntVndCd);
        param.put(SSM_PRM_LOC_NM, locNm);

        return (BigDecimal) this.ssmBatchClient.queryObject("getSupplierSiteName", param);
    }

    /**
     * GetbSupplierSitePrimaryKey.
     * @return CMPY_PK, PTY_LOC_PK, VND_PK or Null
     */
    private String getCurrencyCd(String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(SSM_PRM_GLBL_CMPY_CD, glblCmpyCd);

        return (String) this.ssmBatchClient.queryObject("getCurrencyCd", param);
    }

    /**
     * Get CntyPk.
     * @param glblCmpyCd String
     * @param cntyNm String
     * @param stCd String
     * @return cntyPk or null
     */
    private BigDecimal getCntyPk(String glblCmpyCd, String cntyNm, String stCd) {

        CNTYTMsg cond = new CNTYTMsg();
        cond.setSQLID("002");
        cond.setConditionValue("glblCmpyCd01", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(cntyNm)) {
            cond.setConditionValue("cntyNm01", cntyNm);
        }
        cond.setConditionValue("stCd01", stCd);

        CNTYTMsgArray result = (CNTYTMsgArray) S21ApiTBLAccessor.findByCondition(cond);

        if (result.length() > 0) {
            // cntyNM + stCd : result 1 record.
            return result.no(0).cntyPk.getValue();
        } else {
            return null;
        }
    }

    /**
     * Get VndTpCd.
     * @param glblCmpyCd String
     * @param prntVndTpCd String
     * @return vndTpCd or null
     */
    private String getVndTpCd(String glblCmpyCd, String prntVndTpCd) {

        PRNT_VND_TPTMsg prntVndTp = new PRNT_VND_TPTMsg();

        ZYPEZDItemValueSetter.setValue(prntVndTp.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prntVndTp.prntVndTpCd, prntVndTpCd);

        prntVndTp = (PRNT_VND_TPTMsg) S21ApiTBLAccessor.findByKey(prntVndTp);

        if (prntVndTp == null) {
            return null;
        } else {
            return prntVndTp.vndTpCd.getValue();
        }
    }

    /**
     * Set PrntVndData.
     * @param pMsg01 NMZC201001PMsg
     * @param defVndInfo DEF_VND_INFOTMsg
     * @param prntVnd PRNT_VNDTMsg
     * @return PRNT_VNDTMsg
     */
    private PRNT_VNDTMsg setPrntVndData(NMZC201001PMsg pMsg01, DEF_VND_INFOTMsg defVndInfo, PRNT_VNDTMsg prntVnd) {

        ZYPEZDItemValueSetter.setValue(prntVnd.glblCmpyCd, pMsg01.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.prntVndCd, pMsg01.prntVndCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.prntVndNm, pMsg01.prntVndNm);
        ZYPEZDItemValueSetter.setValue(prntVnd.prntVndTpCd, pMsg01.prntVndTpCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.taxPayerId, pMsg01.taxPayerId);
        if (ZYPCommonFunc.hasValue(pMsg01.inacDt)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.inacDt, pMsg01.inacDt);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.inacDt, DEF_THRU_DT);
        }
        ZYPEZDItemValueSetter.setValue(prntVnd.taxPayerRgNum, pMsg01.taxPayerRgNum);
        ZYPEZDItemValueSetter.setValue(prntVnd.arcsSplyNum, pMsg01.arcsSplyNum);
        ZYPEZDItemValueSetter.setValue(prntVnd.arcsSplyId, pMsg01.arcsSplyId);
        ZYPEZDItemValueSetter.setValue(prntVnd.indyTpCd, pMsg01.indyTpCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.mnrtyOwndTpCd, pMsg01.mnrtyOwndTpCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.splyOrgTpCd, pMsg01.splyOrgTpCd);
        if (ZYPCommonFunc.hasValue(pMsg01.splyOneTmFlg)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.splyOneTmFlg, pMsg01.splyOneTmFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.splyOneTmFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(pMsg01.smBizFlg)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.smBizFlg, pMsg01.smBizFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.smBizFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(pMsg01.womenOwndFlg)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.womenOwndFlg, pMsg01.womenOwndFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.womenOwndFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(pMsg01.payAloneFlg)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.payAloneFlg, pMsg01.payAloneFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.payAloneFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(pMsg01.discTakeFlg)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.discTakeFlg, pMsg01.discTakeFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.discTakeFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(pMsg01.poPmtHldFlg)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.poPmtHldFlg, pMsg01.poPmtHldFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.poPmtHldFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(pMsg01.fdRptFlg)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.fdRptFlg, pMsg01.fdRptFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.fdRptFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(prntVnd.incTaxTpCd, pMsg01.incTaxTpCd);
        if (ZYPCommonFunc.hasValue(pMsg01.stTaxFlg)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.stTaxFlg, pMsg01.stTaxFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.stTaxFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(pMsg01.coaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.coaAfflCd, pMsg01.coaAfflCd);
        } else if (defVndInfo != null && ZYPCommonFunc.hasValue(defVndInfo.splyCoaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(prntVnd.coaAfflCd, defVndInfo.splyCoaAfflCd);
        } else {
            ZYPEZDItemValueSetter.setValue(prntVnd.coaAfflCd, "000");
        }
        ZYPEZDItemValueSetter.setValue(prntVnd.splyHubZnCd, pMsg01.splyHubZnCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.dsAcctNum, pMsg01.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(prntVnd.vndPmtTermCd, pMsg01.vndPmtTermCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.vndPmtTermDescTxt, pMsg01.vndPmtTermDescTxt);
        ZYPEZDItemValueSetter.setValue(prntVnd.vndPmtMethCd, pMsg01.vndPmtMethCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.payGrpCd, pMsg01.payGrpCd);

        // No longer used columns.
        ZYPEZDItemValueSetter.setValue(prntVnd.womenOwnedFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prntVnd.exclPmtFlg, ZYPConstant.FLG_OFF_N);

        return prntVnd;
    }

    /**
     * Set CmpyData.
     * @param glblCmpyCd String
     * @param pMsg02 NMZC201002PMsg
     * @param cmpy CMPYTMsg
     * @return CMPYTMsg
     */
    private CMPYTMsg setCmpyData(String glblCmpyCd, NMZC201002PMsg pMsg02, CMPYTMsg cmpy) {

        ZYPEZDItemValueSetter.setValue(cmpy.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmpy.cmpyNm, pMsg02.locNm);
        ZYPEZDItemValueSetter.setValue(cmpy.dbaNm, pMsg02.dbaNm);

        return cmpy;
    }

    /**
     * Set PtyLocWrkData.
     * @param glblCmpyCd String
     * @param procDt String
     * @param prntVndNm String
     * @param cmpyPk BigDecimal
     * @param pMsg02 NMZC201002PMsg
     * @param ptyLocWrk PTY_LOC_WRKTMsg
     * @return PTY_LOC_WRKTMsg
     */
    private PTY_LOC_WRKTMsg setPtyLocWrkData(String glblCmpyCd, String procDt, String prntVndNm, BigDecimal cmpyPk, NMZC201002PMsg pMsg02, PTY_LOC_WRKTMsg ptyLocWrk) {

        ZYPEZDItemValueSetter.setValue(ptyLocWrk.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.cmpyPk, cmpyPk);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.firstLineAddr, pMsg02.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.scdLineAddr, pMsg02.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.thirdLineAddr, pMsg02.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.frthLineAddr, pMsg02.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.ctyAddr, pMsg02.ctyAddr);
        // QC#22054 MOD START
        if (CTRY.UNITED_STATES_OF_AMERICA.equals(pMsg02.ctryCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrk.cntyPk, getCntyPk(glblCmpyCd, pMsg02.cntyNm.getValue(), pMsg02.stCd.getValue()));
        }
        // QC#22054 MOD END
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.provNm, pMsg02.provNm);
        // QC#22054 MOD START
        if (CTRY.UNITED_STATES_OF_AMERICA.equals(pMsg02.ctryCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrk.stCd, pMsg02.stCd);
        }
        // QC#22054 MOD END
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.postCd, pMsg02.postCd);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.ctryCd, pMsg02.ctryCd);
        // QC#15314
        String locNmWk = prntVndNm + "-" + pMsg02.locNm.getValue();

        if (locNmWk.length() > LOC_NM_LENGTH_60) {
            locNmWk = locNmWk.substring(0, LOC_NM_LENGTH_60);
        }

        ZYPEZDItemValueSetter.setValue(ptyLocWrk.locNm, locNmWk);

        // ZYPEZDItemValueSetter.setValue(ptyLocWrk.locNm, prntVndNm +
        // "-" + pMsg02.locNm.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.addlLocNm, pMsg02.addlLocNm);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.glnNum, pMsg02.glnNum);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.firstRefCmntTxt, pMsg02.firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.scdRefCmntTxt, pMsg02.scdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.dbaNm, pMsg02.dbaNm);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.telNum, pMsg02.telNum);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.faxNum, pMsg02.faxNum);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.apvlStsCd, APVL_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.effFromDt, procDt);
        if (ZYPCommonFunc.hasValue(pMsg02.effThruDt)) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrk.effThruDt, pMsg02.effThruDt);
        } else {
            ZYPEZDItemValueSetter.setValue(ptyLocWrk.effThruDt, DEF_THRU_DT);
        }
        if (ZYPCommonFunc.hasValue(pMsg02.rgtnStsCd)) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrk.rgtnStsCd, pMsg02.rgtnStsCd);
        } else {
            if (ZYPDateUtil.compare(pMsg02.effThruDt.getValue(), procDt) < 0) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrk.rgtnStsCd, RGTN_STS.TERMINATED);
            } else {
                ZYPEZDItemValueSetter.setValue(ptyLocWrk.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            }
        }
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.embgoFlg, ZYPConstant.FLG_OFF_N);

        // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 START
        if (!ZYPCommonFunc.hasValue(ptyLocWrk.locNum)) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrk.locNum, derivingLocationNumber(glblCmpyCd));
        }
        this.LocNum = ptyLocWrk.locNum.getValue();
        // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 END

        ZYPEZDItemValueSetter.setValue(ptyLocWrk.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.lineBizTpCd, TP_CD_ALL);
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.dsAcctNm, prntVndNm + "-" + pMsg02.locNm.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrk.dsLocNm, pMsg02.locNm);

        return ptyLocWrk;
    }

    /**
     * Set LocUsgData.
     * @param glblCmpyCd String
     * @param ptyLocPk BigDecimal
     * @param pMsg02 NMZC201002PMsg
     * @param locUsg LOC_USGTMsg
     * @return LOC_USGTMsg
     */
    private LOC_USGTMsg setLocUsgData(String glblCmpyCd, BigDecimal ptyLocPk, NMZC201002PMsg pMsg02, LOC_USGTMsg locUsg) {

        ZYPEZDItemValueSetter.setValue(locUsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(locUsg.ptyLocPk, ptyLocPk);
        if (ZYPCommonFunc.hasValue(pMsg02.locRoleTpCd)) {
            ZYPEZDItemValueSetter.setValue(locUsg.locRoleTpCd, pMsg02.locRoleTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(locUsg.locRoleTpCd, LOC_ROLE_TP.VENDOR);
        }
        if (ZYPCommonFunc.hasValue(pMsg02.locGrpCd)) {
            ZYPEZDItemValueSetter.setValue(locUsg.locGrpCd, pMsg02.locGrpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(locUsg.locGrpCd, LOC_GRP.VENDOR);
        }

        return locUsg;
    }

    /**
     * Set PtyData.
     * @param glblCmpyCd String
     * @param ptyLocPk BigDecimal
     * @param pMsg02 NMZC201002PMsg
     * @param pty PTYTMsg
     * @return PTYTMsg
     */
    private PTYTMsg setPtyData(String glblCmpyCd, BigDecimal ptyLocPk, NMZC201002PMsg pMsg02, PTYTMsg pty) {

        ZYPEZDItemValueSetter.setValue(pty.ptyCd, pMsg02.vndCd);
        if (ZYPCommonFunc.hasValue(pMsg02.locGrpCd)) {
            ZYPEZDItemValueSetter.setValue(pty.locGrpCd, pMsg02.locGrpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(pty.locGrpCd, LOC_GRP.VENDOR);
        }

        ZYPEZDItemValueSetter.setValue(pty.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pty.ptyLocPk, ptyLocPk);

        return pty;
    }

    /**
     * Set VndData.
     * @param glblCmpyCd String
     * @param prntVndPk BigDecimal
     * @param ptyLocWrk PTY_LOC_WRKTMsg
     * @param pMsg01 NMZC201001PMsg
     * @param pMsg02 NMZC201002PMsg
     * @param vnd VNDTMsg
     * @return VNDTMsg
     */
    private VNDTMsg setVndData(String glblCmpyCd, BigDecimal prntVndPk, PTY_LOC_WRKTMsg ptyLocWrk, NMZC201001PMsg pMsg01, NMZC201002PMsg pMsg02, VNDTMsg vnd) {

        ZYPEZDItemValueSetter.setValue(vnd.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndCd, pMsg02.vndCd);
        ZYPEZDItemValueSetter.setValue(vnd.taxPayerId, pMsg01.taxPayerId);

        ZYPEZDItemValueSetter.setValue(vnd.firstLineAddr, ptyLocWrk.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(vnd.scdLineAddr, ptyLocWrk.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(vnd.thirdLineAddr, ptyLocWrk.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(vnd.frthLineAddr, ptyLocWrk.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(vnd.ctyAddr, ptyLocWrk.ctyAddr);
        ZYPEZDItemValueSetter.setValue(vnd.cntyPk, ptyLocWrk.cntyPk);
        ZYPEZDItemValueSetter.setValue(vnd.provNm, ptyLocWrk.provNm);
        ZYPEZDItemValueSetter.setValue(vnd.stCd, ptyLocWrk.stCd);
        ZYPEZDItemValueSetter.setValue(vnd.postCd, ptyLocWrk.postCd);
        ZYPEZDItemValueSetter.setValue(vnd.ctryCd, ptyLocWrk.ctryCd);
        ZYPEZDItemValueSetter.setValue(vnd.locNm, pMsg02.locNm.getValue());

        ZYPEZDItemValueSetter.setValue(vnd.firstRefCmntTxt, ptyLocWrk.firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(vnd.scdRefCmntTxt, ptyLocWrk.scdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(vnd.dbaNm, ptyLocWrk.dbaNm);
        ZYPEZDItemValueSetter.setValue(vnd.rgtnStsCd, ptyLocWrk.rgtnStsCd);
        ZYPEZDItemValueSetter.setValue(vnd.ptyLocPk, ptyLocWrk.ptyLocPk);

        // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 START
        ZYPEZDItemValueSetter.setValue(vnd.locNum, ptyLocWrk.locNum);
        // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 END

        if (ZYPCommonFunc.hasValue(pMsg02.locRoleTpCd)) {
            ZYPEZDItemValueSetter.setValue(vnd.locRoleTpCd, pMsg02.locRoleTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.locRoleTpCd, LOC_ROLE_TP.VENDOR);
        }
        if (ZYPCommonFunc.hasValue(pMsg02.locGrpCd)) {
            ZYPEZDItemValueSetter.setValue(vnd.locGrpCd, pMsg02.locGrpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.locGrpCd, LOC_GRP.VENDOR);
        }
        if (ZYPCommonFunc.hasValue(pMsg02.effFromDt)) {
            ZYPEZDItemValueSetter.setValue(vnd.effFromDt, pMsg02.effFromDt);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.effFromDt, pMsg01.procDt);
        }
        if (ZYPCommonFunc.hasValue(pMsg02.effThruDt)) {
            ZYPEZDItemValueSetter.setValue(vnd.effThruDt, pMsg02.effThruDt);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.effThruDt, DEF_THRU_DT);
        }
        if (ZYPCommonFunc.hasValue(pMsg02.intlVndFlg)) {
            ZYPEZDItemValueSetter.setValue(vnd.intlVndFlg, pMsg02.intlVndFlg);
        } else {
            //QC#12215 Start
            if (!CTRY.UNITED_STATES_OF_AMERICA.equals(pMsg02.ctryCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(vnd.intlVndFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(vnd.intlVndFlg, ZYPConstant.FLG_OFF_N);
            }
            //QC#12215 End
        }
        if (ZYPCommonFunc.hasValue(pMsg02.asnReqFlg)) {
            ZYPEZDItemValueSetter.setValue(vnd.asnReqFlg, pMsg02.asnReqFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.asnReqFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(vnd.cmpyPk, ptyLocWrk.cmpyPk);
        ZYPEZDItemValueSetter.setValue(vnd.faxNum, ptyLocWrk.faxNum);
        ZYPEZDItemValueSetter.setValue(vnd.telNum, ptyLocWrk.telNum);

        if (ZYPCommonFunc.hasValue(pMsg02.payeeFlg)) {
            ZYPEZDItemValueSetter.setValue(vnd.payeeFlg, pMsg02.payeeFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.payeeFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(pMsg02.thirdPtyVndFlg)) {
            ZYPEZDItemValueSetter.setValue(vnd.thirdPtyVndFlg, pMsg02.thirdPtyVndFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.thirdPtyVndFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(vnd.trsmtMethTpCd, pMsg02.trsmtMethTpCd);
        ZYPEZDItemValueSetter.setValue(vnd.shpgSvcLvlCd, pMsg02.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(vnd.frtChrgToCd, pMsg02.frtChrgToCd);
        ZYPEZDItemValueSetter.setValue(vnd.frtChrgMethCd, pMsg02.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(vnd.invRcvMethTpCd, pMsg02.invRcvMethTpCd);

        ZYPEZDItemValueSetter.setValue(vnd.attnNm, pMsg02.attnNm);
        ZYPEZDItemValueSetter.setValue(vnd.whCd, pMsg02.whCd);

        // QC#16147 Add.
        // MOD START 2018/02/22 S21_NA#24142
        if (ZYPCommonFunc.hasValue(pMsg01.coaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(vnd.coaAfflCd, pMsg01.coaAfflCd);
        } else {
            DEF_VND_INFOTMsg defVndInfo = getDefaultVenderInfo(pMsg01);
            if (defVndInfo != null && ZYPCommonFunc.hasValue(defVndInfo.splyCoaAfflCd)) {
                ZYPEZDItemValueSetter.setValue(vnd.coaAfflCd, defVndInfo.splyCoaAfflCd);
            } else {
                ZYPEZDItemValueSetter.setValue(vnd.coaAfflCd, "000");
            }
        }
        // ZYPEZDItemValueSetter.setValue(vnd.coaAfflCd, pMsg01.coaAfflCd);
        // MOD END 2018/02/22 S21_NA#24142

        ZYPEZDItemValueSetter.setValue(vnd.sendPoEmlAddr, pMsg02.sendPoEmlAddr);

        if (ZYPCommonFunc.hasValue(pMsg02.dealCcyCd)) {
            ZYPEZDItemValueSetter.setValue(vnd.dealCcyCd, pMsg02.dealCcyCd);
        } else {
            String stdCcyCd = getCurrencyCd(glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(vnd.dealCcyCd, stdCcyCd);
        }
        if (ZYPCommonFunc.hasValue(pMsg02.invVndCd)) {
            ZYPEZDItemValueSetter.setValue(vnd.invVndCd, pMsg02.invVndCd);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.invVndCd, pMsg02.vndCd);
        }

        ZYPEZDItemValueSetter.setValue(vnd.prntVndPk, prntVndPk);
        ZYPEZDItemValueSetter.setValue(vnd.splyPmtFlg, pMsg02.splyPmtFlg);
        ZYPEZDItemValueSetter.setValue(vnd.splyPoFlg, pMsg02.splyPoFlg);
        ZYPEZDItemValueSetter.setValue(vnd.splyCoaCmpyCd, pMsg02.splyCoaCmpyCd);
        ZYPEZDItemValueSetter.setValue(vnd.splyCoaBrCd, pMsg02.splyCoaBrCd);
        ZYPEZDItemValueSetter.setValue(vnd.splyCoaCcCd, pMsg02.splyCoaCcCd);
        ZYPEZDItemValueSetter.setValue(vnd.splyCoaAcctCd, pMsg02.splyCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(vnd.splyCoaProdCd, pMsg02.splyCoaProdCd);
        ZYPEZDItemValueSetter.setValue(vnd.splyCoaChCd, pMsg02.splyCoaChCd);
        ZYPEZDItemValueSetter.setValue(vnd.splyCoaAfflCd, pMsg02.splyCoaAfflCd);
        ZYPEZDItemValueSetter.setValue(vnd.splyCoaProjCd, pMsg02.splyCoaProjCd);
        ZYPEZDItemValueSetter.setValue(vnd.splyCoaExtnCd, pMsg02.splyCoaExtnCd);

        ZYPEZDItemValueSetter.setValue(vnd.invMatchTpCd, pMsg02.invMatchTpCd);
        ZYPEZDItemValueSetter.setValue(vnd.invTolRate, pMsg02.invTolRate);
        ZYPEZDItemValueSetter.setValue(vnd.rcvTolRate, pMsg02.rcvTolRate);
        ZYPEZDItemValueSetter.setValue(vnd.vndPmtTermCd, pMsg02.vndPmtTermCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndPmtTermDescTxt, pMsg02.vndPmtTermDescTxt);
        ZYPEZDItemValueSetter.setValue(vnd.vndPmtMethCd, pMsg02.vndPmtMethCd);
        ZYPEZDItemValueSetter.setValue(vnd.payGrpCd, pMsg02.payGrpCd);

        ZYPEZDItemValueSetter.setValue(vnd.splyEdiLocCd, pMsg02.splyEdiLocCd);
        ZYPEZDItemValueSetter.setValue(vnd.splyEdiNum, pMsg02.splyEdiNum);

        if (ZYPCommonFunc.hasValue(pMsg02.inacDt)) {
            ZYPEZDItemValueSetter.setValue(vnd.inacDt, pMsg02.inacDt);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.inacDt, DEF_THRU_DT);
        }

        ZYPEZDItemValueSetter.setValue(vnd.splySiteDealCd, pMsg02.splySiteDealCd);
        ZYPEZDItemValueSetter.setValue(vnd.prePmtCoaCmpyCd, pMsg02.prePmtCoaCmpyCd);
        ZYPEZDItemValueSetter.setValue(vnd.prePmtCoaBrCd, pMsg02.prePmtCoaBrCd);
        ZYPEZDItemValueSetter.setValue(vnd.prePmtCoaCcCd, pMsg02.prePmtCoaCcCd);
        ZYPEZDItemValueSetter.setValue(vnd.prePmtCoaAcctCd, pMsg02.prePmtCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(vnd.prePmtCoaProdCd, pMsg02.prePmtCoaProdCd);
        ZYPEZDItemValueSetter.setValue(vnd.prePmtCoaChCd, pMsg02.prePmtCoaChCd);
        ZYPEZDItemValueSetter.setValue(vnd.prePmtCoaAfflCd, pMsg02.prePmtCoaAfflCd);
        ZYPEZDItemValueSetter.setValue(vnd.prePmtCoaProjCd, pMsg02.prePmtCoaProjCd);
        ZYPEZDItemValueSetter.setValue(vnd.prePmtCoaExtnCd, pMsg02.prePmtCoaExtnCd);

        ZYPEZDItemValueSetter.setValue(vnd.vndRtrnCoaCmpyCd, pMsg02.vndRtrnCoaCmpyCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndRtrnCoaBrCd, pMsg02.vndRtrnCoaBrCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndRtrnCoaCcCd, pMsg02.vndRtrnCoaCcCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndRtrnCoaAcctCd, pMsg02.vndRtrnCoaAcctCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndRtrnCoaProdCd, pMsg02.vndRtrnCoaProdCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndRtrnCoaChCd, pMsg02.vndRtrnCoaChCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndRtrnCoaAfflCd, pMsg02.vndRtrnCoaAfflCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndRtrnCoaProjCd, pMsg02.vndRtrnCoaProjCd);
        ZYPEZDItemValueSetter.setValue(vnd.vndRtrnCoaExtnCd, pMsg02.vndRtrnCoaExtnCd);

        ZYPEZDItemValueSetter.setValue(vnd.xtrnlRefNum, pMsg02.xtrnlRefNum);
        ZYPEZDItemValueSetter.setValue(vnd.endCustNum, pMsg02.endCustNum);
        ZYPEZDItemValueSetter.setValue(vnd.arcsSplySiteCd, pMsg02.arcsSplySiteCd);
        ZYPEZDItemValueSetter.setValue(vnd.arcsSplySiteId, pMsg02.arcsSplySiteId);
        ZYPEZDItemValueSetter.setValue(vnd.billToCustCd, pMsg02.billToCustCd);
        if (ZYPCommonFunc.hasValue(pMsg02.sendArcsFlg)) {
            ZYPEZDItemValueSetter.setValue(vnd.sendArcsFlg, pMsg02.sendArcsFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(vnd.sendArcsFlg, ZYPConstant.FLG_OFF_N);
        }

        return vnd;
    }

    /**
     * Set ShipToCustData.
     * @param glblCmpyCd String
     * @param ptyLocWrk PTY_LOC_WRKTMsg
     * @param pMsg02 NMZC201002PMsg
     * @param shipToCust SHIP_TO_CUSTTMsg
     * @return SHIP_TO_CUSTTMsg
     */
    private SHIP_TO_CUSTTMsg setShipToCustData(String glblCmpyCd, PTY_LOC_WRKTMsg ptyLocWrk, NMZC201002PMsg pMsg02, SHIP_TO_CUSTTMsg shipToCust) {

        ZYPEZDItemValueSetter.setValue(shipToCust.glblCmpyCd, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(shipToCust.firstLineAddr, ptyLocWrk.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(shipToCust.scdLineAddr, ptyLocWrk.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(shipToCust.thirdLineAddr, ptyLocWrk.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(shipToCust.frthLineAddr, ptyLocWrk.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(shipToCust.ctyAddr, ptyLocWrk.ctyAddr);
        ZYPEZDItemValueSetter.setValue(shipToCust.cntyPk, ptyLocWrk.cntyPk);
        ZYPEZDItemValueSetter.setValue(shipToCust.provNm, ptyLocWrk.provNm);
        ZYPEZDItemValueSetter.setValue(shipToCust.stCd, ptyLocWrk.stCd);
        ZYPEZDItemValueSetter.setValue(shipToCust.postCd, ptyLocWrk.postCd);
        ZYPEZDItemValueSetter.setValue(shipToCust.ctryCd, ptyLocWrk.ctryCd);
        ZYPEZDItemValueSetter.setValue(shipToCust.locNm, ptyLocWrk.locNm);

        ZYPEZDItemValueSetter.setValue(shipToCust.addlLocNm, ptyLocWrk.addlLocNm);
        ZYPEZDItemValueSetter.setValue(shipToCust.glnNum, ptyLocWrk.glnNum);
        ZYPEZDItemValueSetter.setValue(shipToCust.firstRefCmntTxt, ptyLocWrk.firstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(shipToCust.scdRefCmntTxt, ptyLocWrk.scdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(shipToCust.dbaNm, ptyLocWrk.dbaNm);
        ZYPEZDItemValueSetter.setValue(shipToCust.dunsNum, ptyLocWrk.dunsNum);
        ZYPEZDItemValueSetter.setValue(shipToCust.rgtnStsCd, ptyLocWrk.rgtnStsCd);

        ZYPEZDItemValueSetter.setValue(shipToCust.cnsgnFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shipToCust.ptyLocPk, ptyLocWrk.ptyLocPk);

        if (ZYPCommonFunc.hasValue(pMsg02.locRoleTpCd)) {
            ZYPEZDItemValueSetter.setValue(shipToCust.locRoleTpCd, pMsg02.locRoleTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(shipToCust.locRoleTpCd, LOC_ROLE_TP.VENDOR);
        }
        if (ZYPCommonFunc.hasValue(pMsg02.locGrpCd)) {
            ZYPEZDItemValueSetter.setValue(shipToCust.locGrpCd, pMsg02.locGrpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(shipToCust.locGrpCd, LOC_GRP.VENDOR);
        }

        ZYPEZDItemValueSetter.setValue(shipToCust.effFromDt, ptyLocWrk.effFromDt);
        ZYPEZDItemValueSetter.setValue(shipToCust.effThruDt, ptyLocWrk.effThruDt);

        ZYPEZDItemValueSetter.setValue(shipToCust.cmpyPk, ptyLocWrk.cmpyPk);

        ZYPEZDItemValueSetter.setValue(shipToCust.faxNum, ptyLocWrk.faxNum);
        ZYPEZDItemValueSetter.setValue(shipToCust.telNum, ptyLocWrk.telNum);

        // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 START
        ZYPEZDItemValueSetter.setValue(shipToCust.locNum, ptyLocWrk.locNum);
        // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 END

        ZYPEZDItemValueSetter.setValue(shipToCust.shipToCustCd, pMsg02.vndCd);

        ZYPEZDItemValueSetter.setValue(shipToCust.oemFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shipToCust.embgoFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(shipToCust.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shipToCust.primUsgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(shipToCust.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(shipToCust.lineBizTpCd, ptyLocWrk.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(shipToCust.dsAcctNm, ptyLocWrk.dsAcctNm);
        ZYPEZDItemValueSetter.setValue(shipToCust.dsLocNm, ptyLocWrk.dsLocNm);
        // 2023/11/06 QC#61924 Add Start
        ZYPEZDItemValueSetter.setValue(shipToCust.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
        // 2023/11/06 QC#61924 Add End
        return shipToCust;
    }

    /**
     * Set VndTpRelnData.
     * @param glblCmpyCd String
     * @param vndCd String
     * @param vndTpCd String
     * @param vndTpReln VND_TP_RELNTMsg
     * @return VND_TP_RELNTMsg
     */
    private VND_TP_RELNTMsg setVndTpRelnData(String glblCmpyCd, String vndCd, String vndTpCd, VND_TP_RELNTMsg vndTpReln) {

        ZYPEZDItemValueSetter.setValue(vndTpReln.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndTpReln.vndCd, vndCd);
        ZYPEZDItemValueSetter.setValue(vndTpReln.vndTpCd, vndTpCd);

        return vndTpReln;
    }

    /**
     * execute ContactInfo API.
     * @param glblCmpyCd String
     * @param procDt String
     * @param pMsg03List List<NMZC201003PMsg>
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    private boolean executeContactInfoAPI(String glblCmpyCd, String procDt, List<NMZC201003PMsg> pMsg03List, S21ApiMessageMap msgMap) {

        // create NMZC002001 parameter
        List<NMZC002001PMsg> paramList = createNMZC002001PmsgList(glblCmpyCd, procDt, pMsg03List);

        NMZC002001 api = new NMZC002001();

        // execute
        api.execute(paramList, batchType);

        // check result
        boolean apiChkFlg = false;
        for (NMZC002001PMsg pMsg : paramList) {
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                // Error
                apiChkFlg = true;
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
            }
        }

        if (apiChkFlg) {
            // api Error
            return false;
        } else {
            // success
            setOutputParameter(paramList, pMsg03List);
            return true;
        }
    }

    /**
     * set OutputParameter.
     * @param paramList
     * @param pMsg03List
     */
    private void setOutputParameter(List<NMZC002001PMsg> paramList, List<NMZC201003PMsg> pMsg03List) {

        for (int i = 0; i < pMsg03List.size(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg03List.get(i).ctacPsnPk, paramList.get(i).ctacPsnPk);
            for (int j = 0; j < pMsg03List.get(i).ContactPointInfoList.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(pMsg03List.get(i).ContactPointInfoList.no(j).dsCtacPntPk, paramList.get(i).ContactPointInfoList.no(j).dsCtacPntPk);
            }
        }
    }

    /**
     * create NMZC002001PmsgList.
     * @param glblCmpyCd String
     * @param procDt String
     * @param pMsg03List List<NMZC201003PMsg>
     * @return List<NMZC002001PMsg>
     */
    private List<NMZC002001PMsg> createNMZC002001PmsgList(String glblCmpyCd, String procDt, List<NMZC201003PMsg> pMsg03List) {

        List<NMZC002001PMsg> list = new ArrayList<NMZC002001PMsg>();

        for (NMZC201003PMsg pMsg03 : pMsg03List) {
            NMZC002001PMsg cotactIofo = new NMZC002001PMsg();

            ZYPEZDItemValueSetter.setValue(cotactIofo.xxModeCd, pMsg03.xxModeCd);
            ZYPEZDItemValueSetter.setValue(cotactIofo.glblCmpyCd, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(procDt)) {
                cotactIofo.slsDt.setValue(procDt);
            }
            ZYPEZDItemValueSetter.setValue(cotactIofo.ctacPsnPk, pMsg03.ctacPsnPk);
            ZYPEZDItemValueSetter.setValue(cotactIofo.dsAcctNum, pMsg03.dsAcctNum);

            if (!ZYPCommonFunc.hasValue(pMsg03.locNum)) {
                ZYPEZDItemValueSetter.setValue(cotactIofo.locNum, this.LocNum);
                ZYPEZDItemValueSetter.setValue(pMsg03.locNum, this.LocNum);
            } else {
                ZYPEZDItemValueSetter.setValue(cotactIofo.locNum, pMsg03.locNum);
            }

            ZYPEZDItemValueSetter.setValue(cotactIofo.effFromDt, pMsg03.effFromDt);
            ZYPEZDItemValueSetter.setValue(cotactIofo.effThruDt, pMsg03.effThruDt);
            // QC#8598(L3) DEL START Do not update Primary Contact.
            // ZYPEZDItemValueSetter.setValue(cotactIofo.dsPrimLocFlg, pMsg03.dsPrimLocFlg);
            // QC#8598(L3) DEL END
            ZYPEZDItemValueSetter.setValue(cotactIofo.ctacPsnFirstNm, pMsg03.ctacPsnFirstNm);
            ZYPEZDItemValueSetter.setValue(cotactIofo.ctacPsnLastNm, pMsg03.ctacPsnLastNm);
            ZYPEZDItemValueSetter.setValue(cotactIofo.ctacTpCd, pMsg03.ctacTpCd);
            ZYPEZDItemValueSetter.setValue(cotactIofo.ctacPsnCmntTxt, pMsg03.ctacPsnCmntTxt);
            ZYPEZDItemValueSetter.setValue(cotactIofo.ctacPsnActvFlg, pMsg03.ctacPsnActvFlg);
            ZYPEZDItemValueSetter.setValue(cotactIofo.dsCtacPsnSaltCd, pMsg03.dsCtacPsnSaltCd);
            ZYPEZDItemValueSetter.setValue(cotactIofo.dsCtacPsnDeptCd, pMsg03.dsCtacPsnDeptCd);
            ZYPEZDItemValueSetter.setValue(cotactIofo.dsCtacPsnTtlCd, pMsg03.dsCtacPsnTtlCd);
            ZYPEZDItemValueSetter.setValue(cotactIofo.dsPrimCtacTpCd, pMsg03.dsPrimCtacTpCd);

            for (int i = 0; i < pMsg03.ContactPointInfoList.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).xxModeCd, pMsg03.ContactPointInfoList.no(i).xxModeCd);
                ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntPk, pMsg03.ContactPointInfoList.no(i).dsCtacPntPk);
                ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntTpCd, pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd);
                ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntValTxt, pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt);
                ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPsnExtnNum, pMsg03.ContactPointInfoList.no(i).dsCtacPsnExtnNum);
                ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsOpsOutFlg, pMsg03.ContactPointInfoList.no(i).dsOpsOutFlg);
                ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntActvFlg, pMsg03.ContactPointInfoList.no(i).dsCtacPntActvFlg);
            }
            cotactIofo.ContactPointInfoList.setValidCount(pMsg03.ContactPointInfoList.getValidCount());

            // Add list
            list.add(cotactIofo);
        }

        return list;
    }

    /** QC#14489
     * get Exclude TaxPayerId.
     * @param glblCmpyCd String
     */
    private void getExcludeTaxPayerId(String glblCmpyCd) {

        // Get CUSA Excluded System Source Code
        String excludeTaxPayerId = ZYPCodeDataUtil.getVarCharConstValue(CUSA_EXCL_TAX_PAY_ID, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(excludeTaxPayerId)) {

            excludeTaxPayerIdList = new ArrayList<String>();
            String[] arrayExcludeTaxPayerId = excludeTaxPayerId.split(",", 0);
            for (int i = 0; i < arrayExcludeTaxPayerId.length; i++) {
                excludeTaxPayerIdList.add(arrayExcludeTaxPayerId[i]);
            }

        }

    }
}
