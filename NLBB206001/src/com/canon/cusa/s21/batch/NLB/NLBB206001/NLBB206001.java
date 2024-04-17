/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLB.NLBB206001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.REV_RECOG_SHPG_STSTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;
import business.parts.NLZC002001PMsg;
import business.parts.NWZC044001PMsg;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NWZ.NWZC044001.NWZC044001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.REV_RECOG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Intangible Inventory Transaction Creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/11/27   Fujitsu         S.Takami        Create          N/A
 * 2013/03/21   Fujitsu         F.Saito         Update          Defect#592(ReOpen)
 * 2013/04/30   Fujitsu         K.Fujita        Update          R-OM004
 * 2013/06/17   Fujitsu         K.Fujita        Update          Defect#1192
 * 2013/07/29   Fujitsu         S.Takami        Update          R-OM028 Sales Bom
 * 2013/08/30   Fujitsu         S.Takami        Update          Defect#1845 Add
 * 2015/09/17   Fujitsu         H.Nagashima     Update          CSA
 * 2016/04/15   Fujitsu         H.Nagashima     Update          QC#7127
 * 2016/09/09   Hitachi         Y.Takeno        Update          QC#12003
 * 2016/09/28   Fujitsu         T.Ishii         Update          S21_NA#14557-2
 * 2016/11/24   Hitachi         N.Arai          Update          QC#15829
 * 2016/12/08   Fujitsu         H.Nagashima     Update          QC#16290
 * 2017/01/25   Fujitsu         H.Nagashima     Update          QC#17206
 * 2017/05/18   CITS            S.Endo          Update          RS#7250
 * 2018/02/06   CITS            T.Gotoda        Update          QC#23548
 * 2018/02/20   CITS            K.Ogino         Update          QC#24095
 * 2018/12/13   Fujitsu         T.Murai         Update          QC#29488
 * 2022/06/14   CITS            K.Ogino         Update          QC#59704
 *</pre>
 */
public class NLBB206001 extends S21BatchMain {

    /**
     *  Message IDs
     * @author Q05163
     *
     */
    private static enum MSG_ID {
        /** [@] is mandatory. */
        ZZZM9025E,
        /** [@] is invalid value */
        ZZZM9026E,
        /** No search results found. */
        ZZOM0011E,
        /** Update failed because this record has been recently updated by another user. */
        ZZSM4104E,
        /** Database error : [@@@@]. */
        ZZSM4101E,
        /** @ ended abnormally. */
        NLBM0024E
    }

    /**
     * SQL Select columns
     * @author Q05163
     *
     */
    private static enum Select {
        /** DB Select SHPG_PLN.EZUPTIME */
        EZUPTIME,
        /** DB Select SHPG_PLN.EZUPTIMEZONE */
        EZUPTIMEZONE,
        /** DB Select SHPG_PLN.SHPG_PLN_NUM */
        SHPG_PLN_NUM,
        /** DB Select SHPG_PLN.TRX_HDR_NUM */
        TRX_HDR_NUM,
        /** DB Select SHPG_PLN.TRX_LINE_NUM */
        TRX_LINE_NUM,
        /** DB Select SHPG_PLN.TRX_LINE_SUB_NUM */
        TRX_LINE_SUB_NUM,
        /** DB Select SHPG_PLN.MDSE_CD */
        MDSE_CD,
        /** DB Select SHPG_PLN.STK_STS_CD */
        STK_STS_CD,
        /** DB Select SHPG_PLN.ORD_QTY */
        ORD_QTY,
        /** DB Select SHPG_PLN.SO_NUM */
        SO_NUM,
        /** DB Select SHPG_PLN.SO_SLP_NUM */
        SO_SLP_NUM,
        /** DB Select SHPG_PLN.BOL_NUM */
        BOL_NUM,
        /** DB Select SHPG_PLN.CARR_CD */
        CARR_CD,
        /** DB Select SHPG_PLN.PRO_NUM */
        PRO_NUM,
        /** DB Select SHPG_PLN.SELL_TO_CUST_CD */
        SELL_TO_CUST_CD,
        /** DB Select SHPG_PLN.BILL_TO_CUST_CD */
        BILL_TO_CUST_CD,
        /** DB Select SHPG_PLN.SHIP_TO_CUST_CD */
        SHIP_TO_CUST_CD,
        /** DB Select SHPG_PLN.SHIP_TO_LOC_NM */
        SHIP_TO_LOC_NM,
        /** DB Select SHPG_PLN.SLS_REP_TOC_CD */
        SLS_REP_TOC_CD,
        /** DB Select MDSE.VND_CD */
        VND_CD,
        /** DB Select MDSE.INVTY_CTRL_FLG */
        INVTY_CTRL_FLG,
        /** DB Select MDSE.INVTY_VAL_FLG */
        INVTY_VAL_FLG,
        /** DB Select MDSE.MDSE_TP_CD */
        MDSE_TP_CD,
        /** DB Select SHPG_PLN.SET_SHPG_PLN_NUM */
        SET_SHPG_PLN_NUM,
        /** DB Select SHPG_PLN.SET_MDSE_CD */
        SET_MDSE_CD,
        /** DB Select SHPG_PLN.SHIP_CPLT_CD */
        SHIP_CPLT_CD,
        /** DB Select SHPG_PLN.PO_REQ_FLG */
        PO_REQ_FLG,
        /** DB Select CPO_DTL.EZUPTIME */
        CPO_DTL_EZUPTIME,
        /** DB Select CPO_DTL.EZUPTIMEZONE */
        CPO_DTL_EZUPTIMEZONE,
        /** DB Select CPO.SYS_SRC_CD */
        SYS_SRC_CD,
        /** DB Select MDSE_TP_SRT(with statement view).SRT_NUM */
        SRT_NUM,
        /** DB Select SQL Type */
        DT_PTN,
        /** DB Select CPO_DTL.DS_CPO_CONFIG_PK */
        DS_CPO_CONFIG_PK,
        /** DB Select SHPG_PLN.INVTY_LOC_CD */
        INVTY_LOC_CD,
        //QC#16290 add Start
        /** DB Select CPO_DTL.RTL_WH_CD */
        RTL_WH_CD,
        //QC#16290 add End
        // QC#59704 Add Start
        /** DB Select SHPG_PLN.SHPG_STS_CD */
        SHPG_STS_CD
        // QC#59704 Add End
    }

    /** Inventory Update API object */
    private static final NLZC002001 INVTY_UPDATE_API = new NLZC002001();

    // 2015/09/18 CSA add start
    /** Add Hold API object */
    private static final NWZC044001 ADD_HOLD_API = new NWZC044001();
    // 2015/09/18 CSA add end

// START 2016/11/24 N.Arai [QC#15829, MOD]
    /** Batch Id */
    private static final String BATCH_ID = "NLBB206001";
// END 2016/11/24 N.Arai [QC#15829, MOD]

    /** Program ID for Log */
    private static final String PROGRAM_ID = " ## NLBB206001 ## ";

    /** Program Name */
    private static final String PROGRAM_NM = "Intangible Inventory Transaction Creation Batch";

    /** Sales Date */
    private String salesDate;

    /** Currency Code */
    private String ccyCd;

    /** Normal Record Count */
    private int normalRecCnt;

    /** Error Record Count */
    private int errRecCnt;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** records */
    private int totalRecCnt;

    // 2015/09/18 CSA add start
    private List<BigDecimal> procDsCpoCofgiPk = new ArrayList<BigDecimal>();
    // 2015/09/18 CSA add end

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = null;

    /** FLG "Y" */
    private static final String Y = ZYPConstant.FLG_ON_Y;

    /** FLG "N" */
    private static final String N = ZYPConstant.FLG_OFF_N;

    /** Non Shipment Warehouse Code */
    private static final String NS_WH_CD = "NS";

    /** Direct Shipment Warehouse Code */
    private static final String DS_WH_CD = "DS";

    /** default so_slp_num */
    private static final String DEF_SO_SLP_NUM = "001";

    /** Set TRX_LINE_SUB_NUM */
    private static final String SET_TRX_LINE_SUB_NUM = "000";

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Mail template ID */
    private  String mailTemplateId = null;

    /** S21BusinessProcessLog EventID : Ship */
    private static final String EVENTID_SHIPPED = "Ship";

    /** S21BusinessProcessLog EventID : Arrive */
    private static final String EVENTID_ARRIVED = "Arrive";

    /** S21BusinessProcessLog EventID : Install */
    private static final String EVENTID_INSTALLED = "Install";

    /** S21BusinessProcessLog ProcID : OM */
    private static final String PROCID = "OM";

    /** S21BusinessProcessLog SubSysID : NLB */
    private static final String SUBSYSID = "NLB";

    /** S21BusinessProcessLog DocTpID : OM */
    private static final String DOCTPCD = "OM";

    private List<String> shpgPlnNumList = new ArrayList<String>();
    
