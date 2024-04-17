/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NPA.NPAB260001;

import static com.canon.cusa.s21.batch.NPA.NPAB260001.constant.NPAB260001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CMBN_PO_SO_XREFTMsg;
import business.db.NPAI0400_01TMsg;
import business.db.NPAI0400_02TMsg;
import business.db.NPAI0400_03TMsg;
import business.db.POTMsg;
import business.db.PO_ACK_DTLTMsg;
import business.db.PO_ACK_HDRTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_MSGTMsg;
import business.parts.NPZC004001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreatePOHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001PoMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
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
 *Send PO to EDI Vendor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/11   CITS            T.Hakodate      Created         QC#26964
 * 2018/09/28   CITS            T.Hakodate      Update          QC#28559
 * 2018/12/19   CITS            M.Naito         Update          QC#29698
 * 2019/01/15   Fujitsu         S.Ohki          Update          QC#29892
 * 2019/03/04   CITS            K.Ogino         Update          QC#30571
 * 2019/03/22   Fujitsu         T.Ogura         Update          QC#30565
 * 2020/10/14   CITS            J.Evangelista   Update          QC#57795
 * 2021/04/30   CITS            K.Ogino         Update          QC#58769
 * 2022/03/08   CITS            R.Azucena       Update          QC#59752
 * 2022/03/17   CITS            R.Azucena       Update          QC#59752
 * 2023/01/24   Hitachi         E.Watabe        Update          QC#61046
 * 2023/03/03   Hitachi         E.Watabe        Update          QC#61128
 * 2023/09/25   Hitachi         G.Quan          Update          QC#61608
 */

public class NPAB260001 extends S21BatchMain {

    private TERM_CD termCd;

    private S21UserProfileService profile;

    private String glblCmpyCd;

    private String interfaceId;

    private String sellToLocNmEss = "";

    private String sellToAddLocNmEss = "";

    private String sellToFirstLineAddrEss = "";

    private String sellToScdLineAddrEss = "";

    private String sellToCtyAddrEss = "";

    private String sellToStCdEss = "";

    private String sellToPostCdEss = "";

    private String sellToProvNmEss = "";

    private String sellToCtryCdEss = "";

    private String sellToLocNmLfsPps = "";

    private String sellToAddLocNmLfsPps = "";

    private String sellToFirstLineAddrLfsPps = "";

    private String sellToScdLineAddrLfsPps = "";

    private String sellToCtyAddrLfsPps = "";

    private String sellToStCdLfsPps = "";

    private String sellToPostCdLfsPps = "";

    private String sellToProvNmLfsPps = "";

    private String sellToCtryCdLfsPps = "";

    private int normalRecCount = 0;

    private int totalErrCount = 0;

    private static String carrCdfromXref = "";

    private static boolean carrAcctNumFromXref = false;

    private BigDecimal transactionId = null;

    private S21SsmBatchClient ssmBatchClient = null;

    private BigDecimal unitId = BigDecimal.ZERO;

    private BigDecimal seqNumber = BigDecimal.ONE;

    private List<String> errorMsgList = new ArrayList<String>();

    protected void initRoutine() {

        this.termCd = TERM_CD.NORMAL_END;

        this.profile = S21UserProfileServiceFactory.getInstance().getService();

        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NLGM0049E, new String[] {"Global Company Code" });
        }

        // *********************************************************************************************************
        // Interface
        // *********************************************************************************************************
        this.interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NLGM0049E, new String[] {"Interface ID" });
        }

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // *********************************************************************************************************
        // sell to
        // *********************************************************************************************************
        NPAI0400_01TMsg tmsg = new NPAI0400_01TMsg();

        sellToLocNmEss = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_LOC_NM_ESS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToLocNmEss) && sellToLocNmEss.length() > tmsg.getAttr("sellToLocNm").getDigit()) {
            sellToLocNmEss = sellToLocNmEss.substring(0, tmsg.getAttr("sellToLocNm").getDigit());
        }

        sellToAddLocNmEss = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_ADDL_LOC_NM_ESS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToAddLocNmEss) && sellToAddLocNmEss.length() > tmsg.getAttr("sellToAddlLocNm").getDigit()) {
            sellToAddLocNmEss = sellToAddLocNmEss.substring(0, tmsg.getAttr("sellToAddlLocNm").getDigit());
        }

        sellToFirstLineAddrEss = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_FIRST_LINE_ADDR_ESS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToFirstLineAddrEss) && sellToFirstLineAddrEss.length() > tmsg.getAttr("sellToFirstLineAddr").getDigit()) {
            sellToFirstLineAddrEss = sellToFirstLineAddrEss.substring(0, tmsg.getAttr("sellToFirstLineAddr").getDigit());
        }

        sellToScdLineAddrEss = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_SCD_LINE_ADDR_ESS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToScdLineAddrEss) && sellToScdLineAddrEss.length() > tmsg.getAttr("sellToScdLineAddr").getDigit()) {
            sellToScdLineAddrEss = sellToScdLineAddrEss.substring(0, tmsg.getAttr("sellToScdLineAddr").getDigit());
        }

        sellToCtyAddrEss = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_CTY_ADDR_ESS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToCtyAddrEss) && sellToCtyAddrEss.length() > tmsg.getAttr("sellToCtyAddr").getDigit()) {
            sellToCtyAddrEss = sellToCtyAddrEss.substring(0, tmsg.getAttr("sellToCtyAddr").getDigit());
        }

        sellToStCdEss = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_ST_CD_ESS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToStCdEss) && sellToStCdEss.length() > tmsg.getAttr("sellToStCd").getDigit()) {
            sellToStCdEss = sellToStCdEss.substring(0, tmsg.getAttr("sellToStCd").getDigit());
        }

        sellToPostCdEss = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_POST_CD_ESS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToPostCdEss) && sellToPostCdEss.length() > tmsg.getAttr("sellToPostCd").getDigit()) {
            sellToPostCdEss = sellToPostCdEss.substring(0, tmsg.getAttr("sellToPostCd").getDigit());
        }

        sellToProvNmEss = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_PROV_NM_ESS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToProvNmEss) && sellToProvNmEss.length() > tmsg.getAttr("sellToProvNm").getDigit()) {
            sellToProvNmEss = sellToProvNmEss.substring(0, tmsg.getAttr("sellToProvNm").getDigit());
        }

        sellToCtryCdEss = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_CTRY_CD_ESS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToCtryCdEss) && sellToCtryCdEss.length() > tmsg.getAttr("sellToCtryCd").getDigit()) {
            sellToCtryCdEss = sellToCtryCdEss.substring(0, tmsg.getAttr("sellToCtryCd").getDigit());
        }

        sellToLocNmLfsPps = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_LOC_NM_LFS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToLocNmLfsPps) && sellToLocNmLfsPps.length() > tmsg.getAttr("sellToLocNm").getDigit()) {
            sellToLocNmLfsPps = sellToLocNmLfsPps.substring(0, tmsg.getAttr("sellToLocNm").getDigit());
        }

        sellToAddLocNmLfsPps = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_ADDL_LOC_NM_LFS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToAddLocNmLfsPps) && sellToAddLocNmLfsPps.length() > tmsg.getAttr("sellToAddlLocNm").getDigit()) {
            sellToAddLocNmLfsPps = sellToAddLocNmLfsPps.substring(0, tmsg.getAttr("sellToAddlLocNm").getDigit());
        }

        sellToFirstLineAddrLfsPps = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_FIRST_LINE_ADDR_LFS", this.glblCmpyCd);
        // START 2019/03/22 T.Ogura [QC#30565,MOD]
