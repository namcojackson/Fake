/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB138001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.PRCH_REQ_INTFCTMsg;
import business.db.PRCH_REQ_TPTMsg;
import business.db.PROCR_TPTMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC108001PMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NPZC129001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC108001.NPZC108001;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NPZ.NPZC129001.NPZC129001;
import com.canon.cusa.s21.batch.NPA.NPAB138001.constant.NPAB138001Constant;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversion;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversionBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NPAB138001:PR Import
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   CITS            T.Kikuhara      Create          N/A
 * 2016/03/21   CSAI            K.Lee           Update          QC#5781
 * 2016/03/22   CSAI            K.Lee           Update          QC#5847
 * 2016/03/22   CSAI            K.Lee           Update          QC#5848
 * 2016/03/24   CSAI            K.Lee           Update          QC#5958
 * 2016/03/24   CSAI            K.Lee           Update          QC#5959
 * 2016/03/24   CSAI            K.Lee           Update          QC#6010
 * 2016/03/24   CSAI            K.Lee           Update          QC#5959
 * 2016/04/12   CSAI            K.Lee           Update          QC#6181
 * 2016/04/19   CSAI            K.Lee           Update          QC#7234
 * 2016/04/19   CSAI            K.Lee           Update          QC#7474
 * 2016/05/03   CSAI            K.Lee           Update          QC#7802
 * 2016/05/05   CSAI            K.Lee           Update          QC#7921
 * 2016/10/31   CSAI            K.Ogino         Update          QC#14888
 * 2016/12/08   CITS            T.Kikuhara      Update          QC#15123
 * 01/27/2017   CITS            K.Ogino         Update          QC#11314
 * 03/07/2017   CITS            T.Kikuhara      Update          QC#15983
 * 06/30/2017   CITS            K.Ogino         Update          QC#19677
 * 07/04/2017   CITS            Y.Iwasaki       Update          QC#19742
 * 08/17/2017   CITS            K.Ogino         Update          QC#20626,QC#20506
 * 08/21/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 09/21/2017   CITS            K.Ogino         Update          QC#21275
 * 09/25/2017   CITS            R.Shimamoto     Update          QC#21006
 * 11/16/2017   CITS            T.Tokutomi      Update          QC#18689-Sol#303
 * 04/04/2018   CITS            T.Wada          Update          QC#21170
 * 04/13/2018   CITS            K.Ogino         Update          QC#25411
 * 06/12/2018   CITS            K.Ogino         Update          QC#25411-1
 * 10/09/2018   CITS            T.Tokutomi      Update          QC#28268
 * 12/17/2018   CITS            K.Ogino         Update          QC#29177
 * 01/18/2019   CITS            T.Tokutomi      Update          QC#29898
 * 06/17/2019   CITS            K.Ogino         Update          QC#50850
 * 09/24/2019   CITS            M.Naito         Update          QC#53245
 * 01/20/2023   CITS            A.Cullano       Update          QC#61081
 * 06/23/2023   CITS            R.Kurahashi     Update          QC#61081
 * 10/11/2023   CITS            F.Komaki        Update          QC#61870
 * 02/22/2024   CITS            H.Tashiro       Update          QC#62208
 *</pre>
 */
public class NPAB138001 extends S21BatchMain {

    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total count */
    private int totalCount = 0;

    /** Error count */
    private int errorCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** divideGrpColumns */
    private String[] divideGrpColumns;

    /** mrpTypes */
    private String[] mrpTypes;

    /** Error Mail Detail */
    private StringBuilder errMailDetail = null;

    /** newLine */
    private String newLine = null;

    /** rossVndCds */
    private String[] rossVndCds = null;

    /** prCancelApvlCds */
    private String[] prCancelApvlCds = null;

    // QC#18689-Sol#303
    /** technician Error Mail List */
    private ArrayList<HashMap<String, String>> techErrMailList = new ArrayList<HashMap<String, String>>();

    // QC#28268 Add.
    /** CPO(DS) Merge Planning Group Code */
    private String dsMrgPlnGrpCd = null;

    // QC#28268 Add.
    /** ROSS Merge Planning Group Code */
    private String rossMrgPlnGrpCd = null;

    // QC#28268 Add.
    /** MRP Merge Planning Group Code */
    private String mrpMrgPlnGrpCd = null;

    // QC#28268 Add.
    /** Drop Ship Warehouse Code */
    private String dropShipWhCd = null;