    private static final int MAX_SIZE_PER_CALL_NWZC1880 = 1000;
    /**
     * main process of Intangible Inventory Transaction Creation
     * @param args String[] batch parameters
     */
    public static void main(String[] args) {
        new NLBB206001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        this.normalRecCnt = 0;
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> glblCmpyCd:" + this.glblCmpyCd, this);
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (null == glblCmpyTMsg) {
            throw new S21AbendException(MSG_ID.ZZZM9026E.toString(), toArray("Global Company Code"));
        } else {
            this.ccyCd = glblCmpyTMsg.stdCcyCd.getValue();
        }

        mailTemplateId = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mailTemplateId)) {
            throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Mail Template Id"));
        }

        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
    }

    @Override
    protected void mainRoutine() {
        // Search Target Data
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("shpgStsCdValidated", SHPG_STS.VALIDATED);
        ssmParam.put("shpgStsCdPoPrinted", SHPG_STS.P_OR_O_PRINTED);
        ssmParam.put("flgOffN", N);
        ssmParam.put("flgOnY", Y);

        List<String> mdseTpCdList = new ArrayList<String>();
        mdseTpCdList.add(MDSE_TP.SET);
        mdseTpCdList.add(MDSE_TP.SALES_BOM);
        ssmParam.put("mdseTpCdList", mdseTpCdList.toArray(new String[0]));
        ssmParam.put("poStsCdClosed", PO_STS.CLOSED);

        List<String> shpgStsCdList = new ArrayList<String>();
        shpgStsCdList.add(SHPG_STS.SHIPPED);
        shpgStsCdList.add(SHPG_STS.ARRIVED);
        shpgStsCdList.add(SHPG_STS.N_INVOICE_READY);
        shpgStsCdList.add(SHPG_STS.INSTALLED);
        shpgStsCdList.add(SHPG_STS.INVOICED);
        ssmParam.put("shpgStsCdList", shpgStsCdList.toArray(new String[0]));
        ssmParam.put("trxSrcTpCd"   , TRX_SRC_TP.WHOLE_SALES);

        // 2013/07/29 R-OM028 Add Start
        ssmParam.put("shpgStsCdPOPrinted", SHPG_STS.P_OR_O_PRINTED);
        ssmParam.put("shpgStsCdSOPrinted", SHPG_STS.S_OR_O_PRINTED);
        ssmParam.put("mdseTpCdSet", MDSE_TP.SET);
        // 2013/07/29 R-OM028 Add End

        ssmParam.put("shpgStsCdCancelled", SHPG_STS.CANCELLED); // 2013/08/30 Defect#1845 Add

        //CSA QC#2836 Start
        ssmParam.put("shpgStsCdShipped", SHPG_STS.SHIPPED);
        //CSA QC#2836 End

        boolean rslt = (Boolean) ssmBatchClient.queryObject("getIntangibleShpgPlnData", ssmParam, new IntangibleInventoryTrxCreator());
        if (!rslt) {
//            if (this.normalRecCnt > 0) {
                this.termCd = TERM_CD.WARNING_END;
//            } else {
//                this.termCd = TERM_CD.ABNORMAL_END;
//            }
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);

        boolean isNeededMailInfo = TERM_CD.WARNING_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || TERM_CD.ABNORMAL_END == this.termCd;
        isNeededMailInfo = isNeededMailInfo || this.errRecCnt > 0;

        if (isNeededMailInfo) {
            // post error mail.
            if (!postErrorMail()) {
                throw new S21AbendException(MSG_ID.NLBM0024E.toString(), toArray(PROGRAM_NM + ":mail sending process"));
            }
        }
    }

    private boolean postErrorMail() {
        final String methodNm = "postErrorMail";
        debugMethodStart(getClass(), methodNm);

        try {

            final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>();

            NWXC001001MailSubstituteString sbsStr;

            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("batchId");
            sbsStr.setSbstStr(this.getClass().getSimpleName());
            sbsStrList.add(sbsStr);

            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("batchNm");
            sbsStr.setSbstStr("Intangible Inventory Transaction Creation");
            sbsStrList.add(sbsStr);

            sbsStr = new NWXC001001MailSubstituteString();
            sbsStr.setSbstKey("batchProcLogId");
            sbsStr.setSbstStr(super.getBatchProcessLogID());
            sbsStrList.add(sbsStr);

            boolean isNormalEnd = new NWXC001001Mail().postMail(glblCmpyCd, "NLBB2060", mailTemplateId, sbsStrList);
            debug(getClass(), "isNormalEnd? = [", isNormalEnd, "]");

            return isNormalEnd;

        } finally {
            debugMethodEnd(getClass(), methodNm);
        }
    }

    /**
     * ResultSet of SQL process.
     * @author Q05163
     *
     */
    protected class IntangibleInventoryTrxCreator extends S21SsmBooleanResultSetHandlerSupport {

        /** Max SO Slip Number by SO_NUM map*/
        private Map<String, Integer>maxSoSlpNumMap = null;
        /** Normal Record Count in same TRX_HDR_NUM */
        private Integer trnNormalRecCnt = 0;
// QC#5910 add Start
        private String CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD = "CR_AND_BILL_ONLY_DUMMY_WH_CD";
        private List<String> crRebillDummyWhCdList;
// QC#5910 add End
        private boolean spUpdFlg;   //QC#17206 add

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            // TODO Auto-generated method stub
            Boolean rslt = Boolean.TRUE;
            rs.last();
            totalRecCnt = rs.getRow();
            rs.first();
            if (0 == totalRecCnt) {
                S21InfoLogOutput.println(MSG_ID.ZZOM0011E.toString());
            } else {
                maxSoSlpNumMap = new HashMap<String, Integer>();
                rslt = generateInvtyTrxData(rs);
            }
            return rslt;
        }

        private Boolean generateInvtyTrxData(ResultSet rs) throws SQLException {
// QC#5910 add Start
            //get credit&rebill dummy wh cd
            String crRebillDummyWhCdCsv = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD, glblCmpyCd);
            crRebillDummyWhCdList = new ArrayList<String>();
            if (crRebillDummyWhCdCsv != null) {
                String[] crRebillDummyWhCd = crRebillDummyWhCdCsv.split(",");
                crRebillDummyWhCdList = Arrays.asList(crRebillDummyWhCd);
            }