//        if (ZYPCommonFunc.hasValue(sellToFirstLineAddrLfsPps) && sellToFirstLineAddrLfsPps.length() > tmsg.getAttr("sellToAddlLocNm").getDigit()) {
//            sellToFirstLineAddrLfsPps = sellToFirstLineAddrLfsPps.substring(0, tmsg.getAttr("sellToAddlLocNm").getDigit());
//        }
        if (ZYPCommonFunc.hasValue(sellToFirstLineAddrLfsPps) && sellToFirstLineAddrLfsPps.length() > tmsg.getAttr("sellToFirstLineAddr").getDigit()) {
            sellToFirstLineAddrLfsPps = sellToFirstLineAddrLfsPps.substring(0, tmsg.getAttr("sellToFirstLineAddr").getDigit());
        }
        // END   2019/03/22 T.Ogura [QC#30565,MOD]

        sellToScdLineAddrLfsPps = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_SCD_LINE_ADDR_LFS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToScdLineAddrLfsPps) && sellToScdLineAddrLfsPps.length() > tmsg.getAttr("sellToScdLineAddr").getDigit()) {
            sellToScdLineAddrLfsPps = sellToScdLineAddrLfsPps.substring(0, tmsg.getAttr("sellToScdLineAddr").getDigit());
        }

        sellToCtyAddrLfsPps = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_CTY_ADDR_LFS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToCtyAddrLfsPps) && sellToCtyAddrLfsPps.length() > tmsg.getAttr("sellToCtyAddr").getDigit()) {
            sellToCtyAddrLfsPps = sellToCtyAddrLfsPps.substring(0, tmsg.getAttr("sellToCtyAddr").getDigit());
        }

        sellToStCdLfsPps = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_ST_CD_LFS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToStCdLfsPps) && sellToStCdLfsPps.length() > tmsg.getAttr("sellToStCd").getDigit()) {
            sellToStCdLfsPps = sellToStCdLfsPps.substring(0, tmsg.getAttr("sellToStCd").getDigit());
        }

        sellToPostCdLfsPps = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_POST_CD_LFS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToPostCdLfsPps) && sellToPostCdLfsPps.length() > tmsg.getAttr("sellToPostCd").getDigit()) {
            sellToPostCdLfsPps = sellToPostCdLfsPps.substring(0, tmsg.getAttr("sellToPostCd").getDigit());
        }

        sellToProvNmLfsPps = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_PROV_NM_LFS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToProvNmLfsPps) && sellToProvNmLfsPps.length() > tmsg.getAttr("sellToProvNm").getDigit()) {
            sellToProvNmLfsPps = sellToProvNmLfsPps.substring(0, tmsg.getAttr("sellToProvNm").getDigit());
        }

        sellToCtryCdLfsPps = ZYPCodeDataUtil.getVarCharConstValue("SELL_TO_CTRY_CD_LFS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(sellToCtryCdLfsPps) && sellToCtryCdLfsPps.length() > tmsg.getAttr("sellToCtryCd").getDigit()) {
            sellToCtryCdLfsPps = sellToCtryCdLfsPps.substring(0, tmsg.getAttr("sellToCtryCd").getDigit());
        }

    }

    /**
     * mainRoutine
     */
    protected void mainRoutine() {

        // ****************************************************************
        // create interface data.
        // ****************************************************************
        S21TransactionTableAccessor s21TrxTblAccessor = new S21TransactionTableAccessor();
        transactionId = s21TrxTblAccessor.getNextTransactionId();

        if (INTERFACE_ID_NPAI0400.equals(this.interfaceId)) {
            SendPoForTst();
        } else if (INTERFACE_ID_NPAI0500.equals(this.interfaceId)) {
            SendPoForDietzgen();
            // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//            SendPoForDietzgenForSoOnly();
            // END   2020/10/14 J.Evangelista [QC#57795,DEL]
        // START 2023/03/03 E.Watabe [QC#61128,ADD]
        } else if (INTERFACE_ID_NPAI0600.equals(this.interfaceId)) {
            SendPoForHytec();
        // END 2023/03/03 E.Watabe [QC#61128,ADD]
        // START 2023/09/25 G.Quan [QC#61608, ADD]
        } else if (INTERFACE_ID_NPAI0800.equals(this.interfaceId)) {
            SendPoForAtrix();
        }
        // START 2023/09/25 G.Quan [QC#61608, ADD]

        // ****************************************************************
        // send error Mail
        // ****************************************************************
        if (errorMsgList.size() > 0) {

            termCd = TERM_CD.WARNING_END;

            registerMail();

            commit();

        }

        if (normalRecCount > 0) {
            s21TrxTblAccessor.createIntegrationRecordForBatch(this.interfaceId, transactionId);
        }

    }

    /***********************************************************************************************************
     * Send PO for TST.
     ***********************************************************************************************************/
    private void SendPoForTst() {

        // search PO
        // START 2020/10/14 J.Evangelista [QC#57795,MOD]
        //List<Map<String, Object>> sendPoList = searchPo();
        // START 2023/01/23 E.Watabe[QC#61046, MOD]
        List<Map<String, Object>> sendPoList = searchPo(true);
        // END 2023/01/23 E.Watabe[QC#61046, MOD]
        // END   2020/10/14 J.Evangelista [QC#57795,MOD]

        int dataCount = 0;

        String poOrdNumPrev = "";
        String poOrdNumCur = "";

        boolean isError = false;

        String poSendTs = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);
        BigDecimal poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ);

        // ****************************************************************
        // search send PO info for NPAI0400
        // ****************************************************************
        for (int i = 0; i < sendPoList.size(); i++) {

            Map<String, Object> sendPo = sendPoList.get(i);

            poOrdNumCur = (String) sendPo.get(PO_ORD_NUM);

            // ****************************************************************
            // commit or rollback prev PO.
            // ****************************************************************
            if (ZYPCommonFunc.hasValue(poOrdNumPrev) && !poOrdNumCur.equals(poOrdNumPrev)) {

                if (!isError) {
                    commit();
                    // rollback();
                    this.normalRecCount++;
                } else {
                    rollback();
                    this.totalErrCount++;
                    if (!updatePo(poOrdNumPrev, null, null)) {
                        rollback();
                    } else {
                        commit();
                        // rollback();
                    }
                }

                isError = false;
                carrAcctNumFromXref = false;
                poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ);
            }

            if (!poOrdNumCur.equals(poOrdNumPrev)) {
                if (isError || !createNpai040001(sendPo)) {
                    isError = true;
                }

                if (isError || !createNpai040002(sendPo, poSendTs, poAckHdrPk)) {
                    isError = true;
                }

            }

            // START 2022/03/17 R.Azucena[QC#59752, MOD]
            // if (isError || !createNpai040003(sendPo, poSendTs, poAckHdrPk)) {
            // START 2023/01/23 E.Watabe[QC#61046, MOD]   
            if (isError || !createNpai040003(sendPo, poSendTs, poAckHdrPk, true)) {
            // END 2023/01/23 E.Watabe[QC#61046, MOD]
             // START 2022/03/17 R.Azucena[QC#59752, MOD]
            // END 2022/03/17 R.Azucena[QC#59752, MOD]
                isError = true;
            }

            poOrdNumPrev = poOrdNumCur;
            dataCount++;
        }

        // ****************************************************************
        // commit or rollback Last PO.
        // ****************************************************************
        if (dataCount == 0) {

            // do nothing

        } else if (!isError) {

            commit();
            // rollback();
            this.normalRecCount++;

        } else {

            rollback();
            this.totalErrCount++;

            if (!updatePo(poOrdNumCur, null, null)) {
                rollback();
            } else {
                commit();
                // rollback();
            }
        }

        S21InfoLogOutput.println("Send PO to EDI Vendor Batch is normally end.");

    }

    /***********************************************************************************************************
     * Send PO for Dietzgen.
     ***********************************************************************************************************/
    private void SendPoForDietzgen() {

        int dataCount = 0;

        // search PO
        // START 2020/10/14 J.Evangelista [QC#57795,MOD]
//        List<Map<String, Object>> sendPoList = searchPo();
        List<Map<String, Object>> sendPoList = searchPo(true);
        // END   2020/10/14 J.Evangelista [QC#57795,MOD]

        // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//        List<Map<String, Object>> sendSoList = new ArrayList<Map<String, Object>>();
//        Map<String, Object> skipPo = new HashMap<String, Object>();
        // END   2020/10/14 J.Evangelista [QC#57795,DEL]

        String poOrdNumPrev = "";
        String poOrdNumCur = "";
        // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//        String poOrdDtlLineNum = "";
        // END   2020/10/14 J.Evangelista [QC#57795,DEL]

        boolean isError = false;

        String poSendTs = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);
        BigDecimal poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ);

        // ****************************************************************
        // search send PO info for NPAI0500
        // ****************************************************************
        for (int i = 0; i < sendPoList.size(); i++) {

            Map<String, Object> sendPo = sendPoList.get(i);

            poOrdNumCur = (String) sendPo.get(PO_ORD_NUM);

            // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//            // ****************************************************************
//            // Wait for combine SO. If validated shipping plan exists
//            // skip PO.
//            // ****************************************************************
//            if (skipPo.containsKey(poOrdNumCur)) {
//                continue;
//            }
            // END   2020/10/14 J.Evangelista [QC#57795,DEL]

            // ****************************************************************
            // commit or rollback prev PO.
            // ****************************************************************
            // START 2020/10/14 J.Evangelista [QC#57795,MOD]
//            if (!skipPo.containsKey(poOrdNumPrev) && ZYPCommonFunc.hasValue(poOrdNumPrev) && !poOrdNumCur.equals(poOrdNumPrev)) {
            if (ZYPCommonFunc.hasValue(poOrdNumPrev) && !poOrdNumCur.equals(poOrdNumPrev)) {
            // END   2020/10/14 J.Evangelista [QC#57795,MOD]

                if (!isError) {

                    // START 2020/10/14 J.Evangelista [QC#57795,MOD]
//                    if (createNpai040003ForSo(sendSoList, poOrdNumPrev, poOrdDtlLineNum, poSendTs)) {
//                        commit();
//                        // rollback();
//                        this.normalRecCount++;
//
//                    } else {
//
//                        rollback();
//
//                        // update PO with error.poSendTs is null
//                        if (!updatePo(poOrdNumPrev, null, null) && !updateSo((String) sendSoList.get(0).get(SO_NUM), null)) {
//                            rollback();
//                            this.totalErrCount++;
//                        } else {
//                            commit();
//                            // rollback();
//                            this.totalErrCount++;
//                        }
//                    }
                    commit();
                    // rollback();
                    this.normalRecCount++;
                    // END   2020/10/14 J.Evangelista [QC#57795,MOD]

                } else {

                    rollback();

                    // update PO with error.poSendTs is null
                    if (!updatePo(poOrdNumPrev, null, null)) {
                        rollback();
                        this.totalErrCount++;
                    } else {
                        commit();
                        // rollback();
                    }
                }

                isError = false;
                carrAcctNumFromXref = false;
                poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ);
            }

            if (!poOrdNumCur.equals(poOrdNumPrev)) {

                // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//                // check combine SO.
//                sendSoList = searchSo((String) sendPo.get(CPO_ORD_NUM));
//
//                // Wait for combine SO.
//                if (validatedCombineSoExists((String) sendPo.get(CPO_ORD_NUM), poSendTs)) {
//                    skipPo.put(poOrdNumCur, PO_ORD_NUM);
//                    poOrdNumPrev = poOrdNumCur;
//                    continue;
//                }
                // END 2020/10/14 J.Evangelista [QC#57795,DEL]

                if (isError || !createNpai040001(sendPo)) {
                    isError = true;
                }

                if (isError || !createNpai040002(sendPo, poSendTs, poAckHdrPk)) {
                    isError = true;
                }

            }

            // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//            if (skipPo.containsKey(poOrdNumCur)) {
//                poOrdNumPrev = poOrdNumCur;
//                continue;
//            }
            // END   2020/10/14 J.Evangelista [QC#57795,DEL]

            // START 2022/03/17 R.Azucena[QC#59752, MOD]
            // if (isError || !createNpai040003(sendPo, poSendTs, poAckHdrPk)) {
            if (isError || !createNpai040003(sendPo, poSendTs, poAckHdrPk, true)) {
            // END 2022/03/17 R.Azucena[QC#59752, MOD]
                isError = true;
            }
            // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//            poOrdDtlLineNum = (String) sendPo.get(PO_ORD_DTL_LINE_NUM);
            // END   2020/10/14 J.Evangelista [QC#57795,DEL]
            poOrdNumPrev = poOrdNumCur;
            dataCount++;
        }

        // ****************************************************************
        // commit or rollback Last PO.
        // ****************************************************************

        // START 2020/10/14 J.Evangelista [QC#57795,MOD]
//        if (dataCount == 0 || skipPo.containsKey(poOrdNumCur)) {
        if (dataCount == 0) {
        // END   2020/10/14 J.Evangelista [QC#57795,MOD]

            // do nothing

        } else if (!isError) {
            // START 2020/10/14 J.Evangelista [QC#57795,MOD]
//            if (createNpai040003ForSo(sendSoList, poOrdNumPrev, poOrdDtlLineNum, poSendTs)) {
//                commit();
//                // rollback();
//                this.normalRecCount++;
//
//            } else {
//                rollback();
//                // update PO with error.poSendTs is null
//                if (!updatePo(poOrdNumPrev, null, null)) {
//                    rollback();
//                    this.totalErrCount++;
//                } else {
//                    commit();
//                    // rollback();
//                }
//            }
            commit();
            // rollback();
            this.normalRecCount++;
            // END   2020/10/14 J.Evangelista [QC#57795,MOD]

        } else {

            rollback();

            // update PO with error.poSendTs is null
            if (!updatePo(poOrdNumPrev, null, null)) {
                rollback();
                this.totalErrCount++;
            } else {
                commit();
                // rollback();
            }
        }

    }

    // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//    /***********************************************************************************************************
