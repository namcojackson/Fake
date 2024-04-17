/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFB.NFBC000102;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.parts.NFBC000102PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Planned Standard Price Calculation
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/04/18   Fujitsu         A.Shinohara     Update          WDS Downsizing #1
 *</pre>
 */

public class NFBC000102 extends S21ApiCommonBase {

    /** Input */
    /** For Merchandise And Parts */
    /** Merchandise or Parts indicate Code */
    private String mdseOrPrtCd;

    /** Merchandise Code */
    private String mdseCd;

    /** Invoice Qty */
    private BigDecimal invQty;

    /** For Sales Promotion */
    /** Sales Promotion flag */
    private String xxSlsPrmoFlg;

    private BigDecimal vndScFobCostAmt;

    private String acctArthTpCd;

    private BigDecimal stdExchRate;

    /** For Merchandise */
    /** Import/Domestic Vendor Code(I = Import, D = Domestic) */
    private String xxIntlDomCd;

    /** For Parts */
    /** Vendor Code */
    // private String vndCd;

    /** Product Line Control Code */
    private String prodLineProdCtrlCd;

    /** Output */
    /** Plan Standard Amount: FOB */
    private BigDecimal bdPlnFobStdAmt;

    /** Plan Standard Amount: Freigt */
    private BigDecimal bdPlnFrtStdAmt;

    /** Plan Standard Amount: Duty */
    private BigDecimal bdPlnDtyStdAmt;

    /** Plan Standard Amount: Insuranse */
    private BigDecimal bdPlnInsStdAmt;

    /** Plan Standard Amount: Other */
    private BigDecimal bdPlnOthStdAmt;

    /** Sales Promotion Standard Amount: FOB */
    private BigDecimal bdSlsPrmoFobStdAmt;

    /** Sales Promotion Standard Amount: Freigt */
    private BigDecimal bdSlsPrmoFrtStdAmt;

    /** Sales Promotion Standard Amount: Duty */
    private BigDecimal bdSlsPrmoDtyStdAmt;

    /** Sales Promotion Standard Amount: Insuranse */
    private BigDecimal bdSlsPrmoInsStdAmt;

    /** Sales Promotion Standard Amount: Other */
    private BigDecimal bdSlsPrmoOthStdAmt;

    /** Global Company Code */
    private String glblCmpyCd;

    /**
     * Temporary FOB, for calcurating Standard price without Parts
     * Master
     */
    static final BigDecimal bdTmpFobPrt = new BigDecimal(1);

    /** Temporary FOB, for calcurating Standard price without Mdse */
    static final BigDecimal bdTmpFobMdse = new BigDecimal(1);

    /** fixed rate for calcurating freight charge */
    static final BigDecimal db100 = new BigDecimal(100);

    static final String getMdse = "getMdse";

    static final String getMdseChrgDefRate = "getMdseChrgDefRate";

    /** Mdse/Parts indicator */
    static final String strMdseInd = "M";

    // static final String strPrtInd = "P";

    /** Arithmetic Type Code */
    static final String strMultiply = "*";

    /** Sales Promotion Flag */
    static final String strSlsPrmoY = ZYPConstant.FLG_ON_Y;

    /** Mdse/Parts indicator */
    static final String strDomesticCd = "D";

    static final String strImportCd = "I";

    static final String strDomesticNum = "0";

    static final String strImportNum = "1";

    /** Standard Currency Code */
    static final String standardCcyCd = "USD";

    /** Scale unit */
    static final int intScale = 2;

    /** SQL Access object */
    private S21SsmBatchClient ssmBatchClient = null;

    /** User Profile */
    private S21UserProfileService profile;

    /** Tracking fee calculation for Merchandise Data */
    private BigDecimal bdMdseFobAmt = new BigDecimal(0.0000); // FOB
                                                                // Amount
                                                                // from
                                                                // Merchandsie

    private BigDecimal bdMdseFrtAmt = new BigDecimal(0.0000); // Freight
                                                                // Amount
                                                                // from
                                                                // Merchandsie

    private BigDecimal bdMdseInsAmt = new BigDecimal(0.0000); // Insurance
                                                                // Amount
                                                                // from
                                                                // Merchandsie