// QC#5910 add End
            Boolean rslt = Boolean.TRUE;
            rs.first();

            NLBB206001Bean recordBean = new NLBB206001Bean();

            String prevTrxHdrNum = rs.getString(Select.TRX_HDR_NUM.name());
            String setShpgPlnNum   = null;
            String setMdseCd       = null;
            String shipCpltCd      = null;
            String errTrxHdrNum = null;
            String curTrxHdrNum = null;
            totalRecCnt = 0;
            do {
                //QC#16290 add Start
                if (crRebillDummyWhCdList.contains(rs.getString(Select.RTL_WH_CD.name()))) {
                    continue;
                }
                //QC#16290 add End
                setRsltData(rs, recordBean);
                //CSA QC#2800 Start
                //get uptime ... updated by add hold api
                SHPG_PLNTMsg shpgPlnTmsg = new SHPG_PLNTMsg();
                shpgPlnTmsg.glblCmpyCd.setValue(glblCmpyCd);
                shpgPlnTmsg.shpgPlnNum.setValue(recordBean.getShpgPlnNum());
                shpgPlnTmsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKey(shpgPlnTmsg);
                // QC#24095
                if (shpgPlnTmsg == null) {
                    continue;
                }
                recordBean.setEzuptime(shpgPlnTmsg.ezUpTime.getValue());
                //CSA QC#2800 End
                // QC#59704 Add Start
                if (!S21StringUtil.isEquals(shpgPlnTmsg.shpgStsCd.getValue(), recordBean.getShpgStsCd())) {
                    debug(getClass(), recordBean.getShpgPlnNum() + ": Skip, because Shipping Status Code was changed by other process.");
                    continue;
                }
                // QC#59704 Add End
                shipCpltCd    = recordBean.getShipCpltCd();
                curTrxHdrNum  = recordBean.getTrxHdrNum();
                setShpgPlnNum = recordBean.getSetShpgPlnNum();
                setMdseCd     = recordBean.getSetMdseCd();
                if (isErrTrxHdrNum(errTrxHdrNum, curTrxHdrNum)) {
//                    errRecCnt++;
                    continue;
                }
                // 2013/07/29 R-OM028 Mod Start
//                if (!S21StringUtil.isEmpty(shipCpltCd)) {
                String poReqFlg = recordBean.getPoReqFlg();
                if (!S21StringUtil.isEmpty(shipCpltCd) && N.equals(poReqFlg)) {
                // 2013/07/29 R-OM028 Mod End
                    if (isOtherComponentHold(curTrxHdrNum, shipCpltCd)) {
//                        normalRecCnt++;
//                        trnNormalRecCnt++;
                        continue;
                    }
                }
                // 2013/07/29 R-OM028 Mod Start
//                if (!S21StringUtil.isEmpty(setMdseCd)) {
                String trxLineSubNum = recordBean.getTrxLineSubNum();
                if (!S21StringUtil.isEmpty(setMdseCd) || SET_TRX_LINE_SUB_NUM.equals(trxLineSubNum)) {
                // 2013/07/29 R-OM028 Mod End
                    if (isSetOtherComponentHold(recordBean)) {
//                        normalRecCnt++;
//                        trnNormalRecCnt++;
                        continue;
                    }
                    // 2013/07/29 R-OM028 Add Start
                    if (SET_TRX_LINE_SUB_NUM.equals(trxLineSubNum)) {
                        if (isSetOtherComponentBeforeShippedForSet(recordBean)) {
//                            normalRecCnt++;
//                            trnNormalRecCnt++;
                            continue;
                        }
                    }
                    // 2013/07/29 R-OM028 Add End

                    // 2013/07/29 R-OM028 Add Start
                    if (!S21StringUtil.isEmpty(setMdseCd)) {
                    // 2013/07/29 R-OM028 Add End
                        if (isSetOtherComponentBeforeShipped(setShpgPlnNum)) {
//                            normalRecCnt++;
//                            trnNormalRecCnt++;
                            continue;
                        }
                    } // 2013/07/29 R-OM028 Add
                }
                // 2013/07/29 R-OM028 Add Start
                // vendor sales bom
                String mdseTpCd = recordBean.getMdseTpCd();
                if (MDSE_TP.SALES_BOM.equals(mdseTpCd)) {
                    if (isSalesBomMainBaseCompNotShipped(recordBean)) {
//                        normalRecCnt++;
//                        trnNormalRecCnt++;
                        continue;
                    }
                }
                // 2013/07/29 R-OM028 Add End
                if (isNeededCommit(prevTrxHdrNum, curTrxHdrNum)) {

                    executeNwzc1880(prevTrxHdrNum);
                    
                    commit();
                    prevTrxHdrNum = curTrxHdrNum;
                    trnNormalRecCnt = 0;
                    shpgPlnNumList.clear();
                }

                if (shpgPlnNumList.size() >= MAX_SIZE_PER_CALL_NWZC1880) {
                    executeNwzc1880(prevTrxHdrNum);
                    shpgPlnNumList.clear();
                }
                
                spUpdFlg = true;   //QC#17206 add
                //QC#17206 move to after upadate shipping plan
                if (!callInvtyTrxApi(recordBean)) {
                    errTrxHdrNum = curTrxHdrNum;
                    rslt = Boolean.FALSE;
                } else if (!updateShpgPln(recordBean)) {
                    errTrxHdrNum = curTrxHdrNum;
                    rslt = Boolean.FALSE;
                // 2015/09/17 CSA Add Start
                // QC#17206 add Start
                } else if (!spUpdFlg) {
                    continue;
                // QC#17206 add End
                } else if (!setBillingHold(recordBean)) {
                    errTrxHdrNum = curTrxHdrNum;
                    rslt = Boolean.FALSE;
                } else if (!updateMachineMaster(recordBean)) {
                    errTrxHdrNum = curTrxHdrNum;
                    rslt = Boolean.FALSE;
                // 2015/09/17 CSA Add End
                } else {
                    rslt = Boolean.TRUE;
                }

                if (rslt) {
                    normalRecCnt++;
                    trnNormalRecCnt++;
                } else {
                    rollback();
                    errRecCnt++;
                    normalRecCnt -= trnNormalRecCnt;
                    errRecCnt += trnNormalRecCnt;
                    trnNormalRecCnt = 0;
                }
                ++totalRecCnt;
            } while (rs.next());

            if (rslt) {
                // if last data is operated without error, commit
                // data.
                executeNwzc1880(prevTrxHdrNum);
                commit();
            } else {
                // if last data is operated with error, rollback data.
                normalRecCnt -= trnNormalRecCnt;
                errRecCnt += trnNormalRecCnt;
                rollback();
            }

            return rslt;
        }

        private void executeNwzc1880(String prevTrxHdrNum) {
            NWZC188001PMsg pMsg = new NWZC188001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd,glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum,prevTrxHdrNum);
            int shpgPlnCount = 0;
            for (String shpgPlnNum : shpgPlnNumList) {
                ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNumList.no(shpgPlnCount).shpgPlnNum,shpgPlnNum);
                shpgPlnCount++;
            }
            pMsg.shpgPlnNumList.setValidCount(shpgPlnCount);

            if (shpgPlnCount > 0){
                NWZC188001 nwzc188001 = new NWZC188001();
                nwzc188001.execute(pMsg, ONBATCH_TYPE.BATCH);
            }
        }

        private Boolean callInvtyTrxApi(NLBB206001Bean recordBean) throws SQLException {
            Boolean rslt = Boolean.TRUE;
            String invtyValFlg = recordBean.getInvtyValFlg();
            if (Y.equals(invtyValFlg)) {
                // call Inventory Update API
                List<NLZC002001PMsg> invtyUpdateApiParamList = createDummyStockInOutParam(recordBean);
                INVTY_UPDATE_API.execute(invtyUpdateApiParamList, ONBATCH_TYPE.BATCH);
                if (hasInvtyUpdateApiError(invtyUpdateApiParamList)) {
                    rslt = Boolean.FALSE;
                }
            }
            return rslt;
        }

        private Boolean updateShpgPln(NLBB206001Bean recordBean) {
            Boolean rslt = Boolean.TRUE;

            SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgPlnNum, recordBean.getShpgPlnNum());

            shpgPlnTMsg = (SHPG_PLNTMsg) EZDTBLAccessor.findByKeyForUpdate(shpgPlnTMsg);
            String origShpgStsCd = null;
            if (null != shpgPlnTMsg) {
                String dbUpTime = shpgPlnTMsg.ezUpTime.getValue();
                String dbUpTimeZone = shpgPlnTMsg.ezUpTimeZone.getValue();

                String locUpTime = recordBean.getEzuptime();
                String locUpTimeZone = recordBean.getEzuptimezone();
                origShpgStsCd = shpgPlnTMsg.shpgStsCd.getValue();

                if (ZYPDateUtil.isSameTimeStamp(dbUpTime, dbUpTimeZone, locUpTime, locUpTimeZone)) {
                    // 2015/09/16 CSA mod start
//                    ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgStsCd, SHPG_STS.SHIPPED);
                    //get shipping status cd
                    //CSA QC#2836 Start
//                    String shpgStsCd = getShippingStatusFromReferenceLine(shpgPlnTMsg);
//                    if (shpgStsCd == null) {
//                        shpgStsCd = getShippingStatusFromOrder(shpgPlnTMsg);
//                    }
                    String shpgStsCd =  null;
                    CPO_DTLTMsg dsCpoDtlTMsg = new CPO_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoOrdNum, recordBean.getTrxHdrNum());
                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoDtlLineNum, recordBean.getTrxLineNum());
                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoDtlLineSubNum, recordBean.getTrxLineSubNum());
                    dsCpoDtlTMsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKey(dsCpoDtlTMsg);

                    // CSA QC#2846 add Start
                    if (ZYPCommonFunc.hasValue(recordBean.getSetMdseCd())) {
                        // set related tangible compornent shipping info
                        setTangibleCompornentShippingInfo(shpgPlnTMsg, recordBean);
                    } else {
                    // CSA QC#2846 add End
                        if (ZYPCommonFunc.hasValue(dsCpoDtlTMsg.refCpoDtlLineNum) && ZYPCommonFunc.hasValue(dsCpoDtlTMsg.refCpoDtlLineSubNum)) {
                            shpgStsCd = getShippingStatusFromReferenceLine(dsCpoDtlTMsg, shpgPlnTMsg, recordBean);
                            if (ZYPCommonFunc.hasValue(shpgStsCd)) {
                                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgStsCd, shpgStsCd);
                            }
                            // QC#12003 add Start
                            if (ZYPCommonFunc.hasValue(recordBean.getActlShipDt())) {
                                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.actlShipDt, recordBean.getActlShipDt());
                            }
                            // QC#12003 add End
                        } else {
                            shpgStsCd = getShippingStatusFromOrder(shpgPlnTMsg);
                            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgStsCd, shpgStsCd);
                        }
                    }
                    //CSA QC#2836 End
                    // 2015/09/16 CSA mod end
                    // QC#17206 add Start
                    if (origShpgStsCd != null && origShpgStsCd.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
                        spUpdFlg = false;
                        return true;
                    }
                    recordBean.setUpdShpgStsCd(shpgPlnTMsg.shpgStsCd.getValue());
                    // QC#17206 add End

                    // CSA QC#2836 Start
//                    ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.soCloseFlg, Y);
                    if (judgeSoClose(shpgPlnTMsg)) {
                        ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.soCloseFlg, Y);
                    }
                    // CSA QC#2836 End

                    //CSA QC#3145
