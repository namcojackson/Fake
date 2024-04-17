package com.canon.cusa.s21.api.NPZ.NPZC115001;

import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.COST_UPDATE_MODE;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.DEF_THRU_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.ENTRY_MODE;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0001E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0059E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0079E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0093E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0236E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0237E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0238E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0239E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0240E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0241E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0242E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0243E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0244E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0245E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0246E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0247E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0248E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0249E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0251E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0252E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0253E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0254E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0255E;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0256W;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0257W;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.NPZM0309W;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.ROUND;
import static com.canon.cusa.s21.api.NPZ.NPZC115001.constant.NPZC115001Constant.ROUND_UP;
import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ASL_DTLTMsg;
import business.db.ASL_HDRTMsg;
import business.db.ASL_QLFY_RELNTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_CST_UPD_HIST_DTLTMsg;
import business.db.MDSE_CST_UPD_HIST_HDRTMsg;
import business.parts.NPZC115001PMsg;
import business.parts.NPZC115001_xxAslDtlListPMsg;
import business.parts.NPZC115001_xxAslQlfyRelnListPMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASL_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * ASL Update API.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   CITS            T.Gotoda        Create          N/A
 * 2017/06/21   CITS            S.Katsuma       Update          QC#13782
 * 2018/04/11   CITS            K.Fukumura      Update          QC#21170
 * 2019/01/11   CITS            M.Naito         Update          QC#29694
 * 2020/03/09   CITS            K.Ogino         Update          QC#56059
 * 2023/02/07   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPZC115001 extends S21ApiCommonBase {

    /** S21SsmBatchClient. */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code. */
    private String glblCmpyCd = null;

    /** Process Date. */
    private String procDt = null;

    /**
     * Constructor.
     */
    public NPZC115001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Update ASL API.
     * @param pMsg NPZC115001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC115001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        // set message map
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        // check Mandatory
        if (!checkCommonMandatoryItem(pMsg, msgMap)) {
            // Error
            msgMap.flush();
            return;
        }

        glblCmpyCd = pMsg.glblCmpyCd.getValue();
        procDt = pMsg.procDt.getValue();

        if (ENTRY_MODE.equals(pMsg.xxModeCd.getValue())) {

            // checkEntryMode
            if (!checkEntryMode(pMsg, msgMap)) {
                // Error
                msgMap.flush();
                return;
            }
            // executeEntryMode
            executeEntryMode(pMsg, msgMap);

        } else if (COST_UPDATE_MODE.equals(pMsg.xxModeCd.getValue())) {

            // checkCostUpdateMode
            if (!checkCostUpdateMode(pMsg, msgMap)) {
                // Error
                msgMap.flush();
                return;
            }

            // executeUpdateMode
            executeCostUpdateMode(pMsg, msgMap);

        } else {
            // process mode invalid value.
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0093E);
            // END 2017/06/21 S.Katsuma [QC#13782]
        }

        // end
        msgMap.flush();
    }

    /**
     * Execute Entry Mode.
     * @param pMsg NPZC115001PMsg
     * @param msgMap S21ApiMessageMap
     */
    private void executeEntryMode(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        // ASL Header
        if (!ZYPCommonFunc.hasValue(pMsg.aslHdrPk)) {

            // Create ASL Header
            if (!createAslHdr(pMsg, msgMap)) {
                return;
            }

            // Create or Update ASL Details
            if (!createOrUpdateAslDtlList(pMsg, msgMap)) {
                return;
            }
        } else {

            // Create or Update ASL Details
            if (!createOrUpdateAslDtlList(pMsg, msgMap)) {
                return;
            }

            // Update ASL Header
            if (!updateAslHdr(pMsg, msgMap)) {
                return;
            }
        }

        // Create or Update ASL Qualifier Relation
        if (!createOrUpdateAslQlfyRelnList(pMsg, msgMap)) {
            return;
        }
    }

    /**
     * Execute Cost Update Mode.
     * @param pMsg
     * @param msgMap
     */
    @SuppressWarnings("unchecked")
    private void executeCostUpdateMode(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        for (int i = 0; i < pMsg.xxAslDtlList.getValidCount(); i++) {

            boolean costUpdateAgainFlag = false;

            NPZC115001_xxAslDtlListPMsg xxAslDtl = pMsg.xxAslDtlList.no(i);

            List<Map> resultList = getAslDtlInfo(pMsg, i);

            // START 2019/01/11 M.Naito [QC#29694,MOD]
//            if (resultList == null || resultList.size() != 1) {
            if (resultList == null) {
                // Skip
                xxAslDtl.xxMsgId.setValue(NPZM0256W);
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0256W);
                // END 2017/06/21 S.Katsuma [QC#13782]
                continue;
            }

            boolean isChangeUnitPrcAmt = false;
            for (Map result : resultList) {
//            Map result = resultList.get(0);
                BigDecimal aslHdrPk = (BigDecimal) result.get("ASL_HDR_PK");

                // check Vendor UOM Code
                String vndUomCd = (String) result.get("VND_UOM_CD");
                if (ZYPCommonFunc.hasValue(xxAslDtl.vndUomCd)) {
                    if (!xxAslDtl.vndUomCd.getValue().equals(vndUomCd)) {
                        // Skip
                        xxAslDtl.xxMsgId.setValue(NPZM0257W);
                        // START 2017/06/21 S.Katsuma [QC#13782]
                        addUniqueXxMsgId(msgMap, NPZM0257W);
                        // END 2017/06/21 S.Katsuma [QC#13782]
                        continue;
                    }
                }

                // Check PO Price
                BigDecimal unitPrcAmt = (BigDecimal) result.get("UNIT_PRC_AMT");
                if (pMsg.xxAslDtlList.no(i).unitPrcAmt.getValue().compareTo(unitPrcAmt) == 0) {
                    // Skip No change price
                    continue;
                }

                // Calculate Effective Date
                String effFromDt = (String) result.get("EFF_FROM_DT");
                String effThruDt = (String) result.get("EFF_THRU_DT");

                String newEffFromDt = null;
                String newEffThruDt = null;

                if (!ZYPCommonFunc.hasValue(xxAslDtl.effFromDt)) {
                    newEffFromDt = procDt;
                } else {
                    newEffFromDt = xxAslDtl.effFromDt.getValue();
                }

                if (!ZYPCommonFunc.hasValue(xxAslDtl.effThruDt)) {
                    // QC#56059 Future day check
                    List<Map> futureDayList = getFutureDay(pMsg, i, ZYPDateUtil.addDays(effThruDt, 1));
                    if (futureDayList == null || futureDayList.isEmpty()) {
                        newEffThruDt = DEF_THRU_DT;
                    } else {
                        newEffThruDt = ZYPDateUtil.addDays((String) futureDayList.get(0).get("EFF_FROM_DT"), -1);
                    }
                } else {
                    newEffThruDt = xxAslDtl.effThruDt.getValue();
                }

                if (newEffFromDt.compareTo(effFromDt) == 0) {
                    newEffThruDt = effThruDt;
                    costUpdateAgainFlag = true;

                } else if (newEffFromDt.compareTo(effFromDt) < 0) {

                    // Skip Past date
                    continue;

                }

                // Create New Item Price
                ASL_DTLTMsg newAslDtl = new ASL_DTLTMsg();

                ZYPEZDItemValueSetter.setValue(newAslDtl.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(newAslDtl.aslHdrPk, aslHdrPk);

                // Get PK
                BigDecimal aslDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("ASL_DTL_SQ");
                ZYPEZDItemValueSetter.setValue(newAslDtl.aslDtlPk, aslDtlPk);

                String mdseCd = (String) result.get("MDSE_CD");
                ZYPEZDItemValueSetter.setValue(newAslDtl.mdseCd, mdseCd);

                String coaMdseTpCd = getCoaMdseTpCd(mdseCd);
                ZYPEZDItemValueSetter.setValue(newAslDtl.coaMdseTpCd, coaMdseTpCd);

                ZYPEZDItemValueSetter.setValue(newAslDtl.vndUomCd, vndUomCd);

                String splyItemNum = (String) result.get("SPLY_ITEM_NUM");
                ZYPEZDItemValueSetter.setValue(newAslDtl.splyItemNum, splyItemNum);

                ZYPEZDItemValueSetter.setValue(newAslDtl.vndCd, xxAslDtl.vndCd);

                String primSplyFlg = (String) result.get("PRIM_SPLY_FLG");
                ZYPEZDItemValueSetter.setValue(newAslDtl.primSplyFlg, primSplyFlg);

                ZYPEZDItemValueSetter.setValue(newAslDtl.unitPrcAmt, xxAslDtl.unitPrcAmt);

                BigDecimal baseOrdQty = (BigDecimal) result.get("BASE_ORD_QTY");
                ZYPEZDItemValueSetter.setValue(newAslDtl.baseOrdQty, baseOrdQty);

                BigDecimal vndUomQty = (BigDecimal) result.get("VND_UOM_QTY");
                ZYPEZDItemValueSetter.setValue(newAslDtl.vndUomQty, vndUomQty);

                BigDecimal vndIncrOrdQty = (BigDecimal) result.get("VND_INCR_ORD_QTY");
                ZYPEZDItemValueSetter.setValue(newAslDtl.vndIncrOrdQty, vndIncrOrdQty);

                BigDecimal vndMinOrdQty = (BigDecimal) result.get("VND_MIN_ORD_QTY");
                ZYPEZDItemValueSetter.setValue(newAslDtl.vndMinOrdQty, vndMinOrdQty);

                // QC#21170 2018/04/11 Start
                BigDecimal vndLtDaysNum = (BigDecimal) result.get("VND_LT_DAYS_NUM");
                ZYPEZDItemValueSetter.setValue(newAslDtl.vndLtDaysNum, vndLtDaysNum);
                // QC#21170 2018/04/11 Start
                
                // START 2023/02/07 S.Dong [QC#60966, ADD]
                BigDecimal vndShipLtDaysNum = (BigDecimal) result.get("VND_SHIP_LT_DAYS_NUM");
                ZYPEZDItemValueSetter.setValue(newAslDtl.vndShipLtDaysNum, vndShipLtDaysNum);
                // END 2023/02/07 S.Dong [QC#60966, ADD]
                
                ZYPEZDItemValueSetter.setValue(newAslDtl.effFromDt, newEffFromDt);
                ZYPEZDItemValueSetter.setValue(newAslDtl.effThruDt, newEffThruDt);

                S21ApiTBLAccessor.create(newAslDtl);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(newAslDtl.getReturnCode())) {
                    // Error
                    xxAslDtl.xxMsgId.setValue(NPZM0252E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0252E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    return;
                }

                if (costUpdateAgainFlag) {

                    // Update Previous Item Price Effective Thru Date
                    ASL_DTLTMsg prevAslDtl = new ASL_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(prevAslDtl.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(prevAslDtl.aslHdrPk, aslHdrPk);

                    BigDecimal prevAslDtlPk = (BigDecimal) result.get("ASL_DTL_PK");
                    ZYPEZDItemValueSetter.setValue(prevAslDtl.aslDtlPk, prevAslDtlPk);

                    prevAslDtl = (ASL_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(prevAslDtl);
                    if (prevAslDtl == null) {
                        // Error
                        xxAslDtl.xxMsgId.setValue(NPZM0252E);
                        // START 2017/06/21 S.Katsuma [QC#13782]
                        addUniqueXxMsgId(msgMap, NPZM0252E);
                        // END 2017/06/21 S.Katsuma [QC#13782]
                        return;
                    }

                    S21ApiTBLAccessor.logicalRemove(prevAslDtl);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prevAslDtl.getReturnCode())) {
                        // Error
                        xxAslDtl.xxMsgId.setValue(NPZM0252E);
                        // START 2017/06/21 S.Katsuma [QC#13782]
                        addUniqueXxMsgId(msgMap, NPZM0252E);
                        // END 2017/06/21 S.Katsuma [QC#13782]
                        return;
                    }

                } else if (newEffFromDt.compareTo(effThruDt) <= 0) {
                    // Update Previous Item Price Effective Thru Date
                    ASL_DTLTMsg prevAslDtl = new ASL_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(prevAslDtl.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(prevAslDtl.aslHdrPk, aslHdrPk);

                    BigDecimal prevAslDtlPk = (BigDecimal) result.get("ASL_DTL_PK");
                    ZYPEZDItemValueSetter.setValue(prevAslDtl.aslDtlPk, prevAslDtlPk);

                    prevAslDtl = (ASL_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(prevAslDtl);
                    if (prevAslDtl == null) {
                        // Error
                        xxAslDtl.xxMsgId.setValue(NPZM0252E);
                        // START 2017/06/21 S.Katsuma [QC#13782]
                        addUniqueXxMsgId(msgMap, NPZM0252E);
                        // END 2017/06/21 S.Katsuma [QC#13782]
                        return;
                    }

                    ZYPEZDItemValueSetter.setValue(prevAslDtl.effThruDt, ZYPDateUtil.addDays(newEffFromDt, -1));
                    S21ApiTBLAccessor.update(prevAslDtl);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(prevAslDtl.getReturnCode())) {
                        // Error
                        xxAslDtl.xxMsgId.setValue(NPZM0252E);
                        // START 2017/06/21 S.Katsuma [QC#13782]
                        addUniqueXxMsgId(msgMap, NPZM0252E);
                        // END 2017/06/21 S.Katsuma [QC#13782]
                        return;
                    }

                }
                isChangeUnitPrcAmt = true;

                // has BigDeal# or MDSE Cost Update History flag not
                // equals 'Y';
                if (pMsg.xxAslQlfyRelnList.getValidCount() > 0 || !ZYPConstant.FLG_ON_Y.equals(xxAslDtl.xxValUpdFlg.getValue())) {
                    // skip Item Cost Update
                    continue;
                }

                // MDSE Cost Update History Header
                MDSE_CST_UPD_HIST_HDRTMsg mdseCstHdr = new MDSE_CST_UPD_HIST_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(mdseCstHdr.glblCmpyCd, glblCmpyCd);

                // Get PK
                BigDecimal mdseCstUpdHistHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal("MDSE_CST_UPD_HIST_HDR_SQ");
                ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);

                String azertyCostUpdataTxt = "AZERTY COST UPDATE" + ZYPDateUtil.getCurrentSystemTime("MMddyyyyHHmmss");

                if (ZYPCommonFunc.hasValue(xxAslDtl.mdseCstUpdGrpTxt)) {
                    ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdGrpTxt, xxAslDtl.mdseCstUpdGrpTxt);
                } else {
                    ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdGrpTxt, azertyCostUpdataTxt);
                }

                ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdTpCd, MDSE_CST_UPD_TP.VENDOR_UPDATE);

                if (ZYPCommonFunc.hasValue(xxAslDtl.mdseCstUpdDescTxt)) {
                    ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdDescTxt, xxAslDtl.mdseCstUpdDescTxt);
                } else {
                    ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdDescTxt, azertyCostUpdataTxt);
                }

                if (ZYPCommonFunc.hasValue(xxAslDtl.mdseCstUpdRefTxt)) {
                    ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdRefTxt, xxAslDtl.mdseCstUpdRefTxt);
                }

                if (ZYPCommonFunc.hasValue(xxAslDtl.mdseCstUpdRefId)) {
                    ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdRefId, xxAslDtl.mdseCstUpdRefId);
                }

                String bizAppId = EZDDBCICarrier.getUppgID().substring(0, 8);
                ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdCratPsnCd, bizAppId);

                ZYPEZDItemValueSetter.setValue(mdseCstHdr.mdseCstUpdCratDt, procDt);

                S21ApiTBLAccessor.create(mdseCstHdr);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseCstHdr.getReturnCode())) {
                    // Error
                    xxAslDtl.xxMsgId.setValue(NPZM0254E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0254E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    return;
                }

                // MDSE Cost Update History Detail
                MDSE_CST_UPD_HIST_DTLTMsg mdseCstDtl = new MDSE_CST_UPD_HIST_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(mdseCstDtl.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseCstDtl.mdseCstUpdHistHdrPk, mdseCstUpdHistHdrPk);
                ZYPEZDItemValueSetter.setValue(mdseCstDtl.mdseCd, mdseCd);

                // Get Var Char Const
                String costRound = ZYPCodeDataUtil.getVarCharConstValue("COST_ROUND_OPTION", glblCmpyCd);
                int costPrecision = Integer.valueOf(ZYPCodeDataUtil.getVarCharConstValue("COST_PRECISION", glblCmpyCd));

                BigDecimal rqstTotStdCostAmt = BigDecimal.ZERO;

                if (ROUND_UP.equals(costRound)) {
                    rqstTotStdCostAmt = (xxAslDtl.unitPrcAmt.getValue()).divide(vndUomQty, costPrecision, BigDecimal.ROUND_UP).multiply(baseOrdQty);

                } else if (ROUND.equals(costRound)) {
                    rqstTotStdCostAmt = (xxAslDtl.unitPrcAmt.getValue()).divide(vndUomQty, costPrecision, BigDecimal.ROUND_HALF_UP).multiply(baseOrdQty);

                } else {
                    rqstTotStdCostAmt = (xxAslDtl.unitPrcAmt.getValue()).divide(vndUomQty, costPrecision, BigDecimal.ROUND_DOWN).multiply(baseOrdQty);
                }
                ZYPEZDItemValueSetter.setValue(mdseCstDtl.rqstTotStdCostAmt, rqstTotStdCostAmt);

                ZYPEZDItemValueSetter.setValue(mdseCstDtl.pkgUomCd, PKG_UOM.EACH);
                ZYPEZDItemValueSetter.setValue(mdseCstDtl.mdseCstUpdEffFromDt, newEffFromDt);
                ZYPEZDItemValueSetter.setValue(mdseCstDtl.mdseCstUpdStsCd, MDSE_CST_UPD_STS.PENDING);
                S21ApiTBLAccessor.create(mdseCstDtl);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(mdseCstDtl.getReturnCode())) {
                    // Error
                    xxAslDtl.xxMsgId.setValue(NPZM0255E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0255E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    return;
                }
            }
            if (isChangeUnitPrcAmt) {
                xxAslDtl.xxMsgId.setValue(NPZM0309W);
                addUniqueXxMsgId(msgMap, NPZM0309W);
            }
            // END 2019/01/11 M.Naito [QC#29694,MOD]
        }
    }

    /**
     * create ASL Header.
     * @param pMsg NPZC115001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    private boolean createAslHdr(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        ASL_HDRTMsg aslHdr = new ASL_HDRTMsg();

        // set pk
        BigDecimal aslHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal("ASL_HDR_SQ");
        ZYPEZDItemValueSetter.setValue(pMsg.aslHdrPk, aslHdrPk);
        ZYPEZDItemValueSetter.setValue(aslHdr.aslHdrPk, aslHdrPk);
        ZYPEZDItemValueSetter.setValue(aslHdr.glblCmpyCd, glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(aslHdr.aslNm, pMsg.aslNm);
        ZYPEZDItemValueSetter.setValue(aslHdr.aslDescTxt, pMsg.aslDescTxt);
        ZYPEZDItemValueSetter.setValue(aslHdr.prntVndCd, pMsg.prntVndCd);

        if (ZYPCommonFunc.hasValue(pMsg.aslStartDt)) {
            ZYPEZDItemValueSetter.setValue(aslHdr.aslStartDt, pMsg.aslStartDt);
        } else {
            ZYPEZDItemValueSetter.setValue(aslHdr.aslStartDt, pMsg.procDt);
        }

        ZYPEZDItemValueSetter.setValue(aslHdr.aslEndDt, pMsg.aslEndDt);

        S21ApiTBLAccessor.create(aslHdr);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(aslHdr.getReturnCode())) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0251E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }

        return true;
    }

    /**
     * update ASL Header.
     * @param pMsg NPZC115001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    @SuppressWarnings("unchecked")
    private boolean updateAslHdr(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        ASL_HDRTMsg aslHdr = new ASL_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(aslHdr.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aslHdr.aslHdrPk, pMsg.aslHdrPk);
        aslHdr = (ASL_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(aslHdr);
        if (aslHdr == null) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0251E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }

        ZYPEZDItemValueSetter.setValue(aslHdr.aslNm, pMsg.aslNm);
        ZYPEZDItemValueSetter.setValue(aslHdr.aslDescTxt, pMsg.aslDescTxt);

        if (ZYPCommonFunc.hasValue(pMsg.aslStartDt)) {
            ZYPEZDItemValueSetter.setValue(aslHdr.aslStartDt, pMsg.aslStartDt);
        }

        // Calculate ASL End Date
        Map result = getAslEndDt(pMsg);
        if (result != null) {
            String aslEndDt = (String) result.get("ASL_END_DT");
            String effThruDt = (String) result.get("EFF_THRU_DT");

            if (!DEF_THRU_DT.equals(aslEndDt)) {
                if (!DEF_THRU_DT.equals(effThruDt)) {
                    if (effThruDt.compareTo(aslEndDt) > 0) {
                        ZYPEZDItemValueSetter.setValue(aslHdr.aslEndDt, effThruDt);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(aslHdr.aslEndDt, pMsg.aslEndDt);
                }
            }
        }

        S21ApiTBLAccessor.update(aslHdr);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(aslHdr.getReturnCode())) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0251E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }

        return true;
    }

    /**
     * create or update ASL Detail List.
     * @param pMsg NPZC115001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    private boolean createOrUpdateAslDtlList(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        for (int i = 0; i < pMsg.xxAslDtlList.getValidCount(); i++) {

            BigDecimal aslHdrPk = pMsg.aslHdrPk.getValue();

            if (!ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).aslDtlPk)) {
                if (!createAslDtl(aslHdrPk, pMsg.xxAslDtlList.no(i), msgMap)) {
                    return false;
                }
            } else {
                if (!updateAslDtl(aslHdrPk, pMsg.xxAslDtlList.no(i), msgMap)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * create ASL Detail.
     * @param aslHdrPk aslHdrPk
     * @param xxAslDtl NPZC115001_xxAslDtlListPMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     * @return
     */
    private boolean createAslDtl(BigDecimal aslHdrPk, NPZC115001_xxAslDtlListPMsg xxAslDtl, S21ApiMessageMap msgMap) {

        ASL_DTLTMsg aslDtl = new ASL_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(aslDtl.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aslDtl.aslHdrPk, aslHdrPk);

        // Get PK
        BigDecimal aslDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("ASL_DTL_SQ");
        ZYPEZDItemValueSetter.setValue(aslDtl.aslDtlPk, aslDtlPk);

        ZYPEZDItemValueSetter.setValue(aslDtl.mdseCd, xxAslDtl.mdseCd);

        String coaMdseTpCd = getCoaMdseTpCd(xxAslDtl.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(aslDtl.coaMdseTpCd, coaMdseTpCd);

        if (ZYPCommonFunc.hasValue(xxAslDtl.vndUomCd)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndUomCd, xxAslDtl.vndUomCd);
        } else {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndUomCd, VND_UOM.EACH);
        }

        ZYPEZDItemValueSetter.setValue(aslDtl.splyItemNum, xxAslDtl.splyItemNum);
        ZYPEZDItemValueSetter.setValue(aslDtl.vndCd, xxAslDtl.vndCd);

        if (ZYPCommonFunc.hasValue(xxAslDtl.primSplyFlg)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.primSplyFlg, xxAslDtl.primSplyFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(aslDtl.primSplyFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(aslDtl.unitPrcAmt, xxAslDtl.unitPrcAmt);

        if (ZYPCommonFunc.hasValue(xxAslDtl.baseOrdQty)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.baseOrdQty, xxAslDtl.baseOrdQty);
        } else {
            ZYPEZDItemValueSetter.setValue(aslDtl.baseOrdQty, ONE);
        }

        if (ZYPCommonFunc.hasValue(xxAslDtl.vndUomQty)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndUomQty, xxAslDtl.vndUomQty);
        } else {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndUomQty, ONE);
        }

        if (ZYPCommonFunc.hasValue(xxAslDtl.vndIncrOrdQty)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndIncrOrdQty, xxAslDtl.vndIncrOrdQty);
        } else {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndIncrOrdQty, ONE);
        }

        if (ZYPCommonFunc.hasValue(xxAslDtl.vndMinOrdQty)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndMinOrdQty, xxAslDtl.vndMinOrdQty);
        } else {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndMinOrdQty, ONE);
        }
        // QC#21170 2018/04/11 Start
        BigDecimal vndLtDaysNum = xxAslDtl.vndLtDaysNum.getValue();
        if (!ZYPCommonFunc.hasValue(vndLtDaysNum) || 
             BigDecimal.ZERO.compareTo(vndLtDaysNum) > 0) {
            // null or minus value
            aslDtl.vndLtDaysNum.clear();
        } else {
            // 0 or plus value
            ZYPEZDItemValueSetter.setValue(aslDtl.vndLtDaysNum, vndLtDaysNum);
        }
        // START 2023/02/07 S.Dong [QC#60966, ADD]
        BigDecimal vndShipLtDaysNum = xxAslDtl.vndShipLtDaysNum.getValue();
        if (!ZYPCommonFunc.hasValue(vndShipLtDaysNum) || 
             BigDecimal.ZERO.compareTo(vndShipLtDaysNum) > 0) {
            // null or minus value
            aslDtl.vndShipLtDaysNum.clear();
        } else {
            // 0 or plus value
            ZYPEZDItemValueSetter.setValue(aslDtl.vndShipLtDaysNum, vndShipLtDaysNum);
        }
        // END 2023/02/07 S.Dong [QC#60966, ADD]
        // QC#21170 2018/04/11 End
        if (ZYPCommonFunc.hasValue(xxAslDtl.effFromDt)) {

            ZYPEZDItemValueSetter.setValue(aslDtl.effFromDt, xxAslDtl.effFromDt);

        } else {

            ZYPEZDItemValueSetter.setValue(aslDtl.effFromDt, procDt);
        }

        ZYPEZDItemValueSetter.setValue(aslDtl.effThruDt, xxAslDtl.effThruDt);

        ZYPEZDItemValueSetter.setValue(aslDtl.aslItemCmntTxt, xxAslDtl.aslItemCmntTxt);

        S21ApiTBLAccessor.create(aslDtl);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(aslDtl.getReturnCode())) {
            // Error
            xxAslDtl.xxMsgId.setValue(NPZM0252E);
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0252E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }

        return true;
    }

    /**
     * update ASL Detail.
     * @param pMsg NPZC115001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    private boolean updateAslDtl(BigDecimal aslHdrPk, NPZC115001_xxAslDtlListPMsg xxAslDtl, S21ApiMessageMap msgMap) {

        ASL_DTLTMsg aslDtl = new ASL_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(aslDtl.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aslDtl.aslHdrPk, aslHdrPk);
        ZYPEZDItemValueSetter.setValue(aslDtl.aslDtlPk, xxAslDtl.aslDtlPk);
        aslDtl = (ASL_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(aslDtl);
        if (aslDtl == null) {
            // Error
            xxAslDtl.xxMsgId.setValue(NPZM0252E);
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0252E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }

        ZYPEZDItemValueSetter.setValue(aslDtl.mdseCd, xxAslDtl.mdseCd);

        String coaMdseTpCd = getCoaMdseTpCd(xxAslDtl.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(aslDtl.coaMdseTpCd, coaMdseTpCd);

        if (ZYPCommonFunc.hasValue(xxAslDtl.vndUomCd)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndUomCd, xxAslDtl.vndUomCd);
        } else {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndUomCd, VND_UOM.EACH);
        }

        ZYPEZDItemValueSetter.setValue(aslDtl.splyItemNum, xxAslDtl.splyItemNum);
        ZYPEZDItemValueSetter.setValue(aslDtl.vndCd, xxAslDtl.vndCd);

        if (ZYPCommonFunc.hasValue(xxAslDtl.primSplyFlg)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.primSplyFlg, xxAslDtl.primSplyFlg);
        }

        ZYPEZDItemValueSetter.setValue(aslDtl.unitPrcAmt, xxAslDtl.unitPrcAmt);

        if (ZYPCommonFunc.hasValue(xxAslDtl.baseOrdQty)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.baseOrdQty, xxAslDtl.baseOrdQty);
        }

        if (ZYPCommonFunc.hasValue(xxAslDtl.vndUomQty)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndUomQty, xxAslDtl.vndUomQty);
        }

        if (ZYPCommonFunc.hasValue(xxAslDtl.vndIncrOrdQty)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndIncrOrdQty, xxAslDtl.vndIncrOrdQty);
        }

        if (ZYPCommonFunc.hasValue(xxAslDtl.vndMinOrdQty)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.vndMinOrdQty, xxAslDtl.vndMinOrdQty);
        }
        // QC#21170 2018/04/11 Start
        BigDecimal vndLtDaysNum = xxAslDtl.vndLtDaysNum.getValue();
        if (!ZYPCommonFunc.hasValue(vndLtDaysNum) || 
             BigDecimal.ZERO.compareTo(vndLtDaysNum) > 0) {
                // null or minus value
                aslDtl.vndLtDaysNum.clear();
        } else {
            // 0 or plus value
            ZYPEZDItemValueSetter.setValue(aslDtl.vndLtDaysNum, vndLtDaysNum);
        }
        // START 2023/02/07 S.Dong [QC#60966, ADD]
        BigDecimal vndShipLtDaysNum = xxAslDtl.vndShipLtDaysNum.getValue();
        if (!ZYPCommonFunc.hasValue(vndShipLtDaysNum) || 
             BigDecimal.ZERO.compareTo(vndShipLtDaysNum) > 0) {
            // null or minus value
            aslDtl.vndShipLtDaysNum.clear();
        } else {
            // 0 or plus value
            ZYPEZDItemValueSetter.setValue(aslDtl.vndShipLtDaysNum, vndShipLtDaysNum);
        }
        // END 2023/02/07 S.Dong [QC#60966, ADD]
        // QC#21170 2018/04/11 End
        if (ZYPCommonFunc.hasValue(xxAslDtl.effFromDt)) {
            ZYPEZDItemValueSetter.setValue(aslDtl.effFromDt, xxAslDtl.effFromDt);
        }
        ZYPEZDItemValueSetter.setValue(aslDtl.effThruDt, xxAslDtl.effThruDt);
        ZYPEZDItemValueSetter.setValue(aslDtl.aslItemCmntTxt, xxAslDtl.aslItemCmntTxt);

        S21ApiTBLAccessor.update(aslDtl);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(aslDtl.getReturnCode())) {
            // Error
            xxAslDtl.xxMsgId.setValue(NPZM0252E);
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0252E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }

        return true;
    }

    /**
     * create or update ASL Qualifier Relation List.
     * @param pMsg NPZC115001PMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    private boolean createOrUpdateAslQlfyRelnList(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        // logical remove

        if (!deleteAslQlfyReln(pMsg, msgMap)) {
            return false;
        }

        for (int i = 0; i < pMsg.xxAslQlfyRelnList.getValidCount(); i++) {

            BigDecimal aslHdrPk = pMsg.aslHdrPk.getValue();

            if (!ZYPCommonFunc.hasValue(pMsg.xxAslQlfyRelnList.no(i).aslQlfyRelnPk)) {
                if (!createAslQlfyReln(aslHdrPk, pMsg.xxAslQlfyRelnList.no(i), msgMap)) {
                    return false;
                }
            } else {
                if (!updateAslQlfyReln(aslHdrPk, pMsg.xxAslQlfyRelnList.no(i), msgMap)) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * create ASL Qualifier Relation.
     * @param aslHdrPk aslHdrPk
     * @param xxAslQlfyReln NPZC115001_xxAslQlfyRelnListPMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    private boolean createAslQlfyReln(BigDecimal aslHdrPk, NPZC115001_xxAslQlfyRelnListPMsg xxAslQlfyReln, S21ApiMessageMap msgMap) {

        ASL_QLFY_RELNTMsg aslQlfyReln = new ASL_QLFY_RELNTMsg();

        ZYPEZDItemValueSetter.setValue(aslQlfyReln.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslHdrPk, aslHdrPk);

        // Get PK
        BigDecimal aslQlfyRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal("ASL_QLFY_RELN_SQ");
        ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslQlfyRelnPk, aslQlfyRelnPk);

        ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslQlfyTpCd, xxAslQlfyReln.aslQlfyTpCd);
        ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslQlfyRefCd, xxAslQlfyReln.aslQlfyRefCd);

        S21ApiTBLAccessor.create(aslQlfyReln);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(aslQlfyReln.getReturnCode())) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0253E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }
        return true;
    }

    /**
     * update ASL Qualifier Relation.
     * @param aslHdrPk aslHdrPk
     * @param xxAslQlfyReln NPZC115001_xxAslQlfyRelnListPMsg
     * @param msgMap S21ApiMessageMap
     * @return true:create success / false:error
     */
    private boolean updateAslQlfyReln(BigDecimal aslHdrPk, NPZC115001_xxAslQlfyRelnListPMsg xxAslQlfyReln, S21ApiMessageMap msgMap) {

        ASL_QLFY_RELNTMsg aslQlfyReln = new ASL_QLFY_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(aslQlfyReln.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslHdrPk, aslHdrPk);
        ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslQlfyRelnPk, xxAslQlfyReln.aslQlfyRelnPk);
        aslQlfyReln = (ASL_QLFY_RELNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(aslQlfyReln);
        if (aslQlfyReln == null) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0253E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }

        ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslQlfyTpCd, xxAslQlfyReln.aslQlfyTpCd);
        ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslQlfyRefCd, xxAslQlfyReln.aslQlfyRefCd);

        S21ApiTBLAccessor.update(aslQlfyReln);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(aslQlfyReln.getReturnCode())) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0253E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }

        return true;
    }

    /**
     * get COA_MDSE_TP_CD.
     * @param mdseCd mdseCd
     * @return true:create success / false:error
     */
    private String getCoaMdseTpCd(String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        // Primary Key Search
        mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
        if (mdseTMsg == null) {
            return null;
        } else {
            return mdseTMsg.coaMdseTpCd.getValue();
        }
    }

    /**
     * checkCommonMandatoryItem.
     * @param pMsg
     * @param msgMap
     * @return true:OK false:NG
     */
    private boolean checkCommonMandatoryItem(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        boolean errFlg = true;

        // check xxModeCd
        if (!ZYPCommonFunc.hasValue(pMsg.xxModeCd)) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0001E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            errFlg = false;
        }
        // check glblCmpyCd
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0001E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            errFlg = false;
        }

        // check procDt
        if (!ZYPCommonFunc.hasValue(pMsg.procDt)) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0059E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            errFlg = false;
        }
        return errFlg;
    }

    /**
     * checkEntryMode.
     * @param pMsg
     * @param msgMap
     * @return true:OK false:NG
     */
    @SuppressWarnings("unchecked")
    private boolean checkEntryMode(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        boolean errFlg = true;

        // check ASL Name
        if (!ZYPCommonFunc.hasValue(pMsg.aslNm)) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0236E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            errFlg = false;
        }
        // check ASL Detail List
        if (pMsg.xxAslDtlList.getValidCount() == 0) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0237E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            errFlg = false;
        }

        // Check AslDtlList
        for (int i = 0; i < pMsg.xxAslDtlList.getValidCount(); i++) {
            // Check mdseCd
            if (!ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).mdseCd)) {
                // Error
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0238E);
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0238E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
            }

            // Check Vendor Code
            if (!ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).vndCd)) {
                // Error
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0079E);
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0079E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
            }

            // Check Unit Price Amount
            if (!ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).unitPrcAmt)) {
                // Error
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0239E);
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0239E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
            }

            // Check Base PO Qty
            if (ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).baseOrdQty)) {
                if (BigDecimal.ZERO.compareTo(pMsg.xxAslDtlList.no(i).baseOrdQty.getValue()) >= 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0240E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0240E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    errFlg = false;
                }
            }

            // Check Vendor UOM Qty
            if (ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).vndUomQty)) {
                if (BigDecimal.ZERO.compareTo(pMsg.xxAslDtlList.no(i).vndUomQty.getValue()) >= 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0241E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0241E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    errFlg = false;
                }
            }

            // Check Vendor Incr Ord Qty
            if (ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).vndIncrOrdQty)) {
                if (BigDecimal.ZERO.compareTo(pMsg.xxAslDtlList.no(i).vndIncrOrdQty.getValue()) >= 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0242E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0242E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    errFlg = false;
                }
            }

            // Check Vendor Min Ord Qty
            if (ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).vndMinOrdQty)) {
                if (BigDecimal.ZERO.compareTo(pMsg.xxAslDtlList.no(i).vndMinOrdQty.getValue()) >= 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0243E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0243E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    errFlg = false;
                }
            }
        }

        // Check Parent Vendor Code
        List<Map> prntVndCdList = getPrntVndCd(pMsg);

        if (prntVndCdList == null) {
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0244E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            return false;
        }

        String prntVndCd = "";
        if (ZYPCommonFunc.hasValue(pMsg.prntVndCd)) {
            prntVndCd = pMsg.prntVndCd.getValue();
        }

        for (Map m : prntVndCdList) {
            String prntVndCdNxt = (String) m.get("PRNT_VND_CD");
            if (!prntVndCd.equals(prntVndCdNxt)) {
                // Error
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0245E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
                break;
            } else {
                prntVndCd = prntVndCdNxt;
            }
        }

        if (!ZYPCommonFunc.hasValue(pMsg.prntVndCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, prntVndCd);
        }

        // Check AslQlfyRelnList
        for (int i = 0; i < pMsg.xxAslQlfyRelnList.getValidCount(); i++) {
            // Check ASL Qualifier Type Code
            if (!ZYPCommonFunc.hasValue(pMsg.xxAslQlfyRelnList.no(i).aslQlfyTpCd)) {
                // Error
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0246E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
            }
            // Check ASL Qualifier Reference Code
            if (!ZYPCommonFunc.hasValue(pMsg.xxAslQlfyRelnList.no(i).aslQlfyRefCd)) {
                // Error
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0247E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
            }
        }
        return errFlg;
    }

    /**
     * checkCostUpdateMode.
     * @param pMsg
     * @param msgMap
     * @return true:OK false:NG
     */
    private boolean checkCostUpdateMode(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        boolean errFlg = true;

        // check ASL Detail List
        if (pMsg.xxAslDtlList.getValidCount() == 0) {
            // Error
            // START 2017/06/21 S.Katsuma [QC#13782]
            addUniqueXxMsgId(msgMap, NPZM0237E);
            // END 2017/06/21 S.Katsuma [QC#13782]
            errFlg = false;
        }

        for (int i = 0; i < pMsg.xxAslDtlList.getValidCount(); i++) {
            // Check Supplier Item Number
            if (!ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).splyItemNum)) {
                // Error
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0248E);
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0248E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
            }

            // Check Vendor Code
            if (!ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).vndCd)) {
                // Error
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0079E);
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0079E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
            }

            // Check Effective From Date
            if (!ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).effFromDt) && ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).effThruDt)) {
                // Error
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0249E);
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0249E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
            }

            // Check Unit Price Amount
            if (!ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).unitPrcAmt)) {
                // Error
                ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0239E);
                // START 2017/06/21 S.Katsuma [QC#13782]
                addUniqueXxMsgId(msgMap, NPZM0239E);
                // END 2017/06/21 S.Katsuma [QC#13782]
                errFlg = false;
            }

            // Check Base PO Qty
            if (ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).baseOrdQty)) {
                if (BigDecimal.ZERO.compareTo(pMsg.xxAslDtlList.no(i).baseOrdQty.getValue()) >= 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0240E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0240E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    errFlg = false;
                }
            }

            // Check Vendor UOM Qty
            if (ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).vndUomQty)) {
                if (BigDecimal.ZERO.compareTo(pMsg.xxAslDtlList.no(i).vndUomQty.getValue()) >= 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0241E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0241E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    errFlg = false;
                }
            }

            // Check Vendor Incr Ord Qty
            if (ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).vndIncrOrdQty)) {
                if (BigDecimal.ZERO.compareTo(pMsg.xxAslDtlList.no(i).vndIncrOrdQty.getValue()) >= 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0242E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0242E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    errFlg = false;
                }
            }

            // Check Vendor Min Ord Qty
            if (ZYPCommonFunc.hasValue(pMsg.xxAslDtlList.no(i).vndMinOrdQty)) {
                if (BigDecimal.ZERO.compareTo(pMsg.xxAslDtlList.no(i).vndMinOrdQty.getValue()) >= 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg.xxAslDtlList.no(i).xxMsgId, NPZM0243E);
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0243E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    errFlg = false;
                }
            }
        }
        return errFlg;
    }

    /**
     * getPrntVndCd.
     * @param pMsg NPZC115001PMsg
     * @return PRNT_VND_CD or Null
     */
    @SuppressWarnings("unchecked")
    private List<Map> getPrntVndCd(NPZC115001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ArrayList<String> vndCdList = new ArrayList<String>();
        for (int i = 0; i < pMsg.xxAslDtlList.getValidCount(); i++) {
            vndCdList.add(pMsg.xxAslDtlList.no(i).vndCd.getValue());
        }
        param.put("vndCdList", vndCdList);

        return (List<Map>) this.ssmBatchClient.queryObjectList("getPrntVndCd", param);
    }

    /**
     * getAslEndDt.
     * @param pMsg NPZC115001PMsg
     * @return ASL_END_DT or Null
     */
    @SuppressWarnings("unchecked")
    private Map getAslEndDt(NPZC115001PMsg pMsg) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("aslHdrPk", pMsg.aslHdrPk.getValue());

        return (Map) this.ssmBatchClient.queryObject("getAslEndDt", param);
    }

    /**
     * @param pMsg
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean deleteAslQlfyReln(NPZC115001PMsg pMsg, S21ApiMessageMap msgMap) {

        ArrayList<BigDecimal> aslQlfyRelnPkList = new ArrayList<BigDecimal>();

        for (int i = 0; i < pMsg.xxAslQlfyRelnList.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(pMsg.xxAslQlfyRelnList.no(i).aslQlfyRelnPk.getValue())) {

                aslQlfyRelnPkList.add(pMsg.xxAslQlfyRelnList.no(i).aslQlfyRelnPk.getValue());

            }
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("aslHdrPk", pMsg.aslHdrPk.getValue());
        param.put("aslQlfyRelnPkList", aslQlfyRelnPkList);

        List<BigDecimal> qlfyRelnDeleteList = this.ssmBatchClient.queryObjectList("getQlfyRelnList", param);

        if (qlfyRelnDeleteList.size() > 0) {

            for (int i = 0; i < qlfyRelnDeleteList.size(); i++) {

                BigDecimal aslQlfyRelnPk = qlfyRelnDeleteList.get(i);

                ASL_QLFY_RELNTMsg aslQlfyReln = new ASL_QLFY_RELNTMsg();

                ZYPEZDItemValueSetter.setValue(aslQlfyReln.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslHdrPk, pMsg.aslHdrPk.getValue());
                ZYPEZDItemValueSetter.setValue(aslQlfyReln.aslQlfyRelnPk, aslQlfyRelnPk);
                aslQlfyReln = (ASL_QLFY_RELNTMsg) S21ApiTBLAccessor.findByKeyForUpdate(aslQlfyReln);

                if (aslQlfyReln == null) {
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0253E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    return false;
                }

                S21ApiTBLAccessor.logicalRemove(aslQlfyReln);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(aslQlfyReln.getReturnCode())) {
                    // START 2017/06/21 S.Katsuma [QC#13782]
                    addUniqueXxMsgId(msgMap, NPZM0253E);
                    // END 2017/06/21 S.Katsuma [QC#13782]
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * getAslDtlInfo.
     * @param pMsg NPZC115001PMsg
     * @return aslDtlInfoList or Null
     */
    @SuppressWarnings("unchecked")
    private List<Map> getAslDtlInfo(NPZC115001PMsg pMsg, int index) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("procDt", procDt);

        param.put("prntVndCd", pMsg.prntVndCd.getValue());
        param.put("vndCd", pMsg.xxAslDtlList.no(index).vndCd.getValue());
        param.put("splyItemNum", pMsg.xxAslDtlList.no(index).splyItemNum.getValue());

        param.put("aslQlfyTpCd", ASL_QLFY_TP.BIG_DEAL_SPECIFIC);

        ArrayList<String> aslQlfyRefCdList = new ArrayList<String>();
        for (int i = 0; i < pMsg.xxAslQlfyRelnList.getValidCount(); i++) {
            aslQlfyRefCdList.add(pMsg.xxAslQlfyRelnList.no(i).aslQlfyRefCd.getValue());
        }
        param.put("aslQlfyRefCdList", aslQlfyRefCdList);

        if (aslQlfyRefCdList.size() > 0) {
            param.put("isAslQlfyRefFlg", ZYPConstant.FLG_ON_Y);
        } else {
            param.put("isAslQlfyRefFlg", ZYPConstant.FLG_OFF_N);
        }

        return (List<Map>) this.ssmBatchClient.queryObjectList("getAslDtlInfo", param);
    }

    // START 2017/06/21 S.Katsuma [QC#13782]
    /**
     * addUniqueXxMsgId
     * @param msgMap
     * @param msgId
     */
    private void addUniqueXxMsgId(S21ApiMessageMap msgMap, String msgId) {
        if (msgMap != null) {
            if (!msgMap.getMsgMgr().hasXxMsgId(msgId)) {
                msgMap.addXxMsgId(msgId);
            }
        }
    }
    // END 2017/06/21 S.Katsuma [QC#13782]

    /**
     * getFutureDay.Add QC#56059
     * @param pMsg NPZC115001PMsg
     * @return aslDtlInfoList or Null
     */
    @SuppressWarnings("unchecked")
    private List<Map> getFutureDay(NPZC115001PMsg pMsg, int index, String date) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("procDt", date);

        param.put("prntVndCd", pMsg.prntVndCd.getValue());
        param.put("vndCd", pMsg.xxAslDtlList.no(index).vndCd.getValue());
        param.put("splyItemNum", pMsg.xxAslDtlList.no(index).splyItemNum.getValue());

        param.put("aslQlfyTpCd", ASL_QLFY_TP.BIG_DEAL_SPECIFIC);

        ArrayList<String> aslQlfyRefCdList = new ArrayList<String>();
        for (int i = 0; i < pMsg.xxAslQlfyRelnList.getValidCount(); i++) {
            aslQlfyRefCdList.add(pMsg.xxAslQlfyRelnList.no(i).aslQlfyRefCd.getValue());
        }
        param.put("aslQlfyRefCdList", aslQlfyRefCdList);

        if (aslQlfyRefCdList.size() > 0) {
            param.put("isAslQlfyRefFlg", ZYPConstant.FLG_ON_Y);
        } else {
            param.put("isAslQlfyRefFlg", ZYPConstant.FLG_OFF_N);
        }

        return (List<Map>) this.ssmBatchClient.queryObjectList("getAslDtlInfo", param);
    }
}
