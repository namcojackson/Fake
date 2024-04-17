/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC100001;

import static com.canon.cusa.s21.api.NSZ.NSZC100001.constant.NSZC100001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.sql.SQLException;

import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.INTL_LANG_VALTMsg;
import business.db.SVC_PRC_RNW_LTR_WRKTMsg;
import business.db.SVC_PRC_RNW_LTR_WRKTMsgArray;
import business.db.SVC_TERM_COND_LTR_WRKTMsg;
import business.db.SVC_TERM_COND_LTR_WRKTMsgArray;
import business.parts.NSZC100001PMsg;
import business.parts.NSZC100001_dsContrDtlPkListPMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Contract Agreement Letter Creation API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/27/2016   Hitachi         T.Tsuchida      Create          #N/A
 * 08/08/2017   Hitachi         K.Kim           Update          QC#20351
 * 08/22/2017   CITS            M.Naito         Update          QC#8661
 * 06/06/2018   CITS            T.Wada          Update          QC#25935
 * 02/03/2021   CITS            R.Mercado       Update          QC#58314
 * 02/10/2021   CITS            Y.Penequito     Update          QC#58312
 * 07/23/2021   CITS            L.Mandanas      Update          QC#58314-1
 * 01/14/2022   CITS            L.Mandanas      Update          QC#58314-4
 * </pre>
 */
public class NSZC100001 extends S21ApiCommonBase {

    /**
     * S21ApiMessageMap
     */
    private S21ApiMessageMap msgMap = null;

    /**
     * ONBATCH_TYPE
     */
    @SuppressWarnings("unused")
    private ONBATCH_TYPE onBatType = null;

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Global Company Code
     */
    private String glblCmpyCd = null;

    /**
     * Constructor
     */
    public NSZC100001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Contract Agreement Letter Creation API
     * @param pMsg {@link NSZC100001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(NSZC100001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        this.msgMap = new S21ApiMessageMap(pMsg);
        this.onBatType = onBatTp;
        init(pMsg, onBatTp);
        this.msgMap.flush();

    }

    private void init(NSZC100001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        // 1. input check
        checkParameter();
        if (hasErrMsg()) {
            return;
        }

        this.glblCmpyCd = pMsg.glblCmpyCd.getValue();

        doProcess();

    }

    private void checkParameter() {

        NSZC100001PMsg pMsg = (NSZC100001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }

        if (!hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NSZM0002E);
        }

        if (!hasValue(pMsg.bizAppId)) {
            msgMap.addXxMsgId(NSZM0854E);
        }

        if (!hasValue(pMsg.otptOpCd)) {
            msgMap.addXxMsgId(NSZM0855E);
        }

        if (!hasValue(pMsg.dsContrPk)) {
            msgMap.addXxMsgId(NSZM0856E);
        }

        // START 2017/08/22 M.Naito [QC#8661, ADD]
        if (!hasValue(pMsg.rptId)) {
            msgMap.addXxMsgId(NSZM1294E);
        }
        // START 2017/08/22 M.Naito [QC#8661, ADD]

        for (int i = 0; i < pMsg.dsContrDtlPkList.getValidCount(); i++) {
            NSZC100001_dsContrDtlPkListPMsg dtlPMsg = pMsg.dsContrDtlPkList.no(i);
            if (!hasValue(dtlPMsg.dsContrDtlPk)) {
                msgMap.addXxMsgId(NSZM0857E);
            }
        }
    }