//                    if (Y.equals(recordBean.getInvtyValFlg())) {
                        if (!ZYPCommonFunc.hasValue(shpgPlnTMsg.soNum)) {
                            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.soNum, recordBean.getSoNum());
                            //CSA QC#2846 add Start
                            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.proNum, recordBean.getProNum());
                            ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.bolNum, recordBean.getBolNum());
                            //CSA QC#2846 add End
                        }
//                    }

                    EZDTBLAccessor.update(shpgPlnTMsg);
                    String rtnCd = shpgPlnTMsg.getReturnCode();
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        S21InfoLogOutput.println(MSG_ID.ZZSM4101E.toString(), toArray("to Update ", "Shipping Plan", " Shipping Plan Number = ", recordBean.getShpgPlnNum()));
                    }
                    // When SHPG_PLN.TRX_HDR_NUM has been changed (or greater than 1000 count) Invoke NWZC188001.execute
                    shpgPlnNumList.add(shpgPlnTMsg.shpgPlnNum.getValue());

                } else {
                    S21InfoLogOutput.println(MSG_ID.ZZSM4104E.toString());
                    debug(getClass(), recordBean.getShpgPlnNum());
                }
            } else {
                S21InfoLogOutput.println(MSG_ID.ZZOM0011E.toString());
                debug(getClass(), recordBean.getShpgPlnNum());
                rslt = Boolean.FALSE;
            }

            if (Boolean.TRUE.equals(rslt) && isShippedCompletion(shpgPlnTMsg)) {

                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd       , shpgPlnTMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum        , shpgPlnTMsg.trxHdrNum);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum    , shpgPlnTMsg.trxLineNum);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum , shpgPlnTMsg.trxLineSubNum);

                cpoDtlTMsg = (CPO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(cpoDtlTMsg);

                if (null != cpoDtlTMsg) {
                    String dbUpTime = cpoDtlTMsg.ezUpTime.getValue();
                    String dbUpTimeZone = cpoDtlTMsg.ezUpTimeZone.getValue();

                    String locUpTime = recordBean.getCpoDtlEzuptime();
                    String locUpTimeZone = recordBean.getCpoDtlEzuptimezone();

                    if (ZYPDateUtil.isSameTimeStamp(dbUpTime, dbUpTimeZone, locUpTime, locUpTimeZone)) {
                        ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ordLineStsCd, ORD_LINE_STS.SHIPPED);

                        EZDTBLAccessor.update(cpoDtlTMsg);
                        String rtnCd = cpoDtlTMsg.getReturnCode();
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            S21InfoLogOutput.println(MSG_ID.ZZSM4101E.toString(), toArray("to Update ", "Cpo Dtl", " Shipping Plan Number = ", recordBean.getShpgPlnNum()));
                        }
                    } else {
                        S21InfoLogOutput.println(MSG_ID.ZZSM4104E.toString());
                        // QC#59704 Mod Start
//                      debug(getClass(), recordBean.getShpgPlnNum());
                        debug(getClass(), recordBean.getShpgPlnNum() + " ( checking at CPO_DTL)");
                        // QC#59704 Mod End
                    }
                } else {
                    S21InfoLogOutput.println(MSG_ID.ZZOM0011E.toString());
                    debug(getClass(), recordBean.getShpgPlnNum());
                    rslt = Boolean.FALSE;
                }
            }

            if (Boolean.TRUE.equals(rslt)) {
//                outProcessLog(recordBean, EVENTID_SHIPPED, shpgPlnTMsg);
                String eventId = null;
                String shpgStsCd = shpgPlnTMsg.shpgStsCd.getValue();
                if (origShpgStsCd != null && !origShpgStsCd.equals(shpgStsCd)) {
                    if (SHPG_STS.SHIPPED.equals(shpgStsCd)) {
                        eventId = EVENTID_SHIPPED;
    
                    } else if (SHPG_STS.ARRIVED.equals(shpgStsCd)) {
                        eventId = EVENTID_ARRIVED;
    
                    } else if (SHPG_STS.INSTALLED.equals(shpgStsCd)) {
                        eventId = EVENTID_INSTALLED;
                    }
                }

                if (eventId != null) {
                    outProcessLog(recordBean, eventId, shpgPlnTMsg);
                }
            }

            return rslt;
        }

        private void setRsltData(ResultSet rs, NLBB206001Bean recordBean) throws SQLException {
            recordBean.setEzuptime(nullToString(rs.getString(Select.EZUPTIME.name())));
            recordBean.setEzuptimezone(nullToString(rs.getString(Select.EZUPTIMEZONE.name())));
            recordBean.setShpgPlnNum(nullToString(rs.getString(Select.SHPG_PLN_NUM.name())));
            recordBean.setTrxHdrNum(nullToString(rs.getString(Select.TRX_HDR_NUM.name())));
            recordBean.setTrxLineNum(nullToString(rs.getString(Select.TRX_LINE_NUM.name())));
            recordBean.setTrxLineSubNum(nullToString(rs.getString(Select.TRX_LINE_SUB_NUM.name())));
            recordBean.setMdseCd(nullToString(rs.getString(Select.MDSE_CD.name())));
            recordBean.setStkStsCd(nullToString(rs.getString(Select.STK_STS_CD.name())));
            recordBean.setOrdQty(nullToBigDecimal(rs.getBigDecimal(Select.ORD_QTY.name())));
            recordBean.setSoNum(nullToString(rs.getString(Select.SO_NUM.name())));
            recordBean.setSoSlpNum(nullToString(rs.getString(Select.SO_SLP_NUM.name())));
            recordBean.setBolNum(nullToString(rs.getString(Select.BOL_NUM.name())));
            recordBean.setCarrCd(nullToString(rs.getString(Select.CARR_CD.name())));
            recordBean.setProNum(nullToString(rs.getString(Select.PRO_NUM.name())));
            recordBean.setSellToCustCd(nullToString(rs.getString(Select.SELL_TO_CUST_CD.name())));
            recordBean.setBillToCustCd(nullToString(rs.getString(Select.BILL_TO_CUST_CD.name())));
            recordBean.setShipToCustCd(nullToString(rs.getString(Select.SHIP_TO_CUST_CD.name())));
            recordBean.setShipToLocNm(nullToString(rs.getString(Select.SHIP_TO_LOC_NM.name())));
            recordBean.setSlsRepTocCd(nullToString(rs.getString(Select.SLS_REP_TOC_CD.name())));
            recordBean.setVndCd(nullToString(rs.getString(Select.VND_CD.name())));
            recordBean.setInvtyCtrlFlg(nullToString(rs.getString(Select.INVTY_CTRL_FLG.name())));        // 2013/07/29 R-OM028 Add
            recordBean.setInvtyValFlg(nullToString(rs.getString(Select.INVTY_VAL_FLG.name())));
            recordBean.setMdseTpCd(nullToString(rs.getString(Select.MDSE_TP_CD.name())));            // 2013/07/29 R-OM028 Add
            recordBean.setSetShpgPlnNum(nullToString(rs.getString(Select.SET_SHPG_PLN_NUM.name())));
            recordBean.setSetMdseCd(nullToString(rs.getString(Select.SET_MDSE_CD.name())));
            recordBean.setShipCpltCd(nullToString(rs.getString(Select.SHIP_CPLT_CD.name())));
            recordBean.setPoReqFlg(nullToString(rs.getString(Select.PO_REQ_FLG.name())));
            recordBean.setCpoDtlEzuptime(nullToString(rs.getString(Select.CPO_DTL_EZUPTIME.name())));
            recordBean.setCpoDtlEzuptimezone(nullToString(rs.getString(Select.CPO_DTL_EZUPTIMEZONE.name())));
            recordBean.setSysSrcCd(nullToString(rs.getString(Select.SYS_SRC_CD.name())));          // 2013/07/29 R-OM028 Add
            recordBean.setSrtNum(nullToString(rs.getString(Select.SRT_NUM.name())));               // 2013/07/29 R-OM028 Add
            recordBean.setDtPtn(nullToString(rs.getString(Select.DT_PTN.name())));
            recordBean.setDsCpoConfigPk(nullToBigDecimal(rs.getBigDecimal(Select.DS_CPO_CONFIG_PK.name())));
            recordBean.setInvtyLocCd(nullToString(rs.getString(Select.INVTY_LOC_CD.name())));
            // QC#59704 Add Start
            recordBean.setShpgStsCd(nullToString(rs.getString(Select.SHPG_STS_CD.name())));
            // QC#59704 Add End

            debug(getClass(), recordBean.toString());
        }

        private boolean isNeededCommit(String prevTrxHdrNum, String curTrxHdrNum) {
            return !prevTrxHdrNum.equals(curTrxHdrNum);
        }

        private boolean isErrTrxHdrNum(String errTrxHdrNum, String curTrxHdrNum) {
            return ZYPCommonFunc.hasValue(errTrxHdrNum) && errTrxHdrNum.equals(curTrxHdrNum);
        }

        private List<NLZC002001PMsg> createDummyStockInOutParam(NLBB206001Bean recordBean) {
            List<NLZC002001PMsg> invtyUpdateApiParamList = new ArrayList<NLZC002001PMsg>();

            // Domestic Purchase Stock-In
            NLZC002001PMsg pmsg = new NLZC002001PMsg();

            ZYPEZDItemValueSetter.setValue(pmsg.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pmsg.trxCd, TRX.PURCHASE_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(pmsg.trxRsnCd, TRX_RSN.PURCHASE_STOCK_IN);
            
            // Modify Start 2013/06/17 Defect#1192
            //ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_ROSS);
            ZYPEZDItemValueSetter.setValue(pmsg.xxTrxDtlCd, NLZC002001.TRX_DTL_INTG_WITH_COST);
            // Modify End   2013/06/17 Defect#1192
            
            ZYPEZDItemValueSetter.setValue(pmsg.mdseCd, recordBean.getMdseCd());

            if(Y.equals(recordBean.getPoReqFlg())){
                ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, DS_WH_CD);
            } else {
                ZYPEZDItemValueSetter.setValue(pmsg.invtyLocCd, NS_WH_CD);
            }

            ZYPEZDItemValueSetter.setValue(pmsg.locStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(pmsg.stkStsCd, recordBean.getStkStsCd());
            ZYPEZDItemValueSetter.setValue(pmsg.xxRqstQty, recordBean.getOrdQty());
            ZYPEZDItemValueSetter.setValue(pmsg.invtyTrxDt, salesDate);
            
            // Modify Start 2013/06/17 Defect#1192
            //ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_ROSS);
            ZYPEZDItemValueSetter.setValue(pmsg.xxSysTp, NLZC002001.SYS_TP_OM);
            // Modify End   2013/06/17 Defect#1192

            // 2013/07/29 R-OM028 Mod Start
//            ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, SYS_SRC.ROSS);
            ZYPEZDItemValueSetter.setValue(pmsg.sysSrcCd, recordBean.getSysSrcCd());
            // 2013/07/29 R-OM028 Mod End
            
            String soNum = recordBean.getSoNum();
            String soSlpNum = recordBean.getSoSlpNum();
            if (!ZYPCommonFunc.hasValue(soNum)) {
                // 2013/08/30 dEFECT#1845 Mod Start
//                soNum = ZYPExtnNumbering.getUniqueID(glblCmpyCd, "DUMMY_SO_NUM_FOR_INTG");
//                recordBean.setSoNum(soNum);

                String setShpgPlnNum = recordBean.getSetShpgPlnNum();
                if (ZYPCommonFunc.hasValue(setShpgPlnNum)) {
                    Map<String, String> queryMap = new HashMap<String, String>();
                    queryMap.put("glblCmpyCd", glblCmpyCd);
                    queryMap.put("trxHdrNum", recordBean.getTrxHdrNum());
                    queryMap.put("setShpgPlnNum", setShpgPlnNum);
                    soNum = (String) ssmBatchClient.queryObject("getSetSoNum", queryMap);
                }
                if (!ZYPCommonFunc.hasValue(soNum)) {
                    soNum = ZYPExtnNumbering.getUniqueID(glblCmpyCd, "DUMMY_SO_NUM_FOR_INTG");
                }
                recordBean.setSoNum(soNum);
                // 2013/08/30 dEFECT#1845 Mod End
            }
            if (!ZYPCommonFunc.hasValue(soSlpNum)) {
                soSlpNum = getMaxSoSlpNum(recordBean);
                recordBean.setSoSlpNum(soSlpNum);
            }
            ZYPEZDItemValueSetter.setValue(pmsg.soNum, soNum);
            ZYPEZDItemValueSetter.setValue(pmsg.soSlpNum, soSlpNum);
            ZYPEZDItemValueSetter.setValue(pmsg.bolNum, recordBean.getBolNum());
            ZYPEZDItemValueSetter.setValue(pmsg.carrCd, recordBean.getCarrCd());
            ZYPEZDItemValueSetter.setValue(pmsg.proNum, recordBean.getProNum());
            

            // Modify Start 2013/06/17 Defect#1192
//          ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, recordBean.getTrxHdrNum());
//          ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, recordBean.getTrxLineNum());
//          ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, recordBean.getTrxLineSubNum());
            ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, recordBean.getTrxHdrNum());
            ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, recordBean.getTrxLineNum());
            ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, recordBean.getTrxLineSubNum());
