/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC050001;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.COLUMN_DEL_DVC_NM;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.COLUMN_DEL_RDS_NM;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.COLUMN_VRSN_NM;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.COLUMN_VRSN_NUM;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.CONST_DEF_MTR_READ_METH_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.CONST_NORMAL_MSG;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.CONST_UGW_CSV_DBL_QUOT_FLG;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.CONST_UGW_LINE_FEED_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.CRLF;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.CRLF_CODE;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.IWR_COND_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.IWR_RGTN_STS_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.LENGTH_API_MSG;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.LF;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.LF_CODE;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.MSG_ERR;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.MSG_NML;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSBM0120E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM0001E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM0002E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM0006E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM0036E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM0074E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM1047E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM1048E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM1049E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM1050W;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM1051E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM1052E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM1053E;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.NSZM1068I;
import static com.canon.cusa.s21.api.NSZ.NSZC050001.constant.NSZC050001Constant.UPDATE_USER_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_DTLTMsg;
import business.db.IWR_RGTN_CONDTMsg;
import business.db.IWR_RGTN_CONDTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;
import business.db.UGW_DEF_SETTMsg;
import business.db.UGW_DEF_SETTMsgArray;
import business.parts.NSZC050001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.usa.s21.integration.service.s21csawds.universalgateway.data.UniversalGatewayRequest;
import com.canon.usa.s21.integration.service.s21csawds.universalgateway.data.UniversalGatewayResponse;
import com.canon.usa.s21.integration.service.s21csawds.universalgateway.wrapper.S21csawdsUniversalGatewayServiceProxy;

/**
 *<pre>
 * De-Register from UGW API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/03   Hitachi         Y.Zhang         Create          N/A
 * 2016/10/17   Hitachi         Y.Zhang         Create          QC#15162
 * 2016/11/24   Hitachi         N.Arai          Update          QC#15829
 * 12/06/2016   Fujitsu         S.Nakai         Update          QC#15437
 *</pre>
 */
public class NSZC050001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** User Id */
    private String usrId;

    /** response Message Flag */
    private String respMsgFlg = null;

    /**
     * execute
     * @param pMsgList List<NSZC050001PMsg>
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(List<NSZC050001PMsg> pMsgList, ONBATCH_TYPE onBatTp) {
        for (NSZC050001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * execute
     * @param pMsg NSZC050001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC050001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

// START 2016/11/24 N.Arai [QC#15829, MOD]
        /*************************************************************
         * 0. initialize global variables.
         ************************************************************/
        S21UserProfileServiceFactory profileService = S21UserProfileServiceFactory.getInstance();

        if (profileService != null) {

            S21UserProfileService profile = profileService.getService();

            if (profile != null) {

                S21UserInfo userInfo = profile.getContextUserInfo();

                if (userInfo != null) {

                    this.usrId = userInfo.getUserId();
                }
            }
        }

        if (!hasValue(this.usrId)) {
            this.usrId = UPDATE_USER_ID;
        }
