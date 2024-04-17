/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPB.NPBB001001;

import static com.canon.cusa.s21.batch.NPB.NPBB001001.constant.NPBB001001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_IMPT_ORD_ISTL_INFOTMsg;
import business.db.DS_MDLTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHPG_ORD_PRO_NUM_LISTTMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.VND_RTRNTMsg;
import business.db.VND_RTRN_CMNTTMsg;
import business.db.VND_RTRN_DTLTMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NLZC210001PMsg;
import business.parts.NLZC210002PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC136001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NLZ.NLZC210001.NLZC210001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC136001.NPZC136001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomer;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomerBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DSPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_TO;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NPBB001001:Inventory Request Release Batch
 * </pre>
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/22   CITS            T.Kikuhara      Create          N/A
 * 2016/04/19   CITS            K.Ogino         Update          QC#7124
 * 2016/04/20   CITS            K.Ogino         Update          QC#7352
 * 2016/04/26   CSAI            K.Lee           Update          QC#7454
 * 2016/04/26   CSAI            K.Lee           Update          QC#7347
 * 2016/05/06   CITS            Y.Nomura        Update          QC#7851
 * 2016/06/20   CSAI            D.Fukaya        Update          QC#8134
 * 2016/06/30   CSAI            D.Fukaya        Update          QC#7735
 * 2016/07/14   Fujitsu         T.Ishii         Update          QC#11561
 * 2016/08/03   CITS            K.Ogino         Update          QC#12513
 * 2016/08/09   CITS            K.Ogino         Update          QC#13215
 * 2016/08/10   CITS            K.Ogino         Update          QC#11788
 * 2016/08/24   CITS            K.Ogino         Update          QC#13215
 * 2016/09/02   CITS            K.Ogino         Update          QC#14121
 * 2016/09/09   CITS            K.Ogino         Update          QC#14046
 * 2016/11/29   Fujitsu         S.Iidaka        Update          QC#16265
 * 2016/11/29   CITS            K.Ogino         Update          QC#16262
 * 2016/12/01   CITS            Y.IWASAKI       Update          QC#16073
 * 2016/12/01   CITS            Y.IWASAKI       Update          QC#15584
 * 2017/03/03   CITS            R.Shimamoto     Update          QC#17395
 * 2017/04/07   CITS            R.Shimamoto     Update          QC#18036
 * 2017/06/29   CITS            Y.Iwasaki       Update          QC#19641
 * 2017/07/03   CITS            M.Naito         Update          QC#19682
 * 2017/07/26   CITS            K.Ogino         Update          QC#20008
 * 2017/09/01   CITS            T.Kikuhara      Create          QC#18365(L3)
 * 2017/10/04   CITS            K.Ogino         Update          QC#21314
 * 2017/11/30   CITS            K.Ogino         Update          QC#22255
 * 2018/02/02   Hitachi         Y.Takeno        Update          QC#21703
 * 2018/02/26   CITS            K.Ogino         Update          QC#22378
 * 2018/03/27   CITS            K.Fukumura      Update          QC#22517
 * 2018/05/28   CITS            S.Katsuma       Update          QC#25893
 * 2018/10/31   CITS            M.Naito         Update          QC#29009
 * 2019/01/08   Fujitsu         C.Hara          Update          QC#29779
 * 2019/02/06   CITS            M.Naito         Update          QC#30156
 * 2019/02/26   Fujitsu         S.Takami        Update          QC#30451
 * 2019/03/08   CITS            K.Ogino         Update          QC#30618
 * 2019/09/11   CITS            K.Ogino         Update          QC#52809
 * 2019/10/03   CITS            M.Naito         Update          QC#52809
 * 2020/01/28   Fujitsu         R.Nakamura      Update          QC#54989
 * 2020/02/22   CITS            T.Wada          Update          QC#55753
 * 2021/04/21   CITS            M.Furugoori     Update          QC#58700
 * 2022/03/14   Hitachi         A.Kohinata      Update          QC#59780
 * 2022/12/16   Hitachi         T.Kuroda        Update          QC#60562
 * 2023/06/13   Hitachi         S.Dong          Update          QC#55629
 *</pre>
 */
public class NPBB001001 extends S21BatchMain {

    /** S21SsmBatchClient */
    private S21SsmBatchClient glSsmBatchClient = null;

    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** total count */
    private int totalCount = 0;

    /** error count */
    private int errorCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** Inventory Order API */
    private NLZC003001 invOrdApi = new NLZC003001();

    /** Inventory Order Number */
    private String glInvtyOrdNum;

    /** Order Information for no config order*/
    private Map<String, Object> glOrdInfoForNoConfig;

    /** Order Information for config order*/
    private Map<String, Object> glOrdInfoForConfig;

    /** Standard Currency CD */
    private String glStdCcyCd;

    // QC#17395 Start.
    /** VAR_CHAR_CONST Key : WH_OWNR_CD */
    private String HAZMAT_WH_OWNR_CD = "NLBB0020_HAZMAT_WH_OWNR_CD";

    /** whOwnrCdList */
    private String[] whOwnrCdList = null;
    // QC#17395 End.

