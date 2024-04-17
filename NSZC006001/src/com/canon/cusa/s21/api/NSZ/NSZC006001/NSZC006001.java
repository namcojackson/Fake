/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC006001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;
import business.db.CCYTMsg;
import business.db.CNTYTMsg;
import business.db.CR_CARD_TRXTMsg;
import business.db.CR_CARD_TRXTMsgArray;
// import business.db.DS_CONTRTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.FSRTMsg;
import business.db.FSR_EVENTTMsg;
import business.db.FSR_VISITTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDSETMsg;
import business.db.PMT_TERMTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.REM_TOTMsg;
import business.db.REM_TOTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_INVTMsg;
import business.db.SVC_INVTMsgArray;
import business.db.SVC_INV_CHRG_TPTMsg;
import business.db.SVC_INV_LINETMsg;
import business.db.SVC_INV_LINETMsgArray;
import business.db.SVC_INV_LINE_ALLOCTMsg;
import business.db.SVC_INV_LINE_ALLOCTMsgArray;
import business.db.SVC_INV_LINE_TAX_DTLTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.TOCTMsg;
import business.parts.NSXC001001PMsg;
import business.parts.NSZC006001PMsg;
import business.parts.NSZC006001_xxInvLineListPMsg;
import business.parts.NWZC036101PMsg;
import business.parts.NWZC036101_taxCalculateOutputLinePMsg;

import com.canon.cusa.s21.api.NSZ.NSZC006001.constant.NSZC006001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001Contr;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcIntgMdse;
import com.canon.cusa.s21.common.NSX.NSXC003001.CallTaxCalcAPIBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CallTaxCalcAPIForSvcPrc;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001Exchange;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_LINK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_PRC_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SLS_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TRVL_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21ApplicationCacheHolder;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * One Time Invoice API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/03/2013   Fujitsu         S.Nakai         Create          N/A
 * 06/13/2013   SRAA            Y.Chen          Update          ISSUE#48
 * 08/26/2013   Hitachi        T.Yonekrua       Update          QC1827
 * 08/28/2013   Hitachi        Y.Igarashi       Update          QC1943
 * 10/22/2013   SRA             E.Inada         Update          QC2687
 * 09/28/2015   Hitachi         A.Kohinata      Update          NA#
 * 02/25/2016   Hitachi         T.Iwamoto       Update          QC#3991
 * 04/19/2016   Hitachi         T.Iwamoto       Update          QC#7165
 * 04/25/2016   Hitachi         T.Iwamoto       Update          QC#7450
 * 05/10/2016   Hitachi         O.Okuma         Update          QC#8107
 * 05/19/2016   Hitachi         T.Iwamoto       Update          QC#8457
 * 06/16/2016   Hitachi         T.Aoyagi        Update          QC#9659
 * 06/28/2016   Hitachi         T.Kanasaka      Update          QC#10585
 * 07/21/2016   Hitachi         T.Iwamoto       Update          QC#12154
 * 09/01/2016   Hitachi         T.Kanasaka      Update          QC#13529
 * 06/07/2017   Hitachi         N.Arai          Update          QC#17527
 * 08/07/2017   Hitachi         M.Kidokoro      Update          QC#20073
 * 08/16/2017   Hitachi         K.Kim           Update          QC#19406
 * 09/12/2017   Hitachi         T.Kanasaka      Update          QC#15134
 * 10/25/2017   CITS            M.Naito         Update          QC#22062
 * 11/22/2017   Hitachi         E.Kameishi      Update          QC#19735
 * 04/02/2018   Hitachi         U.Kim           Update          QC#23559(Sol358)
 * 2018/05/31   Hitachi         K.Kojima        Update          QC#23685
 * 07/19/2018   CITS            M.Naito         Update          QC#13309
 * 04/24/2019   Hitachi         K.Kim           Update          QC#31272
 *</pre>
 */
public class NSZC006001 extends S21ApiCommonBase implements NSZC006001Constant {

    /** mode : Create */
    public static final String CREATE = "C";

    /** mode : Correct */
    public static final String CORRECT = "U";

    // START 2016/06/16 T.Aoyagi [QC#9659, ADD]
    /** Sales Allocation Rate : 100 */
    public static final int SLS_ALLOC_RATE_100 = 100;
    // END 2016/06/16 T.Aoyagi [QC#9659, ADD]

    /** ssm batch client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** EXCH_RATE for cache */
    private Map<String, Object> exchRateHash = null;

    /** GLBL_CMPY for cache */
    private static Map<String, Object> glblCmpyHash = null;

    /** invLine insert data. */
    private List<SVC_INV_LINETMsg> insertSvcInvLineList = new ArrayList<SVC_INV_LINETMsg>();

    /** invLineAlloc insert data. */
    private List<SVC_INV_LINE_ALLOCTMsg> insertSvcInvLineAllocList = new ArrayList<SVC_INV_LINE_ALLOCTMsg>();

    /** FsrEvent insert data. */
    private List<FSR_EVENTTMsg> insertFsrEventList = new ArrayList<FSR_EVENTTMsg>();

    // Add Start 09/28/2015
    /** SvcInvLineTaxDtl insert data. */
    private List<SVC_INV_LINE_TAX_DTLTMsg> insertSvcInvLineTaxDtlList = new ArrayList<SVC_INV_LINE_TAX_DTLTMsg>();

    /** Online Batch Type */
    private ONBATCH_TYPE onBatTp = null;
    // Add End 09/28/2015

    /**
     * <pre>
     * Constructor
     * </pre>
     * @param none
     * @throws none
     */
    public NSZC006001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        glblCmpyHash = (Map<String, Object>) S21ApplicationCacheHolder.get(KEY_GLBL_CMPY);

        if (glblCmpyHash == null) {

            glblCmpyHash = new HashMap<String, Object>();
            S21ApplicationCacheHolder.put(KEY_GLBL_CMPY, glblCmpyHash);
        }

        exchRateHash = (Map<String, Object>) S21ApplicationCacheHolder.get(KEY_EXCH_RATE);

        if (exchRateHash == null) {

            exchRateHash = new HashMap<String, Object>();
            S21ApplicationCacheHolder.put(KEY_EXCH_RATE, exchRateHash);
        }
    }

    /**
     * <pre>
     * One Time Invoice API
     * call execute(NSZC006001PMsg, ONBATCH_TYPE) method by each PMsg.
     * </pre>
     * @param inpPrmMsg NSZC006001PMsg List
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NSZC006001PMsg> inpPrmMsg, final ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inpPrmMsg.size(); i++) {
            execute(inpPrmMsg.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * One Time Invoice API
     * </pre>
     * @param inPrmPMsg NSZC006001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NSZC006001PMsg inPrmPMsg, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inPrmPMsg);
        // Add Start 09/28/2015
        this.onBatTp = onBatchType;
        // Add End 09/28/2015

        // Common In-parameter check
        if (!checkInputHdrCommon(msgMap)) {
            // When error, exit process.
            msgMap.flush();
            return;
        }
        // Common In-parameter check
        if (!checkInputLineCommon(msgMap)) {
            // When error, exit process.
            msgMap.flush();
            return;
        }

        SvcInvBean invBean = new SvcInvBean(inPrmPMsg);

        // execute main process
        doProcess(invBean, onBatchType);

        // send Message List to EZ API parameter.
        invBean.flushMessageMap();
    }

    /**
     * <pre>
     * One Time Invoice (Main process)
     * </pre>
     * @param msgMap Message Map
     * @param onBatchType
     */
    private void doProcess(SvcInvBean invBean, ONBATCH_TYPE onBatchType) {

        // ***********************************************************************
        // If Mode Code is Correct, create reversal invoice and
        // invoice line data.
        // ***********************************************************************
        if (CORRECT.equals(invBean.getModeCd())) {
            // create correct invoice and invoice line data
            if (!createCrctInv(invBean)) {
                return;
            }
        }

        // ***********************************************************************
        // If Mode Code is Correct, create correct invoice and invoice
        // line
        // data.
        // The Other, create normal invoice and invoice line data.
        // ***********************************************************************
        // get invoice header master information
        if (!setInvTmsg(invBean)) {
            return;
        }

        // Add Start 09/01/2016 <QC#13529>
        // set invoice type
        if (!setInvTp(invBean, invBean.getSvcInvTMsg())) {
            return;
        }

        // set invoice number
        if (!setInvNum(invBean, invBean.getSvcInvTMsg())) {
            return;
        }
        // Add End   09/01/2016 <QC#13529>

        // get invoice line master information
        if (!setInvLineTmsg(invBean)) {
            return;
        }

        // get invoice line allocation
        if (!setInvLineAllocTmsg(invBean)) {
            return;
        }

        // calculation invoice total amount
        if (!setInvTotAmt(invBean)) {
            return;
        }

        // Del Start 09/01/2016 <QC#13529>
//        // set invoice type
//        if (!setInvTp(invBean, invBean.getSvcInvTMsg())) {
//            return;
//        }
        // Del End   09/01/2016 <QC#13529>

        // set print status
        if (!setPrintSts(invBean, invBean.getSvcInvTMsg())) {
            return;
        }

        // create service event
        if (!createFsrEvent(invBean)) {
            return;
        }

        // set return parameter
        if (!setRtrnPmsg(invBean)) {
            return;
        }

        // create invoice line
        List<SvcInvLineBean> invLineBeanList = invBean.getInvLineBeanList();

        for (SvcInvLineBean svcInvLineBean : invLineBeanList) {
            SVC_INV_LINETMsg invLineTmsg = svcInvLineBean.getSvcInvLineTMsg();
            if (!insertSvcInvLine(invBean, invLineTmsg)) {
                return;
            }
        }
        // create invoice line alloc
        List<SVC_INV_LINE_ALLOCTMsg> invLineAllocList = invBean.getInvLineAllocList();

        for (SVC_INV_LINE_ALLOCTMsg invLineAllocTmsg : invLineAllocList) {
            if (!insertSvcInvLineAlloc(invBean, invLineAllocTmsg)) {
                return;
            }
        }
        // regist invoice header
        if (!createSvcInv(invBean, invBean.getSvcInvTMsg())) {
            return;
        }
        // regist invoice line
        if (!insertSvcInvLineFlash(invBean)) {
            return;
        }
        // regist invoice line alloc
        if (!insertSvcInvLineAllocFlash(invBean)) {
            return;
        }
        // Mod Start 09/28/2015
        // regist fsr event
        if (!insertFsrEventFlash(invBean)) {
            return;
        }
        // regist svc inv line tax dtl
        insertSvcInvLineTaxDtlFlash(invBean);
        // Mod End 09/28/2015
    }

    /**
     * <pre>
     * create correct invoice
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean createCrctInv(SvcInvBean invBean) {

        // create reverse invoice
        SVC_INVTMsg origInvTmsg = getOrigInv(invBean);
        if (origInvTmsg == null) {
            return false;
        }

        SVC_INVTMsg crctInvTmsg = getCrctInvTmsg(invBean, origInvTmsg);
        if (crctInvTmsg == null) {
            return false;
        }

        if (!createSvcInv(invBean, crctInvTmsg)) {
            return false;
        }

        // create reverse invoice line
        SVC_INV_LINETMsgArray origInvLineTmsgArray = getOrigInvLine(invBean);

        if (origInvLineTmsgArray == null || origInvLineTmsgArray.length() == 0) {
            return false;
        }
        // create reverse invoice line alloc
        SVC_INV_LINE_ALLOCTMsgArray origInvLineAllocTmsgArray = getOrigInvLineAlloc(invBean);

        if (origInvLineAllocTmsgArray == null || origInvLineAllocTmsgArray.length() == 0) {
            return false;
        }

        for (int i = 0; i < origInvLineTmsgArray.getValidCount(); i++) {
            SVC_INV_LINETMsg invLineTmsg = (SVC_INV_LINETMsg) origInvLineTmsgArray.get(i);
            SVC_INV_LINETMsg crctInvLineTmsg = setCrctInvLineTmsg(invBean, invLineTmsg, crctInvTmsg.svcInvNum.getValue());
            if (crctInvLineTmsg == null) {
                return false;
            }
            if (!insertSvcInvLine(invBean, crctInvLineTmsg)) {
                return false;
            }

            SVC_INV_LINE_ALLOCTMsg invLineAllocTmsg = (SVC_INV_LINE_ALLOCTMsg) origInvLineAllocTmsgArray.get(i);
            SVC_INV_LINE_ALLOCTMsg crctInvLineAllocTmsg = setCrctInvLineAllocTmsg(invBean, invLineAllocTmsg, crctInvTmsg.svcInvNum.getValue(), crctInvLineTmsg.svcInvLinePk.getValue());
            if (crctInvLineAllocTmsg == null) {
                return false;
            }
            if (!insertSvcInvLineAlloc(invBean, crctInvLineAllocTmsg)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * set correct invoice
     * </pre>
     * @param invBean SvcInvBean
     * @param invTmsg SVC_INVTMsg
     * @return SVC_INVTMsg
     */
    private SVC_INVTMsg getCrctInvTmsg(SvcInvBean invBean, SVC_INVTMsg invTmsg) {

        // create credit invoice data
        SVC_INVTMsg crctInvTmsg = new SVC_INVTMsg();
        EZDMsg.copy(invTmsg, null, crctInvTmsg, null);

        BigDecimal svcInvPk = getSvcInvPk(invBean);

        if (svcInvPk == null) {
            return null;
        }

        crctInvTmsg.svcInvPk.setValue(svcInvPk);
        // Del Start 09/01/2016 <QC#13529>
//        crctInvTmsg.svcInvNum.setValue(ZYPExtnNumbering.getUniqueID(invBean.getGlblCmpyCd(), SVC_INV_NUM));
        // Del End   09/01/2016 <QC#13529>
        crctInvTmsg.origSvcInvNum.setValue(invTmsg.svcInvNum.getValue());

        crctInvTmsg.invTotDealDiscAmt.setValue(invTmsg.invTotDealDiscAmt.getValue().negate());
        crctInvTmsg.invTotDealNetAmt.setValue(invTmsg.invTotDealNetAmt.getValue().negate());
        crctInvTmsg.invTotDealSlsAmt.setValue(invTmsg.invTotDealSlsAmt.getValue().negate());
        crctInvTmsg.invTotDealTaxAmt.setValue(invTmsg.invTotDealTaxAmt.getValue().negate());
        crctInvTmsg.invTotFuncDiscAmt.setValue(invTmsg.invTotFuncDiscAmt.getValue().negate());
        crctInvTmsg.invTotFuncNetAmt.setValue(invTmsg.invTotFuncNetAmt.getValue().negate());
        crctInvTmsg.invTotFuncSlsAmt.setValue(invTmsg.invTotFuncSlsAmt.getValue().negate());
        crctInvTmsg.invTotFuncTaxAmt.setValue(invTmsg.invTotFuncTaxAmt.getValue().negate());
        // Add Start 02/25/2016 QC#3991
        crctInvTmsg.crCardCustRefNum.setValue(invTmsg.crCardCustRefNum.getValue());
        // Add End 02/25/2016 QC#3991

        // Add Start 05/10/2016 QC#8107
        crctInvTmsg.cfsLinkStsCd.setValue(CFS_LINK_STS.N_OR_A);
        // Add End 05/10/2016 QC#8107

        if (!setInvTp(invBean, crctInvTmsg)) {
            return null;
        }

        // Add Start 09/01/2016 <QC#13529>
        // set invoice number
        if (!setInvNum(invBean, crctInvTmsg)) {
            return null;
        }
        // Add End   09/01/2016 <QC#13529>

        if (!setPrintSts(invBean, crctInvTmsg)) {
            return null;
        }
        // set invDt
        if (!ZYPCommonFunc.hasValue(invTmsg.invDt)) {
            String invDt = ZYPDateUtil.getSalesDate(invBean.getGlblCmpyCd());
            invTmsg.invDt.setValue(invDt);
        }
        // reset svc inv sts cd
        crctInvTmsg.svcInvStsCd.setValue(SVC_INV_STS.PRINTED);
        // reset om link flg
        crctInvTmsg.svcInvOmLinkFlg.setValue(ZYPConstant.FLG_OFF_N);
        return crctInvTmsg;
    }

    /**
     * <pre>
     * set Invoice Data
     * </pre>
     * @param invBean SvcInvBean
     */
    private boolean setInvTmsg(SvcInvBean invBean) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        // START 2017/06/07 N.Arai [QC#17527, MOD]
        ZYPEZDItemValueSetter.setValue(invTmsg.custIssPoNum, invBean.getCustPoNum());
        // END 2017/06/07 N.Arai [QC#17527, MOD]
        
        // set invDt
        if (!ZYPCommonFunc.hasValue(invTmsg.invDt)) {
            String invDt = ZYPDateUtil.getSalesDate(invBean.getGlblCmpyCd());
            invTmsg.invDt.setValue(invDt);
        }

        // set svc inv sts
        invTmsg.svcInvStsCd.setValue(SVC_INV_STS.PRINTED);

        // get payment term cash discount
        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTmsg = getPmtTermCashDisc(invBean);

        if (pmtTermCashDiscTmsg == null) {
            return false;
        } else {
            invTmsg.pmtTermCd.setValue(pmtTermCashDiscTmsg.pmtTermCd.getValue());
            invTmsg.pmtTermCashDiscDescTxt.setValue(pmtTermCashDiscTmsg.pmtTermCashDiscDescTxt.getValue());
        }

        // pmtTermSendFaxFlg is used for getting print status
        if (ZYPConstant.FLG_ON_Y.equals(pmtTermCashDiscTmsg.pmtTermSendFaxFlg.getValue())) {
            invBean.setPmtTermSendFaxFlg(true);
        }

        // get payment term
        PMT_TERMTMsg pmtTermTmsg = getPmtTerm(invBean);

        if (pmtTermTmsg == null) {
            return false;
        } else {
            // calc Due Dt
            String dueDt = null;
            if (ZYPCommonFunc.hasValue(invTmsg.pmtTermStartDt)) {
                dueDt = ZYPDateUtil.addDays(invTmsg.pmtTermStartDt.getValue(), pmtTermTmsg.pmtTermAot.getValue().intValue());
            } else {
                dueDt = ZYPDateUtil.addDays(invTmsg.invDt.getValue(), pmtTermTmsg.pmtTermAot.getValue().intValue());
            }
            invTmsg.invDueDt.setValue(dueDt);
        }

        // get sell to cust information
        SELL_TO_CUSTTMsg sellToCustTmsg = getSellToCust(invBean);

        if (sellToCustTmsg == null) {
            return false;
        } else {

            // set sell to cust information
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToLocNm, sellToCustTmsg.locNm);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToAddlLocNm, sellToCustTmsg.addlLocNm);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToFirstLineAddr, sellToCustTmsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToScdLineAddr, sellToCustTmsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToThirdLineAddr, sellToCustTmsg.thirdLineAddr);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToFrthLineAddr, sellToCustTmsg.frthLineAddr);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToCtyAddr, sellToCustTmsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToProvNm, sellToCustTmsg.provNm);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToStCd, sellToCustTmsg.stCd);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToPostCd, sellToCustTmsg.postCd);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToCtryCd, sellToCustTmsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToFirstRefCmntTxt, sellToCustTmsg.firstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(invTmsg.sellToScdRefCmntTxt, sellToCustTmsg.scdRefCmntTxt);
        }

        // get country information
        if (ZYPCommonFunc.hasValue(sellToCustTmsg.cntyPk)) {
            CNTYTMsg cntyTmsg = getCnty(invBean, sellToCustTmsg.cntyPk.getValue());
            if (cntyTmsg != null) {
                invTmsg.sellToCntyNm.setValue(cntyTmsg.cntyNm.getValue());
            }
        }

        // get bill to cust information
        // Mod Start 09/28/2015
        //BILL_TO_CUSTTMsg billToCustTmsg = getBillToCust(invBean);
        //
        //if (billToCustTmsg == null) {
        //    return false;
        //}
        Map<String, String> billToCustMap = getBillToCust(invBean);

        if (billToCustMap == null) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(invTmsg.billToCustAcctCd, (String) billToCustMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToLocNm, (String) billToCustMap.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToAddlLocNm, (String) billToCustMap.get("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToFirstLineAddr, (String) billToCustMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToScdLineAddr, (String) billToCustMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToThirdLineAddr, (String) billToCustMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToFrthLineAddr, (String) billToCustMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToCtyAddr, (String) billToCustMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToProvNm, (String) billToCustMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToCntyNm, (String) billToCustMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToStCd, (String) billToCustMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToPostCd, (String) billToCustMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToCtryCd, (String) billToCustMap.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToFirstRefCmntTxt, (String) billToCustMap.get("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(invTmsg.billToScdRefCmntTxt, (String) billToCustMap.get("SCD_REF_CMNT_TXT"));
        // Mod End 09/28/2015

        // Del Start 09/28/2015
        // flPlnFlg is used for getting print status
        //if (ZYPConstant.FLG_ON_Y.equals(billToCustTmsg.flPlnCmpyFlg.getValue())) {
        //invBean.setFlPlnFlg(true);
        //}
        // Del End 09/28/2015

        // get ofc nm
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(invBean);

        if (glblCmpyTMsg == null) {
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(invTmsg.ofcNm, glblCmpyTMsg.glblCmpyNm.getValue());
        }
        // get Office Information
        //Del Start 2016/04/25 CSA Defect#7450
        //Map<String, String> ofcMap = getOfcInfo(invBean, invTmsg.billToCustCd.getValue());

        //if (ofcMap == null) {
        //    return false;
        //} else {
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcBrCd, (String) ofcMap.get(COL_BR_CD));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcLocNm, (String) ofcMap.get(COL_LOC_NM));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcAddlLocNm, (String) ofcMap.get(COL_ADDL_LOC_NM));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcFirstLineAddr, (String) ofcMap.get(COL_FIRST_LINE_ADDR));
        //   ZYPEZDItemValueSetter.setValue(invTmsg.ofcScdLineAddr, (String) ofcMap.get(COL_SCD_LINE_ADDR));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcThirdLineAddr, (String) ofcMap.get(COL_THIRD_LINE_ADDR));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcFrthLineAddr, (String) ofcMap.get(COL_FRTH_LINE_ADDR));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcCtyAddr, (String) ofcMap.get(COL_CTY_ADDR));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcCntyNm, (String) ofcMap.get(COL_CNTY_NM));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcProvNm, (String) ofcMap.get(COL_PROV_NM));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcStCd, (String) ofcMap.get(COL_ST_CD));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcPostCd, (String) ofcMap.get(COL_POST_CD));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcCtryCd, (String) ofcMap.get(COL_CTRY_CD));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcTelNum, (String) ofcMap.get(COL_TEL_NUM));
        //    ZYPEZDItemValueSetter.setValue(invTmsg.ofcFaxNum, (String) ofcMap.get(COL_FAX_NUM));
        //}
        //Del end 2016/04/25 CSA Defect#7450

        // coaAfflCd is used for coaAfflCd of invoice line
        // Mod Start 09/28/2015, 04/25/2016 QC#7450
        //invBean.setCoaAfflCd(billToCustTmsg.coaAfflCd.getValue());
        invBean.setCoaAfflCd((String) billToCustMap.get("COA_AFFL_CD"));
        invBean.setCoaChCd((String) billToCustMap.get("COA_CH_CD"));
        // Mod End 09/28/2015, 04/25/2016 QC#7450

        // get rem to information
        // Mod Start 09/28/2015
        //REM_TOTMsg remToTmsg = getRemTo(invBean, billToCustTmsg.remId.getValue());
        REM_TOTMsg remToTmsg = getRemTo(invBean, (String) billToCustMap.get("REM_ID"));
        // Mod End 09/28/2015

        if (remToTmsg == null) {
            return false;
        } else {
            invTmsg.remId.setValue(remToTmsg.remId.getValue());
            invTmsg.remToLocNm.setValue(remToTmsg.locNm.getValue());
            invTmsg.remToAddlLocNm.setValue(remToTmsg.addlLocNm.getValue());
        }

        // get exch rate
        if (!ZYPCommonFunc.hasValue(invTmsg.ccyExchRate)) {

            ACCT_DLY_ACTL_EXCH_RATESTMsg exchRateTmsg = getExchRate(invBean);

            if (exchRateTmsg == null) {
                return false;
            }
            invTmsg.ccyExchRate.setValue(exchRateTmsg.actlExchRate.getValue());
        }

        // get curency
        CCYTMsg ccyTmsg = getCcy(invBean);
        if (ccyTmsg == null) {
            return false;
        }
        invBean.setAcctArthTpCd(ccyTmsg.acctArthTpCd.getValue());
        invBean.setAftDeclPntDigitNum(ccyTmsg.aftDeclPntDigitNum.getValue());

        // get mdse information
        if (!ZYPCommonFunc.hasValue(invTmsg.mdseNm)) {
            MDSETMsg mdseTmsg = getMdse(invBean, HEADER);
            if (mdseTmsg == null) {
                return false;
            }
            invTmsg.mdseNm.setValue(mdseTmsg.mdseNm.getValue());
        }

        // get mdl information
        if (!ZYPCommonFunc.hasValue(invTmsg.mdlNm)) {
            // Mod Start 09/28/2015
            //MDL_NMTMsg mdlTmsg = getMdl(invBean, HEADER);
            MDL_NMTMsg mdlTmsg = getMdl(invBean);
            // Mod End 09/28/2015
            if (mdlTmsg == null) {
                invBean.addXxMsgId(NSZM0100E);
                return false;
            }
            invTmsg.mdlId.setValue(mdlTmsg.t_MdlId.getValue());
            invTmsg.mdlNm.setValue(mdlTmsg.t_MdlNm.getValue());
            invBean.setSvcInvTMsg(invTmsg);
        }

        // get contr information
        if (!ZYPCommonFunc.hasValue(invTmsg.dsContrNum)) {
            NSXC001001PMsg contrPmsg = new NSXC001001PMsg();
            contrPmsg.glblCmpyCd.setValue(invBean.getGlblCmpyCd());
            contrPmsg.svcMachMstrPk.setValue(invTmsg.svcMachMstrPk.getValue());
            contrPmsg.slsDt.setValue(invBean.getRtlInvPMsg().invDt.getValue());
            NSXC001001Contr.getContr(contrPmsg);
            if (contrPmsg.xxContrList == null || contrPmsg.xxContrList.length() == 0) {
                // do nothing
            } else {
                ZYPEZDItemValueSetter.setValue(invTmsg.dsContrPk, contrPmsg.xxContrList.no(0).dsContrPk);
                ZYPEZDItemValueSetter.setValue(invTmsg.dsContrDtlPk, contrPmsg.xxContrList.no(0).dsContrDtlPk);
                ZYPEZDItemValueSetter.setValue(invTmsg.dsContrNum, contrPmsg.xxContrList.no(0).dsContrNum);
            }
        }

        // Add Start 09/28/2015
        // set Line of Business Type Code
        SVC_MACH_MSTRTMsg svcMachMstrTmsg = getSvcMachMstr(invBean);
        if (svcMachMstrTmsg == null) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(invTmsg.lineBizTpCd, svcMachMstrTmsg.svcByLineBizTpCd);

        // set Contact Person Primary Key
        // START 2017/08/16 K.Kim [QC#19406, MOD]
