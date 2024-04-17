/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VLD_ACT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmEZDClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2016/02/18   Hitachi         A.Kohinata      Update          QC4252
 * 2017/04/25   Hitachi         Y.Takeno        Update          RS#7239
 * 2017/07/12   CITS            M.Naito         Update          QC#18585
 * 2017/07/26   Hitachi         K.Kasai         Update          QC#18882
 * 2018/02/02   Hitachi         U.Kim           Update          QC#23687
 * 2018/02/14   Hitachi         K.Kojima        Update          QC#24145
 * 2018/03/15   Hitachi         K.Kojima        Update          QC#22611
 * 2018/08/03   Hitachi         K.Kim           Update          QC#14307(Sol#020)
 * </pre>
 */
public class NSZC057001 extends S21ApiCommonBase {

    protected S21LRUMap<String, DS_CONTRTMsg> dsContrCache = new S21LRUMap<String, DS_CONTRTMsg>();

    protected S21LRUMap<String, DS_CONTR_DTLTMsgArray> dsContrDtlArrayCache = new S21LRUMap<String, DS_CONTR_DTLTMsgArray>();

    protected S21LRUMap<String, DS_CONTR_BLLG_MTRTMsgArray> dsContrBllgMtrArrayCache = new S21LRUMap<String, DS_CONTR_BLLG_MTRTMsgArray>();

    protected S21LRUMap<String, CONTR_XS_COPYTMsgArray> contrXsCopyArrayCache = new S21LRUMap<String, CONTR_XS_COPYTMsgArray>();

    // START 2018/02/02 U.Kim [QC#23687,ADD]
    protected S21LRUMap<BigDecimal, Boolean> isLeaseCompanyCache = new S21LRUMap<BigDecimal, Boolean>();

    protected S21LRUMap<String, Boolean> isLeaseCompanyForBllgMtrCache = new S21LRUMap<String, Boolean>();

    protected S21LRUMap<BigDecimal, Map<String, Object>> machAndContrInfoCache = new S21LRUMap<BigDecimal, Map<String,Object>>();
    // END 2018/02/02 U.Kim [QC#23687,ADD]

    // START 2018/02/14 K.Kojima [QC#24145,ADD]
    protected S21LRUMap<BigDecimal, Boolean> existCfsLeaseContr = new S21LRUMap<BigDecimal, Boolean>();

    protected S21LRUMap<String, Boolean> existCfsLeaseContrForSerial = new S21LRUMap<String, Boolean>();

    // END 2018/02/14 K.Kojima [QC#24145,ADD]

    // START 2018/08/03 K.Kim [QC#14307(Sol#020), ADD]
    protected S21LRUMap<String, Boolean> existCustSpclInstruction = new S21LRUMap<String, Boolean>();
    // END 2018/08/03 K.Kim [QC#14307(Sol#020), ADD]

    /** S21SsmBatchClient */
    protected S21SsmBatchClient ssmBatchClient = null;

    /** S21SsmEZDClient */
    protected S21SsmEZDClient ssmEzdClient = null;

    /** Online Batch Type */
    protected ONBATCH_TYPE onBatchType = null;