    private BigDecimal bdMdseDtyAmt = new BigDecimal(0.0000); // Duty
                                                                // Amount
                                                                // from
                                                                // Merchandsie

    private BigDecimal bdMdseOthAmt = new BigDecimal(0.0000); // Other
                                                                // Amount
                                                                // from
                                                                // Merchandsie

    private BigDecimal bdMdseChrgDefRtFrtRt = new BigDecimal(0.0000); // Freight
                                                                        // Rate
                                                                        // from
                                                                        // Merchandsie
                                                                        // Charg
                                                                        // Definition
                                                                        // Rate

    private BigDecimal bdMdseChrgDefRtInsRt = new BigDecimal(0.0000); // Insurance
                                                                        // Rate
                                                                        // from
                                                                        // Merchandsie
                                                                        // Charg
                                                                        // Definition
                                                                        // Rate

    private BigDecimal bdMdseChrgDefRtDtyRt = new BigDecimal(0.0000); // Duty
                                                                        // Rate
                                                                        // from
                                                                        // Merchandsie
                                                                        // Charg
                                                                        // Definition
                                                                        // Rate

    private BigDecimal bdMdseChrgDefRtOthRt = new BigDecimal(0.0000); // Other
                                                                        // Rate
                                                                        // from
                                                                        // Merchandsie
                                                                        // Charg
                                                                        // Definition
                                                                        // Rate


    /**
     * Initializaion
     */
    public NFBC000102() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        mdseOrPrtCd = null;
        // vndCd = null;
        mdseCd = null;
        xxSlsPrmoFlg = null;
        prodLineProdCtrlCd = null;
        xxIntlDomCd = null;
        vndScFobCostAmt = new BigDecimal(0.00);
        invQty = new BigDecimal(0);

