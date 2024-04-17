/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC049001;

import static com.canon.cusa.s21.api.NSZ.NSZC049001.constant.NSZC049001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.IWR_MDL_MAPTMsg;
import business.db.IWR_MDSE_LINKTMsg;
import business.db.IWR_MDSE_LINKTMsgArray;
import business.db.IWR_RGTN_CONDTMsg;
import business.db.IWR_RGTN_CONDTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;
import business.db.SVC_TASKTMsg;
import business.db.UGW_DEF_SETTMsg;
import business.db.UGW_DEF_SETTMsgArray;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC049001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.usa.s21.integration.service.s21csawds.universalgateway.data.UniversalGatewayRequest;
import com.canon.usa.s21.integration.service.s21csawds.universalgateway.data.UniversalGatewayResponse;
import com.canon.usa.s21.integration.service.s21csawds.universalgateway.transaction.FaultConfigurationExp;
import com.canon.usa.s21.integration.service.s21csawds.universalgateway.transaction.FaultDataExp;
import com.canon.usa.s21.integration.service.s21csawds.universalgateway.transaction.FaultResourceExp;
import com.canon.usa.s21.integration.service.s21csawds.universalgateway.wrapper.S21csawdsUniversalGatewayServiceProxy;

/**
 *<pre>
 * Register to UGW API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/01   Hitachi         K.Kasai         Create          N/A
 * 2016/10/17   Hitachi         Y.Zhang         Update          QC#15162
 * 2016/10/17   Fujitsu         S.Nakai         Update          QC#15437
 * 2016/10/25   Hitachi         Y.Zhang         Update          QC#15437
 * 2016/11/04   Hitachi         T.Mizuki        Update          QC#15437
 * 2016/11/24   Hitachi         N.Arai          Update          QC#15829
 * 2016/12/07   Hitachi         A.Kohinata      Update          QC#16356
 * 2017/12/13   Hitachi         K.Yamada        Update          QC#23071
 * 2018/01/22   Hitachi         K.Kojima        Update          QC#22827
 *</pre>
 */
public class NSZC049001 extends S21ApiCommonBase {

    /** child Service Machine Master PK */
    private BigDecimal childSvcMachMstrPk = null;

    /** child Serial Number */
    private String childSerNum = null;

    /** ds Account name */
    private String dsAcctNm = null;
    