    /** Validation Check List Index */
    private int vldChkListIdx = 0;

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NSZC057001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmEzdClient = S21SsmEZDClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Main routine of Contract Validation
     * </pre>
     * @param param Input parameter
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(NSZC057001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        this.onBatchType = onBatchType;

        // parameter check
        if (!checkParam(msgMap)) {
            msgMap.flush();
            return;
        }

        // get contract info
        DS_CONTRTMsg dsContrTMsg = getDsContrHdr(param.glblCmpyCd.getValue(), param.dsContrNum.getValue());
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = getDsContrDtl(param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());

        if (dsContrTMsg == null || dsContrDtlTMsgArray == null || dsContrDtlTMsgArray.getValidCount() == 0) {
            msgMap.addXxMsgIdWithPrm(NSZM0763E, new String[] {param.dsContrNum.getValue() });
            msgMap.flush();
            return;
        }

        // START 2017/04/25 [RS#7239, ADD]
        if (DS_CONTR_STS.ORDER.equals(dsContrTMsg.dsContrStsCd.getValue())) {
            msgMap.addXxMsgIdWithPrm(NSZM0763E, new String[] {param.dsContrNum.getValue() });
            msgMap.flush();
            return;
        }
        // END   2017/04/25 [RS#7239, ADD]

        // set cache
        this.dsContrCache.put(KEY, dsContrTMsg);
        this.dsContrDtlArrayCache.put(KEY, dsContrDtlTMsgArray);

        // set default PMsg
        createDefaultOutPMsg(param);

        // get contract validation info
        List<Map<String, Object>> dsContrVldInfoList = getDsContrVldInfo(param.glblCmpyCd.getValue(), param.slsDt.getValue(), dsContrTMsg.dsContrCatgCd.getValue());
        if (dsContrVldInfoList == null || dsContrVldInfoList.isEmpty() || dsContrVldInfoList.size() == 0) {
            msgMap.addXxMsgId(NSZM0764E);
            msgMap.flush();
            return;
        }

        // delete work table
        deleteDsContrVldRsltWrk(param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());

        // START 2016/02/18 A.Kohinata [QC4252, MOD]
        // Contract Level Primary Validation
        vldCheck(param, dsContrVldInfoList, vldContrLvlPrimCheck(dsContrVldInfoList, param), msgMap);

        // START 2017/07/12 M.Naito [QC18585, MOD]
        // Machine Level Primary Validation
//        if (!isError) {
            vldCheck(param, dsContrVldInfoList, vldMachLvlPrimCheck(dsContrVldInfoList, param), msgMap);
//        }

        // Non Primary Validation
//        if (!isError) {
            vldCheck(param, dsContrVldInfoList, vldNonPrimCheck(dsContrVldInfoList, param), msgMap);
//        }
        // END 2017/07/12 M.Naito [QC18585, MOD]
        // END 2016/02/18 A.Kohinata [QC4252, MOD]

        // START 2017/07/12 M.Naito [QC18585, DEL]
//        // Not Validated
//        if (isError) {
//            DS_CONTR_VLD_RSLT_WRKTMsgArray notValidatedTMsgArray = createNotValidatedTMsgArray(dsContrVldInfoList, param.glblCmpyCd.getValue(), dsContrTMsg.dsContrPk.getValue());
//            if (notValidatedTMsgArray != null && notValidatedTMsgArray.getValidCount() > 0) {
//                if (!insertDsContrVldRsltWrk(notValidatedTMsgArray)) {
//                    return;
//                }
//            }
//        }
        // END 2017/07/12 M.Naito [QC18585, DEL]

        msgMap.flush();
    }

    // START 2016/02/18 A.Kohinata [QC4252, MOD]
    private boolean vldCheck(NSZC057001PMsg param, List<Map<String, Object>> dsContrVldInfoList, DS_CONTR_VLD_RSLT_WRKTMsgArray rsltTMsgArray, S21ApiMessageMap msgMap) {
        boolean isError = false;
        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        boolean isWarning = false;
        //END 2017/07/26 K.Kasai [QC#18882,ADD]
        if (rsltTMsgArray != null && rsltTMsgArray.getValidCount() > 0) {
            // START 2018/03/15 K.Kojima [QC#22611,ADD]
            replaceErrorWarning(dsContrVldInfoList, rsltTMsgArray);
            // END 2018/03/15 K.Kojima [QC#22611,ADD]
            if (!insertDsContrVldRsltWrk(rsltTMsgArray)) {
                msgMap.addXxMsgIdWithPrm(NSZM0626E, new String[] {"DS Contract Validation Result Work" });
            }
            if (isErrorInTMsg(rsltTMsgArray)) {
                isError = true;
            }
            //START 2017/07/26 K.Kasai [QC#18882,ADD]
            if (isWarningInTMsg(rsltTMsgArray)) {
                isWarning = true;
            }
            //END 2017/07/26 K.Kasai [QC#18882,ADD]
            //START 2017/07/26 K.Kasai [QC#18882,MOD]
            createPMsgParam(param, rsltTMsgArray, isError, isWarning);
            //END 2017/07/26 K.Kasai [QC#18882,MOD]
        }
        return isError;
    }
    // END 2016/02/18 A.Kohinata [QC4252, MOD]