        /** Retreive Global Company Code */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = this.profile.getGlobalCompanyCode();
    }

    /**
     * Method name: Execute
     * <dd>The method explanation: Calculate Planned Standard Amount
     * for MDSE/Parts.
     * <dd>Remarks:
     */
    public boolean execute(final NFBC000102PMsg param, final ONBATCH_TYPE onBatchType) {

        // initialized value
        Boolean bRet = Boolean.TRUE;
        bdPlnFobStdAmt = new BigDecimal(0.00);
        bdPlnFrtStdAmt = new BigDecimal(0.00);
        bdPlnDtyStdAmt = new BigDecimal(0.00);
        bdPlnInsStdAmt = new BigDecimal(0.00);
        bdPlnOthStdAmt = new BigDecimal(0.00);
        bdSlsPrmoFobStdAmt = new BigDecimal(0.00);
        bdSlsPrmoFrtStdAmt = new BigDecimal(0.00);
        bdSlsPrmoDtyStdAmt = new BigDecimal(0.00);
        bdSlsPrmoInsStdAmt = new BigDecimal(0.00);
        bdSlsPrmoOthStdAmt = new BigDecimal(0.00);

        // Set Input Value into Global variables
        mdseOrPrtCd = param.mdseOrPrtCd.getValue();
        mdseCd = param.mdseCd.getValue();
        // vndCd = param.vndCd.getValue();
        prodLineProdCtrlCd = param.prodLineProdCtrlCd.getValue();
        xxSlsPrmoFlg = param.xxSlsPrmoFlg.getValue();
        xxIntlDomCd = param.xxIntlDomCd.getValue();
        if (param.vndScFobCostAmt.isClear()) {
            vndScFobCostAmt = new BigDecimal(0.00);
        } else {
            vndScFobCostAmt = param.vndScFobCostAmt.getValue();
        }
        if (param.invQty.isClear()) {
            invQty = new BigDecimal(0);
        } else {
            invQty = param.invQty.getValue();
        }
        acctArthTpCd = param.acctArthTpCd.getValue();
        stdExchRate = param.stdExchRate.getValue();

        // In case of this record is for Sales Promotion, Mdse/Parts
        // standard price would be caluculated by another logic.
        if (xxSlsPrmoFlg.equals(strSlsPrmoY)) {

            bRet = calcStdPrcForSalesPrmo();

        } else {

            // In case of this record is not for Sales Promotion
            bRet = calcStdPrc();
        }

        // Set Output Value
        param.plnScFobCostAmt.setValue(bdPlnFobStdAmt);
        param.plnScFrtCostAmt.setValue(bdPlnFrtStdAmt);
        param.plnScInsCostAmt.setValue(bdPlnInsStdAmt);
        param.plnScDtyCostAmt.setValue(bdPlnDtyStdAmt);
        param.plnScOthCostAmt.setValue(bdPlnOthStdAmt);
        param.slsPrmoScFobCostAmt.setValue(bdSlsPrmoFobStdAmt);
        param.slsPrmoScFrtCostAmt.setValue(bdSlsPrmoFrtStdAmt);
        param.slsPrmoScInsCostAmt.setValue(bdSlsPrmoInsStdAmt);
        param.slsPrmoScDtyCostAmt.setValue(bdSlsPrmoDtyStdAmt);
        param.slsPrmoScOthCostAmt.setValue(bdSlsPrmoOthStdAmt);

        return bRet;

    }

    /*
     * -------------------------------------------------------------------------------------------------------------------------------------
     * -------------------------------------------------------------------------------------------------------------------------------------
     * -------------------------------------------------------------------------------------------------------------------------------------
     * Caluculation for Standard Cost
     * -------------------------------------------------------------------------------------------------------------------------------------
     * -------------------------------------------------------------------------------------------------------------------------------------
     * -------------------------------------------------------------------------------------------------------------------------------------
     */
    private Boolean calcStdPrc() {

        // retrun value
        Boolean bRet = true;

        if (mdseOrPrtCd.equals(strMdseInd)) {
            /*
             * -------------------------------------------------------------------------------------------------------------------------------------
             * Maerchandise Data Caluculation
             * -------------------------------------------------------------------------------------------------------------------------------------
             */

            // Retrieving Standard price from Merchandise
            Map<String, String> queryParamMdse = new HashMap<String, String>();
            // Set bind value
            queryParamMdse.put("glblCmpyCd", glblCmpyCd);
            queryParamMdse.put("mdseCd", mdseCd);

            // Retrieving Merchandise Master
            Boolean bRet1 = (Boolean) this.ssmBatchClient.queryObject(getMdse, queryParamMdse, new getMdse());

            if (bRet1 == Boolean.TRUE) {

                // ------------------------------------------------------------------------------------------------------------------------------------
                // Merchandise Claculate Standard Cost
                // Case-1
                // Mechandise : Y
                // Mechandise Charge definition Rate : unknown
                // ------------------------------------------------------------------------------------------------------------------------------------
                bdPlnFobStdAmt = bdMdseFobAmt.multiply(invQty);
                bdPlnFrtStdAmt = bdMdseFrtAmt.multiply(invQty);
                bdPlnDtyStdAmt = bdMdseDtyAmt.multiply(invQty);
                bdPlnInsStdAmt = bdMdseInsAmt.multiply(invQty);
                bdPlnOthStdAmt = bdMdseOthAmt.multiply(invQty);
                bRet = Boolean.TRUE;

            } else {

                // Retrieving Merchandise Charge Definition Rate

                Map<String, String> queryParamMdseChrgDefRate = new HashMap<String, String>();
                // Set bind value
                queryParamMdseChrgDefRate.put("glblCmpyCd", glblCmpyCd);
                queryParamMdseChrgDefRate.put("mdseChrgDefRateCd", prodLineProdCtrlCd + xxIntlDomCd.replace(strDomesticCd, strDomesticNum).replace(strImportCd, strImportNum));
                Boolean bRet2 = (Boolean) this.ssmBatchClient.queryObject(getMdseChrgDefRate, queryParamMdseChrgDefRate, new getMdseChrgDefRate());
                if (bRet2 == Boolean.TRUE) {

                    // ------------------------------------------------------------------------------------------------------------------------------------
                    // Merchandise Claculate Standard Cost
                    // Case-2
                    // Mechandise : N
                    // Mechandise Charge definition Rate : Y
                    // ------------------------------------------------------------------------------------------------------------------------------------
                    bdPlnFobStdAmt = vndScFobCostAmt;
                    bdPlnFrtStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtFrtRt).divide(db100, intScale, BigDecimal.ROUND_HALF_UP);
                    bdPlnDtyStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtDtyRt).divide(db100, intScale, BigDecimal.ROUND_HALF_UP);
                    bdPlnInsStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtInsRt).divide(db100, intScale, BigDecimal.ROUND_HALF_UP);
                    bdPlnOthStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtOthRt).divide(db100, intScale, BigDecimal.ROUND_HALF_UP);
                    bRet = Boolean.TRUE;
                } else {
                    // ------------------------------------------------------------------------------------------------------------------------------------
                    // Merchandise Claculate Standard Cost
                    // Case-3
                    // Mechandise : N
                    // Mechandise Charge definition Rate : N
                    // ------------------------------------------------------------------------------------------------------------------------------------

                    // all values are set zero up.
                    bRet = Boolean.FALSE;
                }

            }

        } else {
            /*
             * -------------------------------------------------------------------------------------------------------------------------------------
             * No action if mdseOrPrtCd <> 'M'(Merchandise) or
             * 'P'(Parts)
             * -------------------------------------------------------------------------------------------------------------------------------------
             */
            // all values are set zero up.
            bRet = Boolean.FALSE;
        }

        return bRet;
    }

    /*
     * -------------------------------------------------------------------------------------------------------------------------------------
     * -------------------------------------------------------------------------------------------------------------------------------------
     * -------------------------------------------------------------------------------------------------------------------------------------
     * Caluculation for Sales Promotion
     * -------------------------------------------------------------------------------------------------------------------------------------
     * -------------------------------------------------------------------------------------------------------------------------------------
     * -------------------------------------------------------------------------------------------------------------------------------------
     */
    private Boolean calcStdPrcForSalesPrmo() {

        // retrun value
        Boolean bRet = true;

        if (mdseOrPrtCd.equals(strMdseInd)) {
            /*
             * -------------------------------------------------------------------------------------------------------------------------------------
             * Maerchandise Data Caluculation
             * -------------------------------------------------------------------------------------------------------------------------------------
             */

            // Retrieving Merchandise Charge Definition Rate
            Map<String, String> queryParamMdseChrgDefRate = new HashMap<String, String>();
            // Set bind value
            queryParamMdseChrgDefRate.put("glblCmpyCd", glblCmpyCd);
            queryParamMdseChrgDefRate.put("mdseChrgDefRateCd", prodLineProdCtrlCd + xxIntlDomCd.replace(strDomesticCd, strDomesticNum).replace(strImportCd, strImportNum));
            Boolean bRet1 = (Boolean) this.ssmBatchClient.queryObject(getMdseChrgDefRate, queryParamMdseChrgDefRate, new getMdseChrgDefRate());
            if (bRet1 == Boolean.TRUE) {

                // ------------------------------------------------------------------------------------------------------------------------------------
                // Merchandise Claculate Standard Cost
                // Case-1
                // Mechandise Charge definition Rate : Y
                // ------------------------------------------------------------------------------------------------------------------------------------

                if (strMultiply.equals(acctArthTpCd)) {
                    /*
                     * -------------------------------------------------------------------------------------------------------------------------------------
                     * Multiply Standard Rate
                     * -------------------------------------------------------------------------------------------------------------------------------------
                     */

                    // Plan FOB = Round(@FOB * Exchange Rate, 2) * Qty
                    bdPlnFobStdAmt = vndScFobCostAmt.multiply(stdExchRate).setScale(intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);
                    // Plan FRT = Round(@FRT * Exchange Rate /100, 2)
                    // * Qty
                    bdPlnFrtStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtFrtRt).multiply(stdExchRate).divide(db100, intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);
                    // Plan INS = Round(@INS * Exchange Rate /100, 2)
                    // * Qty
                    bdPlnInsStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtInsRt).multiply(stdExchRate).divide(db100, intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);
                    // Plan DTY = Round(@DTY * Exchange Rate /100, 2)
                    // * Qty
                    bdPlnDtyStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtDtyRt).multiply(stdExchRate).divide(db100, intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);
                    // Plan OTH = Round(@OTH * Exchange Rate /100, 2)
                    // * Qty
                    bdPlnOthStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtOthRt).multiply(stdExchRate).divide(db100, intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);

                    // SP FOB = Round(@FOB * Exchange Rate * Qty, 2)
                    bdSlsPrmoFobStdAmt = vndScFobCostAmt.multiply(stdExchRate).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                    // SP FRT = Round(@FRT * Exchange Rate /100 * Qty,
                    // 2)
                    bdSlsPrmoFrtStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtFrtRt).multiply(stdExchRate).divide(db100).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                    // SP INS = Round(@INS * Exchange Rate /100 * Qty,
                    // 2)
                    bdSlsPrmoInsStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtInsRt).multiply(stdExchRate).divide(db100).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                    // SP DTY = Round(@DTY * Exchange Rate /100 * Qty,
                    // 2)
                    bdSlsPrmoDtyStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtDtyRt).multiply(stdExchRate).divide(db100).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                    // SP OTH = Round(@OTH * Exchange Rate /100 * Qty,
                    // 2)
                    bdSlsPrmoOthStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtOthRt).multiply(stdExchRate).divide(db100).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                } else {
                    /*
                     * -------------------------------------------------------------------------------------------------------------------------------------
                     * Divide Standard Rate
                     * -------------------------------------------------------------------------------------------------------------------------------------
                     */

                    // Plan FOB = Round(@FOB / Exchange Rate, 2) * Qty
                    bdPlnFobStdAmt = vndScFobCostAmt.divide(stdExchRate, intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);
                    // Plan FRT = Round(@FRT / Exchange Rate /100, 2)
                    // * Qty
                    bdPlnFrtStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtFrtRt).divide(stdExchRate).divide(db100, intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);
                    // Plan INS = Round(@INS / Exchange Rate /100, 2)
                    // * Qty
                    bdPlnInsStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtInsRt).divide(stdExchRate).divide(db100, intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);
                    // Plan DTY = Round(@DTY / Exchange Rate /100, 2)
                    // * Qty
                    bdPlnDtyStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtDtyRt).divide(stdExchRate).divide(db100, intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);
                    // Plan OTH = Round(@OTH / Exchange Rate /100, 2)
                    // * Qty
                    bdPlnOthStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtOthRt).divide(stdExchRate).divide(db100, intScale, BigDecimal.ROUND_HALF_UP).multiply(invQty);

                    // SP FOB = Round(@FOB / Exchange Rate * Qty, 2)
                    bdSlsPrmoFobStdAmt = vndScFobCostAmt.divide(stdExchRate).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                    // SP FRT = Round(@FRT / Exchange Rate /100 * Qty,
                    // 2)
                    bdSlsPrmoFrtStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtFrtRt).divide(stdExchRate).divide(db100).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                    // SP INS = Round(@INS / Exchange Rate /100 * Qty,
                    // 2)
                    bdSlsPrmoInsStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtInsRt).divide(stdExchRate).divide(db100).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                    // SP DTY = Round(@DTY / Exchange Rate /100 * Qty,
                    // 2)
                    bdSlsPrmoDtyStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtDtyRt).divide(stdExchRate).divide(db100).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                    // SP OTH = Round(@OTH / Exchange Rate /100 * Qty,
                    // 2)
                    bdSlsPrmoOthStdAmt = vndScFobCostAmt.multiply(bdMdseChrgDefRtOthRt).divide(stdExchRate).divide(db100).multiply(invQty).setScale(intScale, BigDecimal.ROUND_HALF_UP);
                }

                bRet = Boolean.TRUE;
            } else {
                // ------------------------------------------------------------------------------------------------------------------------------------
                // Merchandise Claculate Standard Cost
                // Case-2
                // Mechandise Charge definition Rate : N
                // ------------------------------------------------------------------------------------------------------------------------------------

                // all values are set zero up.
                bRet = Boolean.FALSE;
            }

        } else {
            /*
             * s
             * -------------------------------------------------------------------------------------------------------------------------------------
             * No action if mdseOrPrtCd <> 'M'(Merchandise) or
             * 'P'(Parts)
             * -------------------------------------------------------------------------------------------------------------------------------------
             */

            // all values are set zero up.
            bRet = Boolean.FALSE;
        }
        return bRet;
    }

    /**
     * Method name: getMdse
     * <dd>The method explanation: Calculate Planned Standard Amount
     * for Merchandise.
     * <dd>Remarks:
     */
    protected class getMdse extends S21SsmBooleanResultSetHandlerSupport {

        public Boolean doProcessQueryResult(ResultSet rsMdse) throws SQLException {
            // retrun value
            Boolean bRet = Boolean.TRUE;

            try {

                // Retrieve Mdse Data
                if (rsMdse.next()) {

                    if (!ZYPCommonFunc.hasValue(rsMdse.getBigDecimal("THIS_MTH_FOB_AMT"))) {
                        bdMdseFobAmt = new BigDecimal(0.00);
                    } else {
                        bdMdseFobAmt = rsMdse.getBigDecimal("THIS_MTH_FOB_AMT");
                    }

                    if (!ZYPCommonFunc.hasValue(rsMdse.getBigDecimal("THIS_MTH_INTL_FRT_AMT"))) {
                        bdMdseFrtAmt = new BigDecimal(0.00);
                    } else {
                        bdMdseFrtAmt = rsMdse.getBigDecimal("THIS_MTH_INTL_FRT_AMT");
                    }

                    if (!ZYPCommonFunc.hasValue(rsMdse.getBigDecimal("THIS_MTH_IMPT_DTY_AMT"))) {
                        bdMdseDtyAmt = new BigDecimal(0.00);
                    } else {
                        bdMdseDtyAmt = rsMdse.getBigDecimal("THIS_MTH_IMPT_DTY_AMT");
                    }

                    if (!ZYPCommonFunc.hasValue(rsMdse.getBigDecimal("THIS_MTH_INS_AMT"))) {
                        bdMdseInsAmt = new BigDecimal(0.00);
                    } else {
                        bdMdseInsAmt = rsMdse.getBigDecimal("THIS_MTH_INS_AMT");
                    }

                    if (!ZYPCommonFunc.hasValue(rsMdse.getBigDecimal("THIS_MTH_INLND_FRT_AMT"))) {
                        bdMdseOthAmt = new BigDecimal(0.00);
                    } else {
                        bdMdseOthAmt = rsMdse.getBigDecimal("THIS_MTH_INLND_FRT_AMT");
                    }

                    bRet = Boolean.TRUE;
                } else {
                    bRet = Boolean.FALSE;
                }

            } catch (SQLException e) {
                throw new S21AbendException(e);
            } finally {
                // None;
            }

            return bRet;
        }

    }

    /**
     * Method name: getMdseChrgDefRate
     * <dd>The method explanation: Calculate Planned Standard Amount
     * for Merchandise.
     * <dd>Remarks:
     */
    protected class getMdseChrgDefRate extends S21SsmBooleanResultSetHandlerSupport {

        public Boolean doProcessQueryResult(ResultSet rsMdseChrgDefRate) throws SQLException {
            // retrun value
            Boolean bRet = Boolean.TRUE;

            try {
                // RetrieveMdse Charg Def Rate Data
                if (rsMdseChrgDefRate.next()) {
                    bdMdseChrgDefRtFrtRt = rsMdseChrgDefRate.getBigDecimal("INTL_FRT_RATE");
                    bdMdseChrgDefRtDtyRt = rsMdseChrgDefRate.getBigDecimal("DTY_RATE");
                    bdMdseChrgDefRtInsRt = rsMdseChrgDefRate.getBigDecimal("INS_RATE");
                    bdMdseChrgDefRtOthRt = rsMdseChrgDefRate.getBigDecimal("INLND_FRT_RATE");

                    bRet = Boolean.TRUE;
                } else {
                    bRet = Boolean.FALSE;
                }
            } catch (SQLException e) {
                throw new S21AbendException(e);
            } finally {
                // None;
            }

            return bRet;
        }

    }

}
