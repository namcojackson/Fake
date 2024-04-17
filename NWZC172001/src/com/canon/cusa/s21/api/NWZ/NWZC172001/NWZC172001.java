package com.canon.cusa.s21.api.NWZ.NWZC172001;

import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.BLANK;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.EQUIPMENT_ORDER_VALUE_SET;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.LINE;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM0157E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM0163E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM0209E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM0996E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1143E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1253E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1303E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1567E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1597E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1761E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1762E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1763E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1764E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1765E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1766E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1767E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1768E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1770E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1771E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1772E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1773E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1774E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM1786E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM2055E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM2056E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM2227E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.NWZM2228E;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.RQST_TP_CTAC_PSN_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.RQST_TP_SCHD;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.RQST_TP_SLS_CR_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.RQST_TP_TMPL;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.SHPG_CMT_TXT_LIMIT_SIZE;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.SPACE;
import static com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant.SUPPLIES_ORDER_VALUE_SET;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

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

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CONFIGTMsgArray;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.SCHD_CRAT_TMPLTMsg;
import business.db.SCHD_CRAT_TMPL_LINETMsg;
import business.db.SCHD_CRAT_TMPL_LINETMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_INTVLTMsg;
import business.db.SPLY_AGMT_PLNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC611001PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC171001PMsg;
import business.parts.NWZC171001_CPMsg;
import business.parts.NWZC171001_DPMsg;
import business.parts.NWZC171001_EPMsg;
import business.parts.NWZC171002PMsg;
import business.parts.NWZC171003PMsg;
import business.parts.NWZC172001PMsg;
import business.parts.NWZC172002PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC171001.NWZC171001;
import com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_DOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Creation of Schedule via the Deal Configurator API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/23   Fujitsu         Y.Taoka         Create          N/A
 * 2016/07/25   Fujitsu         T.Murai         Update          S21_NA#11463
 * 2016/08/23   Fujitsu         H.Ikeda         Update          S21_NA#12778
 * 2016/09/02   Fujitsu         Y.Kanefusa      Update          S21_NA#11654
 * 2016/11/07   Fujitsu         H.Ikeda         Update          S21_NA#15763
 * 2016/12/15   Fujitsu         S.Iidaka        Update          S21_NA#16111
 * 2017/03/02   Fujitsu         N.Aoyama        Update          S21_NA#16575
 * 2017/03/07   Fujitsu         S.Iidaka        Update          S21_NA#17668
 * 2017/03/09   Fujitsu         S.Iidaka        Update          S21_NA#17701
 * 2017/03/16   Fujitsu         S.Iidaka        Update          S21_NA#18024
 * 2017/05/09   Hitachi         T.Tomita        Update          S21_NA:RS#8416
 * 2017/08/04   Fujitsu         W.Honda         Update          S21_NA#20228
 * 2017/09/26   Fujitsu         S.Iidaka        Update          S21_NA#21193
 * 2017/10/24   Fujitsu         K.Ishizuka      Update          S21_NA#21564
 * 2017/12/22   Fujitsu         N.Sugiura       Update          S21_NA#20164
 * 2018/02/08   Fujitsu         K.Ishizuka      Update          S21_NA#20297(Sol#379)
 * 2018/05/24   Fujitsu         M.Ohno          Update          S21_NA#26148
 * 2018/06/25   Fujitsu         M.Ohno          Update          S21_NA#23726
 * 2018/12/13   Fujitsu         M.Ohno          Update          S21_NA#29315
 * 2020/03/10   Fujitsu         C.Hara          Update          QC#56125
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 *</pre>
 */