    @Override
    protected void initRoutine() {

        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAB138001Constant.NPAM1173E, new String[] {NPAB138001Constant.GLBL_CMPY_CD });
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAB138001Constant.NPAM1173E, new String[] {NPAB138001Constant.SALES_DATE });
        }

        String wkDivideGrpColumns = ZYPCodeDataUtil.getVarCharConstValue(NPAB138001Constant.VAR_CHAR_CONST_KEY_NPAB1380_DIVIDE_GRP_COLUMNS, globalCompanyCode);
        if (wkDivideGrpColumns != null) {
            this.divideGrpColumns = wkDivideGrpColumns.split(",");
        } else {
            throw new S21AbendException(NPAB138001Constant.NSBM0069E, new String[] {NPAB138001Constant.VAR_CHAR_CONST_KEY_NPAB1380_DIVIDE_GRP_COLUMNS });
        }

        String wkMrpTypes = ZYPCodeDataUtil.getVarCharConstValue(NPAB138001Constant.VAR_CHAR_CONST_KEY_NPAB1380_MRP_TPS, globalCompanyCode);
        if (wkMrpTypes != null) {
            this.mrpTypes = wkMrpTypes.split(",");
        } else {
            this.mrpTypes = new String[2];
            this.mrpTypes[0] = PRCH_REQ_SRC_TP.TECH_PLANNING;
            this.mrpTypes[1] = PRCH_REQ_SRC_TP.WH_PLANNING;
        }

        newLine = System.getProperty("line.separator");

        // rossVndCds
        String constValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB138001Constant.VAR_CHAR_CONST_ROSS_VND_CD, this.globalCompanyCode);
        if (ZYPCommonFunc.hasValue(constValue)) {
               rossVndCds = constValue.split(",");
        }

        String prConstValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB138001Constant.NPAB1380_PR_CANCEL_APVL_STATUS, this.globalCompanyCode);
        if (ZYPCommonFunc.hasValue(prConstValue)) {
            prCancelApvlCds = prConstValue.split(",");
        } else {
            prCancelApvlCds = new String[] {PRCH_REQ_APVL_STS.ENTERED, PRCH_REQ_APVL_STS.AWAITING_APPROVAL, PRCH_REQ_APVL_STS.REJECTED, PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL };
        }

        // QC#28268 Update. Add planning group codes.
        dsMrgPlnGrpCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB138001Constant.VAR_CHAR_CONST_NPAB1380_CPO_MRG_PLN_GRP_CD, this.globalCompanyCode);

        if (ZYPCommonFunc.hasValue(dsMrgPlnGrpCd)) {
            // Master check.
            if (!doesExistPrchGrpCd(dsMrgPlnGrpCd)) {
                throw new S21AbendException(NPAB138001Constant.NPAM1537E //
                        , new String[] {dsMrgPlnGrpCd, "PRCH_GRP", NPAB138001Constant.PRCH_GRP_CD});
            }
        }

        rossMrgPlnGrpCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB138001Constant.VAR_CHAR_CONST_NPAB1380_ROSS_MRG_PLN_GRP_CD, this.globalCompanyCode);

        if (ZYPCommonFunc.hasValue(rossMrgPlnGrpCd)) {
            // Master check.
            if (!doesExistPrchGrpCd(rossMrgPlnGrpCd)) {
                throw new S21AbendException(NPAB138001Constant.NPAM1537E //
                        , new String[] {rossMrgPlnGrpCd, "PRCH_GRP", NPAB138001Constant.PRCH_GRP_CD });
            }
        }

        mrpMrgPlnGrpCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB138001Constant.VAR_CHAR_CONST_NPAB1380_MRP_MRG_PLN_GRP_CD, this.globalCompanyCode);

        if (ZYPCommonFunc.hasValue(mrpMrgPlnGrpCd)) {
            // Master check.
            if (!doesExistPrchGrpCd(mrpMrgPlnGrpCd)) {
                throw new S21AbendException(NPAB138001Constant.NPAM1537E //
                        , new String[] {mrpMrgPlnGrpCd, "PRCH_GRP", NPAB138001Constant.PRCH_GRP_CD });
            }
        }

        dropShipWhCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB138001Constant.VAR_CHAR_CONST_DROP_SHIP_RTL_WH_CD, this.globalCompanyCode);

        if (!ZYPCommonFunc.hasValue(dropShipWhCd)) {
            throw new S21AbendException(NPAB138001Constant.NPAM1537E //
                    , new String[] {NPAB138001Constant.VAR_CHAR_CONST_DROP_SHIP_RTL_WH_CD, "VAR_CHAR_CONST", NPAB138001Constant.VAR_CHAR_CONST_DROP_SHIP_RTL_WH_CD });
        }
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Map<String, Object>> prInterFaceList = new ArrayList<Map<String, Object>>();

        try {
            // get PrInterface Data
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB138001Constant.GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB138001Constant.PROC_STS_CD_LIST, new String[]{PROC_STS.IN_COMPLETED, PROC_STS.ERROR});

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement(NPAB138001Constant.GETPRINTERFACE, paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            String oldTrxRefNum = "";
            String oldTrxRefLineNum = "";
            String oldProcStsCd = "";
            String vndCd = "";

            // QC#21170
            String rqstRcvDt = null;
            // QC#29177
            // 2023/10/11 QC#61870 START
            String rqstShipDt = null;
            // 2023/10/11 QC#61870 END
            String prchGrpCd = "";

            while (resultSet.next()) {

                // QC#25411 Start
                String cpoOrdNum = resultSet.getString(NPAB138001Constant.CPO_ORD_NUM);
                // QC#29177
                String trxRefLineNum = resultSet.getString(NPAB138001Constant.TRX_REF_LINE_NUM);
                String trxRefLineSubNum = resultSet.getString(NPAB138001Constant.TRX_REF_LINE_SUB_NUM);
                String rtlWhCd  = resultSet.getString(NPAB138001Constant.DEST_RTL_WH_CD);

                // START 2023/01/19 A.Cullano [QC#61081, ADD]
                BigDecimal existCount = BigDecimal.ZERO;
                // END 2023/01/19 A.Cullano [QC#61081, ADD]

                if (ZYPCommonFunc.hasValue(cpoOrdNum)) {
                    // START 2023/01/19 A.Cullano [QC#61081, ADD]
                    existCount = getExistCnt(cpoOrdNum);
                    // END 2023/01/19 A.Cullano [QC#61081, ADD]

                    // Open PR Cancel
                    if (ZYPCommonFunc.hasValue(rtlWhCd) && dropShipWhCd.equals(rtlWhCd)) {
                        // Dropship PR
                        dropshipPrCancel(cpoOrdNum);
                    } else {
                        // External PR
                        externalPrCancel(cpoOrdNum, trxRefLineNum, trxRefLineSubNum);
                    }
                }

                // QC#25411 End
                totalCount++;

                // START 2023/01/19 A.Cullano [QC#61081, ADD]
                if (BigDecimal.ZERO.compareTo(existCount) != 0) {
                    // START 2023/06/23 R.Kurahashi [QC#61081, ADD]
                    deletePrInterface(resultSet.getBigDecimal(NPAB138001Constant.PRCH_REQ_INTFC_PK));
                    // END 2023/06/23 R.Kurahashi [QC#61081, ADD]
                    // Skip succeeding processing since there is an existing PO Req with the same CPO#
                    continue;
                }
                // END 2023/01/19 A.Cullano [QC#61081, ADD]

                // set DB result to Map
                Map<String, Object> prInterFaceMap = setPrInterFaceList(resultSet);
                // QC#29177
                if (!isMrpOrder(prInterFaceMap) && isSetParent(prInterFaceMap)) {
                    prchGrpCd  = (String) prInterFaceMap.get(NPAB138001Constant.PRCH_GRP_CD);
                }

                String trxRefNum = resultSet.getString(NPAB138001Constant.TRX_REF_NUM);

                if (trxRefNum == null) {
                    trxRefNum = "";
                }
                if (trxRefLineNum == null) {
                    trxRefLineNum = "";
                }
                if (!oldTrxRefNum.equals(trxRefNum) || !oldTrxRefLineNum.equals(trxRefLineNum)) {
                    vndCd = "";
                }

                // Min_Max data check
                // if Min_Max data. same TRX_REF_NUM ERROR is not exec error process.
                boolean mrpDataFlg = false;
                for (int i = 0; i < mrpTypes.length; i++) {
                    if (mrpTypes[i].equals((String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_SRC_TP_CD))) {
                        mrpDataFlg = true;
                    }
                }

                // Set error for Same Group Data without Min_Max data
                if (mrpDataFlg) {
                    oldProcStsCd = PROC_STS.IN_COMPLETED;
                }
                if (oldTrxRefNum.equals(trxRefNum)) {
                    if (PROC_STS.ERROR.equals(oldProcStsCd)) {
                        // Sales Order Process
                        prInterFaceMap.put(NPAB138001Constant.PROC_STS_CD, PROC_STS.ERROR);
                        prInterFaceList.add(prInterFaceMap);
                        errorCount++;
                        continue;
                    }
                } else {
                    oldTrxRefNum = trxRefNum;
                    oldProcStsCd = PROC_STS.IN_COMPLETED;
                }

                String procrTpCd = resultSet.getString(NPAB138001Constant.PROCR_TP_CD);
                if (procrTpCd == null) {
                    // Get Procurement Type
                    if (!getProcurementType(prInterFaceMap)) {

                        if (!mrpDataFlg) {
                            setPrevProcStsCd(prInterFaceList, trxRefNum, PROC_STS.ERROR);
                        }

                        prInterFaceMap.put(NPAB138001Constant.PROC_STS_CD, PROC_STS.ERROR);
                        oldProcStsCd = PROC_STS.ERROR;
                    } else {
                        procrTpCd = (String) prInterFaceMap.get(NPAB138001Constant.PROCR_TP_CD);
                    }
                }

                if (procrTpCd != null && PROCR_TP.SUPPLIER.equals(procrTpCd)) {

                    // QC#11314
                    String chkVndCd = (String) prInterFaceMap.get(NPAB138001Constant.VND_CD);
                    // QC#21006
                    String prTpCd = (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD);
                    // START 2019/09/25 M.Naito [QC#53245, ADD]
                    String procErrMsgCd = (String) prInterFaceMap.get(NPAB138001Constant.PROC_ERR_MSG_CD);
                    // END 2019/09/25 M.Naito [QC#53245, ADD]
                    if (!PRCH_REQ_TP.ITT_OUTBOUND.equals(prTpCd)) {
                        if (!(rossVndCds != null && ZYPCommonFunc.hasValue(chkVndCd) && Arrays.asList(rossVndCds).contains(chkVndCd))) {

                            // Get Supplier
                            String poMdseCmpsnTpCd = (String) prInterFaceMap.get(NPAB138001Constant.PO_MDSE_CMPSN_TP_CD);
                            if (!PO_MDSE_CMPSN_TP.CHILD.equals(poMdseCmpsnTpCd)) {
                                if (getSupplier(prInterFaceMap)) {
                                    vndCd = (String) prInterFaceMap.get(NPAB138001Constant.VND_CD);

                                    // QC#21170
                                    if (mrpDataFlg) {
                                        rqstRcvDt = getRqstRcvDt(prInterFaceMap);
                                        // 2023/10/11 QC#61870 START
                                        rqstShipDt = getRqstShipDt(prInterFaceMap);
                                        prInterFaceMap.put(NPAB138001Constant.RQST_SHIP_DT, rqstShipDt);
                                        // 2023/10/11 QC#61870 END
                                        prInterFaceMap.put(NPAB138001Constant.RQST_RCV_DT, rqstRcvDt);
                                    }

                                } else {
                                    // START 2019/09/25 M.Naito [QC#53245, ADD]
                                    if (ZYPCommonFunc.hasValue(procErrMsgCd) && procErrMsgCd.equals((String) prInterFaceMap.get(NPAB138001Constant.PROC_ERR_MSG_CD))) {
                                        errorCount++;
                                        continue;
                                    }
                                    // END 2019/09/25 M.Naito [QC#53245, ADD]
                                    prInterFaceMap.put(NPAB138001Constant.PROC_STS_CD, PROC_STS.ERROR);
                                    if (!mrpDataFlg) {
                                        setPrevProcStsCd(prInterFaceList, trxRefNum, PROC_STS.ERROR);
                                    }
                                    oldProcStsCd = PROC_STS.ERROR;
                                    vndCd = null;
                                }
                            } else {
                                if (trxRefLineNum.equals(oldTrxRefLineNum) && ZYPCommonFunc.hasValue(vndCd)) {
                                    String glblCmpyCd = (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD);
                                    String mdseCd = (String) prInterFaceMap.get(NPAB138001Constant.MDSE_CD);

                                    // QC#21170
                                    if (mrpDataFlg) {
                                        prInterFaceMap.put(NPAB138001Constant.RQST_RCV_DT, rqstRcvDt);
                                    }

                                    if (!getAslInfo(glblCmpyCd, vndCd, mdseCd, prInterFaceMap)) {
                                        // START 2019/09/25 M.Naito [QC#53245, ADD]
                                        if (ZYPCommonFunc.hasValue(procErrMsgCd) && procErrMsgCd.equals((String) prInterFaceMap.get(NPAB138001Constant.PROC_ERR_MSG_CD))) {
                                            errorCount++;
                                            continue;
                                        }
                                        // END 2019/09/25 M.Naito [QC#53245, ADD]
                                        prInterFaceMap.put(NPAB138001Constant.PROC_STS_CD, PROC_STS.ERROR);
                                        if (!mrpDataFlg) {
                                            setPrevProcStsCd(prInterFaceList, trxRefNum, PROC_STS.ERROR);
                                        }
                                        oldProcStsCd = PROC_STS.ERROR;
                                        // QC#19677
                                        vndCd = null;
                                    }
                                }
                            }

                            if (ZYPCommonFunc.hasValue(vndCd)) {
                                if (!changePrice(prInterFaceMap)) {
                                    prInterFaceMap.put(NPAB138001Constant.PROC_STS_CD, PROC_STS.ERROR);
                                    if (!mrpDataFlg) {
                                        setPrevProcStsCd(prInterFaceList, trxRefNum, PROC_STS.ERROR);
                                    }
                                    oldProcStsCd = PROC_STS.ERROR;
                                }
                                if (!changeQty(prInterFaceMap)) {
                                    prInterFaceMap.put(NPAB138001Constant.PROC_STS_CD, PROC_STS.ERROR);
                                    if (!mrpDataFlg) {
                                        setPrevProcStsCd(prInterFaceList, trxRefNum, PROC_STS.ERROR);
                                    }
                                    oldProcStsCd = PROC_STS.ERROR;
                                }
                            }
                        }
                    }
                }

                // QC#28268 Update.
                if (isDropShipOrder(prInterFaceMap) && ZYPCommonFunc.hasValue(dsMrgPlnGrpCd)) {
                    prInterFaceMap.put(NPAB138001Constant.PRCH_GRP_CD, dsMrgPlnGrpCd);
                } else if (isRossOrder(prInterFaceMap) && ZYPCommonFunc.hasValue(rossMrgPlnGrpCd)) {
                    prInterFaceMap.put(NPAB138001Constant.PRCH_GRP_CD, rossMrgPlnGrpCd);
                } else if (isMrpOrder(prInterFaceMap) && ZYPCommonFunc.hasValue(mrpMrgPlnGrpCd)) {
                    prInterFaceMap.put(NPAB138001Constant.PRCH_GRP_CD, mrpMrgPlnGrpCd);
                // QC#29177
                } else if (isSetComponent(prInterFaceMap)) {
                    prInterFaceMap.put(NPAB138001Constant.PRCH_GRP_CD, prchGrpCd);
                }

                if (PROC_STS.ERROR.equals(oldProcStsCd)) {
                    if (mrpDataFlg) {
                        updateErrorPrInterface(prInterFaceMap);
                        prInterFaceMap = null;
                    }
                    errorCount++;
                }

                oldTrxRefLineNum = trxRefLineNum;
                if (prInterFaceMap != null) {
                    prInterFaceList.add(prInterFaceMap);
                }
            }

            // Sort prInterFaceList
            sortPrInterFaceList(prInterFaceList);

            // PR Update and PR Interface Update
            updatePrAndPrInterface(prInterFaceList);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        // Send Mail if error or warning occurred.
        if (errMailDetail != null && ZYPCommonFunc.hasValue(errMailDetail.toString())) {
            termCd = TERM_CD.WARNING_END;
            sendErrorMail();
            // QC#18689-Sol#303
            sendErrorMailToTech();
        }
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        setTermState(termCd,  totalCount - errorCount, errorCount, totalCount);
    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB138001().executeBatch(NPAB138001.class.getSimpleName());
    }

    /**
     * setPrInterFaceList
     * @param resultSet ResultSet
     */

    private Map<String, Object> setPrInterFaceList(ResultSet rs) {
        Map<String, Object> prInterFaceMap = new HashMap<String, Object>();

        try {
            ResultSetMetaData rsm = rs.getMetaData();
            for (int i = 1; i < rsm.getColumnCount() + 1; i++) {
                String colName = rsm.getColumnName(i);
                Object colVal = rs.getObject(colName);
                prInterFaceMap.put(colName, colVal);
            }

           prInterFaceMap.put(NPAB138001Constant.PROC_STS_CD, PROC_STS.COMPLEATED);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return prInterFaceMap;
    }

    /**
     * getProcurementType
     */
    private boolean getProcurementType(Map<String, Object> prInterFaceMap) {

        boolean isError = false;

        NPZC108001PMsg pMsg = new NPZC108001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) prInterFaceMap.get(NPAB138001Constant.MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) prInterFaceMap.get(NPAB138001Constant.DEST_INVTY_LOC_CD));

        // get Procurement Source API
        new NPZC108001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                if (pMsg.xxMsgIdList.no(i).xxMsgId.getValue().endsWith("E")) {
                    String procErrMsgCd = (String) prInterFaceMap.get(NPAB138001Constant.PROC_ERR_MSG_CD);
                    if (!ZYPCommonFunc.hasValue(procErrMsgCd) || !procErrMsgCd.endsWith("E")) {
                        prInterFaceMap.put(NPAB138001Constant.PROC_ERR_MSG_CD, pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    isError = true;
                }
                addErrorMailDetail(prInterFaceMap, pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        } else {
            prInterFaceMap.put(NPAB138001Constant.PROCR_TP_CD, pMsg.procrTpCd.getValue());
            prInterFaceMap.put(NPAB138001Constant.SRC_LOC_CD, pMsg.srcLocCd.getValue());
            prInterFaceMap.put(NPAB138001Constant.SRC_RTL_WH_CD, pMsg.srcRtlWhCd.getValue());
            prInterFaceMap.put(NPAB138001Constant.SRC_RTL_SWH_CD, pMsg.srcRtlSwhCd.getValue());
        }
        return !isError;
    }

    /**
     * getSupplier
     */
    private boolean getSupplier(Map<String, Object> prInterFaceMap) {

        boolean isError = false;
        NPZC113001PMsg pMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) prInterFaceMap.get(NPAB138001Constant.MDSE_CD));

        // Get Primary Vendor from ASL API
        new NPZC113001().execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                if (pMsg.xxMsgIdList.no(i).xxMsgId.getValue().endsWith("E")) {
                    String procErrMsgCd = (String) prInterFaceMap.get(NPAB138001Constant.PROC_ERR_MSG_CD);
                    if (!ZYPCommonFunc.hasValue(procErrMsgCd) || !procErrMsgCd.endsWith("E")) {
                        prInterFaceMap.put(NPAB138001Constant.PROC_ERR_MSG_CD, pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    isError = true;
                }
                addErrorMailDetail(prInterFaceMap, pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
        if (ZYPConstant.FLG_OFF_N.equals(pMsg.xxErrFlg.getValue())) {
            prInterFaceMap.put(NPAB138001Constant.PRNT_VND_CD, pMsg.prntVndCd.getValue());
            prInterFaceMap.put(NPAB138001Constant.VND_CD, pMsg.vndCd.getValue());
            prInterFaceMap.put(NPAB138001Constant.ASL_DTL_PK, pMsg.aslDtlPk.getValue());
            prInterFaceMap.put(NPAB138001Constant.ASL_UNIT_PRC_AMT, pMsg.unitPrcAmt.getValue());
            //QC#5781 Start
            prInterFaceMap.put(NPAB138001Constant.ASL_MDSE_CD, pMsg.splyItemNum.getValue());
            //QC#5781 End

            // QC#21170
            if (ZYPCommonFunc.hasValue(pMsg.vndLtDaysNum)) {
                prInterFaceMap.put(NPAB138001Constant.ASL_VND_LT_DAYS_NUM, pMsg.vndLtDaysNum.getValue());
            } else {
                prInterFaceMap.put(NPAB138001Constant.ASL_VND_LT_DAYS_NUM, null);
            }

            // 2023/10/11 QC#61870 START
            if (ZYPCommonFunc.hasValue(pMsg.vndShipLtDaysNum)) {
                prInterFaceMap.put(NPAB138001Constant.ASL_VND_SHIP_LT_DAYS_NUM, pMsg.vndShipLtDaysNum.getValue());
            } else {
                prInterFaceMap.put(NPAB138001Constant.ASL_VND_SHIP_LT_DAYS_NUM, null);
            }
            // 2023/10/11 QC#61870 END

        } else {
            String prchReqApvlStsCd = (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_APVL_STS_CD);
            if (!PRCH_REQ_APVL_STS.ENTERED.equals(prchReqApvlStsCd)) {
                isError = true;
                String procErrMsgCd = (String) prInterFaceMap.get(NPAB138001Constant.PROC_ERR_MSG_CD);
                if (!ZYPCommonFunc.hasValue(procErrMsgCd) || !procErrMsgCd.endsWith("E")) {
                    prInterFaceMap.put(NPAB138001Constant.PROC_ERR_MSG_CD, NPAB138001Constant.NPZM0268E);
                    addErrorMailDetail(prInterFaceMap, NPAB138001Constant.NPZM0268E);
                }
            }
        }

        return !isError;
    }

    /**
     * get ASL Information
     * @param glblCmpyCd String
     * @param mdseCd String
     */
    private boolean getAslInfo(String glblCmpyCd, String vndCd, String mdseCd, Map<String, Object> prInterFaceMap) {

        boolean isError = false;

        // Get ASL Info
        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put(NPAB138001Constant.GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(NPAB138001Constant.SLS_DT, ZYPDateUtil.getSalesDate(glblCmpyCd));
        queryParam.put(NPAB138001Constant.VND_CD, vndCd);
        queryParam.put(NPAB138001Constant.MDSE_CD, mdseCd);
        queryParam.put(NPAB138001Constant.MIN_START_DATE, NPAB138001Constant.MIN_START_DATE_VAL);
        queryParam.put(NPAB138001Constant.MAX_START_DATE, NPAB138001Constant.MAX_START_DATE_VAL);

        Map<String, Object> aslInfoMap = (Map<String, Object>) ssmBatchClient.queryObject(NPAB138001Constant.GET_ASL_INFO_BY_VND, queryParam);

        if (aslInfoMap == null || aslInfoMap.isEmpty()) {
            isError = true;
            String procErrMsgCd = (String) prInterFaceMap.get(NPAB138001Constant.PROC_ERR_MSG_CD);
            if (!ZYPCommonFunc.hasValue(procErrMsgCd) || !procErrMsgCd.endsWith("E")) {
                prInterFaceMap.put(NPAB138001Constant.PROC_ERR_MSG_CD, NPAB138001Constant.NPZM0268E);
                addErrorMailDetail(prInterFaceMap, NPAB138001Constant.NPZM0268E);
            }
        } else {
            prInterFaceMap.put(NPAB138001Constant.PRNT_VND_CD, (String) aslInfoMap.get(NPAB138001Constant.PRNT_VND_CD));
            prInterFaceMap.put(NPAB138001Constant.VND_CD, (String) aslInfoMap.get(NPAB138001Constant.VND_CD));
            prInterFaceMap.put(NPAB138001Constant.ASL_DTL_PK, (BigDecimal) aslInfoMap.get(NPAB138001Constant.ASL_DTL_PK));
            prInterFaceMap.put(NPAB138001Constant.ASL_UNIT_PRC_AMT, (BigDecimal) aslInfoMap.get(NPAB138001Constant.UNIT_PRC_AMT));
            prInterFaceMap.put(NPAB138001Constant.ASL_MDSE_CD, (String) aslInfoMap.get(NPAB138001Constant.SPLY_ITEM_NUM));
            // QC#21170
            prInterFaceMap.put(NPAB138001Constant.ASL_VND_LT_DAYS_NUM, (BigDecimal) aslInfoMap.get(NPAB138001Constant.ASL_VND_LT_DAYS_NUM));
            // 2023/10/11 QC#61870 START
            prInterFaceMap.put(NPAB138001Constant.ASL_VND_SHIP_LT_DAYS_NUM, (BigDecimal) aslInfoMap.get(NPAB138001Constant.ASL_VND_SHIP_LT_DAYS_NUM));
            // 2023/10/11 QC#61870 END
        }

        return !isError;
    }

    /**
     * changePrice
     */
    private boolean changePrice(Map<String, Object> prInterFaceMap) {

        // get dealCcyCd
        String dealCcyCd = "";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB138001Constant.GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB138001Constant.VND_CD, (String) prInterFaceMap.get(NPAB138001Constant.VND_CD));

            preparedStatement = ssmLlcClient.createPreparedStatement("getDealCcyCd", paramMap);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // set DB result to Map
                dealCcyCd = resultSet.getString(NPAB138001Constant.DEAL_CCY_CD);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        if (!dealCcyCd.isEmpty()) {
            // change Price
            NPXC001001CurrencyConversion currencyConv = new NPXC001001CurrencyConversion();
            NPXC001001CurrencyConversionBean currencyData = currencyConv.convertCurrency((String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD),
                                                                                         dealCcyCd,
                                                                                         (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ASL_UNIT_PRC_AMT),
                                                                                          salesDate, null);
            prInterFaceMap.put(NPAB138001Constant.DEAL_CCY_CD, currencyData.getFuncCcyCd());
            prInterFaceMap.put(NPAB138001Constant.FUNC_AMT, currencyData.getFuncAmt());
            prInterFaceMap.put(NPAB138001Constant.EXCH_RATE, currencyData.getExchRate());
        }

        return true;
    }

    /**
     * changeQty
     */
    private boolean changeQty(Map<String, Object> prInterFaceMap) {

        boolean isError = false;

        // QC#20506 Add
        if (PO_QLFY.DROPSHIP.equals((String) prInterFaceMap.get(NPAB138001Constant.PO_QLFY_CD))) {
            prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_DPLY_QTY, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ORD_QTY));
            return true;
        }

        // get dealCcyCd by DS_VND
        NPZC129001PMsg pMsg = new NPZC129001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, (String) prInterFaceMap.get(NPAB138001Constant.PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, (String) prInterFaceMap.get(NPAB138001Constant.VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) prInterFaceMap.get(NPAB138001Constant.MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pMsg.poQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_QTY));
        ZYPEZDItemValueSetter.setValue(pMsg.adjQtyIncrFlg, ZYPConstant.FLG_ON_Y);
        if (PO_MDSE_CMPSN_TP.CHILD.equals((String) prInterFaceMap.get(NPAB138001Constant.PO_MDSE_CMPSN_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(pMsg.adjQtyIncrFlg, ZYPConstant.FLG_OFF_N);
        }

        new NPZC129001().execute(pMsg, ONBATCH_TYPE.BATCH);

        // QC#20506 Add
        if (ZYPConstant.FLG_OFF_N.equals(pMsg.xxWrnSkipFlg.getValue()) && S21ApiUtil.isXxMsgId(pMsg)) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                if (pMsg.xxMsgIdList.no(i).xxMsgId.getValue().endsWith("E")) {
                    String procErrMsgCd = (String) prInterFaceMap.get(NPAB138001Constant.PROC_ERR_MSG_CD);
                    if (!ZYPCommonFunc.hasValue(procErrMsgCd) || !procErrMsgCd.endsWith("E")) {
                        prInterFaceMap.put(NPAB138001Constant.PROC_ERR_MSG_CD, pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    }
                    isError = true;
                    // 02/14/2017 QC#5060 Delete.
//                    // QC:14075
//                    if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxWrnSkipFlg.getValue())) {
//                        BigDecimal poDispQty = (BigDecimal) pMsg.poDispQty_O1.getValue();
//                        poDispQty = poDispQty.setScale(0, BigDecimal.ROUND_DOWN);
//                        prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_DPLY_QTY, poDispQty);
//                        isError = false;
//                    } else {
//                        isError = true;
//                    }
                }
                addErrorMailDetail(prInterFaceMap, pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        } else {
            BigDecimal poDispQty = (BigDecimal) pMsg.poDispQty_O1.getValue();
            poDispQty = poDispQty.setScale(0, BigDecimal.ROUND_DOWN);
            prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_DPLY_QTY, poDispQty);
            // QC#29898 Add.
            BigDecimal poQty = (BigDecimal) pMsg.poQty_O1.getValue();
            poQty = poQty.setScale(0, BigDecimal.ROUND_DOWN);
            prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_QTY, poQty);
        }

        return !isError;

    }

    /**
     * sortPrInterFaceList
     */
    private void sortPrInterFaceList(List<Map<String, Object>> prInterFaceList) {

        Collections.sort(prInterFaceList, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                String key1 = "";
                String key2 = "";
                int comp = 0;

                for (int i = 0; i < divideGrpColumns.length; i++) {
                  key1 = String.valueOf(map1.get(divideGrpColumns[i]));
                  key2 = String.valueOf(map2.get(divideGrpColumns[i]));
                  comp = compareString(key1, key2);
                  if (comp != 0) {
                      return comp;
                  }
                }

                key1 = (String) map1.get(NPAB138001Constant.TRX_REF_NUM);
                key2 = (String) map2.get(NPAB138001Constant.TRX_REF_NUM);
                comp = compareString(key1, key2);
                if (comp != 0) {
                    return comp;
                }

                key1 = (String) map1.get(NPAB138001Constant.TRX_REF_LINE_NUM);
                key2 = (String) map2.get(NPAB138001Constant.TRX_REF_LINE_NUM);
                comp = compareString(key1, key2);
                if (comp != 0) {
                    return comp;
                }
                key1 = (String) map1.get(NPAB138001Constant.TRX_REF_LINE_SUB_NUM);
                key2 = (String) map2.get(NPAB138001Constant.TRX_REF_LINE_SUB_NUM);
                comp = compareString(key1, key2);
                if (comp != 0) {
                    return comp;
                }
                return 0;
            }

            private int compareString(String s1, String s2) {
                if (s1 == null && s2 == null) {
                    return 0;
                } else if (s1 == null) {
                    return -1;
                } else if (s2 == null) {
                    return 1;
                } else {
                    return s1.compareTo(s2);
                }
            }
        });
    }

    /**
     * updatePrAndPrInterface
     */
    private void updatePrAndPrInterface(List<Map<String, Object>> prInterFaceList) {

        int iPrchReqLineNum = 0;
        int iPrchReqLineSubNum = 0;
        // 2024/02/22 QC#62208 START
        int iPrchReqErrCnt = 1;
        // 2024/02/22 QC#62208 END
        String oldTrxRefLineNum = "";
        String oldPrchReqTpCd = "";
        String oldProcStsCd = "";
        NPZC103001PMsg npzc103001PMsg = new NPZC103001PMsg();

        int i = 0;
        for (; i < prInterFaceList.size(); i++) {

            Map<String, Object> prInterFaceMap = prInterFaceList.get(i);

            String procStsCd = (String) prInterFaceMap.get(NPAB138001Constant.PROC_STS_CD);
            String trxRefNum = (String) prInterFaceMap.get(NPAB138001Constant.TRX_REF_NUM);
            if (trxRefNum == null) {
                trxRefNum = "";
            }

            String trxRefLineNum = (String) prInterFaceMap.get(NPAB138001Constant.TRX_REF_LINE_NUM);
            if (trxRefLineNum == null) {
                trxRefLineNum = "";
            }

            String prchReqTpCd = (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD);
            if (prchReqTpCd == null) {
                prchReqTpCd = "";
            }

            String poMdseCmpsnTpCd = (String) prInterFaceMap.get(NPAB138001Constant.PO_MDSE_CMPSN_TP_CD);

            if (prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_REC_TP_CD) == null && prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD) == null) {
                // Set PRCH_REQ_REC_TP_CD / PRCH_REQ_TP_CD
                PROCR_TPTMsg procrTpTmsg = new PROCR_TPTMsg();
                ZYPEZDItemValueSetter.setValue(procrTpTmsg.glblCmpyCd, (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(procrTpTmsg.procrTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PROCR_TP_CD));
                procrTpTmsg = (PROCR_TPTMsg) EZDTBLAccessor.findByKey(procrTpTmsg);
                if (procrTpTmsg != null) {
                    prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_REC_TP_CD, procrTpTmsg.prchReqRecTpCd.getValue());
                    prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_TP_CD, procrTpTmsg.prchReqTpCd.getValue());
                }
            }

            if (prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_LINE_TP_CD) == null) {
                // Set PRCH_REQ_LINE_TP_CD
                PRCH_REQ_TPTMsg prchReqTpTmsg = new PRCH_REQ_TPTMsg();
                ZYPEZDItemValueSetter.setValue(prchReqTpTmsg.glblCmpyCd, (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(prchReqTpTmsg.prchReqTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD));
                prchReqTpTmsg = (PRCH_REQ_TPTMsg) EZDTBLAccessor.findByKey(prchReqTpTmsg);
                if (prchReqTpTmsg != null) {
                    prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_LINE_TP_CD, prchReqTpTmsg.defPrchReqLineTpCd.getValue());
                }
            }

            if (i == 0 || !isSameGroup(prInterFaceList, i) || PRCH_REQ_TP.KITTING.equals(oldPrchReqTpCd) || PROC_STS.ERROR.equals(oldProcStsCd)) {
                // 2024/02/22 QC#62208 START
                if (i > 0 && (npzc103001PMsg.prchReqInfo.length() > iPrchReqErrCnt)) {
                // 2024/02/22 QC#62208 END
                    if (PROC_STS.ERROR.equals(oldProcStsCd)) {
                         updateErrorPrInterface(prInterFaceList.get(i - 1));
                         commit();
                    } else {
                        if (!executePrUpdateApi(npzc103001PMsg)) {
                            rollback();
                        }
                        updatePrInterface(prInterFaceList, i, npzc103001PMsg);
                        commit();
                    }
                }

                // set next group head
                npzc103001PMsg = new NPZC103001PMsg();
                setPrUpdateApiHead(npzc103001PMsg, prInterFaceMap);
                iPrchReqLineNum = 1;
                iPrchReqLineSubNum = 0;
            } else {
                // 2024/02/22 QC#62208 START
                if (!oldTrxRefLineNum.equals(trxRefLineNum)) {
                    iPrchReqLineNum++;
                    iPrchReqLineSubNum = 0;
                    iPrchReqErrCnt++;
                } else if (!PO_MDSE_CMPSN_TP.CHILD.equals(poMdseCmpsnTpCd)) {
                    iPrchReqLineNum++;
                    iPrchReqLineSubNum = 0;
                    iPrchReqErrCnt++;
                } else {
                    iPrchReqLineSubNum++;
                    iPrchReqErrCnt++;
                }
                // 2024/02/22 QC#62208 END
            }
            prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_LINE_NUM, String.format("%03d", iPrchReqLineNum));
            prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_LINE_SUB_NUM, BigDecimal.valueOf(iPrchReqLineSubNum));

            // 2024/02/22 QC#62208 START
            if (npzc103001PMsg.prchReqInfo.length() < iPrchReqErrCnt) {
                if (iPrchReqLineNum == 1) {
                    updateErrorPrInterfaceTBLAccessor(prInterFaceList, i , iPrchReqErrCnt);
                    commit();
                    setPrUpdateApiDtl(npzc103001PMsg, prInterFaceMap);
                }
            } else {
               setPrUpdateApiDtl(npzc103001PMsg, prInterFaceMap);
            }

            if (i == 0 || !isSameGroup(prInterFaceList, i) || PRCH_REQ_TP.KITTING.equals(oldPrchReqTpCd) || PROC_STS.ERROR.equals(oldProcStsCd)) {
                iPrchReqErrCnt = 1;
            }
            // 2024/02/22 QC#62208 END

            oldTrxRefLineNum = trxRefLineNum;
            oldPrchReqTpCd = prchReqTpCd;
            oldProcStsCd = procStsCd;
        }
        // prInterFaceList Loop End

        //Last Record
        if (prInterFaceList != null & prInterFaceList.size() > 0) {
            if (PROC_STS.ERROR.equals(oldProcStsCd)) {
                updateErrorPrInterface(prInterFaceList.get(i - 1));
            // 2024/02/22 QC#62208 START
            } else if (npzc103001PMsg.prchReqInfo.length() < iPrchReqErrCnt) {
                updateErrorPrInterfaceTBLAccessor(prInterFaceList, i , iPrchReqErrCnt);
            // 2024/02/22 QC#62208 END
            } else {
                if (!executePrUpdateApi(npzc103001PMsg)) {
                    rollback();
                }
                updatePrInterface(prInterFaceList, i, npzc103001PMsg);
            }
            commit();
        }
    }

    private boolean isSameGroup(List<Map<String, Object>> prInterFaceList, int index) {

        if (index == 0) {
            return true;
        }

        for (int i = 0; i < divideGrpColumns.length; i++) {
            Object oldValue = prInterFaceList.get(index - 1).get(divideGrpColumns[i]);
            Object newValue = prInterFaceList.get(index).get(divideGrpColumns[i]);

            if (oldValue == null && newValue == null) {
                continue;
            } else if (oldValue.equals(newValue)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * setPrUpdateApiHead
     * @param param NPZC103001PMsg
     */
    private void setPrUpdateApiHead(NPZC103001PMsg param, Map<String, Object> prInterFaceMap) {

        if (prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_REC_TP_CD) == null
            && prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD) == null) {
            // Set PRCH_REQ_REC_TP_CD / PRCH_REQ_TP_CD
            PROCR_TPTMsg procrTpTmsg = new PROCR_TPTMsg();
            ZYPEZDItemValueSetter.setValue(procrTpTmsg.glblCmpyCd, (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(procrTpTmsg.procrTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PROCR_TP_CD));
            procrTpTmsg = (PROCR_TPTMsg) EZDTBLAccessor.findByKey(procrTpTmsg);
            if (procrTpTmsg != null) {
                prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_REC_TP_CD, procrTpTmsg.prchReqRecTpCd.getValue());
                prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_TP_CD, procrTpTmsg.prchReqTpCd.getValue());
            }
        }

        if (prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_LINE_TP_CD) == null) {
            // Set PRCH_REQ_LINE_TP_CD
            PRCH_REQ_TPTMsg prchReqTpTmsg = new PRCH_REQ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(prchReqTpTmsg.glblCmpyCd, (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(prchReqTpTmsg.prchReqTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD));
            prchReqTpTmsg = (PRCH_REQ_TPTMsg) EZDTBLAccessor.findByKey(prchReqTpTmsg);
            if (prchReqTpTmsg != null) {
                prInterFaceMap.put(NPAB138001Constant.PRCH_REQ_LINE_TP_CD, prchReqTpTmsg.defPrchReqLineTpCd.getValue());
            }
        }

        ZYPEZDItemValueSetter.setValue(param.xxModeCd, NPZC103001Constant.MODE_CREATE);
        ZYPEZDItemValueSetter.setValue(param.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(param.procDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.prchReqRecTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_REC_TP_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqCratByPsnCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_CRAT_BY_PSN_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqRqstByPsnCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_CRAT_BY_PSN_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqSrcTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_SRC_TP_CD));
        ZYPEZDItemValueSetter.setValue(param.prchGrpCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_GRP_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqApvlStsCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_APVL_STS_CD));
        ZYPEZDItemValueSetter.setValue(param.fsrNum, (String) prInterFaceMap.get(NPAB138001Constant.FSR_NUM));
        ZYPEZDItemValueSetter.setValue(param.svcTaskNum, (String) prInterFaceMap.get(NPAB138001Constant.SVC_TASK_NUM));
        ZYPEZDItemValueSetter.setValue(param.svcMachSerNum, (String) prInterFaceMap.get(NPAB138001Constant.SVC_MACH_SER_NUM));
        ZYPEZDItemValueSetter.setValue(param.cpoOrdNum, (String) prInterFaceMap.get(NPAB138001Constant.CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(param.custIssPoNum, (String) prInterFaceMap.get(NPAB138001Constant.CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(param.custIssPoDt, (String) prInterFaceMap.get(NPAB138001Constant.CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(param.cpoOrdTpCd, (String) prInterFaceMap.get(NPAB138001Constant.CPO_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(param.rqstTechTocCd, (String) prInterFaceMap.get(NPAB138001Constant.RQST_TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(param.shipToCustCd, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(param.shipToLocNm, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(param.shipToAddlLocNm, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(param.shipToFirstLineAddr, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToScdLineAddr, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToThirdLineAddr, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToFrthLineAddr, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToFirstRefCmntTxt, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(param.shipToScdRefCmntTxt, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(param.shipToCtyAddr, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(param.shipToStCd, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(param.shipToProvNm, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(param.shipToCntyNm, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(param.shipToPostCd, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(param.shipToCtryCd, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(param.billToCustCd, (String) prInterFaceMap.get(NPAB138001Constant.BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(param.sellToCustCd, (String) prInterFaceMap.get(NPAB138001Constant.SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqCmntTxt, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(param.delyAddlCmntTxt, (String) prInterFaceMap.get(NPAB138001Constant.DELY_ADDL_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(param.rcvAddlCmntTxt, (String) prInterFaceMap.get(NPAB138001Constant.RCV_ADDL_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(param.poQlfyCd, (String) prInterFaceMap.get(NPAB138001Constant.PO_QLFY_CD));
        //08/21/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        ZYPEZDItemValueSetter.setValue(param.mrpPlnNm, (String) prInterFaceMap.get(NPAB138001Constant.MRP_PLN_NM));
        //08/21/2017 CITS H.Naoi Add Sol#097(QC#18398) END

        ZYPEZDItemValueSetter.setValue(param.ctacPsnNm, (String) prInterFaceMap.get(NPAB138001Constant.CTAC_PSN_NM));
        ZYPEZDItemValueSetter.setValue(param.adminPsnCd, (String) prInterFaceMap.get(NPAB138001Constant.ADMIN_PSN_CD));

        ZYPEZDItemValueSetter.setValue(param.lineBizTpCd, (String) prInterFaceMap.get(NPAB138001Constant.LINE_BIZ_TP_CD));

    }

    /**
     * setPrUpdateApiDtl
     * @param param NPZC103001PMsg
     */
    private void setPrUpdateApiDtl(NPZC103001PMsg param, Map<String, Object> prInterFaceMap) {

        int iDtlNum = param.prchReqInfo.getValidCount();
        int iSerNum = param.serNumInfo.getValidCount();

        String prchReqApvlStsCd = (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_APVL_STS_CD);
        if (PRCH_REQ_APVL_STS.ENTERED.equals(prchReqApvlStsCd)) {
            ZYPEZDItemValueSetter.setValue(param.prchReqApvlStsCd, prchReqApvlStsCd);
        }

        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqIntfcPk, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_INTFC_PK));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqLineNum, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqLineSubNum, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_LINE_SUB_NUM));
        if (prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_LINE_SUB_NUM) == null) {
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqLineSubNum, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqLineTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_LINE_TP_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).shpgPlnNum, (String) prInterFaceMap.get(NPAB138001Constant.SHPG_PLN_NUM));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).shipFromSoNum, (String) prInterFaceMap.get(NPAB138001Constant.SHIP_FROM_SO_NUM));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).procrTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PROCR_TP_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).shpgSvcLvlCd, (String) prInterFaceMap.get(NPAB138001Constant.SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).svcConfigMstrPk, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).frtChrgToCd, (String) prInterFaceMap.get(NPAB138001Constant.FRT_CHRG_TO_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).frtChrgMethCd, (String) prInterFaceMap.get(NPAB138001Constant.FRT_CHRG_METH_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).frtCondCd, (String) prInterFaceMap.get(NPAB138001Constant.FRT_COND_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).carrCd, (String) prInterFaceMap.get(NPAB138001Constant.CARR_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).carrAcctNum, (String) prInterFaceMap.get(NPAB138001Constant.CARR_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).destInvtyLocCd, (String) prInterFaceMap.get(NPAB138001Constant.DEST_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).srcInvtyLocCd, (String) prInterFaceMap.get(NPAB138001Constant.SRC_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prntVndCd, (String) prInterFaceMap.get(NPAB138001Constant.PRNT_VND_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).vndCd, (String) prInterFaceMap.get(NPAB138001Constant.VND_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).vndInvtyLocCd, (String) prInterFaceMap.get(NPAB138001Constant.VND_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).mdseCd, (String) prInterFaceMap.get(NPAB138001Constant.MDSE_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).aslMdseCd, (String) prInterFaceMap.get(NPAB138001Constant.ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_QTY));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).ordQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ORD_QTY));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).custUomCd, (String) prInterFaceMap.get(NPAB138001Constant.CUST_UOM_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqDispQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_DPLY_QTY));
        if (prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_DPLY_QTY) == null || BigDecimal.ZERO.compareTo((BigDecimal) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_DPLY_QTY)) == 0) {
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqDispQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_QTY));
        }
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqDsplUomCd, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_DSPL_UOM));
        if (prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_DSPL_UOM) == null) {
            String baseUom = getBaseUom((String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD), (String) prInterFaceMap.get(NPAB138001Constant.MDSE_CD));
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqDsplUomCd, baseUom);
        }
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).ropQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ROP_QTY));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).minOrdQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.MIN_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).incrOrdQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.INCR_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).maxInvtyQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.MAX_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).curInvtyQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.CUR_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).curInvtyAvalQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.CUR_INVTY_AVAL_QTY));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).schdInbdQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.SCHD_INBD_QTY));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).schdOtbdQty, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.SCHD_OTBD_QTY));

        if (PROCR_TP.SUPPLIER.equals(param.prchReqInfo.no(iDtlNum).procrTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).fromStkStsCd, (String) prInterFaceMap.get(NPAB138001Constant.FROM_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).toStkStsCd, (String) prInterFaceMap.get(NPAB138001Constant.TO_STK_STS_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).fromStkStsCd, STK_STS.GOOD);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).toStkStsCd, STK_STS.GOOD);
        }

        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).aslDtlPk, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ASL_DTL_PK));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).aslUnitPrcAmt, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ASL_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).entDealNetUnitPrcAmt, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ASL_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).entFuncNetUnitPrcAmt, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.FUNC_AMT));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).ccyCd, (String) prInterFaceMap.get(NPAB138001Constant.DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).exchRate, (BigDecimal) prInterFaceMap.get(NPAB138001Constant.EXCH_RATE));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).trxRefNum, (String) prInterFaceMap.get(NPAB138001Constant.TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).trxRefLineNum, (String) prInterFaceMap.get(NPAB138001Constant.TRX_REF_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).trxRefLineSubNum, (String) prInterFaceMap.get(NPAB138001Constant.TRX_REF_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).prchReqLineCmntTxt, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_LINE_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).poSchdRelDt, (String) prInterFaceMap.get(NPAB138001Constant.PO_SCHD_REL_DT));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).rqstRcvDt, (String) prInterFaceMap.get(NPAB138001Constant.RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).rqstRcvTm, (String) prInterFaceMap.get(NPAB138001Constant.RQST_RCV_TM));
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).poMdseCmpsnTpCd, (String) prInterFaceMap.get(NPAB138001Constant.PO_MDSE_CMPSN_TP_CD));
        // 2023/10/11 QC#61870 START
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).rqstShipDt, (String) prInterFaceMap.get(NPAB138001Constant.RQST_SHIP_DT));
        // 2023/10/11 QC#61870 END

        // QC#15983 add start
        if (PRCH_REQ_SRC_TP.WH_PLANNING.equals(param.prchReqSrcTpCd.getValue())) {
            setAccountInfo(param, iDtlNum, prInterFaceMap);
        }
        // QC#15983 add end

        param.prchReqInfo.setValidCount(iDtlNum + 1);

        String serNum = (String) prInterFaceMap.get(NPAB138001Constant.SER_NUM);
        if (ZYPCommonFunc.hasValue(serNum)) {
            ZYPEZDItemValueSetter.setValue(param.serNumInfo.no(iSerNum).prchReqLineNum, param.prchReqInfo.no(iDtlNum).prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(param.serNumInfo.no(iSerNum).prchReqLineSubNum, param.prchReqInfo.no(iDtlNum).prchReqLineSubNum);
            ZYPEZDItemValueSetter.setValue(param.serNumInfo.no(iSerNum).serNum, serNum);
            param.serNumInfo.setValidCount(iSerNum + 1);
        }

        // QC#21209
        ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).spclInstnCmntTxt, (String) prInterFaceMap.get(NPAB138001Constant.SPCL_INSTN_CMNT_TXT));
    }

    // QC#15983 add start
    /**
     * setAccountInfo
     * get and set 9 segment info when Warehouse Planning data
     * this logic is same to NPBL0020 Inventory Request Entry
     * @param param NPZC103001PMsg
     * @param iDtlNum int
     * @param prInterFaceMap Map<String, Object>
     */
    private void setAccountInfo(NPZC103001PMsg param, int iDtlNum, Map<String, Object> prInterFaceMap) {

        // get default segment info
        DEF_DPLY_COA_INFOTMsg inTMsg = new DEF_DPLY_COA_INFOTMsg();
        inTMsg.glblCmpyCd.setValue(globalCompanyCode);
        inTMsg.appFuncId.setValue(NPAB138001Constant.NPBL0020_ID + (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD));
         DEF_DPLY_COA_INFOTMsg tMsg = (DEF_DPLY_COA_INFOTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (tMsg == null) {
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaAfflCd, NPAB138001Constant.DEF_COA_AFFL_CD);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaBrCd, NPAB138001Constant.DEF_COA_BR_CD);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaChCd, NPAB138001Constant.DEF_COA_CH_CD);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaCcCd, NPAB138001Constant.DEF_COA_CC_CD);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaAcctCd, NPAB138001Constant.DEF_COA_ACCT_CD);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaProdCd, NPAB138001Constant.DEF_COA_PROD_CD);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaProjCd, NPAB138001Constant.DEF_COA_PROJ_CD);
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaExtnCd, NPAB138001Constant.DEF_COA_EXTN_CD);
        } else {
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaCmpyCd, tMsg.coaCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaAfflCd, tMsg.coaAfflCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaBrCd, tMsg.coaBrCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaChCd, tMsg.coaChCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaCcCd, tMsg.coaCcCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaAcctCd, tMsg.coaAcctCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaProdCd, tMsg.coaProdCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaProjCd, tMsg.coaProjCd.getValue());
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaExtnCd, tMsg.coaExtnCd.getValue());
        }

        // get account info
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPAB138001Constant.GLBL_CMPY_CD, globalCompanyCode);
        queryParam.put(NPAB138001Constant.DEST_RTL_WH_CD, (String) prInterFaceMap.get(NPAB138001Constant.DEST_RTL_WH_CD));
        queryParam.put(NPAB138001Constant.MDSE_CD, (String) prInterFaceMap.get(NPAB138001Constant.MDSE_CD));
        queryParam.put(NPAB138001Constant.PRCH_REQ_TP_CD, (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD));
        Map<String, Object> accountInfo = (Map<String, Object>) ssmBatchClient.queryObject(NPAB138001Constant.GET_ACCOUNT, queryParam);
        if (accountInfo != null) {
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaBrCd, (String) accountInfo.get(NPAB138001Constant.COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(param.prchReqInfo.no(iDtlNum).coaAcctCd, (String) accountInfo.get(NPAB138001Constant.COA_ACCT_CD));
        }

    }
    // QC#15983 add end

    /**
     * executePrUpdateApi
     * @param param NPZC103001PMsg
     */
    private boolean executePrUpdateApi(NPZC103001PMsg param) {
        // 2023/10/11 QC#61870 START
        String maxDt = param.procDt.getValue();
        for (int i = 0; i < param.prchReqInfo.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(param.prchReqInfo.no(i).rqstShipDt)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(param.prchReqInfo.no(i).rqstShipDt.getValue()) && ZYPCommonFunc.hasValue(maxDt)) {
                if (new BigDecimal(param.prchReqInfo.no(i).rqstShipDt.getValue()).compareTo(new BigDecimal(maxDt)) > 0) {
                    ZYPEZDItemValueSetter.setValue(param.rqstShipDt, param.prchReqInfo.no(i).rqstShipDt.getValue());
                }
            }
        }
        // 2023/10/11 QC#61870 END

        new NPZC103001().execute(param, ONBATCH_TYPE.BATCH);
        boolean isError = false;
        if (S21ApiUtil.isXxMsgId(param)) {
            for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
                if (param.xxMsgIdList.no(i).xxMsgId.getValue().endsWith("E")) {
                    isError = true;
                }
            }
            addErrorMailDetail(param);
        }
        return !isError;
    }

    /**
     * get DS_MDSE_STORE_PKG.BASE_PKG_UOM_CD
     * @param glblCmpyCd String
     * @param mdseCd String
     */
    private String getBaseUom(String glblCmpyCd, String mdseCd) {

        String baseUom = "";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB138001Constant.GLBL_CMPY_CD, glblCmpyCd);
            paramMap.put(NPAB138001Constant.MDSE_CD, mdseCd);
            paramMap.put(NPAB138001Constant.PRIM_PKG_UOM_FLG, ZYPConstant.FLG_ON_Y);

            preparedStatement = ssmLlcClient.createPreparedStatement("getBaseUom", paramMap);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // set DB result to Map
                baseUom = resultSet.getString(NPAB138001Constant.BASE_PKG_UOM_CD);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }

        return baseUom;
    }

    /**
     * updatePrInterface
     */
    private void updatePrInterface(List<Map<String, Object>> prInterFaceList, int idx, NPZC103001PMsg para) {
        int prInfoSize = para.prchReqInfo.getValidCount();
        idx = idx - prInfoSize;

        // loop PR Detail row count
        for (int i = 0; i < prInfoSize; i++) {

            // set PrInterface param
            // pick up 1st err msg and set err msg
            if (S21ApiUtil.isXxMsgId(para)) {
                prInterFaceList.get(idx).put(NPAB138001Constant.PROC_STS_CD, PROC_STS.ERROR);
                prInterFaceList.get(idx).put(NPAB138001Constant.PROC_ERR_MSG_CD, para.xxMsgIdList.no(0).xxMsgId.getValue());
                prInterFaceList.get(idx).put(NPAB138001Constant.INTFC_ERR_MSG_TXT, S21MessageFunc.clspGetMessage(para.xxMsgIdList.no(0).xxMsgId.getValue()));
                prInterFaceList.get(idx).put(NPAB138001Constant.PRCH_REQ_NUM, "");
                prInterFaceList.get(idx).put(NPAB138001Constant.PRCH_REQ_LINE_NUM, "");
                prInterFaceList.get(idx).put(NPAB138001Constant.PRCH_REQ_LINE_SUB_NUM, null);

            } else {
                prInterFaceList.get(idx).put(NPAB138001Constant.PRCH_REQ_NUM, para.prchReqNum.getValue());
                prInterFaceList.get(idx).put(NPAB138001Constant.PRCH_REQ_LINE_NUM, para.prchReqInfo.no(i).prchReqLineNum.getValue());
                prInterFaceList.get(idx).put(NPAB138001Constant.PRCH_REQ_LINE_SUB_NUM, para.prchReqInfo.no(i).prchReqLineSubNum.getValue());
            }

            // select PrInterface for update
            PRCH_REQ_INTFCTMsg prchReqIntfcTMsg = new PRCH_REQ_INTFCTMsg();
            ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqIntfcPk, (BigDecimal) prInterFaceList.get(idx).get(NPAB138001Constant.PRCH_REQ_INTFC_PK));
            prchReqIntfcTMsg = (PRCH_REQ_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdate(prchReqIntfcTMsg);
            // QC#50850
            if (prchReqIntfcTMsg != null && ZYPCommonFunc.hasValue(prchReqIntfcTMsg.prchReqNum)) {
                continue;
            }
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqIntfcTMsg.getReturnCode())) {
                return;
            }

            // update PrInterface
            ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.procStsCd, (String) prInterFaceList.get(idx).get(NPAB138001Constant.PROC_STS_CD));
            ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqNum, (String) prInterFaceList.get(idx).get(NPAB138001Constant.PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqLineNum, (String) prInterFaceList.get(idx).get(NPAB138001Constant.PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqLineSubNum, (BigDecimal) prInterFaceList.get(idx).get(NPAB138001Constant.PRCH_REQ_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.procErrMsgCd, (String) prInterFaceList.get(idx).get(NPAB138001Constant.PROC_ERR_MSG_CD));
            String intfcErrMsgTxt = (String) prInterFaceList.get(idx).get(NPAB138001Constant.INTFC_ERR_MSG_TXT);
            if (ZYPCommonFunc.hasValue(intfcErrMsgTxt) && intfcErrMsgTxt.length() > NPAB138001Constant.SIZE_400) {
                intfcErrMsgTxt = intfcErrMsgTxt.substring(0, NPAB138001Constant.SIZE_400);
            }
            ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.intfcErrMsgTxt, intfcErrMsgTxt);
            EZDTBLAccessor.update(prchReqIntfcTMsg);
            idx++;
        }
    }

    /**
     * updateErrorPrInterface
     */
    private void updateErrorPrInterface(Map<String, Object> map) {
        // select PrInterface for update
        PRCH_REQ_INTFCTMsg prchReqIntfcTMsg = new PRCH_REQ_INTFCTMsg();
        BigDecimal prchReqIntfcPk = (BigDecimal) map.get(NPAB138001Constant.PRCH_REQ_INTFC_PK);
        ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqIntfcPk, prchReqIntfcPk);
        prchReqIntfcTMsg = (PRCH_REQ_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdate(prchReqIntfcTMsg);

        // QC#21275
        if (prchReqIntfcTMsg == null) {
            S21InfoLogOutput.println(NPAB138001Constant.NPAM1171E, new String[] {"PRCH_REQ_INTFC" });
            if (map != null) {
                S21InfoLogOutput.println(NPAB138001Constant.NPAM1171E, new String[] {map.toString() });
            }
            return;
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqIntfcTMsg.getReturnCode())) {
            return;
        }

        String procStsCd = (String) map.get(NPAB138001Constant.PROC_STS_CD);
        String procErrMsgCd = (String) map.get(NPAB138001Constant.PROC_ERR_MSG_CD);
        String intfcErrMsgTxt = (String) map.get(NPAB138001Constant.INTFC_ERR_MSG_TXT);

        // update PrInterface
        if (PROC_STS.ERROR.equals(procStsCd)) {
            if (!ZYPCommonFunc.hasValue(procErrMsgCd)) {
                procErrMsgCd = NPAB138001Constant.NPZM0200E;
            }
        }

        intfcErrMsgTxt = S21MessageFunc.clspGetMessage(procErrMsgCd);
        if (ZYPCommonFunc.hasValue(intfcErrMsgTxt) && intfcErrMsgTxt.length() > NPAB138001Constant.SIZE_400) {
            intfcErrMsgTxt = intfcErrMsgTxt.substring(0, NPAB138001Constant.SIZE_400);
        }
        prchReqIntfcTMsg.prchReqNum.clear();
        prchReqIntfcTMsg.prchReqLineNum.clear();
        prchReqIntfcTMsg.prchReqLineSubNum.clear();
        ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.intfcErrMsgTxt, intfcErrMsgTxt);
        ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.procStsCd, procStsCd);
        ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.procErrMsgCd, procErrMsgCd);
        ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.intfcErrMsgTxt, intfcErrMsgTxt);
        EZDTBLAccessor.update(prchReqIntfcTMsg);
    }

   // 2024/02/22 QC#62208 START
   /**
    * updateErrorPrInterface
    */
   private void updateErrorPrInterfaceTBLAccessor(List<Map<String, Object>> prInterFaceList , int idx, int iPrchReqErrCnt) {
       EZDTMsg[] tmsgs = new EZDTMsg[iPrchReqErrCnt];

       idx = idx - iPrchReqErrCnt;
       int errRec = 0;

       int i = 0;
       for (; i < iPrchReqErrCnt; i++) {
           PRCH_REQ_INTFCTMsg prchReqIntfcTMsg = new PRCH_REQ_INTFCTMsg();
           Map<String, Object> maxNumPrInterFaceMap = new HashMap<String, Object>();
           maxNumPrInterFaceMap = prInterFaceList.get(idx);
           BigDecimal prchReqIntfcPk = (BigDecimal) maxNumPrInterFaceMap.get(NPAB138001Constant.PRCH_REQ_INTFC_PK);
           ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.glblCmpyCd, globalCompanyCode);
           ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqIntfcPk, prchReqIntfcPk);
           prchReqIntfcTMsg = (PRCH_REQ_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdate(prchReqIntfcTMsg);

           String procStsCd = (String) maxNumPrInterFaceMap.get(NPAB138001Constant.PROC_STS_CD);
           String procErrMsgCd = (String) maxNumPrInterFaceMap.get(NPAB138001Constant.PROC_ERR_MSG_CD);
           String intfcErrMsgTxt = (String) maxNumPrInterFaceMap.get(NPAB138001Constant.INTFC_ERR_MSG_TXT);

            // QC#21275
           if (prchReqIntfcTMsg == null) {
               S21InfoLogOutput.println(NPAB138001Constant.NPAM1171E, new String[] {"PRCH_REQ_INTFC" });
               if (maxNumPrInterFaceMap != null) {
                   S21InfoLogOutput.println(NPAB138001Constant.NPAM1171E, new String[] {maxNumPrInterFaceMap.toString() });
               }
               return;
           }

           if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqIntfcTMsg.getReturnCode())) {
               return;
           }

           // update PrInterface
           if (PROC_STS.ERROR.equals(procStsCd)) {
               if (!ZYPCommonFunc.hasValue(procErrMsgCd)) {
                   procErrMsgCd = NPAB138001Constant.NPZM0200E;
               }
           } else {
               procStsCd = PROC_STS.ERROR;
               procErrMsgCd = NPAB138001Constant.NPAM1661E;
           }

           if (ZYPCommonFunc.hasValue((String) maxNumPrInterFaceMap.get(NPAB138001Constant.MRP_PLN_NM))) {
               intfcErrMsgTxt = S21MessageFunc.clspGetMessage(procErrMsgCd, new String[]{NPAB138001Constant.PRCH_REQ_LENGTH, (String) maxNumPrInterFaceMap.get(NPAB138001Constant.MRP_PLN_NM)});
           } else {
               intfcErrMsgTxt = S21MessageFunc.clspGetMessage(procErrMsgCd, new String[]{NPAB138001Constant.PRCH_REQ_LENGTH, (String) maxNumPrInterFaceMap.get(NPAB138001Constant.CPO_ORD_NUM)});
           }
           if (ZYPCommonFunc.hasValue(intfcErrMsgTxt) && intfcErrMsgTxt.length() > NPAB138001Constant.SIZE_400) {
               intfcErrMsgTxt = intfcErrMsgTxt.substring(0, NPAB138001Constant.SIZE_400);
           }

           prchReqIntfcTMsg.prchReqNum.clear();
           prchReqIntfcTMsg.prchReqLineNum.clear();
           prchReqIntfcTMsg.prchReqLineSubNum.clear();
           ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.procStsCd, procStsCd);
           ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.procErrMsgCd, procErrMsgCd);
           ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.intfcErrMsgTxt, intfcErrMsgTxt);

           tmsgs[errRec] = prchReqIntfcTMsg;
           errRec++;
           idx++;

           if (iPrchReqErrCnt == errRec) {
               // bulk update
               S21FastTBLAccessor.update(tmsgs);
               errorCount = errorCount + iPrchReqErrCnt;
               maxNumPrInterFaceMap.put(NPAB138001Constant.TRX_REF_LINE_NUM, null);
               maxNumPrInterFaceMap.put(NPAB138001Constant.TRX_REF_LINE_SUB_NUM, null);
               maxNumPrInterFaceMap.put(NPAB138001Constant.MDSE_CD, null);
               maxNumPrInterFaceMap.put(NPAB138001Constant.PRCH_REQ_QTY, "");

               addErrorMailDetail(maxNumPrInterFaceMap, intfcErrMsgTxt);
               if (ZYPCommonFunc.hasValue((String) maxNumPrInterFaceMap.get(NPAB138001Constant.MRP_PLN_NM))) {
                   S21InfoLogOutput.println(NPAB138001Constant.NPAM1661E, new String[] {NPAB138001Constant.PRCH_REQ_LENGTH, (String) maxNumPrInterFaceMap.get(NPAB138001Constant.MRP_PLN_NM)});
               } else {
                   S21InfoLogOutput.println(NPAB138001Constant.NPAM1661E, new String[] {NPAB138001Constant.PRCH_REQ_LENGTH, (String) maxNumPrInterFaceMap.get(NPAB138001Constant.CPO_ORD_NUM)});
               }
           }
       }
   }
   // 2024/02/22 QC#62208 END

    /**
     * Send Error Mail.
     */
    private void sendErrorMail() {

        String errDate = ZYPDateUtil.getCurrentSystemTime(NPAB138001Constant.MAIL_DATE_TIME_FORMAT);

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.globalCompanyCode, NPAB138001Constant.MAIL_GROUP_ID_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NPAB138001Constant.NPAM1478E, new String[] {"FROM mail-address.", (NPAB138001Constant.MAIL_GROUP_ID_FROM + "/" + NPAB138001Constant.MAIL_GROUP_KEY_FROM) });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(globalCompanyCode, NPAB138001Constant.MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NPAB138001Constant.NPAM1478E, new String[] {"TO mail-address.", NPAB138001Constant.MAIL_GROUP_ID_TO });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, NPAB138001Constant.MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NPAB138001Constant.NPAM1331E, new String[] {NPAB138001Constant.MAIL_TEMPLATE_ID_01 });
        }

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(globalCompanyCode);

        // Set From Mail Address.
        mail.setFromAddress(fromAddress);
        // Set To Mail Address.
        mail.setToAddressList(addrToList);

        // Set Parameter
        template.setTemplateParameter(NPAB138001Constant.MAIL_ITEM_BATCH_ID, NPAB138001Constant.BATCH_PROGRAM_ID);
        template.setTemplateParameter(NPAB138001Constant.MAIL_ITEM_ERR_DATE, errDate);
        template.setTemplateParameter(NPAB138001Constant.MAIL_ITEM_ERR_MSG, errMailDetail.toString());
        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }

    /**
     * Send Error Mail to Technician
     * QC#18689-Sol#303
     */
    private void sendErrorMailToTech() {

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.globalCompanyCode, NPAB138001Constant.MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NPAB138001Constant.NPAM1478E, new String[] {"FROM mail-address.", (NPAB138001Constant.MAIL_GROUP_ID_FROM + "/" + NPAB138001Constant.MAIL_GROUP_KEY_FROM) });
        }

        S21MailAddress fromAddress = addrFromList.get(0);

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, NPAB138001Constant.MAIL_TEMPLATE_ID_02);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NPAB138001Constant.NPAM1331E, new String[] {NPAB138001Constant.MAIL_TEMPLATE_ID_02 });
        }

        for (Map<String, String> techMail : techErrMailList) {
            S21MailAddress toAddress = new S21MailAddress(globalCompanyCode, techMail.get(NPAB138001Constant.TECH_MAIL_ADDR));

            S21Mail mail = new S21Mail(globalCompanyCode);

            // Set From Mail Address.
            mail.setFromAddress(fromAddress);
            // Set To Mail Address.
            mail.setToAddress(toAddress);

            // Set Parameter
            template.setTemplateParameter(NPAB138001Constant.MAIL_ITEM_BATCH_ID, NPAB138001Constant.BATCH_PROGRAM_ID);
            template.setTemplateParameter(NPAB138001Constant.MAIL_ITEM_TRX_REF_NUM, techMail.get(NPAB138001Constant.TECH_MAIL_TRX_REF_NUM));
            template.setTemplateParameter(NPAB138001Constant.MAIL_ITEM_ERR_MSG, techMail.get(NPAB138001Constant.TECH_MAIL_ERR_MSG));

            mail.setMailTemplate(template);
            mail.setSubject(template.getSubject());
            mail.postMail();
        }
    }

    /**
     * Add Error Mail Detail
     */
    private void addErrorMailDetail(Map<String, Object> prInterFaceMap, String msgId) {

        if (errMailDetail == null) {
            addErrorMailHeader();
        }
        String prchReqSrcTpCd = (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_SRC_TP_CD);
        String prchReqSrcTpDescTxt = null;
        if (ZYPCommonFunc.hasValue(prchReqSrcTpCd)) {
            prchReqSrcTpDescTxt = ZYPCodeDataUtil.getName(PRCH_REQ_SRC_TP.class, globalCompanyCode, prchReqSrcTpCd);
        }

        errMailDetail.append(formatString(prchReqSrcTpDescTxt, NPAB138001Constant.MAIL_DETAIL_LENGTH[0], NPAB138001Constant.ALIGN_LEFT));
        errMailDetail.append(formatString((String) prInterFaceMap.get(NPAB138001Constant.TRX_REF_NUM), NPAB138001Constant.MAIL_DETAIL_LENGTH[1], NPAB138001Constant.ALIGN_LEFT));
        errMailDetail.append(formatString((String) prInterFaceMap.get(NPAB138001Constant.TRX_REF_LINE_NUM), NPAB138001Constant.MAIL_DETAIL_LENGTH[2], NPAB138001Constant.ALIGN_LEFT));
        errMailDetail.append(formatString((String) prInterFaceMap.get(NPAB138001Constant.TRX_REF_LINE_SUB_NUM), NPAB138001Constant.MAIL_DETAIL_LENGTH[3], NPAB138001Constant.ALIGN_LEFT));
        errMailDetail.append(formatString((String) prInterFaceMap.get(NPAB138001Constant.MDSE_CD), NPAB138001Constant.MAIL_DETAIL_LENGTH[4], NPAB138001Constant.ALIGN_LEFT));
        errMailDetail.append(formatString(String.valueOf(prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_QTY)), NPAB138001Constant.MAIL_DETAIL_LENGTH[5], NPAB138001Constant.ALIGN_RIGHT));
        errMailDetail.append(formatString(S21MessageFunc.clspGetMessage(msgId), NPAB138001Constant.MAIL_DETAIL_LENGTH[6], NPAB138001Constant.ALIGN_LEFT));
        errMailDetail.append(newLine);
    }

    /**
     * Add Error Mail Detail
     */
    private void addErrorMailDetail(NPZC103001PMsg pMsg) {

        if (errMailDetail == null) {
            addErrorMailHeader();
        }

        String trxRefNum = null;
        if (pMsg.prchReqInfo.getValidCount() > 0) {
            trxRefNum = pMsg.prchReqInfo.no(0).trxRefNum.getValue();
        }

        // QC#18689-Sol#303 Modify. [Start]
        String prchReqSrcTpCd = pMsg.prchReqSrcTpCd.getValue();
        String prchReqSrcTpDescTxt = null;
        if (ZYPCommonFunc.hasValue(prchReqSrcTpCd)) {
            prchReqSrcTpDescTxt = ZYPCodeDataUtil.getName(PRCH_REQ_SRC_TP.class, globalCompanyCode, prchReqSrcTpCd);
        }

        StringBuffer techErrMsg = new StringBuffer();
        boolean techErrFirstFlag = true;

        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

            StringBuffer errMsg = new StringBuffer();

            errMsg.append(formatString(prchReqSrcTpDescTxt, NPAB138001Constant.MAIL_DETAIL_LENGTH[0], NPAB138001Constant.ALIGN_LEFT));
            errMsg.append(formatString(trxRefNum, NPAB138001Constant.MAIL_DETAIL_LENGTH[1], NPAB138001Constant.ALIGN_LEFT));
            errMsg.append(formatString(null, NPAB138001Constant.MAIL_DETAIL_LENGTH[2], NPAB138001Constant.ALIGN_LEFT));
            errMsg.append(formatString(null, NPAB138001Constant.MAIL_DETAIL_LENGTH[3], NPAB138001Constant.ALIGN_LEFT));
            errMsg.append(formatString(null, NPAB138001Constant.MAIL_DETAIL_LENGTH[4], NPAB138001Constant.ALIGN_LEFT));
            errMsg.append(formatString(null, NPAB138001Constant.MAIL_DETAIL_LENGTH[5], NPAB138001Constant.ALIGN_RIGHT));
            errMsg.append(formatString(S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(i).xxMsgId.getValue()), NPAB138001Constant.MAIL_DETAIL_LENGTH[6], NPAB138001Constant.ALIGN_LEFT));
            errMsg.append(newLine);

            errMailDetail.append(errMsg.toString());

            if (PRCH_REQ_SRC_TP.PHONE.equals(prchReqSrcTpCd)) {

                if (techErrFirstFlag) {
                    techErrMsg.append(formatString(NPAB138001Constant.MAIL_DETAIL_HEADER[1], NPAB138001Constant.MAIL_DETAIL_LENGTH[1], NPAB138001Constant.ALIGN_LEFT));
                    techErrMsg.append(formatString(NPAB138001Constant.MAIL_DETAIL_HEADER[6], NPAB138001Constant.MAIL_DETAIL_LENGTH[6], NPAB138001Constant.ALIGN_LEFT));
                    techErrMsg.append(newLine);

                    techErrFirstFlag = false;
                }

                techErrMsg.append(formatString(trxRefNum, NPAB138001Constant.MAIL_DETAIL_LENGTH[1], NPAB138001Constant.ALIGN_LEFT));
                techErrMsg.append(formatString(S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(i).xxMsgId.getValue()), NPAB138001Constant.MAIL_DETAIL_LENGTH[6], NPAB138001Constant.ALIGN_LEFT));
                techErrMsg.append(newLine);
            }
        }

        // Technician Error Mail
        if (PRCH_REQ_SRC_TP.PHONE.equals(prchReqSrcTpCd)) {
            String techMailAddress = getTechnicianMailAddress(pMsg.rqstTechTocCd.getValue());

            if (ZYPCommonFunc.hasValue(techMailAddress)) {
                HashMap<String, String> map = new HashMap<String, String>();

                map.put(NPAB138001Constant.TECH_PSN_CD, pMsg.prchReqCratByPsnCd.getValue());
                map.put(NPAB138001Constant.TECH_MAIL_ADDR, techMailAddress);
                map.put(NPAB138001Constant.TECH_MAIL_TRX_REF_NUM, trxRefNum);
                map.put(NPAB138001Constant.TECH_MAIL_ERR_MSG, techErrMsg.toString());

                techErrMailList.add(map);
            } else {
                // Error email address unregistered.
                StringBuffer errMsg = new StringBuffer();
                errMsg.append(formatString(prchReqSrcTpDescTxt, NPAB138001Constant.MAIL_DETAIL_LENGTH[0], NPAB138001Constant.ALIGN_LEFT));
                errMsg.append(formatString(trxRefNum, NPAB138001Constant.MAIL_DETAIL_LENGTH[1], NPAB138001Constant.ALIGN_LEFT));
                errMsg.append(formatString(null, NPAB138001Constant.MAIL_DETAIL_LENGTH[2], NPAB138001Constant.ALIGN_LEFT));
                errMsg.append(formatString(null, NPAB138001Constant.MAIL_DETAIL_LENGTH[3], NPAB138001Constant.ALIGN_LEFT));
                errMsg.append(formatString(null, NPAB138001Constant.MAIL_DETAIL_LENGTH[4], NPAB138001Constant.ALIGN_LEFT));
                errMsg.append(formatString(null, NPAB138001Constant.MAIL_DETAIL_LENGTH[5], NPAB138001Constant.ALIGN_RIGHT));
                errMsg.append(formatString(S21MessageFunc.clspGetMessage(NPAB138001Constant.NPAM1608E), NPAB138001Constant.MAIL_DETAIL_LENGTH[6], NPAB138001Constant.ALIGN_LEFT));

                errMailDetail.append(errMsg.toString());
            }
        }
        // QC#18689-Sol#303 Modify. [End]
    }

    /**
     * Add Error Mail Header
     */
    private void addErrorMailHeader() {

        if (errMailDetail == null) {
            errMailDetail = new StringBuilder();
            errMailDetail.append(formatString(NPAB138001Constant.MAIL_DETAIL_HEADER[0], NPAB138001Constant.MAIL_DETAIL_LENGTH[0], NPAB138001Constant.ALIGN_LEFT));
            errMailDetail.append(formatString(NPAB138001Constant.MAIL_DETAIL_HEADER[1], NPAB138001Constant.MAIL_DETAIL_LENGTH[1], NPAB138001Constant.ALIGN_LEFT));
            errMailDetail.append(formatString(NPAB138001Constant.MAIL_DETAIL_HEADER[2], NPAB138001Constant.MAIL_DETAIL_LENGTH[2], NPAB138001Constant.ALIGN_LEFT));
            errMailDetail.append(formatString(NPAB138001Constant.MAIL_DETAIL_HEADER[3], NPAB138001Constant.MAIL_DETAIL_LENGTH[3], NPAB138001Constant.ALIGN_LEFT));
            errMailDetail.append(formatString(NPAB138001Constant.MAIL_DETAIL_HEADER[4], NPAB138001Constant.MAIL_DETAIL_LENGTH[4], NPAB138001Constant.ALIGN_LEFT));
            errMailDetail.append(formatString(NPAB138001Constant.MAIL_DETAIL_HEADER[5], NPAB138001Constant.MAIL_DETAIL_LENGTH[5], NPAB138001Constant.ALIGN_LEFT));
            errMailDetail.append(formatString(NPAB138001Constant.MAIL_DETAIL_HEADER[6], NPAB138001Constant.MAIL_DETAIL_LENGTH[6], NPAB138001Constant.ALIGN_LEFT));
            errMailDetail.append(newLine);
        }
    }

    /**
     * String Value Format
     * @param instr String Value
     * @param outlen int Return Value Length
     * @param align int Return Value Length
     * @return Format String Value
     */
    public String formatString(String instr, int outlen, int align) {

        if (!ZYPCommonFunc.hasValue(instr)) {
            instr = "";
        }
        instr = NPAB138001Constant.SPACE_VALUE + NPAB138001Constant.SPACE_VALUE + instr;
        if (align == NPAB138001Constant.ALIGN_LEFT) {
            instr = ZYPCommonFunc.rightPad(instr, outlen, NPAB138001Constant.SPACE_VALUE);
        } else if (align == NPAB138001Constant.ALIGN_RIGHT) {
            instr = ZYPCommonFunc.leftPad(instr, outlen, NPAB138001Constant.SPACE_VALUE);
        }
        return instr;
    }

    /**
     * String Value Format
     * @param prInterFaceList List
     * @param trxRefNum String
     * @param procStsCd String
     */
    public void setPrevProcStsCd(List<Map<String, Object>> prInterFaceList, String trxRefNum, String procStsCd) {
        for (int i = 0; i < prInterFaceList.size(); i++) {
            Map<String, Object> prInterFaceMap = prInterFaceList.get(i);
            String listTrxRefNum = (String) prInterFaceMap.get(NPAB138001Constant.TRX_REF_NUM);
            if (listTrxRefNum.equals(trxRefNum)) {

                // Min_Max data check
                // if Min_Max data. same TRX_REF_NUM ERROR is not set ERROR STATUS.
                boolean mrpDataFlg = false;
                for (int j = 0; j < mrpTypes.length; j++) {
                    if (mrpTypes[j].equals((String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_SRC_TP_CD))) {
                        mrpDataFlg = true;
                    }
                }

                if (!mrpDataFlg) {
                    prInterFaceMap.put(NPAB138001Constant.PROC_STS_CD, procStsCd);
                }

            }
        }
    }

    /**
     * getTechnicianMailAddress
     * @param psnCd String
     * @return mailAddress String
     */
    private String getTechnicianMailAddress(String psnCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(NPAB138001Constant.GLBL_CMPY_CD, globalCompanyCode);
        queryParam.put(NPAB138001Constant.PRCH_REQ_CRAT_BY_PSN_CD, psnCd);

        return (String) ssmBatchClient.queryObject("getTechnicianMailAddress", queryParam);

    }
    // QC#21170 Add Start
    /**
     * getRqstRcvDt
     * @param glblCmpyCd
     * @param rqstRcvDt
     * @param vndLtDaysNum
     * @param prchReqTpCd
     * @return
     */
    private String getRqstRcvDt(Map<String, Object> prInterFaceMap) {
//      String rqstRcvDt = salesDate;
//        if (!ZYPCommonFunc.hasValue(rqstRcvDt)) {
//            return null;
//        }

        String etaDt = salesDate;

        String glblCmpyCd = (String) prInterFaceMap.get(NPAB138001Constant.GLBL_CMPY_CD);
        BigDecimal vndLtDaysNum = (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ASL_VND_LT_DAYS_NUM);
        String prchReqTpCd = PRCH_REQ_TP.MIN_MAX;

        if (ZYPCommonFunc.hasValue(vndLtDaysNum)) {
            // 2023/10/11 QC#61870 START
            // etaDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, salesDate, vndLtDaysNum.intValue());
            etaDt = ZYPDateUtil.addDays(salesDate, vndLtDaysNum.intValue());
            // 2023/10/11 QC#61870 END
        } else {
            if (ZYPCommonFunc.hasValue(prchReqTpCd)) {
                BigDecimal defPrchReqTpDaysNum = getDefPrchReqTpDaysNum(glblCmpyCd, prchReqTpCd);
                if (ZYPCommonFunc.hasValue(defPrchReqTpDaysNum)) {
                    // 2023/10/11 QC#61870 START
                    // etaDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, salesDate, defPrchReqTpDaysNum.intValue());
                    etaDt = ZYPDateUtil.addDays(salesDate, defPrchReqTpDaysNum.intValue());
                    // 2023/10/11 QC#61870 END
                }
            }
        }
        return etaDt;
    }

    // 2023/10/11 QC#61870 START
    /**
     * getRqstRcvDt
     * @param glblCmpyCd
     * @param rqstRcvDt
     * @param vndLtDaysNum
     * @param prchReqTpCd
     * @return
     */
    private String getRqstShipDt(Map<String, Object> prInterFaceMap) {

        String etaDt = "";
        BigDecimal vndLtDaysNum = (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ASL_VND_LT_DAYS_NUM);
        BigDecimal vndShipLtDaysNum = (BigDecimal) prInterFaceMap.get(NPAB138001Constant.ASL_VND_SHIP_LT_DAYS_NUM);

        if (ZYPCommonFunc.hasValue(vndShipLtDaysNum)) {
            if (ZYPCommonFunc.hasValue(vndLtDaysNum)) {
                etaDt = ZYPDateUtil.addDays(salesDate, (vndLtDaysNum.intValue() - vndShipLtDaysNum.intValue()));
            }
        }
        return etaDt;
    }
    // 2023/10/11 QC#61870 END

    /**
     * getDefPrchReqTpDaysNum
     * @param glblCmpyCd
     * @param prchReqTpCd
     * @return
     */
    private BigDecimal getDefPrchReqTpDaysNum(String glblCmpyCd, String prchReqTpCd) {
        BigDecimal defPrchReqTpDaysNum = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prchReqTpCd", prchReqTpCd);
//        params.put("prchReqRecTpCd", PRCH_REQ_REC_TP.PO_REQUISITION);

        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDefPrchReqTpDaysNum", params);
        if (list.size() > 0) {
            Map<String, Object> map = list.get(0);
            defPrchReqTpDaysNum = (BigDecimal) map.get("DEF_PRCH_REQ_TP_DAYS_NUM");
        }
        return defPrchReqTpDaysNum;
    }
    // QC#21170 Add End

    /**
     * isDropShipOrder
     * QC#28268 Add method.
     * @param prInterFaceMap
     * @return true: CPO Drop ship Order
     */
    private boolean isDropShipOrder(Map<String, Object> prInterFaceMap) {

        String cpoOrdNum = (String) prInterFaceMap.get(NPAB138001Constant.CPO_ORD_NUM);
        String rtlWhCd = (String) prInterFaceMap.get(NPAB138001Constant.DEST_RTL_WH_CD);
        if (ZYPCommonFunc.hasValue(cpoOrdNum) && dropShipWhCd.equals(rtlWhCd)) {
            return true;
        }
        return false;
    }

    /**
     * isRossOrder
     * QC#28268 Add method.
     * @param prInterFaceMap
     * @return true: Ross Order
     */
    private boolean isRossOrder(Map<String, Object> prInterFaceMap) {

        String cpoOrdNum = (String) prInterFaceMap.get(NPAB138001Constant.CPO_ORD_NUM);
        CPOTMsg cpo = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpo.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(cpo.cpoOrdNum, cpoOrdNum);

        cpo = (CPOTMsg) EZDTBLAccessor.findByKey(cpo);

        if (cpo != null && CPO_SRC_TP.CUSA_NAD_OR_GMD.equals(cpo.cpoSrcTpCd.getValue())) {
            return true;
        }

        return false;
    }

    /**
     * isMrpOrder
     * QC#28268 Add method.
     * @param prInterFaceMap
     * @return true: MRP Order
     */
    private boolean isMrpOrder(Map<String, Object> prInterFaceMap) {

        String prchReqTpCd = (String) prInterFaceMap.get(NPAB138001Constant.PRCH_REQ_TP_CD);

        if (PRCH_REQ_TP.MIN_MAX.equals(prchReqTpCd)) {
            return true;
        }

        return false;
    }

    /**
     * doesExistPrchGrpCd
     * QC#28268 Add method.
     * @param prchGrpCd
     * @return true: exists prch_grp_cd
     */
    private boolean doesExistPrchGrpCd(String prchGrpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(NPAB138001Constant.GLBL_CMPY_CD, globalCompanyCode);
        params.put(NPAB138001Constant.PRCH_GRP_CD, prchGrpCd);

        String result = (String) ssmBatchClient.queryObject("doesExistPrchGrp", params);

        if (ZYPCommonFunc.hasValue(result)) {
            return true;
        }

        return false;
    }

    /**
     * isSetComponent
     * QC#29177 Add method.
     * @param prInterFaceMap
     * @return true: Set Component Item
     */
    private boolean isSetComponent(Map<String, Object> prInterFaceMap) {

        String poMdseCmpsnTpCd = (String) prInterFaceMap.get(NPAB138001Constant.PO_MDSE_CMPSN_TP_CD);
        if (!ZYPCommonFunc.hasValue(poMdseCmpsnTpCd)) {
            return false;
        }

        if (PO_MDSE_CMPSN_TP.CHILD.equals(poMdseCmpsnTpCd)) {
            return true;
        }

        return false;
    }

    /**
     * isSetParent
     * QC#29177 Add method.
     * @param prInterFaceMap
     * @return true: Set Component Item
     */
    private boolean isSetParent(Map<String, Object> prInterFaceMap) {

        String poMdseCmpsnTpCd = (String) prInterFaceMap.get(NPAB138001Constant.PO_MDSE_CMPSN_TP_CD);
        if (!ZYPCommonFunc.hasValue(poMdseCmpsnTpCd)) {
            return false;
        }

        if (PO_MDSE_CMPSN_TP.PARENT.equals(poMdseCmpsnTpCd)) {
            return true;
        }

        return false;
    }

    private void dropshipPrCancel(String cpoOrdNum) {
        // Check Open PR
        PreparedStatement preparedStatementPR = null;
        ResultSet resultSetPR = null;
        try {
            // Dropship PR Cancel Process.
            S21SsmLowLevelCodingClient ssmLlcClientPR = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMapPR = new HashMap<String, Object>();
            paramMapPR.put(NPAB138001Constant.GLBL_CMPY_CD, globalCompanyCode);
            paramMapPR.put(NPAB138001Constant.CPO_ORD_NUM, cpoOrdNum);
            paramMapPR.put(NPAB138001Constant.PRCH_REQ_STS_CD, PRCH_REQ_STS.OPEN);
            paramMapPR.put(NPAB138001Constant.PRCH_REQ_LINE_STS_CD, PRCH_REQ_LINE_STS.OPEN);
            paramMapPR.put(NPAB138001Constant.PR_CANCEL_APVL_STATUS_CD, prCancelApvlCds);

            S21SsmExecutionParameter execParamPR = new S21SsmExecutionParameter();
            execParamPR.setFetchSize(FETCH_SIZE);
            execParamPR.setMaxRows(0);
            preparedStatementPR = ssmLlcClientPR.createPreparedStatement(NPAB138001Constant.GET_ENTERD_DS_PR, paramMapPR, execParamPR);
            resultSetPR = preparedStatementPR.executeQuery();

            int cnt = 0;
            String prePrchReqNum = null;
            NPZC103001PMsg params = null;
            while (resultSetPR.next()) {
                // Set PR Update API Parameter. Line Cancel Mode
                String prchReqNum = resultSetPR.getString(NPAB138001Constant.PRCH_REQ_NUM);
                if (!prchReqNum.equals(prePrchReqNum)) {
                    // Call PR Update API
                    if (params != null) {
                        NPZC103001 api = new NPZC103001();
                        api.execute(params, ONBATCH_TYPE.BATCH);

                        if (S21ApiUtil.isXxMsgId(params)) {
                            List<String> msgIds = S21ApiUtil.getXxMsgIdList(params);
                            for (String msgId : msgIds) {
                                S21InfoLogOutput.println("PR Update API Line Cancel Error:" + S21MessageFunc.clspGetMessage(msgId));
                            }
                            rollback();
                        } else {
                            // QC#25411-1
                            commit();
                        }
                    }

                    params = new NPZC103001PMsg();
                    ZYPEZDItemValueSetter.setValue(params.xxModeCd, NPZC103001Constant.MODE_CANCEL);
                    ZYPEZDItemValueSetter.setValue(params.eventId, NPZC103001Constant.EVENT_SUBMIT);
                    ZYPEZDItemValueSetter.setValue(params.glblCmpyCd, this.globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(params.prchReqNum, prchReqNum);
                    prePrchReqNum = prchReqNum;
                }

                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(cnt).prchReqLineNum, resultSetPR.getString(NPAB138001Constant.PRCH_REQ_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(cnt).prchReqLineSubNum, resultSetPR.getBigDecimal(NPAB138001Constant.PRCH_REQ_LINE_SUB_NUM));
                params.prchReqInfo.setValidCount(cnt);
            }

            if (params != null) {
                NPZC103001 api = new NPZC103001();
                api.execute(params, ONBATCH_TYPE.BATCH);

                if (S21ApiUtil.isXxMsgId(params)) {
                    List<String> msgIds = S21ApiUtil.getXxMsgIdList(params);
                    for (String msgId : msgIds) {
                        S21InfoLogOutput.println("PR Update API Line Cancel Error:" + S21MessageFunc.clspGetMessage(msgId));
                    }
                    rollback();
                } else {
                    // QC#25411-1
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatementPR, resultSetPR);
        }
    }

    private void externalPrCancel(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        // Check Open PR
        PreparedStatement preparedStatementPR = null;
        ResultSet resultSetPR = null;
        try {
            // Dropship PR Cancel Process.
            S21SsmLowLevelCodingClient ssmLlcClientPR = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMapPR = new HashMap<String, Object>();
            paramMapPR.put(NPAB138001Constant.GLBL_CMPY_CD, globalCompanyCode);
            paramMapPR.put(NPAB138001Constant.CPO_ORD_NUM, cpoOrdNum);
            paramMapPR.put(NPAB138001Constant.CPO_DTL_LINE_NUM, cpoDtlLineNum);
            paramMapPR.put(NPAB138001Constant.CPO_DTL_LINE_SUB_NUM, cpoDtlLineSubNum);
            paramMapPR.put(NPAB138001Constant.PRCH_REQ_STS_CD, PRCH_REQ_STS.OPEN);
            paramMapPR.put(NPAB138001Constant.PRCH_REQ_LINE_STS_CD, PRCH_REQ_LINE_STS.OPEN);
            paramMapPR.put(NPAB138001Constant.PR_CANCEL_APVL_STATUS_CD, prCancelApvlCds);

            S21SsmExecutionParameter execParamPR = new S21SsmExecutionParameter();
            execParamPR.setFetchSize(FETCH_SIZE);
            execParamPR.setMaxRows(0);
            preparedStatementPR = ssmLlcClientPR.createPreparedStatement(NPAB138001Constant.GET_ENTERD_EX_PR, paramMapPR, execParamPR);
            resultSetPR = preparedStatementPR.executeQuery();

            int cnt = 0;
            String prePrchReqNum = null;
            NPZC103001PMsg params = null;
            while (resultSetPR.next()) {
                // Set PR Update API Parameter. Line Cancel Mode
                String prchReqNum = resultSetPR.getString(NPAB138001Constant.PRCH_REQ_NUM);
                if (!prchReqNum.equals(prePrchReqNum)) {
                    // Call PR Update API
                    if (params != null) {
                        NPZC103001 api = new NPZC103001();
                        api.execute(params, ONBATCH_TYPE.BATCH);

                        if (S21ApiUtil.isXxMsgId(params)) {
                            List<String> msgIds = S21ApiUtil.getXxMsgIdList(params);
                            for (String msgId : msgIds) {
                                S21InfoLogOutput.println("PR Update API Line Cancel Error:" + S21MessageFunc.clspGetMessage(msgId));
                            }
                            rollback();
                        } else {
                            // QC#25411-1
                            commit();
                        }
                    }

                    params = new NPZC103001PMsg();
                    ZYPEZDItemValueSetter.setValue(params.xxModeCd, NPZC103001Constant.MODE_CANCEL);
                    ZYPEZDItemValueSetter.setValue(params.eventId, NPZC103001Constant.EVENT_SUBMIT);
                    ZYPEZDItemValueSetter.setValue(params.glblCmpyCd, this.globalCompanyCode);
                    ZYPEZDItemValueSetter.setValue(params.prchReqNum, prchReqNum);
                    prePrchReqNum = prchReqNum;
                }

                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(cnt).prchReqLineNum, resultSetPR.getString(NPAB138001Constant.PRCH_REQ_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(cnt).prchReqLineSubNum, resultSetPR.getBigDecimal(NPAB138001Constant.PRCH_REQ_LINE_SUB_NUM));
                params.prchReqInfo.setValidCount(cnt);
            }

            if (params != null) {
                NPZC103001 api = new NPZC103001();
                api.execute(params, ONBATCH_TYPE.BATCH);

                if (S21ApiUtil.isXxMsgId(params)) {
                    List<String> msgIds = S21ApiUtil.getXxMsgIdList(params);
                    for (String msgId : msgIds) {
                        S21InfoLogOutput.println("PR Update API Line Cancel Error:" + S21MessageFunc.clspGetMessage(msgId));
                    }
                    rollback();
                } else {
                    // QC#25411-1
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatementPR, resultSetPR);
        }
    }

    // START 2023/01/19 A.Cullano [QC#61081, ADD]
    /**
     * getExistCnt
     * @param cpoOrdNum
     * @return BigDecimal
     */
    private BigDecimal getExistCnt(String cpoOrdNum) {
        // Check if an existing PO Req is existing before cancellation
        Map<String, String> map = new HashMap<String, String>();

        map.put(NPAB138001Constant.GLBL_CMPY_CD, globalCompanyCode);
        map.put(NPAB138001Constant.CPO_ORD_NUM, cpoOrdNum);
        map.put(NPAB138001Constant.PRCH_REQ_LINE_STS_CD, PRCH_REQ_LINE_STS.OPEN);

        return (BigDecimal) ssmBatchClient.queryObject(NPAB138001Constant.CHECKEXISTPR, map);
    }
    // END 2023/01/19 A.Cullano [QC#61081, ADD]

    // START 2023/06/23 R.Kurahashi [QC#61081, ADD]
    /**
     * deletePrInterface
     */
    private void deletePrInterface(BigDecimal prchReqIntfcPk) {
        PRCH_REQ_INTFCTMsg prchReqIntfcTMsg = new PRCH_REQ_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(prchReqIntfcTMsg.prchReqIntfcPk, prchReqIntfcPk);
        prchReqIntfcTMsg = (PRCH_REQ_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdate(prchReqIntfcTMsg);

        if (prchReqIntfcTMsg != null) {
            EZDTBLAccessor.logicalRemove(prchReqIntfcTMsg);
        }
    }
    // END 2023/06/23 R.Kurahashi [QC#61081, ADD]

}