    @Override
    protected void initRoutine() {

        glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAM1173E, new String[] {GLBL_CMPY_CD });
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!hasValue(salesDate)) {
            throw new S21AbendException(NPAM1173E, new String[] {SALES_DATE });
        }

        // QC#17395 Start.
        String whOwnrCd = ZYPCodeDataUtil.getVarCharConstValue(HAZMAT_WH_OWNR_CD, globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(whOwnrCd)) {
            whOwnrCdList = null;
        } else {
            whOwnrCdList = whOwnrCd.split(",");
        }
        // QC#17395 End.
    }

    @Override
    protected void mainRoutine() {

        // Inventory Request Release Process
        execInvReqRelProc(false);

        // QC#17395 Start.
        // Inventory Request Release Process For hazMat
        if (whOwnrCdList != null) {
            execInvReqRelProc(true);
        }
        // QC#17395 End.

        // Missing Parts Request Process
        execMissingPartsReqProc();

        return;
    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPBB001001().executeBatch(NPBB001001.class.getSimpleName());
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        setTermState(termCd, totalCount - errorCount, errorCount, totalCount);
    }

    /**
     * Inventory Request Release Process
     */
    private void execInvReqRelProc(boolean hazMatFlg) {
        String invtyReqTpCd = ZYPCodeDataUtil.getVarCharConstValue(INVTY_REQ_TP_CD, globalCompanyCode);
        if (!hasValue(invtyReqTpCd)) {
            throw new S21AbendException(NPAM1173E, new String[] {INVTY_REQ_TP_CD });
        }
        String prchReqStsCdOpen = ZYPCodeDataUtil.getVarCharConstValue(NPBB0010_REL_PRCH_REQ_STS_CD, globalCompanyCode);
        if (!hasValue(prchReqStsCdOpen)) {
            throw new S21AbendException(NPAM1173E, new String[] {NPBB0010_REL_PRCH_REQ_STS_CD });
        }
        String defShpgSvcLvlCd = ZYPCodeDataUtil.getVarCharConstValue(INVTY_REQ_DEF_SHPG_SVC_LVL_CD, globalCompanyCode);
        if (!hasValue(defShpgSvcLvlCd)) {
            throw new S21AbendException(NPAM1173E, new String[] {INVTY_REQ_DEF_SHPG_SVC_LVL_CD });
        }

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // Get Inventory Request Release Data
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(PRCH_REQ_REC_TP_CD, invtyReqTpCd);
            String[] prchReqStsCdOpens = prchReqStsCdOpen.split(",");
            List<String> prReqStsList = Arrays.asList(prchReqStsCdOpens);
            paramMap.put(PRCH_REQ_STS_CD, prReqStsList);
            paramMap.put(IN_COMPLETED, PRCH_REQ_REL_STS.IN_COMPLETED);
            paramMap.put(ERROR, PRCH_REQ_REL_STS.ERROR);
            paramMap.put(PRCH_REQ_APVL_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(INVTY_REQ_DEF_SHPG_SVC_LVL_CD, defShpgSvcLvlCd);
            paramMap.put(MDSE_TP_CTX_TP_CD, MDSE_TP_CTX_TP.MAIN_MACHINE);
            // QC#17395 Start.
            paramMap.put(HAZ_MAT_FLG_N, ZYPConstant.FLG_OFF_N);
            paramMap.put(HAZ_MAT_FLG_Y, ZYPConstant.FLG_ON_Y);
            paramMap.put(DEF_HAZ_MAT_FLG, ZYPConstant.FLG_OFF_N);
            paramMap.put(TRX_SRC_TP_CD_WHOLE_SALES, TRX_SRC_TP.WHOLE_SALES);
            if (whOwnrCdList != null) {
                paramMap.put(WH_OWNR_CD_LIST, whOwnrCdList);
            }
            String sqlID = "";
            if (!hazMatFlg) {
                sqlID = "getInventoryRequestRelease";
            } else {
                sqlID = "getInventoryRequestReleaseForHazmat";
            }
            // QC#17395 End.

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            // QC#17395 Start.
//            preparedStatement = ssmLlcClient.createPreparedStatement("getInventoryRequestRelease", paramMap, execParam);
            preparedStatement = ssmLlcClient.createPreparedStatement(sqlID, paramMap, execParam);

            // QC#17395 End.
            rs = preparedStatement.executeQuery();

            String oldPrchReqNum = "";
            String oldPrchReqLineNum = "";
            String oldConfigTpCd = "";
            String oldDestInvtyLocCd = "";
            String oldSrcRtlWhCd = "";
            String oldDestRtlWhCd = "";
            String oldSceOrdTpCd = "";
            String oldTrxSrcTpCd = "";
            String oldConfigFlg = "";

            boolean errFlg = false;
            int iLineNum = 0;
            BigDecimal dsImptOrdPk = null;
            BigDecimal dsImptOrdConfigPk = null;
            BigDecimal dsOrdPosnNum = BigDecimal.ZERO;

            // QC#22517 2018/03/27 Start
            boolean flgDropShip = false;
            // QC#22517 2018/03/27 End
            List<Map<String, Object>> shpgPlnInfoMapList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> configItemLineInfoMapList = new ArrayList<Map<String, Object>>();

            int rowNum = 0;
            int inProcessCount = 0;
            // QC#30618
            List<BigDecimal> svcMachMstrPkList = new ArrayList<BigDecimal>();

            while (rs.next()) {
                totalCount++;
                rowNum++;

                String prchReqNum = rs.getString(PRCH_REQ_NUM);
                String prchReqLineNum = rs.getString(PRCH_REQ_LINE_NUM);
                String configTpCd = setEmptyStringForNull(rs.getString(CONFIG_TP_CD));
                String destInvtyLocCd = rs.getString(DEST_INVTY_LOC_CD);
                String srcRtlWhCd = setEmptyStringForNull(rs.getString(SRC_RTL_WH_CD));
                String destRtlWhCd = setEmptyStringForNull(rs.getString(DEST_RTL_WH_CD));
                String sceOrdTpCd = rs.getString(SCE_ORD_TP_CD);
                String trxSrcTpCd = rs.getString(TRX_SRC_TP_CD);
                String mdseCd = rs.getString(MDSE_CD);
                String configFlg = rs.getString(CONFIG_FLG);
                String prchReqTpCd = rs.getString(PRCH_REQ_TP_CD);

                boolean isNewImportOrdConfig = false;

                if (errFlg) {
                    if (oldPrchReqNum.equals(prchReqNum)) {
                        continue;
                    } else {
                        rollback();
                        errorCount = errorCount + inProcessCount;
                        inProcessCount = 0;
                        errFlg = false;

                        // START 2021/04/21 [QC#58700,ADD]
                        shpgPlnInfoMapList.clear();
                        // END   2021/04/21 [QC#58700,ADD]

                        // QC#20008 Add
                        configItemLineInfoMapList.clear();
                        iLineNum = 1;

                        if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
                            isNewImportOrdConfig = true;
                            dsOrdPosnNum = BigDecimal.ZERO;
                            dsImptOrdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SQ);
                        }

                    }
                } else if (TRX_SRC_TP.WHOLE_SALES.equals(oldTrxSrcTpCd)
                        && PRCH_REQ_TP.WH_TRANSFER.equals(prchReqTpCd)
                        && oldPrchReqNum.equals(prchReqNum)
                        && oldConfigFlg.equals(configFlg)
                        // START 2022/12/16 [QC#60562,DEL]
                        // && oldSrcRtlWhCd.equals(srcRtlWhCd)
                        // END 2022/12/16 [QC#60562,DEL]
                        && oldDestRtlWhCd.equals(destRtlWhCd)) {

                    iLineNum++;

                    if (!prchReqLineNum.equals(oldPrchReqLineNum)) {
                        if (ZYPCommonFunc.hasValue(configTpCd)) {
                            isNewImportOrdConfig = true;
                        } else if (ZYPCommonFunc.hasValue(oldConfigTpCd)) {
                            isNewImportOrdConfig = true;
                        }
                    }

                    if (isNewImportOrdConfig) {
                        // Create Import Order Config
                        if (!createImptOrdConfig(configItemLineInfoMapList, oldConfigTpCd, flgDropShip)) {
                            errFlg = true;
                        }
                        configItemLineInfoMapList.clear();
                    }
                } else if (TRX_SRC_TP.WHOLE_SALES.equals(oldTrxSrcTpCd)
                        && PRCH_REQ_TP.EXPENSE_ORDER.equals(prchReqTpCd)
                        && oldPrchReqNum.equals(prchReqNum)
                        && oldConfigFlg.equals(configFlg)
                        // START 2022/12/16 [QC#60562,DEL]
                        // && oldSrcRtlWhCd.equals(srcRtlWhCd)
                        // END 2022/12/16 [QC#60562,DEL]
                        && oldDestInvtyLocCd.equals(destInvtyLocCd)) {

                    iLineNum++;

                    if (!prchReqLineNum.equals(oldPrchReqLineNum)) {
                        if (ZYPCommonFunc.hasValue(configTpCd)) {
                            isNewImportOrdConfig = true;
                        } else if (ZYPCommonFunc.hasValue(oldConfigTpCd)) {
                            isNewImportOrdConfig = true;
                        } else if (!oldDestInvtyLocCd.equals(destInvtyLocCd)) {
                            isNewImportOrdConfig = true;
                        }
                    }

                    if (isNewImportOrdConfig) {
                        // Create Import Order Config
                        if (!createImptOrdConfig(configItemLineInfoMapList, oldConfigTpCd, flgDropShip)) {
                            errFlg = true;
                        }
                        configItemLineInfoMapList.clear();
                    }
                } else if (!TRX_SRC_TP.WHOLE_SALES.equals(oldTrxSrcTpCd)
                        && oldPrchReqNum.equals(prchReqNum)
                        && oldSrcRtlWhCd.equals(srcRtlWhCd)
                        && oldDestRtlWhCd.equals(destRtlWhCd)) {

                    iLineNum++;

                } else {
                    if (rowNum == 1) {
                        // do nothing
                    } else if (TRX_SRC_TP.WHOLE_SALES.equals(oldTrxSrcTpCd)) {
                        // Create Import Order Config
                        if (createImptOrdConfig(configItemLineInfoMapList, oldConfigTpCd, flgDropShip)) {
                            if (!oldPrchReqNum.equals(prchReqNum)) {
                                commit();
                                inProcessCount = 0;
                            }
                        } else {
                            if (oldPrchReqNum.equals(prchReqNum)) {
                                errFlg = true;
                            } else {
                                rollback();
                                errorCount = errorCount + inProcessCount;
                                inProcessCount = 0;
                            }
                        }
                        configItemLineInfoMapList.clear();

                    } else if (TRX_SRC_TP.MOVEMENT.equals(oldTrxSrcTpCd) || TRX_SRC_TP.VENDOR_RETURN.equals(oldTrxSrcTpCd)) {

                        if (createSo(oldSceOrdTpCd, shpgPlnInfoMapList)) {
                            if (!oldPrchReqNum.equals(prchReqNum)) {
                                commit();
                                inProcessCount = 0;
                            }
                        } else {
                            if (oldPrchReqNum.equals(prchReqNum)) {
                                errFlg = true;
                            } else {
                                rollback();
                                errorCount = errorCount + inProcessCount;
                                inProcessCount = 0;
                            }
                        }
                        shpgPlnInfoMapList.clear();
                    }

                    iLineNum = 1;

                    if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
                        isNewImportOrdConfig = true;
                        dsOrdPosnNum = BigDecimal.ZERO;
                        dsImptOrdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SQ);
                    }
                }

                String wkLineNum = String.format("%03d", iLineNum);

                oldPrchReqNum = prchReqNum;
                oldPrchReqLineNum = prchReqLineNum;
                oldSrcRtlWhCd = srcRtlWhCd;
                oldDestRtlWhCd = destRtlWhCd;
                oldSceOrdTpCd = sceOrdTpCd;
                oldTrxSrcTpCd = trxSrcTpCd;
                oldConfigTpCd = configTpCd;
                oldDestInvtyLocCd = destInvtyLocCd;
                oldConfigFlg = configFlg;

                inProcessCount++;

                if (TRX_SRC_TP.WHOLE_SALES.equals(trxSrcTpCd)) {
                    // ------------------------------------
                    // Create CPO Process
                    // ------------------------------------
                    if (isNewImportOrdConfig) {
                        dsOrdPosnNum = dsOrdPosnNum.add(BigDecimal.ONE);
                        dsImptOrdConfigPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CONFIG_SQ);
                    }
                    // QC#22517 2018/03/27 Start
                    // ------------------------------------
                    // DropShipCheck
                    // ------------------------------------
                    flgDropShip = IsDropShip(rs);
                    // QC#22517 2018/03/27 End
                    // ------------------------------------
                    // Insert DS_IMPT_ORD, DS_IMPT_ORD_DTL and update PR
                    // ------------------------------------
                    // QC#30618
                    if (!createCpoProc(rs, wkLineNum, dsOrdPosnNum, dsImptOrdPk, dsImptOrdConfigPk, configItemLineInfoMapList, flgDropShip, svcMachMstrPkList)) {
                        errFlg = true;
                    }
                } else if (TRX_SRC_TP.MOVEMENT.equals(trxSrcTpCd)) {
                    // Create Inventory Order Process
                    if (!hasValue(mdseCd)) {
                        // Config Line
                        iLineNum--;
                        if (!createInvOrdProcForConfigLine(rs)) {
                            errFlg = true;
                        }
                    } else {
                        if (!createInvOrdProc(rs, wkLineNum, shpgPlnInfoMapList)) {
                            errFlg = true;
                            continue;
                        }
                    }
                } else if (TRX_SRC_TP.VENDOR_RETURN.equals(trxSrcTpCd)) {
                    // Create Vendor Return Process
                    if (!createVndRtnProc(rs, wkLineNum, shpgPlnInfoMapList)) {
                        errFlg = true;
                    }
                } else {
                    errFlg = true;
                }
            } // End Loop

            if (rowNum == 0) {
                // no process
            } else if (errFlg) {
                rollback();
                errorCount = errorCount + inProcessCount;
            } else if (TRX_SRC_TP.WHOLE_SALES.equals(oldTrxSrcTpCd)) {
                // Create Import Order Config
                if (createImptOrdConfig(configItemLineInfoMapList, oldConfigTpCd, flgDropShip)) {
                    commit();
                } else {
                    rollback();
                    errorCount = errorCount + inProcessCount;
                }
            } else if (TRX_SRC_TP.MOVEMENT.equals(oldTrxSrcTpCd) || TRX_SRC_TP.VENDOR_RETURN.equals(oldTrxSrcTpCd)) {

                if (createSo(oldSceOrdTpCd, shpgPlnInfoMapList)) {
                    commit();
                } else {
                    rollback();
                    errorCount = errorCount + inProcessCount;
                }
            }
        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
    }

    /**
     * Create CPO Process
     * Mod QC#30618.
     */
    private boolean createCpoProc(ResultSet rs, String wkLineNum, BigDecimal dsOrdPosnNum, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, List<Map<String, Object>> configItemLineInfoMapList, boolean pFlgDropShip, List<BigDecimal> svcMachMstrPkList) throws SQLException {
        String errMsgTxt = "";
        String prchReqNum = rs.getString(PRCH_REQ_NUM);
        String prchReqLineNum = rs.getString(PRCH_REQ_LINE_NUM);
        BigDecimal prchReqLineSubNum = rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM);
        String configTpCd = rs.getString(CONFIG_TP_CD);
        String prchReqTpCd = rs.getString(PRCH_REQ_TP_CD);

        // Check Allocation data exist
        // a) Config ID exist
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(PRCH_REQ_NUM, prchReqNum);
        String svcMachMaintAvalFlg = (String) glSsmBatchClient.queryObject("chkAlcByConf", param);
        if (hasValue(svcMachMaintAvalFlg) && ZYPConstant.FLG_OFF_N.equals(svcMachMaintAvalFlg)) {
            errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1370M);
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            return false;
        }

        // b) Config ID not exist
        param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(PRCH_REQ_NUM, prchReqNum);
        svcMachMaintAvalFlg = (String) glSsmBatchClient.queryObject("chkAlcByNoConf", param);
        if (hasValue(svcMachMaintAvalFlg) && ZYPConstant.FLG_OFF_N.equals(svcMachMaintAvalFlg)) {
            errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1370M);
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            return false;
        }

        // Get Order Information
        if (ZYPCommonFunc.hasValue(configTpCd)) {
            param = new HashMap<String, Object>();
            param.put(GLBL_CMPY_CD, globalCompanyCode);
            param.put(DS_COND_CONST_GRP_ID, NPBB0010_DS_ORD_TP);
            param.put(DS_COND_CONST_VAL_TXT_01, ZYPConstant.FLG_ON_Y);
            if (PRCH_REQ_TP.EXPENSE_ORDER.equals(prchReqTpCd)) {
                param.put(DS_COND_CONST_VAL_TXT_02, EXPENSE_ORDER);
            }

            glOrdInfoForConfig = (Map<String, Object>) glSsmBatchClient.queryObject("getOrdInfo", param);
            if (glOrdInfoForConfig == null) {
                errMsgTxt = S21MessageFunc.clspGetMessage(NPAM0076E, new String[] {DS_ORD_TP_CD });
                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                return false;
            }
        } else {
            param = new HashMap<String, Object>();
            param.put(GLBL_CMPY_CD, globalCompanyCode);
            param.put(DS_COND_CONST_GRP_ID, NPBB0010_DS_ORD_TP);
            param.put(DS_COND_CONST_VAL_TXT_01, ZYPConstant.FLG_OFF_N);
            if (PRCH_REQ_TP.EXPENSE_ORDER.equals(prchReqTpCd)) {
                param.put(DS_COND_CONST_VAL_TXT_02, EXPENSE_ORDER);
            }

            glOrdInfoForNoConfig = (Map<String, Object>) glSsmBatchClient.queryObject("getOrdInfo", param);
            if (glOrdInfoForNoConfig == null) {
                errMsgTxt = S21MessageFunc.clspGetMessage(NPAM0076E, new String[] {DS_ORD_TP_CD });
                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                return false;
            }
        }

        // Get Ship To Customer Information
        param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(prchReqTpCd)) {
            param.put(SHIP_TO_CUST_CD, rs.getString(DEST_INVTY_LOC_CD));
        } else {
            param.put(SHIP_TO_CUST_CD, rs.getString(DEST_RTL_WH_CD));
        }
        Map<String, Object> shipToCustInfo = (Map<String, Object>) glSsmBatchClient.queryObject("getShipToCustInfo", param);
        if (shipToCustInfo == null) {
            errMsgTxt = S21MessageFunc.clspGetMessage(NPAM0076E, new String[] {DEST_RTL_WH_CD });
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            return false;
        }

        // Get Bill To Customer Information
        // START 2019/10/03 M.Naito [QC#52809,MOD]
        // QC#52809 Start
        String billToCustCd = "";
        String billToCustAcctCd = "";
        String soldToCustLocCd = "";
        String sellToCustCd = "";

        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(prchReqTpCd)) {
            param.put(RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
            Map<String, Object> shipToCustMap = (Map<String, Object>) glSsmBatchClient.queryObject("getShipToCustInfoForExpenseOrder", param);
            if (shipToCustMap == null) {
                errMsgTxt = S21MessageFunc.clspGetMessage(NPAM0076E, new String[] {SHIP_TO_CUST_CD });
                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                return false;
            }

            String defBillToCustCd = NWXC150001DefaultCustomer.getRelatedBillTo(globalCompanyCode, (String) shipToCustMap.get(SHIP_TO_CUST_CD));

            if (defBillToCustCd == null) {

                String acctNum = (String) shipToCustMap.get(SELL_TO_CUST_CD);

                NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(globalCompanyCode, acctNum, null, null, //
                        NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_ON_Y, ZYPCommonFunc.hasValue(configTpCd));

                if (nMZC610001BSPMsg == null) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPBM0004E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD, DISPLAY_NM_BILL_TO_CUST_CD });
                    errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                }

                defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();

                if (defBillToCustCd == null) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPBM0004E, new String[] {DISPLAY_NM_SHIP_TO_CUST_CD, DISPLAY_NM_BILL_TO_CUST_CD });
                    errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                    return false;
                }
            }

            billToCustCd = defBillToCustCd;

            // get Sold To
            sellToCustCd = (String) shipToCustMap.get(SELL_TO_CUST_CD);
            soldToCustLocCd = null;
            NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(globalCompanyCode, sellToCustCd, null, null, NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N, ZYPCommonFunc.hasValue(configTpCd));
            if (nMZC610001BSPMsg != null && nMZC610001BSPMsg.DefaultBillShipList.getValidCount() != 0) {
                soldToCustLocCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
            }

            param = new HashMap<String, Object>();
            param.put(GLBL_CMPY_CD, globalCompanyCode);
            param.put(BILL_TO_CUST_CD, billToCustCd);
            param.put(RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
            Map<String, Object> billToCustMap = (Map<String, Object>) glSsmBatchClient.queryObject("getBillToCustInfo", param);
            if (billToCustMap == null) {
                errMsgTxt = S21MessageFunc.clspGetMessage(NPAM0076E, new String[] {BILL_TO_CUST_CD });
                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                return false;
            }
            billToCustAcctCd = (String) billToCustMap.get(DS_ACCT_NUM);

            if (!ZYPCommonFunc.hasValue(soldToCustLocCd)) {
                soldToCustLocCd = billToCustCd;
                sellToCustCd = billToCustAcctCd;
            }

        } else {

            NMZC610001PMsg billToCustPmsg = new NMZC610001PMsg();
            ZYPEZDItemValueSetter.setValue(billToCustPmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(billToCustPmsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
            ZYPEZDItemValueSetter.setValue(billToCustPmsg.dsAcctNum_I1, (String) shipToCustInfo.get(SELL_TO_CUST_CD));
            new NMZC610001().execute(billToCustPmsg, ONBATCH_TYPE.BATCH);
            if (S21ApiUtil.isXxMsgId(billToCustPmsg)) {
                List<String> msgIdList = S21ApiUtil.getXxMsgIdList(billToCustPmsg);
                for (String msgId : msgIdList) {
                    if (msgId.endsWith("E")) {
                        errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NMZC6100, S21MessageFunc.clspGetMessage(msgId) });
                        break;
                    }
                }
                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                return false;
            }

            if (billToCustPmsg.DefaultBillShipList.getValidCount() > 0) {
                billToCustCd = billToCustPmsg.DefaultBillShipList.no(0).billToCustCd.getValue();
            }
        }
        // QC#52809 End

        // Insert DS_IMPT_ORD Header
        if (START_LINE_NUM.equals(wkLineNum)) {
            if (ZYPCommonFunc.hasValue(configTpCd)) {
//                insertDsImptOrd(dsImptOrdPk, rs, glOrdInfoForConfig, shipToCustInfo, billToCustCd, pFlgDropShip);
                insertDsImptOrd(dsImptOrdPk, rs, glOrdInfoForConfig, shipToCustInfo, billToCustCd, pFlgDropShip, billToCustAcctCd, soldToCustLocCd, sellToCustCd);
            } else {
//                insertDsImptOrd(dsImptOrdPk, rs, glOrdInfoForNoConfig, shipToCustInfo, billToCustCd, pFlgDropShip);
                insertDsImptOrd(dsImptOrdPk, rs, glOrdInfoForNoConfig, shipToCustInfo, billToCustCd, pFlgDropShip, billToCustAcctCd, soldToCustLocCd, sellToCustCd);
            }
        }
        // END 2019/10/03 M.Naito [QC#52809,MOD]

        // Save data for DS_IMPT_ORD_CONFIG to configItemLineInfoMapList
        Map<String, Object> configItemLineInfoMap = new HashMap<String, Object>();
        configItemLineInfoMap.put(DS_IMPT_ORD_PK, dsImptOrdPk);
        configItemLineInfoMap.put(DS_IMPT_ORD_CONFIG_PK, dsImptOrdConfigPk);
        configItemLineInfoMap.put(DS_ORD_POSN_NUM, dsOrdPosnNum);
        configItemLineInfoMap.put(PRCH_REQ_NUM, prchReqNum);
        configItemLineInfoMap.put(PRCH_REQ_LINE_NUM, prchReqLineNum);
        configItemLineInfoMap.put(PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);
        configItemLineInfoMap.put(MDSE_CD, rs.getString(MDSE_CD));
        configItemLineInfoMap.put(INSTL_BASE_CTRL_FLG, rs.getString(INSTL_BASE_CTRL_FLG));
        configItemLineInfoMap.put(MDSE_TP_CTX_TP_CD, rs.getString(MDSE_TP_CTX_TP_CD));
        configItemLineInfoMap.put(SELL_TO_CUST_CD, (String) shipToCustInfo.get(SELL_TO_CUST_CD));
        if (pFlgDropShip) {
            configItemLineInfoMap.put(LOC_NM, rs.getString(SHIP_TO_LOC_NM));
            configItemLineInfoMap.put(ADDL_LOC_NM, rs.getString(SHIP_TO_ADDL_LOC_NM));
            configItemLineInfoMap.put(FIRST_LINE_ADDR, rs.getString(SHIP_TO_FIRST_LINE_ADDR));
            configItemLineInfoMap.put(SCD_LINE_ADDR, rs.getString(SHIP_TO_SCD_LINE_ADDR));
            configItemLineInfoMap.put(THIRD_LINE_ADDR, rs.getString(SHIP_TO_THIRD_LINE_ADDR));
            configItemLineInfoMap.put(FRTH_LINE_ADDR, rs.getString(SHIP_TO_FRTH_LINE_ADDR));
            configItemLineInfoMap.put(CTY_ADDR, rs.getString(SHIP_TO_CTY_ADDR));
            configItemLineInfoMap.put(ST_CD, rs.getString(SHIP_TO_ST_CD));
            configItemLineInfoMap.put(PROV_NM, rs.getString(SHIP_TO_PROV_NM));
            configItemLineInfoMap.put(CNTY_NM, rs.getString(SHIP_TO_CNTY_NM));
            configItemLineInfoMap.put(POST_CD, rs.getString(SHIP_TO_POST_CD));
            configItemLineInfoMap.put(CTRY_CD, rs.getString(SHIP_TO_CTRY_CD));
            configItemLineInfoMap.put(FIRST_REF_CMNT_TXT, rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT));
            configItemLineInfoMap.put(SCD_REF_CMNT_TXT, rs.getString(SHIP_TO_SCD_REF_CMNT_TXT));
        } else {
            configItemLineInfoMap.put(LOC_NM, (String) shipToCustInfo.get(LOC_NM));
            configItemLineInfoMap.put(ADDL_LOC_NM, (String) shipToCustInfo.get(ADDL_LOC_NM));
            configItemLineInfoMap.put(FIRST_LINE_ADDR, (String) shipToCustInfo.get(FIRST_LINE_ADDR));
            configItemLineInfoMap.put(SCD_LINE_ADDR, (String) shipToCustInfo.get(SCD_LINE_ADDR));
            configItemLineInfoMap.put(THIRD_LINE_ADDR, (String) shipToCustInfo.get(THIRD_LINE_ADDR));
            configItemLineInfoMap.put(FRTH_LINE_ADDR, (String) shipToCustInfo.get(FRTH_LINE_ADDR));
            configItemLineInfoMap.put(CTY_ADDR, (String) shipToCustInfo.get(CTY_ADDR));
            configItemLineInfoMap.put(ST_CD, (String) shipToCustInfo.get(ST_CD));
            configItemLineInfoMap.put(PROV_NM, (String) shipToCustInfo.get(PROV_NM));
            configItemLineInfoMap.put(CNTY_NM, (String) shipToCustInfo.get(CNTY_NM));
            configItemLineInfoMap.put(POST_CD, (String) shipToCustInfo.get(POST_CD));
            configItemLineInfoMap.put(CTRY_CD, (String) shipToCustInfo.get(CTRY_CD));
            configItemLineInfoMap.put(FIRST_REF_CMNT_TXT, (String) shipToCustInfo.get(FIRST_REF_CMNT_TXT));
            configItemLineInfoMap.put(SCD_REF_CMNT_TXT, (String) shipToCustInfo.get(SCD_REF_CMNT_TXT));
        }
        configItemLineInfoMap.put(BILL_TO_CUST_CD, billToCustCd);
        // START 2019/10/03 M.Naito [QC#52809,ADD]
        if (ZYPCommonFunc.hasValue(billToCustAcctCd)) {
            configItemLineInfoMap.put(BILL_TO_CUST_ACCT_CD, billToCustAcctCd);
        }
        // END 2019/10/03 M.Naito [QC#52809,ADD]
        configItemLineInfoMap.put(DS_ORD_POSN_NUM, dsOrdPosnNum);
        configItemLineInfoMap.put(CONFIG_TP_CD, configTpCd);
        configItemLineInfoMap.put(SVC_CONFIG_MSTR_PK, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        configItemLineInfoMap.put(DEST_INVTY_LOC_CD, rs.getString(DEST_INVTY_LOC_CD));
        configItemLineInfoMap.put(DEST_RTL_WH_CD, rs.getString(DEST_RTL_WH_CD));
        configItemLineInfoMapList.add(configItemLineInfoMap);

        // Get Serial Number Before Insert DS_IMPT_ORD_DTL
        List<String> serNumList = getSerNumList(prchReqNum, prchReqLineNum, prchReqLineSubNum);
        BigDecimal prchReqQty = rs.getBigDecimal(PRCH_REQ_QTY);
        // START 2023/06/13 S.Dong [QC#55629, ADD]
        if (PRCH_REQ_TP.WH_TRANSFER.equals(rs.getString(PRCH_REQ_TP_CD))) {
            NPZC136001PMsg pMsg = getMinVndQty(rs, prchReqQty);
            if (pMsg !=null  && prchReqQty.compareTo(pMsg.prchReqQty.getValue()) < 0) {
                prchReqQty = pMsg.prchReqQty.getValue();
           }
        }
        // END 2023/06/13 S.Dong [QC#55629, ADD]
        int serNumCnt = 1;
        if (prchReqQty != null && serNumList != null) {
            BigDecimal listSize = new BigDecimal(serNumList.size());
            serNumCnt = serNumList.size();
            if (prchReqQty.compareTo(listSize) > 0) {
                serNumCnt = serNumList.size() + 1;
            }
        }

        // Insert DS_IMPT_ORD_DTL
//        String configTpCd = rs.getString(CONFIG_TP_CD);
        if ((configTpCd != null && BigDecimal.ZERO.compareTo(prchReqLineSubNum) != 0) || (configTpCd == null)) {
            for (int i = 0; i < serNumCnt; i++) {
                String serNum = "";
                if (serNumList != null && i < serNumList.size()) {
                    serNum = serNumList.get(i);
                }
                BigDecimal ordQty = BigDecimal.ONE;
                if (i == serNumList.size() && prchReqQty != null) {
                    ordQty = prchReqQty.subtract(new BigDecimal(serNumList.size()));
                }
                String prcCatgCd = "";
                if (ZYPCommonFunc.hasValue(configTpCd)) {
                    prcCatgCd = (String) glOrdInfoForConfig.get(DEF_PRC_CATG_CD);
                } else {
                    prcCatgCd = (String) glOrdInfoForNoConfig.get(DEF_PRC_CATG_CD);
                }
                // QC#30618
                insertDsImptOrdDtl(dsImptOrdPk, dsImptOrdConfigPk, rs, shipToCustInfo, ordQty, serNum, dsOrdPosnNum, prcCatgCd, pFlgDropShip, svcMachMstrPkList);
            }
        }

        // Insert DS_IMPT_ORD_DELY_INFO
        if (START_LINE_NUM.equals(wkLineNum)) {
            insertDsImptOrdDelyInfo(rs, dsImptOrdPk);
        }
        // START 2023/06/13 S.Dong [QC#55629, ADD]
        // update PR Request Qty
        if (prchReqQty.compareTo(rs.getBigDecimal(PRCH_REQ_QTY)) != 0){
            updatePrReqQty(prchReqNum, prchReqLineNum, prchReqQty, prchReqLineSubNum);
        }
        // END 2023/06/13 S.Dong [QC#55629, ADD]
        // Call PR Update API
        // START 2023/06/13 S.Dong [QC#55629, MOD]
//        NPZC103001PMsg prUpdPmsg = callPrUpdApi(rs, null, null);
        NPZC103001PMsg prUpdPmsg = callPrUpdApi(rs, null, null, prchReqQty);
        // END 2023/06/13 S.Dong [QC#55629, MOD]
        if (S21ApiUtil.isXxMsgId(prUpdPmsg)) {
            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(prUpdPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                    break;
                }
            }
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            return false;
        }

        return true;
    }

    /**
     * Insert DS_IMPT_ORD Header
     */
    // START 2019/10/03 M.Naito [QC#52809,MOD]
