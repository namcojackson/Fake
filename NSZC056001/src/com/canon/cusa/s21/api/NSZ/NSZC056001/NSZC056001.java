/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC056001;

import static com.canon.cusa.s21.api.NSZ.NSZC056001.constant.NSZC056001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPDateItem;
import parts.common.EZDPStringItem;
import business.db.ADDL_CHRG_INV_TPTMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.BLLG_CYCLETMsgArray;
import business.db.BLLG_SCHD_TEST_MTR_SMRYTMsg;
import business.db.CALC_MTR_SCHD_RELNTMsg;
import business.db.CCYTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsgArray;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLG_ALLOCTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsgArray;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsgArray;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;
import business.db.SVC_INV_CHRG_TPTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.parts.NSZC056001PMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.AddlChrgFromThruDtInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.CalcAddlChrgInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcAddlChrg;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcDate;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetAddlChrgFromThruDt;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIForAddlBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIForUsageBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForAddl;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForBase;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForUsage;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001Exchange;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001MtrCntTwoPntEst;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC004001.GetDefCoaTrxCdForOutListInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC004001.GetDefCoaTrxCdInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC004001.NSXC004001GetDefCoaTrxCd;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_PRC_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_BY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Billing Calculation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/30/2015   Hitachi         K.Kishimoto     Create
 * 01/25/2016   Hitachi         T.Kanasaka      Update          QC3623
 * 01/26/2016   Hitachi         K.Kishimoto     Update          QC3747
 * 03/08/2016   Hitachi         K.Kishimoto     Update          QC3406
 * 03/11/2016   Hitachi         K.Kishimoto     Update          QC5347
 * 03/15/2016   Hitachi         K.Kishimoto     Update          QC5492
 * 03/18/2016   Hitachi         K.Kishimoto     Update          QC5629
 * 03/18/2016   Hitachi         T.Kanasaka      Update          QC5720
 * 03/23/2016   Hitachi         K.Kishimoto     Update          QC5863
 * 03/24/2016   Hitachi         T.Aoyagi        Update          QC5901
 * 03/25/2016   Hitachi         K.Kishimoto     Update          QC5879
 * 03/28/2016   Hitachi         T.Kanasaka      Update          QC5879
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC6131
 * 03/30/2016   Hitachi         K.Kishimoto     Update          QC5740
 * 04/06/2016   Hitachi         K.Kishimoto     Update          QC6131
 * 04/07/2016   Hitachi         K.Kishimoto     Update          QC5720
 * 04/11/2016   Hitachi         T.Kanasaka      Update          QC6657
 * 04/13/2016   Hitachi         K.Kishimoto     Update          QC6657
 * 04/15/2016   Hitachi         K.yamada        Update          QC7057
 * 04/19/2016   Hitachi         K.Kishimoto     Update          QC7240
 * 04/27/2016   Hitachi         T.Aoyagi        Update          QC7578
 * 05/11/2016   Hitachi         K.Kishimoto     Update          QC7764
 * 05/12/2016   Hitachi         K.Kishimoto     Update          QC8183
 * 06/30/2016   Hitachi         T.Aoyagi        Update         QC10733
 * 08/24/2016   Hitachi         K.Kishimoto     Update         QC10733
 * 09/05/2016   Hitachi         K.Kishimoto     Update         QC14146
 * 09/27/2016   Hitachi         T.Tomita        Update         QC10787
 * 10/06/2016   Hitachi         T.Kanasaka      Update         QC12274
 * 02/15/2017   Hitachi         T.Mizuki        Update         QC17489
 * 06/28/2017   Hitachi         K.Kim           Update         QC18539
 * 06/30/2017   Hitachi         T.Kanasaka      Update         QC19581
 * 09/07/2017   Hitachi         K.Kojima        Update         QC18440
 * 09/14/2017   Hitachi         K.Kasai         Update         QC15134
 * 09/15/2017   Hitachi         K.Kojima        Update         QC21125
 * 2017/09/25   Hitachi         K.Kitachi       Update          QC#21186
 * 2017/10/06   Hitachi         E.Kameishi      Update         QC#18636
 * 2017/10/16   Hitachi         U.Kim           Update         QC#21584
 * 2017/10/20   Hitachi         K.Ochiai        Update         QC#18636
 * 2017/10/25   Hitachi         K.Kojima        Update          QC#21562
 * 2018/03/20   Hitachi         K.Kojima        Update          QC#23623
 * 2018/04/11   CITS            M.Naito         Update          QC#23378
 * 2018/05/14   Hitachi         K.Kitachi       Update          QC#23541
 * 2018/06/05   Hitachi         K.Kojima        Update          QC#21974
 * 2018/06/07   Hitachi         K.Kitachi       Update          QC#20750
 * 2018/08/29   Hitachi         K.Kojima        Update          QC#28012
 * 2018/09/07   Hitachi         K.Kojima        Update          QC#28107
 * 2018/10/31   Hitachi         K.Kojima        Update          QC#28999
 * 2019/01/17   Hitachi         E.Kameishi      Update          QC#29083
 * 2019/08/23   Hitachi         A.Kohinata      Update          QC#52934
 * 2019/09/04   Hitachi         T.Aoyagi        Update          QC#53124
 * 2019/11/08   Hitachi         K.Kim           Update          QC#54365
 * 2019/11/13   Hitachi         A.Kohinata      Update          QC#54606
 * 2021/05/07   CITS            T.Wada          Update          QC#58805
 * 2021/05/25   CITS            T.Wada          Update          QC#58805-4
 * 2022/04/07   CITS            T.Wada          Update          QC#59159
 * </pre>
 */

public class NSZC056001 extends S21ApiCommonBase {
    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** onBatTp */
    private ONBATCH_TYPE onBatType = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Daily Prorate Divide Rate */
    private BigDecimal dailyPrrtDivRate = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Currency Code */
    private String ccyCd = null;

    /** Sales Date */
    private String slsDt = null;
    
    /** CCY Scale */ 
    private int ccyScale = 2;

    // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
    private String tmpMtrReadDt = null;
    // END 2016/10/06 T.Kanasaka [QC#12274, ADD]

    // START 2018/09/07 K.Kojima [QC#28107,ADD]
    /** Log Output Flag */
    private String logOutputFlg = null;
    // END 2018/09/07 K.Kojima [QC#28107,ADD]

    // add start 2019/09/04 QC#53124
    /**
     * Contract LOB List
     */
    private List<String> contrLobList = new ArrayList<String>();

    /**
     * Is Exclude Test Copy
     */
    private boolean isExclTestCopy = false;
    // add end 2019/09/04 QC#53124

    /**
     * Constructor
     */
    public NSZC056001() {
        super();
    }

