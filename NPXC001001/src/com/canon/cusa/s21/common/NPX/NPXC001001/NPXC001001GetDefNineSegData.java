package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_CH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * Nine Segment Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/12   Fujitsu         S.Takami        Create          QC#29456
 * 2019/01/15   Fujitsu         S.Takami        Update          QC#29778
 * 2019/01/25   Fujitsu         S.Takami        Update          QC#29778-2
 * 2019/02/06   Fujitsu         S.Takami        Update          QC#30181 (Modify Logic with out any comments)
 *</pre>
 */
public final class NPXC001001GetDefNineSegData {

    /** Instance */
    private static NPXC001001GetDefNineSegData myInstance = null;

    /** Var Char Const Cache */
    private Map<String, String> varCharConstCache = null;

    /** Ship To Cust Cache */
    private Map<String, SHIP_TO_CUSTTMsg> shipToCustCache = null;

    /** Var Char Const Name */
    private enum VarCharConstNm {
        /** Cost Of Sales Condition */
        NPXC0010_COST_OF_SALES,
        /** SGA Condition */
        NPXC0010_SGA,
        /** Default Channel Code for Cost Of Sales */
        NPXC0010_DEF_CH_CD_COST_SALES,
        /** Default Prod Code for SGA */
        NPXC0010_DEF_PROD_CD_SGA,
        /** Default Channel Code for SGA */
        NPXC0010_DEF_CH_CD_SGA,
        /** Default Mdse Type */
        NPXC0010_DEF_PROJ_CD_SGA,
        /** Error COA Product Code for Cost Of Sales */
        NPXC0010_ERR_PROD_CD_COSTSALES,
        /** Error COA Project Code - Mdse Tp for Cost Of Sales */
        NPXC0010_ERR_PROJ_CD_COSTSALES,
        // 2019/02/06 QC#30181 Add Start
        /** Ship Data Over Write */
        NPXC0010_SHIP_DATA_OVER_WRITE,
        /** Item Data Over Write */
        NPXC0010_ITEM_DATA_OVER_WRITE,
        // 2019/02/06 QC#30181 Add End
    }

    /** Default Value Enum */
    private enum DefVal {
        /** Default Cost Of Sales 1st Account Character */
        DEF_CHAR_CONST_OF_SALES("5"),

        /** Default SGA 1st Account Character */
        DEF_CHAR_SGA("6"),

        /** Coast of Sales Fixed Channel Code */
        DEF_CH_CD_COST_OF_SALES(COA_CH.PRODUCT_ADMINI), // 998

        /** SGA Fixed Prod Code */
        DEF_PROD_CD_SGA(COA_PROD.ADMINISTRATION), // ZZ

        /** SGA Fixed Prod Code */
        DEF_CH_CD_SGA(COA_CH.CORPORATE_ADMINI), // 999

        /** SGA Fixed Prod Code */
        DEF_PROJ_CD_SGA(COA_PROJ.DEFAULT); // 00

        /** Default Value */
        private String val;

        /**
         * Constructor
         * @param val Default Value
         */
        DefVal(String val) {
            this.val = val;
        }

        /**
         * get default value
         * @return default value
         */
        protected String getDefVal() {
            return this.val;
        }
    }
    /** Message ID */
    public static enum MessageId {

        /** Ship To Cust Code does not exist in Ship To Cust Master. */
        NPZM0150E("NPZM0150E"),
        /** You cannot use this Product code for Cost Of Sales. Please set the other Product code. */
        NPZM0307E("NPZM0307E"),
        /** You cannot use this Merchandise Type code for Cost Of Sales. Please set the other Merchandise Type code. */
        NPZM0308E("NPZM0308E");

        /** Message ID */
        private String msgId;

        MessageId(String msgId) {
            this.msgId = msgId;
        }

        /**
         * get Message Id
         * @return Message ID
         */
        public String getMessageId() {
            return this.msgId;
        }
    }

    // 2019/02/06 QC#30181 Add Start
    /** Ship To Data Over Write Flag */
    private boolean shipDataOverWrite = false;

    /** Item Data Over Write Flag */
    private boolean itemDataOverWrite = true;
    // 2019/02/06 QC#30181 Add End