//        if (ZYPCommonFunc.hasValue(invTmsg.dsContrPk)) {
//            DS_CONTRTMsg dsContrTmsg = getDsContr(invBean);
//            if (dsContrTmsg != null) {
//                ZYPEZDItemValueSetter.setValue(invTmsg.ctacPsnPk, dsContrTmsg.ctacPsnPk);
//            }
//        }
        if (ZYPCommonFunc.hasValue(invTmsg.billToCustCd)) {
            BigDecimal ctacPsnPk = getCtacPsnPk(invBean);
            if (ctacPsnPk != null) {
                ZYPEZDItemValueSetter.setValue(invTmsg.ctacPsnPk, ctacPsnPk);
            }
        }
        // END 2017/08/16 K.Kim [QC#19406, MOD]

        // set DS Account Number
        ZYPEZDItemValueSetter.setValue(invTmsg.dsAcctNum, invTmsg.sellToCustCd);

        // set Sales Rep TOC Code
        SVC_INV_LINETMsg invLineTmsg = invBean.getInvLineBeanList().get(0).getSvcInvLineTMsg();
        ZYPEZDItemValueSetter.setValue(invTmsg.slsRepTocCd, invLineTmsg.slsRepTocCd);

        // set Ship To Customer Account Code
        SHIP_TO_CUSTTMsg shipToCustTmsg = new SHIP_TO_CUSTTMsg();
        if (ZYPCommonFunc.hasValue(invTmsg.shipToCustCd)) {
            shipToCustTmsg = getShipToCust(invBean, invTmsg.shipToCustCd.getValue());
        } else {
            shipToCustTmsg = getShipToCust(invBean, svcMachMstrTmsg.shipToCustCd.getValue());
        }
        if (shipToCustTmsg == null) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(invTmsg.shipToCustAcctCd, shipToCustTmsg.sellToCustCd);

        // set Service Invoice OM Link Status Code
        ZYPEZDItemValueSetter.setValue(invTmsg.svcInvOmLinkStsCd, STS_CD_ZERO);
        // Add End 09/28/2015

        // get serNum
        ZYPEZDItemValueSetter.setValue(invTmsg.serNum, getSerNum(invBean.getGlblCmpyCd(), invTmsg.svcMachMstrPk.getValue()));
        // set inv ref num
        invTmsg.svcInvRefNum.setValue(invTmsg.fsrNum.getValue() + invTmsg.fsrVisitNum.getValue());

        // get service inv num
        BigDecimal svcInvPk = getSvcInvPk(invBean);
        if (svcInvPk == null) {
            return false;
        }
        invTmsg.svcInvPk.setValue(svcInvPk);
        // Del Start 09/01/2016 <QC#13529>