//     * Send PO for Dietzgen SO ONLY
//     ***********************************************************************************************************/
//    private void SendPoForDietzgenForSoOnly() {
//
//        List<Map<String, Object>> sendSoList = new ArrayList<Map<String, Object>>();
//
//        int dataCount = 0;
//
//        String soSendTs = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);
//
//        String soNumCur = "";
//        String soNumPrev = "";
//
//        boolean isError = false;
//
//        sendSoList = searchSo(null);
//
//        Map<String, Object> skipSo = new HashMap<String, Object>();
//
//        for (int i = 0; i < sendSoList.size(); i++) {
//
//            // check PO exists or not.
//            Map<String, Object> sendSo = sendSoList.get(i);
//
//            soNumCur = (String) sendSo.get(SO_NUM);
//
//            if (skipSo.containsKey(soNumCur)) {
//                continue;
//            }
//
//            // ****************************************************************
//            // commit or rollback prev DO.
//            // ****************************************************************
//            if (ZYPCommonFunc.hasValue(soNumPrev) && !soNumCur.equals(soNumPrev)) {
//
//                if (!isError) {
//                    commit();
//                    // rollback();
//                    this.normalRecCount++;
//                } else {
//                    rollback();
//                    updateSo(soNumPrev, null);
//                    commit();
//                    this.totalErrCount++;
//                }
//
//                isError = false;
//                carrAcctNumFromXref = false;
//            }
//
//            if (validatedCombinePoExists((String) sendSo.get(CPO_ORD_NUM))) {
//                skipSo.put(soNumCur, SO_NUM);
//                soNumPrev = soNumCur;
//                continue;
//            }
//
//            if (!soNumCur.equals(soNumPrev)) {
//
//                if (isError || !createNpai040001ForSoOnly(sendSo)) {
//                    isError = true;
//                }
//
//                if (isError || !createNpai040002ForSoOnly(sendSo, soSendTs)) {
//                    isError = true;
//                }
//
//            }
//
//            if (skipSo.containsKey(soNumCur)) {
//                soNumPrev = soNumCur;
//                continue;
//            }
//
//            if (isError || !createNpai040003ForSoOnly(sendSo, soSendTs)) {
//                isError = true;
//            }
//
//            soNumPrev = soNumCur;
//            dataCount++;
//        }
//
//        // ****************************************************************
//        // commit or rollback Last PO.
//        // ****************************************************************
//
//        if (dataCount == 0) {
//            // do nothing
//        } else if (!isError) {
//            commit();
//            // rollback();
//            this.normalRecCount++;
//        } else {
//            rollback();
//            updateSo(soNumCur, null);
//            commit();
//            this.totalErrCount++;
//        }
//
//        S21InfoLogOutput.println("Send PO to EDI Vendor Batch is normally end.");
//
//    }
    // END   2020/10/14 J.Evangelista [QC#57795,DEL]
    
    // START 2023/03/03 E.Watabe [QC#61128,ADD]
    /***********************************************************************************************************
     * Send PO for Hytec.
     ***********************************************************************************************************/
    private void SendPoForHytec() {

        // search PO
        List<Map<String, Object>> sendPoList = searchPo(true);

        int dataCount = 0;

        String poOrdNumPrev = "";
        String poOrdNumCur = "";

        boolean isError = false;

        String poSendTs = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);
        BigDecimal poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ);

        // ****************************************************************
        // search send PO info for NPAI0600
        // ****************************************************************
        for (int i = 0; i < sendPoList.size(); i++) {

            Map<String, Object> sendPo = sendPoList.get(i);

            poOrdNumCur = (String) sendPo.get(PO_ORD_NUM);

            // ****************************************************************
            // commit or rollback prev PO.
            // ****************************************************************
            if (ZYPCommonFunc.hasValue(poOrdNumPrev) && !poOrdNumCur.equals(poOrdNumPrev)) {

                if (!isError) {
                    commit();
                    // rollback();
                    this.normalRecCount++;
                } else {
                    rollback();
                    this.totalErrCount++;
                    if (!updatePo(poOrdNumPrev, null, null)) {
                        rollback();
                    } else {
                        commit();
                        // rollback();
                    }
                }

                isError = false;
                carrAcctNumFromXref = false;
                poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ);
            }

            if (!poOrdNumCur.equals(poOrdNumPrev)) {
                if (isError || !createNpai040001(sendPo)) {
                    isError = true;
                }

                if (isError || !createNpai040002(sendPo, poSendTs, poAckHdrPk)) {
                    isError = true;
                }

            }

            if (isError || !createNpai040003(sendPo, poSendTs, poAckHdrPk, true)) {
                isError = true;
            }

            poOrdNumPrev = poOrdNumCur;
            dataCount++;
        }

        // ****************************************************************
        // commit or rollback Last PO.
        // ****************************************************************
        if (dataCount == 0) {

            // do nothing

        } else if (!isError) {

            commit();
            // rollback();
            this.normalRecCount++;

        } else {

            rollback();
            this.totalErrCount++;

            if (!updatePo(poOrdNumCur, null, null)) {
                rollback();
            } else {
                commit();
                // rollback();
            }
        }

        S21InfoLogOutput.println("Send PO to EDI Vendor Batch is normally end.");

    }
    // END 2023/03/03 E.Watabe [QC#61128,ADD]

    // START 2023/09/25 G.Quan [QC#61608, ADD]
    /***********************************************************************************************************
     * Send PO for Atrix.
     ***********************************************************************************************************/
    private void SendPoForAtrix() {

        // search PO
        List<Map<String, Object>> sendPoList = searchPo(true);

        int dataCount = 0;

        String poOrdNumPrev = "";
        String poOrdNumCur = "";

        boolean isError = false;

        String poSendTs = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);
        BigDecimal poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ);

        // ****************************************************************
        // search send PO info for NPAI0800
        // ****************************************************************
        for (int i = 0; i < sendPoList.size(); i++) {

            Map<String, Object> sendPo = sendPoList.get(i);

            poOrdNumCur = (String) sendPo.get(PO_ORD_NUM);

            // ****************************************************************
            // commit or rollback prev PO.
            // ****************************************************************
            if (ZYPCommonFunc.hasValue(poOrdNumPrev) && !poOrdNumCur.equals(poOrdNumPrev)) {

                if (!isError) {
                    commit();
                    // rollback();
                    this.normalRecCount++;
                } else {
                    rollback();
                    this.totalErrCount++;
                    if (!updatePo(poOrdNumPrev, null, null)) {
                        rollback();
                    } else {
                        commit();
                        // rollback();
                    }
                }

                isError = false;
                carrAcctNumFromXref = false;
                poAckHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_HDR_SQ);
            }

            if (!poOrdNumCur.equals(poOrdNumPrev)) {
                if (isError || !createNpai040001(sendPo)) {
                    isError = true;
                }

                if (isError || !createNpai040002(sendPo, poSendTs, poAckHdrPk)) {
                    isError = true;
                }

            }

            if (isError || !createNpai040003(sendPo, poSendTs, poAckHdrPk, true)) {
                isError = true;
            }

            poOrdNumPrev = poOrdNumCur;
            dataCount++;
        }

        // ****************************************************************
        // commit or rollback Last PO.
        // ****************************************************************
        if (dataCount == 0) {

            // do nothing

        } else if (!isError) {

            commit();
            // rollback();
            this.normalRecCount++;

        } else {

            rollback();
            this.totalErrCount++;

            if (!updatePo(poOrdNumCur, null, null)) {
                rollback();
            } else {
                commit();
                // rollback();
            }
        }

        S21InfoLogOutput.println("Send PO to EDI Vendor Batch is normally end.");

    }
    // END 2023/09/25 G.Quan [QC#61608, ADD]

    /**
     * searchPo
     * @return
     */
    // START 2020/10/14 J.Evangelista [QC#57795,MOD]
    //private List<Map<String, Object>> searchPo() {
    private List<Map<String, Object>> searchPo(boolean reqSpclHdlg) {
    // END   2020/10/14 J.Evangelista [QC#57795,MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PO_MSG_TP_CD_03, PO_MSG_TP.SPECIAL_INSTRUCTIONS);
        ssmParam.put(BIND_PO_MSG_TP_CD_04, PO_MSG_TP.RECEIVER_NOTE);
        ssmParam.put(BIND_PO_MSG_TP_CD_05, PO_MSG_TP.SHIPPER_NOTE);

        List<String> poStsCdList = new ArrayList<String>();
        poStsCdList.add(PO_STS.VALIDATED);
        poStsCdList.add(PO_STS.PO_ERROR);
        ssmParam.put(BIND_PO_STS_CD_LIST, poStsCdList);

        List<String> vndSysTpCdList = new ArrayList<String>();
        vndSysTpCdList.add(VND_SYS_TP.TST_IMPRESO);
        vndSysTpCdList.add(VND_SYS_TP.DIETZGEN);
        // START 2023/03/03 E.Watabe [QC#61128,ADD]
        vndSysTpCdList.add(VND_SYS_TP.HYTEC);
        // END 2023/03/03 E.Watabe [QC#61128,ADD]
        // START 2023/09/25 G.Quan [QC#61608, ADD]
        vndSysTpCdList.add(VND_SYS_TP.ATRIX);
        // END 2023/09/25 G.Quan [QC#61608, ADD]
        ssmParam.put(BIND_VND_SYS_TP_CD_LIST, vndSysTpCdList);

        ssmParam.put(BIND_EDI_SEND_ID, this.interfaceId);
        // START 2020/10/14 J.Evangelista [QC#57795,ADD]
        ssmParam.put(BIND_REQ_SPCL_HDLG, reqSpclHdlg);
        ssmParam.put(BIND_SVC_LFTGT_DELY_FLG, ZYPConstant.FLG_OFF_N);
        ssmParam.put(BIND_SVC_INSD_DELY_FLG, ZYPConstant.FLG_OFF_N);
        ssmParam.put(BIND_SVC_STRT_TRUCK_FLG, ZYPConstant.FLG_OFF_N);
        // END   2020/10/14 J.Evangelista [QC#57795,ADD]

        return ssmBatchClient.queryObjectList("searchPo", ssmParam);
    }

    // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//    /**
