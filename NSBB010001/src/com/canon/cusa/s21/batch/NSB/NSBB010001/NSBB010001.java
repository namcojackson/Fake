/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB010001;

import static com.canon.cusa.s21.batch.NSB.NSBB010001.constant.NSBB010001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;
import business.db.CCYTMsg;
import business.db.CNTYTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.FSRTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_INVTMsg;
import business.db.SVC_INV_CHRG_TPTMsg;
import business.db.SVC_INV_LINETMsg;
import business.db.SVC_INV_LINETMsgArray;
import business.db.SVC_INV_LINE_ALLOCTMsg;
import business.db.SVC_INV_LINE_ALLOCTMsgArray;
import business.db.SVC_INV_LINE_TAX_DTLTMsg;
import business.db.TEMP_ETTLTMsg;
import business.db.TOCTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;
import business.parts.NWZC040003PMsg;
import business.parts.NWZC040005PMsg;
import business.parts.NWZC040007PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NWZ.NWZC036101.constant.NWZC036101Constant;
import com.canon.cusa.s21.api.NWZ.NWZC040001.NWZC040001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.SVC_COV_FEAT;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIForAddlBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIForFreightBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIForUsageBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForAddl;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForBase;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForFreight;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForSvcPrc;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForUsage;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001Exchange;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_PRC_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL_RSN_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_BAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_OM_LINK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/19/2013   Fujitsu         S.Nakai         Create          N/A
 * 09/19/2013   Fujitsu         S.Nakai         Update          QC2070
 * 12/10/2015   Hitachi         T.Harada        Update          CSA
 * 02/16/2016   Hitachi         A.Kohinata      Update          QC3373
 * 02/23/2016   Hitachi         T.Aoyagi        Update          QC2885
 * 03/22/2016   Hitachi         T.Kanasaka      Update          QC#5727
 * 03/24/2016   Hitachi         T.Kanasaka      Update          QC#5992
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC#5921
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC#3829
 * 06/07/2016   Hitachi         T.Kanasaka      Update          QC#9517
 * 06/29/2016   Hitachi         T.Kanasaka      Update          QC#10735
 * 07/05/2016   Hitachi         T.Kanasaka      Update          QC#9438
 * 08/05/2016   Hitachi         T.Tomita        Update          QC#12836
 * 11/02/2016   Hitachi         K.Kishimoto     Update          QC#12540
 * 11/10/2016   Hitachi         K.Kishimoto     Update          QC#15789
 * 02/22/2017   Hitachi         K.Kitachi       Update          QC#17515
 * 08/07/2017   Hitachi         M.Kidokoro      Update          QC#20073
 * 09/05/2017   Hitachi         K.Kim           Update          QC#20935
 * 10/23/2017   Fujitsu         T.Aoi           Update          QC#20719
 * 2017/11/07   Hitachi         K.Kojima        Update          QC#22224
 * 2017/11/29   Hitachi         K.Kojima        Update          QC#21918
 * 2018/05/31   Hitachi         K.Kojima        Update          QC#23685
 * 2018/07/10   Hitachi         K.Kitachi       Update          QC#22788
 * 2018/07/12   Hitachi         K.Kojima        Update          QC#26571
 * 2018/07/25   CITS            M.Naito         Update          QC#13309
 * 2018/08/08   Hitachi         A.Kohinata      Update          QC#27329-3
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 * 2019/03/01   Hitachi         S.Kitamura      Update          QC#30496
 * 2019/03/18   Hitachi         S.Kitamura      Update          QC#30736
 * 2019/04/03   Hitachi         A.Kohinata      Update          QC#31037
 * 2019/12/02   Hitachi         K.Kim           Update          QC#54899
 * 2020/07/13   Hitachi         K.Kitachi       Update          QC#57304
 * 2021/03/05   CITS            R.Mercado       Update          QC#58471
 * 2023/01/20   Hitachi         R.Onozuka       Update          QC#60823
 * 2023/11/13   Hitachi         K.Kishimoto     Update          QC#61468
 * </pre>
 */
public class NSBB010001 extends S21BatchMain {

    /** Normal Counter */
    private int normCnt = 0;

    /** Normal Counter */
    private int lineNormCnt = 0;

    /** Normal Counter */
    private int curLineCnt = 0;

    /** Normal Counter */
    private int lineErrCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** message list */
    private Map<String, Object> msgMap = new HashMap<String, Object>();

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Term Code */
    private TERM_CD termCd = null;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** ssm batch client */
    private S21SsmBatchClient ssmBatchClient = null;

    // START [CSA ADD]
    /** Divide Number */
    private String divideNum = null;

    /** Service Invoice Page Layout Data Map */
    Map<String, Object> pgLyotMap = null;

    /** NUM_CONST Data Map */
    Map<String, BigDecimal> numConstMap = null;

    // END [CSA ADD]

    // START 2018/05/31 K.Kojima [QC#23685,ADD]
    private BigDecimal funcCcyAftDeclPntDigitNum = null;

    /** SVC_INV update time Info */
    private Map<String, Map<String, String>> svcInvUpdTmInfo = new HashMap<String, Map<String,String>>();

    /** Cache */
    private final S21LRUMap<String, EZDTMsg> cache = new S21LRUMap<String, EZDTMsg>(384);
    // END 2018/05/31 K.Kojima [QC#23685,ADD]

    // add start 2019/04/03 QC#31037
    /** User Variable1 */
    private String usrVar1 = null;
    // add end 2019/04/03 QC#31037

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB010001().executeBatch(NSBB010001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {MSG_GLBL_CMPY_CD });
        }
        // START 2016/02/16 A.Kohinata [QC3373, MOD]
