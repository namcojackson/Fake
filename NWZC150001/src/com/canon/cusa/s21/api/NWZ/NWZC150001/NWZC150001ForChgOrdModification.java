/**<pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.ATTACH_BUSINESS_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.ATTACH_DATA_ORD_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.ATTACH_DOC_TP_ORDER;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.BIZ_APP_ID_ORDER_ENTRY;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0073E;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_RECTMsg;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_CPO_CTAC_PSNTMsgArray;
import business.db.DS_CPO_DELY_INFOTMsg;
import business.db.DS_CPO_DELY_INFOTMsgArray;
import business.db.DS_CPO_ISTL_INFOTMsg;
import business.db.DS_CPO_ISTL_INFOTMsgArray;
import business.db.DS_CPO_SLS_CRTMsg;
import business.db.DS_CPO_SLS_CRTMsgArray;
import business.db.DS_CPO_SLS_CR_RECTMsg;
import business.db.DS_SITE_SRVYTMsg;
import business.db.DS_SITE_SRVYTMsgArray;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Fujitsu         T.Ogura         Create          N/A
 * 2018/01/16   Fujitsu         S.Takami        Update          S21_NA#23498
 * </pre>
 */

public class NWZC150001ForChgOrdModification extends S21ApiCommonBase {

    /** Message ID Manager */
    private S21ApiMessageIdMgr msgIdMgr = null;

    /** SSM Client */
    private final S21SsmBatchClient ssmClient;

    /** Sub System ID : NWZ */
    private static final String SUB_SYS_ID_NWZ = "NWZ";

    /** Process ID : OM */
    private static final String PROC_ID_OM = "OM";

    /** Document Type : OM */
    private static final String DOC_TP_OM = "OM";

    /** Event ID for Business Process Log : Change Order Modification */
    private static final String EVENT_ID_CHNG_ORD_MODIFY = "Change Order Modification";

    /**
     * Constructor for NWZC150001ForChgOrdModification
     */
    public NWZC150001ForChgOrdModification() {
        super();
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * DS CPO Update API.(Change Order Modification)
     * @param inPMsg        NWZC150001PMsg
     * @param resPMsg2List  List<NWZC150002PMsg>
     * @param resPMsg3List  List<NWZC150003PMsg>
     * @param prmOnBatchType ONBATCH_TYPE
     * </pre>
     */
    public void execute(final NWZC150001PMsg inPMsg //
            , final List<NWZC150002PMsg> resPMsg2List //
            , final List<NWZC150003PMsg> resPMsg3List //
            , final ONBATCH_TYPE prmOnBatchType) {

        NWZC150001PMsg pMsg = new NWZC150001PMsg();

        try {
            EZDMsg.copy(inPMsg, null, pMsg, null);

            this.msgIdMgr = new S21ApiMessageIdMgr();

            doProcess(pMsg, resPMsg2List, resPMsg3List);

        } finally {
            inPMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount());
            EZDMsg.copy(pMsg, null, inPMsg, null);
            super.updateMessage(inPMsg, this.msgIdMgr);
        }
    }