    private boolean hasErrMsg() {
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return true;
        }
        return false;
    }

    private void doProcess() {

        NSZC100001PMsg pMsg = (NSZC100001PMsg) msgMap.getPmsg();

        DS_CONTRTMsg dsContrTMsg = getDsContr(pMsg.dsContrPk.getValue());
        if (dsContrTMsg == null) {
            msgMap.addXxMsgId(NSZM0434E);
            return;
        }

        // START 2017/08/22 M.Naito [QC#8661, MOD]
        // START 2017/08/08 K.Kim [QC#20351, ADD]
        // delete Work Table
        try{
//            if (!deletePrcRnwLtrWrk(this.glblCmpyCd, pMsg.dsContrPk.getValue())) {
            if (!deletePrcRnwLtrWrk(this.glblCmpyCd, pMsg.dsContrPk.getValue(), pMsg.bizAppId.getValue())) {
                msgMap.addXxMsgId(ZZZM9004E);
                return;
            }
            if (!deleteTermCondLtrWrk(this.glblCmpyCd, pMsg.dsContrPk.getValue())) {
                msgMap.addXxMsgId(ZZZM9004E);
                return;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        //END 2017/08/08 K.Kim [QC#20351, ADD]
        // END 2017/08/22 M.Naito [QC#8661, MOD]

        // START 2017/08/22 M.Naito [QC#8661, ADD]
        // get Contract Header Information
        Map<String, Object> dsContrHdr = getDsContrHdrInfo(this.glblCmpyCd, pMsg.dsContrPk.getValue());
        if (dsContrHdr.size() == 0) {
          msgMap.addXxMsgId(NSZM0434E);
          return;
        }
        // END 2017/08/22 M.Naito [QC#8661, ADD]

        List<Map<String, Object>> svcPrcRnwLtrWrkList = new ArrayList<Map<String, Object>>();
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>(pMsg.dsContrDtlPkList.getValidCount());
        for (int i = 0; i < pMsg.dsContrDtlPkList.getValidCount(); i++) {
            dsContrDtlPkList.add(pMsg.dsContrDtlPkList.no(i).dsContrDtlPk.getValue());
        }

        if (!hasValue(dsContrTMsg.dsContrCatgCd)) {
            return;
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
//            svcPrcRnwLtrWrkList = getFleetContrInfo(pMsg.dsContrPk.getValue(), dsContrPkList, pMsg.slsDt.getValue());
            svcPrcRnwLtrWrkList = getFleetContrInfo(pMsg.dsContrPk.getValue(), dsContrDtlPkList, pMsg.slsDt.getValue(), pMsg.bizAppId.getValue());
        } else {
//            svcPrcRnwLtrWrkList = getNonFleetContrInfo(pMsg.dsContrPk.getValue(), dsContrPkList, pMsg.slsDt.getValue());
            // START 2021/06/28 L.Mandanas [QC#58314-1, MOD]
              //svcPrcRnwLtrWrkList = getNonFleetContrInfo(pMsg.dsContrPk.getValue(), dsContrDtlPkList, pMsg.slsDt.getValue(), pMsg.bizAppId.getValue());
            svcPrcRnwLtrWrkList = getNonFleetContrInfo(
                   pMsg.dsContrPk.getValue(), dsContrDtlPkList,
                   pMsg.slsDt.getValue(), pMsg.bizAppId.getValue(),
                   dsContrTMsg.dsContrCatgCd.getValue());
            // END 2021/06/28 L.Mandanas [QC#58314-1, MOD]
        }

//        insertSvcPrcRnwLtrWrk(svcPrcRnwLtrWrkList);
        // START 2021/06/28 L.Mandanas [QC#58314-1, MOD]
          //insertSvcPrcRnwLtrWrk(svcPrcRnwLtrWrkList, dsContrHdr);
        insertSvcPrcRnwLtrWrk(svcPrcRnwLtrWrkList, dsContrHdr,
               dsContrTMsg.dsContrCatgCd.getValue());
        // END 2021/06/28 L.Mandanas [QC#58314-1, MOD]
        if (hasErrMsg()) {
            return;
        }

        List<Map<String, Object>> svcTermCondLtrList = getSvcTermCondLtrInfo(pMsg.dsContrPk.getValue(), pMsg.slsDt.getValue());
        insertSvcTermCondLtrWrk(svcTermCondLtrList);
        if (hasErrMsg()) {
            return;
        }
    }

//    private String getData(String inData, String outData) {
//        if (hasValue(inData)) {
//            return inData;
//        }
//        return outData;
//    }

    // START 2017/08/22 M.Naito [QC#8661, MOD]
    // START 2021/06/28 L.Mandanas [QC#58314-1, MOD]
    private void insertSvcPrcRnwLtrWrk(
          List<Map<String, Object>> svcPrcRnwLtrWrkList,
          Map<String, Object> dsContrHdr, String dsContrCatgCd) {
      //private void insertSvcPrcRnwLtrWrk(List<Map<String, Object>> svcPrcRnwLtrWrkList, Map<String, Object> dsContrHdr) {
    // END 2021/06/28 L.Mandanas [QC#58314-1, MOD]
//    private void insertSvcPrcRnwLtrWrk(List<Map<String, Object>> svcPrcRnwLtrWrkList) {
        NSZC100001PMsg pMsg = (NSZC100001PMsg) msgMap.getPmsg();

        BigDecimal totDealNetAmt = BigDecimal.ZERO;
        String totDealNetAmtTxt = "";
        String shipToCustCd = "";
        String shipToLocNm = "";
        String shipToFirstLineAddr = "";
        String shipToScdLineAddr = "";
        String shipToThirdLineAddr = "";
        String shipToFrthLineAddr = "";
        String shipToCtyAddr = "";
        String shipToStCd = "";
        String shipToPostCd = "";
        String shipToCtryNm = "";
        String preBllgMtrLbCd = "";
        // START 2021/02/08 Y.Penequito [QC#56717, ADD]
        BigDecimal dsCntrDtlPkMainUnit = null;
        BigDecimal dsCntrPkValueMainUnit = null;
        String mainContrPrcEffFromDt = null;
        BigDecimal totalBasePrcDealAmt = BigDecimal.ZERO;
        BigDecimal totalOldBasePrcDealAmt = BigDecimal.ZERO;
        int mainSeqNumber = 0;
        // END 2021/02/08 Y.Penequito [QC#56717, ADD]

        for (int i = 0; i < svcPrcRnwLtrWrkList.size(); i++) {
            Map<String, Object> svcPrcRnwLtrWrkInfo = svcPrcRnwLtrWrkList.get(i);
            totDealNetAmtTxt = (String) svcPrcRnwLtrWrkInfo.get(BASE_PRC_DEAL_AMT_TXT);
            if (hasValue(totDealNetAmtTxt)) {
                totDealNetAmt = totDealNetAmt.add(new BigDecimal(totDealNetAmtTxt));
            }
//            shipToCustCd = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_CUST_CD) , shipToCustCd);
//            shipToLocNm = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_LOC_NM) , shipToLocNm);
//            shipToFirstLineAddr = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_FIRST_LINE_ADDR) , shipToFirstLineAddr);
//            shipToScdLineAddr = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_SCD_LINE_ADDR) , shipToScdLineAddr);
//            shipToThirdLineAddr = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_THIRD_LINE_ADDR) , shipToThirdLineAddr);
//            shipToFrthLineAddr = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_FRTH_LINE_ADDR) , shipToFrthLineAddr);
//            shipToCtyAddr = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_CTY_ADDR) , shipToCtyAddr);
//            shipToStCd = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_ST_CD) , shipToStCd);
//            shipToPostCd = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_POST_CD) , shipToPostCd);
//            shipToCtryNm = getData((String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_CTRY_NM) , shipToCtryNm);
        }
        totDealNetAmtTxt = getNumberFormat(totDealNetAmt.toString());
        totDealNetAmtTxt = US_DOLLAR.concat(totDealNetAmtTxt);

        BigDecimal preDsContrDtlPk = BigDecimal.ZERO;
        int dsContrDtlSortNum = 0;
        // START 2021/06/28 L.Mandanas [QC#58314-1, ADD]
        Map < String, Object > aggLine = new HashMap < String, Object >();
        // START 2021/07/23 L.Mandanas [QC#58314-1, ADD]
        List < Map < String, Object > > aggLineList =
             new ArrayList < Map < String, Object > >();
        // END 2021/07/23 L.Mandanas [QC#58314-1, ADD]
        // END 2021/06/28 L.Mandanas [QC#58314-1, ADD]
        for (int i = 0; i < svcPrcRnwLtrWrkList.size(); i++) {
            Map<String, Object> svcPrcRnwLtrWrkInfo = svcPrcRnwLtrWrkList.get(i);
            SVC_PRC_RNW_LTR_WRKTMsg svcPrcRnwLtrWrkTMsg = new SVC_PRC_RNW_LTR_WRKTMsg();
            setValue(svcPrcRnwLtrWrkTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(svcPrcRnwLtrWrkTMsg.svcPrcRnwLtrWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_PRC_RNW_LTR_WRK_SQ));
            setValue(svcPrcRnwLtrWrkTMsg.dsContrPk, (BigDecimal) svcPrcRnwLtrWrkInfo.get(DS_CONTR_PK));
            setValue(svcPrcRnwLtrWrkTMsg.dsContrDtlPk, (BigDecimal) svcPrcRnwLtrWrkInfo.get(DS_CONTR_DTL_PK));
//            if (preDsContrDtlPk.compareTo((BigDecimal) svcPrcRnwLtrWrkInfo.get(DS_CONTR_DTL_PK)) != 0) {
//                preDsContrDtlPk = (BigDecimal) svcPrcRnwLtrWrkInfo.get(DS_CONTR_DTL_PK);
//                dsContrDtlSortNum = 1;
//            }
            setValue(svcPrcRnwLtrWrkTMsg.dsContrDtlSortNum, BigDecimal.valueOf(dsContrDtlSortNum++));
            setValue(svcPrcRnwLtrWrkTMsg.slsDt, pMsg.slsDt);
            setValue(svcPrcRnwLtrWrkTMsg.bizAppId, pMsg.bizAppId);
            setValue(svcPrcRnwLtrWrkTMsg.otptOpCd, pMsg.otptOpCd);
//            setValue(svcPrcRnwLtrWrkTMsg.tocNm, (String) svcPrcRnwLtrWrkInfo.get(TOC_NM));
            setValue(svcPrcRnwLtrWrkTMsg.tocNm, (String) dsContrHdr.get(TOC_NM));
            setValue(svcPrcRnwLtrWrkTMsg.billToCustCd, (String) dsContrHdr.get(BILL_TO_CUST_CD));
            setValue(svcPrcRnwLtrWrkTMsg.billToLocNm, (String) dsContrHdr.get(BILL_TO_LOC_NM));
            setValue(svcPrcRnwLtrWrkTMsg.billToFirstLineAddr, (String) dsContrHdr.get(BILL_TO_FIRST_LINE_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.billToScdLineAddr, (String) dsContrHdr.get(BILL_TO_SCD_LINE_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.billToThirdLineAddr, (String) dsContrHdr.get(BILL_TO_THIRD_LINE_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.billToFrthLineAddr, (String) dsContrHdr.get(BILL_TO_FRTH_LINE_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.billToCtyAddr, (String) dsContrHdr.get(BILL_TO_CTY_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.billToStCd, (String) dsContrHdr.get(BILL_TO_ST_CD));
            setValue(svcPrcRnwLtrWrkTMsg.billToPostCd, (String) dsContrHdr.get(BILL_TO_POST_CD));
            setValue(svcPrcRnwLtrWrkTMsg.billToCtryNm, (String) dsContrHdr.get(BILL_TO_CTRY_NM));
            setValue(svcPrcRnwLtrWrkTMsg.shipToCustCd, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_CUST_CD));
            setValue(svcPrcRnwLtrWrkTMsg.shipToLocNm, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_LOC_NM));
            setValue(svcPrcRnwLtrWrkTMsg.shipToFirstLineAddr, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_FIRST_LINE_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.shipToScdLineAddr, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_SCD_LINE_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.shipToThirdLineAddr, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_THIRD_LINE_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.shipToFrthLineAddr, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_FRTH_LINE_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.shipToCtyAddr, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_CTY_ADDR));
            setValue(svcPrcRnwLtrWrkTMsg.shipToStCd, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_ST_CD));
            setValue(svcPrcRnwLtrWrkTMsg.shipToPostCd, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_POST_CD));
            setValue(svcPrcRnwLtrWrkTMsg.shipToCtryNm, (String) svcPrcRnwLtrWrkInfo.get(SHIP_TO_CTRY_NM));
//            setValue(svcPrcRnwLtrWrkTMsg.dsContrNum, (String) svcPrcRnwLtrWrkInfo.get(DS_CONTR_NUM));
            setValue(svcPrcRnwLtrWrkTMsg.dsContrNum, (String) dsContrHdr.get(DS_CONTR_NUM));
            setValue(svcPrcRnwLtrWrkTMsg.baseBllgCycleDescTxt, (String) svcPrcRnwLtrWrkInfo.get(BASE_BLLG_CYCLE_DESC_TXT));
//            setValue(svcPrcRnwLtrWrkTMsg.usgBllgCycleDescTxt, (String) svcPrcRnwLtrWrkInfo.get(USG_BLLG_CYCLE_DESC_TXT));
            setValue(svcPrcRnwLtrWrkTMsg.contrEffFromDtTxt, (String) svcPrcRnwLtrWrkInfo.get(CONTR_EFF_FROM_DT_TXT));
            setValue(svcPrcRnwLtrWrkTMsg.contrEffThruDtTxt, (String) svcPrcRnwLtrWrkInfo.get(CONTR_EFF_THRU_DT_TXT));
            // TODO Temporary
            String mdlNm = (String) svcPrcRnwLtrWrkInfo.get(MDL_NM);
            if (hasValue(mdlNm) && mdlNm.length() > 50) {
                mdlNm = mdlNm.substring(0, 50);
            }
            setValue(svcPrcRnwLtrWrkTMsg.mdlNm, mdlNm);
            // setValue(svcPrcRnwLtrWrkTMsg.mdlNm, (String) svcPrcRnwLtrWrkInfo.get(MDL_NM));
            // TODO Temporary
            setValue(svcPrcRnwLtrWrkTMsg.serNum, (String) svcPrcRnwLtrWrkInfo.get(SER_NUM));
//            if (LVL_USAGE.equals((String) svcPrcRnwLtrWrkInfo.get(LINE_LV))
//                    && hasValue((BigDecimal) svcPrcRnwLtrWrkInfo.get(SVC_PHYS_MTR_PK))
//                    && hasValue((String) svcPrcRnwLtrWrkInfo.get(CONTR_EFF_FROM_DT_TXT))) {
//                BigDecimal readMtrCnt = NSXC003001SvcPhysMtrRead.estMtrCnt(this.glblCmpyCd, (BigDecimal) svcPrcRnwLtrWrkInfo.get(SVC_PHYS_MTR_PK), (String) svcPrcRnwLtrWrkInfo.get(CONTR_EFF_FROM_DT_TXT));
//                setValue(svcPrcRnwLtrWrkTMsg.startReadMtrCntQtyTxt, getNumberFormat(readMtrCnt));
//            }

            // START 2021/06/28 L.Mandanas [QC#58314-1, ADD]
            String lineLv = (String) svcPrcRnwLtrWrkInfo.get("LINE_LV");
            if (dsContrCatgCd.equals(DS_CONTR_CATG.AGGREGATE)
                   && LINE_LV_FOUR.equals(lineLv)) {
                aggLine.put(CUM_COPY_CNT,
                       (String) svcPrcRnwLtrWrkInfo.get(CUM_COPY_CNT));
                aggLine.put(CUM_BLLG_CYCLE_NM,
                       (String) svcPrcRnwLtrWrkInfo.get(CUM_BLLG_CYCLE_NM));
                aggLine.put(CUM_COPY_END_DT,
                       (String) svcPrcRnwLtrWrkInfo.get(CUM_COPY_END_DT));
                // START 2021/07/23 L.Mandanas [QC#58314-1, ADD]
                aggLine.put(BLLG_MTR_LB_CD,
                        (String) svcPrcRnwLtrWrkInfo.get(BLLG_MTR_LB_CD));
                aggLineList.add(new HashMap<String, Object>(aggLine));
                aggLine.clear();
                // END 2021/07/23 L.Mandanas [QC#58314-1, ADD]
                continue;
            }

            String cumCopyEndDtTxt = (String) svcPrcRnwLtrWrkInfo.get(
                    CUM_COPY_END_DT);
            String cntrctEndDtTxt = (String) svcPrcRnwLtrWrkInfo.get(
                    CONTR_EFF_FROM_DT_TXT);
            String uplftPcyDtTxt = (String) svcPrcRnwLtrWrkInfo.get(
                    UPLFT_PCY_DT);
            String allwnceEndDtTxt = null;
            String cumBllgCycleNm = (String) svcPrcRnwLtrWrkInfo.get(
                    CUM_BLLG_CYCLE_NM);
            String usgBllgCycleDescTxt = (String) svcPrcRnwLtrWrkInfo.get(
                    USG_BLLG_CYCLE_DESC_TXT);
            // END 2021/06/28 L.Mandanas [QC#58314-1, ADD]
            // QC#58314 Add Start
            String xsMtrCovCopyQtyTxt = (String) svcPrcRnwLtrWrkInfo.get(
                    XS_MTR_COV_COPY_QTY_TXT);
            String cumCopyCnt = (String) svcPrcRnwLtrWrkInfo.get(CUM_COPY_CNT);
            // START 2021/06/28 L.Mandanas [QC#58314-1, MOD]
            //int xsMtrCovCopyQtyNum = 0;

            //if (hasValue(xsMtrCovCopyQtyTxt)) {
            //    xsMtrCovCopyQtyNum = new BigDecimal(xsMtrCovCopyQtyTxt).intValue();
            //}
            BigDecimal xsMtrCovCopyQtyNum = BigDecimal.ZERO;
            
            if (dsContrCatgCd.equals(DS_CONTR_CATG.AGGREGATE)
                  && LINE_LV_TWO.equals(lineLv)) {
                // START 2021/07/23 L.Mandanas [QC#58314-1, ADD]
                for(Map < String, Object > aggLineTmp: aggLineList) {
                    if (aggLineTmp.get(BLLG_MTR_LB_CD).equals(
                            svcPrcRnwLtrWrkInfo.get(BLLG_MTR_LB_CD))) {
                        aggLine = new HashMap<String, Object>(aggLineTmp);
                        break;
                    }
                }
                // END 2021/07/23 L.Mandanas [QC#58314-1, ADD]
                cumCopyEndDtTxt = (String) aggLine.get(CUM_COPY_END_DT);
                cumBllgCycleNm = (String) aggLine.get(CUM_BLLG_CYCLE_NM);
                cumCopyCnt = (String) aggLine.get(CUM_COPY_CNT);
            }

            if (hasValue(xsMtrCovCopyQtyTxt)) {
                xsMtrCovCopyQtyNum = new BigDecimal(xsMtrCovCopyQtyTxt);
            }
            
           // START 2021/06/28 L.Mandanas [QC#58314-1, DEL]
           /* if (hasValue(cntrctEndDtTxt)) {
                // subtract one day from cntrctEndDtTxt
                cntrctEndDtTxt = ZYPDateUtil.addDays(cntrctEndDtTxt, -1);
            } */
           // END 2021/06/28 L.Mandanas [QC#58314-1, DEL]

            if (BIZ_APP_ID_NSAB0030.equals(pMsg.bizAppId.getValue())) {
                //renewal letter
                if (hasValue(cntrctEndDtTxt)) {
                    // subtract one day from cntrctEndDtTxt
                    allwnceEndDtTxt = ZYPDateUtil.addDays(cntrctEndDtTxt, -1);
                }
            } else if (BIZ_APP_ID_NSAB0360.equals(pMsg.bizAppId.getValue())) {
                //uplift letter
                if (hasValue(uplftPcyDtTxt)) {
                    // subtract one day from uplftPcyDt
                    allwnceEndDtTxt = ZYPDateUtil.addDays(uplftPcyDtTxt, -1);
                } else {
                    continue;
                }
            } else {
                //default
                allwnceEndDtTxt = cntrctEndDtTxt;
            }
/*            if (xsMtrCovCopyQtyNum > 0 && hasValue(cumCopyCnt)) {
                msgMap.addXxMsgId(NSZM1378E);
                return;
            } */

            if (xsMtrCovCopyQtyNum.compareTo(BigDecimal.ZERO) > 0
                   && hasValue(cumCopyEndDtTxt) && hasValue(allwnceEndDtTxt)) {
                if ((ZYPDateUtil.compare(cumCopyEndDtTxt, allwnceEndDtTxt) > 0)
                    || (BIZ_APP_ID_NSAB0030.equals(pMsg.bizAppId.getValue())
                        && ZYPDateUtil.compare(cumCopyEndDtTxt,
                                allwnceEndDtTxt) == 0)) {
                    msgMap.addXxMsgId(NSZM1378E);
                    return;
                }
            }

            String oldCopyQtyTxt = null;
            String oldBllgCycleTxt = null;
            // END 2021/06/28 L.Mandanas [QC#58314-1, MOD]
            String copyQtyTxt = null;
            String bllgCycleTxt = null;
            // START 2021/06/28 L.Mandanas [QC#58314-1, MOD]
            /*if (xsMtrCovCopyQtyNum == 0 && hasValue(cumCopyCnt)) {
                copyQtyTxt = cumCopyCnt;
                bllgCycleTxt = (String) svcPrcRnwLtrWrkInfo.get(CUM_BLLG_CYCLE_NM);
            } else {
                copyQtyTxt = xsMtrCovCopyQtyTxt;
                bllgCycleTxt = (String) svcPrcRnwLtrWrkInfo.get(USG_BLLG_CYCLE_DESC_TXT);
            }*/

            // START 2022/01/14 L.Mandanas [QC#58314-4, MOD]
            //if (hasValue(cumCopyCnt) && hasValue(cumCopyEndDtTxt)) {
            if (hasValue(cumCopyCnt) && hasValue(cumCopyEndDtTxt)
                    && !dsContrCatgCd.equals(DS_CONTR_CATG.AGGREGATE)) {
            // END 2022/01/14 L.Mandanas [QC#58314-4, MOD]
                // Cum End Date < Contract/Uplift End Date
                if (ZYPDateUtil.compare(cumCopyEndDtTxt,
                        allwnceEndDtTxt) == -1) {
                    oldCopyQtyTxt = xsMtrCovCopyQtyTxt;
                    oldBllgCycleTxt = usgBllgCycleDescTxt;
                    copyQtyTxt = xsMtrCovCopyQtyTxt;
                    bllgCycleTxt = usgBllgCycleDescTxt;
                } else if (ZYPDateUtil.compare(cumCopyEndDtTxt,
                        allwnceEndDtTxt) == 0) {
                    // Cum End Date == Contract/Uplift End Date
                    oldCopyQtyTxt = cumCopyCnt;
                    oldBllgCycleTxt = cumBllgCycleNm;
                    copyQtyTxt = xsMtrCovCopyQtyTxt;
                    bllgCycleTxt = usgBllgCycleDescTxt;
                } else {
                    // Cum End Date > Contract/Uplift End Date
                    // will only run if xsMtrCovCopyQtyNum is 0
                    oldCopyQtyTxt = cumCopyCnt;
                    oldBllgCycleTxt = cumBllgCycleNm;
                    copyQtyTxt = cumCopyCnt;
                    bllgCycleTxt = cumBllgCycleNm;
                }
                /*if (xsMtrCovCopyQtyNum == 0 && hasValue(cumCopyCnt)) {
                    copyQtyTxt = cumCopyCnt;
                    bllgCycleTxt = (String) svcPrcRnwLtrWrkInfo.get(CUM_BLLG_CYCLE_NM);
                }*/
            } else {
                oldCopyQtyTxt = xsMtrCovCopyQtyTxt;
                oldBllgCycleTxt = usgBllgCycleDescTxt;
                copyQtyTxt = xsMtrCovCopyQtyTxt;
                bllgCycleTxt = usgBllgCycleDescTxt;
            }
            // END 2021/06/28 L.Mandanas [QC#58314-1, MOD]
            // START 2021/06/28 L.Mandanas [QC#58314-1, ADD]
            if (hasValue(bllgCycleTxt) && bllgCycleTxt.equals(SEMIANNUAL)) {
                bllgCycleTxt = SEMIANNUALLY;
            }
            if (hasValue(oldBllgCycleTxt)
                    && oldBllgCycleTxt.equals(SEMIANNUAL)) {
                oldBllgCycleTxt = SEMIANNUALLY;
            }

            setValue(svcPrcRnwLtrWrkTMsg.oldUsgBllgCycleDescTxt,
                    oldBllgCycleTxt);
            setValue(svcPrcRnwLtrWrkTMsg.mtrCovCopyQtyDplyTxt,
                    getNumberFormat(oldCopyQtyTxt));
            // END 2021/06/28 L.Mandanas [QC#58314-1, ADD]
            setValue(svcPrcRnwLtrWrkTMsg.usgBllgCycleDescTxt, bllgCycleTxt);
            // QC#58314 Add End
            // QC#58314 Mod Start
            setValue(svcPrcRnwLtrWrkTMsg.xsMtrCovCopyQtyTxt, getNumberFormat(copyQtyTxt));
            // QC#58314 Mod End
            setValue(svcPrcRnwLtrWrkTMsg.xsMtrCopyFromQtyTxt, getNumberFormat((String) svcPrcRnwLtrWrkInfo.get(XS_MTR_COPY_FROM_QTY_TXT)));
            setValue(svcPrcRnwLtrWrkTMsg.xsMtrCopyThruQtyTxt, getNumberFormat((String) svcPrcRnwLtrWrkInfo.get(XS_MTR_COPY_THRU_QTY_TXT)));
            setValue(svcPrcRnwLtrWrkTMsg.xsMtrAmtTxt, setScaleForAmtTxt((String) svcPrcRnwLtrWrkInfo.get(XS_MTR_AMT_TXT), AMOUNT_SCALE_6));
            setValue(svcPrcRnwLtrWrkTMsg.basePrcDealAmtTxt, setScaleForAmtTxt((String) svcPrcRnwLtrWrkInfo.get(BASE_PRC_DEAL_AMT_TXT), AMOUNT_SCALE_2));
            setValue(svcPrcRnwLtrWrkTMsg.totDealNetAmtTxt, totDealNetAmtTxt);

            setValue(svcPrcRnwLtrWrkTMsg.rptId, pMsg.rptId);
            String slsDtDescTxt = convertDateFormat(pMsg.slsDt.getValue());
            if (!hasValue(slsDtDescTxt)) {
                msgMap.addXxMsgId(NSZM0046E);
                return;
            }
            setValue(svcPrcRnwLtrWrkTMsg.slsDtDescTxt, slsDtDescTxt);
            setValue(svcPrcRnwLtrWrkTMsg.oldXsMtrAmtTxt, setScaleForAmtTxt((String) svcPrcRnwLtrWrkInfo.get(OLD_XS_MTR_AMT_TXT), AMOUNT_SCALE_6));
            setValue(svcPrcRnwLtrWrkTMsg.oldBasePrcDealAmtTxt, setScaleForAmtTxt((String) svcPrcRnwLtrWrkInfo.get(OLD_BASE_PRC_DEAL_AMT_TXT), AMOUNT_SCALE_2));
            // START 2021/02/08 Y.Penequito [QC#58312, ADD]
            String svcInvMergeTpCd = (String) svcPrcRnwLtrWrkInfo.get("SVC_INV_MERGE_TP_CD");
            BigDecimal prntDsContrDtlPk = (BigDecimal) svcPrcRnwLtrWrkInfo.get("PRNT_DS_CONTR_DTL_PK");
            String contrPrcEffFromDt = (String) svcPrcRnwLtrWrkInfo.get("CONTR_PRC_EFF_FROM_DT");
            String basePrcDealAmtTxtMainUnit = (String) svcPrcRnwLtrWrkInfo.get(BASE_PRC_DEAL_AMT_TXT);
            String oldBasePrcDealAmtTxtMainUnit = (String) svcPrcRnwLtrWrkInfo.get(OLD_BASE_PRC_DEAL_AMT_TXT);
            // START 2021/06/28 L.Mandanas [QC#58314-1, DEL]
              // String lineLv = (String) svcPrcRnwLtrWrkInfo.get("LINE_LV");
            // END 2021/06/28 L.Mandanas [QC#58314-1, DEL]
            // Get data for main unit
            if (LINE_LV_ONE.equals(lineLv)) {
                mainContrPrcEffFromDt = contrPrcEffFromDt;
            }

            if (!LINE_LV_THREE.equals(lineLv) && basePrcDealAmtTxtMainUnit != null) {
                if (prntDsContrDtlPk == null || dsContrCatgCd.equals(DS_CONTR_CATG.AGGREGATE)) {
                    // Reset values
                    totalBasePrcDealAmt = new BigDecimal(basePrcDealAmtTxtMainUnit);
                    totalOldBasePrcDealAmt = new BigDecimal(oldBasePrcDealAmtTxtMainUnit);
                    dsCntrDtlPkMainUnit = (BigDecimal) svcPrcRnwLtrWrkInfo.get("DS_CONTR_DTL_PK");
                    dsCntrPkValueMainUnit = (BigDecimal) svcPrcRnwLtrWrkInfo.get(DS_CONTR_PK);
                    mainSeqNumber = dsContrDtlSortNum - 1;
                }
            }

            // Condition: Accessary, Print with Base and CONTR_PRC_EFF_FROM_DT is same with main unit
            if (LINE_LV_THREE.equals(lineLv) && svcInvMergeTpCd.equals(PRINT_BASE) && contrPrcEffFromDt.equals(mainContrPrcEffFromDt)) {
                if (prntDsContrDtlPk.equals(dsCntrDtlPkMainUnit) && hasValue(basePrcDealAmtTxtMainUnit) && hasValue(oldBasePrcDealAmtTxtMainUnit)) {
                    // Add BASE_PRC_DEAL_AMT_TXT and OLD_BASE_PRC_DEAL_AMT_TXT values
                    totalBasePrcDealAmt = totalBasePrcDealAmt.add(new BigDecimal(basePrcDealAmtTxtMainUnit));
                    totalOldBasePrcDealAmt = totalOldBasePrcDealAmt.add(new BigDecimal(oldBasePrcDealAmtTxtMainUnit));
                }
            }
            // END 2021/02/08 Y.Penequito [QC#58312, ADD]
            String svcPrcRnwDescTxt = null;
            String svcPrcRnwLtrTxt = null;
            if (BIZ_APP_ID_NSAB0030.equals(pMsg.bizAppId.getValue())) {
                svcPrcRnwDescTxt = getIntlLangVal(pMsg.glblCmpyCd.getValue(), LANG_KEY_6300);
                svcPrcRnwLtrTxt = getIntlLangVal(pMsg.glblCmpyCd.getValue(), LANG_KEY_23216);
            } else if (BIZ_APP_ID_NSAB0360.equals(pMsg.bizAppId.getValue())) {
                svcPrcRnwDescTxt = getIntlLangVal(pMsg.glblCmpyCd.getValue(), LANG_KEY_23215);
                svcPrcRnwLtrTxt = getIntlLangVal(pMsg.glblCmpyCd.getValue(), LANG_KEY_23217);
            }
            setValue(svcPrcRnwLtrWrkTMsg.svcPrcRnwDescTxt, svcPrcRnwDescTxt);

// QC#25935 Mod Start
//            if (LVL_USAGE.equals((String) svcPrcRnwLtrWrkInfo.get(LINE_LV)) && hasValue(preBllgMtrLbCd) && hasValue((String) svcPrcRnwLtrWrkInfo.get(BLLG_MTR_LB_CD))) {
//                if (!preBllgMtrLbCd.equals((String) svcPrcRnwLtrWrkInfo.get(BLLG_MTR_LB_CD))) {
//                    setValue(svcPrcRnwLtrWrkTMsg.svcPrcRnwDescLineTxt, (String) svcPrcRnwLtrWrkInfo.get(SVC_PRC_RNW_DESC_LINE_TXT));
//                    setValue(svcPrcRnwLtrWrkTMsg.usgBllgCycleDescTxt, (String) svcPrcRnwLtrWrkInfo.get(USG_BLLG_CYCLE_DESC_TXT));
//                    preBllgMtrLbCd = (String) svcPrcRnwLtrWrkInfo.get(BLLG_MTR_LB_CD);
//                }
//            } else {
//                setValue(svcPrcRnwLtrWrkTMsg.svcPrcRnwDescLineTxt, (String) svcPrcRnwLtrWrkInfo.get(SVC_PRC_RNW_DESC_LINE_TXT));
//                setValue(svcPrcRnwLtrWrkTMsg.usgBllgCycleDescTxt, (String) svcPrcRnwLtrWrkInfo.get(USG_BLLG_CYCLE_DESC_TXT));
//                preBllgMtrLbCd = (String) svcPrcRnwLtrWrkInfo.get(BLLG_MTR_LB_CD);
//            }
            setValue(svcPrcRnwLtrWrkTMsg.svcPrcRnwDescLineTxt, (String) svcPrcRnwLtrWrkInfo.get(SVC_PRC_RNW_DESC_LINE_TXT));
            //QC#58314 Mod Start 
            //setValue(svcPrcRnwLtrWrkTMsg.usgBllgCycleDescTxt, (String) svcPrcRnwLtrWrkInfo.get(USG_BLLG_CYCLE_DESC_TXT));
            //QC#58314 Mod End
            preBllgMtrLbCd = (String) svcPrcRnwLtrWrkInfo.get(BLLG_MTR_LB_CD);
// QC#25935 Mod End

            String contrEffFromDtTxt = convertDateFormat((String) svcPrcRnwLtrWrkInfo.get(CONTR_EFF_FROM_DT_TXT));
            if (!hasValue(contrEffFromDtTxt)) {
                svcPrcRnwLtrTxt = "";
            } else {
                svcPrcRnwLtrTxt = svcPrcRnwLtrTxt + SPACE + contrEffFromDtTxt + SPACE + getIntlLangVal(pMsg.glblCmpyCd.getValue(), LANG_KEY_23218);
            }
            setValue(svcPrcRnwLtrWrkTMsg.svcPrcRnwLtrTxt, svcPrcRnwLtrTxt);

         // START 2021/02/10 Y.Penequito [QC#58312, MOD]
            if (LINE_LV_THREE.equals(lineLv) && svcInvMergeTpCd.equals(PRINT_BASE) && contrPrcEffFromDt.equals(mainContrPrcEffFromDt)) {
                SVC_PRC_RNW_LTR_WRKTMsgArray svcPrcRnwLtrWrkTMsgArray = getBasePrcDealAmtTxtList(this.glblCmpyCd, dsCntrPkValueMainUnit);

                if (svcPrcRnwLtrWrkTMsgArray.getValidCount() != 0 || svcPrcRnwLtrWrkTMsgArray != null) {
                    for (int j = 0; j < svcPrcRnwLtrWrkTMsgArray.getValidCount(); j++) {
                        BigDecimal sortNumber = svcPrcRnwLtrWrkTMsgArray.no(j).dsContrDtlSortNum.getValue();
                        if (sortNumber.compareTo(new BigDecimal(mainSeqNumber)) == 0) {
                            // Update total BASE_PRC_DEAL_AMT_TXT and OLD_BASE_PRC_DEAL_AMT_TXT of main unit
                            // START 2021/06/28 L.Mandanas [QC#58314-1, MOD]
                              //ZYPEZDItemValueSetter.setValue(svcPrcRnwLtrWrkTMsgArray.no(j).basePrcDealAmtTxt, getNumberFormat(totalBasePrcDealAmt.toString()));
                              //ZYPEZDItemValueSetter.setValue(svcPrcRnwLtrWrkTMsgArray.no(j).oldBasePrcDealAmtTxt, getNumberFormat(totalOldBasePrcDealAmt.toString()));
                            ZYPEZDItemValueSetter.setValue(
                              svcPrcRnwLtrWrkTMsgArray.no(j).basePrcDealAmtTxt,
                              setScaleForAmtTxt(
                                  totalBasePrcDealAmt.toString(),
                                  AMOUNT_SCALE_2));
                            ZYPEZDItemValueSetter.setValue(
                              svcPrcRnwLtrWrkTMsgArray.no(j).oldBasePrcDealAmtTxt,
                              setScaleForAmtTxt(
                                  totalOldBasePrcDealAmt.toString(),
                                  AMOUNT_SCALE_2));
                            // END 2021/06/28 L.Mandanas [QC#58314-1, MOD]
                            S21ApiTBLAccessor.update(svcPrcRnwLtrWrkTMsgArray.no(j));
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(svcPrcRnwLtrWrkTMsgArray.no(j).getReturnCode())) {
                              msgMap.addXxMsgId(NSZM0858E);
                              return;
                            }
                        }
                    }
                }
            } else {
                S21ApiTBLAccessor.insert(svcPrcRnwLtrWrkTMsg);
                String rtnCd = svcPrcRnwLtrWrkTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    msgMap.addXxMsgId(NSZM0858E);
                    return;
                }
            }
            // END 2021/02/10 Y.Penequito [QC#58312, MOD]
        }
    }
    // END 2017/08/22 M.Naito [QC#8661, MOD]

    // START 2021/02/02 Y.Penequito [QC#58312, ADD]
    /**
     * getBasePrcDealAmtTxtList
     * @param glblCmpyCode String
     * @param dsCntrPk BigDecimal
     * @return SVC_PRC_RNW_LTR_WRKTMsgArray
     */
    public SVC_PRC_RNW_LTR_WRKTMsgArray getBasePrcDealAmtTxtList(String glblCmpyCode, BigDecimal dsCntrPk) {
        SVC_PRC_RNW_LTR_WRKTMsg svcParam = new SVC_PRC_RNW_LTR_WRKTMsg();
        svcParam.setSQLID("001");
        svcParam.setConditionValue("glblCmpyCd01", glblCmpyCode);
        svcParam.setConditionValue("dsContrPk01", dsCntrPk);
        SVC_PRC_RNW_LTR_WRKTMsgArray result =  (SVC_PRC_RNW_LTR_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(svcParam);
        return result;
    }
    // END 2021/02/02 Y.Penequito [QC#58312, ADD]

    private void insertSvcTermCondLtrWrk(List<Map<String, Object>> svcTermCondLtrWrkList) {
        NSZC100001PMsg pMsg = (NSZC100001PMsg) msgMap.getPmsg();

        for (int i = 0; i < svcTermCondLtrWrkList.size(); i++) {
            Map<String, Object> svcTermCondLtrWrkInfo = svcTermCondLtrWrkList.get(i);
            SVC_TERM_COND_LTR_WRKTMsg svcTermCondLtrWrkTMsg = new SVC_TERM_COND_LTR_WRKTMsg();

            setValue(svcTermCondLtrWrkTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(svcTermCondLtrWrkTMsg.svcTermCondLtrWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_LTR_WRK_SQ));
            setValue(svcTermCondLtrWrkTMsg.dsContrPk, pMsg.dsContrPk);
            setValue(svcTermCondLtrWrkTMsg.slsDt, pMsg.slsDt);
            setValue(svcTermCondLtrWrkTMsg.bizAppId, pMsg.bizAppId);
            setValue(svcTermCondLtrWrkTMsg.otptOpCd, pMsg.otptOpCd);
            setValue(svcTermCondLtrWrkTMsg.specSideTpCd, (String) svcTermCondLtrWrkInfo.get(SPEC_SIDE_TP_CD));
            setValue(svcTermCondLtrWrkTMsg.svcTermCondHdrNum, (BigDecimal) svcTermCondLtrWrkInfo.get(SVC_TERM_COND_HDR_NUM));
            setValue(svcTermCondLtrWrkTMsg.svcTermCondDtlNum, (BigDecimal) svcTermCondLtrWrkInfo.get(SVC_TERM_COND_DTL_NUM));
            setValue(svcTermCondLtrWrkTMsg.ttlValTxt, (String) svcTermCondLtrWrkInfo.get(TTL_VAL_TXT));
            setValue(svcTermCondLtrWrkTMsg.descValTxt, (String) svcTermCondLtrWrkInfo.get(DESC_VAL_TXT));
            setValue(svcTermCondLtrWrkTMsg.termCondVrsnTxt, (String) svcTermCondLtrWrkInfo.get(TERM_COND_VRSN_TXT));
            S21ApiTBLAccessor.insert(svcTermCondLtrWrkTMsg);
            String rtnCd = svcTermCondLtrWrkTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM0859E);
                return;
            }
        }
    }

    private String getNumberFormat(String data) {
        if (hasValue(data)) {
            return ZYPFormatUtil.formatNumToSysDisp(new BigDecimal(data));
        }
        return data;
    }

    // START 2017/08/22 M.Naito [QC#8661, ADD]
    private String setScaleForAmtTxt(String data, int amountScale) {
        BigDecimal setScaleData = BigDecimal.ZERO;
        if (hasValue(data)) {
            data = new BigDecimal(data).setScale(amountScale).toString();
            return getNumberFormat(data);
        }
        return data;
    }
    // END 2017/08/22 M.Naito [QC#8661, ADD]

    private String getNumberFormat(BigDecimal data) {
        if (hasValue(data)) {
            return ZYPFormatUtil.formatNumToSysDisp(data);
        }
        return "";
    }

    private DS_CONTRTMsg getDsContr(BigDecimal dsContrPk) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
    }

    // START 2017/08/22 M.Naito [QC#8661, MOD]