//     * searchSo
//     * @return
//     */
//    private List<Map<String, Object>> searchSo(String trxHdrNum) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
//        ssmParam.put(BIND_SHPG_STS_CD, SHPG_STS.S_OR_O_PRINTED);
//        ssmParam.put(BIND_TRX_HDR_NUM, trxHdrNum);
//
//        List<String> sceOrdTpCdList = new ArrayList<String>();
//        sceOrdTpCdList.add(SCE_ORD_TP.DIRECT_SALES);
//        ssmParam.put(BIND_SCE_ORD_TP_CD_LIST, sceOrdTpCdList);
//
//        ssmParam.put(BIND_WMS_DROP_RQST_FLG, ZYPConstant.FLG_OFF_N);
//        ssmParam.put(BIND_SO_CUST_DATA_TP_CD, SO_CUST_DATA_TP.SHIP_TO);
//
//        List<String> vndSysTpCdList = new ArrayList<String>();
//        vndSysTpCdList.add(VND_SYS_TP.TST_IMPRESO);
//        vndSysTpCdList.add(VND_SYS_TP.DIETZGEN);
//        ssmParam.put(BIND_VND_SYS_TP_CD_LIST, vndSysTpCdList);
//
//        ssmParam.put(BIND_WMS_DROP_FLG, ZYPConstant.FLG_OFF_N);
//        ssmParam.put(BIND_EDI_SEND_ID, this.interfaceId);
//
//        List<String> soEdiSendStsCdList = new ArrayList<String>();
//        soEdiSendStsCdList.add(SO_EDI_SEND_STS.SO_PRINTED);
//        soEdiSendStsCdList.add(SO_EDI_SEND_STS.EDI_SEND_ERROR);
//        ssmParam.put(BIND_SO_EDI_SEND_STS_CD_LIST, soEdiSendStsCdList);
//
//        return ssmBatchClient.queryObjectList("searchSo", ssmParam);
//    }
//
//    /**
//     * validatedCombineSoExists
//     * @param trxHdrNum
//     * @param poSendTs
//     * @return
//     */
//    private boolean validatedCombineSoExists(String trxHdrNum, String poSendTs) {
//
//        if (!ZYPCommonFunc.hasValue(trxHdrNum)) {
//            return false;
//        }
//
//        // *********************************************************************************************************
//        // Combine PO / SO waiting time. Default 10 min.
//        // *********************************************************************************************************
//        String chkMinCmbnMinVal = ZYPCodeDataUtil.getVarCharConstValue("NPAB260001_PO_SO_CMBN_MIN", this.glblCmpyCd);
//
//        long chkMinCmbnMin = 0;
//
//        if (ZYPCommonFunc.hasValue(chkMinCmbnMinVal)) {
//            chkMinCmbnMin = Long.parseLong(chkMinCmbnMinVal);
//        } else {
//            chkMinCmbnMin = DEF_PO_SO_CMBN_MIN;
//        }
//
//        String chkDtTmTs = EZDCommonFunc.toyyyyMMddHHmmssSSS(EZDCommonFunc.toTimeMinute(ZYPDateUtil.getCurrentSystemTime(CUR_DT_TM_FMT)) - (chkMinCmbnMin * MIN_CALC_MULT_VAL));
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
//        ssmParam.put(BIND_TRX_HDR_NUM, trxHdrNum);
//        ssmParam.put(BIND_WAIT_START, chkDtTmTs);
//        ssmParam.put(BIND_WAIT_END, poSendTs);
//
//        Map<String, Object> validatedCombineSoExists = (Map<String, Object>) ssmBatchClient.queryObject("validatedCombineSoExists", ssmParam);
//
//        if (validatedCombineSoExists == null) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * validatedCombinePoExists
//     * @param trxHdrNum
//     * @param poSendTs
//     * @return
//     */
//    private boolean validatedCombinePoExists(String cpoOrdNum) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
//        ssmParam.put(BIND_PO_QLFY_CD, PO_QLFY.DROPSHIP);
//        ssmParam.put(BIND_PO_MSG_TP_CD_03, PO_MSG_TP.SPECIAL_INSTRUCTIONS);
//        ssmParam.put(BIND_PO_MSG_TP_CD_04, PO_MSG_TP.RECEIVER_NOTE);
//        ssmParam.put(BIND_PO_MSG_TP_CD_05, PO_MSG_TP.SHIPPER_NOTE);
//
//        List<String> poStsCdList = new ArrayList<String>();
//        poStsCdList.add(PO_STS.VALIDATED);
//        poStsCdList.add(PO_STS.PO_ERROR);
//        ssmParam.put(BIND_PO_STS_CD_LIST, poStsCdList);
//
//        List<String> vndSysTpCdList = new ArrayList<String>();
//        vndSysTpCdList.add(VND_SYS_TP.TST_IMPRESO);
//        vndSysTpCdList.add(VND_SYS_TP.DIETZGEN);
//        ssmParam.put(BIND_VND_SYS_TP_CD_LIST, vndSysTpCdList);
//
//        ssmParam.put(BIND_EDI_SEND_ID, this.interfaceId);
//
//        ssmParam.put(BIND_CPO_ORD_NUM, cpoOrdNum);
//
//        List<Map<String, Object>> validatedCombinePoExists = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("searchPo", ssmParam);
//
//        if (validatedCombinePoExists == null || validatedCombinePoExists.isEmpty()) {
//            return false;
//        }
//        return true;
//
//    }
//
//    /**
//     * @param soNum
//     * @return
//     */
//    private boolean updateSo(String soNum, String poSendTs) {
//
//        SHPG_ORDTMsg shpgOrdTmsg = new SHPG_ORDTMsg();
//
//        ZYPEZDItemValueSetter.setValue(shpgOrdTmsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(shpgOrdTmsg.soNum, soNum);
//        shpgOrdTmsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKey(shpgOrdTmsg);
//
//        if (ZYPCommonFunc.hasValue(poSendTs)) {
//            ZYPEZDItemValueSetter.setValue(shpgOrdTmsg.soEdiSendStsCd, SO_EDI_SEND_STS.EDI_SEND_COMPLETE);
//        } else {
//            ZYPEZDItemValueSetter.setValue(shpgOrdTmsg.soEdiSendStsCd, SO_EDI_SEND_STS.EDI_SEND_ERROR);
//        }
//
//        EZDTBLAccessor.update(shpgOrdTmsg);
//
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdTmsg.getReturnCode())) {
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {shpgOrdTmsg.getTableName() })));
//            sb.append(String.format("%-17s", nullToBlank(soNum)));
//
//            errorMsgList.add(sb.toString());
//
//            return false;
//        }
//
//        return true;
//
//    }
    // END   2020/10/14 J.Evangelista [QC#57795,DEL]

    /**
     * updatePo
     * @param sendPo
     * @param carrAcctNum
     * @param poSendTs
     * @return
     */
    private boolean updatePo(String poOrdNum, String carrAcctNum, String poSendTs) {

        POTMsg poTmsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(poTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poTmsg.poOrdNum, poOrdNum);
        poTmsg = (POTMsg) EZDTBLAccessor.findByKey(poTmsg);

        NPZC004001PMsg statusUpdateApiParam = new NPZC004001PMsg();
        NPZC004001 api = new NPZC004001();

        // success
        if (ZYPCommonFunc.hasValue(poSendTs)) {
            ZYPEZDItemValueSetter.setValue(poTmsg.poSendTs, poSendTs);
            ZYPEZDItemValueSetter.setValue(poTmsg.poSendFlg, ZYPConstant.FLG_ON_Y);
            // update carrAcctNum
            if (ZYPCommonFunc.hasValue(carrAcctNum)) {
                ZYPEZDItemValueSetter.setValue(poTmsg.carrAcctNum, carrAcctNum);
            }
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.poOrdNum, poOrdNum);
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.poStsCd, PO_STS.PO_CONFIRMED);

            ZYPEZDItemValueSetter.setValue(poTmsg.poSendTs, poSendTs);
            ZYPEZDItemValueSetter.setValue(poTmsg.poSendFlg, ZYPConstant.FLG_ON_Y);
        } else {
            // error
            ZYPEZDItemValueSetter.setValue(poTmsg.poSendFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.poOrdNum, (poOrdNum));
            ZYPEZDItemValueSetter.setValue(statusUpdateApiParam.poStsCd, PO_STS.PO_ERROR);
        }
        api.execute(statusUpdateApiParam, S21ApiInterface.ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(statusUpdateApiParam)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(statusUpdateApiParam);
            for (String xxMsgId : xxMsgIdList) {
                S21InfoLogOutput.println(xxMsgId);
                if (xxMsgId.endsWith("E")) {
                    return false;
                }
            }
        }

        EZDTBLAccessor.update(poTmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poTmsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {poTmsg.getTableName() })));
            sb.append(String.format("%-17s", nullToBlank(poOrdNum)));

            errorMsgList.add(sb.toString());

            return false;
        }

        return true;
    }

    /**
     * updatePoDtl
     * @param sendPo
     * @return
     */
    private boolean updatePoDtl(Map<String, Object> sendPo, String poSendTs) {

        PO_DTLTMsg poDtlTmsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdNum, (String) sendPo.get(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdDtlLineNum, (String) sendPo.get(PO_ORD_DTL_LINE_NUM));
        poDtlTmsg = (PO_DTLTMsg) EZDTBLAccessor.findByKey(poDtlTmsg);

        ZYPEZDItemValueSetter.setValue(poDtlTmsg.vndPoAckStsCd, VND_PO_ACK_STS_CD_IW);
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poSendTs, poSendTs);

        if (carrAcctNumFromXref) {
            // Freight Terms
            ZYPEZDItemValueSetter.setValue(poDtlTmsg.frtCondCd, FRT_COND.COLLECT);

            // Carrier
            ZYPEZDItemValueSetter.setValue(poDtlTmsg.carrCd, carrCdfromXref);
        }

        // SSL
        if (!ZYPCommonFunc.hasValue(poDtlTmsg.shpgSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(poDtlTmsg.shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
        }

        EZDTBLAccessor.update(poDtlTmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poDtlTmsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {poDtlTmsg.getTableName() })));
            sb.append(String.format("%-17s", nullToBlank((String) sendPo.get(PO_ORD_NUM))));

            errorMsgList.add(sb.toString());

            return false;
        }

        return true;

    }

    /**
     * callCreatePoHistoryApi
     * @param sendPo
     * @return
     */
    private boolean callCreatePoHistoryApi(Map<String, Object> sendPo) {

        String glblCmpyCd = this.glblCmpyCd;
        String poOrdNum = (String) sendPo.get(PO_ORD_NUM);
        String poOrdDtlLineNum = (String) sendPo.get(PO_ORD_DTL_LINE_NUM);
        String eventId = EVENT_ID_SEND_PO;

        // RTRN="1"
        if (NPXC001001CreatePOHistory.createPOHistory(glblCmpyCd, eventId, poOrdNum, poOrdDtlLineNum) == 1) {

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {"Create PO History" })));
            sb.append(String.format("%-17s", nullToBlank((String) sendPo.get(PO_ORD_NUM))));

            errorMsgList.add(sb.toString());

            return false;
        }

        return true;

    }

    /**
     * createCombineXref
     * @param sendPo
     * @param poSendTs
     * @return
     */
    private boolean createCombineXref(Map<String, Object> sendPo, String poSendTs) {

        BigDecimal combineXrefPk = ZYPOracleSeqAccessor.getNumberBigDecimal(CMBN_PO_SO_XREF_SQ);

        CMBN_PO_SO_XREFTMsg combineXrefTmsg = new CMBN_PO_SO_XREFTMsg();

        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.cmbnPoSoXrefPk, combineXrefPk);

        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.poOrdNum, (String) sendPo.get(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.poOrdDtlLineNum, (String) sendPo.get(PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.itrlIntfcId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.poSoSendTs, poSendTs);

        EZDTBLAccessor.insert(combineXrefTmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(combineXrefTmsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {combineXrefTmsg.getTableName() })));
            sb.append(String.format("%-17s", nullToBlank((String) sendPo.get(PO_ORD_NUM))));

            errorMsgList.add(sb.toString());

            return false;
        }

        return true;

    }

    // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//    /**