//        invTmsg.svcInvNum.setValue(ZYPExtnNumbering.getUniqueID(invBean.getGlblCmpyCd(), SVC_INV_NUM));
        // Del End   09/01/2016 <QC#13529>
        // QC#2515 add
        invTmsg.svcInvSrcTpCd.setValue(SVC_INV_SRC_TP.DISPATCH);

        // Add Start 02/25/2016 QC#3991
        if (ZYPCommonFunc.hasValue(invTmsg.fsrNum)) {
            String crCardCustRefNum = getCrCardCustRefNum(invBean.getGlblCmpyCd(), invTmsg.fsrNum.getValue());
            ZYPEZDItemValueSetter.setValue(invTmsg.crCardCustRefNum, crCardCustRefNum);
        }
        // Add End 02/25/2016 QC#3991

        // START 2018/05/31 K.Kojima [QC#23685,ADD]
        ZYPEZDItemValueSetter.setValue(invTmsg.tempSvcInvNumFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/05/31 K.Kojima [QC#23685,ADD]

        // START 2018/07/18 M.Naito [QC#13309, ADD]
        // check Temporary Entitlement
        FSRTMsg fsrTmsg = getFsr(invBean);
        if (fsrTmsg != null && hasValue(fsrTmsg.tempEttlNum)) {
            ZYPEZDItemValueSetter.setValue(invTmsg.tempEttlNum, fsrTmsg.tempEttlNum.getValue());
        }
        // END 2018/07/18 M.Naito [QC#13309, ADD]
        return true;
    }

    /**
     * <pre>
    * calculation invoice line amount
    * </pre>
     * @param invBean SvcInvBean
     * @param invLineTmsg SVC_INV_LINETMsg
     * @return boolean
     */
    private boolean setInvLineAmt(SvcInvBean invBean, SVC_INV_LINETMsg invLineTmsg) {

        BigDecimal exchRate = invBean.getSvcInvTMsg().ccyExchRate.getValue();
        BigDecimal invQty = invLineTmsg.svcInvQty.getValue();
        String acctArthTpCd = invBean.getAcctArthTpCd();
        int scale = invBean.getAftDeclPntDigitNum();

        // ***********************************************************************
        // **************************** set deal amount
        // **************************
        // ***********************************************************************
        BigDecimal invLineDealSlsAmt = BigDecimal.ZERO;
        BigDecimal invLineDealDiscAmt = BigDecimal.ZERO;
        BigDecimal invLineDealNetAmt = BigDecimal.ZERO;

        // ********** set discount amount **********
        if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(invLineTmsg.invLineDiscRate)) {
                // no discount.
                invLineTmsg.dealDiscUnitPrcAmt.setValue(BigDecimal.ZERO);
                invLineTmsg.invLineDealDiscAmt.setValue(BigDecimal.ZERO);
            } else if (!ZYPCommonFunc.hasValue(invLineTmsg.invLineDealDiscAmt)) {
                // both of unit prc amt is set and Line amt is not
                // set.
                invLineDealDiscAmt = invLineTmsg.dealDiscUnitPrcAmt.getValue().multiply(invQty);
                if (!checkDigit(invLineDealDiscAmt.negate())) {
                    invBean.addXxMsgId(NSZM0112E);
                    return false;
                }
                invLineTmsg.invLineDealDiscAmt.setValue(invLineDealDiscAmt);
            }
        } else {
            if (!ZYPCommonFunc.hasValue(invLineTmsg.invLineDiscRate)) {
                // no discount.
                invLineTmsg.dealDiscUnitPrcAmt.setValue(BigDecimal.ZERO);
                invLineTmsg.invLineDealDiscAmt.setValue(BigDecimal.ZERO);
            } else if (!ZYPCommonFunc.hasValue(invLineTmsg.dealDiscUnitPrcAmt)) {
                invLineTmsg.dealDiscUnitPrcAmt.setValue(invLineTmsg.invLineDealDiscAmt.getValue());
            }
        }

        // ********** set unit amount **********
        if (!SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
            // invLineTmsg.dealUnitPrcAmt.setValue(invLineTmsg.invLineDealSlsAmt.getValue().add(invLineTmsg.invLineDealDiscAmt.getValue()));
            invLineTmsg.dealUnitPrcAmt.setValue(invLineTmsg.invLineDealSlsAmt.getValue());
        } else {
            // invLineTmsg.dealUnitPrcAmt.setValue(invLineTmsg.dealUnitPrcAmt.getValue().add(invLineTmsg.dealDiscUnitPrcAmt.getValue()));
            invLineTmsg.dealUnitPrcAmt.setValue(invLineTmsg.dealUnitPrcAmt.getValue());
        }

        // ********** set sls amount **********
        if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue()) && !ZYPCommonFunc.hasValue(invLineTmsg.invLineDealSlsAmt)) {
            // invLineDealSlsAmt =
            // invLineTmsg.dealUnitPrcAmt.getValue().subtract(invLineTmsg.dealDiscUnitPrcAmt.getValue()).multiply(invQty);
            invLineDealSlsAmt = invLineTmsg.dealUnitPrcAmt.getValue().multiply(invQty);
            if (!checkDigit(invLineDealSlsAmt)) {
                invBean.addXxMsgId(NSZM0106E);
                return false;
            }
            invLineTmsg.invLineDealSlsAmt.setValue(invLineDealSlsAmt);
        }

        // ********** set net amount **********
        // invLineDealNetAmt =
        // invLineTmsg.invLineDealSlsAmt.getValue().add(invLineTmsg.invLineDealTaxAmt.getValue().add(invLineTmsg.invLineDealDiscAmt.getValue()));
        // invLineDealNetAmt =
        // invLineTmsg.invLineDealSlsAmt.getValue().add(invLineTmsg.invLineDealDiscAmt.getValue());
        invLineDealNetAmt = invLineTmsg.invLineDealSlsAmt.getValue().subtract(invLineTmsg.invLineDealDiscAmt.getValue());
        if (!checkDigit(invLineDealNetAmt)) {
            invBean.addXxMsgId(NSZM0108E);
            return false;
        }
        invLineTmsg.invLineDealNetAmt.setValue(invLineDealNetAmt);

        // ********** set service labor amount **********
        if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue()) && !ZYPCommonFunc.hasValue(invLineTmsg.svcLborFuncAmt)) {
            invLineTmsg.svcLborFuncAmt.setValue(calcFuncAmt(invLineTmsg.svcLborDealAmt.getValue(), acctArthTpCd, exchRate, scale));
        }

        // ***********************************************************************
        // **************************** set func amount
        // **************************
        // ***********************************************************************
        BigDecimal invLineFuncSlsAmt = BigDecimal.ZERO;
        BigDecimal invLineFuncDiscAmt = BigDecimal.ZERO;
        BigDecimal invLineFuncNetAmt = BigDecimal.ZERO;
        BigDecimal invLineFuncTaxAmt = BigDecimal.ZERO;
        BigDecimal funcUnitPrcAmt = BigDecimal.ZERO;

        // ********** set unit amount **********
        if (!SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
            if (ZYPCommonFunc.hasValue(invLineTmsg.invLineFuncSlsAmt) && ZYPCommonFunc.hasValue(invLineTmsg.invLineFuncDiscAmt)) {
                // invLineTmsg.funcUnitPrcAmt.setValue(invLineTmsg.invLineFuncSlsAmt.getValue().add(invLineTmsg.invLineFuncDiscAmt.getValue()));
                invLineTmsg.funcUnitPrcAmt.setValue(invLineTmsg.invLineFuncSlsAmt.getValue());
            }
        } else {
            if (ZYPCommonFunc.hasValue(invLineTmsg.funcUnitPrcAmt) && ZYPCommonFunc.hasValue(invLineTmsg.funcDiscUnitPrcAmt)) {
                // invLineTmsg.funcUnitPrcAmt.setValue(invLineTmsg.funcUnitPrcAmt.getValue().add(invLineTmsg.funcDiscUnitPrcAmt.getValue()));
                invLineTmsg.funcUnitPrcAmt.setValue(invLineTmsg.funcUnitPrcAmt.getValue());
            }
        }

        // ********** set func amount **********
        if (!ZYPCommonFunc.hasValue(invLineTmsg.funcUnitPrcAmt) || !ZYPCommonFunc.hasValue(invLineTmsg.funcDiscUnitPrcAmt) || !ZYPCommonFunc.hasValue(invLineTmsg.invLineFuncSlsAmt) || !ZYPCommonFunc.hasValue(invLineTmsg.invLineFuncDiscAmt)) {
            funcUnitPrcAmt = calcFuncAmt(invLineTmsg.dealUnitPrcAmt.getValue(), acctArthTpCd, exchRate, scale);
            if (!checkDigit(funcUnitPrcAmt)) {
                invBean.addXxMsgId(NSZM0375E);
                return false;
            }
            invLineTmsg.funcUnitPrcAmt.setValue(funcUnitPrcAmt);
            invLineTmsg.funcDiscUnitPrcAmt.setValue(calcFuncAmt(invLineTmsg.dealDiscUnitPrcAmt.getValue(), acctArthTpCd, exchRate, scale));
            // disc
            invLineFuncDiscAmt = calcFuncAmt(invLineTmsg.invLineDealDiscAmt.getValue(), acctArthTpCd, exchRate, scale);
            if (!checkDigit(invLineFuncDiscAmt.negate())) {
                invBean.addXxMsgId(NSZM0113E);
                return false;
            }
            invLineTmsg.invLineFuncDiscAmt.setValue(invLineFuncDiscAmt);
            // sls
            invLineFuncSlsAmt = calcFuncAmt(invLineTmsg.invLineDealSlsAmt.getValue(), acctArthTpCd, exchRate, scale);
            if (!checkDigit(invLineFuncSlsAmt)) {
                invBean.addXxMsgId(NSZM0107E);
                return false;
            }
            invLineTmsg.invLineFuncSlsAmt.setValue(invLineFuncSlsAmt);
        }

        // ********** set tax amount **********
        if (!ZYPCommonFunc.hasValue(invLineTmsg.invLineFuncTaxAmt) && ZYPCommonFunc.hasValue(invLineTmsg.invLineDealTaxAmt.getValue())) {
            invLineFuncTaxAmt = calcFuncAmt(invLineTmsg.invLineDealTaxAmt.getValue(), acctArthTpCd, exchRate, scale);
            if (!checkDigit(invLineFuncTaxAmt)) {
                invBean.addXxMsgId(NSZM0111E);
                return false;
            }
            invLineTmsg.invLineFuncTaxAmt.setValue(invLineFuncTaxAmt);
        }
        // ********** set net amount **********
        // invLineFuncNetAmt =
        // invLineTmsg.invLineFuncSlsAmt.getValue().add(invLineTmsg.invLineFuncTaxAmt.getValue().add(invLineTmsg.invLineFuncDiscAmt.getValue()));
        invLineFuncNetAmt = invLineTmsg.invLineFuncSlsAmt.getValue().subtract(invLineTmsg.invLineFuncDiscAmt.getValue());
        // invLineFuncNetAmt =
        // invLineTmsg.funcUnitPrcAmt.getValue().multiply(invLineTmsg.svcInvQty.getValue());
        if (!checkDigit(invLineFuncNetAmt)) {
            invBean.addXxMsgId(NSZM0109E);
            return false;
        }
        invLineTmsg.invLineFuncNetAmt.setValue(invLineFuncNetAmt);

        return true;
    }

    /**
     * <pre>
     * set invoice tax amount
     * </pre>
     * @param invBean SvcInvBean
     * @param invLineTmsg SVC_INV_LINETMsg
     * @param taxCalcFlg String
     * @return boolean
     */
    // Mod Start 09/01/2016 <QC#13529>