//    private List<Map<String, Object>> getNonFleetContrInfo(BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList, String slsDt) {
    private List<Map<String, Object>> getNonFleetContrInfo(BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList, String slsDt, String bizAppId, String dsContrCatg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPkList", dsContrDtlPkList);
//        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("svcMachTpCdIsMach", SVC_MACH_TP.MACHINE);
        ssmPrm.put("svcMachTpCdIsAcc", SVC_MACH_TP.ACCESSORY);
//        ssmPrm.put("dsContrDtlTpCdIsAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        ssmPrm.put("bizAppId", bizAppId);
        ssmPrm.put("dsContrCatg", dsContrCatg);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getNonFleetContrInfo", ssmPrm);
    }

//    private List<Map<String, Object>> getFleetContrInfo(BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList, String slsDt) {
    private List<Map<String, Object>> getFleetContrInfo(BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList, String slsDt, String bizAppId) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPkList", dsContrDtlPkList);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("fleetContract", FLEET_CONTRACT);
        ssmPrm.put("dsContrDtlTpCdIsFleet", DS_CONTR_DTL_TP.FLEET);
        ssmPrm.put("svcMachTpCdIsMach", SVC_MACH_TP.MACHINE);
        ssmPrm.put("svcMachTpCdIsAcc", SVC_MACH_TP.ACCESSORY);