//     * createCombineXrefforSo
//     * @param sendSo
//     * @param poOrdNum
//     * @param lineNum
//     * @param poSendTs
//     * @return
//     */
//    private boolean createCombineXrefForSo(Map<String, Object> sendSo, String poOrdNum, String lineNum, String sendTs) {
//
//        BigDecimal combineXrefPk = ZYPOracleSeqAccessor.getNumberBigDecimal(CMBN_PO_SO_XREF_SQ);
//
//        CMBN_PO_SO_XREFTMsg combineXrefTmsg = new CMBN_PO_SO_XREFTMsg();
//
//        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.cmbnPoSoXrefPk, combineXrefPk);
//        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.poOrdNum, poOrdNum);
//        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.poOrdDtlLineNum, lineNum);
//        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.itrlIntfcId, this.interfaceId);
//        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.poSoSendTs, sendTs);
//
//        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.soNum, (String) sendSo.get(SO_NUM));
//        ZYPEZDItemValueSetter.setValue(combineXrefTmsg.soSlpNum, (String) sendSo.get(SO_SLP_NUM));
//
//        EZDTBLAccessor.insert(combineXrefTmsg);
//
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(combineXrefTmsg.getReturnCode())) {
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {combineXrefTmsg.getTableName() })));
//            sb.append(String.format("%-17s", (String) sendSo.get(SO_NUM)));
//
//            errorMsgList.add(sb.toString());
//
//            return false;
//        }
//
//        return true;
//
//    }
    // END   2020/10/14 J.Evangelista [QC#57795,DEL]

    /**
     * createAckHdr
     * @param sendPo
     * @param poSendTs
     * @param poAckHdrPk
     * @return
     */
    private boolean createAckHdr(Map<String, Object> sendPo, String poSendTs, BigDecimal poAckHdrPk) {

        PO_ACK_HDRTMsg poAckHdrTmsg = new PO_ACK_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poAckHdrPk, poAckHdrPk);
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poSendTs, poSendTs);
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poOrdNum, (String) sendPo.get(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.itrlIntfcId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.vndCd, (String) sendPo.get(VND_CD));
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poAckUpdProcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poAckHdrLtstFlg, ZYPConstant.FLG_ON_Y);

        Map<String, Object> ssmParampoAckNum = new HashMap<String, Object>();
        ssmParampoAckNum.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParampoAckNum.put(BIND_PO_ORD_NUM, (String) sendPo.get(PO_ORD_NUM));
        String poAckNum = (String) ssmBatchClient.queryObject("getPoAckNumberInfo", ssmParampoAckNum);
        ZYPEZDItemValueSetter.setValue(poAckHdrTmsg.poAckNum, poAckNum);

        EZDTBLAccessor.insert(poAckHdrTmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poAckHdrTmsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {poAckHdrTmsg.getTableName() })));
            sb.append(String.format("%-17s", nullToBlank((String) sendPo.get(PO_ORD_NUM))));

            errorMsgList.add(sb.toString());

            return false;
        }

        return true;

    }

    /**
     * createAckDtl
     * @param sendPo
     * @param poAckHdrPk
     * @return
     */
    private boolean createAckDtl(Map<String, Object> sendPo, BigDecimal poAckHdrPk) {

        BigDecimal poAckDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(PO_ACK_DTL_SQ);

        PO_ACK_DTLTMsg poAckDtlTmsg = new PO_ACK_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poAckDtlPk, poAckDtlPk);
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poOrdNum, (String) sendPo.get(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poOrdDtlLineNum, (String) sendPo.get(PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poAckLineStsCd, VND_PO_ACK_STS_CD_H);
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndPoAckLineStsTxt, VND_PO_ACK_LINS_STS_TXT);
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndMdseCd, (String) sendPo.get(ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.mdseCd, (String) sendPo.get(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.mdseNm, (String) sendPo.get(MDSE_NM));
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.trdPtnrSkuCd, (String) sendPo.get(ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.ordQty, (BigDecimal) sendPo.get(PO_QTY));
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poAckHdrPk, poAckHdrPk);
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.vndPoAckStsCd, VND_PO_ACK_STS_CD_IW);
        ZYPEZDItemValueSetter.setValue(poAckDtlTmsg.poAckDtlLtstFlg, ZYPConstant.FLG_ON_Y);

        EZDTBLAccessor.insert(poAckDtlTmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poAckDtlTmsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {poAckDtlTmsg.getTableName() })));
            sb.append(String.format("%-17s", nullToBlank((String) sendPo.get(PO_ORD_NUM))));

            errorMsgList.add(sb.toString());

            return false;
        }

        return true;

    }

    /**
     * getTrdPtnrShpgXRef
     * @param sendPo
     * @return
     */
    private Map<String, Object> getTrdPtnrShpgXRef(Map<String, Object> map) {

        Map<String, Object> frtCond = new HashMap<String, Object>();

        if (ZYPCommonFunc.hasValue((String) map.get("FRT_COND_CD"))) {

            // search for TPL_FRT_COND_XREF
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("S21frtCondCd", (String) map.get("FRT_COND_CD"));

            frtCond = (Map<String, Object>) ssmBatchClient.queryObject("getTplFrtCond", ssmParam);

            if (frtCond != null && TPL_FRT_COND_CD_S05.equals((String) frtCond.get("FRT_COND_CD"))) {

                return frtCond;

            } else {

                return searchtrdPtnrShpgXRef(map);

            }

        } else {

            return searchtrdPtnrShpgXRef(map);
        }

    }

    /**
     * searchtrdPtnrShpgXRef
     * @param sendPo
     * @return
     */
    private Map<String, Object> searchtrdPtnrShpgXRef(Map<String, Object> map) {

        Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();

        String vndCd = (String) map.get("VND_CD");
        String carrCd = (String) map.get("CARR_CD");
        String shpgSvcLvlCd = (String) map.get("SHPG_SVC_LVL_CD");
        String frtCondCd = (String) map.get("FRT_COND_CD");

        if (vndCd == null) {

            return trdPtnrShpgXRefMap;

        }

        if (ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 1
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, shpgSvcLvlCd, frtCondCd);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 2
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, frtCondCd);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 3
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 2
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, frtCondCd);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 3
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }
        }

        return trdPtnrShpgXRefMap;

    }

    /**
     * searchTrdPtnrShpgXRefInfo
     * @param glblCmpyCd
     * @param vndCd
     * @param carrCd
     * @param shpgSvcLvlCd
     * @param frtCondCd
     * @return
     */
    private Map<String, Object> searchTrdPtnrShpgXRefInfo(String glblCmpyCd, String vndCd, String carrCd, String shpgSvcLvlCd, String frtCondCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("vndCd", vndCd);
        ssmParam.put("carrCd", carrCd);
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        ssmParam.put("frtCondCd", frtCondCd);

        trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);

        return trdPtnrShpgXRefMap;

    }

    /**
     * createNpai040001
     * @param sendPo
     * @return
     */
    private boolean createNpai040001(Map<String, Object> sendPo) {

        NPAI0400_01TMsg npai040001Tmsg = new NPAI0400_01TMsg();

        seqNumber = BigDecimal.ONE;
        unitId = unitId.add(BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.transactionId, this.transactionId);
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.otbdPoRecId, OTBD_PO_REC_ID_H1);

        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.custAcctNum, (String) sendPo.get(VND_CD));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.ediTrxPrpsCd, (String) sendPo.get(PO_QLFY_CD));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.poTpCd, (String) sendPo.get(DS_PO_TP_CD));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.custEdiPoNum, (String) sendPo.get(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.custIssPoDt, (String) sendPo.get(PO_APVL_DT));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToCustCd, (String) sendPo.get(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.shipToLocNm, (String) sendPo.get(SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToTelNum, (String) sendPo.get(WRK_TEL_NUM));

        Map<String, Object> trdPtnrShpgXRef = getTrdPtnrShpgXRef(sendPo);

        if (trdPtnrShpgXRef != null) {
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.ansiShipMethCd, (String) trdPtnrShpgXRef.get(TRD_PTNR_FRT_CHRG_METH_CD));
            // START 2019/01/15 S.Ohki [QC#29892,MOD]
//            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.carrCd, (String) trdPtnrShpgXRef.get(TRD_PTNR_CARR_CD));
            String xrefCarrCd = (String) trdPtnrShpgXRef.get(TRD_PTNR_CARR_CD);
            String poCarrCd = (String) sendPo.get(CARR_CD);
            if (ZYPCommonFunc.hasValue(poCarrCd) && !ZYPCommonFunc.hasValue(xrefCarrCd)) {
                ZYPEZDItemValueSetter.setValue(npai040001Tmsg.carrCd, poCarrCd);
            } else {
                ZYPEZDItemValueSetter.setValue(npai040001Tmsg.carrCd, xrefCarrCd);
            }
            // END   2019/01/15 S.Ohki [QC#29892,MOD]
        }

        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.rqstDelyDt, (String) sendPo.get(RQST_RCV_DT));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.idocRefNum, (String) sendPo.get(PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToFirstRefCmntTxt, (String) sendPo.get(CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.ccyCd, (String) sendPo.get(CCY_CD));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sendId, (String) sendPo.get(VND_CD));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.carrNm, (String) sendPo.get(CARR_NM));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.trxHdrNum, (String) sendPo.get(CPO_ORD_NUM));

        // sell to
        if (LINE_BIZ_TP.ESS.equals((String) sendPo.get(PRCH_GRP_CD))) {

            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToLocNm, sellToLocNmEss);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToAddlLocNm, sellToAddLocNmEss);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToFirstLineAddr, sellToFirstLineAddrEss);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToScdLineAddr, sellToScdLineAddrEss);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToCtyAddr, sellToCtyAddrEss);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToStCd, sellToStCdEss);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToPostCd, sellToPostCdEss);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToProvNm, sellToProvNmEss);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToCtryCd, sellToCtryCdEss);

        } else if (LINE_BIZ_TP.LFS.equals((String) sendPo.get(PRCH_GRP_CD)) || (LINE_BIZ_TP.PPS.equals((String) sendPo.get(PRCH_GRP_CD)))) {
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToLocNm, sellToLocNmLfsPps);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToAddlLocNm, sellToAddLocNmLfsPps);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToFirstLineAddr, sellToFirstLineAddrLfsPps);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToScdLineAddr, sellToScdLineAddrLfsPps);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToCtyAddr, sellToCtyAddrLfsPps);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToStCd, sellToStCdLfsPps);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToPostCd, sellToPostCdLfsPps);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToProvNm, sellToProvNmLfsPps);
            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToCtryCd, sellToCtryCdLfsPps);
        }

        // START 2020/10/14 J.Evangelista [QC#57795,ADD]
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.svcLftgtDelyFlg, (String) sendPo.get(SVC_LFTGT_DELY_FLG));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.svcInsdDelyFlg, (String) sendPo.get(SVC_INSD_DELY_FLG));
        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.svcStrtTruckFlg, (String) sendPo.get(SVC_STRT_TRUCK_FLG));
        // END   2020/10/14 J.Evangelista [QC#57795,ADD]
        
        EZDTBLAccessor.insert(npai040001Tmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(npai040001Tmsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {npai040001Tmsg.getTableName() })));
            sb.append(String.format("%-17s", nullToBlank((String) sendPo.get(PO_ORD_NUM))));

            errorMsgList.add(sb.toString());

            return false;
        }

        return true;

    }

    // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//    /**