    //START 2017/07/26 K.Kasai [QC#18882,MOD]
    private void createPMsgParam(NSZC057001PMsg param, DS_CONTR_VLD_RSLT_WRKTMsgArray tmsgArray, boolean isError, boolean isWarning) {
    //END 2017/07/26 K.Kasai [QC#18882,MOD]
        //START 2017/07/26 K.Kasai [QC#18882,MOD]
        if (!isError && !isWarning) {
        //END 2017/07/26 K.Kasai [QC#18882,MOD]
            for (int i = 0; i < param.contrVldRsltList.getValidCount(); i++) {
                setValue(param.contrVldRsltList.no(i).dsContrVldRsltMsgTxt, SUCCESS);
            }
        //START 2017/07/26 K.Kasai [QC#18882,MOD]
        } else {
            // TODO set detail validation result status
        }

        // judge validation result
        if (!ZYPCommonFunc.hasValue(param.dsContrVldRsltMsgTxt)) {
            setValue(param.dsContrVldRsltMsgTxt, SUCCESS);
        }

        if (SUCCESS.equals(param.dsContrVldRsltMsgTxt.getValue())) {
            if (isError) {
                setValue(param.dsContrVldRsltMsgTxt, ERROR);
            } else if (isWarning) {
                setValue(param.dsContrVldRsltMsgTxt, WARNING);
            }
        } else if (WARNING.equals(param.dsContrVldRsltMsgTxt.getValue())) {
            if (isError) {
                setValue(param.dsContrVldRsltMsgTxt, ERROR);
            }
        }
        //END 2017/07/26 K.Kasai [QC#18882,MOD]
    }

    private void createDefaultOutPMsg(NSZC057001PMsg param) {
        for (int i = 0; i < this.dsContrDtlArrayCache.get(KEY).getValidCount(); i++) {
            setValue(param.contrVldRsltList.no(i).serNum, getSerNum(this, param.glblCmpyCd.getValue(), this.dsContrDtlArrayCache.get(KEY).no(i).dsContrDtlPk.getValue()));
            setValue(param.contrVldRsltList.no(i).dsContrVldRsltMsgTxt, NOT_VALIDATED);
        }
    }

    private boolean insertDsContrVldRsltWrk(DS_CONTR_VLD_RSLT_WRKTMsgArray insTMsgArray) {

        List<DS_CONTR_VLD_RSLT_WRKTMsg> inMsgLst = new ArrayList<DS_CONTR_VLD_RSLT_WRKTMsg>(insTMsgArray.getValidCount());
        for (int i = 0; i < insTMsgArray.getValidCount(); i++) {
            inMsgLst.add(insTMsgArray.no(i));
        }
        DS_CONTR_VLD_RSLT_WRKTMsg[] inMsgArray;
        inMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            return false;
        }