//    private boolean setTaxAmt(SvcInvBean invBean, SVC_INV_LINETMsg invLineTmsg) {
    private boolean setTaxAmt(SvcInvBean invBean, SVC_INV_LINETMsg invLineTmsg, String taxCalcFlg) {
    // Mod End   09/01/2016 <QC#13529>

        BigDecimal funcTaxAmt = BigDecimal.ZERO;
        BigDecimal dealTaxAmt = BigDecimal.ZERO;
        BigDecimal taxRt = BigDecimal.ZERO;

        // Mod Start 09/01/2016 <QC#13529>
//        if (invLineTmsg.funcUnitPrcAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
        if (invLineTmsg.funcUnitPrcAmt.getValue().compareTo(BigDecimal.ZERO) != 0 && ZYPConstant.FLG_ON_Y.equals(taxCalcFlg)) {
        // Mod End   09/01/2016 <QC#13529>

            // Mod Start 09/28/2015
            //NWZC036001PMsg taxApiPmsg = setTaxApiParams(invBean, invLineTmsg.mdseCd.getValue(), invLineTmsg.svcInvQty.getValue(), invLineTmsg.funcUnitPrcAmt.getValue(), "1");
            //
            //if (taxApiPmsg == null) {
            //    return false;
            //}
            //
            //NWZC036001 taxApi = new NWZC036001();
            //taxApi.execute(taxApiPmsg, ONBATCH_TYPE.ONLINE);

            FSRTMsg fsrTmsg = getFsr(invBean);
            CallTaxCalcAPIBean apiBean = new CallTaxCalcAPIBean(fsrTmsg, invBean.getSvcInvTMsg(), invLineTmsg);
            NWZC036101PMsg taxApiPmsg = NSXC003001CallTaxCalcAPIForSvcPrc.callTaxCalcApi(apiBean, this.onBatTp);
            // Mod End 09/28/2015

            if (!setMessage(invBean, taxApiPmsg)) {
                return false;
            }
            // Mod Start 09/28/2015
            if (ZYPCommonFunc.hasValue(taxApiPmsg.taxCalculateOutputLine.no(0).invLineFuncTaxAmt)) {
                funcTaxAmt = taxApiPmsg.taxCalculateOutputLine.no(0).invLineFuncTaxAmt.getValue();
            }
            dealTaxAmt = calcDealAmt(funcTaxAmt, invBean.getSvcInvTMsg().ccyExchRate.getValue(), invBean.getAcctArthTpCd(), new BigDecimal(invBean.getAftDeclPntDigitNum()));
            if (ZYPCommonFunc.hasValue(taxApiPmsg.taxCalculateOutputLine.no(0).xxTaxCalcLineTaxPct)) {
                taxRt = taxApiPmsg.taxCalculateOutputLine.no(0).xxTaxCalcLineTaxPct.getValue().setScale(DEFAULT_SCL, BigDecimal.ROUND_HALF_UP);
            }
            ZYPEZDItemValueSetter.setValue(invLineTmsg.taxCalcGeoCd, taxApiPmsg.taxCalculateOutputLine.no(0).taxAreaId);

            // START 2016/06/16 T.Aoyagi [QC#9659, MOD]
            if (!createSvcInvLineTaxDtl(invBean, invLineTmsg, taxApiPmsg.taxCalculateOutputLine.no(0))) {
            // END 2016/06/16 T.Aoyagi [QC#9659, MOD]
                return false;
            }
            // Mod End 09/28/2015
        }

        invLineTmsg.slsTaxRate.setValue(taxRt);
        invLineTmsg.invLineFuncTaxAmt.setValue(funcTaxAmt);
        invLineTmsg.invLineDealTaxAmt.setValue(dealTaxAmt);

        return true;
    }

    /**
     * <pre>
     * calculation invoice total amount
     * </pre>
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean setInvTotAmt(SvcInvBean invBean) {

        // create total invoice amount
        List<SvcInvLineBean> svcInvLineBeanList = invBean.getInvLineBeanList();

        BigDecimal invTotDealSlsAmt = BigDecimal.ZERO;
        BigDecimal invTotDealTaxAmt = BigDecimal.ZERO;
        BigDecimal invTotDealDiscAmt = BigDecimal.ZERO;
        BigDecimal invTotDealNetAmt = BigDecimal.ZERO;
        BigDecimal invTotFuncSlsAmt = BigDecimal.ZERO;
        BigDecimal invTotFuncTaxAmt = BigDecimal.ZERO;
        BigDecimal invTotFuncDiscAmt = BigDecimal.ZERO;
        BigDecimal invTotFuncNetAmt = BigDecimal.ZERO;

        for (SvcInvLineBean svcInvLineBean : svcInvLineBeanList) {
            SVC_INV_LINETMsg svcInvLineTmsg = svcInvLineBean.getSvcInvLineTMsg();
            invTotDealSlsAmt = invTotDealSlsAmt.add(svcInvLineTmsg.invLineDealSlsAmt.getValue());
            invTotDealTaxAmt = invTotDealTaxAmt.add(svcInvLineTmsg.invLineDealTaxAmt.getValue());
            invTotDealDiscAmt = invTotDealDiscAmt.add(svcInvLineTmsg.invLineDealDiscAmt.getValue());
            invTotDealNetAmt = invTotDealNetAmt.add(svcInvLineTmsg.invLineDealNetAmt.getValue());
            invTotFuncSlsAmt = invTotFuncSlsAmt.add(svcInvLineTmsg.invLineFuncSlsAmt.getValue());
            invTotFuncTaxAmt = invTotFuncTaxAmt.add(svcInvLineTmsg.invLineFuncTaxAmt.getValue());
            invTotFuncDiscAmt = invTotFuncDiscAmt.add(svcInvLineTmsg.invLineFuncDiscAmt.getValue());
            invTotFuncNetAmt = invTotFuncNetAmt.add(svcInvLineTmsg.invLineFuncNetAmt.getValue());
        }
        if (!checkDigit(invTotDealSlsAmt)) {
            invBean.addXxMsgId(NSZM0114E);
            return false;
        }
        if (!checkDigit(invTotDealTaxAmt)) {
            invBean.addXxMsgId(NSZM0116E);
            return false;
        }
        if (!checkDigit(invTotDealDiscAmt.negate())) {
            invBean.addXxMsgId(NSZM0118E);
            return false;
        }
        if (!checkDigit(invTotDealNetAmt)) {
            invBean.addXxMsgId(NSZM0120E);
            return false;
        }
        if (!checkDigit(invTotFuncSlsAmt)) {
            invBean.addXxMsgId(NSZM0115E);
            return false;
        }
        if (!checkDigit(invTotFuncTaxAmt)) {
            invBean.addXxMsgId(NSZM0117E);
            return false;
        }
        if (!checkDigit(invTotFuncDiscAmt.negate())) {
            invBean.addXxMsgId(NSZM0119E);
            return false;
        }
        if (!checkDigit(invTotFuncNetAmt)) {
            invBean.addXxMsgId(NSZM0121E);
            return false;
        }

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        invTmsg.invTotDealSlsAmt.setValue(invTotDealSlsAmt);
        invTmsg.invTotDealTaxAmt.setValue(invTotDealTaxAmt);
        invTmsg.invTotDealDiscAmt.setValue(invTotDealDiscAmt);
        invTmsg.invTotDealNetAmt.setValue(invTotDealNetAmt);
        invTmsg.invTotFuncSlsAmt.setValue(invTotFuncSlsAmt);
        invTmsg.invTotFuncTaxAmt.setValue(invTotFuncTaxAmt);
        invTmsg.invTotFuncDiscAmt.setValue(invTotFuncDiscAmt);
        invTmsg.invTotFuncNetAmt.setValue(invTotFuncNetAmt);

        invBean.setSvcInvTMsg(invTmsg);

        return true;
    }

    /**
     * <pre>
     * set invoice type
     * </pre>
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean setInvTp(SvcInvBean invBean, SVC_INVTMsg invTmsg) {

        // Mod Start 09/01/2016 <QC#13529>
//        if (BigDecimal.ZERO.compareTo(invTmsg.invTotDealNetAmt.getValue()) > 0) {
        if (CORRECT.equals(invBean.getModeCd())) {
        // Mod End   09/01/2016 <QC#13529>
            invTmsg.invTpCd.setValue(INV_TP.CREDIT_MEMO);
            // Add Start 09/28/2015
            invTmsg.dsInvTpCd.setValue(ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NSZC0060_DS_INV_TYPE_CREDIT, invBean.getGlblCmpyCd()));
            // Add End 09/28/2015
        } else {
            invTmsg.invTpCd.setValue(INV_TP.INVOICE);
            // Add Start 09/28/2015
            invTmsg.dsInvTpCd.setValue(ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_NSZC0060_DS_INV_TYPE_INVOICE, invBean.getGlblCmpyCd()));
            // Add End 09/28/2015
        }
        return true;
    }

    // Add Start 09/01/2016 <QC#13529>
    /**
     * <pre>
     * set invoice number
     * </pre>
     * @param invBean SvcInvBean
     * @param invTmsg SVC_INVTMsg
     * @return boolean
     */
    private boolean setInvNum(SvcInvBean invBean, SVC_INVTMsg invTmsg) {

        DS_INV_TPTMsg dsInvTpTMsg = (DS_INV_TPTMsg) ZYPCodeDataUtil.findByCode(DS_INV_TP.class, invBean.getGlblCmpyCd(), invTmsg.dsInvTpCd.getValue());
        if (dsInvTpTMsg == null) {
            return false;
        }
        // START 2017/11/22 E.Kameishi [QC#19735, MOD]
        //invTmsg.svcInvNum.setValue(ZYPExtnNumbering.getUniqueID(invBean.getGlblCmpyCd(), dsInvTpTMsg.autoSeqCd.getValue()));
        invTmsg.svcInvNum.setValue(ZYPMaxTenDigitsNumbering.getUniqueID(invBean.getGlblCmpyCd(), dsInvTpTMsg.autoSeqCd.getValue()));
        // END 2017/11/22 E.Kameishi [QC#19735, MOD]
        return true;
    }

    /**
     * <pre>
     * set invoice number
     * </pre>
     * @param invBean SvcInvBean
     * @param invTmsg SVC_INVTMsg
     * @return boolean
     */
    private String getTaxCalcFlg(SvcInvBean invBean) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        DS_INV_TPTMsg dsInvTpTMsg = (DS_INV_TPTMsg) ZYPCodeDataUtil.findByCode(DS_INV_TP.class, invBean.getGlblCmpyCd(), invTmsg.dsInvTpCd.getValue());
        if (dsInvTpTMsg == null) {
            return ZYPConstant.FLG_OFF_N;
        }
        return dsInvTpTMsg.taxCalcFlg.getValue();
    }
    // Add End   09/01/2016 <QC#13529>

    /**
     * <pre>
     * set print status code
     * </pre>
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean setPrintSts(SvcInvBean invBean, SVC_INVTMsg invTmsg) {

        // Mod Start 09/28/2015
//        if (BigDecimal.ZERO.equals(invTmsg.invTotDealNetAmt.getValue())) {
//            invTmsg.invPrintStsCd.setValue(STS_CD_ZERO);
//            invTmsg.invMlSendStsCd.setValue(STS_CD_ZERO);
//            invTmsg.invEdiSendStsCd.setValue(STS_CD_ZERO);
//            invTmsg.invFaxSendStsCd.setValue(STS_CD_ZERO);
//            invTmsg.invEmlSendStsCd.setValue(STS_CD_ZERO);
//            return true;
//        }
//
//        // defalut setting
//        invTmsg.invPrintStsCd.setValue(STS_CD_ONE);
//
//        // set print status
//        Map<String, String> prmMap = new HashMap<String, String>();
//        prmMap.put("glblCmpyCd", invTmsg.glblCmpyCd.getValue());
//        if (invBean.isFlPlnFlg()) {
//            prmMap.put("custCd", invTmsg.billToCustCd.getValue());
//        } else {
//            prmMap.put("custCd", invTmsg.sellToCustCd.getValue());
//        }
//        prmMap.put("billTpCd", invTmsg.invTpCd.getValue());
//
//        List<CUST_INV_REQTMsg> custInvReqList = (List<CUST_INV_REQTMsg>) ssmBatchClient.queryObjectList("getCustInvReq", prmMap);
//
//        if (custInvReqList == null || custInvReqList.size() == 0) {
//            invBean.addXxMsgId(NSZM0104E);
//            return false;
//        }
//
//        CUST_INV_REQTMsg custInvReqTmsg = custInvReqList.get(0);
//
//        String sendInvToItrlPtyFlg = custInvReqTmsg.sendInvToItrlPtyFlg.getValue();
//        String sendInvViaPostFlg = custInvReqTmsg.sendInvViaPostFlg.getValue();
//        String sendInvViaEdiFlg = custInvReqTmsg.sendInvViaEdiFlg.getValue();
//        String sendInvViaFaxFlg = custInvReqTmsg.sendInvViaFaxFlg.getValue();
//        String sendInvViaEmlFlg = custInvReqTmsg.sendInvViaEmlFlg.getValue();
//        String inclNonEdiOrdFlg = custInvReqTmsg.inclNonEdiOrdFlg.getValue();
//
//        if (ZYPConstant.FLG_OFF_N.equals(sendInvToItrlPtyFlg) && ZYPConstant.FLG_OFF_N.equals(sendInvViaPostFlg)) {
//            invTmsg.invPrintStsCd.setValue(STS_CD_ZERO);
//        }
//
//        invTmsg.invMlSendStsCd.setValue(flgToStsCd(sendInvViaPostFlg));
//
//        if (ZYPConstant.FLG_ON_Y.equals(sendInvViaEdiFlg)) {
//
//            if (ZYPConstant.FLG_ON_Y.equals(inclNonEdiOrdFlg)) {
//                invTmsg.invEdiSendStsCd.setValue(flgToStsCd(sendInvViaEdiFlg));
//
//            } else {
//                invTmsg.invEdiSendStsCd.setValue(STS_CD_ZERO);
//            }
//        } else {
//            invTmsg.invEdiSendStsCd.setValue(STS_CD_ZERO);
//        }
//
//        if (ZYPConstant.FLG_ON_Y.equals(sendInvViaFaxFlg) && invBean.isPmtTermSendFaxFlg()) {
//            invTmsg.invFaxSendStsCd.setValue(STS_CD_ONE);
//        } else {
//            invTmsg.invFaxSendStsCd.setValue(STS_CD_ZERO);
//        }
//
//        invTmsg.invEmlSendStsCd.setValue(flgToStsCd(sendInvViaEmlFlg));

        invTmsg.invPrintStsCd.setValue(STS_CD_ONE);
        invTmsg.invMlSendStsCd.setValue(STS_CD_TWO);
        invTmsg.invEdiSendStsCd.setValue(STS_CD_TWO);
        invTmsg.invFaxSendStsCd.setValue(STS_CD_TWO);
        invTmsg.invEmlSendStsCd.setValue(STS_CD_TWO);
        // Mod End 09/28/2015
        return true;
    }

    // Del Start 09/28/2015
//    private String flgToStsCd(String flg) {
//
//        String stsCd = "";
//
//        if (ZYPConstant.FLG_ON_Y.equals(flg)) {
//            stsCd = STS_CD_ONE;
//        }
//
//        if (ZYPConstant.FLG_OFF_N.equals(flg)) {
//            stsCd = STS_CD_ZERO;
//        }
//
//        return stsCd;
//    }
    // Del End 09/28/2015

    /**
     * <pre>
     * set Invoice Line Data
     * </pre>
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean setInvLineTmsg(SvcInvBean invBean) {

        List<SvcInvLineBean> invLineBeanList = invBean.getInvLineBeanList();
        String svcInvNum = invBean.getSvcInvTMsg().svcInvNum.getValue();

        String coaCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_COA_CMPY_CD, invBean.getGlblCmpyCd());
        // Del Start 09/28/2015
        //String coaExtnCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_COA_EXTN_CD, invBean.getGlblCmpyCd());
        //String coaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_SVC_COA_ACCT_CD, invBean.getGlblCmpyCd());
        // Del Start 09/28/2015

        // Add Start 09/28/2015
        Map<String, String> productMap = getProduct(invBean);
        if (productMap == null) {
            return false;
        }
        String coaProdCd = (String) productMap.get("COA_PROD_CD");

        SVC_MACH_MSTRTMsg svcMachMstrTmsg = getSvcMachMstr(invBean);
        if (svcMachMstrTmsg == null) {
            return false;
        }
        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        // Add End 09/28/2015

        // Add Start 09/01/2016 <QC#13529>
        String taxCalcFlg = getTaxCalcFlg(invBean);
        // Add End   09/01/2016 <QC#13529>

        for (int i = 0; i < invLineBeanList.size(); i++) {
            SvcInvLineBean invLineBean = invLineBeanList.get(i);

            SVC_INV_LINETMsg invLineTmsg = invLineBean.getSvcInvLineTMsg();

            // get mdse infomation
            MDSETMsg mdseTmsg = getMdse(invBean, i);
            if (mdseTmsg != null) {
                if (!ZYPCommonFunc.hasValue(invLineTmsg.mdseNm)) {
                    invLineTmsg.mdseNm.setValue(mdseTmsg.mdseNm.getValue());
                }
                invLineTmsg.coaProdCd.setValue(mdseTmsg.coaProdCd.getValue());
            } else {
                invBean.addXxMsgId(NSZM0105E);
                return false;
            }

            // set inv line amount
            if (!setInvLineAmt(invBean, invLineTmsg)) {
                return false;
            }

            // Mod Start 09/28/2015
            // set service invoice line number
            BigDecimal svcInvLinePk = getSvcInvLinePk(invBean);
            if (svcInvLinePk == null) {
                return false;
            }
            invLineTmsg.svcInvLinePk.setValue(svcInvLinePk);
            // Mod Start 09/28/2015

            // tax calculation
            if (!ZYPCommonFunc.hasValue(invLineTmsg.slsTaxRate) || !ZYPCommonFunc.hasValue(invLineTmsg.invLineDealTaxAmt) || !ZYPCommonFunc.hasValue(invLineTmsg.invLineFuncTaxAmt)) {
                // Mod Start 09/01/2016 <QC#13529>
//                if (!setTaxAmt(invBean, invLineTmsg)) {
                if (!setTaxAmt(invBean, invLineTmsg, taxCalcFlg)) {
                // Mod End   09/01/2016 <QC#13529>
                    return false;
                }
            }
            // get coa cd
            //Del Start 2016/04/25 CSA Defect#7450
            //S21_ORGTMsg s21OrgTmsg = getS21Org(invBean, invLineTmsg.slsRepTocCd.getValue());
            //if (s21OrgTmsg == null) {
            //    return false;
            //}
            //invLineTmsg.coaBrCd.setValue(s21OrgTmsg.coaBrCd.getValue());
            //invLineTmsg.coaCcCd.setValue(s21OrgTmsg.coaCcCd.getValue());
            //invLineTmsg.coaChCd.setValue(s21OrgTmsg.coaChCd.getValue());
            //Del End 2016/04/25 CSA Defect#7450

            // set coa affl cd
            invLineTmsg.coaAfflCd.setValue(invBean.getCoaAfflCd());
            // set coa cmpy cd
            invLineTmsg.coaCmpyCd.setValue(coaCmpyCd);

            // Mod Start 09/28/2015
            // set coa extn cd
            //invLineTmsg.coaExtnCd.setValue(coaExtnCd);
            // ISSUE#48
            // set coa account code
            //invLineTmsg.coaAcctCd.setValue(coaAcctCd);
            // set coa prod cd
            //if (TRX.SALES.equals(invLineTmsg.trxCd.getValue()) && COA_PROD.ADMINISTRATION.equals(invLineTmsg.coaProdCd.getValue())) {
            //
            //    COA_PRODTMsg coaProdTmsg = getCoaProd(invBean, invLineTmsg.slsRepTocCd.getValue());
            //    if (coaProdTmsg == null) {
            //        return false;
            //   }
            //    invLineTmsg.coaProdCd.setValue(coaProdTmsg.coaProdCd.getValue());
            //}

            // set coa extn cd
            TOCTMsg tocTmsg = getToc(invBean, invLineTmsg.slsRepTocCd.getValue());
            if (tocTmsg == null) {
                return false;
            }
            //mod Start 2016/04/25 CSA Defect#7450
            ZYPEZDItemValueSetter.setValue(invLineTmsg.coaBrCd, tocTmsg.coaBrCd);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.coaCcCd, tocTmsg.coaCcCd);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.coaChCd, invBean.getCoaChCd());
            //mod end 2016/04/25 CSA Defect#7450
            ZYPEZDItemValueSetter.setValue(invLineTmsg.coaExtnCd, tocTmsg.coaExtnCd);

            // set coa account code
            ZYPEZDItemValueSetter.setValue(invLineTmsg.coaAcctCd, mdseTmsg.revCoaAcctCd);

            // set coa prod cd
            ZYPEZDItemValueSetter.setValue(invLineTmsg.coaProdCd, coaProdCd);
            // Mod Start 09/28/2015

            // Mod Start 09/28/2015
            //// get mdl infomation
            //if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue()) && !ZYPCommonFunc.hasValue(invLineTmsg.mdlNm)) {
            //    MDL_NMTMsg mdlNmTmsg = getMdl(invBean, i);
            //    if (mdlNmTmsg != null) {
            //        invLineTmsg.mdlId.setValue(mdlNmTmsg.t_MdlId.getValue());
            //        invLineTmsg.mdlNm.setValue(mdlNmTmsg.t_MdlNm.getValue());
            //    } else {
            //        // do nothing (maybe a parts does not belong mdl)
            //    }
            //}
            invLineTmsg.mdlId.clear();
            invLineTmsg.mdlNm.clear();
            // Mod End 09/28/2015

            // Add Start 09/28/2015
            // set Transaction Code, Transaction Reason Code
            SVC_INV_CHRG_TPTMsg svcInvChrgTpTmsg = getSvcInvChrgTp(invBean, invLineTmsg.svcInvChrgTpCd.getValue());
            if (svcInvChrgTpTmsg == null) {
                return false;
            }
            ZYPEZDItemValueSetter.setValue(invLineTmsg.trxCd, svcInvChrgTpTmsg.trxCd);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.trxRsnCd, svcInvChrgTpTmsg.trxRsnCd);

            ZYPEZDItemValueSetter.setValue(invLineTmsg.svcMachMstrPk, invTmsg.svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.serNum, invTmsg.serNum);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.custIssPoNum, invBean.getCustPoNum());
            ZYPEZDItemValueSetter.setValue(invLineTmsg.custIssPoDt, invBean.getCustPoDt());
            ZYPEZDItemValueSetter.setValue(invLineTmsg.firstBllgAttrbValTxt, svcMachMstrTmsg.ctrlFldTxt_01);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.scdBllgAttrbValTxt, svcMachMstrTmsg.ctrlFldTxt_02);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.thirdBllgAttrbValTxt, svcMachMstrTmsg.ctrlFldTxt_03);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.frthBllgAttrbValTxt, svcMachMstrTmsg.ctrlFldTxt_04);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.fifthBllgAttrbValTxt, svcMachMstrTmsg.ctrlFldTxt_05);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.sixthBllgAttrbValTxt, svcMachMstrTmsg.ctrlFldTxt_06);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.svcInvLineTpCd, SVC_INV_LINE_TP.OTHER);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.copyInclQty, BigDecimal.ZERO);

            // START 2019/04/24 [QC#31272,MOD]
            // set Invoice Display Qty, UOM Code
            // if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue()) || SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
            if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
                // mod start 2016/05/19 CSA Defect#8457
                BigDecimal invDplyQty = BigDecimal.ONE;
                if (ZYPCommonFunc.hasValue(invLineTmsg.svcInvMnAot)) {
                    invDplyQty = invLineTmsg.svcInvMnAot.getValue().divide(new BigDecimal("15"), BigDecimal.ROUND_UP).multiply(new BigDecimal("15"));
                    invDplyQty = invDplyQty.divide(new BigDecimal("60"), 2, BigDecimal.ROUND_HALF_UP);
                }
                // mod end 2016/05/19 CSA Defect#8457
                BigDecimal svcInvUnitHrsAot = invLineTmsg.svcInvUnitHrsAot.getValue();
                if (ZYPCommonFunc.hasValue(svcInvUnitHrsAot) && svcInvUnitHrsAot.compareTo(invDplyQty) > 0) {
                    invDplyQty = svcInvUnitHrsAot;
                }
                ZYPEZDItemValueSetter.setValue(invLineTmsg.invDispQty, invDplyQty);
                ZYPEZDItemValueSetter.setValue(invLineTmsg.uomCd, PKG_UOM.HOUR);
            } else if (SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
                BigDecimal invDplyQty = BigDecimal.ONE;
                if (ZYPCommonFunc.hasValue(invLineTmsg.svcInvMnAot)) {
                    invDplyQty = invLineTmsg.svcInvMnAot.getValue().divide(new BigDecimal("60"), 2, BigDecimal.ROUND_HALF_UP);
                }

                BigDecimal svcTrvlUnitHrsAot = null;
                Map<String, Object> svcTaskInfoMap = getSvcTaskInfo(invBean, invLineTmsg.svcTaskNum.getValue());
                if (svcTaskInfoMap != null) {
                    String svcZnCd = (String) svcTaskInfoMap.get("SVC_ZN_CD");
                    String mdlGrpNm = (String) svcTaskInfoMap.get("MDL_GRP_NM");
                    svcTrvlUnitHrsAot = getSvcTrvlUnitHrsAot(invBean, svcZnCd, svcMachMstrTmsg.svcByLineBizTpCd.getValue(), mdlGrpNm);
                } else {
                    svcTrvlUnitHrsAot = getSvcTrvlUnitHrsAot(invBean, null, svcMachMstrTmsg.svcByLineBizTpCd.getValue(), null);
                }
                if (ZYPCommonFunc.hasValue(svcTrvlUnitHrsAot)) {
                    invDplyQty = svcTrvlUnitHrsAot;
                }
                ZYPEZDItemValueSetter.setValue(invLineTmsg.invDispQty, invDplyQty);
                ZYPEZDItemValueSetter.setValue(invLineTmsg.uomCd, PKG_UOM.HOUR);
            // END 2019/04/24 [QC#31272,MOD]
            } else {
                ZYPEZDItemValueSetter.setValue(invLineTmsg.invDispQty, invLineTmsg.svcInvQty);
            }

            // set Invoice Display Unit Price Amount
            if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(invLineTmsg.invDispUnitPrcAmt, invLineBeanList.get(i).getSvcLborUnitAmt());
            } else if (SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(invLineTmsg.invDispUnitPrcAmt, invLineBeanList.get(i).getSvcTrvlUnitAmt());
            } else {
                ZYPEZDItemValueSetter.setValue(invLineTmsg.invDispUnitPrcAmt, invLineTmsg.dealUnitPrcAmt);
            }
            ZYPEZDItemValueSetter.setValue(invLineTmsg.basePrcAdjDealAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(invLineTmsg.basePrcAdjFuncAmt, BigDecimal.ZERO);
            // Add End 09/28/2015

            // set service invoice number
            invLineTmsg.svcInvNum.setValue(svcInvNum);

            // get svc Line
            invLineTmsg.svcInvLineNum.setValue(getInvLineNum(i));

            // TODO
            invLineTmsg.svcInvEligFlg.setValue(ZYPConstant.FLG_OFF_N);
            // START 2017/09/12 T.Kanasaka [QC#15134,ADD]
            invLineTmsg.mtrRollOverFlg.setValue(ZYPConstant.FLG_OFF_N);
            invLineTmsg.mtrExchFlg.setValue(ZYPConstant.FLG_OFF_N);
            // END 2017/09/12 T.Kanasaka [QC#15134,ADD]

            invLineBean.setSvcInvLineTMsg(invLineTmsg);
        }
        return true;
    }

    /**
     * <pre>
     * set Invoice Line Alloc Data
     * </pre>
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean setInvLineAllocTmsg(SvcInvBean invBean) {

        List<SvcInvLineBean> invLineBeanList = invBean.getInvLineBeanList();
        List<SVC_INV_LINE_ALLOCTMsg> invLineAllocList = invBean.getInvLineAllocList();

        // BigDecimal dealGrsTotPrcAmt = BigDecimal.ZERO;
        // BigDecimal funcGrsTotPrcAmt = BigDecimal.ZERO;
        int scale = invBean.getAftDeclPntDigitNum();

        for (int i = 0; i < invLineBeanList.size(); i++) {
            SvcInvLineBean invLineBean = invLineBeanList.get(i);
            SVC_INV_LINETMsg invLineTmsg = invLineBean.getSvcInvLineTMsg();
            SVC_INV_LINE_ALLOCTMsg invLineAllocTmsg = new SVC_INV_LINE_ALLOCTMsg();

            BigDecimal invLineAllocPk = getSvcInvLineAllocPk(invBean);

            EZDMsg.copy(invLineTmsg, null, invLineAllocTmsg, null);

            invLineAllocTmsg.svcInvLineAllocPk.setValue(invLineAllocPk);
            invLineAllocTmsg.svcInvLineAllocNum.setValue(invLineAllocTmsg.svcInvLineNum.getValue());
            // QC1943 Add Start
            invLineTmsg.svcInvLineNum.setValue(ZYPCommonFunc.leftPad(invLineTmsg.svcInvLineNum.getValue(), SVC_INV_LINE_NUM_PAD, "0"));
            invLineAllocTmsg.svcInvLineNum.setValue(invLineTmsg.svcInvLineNum.getValue());
            // QC1943 Add End

            // Mod Start 09/28/2015
            String contrPrcAllocTpCd = null;
            if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
                contrPrcAllocTpCd = CONTR_PRC_ALLOC_TP.LABOR;
            } else if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
                contrPrcAllocTpCd = CONTR_PRC_ALLOC_TP.PARTS;
            } else if (SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
                contrPrcAllocTpCd = CONTR_PRC_ALLOC_TP.TRAVEL;
            } else {
                contrPrcAllocTpCd = CONTR_PRC_ALLOC_TP.EXPENSE;
            }
            // Mod End 09/28/2015
            invLineAllocTmsg.contrPrcAllocTpCd.setValue(contrPrcAllocTpCd);

            if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {

                String intgMdseCd = NSXC002001SvcIntgMdse.getPrtsIntgMdse(invBean.getGlblCmpyCd(), invLineTmsg.mdseCd.getValue());

                if (ZYPCommonFunc.hasValue(intgMdseCd)) {
                    invLineAllocTmsg.intgMdseCd.setValue(intgMdseCd);
                } else {
                    invBean.addXxMsgId(NSZM0377E);
                    return false;
                }
            } else {
                invLineAllocTmsg.intgMdseCd.setValue(invLineTmsg.mdseCd.getValue());
            }

            invLineAllocTmsg.tocCd.setValue(invLineTmsg.slsRepTocCd.getValue());

            // START 2016/06/16 T.Aoyagi [QC#9659, ADD]
            invLineAllocTmsg.slsAllocRate.setValue(SLS_ALLOC_RATE_100);
            // END 2016/06/16 T.Aoyagi [QC#9659, ADD]

            // QC#2687 modify
            // dealGrsTotPrcAmt =
            // invLineTmsg.dealUnitPrcAmt.getValue().subtract(invLineTmsg.dealDiscUnitPrcAmt.getValue());
            // if (!checkDigit(dealGrsTotPrcAmt)) {
            // invBean.addXxMsgId(NSZM0382E);
            // return false;
            // }
            // invLineAllocTmsg.dealGrsUnitPrcAmt.setValue(dealGrsTotPrcAmt);
            // invLineAllocTmsg.dealNetUnitPrcAmt.setValue(invLineTmsg.dealUnitPrcAmt.getValue());
            invLineAllocTmsg.dealGrsUnitPrcAmt.setValue(invLineTmsg.dealUnitPrcAmt.getValue());
            invLineAllocTmsg.dealNetUnitPrcAmt.setValue(invLineTmsg.invLineDealNetAmt.getValue().divide(invLineTmsg.svcInvQty.getValue(), scale, RoundingMode.HALF_UP));
            invLineAllocTmsg.dealGrsTotPrcAmt.setValue(invLineTmsg.invLineDealSlsAmt.getValue());
            // QC#2687 modify
            // funcGrsTotPrcAmt =
            // invLineTmsg.funcUnitPrcAmt.getValue().subtract(invLineTmsg.funcDiscUnitPrcAmt.getValue());
            // if (!checkDigit(funcGrsTotPrcAmt)) {
            // invBean.addXxMsgId(NSZM0383E);
            // return false;
            // }
            // invLineAllocTmsg.funcGrsUnitPrcAmt.setValue(funcGrsTotPrcAmt);
            // invLineAllocTmsg.funcNetUnitPrcAmt.setValue(invLineTmsg.funcUnitPrcAmt.getValue());
            invLineAllocTmsg.funcGrsUnitPrcAmt.setValue(invLineTmsg.funcUnitPrcAmt.getValue());
            invLineAllocTmsg.funcNetUnitPrcAmt.setValue(invLineTmsg.invLineFuncNetAmt.getValue().divide(invLineTmsg.svcInvQty.getValue(), scale, RoundingMode.HALF_UP));
            invLineAllocTmsg.funcGrsTotPrcAmt.setValue(invLineTmsg.invLineFuncSlsAmt.getValue());
            invLineAllocTmsg.ccyCd.setValue(invBean.getSvcInvTMsg().dealCcyCd.getValue());
            // Add Start 09/28/2015
            invLineAllocTmsg.invLineSplTpCd.setValue(INV_LINE_SPL_TP.SERVICE);
            invLineAllocTmsg.invLineSplRate.setValue(DEFAULT_RATE);
            invLineAllocTmsg.slsRepTocAllocRate.setValue(DEFAULT_RATE);
            // Add End 09/28/2015

            //Add Start 06/28/2016 <QC#10585>
            ZYPEZDItemValueSetter.setValue(invLineAllocTmsg.coaProjCd, getCoaProjCd(invBean.getGlblCmpyCd(), invLineTmsg.mdseCd.getValue()));
            //Add End   06/28/2016 <QC#10585>

            invLineAllocList.add(invLineAllocTmsg);
        }
        return true;
    }

    //Add Start 06/28/2016 <QC#10585>
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
    //Add End   06/28/2016 <QC#10585>

    /**
     * <pre>
     * create correct invoice Line
     * </pre>
     * @param invBean SvcInvBean
     * @param invLineTmsg SVC_INV_LINETMsg
     * @param svcInvNum String
     * @return SVC_INV_LINETMsg
     */
    private SVC_INV_LINETMsg setCrctInvLineTmsg(SvcInvBean invBean, SVC_INV_LINETMsg invLineTmsg, String svcInvNum) {

        // create credit invoice data
        SVC_INV_LINETMsg crctInvLineTmsg = new SVC_INV_LINETMsg();
        EZDMsg.copy(invLineTmsg, null, crctInvLineTmsg, null);

        BigDecimal svcInvLinePk = getSvcInvLinePk(invBean);
        if (svcInvLinePk == null) {
            return null;
        }

        crctInvLineTmsg.svcInvLinePk.setValue(svcInvLinePk);
        crctInvLineTmsg.svcInvNum.setValue(svcInvNum);

        crctInvLineTmsg.svcInvQty.setValue(crctInvLineTmsg.svcInvQty.getValue().negate());

        crctInvLineTmsg.invLineDealNetAmt.setValue(crctInvLineTmsg.invLineDealNetAmt.getValue().negate());
        crctInvLineTmsg.invLineDealSlsAmt.setValue(crctInvLineTmsg.invLineDealSlsAmt.getValue().negate());
        crctInvLineTmsg.invLineDealTaxAmt.setValue(crctInvLineTmsg.invLineDealTaxAmt.getValue().negate());
        crctInvLineTmsg.invLineDealDiscAmt.setValue(crctInvLineTmsg.invLineDealDiscAmt.getValue().negate());
        crctInvLineTmsg.invLineFuncNetAmt.setValue(crctInvLineTmsg.invLineFuncNetAmt.getValue().negate());
        crctInvLineTmsg.invLineFuncSlsAmt.setValue(crctInvLineTmsg.invLineFuncSlsAmt.getValue().negate());
        crctInvLineTmsg.invLineFuncTaxAmt.setValue(crctInvLineTmsg.invLineFuncTaxAmt.getValue().negate());
        crctInvLineTmsg.invLineFuncDiscAmt.setValue(crctInvLineTmsg.invLineFuncDiscAmt.getValue().negate());

        return crctInvLineTmsg;
    }

    /**
     * <pre>
     * create correct invoice Line alloc
     * </pre>
     * @param invBean SvcInvBean
     * @param invLineAllocTmsg SVC_INV_LINE_ALLOCTMsg
     * @param svcInvNum String
     * @return SVC_INV_LINETMsg
     */
    private SVC_INV_LINE_ALLOCTMsg setCrctInvLineAllocTmsg(SvcInvBean invBean, SVC_INV_LINE_ALLOCTMsg invLineAllocTmsg, String svcInvNum, BigDecimal svcInvLinePk) {

        // create credit invoice data
        SVC_INV_LINE_ALLOCTMsg crctInvLineAllocTmsg = new SVC_INV_LINE_ALLOCTMsg();
        EZDMsg.copy(invLineAllocTmsg, null, crctInvLineAllocTmsg, null);

        BigDecimal svcInvLineAllocPk = getSvcInvLineAllocPk(invBean);
        if (svcInvLineAllocPk == null) {
            return null;
        }

        crctInvLineAllocTmsg.svcInvLineAllocPk.setValue(svcInvLineAllocPk);
        crctInvLineAllocTmsg.svcInvNum.setValue(svcInvNum);
        crctInvLineAllocTmsg.svcInvLinePk.setValue(svcInvLinePk);

        crctInvLineAllocTmsg.dealGrsTotPrcAmt.setValue(crctInvLineAllocTmsg.dealGrsTotPrcAmt.getValue().negate());
        crctInvLineAllocTmsg.invLineDealNetAmt.setValue(crctInvLineAllocTmsg.invLineDealNetAmt.getValue().negate());
        crctInvLineAllocTmsg.invLineDealTaxAmt.setValue(crctInvLineAllocTmsg.invLineDealTaxAmt.getValue().negate());
        crctInvLineAllocTmsg.funcGrsTotPrcAmt.setValue(crctInvLineAllocTmsg.funcGrsTotPrcAmt.getValue().negate());
        crctInvLineAllocTmsg.invLineFuncNetAmt.setValue(crctInvLineAllocTmsg.invLineFuncNetAmt.getValue().negate());
        crctInvLineAllocTmsg.invLineFuncTaxAmt.setValue(crctInvLineAllocTmsg.invLineFuncTaxAmt.getValue().negate());

        return crctInvLineAllocTmsg;
    }

    /**
     * <pre>
     * get exchange rate information
     * </pre>
     * @param invBean SvcInvBean
     * @return ACCT_DLY_ACTL_EXCH_RATESTMsg
     */
    private ACCT_DLY_ACTL_EXCH_RATESTMsg getExchRate(SvcInvBean invBean) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        ACCT_DLY_ACTL_EXCH_RATESTMsg exchRateTMsg = new ACCT_DLY_ACTL_EXCH_RATESTMsg();

        // exchRateHash = (Map<String, Object>)
        // S21ApplicationCacheHolder.get(KEY_EXCH_RATE);
        //
        // if (exchRateHash == null) {
        // exchRateHash = new HashMap<String, Object>();
        // S21ApplicationCacheHolder.put(KEY_EXCH_RATE, exchRateHash);
        // }

        if (exchRateHash.containsKey(invTmsg.dealCcyCd.getValue())) {
            exchRateTMsg = (ACCT_DLY_ACTL_EXCH_RATESTMsg) exchRateHash.get(invTmsg.dealCcyCd.getValue());
        } else {
            exchRateTMsg = (ACCT_DLY_ACTL_EXCH_RATESTMsg) ssmBatchClient.queryObject("getExchRate", invTmsg);
            exchRateHash.put(invTmsg.dealCcyCd.getValue(), exchRateTMsg);
        }

        if (exchRateTMsg == null) {
            invBean.addXxMsgId(NSZM0096E);
            return null;
        } else {
            if (exchRateTMsg.actlExchRate.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                invBean.addXxMsgId(NSZM0097E);
                return null;
            } else {
                return exchRateTMsg;
            }
        }
    }

    /**
     * <pre>
     * get payment term cash discount information
     * </pre>
     * @param invBean SvcInvBean
     * @return PMT_TERM_CASH_DISCTMsg
     */
    private PMT_TERM_CASH_DISCTMsg getPmtTermCashDisc(SvcInvBean invBean) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        // create credit invoice data
        PMT_TERM_CASH_DISCTMsg prmPmtTermCashDiscTmsg = new PMT_TERM_CASH_DISCTMsg();

        prmPmtTermCashDiscTmsg.glblCmpyCd.setValue(invTmsg.glblCmpyCd.getValue());
        prmPmtTermCashDiscTmsg.pmtTermCashDiscCd.setValue(invTmsg.pmtTermCashDiscCd.getValue());

        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTmsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(prmPmtTermCashDiscTmsg);

        if (pmtTermCashDiscTmsg != null) {
            return pmtTermCashDiscTmsg;
        } else {
            invBean.addXxMsgId(NSZM0093E);
            return null;
        }
    }

    /**
     * <pre>
     * get payment term cash discount information
     * </pre>
     * @param invBean SvcInvBean
     * @return PMT_TERMTMsg
     */
    private PMT_TERMTMsg getPmtTerm(SvcInvBean invBean) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        // create credit invoice data
        PMT_TERMTMsg prmPmtTermTmsg = new PMT_TERMTMsg();

        prmPmtTermTmsg.glblCmpyCd.setValue(invTmsg.glblCmpyCd.getValue());
        prmPmtTermTmsg.pmtTermCd.setValue(invTmsg.pmtTermCd.getValue());

        PMT_TERMTMsg pmtTermTmsg = (PMT_TERMTMsg) S21ApiTBLAccessor.findByKey(prmPmtTermTmsg);

        if (pmtTermTmsg != null) {
            return pmtTermTmsg;
        } else {
            invBean.addXxMsgId(NSZM0094E);
            return null;
        }
    }

    /**
     * <pre>
     * get sell to cust information
     * </pre>
     * @param invBean SvcInvBean
     * @return SELL_TO_CUSTTMsg
     */
    private SELL_TO_CUSTTMsg getSellToCust(SvcInvBean invBean) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        // create credit invoice data
        SELL_TO_CUSTTMsg prmSellToCustTmsg = new SELL_TO_CUSTTMsg();

        prmSellToCustTmsg.setSQLID("001");
        prmSellToCustTmsg.setConditionValue("glblCmpyCd01", invTmsg.glblCmpyCd.getValue());
        prmSellToCustTmsg.setConditionValue("sellToCustCd01", invTmsg.sellToCustCd.getValue());

        SELL_TO_CUSTTMsgArray sellToCustTmsgArray = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(prmSellToCustTmsg);

        // return inv
        if (sellToCustTmsgArray != null && sellToCustTmsgArray.length() > 0) {
            SELL_TO_CUSTTMsg sellToCustTmsg = (SELL_TO_CUSTTMsg) sellToCustTmsgArray.get(0);
            return sellToCustTmsg;
        } else {
            invBean.addXxMsgId(NSZM0190E);
            return null;
        }
    }

    /**
     * <pre>
     * get cnty information
     * </pre>
     * @param invBean SvcInvBean
     * @param cntyPk BigDecimal
     * @return CNTYTMsg
     */
    private CNTYTMsg getCnty(SvcInvBean invBean, BigDecimal cntyPk) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        // create credit invoice data
        CNTYTMsg prmCntyTmsg = new CNTYTMsg();

        prmCntyTmsg.glblCmpyCd.setValue(invTmsg.glblCmpyCd.getValue());
        prmCntyTmsg.cntyPk.setValue(cntyPk);

        return (CNTYTMsg) S21ApiTBLAccessor.findByKey(prmCntyTmsg);
    }

    /**
     * <pre>
     * get rem to information
     * </pre>
     * @param invBean SvcInvBean
     * @param remId String
     * @return REM_TOTMsg
     */
    private REM_TOTMsg getRemTo(SvcInvBean invBean, String remId) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        REM_TOTMsg prmRemToTmsg = new REM_TOTMsg();

        prmRemToTmsg.setSQLID("002");
        prmRemToTmsg.setConditionValue("glblCmpyCd01", invTmsg.glblCmpyCd.getValue());
        prmRemToTmsg.setConditionValue("remId01", remId);

        REM_TOTMsgArray remToTmsgArray = (REM_TOTMsgArray) S21ApiTBLAccessor.findByCondition(prmRemToTmsg);

        if (remToTmsgArray != null && remToTmsgArray.length() > 0) {
            return (REM_TOTMsg) remToTmsgArray.get(0);
        } else {
            invBean.addXxMsgId(NSZM0093E);
            return null;
        }
    }

    // Mod Start 09/28/2015
