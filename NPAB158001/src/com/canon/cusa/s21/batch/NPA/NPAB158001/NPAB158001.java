/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB158001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PRCH_REQ_INTFCTMsg;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.batch.NPA.NPAB158001.constant.NPAB158001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_OWNR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NPAB158001:Create PR IF from CPO
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   CITS            T.Kikuhara      Create          N/A
 * 2016/03/22   CSAI            K.Lee           Update          QC#5836
 * 2016/03/24   CSAI            K.Lee           Update          QC#5952
 * 2016/03/25   CSAI            K.Lee           Update          QC#6016
 * 2016/05/18   CITS            Hisashi         Update          QC#4296
 * 2016/06/30   CITS            T.Kikuhara      Update          QC#10919
 * 2016/11/09   CITS            K.Ogino         Update          QC#15749,QC#15768
 * 01/27/2017   CITS            K.Ogino         Update          QC#11314
 * 07/25/2017   CITS            T.Tokutomi      Update          QC#18403
 * 08/08/2017   CITS            S.Endo          Update          Sol#035(QC#18108)
 * 08/30/2017   CITS            R.Shimamoto     Update          QC#18403-1
 * 09/22/2017   CITS            R.Shimamoto     Update          QC#21006
 * 10/17/2016   CITS            S.Katsuma       Update          Sol#454
 * 12/22/2017   CITS            K.Ogino         Update          QC#22687
 * 07/31/2017   CITS            K.Ogino         Update          QC#27535
 * 11/13/2018   CITS            K.Ogino         Update          QC#29208
 * 11/15/2018   CITS            K.Ogino         Update          QC#29255
 * 12/17/2018   CITS            K.Ogino         Update          QC#29177
 * 07/30/2019   CITS            K.Ogino         Update          QC#52146
 * 08/07/2019   Fujitsu         S.Ohki          Update          QC#52310
 * 2023/07/28   Hitachi         R.Takau         Update          QC#60676
 *</pre>
 */
public class NPAB158001 extends S21BatchMain {

    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** DS Order Line Category Type Code List */
    ArrayList<String> dsOrdLineCatgTpList = new ArrayList<String>();

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** commit count */
    private int commitCount = 0;

    /** total commit count */
    private int totalCommitCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** rossVndCds */
    private String[] rossVndCds = null;

    /** rossPrntVndCd */
    private String rossPrntVndCd = null;

    // QC#27535
    /** Manual Dropship WH */
    private String[] mdWhCds = null;
    // QC#27535
    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    // QC#29255
    /** Order Line Status Code */
    private String[] ordLineStsCds = null;