    /**
     * Private Constants
     */
    private NPXC001001GetDefNineSegData() {

        this.varCharConstCache = new HashMap<String, String>();
        this.shipToCustCache = new HashMap<String, SHIP_TO_CUSTTMsg>();
    }

    /**
     * Get Instance of This Class
     * @return NPXC001001GetDefNineSegData class
     */
    public static NPXC001001GetDefNineSegData getInstance() {

        if (myInstance == null) {
            myInstance = new NPXC001001GetDefNineSegData();
        }
        return myInstance;
    }

    /**
     * <pre>
     * 2019/02/06 QC#30181 change logic add parameter chargeAccountField
     * Get Default 9 Segment Data based on Ship To Customer.
     * @param glblCmpyCd Global Company Code (Mandatory)
     * @param shipToCustCd Ship To Customer Code (Mandatory)
     * @param mdseCd Merchandise Code (Optional)
     * @param chargeAccountField Charge Account Field text. COA data delimitted by ".".
     * @return
     * </pre>
     */
    public NPXC001001GetDefNineSegDataBean getDefNineSegData(String glblCmpyCd, String shipToCustCd, String mdseCd, String chargeAccountField) {

        // 2019/02/06 QC#30181 Del Start
//        if (!ZYPCommonFunc.hasValue(glblCmpyCd)//
//                || !ZYPCommonFunc.hasValue(shipToCustCd)) {
//            return null;
//        }
        // 2019/02/06 QC#30181 Del End

        // 2019/02/06 QC#30181 Add Start
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(shipToCustCd) && !ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }
        // 2019/02/06 QC#30181 Add End

        // 2019/02/06 QC#30181 Add Start
        setOverWriteFlag(glblCmpyCd);
        // 2019/02/06 QC#30181 Add End

        NPXC001001GetDefNineSegDataBean defNineSegBean = new NPXC001001GetDefNineSegDataBean();
        // 2019/02/06 QC#30181 Add Start
        if (chargeAccountField != null) {
            defNineSegBean.setChrgAcctText(chargeAccountField);
            setCoaValueFromChargeAccountTxt(defNineSegBean);
        }
        // 2019/02/06 QC#30181 Add End

        // 2019/02/06 QC#30181 Mod Start  without any comments.
        /** COA_CMPY_CD (Company) */
        defNineSegBean.setCoaCmpyCd(glblCmpyCd);
        // If shipToCustCd parameter was set, coa data from Ship To Cust Table.
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            SHIP_TO_CUSTTMsg shipToCustTMsg = getShipToCust(glblCmpyCd, shipToCustCd);
            // If SHIP_TO_CUST data was be able to obtain, this logic will be used.
            if (shipToCustTMsg != null) {
                // 2019/02/06 QC#30181 Mod Start Data Set Logic are modified without any comments. ->
                /** COA_BR_CD (Branch) */
                defNineSegBean.setCoaBrCd(getValidStringValueShipFields(defNineSegBean.getCoaBrCd(), shipToCustTMsg.coaBrCd.getValue()));

                /** COA_CC_CD (Cost Center) */
                defNineSegBean.setCoaCcCd(getValidStringValueShipFields(defNineSegBean.getCoaCcCd(), shipToCustTMsg.coaCcCd.getValue()));

                /** COA_ACCT_CD (Account) */
                defNineSegBean.setCoaAcctCd(getValidStringValueShipFields(defNineSegBean.getCoaAcctCd(), shipToCustTMsg.coaAcctCd.getValue()));

                /** COA_PROD_CD (Product) */
                // Product Code is MDSE Field, however this logic is ship to logic. Then using "getValidStringValueShipFields" method.
                defNineSegBean.setCoaProdCd(getValidStringValueShipFields(defNineSegBean.getCoaProdCd(), shipToCustTMsg.coaProdCd.getValue()));

                /** COA_CH_CD (Channel) */
                defNineSegBean.setCoaChCd(getValidStringValueShipFields(defNineSegBean.getCoaChCd(), shipToCustTMsg.coaChCd.getValue()));

                /** COA_AFFL_CD (Intercompany) */
                defNineSegBean.setCoaAfflCd(getValidStringValueShipFields(defNineSegBean.getCoaAfflCd(), shipToCustTMsg.coaAfflCd.getValue()));

                /** COA_PROJ_CD (Merchandise) */
                // Project Code is MDSE Field, however this logic is ship to logic. Then using "getValidStringValueShipFields" method.
                defNineSegBean.setCoaProjCd(getValidStringValueShipFields(defNineSegBean.getCoaProjCd(), shipToCustTMsg.coaProjCd.getValue()));

                /** COA_EXTN_CD (Business Unit) */
                defNineSegBean.setCoaExtnCd(getValidStringValueShipFields(defNineSegBean.getCoaExtnCd(), shipToCustTMsg.coaExtnCd.getValue()));
            }
        }
        // 2019/02/06 QC#30181 Mod End  without any comments.

        // Other Default Setting
        boolean isShipToMode = ZYPCommonFunc.hasValue(shipToCustCd);
        if (ZYPCommonFunc.hasValue(mdseCd)) {
            isShipToMode = false;
        }
        setOtherDefault(glblCmpyCd, mdseCd, defNineSegBean, isShipToMode);

        // 2019/02/06 QC#30181 Add Start
        setChargeAccountTxtFromCoaData(defNineSegBean);
        // 2019/02/06 QC#30181 Add End

        // Default Error Check
        checkDefault(glblCmpyCd, defNineSegBean);

        return defNineSegBean;
    }

    // 2019/02/06 QC#30181 Del Start
