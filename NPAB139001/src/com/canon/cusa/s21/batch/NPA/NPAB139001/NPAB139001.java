/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB139001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PO_DTLTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC131001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC104001.NPZC104001;
import com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC131001.NPZC131001;
import com.canon.cusa.s21.api.NPZ.NPZC131001.constant.NPZC131001Constant;
import com.canon.cusa.s21.batch.NPA.NPAB139001.constant.NPAB139001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
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
 * NPAB139001:PO Import
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   CITS            T.Kikuhara      Create          N/A
 * 2016/03/22   CSAI            K.Lee           Update          QC#5856
 * 2016/03/22   CSAI            K.Lee           Update          QC#5836
 * 2016/03/24   CSAI            K.Lee           Update          QC#6039
 * 2016/03/24   CSAI            K.Lee           Update          QC#5961
 * 2016/06/23   CSAI            K.Lee           Update          QC#10717
 * 2016/07/08   CSAI            K.Lee           Update          QC#11397
 * 2016/07/08   CSAI            T.Kikuhara      Update          QC#10919
 * 2016/07/14   CITS            K.Ogino         Update          QC#11934
 * 2016/07/15   CITS            K.Ogino         Update          QC#11938
 * 2016/08/17   CSAI            K.Lee           Update          QC#12573
 * 2016/08/18   CSAI            K.Lee           Update          QC#10455
 * 2016/09/06   CITS            K.Ogino         Update          QC#11133
 * 2016/09/26   CITS            K.Ogino         Update          QC#8195
 * 2016/12/20   CITS            S.Endo          Update          QC#15717, CheckStyle Error fix
 * 2016/12/21   CITS            K.Ogino         Update          QC#16311
 * 2016/12/22   CITS            S.Endo          Update          QC#15717
 * 2017/09/21   CITS            R.Shimamoto     Update          QC#21271
 * 2017/09/25   CITS            R.Shimamoto     Update          QC#21006
 * 2017/09/29   CITS            R.Shimamoto     Update          QC#21006-1
 * 2017/10/05   CITS            S.Katsuma       Update          QC#21182
 * 2017/10/06   CITS            T.Tokutomi      Update          QC#21209
 * 2018/02/20   CITS            K.Ogino         Update          QC#17415
 * 2018/06/14   CITS            T.Hakodate      Update          QC#24932
 * 2018/07/05   CITS            K.Ogino         Update          QC#24918
 * 2018/08/24   CITS            K.Ogino         Update          QC#27490
 * 2018/11/12   CITS            T.Hakodate      Update          QC#28939
 * 2018/12/17   CITS            K.Ogino         Update          QC#29645
 * 2019/03/22   Fujitsu         T.Ogura         Update          QC#30565
 * 2019/04/09   CITS            K.Ogino         Update          QC#31073
 * 2019/12/26   Fujitsu         R.Nakamura      Update          QC#55226
 * 2020/03/02   Fujitsu         T.Ogura         Update          QC#55920
 * 2021/04/28   CITS            F.Deola         Update          QC#58732
 * 2022/11/14   Hitachi         M.Kikushima     Update          QC#56708
 * 2023/02/16   Hitachi         TZ.Win          Update          QC#60966
 * 2023/07/28   Hitachi         R.Takau         Update          QC#60676
 * 2023/10/18   CITS            F.Komaki        Update          QC#61870
 * 2023/11/28   CITS            K.Ikeda         Update          QC#62100
 * 2023/12/13   CITS            K.Ikeda         Update          QC#62738
 *</pre>
 */
public class NPAB139001 extends S21BatchMain {
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

    /** Commit count */
    private int commitCount = 0;

    /** Set PO Order Detail Line Number */
    private String setPoOrdDtlLineNum = "";

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** PO Message */
    List<Map<String, Object>> poMsgList = null;

    /** Serial Count */
    int poSerCnt = 0;

    /** Serial Count For  DropShip */
    int dsSerCnt = 0;


    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Create Material Parts Codes */
    private String[] materialParts = null;

    /** Special Instructions Message set. */
    private LinkedHashSet<String> siMsgSet = new LinkedHashSet<String>();

    /** Add QC#27490. Source Type Code */
    private String[] preApvlSrcTpCd = null;

    /** Add QC#27490. Planning Group */
    private String[] preApvlPrchGrp = null;