//    /**
//     * <pre>
//     * get rem to information
//     * </pre>
//     * @param invBean SvcInvBean
//     * @return BILL_TO_CUSTTMsg
//     */
//    private BILL_TO_CUSTTMsg getBillToCust(SvcInvBean invBean) {
//
//        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
//
//        BILL_TO_CUSTTMsg prmBillToCustTmsg = new BILL_TO_CUSTTMsg();
//
//        prmBillToCustTmsg.setSQLID("001");
//        prmBillToCustTmsg.setConditionValue("glblCmpyCd01", invTmsg.glblCmpyCd.getValue());
//        prmBillToCustTmsg.setConditionValue("billToCustCd01", invTmsg.billToCustCd.getValue());
//        prmBillToCustTmsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//
//        BILL_TO_CUSTTMsgArray billToCustTmsgArray = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(prmBillToCustTmsg);
//
//        if (billToCustTmsgArray != null && billToCustTmsgArray.length() > 0) {
//            return (BILL_TO_CUSTTMsg) billToCustTmsgArray.get(0);
//        }
//        invBean.addXxMsgId(NSZM0142E);
//        return null;
//    }
    /**
     * <pre>
     * get Bill To Cust information
     * </pre>
     * @param invBean SvcInvBean
     * @return Map<String, String>
     */
    private Map<String, String> getBillToCust(SvcInvBean invBean) {
        Map<String, String> prmMap = new HashMap<String, String>();
        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        prmMap.put("glblCmpyCd", invBean.getGlblCmpyCd());
        prmMap.put("billToCustCd", invTmsg.billToCustCd.getValue());
        Map<String, String> billToCustMap = (Map<String, String>) ssmBatchClient.queryObject("getBillToCust", prmMap);
        if (billToCustMap != null) {
            return billToCustMap;
        }
        invBean.addXxMsgId(NSZM0142E);
        return null;
    }
    // Mod End 09/28/2015

    /**
     * <pre>
     * get Original Invoice
     * </pre>
     * @param invBean SvcInvBean
     * @return SVC_INVTMsg
     */
    private SVC_INVTMsg getOrigInv(SvcInvBean invBean) {

        SVC_INVTMsg prmInvTmsg = new SVC_INVTMsg();
        prmInvTmsg.setSQLID("001");
        prmInvTmsg.setConditionValue("glblCmpyCd01", invBean.getGlblCmpyCd());
        prmInvTmsg.setConditionValue("svcInvNum01", invBean.getSvcInvTMsg().origSvcInvNum.getValue());

        SVC_INVTMsgArray invTmsg = (SVC_INVTMsgArray) S21ApiTBLAccessor.findByCondition(prmInvTmsg);

        if (invTmsg != null && invTmsg.length() > 0) {
            return invTmsg.no(0);
        }
        invBean.addXxMsgId(NSZM0136E);
        return null;
    }

    /**
     * <pre>
     * get Fsr Visit
     * </pre>
     * @param invBean SvcInvBean
     * @return FSR_VISITTMsg
     */
    private FSR_VISITTMsg getFsrVisit(SvcInvBean invBean) {

        FSR_VISITTMsg prmFsrVisitTmsg = new FSR_VISITTMsg();
        prmFsrVisitTmsg.glblCmpyCd.setValue(invBean.getGlblCmpyCd());
        prmFsrVisitTmsg.fsrNum.setValue(invBean.getSvcInvTMsg().fsrNum.getValue());
        prmFsrVisitTmsg.fsrVisitNum.setValue(invBean.getSvcInvTMsg().fsrVisitNum.getValue());

        FSR_VISITTMsg fsrVisitTmsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKey(prmFsrVisitTmsg);

        // return inv line array
        if (fsrVisitTmsg != null) {
            return fsrVisitTmsg;
        }
        invBean.addXxMsgId(NSZM0092E);
        return null;
    }

    /**
     * <pre>
     * get Original Invoice Line
     * </pre>
     * @param invBean SvcInvBean
     * @return SVC_INV_LINETMsgArray
     */
    private SVC_INV_LINETMsgArray getOrigInvLine(SvcInvBean invBean) {

        SVC_INV_LINETMsg prmInvLineTmsg = new SVC_INV_LINETMsg();
        prmInvLineTmsg.setSQLID("001");
        prmInvLineTmsg.setConditionValue("glblCmpyCd01", invBean.getGlblCmpyCd());
        prmInvLineTmsg.setConditionValue("svcInvNum01", invBean.getSvcInvTMsg().origSvcInvNum.getValue());

        SVC_INV_LINETMsgArray invLineTmsgArray = (SVC_INV_LINETMsgArray) S21ApiTBLAccessor.findByCondition(prmInvLineTmsg);

        // return inv line array
        if (invLineTmsgArray != null && invLineTmsgArray.length() > 0) {
            return invLineTmsgArray;
        }

        invBean.addXxMsgId(NSZM0137E);
        return null;
    }

    /**
     * <pre>
     * get Original Invoice Line Alloc
     * </pre>
     * @param invBean SvcInvBean
     * @return SVC_INV_LINE_ALLOCTMsgArray
     */
    private SVC_INV_LINE_ALLOCTMsgArray getOrigInvLineAlloc(SvcInvBean invBean) {

        SVC_INV_LINE_ALLOCTMsg prmInvLineAllocTmsg = new SVC_INV_LINE_ALLOCTMsg();
        prmInvLineAllocTmsg.setSQLID("002");
        prmInvLineAllocTmsg.setConditionValue("glblCmpyCd01", invBean.getGlblCmpyCd());
        prmInvLineAllocTmsg.setConditionValue("svcInvNum01", invBean.getSvcInvTMsg().origSvcInvNum.getValue());

        SVC_INV_LINE_ALLOCTMsgArray invLineAllocTmsgArray = (SVC_INV_LINE_ALLOCTMsgArray) S21ApiTBLAccessor.findByCondition(prmInvLineAllocTmsg);

        // return inv line alloc array
        if (invLineAllocTmsgArray != null && invLineAllocTmsgArray.length() > 0) {
            return invLineAllocTmsgArray;
        }

        invBean.addXxMsgId(NSZM0137E);
        return null;
    }

    /**
     * <pre>
     * get FSR
     * </pre>
     * @param invBean SvcInvBean
     * @return FSRTMsg
     */
    private FSRTMsg getFsr(SvcInvBean invBean) {

        FSRTMsg prmFsrTmsg = new FSRTMsg();
        prmFsrTmsg.glblCmpyCd.setValue(invBean.getGlblCmpyCd());
        prmFsrTmsg.fsrNum.setValue(invBean.getSvcInvTMsg().fsrNum.getValue());

        FSRTMsg fsrTmsg = (FSRTMsg) S21ApiTBLAccessor.findByKey(prmFsrTmsg);

        // return inv line array
        if (fsrTmsg != null) {
            return fsrTmsg;
        }
        invBean.addXxMsgId(NSZM0091E);
        return null;
    }

    // Del Start 09/28/2015
