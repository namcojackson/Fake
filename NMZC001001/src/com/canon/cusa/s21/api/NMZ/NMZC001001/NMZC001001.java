/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC001001;

import static com.canon.cusa.s21.api.NMZ.NMZC001001.constant.NMZC001001Constant.*;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_SUGGEST;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDPItem;
import parts.common.EZDPStringItem;
import parts.common.EZDStringUtil;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_LOCTMsg;
import business.db.ALT_PAYERTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.CLT_CUST_TPTMsg;
import business.db.CMPYTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_CHTMsg;
import business.db.CR_CHK_REQ_TPTMsg;
import business.db.CR_RISK_CLSTMsg;
import business.db.CTRYTMsg;
import business.db.CUST_CR_PRFLTMsg;
import business.db.CUST_CR_RTGTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DPL_STSTMsg;
import business.db.DS_ACCT_CLSTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_ACCT_DLRTMsg;
import business.db.DS_ACCT_INAC_RSNTMsg;
import business.db.DS_ACCT_PROSTMsg;
import business.db.DS_CLT_ACCT_STSTMsg;
import business.db.DS_CUST_SPCL_MSGTMsg;
import business.db.DS_CUST_TAX_CALCTMsg;
import business.db.DS_TAX_GRP_EXEMTMsg;
import business.db.DS_TAX_PRNT_TPTMsg;
import business.db.DS_XREF_ACCTTMsg;
import business.db.DS_XREF_ACCT_TPTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.INV_RCPNTTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.LOC_USGTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.PRIN_CUSTTMsg;
import business.db.PROS_PTY_LOC_WRKTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.RGTN_STSTMsg;
import business.db.RTL_WHTMsg;
import business.db.RTL_WHTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.db.WHTMsg;
import business.db.WHTMsgArray;
import business.parts.NFZC202001PMsg;
import business.parts.NMZC001001PMsg;
import business.parts.NMZC001002PMsg;
import business.parts.NMZC001002_xxMsgIdListPMsg;
import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CHK_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_EFF_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DPL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_CHNG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_FUFL_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.USR_DLR_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.usa.s21.integration.api.properties.PropertyException;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.CallPartyScreeningServiceFaultDataExp;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.CallPartyScreeningServiceFaultResourceExp;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.CallPartyScreeningServiceFaultUserExp;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Administration;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Country;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.OutputFormat;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Parameter;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Parameters;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Party;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Request;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.ServiceRequest;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.ShipFromCountry;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.ShipToCountry;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Transaction;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.TransactionLine;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service.Svc;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.DeniedParty;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Error;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Response;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Service;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.wrapper.S21ChileKewillPartyScreeningServiceProxy;