    @Override
    protected void initRoutine() {

        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAB139001Constant.NPAM0078E, new String[] {NPAB139001Constant.GLBL_CMPY_CD });
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAB139001Constant.NPAM0078E, new String[] {NPAB139001Constant.SALES_DATE });
        }

        String constValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB139001Constant.VAR_CONST_CREATE_MATERIAL_PARTS, getGlobalCompanyCode());
        if (constValue != null) {
            materialParts = constValue.split(",");
        }

        // QC#27490 Start
        constValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB139001Constant.VAR_CONST_PRE_APPROVE_PR_SRC_TP_CD, getGlobalCompanyCode());
        if (constValue != null) {
            preApvlSrcTpCd = constValue.split(",");
        }

        constValue = ZYPCodeDataUtil.getVarCharConstValue(NPAB139001Constant.VAR_CONST_PRE_APPROVE_PRCH_GRP_CD, getGlobalCompanyCode());
        if (constValue != null) {
            preApvlPrchGrp = constValue.split(",");
        }
        // QC#27490 End
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // get Order Info
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB139001Constant.GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB139001Constant.PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.PO_REQUISITION); // "20"
            paramMap.put(NPAB139001Constant.PRCH_REQ_REL_STS_CD, PRCH_REQ_REL_STS.IN_COMPLETED); // "0"
            paramMap.put(NPAB139001Constant.PRCH_REQ_APVL_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(NPAB139001Constant.PO_SCHD_REL_DT, salesDate);
            paramMap.put(NPAB139001Constant.PO_MDSE_CMPSN_TP_CD, NPAB139001Constant.CHILED);     // "2"
            paramMap.put("constValues", materialParts);
            // START QC#21006 Add.
            paramMap.put(NPAB139001Constant.DB_PARAM_PR_TP_CD_ITT_OUTBOUND, PRCH_REQ_TP.ITT_OUTBOUND);
        	// END QC#21006 Add.
            // QC#17415
            paramMap.put("parent", PO_MDSE_CMPSN_TP.PARENT);
            paramMap.put("child", PO_MDSE_CMPSN_TP.CHILD);
            paramMap.put("parts", VND_SYS_TP.PARTS);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement(NPAB139001Constant.GET_PR_INTERFACE, paramMap, execParam);
            rs = preparedStatement.executeQuery();

            String prchReqNum = "";
            String oldPrchReqNum = "";
            String groupKey = "";
            String oldGroupKey = "";
            String msgTxt = "";

            int groupCnt = 0;
            boolean errFlg = false;
            // 2023/11/28 QC#62100 START
            boolean isPOCreated = true;
            // 2023/11/28 QC#62100 END
            Map<String, String> groupKeyPoNumMap = new HashMap<String, String>();

            NPZC103001PMsg prPmsg = new NPZC103001PMsg();
            NPZC104001PMsg poPmsg = new NPZC104001PMsg();
            NPZC104001PMsg poPmsgForDsSerial = new NPZC104001PMsg();
            Map<String, Object> prchReqMap = new HashMap<String, Object>();
            Map<String, Object> prevPrchReqMap = new HashMap<String, Object>();
            List<Map<String, Object>> prchReqList = new ArrayList<Map<String, Object>>();

            while (rs.next()) {

                // QC#16311
                String shpgPlnNum = rs.getString(NPAB139001Constant.SHPG_PLN_NUM);
                if (ZYPCommonFunc.hasValue(shpgPlnNum)) {

                    SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
                    tMsg.glblCmpyCd.setValue(globalCompanyCode);
                    tMsg.shpgPlnNum.setValue(shpgPlnNum);

                    tMsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKey(tMsg);

                    if (tMsg == null) {
                        // PR Line Cancel
                        prUpdateAPILineCancel(rs.getString(NPAB139001Constant.PRCH_REQ_NUM), rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM), rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                        commit();
                        // 2023/11/28 QC#62100 START
                        if (rs.isLast() && isPOCreated) {
                            msgTxt = execBreakProc(rs, poPmsg, poPmsgForDsSerial, prevPrchReqMap);
                            if (ZYPCommonFunc.hasValue(msgTxt)) {
                                errFlg = true;
                                rollback();
                            } else {
                            	groupKeyPoNumMap.put((String) prchReqMap.get(NPAB139001Constant.PR_UPDATE_KEY), poPmsg.poOrdNum.getValue());
                                if (!groupKeyPoNumMap.containsKey(groupKey)) {
                                    groupKeyPoNumMap.put(groupKey, poPmsg.poOrdNum.getValue());
                                }
                                commit();
                            }
                        }
                        // 2023/11/28 QC#62100 END
                        continue;
                    } else if (ZYPConstant.FLG_ON_1.equals(tMsg.ezCancelFlag.getValue())) {
                        // PR Line Cancel
                        prUpdateAPILineCancel(rs.getString(NPAB139001Constant.PRCH_REQ_NUM), rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM), rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                        commit();
                        // 2023/11/28 QC#62100 START
                        if (rs.isLast() && isPOCreated) {
                            msgTxt = execBreakProc(rs, poPmsg, poPmsgForDsSerial, prevPrchReqMap);
                            if (ZYPCommonFunc.hasValue(msgTxt)) {
                                errFlg = true;
                                rollback();
                            } else {
                            	groupKeyPoNumMap.put((String) prchReqMap.get(NPAB139001Constant.PR_UPDATE_KEY), poPmsg.poOrdNum.getValue());
                                if (!groupKeyPoNumMap.containsKey(groupKey)) {
                                    groupKeyPoNumMap.put(groupKey, poPmsg.poOrdNum.getValue());
                                }
                                commit();
                            }
                        }
                        // 2023/11/28 QC#62100 END
                        continue;
                    } else if (!SHPG_STS.VALIDATED.equals(tMsg.shpgStsCd.getValue())) {
                     // PR Line Cancel
                        prUpdateAPILineCancel(rs.getString(NPAB139001Constant.PRCH_REQ_NUM), rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM), rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                        commit();
                        // 2023/11/28 QC#62100 START
                        if (rs.isLast() && isPOCreated) {
                            msgTxt = execBreakProc(rs, poPmsg, poPmsgForDsSerial, prevPrchReqMap);
                            if (ZYPCommonFunc.hasValue(msgTxt)) {
                                errFlg = true;
                                rollback();
                            } else {
                            	groupKeyPoNumMap.put((String) prchReqMap.get(NPAB139001Constant.PR_UPDATE_KEY), poPmsg.poOrdNum.getValue());
                                if (!groupKeyPoNumMap.containsKey(groupKey)) {
                                    groupKeyPoNumMap.put(groupKey, poPmsg.poOrdNum.getValue());
                                }
                                commit();
                            }
                        }
                        // 2023/11/28 QC#62100 END
                        continue;
                    } else if (!RTE_STS.UN_ROUTED.equals(tMsg.rteStsCd.getValue())) {
                     // PR Line Cancel
                        prUpdateAPILineCancel(rs.getString(NPAB139001Constant.PRCH_REQ_NUM), rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM), rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                        commit();
                        // 2023/11/28 QC#62100 START
                        if (rs.isLast() && isPOCreated) {
                            msgTxt = execBreakProc(rs, poPmsg, poPmsgForDsSerial, prevPrchReqMap);
                            if (ZYPCommonFunc.hasValue(msgTxt)) {
                                errFlg = true;
                                rollback();
                            } else {
                            	groupKeyPoNumMap.put((String) prchReqMap.get(NPAB139001Constant.PR_UPDATE_KEY), poPmsg.poOrdNum.getValue());
                                if (!groupKeyPoNumMap.containsKey(groupKey)) {
                                    groupKeyPoNumMap.put(groupKey, poPmsg.poOrdNum.getValue());
                                }
                                commit();
                            }
                        }
                        // 2023/11/28 QC#62100 END
                        continue;
                    }

                    if (!("000".equals(tMsg.trxLineSubNum.getValue())) && isShpgPlnCheck(shpgPlnNum)) {
                        // PR Line Cancel
                        prUpdateAPILineCancel(rs.getString(NPAB139001Constant.PRCH_REQ_NUM), rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM), rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                        commit();
                        // 2023/11/28 QC#62100 START
                        if (rs.isLast() && isPOCreated) {
                            msgTxt = execBreakProc(rs, poPmsg, poPmsgForDsSerial, prevPrchReqMap);
                            if (ZYPCommonFunc.hasValue(msgTxt)) {
                                errFlg = true;
                                rollback();
                            } else {
                            	groupKeyPoNumMap.put((String) prchReqMap.get(NPAB139001Constant.PR_UPDATE_KEY), poPmsg.poOrdNum.getValue());
                                if (!groupKeyPoNumMap.containsKey(groupKey)) {
                                    groupKeyPoNumMap.put(groupKey, poPmsg.poOrdNum.getValue());
                                }
                                commit();
                            }
                        }
                        // 2023/11/28 QC#62100 END
                        // Execute Line Cancel Success.
                        continue;
                    }
                }

                prchReqNum = rs.getString(NPAB139001Constant.PRCH_REQ_NUM);
                groupKey = rs.getString(NPAB139001Constant.PRCH_REQ_NUM)
                         + rs.getString(NPAB139001Constant.REL_RQST_TO_PO_ORD_NUM)
                         + rs.getString(NPAB139001Constant.VND_CD)
                         + rs.getString(NPAB139001Constant.DEST_INVTY_LOC_CD)
                         + rs.getString(NPAB139001Constant.RQST_RCV_DT)
                         + rs.getString(NPAB139001Constant.RQST_RCV_TM)
                         + rs.getString(NPAB139001Constant.FRT_CHRG_TO_CD)
                         + rs.getString(NPAB139001Constant.FRT_CHRG_METH_CD)
                         + rs.getString(NPAB139001Constant.FRT_COND_CD)
                         + rs.getString(NPAB139001Constant.SHPG_SVC_LVL_CD)
                         + rs.getString(NPAB139001Constant.CARR_CD)
                         + rs.getString(NPAB139001Constant.CARR_ACCT_NUM)
                         + rs.getBigDecimal("MDSE_ROW_NUMBER").toString();

                prchReqMap = new HashMap<String, Object>();
                prchReqMap.put(NPAB139001Constant.PRCH_REQ_NUM, rs.getString(NPAB139001Constant.PRCH_REQ_NUM));
                prchReqMap.put(NPAB139001Constant.PRCH_REQ_LINE_NUM, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM));
                prchReqMap.put(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM, rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                prchReqMap.put(NPAB139001Constant.PRCH_REQ_QTY, rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_QTY));
                prchReqMap.put(NPAB139001Constant.GROUP_KEY, groupKey);
                prchReqMap.put(NPAB139001Constant.PO_QLFY_CD, rs.getString(NPAB139001Constant.PO_QLFY_CD));
                prchReqMap.put(NPAB139001Constant.PRCH_REQ_TP_CD, rs.getString(NPAB139001Constant.PRCH_REQ_TP_CD));
                prchReqMap.put(NPAB139001Constant.PRCH_REQ_TP_CD, rs.getString(NPAB139001Constant.PRCH_REQ_TP_CD));
                // QC#8159
                String prUpdatekey = rs.getString(NPAB139001Constant.PRCH_REQ_NUM)
                                   + rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM)
                                   + rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM);

                prchReqMap.put(NPAB139001Constant.PR_UPDATE_KEY, prUpdatekey);

                if (rs.isFirst()) {
                    oldPrchReqNum = prchReqNum;
                    oldGroupKey = groupKey;
                } else {

                    if (!oldGroupKey.equals(groupKey) || ZYPConstant.FLG_ON_Y.equals(rs.getString("CUSTOM_FLG"))) {
                        if (!errFlg) {
                        // Execute Break Process

                            msgTxt = execBreakProc(rs, poPmsg, poPmsgForDsSerial, prevPrchReqMap);
                            // 2023/11/28 QC#62100 END
                            isPOCreated = false;
                            // 2023/11/28 QC#62100 END
                            if (ZYPCommonFunc.hasValue(msgTxt)) {
                                errFlg = true;
                                rollback();
                            } else {
                                // QC#8195
                                groupKeyPoNumMap.put((String) prevPrchReqMap.get(NPAB139001Constant.PR_UPDATE_KEY), poPmsg.poOrdNum.getValue());
                                groupKeyPoNumMap.put(oldGroupKey, poPmsg.poOrdNum.getValue());
                            }
                        }
                        oldGroupKey = groupKey;
                        groupCnt = 0;
                        poSerCnt = 0;
                        dsSerCnt = 0;
                        poPmsg = new NPZC104001PMsg();
                        poPmsgForDsSerial = new NPZC104001PMsg();
                        //QC# 21209 modify.
                        siMsgSet.clear();
                    } else {
                        groupCnt++;
                    }

                    if (!oldPrchReqNum.equals(prchReqNum)) {
                        // Execute PR Update API
                        prPmsg = setPrUpdateApiParam(prchReqList, groupKeyPoNumMap, errFlg, msgTxt);
                        new NPZC103001().execute(prPmsg, ONBATCH_TYPE.BATCH);
                        prchReqList = new ArrayList<Map<String, Object>>();
                        commitCount++;
                        commit();
                        // set next group param
                        errFlg = false;
                    }
                }

                if (!errFlg) {

                    if (!setPoCreateApiParam(rs, prchReqMap, poPmsg, poPmsgForDsSerial, groupCnt)) {
                        errFlg = true;
                        msgTxt = S21MessageFunc.clspGetMessage(NPAB139001Constant.NPAM1377E);
                    // 2023/11/28 QC#62100 START
                    // }
                    } else {
                        isPOCreated = true;
                    }
                    // 2023/11/28 QC#62100 END
                    // Last record process
                    if (rs.isLast()) {
                        if (!errFlg) {
                            // Execute Break Process
                            msgTxt = execBreakProc(rs, poPmsg, poPmsgForDsSerial, prevPrchReqMap);
                            if (ZYPCommonFunc.hasValue(msgTxt)) {
                                errFlg = true;
                                rollback();
                            } else {
                                // QC#8195
                                groupKeyPoNumMap.put((String) prchReqMap.get(NPAB139001Constant.PR_UPDATE_KEY), poPmsg.poOrdNum.getValue());
                                // Mod Start 2019/12/26 QC#55226
                                if (!groupKeyPoNumMap.containsKey(groupKey)) {
                                    groupKeyPoNumMap.put(groupKey, poPmsg.poOrdNum.getValue());
                                }
                                // Mod End 2019/12/26 QC#55226
                            }
                        }
                    }
                }
                prchReqList.add(prchReqMap);
                oldPrchReqNum = prchReqNum;
                prevPrchReqMap = prchReqMap;
            } // End Loop

            if (prchReqList.size() > 0) {
                // Execute PR Update API
                prPmsg = setPrUpdateApiParam(prchReqList, groupKeyPoNumMap, errFlg, msgTxt);
                new NPZC103001().execute(prPmsg, ONBATCH_TYPE.BATCH);
                commitCount++;
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
     	// START QC#21006 Add.
        updateFromITTOutboudPrchReq();
        // End QC#21006 Add.
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
        new NPAB139001().executeBatch(NPAB139001.class.getSimpleName());
    }

    /**
     * Execute Break Process
     * @param rs ResultSet
     * @param poPmsg NPZC104001PMsg
     * @return errMsgTxt String
     */
    private String execBreakProc(ResultSet rs, NPZC104001PMsg poPmsg, NPZC104001PMsg poPmsgForDsSerial, Map<String, Object> prchReqMap) {
       String errMsgTxt = null;
       // Execute PO Create API
       new NPZC104001().execute(poPmsg, ONBATCH_TYPE.BATCH);

       if (S21ApiUtil.isXxMsgId(poPmsg)) {
           List<String> msgIds = S21ApiUtil.getXxMsgIdList(poPmsg);
           for (String msgId : msgIds) {
               S21InfoLogOutput.println("PO Create API Error:" + S21MessageFunc.clspGetMessage(msgId));
               if (msgId.endsWith("E") && !ZYPCommonFunc.hasValue(errMsgTxt)) {
                   errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                   break;
               }
           }
       } else {
           // Execute Drop Ship Create API
           NPZC131001PMsg dsPmsg = new NPZC131001PMsg();
           execDropShipCreateApi(rs, poPmsg, poPmsgForDsSerial, dsPmsg, prchReqMap);
           List<String> msgIds = S21ApiUtil.getXxMsgIdList(dsPmsg);
           for (String msgId : msgIds) {
               S21InfoLogOutput.println("Drop Ship Create API Error:" + S21MessageFunc.clspGetMessage(msgId));
               if (msgId.endsWith("E") && !ZYPCommonFunc.hasValue(errMsgTxt)) {
                   errMsgTxt = S21MessageFunc.clspGetMessage(msgId);
                   break;
               }
           }
       }
       return errMsgTxt;
    }

    /**
     * Set PO Create API Parameter
     * @param rs ResultSet
     * @param groupCnt int
     * @return result(OK:true, NG:false) boolean
     */
    private boolean setPoCreateApiParam(ResultSet rs, Map<String, Object> prchReqMap, NPZC104001PMsg poPmsg, NPZC104001PMsg poPmsgForDsSerial, int groupCnt) {

        HashMap<String, Object> paramMap = null;
        List<Map<String, Object>> poInfoList = null;
        List<Map<String, Object>> prSerList = null;

        try {
            if (groupCnt == 0) {
                // get PO_MSG_TXT
                paramMap = new HashMap<String, Object>();
                paramMap.put(NPAB139001Constant.GLBL_CMPY_CD, globalCompanyCode);
                paramMap.put(NPAB139001Constant.PRCH_REQ_NUM, rs.getString(NPAB139001Constant.PRCH_REQ_NUM));
                poMsgList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(NPAB139001Constant.GET_PO_MSG_TXT, paramMap);
            }

            // get PR Serial
            paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB139001Constant.GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB139001Constant.PRCH_REQ_NUM, rs.getString(NPAB139001Constant.PRCH_REQ_NUM));
            paramMap.put(NPAB139001Constant.PRCH_REQ_LINE_NUM, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM));
            paramMap.put(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
            prSerList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(NPAB139001Constant.GET_SER_NUM, paramMap);

            // get Release PO Info
            String ordNum = rs.getString(NPAB139001Constant.REL_RQST_TO_PO_ORD_NUM);
            if (groupCnt == 0 && ordNum != null && !ordNum.isEmpty()) {
                paramMap = new HashMap<String, Object>();
                paramMap.put(NPAB139001Constant.GLBL_CMPY_CD, globalCompanyCode);
                paramMap.put(NPAB139001Constant.PO_ORD_NUM, rs.getString(NPAB139001Constant.REL_RQST_TO_PO_ORD_NUM));
                paramMap.put(NPAB139001Constant.VND_CD, rs.getString(NPAB139001Constant.VND_CD));
                paramMap.put(NPAB139001Constant.PO_HDR_STS_CD, PO_HDR_STS.OPEN);
                paramMap.put(NPAB139001Constant.DEST_RTL_WH_CD, rs.getString(NPAB139001Constant.DEST_RTL_WH_CD));

                String shipToCustCd = rs.getString(NPAB139001Constant.SHIP_TO_CUST_CD);
                if (shipToCustCd == null || shipToCustCd.isEmpty()) {
                    shipToCustCd = rs.getString(NPAB139001Constant.DEST_RTL_WH_CD);
                }
                paramMap.put(NPAB139001Constant.SHIP_TO_CUST_CD, shipToCustCd);
                poInfoList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(NPAB139001Constant.GET_RELEASE_PO_INFO, paramMap);

                // check result count
                if (poInfoList == null || poInfoList.isEmpty()) {
                    // 0 record return
                    return false;
                }
            }

            if (groupCnt == 0) {
                // set PO Create API Param Head
                setPoCreateApiHead(rs, poInfoList, poPmsg);
            }

            // set PO Create API Param Detail
            setPoCreateApiDetail(rs, prchReqMap, prSerList, poInfoList, poPmsg, poPmsgForDsSerial, groupCnt);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        return true;
    }

    /**
     * set PO Create API Param Head
     * @param rs ResultSet
     * @param poInfoRs ResultSet
     * @return result(OK:true, NG:false) boolean
     */
    private void setPoCreateApiHead(ResultSet rs, List<Map<String, Object>> poInfoList, NPZC104001PMsg pMsg) {
        try {
            String ordNum = rs.getString(NPAB139001Constant.REL_RQST_TO_PO_ORD_NUM);
            // QC#31073
            String existPoStsCD = rs.getString("EXIST_PO_STS_CD");
            String existPoApvlStsCD = rs.getString("EXIST_PO_APVL_STS_CD");

            if (ordNum == null || ordNum.isEmpty() || PO_STS.SAVED.equals(existPoStsCD)) {

                // QC# 24932 ADD START
                // 3rd Party Supplier. Create PO Approval Status with
                // ENTERED.
                // Mod QC#27490
                boolean is3rdPty = false;
                if (preApvlPrchGrp != null //
                        && preApvlSrcTpCd != null //
                        && Arrays.asList(preApvlSrcTpCd).contains(rs.getString(NPAB139001Constant.PRCH_REQ_SRC_TP_CD)) //
                        && Arrays.asList(preApvlPrchGrp).contains(rs.getString(NPAB139001Constant.PRCH_GRP_CD))) {

                    is3rdPty = false;

                } else {

                    is3rdPty = searchSupplier(rs.getString(NPAB139001Constant.VND_CD));

                }
                // QC# 24932 ADD END
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_CREATE);
                // QC#31073
                if (ZYPCommonFunc.hasValue(existPoStsCD)){
                    ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, ordNum);
                    ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, existPoApvlStsCD);
                }
                
                // QC# 24932 MOD START
                if (is3rdPty) {
                    ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC104001Constant.EVENT_SAVE);
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.eventId, NPZC104001Constant.EVENT_SUBMIT);
                }
                // QC# 24932 MOD END
                
                ZYPEZDItemValueSetter.setValue(pMsg.procDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpCd, rs.getString(NPAB139001Constant.DS_PO_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, rs.getString(NPAB139001Constant.PO_QLFY_CD));
                // START 2017/10/05 QC#21182
                String poSubmtPsnCd = "";
                String prchReqCratByPsnCd = rs.getString(NPAB139001Constant.PRCH_REQ_CRAT_BY_PSN_CD);
                String defaultPoSubmtPsnCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB139001Constant.NPZC1390_PO_CRAT_SYSTEM_USER, globalCompanyCode);;
                if (ZYPCommonFunc.hasValue(prchReqCratByPsnCd)) {
                    poSubmtPsnCd = prchReqCratByPsnCd;
                } else {
                    poSubmtPsnCd = defaultPoSubmtPsnCd;
                }
                // END 2017/10/05 QC#21182
                String poSubmtTs = salesDate + ZYPDateUtil.getCurrentSystemTime("HHmmssSSS");
                ZYPEZDItemValueSetter.setValue(pMsg.poSubmtPsnCd, poSubmtPsnCd);
                ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, poSubmtTs);
                // QC# 24932 MOD START
                if (is3rdPty) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, PO_APVL_STS.ENTERED);
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, PO_APVL_STS.PRE_APPROVED);
                    ZYPEZDItemValueSetter.setValue(pMsg.poApvlPsnCd, defaultPoSubmtPsnCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.poApvlDt, salesDate);
                    ZYPEZDItemValueSetter.setValue(pMsg.poApvlTs, poSubmtTs);
                }
                ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermCd, rs.getString(NPAB139001Constant.VND_PMT_TERM_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermDescTxt, rs.getString(NPAB139001Constant.VND_PMT_TERM_DESC_TXT));
                // QC# 24932 MOD END

                ZYPEZDItemValueSetter.setValue(pMsg.destRtlWhCd, rs.getString(NPAB139001Constant.DEST_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdSrcCd, rs.getString(NPAB139001Constant.PO_ORD_SRC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, rs.getString(NPAB139001Constant.PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndCd, rs.getString(NPAB139001Constant.VND_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.dealCcyCd, rs.getString(NPAB139001Constant.CCY_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndDropShipFlg, rs.getString(NPAB139001Constant.VND_DROP_SHIP_FLG));
                ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, rs.getString(NPAB139001Constant.PRCH_GRP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, rs.getString(NPAB139001Constant.RQST_TECH_TOC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvDt, rs.getString(NPAB139001Constant.RQST_RCV_DT));
                ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvTm, rs.getString(NPAB139001Constant.RQST_RCV_TM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, rs.getString(NPAB139001Constant.SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, rs.getString(NPAB139001Constant.SHIP_TO_LOC_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToAcctNm, rs.getString(NPAB139001Constant.DS_ACCT_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, rs.getString(NPAB139001Constant.SHIP_TO_ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, rs.getString(NPAB139001Constant.SHIP_TO_FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, rs.getString(NPAB139001Constant.SHIP_TO_SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, rs.getString(NPAB139001Constant.SHIP_TO_THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, rs.getString(NPAB139001Constant.SHIP_TO_FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, rs.getString(NPAB139001Constant.SHIP_TO_FIRST_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, rs.getString(NPAB139001Constant.SHIP_TO_SCD_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, rs.getString(NPAB139001Constant.SHIP_TO_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, rs.getString(NPAB139001Constant.SHIP_TO_ST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, rs.getString(NPAB139001Constant.SHIP_TO_PROV_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, rs.getString(NPAB139001Constant.SHIP_TO_CNTY_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, rs.getString(NPAB139001Constant.SHIP_TO_POST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, rs.getString(NPAB139001Constant.SHIP_TO_CTRY_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, rs.getString(NPAB139001Constant.CTAC_PSN_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.carrCd, rs.getString(NPAB139001Constant.CARR_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, rs.getString(NPAB139001Constant.CARR_ACCT_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, rs.getString(NPAB139001Constant.SHPG_SVC_LVL_CD));
                // 2023/12/06 QC#62738 K.Ikeda START
                // START 2022/11/14 M.Kikushima [QC#56708, ADD]
                // ZYPEZDItemValueSetter.setValue(pMsg.poOrdCmntTxt, rs.getString(NPAB139001Constant.PRCH_REQ_CMNT_TXT));
                // END 2022/11/14 M.Kikushima [QC#56708, ADD]
                
                String reqLineComment = rs.getString(NPAB139001Constant.PRCH_REQ_CMNT_TXT);
                if (reqLineComment != null) {
                    if (reqLineComment.length() > NPAB139001Constant.MAX_PO_ORD_DTL_CMNT_TXT) {
                        reqLineComment = reqLineComment.substring(0, NPAB139001Constant.MAX_PO_ORD_DTL_CMNT_TXT);
                    }
                }
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdCmntTxt, reqLineComment);
                // 2023/12/06 QC#62738 K.Ikeda END
                
                ZYPEZDItemValueSetter.setValue(pMsg.frtChrgToCd, rs.getString(NPAB139001Constant.FRT_CHRG_TO_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.frtChrgMethCd, rs.getString(NPAB139001Constant.FRT_CHRG_METH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, rs.getString(NPAB139001Constant.LINE_BIZ_TP_CD));
                // START 2017/09/21 QC#21271
                if (DS_PO_TP.ROSS_PO.equals(rs.getString(NPAB139001Constant.DS_PO_TP_CD))) {
                	ZYPEZDItemValueSetter.setValue(pMsg.shipFromSoNumListTxt, rs.getString(NPAB139001Constant.TRX_REF_NUM));
                }
                // END 2017/09/21 QC#21271
                // START 2023/02/16 TZ.Win [QC#60966, ADD]
                // ZYPEZDItemValueSetter.setValue(pMsg.rqstShipDt, rs.getString(NPAB139001Constant.RQST_SHIP_DT));
                // END 2023/02/16 TZ.Win [QC#60966, ADD]
                // 2023/10/18 QC#61870 START
                ZYPEZDItemValueSetter.setValue(pMsg.rqstShipDt, rs.getString(NPAB139001Constant.RQST_SHIP_DT_H));
                // 2023/10/18 QC#61870 END

            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, globalCompanyCode);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NPZC104001Constant.MODE_UPDATE);
                ZYPEZDItemValueSetter.setValue(pMsg.procDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                // QC#31073
                if (ZYPCommonFunc.hasValue(existPoApvlStsCD) && (PO_APVL_STS.APPROVED.equals(existPoApvlStsCD) || PO_APVL_STS.PRE_APPROVED.equals(existPoApvlStsCD))) {
                    pMsg.wfFlg.setValue(ZYPConstant.FLG_OFF_N);
                }
                // QC#29645
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, (String) poInfoList.get(0).get(NPAB139001Constant.PO_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpCd, (String) poInfoList.get(0).get(NPAB139001Constant.DS_PO_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpNm, (String) poInfoList.get(0).get(NPAB139001Constant.DS_PO_TP_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.poQlfyCd, (String) poInfoList.get(0).get(NPAB139001Constant.PO_QLFY_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poSubmtPsnCd, (String) poInfoList.get(0).get(NPAB139001Constant.PO_SUBMT_PSN_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, (String) poInfoList.get(0).get(NPAB139001Constant.PO_SUBMT_TS));
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, (String) poInfoList.get(0).get(NPAB139001Constant.PO_APVL_STS_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlPsnCd, (String) poInfoList.get(0).get(NPAB139001Constant.PO_APVL_PSN_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlDt, (String) poInfoList.get(0).get(NPAB139001Constant.PO_APVL_DT));
                ZYPEZDItemValueSetter.setValue(pMsg.poApvlTs, (String) poInfoList.get(0).get(NPAB139001Constant.PO_APVL_TS));
                ZYPEZDItemValueSetter.setValue(pMsg.destRtlWhCd, (String) poInfoList.get(0).get(NPAB139001Constant.DEST_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.srcRtlWhCd, (String) poInfoList.get(0).get(NPAB139001Constant.SRC_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdSrcCd, (String) poInfoList.get(0).get(NPAB139001Constant.PO_ORD_SRC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, (String) poInfoList.get(0).get(NPAB139001Constant.PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.prntVndNm, (String) poInfoList.get(0).get(NPAB139001Constant.PRNT_VND_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.vndCd, (String) poInfoList.get(0).get(NPAB139001Constant.VND_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndNm, (String) poInfoList.get(0).get(NPAB139001Constant.VND_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.dealCcyCd, (String) poInfoList.get(0).get(NPAB139001Constant.CCY_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndDropShipFlg, (String) poInfoList.get(0).get(NPAB139001Constant.VND_DROP_SHIP_FLG));
                ZYPEZDItemValueSetter.setValue(pMsg.prchGrpCd, (String) poInfoList.get(0).get(NPAB139001Constant.PRCH_GRP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermCd, (String) poInfoList.get(0).get(NPAB139001Constant.VND_PMT_TERM_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermDescTxt, (String) poInfoList.get(0).get(NPAB139001Constant.VND_PMT_TERM_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, (String) poInfoList.get(0).get(NPAB139001Constant.RQST_TECH_TOC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvDt, (String) poInfoList.get(0).get(NPAB139001Constant.RQST_RCV_DT));
                ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvTm, (String) poInfoList.get(0).get(NPAB139001Constant.RQST_RCV_TM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_LOC_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToAcctNm, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_ACCT_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_ADDL_LOC_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_FIRST_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_SCD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_THIRD_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_FRTH_LINE_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_FIRST_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_SCD_REF_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_CTY_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_ST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_PROV_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_CNTY_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_POST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_TO_CTRY_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnNm, (String) poInfoList.get(0).get(NPAB139001Constant.CTAC_PSN_NM));
                ZYPEZDItemValueSetter.setValue(pMsg.rtrnShipToRtlWhCd, (String) poInfoList.get(0).get(NPAB139001Constant.RTRN_SHIP_TO_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.shipFromSoNumListTxt, (String) poInfoList.get(0).get(NPAB139001Constant.SHIP_FROM_SO_NUM_LIST_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.carrCd, (String) poInfoList.get(0).get(NPAB139001Constant.CARR_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, (String) poInfoList.get(0).get(NPAB139001Constant.CARR_ACCT_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, (String) poInfoList.get(0).get(NPAB139001Constant.SHPG_SVC_LVL_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.frtChrgToCd, (String) poInfoList.get(0).get(NPAB139001Constant.FRT_CHRG_TO_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.frtChrgMethCd, (String) poInfoList.get(0).get(NPAB139001Constant.FRT_CHRG_METH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, (String) poInfoList.get(0).get(NPAB139001Constant.LINE_BIZ_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poOrdCmntTxt, (String) poInfoList.get(0).get(NPAB139001Constant.PO_ORD_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.trsmtMethTpCd, (String) poInfoList.get(0).get(NPAB139001Constant.TRSMT_METH_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.sendPoFaxNum, (String) poInfoList.get(0).get(NPAB139001Constant.SEND_PO_FAX_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.sendPoEmlAddr, (String) poInfoList.get(0).get(NPAB139001Constant.SEND_PO_EML_ADDR));
                ZYPEZDItemValueSetter.setValue(pMsg.poSendFlg, (String) poInfoList.get(0).get(NPAB139001Constant.PO_SEND_FLG));
                ZYPEZDItemValueSetter.setValue(pMsg.poSendTs, (String) poInfoList.get(0).get(NPAB139001Constant.PO_SEND_TS));
                ZYPEZDItemValueSetter.setValue(pMsg.poPrintFlg, (String) poInfoList.get(0).get(NPAB139001Constant.PO_PRINT_FLG));
                ZYPEZDItemValueSetter.setValue(pMsg.dsctnInd, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(pMsg.eipRptRqstPk, (BigDecimal) poInfoList.get(0).get(NPAB139001Constant.EIP_RPT_RQST_PK));
                // START 2023/02/16 TZ.Win [QC#60966, ADD]
                // ZYPEZDItemValueSetter.setValue(pMsg.rqstShipDt, (String) poInfoList.get(0).get(NPAB139001Constant.RQST_SHIP_DT));
                // END 2023/02/16 TZ.Win [QC#60966, ADD]
                // 2023/10/18 QC#61870 START
                ZYPEZDItemValueSetter.setValue(pMsg.rqstShipDt, (String) poInfoList.get(0).get(NPAB139001Constant.RQST_SHIP_DT_D));
                // 2023/10/18 QC#61870 END
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    /**
     * set PO Create API Param Detail
     * @param rs ResultSet
     * @param groupCnt int
     * @return result(OK:true, NG:false) boolean
     */
    private void setPoCreateApiDetail(ResultSet rs, Map<String, Object> prchReqMap,  List<Map<String, Object>> prSerList, List<Map<String, Object>> poInfoList, NPZC104001PMsg pMsg, NPZC104001PMsg pMsgForDsSerial, int groupCnt) {
        try {
            String ordNum = rs.getString(NPAB139001Constant.REL_RQST_TO_PO_ORD_NUM);
            int poDtlCnt = pMsg.poLineInfo.getValidCount();
            int poAcctCnt = pMsg.poAcctInfo.getValidCount();
            // QC#29645
            boolean isExistPo = false;

            if (ZYPCommonFunc.hasValue(ordNum) && groupCnt == 0) {
                isExistPo = true;
                for (int i = 0; i < poInfoList.size(); i++) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poOrdDtlLineNum, (String) poInfoList.get(i).get(NPAB139001Constant.PO_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).dispPoDtlLineNum, (String) poInfoList.get(i).get(NPAB139001Constant.DISP_PO_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poLineTpCd, (String) poInfoList.get(i).get(NPAB139001Constant.PO_LINE_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poMdseCmpsnTpCd, (String) poInfoList.get(i).get(NPAB139001Constant.PO_MDSE_CMPSN_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).setPoOrdDtlLineNum, (String) poInfoList.get(i).get(NPAB139001Constant.SET_PO_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).mdseCd, (String) poInfoList.get(i).get(NPAB139001Constant.MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).mdseNm, (String) poInfoList.get(i).get(NPAB139001Constant.MDSE_NM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).mdseDescShortTxt, (String) poInfoList.get(i).get(NPAB139001Constant.MDSE_DESC_SHORT_TXT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poQty, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.PO_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poDispQty, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.PO_DISP_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poInvQty, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.PO_INV_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poDispUomCd, (String) poInfoList.get(i).get(NPAB139001Constant.PO_DISP_UOM_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).thisMthFobCostAmt, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.THIS_MTH_FOB_COST_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).entDealNetUnitPrcAmt, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.ENT_DEAL_NET_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).entPoDtlDealNetAmt, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.ENT_PO_DTL_DEAL_NET_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).entFuncNetUnitPrcAmt, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.ENT_FUNC_NET_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).entPoDtlFuncNetAmt, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.ENT_PO_DTL_FUNC_NET_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).exchRate, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.EXCH_RATE));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).custUomCd, (String) poInfoList.get(i).get(NPAB139001Constant.CUST_UOM_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).destRtlSwhCd, (String) poInfoList.get(i).get(NPAB139001Constant.DEST_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).srcRtlSwhCd, (String) poInfoList.get(i).get(NPAB139001Constant.SRC_RTL_SWH_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).invtyLocCd, (String) poInfoList.get(i).get(NPAB139001Constant.INVTY_LOC_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).rqstRcvDt, (String) poInfoList.get(i).get(NPAB139001Constant.RQST_RCV_DT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).rqstRcvTm, (String) poInfoList.get(i).get(NPAB139001Constant.RQST_RCV_TM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).frtCondCd, (String) poInfoList.get(i).get(NPAB139001Constant.FRT_COND_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).origMdseCd, (String) poInfoList.get(i).get(NPAB139001Constant.ORIG_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).fromStkStsCd, (String) poInfoList.get(i).get(NPAB139001Constant.FROM_STK_STS_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).toStkStsCd, (String) poInfoList.get(i).get(NPAB139001Constant.TO_STK_STS_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).adminPsnCd, (String) poInfoList.get(i).get(NPAB139001Constant.ADMIN_PSN_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poMatchTpCd, (String) poInfoList.get(i).get(NPAB139001Constant.PO_MATCH_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).ordQty, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.ORD_QTY));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).cpoOrdNum, (String) poInfoList.get(i).get(NPAB139001Constant.CPO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).cpoDtlLineNum, (String) poInfoList.get(i).get(NPAB139001Constant.CPO_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).cpoDtlLineSubNum, (String) poInfoList.get(i).get(NPAB139001Constant.CPO_DTL_LINE_SUB_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).custIssPoNum, (String) poInfoList.get(i).get(NPAB139001Constant.CUST_ISS_PO_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).custIssPoDt, (String) poInfoList.get(i).get(NPAB139001Constant.CUST_ISS_PO_DT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).cpoOrdTpCd, (String) poInfoList.get(i).get(NPAB139001Constant.CPO_ORD_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).billToCustCd, (String) poInfoList.get(i).get(NPAB139001Constant.BILL_TO_CUST_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).sellToCustCd, (String) poInfoList.get(i).get(NPAB139001Constant.SELL_TO_CUST_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).shpgPlnNum, (String) poInfoList.get(i).get(NPAB139001Constant.SHPG_PLN_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).prchReqNum, (String) poInfoList.get(i).get(NPAB139001Constant.PRCH_REQ_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).prchReqLineNum, (String) poInfoList.get(i).get(NPAB139001Constant.PRCH_REQ_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).prchReqLineSubNum, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).trxRefNum, (String) poInfoList.get(i).get(NPAB139001Constant.TRX_REF_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).trxRefLineNum, (String) poInfoList.get(i).get(NPAB139001Constant.TRX_REF_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).trxRefLineSubNum, (String) poInfoList.get(i).get(NPAB139001Constant.TRX_REF_LINE_SUB_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).aslDtlPk, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.ASL_DTL_PK));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).aslMdseCd, (String) poInfoList.get(i).get(NPAB139001Constant.ASL_MDSE_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).aslUnitPrcAmt, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.ASL_UNIT_PRC_AMT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).shipFromSoNumListTxt, (String) poInfoList.get(i).get(NPAB139001Constant.SHIP_FROM_SO_NUM_LIST_TXT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).vndInvtyLocCd, (String) poInfoList.get(i).get(NPAB139001Constant.VND_INVTY_LOC_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).vndIssPoOrdNum, (String) poInfoList.get(i).get(NPAB139001Constant.VND_ISS_PO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).proNum, (String) poInfoList.get(i).get(NPAB139001Constant.PRO_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).vndPoAckStsCd, (String) poInfoList.get(i).get(NPAB139001Constant.VND_PO_ACK_STS_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).origPoOrdNum, (String) poInfoList.get(i).get(NPAB139001Constant.ORIG_PO_ORD_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).origPoOrdDtlLineNum, (String) poInfoList.get(i).get(NPAB139001Constant.ORIG_PO_ORD_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).origDispPoDtlLineNum, (String) poInfoList.get(i).get(NPAB139001Constant.ORIG_DISP_PO_DTL_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).svcConfigMstrPk, (BigDecimal) poInfoList.get(i).get(NPAB139001Constant.SVC_CONFIG_MSTR_PK));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poSendTs, (String) poInfoList.get(i).get(NPAB139001Constant.PO_SEND_TS));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poOrdDtlCmntTxt, (String) poInfoList.get(i).get(NPAB139001Constant.PRCH_REQ_LINE_CMNT_TXT));
                    // START 2023/02/16 TZ.Win [QC#60966, ADD]
                    // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).rqstShipDt, (String) poInfoList.get(i).get(NPAB139001Constant.RQST_SHIP_DT));
                    // END 2023/02/16 TZ.Win [QC#60966, ADD]
                    // 2023/10/18 QC#61870 START
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).rqstShipDt, (String) poInfoList.get(i).get(NPAB139001Constant.RQST_SHIP_DT_D));
                    // 2023/10/18 QC#61870 END
                    pMsg.poLineInfo.setValidCount(poDtlCnt + 1);
                    poDtlCnt = pMsg.poLineInfo.getValidCount();
                }
            }

            if (ordNum == null || ordNum.isEmpty() || groupCnt > 0 || isExistPo) {

                // PO Detail
                DecimalFormat df = new DecimalFormat("000");
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poOrdDtlLineNum, df.format(poDtlCnt + 1));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poLineTpCd, rs.getString(NPAB139001Constant.PO_LINE_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poMdseCmpsnTpCd, rs.getString(NPAB139001Constant.PO_MDSE_CMPSN_TP_CD));
                if (pMsg.poLineInfo.no(poDtlCnt).poMdseCmpsnTpCd.getValue().equals(PO_MDSE_CMPSN_TP.CHILD)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).setPoOrdDtlLineNum, setPoOrdDtlLineNum);
                } else if (pMsg.poLineInfo.no(poDtlCnt).poMdseCmpsnTpCd.getValue().equals(PO_MDSE_CMPSN_TP.PARENT)) {
                    setPoOrdDtlLineNum = pMsg.poLineInfo.no(poDtlCnt).poOrdDtlLineNum.getValue();
                } else {
                    setPoOrdDtlLineNum = null;
                }
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).mdseCd, rs.getString(NPAB139001Constant.MDSE_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poQty, rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_QTY));

                // QC#5060 Add.
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poDispQty, rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_DISP_QTY));

                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).entDealNetUnitPrcAmt, rs.getBigDecimal(NPAB139001Constant.ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).entPoDtlDealNetAmt, rs.getBigDecimal(NPAB139001Constant.ENT_PO_DTL_DEAL_NET_AMT));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).custUomCd, rs.getString(NPAB139001Constant.CUST_UOM_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).destRtlSwhCd, rs.getString(NPAB139001Constant.DEST_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).rqstRcvDt, rs.getString(NPAB139001Constant.RQST_RCV_DT));
                // START 2023/02/16 TZ.Win [QC#60966, ADD]
                // ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).rqstShipDt, rs.getString(NPAB139001Constant.RQST_SHIP_DT));
                // END 2023/02/16 TZ.Win [QC#60966, ADD]
                // 2023/10/18 QC#61870 START
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).rqstShipDt, rs.getString(NPAB139001Constant.RQST_SHIP_DT_D));
                // 2023/10/18 QC#61870 END
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).rqstRcvTm, rs.getString(NPAB139001Constant.RQST_RCV_TM));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).frtCondCd, rs.getString(NPAB139001Constant.FRT_COND_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).toStkStsCd, rs.getString(NPAB139001Constant.TO_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).adminPsnCd, rs.getString(NPAB139001Constant.ADMIN_PSN_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).ordQty, rs.getBigDecimal(NPAB139001Constant.ORD_QTY));

                String cpoOrdNum = rs.getString(NPAB139001Constant.CPO_ORD_NUM);
                if (cpoOrdNum != null && !cpoOrdNum.isEmpty()) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).cpoOrdNum, rs.getString(NPAB139001Constant.TRX_REF_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).cpoDtlLineNum, rs.getString(NPAB139001Constant.TRX_REF_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).cpoDtlLineSubNum, rs.getString(NPAB139001Constant.TRX_REF_LINE_SUB_NUM));
                }

                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).custIssPoNum, rs.getString(NPAB139001Constant.CUST_ISS_PO_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).custIssPoDt, rs.getString(NPAB139001Constant.CUST_ISS_PO_DT));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).cpoOrdTpCd, rs.getString(NPAB139001Constant.CPO_ORD_TP_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).billToCustCd, rs.getString(NPAB139001Constant.BILL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).sellToCustCd, rs.getString(NPAB139001Constant.SELL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).shpgPlnNum, rs.getString(NPAB139001Constant.SHPG_PLN_NUM));

                String srcTpCd = rs.getString(NPAB139001Constant.PRCH_REQ_SRC_TP_CD);
                if (srcTpCd != null && !srcTpCd.isEmpty() && PRCH_REQ_SRC_TP.INSOURCING.equals(srcTpCd)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).prchReqNum, rs.getString(NPAB139001Constant.TRX_REF_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).prchReqLineNum, rs.getString(NPAB139001Constant.TRX_REF_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).prchReqLineSubNum, rs.getBigDecimal(NPAB139001Constant.TRX_REF_LINE_SUB_NUM));
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).prchReqNum, rs.getString(NPAB139001Constant.PRCH_REQ_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).prchReqLineNum, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).prchReqLineSubNum, rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                }

                if (PRCH_REQ_SRC_TP.PO_REQUISITION_ENTRY.equals(rs.getString(NPAB139001Constant.PRCH_REQ_SRC_TP_CD))) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).trxRefNum, rs.getString(NPAB139001Constant.PRCH_REQ_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).trxRefLineNum, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).trxRefLineSubNum, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).trxRefNum, rs.getString(NPAB139001Constant.TRX_REF_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).trxRefLineNum, rs.getString(NPAB139001Constant.TRX_REF_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).trxRefLineSubNum, rs.getString(NPAB139001Constant.TRX_REF_LINE_SUB_NUM));
                }
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).aslDtlPk, rs.getBigDecimal(NPAB139001Constant.ASL_DTL_PK));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).aslMdseCd, rs.getString(NPAB139001Constant.ASL_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).aslUnitPrcAmt, rs.getBigDecimal(NPAB139001Constant.ASL_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).vndInvtyLocCd, rs.getString(NPAB139001Constant.VND_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).proNum, rs.getString(NPAB139001Constant.PRO_NUM));
                // START 2021/04/28 F.Deola [QC#58732, MOD]
                //TODO:ここの処理と似てる
                String reqLineComment = rs.getString(NPAB139001Constant.PRCH_REQ_LINE_CMNT_TXT);
                if (reqLineComment != null) {
                    if (reqLineComment.length() > NPAB139001Constant.MAX_PO_ORD_DTL_CMNT_TXT) {
                        reqLineComment = reqLineComment.substring(0, NPAB139001Constant.MAX_PO_ORD_DTL_CMNT_TXT);
                    }
                }
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poOrdDtlCmntTxt, reqLineComment);
//                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poOrdDtlCmntTxt, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_CMNT_TXT));
                // END 2021/04/28 F.Deola [QC#58732, MOD]
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).poDispUomCd, rs.getString(NPAB139001Constant.PRCH_REQ_DSPL_UOM_CD));
                
                // QC#28939 add start
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(poDtlCnt).mdseDescShortTxt, rs.getString(NPAB139001Constant.MDSE_DESC_SHORT_TXT));
                // QC#28939 add end

                pMsg.poLineInfo.setValidCount(poDtlCnt + 1);

                // SER_INFO Detail
                String dropShipQulf = ZYPCodeDataUtil.getVarCharConstValue(NPAB139001Constant.CUST_DROP_SHIP_PO_QULF, globalCompanyCode);
                String poQlfyCd = rs.getString(NPAB139001Constant.PO_QLFY_CD);
                if (prSerList != null) {
                    for (Map<String, Object> prSerMap : prSerList) {
                        String serNum = (String) prSerMap.get(NPAB139001Constant.SER_NUM);
                        String mdseCd = (String) prSerMap.get(NPAB139001Constant.MDSE_CD);
                        if (ZYPCommonFunc.hasValue(serNum)) {
                            if (!ZYPCommonFunc.hasValue(poQlfyCd) || (ZYPCommonFunc.hasValue(poQlfyCd) && !poQlfyCd.equals(dropShipQulf))) {
                                ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(poSerCnt).poOrdDtlLineNum, df.format(poDtlCnt + 1));
                                ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(poSerCnt).serNum, serNum);
                                // START 03/02/2020 T.Ogura [QC#55920,MOD]
//                                ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(poSerCnt).serNum, serNum);
                                ZYPEZDItemValueSetter.setValue(pMsg.serNumList.no(poSerCnt).mdseCd, mdseCd);
                                // END   03/02/2020 T.Ogura [QC#55920,MOD]
                                pMsg.serNumList.setValidCount(poSerCnt + 1);
                                poSerCnt++;
                            }
                            ZYPEZDItemValueSetter.setValue(pMsgForDsSerial.serNumList.no(dsSerCnt).poOrdDtlLineNum, df.format(poDtlCnt + 1));
                            ZYPEZDItemValueSetter.setValue(pMsgForDsSerial.serNumList.no(dsSerCnt).serNum, serNum);
                            ZYPEZDItemValueSetter.setValue(pMsgForDsSerial.poLineInfo.no(dsSerCnt).mdseCd, mdseCd);
                            pMsgForDsSerial.serNumList.setValidCount(dsSerCnt + 1);
                            dsSerCnt++;
                        }
                    }
                }
            }

            // PO_MSG Detail
            int poMsgCnt = 0;
            if (poMsgList != null) {
                for (Map<String, Object> poMsgMap : poMsgList) {
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).poMsgPk, (BigDecimal) poMsgMap.get(NPAB139001Constant.PO_MSG_PK));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).poMsgTpCd, (String) poMsgMap.get(NPAB139001Constant.PO_MSG_TP_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).poMsgSubmtPsnCd, (String) poMsgMap.get(NPAB139001Constant.PO_MSG_SUBMT_PSN_CD));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).xxDsMultMsgDplyTxt, (String) poMsgMap.get(NPAB139001Constant.PO_MSG_TXT));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).prchReqNum, (String) poMsgMap.get(NPAB139001Constant.PRCH_REQ_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).prchReqLineNum, (String) poMsgMap.get(NPAB139001Constant.PRCH_REQ_LINE_NUM));
                    ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).prchReqLineSubNum, (BigDecimal) poMsgMap.get(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                    pMsg.poInfo.setValidCount(poMsgCnt + 1);
                    poMsgCnt++;
                }
            }

            String wkMsgTxt = rs.getString(NPAB139001Constant.SPCL_INSTN_CMNT_TXT);
            // START 2023/08/04 R.Takau  [QC#60676,ADD]
            String spclHdlgTpDescTxt = rs.getString(NPAB139001Constant.SPCL_HDLG_TP_DESC_TXT);
            // END 2023/08/04 R.Takau  [QC#60676,ADD]

            // START 2023/08/04 R.Takau  [QC#60676,MOD]