//     * createNpai040001ForSoOnly
//     * @param sendSo
//     * @return
//     */
//    private boolean createNpai040001ForSoOnly(Map<String, Object> sendSo) {
//
//        NPAI0400_01TMsg npai040001Tmsg = new NPAI0400_01TMsg();
//
//        seqNumber = BigDecimal.ONE;
//        unitId = unitId.add(BigDecimal.ONE);
//
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.interfaceId, this.interfaceId);
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.transactionId, this.transactionId);
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.segmentId, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.unitId, unitId);
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.seqNumber, seqNumber);
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.otbdPoRecId, OTBD_PO_REC_ID_H1);
//
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.custAcctNum, (String) sendSo.get(VND_CD));
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.ediTrxPrpsCd, PO_QLFY.DROPSHIP);
//
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.poTpCd, DS_PO_TP_SO);
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.custEdiPoNum, (String) sendSo.get(SO_NUM));
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.custIssPoDt, ZYPDateUtil.DateFormatter((String) sendSo.get(SO_CRAT_TS), "yyyyMMddHHmmss", "yyyyMMdd"));
//
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToCustCd, (String) sendSo.get(SELL_TO_CUST_CD));
//
//        // edit ship to cust num.(shipToLocNm)
//        String shipToLocNm = (String) sendSo.get(SHIP_TO_LOC_NM);
//        if (shipToLocNm.length() > npai040001Tmsg.getAttr("shipToLocNm").getDigit()) {
//            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.shipToLocNm, shipToLocNm.substring(0, npai040001Tmsg.getAttr("shipToLocNm").getDigit()));
//        } else {
//            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.shipToLocNm, shipToLocNm);
//        }
//
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToTelNum, (String) sendSo.get(WRK_TEL_NUM));
//
//        Map<String, Object> trdPtnrShpgXRef = getTrdPtnrShpgXRef(sendSo);
//
//        if (trdPtnrShpgXRef != null) {
//            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.ansiShipMethCd, (String) trdPtnrShpgXRef.get(TRD_PTNR_FRT_CHRG_METH_CD));
//            // START 2019/01/15 S.Ohki [QC#29892,MOD]
////            ZYPEZDItemValueSetter.setValue(npai040001Tmsg.carrCd, (String) trdPtnrShpgXRef.get(TRD_PTNR_CARR_CD));
//            String xrefCarrCd = (String) trdPtnrShpgXRef.get(TRD_PTNR_CARR_CD);
//            String soCarrCd = (String) sendSo.get(CARR_CD);
//            if (ZYPCommonFunc.hasValue(soCarrCd) && !ZYPCommonFunc.hasValue(xrefCarrCd)) {
//                ZYPEZDItemValueSetter.setValue(npai040001Tmsg.carrCd, soCarrCd);
//            } else {
//                ZYPEZDItemValueSetter.setValue(npai040001Tmsg.carrCd, xrefCarrCd);
//            }
//            // END   2019/01/15 S.Ohki [QC#29892,MOD]
//        }
//
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.rqstDelyDt, (String) sendSo.get(RDD_DT));
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.idocRefNum, (String) sendSo.get(SO_NUM));
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sellToFirstRefCmntTxt, (String) sendSo.get(CUST_ISS_PO_NUM));
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.ccyCd, (String) sendSo.get(CCY_CD));
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.sendId, (String) sendSo.get(VND_CD));
//
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.carrNm, (String) sendSo.get(CARR_NM));
//        ZYPEZDItemValueSetter.setValue(npai040001Tmsg.trxHdrNum, (String) sendSo.get(CPO_ORD_NUM));
//
//        EZDTBLAccessor.insert(npai040001Tmsg);
//
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(npai040001Tmsg.getReturnCode())) {
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {npai040001Tmsg.getTableName() })));
//            sb.append(String.format("%-17s", nullToBlank((String) sendSo.get(SO_NUM))));
//
//            errorMsgList.add(sb.toString());
//
//            return false;
//        }
//
//        return true;
//
//    }
    // END   2020/10/14 J.Evangelista [QC#57795,DEL]

    /**
     * createNpai040002
     * @param sendPo
     * @return
     */
    private boolean createNpai040002(Map<String, Object> sendPo, String poSendTs, BigDecimal poAckHdrPk) {

        NPAI0400_02TMsg npai040002Tmsg = new NPAI0400_02TMsg();

        seqNumber = seqNumber.add(BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.transactionId, this.transactionId);
        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.otbdPoRecId, OTBD_PO_REC_ID_M1);

        // **************************************************************
        // get default carrier account.
        // **************************************************************
        String carrAcctNum = null;

        if (sendPo.get(CARR_ACCT_NUM) == null) {

            carrAcctNum = getCarrAcctNum(sendPo);

            if (ZYPCommonFunc.hasValue(carrAcctNum)) {
                carrAcctNumFromXref = true;
            }

        } else {

            carrAcctNum = (String) sendPo.get(CARR_ACCT_NUM);

        }

        boolean needToCutMsg = true;

        // **************************************************************
        // edit message.
        // **************************************************************
        String msg = null;

        if (ZYPCommonFunc.hasValue(carrAcctNum)) {
            msg = carrAcctNum;
        }

        String[] poMsgTpArray = new String[] {PO_MSG_TP.RECEIVER_NOTE, PO_MSG_TP.SHIPPER_NOTE, PO_MSG_TP.SPECIAL_INSTRUCTIONS };

        StringBuilder poSb = new StringBuilder();

        for (int i = 0; i < poMsgTpArray.length; i++) {

            List<PO_MSGTMsg> poMsgList = NPXC001001PoMsg.getPoMsg(this.glblCmpyCd, poMsgTpArray[i], (String) sendPo.get(PO_ORD_NUM), null);

            if (poMsgList != null && poMsgList.size() > 0) {

                String poMsg = NPXC001001PoMsg.concatenatePoMsg(poMsgList);

                poSb = poSb.append(poMsg);
                poSb = poSb.append(" ");
            }

        }

        String concatStr = poSb.toString();

        // START 2018/12/19 M.Naito [QC#29698,MOD]
        if (ZYPCommonFunc.hasValue(msg)) {
            msg = msg.concat(concatStr);
        } else {
            msg = concatStr;
        }

        if (ZYPCommonFunc.hasValue(msg)) {
            // QC#30571
            msg = ZYPCommonFunc.replaceAll(msg, "\r\n", " ").trim();
            msg = ZYPCommonFunc.replaceAll(msg, "\r", " ").trim();
            msg = ZYPCommonFunc.replaceAll(msg, "\n", " ").trim();
        }
        // END 2018/12/19 M.Naito [QC#29698,MOD]

        if (ZYPCommonFunc.hasValue(msg) && msg.length() > npai040002Tmsg.getAttr("msgTxtInfoTxt").getDigit()) {
            ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt, msg.substring(0, npai040002Tmsg.getAttr("msgTxtInfoTxt").getDigit()));
            msg = msg.substring(npai040002Tmsg.getAttr("msgTxtInfoTxt").getDigit(), msg.length());
        } else {
            ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt, msg);
            needToCutMsg = false;
        }

        if (needToCutMsg) {
            if (msg.length() > npai040002Tmsg.getAttr("msgTxtInfoTxt_02").getDigit()) {
                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_02, msg.substring(0, npai040002Tmsg.getAttr("msgTxtInfoTxt_02").getDigit()));
                msg = msg.substring(npai040002Tmsg.getAttr("msgTxtInfoTxt_02").getDigit(), msg.length());
            } else {
                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_02, msg);
                needToCutMsg = false;
            }
        }

        if (needToCutMsg) {
            if (needToCutMsg && msg.length() > npai040002Tmsg.getAttr("msgTxtInfoTxt_03").getDigit()) {
                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_03, msg.substring(0, npai040002Tmsg.getAttr("msgTxtInfoTxt_03").getDigit()));
                msg = msg.substring(npai040002Tmsg.getAttr("msgTxtInfoTxt_03").getDigit(), msg.length());
            } else {
                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_03, msg);
                needToCutMsg = false;
            }
        }

        if (needToCutMsg) {
            if (needToCutMsg && msg.length() > npai040002Tmsg.getAttr("msgTxtInfoTxt_04").getDigit()) {
                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_04, msg.substring(0, npai040002Tmsg.getAttr("msgTxtInfoTxt_04").getDigit()));
                msg = msg.substring(npai040002Tmsg.getAttr("msgTxtInfoTxt_04").getDigit(), msg.length());
            } else {
                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_04, msg);
            }
        }

        EZDTBLAccessor.insert(npai040002Tmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(npai040002Tmsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {npai040002Tmsg.getTableName() })));
            sb.append(String.format("%-17s", nullToBlank((String) sendPo.get(PO_ORD_NUM))));

            errorMsgList.add(sb.toString());

            return false;
        }

        // **************************************************************
        // update PO
        // **************************************************************
        if (!updatePo((String) sendPo.get(PO_ORD_NUM), carrAcctNum, poSendTs)) {
            return false;
        }

        // **************************************************************
        // create ACK HDR
        // **************************************************************
        if (!createAckHdr(sendPo, poSendTs, poAckHdrPk)) {
            return false;
        }

        return true;
    }

    // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//    /**