    private void doProcess(NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> resPMsg2List //
            , List<NWZC150003PMsg> resPMsg3List) {

        boolean resultFlg = false;
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String cpoOrdNum = pMsg.cpoOrdNum.getValue();

        CPOTMsg cpoTMsg = getCpo(glblCmpyCd, cpoOrdNum);
        if (cpoTMsg == null) {
            this.msgIdMgr.addXxMsgId(NWZM0073E, pMsg);
            return;
        }

        DS_CPO_SLS_CRTMsgArray dsCposlsCrAry = getCposlsCrAryHdr(glblCmpyCd, cpoOrdNum);

        DS_CPO_ISTL_INFOTMsgArray dsCpoIstlInfoAry = getdsCpoIstlInfoHdr(glblCmpyCd, cpoOrdNum);

        DS_CPO_DELY_INFOTMsgArray dsCpoDelyInfoAry = getdsCpoDelyInfoHdr(glblCmpyCd, cpoOrdNum);

        DS_CPO_CTAC_PSNTMsgArray dsCpoCtacPsnAry = getDsCpoCtacPsn(glblCmpyCd, cpoOrdNum);

        DS_SITE_SRVYTMsgArray dsSiteSrvyTMsgAry = getDsSiteSrvyTMsgAry(glblCmpyCd, cpoOrdNum);

        final String newCpoOrdNum = ZYPMaxTenDigitsNumbering.getUniqueID("CPO_ORD_NUM");

        resultFlg = registerCpoForChngOrdModify(pMsg, newCpoOrdNum, cpoTMsg);
        if (resultFlg) {
            return;
        }
        resultFlg = registerDsCposlsCrForChngOrdModify(pMsg, newCpoOrdNum, dsCposlsCrAry);
        if (resultFlg) {
            return;
        }
        resultFlg = registerDsCpoIstlInfoForChngOrdModify(pMsg, newCpoOrdNum, dsCpoIstlInfoAry);
        if (resultFlg) {
            return;
        }
        resultFlg = registerDsCpoDelyInfoForChngOrdModify(pMsg, newCpoOrdNum, dsCpoDelyInfoAry);
        if (resultFlg) {
            return;
        }
        resultFlg = registerDsCpoCtacPsnForChngOrdModify(pMsg, newCpoOrdNum, dsCpoCtacPsnAry);
        if (resultFlg) {
            return;
        }
        resultFlg = registerDsSiteSrvyForChngOrdModify(pMsg, newCpoOrdNum, dsSiteSrvyTMsgAry);
        if (resultFlg) {
            return;
        }

        registAttachComment(pMsg, newCpoOrdNum);

        S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
        bizLogMsg.setSubSysId(SUB_SYS_ID_NWZ);
        bizLogMsg.setProcId(PROC_ID_OM);
        bizLogMsg.setDocTpCd(DOC_TP_OM);
        bizLogMsg.setEventId(EVENT_ID_CHNG_ORD_MODIFY);
        bizLogMsg.setDocId("");
        bizLogMsg.setPrntDocId(newCpoOrdNum);
        bizLogMsg.setBizProcCmntTxt_01("");
        bizLogMsg.setBizProcCmntTxt_02("");

        S21BusinessProcessLog.print(bizLogMsg);

        // get CPO
        cpoTMsg = getCpo(pMsg.glblCmpyCd.getValue(), newCpoOrdNum);
        dsCposlsCrAry = getCposlsCrAryHdr(glblCmpyCd, newCpoOrdNum);

        BigDecimal bizProcLogPK = getBizProcLogPK(pMsg.glblCmpyCd.getValue(), cpoTMsg, EVENT_ID_CHNG_ORD_MODIFY);
        if (bizProcLogPK != null) {

            CPO_RECTMsg cpoRecTMsg = new CPO_RECTMsg();
            EZDMsg.copy(cpoTMsg, null, cpoRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(cpoRecTMsg.bizProcLogPk, bizProcLogPK);

            S21FastTBLAccessor.create(cpoRecTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
                throw new S21AbendException("CPO_REC : Insert Error. BIZ_PROC_LOG_PK=[" + bizProcLogPK.toString() + "]");
            }

            DS_CPO_SLS_CR_RECTMsg dsCpoSlsCrRecTMsg = new DS_CPO_SLS_CR_RECTMsg();
            if (dsCposlsCrAry != null) {
                for (int i = 0; i < dsCposlsCrAry.getValidCount(); i++) {
                    DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg = dsCposlsCrAry.no(i);
                    EZDMsg.copy(dsCpoSlsCrTMsg, null, dsCpoSlsCrRecTMsg, null);
                    ZYPEZDItemValueSetter.setValue(dsCpoSlsCrRecTMsg.bizProcLogPk, bizProcLogPK);

                    S21FastTBLAccessor.insert(dsCpoSlsCrRecTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.endsWith(dsCpoSlsCrRecTMsg.getReturnCode())) {
                        throw new S21AbendException("DS_CPO_SLS_CR_REC : Insert Error. BIZ_PROC_LOG_PK=[" + bizProcLogPK.toString() + "]");
                    }
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, newCpoOrdNum);
    }

    /**
     * getCpo
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @return  CPOTMsg
     */
    private CPOTMsg getCpo(String glblCmpyCd, String cpoOrdNum) {
        CPOTMsg inTMsg = new CPOTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inTMsg.cpoOrdNum.setValue(cpoOrdNum);
        CPOTMsg outTMsg = (CPOTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    /**
     * getCposlsCrAryHdr
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @return  DS_CPO_SLS_CRTMsgArray
     */
    private DS_CPO_SLS_CRTMsgArray getCposlsCrAryHdr(String glblCmpyCd, String cpoOrdNum) {
        DS_CPO_SLS_CRTMsg inTMsg = new DS_CPO_SLS_CRTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        DS_CPO_SLS_CRTMsgArray array = (DS_CPO_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array;
    }

    /**
     * getdsCpoIstlInfoHdr
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @return  DS_CPO_ISTL_INFOTMsgArray
     */
    private DS_CPO_ISTL_INFOTMsgArray getdsCpoIstlInfoHdr(String glblCmpyCd, String cpoOrdNum) {
        DS_CPO_ISTL_INFOTMsg inTMsg = new DS_CPO_ISTL_INFOTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        DS_CPO_ISTL_INFOTMsgArray array = (DS_CPO_ISTL_INFOTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array;
    }

    /**
     * getdsCpoDelyInfoHdr
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @return  DS_CPO_DELY_INFOTMsgArray
     */
    private DS_CPO_DELY_INFOTMsgArray getdsCpoDelyInfoHdr(String glblCmpyCd, String cpoOrdNum) {
        DS_CPO_DELY_INFOTMsg inTMsg = new DS_CPO_DELY_INFOTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        DS_CPO_DELY_INFOTMsgArray array = (DS_CPO_DELY_INFOTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array;
    }

    /**
     * getDsCpoCtacPsn
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @return  DS_CPO_CTAC_PSNTMsgArray
     */
    private DS_CPO_CTAC_PSNTMsgArray getDsCpoCtacPsn(String glblCmpyCd, String cpoOrdNum) {
        DS_CPO_CTAC_PSNTMsg inTMsg = new DS_CPO_CTAC_PSNTMsg();
        inTMsg.setSQLID("009");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        DS_CPO_CTAC_PSNTMsgArray array = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array;
    }

    /**
     * getDsSiteSrvyTMsgAry
     * @param glblCmpyCd    String
     * @param cpoOrdNum     String
     * @return  DS_SITE_SRVYTMsgArray
     */
    private DS_SITE_SRVYTMsgArray getDsSiteSrvyTMsgAry(String glblCmpyCd, String cpoOrdNum) {
        DS_SITE_SRVYTMsg inTMsg = new DS_SITE_SRVYTMsg();
        inTMsg.setSQLID("005");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        DS_SITE_SRVYTMsgArray array = (DS_SITE_SRVYTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (array == null || array.length() == 0) {
            return null;
        }
        return array;
    }

    /**
     * registerCpoForChngOrdModify
     * @param pMsg      NWZC150001PMsg
     * @param cpoOrdNum String
     * @param cpoTMsg   CPOTMsg
     * @return  boolean
     */
    private boolean registerCpoForChngOrdModify(NWZC150001PMsg pMsg, String cpoOrdNum, CPOTMsg cpoTMsg) {
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdTs, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrStsCd, ORD_HDR_STS.SAVED);
        // 2018/01/16 S21_NA#23498 Add Start
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordHdrDplyStsCd, ORD_HDR_DPLY_STS.ENTERED);
        // 2018/01/16 S21_NA#23498 Add End
        ZYPEZDItemValueSetter.setValue(cpoTMsg.adminPsnCd, pMsg.usrId.getValue());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotDealNetAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.entCpoTotFuncNetAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotDealNetAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncSlsAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoTotFuncNetAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.addRddDt, pMsg.slsDt.getValue());
        cpoTMsg.addRsdDt.clear();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoCancFlg, ZYPConstant.FLG_OFF_N);
        cpoTMsg.cpoCancDt.clear();
        cpoTMsg.origOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.submtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.openFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoSrcTpCd, CPO_SRC_TP.CHANGE_ORDER_MODIFICATION);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.shpgChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.orgRqstDelyDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.prcBaseDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.prcCalcDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.negoDealAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordSrcImptDt, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordSrcRefNum, pMsg.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(cpoTMsg.diChkHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.wfRejFlg, ZYPConstant.FLG_OFF_N);
        cpoTMsg.ordBookTs.clear();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.ordBookFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoUpdVrsnNum, BigDecimal.ONE);
        cpoTMsg.prePmtChkNum.clear();
        cpoTMsg.prePmtAmt.clear();
        cpoTMsg.prePmtTpCd.clear();
        S21FastTBLAccessor.insert(cpoTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
            throw new S21AbendException("CPO : Insert Error. CPO_ORD_NUM=[" + cpoOrdNum + "]");
        }
        return false;
    }

    /**
     * registerDsCposlsCrForChngOrdModify
     * @param pMsg          NWZC150001PMsg
     * @param cpoOrdNum     String
     * @param dsCposlsCrAry DS_CPO_SLS_CRTMsgArray
     * @return  boolean
     */
    private boolean registerDsCposlsCrForChngOrdModify(NWZC150001PMsg pMsg, String cpoOrdNum, DS_CPO_SLS_CRTMsgArray dsCposlsCrAry) {
        if (dsCposlsCrAry == null) {
            return false;
        }
        DS_CPO_SLS_CRTMsg inTMsg;
        for (int i = 0; i < dsCposlsCrAry.getValidCount(); i++) {
            inTMsg = dsCposlsCrAry.no(i);
            ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoSlsCrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_SLS_CR_SQ));
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
            S21FastTBLAccessor.insert(inTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                throw new S21AbendException("DS_CPO_SLS_CR : Insert Error. CPO_ORD_NUM=[" + cpoOrdNum + "]");
            }
        }
        return false;
    }

    /**
     * registerDsCpoIstlInfoForChngOrdModify
     * @param pMsg          NWZC150001PMsg
     * @param cpoOrdNum     String
     * @param dsCpoIstlInfoAry  DS_CPO_ISTL_INFOTMsgArray
     * @return  boolean
     */
    private boolean registerDsCpoIstlInfoForChngOrdModify(NWZC150001PMsg pMsg, String cpoOrdNum, DS_CPO_ISTL_INFOTMsgArray dsCpoIstlInfoAry) {
        if (dsCpoIstlInfoAry == null) {
            return false;
        }
        DS_CPO_ISTL_INFOTMsg inTMsg;
        for (int i = 0; i < dsCpoIstlInfoAry.getValidCount(); i++) {
            inTMsg = dsCpoIstlInfoAry.no(i);
            ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoIstlInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_ISTL_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
            S21FastTBLAccessor.insert(inTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                throw new S21AbendException("DS_CPO_ISTL_INFO : Insert Error. CPO_ORD_NUM=[" + cpoOrdNum + "]");
            }
        }
        return false;
    }

    /**
     * registerDsCpoDelyInfoForChngOrdModify
     * @param pMsg          NWZC150001PMsg
     * @param cpoOrdNum     String
     * @param dsCpoDelyInfoAry  DS_CPO_DELY_INFOTMsgArray
     * @return  boolean
     */
    private boolean registerDsCpoDelyInfoForChngOrdModify(NWZC150001PMsg pMsg, String cpoOrdNum, DS_CPO_DELY_INFOTMsgArray dsCpoDelyInfoAry) {
        if (dsCpoDelyInfoAry == null) {
            return false;
        }
        DS_CPO_DELY_INFOTMsg inTMsg;
        for (int i = 0; i < dsCpoDelyInfoAry.getValidCount(); i++) {
            inTMsg = dsCpoDelyInfoAry.no(i);
            ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoDelyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DELY_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(inTMsg.rddDt, pMsg.slsDt.getValue());
            S21FastTBLAccessor.insert(inTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                throw new S21AbendException("DS_CPO_DELY_INFO : Insert Error. CPO_ORD_NUM=[" + cpoOrdNum + "]");
            }
        }
        return false;
    }

    /**
     * registerDsCpoCtacPsnForChngOrdModify
     * @param pMsg      NWZC150001PMsg
     * @param cpoOrdNum String
     * @param dsCpoCtacPsnAry   DS_CPO_CTAC_PSNTMsgArray
     * @return  boolean
     */
    private boolean registerDsCpoCtacPsnForChngOrdModify(NWZC150001PMsg pMsg, String cpoOrdNum, DS_CPO_CTAC_PSNTMsgArray dsCpoCtacPsnAry) {
        if (dsCpoCtacPsnAry == null) {
            return false;
        }
        DS_CPO_CTAC_PSNTMsg inTMsg;
        for (int i = 0; i < dsCpoCtacPsnAry.getValidCount(); i++) {
            inTMsg = dsCpoCtacPsnAry.no(i);
            ZYPEZDItemValueSetter.setValue(inTMsg.dsCpoCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CTAC_PSN_SQ));
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
            S21FastTBLAccessor.insert(inTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                throw new S21AbendException("DS_CPO_CTAC_PSN : Insert Error. CPO_ORD_NUM=[" + cpoOrdNum + "]");
            }
        }
        return false;
    }