    /**
     * Billing Calculation API
     * @param list List<NSZC056001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NSZC056001PMsg> list, final ONBATCH_TYPE onBatchType) {

        for (NSZC056001PMsg param : list) {
            execute(param, onBatchType);
        }
    }

    /**
     * Billing Calculation API
     * @param pMsg {@link NSZC056001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(NSZC056001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        init(pMsg, onBatTp);

        if (MODE_BILLING_STAGE.equals(pMsg.xxModeCd.getValue())) {
            if (!inputCheckBiilingStage(pMsg)) {
                return;
            }
        } else if (MODE_PREVIEW_BILLING_API.equals(pMsg.xxModeCd.getValue())) {
            if (!inputPreviewBillingAPI(pMsg)) {
                return;
            }
        } else if (MODE_CREDIT_REBILL.equals(pMsg.xxModeCd.getValue())) {
            if (!inputCheckCreditRebill(pMsg)) {
                return;
            }
        } else {
            setErrMsg(pMsg, NSZM0039E);
        }
        execBilling(pMsg);
    }

    private void init(NSZC056001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);

        this.onBatType = onBatTp;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.dailyPrrtDivRate = getDailyPrrtDivRate(pMsg);
        this.glblCmpyCd = pMsg.glblCmpyCd.getValue();
        this.slsDt = pMsg.slsDt.getValue();

        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        this.logOutputFlg = pMsg.xxSetFlg_LG.getValue();
        // END 2018/09/07 K.Kojima [QC#28107,ADD]

        // add start 2019/09/04 QC#53124
        String contrLob = ZYPCodeDataUtil.getVarCharConstValue(CONTR_LOB_EXCL_TEST_COPY, this.glblCmpyCd);
        if (hasValue(contrLob)) {
            this.contrLobList = Arrays.asList(contrLob.split(","));
        }
        // add end 2019/09/04 QC#53124
    }

    private void execBilling(NSZC056001PMsg pMsg) {
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-execBilling start");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
        DS_CONTR_DTLTMsg dsContrDtlTmsg = getDsContrDtl(pMsg);
        DS_CONTRTMsg dsContrTmsg = getDsContr(dsContrDtlTmsg.glblCmpyCd.getValue(), dsContrDtlTmsg.dsContrPk.getValue());

        List<Map<String, Object>> rsltSchdMapList = getTargetDsContrBllgSchd(pMsg);
        if (rsltSchdMapList.size() == 0) {
            // TODO error Msg set
            return;
        }

        // add start 2019/09/04 QC#53124
        if (!MODE_CREDIT_REBILL.equals(pMsg.xxModeCd.getValue())) {
            String svcLineBizCd = (String) rsltSchdMapList.get(0).get("SVC_LINE_BIZ_CD");
            if (this.contrLobList.contains(svcLineBizCd)) {
                this.isExclTestCopy = true;
            }
        }
        // add end 2019/09/04 QC#53124

        this.ccyCd = (String) rsltSchdMapList.get(0).get("CCY_CD");
        this.ccyScale = getCcyScale();
        if (DS_CONTR_CATG.REGULAR.equals(dsContrTmsg.dsContrCatgCd.getValue())) {
            execRegular(pMsg, dsContrTmsg, rsltSchdMapList);
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrTmsg.dsContrCatgCd.getValue())) {
            execFleet(pMsg, dsContrTmsg, rsltSchdMapList);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTmsg.dsContrCatgCd.getValue())) {
            execAggregate(pMsg, dsContrTmsg, rsltSchdMapList);
        }
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-execBilling end");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
    }

    private void execRegular(NSZC056001PMsg pMsg, DS_CONTRTMsg dsContrTmsg, List<Map<String, Object>> rsltSchdMapList) {
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-execRegular start");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
        // mod start 2019/11/13 QC#54606
        //Map<String, Object> rsltSchdMap = rsltSchdMapList.get(0);
        for (Map<String, Object> rsltSchdMap : rsltSchdMapList) {
        // mod end 2019/11/13 QC#54606
        deleteTargetPeriod(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTmsg = getdsContrBllgSchd(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        SVC_CONTR_BLLGTMsg svcContrBllgTmsg = getSvcContrBllg(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            SVC_CONTR_BASE_BLLGTMsg svcContrBaseBllgTmsg = createSvcContrBaseBllg(pMsg, rsltSchdMap, svcContrBllgTmsg);
            setValue(svcContrBllgTmsg.bllgApvlCpltFlg, FLG_ON_Y);
            setValue(dsContrBllgSchdTmsg.dealTaxAmt, svcContrBaseBllgTmsg.dealTaxAmt);
            setValue(dsContrBllgSchdTmsg.funcTaxAmt, svcContrBaseBllgTmsg.funcTaxAmt);

        } else {
            SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTmsg = createMtrBllg(pMsg, rsltSchdMap, svcContrBllgTmsg);
            // START 2017/09/15 K.Kojima [QC#21125,ADD]
            if (svcContrMtrBllgTmsg == null) {
                return;
            }
            // END 2017/09/15 K.Kojima [QC#21125,ADD]
            //Del Start 03/18/2016 <QC#5629>
//            setValue(svcContrBllgTmsg.bllgApvlCpltFlg, FLG_OFF_N);
            //Del End   03/18/2016 <QC#5629>
            setValue(dsContrBllgSchdTmsg.readMtrCnt, svcContrMtrBllgTmsg.mtrCopyQty);
            setValue(dsContrBllgSchdTmsg.bllgMtrCnt, svcContrMtrBllgTmsg.bllgCopyQty);
            setValue(dsContrBllgSchdTmsg.mtrChrgDealAmt, svcContrMtrBllgTmsg.mtrChrgDealAmt);
            // Add Start 04/13/2016 <QC#6657>
            setValue(dsContrBllgSchdTmsg.mtrChrgFuncAmt, svcContrMtrBllgTmsg.mtrChrgFuncAmt);
            // Add End   04/13/2016 <QC#6657>
            setValue(dsContrBllgSchdTmsg.dealTaxAmt, svcContrMtrBllgTmsg.dealTaxAmt);
            setValue(dsContrBllgSchdTmsg.funcTaxAmt, svcContrMtrBllgTmsg.funcTaxAmt);
            // START 2019/11/08 [QC#54365,ADD]
            if (!hasValue(dsContrBllgSchdTmsg.svcPhysMtrReadGrpSq) && DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL.equals(dsContrBllgSchdTmsg.dsBllgSchdTpCd.getValue())) {
                setValue(dsContrBllgSchdTmsg.svcPhysMtrReadGrpSq, svcContrMtrBllgTmsg.svcPhysMtrReadGrpSq);
                setValue(dsContrBllgSchdTmsg.mtrEntryCpltFlg, FLG_ON_Y);
            }
            // END 2019/11/08 [QC#54365,ADD]
        }
        setValue(svcContrBllgTmsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
        S21ApiTBLAccessor.update(svcContrBllgTmsg);
        setValue(dsContrBllgSchdTmsg.bllgCalcFlg, FLG_ON_Y);
        setValue(dsContrBllgSchdTmsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
        S21ApiTBLAccessor.update(dsContrBllgSchdTmsg);
        // add start 2019/11/13 QC#54606
        }
        // add end 2019/11/13 QC#54606
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-execRegular end");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
    }

    private void execFleet(NSZC056001PMsg pMsg, DS_CONTRTMsg dsContrTmsg, List<Map<String, Object>> rsltSchdMapList) {
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-execFleet start");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
        // mod start 2019/11/13 QC#54606
        //Map<String, Object> rsltSchdMap = rsltSchdMapList.get(0);
        for (Map<String, Object> rsltSchdMap : rsltSchdMapList) {
        // mod end 2019/11/13 QC#54606
        deleteTargetPeriod(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTmsg = getdsContrBllgSchd(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        SVC_CONTR_BLLGTMsg svcContrBllgTmsg = getSvcContrBllg(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            SVC_CONTR_BASE_BLLGTMsg svcContrBaseBllgTmsg = createSvcContrBaseBllg(pMsg, rsltSchdMap, svcContrBllgTmsg);
            setValue(svcContrBllgTmsg.bllgApvlCpltFlg, FLG_ON_Y);
            setValue(dsContrBllgSchdTmsg.dealTaxAmt, svcContrBaseBllgTmsg.dealTaxAmt);
            setValue(dsContrBllgSchdTmsg.funcTaxAmt, svcContrBaseBllgTmsg.funcTaxAmt);

        } else {
            SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTmsg = createMtrBllg(pMsg, rsltSchdMap, svcContrBllgTmsg);
            // START 2017/09/15 K.Kojima [QC#21125,ADD]
            if (svcContrMtrBllgTmsg == null) {
                return;
            }
            // END 2017/09/15 K.Kojima [QC#21125,ADD]
            //Del Start 03/18/2016 <QC#5629>
//          setValue(svcContrBllgTmsg.bllgApvlCpltFlg, FLG_OFF_N);
            //Del End   03/18/2016 <QC#5629>
            setValue(dsContrBllgSchdTmsg.readMtrCnt, svcContrMtrBllgTmsg.mtrCopyQty);
            setValue(dsContrBllgSchdTmsg.bllgMtrCnt, svcContrMtrBllgTmsg.bllgCopyQty);
            setValue(dsContrBllgSchdTmsg.mtrChrgDealAmt, svcContrMtrBllgTmsg.mtrChrgDealAmt);
            // Add Start 04/13/2016 <QC#6657>
            setValue(dsContrBllgSchdTmsg.mtrChrgFuncAmt, svcContrMtrBllgTmsg.mtrChrgFuncAmt);
            // Add End   04/13/2016 <QC#6657>
            setValue(dsContrBllgSchdTmsg.dealTaxAmt, svcContrMtrBllgTmsg.dealTaxAmt);
            setValue(dsContrBllgSchdTmsg.funcTaxAmt, svcContrMtrBllgTmsg.funcTaxAmt);
        }
        setValue(svcContrBllgTmsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
        S21ApiTBLAccessor.update(svcContrBllgTmsg);
        setValue(dsContrBllgSchdTmsg.bllgCalcFlg, FLG_ON_Y);
        setValue(dsContrBllgSchdTmsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
        S21ApiTBLAccessor.update(dsContrBllgSchdTmsg);

        if (FLG_ON_Y.equals(pMsg.usgChrgFlg.getValue())) {
            List<Map<String, Object>> rsltSchdChildMapList = getTargetDsContrBllgSchdFleetAggregateChild(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
            for (Map<String, Object> rsltSchdChildMap : rsltSchdChildMapList) {
                DS_CONTR_BLLG_SCHDTMsg chidDsContrBllgSchdTmsg = getdsContrBllgSchd(pMsg, (BigDecimal) rsltSchdChildMap.get("DS_CONTR_BLLG_SCHD_PK"));
                deleteTargetPeriod(pMsg, (BigDecimal) rsltSchdChildMap.get("DS_CONTR_BLLG_SCHD_PK"));
                //Mod Start 04/06/2016 <QC#6131>
                SVC_CONTR_BLLGTMsg childSvcContrBllgTmsg = getSvcContrBllg(pMsg, (BigDecimal) rsltSchdChildMap.get("DS_CONTR_BLLG_SCHD_PK"));
                //Mod End   04/06/2016 <QC#6131>
                // add start 2017/06/30 CSA Defect#19581
                if (childSvcContrBllgTmsg == null) {
                    continue;
                }
                // add end 2017/06/30 CSA Defect#19581
                SVC_CONTR_MTR_BLLGTMsg chidSvcContMtrBllgTmsg = createSvcContrMtrBllgForFleetMachine(pMsg, rsltSchdChildMap, childSvcContrBllgTmsg);
                // START 2017/09/15 K.Kojima [QC#21125,ADD]
                if (chidSvcContMtrBllgTmsg == null) {
                    return;
                }
                // END 2017/09/15 K.Kojima [QC#21125,ADD]
                //Mod Start 03/28/2016 <QC#6131>
                setValue(chidDsContrBllgSchdTmsg.readMtrCnt, chidSvcContMtrBllgTmsg.mtrCopyQty);
                //Mod End   03/28/2016 <QC#6131>
                //Mod Start 03/18/2016 <QC#5720>
//                setValue(svcContrBllgTmsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
                setValue(childSvcContrBllgTmsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
                //Mod End 03/18/2016 <QC#5720>
                S21ApiTBLAccessor.update(childSvcContrBllgTmsg);
                setValue(chidDsContrBllgSchdTmsg.bllgCalcFlg, FLG_ON_Y);
                setValue(chidDsContrBllgSchdTmsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
                S21ApiTBLAccessor.update(chidDsContrBllgSchdTmsg);
            }
        }
        // add start 2019/11/13 QC#54606
        }
        // add end 2019/11/13 QC#54606
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-execFleet end");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
    }

    private void execAggregate(NSZC056001PMsg pMsg, DS_CONTRTMsg dsContrTmsg, List<Map<String, Object>> rsltSchdMapList) {
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-execAggregate start");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
        // mod start 2019/11/13 QC#54606
        //Map<String, Object> rsltSchdMap = rsltSchdMapList.get(0);
        for (Map<String, Object> rsltSchdMap : rsltSchdMapList) {
        // mod end 2019/11/13 QC#54606
        deleteTargetPeriod(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTmsg = getdsContrBllgSchd(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        SVC_CONTR_BLLGTMsg svcContrBllgTmsg = getSvcContrBllg(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            setValue(svcContrBllgTmsg.bllgApvlCpltFlg, FLG_ON_Y);
        //Del Start 03/18/2016 <QC#5629>
//        } else {
//            setValue(svcContrBllgTmsg.bllgApvlCpltFlg, FLG_OFF_N);
         //Del End   03/18/2016 <QC#5629>
        }
        setValue(svcContrBllgTmsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
        S21ApiTBLAccessor.update(svcContrBllgTmsg);
        setValue(dsContrBllgSchdTmsg.bllgCalcFlg, FLG_ON_Y);
        setValue(dsContrBllgSchdTmsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
        S21ApiTBLAccessor.update(dsContrBllgSchdTmsg);

        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            List<Map<String, Object>> rsltSchdChildMapList = getTargetDsContrBllgSchdFleetAggregateChild(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
            for (Map<String, Object> rsltSchdChildMap : rsltSchdChildMapList) {
                DS_CONTR_BLLG_SCHDTMsg chidDsContrBllgSchdTmsg = getdsContrBllgSchd(pMsg, (BigDecimal) rsltSchdChildMap.get("DS_CONTR_BLLG_SCHD_PK"));
                deleteTargetPeriod(pMsg, (BigDecimal) rsltSchdChildMap.get("DS_CONTR_BLLG_SCHD_PK"));
                SVC_CONTR_BLLGTMsg childSvcContrBllgTmsg = getSvcContrBllg(pMsg, (BigDecimal) rsltSchdChildMap.get("DS_CONTR_BLLG_SCHD_PK"));
                // add start 2017/06/30 CSA Defect#19581
                if (childSvcContrBllgTmsg == null) {
                    continue;
                }
                // add end 2017/06/30 CSA Defect#19581
                SVC_CONTR_BASE_BLLGTMsg chidSvcContBaseBllgTmsg = createSvcContrBaseBllg(pMsg, rsltSchdChildMap, childSvcContrBllgTmsg);
                //Mod Start 03/18/2016 <QC#5720>
//                setValue(svcContrBllgTmsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
                setValue(childSvcContrBllgTmsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
                //Mod End 03/18/2016 <QC#5720>
                S21ApiTBLAccessor.update(childSvcContrBllgTmsg);
                //Mod Start 04/07/2016 <QC#5720>
                setValue(chidDsContrBllgSchdTmsg.dealTaxAmt, chidSvcContBaseBllgTmsg.dealTaxAmt);
                setValue(chidDsContrBllgSchdTmsg.funcTaxAmt, chidSvcContBaseBllgTmsg.funcTaxAmt);
                setValue(chidDsContrBllgSchdTmsg.bllgCalcFlg, FLG_ON_Y);
                setValue(chidDsContrBllgSchdTmsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
                S21ApiTBLAccessor.update(chidDsContrBllgSchdTmsg);
                createBaseBllgForAggAcc(pMsg, rsltSchdChildMap);
                //Mod End 04/07/2016 <QC#5720>
            }
        } else {
            List<Map<String, Object>> rsltSchdChildMapList = getTargetDsContrBllgSchdFleetAggregateChild(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
            for (Map<String, Object> rsltSchdChildMap : rsltSchdChildMapList) {
                DS_CONTR_BLLG_SCHDTMsg chidDsContrBllgSchdTmsg = getdsContrBllgSchd(pMsg, (BigDecimal) rsltSchdChildMap.get("DS_CONTR_BLLG_SCHD_PK"));
                deleteTargetPeriod(pMsg, (BigDecimal) rsltSchdChildMap.get("DS_CONTR_BLLG_SCHD_PK"));
                SVC_CONTR_BLLGTMsg childSvcContrBllgTmsg = getSvcContrBllg(pMsg, (BigDecimal) rsltSchdChildMap.get("DS_CONTR_BLLG_SCHD_PK"));
                // add start 2017/06/30 CSA Defect#19581
                if (childSvcContrBllgTmsg == null) {
                    continue;
                }
                // add end 2017/06/30 CSA Defect#19581
                SVC_CONTR_MTR_BLLGTMsg chidSvcContMtrBllgTmsg = createMtrBllg(pMsg, rsltSchdChildMap, childSvcContrBllgTmsg);
                // START 2017/09/15 K.Kojima [QC#21125,ADD]
                if (chidSvcContMtrBllgTmsg == null) {
                    return;
                }
                // END 2017/09/15 K.Kojima [QC#21125,ADD]
                //Mod Start 03/28/2016 <QC#6131>
                setValue(chidDsContrBllgSchdTmsg.readMtrCnt, chidSvcContMtrBllgTmsg.mtrCopyQty);
                setValue(chidDsContrBllgSchdTmsg.bllgMtrCnt, chidSvcContMtrBllgTmsg.bllgCopyQty);
                //Mod End   03/28/2016 <QC#6131>
                // Add Start 04/13/2016 <QC#6657>
                setValue(chidDsContrBllgSchdTmsg.mtrChrgDealAmt, chidSvcContMtrBllgTmsg.mtrChrgDealAmt);
                setValue(chidDsContrBllgSchdTmsg.mtrChrgFuncAmt, chidSvcContMtrBllgTmsg.mtrChrgFuncAmt);
                // Add End   04/13/2016 <QC#6657>
                setValue(childSvcContrBllgTmsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
                S21ApiTBLAccessor.update(childSvcContrBllgTmsg);
                setValue(chidDsContrBllgSchdTmsg.bllgCalcFlg, FLG_ON_Y);
                setValue(chidDsContrBllgSchdTmsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
                // START 2019/11/08 [QC#54365,ADD]
                if (!hasValue(chidDsContrBllgSchdTmsg.svcPhysMtrReadGrpSq) && DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL.equals(chidDsContrBllgSchdTmsg.dsBllgSchdTpCd.getValue())) {
                    setValue(chidDsContrBllgSchdTmsg.svcPhysMtrReadGrpSq, chidSvcContMtrBllgTmsg.svcPhysMtrReadGrpSq);
                    setValue(chidDsContrBllgSchdTmsg.mtrEntryCpltFlg, FLG_ON_Y);
                }
                // END 2019/11/08 [QC#54365,ADD]
                S21ApiTBLAccessor.update(chidDsContrBllgSchdTmsg);
            }
        }
        // add start 2019/11/13 QC#54606
        }
        // add end 2019/11/13 QC#54606
        // START 2018/09/07 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-execAggregate end");
        // END 2018/09/07 K.Kojima [QC#28107,ADD]
    }

    //Add Start 04/07/2016 <QC#5720>
    private void createBaseBllgForAggAcc(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdAggMachMap) {
        List<Map<String, Object>> rsltSchdAggAccMapList = getTargetDsContrBllgSchdFleetAggregateChild(pMsg, (BigDecimal) rsltSchdAggMachMap.get("DS_CONTR_BLLG_SCHD_PK"));
        for (Map<String, Object> rsltSchdAggAccMap : rsltSchdAggAccMapList) {
            DS_CONTR_BLLG_SCHDTMsg chidDsContrBllgSchdTmsg = getdsContrBllgSchd(pMsg, (BigDecimal) rsltSchdAggAccMap.get("DS_CONTR_BLLG_SCHD_PK"));
            deleteTargetPeriod(pMsg, (BigDecimal) rsltSchdAggAccMap.get("DS_CONTR_BLLG_SCHD_PK"));
            SVC_CONTR_BLLGTMsg childSvcContrBllgTmsg = getSvcContrBllg(pMsg, (BigDecimal) rsltSchdAggAccMap.get("DS_CONTR_BLLG_SCHD_PK"));
            // add start 2017/06/30 CSA Defect#19581
            if (childSvcContrBllgTmsg == null) {
                continue;
            }
            // add end 2017/06/30 CSA Defect#19581
            SVC_CONTR_BASE_BLLGTMsg chidSvcContBaseBllgTmsg = createSvcContrBaseBllg(pMsg, rsltSchdAggAccMap, childSvcContrBllgTmsg);
            setValue(childSvcContrBllgTmsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
            S21ApiTBLAccessor.update(childSvcContrBllgTmsg);
            setValue(chidDsContrBllgSchdTmsg.dealTaxAmt, chidSvcContBaseBllgTmsg.dealTaxAmt);
            setValue(chidDsContrBllgSchdTmsg.funcTaxAmt, chidSvcContBaseBllgTmsg.funcTaxAmt);
            setValue(chidDsContrBllgSchdTmsg.bllgCalcFlg, FLG_ON_Y);
            setValue(chidDsContrBllgSchdTmsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.BILLED);
            S21ApiTBLAccessor.update(chidDsContrBllgSchdTmsg);
        }
    }
    //Add End  04/07/2016 <QC#5720>

    private SVC_CONTR_BASE_BLLGTMsg createSvcContrBaseBllg(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, SVC_CONTR_BLLGTMsg svcContrBllgTmsg) {
        SVC_CONTR_BASE_BLLGTMsg inMsg = new SVC_CONTR_BASE_BLLGTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.svcContrBaseBllgPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BASE_BLLG_SQ));
        setValue(inMsg.svcContrBllgPk, svcContrBllgTmsg.svcContrBllgPk);
        setValue(inMsg.dsContrDtlPk, (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
        BigDecimal dealAmt = (BigDecimal) rsltSchdMap.get("BASE_ACTL_PRC_DEAL_AMT");
        setValue(inMsg.baseDealAmt, setAmtScale(dealAmt));
        BigDecimal funcAmt = setAmtScale(calcFuncAmtFromDealAmt(dealAmt));
        setValue(inMsg.baseFuncAmt, funcAmt);
        setValue(inMsg.baseDiscDealAmt, BigDecimal.ZERO);
        setValue(inMsg.baseDiscFuncAmt, BigDecimal.ZERO);
        setValue(inMsg.mdseCd, (String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
        setValue(inMsg.ccyCd, (String) rsltSchdMap.get("CCY_CD"));
        // Mod Start 03/23/2016 <QC#5863>
        setValue(inMsg.baseBllgFromDt, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"));
        setValue(inMsg.baseBllgThruDt, (String) rsltSchdMap.get("BLLG_SCHD_THRU_DT"));
        // Mod End   03/23/2016 <QC#5863>
        setValue(inMsg.basePerMthAot, calcPerMthAot(rsltSchdMap));
        setValue(inMsg.slsTaxRate, BigDecimal.ZERO);
        setValue(inMsg.dealTaxAmt, BigDecimal.ZERO);
        setValue(inMsg.funcTaxAmt, BigDecimal.ZERO);
        if (BigDecimal.ZERO.compareTo(funcAmt) < 0) {
            NWZC036101PMsg taxApiPMsg = new NWZC036101PMsg();
            // START 2018/09/07 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createSvcContrBaseBllg-callTaxCalcAPIForBase start");
            // END 2018/09/07 K.Kojima [QC#28107,ADD]
            taxApiPMsg = callTaxCalcAPIForBase(rsltSchdMap, funcAmt);
            // START 2018/09/07 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createSvcContrBaseBllg-callTaxCalcAPIForBase end");
            // END 2018/09/07 K.Kojima [QC#28107,ADD]
            if (taxApiPMsg != null) {
                if (!S21ApiUtil.isXxMsgId(taxApiPMsg)) {
                    BigDecimal baseTaxFuncAmt = setAmtScale(getTaxFuncAmt(taxApiPMsg));
                    BigDecimal baseTaxDealAmt = calcDealFromFunc(baseTaxFuncAmt);
                    setValue(inMsg.slsTaxRate, getTaxRate(taxApiPMsg));
                    setValue(inMsg.dealTaxAmt, baseTaxDealAmt);
                    setValue(inMsg.funcTaxAmt, baseTaxFuncAmt);
                }
            }
        }
        setValue(inMsg.basePrcAdjDealAmt, setAmtScale((BigDecimal) rsltSchdMap.get("BASE_PRC_ADJ_DEAL_AMT")));
        setValue(inMsg.basePrcAdjFuncAmt, setAmtScale((BigDecimal) rsltSchdMap.get("BASE_PRC_ADJ_FUNC_AMT")));
        S21ApiTBLAccessor.create(inMsg);
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-createSvcContrBaseBllg-insertSvcContrBllgAllocForBase start");
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        insertSvcContrBllgAllocForBase(pMsg, rsltSchdMap, inMsg);
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-createSvcContrBaseBllg-insertSvcContrBllgAllocForBase end");
        // END 2018/09/10 K.Kojima [QC#28107,ADD]

        createAddlBllgBy(pMsg, rsltSchdMap, inMsg.baseDealAmt.getValue(), inMsg.svcContrBaseBllgPk.getValue(), null, SVC_BILL_BY_TP.BASE);
        return inMsg;
    }

    private SVC_CONTR_MTR_BLLGTMsg createMtrBllg(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, SVC_CONTR_BLLGTMsg svcContrBllgTmsg) {
        SVC_CONTR_MTR_BLLGTMsg inMsg = createSvcContrMtrBllg(pMsg, rsltSchdMap, svcContrBllgTmsg);
        if (inMsg == null) {
            return inMsg;
        }
        BigDecimal funcAmt = inMsg.mtrChrgFuncAmt.getValue();
        if (BigDecimal.ZERO.compareTo(funcAmt) < 0) {
            NWZC036101PMsg taxApiPMsg = new NWZC036101PMsg();
            // START 2018/09/07 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createMtrBllg-callTaxCalcAPIForUsg start");
            // END 2018/09/07 K.Kojima [QC#28107,ADD]
            taxApiPMsg = callTaxCalcAPIForUsg(rsltSchdMap, funcAmt);
            // START 2018/09/07 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createMtrBllg-callTaxCalcAPIForUsg end");
            // END 2018/09/07 K.Kojima [QC#28107,ADD]
            if (taxApiPMsg != null) {
                if (!S21ApiUtil.isXxMsgId(taxApiPMsg)) {
                    BigDecimal baseTaxFuncAmt = setAmtScale(getTaxFuncAmt(taxApiPMsg));
                    BigDecimal baseTaxDealAmt = calcDealFromFunc(baseTaxFuncAmt);
                    setValue(inMsg.slsTaxRate, getTaxRate(taxApiPMsg));
                    setValue(inMsg.dealTaxAmt, baseTaxDealAmt);
                    setValue(inMsg.funcTaxAmt, baseTaxFuncAmt);
                    S21ApiTBLAccessor.update(inMsg);
                }
            }
        }
        createAddlBllgBy(pMsg, rsltSchdMap, inMsg.mtrChrgDealAmt.getValue(), null, inMsg.svcContrMtrBllgPk.getValue(), SVC_BILL_BY_TP.USAGE);
        return inMsg;
    }

    private void createAddlBllgBy(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, BigDecimal srcPrcAmt, BigDecimal svcContrBaseBllgPk, BigDecimal svcContrMtrBllgPk, String  svcBillByTpCd) {
        // Bill By Contract
        List<Map<String, Object>> billByContrList = getDsContrAddlChrg(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_PK"), null, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (String) rsltSchdMap.get("BLLG_SCHD_THRU_DT"),
                SVC_BILL_BY_TP.CONTRACT);
        if (billByContrList.size() > 0) {
            createAddlBllgByContr(pMsg, rsltSchdMap, srcPrcAmt, svcContrBaseBllgPk, svcContrMtrBllgPk, billByContrList);
        }
        // Bill By Machine
        List<Map<String, Object>> billByMachList = getDsContrAddlChrg(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_PK"), (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"), (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (String) rsltSchdMap
                .get("BLLG_SCHD_THRU_DT"), SVC_BILL_BY_TP.MACHINE);
        if (billByMachList.size() == 0) {
            billByMachList = getDsContrAddlChrg(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_PK"), null, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (String) rsltSchdMap
                    .get("BLLG_SCHD_THRU_DT"), SVC_BILL_BY_TP.MACHINE);
        } 
        if (billByMachList.size() > 0) {
            createAddlBllgByMach(pMsg, rsltSchdMap, srcPrcAmt, svcContrBaseBllgPk, svcContrMtrBllgPk, billByMachList);
        }
        // Bill By Base/Usage
        List<Map<String, Object>> billByBaseUsgList = getDsContrAddlChrg(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_PK"), (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"), (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (String) rsltSchdMap
                .get("BLLG_SCHD_THRU_DT"), svcBillByTpCd);
        if (billByBaseUsgList.size() == 0) {
            billByBaseUsgList = getDsContrAddlChrg(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_PK"), null, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (String) rsltSchdMap
                    .get("BLLG_SCHD_THRU_DT"), svcBillByTpCd);
        } 
        if (billByBaseUsgList.size() > 0) {
            createAddlBllgByBaseUsg(pMsg, rsltSchdMap, srcPrcAmt, svcContrBaseBllgPk, svcContrMtrBllgPk, billByBaseUsgList);
        }
    }

    private void createAddlBllgByContr(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, BigDecimal srcPrcAmt, BigDecimal svcContrBaseBllgPk, BigDecimal svcContrMtrBllgPk, List<Map<String, Object>> billByContrList) {
        BigDecimal dsContrPk = (BigDecimal) rsltSchdMap.get("DS_CONTR_PK");
        for (Map<String, Object> billByContr : billByContrList) {
            BigDecimal flatAmt = null2Zero((BigDecimal) billByContr.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
            BigDecimal dsContrAddlChrgPk = (BigDecimal) billByContr.get("DS_CONTR_ADDL_CHRG_PK");
            AddlChrgFromThruDtInfo addlChrgFromThruDtinfo = getAddlChrgFromThruDtinfo(pMsg, rsltSchdMap, billByContr);
            if (!hasValue(addlChrgFromThruDtinfo.getAddlChrgFromDt())) {
                continue;
            }
            if (BigDecimal.ZERO.compareTo(flatAmt) != 0) {
            	// Mod Start 09/05/2016 <QC#14146>
                // START 2018/10/31 K.Kojima [QC#28999,MOD]
                // if (hasSameAddl(pMsg, dsContrPk, null, dsContrAddlChrgPk, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (String) rsltSchdMap.get("BLLG_SCHD_THRU_DT"))) {
                if (hasSameAddl(pMsg, dsContrPk, null, dsContrAddlChrgPk, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (String) rsltSchdMap.get("BLLG_SCHD_THRU_DT"), (String) rsltSchdMap.get("INV_TP_CD"))) {
                // END 2018/10/31 K.Kojima [QC#28999,MOD]
                    continue;
                }
            	// Mod End   09/05/2016 <QC#14146>
            }
            createAddlChrg(pMsg, rsltSchdMap, billByContr, addlChrgFromThruDtinfo, srcPrcAmt, svcContrBaseBllgPk, svcContrMtrBllgPk);
        }
    }

    private void createAddlBllgByMach(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, BigDecimal srcPrcAmt, BigDecimal svcContrBaseBllgPk, BigDecimal svcContrMtrBllgPk, List<Map<String, Object>> billByMachList) {
        BigDecimal dsContrPk = (BigDecimal) rsltSchdMap.get("DS_CONTR_PK");
        BigDecimal dsContrDtlPk = (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK");
        for (Map<String, Object> billByMach : billByMachList) {
            BigDecimal flatAmt = null2Zero((BigDecimal) billByMach.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
            BigDecimal dsContrAddlChrgPk = (BigDecimal) billByMach.get("DS_CONTR_ADDL_CHRG_PK");
            AddlChrgFromThruDtInfo addlChrgFromThruDtinfo = getAddlChrgFromThruDtinfo(pMsg, rsltSchdMap, billByMach);
            if (!hasValue(addlChrgFromThruDtinfo.getAddlChrgFromDt())) {
                continue;
            }
            if (BigDecimal.ZERO.compareTo(flatAmt) != 0) {
            	// Mod Start 09/05/2016 <QC#14146>
                // START 2018/10/31 K.Kojima [QC#28999,MOD]
                // if (hasSameAddl(pMsg, dsContrPk, dsContrDtlPk, dsContrAddlChrgPk, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (String) rsltSchdMap.get("BLLG_SCHD_THRU_DT"))) {
                if (hasSameAddl(pMsg, dsContrPk, dsContrDtlPk, dsContrAddlChrgPk, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (String) rsltSchdMap.get("BLLG_SCHD_THRU_DT"), (String) rsltSchdMap.get("INV_TP_CD"))) {
                // END 2018/10/31 K.Kojima [QC#28999,MOD]
                    continue;
                }
            	// Mod End   09/05/2016 <QC#14146>
            }
            createAddlChrg(pMsg, rsltSchdMap, billByMach, addlChrgFromThruDtinfo, srcPrcAmt, svcContrBaseBllgPk, svcContrMtrBllgPk);
        }
    }

    private void createAddlBllgByBaseUsg(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, BigDecimal srcPrcAmt, BigDecimal svcContrBaseBllgPk, BigDecimal svcContrMtrBllgPk, List<Map<String, Object>> addlChrgList) {
        for (Map<String, Object> addlCrhg : addlChrgList) {
            AddlChrgFromThruDtInfo addlChrgFromThruDtinfo = getAddlChrgFromThruDtinfo(pMsg, rsltSchdMap, addlCrhg);
            if (!hasValue(addlChrgFromThruDtinfo.getAddlChrgFromDt())) {
                continue;
            }
            createAddlChrg(pMsg, rsltSchdMap, addlCrhg, addlChrgFromThruDtinfo, srcPrcAmt, svcContrBaseBllgPk, svcContrMtrBllgPk);
        }
    }

    private BigDecimal null2Zero(BigDecimal val) {
        if (hasValue(val)) {
            return val;
        }
        return BigDecimal.ZERO;
    }

    private AddlChrgFromThruDtInfo getAddlChrgFromThruDtinfo(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, Map<String, Object> rsltAddlChrg) {
        NSXC001001GetAddlChrgFromThruDt getAddlChrgFromThruDt = new NSXC001001GetAddlChrgFromThruDt();
        AddlChrgFromThruDtInfo addlChrgFromThruDtinfo = new AddlChrgFromThruDtInfo();
        addlChrgFromThruDtinfo.setEffFromDt((String) rsltAddlChrg.get("EFF_FROM_DT"));
        addlChrgFromThruDtinfo.setEffThruDt((String) rsltAddlChrg.get("EFF_THRU_DT"));
        addlChrgFromThruDtinfo.setTrmnDt((String) rsltAddlChrg.get("TRMN_DT"));
        // Mod Start 09/05/2016 <QC#14146>
        String invUpToDt = (String) rsltAddlChrg.get("INV_UP_TO_DT");
        String preCalcAddlNextBllgDt = getPreCalcAddlNextBllgDt(pMsg, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), (BigDecimal) rsltAddlChrg.get("DS_CONTR_ADDL_CHRG_PK"));
        if (!hasValue(invUpToDt) || invUpToDt.compareTo(preCalcAddlNextBllgDt) < 0) {
            invUpToDt = preCalcAddlNextBllgDt;
        }
        addlChrgFromThruDtinfo.setInvUpToDt(invUpToDt);
        // Mod End   09/05/2016 <QC#14146>
        // Add Start 03/30/2016 <QC#5740>
        addlChrgFromThruDtinfo.setBllgFromDt((String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"));
        // Add End   03/30/2016 <QC#5740>
        addlChrgFromThruDtinfo.setBllgThruDt((String) rsltSchdMap.get("BLLG_SCHD_THRU_DT"));
        addlChrgFromThruDtinfo.setBllgCycleMthAot((BigDecimal) rsltAddlChrg.get("BLLG_CYCLE_MTH_AOT"));
        getAddlChrgFromThruDt.getAddlChrgFromThruDt(addlChrgFromThruDtinfo);
        return addlChrgFromThruDtinfo;
    }

    // Add Start 09/05/2016 <QC#14146>
    private String getPreCalcAddlNextBllgDt(NSZC056001PMsg pMsg, String effFromDt, BigDecimal dsContrAddlChrgPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrAddlChrgPk", dsContrAddlChrgPk);
        param.put("effFromDt", effFromDt);
        @SuppressWarnings("unchecked")
        String preCalcThruDt = (String) ssmBatchClient.queryObject("getPreCalcAddlNextBllgDt", param);
        if (!hasValue(preCalcThruDt)) {
            preCalcThruDt ="";
        }
        return preCalcThruDt;
    }
    // Add End   09/05/2016 <QC#14146>

    private SVC_CONTR_MTR_BLLGTMsg createSvcContrMtrBllg(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, SVC_CONTR_BLLGTMsg svcContrBllgTmsg) {
        Map<String, Object> prevSchd = getPrevSchd(pMsg, rsltSchdMap);
        BigDecimal currPhysMtrReadGrpSq = null;
        BigDecimal prevPhysMtrReadGrpSq = null;

        BigDecimal currBllgMtrCnt = BigDecimal.ZERO;
        BigDecimal prevBllgMtrCnt = BigDecimal.ZERO;
        BigDecimal contrMtrMultRate = BigDecimal.ZERO;
        // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
        BigDecimal testCopyQty = BigDecimal.ZERO;
        // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
        //START 2017/09/12 K.Kasai [QC#15134,ADD]
        BigDecimal adjBllgMtrCnt = BigDecimal.ZERO;
        //END 2017/09/12 K.Kasai [QC#15134,ADD]
        // mod start 2016/04/15 CSA Defect#7057
        if (!MODE_CREDIT_REBILL.equals(pMsg.xxModeCd.getValue())) {
            if (DS_CONTR_CATG.FLEET.equals((String) rsltSchdMap.get("DS_CONTR_CATG_CD"))) {
                Map<String, Object> flletReadRollUp = getFleetReadRollUp(pMsg, rsltSchdMap);
                if (flletReadRollUp != null) {
                    currPhysMtrReadGrpSq = (BigDecimal) flletReadRollUp.get("SVC_PHYS_MTR_READ_GRP_SQ");
                    prevPhysMtrReadGrpSq = (BigDecimal) flletReadRollUp.get("PRI_PHYS_MTR_READ_GRP_SQ");
                    currBllgMtrCnt = (BigDecimal) flletReadRollUp.get("CUR_FLEET_READ_MTR_CNT");
                    prevBllgMtrCnt = (BigDecimal) flletReadRollUp.get("PRI_FLEET_READ_MTR_CNT");
                    // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                    testCopyQty = (BigDecimal) flletReadRollUp.get("TEST_COPY_QTY");
                    // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
                }
            } else {
                currPhysMtrReadGrpSq = (BigDecimal) rsltSchdMap.get("SVC_PHYS_MTR_READ_GRP_SQ");
                currBllgMtrCnt = getBllgMtrCnt(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
                // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                String prevMtrReadDt = null;
                String currMtrReadDt = this.tmpMtrReadDt;
                // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
                //START 2017/09/12 K.Kasai [QC#15134,ADD]
                CONTR_PHYS_BLLG_MTR_RELNTMsgArray svcPhysMtrPkArray = getSvcPhysMtrPk(pMsg, rsltSchdMap);
                BigDecimal svcPhysMtrPk = svcPhysMtrPkArray.no(0).svcPhysMtrPk.getValue();
                //END 2017/09/12 K.Kasai [QC#15134,ADD]
                if (prevSchd == null) {
                    // Start Meter count
                    BigDecimal dsContrDtlPk = (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK");
                    BigDecimal svcMachMstrPk = (BigDecimal) rsltSchdMap.get("SVC_MACH_MSTR_PK");
                    //START 2017/09/12 K.Kasai [QC#15134,DEL]
//                    BigDecimal svcPhysMtrPk = getSvcPhysMtrPk(pMsg, rsltSchdMap);
                    //END 2017/09/12 K.Kasai [QC#15134,DEL]
                    if (svcPhysMtrPk != null) {
                        prevPhysMtrReadGrpSq = getStartMtrGrpSq(pMsg, dsContrDtlPk, svcMachMstrPk, svcPhysMtrPk);
                    }
                    if (prevPhysMtrReadGrpSq == null) {
                        prevBllgMtrCnt = BigDecimal.ZERO;
                        // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                        prevMtrReadDt = ZYPDateUtil.addDays((String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), -1);
                        // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
                    } else {
                        prevBllgMtrCnt = getStartMtrCnt(pMsg, prevPhysMtrReadGrpSq, rsltSchdMap);
                        // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                        prevMtrReadDt = ZYPDateUtil.addDays(this.tmpMtrReadDt, -1);
                        // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
                    }
                } else {
                    prevBllgMtrCnt = getBllgMtrCnt(pMsg, (BigDecimal) prevSchd.get("DS_CONTR_BLLG_SCHD_PK"));
                    prevPhysMtrReadGrpSq = (BigDecimal) prevSchd.get("SVC_PHYS_MTR_READ_GRP_SQ");
                    // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                    prevMtrReadDt = this.tmpMtrReadDt;
                    // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
                }

                // START 2017/09/15 K.Kojima [QC#21125,ADD]
                if (!ZYPCommonFunc.hasValue(prevMtrReadDt)) {
                    setErrMsg(pMsg, NSZM1299E);
                    return null;
                }
                // END 2017/09/15 K.Kojima [QC#21125,ADD]

                // START 2018/06/07 K.Kitachi [QC#20750, MOD]
                // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                testCopyQty = getTestCopyQty(rsltSchdMap, prevMtrReadDt, currMtrReadDt);
                // END 2016/10/06 T.Kanasaka [QC#12274, ADD]

                // add start 2019/09/04 QC#53124
                if (this.isExclTestCopy) {
                    testCopyQty = BigDecimal.ZERO;
                }
                // add end 2019/09/04 QC#53124

                //START 2017/09/12 K.Kasai [QC#15134,ADD]
                adjBllgMtrCnt = getAdjBllgMtrCnt(prevPhysMtrReadGrpSq, currPhysMtrReadGrpSq, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"), (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"), svcPhysMtrPkArray, null, null);
                //END 2017/09/12 K.Kasai [QC#15134,ADD]
                // END 2018/06/07 K.Kitachi [QC#20750, MOD]
            }
        } else if (MODE_CREDIT_REBILL.equals(pMsg.xxModeCd.getValue())) {
            // add start 2016/04/27 CSA Defect#7578
            if (DS_CONTR_CATG.FLEET.equals((String) rsltSchdMap.get("DS_CONTR_CATG_CD"))) {
                Map<String, Object> flletReadRollUp = getFleetReadRollUp(pMsg, rsltSchdMap);
                if (flletReadRollUp != null) {
                    currPhysMtrReadGrpSq = (BigDecimal) flletReadRollUp.get("SVC_PHYS_MTR_READ_GRP_SQ");
                    prevPhysMtrReadGrpSq = (BigDecimal) flletReadRollUp.get("PRI_PHYS_MTR_READ_GRP_SQ");
                    currBllgMtrCnt = (BigDecimal) flletReadRollUp.get("CUR_FLEET_READ_MTR_CNT");
                    prevBllgMtrCnt = (BigDecimal) flletReadRollUp.get("PRI_FLEET_READ_MTR_CNT");
                    // START 2017/10/20 K.Ochiai [QC#18636, ADD]
                    BigDecimal tmpTestMtrCnt = (BigDecimal) flletReadRollUp.get("TEST_COPY_QTY");
                    testCopyQty = testCopyQty.add(tmpTestMtrCnt);
                    // END 2017/10/20 K.Ochiai [QC#18636, ADD]
                }
            } else {
            // add end 2016/04/27 CSA Defect#7578
                List<Map<String, Object>> rebilMtrMapList = getCrRebilMtrCnt(pMsg, rsltSchdMap);
                for (Map<String, Object> rebilMtrMap : rebilMtrMapList) {
                    BigDecimal startCnt = BigDecimal.ZERO;
                    BigDecimal endCnt = BigDecimal.ZERO;
                    BigDecimal newStartCnt = (BigDecimal) rebilMtrMap.get("NEW_START_READ_MTR_CNT");
                    BigDecimal newEndCnt = (BigDecimal) rebilMtrMap.get("NEW_END_READ_MTR_CNT");
                    BigDecimal oldStartCnt = (BigDecimal) rebilMtrMap.get("OLD_START_READ_MTR_CNT");
                    BigDecimal oldEndCnt = (BigDecimal) rebilMtrMap.get("OLD_END_READ_MTR_CNT");
                    BigDecimal mtrMultRate = (BigDecimal) rebilMtrMap.get("CONTR_MTR_MULT_RATE");
                    contrMtrMultRate = mtrMultRate;
                    // Mod Start 05/11/2016 <QC#7764>
                    BigDecimal newStartGrpSq = (BigDecimal) rebilMtrMap.get("NEW_START_MTR_READ_GRP_SQ");
                    BigDecimal newEndGrpSq = (BigDecimal) rebilMtrMap.get("NEW_END_MTR_READ_GRP_SQ");
                    BigDecimal oldStartGrpSq = (BigDecimal) rebilMtrMap.get("OLD_START_MTR_READ_GRP_SQ");
                    BigDecimal OldEndGrpSq = (BigDecimal) rebilMtrMap.get("OLD_END_MTR_READ_GRP_SQ");
                    if (hasValue(newStartCnt)) {
                        startCnt = newStartCnt;
                    } else if (hasValue(oldStartCnt)) {
                        startCnt = oldStartCnt;
                    }
                    if (hasValue(newEndCnt)) {
                        endCnt = newEndCnt;
                    } else if (hasValue(oldEndCnt)) {
                        endCnt = oldEndCnt;
                    }
                    if (hasValue(newStartGrpSq)) {
                        prevPhysMtrReadGrpSq = newStartGrpSq;
                    } else if (hasValue(oldStartGrpSq)) {
                        prevPhysMtrReadGrpSq = oldStartGrpSq;
                    }
                    if (hasValue(newEndGrpSq)) {
                        currPhysMtrReadGrpSq = newEndGrpSq;
                    } else if (hasValue(oldEndCnt)) {
                        currPhysMtrReadGrpSq = OldEndGrpSq;
                    }
                    // Mod End   05/11/2016 <QC#7764>
                    prevBllgMtrCnt = prevBllgMtrCnt.add(startCnt.multiply(mtrMultRate).setScale(BigDecimal.ROUND_HALF_UP));
                    currBllgMtrCnt = currBllgMtrCnt.add(endCnt.multiply(mtrMultRate).setScale(BigDecimal.ROUND_HALF_UP));

                    // START 2018/06/07 K.Kitachi [QC#20750, MOD]
                    // START 2017/10/17 E.Kameishi [QC#18636, MOD]
                    // START 2017/10/06 E.Kameishi [QC#18636, ADD]
                    BigDecimal tmpTestMtrCnt = insertBllgSchdTestMtrSmryForRebill(rsltSchdMap, rebilMtrMap).multiply(mtrMultRate).setScale(0, BigDecimal.ROUND_HALF_UP);
                    // END 2017/10/06 E.Kameishi [QC#18636, ADD]
                    testCopyQty = testCopyQty.add(tmpTestMtrCnt);
                    // END 2017/10/17 E.Kameishi [QC#18636, MOD]

                    //START 2017/09/12 K.Kasai [QC#15134,ADD]
                    // mod start 2019/08/23 QC#52934
                    //BigDecimal adjBllgMtrCntForCalcu = getAdjBllgMtrCntForCrRebil(currPhysMtrReadGrpSq, prevPhysMtrReadGrpSq, (BigDecimal) rebilMtrMap.get("SVC_PHYS_MTR_PK"), startCnt, endCnt, mtrMultRate, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
                    BigDecimal newTestCnt = (BigDecimal) rebilMtrMap.get("NEW_TEST_MTR_CNT");
                    BigDecimal endSvcPhysMtrReadPk = (BigDecimal) rebilMtrMap.get("END_SVC_PHYS_MTR_READ_PK");
                    BigDecimal exclSvcPhysMtrReadPk = null;
                    if (hasValue(newEndCnt) || hasValue(newTestCnt)) {
                        exclSvcPhysMtrReadPk = endSvcPhysMtrReadPk;
                    }
                    BigDecimal adjBllgMtrCntForCalcu = getAdjBllgMtrCntForCrRebil(currPhysMtrReadGrpSq, prevPhysMtrReadGrpSq, (BigDecimal) rebilMtrMap.get("SVC_PHYS_MTR_PK"), startCnt, endCnt, mtrMultRate, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"), exclSvcPhysMtrReadPk);
                    // mod end 2019/08/23 QC#52934
                    adjBllgMtrCnt = adjBllgMtrCnt.add(adjBllgMtrCntForCalcu);
                    //END 2017/09/12 K.Kasai [QC#15134,ADD]
                    // END 2018/06/07 K.Kitachi [QC#20750, MOD]
                }
            // add start 2016/04/27 CSA Defect#7578
            }
            // add end 2016/04/27 CSA Defect#7578
        }
        // mod end 2016/04/15 CSA Defect#7057

        List<Map<String, Object>> usgChrgRateMapList = getUsgChrgRate(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        BigDecimal aftDeclPntDigitNum = (BigDecimal) rsltSchdMap.get("AFT_DECL_PNT_DIGIT_NUM");
        BigDecimal copyInclQty = BigDecimal.ZERO;
        BigDecimal xsMtrAmtRate = null;
        Map<String, Object> firstUsgChrgRateMap = null;
        for (Map<String, Object> usgChrgRateMap : usgChrgRateMapList) {
            if (FLG_ON_Y.equals((String) usgChrgRateMap.get("XS_MTR_FIRST_FLG"))) {
                firstUsgChrgRateMap = usgChrgRateMap;
                copyInclQty = (BigDecimal) usgChrgRateMap.get("XS_MTR_COPY_QTY");
                xsMtrAmtRate = (BigDecimal) usgChrgRateMap.get("XS_MTR_AMT_RATE");
                break;
            }
        }
        SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTmsg = defSetSvcContrMtrBllg(pMsg, rsltSchdMap, svcContrBllgTmsg.svcContrBllgPk.getValue());
        // Add Start 01/26/2016 <QC3747>
        setValue(svcContrMtrBllgTmsg.copyInclQty, copyInclQty);
        // Add End 01/26/2016 <QC3747>
        setValue(svcContrMtrBllgTmsg.totCopyQty, currBllgMtrCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
        setValue(svcContrMtrBllgTmsg.prevTotCopyQty, prevBllgMtrCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
        setValue(svcContrMtrBllgTmsg.prevPhysMtrReadGrpSq, prevPhysMtrReadGrpSq);
        setValue(svcContrMtrBllgTmsg.svcPhysMtrReadGrpSq, currPhysMtrReadGrpSq);
        setValue(svcContrMtrBllgTmsg.mtrPerMthAot, calcPerMthAot(rsltSchdMap));
        setValue(svcContrMtrBllgTmsg.slsTaxRate, BigDecimal.ZERO);
        setValue(svcContrMtrBllgTmsg.dealTaxAmt, BigDecimal.ZERO);
        setValue(svcContrMtrBllgTmsg.funcTaxAmt, BigDecimal.ZERO);
        if (!hasValue(contrMtrMultRate)) {
            contrMtrMultRate = getContrMtrMultRate((BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
        }
        setValue(svcContrMtrBllgTmsg.contrMtrMultRate, contrMtrMultRate);

        BigDecimal bllgMinCnt = (BigDecimal) usgChrgRateMapList.get(0).get("BLLG_MIN_CNT");
        BigDecimal bllgRollOrverRatio = (BigDecimal) usgChrgRateMapList.get(0).get("BLLG_ROLL_OVER_RATIO");
        BigDecimal rollOrverCnt = (BigDecimal) usgChrgRateMapList.get(0).get("ROLL_OVER_CNT");
        BigDecimal bllgFreeCopyCnt = (BigDecimal) usgChrgRateMapList.get(0).get("BLLG_FREE_COPY_CNT");
        // START 2019/01/17 E.Kameishi [QC#29083,ADD]
        BigDecimal cumCopyCnt = (BigDecimal) usgChrgRateMapList.get(0).get("CUM_COPY_CNT");
        BigDecimal cumCopyFreq = (BigDecimal) usgChrgRateMapList.get(0).get("CUM_COPY_FREQ_MTH_AOT");
        String cumCopyStDt = (String) usgChrgRateMapList.get(0).get("CUM_COPY_START_DT");
        String cumCopyEdDt = (String) usgChrgRateMapList.get(0).get("CUM_COPY_END_DT");
        // END 2019/01/17 E.Kameishi [QC#29083,ADD]
        // START 2016/06/30 T.Aoyagi [QC10733, ADD]
        Map<String, BigDecimal> freeCopyAndRollOverMap = getFreeCopyAndRollOverCnt(prevSchd);
        if (freeCopyAndRollOverMap != null) {
            rollOrverCnt = freeCopyAndRollOverMap.get("ROLL_OVER_CNT");
            bllgFreeCopyCnt = freeCopyAndRollOverMap.get("FREE_COPY_CNT");
            // START 2018/06/05 K.Kojima [QC#21974,ADD]
        }
        // START 2019/01/17 E.Kameishi [QC#29083,ADD]
        if (cumCopyCnt != null) {
            if (checkFromToDt(svcContrMtrBllgTmsg.mtrBllgThruDt.getValue(), cumCopyStDt, cumCopyEdDt)) {
                if (freeCopyAndRollOverMap != null && checkCumCopyReset(cumCopyFreq, cumCopyStDt, svcContrMtrBllgTmsg.mtrBllgFromDt.getValue(), svcContrMtrBllgTmsg.mtrBllgThruDt.getValue())) {
                    bllgFreeCopyCnt = cumCopyCnt;
                }
            } else {
                if (checkCumCopyPerDays(svcContrMtrBllgTmsg, svcContrBllgTmsg, cumCopyStDt)) {
                    String cumCopyCycleEndDt = addDays(NSXC001001CalcDate.addMonths(cumCopyStDt, cumCopyFreq.intValue()), -1);
                    BigDecimal cumCopyDaysAot = new BigDecimal(ZYPDateUtil.getDiffDays(cumCopyCycleEndDt, cumCopyStDt) + 1);
                    BigDecimal cumAllowancePerDays = cumCopyCnt.divide(cumCopyDaysAot, 0, BigDecimal.ROUND_UP);
                    BigDecimal mtrBllgDaysAot = new BigDecimal(ZYPDateUtil.getDiffDays(svcContrMtrBllgTmsg.mtrBllgThruDt.getValue(), svcContrMtrBllgTmsg.mtrBllgFromDt.getValue()) + 1);
                    bllgFreeCopyCnt = cumAllowancePerDays.multiply(mtrBllgDaysAot);
                }
            }
        }
        
        // END 2019/01/17 E.Kameishi [QC#29083,ADD]
        if (MODE_CREDIT_REBILL.equals(pMsg.xxModeCd.getValue())) {
            bllgRollOrverRatio = null;
            Map<String, Object> freeCopyRollOverRatioUsed = getFreeCopyRollOverRatioUsed((BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
            if (freeCopyRollOverRatioUsed != null) {
                bllgRollOrverRatio = (BigDecimal) freeCopyRollOverRatioUsed.get("ROLL_OVER_RATIO");
            }
            if (bllgRollOrverRatio == null) {
                bllgRollOrverRatio = BigDecimal.ZERO;
            }
            if (freeCopyAndRollOverMap == null) {
                bllgFreeCopyCnt = null;
                rollOrverCnt = null;
                if (freeCopyRollOverRatioUsed != null) {
                    bllgFreeCopyCnt = (BigDecimal) freeCopyRollOverRatioUsed.get("FREE_COPY_CNT");
                }
                rollOrverCnt = getRollOverCntUsed((BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
                if (bllgFreeCopyCnt == null) {
                    bllgFreeCopyCnt = BigDecimal.ZERO;
                }
                if (rollOrverCnt == null) {
                    rollOrverCnt = BigDecimal.ZERO;
                }
            }
            // END 2018/06/05 K.Kojima [QC#21974,ADD]
        }
        // END 2016/06/30 T.Aoyagi [QC10733, ADD]
        BigDecimal usgFreeCopyCnt = BigDecimal.ZERO;
        BigDecimal bllgMinAmtRate = (BigDecimal) usgChrgRateMapList.get(0).get("BLLG_MIN_AMT_RATE");
        BigDecimal bllgAmt = BigDecimal.ZERO;
        BigDecimal mtrCnt = currBllgMtrCnt.subtract(prevBllgMtrCnt);
        // START 2017/09/07 K.Kojima [QC#18440,ADD]
        mtrCnt = mtrCnt.subtract(testCopyQty);
        // END 2017/09/07 K.Kojima [QC#18440,ADD]
        //START 2017/09/12 K.Kasai [QC#15134,ADD]
        mtrCnt = mtrCnt.add(adjBllgMtrCnt);
        //END 2017/09/12 K.Kasai [QC#15134,ADD]
        setValue(svcContrMtrBllgTmsg.mtrCopyQty, mtrCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
        BigDecimal bllgCopyCnt = mtrCnt;
        // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
        setValue(svcContrMtrBllgTmsg.testCopyQty, testCopyQty);
        // START 2017/09/07 K.Kojima [QC#18440,DEL]
        // bllgCopyCnt = bllgCopyCnt.subtract(testCopyQty);
        // END 2017/09/07 K.Kojima [QC#18440,DEL]
        if (BigDecimal.ZERO.compareTo(bllgCopyCnt) > 0) {
            bllgCopyCnt = BigDecimal.ZERO;
        }
        // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
        BigDecimal remainingBllgCopyCnt = bllgCopyCnt;
        boolean isExecTierBllg = true;
        // START 2018/05/14 K.Kitachi [QC#23541, ADD]
        boolean isAggregate = false;
        if (DS_CONTR_CATG.AGGREGATE.equals((String) rsltSchdMap.get("DS_CONTR_CATG_CD"))) {
            isAggregate = true;
        }
        // END 2018/05/14 K.Kitachi [QC#23541, ADD]
        // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//        if (hasValue(bllgMinCnt) && BigDecimal.ZERO.compareTo(bllgMinCnt) < 0) {
        if (!isAggregate && hasValue(bllgMinCnt) && BigDecimal.ZERO.compareTo(bllgMinCnt) < 0) {
        // END 2018/05/14 K.Kitachi [QC#23541, MOD]
            // Min count
            // START 2018/03/20 K.Kojima [QC#23623,ADD]
            bllgMinCnt = getBllgMinCnt(rsltSchdMap, bllgMinCnt);
            // END 2018/03/20 K.Kojima [QC#23623,ADD]
            if (bllgCopyCnt.compareTo(bllgMinCnt) < 0) {
                remainingBllgCopyCnt = bllgMinCnt;
            }
            if (copyInclQty.compareTo(remainingBllgCopyCnt) > 0) {
                bllgAmt = BigDecimal.ZERO;
            } else {
                remainingBllgCopyCnt = remainingBllgCopyCnt.subtract(copyInclQty);
                bllgAmt = remainingBllgCopyCnt.multiply(xsMtrAmtRate).setScale(aftDeclPntDigitNum.intValue(), BigDecimal.ROUND_HALF_UP);
            }
            bllgAmt = setAmtScale(bllgAmt);
            setValue(svcContrMtrBllgTmsg.mtrChrgDealAmt, bllgAmt);
            setValue(svcContrMtrBllgTmsg.mtrChrgFuncAmt, calcFuncAmtFromDealAmt(bllgAmt));
            setValue(svcContrMtrBllgTmsg.bllgCopyQty, remainingBllgCopyCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
            S21ApiTBLAccessor.create(svcContrMtrBllgTmsg);
            // START 2018/09/10 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createSvcContrMtrBllg-insertSvcContrBllgAllocForMtr start");
            // END 2018/09/10 K.Kojima [QC#28107,ADD]
            insertSvcContrBllgAllocForMtr(pMsg, rsltSchdMap, svcContrMtrBllgTmsg);
            // START 2018/09/10 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createSvcContrMtrBllg-insertSvcContrBllgAllocForMtr end");
            // END 2018/09/10 K.Kojima [QC#28107,ADD]
            insertSvcContrXsMtrBllgForSingleTier(svcContrMtrBllgTmsg, firstUsgChrgRateMap);
            isExecTierBllg = false;
        // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//        } else if (hasValue(bllgFreeCopyCnt) && BigDecimal.ZERO.compareTo(bllgFreeCopyCnt) < 0) {
        } else if (!isAggregate && hasValue(bllgFreeCopyCnt) && BigDecimal.ZERO.compareTo(bllgFreeCopyCnt) < 0) {
        // END 2018/05/14 K.Kitachi [QC#23541, MOD]
            // Free Copy
            if (copyInclQty.compareTo(bllgCopyCnt) > 0) {
                remainingBllgCopyCnt = BigDecimal.ZERO;
                usgFreeCopyCnt = BigDecimal.ZERO;
                // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//                bllgAmt = BigDecimal.ZERO;
                // END 2018/05/14 K.Kitachi [QC#23541, DEL]
            } else if (bllgFreeCopyCnt.add(copyInclQty).compareTo(bllgCopyCnt) >= 0) {
                remainingBllgCopyCnt = BigDecimal.ZERO;
                usgFreeCopyCnt = bllgCopyCnt.subtract(copyInclQty);
                bllgFreeCopyCnt = bllgFreeCopyCnt.subtract(usgFreeCopyCnt);
                // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//                bllgAmt = BigDecimal.ZERO;
                // END 2018/05/14 K.Kitachi [QC#23541, DEL]
            } else {
                // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//                remainingBllgCopyCnt = bllgCopyCnt.subtract(bllgFreeCopyCnt.add(copyInclQty));
                remainingBllgCopyCnt = bllgCopyCnt.subtract(bllgFreeCopyCnt);
                // END 2018/05/14 K.Kitachi [QC#23541, MOD]
                usgFreeCopyCnt = bllgFreeCopyCnt;
                bllgFreeCopyCnt = BigDecimal.ZERO;
                // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//                bllgAmt = remainingBllgCopyCnt.multiply(xsMtrAmtRate).setScale(aftDeclPntDigitNum.intValue(), BigDecimal.ROUND_HALF_UP);
                // END 2018/05/14 K.Kitachi [QC#23541, DEL]
            }
            setValue(svcContrMtrBllgTmsg.freeCopyCnt, bllgFreeCopyCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
            setValue(svcContrMtrBllgTmsg.usgFreeCopyCnt, usgFreeCopyCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
            // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//            setValue(svcContrMtrBllgTmsg.bllgCopyQty, remainingBllgCopyCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
//            bllgAmt = setAmtScale(bllgAmt);
//            setValue(svcContrMtrBllgTmsg.mtrChrgDealAmt, bllgAmt);
//            setValue(svcContrMtrBllgTmsg.mtrChrgFuncAmt, calcFuncAmtFromDealAmt(bllgAmt));
//            S21ApiTBLAccessor.create(svcContrMtrBllgTmsg);
//            insertSvcContrBllgAllocForMtr(pMsg, rsltSchdMap, svcContrMtrBllgTmsg);
//            insertSvcContrXsMtrBllgForSingleTier(svcContrMtrBllgTmsg, firstUsgChrgRateMap);
//            isExecTierBllg = false;
            // END 2018/05/14 K.Kitachi [QC#23541, DEL]
        // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//        } else if (hasValue(bllgRollOrverRatio) && BigDecimal.ZERO.compareTo(bllgRollOrverRatio) < 0) {
        } else if (!isAggregate && hasValue(bllgRollOrverRatio) && BigDecimal.ZERO.compareTo(bllgRollOrverRatio) < 0) {
        // END 2018/05/14 K.Kitachi [QC#23541, MOD]
            // Roll Over
            if (!hasValue(rollOrverCnt)) {
                rollOrverCnt = BigDecimal.ZERO;
            }

            // START 2021/07/19 [QC#58805-4 MOD]
            remainingBllgCopyCnt = bllgCopyCnt.subtract(rollOrverCnt);
            if (remainingBllgCopyCnt.compareTo(BigDecimal.ZERO) < 0) {
                remainingBllgCopyCnt = BigDecimal.ZERO;
            }

            if (copyInclQty.compareTo(bllgCopyCnt) > 0) {
                rollOrverCnt = ((copyInclQty.subtract(bllgCopyCnt)).multiply(bllgRollOrverRatio.divide(BIGDECIMAL_100)).setScale(0, BigDecimal.ROUND_HALF_UP)).add(rollOrverCnt);
            } else {
                rollOrverCnt = (copyInclQty.subtract(bllgCopyCnt).setScale(0, BigDecimal.ROUND_HALF_UP)).add(rollOrverCnt);
            }
            if  (rollOrverCnt.compareTo(BigDecimal.ZERO) < 0) {
                rollOrverCnt = BigDecimal.ZERO;
            }

//            remainingBllgCopyCnt = bllgCopyCnt.subtract(rollOrverCnt);    //bllgCopyCnt=1000 copyInclQty=2000 rollOrverCnt = 750
//            // START 2017/09/25 K.Kitachi [QC#21186, DEL]
//            if (remainingBllgCopyCnt.compareTo(BigDecimal.ZERO) < 0) {
//                remainingBllgCopyCnt = BigDecimal.ZERO;
//            }
//            // END 2017/09/25 K.Kitachi [QC#21186, DEL]
//            if (copyInclQty.compareTo(remainingBllgCopyCnt) > 0) {
//                rollOrverCnt = copyInclQty.subtract(remainingBllgCopyCnt).multiply(bllgRollOrverRatio.divide(BIGDECIMAL_100)).setScale(0, BigDecimal.ROUND_HALF_UP);
//                remainingBllgCopyCnt = BigDecimal.ZERO;
//                // START 2018/05/14 K.Kitachi [QC#23541, DEL]
////                bllgAmt = BigDecimal.ZERO;
//                // END 2018/05/14 K.Kitachi [QC#23541, DEL]
//            } else {
//                rollOrverCnt = BigDecimal.ZERO;
//                // START 2018/05/14 K.Kitachi [QC#23541, DEL]
////                // Mod Start 08/24/2016 <QC#10733>
////                remainingBllgCopyCnt = remainingBllgCopyCnt.subtract(copyInclQty);
////                // Mod End   08/24/2016 <QC#10733>
////                bllgAmt = remainingBllgCopyCnt.multiply(xsMtrAmtRate).setScale(aftDeclPntDigitNum.intValue(), BigDecimal.ROUND_HALF_UP);
//                // END 2018/05/14 K.Kitachi [QC#23541, DEL]
//            }
            // END 2021/07/19 [QC#58805-4 MOD]

            setValue(svcContrMtrBllgTmsg.rollOverRatio, bllgRollOrverRatio);
            setValue(svcContrMtrBllgTmsg.rollOverCnt, rollOrverCnt);
            // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//            setValue(svcContrMtrBllgTmsg.bllgCopyQty, remainingBllgCopyCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
//            bllgAmt = setAmtScale(bllgAmt);
//            setValue(svcContrMtrBllgTmsg.mtrChrgDealAmt, bllgAmt);
//            setValue(svcContrMtrBllgTmsg.mtrChrgFuncAmt, calcFuncAmtFromDealAmt(bllgAmt));
//            S21ApiTBLAccessor.create(svcContrMtrBllgTmsg);
//            insertSvcContrBllgAllocForMtr(pMsg, rsltSchdMap, svcContrMtrBllgTmsg);
//            insertSvcContrXsMtrBllgForSingleTier(svcContrMtrBllgTmsg, firstUsgChrgRateMap);
//            isExecTierBllg = false;
            // END 2018/05/14 K.Kitachi [QC#23541, DEL]
        // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//        } else if (hasValue(bllgMinAmtRate) && BigDecimal.ZERO.compareTo(bllgMinAmtRate) < 0) {
        } else if (!isAggregate && hasValue(bllgMinAmtRate) && BigDecimal.ZERO.compareTo(bllgMinAmtRate) < 0) {
        // END 2018/05/14 K.Kitachi [QC#23541, MOD]
            // Min Amt Rate
            // START 2018/03/20 K.Kojima [QC#23623,ADD]
            bllgMinAmtRate = getBllgMinAmtRate(rsltSchdMap, bllgMinAmtRate);
            // END 2018/03/20 K.Kojima [QC#23623,ADD]
            remainingBllgCopyCnt = bllgCopyCnt.subtract(copyInclQty);
            if (remainingBllgCopyCnt.compareTo(BigDecimal.ZERO) < 0) {
                remainingBllgCopyCnt = BigDecimal.ZERO;
            }
            bllgAmt = remainingBllgCopyCnt.multiply(xsMtrAmtRate).setScale(aftDeclPntDigitNum.intValue(), BigDecimal.ROUND_HALF_UP);
            if (bllgAmt.compareTo(bllgMinAmtRate) < 0) {
                bllgAmt = bllgMinAmtRate;
            }
            setValue(svcContrMtrBllgTmsg.bllgCopyQty, remainingBllgCopyCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
            bllgAmt = setAmtScale(bllgAmt);
            setValue(svcContrMtrBllgTmsg.mtrChrgDealAmt, bllgAmt);
            setValue(svcContrMtrBllgTmsg.mtrChrgFuncAmt, calcFuncAmtFromDealAmt(bllgAmt));
            S21ApiTBLAccessor.create(svcContrMtrBllgTmsg);
            // START 2018/09/10 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createSvcContrMtrBllg-insertSvcContrBllgAllocForMtr start");
            // END 2018/09/10 K.Kojima [QC#28107,ADD]
            insertSvcContrBllgAllocForMtr(pMsg, rsltSchdMap, svcContrMtrBllgTmsg);
            // START 2018/09/10 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createSvcContrMtrBllg-insertSvcContrBllgAllocForMtr end");
            // END 2018/09/10 K.Kojima [QC#28107,ADD]
            insertSvcContrXsMtrBllgForSingleTier(svcContrMtrBllgTmsg, firstUsgChrgRateMap);
            isExecTierBllg = false;
        }

        if (isExecTierBllg == false) {
            return svcContrMtrBllgTmsg;
        }
        BigDecimal bllgCopy = remainingBllgCopyCnt.subtract(copyInclQty);
        if (BigDecimal.ZERO.compareTo(bllgCopy) > 0) {
            bllgCopy = BigDecimal.ZERO;
        }
        if (XS_CHRG_TP.POINT.equals((String) usgChrgRateMapList.get(0).get("XS_CHRG_TP_CD"))) {
            Map<String, Object> pointChrgRateMap = null;
            for (Map<String, Object> usgChrgRateMap : usgChrgRateMapList) {
                xsMtrAmtRate = (BigDecimal) usgChrgRateMap.get("XS_MTR_AMT_RATE");
                pointChrgRateMap = usgChrgRateMap;
                if (bllgCopyCnt.compareTo((BigDecimal) usgChrgRateMap.get("XS_MTR_COPY_QTY")) > 0) {
                    break;
                }
            }
            if (BigDecimal.ZERO.compareTo(bllgCopy) >= 0) {
                bllgAmt = setAmtScale(BigDecimal.ZERO);
            } else {
                bllgAmt = xsMtrAmtRate.multiply(remainingBllgCopyCnt.subtract(copyInclQty));
                bllgAmt = setAmtScale(bllgAmt);
            }
            setValue(svcContrMtrBllgTmsg.mtrChrgDealAmt, bllgAmt);
            setValue(svcContrMtrBllgTmsg.mtrChrgFuncAmt, calcFuncAmtFromDealAmt(bllgAmt));
            // Add Start 04/11/2016 <QC#6657>
            setValue(svcContrMtrBllgTmsg.bllgCopyQty, bllgCopy.setScale(0, BigDecimal.ROUND_HALF_UP));
            // Add End   04/11/2016 <QC#6657>
            insertSvcContrXsMtrBllgForSingleTier(svcContrMtrBllgTmsg, pointChrgRateMap);
        } else {
            Boolean isCreateXsMtr = false;
            Map<String, Object> firstChrgRateMap = null;
            for (Map<String, Object> usgChrgRateMap : usgChrgRateMapList) {
                firstChrgRateMap = usgChrgRateMap;
                if (remainingBllgCopyCnt.compareTo((BigDecimal) usgChrgRateMap.get("XS_MTR_COPY_QTY")) > 0) {
                    isCreateXsMtr = true;
                    xsMtrAmtRate = (BigDecimal) usgChrgRateMap.get("XS_MTR_AMT_RATE");
                    BigDecimal chrgCopyCnt = remainingBllgCopyCnt.subtract((BigDecimal) usgChrgRateMap.get("XS_MTR_COPY_QTY"));
                    chrgCopyCnt = chrgCopyCnt.setScale(0, BigDecimal.ROUND_HALF_UP);
                    BigDecimal chrgXsAmt = xsMtrAmtRate.multiply(chrgCopyCnt);
                    remainingBllgCopyCnt = remainingBllgCopyCnt.subtract(chrgCopyCnt);
                    bllgAmt = bllgAmt.add(chrgXsAmt);
                    // svc_contr_xs_bllg
                    SVC_CONTR_XS_MTR_BLLGTMsg inXsMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
                    setValue(inXsMsg.glblCmpyCd, this.glblCmpyCd);
                    setValue(inXsMsg.svcContrXsMtrBllgPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_XS_MTR_BLLG_SQ));
                    setValue(inXsMsg.svcContrMtrBllgPk, svcContrMtrBllgTmsg.svcContrMtrBllgPk);
                    setValue(inXsMsg.svcContrBllgPk, svcContrMtrBllgTmsg.svcContrBllgPk);
                    setValue(inXsMsg.dsContrDtlPk, svcContrMtrBllgTmsg.dsContrDtlPk);
                    setValue(inXsMsg.dsContrBllgMtrPk, svcContrMtrBllgTmsg.dsContrBllgMtrPk);
                    // dsContrBllgMtrId does not require variable
                    setValue(inXsMsg.contrXsCopyPk, (BigDecimal) usgChrgRateMap.get("CONTR_XS_COPY_PK"));
                    setValue(inXsMsg.xsMtrCopyQty, chrgCopyCnt);
                    setValue(inXsMsg.xsMtrChrgDealAmt, setAmtScale(chrgXsAmt));
                    setValue(inXsMsg.xsMtrChrgFuncAmt, calcFuncAmtFromDealAmt(chrgXsAmt));
                    setValue(inXsMsg.xsMtrChrgDiscDealAmt, BigDecimal.ZERO);
                    setValue(inXsMsg.xsMtrChrgDiscFuncAmt, BigDecimal.ZERO);
                    setValue(inXsMsg.ccyCd, this.ccyCd);
                    setValue(inXsMsg.xsMtrAmtRate, (BigDecimal) usgChrgRateMap.get("XS_MTR_AMT_RATE"));
                    setValue(inXsMsg.xsMtrFromCopyQty, (BigDecimal) usgChrgRateMap.get("XS_MTR_COPY_QTY"));
                    S21ApiTBLAccessor.create(inXsMsg);
                }
            }
            bllgAmt = setAmtScale(bllgAmt);
            setValue(svcContrMtrBllgTmsg.mtrChrgDealAmt, bllgAmt);
            setValue(svcContrMtrBllgTmsg.mtrChrgFuncAmt, calcFuncAmtFromDealAmt(bllgAmt));
            // Add Start 04/11/2016 <QC#6657>
            setValue(svcContrMtrBllgTmsg.bllgCopyQty, bllgCopy.setScale(0, BigDecimal.ROUND_HALF_UP));
            // Add End   04/11/2016 <QC#6657>
            if (!isCreateXsMtr) {
                insertSvcContrXsMtrBllgForSingleTier(svcContrMtrBllgTmsg, firstChrgRateMap);
            }
        }
        // Del Start 04/11/2016 <QC#6657>
//        setValue(svcContrMtrBllgTmsg.bllgCopyQty, bllgCopy.setScale(0, BigDecimal.ROUND_HALF_UP));
        // Del End   04/11/2016 <QC#6657>
        S21ApiTBLAccessor.create(svcContrMtrBllgTmsg);
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-createSvcContrMtrBllg-insertSvcContrBllgAllocForMtr start");
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        insertSvcContrBllgAllocForMtr(pMsg, rsltSchdMap, svcContrMtrBllgTmsg);
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-createSvcContrMtrBllg-insertSvcContrBllgAllocForMtr end");
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        return svcContrMtrBllgTmsg;
    }

    // Mod Start 05/11/2016 <QC#7764>
    private SVC_CONTR_MTR_BLLGTMsg createSvcContrMtrBllgForFleetMachine(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, SVC_CONTR_BLLGTMsg svcContrBllgTmsg) {
        BigDecimal prevPhysMtrReadGrpSq = null;
        BigDecimal currPhysMtrReadGrpSq = null;
        BigDecimal currBllgMtrCnt = BigDecimal.ZERO;
        BigDecimal prevBllgMtrCnt = BigDecimal.ZERO;
        // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
        BigDecimal testCopyQty = BigDecimal.ZERO;
        // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
        //START 2017/09/12 K.Kasai [QC#15134,ADD]
        BigDecimal adjBllgMtrCnt = BigDecimal.ZERO;
        //END 2017/09/12 K.Kasai [QC#15134,ADD]
        if (!MODE_CREDIT_REBILL.equals(pMsg.xxModeCd.getValue())) {
            Map<String, Object> prevSchd = getPrevSchd(pMsg, rsltSchdMap);

            currBllgMtrCnt = getBllgMtrCnt(pMsg, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
            currPhysMtrReadGrpSq = (BigDecimal) rsltSchdMap.get("SVC_PHYS_MTR_READ_GRP_SQ");
            // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
            String prevMtrReadDt = null;
            String currMtrReadDt = this.tmpMtrReadDt;
            // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
            //START 2017/09/12 K.Kasai [QC#15134,ADD]
            CONTR_PHYS_BLLG_MTR_RELNTMsgArray svcPhysMtrPkArray = getSvcPhysMtrPk(pMsg, rsltSchdMap);
            BigDecimal svcPhysMtrPk = svcPhysMtrPkArray.no(0).svcPhysMtrPk.getValue();
            //END 2017/09/12 K.Kasai [QC#15134,ADD]
            if (prevSchd == null) {
                // Start Meter count
                BigDecimal dsContrDtlPk = (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK");
                BigDecimal svcMachMstrPk = (BigDecimal) rsltSchdMap.get("SVC_MACH_MSTR_PK");
                //START 2017/09/12 K.Kasai [QC#15134,DEL]
//                BigDecimal svcPhysMtrPk = getSvcPhysMtrPk(pMsg, rsltSchdMap);
                //END 2017/09/12 K.Kasai [QC#15134,DEL]
                if (svcPhysMtrPk != null) {
                    prevPhysMtrReadGrpSq = getStartMtrGrpSq(pMsg, dsContrDtlPk, svcMachMstrPk, svcPhysMtrPk);
                }
                if (prevPhysMtrReadGrpSq == null) {
                    prevBllgMtrCnt = BigDecimal.ZERO;
                    // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                    prevMtrReadDt = ZYPDateUtil.addDays((String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), -1);
                    // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
                } else {
                    prevBllgMtrCnt = getStartMtrCnt(pMsg, prevPhysMtrReadGrpSq, rsltSchdMap);
                    // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                    prevMtrReadDt = ZYPDateUtil.addDays(this.tmpMtrReadDt, -1);
                    // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
                }
            } else {
                prevBllgMtrCnt = getBllgMtrCnt(pMsg, (BigDecimal) prevSchd.get("DS_CONTR_BLLG_SCHD_PK"));
                prevPhysMtrReadGrpSq = (BigDecimal) prevSchd.get("SVC_PHYS_MTR_READ_GRP_SQ");
                // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
                prevMtrReadDt = this.tmpMtrReadDt;
                // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
            }

            // START 2017/09/15 K.Kojima [QC#21125,ADD]
            if (!ZYPCommonFunc.hasValue(prevMtrReadDt)) {
                setErrMsg(pMsg, NSZM1299E);
                return null;
            }
            // END 2017/09/15 K.Kojima [QC#21125,ADD]
            // START 2018/06/07 K.Kitachi [QC#20750, MOD]
            // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
            testCopyQty = getTestCopyQty(rsltSchdMap, prevMtrReadDt, currMtrReadDt);
            // END 2016/10/06 T.Kanasaka [QC#12274, ADD]

            // add start 2019/09/04 QC#53124
            if (this.isExclTestCopy) {
                testCopyQty = BigDecimal.ZERO;
            }
            // add end 2019/09/04 QC#53124

            //START 2017/09/12 K.Kasai [QC#15134,ADD]
            adjBllgMtrCnt = getAdjBllgMtrCnt(prevPhysMtrReadGrpSq, currPhysMtrReadGrpSq, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"), (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"), svcPhysMtrPkArray, null, null);
            //END 2017/09/12 K.Kasai [QC#15134,ADD]
            // END 2018/06/07 K.Kitachi [QC#20750, MOD]
        } else if (MODE_CREDIT_REBILL.equals(pMsg.xxModeCd.getValue())) {
            List<Map<String, Object>> rebilMtrMapList = getCrRebilMtrCnt(pMsg, rsltSchdMap);
            for (Map<String, Object> rebilMtrMap : rebilMtrMapList) {
                BigDecimal startCnt = BigDecimal.ZERO;
                BigDecimal endCnt = BigDecimal.ZERO;
                BigDecimal newStartCnt = (BigDecimal) rebilMtrMap.get("NEW_START_READ_MTR_CNT");
                BigDecimal newEndCnt = (BigDecimal) rebilMtrMap.get("NEW_END_READ_MTR_CNT");
                BigDecimal oldStartCnt = (BigDecimal) rebilMtrMap.get("OLD_START_READ_MTR_CNT");
                BigDecimal oldEndCnt = (BigDecimal) rebilMtrMap.get("OLD_END_READ_MTR_CNT");
                BigDecimal mtrMultRate = (BigDecimal) rebilMtrMap.get("CONTR_MTR_MULT_RATE");
                BigDecimal newStartGrpSq = (BigDecimal) rebilMtrMap.get("NEW_START_MTR_READ_GRP_SQ");
                BigDecimal newEndGrpSq = (BigDecimal) rebilMtrMap.get("NEW_END_MTR_READ_GRP_SQ");
                BigDecimal oldStartGrpSq = (BigDecimal) rebilMtrMap.get("OLD_START_MTR_READ_GRP_SQ");
                BigDecimal OldEndGrpSq = (BigDecimal) rebilMtrMap.get("OLD_END_MTR_READ_GRP_SQ");
                if (hasValue(newStartCnt)) {
                    startCnt = newStartCnt;
                } else if (hasValue(oldStartCnt)) {
                    startCnt = oldStartCnt;
                }
                if (hasValue(newEndCnt)) {
                    endCnt = newEndCnt;
                } else if (hasValue(oldEndCnt)) {
                    endCnt = oldEndCnt;
                }
                if (hasValue(newStartGrpSq)) {
                    prevPhysMtrReadGrpSq = newStartGrpSq;
                } else if (hasValue(oldStartGrpSq)) {
                    prevPhysMtrReadGrpSq = oldStartGrpSq;
                }
                if (hasValue(newEndGrpSq)) {
                    currPhysMtrReadGrpSq = newEndGrpSq;
                } else if (hasValue(oldEndCnt)) {
                    currPhysMtrReadGrpSq = OldEndGrpSq;
                }
                prevBllgMtrCnt = prevBllgMtrCnt.add(startCnt.multiply(mtrMultRate).setScale(BigDecimal.ROUND_HALF_UP));
                currBllgMtrCnt = currBllgMtrCnt.add(endCnt.multiply(mtrMultRate).setScale(BigDecimal.ROUND_HALF_UP));
                // START 2017/10/17 E.Kameishi [QC#18636, MOD]
                // START 2017/10/06 E.Kameishi [QC#18636, ADD]
                BigDecimal tmpTestMtrCnt = insertBllgSchdTestMtrSmryForRebill(rsltSchdMap, rebilMtrMap).multiply(mtrMultRate).setScale(0, BigDecimal.ROUND_HALF_UP);
                // END 2017/10/06 E.Kameishi [QC#18636, ADD]
                testCopyQty = testCopyQty.add(tmpTestMtrCnt);
                // END 2017/10/17 E.Kameishi [QC#18636, MOD]
            }
        }
        SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTmsg = defSetSvcContrMtrBllg(pMsg, rsltSchdMap, svcContrBllgTmsg.svcContrBllgPk.getValue());
        setValue(svcContrMtrBllgTmsg.totCopyQty, currBllgMtrCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
        setValue(svcContrMtrBllgTmsg.prevTotCopyQty, prevBllgMtrCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
        setValue(svcContrMtrBllgTmsg.prevPhysMtrReadGrpSq, prevPhysMtrReadGrpSq);
        setValue(svcContrMtrBllgTmsg.svcPhysMtrReadGrpSq, currPhysMtrReadGrpSq);
        setValue(svcContrMtrBllgTmsg.mtrPerMthAot, calcPerMthAot(rsltSchdMap));
        // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
        setValue(svcContrMtrBllgTmsg.testCopyQty, testCopyQty);
        // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
        // START 2017/09/07 K.Kojima [QC#18440,ADD]
        BigDecimal mtrCnt = currBllgMtrCnt.subtract(prevBllgMtrCnt);
        mtrCnt = mtrCnt.subtract(testCopyQty);
        //START 2017/09/12 K.Kasai [QC#15134,ADD]
        mtrCnt = mtrCnt.add(adjBllgMtrCnt);
        //END 2017/09/12 K.Kasai [QC#15134,ADD]
        setValue(svcContrMtrBllgTmsg.mtrCopyQty, mtrCnt.setScale(0, BigDecimal.ROUND_HALF_UP));
        // END 2017/09/07 K.Kojima [QC#18440,ADD]

        S21ApiTBLAccessor.create(svcContrMtrBllgTmsg);
        return svcContrMtrBllgTmsg;
    }
    // Mod End   05/11/2016 <QC#7764>

    private SVC_CONTR_BLLGTMsg createSvcContrBllgForAddlChrg(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, Map<String, Object> rsltAddlChrg, BigDecimal svcContrBllgPk, BigDecimal svcContrBllgGrpSq,
            AddlChrgFromThruDtInfo addlChrgFromThruDtinfo) {
        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.svcContrBllgPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_SQ));
        setValue(inMsg.dsContrPk, (BigDecimal) rsltSchdMap.get("DS_CONTR_PK"));
        setValue(inMsg.dsContrDtlPk, (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
        setValue(inMsg.bllgCpltStsCd, BLLG_CPLT_STS.SCHEDULED);
        // Mod Start 03/18/2016 <QC#5692>
        setValue(inMsg.bllgApvlReqFlg, FLG_OFF_N);
        setValue(inMsg.bllgApvlCpltFlg, FLG_ON_Y);
        // Mod End   03/18/2016 <QC#5692>
        // bllgApvlPsnCd does not require variable
        // mthEndCloFlg does not require variable
        setValue(inMsg.mthEndCloFlg, FLG_OFF_N);
        // contrCloDay does not require variable
        setValue(inMsg.svcContrBllgFromDt, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"));
        setValue(inMsg.svcContrBllgThruDt, (String) rsltSchdMap.get("BLLG_SCHD_THRU_DT"));
        setValue(inMsg.baseBllgTmgCd, (String) rsltSchdMap.get("BASE_BLLG_TMG_CD"));
        setValue(inMsg.baseBllgCycleMthAot, (BigDecimal) rsltSchdMap.get("BASE_BLLG_CYCLE_MTH_AOT"));
        // baseBllgNextBllgDt does not require variable
        // baseBllgNextBllgDt does not require variable
        setValue(inMsg.baseBllgInvUpToDt, (String) rsltSchdMap.get("BASE_BLLG_INV_UP_TO_DT"));
        // mtrBllgTmgCd does not require variable
        // mtrBllgCycleMthAot does not require variable
        // mtrBllgNextBllgDt does not require variable
        // mtrBllgLastBllgDt does not require variable
        // mtrBllgInvUpToDt does not require variable
        setValue(inMsg.ccyCd, (String) rsltSchdMap.get("CCY_CD"));
        // svcMachMstrPk does not require variable
        setValue(inMsg.svcMachMstrPk, (BigDecimal) rsltSchdMap.get("SVC_MACH_MSTR_PK"));
        setValue(inMsg.baseChrgFlg, FLG_OFF_N);
        setValue(inMsg.usgChrgFlg, FLG_OFF_N);
        // sellToCustCd does not require variable
        setValue(inMsg.fleetCalcFlg, FLG_OFF_N);
        setValue(inMsg.prntSvcContrBllgPk, svcContrBllgPk);
        setValue(inMsg.dsContrCatgCd, (String) rsltSchdMap.get("DS_CONTR_CATG_CD"));
        // ovrdNextBllgDt does not require variable
        setValue(inMsg.bllgReProcFlg, FLG_OFF_N);
        // bllgReProcPsnCd does not require variable
        // prevSvcContrBllgPk does not require variable
        // lastMtrReadTs does not require variable
        setValue(inMsg.dsContrBllgSchdPk, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        setValue(inMsg.dsAcctNum, (String) rsltSchdMap.get("DS_ACCT_NUM"));
        setValue(inMsg.aggrCalcFlg, FLG_OFF_N);
        setValue(inMsg.svcCrRebilPk, (BigDecimal) rsltSchdMap.get("SVC_CR_REBIL_PK"));
        // svcCrRebilDtlPk does not require variable
        setValue(inMsg.invTpCd, (String) rsltSchdMap.get("INV_TP_CD"));
        // dsContrBllgMtrPk does not require variable
        setValue(inMsg.svcContrBllgGrpSq, svcContrBllgGrpSq);
        setValue(inMsg.bllgCalcEstFlg, pMsg.estFlg);
        setValue(inMsg.addlChrgFlg, FLG_ON_Y);
        setValue(inMsg.bllgCalcEstFlg, FLG_OFF_N);
        setValue(inMsg.prcAllocByMachQtyFlg, FLG_OFF_N);
        // mod start 2016/09/27 CSA Defect#10787
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            setValue(inMsg.addlChrgBllgNextBllgDt, (String) rsltSchdMap.get("BASE_BLLG_NEXT_BLLG_DT"));
            setValue(inMsg.billToCustCd, (String) rsltSchdMap.get("BASE_BILL_TO_CUST_CD"));
        } else {
            setValue(inMsg.addlChrgBllgNextBllgDt, (String) rsltSchdMap.get("MTR_BLLG_NEXT_BLLG_DT"));
            setValue(inMsg.billToCustCd, (String) rsltSchdMap.get("BLLG_MTR_BILL_TO_CUST_CD"));
        }
        // mod end 2016/09/27 CSA Defect#10787
        setValue(inMsg.addlChrgInvUpToDt, (String) rsltAddlChrg.get("INV_UP_TO_DT"));
        setValue(inMsg.origSvcInvNum, (String) rsltSchdMap.get("ORIG_SVC_INV_NUM"));
        setValue(inMsg.dsContrAddlChrgPk, (BigDecimal) rsltAddlChrg.get("DS_CONTR_ADDL_CHRG_PK"));
        S21ApiTBLAccessor.create(inMsg);
        return inMsg;
    }

    private void createAddlChrg(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, Map<String, Object> rsltAddlChrg, AddlChrgFromThruDtInfo addlChrgFromThruDtinfo, BigDecimal srcPrcAmt, BigDecimal svcContrBaseBllgPk, BigDecimal svcContrMtrBllgPk) {
        BigDecimal svcContrBllgPk = (BigDecimal) rsltSchdMap.get("SVC_CONTR_BLLG_PK");
        BigDecimal svcContrBllgGrpSq = (BigDecimal) rsltSchdMap.get("SVC_CONTR_BLLG_GRP_SQ");
        SVC_CONTR_BLLGTMsg svcContrBllgForAddl = createSvcContrBllgForAddlChrg(pMsg, rsltSchdMap, rsltAddlChrg, svcContrBllgPk, svcContrBllgGrpSq, addlChrgFromThruDtinfo);
        CalcAddlChrgInfo calcAddlChrgInfo = new CalcAddlChrgInfo();
        if (!hasValue(srcPrcAmt)) {
            srcPrcAmt = BigDecimal.ZERO;
        }
        calcAddlChrgInfo.setBasePrcAmt(srcPrcAmt);
        calcAddlChrgInfo.setFlatRateAmt((BigDecimal) rsltAddlChrg.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
        calcAddlChrgInfo.setAplcPct((BigDecimal) rsltAddlChrg.get("ADDL_CHRG_APLC_PCT"));
        calcAddlChrgInfo.setInvUpToDt(svcContrBllgForAddl.addlChrgInvUpToDt.getValue());
        // START 2017/12/21 K.Kojima [QC#18562,ADD]
        calcAddlChrgInfo.setAddlChrgFromDt(addlChrgFromThruDtinfo.getAddlChrgFromDt());
        calcAddlChrgInfo.setAddlChrgThueDt(addlChrgFromThruDtinfo.getAddlChrgThruDt());
        calcAddlChrgInfo.setBllgFromDt(addlChrgFromThruDtinfo.getBllgFromDt());
        // END 2017/12/21 K.Kojima [QC#18562,ADD]
        calcAddlChrgInfo.setBllgThruDt(addlChrgFromThruDtinfo.getBllgThruDt());
        calcAddlChrgInfo.setAftDeclPntDigitNum((BigDecimal) rsltSchdMap.get("AFT_DECL_PNT_DIGIT_NUM"));
        calcAddlChrgInfo.setSvcMachCnt(BigDecimal.ONE);
        // START 2017/12/21 K.Kojima [QC#18562,ADD]
        calcAddlChrgInfo.setPerBllgCycleCd((String) rsltSchdMap.get("PER_BLLG_CYCLE_CD"));
        calcAddlChrgInfo.setPrrtDivRate((BigDecimal) rsltAddlChrg.get("PRRT_DIV_RATE"));
        // END 2017/12/21 K.Kojima [QC#18562,ADD]
        NSXC001001CalcAddlChrg calcAddlChrg = new NSXC001001CalcAddlChrg();
        calcAddlChrg.calcAddrChrg(calcAddlChrgInfo);
        SVC_CONTR_ADDL_CHRG_BLLGTMsg svcContrAddlChrgBllgTmsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
        setValue(svcContrAddlChrgBllgTmsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcContrAddlChrgBllgTmsg.svcContrAddlChrgBllgPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_ADDL_CHRG_BLLG_SQ));
        //Mod Start 03/11/2016 <QC#5347>
        setValue(svcContrAddlChrgBllgTmsg.svcContrBllgPk, svcContrBllgForAddl.svcContrBllgPk.getValue());
        //Mod End   03/11/2016 <QC#5347>
        // mod start 2016/09/27 CSA Defect#10787
        String addlChrgTpCd = (String) rsltAddlChrg.get("ADDL_CHRG_TP_CD");
        setValue(svcContrAddlChrgBllgTmsg.addlChrgTpCd, addlChrgTpCd);
        // mod end 2016/09/27 CSA Defect#10787
        setValue(svcContrAddlChrgBllgTmsg.svcBillByTpCd, (String) rsltAddlChrg.get("SVC_BILL_BY_TP_CD"));
        setValue(svcContrAddlChrgBllgTmsg.addlDealPrcAmt, calcAddlChrgInfo.getAddlChrgAmt());
        BigDecimal funcAmt = calcFuncAmtFromDealAmt(calcAddlChrgInfo.getAddlChrgAmt());
        setValue(svcContrAddlChrgBllgTmsg.addlFuncPrcAmt, funcAmt);
        setValue(svcContrAddlChrgBllgTmsg.addlChrgFlatDealPrcAmt, (BigDecimal) rsltAddlChrg.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
        setValue(svcContrAddlChrgBllgTmsg.addlChrgFlatFuncPrcAmt, (BigDecimal) rsltAddlChrg.get("ADDL_CHRG_FLAT_FUNC_PRC_AMT"));
        setValue(svcContrAddlChrgBllgTmsg.addlChrgAplcPct, (BigDecimal) rsltAddlChrg.get("ADDL_CHRG_APLC_PCT"));
        // START 2017/06/28 K.Kim [QC#18539, MOD]
//        setValue(svcContrAddlChrgBllgTmsg.addlChrgInvTpCd, (String) rsltSchdMap.get("ADDL_CHRG_INV_TP_CD"));
        setValue(svcContrAddlChrgBllgTmsg.addlChrgInvTpCd, (String) rsltAddlChrg.get("ADDL_CHRG_INV_TP_CD"));
        // END 2017/06/28 K.Kim [QC#18539, MOD]
        setValue(svcContrAddlChrgBllgTmsg.printDtlFlg, (String) rsltSchdMap.get("PRINT_DTL_FLG"));
        setValue(svcContrAddlChrgBllgTmsg.chrgMachCnt, BigDecimal.ONE);
        setValue(svcContrAddlChrgBllgTmsg.addlChrgFromDt, addlChrgFromThruDtinfo.getAddlChrgFromDt());
        setValue(svcContrAddlChrgBllgTmsg.addlChrgThruDt, addlChrgFromThruDtinfo.getAddlChrgThruDt());
        setValue(svcContrAddlChrgBllgTmsg.svcContrBaseBllgPk, svcContrBaseBllgPk);
        setValue(svcContrAddlChrgBllgTmsg.svcContrMtrBllgPk, svcContrMtrBllgPk);
        // mod start 2016/09/27 CSA Defect#10787
//        if (hasValue(svcContrBaseBllgPk)) {
//            setValue(svcContrAddlChrgBllgTmsg.intgMdseCd, (String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
//        } else {
//            setValue(svcContrAddlChrgBllgTmsg.intgMdseCd, (String) rsltSchdMap.get("INTG_MDSE_CD"));
//        }
        setValue(svcContrAddlChrgBllgTmsg.intgMdseCd, getIntgMdseCdForAddlChrgTpV(pMsg.glblCmpyCd.getValue(), addlChrgTpCd));
        // mod end 2016/09/27 CSA Defect#10787
        setValue(svcContrAddlChrgBllgTmsg.printDtlFlg, (String) rsltAddlChrg.get("PRINT_DTL_FLG"));
        if (BigDecimal.ZERO.compareTo(funcAmt) < 0) {
            NWZC036101PMsg taxApiPMsg = new NWZC036101PMsg();
            // START 2018/09/07 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createAddlChrg-callTaxCalcAPIForAddl start");
            // END 2018/09/07 K.Kojima [QC#28107,ADD]
            taxApiPMsg = callTaxCalcAPIForAddl(rsltSchdMap, svcContrBllgForAddl, svcContrAddlChrgBllgTmsg, funcAmt);
            // START 2018/09/07 K.Kojima [QC#28107,ADD]
            s21InfoLogOutputPrintln("NSZC0560-createAddlChrg-callTaxCalcAPIForAddl end");
            // END 2018/09/07 K.Kojima [QC#28107,ADD]
            if (taxApiPMsg != null) {
                if (!S21ApiUtil.isXxMsgId(taxApiPMsg)) {
                    BigDecimal baseTaxFuncAmt = setAmtScale(getTaxFuncAmt(taxApiPMsg));
                    BigDecimal baseTaxDealAmt = calcDealFromFunc(baseTaxFuncAmt);
                    setValue(svcContrAddlChrgBllgTmsg.slsTaxRate, getTaxRate(taxApiPMsg));
                    setValue(svcContrAddlChrgBllgTmsg.dealTaxAmt, baseTaxDealAmt);
                    setValue(svcContrAddlChrgBllgTmsg.funcTaxAmt, baseTaxFuncAmt);
                }
            }
        }
        S21ApiTBLAccessor.create(svcContrAddlChrgBllgTmsg);
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-createAddlChrg-insertSvcContrBllgAllocForAddl start");
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
        insertSvcContrBllgAllocForAddl(pMsg, rsltSchdMap, svcContrAddlChrgBllgTmsg);
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        s21InfoLogOutputPrintln("NSZC0560-createAddlChrg-insertSvcContrBllgAllocForAddl end");
        // END 2018/09/10 K.Kojima [QC#28107,ADD]
    }

    private boolean inputCheckBiilingStage(NSZC056001PMsg pMsg) {
        if (!mandatoryCheckBiilingStage(pMsg)) {
            return false;
        }
        return true;
    }

    private boolean inputPreviewBillingAPI(NSZC056001PMsg pMsg) {
        if (!mandatoryCheckPreviewBillingAPI(pMsg)) {
            return false;
        }
        return true;
    }

    private boolean inputCheckCreditRebill(NSZC056001PMsg pMsg) {
        if (!mandatoryCheckCreditRebill(pMsg)) {
            return false;
        }
        return true;
    }

    private boolean mandatoryCheckBiilingStage(NSZC056001PMsg pMsg) {

        mandatoryCheck(pMsg, pMsg.glblCmpyCd, NSZM0401E, MSG_GLBL_CMPY_CD, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.slsDt, NSZM0401E, MSG_SLS_DT, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.dsContrDtlPk, NSZM0401E, MSG_DS_CONTR_DTL_PK, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.baseChrgFlg, NSZM0401E, MSG_BASE_CHRG_FLG, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.usgChrgFlg, NSZM0401E, MSG_USG_CHRG_FLG, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.nextBllgDt, NSZM0401E, MSG_EFF_FROM_DT, MSG_INFO_IN_PRM);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private boolean mandatoryCheckPreviewBillingAPI(NSZC056001PMsg pMsg) {

        mandatoryCheck(pMsg, pMsg.glblCmpyCd, NSZM0401E, MSG_GLBL_CMPY_CD, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.slsDt, NSZM0401E, MSG_SLS_DT, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.dsContrDtlPk, NSZM0401E, MSG_DS_CONTR_DTL_PK, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.baseChrgFlg, NSZM0401E, MSG_BASE_CHRG_FLG, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.usgChrgFlg, NSZM0401E, MSG_USG_CHRG_FLG, MSG_INFO_IN_PRM);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private boolean mandatoryCheckCreditRebill(NSZC056001PMsg pMsg) {

        mandatoryCheck(pMsg, pMsg.glblCmpyCd, NSZM0401E, MSG_GLBL_CMPY_CD, MSG_INFO_IN_PRM);
        mandatoryCheck(pMsg, pMsg.slsDt, NSZM0401E, MSG_SLS_DT, MSG_INFO_IN_PRM);
        // Mod Start 05/12/2016 <QC#8183>
        mandatoryCheck(pMsg, pMsg.svcCrRebilDtlPk, NSZM0401E, MSG_SVC_CR_REBIL_PK, MSG_INFO_IN_PRM);
        // Mod End   05/12/2016 <QC#8183>
        mandatoryCheck(pMsg, pMsg.estFlg, NSZM0401E, MSG_EST_FLG, MSG_INFO_IN_PRM);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private boolean mandatoryCheck(NSZC056001PMsg pMsg, EZDPDateItem item, String msgId, String itemName, String infoInPrm) {
        if (!hasValue(item)) {
            setErrMsg(pMsg, msgId, itemName, infoInPrm);
            return false;
        }
        return false;
    }

    private boolean mandatoryCheck(NSZC056001PMsg pMsg, EZDPStringItem item, String msgId, String itemName, String infoInPrm) {
        if (!hasValue(item)) {
            setErrMsg(pMsg, msgId, itemName, infoInPrm);
            return false;
        }
        return true;
    }

    private boolean mandatoryCheck(NSZC056001PMsg pMsg, EZDPBigDecimalItem item, String msgId, String itemName, String infoInPrm) {
        if (!hasValue(item)) {
            setErrMsg(pMsg, msgId, itemName, infoInPrm);
            return false;
        }
        return true;
    }

    private BigDecimal getDailyPrrtDivRate(NSZC056001PMsg pMsg) {
        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("bllgCycleCd01", BLLG_CYCLE.DAILY);
        inMsg.setConditionValue("effFromDt01", pMsg.slsDt.getValue());
        inMsg.setConditionValue("effThruDt01", pMsg.slsDt.getValue());
        BLLG_CYCLETMsgArray outArray = (BLLG_CYCLETMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return BigDecimal.valueOf(12);
        }
        return outArray.no(0).prrtDivRate.getValue();
    }

    private DS_CONTR_DTLTMsg getDsContrDtl(NSZC056001PMsg pMsg) {
        DS_CONTR_DTLTMsg dsContrDtlTmsg = new DS_CONTR_DTLTMsg();
        setValue(dsContrDtlTmsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(dsContrDtlTmsg.dsContrDtlPk, pMsg.dsContrDtlPk);
        dsContrDtlTmsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(dsContrDtlTmsg);
        return dsContrDtlTmsg;
    }

    private DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg dsContrTmsg = new DS_CONTRTMsg();
        setValue(dsContrTmsg.glblCmpyCd, glblCmpyCd);
        setValue(dsContrTmsg.dsContrPk, dsContrPk);
        dsContrTmsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(dsContrTmsg);
        return dsContrTmsg;
    }

    private DS_CONTR_BLLG_SCHDTMsg getdsContrBllgSchd(NSZC056001PMsg pMsg, BigDecimal dsContrBllgSchdPk) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        inMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        return inMsg;
    }

    private SVC_CONTR_BLLGTMsg getSvcContrBllg(NSZC056001PMsg pMsg, BigDecimal dsContrBllgSchdPk) {
        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrBllgSchdPk01", dsContrBllgSchdPk);
        inMsg.setConditionValue("addlChrgFlg01", FLG_OFF_N);
        SVC_CONTR_BLLGTMsgArray svcContrBllgTmsgArray = (SVC_CONTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (svcContrBllgTmsgArray.getValidCount() != 0) {
            inMsg = svcContrBllgTmsgArray.no(0);
        } else {
            inMsg = null;
        }
        return inMsg;
    }

    private BigDecimal getBllgMtrCnt(NSZC056001PMsg pMsg, BigDecimal dsContrBllgSchdPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMtrCnt", param);
        BigDecimal bllgMtrCnt = BigDecimal.ZERO;
        // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
        this.tmpMtrReadDt = null;
        // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
        for (Map<String, Object> resultMap : resultMapList) {
            BigDecimal readMtrCnt = (BigDecimal) resultMap.get("READ_MTR_CNT");
            BigDecimal mtrMultRate = (BigDecimal) resultMap.get("CONTR_MTR_MULT_RATE");
            bllgMtrCnt = bllgMtrCnt.add(readMtrCnt.multiply(mtrMultRate).setScale(BigDecimal.ROUND_HALF_UP));
            // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
            if (this.tmpMtrReadDt == null) {
                this.tmpMtrReadDt = (String) resultMap.get("MTR_READ_DT");
            }
            // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
        }
        return bllgMtrCnt;
    }

    private BigDecimal getStartMtrGrpSq(NSZC056001PMsg pMsg, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrPk) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        inMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
        this.tmpMtrReadDt = null;
        // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
        if (outArray.getValidCount() == 0) {
            return null;
        }
        BigDecimal startMtrGrpSq = null;
        if (hasValue(outArray.no(0).svcPhysMtrReadGrpSq)) {
            startMtrGrpSq = outArray.no(0).svcPhysMtrReadGrpSq.getValue();
        }

        // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
        this.tmpMtrReadDt = outArray.no(0).mtrReadDt.getValue();
        // END 2016/10/06 T.Kanasaka [QC#12274, ADD]

        return startMtrGrpSq;
    }

    private BigDecimal getStartMtrCnt(NSZC056001PMsg pMsg, BigDecimal svcPhysMtrReadGrpSq, Map<String, Object> rsltSchdMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // START 2016/10/06 T.Kanasaka [QC#12274, MOD]
//        if (hasValue(pMsg.dsContrBllgMtrPk)) {
//        param.put("dsContrBllgMtrPk", pMsg.dsContrBllgMtrPk.getValue());
//        } else {
//            param.put("dsContrBllgMtrPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
//        }
        param.put("dsContrBllgMtrPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
        // END   2016/10/06 T.Kanasaka [QC#12274, MOD]
        param.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getStartMtrCnt", param);
        BigDecimal bllgMtrCnt = BigDecimal.ZERO;
        for (Map<String, Object> resultMap : resultMapList) {
            BigDecimal readMtrCnt = (BigDecimal) resultMap.get("READ_MTR_CNT");
            BigDecimal mtrMultRate = (BigDecimal) resultMap.get("CONTR_MTR_MULT_RATE");
            bllgMtrCnt = bllgMtrCnt.add(readMtrCnt.multiply(mtrMultRate).setScale(BigDecimal.ROUND_HALF_UP));
        }
        return bllgMtrCnt;
    }

    //START 2017/09/12 K.Kasai [QC#15134,MOD]
    // Mod Start 04/19/2016 <QC#7240>
    private CONTR_PHYS_BLLG_MTR_RELNTMsgArray getSvcPhysMtrPk(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//        if (hasValue(pMsg.dsContrBllgMtrPk)) {
//            inMsg.setConditionValue("dsContrBllgMtrPk01", pMsg.dsContrBllgMtrPk.getValue());
//        } else {
//            inMsg.setConditionValue("dsContrBllgMtrPk01", (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
//        }
        inMsg.setConditionValue("dsContrBllgMtrPk01", (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
        inMsg.setConditionValue("bllblFlg01", FLG_ON_Y);
        CONTR_PHYS_BLLG_MTR_RELNTMsgArray outArray = (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray;
    }
    // Mod End   04/19/2016 <QC#7240>
    //END 2017/09/12 K.Kasai [QC#15134,MOD]

    private List<Map<String, Object>> getCrRebilMtrCnt(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // START 2016/03/24 T.Aoyagi [QC#5901, MOD]
        param.put("svcCrRebilDtlPk", (BigDecimal) rsltSchdMap.get("SVC_CR_REBIL_DTL_PK"));
        param.put("dsContrBllgMtrPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
        // END 2016/03/24 T.Aoyagi [QC#5901, MOD]
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMap = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCrRebilMtrCnt", param);
        return resultMap;
    }

    private List<Map<String, Object>> getTargetDsContrBllgSchd(NSZC056001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrDtlPk", pMsg.dsContrDtlPk.getValue());
        param.put("nextBllgDt", pMsg.nextBllgDt);
        // START 2018/08/29 K.Kojima [QC#28012,ADD]
        param.put("bllgSchdFromDt", pMsg.svcContrBllgFromDt.getValue());
        // END 2018/08/29 K.Kojima [QC#28012,ADD]
        param.put("baseChrgFlg", pMsg.baseChrgFlg.getValue());
        param.put("usgChrgFlg", pMsg.usgChrgFlg.getValue());
        param.put("dsContrBllgMtrPk", pMsg.dsContrBllgMtrPk.getValue());
        // Mod Start 05/12/2016 <QC#8183>
        if (hasValue(pMsg.svcCrRebilDtlPk)) {
            param.put("svcCrRebilDtlPk", pMsg.svcCrRebilDtlPk.getValue());
        }
        // Mod End   05/12/2016 <QC#8183>
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMap = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTargetDsContrBllgSchdNonFleet", param);
        return resultMap;
    }

    private List<Map<String, Object>> getTargetDsContrBllgSchdFleetAggregateChild(NSZC056001PMsg pMsg, BigDecimal prntDsContrBllgSchdPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("prntDsContrBllgSchdPk", prntDsContrBllgSchdPk);
        param.put("baseChrgFlg", pMsg.baseChrgFlg.getValue());
        param.put("usgChrgFlg", pMsg.usgChrgFlg.getValue());
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTargetDsContrBllgSchdFleetAggregateChild", param);
        return resultMapList;
    }

    private List<Map<String, Object>> getDsContrAddlChrg(NSZC056001PMsg pMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String fromDt, String thruDt, String billByTp) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        // START 2017/12/21 K.Kojima [QC#18562,MOD]
        // param.put("fromDt", fromDt);
        // param.put("thruDt", thruDt);
        param.put("fromDt", thruDt);
        param.put("thruDt", fromDt);
        // END 2017/12/21 K.Kojima [QC#18562,MOD]
        param.put("slsDt", pMsg.slsDt.getValue());
        param.put("svcBillByTpCd", billByTp);
        if (FLG_ON_Y.equals(pMsg.baseChrgFlg.getValue())) {
            param.put("addlChrgInvTpCd", ADDL_CHRG_INV_TP.BASE);
        } else if (FLG_ON_Y.equals(pMsg.usgChrgFlg.getValue())) {
            param.put("addlChrgInvTpCd", ADDL_CHRG_INV_TP.USAGE);
        } else {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsContrAddlChrg", param);
        return resultMapList;
    }

    private Map<String, Object> getPrevSchd(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrDtlPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
        param.put("dsContrBllgMtrPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
        param.put("targetDt", addDays((String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"), -1));
        param.put("invTpCd", INV_TP.INVOICE);
        @SuppressWarnings("unchecked")
        Map<String, Object> resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getPrevSchd", param);
        return resultMap;
    }

    private Map<String, Object> getFleetReadRollUp(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("prntDsContrBllgSchdPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
        @SuppressWarnings("unchecked")
        Map<String, Object> resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getFleetReadRollUp", param);
        return resultMap;
    }

    private List<Map<String, Object>> getUsgChrgRate(NSZC056001PMsg pMsg, BigDecimal dsContrBllgSchdPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getUsgChrgRate", param);
        return resultMapList;
    }

    private List<GetDefCoaTrxCdForOutListInfoBean> getAllocList(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, String mdseCd, String svcInvChrgTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_PK"));
        param.put("dsContrDtlPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
        // START 2018/04/11 M.Naito [QC#23378,MOD]
//        param.put("svcInvCrhgTpCd", "MC");
        param.put("svcInvChrgTpCd", svcInvChrgTpCd);
        List<GetDefCoaTrxCdForOutListInfoBean> outList = new ArrayList<GetDefCoaTrxCdForOutListInfoBean>();
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getContrAlloc", param);
        if (resultMapList.size() == 0) {
            param.remove("svcInvChrgTpCd");
            resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getContrAlloc", param);
        } else if (resultMapList.size() == 0) {
        // END 2018/04/11 M.Naito [QC#23378,MOD]
//        if (resultMapList.size() == 0) {
            param.remove("dsContrDtlPk");
            resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getContrAlloc", param);
            if (resultMapList.size() == 0) {
                return outList;
            }
        }
        SVC_INV_CHRG_TPTMsg svcInvChrgTpTmsg = new SVC_INV_CHRG_TPTMsg();
        setValue(svcInvChrgTpTmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(svcInvChrgTpTmsg.svcInvChrgTpCd, svcInvChrgTpCd);
        svcInvChrgTpTmsg = (SVC_INV_CHRG_TPTMsg) S21ApiTBLAccessor.findByKey(svcInvChrgTpTmsg);

        Map<String, Object> paramRule = new HashMap<String, Object>();
        paramRule = new HashMap<String, Object>();
        paramRule.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramRule.put("mdseCd", mdseCd);
        Map<String, Object> resultRule = (Map<String, Object>) ssmBatchClient.queryObject("getdfrdAcctgRule", paramRule);

        for (Map<String, Object> reslultMap : resultMapList) {
            GetDefCoaTrxCdForOutListInfoBean outBean = new GetDefCoaTrxCdForOutListInfoBean();
            outBean.setCoaCmpyCd((String) reslultMap.get("COA_CMPY_CD"));
            outBean.setCoaAfflCd((String) reslultMap.get("COA_AFFL_CD"));
            outBean.setCoaBrCd((String) reslultMap.get("COA_BR_CD"));
            outBean.setCoaChCd((String) reslultMap.get("COA_CH_CD"));
            outBean.setCoaCcCd((String) reslultMap.get("COA_CC_CD"));
            outBean.setCoaAcctCd((String) reslultMap.get("COA_ACCT_CD"));
            outBean.setCoaProdCd((String) reslultMap.get("COA_PROD_CD"));
            outBean.setCoaProjCd((String) reslultMap.get("COA_PROJ_CD"));
            outBean.setCoaExtnCd((String) reslultMap.get("COA_EXTN_CD"));
            outBean.setInvLineSplTpCd("");
            outBean.setPrcAllocPct((BigDecimal) reslultMap.get("PRC_ALLOC_RATE"));
            // START 2018/04/11 M.Naito [QC#23378,ADD]
            outBean.setPrcAllocAmt((BigDecimal) reslultMap.get("PRC_ALLOC_AMT"));
            // START 2018/04/11 M.Naito [QC#23378,ADD]
            outBean.setTrxCd(svcInvChrgTpTmsg.trxCd.getValue());
            outBean.setTrxRsnCd(svcInvChrgTpTmsg.trxRsnCd.getValue());
            outBean.setDfrdAcctgRuleCd((String) resultRule.get("DFRD_ACCTG_RULE_CD"));
            outBean.setDfrdAcctgRuleDurnAot((BigDecimal) resultRule.get("DFRD_ACCTG_RULE_DURN_AOT"));
            outList.add(outBean);
        }
        return outList;
    }

    private void deleteTargetPeriod(NSZC056001PMsg pMsg, BigDecimal dsContrBllgSchdPk) {
        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = new SVC_CONTR_BLLGTMsg();
        svcContrBllgTMsg.setSQLID("004");
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        svcContrBllgTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcContrBllgTMsg.setConditionValue("dsContrBllgSchdPk01", dsContrBllgSchdPk);
//        svcContrBllgTMsg.setConditionValue("addlChrgFlg01", FLG_ON_Y);
        SVC_CONTR_BLLGTMsgArray svcContrBllgTMsgArray = (SVC_CONTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(svcContrBllgTMsg);
        if (svcContrBllgTMsgArray == null) {
            return;
        }
        for (int idx = 0; idx < svcContrBllgTMsgArray.getValidCount(); idx++) {
            BigDecimal svcContrBllgPk = svcContrBllgTMsgArray.no(idx).svcContrBllgPk.getValue();
            BigDecimal svcContrBllgGrpSq = svcContrBllgTMsgArray.no(idx).svcContrBllgGrpSq.getValue();
            deleteSvcContrBaseBllg(glblCmpyCd, svcContrBllgPk);
            deleteSvcContrMtrBllg(glblCmpyCd, svcContrBllgPk);
            deleteSvcContrAddlChrgBllg(glblCmpyCd, svcContrBllgPk);
            if (FLG_ON_Y.equals(svcContrBllgTMsgArray.no(idx).addlChrgFlg.getValue())) {
                S21ApiTBLAccessor.remove(svcContrBllgTMsgArray.no(idx));
            }
        }
    }

    private void deleteSvcContrBaseBllg(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_BASE_BLLGTMsg inTmsg = new SVC_CONTR_BASE_BLLGTMsg();
        inTmsg.setSQLID("001");
        inTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTmsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        SVC_CONTR_BASE_BLLGTMsgArray outArray = (SVC_CONTR_BASE_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inTmsg);
        if (outArray == null) {
            return;
        }
        for (int idx = 0; idx < outArray.getValidCount(); idx++) {
            deleteSvcContrBllgAlloc(glblCmpyCd, outArray.no(idx).svcContrBaseBllgPk.getValue(), null);
            S21ApiTBLAccessor.remove(outArray.no(idx));
        }
    }

    private void deleteSvcContrMtrBllg(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_MTR_BLLGTMsg inTmsg = new SVC_CONTR_MTR_BLLGTMsg();
        inTmsg.setSQLID("001");
        inTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTmsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        SVC_CONTR_MTR_BLLGTMsgArray outArray = (SVC_CONTR_MTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inTmsg);
        if (outArray == null) {
            return;
        }
        for (int idx = 0; idx < outArray.getValidCount(); idx++) {
            deleteSvcContrXsMtrBllg(glblCmpyCd, outArray.no(idx).svcContrMtrBllgPk.getValue());
            deleteSvcContrBllgAlloc(glblCmpyCd, null, outArray.no(idx).svcContrMtrBllgPk.getValue());
            S21ApiTBLAccessor.remove(outArray.no(idx));
        }
    }

    private void deleteSvcContrXsMtrBllg(String glblCmpyCd, BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_XS_MTR_BLLGTMsg inTmsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
        inTmsg.setSQLID("001");
        inTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTmsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        SVC_CONTR_XS_MTR_BLLGTMsgArray outArray = (SVC_CONTR_XS_MTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inTmsg);
        if (outArray == null) {
            return;
        }
        for (int idx = 0; idx < outArray.getValidCount(); idx++) {
            S21ApiTBLAccessor.remove(outArray.no(idx));
        }
    }

    private void deleteSvcContrBllgAlloc(String glblCmpyCd, BigDecimal svcContrBaseBllgPk, BigDecimal svcContrMtrBllgPk) {
        SVC_CONTR_BLLG_ALLOCTMsg inTmsg = new SVC_CONTR_BLLG_ALLOCTMsg();
        inTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        if (hasValue(svcContrBaseBllgPk)) {
            inTmsg.setSQLID("001");
            inTmsg.setConditionValue("svcContrBaseBllgPk01", svcContrBaseBllgPk);
        } else {
            inTmsg.setSQLID("002");
            inTmsg.setConditionValue("svcContrMtrBllgPk01", svcContrMtrBllgPk);
        }
        SVC_CONTR_BLLG_ALLOCTMsgArray outArray = (SVC_CONTR_BLLG_ALLOCTMsgArray) S21ApiTBLAccessor.findByCondition(inTmsg);
        if (outArray == null) {
            return;
        }
        for (int idx = 0; idx < outArray.getValidCount(); idx++) {
            S21ApiTBLAccessor.remove(outArray.no(idx));
        }
    }

    private void deleteSvcContrAddlChrgBllg(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_ADDL_CHRG_BLLGTMsg inTmsg = new SVC_CONTR_ADDL_CHRG_BLLGTMsg();
        inTmsg.setSQLID("001");
        inTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTmsg.setConditionValue("svcContrBllgPk01", svcContrBllgPk);
        SVC_CONTR_ADDL_CHRG_BLLGTMsgArray outArray = (SVC_CONTR_ADDL_CHRG_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inTmsg);
        if (outArray == null) {
            return;
        }
        for (int idx = 0; idx < outArray.getValidCount(); idx++) {
            S21ApiTBLAccessor.remove(outArray.no(idx));
        }
    }

//    private void deleteAggrUsgRecalc(String glblCmpyCd, BigDecimal svcContrBllgGrpSq) {
//        AGGR_USG_RECALTMsg inTmsg = new AGGR_USG_RECALTMsg();
//        inTmsg.setSQLID("001");
//        inTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inTmsg.setConditionValue("svcContrBllgGrpSq01", svcContrBllgGrpSq);
//        AGGR_USG_RECALTMsgArray outArray = (AGGR_USG_RECALTMsgArray) S21ApiTBLAccessor.findByCondition(inTmsg);
//        if (outArray == null) {
//            return;
//        }
//        for (int idx = 0; idx < outArray.getValidCount(); idx++) {
//            deleteAggrUsgRecalcXsMtr(glblCmpyCd, outArray.no(idx).aggrUsgRecalPk.getValue());
//            S21ApiTBLAccessor.remove(outArray.no(idx));
//        }
//    }
//
//    private void deleteAggrUsgRecalcDtl(String glblCmpyCd, BigDecimal aggrUsgRecalPk) {
//        AGGR_USG_RECAL_DTLTMsg inTmsg = new AGGR_USG_RECAL_DTLTMsg();
//        inTmsg.setSQLID("002");
//        inTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inTmsg.setConditionValue("aggrUsgRecalPk01", aggrUsgRecalPk);
//        AGGR_USG_RECAL_DTLTMsgArray outArray = (AGGR_USG_RECAL_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inTmsg);
//        if (outArray == null) {
//            return;
//        }
//        for (int idx = 0; idx < outArray.getValidCount(); idx++) {
//            deleteAggrUsgRecalcXsMtr(glblCmpyCd, outArray.no(idx).aggrUsgRecalDtlPk.getValue());
//            S21ApiTBLAccessor.remove(outArray.no(idx));
//        }
//    }
//
//    private void deleteAggrUsgRecalcXsMtr(String glblCmpyCd, BigDecimal aggrUsgRecalDtlPk) {
//        AGGR_USG_RECAL_XS_MTRTMsg inTmsg = new AGGR_USG_RECAL_XS_MTRTMsg();
//        inTmsg.setSQLID("002");
//        inTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inTmsg.setConditionValue("aggrUsgRecalDtlPk01", aggrUsgRecalDtlPk);
//        AGGR_USG_RECAL_XS_MTRTMsgArray outArray = (AGGR_USG_RECAL_XS_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(inTmsg);
//        if (outArray == null) {
//            return;
//        }
//        for (int idx = 0; idx < outArray.getValidCount(); idx++) {
//            S21ApiTBLAccessor.remove(outArray.no(idx));
//        }
//    }

    private SVC_CONTR_MTR_BLLGTMsg defSetSvcContrMtrBllg(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, BigDecimal svcContrBllgPk) {
        SVC_CONTR_MTR_BLLGTMsg inMsg = new SVC_CONTR_MTR_BLLGTMsg();
        BigDecimal svcContrMtrBllgPk = (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_MTR_BLLG_SQ);
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.svcContrMtrBllgPk, svcContrMtrBllgPk);
        setValue(inMsg.svcContrBllgPk, svcContrBllgPk);
        setValue(inMsg.dsContrDtlPk, (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
        setValue(inMsg.dsContrBllgMtrPk, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
        // dsContrBllgMtrId does not require variable
        setValue(inMsg.mtrChrgDealAmt, BigDecimal.ZERO);
        setValue(inMsg.mtrChrgFuncAmt, BigDecimal.ZERO);
        setValue(inMsg.mtrChrgDiscDealAmt, BigDecimal.ZERO);
        setValue(inMsg.mtrChrgDiscFuncAmt, BigDecimal.ZERO);
        if (hasValue(pMsg.estFlg)) {
            setValue(inMsg.mtrEstFlg, pMsg.estFlg);
        } else {
            setValue(inMsg.mtrEstFlg, FLG_OFF_N);
        }
        setValue(inMsg.mtrCopyQty, BigDecimal.ZERO);
        // mdseCd does not require variable
        setValue(inMsg.ccyCd, (String) rsltSchdMap.get("CCY_CD"));
        setValue(inMsg.mtrBllgFromDt, (String) rsltSchdMap.get("BLLG_SCHD_FROM_DT"));
        setValue(inMsg.mtrBllgThruDt, (String) rsltSchdMap.get("BLLG_SCHD_THRU_DT"));
        setValue(inMsg.bllgCopyQty, BigDecimal.ZERO);
        setValue(inMsg.testCopyQty, BigDecimal.ZERO);
        setValue(inMsg.totCopyQty, BigDecimal.ZERO);
        setValue(inMsg.prevTotCopyQty, BigDecimal.ZERO);
        setValue(inMsg.prevPhysMtrReadGrpSq, (BigDecimal) rsltSchdMap.get("SVC_PHYS_MTR_READ_GRP_SQ"));
        setValue(inMsg.svcPhysMtrReadGrpSq, BigDecimal.ZERO);
        setValue(inMsg.mtrPerMthAot, BigDecimal.ZERO);
        setValue(inMsg.freeCopyCnt, BigDecimal.ZERO);
        setValue(inMsg.usgFreeCopyCnt, BigDecimal.ZERO);
        setValue(inMsg.xsChrgTpCd, (String) rsltSchdMap.get("XS_CHRG_TP_CD"));
        setValue(inMsg.rollOverRatio, BigDecimal.ZERO);
        setValue(inMsg.rollOverCnt, BigDecimal.ZERO);
        setValue(inMsg.copyInclQty, BigDecimal.ZERO);
        setValue(inMsg.aggrAdjCopyQty, BigDecimal.ZERO);
        setValue(inMsg.slsTaxRate, BigDecimal.ZERO);
        return inMsg;
    }

    private void insertSvcContrXsMtrBllgForSingleTier(SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTmsg, Map<String, Object> usgChrgRateMap) {
        SVC_CONTR_XS_MTR_BLLGTMsg inMsg = new SVC_CONTR_XS_MTR_BLLGTMsg();
        setValue(inMsg.glblCmpyCd, svcContrMtrBllgTmsg.glblCmpyCd);
        setValue(inMsg.svcContrXsMtrBllgPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_XS_MTR_BLLG_SQ));
        setValue(inMsg.svcContrMtrBllgPk, svcContrMtrBllgTmsg.svcContrMtrBllgPk);
        setValue(inMsg.svcContrBllgPk, svcContrMtrBllgTmsg.svcContrBllgPk);
        setValue(inMsg.dsContrDtlPk, svcContrMtrBllgTmsg.dsContrDtlPk);
        setValue(inMsg.dsContrBllgMtrPk, svcContrMtrBllgTmsg.dsContrBllgMtrPk);
        // dsContrBllgMtrId does not require variable
        setValue(inMsg.contrXsCopyPk, (BigDecimal) usgChrgRateMap.get("CONTR_XS_COPY_PK"));
        setValue(inMsg.xsMtrCopyQty, svcContrMtrBllgTmsg.bllgCopyQty);
        setValue(inMsg.xsMtrChrgDealAmt, svcContrMtrBllgTmsg.mtrChrgDealAmt);
        setValue(inMsg.xsMtrChrgFuncAmt, svcContrMtrBllgTmsg.mtrChrgFuncAmt);
        setValue(inMsg.xsMtrChrgDiscDealAmt, BigDecimal.ZERO);
        setValue(inMsg.xsMtrChrgDiscFuncAmt, BigDecimal.ZERO);
        setValue(inMsg.ccyCd, svcContrMtrBllgTmsg.ccyCd);
        setValue(inMsg.xsMtrAmtRate, (BigDecimal) usgChrgRateMap.get("XS_MTR_AMT_RATE"));
        setValue(inMsg.xsMtrFromCopyQty, (BigDecimal) usgChrgRateMap.get("XS_MTR_COPY_QTY"));
        S21ApiTBLAccessor.create(inMsg);
    }

    private void insertSvcContrBllgAllocForBase(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, SVC_CONTR_BASE_BLLGTMsg orgBllgTmsg) {
        List<GetDefCoaTrxCdForOutListInfoBean> allocList = getAllocList(pMsg, rsltSchdMap, (String) rsltSchdMap.get("SVC_PGM_MDSE_CD"), SVC_INV_CHRG_TP.BASE_CHARGE);
        if (allocList.size() == 0) {
            GetDefCoaTrxCdInfoBean bean = new GetDefCoaTrxCdInfoBean();
            bean.setGlblCmpyCd(orgBllgTmsg.glblCmpyCd.getValue());
            bean.setSvcMachMstrPk((BigDecimal) rsltSchdMap.get("SVC_MACH_MSTR_PK"));
            bean.setDsContrDtlPk((BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
            bean.setMdseCd((String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
            bean.setDsAcctNum((String) rsltSchdMap.get("DS_ACCT_NUM"));
            bean.setBaseChrgFlg(FLG_ON_Y);
            bean.setUsgChrgFlg(FLG_OFF_N);
            bean.setAddlChrgFlg(FLG_OFF_N);
            if (NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(bean) != null) {
                allocList = bean.getOutLisstInfoBean();
            }
        }
        if (allocList.size() == 0) {
            return;
        }
        List<SVC_CONTR_BLLG_ALLOCTMsg> listTmsg = new ArrayList<SVC_CONTR_BLLG_ALLOCTMsg>();
        SVC_CONTR_BLLG_ALLOCTMsg maxAllocTmsg = null;
        BigDecimal maxBllgAmt = BigDecimal.ZERO;
        BigDecimal bllgAmt = orgBllgTmsg.baseDealAmt.getValue();
        BigDecimal bllgDiscAmt = orgBllgTmsg.baseDiscDealAmt.getValue();
        BigDecimal totAmt = BigDecimal.ZERO;
        BigDecimal totDiscAmt = BigDecimal.ZERO;
        BigDecimal slsAllocRate = BigDecimal.ZERO;
        BigDecimal aftDeclPntDigitNum = (BigDecimal) rsltSchdMap.get("AFT_DECL_PNT_DIGIT_NUM");
        int scale = aftDeclPntDigitNum.intValue();
        // START 2018/04/11 M.Naito [QC#23378,ADD]
        BigDecimal totPrcAllocAmt = getTotPrcAllocAmt(allocList, scale);
        // END 2018/04/11 M.Naito [QC#23378,ADD]
        for (GetDefCoaTrxCdForOutListInfoBean outBean : allocList) {
            slsAllocRate = outBean.getPrcAllocPct();
            if (!hasValue(slsAllocRate) && !hasValue(outBean.getPrcAllocAmt())) {
                continue;
            }
            SVC_CONTR_BLLG_ALLOCTMsg inMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inMsg.svcContrBllgAllocPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_ALLOC_SQ));
            setValue(inMsg.svcContrBaseBllgPk, orgBllgTmsg.svcContrBaseBllgPk);
            setValue(inMsg.contrPrcAllocTpCd, CONTR_PRC_ALLOC_TP.BASE);
            setValue(inMsg.intgMdseCd, (String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
            setValue(inMsg.tocCd, (String) rsltSchdMap.get("TOC_CD"));
            setValue(inMsg.slsAllocRate, slsAllocRate);
            setValue(inMsg.dealGrsUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.dealNetUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.dealGrsTotPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineDealNetAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineDealTaxAmt, BigDecimal.ZERO);
            setValue(inMsg.funcGrsUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.funcDiscUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.funcNetUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.funcGrsTotPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineFuncNetAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineFuncTaxAmt, BigDecimal.ZERO);
            setValue(inMsg.coaCmpyCd, outBean.getCoaCmpyCd());
            setValue(inMsg.coaAfflCd, outBean.getCoaAfflCd());
            setValue(inMsg.coaBrCd, outBean.getCoaBrCd());
            setValue(inMsg.coaChCd, outBean.getCoaChCd());
            setValue(inMsg.coaCcCd, outBean.getCoaCcCd());
            setValue(inMsg.coaAcctCd, outBean.getCoaAcctCd());
            setValue(inMsg.coaProdCd, outBean.getCoaProdCd());
            setValue(inMsg.coaProjCd, outBean.getCoaProjCd());
            setValue(inMsg.coaExtnCd, outBean.getCoaExtnCd());
            setValue(inMsg.ccyCd, (String) rsltSchdMap.get("CCY_CD"));
            setValue(inMsg.svcContrBllgPk, orgBllgTmsg.svcContrBllgPk);
            setValue(inMsg.trxCd, outBean.getTrxCd());
            setValue(inMsg.trxRsnCd, outBean.getTrxRsnCd());
            setValue(inMsg.dfrdAcctgRuleCd, outBean.getDfrdAcctgRuleCd());
            setValue(inMsg.dfrdAcctgRuleDurnAot, outBean.getDfrdAcctgRuleDurnAot());

            // START 2018/04/11 M.Naito [QC#23378,MOD]
            if (hasValue(totPrcAllocAmt) && BigDecimal.ZERO.compareTo(totPrcAllocAmt) != 0) {
                calcAllocFromPrcAllocAmt(bllgAmt, bllgDiscAmt, totPrcAllocAmt, outBean.getPrcAllocAmt(), scale, inMsg, SVC_INV_CHRG_TP.BASE_CHARGE);
            } else {
                calcAlloc(bllgAmt, bllgDiscAmt, slsAllocRate, scale, inMsg);
            }
            // END 2018/04/11 M.Naito [QC#23378,MOD]
            if (maxAllocTmsg == null || maxBllgAmt.compareTo(inMsg.dealGrsUnitPrcAmt.getValue()) < 0) {
                maxAllocTmsg = inMsg;
                maxBllgAmt = inMsg.dealGrsUnitPrcAmt.getValue();
            }
            totAmt = totAmt.add(inMsg.dealGrsUnitPrcAmt.getValue());
            totDiscAmt = totDiscAmt.add(inMsg.dealDiscUnitPrcAmt.getValue());
            listTmsg.add(inMsg);
        }
        if (bllgAmt.compareTo(totAmt) != 0 || bllgDiscAmt.compareTo(totDiscAmt) != 0) {
            BigDecimal adjBillAmt = bllgAmt.subtract(totAmt);
            BigDecimal adjDiscAmt = bllgDiscAmt.subtract(totDiscAmt);
            BigDecimal dealGrsUnitPrcAmt = maxAllocTmsg.dealGrsUnitPrcAmt.getValue();
            BigDecimal dealDiscUnitPrcAmt = maxAllocTmsg.dealDiscUnitPrcAmt.getValue();

            dealGrsUnitPrcAmt = dealGrsUnitPrcAmt.add(adjBillAmt);
            dealDiscUnitPrcAmt = dealDiscUnitPrcAmt.add(adjDiscAmt);
            setValue(maxAllocTmsg.dealGrsUnitPrcAmt, dealGrsUnitPrcAmt);
            setValue(maxAllocTmsg.dealDiscUnitPrcAmt, dealDiscUnitPrcAmt);
            setValue(maxAllocTmsg.dealNetUnitPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
            setValue(maxAllocTmsg.dealGrsTotPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
            setValue(maxAllocTmsg.invLineDealNetAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));

            setValue(maxAllocTmsg.funcGrsUnitPrcAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt));
            setValue(maxAllocTmsg.funcDiscUnitPrcAmt, calcFuncAmtFromDealAmt(dealDiscUnitPrcAmt));
            setValue(maxAllocTmsg.funcNetUnitPrcAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt)));
            setValue(maxAllocTmsg.funcGrsTotPrcAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt)));
            setValue(maxAllocTmsg.invLineFuncNetAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt)));
        }
        for (SVC_CONTR_BLLG_ALLOCTMsg outTmsg : listTmsg) {
            S21ApiTBLAccessor.create(outTmsg);
        }
    }

    private void insertSvcContrBllgAllocForMtr(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, SVC_CONTR_MTR_BLLGTMsg orgBllgTmsg) {
        List<GetDefCoaTrxCdForOutListInfoBean> allocList = getAllocList(pMsg, rsltSchdMap, (String) rsltSchdMap.get("INTG_MDSE_CD"), SVC_INV_CHRG_TP.METER_CHARGE);
        if (allocList.size() == 0) {
            GetDefCoaTrxCdInfoBean bean = new GetDefCoaTrxCdInfoBean();
            bean.setGlblCmpyCd(orgBllgTmsg.glblCmpyCd.getValue());
            bean.setSvcMachMstrPk((BigDecimal) rsltSchdMap.get("SVC_MACH_MSTR_PK"));
            bean.setDsContrDtlPk((BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
            bean.setMdseCd((String) rsltSchdMap.get("INTG_MDSE_CD"));
            bean.setDsAcctNum((String) rsltSchdMap.get("DS_ACCT_NUM"));
            bean.setBaseChrgFlg(FLG_OFF_N);
            bean.setUsgChrgFlg(FLG_ON_Y);
            bean.setAddlChrgFlg(FLG_OFF_N);
            bean.setDsContrBllgMtrPk(orgBllgTmsg.dsContrBllgMtrPk.getValue());
            NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(bean);
            allocList = bean.getOutLisstInfoBean();
        }
        if (allocList == null || allocList.size() == 0) {
            return;
        }
        List<SVC_CONTR_BLLG_ALLOCTMsg> listTmsg = new ArrayList<SVC_CONTR_BLLG_ALLOCTMsg>();
        SVC_CONTR_BLLG_ALLOCTMsg maxAllocTmsg = null;
        BigDecimal maxBllgAmt = BigDecimal.ZERO;
        BigDecimal bllgAmt = orgBllgTmsg.mtrChrgDealAmt.getValue();
        BigDecimal bllgDiscAmt = orgBllgTmsg.mtrChrgDiscDealAmt.getValue();
        BigDecimal totAmt = BigDecimal.ZERO;
        BigDecimal totDiscAmt = BigDecimal.ZERO;
        BigDecimal slsAllocRate = BigDecimal.ZERO;
        BigDecimal aftDeclPntDigitNum = (BigDecimal) rsltSchdMap.get("AFT_DECL_PNT_DIGIT_NUM");
        int scale = aftDeclPntDigitNum.intValue();
        for (GetDefCoaTrxCdForOutListInfoBean outBean : allocList) {
            slsAllocRate = outBean.getPrcAllocPct();
            if (!hasValue(slsAllocRate)) {
                continue;
            }
            SVC_CONTR_BLLG_ALLOCTMsg inMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inMsg.svcContrBllgAllocPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_ALLOC_SQ));
            setValue(inMsg.svcContrMtrBllgPk, orgBllgTmsg.svcContrMtrBllgPk);
            setValue(inMsg.contrPrcAllocTpCd, CONTR_PRC_ALLOC_TP.USAGE);
            setValue(inMsg.intgMdseCd, (String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
            setValue(inMsg.tocCd, (String) rsltSchdMap.get("TOC_CD"));
            setValue(inMsg.slsAllocRate, slsAllocRate);
            setValue(inMsg.dealGrsUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.dealNetUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.dealGrsTotPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineDealNetAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineDealTaxAmt, BigDecimal.ZERO);
            setValue(inMsg.funcGrsUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.funcDiscUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.funcNetUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.funcGrsTotPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineFuncNetAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineFuncTaxAmt, BigDecimal.ZERO);
            setValue(inMsg.coaCmpyCd, outBean.getCoaCmpyCd());
            setValue(inMsg.coaAfflCd, outBean.getCoaAfflCd());
            setValue(inMsg.coaBrCd, outBean.getCoaBrCd());
            setValue(inMsg.coaChCd, outBean.getCoaChCd());
            setValue(inMsg.coaCcCd, outBean.getCoaCcCd());
            setValue(inMsg.coaAcctCd, outBean.getCoaAcctCd());
            setValue(inMsg.coaProdCd, outBean.getCoaProdCd());
            setValue(inMsg.coaProjCd, outBean.getCoaProjCd());
            setValue(inMsg.coaExtnCd, outBean.getCoaExtnCd());
            setValue(inMsg.ccyCd, (String) rsltSchdMap.get("CCY_CD"));
            setValue(inMsg.svcContrBllgPk, orgBllgTmsg.svcContrBllgPk);
            setValue(inMsg.svcContrAddlChrgBllgPk, BigDecimal.ZERO);
            setValue(inMsg.trxCd, outBean.getTrxCd());
            setValue(inMsg.trxRsnCd, outBean.getTrxRsnCd());
            setValue(inMsg.dfrdAcctgRuleCd, outBean.getDfrdAcctgRuleCd());
            setValue(inMsg.dfrdAcctgRuleDurnAot, outBean.getDfrdAcctgRuleDurnAot());

            calcAlloc(bllgAmt, bllgDiscAmt, slsAllocRate, scale, inMsg);
            if (maxBllgAmt.compareTo(inMsg.dealGrsUnitPrcAmt.getValue()) < 0) {
                maxAllocTmsg = inMsg;
                maxBllgAmt = inMsg.dealGrsUnitPrcAmt.getValue();
            }
            totAmt = totAmt.add(inMsg.dealGrsUnitPrcAmt.getValue());
            totDiscAmt = totDiscAmt.add(inMsg.dealDiscUnitPrcAmt.getValue());
            listTmsg.add(inMsg);
        }
        if (bllgAmt.compareTo(totAmt) != 0 || bllgDiscAmt.compareTo(totDiscAmt) != 0) {
            BigDecimal adjBillAmt = bllgAmt.subtract(totAmt);
            BigDecimal adjDiscAmt = bllgDiscAmt.subtract(totDiscAmt);
            BigDecimal dealGrsUnitPrcAmt = maxAllocTmsg.dealGrsUnitPrcAmt.getValue();
            BigDecimal dealDiscUnitPrcAmt = maxAllocTmsg.dealDiscUnitPrcAmt.getValue();

            dealGrsUnitPrcAmt = dealGrsUnitPrcAmt.add(adjBillAmt);
            dealDiscUnitPrcAmt = dealDiscUnitPrcAmt.add(adjDiscAmt);
            setValue(maxAllocTmsg.dealGrsUnitPrcAmt, dealGrsUnitPrcAmt);
            setValue(maxAllocTmsg.dealDiscUnitPrcAmt, dealDiscUnitPrcAmt);
            setValue(maxAllocTmsg.dealNetUnitPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
            setValue(maxAllocTmsg.dealGrsTotPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
            setValue(maxAllocTmsg.invLineDealNetAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));

            setValue(maxAllocTmsg.funcGrsUnitPrcAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt));
            setValue(maxAllocTmsg.funcDiscUnitPrcAmt, calcFuncAmtFromDealAmt(dealDiscUnitPrcAmt));
            setValue(maxAllocTmsg.funcNetUnitPrcAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt)));
            setValue(maxAllocTmsg.funcGrsTotPrcAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt)));
            setValue(maxAllocTmsg.invLineFuncNetAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt)));
        }
        for (SVC_CONTR_BLLG_ALLOCTMsg outTmsg : listTmsg) {
            S21ApiTBLAccessor.create(outTmsg);
        }
    }

    private void insertSvcContrBllgAllocForAddl(NSZC056001PMsg pMsg, Map<String, Object> rsltSchdMap, SVC_CONTR_ADDL_CHRG_BLLGTMsg orgBllgTmsg) {
        // mod start 2016/09/27 CSA Defect#10787
//        List<GetDefCoaTrxCdForOutListInfoBean> allocList = getAllocList(pMsg, rsltSchdMap, (String) rsltSchdMap.get("SVC_PGM_MDSE_CD"), SVC_INV_CHRG_TP.BASE_CHARGE);
//        List<GetDefCoaTrxCdForOutListInfoBean> allocList = getAllocList(pMsg, rsltSchdMap, orgBllgTmsg.intgMdseCd.getValue(), SVC_INV_CHRG_TP.BASE_CHARGE);
        // START 2018/04/11 M.Naito [QC#23378,MOD]
        ADDL_CHRG_INV_TPTMsg addlChrgInvTpTmsg = new ADDL_CHRG_INV_TPTMsg();
        setValue(addlChrgInvTpTmsg.glblCmpyCd, orgBllgTmsg.glblCmpyCd.getValue());
        setValue(addlChrgInvTpTmsg.addlChrgInvTpCd, orgBllgTmsg.addlChrgInvTpCd.getValue());
        addlChrgInvTpTmsg = (ADDL_CHRG_INV_TPTMsg) S21ApiTBLAccessor.findByKey(addlChrgInvTpTmsg);
        if (addlChrgInvTpTmsg == null) {
            return;
        }
        List<GetDefCoaTrxCdForOutListInfoBean> allocList = getAllocList(pMsg, rsltSchdMap, orgBllgTmsg.intgMdseCd.getValue(), addlChrgInvTpTmsg.svcInvChrgTpCd.getValue());
        // END 2018/04/11 M.Naito [QC#23378,MOD]
        // mod end 2016/09/27 CSA Defect#10787
        if (allocList.size() == 0) {
            GetDefCoaTrxCdInfoBean bean = new GetDefCoaTrxCdInfoBean();
            bean.setGlblCmpyCd(orgBllgTmsg.glblCmpyCd.getValue());
            bean.setSvcMachMstrPk((BigDecimal) rsltSchdMap.get("SVC_MACH_MSTR_PK"));
            bean.setDsContrDtlPk((BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
            // mod start 2016/09/27 CSA Defect#10787
//            bean.setMdseCd((String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
            bean.setMdseCd(orgBllgTmsg.intgMdseCd.getValue());
            // mod end 2016/09/27 CSA Defect#10787
            bean.setDsAcctNum((String) rsltSchdMap.get("DS_ACCT_NUM"));
            bean.setBaseChrgFlg(FLG_ON_Y);
            bean.setUsgChrgFlg(FLG_OFF_N);
            bean.setAddlChrgFlg(FLG_OFF_N);
            NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(bean);
            allocList = bean.getOutLisstInfoBean();
        }
        if (allocList == null || allocList.size() == 0) {
            return;
        }
        List<SVC_CONTR_BLLG_ALLOCTMsg> listTmsg = new ArrayList<SVC_CONTR_BLLG_ALLOCTMsg>();
        SVC_CONTR_BLLG_ALLOCTMsg maxAllocTmsg = null;
        BigDecimal maxBllgAmt = BigDecimal.ZERO;
        BigDecimal bllgAmt = orgBllgTmsg.addlDealPrcAmt.getValue();
        BigDecimal bllgDiscAmt = BigDecimal.ZERO;
        BigDecimal totAmt = BigDecimal.ZERO;
        BigDecimal totDiscAmt = BigDecimal.ZERO;
        BigDecimal slsAllocRate = BigDecimal.ZERO;
        BigDecimal aftDeclPntDigitNum = (BigDecimal) rsltSchdMap.get("AFT_DECL_PNT_DIGIT_NUM");
        int scale = aftDeclPntDigitNum.intValue();
        // START 2018/04/11 M.Naito [QC#23378,ADD]
        BigDecimal totPrcAllocAmt = getTotPrcAllocAmt(allocList, scale);
        // END 2018/04/11 M.Naito [QC#23378,ADD]
        for (GetDefCoaTrxCdForOutListInfoBean outBean : allocList) {
            slsAllocRate = outBean.getPrcAllocPct();
            if (!hasValue(slsAllocRate) && !hasValue(outBean.getPrcAllocAmt())) {
                continue;
            }
            SVC_CONTR_BLLG_ALLOCTMsg inMsg = new SVC_CONTR_BLLG_ALLOCTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inMsg.svcContrBllgAllocPk, (BigDecimal) ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONTR_BLLG_ALLOC_SQ));
            setValue(inMsg.svcContrMtrBllgPk, orgBllgTmsg.svcContrMtrBllgPk);
            setValue(inMsg.contrPrcAllocTpCd, CONTR_PRC_ALLOC_TP.ADDITIONAL_CHARGE);
            // mod start 2016/09/27 CSA Defect#10787
//            setValue(inMsg.intgMdseCd, (String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
            setValue(inMsg.intgMdseCd, orgBllgTmsg.intgMdseCd.getValue());
            // mod end 2016/09/27 CSA Defect#10787
            setValue(inMsg.tocCd, (String) rsltSchdMap.get("TOC_CD"));
            setValue(inMsg.slsAllocRate, slsAllocRate);
            setValue(inMsg.dealGrsUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.dealNetUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.dealGrsTotPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineDealNetAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineDealTaxAmt, BigDecimal.ZERO);
            setValue(inMsg.funcGrsUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.funcDiscUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.funcNetUnitPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.funcGrsTotPrcAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineFuncNetAmt, BigDecimal.ZERO);
            setValue(inMsg.invLineFuncTaxAmt, BigDecimal.ZERO);
            setValue(inMsg.coaCmpyCd, outBean.getCoaCmpyCd());
            setValue(inMsg.coaAfflCd, outBean.getCoaAfflCd());
            setValue(inMsg.coaBrCd, outBean.getCoaBrCd());
            setValue(inMsg.coaChCd, outBean.getCoaChCd());
            setValue(inMsg.coaCcCd, outBean.getCoaCcCd());
            setValue(inMsg.coaAcctCd, outBean.getCoaAcctCd());
            setValue(inMsg.coaProdCd, outBean.getCoaProdCd());
            setValue(inMsg.coaProjCd, outBean.getCoaProjCd());
            setValue(inMsg.coaExtnCd, outBean.getCoaExtnCd());
            setValue(inMsg.ccyCd, (String) rsltSchdMap.get("CCY_CD"));
            setValue(inMsg.svcContrBllgPk, orgBllgTmsg.svcContrBllgPk);
            setValue(inMsg.svcContrAddlChrgBllgPk, orgBllgTmsg.svcContrAddlChrgBllgPk);
            setValue(inMsg.trxCd, outBean.getTrxCd());
            setValue(inMsg.trxRsnCd, outBean.getTrxRsnCd());
            setValue(inMsg.dfrdAcctgRuleCd, outBean.getDfrdAcctgRuleCd());
            setValue(inMsg.dfrdAcctgRuleDurnAot, outBean.getDfrdAcctgRuleDurnAot());

            // START 2018/04/11 M.Naito [QC#23378,MOD]
            if (hasValue(totPrcAllocAmt) && BigDecimal.ZERO.compareTo(totPrcAllocAmt) != 0) {
                calcAllocFromPrcAllocAmt(bllgAmt, bllgDiscAmt, totPrcAllocAmt, outBean.getPrcAllocAmt(), scale, inMsg, SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
            } else {
                calcAlloc(bllgAmt, bllgDiscAmt, slsAllocRate, scale, inMsg);
            }
            // END 2018/04/11 M.Naito [QC#23378,MOD]
            if (maxBllgAmt.compareTo(inMsg.dealGrsUnitPrcAmt.getValue()) < 0) {
                maxAllocTmsg = inMsg;
                maxBllgAmt = inMsg.dealGrsUnitPrcAmt.getValue();
            }
            totAmt = totAmt.add(inMsg.dealGrsUnitPrcAmt.getValue());
            totDiscAmt = totDiscAmt.add(inMsg.dealDiscUnitPrcAmt.getValue());
            listTmsg.add(inMsg);
        }
        if (bllgAmt.compareTo(totAmt) != 0 || bllgDiscAmt.compareTo(totDiscAmt) != 0) {
            BigDecimal adjBillAmt = bllgAmt.subtract(totAmt);
            BigDecimal adjDiscAmt = bllgDiscAmt.subtract(totDiscAmt);
            BigDecimal dealGrsUnitPrcAmt = maxAllocTmsg.dealGrsUnitPrcAmt.getValue();
            BigDecimal dealDiscUnitPrcAmt = maxAllocTmsg.dealDiscUnitPrcAmt.getValue();

            dealGrsUnitPrcAmt = dealGrsUnitPrcAmt.add(adjBillAmt);
            dealDiscUnitPrcAmt = dealDiscUnitPrcAmt.add(adjDiscAmt);
            setValue(maxAllocTmsg.dealGrsUnitPrcAmt, dealGrsUnitPrcAmt);
            setValue(maxAllocTmsg.dealDiscUnitPrcAmt, dealDiscUnitPrcAmt);
            setValue(maxAllocTmsg.dealNetUnitPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
            setValue(maxAllocTmsg.dealGrsTotPrcAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));
            setValue(maxAllocTmsg.invLineDealNetAmt, dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt));

            setValue(maxAllocTmsg.funcGrsUnitPrcAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt));
            setValue(maxAllocTmsg.funcDiscUnitPrcAmt, calcFuncAmtFromDealAmt(dealDiscUnitPrcAmt));
            setValue(maxAllocTmsg.funcNetUnitPrcAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt)));
            setValue(maxAllocTmsg.funcGrsTotPrcAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt)));
            setValue(maxAllocTmsg.invLineFuncNetAmt, calcFuncAmtFromDealAmt(dealGrsUnitPrcAmt.subtract(dealDiscUnitPrcAmt)));
        }
        for (SVC_CONTR_BLLG_ALLOCTMsg outTmsg : listTmsg) {
            S21ApiTBLAccessor.create(outTmsg);
        }
    }

    private void calcAlloc(BigDecimal bllgAmt, BigDecimal bllgDiscAmt, BigDecimal slsAllocRate, int aftDeclPntDigitNum, SVC_CONTR_BLLG_ALLOCTMsg allocTmsg) {
        BigDecimal dealGrossUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal dealDiscountUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal dealNetUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal dealGrossTotalPriceAmount = BigDecimal.ZERO;
        BigDecimal invoiceLineDealNetAmount = BigDecimal.ZERO;
        BigDecimal funcGrossUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal funcDiscountUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal funcNetUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal funcGrossTotalPriceAmount = BigDecimal.ZERO;
        BigDecimal invoiceLineFuncNetAmount = BigDecimal.ZERO;

        dealGrossUnitPriceAmount = bllgAmt.multiply(slsAllocRate.divide(BIGDECIMAL_100)).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        dealDiscountUnitPriceAmount = bllgDiscAmt.multiply(slsAllocRate.divide(BIGDECIMAL_100)).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        dealNetUnitPriceAmount = dealGrossUnitPriceAmount.subtract(dealDiscountUnitPriceAmount);
        dealGrossTotalPriceAmount = dealGrossUnitPriceAmount.subtract(dealDiscountUnitPriceAmount);
        invoiceLineDealNetAmount = dealGrossUnitPriceAmount.subtract(dealDiscountUnitPriceAmount);

        funcGrossUnitPriceAmount = calcFuncAmtFromDealAmt(dealGrossUnitPriceAmount);
        funcDiscountUnitPriceAmount = calcFuncAmtFromDealAmt(dealDiscountUnitPriceAmount);
        funcNetUnitPriceAmount = calcFuncAmtFromDealAmt(dealNetUnitPriceAmount);
        funcGrossTotalPriceAmount = calcFuncAmtFromDealAmt(dealGrossTotalPriceAmount);
        invoiceLineFuncNetAmount = calcFuncAmtFromDealAmt(invoiceLineDealNetAmount);

        setValue(allocTmsg.dealGrsUnitPrcAmt, dealGrossUnitPriceAmount);
        setValue(allocTmsg.dealDiscUnitPrcAmt, dealDiscountUnitPriceAmount);
        setValue(allocTmsg.dealNetUnitPrcAmt, dealNetUnitPriceAmount);
        setValue(allocTmsg.dealGrsTotPrcAmt, dealGrossTotalPriceAmount);
        setValue(allocTmsg.invLineDealNetAmt, invoiceLineDealNetAmount);

        setValue(allocTmsg.funcGrsUnitPrcAmt, funcGrossUnitPriceAmount);
        setValue(allocTmsg.funcDiscUnitPrcAmt, funcDiscountUnitPriceAmount);
        setValue(allocTmsg.funcNetUnitPrcAmt, funcNetUnitPriceAmount);
        setValue(allocTmsg.funcGrsTotPrcAmt, funcGrossTotalPriceAmount);
        setValue(allocTmsg.invLineFuncNetAmt, invoiceLineFuncNetAmount);
        return;
    }

    // START 2018/04/11 M.Naito [QC#23378,ADD]
    private void calcAllocFromPrcAllocAmt(BigDecimal bllgAmt, BigDecimal bllgDiscAmt, BigDecimal totPrcAllocAmt, BigDecimal prcAllocAmt, int aftDeclPntDigitNum, SVC_CONTR_BLLG_ALLOCTMsg allocTmsg, String svcInvLineTpCd) {
        BigDecimal dealGrossUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal dealDiscountUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal dealNetUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal dealGrossTotalPriceAmount = BigDecimal.ZERO;
        BigDecimal invoiceLineDealNetAmount = BigDecimal.ZERO;
        BigDecimal funcGrossUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal funcDiscountUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal funcNetUnitPriceAmount = BigDecimal.ZERO;
        BigDecimal funcGrossTotalPriceAmount = BigDecimal.ZERO;
        BigDecimal invoiceLineFuncNetAmount = BigDecimal.ZERO;

        if (bllgAmt.setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP).equals(totPrcAllocAmt)) {
            dealGrossUnitPriceAmount = prcAllocAmt;
        } else {
            dealGrossUnitPriceAmount = bllgAmt.multiply(prcAllocAmt).divide(totPrcAllocAmt, aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        }
        dealDiscountUnitPriceAmount = bllgDiscAmt.multiply(prcAllocAmt).divide(totPrcAllocAmt, aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        dealNetUnitPriceAmount = dealGrossUnitPriceAmount.subtract(dealDiscountUnitPriceAmount);
        dealGrossTotalPriceAmount = dealGrossUnitPriceAmount.subtract(dealDiscountUnitPriceAmount);
        invoiceLineDealNetAmount = dealGrossUnitPriceAmount.subtract(dealDiscountUnitPriceAmount);

        funcGrossUnitPriceAmount = calcFuncAmtFromDealAmt(dealGrossUnitPriceAmount);
        funcDiscountUnitPriceAmount = calcFuncAmtFromDealAmt(dealDiscountUnitPriceAmount);
        funcNetUnitPriceAmount = calcFuncAmtFromDealAmt(dealNetUnitPriceAmount);
        funcGrossTotalPriceAmount = calcFuncAmtFromDealAmt(dealGrossTotalPriceAmount);
        invoiceLineFuncNetAmount = calcFuncAmtFromDealAmt(invoiceLineDealNetAmount);

        setValue(allocTmsg.dealGrsUnitPrcAmt, dealGrossUnitPriceAmount);
        setValue(allocTmsg.dealDiscUnitPrcAmt, dealDiscountUnitPriceAmount);
        setValue(allocTmsg.dealNetUnitPrcAmt, dealNetUnitPriceAmount);
        setValue(allocTmsg.dealGrsTotPrcAmt, dealGrossTotalPriceAmount);
        setValue(allocTmsg.invLineDealNetAmt, invoiceLineDealNetAmount);

        setValue(allocTmsg.funcGrsUnitPrcAmt, funcGrossUnitPriceAmount);
        setValue(allocTmsg.funcDiscUnitPrcAmt, funcDiscountUnitPriceAmount);
        setValue(allocTmsg.funcNetUnitPrcAmt, funcNetUnitPriceAmount);
        setValue(allocTmsg.funcGrsTotPrcAmt, funcGrossTotalPriceAmount);
        setValue(allocTmsg.invLineFuncNetAmt, invoiceLineFuncNetAmount);
        return;
    }
    // END 2018/04/11 M.Naito [QC#23378,ADD]

    private void setErrMsg(NSZC056001PMsg pMsg, String msgId, String... msgPrm) {
        msgMap.addXxMsgIdWithPrm(msgId, msgPrm);
        msgMap.flush();
    }

    private BigDecimal calcPerMthAot(Map<String, Object> rsltSchdMap) {
        String perBllgCycleCd = (String) rsltSchdMap.get("PER_BLLG_CYCLE_CD");
        BigDecimal perMthAot = BigDecimal.ONE;
        if (BLLG_CYCLE.DAILY.equals(perBllgCycleCd)) {
            BigDecimal perSchdNum = (BigDecimal) rsltSchdMap.get("PER_SCHD_NUM");
            perMthAot = perSchdNum.divide(DAYS_OF_YEAR.divide(this.dailyPrrtDivRate, 2, BigDecimal.ROUND_HALF_UP), 2, BigDecimal.ROUND_HALF_UP);
        }
        return perMthAot;
    }

    // Mod Start 09/05/2016 <QC#14146>
    // START 2018/10/31 K.Kojima [QC#28999,MOD]
    // private boolean hasSameAddl(NSZC056001PMsg pMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrAddlChrgPk, String targetFromDt, String targetThruDt) {
    private boolean hasSameAddl(NSZC056001PMsg pMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrAddlChrgPk, String targetFromDt, String targetThruDt, String invTpCd) {
    // END 2018/10/31 K.Kojima [QC#28999,MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrAddlChrgPk", dsContrAddlChrgPk);
        param.put("targetFromDt", targetFromDt);
        param.put("targetThruDt", targetThruDt);
        // START 2018/10/31 K.Kojima [QC#28999,ADD]
        param.put("invTpCd", invTpCd);
        // END 2018/10/31 K.Kojima [QC#28999,ADD]
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("hasSameAddl", param);
        if (resultMapList.size() != 0) {
            return true;
        }
        return false;
    }
    // Mod End   09/05/2016 <QC#14146>

    private String addDays(String date, int numberOfDays) {
        if (!hasValue(date)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DATE, numberOfDays);
        return format.format(cal.getTime());
    }

    private BigDecimal calcFuncAmtFromDealAmt(BigDecimal dealAmt) {
        BigDecimal funcAmt = NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, this.ccyCd, this.slsDt, dealAmt);
        if (!hasValue(funcAmt)) {
            funcAmt = dealAmt;
        }
        return funcAmt;
    }

    private BigDecimal calcDealFromFunc(BigDecimal funcAmt) {
        BigDecimal dealAmt = NSXC003001Exchange.calcDealFromFunc(this.glblCmpyCd, this.ccyCd, this.slsDt, funcAmt);
        if (!hasValue(funcAmt)) {
            dealAmt = funcAmt;
        }
        return dealAmt;
    }

    private int getCcyScale() {
        int scale = 2;
        if (!hasValue(this.ccyCd)) {
            return scale;
        }
        CCYTMsg inMsg = new CCYTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.ccyCd, this.ccyCd);
        inMsg = (CCYTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null || !hasValue(inMsg.aftDeclPntDigitNum)) {
            return scale;
        }
        return inMsg.aftDeclPntDigitNum.getValueInt();
    }

    private BigDecimal setAmtScale(BigDecimal amt) {
        if (!hasValue(amt)) {
            return BigDecimal.ZERO;
        }
        return amt.setScale(this.ccyScale, BigDecimal.ROUND_HALF_UP);
    }
    private NWZC036101PMsg callTaxCalcAPIForBase(Map<String, Object> rsltSchdMap, BigDecimal funcAmt) {

        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = getSvcContrBllgForTax((BigDecimal) rsltSchdMap.get("SVC_CONTR_BLLG_PK"));
        if (svcContrBllgTMsg == null) {
            return null;
        }
        CallTaxCalcAPIForBaseBean inBean = new CallTaxCalcAPIForBaseBean();

        inBean.setGlblCmpyCd(glblCmpyCd);
        inBean.setSlsDt(slsDt);
        inBean.setXxProcMd(PROC_MODE_INVOICE);
        inBean.setInvTp(svcContrBllgTMsg.invTpCd.getValue());
        inBean.setDsAcctNum((String) rsltSchdMap.get("DS_ACCT_NUM"));
        inBean.setBaseBillToCustCd((String) rsltSchdMap.get("BASE_BILL_TO_CUST_CD"));
        inBean.setDsContrDtlTpCd((String) rsltSchdMap.get("DS_CONTR_DTL_TP_CD"));
        inBean.setSvcMachMstrPk(svcContrBllgTMsg.svcMachMstrPk.getValue());
        inBean.setDsContrPk((BigDecimal) rsltSchdMap.get("DS_CONTR_PK"));
        inBean.setNextBllgDt(svcContrBllgTMsg.baseBllgNextBllgDt.getValue());
        inBean.setDsContrBllgSchdPk(svcContrBllgTMsg.dsContrBllgSchdPk.getValue());
        inBean.setSvcPgmMdseCd((String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
        inBean.setBaseFuncAmt(funcAmt);
        // START 2017/10/16 U.Kim [QC#21584, ADD]
        inBean.setOrigSvcInvNum((String) rsltSchdMap.get("ORIG_SVC_INV_NUM"));
        // END 2017/10/16 U.Kim [QC#21584, ADD]
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        inBean.setLogOutputFlg(this.logOutputFlg);
        // END 2018/09/10 K.Kojima [QC#28107,ADD]

        return NSXC003001CallTaxCalcAPIForBase.callTaxCalcApi(inBean, this.onBatType);
    }

    private NWZC036101PMsg callTaxCalcAPIForUsg(Map<String, Object> rsltSchdMap, BigDecimal funcAmt) {

        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = getSvcContrBllgForTax((BigDecimal) rsltSchdMap.get("SVC_CONTR_BLLG_PK"));
        if (svcContrBllgTMsg == null) {
            return null;
        }
        CallTaxCalcAPIForUsageBean inBean = new CallTaxCalcAPIForUsageBean();

        inBean.setGlblCmpyCd(glblCmpyCd);
        inBean.setSlsDt(slsDt);
        inBean.setXxProcMd(PROC_MODE_INVOICE);
        inBean.setInvTp(svcContrBllgTMsg.invTpCd.getValue());
        inBean.setDsAcctNum((String) rsltSchdMap.get("DS_ACCT_NUM"));
        inBean.setBaseBillToCustCd((String) rsltSchdMap.get("BLLG_MTR_BILL_TO_CUST_CD"));
        inBean.setDsContrDtlTpCd((String) rsltSchdMap.get("DS_CONTR_DTL_TP_CD"));
        inBean.setSvcMachMstrPk(svcContrBllgTMsg.svcMachMstrPk.getValue());
        inBean.setDsContrPk((BigDecimal) rsltSchdMap.get("DS_CONTR_PK"));
        inBean.setNextBllgDt(svcContrBllgTMsg.mtrBllgNextBllgDt.getValue());
        inBean.setDsContrBllgSchdPk(svcContrBllgTMsg.dsContrBllgSchdPk.getValue());
        inBean.setSvcPgmMdseCd((String) rsltSchdMap.get("INTG_MDSE_CD"));
        // Add Start 03/25/2016 <QC#5879>
        inBean.setMdseCdForSvcAllocTp((String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
        // Add End   03/25/2016 <QC#5879>
        inBean.setBaseFuncAmt(funcAmt);
        // START 2017/10/16 U.Kim [QC#21584, ADD]
        inBean.setOrigSvcInvNum((String) rsltSchdMap.get("ORIG_SVC_INV_NUM"));
        // END 2017/10/16 U.Kim [QC#21584, ADD]
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        inBean.setLogOutputFlg(this.logOutputFlg);
        // END 2018/09/10 K.Kojima [QC#28107,ADD]

        return NSXC003001CallTaxCalcAPIForUsage.callTaxCalcApi(inBean, this.onBatType);
    }

    private NWZC036101PMsg callTaxCalcAPIForAddl(Map<String, Object> rsltSchdMap, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, SVC_CONTR_ADDL_CHRG_BLLGTMsg svcContrAddlChrgBllgTMsg, BigDecimal funcAmt) {

        CallTaxCalcAPIForAddlBean inBean = new CallTaxCalcAPIForAddlBean();

        inBean.setGlblCmpyCd(glblCmpyCd);
        inBean.setSlsDt(slsDt);
        inBean.setXxProcMd(PROC_MODE_INVOICE);
        inBean.setInvTp(svcContrBllgTMsg.invTpCd.getValue());
        inBean.setDsAcctNum((String) rsltSchdMap.get("DS_ACCT_NUM"));
        // Mod Start 03/28/2016 <QC#5879>
//        inBean.setBaseBillToCustCd((String) rsltSchdMap.get("BILL_TO_CUST_CD"));
        inBean.setDsContrDtlTpCd((String) rsltSchdMap.get("DS_CONTR_DTL_TP_CD"));
        inBean.setSvcMachMstrPk(svcContrBllgTMsg.svcMachMstrPk.getValue());
        inBean.setDsContrPk((BigDecimal) rsltSchdMap.get("DS_CONTR_PK"));
        // Mod Start 04/04/2016 <QC#6492>
        inBean.setNextBllgDt(svcContrBllgTMsg.addlChrgBllgNextBllgDt.getValue());
        // Mod End 04/04/2016 <QC#6492>
        inBean.setDsContrBllgSchdPk(svcContrBllgTMsg.dsContrBllgSchdPk.getValue());
        inBean.setSvcPgmMdseCd(svcContrAddlChrgBllgTMsg.intgMdseCd.getValue());
        // Add Start 03/25/2016 <QC#5879>
        // mod start 2016/09/27 CSA Defect#10787
        if (!hasValue(svcContrAddlChrgBllgTMsg.svcContrBaseBllgPk)) {
            inBean.setBaseBillToCustCd((String) rsltSchdMap.get("BLLG_MTR_BILL_TO_CUST_CD"));
//            inBean.setMdseCdForSvcAllocTp((String) rsltSchdMap.get("SVC_PGM_MDSE_CD"));
        } else {
            inBean.setBaseBillToCustCd((String) rsltSchdMap.get("BASE_BILL_TO_CUST_CD"));
//            inBean.setMdseCdForSvcAllocTp(null);
        }
        inBean.setMdseCdForSvcAllocTp(svcContrAddlChrgBllgTMsg.intgMdseCd.getValue());
        // mod end 2016/09/27 CSA Defect#10787

        // Add End   03/25/2016 <QC#5879>
        // Mod End   03/28/2016 <QC#5879>
        inBean.setBaseFuncAmt(funcAmt);
        // START 2017/10/16 U.Kim [QC#21584, ADD]
        inBean.setOrigSvcInvNum((String) rsltSchdMap.get("ORIG_SVC_INV_NUM"));
        // END 2017/10/16 U.Kim [QC#21584, ADD]
        // START 2018/09/10 K.Kojima [QC#28107,ADD]
        inBean.setLogOutputFlg(this.logOutputFlg);
        // END 2018/09/10 K.Kojima [QC#28107,ADD]

        return NSXC003001CallTaxCalcAPIForAddl.callTaxCalcApi(inBean, this.onBatType);
    }

    private BigDecimal getTaxFuncAmt(NWZC036101PMsg taxApiPMsg) {
        BigDecimal taxFuncAmt = BigDecimal.ZERO;
        if (taxApiPMsg.taxCalculateOutputLine.getValidCount() > 0) {

            NWZC036101_taxCalculateOutputLinePMsg linePMsg = taxApiPMsg.taxCalculateOutputLine.no(0);
            //Mod Start 03/15/2016 <QC#QC5492>
            taxFuncAmt = taxFuncAmt.add(null2Zero(linePMsg.taxAmt_01.getValue()));
            taxFuncAmt = taxFuncAmt.add(null2Zero(linePMsg.taxAmt_02.getValue()));
            taxFuncAmt = taxFuncAmt.add(null2Zero(linePMsg.taxAmt_03.getValue()));
            //Mod End   03/15/2016 <QC#QC5492>
        }
        return taxFuncAmt;
    }

    private BigDecimal getTaxRate(NWZC036101PMsg taxApiPMsg) {
        BigDecimal taxRate = BigDecimal.ZERO;
        if (taxApiPMsg.taxCalculateOutputLine.getValidCount() > 0) {

            NWZC036101_taxCalculateOutputLinePMsg linePMsg = taxApiPMsg.taxCalculateOutputLine.no(0);
            //Mod Start 03/15/2016 <QC#QC5492>
            taxRate = null2Zero(linePMsg.xxTaxCalcLineTaxPct.getValue());
            //Mod End   03/15/2016 <QC#QC5492>
        }
        return taxRate;
    }

    private SVC_CONTR_BLLGTMsg getSvcContrBllgForTax(BigDecimal svcContrBllgPk) {
        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcContrBllgPk, svcContrBllgPk);
        inMsg = (SVC_CONTR_BLLGTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        return inMsg;
    }

    private BigDecimal getContrMtrMultRate(BigDecimal dsContrBllgMtrPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        CONTR_PHYS_BLLG_MTR_RELNTMsgArray outArray = (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        BigDecimal outRate = BigDecimal.ZERO;
        if (outArray.length() > 0) {
            outRate = outArray.no(0).contrMtrMultRate.getValue();
        }
        return outRate;
    }
    // START 2016/06/30 T.Aoyagi [QC10733, ADD]
    private Map<String, BigDecimal> getFreeCopyAndRollOverCnt(Map<String, Object> prevSchd) {
        BigDecimal prevSchdPk = null;
        if (prevSchd != null) {
            prevSchdPk = (BigDecimal) prevSchd.get("DS_CONTR_BLLG_SCHD_PK");
            if (hasValue(prevSchdPk)) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("glblCmpyCd", this.glblCmpyCd);
                param.put("dsContrBllgSchdPk", prevSchdPk);
                return (Map<String, BigDecimal>) ssmBatchClient.queryObject("getFreeCopyAndRollOverCnt", param);
            }
        }
        return null;
    }
    // END 2016/06/30 T.Aoyagi [QC10733, ADD]

    // START 2018/06/05 K.Kojima [QC#21974,ADD]
    private Map<String, Object> getFreeCopyRollOverRatioUsed(BigDecimal dsContrBllgSchdPk) {
        if (!hasValue(dsContrBllgSchdPk)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (Map<String, Object>) ssmBatchClient.queryObject("getFreeCopyRollOverRatioUsed", param);
    }

    private BigDecimal getRollOverCntUsed(BigDecimal dsContrBllgSchdPk) {
        if (!hasValue(dsContrBllgSchdPk)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        param.put("invTpInvoice", INV_TP.INVOICE);
        return (BigDecimal) ssmBatchClient.queryObject("getRollOverCntUsed", param);
    }

    // END 2018/06/05 K.Kojima [QC#21974,ADD]

    // add start 2016/09/27 CSA Defect#10787
    private String getIntgMdseCdForAddlChrgTpV(String glblCmpCd, String addlChrgTpCd) {
        if (!hasValue(addlChrgTpCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpCd);
        param.put("addlChrgTpCd", addlChrgTpCd);
        return (String) this.ssmBatchClient.queryObject("getIntgMdseCdForAddlChrgTpV", param);
    }
    // add end 2016/09/27 CSA Defect#10787

    // START 2016/10/06 T.Kanasaka [QC#12274, ADD]
    private BigDecimal getTestCopyQty(Map<String, Object> rsltSchdMap, String prevMtrReadDt, String currMtrReadDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
        param.put("dsContrBllgMtrPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
        param.put("prevMtrReadDt", prevMtrReadDt);
        param.put("currMtrReadDt", currMtrReadDt);
        param.put("testCopyClsCd_In", DS_TEST_COPY_CLS.TEST_COPY_IN);
        param.put("testCopyClsCd_Out", DS_TEST_COPY_CLS.TEST_COPY_OUT);
        List<Map<String, Object>> testMtrReadList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTestCopyQty", param);

        BigDecimal testCopyQty = BigDecimal.ZERO;
        String prevFsrNum = null;
        String prevFsrVisitNum = null;
        // mod start 2017/02/15 CSA QC17489
        String prevSvcTaskNum = null;
        // mod end 2017/02/15 CSA QC17489
        BigDecimal prevSvcPhysMtrPk = BigDecimal.ONE.negate();
        // START 2017/10/06 E.Kameishi [QC#18636, ADD]
        Map<BigDecimal, BigDecimal> sumMap = new HashMap<BigDecimal, BigDecimal>();
        // END 2017/10/06 E.Kameishi [QC#18636, ADD]

        for (int i = 0; i < testMtrReadList.size(); i++) {
            Map<String, Object> testMtrReadInfo = testMtrReadList.get(i);
            String fsrNum = (String) testMtrReadInfo.get("FSR_NUM");
            String fsrVisitNum = (String) testMtrReadInfo.get("FSR_VISIT_NUM");
            // mod start 2017/02/15 CSA QC17489
            String svcTaskNum = (String) testMtrReadInfo.get("SVC_TASK_NUM");
            BigDecimal svcPhysMtrPk = (BigDecimal) testMtrReadInfo.get("SVC_PHYS_MTR_PK");
            if (!hasValue(fsrNum) || !hasValue(fsrVisitNum)) {
                if(svcTaskNum.equals(prevSvcTaskNum) && svcPhysMtrPk.compareTo(prevSvcPhysMtrPk) == 0) {
                    continue;
                }
            } else if (fsrNum.equals(prevFsrNum) && fsrVisitNum.equals(prevFsrVisitNum) && svcPhysMtrPk.compareTo(prevSvcPhysMtrPk) == 0) {
                continue;
            }
            // mod end 2017/02/15 CSA QC17489

            String testCopyInOutCd = (String) testMtrReadInfo.get("DS_TEST_COPY_CLS_CD");
            if (DS_TEST_COPY_CLS.TEST_COPY_OUT.equals(testCopyInOutCd) && i < testMtrReadList.size() - 1) {
                String nextTestCopyInOutCd = (String) (testMtrReadList.get(i + 1)).get("DS_TEST_COPY_CLS_CD");
                if (DS_TEST_COPY_CLS.TEST_COPY_IN.equals(nextTestCopyInOutCd)) {
                    // START 2017/10/25 K.Kojima [QC#21562,ADD]
                    if (!checkTestCopyDtAndStartReadDt((BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"), (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"), (BigDecimal) testMtrReadList.get(i + 1).get("SVC_PHYS_MTR_PK"),
                            (String) testMtrReadInfo.get("MTR_READ_DT"), (BigDecimal) testMtrReadList.get(i + 1).get("READ_MTR_CNT"))) {
                        // 2022/04/07 QC#59159 Add Statrt
                        if (!sumMap.containsKey(svcPhysMtrPk)) {
                            sumMap.put(svcPhysMtrPk,new BigDecimal("0"));
                        }
                        // 2022/04/07 QC#59159 Add End
                        continue;
                    }
                    // END 2017/10/25 K.Kojima [QC#21562,ADD]
                    BigDecimal testMtrCnt = (BigDecimal) testMtrReadInfo.get("TEST_MTR_CNT");
                    BigDecimal mtrMultRate = (BigDecimal) testMtrReadInfo.get("CONTR_MTR_MULT_RATE");
                    BigDecimal testBllgMtrCnt = testMtrCnt.multiply(mtrMultRate).setScale(0, BigDecimal.ROUND_HALF_UP);
                    // START 2017/10/06 E.Kameishi [QC#18636, ADD]
                    if (sumMap.containsKey(svcPhysMtrPk)) {
                        BigDecimal testPhysCopyCnt = sumMap.get(svcPhysMtrPk).add(testMtrCnt);
                        sumMap.put(svcPhysMtrPk, testPhysCopyCnt);
                    } else {
                        sumMap.put(svcPhysMtrPk, testMtrCnt);
                    }
                    // END 2017/10/06 E.Kameishi [QC#18636, ADD]
                    testCopyQty = testCopyQty.add(testBllgMtrCnt);
                    prevFsrNum = fsrNum;
                    prevFsrVisitNum = fsrVisitNum;
                    prevSvcPhysMtrPk = svcPhysMtrPk;
                    // mod start 2017/02/15 CSA QC17489
                    prevSvcTaskNum = svcTaskNum;
                    // mod end 2017/02/15 CSA QC17489
                }
            }
        }
        // START 2017/10/06 E.Kameishi [QC#18636, ADD]
        insertBllgSchdTestMtrSmry(rsltSchdMap, sumMap);
        // END 2017/10/06 E.Kameishi [QC#18636, ADD]

        return testCopyQty;
    }
    // END 2016/10/06 T.Kanasaka [QC#12274, ADD]
    //START 2017/09/12 K.Kasai [QC#15134,ADD]

    private BigDecimal getAdjBllgMtrCnt(BigDecimal prevPhysMtrReadGrpSq, BigDecimal currPhysMtrReadGrpSq, BigDecimal dsContrBllgSchdPk, BigDecimal dsContrDtlPk, CONTR_PHYS_BLLG_MTR_RELNTMsgArray contrPhysBllgMtrRelnArray, BigDecimal prevBllgMtrCnt, BigDecimal currBllgMtrCnt) {

        BigDecimal svcPhysMtrPk = BigDecimal.ZERO;
        BigDecimal rollOverExchCnt = BigDecimal.ZERO;
        BigDecimal sumAdjBllgMtrCnt = BigDecimal.ZERO;
        BigDecimal contrMtrMultRate = BigDecimal.ZERO;
        BigDecimal adjBllgMtrCnt = BigDecimal.ZERO;
        SVC_PHYS_MTR_READTMsg prevTMsg = new SVC_PHYS_MTR_READTMsg();
        SVC_PHYS_MTR_READTMsg currTMsg = new SVC_PHYS_MTR_READTMsg();
        CONTR_PHYS_BLLG_MTR_RELNTMsg bllgMtrInfoTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        SvcPhysMtrReadInfoBean prevSvcPhysMtrReadInfoBean = new SvcPhysMtrReadInfoBean();
        SvcPhysMtrReadInfoBean currSvcPhysMtrReadInfoBean = new SvcPhysMtrReadInfoBean();
        NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();

        // process per SVC_PHYS_MTR
        for (int i = 0; i < contrPhysBllgMtrRelnArray.getValidCount(); i++) {

            svcPhysMtrPk = contrPhysBllgMtrRelnArray.no(i).svcPhysMtrPk.getValue();
            // get previous SVC_PHYS_MTR_READ Info using SVC_PHYS_MTR_READ_GRP_SQ, SVC_PHYS_MTR_PK
            prevTMsg = getSvcPhysMtrReadByGrpSqPhysMtrPk(svcPhysMtrPk, prevPhysMtrReadGrpSq);

            // get Current SVC_PHYS_MTR_READ Info using SVC_PHYS_MTR_READ_GRP_SQ
            currTMsg = getSvcPhysMtrReadByGrpSqPhysMtrPk(svcPhysMtrPk, currPhysMtrReadGrpSq);

            // get read meter count between prev and curr considering Rollover/Exchange (call NSXC003001MtrEst.getRollOverExchCnt)
            prevSvcPhysMtrReadInfoBean = setMtrReadBean(prevTMsg, prevBllgMtrCnt);
            currSvcPhysMtrReadInfoBean = setMtrReadBean(currTMsg, currBllgMtrCnt);
            rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCnt(this.glblCmpyCd, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean);

            // START 2018/06/07 K.Kitachi [QC#20750, ADD]
            updateBllgSchdTestMtrSmry(dsContrBllgSchdPk, svcPhysMtrPk, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean, rollOverExchCnt, contrPhysBllgMtrRelnArray.no(i).contrMtrMultRate.getValue());
            // END 2018/06/07 K.Kitachi [QC#20750, ADD]

            // if meter is NOT set rollover/exchange, skip process
            if (BigDecimal.ZERO.compareTo(rollOverExchCnt) == 0) {
                continue;
            }

            // insert CALC_MTR_SCHD_RELN Info
            List<Map<String, Object>> mtrRolloverExchList = nsxc003001MtrEst.getRollOverExchList(this.glblCmpyCd, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean);
            for (Map<String, Object> mtrRolloverExchMap : mtrRolloverExchList) {
                insertCalcMtrSchdReln(dsContrBllgSchdPk, mtrRolloverExchMap);
            }

            // get CONTR_MTR_MULT_RATE in CONTR_PHYS_BLLG_MTR_RELN
            bllgMtrInfoTMsg = getContrPhysBllgMtrRelnByPhysMttrPk(dsContrDtlPk, currTMsg.svcPhysMtrPk.getValue());
            contrMtrMultRate = bllgMtrInfoTMsg.contrMtrMultRate.getValue();
            adjBllgMtrCnt = rollOverExchCnt.multiply(contrMtrMultRate).setScale(BigDecimal.ROUND_HALF_UP);
            sumAdjBllgMtrCnt = sumAdjBllgMtrCnt.add(adjBllgMtrCnt);
        }

        return sumAdjBllgMtrCnt;
    }

    // mod start 2019/08/23 QC#52934
    //private BigDecimal getAdjBllgMtrCntForCrRebil(BigDecimal currPhysMtrReadGrpSq, BigDecimal prevPhysMtrReadGrpSq, BigDecimal svcPhysMtrPk, BigDecimal startCnt, BigDecimal endCnt, BigDecimal mtrMultRate, BigDecimal dsContrBllgSchdPk) {
    private BigDecimal getAdjBllgMtrCntForCrRebil(BigDecimal currPhysMtrReadGrpSq, BigDecimal prevPhysMtrReadGrpSq, BigDecimal svcPhysMtrPk, BigDecimal startCnt, BigDecimal endCnt, BigDecimal mtrMultRate, BigDecimal dsContrBllgSchdPk, BigDecimal exclSvcPhysMtrReadPk) {
    // mod end 2019/08/23 QC#52934

        // get previous SVC_PHYS_MTR_READ Info using SVC_PHYS_MTR_READ_GRP_SQ, SVC_PHYS_MTR_PK
        SVC_PHYS_MTR_READTMsg prevTMsg = getSvcPhysMtrReadByGrpSqPhysMtrPk(svcPhysMtrPk, prevPhysMtrReadGrpSq);

        // get Current SVC_PHYS_MTR_READ Info using SVC_PHYS_MTR_READ_GRP_SQ
        SVC_PHYS_MTR_READTMsg currTMsg = getSvcPhysMtrReadByGrpSqPhysMtrPk(svcPhysMtrPk, currPhysMtrReadGrpSq);

        // get read meter count between prev and curr considering Rollover/Exchange (call NSXC003001MtrEst.getRollOverExchCnt)
        SvcPhysMtrReadInfoBean prevSvcPhysMtrReadInfoBean = setMtrReadBean(prevTMsg, startCnt);
        SvcPhysMtrReadInfoBean currSvcPhysMtrReadInfoBean = setMtrReadBean(currTMsg, endCnt);
        NSXC003001MtrCntTwoPntEst nsxc003001MtrEst = new NSXC003001MtrCntTwoPntEst();
        // mod start 2019/08/23 QC#52934
        //BigDecimal rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCnt(this.glblCmpyCd, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean);
        BigDecimal rollOverExchCnt = nsxc003001MtrEst.getRollOverExchCntForCrRebil(this.glblCmpyCd, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean, exclSvcPhysMtrReadPk);
        // mod end 2019/08/23 QC#52934

        // START 2018/06/07 K.Kitachi [QC#20750, ADD]
        updateBllgSchdTestMtrSmry(dsContrBllgSchdPk, svcPhysMtrPk, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean, rollOverExchCnt, mtrMultRate);
        // END 2018/06/07 K.Kitachi [QC#20750, ADD]

        // if meter is NOT set rollover/exchange, skip process
        if (BigDecimal.ZERO.compareTo(rollOverExchCnt) == 0) {
            return BigDecimal.ZERO;
        }

        // insert CALC_MTR_SCHD_RELN Info
        // mod start 2019/08/23 QC#52934
        //List<Map<String, Object>> mtrRolloverExchList = nsxc003001MtrEst.getRollOverExchList(this.glblCmpyCd, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean);
        List<Map<String, Object>> mtrRolloverExchList = nsxc003001MtrEst.getRollOverExchListForCrRebil(this.glblCmpyCd, prevSvcPhysMtrReadInfoBean, currSvcPhysMtrReadInfoBean, exclSvcPhysMtrReadPk);
        // mod end 2019/08/23 QC#52934
        for (Map<String, Object> mtrRolloverExchMap : mtrRolloverExchList) {
            insertCalcMtrSchdReln(dsContrBllgSchdPk, mtrRolloverExchMap);
        }

        // get CONTR_MTR_MULT_RATE in CONTR_PHYS_BLLG_MTR_RELN
        BigDecimal adjBllgMtrCnt = rollOverExchCnt.multiply(mtrMultRate).setScale(BigDecimal.ROUND_HALF_UP);
        return adjBllgMtrCnt;
    }

    private void insertCalcMtrSchdReln(BigDecimal dsContrBllgSchdPk, Map<String, Object> mtrRolloverExchMap) {

        CALC_MTR_SCHD_RELNTMsg inTMsg = new CALC_MTR_SCHD_RELNTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTMsg.svcPhysMtrReadPk, (BigDecimal)mtrRolloverExchMap.get("SVC_PHYS_MTR_READ_PK"));
        setValue(inTMsg.cntrResetTpCd, (String)mtrRolloverExchMap.get("CNTR_RESET_TP_CD"));
        // insert CALC_MTR_SCHD_RELN
        S21ApiTBLAccessor.create(inTMsg);
    }

    private SvcPhysMtrReadInfoBean setMtrReadBean(SVC_PHYS_MTR_READTMsg inTMsg, BigDecimal bllgMtrCnt) {
        SvcPhysMtrReadInfoBean mtrReadBean = new SvcPhysMtrReadInfoBean();
        mtrReadBean.setSvcPhysMtrReadPk(inTMsg.svcPhysMtrReadPk.getValue());
        mtrReadBean.setSvcPhysMtrPk(inTMsg.svcPhysMtrPk.getValue());
        mtrReadBean.setMtrReadDt(inTMsg.mtrReadDt.getValue());
        if (!hasValue(bllgMtrCnt)) {
            mtrReadBean.setReadMtrCnt(inTMsg.readMtrCnt.getValue());
        } else {
            mtrReadBean.setReadMtrCnt(bllgMtrCnt);
        }
        return mtrReadBean;
    }

    private SVC_PHYS_MTR_READTMsg getSvcPhysMtrReadByGrpSqPhysMtrPk(BigDecimal svcPhysMtrPk, BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0);
    }

    private CONTR_PHYS_BLLG_MTR_RELNTMsg getContrPhysBllgMtrRelnByPhysMttrPk(BigDecimal dsContrDtlPk, BigDecimal svcPhysMtrPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        CONTR_PHYS_BLLG_MTR_RELNTMsgArray outArray = (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0);
    }

    // START 2017/10/06 E.Kameishi [QC#18636, ADD]
    private void insertBllgSchdTestMtrSmry(Map<String, Object> rsltSchdMap, Map<BigDecimal, BigDecimal> sumMap) {
        // When Test Count is Zero.
        if (sumMap.size() == 0) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("dsContrDtlPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_DTL_PK"));
            param.put("dsContrBllgMtrPk", (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_MTR_PK"));
            List<Map<String, Object>> svcPhysMtrPkList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcPhysMtrPk", param);

            for (int i = 0; i < svcPhysMtrPkList.size(); i++) {
                Map<String, Object> svcPhysMtrPkInfo = svcPhysMtrPkList.get(i);
                BLLG_SCHD_TEST_MTR_SMRYTMsg inTmsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
                setValue(inTmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inTmsg.dsContrBllgSchdPk, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
                setValue(inTmsg.svcPhysMtrPk, (BigDecimal) svcPhysMtrPkInfo.get("SVC_PHYS_MTR_PK"));
                BLLG_SCHD_TEST_MTR_SMRYTMsg outTmsg = (BLLG_SCHD_TEST_MTR_SMRYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTmsg);

                if (outTmsg != null) {
                    setValue(outTmsg.testMtrCnt, BigDecimal.ZERO);
                    S21ApiTBLAccessor.update(outTmsg);
                } else {
                    setValue(inTmsg.testMtrCnt, BigDecimal.ZERO);
                    S21ApiTBLAccessor.create(inTmsg);
                }
            }
        } else {
            // Whern Test Count is not Zero.
            for (BigDecimal svcPhysMtrPk : sumMap.keySet()) {
                BLLG_SCHD_TEST_MTR_SMRYTMsg inTmsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
                setValue(inTmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(inTmsg.dsContrBllgSchdPk, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
                setValue(inTmsg.svcPhysMtrPk, svcPhysMtrPk);
                BLLG_SCHD_TEST_MTR_SMRYTMsg outTmsg = (BLLG_SCHD_TEST_MTR_SMRYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTmsg);

                if (outTmsg != null) {
                    setValue(outTmsg.testMtrCnt, sumMap.get(svcPhysMtrPk));
                    // add start 2019/09/04 QC#53124
                    if (this.isExclTestCopy) {
                        setValue(outTmsg.testMtrCnt, BigDecimal.ZERO);
                    }
                    // add end 2019/09/04 QC#53124
                    S21ApiTBLAccessor.update(outTmsg);
                } else {
                    setValue(inTmsg.testMtrCnt, sumMap.get(svcPhysMtrPk));
                    // add start 2019/09/04 QC#53124
                    if (this.isExclTestCopy) {
                        setValue(inTmsg.testMtrCnt, BigDecimal.ZERO);
                    }
                    // add end 2019/09/04 QC#53124
                    S21ApiTBLAccessor.create(inTmsg);
                }
            }
        }
    }

    private BigDecimal insertBllgSchdTestMtrSmryForRebill(Map<String, Object> rsltSchdMap, Map<String, Object> rebilMtrMap) {
            BLLG_SCHD_TEST_MTR_SMRYTMsg inTmsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
            setValue(inTmsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inTmsg.dsContrBllgSchdPk, (BigDecimal) rsltSchdMap.get("DS_CONTR_BLLG_SCHD_PK"));
            setValue(inTmsg.svcPhysMtrPk, (BigDecimal) rebilMtrMap.get("SVC_PHYS_MTR_PK"));
            BigDecimal testMtrCnt = (BigDecimal) rebilMtrMap.get("NEW_TEST_MTR_CNT");
            if (testMtrCnt == null) {
                testMtrCnt = (BigDecimal) rebilMtrMap.get("OLD_TEST_MTR_CNT");
            }
            if (!hasValue(testMtrCnt)) {
                testMtrCnt = BigDecimal.ZERO;
            }
            BLLG_SCHD_TEST_MTR_SMRYTMsg outTmsg = (BLLG_SCHD_TEST_MTR_SMRYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTmsg);

            if (outTmsg != null) {
                setValue(outTmsg.testMtrCnt, testMtrCnt);
                S21ApiTBLAccessor.update(outTmsg);
            } else {
                setValue(inTmsg.testMtrCnt, testMtrCnt);
                S21ApiTBLAccessor.create(inTmsg);
            }
            return testMtrCnt;
    }
    // END 2017/10/06 E.Kameishi [QC#18636, ADD]

    // START 2017/10/25 K.Kojima [QC#21562,ADD]
    private boolean checkTestCopyDtAndStartReadDt(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal readMtrCnt) {
        List<Map<String, Object>> startMtrReadList = getStartCopyQty(dsContrDtlPk, dsContrBllgMtrPk, mtrReadDt);
        boolean isCalcTrgt = true;
        if (startMtrReadList != null && startMtrReadList.size() > 0) {
            for (Map<String, Object> startMtrReadInfo : startMtrReadList) {
                if (svcPhysMtrPk.compareTo((BigDecimal) startMtrReadInfo.get("SVC_PHYS_MTR_PK")) == 0) {
                    if (readMtrCnt.compareTo((BigDecimal) startMtrReadInfo.get("READ_MTR_CNT")) < 0) {
                        isCalcTrgt = false;
                    }
                    break;
                }
            }
        }
        return isCalcTrgt;
    }

    private List<Map<String, Object>> getStartCopyQty(BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String mtrReadDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        param.put("mtrReadDt", mtrReadDt);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getStartCopyQty", param);
    }
    // END 2017/10/25 K.Kojima [QC#21562,ADD]

    // START 2018/03/20 K.Kojima [QC#23623,ADD]
    private BigDecimal getBllgMinCnt(Map<String, Object> rsltSchdMap, BigDecimal bllgMinCnt) {
        return getBllgMin(rsltSchdMap, bllgMinCnt, 0);
    }

    private BigDecimal getBllgMinAmtRate(Map<String, Object> rsltSchdMap, BigDecimal bllgMinAmtRate) {
        return getBllgMin(rsltSchdMap, bllgMinAmtRate, this.ccyScale);
    }

    private BigDecimal getBllgMin(Map<String, Object> rsltSchdMap, BigDecimal bllgMin, int scale) {
        BigDecimal prrtDivRate = (BigDecimal) rsltSchdMap.get("PRRT_DIV_RATE");
        String perBllgCycleCd = (String) rsltSchdMap.get("PER_BLLG_CYCLE_CD");
        BigDecimal perSchdNum = (BigDecimal) rsltSchdMap.get("PER_SCHD_NUM");

        if (!ZYPCommonFunc.hasValue(prrtDivRate) || !ZYPCommonFunc.hasValue(perBllgCycleCd) || !ZYPCommonFunc.hasValue(perSchdNum)) {
            return bllgMin;
        }
        if (!BLLG_CYCLE.DAILY.equals(perBllgCycleCd)) {
            return bllgMin;
        }

        return bllgMin.multiply(perSchdNum).divide(DAYS_OF_YEAR.divide(prrtDivRate, 2, BigDecimal.ROUND_HALF_UP), scale, BigDecimal.ROUND_HALF_UP);
    }
    // END 2018/03/20 K.Kojima [QC#23623,ADD]

    // START 2018/04/11 M.Naito [QC#23378,ADD]
    private BigDecimal getTotPrcAllocAmt(List<GetDefCoaTrxCdForOutListInfoBean> allocList, int scale) {
        BigDecimal totPrcAllocAmt = null;
        BigDecimal getTotPrcAllocAmt = BigDecimal.ZERO;
        if (allocList == null || allocList.size() == 0) {
            return totPrcAllocAmt;
        }

        for (int i = 0; i < allocList.size(); i++) {
            if (hasValue(allocList.get(i).getPrcAllocAmt())) {
                getTotPrcAllocAmt = getTotPrcAllocAmt.add(allocList.get(i).getPrcAllocAmt());
            }
        }
        if (BigDecimal.ZERO.compareTo(getTotPrcAllocAmt) != 0) {
            totPrcAllocAmt = getTotPrcAllocAmt.setScale(scale, BigDecimal.ROUND_HALF_UP);
        }
        return totPrcAllocAmt;
    }
    // END 2018/04/11 M.Naito [QC#23378,ADD]

    // START 2018/06/07 K.Kitachi [QC#20750, ADD]
    private void updateBllgSchdTestMtrSmry(BigDecimal dsContrBllgSchdPk, BigDecimal svcPhysMtrPk, SvcPhysMtrReadInfoBean prevMtrReadBean, SvcPhysMtrReadInfoBean currMtrReadBean, BigDecimal rollOverExchCnt, BigDecimal contrMtrMultRate) {
        BLLG_SCHD_TEST_MTR_SMRYTMsg inTmsg = new BLLG_SCHD_TEST_MTR_SMRYTMsg();
        setValue(inTmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTmsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        setValue(inTmsg.svcPhysMtrPk, svcPhysMtrPk);
        BLLG_SCHD_TEST_MTR_SMRYTMsg outTmsg = (BLLG_SCHD_TEST_MTR_SMRYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTmsg);
        if (outTmsg == null) {
            return;
        }
        if (prevMtrReadBean != null) {
            setValue(outTmsg.startReadMtrCnt, prevMtrReadBean.getReadMtrCnt());
        }
        if (currMtrReadBean != null) {
            setValue(outTmsg.endReadMtrCnt, currMtrReadBean.getReadMtrCnt());
        }
        if (prevMtrReadBean != null && currMtrReadBean != null) {
            // mod start 2019/09/04 QC#53124
            //BigDecimal totCopyQty = currMtrReadBean.getReadMtrCnt().subtract(prevMtrReadBean.getReadMtrCnt()).subtract(outTmsg.testMtrCnt.getValue());
            BigDecimal totCopyQty = BigDecimal.ZERO;
            if (this.isExclTestCopy) {
            	totCopyQty = currMtrReadBean.getReadMtrCnt().subtract(prevMtrReadBean.getReadMtrCnt()).subtract(BigDecimal.ZERO);
            } else {
            	totCopyQty = currMtrReadBean.getReadMtrCnt().subtract(prevMtrReadBean.getReadMtrCnt()).subtract(outTmsg.testMtrCnt.getValue());
            }
            // mod end 2019/09/04 QC#53124

            if (rollOverExchCnt.compareTo(BigDecimal.ZERO) > 0) {
                totCopyQty = totCopyQty.add(rollOverExchCnt);
            }
            setValue(outTmsg.totCopyQty, totCopyQty);
        }
        setValue(outTmsg.contrMtrMultRate, contrMtrMultRate);
        S21ApiTBLAccessor.update(outTmsg);
    }
    // END 2018/06/07 K.Kitachi [QC#20750, ADD]


    // START 2018/09/07 K.Kojima [QC#28107,ADD]
    private void s21InfoLogOutputPrintln(String message) {
        if (this.logOutputFlg != null && this.logOutputFlg.equals(ZYPConstant.FLG_ON_Y)) {
            S21InfoLogOutput.println("[Billing Calculation Log]" + message);
        }
    }
    // END 2018/09/07 K.Kojima [QC#28107,ADD]
    // START 2019/01/17 E.Kameishi [QC#29083,ADD]
    private boolean checkFromToDt(String targetDt, String FromDt, String ToDt) {
        if (ZYPDateUtil.compare(targetDt, FromDt) >= 0 && ZYPDateUtil.compare(targetDt, ToDt) <= 0) {
            return true;
        }
        return false;
    }

    private boolean checkCumCopyReset(BigDecimal cumCopyFreq, String cumCopyStDt, String mtrBllgFromDt, String mtrBllgThruDt) {
        int cumCopyCnt = NSXC001001CalcDate.diffMonths(cumCopyStDt, mtrBllgThruDt) / cumCopyFreq.intValue();
        String cumCopyResetDt = NSXC001001CalcDate.addMonths(cumCopyStDt, cumCopyCnt * cumCopyFreq.intValue());

        if (checkFromToDt(cumCopyResetDt, mtrBllgFromDt, mtrBllgThruDt)) {
            return true;
        }
        return false;
    }

    private boolean checkCumCopyPerDays(SVC_CONTR_MTR_BLLGTMsg svcContrMtrBllgTmsg, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, String cumCopyStDt) {
        DS_CONTR_DTLTMsg dsContrDtlTmsg = new DS_CONTR_DTLTMsg();
        setValue(dsContrDtlTmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsContrDtlTmsg.dsContrDtlPk, svcContrMtrBllgTmsg.dsContrDtlPk);
        dsContrDtlTmsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(dsContrDtlTmsg);

        if (ZYPDateUtil.compare(svcContrMtrBllgTmsg.mtrBllgFromDt.getValue(), dsContrDtlTmsg.contrEffFromDt.getValue()) == 0) {
            DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTmsg = new DS_CONTR_BLLG_SCHDTMsg();
            setValue(dsContrBllgSchdTmsg.glblCmpyCd, this.glblCmpyCd);
            setValue(dsContrBllgSchdTmsg.dsContrBllgSchdPk, svcContrBllgTMsg.dsContrBllgSchdPk);
            dsContrBllgSchdTmsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(dsContrBllgSchdTmsg);

            if (ZYPConstant.FLG_ON_Y.equals(dsContrBllgSchdTmsg.bllgSchdPrrtFlg.getValue())) {
                if (ZYPDateUtil.compare(addDays(svcContrMtrBllgTmsg.mtrBllgThruDt.getValue(), 1), cumCopyStDt) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    // END 2019/01/17 E.Kameishi [QC#29083,ADD]
}
