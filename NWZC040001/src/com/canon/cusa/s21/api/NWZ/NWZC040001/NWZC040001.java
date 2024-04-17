/**
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 * 
 * <pre>
 * Invoice Import API
 * 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * 2017/10/23   Fujitsu         T.Aoi           Update          QC#20719
 * 2018/01/29   Hitachi         T.Tomita        Update          QC#23419
 * 2018/05/28   Fujitsu         M.Yamada        Update          QC#21841
 * 2020/07/17   Fujitsu         T.Ogura         Update          QC#57368
 * 2023/11/13   Hitachi         K.Kishimoto     Update          QC#61468
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.BILL_TP_REGULAR_INVOICE;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.CONST_KEY_CC_CASH_DISC_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.CONST_KEY_COA_CMPY_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.CONST_KEY_COA_EXTN_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.CONST_KEY_CR_LIMIT_SKIP_DS_INV_TP_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.CONST_KEY_INV_LINE_COA_ACCT_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.CONST_KEY_OFF_INV_COA_ACCT_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.DIGIT_INV_CASH_DISC_TERM_DTL_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.DIGIT_INV_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.DIVISION;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.FIXED_INV_LINE_SUB_TRX_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.FIXED_INV_PRMO_INFO_SEQ_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.MULTIPLICATION;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.N;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NOT_EQUALS;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0011E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0233E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0257E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0259E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0260E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0261E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0262E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0263E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0265E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0269E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0271E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0272E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0273E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0276E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0277E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0278E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0355E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0368E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0415E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0437E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0438E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0444E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0453E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0492E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0508E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0509E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0510E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0521E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0608E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0681E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0683E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0684E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0685E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0686E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0687E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0688E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0689E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0697E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0699E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0924E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM0996E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1143E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1279E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1281E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1282E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1285E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1286E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1289E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1300E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1508E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1509E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1536E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1537E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1538E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1539E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1540E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1541E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1542E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1543E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1544E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1545E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1546E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1547E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1548E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1549E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1550E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1552E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1553E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1554E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1555E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1557E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1558E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1559E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1560E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.NWZM1561E;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.STS_CD_ONE;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.STS_CD_PROCESSED;
import static com.canon.cusa.s21.api.NWZ.NWZC040001.constant.NWZC040001Constant.Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.getBigDecimal;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDTBigDecimalItem;
import business.db.BIZ_AREA_ORGTMsg;
import business.db.CCYTMsg;
import business.db.CR_DR_SUB_RSNTMsg;
import business.db.CTAC_PSNTMsg;
import business.db.DS_INV_LINE_SER_NUMTMsg;
import business.db.DS_INV_LINE_TAX_DTLTMsg;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.INVTMsg;
import business.db.INV_BOLTMsg;
import business.db.INV_CASH_DISC_TERMTMsg;
import business.db.INV_LINETMsg;
import business.db.INV_PRMO_INFOTMsg;
import business.db.MDL_NMTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.S21_ORGTMsg;
import business.parts.NFZC103001PMsg;
import business.parts.NMZC600001PMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;
import business.parts.NWZC040003PMsg;
import business.parts.NWZC040004PMsg;
import business.parts.NWZC040005PMsg;
import business.parts.NWZC040006PMsg;
import business.parts.NWZC040007PMsg;
import business.parts.NWZC055001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC103001.NFZC103001;
import com.canon.cusa.s21.api.NMZ.NMZC600001.NMZC600001;
import com.canon.cusa.s21.api.NWZ.NWZC055001.NWZC055001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001RateData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_CHRG_CPLT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SFTY_INV_TXT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

public class NWZC040001 extends S21ApiCommonBase {

    private final S21SsmEZDClient   ssmEzdClient;
    private final S21SsmBatchClient ssmBatchClient;

    /** systemSourceCode */
    private String sysSrcCd;
    /** global company code */
    private String glblCmpyCd;

    private boolean isServiceContract = false;
    private boolean isServiceDispatch = false;
    private boolean isReturn = false;
    private boolean isManualInvoice = false;

    public NWZC040001() {
        super();
        this.ssmEzdClient = S21SsmEZDClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Invoice Import API
     * @param invHeaderPmsgList List of Invoice Header parameter
     * @param shipmentsPMsgList List of Shipments parameter
     * @param invLinePMsgList List of Invoice Line parameter
     * @param promoPMsgList List of Promotion parameter
     * @param taxDtlPMsgList List of Invoice Line Tax Detail parameter
     * @param serNumPMsgList List of Invoice Line Serial Number parameter
     * @param invSlsCrPMsgList List of Invoice Sales Credit parameter
     * @param onBatchType OnBatchType
     */
    public void execute(
            final List<NWZC040001PMsg> invHeaderPmsgList
            , final List<NWZC040002PMsg> shipmentsPMsgList
            , final List<NWZC040003PMsg> invLinePMsgList
            , final List<NWZC040004PMsg> promoPMsgList
            , final List<NWZC040005PMsg> taxDtlPMsgList
            , final List<NWZC040006PMsg> serNumPMsgList
            , final List<NWZC040007PMsg> invSlsCrPMsgList
            , final ONBATCH_TYPE onBatchType) {

        boolean isSuccess = true;

        NWZC040001PMsg invPMsg = invHeaderPmsgList.get(0);
        isServiceContract = (SYS_SRC.S21_SERVICE.equals(invPMsg.sysSrcCd.getValue())) && (DS_BIZ_AREA.CONTRACTS.equals(invPMsg.dsBizAreaCd.getValue()))
                            || (SYS_SRC.CFS.equals(invPMsg.sysSrcCd.getValue())) && (DS_BIZ_AREA.INVOICING.equals(invPMsg.dsBizAreaCd.getValue()));
        isServiceDispatch = (SYS_SRC.S21_SERVICE.equals(invPMsg.sysSrcCd.getValue())) && (DS_BIZ_AREA.FIELD_SERVICE.equals(invPMsg.dsBizAreaCd.getValue()));
        isReturn = (TRX_SRC_TP.WHOLE_SALES_RETURN.equals(invPMsg.trxSrcTpCd.getValue()));
        isManualInvoice = (SYS_SRC.S21_ACCOUNTING_AR.equals(invPMsg.sysSrcCd.getValue()));

        if (!invHeaderMandatoryCheck(invHeaderPmsgList)) {
            isSuccess = false;
        }

        if (!shipmentsMandatoryCheck(shipmentsPMsgList)) {
            isSuccess = false;
        }

        if (!invLineMandatoryCheck(invLinePMsgList)) {
            isSuccess = false;
        }

        if (promoPMsgList != null) {
            if (!promotionMandatoryCheck(promoPMsgList)) {
                isSuccess = false;
            }
        }

        if (!taxDtlMandatoryCheck(taxDtlPMsgList)) {
            isSuccess = false;
        }

        if (invSlsCrPMsgList != null) {
            if (!invSlsCrMandatoryCheck(invSlsCrPMsgList)) {
                isSuccess = false;
            }
        }

        if (!isSuccess) {
            return;
        }

        this.sysSrcCd = invHeaderPmsgList.get(0).sysSrcCd.getValue();

        if (!invHeaderMandatoryByConditionCheck(invHeaderPmsgList)) {
            isSuccess = false;
        }

        if (!shipmentsMandatoryByConditionCheck(shipmentsPMsgList)) {
            isSuccess = false;
        }

        if (!invLineMandatoryByConditionCheck(invLinePMsgList)) {
            isSuccess = false;
        }

        if (promoPMsgList != null) {
            if (!promotionMandatoryByConditionCheck(promoPMsgList)) {
                isSuccess = false;
            }
        }

        if (!isSuccess) {
            return;
        }

        List<INVBean> invBeanList = doProcess(invHeaderPmsgList
                                            , shipmentsPMsgList
                                            , invLinePMsgList
                                            , promoPMsgList
                                            , taxDtlPMsgList
                                            , serNumPMsgList
                                            , invSlsCrPMsgList
                                            , onBatchType);
        for (INVBean invBean : invBeanList) {
            invBean.flushAllMessageMap();
        }
    }

    public void execute(
            final List<NWZC040001PMsg> invHeaderPmsgList
            , final List<NWZC040002PMsg> shipmentsPMsgList
            , final List<NWZC040003PMsg> invLinePMsgList
            , final List<NWZC040004PMsg> promoPMsgList
            , final ONBATCH_TYPE onBatchType) {
        
    }
    public void execute(
    	    final List<NWZC040001PMsg> invHeaderPmsgList
    		, final List<NWZC040002PMsg> shipmentsPMsgList
    		, final List<NWZC040003PMsg> invLinePMsgList
    		, final ONBATCH_TYPE onBatchType) {
    	
    }
//Add Dummy Function because it can not deploy End --
    
    /**
     * Invoice Import API
     * @param invHeaderPmsgList List of Invoice Header parameter
     * @param shipmentsPMsgList List of Shipments parameter
     * @param invLinePMsgList List of Invoice Line parameter
     * @param taxDtlPMsgList List of Invoice Line Tax Detail parameter
     * @param serNumPMsgList List of Invoice Line Serial Number parameter
     * @param invSlsCrPMsgList List of Invoice Sales Credit parameter
     * @param onBatchType OnBatchType
     */
    public void execute(final List<NWZC040001PMsg> invHeaderPmsgList
                        , final List<NWZC040002PMsg> shipmentsPMsgList
                        , final List<NWZC040003PMsg> invLinePMsgList
                        , final List<NWZC040005PMsg> taxDtlPMsgList
                        , final List<NWZC040006PMsg> serNumPMsgList
                        , final List<NWZC040007PMsg> invSlsCrPMsgList
                        , final ONBATCH_TYPE onBatchType) {
        execute(invHeaderPmsgList
                , shipmentsPMsgList
                , invLinePMsgList
                , null
                , taxDtlPMsgList
                , serNumPMsgList
                , invSlsCrPMsgList
                , onBatchType);
    }

    protected List<INVBean> doProcess(final List<NWZC040001PMsg> invHeaderPmsgList, final List<NWZC040002PMsg> shipmentsPMsgList, final List<NWZC040003PMsg> invLinePMsgList, final List<NWZC040004PMsg> promoPMsgList,
            final List<NWZC040005PMsg> taxDtlPmsgList, 
            final List<NWZC040006PMsg> serNumPmsgList, 
            final List<NWZC040007PMsg> invSlsCrPmsgList, 
            final ONBATCH_TYPE onBatchType) {

        final List<INVBean> invBeanList = makingInvoiceStructure(invHeaderPmsgList
                                                                , shipmentsPMsgList
                                                                , invLinePMsgList
                                                                , promoPMsgList
                                                                , taxDtlPmsgList
                                                                , serNumPmsgList
                                                                , invSlsCrPmsgList
                                                                );
        final List<INV_BOLBean>       allInvBolList      = new ArrayList<INV_BOLBean>();
        final List<INV_LINEBean>      allInvLineList     = new ArrayList<INV_LINEBean>();
        final List<INV_PRMO_INFOBean> allInvPrmoInfoList = new ArrayList<INV_PRMO_INFOBean>();
        for (INVBean invBean : invBeanList) {
            allInvBolList.addAll(invBean.getInvBolList());
            allInvLineList.addAll(invBean.getInvLineList());
            allInvPrmoInfoList.addAll(invBean.getInvPrmoInfoList());
        }

        boolean isSuccess = true;

        if (!chkFuncAmtRelation(invBeanList)) {
            isSuccess = false;
        }

        if (!invLineCheckWithInvHeader(allInvLineList)) {
            isSuccess = false;
        }

        if (!isSuccess) {
            return invBeanList;
        }
        if (!getFromMasterForINV(invBeanList)) {
            isSuccess = false;
        }

        if (!getFromMasterForINV_BOL(allInvBolList)) {
            isSuccess = false;
        }

        if (!getFromMasterForINV_LINE(allInvLineList)) {
            isSuccess = false;
        }

        if (!setUpINV_PRMO_INFO(allInvLineList, allInvPrmoInfoList)) {
            isSuccess = false;
        }

        if (!isSuccess) {
            return invBeanList;
        }

        isSuccess = exchDealAmtToFuncAmt(invBeanList);
        if (!isSuccess) {
            return invBeanList;
        }

        final List<INVTMsg>                invTMsgList             = new ArrayList<INVTMsg>();
        final List<INV_BOLTMsg>            invBolTMsgList          = new ArrayList<INV_BOLTMsg>();
        final List<INV_LINETMsg>           invLineTMsgList         = new ArrayList<INV_LINETMsg>();
        final List<INV_PRMO_INFOTMsg>      invPrmoInfoTMsgList     = new ArrayList<INV_PRMO_INFOTMsg>();
        final List<INV_CASH_DISC_TERMTMsg> invCashDiscTermTMsgList = new ArrayList<INV_CASH_DISC_TERMTMsg>();
        final List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTMsgList = new ArrayList<DS_INV_LINE_TAX_DTLTMsg>();
        final List<DS_INV_LINE_SER_NUMTMsg> dsInvLineSerNumTMsgList = new ArrayList<DS_INV_LINE_SER_NUMTMsg>();
        final List<DS_INV_SLS_CRTMsg>       dsInvSlsCrTMsgList      = new ArrayList<DS_INV_SLS_CRTMsg>();

        calcTotalAmount(invBeanList);

        setInvNum(invBeanList, onBatchType);
        setDsInv(invBeanList);
        /**********************************************************************
         * Can Insert?
         **********************************************************************/
        isSuccess = canInsertINVandINV_CASH_DISC_TERM(invBeanList, invTMsgList, invCashDiscTermTMsgList);
        if (!isSuccess) {
            return invBeanList;
        }
        isSuccess = canInsertINV_BOL(allInvBolList, invBolTMsgList);
        if (!isSuccess) {
            return invBeanList;
        }
        isSuccess = canInsertINV_LINE(allInvLineList, invLineTMsgList);
        if (!isSuccess) {
            return invBeanList;
        }
        isSuccess = canInsertINV_PRMO_INFO(allInvPrmoInfoList, invPrmoInfoTMsgList);
        if (!isSuccess) {
            return invBeanList;
        }

        isSuccess = canInsertDS_INV_LINE_TAX_DTL(invBeanList, taxDtlPmsgList, dsInvLineTaxDtlTMsgList);
        if (!isSuccess) {
            return invBeanList;
        }
        isSuccess = canInsertDS_INV_LINE_SER_NUM(allInvLineList, dsInvLineSerNumTMsgList);
        if (!isSuccess) {
            return invBeanList;
        }
        isSuccess = canInsertDS_INV_SLS_CR(allInvLineList, dsInvSlsCrTMsgList);
        if (!isSuccess) {
            return invBeanList;
        }

        // call credit limit update api after invoice existance check
        isSuccess = callCreditLimitUpdateAPI(invBeanList, onBatchType);
        if (!isSuccess) {
            return invBeanList;
        }

        /**********************************************************************
         * Insert INV, INV_BOL, INV_LINE, INV_PRMO_INFO and INV_CASH_DISC_TERM
         **********************************************************************/
        execInsert(invTMsgList
                    , invBolTMsgList
                    , invLineTMsgList
                    , invPrmoInfoTMsgList
                    , invCashDiscTermTMsgList
                    , dsInvLineTaxDtlTMsgList
                    , dsInvLineSerNumTMsgList
                    , dsInvSlsCrTMsgList
                    );

        // call invoice sales credit api creation after invoice data created
        if (isReturn) {
            isSuccess = callInvoiceSalesCreditCreationAPI(invBeanList);
            if (!isSuccess) {
                return invBeanList;
            }
        }
        // START 2018/03/13 E.Kameishi [QC#23689,MOD]
        //call invoice distribution api after invoice data created
        if (isManualInvoice) {
            isSuccess = callInvoiceDistributionAPI(invBeanList);
        }
        // END 2018/03/13 E.Kameishi [QC#23689,MOD]

        return invBeanList;
    }

    private boolean calcFuncAmtFromDealAmt(INVBean invBean) {

        boolean isSuccess = true;

        INVTMsg invTMsg = invBean.getInvTmsg();
        List<INV_BOLBean> invBolList = invBean.getInvBolList();

        NWXC001001RateData rateData = new NWXC001001RateData();
        rateData.setAcctArthTpCd(invBean.getAcctArthTpCd());
        rateData.setActlExchRate(invTMsg.actlApplyExchRate.getValue());

        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(invTMsg.glblCmpyCd.getValue());
        exchData.setCcyCd(invTMsg.dealCcyCd.getValue());
        exchData.setSlsDt(invTMsg.invDt.getValue());

        List<NWXC001001ExchangePriceData> invExchDataList = new ArrayList<NWXC001001ExchangePriceData>();
        List<NWXC001001ExchangePriceData> invBolExchDataList = new ArrayList<NWXC001001ExchangePriceData>();
        List<NWXC001001ExchangePriceData> invLineExchDataList = new ArrayList<NWXC001001ExchangePriceData>();
        
        InvExchangeAmontData exchAmontData = new InvExchangeAmontData();
        exchAmontData.setInvTotDealInsAmt(invTMsg.invTotDealInsAmt.getValue());
        invExchDataList.add(exchAmontData);

        if (hasValue(invTMsg.invTotDealInsAmt)) {
            exchData.setPriceData(invExchDataList);
            NWXC001001Exchange.exchFuncUnitPrice(exchData, rateData);
            if (!exchData.getXxMsgIdList().isEmpty()) {
                for (String msgId : exchData.getXxMsgIdList()) {
                    invBean.addXxMsgId(msgId);
                }
                isSuccess = false;
            }

            invExchDataList = exchData.getPriceData();
            exchAmontData = (InvExchangeAmontData)invExchDataList.get(0);
            NWZC040001.zeroThenOverWrite(invTMsg.invTotFuncInsAmt, exchAmontData.getInvTotFuncInsAmt());
        }
        
        for (INV_BOLBean invBolBean : invBolList) {
            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

            InvBolExchangeAmontData invBolExchAmtData = new InvBolExchangeAmontData();
            invBolExchAmtData.setFrtDealTaxAmt(invBolTMsg.frtDealTaxAmt.getValue());
            invBolExchAmtData.setShipDealFrtAmt(invBolTMsg.shipDealFrtAmt.getValue());
            invBolExchAmtData.setShipDealHdlgChrgAmt(invBolTMsg.shipDealHdlgChrgAmt.getValue());

            List<INV_LINEBean> invLineList = invBolBean.getInvLineList();

            for (INV_LINEBean invLineBean : invLineList) {

                InvLineExchangeAmountData invLineExchAmtData = invLineBean.getInvLineExchangeAmountData();

                invLineExchDataList.add(invLineExchAmtData);
            }
            invBolExchDataList.add(invBolExchAmtData);
        }

        exchData.setPriceData(invBolExchDataList);
        NWXC001001Exchange.exchFuncUnitPrice(exchData, rateData);
        if (!exchData.getXxMsgIdList().isEmpty()) {
            for (String msgId : exchData.getXxMsgIdList()) {
                invBean.addXxMsgId(msgId);
            }
            isSuccess = false;
        }

        invBolExchDataList = exchData.getPriceData();

        exchData.setPriceData(invLineExchDataList);
        NWXC001001Exchange.exchFuncUnitPrice(exchData, rateData);
        if (!exchData.getXxMsgIdList().isEmpty()) {
            for (String msgId : exchData.getXxMsgIdList()) {
                invBean.addXxMsgId(msgId);
            }
            isSuccess = false;
        }

        invLineExchDataList = exchData.getPriceData();
        Iterator<NWXC001001ExchangePriceData> invLineExchDataItr = invLineExchDataList.iterator();

        for (int i = 0; i < invBolList.size(); i++) {
            InvBolExchangeAmontData invBolExchAmtData = (InvBolExchangeAmontData) invBolExchDataList.get(i);
            INV_BOLBean invBolBean = invBolList.get(i);
            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

            if (BigDecimal.ZERO.compareTo(invBolTMsg.frtFuncTaxAmt.getValue())==0) {
                invBolTMsg.frtFuncTaxAmt.setValue(invBolExchAmtData.getFrtFuncTaxAmt());

            } else if (BigDecimal.ZERO.compareTo(invBolTMsg.frtDealTaxAmt.getValue())==0) {
                ZYPEZDItemValueSetter.setValue(invBolTMsg.frtDealTaxAmt, getDealAmount(invBean, invTMsg, invBolTMsg.frtFuncTaxAmt.getValue()));
            }

            if (BigDecimal.ZERO.compareTo(invBolTMsg.shipFuncFrtAmt.getValue())==0) {
                invBolTMsg.shipFuncFrtAmt.setValue(invBolExchAmtData.getShipFuncFrtAmt());

            } else if (BigDecimal.ZERO.compareTo(invBolTMsg.shipDealFrtAmt.getValue())==0) {
                ZYPEZDItemValueSetter.setValue(invBolTMsg.shipDealFrtAmt, getDealAmount(invBean, invTMsg, invBolTMsg.shipFuncFrtAmt.getValue()));
            }

            if (BigDecimal.ZERO.compareTo(invBolTMsg.shipFuncHdlgChrgAmt.getValue())==0) {
                invBolTMsg.shipFuncHdlgChrgAmt.setValue(invBolExchAmtData.getShipFuncHdlgChrgAmt());

            } else if (BigDecimal.ZERO.compareTo(invBolTMsg.shipDealHdlgChrgAmt.getValue())==0) {
                ZYPEZDItemValueSetter.setValue(invBolTMsg.shipDealHdlgChrgAmt, getDealAmount(invBean, invTMsg, invBolTMsg.shipFuncHdlgChrgAmt.getValue()));
            }

            List<INV_LINEBean> invLineList = invBolBean.getInvLineList();

            for (INV_LINEBean invLineBean : invLineList) {
                InvLineExchangeAmountData invLineExchAmtData = (InvLineExchangeAmountData) invLineExchDataItr.next();

                invLineBean.setInvLineFuncAmt(invBean, invBean.getInvTmsg(), invLineExchAmtData);
            }
        }
        return isSuccess;
    }

    private void calcTotalAmount(List<INVBean> invList) {

        for (INVBean invBean : invList) {
            INVTMsg invTMsg = invBean.getInvTmsg();
            BigDecimal invDealNetAmt = ZERO;
            BigDecimal invTotDealSlsAmt = ZERO;
            BigDecimal invTotDealDiscAmt = ZERO;
            BigDecimal invTotDealTaxAmt = ZERO;
            BigDecimal invToTDealFrtAmt = ZERO;
            BigDecimal invFuncNetAmt = ZERO;
            BigDecimal invTotFuncSlsAmt = ZERO;
            BigDecimal invTotFuncDiscAmt = ZERO;
            BigDecimal invTotFuncTaxAmt = ZERO;
            BigDecimal invTotFuncFrtAmt = ZERO;

            List<INV_BOLBean> invBolList = invBean.getInvBolList();

            for (INV_BOLBean invBolBean : invBolList) {
                INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();
                BigDecimal invBolDealNetAmt = ZERO;
                BigDecimal invBolDealGrsAmt = ZERO;
                BigDecimal invBolDealDiscUnitPrcAmt = ZERO;
                BigDecimal invBolDealTaxAmt = ZERO;
                BigDecimal invBolFuncNetAmt = ZERO;
                BigDecimal invBolFuncGrsAmt = ZERO;
                BigDecimal invBolFuncDiscUnitPrcAmt = ZERO;
                BigDecimal invBolFuncTaxAmt = ZERO;
                // QC#21841 2018/05/21 Add Start
                BigDecimal invBolDealFrtAmt = ZERO;
                BigDecimal invBolFuncFrtAmt = ZERO;
                // QC#21841 2018/05/21 Add End
                // START 2023/11/10 [QC#61468, MOD]
                BigDecimal frtDealTaxAmt = ZERO;
                BigDecimal frtFuncTaxAmt = ZERO;
                // END   2023/11/10 [QC#61468, MOD]

                List<INV_LINEBean> invLineList = invBolBean.getInvLineList();
                
                for (INV_LINEBean invLineBean : invLineList) {
                    INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
                    // QC#21841 2018/05/21 Add Start
                    //invBolDealNetAmt = invBolDealNetAmt.add(invLineTMsg.invLineDealNetAmt.getValue());
                    //invBolDealGrsAmt = invBolDealGrsAmt.add(invLineTMsg.dealGrsTotPrcAmt.getValue());
                    //invBolDealDiscUnitPrcAmt = invBolDealDiscUnitPrcAmt.add(invLineTMsg.dealDiscUnitPrcAmt.getValue());
                    //invBolDealTaxAmt = invBolDealTaxAmt.add(invLineTMsg.invLineDealTaxAmt.getValue());
                    //invBolFuncNetAmt = invBolFuncNetAmt.add(invLineTMsg.invLineFuncNetAmt.getValue());
                    //invBolFuncGrsAmt = invBolFuncGrsAmt.add(invLineTMsg.funcGrsTotPrcAmt.getValue());
                    //invBolFuncDiscUnitPrcAmt = invBolFuncDiscUnitPrcAmt.add(invLineTMsg.funcDiscUnitPrcAmt.getValue());
                    //invBolFuncTaxAmt = invBolFuncTaxAmt.add(invLineTMsg.invLineFuncTaxAmt.getValue());
                    if (INV_LINE_CATG.FREIGHT.equals(invLineTMsg.invLineCatgCd.getValue())) {
                        invBolDealFrtAmt = invBolDealFrtAmt.add(invLineTMsg.invLineDealNetAmt.getValue());

                        invBolFuncFrtAmt = invBolFuncFrtAmt.add(invLineTMsg.invLineFuncNetAmt.getValue());
                        // START 2023/11/10 [QC#61468, MOD]
                        invBolDealTaxAmt = invBolDealTaxAmt.add(invLineTMsg.invLineDealTaxAmt.getValue());
                        invBolFuncTaxAmt = invBolFuncTaxAmt.add(invLineTMsg.invLineFuncTaxAmt.getValue());
                        frtDealTaxAmt = frtDealTaxAmt.add(invLineTMsg.invLineDealTaxAmt.getValue());
                        frtFuncTaxAmt = frtFuncTaxAmt.add(invLineTMsg.invLineFuncTaxAmt.getValue());
                        // END   2023/11/10 [QC#61468, MOD]
                    } else {
                        invBolDealNetAmt = invBolDealNetAmt.add(invLineTMsg.invLineDealNetAmt.getValue());
                        invBolDealGrsAmt = invBolDealGrsAmt.add(invLineTMsg.dealGrsTotPrcAmt.getValue());
                        invBolDealDiscUnitPrcAmt = invBolDealDiscUnitPrcAmt.add(invLineTMsg.dealDiscUnitPrcAmt.getValue());
                        invBolDealTaxAmt = invBolDealTaxAmt.add(invLineTMsg.invLineDealTaxAmt.getValue());

                        invBolFuncNetAmt = invBolFuncNetAmt.add(invLineTMsg.invLineFuncNetAmt.getValue());
                        invBolFuncGrsAmt = invBolFuncGrsAmt.add(invLineTMsg.funcGrsTotPrcAmt.getValue());
                        invBolFuncDiscUnitPrcAmt = invBolFuncDiscUnitPrcAmt.add(invLineTMsg.funcDiscUnitPrcAmt.getValue());
                        invBolFuncTaxAmt = invBolFuncTaxAmt.add(invLineTMsg.invLineFuncTaxAmt.getValue());
                    }
                    // QC#21841 2018/05/21 Add End

                }

                zeroThenOverWrite(invBolTMsg.totBolDealTaxAmt, invBolDealTaxAmt.add(invBolTMsg.frtDealTaxAmt.getValue()));
                zeroThenOverWrite(invBolTMsg.shipDealNetAmt, invBolDealNetAmt);
                zeroThenOverWrite(invBolTMsg.shipDealSlsAmt, invBolDealGrsAmt);
                zeroThenOverWrite(invBolTMsg.shipDealDiscAmt, invBolDealDiscUnitPrcAmt);
                zeroThenOverWrite(invBolTMsg.shipDealFrtAmt, invBolDealFrtAmt); // QC#21841 2018/05/21 Add
                // START 2023/11/10 [QC#61468, ADD]
                zeroThenOverWrite(invBolTMsg.frtDealTaxAmt, frtDealTaxAmt);
                // END   2023/11/10 [QC#61468, ADD]

                zeroThenOverWrite(invBolTMsg.totBolFuncTaxAmt, invBolFuncTaxAmt.add(invBolTMsg.frtFuncTaxAmt.getValue()));
                zeroThenOverWrite(invBolTMsg.shipFuncNetAmt, invBolFuncNetAmt);
                zeroThenOverWrite(invBolTMsg.shipFuncSlsAmt, invBolFuncGrsAmt);
                zeroThenOverWrite(invBolTMsg.shipFuncDiscAmt, invBolFuncDiscUnitPrcAmt);
                zeroThenOverWrite(invBolTMsg.shipFuncFrtAmt, invBolFuncFrtAmt); // QC#21841 2018/05/21 Add
                // START 2023/11/10 [QC#61468, ADD]
                zeroThenOverWrite(invBolTMsg.frtFuncTaxAmt, frtFuncTaxAmt);
                // END   2023/11/10 [QC#61468, ADD]

                invDealNetAmt = invDealNetAmt.add(invBolTMsg.shipDealNetAmt.getValue()).add(invBolTMsg.shipDealHdlgChrgAmt.getValue());
                invTotDealSlsAmt = invTotDealSlsAmt.add(invBolTMsg.shipDealSlsAmt.getValue());
                invTotDealDiscAmt = invTotDealDiscAmt.add(invBolTMsg.shipDealDiscAmt.getValue());
                invTotDealTaxAmt = invTotDealTaxAmt.add(invBolTMsg.totBolDealTaxAmt.getValue());
                invToTDealFrtAmt = invToTDealFrtAmt.add(invBolTMsg.shipDealFrtAmt.getValue());

                invFuncNetAmt = invFuncNetAmt.add(invBolTMsg.shipFuncNetAmt.getValue()).add(invBolTMsg.shipFuncHdlgChrgAmt.getValue());
                invTotFuncSlsAmt = invTotFuncSlsAmt.add(invBolTMsg.shipFuncSlsAmt.getValue());
                invTotFuncDiscAmt = invTotFuncDiscAmt.add(invBolTMsg.shipFuncDiscAmt.getValue());
                invTotFuncTaxAmt = invTotFuncTaxAmt.add(invBolTMsg.totBolFuncTaxAmt.getValue());
                invTotFuncFrtAmt = invTotFuncFrtAmt.add(invBolTMsg.shipFuncFrtAmt.getValue());
            }

            zeroThenOverWrite(invTMsg.invTotDealSlsAmt, invTotDealSlsAmt);
            zeroThenOverWrite(invTMsg.invTotDealDiscAmt, invTotDealDiscAmt);
            zeroThenOverWrite(invTMsg.invTotDealTaxAmt, invTotDealTaxAmt);
            zeroThenOverWrite(invTMsg.invTotDealFrtAmt, invToTDealFrtAmt);
            zeroThenOverWrite(invTMsg.invTotDealNetAmt, invDealNetAmt.add(invTMsg.invTotDealTaxAmt.getValue().add(invTMsg.invTotDealFrtAmt.getValue())).add(invTMsg.invTotDealInsAmt.getValue()));

            // QC#21841 mod
            zeroThenOverWrite(invTMsg.invTotFuncSlsAmt, invTotFuncSlsAmt);
            zeroThenOverWrite(invTMsg.invTotFuncDiscAmt, invTotFuncDiscAmt);
            zeroThenOverWrite(invTMsg.invTotFuncTaxAmt, invTotFuncTaxAmt);
            zeroThenOverWrite(invTMsg.invTotFuncFrtAmt, invTotFuncFrtAmt);
            zeroThenOverWrite(invTMsg.invTotFuncNetAmt, invFuncNetAmt.add(invTMsg.invTotFuncTaxAmt.getValue().add(invTMsg.invTotFuncFrtAmt.getValue())).add(invTMsg.invTotFuncInsAmt.getValue()));

        }
    }

    private boolean callCreditLimitUpdateAPI(List<INVBean> invList, ONBATCH_TYPE onBatchType) {

        boolean isSuccess = true;

        List<NMZC600001PMsg> pMsgList = new ArrayList<NMZC600001PMsg>();
        // Add Start 2018/01/29 QC#23419
        List<String> skipDsInvTpList;
        // Add End 2018/01/29 QC#23419

        for (INVBean invBean : invList) {
            INVTMsg invTMsg = invBean.getInvTmsg();
            // Mod Start 2018/01/29 QC#23419
            String glblCmpyCd = invTMsg.glblCmpyCd.getValue();
            skipDsInvTpList = getCrLimitUpdateApiSkipDsInvTp(glblCmpyCd);
            if (hasValue(invTMsg.dsInvTpCd) && skipDsInvTpList.size() > 0 && skipDsInvTpList.contains(invTMsg.dsInvTpCd.getValue())) {
                continue;
            }
            if (isServiceContract || isServiceDispatch || isReturn) {
                BigDecimal invTotfuncNetAmt = invTMsg.invTotFuncNetAmt.getValue();
                String invDt = invTMsg.invDt.getValue();
                String invNum = invTMsg.invNum.getValue();
                String invTpCd = invTMsg.invTpCd.getValue();
                if (INV_TP.CREDIT_MEMO.equals(invTpCd)) {
                    invTotfuncNetAmt = invTotfuncNetAmt.negate();
                }

                NMZC600001PMsg pMsg = new NMZC600001PMsg();
                pMsg.glblCmpyCd.setValue(glblCmpyCd);
                pMsg.xxModeCd.setValue(NMZC600001.MODE_ALL);
                pMsg.dsAcctNum.setValue(invTMsg.billToCustAcctCd.getValue());
                pMsg.billToCustCd.setValue(invTMsg.billToCustCd.getValue());
                pMsg.invAmt.setValue(invTotfuncNetAmt);
                pMsg.invDt.setValue(invDt);
                pMsg.updKeyNum.setValue(invNum);
                pMsg.slsDt.setValue(invDt);

                pMsgList.add(pMsg);
            }
            // Mod End 2018/01/29 QC#23419
        }
        // Add Start 2018/01/29 QC#23419
        if (pMsgList.size() == 0) {
            return isSuccess;
        }
        // Add End 2018/01/29 QC#23419

        NMZC600001 creditLimitUpdateAPI = new NMZC600001();
        creditLimitUpdateAPI.execute(pMsgList, onBatchType);

        for (int i = 0; i < pMsgList.size(); i++) {
            NMZC600001PMsg pMsg = pMsgList.get(i);
            INVBean invBean = invList.get(i);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int j = 0; j < pMsg.xxMsgIdList.getValidCount(); j++) {
                    invBean.addXxMsgId(pMsg.xxMsgIdList.no(j).xxMsgId.getValue());
                }
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    private void copyDealAmtToFuncAmt(INVBean invBean) {
        INVTMsg invTMsg = invBean.getInvTmsg();
        setValue(invTMsg.invTotFuncInsAmt, invTMsg.invTotDealInsAmt);
        
        List<INV_BOLBean> invBolList = invBean.getInvBolList();
        for (INV_BOLBean invBolBean : invBolList) {
            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

            invBolTMsg.frtFuncTaxAmt.setValue(invBolTMsg.frtDealTaxAmt.getValue());
            invBolTMsg.shipFuncFrtAmt.setValue(invBolTMsg.shipDealFrtAmt.getValue());
            invBolTMsg.shipFuncHdlgChrgAmt.setValue(invBolTMsg.shipDealHdlgChrgAmt.getValue());

            List<INV_LINEBean> invLineList = invBolBean.getInvLineList();

            for (INV_LINEBean invLineBean : invLineList) {

                invLineBean.copyInvLineDealAmtToFuncAmt();
            }
        }
    }

    private boolean exchDealAmtToFuncAmt(List<INVBean> invList) {

        boolean isSuccess = true;

        for (INVBean invBean : invList) {
            INVTMsg invTMsg = invBean.getInvTmsg();

            if (invTMsg.dealCcyCd.getValue().equals(invBean.getFuncCcyCd())) {

                copyDealAmtToFuncAmt(invBean);

            } else {

                if (!calcFuncAmtFromDealAmt(invBean)) {
                    isSuccess = false;
                }
            }
        }
        return isSuccess;
    }

    private boolean getFromMasterForINV(List<INVBean> invBeanList) {

        boolean isSuccess = true;

        Map<String, String> cashMap = new HashMap<String, String>();
        S21LRUMap<String, Object> s21LRUMap = new S21LRUMap<String, Object>();

        for (INVBean invBean : invBeanList) {
            NWZC040001PMsg invPMsg = invBean.getNWZC040001PMsg();
            INVTMsg invTMsg = invBean.getInvTmsg();
            String glblCmpyCd = invPMsg.glblCmpyCd.getValue();
            if (!glblCmpyCd.equals(cashMap.get("glblCmpyCd"))) {
                Map mapRes = (Map) ssmBatchClient.queryObject("queryGLBL_CMPY", invPMsg);
                if (mapRes == null) {
                    invBean.addXxMsgId(NWZM0257E);
                    isSuccess = false;
                } else {
                    invBean.setFuncCcyCd((String) mapRes.get("STD_CCY_CD"));
                    cashMap.put("glblCmpyCd", glblCmpyCd);
                    cashMap.put("glblCmpyNm", (String) mapRes.get("GLBL_CMPY_NM"));
                    cashMap.put("funcCcyCd", (String) mapRes.get("STD_CCY_CD"));
                }
            } else {
                invBean.setFuncCcyCd(cashMap.get("funcCcyCd"));
            }

            if (!hasValue(invPMsg.dealCcyCd)) {
                invTMsg.dealCcyCd.setValue(nvl(invBean.getFuncCcyCd()));
            }

            if (INV_TP.CREDIT_MEMO.equals(invPMsg.invTpCd.getValue()) && hasValue(invPMsg.crDrRsnSubCd)) {
                S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryCR_DR_SUB_RSN", invPMsg, invTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invBean.addXxMsgId(NWZM0444E);
                    isSuccess = false;
                }
            }

            Map mapRes;

            boolean isExport = isExportFlg(invBean);
            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryBILL_TO_and_REM_TO", invPMsg, invTMsg);
            if (ssmRes.getQueryResultCount() == 0 && STS_CD_ONE.equals(invPMsg.invPrintStsCd.getValue()) && !isExport) {
                invBean.addXxMsgId(NWZM0259E);
                isSuccess = false;
            }
            if (!ZYPCommonFunc.hasValue(invBean.getNWZC040001PMsg().invRcpntCustCd)) {
                Map<String, String> mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd", invBean.getNWZC040001PMsg().glblCmpyCd.getValue());
                mapParam.put("billToCust", invBean.getNWZC040001PMsg().billToCustCd.getValue());
                mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

                String invRcpnt = (String)ssmBatchClient.queryObject("queryInvRcpnt", mapParam);
                if (!ZYPCommonFunc.hasValue(invRcpnt)) {
                    isSuccess = false;
                    invBean.addXxMsgId(NWZM1300E);
                } else {
                    ZYPEZDItemValueSetter.setValue(invBean.getNWZC040001PMsg().invRcpntCustCd, invRcpnt);
                    ZYPEZDItemValueSetter.setValue(invBean.getInvTmsg().invRcpntCustCd, invRcpnt);
                }
            }
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", invPMsg.glblCmpyCd.getValue());
            mapParam.put("payerCustCd", invPMsg.payerCustCd.getValue());
            mapParam.put("billTpCd", BILL_TP_REGULAR_INVOICE);
            mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

            mapParam.put("invRcpntCustCd", invPMsg.invRcpntCustCd.getValue());
            ssmRes = ssmEzdClient.queryEZDMsg("queryINV_RCPNT_FOR_DUNS_NUM_BY_INV_RCPNT_CUST_CD", mapParam, invTMsg);
            if (ssmRes.getQueryResultCount() == 0) {
                mapParam.put("rgtnStsCd", RGTN_STS.TERMINATED);
                ssmRes = ssmEzdClient.queryEZDMsg("queryINV_RCPNT_FOR_DUNS_NUM_BY_INV_RCPNT_CUST_CD", mapParam, invTMsg);
            }
            ssmRes = ssmEzdClient.queryEZDMsg("querySELL_TO_and_CNTY", invPMsg, invTMsg);
            if (ssmRes.getQueryResultCount() == 0) {
                invBean.addXxMsgId(NWZM0260E);
                isSuccess = false;
            }

            mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", invPMsg.glblCmpyCd.getValue());
            mapParam.put("sellToCustCd", invPMsg.sellToCustCd.getValue());
            mapParam.put("billTpCd", BILL_TP_REGULAR_INVOICE);
            mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

            mapParam.put("invRcpntCustCd", invPMsg.invRcpntCustCd.getValue());
            ssmRes = ssmEzdClient.queryEZDMsg("queryINV_RCPNT_and_CNTY_BY_INV_RCPNT_CUST_CD", mapParam, invTMsg);

            if (ssmRes.getQueryResultCount() == 0) {
                mapParam.put("rgtnStsCd", RGTN_STS.TERMINATED);
                ssmRes = ssmEzdClient.queryEZDMsg("queryINV_RCPNT_and_CNTY_BY_INV_RCPNT_CUST_CD", mapParam, invTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invBean.addXxMsgId(NWZM0261E);
                    isSuccess = false;
                }
            }

            if (hasValue(invPMsg.pmtTermCashDiscCd)) {
                PMT_TERM_CASH_DISCTMsg pmtTermCashTMsg = new PMT_TERM_CASH_DISCTMsg();
                pmtTermCashTMsg.glblCmpyCd.setValue(invPMsg.glblCmpyCd.getValue());
                pmtTermCashTMsg.pmtTermCashDiscCd.setValue(invPMsg.pmtTermCashDiscCd.getValue()); 

                pmtTermCashTMsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermCashTMsg);
                
                if (pmtTermCashTMsg == null) {
                    invBean.addXxMsgId(NWZM0924E);
                    isSuccess = false;
                } else {
                    invTMsg.pmtTermCd.setValue(pmtTermCashTMsg.pmtTermCd.getValue());
                    invTMsg.cashDiscTermCd.setValue(pmtTermCashTMsg.cashDiscTermCd.getValue());
                    invTMsg.pmtTermCashDiscDescTxt.setValue(pmtTermCashTMsg.pmtTermCashDiscDescTxt.getValue());

                    invPMsg.pmtTermCd.setValue(pmtTermCashTMsg.pmtTermCd.getValue());
                    invPMsg.cashDiscTermCd.setValue(pmtTermCashTMsg.cashDiscTermCd.getValue());
                }
            }

            mapRes = (Map) ssmBatchClient.queryObject("queryPMT_TERM", invPMsg);
            if (mapRes == null) {
                invBean.addXxMsgId(NWZM0262E);
                isSuccess = false;
            } else {

                if (!hasValue(invPMsg.pmtTermNm)) {
                    setValue(invTMsg.pmtTermNm, (String) mapRes.get("PMT_TERM_NM"));
                }

                if (!INV_TP.CREDIT_MEMO.equals(invPMsg.invTpCd.getValue())) {

                     if (hasValue(invPMsg.cashDiscTermCd)) {
                         getFromMasterForINV_CASH_DISC_TERM(invBean);
                     }
                 }
            }

            if (hasValue(invTMsg.slsRepTocCd)) {
                S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
                setValue(s21OrgTmsg.glblCmpyCd, glblCmpyCd);
                setValue(s21OrgTmsg.tocCd,      invTMsg.slsRepTocCd.getValue());
                s21OrgTmsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgTmsg);

                if (s21OrgTmsg == null) {
                    invBean.addXxMsgId(NWZM0263E);
                    isSuccess = false;
                } else {
                    setValue(invTMsg.slsRepFirstOrgCd,  s21OrgTmsg.firstOrgCd.getValue());
                    setValue(invTMsg.slsRepFirstOrgNm,  s21OrgTmsg.firstOrgNm.getValue());
                    setValue(invTMsg.slsRepScdOrgCd,    s21OrgTmsg.scdOrgCd.getValue());
                    setValue(invTMsg.slsRepScdOrgNm,    s21OrgTmsg.scdOrgNm.getValue());
                    setValue(invTMsg.slsRepThirdOrgCd,  s21OrgTmsg.thirdOrgCd.getValue());
                    setValue(invTMsg.slsRepThirdOrgNm,  s21OrgTmsg.thirdOrgNm.getValue());
                    setValue(invTMsg.slsRepFrthOrgCd,   s21OrgTmsg.frthOrgCd.getValue());
                    setValue(invTMsg.slsRepFrthOrgNm,   s21OrgTmsg.frthOrgNm.getValue());
                    setValue(invTMsg.slsRepFifthOrgCd,  s21OrgTmsg.fifthOrgCd.getValue());
                    setValue(invTMsg.slsRepFifthOrgNm,  s21OrgTmsg.fifthOrgNm.getValue());
                    setValue(invTMsg.slsRepSixthOrgCd,  s21OrgTmsg.sixthOrgCd.getValue());
                    setValue(invTMsg.slsRepSixthOrgNm,  s21OrgTmsg.sixthOrgNm.getValue());
                    setValue(invTMsg.slsRepSvnthOrgCd,  s21OrgTmsg.svnthOrgCd.getValue());
                    setValue(invTMsg.slsRepSvnthOrgNm,  s21OrgTmsg.svnthOrgNm.getValue());
                    setValue(invTMsg.slsRepEighthOrgCd, s21OrgTmsg.eighthOrgCd.getValue());
                    setValue(invTMsg.slsRepEighthOrgNm, s21OrgTmsg.eighthOrgNm.getValue());
                    setValue(invTMsg.slsRepNinthOrgCd,  s21OrgTmsg.ninthOrgCd.getValue());
                    setValue(invTMsg.slsRepNinthOrgNm,  s21OrgTmsg.ninthOrgNm.getValue());
                    setValue(invTMsg.slsRepTenthOrgCd,  s21OrgTmsg.tenthOrgCd.getValue());
                    setValue(invTMsg.slsRepTenthOrgNm,  s21OrgTmsg.tenthOrgNm.getValue());
                    setValue(invTMsg.slsRepElvthOrgCd,  s21OrgTmsg.elvthOrgCd.getValue());
                    setValue(invTMsg.slsRepElvthOrgNm,  s21OrgTmsg.elvthOrgNm.getValue());
                    setValue(invTMsg.slsRepTocNm,       s21OrgTmsg.tocNm.getValue());
                }

            }

            if (hasValue(invTMsg.crAnlstPsnCd)) {
                mapRes = (Map) ssmBatchClient.queryObject("queryS21_PSN", invTMsg);
                if (mapRes == null) {
                    invBean.addXxMsgId(NWZM0355E);
                    isSuccess = false;
                } else {
                    String firstNm = nvl((String) mapRes.get("PSN_FIRST_NM"));
                    String midNm = (String) mapRes.get("PSN_MID_NM");
                    String lastNm = nvl((String) mapRes.get("PSN_LAST_NM"));

                    if (hasValue(midNm)) {
                        invTMsg.crAnlstPsnNm.setValue(firstNm + " " + midNm.charAt(0) + "." + " " + lastNm);
                    } else {
                        invTMsg.crAnlstPsnNm.setValue(firstNm + " " + lastNm);
                    }
                }
            }

            if (!hasValue(invPMsg.flPlnFlg)) {
                ssmRes = ssmEzdClient.queryEZDMsg("queryBILL_TO_FOR_FLR_PLN_FLG", invPMsg, invTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invBean.addXxMsgId(NWZM0608E);
                    isSuccess = false;
                }
            }

            if (!hasValue(invPMsg.invMlSendStsCd)) {
                setValue(invTMsg.invMlSendStsCd, STS_CD_PROCESSED);
            }
            if (!hasValue(invPMsg.invEdiSendStsCd)) {
                setValue(invTMsg.invEdiSendStsCd, STS_CD_PROCESSED);
            }
            if (!hasValue(invPMsg.invFaxSendStsCd)) {
                setValue(invTMsg.invFaxSendStsCd, STS_CD_PROCESSED);
            }
            if (!hasValue(invPMsg.invEmlSendStsCd)) {
                setValue(invTMsg.invEmlSendStsCd, STS_CD_PROCESSED);
            }

            NWXC001001RateData rateData = NWXC001001Exchange.getRate(glblCmpyCd, invTMsg.dealCcyCd.getValue(), invTMsg.invDt.getValue());
            if (rateData == null) {
                invBean.addXxMsgId(NWZM0233E);
                isSuccess = false;
            } else if (!rateData.getXxMsgIdList().isEmpty()) {
                for (String msgId : rateData.getXxMsgIdList()) {
                    invBean.addXxMsgId(msgId);
                }
                isSuccess = false;
            } else {
                invBean.setAcctArthTpCd(rateData.getAcctArthTpCd());

                if (!invTMsg.dealCcyCd.getValue().equals(nvl(invBean.getFuncCcyCd())) && !hasValue(invPMsg.actlApplyExchRate)) {
                    invTMsg.actlApplyExchRate.setValue(rateData.getActlExchRate());
                } else if (invTMsg.dealCcyCd.getValue().equals(nvl(invBean.getFuncCcyCd())) && !hasValue(invPMsg.actlApplyExchRate)) {
                    invTMsg.actlApplyExchRate.setValue(BigDecimal.ONE);
                }
            }

            // START 07/17/2020 T.Ogura [QC#57368,MOD]
//            String billToCustCd = invTMsg.billToCustCd.getValue();
            String billToCustCd = invTMsg.soldToCustLocCd.getValue();
            // END   07/17/2020 T.Ogura [QC#57368,MOD]
            String cacheKey = createCacheKey("getSoldToCustLocNm", billToCustCd);
            String custName = (String) s21LRUMap.get(cacheKey);
            if (custName == null) {
                mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd",   glblCmpyCd);
                mapParam.put("billToCustCd", billToCustCd);
                mapParam.put("rgtnStsCd",    RGTN_STS.READY_FOR_ORDER_TAKING);
                custName = (String) ssmBatchClient.queryObject("getSoldToCustLocNm", mapParam);
                if (custName != null) {
                    s21LRUMap.put(cacheKey, custName);
                }
            }
            setValue(invTMsg.soldToCustLocNm, custName);

            //BILL_TO_CUST_ACCT_NM
            String billToCustAcctCd = invTMsg.billToCustAcctCd.getValue();
            cacheKey = createCacheKey("getCustAcctNm", billToCustAcctCd);
            custName = (String) s21LRUMap.get(cacheKey);
            if (custName == null) {
                mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd", glblCmpyCd);
                mapParam.put("dsAcctNum", billToCustAcctCd);
                mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                custName = (String) ssmBatchClient.queryObject("getCustAcctNm", mapParam);
                if (custName == null) {
                    invBean.addXxMsgId(NWZM1509E);
                    isSuccess = false;
                } else {
                    s21LRUMap.put(cacheKey, custName);
                }
            }
            setValue(invTMsg.billToCustAcctNm, custName);
        }
        return isSuccess;
    }
    private boolean getFromMasterForINV_BOL(List<INV_BOLBean> allInvBolList) {

        boolean isSuccess = true;
        S21LRUMap<String, Object> s21LRUMap = new S21LRUMap<String, Object>();

        for (INV_BOLBean invBolBean : allInvBolList) {
            NWZC040002PMsg invBolPMsg = invBolBean.getNWZC040002PMsg();
            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

            if (hasValue(invBolPMsg.carrCd) && !hasValue(invBolPMsg.carrNm)) {
                S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryVND", invBolPMsg, invBolTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invBolBean.addXxMsgId(NWZM0415E);
                    isSuccess = false;
                }
            }

            if (hasValue(invBolPMsg.shpgSvcLvlCd)) {
                S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("querySHPG_SVC_LVL", invBolPMsg, invBolTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invBolBean.addXxMsgId(NWZM0276E);
                    isSuccess = false;
                }
            }

            if (hasValue(invBolPMsg.frtChrgMethCd)) {
                S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryFRT_CHRG_METH", invBolPMsg, invBolTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invBolBean.addXxMsgId(NWZM0277E);
                    isSuccess = false;
                }
            }

            if (hasValue(invBolPMsg.frtChrgToCd)) {
                S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryFRT_CHRG_TO", invBolPMsg, invBolTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invBolBean.addXxMsgId(NWZM0278E);
                    isSuccess = false;
                }
            }
            //SHIP_TO_CUST_ACCT_NM
            if (hasValue(invBolTMsg.shipToCustAcctCd)) {
                String shipToCustAcctCd = invBolTMsg.shipToCustAcctCd.getValue();
                String cacheKey = createCacheKey("getCustAcctNm", shipToCustAcctCd);
                String custName = (String) s21LRUMap.get(cacheKey);
                if (custName == null) {
                    Map<String, String> mapParam = new HashMap<String, String>();
                    mapParam.put("glblCmpyCd", glblCmpyCd);
                    mapParam.put("dsAcctNum", shipToCustAcctCd);
                    mapParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                    custName = (String) ssmBatchClient.queryObject("getCustAcctNm", mapParam);
                    if (custName == null) {
                        invBolBean.addXxMsgId(NWZM1509E);
                        isSuccess = false;
                    } else {
                        s21LRUMap.put(cacheKey, custName);
                    }
                }
                setValue(invBolTMsg.shipToCustAcctNm, custName);

            }
            // ctac psn
            if (hasValue(invBolTMsg.ctacPsnPk)
             && !hasValue(invBolTMsg.shipToCtacPsnFirstNm)
             && !hasValue(invBolTMsg.shipToCtacPsnMidNm)
             && !hasValue(invBolTMsg.shipToCtacPsnLastNm)) {
                CTAC_PSNTMsg ctacPsnTMsg = new CTAC_PSNTMsg();
                setValue(ctacPsnTMsg.glblCmpyCd, glblCmpyCd);
                setValue(ctacPsnTMsg.ctacPsnPk, invBolTMsg.ctacPsnPk.getValue());
                ctacPsnTMsg = (CTAC_PSNTMsg) S21CacheTBLAccessor.findByKey(ctacPsnTMsg);
                if (ctacPsnTMsg == null) {
                    invBolBean.addXxMsgId(NWZM1508E);
                    isSuccess = false;
                } else {
                    setValue(invBolTMsg.shipToCtacPsnFirstNm, ctacPsnTMsg.ctacPsnFirstNm);
                    setValue(invBolTMsg.shipToCtacPsnMidNm, ctacPsnTMsg.ctacPsnMidNm);
                    setValue(invBolTMsg.shipToCtacPsnLastNm, ctacPsnTMsg.ctacPsnLastNm);
                }
            }

        }
        return isSuccess;
    }

    @SuppressWarnings("unchecked")
    private boolean getFromMasterForINV_CASH_DISC_TERM(INVBean invBean) {

        boolean isSuccess = true;

        NWZC040001PMsg invPMsg = invBean.getNWZC040001PMsg();

        String glblCmpyCd = invPMsg.glblCmpyCd.getValue();
        String beforeAdjustNETDueDt = invBean.getBeforeAdjustNETDueDt();
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("glblCmpyCd", glblCmpyCd);
        mapParam.put("cashDiscTermCd", invPMsg.cashDiscTermCd.getValue());
        mapParam.put("invDt", invPMsg.invDt.getValue());

        BigDecimal cashDiscAOT = new BigDecimal(ZYPDateUtil.getDiffDays(beforeAdjustNETDueDt, invPMsg.invDt.getValue()));
        mapParam.put("cashDiscAOT", cashDiscAOT);

        List<Map> ssmResList = (List<Map>) ssmBatchClient.queryObjectList("queryCASH_DISC_TERM", mapParam);

        if (ssmResList.isEmpty()) {
            return isSuccess;
        }

        INVTMsg invTMsg = invBean.getInvTmsg();
        Integer scale = (Integer) ssmBatchClient.queryObject("queryCCY", invTMsg);
        if (scale == null) {
            invBean.addXxMsgId(NWZM0368E);
            isSuccess = false;
        }

        int count = 1;
        for (Map mapRes : ssmResList) {
            INV_CASH_DISC_TERMBean invCashDiscBean = new INV_CASH_DISC_TERMBean(invPMsg);
            INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg = invCashDiscBean.getInvCashDiscTermTMsg();

            BigDecimal cashDiscPct = (BigDecimal) mapRes.get("CASH_DISC_PCT");
            invCashDiscTermTMsg.invCashDiscRatioPct.setValue(cashDiscPct);

            int minusCashDiscFromAOT = ((BigDecimal) mapRes.get("CASH_DISC_FROM_AOT")).negate().intValue();

            String cashDiscDueDt = ZYPDateUtil.addDays(beforeAdjustNETDueDt, minusCashDiscFromAOT);

            invCashDiscTermTMsg.invCashDiscDueDt.setValue(cashDiscDueDt);

            BigDecimal invTotDealNetAmt = getBigDecimal(invPMsg.invTotDealNetAmt, "0");
            BigDecimal invTotDealFrtAmt = getBigDecimal(invPMsg.invTotDealFrtAmt, "0");
            BigDecimal invTotDealTaxAmt = getBigDecimal(invPMsg.invTotDealTaxAmt, "0");

            BigDecimal invCashDiscAmt = invTotDealNetAmt.subtract(invTotDealNetAmt.subtract(invTotDealFrtAmt).subtract(invTotDealTaxAmt).multiply(cashDiscPct.movePointLeft(2)));
            invCashDiscTermTMsg.invCashDiscAmt.setValue(invCashDiscAmt.setScale(scale, HALF_UP));

            String invCashDiscTermDtlNum = ZYPCommonFunc.leftPad(Integer.toString(count++), DIGIT_INV_CASH_DISC_TERM_DTL_NUM, "0");
            invCashDiscTermTMsg.invCashDiscTermDtlNum.setValue(invCashDiscTermDtlNum);

            invBean.addInvCashDiscTerm(invCashDiscBean);
        }

        return isSuccess;
    }
    
    private boolean getFromMasterForINV_LINE(List<INV_LINEBean> allInvLineList) {

        boolean isSuccess = true;

        try {

            S21LRUMap<String, Object> s21LRUMap = new S21LRUMap<String, Object>();

            for (INV_LINEBean invLineBean : allInvLineList) {
                
                NWZC040003PMsg invLinePMsg = invLineBean.getNWZC040003PMsg();
                INV_LINETMsg   invLineTMsg = invLineBean.getInvLineTMsg();
                NWZC040001PMsg invPMsg     = invLineBean.getNWZC040001PMsg();
                // COA_CMPY_CD
                setValue(invLineTMsg.coaCmpyCd, ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_COA_CMPY_CD, invLinePMsg.glblCmpyCd.getValue()));
                // COA_EXTN_CD
                setValue(invLineTMsg.coaExtnCd, ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_COA_EXTN_CD, invLinePMsg.glblCmpyCd.getValue()));
                // COA_AFFL_CD
                S21SsmEZDResult ssmRes1 = ssmEzdClient.queryEZDMsg("queryBILL_TO_FOR_COA_AFFL_CD", invPMsg, invLineTMsg);
                if (ssmRes1.getQueryResultCount() == 0) {
                    invLineBean.addXxMsgId(NWZM0608E);
                    isSuccess = false;
                }

                S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryS21_ORG_FOR_INV_LINE", invLinePMsg, invLineTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invLineBean.addXxMsgId(NWZM0263E);
                    isSuccess = false;
                }

                Map mapRes = (Map) ssmBatchClient.queryObject("queryMDSE", invLinePMsg);
                if (mapRes == null) {
                    if (!(SYS_SRC.S21_SERVICE_AND_REPAIR.equals(this.sysSrcCd) || SYS_SRC.ESTORE.equals(this.sysSrcCd)
                            || SYS_SRC.S21_PARTS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS_EXPORT.equals(this.sysSrcCd))) {
                        invLineBean.addXxMsgId(NWZM0265E);
                        isSuccess = false;
                    }
                    
                } else {
                    if (Y.equals((String) mapRes.get("EXP_ITEM_FLG"))) {
                        int mdseNmDigit = invLinePMsg.getAttr("mdseNm").getDigit();
                        if (hasValue(invLinePMsg.univsPrmoId)) {
                            String prmoShortNm = invLinePMsg.prmoShortNm.getValue();
                            if (mdseNmDigit < prmoShortNm.length()) {
                                prmoShortNm = prmoShortNm.substring(0, mdseNmDigit);
                            }
                            setValue(invLineTMsg.mdseNm, prmoShortNm);
                        } else {
                            String crDrSubRsnNm = (String) ssmBatchClient.queryObject("queryCR_DR_SUB_RSN_FOR_MDSE_NM", invLinePMsg);
                            if (mdseNmDigit < crDrSubRsnNm.length()) {
                                crDrSubRsnNm = crDrSubRsnNm.substring(0, mdseNmDigit);
                            }
                            setValue(invLineTMsg.mdseNm, crDrSubRsnNm);
                        }
                    } else if (!hasValue(invLinePMsg.mdseNm)) {
                        setValue(invLineTMsg.mdseNm, (String) mapRes.get("MDSE_NM"));
                    }
                    if (!hasValue(invLinePMsg.coaProdCd)) {
                        setValue(invLineTMsg.coaProdCd, (String) mapRes.get("COA_PROD_CD"));
                    }
                    setValue(invLineTMsg.zerothProdCtrlCd, (String) mapRes.get("ZEROTH_PROD_CTRL_CD"));
                    setValue(invLineTMsg.zerothProdCtrlNm, (String) mapRes.get("ZEROTH_PROD_CTRL_NM"));
                    setValue(invLineTMsg.firstProdCtrlCd, (String) mapRes.get("FIRST_PROD_CTRL_CD"));
                    setValue(invLineTMsg.firstProdCtrlNm, (String) mapRes.get("FIRST_PROD_CTRL_NM"));
                    setValue(invLineTMsg.scdProdCtrlCd, (String) mapRes.get("SCD_PROD_CTRL_CD"));
                    setValue(invLineTMsg.scdProdCtrlNm, (String) mapRes.get("SCD_PROD_CTRL_NM"));
                    setValue(invLineTMsg.thirdProdCtrlCd, (String) mapRes.get("THIRD_PROD_CTRL_CD"));
                    setValue(invLineTMsg.thirdProdCtrlNm, (String) mapRes.get("THIRD_PROD_CTRL_NM"));
                    setValue(invLineTMsg.frthProdCtrlCd, (String) mapRes.get("FRTH_PROD_CTRL_CD"));
                    setValue(invLineTMsg.frthProdCtrlNm, (String) mapRes.get("FRTH_PROD_CTRL_NM"));
                    setValue(invLineTMsg.fifthProdCtrlCd, (String) mapRes.get("FIFTH_PROD_CTRL_CD"));
                    setValue(invLineTMsg.fifthProdCtrlNm, (String) mapRes.get("FIFTH_PROD_CTRL_NM"));
                    setValue(invLineTMsg.sixthProdCtrlCd, (String) mapRes.get("SIXTH_PROD_CTRL_CD"));
                    setValue(invLineTMsg.sixthProdCtrlNm, (String) mapRes.get("SIXTH_PROD_CTRL_NM"));
                    setValue(invLineTMsg.svnthProdCtrlCd, (String) mapRes.get("SVNTH_PROD_CTRL_CD"));
                    setValue(invLineTMsg.svnthProdCtrlNm, (String) mapRes.get("SVNTH_PROD_CTRL_NM"));

                    setValue(invLineTMsg.mdseTpCd, (String) mapRes.get("MDSE_TP_CD"));
                    String slsRepTocCd = invLineTMsg.slsRepTocCd.getValue();
                    String cacheKey = createCacheKey("queryTRTY_STRU", slsRepTocCd);
                    Map resultMap = (Map<String, String>) s21LRUMap.get(cacheKey);
                    if (resultMap == null) {
                        Map<String, String> mapParam = new HashMap<String, String>();
                        mapParam.put("glblCmpyCd", invLineTMsg.glblCmpyCd.getValue());
                        mapParam.put("tocCd", slsRepTocCd);
                        resultMap = (Map<String, String>) ssmBatchClient.queryObject("queryTRTY_STRU", mapParam);
                    }
                    if (resultMap != null) {
                        setValue(invLineTMsg.dsFirstOrgCd,  (String) resultMap.get("FIRST_ORG_CD"));
                        setValue(invLineTMsg.dsFirstOrgNm,  (String) resultMap.get("FIRST_ORG_NM"));
                        setValue(invLineTMsg.dsScdOrgCd,    (String) resultMap.get("SCD_ORG_CD"));
                        setValue(invLineTMsg.dsScdOrgNm,    (String) resultMap.get("SCD_ORG_NM"));
                        setValue(invLineTMsg.dsThirdOrgCd,  (String) resultMap.get("THIRD_ORG_CD"));
                        setValue(invLineTMsg.dsThirdOrgNm,  (String) resultMap.get("THIRD_ORG_NM"));
                        setValue(invLineTMsg.dsFrthOrgCd,   (String) resultMap.get("FRTH_ORG_CD"));
                        setValue(invLineTMsg.dsFrthOrgNm,   (String) resultMap.get("FRTH_ORG_NM"));
                        setValue(invLineTMsg.dsFifthOrgCd,  (String) resultMap.get("FIFTH_ORG_CD"));
                        setValue(invLineTMsg.dsFifthOrgNm,  (String) resultMap.get("FIFTH_ORG_NM"));
                        setValue(invLineTMsg.dsSixthOrgCd,  (String) resultMap.get("SIXTH_ORG_CD"));
                        setValue(invLineTMsg.dsSixthOrgNm,  (String) resultMap.get("SIXTH_ORG_NM"));
                        setValue(invLineTMsg.dsSvnthOrgCd,  (String) resultMap.get("SVNTH_ORG_CD"));
                        setValue(invLineTMsg.dsSvnthOrgNm,  (String) resultMap.get("SVNTH_ORG_NM"));
                        setValue(invLineTMsg.dsEighthOrgCd, (String) resultMap.get("EIGHTH_ORG_CD"));
                        setValue(invLineTMsg.dsEighthOrgNm, (String) resultMap.get("EIGHTH_ORG_NM"));
                        setValue(invLineTMsg.dsNinthOrgCd,  (String) resultMap.get("NINTH_ORG_CD"));
                        setValue(invLineTMsg.dsNinthOrgNm,  (String) resultMap.get("NINTH_ORG_NM"));
                        setValue(invLineTMsg.dsTenthOrgCd,  (String) resultMap.get("TENTH_ORG_CD"));
                        setValue(invLineTMsg.dsTenthOrgNm,  (String) resultMap.get("TENTH_ORG_NM"));
                        setValue(invLineTMsg.bizAreaOrgCd,  (String) resultMap.get("FIRST_BIZ_AREA_ORG_CD"));
                        BIZ_AREA_ORGTMsg bizAreaOrgTmsg = new BIZ_AREA_ORGTMsg();
                        setValue(bizAreaOrgTmsg.glblCmpyCd, glblCmpyCd);
                        setValue(bizAreaOrgTmsg.bizAreaOrgCd, invLineTMsg.bizAreaOrgCd.getValue());
                        bizAreaOrgTmsg = (BIZ_AREA_ORGTMsg) S21CacheTBLAccessor.findByKey(bizAreaOrgTmsg);
                        if (bizAreaOrgTmsg != null) {
                            setValue(invLineTMsg.bizAreaOrgNm, bizAreaOrgTmsg.bizAreaOrgNm.getValue());
                        }
                    }

                    //MDL_NM
                    if (hasValue(invLineTMsg.mdlId)) {
                        MDL_NMTMsg mdlNmTmsg = new MDL_NMTMsg();
                        setValue(mdlNmTmsg.t_GlblCmpyCd, glblCmpyCd);
                        setValue(mdlNmTmsg.t_MdlId, invLineTMsg.mdlId.getValue());
                        mdlNmTmsg = (MDL_NMTMsg) S21CacheTBLAccessor.findByKey(mdlNmTmsg);
                        if (mdlNmTmsg != null) {
                            setValue(invLineTMsg.mdlNm, mdlNmTmsg.t_MdlNm.getValue());
                        }
                    }

                    //org nm
                    if (hasValue(invLineTMsg.slsRepTocCd)) {
                        S21_ORGTMsg s21OrgTmsg = new S21_ORGTMsg();
                        setValue(s21OrgTmsg.glblCmpyCd, glblCmpyCd);
                        setValue(s21OrgTmsg.tocCd, invLineTMsg.slsRepTocCd.getValue());
                        s21OrgTmsg = (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(s21OrgTmsg);
                        if (s21OrgTmsg == null) {
                            invLineBean.addXxMsgId(NWZM0263E);
                            isSuccess = false;
                        } else {
                            setValue(invLineTMsg.firstOrgCd, s21OrgTmsg.firstOrgCd.getValue());
                            setValue(invLineTMsg.firstOrgNm, s21OrgTmsg.firstOrgNm.getValue());
                            setValue(invLineTMsg.scdOrgCd, s21OrgTmsg.scdOrgCd.getValue());
                            setValue(invLineTMsg.scdOrgNm, s21OrgTmsg.scdOrgNm.getValue());
                            setValue(invLineTMsg.thirdOrgCd, s21OrgTmsg.thirdOrgCd.getValue());
                            setValue(invLineTMsg.thirdOrgNm, s21OrgTmsg.thirdOrgNm.getValue());
                            setValue(invLineTMsg.frthOrgCd, s21OrgTmsg.frthOrgCd.getValue());
                            setValue(invLineTMsg.frthOrgNm, s21OrgTmsg.frthOrgCd.getValue());
                            setValue(invLineTMsg.fifthOrgCd, s21OrgTmsg.fifthOrgCd.getValue());
                            setValue(invLineTMsg.sixthOrgCd, s21OrgTmsg.sixthOrgCd.getValue());
                            setValue(invLineTMsg.sixthOrgNm, s21OrgTmsg.sixthOrgNm.getValue());
                            setValue(invLineTMsg.svnthOrgCd, s21OrgTmsg.svnthOrgCd.getValue());
                            setValue(invLineTMsg.svnthOrgNm, s21OrgTmsg.svnthOrgNm.getValue());
                            setValue(invLineTMsg.eighthOrgCd, s21OrgTmsg.eighthOrgCd.getValue());
                            setValue(invLineTMsg.eighthOrgNm, s21OrgTmsg.eighthOrgNm.getValue());
                            setValue(invLineTMsg.ninthOrgCd, s21OrgTmsg.ninthOrgCd.getValue());
                            setValue(invLineTMsg.ninthOrgNm, s21OrgTmsg.ninthOrgNm.getValue());
                            setValue(invLineTMsg.tenthOrgCd, s21OrgTmsg.tenthOrgCd.getValue());
                            setValue(invLineTMsg.tenthOrgNm, s21OrgTmsg.tenthOrgNm.getValue());
                            setValue(invLineTMsg.elvthOrgCd, s21OrgTmsg.elvthOrgCd.getValue());
                            setValue(invLineTMsg.elvthOrgNm, s21OrgTmsg.elvthOrgNm.getValue());
                        }
                    }

                }

                if (hasValue(invLineTMsg.coaProdCd)) {

                    Map<String, String> mapParam = new HashMap<String, String>();
                    mapParam.put("glblCmpyCd", invLinePMsg.glblCmpyCd.getValue());
                    mapParam.put("coaProdCd", invLineTMsg.coaProdCd.getValue());
                    mapParam.put("slsRepTocCd", invLineTMsg.slsRepTocCd.getValue());

                    if (TRX.SALES.equals(invLineTMsg.trxCd.getValue()) && COA_PROD.ADMINISTRATION.equals(invLineTMsg.coaProdCd.getValue())) {
                        ssmRes = ssmEzdClient.queryEZDMsg("queryCOA_PROD_BySalesRep", mapParam, invLineTMsg);
                    } else {
                        ssmRes = ssmEzdClient.queryEZDMsg("queryCOA_PROD", mapParam, invLineTMsg);
                    }

                    if (ssmRes.getQueryResultCount() == 0) {
                        if (!(SYS_SRC.S21_SERVICE_AND_REPAIR.equals(this.sysSrcCd) || SYS_SRC.ESTORE.equals(this.sysSrcCd)
                                || SYS_SRC.S21_PARTS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS_EXPORT.equals(this.sysSrcCd))) {
                            invLineBean.addXxMsgId(NWZM0265E);
                            isSuccess = false;
                        }
    
                        continue;
                    }
                }

                if (hasValue(invLineTMsg.brCd)) {
                    ssmEzdClient.queryEZDMsg("queryBR", invLineTMsg, invLineTMsg);
                }

                if (Y.equals(invLineTMsg.mercCntnFlg.getValue())) {
                    Map<String, String> mapParam = new HashMap<String, String>();
                    mapParam.put("glblCmpyCd", invLinePMsg.glblCmpyCd.getValue());
                    mapParam.put("sftyInvTxtCd", SFTY_INV_TXT.TEXT_TO_BE_PRINTED_ON_INVOICE_FOR_MDSE_WITH_HG);
                    mapRes = (Map) ssmBatchClient.queryObject("querySFTY_INV_TXT", mapParam);

                    if (mapRes != null) {
                        String firstSftyInvTxt = nvl((String) mapRes.get("FIRST_SFTY_INV_TXT"));
                        String scdSftyInvTxt = nvl((String) mapRes.get("SCD_SFTY_INV_TXT"));
                        String thirdSftyInvTxt = nvl((String) mapRes.get("THIRD_SFTY_INV_TXT"));
                        setValue(invLineTMsg.mercStmtOnInvTxt, (firstSftyInvTxt + " " + scdSftyInvTxt + " " + thirdSftyInvTxt).trim());
                    }
                }
                // QC#21841 2018/05/21 Add Start
                if (!ZYPCommonFunc.hasValue(invLineTMsg.invLineCatgCd)) {
                    setValue(invLineTMsg.invLineCatgCd, INV_LINE_CATG.ITEM);
                }
                // QC#21841 2018/05/21 Add End
            }

            return isSuccess;

        } finally {

            if (isSuccess) {

                String glblCmpyCd = allInvLineList.get(0).getNWZC040003PMsg().glblCmpyCd.getValue();

                String invLineCoaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_INV_LINE_COA_ACCT_CD, glblCmpyCd);
                String offInvCoaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_OFF_INV_COA_ACCT_CD, glblCmpyCd);

                List<String> invLineCoaAcctLst = new ArrayList<String>();
                List<String> offInvCoaAcctLst = new ArrayList<String>();

                if (hasValue(invLineCoaAcctCd)) {
                    invLineCoaAcctLst = Arrays.asList(invLineCoaAcctCd.split(","));
                }
                if (hasValue(offInvCoaAcctCd)) {
                    offInvCoaAcctLst = Arrays.asList(offInvCoaAcctCd.split(","));
                }

                final S21LRUMap<String, String> cache = new S21LRUMap<String, String>();

                // --------------------------------------------------
                // Cost to Zero.
                //  + 010-02 : Sales Without Cost
                //  + 010-03 : Credti or Debit
                // --------------------------------------------------
                for (INV_LINEBean invLineBean : allInvLineList) {

                    final INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
                    String trxCd    = invLineTMsg.trxCd.getValue();
                    String trxRsnCd = invLineTMsg.trxRsnCd.getValue();
                    String crDrSubRsnCd = invLineTMsg.crDrRsnSubCd.getValue();

                    String cacheKey = createCacheKey("drSubRsnCd", crDrSubRsnCd);

                    if (!offInvCoaAcctLst.isEmpty()) {

                        List<INV_PRMO_INFOBean> invPrmoInfoList = invLineBean.getInvPrmoInfoList();

                        for (INV_PRMO_INFOBean invPrmoInfoBean : invPrmoInfoList) {

                            INV_PRMO_INFOTMsg invPrmoInfoTMsg = invPrmoInfoBean.getInvPrmoInfoTMsg();

                            if (hasValue(invPrmoInfoTMsg.coaAcctCd) && offInvCoaAcctLst.contains(invPrmoInfoTMsg.coaAcctCd.getValue())) {

                                for (String invLineCoaAcct : invLineCoaAcctLst) {

                                    String[] value = invLineCoaAcct.split("-");

                                    if (value.length == 4) {

                                        // -------------------------------------------------------------------------------------------------
                                        // Variable Character Const Table
                                        //  VAR_CHAR_CONST_VAL setting
                                        //  value + "," + value + ",".....
                                        //   value = TRX_CD + "-" + TRX_RSN_CD + "-" + CR_DR_SUB_RSN.PRC_PROT_CATG_CD + "-" + COA_ACCT_CD
                                        // -------------------------------------------------------------------------------------------------
                                        String prcProtCatgCd = "";

                                        String constTrxCd = value[0];
                                        String constTrxRsnCd = value[1];
                                        String constPrcProtCatgCd = value[2];
                                        String constCoaAcctCd = value[3];

                                        if (hasValue(constPrcProtCatgCd) && hasValue(crDrSubRsnCd)) {
                                            prcProtCatgCd = cache.get(cacheKey);
                                            if (!hasValue(prcProtCatgCd)) {
                                                CR_DR_SUB_RSNTMsg crDrSubRsnMsg = findCrDrSubRsnByKey(glblCmpyCd, crDrSubRsnCd);
                                                prcProtCatgCd = crDrSubRsnMsg.prcProtCatgCd.getValue();
                                                cache.put(cacheKey, prcProtCatgCd);
                                            }
                                        }

                                        if (checkValue(trxCd, constTrxCd) && checkValue(trxRsnCd, constTrxRsnCd) && checkValue(prcProtCatgCd, constPrcProtCatgCd)) {
                                            setValue(invPrmoInfoTMsg.coaAcctCd, constCoaAcctCd);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean getFromMasterForINV_PRMO_INFO(INV_LINEBean invLineBean) {

        boolean isSuccess = true;

        List<INV_PRMO_INFOBean> invPrmoInfoList = invLineBean.getInvPrmoInfoList();

        for (INV_PRMO_INFOBean invPrmoInfoBean : invPrmoInfoList) {

            NWZC040004PMsg invPrmoInfoPMsg = invPrmoInfoBean.getNWZC040004PMsg();
            INV_PRMO_INFOTMsg invPrmoInfoTMsg = invPrmoInfoBean.getInvPrmoInfoTMsg();

            if (hasValue(invPrmoInfoPMsg.prmoPk)) {
                S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryPRMO", invPrmoInfoPMsg, invPrmoInfoTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invPrmoInfoBean.addXxMsgId(NWZM0437E);
                    isSuccess = false;
                }
            }

            if (hasValue(invPrmoInfoPMsg.prmoCatgPk)) {
                S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("queryPRMO_CATG", invPrmoInfoPMsg, invPrmoInfoTMsg);
                if (ssmRes.getQueryResultCount() == 0) {
                    invPrmoInfoBean.addXxMsgId(NWZM0438E);
                    isSuccess = false;
                }
            }
        }
        return isSuccess;
    }

    private void execInsert(List<INVTMsg> invTmsgList
                              , List<INV_BOLTMsg> invBolTmsgList
                              , List<INV_LINETMsg> invLineTmsgList
                              , List<INV_PRMO_INFOTMsg> invPrmoInfoTmsgList
                              , List<INV_CASH_DISC_TERMTMsg>invCashDiscTermTmsgList
                              , List<DS_INV_LINE_TAX_DTLTMsg> dsInvLineTaxDtlTmsgList
                              , List<DS_INV_LINE_SER_NUMTMsg> dsInvLineSerNumTmsgList
                              , List<DS_INV_SLS_CRTMsg> dsInvSlsCrTmsgList
    ) {

        // insert
        if (invTmsgList.size() > 0) {
            INVTMsg[] invTmsgs = new INVTMsg[invTmsgList.size()];
            for (int i = 0; i < invTmsgList.size(); i++) {
                INVTMsg invTmsg = invTmsgList.get(i);
                invTmsgs[i] = invTmsg;
            }
            S21FastTBLAccessor.insert(invTmsgs);
        }

        if (invBolTmsgList.size() > 0) {
            INV_BOLTMsg[] invBolTmsgs = new INV_BOLTMsg[invBolTmsgList.size()];
            for (int i = 0; i < invBolTmsgList.size(); i++) {
                INV_BOLTMsg tmsg = invBolTmsgList.get(i);
                invBolTmsgs[i] = tmsg;
            }
            S21FastTBLAccessor.insert(invBolTmsgs);
        }

        if (invLineTmsgList.size() > 0) {
            INV_LINETMsg[] invLineTmsgs = new INV_LINETMsg[invLineTmsgList.size()];
            for (int i = 0; i < invLineTmsgList.size(); i++) {
                INV_LINETMsg tmsg = invLineTmsgList.get(i);
                invLineTmsgs[i] = tmsg;
            }
            S21FastTBLAccessor.insert(invLineTmsgs);
        }

        if (invPrmoInfoTmsgList.size() > 0) {
            INV_PRMO_INFOTMsg[] invPrmoInfoTmsgs = new INV_PRMO_INFOTMsg[invPrmoInfoTmsgList.size()];
            for (int i = 0; i < invPrmoInfoTmsgList.size(); i++) {
                INV_PRMO_INFOTMsg tmsg = invPrmoInfoTmsgList.get(i);
                invPrmoInfoTmsgs[i] = tmsg;
            }
            S21FastTBLAccessor.insert(invPrmoInfoTmsgs);
        }

        if (invCashDiscTermTmsgList.size() > 0) {

            // [INV] : grouping by 'INV_NUM'.
            final Map<String, INVTMsg> invTMsgMap = new HashMap<String, INVTMsg>();
            for (INVTMsg invTMsg : invTmsgList) {
                final String invNum = invTMsg.invNum.getValue();
                if (!invTMsgMap.containsKey(invNum)) {
                    invTMsgMap.put(invNum, invTMsg);
                }
            }

            // [INV_CASH_DISC_TERM] : grouping by 'INV_NUM'.
            final Map<String, List<INV_CASH_DISC_TERMTMsg>> invCashDiscTermTMsgMap = new HashMap<String, List<INV_CASH_DISC_TERMTMsg>>();
            for (INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg : invCashDiscTermTmsgList) {
                final String invNum = invCashDiscTermTMsg.invNum.getValue();
                if (!invCashDiscTermTMsgMap.containsKey(invNum)) {
                    invCashDiscTermTMsgMap.put(invNum, new ArrayList<INV_CASH_DISC_TERMTMsg>());
                }
                invCashDiscTermTMsgMap.get(invNum).add(invCashDiscTermTMsg);
            }

            for (Map.Entry<String, List<INV_CASH_DISC_TERMTMsg>> entry : invCashDiscTermTMsgMap.entrySet()) {

                final String                       invNum                  = entry.getKey();
                final List<INV_CASH_DISC_TERMTMsg> invCashDiscTermTMsgList = entry.getValue();
                
                if (invCashDiscTermTMsgList.isEmpty()) {
                    invCashDiscTermTMsgMap.remove(invNum);
                    continue;
                }

                final INVTMsg invTMsg = invTMsgMap.get(invNum);

                if (invTMsg != null) {

                    // create a 'net due date record'. (Percentage = 0%)
                    final INV_CASH_DISC_TERMTMsg netDueDateRecord = new INV_CASH_DISC_TERMTMsg();
                    setValue(netDueDateRecord.glblCmpyCd,          invTMsg.glblCmpyCd);
                    setValue(netDueDateRecord.invNum,              invTMsg.invNum);
                    setValue(netDueDateRecord.invCashDiscDueDt,    invTMsg.netDueDt);
                    setValue(netDueDateRecord.invCashDiscRatioPct, ZERO);
                    setValue(netDueDateRecord.invCashDiscAmt,      invTMsg.invTotDealNetAmt);

                    invCashDiscTermTMsgList.add(netDueDateRecord);

                    // order by 'INV_CASH_DISC_DUE_DT' ASC.
                    Collections.sort(invCashDiscTermTMsgList, 
                            new Comparator<INV_CASH_DISC_TERMTMsg>(){
                                public int compare(INV_CASH_DISC_TERMTMsg tMsg1, INV_CASH_DISC_TERMTMsg tMsg2) {
                                    return tMsg1.invCashDiscDueDt.getValue().compareTo(tMsg2.invCashDiscDueDt.getValue());
                                }
                            }
                    );
                }
            }

            // ***** insert
            for (Map.Entry<String, List<INV_CASH_DISC_TERMTMsg>> entry : invCashDiscTermTMsgMap.entrySet()) {
                // INV_CASH_DISC_TERM_DTL_NUM
                int invCashDiscTermDtlNum = 1;
                for (INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg : entry.getValue()) {
                    setValue(invCashDiscTermTMsg.invCashDiscTermDtlNum, Integer.toString(invCashDiscTermDtlNum++));
                    S21ApiTBLAccessor.insert(invCashDiscTermTMsg);
                }
            }
        }

        // insert invoice line tax detail
        if (dsInvLineTaxDtlTmsgList.size() > 0) {
            DS_INV_LINE_TAX_DTLTMsg[] dsInvLineTaxDtlTmsgs = dsInvLineTaxDtlTmsgList.toArray(new DS_INV_LINE_TAX_DTLTMsg[dsInvLineTaxDtlTmsgList.size()]);
            S21FastTBLAccessor.insert(dsInvLineTaxDtlTmsgs);
        }
        // insert invoice line serial number
        if (dsInvLineSerNumTmsgList.size() > 0) {
            DS_INV_LINE_SER_NUMTMsg[] dsInvLineSerNumTmsgs = dsInvLineSerNumTmsgList.toArray(new DS_INV_LINE_SER_NUMTMsg[dsInvLineSerNumTmsgList.size()]);
            S21FastTBLAccessor.insert(dsInvLineSerNumTmsgs);
        }
        // insert invoice sales credit
        if (!isReturn) {
            if (dsInvSlsCrTmsgList.size() > 0) {
                DS_INV_SLS_CRTMsg[] dsInvSlsCrTmsgs = dsInvSlsCrTmsgList.toArray(new DS_INV_SLS_CRTMsg[dsInvSlsCrTmsgList.size()]);
                S21FastTBLAccessor.insert(dsInvSlsCrTmsgs);
            }
        }

    }

    private boolean canInsertINV_BOL(List<INV_BOLBean> invBolList, List<INV_BOLTMsg> invBolTmsgList) {
        
        for (INV_BOLBean invBolBean : invBolList) {
            INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

            INV_BOLTMsg tmsg = (INV_BOLTMsg) S21ApiTBLAccessor.findByKey(invBolTMsg);
            if (tmsg != null) {
                invBolBean.addXxMsgId(NWZM0272E);
                return false;
            }
            invBolTmsgList.add(invBolTMsg);
        }
        return true;
    }

    private boolean insertINV_CASH_DISC_TERM(INVBean invBean, List<INV_CASH_DISC_TERMTMsg> invCashDiscTMsgList) {

        List<INV_CASH_DISC_TERMBean> invCashDiscTermList = invBean.getInvCashDiscTermList();

        for (INV_CASH_DISC_TERMBean invCashDiscTermBean : invCashDiscTermList) {
            INV_CASH_DISC_TERMTMsg invCashDiscTermTMsg = invCashDiscTermBean.getInvCashDiscTermTMsg();

            INV_CASH_DISC_TERMTMsg tmsg = (INV_CASH_DISC_TERMTMsg) S21ApiTBLAccessor.findByKey(invCashDiscTermTMsg);
            if (tmsg != null) {
                invBean.addXxMsgId(NWZM0271E);
                return false;
            }
            invCashDiscTMsgList.add(invCashDiscTermTMsg);
       }
        return true;
    }

    private boolean canInsertINV_LINE(List<INV_LINEBean> invLineList, List<INV_LINETMsg> invLineTmsgList) {

        for (INV_LINEBean invLineBean : invLineList) {

            INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
            if (!hasValue(invLineTMsg.invLineSubTrxNum)) {
                invLineTMsg.invLineSubTrxNum.setValue(FIXED_INV_LINE_SUB_TRX_NUM);
            }

            INV_LINETMsg tmsg = (INV_LINETMsg) S21ApiTBLAccessor.findByKey(invLineTMsg);
            if (tmsg != null) {
                invLineBean.addXxMsgId(NWZM0273E);
                return false;
            }
            invLineTmsgList.add(invLineTMsg);
        }
        return true;
    }

    private boolean canInsertINV_PRMO_INFO(List<INV_PRMO_INFOBean> invPrmoInfoList, List<INV_PRMO_INFOTMsg> invPrmoInfoTmsgList) {

        for (INV_PRMO_INFOBean invPrmoInfoBean : invPrmoInfoList) {

            INV_PRMO_INFOTMsg invPrmoInfoTMsg = invPrmoInfoBean.getInvPrmoInfoTMsg();
            BigDecimal invPrmoInfopk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.INV_PRMO_INFO_SQ);
            invPrmoInfoTMsg.invPrmoInfoPk.setValue(invPrmoInfopk);
            if (!hasValue(invPrmoInfoTMsg.invLineSubTrxNum)) {
                invPrmoInfoTMsg.invLineSubTrxNum.setValue(FIXED_INV_LINE_SUB_TRX_NUM);
            }

            INV_PRMO_INFOTMsg tmsg = (INV_PRMO_INFOTMsg) S21ApiTBLAccessor.findByKey(invPrmoInfoTMsg);
            if (tmsg != null) {
                invPrmoInfoBean.addXxMsgId(NWZM0273E);
                return false;
            }
            invPrmoInfoTmsgList.add(invPrmoInfoTMsg);
        }
        return true;
    }

    private boolean canInsertINVandINV_CASH_DISC_TERM(List<INVBean> invList, List<INVTMsg> invTMsgList, List<INV_CASH_DISC_TERMTMsg> invCashDiscTMsgList) {

        for (INVBean invBean : invList) {
            INVTMsg invTMsg = invBean.getInvTmsg();
            INVTMsg tmsg = (INVTMsg) S21ApiTBLAccessor.findByKey(invTMsg);
            if (tmsg != null) {
                String maxInvNum = (String)ssmBatchClient.queryObject("queryMaxInvNum", invTMsg);

                int branchNumber = 0;

                if (maxInvNum.indexOf("-") >= 0) {
                    String[] maxInvNumLst = maxInvNum.split("-");
                    branchNumber = Integer.parseInt(maxInvNumLst[maxInvNumLst.length - 1]);
                }

                int branchNumberLength = DIGIT_INV_NUM - invTMsg.invNum.getValue().length() - 1;

                String strBranchNumber = ZYPCommonFunc.leftPad(Integer.toString(branchNumber + 1), branchNumberLength, "0");

                String invNum = invTMsg.invNum.getValue() + "-" + strBranchNumber;

                if (DIGIT_INV_NUM < invNum.length()) {
                    invBean.addXxMsgId(NWZM0269E);
                    return false;
                }
                invBean.setInvNum(invNum);

            } else {
                invBean.setInvNum(invTMsg.invNum.getValue());
            }
            invTMsgList.add(invTMsg);

            boolean isSuccess = insertINV_CASH_DISC_TERM(invBean, invCashDiscTMsgList);
            if (!isSuccess) {
                return false;
            }
        }
        return true;
    }

    private boolean invHeaderMandatoryByConditionCheck(final List<NWZC040001PMsg> invHeaderPmsgList) {

        boolean isSuccess = true;

        for (NWZC040001PMsg invPMsg : invHeaderPmsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(invPMsg);

            if ((SYS_SRC.ROSS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS_EXPORT.equals(this.sysSrcCd) || SYS_SRC.ESTORE.equals(this.sysSrcCd))
                    && !hasValue(invPMsg.invNum)) {
                msgMap.addXxMsgId(NWZM0689E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.crCardChrgCpltCd)) {

                String ccPmtTermCashDisc = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_CC_CASH_DISC_CD, invPMsg.glblCmpyCd.getValue());
                if (ZYPCommonFunc.hasValue(ccPmtTermCashDisc)){
                    List<String> ccPmtTermCashDiscList = asList(ccPmtTermCashDisc.split(","));
                    if (ccPmtTermCashDiscList.contains(invPMsg.pmtTermCashDiscCd.getValue())) {
                        invPMsg.crCardChrgCpltCd.setValue(CR_CARD_CHRG_CPLT.WAITING_FOR_CREDIT_CARD_CHARGE);
                    } else {
                        invPMsg.crCardChrgCpltCd.setValue(CR_CARD_CHRG_CPLT.NO_NEED_CREDIT_CARD_CHARGE);
                    }
                } else {
                    invPMsg.crCardChrgCpltCd.setValue(CR_CARD_CHRG_CPLT.NO_NEED_CREDIT_CARD_CHARGE);
                }

            }
            if (CR_CARD_CHRG_CPLT.WAITING_FOR_CREDIT_CARD_CHARGE.equals(invPMsg.crCardChrgCpltCd.getValue())) {
                if (!hasValue(invPMsg.crCardCustRefNum)) {
                    msgMap.addXxMsgId(NWZM1281E);
                    isSuccess = false;
                }
                if (!hasValue(invPMsg.crCardTpCd)) {
                    msgMap.addXxMsgId(NWZM1289E);
                    isSuccess = false;
                }
                if (!hasValue(invPMsg.dsInvExprDt)) {
                    msgMap.addXxMsgId(NWZM1282E);
                    isSuccess = false;
                }
                
            }
            DS_ORD_TPTMsg dsOrdTpTMsg  = new DS_ORD_TPTMsg();
            dsOrdTpTMsg.glblCmpyCd.setValue(invPMsg.glblCmpyCd.getValue());
            dsOrdTpTMsg.dsOrdTpCd.setValue(invPMsg.dsOrdTpCd.getValue());

            if (hasValue(invPMsg.dsOrdTpCd.getValue())) {
                DS_ORD_TPTMsg dsOrdTpTMsgRes = (DS_ORD_TPTMsg)S21CodeTableAccessor.findByKey(dsOrdTpTMsg);
                if (dsOrdTpTMsgRes == null) {
                    msgMap.addXxMsgId(NWZM1286E);
                    isSuccess = false;
                    msgMap.flush();
                    return isSuccess;
                }
            }
            if (!isServiceContract && !isServiceDispatch && !isManualInvoice) {
                if (!hasValue(invPMsg.dsOrdTpCd)) {
                    msgMap.addXxMsgId(NWZM1279E);
                    isSuccess = false;
                }
            }

            if (isServiceContract || isServiceDispatch) {

                if (!hasValue(invPMsg.svcInvPk)) {
                    msgMap.addXxMsgId(NWZM1540E);
                    isSuccess = false;
                }
            }

            if (isServiceContract) {
                if (!hasValue(invPMsg.dsContrCatgCd)) {
                    msgMap.addXxMsgId(NWZM1541E);
                    isSuccess = false;
                }

                if (!hasValue(invPMsg.contrRnwTotCnt)) {
                    msgMap.addXxMsgId(NWZM1545E);
                    isSuccess = false;
                }
            }

            msgMap.flush();
        }
        return isSuccess;
    }

    private boolean invHeaderMandatoryCheck(List<NWZC040001PMsg> invHeaderPmsgList) {

        boolean isSuccess = true;

        for (NWZC040001PMsg invPMsg : invHeaderPmsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(invPMsg);

            if (!hasValue(invPMsg.glblCmpyCd)) {
                msgMap.addXxMsgId(NWZM0011E);
                isSuccess = false;
            } else {
                this.glblCmpyCd = invPMsg.glblCmpyCd.getValue();
            }

            if (!hasValue(invPMsg.invDt)) {
                msgMap.addXxMsgId(NWZM0681E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.billToCustCd)) {
                msgMap.addXxMsgId(NWZM0510E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.sellToCustCd)) {
                msgMap.addXxMsgId(NWZM0508E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.payerCustCd)) {
                msgMap.addXxMsgId(NWZM0509E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.pmtTermCd)) {
                msgMap.addXxMsgId(NWZM0683E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.invPrintStsCd)) {
                msgMap.addXxMsgId(NWZM0684E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.trxSrcTpCd)) {
                msgMap.addXxMsgId(NWZM0521E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.sysSrcCd)) {
                msgMap.addXxMsgId(NWZM0453E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.dsInvTpCd)) {
                msgMap.addXxMsgId(NWZM1536E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.srcSysDocNum)) {
                msgMap.addXxMsgId(NWZM1537E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.slsRepTocCd)) {
                msgMap.addXxMsgId(NWZM1538E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.billToCustAcctCd)) {
                msgMap.addXxMsgId(NWZM1539E);
                isSuccess = false;
            }


            if (!hasValue(invPMsg.soldToCustLocCd)) {
                msgMap.addXxMsgId(NWZM1542E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.lineBizTpCd)) {
                msgMap.addXxMsgId(NWZM1543E);
                isSuccess = false;
            }

            if (!hasValue(invPMsg.dsBizAreaCd)) {
                msgMap.addXxMsgId(NWZM1544E);
                isSuccess = false;
            }

            msgMap.flush();
        }

        return isSuccess;
    }

    private boolean invLineCheckWithInvHeader(List<INV_LINEBean> allInvLineList) {

        boolean isSuccess = true;

        for (INV_LINEBean invLineBean : allInvLineList) {
            NWZC040003PMsg invLinePMsg = invLineBean.getNWZC040003PMsg();

            NWZC040001PMsg invPMsg = invLineBean.getNWZC040001PMsg();

            if (hasValue(invPMsg.dsOrdTpCd.getValue())) {
                DS_ORD_TPTMsg dsOrdTpTMsg  = new DS_ORD_TPTMsg();
                dsOrdTpTMsg.glblCmpyCd.setValue(invPMsg.glblCmpyCd.getValue());
                dsOrdTpTMsg.dsOrdTpCd.setValue(invPMsg.dsOrdTpCd.getValue());
                DS_ORD_TPTMsg dsOrdTpTMsgRes = (DS_ORD_TPTMsg)S21CodeTableAccessor.findByKey(dsOrdTpTMsg);
                if (dsOrdTpTMsgRes == null) {
                    invLineBean.addXxMsgId(NWZM1286E);
                    isSuccess = false;
                    return isSuccess;
                }
                if (ZYPConstant.FLG_ON_Y.equals(dsOrdTpTMsgRes.conslInvFlg.getValue())) {
                    if (!hasValue(invLinePMsg.espacLineShipQty)) {
                        invLineBean.addXxMsgId(NWZM1285E);
                        isSuccess = false;
                    }
                }
                if (ZYPConstant.FLG_ON_Y.equals(dsOrdTpTMsgRes.splyContrChkFlg.getValue()) || DS_ORD_CATG.SERVICE.equals(dsOrdTpTMsgRes.dsOrdCatgCd.getValue())) {
                    if (!hasValue(invLinePMsg.dsContrNum)) {
                        invLineBean.addXxMsgId(NWZM1143E);
                        isSuccess = false;
                    }
                }
            }
        }
        return isSuccess;
    }

    private boolean invLineMandatoryByConditionCheck(final List<NWZC040003PMsg> invLinePMsgList) {

        boolean isSuccess = true;

        for (NWZC040003PMsg invLinePMsg : invLinePMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(invLinePMsg);

            if ((SYS_SRC.ROSS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS_EXPORT.equals(this.sysSrcCd) || SYS_SRC.ESTORE.equals(this.sysSrcCd))
                    && !hasValue(invLinePMsg.invNum)) {
                msgMap.addXxMsgId(NWZM0689E);
                isSuccess = false;
            }

            if (SYS_SRC.ESTORE.equals(this.sysSrcCd) && !hasValue(invLinePMsg.stkStsCd)) {
                msgMap.addXxMsgId(NWZM0697E);
                isSuccess = false;
            }

            // 2017/10/23 QC#20719 Mod Start
            //if ((SYS_SRC.S21_PARTS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS_EXPORT.equals(this.sysSrcCd) || SYS_SRC.S21_SERVICE_AND_REPAIR.equals(this.sysSrcCd)) && !hasValue(invLinePMsg.shipCmplCostAmt)) {
            if ((SYS_SRC.S21_PARTS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS_EXPORT.equals(this.sysSrcCd) || SYS_SRC.S21_SERVICE_AND_REPAIR.equals(this.sysSrcCd))) {
            // 2017/10/23 QC#20719 Mod End
                msgMap.addXxMsgId(NWZM0699E);
                isSuccess = false;
            }

            if (isServiceContract || isServiceDispatch) {
                if (!hasValue(invLinePMsg.svcInvLinePk)) {
                    msgMap.addXxMsgId(NWZM1548E);
                    isSuccess = false;
                }
            }

            if (isServiceContract) {
                if (!hasValue(invLinePMsg.dsContrDtlPk)) {
                    msgMap.addXxMsgId(NWZM1549E);
                    isSuccess = false;
                }
            }

            if (isReturn) {
                if (!hasValue(invLinePMsg.dsCpoLineNum)) {
                    msgMap.addXxMsgId(NWZM1550E);
                    isSuccess = false;
                }
            }
            msgMap.flush();
        }
        return isSuccess;
    }

    private boolean invLineMandatoryCheck(List<NWZC040003PMsg> invLinePMsgList) {

        boolean isSuccess = true;

        for (NWZC040003PMsg invLinePMsg : invLinePMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(invLinePMsg);

            if (!hasValue(invLinePMsg.glblCmpyCd)) {
                msgMap.addXxMsgId(NWZM0011E);
                isSuccess = false;
            }

            if (!hasValue(invLinePMsg.invBolLineNum)) {
                msgMap.addXxMsgId(NWZM0685E);
                isSuccess = false;
            }

            if (!hasValue(invLinePMsg.invLineNum)) {
                msgMap.addXxMsgId(NWZM0686E);
                isSuccess = false;
            }

            if (!hasValue(invLinePMsg.invLineSubNum)) {
                msgMap.addXxMsgId(NWZM0687E);
                isSuccess = false;
            }

            if (!hasValue(invLinePMsg.slsRepTocCd)) {
                msgMap.addXxMsgId(NWZM0688E);
                isSuccess = false;
            }

            if (!hasValue(invLinePMsg.mdseCd)) {
                msgMap.addXxMsgId(NWZM0492E);
                isSuccess = false;
            }

            if (!hasValue(invLinePMsg.uomCd)) {
                msgMap.addXxMsgId(NWZM1547E);
                isSuccess = false;
            }
            msgMap.flush();
        }

        return isSuccess;
    }

    private INV_PRMO_INFOBean makingINV_PRMO_INFOData(INV_LINEBean invLineBean) {

        NWZC040001PMsg invPMsg = invLineBean.getNWZC040001PMsg();
        NWZC040003PMsg invLinePMsg = invLineBean.getNWZC040003PMsg();

        INV_PRMO_INFOBean invPrmoInfoBean = new INV_PRMO_INFOBean(invPMsg);
        INV_PRMO_INFOTMsg invPrmoInfoTmsg = invPrmoInfoBean.getInvPrmoInfoTMsg();

        setValue(invPrmoInfoTmsg.glblCmpyCd, invLinePMsg.glblCmpyCd);
        setValue(invPrmoInfoTmsg.invNum, invLinePMsg.invNum);
        setValue(invPrmoInfoTmsg.invBolLineNum, invLinePMsg.invBolLineNum);
        setValue(invPrmoInfoTmsg.invLineNum, invLinePMsg.invLineNum);
        setValue(invPrmoInfoTmsg.invLineSubNum, invLinePMsg.invLineSubNum);
        setValue(invPrmoInfoTmsg.invLineSubTrxNum, invLinePMsg.invLineSubTrxNum);
        invPrmoInfoTmsg.dealPerUnitFixAmt.setValue(ZERO);
        invPrmoInfoTmsg.dealSlsPctNum.setValue(ZERO);
        invPrmoInfoTmsg.funcPerUnitFixAmt.setValue(ZERO);
        setValue(invPrmoInfoTmsg.prmoQty, invLinePMsg.shipQty);
        setValue(invPrmoInfoTmsg.dealUnitPrcAmt, invLinePMsg.dealNetUnitPrcAmt);
        setValue(invPrmoInfoTmsg.dealLastNetUnitPrcAmt, invLinePMsg.dealNetUnitPrcAmt);
        setValue(invPrmoInfoTmsg.dealNetAmt, invLinePMsg.invLineDealNetAmt);
        invPrmoInfoTmsg.dealDiscAmt.setValue(ZERO);
        invPrmoInfoTmsg.dealPrmoNetUnitPrcAmt.setValue(ZERO);
        invPrmoInfoTmsg.funcDiscAmt.setValue(ZERO);
        invPrmoInfoTmsg.funcPrmoNetUnitPrcAmt.setValue(ZERO);
        invPrmoInfoTmsg.invPrmoInfoSqNum.setValue(FIXED_INV_PRMO_INFO_SEQ_NUM);
        setValue(invPrmoInfoTmsg.coaAcctCd,invLinePMsg.coaAcctCd);

        invLineBean.addInvPrmoInfo(invPrmoInfoBean);

        return invPrmoInfoBean;
    }

    private List<INVBean> makingInvoiceStructure(final List<NWZC040001PMsg> invHeaderPmsgList
                                                , final List<NWZC040002PMsg> shipmentsPMsgList
                                                , final List<NWZC040003PMsg> invLinePMsgList
                                                , final List<NWZC040004PMsg> promoPMsgList
                                                , final List<NWZC040005PMsg> taxDtlPMsgList
                                                , final List<NWZC040006PMsg> serNumPMsgList
                                                , final List<NWZC040007PMsg> invSlsCrPMsgList
                                                ) {
        List<INVBean> invBeanList = new ArrayList<INVBean>();

        for (NWZC040001PMsg invPMsg : invHeaderPmsgList) {
            INVBean invBean = new INVBean(invPMsg);
            invBeanList.add(invBean);

            for (NWZC040002PMsg invBolPMsg : shipmentsPMsgList) {
                if (invPMsg.invNum.getValue().equals(invBolPMsg.invNum.getValue())) {

                    if (!hasValue(invBolPMsg.shipDt)) {
                        invBolPMsg.shipDt.setValue(invPMsg.invDt.getValue());
                    }

                    if (N.equals(invBolPMsg.dropShipFlg.getValue())) {
                        setShipToINformation(invBolPMsg,invPMsg, invBean);
                    }

                    INV_BOLBean invBolBean = new INV_BOLBean(invPMsg, invBolPMsg);
                    invBean.addInvBol(invBolBean);

                    for (NWZC040003PMsg invLinePMsg : invLinePMsgList) {
                        if (invBolPMsg.invNum.getValue().equals(invLinePMsg.invNum.getValue()) && invBolPMsg.invBolLineNum.getValue().equals(invLinePMsg.invBolLineNum.getValue())) {
                            INV_LINEBean invLineBean = new INV_LINEBean(invPMsg, invLinePMsg);
                            invBolBean.addInvLine(invLineBean);

                            if (promoPMsgList != null && promoPMsgList.size() > 0) {

                                for (NWZC040004PMsg invPrmoInfoPMsg : promoPMsgList) {
                                    if (invLinePMsg.invNum.getValue().equals(invPrmoInfoPMsg.invNum.getValue()) && invLinePMsg.invBolLineNum.getValue().equals(invPrmoInfoPMsg.invBolLineNum.getValue())
                                            && invLinePMsg.invLineNum.getValue().equals(invPrmoInfoPMsg.invLineNum.getValue()) && invLinePMsg.invLineSubNum.getValue().equals(invPrmoInfoPMsg.invLineSubNum.getValue())
                                            && invLinePMsg.invLineSubTrxNum.getValue().equals(invPrmoInfoPMsg.invLineSubTrxNum.getValue())) {
                                        INV_PRMO_INFOBean invPrmoInfoBean = new INV_PRMO_INFOBean(invPMsg, invPrmoInfoPMsg);
                                        invLineBean.addInvPrmoInfo(invPrmoInfoBean);
                                    }
                                }
                            }

                            // make invoice line serial number
                            if (serNumPMsgList != null && serNumPMsgList.size() > 0) {
                                for (NWZC040006PMsg serNumPMsg : serNumPMsgList) {
                                    if (invLinePMsg.invNum.getValue().equals(serNumPMsg.invNum.getValue())
                                     && invLinePMsg.invBolLineNum.getValue().equals(serNumPMsg.invBolLineNum.getValue())
                                     && invLinePMsg.invLineNum.getValue().equals(serNumPMsg.invLineNum.getValue())
                                     && invLinePMsg.invLineSubNum.getValue().equals(serNumPMsg.invLineSubNum.getValue())
                                     && invLinePMsg.invLineSubTrxNum.getValue().equals(serNumPMsg.invTrxLineSubNum.getValue())) {
                                        DS_INV_LINE_SER_NUMTMsg serNumTMsg = new DS_INV_LINE_SER_NUMTMsg();
                                        EZDMsg.copy(invLinePMsg, null, serNumTMsg, null);
                                        EZDMsg.copy(serNumPMsg,  null, serNumTMsg, null);
                                        invLineBean.getDsInvLineSerNumTMsgList().add(serNumTMsg);
                                    }
                                }
                            }
                            // make invoice sales credit
                            if (invSlsCrPMsgList != null && invSlsCrPMsgList.size() > 0) {
                                for (NWZC040007PMsg invSlsCrPMsg : invSlsCrPMsgList) {
                                    if (invLinePMsg.invNum.getValue().equals(invSlsCrPMsg.invNum.getValue())
                                    && invLinePMsg.invBolLineNum.getValue().equals(invSlsCrPMsg.invBolLineNum.getValue())
                                    && invLinePMsg.invLineNum.getValue().equals(invSlsCrPMsg.invLineNum.getValue())
                                    && invLinePMsg.invLineSubNum.getValue().equals(invSlsCrPMsg.invLineSubNum.getValue())
                                    && invLinePMsg.invLineSubTrxNum.getValue().equals(invSlsCrPMsg.invLineSubTrxNum.getValue())) {
                                        DS_INV_SLS_CRTMsg invSlsCrTMsg = new DS_INV_SLS_CRTMsg();
                                        EZDMsg.copy(invLinePMsg,  null, invSlsCrTMsg, null);
                                        EZDMsg.copy(invSlsCrPMsg, null, invSlsCrTMsg, null);
                                        invLineBean.getDsInvSlsCrTMsgList().add(invSlsCrTMsg);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return invBeanList;
    }

    private String nvl(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    private boolean promotionMandatoryByConditionCheck(final List<NWZC040004PMsg> promoPMsgList) {

        boolean isSuccess = true;

        for (NWZC040004PMsg invPrmoInfoPMsg : promoPMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(invPrmoInfoPMsg);

            if ((SYS_SRC.ROSS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS_EXPORT.equals(this.sysSrcCd) || SYS_SRC.ESTORE.equals(this.sysSrcCd))
                    && !hasValue(invPrmoInfoPMsg.invNum)) {
                msgMap.addXxMsgId(NWZM0689E);
                isSuccess = false;
            }
            msgMap.flush();
        }
        return isSuccess;
    }

    private boolean promotionMandatoryCheck(List<NWZC040004PMsg> promoPMsgList) {

        boolean isSuccess = true;

        for (NWZC040004PMsg invPrmoInfoPMsg : promoPMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(invPrmoInfoPMsg);

            if (!hasValue(invPrmoInfoPMsg.glblCmpyCd)) {
                msgMap.addXxMsgId(NWZM0011E);
                isSuccess = false;
            }

            if (!hasValue(invPrmoInfoPMsg.invBolLineNum)) {
                msgMap.addXxMsgId(NWZM0685E);
                isSuccess = false;
            }

            if (!hasValue(invPrmoInfoPMsg.invLineNum)) {
                msgMap.addXxMsgId(NWZM0686E);
                isSuccess = false;
            }

            if (!hasValue(invPrmoInfoPMsg.invLineSubNum)) {
                msgMap.addXxMsgId(NWZM0687E);
                isSuccess = false;
            }

            msgMap.flush();
        }
        return isSuccess;
    }

    private void setInvNum(List<INVBean> invList, ONBATCH_TYPE onBatchType) {

        for (INVBean invBean : invList) {

            if (invBean.hasInvNum()) {
                continue;
            }

            NWZC040001PMsg invPMsg = invBean.getNWZC040001PMsg();

            String glblCmpyCd = invPMsg.glblCmpyCd.getValue();
            DS_INV_TPTMsg dsInvTpTMsg = new DS_INV_TPTMsg();
            setValue(dsInvTpTMsg.glblCmpyCd, glblCmpyCd);
            setValue(dsInvTpTMsg.dsInvTpCd,  invPMsg.dsInvTpCd.getValue());
            dsInvTpTMsg = (DS_INV_TPTMsg) S21CacheTBLAccessor.findByKey(dsInvTpTMsg);
            // START 2017/11/24 E.Kameishi [QC#19735, MOD]
            //invBean.setInvNum(ZYPExtnNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue()));
            invBean.setInvNum(ZYPMaxTenDigitsNumbering.getUniqueID(glblCmpyCd, dsInvTpTMsg.autoSeqCd.getValue()));
            // END 2017/11/24 E.Kameishi [QC#19735, MOD]
        }
    }

    private boolean setShipToINformation(NWZC040002PMsg invBolPMsg, NWZC040001PMsg invPMsg, INVBean invBean) {

        if (hasValue(invBolPMsg.shipToCustCd.getValue())) {
            INV_BOLTMsg invBolTMsg = new INV_BOLTMsg();
            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("querySHIP_TO_and_CNTY", invBolPMsg, invBolTMsg);
            if (ssmRes.getQueryResultCount() == 0) {
                invBean.addXxMsgId(NWZM0260E);
                return false;
            }
            invBolPMsg.shipToLocNm.setValue(invBolTMsg.shipToLocNm.getValue());
            invBolPMsg.shipToAddlLocNm.setValue(invBolTMsg.shipToAddlLocNm.getValue());
            invBolPMsg.shipToFirstLineAddr.setValue(invBolTMsg.shipToFirstLineAddr.getValue());
            invBolPMsg.shipToScdLineAddr.setValue(invBolTMsg.shipToScdLineAddr.getValue());
            invBolPMsg.shipToThirdLineAddr.setValue(invBolTMsg.shipToThirdLineAddr.getValue());
            invBolPMsg.shipToFrthLineAddr.setValue(invBolTMsg.shipToFrthLineAddr.getValue());
            invBolPMsg.shipToStCd.setValue(invBolTMsg.shipToStCd.getValue());
            invBolPMsg.shipToPostCd.setValue(invBolTMsg.shipToPostCd.getValue());
            invBolPMsg.shipToCtyAddr.setValue(invBolTMsg.shipToCtyAddr.getValue());
            invBolPMsg.shipToCntyNm.setValue(invBolTMsg.shipToCntyNm.getValue());
            invBolPMsg.shipToProvNm.setValue(invBolTMsg.shipToProvNm.getValue());
            invBolPMsg.shipToCtryCd.setValue(invBolTMsg.shipToCtryCd.getValue());

        } else {
            INVTMsg invTMsg = new INVTMsg();
            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd", invPMsg.glblCmpyCd.getValue());
            mapParam.put("sellToCustCd", invPMsg.sellToCustCd.getValue());
            S21SsmEZDResult ssmRes = ssmEzdClient.queryEZDMsg("querySELL_TO_and_CNTY", invPMsg, invTMsg);
            if (ssmRes.getQueryResultCount() == 0) {
                invBean.addXxMsgId(NWZM0260E);
                return false;
            }

            invBolPMsg.shipToCustCd.setValue(invPMsg.sellToCustCd.getValue());
            invBolPMsg.shipToLocNm.setValue(invTMsg.sellToLocNm.getValue());
            invBolPMsg.shipToAddlLocNm.setValue(invTMsg.sellToAddlLocNm.getValue());
            invBolPMsg.shipToFirstLineAddr.setValue(invTMsg.sellToFirstLineAddr.getValue());
            invBolPMsg.shipToScdLineAddr.setValue(invTMsg.sellToScdLineAddr.getValue());
            invBolPMsg.shipToThirdLineAddr.setValue(invTMsg.sellToThirdLineAddr.getValue());
            invBolPMsg.shipToFrthLineAddr.setValue(invTMsg.sellToFrthLineAddr.getValue());
            invBolPMsg.shipToStCd.setValue(invTMsg.sellToStCd.getValue());
            invBolPMsg.shipToPostCd.setValue(invTMsg.sellToPostCd.getValue());
            invBolPMsg.shipToCtyAddr.setValue(invTMsg.sellToCtyAddr.getValue());
            invBolPMsg.shipToCntyNm.setValue(invTMsg.sellToCntyNm.getValue());
            invBolPMsg.shipToProvNm.setValue(invTMsg.sellToProvNm.getValue());
            invBolPMsg.shipToCtryCd.setValue(invTMsg.sellToCtryCd.getValue());
        }
        return true;
    }

    private boolean setUpINV_PRMO_INFO(List<INV_LINEBean> allInvLineList, List<INV_PRMO_INFOBean> allInvPrmoInfoBeanList) {

        boolean isSuccess = true;

        for (INV_LINEBean invLineBean : allInvLineList) {

            List<INV_PRMO_INFOBean> invPrmoInfoBeanList = invLineBean.getInvPrmoInfoList();

            if (invPrmoInfoBeanList.isEmpty()) {

                INV_PRMO_INFOBean invPrmoInfoBean = makingINV_PRMO_INFOData(invLineBean);
                allInvPrmoInfoBeanList.add(invPrmoInfoBean);
                invLineBean.setInvLineFuncAmtSetter(new InvLineFuncAmtSetterWithoutPrmoParam());

            } else {

                if (!getFromMasterForINV_PRMO_INFO(invLineBean)) {
                    isSuccess = false;
                    continue;
                }

                invLineBean.setInvLineFuncAmtSetter(new InvLineFuncAmtSetterWithPrmoParam());
            }
        }
        return isSuccess;
    }

    private boolean shipmentsMandatoryByConditionCheck(final List<NWZC040002PMsg> shipmentsPMsgList) {

        boolean isSuccess = true;

        for (NWZC040002PMsg invBolPMsg : shipmentsPMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(invBolPMsg);

            if ((SYS_SRC.ROSS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS.equals(this.sysSrcCd) || SYS_SRC.S21_PARTS_EXPORT.equals(this.sysSrcCd) || SYS_SRC.ESTORE.equals(this.sysSrcCd))
                    && !hasValue(invBolPMsg.invNum)) {
                msgMap.addXxMsgId(NWZM0689E);
                isSuccess = false;
            }

            if (!isServiceContract && !isServiceDispatch && !isManualInvoice) {
                if (!hasValue(invBolPMsg.shipToCustAcctCd)) {
                    msgMap.addXxMsgId(NWZM1546E);
                    isSuccess = false;
                }
            }
            msgMap.flush();
        }

        return isSuccess;
    }

    private boolean shipmentsMandatoryCheck(List<NWZC040002PMsg> shipmentsPMsgList) {

        boolean isSuccess = true;

        for (NWZC040002PMsg invBolPMsg : shipmentsPMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(invBolPMsg);

            if (!hasValue(invBolPMsg.glblCmpyCd)) {
                msgMap.addXxMsgId(NWZM0011E);
                isSuccess = false;
            }

            if (!hasValue(invBolPMsg.invBolLineNum)) {
                msgMap.addXxMsgId(NWZM0685E);
                isSuccess = false;
            }
            msgMap.flush();
        }

        return isSuccess;
    }

    private boolean isExportFlg(INVBean invBean) {
        
        boolean isExport = false;
        
        List<INV_BOLBean> invBolList = invBean.getInvBolList();
        for (INV_BOLBean invBol : invBolList) {
            INV_BOLTMsg invBolTMsg = invBol.getInvBolTMsg();
            if (invBolTMsg == null) {
                return isExport;
            } else {
                if (!Y.equals(invBolTMsg.exptFlg.getValue())) {
                    return false;
                } else {
                    isExport = true;
                }
            }
        }
        return isExport;
        
    }

    public static void zeroThenOverWrite(EZDTBigDecimalItem arg0, BigDecimal arg1) {

        if (!ZYPCommonFunc.hasValue(arg0) || ZERO.compareTo(arg0.getValue()) == 0) { // QC#21841 mod
            arg0.setValue(arg1);
        }
    }

    private boolean checkValue(String value, String constValue) {

        if (constValue.startsWith(NOT_EQUALS)) {
            if (!constValue.substring(NOT_EQUALS.length()).equals(value)) {
                return true;
            }
        } else {
            if (constValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private CR_DR_SUB_RSNTMsg findCrDrSubRsnByKey(String glblCmpyCd, String crDrSubRsnCd) {
        
        CR_DR_SUB_RSNTMsg inMsg = new CR_DR_SUB_RSNTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.crDrSubRsnCd.setValue(crDrSubRsnCd);

        return (CR_DR_SUB_RSNTMsg)S21FastTBLAccessor.findByKey(inMsg);
    }

    private void setDsInv(List<INVBean> invBeanList){
        for (INVBean invBean : invBeanList) {
            INVTMsg invTMsg = invBean.getInvTmsg();
            DS_ORD_TPTMsg dsOrdTpTMsg  = new DS_ORD_TPTMsg();
            dsOrdTpTMsg.glblCmpyCd.setValue(invTMsg.glblCmpyCd.getValue());
            dsOrdTpTMsg.dsOrdTpCd.setValue(invTMsg.dsOrdTpCd.getValue());
            if (hasValue(invTMsg.dsOrdTpCd.getValue())) {
                DS_ORD_TPTMsg dsOrdTpTMsgRes = (DS_ORD_TPTMsg)S21CodeTableAccessor.findByKey(dsOrdTpTMsg);
                if (ZYPConstant.FLG_ON_Y.equals(dsOrdTpTMsgRes.conslInvFlg.getValue())) {
                    invTMsg.subTotNetAmt.setValue(invBean.getInvTmsg().invTotDealNetAmt.getValue());
                    invTMsg.subTotSvcAmt.setValue(add(invBean.getInvTmsg().invTotDealSlsAmt.getValue(), invBean.getInvTmsg().invTotDealFrtAmt.getValue()));
                    invTMsg.subTotTaxAmt.setValue(invBean.getInvTmsg().invTotDealTaxAmt.getValue());
                    BigDecimal sqFtAmt = BigDecimal.ZERO;
                    for (INV_BOLBean invBolBean : invBean.getInvBolList()) {
                        for (INV_LINEBean invLineBean : invBolBean.getInvLineList()) {
                            sqFtAmt = add(sqFtAmt, invLineBean.getInvLineTMsg().espacLineShipQty.getValue());
                        }
                    }
                    invTMsg.easyPackTotShipSumQty.setValue(sqFtAmt);
                }
            }
        }
    }

    private BigDecimal add(BigDecimal b1, BigDecimal b2) {
        if (ZYPCommonFunc.hasValue(b1) && ZYPCommonFunc.hasValue(b2)) {
            return b1.add(b2);
        }
        if (ZYPCommonFunc.hasValue(b1) && !ZYPCommonFunc.hasValue(b2)) {
            return b1;
        }
        if (!ZYPCommonFunc.hasValue(b1) && ZYPCommonFunc.hasValue(b2)) {
            return b2;
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal getDealAmount(INVBean invBean, INVTMsg invTMsg, BigDecimal prcAmt){

        BigDecimal actlApplyExchRate = invTMsg.actlApplyExchRate.getValue();

        String acctArthTpCd = invBean.getAcctArthTpCd();
        if (MULTIPLICATION.equals(acctArthTpCd)) {
            acctArthTpCd = DIVISION;
        } else {
            acctArthTpCd = MULTIPLICATION;
        }

        String glblCmpyCd = invTMsg.glblCmpyCd.getValue();
        String toCcyCd = invTMsg.dealCcyCd.getValue();
        BigDecimal scaleForCcy;

        CCYTMsg ccyTMsg = new CCYTMsg();
        setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        setValue(ccyTMsg.ccyCd,      toCcyCd);

        ccyTMsg = (CCYTMsg)S21CacheTBLAccessor.findByKey(ccyTMsg);

        if (ccyTMsg == null) {
            return null;
        } else {
            // AFT_DECL_PNT_DIGIT_NUM
            scaleForCcy = ccyTMsg.aftDeclPntDigitNum.getValue();
        }

        BigDecimal dealPrcAmt = calcAmt(prcAmt, acctArthTpCd, actlApplyExchRate, scaleForCcy.intValue(), HALF_UP);

        return dealPrcAmt;
    }

    public static BigDecimal calcAmt(BigDecimal amt, String acctArthTpCd, BigDecimal actlExchRate, int scale, RoundingMode rMode) {

        BigDecimal retAmt = null;

        if (MULTIPLICATION.equals(acctArthTpCd)) {
            if (amt != null) {
                retAmt = amt.multiply(actlExchRate).setScale(scale, HALF_UP);
            }
        } else {
            if (amt != null) {
                retAmt = amt.divide(actlExchRate, scale, HALF_UP);
            }
        }

        return retAmt;
    }

    private boolean chkFuncAmtRelation(List<INVBean> invList) {

        boolean isSuccess = true;

        for (INVBean invBean : invList) {
            INVTMsg invTMsg = invBean.getInvTmsg();
            BigDecimal actlApplyExchRate = invTMsg.actlApplyExchRate.getValue();

            if(!ZYPCommonFunc.hasValue(actlApplyExchRate)){

                for (INV_BOLBean invBolBean : invBean.getInvBolList()) {
                    INV_BOLTMsg invBolTMsg = invBolBean.getInvBolTMsg();

                    if(BigDecimal.ZERO.compareTo(invBolTMsg.frtFuncTaxAmt.getValue()) < 0){
                        isSuccess = false;
                    }
                    if(BigDecimal.ZERO.compareTo(invBolTMsg.shipFuncHdlgChrgAmt.getValue()) < 0){
                        isSuccess = false;
                    }
                    if(BigDecimal.ZERO.compareTo(invBolTMsg.shipFuncFrtAmt.getValue()) < 0){
                        isSuccess = false;
                    }

                    for (INV_LINEBean invLineBean : invBolBean.getInvLineList()) {
                        INV_LINETMsg invLineTMsg = invLineBean.getInvLineTMsg();
                        if(BigDecimal.ZERO.compareTo(invLineTMsg.invLineFuncTaxAmt.getValue()) < 0){
                            isSuccess = false;
                        }
                    }
                }
            }
        }
        return isSuccess;
    }

    private boolean taxDtlMandatoryCheck(List<NWZC040005PMsg> taxDtlPMsgList) {

        boolean isSuccess = true;

        for (NWZC040005PMsg taxDtlPMsg : taxDtlPMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(taxDtlPMsg);

            if (!hasValue(taxDtlPMsg.glblCmpyCd)) {
                msgMap.addXxMsgId(NWZM0011E);
                isSuccess = false;
            }

            if (!hasValue(taxDtlPMsg.invBolLineNum)) {
                msgMap.addXxMsgId(NWZM0685E);
                isSuccess = false;
            }

            if (!hasValue(taxDtlPMsg.invLineNum)) {
                msgMap.addXxMsgId(NWZM0686E);
                isSuccess = false;
            }

            if (!hasValue(taxDtlPMsg.invLineSubNum)) {
                msgMap.addXxMsgId(NWZM0687E);
                isSuccess = false;
            }

            if (!hasValue(taxDtlPMsg.dsSlsTaxTpCd)) {
                msgMap.addXxMsgId(NWZM1552E);
                isSuccess = false;
            }

            if (!hasValue(taxDtlPMsg.funcSlsTaxAmt)) {
                msgMap.addXxMsgId(NWZM1553E);
                isSuccess = false;
            }

            if (!hasValue(taxDtlPMsg.dealSlsTaxAmt)) {
                msgMap.addXxMsgId(NWZM1554E);
                isSuccess = false;
            }

            if (!hasValue(taxDtlPMsg.slsTaxPct)) {
                msgMap.addXxMsgId(NWZM1555E);
                isSuccess = false;
            }

            msgMap.flush();
        }

        return isSuccess;
    }

    private boolean invSlsCrMandatoryCheck(List<NWZC040007PMsg> invSlsCrPMsgList) {
        
        boolean isSuccess = true;
        
        for (NWZC040007PMsg invSlsCrPMsg : invSlsCrPMsgList) {

            S21ApiMessageMap msgMap = new S21ApiMessageMap(invSlsCrPMsg);

            if (!hasValue(invSlsCrPMsg.glblCmpyCd)) {
                msgMap.addXxMsgId(NWZM0011E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.invBolLineNum)) {
                msgMap.addXxMsgId(NWZM0685E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.invLineNum)) {
                msgMap.addXxMsgId(NWZM0686E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.invLineSubNum)) {
                msgMap.addXxMsgId(NWZM0687E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.mdseCd)) {
                msgMap.addXxMsgId(NWZM0996E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.invLineSplPct)) {
                msgMap.addXxMsgId(NWZM1557E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.slsRepTocCd)) {
                msgMap.addXxMsgId(NWZM1538E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.slsRepCrPct)) {
                msgMap.addXxMsgId(NWZM1558E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.dealSlsCrAmt)) {
                msgMap.addXxMsgId(NWZM1559E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.funcSlsCrAmt)) {
                msgMap.addXxMsgId(NWZM1560E);
                isSuccess = false;
            }

            if (!hasValue(invSlsCrPMsg.dealCcyCd)) {
                msgMap.addXxMsgId(NWZM1561E);
                isSuccess = false;
            }

            msgMap.flush();
        }

        return isSuccess;
    }

    private boolean callInvoiceDistributionAPI(List<INVBean> invBeanList) {

        boolean isSuccess = true;
        NFZC103001 invDistAPI = new NFZC103001();
        List<NFZC103001PMsg> pMsgList = new ArrayList<NFZC103001PMsg>();

        for (INVBean invBean : invBeanList) {
            INVTMsg invTMsg = invBean.getInvTmsg();
            NFZC103001PMsg pMsg = new NFZC103001PMsg();
            setValue(pMsg.glblCmpyCd, invTMsg.glblCmpyCd.getValue());
            setValue(pMsg.invNum, invTMsg.invNum.getValue());
            setValue(pMsg.procDt, invTMsg.invDt.getValue());
            pMsgList.add(pMsg);
        }

        invDistAPI.excute(pMsgList, ONBATCH_TYPE.BATCH);

        for(NFZC103001PMsg pMsg : pMsgList) {
            if (pMsg.xxMsgIdList.getValidCount() > 0) {

                INVTMsg invTMsg = new INVTMsg();
                setValue(invTMsg.glblCmpyCd, glblCmpyCd);
                setValue(invTMsg.invNum, pMsg.invNum.getValue());
                // update ITRL_INV_ERR_FLG:Y
                setValue(invTMsg.itrlInvErrFlg, Y);
                // update FNLZ_INV_FLG:N
                setValue(invTMsg.fnlzInvFlg, ZYPConstant.FLG_OFF_N);
                // 2017/12/15 QC#23026 mod start
                // S21ApiTBLAccessor.updateSelectionField(invTMsg, new String[]{"fnlzInvFlg"});
                S21ApiTBLAccessor.updateSelectionField(invTMsg, new String[] {"fnlzInvFlg", "itrlInvErrFlg" });
                // 2017/12/15 QC#23026 mod end

                break;
            }
        }

        return isSuccess;
    }

    private boolean canInsertDS_INV_LINE_TAX_DTL(List<INVBean> invBeanList, List<NWZC040005PMsg> taxDtlPMsgList, List<DS_INV_LINE_TAX_DTLTMsg> taxDtlTMsgList) {

        if (taxDtlPMsgList == null || taxDtlPMsgList.isEmpty()) {
            return true;
        }

        String invNum = invBeanList.get(0).getInvTmsg().invNum.getValue();

        for (NWZC040005PMsg taxDtlPMsg : taxDtlPMsgList) {
            setValue(taxDtlPMsg.invNum, invNum);
            DS_INV_LINE_TAX_DTLTMsg taxDtlTMsg = new DS_INV_LINE_TAX_DTLTMsg();
            EZDMsg.copy(taxDtlPMsg,  null, taxDtlTMsg, null);

            if (!hasValue(taxDtlTMsg.invTrxLineSubNum)) {
                taxDtlTMsg.invTrxLineSubNum.setValue(FIXED_INV_LINE_SUB_TRX_NUM);
            }

            Map<String, String> mapParam = new HashMap<String, String>();
            mapParam.put("glblCmpyCd",       glblCmpyCd);
            mapParam.put("invNum",           taxDtlTMsg.invNum.getValue());
            mapParam.put("invBolLineNum",    taxDtlTMsg.invBolLineNum.getValue());
            mapParam.put("invLineNum",       taxDtlTMsg.invLineNum.getValue());
            mapParam.put("invLineSubNum",    taxDtlTMsg.invLineSubNum.getValue());
            mapParam.put("invTrxLineSubNum", taxDtlTMsg.invTrxLineSubNum.getValue());
            mapParam.put("dsSlsTaxTpCd",     taxDtlTMsg.dsSlsTaxTpCd.getValue());
            BigDecimal pk = (BigDecimal) ssmBatchClient.queryObject("queryDS_INV_LINE_TAX_DTL", mapParam);

            if (pk == null) {
                //can insert
                setValue(taxDtlTMsg.dsInvLineTaxDtlPk,
                        ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_LINE_TAX_DTL_SQ));
                taxDtlTMsgList.add(taxDtlTMsg);
            }

        }
        return true;
    }

    private boolean canInsertDS_INV_LINE_SER_NUM(List<INV_LINEBean> invLineList, List<DS_INV_LINE_SER_NUMTMsg> serNumTMsgList) {

        for (INV_LINEBean invLineBean : invLineList) {

            List<DS_INV_LINE_SER_NUMTMsg> invLineSerNumTMsgList = invLineBean.getDsInvLineSerNumTMsgList();
            
            for (DS_INV_LINE_SER_NUMTMsg serNumTMsg : invLineSerNumTMsgList) {
                
                if (!hasValue(serNumTMsg.invTrxLineSubNum)) {
                    serNumTMsg.invTrxLineSubNum.setValue(FIXED_INV_LINE_SUB_TRX_NUM);
                }

                Map<String, String> mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd",       glblCmpyCd);
                mapParam.put("invNum",           serNumTMsg.invNum.getValue());
                mapParam.put("invBolLineNum",    serNumTMsg.invBolLineNum.getValue());
                mapParam.put("invLineNum",       serNumTMsg.invLineNum.getValue());
                mapParam.put("invLineSubNum",    serNumTMsg.invLineSubNum.getValue());
                mapParam.put("invTrxLineSubNum", serNumTMsg.invTrxLineSubNum.getValue());
                mapParam.put("mdseCd",           serNumTMsg.mdseCd.getValue());
                mapParam.put("serNum",           serNumTMsg.serNum.getValue());
                BigDecimal pk = (BigDecimal) ssmBatchClient.queryObject("queryDS_INV_LINE_SER_NUM", mapParam);

                if (pk == null) {
                    //can insert
                    setValue(serNumTMsg.dsInvLineSerNumPk,
                            ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_LINE_SER_NUM_SQ));
                    serNumTMsgList.add(serNumTMsg);
                }
            }
        }
        return true;
    }

    private boolean canInsertDS_INV_SLS_CR(List<INV_LINEBean> invLineList, List<DS_INV_SLS_CRTMsg> slsCrTMsgList) {

        for (INV_LINEBean invLineBean : invLineList) {

            List<DS_INV_SLS_CRTMsg> dsInvSlsCrTMsgList = invLineBean.getDsInvSlsCrTMsgList();

            for (DS_INV_SLS_CRTMsg slsCrTMsg : dsInvSlsCrTMsgList) {
                
                if (!hasValue(slsCrTMsg.invTrxLineSubNum)) {
                    slsCrTMsg.invTrxLineSubNum.setValue(FIXED_INV_LINE_SUB_TRX_NUM);
                }

                Map<String, String> mapParam = new HashMap<String, String>();
                mapParam.put("glblCmpyCd",       glblCmpyCd);
                mapParam.put("invNum",           slsCrTMsg.invNum.getValue());
                mapParam.put("invBolLineNum",    slsCrTMsg.invBolLineNum.getValue());
                mapParam.put("invLineNum",       slsCrTMsg.invLineNum.getValue());
                mapParam.put("invLineSubNum",    slsCrTMsg.invLineSubNum.getValue());
                mapParam.put("invTrxLineSubNum", slsCrTMsg.invTrxLineSubNum.getValue());
                mapParam.put("mdseCd",           slsCrTMsg.mdseCd.getValue());
                mapParam.put("invLineSplTpCd",   slsCrTMsg.invLineSplTpCd.getValue());
                mapParam.put("slsRepTocCd",      slsCrTMsg.slsRepTocCd.getValue());
                BigDecimal pk = (BigDecimal) ssmBatchClient.queryObject("queryDS_INV_SLS_CR", mapParam);

                if (pk == null) {
                    //can insert
                    setValue(slsCrTMsg.dsInvSlsCrPk,
                            ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_INV_SLS_CR_SQ));
                    slsCrTMsgList.add(slsCrTMsg);
                }
            }
        }
        return true;
    }

    private boolean callInvoiceSalesCreditCreationAPI(List<INVBean> invBeanList) {

        boolean isSuccess = true;

        NWZC055001 invSlsCrCratAPI = new NWZC055001();
        NWZC055001PMsg pMsg;
        INVTMsg invTMsg;

        for (INVBean invBean : invBeanList) {
            invTMsg = invBean.getInvTmsg();
            pMsg = new NWZC055001PMsg();
            setValue(pMsg.glblCmpyCd, glblCmpyCd);
            setValue(pMsg.invNum, invTMsg.invNum.getValue());
            setValue(pMsg.dsOrdLineDrctnCd, DS_ORD_LINE_DRCTN.INBOUND);
            invSlsCrCratAPI.execute(pMsg, ONBATCH_TYPE.BATCH);

            S21ApiMessageMap msgMap = invBean.getInvMsgMap();
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                isSuccess = false;
            }
        }

        return isSuccess;
    }

    // Add Start 2018/01/29 QC#23419
    private List<String> getCrLimitUpdateApiSkipDsInvTp(String glblCmpyCd) {
        String dsInvTp = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_CR_LIMIT_SKIP_DS_INV_TP_CD, glblCmpyCd);
        if (!hasValue(dsInvTp)) {
            return new ArrayList<String>();
        }
        String[] dsInvTpArray = dsInvTp.split(",");
        return Arrays.asList(dsInvTpArray);
    }
    // Add End 2018/01/29 QC#23419
}