//    /**
//     * <pre>
//     * get coa info
//     * </pre>
//     * @param invBean SvcInvBean
//     * @param slsRepTocCd String
//     * @return COA_PRODTMsg
//     */
//    private COA_PRODTMsg getCoaProd(SvcInvBean invBean, String slsRepTocCd) {
//
//        Map<String, String> prmMap = new HashMap<String, String>();
//
//        prmMap.put("glblCmpyCd", invBean.getGlblCmpyCd());
//        prmMap.put("slsRepTocCd", slsRepTocCd);
//
//        COA_PRODTMsg coaProdTmsg = (COA_PRODTMsg) ssmBatchClient.queryObject("getCoaProd", prmMap);
//
//        if (coaProdTmsg != null) {
//            return coaProdTmsg;
//        }
//        invBean.addXxMsgId(NSZM0103E);
//        return null;
//    }
    // Del End 09/28/2015

//Del 2016/04/25 CSA Defect#7450
//    /**
//     * <pre>
//     * get Office Info
//     * </pre>
//     * @param invBean SvcInvBean
//     * @param billToCustCd BillToCustCd
//     * @return Map<String, String>
//     */
//    private Map<String, String> getOfcInfo(SvcInvBean invBean, String billToCustCd) {
//
//        Map<String, String> prmMap = new HashMap<String, String>();
//
//        prmMap.put("glblCmpyCd", invBean.getGlblCmpyCd());
//        prmMap.put("billToCustCd", billToCustCd);
//
//        Map<String, String> ofcMap = (Map<String, String>) ssmBatchClient.queryObject("getOfcAccptnce", prmMap);
//
//        if (ofcMap != null) {
//            return ofcMap;
//        }
//        invBean.addXxMsgId(NSZM0381E);
//        return null;
//    }

    // Mod Start 09/28/2015
//    /**
//     * <pre>
//     * get mdl info
//     * </pre>
//     * @param invBean SvcInvBean
//     * @param rowNum rownum of invLine
//     * @return MDL_NMTMsg
//     */
//    private MDL_NMTMsg getMdl(SvcInvBean invBean, int rowNum) {
//
//        Map<String, String> prmMap = new HashMap<String, String>();
//        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
//
//        prmMap.put(BIND_GLBL_CMPY_CD, invTmsg.glblCmpyCd.getValue());
//
//        if (rowNum != HEADER) {
//            String mdseCd = invBean.getInvLineBeanList().get(rowNum).getSvcInvLineTMsg().mdseCd.getValue();
//            prmMap.put(BIND_MDSE_CD, mdseCd);
//        } else {
//            prmMap.put(BIND_MDSE_CD, invTmsg.mdseCd.getValue());
//        }
//
//        List<MDL_NMTMsg> listRes = (List<MDL_NMTMsg>) ssmBatchClient.queryObjectList("getMdl", prmMap);
//
//        if (listRes != null && listRes.size() > 0) {
//            return listRes.get(0);
//        } else {
//            return null;
//        }
//    }
    /**
     * <pre>
     * get mdl info
     * </pre>
     * @param invBean SvcInvBean
     * @return MDL_NMTMsg
     */
    private MDL_NMTMsg getMdl(SvcInvBean invBean) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        prmMap.put("glblCmpyCd", invTmsg.glblCmpyCd.getValue());
        prmMap.put("svcMachMstrPk", invTmsg.svcMachMstrPk.getValue());
        return (MDL_NMTMsg) ssmBatchClient.queryObject("getMdl", prmMap);
    }
    // Mod End 09/28/2015