//        ssmPrm.put("dsContrDtlTpCdIsAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        ssmPrm.put("bizAppId", bizAppId);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getFleetContrInfo", ssmPrm);
    }
    // END 2017/08/22 M.Naito [QC#8661, MOD]

    private List<Map<String, Object>> getSvcTermCondLtrInfo(BigDecimal dsContrPk, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", this.glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("slsDt", slsDt);
        String[] dsContrDtlTpList = new String[] {DS_CONTR_DTL_TP.FLEET, DS_CONTR_DTL_TP.AGGREGATE };
        ssmPrm.put("dsContrDtlTpList", dsContrDtlTpList);
        ssmPrm.put("maxThruDt", MAX_THRU_DT);
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSvcTermCondLtrInfo", ssmPrm);
    }

// START 2017/08/08 K.Kim [QC#20351, ADD]
  //START 2017/08/22 M.Naito [QC#8661, DEL]
//  /**
//   * get SVC_PRC_RNW_LTR_WRK
//   * @param glblCmpyCd String
//   * @param dsContrPk BigDecimal
//   * @return SVC_PRC_RNW_LTR_WRKTMsgArray
//   */
//  private static SVC_PRC_RNW_LTR_WRKTMsgArray getSVC_PRC_RNW_LTR_WRK(String glblCmpyCd, BigDecimal dsContrPk) {
//      SVC_PRC_RNW_LTR_WRKTMsg param = new SVC_PRC_RNW_LTR_WRKTMsg();
//      param.setSQLID("001");
//      param.setConditionValue("glblCmpyCd01", glblCmpyCd);
//      param.setConditionValue("dsContrPk01", dsContrPk);
//      SVC_PRC_RNW_LTR_WRKTMsgArray result =  (SVC_PRC_RNW_LTR_WRKTMsgArray) EZDTBLAccessor.findByCondition(param);
//      return result;
//  }
  //END 2017/08/22 M.Naito [QC#8661, DEL]

  //START 2017/08/22 M.Naito [QC#8661, ADD]
  /**
   * getDsContrHdrInfo
   * @param glblCmpyCd String
   * @param dsContrPk BigDecimal
   * @param bizAppId String
   * @param rptId String
   * @return List<SVC_PRC_RNW_LTR_WRKTMsg>
   */
  private Map<String, Object> getDsContrHdrInfo(String glblCmpyCd, BigDecimal dsContrPk) {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("glblCmpyCd", this.glblCmpyCd);
      param.put("dsContrPk", dsContrPk);
      return (Map<String, Object>) ssmBatchClient.queryObject("getDsContrHdrInfo", param);
  }
  //END 2017/08/22 M.Naito [QC#8661, ADD]

  /**
   * get SVC_TERM_COND_LTR_WRK
   * @param glblCmpyCd String
   * @param dsContrPk BigDecimal
   * @return SVC_TERM_COND_LTR_WRKTMsgArray
   */
  private static SVC_TERM_COND_LTR_WRKTMsgArray getSVC_TERM_COND_LTR_WRK(String glblCmpyCd, BigDecimal dsContrPk) {
      SVC_TERM_COND_LTR_WRKTMsg param = new SVC_TERM_COND_LTR_WRKTMsg();
      param.setSQLID("001");
      param.setConditionValue("glblCmpyCd01", glblCmpyCd);
      param.setConditionValue("dsContrPk01", dsContrPk);
      SVC_TERM_COND_LTR_WRKTMsgArray result =  (SVC_TERM_COND_LTR_WRKTMsgArray) EZDTBLAccessor.findByCondition(param);
      return result;
  }

  //START 2017/08/22 M.Naito [QC#8661, MOD]
  /**
   * removePhysical SVC_PRC_RNW_LTR_WRK
   * @param glblCmpyCd String
   * @param dsContrPk BigDecimal
   * @return boolean
   * @throws SQLException
   */
