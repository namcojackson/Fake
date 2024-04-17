/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB123001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AP_INV_ROSSTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.batch.NFB.NFBB123001.constant.NFBB123001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NFBB1230:Invoice Import For CUSA ROSS
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/16/2016   CITS            Y.Nomura        Create          N/A
 * 07/14/2016   Hitachi         T.Tsuchida      Update          QC#11625
 * 09/28/2016   Hitachi         K.Kasai         Update          QC#14798
 * 11/16/2016   Hitachi         T.Tsuchida      Update          QC#16041
 *</pre>
 */
public class NFBB123001 extends S21BatchMain {
    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** Batch execute Date */
    private String executeDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** commit count */
    private int totalCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** CUSA EDI Trading Partner Code */
    private String cusaEdiTradingPartnerCd = null;

    // START 07/14/2016 T.Tsuchida [QC#11625,ADD]
    /** CUSA EDI Dummy Merchandise Code */
    private String cusaEdiDummyMdseCd = null;
    // END 07/14/2016 T.Tsuchida [QC#11625,ADD]

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        // Initialization S21BatchMain
        new NFBB123001().executeBatch(NFBB123001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            S21InfoLogOutput.println(NFBB123001Constant.NLAM1118E, new String[] {"Global Company Code" });
            throw new S21AbendException(NFBB123001Constant.NLAM1118E, new String[] {"Global Company Code" });
        }
        // check master
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        if (EZDTBLAccessor.findByKey(tMsg) == null) {
            S21InfoLogOutput.println(NFBB123001Constant.NWZM0650E);
            throw new S21AbendException(NFBB123001Constant.NWZM0650E);
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            S21InfoLogOutput.println(NFBB123001Constant.NLAM1118E, new String[] {"Sales date" });
            throw new S21AbendException(NFBB123001Constant.NLAM1118E, new String[] {"Sales date" });
        }
        executeDate = ZYPDateUtil.getBatProcDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(executeDate)) {
            S21InfoLogOutput.println(NFBB123001Constant.NLAM1118E, new String[] {"Batch process date" });
            throw new S21AbendException(NFBB123001Constant.NLAM1118E, new String[] {"Batch process date" });
        }

        cusaEdiTradingPartnerCd = ZYPCodeDataUtil.getVarCharConstValue(NFBB123001Constant.CUSA_EDI_TRD_PTNR_CD, globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(cusaEdiTradingPartnerCd)) {
            S21InfoLogOutput.println(NFBB123001Constant.NLAM1118E, new String[] {"CUSA EDI Trading Partner Code" });
            throw new S21AbendException(NFBB123001Constant.NLAM1118E, new String[] {"CUSA EDI Trading Partner Code" });
        }

        // START 07/14/2016 T.Tsuchida [QC#11625,ADD]
        cusaEdiDummyMdseCd = ZYPCodeDataUtil.getVarCharConstValue(NFBB123001Constant.CUSA_EDI_DUMMY_MDSE_CD, globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(cusaEdiDummyMdseCd)) {
            S21InfoLogOutput.println(NFBB123001Constant.NLAM1118E, new String[] {"CUSA EDI Dummy Merchandise Code" });
            throw new S21AbendException(NFBB123001Constant.NLAM1118E, new String[] {"CUSA EDI Dummy Merchandise Code" });
        }
        // END 07/14/2016 T.Tsuchida [QC#11625,ADD]
    }

    @Override
    protected void mainRoutine() {
        ArrayList<String> cusaEdiTrdPtnrCdList = new ArrayList<String>();
        String[] arrayEdiTrdPtnrCd = cusaEdiTradingPartnerCd.split(",", 0);
        for (int i = 0; i < arrayEdiTrdPtnrCd.length; i++) {
            cusaEdiTrdPtnrCdList.add(arrayEdiTrdPtnrCd[i]);
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NFBB123001Constant.DB_PARAM_GLBL_CMPY_CD_CUSA, GLBL_CMPY.CANON_USA_INC);
            paramMap.put(NFBB123001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NFBB123001Constant.DB_PARAM_SALES_DATE, salesDate);
            paramMap.put(NFBB123001Constant.DB_PARAM_RTL_INV_APVL_DT, executeDate);
            paramMap.put(NFBB123001Constant.DB_PARAM_EDI_CUST_TP_CD_SELL_TO, NFBB123001Constant.EDI_CUST_TP_CD_SELL_TO);
            paramMap.put(NFBB123001Constant.DB_PARAM_EDI_CUST_TP_CD_SHIP_TO, NFBB123001Constant.EDI_CUST_TP_CD_SHIP_TO);
            paramMap.put(NFBB123001Constant.DB_PARAM_ACTV_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(NFBB123001Constant.DB_PARAM_EDI_TRD_PTNR_CD, cusaEdiTrdPtnrCdList);
            paramMap.put(NFBB123001Constant.DB_PARAM_EFF_FROM_DT, NFBB123001Constant.DEFAULT_EFF_FROM_DT);
            paramMap.put(NFBB123001Constant.DB_PARAM_EFF_THRU_DT, NFBB123001Constant.DEFAULT_EFF_THRU_DT);
            // START 07/14/2016 T.Tsuchida [QC#11625,ADD]
            paramMap.put(NFBB123001Constant.DB_PARAM_DUMMY_MDSE_CD, cusaEdiDummyMdseCd);
            // END 07/14/2016 T.Tsuchida [QC#11625,ADD]

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            preparedStatement = ssmLlcClient.createPreparedStatement("getInvoiceData", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AP_INV_ROSSTMsg tMsg = new AP_INV_ROSSTMsg();

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
                // RTL_INV_PK
                ZYPEZDItemValueSetter.setValue(tMsg.rtlInvPk, resultSet.getBigDecimal(NFBB123001Constant.RTL_INV_PK));
                // RTL_INV_LINE_PK
                ZYPEZDItemValueSetter.setValue(tMsg.rtlInvLinePk, resultSet.getBigDecimal(NFBB123001Constant.RTL_INV_LINE_PK));
                // RTL_INV_STS_CD
                ZYPEZDItemValueSetter.setValue(tMsg.rtlInvStsCd, resultSet.getString(NFBB123001Constant.RTL_INV_STS_CD));
                // BILL_TO_CUST_CD
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, resultSet.getString(NFBB123001Constant.BILL_TO_CUST_CD));
                // SELL_TO_CUST_CD
                ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, resultSet.getString(NFBB123001Constant.SELL_TO_CUST_CD));
                // SER_NUM
                ZYPEZDItemValueSetter.setValue(tMsg.serNum, resultSet.getString(NFBB123001Constant.SER_NUM));
                // MDL_NM
                ZYPEZDItemValueSetter.setValue(tMsg.mdlNm, resultSet.getString(NFBB123001Constant.MDL_NM));
                // RTL_INV_APVL_DT
                ZYPEZDItemValueSetter.setValue(tMsg.rtlInvApvlDt, resultSet.getString(NFBB123001Constant.RTL_INV_APVL_DT));
                // BLLG_PER_FROM_DT
                ZYPEZDItemValueSetter.setValue(tMsg.bllgPerFromDt, resultSet.getString(NFBB123001Constant.BLLG_PER_FROM_DT));
                // BLLG_PER_THRU_DT
                ZYPEZDItemValueSetter.setValue(tMsg.bllgPerThruDt, resultSet.getString(NFBB123001Constant.BLLG_PER_THRU_DT));
                // RTL_INV_LINE_NUM
                ZYPEZDItemValueSetter.setValue(tMsg.rtlInvLineNum, resultSet.getString(NFBB123001Constant.RTL_INV_LINE_NUM));
                // RTL_INV_CHRG_TP_DESC_TXT
                ZYPEZDItemValueSetter.setValue(tMsg.rtlInvChrgTpDescTxt, resultSet.getString(NFBB123001Constant.RTL_INV_CHRG_TP_DESC_TXT));
                // SHIP_QTY
                ZYPEZDItemValueSetter.setValue(tMsg.shipQty, resultSet.getBigDecimal(NFBB123001Constant.SHIP_QTY));
                // DEAL_GRS_UNIT_PRC_AMT
                ZYPEZDItemValueSetter.setValue(tMsg.dealGrsUnitPrcAmt, resultSet.getBigDecimal(NFBB123001Constant.DEAL_GRS_UNIT_PRC_AMT));
                // SLS_TAX_RATE
                ZYPEZDItemValueSetter.setValue(tMsg.slsTaxRate, resultSet.getBigDecimal(NFBB123001Constant.SLS_TAX_RATE));
                // BLLG_BIZ_TP_CD
                ZYPEZDItemValueSetter.setValue(tMsg.bllgBizTpCd, resultSet.getString(NFBB123001Constant.BLLG_BIZ_TP_CD));
                // RTL_DIV_CD
                ZYPEZDItemValueSetter.setValue(tMsg.rtlDivCd, resultSet.getString(NFBB123001Constant.RTL_DIV_CD));
                // RTL_INV_NUM
                ZYPEZDItemValueSetter.setValue(tMsg.rtlInvNum, resultSet.getString(NFBB123001Constant.RTL_INV_NUM));
                // RTL_SMRY_INV_NUM
                // START 2016/11/16 T.Tsuchida [QC#16041,MOD]
                //ZYPEZDItemValueSetter.setValue(tMsg.rtlSmryInvNum, resultSet.getString(NFBB123001Constant.ITRL_RTL_SMRY_INV_NUM));
                ZYPEZDItemValueSetter.setValue(tMsg.itrlRtlSmryInvNum, resultSet.getString(NFBB123001Constant.ITRL_RTL_SMRY_INV_NUM));
                // END 2016/11/16 T.Tsuchida [QC#16041,MOD]
                // MDSE_CD
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, resultSet.getString(NFBB123001Constant.MDSE_CD));
                // SVC_DLR_CD
                ZYPEZDItemValueSetter.setValue(tMsg.svcDlrCd, resultSet.getString(NFBB123001Constant.SVC_DLR_CD));
                // INSTL_POST_CD
                ZYPEZDItemValueSetter.setValue(tMsg.instlPostCd, resultSet.getString(NFBB123001Constant.INSTL_POST_CD));
                // INSTL_CD
                ZYPEZDItemValueSetter.setValue(tMsg.instlCd, resultSet.getString(NFBB123001Constant.INSTL_CD));
                // ISTL_SUB_LOC_CD
                ZYPEZDItemValueSetter.setValue(tMsg.istlSubLocCd, resultSet.getString(NFBB123001Constant.ISTL_SUB_LOC_CD));
                // INV_LINE_CRAT_DT
                ZYPEZDItemValueSetter.setValue(tMsg.invLineCratDt, resultSet.getString(NFBB123001Constant.INV_LINE_CRAT_DT));
                // INV_LINE_MOD_DT
                ZYPEZDItemValueSetter.setValue(tMsg.invLineModDt, resultSet.getString(NFBB123001Constant.INV_LINE_MOD_DT));
                // AP_INV_ROSS_STS_CD
                ZYPEZDItemValueSetter.setValue(tMsg.apInvRossStsCd, NFBB123001Constant.ROSS_STS_CD_NORMAL);
                // START 2016/09/29 [QC#14798, ADD]
                // RTL_INV_CHRG_TP_CD
                ZYPEZDItemValueSetter.setValue(tMsg.rtlInvChrgTpCd, resultSet.getString(NFBB123001Constant.RTL_INV_CHRG_TP_CD));
                // END 2016/09/29 [QC#14798, ADD]

                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    S21InfoLogOutput.println(String.format("DB error . RTL_INV_PK='%f' RTL_INV_LINE_PK='%f'", resultSet.getBigDecimal(NFBB123001Constant.RTL_INV_PK), resultSet.getBigDecimal(NFBB123001Constant.RTL_INV_LINE_PK)));
                    rollback();
                    throw new S21AbendException(NFBB123001Constant.NFDM0013E, new String[] {"AP_INV_ROSS" });
                }
                totalCount++;
            }
            commit();
        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, totalCount, 0, totalCount);
    }
}
