/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB007001;

import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.COA_MDSE_TP_CD;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.PROC_MODE_41;
import static com.canon.cusa.s21.batch.NLE.NLEB007001.constant.NLEB007001Constant.SQLID;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.COA_PROJTMsg;
import business.db.CPOTMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.DS_ASSET_MSTRTMsgArray;
import business.db.DS_RTL_WH_VTMsg;
import business.db.DS_RTL_WH_VTMsgArray;
import business.db.INVTMsg;
import business.db.MDSETMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_MTH_CTRL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NLEB007001
 * Asset Creation Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Hitachi         J.Kim           Create          N/A
 * 2016/04/12   Hitachi         J.Kim           Update          QC#6949
 * 2016/04/15   Hitachi         T.Tsuchida      Update          QC#7140
 * 2016/04/27   Hitachi         J.Kim           Update          QC#7537
 * 2016/04/28   Hitachi         J.Kim           Update          QC#7605
 * 2016/05/09   Hitachi         T.Tsuchida      Update          QC#7743
 * 2016/05/12   Hitachi         T.Tsuchida      Update          QC#7773
 * 2016/05/19   Hitachi         T.Tsuchida      Update          QC#8510
 * 2016/06/06   Hitachi         T.Tsuchida      Update          QC#9153
 * 2016/07/08   Hitachi         J.Kim           Update          QC#11627
 * 2017/11/17   Hitachi         J.Kim           Update          QC#17088
 * 2017/12/08   Hitachi         J.Kim           Update          QC#18127
 * 2018/02/19   Hitachi         J.Kim           Update          QC#23431
 * </pre>
 */
public final class NLEB007001Query extends S21SsmEZDQuerySupport {

    /** */
    private static final NLEB007001Query MYINSTANCE = new NLEB007001Query();

    /**
     * Constructor.
     */
    private NLEB007001Query() {
        super();
    }