    /** response Message Flag */
    private String respMsgFlg = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /**
     * execute
     * @param pMsgList List<NSZC049001PMsg>
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(List<NSZC049001PMsg> pMsgList, ONBATCH_TYPE onBatTp) {
        for (NSZC049001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * execute
     * @param pMsg NSZC049001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC049001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        executeProc(pMsg, onBatchType, msgMap);
        msgMap.flush();
    }

    private void executeProc(NSZC049001PMsg pMsg, final ONBATCH_TYPE onBatchType, S21ApiMessageMap msgMap) {

        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();

        // mandatory check
        if (!checkParam(msgMap)) {
            return;
        }

        // initialize
        this.childSvcMachMstrPk = null;
        this.childSerNum = null;
        this.dsAcctNm = null;
        this.respMsgFlg = ZYPCodeDataUtil.getVarCharConstValue(NSZC0460_RSP_MSG, param.glblCmpyCd.getValue());
        // get UGW_DEF_SET Info
        UGW_DEF_SETTMsgArray ugwDefSetMsgArray = getUgwDefSet(param);

        // create Interface Data
        String iwrLinkMdseCd = null;
        String mdseCdForOrdTake = null;
        boolean createSvcCallFlg = false;
        boolean updateContrFlg = false;
        boolean regsterUgwFlg = true;
        boolean shrdMachFlg = false;
        // get svc_mach_mstr
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(param);
        // START 2016/10/25 Y.Zhang [QC#15437, MOD]
        // get in plant date and get ship date
        Map<String, Object> installDateMap = getInstallDate(svcMachMstrTMsg, param);
        // get Install Date
        String installDate = "";
        if (svcMachMstrTMsg != null && hasValue(svcMachMstrTMsg.istlDt.getValue())) {
            // if the value of SVC_MACH_MSTR.ISTL_DT is exists
            installDate = svcMachMstrTMsg.istlDt.getValue();
            // if the value of SHPG_PLN.ACTL_ARV_DT is exists
        } else if (installDateMap != null && installDateMap.get("ACTL_ARV_DT") != null) {
            installDate = installDateMap.get("ACTL_ARV_DT").toString();
         // if the value of SHPG_PLN.ACTL_SHIP_DT is exists
        } else if (installDateMap != null && installDateMap.get("ACTL_SHIP_DT") != null) {
            installDate = installDateMap.get("ACTL_SHIP_DT").toString();
        } else {
            // Other than those above
            installDate = param.slsDt.getValue();
        }
        // END 2016/10/25 Y.Zhang [QC#15437, MOD]
        // get power supply
        if (svcMachMstrTMsg != null) {
            mdseCdForOrdTake = svcMachMstrTMsg.mdseCd.getValue();
            if (mdseCdForOrdTake.length() > LENGTH_ORD_TAKE_MDSE) {
                mdseCdForOrdTake = mdseCdForOrdTake.substring(0, LENGTH_ORD_TAKE_MDSE);
            }
            // get IWR link Mdse Code
            iwrLinkMdseCd = getIwrLinkMdseCd(param, mdseCdForOrdTake);
        }
        String csvData = null;
        String apiResponseMsg = null;
        if (!checkInterfaceData(msgMap, svcMachMstrTMsg, iwrLinkMdseCd, mdseCdForOrdTake)) {
            return;
        }
        // mod start 2016/12/07 CSA QC#16356
        csvData = setInterfaceData(msgMap, svcMachMstrTMsg, ugwDefSetMsgArray, iwrLinkMdseCd, mdseCdForOrdTake, installDate);
        if (csvData == null) {
            return;
        }
        // mod end 2016/12/07 CSA QC#16356
        // call register UGW API To WMB
        try {
            apiResponseMsg = callRegisterUgwApi(csvData);
            setRespMsg(svcMachMstrTMsg.serNum.getValue(), apiResponseMsg);
            String nomalMsg = setMsg(CONST_NORMAL_MSG, NORMAL_MSG, param.glblCmpyCd.getValue());
            String errMsgDup = setMsg(CONST_ERR_MSG_DUP, ERR_MSG_DUP, param.glblCmpyCd.getValue());
            String errMsgNotExt = setMsg(CONST_ERR_MSG_NOT_EXIST, ERR_MSG_NOT_EXIST_RDS_ID, param.glblCmpyCd.getValue());
            String errMsgChkCont = setMsg(CONST_ERR_MSG_CHECK_CONT, ERR_MSG_CHECK_CONTENTS, param.glblCmpyCd.getValue());
            if (hasValue(apiResponseMsg) && apiResponseMsg.contains(nomalMsg)) {
                createSvcCallFlg = true;
                updateContrFlg = true;
                regsterUgwFlg = true;
                shrdMachFlg = false;
            // mod start 2017/12/13 QC#23071
            //} else if (hasValue(apiResponseMsg) && (apiResponseMsg.contains(errMsgDup) || apiResponseMsg.contains(errMsgNotExt) || apiResponseMsg.contains(errMsgChkCont))) {
            } else if (hasValue(apiResponseMsg) &&
                    (apiResponseMsg.contains(errMsgDup) || (apiResponseMsg.contains(errMsgNotExt) && apiResponseMsg.contains(errMsgChkCont)))) {
            // mod end 2017/12/13 QC#23071
                createSvcCallFlg = false;
                updateContrFlg = false;
                regsterUgwFlg = true;
                shrdMachFlg = true;
                msgMap.addXxMsgId(NSZM1043W);
            } else {
                createSvcCallFlg = false;
                updateContrFlg = false;
                regsterUgwFlg = false;
                shrdMachFlg = false;
                msgMap.addXxMsgId(NSZM1044W);
            }
        } catch (Throwable e) {
            msgMap.addXxMsgId(NSZM1049E);
            EZDDebugOutput.println(1, NSZM1049E, this);
            return;
        }
        // create Service Call
        if (ZYPConstant.FLG_ON_Y.equals(param.svcCallFlg.getValue()) && createSvcCallFlg) {
            if (!callFsrUpdateApi(msgMap, svcMachMstrTMsg, onBatchType)) {
                return;
            }
        }
        // update DS_CONTR_DTL
        if (updateContrFlg) {
            updateDsContrDtl(msgMap);
        }

        // register IWR_RGTN_COND
        registerIwrRgtnCond(msgMap, svcMachMstrTMsg, apiResponseMsg, onBatchType, regsterUgwFlg);

        // update SVC_MACH_MSTR
        if (regsterUgwFlg) {
            updateSvcMachMstr(msgMap, shrdMachFlg);
        }
    }

    private String setMsg(String varCharConstNm, String defMsg, String glblCmpyCd) {
        String msg = ZYPCodeDataUtil.getVarCharConstValue(varCharConstNm, glblCmpyCd);
        if (!hasValue(msg)) {
            msg = defMsg;
        }
        return msg;
    }

    private String getIwrLinkMdseCd(NSZC049001PMsg param, String mdseCdForOrdTake) {
        String iwrLinkMdseCd = null;
        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = getOrdTakeMdse(param, mdseCdForOrdTake);
        // Mapping For Power Supply
        if (ordTakeMdseTMsg != null) {
            iwrLinkMdseCd = getIwrLinkMdseCdFromIerMdseLink(param, mdseCdForOrdTake);
        }
        return iwrLinkMdseCd;
    }

    private void updateSvcMachMstr(S21ApiMessageMap msgMap, boolean shrDlrFlg) {

        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg inTMsg = new SVC_MACH_MSTRTMsg();
        setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(inTMsg.svcMachMstrPk, param.svcMachMstrPk);
        inTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);

// START 2016/11/24 N.Arai [QC#15829, MOD]
        Map<String, String> beforParamMap = new HashMap<String, String>();
        beforParamMap.put(IWR_RGTN_STS_CD, inTMsg.iwrRgtnStsCd.getValue());
        beforParamMap.put(SHR_DLR_FLG, inTMsg.shrDlrFlg.getValue());
// END 2016/11/24 N.Arai [QC#15829, MOD]

        setValue(inTMsg.iwrRgtnStsCd, IWR_RGTN_STS.REGISTERED);
        if (shrDlrFlg) {
            setValue(inTMsg.shrDlrFlg, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(inTMsg.shrDlrFlg, ZYPConstant.FLG_OFF_N);
        }
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

    private void registerIwrRgtnCond(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String apiMsg, final ONBATCH_TYPE onBatchType, boolean regsterUgwFlg) {

        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();
        IWR_RGTN_CONDTMsg inTMsg = getIwrRgtnCond(param);
        if (inTMsg == null) {
            insertIwrRgtnCond(msgMap, svcMachMstrTMsg, apiMsg, regsterUgwFlg);
        } else {
            updateIwrRgtnCond(msgMap, svcMachMstrTMsg, inTMsg, apiMsg, regsterUgwFlg);
        }
    }

    private void updateIwrRgtnCond(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, IWR_RGTN_CONDTMsg inTMsg, String apiMsg, boolean regsterUgwFlg) {

        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();
        setValue(inTMsg.serNum, svcMachMstrTMsg.serNum);
        setValue(inTMsg.childSvcMachMstrPk, this.childSvcMachMstrPk);
        setValue(inTMsg.childSerNum, this.childSerNum);
        if (regsterUgwFlg) {
            setValue(inTMsg.iwrRgtnDt, param.slsDt);
        }
        setValue(inTMsg.ugwMsgTxt, cutStr(apiMsg, LENGTH_API_MSG));
        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM1047E);
        }
    }

    private void insertIwrRgtnCond(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String apiMsg, boolean regsterUgwFlg) {

        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();
        IWR_RGTN_CONDTMsg inTMsg = new IWR_RGTN_CONDTMsg();
        setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(inTMsg.iwrRgtnCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.IWR_RGTN_COND_SQ));
        setValue(inTMsg.svcMachMstrPk, param.svcMachMstrPk);
        setValue(inTMsg.serNum, svcMachMstrTMsg.serNum);
        setValue(inTMsg.childSvcMachMstrPk, this.childSvcMachMstrPk);
        setValue(inTMsg.childSerNum, this.childSerNum);
        if (regsterUgwFlg) {
            setValue(inTMsg.iwrRgtnDt, param.slsDt);
        }
        setValue(inTMsg.ugwMsgTxt, cutStr(apiMsg, LENGTH_API_MSG));
        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM1046E);
        }
    }

    private void updateDsContrDtl(S21ApiMessageMap msgMap) {
        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();
        List<Map<String, Object>> dsContrDtlList = getContrDtlInfo(param);
        // START 2016/10/25 Y.Zhang [QC#15437, MOD]
        // mod start 2016/12/07 CSA QC#16356
        // if contract info is not exist,there has an error
        //if (dsContrDtlList.size() == 0) {
        //    msgMap.addXxMsgId(NSZM1071E);
        //} else {
        for (Map<String, Object> dsContrDtlMap : dsContrDtlList) {
            DS_CONTR_DTLTMsg dsContrDtlMsg = new DS_CONTR_DTLTMsg();
            setValue(dsContrDtlMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(dsContrDtlMsg.dsContrDtlPk, (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
            dsContrDtlMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlMsg);
            setValue(dsContrDtlMsg.prevMtrReadMethCd, dsContrDtlMsg.mtrReadMethCd);
            setValue(dsContrDtlMsg.mtrReadMethCd, MTR_READ_METH.IMAGEWARE);
            S21ApiTBLAccessor.update(dsContrDtlMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM1048E);
            }
        }
        //}
        // mod end 2016/12/07 CSA QC#16356
    }
    // END 2016/10/25 Y.Zhang [QC#15437, MOD]

    private boolean callFsrUpdateApi(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, final ONBATCH_TYPE onBatchType) {

        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();
        // get Var Char Const
        String svcCallTp = ZYPCodeDataUtil.getVarCharConstValue(CONST_IWR_ISTL_SVC_CALL_TP, param.glblCmpyCd.getValue());
        String svcPblmTp = ZYPCodeDataUtil.getVarCharConstValue(CONST_IWR_ISTL_SVC_PBLM_TP, param.glblCmpyCd.getValue());
        String svcCallBillTp = ZYPCodeDataUtil.getVarCharConstValue(CONST_IWR_SVC_CALL_BILL_TP, param.glblCmpyCd.getValue());
        String custCllrTxt = ZYPCodeDataUtil.getVarCharConstValue(CONST_IWR_CUST_CLLR_TXT, param.glblCmpyCd.getValue());

        // check exist SVC_TASK
        if (existSvcTask(param, svcCallTp)) {
            return true;
        }

        NSZC043001PMsg fsrUpdatePMsg = new NSZC043001PMsg();
        // set Param
        setValue(fsrUpdatePMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(fsrUpdatePMsg.slsDt, param.slsDt);
        setValue(fsrUpdatePMsg.xxModeCd, NSZC043001Constant.MODE_CREATE_FSR);
        setValue(fsrUpdatePMsg.userId, param.usrId);
        setValue(fsrUpdatePMsg.svcMachMstrPk, param.svcMachMstrPk);
        setValue(fsrUpdatePMsg.svcCustAttnTxt, this.dsAcctNm);

        // set Customer Info
        setCustomerInfo(fsrUpdatePMsg, param, svcMachMstrTMsg);

        setValue(fsrUpdatePMsg.dsSvcCallTpCd, svcCallTp);
        setValue(fsrUpdatePMsg.svcBillTpCd, svcCallBillTp);
        setValue(fsrUpdatePMsg.svcTaskRcvDt, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(fsrUpdatePMsg.svcTaskRcvTm, ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_DT_TM).substring(HHMMDD_START, HHMMDD_END));
        setValue(fsrUpdatePMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        setValue(fsrUpdatePMsg.svcPblmTpCd, svcPblmTp);
        setValue(fsrUpdatePMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.IWR);
        setValue(fsrUpdatePMsg.svcCustCllrTxt, custCllrTxt);

        // Task List
        setValue(fsrUpdatePMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        fsrUpdatePMsg.taskList.setValidCount(1);

        new NSZC043001().execute(fsrUpdatePMsg, onBatchType);
        if (S21ApiUtil.isXxMsgId(fsrUpdatePMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(fsrUpdatePMsg);
            for (String xxMsgId : xxMsgIdList) {
                msgMap.addXxMsgId(xxMsgId);
            }
            return false;
        }
        return true;
    }

    private void setCustomerInfo(NSZC043001PMsg apiPMsg, NSZC049001PMsg param, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        List<Map<String, Object>> custInfoList = getMachineContactInfo(param);
        String custTelNum = null;
        String custTelExtnNum = null;
        String custEmlAddr = null;
        boolean setCustInfoFlg = false;
        for (Map<String, Object> custInfo : custInfoList) {
            if (DS_CTAC_PNT_TP.PHONE_WORK.equals(custInfo.get("DS_CTAC_PNT_TP_CD"))) {
                if (!hasValue(custTelNum)) {
                    custTelNum = (String) custInfo.get("DS_CTAC_PNT_VAL_TXT");
                }
                if (!hasValue(custTelExtnNum)) {
                    custTelExtnNum = (String) custInfo.get("DS_CTAC_PSN_EXTN_NUM");
                }
                setCustInfoFlg = true;
            } else if (!setCustInfoFlg && DS_CTAC_PNT_TP.PHONE_MOBILE.equals(custInfo.get("DS_CTAC_PNT_TP_CD"))) {
                if (!hasValue(custTelNum)) {
                    custTelNum = (String) custInfo.get("DS_CTAC_PNT_VAL_TXT");
                }
                if (!hasValue(custTelExtnNum)) {
                    custTelExtnNum = (String) custInfo.get("DS_CTAC_PSN_EXTN_NUM");
                }
            } else if (DS_CTAC_PNT_TP.EMAIL_WORK.equals(custInfo.get("DS_CTAC_PNT_TP_CD"))) {
                if (!hasValue(custEmlAddr)) {
                    custEmlAddr = (String) custInfo.get("DS_CTAC_PNT_VAL_TXT");
                }
            }
        }
        if (!hasValue(custTelNum)) {
            custTelNum = getDsCondConst(param, CONST_CUST_TEL_NUM).dsCondConstValTxt_01.getValue();
        }
        if (!hasValue(custTelExtnNum)) {
            custTelExtnNum = getDsCondConst(param, CONST_SVC_CUST_ATTN_TXT).dsCondConstValTxt_01.getValue();
        }
        if (!hasValue(custEmlAddr)) {
            custEmlAddr = getDsCondConst(param, CONST_CUST_EML_ADDR).dsCondConstValTxt_01.getValue();
        }
        setValue(apiPMsg.custTelExtnNum, custTelExtnNum);
        setValue(apiPMsg.custTelNum, custTelNum);
        setValue(apiPMsg.custEmlAddr, custEmlAddr);
    }

    private boolean checkParam(S21ApiMessageMap msgMap) {

        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();
        // check common mandatory parameter
        checkCommonParameter(msgMap, param.glblCmpyCd, NSZM0001E);
        checkCommonParameter(msgMap, param.slsDt, NSZM0002E);
        checkCommonParameter(msgMap, param.usrId, NSZM0293E);
        checkCommonParameter(msgMap, param.svcMachMstrPk, NSZM0074E);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void checkCommonParameter(S21ApiMessageMap msgMap, EZDPItem item, String messageId) {
        if (!hasValue(item)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private boolean checkInterfaceData(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String iwrLinkMdseCd, String mdseCdForOrdTake) {

        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();
        // check interface data
        if (svcMachMstrTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return false;
        }
        if (IWR_RGTN_STS.REGISTERED.equals(svcMachMstrTMsg.iwrRgtnStsCd.getValue()) || IWR_RGTN_STS.AWAITING_DEREGISTRATION.equals(svcMachMstrTMsg.iwrRgtnStsCd.getValue())) {
            msgMap.addXxMsgId(NSZM1038E);
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(svcMachMstrTMsg.shrDlrFlg.getValue())) {
            msgMap.addXxMsgId(NSZM1039E);
            return false;
        }
        // iwrRgtnCond
        if (existIwrRgtnCond(param)) {
            msgMap.addXxMsgId(NSZM1040E);
            return false;
        }
        // check Power Supply
        if (iwrLinkMdseCd != null) {
            List<Map<String, Object>> childSerNumList = getChildSerNum(param, iwrLinkMdseCd);
            if (childSerNumList.size() == 0) {
                msgMap.addXxMsgId(NSZM1041E);
                return false;
            } else if (childSerNumList.size() > 1) {
                msgMap.addXxMsgId(NSZM1042E);
                return false;
            } else {
                this.childSvcMachMstrPk = (BigDecimal) childSerNumList.get(0).get("SVC_MACH_MSTR_PK");
                this.childSerNum = (String) childSerNumList.get(0).get("SER_NUM");
            }
        }
        return true;
    }

    // mod start 2016/12/07 CSA QC#16356
    private String setInterfaceData(S21ApiMessageMap msgMap, SVC_MACH_MSTRTMsg svcMachMstrTMsg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, String iwrLinkMdseCd, String mdseCdForOrdTake, String installDate) {
    // mod end 2016/12/07 CSA QC#16356

        // add start 2016/12/07 CSA QC#16356
        NSZC049001PMsg param = (NSZC049001PMsg) msgMap.getPmsg();
        // add end 2016/12/07 CSA QC#16356

        // get var Char Info from VAR_CHAR_CONST
        String csvDblQuotFlg = ZYPCodeDataUtil.getVarCharConstValue(CONST_UGW_CSV_DBL_QUOT_FLG, param.glblCmpyCd.getValue());

        // START 2016/10/17 Y.Zhang [QC#15162, MOD]
        String lineFeedCode = ZYPCodeDataUtil.getVarCharConstValue(CONST_UGW_LINE_FEED_CD, param.glblCmpyCd.getValue());
        if (LF.equals(lineFeedCode)) {
            lineFeedCode = LF_CODE;
        } else if (CRLF.equals(lineFeedCode)) {
            lineFeedCode = CRLF_CODE;
        }
        // END 2016/10/17 Y.Zhang [QC#15162, MOD]
        // get Contract Number
        Map<String, Object> contrInfo = getContrNum(param);
        // add start 2016/12/07 CSA QC#16356
        if (contrInfo == null) {
            msgMap.addXxMsgId(NSZM1071E);
            return null;
        }
        // add end 2016/12/07 CSA QC#16356
        // get Machine Info
        Map<String, Object> svcMachInfo = getSvcMachInfo(param);
        if (svcMachInfo != null) {
            this.dsAcctNm = (String) svcMachInfo.get("DS_ACCT_NM");
        }

        // create Record line
        String lineData = null;
        StringBuilder csvDataBul = new StringBuilder();
        // create Version Record
        lineData = createVersionRecord(csvDblQuotFlg, ugwDefSetMsgArray);
        csvDataBul.append(lineData);
        // START 2016/10/17 Y.Zhang [QC#15162, MOD]
        csvDataBul.append(lineFeedCode);
        // END 2016/10/17 Y.Zhang [QC#15162, MOD]
        // create Customer1 Record
        lineData = createCust1Record(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg);
        csvDataBul.append(lineData);
        // START 2016/10/17 Y.Zhang [QC#15162, MOD]
        csvDataBul.append(lineFeedCode);
        // END 2016/10/17 Y.Zhang [QC#15162, MOD]
        // create Customer2 Record
        lineData = createCust2Record(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg);
        csvDataBul.append(lineData);
        // START 2016/10/17 Y.Zhang [QC#15162, MOD]
        csvDataBul.append(lineFeedCode);
        // END 2016/10/17 Y.Zhang [QC#15162, MOD]
        // create Administrator Record
        lineData = createAdminRecord(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg);
        csvDataBul.append(lineData);
        // START 2016/10/17 Y.Zhang [QC#15162, MOD]
        csvDataBul.append(lineFeedCode);
        // END 2016/10/17 Y.Zhang [QC#15162, MOD]
        // create Contract1 Record
        lineData = createContr1Record(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg, contrInfo);
        csvDataBul.append(lineData);
        // START 2016/10/17 Y.Zhang [QC#15162, MOD]
        csvDataBul.append(lineFeedCode);
        // END 2016/10/17 Y.Zhang [QC#15162, MOD]
        // create Contract2 Record
        lineData = createContr2Record(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg, contrInfo);
        csvDataBul.append(lineData);
        // START 2016/10/17 Y.Zhang [QC#15162, MOD]
        csvDataBul.append(lineFeedCode);
        // END 2016/10/17 Y.Zhang [QC#15162, MOD]
        // Mapping For Power Supply
//        if (iwrLinkMdseCd != null) {
            // create Version Record
//            lineData = createVersionRecord(csvDblQuotFlg, ugwDefSetMsgArray);
//            csvDataBul.append(lineData);
//            // START 2016/10/17 Y.Zhang [QC#15162, MOD]
//            csvDataBul.append(lineFeedCode);
            // END 2016/10/17 Y.Zhang [QC#15162, MOD]
            // create RDS Record
            lineData = createRdsRecord(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg, contrInfo, installDate);
            csvDataBul.append(lineData);
            // START 2016/10/17 Y.Zhang [QC#15162, MOD]
            csvDataBul.append(lineFeedCode);
            // END 2016/10/17 Y.Zhang [QC#15162, MOD]
            // create RDS2 Record
            lineData = createRds2Record(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg, contrInfo);
            csvDataBul.append(lineData);
            // START 2016/10/17 Y.Zhang [QC#15162, MOD]
            csvDataBul.append(lineFeedCode);
            // END 2016/10/17 Y.Zhang [QC#15162, MOD]
            // create Device Record
            IWR_MDL_MAPTMsg iwrMdlMapTMsg = getIwrMdlMap(param, mdseCdForOrdTake);
            lineData = createDvcRecord(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg, contrInfo, svcMachInfo, iwrMdlMapTMsg, installDate);
            csvDataBul.append(lineData);
            // START 2016/10/17 Y.Zhang [QC#15162, MOD]
            csvDataBul.append(lineFeedCode);
            // END 2016/10/17 Y.Zhang [QC#15162, MOD]
            // create Device2 Record
            lineData = createDvc2Record(csvDblQuotFlg, ugwDefSetMsgArray, svcMachMstrTMsg, contrInfo, svcMachInfo);
            csvDataBul.append(lineData);
            // START 2016/10/17 Y.Zhang [QC#15162, MOD]
            csvDataBul.append(lineFeedCode);
            // END 2016/10/17 Y.Zhang [QC#15162, MOD]
//        }
        return csvDataBul.toString();
    }

    private String createVersionRecord(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray) {
        VersionRecordBean verRecBean = new VersionRecordBean();
        verRecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        verRecBean.setRecordName(getUgwDefSetVal(COLUMN_VRSN_REC_NM, ugwDefSetMsgArray));
        verRecBean.setVersion(getUgwDefSetVal(COLUMN_VRSN_NUM, ugwDefSetMsgArray));
        return verRecBean.createLineData();
    }

    private String createCust1Record(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        Customer1RecordBean cust1RecBean = new Customer1RecordBean();
        cust1RecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        cust1RecBean.setRecordName(getUgwDefSetVal(COLUMN_CUST_NM, ugwDefSetMsgArray));
        cust1RecBean.setCustomerId(svcMachMstrTMsg.curLocAcctNum.getValue());
        cust1RecBean.setSalesOrganizationId(getUgwDefSetVal(COLUMN_ORG_ID, ugwDefSetMsgArray));
        cust1RecBean.setCustomerName(this.dsAcctNm);
        cust1RecBean.setCustomerAddress1(EMP);
        cust1RecBean.setCustomerAddress2(EMP);
        cust1RecBean.setCustomerCountry(getUgwDefSetVal(COLUMN_CUST_CTRY_NM, ugwDefSetMsgArray));
        cust1RecBean.setCustomerZip(EMP);
        cust1RecBean.setCustomerTelephone(EMP);
        cust1RecBean.setCustomerFax(EMP);
        // START 2016/10/25 Y.Zhang [QC#15437, MOD]
        cust1RecBean.setCustomerIndustry(COLUMN_CUST_INDY);
        cust1RecBean.setClosingDate(COLUMN_CUST_CLO_DAY);
        // END 2016/10/25 Y.Zhang [QC#15437, MOD]
        cust1RecBean.setBillingDepartment(EMP);
        cust1RecBean.setBillingContact(EMP);
        cust1RecBean.setBillingContactPhone(EMP);
        cust1RecBean.setBillingContactFax(EMP);
        cust1RecBean.setBillingContactEmail(EMP);
        cust1RecBean.setRdsContractingDept(EMP);
        cust1RecBean.setRdsContractingPerson(EMP);
        cust1RecBean.setRdsContractingPersonPhone(EMP);
        cust1RecBean.setRdsContractingPersonFax(EMP);
        cust1RecBean.setRdsContractingPersonEmail(EMP);
        // START 2016/10/25 Y.Zhang [QC#15437, MOD]
        cust1RecBean.setDepartmentCounter(COLUMN_DEPT_CNTR);
        // END 2016/10/25 Y.Zhang [QC#15437, MOD]
        return cust1RecBean.createLineData();
    }

    private String createCust2Record(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        Customer2RecordBean cust2RecBean = new Customer2RecordBean();
        cust2RecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        cust2RecBean.setRecordName(getUgwDefSetVal(COLUMN_CUST2_REC_NM, ugwDefSetMsgArray));
        cust2RecBean.setCustomerId(svcMachMstrTMsg.curLocAcctNum.getValue());
        cust2RecBean.setSalesOrganizationId(getUgwDefSetVal(COLUMN_ORG_ID, ugwDefSetMsgArray));
        cust2RecBean.setCustomerName(this.dsAcctNm);
        cust2RecBean.setCustomerAddress1(EMP);
        cust2RecBean.setCustomerAddress2(EMP);
        cust2RecBean.setCustomerAddress3(EMP);
        cust2RecBean.setCustomerAddress4(EMP);
        cust2RecBean.setProductionSetting(getUgwDefSetVal(COLUMN_CUST2_PRD_SET, ugwDefSetMsgArray));
        cust2RecBean.setFirmwareDistributionControlFlag(getUgwDefSetVal(COLUMN_CUST2_FIRM_DIST, ugwDefSetMsgArray));
        return cust2RecBean.createLineData();
    }

    private String createAdminRecord(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        AdministratorRecordBean adminRecBean = new AdministratorRecordBean();
        adminRecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        adminRecBean.setRecordName(getUgwDefSetVal(COLUMN_ADMIN_REC_NM, ugwDefSetMsgArray));
        adminRecBean.setAdministratorName(getUgwDefSetVal(COLUMN_ADMIN_NM, ugwDefSetMsgArray));
        adminRecBean.setSalesOrganizationId(getUgwDefSetVal(COLUMN_ORG_ID, ugwDefSetMsgArray));
        adminRecBean.setCustomerId(svcMachMstrTMsg.curLocAcctNum.getValue());
        adminRecBean.setAdministratorEmail(getUgwDefSetVal(COLUMN_ADMIN_EML_ADDL, ugwDefSetMsgArray));
        adminRecBean.setAdministratorPhone(EMP);
        adminRecBean.setAdministratorFax(EMP);
        adminRecBean.setAdministratorCell(EMP);
        adminRecBean.setAdministratorMemo(EMP);
        return adminRecBean.createLineData();
    }

    private String createContr1Record(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, Object> contrInfo) {
        Contract1RecordBean count1RecBean = new Contract1RecordBean();
        count1RecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        count1RecBean.setRecordName(getUgwDefSetVal(COLUMN_CONTR_REC_NM, ugwDefSetMsgArray));
        if (contrInfo != null) {
            count1RecBean.setContractNumber((String) contrInfo.get("DS_CONTR_NUM"));
        }
        count1RecBean.setSalesOrganizationId(getUgwDefSetVal(COLUMN_ORG_ID, ugwDefSetMsgArray));
        count1RecBean.setCustomerId(svcMachMstrTMsg.curLocAcctNum.getValue());
        count1RecBean.setContractCategory(EMP);
        count1RecBean.setServiceOption(getUgwDefSetVal(COLUMN_CONTR_SVC_OPT, ugwDefSetMsgArray));
        count1RecBean.setContractType(EMP);
        count1RecBean.setFuture1(EMP);
        count1RecBean.setFuture2(EMP);
        count1RecBean.setFuture3(EMP);
        count1RecBean.setNumberOfJam(EMP);
        count1RecBean.setRateOfJam1(EMP);
        count1RecBean.setRateOfJam2(EMP);
        // START 2016/10/25 Y.Zhang [QC#15437, MOD]
        // mod start 2016/11/04 CSA QC#15437
        String currentSystemTim = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT_ddMMyyyy);
        // mod end 2016/11/04 CSA QC#15437
        count1RecBean.setContractDate(currentSystemTim);
        count1RecBean.setContractStartDate(currentSystemTim);
        count1RecBean.setStartDateOfService(currentSystemTim);
        String endDate = addMonths(currentSystemTim, COLUMN_ADD_MONTH);
        count1RecBean.setEndDateOfService(endDate);
        count1RecBean.setTerm(COLUMN_CONTR_TERM);
        // END 2016/10/25 Y.Zhang [QC#15437, MOD]
        count1RecBean.setAvailableUnavailable(EMP);
        return count1RecBean.createLineData();
    }

    private String createContr2Record(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, Object> contrInfo) {
        Contract2RecordBean count2RecBean = new Contract2RecordBean();
        count2RecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        count2RecBean.setRecordName(getUgwDefSetVal(COLUMN_CONTR2_REC_NM, ugwDefSetMsgArray));
        if (contrInfo != null) {
            count2RecBean.setContractNumber((String) contrInfo.get("DS_CONTR_NUM"));
        }
        count2RecBean.setSalesOrganizationId(getUgwDefSetVal(COLUMN_ORG_ID, ugwDefSetMsgArray));
        count2RecBean.setCustomerId(svcMachMstrTMsg.curLocAcctNum.getValue());
        return count2RecBean.createLineData();
    }

    private String createRdsRecord(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, Object> contrInfo, String installDate) {
        RdsRecordBean rdsRecBean = new RdsRecordBean();
        rdsRecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        rdsRecBean.setRecordName(getUgwDefSetVal(COLUMN_RDS_REC_NM, ugwDefSetMsgArray));
        // START 2018/01/22 K.Kojima [QC#22827,MOD]
        // rdsRecBean.setRdsId(svcMachMstrTMsg.serNum.getValue());
        if (hasValue(this.childSerNum)) {
            rdsRecBean.setRdsId(this.childSerNum);
        } else {
            rdsRecBean.setRdsId(svcMachMstrTMsg.serNum.getValue());
        }
        // END 2018/01/22 K.Kojima [QC#22827,MOD]
        rdsRecBean.setSalesOrganizationId(getUgwDefSetVal(COLUMN_ORG_ID, ugwDefSetMsgArray));
        rdsRecBean.setCustomerId(svcMachMstrTMsg.curLocAcctNum.getValue());
        if (contrInfo != null) {
            rdsRecBean.setContractNumber((String) contrInfo.get("DS_CONTR_NUM"));
        }
        rdsRecBean.setLanguage(EMP);
        rdsRecBean.setContractCategory(EMP);
        rdsRecBean.setFutureUse1(getUgwDefSetVal(COLUMN_RDS_FUT_USE1, ugwDefSetMsgArray));
        rdsRecBean.setFutureUse2(getUgwDefSetVal(COLUMN_RDS_FUT_USE2, ugwDefSetMsgArray));
        rdsRecBean.setFutureUse3(getUgwDefSetVal(COLUMN_RDS_FUT_USE3, ugwDefSetMsgArray));
        rdsRecBean.setFutureUse4(getUgwDefSetVal(COLUMN_RDS_FUT_USE4, ugwDefSetMsgArray));
        rdsRecBean.setFutureUse5(getUgwDefSetVal(COLUMN_RDS_FUT_USE5, ugwDefSetMsgArray));
        rdsRecBean.setRdsVersion(getUgwDefSetVal(COLUMN_RDS_VRSN_NM, ugwDefSetMsgArray));
        rdsRecBean.setRdsIpAddress(EMP);
        rdsRecBean.setRdsEmailAddress(EMP);
        rdsRecBean.setRdsEventEmailAddress(EMP);
        rdsRecBean.setRdsTransmissionEmailAddress(EMP);
        rdsRecBean.setRdsLocation1(EMP);
        rdsRecBean.setRdsLocation2(EMP);
        rdsRecBean.setRdsLocation3(EMP);
        rdsRecBean.setRdsCity(EMP);
        rdsRecBean.setRdsCountry(getUgwDefSetVal(COLUMN_RDS_CTRY_NM, ugwDefSetMsgArray));
        rdsRecBean.setRdsZip(EMP);
        rdsRecBean.setRdsAdministrator(EMP);
        rdsRecBean.setRdsAdministratorPhone(EMP);
        rdsRecBean.setRdsAdministratorFax(EMP);
        rdsRecBean.setRdsAdministratorEmail(EMP);
        rdsRecBean.setConsumableAdministrator(EMP);
        rdsRecBean.setConsumableAdministratorPhone(EMP);
        rdsRecBean.setConsumableAdministratorFax(EMP);
        rdsRecBean.setConsumableAdministratorEmail(EMP);
        // START 2016/10/25 Y.Zhang [QC#15437, MOD]
        // mod start 2016/11/04 CSA QC#15437
        rdsRecBean.setInstallDate(formatDDMMYYYY(installDate));
        // mod end 2016/11/04 CSA QC#15437
        // END 2016/10/25 Y.Zhang [QC#15437, MOD]
        rdsRecBean.setUninstallDate(EMP);
        rdsRecBean.setDispatcherEmail(EMP);
        rdsRecBean.setConsumablePruchasingEmail(EMP);
        rdsRecBean.setRdsServerName(EMP);
        rdsRecBean.setAvailableUnavailable(getUgwDefSetVal(COLUMN_RDS_AVAL_CD, ugwDefSetMsgArray));
        return rdsRecBean.createLineData();
    }

    private String createRds2Record(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, Object> contrInfo) {
        Rds2RecordBean rds2RecBean = new Rds2RecordBean();
        rds2RecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        rds2RecBean.setRecordName(getUgwDefSetVal(COLUMN_RDS2_REC_NM, ugwDefSetMsgArray));
        // START 2018/01/22 K.Kojima [QC#22394,MOD]
        // rds2RecBean.setRdsId(svcMachMstrTMsg.serNum.getValue());
        if (hasValue(this.childSerNum)) {
            rds2RecBean.setRdsId(this.childSerNum);
        } else {
            rds2RecBean.setRdsId(svcMachMstrTMsg.serNum.getValue());
        }
        // END 2018/01/22 K.Kojima [QC#22394,MOD]
        rds2RecBean.setSalesOrganizationId(getUgwDefSetVal(COLUMN_ORG_ID, ugwDefSetMsgArray));
        rds2RecBean.setCustomerId(svcMachMstrTMsg.curLocAcctNum.getValue());
        if (contrInfo != null) {
            rds2RecBean.setContractNumber((String) contrInfo.get("DS_CONTR_NUM"));
        }
        rds2RecBean.setRdsLocation1(EMP);
        rds2RecBean.setRdsLocation2(EMP);
        rds2RecBean.setRdsLocation3(EMP);
        rds2RecBean.setRdsLocation4(EMP);
        rds2RecBean.setMailserverAdministrator(EMP);
        rds2RecBean.setMailserverAdministratorPhone(EMP);
        rds2RecBean.setMailserverAdministratorFax(EMP);
        rds2RecBean.setMailserverAdministratorEmail(EMP);
        rds2RecBean.setTimeDifference(EMP);
        rds2RecBean.setDstStartAvailability(EMP);
        rds2RecBean.setDstStartMonth(EMP);
        rds2RecBean.setDstStartWeek(EMP);
        rds2RecBean.setDstStartDay(EMP);
        rds2RecBean.setDstStartTime(EMP);
        rds2RecBean.setDstEndMonth(EMP);
        rds2RecBean.setDstEndWeek(EMP);
        rds2RecBean.setDstEndDay(EMP);
        rds2RecBean.setDstEndTime(EMP);
        rds2RecBean.setServiceOptionBilling(getUgwDefSetVal(COLUMN_RDS2_SVC_OPT_BLLG, ugwDefSetMsgArray));
        rds2RecBean.setServiceOptionJam(getUgwDefSetVal(COLUMN_RDS2_SVC_OPT_JAM, ugwDefSetMsgArray));
        rds2RecBean.setServiceOptionOperationReport(getUgwDefSetVal(COLUMN_RDS2_SVC_OPT_OP, ugwDefSetMsgArray));
        rds2RecBean.setServiceOptionConsumbable(getUgwDefSetVal(COLUMN_RDS2_SVC_OPT_CNSMR, ugwDefSetMsgArray));
        rds2RecBean.setServiceOptionConsumbableOutOfStock(getUgwDefSetVal(COLUMN_RDS2_SVC_OPT_OUT_STK, ugwDefSetMsgArray));
        rds2RecBean.setPingSetting(EMP);
        rds2RecBean.setRemoteModuleUpdate(EMP);
        rds2RecBean.setRdsSubnetMask(EMP);
        rds2RecBean.setRdsGatewayAddress(EMP);
        rds2RecBean.setRdsDnsAddress(EMP);
        rds2RecBean.setAgentNtpAddress(EMP);
        rds2RecBean.setSmtpAddress(EMP);
        rds2RecBean.setSmtpPortNumber(EMP);
        rds2RecBean.setSmtpAuthentication(EMP);
        rds2RecBean.setSmtpAuthenticationUser(EMP);
        rds2RecBean.setSmtpAuthenticationPassword(EMP);
        rds2RecBean.setServerType1(EMP);
        rds2RecBean.setPopUsage(EMP);
        rds2RecBean.setPopServerAddress(EMP);
        rds2RecBean.setPopPortNumber(EMP);
        rds2RecBean.setPopAuthentication(EMP);
        rds2RecBean.setPopAuthenticationUser(EMP);
        rds2RecBean.setPopAuthenticationPassword(EMP);
        rds2RecBean.setServerType2(EMP);
        rds2RecBean.setRdsFaultNotification(EMP);
        rds2RecBean.setRdsLowTonerNotificationAdmin(EMP);
        rds2RecBean.setRdsLowTonerNotificationUgw(EMP);
        rds2RecBean.setDetailedCounterTransmission(getUgwDefSetVal(COLUMN_RDS2_DTL_CNTR_TRSMT, ugwDefSetMsgArray));
        rds2RecBean.setPaperSizeCounterTransmission(getUgwDefSetVal(COLUMN_RDS2_PAPER_SIZE_TRSMT, ugwDefSetMsgArray));
        rds2RecBean.setPartsCounterTransmission(getUgwDefSetVal(COLUMN_RDS2_PART_CNTR_TRSMT, ugwDefSetMsgArray));
        rds2RecBean.setDetailedJamCounterTransmission(getUgwDefSetVal(COLUMN_RDS2_JAM_CNTR_TRSMT, ugwDefSetMsgArray));
        rds2RecBean.setAlarmLogTransmission(getUgwDefSetVal(COLUMN_RDS2_ALARM_LOG_TRSMT, ugwDefSetMsgArray));
        rds2RecBean.setRomVersiionTransmission(getUgwDefSetVal(COLUMN_RDS2_ROM_VRSN_TRSMT, ugwDefSetMsgArray));
        rds2RecBean.setCounterTransmissionByFunction(getUgwDefSetVal(COLUMN_RDS2_CNTR_FUNC_TRSMT, ugwDefSetMsgArray));
        rds2RecBean.setConsumableCounterTransmission(getUgwDefSetVal(COLUMN_RDS2_CNSMR_CNTR_TRSMT, ugwDefSetMsgArray));
        rds2RecBean.setDebugLogTransmission(getUgwDefSetVal(COLUMN_RDS2_DEBUG_LOG_TRSMT, ugwDefSetMsgArray));
        rds2RecBean.setDebugLogTransmissionEmail(EMP);
        rds2RecBean.setDebugLogEmailSubject(EMP);
        rds2RecBean.setDebugLogEmailBody(EMP);
        rds2RecBean.setHeartbeatNotification(EMP);
        rds2RecBean.setHeartbeatNotificationEmail(EMP);
        rds2RecBean.setHeartbeatNotificationEmailSubject(EMP);
        rds2RecBean.setHeartbeatNotificationEmailBody(EMP);
        rds2RecBean.setHeartbeatNotificationEmailTime1(EMP);
        rds2RecBean.setHeartbeatNotificationEmailTime2(EMP);
        rds2RecBean.setCcSettings(EMP);
        rds2RecBean.setDhcpSettings(EMP);
        rds2RecBean.setSecondDnsServer(EMP);
        rds2RecBean.setFirstWinsServer(EMP);
        rds2RecBean.setSecondWinsServer(EMP);
        rds2RecBean.setNetworkConnection(EMP);
        rds2RecBean.setPortNumber(EMP);
        rds2RecBean.setDestinationUrl(EMP);
        rds2RecBean.setDestionPortNumber(EMP);
        rds2RecBean.setProxyServerSetting(EMP);
        rds2RecBean.setProxyServerAddress(EMP);
        rds2RecBean.setProxyNumberPort(EMP);
        rds2RecBean.setProxyAuthenticationSetting(EMP);
        rds2RecBean.setProxyAuthenticationUser(EMP);
        rds2RecBean.setProxyAuthenticationPassword(EMP);
        rds2RecBean.setRdsMacAddress(EMP);
        rds2RecBean.setSpecifiedNumberOfAlarmOccurances(EMP);

        return rds2RecBean.createLineData();
    }

    private String createDvcRecord(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, Object> contrInfo, Map<String, Object> svcMachInfo, IWR_MDL_MAPTMsg iwrMdlMapTMsg,
            String installDate) {
        DeviceRecordBean dvcRecBean = new DeviceRecordBean();
        dvcRecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        dvcRecBean.setRecordName(getUgwDefSetVal(COLUMN_DVC_REC_NM, ugwDefSetMsgArray));
        if (ZYPCommonFunc.hasValue(this.childSerNum)) {
            dvcRecBean.setDeviceId(this.childSerNum);
            dvcRecBean.setDeviceName(this.childSerNum);
        } else {
            dvcRecBean.setDeviceId(svcMachMstrTMsg.serNum.getValue());
            dvcRecBean.setDeviceName(svcMachMstrTMsg.serNum.getValue());
        }
        dvcRecBean.setSalesOrganizationId(getUgwDefSetVal(COLUMN_ORG_ID, ugwDefSetMsgArray));
        dvcRecBean.setCustomerId(svcMachMstrTMsg.curLocAcctNum.getValue());
        if (ZYPCommonFunc.hasValue(this.childSerNum)) {
            dvcRecBean.setRdsId(this.childSerNum);
        } else {
            dvcRecBean.setRdsId(svcMachMstrTMsg.serNum.getValue());
        }
        dvcRecBean.setStockEntryLcoation(EMP);
        if (iwrMdlMapTMsg != null) {
            dvcRecBean.setModelName(iwrMdlMapTMsg.iwrMdlNm.getValue());
        }
        dvcRecBean.setMercuryCodeName(EMP);
        dvcRecBean.setContractCategory(EMP);
        dvcRecBean.setFixedValue1(getUgwDefSetVal(COLUMN_DVC_FIX_VAL1, ugwDefSetMsgArray));
        dvcRecBean.setFixedValue2(getUgwDefSetVal(COLUMN_DVC_FIX_VAL2, ugwDefSetMsgArray));
        dvcRecBean.setFixedValue3(getUgwDefSetVal(COLUMN_DVC_FIX_VAL3, ugwDefSetMsgArray));
        dvcRecBean.setFixedValue4(getUgwDefSetVal(COLUMN_DVC_FIX_VAL4, ugwDefSetMsgArray));
        dvcRecBean.setFixedValue5(getUgwDefSetVal(COLUMN_DVC_FIX_VAL5, ugwDefSetMsgArray));
        dvcRecBean.setGuarnteeContractType(EMP);
        // mod start 2016/11/04 CSA QC#15437
        // START 2016/10/26 Y.Zhang [QC#15437, MOD]
        dvcRecBean.setContractStartDate(formatDDMMYYYY(installDate));
        String calcuYear = calcuDate(ugwDefSetMsgArray, installDate);
        // END 2016/10/26 Y.Zhang [QC#15437, MOD]
        dvcRecBean.setContractEndDate(formatDDMMYYYY(calcuYear));
        // mod end 2016/11/04 CSA QC#15437
        dvcRecBean.setDaUnitExistance(getUgwDefSetVal(COLUMN_DVC_UNIT_EXST, ugwDefSetMsgArray));
        dvcRecBean.setDaId(EMP);
        dvcRecBean.setDaVersion(EMP);
        dvcRecBean.setIpaddress(EMP);
        dvcRecBean.setMacAddress(EMP);
        dvcRecBean.setErrorNotification(getUgwDefSetVal(COLUMN_DVC_ERR_NTFY, ugwDefSetMsgArray));
        dvcRecBean.setJamOccuranceNumberNotification(getUgwDefSetVal(COLUMN_DVC_JAM_NUM_NTFY, ugwDefSetMsgArray));
        dvcRecBean.setJamOccuranceRateNotification(getUgwDefSetVal(COLUMN_DVC_JAM_RATE_NTFY, ugwDefSetMsgArray));
        dvcRecBean.setSpecifiedNumberOfJamOccurances(EMP);
        dvcRecBean.setSpecifidJamOccuranceRate1(EMP);
        dvcRecBean.setSpecifidJamOccuranceRate2(EMP);
        // mod start 2016/11/04 CSA QC#15437
        // START 2016/10/26 Y.Zhang [QC#15437, MOD]
        dvcRecBean.setInstallDate(formatDDMMYYYY(installDate));
        // END 2016/10/26 Y.Zhang [QC#15437, MOD]
        dvcRecBean.setUninstallDate(formatDDMMYYYY(calcuYear));
        // mod end 2016/11/04 CSA QC#15437
        if (svcMachInfo != null) {
            dvcRecBean.setDeviceLocation1((String) svcMachInfo.get("FIRST_LINE_ADDR"));
            dvcRecBean.setDeviceLocation2((String) svcMachInfo.get("SCD_LINE_ADDR"));
            dvcRecBean.setDeviceLocationCity((String) svcMachInfo.get("CTY_ADDR"));
            dvcRecBean.setDeviceLocationZip((String) svcMachInfo.get("POST_CD"));
        }
        dvcRecBean.setDeviceLocationCountry(getUgwDefSetVal(COLUMN_DVC_CTRY_NM, ugwDefSetMsgArray));
        dvcRecBean.setDeviceLocation3(EMP);
        dvcRecBean.setDeviceAdministrator(EMP);
        dvcRecBean.setDeviceAdministratorPhone(EMP);
        dvcRecBean.setDeviceAdministratorFax(EMP);
        dvcRecBean.setDeviceAdministratorCell(EMP);
        dvcRecBean.setDeviceAdministratorEmail(EMP);
        dvcRecBean.setPortNumber(getUgwDefSetVal(COLUMN_DVC_PORT_NUM, ugwDefSetMsgArray));
        dvcRecBean.setAvailableUnavailable(getUgwDefSetVal(COLUMN_DVC_AVAL_CD, ugwDefSetMsgArray));
        dvcRecBean.setTonerAdministrator(EMP);
        return dvcRecBean.createLineData();
    }

    private String calcuDate(UGW_DEF_SETTMsgArray ugwDefSetMsgArray, String calcuDate) {
        Calendar contrEndDateCalendar = Calendar.getInstance();
        Date inParamDate = toDate(calcuDate, DATE_FORMAT);
        contrEndDateCalendar.setTime(inParamDate);
        if (hasValue(getUgwDefSetVal(COLUMN_DVC_EFF_TERM_YR, ugwDefSetMsgArray))) {
            int addYear = Integer.valueOf(getUgwDefSetVal(COLUMN_DVC_EFF_TERM_YR, ugwDefSetMsgArray));
            contrEndDateCalendar.add(Calendar.YEAR, addYear);
            SimpleDateFormat formatYyyymmdd = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            return formatYyyymmdd.format(contrEndDateCalendar.getTime());
        } else {
            return calcuDate;
        }
    }

    private String createDvc2Record(String csvDblQuotFlg, UGW_DEF_SETTMsgArray ugwDefSetMsgArray, SVC_MACH_MSTRTMsg svcMachMstrTMsg, Map<String, Object> contrInfo, Map<String, Object> svcMachInfo) {
        Device2RecordBean dvc2RecBean = new Device2RecordBean();
        dvc2RecBean.setCsvDblQoutFlg(csvDblQuotFlg);
        dvc2RecBean.setRecordName(getUgwDefSetVal(COLUMN_DVC2_REC_NM, ugwDefSetMsgArray));
        if (ZYPCommonFunc.hasValue(this.childSerNum)) {
            dvc2RecBean.setDeviceId(this.childSerNum);
            dvc2RecBean.setDeviceName(this.childSerNum);
        } else {
            dvc2RecBean.setDeviceId(svcMachMstrTMsg.serNum.getValue());
            dvc2RecBean.setDeviceName(svcMachMstrTMsg.serNum.getValue());
        }
        dvc2RecBean.setSalesOrganizationId(getUgwDefSetVal(COLUMN_ORG_ID, ugwDefSetMsgArray));
        dvc2RecBean.setCustomerId(svcMachMstrTMsg.curLocAcctNum.getValue());
        if (ZYPCommonFunc.hasValue(this.childSerNum)) {
            dvc2RecBean.setRdsId(this.childSerNum);
        } else {
            dvc2RecBean.setRdsId(svcMachMstrTMsg.serNum.getValue());
        }
        if (svcMachInfo != null) {
            dvc2RecBean.setDeviceLocation1((String) svcMachInfo.get("FIRST_LINE_ADDR"));
            dvc2RecBean.setDeviceLocation2((String) svcMachInfo.get("SCD_LINE_ADDR"));
            if (svcMachInfo.get("CTY_ADDR") != null && svcMachInfo.get("ST_CD") != null) {
                dvc2RecBean.setDeviceLocation4(ZYPCommonFunc.concatString((String) svcMachInfo.get("CTY_ADDR"), ", ", (String) svcMachInfo.get("ST_CD")));
            } else if (svcMachInfo.get("CTY_ADDR") != null) {
                dvc2RecBean.setDeviceLocation4((String) svcMachInfo.get("CTY_ADDR"));
            } else if (svcMachInfo.get("CTY_ADDR") == null && svcMachInfo.get("ST_CD") != null) {
                dvc2RecBean.setDeviceLocation4((String) svcMachInfo.get("ST_CD"));
            }
        }
        dvc2RecBean.setDeviceLocation3(EMP);
        dvc2RecBean.setTonerDeliveryContact(EMP);
        dvc2RecBean.setTonerDeliveryContactPhone(EMP);
        dvc2RecBean.setTonerDeliveryContactFax(EMP);
        dvc2RecBean.setTonerDeliveryContactEmail(EMP);
        dvc2RecBean.setDeliveryAddress1(EMP);
        dvc2RecBean.setDeliveryAddress2(EMP);
        dvc2RecBean.setDeliveryAddress3(EMP);
        dvc2RecBean.setDeliveryAddress4(EMP);
        dvc2RecBean.setDeliveryZip(EMP);
        dvc2RecBean.setServiceOptionBilling(getUgwDefSetVal(COLUMN_DVC2_SVC_OPT_BLLG, ugwDefSetMsgArray));
        dvc2RecBean.setServiceOptionError(getUgwDefSetVal(COLUMN_DVC2_SVC_OPT_ERR, ugwDefSetMsgArray));
        dvc2RecBean.setServiceOptionOperationReport(getUgwDefSetVal(COLUMN_DVC2_SVC_OPT_OP, ugwDefSetMsgArray));
        dvc2RecBean.setServiceOptionOutOfStock(getUgwDefSetVal(COLUMN_DVC2_SVC_OPT_OUT_STK, ugwDefSetMsgArray));
        dvc2RecBean.setServiceOptionConsumable(getUgwDefSetVal(COLUMN_DVC2_SVC_OPT_CNSMR, ugwDefSetMsgArray));
        dvc2RecBean.setSubnetMask(EMP);
        dvc2RecBean.setEnableEfiController(getUgwDefSetVal(COLUMN_DVC2_ENBL_EFI_CTRL, ugwDefSetMsgArray));
        dvc2RecBean.setDaUnitMacAddress(EMP);
        dvc2RecBean.setDaUnitIpAddress(EMP);
        dvc2RecBean.setDaSubnetMask(EMP);
        dvc2RecBean.setDaGateway(EMP);
        dvc2RecBean.setIpcConnectionAvailability(getUgwDefSetVal(COLUMN_DVC2_IPC_CONN_AVAL, ugwDefSetMsgArray));
        dvc2RecBean.setIpcCounterType1(EMP);
        dvc2RecBean.setIpcCounterType2(EMP);
        dvc2RecBean.setIpcCounterType3(EMP);
        dvc2RecBean.setIpcCounterType4(EMP);
        dvc2RecBean.setIpcCounterType5(EMP);
        dvc2RecBean.setIpcCounterType6(EMP);
        dvc2RecBean.setServiceType(getUgwDefSetVal(COLUMN_DVC2_SVC_TP, ugwDefSetMsgArray));

        return dvc2RecBean.createLineData();
    }

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
        } catch (RemoteException e) {
            EZDDebugOutput.println(1, MSG_ERR, this);
        } catch (FaultResourceExp e) {
            EZDDebugOutput.println(1, MSG_ERR, this);
        } catch (FaultDataExp e) {
            EZDDebugOutput.println(1, MSG_ERR, this);
        } catch (FaultConfigurationExp e) {
            EZDDebugOutput.println(1, MSG_ERR, this);
        } catch (Exception e) {
            EZDDebugOutput.println(1, MSG_ERR, this);
        }

        return csvDataOut;
    }

    private Map<String, Object> getContrNum(NSZC049001PMsg param) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("svcMachMstrPk", param.svcMachMstrPk.getValue());
        ssmParam.put("slsDt", param.slsDt.getValue());
        ssmParam.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.CANCELLED);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getContrNum", ssmParam);
    }

    private Map<String, Object> getSvcMachInfo(NSZC049001PMsg param) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("svcMachMstrPk", param.svcMachMstrPk.getValue());
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcMachInfo", ssmParam);
    }

    private List<Map<String, Object>> getChildSerNum(NSZC049001PMsg param, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("svcMachMstrPk", param.svcMachMstrPk.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getChildSerNum", ssmParam);
    }

    private List<Map<String, Object>> getMachineContactInfo(NSZC049001PMsg param) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("svcMachMstrPk", param.svcMachMstrPk.getValue());
        ssmParam.put("slsDt", param.slsDt.getValue());
        String[] dsCtacPntTpList = new String[] {DS_CTAC_PNT_TP.PHONE_WORK, DS_CTAC_PNT_TP.PHONE_MOBILE, DS_CTAC_PNT_TP.EMAIL_WORK };
        ssmParam.put("dsCtacPntTp", dsCtacPntTpList);
        ssmParam.put("svcCtacTpCd", SVC_CTAC_TP.DELIVERY_CONTACT);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMachineContactInfo", ssmParam);
    }

    private List<Map<String, Object>> getContrDtlInfo(NSZC049001PMsg param) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("svcMachMstrPk", param.svcMachMstrPk.getValue());
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        ssmParam.put("slsDt", param.slsDt.getValue());
        ssmParam.put("mtrReadMethCd", MTR_READ_METH.IMAGEWARE);
        ssmParam.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.CANCELLED);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrDtlInfo", ssmParam);
    }

    private UGW_DEF_SETTMsgArray getUgwDefSet(NSZC049001PMsg param) {

        UGW_DEF_SETTMsg inMsg = new UGW_DEF_SETTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("actvFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("effFromDt01", param.slsDt.getValue());
        inMsg.setConditionValue("effThruDt01", param.slsDt.getValue());
        UGW_DEF_SETTMsgArray tMsgAry = (UGW_DEF_SETTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return tMsgAry;
    }

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

    private ORD_TAKE_MDSETMsg getOrdTakeMdse(NSZC049001PMsg param, String mdseCdForOrdTake) {
        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        setValue(ordTakeMdseTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(ordTakeMdseTMsg.ordTakeMdseCd, mdseCdForOrdTake);
        return (ORD_TAKE_MDSETMsg) S21ApiTBLAccessor.findByKey(ordTakeMdseTMsg);
    }

    private IWR_MDL_MAPTMsg getIwrMdlMap(NSZC049001PMsg param, String mdseCdForOrdTake) {
        IWR_MDL_MAPTMsg iwrMdlMapTMsg = new IWR_MDL_MAPTMsg();
        setValue(iwrMdlMapTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(iwrMdlMapTMsg.mdseCd, mdseCdForOrdTake);
        return (IWR_MDL_MAPTMsg) S21ApiTBLAccessor.findByKey(iwrMdlMapTMsg);
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(NSZC049001PMsg param) {
        SVC_MACH_MSTRTMsg inTMsg = new SVC_MACH_MSTRTMsg();
        setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(inTMsg.svcMachMstrPk, param.svcMachMstrPk);
        SVC_MACH_MSTRTMsg outTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    // START 2016/10/26 Y.Zhang [QC#15437, MOD]
    /**
     * getInstallDate
     * @param SVC_MACH_MSTRTMsg svcMachMstrTMsg
     * @param NSZC049001PMsg param
     * @return Map
     */
    private Map<String, Object> getInstallDate(SVC_MACH_MSTRTMsg svcMachMstrTMsg, NSZC049001PMsg param) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("soNum", svcMachMstrTMsg.soNum.getValue());
        ssmParam.put("soSlpNum", svcMachMstrTMsg.soSlpNum.getValue());
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getInstallDate", ssmParam);
    }

    /**
     * addMonths
     * @param date String
     * @param numberOfMonths int
     * @return String
     */
    public static String addMonths(String date, int numberOfMonths) {
        // mod start 2016/11/09 CSA QC#15437
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_ddMMyyyy);
        // mod end 2016/11/09 CSA QC#15437
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MONTH, numberOfMonths);
        return format.format(cal.getTime());
    }
    // END 2016/10/26 Y.Zhang [QC#15437, MOD]

    private DS_COND_CONSTTMsg getDsCondConst(NSZC049001PMsg param, String dsCondConstCd) {
        DS_COND_CONSTTMsg inTMsg = new DS_COND_CONSTTMsg();
        setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(inTMsg.dsCondConstGrpId, DS_COND_CONST_GRP_ID);
        setValue(inTMsg.dsCondConstCd, dsCondConstCd);
        DS_COND_CONSTTMsg outTMsg = (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    private boolean existIwrRgtnCond(NSZC049001PMsg param) {

        IWR_RGTN_CONDTMsg inMsg = new IWR_RGTN_CONDTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMachMstrPk01", param.svcMachMstrPk.getValue());
        if (S21ApiTBLAccessor.count(inMsg) == 0) {
            return false;
        }
        return true;
    }

    private IWR_RGTN_CONDTMsg getIwrRgtnCond(NSZC049001PMsg param) {

        IWR_RGTN_CONDTMsg inMsg = new IWR_RGTN_CONDTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMachMstrPk01", param.svcMachMstrPk.getValue());
        IWR_RGTN_CONDTMsgArray inArray = (IWR_RGTN_CONDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (inArray.getValidCount() > 0) {
            return inArray.no(0);
        }
        return null;
    }

    private boolean existSvcTask(NSZC049001PMsg param, String dsSvcCallTpCd) {

        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        inMsg.setSQLID("014");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsSvcCallTpCd01", dsSvcCallTpCd);
        inMsg.setConditionValue("svcTaskStsCd01A", SVC_TASK_STS.OPEN);
        inMsg.setConditionValue("svcTaskStsCd01B", SVC_TASK_STS.IN_PROCESS);
        inMsg.setConditionValue("svcMachMstrPk01", param.svcMachMstrPk.getValue());
        if (S21ApiTBLAccessor.count(inMsg) == 0) {
            return false;
        }
        return true;
    }

    private String getIwrLinkMdseCdFromIerMdseLink(NSZC049001PMsg param, String mdseCd) {

        IWR_MDSE_LINKTMsg inMsg = new IWR_MDSE_LINKTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("mdseCd01", mdseCd);
        inMsg.setConditionValue("actvFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("effFromDt01", param.slsDt.getValue());
        inMsg.setConditionValue("effThruDt01", param.slsDt.getValue());
        IWR_MDSE_LINKTMsgArray outMsgArray = (IWR_MDSE_LINKTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() > 0) {
            return outMsgArray.no(0).iwrLinkMdseCd.getValue();
        }
        return null;
    }

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

    private String cutStr(String str, int length) {
        if (!hasValue(str)) {
            return null;
        } else if (str.length() > length) {
            return str.substring(0, length);
        }
        return str;
    }

    private Date toDate(String fromTs, String formatFrom) {

        if (!hasValue(fromTs)) {
            return null;
        }
        SimpleDateFormat parser = new SimpleDateFormat(formatFrom);
        parser.setLenient(false);

        try {
            return parser.parse(fromTs);
        } catch (ParseException e) {
            return null;
        }
    }
    private void setRespMsg(String serNum, String respMsg) {
        if (!ZYPCommonFunc.hasValue(this.respMsgFlg) || ZYPConstant.FLG_ON_Y.equals(this.respMsgFlg)) {
            String msg = S21MessageFunc.clspGetMessage(NSZM1068I, new String[] {serNum, respMsg});
            S21InfoLogOutput.println(msg);
        }
    }

    // mod start 2016/11/04 CSA QC#15437
    /**
     * @param strDate String
     * @return strDateDDMMYYYY String
     */
    public static String formatDDMMYYYY(String strDate) {

        if (!hasValue(strDate)) {
            return null;
        }
        String yyyy = strDate.substring(0, 4);
        String mm = strDate.substring(4, 6);
        String dd = strDate.substring(6, 8);

        StringBuffer dt = new StringBuffer();
        dt.append(dd);
        dt.append(mm);
        dt.append(yyyy);

        return dt.toString();
    }
    // mod end 2016/11/04 CSA QC#15437

// START 2016/11/24 N.Arai [QC#15829, MOD]
    private boolean insertSvcMachMstrTrk(NSZC049001PMsg param, Map<String, String> beforMap, SVC_MACH_MSTRTMsg newTMsg) {

        if (!setSvcMachMstrTrk(param, IWR_RGTN_STS_CD, beforMap.get(IWR_RGTN_STS_CD), newTMsg.iwrRgtnStsCd.getValue())) {
            return false;
        }
        if (!setSvcMachMstrTrk(param, SHR_DLR_FLG, beforMap.get(SHR_DLR_FLG), newTMsg.shrDlrFlg.getValue())) {
            return false;
        }
        return true;

    }

    private boolean setSvcMachMstrTrk(NSZC049001PMsg param, String updFld, String oldVal, String newVal) {

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
        setValue(tMsg.updUsrId, param.usrId);
        setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.create(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
// END 2016/11/24 N.Arai [QC#15829, MOD]
}