// END 2016/11/24 N.Arai [QC#15829, MOD]
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        executeProc(pMsg, onBatchType, msgMap);
        msgMap.flush();
    }

    /**
     * execute
     * @param pMsg NSZC050001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @param msgMap S21ApiMessageMap
     */
    public void executeProc(NSZC050001PMsg pMsg, final ONBATCH_TYPE onBatchType, S21ApiMessageMap msgMap) {

        // initialize
        NSZC050001PMsg param = (NSZC050001PMsg) msgMap.getPmsg();
        String apiResponseMsg = null;
        String csvData = null;
        boolean updateContrFlg = false;
        boolean regsterUgwFlg = false;

        // mandatory check
        if (!checkParam(msgMap)) {
            return;
        }

        // interface data create
        // get SVC_MACH_MSTR Info
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(param);

        // get register IWR_RGTN_COND
        IWR_RGTN_CONDTMsgArray iwrRgtnCondTMsgArray = getiwrRgtnCond(param);

        // interface data check
        if (!checkInterfaceData(msgMap, svcMachMstrTMsg, iwrRgtnCondTMsgArray)) {
            return;
        }

        // get UGW_DEF_SET Info
        UGW_DEF_SETTMsgArray ugwDefSetMsgArray = getUgwDefSet(param);

        // csv data creat
        csvData = setInterfaceData(param, svcMachMstrTMsg, ugwDefSetMsgArray, iwrRgtnCondTMsgArray);

        // get API normal message 
        String nomalMsg = ZYPCodeDataUtil.getVarCharConstValue(CONST_NORMAL_MSG, param.glblCmpyCd.getValue());

        // call register UGW API To WMB
        try {
            apiResponseMsg = callRegisterUgwApi(csvData);
            setRespMsg(svcMachMstrTMsg.serNum.getValue(), apiResponseMsg);
            if (!(ZYPConstant.FLG_ON_Y.equals(svcMachMstrTMsg.shrDlrFlg.getValue()))) {

                if (hasValue(apiResponseMsg) && apiResponseMsg.contains(nomalMsg)) {
                    updateContrFlg = true;
                    regsterUgwFlg = true;
                } else {
                    updateContrFlg = false;
                    regsterUgwFlg = false;
                    // warning messageId
                    msgMap.addXxMsgId(NSZM1050W);
                }

                // update DS_CONTR_DTL
                if (updateContrFlg) {
                    updateDsContrDtl(msgMap, svcMachMstrTMsg);
                }

                // update SVC_MACH_MSTR
                if (regsterUgwFlg) {
                    updateSvcMachMstr(msgMap);
                }
            }
            // update IWR_RGTN_COND
            updateIwrRgtnCond(msgMap, svcMachMstrTMsg, iwrRgtnCondTMsgArray, apiResponseMsg, nomalMsg);
        } catch (Throwable e) {
            msgMap.addXxMsgId(NSZM1049E);
            EZDDebugOutput.println(1, NSZM1049E, this);
            return;
        }
    }

    /**
     *updateDsContrDtl
     * @param msgMap S21ApiMessageMap
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private void updateDsContrDtl(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        NSZC050001PMsg param = (NSZC050001PMsg) msgMap.getPmsg();
        List<Map<String, Object>> dsContrDtlList = getContrDtlInfo(param, svcMachMstrTMsg);
        String mtrReadMethCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_DEF_MTR_READ_METH_CD, param.glblCmpyCd.getValue());
        for (Map<String, Object> dsContrDtlMap : dsContrDtlList) {
            DS_CONTR_DTLTMsg dsContrDtlMsg = new DS_CONTR_DTLTMsg();
            setValue(dsContrDtlMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(dsContrDtlMsg.dsContrDtlPk, (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
            dsContrDtlMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlMsg);
            if (!hasValue(dsContrDtlMsg.prevMtrReadMethCd)) {
                setValue(dsContrDtlMsg.mtrReadMethCd, mtrReadMethCd);
            } else {
                setValue(dsContrDtlMsg.mtrReadMethCd, dsContrDtlMsg.prevMtrReadMethCd);
            }

            S21ApiTBLAccessor.update(dsContrDtlMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM1048E);
            }
        }
    }

    /**
     *updateIwrRgtnCond
     * @param msgMap S21ApiMessageMap
     */
    private void updateIwrRgtnCond(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, IWR_RGTN_CONDTMsgArray iwrRgtnCondTMsgArray, String apiMsg, String nomalMsg) {

        NSZC050001PMsg param = (NSZC050001PMsg) msgMap.getPmsg();
        IWR_RGTN_CONDTMsg inTMsg = new IWR_RGTN_CONDTMsg();
        setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(inTMsg.iwrRgtnCondPk, iwrRgtnCondTMsgArray.no(0).iwrRgtnCondPk.getValue());
        inTMsg = (IWR_RGTN_CONDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
        // normal finished data update
        if (hasValue(apiMsg) && apiMsg.contains(nomalMsg)) {
            setValue(inTMsg.iwrDeinsDt, param.slsDt);
        }
        setValue(inTMsg.ugwMsgTxt, cutStr(apiMsg, LENGTH_API_MSG));
        // update
        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM1047E);
        }
    }

    /**
     *updateDsContrDtl
     * @param msgMap S21ApiMessageMap
     */
    private void updateSvcMachMstr(S21ApiMessageMap msgMap) {

        NSZC050001PMsg param = (NSZC050001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg inTMsg = new SVC_MACH_MSTRTMsg();
        setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(inTMsg.svcMachMstrPk, param.svcMachMstrPk);
        inTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
// START 2016/11/24 N.Arai [QC#15829, MOD]
        Map<String, String> beforParamMap = new HashMap<String, String>();
        beforParamMap.put(IWR_RGTN_STS_CD, inTMsg.iwrRgtnStsCd.getValue());
        beforParamMap.put(IWR_COND_CD, inTMsg.iwrCondCd.getValue());
// END 2016/11/24 N.Arai [QC#15829, MOD]
        setValue(inTMsg.iwrRgtnStsCd, IWR_RGTN_STS.DEREGISTERED);
        setValue(inTMsg.iwrCondCd, IWR_COND.DISABLED);
        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0036E);
        }
