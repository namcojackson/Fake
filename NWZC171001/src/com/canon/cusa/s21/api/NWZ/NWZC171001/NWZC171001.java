/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC171001;

import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.BLANK;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.DATE_FORMAT;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.DOCUMENT_TYPE;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.EVENT_ID_CHANGE;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.EVENT_ID_CREATE;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.EVENT_ID_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.MODE_ERROR;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.MODE_SAVE;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.MODE_SUBMIT;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM0011E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM0021E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM0097E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM0209E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM0977E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1158E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1253E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1255E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1377E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1378E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1401E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1402E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1403E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1405E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1764E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1765E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1799E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1806E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1807E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1847E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1850E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1851E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1852E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1853E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1854E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1855E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1856E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1857E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1858E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1859E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1860E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1861E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1862E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1863E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1864E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1865E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1866E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1867E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1868E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM1869E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM2031E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM2051W;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.NWZM2251E;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.PROCESS_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.REQUEST_TYPE_DEL;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.REQUEST_TYPE_MOD;
import static com.canon.cusa.s21.api.NWZ.NWZC171001.constant.NWZC171001Constant.REQUEST_TYPE_NEW;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDSeqTable;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BIZ_PROC_LOGTMsg;
import business.db.CCYTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.PRC_COND_TPTMsg;
import business.db.SCHD_AGMTTMsg;
import business.db.SCHD_AGMT_CALC_BASE_RECTMsg;
import business.db.SCHD_AGMT_CTAC_PSNTMsg;
import business.db.SCHD_AGMT_LINETMsg;
import business.db.SCHD_AGMT_LINETMsgArray;
import business.db.SCHD_AGMT_LINE_RECTMsg;
import business.db.SCHD_AGMT_PLNTMsg;
import business.db.SCHD_AGMT_PLNTMsgArray;
import business.db.SCHD_AGMT_PLN_RECTMsg;
import business.db.SCHD_AGMT_PRC_CALC_BASETMsg;
import business.db.SCHD_AGMT_PRC_CALC_BASETMsgArray;
import business.db.SCHD_AGMT_RECTMsg;
import business.db.SCHD_AGMT_SLS_CRTMsg;
import business.db.SCHD_AGMT_SLS_CRTMsgArray;
import business.db.SCHD_AGMT_SLS_CR_RECTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.parts.NWZC171001PMsg;
import business.parts.NWZC171001_CPMsg;
import business.parts.NWZC171001_DPMsg;
import business.parts.NWZC171001_EPMsg;
import business.parts.NWZC171002PMsg;
import business.parts.NWZC171003PMsg;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001EditPriceAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001EditPriceAmountInfo;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001UnitPriceData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SUB_SYS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/04   Fujitsu         Y.Kanefusa      Create          N/A
 * 2016/06/13   Fujitsu         Y.Kanefusa      Update          QC#6480
 * 2016/09/02   Fujitsu         H.Ikeda         Update          QC#13036
 * 2016/09/09   Fujitsu         T.Murai         Update          QC#13853
 * 2016/09/28   Fujitsu         T.Murai         Update          QC#14723
 * 2016/10/04   Fujitsu         T.Murai         Update          QC#11612
 * 2016/10/06   Fujitsu         T.Murai         Update          QC#11883
 * 2016/11/07   Fujitsu         H.Ikeda         UpDate          QC#15763
 * 2017/05/10   Hitachi         T.Tomita        Update          RS#8359
 * 2017/08/04   Fujitsu         W.Honda         Update          S21_NA#20228
 * 2017/08/15   Fujitsu         N.Sugiura       Update          S21_NA#16452
 * 2017/10/18   Fujitsu         S.Yamamoto      Update          S21_NA#20246
 * 2017/12/26   Fujitus         K.Ishizuka      Update          S21_NA#20164(Sol#356)
 * 2018/05/21   Fujitsu         Y.Kanefusa      Update          S21_NA#21841
 * 2018/08/03   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * 2018/09/14   Fujitsu         M.Ohno          Update          QC#9700
 * </pre>
 */
public class NWZC171001 extends S21ApiCommonBase {

    /** Now Date */
    private String nowDate = null;

    /** Message ID Manager */
    private S21ApiMessageIdMgr msgIdMgr = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType;

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;


    /**
     * Constructor
     */
    public NWZC171001() {
        super();
        msgIdMgr = new S21ApiMessageIdMgr();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWZC171001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NWZC171001PMsg param, final ONBATCH_TYPE onBatchType) {

        if (MODE_SUBMIT.equals(param.xxModeCd.getValue())) {
            if (paramCheck(param)) {
                return;
            }
        }

        if (deleteSaveData(param)) {
            return;
        }
        // Add Start 2016/10/04 S21_NA#11612
        nowDate = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);
        String newSchdAgmtNum = null;
        if (!ZYPCommonFunc.hasValue(param.schdAgmtNum)) {
            newSchdAgmtNum = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), "SA#");
         // Mod Start 2016/11/07 S21_NA#15763
        } else {
            if (NWZM2051W.equals(param.schdAgmtNum.getValue())) {
                param.schdAgmtNum.clear();
                newSchdAgmtNum = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), "SA#");
                S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
                msgMap.addXxMsgIdWithPrm(NWZM2051W, new String[] {newSchdAgmtNum});
                msgMap.flush();
            }
        }
        // Mod End   2016/11/07 S21_NA#15763
        // Add End 2016/10/04 S21_NA#11612
        // Mod Start 2016/10/04 S21_NA#11612
        Set<String> newLineSet = new HashSet<String>();
        if (registSchdAgmtLine(param, newSchdAgmtNum, newLineSet)) {
            return;
        }

        if (registSchdAgmt(param, newSchdAgmtNum)) {
            return;
        }

        if (registSchdAgmtPln(param, newLineSet)) {
            return;
        }


//        if (registSchdAgmt(param)) {
//            return;
//        }
//
//        if (registSchdAgmtLine(param)) {
//            return;
//        }
//        if (registSchdAgmtPln(param)) {
//            return;
//        }
        // Mod End 2016/10/04 S21_NA#11612

        if (registSchdAgmtSlsCr(param)) {
            return;
        }

        if (registSchdAgmtCtacPsn(param)) {
            return;
        }

        if (registSchdAgmtPrcCalcBase(param)) {
            return;
        }

        // Del Start 2016/10/05 S21_NA#11612