public class NWZC172001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    // Add Start 2017/08/04 S21_NA#20228
    /** Standard CCY */
    private String stdCcyCd;
    // Add End 2017/08/04 S21_NA#20228

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType;

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /**
     * constructor
     */
    public NWZC172001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsg NWZC172001PMsg
     * @param linePMsgList List<NWZC172002PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWZC172001PMsg pMsg, List<NWZC172002PMsg> linePMsgList, final ONBATCH_TYPE onBatchType) {
        this.msgMap = new S21ApiMessageMap(pMsg);
        this.onBatchType = onBatchType;
        doProcess(pMsg, linePMsgList);

        msgMap.flush();
        flushListMsgMap(linePMsgList);
    }

    /**
     * doProcess
     * @param param NWZC172001PMsg
     * @param lineParams List<NWZC172002PMsg>
     */
    private void doProcess(NWZC172001PMsg pMsg, List<NWZC172002PMsg> linePMsgList) {
        // Add Start 2017/08/04 S21_NA#20228
        this.stdCcyCd = getStdCcyCd(pMsg.glblCmpyCd.getValue());
        // Add End 2017/08/04 S21_NA#20228

        // Check Parameter
        if (!checkParam(pMsg, linePMsgList)) {
            return;
        }

        if (RQST_TP_TMPL.equals(pMsg.xxRqstTpCd.getValue())) {
            // Insert SCHD_CRAT_TMPL and SCHD_CRAT_TMPL_LINE 
            createTemplate(pMsg, linePMsgList);

        } else if (RQST_TP_SCHD.equals(pMsg.xxRqstTpCd.getValue())) {
            createSchduleAgreement(pMsg, linePMsgList);
        }
    }

    /**
     * checkParam
     * @param pMsg NWZC172001PMsg
     * @param linePMsgList List<NWZC172002PMsg>
     * @return boolean
     */
    private boolean checkParam(NWZC172001PMsg pMsg, List<NWZC172002PMsg> linePMsgList) {

        // Global Compny Code
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0163E);
        }
        // Request Type
        if (!ZYPCommonFunc.hasValue(pMsg.xxRqstTpCd)) {
            msgMap.addXxMsgId(NWZM0157E);

        } else if (!RQST_TP_TMPL.equals(pMsg.xxRqstTpCd.getValue()) && !RQST_TP_SCHD.equals(pMsg.xxRqstTpCd.getValue())) {
            msgMap.addXxMsgId(NWZM0209E);
        }
        // Contract PK
        if (!ZYPCommonFunc.hasValue(pMsg.dsContrPk)) {
            msgMap.addXxMsgId(NWZM2227E);
        }
        // Reference CPO Order Number
        if (!ZYPCommonFunc.hasValue(pMsg.refCpoOrdNum)) {
            msgMap.addXxMsgId(NWZM1597E);
        }
        // If Request Type is Template.
        if (RQST_TP_TMPL.equals(pMsg.xxRqstTpCd.getValue())) {
            // Length of the contract
//            if (!ZYPCommonFunc.hasValue(pMsg.lgContrDaysAot)) {
//                msgMap.addXxMsgId(NWZM1760E);
//            }
            // DS Order Reason 
            if (!ZYPCommonFunc.hasValue(pMsg.dsOrdTpCd)) {
                msgMap.addXxMsgId(NWZM1253E);
            }

        } else if (RQST_TP_SCHD.equals(pMsg.xxRqstTpCd.getValue())) {
            //// If Request Type is Schelude.
            // Model
            if (!ZYPCommonFunc.hasValue(pMsg.mdlId)) {
                msgMap.addXxMsgId(NWZM1567E);
            }
            // Serial Number   
            if (!ZYPCommonFunc.hasValue(pMsg.serNum)) {
                msgMap.addXxMsgId(NWZM1761E);
            }
            // Service Machine Master PK      
            if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                msgMap.addXxMsgId(NWZM1762E);
            }
            // Configuration ID     
            if (!ZYPCommonFunc.hasValue(pMsg.svcConfigMstrPk)) {
                msgMap.addXxMsgId(NWZM1303E);
            }
            // Contract Number
            if (!ZYPCommonFunc.hasValue(pMsg.dsContrNum)) {
                msgMap.addXxMsgId(NWZM1143E);
            }
            // Contract Sequence Number
            if (!ZYPCommonFunc.hasValue(pMsg.dsContrSqNum)) {
                msgMap.addXxMsgId(NWZM1763E);
            }
            // Contract Start Date 
            if (!ZYPCommonFunc.hasValue(pMsg.schdAgmtVldFromDt)) {
                msgMap.addXxMsgId(NWZM1764E);
            } else if (!checkDtFormat(pMsg.schdAgmtVldFromDt.getValue())) {
                msgMap.addXxMsgId(NWZM1766E);
            }

            // Contract End Date 
            if (!ZYPCommonFunc.hasValue(pMsg.schdAgmtVldThruDt)) {
                msgMap.addXxMsgId(NWZM1765E);
            } else if (!checkDtFormat(pMsg.schdAgmtVldThruDt.getValue())) {
                msgMap.addXxMsgId(NWZM1767E);
            }

            if (ZYPCommonFunc.hasValue(pMsg.schdAgmtVldFromDt) && ZYPCommonFunc.hasValue(pMsg.schdAgmtVldThruDt)) {
                if (0 < pMsg.schdAgmtVldFromDt.getValue().compareTo(pMsg.schdAgmtVldThruDt.getValue())) {
                    msgMap.addXxMsgId(NWZM1786E);
                }
            }
            // Contract Detail PK
            if (!ZYPCommonFunc.hasValue(pMsg.dsContrDtlPk)) {
                msgMap.addXxMsgId(NWZM2228E);
            }
        }

        // Detail List
        if (RQST_TP_TMPL.equals(pMsg.xxRqstTpCd.getValue())) {
            if (linePMsgList == null || linePMsgList.size() == 0) {
                // Schedule List is required.
                msgMap.addXxMsgId(NWZM1768E);

            } else {
                for (NWZC172002PMsg linePMs : linePMsgList) {
                    S21ApiMessageMap lineMsgMap = new S21ApiMessageMap(linePMs);

                    // Merchandise Code
                    if (!ZYPCommonFunc.hasValue(linePMs.mdseCd)) {
                        lineMsgMap.addXxMsgId(NWZM0996E);
                    }
                    // If Request Type is Template.
                    if (RQST_TP_TMPL.equals(pMsg.xxRqstTpCd.getValue())) {
                        // Quantity Allowed
                        if (!ZYPCommonFunc.hasValue(linePMs.schdAllwQty)) {
                            lineMsgMap.addXxMsgId(NWZM1770E);
                        }
                        // Shipping Intervals
                        if (!ZYPCommonFunc.hasValue(linePMs.shpgIntvlCd)) {
                            lineMsgMap.addXxMsgId(NWZM1771E);
                        }
                        // Amount of Ship for each RDD
                        if (!ZYPCommonFunc.hasValue(linePMs.otmShipQty)) {
                            lineMsgMap.addXxMsgId(NWZM1772E);
                        }
                        // Contract Detail PK
                        if (!ZYPCommonFunc.hasValue(linePMs.dsContrDtlPk)) {
                            lineMsgMap.addXxMsgId(NWZM2228E);
                        }
                    }
                    lineMsgMap.flush();
                }
            }
        }

        if (hasError(pMsg) || hasError(linePMsgList)) {
            return false;
        }
        return true;
    }

    /**
     * checkDtFormat
     * @param dt String
     * @return boolean
     */
    private boolean checkDtFormat(String dt) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            format.parse(dt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * createTemplate
     * @param pMsg NWZC172001PMsg
     * @param linePMsgList  List<NWZC172002PMsg>
     */
    private void createTemplate(NWZC172001PMsg pMsg, List<NWZC172002PMsg> linePMsgList) {

        // Add Start 2017/03/09 S21_NA#17701
        // Delete SCHD_CRAT_TMPL_LINE
        if (linePMsgList != null && linePMsgList.size() > 0) {
            NWZC172002PMsg line = linePMsgList.get(0);
            String[] condList = new String[] {"glblCmpyCd", "dsContrDtlPk" };

            SCHD_CRAT_TMPL_LINETMsg delTmplLine = new SCHD_CRAT_TMPL_LINETMsg();

            delTmplLine.setSQLID("001");
            delTmplLine.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            delTmplLine.setConditionValue("dsContrDtlPk01", line.dsContrDtlPk.getValue());
            SCHD_CRAT_TMPL_LINETMsgArray delTmplLineAry = (SCHD_CRAT_TMPL_LINETMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(delTmplLine);

            if (delTmplLineAry.length() > 0) {
                delTmplLine.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                delTmplLine.dsContrDtlPk.setValue(line.dsContrDtlPk.getValue());
                int res = S21FastTBLAccessor.logicalRemoveByPartialValue(delTmplLine, condList);

                if (res < 0) {
                    msgMap.addXxMsgId(NWZM2056E);
                    return;
                }

                // Delete SCHD_CRAT_TMPL
                SCHD_CRAT_TMPLTMsg delTmpl = new SCHD_CRAT_TMPLTMsg();
                delTmpl.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
                delTmpl.schdCratTmplPk.setValue(delTmplLineAry.no(0).schdCratTmplPk.getValue());

                delTmpl = (SCHD_CRAT_TMPLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(delTmpl);
                if (delTmpl != null) {
                    S21ApiTBLAccessor.logicalRemove(delTmpl);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(delTmpl.getReturnCode())) {
                        msgMap.addXxMsgId(NWZM2055E);
                        return;
                    }
                }
            }
        }
        // Add End   2017/03/09 S21_NA#17701

        // Insert SCHD_CRAT_TMPL
        SCHD_CRAT_TMPLTMsg schdCratTmpl = new SCHD_CRAT_TMPLTMsg();

        ZYPEZDItemValueSetter.setValue(schdCratTmpl.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_CRAT_TMPL_SQ);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.schdCratTmplPk, pk);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.dsContrPk, pMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.refCpoOrdNum, pMsg.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.lgContrDaysAot, pMsg.lgContrDaysAot);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.wtyPerDaysAot, pMsg.wtyPerDaysAot);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.custIssPoNum, pMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.custIssPoDt, pMsg.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.sysSrcCd, pMsg.sysSrcCd);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.sysSrcRefNum, pMsg.sysSrcRefNum);
        // Add Start 2016/07/28 S21_NA#11463
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.cpoSrcTpCd, pMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.ordSrcRefNum, pMsg.ordSrcRefNum);
        // Add End 2016/07/28 S21_NA#11463
        String currentSystemTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.schdCratTs, currentSystemTime);
        // Add Start 2016/12/15 S21_NA#16111
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.splyBaseAmt, pMsg.splyBaseAmt);
        ZYPEZDItemValueSetter.setValue(schdCratTmpl.qtyContrCapQty, pMsg.qtyContrCapQty);
        // Add End   2016/12/15 S21_NA#16111

        //// insert
        S21FastTBLAccessor.insert(schdCratTmpl);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(schdCratTmpl.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1773E);
            return;
        }
        // Set output parameter 
        ZYPEZDItemValueSetter.setValue(pMsg.schdCratTmplPk, pk);

        // Insert SCHD_CRAT_TMPL_LINE
        BigDecimal linenum = BigDecimal.ZERO;
        for (NWZC172002PMsg linePMsg : linePMsgList) {
            SCHD_CRAT_TMPL_LINETMsg schdCratTmplLine = new SCHD_CRAT_TMPL_LINETMsg();

            linenum = linenum.add(BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(schdCratTmplLine.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(schdCratTmplLine.schdCratTmplPk, pk);
            ZYPEZDItemValueSetter.setValue(schdCratTmplLine.schdCratTmplLineNum, linenum);
            ZYPEZDItemValueSetter.setValue(schdCratTmplLine.dsContrDtlPk, pMsg.dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(schdCratTmplLine.mdseCd, linePMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(schdCratTmplLine.schdAllwQty, linePMsg.schdAllwQty);
            ZYPEZDItemValueSetter.setValue(schdCratTmplLine.shpgIntvlCd, linePMsg.shpgIntvlCd);
            ZYPEZDItemValueSetter.setValue(schdCratTmplLine.otmShipQty, linePMsg.otmShipQty);
            // Add Start 2017/03/07 S21_NA#17668
            ZYPEZDItemValueSetter.setValue(schdCratTmplLine.firstShipQty, linePMsg.firstShipQty);
            // Add End   2017/03/07 S21_NA#17668

            //// insert
            S21FastTBLAccessor.insert(schdCratTmplLine);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(schdCratTmplLine.getReturnCode())) {
                S21ApiMessageMap lineMsgMap  = new S21ApiMessageMap(linePMsg);
                lineMsgMap.addXxMsgId(NWZM1774E);
                return;
            }
        }
    }

    /**
     * createSchduleAgreement
     * @param pMsg NWZC172001PMsg
     * @param linePMsgList List<NWZC172002PMsg> 
     */
    private void createSchduleAgreement(NWZC172001PMsg pMsg, List<NWZC172002PMsg> linePMsgList) {
        // Get SCHD_CRAT_TMPL
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrPk", pMsg.dsContrPk.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("dsOrdLineDrctnOut", DS_ORD_LINE_DRCTN.OUTBOUND);
        // Add Start 2017/03/16 S21_NA#18024
        ssmParam.put("dsContrDtlPk", pMsg.dsContrDtlPk.getValue());
        // Add End   2017/03/16 S21_NA#18024

        List<Map<String, Object>> schdCratTmplDataList = (List<Map<String, Object>>) ssmClient.queryObjectList("getSchdCratTmpl", ssmParam);
        // Mod Start 2016/08/23 S21_NA#12778
        //if (schdCratTmplDataList == null || schdCratTmplDataList.size() == 0 || 1 < schdCratTmplDataList.size()) {
        if (schdCratTmplDataList == null || schdCratTmplDataList.size() == 0) {
        // Mod End 2016/08/23 S21_NA#12778
            // Not found.
            // msgMap.addXxMsgId(NWZM1599E);
            return;
        }

        // Mod Start 2016/08/23 S21_NA#12778
        for (int j = 0; j < schdCratTmplDataList.size(); j++) {
            //Map<String, Object> schdCratTmplData = schdCratTmplDataList.get(0);
            Map<String, Object> schdCratTmplData = schdCratTmplDataList.get(j);

            // Add Start 2017/03/16 S21_NA#18024
            // Get Supply Agreement Doc Type
            BigDecimal schdCratTmplPk = (BigDecimal) schdCratTmplData.get("SCHD_CRAT_TMPL_PK");
            String splyAgmtDocTpCd = getSplyAgmtDocTpCd(pMsg.glblCmpyCd.getValue(), schdCratTmplPk);
            if (!SPLY_AGMT_DOC_TP.SCHEDULING_AGREEMENT.equals(splyAgmtDocTpCd)) {
                continue;
            }
            // Add End   2017/03/16 S21_NA#18024

            // Get DS_CPO_CONFIG
            DS_CPO_CONFIGTMsg dsCpoConfig = new DS_CPO_CONFIGTMsg();
            dsCpoConfig.setSQLID("002");
            dsCpoConfig.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            dsCpoConfig.setConditionValue("svcConfigMstrPk01", pMsg.svcConfigMstrPk.getValue());
            DS_CPO_CONFIGTMsgArray dsCpoConfigList = (DS_CPO_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(dsCpoConfig);
            if (dsCpoConfigList == null || dsCpoConfigList.getValidCount() == 0) {
                // Not found.
                // msgMap.addXxMsgId(NWZM1779E);
                return;
            }
            dsCpoConfig = dsCpoConfigList.no(0);

            // QC#16575 2017/03/02 UPD START
            // QC#11654 2016/09/02 Mod Start
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
            svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(svcMachMstrTMsg);
            if (svcMachMstrTMsg == null) {
                return;
            }

            // svcMachMstrTMsg.setSQLID("002");
            // svcMachMstrTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            // svcMachMstrTMsg.setConditionValue("serNum01", pMsg.serNum.getValue());
            // SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(svcMachMstrTMsg);
            // if (svcMachMstrTMsgArray == null || svcMachMstrTMsgArray.getValidCount() == 0) {
            //     return;
            // }
            // svcMachMstrTMsg = svcMachMstrTMsgArray.no(0);

            //
            // SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            // svcMachMstrTMsg.setSQLID("020");
            // svcMachMstrTMsg.setConditionValue("glblCmpyCd01",  pMsg.glblCmpyCd.getValue());
            // svcMachMstrTMsg.setConditionValue("serNum01",  pMsg.serNum.getValue());
            //  svcMachMstrTMsg.setConditionValue("mdseCd01",  pMsg.mdseCd.getValue() + '%');
            //  SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray)  EZDTBLAccessor.findByCondition(svcMachMstrTMsg);
            // if(svcMachMstrTMsgArray == null || svcMachMstrTMsgArray.getValidCount() == 0 ){
            //     return;
            // }
            // svcMachMstrTMsg = svcMachMstrTMsgArray.no(0);

            // QC#11654 2016/09/02 Mod End
            // QC#16575 2017/03/02 UPD E N D

            // GET Payment Term Cash Discount Code
            String pmtTermCashDiscCd = null;
              if (ZYPCommonFunc.hasValue(svcMachMstrTMsg.billToLocNum)) {
                ssmParam = new HashMap<String, Object>();
                ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
                ssmParam.put("billToCustCd", svcMachMstrTMsg.billToLocNum.getValue());

                List<String> pmtTermCashDiscCdList = (List<String>) ssmClient.queryObjectList("getPmtTermCashDiscCd", ssmParam);
                if (pmtTermCashDiscCdList != null && pmtTermCashDiscCdList.size() == 1) {
                    pmtTermCashDiscCd = pmtTermCashDiscCdList.get(0);
                }
            }

            if (pmtTermCashDiscCd == null && ZYPCommonFunc.hasValue(svcMachMstrTMsg.billToAcctNum)) {
                DS_ACCT_CR_PRFLTMsg dsAcctCrPrfl = new DS_ACCT_CR_PRFLTMsg();
                ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.dsAcctNum, svcMachMstrTMsg.billToAcctNum.getValue());
                dsAcctCrPrfl = (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKey(dsAcctCrPrfl);
                if (dsAcctCrPrfl != null) {
                    pmtTermCashDiscCd = dsAcctCrPrfl.pmtTermCashDiscCd.getValue();
                }
            }
            // GET Scheduling Agreement Sales Credit API Parameter
            // List<NMZC260001_defSlsRepListPMsg> defSlsRepList = createSchdAgmSlsCrList(pMsg, svcMachMstrTMsg.curLocAcctNum.getValue(), (String) schdCratTmplData.get("LINE_BIZ_TP_CD"));
            // 2018/05/24 QC#26148 mod start
//            List<Map<String, Object>> slsRepList = getSalesCredit(pMsg.glblCmpyCd.getValue(), dsCpoConfig.cpoOrdNum.getValue(), dsCpoConfig.dsCpoConfigPk.getValue());
            // 2020/04/27 QC#56638 Mod Start
            List<NMZC260001_defSlsRepListPMsg> slsRepList = null;
            CPOTMsg cpo = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpo.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpo.cpoOrdNum, dsCpoConfig.cpoOrdNum);
            cpo = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpo);
            if (cpo != null) {
                slsRepList = createSchdAgmSlsCrList(pMsg, svcMachMstrTMsg.shipToCustCd.getValue(), cpo.soldToCustLocCd.getValue(), schdCratTmplData);
            } else {
                slsRepList = createSchdAgmSlsCrList(pMsg, svcMachMstrTMsg.shipToCustCd.getValue(), dsCpoConfig.billToCustLocCd.getValue(), schdCratTmplData);
            }
            // 2018/05/24 QC#26148 mod end
            // 2020/04/27 QC#56638 Mod End

            // GET Contract Person
            // NWXC021001PMsg ctatPsn = getContractPerson(pMsg, svcMachMstrTMsg.billToAcctNum.getValue(), svcMachMstrTMsg.curLocAcctNum.getValue());
            List<Map<String, Object>> ctatPsnList = getCtacPsn(pMsg.glblCmpyCd.getValue(), dsCpoConfig.cpoOrdNum.getValue(), dsCpoConfig.dsCpoConfigPk.getValue());
            

            // 2020/03/10 QC#56125 Add Start
            // GET SCHD_CRAT_TMPL_LINE
            BigDecimal lineNum = BigDecimal.ONE;
            SCHD_CRAT_TMPL_LINETMsg schdCratTmplLine = new SCHD_CRAT_TMPL_LINETMsg();
            schdCratTmplLine.setSQLID("002");
            schdCratTmplLine.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            schdCratTmplLine.setConditionValue("dsContrDtlPk01", pMsg.dsContrDtlPk.getValue());
            schdCratTmplLine.setConditionValue("schdCratTmplPk01", schdCratTmplData.get("SCHD_CRAT_TMPL_PK"));
            SCHD_CRAT_TMPL_LINETMsgArray schdCratTmplLineList = (SCHD_CRAT_TMPL_LINETMsgArray) EZDTBLAccessor.findByCondition(schdCratTmplLine);
            // 2020/03/10 QC#56125 Add End

            // Create API Parameter for Header
            // Schedule Agreement Update API for Header
            NWZC171001PMsg schdAgmt = new NWZC171001PMsg();
            // 2018/02/08 S21_NA#20297(Sol#379) Mod Start
            //createAPIParamSchdAgtUpdHdr(schdAgmt, pMsg, schdCratTmplData, slsRepList, svcMachMstrTMsg, pmtTermCashDiscCd);
            // 2020/03/10 QC#56125 Mod Start
            // createAPIParamSchdAgtUpdHdr(schdAgmt, pMsg, schdCratTmplData, slsRepList, svcMachMstrTMsg, pmtTermCashDiscCd, dsCpoConfig);
            createAPIParamSchdAgtUpdHdr(schdAgmt, pMsg, schdCratTmplData, slsRepList, svcMachMstrTMsg, pmtTermCashDiscCd, dsCpoConfig, schdCratTmplLineList);
            // 2020/03/10 QC#56125 Mod End
            // 2018/02/08 S21_NA#20297(Sol#379) Mod End

            //// Schedule Agreement Update API for Sales Credit 
            //(schdAgmt, defSlsRepList);
            createAPIParamSchdAgmtUpdSlsCr(schdAgmt, slsRepList);

            //// Schedule Agreement Update API for Contact Person
            //createAPIParamSchdAgmtUpdContactPsn(schdAgmt, ctatPsn);
            createAPIParamSchdAgmtUpdContactPsn(schdAgmt, ctatPsnList);

            // 2020/03/10 QC#56125 Del Start
            // GET SCHD_CRAT_TMPL_LINE
//            BigDecimal lineNum = BigDecimal.ONE;
//            SCHD_CRAT_TMPL_LINETMsg schdCratTmplLine = new SCHD_CRAT_TMPL_LINETMsg();
//            // Mod Start 2016/08/23 S21_NA#12778
//            //schdCratTmplLine.setSQLID("001");
//            //schdCratTmplLine.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            //schdCratTmplLine.setConditionValue("cpoSvcConfigRefPk01", pMsg.cpoSvcConfigRefPk.getValue());
//            schdCratTmplLine.setSQLID("002");
//            schdCratTmplLine.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            schdCratTmplLine.setConditionValue("dsContrDtlPk01", pMsg.dsContrDtlPk.getValue());
//            schdCratTmplLine.setConditionValue("schdCratTmplPk01", schdCratTmplData.get("SCHD_CRAT_TMPL_PK"));
//            // Mod End 2016/08/23 S21_NA#12778
//            SCHD_CRAT_TMPL_LINETMsgArray schdCratTmplLineList = (SCHD_CRAT_TMPL_LINETMsgArray) EZDTBLAccessor.findByCondition(schdCratTmplLine);
            // 2020/03/10 QC#56125 Del End

            MDSETMsg mdseTMsg = null;
            for(int i = 0; i < schdCratTmplLineList.length(); i++){
                // GET MDSE
                schdCratTmplLine = schdCratTmplLineList.no(i);
                mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), schdCratTmplLine.mdseCd.getValue());

                // GET Delivery date
                List<NWZC171003PMsg> delyDtList = getDeliveryDate(pMsg, schdCratTmplLine);

                // Create API Parameter for Line
                //// Schedule Agreement Update API for Line
                createAPIParamSchdAgmtUpdLine(schdAgmt, schdCratTmplData, lineNum, schdCratTmplLine, mdseTMsg);

                //// Schedule Agreement Update API for line RDD
                createAPIParamSchdAgmtUpdLineRdd(schdAgmt, delyDtList, lineNum);

                // Create API Parameter
                //// Schedule Agreement Update API for Pricing
                lineNum = lineNum.add(BigDecimal.ONE);
            }

            //// Pricing API
            NWZC157001PMsg pricing = new NWZC157001PMsg();
            createAPIParamPricingHdr(pricing, pMsg, schdCratTmplData, schdAgmt);

            for(int i = 0; i < schdAgmt.NWZC171002PMsg.getValidCount(); i++){
                NWZC171002PMsg schdAgmtLine = schdAgmt.NWZC171002PMsg.no(i);
                createAPIParamPricingLine(pricing, pMsg, schdCratTmplData, schdAgmt, schdAgmtLine);
            }
            // Call Pricing API
            callAPIPricing(pricing, pMsg);
            // Mod Start 2017/08/04 S21_NA#20228
            if (hasError(pMsg)) {
                // return;
                ZYPEZDItemValueSetter.setValue(schdAgmt.xxModeCd, NWZC171001Constant.MODE_ERROR);
                pMsg.xxMsgIdList.setValidCount(BigDecimal.ZERO.intValue());

            } else {
                createAPIParamSchdAgmtUpdPricing(schdAgmt, pricing);
            }
            // }
            // createAPIParamSchdAgmtUpdPricing(schdAgmt, pricing);
            // Mod End 2017/08/04 S21_NA#20228

            // Call Schedule Agreement Update API
            callAPISchdAgmtUpd(schdAgmt, pMsg, linePMsgList);

            if (hasError(pMsg)) {
                return;
            }

        }
        // Mod End 2016/08/23 S21_NA#12778
    }

    // Add Start 2017/03/16 S21_NA#18024
    @SuppressWarnings("unchecked")
    private String getSplyAgmtDocTpCd(String glblCmpyCd, BigDecimal schdCratTmplPk) {
        // get dsContrDtlPk
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("schdCratTmplPk", schdCratTmplPk);

        List<BigDecimal> svcPrcPkRslt = ssmClient.queryObjectList("getDsContrDtlPk", param);

        if (svcPrcPkRslt == null || svcPrcPkRslt.size() == 0) {
            return null;
        }

        // get query parameter
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", svcPrcPkRslt.get(0));

        List<Map<String, Object>> paramRslt = ssmClient.queryObjectList("getSplyAgmtParam", param);

        if (paramRslt == null || paramRslt.size() == 0) {
            return null;
        }

        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);

        // from detail
        param.put("prcCatgCd", paramRslt.get(0).get("PRC_CATG_CD"));
        param.put("prcSvcPlnTpCd", paramRslt.get(0).get("PRC_SVC_PLN_TP_CD"));
        param.put("prcSvcContrTpCd", paramRslt.get(0).get("PRC_SVC_CONTR_TP_CD"));
        // from price
        param.put("mdlId", paramRslt.get(0).get("MDL_ID"));
        param.put("prcMtrPkgPk", paramRslt.get(0).get("PRC_MTR_PKG_PK"));
        // from usage
        param.put("bllgMtrLbCd", paramRslt.get(0).get("BLLG_MTR_LB_CD"));
        param.put("prcListBandCd", paramRslt.get(0).get("PRC_LIST_BAND_CD"));

        // get Supply Agreement Plan ID
        List<BigDecimal> plnPkRslt = ssmClient.queryObjectList("getSplyAgmtPlnPk", param);

        if (plnPkRslt == null || plnPkRslt.size() == 0 || !ZYPCommonFunc.hasValue(plnPkRslt.get(0))) {
            return null;
        }

        // get Supply Agreement Doc Type
        SPLY_AGMT_PLNTMsg splyAgmtPlnTMsg = new SPLY_AGMT_PLNTMsg();
        ZYPEZDItemValueSetter.setValue(splyAgmtPlnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(splyAgmtPlnTMsg.splyAgmtPlnPk, plnPkRslt.get(0));
        splyAgmtPlnTMsg = (SPLY_AGMT_PLNTMsg) S21ApiTBLAccessor.findByKey(splyAgmtPlnTMsg);

        if (splyAgmtPlnTMsg == null || !ZYPCommonFunc.hasValue(splyAgmtPlnTMsg.splyAgmtDocTpCd)) {
            return null;
        }
 
        return splyAgmtPlnTMsg.splyAgmtDocTpCd.getValue();
    }
    // Add End   2017/03/16 S21_NA#18024

    // Del Start 2017/08/04 S21_NA#20228