//            if ((wkMsgTxt != null && !wkMsgTxt.isEmpty()) //
//                    || !siMsgSet.isEmpty()) {
            if ((wkMsgTxt != null && !wkMsgTxt.isEmpty()) //
                    || !siMsgSet.isEmpty() || spclHdlgTpDescTxt != null) {
            // END 2023/08/04 R.Takau  [QC#60676,MOD]
                // QC#21209 modify
                if (ZYPCommonFunc.hasValue(wkMsgTxt)) {
                    siMsgSet.add(wkMsgTxt);
                }

                StringBuffer msgBuf = new StringBuffer();
                for (String s : siMsgSet) {
                    msgBuf.append(s);
                    msgBuf.append(" ");
                }

                // START 2023/07/28 R.Takau  [QC#60676,ADD]
                if (ZYPCommonFunc.hasValue(spclHdlgTpDescTxt)) {
                    msgBuf.append(spclHdlgTpDescTxt);
                    msgBuf.append(" ");
                }
                // END 2023/07/28 R.Takau  [QC#60676,ADD]

                String msg = msgBuf.substring(0, msgBuf.length() - 1);

                if (NPAB139001Constant.XX_DS_MULT_MSG_DPLY_TXT < msg.length()) {
                    // START 2019/03/22 T.Ogura [QC#30565,MOD]
//                    msg = msg.substring(0, NPAB139001Constant.XX_DS_MULT_MSG_DPLY_TXT - 1);
                    msg = msg.substring(0, NPAB139001Constant.XX_DS_MULT_MSG_DPLY_TXT);
                    // END   2019/03/22 T.Ogura [QC#30565,MOD]
                }

                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).poMsgTpCd, NPAB139001Constant.SPECIAL_INSTRUCTIONS);
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).xxDsMultMsgDplyTxt, msg);
                pMsg.poInfo.setValidCount(poMsgCnt + 1);
                poMsgCnt++;
            }

            wkMsgTxt = rs.getString(NPAB139001Constant.DELY_ADDL_CMNT_TXT);
            if (wkMsgTxt != null && !wkMsgTxt.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).poMsgTpCd, NPAB139001Constant.SHIPPER_NOTE);
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).xxDsMultMsgDplyTxt, wkMsgTxt);
                pMsg.poInfo.setValidCount(poMsgCnt + 1);
                poMsgCnt++;
            }

            wkMsgTxt = rs.getString(NPAB139001Constant.RCV_ADDL_CMNT_TXT);
            if (wkMsgTxt != null && !wkMsgTxt.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).poMsgTpCd, NPAB139001Constant.RECEIVER_NOTE);
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).xxDsMultMsgDplyTxt, wkMsgTxt);
                pMsg.poInfo.setValidCount(poMsgCnt + 1);
                poMsgCnt++;
            }

            // set PO Message(Create Material Parts Item info) Mod QC#24918
            if (materialParts != null && Arrays.asList(materialParts).contains(rs.getString(NPAB139001Constant.MDSE_CD))) {
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).poMsgTpCd, PO_MSG_TP.SPECIAL_INSTRUCTIONS);
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).xxDsMultMsgDplyTxt, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_CMNT_TXT));
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).prchReqNum, rs.getString(NPAB139001Constant.PRCH_REQ_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).prchReqLineNum, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(pMsg.poInfo.no(poMsgCnt).prchReqLineSubNum, rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                pMsg.poInfo.setValidCount(poMsgCnt + 1);
                poMsgCnt++;
            }

            //PO_ACCOUNT_INFO(QC#15717)
            DecimalFormat df = new DecimalFormat("000");
            if (ZYPCommonFunc.hasValue(rs.getString(NPAB139001Constant.COA_CMPY_CD))) {
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).poOrdDtlLineNum, df.format(poDtlCnt + 1));
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).poAcctTpCd, PO_ACCT_TP.CHARGE);
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).coaCmpyCd, rs.getString(NPAB139001Constant.COA_CMPY_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).coaAfflCd, rs.getString(NPAB139001Constant.COA_AFFL_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).coaBrCd, rs.getString(NPAB139001Constant.COA_BR_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).coaChCd, rs.getString(NPAB139001Constant.COA_CH_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).coaCcCd, rs.getString(NPAB139001Constant.COA_CC_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).coaAcctCd, rs.getString(NPAB139001Constant.COA_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).coaProdCd, rs.getString(NPAB139001Constant.COA_PROD_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).coaProjCd, rs.getString(NPAB139001Constant.COA_PROJ_CD));
                ZYPEZDItemValueSetter.setValue(pMsg.poAcctInfo.no(poAcctCnt).coaExtnCd, rs.getString(NPAB139001Constant.COA_EXTN_CD));
                pMsg.poAcctInfo.setValidCount(poAcctCnt + 1);
            }
            prchReqMap.put(NPAB139001Constant.PO_ORD_DTL_LINE_NUM, pMsg.poLineInfo.no(poDtlCnt).poOrdDtlLineNum.getValue());

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    /**
     * Execute Drop Ship Create API
     * @param rs ResultSet
     * @param poPmsg NPZC104001PMsg
     * @param dsPmsg NPZC131001PMsg
     * @return result(OK:true, NG:false) boolean
     */
    private void execDropShipCreateApi(ResultSet rs, NPZC104001PMsg poPmsg, NPZC104001PMsg poPmsgForDsSerial, NPZC131001PMsg dsPmsg, Map<String, Object> prchReqMap) {

        String poQlfyCd = ZYPCodeDataUtil.getVarCharConstValue(NPAB139001Constant.CUST_DROP_SHIP_PO_QULF, globalCompanyCode);
        String rossPrType = ZYPCodeDataUtil.getVarCharConstValue(NPAB139001Constant.NPAB1390_ROSS_PR_TYPE, globalCompanyCode);

        try {
            if (!rossPrType.equals((String) prchReqMap.get(NPAB139001Constant.PRCH_REQ_TP_CD))
            ||  !poQlfyCd.equals((String) prchReqMap.get(NPAB139001Constant.PO_QLFY_CD))) {
                return;
            }

            // Execute Drop Ship Create API
            ZYPEZDItemValueSetter.setValue(dsPmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(dsPmsg.xxModeCd, NPZC131001Constant.MODE_CREATE);
            ZYPEZDItemValueSetter.setValue(dsPmsg.slsDt, salesDate);
            ZYPEZDItemValueSetter.setValue(dsPmsg.poOrdNum, poPmsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(dsPmsg.rwsRefNum, poPmsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(dsPmsg.etaDt, rs.getString(NPAB139001Constant.RQST_RCV_DT));
            ZYPEZDItemValueSetter.setValue(dsPmsg.sceOrdTpCd, NPAB139001Constant.SCE_ORD_TP_CD_DN);
            ZYPEZDItemValueSetter.setValue(dsPmsg.vndInvNum, poPmsg.poOrdNum);
            // QC#11934
            ZYPEZDItemValueSetter.setValue(dsPmsg.shipDt, salesDate);

            for (int i = 0; i < poPmsg.poLineInfo.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(dsPmsg.ordDtlInfo.no(i).poOrdDtlLineNum, poPmsg.poLineInfo.no(i).poOrdDtlLineNum);
                ZYPEZDItemValueSetter.setValue(dsPmsg.ordDtlInfo.no(i).mdseCd, poPmsg.poLineInfo.no(i).mdseCd);
                ZYPEZDItemValueSetter.setValue(dsPmsg.ordDtlInfo.no(i).stkStsCd, poPmsg.poLineInfo.no(i).toStkStsCd);
                ZYPEZDItemValueSetter.setValue(dsPmsg.ordDtlInfo.no(i).shipQty, poPmsg.poLineInfo.no(i).poQty);
                ZYPEZDItemValueSetter.setValue(dsPmsg.ordDtlInfo.no(i).carrCd, poPmsg.carrCd);
                dsPmsg.ordDtlInfo.setValidCount(i + 1);
            }

            for (int i = 0; i < poPmsgForDsSerial.serNumList.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(dsPmsg.serNumList.no(i).poOrdDtlLineNum, poPmsgForDsSerial.serNumList.no(i).poOrdDtlLineNum);
                ZYPEZDItemValueSetter.setValue(dsPmsg.serNumList.no(i).mdseCd, poPmsgForDsSerial.poLineInfo.no(i).mdseCd);
                ZYPEZDItemValueSetter.setValue(dsPmsg.serNumList.no(i).serNum, poPmsgForDsSerial.serNumList.no(i).serNum);
                dsPmsg.serNumList.setValidCount(i + 1);
            }

            new NPZC131001().execute(dsPmsg, ONBATCH_TYPE.BATCH);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    /**
     * Set PR Update API Parameter
     */
    private NPZC103001PMsg setPrUpdateApiParam(List<Map<String, Object>> prchReqList, Map<String, String> groupKeyPoNumMap, boolean errFlg, String msgTxt) {
        totalCount++;
        if (errFlg) {
            errorCount++;
        }
        NPZC103001PMsg prPmsg = new NPZC103001PMsg();

        for (int i = 0; i < prchReqList.size(); i++) {
            Map<String, Object> prchReqMap = prchReqList.get(i);
            String groupKey = (String) prchReqMap.get(NPAB139001Constant.PR_UPDATE_KEY);
            if (!ZYPCommonFunc.hasValue(groupKeyPoNumMap.get(groupKey))) {
                groupKey = (String) prchReqMap.get(NPAB139001Constant.GROUP_KEY);
            }
            ZYPEZDItemValueSetter.setValue(prPmsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(prPmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
            ZYPEZDItemValueSetter.setValue(prPmsg.eventId, NPZC103001Constant.EVENT_ORDER_RELEASE);
            ZYPEZDItemValueSetter.setValue(prPmsg.prchReqNum, (String) prchReqMap.get(NPAB139001Constant.PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).prchReqLineNum, (String) prchReqMap.get(NPAB139001Constant.PRCH_REQ_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).prchReqLineSubNum, (BigDecimal) prchReqMap.get(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));

            if (errFlg) {
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqStsCd, PRCH_REQ_STS.RELEASE_ERROR);
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).prchReqLineStsCd, PRCH_REQ_STS.RELEASE_ERROR);
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).prchReqRelQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).prchReqRelStsCd, PRCH_REQ_REL_STS.IN_COMPLETED);
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).prchReqRelErrMsgTxt, msgTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).prchReqLineStsCd, PRCH_REQ_STS.CLOSED);
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).prchReqRelQty, (BigDecimal) prchReqMap.get(NPAB139001Constant.PRCH_REQ_QTY));
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).prchReqRelStsCd, PRCH_REQ_REL_STS.COMPLEATED);
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).poOrdNum, groupKeyPoNumMap.get(groupKey));
                ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(i).poOrdDtlLineNum, (String) prchReqMap.get(NPAB139001Constant.PO_ORD_DTL_LINE_NUM));
            }
            prPmsg.prchReqInfo.setValidCount(i + 1);
        }
        return prPmsg;
    }

    /**
     * prUpdateAPILineCancel
     * @param prchReqNum String
     * @param prchReqLineNum String
     * @param prchReqLineSubNum BigDecimal
     */
    private void prUpdateAPILineCancel(String prchReqNum, String prchReqLineNum, BigDecimal prchReqLineSubNum) {
     // Set PR Update API Parameter. Line Cancel Mode
        NPZC103001PMsg params = new NPZC103001PMsg();
        ZYPEZDItemValueSetter.setValue(params.xxModeCd, NPZC103001Constant.MODE_CANCEL);
        ZYPEZDItemValueSetter.setValue(params.eventId, NPZC103001Constant.EVENT_SUBMIT);
        ZYPEZDItemValueSetter.setValue(params.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(params.prchReqNum, prchReqNum);
        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(0).prchReqLineNum, prchReqLineNum);
        ZYPEZDItemValueSetter.setValue(params.prchReqInfo.no(0).prchReqLineSubNum, prchReqLineSubNum);
        params.prchReqInfo.setValidCount(1);

        // Call PR Update API
        NPZC103001 api = new NPZC103001();
        api.execute(params, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(params)) {
            List<String> msgIds = S21ApiUtil.getXxMsgIdList(params);
            for (String msgId : msgIds) {
                S21InfoLogOutput.println("PR Update API Line Cancel Error:" + S21MessageFunc.clspGetMessage(msgId));
            }
        }
    }

    /**
     * 
     * @param shpgPlnNum
     * @return true:PR Cancel
     */
    private boolean isShpgPlnCheck(String shpgPlnNum) {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(NPAB139001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
        paramMap.put(NPAB139001Constant.DB_PARAM_SHPG_PLN_NUM, shpgPlnNum);
        paramMap.put(NPAB139001Constant.DB_PARAM_FLG_ON_Y, ZYPConstant.FLG_ON_Y);
        paramMap.put(NPAB139001Constant.DB_PARAM_FLG_OFF_N, ZYPConstant.FLG_OFF_N);
        paramMap.put(NPAB139001Constant.DB_PARAM_RTE_STS_CD, RTE_STS.UN_ROUTED);
        paramMap.put(NPAB139001Constant.DB_PARAM_SHPG_STS_CD, SHPG_STS.VALIDATED);
        paramMap.put(NPAB139001Constant.DB_PARAM_ORD_LINE_SRC_CATG_CD, ORD_LINE_SRC_CATG.EXTERNAL);

        List<String> shpgPlnChkList = (List<String>) ssmBatchClient.queryObjectList("isShpgPlnCheck", paramMap);

        // check result count
        if (shpgPlnChkList == null || shpgPlnChkList.isEmpty()) {
            return true;
        }

        return false;
    }

    // QC# 24932 ADD START
    /**
     * searchSupplierType
     */
    private boolean searchSupplier(String vndCd) {

        boolean is3rdPty = true;

        if (!ZYPCommonFunc.hasValue(vndCd)) {
            return false;
        }

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(NPAB139001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
        paramMap.put(NPAB139001Constant.DB_PARAM_VND_CD, vndCd);
        Map<String, Object> supplierCd = (Map<String, Object>) ssmBatchClient.queryObject("searchSupplier", paramMap);

        if (supplierCd == null) {
            is3rdPty = true;
        } else {
            is3rdPty = false;
        }
        return is3rdPty;

    }
    // QC# 24932 ADD END
    
    
    
    /**QC#21006 Add.
     * updateFromITTOutboudPrchReq
     */
    private void updateFromITTOutboudPrchReq() {

    	PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        NPZC103001 nPZC103001 = new NPZC103001();
        boolean poExistsFlg = false;

        try {
        	S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        	S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);

        	// Check CPO Cancel And PO Closed.
        	HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB139001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB139001Constant.DB_PARAM_PR_TP_CD_ITT_OUTBOUND, PRCH_REQ_TP.ITT_OUTBOUND);
            paramMap.put(NPAB139001Constant.DB_PARAM_PR_LINE_STS_CD_CANCEL, PRCH_REQ_LINE_STS.CANCELLED);
            paramMap.put(NPAB139001Constant.DB_PARAM_PR_LINE_STS_CD_CLOSED, PRCH_REQ_LINE_STS.CLOSED);
            paramMap.put(NPAB139001Constant.DB_PARAM_ORD_LINE_STS_CD_CANCEL, ORD_LINE_STS.CANCELLED);

            preparedStatement = ssmLlcClient.createPreparedStatement("checkPrFromITTOutbound", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

            	poExistsFlg = false;

            	// Select For Update PR Detail.
            	PRCH_REQ_DTLTMsg prchReqDtlTmsg = new PRCH_REQ_DTLTMsg();
            	ZYPEZDItemValueSetter.setValue(prchReqDtlTmsg.glblCmpyCd, this.globalCompanyCode);
            	ZYPEZDItemValueSetter.setValue(prchReqDtlTmsg.prchReqNum, rs.getString(NPAB139001Constant.PRCH_REQ_NUM));
            	ZYPEZDItemValueSetter.setValue(prchReqDtlTmsg.prchReqLineNum, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM));
            	ZYPEZDItemValueSetter.setValue(prchReqDtlTmsg.prchReqLineSubNum, rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
            	prchReqDtlTmsg = (PRCH_REQ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prchReqDtlTmsg);

            	if (prchReqDtlTmsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(prchReqDtlTmsg.getReturnCode())) {
            		// Call NPZC103001 PR Update API.
            		NPZC103001PMsg prPmsg = new NPZC103001PMsg();

            		if (ORD_LINE_STS.CANCELLED.equals(rs.getString(NPAB139001Constant.ORD_LINE_STS_CD))
            				|| ZYPConstant.FLG_ON_1.equals(rs.getString(NPAB139001Constant.SHPG_PLN_CANCELFLAG))) {

            			// CPO Line Canceled or Shipping Plan Canceled.
            			ZYPEZDItemValueSetter.setValue(prPmsg.glblCmpyCd, globalCompanyCode);
                        ZYPEZDItemValueSetter.setValue(prPmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
                        ZYPEZDItemValueSetter.setValue(prPmsg.eventId, NPZC103001Constant.EVENT_ORDER_RELEASE);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqNum, prchReqDtlTmsg.prchReqNum);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqLineNum, prchReqDtlTmsg.prchReqLineNum);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqLineSubNum, prchReqDtlTmsg.prchReqLineSubNum);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqLineStsCd, PRCH_REQ_LINE_STS.CANCELLED);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqRelQty, prchReqDtlTmsg.prchReqRelQty);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqRelStsCd, prchReqDtlTmsg.prchReqRelStsCd);
                        prPmsg.prchReqInfo.setValidCount(1);

            	    } else if (!ORD_LINE_STS.CANCELLED.equals(rs.getString(NPAB139001Constant.ORD_LINE_STS_CD))
            				&& ZYPCommonFunc.hasValue(rs.getString(NPAB139001Constant.PO_ORD_NUM))
            				&& ZYPCommonFunc.hasValue(rs.getString(NPAB139001Constant.PO_ORD_DTL_LINE_NUM))) {
            			// PO created update.
            			ZYPEZDItemValueSetter.setValue(prPmsg.glblCmpyCd, globalCompanyCode);
                        ZYPEZDItemValueSetter.setValue(prPmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
                        ZYPEZDItemValueSetter.setValue(prPmsg.eventId, NPZC103001Constant.EVENT_ORDER_RELEASE);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqNum, prchReqDtlTmsg.prchReqNum);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqLineNum, prchReqDtlTmsg.prchReqLineNum);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqLineSubNum, prchReqDtlTmsg.prchReqLineSubNum);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqLineStsCd, PRCH_REQ_LINE_STS.CLOSED);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).poOrdNum, rs.getString(NPAB139001Constant.PO_ORD_NUM));
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).poOrdDtlLineNum, rs.getString(NPAB139001Constant.PO_ORD_DTL_LINE_NUM));
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqRelQty, prchReqDtlTmsg.prchReqRelQty);
                        ZYPEZDItemValueSetter.setValue(prPmsg.prchReqInfo.no(0).prchReqRelStsCd, prchReqDtlTmsg.prchReqRelStsCd);
                        prPmsg.prchReqInfo.setValidCount(1);
                        poExistsFlg = true;

            		}

            		nPZC103001.execute(prPmsg, ONBATCH_TYPE.BATCH);
            		if (S21ApiUtil.isXxMsgId(prPmsg)) {
						List<String> msgIds = S21ApiUtil.getXxMsgIdList(prPmsg);
						for (String msgId : msgIds) {
							S21InfoLogOutput.println("PR Update API Error(ITT Outbound PR):" + S21MessageFunc.clspGetMessage(msgId));
						}
					}
            	}

            	// STATT QC#21006-1 Add.
            	if (poExistsFlg) {

            		// Update PR number of PO.
            		PO_DTLTMsg poTMsg = new PO_DTLTMsg();
            		ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, this.globalCompanyCode);
                	ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, rs.getString(NPAB139001Constant.PO_ORD_NUM));
                	ZYPEZDItemValueSetter.setValue(poTMsg.poOrdDtlLineNum, rs.getString(NPAB139001Constant.PO_ORD_DTL_LINE_NUM));
                	poTMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(poTMsg);

                	if (poTMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(poTMsg.getReturnCode())) {

                		ZYPEZDItemValueSetter.setValue(poTMsg.prchReqNum, rs.getString(NPAB139001Constant.PRCH_REQ_NUM));
                		ZYPEZDItemValueSetter.setValue(poTMsg.prchReqLineNum, rs.getString(NPAB139001Constant.PRCH_REQ_LINE_NUM));
                		ZYPEZDItemValueSetter.setValue(poTMsg.prchReqLineSubNum, rs.getBigDecimal(NPAB139001Constant.PRCH_REQ_LINE_SUB_NUM));
                		EZDTBLAccessor.update(poTMsg);
                	}
            	}
            	// END QC#21006-1 Add.
            }
            commit();

		} catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }

    }
}