//        // Add Start 2016/09/09 S21_NA#13853
//        if (isLineAllCancel(param)) {
//            headerCancel(param);
//        }
//        // Add End 2016/09/09 S21_NA#13853
        // Del End 2016/10/05 S21_NA#11612

        // Mod 2016/10/05 S21_NA#11612
        //registBizProcLog(param);
        registBizProcLog(param, newLineSet);
        // Mod 2016/10/05 S21_NA#11612
    }

    /**
     * execute
     * @param params List<NWZC171001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWZC171001PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NWZC171001PMsg param : params) {
            execute(param, onBatchType);
        }
    }

    /**
     * Parameter Check
     * @param param NWZC171001PMsg
     * @return Check NG : true
     */
    private boolean paramCheck(NWZC171001PMsg param) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0011E);
            msgMap.flush();
            return true;
        }

        // Sales Date
        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NWZM0011E);
            msgMap.flush();
            return true;
        }

        // Mode
        // Mod 2017/08/04 S21_NA#20228
        // if (!S21StringUtil.isEquals(MODE_SAVE, param.xxModeCd.getValue()) && !S21StringUtil.isEquals(MODE_SUBMIT, param.xxModeCd.getValue())) {
        if (!S21StringUtil.isEquals(MODE_SAVE, param.xxModeCd.getValue()) 
                && !S21StringUtil.isEquals(MODE_SUBMIT, param.xxModeCd.getValue())
                && !S21StringUtil.isEquals(MODE_ERROR, param.xxModeCd.getValue())) {
        // Mod 2017/08/04 S21_NA#20228
            msgMap.addXxMsgId(NWZM0977E);
            msgMap.flush();
            return true;
        }

        // Scheduling Agreement Category Code
        // if (!ZYPCommonFunc.hasValue(param.schdAgmtCatgCd)) {
        // msgMap.addXxMsgId(NWZM1845E);
        // msgMap.flush();
        // return true;
        // }

        // DS Order Category Code
        if (!ZYPCommonFunc.hasValue(param.dsOrdCatgCd)) {
            msgMap.addXxMsgId(NWZM1401E);
            msgMap.flush();
            return true;
        }

        // DS Order Type
        if (!ZYPCommonFunc.hasValue(param.dsOrdTpCd)) {
            msgMap.addXxMsgId(NWZM1253E);
            msgMap.flush();
            return true;
        }

        // CPO Order Type Code
        if (!ZYPCommonFunc.hasValue(param.cpoOrdTpCd)) {
            msgMap.addXxMsgId(NWZM0097E);
            msgMap.flush();
            return true;
        }
        
        // Add Start 2016/10/06 S21_NA#11883
        if (!checkContrCatgRelation(param)) {
            msgMap.addXxMsgId(NWZM2031E);
            msgMap.flush();
            return true;
        }
        // Add Start 2016/10/06 S21_NA#11883

        // Price Category Code
        if (!ZYPCommonFunc.hasValue(param.prcCatgCd)) {
            msgMap.addXxMsgId(NWZM1405E);
            msgMap.flush();
            return true;
        }

        // Valid From
        if (!ZYPCommonFunc.hasValue(param.schdAgmtVldFromDt)) {
            msgMap.addXxMsgId(NWZM1764E);
            msgMap.flush();
            return true;
        }

        // Valid Thru
        if (!ZYPCommonFunc.hasValue(param.schdAgmtVldThruDt)) {
            msgMap.addXxMsgId(NWZM1765E);
            msgMap.flush();
            return true;
        }

        // Sold To Customer Account Code
        if (!ZYPCommonFunc.hasValue(param.sellToCustCd)) {
            msgMap.addXxMsgId(NWZM1402E);
            msgMap.flush();
            return true;
        }

        // Sold To Customer Location Code
        if (!ZYPCommonFunc.hasValue(param.soldToCustLocCd)) {
            msgMap.addXxMsgId(NWZM1403E);
            msgMap.flush();
            return true;
        }

        // Sold To Customer Location Code
        if (!ZYPCommonFunc.hasValue(param.soldToCustLocCd)) {
            msgMap.addXxMsgId(NWZM1403E);
            msgMap.flush();
            return true;
        }

        // Bill To Customer Account Code
        if (!ZYPCommonFunc.hasValue(param.billToCustAcctCd)) {
            msgMap.addXxMsgId(NWZM1377E);
            msgMap.flush();
            return true;
        }

        // Bill To Customer Location Code
        if (!ZYPCommonFunc.hasValue(param.billToCustLocCd)) {
            msgMap.addXxMsgId(NWZM1378E);
            msgMap.flush();
            return true;
        }

        // Pricing Base Date
        if (!ZYPCommonFunc.hasValue(param.prcBaseDt)) {
            msgMap.addXxMsgId(NWZM1799E);
            msgMap.flush();
            return true;
        }

        if (!ZYPCommonFunc.hasValue(param.frtCondCd)) {
            msgMap.addXxMsgId(NWZM1158E);
            msgMap.flush();
            return true;
        }
        if (!ZYPCommonFunc.hasValue(param.shpgSvcLvlCd)) {
            msgMap.addXxMsgId(NWZM1255E);
            msgMap.flush();
            return true;
        }
        
        // START 2017/12/26 K.Ishizuka [QC#20164, ADD]
        // Attention
        if (!ZYPCommonFunc.hasValue(param.sellToFirstRefCmntTxt)) {
            msgMap.addXxMsgId(NWZM2251E);
            msgMap.flush();
            return true;
        }
        // END 2017/12/26 K.Ishizuka [QC#20164, ADD]

        for (int i = 0; i < param.NWZC171002PMsg.getValidCount(); i++) {
            NWZC171002PMsg line = param.NWZC171002PMsg.no(i);
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);

            // Scheduling Agreement Line Number
            if (!ZYPCommonFunc.hasValue(line.schdAgmtLineNum)) {
                lineMap.addXxMsgId(NWZM1847E);
                lineMap.flush();
                return true;
            }

            // Merchandise Code
            if (!ZYPCommonFunc.hasValue(line.mdseCd)) {
                lineMap.addXxMsgId(NWZM0021E);
                lineMap.flush();
                return true;
            }

            // Quantity Allowed
            if (BigDecimal.ZERO.compareTo(line.schdAllwQty.getValue()) > 0) {
                lineMap.addXxMsgId(NWZM1850E);
                lineMap.flush();
                return true;
            }
        }

        for (int i = 0; i < param.NWZC171003PMsg.getValidCount(); i++) {
            NWZC171003PMsg schdLine = param.NWZC171003PMsg.no(i);
            S21ApiMessageMap schdLineMap = new S21ApiMessageMap(schdLine);

            // Scheduling Agreement Line Number
            if (!ZYPCommonFunc.hasValue(schdLine.schdAgmtLineNum)) {
                schdLineMap.addXxMsgId(NWZM1847E);
                schdLineMap.flush();
                return true;
            }

            // Scheduling Agreement Scheduled Number
            if (!ZYPCommonFunc.hasValue(schdLine.schdAgmtSchdLineNum)) {
                schdLineMap.addXxMsgId(NWZM1847E);
                schdLineMap.flush();
                return true;
            }

            // Requested Delivery Date
            if (!ZYPCommonFunc.hasValue(schdLine.rddDt)) {
                schdLineMap.addXxMsgId(NWZM1847E);
                schdLineMap.flush();
                return true;
            }

            // Order Quantity
            if (BigDecimal.ZERO.compareTo(schdLine.ordQty.getValue()) > 0) {
                schdLineMap.addXxMsgId(NWZM1850E);
                schdLineMap.flush();
                return true;
            }
        }

        for (int i = 0; i < param.C.getValidCount(); i++) {
            NWZC171001_CPMsg slsCrLine = param.C.no(i);
            // Request Type
            if (!S21StringUtil.isEquals(REQUEST_TYPE_NEW, slsCrLine.xxRqstTpCd.getValue()) && !S21StringUtil.isEquals(REQUEST_TYPE_MOD, slsCrLine.xxRqstTpCd.getValue())
                    && !S21StringUtil.isEquals(REQUEST_TYPE_DEL, slsCrLine.xxRqstTpCd.getValue())) {
                msgMap.addXxMsgId(NWZM0209E);
                msgMap.flush();
                return true;
            }

            if (!S21StringUtil.isEquals(REQUEST_TYPE_NEW, slsCrLine.xxRqstTpCd.getValue())) {
                // Scheduling Agreement Sales Credit Primary Key
                if (!ZYPCommonFunc.hasValue(slsCrLine.schdAgmtSlsCrPk)) {
                    msgMap.addXxMsgId(NWZM1851E);
                    msgMap.flush();
                    return true;
                }
            }
        }

        for (int i = 0; i < param.D.getValidCount(); i++) {
            NWZC171001_DPMsg ctacLine = param.D.no(i);
            // Request Type
            if (!S21StringUtil.isEquals(REQUEST_TYPE_NEW, ctacLine.xxRqstTpCd.getValue()) && !S21StringUtil.isEquals(REQUEST_TYPE_MOD, ctacLine.xxRqstTpCd.getValue())
                    && !S21StringUtil.isEquals(REQUEST_TYPE_DEL, ctacLine.xxRqstTpCd.getValue())) {
                msgMap.addXxMsgId(NWZM0209E);
                msgMap.flush();
                return true;
            }

            if (!S21StringUtil.isEquals(REQUEST_TYPE_NEW, ctacLine.xxRqstTpCd.getValue())) {
                // :Scheduling Agreement Contact Person Primary Key
                if (!ZYPCommonFunc.hasValue(ctacLine.schdAgmtCtacPsnPk)) {
                    msgMap.addXxMsgId(NWZM1851E);
                    msgMap.flush();
                    return true;
                }
                // Contact Type Code
                if (!ZYPCommonFunc.hasValue(ctacLine.ctacPsnTpCd)) {
                    msgMap.addXxMsgId(NWZM1806E);
                    msgMap.flush();
                    return true;
                }
                // First Name
                if (!ZYPCommonFunc.hasValue(ctacLine.ctacPsnLastNm)) {
                    msgMap.addXxMsgId(NWZM1807E);
                    msgMap.flush();
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Delete Save Data
     * @param param NWZC171001PMsg
     * @return Delete NG : true
     */
    private boolean deleteSaveData(NWZC171001PMsg param) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        String schdAgmtNum = param.schdAgmtNum.getValue();
        if (ZYPCommonFunc.hasValue(schdAgmtNum)) {
            return false;
        }

        SCHD_AGMTTMsg schdAgmtTMsg = getSchdAgmtForUpdate(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
        if (schdAgmtTMsg == null) {
            return false;
        }

        // Mod Start 2017/08/04 S21_NA#20228
//        if (!SCHD_AGMT_STS.SAVED.equals(schdAgmtTMsg.schdAgmtStsCd.getValue())) {
        if (!SCHD_AGMT_STS.SAVED.equals(schdAgmtTMsg.schdAgmtStsCd.getValue())
                && !SCHD_AGMT_STS.ERROR.equals(schdAgmtTMsg.schdAgmtStsCd.getValue())) {
        // Mod Start 2017/08/04 S21_NA#20228
            return false;
        }

        if (removePhysicalSchdAgmt(schdAgmtTMsg)) {
            msgMap.addXxMsgId(NWZM1854E);
            msgMap.flush();
            return true;
        }

        SCHD_AGMT_LINETMsgArray schdAgmtLineTMsgArray = getSchdAgmtLineArray(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
        if (removePhysicalSchdAgmtLine(schdAgmtLineTMsgArray)) {
            msgMap.addXxMsgId(NWZM1857E);
            msgMap.flush();
            return true;
        }

        SCHD_AGMT_PLNTMsgArray schdAgmtPlnTMsgArray = getSchdAgmtPlnArray(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
        if (removePhysicalSchdAgmtPln(schdAgmtPlnTMsgArray)) {
            msgMap.addXxMsgId(NWZM1860E);
            msgMap.flush();
            return true;
        }

        SCHD_AGMT_PRC_CALC_BASETMsgArray schdAgmtPrcCalcBaseTMsgArray = getSchdAgmtPrcCalcBaseArrayForUpdate(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
        if (removePhysicalSchdAgmtPrcCalcBase(schdAgmtPrcCalcBaseTMsgArray)) {
            msgMap.addXxMsgId(NWZM1869E);
            msgMap.flush();
            return true;
        }

        return false;
    }

    /**
     * Regist Schedule Agreement Data
     * @param param NWZC171001PMsg
     * @return Regist NG : true
     */
    private boolean registSchdAgmt(NWZC171001PMsg param , String schdAgmtNum) { // Add 2016/10/05 S21_NA#11612 String schdAgmtNum

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        SCHD_AGMTTMsg schdAgmtTMsg = null;
        
        String eventId = null; // Add 2016/10/04 S21_NA#11612

        // Mod Start 2016/10/05 S21_NA#11612
//        if (!ZYPCommonFunc.hasValue(param.schdAgmtNum)) {
//            String newSchdAgmtNum = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), "SA#");
//            ZYPEZDItemValueSetter.setValue(param.schdAgmtNum, newSchdAgmtNum);
//        } else {
//            schdAgmtTMsg = getSchdAgmtForUpdate(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
//        }
        if (!ZYPCommonFunc.hasValue(param.schdAgmtNum)) {
            ZYPEZDItemValueSetter.setValue(param.schdAgmtNum, schdAgmtNum);
            eventId = EVENT_ID_CREATE; // Add 2016/10/04 S21_NA#11612
        } else {
            schdAgmtTMsg = getSchdAgmtForUpdate(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
            eventId = EVENT_ID_CHANGE; // Add 2016/10/04 S21_NA#11612
        }
        // Mod End 2016/10/05 S21_NA#11612

        if (schdAgmtTMsg == null) {
            schdAgmtTMsg = new SCHD_AGMTTMsg();
            setSchdAgmt(param, schdAgmtTMsg);
            S21ApiTBLAccessor.insert(schdAgmtTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM1852E);
                msgMap.flush();
                return true;
            }
        } else {
            setSchdAgmt(param, schdAgmtTMsg);
            // Add Start 2016/10/05 S21_NA#11612
            if (isLineAllCancel(param)) {
                eventId = EVENT_ID_DELETE; 
                schdAgmtTMsg.schdAgmtStsCd.setValue(SCHD_AGMT_STS.CANCELLED);
            }
            // Add End 2016/10/05 S21_NA#11612
            S21ApiTBLAccessor.update(schdAgmtTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM1853E);
                msgMap.flush();
                return true;
            }
        }

        // Add Start 2016/10/04 S21_NA#11612
        int bizProcLogPk = 0;
        SCHD_AGMT_RECTMsg schdAgmtRecTMsg = new SCHD_AGMT_RECTMsg();

        bizProcLogPk = createBizProcLog(eventId, BLANK, param.schdAgmtNum.getValue());
        EZDMsg.copy(schdAgmtTMsg, null, schdAgmtRecTMsg, null);
        ZYPEZDItemValueSetter.setValue(schdAgmtRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
        S21ApiTBLAccessor.insert(schdAgmtRecTMsg);
        // Add End 2016/10/04 S21_NA#11612

        return false;
    }

    /**
     * Set Schedule Agreement Data
     * @param param NWZC171001PMsg
     * @param tMsg SCHD_AGMTTMsg
     */
    private void setSchdAgmt(NWZC171001PMsg param, SCHD_AGMTTMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtNum, param.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(tMsg.refCpoOrdNum, param.refCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(tMsg.schdCratTmplPk, param.schdCratTmplPk);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtCatgCd, param.schdAgmtCatgCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, param.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, param.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdRsnCd, param.dsOrdRsnCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdTpCd, param.cpoOrdTpCd);
        if (MODE_SAVE.equals(param.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtStsCd, SCHD_AGMT_STS.SAVED);
        // Mod Start 2017/08/04 S21_NA#20228
//        } else {
        } else if (MODE_SUBMIT.equals(param.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtStsCd, SCHD_AGMT_STS.ACTIVE);
//        }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtStsCd, SCHD_AGMT_STS.ERROR);
        }
        // Mod End 2017/08/04 S21_NA#20228
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdTpCd, param.cpoOrdTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdTpCd, param.cpoOrdTpCd);
        if (ZYPCommonFunc.hasValue(param.schdAgmtCratDt)) {
            ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtCratDt, param.schdAgmtCratDt);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtCratDt, param.slsDt);
        }
        // Mod Start 2017/08/04 S21_NA#20228
//        if (MODE_SAVE.equals(param.xxModeCd.getValue())) {
        if (MODE_SAVE.equals(param.xxModeCd.getValue())
                || MODE_ERROR.equals(param.xxModeCd.getValue())) {
        // Mod End 2017/08/04 S21_NA#20228
            tMsg.schdAgmtSubmtTs.clear();
        } else {
            if (!ZYPCommonFunc.hasValue(tMsg.schdAgmtSubmtTs)) {
                ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtSubmtTs, nowDate);
            }
        }
        ZYPEZDItemValueSetter.setValue(tMsg.adminPsnCd, param.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcBaseDt, param.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCalcDt, param.prcCalcDt);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, param.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(tMsg.flPrcListCd, param.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, param.mdlId);
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, param.serNum);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, param.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrNum, param.dsContrNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrSqNum, param.dsContrSqNum);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoSrcTpCd, param.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordSrcRefNum, param.ordSrcRefNum);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtVldFromDt, param.schdAgmtVldFromDt);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtVldThruDt, param.schdAgmtVldThruDt);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtDelyHldCd, param.schdAgmtDelyHldCd);
        ZYPEZDItemValueSetter.setValue(tMsg.custIssPoNum, param.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(tMsg.custIssPoDt, param.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(tMsg.slsRepTocCd, param.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(tMsg.carrCd, param.carrCd);
        ZYPEZDItemValueSetter.setValue(tMsg.carrAcctNum, param.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.spclHdlgTpCd, param.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, param.frtCondCd);
        ZYPEZDItemValueSetter.setValue(tMsg.carrSvcLvlCd, param.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, param.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtChrgToCd, param.frtChrgToCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtChrgMethCd, param.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, param.pmtTermCashDiscCd);
        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = getpmtTermCashDisc(param.glblCmpyCd.getValue(), param.pmtTermCashDiscCd.getValue());
        if (pmtTermCashDiscTMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCd, pmtTermCashDiscTMsg.pmtTermCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.cashDiscTermCd, pmtTermCashDiscTMsg.cashDiscTermCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(tMsg.sellToCustCd, param.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(tMsg.soldToCustLocCd, param.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, param.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(tMsg.billToCustLocCd, param.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustAcctCd, param.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustLocCd, param.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(tMsg.sellToFirstRefCmntTxt, param.sellToFirstRefCmntTxt);// S21_NA ADD QC#20246
        ZYPEZDItemValueSetter.setValue(tMsg.dropShipFlg, param.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToLocNm, param.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToAddlLocNm, param.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToFirstLineAddr, param.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToScdLineAddr, param.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToThirdLineAddr, param.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToFrthLineAddr, param.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCtyAddr, param.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToStCd, param.shipToStCd);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToProvNm, param.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToPostCd, param.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCtryCd, param.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(tMsg.shipToCntyNm, param.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(tMsg.shipTo01RefCmntTxt, param.shipTo01RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.shipTo02RefCmntTxt, param.shipTo02RefCmntTxt);
        SELL_TO_CUSTTMsg sellToCustTMsg = getSellToCust(param.glblCmpyCd.getValue(), param.sellToCustCd.getValue());
        if (sellToCustTMsg != null) {
//            ZYPEZDItemValueSetter.setValue(tMsg.sellToFirstRefCmntTxt, sellToCustTMsg.firstRefCmntTxt);// S21_NA ADD QC#20246
            ZYPEZDItemValueSetter.setValue(tMsg.sellToScdRefCmntTxt, sellToCustTMsg.scdRefCmntTxt);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.shpgCmntTxt, param.shpgCmntTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.itrlOrdCmntTxt, param.itrlOrdCmntTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.invCmntTxt, param.invCmntTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrNum, param.prcContrNum);
    }

    /**
     * Regist Schedule Agreement Line Data
     * @param param NWZC171001PMsg
     * @return Regist NG : true
     */
    private boolean registSchdAgmtLine(NWZC171001PMsg param, String schdAgmtNum, Set<String> newLineSet) { // Add 2016/10/05 S21_NA#11612 String schdAgmtNum, Set newLineSet

        if (!ZYPCommonFunc.hasValue(schdAgmtNum)) {
            schdAgmtNum = param.schdAgmtNum.getValue();
        }
        
        SCHD_AGMT_LINETMsg schdAgmtLineTMsg = null;
        for (int i = 0; i < param.NWZC171002PMsg.getValidCount(); i++) {
            NWZC171002PMsg line = param.NWZC171002PMsg.no(i);
            S21ApiMessageMap lineMap = new S21ApiMessageMap(line);
            // Mod 2016/10/05 S21_NA#11612
            //schdAgmtLineTMsg = getSchdAgmtLineForUpdate(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue(), line.schdAgmtLineNum.getValue());
            schdAgmtLineTMsg = getSchdAgmtLineForUpdate(param.glblCmpyCd.getValue(), schdAgmtNum, line.schdAgmtLineNum.getValue());
            
            if (schdAgmtLineTMsg == null) {
                schdAgmtLineTMsg = new SCHD_AGMT_LINETMsg();
                ZYPEZDItemValueSetter.setValue(schdAgmtLineTMsg.schdAgmtNum, schdAgmtNum); 
                setSchdAgmtLine(param, line, schdAgmtLineTMsg);
                newLineSet.add(line.schdAgmtLineNum.getValue().toPlainString()); // Add 2016/10/05 S21_NA#11612
                S21ApiTBLAccessor.insert(schdAgmtLineTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtLineTMsg.getReturnCode())) {
                    lineMap.addXxMsgId(NWZM1855E);
                    lineMap.flush();
                    return true;
                }
            } else {
                setSchdAgmtLine(param, line, schdAgmtLineTMsg);
                S21ApiTBLAccessor.update(schdAgmtLineTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtLineTMsg.getReturnCode())) {
                    lineMap.addXxMsgId(NWZM1856E);
                    lineMap.flush();
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Set Schedule Agreement Line Data
     * @param param NWZC171001PMsg
     * @param line NWZC171002PMsg
     * @param tMsg SCHD_AGMT_LINETMsg
     */
    private void setSchdAgmtLine(NWZC171001PMsg param, NWZC171002PMsg line, SCHD_AGMT_LINETMsg tMsg) {

        //final Integer ccyScale = getDealCcyDigit(param.glblCmpyCd.getValue(), line.ccyCd.getValue());

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtNum, param.schdAgmtNum); // Del 2016/10/05 S21_NA#11612
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineNum, line.schdAgmtLineNum);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, line.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(tMsg.schdCratTmplPk, line.schdCratTmplPk);
        ZYPEZDItemValueSetter.setValue(tMsg.schdCratTmplLineNum, line.schdCratTmplLineNum);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, line.mdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseNm, line.mdseNm);
        ZYPEZDItemValueSetter.setValue(tMsg.sbstMdseCd, line.sbstMdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, line.stkStsCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, line.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAllwQty, line.schdAllwQty);
        ZYPEZDItemValueSetter.setValue(tMsg.shpgIntvlCd, line.shpgIntvlCd);
        ZYPEZDItemValueSetter.setValue(tMsg.otmShipQty, line.otmShipQty);
        ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, line.ccyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.manPrcFlg, line.manPrcFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.supdLockFlg, line.supdLockFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineCancFlg, line.schdAgmtLineCancFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineCratDt, nowDate.substring(0, 8));

        List<NWZC171001_EPMsg> priceList = getPriceList(param, line.schdAgmtLineNum.getValue());
        BigDecimal basePrice = getGrsPrice(priceList);
        NWXC001001UnitPriceData baseUnitPrcData = callNWXC001001EditPriceAmount(param, line, basePrice, basePrice);
        if (hasError(line)) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.dealPrcListPrcAmt, baseUnitPrcData.getDealNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.funcPrcListPrcAmt, baseUnitPrcData.getFuncNetUnitPrcAmt());

        BigDecimal netPrice = getNetPrice(priceList);
        NWXC001001UnitPriceData unitPrcData = callNWXC001001EditPriceAmount(param, line, netPrice, netPrice);
        if (hasError(line)) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.dealNetUnitPrcAmt, unitPrcData.getDealNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.funcNetUnitPrcAmt, unitPrcData.getFuncNetUnitPrcAmt());

        BigDecimal netAmt = getNetAmt(priceList);
        NWXC001001UnitPriceData netAMtData = callNWXC001001EditPriceAmount(param, line, netAmt, netAmt);
        if (hasError(line)) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineDealNetAmt, netAMtData.getDealNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineFuncNetAmt, netAMtData.getFuncNetUnitPrcAmt());

        // 2016/09/02 S21_NA#13036 Mod Start
//        BigDecimal frtUnitPrc = getFrtPrice(priceList);
//        NWXC001001UnitPriceData frtUnitPrcData = callNWXC001001EditPriceAmount(param, line, frtUnitPrc, frtUnitPrc);
//        if (hasError(line)) {
//            return;
//        }

        BigDecimal frtAmt = getFrtAmt(priceList);
        NWXC001001UnitPriceData frtAmtData = callNWXC001001EditPriceAmount(param, line, frtAmt, frtAmt);
        if (hasError(line)) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineDealFrtAmt, frtAmtData.getDealNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineFuncFrtAmt, frtAmtData.getFuncNetUnitPrcAmt());
        // 2016/09/02 S21_NA#13036 Mod Start

        // 2016/09/02 S21_NA#13036 Mod Start
//        BigDecimal taxAmt = getTaxPrice(priceList);
//        NWXC001001UnitPriceData taxUnitPrcData = callNWXC001001EditPriceAmount(param, line, taxAmt, taxAmt);
//        if (hasError(line)) {
//            return;
//        }
        BigDecimal taxAmt = getCalcPrcAmtRate(priceList);
        NWXC001001UnitPriceData taxUnitPrcData = callNWXC001001EditPriceAmount(param, line, taxAmt, taxAmt);
        if (hasError(line)) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineDealTaxAmt, taxUnitPrcData.getDealNetUnitPrcAmt());
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineFuncTaxAmt, taxUnitPrcData.getFuncNetUnitPrcAmt());
        // 2016/09/02 S21_NA#13036 Mod Start

    }

    /**
     * Regist Schedule Agreement Plan Data
     * @param param NWZC171001PMsg
     * @return Regist NG : true
     */
    private boolean registSchdAgmtPln(NWZC171001PMsg param, Set<String> newPlnSet) { // Add 2016/010/05 S21_NA#11612

        SCHD_AGMT_PLNTMsg schdAgmtPlnTMsg = null;
        for (int i = 0; i < param.NWZC171003PMsg.getValidCount(); i++) {
            NWZC171003PMsg schdLine = param.NWZC171003PMsg.no(i);
            S21ApiMessageMap lineMap = new S21ApiMessageMap(schdLine);
            schdAgmtPlnTMsg = getSchdAgmtPlnForUpdate(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue(), schdLine.schdAgmtLineNum.getValue(), schdLine.schdAgmtSchdLineNum.getValue());
            if (schdAgmtPlnTMsg == null) {
                schdAgmtPlnTMsg = new SCHD_AGMT_PLNTMsg();
                setSchdAgmtSchdLine(param, schdLine, schdAgmtPlnTMsg);
                newPlnSet.add(schdLine.schdAgmtLineNum.getValue().toPlainString() +"." + schdLine.schdAgmtSchdLineNum.getValue().toPlainString());
                S21ApiTBLAccessor.insert(schdAgmtPlnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtPlnTMsg.getReturnCode())) {
                    lineMap.addXxMsgId(NWZM1858E);
                    lineMap.flush();
                    return true;
                }
            } else {
                setSchdAgmtSchdLine(param, schdLine, schdAgmtPlnTMsg);
                S21ApiTBLAccessor.update(schdAgmtPlnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtPlnTMsg.getReturnCode())) {
                    lineMap.addXxMsgId(NWZM1859E);
                    lineMap.flush();
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Set Schedule Agreement Line Data
     * @param param NWZC171001PMsg
     * @param schdLine NWZC171003PMsg
     * @param tMsg SCHD_AGMT_PLNTMsg
     */
    private void setSchdAgmtSchdLine(NWZC171001PMsg param, NWZC171003PMsg schdLine, SCHD_AGMT_PLNTMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtNum, param.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineNum, schdLine.schdAgmtLineNum);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtSchdLineNum, schdLine.schdAgmtSchdLineNum);
        ZYPEZDItemValueSetter.setValue(tMsg.rddDt, schdLine.rddDt);
        ZYPEZDItemValueSetter.setValue(tMsg.ordQty, schdLine.ordQty);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtPlnCancFlg, schdLine.schdAgmtPlnCancFlg);
    }

    /**
     * Regist Schedule Agreement Sales Credit Data
     * @param param NWZC171001PMsg
     * @return Regist NG : true
     */
    private boolean registSchdAgmtSlsCr(NWZC171001PMsg param) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        SCHD_AGMT_SLS_CRTMsg schdAgmtSlsCrTMsg = null;
        List<SCHD_AGMT_SLS_CRTMsg> rmvlist = new ArrayList<SCHD_AGMT_SLS_CRTMsg>();
        List<SCHD_AGMT_SLS_CR_RECTMsg> slsCrList = new ArrayList<SCHD_AGMT_SLS_CR_RECTMsg>();

        int bizProcLogPk = 0;

        for (int i = 0; i < param.C.getValidCount(); i++) {
            NWZC171001_CPMsg slsCrLine = param.C.no(i);
            if (REQUEST_TYPE_NEW.equals(slsCrLine.xxRqstTpCd.getValue())) {
                schdAgmtSlsCrTMsg = new SCHD_AGMT_SLS_CRTMsg();
                setSchdAgmtSlsCr(param, slsCrLine, schdAgmtSlsCrTMsg);
                S21ApiTBLAccessor.insert(schdAgmtSlsCrTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtSlsCrTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM1861E);
                    msgMap.flush();
                    return true;
                }
                SCHD_AGMT_SLS_CR_RECTMsg schdAgmtSlsCrRecTMsg = new SCHD_AGMT_SLS_CR_RECTMsg();
                bizProcLogPk = createBizProcLog(EVENT_ID_CREATE, BLANK, param.schdAgmtNum.getValue());
                EZDMsg.copy(schdAgmtSlsCrTMsg, null, schdAgmtSlsCrRecTMsg, null);
                ZYPEZDItemValueSetter.setValue(schdAgmtSlsCrRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
                slsCrList.add(schdAgmtSlsCrRecTMsg);

            } else if (REQUEST_TYPE_MOD.equals(slsCrLine.xxRqstTpCd.getValue())) {
                schdAgmtSlsCrTMsg = getSchdAgmtSlsCrForUpdate(param.glblCmpyCd.getValue(), slsCrLine.schdAgmtSlsCrPk.getValue());
                if (schdAgmtSlsCrTMsg == null) {
                    msgMap.addXxMsgId(NWZM1862E);
                    msgMap.flush();
                    return true;
                }
                setSchdAgmtSlsCr(param, slsCrLine, schdAgmtSlsCrTMsg);
                S21ApiTBLAccessor.update(schdAgmtSlsCrTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtSlsCrTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM1862E);
                    msgMap.flush();
                    return true;
                }

                SCHD_AGMT_SLS_CR_RECTMsg schdAgmtSlsCrRecTMsg = new SCHD_AGMT_SLS_CR_RECTMsg();
                bizProcLogPk = createBizProcLog(EVENT_ID_CHANGE, BLANK, param.schdAgmtNum.getValue());
                EZDMsg.copy(schdAgmtSlsCrTMsg, null, schdAgmtSlsCrRecTMsg, null);
                ZYPEZDItemValueSetter.setValue(schdAgmtSlsCrRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
                slsCrList.add(schdAgmtSlsCrRecTMsg);
            } else if (REQUEST_TYPE_DEL.equals(slsCrLine.xxRqstTpCd.getValue())) {
                schdAgmtSlsCrTMsg = getSchdAgmtSlsCrForUpdate(param.glblCmpyCd.getValue(), slsCrLine.schdAgmtSlsCrPk.getValue());
                if (schdAgmtSlsCrTMsg == null) {
                    msgMap.addXxMsgId(NWZM1863E);
                    msgMap.flush();
                    return true;
                }
                rmvlist.add(schdAgmtSlsCrTMsg);

                SCHD_AGMT_SLS_CR_RECTMsg schdAgmtSlsCrRecTMsg = new SCHD_AGMT_SLS_CR_RECTMsg();
                bizProcLogPk = createBizProcLog(EVENT_ID_DELETE, BLANK, param.schdAgmtNum.getValue());
                EZDMsg.copy(schdAgmtSlsCrTMsg, null, schdAgmtSlsCrRecTMsg, null);
                ZYPEZDItemValueSetter.setValue(schdAgmtSlsCrRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
                slsCrList.add(schdAgmtSlsCrRecTMsg);
            }
        }

        if (rmvlist.size() > 0) {
            boolean ret = removeLogicalSchdAgmtSlsCr(rmvlist);
            if (ret) {
                msgMap.addXxMsgId(NWZM1863E);
                msgMap.flush();
                return true;
            }
        }

        if (slsCrList.size() > 0) {
        //if (rmvlist.size() > 0) {// Mod 2016/09/28 S21_NA#14723
            S21FastTBLAccessor.insert(slsCrList.toArray(new SCHD_AGMT_SLS_CR_RECTMsg[0]));
        }

        return false;
    }

    /**
     * Set Schedule Agreement Sales Credit Data
     * @param param NWZC171001PMsg
     * @param slsCrLine NWZC171001_CPMsg
     * @param tMsg SCHD_AGMT_SLS_CRTMsg
     */
    private void setSchdAgmtSlsCr(NWZC171001PMsg param, NWZC171001_CPMsg slsCrLine, SCHD_AGMT_SLS_CRTMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(tMsg.schdAgmtSlsCrPk)) {
            ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_AGMT_SLS_CR_SQ));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtNum, param.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(tMsg.slsRepTocCd, slsCrLine.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsRepRoleTpCd, slsCrLine.slsRepRoleTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.slsRepCrPct, slsCrLine.slsRepCrPct);
        ZYPEZDItemValueSetter.setValue(tMsg.slsCrQuotFlg, slsCrLine.slsCrQuotFlg);
    }

    /**
     * Regist Schedule Agreement Contact Person Data
     * @param param NWZC171001PMsg
     * @return Regist NG : true
     */
    private boolean registSchdAgmtCtacPsn(NWZC171001PMsg param) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        SCHD_AGMT_CTAC_PSNTMsg schdAgmtCtacPsnTMsg = null;
        List<SCHD_AGMT_CTAC_PSNTMsg> list = new ArrayList<SCHD_AGMT_CTAC_PSNTMsg>();

        for (int i = 0; i < param.D.getValidCount(); i++) {
            NWZC171001_DPMsg ctacPsnLine = param.D.no(i);
            if (REQUEST_TYPE_NEW.equals(ctacPsnLine.xxRqstTpCd.getValue())) {
                schdAgmtCtacPsnTMsg = new SCHD_AGMT_CTAC_PSNTMsg();
                setSchdAgmtCtacPsn(param, ctacPsnLine, schdAgmtCtacPsnTMsg);
                S21ApiTBLAccessor.insert(schdAgmtCtacPsnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtCtacPsnTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM1864E);
                    msgMap.flush();
                    return true;
                }
            } else if (REQUEST_TYPE_MOD.equals(ctacPsnLine.xxRqstTpCd.getValue())) {
                schdAgmtCtacPsnTMsg = getSchdAgmtCtacPsnForUpdate(param.glblCmpyCd.getValue(), ctacPsnLine.schdAgmtCtacPsnPk.getValue());
                if (schdAgmtCtacPsnTMsg == null) {
                    msgMap.addXxMsgId(NWZM1865E);
                    msgMap.flush();
                    return true;
                }
                setSchdAgmtCtacPsn(param, ctacPsnLine, schdAgmtCtacPsnTMsg);
                S21ApiTBLAccessor.update(schdAgmtCtacPsnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtCtacPsnTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM1865E);
                    msgMap.flush();
                    return true;
                }
            } else if (REQUEST_TYPE_DEL.equals(ctacPsnLine.xxRqstTpCd.getValue())) {
                schdAgmtCtacPsnTMsg = getSchdAgmtCtacPsnForUpdate(param.glblCmpyCd.getValue(), ctacPsnLine.schdAgmtCtacPsnPk.getValue());
                if (schdAgmtCtacPsnTMsg == null) {
                    msgMap.addXxMsgId(NWZM1866E);
                    msgMap.flush();
                    return true;
                }
                list.add(schdAgmtCtacPsnTMsg);
            }
        }

        if (list.size() > 0) {
            boolean ret = removeLogicalSchdAgmtCtacPsn(list);
            if (ret) {
                msgMap.addXxMsgId(NWZM1866E);
                msgMap.flush();
                return true;
            }
        }

        return false;
    }

    /**
     * Set Schedule Agreement Contact Person Data
     * @param param NWZC171001PMsg
     * @param ctacPsnLine NWZC171001_DPMsg
     * @param tMsg SCHD_AGMT_CTAC_PSNTMsg
     */
    private void setSchdAgmtCtacPsn(NWZC171001PMsg param, NWZC171001_DPMsg ctacPsnLine, SCHD_AGMT_CTAC_PSNTMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, ctacPsnLine.ctacPsnPk);
        if (!ZYPCommonFunc.hasValue(tMsg.schdAgmtCtacPsnPk)) {
            ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_AGMT_CTAC_PSN_SQ));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtNum, param.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTpCd, ctacPsnLine.ctacPsnTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnOvrdFlg, ctacPsnLine.ctacPsnOvrdFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFirstNm, ctacPsnLine.ctacPsnFirstNm);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnMidNm, ctacPsnLine.ctacPsnMidNm);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, ctacPsnLine.ctacPsnLastNm);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnEmlAddr, ctacPsnLine.ctacPsnEmlAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnTelNum, ctacPsnLine.ctacPsnTelNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnExtnNum, ctacPsnLine.ctacPsnExtnNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFaxNum, ctacPsnLine.ctacPsnFaxNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnCmntTxt, ctacPsnLine.ctacPsnCmntTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnCellPhoNum, ctacPsnLine.ctacPsnCellPhoNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacCustRefTpCd, ctacPsnLine.ctacCustRefTpCd); // S21_NA#16452 Add
    }

    /**
     * Regist Schedule Agreement Calc Base Data
     * @param param NWZC171001PMsg
     * @return Regist NG : true
     */
    private boolean registSchdAgmtPrcCalcBase(NWZC171001PMsg param) {

        SCHD_AGMT_PRC_CALC_BASETMsg schdAgmtPrcCalcBaseTMsg = null;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        List<SCHD_AGMT_PRC_CALC_BASETMsg> list = new ArrayList<SCHD_AGMT_PRC_CALC_BASETMsg>();

        // delete
        SCHD_AGMT_PRC_CALC_BASETMsgArray schdAgmtPrcCalcBaseTMsgArray = getSchdAgmtPrcCalcBaseArrayForUpdate(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
        if (schdAgmtPrcCalcBaseTMsgArray != null) {
            for (int i = 0; i < schdAgmtPrcCalcBaseTMsgArray.length(); i++) {
                schdAgmtPrcCalcBaseTMsg = schdAgmtPrcCalcBaseTMsgArray.no(i);
                if (!isRemovePriceTMsg(param, schdAgmtPrcCalcBaseTMsg)) {
                    list.add(schdAgmtPrcCalcBaseTMsg);
                }
            }
            if (list.size() > 0) {
                boolean ret = removeLogicalSchdAgmtPrcCalcBase(list);
                if (ret) {
                    msgMap.addXxMsgId(NWZM1869E);
                    msgMap.flush();
                    return true;
                }
            }
        }

        for (int i = 0; i < param.E.getValidCount(); i++) {
            NWZC171001_EPMsg calcBase = param.E.no(i);
            schdAgmtPrcCalcBaseTMsg = getSchdAgmtCalcBase(param, calcBase, schdAgmtPrcCalcBaseTMsgArray);
            if (schdAgmtPrcCalcBaseTMsg == null) {
                schdAgmtPrcCalcBaseTMsg = new SCHD_AGMT_PRC_CALC_BASETMsg();
                setSchdAgmtCalcBase(param, calcBase, schdAgmtPrcCalcBaseTMsg);
                S21FastTBLAccessor.insert(schdAgmtPrcCalcBaseTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtPrcCalcBaseTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM1867E);
                    msgMap.flush();
                    return true;
                }
            } else {
                setSchdAgmtCalcBase(param, calcBase, schdAgmtPrcCalcBaseTMsg);
                S21FastTBLAccessor.update(schdAgmtPrcCalcBaseTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtPrcCalcBaseTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NWZM1868E);
                    msgMap.flush();
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * isRemovePriceTMsg
     * @param param NWZC171001PMsg
     * @param tMsg SCHD_AGMT_PRC_CALC_BASETMsg
     * @return boolean
     */
    private boolean isRemovePriceTMsg(NWZC171001PMsg param, SCHD_AGMT_PRC_CALC_BASETMsg tMsg) {

        for (int i = 0; i < param.E.getValidCount(); i++) {
            NWZC171001_EPMsg calcBase = param.E.no(i);
            if (isEqualsPriceKey(param, calcBase, tMsg)) {
                return true;
            }
        }

        return false;
    }

    /**
     * getSchdAgmtCalcBase
     * @param param NWZC171001PMsg
     * @param calcBase NWZC171001_EPMsg
     * @param schdAgmtPrcCalcBaseTMsgArray SCHD_AGMT_PRC_CALC_BASETMsgArray
     * @return
     */
     private SCHD_AGMT_PRC_CALC_BASETMsg getSchdAgmtCalcBase(NWZC171001PMsg param, NWZC171001_EPMsg calcBase, SCHD_AGMT_PRC_CALC_BASETMsgArray schdAgmtPrcCalcBaseTMsgArray) {
        if (schdAgmtPrcCalcBaseTMsgArray == null) {
            return null;
        }

        for (int i = 0; i < schdAgmtPrcCalcBaseTMsgArray.getValidCount(); i++) {
            SCHD_AGMT_PRC_CALC_BASETMsg tMsg = schdAgmtPrcCalcBaseTMsgArray.no(i);
            if (isEqualsPriceKey(param, calcBase, tMsg)) {
                return tMsg;
            }
        }

        return null;
    }

    /**
     * Set Schedule Agreement Calc Base Data
     * @param param NWZC171001PMsg
     * @param calcBase NWZC171001_EPMsg
     * @param tMsg SCHD_AGMT_PRC_CALC_BASETMsg
     */
    private void setSchdAgmtCalcBase(NWZC171001PMsg param, NWZC171001_EPMsg calcBase, SCHD_AGMT_PRC_CALC_BASETMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(tMsg.schdAgmtPrcCalcBasePk)) {
            ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtPrcCalcBasePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_AGMT_PRC_CALC_BASE_SQ));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtNum, param.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(tMsg.schdAgmtLineNum, calcBase.schdAgmtLineNum);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondTpCd, calcBase.prcCondTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcDtlGrpCd, calcBase.prcDtlGrpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcJrnlGrpCd, calcBase.prcJrnlGrpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, calcBase.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondUnitCd, calcBase.prcCondUnitCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCalcMethCd, calcBase.prcCalcMethCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsMdsePrcPk, calcBase.dsMdsePrcPk);
        ZYPEZDItemValueSetter.setValue(tMsg.specCondPrcPk, calcBase.specCondPrcPk);
        ZYPEZDItemValueSetter.setValue(tMsg.frtPerWtPk, calcBase.frtPerWtPk);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondManEntryFlg, calcBase.prcCondManEntryFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondManAddFlg, calcBase.prcCondManAddFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCondManDelFlg, calcBase.prcCondManDelFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.autoPrcAmtRate, calcBase.autoPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.maxPrcAmtRate, calcBase.maxPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.minPrcAmtRate, calcBase.minPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.manPrcAmtRate, calcBase.manPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.calcPrcAmtRate, calcBase.calcPrcAmtRate);
        ZYPEZDItemValueSetter.setValue(tMsg.unitPrcAmt, calcBase.unitPrcAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.coaAcctCd, getCoaAcctCdForPrc(param.glblCmpyCd.getValue(), calcBase.prcCondTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.autoPrcCcyCd, calcBase.autoPrcCcyCd);
        // 2018/09/14 QC#9700 add start
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleApplyFlg, calcBase.prcRuleApplyFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRulePrcdPk, calcBase.prcRulePrcdPk);
        // 2018/09/14 QC#9700 add end
    }

    private boolean isEqualsPriceKey(NWZC171001PMsg param, NWZC171001_EPMsg calcBase, SCHD_AGMT_PRC_CALC_BASETMsg tMsg) {

        if (!tMsg.glblCmpyCd.getValue().equals(param.glblCmpyCd.getValue())) {
            return false;
        }

        if (!tMsg.schdAgmtLineNum.getValue().equals(calcBase.schdAgmtLineNum.getValue())) {
            return false;
        }

        if (!tMsg.prcDtlGrpCd.getValue().equals(calcBase.prcDtlGrpCd.getValue())) {
            return false;
        }

        if (!tMsg.specCondPrcPk.getValue().equals(calcBase.specCondPrcPk.getValue())) {
            return false;
        }

        return true;
    }
    
    // Add Start 2016/09/09 S21_NA#13853
    private boolean isLineAllCancel(NWZC171001PMsg param) {
        boolean allCancelFlg = true;

        SCHD_AGMT_LINETMsg lineTMsg = new SCHD_AGMT_LINETMsg();
        lineTMsg.setSQLID("001");
        lineTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        lineTMsg.setConditionValue("schdAgmtNum01", param.schdAgmtNum.getValue());
        SCHD_AGMT_LINETMsgArray lineTMsgArray = (SCHD_AGMT_LINETMsgArray) S21ApiTBLAccessor.findByCondition(lineTMsg);
        
        if (lineTMsgArray.length() == 0) {
            return false;
        }
        for (int i = 0; i < lineTMsgArray.length(); i++) {
            lineTMsg = lineTMsgArray.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(lineTMsg.schdAgmtLineCancFlg.getValue())) {
                allCancelFlg = false;
                break;
            }
        }
        return allCancelFlg;
    }

    private void headerCancel(NWZC171001PMsg param) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        SCHD_AGMTTMsg schdAgmtTMsg = getSchdAgmtForUpdate(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
        
        if (schdAgmtTMsg != null) {
            schdAgmtTMsg.schdAgmtStsCd.setValue(SCHD_AGMT_STS.CANCELLED);
            S21ApiTBLAccessor.update(schdAgmtTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(schdAgmtTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM1862E);
                msgMap.flush();
                return;
            }
        }
        // Add Start 2016/10/04 S21_NA#11612 Rec - Cancel
        SCHD_AGMT_RECTMsg schdAgmtRecTMsg = new SCHD_AGMT_RECTMsg();
        EZDMsg.copy(schdAgmtTMsg, null, schdAgmtRecTMsg, null);

        int bizProcLogPk = createBizProcLog(EVENT_ID_DELETE, BLANK, param.schdAgmtNum.getValue());
        ZYPEZDItemValueSetter.setValue(schdAgmtRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
        S21FastTBLAccessor.insert(schdAgmtRecTMsg);
        // Add End 2016/10/04 S21_NA#11612

    }
    // Add End 2016/09/09 S21_NA#13853

    private void registBizProcLog(NWZC171001PMsg param, Set<String> newLineSet) { // Add 2016/10/05 S21_NA#11612

        String eventId = null;
        int bizProcLogPk = 0;

        List<SCHD_AGMT_LINE_RECTMsg> listList = new ArrayList<SCHD_AGMT_LINE_RECTMsg>();
        List<SCHD_AGMT_CALC_BASE_RECTMsg> calcBaseList = new ArrayList<SCHD_AGMT_CALC_BASE_RECTMsg>();
        List<SCHD_AGMT_PLN_RECTMsg> plnList = new ArrayList<SCHD_AGMT_PLN_RECTMsg>();
        List<SCHD_AGMT_SLS_CR_RECTMsg> slsCrList = new ArrayList<SCHD_AGMT_SLS_CR_RECTMsg>();

        // Del Start 2016/10/04 S21_NA#11612
//        if (S21StringUtil.isEquals(MODE_SUBMIT, param.xxModeCd.getValue())) {
//            eventId = EVENT_ID_SUBMIT;
//        } else {
//            eventId = EVENT_ID_SAVE;
//        }
//
//        bizProcLogPk = createBizProcLog(eventId, BLANK, param.schdAgmtNum.getValue());
//
//        SCHD_AGMTTMsg schdAgmtTMsg = getSchdAgmt(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
//        SCHD_AGMT_RECTMsg schdAgmtRecTMsg = new SCHD_AGMT_RECTMsg();
//        EZDMsg.copy(schdAgmtTMsg, null, schdAgmtRecTMsg, null);
//        ZYPEZDItemValueSetter.setValue(schdAgmtRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
//        S21FastTBLAccessor.insert(schdAgmtRecTMsg);
        // Del End 2016/10/04 S21_NA#11612

        for (int i = 0; i < param.NWZC171002PMsg.getValidCount(); i++) {
            NWZC171002PMsg line = param.NWZC171002PMsg.no(i);
            // Mod Start 2016/10/05 S21_NA#11612
//            if (!ZYPCommonFunc.hasValue(line.schdAgmtNum)) {
//                eventId = EVENT_ID_CREATE;
//            } else if (ZYPConstant.FLG_ON_Y.equals(line.schdAgmtLineCancFlg.getValue())) {
//                eventId = EVENT_ID_DELETE;
//            } else {
//                eventId = EVENT_ID_CHANGE;
//            }
            String lineNum = line.schdAgmtLineNum.getValue().toPlainString();
            if (newLineSet.contains(lineNum) ) {
                eventId = EVENT_ID_CREATE;
            } else if (ZYPConstant.FLG_ON_Y.equals(line.schdAgmtLineCancFlg.getValue())) {
                eventId = EVENT_ID_DELETE;
            } else {
                eventId = EVENT_ID_CHANGE;
            }
            // Mod End 2016/10/05 S21_NA#11612

            SCHD_AGMT_LINE_RECTMsg schdAgmtLineRecTMsg = new SCHD_AGMT_LINE_RECTMsg();
            bizProcLogPk = createBizProcLog(eventId, lineNum, param.schdAgmtNum.getValue());
            SCHD_AGMT_LINETMsg schdAgmtLineTMsg = getSchdAgmtLine(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue(), line.schdAgmtLineNum.getValue());
            EZDMsg.copy(schdAgmtLineTMsg, null, schdAgmtLineRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(schdAgmtLineRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
            listList.add(schdAgmtLineRecTMsg);

            SCHD_AGMT_PRC_CALC_BASETMsgArray calcBaseAry = getSchdAgmtPrcCalcBaseArray(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue(), line.schdAgmtLineNum.getValue());
            for (int j = 0; j < calcBaseAry.length(); j++) {
                SCHD_AGMT_CALC_BASE_RECTMsg schdAgmtCalcBaseRecTMsg = new SCHD_AGMT_CALC_BASE_RECTMsg();
                EZDMsg.copy(calcBaseAry.get(j), null, schdAgmtCalcBaseRecTMsg, null);
                ZYPEZDItemValueSetter.setValue(schdAgmtCalcBaseRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
                // 2018/09/14 QC#9700 add start
                if (ZYPConstant.FLG_OFF_N.equals(schdAgmtCalcBaseRecTMsg.prcRuleApplyFlg.getValue())) {
                    continue;
                }
                // 2018/09/14 QC#9700 add end
                calcBaseList.add(schdAgmtCalcBaseRecTMsg);
            }
        }

        String docId = null;
        for (int i = 0; i < param.NWZC171003PMsg.getValidCount(); i++) {
            NWZC171003PMsg plnLine = param.NWZC171003PMsg.no(i);

            docId = plnLine.schdAgmtLineNum.getValue().toPlainString() + "." + plnLine.schdAgmtSchdLineNum.getValue().toPlainString();

            // Mod Start 2016/10/05 S21_NA#11612
//            if (!ZYPCommonFunc.hasValue(plnLine.schdAgmtNum)) {
//                eventId = EVENT_ID_CREATE;
//            } else if (ZYPConstant.FLG_ON_Y.equals(plnLine.schdAgmtPlnCancFlg.getValue())) {
//                eventId = EVENT_ID_DELETE;
//            } else {
//                eventId = EVENT_ID_CHANGE;
//            }
            if (newLineSet.contains(docId) ) {
                eventId = EVENT_ID_CREATE;
            } else if (ZYPConstant.FLG_ON_Y.equals(plnLine.schdAgmtPlnCancFlg.getValue())) {
                eventId = EVENT_ID_DELETE;
            } else {
                eventId = EVENT_ID_CHANGE;
            }
            // Mod Start 2016/10/05 S21_NA#11612

            SCHD_AGMT_PLN_RECTMsg schdAgmtPlnRecTMsg = new SCHD_AGMT_PLN_RECTMsg();
            bizProcLogPk = createBizProcLog(eventId, docId, param.schdAgmtNum.getValue());
            SCHD_AGMT_PLNTMsg schdAgmtPlnTMsg = getSchdAgmtPln(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue(), plnLine.schdAgmtLineNum.getValue(), plnLine.schdAgmtSchdLineNum.getValue());
            EZDMsg.copy(schdAgmtPlnTMsg, null, schdAgmtPlnRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(schdAgmtPlnRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
            plnList.add(schdAgmtPlnRecTMsg);
        }

        // Del Start 2016/09/28 S21_NA#14723
//        for (int i = 0; i < param.C.getValidCount(); i++) {
//            NWZC171001_CPMsg slsCrMsg = param.C.no(i);
//
//            if (REQUEST_TYPE_NEW.equals(slsCrMsg.xxRqstTpCd.getValue())) {
//                eventId = EVENT_ID_CREATE;
//            } else if (REQUEST_TYPE_MOD.equals(slsCrMsg.xxRqstTpCd.getValue())) {
//                eventId = EVENT_ID_CHANGE;
//            } else if (REQUEST_TYPE_DEL.equals(slsCrMsg.xxRqstTpCd.getValue())) {
//                eventId = EVENT_ID_DELETE;
//            }
//
//            SCHD_AGMT_SLS_CRTMsgArray slsCrTMsgArray = getSchdAgmtSlsCrArray(param.glblCmpyCd.getValue(), param.schdAgmtNum.getValue());
//            for (int j = 0; j < slsCrTMsgArray.length(); j++) {
//                SCHD_AGMT_SLS_CR_RECTMsg schdAgmtSlsCrRecTMsg = new SCHD_AGMT_SLS_CR_RECTMsg();
//                EZDMsg.copy(slsCrTMsgArray.get(j), null, schdAgmtSlsCrRecTMsg, null);
//                bizProcLogPk = createBizProcLog(eventId, BLANK, param.schdAgmtNum.getValue()); // Add 2016/09/28 S21_NA#14723
//                ZYPEZDItemValueSetter.setValue(schdAgmtSlsCrRecTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
//                slsCrList.add(schdAgmtSlsCrRecTMsg);
//            }
//           
//        }
        // Del End 2016/09/28 S21_NA#14723

        if (!listList.isEmpty()) {
            S21FastTBLAccessor.insert(listList.toArray(new SCHD_AGMT_LINE_RECTMsg[0]));
        }

        if (!calcBaseList.isEmpty()) {
            S21FastTBLAccessor.insert(calcBaseList.toArray(new SCHD_AGMT_CALC_BASE_RECTMsg[0]));
        }

        if (!plnList.isEmpty()) {
            S21FastTBLAccessor.insert(plnList.toArray(new SCHD_AGMT_PLN_RECTMsg[0]));
        }

        // Del End 2016/09/28 S21_NA#14723
//        if (!slsCrList.isEmpty()) {
//            S21FastTBLAccessor.insert(slsCrList.toArray(new SCHD_AGMT_SLS_CR_RECTMsg[0]));
//        }
        // Del End 2016/09/28 S21_NA#14723
    }

    private List<NWZC171001_EPMsg> getPriceList(NWZC171001PMsg param, BigDecimal schdAgmtLineNum) {

        List<NWZC171001_EPMsg> priceList = new ArrayList<NWZC171001_EPMsg>();
        for (int i = 0; i < param.E.getValidCount(); i++) {
            NWZC171001_EPMsg calcBase = param.E.no(i);
            if (calcBase.schdAgmtLineNum.getValue().compareTo(schdAgmtLineNum) == 0) {
                priceList.add(calcBase);
            }
        }

        return priceList;
    }

    private BigDecimal getGrsPrice(List<NWZC171001_EPMsg> priceList) {

        BigDecimal basePrice = BigDecimal.ZERO;
        for (NWZC171001_EPMsg calcBase : priceList) {
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                basePrice = basePrice.add(calcBase.unitPrcAmt.getValue());
            }
        }

        return basePrice;
    }

    private BigDecimal getNetPrice(List<NWZC171001_EPMsg> priceList) {

        BigDecimal netPrice = BigDecimal.ZERO;
        for (NWZC171001_EPMsg calcBase : priceList) {
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                netPrice = netPrice.add(calcBase.unitPrcAmt.getValue());
            }
            if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
                netPrice = netPrice.subtract(calcBase.unitPrcAmt.getValue());
            }
        }

        return netPrice;
    }

    private BigDecimal getFrtPrice(List<NWZC171001_EPMsg> priceList) {

        BigDecimal basePrice = BigDecimal.ZERO;
        for (NWZC171001_EPMsg calcBase : priceList) {
            if (PRC_DTL_GRP.FREIGHT.equals(calcBase.prcDtlGrpCd.getValue())) {
                basePrice = basePrice.add(calcBase.unitPrcAmt.getValue());
            }
        }

        return basePrice;
    }

    // 2016/09/02 S21_NA#13036 Add Start
    private BigDecimal getNetAmt(List<NWZC171001_EPMsg> priceList) {

        BigDecimal amt = BigDecimal.ZERO;
        for (NWZC171001_EPMsg calcBase : priceList) {
            // QC#22965 2018/06/05 Add Start
            if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManDelFlg.getValue())) {
                continue;
            }
            // QC#22965 2018/06/05 Add End
            if (PRC_DTL_GRP.BASE_PRICE.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.add(calcBase.calcPrcAmtRate.getValue());
            }
            if (PRC_DTL_GRP.DISCOUNT.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.subtract(calcBase.calcPrcAmtRate.getValue());
            }
            if (PRC_DTL_GRP.ROUNDING_FACTOR_0.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.subtract(calcBase.calcPrcAmtRate.getValue());
            }
        }

        return amt;
    }

    private BigDecimal getFrtAmt(List<NWZC171001_EPMsg> priceList) {

        BigDecimal amt = BigDecimal.ZERO;
        for (NWZC171001_EPMsg calcBase : priceList) {
            // QC#22965 2018/06/05 Add Start
            if (ZYPConstant.FLG_ON_Y.equals(calcBase.prcCondManDelFlg.getValue())) {
                continue;
            }
            // QC#22965 2018/06/05 Add End
            if (PRC_DTL_GRP.FREIGHT.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.add(calcBase.calcPrcAmtRate.getValue());
            }
            // QC#21841 2018/05/21 Add Start
            if (PRC_DTL_GRP.HANDLING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.add(calcBase.calcPrcAmtRate.getValue());
            }
            if (PRC_DTL_GRP.FUEL_SURCHARGE.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.add(calcBase.calcPrcAmtRate.getValue());
            }
            if (PRC_DTL_GRP.SHIPPING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.add(calcBase.calcPrcAmtRate.getValue());
            }
            // QC#21841 2018/05/21 Add End
            if (PRC_DTL_GRP.ROUNDING_FACTOR_1.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.add(calcBase.calcPrcAmtRate.getValue());
            }
            // QC#21841 2018/05/21 Add Start
            if (PRC_DTL_GRP.ROUNDING_FACTOR_2.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.add(calcBase.calcPrcAmtRate.getValue());
            }
            // QC#21841 2018/05/21 Add End
            // QC#27479 2018/08/03 Add Start
            if (PRC_DTL_GRP.RESTOCKING_FEE.equals(calcBase.prcDtlGrpCd.getValue())) {
                amt = amt.add(calcBase.calcPrcAmtRate.getValue());
            }
            // QC#27479 2018/08/03 Add End
        }

        return amt;
    }
    // 2016/09/02 S21_NA#13036 Add End

    private BigDecimal getTaxPrice(List<NWZC171001_EPMsg> priceList) {

        BigDecimal basePrice = BigDecimal.ZERO;
        for (NWZC171001_EPMsg calcBase : priceList) {
            if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue())) {
                basePrice = basePrice.add(calcBase.unitPrcAmt.getValue());
            }
        }

        return basePrice;
    }

    // 2016/09/02 S21_NA#13036 Add Start
    private BigDecimal getCalcPrcAmtRate(List<NWZC171001_EPMsg> priceList) {

        BigDecimal calcPrcAmtRate = BigDecimal.ZERO;
        for (NWZC171001_EPMsg calcBase : priceList) {
            if (PRC_DTL_GRP.TAX.equals(calcBase.prcDtlGrpCd.getValue())) {
                calcPrcAmtRate = calcPrcAmtRate.add(calcBase.calcPrcAmtRate.getValue());
            }
        }

        return calcPrcAmtRate;
    }
    // 2016/09/02 S21_NA#13036 Add End
    
    private NWXC001001UnitPriceData callNWXC001001EditPriceAmount(NWZC171001PMsg param, NWZC171002PMsg line, BigDecimal grsPrice, BigDecimal netPrice) {

        NWXC001001EditPriceAmountInfo editPrcAmtInfo = new NWXC001001EditPriceAmountInfo();
        editPrcAmtInfo.setGlblCmpyCd(param.glblCmpyCd.getValue());
        editPrcAmtInfo.setMdseCd(line.mdseCd.getValue());
        editPrcAmtInfo.setSlsDt(param.slsDt.getValue());
        editPrcAmtInfo.setCcyCd(line.ccyCd.getValue());
        editPrcAmtInfo.setDealGrsPrcAmt(grsPrice);
        editPrcAmtInfo.setDealNetPrcAmt(netPrice);
        editPrcAmtInfo = NWXC001001EditPriceAmount.getCmpsnPriceList(editPrcAmtInfo);

        S21ApiMessageMap lineMap = new S21ApiMessageMap(line);

        for (String errorID : editPrcAmtInfo.getXxMsgIdList()) {
            lineMap.addXxMsgId(errorID);
            lineMap.flush();
        }
        return editPrcAmtInfo.getUnitPrcData();
    }

    private boolean hasError(NWZC171002PMsg line) {

        //if (msgIdMgr.getXxMsgIdListSize() > 0) {
        if (line.xxMsgIdList.getValidCount() > 0) {
            return true;
        }

        return false;
    }

    private int getDealCcyDigit(String glblCmpyCd, String ccyCd) {

        CCYTMsg ccyMsg = new CCYTMsg();
        ccyMsg.glblCmpyCd.setValue(glblCmpyCd);
        ccyMsg.ccyCd.setValue(ccyCd);
        ccyMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyMsg);

        if (ccyMsg != null) {
            return ccyMsg.aftDeclPntDigitNum.getValueInt();
        }

        return 0;
    }

    private PMT_TERM_CASH_DISCTMsg getpmtTermCashDisc(String glblCmpyCd, String pmtTermCashDiscCd) {

        PMT_TERM_CASH_DISCTMsg inTMsg = new PMT_TERM_CASH_DISCTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.pmtTermCashDiscCd.setValue(pmtTermCashDiscCd);
        PMT_TERM_CASH_DISCTMsg outTMsg = (PMT_TERM_CASH_DISCTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SELL_TO_CUSTTMsg getSellToCust(String glblCmpyCd, String sellToCustCd) {

        SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
        inTMsg.setSQLID("017");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("sellToCustCd01", sellToCustCd);

        SELL_TO_CUSTTMsgArray array = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);

        if (array.getValidCount() == 0) {
            return null;
        }

        return array.no(0);
    }

    private static String getCoaAcctCdForPrc(String glblCmpyCd, String prcCondTpCd) {

        if (!ZYPCommonFunc.hasValue(prcCondTpCd)) {
            return null;
        }

        PRC_COND_TPTMsg tMsg = new PRC_COND_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.prcCondTpCd.setValue(prcCondTpCd);
        tMsg = (PRC_COND_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);

        if (tMsg != null) {
            return tMsg.coaAcctCd.getValue();
        }

        return null;
    }

    private SCHD_AGMTTMsg getSchdAgmt(String glblCmpyCd, String schdAgmtNum) {

        SCHD_AGMTTMsg inTMsg = new SCHD_AGMTTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.schdAgmtNum.setValue(schdAgmtNum);
        SCHD_AGMTTMsg outTMsg = (SCHD_AGMTTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_LINETMsg getSchdAgmtLine(String glblCmpyCd, String schdAgmtNum, BigDecimal schdAgmtLineNum) {

        SCHD_AGMT_LINETMsg inTMsg = new SCHD_AGMT_LINETMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.schdAgmtNum.setValue(schdAgmtNum);
        inTMsg.schdAgmtLineNum.setValue(schdAgmtLineNum);
        SCHD_AGMT_LINETMsg outTMsg = (SCHD_AGMT_LINETMsg) S21ApiTBLAccessor.findByKey(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_PLNTMsg getSchdAgmtPln(String glblCmpyCd, String schdAgmtNum, BigDecimal schdAgmtLineNum, BigDecimal schdAgmtSchdLineNum) {

        SCHD_AGMT_PLNTMsg inTMsg = new SCHD_AGMT_PLNTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.schdAgmtNum.setValue(schdAgmtNum);
        inTMsg.schdAgmtLineNum.setValue(schdAgmtLineNum);
        inTMsg.schdAgmtSchdLineNum.setValue(schdAgmtSchdLineNum);
        SCHD_AGMT_PLNTMsg outTMsg = (SCHD_AGMT_PLNTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_PRC_CALC_BASETMsgArray getSchdAgmtPrcCalcBaseArray(String glblCmpyCd, String schdAgmtNum, BigDecimal schdAgmtLineNum) {

        SCHD_AGMT_PRC_CALC_BASETMsg inTMsg = new SCHD_AGMT_PRC_CALC_BASETMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("schdAgmtNum01", schdAgmtNum);
        inTMsg.setConditionValue("schdAgmtLineNum01", schdAgmtLineNum);
        SCHD_AGMT_PRC_CALC_BASETMsgArray array = (SCHD_AGMT_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);

        return array;
    }

    private SCHD_AGMT_SLS_CRTMsgArray getSchdAgmtSlsCrArray(String glblCmpyCd, String schdAgmtNum) {

        SCHD_AGMT_SLS_CRTMsg inTMsg = new SCHD_AGMT_SLS_CRTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("schdAgmtNum01", schdAgmtNum);
        SCHD_AGMT_SLS_CRTMsgArray array = (SCHD_AGMT_SLS_CRTMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);

        return array;
    }

    private SCHD_AGMTTMsg getSchdAgmtForUpdate(String glblCmpyCd, String schdAgmtNum) {

        SCHD_AGMTTMsg inTMsg = new SCHD_AGMTTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.schdAgmtNum.setValue(schdAgmtNum);
        SCHD_AGMTTMsg outTMsg = (SCHD_AGMTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_LINETMsg getSchdAgmtLineForUpdate(String glblCmpyCd, String schdAgmtNum, BigDecimal schdAgmtLineNum) {

        SCHD_AGMT_LINETMsg inTMsg = new SCHD_AGMT_LINETMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.schdAgmtNum.setValue(schdAgmtNum);
        inTMsg.schdAgmtLineNum.setValue(schdAgmtLineNum);
        SCHD_AGMT_LINETMsg outTMsg = (SCHD_AGMT_LINETMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_PLNTMsg getSchdAgmtPlnForUpdate(String glblCmpyCd, String schdAgmtNum, BigDecimal schdAgmtLineNum, BigDecimal schdAgmtSchdLineNum) {

        SCHD_AGMT_PLNTMsg inTMsg = new SCHD_AGMT_PLNTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.schdAgmtNum.setValue(schdAgmtNum);
        inTMsg.schdAgmtLineNum.setValue(schdAgmtLineNum);
        inTMsg.schdAgmtSchdLineNum.setValue(schdAgmtSchdLineNum);
        SCHD_AGMT_PLNTMsg outTMsg = (SCHD_AGMT_PLNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_SLS_CRTMsg getSchdAgmtSlsCrForUpdate(String glblCmpyCd, BigDecimal schdAgmtSlsCrPk) {

        SCHD_AGMT_SLS_CRTMsg inTMsg = new SCHD_AGMT_SLS_CRTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.schdAgmtSlsCrPk.setValue(schdAgmtSlsCrPk);
        SCHD_AGMT_SLS_CRTMsg outTMsg = (SCHD_AGMT_SLS_CRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_CTAC_PSNTMsg getSchdAgmtCtacPsnForUpdate(String glblCmpyCd, BigDecimal schdAgmtCtacPsnPk) {

        SCHD_AGMT_CTAC_PSNTMsg inTMsg = new SCHD_AGMT_CTAC_PSNTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.schdAgmtCtacPsnPk.setValue(schdAgmtCtacPsnPk);
        SCHD_AGMT_CTAC_PSNTMsg outTMsg = (SCHD_AGMT_CTAC_PSNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }

    private SCHD_AGMT_LINETMsgArray getSchdAgmtLineArray(String glblCmpyCd, String schdAgmtNum) {

        SCHD_AGMT_LINETMsg inTMsg = new SCHD_AGMT_LINETMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("schdAgmtNum01", schdAgmtNum);
        SCHD_AGMT_LINETMsgArray array = (SCHD_AGMT_LINETMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inTMsg);

        return array;
    }

    private SCHD_AGMT_PLNTMsgArray getSchdAgmtPlnArray(String glblCmpyCd, String schdAgmtNum) {

        SCHD_AGMT_PLNTMsg inTMsg = new SCHD_AGMT_PLNTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("schdAgmtNum01", schdAgmtNum);
        SCHD_AGMT_PLNTMsgArray array = (SCHD_AGMT_PLNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inTMsg);

        return array;
    }

    private SCHD_AGMT_PRC_CALC_BASETMsgArray getSchdAgmtPrcCalcBaseArrayForUpdate(String glblCmpyCd, String schdAgmtNum) {

        SCHD_AGMT_PRC_CALC_BASETMsg inTMsg = new SCHD_AGMT_PRC_CALC_BASETMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("schdAgmtNum01", schdAgmtNum);
        SCHD_AGMT_PRC_CALC_BASETMsgArray array = (SCHD_AGMT_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inTMsg);

        return array;
    }

    private boolean removePhysicalSchdAgmt(SCHD_AGMTTMsg tmsg) {

        final int resRecordCnt = S21FastTBLAccessor.removePhysical(new EZDTMsg[] {tmsg });

        if (resRecordCnt != 1) {
            return true;
        }

        return false;
    }

    private boolean removePhysicalSchdAgmtLine(SCHD_AGMT_LINETMsgArray array) {

        if (array == null || array.getValidCount() == 0) {
            return true;
        }

        List<SCHD_AGMT_LINETMsg> list = new ArrayList<SCHD_AGMT_LINETMsg>();
        for (int i = 0; i < array.getValidCount(); i++) {
            list.add(array.no(i));
        }

        final EZDTMsg[] tMsgArray = (EZDTMsg[]) list.toArray(new EZDTMsg[list.size()]);
        final int resRecordCnt = S21FastTBLAccessor.removePhysical(tMsgArray);

        if (tMsgArray.length != resRecordCnt) {
            return true;
        }

        return false;
    }

    private boolean removePhysicalSchdAgmtPln(SCHD_AGMT_PLNTMsgArray array) {

        if (array == null || array.getValidCount() == 0) {
            return true;
        }

        List<SCHD_AGMT_PLNTMsg> list = new ArrayList<SCHD_AGMT_PLNTMsg>();
        for (int i = 0; i < array.getValidCount(); i++) {
            list.add(array.no(i));
        }

        final EZDTMsg[] tMsgArray = (EZDTMsg[]) list.toArray(new EZDTMsg[list.size()]);
        final int resRecordCnt = S21FastTBLAccessor.removePhysical(tMsgArray);

        if (tMsgArray.length != resRecordCnt) {
            return true;
        }

        return false;
    }

    private boolean removePhysicalSchdAgmtPrcCalcBase(SCHD_AGMT_PRC_CALC_BASETMsgArray array) {

        if (array == null || array.getValidCount() == 0) {
            return true;
        }

        List<SCHD_AGMT_PRC_CALC_BASETMsg> list = new ArrayList<SCHD_AGMT_PRC_CALC_BASETMsg>();
        for (int i = 0; i < array.getValidCount(); i++) {
            list.add(array.no(i));
        }

        final EZDTMsg[] tMsgArray = (EZDTMsg[]) list.toArray(new EZDTMsg[list.size()]);
        final int resRecordCnt = S21FastTBLAccessor.removePhysical(tMsgArray);

        if (tMsgArray.length != resRecordCnt) {
            return true;
        }

        return false;
    }

    private boolean removeLogicalSchdAgmtSlsCr(List<SCHD_AGMT_SLS_CRTMsg> list) {

        if (list == null) {
            return true;
        }

        final EZDTMsg[] tMsgArray = (EZDTMsg[]) list.toArray(new EZDTMsg[list.size()]);
        final int resRecordCnt = S21FastTBLAccessor.removeLogical(tMsgArray);

        if (tMsgArray.length != resRecordCnt) {
            return true;
        }

        return false;
    }

    private boolean removeLogicalSchdAgmtCtacPsn(List<SCHD_AGMT_CTAC_PSNTMsg> list) {

        if (list == null) {
            return true;
        }

        final EZDTMsg[] tMsgArray = (EZDTMsg[]) list.toArray(new EZDTMsg[list.size()]);
        final int resRecordCnt = S21FastTBLAccessor.removeLogical(tMsgArray);

        if (tMsgArray.length != resRecordCnt) {
            return true;
        }

        return false;
    }

    private boolean removeLogicalSchdAgmtPrcCalcBase(List<SCHD_AGMT_PRC_CALC_BASETMsg> list) {

        if (list == null) {
            return true;
        }

        final EZDTMsg[] tMsgArray = (EZDTMsg[]) list.toArray(new EZDTMsg[list.size()]);
        final int resRecordCnt = S21FastTBLAccessor.removeLogical(tMsgArray);

        if (tMsgArray.length != resRecordCnt) {
            return true;
        }

        return false;
    }

    /**
     * Create Bisiness Prcess Log
     * @param eventId Event ID
     * @param dsOrdPosnNum DS Order Position Number
     * @param cpoOrdNum CPO Order Number
     * @return Business Process Log PK
     */
    private int createBizProcLog(String eventId, String docId, String prntDocId) {

        // Get Business Process Log PK
        int bizProcLogPk = EZDSeqTable.getNumberInt(ZYPOracleSeqConstant.BIZ_PROC_LOG_SQ);

        BIZ_PROC_LOGTMsg bizProcLogTMsg = new BIZ_PROC_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.bizProcLogPk, BigDecimal.valueOf(bizProcLogPk));
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.trxId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.subSysId, SUB_SYS.NWZ);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.procId, PROCESS_ID);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.eventId, eventId);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docTpCd, DOCUMENT_TYPE);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docId, docId);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.prntDocId, prntDocId);
        S21ApiTBLAccessor.insert(bizProcLogTMsg);

        return bizProcLogPk;
    }

    // Add Start 2016/10/06 S21_NA#11883
    private boolean checkContrCatgRelation(NWZC171001PMsg param) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", param.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", param.dsOrdCatgCd.getValue());
        params.put("dsOrdTpCd", param.dsOrdTpCd.getValue());
        
        if ((Integer) ssmClient.queryObject("countDsOrdCatgRelnTp", params) > 0) {
            if (!ZYPCommonFunc.hasValue(param.dsContrNum)) {
                return false;
            }
        }
        return true;
    }
    // Add Start 2016/10/06 S21_NA#11883
}