//  private static boolean deletePrcRnwLtrWrk(String glblCmpyCd, BigDecimal dsContrPk) throws SQLException {
  private boolean deletePrcRnwLtrWrk(String glblCmpyCd, BigDecimal dsContrPk, String bizAppId) throws SQLException {
      List<SVC_PRC_RNW_LTR_WRKTMsg> deleteTMsgList = new ArrayList<SVC_PRC_RNW_LTR_WRKTMsg>();
//      SVC_PRC_RNW_LTR_WRKTMsgArray result =  getSVC_PRC_RNW_LTR_WRK(glblCmpyCd, dsContrPk);

      SVC_PRC_RNW_LTR_WRKTMsg param = new SVC_PRC_RNW_LTR_WRKTMsg();
      param.setSQLID("003");
      param.setConditionValue("glblCmpyCd01", glblCmpyCd);
      param.setConditionValue("dsContrPk01", dsContrPk);
      param.setConditionValue("bizAppId01", bizAppId);
      SVC_PRC_RNW_LTR_WRKTMsgArray result = (SVC_PRC_RNW_LTR_WRKTMsgArray) EZDTBLAccessor.findByCondition(param);
      if (result.getValidCount() == 0) {
          return true;
      }
      for (int i = 0; i < result.getValidCount(); i++) {
          deleteTMsgList.add(result.no(i));
      }
      int delCnt = S21FastTBLAccessor.removePhysical(deleteTMsgList.toArray(new SVC_PRC_RNW_LTR_WRKTMsg[deleteTMsgList.size()]));
      if (delCnt != deleteTMsgList.size()) {
          EZDConnectionMgr.getInstance().rollback();
          return false;
      }
      return true;
  }
  //END 2017/08/22 M.Naito [QC#8661, MOD]

  /**
   * removePhysical SVC_TERM_COND_LTR_WRK
   * @param glblCmpyCd String
   * @param dsContrPk BigDecimal
   * @return boolean
   * @throws SQLException
   */
  private static boolean deleteTermCondLtrWrk(String glblCmpyCd, BigDecimal dsContrPk) throws SQLException {
      List<SVC_TERM_COND_LTR_WRKTMsg> deleteTMsgList = new ArrayList<SVC_TERM_COND_LTR_WRKTMsg>();
      SVC_TERM_COND_LTR_WRKTMsgArray result =  getSVC_TERM_COND_LTR_WRK(glblCmpyCd, dsContrPk);
      if (result.getValidCount() == 0) {
          return true;
      }
      for (int i = 0; i < result.getValidCount(); i++) {
          deleteTMsgList.add(result.no(i));
      }
      int delCnt = S21FastTBLAccessor.removePhysical(deleteTMsgList.toArray(new SVC_TERM_COND_LTR_WRKTMsg[deleteTMsgList.size()]));
      if (delCnt != deleteTMsgList.size()) {
          EZDConnectionMgr.getInstance().rollback();
          return false;
      }
      return true;
  }