//     * createNpai040002ForSoOnly
//     * @param sendSo
//     * @return
//     */
//    private boolean createNpai040002ForSoOnly(Map<String, Object> sendSo, String soSendTs) {
//
//        // **************************************************************
//        // get default carrier account.
//        // **************************************************************
//        String carrAcctNum = null;
//
//        if (sendSo.get(CARR_ACCT_NUM) == null) {
//
//            carrAcctNum = getCarrAcctNum(sendSo);
//            if (ZYPCommonFunc.hasValue(carrAcctNum)) {
//                carrAcctNumFromXref = true;
//            }
//
//        } else {
//
//            carrAcctNum = (String) sendSo.get(CARR_ACCT_NUM);
//
//        }
//
//        NPAI0400_02TMsg npai040002Tmsg = new NPAI0400_02TMsg();
//
//        seqNumber = seqNumber.add(BigDecimal.ONE);
//
//        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.interfaceId, this.interfaceId);
//        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.transactionId, this.transactionId);
//        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.segmentId, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.unitId, unitId);
//        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.seqNumber, seqNumber);
//        ZYPEZDItemValueSetter.setValue(npai040002Tmsg.otbdPoRecId, OTBD_PO_REC_ID_M1);
//
//        // **************************************************************
//        // get SO Message
//        // **************************************************************
//
//        boolean needToCutMsg = true;
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
//        ssmParam.put(BIND_SO_NUM, (String) sendSo.get(SO_NUM));
//
//        List<Map<String, Object>> soMsgList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSoMsg", ssmParam);
//
//        String soMsg = "";
//
//        if (ZYPCommonFunc.hasValue(carrAcctNum)) {
//            soMsg = soMsg + carrAcctNum;
//        }
//
//        for (int i = 0; i < soMsgList.size(); i++) {
//            Map<String, Object> soMsgMap = soMsgList.get(i);
//            soMsg = soMsg + (String) soMsgMap.get(SO_MSG_DESC_TXT);
//        }
//
//        // QC#30571
//        if (ZYPCommonFunc.hasValue(soMsg)) {
//            soMsg = ZYPCommonFunc.replaceAll(soMsg, "\r\n", " ").trim();
//            soMsg = ZYPCommonFunc.replaceAll(soMsg, "\r", " ").trim();
//            soMsg = ZYPCommonFunc.replaceAll(soMsg, "\n", " ").trim();
//        }
//
//        if (ZYPCommonFunc.hasValue(soMsg) && soMsg.length() > npai040002Tmsg.getAttr("msgTxtInfoTxt").getDigit()) {
//            ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt, soMsg.substring(0, npai040002Tmsg.getAttr("msgTxtInfoTxt").getDigit()));
//            soMsg = soMsg.substring(npai040002Tmsg.getAttr("msgTxtInfoTxt").getDigit(), soMsg.length());
//        } else {
//            ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt, soMsg);
//            needToCutMsg = false;
//        }
//
//        if (needToCutMsg) {
//            if (needToCutMsg && soMsg.length() > npai040002Tmsg.getAttr("msgTxtInfoTxt_02").getDigit()) {
//                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_02, soMsg.substring(0, npai040002Tmsg.getAttr("msgTxtInfoTxt_02").getDigit()));
//                soMsg = soMsg.substring(npai040002Tmsg.getAttr("msgTxtInfoTxt_02").getDigit(), soMsg.length());
//            } else {
//                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_02, soMsg);
//                needToCutMsg = false;
//            }
//        }
//
//        if (needToCutMsg) {
//            if (needToCutMsg && soMsg.length() > npai040002Tmsg.getAttr("msgTxtInfoTxt_03").getDigit()) {
//                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_03, soMsg.substring(0, npai040002Tmsg.getAttr("msgTxtInfoTxt_03").getDigit()));
//                soMsg = soMsg.substring(npai040002Tmsg.getAttr("msgTxtInfoTxt_03").getDigit(), soMsg.length());
//            } else {
//                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_03, soMsg);
//                needToCutMsg = false;
//            }
//        }
//
//        if (needToCutMsg) {
//            if (needToCutMsg && soMsg.length() > npai040002Tmsg.getAttr("msgTxtInfoTxt_04").getDigit()) {
//                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_04, soMsg.substring(0, npai040002Tmsg.getAttr("msgTxtInfoTxt_04").getDigit()));
//                soMsg = soMsg.substring(npai040002Tmsg.getAttr("msgTxtInfoTxt_04").getDigit(), soMsg.length());
//            } else {
//                ZYPEZDItemValueSetter.setValue(npai040002Tmsg.msgTxtInfoTxt_04, soMsg);
//            }
//        }
//        EZDTBLAccessor.insert(npai040002Tmsg);
//
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(npai040002Tmsg.getReturnCode())) {
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {npai040002Tmsg.getTableName() })));
//            sb.append(String.format("%-17s", nullToBlank((String) sendSo.get(SO_NUM))));
//
//            errorMsgList.add(sb.toString());
//
//            return false;
//        }
//
//        // **************************************************************
//        // update SO
//        // **************************************************************
//        if (!updateSo((String) sendSo.get(SO_NUM), soSendTs)) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    /**
//     * createNpai040003ForSoOnly
//     * @param sendSo
//     * @param poSendTs
//     * @return
//     */
//    private boolean createNpai040003ForSoOnly(Map<String, Object> sendSo, String poSendTs) {
//
//        NPAI0400_03TMsg npai040003Tmsg = new NPAI0400_03TMsg();
//
//        seqNumber = seqNumber.add(BigDecimal.ONE);
//
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.interfaceId, this.interfaceId);
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.transactionId, this.transactionId);
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.segmentId, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.unitId, unitId);
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.seqNumber, seqNumber);
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.otbdPoRecId, OTBD_PO_REC_ID_L1);
//
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.ediPoLineNum, (String) sendSo.get(SO_SLP_NUM));
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.trdPtnrSkuCd, (String) sendSo.get(ASL_MDSE_CD));
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.cusaMdseCd, (String) sendSo.get(MDSE_CD));
//
//        String mdseNm = (String) sendSo.get(MDSE_DESC_SHORT_TXT);
//        if (ZYPCommonFunc.hasValue(mdseNm) && mdseNm.length() > npai040003Tmsg.getAttr("mdseNm").getDigit()) {
//            mdseNm = mdseNm.substring(0, npai040003Tmsg.getAttr("mdseNm").getDigit());
//        }
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.mdseNm, mdseNm);
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.ordQty, (BigDecimal) sendSo.get(RQST_ORD_QTY));
//
//        // **************************************************************
//        // UMO from XREF
//        // **************************************************************
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.uomCd, getUomCd(sendSo));
//
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.unitPrcAmt, (BigDecimal) sendSo.get(SP_DEAL_UNIT_PRC_AMT));
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToCustCd, (String) sendSo.get(SHIP_TO_CUST_CD));
//
//        // **************************************************************
//        // edit LOC_NM & ADDL_LOC_NM
//        // **************************************************************
//        String locNum = (String) sendSo.get(SO_CUST_LINE_LOC_NM);
//        if (ZYPCommonFunc.hasValue(locNum) && locNum.length() > npai040003Tmsg.getAttr("shipToLocNm").getDigit()) {
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToLocNm, locNum.substring(0, npai040003Tmsg.getAttr("shipToLocNm").getDigit()));
//            locNum = locNum.substring(npai040003Tmsg.getAttr("shipToLocNm").getDigit(), locNum.length());
//            if (ZYPCommonFunc.hasValue(locNum) && locNum.length() > npai040003Tmsg.getAttr("shipToAddlLocNm").getDigit()) {
//                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToAddlLocNm, locNum.substring(0, npai040003Tmsg.getAttr("shipToAddlLocNm").getDigit()));
//            } else {
//                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToAddlLocNm, locNum);
//            }
//        } else {
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToLocNm, locNum);
//        }
//
//        // **************************************************************
//        // edit address
//        // **************************************************************
//        String address = (String) sendSo.get(SO_CUST_LINE_ADDR);
//        // QC#30571
//        if (ZYPCommonFunc.hasValue(address)) {
//            address = ZYPCommonFunc.replaceAll(address, "\r\n", " ").trim();
//            address = ZYPCommonFunc.replaceAll(address, "\r", " ").trim();
//            address = ZYPCommonFunc.replaceAll(address, "\n", " ").trim();
//        }
//        if (ZYPCommonFunc.hasValue(address) && address.length() > npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit()) {
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToFirstLineAddr, address.substring(0, npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit()));
//            address = address.substring(npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit(), address.length());
//            if (ZYPCommonFunc.hasValue(address) && address.length() > npai040003Tmsg.getAttr("shipToScdLineAddr").getDigit()) {
//                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToScdLineAddr, address.substring(0, npai040003Tmsg.getAttr("shipToScdLineAddr").getDigit()));
//            } else {
//                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToScdLineAddr, address);
//            }
//        } else {
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToFirstLineAddr, address);
//        }
//
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToCustCtyAddr, (String) sendSo.get(SHIP_TO_CTY_ADDR));
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToStCd, (String) sendSo.get(SHIP_TO_ST_CD));
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToPostCd, (String) sendSo.get(SHIP_TO_POST_CD));
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToCtryCd, (String) sendSo.get(SHIP_TO_CTRY_CD));
//        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToTelNum, (String) sendSo.get(CTAC_PTNR_PSN_TEL_NUM));
//
//        EZDTBLAccessor.insert(npai040003Tmsg);
//
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(npai040003Tmsg.getReturnCode())) {
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {npai040003Tmsg.getTableName() })));
//            sb.append(String.format("%-17s", nullToBlank((String) sendSo.get(PO_ORD_NUM))));
//
//            errorMsgList.add(sb.toString());
//
//            return false;
//        }
//
//        // **************************************************************
//        // create combine XREF
//        // **************************************************************
//        if (!createCombineXrefForSo(sendSo, null, null, poSendTs)) {
//            return false;
//        }
//
//        return true;
//
//    }
    // END   2020/10/14 J.Evangelista [QC#57795,DEL]

    /**
     * createNpai040003
     * @param sendPo
     * @param poSendTs
     * @param poAckHdrPk
     * @param reqSpclHdlg
     * @return
     */
    // START 2022/03/17 R.Azucena[QC#59752, MOD]
    // private boolean createNpai040003(Map<String, Object> sendPo, String poSendTs, BigDecimal poAckHdrPk) {
    private boolean createNpai040003(Map<String, Object> sendPo, String poSendTs, BigDecimal poAckHdrPk, boolean reqSpclHdlg) {
    // END 2022/03/17 R.Azucena[QC#59752, MOD]

        NPAI0400_03TMsg npai040003Tmsg = new NPAI0400_03TMsg();

        seqNumber = seqNumber.add(BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.interfaceId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.transactionId, this.transactionId);
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.otbdPoRecId, OTBD_PO_REC_ID_L1);

        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.ediPoLineNum, (String) sendPo.get(PO_ORD_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.trdPtnrSkuCd, (String) sendPo.get(ASL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.cusaMdseCd, (String) sendPo.get(MDSE_CD));

        String mdseNm = (String) sendPo.get(MDSE_DESC_SHORT_TXT);
        if (ZYPCommonFunc.hasValue(mdseNm) && mdseNm.length() > npai040003Tmsg.getAttr("mdseNm").getDigit()) {
            mdseNm = mdseNm.substring(0, npai040003Tmsg.getAttr("mdseNm").getDigit());
        }

        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.mdseNm, mdseNm);

        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.ordQty, (BigDecimal) sendPo.get(PO_DISP_QTY));

        // **************************************************************
        // UMO from XREF
        // **************************************************************
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.uomCd, getUomCd(sendPo));

        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.unitPrcAmt, (BigDecimal) sendPo.get(ENT_DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToCustCd, (String) sendPo.get(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToLocNm, (String) sendPo.get(SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToAddlLocNm, (String) sendPo.get(SHIP_TO_ADDL_LOC_NM));

        // **************************************************************
        // edit address
        // **************************************************************
        String address = (String) sendPo.get(SHIP_TO_LINE_ADDR);
        if (ZYPCommonFunc.hasValue(address)) {
            address = ZYPCommonFunc.replaceAll(address, "\r\n", " ").trim();
            address = ZYPCommonFunc.replaceAll(address, "\r", " ").trim();
            address = ZYPCommonFunc.replaceAll(address, "\n", " ").trim();
        }
        if (ZYPCommonFunc.hasValue(address) && address.length() > npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit()) {
            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToFirstLineAddr, address.substring(0, npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit()));
            address = address.substring(npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit(), address.length());
            if (ZYPCommonFunc.hasValue(address) && address.length() > npai040003Tmsg.getAttr("shipToScdLineAddr").getDigit()) {
                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToScdLineAddr, address.substring(0, npai040003Tmsg.getAttr("shipToScdLineAddr").getDigit()));
            } else {
                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToScdLineAddr, address);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToFirstLineAddr, address);
        }

        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToCustCtyAddr, (String) sendPo.get(SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToStCd, (String) sendPo.get(SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToPostCd, (String) sendPo.get(SHIP_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToProvNm, (String) sendPo.get(SHIP_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToCtryCd, (String) sendPo.get(SHIP_TO_CTRY_CD));
        // START 2022/03/08 R.Azucena[QC#59752, ADD]
        if (reqSpclHdlg) {
            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.addShipTo01RefCmntTxt, (String) sendPo.get(ADD_SHIP_TO_01_REF_CMNT_TXT));
        }
        // END 2022/03/08 R.Azucena[QC#59752, ADD]

        EZDTBLAccessor.insert(npai040003Tmsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(npai040003Tmsg.getReturnCode())) {

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {npai040003Tmsg.getTableName() })));
            sb.append(String.format("%-17s", nullToBlank((String) sendPo.get(PO_ORD_NUM))));

            errorMsgList.add(sb.toString());

            return false;
        }

        // **************************************************************
        // update PO DTL
        // **************************************************************
        if (!updatePoDtl(sendPo, poSendTs)) {
            return false;
        }

        // **************************************************************
        // create ACK DTL
        // **************************************************************
        if (!createAckDtl(sendPo, poAckHdrPk)) {
            return false;
        }

        // **************************************************************
        // create combine XREF
        // **************************************************************
        if (!createCombineXref(sendPo, poSendTs)) {
            return false;
        }

        // **************************************************************
        // create PO History
        // **************************************************************
        if (!callCreatePoHistoryApi(sendPo)) {
            return false;
        }

        return true;
    }

    // START 2020/10/14 J.Evangelista [QC#57795,DEL]