// START 2016/11/24 N.Arai [QC#15829, MOD]
        if (!insertSvcMachMstrTrk(param, beforParamMap, inTMsg)) {
            throw new S21AbendException(NSBM0120E, new String[] {"SVC_MACH_MSTR_TRK"});
        }
//END 2016/11/24 N.Arai [QC#15829, MOD]
    }

    /**
     *getContrDtlInfo
     * @param param NSZC050001PMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private List<Map<String, Object>> getContrDtlInfo(NSZC050001PMsg param, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("svcMachMstrPk", svcMachMstrTMsg.svcMachMstrPk.getValue());
        ssmParam.put("slsDt", param.slsDt.getValue());
        ssmParam.put("mtrReadMethCd", MTR_READ_METH.IMAGEWARE);
        ssmParam.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.CANCELLED);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrDtlInfo", ssmParam);
    }

    /**
     *callRegisterUgwApi
     * @param param NSZC050001PMsg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private String callRegisterUgwApi(String csvData) throws Throwable {

        String csvDataOut = null;
        UniversalGatewayRequest request = new UniversalGatewayRequest();
        request.setCsvData(csvData);
        UniversalGatewayResponse response = null;

        try {
            // Invoke the proxy
            response = new S21csawdsUniversalGatewayServiceProxy().invokeUniversalGateway(request);
            csvDataOut = response.getCsvData();
            EZDDebugOutput.println(1, MSG_NML, this);
        } catch (Exception e) {
            EZDDebugOutput.println(1, MSG_ERR, this);
        }

        return csvDataOut;
    }

    /**
     *checkParam
     * @param pMsg S21ApiMessageMap
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {

        NSZC050001PMsg param = (NSZC050001PMsg) msgMap.getPmsg();
        // check common mandatory parameter
        checkCommonParameter(msgMap, param.glblCmpyCd, NSZM0001E);
        checkCommonParameter(msgMap, param.slsDt, NSZM0002E);
        checkCommonParameter(msgMap, param.svcMachMstrPk, NSZM0074E);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    /**
     *checkParam
     * @param pMsg S21ApiMessageMap
     */
    private void checkCommonParameter(S21ApiMessageMap msgMap, EZDPItem item, String messageId) {
        if (!hasValue(item)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    /**
     *checkParam
     * @param pMsg S21ApiMessageMap
     */
    private UGW_DEF_SETTMsgArray getUgwDefSet(NSZC050001PMsg param) {
        UGW_DEF_SETTMsg inMsg = new UGW_DEF_SETTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("actvFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("effFromDt01", param.slsDt.getValue());
        inMsg.setConditionValue("effThruDt01", param.slsDt.getValue());
        UGW_DEF_SETTMsgArray tMsgAry = (UGW_DEF_SETTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return tMsgAry;
    }

    /**
     *getSvcMachMstr
     * @param param NSZC050001PMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(NSZC050001PMsg param) {
        SVC_MACH_MSTRTMsg inTMsg = new SVC_MACH_MSTRTMsg();
        setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(inTMsg.svcMachMstrPk, param.svcMachMstrPk);
        SVC_MACH_MSTRTMsg outTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     *getiwrRgtnCond
     * @param param NSZC050001PMsg
     */
    private IWR_RGTN_CONDTMsgArray getiwrRgtnCond(NSZC050001PMsg param) {

        IWR_RGTN_CONDTMsg inMsg = new IWR_RGTN_CONDTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMachMstrPk01", param.svcMachMstrPk.getValue());
        IWR_RGTN_CONDTMsgArray tMsgAry = (IWR_RGTN_CONDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return tMsgAry;
    }

    /**
     *checkInterfaceData
     * @param pMsg S21ApiMessageMap
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private boolean checkInterfaceData(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, IWR_RGTN_CONDTMsgArray iwrRgtnCondTMsgArray) {

        // check interface data
        if (svcMachMstrTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return false;
        }

        // register check
        if (!(IWR_RGTN_STS.REGISTERED.equals(svcMachMstrTMsg.iwrRgtnStsCd.getValue())) && !(IWR_RGTN_STS.AWAITING_DEREGISTRATION.equals(svcMachMstrTMsg.iwrRgtnStsCd.getValue()))) {
            msgMap.addXxMsgId(NSZM1051E);
            return false;
        }

        // register check
        if (iwrRgtnCondTMsgArray.getValidCount() == 0) {
            // not existed mistake
            msgMap.addXxMsgId(NSZM1052E);
            return false;
        } else if (iwrRgtnCondTMsgArray.getValidCount() >= 2) {
            // Multiple registration Information exists mistake
            msgMap.addXxMsgId(NSZM1053E);
            return false;

        }
        return true;
    }

    /**
     *setInterfaceData
     * @param pMsg S21ApiMessageMap
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private String setInterfaceData(NSZC050001PMsg param, SVC_MACH_MSTRTMsg svcMachMstrTMsg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, IWR_RGTN_CONDTMsgArray iwrRgtnCondTMsgArray) {

        // get var Char Info from VAR_CHAR_CONST
        String csvDblQuotFlg = ZYPCodeDataUtil.getVarCharConstValue(CONST_UGW_CSV_DBL_QUOT_FLG, param.glblCmpyCd.getValue());
        String lineFeedCode = ZYPCodeDataUtil.getVarCharConstValue(CONST_UGW_LINE_FEED_CD, param.glblCmpyCd.getValue());
        if (LF.equals(lineFeedCode)) {
            lineFeedCode = LF_CODE;
        } else if (CRLF.equals(lineFeedCode)) {
            lineFeedCode = CRLF_CODE;
        }

        // create Record line
        String lineData = null;
        StringBuilder csvDataBul = new StringBuilder();

        // create Version Record
        lineData = createVersionRecord(csvDblQuotFlg, ugwDefSetMsgArray);
        csvDataBul.append(lineData);
        csvDataBul.append(lineFeedCode);

        // create Del RDS Record
//        lineData = createDelRdsRecord(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg);
//        csvDataBul.append(lineData);
//        csvDataBul.append(lineFeedCode);

        // create Del Device Record
        lineData = createDelDevRecord(csvDblQuotFlg, ugwDefSetMsgArray, iwrRgtnCondTMsgArray);
        csvDataBul.append(lineData);
        csvDataBul.append(lineFeedCode);

        // return csv data
        return csvDataBul.toString();
    }

    /**
     *createVersionRecord
     * @param pMsg S21ApiMessageMap
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     */
    private String createVersionRecord(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray) {
        VersionRecordBean verRecBean = new VersionRecordBean();
        verRecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        verRecBean.setRecordName(getUgwDefSetVal(COLUMN_VRSN_NM, ugwDefSetMsgArray));
        verRecBean.setVersion(getUgwDefSetVal(COLUMN_VRSN_NUM, ugwDefSetMsgArray));
        return verRecBean.createLineData();
    }

    /**
     *createDelRdsRecord
     * @param String csvDblQuotFlg
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param ugwDefSetMsgArray UGW_DEF_SETTMsgArray
     */
    private String createDelRdsRecord(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        DelRdsRecordBean delRecBean = new DelRdsRecordBean();
        delRecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        delRecBean.setRecordName(getUgwDefSetVal(COLUMN_DEL_RDS_NM, ugwDefSetMsgArray));
        delRecBean.setRdsId(svcMachMstrTMsg.serNum.getValue());
        return delRecBean.createLineData();
    }

    /**
     *createDelDevRecord
     * @param String csvDblQuotFlg
     * @param UGW_DEF_SETTMsgArray ugwDefSetMsgArray
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param IWR_RGTN_CONDTMsgArray iwrRgtnCondTMsgArray
     */
  private String createDelDevRecord(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, IWR_RGTN_CONDTMsgArray iwrRgtnCondTMsgArray) {
        DelDevRecordBean delDevBean = new DelDevRecordBean();
        delDevBean.setCsvDblQoutFlg(csvDblQuotFlg);
        delDevBean.setUgwRecNm(getUgwDefSetVal(COLUMN_DEL_DVC_NM, ugwDefSetMsgArray));
        if (!hasValue(iwrRgtnCondTMsgArray.no(0).childSerNum.getValue())) {
            delDevBean.setUgwDvcId(iwrRgtnCondTMsgArray.no(0).serNum.getValue());
        } else {
            delDevBean.setUgwDvcId(iwrRgtnCondTMsgArray.no(0).childSerNum.getValue());
        }
        return delDevBean.createLineData();
    }

    /**
     *getUgwDefSetVal
     * @param str String
     * @param tMsgAry UGW_DEF_SETTMsgArray
     */
    private String getUgwDefSetVal(String str, UGW_DEF_SETTMsgArray tMsgAry) {

        if (!hasValue(str)) {
            return null;
        }
        for (int i = 0; i < tMsgAry.getValidCount(); i++) {
            if (str.equals(tMsgAry.no(i).ugwDefSetCd.getValue())) {
                return tMsgAry.no(i).ugwDefSetValTxt.getValue();
            }
        }
        return null;
    }

    /**
     *createRecordData
     * @param String csvDblQoutFlg
     * @param String params
     */
    protected static String createRecordData(String csvDblQoutFlg, String... param) {
        String lineStr = null;
        for (String s : param) {
            if (!hasValue(s)) {
                s = "";
            }
            if (ZYPConstant.FLG_ON_Y.equals(csvDblQoutFlg)) {
                s = ZYPCommonFunc.concatString("\"", s, "\"");
            }
            if (!hasValue(lineStr)) {
                lineStr = s;
            } else {
                lineStr = ZYPCommonFunc.concatString(lineStr, ",", s);
            }
        }
        return lineStr;
    }

    /**
     *cut string length
     * @param str String
     * @param str String
     */
    private String cutStr(String str, int length) {
        if (!hasValue(str)) {
            return null;
        } else if (str.length() > length) {
            return str.substring(0, length);
        }
        return str;
    }

// START 2016/11/24 N.Arai [QC#15829, MOD]
    private boolean insertSvcMachMstrTrk(NSZC050001PMsg param, Map<String, String> beforMap, SVC_MACH_MSTRTMsg newTMsg) {

        if (!setSvcMachMstrTrk(param, IWR_RGTN_STS_CD, beforMap.get(IWR_RGTN_STS_CD), newTMsg.iwrRgtnStsCd.getValue())) {
            return false;
        }
        if (!setSvcMachMstrTrk(param, IWR_COND_CD, beforMap.get(IWR_COND_CD), newTMsg.iwrCondCd.getValue())) {
            return false;
        }
        return true;

    }

    private boolean setSvcMachMstrTrk(NSZC050001PMsg param, String updFld, String oldVal, String newVal) {

        if (!ZYPCommonFunc.hasValue(oldVal) && !ZYPCommonFunc.hasValue(newVal)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(oldVal) && newVal.equals(oldVal)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(newVal) && oldVal.equals(newVal)) {
            return true;
        }

        SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();

        setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(tMsg.svcMachMstrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ));
        setValue(tMsg.svcMachMstrPk, param.svcMachMstrPk);
        setValue(tMsg.trxRgtnDt, param.slsDt);
        setValue(tMsg.updFldTxt, updFld);
        setValue(tMsg.oldValTxt, oldVal);
        setValue(tMsg.newValTxt, newVal);
        setValue(tMsg.updUsrId, this.usrId);
        setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.create(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
// END 2016/11/24 N.Arai [QC#15829, MOD]
    private void setRespMsg(String serNum, String respMsg) {
        if (!ZYPCommonFunc.hasValue(this.respMsgFlg) || ZYPConstant.FLG_ON_Y.equals(this.respMsgFlg)) {
            String msg = S21MessageFunc.clspGetMessage(NSZM1068I, new String[] {serNum, respMsg});
            S21InfoLogOutput.println(msg);
        }
    }
}