//Del Start 2016/04/25 CSA Defect#7450
//    /**
//     * <pre>
//     * get s21Org
//     * </pre>
//     * @param invBean SvcInvBean
//     * @param rowNum rownum of invLine
//     * @return MDL_NMTMsg
//     */
//    private S21_ORGTMsg getS21Org(SvcInvBean invBean, String slsRepTocCd) {
//
//        S21_ORGTMsg paramS21OrgTmsg = new S21_ORGTMsg();
//
//        paramS21OrgTmsg.setSQLID("003");
//        paramS21OrgTmsg.setConditionValue("glblCmpyCd01", invBean.getGlblCmpyCd());
//        paramS21OrgTmsg.setConditionValue("tocCd01", slsRepTocCd);
//        paramS21OrgTmsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
//
//        S21_ORGTMsgArray s21OrgTmsgArray = (S21_ORGTMsgArray) S21ApiTBLAccessor.findByCondition(paramS21OrgTmsg);
//
//        if (s21OrgTmsgArray != null && s21OrgTmsgArray.length() > 0) {
//            S21_ORGTMsg s21OrgTmsg = (S21_ORGTMsg) s21OrgTmsgArray.get(0);
//            return s21OrgTmsg;
//        }
//        invBean.addXxMsgId(NSZM0101E);
//        return null;
//    }
//Del End 2016/04/25 CSA Defect#7450

    /**
     * <pre>
     * get Ser Num
     * </pre>
     * @param glblCmpyCd glblCmpyCd
     * @param svcMachMstrPk svcMachMstrPk
     * @return serNum
     */
    private String getSerNum(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcMachMstrPk", svcMachMstrPk);

        return (String) ssmBatchClient.queryObject("getSerNum", prmMap);
    }

    /**
     * <pre>
     * get curency info
     * </pre>
     * @param invBean SvcInvBean
     * @return CCYTMsg
     */
    private CCYTMsg getCcy(SvcInvBean invBean) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        CCYTMsg prmCcyTmsg = new CCYTMsg();

        prmCcyTmsg.glblCmpyCd.setValue(invTmsg.glblCmpyCd.getValue());
        prmCcyTmsg.ccyCd.setValue(invTmsg.dealCcyCd.getValue());

        CCYTMsg ccyTmsg = (CCYTMsg) S21ApiTBLAccessor.findByKey(prmCcyTmsg);

        if (ccyTmsg != null) {
            return ccyTmsg;
        }
        invBean.addXxMsgId(NSZM0099E);
        return null;
    }

    /**
     * <pre>
     * get glbl cmpy info
     * </pre>
     * @param invBean SvcInvBean
     * @return GLBL_CMPYTMsg
     */
    private static GLBL_CMPYTMsg getGlblCmpy(SvcInvBean invBean) {

        GLBL_CMPYTMsg glblCmpyTmsg = null;

        if (glblCmpyHash.containsKey(invBean.getGlblCmpyCd())) {
            glblCmpyTmsg = (GLBL_CMPYTMsg) glblCmpyHash.get(invBean.getGlblCmpyCd());
        } else {
            GLBL_CMPYTMsg paramGlblCmpyTMsg = new GLBL_CMPYTMsg();
            ZYPEZDItemValueSetter.setValue(paramGlblCmpyTMsg.glblCmpyCd, invBean.getGlblCmpyCd());
            glblCmpyTmsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(paramGlblCmpyTMsg);
            glblCmpyHash.put(invBean.getGlblCmpyCd(), glblCmpyTmsg);
        }
        if (glblCmpyTmsg != null) {
            return glblCmpyTmsg;
        }
        invBean.addXxMsgId(NSZM0380E);
        return null;
    }

    /**
     * <pre>
     * get mdse info
     * </pre>
     * @param invBean SvcInvBean
     * @param rowNum rownum of invLine
     * @return MDSETMsg
     */
    private MDSETMsg getMdse(SvcInvBean invBean, int rowNum) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();

        MDSETMsg prmMdseTmsg = new MDSETMsg();

        prmMdseTmsg.glblCmpyCd.setValue(invTmsg.glblCmpyCd.getValue());

        if (rowNum != HEADER) {
            String mdseCd = invBean.getInvLineBeanList().get(rowNum).getSvcInvLineTMsg().mdseCd.getValue();
            prmMdseTmsg.mdseCd.setValue(mdseCd);
        } else {
            prmMdseTmsg.mdseCd.setValue(invTmsg.mdseCd.getValue());
        }

        MDSETMsg mdseTmsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(prmMdseTmsg);

        if (mdseTmsg != null) {
            return mdseTmsg;
        }
        invBean.addXxMsgId(NSZM0105E);
        return null;
    }

    // Add Start 09/28/2015
    /**
     * <pre>
     * get machine master information
     * </pre>
     * @param invBean SvcInvBean
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(SvcInvBean invBean) {
        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, invTmsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, invTmsg.svcMachMstrPk);
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (svcMachMstrTMsg != null) {
            return svcMachMstrTMsg;
        }
        invBean.addXxMsgId(NSZM0638E);
        return null;
    }

    // START 2017/08/16 K.Kim [QC#19406, DEL]
//    /**
//     * <pre>
//     * get contract information
//     * </pre>
//     * @param invBean SvcInvBean
//     * @return DS_CONTRTMsg
//     */
//    private DS_CONTRTMsg getDsContr(SvcInvBean invBean) {
//        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
//        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
//        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, invTmsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPk, invTmsg.dsContrPk);
//        DS_CONTRTMsg dsContrTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
//        if (dsContrTMsg != null) {
//            return dsContrTMsg;
//        }
//        invBean.addXxMsgId(NSZM0639E);
//        return null;
//    }
    // END 2017/08/16 K.Kim [QC#19406, DEL]

    /**
     * <pre>
     * get toc information
     * </pre>
     * @param invBean SvcInvBean
     * @param slsRepTocCd String
     * @return TOCTMsg
     */
    private TOCTMsg getToc(SvcInvBean invBean, String slsRepTocCd) {
        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        TOCTMsg inMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, invTmsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.tocCd, slsRepTocCd);
        TOCTMsg tocTMsg = (TOCTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (tocTMsg != null) {
            return tocTMsg;
        }
        invBean.addXxMsgId(NSZM0640E);
        return null;
    }

    /**
     * <pre>
     * get ship to cust information
     * </pre>
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return SHIP_TO_CUSTTMsg
     */
    private SHIP_TO_CUSTTMsg getShipToCust(SvcInvBean invBean, String shipToCustCd) {
        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        SHIP_TO_CUSTTMsg prmShipToCustTmsg = new SHIP_TO_CUSTTMsg();
        prmShipToCustTmsg.setSQLID("004");
        prmShipToCustTmsg.setConditionValue("glblCmpyCd01", invTmsg.glblCmpyCd.getValue());
        prmShipToCustTmsg.setConditionValue("shipToCustCd01", shipToCustCd);
        SHIP_TO_CUSTTMsgArray shipToCustTmsgArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(prmShipToCustTmsg);
        if (shipToCustTmsgArray != null && shipToCustTmsgArray.length() > 0) {
            SHIP_TO_CUSTTMsg shipToCustTmsg = (SHIP_TO_CUSTTMsg) shipToCustTmsgArray.get(0);
            return shipToCustTmsg;
        }
        invBean.addXxMsgId(NSZM0642E);
        return null;
    }

    /**
     * <pre>
     * get product information
     * </pre>
     * @param invBean SvcInvBean
     * @return Map<String, String>
     */
    private Map<String, String> getProduct(SvcInvBean invBean) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        prmMap.put("glblCmpyCd", invBean.getGlblCmpyCd());
        prmMap.put("svcMachMstrPk", invTmsg.svcMachMstrPk.getValue());
        Map<String, String> productMap = (Map<String, String>) ssmBatchClient.queryObject("getProduct", prmMap);
        if (productMap != null) {
            return productMap;
        }
        invBean.addXxMsgId(NSZM0105E);
        return null;
    }

    /**
     * <pre>
     * get svc inv chrg tp information
     * </pre>
     * @param invBean SvcInvBean
     * @param svcInvChrgTpCd String
     * @return SVC_INV_CHRG_TPTMsg
     */
    private SVC_INV_CHRG_TPTMsg getSvcInvChrgTp(SvcInvBean invBean, String svcInvChrgTpCd) {
        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        SVC_INV_CHRG_TPTMsg inMsg = new SVC_INV_CHRG_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, invTmsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcInvChrgTpCd, svcInvChrgTpCd);
        SVC_INV_CHRG_TPTMsg svcInvChrgTpTmsg = (SVC_INV_CHRG_TPTMsg) S21CodeTableAccessor.findByKey(inMsg);
        if (svcInvChrgTpTmsg != null) {
            return svcInvChrgTpTmsg;
        }
        invBean.addXxMsgId(NSZM0643E);
        return null;
    }
    // Add End 09/28/2015

    // START 2019/04/24 [QC#31272,ADD]
    /**
     * <pre>
     * get Service Task Info
     * </pre>
     * @param invBean SvcInvBean
     * @param svcTaskNum String
     * @return String
     */
    private Map<String, Object> getSvcTaskInfo(SvcInvBean invBean, String svcTaskNum) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", invBean.getGlblCmpyCd());
        prmMap.put("svcTaskNum", svcTaskNum);
        prmMap.put("fsrNum", invBean.getSvcInvTMsg().fsrNum.getValue());

        return (Map<String, Object>) ssmBatchClient.queryObject("getSvcTaskInfo", prmMap);
    }

    /**
     * <pre>
     * get Service Travel Unit Hours Aot
     * </pre>
     * @param invBean SvcInvBean
     * @param svcZnCd String
     * @param svcByLineBizTpCd String
     * @param mdlGrpNm String
     * @return BigDecimal
     */
    private BigDecimal getSvcTrvlUnitHrsAot(SvcInvBean invBean, String svcZnCd, String svcByLineBizTpCd, String mdlGrpNm) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", invBean.getGlblCmpyCd());
        prmMap.put("svcZnCd", svcZnCd);
        prmMap.put("svcByLineBizTpCd", svcByLineBizTpCd);
        prmMap.put("svcTrvlChrgTp", SVC_TRVL_CHRG_TP.FLAT_RATE);
        prmMap.put("mdlGrpNm", mdlGrpNm);
        prmMap.put("sysSrcCd", SYS_SRC.S21_SERVICE);

        BigDecimal svcTrvlUnitHrsAot = (BigDecimal) ssmBatchClient.queryObject("getSvcTrvlUnitHrsAot", prmMap);
        if (svcTrvlUnitHrsAot != null) {
            return svcTrvlUnitHrsAot;
        }
        return null;
    }
    // END 2019/04/24 [QC#31272,ADD]

    /**
     * <pre>
     * common input parameter check
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean checkInputHdrCommon(S21ApiMessageMap msgMap) {

        NSZC006001PMsg inPrmPMsg = (NSZC006001PMsg) msgMap.getPmsg();

        // glblCmpyCd
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
            return false;
        }
        // xx Mode Code
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.xxModeCd)) {
            msgMap.addXxMsgId(NSZM0122E);
            return false;
        }
        // mdse Cd
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.mdseCd)) {
            msgMap.addXxMsgId(NSZM0013E);
            return false;
        }
        // Org Inv Num
        if (CORRECT.equals(inPrmPMsg.xxModeCd.getValue()) && !ZYPCommonFunc.hasValue(inPrmPMsg.origSvcInvNum)) {
            msgMap.addXxMsgId(NSZM0123E);
            return false;
        }
        // FSR Num
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.fsrNum)) {
            msgMap.addXxMsgId(NSZM0124E);
            return false;
        }
        // FSR Visit Num
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.fsrVisitNum)) {
            msgMap.addXxMsgId(NSZM0125E);
            return false;
        }
        // Sell To Cust Cd
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.sellToCustCd)) {
            msgMap.addXxMsgId(NSZM0016E);
            return false;
        }
        // Mach Mstr Pk
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.svcMachMstrPk)) {
            msgMap.addXxMsgId(NSZM0074E);
            return false;
        }
        // Deal Ccy Cd
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.dealCcyCd)) {
            msgMap.addXxMsgId(NSZM0070E);
            return false;
        }
        // Pmt Term Cash Disc Cd
        if (!ZYPCommonFunc.hasValue(inPrmPMsg.pmtTermCashDiscCd)) {
            msgMap.addXxMsgId(NSZM0126E);
            return false;
        }
        // xx Inv Line List
        if (inPrmPMsg.xxInvLineList == null || inPrmPMsg.xxInvLineList.getValidCount() == 0) {
            msgMap.addXxMsgId(NSZM0174E);
            return false;
        }
        // xx Mode Cd Value
        if (!CREATE.equals(inPrmPMsg.xxModeCd.getValue()) && !CORRECT.equals(inPrmPMsg.xxModeCd.getValue())) {
            msgMap.addXxMsgId(NSZM0175E);
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * common input parameter check
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean checkInputLineCommon(S21ApiMessageMap msgMap) {

        NSZC006001PMsg inPrmPMsg = (NSZC006001PMsg) msgMap.getPmsg();

        for (int i = 0; i < inPrmPMsg.xxInvLineList.getValidCount(); i++) {

            NSZC006001_xxInvLineListPMsg invLinePMsg = inPrmPMsg.xxInvLineList.no(i);

            // svc chrg tp cd
            if (!ZYPCommonFunc.hasValue(invLinePMsg.svcInvChrgTpCd)) {
                msgMap.addXxMsgId(NSZM0176E);
                return false;
            }
            // mdse Cd
            if (!ZYPCommonFunc.hasValue(invLinePMsg.mdseCd)) {
                msgMap.addXxMsgId(NSZM0013E);
                return false;
            }
            // inv Qty
            if (!ZYPCommonFunc.hasValue(invLinePMsg.svcInvQty)) {
                msgMap.addXxMsgId(NSZM0128E);
                return false;
            }
            // DEL Start 04/19/2016 QC#7165
            // unit Hos Aot
            // if ((SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue()) || SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue())) && !ZYPCommonFunc.hasValue(invLinePMsg.svcInvUnitHrsAot)) {
            //     msgMap.addXxMsgId(NSZM0129E);
            //     return false;
            // }
            // DEL End 04/19/2016 QC#7165
            // Add Start 09/28/2015
            // uom cd
            if (!ZYPCommonFunc.hasValue(invLinePMsg.uomCd)) {
                msgMap.addXxMsgId(NSZM0644E);
                return false;
            }
            // Add End 09/28/2015
            // unit Deal Amt
            if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue()) && !ZYPCommonFunc.hasValue(invLinePMsg.dealUnitPrcAmt)) {
                msgMap.addXxMsgId(NSZM0130E);
                return false;
            }
            // parts charge && inv Deal Net Amt
            if (!SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue()) && !ZYPCommonFunc.hasValue(invLinePMsg.invLineDealSlsAmt)) {
                msgMap.addXxMsgId(NSZM0131E);
                return false;
            }
            // sls Rep Cd
            if (!ZYPCommonFunc.hasValue(invLinePMsg.slsRepTocCd)) {
                msgMap.addXxMsgId(NSZM0133E);
                return false;
            }
            // Del Start 09/28/2015
            // trx Cd
            //if (!ZYPCommonFunc.hasValue(invLinePMsg.trxCd)) {
            //    msgMap.addXxMsgId(NSZM0134E);
            //    return false;
            //}
            //// trx Rsn Cd
            //if (!ZYPCommonFunc.hasValue(invLinePMsg.trxRsnCd)) {
            //    msgMap.addXxMsgId(NSZM0135E);
            //    return false;
            //}
            // Del End 09/28/2015
            // tax rate or ship to cust cd
            if (!ZYPCommonFunc.hasValue(invLinePMsg.slsTaxRate) && !ZYPCommonFunc.hasValue(invLinePMsg.shipToCustCd)) {
                msgMap.addXxMsgId(NSZM0177E);
                return false;
            }
            // disc amt
            if (ZYPCommonFunc.hasValue(invLinePMsg.invLineDiscRate)) {
                if (SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue()) && !ZYPCommonFunc.hasValue(invLinePMsg.dealDiscUnitPrcAmt)) {
                    msgMap.addXxMsgId(NSZM0179E);
                    return false;
                } else if (!SVC_INV_CHRG_TP.PARTS_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue()) && !ZYPCommonFunc.hasValue(invLinePMsg.invLineDealDiscAmt)) {
                    msgMap.addXxMsgId(NSZM0178E);
                    return false;
                }
            }
            // Add Start 09/28/2015
            // svc lbor unit amt
            if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue()) && !ZYPCommonFunc.hasValue(invLinePMsg.svcLborUnitAmt)) {
                msgMap.addXxMsgId(NSZM0645E);
                return false;
            }
            // svc trvl unit amt
            if (SVC_INV_CHRG_TP.TRAVEL_CHARGE.equals(invLinePMsg.svcInvChrgTpCd.getValue()) && !ZYPCommonFunc.hasValue(invLinePMsg.svcTrvlUnitAmt)) {
                msgMap.addXxMsgId(NSZM0646E);
                return false;
            }
            // Add End 09/28/2015
        }
        return true;
    }

    /**
     * create Service Invoice
     * @param invBean SvcInvBean
     * @param invTmsg SVC_INVTMsg
     * @return boolean
     */
    private boolean createSvcInv(SvcInvBean invBean, SVC_INVTMsg invTmsg) {

        S21FastTBLAccessor.insert(invTmsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(invTmsg.getReturnCode())) {
            invBean.addXxMsgId(NSZM0138E);
            return false;
        }
        return true;
    }

    /**
     * getServiceInvoicePk
     * @param invBean SvcInvBean
     * @return svcInvPk
     */
    private BigDecimal getSvcInvPk(SvcInvBean invBean) {

        BigDecimal svcInvPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_INV_SQ);

        if (svcInvPk != null) {
            return svcInvPk;
        }
        invBean.addXxMsgId(NSZM0141E);
        return null;
    }

    /**
     * getServiceInvoiceLineAllocPk
     * @param invBean SvcInvBean
     * @return svcInvLineAllocPk
     */
    private BigDecimal getSvcInvLineAllocPk(SvcInvBean invBean) {

        BigDecimal svcInvPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_INV_LINE_ALLOC_SQ);

        if (svcInvPk != null) {
            return svcInvPk;
        }
        invBean.addXxMsgId(NSZM0378E);
        return null;
    }

    /**
     * create fsr evnet
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean createFsrEvent(SvcInvBean invBean) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        String fsrNum = invTmsg.fsrNum.getValue();
        String fsrVisitNum = invTmsg.fsrVisitNum.getValue();
        List<SvcInvLineBean> invLineBeanList = invBean.getInvLineBeanList();

        for (int i = 0; i < invLineBeanList.size(); i++) {
            SVC_INV_LINETMsg invLineTmsg = invLineBeanList.get(i).getSvcInvLineTMsg();
            if (SVC_INV_CHRG_TP.LABOR_CHARGE.equals(invLineTmsg.svcInvChrgTpCd.getValue())) {
                FSR_EVENTTMsg fsrEventTmsg = new FSR_EVENTTMsg();

                BigDecimal fsrEvnetPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.FSR_EVENT_SQ);
                fsrEventTmsg.glblCmpyCd.setValue(invLineTmsg.glblCmpyCd.getValue());
                fsrEventTmsg.fsrEventPk.setValue(fsrEvnetPk);
                fsrEventTmsg.fsrNum.setValue(fsrNum);
                fsrEventTmsg.fsrVisitNum.setValue(fsrVisitNum);
                fsrEventTmsg.svcTaskNum.setValue(invLineTmsg.svcTaskNum.getValue());
                fsrEventTmsg.fsrEventExeUsrId.setValue(invLineBeanList.get(i).getPsnCd());
                fsrEventTmsg.svcDisptEventCd.setValue(SVC_DISPT_EVENT.INVOICE_FSR);
                fsrEventTmsg.fsrEventExeTs.setValue(ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT_TIMESTAMP));
                // Add Start 09/28/2015
                fsrEventTmsg.mblIntfcProcCd.setValue(MBL_INTFC_PROC.NO_NEED);
                fsrEventTmsg.mblIntfcFlg.setValue(ZYPConstant.FLG_ON_Y);
                // Add End 09/28/2015

                if (!insertFsrEvent(invBean, fsrEventTmsg)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Add Start 09/28/2015
    /**
     * create svc inv line tax dtl
     * @param invBean SvcInvBean
     * @return boolean
     */
    // START 2016/06/16 T.Aoyagi [QC#9659, MOD]
    private boolean createSvcInvLineTaxDtl(SvcInvBean invBean, SVC_INV_LINETMsg invLineTmsg, NWZC036101_taxCalculateOutputLinePMsg taxApiLinePmsg) {
    // END 2016/06/16 T.Aoyagi [QC#9659, MOD]

        SVC_INV_LINE_TAX_DTLTMsg svcInvLineTaxDtlTmsg = new SVC_INV_LINE_TAX_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.glblCmpyCd, invLineTmsg.glblCmpyCd);
        BigDecimal svcInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_INV_LINE_TAX_DTL_SQ");
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.svcInvLineTaxDtlPk, svcInvLineTaxDtlPk);
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.svcInvLinePk, invLineTmsg.svcInvLinePk);
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.dsSlsTaxTpCd, DS_SLS_TAX_TP.STATE_TAX);
        // START 2016/06/16 T.Aoyagi [QC#9659, MOD]
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.slsTaxRate, nullToZero(taxApiLinePmsg.taxPct_01.getValue()));
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.dealTaxAmt, calcDealFromFunc(invBean, taxApiLinePmsg.taxAmt_01.getValue()));
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.funcTaxAmt, nullToZero(taxApiLinePmsg.taxAmt_01.getValue()));
        // END 2016/06/16 T.Aoyagi [QC#9659, MOD]
        // START 2017/08/07 M.Kidokoro [QC#20073, ADD]
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.taxAreaId, taxApiLinePmsg.taxAreaId.getValue());
        // END 2017/08/07 M.Kidokoro [QC#20073, ADD]
        if (!insertSvcInvLineTaxDtl(invBean, svcInvLineTaxDtlTmsg)) {
            return false;
        }

        svcInvLineTaxDtlTmsg = new SVC_INV_LINE_TAX_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.glblCmpyCd, invLineTmsg.glblCmpyCd);
        svcInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_INV_LINE_TAX_DTL_SQ");
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.svcInvLineTaxDtlPk, svcInvLineTaxDtlPk);
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.svcInvLinePk, invLineTmsg.svcInvLinePk);
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.dsSlsTaxTpCd, DS_SLS_TAX_TP.COUNTY_TAX);
        // START 2016/06/16 T.Aoyagi [QC#9659, MOD]
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.slsTaxRate, nullToZero(taxApiLinePmsg.taxPct_02.getValue()));
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.dealTaxAmt, calcDealFromFunc(invBean, taxApiLinePmsg.taxAmt_02.getValue()));
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.funcTaxAmt, nullToZero(taxApiLinePmsg.taxAmt_02.getValue()));
        // END 2016/06/16 T.Aoyagi [QC#9659, MOD]
        // START 2017/08/07 M.Kidokoro [QC#20073, ADD]
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.taxAreaId, taxApiLinePmsg.taxAreaId.getValue());
        // END 2017/08/07 M.Kidokoro [QC#20073, ADD]
        if (!insertSvcInvLineTaxDtl(invBean, svcInvLineTaxDtlTmsg)) {
            return false;
        }

        svcInvLineTaxDtlTmsg = new SVC_INV_LINE_TAX_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.glblCmpyCd, invLineTmsg.glblCmpyCd);
        svcInvLineTaxDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_INV_LINE_TAX_DTL_SQ");
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.svcInvLineTaxDtlPk, svcInvLineTaxDtlPk);
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.svcInvLinePk, invLineTmsg.svcInvLinePk);
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.dsSlsTaxTpCd, DS_SLS_TAX_TP.CITY_TAX);
        // START 2016/06/16 T.Aoyagi [QC#9659, MOD]
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.slsTaxRate, nullToZero(taxApiLinePmsg.taxPct_03.getValue()));
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.dealTaxAmt, calcDealFromFunc(invBean, taxApiLinePmsg.taxAmt_03.getValue()));
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.funcTaxAmt, nullToZero(taxApiLinePmsg.taxAmt_03.getValue()));
        // END 2016/06/16 T.Aoyagi [QC#9659, MOD]
        // START 2017/08/07 M.Kidokoro [QC#20073, ADD]
        ZYPEZDItemValueSetter.setValue(svcInvLineTaxDtlTmsg.taxAreaId, taxApiLinePmsg.taxAreaId.getValue());
        // END 2017/08/07 M.Kidokoro [QC#20073, ADD]
        if (!insertSvcInvLineTaxDtl(invBean, svcInvLineTaxDtlTmsg)) {
            return false;
        }
        return true;
    }
    // Add End 09/28/2015

    // Del Start 09/28/2015