    /**
     * @return MYINSTANCE
     */
    public static NLEB007001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * getAssetGroupingCancelList
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public List<Map<String, Object>> getAssetGroupingCancelList(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("dsAssetMstrPk", bean.getDsAssetMstrPk());
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAssetGroupingCancelList", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrConfigInfoList
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public List<Map<String, Object>> getDsAssetMstrConfigInfoList(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("prntDsAssetMstrPk", bean.getPrntDsAssetMstrPk());
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsAssetMstrParent", queryParam).getResultObject();
    }

    /**
     * getParentDsAssetMstrTosvcConfigMstrPk
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsAssetMstrParentCc(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcConfigMstrPk", bean.getServcConfigMstrPk());
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsAssetMstrParentCc", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrParentAssetAdjOrDisposal
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsAssetMstrParentAssetAdjOrDisposal(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcMachMstrPk", bean.getSvcMachMstrPk());
        queryParam.put("assetPostFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("actvAssetFlg", ZYPConstant.FLG_ON_Y);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsAssetMstrParent", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrConfigurationChange
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsAssetMstrConfigurationChange(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcMachMstrPk", bean.getSvcMachMstrPk());
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsAssetMstrConfigurationChange", queryParam).getResultObject();
    }

    /**
     * getAcctMthCtrl
     * @param glblCmpyCd String
     * @return String
     */
    public String getAcctMthCtrl(String glblCmpyCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("acctMthCtrlCd", ACCT_MTH_CTRL.DEPRICIATION_CONTROL);
        return (String) getSsmEZDClient().queryObject("getAcctMthCtrl", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrPkRentalTermOfConv
     * @param bean DSAssetStagingInfoBean
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getDsAssetMstrPkRentalTermOfConv(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcConfigMstrPk", bean.getServcConfigMstrPk());
        queryParam.put("assetPostFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("actvAssetFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
        List<Map<String, Object>> ssmEZDClientList = (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsAssetMstrPkRentalTermOfConv", queryParam).getResultObject();
        return ssmEZDClientList;
    }

    /**
     * getDsAssetMstrPkPostData
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsAssetMstrPkPostData(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcConfigMstrPk", bean.getServcConfigMstrPk());
        queryParam.put("assetPostFlg", ZYPConstant.FLG_OFF_N);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsAssetMstrPkPostData", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrPkPostActive
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public List<Map<String, Object>> getDsAssetMstrPkPostActive(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcConfigMstrPk", bean.getServcConfigMstrPk());
        queryParam.put("assetPostFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("actvAssetFlg", ZYPConstant.FLG_ON_Y);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsAssetMstrPkPostActive", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrPkPost
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsAssetMstrPkPost(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcMachMstrPk", bean.getSvcMachMstrPk());
        queryParam.put("assetPostFlg", ZYPConstant.FLG_ON_Y);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsAssetMstrPkPost", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrPkReturn
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsAssetMstrPkReturn(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcMachMstrPk", bean.getSvcMachMstrPk());
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsAssetMstrPkReturn", queryParam).getResultObject();
    }

    /**
     * getCpoDtlFuncNetAmt(Mode:01(Rental Ship) and Mode:02(EMSD
     * Ship))
     * @param bean DSAssetStagingInfoBean
     * @return BigDecimal
     */
    public BigDecimal getCpoDtlFuncNetAmt(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("cpoOrdNum", bean.getCpoOrdNum());
        queryParam.put("dsOrdPosnNum", bean.getDsOrdPosnNum());

        String coaMdseTpCd = ZYPCodeDataUtil.getVarCharConstValue(COA_MDSE_TP_CD, bean.getGlblCmpyCd());
        List<String> coaMdseTpCdList = new ArrayList<String>();

        if (coaMdseTpCd != null) {
            String[] coaMdseTp = coaMdseTpCd.split(",");
            coaMdseTpCdList = Arrays.asList(coaMdseTp);
        }

        queryParam.put("coaMdseTpCd", coaMdseTpCdList);

        return (BigDecimal) getSsmEZDClient().queryObject("getCpoDtlFuncNetAmt", queryParam).getResultObject();
    }

    /**
     * getFuncCsmpCrAmt(Mode:01(Rental Ship) and Mode:02(EMSD Ship))
     * @param bean DSAssetStagingInfoBean
     * @return BigDecimal
     */
    public BigDecimal getFuncCsmpCrAmt(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("cpoOrdNum", bean.getCpoOrdNum());
        queryParam.put("cpoDtlLineNum", bean.getCpoDtlLineNum());
        queryParam.put("cpoDtlLineSubNum", bean.getCpoDtlLineSubNum());
        return (BigDecimal) getSsmEZDClient().queryObject("getFuncCsmpCrAmt", queryParam).getResultObject();
    }

    /**
     * getSvcMachMstrInfo
     * @param glblCmpyCd String
     * @param soNum String
     * @param soSlpNum String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcMachMstrInfo(String glblCmpyCd, String soNum, String soSlpNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("soNum", soNum);
        queryParam.put("soSlpNum", soSlpNum);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcMachMstrInfo", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrPk
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getDsAssetMstrPk(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcConfigMstrPk", bean.getServcConfigMstrPk());
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsAssetMstrPk", queryParam).getResultObject();
    }

    /**
     * Get MDSETMSg
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETMSg
     */
    public static MDSETMsg getMdseTMsg(String glblCmpyCd, String mdseCd) {
        MDSETMsg inTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, mdseCd);
        MDSETMsg outTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     * getINVTMsg
     * @param glblCmpyCd String
     * @param invNum String
     * @return INVTMsg
     */
    public INVTMsg getINVTMsg(String glblCmpyCd, String invNum) {
        INVTMsg inTMsg = new INVTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.invNum, invNum);
        INVTMsg outTMsg = (INVTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     * getDsRtlWhVTMsg
     * @param bean DSAssetStagingInfoBean
     * @return DS_RTL_WH_VTMsgArray
     */
    public DS_RTL_WH_VTMsgArray getDsRtlWhVTMsg(DSAssetStagingInfoBean bean) {

        DS_RTL_WH_VTMsg inTMsg = new DS_RTL_WH_VTMsg();
        inTMsg.setSQLID(SQLID);
        inTMsg.setConditionValue("glblCmpyCd01", bean.getGlblCmpyCd());
        inTMsg.setConditionValue("invtyLocCd01", bean.getRtnWhCd());
        DS_RTL_WH_VTMsgArray outVTMsg = (DS_RTL_WH_VTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return outVTMsg;
    }

    /**
     * getDsRtlWhVTMsg
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return DS_RTL_WH_VTMsgArray
     */
    public DS_RTL_WH_VTMsgArray getDsRtlWhVTMsg(String glblCmpyCd, String invtyLocCd) {

        DS_RTL_WH_VTMsg inTMsg = new DS_RTL_WH_VTMsg();
        inTMsg.setSQLID(SQLID);
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("invtyLocCd01", invtyLocCd);
        DS_RTL_WH_VTMsgArray outVTMsg = (DS_RTL_WH_VTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return outVTMsg;
    }

    /**
     * getDsAssetMstr
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return DS_ASSET_MSTRTMsgArray
     */
    public DS_ASSET_MSTRTMsgArray getDsAssetMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        DS_ASSET_MSTRTMsg inTMsg = new DS_ASSET_MSTRTMsg();
        inTMsg.setSQLID(SQLID);
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        DS_ASSET_MSTRTMsgArray outVTMsg = (DS_ASSET_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return outVTMsg;
    }

    /**
     * getShippingPlanTMsg
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @return SHPG_PLNTMsg
     */
    public SHPG_PLNTMsg getShippingPlanTMsg(String glblCmpyCd, String shpgPlnNum) {

        SHPG_PLNTMsg inTMsg = new SHPG_PLNTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.shpgPlnNum, shpgPlnNum);
        SHPG_PLNTMsg outTMsg = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     * getSvcMachMstr
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    public static SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg svcMachMstr = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstr.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstr.svcMachMstrPk, svcMachMstrPk);
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(svcMachMstr);
        return svcMachMstrTMsg;
    }

    /**
     * getCoaProj
     * @param glblCmpyCd String
     * @param coaMdseTpcd String
     * @return COA_PROJTMsg
     */
    public static COA_PROJTMsg getCoaProj(String glblCmpyCd, String coaMdseTpcd) {
        COA_PROJTMsg coaMdseTp = new COA_PROJTMsg();
        ZYPEZDItemValueSetter.setValue(coaMdseTp.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaMdseTp.coaProjCd, coaMdseTpcd);
        COA_PROJTMsg outCoaMdseTp = (COA_PROJTMsg) S21FastTBLAccessor.findByKey(coaMdseTp);
        return outCoaMdseTp;
    }

    /**
     * getCoaMdseTp
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return SHIP_TO_CUSTTMsg
     */
    public static SHIP_TO_CUSTTMsg getShipToCust(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg coaMdseTpTMsg = new SHIP_TO_CUSTTMsg();
        coaMdseTpTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        coaMdseTpTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        coaMdseTpTMsg.setSQLID("004");
        SHIP_TO_CUSTTMsgArray outCoaMdseTpTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(coaMdseTpTMsg);
        if (outCoaMdseTpTMsgArray.length() == 0) {
            return null;
        }
        return (SHIP_TO_CUSTTMsg) outCoaMdseTpTMsgArray.get(0);
    }

    /**
     * S21SsmExecutionParameter
     * @param queryParam queryParam
     * @param getExecPrm S21SsmExecutionParameter
     * @return PreparedStatement
     * @throws SQLException sql
     */
    public PreparedStatement getDsAssetStagingInfo(Map<String, Object> queryParam, S21SsmExecutionParameter getExecPrm) throws SQLException {
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NLEB007001Query.getInstance().getClass());
        return ssmLLClient.createPreparedStatement("getDsAssetStagingInfo", queryParam, getExecPrm);
    }

    /**
     * getSvcExchForReturn
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getSvcExchForReturn(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("dsAssetStgngPk", bean.getDsAssetStgngPk());
        queryParam.put("procModeCdIsSvcExch", PROC_MODE_41);
        queryParam.put("dsOrdCatgCdIsSvcExch", DS_ORD_CATG.SERVICE_EXCHANGE_CSA);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSvcExchForReturn", queryParam).getResultObject();
    }

    /**
     * Get Return type
     * @param bean DSAssetStagingInfoBean
     * @return String
     */
    public String getReturnType(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("dsAssetStgngPk", bean.getDsAssetStgngPk());
        queryParam.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);
        return (String) getSsmEZDClient().queryObject("getReturnType", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrParentSrvEx
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getOldDsAssetMstrSrvEx(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcConfigMstrPk", bean.getServcConfigMstrPk());
        queryParam.put("assetPostFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("actvAssetFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getOldDsAssetMstrSrvEx", queryParam).getResultObject();
    }

    /**
     * getTargetDsAssetMstrPkSevEx
     * @param bean DSAssetStagingInfoBean
     * @return BigDecimal
     */
    public BigDecimal getNewDsAssetMstrPkSevEx(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcMachMstrPk", bean.getSmmSvcMachMstrPk());
        queryParam.put("actvAssetFlg", ZYPConstant.FLG_ON_Y);
        return (BigDecimal) getSsmEZDClient().queryObject("getNewDsAssetMstrPkSevEx", queryParam).getResultObject();
    }

    /**
     * getCurDsAssetMstrSrvEx
     * @param glblCmpyCd String
     * @param dsAssetMstrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getCurDsAssetMstrSrvEx(String glblCmpyCd, BigDecimal dsAssetMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("prntDsAssetMstrPk", dsAssetMstrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getCurDsAssetMstrSrvEx", queryParam).getResultObject();
    }

    /**
     * getDsAssetMstrPk
     * @param glblCmpyCd String
     * @param dsAssetMstrPk BigDecimal
     * @return DS_ASSET_MSTRTMsg
     */
    public static DS_ASSET_MSTRTMsg getDsAssetMstrPk(String glblCmpyCd, BigDecimal dsAssetMstrPk) {

        DS_ASSET_MSTRTMsg inTMsg = new DS_ASSET_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsAssetMstrPk, dsAssetMstrPk);
        DS_ASSET_MSTRTMsg outTMsg = (DS_ASSET_MSTRTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     * getConfigMainMachineAssetMstr
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public Map<String, Object> getConfigMainMachineAssetMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("svcConfigMstrPk", svcConfigMstrPk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getConfigMainMachineAssetMstr", queryParam).getResultObject();
    }

    // START 2017/11/17 J.Kim [QC#17088,ADD]
    /**
     * getOriginalCpoTMsg
     * @param bean DSAssetStagingInfoBean
     * @return CPOTMsg
     */
    public CPOTMsg getOriginalCpoTMsg(DSAssetStagingInfoBean bean) {
        CPOTMsg inCpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(inCpoTMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(inCpoTMsg.cpoOrdNum, bean.getCpoOrdNum());
        CPOTMsg outTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(inCpoTMsg);
        return outTMsg;
    }
    // END 2017/11/17 J.Kim [QC#17088,ADD]

    // START 2017/12/08 J.Kim [QC#18127,ADD]
    /**
     * getTargetAssetMasterInfo
     * @param bean DSAssetStagingInfoBean
     * @return Map<String, Object>
     */
    // START 2018/02/19 J.Kim [QC#23431,MOD]
    //public List<Map<String, Object>> getTargetAssetMasterInfo(DSAssetStagingInfoBean bean) {
    //    Map<String, Object> queryParam = new HashMap<String, Object>();
    //    queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
    //    queryParam.put("svcConfigMstrPk", bean.getServcConfigMstrPk());
    //    queryParam.put("assetPostFlg", ZYPConstant.FLG_ON_Y);
    //    queryParam.put("actvAssetFlg", ZYPConstant.FLG_ON_Y);
    //    queryParam.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
    //    return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getDsAssetMstrAccessoryInfo", queryParam).getResultObject();
    //}
    public Map<String, Object> getTargetAssetMasterInfo(DSAssetStagingInfoBean bean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", bean.getGlblCmpyCd());
        queryParam.put("svcConfigMstrPk", bean.getServcConfigMstrPk());
        queryParam.put("svcMachMstrPk", bean.getSvcMachMstrPk());
        queryParam.put("assetPostFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("actvAssetFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("manEntryFlg", ZYPConstant.FLG_OFF_N);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsAssetMstrParent", queryParam).getResultObject();
    }
    // END 2018/02/19 J.Kim [QC#23431,MOD]
    // END 2017/12/08 J.Kim [QC#18127,ADD]
}