//    /**
//     * createSchdAgmSlsCrList
//     * @param pMsg List<NMZC260001_defSlsRepListPMsg>
//     * @param shipToCustCd String
//     * @param lineBizTpCd String
//     * @return List<NMZC260001_defSlsRepListPMsg>
//     */
//    private List<NMZC260001_defSlsRepListPMsg> createSchdAgmSlsCrList(NWZC172001PMsg pMsg, String shipToCustCd, String lineBizTpCd) {
//
//        if (!ZYPCommonFunc.hasValue(shipToCustCd) || !ZYPCommonFunc.hasValue(lineBizTpCd)) {
//            return null;
//        }
//
//        // Call Default Rep API[NMZC260001]
//        NMZC260001PMsg defRepPMsg = new NMZC260001PMsg();
//        ZYPEZDItemValueSetter.setValue(defRepPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(defRepPMsg.shipToCustCd, shipToCustCd);
//        NMZC260001 defaultRepAPI = new NMZC260001();
//        ////  Call API
//        defaultRepAPI.execute(defRepPMsg, onBatchType);
//
//        if (!S21ApiUtil.getXxMsgIdList(defRepPMsg).isEmpty()) {
//            for (int j = 0; j < defRepPMsg.xxMsgIdList.getValidCount(); j++) {
//                String msgId = defRepPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
//                if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith("E")) {
//                    return null;
//                }
//            }
//        }
//        // Create scheduling Agreement Sales Credit parametr list for scheduling Agreement update API
//        List<NMZC260001_defSlsRepListPMsg> defRepList = new ArrayList<NMZC260001_defSlsRepListPMsg>(999);
//        List<NMZC260001_defSlsRepListPMsg> defRepListPrmy = new ArrayList<NMZC260001_defSlsRepListPMsg>(1000);
//        String[] targetRole = new String[]{LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER};
//        for (int i = 0; i < defRepPMsg.defSlsRepList.getValidCount(); i++) {
//
//            NMZC260001_defSlsRepListPMsg defSlsRep = defRepPMsg.defSlsRepList.no(i);
//            String lineBizTpCdDefRep = defSlsRep.lineBizTpCd.getValue();
//            if (ZYPCommonFunc.hasValue(lineBizTpCdDefRep) && lineBizTpCdDefRep.equals(lineBizTpCd)) {
//                if (defRepListPrmy.size() == 0 && Arrays.asList(targetRole).contains(defSlsRep.lineBizRoleTpCd.getValue())) {
//                    // If role type is the writer
//                    defRepListPrmy.add(defSlsRep);
//                } else {
//                    // If role type is not the writer
//                    defRepList.add(defSlsRep);
//                }
//            }
//        }
//        defRepListPrmy.addAll(defRepList);
//        return defRepListPrmy;
//    }
    // Del End 2017/08/04 S21_NA#20228

    // Del Start 2017/08/04 S21_NA#20228