//          ZYPEZDItemValueSetter.setValue(pmsg.rossOrdNum, recordBean.getTrxHdrNum());
//          ZYPEZDItemValueSetter.setValue(pmsg.rossDtlLineNum, recordBean.getTrxLineNum());
//          ZYPEZDItemValueSetter.setValue(pmsg.rossDtlLineSubNum, recordBean.getTrxLineSubNum());
            // Modify End   2013/06/17 Defect#1192            
          
            ZYPEZDItemValueSetter.setValue(pmsg.sellToCustCd, recordBean.getSellToCustCd());
            ZYPEZDItemValueSetter.setValue(pmsg.billToCustCd, recordBean.getBillToCustCd());
            ZYPEZDItemValueSetter.setValue(pmsg.shipToCustCd, recordBean.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(pmsg.shipToCustNm, recordBean.getShipToLocNm());
            ZYPEZDItemValueSetter.setValue(pmsg.vndCd, recordBean.getVndCd());
            ZYPEZDItemValueSetter.setValue(pmsg.tocCd, recordBean.getSlsRepTocCd());
            ZYPEZDItemValueSetter.setValue(pmsg.ccyCd, ccyCd);
            ZYPEZDItemValueSetter.setValue(pmsg.uomCd, PKG_UOM.EACH);

            invtyUpdateApiParamList.add(pmsg);

            // Direct Sales Stock Out
            NLZC002001PMsg pmsgStkOut = new NLZC002001PMsg();
            EZDMsg.copy(pmsg, null, pmsgStkOut, null);
            ZYPEZDItemValueSetter.setValue(pmsgStkOut.xxRqstTpCd, NLZC002001.RQST_STOCK_OUT);
            ZYPEZDItemValueSetter.setValue(pmsgStkOut.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(pmsgStkOut.trxRsnCd, TRX_RSN.DIRECT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST);

            if(Y.equals(recordBean.getPoReqFlg())){
                ZYPEZDItemValueSetter.setValue(pmsgStkOut.shipFromLocCustCd, DS_WH_CD);
            } else {
                ZYPEZDItemValueSetter.setValue(pmsgStkOut.shipFromLocCustCd, NS_WH_CD);
            }
            
            // Add Start 2013/06/17 Defect#1192
            ZYPEZDItemValueSetter.setValue(pmsgStkOut.xxSysTp, NLZC002001.SYS_TP_ROSS);    
            // Add End   2013/06/17 Defect#1192

            pmsgStkOut.vndCd.clear();
            pmsgStkOut.xxTrxDtlCd.clear();

            invtyUpdateApiParamList.add(pmsgStkOut);

            // Direct Sales Stock In
            NLZC002001PMsg pmsgStkIn = new NLZC002001PMsg();
            EZDMsg.copy(pmsgStkOut, null, pmsgStkIn, null);
            ZYPEZDItemValueSetter.setValue(pmsgStkIn.xxRqstTpCd, NLZC002001.RQST_STOCK_IN);
            ZYPEZDItemValueSetter.setValue(pmsgStkIn.trxCd, TRX.MOVEMENT);
            ZYPEZDItemValueSetter.setValue(pmsgStkIn.trxRsnCd, TRX_RSN.DIRECT_SALE_STOCK_IN_INTANGIBLE_WITH_COST);
            // 2015/09/08 CSA Mod Start
//            ZYPEZDItemValueSetter.setValue(pmsgStkIn.invtyLocCd, recordBean.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(pmsgStkIn.invtyLocCd, recordBean.getInvtyLocCd());
            // 2015/09/08 CSA Mod End
            ZYPEZDItemValueSetter.setValue(pmsgStkIn.locStsCd, LOC_STS.WAITING_FOR_INSTALLATION);

            if(Y.equals(recordBean.getPoReqFlg())){
                ZYPEZDItemValueSetter.setValue(pmsgStkIn.shipFromLocCustCd, DS_WH_CD);
            } else {
                ZYPEZDItemValueSetter.setValue(pmsgStkIn.shipFromLocCustCd, NS_WH_CD);
            }

            invtyUpdateApiParamList.add(pmsgStkIn);

            return invtyUpdateApiParamList;
        }

        private boolean hasInvtyUpdateApiError(List<NLZC002001PMsg> invtyUpdateApiParamList) {
            boolean rslt = Boolean.FALSE;

            for (NLZC002001PMsg pMsg : invtyUpdateApiParamList) {
                if (pMsg.xxMsgIdList.getValidCount() > 0) {
                    rslt = Boolean.TRUE;
                    for (int n = 0; n < pMsg.xxMsgIdList.getValidCount(); n++) {
                        String errId = pMsg.xxMsgIdList.no(n).xxMsgId.getValue();
                        // print log
                        S21InfoLogOutput.println(errId);
                    }
                }
            }
            return rslt;
        }

        private String getMaxSoSlpNum(NLBB206001Bean recordBean) {
            Integer maxSoSlpNum = maxSoSlpNumMap.get(recordBean.getSoNum());
            if (null == maxSoSlpNum) {
                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("soNum", recordBean.getSoNum());
                queryParam.put("trxSrcTpCd"    , TRX_SRC_TP.WHOLE_SALES);
                Map<String, String> result = (Map<String, String>) ssmBatchClient.queryObject("getMaxSoSlpNum", queryParam);
                if (null == result) {
                    maxSoSlpNum = 0;
                } else {
                    maxSoSlpNum = getIntegerVal(result.get("MAX_SO_SLP_NUM"));
                }
            }
            SHPG_PLNTMsg tMsg = new SHPG_PLNTMsg();
            int lengthOfSoSlpNum = tMsg.getAttr("soSlpNum").getDigit();
            maxSoSlpNum = maxSoSlpNum + 1;
            maxSoSlpNumMap.put(recordBean.getSoNum(), maxSoSlpNum);
            return ZYPCommonFunc.leftPad(String.valueOf(maxSoSlpNum), lengthOfSoSlpNum, "0");
        }

        private boolean isOtherComponentHold(String trxHdrNum, String shipCpltCd) {

            boolean otherComponentHoldFlag = false;

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd" , glblCmpyCd);
            queryParam.put("trxHdrNum" , trxHdrNum);
            queryParam.put("shipCpltCd" , shipCpltCd);
            queryParam.put("relPntSoPrintFlg"  , Y);
            queryParam.put("relFlg"            , N);

            String result = (String) ssmBatchClient.queryObject("isOtherComponentHold", queryParam);
            if (null == result) {
                otherComponentHoldFlag = false;
            } else {
                otherComponentHoldFlag = true;
            }

            return otherComponentHoldFlag;
        }

        // 2013/07/29 R-OM028 Mod Start
//        private boolean isSetOtherComponentHold(String trxHdrNum) {
        private boolean isSetOtherComponentHold(NLBB206001Bean recordBean) {
        // 2013/07/29 R-OM028 Mod End

            boolean otherSetComponentHoldFlag = false;

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd" , glblCmpyCd);
            // 2013/07/29 R-OM028 Mod Start
            //queryParam.put("setShpgPlnNum"     , trxHdrNum);
            queryParam.put("trxHdrNum"         , recordBean.getTrxHdrNum());
            queryParam.put("trxLineNum"        , recordBean.getTrxLineNum());
            // 2013/07/29 R-OM028 Mod End
            queryParam.put("relPntSoPrintFlg"  , Y);
            queryParam.put("relFlg"            , N);

            // 2013/08/30 Defect#1845 Add Start
            String setShpgPlnNum = recordBean.getSetShpgPlnNum();
            if (ZYPCommonFunc.hasValue(setShpgPlnNum)) {
                queryParam.put("chkPrnt", Y);
                queryParam.put("targetShpgPlnNum", setShpgPlnNum);
            } else {
                queryParam.remove("chkPrnt");
                queryParam.put("targetShpgPlnNum", recordBean.getShpgPlnNum());
            }
            // 2013/08/30 Defect#1845 Add End

            String result = (String) ssmBatchClient.queryObject("isSetOtheComponentHold", queryParam);
            if (null == result) {
                otherSetComponentHoldFlag = false;
            } else {
                otherSetComponentHoldFlag = true;
            }

            return otherSetComponentHoldFlag;
        }

        private boolean isSetOtherComponentBeforeShipped(String setShpgPlnNum) {

            boolean setOtherComponentBeforeShippedFlag = false;

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd" , glblCmpyCd);
            queryParam.put("shpgStsCd"        , SHPG_STS.SHIPPED);
            queryParam.put("setShpgPlnNum"    , setShpgPlnNum);
            queryParam.put("invtyCtrlFlg" , Y);

            // Modify Start 2013/03/21 Defect#592
//            String result = (String) ssmBatchClient.queryObject("isOtherComponentBeforeShipped", queryParam);
            String result = (String) ssmBatchClient.queryObject("isSetOtherComponentBeforeShipped", queryParam);
            // Modify End   2013/03/21 Defect#592
            if (null == result) {
                setOtherComponentBeforeShippedFlag = false;
            } else {
                setOtherComponentBeforeShippedFlag = true;
            }

            return setOtherComponentBeforeShippedFlag;
        }

        // 2013/07/29 R-OM028 Add Start
        private boolean isSetOtherComponentBeforeShippedForSet(NLBB206001Bean recordBean) {

            boolean setOtherComponentBeforeShippedFlag = false;

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd"   , glblCmpyCd);
            queryParam.put("shpgStsCd"    , SHPG_STS.SHIPPED);
            queryParam.put("trxHdrNum"    , recordBean.getTrxHdrNum());
            queryParam.put("setShpgPlnNum", recordBean.getShpgPlnNum());
            queryParam.put("invtyCtrlFlg" , Y);

            String result = (String) ssmBatchClient.queryObject("isSetOtherComponentBeforeShippedForSet", queryParam);
            if (null == result) {
                setOtherComponentBeforeShippedFlag = false;
            } else {
                setOtherComponentBeforeShippedFlag = true;
            }

            return setOtherComponentBeforeShippedFlag;
        }
        private boolean isSalesBomMainBaseCompNotShipped (NLBB206001Bean recordBean) {
            boolean salesBomMainBaseCompNotShippedFlag = true;

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd"   , glblCmpyCd);
            queryParam.put("trxHdrNum"    , recordBean.getTrxHdrNum());
            String bomPosn = recordBean.getTrxLineNum() + recordBean.getTrxLineSubNum();
            queryParam.put("dsOrdPosnNum", bomPosn);
            queryParam.put("setShpgPlnNum", recordBean.getShpgPlnNum());
            queryParam.put("flgOnY" , Y);

            List<String> shpgStsCdList = new ArrayList<String>();
            shpgStsCdList.add(SHPG_STS.SHIPPED);
            shpgStsCdList.add(SHPG_STS.ARRIVED);
            shpgStsCdList.add(SHPG_STS.N_INVOICE_READY);
            shpgStsCdList.add(SHPG_STS.INSTALLED);
            shpgStsCdList.add(SHPG_STS.INVOICED);
            queryParam.put("shpgStsCdList", shpgStsCdList);

            String result = (String) ssmBatchClient.queryObject("isNotShippedBomMain", queryParam);

            if (null == result) {
                salesBomMainBaseCompNotShippedFlag = false;
            }
            return salesBomMainBaseCompNotShippedFlag;
        }
        // 2013/07/29 R-OM028 Add End
        // 2015/09/18 CSA add start
//        private String getShippingStatusFromReferenceLine(SHPG_PLNTMsg shpgPlnTmsg) {
        private String getShippingStatusFromReferenceLine(CPO_DTLTMsg dsCpoDtlTMsg, SHPG_PLNTMsg shpgPlnTMsg, NLBB206001Bean recordBean) {

            String refShpgStsCd = null;
            Map<String, String> refShpgPlnMap = new HashMap<String, String>();
            //get INVTY_CTRL_FLG
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd",   glblCmpyCd);
            ssmParam.put("cpoOrdNum",        dsCpoDtlTMsg.cpoOrdNum.getValue());
            ssmParam.put("cpoDtlLineNum",    dsCpoDtlTMsg.refCpoDtlLineNum.getValue());
            ssmParam.put("cpoDtlLineSubNum", dsCpoDtlTMsg.refCpoDtlLineSubNum.getValue());
//QC#5910 add Start
            ssmParam.put("crRebillDummyWhCdList", crRebillDummyWhCdList);
//QC#5910 add End
            String refLineInvtyCtrlFlg = (String) ssmBatchClient.queryObject("getRefLineInvtyCtrlFlg", ssmParam);

            if (ZYPCommonFunc.hasValue(refLineInvtyCtrlFlg) && ZYPConstant.FLG_ON_Y.equals(refLineInvtyCtrlFlg)) {
                // Reference line is tangible
                ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd",       glblCmpyCd);
//            ssmParam.put("cpoOrdNum",        shpgPlnTmsg.trxHdrNum.getValue());
//            ssmParam.put("cpoDtlLineNum",    shpgPlnTmsg.trxLineNum.getValue());
//            ssmParam.put("cpoDtlLineSubNum", shpgPlnTmsg.trxLineSubNum.getValue());
                ssmParam.put("trxHdrNum",        dsCpoDtlTMsg.cpoOrdNum.getValue());
                ssmParam.put("trxLineNum",       dsCpoDtlTMsg.refCpoDtlLineNum.getValue());
                ssmParam.put("trxLineSubNum",    dsCpoDtlTMsg.refCpoDtlLineSubNum.getValue());
                List<String> shpgStsCdList = new ArrayList<String>();
                shpgStsCdList.add(SHPG_STS.SHIPPED);
                shpgStsCdList.add(SHPG_STS.ARRIVED);
                //QC#10964 add Start
                shpgStsCdList.add(SHPG_STS.INSTALLED);
                shpgStsCdList.add(SHPG_STS.INVOICED);
                //QC#10964 add End
                ssmParam.put("shpgStsCdList",    shpgStsCdList.toArray(new String[0]));
                
//                List<String> refShpgStsCdList = (List<String>) ssmBatchClient.queryObjectList("getReferenceShippingPlanStatus", ssmParam);
                List<Map<String, String>> refShpgPlanMapList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getReferenceShippingPlanStatus", ssmParam);
                if (refShpgPlanMapList.isEmpty()) {
                    // reference line is not found
                    return null;
                } else {
                    refShpgPlnMap = (Map<String, String>) refShpgPlanMapList.get(0);
                    refShpgStsCd = refShpgPlnMap.get("SHPG_STS_CD");
                    //QC#10964 add Start
                    if (SHPG_STS.INVOICED.equals(refShpgStsCd)) {
                        refShpgStsCd = getShippingStatusFromOrder(shpgPlnTMsg);
                    }
                    //QC#10964 add End
                    recordBean.setSoNum(refShpgPlnMap.get("SO_NUM"));
                    recordBean.setBolNum(refShpgPlnMap.get("BOL_NUM"));
                    recordBean.setProNum(refShpgPlnMap.get("PRO_NUM"));
                    //QC#12003 add Start
                    recordBean.setActlShipDt(refShpgPlnMap.get("ACTL_SHIP_DT"));
                    //QC#12003 add End
                }
            } else {
                // reference line is intangible
                refShpgStsCd = getShippingStatusFromOrder(shpgPlnTMsg);
            }

            return refShpgStsCd;
        }

        private String getShippingStatusFromOrder(SHPG_PLNTMsg shpgPlnTmsg) {

            CPOTMsg cpoTmsg = getCpo(shpgPlnTmsg.trxHdrNum.getValue());

            String shpgStsCd = null;
            if (REV_RECOG_METH.BOL.equals(cpoTmsg.revRecogMethCd.getValue())) {
                shpgStsCd = SHPG_STS.SHIPPED;

            } else if (REV_RECOG_METH.POD.equals(cpoTmsg.revRecogMethCd.getValue())) {
                shpgStsCd = SHPG_STS.ARRIVED;
            }

            return shpgStsCd;
        }
        //QC#2836
        private boolean judgeSoClose(SHPG_PLNTMsg shpgPlnTMsg) {

            boolean soClose = false;
            CPOTMsg cpoTMsg = getCpo(shpgPlnTMsg.trxHdrNum.getValue());

            REV_RECOG_SHPG_STSTMsg revRecogShpgStsTMsg = new REV_RECOG_SHPG_STSTMsg();
            ZYPEZDItemValueSetter.setValue(revRecogShpgStsTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(revRecogShpgStsTMsg.revRecogMethCd, cpoTMsg.revRecogMethCd.getValue());
            ZYPEZDItemValueSetter.setValue(revRecogShpgStsTMsg.shpgStsCd, shpgPlnTMsg.shpgStsCd.getValue());
            revRecogShpgStsTMsg = (REV_RECOG_SHPG_STSTMsg) S21CacheTBLAccessor.findByKey(revRecogShpgStsTMsg);

            if (revRecogShpgStsTMsg != null) {
                soClose = true;
            }

            return soClose;
        }

        private CPOTMsg getCpo(String cpoOrdNum) {
            CPOTMsg cpoTmsg = new CPOTMsg();
            cpoTmsg.glblCmpyCd.setValue(glblCmpyCd);
            cpoTmsg.cpoOrdNum.setValue(cpoOrdNum);
            cpoTmsg = (CPOTMsg) S21CacheTBLAccessor.findByKey(cpoTmsg);
            return cpoTmsg;
        }

        private boolean setBillingHold(NLBB206001Bean recordBean) {

            boolean isSuccess = true;

            //QC#17206 add Start
            if (!SHPG_STS.ARRIVED.equals(recordBean.getUpdShpgStsCd())) {
                return true;
            }
            //QC#17206 add End
            // judgement billing hold target
            String cpoOrdNum = recordBean.getTrxHdrNum();
//            Map<String, String> queryParam = new HashMap<String, String>();
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd",     glblCmpyCd);
            queryParam.put("cpoOrdNum",      cpoOrdNum);
            queryParam.put("revRecogMethCd", REV_RECOG_METH.POD);
            queryParam.put("dsCpoConfigPk",  recordBean.getDsCpoConfigPk().toString());
            queryParam.put("svcIstlReqFlg",  Y);
//CSA 2015/01/06 QC#2648 Start
            queryParam.put("hldRsnCd",       HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
            queryParam.put("relFlg",         N);
//CSA 2015/01/06 QC#2648 End
//QC#5910 add Start
            queryParam.put("crRebillDummyWhCdList", crRebillDummyWhCdList);
//QC#5910 add End
//QC#29488 Add Start
            queryParam.put("cpoOrdLineNum", recordBean.getTrxLineNum());
            queryParam.put("cpoOrdLineSubNum", recordBean.getTrxLineSubNum());
            queryParam.put("shpgPlnNum", recordBean.getShpgPlnNum());
//QC#29488 Add End
            Integer result = (Integer) ssmBatchClient.queryObject("checkBillingHoldTarget", queryParam);
            if (result == null || result.intValue() == 0) {
                return true;
            }

            // call add hold api
            NWZC044001PMsg pmsg = new NWZC044001PMsg();
            pmsg.glblCmpyCd.setValue(glblCmpyCd);
            pmsg.cpoOrdNum.setValue(recordBean.getTrxHdrNum());
//CSA 2015/01/04 QC#2648 Start
            // -> S21_NA#14557-2 QC#2648 cancel start
            pmsg.cpoDtlLineNum.setValue(recordBean.getTrxLineNum());
            pmsg.cpoDtlLineSubNum.setValue(recordBean.getTrxLineSubNum());
            pmsg.shpgPlnNum.setValue(recordBean.getShpgPlnNum());
//CSA 2015/01/04 QC#2648 End
            // -> S21_NA#14557-2 QC#2648 cancel end
            pmsg.hldRsnCd.setValue(HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
            pmsg.slsDt.setValue(salesDate);
            
            ADD_HOLD_API.execute(pmsg, ONBATCH_TYPE.BATCH);

            if (pmsg.xxMsgIdList.getValidCount() > 0) {
                for (int n = 0; n < pmsg.xxMsgIdList.getValidCount(); n++) {
                    String errId = pmsg.xxMsgIdList.no(n).xxMsgId.getValue();
                    S21InfoLogOutput.println(errId);
                    isSuccess = false;
                }
            }
            return isSuccess;
        }

        private boolean updateMachineMaster(NLBB206001Bean recordBean) {
            
            boolean isSuccess = true;
            BigDecimal dsCpoConfigPk = recordBean.getDsCpoConfigPk();
            // processing for each config
            if (!procDsCpoCofgiPk.contains(dsCpoConfigPk)) {

                procDsCpoCofgiPk.add(dsCpoConfigPk);
                // Check whether the target
                Map<String, String> queryParam = new HashMap<String, String>();
                queryParam.put("glblCmpyCd",          glblCmpyCd);
                queryParam.put("dsCpoConfigPk",       dsCpoConfigPk.toString());
                queryParam.put("excludeOrdLineStsCd", ORD_LINE_STS.CANCELLED);
                queryParam.put("excludeShpgStsCd",    SHPG_STS.CANCELLED);
                queryParam.put("invtyCtrlFlg",        Y);
                queryParam.put("instlBaseCtrlFlg",    Y);

                BigDecimal svcMachMstrPk = (BigDecimal) ssmBatchClient.queryObject("checkMachineMasterUpdateTarget", queryParam);
                if (svcMachMstrPk == null) {
                    return true;
                }

                // update DS_CPO_CONFIG
                // select for update
                DS_CPO_CONFIGTMsg dsCpoConfigTmsg = new DS_CPO_CONFIGTMsg();
                dsCpoConfigTmsg.glblCmpyCd.setValue(glblCmpyCd);
                dsCpoConfigTmsg.dsCpoConfigPk.setValue(dsCpoConfigPk);
                dsCpoConfigTmsg = (DS_CPO_CONFIGTMsg) EZDTBLAccessor.findByKeyForUpdate(dsCpoConfigTmsg);
                if (dsCpoConfigTmsg == null)  {
                    S21InfoLogOutput.println(MSG_ID.ZZOM0011E.toString());
                    debug(getClass(), dsCpoConfigPk);
                    return false;
                }
                // update
                dsCpoConfigTmsg.svcConfigMstrPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ));
                EZDTBLAccessor.update(dsCpoConfigTmsg);
                String rtnCd = dsCpoConfigTmsg.getReturnCode();
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    S21InfoLogOutput.println(MSG_ID.ZZSM4101E.toString(), toArray("to Update ", "DS_CPO_CONFIG", " DS_CPO_CONFIG_PK=", dsCpoConfigPk.toString()));
                }

                // update SVC_MACH_MSTR
                // select for update
                SVC_MACH_MSTRTMsg svcMachMstrTmsg = new SVC_MACH_MSTRTMsg();
                svcMachMstrTmsg.glblCmpyCd.setValue(glblCmpyCd);
                svcMachMstrTmsg.svcMachMstrPk.setValue(svcMachMstrPk);
                svcMachMstrTmsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(svcMachMstrTmsg);
                if (svcMachMstrTmsg == null)  {
                    S21InfoLogOutput.println(MSG_ID.ZZOM0011E.toString());
                    debug(getClass(), svcMachMstrPk);
                    return false;
                }
// START 2016/11/24 N.Arai [QC#15829, MOD]
                String beforSvcMachMstrStsCd = svcMachMstrTmsg.svcMachMstrStsCd.getValue();
// END 2016/11/24 N.Arai [QC#15829, MOD]
                // update
                svcMachMstrTmsg.svcMachMstrStsCd.setValue(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
                EZDTBLAccessor.update(svcMachMstrTmsg);
                rtnCd = svcMachMstrTmsg.getReturnCode();
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    S21InfoLogOutput.println(MSG_ID.ZZSM4101E.toString(), toArray("to Update ", "SVC_MACH_MSTR", " SVC_MACH_MSTR_PK=", svcMachMstrPk.toString()));
                }
// START 2016/11/24 N.Arai [QC#15829, MOD]
                 if (!createSvcMachMstrTrk(svcMachMstrPk, beforSvcMachMstrStsCd, svcMachMstrTmsg.svcMachMstrStsCd.getValue())) {
                     return false;
                 }
// END 2016/11/24 N.Arai [QC#15829, MOD]
            }
            
            return isSuccess;
        }
        // 2015/09/18 CSA add end
        // CSA QC#2846 add Start
        private void setTangibleCompornentShippingInfo(SHPG_PLNTMsg shpgPlnTMsg, NLBB206001Bean recordBean) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("trxHdrNum", recordBean.getTrxHdrNum());
            ssmParam.put("setShpgPlnNum", recordBean.getSetShpgPlnNum());
            List<String> shpgStsCdList = new ArrayList<String>();
            shpgStsCdList.add(SHPG_STS.SHIPPED);
            shpgStsCdList.add(SHPG_STS.ARRIVED);
            ssmParam.put("shpgStsCdList",    shpgStsCdList.toArray(new String[0]));
            ssmParam.put("invtyCtrlFlg", ZYPConstant.FLG_ON_Y);
            Map<String, String> mapRes = (Map<String, String>) ssmBatchClient.queryObject("getTangibleCompornentInfo", ssmParam);
            if (mapRes != null) {
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.shpgStsCd, mapRes.get("SHPG_STS_CD"));
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.soNum, mapRes.get("SO_NUM"));
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.proNum, mapRes.get("PRO_NUM"));
                ZYPEZDItemValueSetter.setValue(shpgPlnTMsg.bolNum, mapRes.get("BOL_NUM"));
            }
        }
        // CSA QC#2846 add End
     }

    private boolean isShippedCompletion(SHPG_PLNTMsg shpgPlnTMsg) {

      boolean isShipped = false;

      Map<String, String> queryParam = new HashMap<String, String>();
      queryParam.put("glblCmpyCd" , glblCmpyCd);
      queryParam.put("trxHdrNum"     , shpgPlnTMsg.trxHdrNum.getValue());
      queryParam.put("trxLineNum"    , shpgPlnTMsg.trxLineNum.getValue());
      queryParam.put("trxLineSubNum" , shpgPlnTMsg.trxLineSubNum.getValue());
      queryParam.put("trxSrcTpCd"    , TRX_SRC_TP.WHOLE_SALES);

      Map<String, BigDecimal> result = (Map<String, BigDecimal>) ssmBatchClient.queryObject("isShippedCompletion", queryParam);
      if (null == result) {
          isShipped = false;
      } else {
          BigDecimal shpgPlnCnt      = result.get("SHPG_PLN_CNT");
          BigDecimal shpgStsCdCmpCnt = result.get("SHPG_STS_CD_CMP_CNT");

          if (!shpgPlnCnt.equals(BigDecimal.ZERO) && shpgPlnCnt.equals(shpgStsCdCmpCnt)) {
              isShipped = true;
          } else {
              isShipped = false;
          }
      }

      return isShipped;
    }

    private String nullToString(String prm) {
        if (ZYPCommonFunc.hasValue(prm)) {
            return prm;
        } else {
            return String.valueOf("");
        }
    }

    private BigDecimal nullToBigDecimal(BigDecimal prm) {
        if (null == prm) {
            return BigDecimal.ZERO;
        } else {
            return prm;
        }
    }

    private static void debug(Class caller, Object... debugInfos) {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("<<").append(caller.getName()).append(">> ");
        for (Object debugInfo : debugInfos) {
            sb.append(debugInfo);
        }
        S21InfoLogOutput.println(sb.toString());
    }

    private static void debugMethodEnd(Class caller, String methodNm) {
        debug(caller, new StringBuilder().append("[E N D]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private static void debugMethodStart(Class caller, String methodNm) {
        debug(caller, new StringBuilder().append("[START]").append("******************** method : [").append(methodNm).append("]").toString());
    }

    private String[] toArray(String ...appendMsgList) {
        String[] msgArray = new String[appendMsgList.length];
        for (int i = 0; i < msgArray.length; i++) {
            msgArray[i] = appendMsgList[i];
        }
        return msgArray;
    }

    private Integer getIntegerVal(String numString) {
        Integer rslt = 0;
        try {
            rslt = Integer.parseInt(numString);
        } catch (Exception ex) {
            rslt = 0;
        }
        return rslt;
    }

    private void outProcessLog(NLBB206001Bean param, String eventID, SHPG_PLNTMsg shpgPlnMsg) {

        S21BusinessProcessLogMsg bizProcLogMsg = new S21BusinessProcessLogMsg();
        bizProcLogMsg.setSubSysId(SUBSYSID);
        bizProcLogMsg.setProcId(PROCID);
        bizProcLogMsg.setEventId(eventID);
        bizProcLogMsg.setDocTpCd(DOCTPCD);
        bizProcLogMsg.setDocId(ZYPCommonFunc.concatString(shpgPlnMsg.trxLineNum.getValue(), ".", shpgPlnMsg.trxLineSubNum.getValue()));
        bizProcLogMsg.setPrntDocId(shpgPlnMsg.trxHdrNum.getValue());
        S21BusinessProcessLog.print(bizProcLogMsg);
    }

    // Delete Start 2013/03/21 Defect#592
//    private String getCPOExistenceFlagKeyTransactionSourceTypeCode(String glblCmpyCd, String trxSrcTpCd) {
//
//        TRX_SRC_TPTMsg tMsg = new TRX_SRC_TPTMsg();
//        tMsg.glblCmpyCd.setValue(glblCmpyCd);
//        tMsg.trxSrcTpCd.setValue(trxSrcTpCd);
//        tMsg = (TRX_SRC_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
//        return tMsg.cpoExstFlg.getValue();
//    }
    // Delete End   2013/03/21 Defect#592

// START 2016/11/24 N.Arai [QC#15829, MOD]
     private boolean createSvcMachMstrTrk(BigDecimal svcMachMstrPk, String beforSvcMachMstrStsCd, String newSvcMachMstrStsCd) {

         if (newSvcMachMstrStsCd.equals(beforSvcMachMstrStsCd)) {
             return true;
         }

         BigDecimal svcMachMstrTrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ);
         SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();
         setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
         setValue(tMsg.svcMachMstrTrkPk, svcMachMstrTrkPk);
         setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
         setValue(tMsg.trxRgtnDt, this.salesDate);
         setValue(tMsg.updFldTxt, "SVC_MACH_MSTR_STS_CD");
         setValue(tMsg.oldValTxt, beforSvcMachMstrStsCd);
         setValue(tMsg.newValTxt, newSvcMachMstrStsCd);
         setValue(tMsg.updUsrId, BATCH_ID);
         setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
         EZDTBLAccessor.create(tMsg);
         String rtnCd = tMsg.getReturnCode();
         if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
             S21InfoLogOutput.println(MSG_ID.ZZSM4101E.toString(), toArray("to Update ", "SVC_MACH_MSTR_TRK", " SVC_MACH_MSTR_TRK_PK=", svcMachMstrTrkPk.toString()));
             return false;
         }
         return true;
     }
// END 2016/11/24 N.Arai [QC#15829, MOD]
}