//    /**
//     * createNpai040003ForSo
//     * @param sendSoList
//     * @param poOrdNum
//     * @param poOrdDtlLineNum
//     * @param poSendTs
//     * @return
//     */
//    private boolean createNpai040003ForSo(List<Map<String, Object>> sendSoList, String poOrdNum, String poOrdDtlLineNum, String poSendTs) {
//
//        NPAI0400_03TMsg npai040003Tmsg = new NPAI0400_03TMsg();
//
//        String soNum = "";
//
//        int line = Integer.parseInt(poOrdDtlLineNum) + 1;
//
//        List<String> updateSoList = new ArrayList<String>();
//
//        for (int i = 0; i < sendSoList.size(); i++) {
//
//            String lineNum = String.format("%03d", line);
//
//            Map<String, Object> sendSo = sendSoList.get(i);
//
//            soNum = (String) sendSo.get(SO_NUM);
//
//            seqNumber = seqNumber.add(BigDecimal.ONE);
//
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.interfaceId, this.interfaceId);
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.transactionId, this.transactionId);
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.segmentId, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.unitId, unitId);
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.seqNumber, seqNumber);
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.otbdPoRecId, OTBD_PO_REC_ID_L1);
//
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.ediPoLineNum, lineNum);
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.trdPtnrSkuCd, (String) sendSo.get(ASL_MDSE_CD));
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.cusaMdseCd, (String) sendSo.get(MDSE_CD));
//
//            String mdseNm = (String) sendSo.get(MDSE_DESC_SHORT_TXT);
//            if (ZYPCommonFunc.hasValue(mdseNm) && mdseNm.length() > npai040003Tmsg.getAttr("mdseNm").getDigit()) {
//                mdseNm = mdseNm.substring(0, npai040003Tmsg.getAttr("mdseNm").getDigit());
//            }
//
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.mdseNm, mdseNm);
//
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.ordQty, (BigDecimal) sendSo.get(RQST_ORD_QTY));
//
//            // **************************************************************
//            // UMO from XREF
//            // **************************************************************
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.uomCd, getUomCd(sendSo));
//
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.unitPrcAmt, (BigDecimal) sendSo.get(SP_DEAL_UNIT_PRC_AMT));
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToCustCd, (String) sendSo.get(SHIP_TO_CUST_CD));
//
//            // **************************************************************
//            // edit LOC_NM & ADDL_LOC_NM
//            // **************************************************************
//            String locNum = (String) sendSo.get(SO_CUST_LINE_LOC_NM);
//
//            if (ZYPCommonFunc.hasValue(locNum) && locNum.length() > npai040003Tmsg.getAttr("shipToLocNm").getDigit()) {
//                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToLocNm, locNum.substring(0, npai040003Tmsg.getAttr("shipToLocNm").getDigit()));
//                locNum = locNum.substring(npai040003Tmsg.getAttr("shipToLocNm").getDigit(), locNum.length());
//                if (ZYPCommonFunc.hasValue(locNum) && locNum.length() > npai040003Tmsg.getAttr("shipToAddlLocNm").getDigit()) {
//                    ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToAddlLocNm, locNum.substring(0, npai040003Tmsg.getAttr("shipToAddlLocNm").getDigit()));
//                } else {
//                    ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToAddlLocNm, locNum);
//                }
//            } else {
//                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToLocNm, locNum);
//            }
//
//            // **************************************************************
//            // edit address
//            // **************************************************************
//            String address = (String) sendSo.get(SO_CUST_LINE_ADDR);
//            if (ZYPCommonFunc.hasValue(address)) {
//                address = ZYPCommonFunc.replaceAll(address, "\r\n", " ").trim();
//                address = ZYPCommonFunc.replaceAll(address, "\r", " ").trim();
//                address = ZYPCommonFunc.replaceAll(address, "\n", " ").trim();
//            }
//            // START 2019/03/22 T.Ogura [QC#30565,MOD]
////            if (ZYPCommonFunc.hasValue(address) && locNum.length() > npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit()) {
//            if (ZYPCommonFunc.hasValue(address) && address.length() > npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit()) {
//            // END   2019/03/22 T.Ogura [QC#30565,MOD]
//                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToFirstLineAddr, address.substring(0, npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit()));
//                address = address.substring(npai040003Tmsg.getAttr("shipToFirstLineAddr").getDigit(), address.length());
//                if (ZYPCommonFunc.hasValue(address) && address.length() > npai040003Tmsg.getAttr("shipToScdLineAddr").getDigit()) {
//                    ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToScdLineAddr, address.substring(0, npai040003Tmsg.getAttr("shipToScdLineAddr").getDigit()));
//                } else {
//                    ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToScdLineAddr, address);
//                }
//            } else {
//                ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToFirstLineAddr, address);
//            }
//
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToCustCtyAddr, (String) sendSo.get(SHIP_TO_CTY_ADDR));
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToStCd, (String) sendSo.get(SHIP_TO_ST_CD));
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToPostCd, (String) sendSo.get(SHIP_TO_POST_CD));
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToCtryCd, (String) sendSo.get(SHIP_TO_CTRY_CD));
//            ZYPEZDItemValueSetter.setValue(npai040003Tmsg.shipToTelNum, (String) sendSo.get(CTAC_PTNR_PSN_TEL_NUM));
//
//            EZDTBLAccessor.insert(npai040003Tmsg);
//
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(npai040003Tmsg.getReturnCode())) {
//
//                StringBuilder sb = new StringBuilder();
//                sb.append(String.format("%-50s", S21MessageFunc.clspGetMessage(NPAM1172E, new String[] {npai040003Tmsg.getTableName() })));
//                sb.append(String.format("%-17s", nullToBlank((String) sendSo.get(PO_ORD_NUM))));
//
//                errorMsgList.add(sb.toString());
//
//                return false;
//            }
//
//            // **************************************************************
//            // create combine XREF
//            // **************************************************************
//            if (!createCombineXrefForSo(sendSo, poOrdNum, lineNum, poSendTs)) {
//                return false;
//            }
//            line++;
//            updateSoList.add(soNum);
//        }
//
//        // **************************************************************
//        // update SO
//        // **************************************************************
//        List<String> list = new ArrayList<String>(new HashSet<String>(updateSoList));
//
//        for (String updatesoNum : list) {
//
//            if (ZYPCommonFunc.hasValue(updatesoNum) && !updateSo(updatesoNum, poSendTs)) {
//                return false;
//            }
//
//        }
//
//        return true;
//
//    }
    // END   2020/10/14 J.Evangelista [QC#57795,DEL]

    /**
     * nullToBlank
     * @param val
     * @return
     */
    private String nullToBlank(String val) {

        if (!ZYPCommonFunc.hasValue(val)) {

            return "";
        }

        return val;

    }

    /**
     * @param getUomCd
     * @return
     */
    private String getUomCd(Map<String, Object> map) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(BIND_PKG_UOM_CD, (String) map.get(VND_UOM_CD));
        ssmParam.put(BIND_VND_CD, (String) map.get(VND_CD));

        String uomCd = (String) ssmBatchClient.queryObject("getUomCd", ssmParam);

        return uomCd;

    }

    /**
     * getCarrAcctNum
     * @param sendPo
     * @return
     */
    private String getCarrAcctNum(Map<String, Object> map) {

        String carrAcctNum = null;
        String prchGrpCd = null;
        String carrTpCd = null;
        boolean cpoFlg = false;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        Map<String, Object> cpoInfoMap = new HashMap<String, Object>();
        Map<String, Object> carrXrefMap = new HashMap<String, Object>();

        if (map.get(CARR_ACCT_NUM) == null) {

            if (map.get(CARR_CD) != null) {

                // Search VND
                ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                ssmParam.put(BIND_CARR_CD, (String) map.get(CARR_CD));

                // Get Carrier Type Code From VND
                carrTpCd = (String) ssmBatchClient.queryObject("getcarrTpCd", ssmParam);

                // QC#58769 Start
                // Other -DS_ORD_CATG_CD / DS_ORD_TP_CD
                if (PO_ORD_SRC.SALES_ORDER.equals(map.get(PO_ORD_SRC_CD))) {
                    ssmParam.clear();
                    // Search Order Category/Reason from CPO
                    ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    ssmParam.put(BIND_CPO_ORD_NUM, (String) map.get(CPO_ORD_NUM));

                    cpoInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getCpoInfo", ssmParam);

                    cpoFlg = true;

                }
                // QC#58769 End
                // Other -PRCH_GRP_CD
                if (map.get(PRCH_GRP_CD) != null) {
                    prchGrpCd = (String) map.get(PRCH_GRP_CD);
                }

                if (carrTpCd == null) {
                    return null;
                }

                // search 1
                ssmParam.clear();

                ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                ssmParam.put(BIND_CARR_TP_CD, carrTpCd);

                if (carrAcctNum == null && cpoFlg) {
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);
                    ssmParam.put(BIND_PO_ORD_SRC_CD, (String) cpoInfoMap.get(PO_ORD_SRC_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }
                }

                if (carrAcctNum == null && cpoFlg) {
                    // search 2
                    // QC#58769
//                  ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 3
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 4
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 5
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 6
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 7
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 8
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }
                }

                if (carrAcctNum == null) {
                    // search 9
                    ssmParam.put(BIND_PO_ORD_SRC_CD, STAR);
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_ACCT_NUM);
                    }
                }
                if (carrAcctNum == null) {
                    // search 10
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }

            } else {

                // Other -DS_ORD_CATG_CD / DS_ORD_TP_CD
                if (PO_ORD_SRC.SALES_ORDER.equals(map.get(PO_ORD_SRC_CD))) {
                    // Search Order Category/Reason from CPO
                    ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                    ssmParam.put(BIND_CPO_ORD_NUM, (String) map.get(CPO_ORD_NUM));

                    cpoInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getCpoInfo", ssmParam);

                    cpoFlg = true;
                }
                // Other -PRCH_GRP_CD
                if (map.get(PRCH_GRP_CD) != null) {
                    prchGrpCd = (String) map.get(PRCH_GRP_CD);
                }

                // Search CARR_TP_CD from TRD_PTNR_SHPG_X_REF
                Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();

                trdPtnrShpgXRefMap = searchtrdPtnrShpgXRef(map);

                if (trdPtnrShpgXRefMap != null) {
                    carrTpCd = (String) trdPtnrShpgXRefMap.get(CARR_TP_CD);
                }

                if (carrTpCd == null) {
                    return null;
                }

                // search 1
                ssmParam.clear();

                ssmParam.put(BIND_GLBL_CMPY_CD, this.glblCmpyCd);
                ssmParam.put(BIND_CARR_TP_CD, carrTpCd);

                if (carrAcctNum == null && cpoFlg) {
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);
                    ssmParam.put(BIND_PO_ORD_SRC_CD, (String) cpoInfoMap.get(PO_ORD_SRC_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 2
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 3
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 4
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 5
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 6
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 7
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 8
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }

                }
                if (carrAcctNum == null) {
                    // search 9
                    ssmParam.put(BIND_PO_ORD_SRC_CD, STAR);
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }
                }
                if (carrAcctNum == null) {
                    // search 10
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get(CARR_ACCT_NUM);
                        carrCdfromXref = (String) carrXrefMap.get(CARR_CD);
                    }
                }
            }

        } else {

            carrAcctNum = (String) map.get(CARR_ACCT_NUM);
        }
        return carrAcctNum;

    }

    /**
     * registerMail
     */
    private void registerMail() {

        /*************************************************************
         * 1. Get From
         ************************************************************/
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress from = null;

        if (!addrFromList.isEmpty()) {

            from = addrFromList.get(0);
        }
        /*************************************************************
         * 2. Get To
         ************************************************************/
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.isEmpty()) {

            throw new S21AbendException(NWBM0092E);
        }

        /*************************************************************
         * 3. Create message for Body
         ************************************************************/
        StringBuilder msgBuf = new StringBuilder();

        for (int i = 0; i < errorMsgList.size(); i++) {

            msgBuf.append((String) errorMsgList.get(i));
            msgBuf.append(LINE_FEED_CODE);
        }

        String message = msgBuf.toString();
        /*************************************************************
         * 4. Create Subject and Body
         ************************************************************/
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);

        if (template == null) {

            throw new S21AbendException(NWBM0092E);
        }

        String currentTime = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN_FOR_MAIL);

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_BATCH_ID, BUSINESS_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, message);

        /*************************************************************
         * 5. Call Mail API
         ************************************************************/
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();

    }

    /**
     * termRoutine
     */
    protected void termRoutine() {

        // Setting Process Termination Code
        setTermState(this.termCd, this.normalRecCount, this.totalErrCount, this.normalRecCount + this.totalErrCount);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        new NPAB260001().executeBatch(NPAB260001.class.getSimpleName());

    }

}