//END 2017/08/08 K.Kim [QC#20351, ADD]

  //START 2017/08/22 M.Naito [QC#8661, ADD]
  /**
   * get INTL_LANG_VAL
   * @param glblCmpyCd String
   * @param langKeyCd String
   * @return String
   */
  private String getIntlLangVal(String glblCmpyCd, String langKeyCd) {
      INTL_LANG_VALTMsg tMsg = new INTL_LANG_VALTMsg();
      ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
      ZYPEZDItemValueSetter.setValue(tMsg.langKeyCd, langKeyCd);
      tMsg = (INTL_LANG_VALTMsg) S21ApiTBLAccessor.findByKey(tMsg);
      return tMsg.engValTxt.getValue();
  }

  /**
   * convert format(yyyyMMdd) to dd-MMM-yyyy
   * @param yyyyMMdd String
   * @return String
   */
  public String convertDateFormat(String yyyyMMdd) {
      if (yyyyMMdd == null) {
          return null;
      }
      SimpleDateFormat inFormat = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
      inFormat.setLenient(false);
      Date inputDate;
      try {
          inputDate = inFormat.parse(yyyyMMdd);
      } catch (ParseException e) {
          return null;
      }
      SimpleDateFormat sdf;
      try {
          sdf = new SimpleDateFormat(DATE_FORMAT_DDMMMYYYY, Locale.ENGLISH);
      } catch (Exception e) {
          return null;
      }
      return sdf.format(inputDate);
  }
  //END 2017/08/22 M.Naito [QC#8661, ADD]
}