    /**
     * Register DS Site Survey For Change Order Modify.
     * @param pMsg NWZC150001PMsg
     * @param cpoOrdNum String
     * @param dsSiteSrvyTMsgAry DS_SITE_SRVYTMsgArray
     * @return boolean
     */
    private boolean registerDsSiteSrvyForChngOrdModify(NWZC150001PMsg pMsg, String cpoOrdNum, DS_SITE_SRVYTMsgArray dsSiteSrvyTMsgAry) {
        if (dsSiteSrvyTMsgAry == null) {
            return false;
        }
        DS_SITE_SRVYTMsg inTMsg;
        for (int i = 0; i < dsSiteSrvyTMsgAry.getValidCount(); i++) {
            inTMsg = dsSiteSrvyTMsgAry.no(i);
            ZYPEZDItemValueSetter.setValue(inTMsg.dsSiteSrvyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SITE_SRVY_SQ));
            ZYPEZDItemValueSetter.setValue(inTMsg.cpoOrdNum, cpoOrdNum);
            S21FastTBLAccessor.insert(inTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                throw new S21AbendException("DS_SITE_SRVY : Insert Error. CPO_ORD_NUM=[" + cpoOrdNum + "]");
            }
        }
        return false;
    }

    /**
     * registAttachComment
     * @param pMsg NWZC150001PMsg
     * @param newCpoOrdNum String
     */
    private void registAttachComment(NWZC150001PMsg pMsg, String newCpoOrdNum) {
        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
        params.setBusinessId(BIZ_APP_ID_ORDER_ENTRY);
        params.setAttDataGrp(pMsg.cpoOrdNum.getValue());
        params.setAttDataCmnt(pMsg.xxBizProcCmntTxt.getValue());
        params.setBusinessNm(ATTACH_BUSINESS_NM);
        params.setAttDataNm(ATTACH_DATA_ORD_NUM);
        params.setAttDocTpCd(ATTACH_DOC_TP_ORDER);
        ZYPFileUpDown.uploadNote(params);
        params.setAttDataGrp(newCpoOrdNum);
        ZYPFileUpDown.uploadNote(params);
    }

    /**
     * getBizProcLogPK
     * @param glblCmpyCd String
     * @param cpoTMsg CPOTMsg
     * @param eventId String
     * @return boolean
     */
    private BigDecimal getBizProcLogPK(String glblCmpyCd, CPOTMsg cpoTMsg, String eventId){
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("subSysID", SUB_SYS_ID_NWZ);
        queryParam.put("procId", PROC_ID_OM);
        queryParam.put("eventId", eventId);
        queryParam.put("docTpCd", DOC_TP_OM);
        queryParam.put("prntDocId", cpoTMsg.cpoOrdNum.getValue());
        queryParam.put("ezUpUsrId", cpoTMsg.ezUpUserID.getValue());
        queryParam.put("ezUpTimeZone", cpoTMsg.ezUpTimeZone.getValue());
        queryParam.put("ezUpTime", cpoTMsg.ezUpTime.getValue());
        return (BigDecimal)this.ssmClient.queryObject("getBizProcLogPK", queryParam);
    }
}