//    /**
//     * <pre>
//     * Get Nine Segment Data from Item data(MDSE).
//     * This method get just only coaMdseTpCd and coaProdCd from
//     * @param glblCmpyCd Global Company Code
//     * @param coaAcctCd COA Account Code
//     * @param mdseCd MDSE Code
//     * @return NPXC001001GetDefNineSegDataBean
//     * </pre>
//     */
//    public NPXC001001GetDefNineSegDataBean getNineSegDataFromItem(String glblCmpyCd, String coaAcctCd, String mdseCd) {
//
//        if (!ZYPCommonFunc.hasValue(glblCmpyCd)//
//                || !ZYPCommonFunc.hasValue(mdseCd)) {
//            return null;
//        }
//
//        NPXC001001GetDefNineSegDataBean defNineSegBean = new NPXC001001GetDefNineSegDataBean();
//        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
//            defNineSegBean.setCoaAcctCd(coaAcctCd);
//        }
//        setOtherDefault(glblCmpyCd, mdseCd,  defNineSegBean);
//        // 2019/01/25 QC#29778-2 Add Start
//        if (!ZYPCommonFunc.hasValue(defNineSegBean.getCoaCmpyCd())) {
//            defNineSegBean.setCoaCmpyCd(glblCmpyCd);
//        }
//        // 2019/01/25 QC#29778-2 Add End
//        return defNineSegBean;
//    }
    // 2019/02/06 QC#30181 Del End
    /**
     * <pre>
     * Check relation ship between COA accout code, COA prod code, coa Project Code
     * @param glblCmpyCd Global Company Code
     * @param defNineSegBean  if some errors were occurred, error message will be setup in NPXC001001GetDefNineSegDataBean#getMsgIdList() -> List Of message Id
     */
    private void checkDefault(String glblCmpyCd, NPXC001001GetDefNineSegDataBean defNineSegBean) {

        String coaAcctCd = defNineSegBean.getCoaAcctCd();
        // 2019/02/06 QC#30181 Add Start
        if (!ZYPCommonFunc.hasValue(coaAcctCd)) {
            return;
        }
        // 2019/02/06 QC#30181 Add End

        if (isCostOfSales(glblCmpyCd, coaAcctCd)) {
            // Error Check: COA_PROD_CD
            String errProdCd = getVarCharConstVal(VarCharConstNm.NPXC0010_ERR_PROD_CD_COSTSALES.name(), glblCmpyCd);
            if (errProdCd != null //
                    && S21StringUtil.isEquals(errProdCd, defNineSegBean.getCoaProdCd())) {
                defNineSegBean.addMsgIdList(MessageId.NPZM0307E.getMessageId());
            }

            // Error Check: COA_PROJ_CD (COA_MDSE_TP_CD)
            String errProjCd = getVarCharConstVal(VarCharConstNm.NPXC0010_ERR_PROJ_CD_COSTSALES.name(), glblCmpyCd);
            if (errProjCd != null //
                    && S21StringUtil.isEquals(errProdCd, defNineSegBean.getCoaProjCd())) {
                defNineSegBean.addMsgIdList(MessageId.NPZM0308E.getMessageId());
            }
        }
    }

    /**
     * <pre>
     * Check relation ship between COA accout code, COA prod code, coa Project Code
     * @param glblCmpyCd Global Company Code
     * @param coaAcctCd COA Account Code
     * @param coaProdCd COA Product Code
     * @param coaProjCd COA Project Code (COA_MDSE_TP_CD)
     * @return if some errors were occurred, error message will be setup in NPXC001001GetDefNineSegDataBean#getMsgIdList() -> List Of message Id
     * </pre>
     */
    public NPXC001001GetDefNineSegDataBean checkDefault(String glblCmpyCd, String coaAcctCd, String coaProdCd, String coaProjCd) {

        NPXC001001GetDefNineSegDataBean defNineSegBean = new NPXC001001GetDefNineSegDataBean();
        defNineSegBean.setCoaAcctCd(coaAcctCd);
        defNineSegBean.setCoaProdCd(coaProdCd);
        defNineSegBean.setCoaProjCd(coaProjCd);

        checkDefault(glblCmpyCd, defNineSegBean);

        return defNineSegBean;
    }

    private SHIP_TO_CUSTTMsg getShipToCust(String glblCmpyCd, String shipToCustCd) {

        String key = glblCmpyCd + ":" + shipToCustCd;
        SHIP_TO_CUSTTMsg rsltShipToCustTMsg = this.shipToCustCache.get(key);
        if (rsltShipToCustTMsg == null) {
            SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
            shipToCustTMsg.setSQLID("002");
            shipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
            shipToCustTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

            SHIP_TO_CUSTTMsgArray rsltShipToCustTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
            if (rsltShipToCustTMsgArray != null && rsltShipToCustTMsgArray.getValidCount() > 0) {
                rsltShipToCustTMsg = rsltShipToCustTMsgArray.no(0);
                this.shipToCustCache.put(key, rsltShipToCustTMsg);
            }
        }
        return rsltShipToCustTMsg;
    }

    private void setOtherDefault(String glblCmpyCd, String mdseCd,  NPXC001001GetDefNineSegDataBean defNineSegBean, boolean shipToMode) {

        // 2019/01/25 QC#29778-2 Del Start
//        if (!ZYPCommonFunc.hasValue(mdseCd)) {
//            return;
//        }
        // 2019/01/25 QC#29778-2 Del End

        if (ZYPCommonFunc.hasValue(mdseCd)) { // 2019/01/25 QC#29778-2 Add Condition
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
            // 2019/01/25 QC#29778-2 Del Start
//            if (mdseTMsg == null) {
//                return;
//            }
            // 2019/01/25 QC#29778-2 Del End
            if (mdseTMsg != null) { // 2019/01/25 QC#29778-2 Add Condition
                // 2019/02/06 QC#30181 Del Start
//                if (ZYPCommonFunc.hasValue(mdseTMsg.coaMdseTpCd)) { // 2019/01/15 QC#29778 Add Condition
//                    defNineSegBean.setCoaProjCd(mdseTMsg.coaMdseTpCd.getValue());
//                } // 2019/01/15 QC#29778 Add Condition
//                if (ZYPCommonFunc.hasValue(mdseTMsg.coaProdCd)) { // 2019/01/15 QC#29778 Add Condition
//                    defNineSegBean.setCoaProdCd(mdseTMsg.coaProdCd.getValue());
//                }
                defNineSegBean.setCoaProjCd(getValidStringValueItemFields(defNineSegBean.getCoaProjCd(), mdseTMsg.coaMdseTpCd.getValue()));
                defNineSegBean.setCoaProdCd(getValidStringValueItemFields(defNineSegBean.getCoaProdCd(), mdseTMsg.coaProdCd.getValue()));
                // 2019/02/06 QC#30181 Del End
            } // 2019/01/25 QC#29778-2 Add Condition
        } // 2019/01/15 QC#29778 Add Condition

        String coaAcctCd = defNineSegBean.getCoaAcctCd();
        // 2019/01/15 QC#29778 Add Start
        if (!ZYPCommonFunc.hasValue(coaAcctCd)) {
            return;
        }
        // 2019/01/15 QC#29778 Add End
        if (isCostOfSales(glblCmpyCd, coaAcctCd)) {
            String chCd = getVarCharConstVal(VarCharConstNm.NPXC0010_DEF_CH_CD_COST_SALES.name(), glblCmpyCd);
            if (chCd == null) {
                chCd = DefVal.DEF_CH_CD_COST_OF_SALES.getDefVal();
            }
            if (defNineSegBean.isOrigCoaChNullFlg()) {
                defNineSegBean.setCoaChCd(chCd);
            } else {
                defNineSegBean.setCoaChCd(getValidStringValueShipFields(defNineSegBean.getCoaChCd(), chCd));
            }
        } else if (isSga(glblCmpyCd, coaAcctCd)) {
            // SGA, Default Prod Code
            String prodCd = getVarCharConstVal(VarCharConstNm.NPXC0010_DEF_PROD_CD_SGA.name(), glblCmpyCd);
            if (prodCd == null) {
                prodCd = DefVal.DEF_PROD_CD_SGA.getDefVal();
            }
            if (defNineSegBean.isOrigCoaProdNullFlg()) {
                defNineSegBean.setCoaProdCd(prodCd);
            } else {
                if (shipToMode) {
                    defNineSegBean.setCoaProdCd(getValidStringValueShipFields(defNineSegBean.getCoaProdCd(), prodCd));
                } else {
                    defNineSegBean.setCoaProdCd(getValidStringValueItemFields(defNineSegBean.getCoaProdCd(), prodCd));
                }
            }

            // SGA, Default Channel Code
            String chCd = getVarCharConstVal(VarCharConstNm.NPXC0010_DEF_CH_CD_SGA.name(), glblCmpyCd);
            if (chCd == null) {
                chCd = DefVal.DEF_CH_CD_SGA.getDefVal();
            }
            if (defNineSegBean.isOrigCoaChNullFlg()) {
                defNineSegBean.setCoaChCd(chCd);
            } else {
                defNineSegBean.setCoaChCd(getValidStringValueShipFields(defNineSegBean.getCoaChCd(), chCd));
            }
            // SGA, Default Project Code (Mdse Type Code)
            String projCd = getVarCharConstVal(VarCharConstNm.NPXC0010_DEF_PROJ_CD_SGA.name(), glblCmpyCd);
            if (projCd == null) {
                projCd = DefVal.DEF_PROJ_CD_SGA.getDefVal();
            }
            if (defNineSegBean.isOrigCoaProjNullFlg()) {
                defNineSegBean.setCoaProjCd(projCd);
            } else {
                if (shipToMode) {
                    defNineSegBean.setCoaProjCd(getValidStringValueShipFields(defNineSegBean.getCoaProjCd(), projCd));
                } else {
                    defNineSegBean.setCoaProjCd(getValidStringValueItemFields(defNineSegBean.getCoaProjCd(), projCd));
                }
            }
        }
    }

    private boolean isCostOfSales(String glblCmpyCd, String coaAcctCd) {

        String costOfSalesCond = getVarCharConstVal(VarCharConstNm.NPXC0010_COST_OF_SALES.name(), glblCmpyCd);
        if (costOfSalesCond == null) {
            costOfSalesCond = DefVal.DEF_CHAR_CONST_OF_SALES.getDefVal();
        }
        return coaAcctCd.startsWith(costOfSalesCond);
    }

    private boolean isSga(String glblCmpyCd, String coaAcctCd) {

        String costOfSalesCond = getVarCharConstVal(VarCharConstNm.NPXC0010_SGA.name(), glblCmpyCd);
        if (costOfSalesCond == null) {
            costOfSalesCond = DefVal.DEF_CHAR_SGA.getDefVal();
        }
        return coaAcctCd.startsWith(costOfSalesCond);
    }

    private String getVarCharConstVal(String varCharConstNm, String glblCmpyCd) {

        String key = glblCmpyCd + ":" + varCharConstNm;
        String varCharVal = this.varCharConstCache.get(key);
        if (varCharVal == null) {
            varCharVal = ZYPCodeDataUtil.getVarCharConstValue(varCharConstNm, glblCmpyCd);
            if (varCharVal != null) {
                this.varCharConstCache.put(key, varCharVal);
            }
        }
        return varCharVal;
    }

    // 2019/02/06 QC#30181 Add Start
    private void setOverWriteFlag(String glblCmpyCd) {

        String shipDataOverWriteVal = getVarCharConstVal(VarCharConstNm.NPXC0010_SHIP_DATA_OVER_WRITE.name(), glblCmpyCd);
        String itemDataOverWriteVal = getVarCharConstVal(VarCharConstNm.NPXC0010_ITEM_DATA_OVER_WRITE.name(), glblCmpyCd);

        if (shipDataOverWriteVal == null) {
            this.shipDataOverWrite = false;
        } else {
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, shipDataOverWriteVal)) {
                this.shipDataOverWrite = true;
            } else {
                this.shipDataOverWrite = false;
            }
        }

        if (itemDataOverWriteVal == null) {
            this.itemDataOverWrite = true;
        } else {
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, itemDataOverWriteVal)) {
                this.itemDataOverWrite = true;
            } else {
                this.itemDataOverWrite = false;
            }
        }
    }

    private void setCoaValueFromChargeAccountTxt(NPXC001001GetDefNineSegDataBean defNineSegBean) {

        if (!ZYPCommonFunc.hasValue(defNineSegBean.getChrgAcctText())) {
            defNineSegBean.setOrigCoaProdNullFlg(true);
            defNineSegBean.setOrigCoaChNullFlg(true);
            defNineSegBean.setOrigCoaProjNullFlg(true);
            return;
        }

        String[] values = defNineSegBean.getChrgAcctText().split(Pattern.quote("."));
        if (values == null || values.length <= 0) {
            defNineSegBean.setOrigCoaProdNullFlg(true);
            defNineSegBean.setOrigCoaChNullFlg(true);
            defNineSegBean.setOrigCoaProjNullFlg(true);
            return;
        }

        StrFields coaCmpyCd = new StrFields("");
        StrFields coaBrCd = new StrFields("");
        StrFields coaCcCd = new StrFields("");
        StrFields coaAcctCd = new StrFields("");
        StrFields coaProdCd = new StrFields("");
        StrFields coaChCd = new StrFields("");
        StrFields coaAfflCd = new StrFields("");
        StrFields coaProjCd = new StrFields("");
        StrFields coaExtnCd = new StrFields("");

        StrFields[] fields = new StrFields[] {//
                coaCmpyCd, //
                coaBrCd, //
                coaCcCd, //
                coaAcctCd, //
                coaProdCd, //
                coaChCd, //
                coaAfflCd, //
                coaProjCd, //
                coaExtnCd
        };

        for (int i = 0; i < values.length; i++) {
            fields[i].setValue(values[i]);
        }

        defNineSegBean.setCoaCmpyCd(getStringValue(coaCmpyCd.getValue()));
        defNineSegBean.setCoaBrCd(getStringValue(coaBrCd.getValue()));
        defNineSegBean.setCoaCcCd(getStringValue(coaCcCd.getValue()));
        defNineSegBean.setCoaAcctCd(getStringValue(coaAcctCd.getValue()));
        defNineSegBean.setCoaProdCd(getStringValue(coaProdCd.getValue()));
        defNineSegBean.setCoaChCd(getStringValue(coaChCd.getValue()));
        defNineSegBean.setCoaAfflCd(getStringValue(coaAfflCd.getValue()));
        defNineSegBean.setCoaProjCd(getStringValue(coaProjCd.getValue()));
        defNineSegBean.setCoaExtnCd(getStringValue(coaExtnCd.getValue()));

        if (ZYPCommonFunc.hasValue(defNineSegBean.getCoaProdCd())) {
            defNineSegBean.setOrigCoaProdNullFlg(false);
        } else {
            defNineSegBean.setOrigCoaProdNullFlg(true);
        }

        if (ZYPCommonFunc.hasValue(defNineSegBean.getCoaChCd())) {
            defNineSegBean.setOrigCoaChNullFlg(false);
        } else {
            defNineSegBean.setOrigCoaChNullFlg(true);
        }

        if (ZYPCommonFunc.hasValue(defNineSegBean.getCoaProjCd())) {
            defNineSegBean.setOrigCoaProjNullFlg(false);
        } else {
            defNineSegBean.setOrigCoaProjNullFlg(true);
        }
    }

    private void setChargeAccountTxtFromCoaData(NPXC001001GetDefNineSegDataBean defNineSegBean) {

        StringBuilder chrgAcctTextBuilder = new StringBuilder("");

        chrgAcctTextBuilder.append(getStringValue(defNineSegBean.getCoaCmpyCd()));
        chrgAcctTextBuilder.append(".");
        chrgAcctTextBuilder.append(getStringValue(defNineSegBean.getCoaBrCd()));
        chrgAcctTextBuilder.append(".");
        chrgAcctTextBuilder.append(getStringValue(defNineSegBean.getCoaCcCd()));
        chrgAcctTextBuilder.append(".");
        chrgAcctTextBuilder.append(getStringValue(defNineSegBean.getCoaAcctCd()));
        chrgAcctTextBuilder.append(".");
        chrgAcctTextBuilder.append(getStringValue(defNineSegBean.getCoaProdCd()));
        chrgAcctTextBuilder.append(".");
        chrgAcctTextBuilder.append(getStringValue(defNineSegBean.getCoaChCd()));
        chrgAcctTextBuilder.append(".");
        chrgAcctTextBuilder.append(getStringValue(defNineSegBean.getCoaAfflCd()));
        chrgAcctTextBuilder.append(".");
        chrgAcctTextBuilder.append(getStringValue(defNineSegBean.getCoaProjCd()));
        chrgAcctTextBuilder.append(".");
        chrgAcctTextBuilder.append(getStringValue(defNineSegBean.getCoaExtnCd()));

        defNineSegBean.setChrgAcctText(chrgAcctTextBuilder.toString());
    }

    private String getStringValue(String prm) {

        if (ZYPCommonFunc.hasValue(prm)) {
            return prm;
        } else {
            return "";
        }
    }

    private String getValidStringValueShipFields(String origField, String shipToField) {

        if (ZYPCommonFunc.hasValue(origField) && !ZYPCommonFunc.hasValue(shipToField)) {
            return origField;
        } else if (!ZYPCommonFunc.hasValue(origField) && ZYPCommonFunc.hasValue(shipToField)) {
            return shipToField;
        } else if (ZYPCommonFunc.hasValue(origField) && ZYPCommonFunc.hasValue(shipToField)) {
            if (this.shipDataOverWrite) {
                return shipToField;
            } else {
                return origField;
            }
        } else {
            return "";
        }
    }


    private String getValidStringValueItemFields(String origField, String itemToField) {

        if (ZYPCommonFunc.hasValue(origField) && !ZYPCommonFunc.hasValue(itemToField)) {
            return origField;
        } else if (!ZYPCommonFunc.hasValue(origField) && ZYPCommonFunc.hasValue(itemToField)) {
            return itemToField;
        } else if (ZYPCommonFunc.hasValue(origField) && ZYPCommonFunc.hasValue(itemToField)) {
            if (this.itemDataOverWrite) {
                return itemToField;
            } else {
                return origField;
            }
        } else {
            return "";
        }
    }
    /** String Field Object Class */
    private static class StrFields {

        /** String value */
        private String value = null;

        protected StrFields(String value) {
            setValue(value);
        }

        protected String getValue() {
            return this.value;
        }

        protected void setValue(String value) {
            this.value = value;
        }
    }
    // 2019/02/06 QC#30181 Add End
}