//    private void insertDsImptOrd(BigDecimal dsImptOrdPk, ResultSet rs, Map<String, Object> ordInfo, Map<String, Object> shipToCustInfo, String billToCustCd, boolean pFlgDropShip) throws SQLException {
    private void insertDsImptOrd(BigDecimal dsImptOrdPk, ResultSet rs, Map<String, Object> ordInfo, Map<String, Object> shipToCustInfo, String billToCustCd, boolean pFlgDropShip, String billToCustAcctCd, String soldToCustLocCd, String sellToCustCd) throws SQLException {
        // Insert DS_IMPT_ORD Header
        DS_IMPT_ORDTMsg tmsg = new DS_IMPT_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdPk, dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(tmsg.cpoSrcTpCd, IR);
        // 2019/01/08 QC#29779 Mod Start
        //ZYPEZDItemValueSetter.setValue(tmsg.ordSrcImptTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmssSSS"));
        ZYPEZDItemValueSetter.setValue(tmsg.ordSrcImptTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        // 2019/01/08 QC#29779 Mod End
        ZYPEZDItemValueSetter.setValue(tmsg.ordSrcRefNum, rs.getString(PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(tmsg.imptStsCd, IMPT_STS.NOT_PROCESSED);
        ZYPEZDItemValueSetter.setValue(tmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(tmsg.dsOrdCatgCd, (String) ordInfo.get(DS_ORD_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.dsOrdTpCd, (String) ordInfo.get(DS_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.custIssPoNum, rs.getString(CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(tmsg.custIssPoDt, rs.getString(CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(tmsg.billToCustAcctCd, (String) shipToCustInfo.get(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.billToCustCd, billToCustCd);
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCustAcctCd, (String) shipToCustInfo.get(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.sellToCustCd, (String) shipToCustInfo.get(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.soldToCustLocCd, billToCustCd);
        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(rs.getString(PRCH_REQ_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCustCd, rs.getString(DEST_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.billToCustAcctCd, billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(tmsg.sellToCustCd, sellToCustCd);
            ZYPEZDItemValueSetter.setValue(tmsg.soldToCustLocCd, soldToCustLocCd);
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCustCd, rs.getString(DEST_RTL_WH_CD));
        }
        // QC#22517 2018/03/27 Start
        if (pFlgDropShip) {
            ZYPEZDItemValueSetter.setValue(tmsg.dropShipFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tmsg.shipToLocNm, rs.getString(SHIP_TO_LOC_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToAddlLocNm, rs.getString(SHIP_TO_ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFirstLineAddr, rs.getString(SHIP_TO_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToScdLineAddr, rs.getString(SHIP_TO_SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToThirdLineAddr, rs.getString(SHIP_TO_THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFrthLineAddr, rs.getString(SHIP_TO_FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCtyAddr, rs.getString(SHIP_TO_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToStCd, rs.getString(SHIP_TO_ST_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToProvNm, rs.getString(SHIP_TO_PROV_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCntyNm, rs.getString(SHIP_TO_CNTY_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToPostCd, rs.getString(SHIP_TO_POST_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCtryCd, rs.getString(SHIP_TO_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipTo01RefCmntTxt, rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(tmsg.shipTo02RefCmntTxt, rs.getString(SHIP_TO_SCD_REF_CMNT_TXT));
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tmsg.shipToLocNm, (String) shipToCustInfo.get(LOC_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToAddlLocNm, (String) shipToCustInfo.get(ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFirstLineAddr, (String) shipToCustInfo.get(FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToScdLineAddr, (String) shipToCustInfo.get(SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToThirdLineAddr, (String) shipToCustInfo.get(THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFrthLineAddr, (String) shipToCustInfo.get(FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCtyAddr, (String) shipToCustInfo.get(CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToStCd, (String) shipToCustInfo.get(ST_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToProvNm, (String) shipToCustInfo.get(PROV_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCntyNm, (String) shipToCustInfo.get(CNTY_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToPostCd, (String) shipToCustInfo.get(POST_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCtryCd, (String) shipToCustInfo.get(CTRY_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipTo01RefCmntTxt, (String) shipToCustInfo.get(FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(tmsg.shipTo02RefCmntTxt, (String) shipToCustInfo.get(SCD_REF_CMNT_TXT));
        }
        // QC#22517 2018/03/27 End
        ZYPEZDItemValueSetter.setValue(tmsg.adminPsnCd, rs.getString(ADMIN_PSN_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.rddDt, rs.getString(RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(tmsg.frtCondCd, rs.getString(FRT_COND_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.carrCd, rs.getString(CARR_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.shpgSvcLvlCd, rs.getString(SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.frtChrgToCd, rs.getString(FRT_CHRG_TO_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.frtChrgMethCd, rs.getString(FRT_CHRG_METH_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.carrAcctNum, rs.getString(CARR_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tmsg.prcBaseDt, salesDate);
        String slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(ITRL_TRNSF_DEF_SLS_REP_TOC_CD, globalCompanyCode);
        if (hasValue(slsRepTocCd)) {
            ZYPEZDItemValueSetter.setValue(tmsg.slsRepTocCd, slsRepTocCd);
        }
        ZYPEZDItemValueSetter.setValue(tmsg.prcCatgCd, (String) ordInfo.get(DEF_PRC_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.flPrcListCd, (String) ordInfo.get(DEF_PRC_CATG_CD));

        ZYPEZDItemValueSetter.setValue(tmsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.ordHdrEdtblFlg, ZYPConstant.FLG_OFF_N);
        // 2016/11/29 QC#16265 Add Start
        ZYPEZDItemValueSetter.setValue(tmsg.maintOnlyFlg, ZYPConstant.FLG_OFF_N);
        // 2016/11/29 QC#16265 Add End
        EZDTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            throw new SQLException();
        }
    }
    // END 2019/10/03 M.Naito [QC#52809,MOD]

    /**
     * Insert DS_IMPT_ORD_DTL
     * Mod QC#30618.
     */
    private void insertDsImptOrdDtl(BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, ResultSet rs, Map<String, Object> shipToCustInfo, BigDecimal ordQty, String serNum, BigDecimal dsOrdPosnNum, String prcCatgCd, boolean pFlgDropShip, List<BigDecimal> svcMachMstrPkList) throws SQLException {

        // Retrieve SVC_MACH_MSTR_PK from SVC_MACH_MSTR
        BigDecimal svcMachMstrPk = null;
        String prchReqNum = rs.getString(PRCH_REQ_NUM);
        String prchReqLineNum = rs.getString(PRCH_REQ_LINE_NUM);
        BigDecimal prchReqLineSubNum = rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM);
        ArrayList<String> serNumList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(serNum)) {
            serNumList.add(serNum);
        }
        List<Map<String, Object>> svcMachMstrList = getAvailableMachMstrList(prchReqNum, prchReqLineNum, prchReqLineSubNum, serNumList);
        // Result may have more than one in case of Non-serial IB item. E.G.: 1x[Serial IB main]+2x[same Non-serial IB parts]
        // QC#30618
        if (svcMachMstrList.size() == 1) {
            Map<String, Object> svcMachMstr = svcMachMstrList.get(0);
            svcMachMstrPk = (BigDecimal) svcMachMstr.get(SVC_MACH_MSTR_PK);
        } else if (svcMachMstrList.size() > 1) {
            for (int i = 0; i < svcMachMstrList.size(); i++) {
                Map<String, Object> svcMachMstr = svcMachMstrList.get(i);
                svcMachMstrPk = (BigDecimal) svcMachMstr.get(SVC_MACH_MSTR_PK);
                if (svcMachMstrPkList.contains(svcMachMstrPk)) {
                    continue;
                }
                break;
            }
            svcMachMstrPkList.add(svcMachMstrPk);
        }

        // Insert DS_IMPT_ORD_DTL
        DS_IMPT_ORD_DTLTMsg tmsg = new DS_IMPT_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdPk, dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(tmsg.ordSrcRefLineNum, rs.getString(PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tmsg.ordSrcRefLineSubNum, rs.getString(PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(tmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCustCd, rs.getString(DEST_INVTY_LOC_CD));
        // QC#22517 2018/03/27 Start
        if (pFlgDropShip) {
            ZYPEZDItemValueSetter.setValue(tmsg.dropShipFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tmsg.shipToLocNm, rs.getString(SHIP_TO_LOC_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToAddlLocNm, rs.getString(SHIP_TO_ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFirstLineAddr, rs.getString(SHIP_TO_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToScdLineAddr, rs.getString(SHIP_TO_SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToThirdLineAddr, rs.getString(SHIP_TO_THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFrthLineAddr, rs.getString(SHIP_TO_FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFirstRefCmntTxt, rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToScdRefCmntTxt, rs.getString(SHIP_TO_SCD_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCtyAddr, rs.getString(SHIP_TO_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToStCd, rs.getString(SHIP_TO_ST_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToProvNm, rs.getString(SHIP_TO_PROV_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCntyNm, rs.getString(SHIP_TO_CNTY_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToPostCd, rs.getString(SHIP_TO_POST_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCtryCd, rs.getString(SHIP_TO_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.invtyLocCd, (String) shipToCustInfo.get(SRC_INVTY_LOC_CD));
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tmsg.shipToLocNm, (String) shipToCustInfo.get(LOC_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToAddlLocNm, (String) shipToCustInfo.get(ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFirstLineAddr, (String) shipToCustInfo.get(FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToScdLineAddr, (String) shipToCustInfo.get(SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToThirdLineAddr, (String) shipToCustInfo.get(THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFrthLineAddr, (String) shipToCustInfo.get(FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToFirstRefCmntTxt, (String) shipToCustInfo.get(FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToScdRefCmntTxt, (String) shipToCustInfo.get(SCD_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCtyAddr, (String) shipToCustInfo.get(CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToStCd, (String) shipToCustInfo.get(ST_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToProvNm, (String) shipToCustInfo.get(PROV_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCntyNm, (String) shipToCustInfo.get(CNTY_NM));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToPostCd, (String) shipToCustInfo.get(POST_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.shipToCtryCd, (String) shipToCustInfo.get(CTRY_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.invtyLocCd, (String) shipToCustInfo.get(SRC_INVTY_LOC_CD));
        }
        // QC#22517 2018/03/27 End
        ZYPEZDItemValueSetter.setValue(tmsg.entDealNetUnitPrcAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(tmsg.entFuncNetUnitPrcAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(tmsg.prcBaseDt, salesDate);
        ZYPEZDItemValueSetter.setValue(tmsg.rddDt, rs.getString(RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(tmsg.stkStsCd, rs.getString(FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.configItemFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tmsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(tmsg.rtlWhCd, rs.getString(SRC_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.rtlSwhCd, rs.getString(SRC_RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(tmsg.ordQty, ordQty);
        ZYPEZDItemValueSetter.setValue(tmsg.uomCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.setItemShipCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.custIstlFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.supdLockFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(tmsg.custUomCd, CUST_UOM_EA);
        ZYPEZDItemValueSetter.setValue(tmsg.ordCustUomQty, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tmsg.dsOrdPosnNum, String.valueOf(dsOrdPosnNum));
        ZYPEZDItemValueSetter.setValue(tmsg.prcCatgCd, prcCatgCd);
        // QC#18036 Add.
        ZYPEZDItemValueSetter.setValue(tmsg.ordLineSrcCd, ORD_LINE_SRC.INTERNAL);

        String slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(ITRL_TRNSF_DEF_SLS_REP_TOC_CD, globalCompanyCode);
        if (hasValue(slsRepTocCd)) {
            ZYPEZDItemValueSetter.setValue(tmsg.slsRepOrSlsTeamTocCd, slsRepTocCd);
        }
        ZYPEZDItemValueSetter.setValue(tmsg.imptLineFlg, ZYPConstant.FLG_ON_Y); // QC#11561
        // QC#13215 9Seg Add
        if (PRCH_REQ_TP.EXPENSE_ORDER.equals(rs.getString(PRCH_REQ_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(tmsg.coaCmpyCd, rs.getString(COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.coaAfflCd, rs.getString(COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.coaBrCd, rs.getString(COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.coaChCd, rs.getString(COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.coaCcCd, rs.getString(COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.coaAcctCd, rs.getString(COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.coaProjCd, rs.getString(COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.coaExtnCd, rs.getString(COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(tmsg.coaProdCd, rs.getString(COA_PROD_CD));
        }
        // QC#13215 9Seg End
        // 2016/11/29 QC#16265 Add Start
        ZYPEZDItemValueSetter.setValue(tmsg.preExistFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.finItemLineFlg, ZYPConstant.FLG_OFF_N);
        // 2016/11/29 QC#16265 Add End
        EZDTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            throw new SQLException();
        }
    }

    /**
     * insertDsImptOrdDelyInfo
     * @param rs ResultSet
     */
    private void insertDsImptOrdDelyInfo(ResultSet rs, BigDecimal dsImptOrdPk) throws SQLException {

        String prchReqCmntTxt = rs.getString(PRCH_REQ_CMNT_TXT);
        if (!ZYPCommonFunc.hasValue(prchReqCmntTxt)) {
            // No data to insert
            return;
        }

        DS_IMPT_ORD_DELY_INFOTMsg tmsg = new DS_IMPT_ORD_DELY_INFOTMsg();
        BigDecimal dsImptOrdDelyInfoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DELY_INFO_SQ);
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdDelyInfoPk, dsImptOrdDelyInfoPk);
        ZYPEZDItemValueSetter.setValue(tmsg.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.elevAvalFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdPk, dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(tmsg.delyAddlCmntTxt, prchReqCmntTxt);
        EZDTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            throw new SQLException();
        }
    }

    /**
     * call PR Update API
     */
    // START 2023/06/13 S.Dong [QC#55629, MOD]
    //private NPZC103001PMsg callPrUpdApi(ResultSet rs, String invtyOrdNum, String invtyOrdLineNum) throws SQLException {
    private NPZC103001PMsg callPrUpdApi(ResultSet rs, String invtyOrdNum, String invtyOrdLineNum, BigDecimal prchReqQty) throws SQLException {
    // END 2023/06/13 S.Dong [QC#55629, MOD
        NPZC103001PMsg pmsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pmsg.eventId, NPZC103001Constant.EVENT_ORDER_RELEASE);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqNum, rs.getString(PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqStsCd, PRCH_REQ_STS.OPEN);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqLineNum, rs.getString(PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqLineSubNum, rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqLineStsCd, PRCH_REQ_LINE_STS.AWAITING_SHIPPING);
        // START 2023/06/13 S.Dong [QC#55629, MOD]
//        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqRelQty, rs.getBigDecimal(PRCH_REQ_QTY));
        if (prchReqQty != null) {
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqRelQty, prchReqQty);
        } else {
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqRelQty, rs.getBigDecimal(PRCH_REQ_QTY));
        }
        // END 2023/06/13 S.Dong [QC#55629, MOD]
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).poOrdNum, rs.getString(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).poOrdDtlLineNum, rs.getString(PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).invtyOrdNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).invtyOrdLineNum, invtyOrdLineNum);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).trxRefNum, rs.getString(TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).trxRefLineNum, rs.getString(TRX_REF_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).trxRefLineSubNum, rs.getString(TRX_REF_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqLineCmntTxt, rs.getString(PRCH_REQ_LINE_CMNT_TXT));
        pmsg.prchReqInfo.setValidCount(1);
        new NPZC103001().execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;
    }
    /**
     * call PR Update API
     */
    private NPZC103001PMsg callPrUpdApiForMissingParts(List<Map<String, Object>> shpgPlnInfoList, String soNum) throws SQLException {
        NPZC103001PMsg pmsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pmsg.eventId, NPZC103001Constant.EVENT_ORDER_RELEASE);
        for (int i = 0; i < shpgPlnInfoList.size(); i++) {
            Map<String, Object> shpgPlnInfo = shpgPlnInfoList.get(i);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqNum, (String) shpgPlnInfo.get(PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqStsCd, PRCH_REQ_STS.OPEN);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineNum, (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineSubNum, (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM));
            // QC#22255
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineStsCd, PRCH_REQ_LINE_STS.SHIPPED);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqRelQty, (BigDecimal) shpgPlnInfo.get(PRCH_REQ_QTY));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).poOrdNum, (String) shpgPlnInfo.get(PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).poOrdDtlLineNum, (String) shpgPlnInfo.get(PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).trxRefNum, (String) shpgPlnInfo.get(TRX_REF_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).trxRefLineNum, (String) shpgPlnInfo.get(TRX_REF_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).trxRefLineSubNum, (String) shpgPlnInfo.get(TRX_REF_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineCmntTxt, (String) shpgPlnInfo.get(PRCH_REQ_LINE_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).soNum, soNum);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).invtyOrdNum, (String) shpgPlnInfo.get(INVTY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).invtyOrdLineNum, (String) shpgPlnInfo.get(INVTY_ORD_LINE_NUM));
            pmsg.prchReqInfo.setValidCount(i + 1);
        }
        new NPZC103001().execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;
    }

    /**
     * call PR Update API
     */
    private NPZC103001PMsg callPrUpdApiForSO(List<Map<String, Object>> shpgPlnInfoMapList, String soNum) throws SQLException {
        NPZC103001PMsg pmsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pmsg.eventId, NPZC103001Constant.EVENT_ORDER_RELEASE);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqStsCd, PRCH_REQ_STS.OPEN);

        for (int i = 0; i < shpgPlnInfoMapList.size(); i++) {
            Map<String, Object> shpgPlnInfo = shpgPlnInfoMapList.get(i);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqNum, (String) shpgPlnInfo.get(PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineNum, (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineSubNum, (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineStsCd, PRCH_REQ_LINE_STS.AWAITING_SHIPPING);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqRelQty, (BigDecimal) shpgPlnInfo.get(PRCH_REQ_QTY));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).poOrdNum, (String) shpgPlnInfo.get(PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).poOrdDtlLineNum, (String) shpgPlnInfo.get(PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).invtyOrdNum, (String) shpgPlnInfo.get(TRX_HDR_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).invtyOrdLineNum, (String) shpgPlnInfo.get(TRX_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).wrkOrdNum, (String) shpgPlnInfo.get(WRK_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).wrkOrdDtlLineNum, (String) shpgPlnInfo.get(WRK_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).trxRefNum, (String) shpgPlnInfo.get(TRX_REF_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).trxRefLineNum, (String) shpgPlnInfo.get(TRX_REF_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).trxRefLineSubNum, (String) shpgPlnInfo.get(TRX_REF_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).soNum, soNum);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineCmntTxt, (String) shpgPlnInfo.get(PRCH_REQ_LINE_CMNT_TXT));
            pmsg.prchReqInfo.setValidCount(i + 1);
        }
        new NPZC103001().execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;
    }

    /**
     * Create Inventory Order Process
     */
    private boolean createInvOrdProc(ResultSet rs, String wkLineNum, List<Map<String, Object>> shpgPlnInfoMapList) throws SQLException {
        String errMsgTxt = "";
        List<String> msgIdList;

        // Get Serial Number
        String prchReqNum = rs.getString(PRCH_REQ_NUM);
        String prchReqLineNum = rs.getString(PRCH_REQ_LINE_NUM);
        BigDecimal prchReqLineSubNum = rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM);
        BigDecimal prchReqQty = rs.getBigDecimal(PRCH_REQ_QTY);
        String trxRefNum = rs.getString(TRX_REF_NUM);
        String trxRefLineNum = rs.getString(TRX_REF_LINE_NUM);
        String trxRefLineSubNum = rs.getString(TRX_REF_LINE_SUB_NUM);
        String prchReqLineCmntTxt = rs.getString(PRCH_REQ_LINE_CMNT_TXT);
        String poOrdNum = rs.getString(PO_ORD_NUM);
        String poOrdDtlLineNum = rs.getString(PO_ORD_DTL_LINE_NUM);
        String wrkOrdNum = rs.getString(WRK_ORD_NUM);
        String wrkOrdDtlLineNum = rs.getString(WRK_ORD_DTL_LINE_NUM);

        List<String> serNumList = getSerNumList(prchReqNum, prchReqLineNum, prchReqLineSubNum);

        // Get Service Machine Master PK
        // QC#55753 Mod Start
        List<Map<String, Object>> macMstList;
        if(serNumList != null && serNumList.size() > 0) {
            macMstList = getMacMstList(prchReqNum, prchReqLineNum, prchReqLineSubNum, serNumList);
        } else {
            macMstList = getNonSerMacMstList(prchReqNum, prchReqLineNum, prchReqLineSubNum);
        }
        // QC#55753 Mod End

        // Get TRX_CD and TRX_RSN_CD by SCE Order Type before Call
        // Inventory Order API
        Map<String, Object> sceOrdTp = getSceOrdTp(rs.getString(SCE_ORD_TP_CD), INBD_OTBD.OUTBOUND);
        String trxCd = "";
        String trxRsnCd = "";
        if (sceOrdTp != null) {
            trxCd = (String) sceOrdTp.get(TRX_CD);
            trxRsnCd = (String) sceOrdTp.get(TRX_RSN_CD);
        }

        // Call Inventory Order API
        NLZC003001PMsg invOrdPmsg = callInvOrdApi(rs, serNumList, trxCd, trxRsnCd, wkLineNum);
        if (S21ApiUtil.isXxMsgId(invOrdPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(invOrdPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NLZC0030, S21MessageFunc.clspGetMessage(msgId) });
                    break;
                }
            }
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            return false;
        }

        // Call Allocation for non CPO API
        NWZC107001PMsg alcPmsg = callAlcApi(rs, invOrdPmsg, trxCd, trxRsnCd);
        if (S21ApiUtil.isXxMsgId(alcPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(alcPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NWZC1070, S21MessageFunc.clspGetMessage(msgId) });
                    break;
                }
            }
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            return false;
        }

        // Call Shipping Plan Update API
        NWZC003001PMsg shpgPlnPmsg = callShpgPlnUpdApi(rs, invOrdPmsg, alcPmsg);
        if (S21ApiUtil.isXxMsgId(shpgPlnPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(shpgPlnPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NWZC0030, S21MessageFunc.clspGetMessage(msgId) });
                    break;
                }
            }
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            return false;
        }

        String trxSrcTpCd = rs.getString(TRX_SRC_TP_CD);
        List<Map<String, Object>> shpgPlnListWrk = getShpgPlnList(glInvtyOrdNum, wkLineNum, START_LINE_NUM, trxSrcTpCd);
        for (Map<String, Object> shpgPlnInfo : shpgPlnListWrk) {
            shpgPlnInfo.put(PRCH_REQ_NUM, prchReqNum);
            shpgPlnInfo.put(PRCH_REQ_LINE_NUM, prchReqLineNum);
            shpgPlnInfo.put(PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);
            shpgPlnInfo.put(PRCH_REQ_QTY, prchReqQty);
            shpgPlnInfo.put(TRX_REF_NUM, trxRefNum);
            shpgPlnInfo.put(TRX_REF_LINE_NUM, trxRefLineNum);
            shpgPlnInfo.put(TRX_REF_LINE_SUB_NUM, trxRefLineSubNum);
            shpgPlnInfo.put(PRCH_REQ_LINE_CMNT_TXT, prchReqLineCmntTxt);
            shpgPlnInfo.put(PO_ORD_NUM, poOrdNum);
            shpgPlnInfo.put(PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
            shpgPlnInfo.put(WRK_ORD_NUM, wrkOrdNum);
            shpgPlnInfo.put(WRK_ORD_DTL_LINE_NUM, wrkOrdDtlLineNum);
            shpgPlnInfoMapList.add(shpgPlnInfo);
        }

        // Call Machine Master Update API
        String hdrNum = (String) invOrdPmsg.invtyOrdNum.getValue();
        String lineNum = (String) invOrdPmsg.invtyOrdLineNum.getValue();
        String lineSubNum = START_LINE_NUM;
        NSZC001001PMsg macMstUpdApiPmsg = callMacMstUpdApi(macMstList, hdrNum, lineNum, lineSubNum);
        msgIdList = S21ApiUtil.getXxMsgIdList(macMstUpdApiPmsg);
        errMsgTxt = null;
        for (String msgId : msgIdList) {
            if (msgId.endsWith("E")) {
                errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NSZC0010, S21MessageFunc.clspGetMessage(msgId) });
                break;
            }
        }
        if (errMsgTxt != null) {
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            return false;
        }
        // Call PR Update API
        // START 2023/06/13 S.Dong [QC#55629, MOD]
//        NPZC103001PMsg prUpdPmsg = callPrUpdApi(rs, hdrNum, lineNum);
        NPZC103001PMsg prUpdPmsg = callPrUpdApi(rs, hdrNum, lineNum, null);
        // END 2023/06/13 S.Dong [QC#55629, MOD]
        if (S21ApiUtil.isXxMsgId(prUpdPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(prUpdPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                    break;
                }
            }
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            return false;
        }

        return true;
    }


    /**
     * Create Inventory Order Process for Config Line
     */
    private boolean createInvOrdProcForConfigLine(ResultSet rs) throws SQLException {
        String errMsgTxt = "";
        List<String> msgIdList;
        // Call PR Update API
        // START 2023/06/13 S.Dong [QC#55629, MOD]
//        NPZC103001PMsg prUpdPmsg = callPrUpdApi(rs, null, null);
        NPZC103001PMsg prUpdPmsg = callPrUpdApi(rs, null, null, null);
        // END 2023/06/13 S.Dong [QC#55629, MOD]
        if (S21ApiUtil.isXxMsgId(prUpdPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(prUpdPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                    break;
                }
            }
            String prchReqNum = rs.getString(PRCH_REQ_NUM);
            String prchReqLineNum = rs.getString(PRCH_REQ_LINE_NUM);
            BigDecimal prchReqLineSubNum = rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM);

            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            errorCount++;
            return false;
        }

        return true;
    }

    /**
     * Create SO
     */
    private boolean createSo(String sceOrdTpCd, List<Map<String, Object>> shpgPlnInfoMapList) throws SQLException {
        String errMsgTxt = "";
        List<String> msgIdList;

        // Call SO API
        List<NLZC205001PMsg> soApiPmsgList = callSoApi(sceOrdTpCd, shpgPlnInfoMapList);
        errMsgTxt = null;
        for (NLZC205001PMsg soApiPmsg : soApiPmsgList) {
            msgIdList = S21ApiUtil.getXxMsgIdList(soApiPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NLZC2050, S21MessageFunc.clspGetMessage(msgId) });
                    break;
                }
            }
            if (errMsgTxt != null) {
                break;
            }
        }
        if (errMsgTxt != null) {
            for (Map<String, Object> shpgPlnInfo : shpgPlnInfoMapList) {
                String prchReqNum = (String) shpgPlnInfo.get(PRCH_REQ_NUM);
                String prchReqLineNum = (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM);
                BigDecimal prchReqLineSubNum = (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM);
                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            }
            return false;
        }

        String soNum = ((NLZC205001PMsg) soApiPmsgList.get(0)).soNum.getValue();

        // Call PR Update API
        NPZC103001PMsg prUpdPmsg = callPrUpdApiForSO(shpgPlnInfoMapList, soNum);
        if (S21ApiUtil.isXxMsgId(prUpdPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(prUpdPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                    break;
                }
            }

            if (errMsgTxt != null) {
                for (Map<String, Object> shpgPlnInfo : shpgPlnInfoMapList) {
                    String prchReqNum = (String) shpgPlnInfo.get(PRCH_REQ_NUM);
                    String prchReqLineNum = (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM);
                    BigDecimal prchReqLineSubNum = (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM);
                    errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                }
                return false;
            }
        }

        // QC#18365(L3) ADD START
        // When drop vendor return item data.
        // Call SO Conf API.
        List<NLZC210001PMsg> pMsgList = new ArrayList<NLZC210001PMsg>();
        for (Map<String, Object> soConfInfo : shpgPlnInfoMapList) {

            String trxSrcTpCd = (String) soConfInfo.get(TRX_SRC_TP_CD);
            String dropRtrnVndCd = (String) soConfInfo.get(DROP_RTRN_VND_CD);
            if (TRX_SRC_TP.VENDOR_RETURN.equals(trxSrcTpCd) && ZYPCommonFunc.hasValue(dropRtrnVndCd)) {

                // When RTL_WH_CATG_CD is not Drop Vendor then skip.
                RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
                ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, (String) soConfInfo.get(SRC_RTL_WH_CD));
                rtlWhTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(rtlWhTMsg);
                if (rtlWhTMsg != null && ZYPCommonFunc.hasValue(rtlWhTMsg.rtlWhCatgCd) && !RTL_WH_CATG.DROP_RMA.equals(rtlWhTMsg.rtlWhCatgCd.getValue())) {
                    continue;
                }

                // Get Shipping Order info By PR_NUM
                HashMap<String, Object> param = new HashMap<String, Object>();
                param.put(GLBL_CMPY_CD, globalCompanyCode);
                param.put(PRCH_REQ_NUM, (String) soConfInfo.get(PRCH_REQ_NUM));
                param.put(PRCH_REQ_LINE_NUM, (String) soConfInfo.get(PRCH_REQ_LINE_NUM));
                param.put(PRCH_REQ_LINE_SUB_NUM, (BigDecimal) soConfInfo.get(PRCH_REQ_LINE_SUB_NUM));
                Map<String, Object> soInfo = (Map<String, Object>) glSsmBatchClient.queryObject("getSoInfoByPrNum", param);

                // SO Conf API param set
                NLZC210001PMsg pMsg = new NLZC210001PMsg();
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pMsg.whCd, (String) soConfInfo.get(SRC_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.soNum, (String) soInfo.get(SO_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.sceOrdTpCd, (String) soConfInfo.get(SCE_ORD_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd, SO_PROC_STS.SHIP);
                ZYPEZDItemValueSetter.setValue(pMsg.shipDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
                ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, (String) soInfo.get(SO_SLP_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.soProcStsCd_DT, SO_PROC_STS.SHIP);
                ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) soConfInfo.get(MDSE_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.fromStkStsCd, (String) soConfInfo.get(FROM_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipQty, (BigDecimal) soConfInfo.get(PRCH_REQ_QTY));
                ZYPEZDItemValueSetter.setValue(pMsg.proNum, (String) soConfInfo.get(PRO_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.totFrtAmt, BigDecimal.ZERO);
                pMsgList.add(pMsg);
            }
        }

        if (0 < pMsgList.size()) {
            // SO Conf API execute
            NLZC210001 api = new NLZC210001();
            api.execute(pMsgList, null, ONBATCH_TYPE.BATCH);
            for (NLZC210001PMsg dcSoConfApiPmsg : pMsgList) {
                msgIdList = S21ApiUtil.getXxMsgIdList(dcSoConfApiPmsg);
                for (String msgId : msgIdList) {
                    if (msgId.endsWith("E")) {
                        errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NLZC2100, S21MessageFunc.clspGetMessage(msgId) });
                        break;
                    }
                }
                if (errMsgTxt != null) {
                    break;
                }
            }
            if (errMsgTxt != null) {
                for (Map<String, Object> shpgPlnInfo : shpgPlnInfoMapList) {
                    String prchReqNum = (String) shpgPlnInfo.get(PRCH_REQ_NUM);
                    String prchReqLineNum = (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM);
                    BigDecimal prchReqLineSubNum = (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM);
                    errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                }
                return false;
            }

            // Close PR
            NPZC103001PMsg pmsg = new NPZC103001PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
            ZYPEZDItemValueSetter.setValue(pmsg.eventId, NPZC103001Constant.EVENT_SHIPPED);
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqStsCd, PRCH_REQ_STS.CLOSED);
            for (int i = 0; i < shpgPlnInfoMapList.size(); i++) {
                Map<String, Object> shpgPlnInfo = shpgPlnInfoMapList.get(i);
                ZYPEZDItemValueSetter.setValue(pmsg.prchReqNum, (String) shpgPlnInfo.get(PRCH_REQ_NUM));
                ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineNum, (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineSubNum, (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM));
                ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).soNum, soNum);
                ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineStsCd, PRCH_REQ_LINE_STS.CLOSED);
                ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
                // START 2018/10/31 M.Naito [QC#29009,ADD]
                ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).shipQty, (BigDecimal) shpgPlnInfo.get(PRCH_REQ_QTY));
                ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).shipDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
                // END 2018/10/31 M.Naito [QC#29009,ADD]
                pmsg.prchReqInfo.setValidCount(i + 1);
            }
            new NPZC103001().execute(pmsg, ONBATCH_TYPE.BATCH);
            if (S21ApiUtil.isXxMsgId(pmsg)) {
                msgIdList = S21ApiUtil.getXxMsgIdList(pmsg);
                for (String msgId : msgIdList) {
                    if (msgId.endsWith("E")) {
                        errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                        break;
                    }
                }
                if (errMsgTxt != null) {
                    for (Map<String, Object> shpgPlnInfo : shpgPlnInfoMapList) {
                        String prchReqNum = (String) shpgPlnInfo.get(PRCH_REQ_NUM);
                        String prchReqLineNum = (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM);
                        BigDecimal prchReqLineSubNum = (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM);
                        errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                    }
                    return false;
                }
            }
        }
        // QC#18365(L3) ADD END

        if (errMsgTxt != null) {
            return false;
        }

        return true;
    }

    /**
     * Create Vendor Return Process
     */
    private boolean createVndRtnProc(ResultSet rs, String wkLineNum, List<Map<String, Object>> shpgPlnInfoMapList) throws SQLException {
        String errMsgTxt = "";
        List<String> msgIdList;

        // Get Vendor Return Type
        String prchReqNum = rs.getString(PRCH_REQ_NUM);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(PRCH_REQ_NUM, prchReqNum);
        String vndRtrnTpCd = (String) glSsmBatchClient.queryObject("getVndRtnTp", param);

        // Get Serial Number
        String prchReqLineNum = rs.getString(PRCH_REQ_LINE_NUM);
        BigDecimal prchReqLineSubNum = rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM);
        List<String> serNumList = getSerNumList(prchReqNum, prchReqLineNum, prchReqLineSubNum);

        BigDecimal prchReqQty = rs.getBigDecimal(PRCH_REQ_QTY);
        String trxRefNum = rs.getString(TRX_REF_NUM);
        String trxRefLineNum = rs.getString(TRX_REF_LINE_NUM);
        String trxRefLineSubNum = rs.getString(TRX_REF_LINE_SUB_NUM);
        String poOrdNum = rs.getString(PO_ORD_NUM);
        String poOrdDtlLineNum = rs.getString(PO_ORD_DTL_LINE_NUM);
        String wrkOrdNum = rs.getString(WRK_ORD_NUM);
        String wrkOrdDtlLineNum = rs.getString(WRK_ORD_DTL_LINE_NUM);

        // Get Service Machine Master PK
        List<Map<String, Object>> macMstList = getMacMstList(prchReqNum, prchReqLineNum, prchReqLineSubNum, serNumList);

        // Insert Vendor Return Header
        if (START_LINE_NUM.equals(wkLineNum)) {
            VND_RTRNTMsg vndRtnTmsg = new VND_RTRNTMsg();
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.vndRtrnNum, prchReqNum);
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.exptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.vndRtrnTpCd, vndRtrnTpCd);
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.vndRtrnSubmtDt, salesDate);
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.trxSrcTpCd, rs.getString(TRX_SRC_TP_CD));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.invtyLocCd, rs.getString(SRC_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.psIssRqstFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.prtlShipAllwFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.billToVndCd, rs.getString(VND_CD));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.debitToVndCd, rs.getString(VND_CD));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToVndCd, rs.getString(VND_CD));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToLocNm, rs.getString(SHIP_TO_LOC_NM));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToAddlLocNm, rs.getString(SHIP_TO_ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToFirstLineAddr, rs.getString(SHIP_TO_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToScdLineAddr, rs.getString(SHIP_TO_SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToThirdLineAddr, rs.getString(SHIP_TO_THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToFrthLineAddr, rs.getString(SHIP_TO_FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToCtyAddr, rs.getString(SHIP_TO_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToStCd, rs.getString(SHIP_TO_ST_CD));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToProvNm, rs.getString(SHIP_TO_PROV_NM));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToCntyNm, rs.getString(SHIP_TO_CNTY_NM));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToPostCd, rs.getString(SHIP_TO_POST_CD));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToCtryCd, rs.getString(SHIP_TO_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToFirstRefCmntTxt, rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.shipToScdRefCmntTxt, rs.getString(SHIP_TO_SCD_REF_CMNT_TXT));

            GLBL_CMPYTMsg glblCmpyTmsg = new GLBL_CMPYTMsg();
            ZYPEZDItemValueSetter.setValue(glblCmpyTmsg.glblCmpyCd, globalCompanyCode);
            glblCmpyTmsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTmsg);
            glStdCcyCd = null;
            if (glblCmpyTmsg != null) {
                glStdCcyCd = (String) glblCmpyTmsg.stdCcyCd.getValue();
                ZYPEZDItemValueSetter.setValue(vndRtnTmsg.dealCcyCd, glStdCcyCd);
            }

            // Get Summary ENT_PO_DTL_DEAL_NET_AMT
            param = new HashMap<String, Object>();
            param.put(GLBL_CMPY_CD, globalCompanyCode);
            param.put(PRCH_REQ_NUM, prchReqNum);
            param.put(IN_COMPLETED, PRCH_REQ_REL_STS.IN_COMPLETED);
            param.put(ERROR, PRCH_REQ_REL_STS.ERROR);
            BigDecimal totVndRtrnAmt = (BigDecimal) glSsmBatchClient.queryObject("getSumEntPoDtlDealNetAmt", param);
            if (hasValue(totVndRtrnAmt)) {
                ZYPEZDItemValueSetter.setValue(vndRtnTmsg.totVndRtrnAmt, totVndRtrnAmt);
            }

            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.vndRtrnHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(vndRtnTmsg.dropShipFlg, ZYPConstant.FLG_OFF_N);

            EZDTBLAccessor.insert(vndRtnTmsg);
            // QC#17395 Start.
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString("HAZ_MAT_FLG")) && EZDTBLAccessor.RTNCD_DUPLICATE.equals(vndRtnTmsg.getReturnCode())) {
                // no processing.
                EZDDebugOutput.println(1, "NPBB001001" + ">>>>> vndRtrnNum duplicate:" + prchReqNum, this);
            } else if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndRtnTmsg.getReturnCode())) {
                throw new SQLException();
            }
            // QC#17395 End.
        } // End Insert Vendor Return Header

        // Insert Vendor Return Detail
        VND_RTRN_DTLTMsg vndRtnDtlTmsg = new VND_RTRN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.vndRtrnNum, prchReqNum);
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.trxLineNum, prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.trxLineSubNum, prchReqLineSubNum.toString());
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.vndRtrnDtlSubmtDt, salesDate);
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.mdseNm, rs.getString(MDSE_NM));
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.avalQty, new BigDecimal(0));
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.rtrnQty, rs.getBigDecimal(PRCH_REQ_QTY));
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.stkStsCd, rs.getString(FROM_STK_STS_CD));
        if (glStdCcyCd != null) {
            ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.dealCcyCd, glStdCcyCd);
            // QC#21314
            ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.funcCcyCd, glStdCcyCd);
        }
        // START 2018/02/02  [QC#21703,MOD]
        // START 2018/05/25 S.Katsuma [QC#25893,MOD]
         ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.dealVndRtrnUnitPrcAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
//        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.dealVndRtrnUnitPrcAmt, rs.getBigDecimal(THIS_MTH_TOT_STD_COST_AMT));
        // END 2018/05/25 S.Katsuma [QC#25893,MOD]
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.uomCd, rs.getString(PKG_UOM_CD));
        // END   2018/02/02  [QC#21703,MOD]
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.origVndRtrnUnitPrcAmt, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.coaAcctCd, rs.getString(COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(vndRtnDtlTmsg.vndRtrnDtlHldFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.insert(vndRtnDtlTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndRtnDtlTmsg.getReturnCode())) {
            throw new SQLException();
        }

        // Insert Vendor Return Comment
        if (START_LINE_NUM.equals(wkLineNum)) {
            // PRCH_REQ_CMNT_TXT
            String prchReqCmntTxt = rs.getString(PRCH_REQ_CMNT_TXT);
            insertVndRtrnCmnt(prchReqNum, prchReqLineNum, prchReqLineSubNum, SO_COMMENT, prchReqCmntTxt);
        }
        // PRCH_REQ_LINE_CMNT_TXT
        String prchReqLineCmntTxt = rs.getString(PRCH_REQ_LINE_CMNT_TXT);
        insertVndRtrnCmnt(prchReqNum, prchReqLineNum, prchReqLineSubNum, LINE_COMMENT, prchReqLineCmntTxt);

        // Get TRX_CD and TRX_RSN_CD by SCE Order Type before Call
        // Allocation for non CPO API
        Map<String, Object> sceOrdTp = getSceOrdTp(rs.getString(SCE_ORD_TP_CD), INBD_OTBD.OUTBOUND);
        String trxCd = "";
        String trxRsnCd = "";
        if (sceOrdTp != null) {
            trxCd = (String) sceOrdTp.get(TRX_CD);
            trxRsnCd = (String) sceOrdTp.get(TRX_RSN_CD);
        }

        // Call Allocation for non CPO API
        NWZC107001PMsg alcPmsg = callAlcApi(rs, null, trxCd, trxRsnCd);
        if (S21ApiUtil.isXxMsgId(alcPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(alcPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NWZC1070, S21MessageFunc.clspGetMessage(msgId) });
                    break;
                }
            }
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            errorCount++;
            return false;
        }

        // Call Shipping Plan Update API
        NWZC003001PMsg shpgPlnPmsg = callShpgPlnUpdApi(rs, null, alcPmsg);
        if (S21ApiUtil.isXxMsgId(shpgPlnPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(shpgPlnPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NWZC0030, S21MessageFunc.clspGetMessage(msgId) });
                    break;
                }
            }
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            errorCount++;
            return false;
        }

        String trxSrcTpCd = rs.getString(TRX_SRC_TP_CD);
        List<Map<String, Object>> shpgPlnListWrk = getShpgPlnList(prchReqNum, prchReqLineNum, prchReqLineSubNum.toString(), trxSrcTpCd);
        for (Map<String, Object> shpgPlnInfo : shpgPlnListWrk) {
            shpgPlnInfo.put(PRCH_REQ_NUM, prchReqNum);
            shpgPlnInfo.put(PRCH_REQ_LINE_NUM, prchReqLineNum);
            shpgPlnInfo.put(PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);
            shpgPlnInfo.put(PRCH_REQ_QTY, prchReqQty);
            shpgPlnInfo.put(TRX_REF_NUM, trxRefNum);
            shpgPlnInfo.put(TRX_REF_LINE_NUM, trxRefLineNum);
            shpgPlnInfo.put(TRX_REF_LINE_SUB_NUM, trxRefLineSubNum);
            shpgPlnInfo.put(PRCH_REQ_LINE_CMNT_TXT, prchReqLineCmntTxt);
            shpgPlnInfo.remove(TRX_HDR_NUM);
            shpgPlnInfo.remove(TRX_LINE_NUM);
            shpgPlnInfo.put(PO_ORD_NUM, poOrdNum);
            shpgPlnInfo.put(PO_ORD_DTL_LINE_NUM, poOrdDtlLineNum);
            shpgPlnInfo.put(WRK_ORD_NUM, wrkOrdNum);
            shpgPlnInfo.put(WRK_ORD_DTL_LINE_NUM, wrkOrdDtlLineNum);
            // QC#18365(L3) ADD START
            shpgPlnInfo.put(TRX_SRC_TP_CD, rs.getString(TRX_SRC_TP_CD));
            shpgPlnInfo.put(DROP_RTRN_VND_CD, rs.getString(DROP_RTRN_VND_CD));
            shpgPlnInfo.put(SRC_INVTY_LOC_CD, rs.getString(SRC_INVTY_LOC_CD));
            shpgPlnInfo.put(SRC_RTL_WH_CD, rs.getString(SRC_RTL_WH_CD));
            shpgPlnInfo.put(SCE_ORD_TP_CD, rs.getString(SCE_ORD_TP_CD));
            shpgPlnInfo.put(MDSE_CD, rs.getString(MDSE_CD));
            shpgPlnInfo.put(FROM_STK_STS_CD, rs.getString(FROM_STK_STS_CD));
            shpgPlnInfo.put(PRCH_REQ_QTY, rs.getBigDecimal(PRCH_REQ_QTY));
            shpgPlnInfo.put(PRO_NUM, rs.getString(PRO_NUM));
            // QC#18365(L3) ADD END
            shpgPlnInfoMapList.add(shpgPlnInfo);
        }

        String hdrNum = rs.getString(PRCH_REQ_NUM);
        String lineNum = rs.getString(PRCH_REQ_LINE_NUM);
        String lineSubNum = rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM).toString();

        // Call Machine Master Update API
        NSZC001001PMsg macMstUpdApiPmsg = callMacMstUpdApi(macMstList, hdrNum, lineNum, lineSubNum);
        msgIdList = S21ApiUtil.getXxMsgIdList(macMstUpdApiPmsg);
        errMsgTxt = null;
        for (String msgId : msgIdList) {
            if (msgId.endsWith("E")) {
                errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NSZC0010, S21MessageFunc.clspGetMessage(msgId) });
                break;
            }
        }
        if (errMsgTxt != null) {
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            errorCount++;
            return false;
        }
        // Call PR Update API
        // START 2023/06/13 S.Dong [QC#55629, MOD]
//        NPZC103001PMsg prUpdPmsg = callPrUpdApi(rs, null, null);
        NPZC103001PMsg prUpdPmsg = callPrUpdApi(rs, null, null, null);
        // END 2023/06/13 S.Dong [QC#55629, ADD]
        if (S21ApiUtil.isXxMsgId(prUpdPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(prUpdPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                    break;
                }
            }
            errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
            errorCount++;
            return false;
        }

        return true;
    }

    /**
     * insertVndRtrnCmnt
     * @param trxHdrNum String
     * @param trxLineNum String
     * @param trxLineSubNum trxLineSubNum
     * @param cmntTpCd String
     * @param cmntTxt String
     * @throws SQLException
     */
    private void insertVndRtrnCmnt(String trxHdrNum, String trxLineNum, BigDecimal trxLineSubNum, String cmntTpCd, String cmntTxt) throws SQLException {
        if (!ZYPCommonFunc.hasValue(cmntTxt)) {
            return;
        }

        // Insert record of the comment in maximum 60 char length each.
        int idx = 1;
        int len = 0;
        for (int pos = 0; pos < cmntTxt.length(); pos += len, ++idx) {
            len = cmntTxt.length() - pos;
            if (len > MAX_CMNT_LENGTH) {
                len = MAX_CMNT_LENGTH;
            }

            // Insert Vendor Return Comment
            VND_RTRN_CMNTTMsg tmsg = new VND_RTRN_CMNTTMsg();
            // under 60 byte
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(tmsg.trxHdrNum, trxHdrNum);
            ZYPEZDItemValueSetter.setValue(tmsg.trxLineNum, trxLineNum);
            ZYPEZDItemValueSetter.setValue(tmsg.trxLineSubNum, trxLineSubNum.toPlainString());
            ZYPEZDItemValueSetter.setValue(tmsg.vndRtrnCmntTpCd, cmntTpCd);
            ZYPEZDItemValueSetter.setValue(tmsg.vndRtrnCmntSqNum, String.valueOf(idx));
            ZYPEZDItemValueSetter.setValue(tmsg.vndRtrnCmntTxt, cmntTxt.substring(pos, pos + len));
            EZDTBLAccessor.insert(tmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                throw new SQLException();
            }
        }
    }

    /**
     * Missing Parts Request Process
     */
    private void execMissingPartsReqProc() {

        String techRtrnTpCd = ZYPCodeDataUtil.getVarCharConstValue(TECH_RTRN_TP_CD, globalCompanyCode);
        if (!hasValue(techRtrnTpCd)) {
            throw new S21AbendException(NPAM1173E, new String[] {TECH_RTRN_TP_CD });
        }
        String missPrtLineTpCd = ZYPCodeDataUtil.getVarCharConstValue(MISS_PRT_LINE_TP_CD, globalCompanyCode);
        if (!hasValue(missPrtLineTpCd)) {
            throw new S21AbendException(NPAM1173E, new String[] {MISS_PRT_LINE_TP_CD });
        }
        String missPrtOrigLineStsCd = ZYPCodeDataUtil.getVarCharConstValue(MISS_PRT_ORIG_LINE_STS_CD, globalCompanyCode);
        if (!hasValue(missPrtOrigLineStsCd)) {
            throw new S21AbendException(NPAM1173E, new String[] {MISS_PRT_ORIG_LINE_STS_CD });
        }
        String defShpgSvcLvlCd = ZYPCodeDataUtil.getVarCharConstValue(INVTY_REQ_DEF_SHPG_SVC_LVL_CD, globalCompanyCode);
        if (!hasValue(defShpgSvcLvlCd)) {
            throw new S21AbendException(NPAM1173E, new String[] {INVTY_REQ_DEF_SHPG_SVC_LVL_CD });
        }

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // Get Missing Parts Request Data
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(PRCH_REQ_REC_TP_CD, techRtrnTpCd);
            paramMap.put(PRCH_REQ_LINE_TP_CD, missPrtLineTpCd);
            paramMap.put(IN_COMPLETED, PRCH_REQ_REL_STS.IN_COMPLETED);
            paramMap.put(ERROR, PRCH_REQ_REL_STS.ERROR);
            paramMap.put(PRCH_REQ_LINE_STS_CD, missPrtOrigLineStsCd);
            paramMap.put(INVTY_REQ_DEF_SHPG_SVC_LVL_CD, defShpgSvcLvlCd);
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("getMissingPartsRequest", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            String oldPrchReqNum = "";
            String oldDestRtlWhCd = "";
            String oldSceOrdTpCd = "";
            String errMsgTxt = "";
            List<String> msgIdList;
            int iLineNum = 0;
            List<Map<String, Object>> shpgPlnInfoList = new ArrayList<Map<String, Object>>();
            boolean errFlg = false;
            int rowNum = 0;

            while (rs.next()) {
                totalCount++;
                rowNum++;

                // Get Serial Number
                String prchReqNum = rs.getString(PRCH_REQ_NUM);
                String prchReqLineNum = rs.getString(PRCH_REQ_LINE_NUM);
                BigDecimal prchReqLineSubNum = rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM);
                List<String> serNumList = getSerNumList(prchReqNum, prchReqLineNum, prchReqLineSubNum);
                String destRtlWhCd = setEmptyStringForNull(rs.getString(DEST_RTL_WH_CD));
                String sceOrdTpCd = rs.getString(SCE_ORD_TP_CD);

                if (oldPrchReqNum.equals(prchReqNum) && oldDestRtlWhCd.equals(destRtlWhCd)) {
                    if (errFlg) {
                        errorCount++;
                        continue;
                    }

                    iLineNum++;

                } else {
                    if (rowNum == 1) {
                        // do nothing
                    } else if (errFlg) {
                        rollback();
                        shpgPlnInfoList.clear();
                    } else {
                        if (createSoAndSoConfForMissingParts(oldSceOrdTpCd, shpgPlnInfoList)) {
                            commit();
                        } else {
                            rollback();
                        }
                        shpgPlnInfoList.clear();
                    }

                    errFlg = false;
                    iLineNum = 1;
                    oldPrchReqNum = prchReqNum;
                    oldDestRtlWhCd = destRtlWhCd;
                    oldSceOrdTpCd = sceOrdTpCd;
                }

                String wkLineNum = String.format("%03d", iLineNum);

                // Get TRX_CD and TRX_RSN_CD by SCE Order Type before
                // Call Inventory Order API
                Map<String, Object> sceOrdTp = getSceOrdTp(rs.getString(SCE_ORD_TP_CD), INBD_OTBD.OUTBOUND);
                String trxCd = "";
                String trxRsnCd = "";
                if (sceOrdTp != null) {
                    trxCd = (String) sceOrdTp.get(TRX_CD);
                    trxRsnCd = (String) sceOrdTp.get(TRX_RSN_CD);
                }

                // Call Inventory Order API
                NLZC003001PMsg invOrdPmsg = callInvOrdApi(rs, serNumList, trxCd, trxRsnCd, wkLineNum);
                if (S21ApiUtil.isXxMsgId(invOrdPmsg)) {
                    msgIdList = S21ApiUtil.getXxMsgIdList(invOrdPmsg);
                    for (String msgId : msgIdList) {
                        if (msgId.endsWith("E")) {
                            errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NLZC0030, S21MessageFunc.clspGetMessage(msgId) });
                            break;
                        }
                    }
                    errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                    errorCount++;
                    errFlg = true;
                    continue;
                }

                // Call Allocation for non CPO API
                NWZC107001PMsg alcPmsg = callAlcApi(rs, invOrdPmsg, trxCd, trxRsnCd);
                if (S21ApiUtil.isXxMsgId(alcPmsg)) {
                    msgIdList = S21ApiUtil.getXxMsgIdList(alcPmsg);
                    for (String msgId : msgIdList) {
                        if (msgId.endsWith("E")) {
                            errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NWZC1070, S21MessageFunc.clspGetMessage(msgId) });
                            break;
                        }
                    }
                    errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                    errorCount++;
                    errFlg = true;
                    continue;
                }

                // Call Shipping Plan Update API
                NWZC003001PMsg shpgPlnPmsg = callShpgPlnUpdApi(rs, invOrdPmsg, alcPmsg);
                if (S21ApiUtil.isXxMsgId(shpgPlnPmsg)) {
                    msgIdList = S21ApiUtil.getXxMsgIdList(shpgPlnPmsg);
                    for (String msgId : msgIdList) {
                        if (msgId.endsWith("E")) {
                            errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NWZC0030, S21MessageFunc.clspGetMessage(msgId) });
                            break;
                        }
                    }
                    errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                    errorCount++;
                    errFlg = true;
                    continue;
                }

                String trxSrcTpCd = rs.getString(TRX_SRC_TP_CD);
                List<Map<String, Object>> shpgPlnListWrk = getShpgPlnList(glInvtyOrdNum, wkLineNum, START_LINE_NUM, trxSrcTpCd);
                for (Map<String, Object> shpgPlnInfo : shpgPlnListWrk) {
                    shpgPlnInfo.put(PRCH_REQ_NUM, prchReqNum);
                    shpgPlnInfo.put(PRCH_REQ_LINE_NUM, prchReqLineNum);
                    shpgPlnInfo.put(PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);
                    shpgPlnInfo.put(PRCH_REQ_QTY, rs.getBigDecimal(PRCH_REQ_QTY));
                    shpgPlnInfo.put(PO_ORD_NUM, rs.getString(PO_ORD_NUM));
                    shpgPlnInfo.put(PO_ORD_DTL_LINE_NUM, rs.getString(PO_ORD_DTL_LINE_NUM));
                    shpgPlnInfo.put(INVTY_ORD_NUM, invOrdPmsg.invtyOrdNum.getValue());
                    shpgPlnInfo.put(INVTY_ORD_LINE_NUM, invOrdPmsg.invtyOrdLineNum.getValue());
                    shpgPlnInfo.put(TRX_REF_NUM, rs.getString(TRX_REF_NUM));
                    shpgPlnInfo.put(TRX_REF_LINE_NUM, rs.getString(TRX_REF_LINE_NUM));
                    shpgPlnInfo.put(TRX_REF_LINE_SUB_NUM, rs.getString(TRX_REF_LINE_SUB_NUM));
                    shpgPlnInfo.put(PRCH_REQ_LINE_CMNT_TXT, rs.getString(PRCH_REQ_LINE_CMNT_TXT));
                    shpgPlnInfo.put(PRO_NUM, rs.getString(PRO_NUM));
                    shpgPlnInfoList.add(shpgPlnInfo);
                }

                // Call Machine Master Update API
                String hdrNum = (String) invOrdPmsg.invtyOrdNum.getValue();
                String lineNum = (String) invOrdPmsg.invtyOrdLineNum.getValue();
                String lineSubNum = START_LINE_NUM;

                List<Map<String, Object>> macMstList = getMacMstList(prchReqNum, prchReqLineNum, prchReqLineSubNum, serNumList);

                NSZC001001PMsg macMstUpdApiPmsg = callMacMstUpdApi(macMstList, hdrNum, lineNum, lineSubNum);
                msgIdList = S21ApiUtil.getXxMsgIdList(macMstUpdApiPmsg);
                errMsgTxt = null;
                for (String msgId : msgIdList) {
                    if (msgId.endsWith("E")) {
                        errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NSZC0010, S21MessageFunc.clspGetMessage(msgId) });
                        break;
                    }
                }
                if (errMsgTxt != null) {
                    errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                    errFlg = true;
                    errorCount++;
                    continue;
                }

            } // End Loop

            if (rowNum == 0) {
                // no process
            } else if (errFlg) {
                rollback();
            } else {
                if (createSoAndSoConfForMissingParts(oldSceOrdTpCd, shpgPlnInfoList)) {
                    commit();
                } else {
                    rollback();
                }
            }

        } catch (SQLException e) {
            rollback();
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
    }

    /**
     * Create SO and SO Conf for Missing Parts
     */
    private boolean createSoAndSoConfForMissingParts(String sceOrdTpCd, List<Map<String, Object>> shpgPlnInfoList) throws SQLException {
        String errMsgTxt = "";
        List<String> msgIdList;

        // Call SO API
        List<NLZC205001PMsg> soApiPmsgList = callSoApi(sceOrdTpCd, shpgPlnInfoList);
        errMsgTxt = null;
        for (NLZC205001PMsg soApiPmsg : soApiPmsgList) {
            msgIdList = S21ApiUtil.getXxMsgIdList(soApiPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NLZC2050, S21MessageFunc.clspGetMessage(msgId) });
                    break;
                }
            }
            if (errMsgTxt != null) {
                break;
            }
        }
        if (errMsgTxt != null) {
            for (Map<String, Object> shpgPlnInfo : shpgPlnInfoList) {
                String prchReqNum = (String) shpgPlnInfo.get(PRCH_REQ_NUM);
                String prchReqLineNum = (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM);
                BigDecimal prchReqLineSubNum = (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM);
                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                errorCount++;
            }
            return false;
        }

        String soNum = ((NLZC205001PMsg) soApiPmsgList.get(0)).soNum.getValue();

        // Call S21 DC SO Confirmation API
        List<NLZC210001PMsg> dcSoConfApiPmsgList = callDcSoConfApi(soNum, sceOrdTpCd);
        errMsgTxt = null;
        for (NLZC210001PMsg dcSoConfApiPmsg : dcSoConfApiPmsgList) {
            msgIdList = S21ApiUtil.getXxMsgIdList(dcSoConfApiPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(NPAM1323E, new String[] {NLZC2100, S21MessageFunc.clspGetMessage(msgId) });
                    break;
                }
            }
            if (errMsgTxt != null) {
                break;
            }
        }
        if (errMsgTxt != null) {
            for (Map<String, Object> shpgPlnInfo : shpgPlnInfoList) {
                String prchReqNum = (String) shpgPlnInfo.get(PRCH_REQ_NUM);
                String prchReqLineNum = (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM);
                BigDecimal prchReqLineSubNum = (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM);
                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                errorCount++;
            }
            return false;
        }

//        // Call PR Update API For SO Conf
//        NPZC103001PMsg prUpdPmsg = callPrUpdApiForSoConf(shpgPlnInfoList, soNum);
//        if (S21ApiUtil.isXxMsgId(prUpdPmsg)) {
//            msgIdList = S21ApiUtil.getXxMsgIdList(prUpdPmsg);
//            for (String msgId : msgIdList) {
//                if (msgId.endsWith("E")) {
//                    errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
//                    break;
//                }
//            }
//            for (Map<String, Object> shpgPlnInfo : shpgPlnInfoList) {
//                String prchReqNum = (String) shpgPlnInfo.get(PRCH_REQ_NUM);
//                String prchReqLineNum = (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM);
//                BigDecimal prchReqLineSubNum = (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM);
//                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
//                errorCount++;
//            }
//            return false;
//        }

        // Call PR Update API
        NPZC103001PMsg prUpdPmsg = callPrUpdApiForMissingParts(shpgPlnInfoList, soNum);
        if (S21ApiUtil.isXxMsgId(prUpdPmsg)) {
            msgIdList = S21ApiUtil.getXxMsgIdList(prUpdPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                    break;
                }
            }
            for (Map<String, Object> shpgPlnInfo : shpgPlnInfoList) {
                String prchReqNum = (String) shpgPlnInfo.get(PRCH_REQ_NUM);
                String prchReqLineNum = (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM);
                BigDecimal prchReqLineSubNum = (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM);
                errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                errorCount++;
            }
            return false;
        }

        // add start 2022/03/14 QC#59780
        updateShipOrdProNumList(shpgPlnInfoList, soNum);
        // add end 2022/03/14 QC#59780

        return true;
    }

    /**
     * Call Inventory Order API
     */
    private NLZC003001PMsg callInvOrdApi(ResultSet rs, List<String> serNumList, String trxCd, String trxRsnCd, String wkLineNum) throws SQLException {
        NLZC003001PMsg pmsg = new NLZC003001PMsg();

        if (START_LINE_NUM.equals(wkLineNum)) {
            // header
            ZYPEZDItemValueSetter.setValue(pmsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
            ZYPEZDItemValueSetter.setValue(pmsg.xxDtTpCd, NLZC003001.DT_TP_HDR);
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdTpCd, rs.getString(INVTY_ORD_TP_CD));
            ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(SRC_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(pmsg.trxCd, trxCd);
            ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, trxRsnCd);
            ZYPEZDItemValueSetter.setValue(pmsg.shpgSvcLvlCd, rs.getString(SHPG_SVC_LVL_CD));
            ZYPEZDItemValueSetter.setValue(pmsg.dcTrnsfRddDt, rs.getString(RQST_RCV_DT));
            // QC#19682 Start.
            ZYPEZDItemValueSetter.setValue(pmsg.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
            // QC#19682 End.

            String invtyOrdTpCd = rs.getString(INVTY_ORD_TP_CD);
            if (hasValue(invtyOrdTpCd) && INVTY_ORD_TP.DISPOSAL.equals(invtyOrdTpCd)) {
                ZYPEZDItemValueSetter.setValue(pmsg.prodCtrlCd, rs.getString(FIRST_PROD_CTRL_CD));
                ZYPEZDItemValueSetter.setValue(pmsg.dsplTpCd, DSPL_TP.OTHER);
            }

            ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdStsCd, INVTY_ORD_STS.APPROVED);

            // QC#19641
            // Split PRCH_REQ_CMNT_TXT into 60bytes each, and set them into 4columns.
            String prchReqCmntTxt = rs.getString(PRCH_REQ_CMNT_TXT);
            if (ZYPCommonFunc.hasValue(prchReqCmntTxt)) {
                int pos = 0;
                int len = MAX_CMNT_LENGTH;
                EZDPStringItem[] items = new EZDPStringItem[] {pmsg.firstInvtyOrdCmntTxt, pmsg.scdInvtyOrdCmntTxt, pmsg.thirdInvtyOrdCmntTxt, pmsg.frthInvtyOrdCmntTxt };
                for (int idx = 0; pos < prchReqCmntTxt.length() && idx < items.length; ++idx) {
                    len = prchReqCmntTxt.length() - pos;
                    if (len > MAX_CMNT_LENGTH) {
                        len = MAX_CMNT_LENGTH;
                    }
                    ZYPEZDItemValueSetter.setValue(items[idx], prchReqCmntTxt.substring(pos, pos + len));
                    pos += len;
                }
            }

            ZYPEZDItemValueSetter.setValue(pmsg.trxSrcTpCd, rs.getString(TRX_SRC_TP_CD));
            ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);

            invOrdApi = new NLZC003001();
            invOrdApi.execute(pmsg, ONBATCH_TYPE.BATCH);

            // API ERROR occurred
            if (S21ApiUtil.isXxMsgId(pmsg)) {
                return pmsg;
            }

            // Set Inventory Order Number
            glInvtyOrdNum = pmsg.invtyOrdNum.getValue();

        } // header end

        // detail
        pmsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.xxProcTpCd, NLZC003001.PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxDtTpCd, NLZC003001.DT_TP_DTL);
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdNum, glInvtyOrdNum);
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdTpCd, rs.getString(INVTY_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(SRC_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdLineNum, wkLineNum);
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(FROM_STK_STS_CD));
        if (PRCH_REQ_TP.SUBCONTRACT.equals(rs.getString(PRCH_REQ_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd_D1, rs.getString(DEST_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, rs.getString(VND_CD));
        } else if (PRCH_REQ_TP.REFURBISHING.equals(rs.getString(PRCH_REQ_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd_D1, rs.getString(VND_CD));
            ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, rs.getString(VND_CD));
        } else if (PRCH_REQ_TP.DISPOSAL.equals(rs.getString(PRCH_REQ_TP_CD))) {
            // QC#12513
            if (ZYPCommonFunc.hasValue(rs.getString(VND_CD))) {
                ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd_D1, rs.getString(VND_CD));
                ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, rs.getString(VND_CD));
            // Mod Start 2020/01/28 QC#54989
            } else if(!S21StringUtil.isEquals(rs.getString(SHIP_TO_CUST_CD), rs.getString(SRC_RTL_WH_CD))) {
                ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd_D1, rs.getString(SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, rs.getString(SHIP_TO_CUST_CD));
            // Mod End 2020/01/28 QC#54989
            } else {
                ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd_D1, rs.getString(SRC_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, rs.getString(SRC_INVTY_LOC_CD));
            }
        } else if (PRCH_REQ_TP.TECH_RETURN.equals(rs.getString(PRCH_REQ_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd_D1, rs.getString(DEST_INVTY_LOC_CD));
        }
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd_D1, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.toStkStsCd, rs.getString(FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.ordQty, rs.getBigDecimal(PRCH_REQ_QTY));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyOrdDtlStsCd, INVTY_ORD_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqNum, rs.getString(PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqLineNum, rs.getString(PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqLineSubNum, rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqRecTpCd, rs.getString(PRCH_REQ_REC_TP_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.trxRefSrcTpCd, rs.getString(SCE_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.trxRefNum, rs.getString(TRX_REF_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.trxRefLineNum, rs.getString(TRX_REF_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.trxRefLineSubNum, rs.getString(TRX_REF_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.coaCmpyCd, rs.getString(COA_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.coaBrCd, rs.getString(COA_BR_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.coaAcctCd, rs.getString(COA_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.coaProdCd, rs.getString(COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.coaChCd, rs.getString(COA_CH_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.coaCcCd, rs.getString(COA_CC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.coaAfflCd, rs.getString(COA_AFFL_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.coaExtnCd, rs.getString(COA_EXTN_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.coaProjCd, rs.getString(COA_PROJ_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));

        for (int i = 0; i < serNumList.size(); i++) {
            ZYPEZDItemValueSetter.setValue(pmsg.serialInfoList.no(i).invtyOrdNum, glInvtyOrdNum);
            ZYPEZDItemValueSetter.setValue(pmsg.serialInfoList.no(i).invtyOrdLineNum, wkLineNum);
            ZYPEZDItemValueSetter.setValue(pmsg.serialInfoList.no(i).serNum, serNumList.get(i));
            pmsg.serialInfoList.setValidCount(i + 1);
        }

        invOrdApi.execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;

    }

    /**
     * Call Allocation for non CPO API
     */
    private NWZC107001PMsg callAlcApi(ResultSet rs, NLZC003001PMsg invOrdPmsg, String trxCd, String trxRsnCd) throws SQLException {
        NWZC107001PMsg pmsg = new NWZC107001PMsg();

        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.xxSysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
        ZYPEZDItemValueSetter.setValue(pmsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pmsg.trxSrcTpCd, rs.getString(TRX_SRC_TP_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.trxCd, trxCd);
        ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, trxRsnCd);
        if (invOrdPmsg == null) { // this case is Vendor Return
            ZYPEZDItemValueSetter.setValue(pmsg.trxHdrNum, rs.getString(PRCH_REQ_NUM)); // same
            // val
            // VND_RTRN_NUM
            ZYPEZDItemValueSetter.setValue(pmsg.trxLineNum, rs.getString(PRCH_REQ_LINE_NUM)); // same
            // val
            // TRX_LINE_NUM
            ZYPEZDItemValueSetter.setValue(pmsg.trxLineSubNum, rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM).toString()); // same
            // val
            // TRX_LINE_SUB_NUM
            ZYPEZDItemValueSetter.setValue(pmsg.billToCustCd, rs.getString(VND_CD)); // same
            // val
            // BILL_TO_VND_CD
            ZYPEZDItemValueSetter.setValue(pmsg.sellToCustCd, rs.getString(VND_CD)); // same
            // val
            // DEBIT_TO_VND_CD
            ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, rs.getString(VND_CD)); // same
            // val
            // SHIP_TO_VND_CD

            // START 2019/02/26 S.Takami [QC#30451,ADD]
            ZYPEZDItemValueSetter.setValue(pmsg.xxUnitPrc, rs.getBigDecimal(ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(pmsg.xxSlsAmt, rs.getBigDecimal(ENT_PO_DTL_DEAL_NET_AMT));
          // END 2019/02/26 S.Takami [QC#30451,ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(pmsg.trxHdrNum, invOrdPmsg.invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(pmsg.trxLineNum, invOrdPmsg.invtyOrdLineNum);
            ZYPEZDItemValueSetter.setValue(pmsg.trxLineSubNum, START_LINE_NUM);
            ZYPEZDItemValueSetter.setValue(pmsg.billToCustCd, invOrdPmsg.invtyLocCd_D1);
            ZYPEZDItemValueSetter.setValue(pmsg.sellToCustCd, invOrdPmsg.invtyLocCd_D1);
            ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, invOrdPmsg.invtyLocCd_D1);

            // START 2019/02/26 S.Takami [QC#30451,MOD]
            ZYPEZDItemValueSetter.setValue(pmsg.xxUnitPrc, new BigDecimal(0));
            ZYPEZDItemValueSetter.setValue(pmsg.xxSlsAmt, new BigDecimal(0));
            // END 2019/02/26 S.Takami [QC#30451,MOD]
        }
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, rs.getString(SRC_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, rs.getString(FROM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, rs.getBigDecimal(PRCH_REQ_QTY));

        String shpgSvcLvlCd = rs.getString(SHPG_SVC_LVL_CD);
        String shpgSvcTpCd = null;
        if (shpgSvcLvlCd != null) {
            shpgSvcTpCd = getShpgSvcTpCd(shpgSvcLvlCd);
        }
        if (shpgSvcTpCd != null && SHPG_SVC_TP.PICK_UP.equals(shpgSvcTpCd)) {
            ZYPEZDItemValueSetter.setValue(pmsg.frtChrgMethCd, FRT_CHRG_METH.PICK_UP_NO_CHARGE);
            ZYPEZDItemValueSetter.setValue(pmsg.frtChrgToCd, FRT_CHRG_TO.CUSTOMER);
        } else {
            ZYPEZDItemValueSetter.setValue(pmsg.frtChrgMethCd, FRT_CHRG_METH.N_OR_A);
            ZYPEZDItemValueSetter.setValue(pmsg.frtChrgToCd, FRT_CHRG_TO.CANON);
        }

        ZYPEZDItemValueSetter.setValue(pmsg.carrCd, rs.getString(CARR_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.carrAcctNum, rs.getString(CARR_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(pmsg.shpgSvcLvlCd, rs.getString(SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.xxOrdTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmssSSS"));
        ZYPEZDItemValueSetter.setValue(pmsg.rddDt, rs.getString(RQST_RCV_DT));
        // START 2019/02/26 S.Takami [QC#30451,DEL]
//        ZYPEZDItemValueSetter.setValue(pmsg.xxUnitPrc, new BigDecimal(0));
//        ZYPEZDItemValueSetter.setValue(pmsg.xxSlsAmt, new BigDecimal(0));
        // END 2019/02/26 S.Takami [QC#30451,DEL]

        // START 2019/02/06 M.Naito [QC#30156,MOD]
//        if (hasValue(rs.getString(SHIP_TO_CUST_CD))) {
        // QC#54267 Start
        String strTrxSrcTpCd =  rs.getString(TRX_SRC_TP_CD);
        String vndCd = rs.getString(VND_CD);
        String shiptoCustCd = rs.getString(SHIP_TO_CUST_CD);

        if (!TRX_SRC_TP.WHOLE_SALES.equals(strTrxSrcTpCd) //
                && ZYPCommonFunc.hasValue(vndCd) && ZYPCommonFunc.hasValue(shiptoCustCd) //
                && !vndCd.equals(shiptoCustCd)) {
            ZYPEZDItemValueSetter.setValue(pmsg.dropShipFlg, ZYPConstant.FLG_ON_Y);
        // QC#54267 End
        } else if (IsDropShip(rs)) {
        // END 2019/02/06 M.Naito [QC#30156,MOD]
            ZYPEZDItemValueSetter.setValue(pmsg.dropShipFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pmsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(pmsg.xxShipToName, rs.getString(SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(pmsg.xxShipToNameAddl, rs.getString(SHIP_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToFirstLineAddr, rs.getString(SHIP_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToScdLineAddr, rs.getString(SHIP_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToThirdLineAddr, rs.getString(SHIP_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToFrthLineAddr, rs.getString(SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToCtyAddr, rs.getString(SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToCntyNm, rs.getString(SHIP_TO_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(pmsg.xxShipToProvName, rs.getString(SHIP_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToStCd, rs.getString(SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToPostCd, rs.getString(SHIP_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToCtryCd, rs.getString(SHIP_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToFirstRefCmntTxt, rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(pmsg.shipToScdRefCmntTxt, rs.getString(SHIP_TO_SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(pmsg.slsDt, salesDate);

        new NWZC107001().execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;
    }

    /**
     * Get SHPG_SVC_TP_CD
     */
    private String getShpgSvcTpCd(String shpgSvcLvlCd) {
        SHPG_SVC_LVLTMsg tMsg = new SHPG_SVC_LVLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, shpgSvcLvlCd);
        tMsg = (SHPG_SVC_LVLTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.shpgSvcTpCd.getValue();
    }

    /**
     * Call Shipping Plan Update API
     */
    private NWZC003001PMsg callShpgPlnUpdApi(ResultSet rs, NLZC003001PMsg invOrdPmsg, NWZC107001PMsg alcPmsg) throws SQLException {
        NWZC003001PMsg pmsg = new NWZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
        ZYPEZDItemValueSetter.setValue(pmsg.trxSrcTpCd, rs.getString(TRX_SRC_TP_CD));
        ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, rs.getString(MDSE_CD));
        if (invOrdPmsg == null) { // this case is Vendor Return
            ZYPEZDItemValueSetter.setValue(pmsg.trxHdrNum, rs.getString(PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.trxLineNum, rs.getString(PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.trxLineSubNum, rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM).toString());
        } else {
            ZYPEZDItemValueSetter.setValue(pmsg.trxHdrNum, invOrdPmsg.invtyOrdNum);
            ZYPEZDItemValueSetter.setValue(pmsg.trxLineNum, invOrdPmsg.invtyOrdLineNum);
            ZYPEZDItemValueSetter.setValue(pmsg.trxLineSubNum, START_LINE_NUM);
        }
        ZYPEZDItemValueSetter.setValue(pmsg.avalSoQty, alcPmsg.allocQty);

        // split DELY_ADDL_CMNT_TXT by 60 byte
        String delyAddlCmntTxt = rs.getString(DELY_ADDL_CMNT_TXT);
        // under 60 byte
        if (hasValue(delyAddlCmntTxt) && delyAddlCmntTxt.length() <= MAX_CMNT_LENGTH) {
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntFirstLineTxt, delyAddlCmntTxt);
            // under 120 byte
        } else if (hasValue(delyAddlCmntTxt) && delyAddlCmntTxt.length() <= MAX_CMNT_LENGTH * 2) {
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntFirstLineTxt, delyAddlCmntTxt.substring(0, MAX_CMNT_LENGTH));
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntScdLineTxt, delyAddlCmntTxt.substring(MAX_CMNT_LENGTH));
            // under 180 byte
        } else if (hasValue(delyAddlCmntTxt) && delyAddlCmntTxt.length() <= MAX_CMNT_LENGTH * 3) {
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntFirstLineTxt, delyAddlCmntTxt.substring(0, MAX_CMNT_LENGTH));
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntScdLineTxt, delyAddlCmntTxt.substring(MAX_CMNT_LENGTH, MAX_CMNT_LENGTH * 2));
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntThirdLineTxt, delyAddlCmntTxt.substring(MAX_CMNT_LENGTH * 2));
            // under 240 byte
        } else if (hasValue(delyAddlCmntTxt)) {
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntFirstLineTxt, delyAddlCmntTxt.substring(0, MAX_CMNT_LENGTH));
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntScdLineTxt, delyAddlCmntTxt.substring(MAX_CMNT_LENGTH, MAX_CMNT_LENGTH * 2));
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntThirdLineTxt, delyAddlCmntTxt.substring(MAX_CMNT_LENGTH * 2, MAX_CMNT_LENGTH * 3));
            ZYPEZDItemValueSetter.setValue(pmsg.shipCmntFrthLineTxt, delyAddlCmntTxt.substring(MAX_CMNT_LENGTH * 3));
        }

        List<NWZC003001PMsg> pMsgList = new ArrayList<NWZC003001PMsg>();
        pMsgList.add(pmsg);
        new NWZC003001().execute(pMsgList, ONBATCH_TYPE.BATCH);
        return pMsgList.get(0);
    }

    /**
     * Call SO API
     */
    private List<NLZC205001PMsg> callSoApi(String sceOrdTpCd, List<Map<String, Object>> shpgPlnList) {
        List<NLZC205001PMsg> pMsgList = new ArrayList<NLZC205001PMsg>();
        for (Map<String, Object> shpgPln : shpgPlnList) {
            NLZC205001PMsg pmsg = new NLZC205001PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pmsg.sceOrdTpCd, sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(pmsg.shpgPlnNum, (String) shpgPln.get(SHPG_PLN_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NLZC205001.MODE_NEW);
            pMsgList.add(pmsg);
        }
        new NLZC205001().execute(pMsgList, ONBATCH_TYPE.BATCH);
        return pMsgList;
    }

    /**
     * Call Machine Master Update API
     */
    private NSZC001001PMsg callMacMstUpdApi(List<Map<String, Object>> macMstList, String hdrNum, String lineNum, String lineSubNum) {
        NSZC001001PMsg pmsg = new NSZC001001PMsg();
        for (Map<String, Object> macMst : macMstList) {
            if (ZYPConstant.FLG_ON_Y.equals(macMst.get(INSTL_BASE_CTRL_FLG))) {
                pmsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pmsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
                ZYPEZDItemValueSetter.setValue(pmsg.svcConfigMstrPk, (BigDecimal) macMst.get(SVC_CONFIG_MSTR_PK));
                ZYPEZDItemValueSetter.setValue(pmsg.svcMachMstrPk, (BigDecimal) macMst.get(SVC_MACH_MSTR_PK));
                ZYPEZDItemValueSetter.setValue(pmsg.trxHdrNum, hdrNum);
                ZYPEZDItemValueSetter.setValue(pmsg.trxLineNum, lineNum);
                ZYPEZDItemValueSetter.setValue(pmsg.trxLineSubNum, lineSubNum);
                new NSZC001001().execute(pmsg, ONBATCH_TYPE.BATCH);
            }
        }
        return pmsg;
    }

    /**
     * Call S21 DC SO Confirmation API
     */
    private List<NLZC210001PMsg> callDcSoConfApi(String soNum, String sceOrdTpCd) {

        List<NLZC210001PMsg> pMsgList = new ArrayList<NLZC210001PMsg>();
        List<NLZC210002PMsg> pMsgList2 = new ArrayList<NLZC210002PMsg>();

        // Get Shipping Order info By SO_NUM
        HashMap<String, String> param = new HashMap<String, String>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(SO_NUM, soNum);
        List<Map<String, Object>> soInfoList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getSoInfoBySoNum", param);

        for (Map<String, Object> soInfo : soInfoList) {
            NLZC210001PMsg pmsg = new NLZC210001PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pmsg.whCd, (String) soInfo.get(INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
            ZYPEZDItemValueSetter.setValue(pmsg.sceOrdTpCd, sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd, SO_PROC_STS.SHIP);
            ZYPEZDItemValueSetter.setValue(pmsg.shipDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
            ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, (String) soInfo.get(SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.soProcStsCd_DT, SO_PROC_STS.SHIP);
            ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, (String) soInfo.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(pmsg.fromStkStsCd, (String) soInfo.get(FROM_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(pmsg.shipQty, (BigDecimal) soInfo.get(PRCH_REQ_QTY));
            ZYPEZDItemValueSetter.setValue(pmsg.proNum,  (String) soInfo.get(PRO_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.totFrtAmt, new BigDecimal(0));
            pMsgList.add(pmsg);
        }

        // Get Shipping Order Serial info By SO_NUM
        List<Map<String, Object>> soSerInfoList = (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getSoSerInfoBySoNum", param);

        for (Map<String, Object> soSerInfo : soSerInfoList) {
            NLZC210002PMsg pmsg2 = new NLZC210002PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg2.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(pmsg2.whCd, (String) soSerInfo.get(INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(pmsg2.soNum, soNum);
            ZYPEZDItemValueSetter.setValue(pmsg2.sceOrdTpCd, sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(pmsg2.soProcStsCd, SO_PROC_STS.SHIP);
            ZYPEZDItemValueSetter.setValue(pmsg2.shipDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
            ZYPEZDItemValueSetter.setValue(pmsg2.soSlpNum, (String) soSerInfo.get(SO_SLP_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg2.mdseCd, (String) soSerInfo.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(pmsg2.serNum, (String) soSerInfo.get(SER_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg2.serTakeDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
            pMsgList2.add(pmsg2);
        }

        try {
            new NLZC210001().execute(pMsgList, pMsgList2, ONBATCH_TYPE.BATCH);
        } catch (S21AbendException e) {
            return pMsgList;
        }
        return pMsgList;

    }

    /**
     * call PR Update API For SO Conf
     */
    private NPZC103001PMsg callPrUpdApiForSoConf(List<Map<String, Object>> shpgPlnInfoList, String soNum) throws SQLException {
        NPZC103001PMsg pmsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pmsg.eventId, NPZC103001Constant.EVENT_SHIPPED);

        for (int i = 0; i < shpgPlnInfoList.size(); i++) {
            Map<String, Object> shpgPlnInfo = shpgPlnInfoList.get(i);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqNum, (String) shpgPlnInfo.get(PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineNum, (String) shpgPlnInfo.get(PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).prchReqLineSubNum, (BigDecimal) shpgPlnInfo.get(PRCH_REQ_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).soNum, soNum);
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).shipDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddhhmmss"));
            ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(i).proNum, (String) shpgPlnInfo.get(PRO_NUM));
            pmsg.prchReqInfo.setValidCount(i + 1);
        }
        new NPZC103001().execute(pmsg, ONBATCH_TYPE.BATCH);
        return pmsg;
    }

    /**
     * Error Process
     */
    private boolean errProc(String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum, String errMsgTxt) {

        // Transaction rollback before Error Info regist
        rollback();

        S21InfoLogOutput.println("ERROR:PRCH_REQ_NUM:" + prchReqNum + ", PRCH_REQ_LINE_NUM:" + prchReqLineNum + ", PRCH_REQ_LINE_SUB_NUM:" + prchReqLineSubNum);
        S21InfoLogOutput.println(errMsgTxt);

        NPZC103001PMsg pmsg = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(pmsg.eventId, NPZC103001Constant.EVENT_ORDER_RELEASE);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqNum, prchReqNum);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqStsCd, PRCH_REQ_STS.RELEASE_ERROR);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqLineNum, prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqLineSubNum, prchReqLineSubNum);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqLineStsCd, PRCH_REQ_STS.RELEASE_ERROR);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqRelQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(pmsg.prchReqInfo.no(0).prchReqRelErrMsgTxt, errMsgTxt);
        pmsg.prchReqInfo.setValidCount(1);
        new NPZC103001().execute(pmsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pmsg)) {
            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    String xxMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                    S21InfoLogOutput.println(xxMsgTxt);
                    break;
                }
            }
            return false;
        }

        // Error Info regist commit
        commit();

        return true;
    }

    /**
     * Get Serial Number
     */
    private List<String> getSerNumList(String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(PRCH_REQ_NUM, prchReqNum);
        param.put(PRCH_REQ_LINE_NUM, prchReqLineNum);
        param.put(PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);
        List<String> wkSerNumList = (List<String>) glSsmBatchClient.queryObjectList("getSerNum", param);
        List<String> serNumList = new ArrayList<String>();
        for (String serNum : wkSerNumList) {
            if (hasValue(serNum)) {
                serNumList.add(serNum);
            }
        }
        return serNumList;
    }

    /**
     * Get Service Machine Master PK
     */
    private List<Map<String, Object>> getMacMstList(String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum, List<String> serNumList) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(PRCH_REQ_NUM, prchReqNum);
        param.put(PRCH_REQ_LINE_NUM, prchReqLineNum);
        param.put(PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);
        param.put(SER_NUM, serNumList);
        return (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getMachineMaster", param);
    }

    // QC#55753 Add Start
    /**
     * getNonSerMacMstList
     * @param prchReqNum
     * @param prchReqLineNum
     * @param prchReqLineSubNum
     * @return
     */
    private  List<Map<String, Object>> getNonSerMacMstList(String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(PRCH_REQ_NUM, prchReqNum);
        param.put(PRCH_REQ_LINE_NUM, prchReqLineNum);
        param.put(PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);
        return (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getNonSerMachineMaster", param);
    }
    // QC#55753 Add End

    /**
     * Get Service Machine Master supporting Non-serial IB item
     * A config may have more than one same Non-serial IB items.
     * This method retrieve SVC_MACH_MSTR record from PRCH_REQ info, checking if retrieved record is already used. 
     */
    private List<Map<String, Object>> getAvailableMachMstrList(String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum, List<String> serNumList) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(PRCH_REQ_NUM, prchReqNum);
        param.put(PRCH_REQ_LINE_NUM, prchReqLineNum);
        param.put(PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);
        param.put(SER_NUM, serNumList);
        return (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getAvailableMachineMaster", param);
    }

    /**
     * Get SHPG_PLN Info List
     */
    private List<Map<String, Object>> getShpgPlnList(String hdrNum, String lineNum, String lineSubNum, String trxSrcTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(TRX_HDR_NUM, hdrNum);
        param.put(TRX_LINE_NUM, lineNum);
        param.put(TRX_LINE_SUB_NUM, lineSubNum);
        param.put(TRX_SRC_TP_CD, trxSrcTpCd);
        return (List<Map<String, Object>>) glSsmBatchClient.queryObjectList("getShpgPln", param);
    }

    /**
     * Get TRX_CD and TRX_RSN_CD by SCE Order Type
     */
    private Map<String, Object> getSceOrdTp(String sceOrdTpCd, String inbdOtbdCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(SCE_ORD_TP_CD, sceOrdTpCd);
        param.put(INBD_OTBD_CD, inbdOtbdCd);
        return (Map<String, Object>) glSsmBatchClient.queryObject("getSceOrdTp", param);
    }

    private String setEmptyStringForNull(String value) {
        if (value == null) {
            return "";
        }
        return value;
    }

    /**
     * <pre>
     * Create Import Order Config
     *     1. Check Base Component Existence
     *     2. Get Model ID if Base Component Exists
     *     3. Insert data to DS_IMPT_ORD_CONFIG
     * </pre>
     * @param configItemLineInfoMapList List<Map<String, Object>>
     * @param configTpCd String
     * @return boolean
     */
    private boolean createImptOrdConfig(List<Map<String, Object>> configItemLineInfoMapList, String configTpCd, boolean pFlgDropShip) {

        Map<String, Object> baseComponentLineMap = null;

        if (ZYPCommonFunc.hasValue(configTpCd)) {

            // Check Base Component existence
            for (int i = 0; i < configItemLineInfoMapList.size(); i++) {
                Map<String, Object> configItemLineInfoMap = (Map<String, Object>) configItemLineInfoMapList.get(i);
                String mdseTpCtxTpCd = (String) configItemLineInfoMap.get(MDSE_TP_CTX_TP_CD);
                if (ZYPCommonFunc.hasValue(mdseTpCtxTpCd)) {
                    baseComponentLineMap = configItemLineInfoMap;
                    break;
                }
                String instlBaseCtrlFlg = (String) configItemLineInfoMap.get(INSTL_BASE_CTRL_FLG);
                if (ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                    if (baseComponentLineMap == null) {
                        baseComponentLineMap = configItemLineInfoMap;
                    }
                }
            }
            if (baseComponentLineMap == null) {
                String errMsgTxt = S21MessageFunc.clspGetMessage(NWZM1482E);
                for (int i = 0; i < configItemLineInfoMapList.size(); i++) {
                    Map<String, Object> configItemLineInfoMap = (Map<String, Object>) configItemLineInfoMapList.get(i);
                    String prchReqNum = (String) configItemLineInfoMap.get(PRCH_REQ_NUM);
                    String prchReqLineNum = (String) configItemLineInfoMap.get(PRCH_REQ_LINE_NUM);
                    BigDecimal prchReqLineSubNum = (BigDecimal) configItemLineInfoMap.get(PRCH_REQ_LINE_SUB_NUM);
                    errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                }
                return false;
            }

            // Call Service Model API to get Model ID for Base Component
            if (!callServiceModelAPI(configItemLineInfoMapList, baseComponentLineMap)) {
                return false;
            }
        }

        // Insert data to DS_IMPT_ORD_CONFIG
        if (baseComponentLineMap != null) {
            insertDsImptOrdConf(baseComponentLineMap, pFlgDropShip);
            // QC#22378 Insert data to DS_IMPT_ORD_ISTL_INFOQC#22378
            insertDsImptOrdIstlInfo(baseComponentLineMap);
        } else {
            insertDsImptOrdConf(configItemLineInfoMapList.get(0), pFlgDropShip);
        }
        return true;
    }

    /**
     * <pre>
     * Call Service Model API(NSZC0480)
     * </pre>
     * @param configItemLineInfoMapList List<Map<String, Object>>
     * @param baseComponentLineMap Map<String, Object>
     * @return boolean
     */
    private boolean callServiceModelAPI(List<Map<String, Object>> configItemLineInfoMapList, Map<String, Object> baseComponentLineMap) {

//        String prchReqNumForBaseComponent = (String)baseComponentLineMap.get(PRCH_REQ_NUM);
//        String prchReqLineNumForBaseComponent = (String)baseComponentLineMap.get(PRCH_REQ_LINE_NUM);
        BigDecimal prchReqLineSubNumForBaseComponent = (BigDecimal) baseComponentLineMap.get(PRCH_REQ_LINE_SUB_NUM);

        NSZC048001PMsg pMsg = new NSZC048001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(globalCompanyCode));

        for (int i = 0; i < configItemLineInfoMapList.size(); i++) {
            Map<String, Object> configItemLineInfoMap = (Map<String, Object>) configItemLineInfoMapList.get(i);

            BigDecimal prchReqLineSubNum = (BigDecimal) configItemLineInfoMap.get(PRCH_REQ_LINE_SUB_NUM);
            String mdseCd = (String) configItemLineInfoMap.get(MDSE_CD);

            if (!ZYPCommonFunc.hasValue(mdseCd)) {
                // It's config line, so skip the process.
                continue;
            }
            if (prchReqLineSubNumForBaseComponent.compareTo(prchReqLineSubNum) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntMdseCd, mdseCd);

            } else {
                int ix = pMsg.xxChildMdseCdList.getValidCount();
                ZYPEZDItemValueSetter.setValue(pMsg.xxChildMdseCdList.no(ix++).childMdseCd, mdseCd);
                pMsg.xxChildMdseCdList.setValidCount(ix);
            }
        }
        NSZC048001 smApi = new NSZC048001();
        smApi.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    String errMsgTxt = S21MessageFunc.clspGetMessage(msgId, msgPrms);

                    for (int j = 0; j < configItemLineInfoMapList.size(); j++) {
                        Map<String, Object> configItemLineInfoMap = (Map<String, Object>) configItemLineInfoMapList.get(j);
                        String prchReqNum = (String) configItemLineInfoMap.get(PRCH_REQ_NUM);
                        String prchReqLineNum = (String) configItemLineInfoMap.get(PRCH_REQ_LINE_NUM);
                        BigDecimal prchReqLineSubNum = (BigDecimal) configItemLineInfoMap.get(PRCH_REQ_LINE_SUB_NUM);
                        errProc(prchReqNum, prchReqLineNum, prchReqLineSubNum, errMsgTxt);
                        errorCount++;
                    }
                    return false;
                }
            }
        }

        // Get Model Description
        BigDecimal mdlId = pMsg.mdlId.getValue();
        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
        if (ZYPCommonFunc.hasValue(mdlId)) {
            ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, mdlId);
            dsMdlTMsg = (DS_MDLTMsg) EZDTBLAccessor.findByKey(dsMdlTMsg);
        }

        if (ZYPCommonFunc.hasValue(mdlId)) {
            baseComponentLineMap.put(MDL_ID, mdlId);

            if (dsMdlTMsg != null && ZYPCommonFunc.hasValue(dsMdlTMsg.mdlDescTxt)) {
                baseComponentLineMap.put(MDL_DESC_TXT, dsMdlTMsg.mdlDescTxt.getValue());
                // QC#22378
                baseComponentLineMap.put(SVC_ISTL_RULE_NUM, dsMdlTMsg.svcIstlRuleNum.getValue());
            }
        }

        return true;
    }

    /**
     * Insert DS_IMPT_ORD_CONFIG
     */
    private void insertDsImptOrdConf(Map<String, Object> configItemLineInfoMap, boolean pFlgDropShip) {

        String configTpCd = (String) configItemLineInfoMap.get(CONFIG_TP_CD);

        // Insert data to DS_IMPT_ORD_CONFIG
        DS_IMPT_ORD_CONFIGTMsg tmsg = new DS_IMPT_ORD_CONFIGTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdConfigPk, (BigDecimal) configItemLineInfoMap.get(DS_IMPT_ORD_CONFIG_PK));
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdPk, (BigDecimal) configItemLineInfoMap.get(DS_IMPT_ORD_PK));
        ZYPEZDItemValueSetter.setValue(tmsg.dsOrdPosnNum, String.valueOf((BigDecimal) configItemLineInfoMap.get(DS_ORD_POSN_NUM)));
        ZYPEZDItemValueSetter.setValue(tmsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        if (ZYPCommonFunc.hasValue(configTpCd)) {
            ZYPEZDItemValueSetter.setValue(tmsg.configTpCd, configTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.configTpCd, CONFIG_TP.NEW);
        }
        ZYPEZDItemValueSetter.setValue(tmsg.svcConfigMstrPk, (BigDecimal) configItemLineInfoMap.get(SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tmsg.billToCustAcctCd, (String) configItemLineInfoMap.get(SELL_TO_CUST_CD));
        // START 2019/10/03 M.Naito [QC#52809,ADD]
        if (ZYPCommonFunc.hasValue((String) configItemLineInfoMap.get(BILL_TO_CUST_ACCT_CD))) {
            ZYPEZDItemValueSetter.setValue(tmsg.billToCustAcctCd, (String) configItemLineInfoMap.get(BILL_TO_CUST_ACCT_CD));
        }
        // END 2019/10/03 M.Naito [QC#52809,ADD]
        ZYPEZDItemValueSetter.setValue(tmsg.billToCustLocCd, (String) configItemLineInfoMap.get(BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCustAcctCd, (String) configItemLineInfoMap.get(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCustLocCd, (String) configItemLineInfoMap.get(DEST_RTL_WH_CD));
        if (pFlgDropShip) {
            ZYPEZDItemValueSetter.setValue(tmsg.dropShipFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(tmsg.shipToLocNm, (String) configItemLineInfoMap.get(LOC_NM));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToAddlLocNm, (String) configItemLineInfoMap.get(ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToFirstLineAddr, (String) configItemLineInfoMap.get(FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToScdLineAddr, (String) configItemLineInfoMap.get(SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToThirdLineAddr, (String) configItemLineInfoMap.get(THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToFrthLineAddr, (String) configItemLineInfoMap.get(FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCtyAddr, (String) configItemLineInfoMap.get(CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToStCd, (String) configItemLineInfoMap.get(ST_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToProvNm, (String) configItemLineInfoMap.get(PROV_NM));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCntyNm, (String) configItemLineInfoMap.get(CNTY_NM));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToPostCd, (String) configItemLineInfoMap.get(POST_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToCtryCd, (String) configItemLineInfoMap.get(CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToFirstRefCmntTxt, (String) configItemLineInfoMap.get(FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(tmsg.shipToScdRefCmntTxt, (String) configItemLineInfoMap.get(SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(tmsg.mdlId, (BigDecimal) configItemLineInfoMap.get(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tmsg.mdlDescTxt, (String) configItemLineInfoMap.get(MDL_DESC_TXT));
        EZDTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            rollback();
            String message = S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {"DS_IMPT_ORD_CONFIG" });
            S21InfoLogOutput.println(message);
            throw new S21AbendException(message);
        }
    }

    /**
     * Add QC#22378
     * Insert DS_IMPT_ORD_ISTL_INFO
     */
    private void insertDsImptOrdIstlInfo(Map<String, Object> configItemLineInfoMap) {

        BigDecimal dsImptOrdIstlInfoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_ISTL_INFO_SQ);

        // Insert data to DS_IMPT_ORD_CONFIG
        DS_IMPT_ORD_ISTL_INFOTMsg tmsg = new DS_IMPT_ORD_ISTL_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdIstlInfoPk, dsImptOrdIstlInfoPk);
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdConfigPk, (BigDecimal) configItemLineInfoMap.get(DS_IMPT_ORD_CONFIG_PK));
        ZYPEZDItemValueSetter.setValue(tmsg.dsImptOrdPk, (BigDecimal) configItemLineInfoMap.get(DS_IMPT_ORD_PK));
        ZYPEZDItemValueSetter.setValue(tmsg.svcIstlRuleNum, (String) configItemLineInfoMap.get(SVC_ISTL_RULE_NUM));

        EZDTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            rollback();
            String message = S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {"DS_IMPT_DS_IMPT_ORD_ISTL_INFOORD_CONFIG" });
            S21InfoLogOutput.println(message);
            throw new S21AbendException(message);
        }
    }
    // QC#22517 2018/03/27 Start
    private boolean IsDropShip(ResultSet rs) throws SQLException {

        StringBuffer sb = new StringBuffer();
        String strTrxSrcTpCd =  rs.getString(TRX_SRC_TP_CD);
        String strPrchReqTpCd = rs.getString(PRCH_REQ_TP_CD);
        // Multiple Check
        String strShipToLocNm           = rs.getString(SHIP_TO_LOC_NM);
        String strShipToAddlLocNm       = nullToString(rs.getString(SHIP_TO_ADDL_LOC_NM));
        String strShipToFirstRefCmntTxt = nullToString(rs.getString(SHIP_TO_FIRST_REF_CMNT_TXT));
        String strShipToScdRefCmntTxt   = nullToString(rs.getString(SHIP_TO_SCD_REF_CMNT_TXT));
        String strShipToCityAddr        = nullToString(rs.getString(SHIP_TO_CTY_ADDR));
        String strShipToStCd            = nullToString(rs.getString(SHIP_TO_ST_CD));
        String strShipToProvNm          = nullToString(rs.getString(SHIP_TO_PROV_NM));
        String strShipToCntyNm          = nullToString(rs.getString(SHIP_TO_CNTY_NM));
        String strShipToPostCd          = nullToString(rs.getString(SHIP_TO_POST_CD));
        String strShipToCtryCD          = nullToString(rs.getString(SHIP_TO_CTRY_CD));

        // ---------------------------------
        // WholeSales, ExpenseOrder Check
        // ---------------------------------
        if (!(TRX_SRC_TP.WHOLE_SALES.equals(strTrxSrcTpCd)
             && PRCH_REQ_TP.EXPENSE_ORDER.equals(strPrchReqTpCd))) {
            return false;
        }
        // ---------------------------------
        // Multiplu Check
        // ---------------------------------
        if (SHIP_TO_LOC_MULTIPLE.equals(strShipToLocNm)) {
            return false;
        }
        // ---------------------------------
        // Check Ship info
        // ---------------------------------
        // Get Ship To Customer Information
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(SHIP_TO_CUST_CD, rs.getString(DEST_INVTY_LOC_CD));

        Map<String, Object> shipToCustInfo = (Map<String, Object>) glSsmBatchClient.queryObject("getShipToCustInfo", param);
        if (shipToCustInfo == null) {
            return false;
        }
        // Address Combine(PRCH_REQ SHIP_TO)
        sb = new StringBuffer();
        sb.append(nullToString(rs.getString(SHIP_TO_FIRST_LINE_ADDR)));
        sb.append(nullToString(rs.getString(SHIP_TO_SCD_LINE_ADDR)));
        sb.append(nullToString(rs.getString(SHIP_TO_THIRD_LINE_ADDR)));
        sb.append(nullToString(rs.getString(SHIP_TO_FRTH_LINE_ADDR)));
        String strShipToAddressPrchReq = ZYPCommonFunc.replaceAll(sb.toString(), " ", "") ;

        // Address Combine(PRCH_REQ SHIP_TO)
        sb = new StringBuffer();
        sb.append(nullToString((String) shipToCustInfo.get(FIRST_LINE_ADDR)));
        sb.append(nullToString((String) shipToCustInfo.get(SCD_LINE_ADDR)));
        sb.append(nullToString((String) shipToCustInfo.get(THIRD_LINE_ADDR)));
        sb.append(nullToString((String) shipToCustInfo.get(FRTH_LINE_ADDR)));
        String strShipToAddressShipToCust = ZYPCommonFunc.replaceAll(sb.toString(), " ", "");

        if (!isSameData(strShipToLocNm, (String) shipToCustInfo.get(LOC_NM))) {
            return true;
        }
        if (!isSameData(strShipToAddlLocNm, (String) shipToCustInfo.get(ADDL_LOC_NM))) {
            return true;
        }
        if (!isSameData(strShipToAddressPrchReq, strShipToAddressShipToCust)) {
            return true;
        }
        if (!isSameData(strShipToFirstRefCmntTxt, (String) shipToCustInfo.get(FIRST_REF_CMNT_TXT))) {
            return true;
        }
        if (!isSameData(strShipToScdRefCmntTxt, (String) shipToCustInfo.get(SCD_REF_CMNT_TXT))) {
            return true;
        }
        if (!isSameData(strShipToCityAddr, (String) shipToCustInfo.get(CTY_ADDR))) {
            return true;
        }
        if (!isSameData(strShipToStCd, (String) shipToCustInfo.get(ST_CD))) {
            return true;
        }
        if (!isSameData(strShipToProvNm, (String) shipToCustInfo.get(PROV_NM))) {
            return true;
        }
        if (!isSameData(strShipToCntyNm, (String) shipToCustInfo.get(CNTY_NM))) {
            return true;
        }
        if (!isSameData(strShipToPostCd, (String) shipToCustInfo.get(POST_CD))) {
            return true;
        }
        if (!isSameData(strShipToCtryCD, (String) shipToCustInfo.get(CTRY_CD))) {
            return true;
        }

        return false;
    }
    private String nullToString(String pValue) {
        String retValue = pValue;
        if (!hasValue(retValue)) {
            retValue = "";
        }
        return retValue;
    }
    private boolean isSameData(String pValue1, String pValue2) {
        String strValue1 = pValue1;
        String strValue2 = pValue2;
        if (!hasValue(strValue1)) {
            strValue1 = "";
        }
        if (!hasValue(strValue2)) {
            strValue2 = "";
        }
        return strValue1.equals(strValue2);
    }
    // QC#22517 2018/03/27 End

    // QC#52809 Add
    private NMZC610001PMsg callCustInfoGetApiForDefaultMode(String glblCmpyCd, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode, String xxSelFlg, boolean isConfig) {

        Map<String, Object> ordInfoMap = null;
        if (isConfig) {
            ordInfoMap = glOrdInfoForConfig;
        } else {
            ordInfoMap = glOrdInfoForNoConfig;
        }

        NWXC150001DefaultCustomerBean paramBean = new NWXC150001DefaultCustomerBean();
        paramBean.setGlblCmpyCd(glblCmpyCd);
        paramBean.setDsTrxRuleTpCd(getDsTrxRuleTpCd(glblCmpyCd, ordInfoMap));
        paramBean.setDsAcctNum(dsAcctNum);
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            paramBean.setShipToCustCd(shipToCustCd);
        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {
            if (!S21StringUtil.isEquals(NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, xxMode)) {
                paramBean.setBillToCustCd(billToCustCd);
            }
        }
        paramBean.setXxMode(xxMode);
        paramBean.setXxSelFlg(xxSelFlg);
        paramBean.setOnBatchType(ONBATCH_TYPE.ONLINE);

        NMZC610001PMsg pMsg = NWXC150001DefaultCustomer.getDefaultCustomerData(paramBean);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                return null;
            }
        }

        return pMsg;
    }

    private static String getDsTrxRuleTpCd(String glblCmpyCd, Map<String, Object> ordInfoMap) {

        String dsOrdCatgCd = (String) ordInfoMap.get(DS_ORD_CATG_CD);
        String dsOrdTpCd = (String) ordInfoMap.get(DS_ORD_TP_CD);

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
        condition.setConditionValue("dsOrdTpCd01", dsOrdTpCd);

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }
    // QC#52809 End

    // add start 2022/03/14 QC#59780
    private void updateShipOrdProNumList(List<Map<String, Object>> shpgPlnInfoList, String soNum) {
        String prchReqNum = null;
        BigDecimal prchReqLineSubNum = null;
        String prePrchReqNum = null;
        BigDecimal prePrchReqLineSubNum = null;
        for (int i = 0; i < shpgPlnInfoList.size(); i++) {
            prchReqNum = (String) shpgPlnInfoList.get(i).get(PRCH_REQ_NUM);
            prchReqLineSubNum = (BigDecimal) shpgPlnInfoList.get(i).get(PRCH_REQ_LINE_SUB_NUM);
            if (hasValue(prePrchReqNum) && hasValue(prePrchReqLineSubNum) && prePrchReqNum.equals(prchReqNum) && prePrchReqLineSubNum.equals(prchReqLineSubNum)) {
                continue;
            }
            List<BigDecimal> shpgOrdProNumListPkList = getShpgOrdProNumListPkList(prchReqNum, prchReqLineSubNum);
            for (BigDecimal shpgOrdProNumListPk : shpgOrdProNumListPkList) {
                SHPG_ORD_PRO_NUM_LISTTMsg tmsg = new SHPG_ORD_PRO_NUM_LISTTMsg();
                ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(tmsg.shpgOrdProNumListPk, shpgOrdProNumListPk);
                tmsg = (SHPG_ORD_PRO_NUM_LISTTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tmsg);
                if (tmsg == null) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(tmsg.trxHdrNum, soNum);
                tmsg.prchReqNum.clear();
                tmsg.prchReqLineSubNum.clear();
                EZDTBLAccessor.update(tmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                    rollback();
                    String message = S21MessageFunc.clspGetMessage(NPAM1171E, new String[] {"SHPG_ORD_PRO_NUM_LIST" });
                    S21InfoLogOutput.println(message);
                    throw new S21AbendException(message);
                }
            }
            prePrchReqNum = prchReqNum;
            prePrchReqLineSubNum = prchReqLineSubNum;
        }
    }

    private List<BigDecimal> getShpgOrdProNumListPkList(String prchReqNum, BigDecimal prchReqLineSubNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(GLBL_CMPY_CD, globalCompanyCode);
        param.put(PRCH_REQ_NUM, prchReqNum);
        param.put(PRCH_REQ_LINE_SUB_NUM, prchReqLineSubNum);
        return (List<BigDecimal>) glSsmBatchClient.queryObjectList("getShpgOrdProNumListPkList", param);
    }
    // add end 2022/03/14 QC#59780

    // START 2023/06/13 S.Dong [QC#55629, ADD]
    // get Vendor min Qty from ASL
    private NPZC136001PMsg getMinVndQty (ResultSet rs, BigDecimal reqQty) throws SQLException{
        NPZC136001PMsg pMsg = getVndMinOrdQty(rs, reqQty);
        return pMsg;
    }

    private NPZC136001PMsg getVndMinOrdQty(ResultSet rs, BigDecimal reqQty) throws SQLException {

        NPZC136001 api = new NPZC136001();
        NPZC136001PMsg param = new NPZC136001PMsg();

        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(param.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(param.prchReqQty, reqQty);
        ZYPEZDItemValueSetter.setValue(param.prchReqRecTpCd, rs.getString("PRCH_REQ_REC_TP_CD"));
        if (ZYPCommonFunc.hasValue(rs.getString("SHIP_TO_CUST_CD"))) {
            ZYPEZDItemValueSetter.setValue(param.shipToCustCd, rs.getString("SHIP_TO_CUST_CD"));
        }
        if (ZYPCommonFunc.hasValue(rs.getString("PRNT_VND_CD"))) {
            ZYPEZDItemValueSetter.setValue(param.prntVndCd, rs.getString("PRNT_VND_CD"));
        }
        if (ZYPCommonFunc.hasValue(rs.getString("VND_CD"))) {
            ZYPEZDItemValueSetter.setValue(param.vndCd, rs.getString("VND_CD"));
        }
        api.execute(param, ONBATCH_TYPE.BATCH);

        return param;
    }

    private boolean updatePrReqQty (String prchReqNum, String prchReqLineNum, BigDecimal prchReqQty, BigDecimal prchReqLineSubNum) {
        PRCH_REQ_DTLTMsg dtlMsg = new PRCH_REQ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dtlMsg.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(dtlMsg.prchReqNum, prchReqNum);
        ZYPEZDItemValueSetter.setValue(dtlMsg.prchReqLineNum, prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(dtlMsg.prchReqLineSubNum, prchReqLineSubNum);
        dtlMsg = getPrchReqDtl(dtlMsg);
        if (dtlMsg != null) {
        ZYPEZDItemValueSetter.setValue(dtlMsg.prchReqQty, prchReqQty);
        ZYPEZDItemValueSetter.setValue(dtlMsg.prchReqBalQty, prchReqQty);
        ZYPEZDItemValueSetter.setValue(dtlMsg.prchReqDispQty, prchReqQty);
        EZDTBLAccessor.update(dtlMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dtlMsg.getReturnCode())) {
            rollback();
            String message = S21MessageFunc.clspGetMessage(NPAM1171E, new String[] {"PRCH_REQ_DTL" });
            S21InfoLogOutput.println(message);
            throw new S21AbendException(message);
           }
        }
        return true;
    }

    private PRCH_REQ_DTLTMsg getPrchReqDtl(PRCH_REQ_DTLTMsg dtlMsg) {

        PRCH_REQ_DTLTMsg prchReqDtlTMsg = new PRCH_REQ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.glblCmpyCd, dtlMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqNum, dtlMsg.prchReqNum.getValue());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineNum, dtlMsg.prchReqLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(prchReqDtlTMsg.prchReqLineSubNum, dtlMsg.prchReqLineSubNum.getValue());

        return (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(prchReqDtlTMsg);
    }
    // END 2023/06/13 S.Dong [QC#55629, ADD]
}