//        this.batchDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(slsDt)) {
//            throw new S21AbendException(NSBM0071E, new String[] {MSG_BATCH_DATE });
            throw new S21AbendException(NSBM0071E, new String[] {MSG_SALES_DATE });
        }
        // END 2016/02/16 A.Kohinata [QC3373, MOD]
        // mod start 2019/04/03 QC#31037
        // START [CSA ADD]
        this.usrVar1 = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.usrVar1)) {
            throw new S21AbendException(NSBM0071E, new String[] {MSG_USR_VAR1 });
        }
        // END [CSA ADD]
        // mod end 2019/04/03 QC#31037
    }

    // mod start 2019/04/03 QC#31037
    @Override
    protected void mainRoutine() {
        if (CREATE_STAND_BY_FEE_MODE.equals(this.usrVar1)) {
            // Create Stand By Fee Invoice
            this.numConstMap = getNumConstMap();
            this.pgLyotMap = getSvcInvPgLyotMap();
            createSvcInvForStandByFee();
        } else {
            this.divideNum = this.usrVar1;
            createInv();
        }
    }
    // mod end 2019/04/03 QC#31037

    // START [CSA CHANGE]
    // START 2016/07/05 T.Kanasaka [QC#9438, MOD]
    // mod start 2019/04/03 QC#31037
    //@Override
    //protected void mainRoutine() {
    private void createInv() {
    // mod end 2019/04/03 QC#31037

        Map<String, String> varConstMap = getVarConstMap();
        this.numConstMap = getNumConstMap();
        this.pgLyotMap = getSvcInvPgLyotMap();

        // del start 2019/04/03 QC#31037
        // START 2018/07/18 M.Naito [QC#13309, ADD]
        // check Temporary Entitlement
        //createSvcInvForStandByFee();
        // END 2018/07/18 M.Naito [QC#13309, ADD]
        // del end 2019/04/03 QC#31037

        // START 2018/05/31 K.Kojima [QC#23685,ADD]
        getInitData();
        // END 2018/05/31 K.Kojima [QC#23685,ADD]

        PreparedStatement st = null;
        ResultSet rs = null;
        Map<String, Object> prmsMap = getSsmParams();

        try {
            st = ssmLLClient.createPreparedStatement("getSvcInvList", prmsMap);
            rs = st.executeQuery();

            List<NWZC040001PMsg> invPmsgList = new ArrayList<NWZC040001PMsg>();
            List<NWZC040002PMsg> shipPmsgList = new ArrayList<NWZC040002PMsg>();
            List<NWZC040003PMsg> invLinePmsgList = new ArrayList<NWZC040003PMsg>();
            List<NWZC040005PMsg> taxDtlPMsgList = new ArrayList<NWZC040005PMsg>();
            List<NWZC040007PMsg> invSlsCrPMsgList = new ArrayList<NWZC040007PMsg>();

            int intInvBolLineNum = 0;
            int intInvLineNum = 0;
            BigDecimal svcInvPk = null;
            String svcInvNum = null;
            String curUpTm = null;
            String curUpTmZn = null;
            String invBolLineNum = null;
            String invLineNum = null;

            // START 2018/05/31 K.Kojima [QC#23685,ADD]
            List<String> taxSetSvcInvNumList = new ArrayList<String>();
            List<String> taxErrSvcInvNumList = new ArrayList<String>();
            SVC_INVTMsg svcInvTMsg = null;
            // END 2018/05/31 K.Kojima [QC#23685,ADD]

            while (rs.next()) {
                // START 2018/07/12 K.Kojima [QC#26571,MOD]
                // // START 2017/09/05 K.Kim [QC#20935, MOD]
                // // if (intInvLineNum > 0 && BigDecimal.ONE.compareTo(getBigDecimal(rs, SELECT.INV_RANK)) == 0) {
                // if (intInvLineNum > 0 && !getString(rs, SELECT.SVC_INV_NUM).equals(svcInvNum)) {
                // // END 2017/09/05 K.Kim [QC#20935, MOD]
                // 
                //     // START 2018/05/31 K.Kojima [QC#23685,MOD]
                //     // createInvoice(svcInvNum, svcInvPk, curUpTm, curUpTmZn, invPmsgList, shipPmsgList, invLinePmsgList, taxDtlPMsgList, invSlsCrPMsgList);
                //     if (taxErrSvcInvNumList.contains(svcInvNum)) {
                //         rollback();
                //         errCnt++;
                //         updateSvcInv(svcInvPk, svcInvNum, curUpTm, curUpTmZn, SVC_OM_LINK_STS.ERROR);
                //         commit();
                //     } else {
                //         createInvoice(svcInvNum, svcInvPk, curUpTm, curUpTmZn, invPmsgList, shipPmsgList, invLinePmsgList, taxDtlPMsgList, invSlsCrPMsgList);
                //     }
                //     // END 2018/05/31 K.Kojima [QC#23685,MOD]
                // 
                //     intInvBolLineNum = 0;
                //     intInvLineNum = 0;
                //     clearList(invPmsgList, shipPmsgList, invLinePmsgList, taxDtlPMsgList, invSlsCrPMsgList);
                // }
                if (!getString(rs, SELECT.SVC_INV_NUM).equals(svcInvNum)) {
                    if (taxErrSvcInvNumList.contains(svcInvNum)) {
                        rollback();
                        errCnt++;
                        updateSvcInv(svcInvPk, svcInvNum, curUpTm, curUpTmZn, SVC_OM_LINK_STS.ERROR);
                        commit();
                    } else if (intInvLineNum > 0){
                        createInvoice(svcInvNum, svcInvPk, curUpTm, curUpTmZn, invPmsgList, shipPmsgList, invLinePmsgList, taxDtlPMsgList, invSlsCrPMsgList);
                    }
                    intInvBolLineNum = 0;
                    intInvLineNum = 0;
                    clearList(invPmsgList, shipPmsgList, invLinePmsgList, taxDtlPMsgList, invSlsCrPMsgList);
                }
                // END 2018/07/12 K.Kojima [QC#26571,MOD]

                /** process each invoice header. */
                svcInvPk = getBigDecimal(rs, SELECT.SVC_INV_PK);
                svcInvNum = getString(rs, SELECT.SVC_INV_NUM);
                curUpTm = getString(rs, SELECT.TM);
                curUpTmZn = getString(rs, SELECT.TM_ZN);

                // START 2018/05/31 K.Kojima [QC#23685,ADD]
                if (!taxSetSvcInvNumList.contains(svcInvNum)) {
                    if (!setTax(svcInvPk, svcInvNum, getString(rs, SELECT.DS_INV_TP_CD), getString(rs, SELECT.SVC_INV_SRC_TP_CD), curUpTm, curUpTmZn)) {
                        taxErrSvcInvNumList.add(svcInvNum);
                    }
                    taxSetSvcInvNumList.add(svcInvNum);
                }
                if (this.svcInvUpdTmInfo.containsKey(svcInvNum)) {
                    Map<String, String> updTmInfo = this.svcInvUpdTmInfo.get(svcInvNum);
                    curUpTm = updTmInfo.get("ezUpTime");
                    curUpTmZn = updTmInfo.get("ezUpTimeZone");
                }
                if (taxErrSvcInvNumList.contains(svcInvNum)) {
                    continue;
                }

                if (BigDecimal.ONE.equals(getBigDecimal(rs, SELECT.INV_RANK))) {
                    svcInvTMsg = getSvcInvTMsg(svcInvPk);
                }
                SVC_INV_LINETMsg svcInvLineTMsg = getSvcInvLineTMsg(getBigDecimal(rs, SELECT.SVC_INV_LINE_PK));
                // END 2018/05/31 K.Kojima [QC#23685,ADD]

                if (BigDecimal.ONE.equals(getBigDecimal(rs, SELECT.INV_RANK))) {
                    /** Invoice and Shipments Parameter Set */
                    // START 2018/05/31 K.Kojima [QC#23685,MOD]
                    // NWZC040001PMsg invPmsg = setNWZC040001PMsg(rs, varConstMap);
                    NWZC040001PMsg invPmsg = setNWZC040001PMsg(rs, varConstMap, svcInvTMsg);
                    // END 2018/05/31 K.Kojima [QC#23685,MOD]
                    invPmsgList.add(invPmsg);

//                    NWZC040002PMsg shipPmsg = setNWZC040002PMsg(rs);
//                    shipPmsgList.add(shipPmsg);
                }

                if (BigDecimal.ONE.equals(getBigDecimal(rs, SELECT.SHIP_RANK))) {
                    intInvBolLineNum++;
                    invBolLineNum = ZYPCommonFunc.leftPad(String.valueOf(intInvBolLineNum), INV_BOL_LINE_NUM_LENGTH, ZERO);
                    intInvLineNum = 0;

                    NWZC040002PMsg shipPmsg = setNWZC040002PMsg(rs, invBolLineNum);
                    shipPmsgList.add(shipPmsg);
                }

                boolean isCatgFleet = DS_CONTR_CATG.FLEET.equals(getString(rs, SELECT.DS_CONTR_CATG_CD));
                boolean isLineMach = ZYPConstant.FLG_ON_Y.equals(getString(rs, SELECT.MAIN_MACH_FLG));
                boolean isLineAsry = ZYPConstant.FLG_ON_Y.equals(getString(rs, SELECT.CONTR_ASRY_FLG));

                if (!isCatgFleet || (isCatgFleet && !isLineMach && !isLineAsry)) {
                    /** process each invoice Line. */
                    intInvLineNum++;
                    invLineNum = ZYPCommonFunc.leftPad(String.valueOf(intInvLineNum), INV_LINE_NUM_LENGTH, ZERO);

                    BigDecimal svcInvLinePk = getBigDecimal(rs, SELECT.SVC_INV_LINE_PK);

                    /** Invoice Line Parameter Set */
                    // START 2018/05/31 K.Kojima [QC#23685,MOD]
                    // NWZC040003PMsg invLinePmsg = setNWZC040003PMsg(rs, invBolLineNum, invLineNum);
                    NWZC040003PMsg invLinePmsg = setNWZC040003PMsg(rs, invBolLineNum, invLineNum, svcInvLineTMsg);
                    // END 2018/05/31 K.Kojima [QC#23685,MOD]
                    invLinePmsgList.add(invLinePmsg);

                    /** Invoice Line Tax Detail Parameter Set */
                    taxDtlPMsgList = setNWZC040005PMsg(svcInvLinePk, taxDtlPMsgList, invBolLineNum, invLineNum);

                    /** Invoice Line Sales Credit Parameter Set */
                    invSlsCrPMsgList = setNWZC040007PMsg(svcInvLinePk, invSlsCrPMsgList, invBolLineNum, invLineNum);
                }
            }

            // START 2018/07/12 K.Kojima [QC#26571,MOD]
            // if (intInvLineNum > 0) {
            if (taxErrSvcInvNumList.contains(svcInvNum)) {
                rollback();
                errCnt++;
                updateSvcInv(svcInvPk, svcInvNum, curUpTm, curUpTmZn, SVC_OM_LINK_STS.ERROR);
                commit();
            } else if (intInvLineNum > 0) {
            // END 2018/07/12 K.Kojima [QC#26571,MOD]
                createInvoice(svcInvNum, svcInvPk, curUpTm, curUpTmZn, invPmsgList, shipPmsgList, invLinePmsgList, taxDtlPMsgList, invSlsCrPMsgList);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(st, rs);
        }
    }
    // END 2016/07/05 T.Kanasaka [QC#9438, MOD]
    // END [CSA CHANGE]

    private Map<String, String> getVarConstMap() {

        Map<String, String> varConstMap = new HashMap<String, String>();
        String dsOrdTpCd = ZYPCodeDataUtil.getVarCharConstValue(NAAB0030_DS_ORD_TP_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NAAB0030_DS_ORD_TP_CD });
        }
        String dsOrdRsnCd = ZYPCodeDataUtil.getVarCharConstValue(NAAB0030_DS_ORD_RSN_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(dsOrdRsnCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NAAB0030_DS_ORD_RSN_CD });
        }
        String trxSrcTpCd = ZYPCodeDataUtil.getVarCharConstValue(NAAB0030_TRX_SRC_TP_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(trxSrcTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NAAB0030_TRX_SRC_TP_CD });
        }
        String sysSrcCd = ZYPCodeDataUtil.getVarCharConstValue(NAAB0030_SYS_SRC_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(sysSrcCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NAAB0030_SYS_SRC_CD });
        }
        varConstMap.put(NAAB0030_DS_ORD_TP_CD, dsOrdTpCd);
        varConstMap.put(NAAB0030_DS_ORD_RSN_CD, dsOrdRsnCd);
        varConstMap.put(NAAB0030_TRX_SRC_TP_CD, trxSrcTpCd);
        varConstMap.put(NAAB0030_SYS_SRC_CD, sysSrcCd);
        return varConstMap;
    }

    // START [CSA ADD]
    /** Get NUM_CONST */
    private Map<String, BigDecimal> getNumConstMap() {

        Map<String, BigDecimal> constMap = new HashMap<String, BigDecimal>();
        BigDecimal multiCalcCnt = ZYPCodeDataUtil.getNumConstValue(MULTI_SVC_INV_CALC_CNT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(multiCalcCnt)) {
            throw new S21AbendException(NSBM0069E, new String[] {MULTI_SVC_INV_CALC_CNT });
        }
        BigDecimal invPrtTotLmt = ZYPCodeDataUtil.getNumConstValue(INV_PRT_TOT_LMT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(invPrtTotLmt)) {
            throw new S21AbendException(NSBM0069E, new String[] {INV_PRT_TOT_LMT });
        }
        BigDecimal bllgPrvwAvalMaxPrc = ZYPCodeDataUtil.getNumConstValue(BLLG_PRVW_AVAL_MAX_PRC, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(bllgPrvwAvalMaxPrc)) {
            throw new S21AbendException(NSBM0069E, new String[] {BLLG_PRVW_AVAL_MAX_PRC });
        }
        BigDecimal defSlsRepCrPct = ZYPCodeDataUtil.getNumConstValue(DEF_SLS_REP_CR_PCT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(defSlsRepCrPct)) {
            throw new S21AbendException(NSBM0069E, new String[] {DEF_SLS_REP_CR_PCT });
        }
        constMap.put(MULTI_SVC_INV_CALC_CNT, multiCalcCnt);
        constMap.put(INV_PRT_TOT_LMT, invPrtTotLmt);
        constMap.put(BLLG_PRVW_AVAL_MAX_PRC, bllgPrvwAvalMaxPrc);
        constMap.put(DEF_SLS_REP_CR_PCT, defSlsRepCrPct);
        return constMap;
    }

    /** Get Service Invoice Page Layout Calculation Parameter */
    private Map<String, Object> getSvcInvPgLyotMap() {

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        List<Map<String, Object>> svcInvPgLineHgtList = ssmBatchClient.queryObjectList("getSvcInvPgLineHgtList", prmMap);

        if (svcInvPgLineHgtList.size() == 0) {
            throw new S21AbendException(NSBM0071E, new String[] {SVC_INV_PG_LYOT });
        }

        Map<String, Object> svcInvPgLyotMap = new HashMap<String, Object>();
        for (Map<String, Object> dtl : svcInvPgLineHgtList) {
            String svcInvPgLyotCd = (String) dtl.get(SVC_INV_PG_LYOT_CD);
            BigDecimal svcInvPgLineHgt = (BigDecimal) dtl.get(SVC_INV_PG_LINE_HGT);
            svcInvPgLyotMap.put(svcInvPgLyotCd, svcInvPgLineHgt);
        }
        return svcInvPgLyotMap;
    }

    // END [CSA ADD]

    private Map<String, Object> getSsmParams() {

        Map<String, Object> prmsMap = new HashMap<String, Object>();
        prmsMap.put("glblCmpyCd", glblCmpyCd);
        prmsMap.put("svcInvStsCd", SVC_INV_STS.PRINTED);
        prmsMap.put("svcInvOmLinkFlg", ZYPConstant.FLG_OFF_N);

        // START [CSA CHANGE]
        prmsMap.put("multiCalcCnt", this.numConstMap.get(MULTI_SVC_INV_CALC_CNT));
        prmsMap.put("divideNum", this.divideNum);
        prmsMap.put("inCompleted", SVC_OM_LINK_STS.INCOMPLETED);
        prmsMap.put("error", SVC_OM_LINK_STS.ERROR);
        // END [CSA CHANGE]

        // START 2016/07/05 T.Kanasaka [QC#9438, ADD]
        prmsMap.put("dsContrCatgCd_Fleet", DS_CONTR_CATG.FLEET);
        // END 2016/07/05 T.Kanasaka [QC#9438, ADD]

        // START 2020/07/13 K.Kitachi [QC#57304, ADD]
        prmsMap.put("baseCharge", SVC_INV_CHRG_TP.BASE_CHARGE);
        prmsMap.put("meterCharge", SVC_INV_CHRG_TP.METER_CHARGE);
        // END 2020/07/13 K.Kitachi [QC#57304, ADD]
        // Start 2023/1/20 R.Onozuka [QC#60823, ADD]
        prmsMap.put("flgOne", ZYPConstant.FLG_ON_1);
        // End 2023/1/20 R.Onozuka [QC#60823, ADD]

        return prmsMap;
    }

    // START [CSA ADD]
    private void createInvoice(String svcInvNum, BigDecimal svcInvPk, String curUpTm, String curUpTmZn, //
            List<NWZC040001PMsg> invPmsgList, //
            List<NWZC040002PMsg> shipPmsgList, //
            List<NWZC040003PMsg> invLinePmsgList, //
            List<NWZC040005PMsg> taxDtlPMsgList, //
            List<NWZC040007PMsg> invSlsCrPMsgList) {

        //TODO
        S21InfoLogOutput.println("[TestLog][createInvoice] START");

        boolean errFlg = false;

        if (checkDupliInvNum(svcInvNum)) {
            /** Call Import Invoice API . */
            NWZC040001 imptInvApi = new NWZC040001();

            imptInvApi.execute(invPmsgList, shipPmsgList, invLinePmsgList, null, taxDtlPMsgList, null, invSlsCrPMsgList, ONBATCH_TYPE.BATCH);
            errFlg = isErr(svcInvNum, invPmsgList, shipPmsgList, invLinePmsgList, taxDtlPMsgList, invSlsCrPMsgList);

            if (!errFlg) {
                /** update OM link flag. */
                //Mod Start 03/28/2016 <QC#5921> 
                errFlg = updateSvcInv(svcInvPk, svcInvNum, curUpTm, curUpTmZn, SVC_OM_LINK_STS.COMPLETED);
                //Mod End   03/28/2016 <QC#5921> 
            }
        } else {
            setErrMsg(svcInvNum, NSBM0016E, MSG_SVC_INV_NUM + svcInvNum);
            errFlg = true;
        }
        if (errFlg) {
            rollback();
            errCnt++;
            lineErrCnt = lineErrCnt + curLineCnt;
            //Add Start 03/28/2016 <QC#5921>
            errFlg = updateSvcInv(svcInvPk, svcInvNum, curUpTm, curUpTmZn, SVC_OM_LINK_STS.ERROR);
            commit();
            //Add End   03/28/2016 <QC#5921>
        } else {
            commit();
            normCnt++;
            lineNormCnt = lineNormCnt + curLineCnt;
        }

        //TODO
        S21InfoLogOutput.println("[TestLog][createInvoice] END");
    }

    // END [CSA ADD]

    private boolean checkDupliInvNum(String invNum) {

        Map<String, String> prmMap = new HashMap<String, String>();

        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("invNum", invNum);

        BigDecimal invNumCnt = (BigDecimal) ssmBatchClient.queryObject("getInvCnt", prmMap);

        if (BigDecimal.ZERO.compareTo(invNumCnt) < 0) {
            return false;
        } else {
            return true;
        }
    }

    //Mod Start 03/28/2016 <QC#5921>
    private boolean updateSvcInv(BigDecimal svcInvPk, String svcInvNum, String upTm, String upTmZn, String setStsCd) {

        SVC_INVTMsg prmTmsg = new SVC_INVTMsg();
        ZYPEZDItemValueSetter.setValue(prmTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTmsg.svcInvPk, svcInvPk);

        SVC_INVTMsg svcInvTmsg = (SVC_INVTMsg) EZDTBLAccessor.findByKeyForUpdateWait(prmTmsg);
        if (svcInvTmsg == null) {
            setErrMsg(svcInvNum, NSBM0072E, MSG_SVC_INV, MSG_SVC_INV_PK, svcInvPk.toString());
            return true;
        }

        if (setStsCd.equals(SVC_OM_LINK_STS.COMPLETED)) {
            ZYPEZDItemValueSetter.setValue(svcInvTmsg.svcInvOmLinkFlg, ZYPConstant.FLG_ON_Y);
        }
        // START [CSA CHANGE]
        ZYPEZDItemValueSetter.setValue(svcInvTmsg.svcInvOmLinkStsCd, setStsCd);
        EZDTBLAccessor.updateSelectionField(svcInvTmsg, new String[] {"svcInvOmLinkFlg", "svcInvOmLinkStsCd" });
        // END [CSA CHANGE]
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcInvTmsg.getReturnCode())) {
            setErrMsg(svcInvNum, NSBM0073E, MSG_SVC_INV, MSG_SVC_INV_PK, svcInvPk.toString());
            return true;
        }
        return false;
    }
    //Mod End  03/28/2016 <QC#5921>

    // START 2018/05/31 K.Kojima [QC#23685,MOD]
    // private NWZC040001PMsg setNWZC040001PMsg(ResultSet rs, Map<String, String> varConstMap) throws SQLException {
    private NWZC040001PMsg setNWZC040001PMsg(ResultSet rs, Map<String, String> varConstMap, SVC_INVTMsg svcInvTMsg) throws SQLException {
    // END 2018/05/31 K.Kojima [QC#23685,MOD]

        NWZC040001PMsg invPmsg = new NWZC040001PMsg();
        ZYPEZDItemValueSetter.setValue(invPmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invPmsg.invNum, getString(rs, SELECT.SVC_INV_NUM));
        ZYPEZDItemValueSetter.setValue(invPmsg.origInvNum, getString(rs, SELECT.ORIG_SVC_INV_NUM));
        ZYPEZDItemValueSetter.setValue(invPmsg.invDt, getString(rs, SELECT.INV_DT));
        ZYPEZDItemValueSetter.setValue(invPmsg.acctDt, slsDt);
        ZYPEZDItemValueSetter.setValue(invPmsg.invTpCd, getString(rs, SELECT.INV_TP_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.netDueDt, getString(rs, SELECT.INV_DUE_DT));
        ZYPEZDItemValueSetter.setValue(invPmsg.custIssPoNum, getString(rs, SELECT.CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(invPmsg.custIssPoDt, getString(rs, SELECT.CUST_ISS_PO_DT));
        ZYPEZDItemValueSetter.setValue(invPmsg.billToCustCd, getString(rs, SELECT.BILL_TO_CUST_CD));
        // START [CSA CHANGE billTo -> sellTo]
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToCustCd, getString(rs, SELECT.BILL_TO_CUST_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToLocNm, getString(rs, SELECT.BILL_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToAddlLocNm, getString(rs, SELECT.BILL_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToFirstLineAddr, getString(rs, SELECT.BILL_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToScdLineAddr, getString(rs, SELECT.BILL_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToThirdLineAddr, getString(rs, SELECT.BILL_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToFrthLineAddr, getString(rs, SELECT.BILL_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToCtyAddr, getString(rs, SELECT.BILL_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToCntyNm, getString(rs, SELECT.BILL_TO_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToProvNm, getString(rs, SELECT.BILL_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToStCd, getString(rs, SELECT.BILL_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToPostCd, getString(rs, SELECT.BILL_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToCtryCd, getString(rs, SELECT.BILL_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToFirstRefCmntTxt, getString(rs, SELECT.BILL_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(invPmsg.sellToScdRefCmntTxt, getString(rs, SELECT.BILL_TO_SCD_REF_CMNT_TXT));
        // END [CSA CHANGE billTo -> sellTo]
        ZYPEZDItemValueSetter.setValue(invPmsg.payerCustCd, getString(rs, SELECT.BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.pmtTermStartDt, getString(rs, SELECT.PMT_TERM_START_DT));
        ZYPEZDItemValueSetter.setValue(invPmsg.pmtTermCd, getString(rs, SELECT.PMT_TERM_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.cashDiscTermCd, getString(rs, SELECT.CASH_DISC_TERM_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.invTotDealSlsAmt, getBigDecimal(rs, SELECT.INV_TOT_DEAL_NET_AMT));
        // START 2023/11/10 [QC#61468, MOD]
//        ZYPEZDItemValueSetter.setValue(invPmsg.invTotDealFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(invPmsg.invTotDealFrtAmt, getInvTotDealFrtAmt(getString(rs, SELECT.SVC_INV_NUM)));
        // END   2023/11/10 [QC#61468, MOD]
        // START 2018/05/31 K.Kojima [QC#23685,MOD]
        // ZYPEZDItemValueSetter.setValue(invPmsg.invTotDealTaxAmt, getBigDecimal(rs, SELECT.INV_TOT_DEAL_TAX_AMT));
        ZYPEZDItemValueSetter.setValue(invPmsg.invTotDealTaxAmt, svcInvTMsg.invTotDealTaxAmt);
        // END 2018/05/31 K.Kojima [QC#23685,MOD]
        ZYPEZDItemValueSetter.setValue(invPmsg.invTotDealDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(invPmsg.invTotDealInsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(invPmsg.invTotFuncInsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(invPmsg.dealCcyCd, getString(rs, SELECT.DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.actlApplyExchRate, getBigDecimal(rs, SELECT.CCY_EXCH_RATE));
        ZYPEZDItemValueSetter.setValue(invPmsg.flPlnFlg, ZYPConstant.FLG_OFF_N);
        // START [CSA CHANGE]
        if (ZYPConstant.FLG_OFF_N.equals(getString(rs, SELECT.INV_PRINT_FLG_CRD))) {
            ZYPEZDItemValueSetter.setValue(invPmsg.invPrintStsCd, INV_PRINT_STS_N);
        } else {
            ZYPEZDItemValueSetter.setValue(invPmsg.invPrintStsCd, INV_PRINT_STS_DEF);
        }
        ZYPEZDItemValueSetter.setValue(invPmsg.trxSrcTpCd, varConstMap.get(NAAB0030_TRX_SRC_TP_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.invMlSendStsCd, INV_SEND_STS_PROCESSED);
        ZYPEZDItemValueSetter.setValue(invPmsg.invEdiSendStsCd, INV_SEND_STS_PROCESSED);
        ZYPEZDItemValueSetter.setValue(invPmsg.invFaxSendStsCd, INV_SEND_STS_PROCESSED);
        ZYPEZDItemValueSetter.setValue(invPmsg.invEmlSendStsCd, INV_SEND_STS_PROCESSED);
        // END [CSA CHANGE]
        ZYPEZDItemValueSetter.setValue(invPmsg.crDrRsnCd, getString(rs, SELECT.CR_DR_RSN_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.crDrRsnSubCd, getString(rs, SELECT.CR_DR_SUB_RSN_CD));
        // START [CSA CHANGE]
        // invPmsg.sysSrcCd.setValue(varConstMap.get(NAAB0030_SYS_SRC_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.sysSrcCd, SYS_SRC.S21_SERVICE);
        // END [CSA CHANGE]
        ZYPEZDItemValueSetter.setValue(invPmsg.pmtTermCashDiscCd, getString(rs, SELECT.PMT_TERM_CASH_DISC_CD));

        // START [CSA CHANGE]
        // invPmsg.dsOrdTpCd.setValue(varConstMap.get(NAAB0030_DS_ORD_TP_CD));
        // invPmsg.dsOrdRsnCd.setValue(varConstMap.get(NAAB0030_DS_ORD_RSN_CD));
        // invPmsg.crCardChrgCpltCd.setValue(BigDecimal.ZERO.toString());
        // Start 2023/1/20 R.Onozuka [QC#60823, MOD]
        //if (ZYPConstant.FLG_ON_Y.equals(getString(rs, SELECT.PMT_CC_FLG))) {
        if (ZYPConstant.FLG_ON_Y.equals(getString(rs, SELECT.PMT_CC_FLG)) && !ZYPConstant.FLG_ON_1.equals(getString(rs, SELECT.DS_CR_CARD_EZCANCELFLAG))) {
        // End 2023/1/20 R.Onozuka [QC#60823, MOD]
            ZYPEZDItemValueSetter.setValue(invPmsg.crCardChrgCpltCd, ZYPConstant.FLG_ON_1);
            // ADD Start 03/28/2016 <QC#3829>
            ZYPEZDItemValueSetter.setValue(invPmsg.crCardCustRefNum, getString(rs, SELECT.CR_CARD_CUST_REF_NUM));
            ZYPEZDItemValueSetter.setValue(invPmsg.crCardTpCd, getString(rs, SELECT.CR_CARD_TP_CD));
            ZYPEZDItemValueSetter.setValue(invPmsg.dsInvExprDt, getString(rs, SELECT.DS_INV_EXPR_DT));
            // ADD End   03/28/2016 <QC#3829>
        } else {
            ZYPEZDItemValueSetter.setValue(invPmsg.crCardChrgCpltCd, ZYPConstant.FLG_OFF_0);
        }
        // END [CSA CHANGE]

        // START 2018/07/18 M.Naito [QC#13309, MOD]
        // START [CSA ADD]
        ZYPEZDItemValueSetter.setValue(invPmsg.dsInvTpCd, getString(rs, SELECT.DS_INV_TP_CD));
        if (SVC_INV_SRC_TP.CONTRACT.equals(getString(rs, SELECT.SVC_INV_SRC_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(invPmsg.srcSysDocNum, getString(rs, SELECT.DS_CONTR_NUM));
        } else if (SVC_INV_SRC_TP.DISPATCH.equals(getString(rs, SELECT.SVC_INV_SRC_TP_CD))) {
            if (hasValue(getString(rs, SELECT.TEMP_ETTL_NUM))) {
                ZYPEZDItemValueSetter.setValue(invPmsg.srcSysDocNum, getString(rs, SELECT.TEMP_ETTL_NUM));
            } else {
                ZYPEZDItemValueSetter.setValue(invPmsg.srcSysDocNum, getString(rs, SELECT.FSR_NUM));
            }
        }
        // END 2018/07/18 M.Naito [QC#13309, MOD]
        ZYPEZDItemValueSetter.setValue(invPmsg.slsRepTocCd, getString(rs, SELECT.SLS_REP_TOC_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.custCareTktNum, getString(rs, SELECT.CUST_CARE_TKT_NUM));
        ZYPEZDItemValueSetter.setValue(invPmsg.billToCustAcctCd, getString(rs, SELECT.BILL_TO_CUST_ACCT_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.svcInvPk, getBigDecimal(rs, SELECT.SVC_INV_PK));
        ZYPEZDItemValueSetter.setValue(invPmsg.dsContrCatgCd, getString(rs, SELECT.DS_CONTR_CATG_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.soldToCustLocCd, getString(rs, SELECT.BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.lineBizTpCd, getString(rs, SELECT.LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.dsBizAreaCd, getString(rs, SELECT.DS_BIZ_AREA_CD));
        ZYPEZDItemValueSetter.setValue(invPmsg.dplyMdseDtlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(invPmsg.billToCtacPsnFirstNm, getString(rs, SELECT.CTAC_PSN_FIRST_NM));
        ZYPEZDItemValueSetter.setValue(invPmsg.billToCtacPsnMidNm, getString(rs, SELECT.CTAC_PSN_MID_NM));
        ZYPEZDItemValueSetter.setValue(invPmsg.billToCtacPsnLastNm, getString(rs, SELECT.CTAC_PSN_LAST_NM));
        String invPrtBatTpCd = null;
        if (SVC_INV_SRC_TP.CONTRACT.equals(getString(rs, SELECT.SVC_INV_SRC_TP_CD))) {
            // START 2018/05/31 K.Kojima [QC#23685,MOD]
            // invPrtBatTpCd = getInvPrtBatTpCd(rs);
            invPrtBatTpCd = getInvPrtBatTpCd(rs, svcInvTMsg);
            // END 2018/05/31 K.Kojima [QC#23685,MOD]
        }
        ZYPEZDItemValueSetter.setValue(invPmsg.invPrtBatTpCd, invPrtBatTpCd);

        BigDecimal contrRnwTotCnt = getContrRnwTotCnt(getString(rs, SELECT.SVC_INV_NUM));
        ZYPEZDItemValueSetter.setValue(invPmsg.contrRnwTotCnt, contrRnwTotCnt);
        // END [CSA ADD]

        // START 2017/02/22 K.Kitachi [QC#17515, ADD]
        String contrInvCmntTxt = getString(rs, SELECT.CONTR_INV_CMNT_TXT);
        ZYPEZDItemValueSetter.setValue(invPmsg.invFirstCmntTxt, S21StringUtil.subStringByLength(contrInvCmntTxt, 0, INV_CMNT_TXT_LENGTH));
        ZYPEZDItemValueSetter.setValue(invPmsg.invScdCmntTxt, S21StringUtil.subStringByLength(contrInvCmntTxt, INV_SCD_CMNT_TXT_START_IDX, INV_CMNT_TXT_LENGTH));
        ZYPEZDItemValueSetter.setValue(invPmsg.invThirdCmntTxt, S21StringUtil.subStringByLength(contrInvCmntTxt, INV_THIRD_CMNT_TXT_START_IDX, INV_CMNT_TXT_LENGTH));
        ZYPEZDItemValueSetter.setValue(invPmsg.invFrthCmntTxt, S21StringUtil.subStringByLength(contrInvCmntTxt, INV_FRTH_CMNT_TXT_START_IDX, INV_CMNT_TXT_LENGTH));
        // END 2017/02/22 K.Kitachi [QC#17515, ADD]

        // START 2017/11/07 K.Kojima [QC#22224,ADD]
        if (ZYPCommonFunc.hasValue(invPmsg.origInvNum)) {
            ZYPEZDItemValueSetter.setValue(invPmsg.crRebilRsnCatgCd, CR_REBIL_RSN_CATG.EXTERNAL);
        }
        // END 2017/11/07 K.Kojima [QC#22224,ADD]

        // START 2020/07/13 K.Kitachi [QC#57304, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(getString(rs, SELECT.LEASE_CMPY_FLG))) {
            ZYPEZDItemValueSetter.setValue(invPmsg.soldToCustLocCd, getString(rs, SELECT.ALT_PAYER_CUST_CD));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToCustCd, getString(rs, SELECT.DS_ACCT_NUM));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToLocNm, getString(rs, SELECT.SELL_TO_LOC_NM));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToAddlLocNm, getString(rs, SELECT.SELL_TO_ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToFirstLineAddr, getString(rs, SELECT.SELL_TO_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToScdLineAddr, getString(rs, SELECT.SELL_TO_SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToThirdLineAddr, getString(rs, SELECT.SELL_TO_THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToFrthLineAddr, getString(rs, SELECT.SELL_TO_FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToCtyAddr, getString(rs, SELECT.SELL_TO_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToCntyNm, getString(rs, SELECT.SELL_TO_CNTY_NM));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToProvNm, getString(rs, SELECT.SELL_TO_PROV_NM));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToStCd, getString(rs, SELECT.SELL_TO_ST_CD));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToPostCd, getString(rs, SELECT.SELL_TO_POST_CD));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToCtryCd, getString(rs, SELECT.SELL_TO_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToFirstRefCmntTxt, getString(rs, SELECT.SELL_TO_FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(invPmsg.sellToScdRefCmntTxt, getString(rs, SELECT.SELL_TO_SCD_REF_CMNT_TXT));
        }
        // END 2020/07/13 K.Kitachi [QC#57304, ADD]

        return invPmsg;
    }

    // START 2016/07/05 T.Kanasaka [QC#9438, MOD]
    private NWZC040002PMsg setNWZC040002PMsg(ResultSet rs, String invBolLineNum) throws SQLException {

        NWZC040002PMsg shipPmsg = new NWZC040002PMsg();
        ZYPEZDItemValueSetter.setValue(shipPmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shipPmsg.invNum, getString(rs, SELECT.SVC_INV_NUM));
//        ZYPEZDItemValueSetter.setValue(shipPmsg.invBolLineNum, DFLT_INV_BOL_LINE_NUM);
        ZYPEZDItemValueSetter.setValue(shipPmsg.invBolLineNum, invBolLineNum);
        // START [CSA DEL]
        // shipPmsg.shipToCustCd.setValue(getString(rs, SELECT.SHIP_TO_CUST_CD));
        // END [CSA DEL]
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipDealSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipDealNetAmt, BigDecimal.ZERO);
        // START 2023/11/10 [QC#61468, MOD]
//        ZYPEZDItemValueSetter.setValue(shipPmsg.shipDealFrtAmt, BigDecimal.ZERO);
        BigDecimal frtAmt = getInvTotDealFrtAmt(getString(rs, SELECT.SVC_INV_NUM));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipDealFrtAmt, frtAmt);
        // END   2023/11/10 [QC#61468, MOD]
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipDealDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipDealHdlgChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.totBolDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.frtTaxPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.frtDealTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipFuncSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipFuncNetAmt, BigDecimal.ZERO);
        // START 2023/11/10 [QC#61468, MOD]
//        ZYPEZDItemValueSetter.setValue(shipPmsg.shipFuncFrtAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipFuncFrtAmt, frtAmt);
        // END   2023/11/10 [QC#61468, MOD]
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipFuncDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipFuncHdlgChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.totBolFuncTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(shipPmsg.frtFuncTaxAmt, BigDecimal.ZERO);

        // START [CSA ADD]
        ZYPEZDItemValueSetter.setValue(shipPmsg.custIssPoNum, getString(rs, SELECT.CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(shipPmsg.ctacPsnPk, getBigDecimal(rs, SELECT.CTAC_PSN_PK));
        // END [CSA ADD]
        // add start 2016/08/05 CSA Defect#12836
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToCustAcctCd, getString(rs, SELECT.SHIP_TO_CUST_ACCT_CD));
        // add end 2016/08/05 CSA Defect#12836
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToCustCd, getString(rs, SELECT.SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToLocNm, getString(rs, SELECT.SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToAddlLocNm, getString(rs, SELECT.SHIP_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToFirstLineAddr, getString(rs, SELECT.SHIP_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToScdLineAddr, getString(rs, SELECT.SHIP_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToThirdLineAddr, getString(rs, SELECT.SHIP_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToFrthLineAddr, getString(rs, SELECT.SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToStCd, getString(rs, SELECT.SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToProvNm, getString(rs, SELECT.SHIP_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToCntyNm, getString(rs, SELECT.SHIP_TO_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToFirstRefCmntTxt, getString(rs, SELECT.SHIP_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToScdRefCmntTxt, getString(rs, SELECT.SHIP_TO_SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToPostCd, getString(rs, SELECT.SHIP_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToCtyAddr, getString(rs, SELECT.SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToCtryCd, getString(rs, SELECT.SHIP_TO_CTRY_CD));

        // add start 2018/08/09 QC#27329-3
        if (INV_TP.CREDIT_MEMO.equals(getString(rs, SELECT.INV_TP_CD)) && hasValue(getString(rs, SELECT.ORIG_SVC_INV_NUM))) {
            setNWZC040002PMsgOrigInv(shipPmsg, getString(rs, SELECT.ORIG_SVC_INV_NUM), getBigDecimal(rs, SELECT.DS_CONTR_BLLG_SCHD_PK));
        }
        // add end 2018/08/09 QC#27329-3

        return shipPmsg;
    }

    // START 2018/05/31 K.Kojima [QC#23685,MOD]
    // private NWZC040003PMsg setNWZC040003PMsg(ResultSet rs, String invBolLineNum, String invLineNum) throws SQLException {
    private NWZC040003PMsg setNWZC040003PMsg(ResultSet rs, String invBolLineNum, String invLineNum, SVC_INV_LINETMsg svcInvLineTMsg) throws SQLException {
    // END 2018/05/31 K.Kojima [QC#23685,MOD]

        NWZC040003PMsg invLinePmsg = new NWZC040003PMsg();
        ZYPEZDItemValueSetter.setValue(invLinePmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invNum, getString(rs, SELECT.SVC_INV_NUM));
//        ZYPEZDItemValueSetter.setValue(invLinePmsg.invBolLineNum, DFLT_INV_BOL_LINE_NUM);
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invBolLineNum, invBolLineNum);
//        ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineNum, getString(rs, SELECT.SVC_INV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineNum, invLineNum);

        // START [CSA CHANGE]
        // invLinePmsg.invLineSubNum.setValue(getString(rs, SELECT.SVC_INV_LINE_ALLOC_NUM));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineSubNum, DFLT_INV_LINE_SUB_TRX_NUM);
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineSubTrxNum, DFLT_INV_LINE_SUB_TRX_NUM);
        // invLinePmsg.slsRepTocCd.setValue(getString(rs, SELECT.TOC_CD));
        // START 2017/11/29 K.Kojima [QC#21918,MOD]
        // ZYPEZDItemValueSetter.setValue(invLinePmsg.slsRepTocCd, getString(rs, SELECT.SLS_REP_TOC_CD));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.slsRepTocCd, getString(rs, SELECT.SLS_REP_TOC_CD_FOR_LINE));
        // END 2017/11/29 K.Kojima [QC#21918,MOD]
        if (SVC_INV_CHRG_TP.ADDITIONAL_CHARGE.equals(getString(rs, SELECT.SVC_INV_CHRG_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(invLinePmsg.mdseCd, getString(rs, SELECT.INTG_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(invLinePmsg.mdseNm, getString(rs, SELECT.INTG_MDSE_NM));
        } else {
            ZYPEZDItemValueSetter.setValue(invLinePmsg.mdseCd, getString(rs, SELECT.MDSE_CD));
            ZYPEZDItemValueSetter.setValue(invLinePmsg.mdseNm, getString(rs, SELECT.MDSE_NM));
        }
        // invLinePmsg.coaProdCd.setValue(getString(rs, SELECT.COA_PROD_CD));
        // END [CSA CHANGE]

        // START [CSA DEL]
        // invLinePmsg.trxCd.setValue(getString(rs, SELECT.TRX_CD));
        // invLinePmsg.trxRsnCd.setValue(getString(rs, SELECT.TRX_RSN_CD));
        // END [CSA DEL]

        ZYPEZDItemValueSetter.setValue(invLinePmsg.ordQty, getBigDecimal(rs, SELECT.SVC_INV_QTY));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.shipQty, getBigDecimal(rs, SELECT.SVC_INV_QTY));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.boQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(invLinePmsg.manPrcFlg, ZYPConstant.FLG_ON_Y);

        // START [CSA CHANGE]
        // invLinePmsg.dealNetUnitPrcAmt, getBigDecimal(rs, SELECT.DEAL_NET_UNIT_PRC_AMT));
        if (SVC_INV_SRC_TP.CONTRACT.equals(getString(rs, SELECT.SVC_INV_SRC_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(invLinePmsg.dealNetUnitPrcAmt, getBigDecimal(rs, SELECT.DEAL_UNIT_PRC_AMT));
        } else if (SVC_INV_SRC_TP.DISPATCH.equals(getString(rs, SELECT.SVC_INV_SRC_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(invLinePmsg.dealNetUnitPrcAmt, getBigDecimal(rs, SELECT.INV_DISP_UNIT_PRC_AMT));
        }
        // END [CSA CHANGE]

        // START 2018/05/31 K.Kojima [QC#23685,MOD]
        // ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineDealTaxAmt, getBigDecimal(rs, SELECT.INV_LINE_DEAL_TAX_AMT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineDealTaxAmt, svcInvLineTMsg.invLineDealTaxAmt);
        // END 2018/05/31 K.Kojima [QC#23685,MOD]
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineDealNetAmt, getBigDecimal(rs, SELECT.INV_LINE_DEAL_NET_AMT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);

        // QC#2783 add funcAmt parameter
        // START [CSA CHANGE]
        // invLinePmsg.funcNetUnitPrcAmt.setValue(getBigDecimal(rs, SELECT.FUNC_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.funcNetUnitPrcAmt, getBigDecimal(rs, SELECT.FUNC_UNIT_PRC_AMT));
        // END [CSA CHANGE]
        // START 2018/05/31 K.Kojima [QC#23685,MOD]
        // ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineFuncTaxAmt, getBigDecimal(rs, SELECT.INV_LINE_FUNC_TAX_AMT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineFuncTaxAmt, svcInvLineTMsg.invLineFuncTaxAmt);
        // END 2018/05/31 K.Kojima [QC#23685,MOD]
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineFuncNetAmt, getBigDecimal(rs, SELECT.INV_LINE_FUNC_NET_AMT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.funcDiscUnitPrcAmt, BigDecimal.ZERO);
        // QC#2783 end
        if (BigDecimal.ZERO.compareTo(invLinePmsg.invLineDealTaxAmt.getValue()) != 0) {
            ZYPEZDItemValueSetter.setValue(invLinePmsg.taxFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(invLinePmsg.taxFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2018/05/31 K.Kojima [QC#23685,MOD]
        // ZYPEZDItemValueSetter.setValue(invLinePmsg.taxPct, getBigDecimal(rs, SELECT.SLS_TAX_RATE));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.taxPct, svcInvLineTMsg.slsTaxRate);
        // END 2018/05/31 K.Kojima [QC#23685,MOD]

        // START [CSA DEL]
        // invLinePmsg.coaAcctCd.setValue(getString(rs, SELECT.COA_ACCT_CD));
        // invLinePmsg.coaProjCd.setValue(getString(rs, SELECT.COA_PROJ_CD));
        // invLinePmsg.crDrRsnSubCd.setValue(getString(rs, SELECT.CR_DR_SUB_RSN_CD));
        // END [CSA DEL]

        //ZYPEZDItemValueSetter.setValue(invLinePmsg.shipCmplCostAmt, BigDecimal.ZERO); // 2017/10/23 QC#20719 Del

        // START [CSA CHANGE]
        // invLinePmsg.dealGrsUnitPrcAmt.setValue(getBigDecimal(rs, SELECT.DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.dealGrsUnitPrcAmt, getBigDecimal(rs, SELECT.DEAL_UNIT_PRC_AMT));
        // END [CSA CHANGE]
        ZYPEZDItemValueSetter.setValue(invLinePmsg.dealGrsTotPrcAmt, getBigDecimal(rs, SELECT.INV_LINE_DEAL_NET_AMT));
        // QC#2783 add funcAmt parameter
        // START [CSA CHANGE]
        // invLinePmsg.funcGrsUnitPrcAmt.setValue(getBigDecimal(rs, SELECT.FUNC_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.funcGrsUnitPrcAmt, getBigDecimal(rs, SELECT.FUNC_UNIT_PRC_AMT));
        // END [CSA CHANGE]
        ZYPEZDItemValueSetter.setValue(invLinePmsg.funcGrsTotPrcAmt, getBigDecimal(rs, SELECT.INV_LINE_FUNC_NET_AMT));
        // QC#2783 end
        ZYPEZDItemValueSetter.setValue(invLinePmsg.setRatioKeepFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(invLinePmsg.svcConfigMstrPk, getBigDecimal(rs, SELECT.SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.dsContrNum, getString(rs, SELECT.DS_CONTR_NUM));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.dsContrSqNum, getString(rs, SELECT.DS_CONTR_SQ_NUM));

        // START [CSA ADD]
        ZYPEZDItemValueSetter.setValue(invLinePmsg.firstBllgAttrbValTxt, getString(rs, SELECT.FIRST_BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.scdBllgAttrbValTxt, getString(rs, SELECT.SCD_BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.thirdBllgAttrbValTxt, getString(rs, SELECT.THIRD_BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.frthBllgAttrbValTxt, getString(rs, SELECT.FRTH_BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.fifthBllgAttrbValTxt, getString(rs, SELECT.FIFTH_BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.sixthBllgAttrbValTxt, getString(rs, SELECT.SIXTH_BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.uomCd, getString(rs, SELECT.UOM_CD));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.svcInvLinePk, getBigDecimal(rs, SELECT.SVC_INV_LINE_PK));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.dsContrDtlPk, getBigDecimal(rs, SELECT.DS_CONTR_DTL_PK));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.mdlId, getBigDecimal(rs, SELECT.MDL_ID));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.svcInvChrgTpCd, getString(rs, SELECT.SVC_INV_CHRG_TP_CD));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.bllgPerFromDt, getString(rs, SELECT.BLLG_PER_FROM_DT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.bllgPerThruDt, getString(rs, SELECT.BLLG_PER_THRU_DT));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.bllgCopyQty, getBigDecimal(rs, SELECT.BLLG_COPY_QTY));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.taxCalcGeoCd, getString(rs, SELECT.TAX_CALC_GEO_CD));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.custIssPoNum, getString(rs, SELECT.CUST_ISS_PO_NUM_2));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.custIssPoDt, getString(rs, SELECT.CUST_ISS_PO_DT_2));
        ZYPEZDItemValueSetter.setValue(invLinePmsg.invDplyQty, getBigDecimal(rs, SELECT.INV_DISP_QTY));
        // END [CSA ADD]

        // START 2018/07/10 K.Kitachi [QC#22788, ADD]
        ZYPEZDItemValueSetter.setValue(invLinePmsg.svcMachMstrPk, getBigDecimal(rs, SELECT.SVC_MACH_MSTR_PK));
        // END 2018/07/10 K.Kitachi [QC#22788, ADD]
        // START 2023/11/10 [QC#61468, ADD]
        if (SVC_INV_CHRG_TP.FREIGHT_CHARGE.equals(getString(rs, SELECT.SVC_INV_CHRG_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(invLinePmsg.invLineCatgCd, INV_LINE_CATG.FREIGHT);
        }
        // END   2023/11/10 [QC#61468, ADD]

        return invLinePmsg;
    }

    // START [CSA ADD]
    private List<NWZC040005PMsg> setNWZC040005PMsg(BigDecimal svcInvLinePk, List<NWZC040005PMsg> taxDtlPMsgList, String invBolLineNum, String invLineNum) {

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("svcInvLinePk", svcInvLinePk);

        List<Map<String, Object>> taxList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcInvLineTaxDtlList", prmMap);

        for (Map<String, Object> taxDtl : taxList) {
            NWZC040005PMsg taxDtlPMsg = new NWZC040005PMsg();
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.invNum, getString(taxDtl, SELECT.SVC_INV_NUM));
//            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.invBolLineNum, DFLT_INV_BOL_LINE_NUM);
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.invBolLineNum, invBolLineNum);
//            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.invLineNum, getString(taxDtl, SELECT.SVC_INV_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.invLineNum, invLineNum);
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.invLineSubNum, DFLT_INV_LINE_SUB_TRX_NUM);
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.invTrxLineSubNum, DFLT_INV_LINE_SUB_TRX_NUM);
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.dsSlsTaxTpCd, getString(taxDtl, SELECT.DS_SLS_TAX_TP_CD));
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.funcSlsTaxAmt, getBigDecimal(taxDtl, SELECT.FUNC_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.dealSlsTaxAmt, getBigDecimal(taxDtl, SELECT.DEAL_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.slsTaxPct, getBigDecimal(taxDtl, SELECT.SLS_TAX_RATE_2));
            // START 2017/08/07 M.Kidokoro [QC#20073, ADD]
            ZYPEZDItemValueSetter.setValue(taxDtlPMsg.taxAreaId, getString(taxDtl, SELECT.TAX_AREA_ID));
            // END 2017/08/07 M.Kidokoro [QC#20073, ADD]
            taxDtlPMsgList.add(taxDtlPMsg);
        }
        return taxDtlPMsgList;
    }

    private List<NWZC040007PMsg> setNWZC040007PMsg(BigDecimal svcInvLinePk, List<NWZC040007PMsg> invSlsCrPMsgList, String invBolLineNum, String invLineNum) {

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("svcInvLinePk", svcInvLinePk);

        List<Map<String, Object>> alcList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcInvLineAllocList", prmMap);

        for (Map<String, Object> alcDtl : alcList) {

            NWZC040007PMsg invSlsCrPMsg = new NWZC040007PMsg();

            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invNum, getString(alcDtl, SELECT.SVC_INV_NUM));
//            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invBolLineNum, DFLT_INV_BOL_LINE_NUM);
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invBolLineNum, invBolLineNum);
//            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invLineNum, getString(alcDtl, SELECT.SVC_INV_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invLineNum, invLineNum);
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invLineSubNum, DFLT_INV_LINE_SUB_TRX_NUM);
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invLineSubTrxNum, DFLT_INV_LINE_SUB_TRX_NUM);
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.mdseCd, getString(alcDtl, SELECT.INTG_MDSE_CD));
            // START 2016/06/29 T.Kanasaka [QC#10735, MOD]
//            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invLineSplTpCd, getString(alcDtl, SELECT.INV_PRINT_FLG_CRD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invLineSplTpCd, getString(alcDtl, SELECT.INV_LINE_SPL_TP_CD));
            // END 2016/06/29 T.Kanasaka [QC#10735, MOD]
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.invLineSplPct, getBigDecimal(alcDtl, SELECT.SLS_ALLOC_RATE));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.slsRepTocCd, getString(alcDtl, SELECT.TOC_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.slsRepCrPct, this.numConstMap.get(DEF_SLS_REP_CR_PCT));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.dealSlsCrAmt, getBigDecimal(alcDtl, SELECT.INV_LINE_DEAL_NET_AMT_2));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.funcSlsCrAmt, getBigDecimal(alcDtl, SELECT.INV_LINE_FUNC_NET_AMT_2));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.dealCcyCd, getString(alcDtl, SELECT.CCY_CD));
            // Mod Start 2016/11/02 <QC#12540>
            if (INV_TP.CREDIT_MEMO.equals(getString(alcDtl, SELECT.INV_TP_CD))) {
                ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.dfrdAcctgRuleCd, DFRD_ACCTG_RULE.IMMEDIATE);
            } else {
                String chrgTpCd = getString(alcDtl, SELECT.SVC_INV_CHRG_TP_CD);
                String bllgTmgCd = null;
                if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(chrgTpCd)) {
                    bllgTmgCd = getString(alcDtl, SELECT.BASE_BLLG_TMG_CD);
                } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(chrgTpCd)) {
                    bllgTmgCd = getString(alcDtl, SELECT.MTR_BLLG_TMG_CD);
                } else {
                    bllgTmgCd = BLLG_TMG_TP.ARREARS;
                }
                if (BLLG_TMG_TP.ARREARS.equals(bllgTmgCd)) {
                    ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.dfrdAcctgRuleCd, DFRD_ACCTG_RULE.IMMEDIATE);
                } else {
                    ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.dfrdAcctgRuleCd, getString(alcDtl, SELECT.DFRD_ACCTG_RULE_CD));
                }
            }
            // Mod End   2016/11/02 <QC#12540>
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.dfrdAcctgRuleDurnAot, getBigDecimal(alcDtl, SELECT.DFRD_ACCTG_RULE_DURN_AOT));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.slsRepBrCd, getString(alcDtl, SELECT.COA_BR_CD_ORG));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.coaCmpyCd, getString(alcDtl, SELECT.COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.coaAfflCd, getString(alcDtl, SELECT.COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.coaBrCd, getString(alcDtl, SELECT.COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.coaChCd, getString(alcDtl, SELECT.COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.coaCcCd, getString(alcDtl, SELECT.COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.coaAcctCd, getString(alcDtl, SELECT.COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.coaProdCd, getString(alcDtl, SELECT.COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.coaProjCd, getString(alcDtl, SELECT.COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.coaExtnCd, getString(alcDtl, SELECT.COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.trxCd, getString(alcDtl, SELECT.TRX_CD));
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.trxRsnCd, getString(alcDtl, SELECT.TRX_RSN_CD));
            // Add Start 2016/11/10 <QC#15789>
            ZYPEZDItemValueSetter.setValue(invSlsCrPMsg.ajeInvLineAllocCd, getString(alcDtl, SELECT.AJE_INV_LINE_ALLOC_CD));
            // Add Start 2016/11/10 <QC#15789>
            invSlsCrPMsgList.add(invSlsCrPMsg);
        }
        return invSlsCrPMsgList;
    }
    // END 2016/07/05 T.Kanasaka [QC#9438, MOD]

    // END [CSA ADD]

    // START [CSA ADD]
    /** 1. Get Contract Version */
    private BigDecimal getContrRnwTotCnt(String svcInvNum) {

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("svcInvNum", svcInvNum);

        BigDecimal contrRnwTotCnt = (BigDecimal) ssmBatchClient.queryObject("getContrRnwTotCnt", prmMap);
        if (contrRnwTotCnt == null) {
            contrRnwTotCnt = BigDecimal.ZERO;
        }
        return contrRnwTotCnt;
    }

    /** 2. Get Invoice Print Batch Type Code */
    // START 2018/05/31 K.Kojima [QC#23685,MOD]
    // private String getInvPrtBatTpCd(ResultSet rs) throws SQLException {
    private String getInvPrtBatTpCd(ResultSet rs, SVC_INVTMsg svcInvTMsg) throws SQLException {
    // END 2018/05/31 K.Kojima [QC#23685,MOD]

        String svcInvNum = getString(rs, SELECT.SVC_INV_NUM);

        // START 2019/03/01 S.Kitamura [QC#30496,DEL]
        // if (checkNegativeMeter(svcInvNum)) {
        //    return INV_PRT_BAT_TP.NEGATIVE_READS;
        // }
        // END 2019/03/01 S.Kitamura [QC#30496,DEL]
        if (checkReviewAmtDiff(svcInvNum)) {
            return INV_PRT_BAT_TP.NEEDS_REVIEW;
        }
        // START 2016/02/23 T.Aoyagi [QC2885, MOD]
//        if (checkConsolidated(getString(rs, SELECT.BILL_TO_CUST_CD))) {
        if (checkConsolidated(rs, getString(rs, SELECT.BILL_TO_CUST_CD))) {
        // END 2016/02/23 T.Aoyagi [QC2885, MOD]
            return INV_PRT_BAT_TP.CONSOLIDATED;
        }
        // START 2018/05/31 K.Kojima [QC#23685,MOD]
        // if (checkNoPrint(rs)) {
        if (checkNoPrint(rs, svcInvTMsg)) {
        // END 2018/05/31 K.Kojima [QC#23685,MOD]
            return INV_PRT_BAT_TP.NO_PRINT;
        }
        // START 2018/05/31 K.Kojima [QC#23685,MOD]
        // if (checkReviewAmtLimit(rs)) {
        if (checkReviewAmtLimit(rs, svcInvTMsg)) {
        // END 2018/05/31 K.Kojima [QC#23685,MOD]
            return INV_PRT_BAT_TP.NEEDS_REVIEW;
        }
        if (checkSinglePage(rs)) {
            return INV_PRT_BAT_TP.SINGLE_PAGE;
        } else {
            return INV_PRT_BAT_TP.MULTIPLE_PAGE;
        }
    }

    /** 2.1. Check Negative Meter */
    // START 2019/03/01 S.Kitamura [QC#30496,DEL]
//    private boolean checkNegativeMeter(String svcInvNum) {
//
//        boolean returnFlg = false;
//
//        Map<String, String> prmMap = new HashMap<String, String>();
//        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
//        prmMap.put("svcInvNum", svcInvNum);
//        BigDecimal negativeMeterCnt = (BigDecimal) ssmBatchClient.queryObject("getNegativeMeterCnt", prmMap);
//        if (negativeMeterCnt.compareTo(BigDecimal.ZERO) > 0) {
//            returnFlg = true;
//        }
//        return returnFlg;
//    }
    // END 2019/03/01 S.Kitamura [QC#30496,DEL]
    
    /** 2.2. Check Review (Amount difference) */
    private boolean checkReviewAmtDiff(String svcInvNum) {

        boolean returnFlg = false;

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("svcInvNum", svcInvNum);
        BigDecimal reviewAmtDiffCnt = (BigDecimal) ssmBatchClient.queryObject("getReviewAmtDiffCnt", prmMap);
        if (reviewAmtDiffCnt.compareTo(BigDecimal.ZERO) > 0) {
            returnFlg = true;
        }
        return returnFlg;
    }

    /** 2.3. Check Consolidated */
    // START 2016/02/23 T.Aoyagi [QC2885, MOD]
//    private boolean checkConsolidated(String billToCustCd) {
    private boolean checkConsolidated(ResultSet rs, String billToCustCd) throws SQLException {
    // END 2016/02/23 T.Aoyagi [QC2885, MOD]
        boolean returnFlg = false;
        String custBllgTpCd = null;

        NMZC610001PMsg inPmsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(inPmsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inPmsg.xxModeCd, PROCESS_MODE_INVOICE);
        // START 2016/02/23 T.Aoyagi [QC2885, MOD]
//        ZYPEZDItemValueSetter.setValue(inPmsg.custInvSrcCd, CUST_INV_SRC.SERVICE_CONTRACT);
        ZYPEZDItemValueSetter.setValue(inPmsg.custInvSrcCd, getString(rs, SELECT.CUST_INV_SRC_CD));
        // END 2016/02/23 T.Aoyagi [QC2885, MOD]
        ZYPEZDItemValueSetter.setValue(inPmsg.billToCustCd, billToCustCd);

        NMZC610001 exApi = new NMZC610001();
        exApi.execute(inPmsg, ONBATCH_TYPE.BATCH);

        if (inPmsg.xxMsgIdList.getValidCount() == 0) {
            custBllgTpCd = inPmsg.InvoiceRuleList.no(0).custBllgTpCd.getValue();
            if (CUST_BLLG_TP.CONSOLIDATED.equals(custBllgTpCd)) {
                returnFlg = true;
            }
        }

        return returnFlg;
    }

    /** 2.4. Check NoPrint */
    // START 2018/05/31 K.Kojima [QC#23685,MOD]
    // private boolean checkNoPrint(ResultSet rs) throws SQLException {
    private boolean checkNoPrint(ResultSet rs, SVC_INVTMsg svcInvTMsg) throws SQLException {
    // END 2018/05/31 K.Kojima [QC#23685,MOD]

        boolean returnFlg = false;

        if (ZYPConstant.FLG_OFF_N.equals(getString(rs, SELECT.INV_PRINT_FLG_DCE))) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(getString(rs, SELECT.DS_ACCT_DLR_CD)) && ZYPConstant.FLG_OFF_N.equals(getString(rs, SELECT.INV_PRINT_FLG_DIT))) {
            return true;
        }
        if (INV_TP.CREDIT_MEMO.equals(getString(rs, SELECT.INV_TP_CD))) {
            return true;
        }
        // START 2018/05/31 K.Kojima [QC#23685,MOD]
        // BigDecimal invTotDealAmt = getBigDecimal(rs, SELECT.INV_TOT_DEAL_NET_AMT).add(getBigDecimal(rs, SELECT.INV_TOT_DEAL_TAX_AMT));
        BigDecimal invTotDealAmt = getBigDecimal(rs, SELECT.INV_TOT_DEAL_NET_AMT).add(svcInvTMsg.invTotDealTaxAmt.getValue());
        // END 2018/05/31 K.Kojima [QC#23685,MOD]
        BigDecimal svcInvNoPrintAmt = this.numConstMap.get(INV_PRT_TOT_LMT);
        if (invTotDealAmt.compareTo(svcInvNoPrintAmt) < 0) {
            return true;
        }
        return returnFlg;
    }

    /** 2.5. Check Review (Amount Limit) */
    // START 2018/05/31 K.Kojima [QC#23685,MOD]
    // private boolean checkReviewAmtLimit(ResultSet rs) throws SQLException {
    private boolean checkReviewAmtLimit(ResultSet rs, SVC_INVTMsg svcInvTMsg) throws SQLException {
    // END 2018/05/31 K.Kojima [QC#23685,MOD]

        boolean returnFlg = false;

        // START 2021/03/05 R.Mercado [QC#58471,DELETE]
        // START 2018/05/31 K.Kojima [QC#23685,MOD]
        // BigDecimal invTotDealAmt = getBigDecimal(rs, SELECT.INV_TOT_DEAL_NET_AMT).add(getBigDecimal(rs, SELECT.INV_TOT_DEAL_TAX_AMT));
        // BigDecimal invTotDealAmt = getBigDecimal(rs, SELECT.INV_TOT_DEAL_NET_AMT).add(svcInvTMsg.invTotDealTaxAmt.getValue());
        // END 2018/05/31 K.Kojima [QC#23685,MOD]
        // BigDecimal svcInvNoPrintAmt = this.numConstMap.get(BLLG_PRVW_AVAL_MAX_PRC);
        // if (invTotDealAmt.compareTo(svcInvNoPrintAmt) > 0) {
        //      return true;
        // }
        // END 2021/03/05 R.Mercado [QC#58471,DELETE]

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("svcInvNum", getString(rs, SELECT.SVC_INV_NUM));
        if (DS_CONTR_CATG.FLEET.equals(getString(rs, SELECT.DS_CONTR_CATG_CD))) {
            prmMap.put("svcInvLineTpCd", SVC_INV_LINE_TP.FLEET);
        } else {
            prmMap.put("svcInvLineTpCd", SVC_INV_LINE_TP.MACHINE);
        }
        prmMap.put("svcInvChrgTpCdMc", SVC_INV_CHRG_TP.METER_CHARGE);
        BigDecimal reviewAmtDiffCnt = (BigDecimal) ssmBatchClient.queryObject("getReviewMeterAmtDiffCnt", prmMap);

        if (reviewAmtDiffCnt.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }

        return returnFlg;
    }

    /** 2.6. Check SinglePage */
    private boolean checkSinglePage(ResultSet rs) throws SQLException {

        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("svcInvNum", getString(rs, SELECT.SVC_INV_NUM));
        if (DS_CONTR_CATG.FLEET.equals(getString(rs, SELECT.DS_CONTR_CATG_CD))) {
            prmMap.put("svcInvLineTpCd", SVC_INV_LINE_TP.FLEET);
        }
        // START 2016/03/22 T.Kanasaka [QC#5727, ADD]
        prmMap.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        // END 2016/03/22 T.Kanasaka [QC#5727, ADD]
        Map<String, Object> invLineCnt = (Map<String, Object>) ssmBatchClient.queryObject("getInvLineCnt", prmMap);
        // START 2016/03/24 T.Kanasaka [QC#5992, MOD]
        if (invLineCnt == null || invLineCnt.size() == 0) {
        // END 2016/03/24 T.Kanasaka [QC#5992, MOD]
            return false;
        }

        BigDecimal dtlLineCnt = BigDecimal.ZERO;
        BigDecimal serCnt = getBigDecimal(invLineCnt, SELECT.SER_CNT);
        BigDecimal tieCnt = getBigDecimal(invLineCnt, SELECT.TIE_CNT);
        BigDecimal bcgCnt = getBigDecimal(invLineCnt, SELECT.BCG_CNT);
        BigDecimal pgmCnt = getBigDecimal(invLineCnt, SELECT.PGM_CNT);

        if (DS_CONTR_CATG.FLEET.equals(getString(rs, SELECT.DS_CONTR_CATG_CD))) {
            dtlLineCnt = serCnt.multiply(getBigDecimal(pgLyotMap, SELECT.SERIAL_SEP_FL));
            dtlLineCnt = dtlLineCnt.add(serCnt.multiply(getBigDecimal(pgLyotMap, SELECT.SERIAL_CONTENT)));
            dtlLineCnt = dtlLineCnt.add(tieCnt.multiply(getBigDecimal(pgLyotMap, SELECT.CHARGE_CONTENT)));
            dtlLineCnt = dtlLineCnt.add(bcgCnt.multiply(getBigDecimal(pgLyotMap, SELECT.CHARGE_CONTENT)));
            dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.SRSUM_SEP_FL));
            dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.SERIAL_TIT));
            dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.CHARGE_TIT));
        } else {
            dtlLineCnt = serCnt.multiply(getBigDecimal(pgLyotMap, SELECT.SERIAL_SEP_NF));
            dtlLineCnt = dtlLineCnt.add(serCnt.multiply(getBigDecimal(pgLyotMap, SELECT.SERIAL_CONTENT)));
            dtlLineCnt = dtlLineCnt.add(serCnt.multiply(getBigDecimal(pgLyotMap, SELECT.SRSUM_SEP_NF)));
            dtlLineCnt = dtlLineCnt.add(serCnt.multiply(getBigDecimal(pgLyotMap, SELECT.SERIAL_TIT)));
            dtlLineCnt = dtlLineCnt.add(tieCnt.multiply(getBigDecimal(pgLyotMap, SELECT.CHARGE_CONTENT)));
            dtlLineCnt = dtlLineCnt.add(bcgCnt.multiply(getBigDecimal(pgLyotMap, SELECT.CHARGE_CONTENT)));
            dtlLineCnt = dtlLineCnt.add(serCnt.multiply(getBigDecimal(pgLyotMap, SELECT.CHARGE_TIT)));
        }
        dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.PROG_BEF_TOT));
        dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.PROG_TOT));
        dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.PROG_STOT_SEP));
        dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.PROG_MTOT_SEP));
        dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.PROG_SEP));
        dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.PROG_NAME));
        dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.PROG_DESC));
        dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.PROG_DESC_SEP));
        dtlLineCnt = dtlLineCnt.add(getBigDecimal(pgLyotMap, SELECT.PROG_DESC_MOD_SEP));

        if (dtlLineCnt.compareTo(getBigDecimal(pgLyotMap, SELECT.DETAIL_FIT)) < 0 && pgmCnt.compareTo(getBigDecimal(pgLyotMap, SELECT.SUMMARY_FIT)) < 0) {
            return true;
        } else {
            return false;
        }
    }

    // END [CSA ADD]

    private boolean isErr(String svcInvNum, List<NWZC040001PMsg> invPmsgList, List<NWZC040002PMsg> shipPmsgList, List<NWZC040003PMsg> invLinePmsgList //
            , List<NWZC040005PMsg> taxDtlPMsgList, List<NWZC040007PMsg> invSlsCrPMsgList) {

        boolean errFlg = false;

        for (NWZC040001PMsg pMsg : invPmsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(svcInvNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        for (NWZC040002PMsg pMsg : shipPmsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(svcInvNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        for (NWZC040003PMsg pMsg : invLinePmsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(svcInvNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        // START [CSA ADD]
        for (NWZC040005PMsg pMsg : taxDtlPMsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(svcInvNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        for (NWZC040007PMsg pMsg : invSlsCrPMsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(svcInvNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        // END [CSA ADD]
        return errFlg;
    }

    private boolean setErrMsg(String svcInvNum, Object pmsg) {
        boolean isErr = false;
        if (pmsg instanceof NWZC040001PMsg) {
            NWZC040001PMsg msg = (NWZC040001PMsg) pmsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(svcInvNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        } else if (pmsg instanceof NWZC040002PMsg) {
            NWZC040002PMsg msg = (NWZC040002PMsg) pmsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(svcInvNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        } else if (pmsg instanceof NWZC040003PMsg) {
            NWZC040003PMsg msg = (NWZC040003PMsg) pmsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(svcInvNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        }
        // START [CSA ADD]
        if (pmsg instanceof NWZC040005PMsg) {
            NWZC040005PMsg msg = (NWZC040005PMsg) pmsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(svcInvNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        } else if (pmsg instanceof NWZC040007PMsg) {
            NWZC040007PMsg msg = (NWZC040007PMsg) pmsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(svcInvNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        }
        // END [CSA ADD]
        return isErr;
    }

    private void setErrMsg(String svcInvNum, String msgId, String... msgParams) {
        S21InfoLogOutput.println(msgId, msgParams);
        if (msgMap.containsKey(svcInvNum)) {
            List<String> msgList = (List<String>) msgMap.get(svcInvNum);
            msgList.add(S21MessageFunc.clspGetMessage(msgId, msgParams));
            msgMap.put(svcInvNum, msgList);
        } else {
            List<String> msgList = new ArrayList<String>();
            msgList.add(S21MessageFunc.clspGetMessage(msgId, msgParams));
            msgMap.put(svcInvNum, msgList);
        }
    }

    private boolean isNextInv(NWZC040003PMsg newPmsg, NWZC040003PMsg oldPmsg) {
        if (!ZYPCommonFunc.hasValue(newPmsg.invNum) && !ZYPCommonFunc.hasValue(oldPmsg.invNum)) {
            return false;
        }
        if ((!ZYPCommonFunc.hasValue(newPmsg.invNum) && ZYPCommonFunc.hasValue(oldPmsg.invNum)) || (ZYPCommonFunc.hasValue(newPmsg.invNum) && !ZYPCommonFunc.hasValue(oldPmsg.invNum))) {
            return true;
        }
        if (newPmsg.invNum.getValue().equals(oldPmsg.invNum.getValue())) {
            return false;
        }
        return true;
    }

    private void clearList(List<NWZC040001PMsg> invPmsgList, List<NWZC040002PMsg> shipPmsgList, List<NWZC040003PMsg> invLinePmsgList //
            , List<NWZC040005PMsg> taxDtlPMsgList, List<NWZC040007PMsg> invSlsCrPMsgList) {
        invPmsgList.clear();
        shipPmsgList.clear();
        invLinePmsgList.clear();
        taxDtlPMsgList.clear();
        invSlsCrPMsgList.clear();
    }

    @Override
    protected void termRoutine() {
        sendEmail();
        if (!msgMap.isEmpty()) {
            termCd = TERM_CD.WARNING_END;
        }
        // line information
        S21InfoLogOutput.println(MSG_ERR_LINE + lineErrCnt);
        S21InfoLogOutput.println(MSG_NRML_LINE + lineNormCnt);
        // header information
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);
        // START 2019/03/18 S.Kitamura [QC#30736,ADD]
        if (errCnt > 0) {
            String monthEndFlg = ZYPCodeDataUtil.getVarCharConstValue(NSBB0100_MONTH_END_MODE, glblCmpyCd);
            if (ZYPConstant.FLG_ON_Y.equals(monthEndFlg)) {
                // START 2019/12/02 [QC#54899,ADD]
                commit();
                // END 2019/12/02 [QC#54899,ADD]
                S21InfoLogOutput.println(NSBM0189E);
                throw new S21AbendException(NSBM0189E);
            }
        }
        // END 2019/03/18 S.Kitamura [QC#30736,ADD]
    }

    /**
     * Send email
     */
    private void sendEmail() {

        if (msgMap.isEmpty()) {
            return;
        }

        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        fromGrp.setMailKey1(MAIL_FROM_GRP);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(glblCmpyCd);

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailGroup toGrp = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            List<S21MailAddress> toAddrList = toGrp.getMailAddress();
            if (!toAddrList.isEmpty()) {

                mail.setToAddressList(toAddrList);

                S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);

                if (ZYPCommonFunc.hasValue(tmpl.getSubject())) {

                    String newLine = System.getProperty("line.separator");

                    StringBuilder msgBuf = new StringBuilder();
                    Set<Map.Entry<String, Object>> msgSet = msgMap.entrySet();
                    Iterator<Map.Entry<String, Object>> msgSetIt = msgSet.iterator();

                    while (msgSetIt.hasNext()) {
                        Map.Entry<String, Object> entry = msgSetIt.next();
                        // set invoice header message
                        String svcInvNum = entry.getKey();
                        msgBuf.append(ML_MSG_SVC_INV);
                        msgBuf.append(svcInvNum);
                        msgBuf.append(newLine);

                        List<String> msgList = (List<String>) entry.getValue();
                        // set error message
                        for (String msg : msgList) {
                            msgBuf.append(SPACE);
                            msgBuf.append(ML_MSG_ERR);
                            msgBuf.append(msg);
                            msgBuf.append(newLine);
                        }
                        msgBuf.append(newLine);
                    }
                    SimpleDateFormat errTmFmt = new SimpleDateFormat(DATE_TIME_PATTERN_FOR_MAIL);

                    tmpl.setTemplateParameter("batchId", BUSINESS_ID);
                    tmpl.setTemplateParameter("errDate", errTmFmt.format(new Date()));
                    tmpl.setTemplateParameter("errMsg", msgBuf.toString());

                    mail.setMailTemplate(tmpl);
                    mail.postMail();
                } else {
                    throw new S21AbendException(NSBM0077E, new String[] {MAIL_TEMPLATE_ID });
                }
            } else {
                throw new S21AbendException(NSBM0076E, new String[] {MAIL_GROUP_ID_TO });
            }
        } else {
            throw new S21AbendException(NSBM0078E, new String[] {MAIL_GROUP_ID_FROM, MAIL_FROM_GRP });
        }
    }

    private static BigDecimal getBigDecimal(ResultSet rs, SELECT select) throws SQLException {
        return rs.getBigDecimal(select.toString());
    }

    private static String getString(ResultSet rs, SELECT select) throws SQLException {
        return nullToEmpty(rs.getString(select.toString()));
    }

    // START [CSA ADD]
    private static BigDecimal getBigDecimal(Map<String, Object> map, SELECT select) {
        return (BigDecimal) map.get(select.toString());
    }

    private static String getString(Map<String, Object> map, SELECT select) {
        return nullToEmpty((String) map.get(select.toString()));
    }

    // END [CSA ADD]

    private static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    // START 2018/05/31 K.Kojima [QC#23685,ADD]
    private void getInitData() {
        this.funcCcyAftDeclPntDigitNum = BigDecimal.valueOf(2);
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpyTMsg();
        if (glblCmpyTMsg != null) {
            CCYTMsg funcCcyTMsg = getCcyTMsg(glblCmpyTMsg.stdCcyCd.getValue());
            if (funcCcyTMsg != null) {
                this.funcCcyAftDeclPntDigitNum = funcCcyTMsg.aftDeclPntDigitNum.getValue();
            }
        }
    }

    private boolean setTax(BigDecimal svcInvPk, String svcInvNum, String dsInvTpCd, String svcInvSrcTpCd, String curUpTm, String curUpTmZn) {
        //TODO
        S21InfoLogOutput.println("[TestLog][setTax] START");

        if (!SVC_INV_SRC_TP.CONTRACT.equals(svcInvSrcTpCd)) {
            return true;
        }

        DS_INV_TPTMsg dsInvTpTMsg = getDsInvTpTMsg(dsInvTpCd);
        if (dsInvTpTMsg == null || !dsInvTpTMsg.taxCalcFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            return true;
        }

        List<SVC_INV_LINETMsg> svcInvLineTMsgList = new ArrayList<SVC_INV_LINETMsg>();
        List<SVC_INV_LINE_ALLOCTMsg> svcInvLineAllocTMsgList = new ArrayList<SVC_INV_LINE_ALLOCTMsg>();
        List<SVC_INV_LINE_TAX_DTLTMsg> svcInvLineTaxDtlTMsgList = new ArrayList<SVC_INV_LINE_TAX_DTLTMsg>();
        List<DS_CONTR_BLLG_SCHDTMsg> dsContrBllgSchdTMsgList = new ArrayList<DS_CONTR_BLLG_SCHDTMsg>();
        BigDecimal invTotDealTaxAmt = BigDecimal.ZERO;
        BigDecimal invTotFuncTaxAmt = BigDecimal.ZERO;

        SVC_INVTMsg svcInvTMsg = getSvcInvTMsgForUpdate(svcInvPk);
        if (svcInvTMsg == null) {
            return false;
        }
        DS_CONTRTMsg dsContrTMsg = getDsContrTMsg(svcInvTMsg.dsContrPk.getValue());
        if (dsContrTMsg == null) {
            return false;
        }
        ACCT_DLY_ACTL_EXCH_RATESTMsg acctDlyActlExchRatesTMsg = getAcctDlyActlExchRates(dsContrTMsg.ccyCd.getValue(), this.slsDt);
        if (acctDlyActlExchRatesTMsg == null) {
            return false;
        }
        CCYTMsg dealCcyTMsg = getCcyTMsg(dsContrTMsg.ccyCd.getValue());
        if (dealCcyTMsg == null) {
            return false;
        }

        BigDecimal actlExchRate = acctDlyActlExchRatesTMsg.actlExchRate.getValue();
        String dealCcyAcctArthTpCd = dealCcyTMsg.acctArthTpCd.getValue();
        BigDecimal dealCcyAftDeclPntDigitNum = dealCcyTMsg.aftDeclPntDigitNum.getValue();

        SVC_INV_LINETMsgArray svcInvLineTMsgArray = getSvcInvLineTMsgArrayForUpdate(svcInvNum);
        for (int i = 0; i < svcInvLineTMsgArray.getValidCount(); i++) {
            SVC_INV_LINETMsg svcInvLineTMsg = svcInvLineTMsgArray.no(i);
            if (!ZYPCommonFunc.hasValue(svcInvLineTMsg.invLineFuncNetAmt)) {
                continue;
            }
            if (svcInvLineTMsg.invLineFuncNetAmt.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }

            DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlTMsg(svcInvLineTMsg.dsContrDtlPk.getValue());

            //TODO
            S21InfoLogOutput.println("[TestLog][setTax] NWZC036101 START");

            // Call Tax Calculation API
            NWZC036101PMsg taxApiPMsg = null;
            if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvLineTMsg.svcInvChrgTpCd.getValue())) {
                taxApiPMsg = callTaxCalcAPIForBase(svcInvTMsg, svcInvLineTMsg, dsContrDtlTMsg);
            } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvLineTMsg.svcInvChrgTpCd.getValue())) {
                taxApiPMsg = callTaxCalcAPIForUsg(svcInvTMsg, svcInvLineTMsg, dsContrDtlTMsg);
            } else if (SVC_INV_CHRG_TP.ADDITIONAL_CHARGE.equals(svcInvLineTMsg.svcInvChrgTpCd.getValue())) {
                SVC_INV_LINETMsg prntSvcInvLineTMsg = getSvcInvLineTMsg(svcInvLineTMsg.prntSvcInvLinePk.getValue());
                taxApiPMsg = callTaxCalcAPIForAddl(svcInvTMsg, svcInvLineTMsg, dsContrDtlTMsg, prntSvcInvLineTMsg);
            // START 2023/11/10 [QC#61468, MOD]
//            }
            } else if (SVC_INV_CHRG_TP.FREIGHT_CHARGE.equals(svcInvLineTMsg.svcInvChrgTpCd.getValue())) {
                taxApiPMsg = callTaxCalcAPIForFreight(svcInvTMsg, svcInvLineTMsg, dsContrDtlTMsg);
            }
            // END   2023/11/10 [QC#61468, MOD]

            //TODO
            S21InfoLogOutput.println("[TestLog][setTax] NWZC036101 END");

            if (S21ApiUtil.isXxMsgId(taxApiPMsg)) {
                return false;
            }
            String taxCalcGeoCd = taxApiPMsg.taxCalculateOutputLine.no(0).taxAreaId.getValue();
            BigDecimal slsTaxRate = getTaxRate(taxApiPMsg);
            BigDecimal invLineFuncTaxAmt = getTaxFuncAmt(taxApiPMsg).setScale(this.funcCcyAftDeclPntDigitNum.intValueExact(), RoundingMode.HALF_UP);
            BigDecimal invLineDealTaxAmt = exchToDealAmt(actlExchRate, dealCcyAcctArthTpCd, invLineFuncTaxAmt, dealCcyAftDeclPntDigitNum);
            ZYPEZDItemValueSetter.setValue(svcInvLineTMsg.slsTaxRate, slsTaxRate);
            ZYPEZDItemValueSetter.setValue(svcInvLineTMsg.invLineDealTaxAmt, invLineDealTaxAmt);
            ZYPEZDItemValueSetter.setValue(svcInvLineTMsg.invLineFuncTaxAmt, invLineFuncTaxAmt);
            ZYPEZDItemValueSetter.setValue(svcInvLineTMsg.taxCalcGeoCd, taxCalcGeoCd);
            svcInvLineTMsgList.add(svcInvLineTMsg);

            invTotDealTaxAmt = invTotDealTaxAmt.add(invLineDealTaxAmt);
            invTotFuncTaxAmt = invTotFuncTaxAmt.add(invLineFuncTaxAmt);

            // SVC_INV_LINE_ALLOC
            createSvcInvLineAlloc(svcInvLineAllocTMsgList, svcInvLineTMsg.svcInvLinePk.getValue(), invLineDealTaxAmt, invLineFuncTaxAmt);

            // SVC_INV_LINE_TAX_DTL
            createSvcInvLineTaxDtl(svcInvLineTaxDtlTMsgList, svcInvLineTMsg.svcInvLinePk.getValue(), dsContrTMsg.ccyCd.getValue(), taxApiPMsg);

            // DS_CONTR_BLLG_SCHD
            if (ZYPCommonFunc.hasValue(svcInvLineTMsg.dsContrBllgSchdPk)) {
                DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = getDsContrBllgSchdTMsgForUpdate(svcInvLineTMsg.dsContrBllgSchdPk.getValue());
                ZYPEZDItemValueSetter.setValue(dsContrBllgSchdTMsg.dealTaxAmt, invLineDealTaxAmt);
                ZYPEZDItemValueSetter.setValue(dsContrBllgSchdTMsg.funcTaxAmt, invLineFuncTaxAmt);
                dsContrBllgSchdTMsgList.add(dsContrBllgSchdTMsg);
            }
        }

        ZYPEZDItemValueSetter.setValue(svcInvTMsg.invTotDealTaxAmt, invTotDealTaxAmt);
        ZYPEZDItemValueSetter.setValue(svcInvTMsg.invTotFuncTaxAmt, invTotFuncTaxAmt);

        // Update
        S21FastTBLAccessor.update(svcInvTMsg);
        if (svcInvLineTMsgList.size() > 0) {
            S21FastTBLAccessor.update(svcInvLineTMsgList.toArray(new SVC_INV_LINETMsg[svcInvLineTMsgList.size()]));
        }
        if (svcInvLineAllocTMsgList.size() > 0) {
            S21FastTBLAccessor.update(svcInvLineAllocTMsgList.toArray(new SVC_INV_LINE_ALLOCTMsg[svcInvLineAllocTMsgList.size()]));
        }
        if (dsContrBllgSchdTMsgList.size() > 0) {
            S21FastTBLAccessor.update(dsContrBllgSchdTMsgList.toArray(new DS_CONTR_BLLG_SCHDTMsg[dsContrBllgSchdTMsgList.size()]));
        }

        // Insert
        if (svcInvLineTaxDtlTMsgList.size() > 0) {
            S21FastTBLAccessor.insert(svcInvLineTaxDtlTMsgList.toArray(new SVC_INV_LINE_TAX_DTLTMsg[svcInvLineTaxDtlTMsgList.size()]));
        }

        svcInvTMsg = getSvcInvTMsg(svcInvPk);
        Map<String, String> updTmInfo = new HashMap<String, String>();
        updTmInfo.put("ezUpTime", svcInvTMsg.ezUpTime.getValue());
        updTmInfo.put("ezUpTimeZone", svcInvTMsg.ezUpTimeZone.getValue());
        this.svcInvUpdTmInfo.put(svcInvTMsg.svcInvNum.getValue(), updTmInfo);

        //TODO
        S21InfoLogOutput.println("[TestLog][setTax] END");

        return true;
    }

    private NWZC036101PMsg callTaxCalcAPIForBase(SVC_INVTMsg svcInvTMsg, SVC_INV_LINETMsg svcInvLineTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        CallTaxCalcAPIForBaseBean inBean = new CallTaxCalcAPIForBaseBean();
        inBean.setGlblCmpyCd(this.glblCmpyCd);
        inBean.setSlsDt(this.slsDt);
        inBean.setXxProcMd(NWZC036101Constant.PROC_MODE_INVOICE);
        inBean.setInvTp(svcInvTMsg.invTpCd.getValue());
        inBean.setDsAcctNum(svcInvTMsg.dsAcctNum.getValue());
        inBean.setBaseBillToCustCd(svcInvTMsg.billToCustCd.getValue());
        inBean.setDsContrDtlTpCd(dsContrDtlTMsg.dsContrDtlTpCd.getValue());
        inBean.setSvcMachMstrPk(svcInvLineTMsg.svcMachMstrPk.getValue());
        inBean.setDsContrPk(svcInvTMsg.dsContrPk.getValue());
        inBean.setNextBllgDt(svcInvTMsg.invDt.getValue());
        inBean.setDsContrBllgSchdPk(svcInvLineTMsg.dsContrBllgSchdPk.getValue());
        inBean.setSvcInvNum(svcInvTMsg.svcInvNum.getValue());
        inBean.setSvcPgmMdseCd(svcInvLineTMsg.svcPgmMdseCd.getValue());
        inBean.setBaseFuncAmt(svcInvLineTMsg.invLineFuncNetAmt.getValue());
        inBean.setOrigSvcInvNum(svcInvTMsg.origSvcInvNum.getValue());
        return NSXC003001CallTaxCalcAPIForBase.callTaxCalcApi(inBean, ONBATCH_TYPE.BATCH);
    }

    private NWZC036101PMsg callTaxCalcAPIForUsg(SVC_INVTMsg svcInvTMsg, SVC_INV_LINETMsg svcInvLineTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        CallTaxCalcAPIForUsageBean inBean = new CallTaxCalcAPIForUsageBean();
        inBean.setGlblCmpyCd(glblCmpyCd);
        inBean.setSlsDt(slsDt);
        inBean.setXxProcMd(NWZC036101Constant.PROC_MODE_INVOICE);
        inBean.setInvTp(svcInvTMsg.invTpCd.getValue());
        inBean.setDsAcctNum(svcInvTMsg.dsAcctNum.getValue());
        inBean.setBaseBillToCustCd(svcInvTMsg.billToCustCd.getValue());
        inBean.setDsContrDtlTpCd(dsContrDtlTMsg.dsContrDtlTpCd.getValue());
        inBean.setSvcMachMstrPk(svcInvLineTMsg.svcMachMstrPk.getValue());
        inBean.setDsContrPk(svcInvTMsg.dsContrPk.getValue());
        inBean.setNextBllgDt(svcInvTMsg.invDt.getValue());
        inBean.setDsContrBllgSchdPk(svcInvLineTMsg.dsContrBllgSchdPk.getValue());
        inBean.setSvcInvNum(svcInvTMsg.svcInvNum.getValue());
        inBean.setSvcPgmMdseCd(svcInvLineTMsg.intgMdseCd.getValue());
        inBean.setMdseCdForSvcAllocTp(svcInvLineTMsg.svcPgmMdseCd.getValue());
        inBean.setBaseFuncAmt(svcInvLineTMsg.invLineFuncNetAmt.getValue());
        inBean.setOrigSvcInvNum(svcInvTMsg.origSvcInvNum.getValue());
        return NSXC003001CallTaxCalcAPIForUsage.callTaxCalcApi(inBean, ONBATCH_TYPE.BATCH);
    }

    private NWZC036101PMsg callTaxCalcAPIForAddl(SVC_INVTMsg svcInvTMsg, SVC_INV_LINETMsg svcInvLineTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, SVC_INV_LINETMsg prntSvcInvLineTMsg) {
        CallTaxCalcAPIForAddlBean inBean = new CallTaxCalcAPIForAddlBean();
        inBean.setGlblCmpyCd(glblCmpyCd);
        inBean.setSlsDt(slsDt);
        inBean.setXxProcMd(NWZC036101Constant.PROC_MODE_INVOICE);
        inBean.setInvTp(svcInvTMsg.invTpCd.getValue());
        inBean.setDsAcctNum(svcInvTMsg.dsAcctNum.getValue());
        inBean.setBaseBillToCustCd(svcInvTMsg.billToCustCd.getValue());
        inBean.setDsContrDtlTpCd(dsContrDtlTMsg.dsContrDtlTpCd.getValue());
        inBean.setSvcMachMstrPk(prntSvcInvLineTMsg.svcMachMstrPk.getValue());
        inBean.setDsContrPk(svcInvTMsg.dsContrPk.getValue());
        inBean.setNextBllgDt(svcInvTMsg.invDt.getValue());
        inBean.setDsContrBllgSchdPk(prntSvcInvLineTMsg.dsContrBllgSchdPk.getValue());
        inBean.setSvcInvNum(svcInvTMsg.svcInvNum.getValue());
        inBean.setSvcPgmMdseCd(svcInvLineTMsg.intgMdseCd.getValue());
        inBean.setMdseCdForSvcAllocTp(svcInvLineTMsg.intgMdseCd.getValue());
        // START 2018/07/12 K.Kojima [QC#26571,ADD]
        inBean.setMdseCdForSvcAllocTpAddlChrg(prntSvcInvLineTMsg.svcPgmMdseCd.getValue());
        // END 2018/07/12 K.Kojima [QC#26571,ADD]
        inBean.setBaseFuncAmt(svcInvLineTMsg.invLineFuncNetAmt.getValue());
        inBean.setOrigSvcInvNum(svcInvTMsg.origSvcInvNum.getValue());
        return NSXC003001CallTaxCalcAPIForAddl.callTaxCalcApi(inBean, ONBATCH_TYPE.BATCH);
    }

    // START 2023/11/10 [QC#61468, ADD]
    private NWZC036101PMsg callTaxCalcAPIForFreight(SVC_INVTMsg svcInvTMsg, SVC_INV_LINETMsg svcInvLineTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        CallTaxCalcAPIForFreightBean inBean = new CallTaxCalcAPIForFreightBean();
        inBean.setGlblCmpyCd(this.glblCmpyCd);
        inBean.setSlsDt(this.slsDt);
        inBean.setXxProcMd(NWZC036101Constant.PROC_MODE_INVOICE);
        inBean.setInvTp(svcInvTMsg.invTpCd.getValue());
        inBean.setDsAcctNum(svcInvTMsg.dsAcctNum.getValue());
        inBean.setBaseBillToCustCd(svcInvTMsg.billToCustCd.getValue());
        inBean.setDsContrDtlTpCd(dsContrDtlTMsg.dsContrDtlTpCd.getValue());
        inBean.setSvcMachMstrPk(svcInvLineTMsg.svcMachMstrPk.getValue());
        inBean.setDsContrPk(svcInvTMsg.dsContrPk.getValue());
        inBean.setNextBllgDt(svcInvTMsg.invDt.getValue());
        inBean.setDsContrBllgSchdPk(svcInvLineTMsg.dsContrBllgSchdPk.getValue());
        inBean.setSvcInvNum(svcInvTMsg.svcInvNum.getValue());
        inBean.setMdseCd(svcInvLineTMsg.mdseCd.getValue());
        inBean.setBaseFuncAmt(svcInvLineTMsg.invLineFuncNetAmt.getValue());
        inBean.setOrigSvcInvNum(svcInvTMsg.origSvcInvNum.getValue());
        return NSXC003001CallTaxCalcAPIForFreight.callTaxCalcApi(inBean, ONBATCH_TYPE.BATCH);
    }
    // END   2023/11/10 [QC#61468, ADD]

    private BigDecimal getTaxRate(NWZC036101PMsg taxApiPMsg) {
        BigDecimal taxRate = BigDecimal.ZERO;
        if (taxApiPMsg.taxCalculateOutputLine.getValidCount() > 0) {
            NWZC036101_taxCalculateOutputLinePMsg linePMsg = taxApiPMsg.taxCalculateOutputLine.no(0);
            taxRate = nullToZero(linePMsg.xxTaxCalcLineTaxPct.getValue());
        }
        return taxRate;
    }

    private BigDecimal getTaxFuncAmt(NWZC036101PMsg taxApiPMsg) {
        BigDecimal taxFuncAmt = BigDecimal.ZERO;
        if (taxApiPMsg.taxCalculateOutputLine.getValidCount() > 0) {
            NWZC036101_taxCalculateOutputLinePMsg linePMsg = taxApiPMsg.taxCalculateOutputLine.no(0);
            taxFuncAmt = taxFuncAmt.add(nullToZero(linePMsg.taxAmt_01.getValue()));
            taxFuncAmt = taxFuncAmt.add(nullToZero(linePMsg.taxAmt_02.getValue()));
            taxFuncAmt = taxFuncAmt.add(nullToZero(linePMsg.taxAmt_03.getValue()));
        }
        return taxFuncAmt;
    }

    private BigDecimal exchToDealAmt(BigDecimal dealToFuncExchRate, String dealCcyAcctArthTpCd, BigDecimal funcAmt, BigDecimal dealCcyAftDeclPntDigitNum) {
        if (ACCT_ARTH_TP_MULTIPLY.equals(dealCcyAcctArthTpCd)) {
            return funcAmt.divide(dealToFuncExchRate, dealCcyAftDeclPntDigitNum.intValueExact(), RoundingMode.HALF_UP);
        } else if (ACCT_ARTH_TP_DIVIDE.equals(dealCcyAcctArthTpCd)) {
            return funcAmt.multiply(dealToFuncExchRate).setScale(dealCcyAftDeclPntDigitNum.intValueExact(), RoundingMode.HALF_UP);
        } else {
            return null;
        }
    }

    private void createSvcInvLineAlloc(List<SVC_INV_LINE_ALLOCTMsg> svcInvLineAllocTMsgList, BigDecimal svcInvLinePk, BigDecimal invLineDealTaxAmt, BigDecimal invLineFuncTaxAmt) {
        SVC_INV_LINE_ALLOCTMsgArray svcInvLineAllocTMsgArray = getSvcInvLineAllocTMsgArrayForUpdate(svcInvLinePk);
        if (svcInvLineAllocTMsgArray.getValidCount() == 0) {
            return;
        }
        List<SVC_INV_LINE_ALLOCTMsg> oriSvcInvLineAllocTMsgList = new ArrayList<SVC_INV_LINE_ALLOCTMsg>();
        for (int i = 0; i < svcInvLineAllocTMsgArray.getValidCount(); i++) {
            oriSvcInvLineAllocTMsgList.add(svcInvLineAllocTMsgArray.no(i));
        }
        adjSvcInvLineAllocFrac(oriSvcInvLineAllocTMsgList, invLineDealTaxAmt, invLineFuncTaxAmt);
        for (SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg : oriSvcInvLineAllocTMsgList) {
            svcInvLineAllocTMsgList.add(svcInvLineAllocTMsg);
        }
    }

    private void adjSvcInvLineAllocFrac(List<SVC_INV_LINE_ALLOCTMsg> oriSvcInvLineAllocTMsgList, BigDecimal taxDealAmt, BigDecimal taxFuncAmt) {
        List<SVC_INV_LINE_ALLOCTMsg> svcInvLineAllocTMsgList = new ArrayList<SVC_INV_LINE_ALLOCTMsg>(oriSvcInvLineAllocTMsgList);
        if (!svcInvLineAllocTMsgList.isEmpty()) {
            BigDecimal allocTotInvLineFuncTaxAmt = BigDecimal.ZERO;
            BigDecimal allocTotInvLineDealTaxAmt = BigDecimal.ZERO;
            for (SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg : svcInvLineAllocTMsgList) {
                allocTotInvLineDealTaxAmt = allocTotInvLineDealTaxAmt.add(svcInvLineAllocTMsg.invLineDealTaxAmt.getValue());
                allocTotInvLineFuncTaxAmt = allocTotInvLineFuncTaxAmt.add(svcInvLineAllocTMsg.invLineFuncTaxAmt.getValue());
            }
            BigDecimal allocAdjInvLineDealTaxAmt = taxDealAmt.subtract(allocTotInvLineDealTaxAmt);
            BigDecimal allocAdjInvLineFuncTaxAmt = taxFuncAmt.subtract(allocTotInvLineFuncTaxAmt);
            Collections.sort(svcInvLineAllocTMsgList, new SVC_INV_LINE_ALLOCTMsgComparator());
            SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTMsg = svcInvLineAllocTMsgList.get(0);
            BigDecimal allocInvLineDealTaxAmt = svcInvLineAllocTMsg.invLineDealTaxAmt.getValue().add(allocAdjInvLineDealTaxAmt);
            BigDecimal allocInvLineFuncTaxAmt = svcInvLineAllocTMsg.invLineFuncTaxAmt.getValue().add(allocAdjInvLineFuncTaxAmt);
            ZYPEZDItemValueSetter.setValue(svcInvLineAllocTMsg.invLineDealTaxAmt, allocInvLineDealTaxAmt);
            ZYPEZDItemValueSetter.setValue(svcInvLineAllocTMsg.invLineFuncTaxAmt, allocInvLineFuncTaxAmt);
        }
    }

    private void createSvcInvLineTaxDtl(List<SVC_INV_LINE_TAX_DTLTMsg> svcInvLineTaxDtlTMsgList, BigDecimal svcInvLinePk, String ccyCd, NWZC036101PMsg taxApiPMsg) {
        if (taxApiPMsg == null) {
            return;
        }
        String[] dsSlsTaxTpCd = new String[] {DS_SLS_TAX_TP.STATE_TAX, DS_SLS_TAX_TP.COUNTY_TAX, DS_SLS_TAX_TP.CITY_TAX };
        for (int i = 0; i < dsSlsTaxTpCd.length; i++) {
            NWZC036101_taxCalculateOutputLinePMsg linePMsg = taxApiPMsg.taxCalculateOutputLine.no(0);
            BigDecimal svcInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_INV_LINE_TAX_DTL_SQ);
            SVC_INV_LINE_TAX_DTLTMsg inTMsg = new SVC_INV_LINE_TAX_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.svcInvLineTaxDtlPk, svcInvLineTaxDtlPk);
            ZYPEZDItemValueSetter.setValue(inTMsg.svcInvLinePk, svcInvLinePk);
            ZYPEZDItemValueSetter.setValue(inTMsg.dsSlsTaxTpCd, dsSlsTaxTpCd[i]);
            if (i == 0) {
                if (!hasValue(linePMsg.taxAmt_01)) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(inTMsg.funcTaxAmt, nullToZero(linePMsg.taxAmt_01.getValue()));
                ZYPEZDItemValueSetter.setValue(inTMsg.slsTaxRate, nullToZero(linePMsg.taxPct_01.getValue()));
            } else if (i == 1) {
                if (!hasValue(linePMsg.taxAmt_02)) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(inTMsg.funcTaxAmt, nullToZero(linePMsg.taxAmt_02.getValue()));
                ZYPEZDItemValueSetter.setValue(inTMsg.slsTaxRate, nullToZero(linePMsg.taxPct_02.getValue()));
            } else if (i == 2) {
                if (!hasValue(linePMsg.taxAmt_03)) {
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(inTMsg.funcTaxAmt, nullToZero(linePMsg.taxAmt_03.getValue()));
                ZYPEZDItemValueSetter.setValue(inTMsg.slsTaxRate, nullToZero(linePMsg.taxPct_03.getValue()));
            }
            BigDecimal dealTaxAmt = NSXC003001Exchange.calcDealFromFunc(this.glblCmpyCd, ccyCd, this.slsDt, inTMsg.funcTaxAmt.getValue());
            ZYPEZDItemValueSetter.setValue(inTMsg.dealTaxAmt, dealTaxAmt);
            ZYPEZDItemValueSetter.setValue(inTMsg.taxAreaId, linePMsg.taxAreaId.getValue());
            svcInvLineTaxDtlTMsgList.add(inTMsg);
        }
        return;
    }

    private GLBL_CMPYTMsg getGlblCmpyTMsg() {
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        return (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    private CCYTMsg getCcyTMsg(String ccyCd) {
        return (CCYTMsg) ZYPCodeDataUtil.findByCode("CCY", glblCmpyCd, ccyCd);
    }

    private DS_INV_TPTMsg getDsInvTpTMsg(String dsInvTpCd) {
        DS_INV_TPTMsg inMsg = new DS_INV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsInvTpCd, dsInvTpCd);
        return (DS_INV_TPTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private DS_CONTRTMsg getDsContrTMsg(BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private DS_CONTR_DTLTMsg getDsContrDtlTMsg(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchdTMsgForUpdate(BigDecimal dsContrBllgSchdPk) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
    }

    private SVC_INVTMsg getSvcInvTMsg(BigDecimal svcInvPk) {
        SVC_INVTMsg inMsg = new SVC_INVTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcInvPk, svcInvPk);
        return (SVC_INVTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private SVC_INV_LINETMsg getSvcInvLineTMsg(BigDecimal svcInvLinePk) {
        SVC_INV_LINETMsg inMsg = new SVC_INV_LINETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcInvLinePk, svcInvLinePk);
        return (SVC_INV_LINETMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private SVC_INVTMsg getSvcInvTMsgForUpdate(BigDecimal svcInvPk) {
        SVC_INVTMsg inMsg = new SVC_INVTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcInvPk, svcInvPk);
        return (SVC_INVTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
    }

    private SVC_INV_LINETMsgArray getSvcInvLineTMsgArrayForUpdate(String svcInvNum) {
        SVC_INV_LINETMsg inMsg = new SVC_INV_LINETMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcInvNum01", svcInvNum);
        return (SVC_INV_LINETMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
    }

    private SVC_INV_LINE_ALLOCTMsgArray getSvcInvLineAllocTMsgArrayForUpdate(BigDecimal svcInvLinePk) {
        SVC_INV_LINE_ALLOCTMsg inMsg = new SVC_INV_LINE_ALLOCTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcInvLinePk01", svcInvLinePk);
        return (SVC_INV_LINE_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
    }

    private ACCT_DLY_ACTL_EXCH_RATESTMsg getAcctDlyActlExchRates(String ccyCd, String actlExchRateEntDt) {
        String cacheKey = "NSBB0100.ACCT_DLY_ACTL_EXCH_RATES." + ccyCd + "." + actlExchRateEntDt;
        if (cache.contains(cacheKey)) {
            return (ACCT_DLY_ACTL_EXCH_RATESTMsg) cache.get(cacheKey);
        } else {
            Map<String, String> prm = new HashMap<String, String>();
            prm.put("glblCmpyCd", glblCmpyCd);
            prm.put("ccyCd", ccyCd);
            prm.put("actlExchRateEntDt", actlExchRateEntDt);
            Map<String, String> rsltMap = (Map<String, String>) ssmBatchClient.queryObject("getAcctDlyActlExchRates", prm);
            if (rsltMap == null || rsltMap.isEmpty()) {
                cache.put(cacheKey, null);
                return null;
            } else {
                ACCT_DLY_ACTL_EXCH_RATESTMsg tMsg = new ACCT_DLY_ACTL_EXCH_RATESTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, ccyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.actlExchRateEntDt, rsltMap.get("ACTL_EXCH_RATE_ENT_DT"));
                ACCT_DLY_ACTL_EXCH_RATESTMsg rsltTMsg = (ACCT_DLY_ACTL_EXCH_RATESTMsg) S21CacheTBLAccessor.findByKey(tMsg);
                cache.put(cacheKey, rsltTMsg);
                return rsltTMsg;
            }
        }
    }

    private BigDecimal nullToZero(BigDecimal val) {
        if (hasValue(val)) {
            return val;
        }
        return BigDecimal.ZERO;
    }

    /**
     * SVC_INV_LINE_ALLOCTMsg Comparator for sorting
     * SVC_INV_LINE_ALLOCTMsgs in the same invoice line by
     * INV_LINE_FUNC_NET_AMT in descending order.
     * 
     * <pre>
     * Date         Company         Name            Create/Update   Defect No
     * ----------------------------------------------------------------------
     * 2013/08/13   SRAA            N.Otsuji        Create          N/A
     *</pre>
     */
    private static final class SVC_INV_LINE_ALLOCTMsgComparator implements Comparator<SVC_INV_LINE_ALLOCTMsg>, Serializable {

        /**
         * Serial Version UID
         */
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(SVC_INV_LINE_ALLOCTMsg t1, SVC_INV_LINE_ALLOCTMsg t2) {
            int cmp = t2.invLineDealNetAmt.getValue().compareTo(t1.invLineDealNetAmt.getValue());
            if (cmp == 0) {
                cmp = t1.svcInvLineAllocNum.getValue().compareTo(t2.svcInvLineAllocNum.getValue());
            }
            return cmp;
        }
    }
    // END 2018/05/31 K.Kojima [QC#23685,ADD]

    // START 2018/07/18 M.Naito [QC#13309, ADD]
    private void createSvcInvForStandByFee() {

        List<Map<String, Object>> tempEttlList = getClosedTempEttlInfo();

        for (Map<String, Object> tempEttl : tempEttlList) {

            List<SVC_INVTMsg> svcInvTMsgList = new ArrayList<SVC_INVTMsg>();
            List<SVC_INV_LINETMsg> svcInvLineTMsgList = new ArrayList<SVC_INV_LINETMsg>();
            List<SVC_INV_LINE_ALLOCTMsg> svcInvLineAllocTMsgList = new ArrayList<SVC_INV_LINE_ALLOCTMsg>();
            List<SVC_INV_LINE_TAX_DTLTMsg> svcInvLineTaxDtlTMsgList = new ArrayList<SVC_INV_LINE_TAX_DTLTMsg>();
            List<Map<String, Object>> svcInvInfoList = getSvcInvInfoForStandByFee(getString(tempEttl, SELECT.TEMP_ETTL_NUM));
            // START 2018/09/10 K.Kitachi [QC#26260, ADD]
            boolean isError = false;
            // END 2018/09/10 K.Kitachi [QC#26260, ADD]

            for (Map<String, Object> svcInvInfo : svcInvInfoList) {

                // set SVC_INVTMsg
                SVC_INVTMsg svcInvTmsg = new SVC_INVTMsg();
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.glblCmpyCd, glblCmpyCd);
                BigDecimal svcInvPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_INV_SQ);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.svcInvPk, svcInvPk);

                String dsInvTpCd = ZYPCodeDataUtil.getVarCharConstValue(DS_INV_TYPE_INVOICE, glblCmpyCd);
                if (!ZYPCommonFunc.hasValue(dsInvTpCd)) {
                    throw new S21AbendException(NSBM0069E, new String[] {DS_INV_TYPE_INVOICE });
                }
                DS_INV_TPTMsg dsInvTpTMsg = (DS_INV_TPTMsg) ZYPCodeDataUtil.findByCode(DS_INV_TP.class, glblCmpyCd, dsInvTpCd);
                String svcInvNum = ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.svcInvNum, svcInvNum);

                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invDt, slsDt);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTpCd, INV_TP.INVOICE);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.svcInvStsCd, SVC_INV_STS.PRINTED);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.svcInvSrcTpCd, SVC_INV_SRC_TP.DISPATCH);

                ZYPEZDItemValueSetter.setValue(svcInvTmsg.shipToCustCd, (String) svcInvInfo.get("CUR_LOC_NUM"));
                SHIP_TO_CUSTTMsg shipToCustTmsg = getShipToCustFindByKey(svcInvTmsg.shipToCustCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToCustCd, (String) shipToCustTmsg.sellToCustCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToCustCd, (String) svcInvInfo.get("BILL_TO_LOC_NUM"));

                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpyTMsg();
                String ccyCd = glblCmpyTMsg.stdCcyCd.getValue();
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.dealCcyCd, ccyCd);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.mdseCd, (String) svcInvInfo.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.svcMachMstrPk, (BigDecimal) svcInvInfo.get("SVC_MACH_MSTR_PK"));

                MDSETMsg mdseTmsg = getMdseFindByKey((String) svcInvInfo.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.mdseNm, mdseTmsg.mdseNm.getValue());
                Map<String, Object> mdlMap = getMdl((BigDecimal) svcInvInfo.get("SVC_MACH_MSTR_PK"));
                if (mdlMap != null) {
                    ZYPEZDItemValueSetter.setValue(svcInvTmsg.mdlId, (BigDecimal) mdlMap.get("MDL_ID"));
                    ZYPEZDItemValueSetter.setValue(svcInvTmsg.mdlNm, (String) mdlMap.get("MDL_NM"));
                }

                ACCT_DLY_ACTL_EXCH_RATESTMsg acctDlyActlExchRatesTMsg = getAcctDlyActlExchRates(ccyCd, slsDt);
                BigDecimal actlExchRate = acctDlyActlExchRatesTMsg.actlExchRate.getValue();
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.ccyExchRate, actlExchRate);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotDealSlsAmt, (BigDecimal) svcInvInfo.get("STAND_BY_FEE_AMT"));
                CCYTMsg ccyTMsg = getCcyFindByKey(ccyCd);
                int scale = ccyTMsg.aftDeclPntDigitNum.getValueInt();
                String acctArthTpCd = ccyTMsg.acctArthTpCd.getValue();
                BigDecimal invTotFuncSlsAmt = calcFuncAmt(svcInvTmsg.invTotDealSlsAmt.getValue(), acctArthTpCd, actlExchRate, scale);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotFuncSlsAmt, invTotFuncSlsAmt);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotDealTaxAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotFuncTaxAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotDealDiscAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotFuncDiscAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotDealNetAmt, svcInvTmsg.invTotDealSlsAmt.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotFuncNetAmt, invTotFuncSlsAmt);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.serNum, (String) svcInvInfo.get("SER_NUM"));

                Map<String, Object> defaultPmtTermMap = getDefaultPmtTerm((String) svcInvInfo.get("BILL_TO_LOC_NUM"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.pmtTermCashDiscCd, (String) defaultPmtTermMap.get("PMT_TERM_CASH_DISC_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.pmtTermCd, (String) defaultPmtTermMap.get("PMT_TERM_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.pmtTermCashDiscDescTxt, (String) defaultPmtTermMap.get("PMT_TERM_CASH_DISC_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.pmtTermStartDt, slsDt);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invDueDt, (String) defaultPmtTermMap.get("INV_DUE_DT"));

                Map<String, String> billToCustMap = getBillToCust(svcInvTmsg.billToCustCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.remToLocNm, (String) billToCustMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.remToAddlLocNm, (String) billToCustMap.get("ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.remId, (String) billToCustMap.get("REM_ID"));

                ZYPEZDItemValueSetter.setValue(svcInvTmsg.ofcNm, glblCmpyTMsg.glblCmpyNm.getValue());

                SELL_TO_CUSTTMsg sellToCustTmsg = getSellToCustFindByKey(svcInvTmsg.sellToCustCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToLocNm, sellToCustTmsg.locNm.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToAddlLocNm, sellToCustTmsg.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToFirstLineAddr, sellToCustTmsg.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToScdLineAddr, sellToCustTmsg.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToThirdLineAddr, sellToCustTmsg.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToFrthLineAddr, sellToCustTmsg.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToCtyAddr, sellToCustTmsg.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToProvNm, sellToCustTmsg.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToStCd, sellToCustTmsg.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToPostCd, sellToCustTmsg.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToCtryCd, sellToCustTmsg.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToFirstRefCmntTxt, sellToCustTmsg.firstRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToScdRefCmntTxt, sellToCustTmsg.scdRefCmntTxt.getValue());
                if (ZYPCommonFunc.hasValue(sellToCustTmsg.cntyPk)) {
                    CNTYTMsg prmCntyTmsg = getCntyFindByKey(sellToCustTmsg.cntyPk.getValue());
                    if (prmCntyTmsg != null) {
                        ZYPEZDItemValueSetter.setValue(svcInvTmsg.sellToCntyNm, prmCntyTmsg.cntyNm.getValue());
                    }
                }
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invPrintStsCd, INV_PRINT_STS_DEF);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invMlSendStsCd, INV_PRINT_STS_N);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invEdiSendStsCd, INV_PRINT_STS_N);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invFaxSendStsCd, INV_PRINT_STS_N);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.invEmlSendStsCd, INV_PRINT_STS_N);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.svcInvOmLinkFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.custIssPoNum, (String) svcInvInfo.get("CUST_PO_NUM"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.manCratInvFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.slsSmryCratCpltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.lineBizTpCd, (String) svcInvInfo.get("SVC_BY_LINE_BIZ_TP_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.dsInvTpCd, dsInvTpCd);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToCustAcctCd, (String) billToCustMap.get("SELL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.dsAcctNum, (String) shipToCustTmsg.sellToCustCd.getValue());

                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToLocNm, (String) billToCustMap.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToAddlLocNm, (String) billToCustMap.get("ADDL_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToFirstLineAddr, (String) billToCustMap.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToScdLineAddr, (String) billToCustMap.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToThirdLineAddr, (String) billToCustMap.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToFrthLineAddr, (String) billToCustMap.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToCtyAddr, (String) billToCustMap.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToProvNm, (String) billToCustMap.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToCntyNm, (String) billToCustMap.get("CNTY_NM"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToStCd, (String) billToCustMap.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToPostCd, (String) billToCustMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToCtryCd, (String) billToCustMap.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToFirstRefCmntTxt, (String) billToCustMap.get("FIRST_REF_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.billToScdRefCmntTxt, (String) billToCustMap.get("SCD_REF_CMNT_TXT"));

                // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//                String slsRepTocCd = getSlsRepTocCd((BigDecimal) svcInvInfo.get("SVC_MACH_MSTR_PK"), (String) svcInvInfo.get("TEMP_ETTL_NUM"), (String) svcInvInfo.get("SVC_BY_LINE_BIZ_TP_CD"));
                String slsRepTocCd = getSlsRepTocCd((BigDecimal) svcInvInfo.get("SVC_MACH_MSTR_PK"), (String) svcInvInfo.get("TEMP_ETTL_NUM"), (String) svcInvInfo.get("SVC_BY_LINE_BIZ_TP_CD"), (String) svcInvInfo.get("SLD_BY_LINE_BIZ_TP_CD"));
                // END 2018/09/10 K.Kitachi [QC#26260, MOD]
                // START 2018/09/10 K.Kitachi [QC#26260, ADD]
                if (!ZYPCommonFunc.hasValue(slsRepTocCd)) {
                    setErrMsg(svcInvNum, NSBM0071E, new String[] {"DEF_SLS_REP_TOC_CD" });
                    errCnt++;
                    isError = true;
                    break;
                }
                // END 2018/09/10 K.Kitachi [QC#26260, ADD]

                ZYPEZDItemValueSetter.setValue(svcInvTmsg.slsRepTocCd, slsRepTocCd);

                ZYPEZDItemValueSetter.setValue(svcInvTmsg.shipToCustAcctCd, shipToCustTmsg.sellToCustCd.getValue());

                ZYPEZDItemValueSetter.setValue(svcInvTmsg.svcInvOmLinkStsCd, STS_CD_ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.prcAllocByMachQtyFlg, ZYPConstant.FLG_OFF_N);

                if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals(svcInvTmsg.pmtTermCashDiscCd.getValue())) {
                    String crCardCustRefNum = getCrCardCustRefNum(svcInvTmsg.sellToCustCd.getValue());
                    if (hasValue(crCardCustRefNum)) {
                        ZYPEZDItemValueSetter.setValue(svcInvTmsg.crCardCustRefNum, crCardCustRefNum);
                    }
                }
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.tempSvcInvNumFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(svcInvTmsg.tempEttlNum, (String) svcInvInfo.get("TEMP_ETTL_NUM"));


                // set SVC_INV_LINETMsg
                SVC_INV_LINETMsg svcInvLineTmsg = new SVC_INV_LINETMsg();
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.glblCmpyCd, glblCmpyCd);
                BigDecimal svcInvLinePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_INV_LINE_SQ);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcInvLinePk, svcInvLinePk);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcInvNum, svcInvNum);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcInvLineNum, DEFAULT_SVC_INV_LINE_NUM);

                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcInvChrgTpCd, SVC_INV_CHRG_TP.EXPENSE_CHARGE);
                String mdseCd = ZYPCodeDataUtil.getVarCharConstValue(STAND_BY_FEE_MDSE_CD, glblCmpyCd);
                if (!ZYPCommonFunc.hasValue(mdseCd)) {
                    throw new S21AbendException(NSBM0069E, new String[] {STAND_BY_FEE_MDSE_CD });
                }
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.mdseCd, mdseCd);
                MDSETMsg standByFeeMdseTmsg = getMdseFindByKey(mdseCd);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.mdseNm, standByFeeMdseTmsg.mdseNm.getValue());
                SVC_INV_CHRG_TPTMsg svcInvChrgTpTmsg = getSvcInvChrgTp(svcInvLineTmsg.svcInvChrgTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.trxCd, svcInvChrgTpTmsg.trxCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.trxRsnCd, svcInvChrgTpTmsg.trxRsnCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.slsRepTocCd, slsRepTocCd);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcInvQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.dealUnitPrcAmt, (BigDecimal) svcInvInfo.get("STAND_BY_FEE_AMT"));
                BigDecimal funcUnitPrcAmt = calcFuncAmt(svcInvLineTmsg.dealUnitPrcAmt.getValue(), acctArthTpCd, actlExchRate, scale);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.funcUnitPrcAmt, funcUnitPrcAmt);

                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcInvMnAot, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineDealSlsAmt, (BigDecimal) svcInvInfo.get("STAND_BY_FEE_AMT"));
                BigDecimal invLineFuncSlsAmt = calcFuncAmt(svcInvLineTmsg.invLineDealSlsAmt.getValue(), acctArthTpCd, actlExchRate, scale);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineFuncSlsAmt, invLineFuncSlsAmt);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcLborDealAmt, (BigDecimal) svcInvInfo.get("STAND_BY_FEE_AMT"));
                BigDecimal svcLborFuncAmt = calcFuncAmt(svcInvLineTmsg.svcLborDealAmt.getValue(), acctArthTpCd, actlExchRate, scale);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcLborFuncAmt, svcLborFuncAmt);

                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.slsTaxRate, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineDealTaxAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineFuncTaxAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineDiscRate, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineDealDiscAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineFuncDiscAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.funcDiscUnitPrcAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineDealNetAmt, svcInvLineTmsg.invLineDealSlsAmt.getValue().subtract(svcInvLineTmsg.invLineDealDiscAmt.getValue()));
                BigDecimal invLineFuncNetAmt = calcFuncAmt(svcInvLineTmsg.invLineDealNetAmt.getValue(), acctArthTpCd, actlExchRate, scale);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineFuncNetAmt, invLineFuncNetAmt);

                String coaCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(COA_CMPY_CD, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.coaCmpyCd, coaCmpyCd);
                TOCTMsg tocTmsg = getTocFindByKey(slsRepTocCd);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.coaAfflCd, (String) billToCustMap.get("COA_AFFL_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.coaChCd, (String) billToCustMap.get("COA_CH_CD"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.coaBrCd, tocTmsg.coaBrCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.coaCcCd, tocTmsg.coaCcCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.coaAcctCd, mdseTmsg.revCoaAcctCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.coaProdCd, mdseTmsg.coaProdCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.coaExtnCd, tocTmsg.coaExtnCd.getValue());

                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.shipToCustCd, (String) svcInvInfo.get("CUR_LOC_NUM"));
                if (mdlMap != null) {
                    ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.mdlId, (BigDecimal) mdlMap.get("MDL_ID"));
                    ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.mdlNm, (String) mdlMap.get("MDL_NM"));
                }
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcMachMstrPk, (BigDecimal) svcInvInfo.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.serNum, (String) svcInvInfo.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.custIssPoNum, (String) svcInvInfo.get("CUST_PO_NUM"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.firstBllgAttrbValTxt, (String) svcInvInfo.get("CTRL_FLD_TXT_01"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.scdBllgAttrbValTxt, (String) svcInvInfo.get("CTRL_FLD_TXT_02"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.thirdBllgAttrbValTxt, (String) svcInvInfo.get("CTRL_FLD_TXT_03"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.frthBllgAttrbValTxt, (String) svcInvInfo.get("CTRL_FLD_TXT_04"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.fifthBllgAttrbValTxt, (String) svcInvInfo.get("CTRL_FLD_TXT_05"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.sixthBllgAttrbValTxt, (String) svcInvInfo.get("CTRL_FLD_TXT_06"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcInvLineTpCd, SVC_INV_LINE_TP.OTHER);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.copyInclQty, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.uomCd, PKG_UOM.EACH);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invDispQty, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invDispUnitPrcAmt, (BigDecimal) svcInvInfo.get("STAND_BY_FEE_AMT"));
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.basePrcAdjDealAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.basePrcAdjFuncAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcInvEligFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.mtrRollOverFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.mtrExchFlg, ZYPConstant.FLG_OFF_N);

                // set SVC_INV_LINE_TAX_DTLTMsg and update SVC_INV_LINETMsg
                if (!setTaxAmt(svcInvLineTaxDtlTMsgList, svcInvTmsg, svcInvLineTmsg, dsInvTpTMsg.taxCalcFlg.getValue())) {
                    // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//                    continue;
                    isError = true;
                    break;
                    // END 2018/09/10 K.Kitachi [QC#26260, MOD]
                }
                svcInvLineTMsgList.add(svcInvLineTmsg);
                svcInvTMsgList.add(svcInvTmsg);

                // set SVC_INV_LINE_ALLOCTMsg
                SVC_INV_LINE_ALLOCTMsg svcInvLineAllocTmsg = new SVC_INV_LINE_ALLOCTMsg();
                EZDMsg.copy(svcInvLineTmsg, null, svcInvLineAllocTmsg, null);

                BigDecimal svcInvLineAllocPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_INV_LINE_ALLOC_SQ);
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.svcInvLineAllocPk, svcInvLineAllocPk);
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.svcInvLineAllocNum, svcInvLineAllocTmsg.svcInvLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.svcInvLineNum, svcInvLineTmsg.svcInvLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.svcInvLineNum, svcInvLineTmsg.svcInvLineNum.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.contrPrcAllocTpCd, CONTR_PRC_ALLOC_TP.EXPENSE);
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.intgMdseCd, svcInvLineTmsg.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.tocCd, svcInvLineTmsg.slsRepTocCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.slsAllocRate, BigDecimal.valueOf(DEFAULT_RATE));
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.dealGrsUnitPrcAmt, svcInvLineTmsg.dealUnitPrcAmt.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.dealNetUnitPrcAmt, svcInvLineTmsg.invLineDealNetAmt.getValue().divide(svcInvLineTmsg.svcInvQty.getValue(), scale, RoundingMode.HALF_UP));
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.dealGrsTotPrcAmt, svcInvLineTmsg.invLineDealSlsAmt.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.funcGrsUnitPrcAmt, svcInvLineTmsg.funcUnitPrcAmt.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.funcNetUnitPrcAmt, svcInvLineTmsg.invLineFuncNetAmt.getValue().divide(svcInvLineTmsg.svcInvQty.getValue(), scale, RoundingMode.HALF_UP));
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.funcGrsTotPrcAmt, svcInvLineTmsg.invLineFuncSlsAmt.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.ccyCd, svcInvTmsg.dealCcyCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.invLineSplTpCd, INV_LINE_SPL_TP.SERVICE);
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.invLineSplRate, BigDecimal.valueOf(DEFAULT_RATE));
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.slsRepTocAllocRate, BigDecimal.valueOf(DEFAULT_RATE));
                ZYPEZDItemValueSetter.setValue(svcInvLineAllocTmsg.coaProjCd, getCoaProjCd(glblCmpyCd, svcInvLineTmsg.mdseCd.getValue()));
                svcInvLineAllocTMsgList.add(svcInvLineAllocTmsg);
            }
            // START 2018/09/10 K.Kitachi [QC#26260, ADD]
            if (isError) {
                continue;
            }
            // END 2018/09/10 K.Kitachi [QC#26260, ADD]
            // Insert SVC_INV
            if (svcInvTMsgList.size() > 0) {
                S21FastTBLAccessor.insert(svcInvTMsgList.toArray(new SVC_INVTMsg[svcInvTMsgList.size()]));
            }
            // Insert SVC_INV_LINE
            if (svcInvLineTMsgList.size() > 0) {
                S21FastTBLAccessor.insert(svcInvLineTMsgList.toArray(new SVC_INV_LINETMsg[svcInvLineTMsgList.size()]));
            }
            // Insert SVC_INV_LINE_ALLOC
            if (svcInvLineAllocTMsgList.size() > 0) {
                S21FastTBLAccessor.insert(svcInvLineAllocTMsgList.toArray(new SVC_INV_LINE_ALLOCTMsg[svcInvLineAllocTMsgList.size()]));
            }
            // Insert SVC_INV_LINE_TAX_DTL
            if (svcInvLineTaxDtlTMsgList.size() > 0) {
                S21FastTBLAccessor.insert(svcInvLineTaxDtlTMsgList.toArray(new SVC_INV_LINE_TAX_DTLTMsg[svcInvLineTaxDtlTMsgList.size()]));
            }
            // update TEMP_ETTL
            updateTempEttl(getString(tempEttl, SELECT.TEMP_ETTL_NUM));
            commit();
            // add start 2019/04/03 QC#31037
            normCnt++;
            // add end 2019/04/03 QC#31037
        }
    }

    private boolean setTaxAmt(List<SVC_INV_LINE_TAX_DTLTMsg> svcInvLineTaxDtlTMsgList, SVC_INVTMsg svcInvTmsg, SVC_INV_LINETMsg svcInvLineTmsg, String taxCalcFlg) {

        BigDecimal funcTaxAmt = BigDecimal.ZERO;
        BigDecimal dealTaxAmt = BigDecimal.ZERO;
        BigDecimal taxRt = BigDecimal.ZERO;

        if (svcInvLineTmsg.funcUnitPrcAmt.getValue().compareTo(BigDecimal.ZERO) != 0 && ZYPConstant.FLG_ON_Y.equals(taxCalcFlg)) {

            FSRTMsg fsrTmsg = new FSRTMsg();
            fsrTmsg.billToCustUpdFlg.setValue(ZYPConstant.FLG_OFF_N);
            fsrTmsg.shipToCustUpdFlg.setValue(ZYPConstant.FLG_OFF_N);
            fsrTmsg.sellToCustCd.setValue(svcInvTmsg.sellToCustCd.getValue());
            fsrTmsg.billToCustAcctCd.setValue(svcInvTmsg.billToCustAcctCd.getValue());
            fsrTmsg.billToCustCd.setValue(svcInvTmsg.billToCustCd.getValue());
            fsrTmsg.shipToCustAcctCd.setValue(svcInvTmsg.shipToCustAcctCd.getValue());
            fsrTmsg.shipToCustCd.setValue(svcInvTmsg.shipToCustCd.getValue());
            CallTaxCalcAPIBean apiBean = new CallTaxCalcAPIBean(fsrTmsg, svcInvTmsg, svcInvLineTmsg);
            NWZC036101PMsg taxApiPmsg = NSXC003001CallTaxCalcAPIForSvcPrc.callTaxCalcApi(apiBean, ONBATCH_TYPE.BATCH);

            if (S21ApiUtil.isXxMsgId(taxApiPmsg)) {
                return false;
            }
            if (ZYPCommonFunc.hasValue(taxApiPmsg.taxCalculateOutputLine.no(0).invLineFuncTaxAmt)) {
                funcTaxAmt = taxApiPmsg.taxCalculateOutputLine.no(0).invLineFuncTaxAmt.getValue();
            }
            CCYTMsg ccyTMsg = getCcyFindByKey(svcInvTmsg.dealCcyCd.getValue());
            int scale = ccyTMsg.aftDeclPntDigitNum.getValueInt();
            String acctArthTpCd = ccyTMsg.acctArthTpCd.getValue();
            dealTaxAmt = calcDealAmt(funcTaxAmt, svcInvTmsg.ccyExchRate.getValue(), acctArthTpCd, scale);
            if (ZYPCommonFunc.hasValue(taxApiPmsg.taxCalculateOutputLine.no(0).xxTaxCalcLineTaxPct)) {
                taxRt = taxApiPmsg.taxCalculateOutputLine.no(0).xxTaxCalcLineTaxPct.getValue().setScale(DEFAULT_SCL, BigDecimal.ROUND_HALF_UP);
            }
            ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.taxCalcGeoCd, taxApiPmsg.taxCalculateOutputLine.no(0).taxAreaId);

            createSvcInvLineTaxDtl(svcInvLineTaxDtlTMsgList, svcInvLineTmsg.svcInvLinePk.getValue(), svcInvTmsg.dealCcyCd.getValue(), taxApiPmsg);
        }

        ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.slsTaxRate, taxRt);
        ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineFuncTaxAmt, funcTaxAmt);
        ZYPEZDItemValueSetter.setValue(svcInvLineTmsg.invLineDealTaxAmt, dealTaxAmt);
        ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotFuncTaxAmt, funcTaxAmt);
        ZYPEZDItemValueSetter.setValue(svcInvTmsg.invTotDealTaxAmt, dealTaxAmt);

        return true;
    }

    private List<Map<String, Object>> getClosedTempEttlInfo() {

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("slsDt", slsDt);
        prmMap.put("multiCalcCnt", this.numConstMap.get(MULTI_SVC_INV_CALC_CNT));
        // del start 2019/04/03 QC#31037
        //prmMap.put("divideNum", this.divideNum);
        // del end 2019/04/03 QC#31037

        List<Map<String, Object>> tempEttlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getClosedTempEttlInfo", prmMap);

        return tempEttlList;
    }

    private List<Map<String, Object>> getSvcInvInfoForStandByFee(String tempEttlNum) {

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("tempEttlNum", tempEttlNum);

        List<Map<String, Object>> tempEttlList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcInvInfoForStandByFee", prmMap);

        return tempEttlList;
    }

    private Map<String, Object> getMdl(BigDecimal svcMachMstrPk) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcMachMstrPk", svcMachMstrPk);
        Map<String, Object> mdlMap = (Map<String, Object>) ssmBatchClient.queryObject("getMdl", prmMap);

        if (mdlMap == null) {
            mdlMap = (Map<String, Object>) ssmBatchClient.queryObject("getMdlInfoFromConfigMstrDtl", prmMap);
        }
        return mdlMap;
    }

    private MDSETMsg getMdseFindByKey(String mdseCd) {
        MDSETMsg paramTMsg = new MDSETMsg();
        paramTMsg.glblCmpyCd.setValue(glblCmpyCd);
        paramTMsg.mdseCd.setValue(mdseCd);
        return (MDSETMsg) EZDTBLAccessor.findByKey(paramTMsg);
    }

    private CCYTMsg getCcyFindByKey(String invCcyCd) {
        CCYTMsg paramTMsg = new CCYTMsg();
        paramTMsg.glblCmpyCd.setValue(glblCmpyCd);
        paramTMsg.ccyCd.setValue(invCcyCd);
        return (CCYTMsg) EZDTBLAccessor.findByKey(paramTMsg);
    }

    private SELL_TO_CUSTTMsg getSellToCustFindByKey(String sellToCustCd) {
        SELL_TO_CUSTTMsg paramTMsg = new SELL_TO_CUSTTMsg();
        paramTMsg.setSQLID("001");
        paramTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        paramTMsg.setConditionValue("sellToCustCd01", sellToCustCd);
        SELL_TO_CUSTTMsgArray tMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(paramTMsg);
        if (tMsgArray.getValidCount() <= 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private CNTYTMsg getCntyFindByKey(BigDecimal cntyPk) {
        CNTYTMsg paramTMsg = new CNTYTMsg();
        paramTMsg.glblCmpyCd.setValue(glblCmpyCd);
        paramTMsg.cntyPk.setValue(cntyPk);
        return (CNTYTMsg) EZDTBLAccessor.findByKey(paramTMsg);
    }

    private TOCTMsg getTocFindByKey(String tocCd) {
        TOCTMsg paramTMsg = new TOCTMsg();
        paramTMsg.glblCmpyCd.setValue(glblCmpyCd);
        paramTMsg.tocCd.setValue(tocCd);
        return (TOCTMsg) EZDTBLAccessor.findByKey(paramTMsg);
    }

    private TEMP_ETTLTMsg getTempEttlFindByKey(String tempEttlNum) {
        TEMP_ETTLTMsg paramTMsg = new TEMP_ETTLTMsg();
        paramTMsg.glblCmpyCd.setValue(glblCmpyCd);
        paramTMsg.tempEttlNum.setValue(tempEttlNum);
        return (TEMP_ETTLTMsg) EZDTBLAccessor.findByKey(paramTMsg);
    }

    private SVC_INV_CHRG_TPTMsg getSvcInvChrgTp(String svcInvChrgTpCd) {
        SVC_INV_CHRG_TPTMsg inMsg = new SVC_INV_CHRG_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcInvChrgTpCd, svcInvChrgTpCd);
        SVC_INV_CHRG_TPTMsg svcInvChrgTpTmsg = (SVC_INV_CHRG_TPTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (svcInvChrgTpTmsg == null) {
            throw new S21AbendException(NSBM0071E, new String[] {"SVC_INV_CHRG_TP" });
        }
        return svcInvChrgTpTmsg;
    }

    private SHIP_TO_CUSTTMsg getShipToCustFindByKey(String shipToCustCd) {
        SHIP_TO_CUSTTMsg prmShipToCustTmsg = new SHIP_TO_CUSTTMsg();
        prmShipToCustTmsg.setSQLID("004");
        prmShipToCustTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmShipToCustTmsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(prmShipToCustTmsg);

        if (tMsgArray.getValidCount() <= 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    private BigDecimal calcFuncAmt(BigDecimal amt, String acctArthTpCd, BigDecimal actlExchRate, int scale) {

        if (amt == null) {
            return amt;
        }
        if (BigDecimal.ZERO.compareTo(actlExchRate) == 0) {
            return BigDecimal.ZERO;
        }
        if (ACCT_ARTH_TP_MULTIPLY.equals(acctArthTpCd)) {
            return amt.multiply(actlExchRate).setScale(scale, RoundingMode.HALF_UP);
        } else {
            return amt.divide(actlExchRate, scale, RoundingMode.HALF_UP);
        }
    }

    private BigDecimal calcDealAmt(BigDecimal funcAmt, BigDecimal rt, String acctArthTpCd, int digit) {
        if (BigDecimal.ZERO.equals(funcAmt)) {
            return BigDecimal.ZERO;
        }
        if (ACCT_ARTH_TP_MULTIPLY.equals(acctArthTpCd)) {
            return funcAmt.divide(rt, digit, BigDecimal.ROUND_HALF_UP);
        } else {
            return funcAmt.multiply(rt).setScale(DEFAULT_SCL, BigDecimal.ROUND_HALF_UP);
        }
    }

    private Map<String, Object> getDefaultPmtTerm(String dsAcctNum) {

        Map<String, Object> pmtTermMap = new HashMap<String, Object>();

        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            Map<String, Object> rsltMap = new HashMap<String, Object>();

            Map<String, Object> custPrmMap = new HashMap<String, Object>();
            custPrmMap.put("glblCmpyCd", glblCmpyCd);
            custPrmMap.put("billToCustCd", dsAcctNum);
            Map<String, Object> custPmtTermMap = (Map<String, Object>) ssmBatchClient.queryObject("getCustPmtTerm", custPrmMap);
            if (custPmtTermMap != null) {
                rsltMap = custPmtTermMap;
            } else {
                Map<String, Object> acctPrmMap = new HashMap<String, Object>();
                acctPrmMap.put("glblCmpyCd", glblCmpyCd);
                acctPrmMap.put("billToCustCd", dsAcctNum);
                Map<String, Object> acctPmtTermMap = (Map<String, Object>) ssmBatchClient.queryObject("getAcctPmtTerm", acctPrmMap);
                if (acctPmtTermMap != null) {
                    rsltMap = acctPmtTermMap;
                }
            }
            if (rsltMap != null) {
                pmtTermMap.put("PMT_TERM_CASH_DISC_CD", (String) rsltMap.get("PMT_TERM_CASH_DISC_CD"));
                pmtTermMap.put("PMT_TERM_CASH_DISC_DESC_TXT", (String) rsltMap.get("PMT_TERM_CASH_DISC_DESC_TXT"));
                pmtTermMap.put("PMT_TERM_CD", (String) rsltMap.get("PMT_TERM_CD"));
                String invDueDt = slsDt;
                if (hasValue((BigDecimal) rsltMap.get("PMT_TERM_AOT"))) {
                    invDueDt = ZYPDateUtil.addDays(slsDt, ((BigDecimal) rsltMap.get("PMT_TERM_AOT")).intValue());
                }
                pmtTermMap.put("INV_DUE_DT", invDueDt);
            }
        }
        return pmtTermMap;
    }

    private Map<String, String> getBillToCust(String billToCustCd) {
        Map<String, String> prmMap = new HashMap<String, String>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("billToCustCd", billToCustCd);
        Map<String, String> billToCustMap = (Map<String, String>) ssmBatchClient.queryObject("getBillToCust", prmMap);
        if (billToCustMap != null) {
            return billToCustMap;
        }
        throw new S21AbendException(NSBM0071E, new String[] {"BILL_TO_CUST" });
    }

    private String getCrCardCustRefNum(String sellToCustCd) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("sellToCustCd", sellToCustCd);
        return (String) ssmBatchClient.queryObject("getCrCardCustRefNum", prmMap);
    }

    // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//    private String getSlsRepTocCd(BigDecimal svcMachMstrPk, String tempEttlNum, String svcLineBizCd) {
    private String getSlsRepTocCd(BigDecimal svcMachMstrPk, String tempEttlNum, String svcLineBizCd, String sldByLineBizTpCd) {
    // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        // get TE start date
        TEMP_ETTLTMsg tempEttlTmsg = getTempEttlFindByKey(tempEttlNum);
        if (tempEttlTmsg == null) {
            throw new S21AbendException(NSBM0071E, new String[] {"TEMP_ETTL" });
        }

        List<DS_CONTR_DTLTMsg> contrDtlList = NSXC001001GetContr.getEttlAvalContrDtlByMachMstrPk(glblCmpyCd, svcMachMstrPk, tempEttlTmsg.effFromDt.getValue());
        BigDecimal dsContrPk = null;
        if (contrDtlList.size() == 0) {
            // get default SalesRep
            String slsRepTocCd = null;
            // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//            if (LINE_BIZ_TP.ESS.equals(svcLineBizCd)) {
//                slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_SLS_REP_TOC_CD_ESS, glblCmpyCd);
//            } else if (LINE_BIZ_TP.LFS.equals(svcLineBizCd)) {
//                slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_SLS_REP_TOC_CD_LFS, glblCmpyCd);
//            } else if (LINE_BIZ_TP.PPS.equals(svcLineBizCd)) {
//                slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_SLS_REP_TOC_CD_PPS, glblCmpyCd);
//            }
            if (LINE_BIZ_TP.ESS.equals(sldByLineBizTpCd)) {
                slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_SLS_REP_TOC_CD_ESS, glblCmpyCd);
            } else if (LINE_BIZ_TP.LFS.equals(sldByLineBizTpCd)) {
                slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_SLS_REP_TOC_CD_LFS, glblCmpyCd);
            } else if (LINE_BIZ_TP.PPS.equals(sldByLineBizTpCd)) {
                slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_SLS_REP_TOC_CD_PPS, glblCmpyCd);
            } else if (LINE_BIZ_TP.ESS.equals(svcLineBizCd)) {
                slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_SLS_REP_TOC_CD_ESS, glblCmpyCd);
            } else if (LINE_BIZ_TP.LFS.equals(svcLineBizCd)) {
                slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_SLS_REP_TOC_CD_LFS, glblCmpyCd);
            } else if (LINE_BIZ_TP.PPS.equals(svcLineBizCd)) {
                slsRepTocCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_SLS_REP_TOC_CD_PPS, glblCmpyCd);
            }
            // END 2018/09/10 K.Kitachi [QC#26260, MOD]
            // START 2018/09/10 K.Kitachi [QC#26260, DEL]