//    /**
//     * getContractPerson
//     * @param pMsg NWZC172001PMsg
//     * @param billTo String
//     * @param ShipTo String
//     * @return
//     */
//    private NWXC021001PMsg getContractPerson(NWZC172001PMsg pMsg, String billTo, String shipTo) {
//
//        if (!ZYPCommonFunc.hasValue(billTo) || !ZYPCommonFunc.hasValue(shipTo)) {
//            return null;
//        }
//        // Search Contact Person
//        NWXC021001PMsg ctacPsnPMsg = new NWXC021001PMsg();
//        ZYPEZDItemValueSetter.setValue(ctacPsnPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(ctacPsnPMsg.billToCustCd, billTo);
//        ZYPEZDItemValueSetter.setValue(ctacPsnPMsg.shipToCustCd, shipTo);
//        NWXC021001 ctacPsn = new NWXC021001();
//        ctacPsn.execute(ctacPsnPMsg, onBatchType);
//
//        if (!S21ApiUtil.getXxMsgIdList(ctacPsnPMsg).isEmpty()) {
//            for (int j = 0; j < ctacPsnPMsg.xxMsgIdList.length(); j++) {
//                String msgId = ctacPsnPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
//                if (ZYPCommonFunc.hasValue(msgId)) {
//                    return null;
//                }
//            }
//        }
//        return ctacPsnPMsg;
//
//    }
    // Del End 2017/08/04 S21_NA#20228

    // 2018/05/24 S21_NA#26148 add start
    private List<NMZC260001_defSlsRepListPMsg> createSchdAgmSlsCrList(NWZC172001PMsg pMsg, String shipToCustCd, String billToCustCd, Map<String, Object> schdCratTmplData) {
        String lineBizTpCd = (String) schdCratTmplData.get("LINE_BIZ_TP_CD");
        if (!ZYPCommonFunc.hasValue(shipToCustCd) || !ZYPCommonFunc.hasValue(lineBizTpCd)) {
            return null;
        }

        NMZC260001PMsg defRepPMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(defRepPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(defRepPMsg.shipToCustCd, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(defRepPMsg.dsOrdCatgCd, (String) schdCratTmplData.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(defRepPMsg.dsOrdTpCd, (String) schdCratTmplData.get("DS_ORD_TP_CD"));
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(defRepPMsg.billToCustCd, billToCustCd);

        NMZC260001 defaultRepAPI = new NMZC260001();
        defaultRepAPI.execute(defRepPMsg, onBatchType);

        if (!S21ApiUtil.getXxMsgIdList(defRepPMsg).isEmpty()) {
            for (int j = 0; j < defRepPMsg.xxMsgIdList.getValidCount(); j++) {
                String msgId = defRepPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId) && msgId.endsWith("E")) {
                    return null;
                }
            }
        }

        String trtyGrpTpTxt = getTrtyGrpTpTxtFromDsOrdTpCd(pMsg.glblCmpyCd.getValue(), (String) schdCratTmplData.get("DS_ORD_TP_CD"));
        List<String> trtyGrpTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)) {
            trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
        }
        List<String> targetWriterList = getTargetWriterList(pMsg.glblCmpyCd.getValue());
        List<NMZC260001_defSlsRepListPMsg> matchLobSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();
        List<NMZC260001_defSlsRepListPMsg> matchLobRoleSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = defRepPMsg.defSlsRepList;
        List<String> psnCdList = new ArrayList<String>(0);
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);

            if(ZYPCommonFunc.hasValue(defSlsRepPMsg.xxRsltFlg) && ZYPConstant.FLG_ON_Y.equals(defSlsRepPMsg.xxRsltFlg.getValue())//
                    && !matchLobRoleSlsRepPMsgList.isEmpty()){
                continue;
            }

            if (ZYPCommonFunc.hasValue(defSlsRepPMsg.psnCd)) {
                if (psnCdList.contains(defSlsRepPMsg.psnCd.getValue())) {
                    continue;
                } else {
                    psnCdList.add(defSlsRepPMsg.psnCd.getValue());
                }
            }

            if ((trtyGrpTpCdList.isEmpty() && defSlsRepPMsg.lineBizTpCd.getValue().equals(lineBizTpCd))
                    || (trtyGrpTpCdList.size() > 0 && trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {

                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                if (targetWriterList.contains(lineBizRoleTpCd)) {
                    matchLobRoleSlsRepPMsgList.add(defSlsRepPMsg);
                } else {
                    matchLobSlsRepPMsgList.add(defSlsRepPMsg);
                }
            }
        }

        matchLobRoleSlsRepPMsgList.addAll(matchLobSlsRepPMsgList);
        return matchLobRoleSlsRepPMsgList;
    }
    // 2018/05/24 S21_NA#26148 add start

    /**
     * getDeliveryDate
     * @param pMsg NWZC172001PMsg
     * @param linePMsg NWZC172002PMsg
     * @return List<String>
     */
    private List<NWZC171003PMsg> getDeliveryDate(NWZC172001PMsg pMsg, SCHD_CRAT_TMPL_LINETMsg schdCratTmplLine) {

        String startDtYYYYMM = convtDtYYYYMM(pMsg.schdAgmtVldFromDt.getValue());
        String endDtYYYYMM = convtDtYYYYMM(pMsg.schdAgmtVldThruDt.getValue());
        List<NWZC171003PMsg> delyDateList = new ArrayList<NWZC171003PMsg>(1000);

        try {
            int diffMonth = getDiffMonth(startDtYYYYMM, endDtYYYYMM);
            int intvlMonth = getShpgIntvlCalMth(pMsg.glblCmpyCd.getValue(), schdCratTmplLine.shpgIntvlCd.getValue());
            int lineCount = getLineCount(diffMonth, intvlMonth);

            // Convert Calendar
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date startDtDate = format.parse(pMsg.schdAgmtVldFromDt.getValue());
            Calendar startDtCal = Calendar.getInstance();
            startDtCal.setTime(startDtDate);

            Date endDtDate = format.parse(pMsg.schdAgmtVldThruDt.getValue());
            Calendar endDt = Calendar.getInstance();
            endDt.setTime(endDtDate);

            int cnt = 0;
            int addMonth = intvlMonth;
            NWZC171003PMsg delyDatePMsg = new NWZC171003PMsg();
            BigDecimal totalQtyCnt = BigDecimal.ZERO;
            BigDecimal preTotalQtyCnt = BigDecimal.ZERO;
            // Set First Date
            Calendar delyDate = getDelyDate(startDtCal, 0, endDt);
            ZYPEZDItemValueSetter.setValue(delyDatePMsg.rddDt, format.format(delyDate.getTime()));
            totalQtyCnt = schdCratTmplLine.otmShipQty.getValue();
            if (0 <= schdCratTmplLine.schdAllwQty.getValue().compareTo(totalQtyCnt)) {
                ZYPEZDItemValueSetter.setValue(delyDatePMsg.ordQty, schdCratTmplLine.otmShipQty);
            } else {
                ZYPEZDItemValueSetter.setValue(delyDatePMsg.ordQty, schdCratTmplLine.schdAllwQty);
            }
            cnt++;
            delyDateList.add(delyDatePMsg);

            // After first date
            while (cnt < lineCount) {
                delyDatePMsg = new NWZC171003PMsg();
                if (schdCratTmplLine.schdAllwQty.getValue().compareTo(totalQtyCnt) <= 0) {
                    //reached Schedule allow Qty
                    break;
                }
                delyDate = getDelyDate(startDtCal, addMonth, endDt);
                if (delyDate == null) {
                    //reached EndDate
                    break;
                }
                ZYPEZDItemValueSetter.setValue(delyDatePMsg.rddDt, format.format(delyDate.getTime()));
                addMonth = addMonth + intvlMonth;
                cnt++;
                preTotalQtyCnt = totalQtyCnt;
                totalQtyCnt = totalQtyCnt.add(schdCratTmplLine.otmShipQty.getValue());
                if (0 <= schdCratTmplLine.schdAllwQty.getValue().compareTo(totalQtyCnt)) {
                    ZYPEZDItemValueSetter.setValue(delyDatePMsg.ordQty, schdCratTmplLine.otmShipQty);
                } else {
                    ZYPEZDItemValueSetter.setValue(delyDatePMsg.ordQty, schdCratTmplLine.schdAllwQty.getValue().subtract(preTotalQtyCnt));
                }
                delyDateList.add(delyDatePMsg);
            }

        } catch (ParseException e) {
            // Error
            return null;
        }
        return delyDateList;
    }

    /**
     * convtDtYYYYMM
     * @param date String
     * @return String
     */
    private String convtDtYYYYMM(String date) {
        if (ZYPCommonFunc.hasValue(date) && 6 <= date.length()) {
            return date.substring(0, 6);
        }
        return date;
    }

    /**
     * getShpgIntvlCalMth
     * @param glblCmpyCd String
     * @param shpgIntvlCd String
     * @return int
     */
    private int getShpgIntvlCalMth(String glblCmpyCd, String shpgIntvlCd) {

        if (ZYPCommonFunc.hasValue(shpgIntvlCd)) {
            SHPG_INTVLTMsg shpgIntvl = new SHPG_INTVLTMsg();
            ZYPEZDItemValueSetter.setValue(shpgIntvl.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgIntvl.shpgIntvlCd, shpgIntvlCd);
            shpgIntvl = (SHPG_INTVLTMsg) S21CodeTableAccessor.findByKey(shpgIntvl);
            if (shpgIntvl != null && ZYPCommonFunc.hasValue(shpgIntvl.shpgIntvlMthNum)) {
                return shpgIntvl.shpgIntvlMthNum.getValueInt();
            }
        }
        return 0;
    }

    /**
     * getDiffMonth
     * @param starDt String
     * @param endDt String
     * @return int
     * @throws ParseException
     */
    private int getDiffMonth(String starDt, String endDt) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Date starDtD = format.parse(starDt);
        Date endDtD = format.parse(endDt);

        Calendar starDtC = Calendar.getInstance();
        starDtC.setTime(starDtD);
        starDtC.set(Calendar.DATE, 1);
        Calendar endDtC = Calendar.getInstance();
        endDtC.setTime(endDtD);
        endDtC.set(Calendar.DATE, 1);

        int count = 0;
        // starDt < endDt
        while (starDtC.before(endDtC)) {
            starDtC.add(Calendar.MONTH, 1);
            count++;
        }
        return count;
    }

    /**
     * getLineCount
     * @param diffMonth int
     * @param intval int
     * @return int
     */
    private int getLineCount(int diffMonth, int intval) {

        if (diffMonth == 0 || intval == 0) {
            return 0;
        } else {
            BigDecimal diffMonthBd = new BigDecimal(diffMonth);
            BigDecimal shpgIntvlMonth = new BigDecimal(intval);
            BigDecimal lineCount = diffMonthBd.divide(shpgIntvlMonth, 0, BigDecimal.ROUND_DOWN);
            return lineCount.intValue() + 1;
        }
    }

    /**
     * getDelyDate
     * @param startDate Calendar
     * @param addMonth int
     * @return Calendar
     */
    private Calendar getDelyDate(Calendar startDate, int addMonth, Calendar thruDt) {
        int startDay = startDate.get(Calendar.DATE);
        Calendar delyDate = (Calendar) startDate.clone();
        delyDate.add(Calendar.MONTH, addMonth);
        int date = delyDate.get(Calendar.DATE);

        if (date != startDay) {
            int endOfDate = delyDate.getActualMaximum(Calendar.DATE);
            if (startDay <= endOfDate) {
                delyDate.set(Calendar.DATE, startDay);
            } else {
                delyDate.set(Calendar.DATE, endOfDate);
            }
        }
        if (!delyDate.before(thruDt)) {
            // thruDt < delyDate
            return null;
        }
        return delyDate;
    }


    /**
     * createAPIParamPricingHdr
     * @param pricing NWZC157001PMsg
     * @param pMsg NWZC172001PMsg
     * @param schdCratTmplData Map<String, Object
     */
    private void createAPIParamPricingHdr(NWZC157001PMsg pricing, NWZC172001PMsg pMsg, Map<String, Object> schdCratTmplData, NWZC171001PMsg schdAgmt) {
        // Creater API Parameter
        ZYPEZDItemValueSetter.setValue(pricing.glblCmpyCd, pMsg.glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(pricing.xxModeCd, NWZC157001.GET_LINE_PRICE);
        ZYPEZDItemValueSetter.setValue(pricing.xxModeCd, NWZC157001.GET_ORDER_ALL);
        ZYPEZDItemValueSetter.setValue(pricing.prcBaseDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pricing.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(pricing.orgRqstDelyDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pricing.dsOrdCatgCd, (String) schdCratTmplData.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(pricing.dsOrdTpCd, (String) schdCratTmplData.get("DS_ORD_TP_CD"));
        // QC#15763 2016/11/07 Mod Start
        ZYPEZDItemValueSetter.setValue(pricing.lineBizTpCd, (String) schdCratTmplData.get("LINE_BIZ_TP_CD"));
        // QC#15763 2016/11/07 Mod End
        //ZYPEZDItemValueSetter.setValue(pricing.dsAcctNum, (String) schdCratTmplData.get("SELL_TO_CUST_CD_CPO"));
        ZYPEZDItemValueSetter.setValue(pricing.dsAcctNum, schdAgmt.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pricing.locNum, schdAgmt.soldToCustLocCd);
        // QC#11654 2016/09/02 Mod End
        ZYPEZDItemValueSetter.setValue(pricing.custIssPoNum, (String) schdCratTmplData.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(pricing.taxCalcFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * createAPIParamPricingLine
     * @param pricing NWZC157001PMsg
     * @param pMsg NWZC172001PMsg
     * @param linePMsg NWZC172002PMsg
     * @param schdCratTmplData Map<String, Object>
     * @param schdLineNum BigDecimal
     * @param dsCpoConfig DS_CPO_CONFIGTMsg
     * @param schdCratTmplLine SCHD_CRAT_TMPL_LINETMsg
     * @param inPoundWt BigDecimal
     */
    private void createAPIParamPricingLine(
            NWZC157001PMsg pricing
            , NWZC172001PMsg pMsg
            , Map<String, Object> schdCratTmplData
            , NWZC171001PMsg schdAgmt
            , NWZC171002PMsg schdAgmtLine) {

        BigDecimal poundWt = null;

        NWZC157002PMsg prcLine = pricing.NWZC157002PMsg.no(pricing.NWZC157002PMsg.getValidCount());

        // GET MDSE Weight
        MDSE_STORE_PKGTMsg mdseStorePkg = new MDSE_STORE_PKGTMsg();
        mdseStorePkg.setSQLID("003");
        mdseStorePkg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        mdseStorePkg.setConditionValue("pkgUomCd01", PKG_UOM.EACH);
        mdseStorePkg.setConditionValue("mdseCd01", schdAgmtLine.mdseCd.getValue());
        MDSE_STORE_PKGTMsgArray mdseStorePkgList = (MDSE_STORE_PKGTMsgArray) EZDTBLAccessor.findByCondition(mdseStorePkg);
        if (mdseStorePkgList != null && 0 < mdseStorePkgList.getValidCount()) {
            poundWt = mdseStorePkgList.no(0).inPoundWt.getValue();
        }

        ZYPEZDItemValueSetter.setValue(prcLine.trxLineNum, schdAgmtLine.schdAgmtLineNum.getValue().toString());
        ZYPEZDItemValueSetter.setValue(prcLine.trxLineSubNum, "001");
        // QC#11654 2016/09/02 Mod Start
        // ZYPEZDItemValueSetter.setValue(prcLine.billToCustCd, dsCpoConfig.billToCustLocCd);
        // ZYPEZDItemValueSetter.setValue(prcLine.shipToCustCd, dsCpoConfig.shipToCustLocCd);
        // ZYPEZDItemValueSetter.setValue(prcLine.dsAcctNum_SH, dsCpoConfig.shipToCustAcctCd);
        // ZYPEZDItemValueSetter.setValue(prcLine.dsAcctNum_BL, dsCpoConfig.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(prcLine.billToCustCd, schdAgmt.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(prcLine.shipToCustCd, schdAgmt.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(prcLine.dsAcctNum_SH, schdAgmt.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(prcLine.dsAcctNum_BL, schdAgmt.billToCustAcctCd);
        // QC#11654 2016/09/02 Mod End
        ZYPEZDItemValueSetter.setValue(prcLine.prcCatgCd, schdAgmt.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(prcLine.ccyCd, (String) schdCratTmplData.get("CCY_CD"));
        ZYPEZDItemValueSetter.setValue(prcLine.mdseCd, schdAgmtLine.mdseCd);
        ZYPEZDItemValueSetter.setValue(prcLine.pkgUomCd, PKG_UOM.EACH);
        // QC#15763 2016/11/07 Mod Start
        if ( null != schdCratTmplData.get("DS_ORD_LINE_CATG_CD")) {
            ZYPEZDItemValueSetter.setValue(prcLine.dsOrdLineCatgCd, (String) schdCratTmplData.get("DS_ORD_LINE_CATG_CD"));
        }
        // QC#15763 2016/11/07 Mod End
        ZYPEZDItemValueSetter.setValue(prcLine.ordQty, schdAgmtLine.schdAllwQty);
        ZYPEZDItemValueSetter.setValue(prcLine.ordCustUomQty, schdAgmtLine.schdAllwQty);
        ZYPEZDItemValueSetter.setValue(prcLine.invQty, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(prcLine.mdlId, pMsg.mdlId);
        // QC#11654 2016/09/02 Mod Start
        // ZYPEZDItemValueSetter.setValue(prcLine.ctyAddr_SH, dsCpoConfig.shipToCtyAddr);
        // ZYPEZDItemValueSetter.setValue(prcLine.stCd_SH, dsCpoConfig.shipToStCd);
        // ZYPEZDItemValueSetter.setValue(prcLine.postCd_SH, dsCpoConfig.shipToPostCd);
        // ZYPEZDItemValueSetter.setValue(prcLine.ctryCd_SH, dsCpoConfig.shipToCtryCd);

        ZYPEZDItemValueSetter.setValue(prcLine.ctyAddr_SH, schdAgmt.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(prcLine.stCd_SH, schdAgmt.shipToStCd);
        ZYPEZDItemValueSetter.setValue(prcLine.postCd_SH, schdAgmt.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(prcLine.ctryCd_SH, schdAgmt.shipToCtryCd);
        // QC#11654 2016/09/02 Mod End
        // QC#15763 2016/11/07 Mod Start
        if ( null != schdCratTmplData.get("DS_ORD_LINE_CATG_CD")) {
            ZYPEZDItemValueSetter.setValue(prcLine.frtCondCd,(String) schdCratTmplData.get("FRT_COND_CD"));
        }
        // QC#15763 2016/11/07 Mod End
        ZYPEZDItemValueSetter.setValue(prcLine.unitNetWt, poundWt);
        // QC#15763 2016/11/07 Mod Start
        if ( null != schdCratTmplData.get("DEF_SHPG_SVC_LVL_CD")) {
            ZYPEZDItemValueSetter.setValue(prcLine.shpgSvcLvlCd, (String) schdCratTmplData.get("DEF_SHPG_SVC_LVL_CD"));            
        }
        ZYPEZDItemValueSetter.setValue(prcLine.frtChrgToCd, (String) schdCratTmplData.get("FRT_CHRG_TO_CD"));
        ZYPEZDItemValueSetter.setValue(prcLine.frtChrgMethCd, (String) schdCratTmplData.get("FRT_CHRG_METH_CD"));
        ZYPEZDItemValueSetter.setValue(prcLine.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

        pricing.NWZC157002PMsg.setValidCount(pricing.NWZC157002PMsg.getValidCount() + 1);
    }

    /**
     * createAPIParamSchdAgtUpdHdr
     * @param schdAgmt NWZC171001PMsg
     * @param pMsg NWZC172001PMsg
     * @param schdCratTmplData Map<String, Object>
     * @param defSlsRepList List<NMZC260001_defSlsRepListPMsg>
     * @param dsCpoConfig List<NMZC260001_defSlsRepListPMsg>
     * @param pmtTermCashDiscCd String
     */
    // 2018/02/08 S21_NA#20297(Sol#379) Mod Start
//    private void createAPIParamSchdAgtUpdHdr(
//            NWZC171001PMsg schdAgmt
//            , NWZC172001PMsg pMsg
//            , Map<String, Object> schdCratTmplData
//            , List<Map<String, Object>> slsRepList
//            , SVC_MACH_MSTRTMsg svcMachMstrTMsg
//            , String pmtTermCashDiscCd) {
    private void createAPIParamSchdAgtUpdHdr(
            NWZC171001PMsg schdAgmt
            , NWZC172001PMsg pMsg
            , Map<String, Object> schdCratTmplData
            , List<NMZC260001_defSlsRepListPMsg> slsRepList
            , SVC_MACH_MSTRTMsg svcMachMstrTMsg
            , String pmtTermCashDiscCd
            , DS_CPO_CONFIGTMsg dsCpoConfig
            , SCHD_CRAT_TMPL_LINETMsgArray schdCratTmplLineList) { // 2020/03/10 QC#56125 Add
        // 2018/02/08 S21_NA#20297(Sol#379) Mod End
        ZYPEZDItemValueSetter.setValue(schdAgmt.glblCmpyCd, pMsg.glblCmpyCd);
        setDefShipInfo(schdAgmt, pMsg, schdCratTmplData, svcMachMstrTMsg, dsCpoConfig); // 2018/02/08 S21_NA#20297(Sol#379) Add 
        // 2018/02/08 S21_NA#20297(Sol#379) Mod Start
        // Mod Start 2016/11/07 S21_NA#15763
        //if (null != schdCratTmplData.get("FRT_COND_CD")) {
        if (ZYPCommonFunc.hasValue(schdAgmt.frtCondCd) || null != schdCratTmplData.get("FRT_COND_CD")) {
        // 2018/02/08 S21_NA#20297(Sol#379) Mod End
            // 2020/03/10 QC#56125 Add Start
            boolean zeroQty = false;
            for (int i = 0; i < schdCratTmplLineList.length(); i++) {
                SCHD_CRAT_TMPL_LINETMsg schdCratTmplLine = (SCHD_CRAT_TMPL_LINETMsg)schdCratTmplLineList.get(i);
                if (BigDecimal.ZERO.compareTo(schdCratTmplLine.schdAllwQty.getValue()) < 0) {
                    zeroQty = false;
                } else {
                    zeroQty = true;
                    break;
                }
            }
            if (zeroQty) {
                ZYPEZDItemValueSetter.setValue(schdAgmt.xxModeCd, NWZC171001Constant.MODE_SAVE);
            } else {
            // 2020/03/10 QC#56125 Add End
                ZYPEZDItemValueSetter.setValue(schdAgmt.xxModeCd, NWZC171001Constant.MODE_SUBMIT);
            }
        } else {
            // Mod Start 2017/08/04 S21_NA#20228
            ZYPEZDItemValueSetter.setValue(schdAgmt.xxModeCd, NWZC171001Constant.MODE_ERROR);
            // set 
//            ZYPEZDItemValueSetter.setValue(schdAgmt.schdAgmtNum, NWZM2051W);
            // Mod End 2017/08/04 S21_NA#20228
        }
        // Mod End 2016/11/07 S21_NA#15763
        ZYPEZDItemValueSetter.setValue(schdAgmt.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(schdAgmt.refCpoOrdNum, (String) schdCratTmplData.get("REF_CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(schdAgmt.schdCratTmplPk, (BigDecimal) schdCratTmplData.get("SCHD_CRAT_TMPL_PK"));
        ZYPEZDItemValueSetter.setValue(schdAgmt.schdAgmtCatgCd, SCHD_AGMT_CATG.SCHEDULE_CSA);
        ZYPEZDItemValueSetter.setValue(schdAgmt.dsOrdCatgCd, (String) schdCratTmplData.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(schdAgmt.dsOrdTpCd, (String) schdCratTmplData.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(schdAgmt.cpoOrdTpCd, (String) schdCratTmplData.get("CPO_ORD_TP_CD"));
        // Mod Start 2016/11/07 S21_NA#15763
        if (null != schdCratTmplData.get("DEF_PRC_CATG_CD")) {
            ZYPEZDItemValueSetter.setValue(schdAgmt.prcCatgCd, (String) schdCratTmplData.get("DEF_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.flPrcListCd, (String) schdCratTmplData.get("DEF_PRC_CATG_CD"));
        }
        // Mod End 2016/11/07 S21_NA#15763
        ZYPEZDItemValueSetter.setValue(schdAgmt.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(schdAgmt.dsContrNum, pMsg.dsContrNum);
        ZYPEZDItemValueSetter.setValue(schdAgmt.dsContrSqNum, pMsg.dsContrSqNum);
        // Mod Start 2016/07/28 S21_NA#11463
        // ZYPEZDItemValueSetter.setValue(schdAgmt.sysSrcCd, (String) schdCratTmplData.get("SYS_SRC_CD"));
        // ZYPEZDItemValueSetter.setValue(schdAgmt.sysSrcRefNum, (String) schdCratTmplData.get("SYS_SRC_REF_NUM"));
        // Mod Start 2016/09/26 S21_NA#21193
        ZYPEZDItemValueSetter.setValue(schdAgmt.cpoSrcTpCd, CPO_SRC_TP.ORDER_ENTRY_SCREEN);
        // Mod End 2016/09/26 S21_NA#21193
        ZYPEZDItemValueSetter.setValue(schdAgmt.ordSrcRefNum, (String) schdCratTmplData.get("ORD_SRC_REF_NUM"));
        // Mod End 2016/07/28 S21_NA#11463
        ZYPEZDItemValueSetter.setValue(schdAgmt.schdAgmtVldFromDt, pMsg.schdAgmtVldFromDt);
        ZYPEZDItemValueSetter.setValue(schdAgmt.schdAgmtVldThruDt, pMsg.schdAgmtVldThruDt);
        ZYPEZDItemValueSetter.setValue(schdAgmt.custIssPoNum, (String) schdCratTmplData.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(schdAgmt.custIssPoDt, (String) schdCratTmplData.get("CUST_ISS_PO_DT"));
        // 2018/05/24 S21_NA#26148 mod start
        if (slsRepList != null && 0 < slsRepList.size() && ZYPCommonFunc.hasValue(slsRepList.get(0).tocCd)) {
//            ZYPEZDItemValueSetter.setValue(schdAgmt.slsRepTocCd, (String) slsRepList.get(0).get("SLS_REP_TOC_CD"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.slsRepTocCd, slsRepList.get(0).tocCd.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(schdAgmt.xxModeCd, NWZC171001Constant.MODE_ERROR);
        }
        // 2018/05/24 S21_NA#26148 mod end
        // Mod Start 2016/11/07 S21_NA#15763
        //if (null != schdCratTmplData.get("DEF_SHPG_SVC_LVL_CD")) {
        if (!ZYPCommonFunc.hasValue(schdAgmt.shpgSvcLvlCd) && null != schdCratTmplData.get("DEF_SHPG_SVC_LVL_CD")) {
            ZYPEZDItemValueSetter.setValue(schdAgmt.shpgSvcLvlCd,  (String) schdCratTmplData.get("DEF_SHPG_SVC_LVL_CD"));
        }
        //if (null != schdCratTmplData.get("FRT_COND_CD")) {
        if (!ZYPCommonFunc.hasValue(schdAgmt.frtCondCd) && null != schdCratTmplData.get("FRT_COND_CD")) {
            ZYPEZDItemValueSetter.setValue(schdAgmt.frtCondCd, (String) schdCratTmplData.get("FRT_COND_CD"));
        }
        // Mod End 2016/11/07 S21_NA#15763
        ZYPEZDItemValueSetter.setValue(schdAgmt.frtChrgToCd, (String) schdCratTmplData.get("FRT_CHRG_TO_CD"));
        ZYPEZDItemValueSetter.setValue(schdAgmt.frtChrgMethCd, (String) schdCratTmplData.get("FRT_CHRG_METH_CD"));
        // QC#11654 2016/09/02 Mod Start
        // ZYPEZDItemValueSetter.setValue(schdAgmt.sellToCustCd, (String) schdCratTmplData.get("SELL_TO_CUST_CD_CPO"));
        // ZYPEZDItemValueSetter.setValue(schdAgmt.soldToCustLocCd, (String) schdCratTmplData.get("SOLD_TO_CUST_LOC_CD_DSCPO"));
        // ZYPEZDItemValueSetter.setValue(schdAgmt.billToCustAcctCd, dsCpoConfig.billToCustAcctCd);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.billToCustLocCd, dsCpoConfig.billToCustLocCd);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCustAcctCd, dsCpoConfig.shipToCustAcctCd);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCustLocCd, dsCpoConfig.shipToCustLocCd);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.dropShipFlg, dsCpoConfig.dropShipFlg);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToLocNm, dsCpoConfig.shipToLocNm);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToAddlLocNm, dsCpoConfig.shipToAddlLocNm);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToFirstLineAddr, dsCpoConfig.shipToFirstLineAddr);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToScdLineAddr, dsCpoConfig.shipToScdLineAddr);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToThirdLineAddr, dsCpoConfig.shipToThirdLineAddr);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToFrthLineAddr, dsCpoConfig.shipToFrthLineAddr);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipTo01RefCmntTxt, dsCpoConfig.shipToFirstRefCmntTxt);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipTo02RefCmntTxt, dsCpoConfig.shipToScdRefCmntTxt);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCtyAddr, dsCpoConfig.shipToCtyAddr);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToStCd, dsCpoConfig.shipToStCd);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToProvNm, dsCpoConfig.shipToProvNm);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCntyNm, dsCpoConfig.shipToCntyNm);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToPostCd, dsCpoConfig.shipToPostCd);
        // ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCtryCd, dsCpoConfig.shipToCtryCd);

        ZYPEZDItemValueSetter.setValue(schdAgmt.sellToCustCd, svcMachMstrTMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(schdAgmt.soldToCustLocCd, svcMachMstrTMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(schdAgmt.billToCustAcctCd, svcMachMstrTMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(schdAgmt.billToCustLocCd, svcMachMstrTMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCustAcctCd, svcMachMstrTMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCustLocCd, svcMachMstrTMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(schdAgmt.dropShipFlg, ZYPConstant.FLG_OFF_N);
        List<Map<String, Object>> shipToInfoList = getShipToCustInfoList(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.curLocNum.getValue());
        if (shipToInfoList != null && shipToInfoList.size() > 0) {
            Map<String, Object> shipToInfo = shipToInfoList.get(0);
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToLocNm, (String) shipToInfo.get("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToAddlLocNm, (String) shipToInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToFirstLineAddr, (String) shipToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToScdLineAddr, (String) shipToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToThirdLineAddr, (String) shipToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToFrthLineAddr, (String) shipToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipTo01RefCmntTxt, (String) shipToInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipTo02RefCmntTxt, (String) shipToInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCtyAddr, (String) shipToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToStCd, (String) shipToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToProvNm, (String) shipToInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCntyNm, (String) shipToInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToPostCd, (String) shipToInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(schdAgmt.shipToCtryCd, (String) shipToInfo.get("CTRY_CD"));
        }
        // QC#11654 2016/09/02 Mod End
        ZYPEZDItemValueSetter.setValue(schdAgmt.adminPsnCd, (String) schdCratTmplData.get("ADMIN_PSN_CD"));
        ZYPEZDItemValueSetter.setValue(schdAgmt.pmtTermCashDiscCd, pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(schdAgmt.prcBaseDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(schdAgmt.prcCalcDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(schdAgmt.mdlId, pMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(schdAgmt.serNum, pMsg.serNum);
        // 2018/06/25 S21_NA#23726 add start
        // 2018/12/14 S21_NA#29315 Mod Start
        String dsBizAreaCd = NWXC150001DsCheck.getDsBizArea(schdAgmt.glblCmpyCd.getValue(), schdAgmt.dsOrdCatgCd.getValue(), schdAgmt.dsOrdTpCd.getValue(), schdAgmt.dsOrdRsnCd.getValue());
//        deriveDefaultCarrSvcLvl(schdAgmt, pMsg, schdCratTmplData, schdAgmt.shipToCustAcctCd.getValue(), schdAgmt.shpgSvcLvlCd.getValue());
        deriveDefaultCarrSvcLvl(schdAgmt, pMsg, schdCratTmplData, schdAgmt.shipToCustAcctCd.getValue(), schdAgmt.shpgSvcLvlCd.getValue(), dsBizAreaCd);
        // 2018/12/14 S21_NA#29315 Mod End
        if (NWXC150001DsCheck.checkCustCarrSvcLvlRelation(schdAgmt.glblCmpyCd.getValue(), dsBizAreaCd, schdAgmt.shipToCustAcctCd.getValue(), schdAgmt.carrSvcLvlCd.getValue(), schdAgmt.frtCondCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(schdAgmt.xxModeCd, NWZC171001Constant.MODE_ERROR);
        }
        // 2018/06/25 S21_NA#23726 add end
    }

    // 2018/05/24 S21_NA#26148 mod start
//
//    /**
//     * createAPIParamSchdAgtUpdSlsCr
//     * @param schdAgmt NWZC171001PMsg
//     * @param slsRepList List<Map<String, Object>>
//     */
//    private void createAPIParamSchdAgmtUpdSlsCr(NWZC171001PMsg schdAgmt, List<Map<String, Object>> slsRepList) {
//
//        if (slsRepList == null) {
//            return;
//        }
//        for (Map<String, Object> slsRep : slsRepList) {
//            NWZC171001_CPMsg schdAgmtSlsCr = schdAgmt.C.no(schdAgmt.C.getValidCount());
//            ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.xxRqstTpCd, RQST_TP_SLS_CR_NEW);
//            ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsRepTocCd, (String) slsRep.get("SLS_REP_TOC_CD"));
//            ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsRepRoleTpCd, (String) slsRep.get("SLS_REP_ROLE_TP_CD"));
//            ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsRepCrPct, (BigDecimal) slsRep.get("SLS_REP_CR_PCT"));
//            ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsCrQuotFlg, (String) slsRep.get("SLS_CR_QUOT_FLG"));
//            schdAgmt.C.setValidCount(schdAgmt.C.getValidCount() + 1);
//        }
//    }

    /**
     * createAPIParamSchdAgtUpdSlsCr
     * @param schdAgmt NWZC171001PMsg
     * @param slsRepList List<Map<String, Object>>
     */
    private void createAPIParamSchdAgmtUpdSlsCr(NWZC171001PMsg schdAgmt, List<NMZC260001_defSlsRepListPMsg> slsRepList) {

        if (slsRepList == null) {
            return;
        }

        String mode = getSalesCreditPrecentMode(slsRepList);
        BigDecimal writerPct = BigDecimal.ZERO;
        BigDecimal installerPct = BigDecimal.ZERO;
        if(S21StringUtil.isEquals("2",mode)){
            BigDecimal pct = ZYPCodeDataUtil.getNumConstValue("DEF_SLS_CR_PCT_WRITER", schdAgmt.glblCmpyCd.getValue());
            if(pct != null){
                writerPct = pct;
                installerPct = new BigDecimal(100).subtract(writerPct);
            }
        }
        List<String> targetWriterList = getTargetWriterList(schdAgmt.glblCmpyCd.getValue());
        
        for (NMZC260001_defSlsRepListPMsg slsRep : slsRepList) {
            NWZC171001_CPMsg schdAgmtSlsCr = schdAgmt.C.no(schdAgmt.C.getValidCount());
            ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.xxRqstTpCd, RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsRepTocCd, slsRep.tocCd.getValue());
            ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsRepRoleTpCd, slsRep.lineBizRoleTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsCrQuotFlg, slsRep.slsCrQuotFlg.getValue());
            if (S21StringUtil.isEquals("1", mode)) {
                ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsRepCrPct, new BigDecimal(100));
            } else if (S21StringUtil.isEquals("2", mode)) {
                if (targetWriterList.contains(slsRep.lineBizRoleTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsRepCrPct, writerPct);
                } else {
                    ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsRepCrPct, installerPct);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(schdAgmtSlsCr.slsRepCrPct, BigDecimal.ZERO);
            }
            schdAgmt.C.setValidCount(schdAgmt.C.getValidCount() + 1);
        }
    }
    // 2018/05/24 S21_NA#26148 mod end

    /**
     * createAPIParamSchdAgtUpdContactPsn
     * @param schdAgmt NWZC171001PMsg
     * @param ctacPsnList List<Map<String, Object>>
     */
    private void createAPIParamSchdAgmtUpdContactPsn(NWZC171001PMsg schdAgmt, List<Map<String, Object>> ctacPsnList) {

        if (ctacPsnList == null) {
            return;
        }
        for (Map<String, Object> ctacPsn : ctacPsnList) {
            NWZC171001_DPMsg schdAgmtCtac = schdAgmt.D.no(schdAgmt.D.getValidCount());
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.xxRqstTpCd, RQST_TP_CTAC_PSN_NEW);
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnPk, (BigDecimal) ctacPsn.get("CTAC_PSN_PK"));
            // Mod Start 2017/12/22 S21_NA#20164 
            // ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnTpCd, (String) ctacPsn.get("CTAC_PSN_TP_CD"));
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnTpCd, (String) ctacPsn.get("CHNG_CTAC_PSN_TP_CD"));
            // Mod End 2017/12/22 S21_NA#20164
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnOvrdFlg, (String) ctacPsn.get("CTAC_PSN_OVRD_FLG"));
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnFirstNm, (String) ctacPsn.get("CTAC_PSN_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnLastNm, (String) ctacPsn.get("CTAC_PSN_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnTelNum, (String) ctacPsn.get("CTAC_PSN_TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnExtnNum, (String) ctacPsn.get("CTAC_PSN_EXTN_NUM"));
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnFaxNum, (String) ctacPsn.get("CTAC_PSN_FAX_NUM"));
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacPsnEmlAddr, (String) ctacPsn.get("CTAC_PSN_EML_ADDR"));
            ZYPEZDItemValueSetter.setValue(schdAgmtCtac.ctacCustRefTpCd, (String) ctacPsn.get("CTAC_CUST_REF_TP_CD"));// S21_NA ADD QC#21564
            // Add Start 2017/12/22 S21_NA#20164
            if (CTAC_TP.DELIVERY_INSTALL.equals((String) ctacPsn.get("CTAC_PSN_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(schdAgmt.sellToFirstRefCmntTxt, getAttention(schdAgmt.D.no(schdAgmt.D.getValidCount())));
            }
            // Add End   2017/12/22 S21_NA#20164
            schdAgmt.D.setValidCount(schdAgmt.D.getValidCount() + 1);
        }
    }

    /**
     * createAPIParamSchdAgtUpdLine
     * @param schdAgmt NWZC171001PMsg
     * @param linePMsg NWZC172002PMsg
     * @param schdCratTmplData Map<String, Object>
     * @param schdLineNum BigDecimal
     * @param schdCratTmplLine SCHD_CRAT_TMPL_LINETMsg
     * @param mdseTMsg MDSETMsg
     */
    private void createAPIParamSchdAgmtUpdLine(
              NWZC171001PMsg schdAgmt
            , Map<String, Object> schdCratTmplData
            , BigDecimal schdLineNum
            , SCHD_CRAT_TMPL_LINETMsg schdCratTmplLine
            , MDSETMsg mdseTMsg) {

        NWZC171002PMsg schdAgmtLine = schdAgmt.NWZC171002PMsg.no(schdAgmt.NWZC171002PMsg.getValidCount());
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.schdAgmtLineNum, schdLineNum);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.dsContrDtlPk, schdCratTmplLine.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.schdCratTmplLineNum, schdCratTmplLine.schdCratTmplLineNum);
        if (mdseTMsg != null) {
            // Mod Start 2016/08/03 S21_NA#12780
//            ZYPEZDItemValueSetter.setValue(schdAgmtLine.mdseCd, mdseTMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(schdAgmtLine.mdseCd, schdCratTmplLine.mdseCd);
            // Mod End 2016/08/03 S21_NA#12780
            ZYPEZDItemValueSetter.setValue(schdAgmtLine.mdseNm, mdseTMsg.mdseNm);
        }
        //        ZYPEZDItemValueSetter.setValue(schdAgmtLine.sbstMdseCd, null);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.pkgUomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.schdAllwQty, schdCratTmplLine.schdAllwQty);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.shpgIntvlCd, schdCratTmplLine.shpgIntvlCd);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.otmShipQty, schdCratTmplLine.otmShipQty);
        // Mod Start 2017/08/04 S21_NA#20228
//        ZYPEZDItemValueSetter.setValue(schdAgmtLine.ccyCd, (String) schdCratTmplData.get("CCY_CD"));
        if (ZYPCommonFunc.hasValue((String) schdCratTmplData.get("CCY_CD"))) {
            ZYPEZDItemValueSetter.setValue(schdAgmtLine.ccyCd, (String) schdCratTmplData.get("CCY_CD"));
        } else {
            ZYPEZDItemValueSetter.setValue(schdAgmtLine.ccyCd, this.stdCcyCd);
        }
        // Mod End 2017/08/04 S21_NA#20228

        ZYPEZDItemValueSetter.setValue(schdAgmtLine.manPrcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.supdLockFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(schdAgmtLine.schdAgmtLineCancFlg, ZYPConstant.FLG_OFF_N);

        schdAgmt.NWZC171002PMsg.setValidCount(schdAgmt.NWZC171002PMsg.getValidCount() + 1);
    }

    /**
     * createAPIParamSchdAgtUpdLineRdd
     * @param schdAgmt NWZC171001PMsg
     * @param delyDtList List<NWZC171003PMsg>
     * @param schdLineNum BigDecimal
     */
    private void createAPIParamSchdAgmtUpdLineRdd(NWZC171001PMsg schdAgmt, List<NWZC171003PMsg> delyDtList, BigDecimal schdLineNum) {
        if (delyDtList == null) {
            return;
        }
        int index = 1;
        for (NWZC171003PMsg delyDt : delyDtList) {
            NWZC171003PMsg schdAgmtLineRdd = schdAgmt.NWZC171003PMsg.no(schdAgmt.NWZC171003PMsg.getValidCount());
            EZDMsg.copy(delyDt, null, schdAgmtLineRdd, null);
            ZYPEZDItemValueSetter.setValue(schdAgmtLineRdd.schdAgmtLineNum, schdLineNum);
            ZYPEZDItemValueSetter.setValue(schdAgmtLineRdd.schdAgmtSchdLineNum, new BigDecimal(index));
            ZYPEZDItemValueSetter.setValue(schdAgmtLineRdd.schdAgmtPlnCancFlg, ZYPConstant.FLG_OFF_N);
            index++;
            schdAgmt.NWZC171003PMsg.setValidCount(schdAgmt.NWZC171003PMsg.getValidCount() + 1);
        }
    }

    /**
     * createAPIParamSchdAgmtUpdPricing
     * @param schdAgmt NWZC171001PMsg
     * @param pricing NWZC157001PMsg
     * @param schdLineNum BigDecimal
     */
    private void createAPIParamSchdAgmtUpdPricing(NWZC171001PMsg schdAgmt, NWZC157001PMsg pricing) {

        for (int i = 0; i < pricing.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg priceLine = pricing.NWZC157002PMsg.no(i);
            NWZC171002PMsg schdAgmtLine = schdAgmt.NWZC171002PMsg.no(i);

            for (int k = 0; k < priceLine.NWZC157003PMsg.getValidCount(); k++) {
                NWZC157003PMsg basePrice = priceLine.NWZC157003PMsg.no(k);
                NWZC171001_EPMsg schdAgmtPrice = schdAgmt.E.no(schdAgmt.E.getValidCount());
                EZDMsg.copy(basePrice, null, schdAgmtPrice, null);
                ZYPEZDItemValueSetter.setValue(schdAgmtPrice.schdAgmtLineNum, schdAgmtLine.schdAgmtLineNum);
                schdAgmt.E.setValidCount(schdAgmt.E.getValidCount() + 1);
            }
        }
    }

    /**
     * callAPIPricing
     * @param pricing NWZC157001PMsg
     * @param pMsg NWZC172001PMsg
     * @param linePMsg NWZC172002PMsg
     */
    private void callAPIPricing(NWZC157001PMsg pricing, NWZC172001PMsg pMsg) {

        // Call Pricing API [NWZC157001]
        NWZC157001 prcAPI = new NWZC157001();
        prcAPI.execute(pricing, ONBATCH_TYPE.ONLINE);

        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pricing);
        for (S21ApiMessage msg : msgList) {
            String msgId = msg.getXxMsgid();
            if (ZYPCommonFunc.hasValue(msgId)) {
                msgMap.addXxMsgIdWithPrm(msgId, msg.getXxMsgPrmArray());
            }
        }

        for (int i = 0; i < pricing.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg prcLine = pricing.NWZC157002PMsg.no(i);

            List<S21ApiMessage> msgListIn = S21ApiUtil.getXxMsgList(prcLine);
            for (S21ApiMessage msg : msgListIn) {
                String msgId = msg.getXxMsgid();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    msgMap.addXxMsgIdWithPrm(msgId, msg.getXxMsgPrmArray());
                }
            }
            msgMap.flush();
        }
    }

    /**
     * callAPISchdAgmtUpd
     * @param schdAgmt NWZC171001PMsg
     * @param pMsg NWZC172001PMsg
     * @param linePMsgList List<NWZC172002PMsg>
     */
    private void callAPISchdAgmtUpd(NWZC171001PMsg schdAgmt, NWZC172001PMsg pMsg, List<NWZC172002PMsg> linePMsgList) {

        // Call Schedule Agreement Update API [NWZC171001]
        NWZC171001 schdAgmtAPI = new NWZC171001();
        schdAgmtAPI.execute(schdAgmt, ONBATCH_TYPE.ONLINE);

        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(schdAgmt);
        for (S21ApiMessage msg : msgList) {
            String msgId = msg.getXxMsgid();
            if (ZYPCommonFunc.hasValue(msgId)) {
                msgMap.addXxMsgIdWithPrm(msgId, msg.getXxMsgPrmArray());
            }
        }

        for (int i = 0; i < schdAgmt.NWZC171002PMsg.getValidCount(); i++) {
            NWZC171002PMsg schdLine = schdAgmt.NWZC171002PMsg.no(i);

            List<S21ApiMessage> msgListIn = S21ApiUtil.getXxMsgList(schdLine);
            for (S21ApiMessage msg : msgListIn) {
                String msgId = msg.getXxMsgid();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    msgMap.addXxMsgIdWithPrm(msgId, msg.getXxMsgPrmArray());
                }
            }
        }
    }

    /**
     * hasError
     * @param pMsg NWZC172001PMsg
     * @return boolean
     */
    private boolean hasError(NWZC172001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * hasError.
     * @param linePMsgList List<NWZC172002PMsg>
     * @return boolean
     */
    private boolean hasError(List<NWZC172002PMsg> linePMsgList) {
        if (linePMsgList == null) {
            return false;
        }
        for (NWZC172002PMsg linePmsg : linePMsgList) {
            if (linePmsg.xxMsgIdList.getValidCount() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * flushListMsgMaps
     * @param linePMsgList List<NWZC172002PMsg>
     */
    private void flushListMsgMap(List<NWZC172002PMsg> linePMsgList) {
        if (linePMsgList == null) {
            return;
        }
        for (NWZC172002PMsg linePMsg : linePMsgList) {
            S21ApiMessageMap lineMsgMap  = new S21ApiMessageMap(linePMsg);
            lineMsgMap.flush();
        }

    }

    /**
     * Get Ship To Customer Info List
     * @param bizMsg NWAL1840CMsg
     * @return Ship To Customer Info List
     */
    public List<Map<String, Object>> getShipToCustInfoList(String glblCmpyCd, String shipToCustCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("shipToCustCd", shipToCustCd);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getShipToCustInfoList", params);
    }
    /**
     * Get Sales Credit Infomation
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param dsCpoConfigPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSalesCredit(String glblCmpyCd, String cpoOrdNum, BigDecimal dsCpoConfigPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("dsCpoConfigPk", dsCpoConfigPk);
        params.put("configCatgCd", CONFIG_CATG.OUTBOUND);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getSalesCredit", params);
    }
    /**
     * Get Sales Credit Infomation
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param dsCpoConfigPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getCtacPsn(String glblCmpyCd, String cpoOrdNum, BigDecimal dsCpoConfigPk) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("cpoOrdNum", cpoOrdNum);
        params.put("dsCpoConfigPk", dsCpoConfigPk);
        params.put("configCatgCd", CONFIG_CATG.OUTBOUND);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getCtacPsn", params);
    }

    private static String getStdCcyCd(String glblCmpyCd) {

        GLBL_CMPYTMsg glblCmpyMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyMsg.glblCmpyCd, glblCmpyCd);

        glblCmpyMsg = (GLBL_CMPYTMsg) S21ApiTBLAccessor.findByKey(glblCmpyMsg);

        if (glblCmpyMsg == null) {
            return null;
        } else {
            return glblCmpyMsg.stdCcyCd.getValue();
        }
    }
    // Add Start 2017/12/22 S21_NA#20164
    /**
    * getAttention
    * @param schdAgmt NWZC171001_DPMsg
    * @return String
    */
    private static String getAttention(NWZC171001_DPMsg schdAgmtCtac) {

        String attention = "";
        if (ZYPCommonFunc.hasValue(schdAgmtCtac.ctacPsnFirstNm)
                && ZYPCommonFunc.hasValue(schdAgmtCtac.ctacPsnLastNm)) {
            attention = schdAgmtCtac.ctacPsnFirstNm.getValue()
                    + SPACE
                    + schdAgmtCtac.ctacPsnLastNm.getValue();
        }

        return attention;
    }
    // Add End   2017/12/22 S21_NA#20164
    
    // 2018/02/08 S21_NA#20297(Sol#379) Add Start
    private void setDefShipInfo(NWZC171001PMsg schdAgmt, NWZC172001PMsg pMsg, //
            Map<String, Object> schdCratTmplData, SVC_MACH_MSTRTMsg svcMachMstrTMsg, DS_CPO_CONFIGTMsg dsCpoConfig) {
        
        if(null == (String) schdCratTmplData.get("LINE_BIZ_TP_CD") || null == (String) schdCratTmplData.get("DS_ORD_CATG_CD")//
                || null == (String) schdCratTmplData.get("DS_ORD_TP_CD") || !ZYPCommonFunc.hasValue(svcMachMstrTMsg.shipToCustCd)//
                || !ZYPCommonFunc.hasValue(dsCpoConfig.shipToCustAcctCd)){
            return;
        }

        setDefFrtInfo(schdAgmt, pMsg, schdCratTmplData, svcMachMstrTMsg.shipToCustCd.getValue(), dsCpoConfig.shipToCustAcctCd.getValue());
        setDefShpgCmt(schdAgmt, pMsg, schdCratTmplData, svcMachMstrTMsg.shipToCustCd.getValue());

    }
    
    private void setDefFrtInfo(NWZC171001PMsg schdAgmt, NWZC172001PMsg pMsg, Map<String, Object> schdCratTmplData, String shipToCustCd, String shipToCustAcctCd) {
        
        NMZC610001PMsg nmzc6100PMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsOrdCatgCd, (String) schdCratTmplData.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsOrdTpCd, (String) schdCratTmplData.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.shipToCustCd, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsAcctNum_I1, shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.slsDt, pMsg.slsDt);

        new NMZC610001().execute(nmzc6100PMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(nmzc6100PMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nmzc6100PMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(schdAgmt, msgId, msgPrms);
            }
        }
        if (hasValue(nmzc6100PMsg.ShippingDefaultInfoList.no(0).frtCondCd)) {
            ZYPEZDItemValueSetter.setValue(schdAgmt.frtCondCd, nmzc6100PMsg.ShippingDefaultInfoList.no(0).frtCondCd);
        }
        
        if (hasValue(nmzc6100PMsg.ShippingDefaultInfoList.no(0).shpgSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(schdAgmt.shpgSvcLvlCd, nmzc6100PMsg.ShippingDefaultInfoList.no(0).shpgSvcLvlCd);
        }
    }

    private void setDefShpgCmt(NWZC171001PMsg schdAgmt, NWZC172001PMsg pMsg, Map<String, Object> schdCratTmplData, String shipToCustCd) {
        
        if(hasValue(schdAgmt.shpgCmntTxt)){
            return;
        }
        
        NMZC610001PMsg nmzc6100PMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.lineBizTpCd, (String) schdCratTmplData.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsBizAreaCd, getDsBizAreaCd(schdAgmt, (String) schdCratTmplData.get("DS_ORD_CATG_CD")));
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SHIP);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.shipToCustCd, shipToCustCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.slsDt, pMsg.slsDt);

        new NMZC610001().execute(nmzc6100PMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(nmzc6100PMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nmzc6100PMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(schdAgmt, msgId, msgPrms);
            }
        }

        int count = nmzc6100PMsg.InstructionList.getValidCount();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < count; i++) {
            if (hasValue(nmzc6100PMsg.InstructionList.no(i).dsCustMsgTxt)) {
                if (i != 0) {
                    sb.append(LINE);
                }
                sb.append(nmzc6100PMsg.InstructionList.no(i).dsCustMsgTxt.getValue());
                if (sb.length() > SHPG_CMT_TXT_LIMIT_SIZE) {
                    ZYPEZDItemValueSetter.setValue(schdAgmt.shpgCmntTxt, sb.substring(0, SHPG_CMT_TXT_LIMIT_SIZE));
                    return;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(schdAgmt.shpgCmntTxt, sb.toString());
    }

    private static String getDsBizAreaCd(NWZC171001PMsg schdAgmt, String dsOrdCatgCd) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", schdAgmt.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).scdBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }
    
    private void setErrMsg(NWZC171001PMsg schdAgmt, String msgId, String[] msgPrms) {
        int cnt = schdAgmt.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(schdAgmt.xxMsgIdList.no(cnt).xxMsgId, msgId);
        schdAgmt.xxMsgIdList.setValidCount(cnt + 1);
    }
    // 2018/02/08 S21_NA#20297(Sol#379) Add End

    // 2018/05/24 S21_NA#26148 add start
    private static List<String> getTargetWriterList(String glblCmpyCd) {

        LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
        lineBizRoleTpTMsg.setSQLID("001");
        lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
        LINE_BIZ_ROLE_TPTMsgArray tMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
        List<String> targetWriterList = new ArrayList<String>();
        if (tMsgArray != null && tMsgArray.length() > 0) {
            for (int i = 0; i < tMsgArray.length(); i++) {
                LINE_BIZ_ROLE_TPTMsg tMsg = (LINE_BIZ_ROLE_TPTMsg) tMsgArray.get(i);
                targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
            }
        }

        return targetWriterList;
    }

    public static String getTrtyGrpTpTxtFromDsOrdTpCd(String glblCmpyCd, String dsOrdTpCd) {

        if (!ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            return dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();
        }

        return null;
    }

    private static String getSalesCreditPrecentMode(List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList){

        boolean hasWriter = false;
        boolean hasInstaller = false;
        if (slsRepPMsgList.size() == 1) {
            return "1";
        } else if (slsRepPMsgList.size() == 2) {
            for (NMZC260001_defSlsRepListPMsg data : slsRepPMsgList) {
                if (isWriter(data.lineBizRoleTpCd.getValue())) {
                    hasWriter = true;
                } else if (isInstaller(data.lineBizRoleTpCd.getValue())) {
                    hasInstaller = true;
                }
            }
            if (hasWriter && hasInstaller) {
                return "2";
            }
        }
        return "0";
    }

    /**
     * isWriter
     * @param slsRepRoleTpCd
     * @return
     */
    private static boolean isWriter(String slsRepRoleTpCd){
        List<String> writerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER, LINE_BIZ_ROLE_TP.EMSD_WRITER);
        return writerList.contains(slsRepRoleTpCd);
    }

    /**
     * isInstaller
     * @param slsRepRoleTpCd
     * @return
     */
    private static boolean isInstaller(String slsRepRoleTpCd){
        List<String> installerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_INSTALLER, LINE_BIZ_ROLE_TP.LFS_INSTALLER, LINE_BIZ_ROLE_TP.PPS_INSTALLER);
        return installerList.contains(slsRepRoleTpCd);
    }
    // 2018/05/24 S21_NA#26148 add end

    // 2018/06/25 S21_NA#23726 add start
    private boolean deriveDefaultCarrSvcLvl(NWZC171001PMsg schdAgmt, NWZC172001PMsg pMsg, Map<String, Object> schdCratTmplData, String shipToCustCd, String shpgSvcLvlCd, String dsBizAreaCd) {

        if (!ZYPCommonFunc.hasValue(shipToCustCd) || !ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
            return true;
        }

        // 2018/12/14 S21_NA#29315 Add Start
        String locNum = null;

        SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("shipToCustCd01", schdAgmt.shipToCustLocCd.getValue());
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        SHIP_TO_CUSTTMsgArray tmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray != null && tmsgArray.getValidCount() > 0) {
            locNum = tmsgArray.no(0).locNum.getValue();
        }
        // 2018/12/14 S21_NA#29315 Add End

        NMZC611001PMsg defCarrApiPMsg = new NMZC611001PMsg();
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.dsAcctNum, shipToCustCd);
        // 2018/12/14 S21_NA#29315 Add Start
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.locNum, locNum);
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.dsBizAreaCd, dsBizAreaCd);
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.lineBizTpCd, (String) schdCratTmplData.get("LINE_BIZ_TP_CD"));
        // 2018/12/14 S21_NA#29315 Add End

        new NMZC611001().execute(defCarrApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(defCarrApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defCarrApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(schdAgmt, msgId, msgPrms);
            }
            return false;
        }

        String vndCd = defCarrApiPMsg.vndCd_O.getValue();
        if (ZYPCommonFunc.hasValue(vndCd)) {
            ZYPEZDItemValueSetter.setValue(schdAgmt.carrAcctNum, defCarrApiPMsg.dsCarrAcctNum.getValue());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", pMsg.glblCmpyCd);
            param.put("shpgSvcLvlCd",shpgSvcLvlCd);
            param.put("carrCd", vndCd);

            String carrSvcLvlCd = (String) ssmClient.queryObject("getCarrSvcLvlCd", param);
            ZYPEZDItemValueSetter.setValue(schdAgmt.carrSvcLvlCd, carrSvcLvlCd);
        }

        return true;
    }
    // 2018/06/25 S21_NA#23726 add start
}