//    /**
//     * set tax api params
//     * @param svcInvBean SvcInvBean
//     * @param mdseCd mdseCd
//     * @param shipQty shipQty
//     * @param funcPrcAmt funcPrcAmt
//     * @param lineNum lineNum
//     * @return pmsg
//     */
//    public static NWZC036001PMsg setTaxApiParams(SvcInvBean svcInvBean, String mdseCd, BigDecimal shipQty, BigDecimal funcPrcAmt, String lineNum) {
//
//        NWZC036001PMsg taxApiPMsg = new NWZC036001PMsg();
//        // heder
//        SVC_INVTMsg svcInvTmsg = svcInvBean.getSvcInvTMsg();
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.glblCmpyCd, svcInvBean.getGlblCmpyCd());
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.xxTaxCalcHdrNum, "1");
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.slsDt, ZYPDateUtil.getSalesDate(svcInvBean.getGlblCmpyCd()));
//        // QC#2324
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.invNum, svcInvBean.getSvcInvTMsg().svcInvNum.getValue());
//
//        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(svcInvBean);
//
//        if (glblCmpyTMsg == null) {
//            return null;
//        }
//
//        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
//        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, svcInvTmsg.svcMachMstrPk);
//
//        SVC_MACH_MSTRTMsg svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
//
//        if (svcMachMstrTMsg == null) {
//
//            svcInvBean.addXxMsgId(NSZM0084E);
//            return null;
//
//        } else {
//
//            ZYPEZDItemValueSetter.setValue(taxApiPMsg.billToCustCd, svcInvTmsg.billToCustCd);
//            ZYPEZDItemValueSetter.setValue(taxApiPMsg.sellToCustCd, svcInvTmsg.sellToCustCd);
//            ZYPEZDItemValueSetter.setValue(taxApiPMsg.shipToCtyAddr, svcMachMstrTMsg.ctyAddr);
//            ZYPEZDItemValueSetter.setValue(taxApiPMsg.shipToStCd, svcMachMstrTMsg.stCd);
//            ZYPEZDItemValueSetter.setValue(taxApiPMsg.shipToPostCd, svcMachMstrTMsg.postCd);
//
//            if (ZYPCommonFunc.hasValue(glblCmpyTMsg.ctryCd) && ZYPCommonFunc.hasValue(svcMachMstrTMsg.ctryCd) && glblCmpyTMsg.ctryCd.getValue().equals(svcMachMstrTMsg.ctryCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(taxApiPMsg.exptFlg, ZYPConstant.FLG_OFF_N);
//            } else {
//                ZYPEZDItemValueSetter.setValue(taxApiPMsg.exptFlg, ZYPConstant.FLG_ON_Y);
//            }
//
//        }
//
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.xxShipFuncFrtAmt, BigDecimal.ZERO);
//        // line
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.xxTaxCalculateInputLine.no(0).xxTaxCalcLineNum, lineNum);
//        // todo mdsecd
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.xxTaxCalculateInputLine.no(0).mdseCd, mdseCd);
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.xxTaxCalculateInputLine.no(0).shipQty, shipQty);
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.xxTaxCalculateInputLine.no(0).funcNetUnitPrcAmt, funcPrcAmt);
//        ZYPEZDItemValueSetter.setValue(taxApiPMsg.xxTaxCalculateInputLine.no(0).taxFlg, ZYPConstant.FLG_OFF_N);
//
//        taxApiPMsg.xxTaxCalculateInputLine.setValidCount(1);
//
//        return taxApiPMsg;
//    }
    // Del End 09/28/2015

    /**
     * getServiceInvoiceLinePk
     * @param invBean SvcInvBean
     * @return svcInvPk
     */
    private BigDecimal getSvcInvLinePk(SvcInvBean invBean) {

        BigDecimal svcInvLinePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_INV_LINE_SQ);

        if (svcInvLinePk != null) {
            return svcInvLinePk;
        }
        invBean.addXxMsgId(NSZM0143E);
        return null;
    }

    /**
     * set Return PMsg
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean setRtrnPmsg(SvcInvBean invBean) {

        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        // set return pmsg
        invBean.getRtlInvPMsg().svcInvNum.setValue(invTmsg.svcInvNum.getValue());
        if (!ZYPCommonFunc.hasValue(invBean.getRtlInvPMsg().invDt)) {
            invBean.getRtlInvPMsg().invDt.setValue(invTmsg.invDt.getValue());
        }
        return true;
    }

    /**
     * @param invBean SvcInvBean
     * @param invLineTmsg SVC_INV_LINETMsg
     * @return boolean
     */
    private boolean insertSvcInvLine(SvcInvBean invBean, SVC_INV_LINETMsg invLineTmsg) {
        insertSvcInvLineList.add(invLineTmsg);
        if (BULK_CNT == insertSvcInvLineList.size()) {
            return insertSvcInvLineFlash(invBean);
        }
        return true;
    }

    /**
     * @param invBean SvcInvBean
     * @param invLineAllocTmsg SVC_INV_LINE_ALLOCTMsg
     * @return boolean
     */
    private boolean insertSvcInvLineAlloc(SvcInvBean invBean, SVC_INV_LINE_ALLOCTMsg invLineAllocTmsg) {
        insertSvcInvLineAllocList.add(invLineAllocTmsg);
        if (BULK_CNT == insertSvcInvLineAllocList.size()) {
            return insertSvcInvLineAllocFlash(invBean);
        }
        return true;
    }

    /**
     * insertSvcInvLineFlash
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean insertSvcInvLineFlash(SvcInvBean invBean) {
        if (insertSvcInvLineList.size() == 0) {
            return true;
        }
        int lineNum = S21FastTBLAccessor.insert(insertSvcInvLineList.toArray(new SVC_INV_LINETMsg[0]));
        int dataNum = insertSvcInvLineList.size();
        if (lineNum != dataNum) {
            insertSvcInvLineList = new ArrayList<SVC_INV_LINETMsg>();
            invBean.addXxMsgId(NSZM0139E);
            return false;
        }
        insertSvcInvLineList = new ArrayList<SVC_INV_LINETMsg>();
        return true;
    }

    /**
     * insertSvcInvLineAllocFlash
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean insertSvcInvLineAllocFlash(SvcInvBean invBean) {
        if (insertSvcInvLineAllocList.size() == 0) {
            return true;
        }
        int lineNum = S21FastTBLAccessor.insert(insertSvcInvLineAllocList.toArray(new SVC_INV_LINE_ALLOCTMsg[0]));
        int dataNum = insertSvcInvLineAllocList.size();
        if (lineNum != dataNum) {
            insertSvcInvLineAllocList = new ArrayList<SVC_INV_LINE_ALLOCTMsg>();
            invBean.addXxMsgId(NSZM0379E);
            return false;
        }
        insertSvcInvLineAllocList = new ArrayList<SVC_INV_LINE_ALLOCTMsg>();
        return true;
    }

    /**
     * @param invBean SvcInvBean
     * @param fsrEventTmsg FSR_EVENTTMsg
     * @return boolean
     */
    private boolean insertFsrEvent(SvcInvBean invBean, FSR_EVENTTMsg fsrEventTmsg) {
        insertFsrEventList.add(fsrEventTmsg);
        if (BULK_CNT == insertFsrEventList.size()) {
            return insertFsrEventFlash(invBean);
        }
        return true;
    }

    /**
     * insertFsrEventFlash
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean insertFsrEventFlash(SvcInvBean invBean) {
        if (insertFsrEventList.size() == 0) {
            return true;
        }
        int lineNum = S21FastTBLAccessor.insert(insertFsrEventList.toArray(new FSR_EVENTTMsg[0]));
        int dataNum = insertFsrEventList.size();
        if (lineNum != dataNum) {
            insertFsrEventList = new ArrayList<FSR_EVENTTMsg>();
            invBean.addXxMsgId(NSZM0173E);
            return false;
        }
        insertFsrEventList = new ArrayList<FSR_EVENTTMsg>();
        return true;
    }

    // Add Start 09/28/2015
    /**
     * insertSvcInvLineTaxDtl
     * @param invBean SvcInvBean
     * @param svcInvLineTaxDtlTmsg SVC_INV_LINE_TAX_DTLTMsg
     * @return boolean
     */
    private boolean insertSvcInvLineTaxDtl(SvcInvBean invBean, SVC_INV_LINE_TAX_DTLTMsg svcInvLineTaxDtlTmsg) {
        insertSvcInvLineTaxDtlList.add(svcInvLineTaxDtlTmsg);
        if (BULK_CNT == insertSvcInvLineTaxDtlList.size()) {
            return insertSvcInvLineTaxDtlFlash(invBean);
        }
        return true;
    }

    /**
     * insertSvcInvLineTaxDtlFlash
     * @param invBean SvcInvBean
     * @return boolean
     */
    private boolean insertSvcInvLineTaxDtlFlash(SvcInvBean invBean) {
        if (insertSvcInvLineTaxDtlList.size() == 0) {
            return true;
        }
        int lineNum = S21FastTBLAccessor.insert(insertSvcInvLineTaxDtlList.toArray(new SVC_INV_LINE_TAX_DTLTMsg[0]));
        int dataNum = insertSvcInvLineTaxDtlList.size();
        if (lineNum != dataNum) {
            insertSvcInvLineTaxDtlList = new ArrayList<SVC_INV_LINE_TAX_DTLTMsg>();
            invBean.addXxMsgId(NSZM0651E);
            return false;
        }
        insertSvcInvLineTaxDtlList = new ArrayList<SVC_INV_LINE_TAX_DTLTMsg>();
        return true;
    }
    // Add End 09/28/2015

    /**
     * deal Amount -> Function Amount : calculate
     * @param amt
     * @param acctArthTpCd
     * @param actlExchRate
     * @param scale
     * @return
     */
    static BigDecimal calcFuncAmt(BigDecimal amt, String acctArthTpCd, BigDecimal actlExchRate, int scale) {

        if (amt == null) {
            return amt;
        }

        if (BigDecimal.ZERO.compareTo(actlExchRate) == 0) {
            return BigDecimal.ZERO;
        }

        if (MULTIPLICATION.equals(acctArthTpCd)) {
            return amt.multiply(actlExchRate).setScale(scale, RoundingMode.HALF_UP);
        } else {
            return amt.divide(actlExchRate, scale, RoundingMode.HALF_UP);
        }
    }

    /**
     * calc deal amt
     * @param funcAmt funcAmt
     * @param rt rate
     * @param acctArthTpCd acctArthTpCd
     * @param digit digit
     * @return dealAmt
     */
    public static BigDecimal calcDealAmt(BigDecimal funcAmt, BigDecimal rt, String acctArthTpCd, BigDecimal digit) {
        if (BigDecimal.ZERO.equals(funcAmt)) {
            return BigDecimal.ZERO;
        }
        if (MULTIPLICATION.equals(acctArthTpCd)) {
            return funcAmt.divide(rt, digit.intValue(), BigDecimal.ROUND_HALF_UP);
        } else {
            return funcAmt.multiply(rt).setScale(DEFAULT_SCL, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * get invoice Line Number
     * @param rownum rownum
     * @return inv Line Num
     */
    private String getInvLineNum(int rownum) {
        int lineNum = rownum + 1;
        return String.format(ZERO3_PAD, lineNum);
    }

    /**
     * checkDidit
     * @param num number
     * @return boolean
     */
    private boolean checkDigit(BigDecimal num) {
        if (BigDecimal.ZERO.compareTo(num) < 0) {
            if (MAX_DIGIT15.compareTo(num) <= 0) {
                return false;
            }
        } else {
            if (MIN_DIGIT15.compareTo(num) >= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * The message set to PMsg is set to the message map.<br>
     * @param msgMap message map
     * @param pMsg PMsg
     * @return true:Error message none false:There is an error
     * message.
     */
    private static boolean setMessage(SvcInvBean svcInvBean, EZDPMsg pMsg) {

        List<String> errList = S21ApiUtil.getXxMsgIdList(pMsg);

        if (!errList.isEmpty()) {
            for (String xxMsgId : errList) {
                svcInvBean.addXxMsgId(xxMsgId);
            }
            return false;
        }
        return true;
    }

    // Add Start 02/25/2016 QC#3991, 2016/07/21 CSA Defect#12154
    private String getCrCardCustRefNum(String glblCmpyCd, String fsrNum) {
        String crCardCustRefNum = null;
        CR_CARD_TRXTMsg inMsg = new CR_CARD_TRXTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("firstTrxInfoTxt01", fsrNum);
        inMsg.setConditionValue("crCardAuthStsCd01", CR_CARD_AUTH_STS.SAVED);
        inMsg.setConditionValue("crCardTrxTpCd01", CR_CARD_TRX_TP.SERVICE_REQUEST);
        CR_CARD_TRXTMsgArray outArray = (CR_CARD_TRXTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() > 0) {
            crCardCustRefNum = outArray.no(0).crCardCustRefNum.getValue();
        }
        return crCardCustRefNum;
     }
    // Add End 02/25/2016 QC#3991
    // START 2016/06/16 T.Aoyagi [QC#9659, ADD]
    private BigDecimal calcDealFromFunc(SvcInvBean invBean, BigDecimal funcAmt) {

        String glblCmpyCd = invBean.getGlblCmpyCd();
        String ccyCd = invBean.getSvcInvTMsg().dealCcyCd.getValue();
        String invDt = invBean.getSvcInvTMsg().invDt.getValue();
        BigDecimal dealAmt = NSXC003001Exchange.calcDealFromFunc(glblCmpyCd, ccyCd, invDt, funcAmt);
        return nullToZero(dealAmt);
    }

    private BigDecimal nullToZero(BigDecimal val) {
        if (hasValue(val)) {
            return val;
        }
        return BigDecimal.ZERO;
    }
    // END 2016/06/16 T.Aoyagi [QC#9659, ADD]

    // START 2017/08/16 K.Kim [QC#19406, ADD]
    /**
     * <pre>
     * get Contact Person Primary Key
     * </pre>
     * @param invBean SvcInvBean
     * @return ctacPsnPk BigDecimal
     */
    private BigDecimal getCtacPsnPk(SvcInvBean invBean) {
        SVC_INVTMsg invTmsg = invBean.getSvcInvTMsg();
        // START 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        String [] ctacTpList = {CTAC_TP.BILL_TO_CONTACT, CTAC_TP.BILL_TO_CONTACT_SVC_CALLS};
        // END 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", invTmsg.glblCmpyCd);
        prmMap.put("ctacTpBill", CTAC_TP.BILL_TO_CONTACT);
        // START 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        prmMap.put("ctacTpBillSvcCall", CTAC_TP.BILL_TO_CONTACT_SVC_CALLS);
        prmMap.put("ctacTpList", ctacTpList);
        // END 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        prmMap.put("invDt", invTmsg.invDt);
        prmMap.put("billToCustCd", invTmsg.billToCustCd);
        BigDecimal ctacPsnPk = (BigDecimal) ssmBatchClient.queryObject("getCtacPsnPk", prmMap);
        if (ctacPsnPk != null) {
            return ctacPsnPk;
        }
        // START 2017/10/25 M.Naito [QC#22062, DEL]
//        invBean.addXxMsgId(NSZM1293E);
        // END 2017/10/25 M.Naito [QC#22062, DEL]
        return null;
    }
    // END 2017/08/16 K.Kim [QC#19406, ADD]
}