//            if (!ZYPCommonFunc.hasValue(slsRepTocCd)) {
//                throw new S21AbendException(NSBM0071E, new String[] {"DEF_SLS_REP_TOC_CD" });
//            }
            // END 2018/09/10 K.Kitachi [QC#26260, DEL]
            return slsRepTocCd;
        } else if (contrDtlList.size() == 1) {
            dsContrPk = contrDtlList.get(0).dsContrPk.getValue();
        } else {
            List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
            for (int i = 0; i < contrDtlList.size(); i++) {
                dsContrDtlPkList.add(contrDtlList.get(i).dsContrDtlPk.getValue());
            }
            dsContrPk = getDsContrPk(glblCmpyCd, dsContrDtlPkList);
        }
        DS_CONTRTMsg dsContrTmsg = getDsContrTMsg(dsContrPk);
        return dsContrTmsg.tocCd.getValue();
    }

    private BigDecimal getDsContrPk(String glblCmpyCd, List<BigDecimal> dsContrDtkPkList) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtkPkList", dsContrDtkPkList);
        ssmPrm.put("svcCovFeatCd", SVC_COV_FEAT.BILL_CD);
        ssmPrm.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmPrm.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        ssmPrm.put("dsContrDtlTpCdFleet", DS_CONTR_DTL_TP.FLEET);
        return (BigDecimal) ssmBatchClient.queryObject("getDsContrPk", ssmPrm);
    }

    private void updateTempEttl(String tempEttlNum) {
        TEMP_ETTLTMsg inMsg = new TEMP_ETTLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.tempEttlNum, tempEttlNum);
        inMsg = (TEMP_ETTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (inMsg == null) {
            throw new S21AbendException(NSBM0071E, new String[] {"TEMP_ETTL" });
        }
        ZYPEZDItemValueSetter.setValue(inMsg.allFsrCloFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inMsg.invCpltFlg, ZYPConstant.FLG_ON_Y);
        EZDTBLAccessor.update(inMsg);
    }

    private String getCoaProjCd(String glblCmpyCd, String mdseCd) {
        MDSETMsg tmsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.mdseCd, mdseCd);
        tmsg = (MDSETMsg) EZDTBLAccessor.findByKey(tmsg);
        if (tmsg != null) {
            return tmsg.coaMdseTpCd.getValue();
        }
        return null;
    }
    // END 2018/07/18 M.Naito [QC#13309, ADD]

    // add start 2018/08/09 QC#27329-3
    private void setNWZC040002PMsgOrigInv(NWZC040002PMsg shipPmsg, String origSvcInvNum, BigDecimal dsContrBllgSchdPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("svcInvNum", origSvcInvNum);
        ssmPrm.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        Map<String, Object> shipToMap = (Map<String, Object>) ssmBatchClient.queryObject("getOrigInvShipTo", ssmPrm);
        if (shipToMap == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToLocNm, (String) shipToMap.get("SHIP_TO_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToAddlLocNm, (String) shipToMap.get("SHIP_TO_ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToFirstLineAddr, (String) shipToMap.get("SHIP_TO_FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToScdLineAddr, (String) shipToMap.get("SHIP_TO_SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToThirdLineAddr, (String) shipToMap.get("SHIP_TO_THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToFrthLineAddr, (String) shipToMap.get("SHIP_TO_FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToStCd, (String) shipToMap.get("SHIP_TO_ST_CD"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToProvNm, (String) shipToMap.get("SHIP_TO_PROV_NM"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToCntyNm, (String) shipToMap.get("SHIP_TO_CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToFirstRefCmntTxt, (String) shipToMap.get("SHIP_TO_FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToScdRefCmntTxt, (String) shipToMap.get("SHIP_TO_SCD_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToPostCd, (String) shipToMap.get("SHIP_TO_POST_CD"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToCtyAddr, (String) shipToMap.get("SHIP_TO_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(shipPmsg.shipToCtryCd, (String) shipToMap.get("SHIP_TO_CTRY_CD"));
    }
    // add end 2018/08/09 QC#27329-3

    // START 2023/11/10 [QC#61468, ADD]
    private BigDecimal getInvTotDealFrtAmt(String svcInvNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("svcInvNum", svcInvNum);
        ssmPrm.put("chrgTpFC", SVC_INV_CHRG_TP.FREIGHT_CHARGE);
        return (BigDecimal) ssmBatchClient.queryObject("getInvTotDealFrtAmt", ssmPrm);
    }
    // END   2023/11/10 [QC#61468, ADD]
}