        return true;
    }

    private boolean deleteDsContrVldRsltWrk(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_VLD_RSLT_WRKTMsg delTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        delTMsg.setSQLID("001");
        delTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        delTMsg.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_VLD_RSLT_WRKTMsgArray delTMsgArray = (DS_CONTR_VLD_RSLT_WRKTMsgArray) S21ApiTBLAccessor.findByCondition(delTMsg);
        List<DS_CONTR_VLD_RSLT_WRKTMsg> deleteTMsgList = new ArrayList<DS_CONTR_VLD_RSLT_WRKTMsg>(delTMsgArray.getValidCount());
        for (int i = 0; i < delTMsgArray.getValidCount(); i++) {
            deleteTMsgList.add(delTMsgArray.no(i));
        }
        if (deleteTMsgList.size() > 0) {
            if (S21FastTBLAccessor.removePhysical(deleteTMsgList.toArray(new DS_CONTR_VLD_RSLT_WRKTMsg[deleteTMsgList.size()])) == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isErrorInTMsg(DS_CONTR_VLD_RSLT_WRKTMsgArray tmsgArray) {
        for (int i = 0; i < tmsgArray.getValidCount(); i++) {
            if (tmsgArray.no(i).dsContrVldStsCd.getValue().equals(DS_CONTR_VLD_STS.ERROR)) {
                return true;
            }
        }
        return false;
    }

    //START 2017/07/26 K.Kasai [QC#18882,ADD]
    private boolean isWarningInTMsg(DS_CONTR_VLD_RSLT_WRKTMsgArray tmsgArray) {
        for (int i = 0; i < tmsgArray.getValidCount(); i++) {
            if (tmsgArray.no(i).dsContrVldStsCd.getValue().equals(DS_CONTR_VLD_STS.WARNING)) {
                return true;
            }
        }
        return false;
    }
    //END 2017/07/26 K.Kasai [QC#18882,ADD]

    private boolean checkParam(S21ApiMessageMap msgMap) {

        NSZC057001PMsg param = (NSZC057001PMsg) msgMap.getPmsg();

        // check common mandatory parameter
        checkCommonParameter(msgMap, param.glblCmpyCd, NSZM0001E);
        checkCommonParameter(msgMap, param.slsDt, NSZM0002E);
        checkCommonParameter(msgMap, param.dsContrNum, NSZM0271E);

        if (S21ApiUtil.isXxMsgId(param)) {
            return false;
        }
        return true;
    }

    private void checkCommonParameter(S21ApiMessageMap msgMap, EZDPItem item, String messageId) {
        if (!hasValue(item)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private DS_CONTRTMsg getDsContrHdr(String glblCmpyCd, String dsContrNum) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        dsContrTMsg.setSQLID("002");
        dsContrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsContrTMsg.setConditionValue("dsContrNum01", dsContrNum);
        dsContrTMsg.setConditionValue("dsContrSqNum01", SQ_NUM);
        DS_CONTRTMsgArray dsContrTMsgArray = (DS_CONTRTMsgArray) EZDTBLAccessor.findByCondition(dsContrTMsg);
        if (dsContrTMsgArray != null && dsContrTMsgArray.getValidCount() > 0) {
            dsContrTMsg = dsContrTMsgArray.no(0);
        }
        return dsContrTMsg;
    }

    private DS_CONTR_DTLTMsgArray getDsContrDtl(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
// START 2017/04/25 [RS#7239, MOD]
//        String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED };
        String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.ORDER };
// END   2017/04/25 [RS#7239, MOD]
        ssmPrm.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = new DS_CONTR_DTLTMsgArray();
        dsContrDtlTMsgArray.setMsgList(new DS_CONTR_DTLTMsg[MAX_SIZE]);
        this.ssmEzdClient.queryEZDMsgArray("getDsContrDtl", ssmPrm, dsContrDtlTMsgArray);
        return dsContrDtlTMsgArray;
    }

    private List<Map<String, Object>> getDsContrVldInfo(String glblCmpyCd, String slsDt, String dsContrCatgCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", END_DT);
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            ssmPrm.put("vldRegFlg", ZYPConstant.FLG_ON_Y);
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            ssmPrm.put("vldFleetFlg", ZYPConstant.FLG_ON_Y);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            ssmPrm.put("vldAggrFlg", ZYPConstant.FLG_ON_Y);
        } else if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            ssmPrm.put("vldWtyFlg", ZYPConstant.FLG_ON_Y);
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> dsContrInfoVldList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getDsContrVldInfo", ssmPrm);
        return dsContrInfoVldList;
    }

    private DS_CONTR_VLD_RSLT_WRKTMsgArray vldContrLvlPrimCheck(List<Map<String, Object>> dsContrInfoVldList, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int i = 0;
        for (; i < dsContrInfoVldList.size(); i++) {
            String primVldFlg = (String) dsContrInfoVldList.get(i).get("PRIM_VLD_FLG");
            String vldLvlContrFlg = (String) dsContrInfoVldList.get(i).get("VLD_LVL_CONTR_FLG");
            if (ZYPConstant.FLG_ON_Y.equals(primVldFlg) && ZYPConstant.FLG_ON_Y.equals(vldLvlContrFlg)) {
                if (!vldCheck(dsContrInfoVldList, param, rtrnTMsgArray, i)) {
                    return null;
                }
            // START 2016/02/18 A.Kohinata [QC4252, DEL]
//            } else {
//                break;
            // END 2016/02/18 A.Kohinata [QC4252, DEL]
            }
        }
        // START 2016/02/18 A.Kohinata [QC4252, DEL]
//        this.vldChkListIdx = i;
        // END 2016/02/18 A.Kohinata [QC4252, DEL]
        return rtrnTMsgArray;
    }

    private DS_CONTR_VLD_RSLT_WRKTMsgArray vldMachLvlPrimCheck(List<Map<String, Object>> dsContrInfoVldList, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int i = 0;
        for (; i < dsContrInfoVldList.size(); i++) {
            String primVldFlg = (String) dsContrInfoVldList.get(i).get("PRIM_VLD_FLG");
            String vldLvlMachFlg = (String) dsContrInfoVldList.get(i).get("VLD_LVL_MACH_FLG");
            if (ZYPConstant.FLG_ON_Y.equals(primVldFlg) && ZYPConstant.FLG_ON_Y.equals(vldLvlMachFlg)) {
                if (!vldCheck(dsContrInfoVldList, param, rtrnTMsgArray, i)) {
                    return null;
                }
            // START 2016/02/18 A.Kohinata [QC4252, DEL]
//            } else {
//                break;
            // END 2016/02/18 A.Kohinata [QC4252, DEL]
            }
        }
        // START 2016/02/18 A.Kohinata [QC4252, DEL]
//        this.vldChkListIdx = i;
        // END 2016/02/18 A.Kohinata [QC4252, DEL]
        return rtrnTMsgArray;
    }

    private DS_CONTR_VLD_RSLT_WRKTMsgArray vldNonPrimCheck(List<Map<String, Object>> dsContrInfoVldList, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int i = 0;
        for (; i < dsContrInfoVldList.size(); i++) {
            String primVldFlg = (String) dsContrInfoVldList.get(i).get("PRIM_VLD_FLG");
            if (ZYPConstant.FLG_OFF_N.equals(primVldFlg)) {
                if (!vldCheck(dsContrInfoVldList, param, rtrnTMsgArray, i)) {
                    return null;
                }
            // START 2016/02/18 A.Kohinata [QC4252, DEL]
//            } else {
//                break;
            // END 2016/02/18 A.Kohinata [QC4252, DEL]
            }
        }
        // START 2016/02/18 A.Kohinata [QC4252, DEL]
//        this.vldChkListIdx = i;
        // END 2016/02/18 A.Kohinata [QC4252, DEL]
        return rtrnTMsgArray;
    }

    private boolean vldCheck(List<Map<String, Object>> dsContrInfoVldList, NSZC057001PMsg param, DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray, int idx) {
        String vldCmptTxt = (String) dsContrInfoVldList.get(idx).get("VLD_CMPT_TXT");
        String[] splitArray = vldCmptTxt.split(COMMA);
        if (splitArray.length > 1) {
            DS_CONTR_VLD_RSLT_WRKTMsgArray rsltTMsgArray = executeMethod(NSZC057001.class.getPackage().getName() + PERIOD + splitArray[0], splitArray[1], param);
            // START 2016/02/18 A.Kohinata [QC4252, ADD]
            this.vldChkListIdx = idx;
            // END 2016/02/18 A.Kohinata [QC4252, ADD]
            for (int i = 0; i < rsltTMsgArray.getValidCount(); i++) {
                setValue(rsltTMsgArray.no(i).glblCmpyCd, param.glblCmpyCd);
                setValue(rsltTMsgArray.no(i).dsContrVldRsltWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_VLD_RSLT_WRK_SQ));
                setValue(rsltTMsgArray.no(i).dsContrVldListPk, (BigDecimal) dsContrInfoVldList.get(idx).get("DS_CONTR_VLD_LIST_PK"));
                setValue(rsltTMsgArray.no(i).dsContrVldPk, (BigDecimal) dsContrInfoVldList.get(idx).get("DS_CONTR_VLD_PK"));
            }
            if (!addTMsgArray(rtrnTMsgArray, rsltTMsgArray)) {
                return false;
            }
        }
        return true;
    }

    private DS_CONTR_VLD_RSLT_WRKTMsgArray createNotValidatedTMsgArray(List<Map<String, Object>> dsContrInfoVldList, String glblCmpyCd, BigDecimal dsContrPk) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        // START 2016/02/18 A.Kohinata [QC4252, MOD]
        for (int i = this.vldChkListIdx + 1; i < dsContrInfoVldList.size(); i++) {
        // END 2016/02/18 A.Kohinata [QC4252, MOD]
            DS_CONTR_VLD_RSLT_WRKTMsg rsltTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rsltTMsg.glblCmpyCd, glblCmpyCd);
            setValue(rsltTMsg.dsContrVldRsltWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_VLD_RSLT_WRK_SQ));
            setValue(rsltTMsg.dsContrPk, dsContrPk);
            setValue(rsltTMsg.dsContrVldListPk, (BigDecimal) dsContrInfoVldList.get(i).get("DS_CONTR_VLD_LIST_PK"));
            setValue(rsltTMsg.dsContrVldPk, (BigDecimal) dsContrInfoVldList.get(i).get("DS_CONTR_VLD_PK"));
            setValue(rsltTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.NOT_VALIDATED);
            if (!addTMsgArray(rtrnTMsgArray, rsltTMsg)) {
                return null;
            }
        }
        return rtrnTMsgArray;
    }

    private boolean addTMsgArray(DS_CONTR_VLD_RSLT_WRKTMsgArray tmsgArray, DS_CONTR_VLD_RSLT_WRKTMsg tmsg) {
        if ((tmsgArray.getValidCount() + 1) > MAX_SIZE) {
            return false;
        }
        EZDMsg.copy(tmsg, null, tmsgArray.no(tmsgArray.getValidCount()), null);
        tmsgArray.setValidCount(tmsgArray.getValidCount() + 1);
        return true;
    }

    private boolean addTMsgArray(DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray, DS_CONTR_VLD_RSLT_WRKTMsgArray rsltTMsgArray) {
        if ((rtrnTMsgArray.getValidCount() + rsltTMsgArray.getValidCount()) > MAX_SIZE) {
            return false;
        }
        for (int i = 0; i < rsltTMsgArray.getValidCount(); i++) {
            EZDMsg.copy(rsltTMsgArray.no(i), null, rtrnTMsgArray.no(rtrnTMsgArray.getValidCount() + i), null);
        }
        rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + rsltTMsgArray.getValidCount());
        return true;
    }

    private DS_CONTR_VLD_RSLT_WRKTMsgArray executeMethod(String classNm, String methodNm, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = null;
        Class<?> clazz = null;
        Method method = null;
        try {
            clazz = Class.forName(classNm);
            method = clazz.getMethod(methodNm, NSZC057001.class, NSZC057001PMsg.class);
        } catch (ClassNotFoundException e) {
            throw new S21AbendException(e);
        } catch (SecurityException e) {
            throw new S21AbendException(e);
        } catch (NoSuchMethodException e) {
            throw new S21AbendException(e);
        }
        try {
            rtrnTMsgArray = (DS_CONTR_VLD_RSLT_WRKTMsgArray) method.invoke(clazz.newInstance(), this, param);
        } catch (InstantiationException e) {
            throw new S21AbendException(e);
        } catch (IllegalAccessException e) {
            throw new S21AbendException(e);
        } catch (IllegalArgumentException e) {
            throw new S21AbendException(e);
        } catch (InvocationTargetException e) {
            throw new S21AbendException(e);
        }
        return rtrnTMsgArray;
    }

    // START 2018/03/15 K.Kojima [QC#22611,ADD]
    private void replaceErrorWarning(List<Map<String, Object>> dsContrVldInfoList, DS_CONTR_VLD_RSLT_WRKTMsgArray rsltTMsgArray) {
        for (int wrkCount = 0; wrkCount < rsltTMsgArray.getValidCount(); wrkCount++) {
            BigDecimal dsContrVldListPkWrk = rsltTMsgArray.no(wrkCount).dsContrVldListPk.getValue();
            BigDecimal dsContrVldPkWrk = rsltTMsgArray.no(wrkCount).dsContrVldPk.getValue();
            for (int infoCount = 0; infoCount < dsContrVldInfoList.size(); infoCount++) {
                if (!ZYPCommonFunc.hasValue(rsltTMsgArray.no(wrkCount).dsContrVldStsCd)) {
                    continue;
                }
                if (!rsltTMsgArray.no(wrkCount).dsContrVldStsCd.getValue().equals(DS_CONTR_VLD_STS.ERROR)) {
                    continue;
                }
                BigDecimal dsContrVldListPkInfo = (BigDecimal) dsContrVldInfoList.get(infoCount).get("DS_CONTR_VLD_LIST_PK");
                BigDecimal dsContrVldPkInfo = (BigDecimal) dsContrVldInfoList.get(infoCount).get("DS_CONTR_VLD_PK");
                if ((dsContrVldListPkWrk.compareTo(dsContrVldListPkInfo) == 0) && (dsContrVldPkWrk.compareTo(dsContrVldPkInfo) == 0)) {
                    String vldActCd = (String) dsContrVldInfoList.get(infoCount).get("VLD_ACT_CD");
                    if (VLD_ACT.WARN.equals(vldActCd)) {
                        ZYPEZDItemValueSetter.setValue(rsltTMsgArray.no(wrkCount).dsContrVldStsCd, DS_CONTR_VLD_STS.WARNING);
                        break;
                    }
                }
            }
        }
    }
    // END 2018/03/15 K.Kojima [QC#22611,ADD]
}