/**
 * <pre>
 * Customer Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/02   Fujitsu         N.Sugiura       Create          N/A
 * 2017/05/09   Fujitsu         M.Yamada        Update          QC#18498
 * 2017/06/22   Hitachi         E.Kameishi      Update          QC#18983
 * 2017/07/06   Fujitsu         H.ikeda         Update          QC#19592
 * 2017/07/14   Fujitsu         H.Sugawara      Update          QC#19880
 * 2017/09/25   Hitachi         J.Kim           Update          QC#21202
 * 2017/12/04   Fujitsu         Hd.Sugawara     Update          QC#22115
 * 2018/02/23   Fujitsu         Hd.Sugawara     Update          QC#22568
 * 2018/04/19   Fujitsu         H.Ikeda         Update          QC#23882
 * 2018/11/28   Fujitsu         C.Hara          Update          QC#29134
 * 2018/12/19   Fujitsu         T.Noguchi       Update          QC#29134
 * 2019/04/05   Fujitsu         T.Noguchi       Update          QC#31030
 * 2019/04/16   Fujitsu         Hd.Sugawara     Update          QC#31114
 * 2019/10/29   Fujitsu         H.Ikeda         Update          QC#54311
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 */
public class NMZC001001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Location Information Map */
    private Map<String, LocInfoBean> locInfoMap;

    /** Existing PRIN_CUST.PRIN_CUST_PK */
    private BigDecimal exstPrinCustPk = null;

    /** DS_ACCT_ACTV_CUST_FLG Before Update */
    private String dsAcctActvCustFlgBefUpd = null;

    /** old AcctCoaChCd */
    private String oldAcctCoaChCd = null;

    /**
     * Constructor
     */
    public NMZC001001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Customer Update API
     * </pre>
     * @param param Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(final NMZC001001PMsg param, final ONBATCH_TYPE onBatchType) {

        /** locInfo Map */
        locInfoMap = new HashMap<String, LocInfoBean>();

        // Checking Input value
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (!checkInput(msgMap)) {
            msgMap.flush();
            return;
        }

        // main
        doProcess(msgMap);
        // end
        msgMap.flush();

    }

    /**
     * <pre>
     * Customer Update API (List)
     * </pre>
     * @param params Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(List<NMZC001001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NMZC001001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * Validation check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkValidation(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC001001PMsg param = (NMZC001001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        // Classification Code
        if (ZYPCommonFunc.hasValue(param.dsAcctClsCd.getValue())) {
            if (!chkDsAcctCls(param.dsAcctClsCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0277E);
                returnValue = false;
            }
        }
        // Direct Sales Account Dealer Code
        if (ZYPCommonFunc.hasValue(param.dsAcctDlrCd.getValue())) {
            if (!chkDsAcctDlr(param.dsAcctDlrCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0278E);
                returnValue = false;
            }
        }
        // Registration Status Code
        if (ZYPCommonFunc.hasValue(param.rgtnStsCd.getValue())) {
            if (!chkRgtn(param.rgtnStsCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0279E);
                returnValue = false;
            }
        }
        // GL Intercompany code
        if (ZYPCommonFunc.hasValue(param.coaAfflCd.getValue())) {
            if (!chkCoaAffl(param.coaAfflCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0280E);
                returnValue = false;
            }
        }
        // Direct Sales Customer Collection Account Status Code
        if (ZYPCommonFunc.hasValue(param.dsCltAcctStsCd.getValue())) {
            if (!chkDsCltAcctSts(param.dsCltAcctStsCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0281E);
                returnValue = false;
            }
        }
        // Collection Customer Type Code
        if (ZYPCommonFunc.hasValue(param.cltCustTpCd.getValue())) {
            if (!chkCltCustTp(param.cltCustTpCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0282E);
                returnValue = false;
            }
        }
        // Tax Print Type Code
        if (ZYPCommonFunc.hasValue(param.dsTaxPrntTpCd.getValue())) {
            if (!chkDsTaxPrntTp(param.dsTaxPrntTpCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0283E);
                returnValue = false;
            }
        }
        // Direct Sales Customer Tax Calculation Code
        if (ZYPCommonFunc.hasValue(param.dsCustTaxCalcCd.getValue())) {
            if (!chkDsCustTaxCalc(param.dsCustTaxCalcCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0284E);
                returnValue = false;
            }
        }
        // Tax Group Exemption Code
        if (ZYPCommonFunc.hasValue(param.dsTaxGrpExemCd.getValue())) {
            if (!chkDsTaxGrpExem(param.dsTaxGrpExemCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0285E);
                returnValue = false;
            }
        }
        // Chart of Account Channel Code
        if (ZYPCommonFunc.hasValue(param.coaChCd.getValue())) {
            if (!chkCoaCh(param.coaChCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0286E);
                returnValue = false;
            }
        }
        // Direct Sales Account Inactive Reason Code
        if (ZYPCommonFunc.hasValue(param.dsAcctInacRsnCd.getValue())) {
            if (!chkDsAcctInacRsn(param.dsAcctInacRsnCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0287E);
                returnValue = false;
            }
        }
        // Credit Rating
        if (ZYPCommonFunc.hasValue(param.custCrRtgCd.getValue())) {
            if (!chkCustCrRtg(param.custCrRtgCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0288E);
                returnValue = false;
            }
        }
        // Grace period(Days)
        if (ZYPCommonFunc.hasValue(param.crRiskClsCd.getValue())) {
            if (!chkCrRiskClsCd(param.crRiskClsCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0289E);
                returnValue = false;
            }
        }
        // Payment Term
        if (ZYPCommonFunc.hasValue(param.pmtTermCashDiscCd.getValue())) {
            if (!chkPmtTermCashDisc(param.pmtTermCashDiscCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0290E);
                returnValue = false;
            }
        }
        // Credit Hold
        if (ZYPCommonFunc.hasValue(param.crChkReqTpCd.getValue())) {
            if (!chkCrChkReqTp(param.crChkReqTpCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMZM0330E);
                returnValue = false;
            }
        }
        for (int i = 0; i < param.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = param.NMZC001002PMsg.no(i);
            S21ApiMessageMap lineMap = new S21ApiMessageMap(linePrm);

            // County Name
            if (ZYPCommonFunc.hasValue(linePrm.cntyNm.getValue())) {
                if (!chkCnty(linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0291E);
                    returnValue = false;
                }
            }
            // State Code
            if (ZYPCommonFunc.hasValue(linePrm.stCd.getValue())) {
                if (ZYPCommonFunc.hasValue(linePrm.ctryCd)) {
                    if (!chkSt(linePrm.stCd.getValue(), linePrm.ctryCd.getValue(), glblCmpyCd)) {
                        msgMap.addXxMsgId(NMZM0292E);
                        returnValue = false;
                    }
                } else {
                    if (!chkSt(linePrm.stCd.getValue(), glblCmpyCd)) {
                        msgMap.addXxMsgId(NMZM0292E);
                        returnValue = false;
                    }
                }
            } else {
                if (CTRY.UNITED_STATES_OF_AMERICA.equals(linePrm.ctryCd.getValue())) {
                    msgMap.addXxMsgId(NMZM0215E);
                    returnValue = false;
                }
            }
            // Country Code
            if (ZYPCommonFunc.hasValue(linePrm.ctryCd.getValue())) {
                if (!chkCtry(linePrm.ctryCd.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0293E);
                    returnValue = false;
                }
            }

            if (!ZYPCommonFunc.hasValue(linePrm.locNum)) {
                String date = param.slsDt.getValue();
                date = ZYPDateUtil.addDays(date, -1);
                // Location Effective From Date
                if (ZYPCommonFunc.hasValue(linePrm.effFromDt.getValue())) {
                    if (ZYPDateUtil.compare(linePrm.effFromDt.getValue(), param.slsDt.getValue()) < 0) {
                        // Mod Start 2018/02/23 QC#22568
                        //lineMap.addXxMsgId(NMZM0060E);
                        lineMap.addXxMsgId(NMZM0368E);
                        // Mod End 2018/02/23 QC#22568
                        returnValue = false;
                    }
                }
                // Location Effective Through Date
                if (ZYPCommonFunc.hasValue(linePrm.effThruDt.getValue())) {

                    if (ZYPDateUtil.compare(linePrm.effThruDt.getValue(), date) < 0) {
                        // Mod Start 2018/02/23 QC#22568
                        //lineMap.addXxMsgId(NMZM0060E);
                        lineMap.addXxMsgId(NMZM0369E);
                        // Mod End 2018/02/23 QC#22568
                        returnValue = false;
                    }
                }
                // Bill to cust Effective From Date
                if (ZYPCommonFunc.hasValue(linePrm.effFromDt_B.getValue())) {
                    if (ZYPDateUtil.compare(linePrm.effFromDt_B.getValue(), param.slsDt.getValue()) < 0) {
                        // Mod Start 2018/02/23 QC#22568
                        //lineMap.addXxMsgId(NMZM0060E);
                        lineMap.addXxMsgId(NMZM0370E);
                        // Mod End 2018/02/23 QC#22568
                        returnValue = false;
                    }
                }
                // Bill to cust Effective Through Date
                if (ZYPCommonFunc.hasValue(linePrm.effThruDt_B.getValue())) {
                    if (ZYPDateUtil.compare(linePrm.effThruDt_B.getValue(), date) < 0) {
                        // Mod Start 2018/02/23 QC#22568
                        //lineMap.addXxMsgId(NMZM0060E);
                        lineMap.addXxMsgId(NMZM0371E);
                        // Mod End 2018/02/23 QC#22568
                        returnValue = false;
                    }
                }
                // Ship to cust Effective From Date
                if (ZYPCommonFunc.hasValue(linePrm.effFromDt_S.getValue())) {
                    if (ZYPDateUtil.compare(linePrm.effFromDt_S.getValue(), param.slsDt.getValue()) < 0) {
                        // Mod Start 2018/02/23 QC#22568
                        //lineMap.addXxMsgId(NMZM0060E);
                        lineMap.addXxMsgId(NMZM0372E);
                        // Mod End 2018/02/23 QC#22568
                        returnValue = false;
                    }
                }
                // Ship to cust Effective Through Date
                if (ZYPCommonFunc.hasValue(linePrm.effThruDt_S.getValue())) {
                    if (ZYPDateUtil.compare(linePrm.effThruDt_S.getValue(), date) < 0) {
                        // Mod Start 2018/02/23 QC#22568
                        //lineMap.addXxMsgId(NMZM0060E);
                        lineMap.addXxMsgId(NMZM0373E);
                        // Mod End 2018/02/23 QC#22568
                        returnValue = false;
                    }
                }
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.actvFlg.getValue())) {
                    if (ZYPCommonFunc.hasValue(linePrm.effFromDt.getValue()) && ZYPDateUtil.compare(linePrm.effFromDt.getValue(), param.slsDt.getValue()) < 0) {
                        String rgtnStsCd = getPtyLocWrkRgtnStsCd(linePrm.locNum.getValue(), glblCmpyCd, checkModeForPros(param.xxModeCd.getValue()));
                        if (!RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                            lineMap.addXxMsgId(NMZM0060E);
                            returnValue = false;
                        }
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(linePrm.effFromDt.getValue())
                    && ZYPCommonFunc.hasValue(linePrm.effThruDt.getValue())) {
                if (ZYPDateUtil.compare(linePrm.effFromDt.getValue(), linePrm.effThruDt.getValue()) > 0) {
                    lineMap.addXxMsgId(NMAM8115E);
                    returnValue = false;
                }
            }
            if (ZYPCommonFunc.hasValue(linePrm.effFromDt_B.getValue())
                    && ZYPCommonFunc.hasValue(linePrm.effThruDt_B.getValue())) {
                if (ZYPDateUtil.compare(linePrm.effFromDt_B.getValue(), linePrm.effThruDt_B.getValue()) > 0) {
                    lineMap.addXxMsgId(NMAM8115E);
                    returnValue = false;
                }
            }
            if (ZYPCommonFunc.hasValue(linePrm.effFromDt_S.getValue())
                   && ZYPCommonFunc.hasValue(linePrm.effThruDt_S.getValue())) {
                if (ZYPDateUtil.compare(linePrm.effFromDt_S.getValue(), linePrm.effThruDt_S.getValue()) > 0) {
                    lineMap.addXxMsgId(NMAM8115E);
                    returnValue = false;
                }
            }

            // Denied Party List Status Code
            if (ZYPCommonFunc.hasValue(linePrm.dplStsCd_S.getValue())) {
                if (!chkDplSts(linePrm.dplStsCd_S.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0294E);
                    returnValue = false;
                }
            }
            // Bill To GL Intercompany code
            if (ZYPCommonFunc.hasValue(linePrm.coaAfflCd_B.getValue())) {
                if (!chkCoaAffl(linePrm.coaAfflCd_B.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0295E);
                    returnValue = false;
                }
            }
            // GL Classification code
            if (ZYPCommonFunc.hasValue(linePrm.coaChCd_B.getValue())) {
                if (!chkCoaCh(linePrm.coaChCd_B.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0296E);
                    returnValue = false;
                }
            }
            // Direct Sales Customer Tax Calculation Code for Bill To
            if (ZYPCommonFunc.hasValue(linePrm.dsCustTaxCalcCd_B.getValue())) {
                if (!chkDsCustTaxCalc(linePrm.dsCustTaxCalcCd_B.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0297E);
                    returnValue = false;
                }
            }
            // Direct Sales Customer Tax Calculation Code for Ship To
            if (ZYPCommonFunc.hasValue(linePrm.dsCustTaxCalcCd_S.getValue())) {
                if (!chkDsCustTaxCalc(linePrm.dsCustTaxCalcCd_S.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0298E);
                    returnValue = false;
                }
            }
            // Collection Customer Type Code for Bill To
            if (ZYPCommonFunc.hasValue(linePrm.cltCustTpCd.getValue())) {
                if (!chkCltCustTp(linePrm.cltCustTpCd.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0299E);
                    returnValue = false;
                }
            }
            // Direct Sales Tax Group Exemption Code for Bill To
            if (ZYPCommonFunc.hasValue(linePrm.dsTaxGrpExemCd_B.getValue())) {
                if (!chkDsTaxGrpExem(linePrm.dsTaxGrpExemCd_B.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0300E);
                    returnValue = false;
                }
            }
            // Direct Sales Tax Group Exemption Code for Ship To
            if (ZYPCommonFunc.hasValue(linePrm.dsTaxGrpExemCd_S.getValue())) {
                if (!chkDsTaxGrpExem(linePrm.dsTaxGrpExemCd_S.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0301E);
                    returnValue = false;
                }
            }
            // Direct Sales Tax Print Type Code for Bill To
            if (ZYPCommonFunc.hasValue(linePrm.dsTaxPrntTpCd_B.getValue())) {
                if (!chkDsTaxPrntTp(linePrm.dsTaxPrntTpCd_B.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0302E);
                    returnValue = false;
                }
            }
            // Direct Sales Tax Print Type Code for Ship To
            if (ZYPCommonFunc.hasValue(linePrm.dsTaxPrntTpCd_S.getValue())) {
                if (!chkDsTaxPrntTp(linePrm.dsTaxPrntTpCd_S.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0303E);
                    returnValue = false;
                }
            }
            // Line of Business Type Code for BIll To
            if (ZYPCommonFunc.hasValue(linePrm.lineBizTpCd.getValue())) {
                if (!chkLineBizTp(linePrm.lineBizTpCd.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0304E);
                    returnValue = false;
                }
            }
            // Direct Sales Cross_Reference Account Type Code
            if (ZYPCommonFunc.hasValue(linePrm.dsXrefAcctTpCd.getValue())) {
                if (!chkDsXrefAcctTp(linePrm.dsXrefAcctTpCd.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0306E);
                    returnValue = false;
                }
            }
            // Direct Sales Customer Collection Account Status Code
            if (ZYPCommonFunc.hasValue(linePrm.dsCltAcctStsCd.getValue())) {
                if (!chkDsCltAcctSts(linePrm.dsCltAcctStsCd.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMZM0281E);
                    returnValue = false;
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocBillToCrInfoFlg.getValue())) {
                // Credit Rating
                if (ZYPCommonFunc.hasValue(linePrm.custCrRtgCd.getValue())) {
                    if (!chkCustCrRtg(linePrm.custCrRtgCd.getValue(), glblCmpyCd)) {
                        msgMap.addXxMsgId(NMZM0288E);
                        returnValue = false;
                    }
                }
                // Grace period(Days)
                if (ZYPCommonFunc.hasValue(linePrm.crRiskClsCd.getValue())) {
                    if (!chkCrRiskClsCd(linePrm.crRiskClsCd.getValue(), glblCmpyCd)) {
                        msgMap.addXxMsgId(NMZM0289E);
                        returnValue = false;
                    }
                }
                // Payment Term
                if (ZYPCommonFunc.hasValue(linePrm.pmtTermCashDiscCd.getValue())) {
                    if (!chkPmtTermCashDisc(linePrm.pmtTermCashDiscCd.getValue(), glblCmpyCd)) {
                        msgMap.addXxMsgId(NMZM0290E);
                        returnValue = false;
                    }
                }
                // Credit Hold
                if (ZYPCommonFunc.hasValue(linePrm.crChkReqTpCd.getValue())) {
                    if (!chkCrChkReqTp(linePrm.crChkReqTpCd.getValue(), glblCmpyCd)) {
                        msgMap.addXxMsgId(NMZM0330E);
                        returnValue = false;
                    }
                }
            }

            lineMap.flush();
        }

        return returnValue;
    }

    /**
     * Input parameter check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkInput(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC001001PMsg param = (NMZC001001PMsg) msgMap.getPmsg();

        // Common mandatory check
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NMZM0009E);
            returnValue = false;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NMZM0011E);
            returnValue = false;
        }

        if (!ZYPCommonFunc.hasValue(param.xxModeCd)) {
            msgMap.addXxMsgId(NMZM0054E);
            returnValue = false;
        } else {
            if (!PROCESS_MODE_PROS_CRAT.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_PROS_UPD.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_CUST_CRAT.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_CUST_UPD.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_CUST_CRAT_ACTV.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_EXST_CUST_LOC.equals(param.xxModeCd.getValue())
                    ) {
                msgMap.addXxMsgId(NMZM0055E);
                returnValue = false;
            }
        }
        // Mandatory by conditions.
        if (param.lateFeeFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            if (!ZYPCommonFunc.hasValue(param.lateFeeAmt)) {
                msgMap.addXxMsgId(NMZM0194E);
                returnValue = false;
            }
        }
        if (ZYPConstant.FLG_ON_Y.equals(param.custHardHldFlg.getValue())) {
            if (!CR_CHK_REQ_TP.HOLD.equals(param.crChkReqTpCd.getValue())) {
                msgMap.addXxMsgId(NMAM8638E);
                returnValue = false;
            }
        }

        // Common mandatory check for mode 01, 03, 05
        if (PROCESS_MODE_PROS_CRAT.equals(param.xxModeCd.getValue())
                || PROCESS_MODE_CUST_CRAT.equals(param.xxModeCd.getValue())
                || PROCESS_MODE_CUST_CRAT_ACTV.equals(param.xxModeCd.getValue())
                ) {
            // Account Name
            if (!ZYPCommonFunc.hasValue(param.dsAcctNm)) {
                msgMap.addXxMsgId(NMZM0195E);
                returnValue = false;
            }
            // Internal / External
            if (!ZYPCommonFunc.hasValue(param.dsAcctItrlFlg)) {
                msgMap.addXxMsgId(NMZM0196E);
                returnValue = false;
            }
            // Classification Code
            if (!ZYPCommonFunc.hasValue(param.dsAcctClsCd)) {
                msgMap.addXxMsgId(NMZM0197E);
                returnValue = false;
            }
            // GL Classification code
            if (!ZYPCommonFunc.hasValue(param.coaChCd)) {
                msgMap.addXxMsgId(NMZM0198E);
                returnValue = false;
            }
            // GL Intercompany code
            if (!ZYPCommonFunc.hasValue(param.coaAfflCd)) {
                msgMap.addXxMsgId(NMZM0199E);
                returnValue = false;
            }
            // Account Credit flag
            if (!ZYPCommonFunc.hasValue(param.xxAcctCrFlg)) {
                msgMap.addXxMsgId(NMZM0200E);
                returnValue = false;
            }
            if (param.xxAcctCrFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                // Credit Rating
                if (!ZYPCommonFunc.hasValue(param.custCrRtgCd)) {
                    msgMap.addXxMsgId(NMZM0201E);
                    returnValue = false;
                }
                // Credit Limit
                if (!ZYPCommonFunc.hasValue(param.crLimitAmt)) {
                    msgMap.addXxMsgId(NMZM0202E);
                    returnValue = false;
                }
                // Credit Hold
                if (!ZYPCommonFunc.hasValue(param.crChkReqTpCd)) {
                    msgMap.addXxMsgId(NMZM0203E);
                    returnValue = false;
                }
                // Grace period(Days)
                if (!ZYPCommonFunc.hasValue(param.crRiskClsCd)) {
                    msgMap.addXxMsgId(NMZM0204E);
                    returnValue = false;
                }
                // Payment Term
                if (!ZYPCommonFunc.hasValue(param.pmtTermCashDiscCd)) {
                    msgMap.addXxMsgId(NMZM0205E);
                    returnValue = false;
                }
            }

        }
        // Common mandatory check for mode 02, 04, 06
        if (PROCESS_MODE_PROS_UPD.equals(param.xxModeCd.getValue())
                || PROCESS_MODE_CUST_UPD.equals(param.xxModeCd.getValue())
                || PROCESS_MODE_EXST_CUST_LOC.equals(param.xxModeCd.getValue())
                ) {

            // Account Number
            if (!ZYPCommonFunc.hasValue(param.dsAcctNum)) {
                msgMap.addXxMsgId(NMZM0206E);
                returnValue = false;
            }

            // Mandatory by conditions.
            // Inactive Reason
            if (param.xxAcctInacRsnFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                if (!ZYPCommonFunc.hasValue(param.dsAcctInacRsnCd)) {
                    msgMap.addXxMsgId(NMZM0207E);
                    returnValue = false;
                }
            }
        }
        // Mandatory check for mode 04, 06
        if (PROCESS_MODE_CUST_UPD.equals(param.xxModeCd.getValue())
                || PROCESS_MODE_EXST_CUST_LOC.equals(param.xxModeCd.getValue())
                ) {
            // Registration Status Code
            if (!ZYPCommonFunc.hasValue(param.rgtnStsCd)) {
                msgMap.addXxMsgId(NMZM0208E);
                returnValue = false;
            }
            // Mandatory by conditions.
            // Inactive reason Effective end date
            if (param.xxAcctInacRsnFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                if (!ZYPCommonFunc.hasValue(param.effThruDt)) {
                    msgMap.addXxMsgId(NMZM0209E);
                    returnValue = false;
                }
            }
        }
        // Mandatory check for mode 05
        if (PROCESS_MODE_CUST_CRAT_ACTV.equals(param.xxModeCd.getValue())) {
            // Account Collection flag
            if (!ZYPCommonFunc.hasValue(param.xxAcctCltFlg)) {
                msgMap.addXxMsgId(NMZM0210E);
                returnValue = false;
            }
            // Default Collection Portfolio
            if (!ZYPCommonFunc.hasValue(param.cltPtfoPk)) {
                msgMap.addXxMsgId(NMZM0211E);
                returnValue = false;
            }
        }
        // Mandatory check for line
        for (int i = 0; i < param.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = param.NMZC001002PMsg.no(i);
            S21ApiMessageMap lineMap = new S21ApiMessageMap(linePrm);

            // Common mandatory check for mode 01, 03, 05, 06
            if (PROCESS_MODE_PROS_CRAT.equals(param.xxModeCd.getValue())
                    || PROCESS_MODE_CUST_CRAT.equals(param.xxModeCd.getValue())
                    || PROCESS_MODE_CUST_CRAT_ACTV.equals(param.xxModeCd.getValue())
                    || PROCESS_MODE_EXST_CUST_LOC.equals(param.xxModeCd.getValue())
                    ) {
                // Location Effective From Date
                if (!ZYPCommonFunc.hasValue(linePrm.effFromDt)) {
                    msgMap.addXxMsgId(NMZM0212E);
                    returnValue = false;
                }
                // First Line Address
                if (!ZYPCommonFunc.hasValue(linePrm.firstLineAddr)) {
                    msgMap.addXxMsgId(NMZM0213E);
                    returnValue = false;
                }
                // City Address
                if (!ZYPCommonFunc.hasValue(linePrm.ctyAddr)) {
                    msgMap.addXxMsgId(NMZM0214E);
                    returnValue = false;
                }
                // Postal Code
                if (!ZYPCommonFunc.hasValue(linePrm.postCd)) {
                    msgMap.addXxMsgId(NMZM0216E);
                    returnValue = false;
                }
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())
                        && !ZYPCommonFunc.hasValue(linePrm.effFromDt_B)) {
                    ZYPEZDItemValueSetter.setValue(linePrm.effFromDt_B, param.slsDt);
                }
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.shipToCustFlg.getValue())
                        && !ZYPCommonFunc.hasValue(linePrm.effFromDt_S)) {
                    ZYPEZDItemValueSetter.setValue(linePrm.effFromDt_S, param.slsDt);
                }
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())) {
                    // Bill to cust Effective From Date
                    if (!ZYPCommonFunc.hasValue(linePrm.effFromDt_B)) {
                        msgMap.addXxMsgId(NMZM0218E);
                        returnValue = false;
                    }
                }
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.shipToCustFlg.getValue())) {
                    // Ship to cust Effective From Date
                    if (!ZYPCommonFunc.hasValue(linePrm.effFromDt_S)) {
                        msgMap.addXxMsgId(NMZM0219E);
                        returnValue = false;
                    }
                }
                // Credit Limit Amount
                if (PROCESS_MODE_CUST_CRAT_ACTV.equals(param.xxModeCd.getValue())) {
                    if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocBillToCrInfoFlg.getValue())) {
                        if (!(ZYPCommonFunc.hasValue(param.crLimitAmt.getValue()) && param.crLimitAmt.getValue().compareTo(BigDecimal.ZERO) > 0)) {
                            msgMap.addXxMsgId(NMZM0062E);
                            returnValue = false;
                        }
                    }
                }
            }

            // Common mandatory check for mode 02, 04
            if (PROCESS_MODE_PROS_UPD.equals(param.xxModeCd.getValue()) || PROCESS_MODE_CUST_UPD.equals(param.xxModeCd.getValue())) {
                // Location Number
                if (!ZYPCommonFunc.hasValue(linePrm.locNum)) {
                    msgMap.addXxMsgId(NMZM0222E);
                    returnValue = false;
                }
            }

            // Mandatory by conditions.
            if (linePrm.xxLocXrefInfoFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                // Cross_Reference Account Type Code
                if (!ZYPCommonFunc.hasValue(linePrm.dsXrefAcctTpCd)) {
                    msgMap.addXxMsgId(NMZM0223E);
                    returnValue = false;
                }
                // Cross_Reference Account Code
                if (!ZYPCommonFunc.hasValue(linePrm.dsXrefAcctCd)) {
                    msgMap.addXxMsgId(NMZM0224E);
                    returnValue = false;
                }
            }
            if (linePrm.lateFeeFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                // Minimum Balance to calculate Late Fee
                if (!ZYPCommonFunc.hasValue(linePrm.lateFeeAmt)) {
                    msgMap.addXxMsgId(NMZM0226E);
                    returnValue = false;
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.custHardHldFlg.getValue())) {
                if (!CR_CHK_REQ_TP.HOLD.equals(linePrm.crChkReqTpCd.getValue())) {
                    msgMap.addXxMsgId(NMAM8638E);
                    returnValue = false;
                }
            }

            // Check parameters which should not be set for prospective mode
            if (PROCESS_MODE_PROS_CRAT.equals(param.xxModeCd.getValue()) || PROCESS_MODE_PROS_UPD.equals(param.xxModeCd.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())
                        || ZYPConstant.FLG_ON_Y.equals(linePrm.shipToCustFlg.getValue())) {
                    msgMap.addXxMsgId(NMZM0346E);
                    returnValue = false;
                }

                List<Object[]> listChkTrgt = new ArrayList<Object[]>();
                listChkTrgt.add(new Object[] {linePrm.billToCustFlg, "Bill To Customer Flag" });
                listChkTrgt.add(new Object[] {linePrm.shipToCustFlg, "Ship To Customer Flag" });
                listChkTrgt.add(new Object[] {linePrm.effFromDt_B, "Bill To Customer Effective From Date" });
                listChkTrgt.add(new Object[] {linePrm.effThruDt_B, "Bill To Customer Effective Through Date" });
                listChkTrgt.add(new Object[] {linePrm.primUsgFlg_B, "Bill To Primary Usage Flag" });
                listChkTrgt.add(new Object[] {linePrm.coaChCd_B, "Bill To GL Classification Code" });
                listChkTrgt.add(new Object[] {linePrm.coaAfflCd_B, "Bill To GL Intercompany Code" });
                listChkTrgt.add(new Object[] {linePrm.remId_B, "Remit ID" });
                listChkTrgt.add(new Object[] {linePrm.xxLocBillToCrInfoFlg, "Location Bill To Credit Flag" });
                listChkTrgt.add(new Object[] {linePrm.custCrRtgCd, "Credit Rating" });
                listChkTrgt.add(new Object[] {linePrm.crLimitAmt, "Credit Limit" });
                listChkTrgt.add(new Object[] {linePrm.crChkReqTpCd, "Credit Hold" });
                listChkTrgt.add(new Object[] {linePrm.crRiskClsCd, "Grace Period (Days)" });
                listChkTrgt.add(new Object[] {linePrm.pmtTermCashDiscCd, "Payment Term" });
                listChkTrgt.add(new Object[] {linePrm.ovrdPmtTermFlg, "Override Payment Term" });
                listChkTrgt.add(new Object[] {linePrm.cashOrCcReqFlg, "Cash On Delivery or Credit Card Required" });
                listChkTrgt.add(new Object[] {linePrm.custHardHldFlg, "Hard Hold" });
                listChkTrgt.add(new Object[] {linePrm.xxLocBillToCltInfoFlg, "Location Bill to Collections Flag" });
                listChkTrgt.add(new Object[] {linePrm.arStmtFlg, "Send Statements " });
                listChkTrgt.add(new Object[] {linePrm.arStmtIssDay, "Statements Issue Day" });
                listChkTrgt.add(new Object[] {linePrm.dunFlg, "Send Dunning Letters" });
                listChkTrgt.add(new Object[] {linePrm.cltCustTpCd, "Collection Customer Type Code" });
                listChkTrgt.add(new Object[] {linePrm.cltPtfoPk, "Default Collection Portfolio" });
                listChkTrgt.add(new Object[] {linePrm.dsCltAcctStsCd, "Collection Account Status Code" });
                listChkTrgt.add(new Object[] {linePrm.lateFeeFlg, "Calculate Late Fee" });
                listChkTrgt.add(new Object[] {linePrm.lateFeeAmt, "Minimum Balance to Calculate Late Fee" });
                listChkTrgt.add(new Object[] {linePrm.xxLocBillToTaxFlg_B, "Location Bill to Taxing Flag" });
                listChkTrgt.add(new Object[] {linePrm.dsCustTaxCd_B, "Bill To Tax Code" });
                listChkTrgt.add(new Object[] {linePrm.dsCustTaxCalcCd_B, "Bill To Tax Calculation" });
                listChkTrgt.add(new Object[] {linePrm.dsTaxExemFlg_B, "Bill To Tax Exempt" });
                listChkTrgt.add(new Object[] {linePrm.dsExemExprDt_B, "Bill To Exempt Expiration Date" });
                listChkTrgt.add(new Object[] {linePrm.dsTaxGrpExemCd_B, "Bill To Tax Group Exemption Code" });
                listChkTrgt.add(new Object[] {linePrm.dsTaxPrntTpCd_B, "Bill To Tax Prining" });
                listChkTrgt.add(new Object[] {linePrm.effFromDt_S, "Ship To Customer Effective From Date" });
                listChkTrgt.add(new Object[] {linePrm.effThruDt_S, "Ship To Customer Effective Through Date" });
                listChkTrgt.add(new Object[] {linePrm.relnBillToCustCd_S, "Related Bill To Customer Code" });
                listChkTrgt.add(new Object[] {linePrm.primUsgFlg_S, "Primary Usage Flag" });
                listChkTrgt.add(new Object[] {linePrm.bigDealNum_S, "Big Deal Number" });
                listChkTrgt.add(new Object[] {linePrm.dplStsCd_S, "Denied Party List Status Code" });
                listChkTrgt.add(new Object[] {linePrm.coaChCd_S, "Ship To GL Classification Code" });
                listChkTrgt.add(new Object[] {linePrm.coaAfflCd_S, "Ship To GL Intercompany Code" });
                listChkTrgt.add(new Object[] {linePrm.xxLocShipToTaxFlg_S, "Location Ship To Tax Flag" });
                listChkTrgt.add(new Object[] {linePrm.dsCustTaxCd_S, "Ship to Direct Sales Customer Tax Code" });
                listChkTrgt.add(new Object[] {linePrm.dsCustTaxCalcCd_S, "Ship To Tax Calculation" });
                listChkTrgt.add(new Object[] {linePrm.dsTaxExemFlg_S, "Ship To Tax Exempt" });
                listChkTrgt.add(new Object[] {linePrm.dsExemExprDt_S, "Ship To Exempt Expiration Date" });
                listChkTrgt.add(new Object[] {linePrm.dsTaxGrpExemCd_S, "Ship To Tax Group Exemption Code" });
                listChkTrgt.add(new Object[] {linePrm.dsTaxPrntTpCd_S, "Ship To Tax Prining" });
                listChkTrgt.add(new Object[] {linePrm.firstRefCmntTxt_B, "Bill To Customer First Reference Comment Text" });
                listChkTrgt.add(new Object[] {linePrm.scdRefCmntTxt_B, "Bill To Customer Second Reference Comment Text" });
                listChkTrgt.add(new Object[] {linePrm.firstRefCmntTxt_S, "Ship To Customer First Reference Comment Text" });
                listChkTrgt.add(new Object[] {linePrm.scdRefCmntTxt_S, "Ship To Customer Second Reference Comment Text" });

                for (Object[] chkTrgt : listChkTrgt) {
                    EZDPItem item = (EZDPItem) chkTrgt[0];
                    String itemNm = (String) chkTrgt[1];
                    if (ZYPCommonFunc.hasValue(item)) {
                        if (lineMap.getMsgMgr().getXxMsgIdListSize() < linePrm.xxMsgIdList.getValidCount()) {
                            lineMap.addXxMsgIdWithPrm(NMZM0133E, new String[] {"Process Mode", "Prospective Mode", itemNm, "Blank" });
                            returnValue = false;
                        }
                    }
                }

            } else {
                setDefaultFlagValue(linePrm.arStmtFlg);
                setDefaultFlagValue(linePrm.primUsgFlg_B);
                setDefaultFlagValue(linePrm.primUsgFlg_S);
                setDefaultFlagValue(linePrm.dsTaxExemFlg_S);
            }
            setDefaultFlagValue(linePrm.dsInsdCtyLimitFlg);

            if (PROCESS_MODE_PROS_CRAT.equals(param.xxModeCd.getValue())
                    || PROCESS_MODE_CUST_CRAT.equals(param.xxModeCd.getValue())
                    || PROCESS_MODE_CUST_CRAT_ACTV.equals(param.xxModeCd.getValue())
                    || PROCESS_MODE_EXST_CUST_LOC.equals(param.xxModeCd.getValue())
                        ) {
                if (!ZYPCommonFunc.hasValue(linePrm.lineBizTpCd)) {
                    linePrm.lineBizTpCd.setValue(LINE_BIZ_TP.ALL);
                }
            }

            lineMap.flush();
        }

        // Location is only when prospective mode
        if (PROCESS_MODE_PROS_CRAT.equals(param.xxModeCd.getValue()) || PROCESS_MODE_PROS_UPD.equals(param.xxModeCd.getValue())) {
            if (LOC_MAX_CNT_FOR_PROS < param.NMZC001002PMsg.getValidCount()) {
                msgMap.addXxMsgId(NMZM0345E);
                returnValue = false;
            }
        }

        return returnValue;
    }

    private void setDefaultFlagValue(EZDPStringItem item) {
        if (!ZYPCommonFunc.hasValue(item)) {
            item.setValue(ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {
        toUpperParameter(msgMap);

        setupLogicPrinAddrFlg(msgMap);

        if (!checkValidation(msgMap)) {
            return;
        }

        if (!setChangeAddrFlg(msgMap)) {
            return;
        }

        // API check
        if (!checkByApi(msgMap)) {
            return;
        }

        // Get DPL Information
        if (!getDplInfo(msgMap)) {
            return;
        }

        // Insert / Update
        if (!insertUpdateTable(msgMap)) {
            return;
        }

        // Update credit profile
        if (!callCrPrflUpdApi(msgMap)) {
            return;
        }

        // Set Output Parameter
        setOutputParameter(msgMap);

    }

    private void setupLogicPrinAddrFlg(S21ApiMessageMap msgMap) {
        boolean isExstPrinAddr = false;
        NMZC001001PMsg param = (NMZC001001PMsg) msgMap.getPmsg();
        for (int i = 0; i < param.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = param.NMZC001002PMsg.no(i);
            ZYPEZDItemValueSetter.setValue(linePrm.xxPrinFlg_NW, linePrm.xxPrinFlg);
            ZYPEZDItemValueSetter.setValue(linePrm.xxPrinFlg_OD, ZYPConstant.FLG_OFF_N);
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                isExstPrinAddr = true;
            }
        }

        if (isNewAcctMode(param)) {
            // If primary location not exist in parameter list and mode is create mode, 
            // set xxPrinFlg_NW as 'Y' for the first location. 
            if (!isExstPrinAddr) {
                if (param.NMZC001002PMsg.getValidCount() > 0) {
                    NMZC001002PMsg linePrm = param.NMZC001002PMsg.no(0);
                    ZYPEZDItemValueSetter.setValue(linePrm.xxPrinFlg_NW, ZYPConstant.FLG_ON_Y);
                }
            }
        } else {
         // If location is primary location before update,
         // set xxPrinFlg_OD as 'Y'.
            Map<String, Object> ssm = new HashMap<String, Object>();
            ssm.put("glblCmpyCd", param.glblCmpyCd.getValue());
            ssm.put("dsAcctNum", param.dsAcctNum.getValue());
            List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPrinCustByAcct", ssm);
            if (list.size() > 0) {
                Map<String, Object> map = list.get(0);
                exstPrinCustPk = (BigDecimal) map.get("PRIN_CUST_PK");
                String exstPrinCustLocNum = (String) map.get("LOC_NUM");
                if (isExstPrinAddr) {
                    for (int i = 0; i < param.NMZC001002PMsg.getValidCount(); i++) {
                        NMZC001002PMsg linePrm = param.NMZC001002PMsg.no(i);
                        if (ZYPCommonFunc.hasValue(linePrm.locNum)) {
                            if (linePrm.locNum.getValue().equals(exstPrinCustLocNum)) {
                                linePrm.xxPrinFlg_OD.setValue(ZYPConstant.FLG_ON_Y);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isNewAcctMode(NMZC001001PMsg param) {
        if (PROCESS_MODE_PROS_CRAT.equals(param.xxModeCd.getValue())
         || PROCESS_MODE_CUST_CRAT.equals(param.xxModeCd.getValue())
         || PROCESS_MODE_CUST_CRAT_ACTV.equals(param.xxModeCd.getValue())
            ) {
            return true;
        }
        return false;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param dsAcctClsCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsAcctCls(String dsAcctClsCd, String glblCmpyCd) {

        DS_ACCT_CLSTMsg dsAcctClsTMsg = new DS_ACCT_CLSTMsg();
        ZYPEZDItemValueSetter.setValue(dsAcctClsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcctClsTMsg.dsAcctClsCd, dsAcctClsCd);
        dsAcctClsTMsg = (DS_ACCT_CLSTMsg) EZDTBLAccessor.findByKey(dsAcctClsTMsg);
        if (dsAcctClsTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param dsAcctDlrCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsAcctDlr(String dsAcctDlrCd, String glblCmpyCd) {

        DS_ACCT_DLRTMsg chkDsAcctDlrTMsg = new DS_ACCT_DLRTMsg();
        ZYPEZDItemValueSetter.setValue(chkDsAcctDlrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkDsAcctDlrTMsg.dsAcctDlrCd, dsAcctDlrCd);
        chkDsAcctDlrTMsg = (DS_ACCT_DLRTMsg) EZDTBLAccessor.findByKey(chkDsAcctDlrTMsg);
        if (chkDsAcctDlrTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param stCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkSt(String stCd, String glblCmpyCd) {

        STTMsg chkStTMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(chkStTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkStTMsg.stCd, stCd);
        chkStTMsg = (STTMsg) EZDTBLAccessor.findByKey(chkStTMsg);
        if (chkStTMsg == null) {
            return false;
        }
        return true;
    }

    private boolean chkSt(String stCd, String ctryCd, String glblCmpyCd) {
        STTMsg chkStTMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(chkStTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkStTMsg.stCd, stCd);
        chkStTMsg = (STTMsg) EZDTBLAccessor.findByKey(chkStTMsg);
        if (chkStTMsg == null) {
            return false;
        }
        if (!ctryCd.equals(chkStTMsg.ctryCd.getValue())) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param cntyNm String
     * @param stCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCnty(String cntyNm, String stCd, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("cntyNm", cntyNm);
        param.put("stCd", stCd);

        Integer resCnt = (Integer) this.ssmBatchClient.queryObject("cntCnty", param);
        if (resCnt < 1) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param pCntyPk BigDecimal
     * @param cntyNm String
     * @param stCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private BigDecimal getCntyPk(BigDecimal pCntyPk, String cntyNm, String stCd, String glblCmpyCd) {
        if (ZYPCommonFunc.hasValue(pCntyPk)) {
            return pCntyPk;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("cntyNm", cntyNm);
        param.put("stCd", stCd);

        BigDecimal cntyPk = (BigDecimal) this.ssmBatchClient.queryObject("getCntyPk", param);
        if (ZYPCommonFunc.hasValue(cntyPk)) {
            return cntyPk;
        } else {
            return null;
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private BigDecimal getDsAcctProsPkForUpd(String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsAcctNum", dsAcctNum);

        BigDecimal dsAcctProsPk = (BigDecimal) this.ssmBatchClient.queryObject("getDsAcctProsPk", param);
        if (ZYPCommonFunc.hasValue(dsAcctProsPk)) {
            return dsAcctProsPk;
        } else {
            return null;
        }
    }

    private BigDecimal getSellToCustPkByAcctNumForUpd(String dsAcctNum, String glblCmpyCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsAcctNum", dsAcctNum);

        BigDecimal sellToCustPk = (BigDecimal) this.ssmBatchClient.queryObject("getSellToCustPkByAcctNum", param);
        if (ZYPCommonFunc.hasValue(sellToCustPk)) {
            return sellToCustPk;
        } else {
            return null;
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param locNum String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private BigDecimal getAcctLocPkForUpd(String locNum, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locNum", locNum);

        BigDecimal acctLocPk = (BigDecimal) this.ssmBatchClient.queryObject("getAcctLocPk", param);
        if (ZYPCommonFunc.hasValue(acctLocPk)) {
            return acctLocPk;
        } else {
            return null;
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param locNum String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private BigDecimal getBillToCustPkForUpd(String locNum, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locNum", locNum);

        BigDecimal billToCusttPk = (BigDecimal) this.ssmBatchClient.queryObject("getBillToCustPk", param);
        if (ZYPCommonFunc.hasValue(billToCusttPk)) {
            return billToCusttPk;
        } else {
            return null;
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param locNum String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private BigDecimal getCustPkForUpd(String locNum, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locNum", locNum);

        BigDecimal custPk = (BigDecimal) this.ssmBatchClient.queryObject("getCustPk", param);
        if (ZYPCommonFunc.hasValue(custPk)) {
            return custPk;
        } else {
            return null;
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param locNum String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private BigDecimal getShipToCustPkForUpd(String locNum, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locNum", locNum);

        BigDecimal shipToCustPk = (BigDecimal) this.ssmBatchClient.queryObject("getShipToCustPk", param);
        if (ZYPCommonFunc.hasValue(shipToCustPk)) {
            return shipToCustPk;
        } else {
            return null;
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param locNum String
     * @param glblCmpyCd String
     * @param dsXrefAcctTpCd Sring
     * @param dsXrefAcctCd String
     * @return returnValue
     */
    private BigDecimal getDsXrefAcctPkForUpd(String locNum, String glblCmpyCd, String dsXrefAcctTpCd, String dsXrefAcctCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locNum", locNum);
        param.put("dsXrefAcctTpCd", dsXrefAcctTpCd);
        param.put("dsXrefAcctCd", dsXrefAcctCd);

        BigDecimal dsXrefAcctPk = (BigDecimal) this.ssmBatchClient.queryObject("getDsXrefAcctPk", param);
        if (ZYPCommonFunc.hasValue(dsXrefAcctPk)) {
            return dsXrefAcctPk;
        } else {
            return null;
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @return returnValue
     */
    private String getCcyCd(String glblCmpyCd) {

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);
        if (glblCmpyTMsg == null) {
            return "";
        }
        return glblCmpyTMsg.stdCcyCd.getValue();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param ctryCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCtry(String ctryCd, String glblCmpyCd) {

        CTRYTMsg chkCtryTMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(chkCtryTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkCtryTMsg.ctryCd, ctryCd);
        chkCtryTMsg = (CTRYTMsg) EZDTBLAccessor.findByKey(chkCtryTMsg);
        if (chkCtryTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param rgtnStsCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkRgtn(String rgtnStsCd, String glblCmpyCd) {

        RGTN_STSTMsg chkRgtnTMsg = new RGTN_STSTMsg();
        ZYPEZDItemValueSetter.setValue(chkRgtnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkRgtnTMsg.rgtnStsCd, rgtnStsCd);
        chkRgtnTMsg = (RGTN_STSTMsg) EZDTBLAccessor.findByKey(chkRgtnTMsg);
        if (chkRgtnTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param coaAfflCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCoaAffl(String coaAfflCd, String glblCmpyCd) {

        COA_AFFLTMsg chkCoaAfflTMsg = new COA_AFFLTMsg();
        ZYPEZDItemValueSetter.setValue(chkCoaAfflTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkCoaAfflTMsg.coaAfflCd, coaAfflCd);
        chkCoaAfflTMsg = (COA_AFFLTMsg) EZDTBLAccessor.findByKey(chkCoaAfflTMsg);
        if (chkCoaAfflTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param dsCltAcctStsCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsCltAcctSts(String dsCltAcctStsCd, String glblCmpyCd) {

        DS_CLT_ACCT_STSTMsg dsCltAcctStsTMsg = new DS_CLT_ACCT_STSTMsg();
        ZYPEZDItemValueSetter.setValue(dsCltAcctStsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCltAcctStsTMsg.dsCltAcctStsCd, dsCltAcctStsCd);
        dsCltAcctStsTMsg = (DS_CLT_ACCT_STSTMsg) EZDTBLAccessor.findByKey(dsCltAcctStsTMsg);
        if (dsCltAcctStsTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param cltCustTpCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCltCustTp(String cltCustTpCd, String glblCmpyCd) {

        CLT_CUST_TPTMsg cltCustTpTMsg = new CLT_CUST_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cltCustTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cltCustTpTMsg.cltCustTpCd, cltCustTpCd);
        cltCustTpTMsg = (CLT_CUST_TPTMsg) EZDTBLAccessor.findByKey(cltCustTpTMsg);
        if (cltCustTpTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param dsTaxPrntTpCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsTaxPrntTp(String dsTaxPrntTpCd, String glblCmpyCd) {

        DS_TAX_PRNT_TPTMsg dsTaxPrntTpTMsg = new DS_TAX_PRNT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsTaxPrntTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsTaxPrntTpTMsg.dsTaxPrntTpCd, dsTaxPrntTpCd);
        dsTaxPrntTpTMsg = (DS_TAX_PRNT_TPTMsg) EZDTBLAccessor.findByKey(dsTaxPrntTpTMsg);
        if (dsTaxPrntTpTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param dsCustTaxCalcCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsCustTaxCalc(String dsCustTaxCalcCd, String glblCmpyCd) {

        DS_CUST_TAX_CALCTMsg dsCustTaxCalcTMsg = new DS_CUST_TAX_CALCTMsg();
        ZYPEZDItemValueSetter.setValue(dsCustTaxCalcTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCustTaxCalcTMsg.dsCustTaxCalcCd, dsCustTaxCalcCd);
        dsCustTaxCalcTMsg = (DS_CUST_TAX_CALCTMsg) EZDTBLAccessor.findByKey(dsCustTaxCalcTMsg);
        if (dsCustTaxCalcTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param dsTaxGrpExemCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsTaxGrpExem(String dsTaxGrpExemCd, String glblCmpyCd) {

        DS_TAX_GRP_EXEMTMsg dsTaxGrpExemTMsg = new DS_TAX_GRP_EXEMTMsg();
        ZYPEZDItemValueSetter.setValue(dsTaxGrpExemTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsTaxGrpExemTMsg.dsTaxGrpExemCd, dsTaxGrpExemCd);
        dsTaxGrpExemTMsg = (DS_TAX_GRP_EXEMTMsg) EZDTBLAccessor.findByKey(dsTaxGrpExemTMsg);
        if (dsTaxGrpExemTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param coaChCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCoaCh(String coaChCd, String glblCmpyCd) {

        COA_CHTMsg coaChTMsg = new COA_CHTMsg();
        ZYPEZDItemValueSetter.setValue(coaChTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaChTMsg.coaChCd, coaChCd);
        coaChTMsg = (COA_CHTMsg) EZDTBLAccessor.findByKey(coaChTMsg);
        if (coaChTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param dsAcctInacRsnCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsAcctInacRsn(String dsAcctInacRsnCd, String glblCmpyCd) {

        DS_ACCT_INAC_RSNTMsg dsAcctInacRsnTMsg = new DS_ACCT_INAC_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(dsAcctInacRsnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcctInacRsnTMsg.dsAcctInacRsnCd, dsAcctInacRsnCd);
        dsAcctInacRsnTMsg = (DS_ACCT_INAC_RSNTMsg) EZDTBLAccessor.findByKey(dsAcctInacRsnTMsg);
        if (dsAcctInacRsnTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param custCrRtgCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCustCrRtg(String custCrRtgCd, String glblCmpyCd) {

        CUST_CR_RTGTMsg custCrRtgTMsg = new CUST_CR_RTGTMsg();
        ZYPEZDItemValueSetter.setValue(custCrRtgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custCrRtgTMsg.custCrRtgCd, custCrRtgCd);
        custCrRtgTMsg = (CUST_CR_RTGTMsg) EZDTBLAccessor.findByKey(custCrRtgTMsg);
        if (custCrRtgTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param chkCrRiskClsCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCrRiskClsCd(String crRiskClsCd, String glblCmpyCd) {

        CR_RISK_CLSTMsg crRiskClsTMsg = new CR_RISK_CLSTMsg();
        ZYPEZDItemValueSetter.setValue(crRiskClsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(crRiskClsTMsg.crRiskClsCd, crRiskClsCd);
        crRiskClsTMsg = (CR_RISK_CLSTMsg) EZDTBLAccessor.findByKey(crRiskClsTMsg);
        if (crRiskClsTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param pmtTermCashDiscCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkPmtTermCashDisc(String pmtTermCashDiscCd, String glblCmpyCd) {

        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermCashDiscTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
        pmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg) EZDTBLAccessor.findByKey(pmtTermCashDiscTMsg);
        if (pmtTermCashDiscTMsg == null) {
            return false;
        }
        return true;
    }

    private boolean chkCrChkReqTp(String crChkReqTpCd, String glblCmpyCd) {
        CR_CHK_REQ_TPTMsg tMsg = new CR_CHK_REQ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.crChkReqTpCd, crChkReqTpCd);
        tMsg = (CR_CHK_REQ_TPTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param dplStsCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDplSts(String dplStsCd, String glblCmpyCd) {

        DPL_STSTMsg dplStsTMsg = new DPL_STSTMsg();
        ZYPEZDItemValueSetter.setValue(dplStsTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dplStsTMsg.dplStsCd, dplStsCd);
        dplStsTMsg = (DPL_STSTMsg) EZDTBLAccessor.findByKey(dplStsTMsg);
        if (dplStsTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param lineBizTpCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkLineBizTp(String lineBizTpCd, String glblCmpyCd) {

        LINE_BIZ_TPTMsg lineBizTpTMsg = new LINE_BIZ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(lineBizTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(lineBizTpTMsg.lineBizTpCd, lineBizTpCd);
        lineBizTpTMsg = (LINE_BIZ_TPTMsg) EZDTBLAccessor.findByKey(lineBizTpTMsg);
        if (lineBizTpTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param dsXrefAcctTpCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsXrefAcctTp(String dsXrefAcctTpCd, String glblCmpyCd) {

        DS_XREF_ACCT_TPTMsg dsXrefAcctTpTMsg = new DS_XREF_ACCT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsXrefAcctTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsXrefAcctTpTMsg.dsXrefAcctTpCd, dsXrefAcctTpCd);
        dsXrefAcctTpTMsg = (DS_XREF_ACCT_TPTMsg) EZDTBLAccessor.findByKey(dsXrefAcctTpTMsg);
        if (dsXrefAcctTpTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * Insert / Update
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean insertUpdateTable(S21ApiMessageMap msgMap) {

        NMZC001001PMsg param = (NMZC001001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();

        if (PROCESS_MODE_PROS_CRAT.equals(param.xxModeCd.getValue())
                || PROCESS_MODE_CUST_CRAT.equals(param.xxModeCd.getValue())
                || PROCESS_MODE_CUST_CRAT_ACTV.equals(param.xxModeCd.getValue())
                ) {
            if (!tableCreation(msgMap, param, glblCmpyCd)) {
                return false;
            }

        } else if (PROCESS_MODE_PROS_UPD.equals(param.xxModeCd.getValue())
                || PROCESS_MODE_CUST_UPD.equals(param.xxModeCd.getValue())
                ) {
            if (!tableUpdate(msgMap, param, glblCmpyCd)) {
                return false;
            }
        } else if (PROCESS_MODE_EXST_CUST_LOC.equals(param.xxModeCd.getValue())
                ) {
            if (!tableUpdateExistCustomerLocation(msgMap, param, glblCmpyCd)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Exist Customer Location (Mode : 06)
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean tableUpdateExistCustomerLocation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        this.oldAcctCoaChCd = null;

        // --------------------------------------------------------
        // Account Level - Update
        // --------------------------------------------------------
        // Update SELL_TO_CUST
        if (!sellToUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update CMPY
        if (!cmpyUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update PRIN_CUST
        if (!prinCustUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update DS_ACCT_CR_PRFL
        if (!dsAcctCrPrflCreationUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update DS_CUST_SPCL_MSG
        if (!dsCustSpclMsgUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }

        // --------------------------------------------------------
        // Account Level Location Information - Update
        // --------------------------------------------------------
        boolean isPrimBillToInParamList = isPrimBillToInParamList(msgMap, pMsg);
        boolean isPrimShipToInParamList = isPrimShipToInParamList(msgMap, pMsg);
        List<Map> locInfoListNotInParam = getLocInfoByAcctNum(msgMap, pMsg);

        for (Map locInfoNotInParam : locInfoListNotInParam) {
            BigDecimal ptyLocPk = (BigDecimal) locInfoNotInParam.get("PTY_LOC_PK");
            BigDecimal prinCustPk = exstPrinCustPk;
            BigDecimal billToCustPk = (BigDecimal) locInfoNotInParam.get("BILL_TO_CUST_PK");
            BigDecimal shipToCustPk = (BigDecimal) locInfoNotInParam.get("SHIP_TO_CUST_PK");
            String locNum = (String) locInfoNotInParam.get("LOC_NUM");
            String billToCustCd = (String) locInfoNotInParam.get("BILL_TO_CUST_CD");

            LocInfoBean locInfoAtAcctLvl = new LocInfoBean();
            locInfoAtAcctLvl.setPtyLocPk(ptyLocPk);
            locInfoAtAcctLvl.setPrinCustPk(prinCustPk);
            locInfoAtAcctLvl.setBillToCustPk(billToCustPk);
            locInfoAtAcctLvl.setShipToCustPk(shipToCustPk);
            locInfoAtAcctLvl.setLocNum(locNum);
            locInfoAtAcctLvl.setBillToCustCd(billToCustCd);

            // Insert PTY_LOC_WRK
            if (!ptyLocWrkUpdate_ExstCustLocMode(msgMap, pMsg, glblCmpyCd, locInfoAtAcctLvl)) {
                return false;
            }
            if (billToCustPk != null) {
                // Insert BILL_TO_CUST
                if (!billToCustUpdate_ExstCustLocMode(msgMap, pMsg, glblCmpyCd, locInfoAtAcctLvl, isPrimBillToInParamList)) {
                    return false;
                }
                // Insert INV_RCPNT
                if (!invRcpntUpdate_ExstCustLocMode(msgMap, pMsg, glblCmpyCd, locInfoAtAcctLvl)) {
                    return false;
                }
                // Insert ALT_PAYER
                if (!altPayerUpdate_ExstCustLocMode(msgMap, pMsg, glblCmpyCd, locInfoAtAcctLvl)) {
                    return false;
                }
            }
            if (shipToCustPk != null) {
                // Insert SHIP_TO_CUST
                if (!shipToCustUpdate_ExstCustLocMode(msgMap, pMsg, glblCmpyCd, locInfoAtAcctLvl, isPrimShipToInParamList)) {
                    return false;
                }
            }
        }

        // --------------------------------------------------------
        // Location Level - Insert
        // --------------------------------------------------------
        // Insert ACCT_LOC
        if (!acctLocCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert PTY_LOC_WRK
        if (!ptyLocWrkCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert LOC_USG
        if (!locUsgCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert BILL_TO_CUST
        if (!billToCustCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert INV_RCPNT
        if (!invRcpntCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert ALT_PAYER
        if (!altPayerCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert CUST_CR_PRFL
        if (!custCrPrflCreationUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert SHIP_TO_CUST
        if (!shipToCustCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert DS_XREF_ACCT
        if (!dsXrefAcctCreateUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }

        return true;
    }

    private boolean isPrinCustLocInParamList(NMZC001001PMsg pMsg) {
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isExstPrinCustLocInParamList(NMZC001001PMsg pMsg) {
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg_OD.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isPrimBillToInParamList(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg) {
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.primUsgFlg_B.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isPrimShipToInParamList(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg) {
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.primUsgFlg_S.getValue())) {
                return true;
            }
        }
        return false;
    }

    private List<Map> getLocInfoByAcctNum(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("dsAcctNum", pMsg.dsAcctNum.getValue());
        List<Map> ptyLocPkList = (List<Map>) this.ssmBatchClient.queryObjectList("getLocInfoByAcctNum", param);
        return ptyLocPkList;
    }

    /**
     * New Prospect update (Mode : 02) / New Customer update (Mode :
     * 04)
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean tableUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        if (PROCESS_MODE_PROS_UPD.equals(pMsg.xxModeCd.getValue())) {
            // Update DS_ACCT_PROS
            if (!dsAcctProsUpdate(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
            // Update PTY_LOC_WRK
            if (!prosPtyLocWrkUpdate(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
        } else if (PROCESS_MODE_CUST_UPD.equals(pMsg.xxModeCd.getValue())) {
            // Update SELL_TO_CUST
            if (!sellToUpdate(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
            // Update ACCT_LOC
            if (!acctLocUpdate(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
            // Update PRIN_CUST
            if (!prinCustUpdate(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
            // Update PTY_LOC_WRK
            if (!ptyLocWrkUpdate(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
        }

        // Update CMPY
        if (!cmpyUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update WareHouse Address
        if (!wareHouseAddressUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update BILL_TO_CUST
        if (!billToCustUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update INV_RCPNT
        if (!invRcpntUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update ALT_PAYER
        if (!altPayerUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update CUST_CR_PRFL
        if (!custCrPrflCreationUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update SHIP_TO_CUST
        if (!shipToCustUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update DS_ACCT_CR_PRFL
        if (!dsAcctCrPrflCreationUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update DS_XREF_ACCT
        if (!dsXrefAcctCreateUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update DS_CUST_SPCL_MSG
        if (!dsCustSpclMsgUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }

        return true;
    }

    /**
     * New Prospect creation (Mode : 01) / New Customer creation (Mode
     * : 03) / New Customer creation for active (Mode : 05)
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean tableCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        if (PROCESS_MODE_PROS_CRAT.equals(pMsg.xxModeCd.getValue())) {
            // Insert DS_ACCT_PROS
            if (!dsAcctProsCreation(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
            // Insert PTY_LOC_WRK
            if (!prosPtyLocWrkCreation(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
        } else if (PROCESS_MODE_CUST_CRAT.equals(pMsg.xxModeCd.getValue()) || PROCESS_MODE_CUST_CRAT_ACTV.equals(pMsg.xxModeCd.getValue())) {
            // Insert SELL_TO_CUST
            if (!sellToCreation(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
            // Insert ACCT_LOC
            if (!acctLocCreation(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
            // Insert PRIN_CUST
            if (!prinCustCreation(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
            // Insert PTY_LOC_WRK
            if (!ptyLocWrkCreation(msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
        }

        // Insert CMPY
        if (!cmpyCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert LOC_USG
        if (!locUsgCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert BILL_TO_CUST
        if (!billToCustCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert INV_RCPNT
        if (!invRcpntCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert ALT_PAYER
        if (!altPayerCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert CUST_CR_PRFL
        if (!custCrPrflCreationUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert SHIP_TO_CUST
        if (!shipToCustCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert DS_ACCT_CR_PRFL
        if (!dsAcctCrPrflCreationUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert DS_XREF_ACCT
        if (!dsXrefAcctCreateUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        return true;
    }

    /**
     * SELL_TO_CUST Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean sellToCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        SELL_TO_CUSTTMsg sellToCustTmsg = new SELL_TO_CUSTTMsg();

        LocInfoBean locInfoBean = locInfoMap.get("0");
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.sellToCustPk, getSellToCustPk());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.effFromDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.sellToCustCd, getSellToCustCd(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.cmpyPk, locInfoBean.getCmpyPk());

        // From DSTable
        if (PROCESS_MODE_CUST_CRAT.equals(pMsg.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctActvCustFlg, ZYPConstant.FLG_OFF_N);
        } else if (PROCESS_MODE_CUST_CRAT_ACTV.equals(pMsg.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctActvCustFlg, ZYPConstant.FLG_ON_Y);
        }

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));

            locInfoBean.setSellToCustCd(sellToCustTmsg.sellToCustCd.getValue());
            locInfoBean.setCmpyPk(sellToCustTmsg.cmpyPk.getValue());

            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg_NW.getValue())) {
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.locNum, getLocNum(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.prinCustPk, getPrinCustPk());
                locInfoBean.setLocNum(sellToCustTmsg.locNum.getValue());
                locInfoBean.setPtyLocPk(sellToCustTmsg.ptyLocPk.getValue());
                locInfoBean.setCntyPk(sellToCustTmsg.cntyPk.getValue());
                locInfoBean.setPrinCustPk(sellToCustTmsg.prinCustPk.getValue());
                locInfoMap.put(String.valueOf(i), locInfoBean);
            }
        }
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.rgtnStsCd, getRgtnSts(pMsg.xxModeCd.getValue()));
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.usrDlrTpCd, USR_DLR_TP.USER);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.ordFuflLvlCd, ORD_FUFL_LVL.UNRESTRICTED);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.locRoleTpCd, LOC_ROLE_TP.SELL_TO);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.locGrpCd, LOC_GRP.CUSTOMER);

        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.sellHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.canRcvInvAtSetCmptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.embgoFlg, ZYPConstant.FLG_OFF_N);

        sellToCustSetParam(pMsg, sellToCustTmsg);
        S21ApiTBLAccessor.create(sellToCustTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(sellToCustTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0231E);
            return false;
        }

        return true;
    }

    /**
     * SELL_TO_CUST Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean sellToUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        SELL_TO_CUSTTMsg sellToCustTmsg = new SELL_TO_CUSTTMsg();
        BigDecimal sellToCustPk = getSellToCustPkByAcctNumForUpd(pMsg.dsAcctNum.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.sellToCustPk, sellToCustPk);
        sellToCustTmsg = (SELL_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(sellToCustTmsg);
        if (sellToCustTmsg == null) {
            msgMap.addXxMsgId(NMZM0311E);
            return false;
        }
        String befRgtnStsCd = sellToCustTmsg.rgtnStsCd.getValue();
        if (PROCESS_MODE_CUST_UPD.equals(pMsg.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.rgtnStsCd, getRgtnStsForChng04(befRgtnStsCd, pMsg.rgtnStsCd.getValue(), pMsg.xxAcctInacRsnFlg.getValue()));
        }

        if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(sellToCustTmsg.rgtnStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctActvCustFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctActvCustFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.effThruDt, getAcctLvlEffThruDtForUpd(befRgtnStsCd, sellToCustTmsg.rgtnStsCd.getValue()
                , sellToCustTmsg.effFromDt.getValue(), sellToCustTmsg.effThruDt.getValue(), pMsg.slsDt.getValue()));
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.effFromDt, getAcctLvlEffFromDtForUpd(befRgtnStsCd, sellToCustTmsg.rgtnStsCd.getValue()
                , sellToCustTmsg.effFromDt.getValue(), pMsg.slsDt.getValue()));

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            // START 2017/09/25 J.Kim [QC#21202,MOD]
                LocInfoBean locInfoBean = locInfoMap.get(String.valueOf(i));

                locInfoBean.setSellToCustCd(sellToCustTmsg.sellToCustCd.getValue());
                locInfoBean.setCmpyPk(sellToCustTmsg.cmpyPk.getValue());

            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
            // END 2017/09/25 J.Kim [QC#21202,MOD]
                if (PROCESS_MODE_EXST_CUST_LOC.equals(pMsg.xxModeCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sellToCustTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
                    ZYPEZDItemValueSetter.setValue(sellToCustTmsg.locNum, getLocNum(glblCmpyCd));
                    ZYPEZDItemValueSetter.setValue(sellToCustTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
                } else {
                    ZYPEZDItemValueSetter.setValue(sellToCustTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
                }

                locInfoBean.setLocNum(sellToCustTmsg.locNum.getValue());
                locInfoBean.setPtyLocPk(sellToCustTmsg.ptyLocPk.getValue());
                locInfoBean.setCntyPk(sellToCustTmsg.cntyPk.getValue());
                locInfoBean.setPrinCustPk(exstPrinCustPk);
                locInfoMap.put(String.valueOf(i), locInfoBean);
            }
        }

        sellToCustSetParam(pMsg, sellToCustTmsg);

        S21ApiTBLAccessor.update(sellToCustTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(sellToCustTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0257E);
            return false;
        }

        return true;
    }

    /**
     * SELL_TO_CUST set parameter for common
     * @param pMsg NMZC001001PMsg
     * @param dsAcctProsTmsg SELL_TO_CUSTTMsg
     */
    private void sellToCustSetParam(NMZC001001PMsg pMsg, SELL_TO_CUSTTMsg sellToCustTmsg) {

        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctTpCd, DS_ACCT_TP.CUSTOMER);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctItrlFlg, pMsg.dsAcctItrlFlg.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctClsCd, pMsg.dsAcctClsCd.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctDlrCd, pMsg.dsAcctDlrCd.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctLegalNm, pMsg.dsAcctLegalNm.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dbaNm, pMsg.dbaNm.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.locGrpCd, LOC_GRP.CUSTOMER);
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.bizRelnTpCd, getBIzRelnTpCd(pMsg.dsAcctItrlFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.coaAfflCd, pMsg.coaAfflCd.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctUrl, pMsg.dsAcctUrl.getValue());

        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.coaChCd, pMsg.coaChCd.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctInacRsnCd, pMsg.dsAcctInacRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.faxNum, linePrm.faxNum.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.telNum, linePrm.telNum.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.geoCd, linePrm.geoCd.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.locNm, linePrm.locNm.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.addlLocNm, linePrm.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.glnNum, linePrm.glnNum.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dunsNum, linePrm.dunsNum);
                }
            }
        }
        if (!isPrinCustLocInParamList(pMsg)) {
            if (isNewAcctMode(pMsg) || isExstPrinCustLocInParamList(pMsg)) {
                sellToCustTmsg.firstLineAddr.clear();
                sellToCustTmsg.scdLineAddr.clear();
                sellToCustTmsg.thirdLineAddr.clear();
                sellToCustTmsg.frthLineAddr.clear();
                sellToCustTmsg.ctyAddr.clear();
                sellToCustTmsg.provNm.clear();
                sellToCustTmsg.stCd.clear();
                sellToCustTmsg.postCd.clear();
                sellToCustTmsg.ctryCd.clear();

                sellToCustTmsg.locNum.clear();
                sellToCustTmsg.ptyLocPk.clear();
                sellToCustTmsg.prinCustPk.clear();
                sellToCustTmsg.cntyPk.clear();
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctDunsInfoFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctDunsNum, pMsg.dsAcctDunsNum.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctDunsNm, pMsg.dsAcctDunsNm.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctAltNm, pMsg.dsAcctAltNm.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsUltDunsNum, pMsg.dsUltDunsNum.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsLocEmpNum, pMsg.dsLocEmpNum.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsLocRevAmt, pMsg.dsLocRevAmt.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsLastUpdDunsDt, pMsg.dsLastUpdDunsDt.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsCustSicCd, pMsg.dsCustSicCd.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsCustSicDescTxt, pMsg.dsCustSicDescTxt.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsXtrnlRefTxt, pMsg.dsXtrnlRefTxt.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsDataSrcTxt, pMsg.dsDataSrcTxt.getValue());
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctCltFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.arStmtFlg, pMsg.arStmtFlg.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsCltAcctStsCd, pMsg.dsCltAcctStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.lateFeeFlg, pMsg.lateFeeFlg.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.lateFeeAmt, pMsg.lateFeeAmt.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.cltCustTpCd, pMsg.cltCustTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.arStmtIssDay, pMsg.arStmtIssDay.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dunFlg, pMsg.dunFlg.getValue());
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctTaxFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsCustTaxCd, pMsg.dsCustTaxCd.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsTaxPrntTpCd, pMsg.dsTaxPrntTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsCustTaxCalcCd, pMsg.dsCustTaxCalcCd.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsTaxGrpExemCd, pMsg.dsTaxGrpExemCd.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsTaxExemFlg, pMsg.dsTaxExemFlg.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsExemExprDt, pMsg.dsExemExprDt.getValue());
        }
        if (!ZYPCommonFunc.hasValue(sellToCustTmsg.dsAcctItrlFlg)) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsAcctItrlFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(sellToCustTmsg.arStmtFlg)) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.arStmtFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(sellToCustTmsg.lateFeeFlg)) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.lateFeeFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(sellToCustTmsg.dunFlg)) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dunFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(sellToCustTmsg.dsTaxExemFlg)) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
        }

        // START 2018/04/19 H.Ikeda [QC#23882, ADD]
        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
        // END   2018/04/19 H.Ikeda [QC#23882, ADD]

        ZYPEZDItemValueSetter.setValue(sellToCustTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));

        // 2023/11/06 QC#61924 Add Start
        if (!ZYPCommonFunc.hasValue(sellToCustTmsg.deacNewTrxFlg)) {
            ZYPEZDItemValueSetter.setValue(sellToCustTmsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2023/11/06 QC#61924 Add End
    }

    /**
     * CMPY Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean cmpyCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        LocInfoBean locInfoBean = locInfoMap.get("0");
        CMPYTMsg cmpyTmsg = new CMPYTMsg();

        ZYPEZDItemValueSetter.setValue(cmpyTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmpyTmsg.cmpyPk, locInfoBean.getCmpyPk());
        ZYPEZDItemValueSetter.setValue(cmpyTmsg.cmpyNm, S21StringUtil.subStringByLength(pMsg.dsAcctNm.getValue(), 0, LEN_CMPY_NM));
        ZYPEZDItemValueSetter.setValue(cmpyTmsg.dbaNm, pMsg.dbaNm.getValue());

        S21ApiTBLAccessor.create(cmpyTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmpyTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0232E);
            return false;
        }

        return true;
    }

    /**
     * CMPY Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean cmpyUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        CMPYTMsg cmpyTmsg = new CMPYTMsg();
        LocInfoBean locInfoBean = locInfoMap.get("0");
        BigDecimal cmpyPk = locInfoBean.getCmpyPk();

        ZYPEZDItemValueSetter.setValue(cmpyTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmpyTmsg.cmpyPk, cmpyPk);

        cmpyTmsg = (CMPYTMsg) EZDTBLAccessor.findByKeyForUpdate(cmpyTmsg);
        if (cmpyTmsg == null) {
            msgMap.addXxMsgId(NMZM0312E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cmpyTmsg.dbaNm, pMsg.dbaNm.getValue());

        S21ApiTBLAccessor.update(cmpyTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmpyTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0258E);
            return false;
        }

        return true;
    }

    /**
     * ACCT_LOC Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean acctLocCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        ACCT_LOCTMsg acctLocTmsg = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            acctLocTmsg = new ACCT_LOCTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));

            ZYPEZDItemValueSetter.setValue(acctLocTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(acctLocTmsg.acctLocPk, getAcctLocPk());
            ZYPEZDItemValueSetter.setValue(acctLocTmsg.dsAcctNum, locInfoBean.getSellToCustCd());
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg_NW.getValue())) {
                ZYPEZDItemValueSetter.setValue(acctLocTmsg.locNum, locInfoBean.getLocNum());
                ZYPEZDItemValueSetter.setValue(acctLocTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
            } else {
                ZYPEZDItemValueSetter.setValue(acctLocTmsg.locNum, getLocNum(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(acctLocTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
                locInfoBean.setLocNum(acctLocTmsg.locNum.getValue());
                locInfoBean.setPtyLocPk(acctLocTmsg.ptyLocPk.getValue());
                locInfoMap.put(String.valueOf(i), locInfoBean);
            }
            if (ZYPCommonFunc.hasValue(linePrm.effFromDt)) {
                ZYPEZDItemValueSetter.setValue(acctLocTmsg.effFromDt, linePrm.effFromDt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(acctLocTmsg.effFromDt, pMsg.slsDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(linePrm.effThruDt)) {
                ZYPEZDItemValueSetter.setValue(acctLocTmsg.effThruDt, linePrm.effThruDt.getValue());
            }
            setLocLvlRgtnStsForCrat(pMsg.slsDt.getValue(), linePrm.effFromDt.getValue(), linePrm.actvFlg.getValue(), acctLocTmsg.rgtnStsCd);

            S21ApiTBLAccessor.create(acctLocTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(acctLocTmsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0233E);
                return false;
            }
        }
        return true;

    }

    /**
     * ACCT_LOC Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean acctLocUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        LocInfoBean locInfoBean = null;
        BigDecimal acctLocPk = null;
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            ACCT_LOCTMsg acctLocTmsg = new ACCT_LOCTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            acctLocPk = getAcctLocPkForUpd(linePrm.locNum.getValue(), glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(acctLocTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(acctLocTmsg.acctLocPk, acctLocPk);

            acctLocTmsg = (ACCT_LOCTMsg) EZDTBLAccessor.findByKeyForUpdate(acctLocTmsg);
            if (acctLocTmsg == null) {
                msgMap.addXxMsgId(NMZM0313E);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(acctLocTmsg.effFromDt, setUpdateParam(acctLocTmsg.effFromDt.getValue(), linePrm.effFromDt.getValue()));

            ZYPEZDItemValueSetter.setValue(acctLocTmsg.effThruDt, linePrm.effThruDt.getValue());
            setLocLvlRgtnStsForUpd(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue()
                    , linePrm.effFromDt.getValue(), linePrm.effThruDt.getValue(), linePrm.actvFlg.getValue()
                    , acctLocTmsg.effFromDt, acctLocTmsg.effThruDt, acctLocTmsg.rgtnStsCd);

            S21ApiTBLAccessor.update(acctLocTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(acctLocTmsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0259E);
                return false;
            }
            locInfoBean.setLocNum(acctLocTmsg.locNum.getValue());
            locInfoBean.setPtyLocPk(acctLocTmsg.ptyLocPk.getValue());
            locInfoMap.put(String.valueOf(i), locInfoBean);

        }
        return true;
    }

    /**
     * PTY_LOC_WRK Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean ptyLocWrkCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        PTY_LOC_WRKTMsg ptyLocWrkTmsg = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            ptyLocWrkTmsg = new PTY_LOC_WRKTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));

            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.cmpyPk, locInfoBean.getCmpyPk());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ctyAddr, linePrm.ctyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.provNm, linePrm.provNm.getValue());
            if (ZYPCommonFunc.hasValue(locInfoBean.getCntyPk())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.cntyPk, locInfoBean.getCntyPk());
            } else {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
            }
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.stCd, linePrm.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.postCd, linePrm.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ctryCd, linePrm.ctryCd.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.locNum, locInfoBean.getLocNum());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.addlLocNm, linePrm.addlLocNm.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.glnNum, linePrm.glnNum.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dbaNm, pMsg.dbaNm.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.telNum, linePrm.telNum.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.faxNum, linePrm.faxNum.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.geoCd, linePrm.geoCd.getValue());
            if (ZYPCommonFunc.hasValue(linePrm.effFromDt)) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.effFromDt, linePrm.effFromDt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.effFromDt, pMsg.slsDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(linePrm.effThruDt)) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.effThruDt, linePrm.effThruDt.getValue());
            }
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.apvlStsCd, APVL_STS.APPROVED);
            setLocLvlRgtnStsForCrat(pMsg.slsDt.getValue(), linePrm.effFromDt.getValue(), linePrm.actvFlg.getValue(), ptyLocWrkTmsg.rgtnStsCd);
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));

            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDplUpdateFlg())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dplStsCd, locInfoBean.getDplStsCd());
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dplRsnTxt, locInfoBean.getDplRsnTxt());
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dplRspDtTmTs, locInfoBean.getDplRspDtTmTs());
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.embgoFlg, locInfoBean.getEmbgoFlg());
            }
            if (!ZYPCommonFunc.hasValue(ptyLocWrkTmsg.embgoFlg)) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.embgoFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsNum, linePrm.dunsNum);
            }

            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLocNm, linePrm.locNm.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.lineBizTpCd, linePrm.lineBizTpCd.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsTradeStyleNm, linePrm.dunsTradeStyleNm);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsActvCd, linePrm.dunsActvCd);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsLineAddr, linePrm.dunsLineAddr);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsCustSicCd, linePrm.dsCustSicCd);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsCustSicDescTxt, linePrm.dsCustSicDescTxt);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsBizNm, linePrm.dunsBizNm);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLastRcvDunsTxt, linePrm.dsLastRcvDunsTxt);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLastRcvDunsDt, linePrm.dsLastRcvDunsDt);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsSendCnt, linePrm.dunsSendCnt);

                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsUltDunsNum, linePrm.dsUltDunsNum);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.hqDunsNum, linePrm.hqDunsNum);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsPrntDunsNum, linePrm.dsPrntDunsNum);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLocEmpNum, linePrm.dsLocEmpNum);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLocRevAmt, linePrm.dsLocRevAmt);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLastUpdDunsDt, linePrm.dsLastUpdDunsDt);
            }

            locInfoBean.setCntyPk(ptyLocWrkTmsg.cntyPk.getValue());
            locInfoMap.put(String.valueOf(i), locInfoBean);

            S21ApiTBLAccessor.create(ptyLocWrkTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ptyLocWrkTmsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0234E);
                return false;
            }
        }
        return true;
    }

    private boolean ptyLocWrkUpdate_ExstCustLocMode(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd, LocInfoBean locInfoBean) {
        PTY_LOC_WRKTMsg ptyLocWrkTmsg = new PTY_LOC_WRKTMsg();
        BigDecimal ptyLocPk = locInfoBean.getPtyLocPk();
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ptyLocPk, ptyLocPk);

        ptyLocWrkTmsg = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(ptyLocWrkTmsg);
        if (ptyLocWrkTmsg == null) {
            msgMap.addXxMsgId(NMZM0314E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dbaNm, pMsg.dbaNm.getValue());

        // From DSTable
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());

        S21ApiTBLAccessor.update(ptyLocWrkTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ptyLocWrkTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0260E);
            return false;
        }
        return true;
    }

    /**
     * PTY_LOC_WRK Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean ptyLocWrkUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        LocInfoBean locInfoBean = null;
        BigDecimal ptyLocPk = null;
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            PTY_LOC_WRKTMsg ptyLocWrkTmsg = new PTY_LOC_WRKTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            ptyLocPk = locInfoBean.getPtyLocPk();
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ptyLocPk, ptyLocPk);

            ptyLocWrkTmsg = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(ptyLocWrkTmsg);
            if (ptyLocWrkTmsg == null) {
                msgMap.addXxMsgId(NMZM0314E);
                return false;
            }
            if (!isEquals(ptyLocWrkTmsg.ctryCd.getValue(), linePrm.ctryCd.getValue()) || !isEquals(ptyLocWrkTmsg.firstLineAddr.getValue(), linePrm.firstLineAddr.getValue())
                    || !isEquals(ptyLocWrkTmsg.scdLineAddr.getValue(), linePrm.scdLineAddr.getValue()) || !isEquals(ptyLocWrkTmsg.thirdLineAddr.getValue(), linePrm.thirdLineAddr.getValue())
                    || !isEquals(ptyLocWrkTmsg.frthLineAddr.getValue(), linePrm.frthLineAddr.getValue()) || !isEquals(ptyLocWrkTmsg.ctyAddr.getValue(), linePrm.ctyAddr.getValue())
                    || !isEquals(ptyLocWrkTmsg.stCd.getValue(), linePrm.stCd.getValue()) || !isEquals(ptyLocWrkTmsg.postCd.getValue(), linePrm.postCd.getValue())
                    || !isEquals(ptyLocWrkTmsg.cntyPk.getValue(), getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd))) {

                locInfoBean.setDunsAddrChngTpCd(ZYPConstant.FLG_ON_Y);
            }
            if (!isEquals(ptyLocWrkTmsg.telNum.getValue(), linePrm.telNum.getValue())) {
                locInfoBean.setDunsTelChngTpCd(ZYPConstant.FLG_ON_Y);

            }

            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.cmpyPk, locInfoBean.getCmpyPk());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ctyAddr, linePrm.ctyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.provNm, linePrm.provNm.getValue());
            if (ZYPCommonFunc.hasValue(locInfoBean.getCntyPk())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.cntyPk, locInfoBean.getCntyPk());
            } else {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
            }
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.stCd, linePrm.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.postCd, linePrm.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ctryCd, linePrm.ctryCd.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.locNum, locInfoBean.getLocNum());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.addlLocNm, linePrm.addlLocNm.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.glnNum, linePrm.glnNum.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dbaNm, pMsg.dbaNm.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.telNum, linePrm.telNum.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.faxNum, linePrm.faxNum.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.geoCd, linePrm.geoCd.getValue());
            if (ZYPCommonFunc.hasValue(linePrm.effFromDt)) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.effFromDt, linePrm.effFromDt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.effFromDt, pMsg.slsDt.getValue());
            }
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.effThruDt, linePrm.effThruDt.getValue());
            setLocLvlRgtnStsForUpd(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue()
                                    , linePrm.effFromDt.getValue(), linePrm.effThruDt.getValue(), linePrm.actvFlg.getValue()
                                    , ptyLocWrkTmsg.effFromDt, ptyLocWrkTmsg.effThruDt, ptyLocWrkTmsg.rgtnStsCd);

            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.apvlStsCd, APVL_STS.APPROVED);

            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDplUpdateFlg())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dplStsCd, locInfoBean.getDplStsCd());
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dplRsnTxt, locInfoBean.getDplRsnTxt());
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dplRspDtTmTs, locInfoBean.getDplRspDtTmTs());
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.embgoFlg, locInfoBean.getEmbgoFlg());
            }
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsNum, linePrm.dunsNum);
            }

            // From DSTable
            if (!isEquals(ptyLocWrkTmsg.dsAcctNm.getValue(), pMsg.dsAcctNm.getValue())) {
                locInfoBean.setDunsAcctNmChngTpCd(ZYPConstant.FLG_ON_Y);

            }
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLocNm, linePrm.locNm.getValue());
            if (ZYPCommonFunc.hasValue(linePrm.lineBizTpCd)) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.lineBizTpCd, linePrm.lineBizTpCd.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsTradeStyleNm, linePrm.dunsTradeStyleNm);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsActvCd, linePrm.dunsActvCd);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsLineAddr, linePrm.dunsLineAddr);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsCustSicCd, linePrm.dsCustSicCd);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsCustSicDescTxt, linePrm.dsCustSicDescTxt);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsBizNm, linePrm.dunsBizNm);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLastRcvDunsTxt, linePrm.dsLastRcvDunsTxt);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLastRcvDunsDt, linePrm.dsLastRcvDunsDt);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsSendCnt, linePrm.dunsSendCnt);

                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsUltDunsNum, linePrm.dsUltDunsNum);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.hqDunsNum, linePrm.hqDunsNum);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsPrntDunsNum, linePrm.dsPrntDunsNum);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLocEmpNum, linePrm.dsLocEmpNum);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLocRevAmt, linePrm.dsLocRevAmt);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dsLastUpdDunsDt, linePrm.dsLastUpdDunsDt);
            }
            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDunsAcctNmChngTpCd())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsAcctNmChngTpCd, DUNS_CHNG_TP.UPDATED);
            }
            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDunsAddrChngTpCd())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsAddrChngTpCd, DUNS_CHNG_TP.UPDATED);
            }
            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDunsTelChngTpCd())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.dunsTelChngTpCd, DUNS_CHNG_TP.UPDATED);
            }

            S21ApiTBLAccessor.update(ptyLocWrkTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ptyLocWrkTmsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0260E);
                return false;
            }
            locInfoBean.setCntyPk(ptyLocWrkTmsg.cntyPk.getValue());
            locInfoMap.put(String.valueOf(i), locInfoBean);

        }
        return true;
    }

    /**
     * PROS_PTY_LOC_WRK Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean prosPtyLocWrkCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        PROS_PTY_LOC_WRKTMsg prosPtyLocWrkTmsg = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            prosPtyLocWrkTmsg = new PROS_PTY_LOC_WRKTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));

            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.cmpyPk, locInfoBean.getCmpyPk());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.ctyAddr, linePrm.ctyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.provNm, linePrm.provNm.getValue());
            if (ZYPCommonFunc.hasValue(locInfoBean.getCntyPk())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.cntyPk, locInfoBean.getCntyPk());
            } else {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
            }
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.stCd, linePrm.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.postCd, linePrm.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.ctryCd, linePrm.ctryCd.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.locNum, locInfoBean.getLocNum());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.addlLocNm, linePrm.addlLocNm.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.glnNum, linePrm.glnNum.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dbaNm, pMsg.dbaNm.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.telNum, linePrm.telNum.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.faxNum, linePrm.faxNum.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.geoCd, linePrm.geoCd.getValue());
            if (ZYPCommonFunc.hasValue(linePrm.effFromDt)) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.effFromDt, linePrm.effFromDt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.effFromDt, pMsg.slsDt.getValue());
            }
            if (ZYPCommonFunc.hasValue(linePrm.effThruDt)) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.effThruDt, linePrm.effThruDt.getValue());
            }
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.apvlStsCd, APVL_STS.APPROVED);
            setLocLvlRgtnStsForCrat(pMsg.slsDt.getValue(), linePrm.effFromDt.getValue(), linePrm.actvFlg.getValue(), prosPtyLocWrkTmsg.rgtnStsCd);
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));

            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDplUpdateFlg())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dplStsCd, locInfoBean.getDplStsCd());
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dplRsnTxt, locInfoBean.getDplRsnTxt());
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dplRspDtTmTs, locInfoBean.getDplRspDtTmTs());
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.embgoFlg, locInfoBean.getEmbgoFlg());
            }
            if (!ZYPCommonFunc.hasValue(prosPtyLocWrkTmsg.embgoFlg)) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.embgoFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsNum, linePrm.dunsNum);
            }

            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLocNm, linePrm.locNm.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.lineBizTpCd, linePrm.lineBizTpCd.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsTradeStyleNm, linePrm.dunsTradeStyleNm);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsActvCd, linePrm.dunsActvCd);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsLineAddr, linePrm.dunsLineAddr);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsCustSicCd, linePrm.dsCustSicCd);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsCustSicDescTxt, linePrm.dsCustSicDescTxt);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsBizNm, linePrm.dunsBizNm);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLastRcvDunsTxt, linePrm.dsLastRcvDunsTxt);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLastRcvDunsDt, linePrm.dsLastRcvDunsDt);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsSendCnt, linePrm.dunsSendCnt);

                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsUltDunsNum, linePrm.dsUltDunsNum);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.hqDunsNum, linePrm.hqDunsNum);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsPrntDunsNum, linePrm.dsPrntDunsNum);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLocEmpNum, linePrm.dsLocEmpNum);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLocRevAmt, linePrm.dsLocRevAmt);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLastUpdDunsDt, linePrm.dsLastUpdDunsDt);
            }

            locInfoBean.setCntyPk(prosPtyLocWrkTmsg.cntyPk.getValue());
            locInfoMap.put(String.valueOf(i), locInfoBean);

            S21ApiTBLAccessor.create(prosPtyLocWrkTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prosPtyLocWrkTmsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0234E);
                return false;
            }
        }
        return true;
    }

    /**
     * PROS_PTY_LOC_WRK Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean prosPtyLocWrkUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        LocInfoBean locInfoBean = null;
        BigDecimal ptyLocPk = null;
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            PROS_PTY_LOC_WRKTMsg prosPtyLocWrkTmsg = new PROS_PTY_LOC_WRKTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            ptyLocPk = locInfoBean.getPtyLocPk();
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.ptyLocPk, ptyLocPk);

            prosPtyLocWrkTmsg = (PROS_PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(prosPtyLocWrkTmsg);
            if (prosPtyLocWrkTmsg == null) {
                msgMap.addXxMsgId(NMZM0314E);
                return false;
            }
            if (!isEquals(prosPtyLocWrkTmsg.ctryCd.getValue(), linePrm.ctryCd.getValue()) || !isEquals(prosPtyLocWrkTmsg.firstLineAddr.getValue(), linePrm.firstLineAddr.getValue())
                    || !isEquals(prosPtyLocWrkTmsg.scdLineAddr.getValue(), linePrm.scdLineAddr.getValue()) || !isEquals(prosPtyLocWrkTmsg.thirdLineAddr.getValue(), linePrm.thirdLineAddr.getValue())
                    || !isEquals(prosPtyLocWrkTmsg.frthLineAddr.getValue(), linePrm.frthLineAddr.getValue()) || !isEquals(prosPtyLocWrkTmsg.ctyAddr.getValue(), linePrm.ctyAddr.getValue())
                    || !isEquals(prosPtyLocWrkTmsg.stCd.getValue(), linePrm.stCd.getValue()) || !isEquals(prosPtyLocWrkTmsg.postCd.getValue(), linePrm.postCd.getValue())
                    || !isEquals(prosPtyLocWrkTmsg.cntyPk.getValue(), getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd))) {

                locInfoBean.setDunsAddrChngTpCd(ZYPConstant.FLG_ON_Y);
            }
            if (!isEquals(prosPtyLocWrkTmsg.telNum.getValue(), linePrm.telNum.getValue())) {
                locInfoBean.setDunsTelChngTpCd(ZYPConstant.FLG_ON_Y);

            }

            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.cmpyPk, locInfoBean.getCmpyPk());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.ctyAddr, linePrm.ctyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.provNm, linePrm.provNm.getValue());
            if (ZYPCommonFunc.hasValue(locInfoBean.getCntyPk())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.cntyPk, locInfoBean.getCntyPk());
            } else {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
            }
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.stCd, linePrm.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.postCd, linePrm.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.ctryCd, linePrm.ctryCd.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.locNum, locInfoBean.getLocNum());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.addlLocNm, linePrm.addlLocNm.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.glnNum, linePrm.glnNum.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dbaNm, pMsg.dbaNm.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.telNum, linePrm.telNum.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.faxNum, linePrm.faxNum.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.geoCd, linePrm.geoCd.getValue());
            if (ZYPCommonFunc.hasValue(linePrm.effFromDt)) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.effFromDt, linePrm.effFromDt.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.effFromDt, pMsg.slsDt.getValue());
            }
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.effThruDt, linePrm.effThruDt.getValue());
            setLocLvlRgtnStsForUpd(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue()
                                    , linePrm.effFromDt.getValue(), linePrm.effThruDt.getValue(), linePrm.actvFlg.getValue()
                                    , prosPtyLocWrkTmsg.effFromDt, prosPtyLocWrkTmsg.effThruDt, prosPtyLocWrkTmsg.rgtnStsCd);

            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.apvlStsCd, APVL_STS.APPROVED);

            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDplUpdateFlg())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dplStsCd, locInfoBean.getDplStsCd());
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dplRsnTxt, locInfoBean.getDplRsnTxt());
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dplRspDtTmTs, locInfoBean.getDplRspDtTmTs());
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.embgoFlg, locInfoBean.getEmbgoFlg());
            }
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsNum, linePrm.dunsNum);
            }

            // From DSTable
            if (!isEquals(prosPtyLocWrkTmsg.dsAcctNm.getValue(), pMsg.dsAcctNm.getValue())) {
                locInfoBean.setDunsAcctNmChngTpCd(ZYPConstant.FLG_ON_Y);

            }
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
            ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLocNm, linePrm.locNm.getValue());
            if (ZYPCommonFunc.hasValue(linePrm.lineBizTpCd)) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.lineBizTpCd, linePrm.lineBizTpCd.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsTradeStyleNm, linePrm.dunsTradeStyleNm);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsActvCd, linePrm.dunsActvCd);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsLineAddr, linePrm.dunsLineAddr);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsCustSicCd, linePrm.dsCustSicCd);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsCustSicDescTxt, linePrm.dsCustSicDescTxt);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsBizNm, linePrm.dunsBizNm);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLastRcvDunsTxt, linePrm.dsLastRcvDunsTxt);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLastRcvDunsDt, linePrm.dsLastRcvDunsDt);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsSendCnt, linePrm.dunsSendCnt);

                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsUltDunsNum, linePrm.dsUltDunsNum);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.hqDunsNum, linePrm.hqDunsNum);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsPrntDunsNum, linePrm.dsPrntDunsNum);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLocEmpNum, linePrm.dsLocEmpNum);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLocRevAmt, linePrm.dsLocRevAmt);
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dsLastUpdDunsDt, linePrm.dsLastUpdDunsDt);
            }
            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDunsAcctNmChngTpCd())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsAcctNmChngTpCd, DUNS_CHNG_TP.UPDATED);
            }
            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDunsAddrChngTpCd())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsAddrChngTpCd, DUNS_CHNG_TP.UPDATED);
            }
            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDunsTelChngTpCd())) {
                ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTmsg.dunsTelChngTpCd, DUNS_CHNG_TP.UPDATED);
            }

            S21ApiTBLAccessor.update(prosPtyLocWrkTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prosPtyLocWrkTmsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0260E);
                return false;
            }
            locInfoBean.setCntyPk(prosPtyLocWrkTmsg.cntyPk.getValue());
            locInfoMap.put(String.valueOf(i), locInfoBean);

        }
        return true;
    }

    /**
     * PRIN_CUST Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean prinCustCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        PRIN_CUSTTMsg prinCustTmsg = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            prinCustTmsg = new PRIN_CUSTTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg_NW.getValue())) {
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.prinCustPk, locInfoBean.getPrinCustPk());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.cmpyPk, locInfoBean.getCmpyPk());

                ZYPEZDItemValueSetter.setValue(prinCustTmsg.bizRelnTpCd, getBIzRelnTpCd(pMsg.dsAcctItrlFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dbaNm, pMsg.dbaNm.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dunsNum, pMsg.dsAcctDunsNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.rgtnStsCd, getRgtnSts(pMsg.xxModeCd.getValue()));
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.effFromDt, pMsg.slsDt.getValue());

                ZYPEZDItemValueSetter.setValue(prinCustTmsg.locRoleTpCd, LOC_ROLE_TP.CUSTOMER_PRINCIPAL);
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.locGrpCd, LOC_GRP.CUSTOMER);

                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsUltDunsNum, linePrm.dsUltDunsNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsPrntDunsNum, linePrm.dsPrntDunsNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsLocEmpNum, linePrm.dsLocEmpNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsLocRevAmt, linePrm.dsLocRevAmt.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsLastUpdDunsDt, linePrm.dsLastUpdDunsDt.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsInsdCtyLimitFlg, linePrm.dsInsdCtyLimitFlg.getValue());
                if (!ZYPCommonFunc.hasValue(prinCustTmsg.dsInsdCtyLimitFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
                }

                if (!ZYPCommonFunc.hasValue(prinCustTmsg.embgoFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.embgoFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsLocNm, linePrm.locNm.getValue());

                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.ctyAddr, linePrm.ctyAddr.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.provNm, linePrm.provNm.getValue());
                    if (ZYPCommonFunc.hasValue(locInfoBean.getCntyPk())) {
                        ZYPEZDItemValueSetter.setValue(prinCustTmsg.cntyPk, locInfoBean.getCntyPk());
                    } else {
                        ZYPEZDItemValueSetter.setValue(prinCustTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
                    }
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.stCd, linePrm.stCd.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.postCd, linePrm.postCd.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.ctryCd, linePrm.ctryCd.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.locNum, locInfoBean.getLocNum());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.ptyLocPk, locInfoBean.getPtyLocPk());

                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.addlLocNm, linePrm.addlLocNm.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.glnNum, linePrm.glnNum.getValue());

                    if (ZYPCommonFunc.hasValue(linePrm.effFromDt)) {
                        ZYPEZDItemValueSetter.setValue(prinCustTmsg.effFromDt, linePrm.effFromDt.getValue());
                    }
                    if (ZYPCommonFunc.hasValue(linePrm.effThruDt)) {
                        ZYPEZDItemValueSetter.setValue(prinCustTmsg.effThruDt, linePrm.effThruDt.getValue());
                    }
                    setLocLvlRgtnStsForCrat(pMsg.slsDt.getValue(), linePrm.effFromDt.getValue(), linePrm.actvFlg.getValue(), prinCustTmsg.rgtnStsCd);

                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.telNum, linePrm.telNum.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.faxNum, linePrm.faxNum.getValue());
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.geoCd, linePrm.geoCd.getValue());
                    if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(prinCustTmsg.dunsNum, linePrm.dunsNum);
                    }
                }

                S21ApiTBLAccessor.create(prinCustTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prinCustTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0236E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * PRIN_CUST Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean prinCustUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        PRIN_CUSTTMsg prinCustTmsg = new PRIN_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prinCustTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prinCustTmsg.prinCustPk, exstPrinCustPk);
        prinCustTmsg = (PRIN_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(prinCustTmsg);
        if (prinCustTmsg == null) {
            msgMap.addXxMsgId(NMZM0316E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(prinCustTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
        ZYPEZDItemValueSetter.setValue(prinCustTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTmsg.dbaNm, pMsg.dbaNm.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTmsg.bizRelnTpCd, getBIzRelnTpCd(pMsg.dsAcctItrlFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(prinCustTmsg.dunsNum, pMsg.dsAcctDunsNum.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            LocInfoBean locInfoBean = locInfoMap.get(String.valueOf(i));
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.locNum, locInfoBean.getLocNum());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.addlLocNm, linePrm.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.glnNum, linePrm.glnNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.effFromDt, setUpdateParam(prinCustTmsg.effFromDt.getValue(), linePrm.effFromDt.getValue()));
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.effThruDt, setUpdateParam(prinCustTmsg.effThruDt.getValue(), linePrm.effThruDt.getValue()));

                setLocLvlRgtnStsForUpd(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue()
                        , linePrm.effFromDt.getValue(), linePrm.effThruDt.getValue(), linePrm.actvFlg.getValue()
                        , prinCustTmsg.effFromDt, prinCustTmsg.effThruDt, prinCustTmsg.rgtnStsCd);

                ZYPEZDItemValueSetter.setValue(prinCustTmsg.telNum, linePrm.telNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.faxNum, linePrm.faxNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.geoCd, linePrm.geoCd.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.dunsNum, linePrm.dunsNum);
                }
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsUltDunsNum, linePrm.dsUltDunsNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsPrntDunsNum, linePrm.dsPrntDunsNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsLocEmpNum, linePrm.dsLocEmpNum.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsLocRevAmt, linePrm.dsLocRevAmt.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsLastUpdDunsDt, linePrm.dsLastUpdDunsDt.getValue());
                ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsLocNm, linePrm.locNm.getValue());
                if (ZYPCommonFunc.hasValue(linePrm.dsInsdCtyLimitFlg)) {
                    ZYPEZDItemValueSetter.setValue(prinCustTmsg.dsInsdCtyLimitFlg, linePrm.dsInsdCtyLimitFlg.getValue());
                }
            }
        }
        if (!isPrinCustLocInParamList(pMsg) && isExstPrinCustLocInParamList(pMsg)) {
            prinCustTmsg.firstLineAddr.clear();
            prinCustTmsg.scdLineAddr.clear();
            prinCustTmsg.thirdLineAddr.clear();
            prinCustTmsg.frthLineAddr.clear();
            prinCustTmsg.ctyAddr.clear();
            prinCustTmsg.provNm.clear();
            prinCustTmsg.stCd.clear();
            prinCustTmsg.postCd.clear();
            prinCustTmsg.ctryCd.clear();
            prinCustTmsg.ptyLocPk.clear();
            prinCustTmsg.locNum.clear();
        }
        S21ApiTBLAccessor.update(prinCustTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prinCustTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0262E);
            return false;
        }
        return true;
    }

    /**
     * LOC_USG Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean locUsgCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        LOC_USGTMsg locUsgTmsg = null;
        NMZC001002PMsg linePrm = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));

            if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())) {
                locUsgTmsg = new LOC_USGTMsg();
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.locUsgPk, getLogUsgPk());
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.locGrpCd, LOC_GRP.CUSTOMER);
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.locRoleTpCd, LOC_ROLE_TP.BILL_TO);

                S21ApiTBLAccessor.create(locUsgTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(locUsgTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0238E);
                    return false;
                }
            }

            if (ZYPConstant.FLG_ON_Y.equals(linePrm.shipToCustFlg.getValue())) {
                locUsgTmsg = new LOC_USGTMsg();
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.locUsgPk, getLogUsgPk());
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.locGrpCd, LOC_GRP.CUSTOMER);
                ZYPEZDItemValueSetter.setValue(locUsgTmsg.locRoleTpCd, LOC_ROLE_TP.SHIP_TO);

                S21ApiTBLAccessor.create(locUsgTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(locUsgTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0238E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * BILL_TO_CUST Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean billToCustCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        BILL_TO_CUSTTMsg billToCustTmsg = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            billToCustTmsg = new BILL_TO_CUSTTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));

            if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.prinCustPk, locInfoBean.getPrinCustPk());
                }
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.billToCustCd, getBillToCustCd(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.billToCustPk, getBillToCustPk());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.cntyPk, locInfoBean.getCntyPk());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.locNum, locInfoBean.getLocNum());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.addlLocNm, linePrm.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.glnNum, linePrm.glnNum.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dbaNm, pMsg.dbaNm.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dunsNum, pMsg.dsAcctDunsNum.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.rgtnStsCd, getLocUsgLvlRgtnStsForCrat(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue(), linePrm.effFromDt_B.getValue()));
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.flPlnCmpyFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.openRmaFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.locRoleTpCd, LOC_ROLE_TP.BILL_TO);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.locGrpCd, LOC_GRP.CUSTOMER);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.effFromDt, setUpdateParam(billToCustTmsg.effFromDt.getValue(), linePrm.effFromDt_B.getValue()));
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.effThruDt, setUpdateParam(billToCustTmsg.effThruDt.getValue(), linePrm.effThruDt_B.getValue()));
                // Del Start 2019/04/16 QC#31114
                //String remId = linePrm.remId_B.getValue();
                //if (!ZYPCommonFunc.hasValue(remId)) {
                //    remId = getDefRemId(pMsg, linePrm.stCd.getValue());
                //    if (!ZYPCommonFunc.hasValue(remId)) {
                //        msgMap.addXxMsgId(NMAM8490E);
                //        return false;
                //    }
                //}
                //ZYPEZDItemValueSetter.setValue(billToCustTmsg.remId, remId);
                // Del End 2019/04/16 QC#31114
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.coaAfflCd, linePrm.coaAfflCd_B.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.coaChCd, linePrm.coaChCd_B.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.cmpyPk, locInfoBean.getCmpyPk());
                // Del Start 2019/04/16 QC#31114
                //ZYPEZDItemValueSetter.setValue(billToCustTmsg.arStmtFlg, linePrm.arStmtFlg.getValue());
                //if (!ZYPCommonFunc.hasValue(billToCustTmsg.arStmtFlg)) {
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.arStmtFlg, ZYPConstant.FLG_OFF_N);
                //}
                // Del End 2019/04/16 QC#31114
                
                // START 2018/04/19 H.Ikeda [QC#23882, ADD]
                // Del Start 2019/04/16 QC#31114
                //ZYPEZDItemValueSetter.setValue(billToCustTmsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
                // Del End 2019/04/16 QC#31114
                // END   2018/04/19 H.Ikeda [QC#23882, ADD]
                
                // Del Start 2019/04/16 QC#31114
                //ZYPEZDItemValueSetter.setValue(billToCustTmsg.arStmtIssDay, linePrm.arStmtIssDay.getValue());
                // Del End 2019/04/16 QC#31114
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.asnReqFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.printSccLbFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.printUccLbFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.printPltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.mixPltUccLbFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.nonAsnUccLbFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.mixPltAllwFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.faxNum, linePrm.faxNum.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.telNum, linePrm.telNum.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.geoCd, linePrm.geoCd.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.rcvAckReqFlg, ZYPConstant.FLG_OFF_N);

                if (!ZYPCommonFunc.hasValue(billToCustTmsg.embgoFlg)) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.embgoFlg, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dunsNum, linePrm.dunsNum);
                }
                setValueIfNotNull(billToCustTmsg.coaAfflCd, linePrm.coaAfflCd_B, pMsg.coaAfflCd);
                setValueIfNotNull(billToCustTmsg.coaChCd, linePrm.coaChCd_B, pMsg.coaChCd);

                locInfoBean.setBillToCustPk(billToCustTmsg.billToCustPk.getValue());
                locInfoBean.setBillToCustCd(billToCustTmsg.billToCustCd.getValue());

                // From DSTable
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsInsdCtyLimitFlg, linePrm.dsInsdCtyLimitFlg.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.sellToCustCd, locInfoBean.getSellToCustCd());

                ZYPEZDItemValueSetter.setValue(billToCustTmsg.primUsgFlg, linePrm.primUsgFlg_B.getValue());
                // Del Start 2019/04/16 QC#31114
                //if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocBillToTaxFlg_B.getValue())) {
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsCustTaxCd, linePrm.dsCustTaxCd_B.getValue());
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsTaxPrntTpCd, linePrm.dsTaxPrntTpCd_B.getValue());
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsCustTaxCalcCd, linePrm.dsCustTaxCalcCd_B.getValue());
                //}
                //if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocBillToCltInfoFlg.getValue())) {
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsCltAcctStsCd, linePrm.dsCltAcctStsCd.getValue());
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.lateFeeFlg, linePrm.lateFeeFlg.getValue());
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.lateFeeAmt, linePrm.lateFeeAmt.getValue());
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.cltCustTpCd, linePrm.cltCustTpCd.getValue());
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsTaxGrpExemCd, linePrm.dsTaxGrpExemCd_B.getValue());
                //}
                // Del End 2019/04/16 QC#31114

                ZYPEZDItemValueSetter.setValue(billToCustTmsg.lineBizTpCd, linePrm.lineBizTpCd.getValue());

                ZYPEZDItemValueSetter.setValue(billToCustTmsg.glblCmpyCd, glblCmpyCd);

                if (!ZYPCommonFunc.hasValue(billToCustTmsg.primUsgFlg)) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.primUsgFlg, ZYPConstant.FLG_OFF_N);
                }
                // Del Start 2019/04/16 QC#31114
                //if (!ZYPCommonFunc.hasValue(billToCustTmsg.lateFeeFlg)) {
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.lateFeeFlg, ZYPConstant.FLG_OFF_N);
                //}
                //if (!ZYPCommonFunc.hasValue(billToCustTmsg.dunFlg)) {
                //    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dunFlg, ZYPConstant.FLG_OFF_N);
                //}
                // Del End 2019/04/16 QC#31114
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);
                // Del Start 2019/04/16 QC#31114
                //ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
                // Del End 2019/04/16 QC#31114
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsLocNm, linePrm.locNm.getValue());

                // 2023/11/06 QC#61924 Add Start
                if (!ZYPCommonFunc.hasValue(billToCustTmsg.deacNewTrxFlg)) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                }
                // 2023/11/06 QC#61924 Add End

                // Add Start 2019/04/16 QC#31114
                setTemplateForFinancials(pMsg, billToCustTmsg);
                // Add End 2019/04/16 QC#31114

                locInfoMap.put(String.valueOf(i), locInfoBean);

                S21ApiTBLAccessor.create(billToCustTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(billToCustTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0239E);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean billToCustUpdate_ExstCustLocMode(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd, LocInfoBean locInfoBean, boolean isPrimBillToInParamList) {
        BILL_TO_CUSTTMsg billToCustTmsg = new BILL_TO_CUSTTMsg();
        BigDecimal billToCustPk = locInfoBean.getBillToCustPk();
        ZYPEZDItemValueSetter.setValue(billToCustTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(billToCustTmsg.billToCustPk, billToCustPk);

        billToCustTmsg = (BILL_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(billToCustTmsg);
        if (billToCustTmsg == null) {
            return true;
        }
        ZYPEZDItemValueSetter.setValue(billToCustTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
        ZYPEZDItemValueSetter.setValue(billToCustTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(billToCustTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(billToCustTmsg.dbaNm, pMsg.dbaNm.getValue());
        ZYPEZDItemValueSetter.setValue(billToCustTmsg.bizRelnTpCd, getBIzRelnTpCd(pMsg.dsAcctItrlFlg.getValue()));

        if (ZYPCommonFunc.hasValue(pMsg.coaAfflCd)) {
            ZYPEZDItemValueSetter.setValue(billToCustTmsg.coaAfflCd, pMsg.coaAfflCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(pMsg.coaChCd)) {
            if (ZYPCommonFunc.hasValue(this.oldAcctCoaChCd) && oldAcctCoaChCd.equals(billToCustTmsg.coaChCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.coaChCd, pMsg.coaChCd.getValue());
            }
        }

        // From 
        ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
        if (isPrimBillToInParamList) {
            if (ZYPConstant.FLG_ON_Y.equals(billToCustTmsg.primUsgFlg.getValue())) {
                billToCustTmsg.primUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
        }

        S21ApiTBLAccessor.update(billToCustTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(billToCustTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0264E);
            return false;
        }
        return true;
    }

    /**
     * BILL_TO_CUST Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean billToCustUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        BILL_TO_CUSTTMsg billToCustTmsg = null;
        LocInfoBean locInfoBean = null;
        BigDecimal billToCustPk = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            billToCustTmsg = new BILL_TO_CUSTTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())) {
                billToCustPk = getBillToCustPkForUpd(linePrm.locNum.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.billToCustPk, billToCustPk);

                billToCustTmsg = (BILL_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(billToCustTmsg);
                if (billToCustTmsg == null) {
                    msgMap.addXxMsgId(NMZM0318E);
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.cntyPk, locInfoBean.getCntyPk());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.locNum, locInfoBean.getLocNum());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.addlLocNm, linePrm.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.glnNum, linePrm.glnNum.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dbaNm, pMsg.dbaNm.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dunsNum, pMsg.dsAcctDunsNum.getValue());

                ZYPEZDItemValueSetter.setValue(billToCustTmsg.rgtnStsCd, getLocUsgLvlRgtnStsForUpd(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue(), linePrm.effFromDt_B.getValue(), linePrm.effThruDt_B.getValue()));
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.effFromDt, linePrm.effFromDt_B.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.effThruDt, linePrm.effThruDt_B.getValue());
                if (ZYPConstant.FLG_OFF_N.equals(linePrm.actvFlg.getValue())) {
                    setLocLvlRgtnStsForUpd(pMsg.xxModeCd.getValue()
                            , pMsg.slsDt.getValue(), linePrm.effFromDt_B.getValue(), linePrm.effThruDt_B.getValue(), linePrm.actvFlg.getValue()
                            , billToCustTmsg.effFromDt, billToCustTmsg.effThruDt, billToCustTmsg.rgtnStsCd);
                }
                if (ZYPCommonFunc.hasValue(linePrm.remId_B)) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.remId, linePrm.remId_B.getValue());
                }
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.bizRelnTpCd, getBIzRelnTpCd(pMsg.dsAcctItrlFlg.getValue()));
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.coaAfflCd, linePrm.coaAfflCd_B.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.coaChCd, linePrm.coaChCd_B.getValue());

                // Add Start 2019/04/16 QC#31114
                if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctCltFlg.getValue())) {
                    // Add End 2019/04/16 QC#31114
                    if (ZYPCommonFunc.hasValue(linePrm.arStmtFlg)) {
                        ZYPEZDItemValueSetter.setValue(billToCustTmsg.arStmtFlg, linePrm.arStmtFlg);
                    }
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.arStmtIssDay, linePrm.arStmtIssDay.getValue());
                    // Add Start 2019/04/16 QC#31114
                }
                // Add End 2019/04/16 QC#31114

                ZYPEZDItemValueSetter.setValue(billToCustTmsg.faxNum, linePrm.faxNum.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.telNum, linePrm.telNum.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.geoCd, linePrm.geoCd.getValue());

                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dunsNum, linePrm.dunsNum);
                }

                setValueIfNotNull(billToCustTmsg.coaAfflCd, linePrm.coaAfflCd_B, pMsg.coaAfflCd);
                setValueIfNotNull(billToCustTmsg.coaChCd, linePrm.coaChCd_B, pMsg.coaChCd);

                S21ApiTBLAccessor.update(billToCustTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(billToCustTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0264E);
                    return false;
                }

                locInfoBean.setBillToCustPk(billToCustTmsg.billToCustPk.getValue());
                locInfoBean.setBillToCustCd(billToCustTmsg.billToCustCd.getValue());

                // From DSTable
                if (ZYPCommonFunc.hasValue(linePrm.dsInsdCtyLimitFlg)) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsInsdCtyLimitFlg, linePrm.dsInsdCtyLimitFlg.getValue());
                }
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.primUsgFlg, linePrm.primUsgFlg_B.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocBillToTaxFlg_B.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsCustTaxCd, linePrm.dsCustTaxCd_B.getValue());
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsTaxPrntTpCd, linePrm.dsTaxPrntTpCd_B.getValue());
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsCustTaxCalcCd, linePrm.dsCustTaxCalcCd_B.getValue());
                }
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocBillToCltInfoFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsCltAcctStsCd, linePrm.dsCltAcctStsCd.getValue());
                    if (ZYPCommonFunc.hasValue(linePrm.lateFeeFlg)) {
                        ZYPEZDItemValueSetter.setValue(billToCustTmsg.lateFeeFlg, linePrm.lateFeeFlg.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.lateFeeAmt, linePrm.lateFeeAmt.getValue());
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.cltCustTpCd, linePrm.cltCustTpCd.getValue());
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsTaxGrpExemCd, linePrm.dsTaxGrpExemCd_B.getValue());
                }
                if (ZYPCommonFunc.hasValue(linePrm.lineBizTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTmsg.lineBizTpCd, linePrm.lineBizTpCd.getValue());
                }

                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    if (ZYPCommonFunc.hasValue(linePrm.dunFlg)) {
                        ZYPEZDItemValueSetter.setValue(billToCustTmsg.dunFlg, linePrm.dunFlg.getValue());
                    }
                }

                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTmsg.dsLocNm, linePrm.locNm.getValue());

                locInfoMap.put(String.valueOf(i), locInfoBean);
            }
        }
        return true;
    }

    /**
     * INV_RCPNT Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean invRcpntCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        INV_RCPNTTMsg invRcpntTmg = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));

            if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())) {
                invRcpntTmg = new INV_RCPNTTMsg();
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.custPk, locInfoBean.getBillToCustPk());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.custCd, locInfoBean.getBillToCustCd());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.cntyPk, locInfoBean.getCntyPk());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.locNum, locInfoBean.getLocNum());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.addlLocNm, linePrm.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.glnNum, linePrm.glnNum.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.dbaNm, pMsg.dbaNm.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.dunsNum, pMsg.dsAcctDunsNum.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.rgtnStsCd, getLocUsgLvlRgtnStsForCrat(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue(), linePrm.effFromDt_B.getValue()));
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.ptyLocPk, locInfoBean.getPtyLocPk());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.locRoleTpCd, LOC_ROLE_TP.BILL_TO);
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.locGrpCd, LOC_GRP.CUSTOMER);
                if (ZYPCommonFunc.hasValue(linePrm.effFromDt_B)) {
                    ZYPEZDItemValueSetter.setValue(invRcpntTmg.effFromDt, linePrm.effFromDt_B.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(invRcpntTmg.effFromDt, pMsg.slsDt.getValue());
                }
                if (ZYPCommonFunc.hasValue(linePrm.effThruDt_B)) {
                    ZYPEZDItemValueSetter.setValue(invRcpntTmg.effThruDt, linePrm.effThruDt_B.getValue());
                }
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.printDunsOnInvFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.cmpyPk, locInfoBean.getCmpyPk());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(invRcpntTmg.prinCustPk, locInfoBean.getPrinCustPk());
                }

                ZYPEZDItemValueSetter.setValue(invRcpntTmg.altInvRcpntFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.faxNum, linePrm.faxNum.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.telNum, linePrm.telNum.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(invRcpntTmg.dunsNum, linePrm.dunsNum);
                }

                // From DSTable
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.invRcpntCustCd, locInfoBean.getBillToCustCd());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.invRcpntDefFlg, ZYPConstant.FLG_ON_Y);

                S21ApiTBLAccessor.create(invRcpntTmg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invRcpntTmg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0241E);
                    return false;
                }

            }
        }
        return true;
    }

    private boolean invRcpntUpdate_ExstCustLocMode(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd, LocInfoBean locInfoBean) {
        INV_RCPNTTMsg invRcpntTmg = new INV_RCPNTTMsg();
        BigDecimal custPk = getCustPkForUpd(locInfoBean.getLocNum(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invRcpntTmg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invRcpntTmg.custPk, custPk);

        invRcpntTmg = (INV_RCPNTTMsg) EZDTBLAccessor.findByKeyForUpdate(invRcpntTmg);
        if (invRcpntTmg == null) {
            return true;
        }

        ZYPEZDItemValueSetter.setValue(invRcpntTmg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
        ZYPEZDItemValueSetter.setValue(invRcpntTmg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTmg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTmg.dbaNm, pMsg.dbaNm.getValue());

        S21ApiTBLAccessor.update(invRcpntTmg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invRcpntTmg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0266E);
            return false;
        }
        return true;
    }

    /**
     * INV_RCPNT Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean invRcpntUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        INV_RCPNTTMsg invRcpntTmg = null;
        LocInfoBean locInfoBean = null;
        BigDecimal custPk = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            invRcpntTmg = new INV_RCPNTTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())) {
                custPk = getCustPkForUpd(linePrm.locNum.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.custPk, custPk);

                invRcpntTmg = (INV_RCPNTTMsg) EZDTBLAccessor.findByKeyForUpdate(invRcpntTmg);
                if (invRcpntTmg == null) {
                    msgMap.addXxMsgId(NMZM0320E);
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.cntyPk, locInfoBean.getCntyPk());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.addlLocNm, linePrm.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.glnNum, linePrm.glnNum.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.dbaNm, pMsg.dbaNm.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.dunsNum, pMsg.dsAcctDunsNum.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.rgtnStsCd, getLocUsgLvlRgtnStsForUpd(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue(), linePrm.effFromDt_B.getValue(), linePrm.effThruDt_B.getValue()));
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.effFromDt, linePrm.effFromDt_B.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.effThruDt, linePrm.effThruDt_B.getValue());
                if (ZYPConstant.FLG_OFF_N.equals(linePrm.actvFlg.getValue())) {
                    setLocLvlRgtnStsForUpd(pMsg.xxModeCd.getValue()
                            , pMsg.slsDt.getValue(), linePrm.effFromDt_B.getValue(), linePrm.effThruDt_B.getValue(), linePrm.actvFlg.getValue()
                            , invRcpntTmg.effFromDt, invRcpntTmg.effThruDt, invRcpntTmg.rgtnStsCd);
                }
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.faxNum, linePrm.faxNum.getValue());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.telNum, linePrm.telNum.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(invRcpntTmg.dunsNum, linePrm.dunsNum);
                }

                // From DSTable
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.invRcpntCustCd, locInfoBean.getBillToCustCd());
                ZYPEZDItemValueSetter.setValue(invRcpntTmg.invRcpntDefFlg, ZYPConstant.FLG_ON_Y);

                S21ApiTBLAccessor.update(invRcpntTmg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invRcpntTmg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0266E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ALT_PAYER Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean altPayerCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        ALT_PAYERTMsg altPayerTmg = null;
        NMZC001002PMsg linePrm = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));

            if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())) {
                altPayerTmg = new ALT_PAYERTMsg();
                ZYPEZDItemValueSetter.setValue(altPayerTmg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(altPayerTmg.sellToCustCd, locInfoBean.getSellToCustCd());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.billToCustCd, locInfoBean.getBillToCustCd());

                ZYPEZDItemValueSetter.setValue(altPayerTmg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.cntyPk, locInfoBean.getCntyPk());

                ZYPEZDItemValueSetter.setValue(altPayerTmg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
                ZYPEZDItemValueSetter.setValue(altPayerTmg.dbaNm, pMsg.dbaNm.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.locNum, locInfoBean.getLocNum());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.telNum, linePrm.telNum.getValue());

                ZYPEZDItemValueSetter.setValue(altPayerTmg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                ZYPEZDItemValueSetter.setValue(altPayerTmg.flPlnCmpyFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(altPayerTmg.ptyLocPk, locInfoBean.getPtyLocPk());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.locRoleTpCd, LOC_ROLE_TP.BILL_TO);
                ZYPEZDItemValueSetter.setValue(altPayerTmg.locGrpCd, LOC_GRP.CUSTOMER);

                if (ZYPCommonFunc.hasValue(linePrm.effFromDt_B)) {
                    ZYPEZDItemValueSetter.setValue(altPayerTmg.effFromDt, linePrm.effFromDt_B.getValue());
                }
                if (ZYPCommonFunc.hasValue(linePrm.effThruDt_B)) {
                    ZYPEZDItemValueSetter.setValue(altPayerTmg.effThruDt, linePrm.effThruDt_B.getValue());
                }
                ZYPEZDItemValueSetter.setValue(altPayerTmg.rgtnStsCd, getLocUsgLvlRgtnStsForCrat(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue(), linePrm.effFromDt_B.getValue()));

                ZYPEZDItemValueSetter.setValue(altPayerTmg.cmpyPk, locInfoBean.getCmpyPk());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.prinCustPk, locInfoBean.getPrinCustPk());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(altPayerTmg.dunsNum, linePrm.dunsNum);
                }

                S21ApiTBLAccessor.create(altPayerTmg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(altPayerTmg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0243E);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean altPayerUpdate_ExstCustLocMode(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd, LocInfoBean locInfoBean) {
        ALT_PAYERTMsg altPayerTmg = new ALT_PAYERTMsg();
        ZYPEZDItemValueSetter.setValue(altPayerTmg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(altPayerTmg.sellToCustCd, pMsg.dsAcctNum.getValue());
        ZYPEZDItemValueSetter.setValue(altPayerTmg.billToCustCd, locInfoBean.getBillToCustCd());

        altPayerTmg = (ALT_PAYERTMsg) EZDTBLAccessor.findByKeyForUpdate(altPayerTmg);
        if (altPayerTmg == null) {
            return true;
        }

        ZYPEZDItemValueSetter.setValue(altPayerTmg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
        ZYPEZDItemValueSetter.setValue(altPayerTmg.dbaNm, pMsg.dbaNm.getValue());

        S21ApiTBLAccessor.update(altPayerTmg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(altPayerTmg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0267E);
            return false;
        }
        return true;
    }

    /**
     * ALT_PAYER Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean altPayerUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        ALT_PAYERTMsg altPayerTmg = null;
        NMZC001002PMsg linePrm = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            altPayerTmg = new ALT_PAYERTMsg();
            linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.billToCustFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(altPayerTmg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(altPayerTmg.sellToCustCd, pMsg.dsAcctNum.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.billToCustCd, locInfoBean.getBillToCustCd());

                altPayerTmg = (ALT_PAYERTMsg) EZDTBLAccessor.findByKeyForUpdate(altPayerTmg);
                if (altPayerTmg == null) {
                    msgMap.addXxMsgId(NMZM0321E);
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(altPayerTmg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.cntyPk, locInfoBean.getCntyPk());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.telNum, linePrm.telNum.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
                ZYPEZDItemValueSetter.setValue(altPayerTmg.dbaNm, pMsg.dbaNm.getValue());

                ZYPEZDItemValueSetter.setValue(altPayerTmg.effFromDt, linePrm.effFromDt_B.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTmg.effThruDt, linePrm.effThruDt_B.getValue());

                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(altPayerTmg.dunsNum, linePrm.dunsNum);
                }

                ZYPEZDItemValueSetter.setValue(altPayerTmg.rgtnStsCd, getLocUsgLvlRgtnStsForUpd(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue(), linePrm.effFromDt_B.getValue(), linePrm.effThruDt_B.getValue()));
                if (ZYPConstant.FLG_OFF_N.equals(linePrm.actvFlg.getValue())) {
                    setLocLvlRgtnStsForUpd(pMsg.xxModeCd.getValue()
                            , pMsg.slsDt.getValue(), linePrm.effFromDt_B.getValue(), linePrm.effThruDt_B.getValue(), linePrm.actvFlg.getValue()
                            , altPayerTmg.effFromDt, altPayerTmg.effThruDt, altPayerTmg.rgtnStsCd);
                }

                S21ApiTBLAccessor.update(altPayerTmg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(altPayerTmg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0267E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * CUST_CR_PRFL Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean custCrPrflCreationUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            LocInfoBean locInfoBean = locInfoMap.get(String.valueOf(i));
            BigDecimal billToCustPk = locInfoBean.getBillToCustPk();
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocBillToCrInfoFlg.getValue())) {
                CUST_CR_PRFLTMsg custCrPrflTMsg = new CUST_CR_PRFLTMsg();
                ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.billToCustPk, billToCustPk);
                ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.billToCustCd, locInfoBean.getBillToCustCd());
                CUST_CR_PRFLTMsg tMsg = (CUST_CR_PRFLTMsg) EZDTBLAccessor.findByKeyForUpdate(custCrPrflTMsg);
                if (tMsg == null) {
                    this.custCrPrflSetParam(pMsg, linePrm, custCrPrflTMsg);
                    this.custCrPrflSetDefVal(linePrm, custCrPrflTMsg, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.cashOnDelyReqFlg, ZYPConstant.FLG_OFF_N);
                    S21ApiTBLAccessor.create(custCrPrflTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(custCrPrflTMsg.getReturnCode())) {
                        msgMap.addXxMsgId(NMZM0244E);
                        return false;
                    }
                } else {
                    custCrPrflTMsg = tMsg;
                    this.custCrPrflSetParam(pMsg, linePrm, custCrPrflTMsg);
                    S21ApiTBLAccessor.update(custCrPrflTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(custCrPrflTMsg.getReturnCode())) {
                        msgMap.addXxMsgId(NMZM0268E);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void custCrPrflSetParam(NMZC001001PMsg pMsg, NMZC001002PMsg linePrm, CUST_CR_PRFLTMsg custCrPrflTMsg) {
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.crLimitAmt, linePrm.crLimitAmt.getValue());
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.crRiskClsCd, linePrm.crRiskClsCd.getValue());
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.crChkReqTpCd, linePrm.crChkReqTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.cashOrCcReqFlg, nullToN(linePrm.cashOrCcReqFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.custCrRtgCd, linePrm.custCrRtgCd.getValue());
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.pmtTermCashDiscCd, linePrm.pmtTermCashDiscCd.getValue());
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.ovrdPmtTermFlg, nullToN(linePrm.ovrdPmtTermFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.custHardHldFlg, nullToN(linePrm.custHardHldFlg.getValue()));
    }

    private void custCrPrflSetDefVal(NMZC001002PMsg linePrm, CUST_CR_PRFLTMsg custCrPrflTMsg, String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.ccyCd, this.getCcyCd(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.invDtChkReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.crRvwDtChkReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.psnGtdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.ovdWsFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.ovdPrtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.srCrCardFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.crBalAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.inProcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(custCrPrflTMsg.arBalAmt, BigDecimal.ZERO);
    }

    /**
     * SHIP_TO_CUST Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean shipToCustCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        SHIP_TO_CUSTTMsg shipToCustTmg = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));

            if (ZYPConstant.FLG_ON_Y.equals(linePrm.shipToCustFlg.getValue())) {
                shipToCustTmg = new SHIP_TO_CUSTTMsg();
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.primUsgFlg, linePrm.primUsgFlg_S.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.shipToCustPk, getShipToCustPk());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.cntyPk, locInfoBean.getCntyPk());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.locNum, locInfoBean.getLocNum());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.addlLocNm, linePrm.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.glnNum, linePrm.glnNum.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.dbaNm, pMsg.dbaNm.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dunsNum, linePrm.dunsNum.getValue());
                }
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.rgtnStsCd, getLocUsgLvlRgtnStsForCrat(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue(), linePrm.effFromDt_S.getValue()));
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.cnsgnFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.ptyLocPk, locInfoBean.getPtyLocPk());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.locRoleTpCd, LOC_ROLE_TP.SHIP_TO);
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.locGrpCd, LOC_GRP.CUSTOMER);
                if (ZYPCommonFunc.hasValue(linePrm.effFromDt_S)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.effFromDt, linePrm.effFromDt_S.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.effFromDt, pMsg.slsDt.getValue());
                }
                if (ZYPCommonFunc.hasValue(linePrm.effThruDt_S)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.effThruDt, linePrm.effThruDt_S.getValue());
                }
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.cmpyPk, locInfoBean.getCmpyPk());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.prinCustPk, locInfoBean.getPrinCustPk());
                }

                ZYPEZDItemValueSetter.setValue(shipToCustTmg.faxNum, linePrm.faxNum.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.telNum, linePrm.telNum.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.sellToCustCd, locInfoBean.getSellToCustCd());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.shipToCustCd, getShipToCustCd(glblCmpyCd));

                ZYPEZDItemValueSetter.setValue(shipToCustTmg.geoCd, linePrm.geoCd.getValue());

                if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDplUpdateFlg())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dplStsCd, locInfoBean.getDplStsCd());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dplRsnTxt, locInfoBean.getDplRsnTxt());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dplRspDtTmTs, locInfoBean.getDplRspDtTmTs());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.embgoFlg, locInfoBean.getEmbgoFlg());
                }
                if (!ZYPCommonFunc.hasValue(shipToCustTmg.embgoFlg)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.embgoFlg, ZYPConstant.FLG_OFF_N);
                }

                ZYPEZDItemValueSetter.setValue(shipToCustTmg.oemFlg, ZYPConstant.FLG_OFF_N);

                // From DSTable
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocShipToTaxFlg_S.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsCustTaxCd, linePrm.dsCustTaxCd_S.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsTaxPrntTpCd, linePrm.dsTaxPrntTpCd_S.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsCustTaxCalcCd, linePrm.dsCustTaxCalcCd_S.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsTaxGrpExemCd, linePrm.dsTaxGrpExemCd_S.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsTaxExemFlg, linePrm.dsTaxExemFlg_S.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsExemExprDt, linePrm.dsExemExprDt_S.getValue());
                }
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsInsdCtyLimitFlg, linePrm.dsInsdCtyLimitFlg.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.relnBillToCustCd, linePrm.relnBillToCustCd_S.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.lineBizTpCd, linePrm.lineBizTpCd.getValue());
                // 2019/04/05 QC#31030 Mod Start
                //setValueIfNotNull(shipToCustTmg.coaAfflCd, linePrm.coaAfflCd_S, pMsg.coaAfflCd);
                //setValueIfNotNull(shipToCustTmg.coaChCd, linePrm.coaChCd_S, pMsg.coaChCd);
                DEF_DPLY_COA_INFOTMsg defTMsg = getShipToDefCoa(glblCmpyCd);
                if (defTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaChCd, defTMsg.coaChCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaAfflCd, defTMsg.coaAfflCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaCmpyCd, defTMsg.coaCmpyCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaBrCd, defTMsg.coaBrCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaCcCd, defTMsg.coaCcCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaAcctCd, defTMsg.coaAcctCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaProdCd, defTMsg.coaProdCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaProjCd, defTMsg.coaProjCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaExtnCd, defTMsg.coaExtnCd);
                }
                // 2019/04/05 QC#31030 Mod End

                ZYPEZDItemValueSetter.setValue(shipToCustTmg.bigDealNum, linePrm.bigDealNum_S.getValue());

                if (!ZYPCommonFunc.hasValue(shipToCustTmg.dsInsdCtyLimitFlg)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
                }
                if (!ZYPCommonFunc.hasValue(shipToCustTmg.dsTaxExemFlg)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsAcctNm, pMsg.dsAcctNm.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsLocNm, linePrm.locNm.getValue());

                // 2023/11/06 QC#61924 Add Start
                if (!ZYPCommonFunc.hasValue(shipToCustTmg.deacNewTrxFlg)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                }
                // 2023/11/06 QC#61924 Add End

                S21ApiTBLAccessor.create(shipToCustTmg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipToCustTmg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0246E);
                    return false;
                }
                locInfoBean.setShipToCustPk(shipToCustTmg.shipToCustPk.getValue());
                locInfoMap.put(String.valueOf(i), locInfoBean);
            }
        }
        return true;
    }

    private boolean shipToCustUpdate_ExstCustLocMode(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd, LocInfoBean locInfoBean, boolean isPrimShipToInParamList) {
        SHIP_TO_CUSTTMsg shipToCustTmg = new SHIP_TO_CUSTTMsg();
        BigDecimal shipToCustPk = locInfoBean.getShipToCustPk();
        ZYPEZDItemValueSetter.setValue(shipToCustTmg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shipToCustTmg.shipToCustPk, shipToCustPk);

        shipToCustTmg = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(shipToCustTmg);
        if (shipToCustTmg == null) {
            return true;
        }

        ZYPEZDItemValueSetter.setValue(shipToCustTmg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
        ZYPEZDItemValueSetter.setValue(shipToCustTmg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(shipToCustTmg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(shipToCustTmg.dbaNm, pMsg.dbaNm.getValue());

        // From DSTable
        ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsAcctNm, pMsg.dsAcctNm.getValue());
        if (isPrimShipToInParamList) {
            if (ZYPConstant.FLG_ON_Y.equals(shipToCustTmg.primUsgFlg.getValue())) {
                shipToCustTmg.primUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
        // 2019/04/05 QC#31030 Del Start
        //if (ZYPCommonFunc.hasValue(pMsg.coaAfflCd)) {
        //    ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaAfflCd, pMsg.coaAfflCd.getValue());
        //}
        //if (ZYPCommonFunc.hasValue(pMsg.coaChCd)) {
        //    if (ZYPCommonFunc.hasValue(this.oldAcctCoaChCd) && oldAcctCoaChCd.equals(shipToCustTmg.coaChCd.getValue())) {
        //        ZYPEZDItemValueSetter.setValue(shipToCustTmg.coaChCd, pMsg.coaChCd.getValue());
        //    }
        //}
        // 2019/04/05 QC#31030 Del End

        S21ApiTBLAccessor.update(shipToCustTmg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipToCustTmg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0270E);
            return false;
        }
        return true;
    }

    /**
     * SHIP_TO_CUST Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean shipToCustUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {
        SHIP_TO_CUSTTMsg shipToCustTmg = null;
        LocInfoBean locInfoBean = null;
        BigDecimal shipToCustPk = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            shipToCustTmg = new SHIP_TO_CUSTTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.shipToCustFlg.getValue())) {
                shipToCustPk = getShipToCustPkForUpd(linePrm.locNum.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.shipToCustPk, shipToCustPk);

                shipToCustTmg = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(shipToCustTmg);
                if (shipToCustTmg == null) {
                    msgMap.addXxMsgId(NMZM0324E);
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.cntyPk, locInfoBean.getCntyPk());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.locNum, locInfoBean.getLocNum());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.locNm, getLocNm(pMsg.dsAcctNm.getValue()));
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.addlLocNm, linePrm.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.glnNum, linePrm.glnNum.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.dbaNm, pMsg.dbaNm.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocDunsFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dunsNum, linePrm.dunsNum.getValue());
                }
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.rgtnStsCd, getLocUsgLvlRgtnStsForUpd(pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue(), linePrm.effFromDt_S.getValue(), linePrm.effThruDt_S.getValue()));
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.effFromDt, linePrm.effFromDt_S.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.effThruDt, linePrm.effThruDt_S.getValue());
                if (ZYPConstant.FLG_OFF_N.equals(linePrm.actvFlg.getValue())) {
                    setLocLvlRgtnStsForUpd(pMsg.xxModeCd.getValue()
                            , pMsg.slsDt.getValue(), linePrm.effFromDt_S.getValue(), linePrm.effThruDt_S.getValue(), linePrm.actvFlg.getValue()
                            , shipToCustTmg.effFromDt, shipToCustTmg.effThruDt, shipToCustTmg.rgtnStsCd);
                }

                ZYPEZDItemValueSetter.setValue(shipToCustTmg.faxNum, linePrm.faxNum.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.telNum, linePrm.telNum.getValue());

                if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDplUpdateFlg())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dplStsCd, locInfoBean.getDplStsCd());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dplRsnTxt, locInfoBean.getDplRsnTxt());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dplRspDtTmTs, locInfoBean.getDplRspDtTmTs());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.embgoFlg, locInfoBean.getEmbgoFlg());
                }

                // From DSTable
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.primUsgFlg, linePrm.primUsgFlg_S.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocShipToTaxFlg_S.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsCustTaxCd, linePrm.dsCustTaxCd_S.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsTaxPrntTpCd, linePrm.dsTaxPrntTpCd_S.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsCustTaxCalcCd, linePrm.dsCustTaxCalcCd_S.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsTaxGrpExemCd, linePrm.dsTaxGrpExemCd_S.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsTaxExemFlg, linePrm.dsTaxExemFlg_S.getValue());
                    if (ZYPCommonFunc.hasValue(linePrm.dsTaxExemFlg_S)) {
                        ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsTaxExemFlg, linePrm.dsTaxExemFlg_S.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsExemExprDt, linePrm.dsExemExprDt_S.getValue());
                }
                if (ZYPCommonFunc.hasValue(linePrm.dsInsdCtyLimitFlg)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsInsdCtyLimitFlg, linePrm.dsInsdCtyLimitFlg.getValue());
                }
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.relnBillToCustCd, linePrm.relnBillToCustCd_S.getValue());
                if (ZYPCommonFunc.hasValue(linePrm.lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.lineBizTpCd, linePrm.lineBizTpCd.getValue());
                }
                // 2019/04/05 QC#31030 Del Start
                //setValueIfNotNull(shipToCustTmg.coaAfflCd, linePrm.coaAfflCd_S, pMsg.coaAfflCd);
                //setValueIfNotNull(shipToCustTmg.coaChCd, linePrm.coaChCd_S, pMsg.coaChCd);
                // 2019/04/05 QC#31030 Del End

                ZYPEZDItemValueSetter.setValue(shipToCustTmg.bigDealNum, linePrm.bigDealNum_S.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsAcctNm, pMsg.dsAcctNm.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTmg.dsLocNm, linePrm.locNm.getValue());

                S21ApiTBLAccessor.update(shipToCustTmg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shipToCustTmg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0270E);
                    return false;
                }
                locInfoBean.setShipToCustPk(shipToCustTmg.shipToCustPk.getValue());
                locInfoMap.put(String.valueOf(i), locInfoBean);
            }
        }
        return true;
    }

    /**
     * DS_ACCT_CR_PRFL Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsAcctCrPrflCreationUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {
        LocInfoBean locInfoBean = locInfoMap.get("0");
        String dsAcctNum = nvl(pMsg.dsAcctNum.getValue(), locInfoBean.getSellToCustCd());
        DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTmg = new DS_ACCT_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.dsAcctNum, dsAcctNum);
        DS_ACCT_CR_PRFLTMsg tMsg = (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsAcctCrPrflTmg);
        if (tMsg == null) {
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctCrFlg.getValue())) {
                this.dsAcctCrPrflSetParam(pMsg, dsAcctCrPrflTmg);
            } else {
                Map<String, Object> map = this.getDsAcctPrflDefTmpl(pMsg, dsAcctCrPrflTmg.dsAcctNum.getValue());
                if (map == null) {
                    msgMap.addXxMsgId(NMAM8477E);
                    return false;
                }
                this.dsAcctCrPrflSetDefTmpl(pMsg, dsAcctCrPrflTmg, map);
            }
            this.dsAcctCrPrflSetDefFlg(pMsg, dsAcctCrPrflTmg);
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.ccyCd, getCcyCd(glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.inProcAmt, BigDecimal.ZERO);
            // QC#18498
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.arBalAmt, BigDecimal.ZERO);

            S21ApiTBLAccessor.create(dsAcctCrPrflTmg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcctCrPrflTmg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0248E);
                return false;
            }
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctCrFlg.getValue())) {
                dsAcctCrPrflTmg = tMsg;
                this.dsAcctCrPrflSetParam(pMsg, dsAcctCrPrflTmg);
                S21ApiTBLAccessor.update(dsAcctCrPrflTmg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcctCrPrflTmg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0272E);
                    return false;
                }
            }
        }
        return true;
    }

    private void dsAcctCrPrflSetParam(NMZC001001PMsg pMsg, DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTmg) {
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.custCrRtgCd, pMsg.custCrRtgCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.crLimitAmt, pMsg.crLimitAmt.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.crChkReqTpCd, pMsg.crChkReqTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.crRiskClsCd, pMsg.crRiskClsCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.pmtTermCashDiscCd, pMsg.pmtTermCashDiscCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.ovrdPmtTermFlg, nullToN(pMsg.ovrdPmtTermFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.cashOrCcReqFlg, nullToN(pMsg.cashOrCcReqFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.custHardHldFlg, nullToN(pMsg.custHardHldFlg.getValue()));
    }

    private void dsAcctCrPrflSetDefTmpl(NMZC001001PMsg pMsg, DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTmg, Map<String, Object> map) {
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.custCrRtgCd, (String) map.get("CUST_CR_RTG_CD"));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.crLimitAmt, (BigDecimal) map.get("CR_LIMIT_AMT"));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.crChkReqTpCd, (String) map.get("CR_CHK_REQ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.crRiskClsCd, (String) map.get("CR_RISK_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.pmtTermCashDiscCd, (String) map.get("PMT_TERM_CASH_DISC_CD"));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.ovrdPmtTermFlg, nullToN((String) map.get("OVRD_PMT_TERM_FLG")));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.cashOrCcReqFlg, nullToN((String) map.get("CASH_OR_CC_REQ_FLG")));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.custHardHldFlg, nullToN((String) map.get("CUST_HARD_HLD_FLG")));
        // 2019/10/29 QC#54311 Add Start
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.contrCrRiskClsCd, (String) map.get("CONTR_CR_RISK_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.autoCashHrchCd, (String) map.get("AUTO_CASH_HRCH_CD"));
        // 2019/10/29 QC#54311 Add End
    }

    private void dsAcctCrPrflSetDefFlg(NMZC001001PMsg pMsg, DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTmg) {
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.ovdWsFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.ovdPrtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.srCrCardFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.invDtChkReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.crRvwDtChkReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTmg.psnGtdFlg, ZYPConstant.FLG_OFF_N);
    }

    private Map<String, Object> getDsAcctPrflDefTmpl(NMZC001001PMsg pMsg, String dsAcctNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsAcctPrflDefTmpl", param);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * DS_XREF_ACCT Creation or Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsXrefAcctCreateUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        DS_XREF_ACCTTMsg dsXrefAcctTmg = null;
        BigDecimal dsXrefAcctPk = null;
        LocInfoBean locInfoBean = null;

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            dsXrefAcctTmg = new DS_XREF_ACCTTMsg();
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocXrefInfoFlg.getValue())) {
                String locNum = linePrm.locNum.getValue();
                String dsXrefAcctTpCd = linePrm.dsXrefAcctTpCd.getValue();
                String dsXrefAcctCd = linePrm.dsXrefAcctCd.getValue();
                dsXrefAcctPk = getDsXrefAcctPkForUpd(locNum, glblCmpyCd, dsXrefAcctTpCd, dsXrefAcctCd);
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.dsXrefAcctPk, dsXrefAcctPk);

                dsXrefAcctTmg = (DS_XREF_ACCTTMsg) EZDTBLAccessor.findByKeyForUpdate(dsXrefAcctTmg);

                if (dsXrefAcctTmg == null) {
                    if (chkDuplicateDsXrefAcct(linePrm, glblCmpyCd)) {
                        // Insert
                        locInfoBean = locInfoMap.get(String.valueOf(i));

                        dsXrefAcctTmg = new DS_XREF_ACCTTMsg();
                        ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.dsXrefAcctPk, getDsXrefAcctPk());
                        ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.dsXrefAcctTpCd, linePrm.dsXrefAcctTpCd);
                        ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.dsXrefAcctCd, linePrm.dsXrefAcctCd);
                        ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.dsXrefAcctNm, linePrm.dsXrefAcctNm);
                        ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.locNum, locInfoBean.getLocNum());

                        S21ApiTBLAccessor.create(dsXrefAcctTmg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsXrefAcctTmg.getReturnCode())) {
                            msgMap.addXxMsgId(NMZM0249E);
                            return false;
                        }
                    } else {
                        msgMap.addXxMsgId(NMAM8628E);
                        return false;
                    }
                } else {
                // update
                    ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.dsXrefAcctTpCd, linePrm.dsXrefAcctTpCd);
                    ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.dsXrefAcctCd, linePrm.dsXrefAcctCd);
                    ZYPEZDItemValueSetter.setValue(dsXrefAcctTmg.dsXrefAcctNm, linePrm.dsXrefAcctNm);
                    S21ApiTBLAccessor.update(dsXrefAcctTmg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsXrefAcctTmg.getReturnCode())) {
                        msgMap.addXxMsgId(NMZM0273E);
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * DS_CUST_SPCL_MSG Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsCustSpclMsgUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {
        String strEffFromDt = "";
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                strEffFromDt = linePrm.effFromDt.getValue();
            }
        }
        if (!ZYPCommonFunc.hasValue(strEffFromDt)) {
            strEffFromDt = pMsg.slsDt.getValue();
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctInacRsnFlg.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(this.dsAcctActvCustFlgBefUpd)) {
                String dsAcctInacRsnNm = getInactiveReasonName(glblCmpyCd, pMsg.dsAcctInacRsnCd.getValue());
                DS_CUST_SPCL_MSGTMsg dsCustSpclMsgTmg = new DS_CUST_SPCL_MSGTMsg();
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.dsCustSpclMsgPk, getDsCustSpclMsgPk());
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.dsAcctNum, pMsg.dsAcctNum.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.lineBizTpCd, LINE_BIZ_TP.ALL);
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.dsBizAreaCd, DS_BIZ_AREA.CUSTOMER);
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.dsCustMsgTpCd, DS_CUST_MSG_TP.NOTE);
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.dsCustMsgTxt, cutToMaxByte(dsAcctInacRsnNm, MAX_SPCL_MSG_LEN));
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.dsPrintOnInvFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.custEffLvlCd, CUST_EFF_LVL.ACCOUNT_ONLY);
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.effFromDt, strEffFromDt);
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTmg.effThruDt, pMsg.effThruDt.getValue());

                S21ApiTBLAccessor.create(dsCustSpclMsgTmg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCustSpclMsgTmg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0250E);
                    return false;
                }
            }
        }

        return true;
    }


    private boolean isUpdated(String updColumn, List<String> updColumnList) {
        for (String cmprUpdColumn : updColumnList) {
            if (cmprUpdColumn.equals(updColumn)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUpdated(BigDecimal updColumn, List<BigDecimal> updColumnList) {
        for (BigDecimal cmprUpdColumn : updColumnList) {
            if (cmprUpdColumn.equals(updColumn)) {
                return true;
            }
        }
        return false;
    }

    private boolean wareHouseAddressUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        LocInfoBean locInfoBean = null;
        BigDecimal ptyLocPk = null;
        String locNum = null;
        String shipToCustCd = null;
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            //PTY_LOC_WRKTMsg ptyLocWrkTmsg = new PTY_LOC_WRKTMsg(); // 2018/11/28 QC#29134 Del
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            
            // 2018/11/28 QC#29134 Mod Start
            
            //ptyLocPk = locInfoBean.getPtyLocPk();
            //ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
            //ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ptyLocPk, ptyLocPk);
            //ptyLocWrkTmsg = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdate(ptyLocWrkTmsg);
            //if (ptyLocWrkTmsg == null) {
            //    continue;
            //}
            //if (isEquals(ptyLocWrkTmsg.ctryCd.getValue(), linePrm.ctryCd.getValue()) && isEquals(ptyLocWrkTmsg.firstLineAddr.getValue(), linePrm.firstLineAddr.getValue())
            //        && isEquals(ptyLocWrkTmsg.scdLineAddr.getValue(), linePrm.scdLineAddr.getValue()) && isEquals(ptyLocWrkTmsg.thirdLineAddr.getValue(), linePrm.thirdLineAddr.getValue())
            //        && isEquals(ptyLocWrkTmsg.frthLineAddr.getValue(), linePrm.frthLineAddr.getValue()) && isEquals(ptyLocWrkTmsg.ctyAddr.getValue(), linePrm.ctyAddr.getValue())
            //        && isEquals(ptyLocWrkTmsg.stCd.getValue(), linePrm.stCd.getValue()) && isEquals(ptyLocWrkTmsg.postCd.getValue(), linePrm.postCd.getValue())
            //        && isEquals(ptyLocWrkTmsg.cntyPk.getValue(), getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd))) {
            //    continue;
            //}
            
            
            if(!ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDunsAddrChngTpCd())){
                continue;
            }
            // 2018/11/28 QC#29134 Mod End

            locNum = locInfoBean.getLocNum();
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("locNum", locNum);
            queryParam.put("rgtnStsCd_P20", RGTN_STS.READY_FOR_ORDER_TAKING);
            queryParam.put("locRoleTp_WareHouse", LOC_ROLE_TP.CANON_WAREHOUSE);
            queryParam.put("locRoleTp_Technician", LOC_ROLE_TP.TECHNICIAN);

            // 2018/11/28 QC#29134 Mod Start
            //shipToCustCd = (String) this.ssmBatchClient.queryObject("getShipToCustCd", queryParam);
            //if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            //    continue;
            //}
            
            List<Map<String, Object>> shipToCustList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getShipToCustCd", queryParam);
            if (shipToCustList.size() == 0) {
                continue;
            }

            Map<String, Object> shipToCustMap = shipToCustList.get(0);
            List<SHIP_TO_CUSTTMsg> updShipToCustList = new ArrayList<SHIP_TO_CUSTTMsg>();
            List<PTY_LOC_WRKTMsg> updPtyLocWrkList = new ArrayList<PTY_LOC_WRKTMsg>();
            String effFfomDt = null;
            String effThruDt = null;
            String constValEndHazmat = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_END_TECH_HAZMAT_CD, glblCmpyCd);
            String shipToLocNum = (String) shipToCustMap.get("LOC_NUM_PL");
            shipToCustCd = (String) shipToCustMap.get("SHIP_TO_CUST_CD");
            String salesDate = ZYPDateUtil.getSalesDate();
            if (ZYPCommonFunc.hasValue(shipToLocNum) && shipToLocNum.contains(constValEndHazmat)) {
                // Check exist Warehouse
                boolean noActiveFlg = true;
                String whCheckCd = shipToLocNum.substring(0, shipToLocNum.indexOf(constValEndHazmat));
                RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
                rtlWhTMsg.setSQLID("002");
                rtlWhTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                rtlWhTMsg.setConditionValue("rtlWhCd01", whCheckCd);

                RTL_WHTMsgArray rtlWhTMsgArray = (RTL_WHTMsgArray) EZDTBLAccessor.findByCondition(rtlWhTMsg);

                if (rtlWhTMsgArray.length() == 0) {
                    // 2018/12/19 QC#29134 Mod Start
                    //msgMap.addXxMsgIdWithPrm(NMAM8693E, new String[] {ERR_MSG_RELATED_TECH_WH });
                    msgMap.addXxMsgId(NMAM8699E);
                    // 2018/12/19 QC#29134 Mod End
                    return false;
                }

                for (int j = 0; j < rtlWhTMsgArray.getValidCount(); j++) {
                    RTL_WHTMsg updRtlWhTMsg = rtlWhTMsgArray.no(j);
                    effFfomDt = updRtlWhTMsg.effFromDt.getValue();
                    effThruDt = updRtlWhTMsg.effThruDt.getValue();
                    if (!ZYPCommonFunc.hasValue(effThruDt)) {
                        effThruDt = TERMINATED_EFF_DT;
                    }
                    if (ZYPDateUtil.compare(salesDate, effFfomDt) >= 0 && ZYPDateUtil.compare(effThruDt, salesDate) >= 0) {
                        noActiveFlg = false;
                    }
                }
                if (noActiveFlg) {
                    // 2018/12/19 QC#29134 Mod Start
                    //msgMap.addXxMsgIdWithPrm(NMAM8694E, new String[] {ERR_MSG_RELATED_TECH_WH });
                    msgMap.addXxMsgId(NMAM8700E);
                    // 2018/12/19 QC#29134 Mod End
                    return false;
                }
                ptyLocPk = (BigDecimal) shipToCustMap.get("PTY_LOC_PK");
                if (ZYPCommonFunc.hasValue(shipToCustCd)) {
                    updShipToCustList = saveShipToCustByWareHouse(updShipToCustList, shipToCustCd, linePrm, glblCmpyCd, locInfoBean);
                }

                if (ZYPCommonFunc.hasValue(ptyLocPk)) {
                    updPtyLocWrkList = savePtyLocWrkByWareHouse(updPtyLocWrkList, ptyLocPk, linePrm, glblCmpyCd, locInfoBean);
                }
            } else {
            // 2018/11/28 QC#29134 Mod End

                // get Warehouse
                Map<String, Object> searchWareHouseParam = new HashMap<String, Object>();
                searchWareHouseParam.put("glblCmpyCd", glblCmpyCd);
                searchWareHouseParam.put("shipToCustCd", shipToCustCd);

                List<Map<String, Object>> wareHouseList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getWareHouse", searchWareHouseParam);
                if (wareHouseList == null || wareHouseList.size() == 0) {
                    msgMap.addXxMsgId(NMZM0337E);
                    return false;
                }

                // update
                List<WHTMsg> updWhList = new ArrayList<WHTMsg>();
                // 2018/11/28 QC#29134 Del Start
                // List<SHIP_TO_CUSTTMsg> updShipToCustList = new ArrayList<SHIP_TO_CUSTTMsg>();
                // List<PTY_LOC_WRKTMsg> updPtyLocWrkList = new ArrayList<PTY_LOC_WRKTMsg>();
                // 2018/11/28 QC#29134 Del End
                List<String> shipToCustCdList = new ArrayList<String>();
                List<BigDecimal> ptyLocPkList = new ArrayList<BigDecimal>();
                for (Map<String, Object> wareHouseMap : wareHouseList) {
                    shipToCustCd = (String) wareHouseMap.get("SHIP_TO_CUST_CD");
                    ptyLocPk = (BigDecimal) wareHouseMap.get("PTY_LOC_PK");

                    if ("WH_CD".equals((String) wareHouseMap.get("WH"))) {
                        if (!saveRetailWarehouseForShipTo(msgMap, wareHouseMap, linePrm, glblCmpyCd)) {
                            return false;
                        }
                        if (!saveRetailWarehouseForReturnTo(msgMap, wareHouseMap, linePrm, glblCmpyCd)) {
                            return false;
                        }
                    }

                    // 2018/11/28 QC#29134 Mod Start
                    //updWhList = saveWarehouse(updWhList, wareHouseMap, linePrm, glblCmpyCd); 
                    updWhList = saveWarehouse(updWhList, wareHouseMap, linePrm, glblCmpyCd, locInfoBean); 
                    // 2018/11/28 QC#29134 Mod End
                    if (ZYPCommonFunc.hasValue(shipToCustCd)) {
                        if (!isUpdated(shipToCustCd, shipToCustCdList)) {
                            // 2018/11/28 QC#29134 Mod Start
                            //updShipToCustList = saveShipToCustByWareHouse(updShipToCustList, shipToCustCd, linePrm, glblCmpyCd);
                            updShipToCustList = saveShipToCustByWareHouse(updShipToCustList, shipToCustCd, linePrm, glblCmpyCd, locInfoBean);
                            // 2018/11/28 QC#29134 Mod End
                            shipToCustCdList.add(shipToCustCd);
                        }
                    }

                    if (ZYPCommonFunc.hasValue(ptyLocPk)) {
                        if (!isUpdated(ptyLocPk, ptyLocPkList)) {
                            // 2018/11/28 QC#29134 Mod Start
                            //updPtyLocWrkList = savePtyLocWrkByWareHouse(updPtyLocWrkList, ptyLocPk, linePrm, glblCmpyCd);
                            updPtyLocWrkList = savePtyLocWrkByWareHouse(updPtyLocWrkList, ptyLocPk, linePrm, glblCmpyCd, locInfoBean);
                            // 2018/11/28 QC#29134 Mod End
                            ptyLocPkList.add(ptyLocPk);
                        }
                    }
                }

                // update WH
                if (!updWhList.isEmpty()) {
                    int updCnt = S21FastTBLAccessor.update(updWhList.toArray(new WHTMsg[0]));
                    if (updCnt != updWhList.size()) {
                        msgMap.addXxMsgId(NMZM0335E);
                        return false;
                    }
                }
            }

            // update SHIP_TO_CUST
            if (!updShipToCustList.isEmpty()) {
                int updCnt = S21FastTBLAccessor.update(updShipToCustList.toArray(new SHIP_TO_CUSTTMsg[0]));
                if (updCnt != updShipToCustList.size()) {
                    msgMap.addXxMsgId(NMZM0270E);
                    return false;
                }
            }

            // update PTY_LOC_WRK
            if (!updPtyLocWrkList.isEmpty()) {
                int updCnt = S21FastTBLAccessor.update(updPtyLocWrkList.toArray(new PTY_LOC_WRKTMsg[0]));
                if (updCnt != updPtyLocWrkList.size()) {
                    msgMap.addXxMsgId(NMZM0260E);
                    return false;
                }
            }

        }

        return true;
    }

    private boolean saveRetailWarehouseForShipTo(S21ApiMessageMap msgMap, Map<String, Object> wareHouseMap, NMZC001002PMsg linePrm, String glblCmpyCd) {
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        rtlWhTMsg.setSQLID("003");
        rtlWhTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rtlWhTMsg.setConditionValue("shipToCustCd01", wareHouseMap.get("RTL_WH_CD"));

        RTL_WHTMsgArray rtlWhTMsgArray = (RTL_WHTMsgArray) EZDTBLAccessor.findByCondition(rtlWhTMsg);
        if (rtlWhTMsgArray.length() == 0) {
            return true;
        }

        List<RTL_WHTMsg> updRtlWhList = new ArrayList<RTL_WHTMsg>();
        for (int i = 0; i < rtlWhTMsgArray.getValidCount(); i++) {
            RTL_WHTMsg updRtlWhTMsg = rtlWhTMsgArray.no(i);

            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.firstLineAddr, linePrm.firstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.scdLineAddr, linePrm.scdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.frthLineAddr, linePrm.frthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.ctyAddr, linePrm.ctyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.cntyPk, linePrm.cntyPk.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.provNm, linePrm.provNm.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.stCd, linePrm.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.postCd, linePrm.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.ctryCd, linePrm.ctryCd.getValue());

            updRtlWhList.add(updRtlWhTMsg);
        }

        // update RTL_WH
        if (!updRtlWhList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updRtlWhList.toArray(new RTL_WHTMsg[0]));
            if (updCnt != updRtlWhList.size()) {
                msgMap.addXxMsgId(NMZM0336E);
                return false;
            }
        }
        return true;
    }

    private boolean saveRetailWarehouseForReturnTo(S21ApiMessageMap msgMap, Map<String, Object> wareHouseMap, NMZC001002PMsg linePrm, String glblCmpyCd) {
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        rtlWhTMsg.setSQLID("004");
        rtlWhTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rtlWhTMsg.setConditionValue("rtrnToCustCd01", wareHouseMap.get("RTL_WH_CD"));

        RTL_WHTMsgArray rtlWhTMsgArray = (RTL_WHTMsgArray) EZDTBLAccessor.findByCondition(rtlWhTMsg);
        if (rtlWhTMsgArray.length() == 0) {
            return true;
        }

        List<RTL_WHTMsg> updRtlWhList = new ArrayList<RTL_WHTMsg>();
        for (int i = 0; i < rtlWhTMsgArray.getValidCount(); i++) {
            RTL_WHTMsg updRtlWhTMsg = rtlWhTMsgArray.no(i);

            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToFirstLineAddr, linePrm.firstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToScdLineAddr, linePrm.scdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToThirdLineAddr, linePrm.thirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToFrthLineAddr, linePrm.frthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToCtyAddr, linePrm.ctyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToCntyPk, linePrm.cntyPk.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToProvNm, linePrm.provNm.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToStCd, linePrm.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToPostCd, linePrm.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToCtryCd, linePrm.ctryCd.getValue());

            updRtlWhList.add(updRtlWhTMsg);
        }

        // update RTL_WH
        if (!updRtlWhList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updRtlWhList.toArray(new RTL_WHTMsg[0]));
            if (updCnt != updRtlWhList.size()) {
                msgMap.addXxMsgId(NMZM0336E);
                return false;
            }
        }
        return true;
    }

    // 2018/11/28 QC#29134 Mod Start
    //private List<WHTMsg> saveWarehouse(List<WHTMsg> updWhList, Map<String, Object> wareHouseMap, NMZC001002PMsg linePrm, String glblCmpyCd) {
    private List<WHTMsg> saveWarehouse(List<WHTMsg> updWhList, Map<String, Object> wareHouseMap, NMZC001002PMsg linePrm, String glblCmpyCd,LocInfoBean locInfoBean) {
    // 2018/11/28 QC#29134 Mod End
        WHTMsg whTMsg = new WHTMsg();
        whTMsg.setSQLID("007");
        whTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        whTMsg.setConditionValue("whCd01", wareHouseMap.get("RTL_WH_CD"));

        WHTMsgArray whTMsgArray = (WHTMsgArray) EZDTBLAccessor.findByCondition(whTMsg);
        if (whTMsgArray.length() == 0) {
            return updWhList;
        }

        for (int i = 0; i < whTMsgArray.getValidCount(); i++) {
            WHTMsg updWhTMsg = whTMsgArray.no(i);

            ZYPEZDItemValueSetter.setValue(updWhTMsg.firstLineAddr, linePrm.firstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updWhTMsg.scdLineAddr, linePrm.scdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updWhTMsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updWhTMsg.frthLineAddr, linePrm.frthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updWhTMsg.ctyAddr, linePrm.ctyAddr.getValue());
            // 2018/11/28 QC#29134 Mod Start
            //ZYPEZDItemValueSetter.setValue(updWhTMsg.cntyPk, linePrm.cntyPk.getValue());
            ZYPEZDItemValueSetter.setValue(updWhTMsg.cntyPk, locInfoBean.getCntyPk());
            // 2018/11/28 QC#29134 Mod End
            ZYPEZDItemValueSetter.setValue(updWhTMsg.provNm, linePrm.provNm.getValue());
            ZYPEZDItemValueSetter.setValue(updWhTMsg.stCd, linePrm.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(updWhTMsg.postCd, linePrm.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(updWhTMsg.ctryCd, linePrm.ctryCd.getValue());

            updWhList.add(updWhTMsg);
        }

        return updWhList;
    }

    // 2018/11/28 QC#29134 Mod Start
    //private List<SHIP_TO_CUSTTMsg>  saveShipToCustByWareHouse(List<SHIP_TO_CUSTTMsg> updShipToCustList, String shipToCustCd, NMZC001002PMsg linePrm, String glblCmpyCd) {
    private List<SHIP_TO_CUSTTMsg>  saveShipToCustByWareHouse(List<SHIP_TO_CUSTTMsg> updShipToCustList, String shipToCustCd, NMZC001002PMsg linePrm, String glblCmpyCd, LocInfoBean locInfoBean) {
    // 2018/11/28 QC#29134 Mod End
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCustCd);

        SHIP_TO_CUSTTMsgArray updShipToCustTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
        if (updShipToCustTMsgArray.length() == 0) {
            return updShipToCustList;
        }

        for (int i = 0; i < updShipToCustTMsgArray.getValidCount(); i++) {
            SHIP_TO_CUSTTMsg updShipToCustTMsg = updShipToCustTMsgArray.no(i);
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.firstLineAddr, linePrm.firstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.scdLineAddr, linePrm.scdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.frthLineAddr, linePrm.frthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.ctyAddr, linePrm.ctyAddr.getValue());
            // 2018/11/28 QC#29134 Mod Start
            //ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.cntyPk, linePrm.cntyPk.getValue());
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.cntyPk, locInfoBean.getCntyPk());
            // 2018/11/28 QC#29134 Mod End
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.provNm, linePrm.provNm.getValue());
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.stCd, linePrm.stCd.getValue());
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.postCd, linePrm.postCd.getValue());
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.ctryCd, linePrm.ctryCd.getValue());

            updShipToCustList.add(updShipToCustTMsg);
        }

        return updShipToCustList;
    }
    
    // 2018/11/28 QC#29134 Mod Start
    //private List<PTY_LOC_WRKTMsg> savePtyLocWrkByWareHouse(List<PTY_LOC_WRKTMsg> updPtyLocWrkList, BigDecimal ptyLocWrkPk, NMZC001002PMsg linePrm, String glblCmpyCd) {
    private List<PTY_LOC_WRKTMsg> savePtyLocWrkByWareHouse(List<PTY_LOC_WRKTMsg> updPtyLocWrkList, BigDecimal ptyLocWrkPk, NMZC001002PMsg linePrm, String glblCmpyCd,LocInfoBean locInfoBean) {
    // 2018/11/28 QC#29134 Mod End
        PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ptyLocPk, ptyLocWrkPk);

        PTY_LOC_WRKTMsg updPtyLocWrkTMsg = (PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKey(ptyLocWrkTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updPtyLocWrkTMsg.getReturnCode())) {
            return updPtyLocWrkList;
        }
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.firstLineAddr, linePrm.firstLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.scdLineAddr, linePrm.scdLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.frthLineAddr, linePrm.frthLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.ctyAddr, linePrm.ctyAddr.getValue());
        // 2018/11/28 QC#29134 Mod Start
        //ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.cntyPk, linePrm.cntyPk.getValue());
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.cntyPk, locInfoBean.getCntyPk());
        // 2018/11/28 QC#29134 Mod End
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.provNm, linePrm.provNm.getValue());
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.stCd, linePrm.stCd.getValue());
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.postCd, linePrm.postCd.getValue());
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.ctryCd, linePrm.ctryCd.getValue());

        updPtyLocWrkList.add(updPtyLocWrkTMsg);

        return updPtyLocWrkList;
    }

    private String getInactiveReasonName(String glblCmpyCd, String dsAcctInacRsnCd) {
        DS_ACCT_INAC_RSNTMsg tMsg = new DS_ACCT_INAC_RSNTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.dsAcctInacRsnCd.setValue(dsAcctInacRsnCd);
        tMsg = (DS_ACCT_INAC_RSNTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.dsAcctInacRsnNm.getValue();
        }
        return "";
    }

    /**
     * DS_ACCT_PROS Creation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsAcctProsCreation(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        DS_ACCT_PROSTMsg dsAcctProsTmsg = new DS_ACCT_PROSTMsg();

        LocInfoBean locInfoBean = locInfoMap.get("0");
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctProsPk, getDsAcctProsPk());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctNum, getSellToCustCd(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.cmpyPk, locInfoBean.getCmpyPk());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.effFromDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctActvCustFlg, ZYPConstant.FLG_ON_Y);

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            locInfoBean.setDsAcctProsPk(dsAcctProsTmsg.dsAcctProsPk.getValue());
            locInfoBean.setSellToCustCd(dsAcctProsTmsg.dsAcctNum.getValue());
            locInfoBean.setCmpyPk(dsAcctProsTmsg.cmpyPk.getValue());
            // Del Start 2017/07/14 QC#19880
            //if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg_NW.getValue())) {
            // Del End 2017/07/14 QC#19880
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.locNum, getLocNum(glblCmpyCd));
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.ptyLocPk, locInfoBean.getPtyLocPk());

                locInfoBean.setLocNum(dsAcctProsTmsg.locNum.getValue());
                locInfoBean.setPtyLocPk(dsAcctProsTmsg.ptyLocPk.getValue());
                locInfoBean.setCntyPk(dsAcctProsTmsg.cntyPk.getValue());

                // Insert ACCT_LOC Info
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.locNum, locInfoBean.getLocNum());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
            // Del Start 2017/07/14 QC#19880
            //} else {
            //    ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.locNum, getLocNum(glblCmpyCd));
            //    ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.ptyLocPk, locInfoBean.getPtyLocPk());
            //    locInfoBean.setLocNum(dsAcctProsTmsg.locNum.getValue());
            //    locInfoBean.setPtyLocPk(dsAcctProsTmsg.ptyLocPk.getValue());
            //}
            // Del End 2017/07/14 QC#19880
            locInfoMap.put(String.valueOf(i), locInfoBean);

        }
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.locRoleTpCd, LOC_ROLE_TP.SELL_TO);
        dsAcctProsSetParam(pMsg, dsAcctProsTmsg);
        S21ApiTBLAccessor.create(dsAcctProsTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcctProsTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0251E);
            return false;
        }
        return true;
    }

    /**
     * DS_ACCT_PROS Update
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsAcctProsUpdate(S21ApiMessageMap msgMap, NMZC001001PMsg pMsg, String glblCmpyCd) {

        BigDecimal dsAcctProsPk = getDsAcctProsPkForUpd(pMsg.dsAcctNum.getValue(), glblCmpyCd);
        DS_ACCT_PROSTMsg dsAcctProsTmsg = new DS_ACCT_PROSTMsg();
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctProsPk, dsAcctProsPk);

        dsAcctProsTmsg = (DS_ACCT_PROSTMsg) EZDTBLAccessor.findByKeyForUpdate(dsAcctProsTmsg);
        if (dsAcctProsTmsg == null) {
            msgMap.addXxMsgId(NMZM0328E);
            return false;
        }

        this.dsAcctActvCustFlgBefUpd = dsAcctProsTmsg.dsAcctActvCustFlg.getValue();

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            LocInfoBean locInfoBean = locInfoMap.get(String.valueOf(i));
            locInfoBean.setDsAcctProsPk(dsAcctProsTmsg.dsAcctProsPk.getValue());
            locInfoBean.setSellToCustCd(dsAcctProsTmsg.dsAcctNum.getValue());
            locInfoBean.setCmpyPk(dsAcctProsTmsg.cmpyPk.getValue());
            // Mod Start 2017/07/06 QC#19592
            //if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.cntyPk, getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd));
                locInfoBean.setLocNum(dsAcctProsTmsg.locNum.getValue());
                locInfoBean.setPtyLocPk(dsAcctProsTmsg.ptyLocPk.getValue());
                locInfoBean.setCntyPk(dsAcctProsTmsg.cntyPk.getValue());
            //}
            // Mod End 2017/07/06 QC#19592
            locInfoMap.put(String.valueOf(i), locInfoBean);

        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctInacRsnFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctActvCustFlg, ZYPConstant.FLG_OFF_N);
        } else if (ZYPConstant.FLG_OFF_N.equals(pMsg.xxAcctInacRsnFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctActvCustFlg, ZYPConstant.FLG_ON_Y);
        }

        dsAcctProsSetParam(pMsg, dsAcctProsTmsg);
        S21ApiTBLAccessor.update(dsAcctProsTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcctProsTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0275E);
            return false;
        }
        return true;
    }

    /**
     * DS_ACCT_PROS set parameter for common
     * @param pMsg NMZC001001PMsg
     * @param dsAcctProsTmsg DS_ACCT_PROSTMsg
     */
    private void dsAcctProsSetParam(NMZC001001PMsg pMsg, DS_ACCT_PROSTMsg dsAcctProsTmsg) {

        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctTpCd, DS_ACCT_TP.PROSPECT);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctNm, pMsg.dsAcctNm.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctItrlFlg, pMsg.dsAcctItrlFlg.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctClsCd, pMsg.dsAcctClsCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctDlrCd, pMsg.dsAcctDlrCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctLegalNm, pMsg.dsAcctLegalNm.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.firstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.scdRefCmntTxt, pMsg.scdRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dbaNm, pMsg.dbaNm.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.locGrpCd, LOC_GRP.CUSTOMER);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.bizRelnTpCd, getBIzRelnTpCd(pMsg.dsAcctItrlFlg.getValue()));
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.coaAfflCd, pMsg.coaAfflCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctUrl, pMsg.dsAcctUrl.getValue());

        String befRgtnStsCd = dsAcctProsTmsg.rgtnStsCd.getValue();
        if (PROCESS_MODE_PROS_UPD.equals(pMsg.xxModeCd.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctInacRsnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.rgtnStsCd, RGTN_STS.TERMINATED);

            } else if (ZYPConstant.FLG_OFF_N.equals(pMsg.xxAcctInacRsnFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
        }
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.effThruDt, getAcctLvlEffThruDtForUpd(befRgtnStsCd, dsAcctProsTmsg.rgtnStsCd.getValue()
                , dsAcctProsTmsg.effFromDt.getValue(), dsAcctProsTmsg.effThruDt.getValue(), pMsg.slsDt.getValue()));
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.effFromDt, getAcctLvlEffFromDtForUpd(befRgtnStsCd, dsAcctProsTmsg.rgtnStsCd.getValue()
                , dsAcctProsTmsg.effFromDt.getValue(), pMsg.slsDt.getValue()));

        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(i);
            // Del Start 2017/07/14 QC#19880
            //if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxPrinFlg_NW.getValue())) {
            // Del End 2017/07/14 QC#19880
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.firstLineAddr, linePrm.firstLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.scdLineAddr, linePrm.scdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.thirdLineAddr, linePrm.thirdLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.frthLineAddr, linePrm.frthLineAddr.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.ctyAddr, linePrm.ctyAddr.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.provNm, linePrm.provNm.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.stCd, linePrm.stCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.postCd, linePrm.postCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.ctryCd, linePrm.ctryCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.faxNum, linePrm.faxNum.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.telNum, linePrm.telNum.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.geoCd, linePrm.geoCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.locNm, linePrm.locNm.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.addlLocNm, linePrm.addlLocNm.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.glnNum, linePrm.glnNum.getValue());
            // Del Start 2017/07/14 QC#19880
            //}
            // Del End 2017/07/14 QC#19880
        }
        // Del Start 2017/07/14 QC#19880
        //if (!isPrinCustLocInParamList(pMsg)) {
        //    if (isNewAcctMode(pMsg) || isExstPrinCustLocInParamList(pMsg)) {
        //        dsAcctProsTmsg.firstLineAddr.clear();
        //        dsAcctProsTmsg.scdLineAddr.clear();
        //        dsAcctProsTmsg.thirdLineAddr.clear();
        //        dsAcctProsTmsg.frthLineAddr.clear();
        //        dsAcctProsTmsg.ctyAddr.clear();
        //        dsAcctProsTmsg.provNm.clear();
        //        dsAcctProsTmsg.stCd.clear();
        //        dsAcctProsTmsg.postCd.clear();
        //        dsAcctProsTmsg.ctryCd.clear();
        //
        //        dsAcctProsTmsg.locNum.clear();
        //        dsAcctProsTmsg.ptyLocPk.clear();
        //        dsAcctProsTmsg.cntyPk.clear();
        //    }
        //}
        // Del End 2017/07/14 QC#19880

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctDunsInfoFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctDunsNum, pMsg.dsAcctDunsNum.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctDunsNm, pMsg.dsAcctDunsNm.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctAltNm, pMsg.dsAcctAltNm.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsUltDunsNum, pMsg.dsUltDunsNum.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsLocEmpNum, pMsg.dsLocEmpNum.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsLocRevAmt, pMsg.dsLocRevAmt.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsLastUpdDunsDt, pMsg.dsLastUpdDunsDt.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsCustSicCd, pMsg.dsCustSicCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsCustSicDescTxt, pMsg.dsCustSicDescTxt.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsXtrnlRefTxt, pMsg.dsXtrnlRefTxt.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsDataSrcTxt, pMsg.dsDataSrcTxt.getValue());
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctCltFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsCltAcctStsCd, pMsg.dsCltAcctStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.lateFeeFlg, pMsg.lateFeeFlg.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.lateFeeAmt, pMsg.lateFeeAmt.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.cltCustTpCd, pMsg.cltCustTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.arStmtFlg, pMsg.arStmtFlg.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.arStmtIssDay, pMsg.arStmtIssDay.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dunFlg, pMsg.dunFlg.getValue());
        }

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctTaxFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsCustTaxCd, pMsg.dsCustTaxCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsTaxPrntTpCd, pMsg.dsTaxPrntTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsCustTaxCalcCd, pMsg.dsCustTaxCalcCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsTaxGrpExemCd, pMsg.dsTaxGrpExemCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsTaxExemFlg, pMsg.dsTaxExemFlg.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsExemExprDt, pMsg.dsExemExprDt.getValue());
        }
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.coaChCd, pMsg.coaChCd.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.xxAcctInacRsnFlg)) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctInacRsnCd, pMsg.dsAcctInacRsnCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(dsAcctProsTmsg.dsAcctItrlFlg)) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctItrlFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(dsAcctProsTmsg.arStmtFlg)) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.arStmtFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(dsAcctProsTmsg.lateFeeFlg)) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.lateFeeFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(dsAcctProsTmsg.dunFlg)) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dunFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(dsAcctProsTmsg.dsTaxExemFlg)) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
        }
        
        // START 2018/04/19 H.Ikeda [QC#23882, ADD]
        ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
        // END   2018/04/19 H.Ikeda [QC#23882, ADD]
    }

    private boolean checkByApi(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        if (!callAddrCheckApi(msgMap)) {
            returnValue = false;
        }

        return returnValue;
    }

    private boolean callCrPrflUpdApi(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC001001PMsg pMsg = (NMZC001001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();

        // Bill To
        NFZC202001 billCrPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsgB = new NFZC202001PMsg();
        ZYPEZDItemValueSetter.setValue(paramMsgB.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
        ZYPEZDItemValueSetter.setValue(paramMsgB.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramMsgB.procDt, slsDt);
        NMZC001002PMsg linePrm;
        LocInfoBean locInfoBean;
        List msgIdList;
        int cnt = 0;
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            linePrm = pMsg.NMZC001002PMsg.no(i);
            locInfoBean = locInfoMap.get(String.valueOf(i));
            cnt = linePrm.xxMsgIdList.getValidCount();
            if (ZYPConstant.FLG_ON_Y.equals(linePrm.xxLocBillToCrInfoFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(paramMsgB.billToCustCd, locInfoBean.getBillToCustCd());
                billCrPrflUpdApi.execute(paramMsgB, ONBATCH_TYPE.BATCH);
                msgIdList = S21ApiUtil.getXxMsgIdList(paramMsgB);
                for (int j = 0; j < msgIdList.size(); j++) {
                    ZYPEZDItemValueSetter.setValue(linePrm.xxMsgIdList.no(cnt).xxMsgId, (String) msgIdList.get(j));
                    cnt++;
                    returnValue = false;
                }
                linePrm.xxMsgIdList.setValidCount(cnt);
            }
        }

        // Account
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxAcctCrFlg.getValue())) {
            NFZC202001 crPrflUpdApi = new NFZC202001();
            NFZC202001PMsg paramMsg = new NFZC202001PMsg();
            ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_CUST_ACCT);
            ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(paramMsg.procDt, slsDt);
            ZYPEZDItemValueSetter.setValue(paramMsg.sellToCustCd, pMsg.dsAcctNum);

            crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.BATCH);
            msgIdList = S21ApiUtil.getXxMsgIdList(paramMsg);
            for (int i = 0; i < msgIdList.size(); i++) {
                msgMap.addXxMsgId((String) msgIdList.get(i));
                returnValue = false;
            }
        }

        return returnValue;
    }

    private boolean callAddrCheckApi(S21ApiMessageMap msgMap) {

        boolean result = true;

        NMZC001001PMsg pMsg = (NMZC001001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        NMZC001002PMsg linePrm;
        NMZC001002_xxMsgIdListPMsg xxMsgIdListPMsg;

        NMZC003001 addrCheckApi = new NMZC003001();
        NMZC003001PMsg param = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
        int cnt = 0;
        LocInfoBean locInfoBean = null;
        for (int i = 0; i < pMsg.NMZC001002PMsg.getValidCount(); i++) {
            locInfoBean = locInfoMap.get(String.valueOf(i));
            if (ZYPConstant.FLG_OFF_N.equals(locInfoBean.getAddrApiCallFlg())) {
                continue;
            }
            linePrm = pMsg.NMZC001002PMsg.no(i);
            cnt = linePrm.xxMsgIdList.getValidCount();
            param.xxMsgIdList.clear();
            param.xxMsgIdList.setValidCount(0);

            ZYPEZDItemValueSetter.setValue(param.firstLineAddr, linePrm.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(param.scdLineAddr, linePrm.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(param.ctyAddr, linePrm.ctyAddr);
            ZYPEZDItemValueSetter.setValue(param.stCd, linePrm.stCd);
            ZYPEZDItemValueSetter.setValue(param.postCd, linePrm.postCd);
            ZYPEZDItemValueSetter.setValue(param.ctryCd, linePrm.ctryCd);
            ZYPEZDItemValueSetter.setValue(param.cntyNm, linePrm.cntyNm);
            ZYPEZDItemValueSetter.setValue(param.xxCntyVldErrExclFlg, linePrm.xxCntyVldErrExclFlg);
            addrCheckApi.execute(param, ONBATCH_TYPE.BATCH);
            if (S21ApiUtil.isXxMsgId(param)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(param);
                for (S21ApiMessage msg : msgList) {
                    ZYPEZDItemValueSetter.setValue(linePrm.xxMsgIdList.no(cnt).xxMsgId, msg.getXxMsgid());
                    cnt++;
                    result = false;
                }
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_01.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0178E);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, FIRST_ADDR);
                result = false;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_01.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0179W);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, FIRST_ADDR);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_1, param.firstLineAddr);
                result = false;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_02.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0178E);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, SCD_ADDR);
                result = false;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_02.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0179W);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, SCD_ADDR);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_1, param.scdLineAddr);
                result = false;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_03.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0178E);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, CTY_ADDR);
                result = false;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_03.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0179W);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, CTY_ADDR);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_1, param.ctyAddr);
                result = false;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_04.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0178E);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, ST_CD);
                result = false;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_04.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0179W);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, ST_CD);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_1, param.stCd);
                result = false;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_05.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0178E);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, POST_CD);
                result = false;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_05.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0179W);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, POST_CD);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_1, param.postCd);
                result = false;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_06.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0178E);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, CTRY_CD);
                result = false;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_06.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0179W);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, CTRY_CD);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_1, param.ctryCd);
                result = false;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_07.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0178E);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, CNTY_NM);
                result = false;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_07.getValue())) {
                xxMsgIdListPMsg = linePrm.xxMsgIdList.no(cnt);
                cnt++;
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgId, NMZM0179W);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_0, CNTY_NM);
                ZYPEZDItemValueSetter.setValue(xxMsgIdListPMsg.xxMsgPrmTxt_1, param.cntyNm);
                result = false;
            }
            linePrm.xxMsgIdList.setValidCount(cnt);

            if (result) {
                if (CTRY.UNITED_STATES_OF_AMERICA.equals(linePrm.ctryCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(param.cntyPk)) {
                        ZYPEZDItemValueSetter.setValue(linePrm.cntyPk, param.cntyPk);
                    }
                }
            }
        }

        return result;
    }

    // START 2017/06/22 E.Kameishi [QC#18983, MOD]
    /**
     * This method will return SELL_TO_CUST_SQ for DS_ACCT_PROS_PK
     * @return BigDecimal
     */
    private BigDecimal getDsAcctProsPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SELL_TO_CUST_SQ);
    }
    // END 2017/06/22 E.Kameishi [QC#18983, MOD]

    /**
     * This method will return DS_ACCT_NUM
     * @param glblCmpyCd String
     * @return String
     */
    private String getSellToCustCd(String glblCmpyCd) {
        return ZYPExtnNumbering.getUniqueID(glblCmpyCd, SEQ_SELL_TO_CUST_CD);
    }

    /**
     * This method will return LOC_NUM
     * @param glblCmpyCd String
     * @return String
     */
    private String getLocNum(String glblCmpyCd) {
        return ZYPExtnNumbering.getUniqueID(glblCmpyCd, SEQ_LOC_NUM);
    }

    /**
     * This method will return DS_ACCT_PROS_SQ for DS_ACCT_PROS_PK
     * @return BigDecimal
     */
    private BigDecimal getPtyLocPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PTY_LOC_WRK_SQ);

    }

    /**
     * This method will return CMPY_SQ for CMPY_PK
     * @return BigDecimal
     */
    private BigDecimal getCmpyPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CMPY_SQ);

    }

    /**
     * This method will return PRIN_CUST_SQ for PRIN_CUST_PK
     * @return BigDecimal
     */
    private BigDecimal getPrinCustPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRIN_CUST_SQ);

    }

    /**
     * This method will return SELL_TO_CUST_SQ for SELL_TO_CUST_PK
     * @return BigDecimal
     */
    private BigDecimal getSellToCustPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SELL_TO_CUST_SQ);

    }

    /**
     * This method will return BILL_TO_CUST_SQ for BILL_TO_CUST_PK
     * @return BigDecimal
     */
    private BigDecimal getBillToCustPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.BILL_TO_CUST_SQ);

    }

    /**
     * This method will return SHIP_TO_CUST_SQ for SHIP_TO_CUST_PK
     * @return BigDecimal
     */
    private BigDecimal getShipToCustPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SHIP_TO_CUST_SQ);

    }

    /**
     * This method will return ACCT_LOC_SQ for ACCT_LOC_PK
     * @return BigDecimal
     */
    private BigDecimal getAcctLocPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.ACCT_LOC_SQ);

    }

    /**
     * This method will return DS_XREF_ACCT_SQ for DS_XREF_ACCT_PK
     * @return BigDecimal
     */
    private BigDecimal getDsXrefAcctPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_XREF_ACCT_SQ);

    }

    /**
     * This method will return LOC_USG_SQ for LOC_USG_PK
     * @return BigDecimal
     */
    private BigDecimal getLogUsgPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.LOC_USG_SQ);

    }

    /**
     * This method will return BILL_TO_CUST_CD
     * @param glblCmpyCd String
     * @return String
     */
    private String getBillToCustCd(String glblCmpyCd) {
        return ZYPExtnNumbering.getUniqueID(glblCmpyCd, SEQ_BILL_TO_CUST_CD);

    }

    /**
     * This method will return SHIP_TO_CUST_CD
     * @param glblCmpyCd String
     * @return String
     */
    private String getShipToCustCd(String glblCmpyCd) {
        return ZYPExtnNumbering.getUniqueID(glblCmpyCd, SEQ_SHIP_TO_CUST_CD);

    }

    /**
     * This method will return DS_CUST_SPCL_MSG_SQ for
     * DS_CUST_SPCL_MSG_PK
     * @return BigDecimal
     */
    private BigDecimal getDsCustSpclMsgPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CUST_SPCL_MSG_SQ);

    }

    /**
     * Get BIZ_RELN_TP_CD
     * @param dsAcctItrlFlg String
     * @return String
     */
    private String getBIzRelnTpCd(String dsAcctItrlFlg) {

        if (ZYPConstant.FLG_ON_Y.equals(dsAcctItrlFlg)) {
            return BIZ_RELN_TP.INTERNCUST;
        } else if (ZYPConstant.FLG_OFF_N.equals(dsAcctItrlFlg)) {
            return BIZ_RELN_TP.EXTERNCUST;
        }
        return "";
    }

    /**
     * Get Registration Status
     * @param xxModeCd String
     * @return String
     */
    private String getRgtnSts(String xxModeCd) {

        if (PROCESS_MODE_PROS_CRAT.equals(xxModeCd) || PROCESS_MODE_PROS_UPD.equals(xxModeCd) || PROCESS_MODE_CUST_CRAT.equals(xxModeCd)
                || PROCESS_MODE_EXST_CUST_LOC.equals(xxModeCd)) {
            return RGTN_STS.PENDING_COMPLETION;
        } else if (PROCESS_MODE_CUST_UPD.equals(xxModeCd) || PROCESS_MODE_CUST_CRAT_ACTV.equals(xxModeCd)) {
            return RGTN_STS.READY_FOR_ORDER_TAKING;
        }
        return "";
    }

    private String getLocUsgLvlRgtnStsForCrat(String xxModeCd, String slsDt, String effFromDt) {
        if (PROCESS_MODE_PROS_CRAT.equals(xxModeCd) || PROCESS_MODE_CUST_CRAT.equals(xxModeCd)) {
            return RGTN_STS.PENDING_COMPLETION;

        } else if (PROCESS_MODE_CUST_CRAT_ACTV.equals(xxModeCd) || PROCESS_MODE_EXST_CUST_LOC.equals(xxModeCd)) {
            if (effFromDt.compareTo(slsDt) <= 0) {
                return RGTN_STS.READY_FOR_ORDER_TAKING;
            } else {
                return RGTN_STS.PENDING_COMPLETION;
            }
        }
        return "";
    }

    private String getLocUsgLvlRgtnStsForUpd(String xxModeCd, String slsDt, String effFromDt, String effThruDt) {
        if (PROCESS_MODE_PROS_UPD.equals(xxModeCd)) {
            return RGTN_STS.PENDING_COMPLETION;

        } else if (PROCESS_MODE_CUST_UPD.equals(xxModeCd)) {
            if (ZYPCommonFunc.hasValue(effThruDt) && effThruDt.compareTo(slsDt) <= 0) {
                if (effThruDt.compareTo(slsDt) < 0) {
                    return RGTN_STS.TERMINATED;
                } else {
                    return RGTN_STS.READY_FOR_ORDER_TAKING;
                }
            } else {
                if (effFromDt.compareTo(slsDt) <= 0) {
                    return RGTN_STS.READY_FOR_ORDER_TAKING;
                } else {
                    return RGTN_STS.PENDING_COMPLETION;
                }
            }
        }
        return "";
    }

    private void setLocLvlRgtnStsForCrat(String slsDt, String paramEffFromDt, String actvFlg, EZDTStringItem tMsgRgtnStsCd) {
        if (ZYPConstant.FLG_ON_Y.equals(actvFlg)) {
            tMsgRgtnStsCd.setValue(RGTN_STS.READY_FOR_ORDER_TAKING);

        } else if (ZYPConstant.FLG_OFF_N.equals(actvFlg)) {
            tMsgRgtnStsCd.setValue(RGTN_STS.PENDING_COMPLETION);

        } else {
            if (paramEffFromDt.compareTo(slsDt) <= 0) {
                tMsgRgtnStsCd.setValue(RGTN_STS.READY_FOR_ORDER_TAKING);
            } else {
                tMsgRgtnStsCd.setValue(RGTN_STS.PENDING_COMPLETION);
            }
        }
    }

    private void setLocLvlRgtnStsForUpd(String xxModeCd, String slsDt
                                                    , String paramEffFromDt, String paramEffThruDt, String actvFlg
                                                    , EZDTDateItem tMsgEffFromDt, EZDTDateItem tMsgEffThruDt, EZDTStringItem tMsgRgtnStsCd) {
        if (ZYPConstant.FLG_ON_Y.equals(actvFlg)) {
            if (!RGTN_STS.READY_FOR_ORDER_TAKING.equals(tMsgRgtnStsCd.getValue())) {
                tMsgRgtnStsCd.setValue(RGTN_STS.READY_FOR_ORDER_TAKING);
            }
        } else if (ZYPConstant.FLG_OFF_N.equals(actvFlg)) {
            if (!RGTN_STS.TERMINATED.equals(tMsgRgtnStsCd.getValue())) {
                tMsgRgtnStsCd.setValue(RGTN_STS.TERMINATED);
                if (tMsgEffFromDt.getValue().compareTo(slsDt) < 0) {
                    tMsgEffThruDt.setValue(ZYPDateUtil.addDays(slsDt, -1));
                } else {
                    tMsgEffFromDt.setValue(ZYPDateUtil.addDays(slsDt, -1));
                    tMsgEffThruDt.setValue(ZYPDateUtil.addDays(slsDt, -1));
                }
            }
        }
    }

    /**
     * Get the registration status when status changing.(Mode : 04)
     * @param befRgtnSts String
     * @param aftRgtnSts String
     * @param rsnFlg String
     * @return String
     */
    private String getRgtnStsForChng04(String befRgtnSts, String aftRgtnSts, String rsnFlg) {
        if (ZYPConstant.FLG_ON_Y.equals(rsnFlg)) {
            return RGTN_STS.TERMINATED;
        }
        if (RGTN_STS.PENDING_COMPLETION.equals(befRgtnSts) && RGTN_STS.READY_FOR_ORDER_TAKING.equals(aftRgtnSts)) {
            return RGTN_STS.READY_FOR_ORDER_TAKING;

        } else if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(befRgtnSts) && RGTN_STS.TERMINATED.equals(aftRgtnSts)) {
            if (ZYPConstant.FLG_ON_Y.equals(rsnFlg)) {
                return RGTN_STS.TERMINATED;
            }
        } else if (RGTN_STS.TERMINATED.equals(befRgtnSts) && RGTN_STS.PENDING_COMPLETION.equals(aftRgtnSts)) {
            if (ZYPConstant.FLG_OFF_N.equals(rsnFlg)) {
                return RGTN_STS.PENDING_COMPLETION;
            }
        } else if (RGTN_STS.TERMINATED.equals(befRgtnSts) && RGTN_STS.READY_FOR_ORDER_TAKING.equals(aftRgtnSts)) {
            if (ZYPConstant.FLG_OFF_N.equals(rsnFlg)) {
                return RGTN_STS.READY_FOR_ORDER_TAKING;
            }
        }
        return befRgtnSts;
    }

    private String getAcctLvlEffThruDtForUpd(String befRgtnSts, String aftRgtnSts, String origEffFromDt, String origEffThruDt, String slsDt) {
        if (!RGTN_STS.TERMINATED.equals(befRgtnSts) && RGTN_STS.TERMINATED.equals(aftRgtnSts)) {
            return ZYPDateUtil.addDays(slsDt, -1);
        } else if (RGTN_STS.TERMINATED.equals(befRgtnSts) && !RGTN_STS.TERMINATED.equals(aftRgtnSts)) {
            return "";
        }
        return origEffThruDt;
    }

    private String getAcctLvlEffFromDtForUpd(String befRgtnSts, String aftRgtnSts, String origEffFromDt, String slsDt) {
        if (!RGTN_STS.TERMINATED.equals(befRgtnSts) && RGTN_STS.TERMINATED.equals(aftRgtnSts)) {
            if (origEffFromDt.compareTo(slsDt) >= 0) {
                return ZYPDateUtil.addDays(slsDt, -1);
            }
        }
        return origEffFromDt;
    }

    /**
     * @param cMsg NMAL6700CMsg
     * @return NMAL6700CMsg
     */
    private String getLocNm(String dsAcctNm) {
        return S21StringUtil.subStringByLength(dsAcctNm, 0, COL_LOC_NM_MAX_DIGIT_LENGTH);
    }

    /**
     * Set output parameter
     * @param msgMap S21ApiMessageMap
     */
    private void setOutputParameter(S21ApiMessageMap msgMap) {
        if (locInfoMap != null && locInfoMap.size() > 0) {
            NMZC001001PMsg param = (NMZC001001PMsg) msgMap.getPmsg();
            LocInfoBean locInfoBean = locInfoMap.get("0");
            ZYPEZDItemValueSetter.setValue(param.dsAcctNum, locInfoBean.getSellToCustCd());
            for (int i = 0; i < param.NMZC001002PMsg.getValidCount(); i++) {
                NMZC001002PMsg linePrm = param.NMZC001002PMsg.no(i);
                locInfoBean = locInfoMap.get((String.valueOf(i)));
                ZYPEZDItemValueSetter.setValue(linePrm.locNum, locInfoBean.getLocNum());
            }
        }
    }

    /**
     * Set Update parameter
     * @param dbParam String
     * @param inParam String
     */
    private String setUpdateParam(String dbParam, String inParam) {
        if (ZYPCommonFunc.hasValue(inParam)) {
            return inParam;
        } else {
            return dbParam;
        }
    }

    private String nullToN(String value) {
        if (ZYPCommonFunc.hasValue(value)) {
            return value;
        }
        return ZYPConstant.FLG_OFF_N;
    }

    private String nvl(String value1, String value2) {
        if (ZYPCommonFunc.hasValue(value1)) {
            return value1;
        }
        return value2;
    }

    private String getDefRemId(NMZC001001PMsg pMsg, String stCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("stCd", stCd);
        List<Map<String, Object>> ptyLocPkList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDefRemId", param);
        if (ptyLocPkList != null && ptyLocPkList.size() > 0) {
            Map<String, Object> map = ptyLocPkList.get(0);
            return (String) map.get("REM_ID");
        } else {
            ptyLocPkList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDefRemIdNoState", param);
            if (ptyLocPkList != null && ptyLocPkList.size() > 0) {
                Map<String, Object> map = ptyLocPkList.get(0);
                return (String) map.get("REM_ID");
            }
        }
        return null;
    }

    private String cutToMaxByte(String value, int maxByteLen) {
        if (!ZYPCommonFunc.hasValue(value)) {
            return value;
        }
        if (EZDStringUtil.getByteLength(value) <= maxByteLen) {
            return value;
        }
        for (int i = 0; i < value.length(); i++) {
            String substr = value.substring(0, value.length() - i - 1);
            if (EZDStringUtil.getByteLength(substr) <= maxByteLen) {
                return substr;
            }
        }
        return value;
    }

    private boolean getDplInfo(S21ApiMessageMap msgMap) {

        NMZC001001PMsg pMsg = (NMZC001001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String saleDate = pMsg.slsDt.getValue();
        LocInfoBean locInfoBean = locInfoMap.get("0");
        BigDecimal cmpyPk = locInfoBean.getCmpyPk();

        if (!ZYPConstant.FLG_ON_Y.equals(ZYPCodeDataUtil.getVarCharConstValue("ECS_AVAL_FLG", glblCmpyCd))) {
            setDPLReslutEcsNotAvailable_RqstList(glblCmpyCd);
            return true;
        }
        // ISO 8601 Date Format: YYYY-MM-DD
        String saleDateISO8601 = saleDate.substring(0, 4) + "-" + saleDate.substring(4, 6) + "-" + saleDate.substring(6, 8);

        Request req = new Request();

        // ---------------------------------------
        // Header
        // ---------------------------------------
        req.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_REQ_TYPE", glblCmpyCd));
        req.setVersion(ZYPCodeDataUtil.getVarCharConstValue("ECS_REQ_VERSION", glblCmpyCd));
        req.setDateFormat(ZYPCodeDataUtil.getVarCharConstValue("ECS_REC_DTFORMAT", glblCmpyCd));
        req.setDeploymentMode(ZYPCodeDataUtil.getVarCharConstValue("ECS_REQ_DEPLOYMODE", glblCmpyCd));
        req.setHandler(ZYPCodeDataUtil.getVarCharConstValue("ECS_REQ_HANDLER", glblCmpyCd));

        // ---------------------------------------
        // Administration
        // ---------------------------------------
        Administration admin = new Administration();
        admin.setSubscriberID(ZYPCodeDataUtil.getVarCharConstValue("ECS_ADMIN_ID", glblCmpyCd));
        admin.setSubscriberPassword(ZYPCodeDataUtil.getVarCharConstValue("ECS_ADMIN_PASS", glblCmpyCd));
        admin.setRequestDate(saleDateISO8601);
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_ADMIN_TYPE", glblCmpyCd));
        admin.setOutputFormat(outputFormat);
        admin.setIncludeRequest(ZYPCodeDataUtil.getVarCharConstValue("ECS_ADMIN_INCREQ", glblCmpyCd));

        req.setAdministration(admin);

        // ---------------------------------------
        // Service
        // ---------------------------------------
        com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service service = new com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service();

        // Service: ECE
        service.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_ECE", glblCmpyCd));
        service.setHandler(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVHDLR_ECE", glblCmpyCd));

        // Service: DeniedParty
        Svc serviceNest0 = new Svc();
        serviceNest0.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_DPL", glblCmpyCd));
        serviceNest0.setHandler(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVHDLR_DPL", glblCmpyCd));

        Parameters parametersForDnp = new Parameters();
        Parameter paraForDnp = new Parameter();
        paraForDnp = new Parameter();
        paraForDnp.setName(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_PARMNAME", glblCmpyCd));
        paraForDnp.setValue(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_PARMVAL", glblCmpyCd));
        parametersForDnp.getParameter().add(paraForDnp);
        serviceNest0.setParameters(parametersForDnp);

        service.getService().add(serviceNest0);

        // Service: Embergo
        Svc serviceNest1 = new Svc();
        serviceNest1.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_EMBGO", glblCmpyCd));
        serviceNest1.setHandler(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVHDLR_EMBGO", glblCmpyCd));

        Parameters parametersForEbg = new Parameters();
        Parameter paraForEbg = new Parameter();
        paraForEbg = new Parameter();
        paraForEbg.setName(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_PARMNAME", glblCmpyCd));
        paraForEbg.setValue(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_PARMVAL", glblCmpyCd));
        parametersForEbg.getParameter().add(paraForEbg);
        serviceNest1.setParameters(parametersForEbg);

        service.getService().add(serviceNest1);
        req.getService().add(service);

        // ---------------------------------------
        // Transaction
        // ---------------------------------------
        ServiceRequest sr = new ServiceRequest();

        Transaction t = new Transaction();
        t.setUserDefined(cmpyPk.toString());
        t.setTransactionDate(saleDateISO8601);

        ShipFromCountry sfc = new ShipFromCountry();
        sfc.setValue(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_CTRY", glblCmpyCd));
        sfc.setScheme(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_SCHM", glblCmpyCd));
        sfc.setDomain(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_DOMAIN", glblCmpyCd));
        sfc.setCode(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_CD", glblCmpyCd));
        t.setShipFromCountry(sfc);

        ShipToCountry stc = new ShipToCountry();
        stc.setValue(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_CTRY", glblCmpyCd));
        stc.setScheme(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_SCHM", glblCmpyCd));
        stc.setDomain(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_DOMAIN", glblCmpyCd));
        stc.setCode(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_CD", glblCmpyCd));
        t.setShipToCountry(stc);

        // ---------------------------------------
        // Transaction Line
        // ---------------------------------------
        List<TransactionLine> listTl = new ArrayList<TransactionLine>();
        boolean isCallDplApi = false;
        for (int n = 0; n < pMsg.NMZC001002PMsg.getValidCount(); n++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(n);
            BigDecimal ptyLocPk = locInfoBean.getPtyLocPk();

            locInfoBean = locInfoMap.get(String.valueOf(n));
            if (ZYPConstant.FLG_OFF_N.equals(locInfoBean.getDplUpdateFlg())) {
                continue;
            }

            TransactionLine tl = new TransactionLine();
            tl.setUserDefined(ptyLocPk.toString());

            Party party = new Party();
            party = new Party();

            party.setUserDefined(ptyLocPk.toString());

            if (ZYPCommonFunc.hasValue(pMsg.dsAcctNm)) {
                party.setName(getLocNm(pMsg.dsAcctNm.getValue()));
            }

            String tmpAddress = "";
            if (ZYPCommonFunc.hasValue(linePrm.firstLineAddr)) {
                tmpAddress += linePrm.firstLineAddr.getValue();
            }
            if (ZYPCommonFunc.hasValue(linePrm.scdLineAddr)) {
                if (ZYPCommonFunc.hasValue(tmpAddress)) {
                    tmpAddress += " ";
                }
                tmpAddress += linePrm.scdLineAddr.getValue();
            }
            if (ZYPCommonFunc.hasValue(linePrm.thirdLineAddr)) {
                if (ZYPCommonFunc.hasValue(tmpAddress)) {
                    tmpAddress += " ";
                }
                tmpAddress += linePrm.thirdLineAddr.getValue();
            }
            if (ZYPCommonFunc.hasValue(linePrm.frthLineAddr)) {
                if (ZYPCommonFunc.hasValue(tmpAddress)) {
                    tmpAddress += " ";
                }
                tmpAddress += linePrm.frthLineAddr.getValue();
            }
            party.setAddress(tmpAddress);

            if (ZYPCommonFunc.hasValue(linePrm.ctyAddr)) {
                party.setCity(linePrm.ctyAddr.getValue());
            }
            boolean flgProv = false;
            if (ZYPCommonFunc.hasValue(linePrm.stCd)) {
                String stNm = getStNm(linePrm.stCd.getValue(), glblCmpyCd);
                if (ZYPCommonFunc.hasValue(stNm)) {
                    party.setSubDivision(stNm);
                } else {
                    flgProv = true;
                }
            } else {
                flgProv = true;
            }
            if (flgProv) {
                if (ZYPCommonFunc.hasValue(linePrm.provNm)) {
                    party.setSubDivision(linePrm.provNm.getValue());
                }
            }

            if (ZYPCommonFunc.hasValue(linePrm.postCd)) {
                party.setPostalCode(linePrm.postCd.getValue());
            }

            Country country = new Country();
            country.setScheme(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_SCHM", glblCmpyCd));
            country.setDomain(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_DOMAIN", glblCmpyCd));

            if (ZYPCommonFunc.hasValue(linePrm.ctryCd)) {
                country.setCode(linePrm.ctryCd.getValue());
                country.setValue(getCtryNmByCtryCd(glblCmpyCd, linePrm.ctryCd.getValue()));
            }

            party.setCountry(country);
            tl.getParty().add(party);
            listTl.add(tl);
            isCallDplApi = true;
        }

        if (!isCallDplApi) {
            return true;
        }

        t.getTransactionLine().addAll(listTl);
        sr.getTransaction().add(t);
        req.setServiceRequest(sr);

        try {
            S21ChileKewillPartyScreeningServiceProxy proxyStub = new S21ChileKewillPartyScreeningServiceProxy();
            // // Call party screening service
            Response res = proxyStub.newRequest(req);

            // Check respone
            if (checkDPLCheckResult_RequestList(msgMap, res) == false) {
                return false;
            }
        } catch (RemoteException e) {
            setEcsApiExceptionError_RqstList(msgMap, e);
            return false;
        } catch (CallPartyScreeningServiceFaultResourceExp e) {
            setEcsApiExceptionError_RqstList(msgMap, e);
            return false;
        } catch (CallPartyScreeningServiceFaultDataExp e) {
            setEcsApiExceptionError_RqstList(msgMap, e);
            return false;
        } catch (CallPartyScreeningServiceFaultUserExp e) {
            setEcsApiExceptionError_RqstList(msgMap, e);
            return false;
        } catch (IOException e) {
            setEcsApiExceptionError_RqstList(msgMap, e);
            return false;
        } catch (PropertyException e) {
            setEcsApiExceptionError_RqstList(msgMap, e);
            return false;
        } catch (Throwable e) {
            msgMap.addXxMsgId(NMAM8052E);
            S21InfoLogOutput.println("[Ecs Err]:" + e.toString());
            return false;
        }

        return true;
    }

    private void setEcsApiExceptionError_RqstList(S21ApiMessageMap msgMap, Exception exception) {
        msgMap.addXxMsgId(NMAM8052E);
        EZDDebugOutput.println(1, "[Ecs Err]: " + exception.toString(), this);
        S21InfoLogOutput.println("[Ecs Err]: " + exception.toString());
    }

    /**
     * @param locNum String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private BigDecimal getPtyLocPk(String locNum, String glblCmpyCd, boolean isProsFlg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locNum", locNum);

        BigDecimal ptyLocPk = null;
        if (isProsFlg) {
            ptyLocPk = (BigDecimal) this.ssmBatchClient.queryObject("getProsPtyLocPk", param);
        } else {
            ptyLocPk = (BigDecimal) this.ssmBatchClient.queryObject("getPtyLocPk", param);
        }
        if (ZYPCommonFunc.hasValue(ptyLocPk)) {
            return ptyLocPk;
        } else {
            return null;
        }
    }

    /**
     * @param stCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private String getStNm(String stCd, String glblCmpyCd) {

        STTMsg stTMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(stTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(stTMsg.stCd, stCd);
        stTMsg = (STTMsg) S21FastTBLAccessor.findByKey(stTMsg);
        if (stTMsg == null) {
            return "";
        }
        return stTMsg.stNm.getValue();
    }

    /**
     * @param ctryCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private static String getCtryNmByCtryCd(String glblCmpyCd, String ctryCd) {
        CTRYTMsg tMsg = new CTRYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ctryCd.setValue(ctryCd);
        tMsg = (CTRYTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.ctryNm.getValue();
        }
        return "";
    }

    private boolean checkDPLCheckResult_RequestList(S21ApiMessageMap msgMap, Response res) {

        // Check API error
        boolean isApiError = isEcsApiError_RqstList(res);
        if (isApiError == true) {
            msgMap.addXxMsgId(NMAM8052E);
            return false;
        }

        // Check screening results
        List<Service> services = res.getServiceResponse().getService();
        boolean hasDeniedParty = false;
        NMZC001001PMsg pMsg = (NMZC001001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        for (int n = 0; n < locInfoMap.size(); n++) {
            LocInfoBean locInfoBean = locInfoMap.get(String.valueOf(n));
            if (ZYPConstant.FLG_OFF_N.equals(locInfoBean.getDplUpdateFlg())) {
                continue;
            }
            String embgoFlg = ZYPConstant.FLG_OFF_N;
            String dplStsCd = DPL_STS.PASS;
            String dplRsnTxt = null;
            String dplRspDtTmTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");

            for (int i = 0; i < services.size(); i++) {
                Service service = services.get(i);

                // Find service respone of the current location
                if (locInfoBean.getPtyLocPk().compareTo(new BigDecimal(service.getUserDefined())) != 0) {
                    continue;
                }

                // Check embargo
                if (ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_EMBGO", glblCmpyCd).equals(service.getType())) {
                    if (ZYPConstant.FLG_ON_Y.equals(service.getEmbargo())) {
                        embgoFlg = ZYPConstant.FLG_ON_Y;

                    }
                }

                // Check deny party
                if (ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_DPL", glblCmpyCd).equals(service.getType())) {
                    List<DeniedParty> deniedParty = service.getDeniedParty();
                    if (deniedParty != null) {
                        if (deniedParty.size() > 0 && ZYPCommonFunc.hasValue(deniedParty.get(0).getWords())) {
                            dplStsCd = DPL_STS.FAIL;
                            dplRsnTxt = deniedParty.get(0).getWords();
                            hasDeniedParty = true;
                        }
                    }
                    String redFlagWords = service.getRedFlagWords();
                    if (false == hasDeniedParty && redFlagWords != null && redFlagWords.trim().length() > 0) {
                        dplStsCd = DPL_STS.FAIL;
                        if (redFlagWords.length() > MAX_LEN_DPL_RSN_TXT) {
                            redFlagWords = redFlagWords.substring(0, MAX_LEN_DPL_RSN_TXT);
                        }
                        dplRsnTxt = redFlagWords;

                    }
                }
            }

            // Check dplRsnTxt's length
            if (dplRsnTxt != null) {
                if (dplRsnTxt.length() > MAX_LEN_DPL_RSN_TXT) {
                    dplRsnTxt = dplRsnTxt.substring(0, MAX_LEN_DPL_RSN_TXT);
                }
            }
            locInfoBean.setDplStsCd(dplStsCd);
            locInfoBean.setDplRsnTxt(dplRsnTxt);
            locInfoBean.setDplRspDtTmTs(dplRspDtTmTs);
            locInfoBean.setEmbgoFlg(embgoFlg);

        }

        return true;
    }

    private void setDPLReslutEcsNotAvailable_RqstList(String glblCmpyCd) {
        for (int n = 0; n < locInfoMap.size(); n++) {
            LocInfoBean locInfoBean = locInfoMap.get(String.valueOf(n));
            if (ZYPConstant.FLG_ON_Y.equals(locInfoBean.getDplUpdateFlg())) {
                String embgoFlg = ZYPConstant.FLG_OFF_N;
                // "The connection to the Kewill System was not available.";
                String dplStsCd = ZYPCodeDataUtil.getVarCharConstValue("DPL_STS_CD_INIT_VAL", glblCmpyCd);
                String dplRsnTxt = "";
                if (DPL_STS.FAIL.equals(dplStsCd)) {
                    dplRsnTxt = "The connection to the Kewill System was not available.";
                }
                String dplRspDtTmTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");

                locInfoBean.setDplStsCd(dplStsCd);
                locInfoBean.setDplRsnTxt(dplRsnTxt);
                locInfoBean.setDplRspDtTmTs(dplRspDtTmTs);
                locInfoBean.setEmbgoFlg(embgoFlg);
            }
        }
    }

    private boolean isEcsApiError_RqstList(Response res) {
        if (res == null) {
            EZDDebugOutput.println(1, "[Ecs Err]: Response is not available.", this);
            S21InfoLogOutput.println("[Ecs Err]: Response is not available.");
            return true;
        }

        if (res.getError() != null) {
            debugOutpuEcsApiError_RqstList(res.getError());
            return true;
        }

        if (res.getServiceResponse() == null) {
            EZDDebugOutput.println(1, "[Ecs Err]: serviceResponse is not available.", this);
            S21InfoLogOutput.println("[Ecs Err]: serviceResponse is not available.");
            return true;
        }

        List<Service> services = res.getServiceResponse().getService();

        if (services == null) {
            EZDDebugOutput.println(1, "[Ecs Err]: services is not available.", this);
            S21InfoLogOutput.println("[Ecs Err]: services is not available.");
            return true;
        }

        // Check API error
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i) != null) {
                List<Error> err = services.get(i).getError();
                if (err != null && err.size() != 0) {
                    for (int c = 0; c < err.size(); c++) {
                        debugOutpuEcsApiError_RqstList(err.get(c));
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private void debugOutpuEcsApiError_RqstList(Error err) {
        EZDDebugOutput.println(1, "[Ecs Err]:" + err.getSource(), this);
        EZDDebugOutput.println(1, "[Ecs Err]:" + err.getType(), this);
        EZDDebugOutput.println(1, "[Ecs Err]:" + err.getCode(), this);
        EZDDebugOutput.println(1, "[Ecs Err]:" + err.getMessage(), this);
        S21InfoLogOutput.println("[Ecs Err]:" + err.getSource());
        S21InfoLogOutput.println("[Ecs Err]:" + err.getType());
        S21InfoLogOutput.println("[Ecs Err]:" + err.getCode());
        S21InfoLogOutput.println("[Ecs Err]:" + err.getMessage());
    }
    private boolean isEquals(String newValue, String oldValue) {
        if (oldValue == null & newValue == null) {
            return true;
        }
        if (oldValue == null || newValue == null) {
            return false;
        }
        return oldValue.equals(newValue);
    }

    private boolean isEquals(BigDecimal newValue, BigDecimal oldValue) {
        if (oldValue == null & newValue == null) {
            return true;
        }
        if (oldValue == null || newValue == null) {
            return false;
        }
        if (oldValue.compareTo(newValue) == 0) {
            return true;
        }
        return false;
    }

    /**
     * toUpper parameter
     * @param msgMap S21ApiMessageMap
     */
    private void toUpperParameter(S21ApiMessageMap msgMap) {
        NMZC001001PMsg param = (NMZC001001PMsg) msgMap.getPmsg();

        ZYPEZDItemValueSetter.setValue(param.dsAcctNm, toUpperCase(param.dsAcctNm.getValue()));
        ZYPEZDItemValueSetter.setValue(param.dbaNm, toUpperCase(param.dbaNm.getValue()));
        ZYPEZDItemValueSetter.setValue(param.dsAcctDunsNum, toUpperCase(param.dsAcctDunsNum.getValue()));
        ZYPEZDItemValueSetter.setValue(param.dsUltDunsNum, toUpperCase(param.dsUltDunsNum.getValue()));
        ZYPEZDItemValueSetter.setValue(param.hqDunsNum, toUpperCase(param.hqDunsNum.getValue()));
        ZYPEZDItemValueSetter.setValue(param.dsAcctDunsNm, toUpperCase(param.dsAcctDunsNm.getValue()));
        ZYPEZDItemValueSetter.setValue(param.dsCustSicCd, toUpperCase(param.dsCustSicCd.getValue()));
        ZYPEZDItemValueSetter.setValue(param.dsCustSicDescTxt, toUpperCase(param.dsCustSicDescTxt.getValue()));
        ZYPEZDItemValueSetter.setValue(param.dsCustTaxCalcCd, toUpperCase(param.dsCustTaxCalcCd.getValue()));

        for (int i = 0; i < param.NMZC001002PMsg.getValidCount(); i++) {
            NMZC001002PMsg linePrm = param.NMZC001002PMsg.no(i);
            ZYPEZDItemValueSetter.setValue(linePrm.firstLineAddr, toUpperCase(linePrm.firstLineAddr.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.scdLineAddr, toUpperCase(linePrm.scdLineAddr.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.thirdLineAddr, toUpperCase(linePrm.thirdLineAddr.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.frthLineAddr, toUpperCase(linePrm.frthLineAddr.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.ctyAddr, toUpperCase(linePrm.ctyAddr.getValue()));
            // Add Start 2017/12/04 QC#22115
            ZYPEZDItemValueSetter.setValue(linePrm.cntyNm, toUpperCase(linePrm.cntyNm.getValue()));
            // Add End 2017/12/04 QC#22115
            ZYPEZDItemValueSetter.setValue(linePrm.provNm, toUpperCase(linePrm.provNm.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.locNm, toUpperCase(linePrm.locNm.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.addlLocNm, toUpperCase(linePrm.addlLocNm.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dsXrefAcctNm, toUpperCase(linePrm.dsXrefAcctNm.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.xxLocDunsFlg, toUpperCase(linePrm.xxLocDunsFlg.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dunsNum, toUpperCase(linePrm.dunsNum.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.hqDunsNum, toUpperCase(linePrm.hqDunsNum.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dsUltDunsNum, toUpperCase(linePrm.dsUltDunsNum.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dsPrntDunsNum, toUpperCase(linePrm.dsPrntDunsNum.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dsCustSicCd, toUpperCase(linePrm.dsCustSicCd.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dsCustSicDescTxt, toUpperCase(linePrm.dsCustSicDescTxt.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dunsTradeStyleNm, toUpperCase(linePrm.dunsTradeStyleNm.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dunsLineAddr, toUpperCase(linePrm.dunsLineAddr.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dunsBizNm, toUpperCase(linePrm.dunsBizNm.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.dsCustTaxCd_B, toUpperCase(linePrm.dsCustTaxCd_B.getValue()));
            ZYPEZDItemValueSetter.setValue(linePrm.bigDealNum_S, toUpperCase(linePrm.bigDealNum_S.getValue()));
        }
    }

    private String toUpperCase(String str) {
        if (!ZYPCommonFunc.hasValue(str)) {
            return null;
        }
        return str.toUpperCase();
    }

    private String getPtyLocWrkRgtnStsCd(String locNum, String glblCmpyCd, boolean isProsFlg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locNum", locNum);

        String rgtnStsCd = null;
        if (isProsFlg) {
            rgtnStsCd = (String) this.ssmBatchClient.queryObject("getProsPtyLocWrkRgtnStsCd", param);
        } else {
            rgtnStsCd = (String) this.ssmBatchClient.queryObject("getPtyLocWrkRgtnStsCd", param);
        }
        return rgtnStsCd;
    }

    /**
     * set Change Address Flag
     * @param msgMap S21ApiMessageMap
     */
    private boolean setChangeAddrFlg(S21ApiMessageMap msgMap) {
        NMZC001001PMsg pMsg = (NMZC001001PMsg) msgMap.getPmsg();

        if (checkModeForPros(pMsg.xxModeCd.getValue())) {
            if (!setChangeAddrFlgForPros(msgMap)) {
                return false;
            }
        } else {
            if (!setChangeAddrFlgForCust(msgMap)) {
                return false;
            }
        }
        return true;
    }

    /**
     * set Change Address Flag
     * @param msgMap S21ApiMessageMap
     */
    private boolean setChangeAddrFlgForCust(S21ApiMessageMap msgMap) {
        NMZC001001PMsg pMsg = (NMZC001001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal cmpyPk = null;
        BigDecimal cntyPk = null;
        if (ZYPCommonFunc.hasValue(pMsg.dsAcctNum)) {
            BigDecimal sellToCustPk = getSellToCustPkByAcctNumForUpd(pMsg.dsAcctNum.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(sellToCustPk)) {
                SELL_TO_CUSTTMsg sellToCustTmsg = new SELL_TO_CUSTTMsg();
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(sellToCustTmsg.sellToCustPk, sellToCustPk);

                sellToCustTmsg = (SELL_TO_CUSTTMsg) EZDTBLAccessor.findByKey(sellToCustTmsg);
                if (sellToCustTmsg != null) {
                    cmpyPk = sellToCustTmsg.cmpyPk.getValue();
                }
            }

            if (!ZYPCommonFunc.hasValue(cmpyPk)) {
                msgMap.addXxMsgId(NMZM0232E);
                return false;
            }
        } else {
            cmpyPk = getCmpyPk();
        }

        LocInfoBean locInfoBean = null;
        PTY_LOC_WRKTMsg ptyLocWrkTmsg = new PTY_LOC_WRKTMsg();
        for (int n = 0; n < pMsg.NMZC001002PMsg.getValidCount(); n++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(n);
            BigDecimal ptyLocPk = null;
            locInfoBean = new LocInfoBean();
            locInfoBean.setCmpyPk(cmpyPk);
            if (ZYPCommonFunc.hasValue(linePrm.locNum)) {
                ptyLocPk = getPtyLocPk(linePrm.locNum.getValue(), glblCmpyCd, false);
                if (ZYPCommonFunc.hasValue(ptyLocPk)) {
                    ptyLocWrkTmsg.clear();
                    ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ptyLocPk, ptyLocPk);

                    ptyLocWrkTmsg = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKey(ptyLocWrkTmsg);
                    cntyPk = getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd);
                    if (nullToBlank(ptyLocWrkTmsg.ctyAddr.getValue()).equals(nullToBlank(linePrm.ctyAddr.getValue()))
                            && nullToBlank(ptyLocWrkTmsg.stCd.getValue()).equals(nullToBlank(linePrm.stCd.getValue()))
                            && nullToBlank(ptyLocWrkTmsg.postCd.getValue()).equals(nullToBlank(linePrm.postCd.getValue()))
                            && nullToZero(ptyLocWrkTmsg.cntyPk.getValue()).compareTo(nullToZero(cntyPk)) == 0) {

                        locInfoBean.setAddrApiCallFlg(ZYPConstant.FLG_OFF_N);
                    } else {
                        locInfoBean.setAddrApiCallFlg(ZYPConstant.FLG_ON_Y);
                    }
                } else {
                    msgMap.addXxMsgId(NMZM0314E);
                }
            } else {
                ptyLocPk = getPtyLocPk();
            }

            if (!ZYPCommonFunc.hasValue(locInfoBean.getAddrApiCallFlg())) {
                locInfoBean.setAddrApiCallFlg(ZYPConstant.FLG_ON_Y);
            }
            locInfoBean.setDplUpdateFlg(chkDplUpdateFlg(linePrm, glblCmpyCd, pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue()));
            locInfoBean.setPtyLocPk(ptyLocPk);
            locInfoMap.put(String.valueOf(n), locInfoBean);
        }
        return true;
    }

    /**
     * set Change Address Flag
     * @param msgMap S21ApiMessageMap
     */
    private boolean setChangeAddrFlgForPros(S21ApiMessageMap msgMap) {
        NMZC001001PMsg pMsg = (NMZC001001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal cmpyPk = null;
        BigDecimal cntyPk = null;
        if (ZYPCommonFunc.hasValue(pMsg.dsAcctNum)) {
            BigDecimal dsAcctProsPk = getDsAcctProsPkForUpd(pMsg.dsAcctNum.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(dsAcctProsPk)) {
                DS_ACCT_PROSTMsg dsAcctProsTmsg = new DS_ACCT_PROSTMsg();
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsAcctProsTmsg.dsAcctProsPk, dsAcctProsPk);

                dsAcctProsTmsg = (DS_ACCT_PROSTMsg) EZDTBLAccessor.findByKey(dsAcctProsTmsg);
                if (dsAcctProsTmsg != null) {
                    cmpyPk = dsAcctProsTmsg.cmpyPk.getValue();
                }
            }

            if (!ZYPCommonFunc.hasValue(cmpyPk)) {
                msgMap.addXxMsgId(NMZM0232E);
                return false;
            }
        } else {
            cmpyPk = getCmpyPk();
        }

        LocInfoBean locInfoBean = null;
        PROS_PTY_LOC_WRKTMsg ptyLocWrkTmsg = new PROS_PTY_LOC_WRKTMsg();
        for (int n = 0; n < pMsg.NMZC001002PMsg.getValidCount(); n++) {
            NMZC001002PMsg linePrm = pMsg.NMZC001002PMsg.no(n);
            BigDecimal ptyLocPk = null;
            locInfoBean = new LocInfoBean();
            locInfoBean.setCmpyPk(cmpyPk);
            if (ZYPCommonFunc.hasValue(linePrm.locNum)) {
                ptyLocPk = getPtyLocPk(linePrm.locNum.getValue(), glblCmpyCd, true);
                if (ZYPCommonFunc.hasValue(ptyLocPk)) {
                    ptyLocWrkTmsg.clear();
                    ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(ptyLocWrkTmsg.ptyLocPk, ptyLocPk);

                    ptyLocWrkTmsg = (PROS_PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKey(ptyLocWrkTmsg);
                    cntyPk = getCntyPk(linePrm.cntyPk.getValue(), linePrm.cntyNm.getValue(), linePrm.stCd.getValue(), glblCmpyCd);
                    if (nullToBlank(ptyLocWrkTmsg.ctyAddr.getValue()).equals(nullToBlank(linePrm.ctyAddr.getValue()))
                            && nullToBlank(ptyLocWrkTmsg.stCd.getValue()).equals(nullToBlank(linePrm.stCd.getValue()))
                            && nullToBlank(ptyLocWrkTmsg.postCd.getValue()).equals(nullToBlank(linePrm.postCd.getValue()))
                            && nullToZero(ptyLocWrkTmsg.cntyPk.getValue()).compareTo(nullToZero(cntyPk)) == 0) {

                        locInfoBean.setAddrApiCallFlg(ZYPConstant.FLG_OFF_N);
                    } else {
                        locInfoBean.setAddrApiCallFlg(ZYPConstant.FLG_ON_Y);
                    }
                } else {
                    msgMap.addXxMsgId(NMZM0314E);
                }
            } else {
                ptyLocPk = getPtyLocPk();
            }
            if (!ZYPCommonFunc.hasValue(locInfoBean.getAddrApiCallFlg())) {
                locInfoBean.setAddrApiCallFlg(ZYPConstant.FLG_ON_Y);
            }
            locInfoBean.setDplUpdateFlg(chkDplUpdateFlg(linePrm, glblCmpyCd, pMsg.xxModeCd.getValue(), pMsg.slsDt.getValue()));
            locInfoBean.setPtyLocPk(ptyLocPk);
            locInfoMap.put(String.valueOf(n), locInfoBean);
        }
        return true;
    }

    /**
     * @param linePrm NMZC001002PMsg
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDuplicateDsXrefAcct(NMZC001002PMsg linePrm, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        param.put("dsXrefAcctTpCd", linePrm.dsXrefAcctTpCd.getValue());
        param.put("dsXrefAcctCd", linePrm.dsXrefAcctCd.getValue());

        Integer resCnt = (Integer) this.ssmBatchClient.queryObject("cntDuplicateDsXrefAcct", param);
        if (resCnt > 0) {
            return false;
        }
      return true;
    }

    /**
     * Convert Null to Blank
     * @param val String
     * @return String
     */
    private String nullToBlank(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return "";
        }
        return val;
    }
    /**
     * @param bdVal BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal nullToZero(BigDecimal bdVal) {
        if (bdVal == null) {
            return BigDecimal.ZERO;
        } else {
            return bdVal;
        }
    }

    /**
     * @param linePrm NMZC001002PMsg
     * @param glblCmpyCd String
     * @param xxModeCd String
     * @param slsDt String
     * @return String
     */
    private String chkDplUpdateFlg(NMZC001002PMsg linePrm, String glblCmpyCd, String xxModeCd, String slsDt) {

        if (ZYPConstant.FLG_ON_Y.equals(linePrm.shipToCustFlg.getValue())) {

            String rgtnStsCd = getLocUsgLvlRgtnStsForUpd(xxModeCd, slsDt, linePrm.effFromDt_S.getValue(), linePrm.effThruDt_S.getValue());
            if (ZYPCommonFunc.hasValue(linePrm.locNum) && RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {

                SHIP_TO_CUSTTMsg shipToCustTmg = new SHIP_TO_CUSTTMsg();
                BigDecimal shipToCustPk = getShipToCustPkForUpd(linePrm.locNum.getValue(), glblCmpyCd);
                if (ZYPCommonFunc.hasValue(shipToCustPk)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTmg.shipToCustPk, shipToCustPk);

                    shipToCustTmg = (SHIP_TO_CUSTTMsg) EZDTBLAccessor.findByKeyForUpdate(shipToCustTmg);
                    if (shipToCustTmg != null && RGTN_STS.TERMINATED.equals(shipToCustTmg.rgtnStsCd.getValue())) {

                        return ZYPConstant.FLG_ON_Y;
                    }
                }

            } else {
                return ZYPConstant.FLG_ON_Y;
            }
        }

        return ZYPConstant.FLG_OFF_N;
    }

    private void setValueIfNotNull(EZDTStringItem dbItem, EZDPStringItem locItem, EZDPStringItem acctItem) {
        if (ZYPCommonFunc.hasValue(locItem)) {
            ZYPEZDItemValueSetter.setValue(dbItem, locItem);
        } else {
            if (ZYPCommonFunc.hasValue(acctItem)) {
                ZYPEZDItemValueSetter.setValue(dbItem, acctItem);
            }
        }
    }

    private boolean checkModeForPros(String modeCd) {
        if (PROCESS_MODE_PROS_CRAT.equals(modeCd)
                || PROCESS_MODE_PROS_UPD.equals(modeCd)) {
            return true;
        } else {
            return false;
        }
    }

    // 2019/04/05 QC#24635 Add Start
    private DEF_DPLY_COA_INFOTMsg getShipToDefCoa(String glblCmpyCd) {
        DEF_DPLY_COA_INFOTMsg defTMsg = new DEF_DPLY_COA_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(defTMsg.appFuncId, APP_FUNC_ID_SHIP_TO_DETAILS);
        ZYPEZDItemValueSetter.setValue(defTMsg.glblCmpyCd, glblCmpyCd);

        defTMsg = (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(defTMsg);
        return defTMsg;
    }
    // 2019/04/05 QC#24635 Add End

    // Add Start 2019/04/16 QC#31114
    /**
     * @param pMsg NMZC001001PMsg
     * @param billToCustTMsg BILL_TO_CUSTTMsg
     */
    public void setTemplateForFinancials(NMZC001001PMsg pMsg, BILL_TO_CUSTTMsg billToCustTMsg) {

        List<Map<String, Object>> dsCustArTmplList = getTemplateForFinancials(pMsg);
        if (dsCustArTmplList.size() > 0) {
            Map<String, Object> dsCustArTmplMap = dsCustArTmplList.get(0);

            ZYPEZDItemValueSetter.setValue(billToCustTMsg.arStmtFlg, (String) dsCustArTmplMap.get("AR_STMT_FLG"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.arStmtIssDay, (String) dsCustArTmplMap.get("AR_STMT_ISS_DAY"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.cltCustTpCd, (String) dsCustArTmplMap.get("CLT_CUST_TP_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.cltPtfoPk, (BigDecimal) dsCustArTmplMap.get("CLT_PTFO_PK"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsCltAcctStsCd, (String) dsCustArTmplMap.get("DS_CLT_ACCT_STS_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsCustTaxCalcCd, (String) dsCustArTmplMap.get("DS_CUST_TAX_CALC_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsExemExprDt, (String) dsCustArTmplMap.get("DS_EXEM_EXPR_DT"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxExemFlg, (String) dsCustArTmplMap.get("DS_TAX_EXEM_FLG"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxGrpExemCd, (String) dsCustArTmplMap.get("DS_TAX_GRP_EXEM_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxPrntTpCd, (String) dsCustArTmplMap.get("DS_TAX_PRNT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dunFlg, (String) dsCustArTmplMap.get("DUN_FLG"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeFlg, (String) dsCustArTmplMap.get("LATE_FEE_FLG"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeAmt, (BigDecimal) dsCustArTmplMap.get("LATE_FEE_AMT"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.remId, (String) dsCustArTmplMap.get("REM_ID"));
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.sendCrBalStmtFlg, (String) dsCustArTmplMap.get("SEND_CR_BAL_STMT_FLG"));
        }

        return;
    }

    /**
     * @param pMsg NMZC001001PMsg
     * @param List<Map<String, Object>>
     */
    private List<Map<String, Object>> getTemplateForFinancials(NMZC001001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("flgOnY", ZYPConstant.FLG_ON_Y);

        List<Map<String, Object>> dsCustArTmplList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTemplateForFinancials", param);

        return dsCustArTmplList;
    }
    // Add End 2019/04/16 QC#31114
}