    @Override
    protected void initRoutine() {

        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAB158001Constant.NPAM0078E, new String[] {NPAB158001Constant.GLBL_CMPY_CD });
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAB158001Constant.NPAM0078E, new String[] {NPAB158001Constant.SALES_DATE });
        }

        // QC#27535
        String mdConstVal = ZYPCodeDataUtil.getVarCharConstValue(NPAB158001Constant.MANUAL_DROPSHIP_WAREHOUSE_CD, this.globalCompanyCode);
        if (ZYPCommonFunc.hasValue(mdConstVal)) {
            this.mdWhCds = mdConstVal.split(",");
        }

        // rossVndCds
        String constValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB158001Constant.VAR_CHAR_CONST_ROSS_VND_CD, this.globalCompanyCode);
        if (ZYPCommonFunc.hasValue(constValue)) {
               rossVndCds = constValue.split(",");

               // get ROSS Parent vender code
               PreparedStatement preparedStatement = null;
               ResultSet resultSet = null;

               try {
                   S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
                   HashMap<String, Object> paramMap = new HashMap<String, Object>();
                   paramMap.put(NPAB158001Constant.GLBL_CMPY_CD, globalCompanyCode);
                   paramMap.put(NPAB158001Constant.DB_PARAM_SLS_DT, salesDate);
                   paramMap.put(NPAB158001Constant.DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
                   paramMap.put(NPAB158001Constant.DB_PARAM_ROSS_VND_CD, rossVndCds[0]);

                   S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
                   execParam.setFetchSize(FETCH_SIZE);
                   execParam.setMaxRows(0);
                   preparedStatement = ssmLlcClient.createPreparedStatement(NPAB158001Constant.GET_PRNT_VND_CD, paramMap, execParam);
                   resultSet = preparedStatement.executeQuery();

                   if (resultSet.next()) {
                       rossPrntVndCd = resultSet.getString(NPAB158001Constant.PRNT_VND_CD);
                   }

               } catch (SQLException e) {
                   sqlExceptionHandler(e);
               } finally {
                   S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
               }
        }

        // QC#29255. External Order Line Condition
        String constOrdLineStsCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB158001Constant.NPAB1580_ORD_LINE_DPLY_STS_CD, this.globalCompanyCode);
        if (ZYPCommonFunc.hasValue(constOrdLineStsCd)) {
            this.ordLineStsCds = constOrdLineStsCd.split(",");
        } else {
            this.ordLineStsCds = new String[]{ORD_LINE_DPLY_STS.PENDING_ALLOCATION, ORD_LINE_DPLY_STS.BACK_ORDER, ORD_LINE_DPLY_STS.PARTIALLY_ALLOCATED};
        }

        // get ROSS Order Line Category Code
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB158001Constant.GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB158001Constant.ORD_LINE_CTX_TP_CD, ZYPCodeDataUtil.getVarCharConstValue(NPAB158001Constant.NPAB1580_ROSS_ORD_CTX_TP, globalCompanyCode));

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement(NPAB158001Constant.GET_DS_ORDER_LINE_CATEGORY_TYPE, paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                dsOrdLineCatgTpList.add(resultSet.getString(NPAB158001Constant.DS_ORD_LINE_CATG_CD));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Map<String, Object>> cpoOrdNumList = new ArrayList<Map<String, Object>>();
        Map<String, Object> cpoOrdNumMap = new HashMap<String, Object>();

        try {
            // get Order Info
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB158001Constant.GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB158001Constant.FLGONY, ZYPConstant.FLG_ON_Y);
            paramMap.put(NPAB158001Constant.FLGOFFN, ZYPConstant.FLG_OFF_N);
            paramMap.put(NPAB158001Constant.ORDER_TYPE_DS, NPAB158001Constant.DROP_SHIP);
            paramMap.put(NPAB158001Constant.ORDER_TYPE_WH, NPAB158001Constant.WARE_HOUSE);
            paramMap.put(NPAB158001Constant.RTE_STS_CD, RTE_STS.UN_ROUTED);
            paramMap.put(NPAB158001Constant.SHPG_STS_CD, SHPG_STS.VALIDATED);
            paramMap.put(NPAB158001Constant.ORD_LINE_SRC_CATG_CD, ORD_LINE_SRC_CATG.EXTERNAL);
            paramMap.put(NPAB158001Constant.SUBMIT_FOR_APPROVAL, PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL);
            paramMap.put(NPAB158001Constant.ENTERED, PRCH_REQ_APVL_STS.ENTERED);
            // START 2017/10/16 S.Katsuma [Sol#454,ADD]
//            paramMap.put(NPAB158001Constant.CTAC_PSN_TP_CD, NPAB158001Constant.SHIPTO);
            paramMap.put(NPAB158001Constant.CTAC_PSN_TP_DELIV_INS, CTAC_TP.DELIVERY_INSTALL);
            paramMap.put(NPAB158001Constant.CTAC_PSN_TP_ORD_CTAC, CTAC_TP.ORDER_CONTACT);
            // END 2017/10/16 S.Katsuma [Sol#454,ADD]
            paramMap.put(NPAB158001Constant.MDSE_TP_CD, MDSE_TP.SET);
            // QC#15749
            paramMap.put(NPAB158001Constant.PO_LINE_STS_CANCEL, PO_LINE_STS.CANCELLED);
            paramMap.put(NPAB158001Constant.PO_LINE_STS_CLOSE, PO_LINE_STS.CLOSED);
            // QC#29255
            paramMap.put("relFlg", ZYPConstant.FLG_OFF_N);
            paramMap.put("relPntHardAllocFlg", ZYPConstant.FLG_ON_Y);
            paramMap.put("whTpCd", WH_TP.COMMON);
            paramMap.put("ordLineStsCdList", ordLineStsCds);
            // QC#29177
            paramMap.put("ordLineDplyStsCd510", ORD_LINE_DPLY_STS.PARTIALLY_SHIPPED);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement(NPAB158001Constant.GET_ORDER_INFO, paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            String cpoOrdNum = "";
            String oldCpoOrdNum = "";
            PRCH_REQ_INTFCTMsg prchReqIntfcTMsg;
            // QC#29255
            String preCpoOrdNum = "";

            while (resultSet.next()) {
                // Set Mdse Order Process. Add QC#29255
                if (!preCpoOrdNum.equals(resultSet.getString(NPAB158001Constant.CPO_ORD_NUM))) {
                    for (int i = 0; i < cpoOrdNumList.size(); i++) {
                        procSetMdseOrder(cpoOrdNumList.get(i));
                    }
                    if (!resultSet.isFirst()) {
                        commit();
                    }
                    cpoOrdNumList = new ArrayList<Map<String, Object>>();
                }

                // set param Before Insert PRCH_REQ_INTFC
                prchReqIntfcTMsg = new PRCH_REQ_INTFCTMsg();
                setPrchReqIntfcTMsgParam(resultSet, prchReqIntfcTMsg, false);
                // Insert PRCH_REQ_INTFC
                EZDTBLAccessor.insert(prchReqIntfcTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqIntfcTMsg.getReturnCode())) {
                    rollback();
                    throw new S21AbendException(NPAB158001Constant.NPAM1172E, new String[] {NPAB158001Constant.PRCH_REQ_INTFC });
                }

                totalCommitCount++;

                // set Mdse Order Info
                cpoOrdNum = resultSet.getString(NPAB158001Constant.CPO_ORD_NUM) + resultSet.getString(NPAB158001Constant.CPO_DTL_LINE_NUM);
                if (ZYPConstant.FLG_ON_Y.equals(resultSet.getString(NPAB158001Constant.PRNT_CMPY_SET_MDSE_FLG)) && !oldCpoOrdNum.equals(cpoOrdNum))
                {
                    oldCpoOrdNum = cpoOrdNum;
                    cpoOrdNumMap = new HashMap<String, Object>();
                    cpoOrdNumMap.put(NPAB158001Constant.CPO_ORD_NUM, resultSet.getString(NPAB158001Constant.CPO_ORD_NUM));
                    cpoOrdNumMap.put(NPAB158001Constant.CPO_DTL_LINE_NUM, resultSet.getString(NPAB158001Constant.CPO_DTL_LINE_NUM));
                    // QC#29208
                    cpoOrdNumMap.put(NPAB158001Constant.ORDER_TYPE, resultSet.getString(NPAB158001Constant.ORDER_TYPE));
                    cpoOrdNumMap.put(NPAB158001Constant.RTL_WH_CD, resultSet.getString(NPAB158001Constant.RTL_WH_CD));
                    cpoOrdNumList.add(cpoOrdNumMap);
                }
                // QC#29255
                preCpoOrdNum = resultSet.getString(NPAB158001Constant.CPO_ORD_NUM);
            }

            // Set Mdse Order Process
            for (int i = 0; i < cpoOrdNumList.size(); i++) {
                procSetMdseOrder(cpoOrdNumList.get(i));
            }

            commit();
            totalCommitCount += commitCount;

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

    }

    @Override
    protected void termRoutine() {
        // Set EndCode, CommitCount, ErrorCount and totalRecord
        setTermState(termCd, totalCommitCount, 0, totalCommitCount);
    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB158001().executeBatch(NPAB158001.class.getSimpleName());
    }

    /**
     * procSetMdseOrder
     * @param cpoOrdNumMap Map<String, Object>
     */
    private void procSetMdseOrder(Map<String, Object> cpoOrdNumMap) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // get Set Mdse Order Detail Info
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB158001Constant.GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB158001Constant.TRX_HDR_NUM, cpoOrdNumMap.get(NPAB158001Constant.CPO_ORD_NUM));
            paramMap.put(NPAB158001Constant.TRX_LINE_NUM, cpoOrdNumMap.get(NPAB158001Constant.CPO_DTL_LINE_NUM));
            paramMap.put(NPAB158001Constant.TRX_LINE_SUB_NUM, NPAB158001Constant.SET_LINE_SUB_NUM);          // '000'
            paramMap.put(NPAB158001Constant.TRX_REF_LINE_SUB_NUM, NPAB158001Constant.SET_REF_LINE_SUB_NUM);  // '001'
            paramMap.put(NPAB158001Constant.ORDER_TYPE, cpoOrdNumMap.get(NPAB158001Constant.ORDER_TYPE));
            paramMap.put(NPAB158001Constant.RTL_WH_CD, cpoOrdNumMap.get(NPAB158001Constant.RTL_WH_CD));

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement(NPAB158001Constant.GET_SET_MDSE_ORDER_INFO, paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            PRCH_REQ_INTFCTMsg prchReqIntfcTMsg = new PRCH_REQ_INTFCTMsg();

            String shpgPlnNum = "";
            String oldShpgPlnNum = "";

            while (resultSet.next()) {

                shpgPlnNum = resultSet.getString(NPAB158001Constant.SHPG_PLN_NUM);
                if (!oldShpgPlnNum.equals(shpgPlnNum)) {
                    // set param Before Insert PRCH_REQ_INTFC
                    setPrchReqIntfcTMsgParam(resultSet, prchReqIntfcTMsg, true);
                    // Insert PRCH_REQ_INTFC
                    EZDTBLAccessor.insert(prchReqIntfcTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqIntfcTMsg.getReturnCode())) {
                        rollback();
                        throw new S21AbendException(NPAB158001Constant.NPAM1172E, new String[] {NPAB158001Constant.PRCH_REQ_INTFC });
                    }

                    totalCommitCount++;
                }

                oldShpgPlnNum = shpgPlnNum;

            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    /**
     * setPrchReqIntfcTMsgParam
     * @param rs ResultSet
     * @param prchReqIntfcTMsg PRCH_REQ_INTFCTMsg
     * @param mode String
     */
    private void setPrchReqIntfcTMsgParam(ResultSet rs, PRCH_REQ_INTFCTMsg param, boolean setMdseFlg) {

        try {
            BigDecimal prchReqIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRCH_REQ_INTFC_SQ);
            param.prchReqIntfcPk.setValue(prchReqIntfcPk);
            param.glblCmpyCd.setValue(globalCompanyCode);
            param.procStsCd.setValue(PROC_STS.IN_COMPLETED); // "0"
            boolean ittDropFlg = false;
            if (ZYPCommonFunc.hasValue(rs.getString(NPAB158001Constant.ITT_DROP_SHIP_FLG))
                    && ZYPConstant.FLG_ON_Y.equals(rs.getString(NPAB158001Constant.ITT_DROP_SHIP_FLG))) {
                ittDropFlg = true;
            }

            if (setMdseFlg) {
                // QC#52146 Start
//                ZYPEZDItemValueSetter.setValue(param.prchReqNum, rs.getString(NPAB158001Constant.PRCH_REQ_NUM));
//                ZYPEZDItemValueSetter.setValue(param.prchReqLineNum, rs.getString(NPAB158001Constant.PRCH_REQ_LINE_NUM));
//                ZYPEZDItemValueSetter.setValue(param.prchReqLineSubNum, BigDecimal.ZERO);
                // QC#52146 End
                // START QC#21006 MOD.
                if (ittDropFlg) {
                    ZYPEZDItemValueSetter.setValue(param.prchReqSrcTpCd, PRCH_REQ_SRC_TP.ITT_OUTBOUND);
                    ZYPEZDItemValueSetter.setValue(param.prchReqTpCd, PRCH_REQ_TP.ITT_OUTBOUND);
                } else {
                    ZYPEZDItemValueSetter.setValue(param.prchReqSrcTpCd, rs.getString(NPAB158001Constant.PRCH_REQ_SRC_TP_CD));
                    ZYPEZDItemValueSetter.setValue(param.prchReqTpCd, rs.getString(NPAB158001Constant.PRCH_REQ_TP_CD));
                }
                // END QC#21006 MOD.
                ZYPEZDItemValueSetter.setValue(param.prchReqRecTpCd, rs.getString(NPAB158001Constant.PRCH_REQ_REC_TP_CD));
                ZYPEZDItemValueSetter.setValue(param.prchReqLineTpCd, rs.getString(NPAB158001Constant.PRCH_REQ_LINE_TP_CD));
                ZYPEZDItemValueSetter.setValue(param.prchReqCratDt, rs.getString(NPAB158001Constant.PRCH_REQ_CRAT_DT));
                ZYPEZDItemValueSetter.setValue(param.prchReqCratTm, rs.getString(NPAB158001Constant.PRCH_REQ_CRAT_TM));
                ZYPEZDItemValueSetter.setValue(param.prchReqCratByPsnCd, rs.getString(NPAB158001Constant.PRCH_REQ_CRAT_BY_PSN_CD));
                ZYPEZDItemValueSetter.setValue(param.prchReqCratByNm, rs.getString(NPAB158001Constant.PRCH_REQ_CRAT_BY_NM));
                ZYPEZDItemValueSetter.setValue(param.rqstTechTocCd, rs.getString(NPAB158001Constant.RQST_TECH_TOC_CD));
                ZYPEZDItemValueSetter.setValue(param.trxRefNum, rs.getString(NPAB158001Constant.TRX_REF_NUM));
                ZYPEZDItemValueSetter.setValue(param.trxRefLineNum, rs.getString(NPAB158001Constant.TRX_REF_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(param.trxRefLineSubNum, rs.getString(NPAB158001Constant.TRX_REF_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(param.fsrNum, rs.getString(NPAB158001Constant.FSR_NUM));
                ZYPEZDItemValueSetter.setValue(param.svcTaskNum, rs.getString(NPAB158001Constant.SVC_TASK_NUM));
                ZYPEZDItemValueSetter.setValue(param.svcMachSerNum, rs.getString(NPAB158001Constant.SVC_MACH_SER_NUM));
                ZYPEZDItemValueSetter.setValue(param.ctacPsnNm, rs.getString(NPAB158001Constant.CTAC_PSN_NM));
                ZYPEZDItemValueSetter.setValue(param.shipFromSoNum, rs.getString(NPAB158001Constant.SHIP_FROM_SO_NUM));
                ZYPEZDItemValueSetter.setValue(param.procrTpCd, rs.getString(NPAB158001Constant.PROCR_TP_CD));
                ZYPEZDItemValueSetter.setValue(param.srcInvtyLocCd, rs.getString(NPAB158001Constant.SRC_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(param.srcRtlWhCd, rs.getString(NPAB158001Constant.SRC_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(param.srcRtlSwhCd, rs.getString(NPAB158001Constant.SRC_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(param.prntVndCd, rs.getString(NPAB158001Constant.PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(param.vndCd, rs.getString(NPAB158001Constant.VND_CD));
                ZYPEZDItemValueSetter.setValue(param.destInvtyLocCd, rs.getString(NPAB158001Constant.DEST_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(param.destRtlWhCd, rs.getString(NPAB158001Constant.DEST_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(param.destRtlSwhCd, rs.getString(NPAB158001Constant.DEST_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(param.rqstRcvDt, rs.getString(NPAB158001Constant.RQST_RCV_DT));
                ZYPEZDItemValueSetter.setValue(param.rqstRcvTm, rs.getString(NPAB158001Constant.RQST_RCV_TM));
                ZYPEZDItemValueSetter.setValue(param.poQlfyCd, rs.getString(NPAB158001Constant.PO_QLFY_CD));
                ZYPEZDItemValueSetter.setValue(param.poMdseCmpsnTpCd, NPAB158001Constant.SETMDSE); // "1"
                ZYPEZDItemValueSetter.setValue(param.prchReqCmntTxt, rs.getString(NPAB158001Constant.PRCH_REQ_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(param.spclInstnCmntTxt, rs.getString(NPAB158001Constant.SPCL_INSTN_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(param.rcvAddlCmntTxt, rs.getString(NPAB158001Constant.RCV_ADDL_CMNT_TXT));
                // START QC#21006 MOD.
                if (!ittDropFlg) {
                    //QC#5836 Start
                    ZYPEZDItemValueSetter.setValue(param.poSchdRelDt, rs.getString(NPAB158001Constant.PO_SCHD_REL_DT));
                    //QC#5836 End
                }
                // END QC#21006 MOD.

            } else {
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString(NPAB158001Constant.PRNT_CMPY_SET_MDSE_FLG))) {
                    ZYPEZDItemValueSetter.setValue(param.prchReqLineSubNum, rs.getBigDecimal(NPAB158001Constant.CPO_DTL_LINE_SUB_NUM));
                } else {
                    ZYPEZDItemValueSetter.setValue(param.prchReqLineSubNum, BigDecimal.ZERO);
                }
                ZYPEZDItemValueSetter.setValue(param.prchReqRecTpCd, PRCH_REQ_REC_TP.PO_REQUISITION); // "20"

                if (!dsOrdLineCatgTpList.isEmpty()
                        && dsOrdLineCatgTpList.contains(rs.getString(NPAB158001Constant.DS_ORD_LINE_CATG_CD))) {
                    ZYPEZDItemValueSetter.setValue(param.prchReqTpCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB158001Constant.NPAB1580_PR_TP_ROSS, globalCompanyCode));
                    // QC#11314
                    if (rossVndCds != null) {
                        ZYPEZDItemValueSetter.setValue(param.prntVndCd, rossPrntVndCd);
                        ZYPEZDItemValueSetter.setValue(param.vndCd, rossVndCds[0]);
                    }
                // START QC#52310 MOD.
                } else if (isGmdWh(globalCompanyCode, rs.getString(NPAB158001Constant.RTL_WH_CD))) {
                    ZYPEZDItemValueSetter.setValue(param.prchReqTpCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB158001Constant.NPAB1580_PR_TP_ROSS, globalCompanyCode));
                // END QC#52310 MOD.
                } else {
                    // START QC#21006 MOD.
                    if (ittDropFlg) {
                        ZYPEZDItemValueSetter.setValue(param.prchReqTpCd, PRCH_REQ_TP.ITT_OUTBOUND);
                    } else {
                        ZYPEZDItemValueSetter.setValue(param.prchReqTpCd, ZYPCodeDataUtil.getVarCharConstValue(NPAB158001Constant.NPAB1580_PR_TP_STANDARD, globalCompanyCode)); // "20"
                    }
                    // END QC#21006 MOD.
                }
                ZYPEZDItemValueSetter.setValue(param.prchReqLineTpCd, PRCH_REQ_TP.STANDARD_2); // "2001"
                ZYPEZDItemValueSetter.setValue(param.prchReqCratDt, salesDate);
                ZYPEZDItemValueSetter.setValue(param.prchReqCratTm, ZYPDateUtil.getCurrentSystemTime("HHmmss"));

                String prCratSystemUser = ZYPCodeDataUtil.getVarCharConstValue(NPAB158001Constant.PR_CRAT_SYSTEM_USER, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(param.prchReqCratByPsnCd, prCratSystemUser);
                ZYPEZDItemValueSetter.setValue(param.prchReqCratByNm, prCratSystemUser);

                // START QC#21006 MOD.
                if (ittDropFlg) {
                    ZYPEZDItemValueSetter.setValue(param.prchReqSrcTpCd, PRCH_REQ_SRC_TP.ITT_OUTBOUND);
                } else {
                    ZYPEZDItemValueSetter.setValue(param.prchReqSrcTpCd, NPAB158001Constant.ORDER_ENTRY); // "22"
                }
                // END QC#21006 MOD.    
                ZYPEZDItemValueSetter.setValue(param.trxRefNum, rs.getString(NPAB158001Constant.CPO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(param.trxRefLineNum, rs.getString(NPAB158001Constant.CPO_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(param.trxRefLineSubNum, rs.getString(NPAB158001Constant.CPO_DTL_LINE_SUB_NUM));

                String ctacPsnNm = "";
                // START 2017/10/16 S.Katsuma [Sol#454,ADD]
//                if (rs.getString(NPAB158001Constant.CTAC_PSN_FIRST_NM) != null) {
//                    ctacPsnNm = rs.getString(NPAB158001Constant.CTAC_PSN_FIRST_NM);
//                }
//                if (rs.getString(NPAB158001Constant.CTAC_PSN_MID_NM) != null) {
//                    ctacPsnNm = ctacPsnNm + " " + rs.getString(NPAB158001Constant.CTAC_PSN_MID_NM);
//                }
//                if (rs.getString(NPAB158001Constant.CTAC_PSN_LAST_NM) != null) {
//                    ctacPsnNm = ctacPsnNm + " " + rs.getString(NPAB158001Constant.CTAC_PSN_LAST_NM);
//                }
                if (rs.getString(NPAB158001Constant.SELL_TO_FIRST_REF_CMNT_TXT) != null) {
                    ctacPsnNm = rs.getString(NPAB158001Constant.SELL_TO_FIRST_REF_CMNT_TXT);
                }
                // END 2017/10/16 S.Katsuma [Sol#454,ADD]

                ctacPsnNm = ctacPsnNm.trim();

                if (ctacPsnNm.length() > NPAB158001Constant.MAX_LEN_CTAC_PSN_NM) {
                    ctacPsnNm = ctacPsnNm.substring(0, NPAB158001Constant.MAX_LEN_CTAC_PSN_NM);
                }

                ZYPEZDItemValueSetter.setValue(param.ctacPsnNm, ctacPsnNm);

                if (NPAB158001Constant.DROP_SHIP.equals(rs.getString(NPAB158001Constant.ORDER_TYPE))) {
                    ZYPEZDItemValueSetter.setValue(param.poQlfyCd, NPAB158001Constant.DROP_SHIP);  // "DS"

                    String cpoRddDt = rs.getString(NPAB158001Constant.CPO_RDD_DT);

                    BigDecimal dsPoSchdRelDays = ZYPCodeDataUtil.getNumConstValue(NPAB158001Constant.DS_PO_SCHD_REL_DAYS, globalCompanyCode);
                    if (dsPoSchdRelDays == null) {
                        dsPoSchdRelDays = BigDecimal.ZERO;
                    }
                    if (cpoRddDt != null && !cpoRddDt.isEmpty() && !ittDropFlg) {
                        String poSchdRelDt = ZYPDateUtil.addDays(cpoRddDt, dsPoSchdRelDays.intValue());
                        ZYPEZDItemValueSetter.setValue(param.poSchdRelDt, poSchdRelDt);
                    }
                }

                ZYPEZDItemValueSetter.setValue(param.procrTpCd, PROCR_TP.SUPPLIER); // "1"
                String destInvtyLocCd = "";
                if (rs.getString(NPAB158001Constant.RTL_WH_CD) != null) {
                    destInvtyLocCd = rs.getString(NPAB158001Constant.RTL_WH_CD);
                }
                if (rs.getString(NPAB158001Constant.RTL_SWH_CD) != null) {
                    destInvtyLocCd = destInvtyLocCd + rs.getString(NPAB158001Constant.RTL_SWH_CD);
                }
                ZYPEZDItemValueSetter.setValue(param.destInvtyLocCd, destInvtyLocCd);

                ZYPEZDItemValueSetter.setValue(param.destRtlWhCd, rs.getString(NPAB158001Constant.RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(param.destRtlSwhCd, rs.getString(NPAB158001Constant.RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(param.rqstRcvDt, rs.getString(NPAB158001Constant.RDD_DT));
                ZYPEZDItemValueSetter.setValue(param.setMdseCd, rs.getString(NPAB158001Constant.SET_MDSE_CD));
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString(NPAB158001Constant.PRNT_CMPY_SET_MDSE_FLG))) {
                    ZYPEZDItemValueSetter.setValue(param.poMdseCmpsnTpCd, NPAB158001Constant.CHILD); // "2"
                } else {
                    ZYPEZDItemValueSetter.setValue(param.poMdseCmpsnTpCd, NPAB158001Constant.REGULAR); // "3"
                }

                //QC#10919 Start
                ZYPEZDItemValueSetter.setValue(param.serNum, rs.getString(NPAB158001Constant.SER_NUM));
                //QC#10919 End

            }

            // START QC#21006 MOD.
            if (ittDropFlg) {
                ZYPEZDItemValueSetter.setValue(param.prchReqApvlStsCd, PRCH_REQ_APVL_STS.PRE_APPROVED);
            } else {
                ZYPEZDItemValueSetter.setValue(param.prchReqApvlStsCd, rs.getString(NPAB158001Constant.PRCH_REQ_APVL_STS_CD));
            }
            // END QC#21006 MOD.
            ZYPEZDItemValueSetter.setValue(param.shpgPlnNum, rs.getString(NPAB158001Constant.SHPG_PLN_NUM));
            ZYPEZDItemValueSetter.setValue(param.cpoOrdNum, rs.getString(NPAB158001Constant.CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(param.custIssPoNum, rs.getString(NPAB158001Constant.CUST_ISS_PO_NUM));
            ZYPEZDItemValueSetter.setValue(param.custIssPoDt, rs.getString(NPAB158001Constant.CUST_ISS_PO_DT));
            ZYPEZDItemValueSetter.setValue(param.cpoOrdTpCd, rs.getString(NPAB158001Constant.CPO_ORD_TP_CD));
            ZYPEZDItemValueSetter.setValue(param.adminPsnCd, rs.getString(NPAB158001Constant.ADMIN_PSN_CD));
            ZYPEZDItemValueSetter.setValue(param.vndInvtyLocCd, rs.getString(NPAB158001Constant.VND_INVTY_LOC_CD));

            // QC#27535
            if (NPAB158001Constant.WARE_HOUSE.equals(rs.getString(NPAB158001Constant.ORDER_TYPE)) //
                    && !Arrays.asList(mdWhCds).contains(rs.getString(NPAB158001Constant.RTL_WH_CD))) {

                ZYPEZDItemValueSetter.setValue(param.shipToCustCd, rs.getString(NPAB158001Constant.RTL_WH_CD));
                getShipToCustAddress(param);

            } else {

                ZYPEZDItemValueSetter.setValue(param.shipToCustCd, rs.getString(NPAB158001Constant.SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(param.shipToLocNm, rs.getString(NPAB158001Constant.SHIP_TO_LOC_NM));
                ZYPEZDItemValueSetter.setValue(param.shipToAddlLocNm, rs.getString(NPAB158001Constant.SHIP_TO_ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(param.shipToFirstLineAddr, rs.getString(NPAB158001Constant.SHIP_TO_FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(param.shipToScdLineAddr, rs.getString(NPAB158001Constant.SHIP_TO_SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(param.shipToThirdLineAddr, rs.getString(NPAB158001Constant.SHIP_TO_THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(param.shipToFrthLineAddr, rs.getString(NPAB158001Constant.SHIP_TO_FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(param.shipToFirstRefCmntTxt, rs.getString(NPAB158001Constant.SHIP_TO_FIRST_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(param.shipToScdRefCmntTxt, rs.getString(NPAB158001Constant.SHIP_TO_SCD_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(param.shipToCtyAddr, rs.getString(NPAB158001Constant.SHIP_TO_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(param.shipToStCd, rs.getString(NPAB158001Constant.SHIP_TO_ST_CD));
                ZYPEZDItemValueSetter.setValue(param.shipToProvNm, rs.getString(NPAB158001Constant.SHIP_TO_PROV_ADDR));
                ZYPEZDItemValueSetter.setValue(param.shipToCntyNm, rs.getString(NPAB158001Constant.SHIP_TO_CNTY_NM));
                ZYPEZDItemValueSetter.setValue(param.shipToPostCd, rs.getString(NPAB158001Constant.SHIP_TO_POST_CD));
                ZYPEZDItemValueSetter.setValue(param.shipToCtryCd, rs.getString(NPAB158001Constant.SHIP_TO_CTRY_CD));

            }

            ZYPEZDItemValueSetter.setValue(param.billToCustCd, rs.getString(NPAB158001Constant.BILL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(param.sellToCustCd, rs.getString(NPAB158001Constant.SELL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(param.vndDropShipFlg, rs.getString(NPAB158001Constant.VND_DROP_SHIP_FLG));
            ZYPEZDItemValueSetter.setValue(param.prchGrpCd, rs.getString(NPAB158001Constant.PRCH_GRP_CD));
            ZYPEZDItemValueSetter.setValue(param.mdseCd, rs.getString(NPAB158001Constant.MDSE_CD));
            ZYPEZDItemValueSetter.setValue(param.prchReqQty, rs.getBigDecimal(NPAB158001Constant.ORD_QTY));
            ZYPEZDItemValueSetter.setValue(param.ordQty, rs.getBigDecimal(NPAB158001Constant.ORD_QTY));
            ZYPEZDItemValueSetter.setValue(param.custUomCd, rs.getString(NPAB158001Constant.CUST_UOM_CD));
            ZYPEZDItemValueSetter.setValue(param.ropQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.maxInvtyQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.minOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.incrOrdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.curInvtyQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.curInvtyAvalQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.schdInbdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.schdOtbdQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(param.lineBizTpCd, rs.getString(NPAB158001Constant.LINE_BIZ_TP_CD));
            ZYPEZDItemValueSetter.setValue(param.frtChrgToCd, rs.getString(NPAB158001Constant.REQ_FRT_CHRG_TO_CD));
            ZYPEZDItemValueSetter.setValue(param.frtChrgMethCd, rs.getString(NPAB158001Constant.REQ_FRT_CHRG_METH_CD));

            // QC#18403 
            if (FRT_COND.COLLECT.equals(rs.getString(NPAB158001Constant.FRT_COND_CD))) {
                ZYPEZDItemValueSetter.setValue(param.frtCondCd, rs.getString(NPAB158001Constant.FRT_COND_CD));
                // QC#18403-1
//                ZYPEZDItemValueSetter.setValue(param.shpgSvcLvlCd, rs.getString(NPAB158001Constant.REQ_SHPG_SVC_LVL_CD));
//                ZYPEZDItemValueSetter.setValue(param.carrCd, rs.getString(NPAB158001Constant.RQST_CARR_CD));
//                ZYPEZDItemValueSetter.setValue(param.carrAcctNum, rs.getString(NPAB158001Constant.CARR_ACCT_NUM));
            }
            // QC#18403-1
            ZYPEZDItemValueSetter.setValue(param.shpgSvcLvlCd, rs.getString(NPAB158001Constant.REQ_SHPG_SVC_LVL_CD));
            ZYPEZDItemValueSetter.setValue(param.carrCd, rs.getString(NPAB158001Constant.RQST_CARR_CD));
            ZYPEZDItemValueSetter.setValue(param.carrAcctNum, rs.getString(NPAB158001Constant.CARR_ACCT_NUM));

            //08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) START
            //ZYPEZDItemValueSetter.setValue(param.delyAddlCmntTxt, rs.getString(NPAB158001Constant.DELY_ADDL_CMNT_TXT));

            StringBuilder sb = new StringBuilder();
            if (ZYPCommonFunc.hasValue(rs.getString(NPAB158001Constant.RQST_CARR_CD)) && ZYPCommonFunc.hasValue(rs.getString(NPAB158001Constant.CARR_ACCT_NUM))) {
                sb = sb.append(rs.getString(NPAB158001Constant.RQST_CARR_CD)).append(" : ").append(rs.getString(NPAB158001Constant.CARR_ACCT_NUM)).append(" ");
            } else if (ZYPCommonFunc.hasValue(rs.getString(NPAB158001Constant.RQST_CARR_CD)) && !ZYPCommonFunc.hasValue(rs.getString(NPAB158001Constant.CARR_ACCT_NUM))) {
                sb = sb.append(rs.getString(NPAB158001Constant.RQST_CARR_CD)).append(" ");
            } else if (!ZYPCommonFunc.hasValue(rs.getString(NPAB158001Constant.RQST_CARR_CD)) && ZYPCommonFunc.hasValue(rs.getString(NPAB158001Constant.CARR_ACCT_NUM))) {
                sb = sb.append(rs.getString(NPAB158001Constant.CARR_ACCT_NUM)).append(" ");
            }
            // START 2023/07/28 R.Takau [QC#60676,DEL]
//            if (ZYPCommonFunc.hasValue(rs.getString(NPAB158001Constant.SPCL_HDLG_TP_DESC_TXT))) {
//                sb.append(rs.getString(NPAB158001Constant.SPCL_HDLG_TP_DESC_TXT));
//            }
            // END 2023/07/28 R.Takau [QC#60676,DEL]
            if (ZYPCommonFunc.hasValue(rs.getString(NPAB158001Constant.DELY_ADDL_CMNT_TXT))) {
                sb.append(rs.getString(NPAB158001Constant.DELY_ADDL_CMNT_TXT));
            }
            if (sb.toString().length() > NPAB158001Constant.MAX_LENGTH_DELY_ADDL_CMNT_TXT) {
                ZYPEZDItemValueSetter.setValue(param.delyAddlCmntTxt, sb.toString().substring(0, NPAB158001Constant.MAX_LENGTH_DELY_ADDL_CMNT_TXT));
            } else {
                ZYPEZDItemValueSetter.setValue(param.delyAddlCmntTxt, sb.toString());
            }
            //08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) END
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    /**
     * Add QC#27535. getShipToCustAddress
     * @param param PRCH_REQ_INTFCTMsg
     */
    private void getShipToCustAddress(PRCH_REQ_INTFCTMsg param) {

        Map<String, String> shipToCustMap = null;

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("shipToCustCd", param.shipToCustCd.getValue());
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        shipToCustMap = (Map<String, String>) this.ssmBatchClient.queryObject("getShipTo", queryParam);

        if (shipToCustMap == null || shipToCustMap.isEmpty()) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(param.shipToLocNm, shipToCustMap.get(NPAB158001Constant.SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(param.shipToAddlLocNm, shipToCustMap.get(NPAB158001Constant.SHIP_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(param.shipToFirstLineAddr, shipToCustMap.get(NPAB158001Constant.SHIP_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToScdLineAddr, shipToCustMap.get(NPAB158001Constant.SHIP_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToThirdLineAddr, shipToCustMap.get(NPAB158001Constant.SHIP_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToFrthLineAddr, shipToCustMap.get(NPAB158001Constant.SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToFirstRefCmntTxt, shipToCustMap.get(NPAB158001Constant.SHIP_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(param.shipToScdRefCmntTxt, shipToCustMap.get(NPAB158001Constant.SHIP_TO_SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(param.shipToCtyAddr, shipToCustMap.get(NPAB158001Constant.SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToPostCd, shipToCustMap.get(NPAB158001Constant.SHIP_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(param.shipToProvNm, shipToCustMap.get(NPAB158001Constant.SHIP_TO_PROV_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToStCd, shipToCustMap.get(NPAB158001Constant.SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(param.shipToCntyNm, shipToCustMap.get(NPAB158001Constant.SHIP_TO_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(param.shipToCtryCd, shipToCustMap.get(NPAB158001Constant.SHIP_TO_CTRY_CD));

        return;
    }

    // START QC#52310 ADD
    /**
     * isGmdWh
     * @param param glblCmpyCd String
     * @param param rtlWhCd String
     * @return boolean
     */
    public static boolean isGmdWh(String glblCmpyCd, String rtlWhCd) {

        if (rtlWhCd == null) {
            return false;
        }

        RTL_WHTMsg rwTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rwTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwTMsg.rtlWhCd, rtlWhCd);

        rwTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rwTMsg);

        if (rwTMsg == null) {
            return false;
        }

        if (INVTY_OWNR.GMD.equals(rwTMsg.invtyOwnrCd.getValue())){
            return true;
        }
        return false;
    }
    // END QC#52310 ADD
}
